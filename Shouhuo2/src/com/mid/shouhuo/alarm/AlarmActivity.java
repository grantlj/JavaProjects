package com.mid.shouhuo.alarm;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mid.shouhuo.R;
import com.mid.shouhuo.obj.Plan;
import com.mid.shouhuo.util.SPUtil;

public class AlarmActivity extends Activity {

	private TextView text_title;
	private TextView text_message;
	private Button btn_pos;
	private Button btn_neg;

	private SoundPool pool;
	private int ring;

	private float volume;
	private boolean vibrate;
	private SPUtil spUtil;
	private Vibrator vibrator;

	private Plan p;
	private MediaPlayer mMediaPlayer;

	private int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);

		Intent intent = getIntent();

		p = (Plan) intent.getSerializableExtra("plan");
		pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		ring = pool.load(this, R.raw.ring, 1);
		spUtil = new SPUtil(this);
		volume = (float) (spUtil.getVolume() / 10.0);
		count = 0;

		iniView();

		mMediaPlayer = MediaPlayer.create(this, R.raw.ring);
		try {
			mMediaPlayer.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mMediaPlayer.setLooping(true);
		mMediaPlayer.start();

		Log.v("volume", volume + "");
		vibrate = spUtil.getIsVibrate();

		if (vibrate) {
			vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			long[] pattern = { 500, 800, 500, 800 };
			vibrator.vibrate(pattern, -1);
		}

	}

	private void iniView() {

		text_title = (TextView) findViewById(R.id.dialog_title);
		text_message = (TextView) findViewById(R.id.dialog_message);
		btn_pos = (Button) findViewById(R.id.dialog_pos_btn);
		btn_neg = (Button) findViewById(R.id.dialog_neg_btn);

		text_title.setText(p.getTitle());
		String message = p.getRemark();
		if (!"".equals(message)) {
			text_message.setText("备注：" + message);
		} else {
			text_message.setText("这个计划没有备注");
		}

		btn_pos.setText("快速完成");
		btn_pos.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				p.finish(AlarmActivity.this);

				vibrator.cancel();
				mMediaPlayer.release();
				finish();
			}
		});

		btn_neg.setText("关  闭");
		btn_neg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				vibrator.cancel();
				mMediaPlayer.release();
				finish();
			}
		});

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);
		Point point = new Point();
		display.getSize(point);
		lp.width = (int) (point.x * 0.9);
		dialogWindow.setAttributes(lp);
	}
}
