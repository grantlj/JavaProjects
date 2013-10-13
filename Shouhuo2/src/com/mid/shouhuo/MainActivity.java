package com.mid.shouhuo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mid.shouhuo.db.HarvestDbAdapter;
import com.mid.shouhuo.db.PlanDbAdapter;
import com.mid.shouhuo.ui.HarvestListAdapter;
import com.mid.shouhuo.ui.HarvestPagerAdapter;
import com.mid.shouhuo.ui.PlanItemListener;
import com.mid.shouhuo.ui.PlanListAdapter;
import com.mid.shouhuo.util.DialogUtil;
import com.mid.shouhuo.util.SDUtil;
import com.mid.shouhuo.util.SPUtil;

public class MainActivity extends Activity {

	private final String TAG = "[MainActivity]";

	// View×Ö¶Î
	private ImageButton portrait;

	private ViewPager pager = null;
	private ArrayList<View> views = new ArrayList<View>();

	private ImageView harvest_list_title;
	private ImageView plan_list_title;

	private TextView text_motto;

	private Button btn_plan, btn_harvest, daily_add_btn, once_add_btn;

	private View planListView, harvestListView;

	private ListView list_harvest, list_plan_daily, list_plan_once;

	private HarvestDbAdapter harvestAdapter;
	private PlanDbAdapter planAdapter;

	private SPUtil spUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// Ëø¶¨ÊúÆÁ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		harvestAdapter = new HarvestDbAdapter(this);
		planAdapter = new PlanDbAdapter(this);
		spUtil = new SPUtil(this);

		// ³õÊ¼»¯ÆÁÄ»×é¼þ
		findView();
		iniView();

		registerForContextMenu(list_harvest);
		registerForContextMenu(list_plan_daily);
		registerForContextMenu(list_plan_once);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// new Data(this).execute();
		prepareData();
	}

	@Override
	protected void onRestart() {
		Log.v(TAG, "-- onRestart -- ");
		new LoadImageAsync().execute(new String[] {});
		super.onRestart();
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	public void prepareData() {

		Log.v(TAG, "--prepareData()--");

		if (list_harvest != null) {
			list_harvest.setAdapter(new HarvestListAdapter(this));
		}

		if (list_plan_daily != null) {
			list_plan_daily.setAdapter(new PlanListAdapter(this, true));
			setListViewHeightBasedOnChildren(list_plan_daily);
		}

		if (list_plan_once != null) {
			list_plan_once.setAdapter(new PlanListAdapter(this, false));
			setListViewHeightBasedOnChildren(list_plan_once);
		}

		if (btn_plan != null) {

			int planCount = planAdapter.open().getCount();
			planAdapter.close();
			btn_plan.setText(planCount + "");

		}

		if (btn_harvest != null) {

			int harvestCount = harvestAdapter.open().getTodayCount();
			harvestAdapter.close();
			btn_harvest.setText(harvestCount + "");
		}

		text_motto.setText(spUtil.getMotto());

	}

	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() + 1));
		listView.setLayoutParams(params);
	}

	private void findView() {

		portrait = (ImageButton) findViewById(R.id.btn_header_portrait);
		harvest_list_title = (ImageView) findViewById(R.id.title_harvest);
		plan_list_title = (ImageView) findViewById(R.id.title_plan);
		pager = (ViewPager) findViewById(R.id.main_content_list);

		text_motto = (TextView) findViewById(R.id.main_header_motto);

		btn_plan = (Button) findViewById(R.id.btn_plan);
		btn_harvest = (Button) findViewById(R.id.btn_harvest);

		harvestListView = this.getLayoutInflater().inflate(
				R.layout.list_harvest, null);
		planListView = this.getLayoutInflater().inflate(R.layout.list_plan,
				null);

		list_harvest = (ListView) harvestListView
				.findViewById(R.id.harvest_list);

		list_plan_daily = (ListView) planListView.findViewById(R.id.daily_list);
		list_plan_once = (ListView) planListView.findViewById(R.id.once_list);
		daily_add_btn = (Button) planListView.findViewById(R.id.btn_daily_add);
		once_add_btn = (Button) planListView.findViewById(R.id.btn_once_add);
	}

	private void iniView() {

		/*
		 * try { Field mField = ViewPager.class.getDeclaredField("mScroller");
		 * mField.setAccessible(true); FixedSpeedScroller mScroller = new
		 * FixedSpeedScroller( pager.getContext(), new
		 * AccelerateInterpolator()); mField.set(pager, mScroller); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */

		new LoadImageAsync().execute(new String[] {});

		btn_plan.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pager.setCurrentItem(0);
			}

		});

		btn_harvest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pager.setCurrentItem(1);
			}

		});

		portrait.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SettingActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MainActivity.this.startActivity(intent);
			}

		});

		/*
		 * list_plan_daily.setAdapter(new PlanListAdapter(this, true));
		 * list_plan_once.setAdapter(new PlanListAdapter(this, false));
		 * 
		 * list_harvest.setAdapter(new HarvestListAdapter(this));
		 */

		list_plan_daily.setOnItemClickListener(new PlanItemListener(
				MainActivity.this));
		list_plan_once.setOnItemClickListener(new PlanItemListener(
				MainActivity.this));

		list_plan_once.setOnItemLongClickListener(new PlanItemListener(
				MainActivity.this));
		list_plan_daily.setOnItemLongClickListener(new PlanItemListener(
				MainActivity.this));

		daily_add_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new DialogUtil(MainActivity.this).showPlanAddDialog(true);
			}
		});

		once_add_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new DialogUtil(MainActivity.this).showPlanAddDialog(false);
			}
		});

		views.add(planListView);
		views.add(harvestListView);
		pager.setAdapter(new HarvestPagerAdapter(this, views));
		pager.setCurrentItem(1);

		pager.setOnPageChangeListener(new PagerListener());
	}

	public void setPager(int which) {
		pager.setCurrentItem(which);
	}

	private class PagerListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {

		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {
			if (position == 1) {
				harvest_list_title.setVisibility(View.VISIBLE);
				plan_list_title.setVisibility(View.GONE);

				btn_plan.setVisibility(View.VISIBLE);
				btn_harvest.setVisibility(View.GONE);
			} else {
				harvest_list_title.setVisibility(View.GONE);
				plan_list_title.setVisibility(View.VISIBLE);

				btn_plan.setVisibility(View.GONE);
				btn_harvest.setVisibility(View.VISIBLE);
			}
		}

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
				portrait.setImageBitmap(result);
			}
			super.onPostExecute(result);
		}

	}
}
