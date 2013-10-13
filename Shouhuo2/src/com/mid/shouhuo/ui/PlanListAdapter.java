package com.mid.shouhuo.ui;

import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mid.shouhuo.R;
import com.mid.shouhuo.alarm.AlarmUtil;
import com.mid.shouhuo.db.PlanDbAdapter;
import com.mid.shouhuo.obj.Plan;
import com.mid.shouhuo.util.DialogUtil;

public class PlanListAdapter extends BaseAdapter {

	private final String TAG = "[PlanListAdapter]";

	private Context context;

	private List<Plan> data;

	private PlanDbAdapter adapter;
	private LayoutInflater inflater;

	private boolean isDaily;

	private ViewHolder holder;

	public PlanListAdapter(Context context, boolean isDaily) {
		this.context = context;
		this.isDaily = isDaily;
		this.adapter = new PlanDbAdapter(context);
		this.inflater = LayoutInflater.from(context);

		prepareData();
	}

	private class ViewHolder {
		TextView textView;
		CheckBox checkBox;
	}

	private void prepareData() {
		data = adapter.open().query(isDaily);
		adapter.close();
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return ((Plan) getItem(position)).get_id();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.plan_list_item, null);

			holder = new ViewHolder();

			holder.textView = (TextView) convertView
					.findViewById(R.id.plan_item_title);
			holder.checkBox = (CheckBox) convertView
					.findViewById(R.id.alarm_box);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// View wrapper = convertView.findViewById(R.id.alarm_wrapper);

		final Plan p = (Plan) getItem(position);

		holder.textView.setText(p.getTitle());
		holder.checkBox.setChecked(p.isHasAlarm());
		holder.checkBox.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final CheckBox box = (CheckBox) v;

				boolean isChecked = box.isChecked();

				if (isChecked) { // 设置提醒
					MyDialog dialog = new DialogUtil(context)
							.showTimeSetDialog(p);

					dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

						@Override
						public void onDismiss(DialogInterface dialog) {
							if (p.isHasAlarm()) { // 提醒设置成功
								new AlarmUtil(context).setAlarm(p);
								adapter.open().update(p);
								adapter.close();
								box.setChecked(true);
								dialog.dismiss();
							} else {
								box.setChecked(false);
								dialog.dismiss();
							}
						}

					});
				} else { // 取消提醒
					p.setHasAlarm(false);
					new AlarmUtil(context).cancelAlarm(p);
				}
			}

		});
		return convertView;
	}
}
