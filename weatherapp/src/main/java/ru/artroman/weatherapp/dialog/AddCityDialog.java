package ru.artroman.weatherapp.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import ru.artroman.weatherapp.R;

public class AddCityDialog extends DialogFragment implements DialogInterface.OnClickListener, AdapterView.OnItemClickListener, TextWatcher {


	public interface AddCityDialogListener {

		public void onDialogPositiveClick(AddCityDialog dialog, String inputTextValue);

		public void onDialogNegativeClick(AddCityDialog dialog);
	}

	private static String[] cityNamesArray;
	private AddCityDialogListener mListener;
	private String inputTextValue;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		cityNamesArray = getResources().getStringArray(R.array.cities_names);

		// Verify that the host activity implements the callback interface
		try {
			mListener = (AddCityDialogListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement AddCityDialogListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_add_city, null);
		builder.setView(dialogView);
		builder.setTitle(R.string.dialog_add_city_title);
		builder.setPositiveButton(android.R.string.ok, this);
		builder.setNegativeButton(android.R.string.cancel, this);

		// Set adapter to AutoCompleteTextView
		if (dialogView != null) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, cityNamesArray);
			AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialogView.findViewById(R.id.dialog_textview_city_name);
			autoCompleteTextView.setAdapter(adapter);
			autoCompleteTextView.setOnItemClickListener(this);
			autoCompleteTextView.addTextChangedListener(this);
		}

		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int id) {
		switch (id) {
			case DialogInterface.BUTTON_POSITIVE:
				mListener.onDialogPositiveClick(this, inputTextValue);
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				mListener.onDialogNegativeClick(this);
				break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		inputTextValue = (String) parent.getAdapter().getItem(position);
	}

	@Override
	public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable editable) {
		inputTextValue = editable.toString().trim();
	}

}
