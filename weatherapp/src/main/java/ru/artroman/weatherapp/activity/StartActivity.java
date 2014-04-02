package ru.artroman.weatherapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import ru.artroman.weatherapp.R;
import ru.artroman.weatherapp.db.DB;
import ru.artroman.weatherapp.dialog.AddCityDialog;
import ru.artroman.weatherapp.dialog.PromtRemoveCityDialog;
import ru.artroman.weatherapp.fragment.MainContentFragment;
import ru.artroman.weatherapp.fragment.NavigationDrawerFragment;
import ru.artroman.weatherapp.streams.FileRetriever;

import java.io.File;

public class StartActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks,
		AddCityDialog.AddCityDialogListener, PromtRemoveCityDialog.PromtRemoveCityDialogListener, FileRetriever.OnDownloadCompleteListener {

	private NavigationDrawerFragment mNavigationDrawerFragment;
	private OnDataUpdateListener mListener;
	private CharSequence mTitle;
	private DB mDbHelper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		// Set up the drawer.
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

		mTitle = getTitle();
		mDbHelper = new DB(getApplication());
	}

	@Override
	public void onNavigationDrawerItemSelected(int itemId) {
		// Update the main content by replacing fragments
		MainContentFragment fragment = MainContentFragment.newInstance(itemId);
		mListener = fragment;
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.container, fragment);
		transaction.commit();
		updateTitle(itemId);
	}

	public void onSectionAttached(int sectionId) {
		updateTitle(sectionId);
	}

	private void updateTitle(int sectionId) {
		if (mDbHelper == null) return;
		int cityId = mDbHelper.getCityInNavigation(sectionId);
		String cityName = mDbHelper.getCityNameByCityId(cityId);
		if (!TextUtils.isEmpty(cityName)) {
			mTitle = cityName;
		}
	}

	/**
	 * Handling ActionBar items
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			getMenuInflater().inflate(R.menu.start, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int actionId = item.getItemId();
		if (actionId == R.id.action_refresh) {
			mListener.onDataUpdateRequested();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	/**
	 * AddCityDialogListener dialogs listener
	 */
	@Override
	public void onDialogPositiveClick(AddCityDialog dialog, String inputTextValue) {
		int cityId = mDbHelper.getCityIdByCityName(inputTextValue);
		if (cityId < 0) {
			Toast.makeText(this, R.string.dialog_add_city_not_found, Toast.LENGTH_SHORT).show();
		} else {
			mNavigationDrawerFragment.addCityToBavigationDrawer(cityId);
		}
	}

	@Override
	public void onDialogNegativeClick(AddCityDialog dialog) {

	}

	/**
	 * PromtRemoveCityDialogListener dialogs listener
	 */
	@Override
	public void onDialogPositiveClick(PromtRemoveCityDialog dialog, long id) {
		mNavigationDrawerFragment.removeCityFromNavigationDrawer(id);
	}

	@Override
	public void onDialogNegativeClick(PromtRemoveCityDialog dialog) {

	}

	/**
	 * File downloading result
	 */
	@Override
	public void onDownloadSuccess(File downloadedFile) {
		mListener.onDataUpdateCompleted();
	}

	@Override
	public void onDownloadError(int errorMessageId) {
		Toast.makeText(getApplication(), errorMessageId, Toast.LENGTH_SHORT).show();
		mListener.onDataUpdateCompleted();
	}


	public interface OnDataUpdateListener {

		void onDataUpdateRequested();

		void onDataUpdateCompleted();
	}

}
