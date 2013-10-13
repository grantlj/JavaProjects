package com.mid.shouhuo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.mid.shouhuo.db.HarvestDbAdapter;
import com.mid.shouhuo.util.SPUtil;

public class SettingStatusActivity extends Activity {

	private TextView text_days;
	private TextView text_harvest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting_status);
		// 锁定竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		TextView title_text = (TextView) findViewById(R.id.setting_title_text);
		Button btn_back = (Button) findViewById(R.id.setting_btn_back);
		text_days = (TextView) findViewById(R.id.text_status_days);
		text_harvest = (TextView) findViewById(R.id.text_status_harvest);

		title_text.setText("关    于");
		btn_back.setText("设置");
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		long iniTime = new SPUtil(this).getInitialTime();
		long now = System.currentTimeMillis();
		int daysNum = (int) (now / 1000 / 60 / 60 / 24 - iniTime / 1000 / 60
				/ 60 / 24) + 1;
		String daysStr = getResources().getString(
				R.string.setting_status_title_days);
		daysStr = String.format(daysStr, daysNum);
		text_days.setText(daysStr);

		HarvestDbAdapter harvestAdapter = new HarvestDbAdapter(this);
		int harvestNum = harvestAdapter.open().getCount();
		harvestAdapter.close();
		String harvestStr = getResources().getString(
				R.string.setting_status_title_harvest);
		harvestStr = String.format(harvestStr, harvestNum);
		text_harvest.setText(harvestStr);

	}
}
