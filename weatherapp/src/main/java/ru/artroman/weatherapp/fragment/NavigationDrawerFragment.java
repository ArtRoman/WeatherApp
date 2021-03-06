package ru.artroman.weatherapp.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import ru.artroman.weatherapp.R;
import ru.artroman.weatherapp.db.DB;
import ru.artroman.weatherapp.dialog.AddCityDialog;
import ru.artroman.weatherapp.dialog.PromtRemoveCityDialog;
import ru.artroman.weatherapp.utils.PreferenceUtils;

public class NavigationDrawerFragment extends Fragment implements AdapterView.OnItemClickListener {

	private final static String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
	private final static int DEFAULT_SECTION = 1;

	private NavigationDrawerCallbacks mCallbacks;
	private ActionBarDrawerToggle mDrawerToggle;

	private DrawerLayout mDrawerLayout;
	private SimpleCursorAdapter mAdapter;
	private PreferenceUtils mPreferenceUtils;
	private View mFragmentContainerView;
	private DB mDbHelper;

	private int mCurrentSelectedid;
	private boolean mFromSavedInstanceState;
	private boolean mUserLearnedDrawer;
	private boolean isEditModeEnabled;

	public NavigationDrawerFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mPreferenceUtils = PreferenceUtils.getInstance(getActivity());

		// Read in the flag indicating whether or not the user has demonstrated awareness of the drawer.
		mUserLearnedDrawer = mPreferenceUtils.getValue(PreferenceUtils.PREF_USER_LEARNED_DRAWER, false);
		int mLastCitySelected = mPreferenceUtils.getValue(PreferenceUtils.PREF_LAST_CITY_SELECTED, DEFAULT_SECTION);

		if (savedInstanceState != null) {
			mCurrentSelectedid = savedInstanceState.getInt(STATE_SELECTED_POSITION);
			mFromSavedInstanceState = true;
		} else {
			mCurrentSelectedid = mLastCitySelected;
		}

		// Select either the default item (0) or the last selected item.
		selectItem(mCurrentSelectedid);
		mDbHelper = new DB(getActivity());
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Indicate that this fragment would like to influence the set of actions in the action bar.
		setHasOptionsMenu(true);
	}

	@Override
	@SuppressWarnings({"ConstantConditions", "deprecation"})
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ListView mDrawerListView = (ListView) inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

		String[] columns = new String[]{DB.CITIES_COLUMN_CITY_NAME};
		int[] layoutIds = new int[]{R.id.navigation_item_text};
		mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.navigation_drawer_list_item, mDbHelper.getAllNavigationCitiesCursor(), columns, layoutIds);
		updateNavigationData();

		mDrawerListView.setAdapter(mAdapter);
		mDrawerListView.setOnItemClickListener(this);
		return mDrawerListView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (isEditModeEnabled) {
			PromtRemoveCityDialog dialog = new PromtRemoveCityDialog(id);
			dialog.show(getFragmentManager(), "PromtRemoveCityDialog");
		} else {
			selectItem((int) id);
		}
	}

	public boolean isDrawerOpen() {
		return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
	}

	/**
	 * Users of this fragment must call this method to set up the navigation drawer interactions.
	 *
	 * @param fragmentId   The android:id of this fragment in its activity's layout.
	 * @param drawerLayout The DrawerLayout containing this fragment's UI.
	 */
	public void setUp(int fragmentId, DrawerLayout drawerLayout) {
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;

		// set a custom shadow that overlays the main content when the drawer opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// set up the drawer's list view with items and click listener
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions between the navigation drawer and the action bar app icon.
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, R.drawable.ic_drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				if (!isAdded()) {
					return;
				}
				changeEditMode(false);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!isAdded()) {
					return;
				}

				if (!mUserLearnedDrawer) {
					// The user manually opened the drawer; store this flag to prevent auto-showing the navigation drawer automatically in the future.
					mUserLearnedDrawer = true;
					mPreferenceUtils.setValue(PreferenceUtils.PREF_USER_LEARNED_DRAWER, true);
				}
				getActivity().supportInvalidateOptionsMenu();
			}
		};

		// If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer, per the navigation drawer design guidelines.
		if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
			mDrawerLayout.openDrawer(mFragmentContainerView);
		}

		// Defer code dependent on restoration of previous instance state.
		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private void selectItem(int itemId) {
		mCurrentSelectedid = itemId;
		if (mDrawerLayout != null) {
			mDrawerLayout.closeDrawer(mFragmentContainerView);
		}
		if (mCallbacks != null) {
			mCallbacks.onNavigationDrawerItemSelected(itemId);
		}
		mPreferenceUtils.setValue(PreferenceUtils.PREF_LAST_CITY_SELECTED, mCurrentSelectedid);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (NavigationDrawerCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedid);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Forward the new configuration the drawer toggle component.
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// If the drawer is open, show the global app actions in the action bar. See also
		// showGlobalContextActionBar, which controls the top-left area of the action bar.
		if (mDrawerLayout != null && isDrawerOpen()) {

			int menuResourceToInflate = isEditModeEnabled ? R.menu.navdrawer_edit : R.menu.navdrawer;
			inflater.inflate(menuResourceToInflate, menu);
			showGlobalContextActionBar();
		}
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		int actionId = item.getItemId();
		switch (actionId) {
			case R.id.action_add_city:
				DialogFragment dialog = new AddCityDialog();
				dialog.show(getFragmentManager(), "AddCityDialog");
				return true;

			case R.id.action_edit:
				changeEditMode(true);
				return true;

			case R.id.action_edit_done:
				changeEditMode(false);
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Call to switch ActionBar menu to edit mode and back
	 */
	private void changeEditMode(boolean isEditEnabled) {
		isEditModeEnabled = isEditEnabled;
		if (isEditModeEnabled) {
			Toast.makeText(getActivity(), R.string.navdrawer_select_to_delete, Toast.LENGTH_SHORT).show();
		}
		getActivity().supportInvalidateOptionsMenu();
	}

	/**
	 * Per the navigation drawer design guidelines, updates the action bar to show the global app
	 * 'context', rather than just what's in the current screen.
	 */
	private void showGlobalContextActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setTitle(R.string.app_name);
	}

	public void addCityToBavigationDrawer(int cityId) {
		mDbHelper.addCityToNavigation(cityId);
		updateNavigationData();
	}

	public void removeCityFromNavigationDrawer(long id) {
		mDbHelper.removeCityFromNavigation(id);
		updateNavigationData();
	}

	public void updateNavigationData() {
		Cursor newCursor = mDbHelper.getAllNavigationCitiesCursor();
		mAdapter.changeCursor(newCursor);
	}

	private ActionBar getActionBar() {
		return ((ActionBarActivity) getActivity()).getSupportActionBar();
	}


	/**
	 * Callbacks interface that all activities using this fragment must implement.
	 */
	public static interface NavigationDrawerCallbacks {
		void onNavigationDrawerItemSelected(int itemId);
	}
}
