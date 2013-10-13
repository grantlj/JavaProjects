package com.mid.shouhuo.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mid.shouhuo.R;
import com.mid.shouhuo.db.HarvestDbAdapter;
import com.mid.shouhuo.obj.Harvest;
import com.mid.shouhuo.obj.SingleDay;

public class HarvestListListAdapter extends BaseAdapter {

	// private String TAG = "HarvestListListAdapter";

	private Context context;
	private LayoutInflater inflater;

	private HarvestDbAdapter adapter;

	private SingleDay day;

	private boolean isToday;

	private List<Harvest> list;

	static class ViewHolder {
		TextView text;
		LinearLayout wrapper;
		TextView times;
	}

	HarvestListListAdapter(Context context, SingleDay day, boolean isToday) {
		this.context = context;
		this.day = day;
		this.isToday = isToday;
		inflater = LayoutInflater.from(this.context);
		adapter = new HarvestDbAdapter(this.context);
		prepareData();
	}

	private void prepareData() {
		if (isToday) {
			list = new ArrayList<Harvest>();
		}
		list = adapter.open().query(day.getDate());
		adapter.close();
	}

	@Override
	public int getCount() {
		// System.out.println("num: " + num + "count:" + texts[num].length);
		return isToday ? list.size() + 1 : list.size();
	}

	@Override
	public Object getItem(int position) {
		if (isToday) {
			if (position == 0) {
				return " ";
			} else {
				return list.get(position - 1);
			}
		} else {
			return list.get(position);
		}
	}

	@Override
	public long getItemId(int position) {
		if (getItem(position) instanceof String) {
			return -1;
		}
		return ((Harvest) getItem(position)).get_id();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.harvest_item_item, null);

			holder = new ViewHolder();
			holder.text = (TextView) convertView
					.findViewById(R.id.harvest_item_title);
			holder.wrapper = (LinearLayout) convertView
					.findViewById(R.id.daily_wrapper);
			holder.times = (TextView) convertView
					.findViewById(R.id.harvest_item_times);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Object o = getItem(position);

		if (o instanceof String) {

			/*Drawable drawable = context.getResources().getDrawable(
					R.drawable.icon_add);
			drawable.setBounds(0, -10, drawable.getIntrinsicWidth() + 10,
					drawable.getIntrinsicHeight());
			SpannableString sStr = new SpannableString("  ");
			sStr.setSpan(new ImageSpan(drawable), 0, 2,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			*/
			holder.text.setText("+");
			// holder.text.setTextColor(Color.parseColor("e96908"));
			holder.wrapper.setVisibility(View.GONE);

		} else {

			Harvest h = (Harvest) o;

			holder.text.setText(h.getTitle());

			if (h.isDaily()) {
				holder.wrapper.setVisibility(View.VISIBLE);
				holder.times.setText("µÚ" + h.getDays() + "Ìì");
			} else {
				holder.wrapper.setVisibility(View.GONE);
			}
		}

		return convertView;
	}
}
