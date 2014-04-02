package ru.artroman.weatherapp.streams;

import android.os.AsyncTask;
import org.simpleframework.xml.core.Persister;
import ru.artroman.weatherapp.fragment.MainContentFragment;
import ru.yandex.weather.forecast.Forecast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Async task to process XML on non-UI thread
 */
public class XmlParser extends AsyncTask<File, Void, Forecast> {

	private ParseResultListener mListener;

	public XmlParser(MainContentFragment listener) {
		try {
			mListener = listener;
		} catch (ClassCastException e) {
			throw new ClassCastException("Class must implement ParseResultListener");
		}
	}

	@Override
	protected Forecast doInBackground(File... params) {
		Forecast forecast = null;
		File xmlFile = params[0];
		if (xmlFile == null) return null;
		try {
			Persister serializer = new Persister();
			InputStream dataFileInputStream = new FileInputStream(xmlFile);
			forecast = serializer.read(Forecast.class, dataFileInputStream, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forecast;
	}

	@Override
	protected void onPostExecute(Forecast forecast) {
		mListener.onParseCompleted(forecast);
	}


	public interface ParseResultListener {
		void onParseCompleted(Forecast parsedForecast);
	}
}
