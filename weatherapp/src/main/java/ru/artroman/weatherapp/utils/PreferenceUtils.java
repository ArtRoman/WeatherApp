package ru.artroman.weatherapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {

	public final static String SCROLL_POSITION = "viewpager_scroll_position";
	public final static String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
	public final static String PREF_LAST_CITY_SELECTED= "navigation_last_city_selected";

	private static PreferenceUtils instance;
	private final SharedPreferences preferences;

	public static PreferenceUtils getInstance(Context context) {
		if (instance == null) {
			instance = new PreferenceUtils(context);
		}
		return instance;
	}

	private PreferenceUtils(Context context) {
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public int getValue(String preferenceName, int defaultValue) {
		return preferences.getInt(preferenceName, defaultValue);
	}

	public String getValue(String preferenceName, String defaultValue) {
		return preferences.getString(preferenceName, defaultValue);
	}

	public boolean getValue(String preferenceName, boolean defaultValue) {
		return preferences.getBoolean(preferenceName, defaultValue);
	}

	public void setValue(String preferenceName, int value) {
		preferences.edit().putInt(preferenceName, value).commit();
	}

	public void setValue(String preferenceName, String value) {
		preferences.edit().putString(preferenceName, value).commit();
	}

	public void setValue(String preferenceName, boolean value) {
		preferences.edit().putBoolean(preferenceName, value).commit();
	}
}
