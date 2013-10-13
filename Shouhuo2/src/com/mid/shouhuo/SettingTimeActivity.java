package com.mid.shouhuo;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mid.shouhuo.util.SPUtil;

public class SettingTimeActivity extends Activity {

	// private static final String TAG = "[SettingTime]";

	private ImageView btn_hour_inc;
	private ImageView btn_hour_dec;
	private ImageView btn_min_inc;
	private ImageView btn_min_dec;

	private TextView hint_hour;
	private TextView hint_min;

	private String endTime;
	private int hour;
	private int min;

	private Button btn_submit;

	private SPUtil spUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting_time);
		// 锁定竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		spUtil = new SPUtil(this);

		TextView title_text = (TextView) findViewById(R.id.setting_title_text);
		Button btn_back = (Button) findViewById(R.id.setting_btn_back);

		title_text.setText("睡眠时间");
		btn_back.setText("设置");
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		findView();

		endTime = spUtil.getEndTime();

		hour = Integer.parseInt(endTime.substring(0, 2));
		min = Integer.parseInt(endTime.substring(2, 4));
		freshHint();
		btn_hour_inc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addHour();
			}

		});

		btn_min_inc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addMin();
			}

		});

		btn_hour_dec.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				decHour();
			}

		});

		btn_min_dec.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				decMin();
			}

		});

		btn_submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DecimalFormat formater = new DecimalFormat("00");
				endTime = formater.format(hour) + formater.format(min);
				// Log.v(TAG, "endTime: " + endTime);
				if (spUtil.setEndTime(endTime)) {
					Toast.makeText(SettingTimeActivity.this, "时间设置成功",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(SettingTimeActivity.this, "时间设置失败",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	private void findView() {
		View v_hour = findViewById(R.id.hour_pick);
		View v_min = findViewById(R.id.minute_pick);
		btn_hour_inc = (ImageView) v_hour.findViewById(R.id.btn_increase);
		btn_hour_dec = (ImageView) v_hour.findViewById(R.id.btn_decrease);
		btn_min_inc = (ImageView) v_min.findViewById(R.id.btn_increase);
		btn_min_dec = (ImageView) v_min.findViewById(R.id.btn_decrease);

		hint_hour = (TextView) v_hour.findViewById(R.id.hint);
		hint_min = (TextView) v_min.findViewById(R.id.hint);

		btn_submit = (Button) findViewById(R.id.btn_setting_time_submit);

	}

	private void addHour() {
		hour++;
		if (hour > 6) {
			hour = 0;
		}
		freshHint();
	}

	private void decHour() {
		hour--;
		if (hour < 0) {
			hour = 6;
		}
		freshHint();
	}

	private void addMin() {
		min += 30;
		if (min > 30) {
			min = 0;
			addHour();
		}
		freshHint();
	}

	private void decMin() {
		min -= 30;
		if (min < 0) {
			min = 30;
			decHour();
		}
		freshHint();
	}

	private void freshHint() {
		hint_hour.setText(hour + "");
		hint_min.setText(min + "");
	}

}
