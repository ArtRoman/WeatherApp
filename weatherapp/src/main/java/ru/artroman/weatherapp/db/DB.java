package ru.artroman.weatherapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB {

	private static final String DB_NAME = "weatherdb";
	private static final int DB_VERSION = 1;
	public static final String DB_TABLE_CITIES = "cities";

	public static final String CITIES_COLUMN_ID = "_id";
	public static final String CITIES_COLUMN_CITY_ID = "city_id";
	public static final String CITIES_COLUMN_CITY_NAME = "city_name";

	private static final String DB_CREATE =
			"create table " + DB_TABLE_CITIES + "(" +
					CITIES_COLUMN_ID + " integer primary key, " +
					CITIES_COLUMN_CITY_ID + " integer, " +
					CITIES_COLUMN_CITY_NAME + " text" +
					");";


	private SQLiteDatabase mDB;


	public DB(Context ctx) {
		Context mContext = ctx;
		DBHelper mDBHelper = new DBHelper(mContext, DB_NAME, null, DB_VERSION);
		mDB = mDBHelper.getWritableDatabase();
	}


	public Cursor getAllCitiesCursor() {
		return mDB.query(DB_TABLE_CITIES, null, null, null, null, null, null);
	}


	public String[] getAllCitiesAsArray() {
		Cursor cursor = mDB.query(DB_TABLE_CITIES, null, null, null, null, null, null);

		int namesCount = cursor.getCount();
		String[] result = new String[namesCount];
		cursor.moveToFirst();
		for (int i = 0; i < namesCount; i++) {
			int columnIndex = cursor.getColumnIndex(DB.CITIES_COLUMN_CITY_NAME);
			Log.d("", i + ") columnIndex = " + columnIndex);
			if (columnIndex >= 0) {
				String cityname = cursor.getString(columnIndex);
				result[i] = cityname;
			}
			cursor.moveToNext();
		}
		return result;
	}

	/*public void setValueById(int id, String txt) {
		ContentValues cv = new ContentValues();
		cv.put(CITIES_COLUMN_CITY_ID, txt);
		mDB.update(DB_TABLE_CITIES, cv, CITIES_COLUMN_ID + " = " + id, null);
	}*/

	public void addCity(int cityId, String cityName) {

		ContentValues cv = new ContentValues();
		//cv.put(CITIES_COLUMN_ID, i);
		cv.put(CITIES_COLUMN_CITY_ID, cityId);
		cv.put(CITIES_COLUMN_CITY_NAME, cityName);
		mDB.insert(DB_TABLE_CITIES, null, cv);

	}


	private class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		// Create DB
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DB_CREATE);

			ContentValues cv = new ContentValues();
			int i = 0;

			{
				// msk
				//cv.put(CITIES_COLUMN_ID, i);
				cv.put(CITIES_COLUMN_CITY_ID, 27612);
				cv.put(CITIES_COLUMN_CITY_NAME, "Москва");
				db.insert(DB_TABLE_CITIES, null, cv);
				i++;
			}

			{
				// spb
				//cv.put(CITIES_COLUMN_ID, i);
				cv.put(CITIES_COLUMN_CITY_ID, 26063);
				cv.put(CITIES_COLUMN_CITY_NAME, "Санкт-Петербург");
				db.insert(DB_TABLE_CITIES, null, cv);
				i++;
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
	}
}
