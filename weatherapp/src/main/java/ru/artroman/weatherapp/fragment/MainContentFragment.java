package ru.artroman.weatherapp.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import ru.artroman.weatherapp.R;
import ru.artroman.weatherapp.activity.StartActivity;
import ru.artroman.weatherapp.db.DB;
import ru.artroman.weatherapp.streams.FileRetriever;
import ru.artroman.weatherapp.streams.XmlParser;
import ru.artroman.weatherapp.utils.NetworkUtils;
import ru.yandex.weather.forecast.Day;
import ru.yandex.weather.forecast.DayPart;
import ru.yandex.weather.forecast.Forecast;
import ru.yandex.weather.forecast.Temperature;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A fragment containing a simple view.
 */
public class MainContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, StartActivity.OnDataUpdateListener, XmlParser.ParseResultListener {

	private final static SimpleDateFormat UPTIME_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private final static SimpleDateFormat DAY_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private final static String ARG_SECTION_NUMBER = "section_number";

	private SwipeRefreshLayout mSwipeRefreshLayout;
	private int mSelectedCityId;
	private View mRootView;

	public MainContentFragment() {

	}

	public static MainContentFragment newInstance(int sectionNumber) {
		MainContentFragment fragment = new MainContentFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_main, container, false);
		if (mRootView == null) return null;

		int navigationItemId = getArguments().getInt(ARG_SECTION_NUMBER);

		DB db = new DB(getActivity());
		mSelectedCityId = db.getCityInNavigation(navigationItemId);

		mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_container);
		mSwipeRefreshLayout.setColorScheme(android.R.color.holo_green_light, android.R.color.holo_red_light, android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
		mSwipeRefreshLayout.setOnRefreshListener(this);

		if (savedInstanceState == null) {
			// If not restoring activity, check for data update
			File cacheFile = new File(NetworkUtils.getCacheFilePathByCityId(getActivity(), mSelectedCityId));
			if (cacheFile.exists()) {
				updateViewFromCachedXmlFile();
			} else {
				initiateDownloadingContentForCurrentCityId();
			}
		}
		return mRootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((StartActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
	}

	/**
	 * Refresh initiated from SwipeRefreshLayout
	 */
	@Override
	public void onRefresh() {
		initiateDownloadingContentForCurrentCityId();
	}

	/**
	 * File downloading result
	 */
	@Override
	public void onDataUpdateRequested() {
		initiateDownloadingContentForCurrentCityId();
	}

	@Override
	public void onDataUpdateCompleted() {
		mSwipeRefreshLayout.setRefreshing(false);
		updateViewFromCachedXmlFile();
	}

	/**
	 * Listener of XML parse completition
	 */
	@Override
	public void onParseCompleted(Forecast forecast) {
		if (forecast == null) return;

		// Update UI
		ProgressBar progressBar = (ProgressBar) mRootView.findViewById(R.id.main_progressbar);
		LinearLayout mainLayout = (LinearLayout) mRootView.findViewById(R.id.main_layout);

		progressBar.setVisibility(View.GONE);
		mainLayout.setVisibility(View.VISIBLE);

		Date parsedDate = parseDate(forecast.getFact().getUptime(), UPTIME_DATE_FORMAT);

		setText(R.id.main_city_name, forecast.getCity());
		setText(R.id.main_date, getUserFriendlyDate(parsedDate));
		setText(R.id.main_day_of_week, getDayOfWeekByDate(parsedDate));
		setText(R.id.main_temp, String.format(getString(R.string.main_temperature_template), formatTemperature(forecast.getFact().getTemperature().getContent())));
		setText(R.id.main_humidity, String.format(getString(R.string.main_humidity_template), forecast.getFact().getHumidity()));
		setText(R.id.main_pressure, String.format(getString(R.string.main_pressure_template), forecast.getFact().getPressure().getContent()));
		setText(R.id.main_wind, String.format(getString(R.string.main_wind_template), forecast.getFact().getWindSpeed()));
		setText(R.id.main_weather_status, forecast.getFact().getWeatherType());

		List<Day> daysList = forecast.getDay();
		int numOfDays = daysList.size();
		for (int i = 0; i < numOfDays; i++) {
			Day day = daysList.get(i);
			List<DayPart> dayPartsList = day.getDayPart();

			if (i == 0) {
				// Current day
				for (DayPart dayPart : dayPartsList) {
					String temperature;
					Temperature temp = dayPart.getTemperature();
					if (temp != null) {
						temperature = formatTemperature(temp.getContent());
					} else {
						// Use middle value
						temperature = formatTemperature(dayPart.getTemperatureFrom(), dayPart.getTemperatureTo());
					}
					String temperatureFormatted = String.format(getString(R.string.main_temperature_template), temperature);
					if ("morning".equals(dayPart.getType())) {
						setText(R.id.main_text_daypart1, temperatureFormatted);
					} else if ("day".equals(dayPart.getType())) {
						setText(R.id.main_text_daypart2, temperatureFormatted);
					} else if ("evening".equals(dayPart.getType())) {
						setText(R.id.main_text_daypart3, temperatureFormatted);
					} else if ("night".equals(dayPart.getType())) {
						setText(R.id.main_text_daypart4, temperatureFormatted);
					}
				}

			} else if (i < 8) {
				// Next days are shown as list
				Date date = parseDate(day.getDate(), DAY_DATE_FORMAT);
				String friendlyDate = getUserFriendlyDate(date);
				String dayOfWeek = getDayOfWeekByDate(date);
				String tempDay = null;
				String tempNight = null;
				for (DayPart dayPart : dayPartsList) {
					if ("day_short".equals(dayPart.getType())) {
						tempDay = formatTemperature(dayPart.getTemperature().getContent());
					} else if ("night_short".equals(dayPart.getType())) {
						tempNight = formatTemperature(dayPart.getTemperature().getContent());
					}
				}
				String formattedDayTemp = String.format(getString(R.string.main_next_day_temp_template), tempNight, tempDay);

				if (i == 1) {
					setText(R.id.main_next_day_date_1, friendlyDate);
					setText(R.id.main_next_day_temp_1, formattedDayTemp);
					setText(R.id.main_next_day_of_week_1, dayOfWeek);
				} else if (i == 2) {
					setText(R.id.main_next_day_date_2, friendlyDate);
					setText(R.id.main_next_day_temp_2, formattedDayTemp);
					setText(R.id.main_next_day_of_week_2, dayOfWeek);
				} else if (i == 3) {
					setText(R.id.main_next_day_date_3, friendlyDate);
					setText(R.id.main_next_day_temp_3, formattedDayTemp);
					setText(R.id.main_next_day_of_week_3, dayOfWeek);
				} else if (i == 4) {
					setText(R.id.main_next_day_date_4, friendlyDate);
					setText(R.id.main_next_day_temp_4, formattedDayTemp);
					setText(R.id.main_next_day_of_week_4, dayOfWeek);
				} else if (i == 5) {
					setText(R.id.main_next_day_date_5, friendlyDate);
					setText(R.id.main_next_day_temp_5, formattedDayTemp);
					setText(R.id.main_next_day_of_week_5, dayOfWeek);
				} else if (i == 6) {
					setText(R.id.main_next_day_date_6, friendlyDate);
					setText(R.id.main_next_day_temp_6, formattedDayTemp);
					setText(R.id.main_next_day_of_week_6, dayOfWeek);
				} else if (i == 7) {
					setText(R.id.main_next_day_date_7, friendlyDate);
					setText(R.id.main_next_day_temp_7, formattedDayTemp);
					setText(R.id.main_next_day_of_week_7, dayOfWeek);
				}

			} else {
				// Skip more days
				break;
			}
		}
	}


	void initiateDownloadingContentForCurrentCityId() {
		mSwipeRefreshLayout.setRefreshing(true);

		String url = NetworkUtils.getUrlForCityId(mSelectedCityId);
		String outputFilePath = NetworkUtils.getCacheFilePathByUrl(getActivity(), url);
		FileRetriever retriever = new FileRetriever(getActivity());
		retriever.execute(url, outputFilePath);
	}

	private void updateViewFromCachedXmlFile() {
		File cachedFile = new File(NetworkUtils.getCacheFilePathByCityId(getActivity(), mSelectedCityId));
		if (mRootView == null || !cachedFile.exists()) return;
		XmlParser parser = new XmlParser(this);
		parser.execute(cachedFile);
	}

	private void setText(int textViewResourceId, String value) {
		if (mRootView == null) return;
		TextView textView = (TextView) mRootView.findViewById(textViewResourceId);
		if (textView != null) {
			textView.setText(value);
		}
	}

	/**
	 * Normalizing temperature value to normal view
	 *
	 * @param tempValue String value of temperature
	 */
	private String formatTemperature(String tempValue) {
		if (tempValue == null) return null;
		if (!tempValue.startsWith("-")) {
			tempValue = "+" + tempValue;
		}
		return tempValue;
	}

	/**
	 * Normalizing temperature value to normal view as average value from two
	 *
	 * @param tempValue1 String value of first temperature
	 * @param tempValue2 String value of second temperature
	 */
	private String formatTemperature(String tempValue1, String tempValue2) {
		if (tempValue1 == null || tempValue2 == null) return null;
		int temp1 = Integer.parseInt(tempValue1);
		int temp2 = Integer.parseInt(tempValue2);
		int averageTemp = (temp1 + temp2) / 2;
		return formatTemperature(String.valueOf(averageTemp));
	}

	/**
	 * Return Date parsed from text in UPTIME_DATE_FORMAT
	 *
	 * @param textDate Input date to parse
	 */
	private Date parseDate(String textDate, SimpleDateFormat format) {
		Date date = null;
		try {
			date = format.parse(textDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Returns localized day of week
	 *
	 * @param date Input date to read
	 */
	private String getDayOfWeekByDate(Date date) {
		if (date == null) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
		String[] dayOfWeekNames = getResources().getStringArray(R.array.day_of_week_names);
		return dayOfWeekNames[dayOfWeekNum - 1];
	}

	/**
	 * Returns localized date string
	 *
	 * @param date Input date to read
	 */
	private String getUserFriendlyDate(Date date) {
		if (date == null) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		String[] monthNames = getResources().getStringArray(R.array.month_names);
		return day + " " + monthNames[month] + " " + year;
	}
}
