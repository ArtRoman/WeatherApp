package ru.artroman.weatherapp.utils;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NetworkUtils {

	private final static String HTTP_ADDRESS_TEMPLATE = "http://export.yandex.ru/weather-ng/forecasts/%d.xml";

	private final static int BUFFER_SIZE_B = 8 * 1024;

	/**
	 * @param activity Activity context
	 * @param url      Url to parse
	 */
	public static String getCacheFilePathForUrl(Activity activity, String url) {
		File cacheFolder = Environment.getExternalStorageDirectory();
		//File cacheFolder =  activity.getFilesDir();
		String fileName = Uri.parse(url).getLastPathSegment();
		String cacheFilePath = cacheFolder.getAbsolutePath() + File.separator + fileName;

		return cacheFilePath;
	}

	public static String getUrlForCityId(int cityId){
		return String.format(HTTP_ADDRESS_TEMPLATE, cityId);
	}

	/**
	 * @param inputStream Input stream to read data from
	 * @param outputFile  File to write data
	 */
	public static void copyStreamToFile(InputStream inputStream, File outputFile) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE_B];
		int bufferLength;

		FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		while ((bufferLength = bufferedInputStream.read(buffer)) != -1) {
			fileOutputStream.write(buffer, 0, bufferLength);
		}
		fileOutputStream.close();
		inputStream.close();
	}

}
