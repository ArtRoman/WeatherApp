package ru.artroman.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ru.artroman.weatherapp.R;


public class NavigationDrawerListAdapter extends BaseAdapter {

	private final static String EMPTY_STRING = "";
	private final LayoutInflater mInflater;
	private String[] pageTitles;


	public NavigationDrawerListAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return pageTitles.length;
	}

	@Override
	public String getItem(int position) {
		return pageTitles[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	@SuppressWarnings("ConstantConditions")
	public View getView(int position, View convertView, ViewGroup parent) {
		View listItemView;
		TextView textView;
		//View blockView;


		listItemView = (convertView == null) ? mInflater.inflate(R.layout.navigation_drawer_list_item, parent, false) : convertView;
		textView = (TextView) listItemView.findViewById(R.id.navigation_item_text);

		String text = EMPTY_STRING;
		if (position < pageTitles.length) {
			text = pageTitles[position];
		}
		textView.setText(text);

		return listItemView;
	}

	public void setPageTitles(String[] pageTitles) {
		this.pageTitles = pageTitles;
		notifyDataSetInvalidated();
	}
}
