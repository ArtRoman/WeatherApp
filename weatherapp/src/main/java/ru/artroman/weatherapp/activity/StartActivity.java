package ru.artroman.weatherapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import ru.artroman.weatherapp.R;
import ru.artroman.weatherapp.db.DB;
import ru.artroman.weatherapp.dialog.AddCityDialog;
import ru.artroman.weatherapp.fragment.MainContentFragment;
import ru.artroman.weatherapp.fragment.NavigationDrawerFragment;

import java.util.Arrays;
import java.util.List;

public class StartActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, AddCityDialog.AddCityDialogListener {


	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CharSequence mTitle;
	private List<String> cityNamesList;
	private int[] cityIdsArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		mTitle = getTitle();
		// Set up the drawer.
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// Update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.container, MainContentFragment.newInstance(position + 1));
		transaction.commit();
	}

	public void onSectionAttached(int number) {
		mTitle = getString(R.string.app_name);
	}


	/**
	 * Handling ActionBar menu items
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
			Toast.makeText(this, R.string.action_refresh, Toast.LENGTH_SHORT).show();
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
	 * Dialog listener
	 */
	@Override
	public void onDialogPositiveClick(AddCityDialog dialog, String inputTextValue) {

		if (cityNamesList == null) cityNamesList = Arrays.asList(getResources().getStringArray(R.array.cities_names));
		if (cityIdsArray == null) cityIdsArray = getResources().getIntArray(R.array.cities_ids);

		int index = cityNamesList.indexOf(inputTextValue);
		if (index < 0) {
			alert(R.string.toast_not_found);
		} else if (index < cityIdsArray.length) {
			int cityId = cityIdsArray[index];
			addCityToNavigationDrawer(cityId, inputTextValue);
		}
	}

	@Override
	public void onDialogNegativeClick(AddCityDialog dialog) {

	}


	private void addCityToNavigationDrawer(int cityId, String cityName) {

		DB db = new DB(getApplication());
		db.addCity(cityId, cityName);

		mNavigationDrawerFragment.updateNavigationData();
	}


	private void alert(int resourceString) {
		alert(getString(resourceString));
	}

	private void alert(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

}
