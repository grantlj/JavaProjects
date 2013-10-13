package com.mid.shouhuo;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.mid.shouhuo.ui.SettingListAdapter;
import com.mid.shouhuo.util.SDUtil;

public class SettingActivity extends Activity {

	private static final String TAG = "[SettingActivity]";

	private ListView setting_setting, setting_about;

	private View portrait;

	private Button btn_back;

	private ImageView portraitImg;

	private String[] items = new String[] { "选择本地图片", "拍照" };

	// private static final String SDUtil.BASEURL + SDUtil.PORTRAIT_FILENAME =
	// "portraitImg.jpg";

	/* 请求码 */
	private static final int IMAGE_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;

	private View.OnClickListener portraitListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			showDialog();
		}
	};

	@Override
	protected void onRestart() {
		Log.v(TAG, "-- onRestart -- ");
		new LoadImageAsync().execute(new String[] {});
		super.onRestart();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		// 锁定竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		findView();

		iniView();
	}

	public void findView() {
		portrait = findViewById(R.id.setting_portrait_wrapper);
		portrait.setClickable(true);
		portrait.setOnClickListener(portraitListener);

		portraitImg = (ImageView) portrait
				.findViewById(R.id.setting_portrait_holder);

		btn_back = (Button) findViewById(R.id.setting_btn_back);

		setting_setting = (ListView) findViewById(R.id.setting_list_setting);
		setting_about = (ListView) findViewById(R.id.setting_list_about);
	}

	public void iniView() {

		setting_setting.setAdapter(new SettingListAdapter<String>(this,
				R.layout.setting_list_item, R.id.setting_item_text,
				getResources().getStringArray(R.array.setting_setting)));

		setting_about.setAdapter(new SettingListAdapter<String>(this,
				R.layout.setting_list_item, R.id.setting_item_text,
				getResources().getStringArray(R.array.setting_about)));

		new LoadImageAsync().execute(new String[] {});

		setting_setting.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent();

				switch (position) {
				case 0:
					intent.setClass(SettingActivity.this,
							SettingTimeActivity.class);
					SettingActivity.this.startActivity(intent);
					break;
				case 1:
					intent.setClass(SettingActivity.this,
							SettingMottoActivity.class);
					SettingActivity.this.startActivity(intent);
					break;
				case 2:
					intent.setClass(SettingActivity.this,
							SettingRemindActivity.class);
					SettingActivity.this.startActivity(intent);
					break;

				}
			}

		});

		setting_about.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent();

				switch (position) {
				case 0:
					intent.setClass(SettingActivity.this,
							SettingStatusActivity.class);
					SettingActivity.this.startActivity(intent);
					break;
				case 1:
					Toast.makeText(SettingActivity.this, "当前为最新版本！",
							Toast.LENGTH_SHORT).show();
					break;
				case 2:
					intent.setClass(SettingActivity.this,
							SettingFeedbackActivity.class);
					SettingActivity.this.startActivity(intent);
					break;
				case 3:
					intent.setClass(SettingActivity.this,
							SettingAboutActivity.class);
					SettingActivity.this.startActivity(intent);
					break;
				}

			}

		});

		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	class LoadImageAsync extends AsyncTask<String, String, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = SDUtil.getPortrait();
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {

			if (result != null) {
				portraitImg.setImageBitmap(result);
			}
			super.onPostExecute(result);
		}

	}

	/**
	 * 显示选择对话框
	 */
	private void showDialog() {

		new AlertDialog.Builder(this)
				.setTitle("设置头像")
				.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							Intent intentFromGallery = new Intent();
							intentFromGallery.setType("image/*"); // 设置文件类型
							intentFromGallery
									.setAction(Intent.ACTION_GET_CONTENT);
							startActivityForResult(intentFromGallery,
									IMAGE_REQUEST_CODE);
							break;
						case 1:

							Intent intentFromCapture = new Intent(
									MediaStore.ACTION_IMAGE_CAPTURE);
							// 判断存储卡是否可以用，可用进行存储
							if (SDUtil.hasSdcard()) {

								intentFromCapture.putExtra(
										MediaStore.EXTRA_OUTPUT,
										Uri.fromFile(new File(
												Environment
														.getExternalStorageDirectory(),
												SDUtil.BASE_URL
														+ SDUtil.PORTRAIT_FILENAME)));
							}

							startActivityForResult(intentFromCapture,
									CAMERA_REQUEST_CODE);
							break;
						}
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 结果码不等于取消时候
		if (resultCode != RESULT_CANCELED) {

			switch (requestCode) {
			case IMAGE_REQUEST_CODE:
				startPhotoZoom(data.getData());
				break;
			case CAMERA_REQUEST_CODE:
				if (SDUtil.hasSdcard()) {
					File tempFile = new File(
							Environment.getExternalStorageDirectory(),
							SDUtil.BASE_URL + SDUtil.PORTRAIT_FILENAME);
					startPhotoZoom(Uri.fromFile(tempFile));
				} else {
					Toast.makeText(SettingActivity.this, "未找到存储卡，无法存储照片！",
							Toast.LENGTH_LONG).show();
				}

				break;
			case RESULT_REQUEST_CODE:
				if (data != null) {
					getImageToView(data);
				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 100);
		intent.putExtra("outputY", 100);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 2);
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param picdata
	 */
	private void getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			@SuppressWarnings("deprecation")
			Drawable drawable = new BitmapDrawable(photo);
			portraitImg.setImageDrawable(drawable);
			SDUtil.savePortrait(photo);
		}
	}

}
