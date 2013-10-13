package com.mid.shouhuo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.mid.shouhuo.db.HarvestDbAdapter;
import com.mid.shouhuo.db.PlanDbAdapter;
import com.mid.shouhuo.util.SPUtil;

public class WelActivity extends Activity {

	private static final String TAG = "[WelActivity]";

	private boolean hasInitialed = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Ëø¶¨ÊúÆÁ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_wel);

		if (!new SPUtil(this).isHasInitialed()) {
			hasInitialed = false;
			Log.v(TAG, "Î´³õÊ¼»¯");
			new SPUtil(this).initial(true);
			new HarvestDbAdapter(this).open().close();
			new PlanDbAdapter(this).open().close();
		}

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (hasInitialed) {
					goMain();
				} else {
					goStart();
				}
			}
		}, 2000);
	}

	private void goMain() {
		Intent intent = new Intent(WelActivity.this, MainActivity.class);
		WelActivity.this.startActivity(intent);
		WelActivity.this.finish();
	}

	private void goStart() {
		Intent intent = new Intent(WelActivity.this, InicialActivity.class);
		WelActivity.this.startActivity(intent);
		WelActivity.this.finish();
	}
}
