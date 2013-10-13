package com.mid.shouhuo.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mid.shouhuo.R;
import com.mid.shouhuo.db.HarvestDbAdapter;
import com.mid.shouhuo.obj.Harvest;
import com.mid.shouhuo.obj.SingleDay;
import com.mid.shouhuo.util.DateUtil;
import com.mid.shouhuo.util.DialogUtil;

public class HarvestListAdapter extends BaseAdapter {

	private String TAG = "HarvestListAdapter";

	private Context context;

	private LayoutInflater layoutInflater;
	private HarvestDbAdapter adapter;

	private List<SingleDay> days;

	private String today;

	static class ViewHolder {
		TextView date;
		ListView list;
	}

	public HarvestListAdapter(Context context) {
		this.context = context;
		adapter = new HarvestDbAdapter(context);
		layoutInflater = LayoutInflater.from(context);
		today = new DateUtil(this.context).getToday();
		prepareData();
	}

	private void prepareData() {
		List<SingleDay> temp = adapter.open().getDays();
		adapter.close();
		Log.v(TAG, "singleDay counts : " + temp.size());
		days = new ArrayList<SingleDay>();

		// int i = 0;
		for (SingleDay d : temp) {
			Log.v(TAG, "singleDay : " + d);
		}

		if (temp.size() == 0
				|| !today.equals((((SingleDay) temp.get(0)).getDate()))) {
			Log.v(TAG, "今天无收获");
			days.add(new SingleDay().setDate(today));
		}
		days.addAll(temp);
	}

	@Override
	public int getCount() {
		return days.size();
	}

	@Override
	public Object getItem(int position) {
		return days.get(position);
	}

	@Override
	public long getItemId(int position) {
		return ((SingleDay) days.get(position)).get_id();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		// 初始化convertView
		if (convertView == null) {
			// inflate布局
			convertView = layoutInflater.inflate(R.layout.harvest_list_item,
					null);

			// 初始化holder
			holder = new ViewHolder();
			holder.date = (TextView) convertView
					.findViewById(R.id.harvest_date);
			holder.list = (ListView) convertView
					.findViewById(R.id.harvest_list_list);

			// setTag
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 初始化数据
		String rawDate = ((SingleDay) getItem(position)).getDate();
		String date = rawDate;
		final boolean isToday = rawDate.equals(today);
		if (isToday) {
			date = "今天";
		} else {
			date = new DateUtil(context).formatDate(rawDate);
		}
		Log.v(TAG, "date: " + date);
		holder.date.setText(date);
		holder.list.setAdapter(new HarvestListListAdapter(context,
				(SingleDay) getItem(position), isToday));
		holder.list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.v(TAG + "_listlistener", "position: " + position + "  id:"
						+ id);
				if (id == -1) {
					Log.v(TAG, "add harvest");
					new DialogUtil(context).showHarvestAddDialog();
				} else {

					/*HarvestDbAdapter adapter = new HarvestDbAdapter(context);
					adapter.open().delete(id);
					adapter.close();
					((MainActivity) context).prepareData();*/

					Harvest h = (Harvest) parent.getItemAtPosition(position);
					new DialogUtil(context).showHarvestEditDialog(h);

				}
			}
		});

		holder.list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Harvest h = (Harvest) parent.getItemAtPosition(position);
				new DialogUtil(context).showContextMenu(h);
				return true;
			}

		});

		if (position == this.getCount() - 1) {
			holder.list.setPadding(0, 0, 0, 100);
		} else {
			holder.list.setPadding(0, 0, 0, 0);
		}

		return convertView;
	}
}
