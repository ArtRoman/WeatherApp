package ru.artroman.weatherapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ru.artroman.weatherapp.R;

public class DB {

	private static final String DB_NAME = "weather.db";
	private static final int DB_VERSION = 2;

	public static final String DB_TABLE_CITIES = "cities";

	public static final String CITIES_COLUMN_ID = "_id";
	public static final String CITIES_COLUMN_CITY_ID = "city_id";
	public static final String CITIES_COLUMN_CITY_NAME = "city_name";
	public static final String CITIES_COLUMN_CITY_NAME_UPPER = "city_name_upper";

	public static final String DB_TABLE_NAVIGATION_CONTENT = "navigation";

	public static final String NAVIGATION_COLUMN_ID = "_id";
	public static final String NAVIGATION_COLUMN_CITY_ID = "city_id";


	private static final String DB_CREATE_CITIES = "create table " + DB_TABLE_CITIES + "(" +
			CITIES_COLUMN_ID + " integer primary key autoincrement, " +
			CITIES_COLUMN_CITY_ID + " integer, " +
			CITIES_COLUMN_CITY_NAME + " text, " +
			CITIES_COLUMN_CITY_NAME_UPPER + " text" +
			");";
	private static final String DB_CREATE_NAVIGATION = "create table " + DB_TABLE_NAVIGATION_CONTENT + " (" +
			NAVIGATION_COLUMN_ID + " integer primary key autoincrement," +
			NAVIGATION_COLUMN_CITY_ID + " integer" +
			");";

	private static SQLiteDatabase mDB;
	private static DBHelper mDBHelper;

	public DB(Context context) {
		if (mDBHelper == null) {
			mDBHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
		}
		if (mDB == null) {
			mDB = mDBHelper.getReadableDatabase();
		}
	}

	public Cursor getAllNavigationCitiesCursor() {
		return mDB.rawQuery("select " + DB_TABLE_NAVIGATION_CONTENT + "." + CITIES_COLUMN_ID + ", " + CITIES_COLUMN_CITY_NAME +
				" from " + DB_TABLE_NAVIGATION_CONTENT + " join " + DB_TABLE_CITIES +
				" on " + DB_TABLE_NAVIGATION_CONTENT + "." + NAVIGATION_COLUMN_CITY_ID + " = " + DB_TABLE_CITIES + "." + CITIES_COLUMN_CITY_ID, null);
	}

	public int getFirstCityIdFromNavigation() {
		int cityId = 0;
		String[] selectColumns = new String[]{NAVIGATION_COLUMN_CITY_ID};
		Cursor cursor = mDB.query(DB_TABLE_NAVIGATION_CONTENT, selectColumns, null, null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			cityId = cursor.getInt(0);
		}
		return cityId;
	}

	public String getCityNameByCityId(int cityId) {
		String cityName = "";
		String[] selectColumns = new String[]{CITIES_COLUMN_CITY_NAME};
		String selection = CITIES_COLUMN_CITY_ID + " = ?";
		String[] selectionArgs = new String[]{String.valueOf(cityId)};
		Cursor cursor = mDB.query(DB_TABLE_CITIES, selectColumns, selection, selectionArgs, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			cityName = cursor.getString(0);
		}
		return cityName;
	}

	public int getCityIdByCityName(String cityName) {
		int cityId = -1;
		String[] selectColumns = new String[]{CITIES_COLUMN_CITY_ID};
		String selection = CITIES_COLUMN_CITY_NAME_UPPER + " = ?";
		String[] selectionArgs = new String[]{cityName.toUpperCase()};
		Cursor cursor = mDB.query(DB_TABLE_CITIES, selectColumns, selection, selectionArgs, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			cityId = cursor.getInt(0);
		}
		return cityId;
	}


	public String[] getAllCitiesAsArray() {
		Cursor cursor = mDB.query(DB_TABLE_CITIES, null, null, null, null, null, null);

		int namesCount = cursor.getCount();
		String[] result = new String[namesCount];
		cursor.moveToFirst();
		for (int i = 0; i < namesCount; i++) {
			int columnIndex = cursor.getColumnIndex(DB.CITIES_COLUMN_CITY_NAME);
			String cityname = cursor.getString(columnIndex);
			result[i] = cityname;
			cursor.moveToNext();
		}
		return result;
	}


	public void addCityToNavigation(int cityId) {
		ContentValues cv = new ContentValues();
		cv.put(NAVIGATION_COLUMN_CITY_ID, cityId);
		mDB.insert(DB_TABLE_NAVIGATION_CONTENT, null, cv);
	}

	public int getCityInNavigation(int navigationItemId) {
		int cityId = -1;
		String[] selectColumns = new String[]{NAVIGATION_COLUMN_CITY_ID};
		String selection = NAVIGATION_COLUMN_ID + " = ?";
		String[] selectionArgs = new String[]{String.valueOf(navigationItemId)};
		Cursor cursor = mDB.query(DB_TABLE_NAVIGATION_CONTENT, selectColumns, selection, selectionArgs, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			cityId = cursor.getInt(0);
		}
		return cityId;
	}

	public void removeCityFromNavigation(long id) {
		String whereClause = NAVIGATION_COLUMN_ID + " = ?";
		String[] whereArgs = new String[]{String.valueOf(id)};
		mDB.delete(DB_TABLE_NAVIGATION_CONTENT, whereClause, whereArgs);
	}


	private class DBHelper extends SQLiteOpenHelper {

		private Context mContext;

		public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
			super(context, name, factory, version);
			mContext = context;
		}

		// Create DB
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DB_CREATE_CITIES);
			db.execSQL(DB_CREATE_NAVIGATION);

			insertDefaultCitiesContent(db);
			insertDefaultNavigationContent(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			switch (newVersion) {
				case 2:
					// drop cities table
					db.execSQL("drop table " + DB_TABLE_CITIES);
					// create cities table
					db.execSQL(DB_CREATE_CITIES);
					// and insert default data
					insertDefaultCitiesContent(db);
					break;
			}
		}


		private void insertDefaultCitiesContent(SQLiteDatabase db) {
			int[] allCitiesIdsArray = mContext.getResources().getIntArray(R.array.cities_ids);
			String[] allCitiesNamesArray = mContext.getResources().getStringArray(R.array.cities_names);

			// Put all data to DB
			ContentValues cv = new ContentValues();
			for (int i = 0; i < allCitiesIdsArray.length; i++) {
				cv.put(CITIES_COLUMN_CITY_ID, allCitiesIdsArray[i]);
				cv.put(CITIES_COLUMN_CITY_NAME, allCitiesNamesArray[i]);
				cv.put(CITIES_COLUMN_CITY_NAME_UPPER, allCitiesNamesArray[i].toUpperCase());
				db.insert(DB_TABLE_CITIES, null, cv);
			}
		}

		private void insertDefaultNavigationContent(SQLiteDatabase db) {
			int[] defaultNavigationArray = mContext.getResources().getIntArray(R.array.default_navigation_ids);

			// Add default cities
			ContentValues cv = new ContentValues();
			for (int aDefaultNavigationArray : defaultNavigationArray) {
				cv.put(NAVIGATION_COLUMN_CITY_ID, aDefaultNavigationArray);
				db.insert(DB_TABLE_NAVIGATION_CONTENT, null, cv);
			}
		}
	}
}
