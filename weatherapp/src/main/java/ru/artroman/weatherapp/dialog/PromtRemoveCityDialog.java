package ru.artroman.weatherapp.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;
import ru.artroman.weatherapp.R;
import ru.artroman.weatherapp.db.DB;

public class PromtRemoveCityDialog extends DialogFragment implements DialogInterface.OnClickListener {

	private PromtRemoveCityDialogListener mListener;
	private long navigationItemToRemove;
	private String cityNameToRemove;
	private DB mDbHelper;

	public PromtRemoveCityDialog(long id) {
		navigationItemToRemove = id;
		mDbHelper = new DB(getActivity());
		int cityId = mDbHelper.getCityInNavigation((int) id);
		cityNameToRemove = mDbHelper.getCityNameByCityId(cityId);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Verify that the host activity implements the callback interface
		try {
			mListener = (PromtRemoveCityDialogListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement PromtRemoveCityDialogListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int citiesInNavigationCount = mDbHelper.getAllNavigationCitiesCursor().getCount();

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.dialog_remove_city_title);
		builder.setNegativeButton(android.R.string.cancel, this);

		if (citiesInNavigationCount > 1) {
			String messageToDisplay = String.format(getString(R.string.dialog_remove_city), cityNameToRemove);
			builder.setMessage(messageToDisplay);
			builder.setPositiveButton(android.R.string.ok, this);
		} else {
			builder.setMessage(R.string.dialog_remove_city_illegal);
		}
		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int id) {
		switch (id) {
			case DialogInterface.BUTTON_POSITIVE:
				String textToShow = String.format(getString(R.string.dialog_remove_city_toast_removed), cityNameToRemove);
				Toast.makeText(getActivity(), textToShow, Toast.LENGTH_SHORT).show();
				mListener.onDialogPositiveClick(this, navigationItemToRemove);
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				mListener.onDialogNegativeClick(this);
				break;
		}
	}


	public interface PromtRemoveCityDialogListener {

		public void onDialogPositiveClick(PromtRemoveCityDialog dialog, long id);

		public void onDialogNegativeClick(PromtRemoveCityDialog dialog);
	}
}
