package com.mid.shouhuo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mid.shouhuo.util.SPUtil;

public class SettingMottoActivity extends Activity {

	private static final String TAG = "[SettingMottoActivity]";

	private EditText edit_motto;

	private Button btn_submit;

	private SPUtil spUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting_motto);
		// 锁定竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		spUtil = new SPUtil(this);

		TextView title_text = (TextView) findViewById(R.id.setting_title_text);
		Button btn_back = (Button) findViewById(R.id.setting_btn_back);

		title_text.setText("格    言");
		btn_back.setText("设置");
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		findView();

		btn_submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String mottoStr = edit_motto.getText().toString();
				Log.v(TAG, mottoStr);

				if ("".equals(mottoStr)) {
					Toast.makeText(SettingMottoActivity.this, "格言不能为空!",
							Toast.LENGTH_SHORT).show();
					return;
				} else {
					if (spUtil.setMotto(mottoStr)) {
						Toast.makeText(SettingMottoActivity.this, "格言设置成功",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(SettingMottoActivity.this, "格言设置失败",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

	}

	private void findView() {
		edit_motto = (EditText) findViewById(R.id.text_setting_motto);
		btn_submit = (Button) findViewById(R.id.btn_setting_motto_submit);
	}

}
