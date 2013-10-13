package com.mid.shouhuo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.mid.shouhuo.util.SPUtil;

public class SettingRemindActivity extends Activity {

	private SeekBar volume_bar;
	private CheckBox box_vibrate;

	private SPUtil spUtil;

	private SoundPool pool;
	private int ring;

	private int volume;
	private Vibrator vibrator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting_remain);
		// 锁定竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		spUtil = new SPUtil(this);
		pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		ring = pool.load(this, R.raw.ring, 0);

		TextView title_text = (TextView) findViewById(R.id.setting_title_text);
		Button btn_back = (Button) findViewById(R.id.setting_btn_back);

		title_text.setText("提    醒");
		btn_back.setText("设置");
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		findView();

		volume = spUtil.getVolume();
		volume_bar.setProgress(volume);

		boolean isVibrate = spUtil.getIsVibrate();
		box_vibrate.setChecked(isVibrate);

		volume_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				volume = seekBar.getProgress();
				if (spUtil.setVolume(volume)) {
					pool.play(ring, (float) (volume / 10.0),
							(float) (volume / 10.0), 1, 0, 1);
					Toast.makeText(SettingRemindActivity.this, "音量设置成功",
							Toast.LENGTH_SHORT).show();
				} else {
					volume = spUtil.getVolume();
					seekBar.setProgress(volume);
					Toast.makeText(SettingRemindActivity.this, "音量设置失败",
							Toast.LENGTH_SHORT).show();
				}
			}

		});

		box_vibrate.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (spUtil.setVibrate(isChecked)) {
					Toast.makeText(SettingRemindActivity.this,
							"震动成功" + (isChecked ? "打开" : "关闭"),
							Toast.LENGTH_SHORT).show();
					if (isChecked) {
						vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
						// long[] pattern = { 0, 1000 };
						vibrator.vibrate(1000);
					}
				} else {
					buttonView.toggle();
					Toast.makeText(SettingRemindActivity.this, "震动设置失败",
							Toast.LENGTH_SHORT).show();
				}
			}

		});
	}

	private void findView() {
		volume_bar = (SeekBar) findViewById(R.id.volome_bar);
		box_vibrate = (CheckBox) findViewById(R.id.box_vibrate);
	}

}
