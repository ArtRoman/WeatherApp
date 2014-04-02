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
import android.widget.TextView;
import ru.artroman.weatherapp.R;
import ru.artroman.weatherapp.activity.StartActivity;
import ru.artroman.weatherapp.db.DB;
import ru.artroman.weatherapp.streams.FileRetriever;
import ru.artroman.weatherapp.streams.XmlParser;
import ru.artroman.weatherapp.utils.NetworkUtils;
import ru.yandex.weather.forecast.Forecast;

import java.io.File;

/**
 * A fragment containing a simple view.
 */
public class MainContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, StartActivity.OnDataUpdateListener, XmlParser.ParseResultListener {

	private static final String ARG_SECTION_NUMBER = "section_number";
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

		// Show data if cache exists
		updateViewFromCachedXmlFile();

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

	/***/
	@Override
	public void onParseCompleted(Forecast forecast) {

		if (forecast == null) return;

		TextView textCityName = (TextView) mRootView.findViewById(R.id.main_city_name);
		TextView textDayOfWeek = (TextView) mRootView.findViewById(R.id.main_day_of_week);
		TextView textHumidity = (TextView) mRootView.findViewById(R.id.main_humidity);
		TextView textPressure = (TextView) mRootView.findViewById(R.id.main_pressure);
		TextView textTemperature = (TextView) mRootView.findViewById(R.id.main_temp);
		TextView textWeatherStatus = (TextView) mRootView.findViewById(R.id.main_weather_status);
		TextView textWind = (TextView) mRootView.findViewById(R.id.main_wind);

		TextView textDayPart1Title = (TextView) mRootView.findViewById(R.id.main_text_daypart1_title);
		TextView textDayPart2Title = (TextView) mRootView.findViewById(R.id.main_text_daypart2_title);
		TextView textDayPart3Title = (TextView) mRootView.findViewById(R.id.main_text_daypart3_title);
		TextView textDayPart4Title = (TextView) mRootView.findViewById(R.id.main_text_daypart4_title);
		TextView textDayPart1 = (TextView) mRootView.findViewById(R.id.main_text_daypart1);
		TextView textDayPart2 = (TextView) mRootView.findViewById(R.id.main_text_daypart2);
		TextView textDayPart3 = (TextView) mRootView.findViewById(R.id.main_text_daypart3);
		TextView textDayPart4 = (TextView) mRootView.findViewById(R.id.main_text_daypart4);

		textCityName.setText(forecast.getCity());
		//textDayOfWeek.setText(forecast.getCity());
		textHumidity.setText(forecast.getFact().getHumidity());
		textPressure.setText(forecast.getFact().getPressure().getContent());
		textTemperature.setText(forecast.getFact().getTemperature().getContent());
		textWeatherStatus.setText(forecast.getFact().getWeatherType());
		textWind.setText(forecast.getFact().getWindSpeed());

		//textDayPart1Title.setText(forecast.getCity());
		//textDayPart2Title.setText(forecast.getCity());
		//textDayPart3Title.setText(forecast.getCity());
		//textDayPart4Title.setText(forecast.getCity());
		//textDayPart1.setText(forecast.getDay().);
		//textDayPart2.setText(forecast.getCity());
		//textDayPart3.setText(forecast.getCity());
		//textDayPart4.setText(forecast.getCity());
	}


	void initiateDownloadingContentForCurrentCityId() {
		mSwipeRefreshLayout.setRefreshing(true);

		String url = NetworkUtils.getUrlForCityId(mSelectedCityId);
		String outputFilePath = NetworkUtils.getCacheFilePathForUrl(getActivity(), url);
		FileRetriever retriever = new FileRetriever(getActivity());
		retriever.execute(url, outputFilePath);
	}

	private void updateViewFromCachedXmlFile() {
		File cachedFile = new File(NetworkUtils.getCacheFilePathForUrl(getActivity(), NetworkUtils.getUrlForCityId(mSelectedCityId)));
		if (mRootView == null || !cachedFile.exists()) return;
		XmlParser parser = new XmlParser(this);
		parser.execute(cachedFile);
	}

}
