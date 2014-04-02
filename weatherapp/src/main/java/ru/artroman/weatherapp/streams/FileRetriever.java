package ru.artroman.weatherapp.streams;

import android.app.Activity;
import android.os.AsyncTask;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import ru.artroman.weatherapp.R;
import ru.artroman.weatherapp.utils.NetworkUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.zip.GZIPInputStream;

/**
 * File donwloader class as AsyncTask.
 */
public class FileRetriever extends AsyncTask<String, Integer, File> {

	private final static long MAXIMUM_FILESIZE_TO_DOWNLOAD_B = 1024 * 1024;
	private final static int HTTP_CONNECTION_TIMEOUT_MS = 5000;
	private final static String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
	private final static String HEADER_ENCODING_TYPE_GZIP = "gzip";
	private final static String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
	private final static String HEADER_LANGUAGE_TYPE_RU = "ru-RU,ru";

	private final static int PARAM_URL = 0;
	private final static int PARAM_FILE_PATH = 1;

	private OnDownloadCompleteListener mListener;
	private int mErrorMessageId;

	public FileRetriever(Activity activity) {
		try {
			mListener = (OnDownloadCompleteListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnDownloadCompleteListener");
		}
	}

	/**
	 * AsyncTask background task to download file
	 *
	 * @param params Two string params as URL to download from and OutputFilePath to write into
	 */
	@Override
	protected File doInBackground(String... params) {

		String url = params[PARAM_URL];
		String downloadToFilePath = params[PARAM_FILE_PATH];

		File downloadedFile = null;
		mErrorMessageId = 0;

		try {
			HttpGet httpGet = new HttpGet(url);
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setSoTimeout(httpParams, HTTP_CONNECTION_TIMEOUT_MS);
			HttpConnectionParams.setConnectionTimeout(httpParams, HTTP_CONNECTION_TIMEOUT_MS);
			httpGet.setParams(httpParams);
			httpGet.addHeader(HEADER_ACCEPT_ENCODING, HEADER_ENCODING_TYPE_GZIP);
			httpGet.addHeader(HEADER_ACCEPT_LANGUAGE, HEADER_LANGUAGE_TYPE_RU);

			DefaultHttpClient http = new DefaultHttpClient();
			HttpResponse response = http.execute(httpGet);
			HttpEntity entity = response.getEntity();
			Header responseContentEncodingHeader = entity.getContentEncoding();
			int mResponseStatusCode = response.getStatusLine().getStatusCode();
			long fileSizeExpected = entity.getContentLength();

			// fileSizeExpected is negative if unknown, so we accept only not zero and less than MAX limit
			if ((mResponseStatusCode == HttpStatus.SC_OK || mResponseStatusCode == HttpStatus.SC_NOT_MODIFIED)
					&& (fileSizeExpected != 0 && fileSizeExpected < MAXIMUM_FILESIZE_TO_DOWNLOAD_B)) {

				InputStream inputStream = entity.getContent();

				if (responseContentEncodingHeader != null && HEADER_ENCODING_TYPE_GZIP.equals(responseContentEncodingHeader.getValue())) {
					inputStream = new GZIPInputStream(inputStream);
				}

				downloadedFile = new File(downloadToFilePath);
				NetworkUtils.copyStreamToFile(inputStream, downloadedFile);
			} else {
				throw new HttpException();
			}

		} catch (FileNotFoundException e) {
			mErrorMessageId = R.string.error_file_not_found_exception;
		} catch (ClientProtocolException e) {
			mErrorMessageId = R.string.error_clien_protocol_exception;
		} catch (ConnectTimeoutException e) {
			mErrorMessageId = R.string.error_connect_timeout_exception;
		} catch (UnknownHostException e) {
			mErrorMessageId = R.string.error_unknown_host_exception;
		} catch (IllegalArgumentException e) {
			mErrorMessageId = R.string.error_illegal_argument_exception;
		} catch (IOException e) {
			mErrorMessageId = R.string.error_io_exception;
		} catch (HttpException e) {
			mErrorMessageId = R.string.error_http_exception;
		}

		return downloadedFile;
	}

	@Override
	protected void onPostExecute(File file) {
		if (mErrorMessageId != 0) {
			mListener.onDownloadError(mErrorMessageId);
		} else {
			mListener.onDownloadSuccess(file);
		}
	}


	public interface OnDownloadCompleteListener {

		public void onDownloadSuccess(File downloadedFile);

		public void onDownloadError(int errorMessageId);
	}

}
