package com.mid.shouhuo.util;

import java.util.Calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mid.shouhuo.MainActivity;
import com.mid.shouhuo.R;
import com.mid.shouhuo.alarm.AlarmUtil;
import com.mid.shouhuo.db.HarvestDbAdapter;
import com.mid.shouhuo.db.PlanDbAdapter;
import com.mid.shouhuo.obj.Harvest;
import com.mid.shouhuo.obj.Plan;
import com.mid.shouhuo.ui.ContextListAdapter;
import com.mid.shouhuo.ui.ContextMenuItemListener;
import com.mid.shouhuo.ui.MyDialog;

public class DialogUtil {

	private final String TAG = "[DialogUtil]";

	private Context context;
	private MyDialog dialog;

	public DialogUtil(Context context) {
		this.context = context;
	}

	public DialogUtil(Context context, MyDialog upperDialog) {
		this.context = context;
	}

	public void showPlanAddDialog(boolean isDaily) {

		final Plan p = new Plan();
		p.setDaily(isDaily);

		MyDialog.Builder builder = new MyDialog.Builder(context);
		builder.setTitle("添加计划").setContentView(R.layout.dialog_add_plan);

		final View v = builder.getContentView();

		final RadioGroup radio_daily = (RadioGroup) v
				.findViewById(R.id.type_group);
		// ((RadioButton) rg.getChildAt(isDaily ? 1 : 0)).setChecked(true);
		radio_daily.check(isDaily ? R.id.type_daily : R.id.type_once);

		final Button btn_alarm = (Button) v.findViewById(R.id.plan_add_settime);

		btn_alarm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				MyDialog timeDialog = new DialogUtil(context)
						.showTimeSetDialog(p);
				timeDialog
						.setOnDismissListener(new DialogInterface.OnDismissListener() {

							@Override
							public void onDismiss(DialogInterface dialog) {
								if (p.isHasAlarm()) {
									Calendar calendar = Calendar.getInstance();
									calendar.setTimeInMillis(p.getAlarmTime());
									if (p.isDaily()) {
										btn_alarm.setText(calendar
												.get(Calendar.HOUR_OF_DAY)
												+ "时"
												+ calendar.get(Calendar.MINUTE)
												+ "分");
									} else {
										btn_alarm.setText(calendar
												.get(Calendar.YEAR)
												+ "年"
												+ (calendar.get(Calendar.MONTH) + 1)
												+ "月"
												+ calendar
														.get(Calendar.DAY_OF_MONTH)
												+ "日"
												+ calendar
														.get(Calendar.HOUR_OF_DAY)
												+ "时"
												+ calendar.get(Calendar.MINUTE)
												+ "分");
									}
								}
							}
						});
			}
		});

		builder.setPositiveButton("提  交",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String title = ((TextView) v
								.findViewById(R.id.plan_add_title)).getText()
								.toString();
						if (title == null || "".equals(title)) {
							Toast.makeText(context, "标题不能为空，请重新填写。",
									Toast.LENGTH_SHORT).show();
							return;
						}
						p.setTitle(title);
						p.setCreated(new DateUtil(context).getToday());
						boolean daily = radio_daily.getCheckedRadioButtonId() == R.id.type_daily;
						Log.v(TAG, "isDaily(): " + daily);
						p.setDaily(daily);
						if (daily) {
							p.setBeginDate(p.getCreated());
						}
						String remark = ((TextView) v
								.findViewById(R.id.plan_add_remark)).getText()
								.toString();
						p.setRemark(remark);

						PlanDbAdapter adapter = new PlanDbAdapter(context);
						long id = -1;
						try {
							id = adapter.open().insert(p);
							((MainActivity) context).prepareData();
						} finally {
							adapter.close();
						}
						if (id > -1) {
							if (p.isHasAlarm()) {
								new AlarmUtil(context).setAlarm(p);
							}
							Toast.makeText(context, "计划设定成功！",
									Toast.LENGTH_SHORT).show();
							dialog.dismiss();
						} else {
							Toast.makeText(context, "计划设定失败，请稍后再试。",
									Toast.LENGTH_SHORT).show();
							dialog.dismiss();
						}

					}
				}).setNegativeButton("取  消",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		dialog = builder.create();
		dialog.show();

	}

	public void showHarvestAddDialog() {
		final MyDialog.Builder builder = new MyDialog.Builder(context);
		dialog = builder
				.setTitle("添加收获")
				.setContentView(R.layout.dialog_add_harvest)
				.setPositiveButton("提  交",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								View v = builder.getContentView();
								String title = ((TextView) v
										.findViewById(R.id.harvest_add_title))
										.getText().toString();
								if (title == null || "".equals(title)) {
									Toast.makeText(context, "标题不能为空，请重新填写。",
											Toast.LENGTH_SHORT).show();
									return;
								}
								RadioGroup rg = (RadioGroup) v
										.findViewById(R.id.harvest_date_group);
								String date = new DateUtil(context).getToday();
								if (rg.getCheckedRadioButtonId() == R.id.date_yestoday) {
									date = new DateUtil(context).getYestoday();
								}
								String content = ""
										+ ((TextView) v
												.findViewById(R.id.harvest_add_content))
												.getText().toString();

								Harvest h = new Harvest();

								h.setContent(content);
								h.setCreated(date);
								h.setTitle(title);
								h.setDaily(false);

								HarvestDbAdapter adapter = new HarvestDbAdapter(
										context);
								long id = -1;
								try {
									id = adapter.open().insert(h);
									((MainActivity) context).prepareData();
								} finally {
									adapter.close();
								}
								if (id > -1) {
									Toast.makeText(context, "收获添加成功！",
											Toast.LENGTH_SHORT).show();
									dialog.dismiss();
								} else {
									Toast.makeText(context, "收获添加失败，请稍后再试。",
											Toast.LENGTH_SHORT).show();
									dialog.dismiss();
								}

							}
						})
				.setNegativeButton("取  消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).create();

		dialog.show();

	}

	public void showPlanEditDialog(final Plan p) {

		String titleStr = p.getTitle();
		// long id = p.get_id();
		String remarkStr = p.getRemark();
		boolean hasAlarm = p.isHasAlarm();
		String alarmTime = "";

		if (p.isHasAlarm()) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(p.getAlarmTime());
			if (p.isDaily()) {
				alarmTime = calendar.get(Calendar.HOUR_OF_DAY) + "时"
						+ calendar.get(Calendar.MINUTE) + "分";
				Log.v(TAG, "Daily Plan, Alarm Time" + alarmTime);
			} else {
				alarmTime = calendar.get(Calendar.YEAR) + "年"
						+ calendar.get(Calendar.MONTH) + "月"
						+ calendar.get(Calendar.DAY_OF_MONTH) + "日"
						+ calendar.get(Calendar.HOUR_OF_DAY) + "时"
						+ calendar.get(Calendar.MINUTE) + "分";
			}
		}
		// boolean isDaily = p.isDaily();

		MyDialog.Builder builder = new MyDialog.Builder(context);
		builder.setTitle("修改计划").setContentView(R.layout.dialog_add_plan);

		final View v = builder.getContentView();

		final RadioGroup radio_daily = (RadioGroup) v
				.findViewById(R.id.type_group);

		final TextView text_daily = (TextView) v
				.findViewById(R.id.text_plan_type);

		final EditText text_title = (EditText) v
				.findViewById(R.id.plan_add_title);
		final EditText text_remark = (EditText) v
				.findViewById(R.id.plan_add_remark);
		final Button btn_alarm = (Button) v.findViewById(R.id.plan_add_settime);

		// ((RadioButton) rg.getChildAt(isDaily ? 1 : 0)).setChecked(true);
		// radio_daily.check(isDaily ? R.id.type_daily : R.id.type_once);
		radio_daily.setVisibility(View.GONE);
		text_daily.setVisibility(View.GONE);

		text_title.setText(titleStr);
		text_remark.setText(remarkStr);
		if (hasAlarm) {
			btn_alarm.setText(alarmTime);
		}

		btn_alarm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MyDialog timeDialog = new DialogUtil(context)
						.showTimeSetDialog(p);
				timeDialog
						.setOnDismissListener(new DialogInterface.OnDismissListener() {

							@Override
							public void onDismiss(DialogInterface dialog) {
								if (p.isHasAlarm()) {
									Calendar calendar = Calendar.getInstance();
									calendar.setTimeInMillis(p.getAlarmTime());
									if (p.isDaily()) {
										btn_alarm.setText(calendar
												.get(Calendar.HOUR_OF_DAY)
												+ "时"
												+ calendar.get(Calendar.MINUTE)
												+ "分");
									} else {
										btn_alarm.setText(calendar
												.get(Calendar.YEAR)
												+ "年"
												+ calendar.get(Calendar.MONTH)
												+ "月"
												+ calendar
														.get(Calendar.DAY_OF_MONTH)
												+ "日"
												+ calendar
														.get(Calendar.HOUR_OF_DAY)
												+ "时"
												+ calendar.get(Calendar.MINUTE)
												+ "分");
									}
								}
							}
						});
			}
		});

		builder.setPositiveButton("提  交",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String title = ((EditText) v
								.findViewById(R.id.plan_add_title)).getText()
								.toString();
						if (title == null || "".equals(title)) {
							Toast.makeText(context, "标题不能为空，请重新填写。",
									Toast.LENGTH_SHORT).show();
							return;
						}
						p.setTitle(title);
						p.setCreated(new DateUtil(context).getToday());
						String remark = ((EditText) v
								.findViewById(R.id.plan_add_remark)).getText()
								.toString();
						p.setRemark(remark);

						PlanDbAdapter adapter = new PlanDbAdapter(context);
						long id = -1;
						if (p.isHasAlarm()) {
							AlarmUtil au = new AlarmUtil(context);
							au.cancelAlarm(p);
							au.setAlarm(p);
						}
						try {
							id = adapter.open().update(p);
							((MainActivity) context).prepareData();
						} finally {
							adapter.close();
						}
						if (id > -1) {
							Toast.makeText(context, "计划修改成功！",
									Toast.LENGTH_SHORT).show();
							dialog.dismiss();
						} else {
							Toast.makeText(context, "计划修改失败，请稍后再试。",
									Toast.LENGTH_SHORT).show();
							dialog.dismiss();
						}

					}
				}).setNegativeButton("取  消",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		dialog = builder.create();
		dialog.show();
	}

	public void showContextMenu(final Object obj) {
		MyDialog.Builder builder = new MyDialog.Builder(context);
		builder.setStyle(R.style.ContextMenu)
				.setLayout(R.layout.dialog_context_menu)
				.setContentView(R.layout.context_menu);

		final View v = builder.getContentView();

		int array = 0;
		if (obj instanceof Plan) {

			if (((Plan) obj).isDaily()) {
				array = R.array.context_plan_daily;
				Log.v(TAG, "Daily Plan");
			} else {
				array = R.array.context_plan_once;
				Log.v(TAG, "Once Plan");
			}
		} else if (obj instanceof Harvest) {
			array = R.array.context_harvest;
			Log.v(TAG, "Harvest");
		} else {
			Log.v(TAG, "Error!");
			// return;
		}
		Log.v(TAG, "array = " + array);
		ListView context_list = (ListView) v.findViewById(R.id.context_list);

		context_list.setAdapter(new ContextListAdapter<String>(context,
				R.layout.context_menu_item, R.id.context_menu_item_text,
				context.getResources().getStringArray(array)));

		dialog = builder.create();

		context_list.setOnItemClickListener(ContextMenuItemListener
				.getListener(obj, dialog));

		dialog.show();

	}

	public void showHarvestEditDialog(final Harvest h) {
		final MyDialog.Builder builder = new MyDialog.Builder(context);

		builder.setTitle("修改收获").setContentView(R.layout.dialog_add_harvest);

		final View v = builder.getContentView();

		final RadioGroup radio_date = (RadioGroup) v
				.findViewById(R.id.harvest_date_group);

		final TextView text_title = (TextView) v
				.findViewById(R.id.harvest_add_title);

		final TextView text_content = (TextView) v
				.findViewById(R.id.harvest_add_content);

		radio_date.setVisibility(View.GONE);
		v.findViewById(R.id.text_harvest_date).setVisibility(View.GONE);
		text_title.setText(h.getTitle());
		text_content.setText(h.getContent());

		builder.setPositiveButton("提  交",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						View v = builder.getContentView();
						String title = ((TextView) v
								.findViewById(R.id.harvest_add_title))
								.getText().toString();
						if (title == null || "".equals(title)) {
							Toast.makeText(context, "标题不能为空，请重新填写。",
									Toast.LENGTH_SHORT).show();
							return;
						}
						String content = ""
								+ ((TextView) v
										.findViewById(R.id.harvest_add_content))
										.getText().toString();

						h.setContent(content);
						h.setTitle(title);

						HarvestDbAdapter adapter = new HarvestDbAdapter(context);

						boolean flag = false;

						try {
							flag = adapter.open().update(h);
							((MainActivity) context).prepareData();
						} finally {
							adapter.close();
						}
						if (flag) {
							Toast.makeText(context, "收获修改成功！",
									Toast.LENGTH_SHORT).show();
							dialog.dismiss();
						} else {
							Toast.makeText(context, "收获修改失败，请稍后再试。",
									Toast.LENGTH_SHORT).show();
							dialog.dismiss();
						}

					}
				}).setNegativeButton("取  消",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		dialog = builder.create();
		dialog.show();

	}

	public void showConfirmFinish(final Plan p) {
		MyDialog.Builder builder = new MyDialog.Builder(context);

		String message = new String();

		if (p.isDaily()) {
			message = "完成后计划将保留";
		} else {
			message = "完成后计划将被删除";
		}

		builder.setTitle("确认完成计划？")
				.setMessage(message)
				.setPositiveButton("确  认",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialogInter,
									int which) {

								p.finish(dialog);
							}

						})
				.setNegativeButton("取  消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (dialog != null) {
									dialog.dismiss();
								}
								return;

							}
						});
		dialog = builder.create();
		dialog.show();
	}

	public void showConfirmDelete(final Plan p) {

		MyDialog.Builder builder = new MyDialog.Builder(context);

		String message = "计划删除后不可恢复，是否继续删除？";

		builder.setTitle("确认删除计划？")
				.setMessage(message)
				.setPositiveButton("确  认",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialogInter,
									int which) {

								p.delete(dialog);

							}

						})
				.setNegativeButton("取  消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (dialog != null) {
									dialog.dismiss();
								}
								return;

							}
						});
		dialog = builder.create();
		dialog.show();
	}

	public void showConfirmDelete(final Harvest h) {

		MyDialog.Builder builder = new MyDialog.Builder(context);

		String message = "收获删除后不可恢复，是否继续删除？";

		builder.setTitle("确认删除收获？")
				.setMessage(message)
				.setPositiveButton("确  认",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialogInter,
									int which) {

								h.delete(dialog);

							}

						})
				.setNegativeButton("取  消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (dialog != null) {
									dialog.dismiss();
								}
								return;

							}
						});
		dialog = builder.create();
		dialog.show();
	}

	public MyDialog showTimeSetDialog(final Plan p) {
		MyDialog.Builder builder = new MyDialog.Builder(context);
		builder.setStyle(R.style.TimeSetDialog)
				.setLayout(R.layout.dialog_set_time_layout).setTitle("选择时间")
				.setContentView(R.layout.dialog_set_time_content);

		View v = builder.getContentView();

		View[] elements = new View[5];
		elements[0] = (View) v.findViewById(R.id.year_pick);
		elements[1] = (View) v.findViewById(R.id.month_pick);
		elements[2] = (View) v.findViewById(R.id.day_pick);
		elements[3] = (View) v.findViewById(R.id.hour_pick);
		elements[4] = (View) v.findViewById(R.id.minute_pick);

		final ImageView[] increases = new ImageView[5];
		final ImageView[] decreases = new ImageView[5];
		final TextView[] hints = new TextView[5];

		for (int i = 0; i < 5; i++) {
			final int k = i;

			increases[i] = (ImageView) elements[i]
					.findViewById(R.id.btn_increase);
			decreases[i] = (ImageView) elements[i]
					.findViewById(R.id.btn_decrease);
			hints[i] = (TextView) elements[i].findViewById(R.id.hint);

			increases[i].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					int num = Integer.parseInt(hints[k].getText().toString());
					if (k == 4) {
						num += 5;
					} else {
						num++;
					}
					int max = 0;
					int min = 1;
					int y = Integer.parseInt(hints[0].getText().toString());
					if (k == 0) {
						y = num;
					}
					int m = Integer.parseInt(hints[1].getText().toString());
					if (k == 1) {
						m = num;
					}
					switch (k) {
					case 0:
						max = 9999;
						break;
					case 1:
						max = 12;
						break;
					case 2: // 最大日期
						max = DateUtil.getMaxDay(y, m);
						break;
					case 3:
						max = 23;
						min = 0;
						break;
					case 4:
						max = 55;
						min = 0;
						break;
					}

					if (num > max) {
						num = min;
					}

					if (Integer.parseInt(hints[2].getText().toString()) > DateUtil
							.getMaxDay(y, m)) {
						System.out.println("__________" + y + "年" + m + "月有"
								+ DateUtil.getMaxDay(y, m) + "天");
						int temp_d = Integer.parseInt(hints[2].getText()
								.toString());
						temp_d = 1;
						hints[2].setText(temp_d + "");
					}

					hints[k].setText(num + "");
				}

			});

			decreases[i].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					int num = Integer.parseInt(hints[k].getText().toString());
					if (k == 4) {
						num -= 5;
					} else {
						num--;
					}
					int max = 0;
					int min = 1;
					int y = Integer.parseInt(hints[0].getText().toString());
					if (k == 0) {
						y = num;
					}
					int m = Integer.parseInt(hints[1].getText().toString());
					if (k == 1) {
						m = num;
					}
					switch (k) {
					case 0:
						max = 9999;
						break;
					case 1:
						max = 12;
						break;
					case 2: // 最大日期
						max = DateUtil.getMaxDay(y, m);
						break;
					case 3:
						max = 23;
						min = 0;
						break;
					case 4:
						max = 55;
						min = 0;
						break;
					}
					if (num < min) {
						num = max;
					}

					if (Integer.parseInt(hints[2].getText().toString()) > DateUtil
							.getMaxDay(y, m)) {
						int temp_d = Integer.parseInt(hints[2].getText()
								.toString());
						temp_d = 1;
						hints[2].setText(temp_d + "");
					}

					hints[k].setText(num + "");
				}

			});
		}

		Calendar c = Calendar.getInstance();
		if (p.isHasAlarm()) {
			c.setTimeInMillis(p.getAlarmTime());
		} else {
			p.setAlarmTime(System.currentTimeMillis());
		}

		hints[0].setText(c.get(Calendar.YEAR) + "");
		hints[1].setText(c.get(Calendar.MONTH) + 1 + "");
		hints[2].setText(c.get(Calendar.DAY_OF_MONTH) + "");
		hints[3].setText(c.get(Calendar.HOUR_OF_DAY) + "");
		hints[4].setText((int) (Math.ceil(c.get(Calendar.MINUTE) / 5) * 5) + "");

		if (p.isDaily()) {
			for (int i = 0; i < 3; i++) {
				increases[i].setVisibility(View.GONE);
				decreases[i].setVisibility(View.GONE);
				hints[i].setVisibility(View.GONE);
			}
			v.findViewById(R.id.mark_text_year).setVisibility(View.GONE);
			v.findViewById(R.id.mark_text_month).setVisibility(View.GONE);
			v.findViewById(R.id.mark_text_day).setVisibility(View.GONE);
		}

		builder.setPositiveButton("确  定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialogInter, int which) {
						// Log.v(TAG, "positive click");
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.YEAR,
								Integer.parseInt(hints[0].getText().toString()));
						calendar.set(
								Calendar.MONTH,
								Integer.parseInt(hints[1].getText().toString()) - 1);
						calendar.set(Calendar.DAY_OF_MONTH,
								Integer.parseInt(hints[2].getText().toString()));
						calendar.set(Calendar.HOUR_OF_DAY,
								Integer.parseInt(hints[3].getText().toString()));
						calendar.set(Calendar.MINUTE,
								Integer.parseInt(hints[4].getText().toString()));
						if (Calendar.getInstance().after(calendar)) {
							// 提醒时间已过
							if (p.isDaily()) {
								calendar.add(Calendar.DAY_OF_MONTH, 1);
							} else {
								Toast.makeText(context, "提醒时间穿越了！再设置一下哦~",
										Toast.LENGTH_SHORT).show();
								return;
							}
						}

						p.setHasAlarm(true);
						p.setAlarmTime(calendar.getTimeInMillis());
						if (dialog != null) {
							dialog.dismiss();
						}
					}

				}).setNegativeButton("取  消",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Log.v(TAG, "negative click");
						p.setHasAlarm(false);
						if (dialog != null) {
							dialog.cancel();
						}
						return;

					}
				});

		dialog = builder.create();
		dialog.show();
		return dialog;
	}

	public void showAlarmDialog(final Plan p) {
		MyDialog.Builder builder = new MyDialog.Builder(context);
		builder.setTitle(p.getTitle());
		String message = p.getRemark();
		String attach = "\n轻触“完成”可以快速完成计划哦~";
		if (!"".equals(message)) {
			builder.setMessage("备注：" + message + attach);
		} else {
			builder.setMessage("这个计划没有备注" + attach);
		}
		builder.setPositiveButton("完  成",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialogInter, int which) {
						p.finish(dialog);
					}
				}).setNegativeButton("知道了",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		dialog = builder.create();
		dialog.show();
	}
}
