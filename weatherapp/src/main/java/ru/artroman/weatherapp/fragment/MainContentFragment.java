package ru.artroman.weatherapp.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import ru.artroman.weatherapp.R;
import ru.artroman.weatherapp.activity.StartActivity;
import ru.artroman.weatherapp.db.DB;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private static SwipeRefreshLayout mSwipeRefreshLayout;

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
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		if (rootView == null) return null;

		int navigationItemId = getArguments().getInt(ARG_SECTION_NUMBER);

		DB db = new DB(getActivity());
		int cityId = db.getCityInNavigation(navigationItemId);
		String cityName = db.getCityNameByCityId(cityId);

		TextView textView = (TextView) rootView.findViewById(R.id.section_label);
		textView.setText("Selected item #" + navigationItemId + ", cityId=" + cityId + ", cityName=" + cityName);

		// Refresh layout
		mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_container);
		mSwipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
		mSwipeRefreshLayout.setOnRefreshListener(this);

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((StartActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
	}

	@Override
	public void onRefresh() {

		Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_SHORT).show();

		if (mSwipeRefreshLayout != null && !mSwipeRefreshLayout.isRefreshing()) {
			mSwipeRefreshLayout.setRefreshing(true);
		}

		new Handler().postDelayed(stopRefreshDelayed, 2000);
	}

	private Runnable stopRefreshDelayed = new Runnable() {
		@Override
		public void run() {
			if (mSwipeRefreshLayout != null) {
				mSwipeRefreshLayout.setRefreshing(false);
			}
		}
	};
}
