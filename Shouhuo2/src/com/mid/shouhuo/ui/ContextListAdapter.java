package com.mid.shouhuo.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mid.shouhuo.R;

public class ContextListAdapter<T> extends ArrayAdapter<String> {

	// private final String TAG = "[SettingListAdapter]";

	public ContextListAdapter(Context context, int resource,
			int textViewResourceId, String[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = super.getView(position, convertView, parent);

		if (view != null) {
			if (position == 0) {
				view.setBackgroundResource(R.drawable.context_menu_item_up);
			}

			if (position == getCount() - 1) {
				view.setBackgroundResource(R.drawable.context_menu_item_bottom);
			}
		}

		return view;
	}

}
