package com.mid.shouhuo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.mid.shouhuo.ui.InicialPagerAdapter;

public class InicialActivity extends Activity implements OnPageChangeListener {

	private ImageView dots[];

	private View pics[];

	private int[] dotsId = { R.id.dot_0, R.id.dot_1, R.id.dot_2, R.id.dot_3,
			R.id.dot_4 };

	private int[] picsId = { R.drawable.ini_pic_0, R.drawable.ini_pic_1,
			R.drawable.ini_pic_2, R.drawable.ini_pic_3, R.drawable.ini_pic_4, };

	private ViewPager pager;

	private List<View> views;

	private int currPic = 0;

	private ImageButton btn_enter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_inicial);
		// Ëø¶¨ÊúÆÁ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		View finalView = LayoutInflater.from(this).inflate(R.layout.ini_final,
				null);

		pager = (ViewPager) findViewById(R.id.inicial_pic_pager);
		btn_enter = (ImageButton) finalView.findViewById(R.id.btn_ini_enter);
		btn_enter.setVisibility(View.GONE);

		btn_enter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InicialActivity.this,
						MainActivity.class);
				InicialActivity.this.startActivity(intent);
				InicialActivity.this.finish();
			}

		});

		dots = new ImageView[5];
		pics = new ImageView[5];
		views = new ArrayList<View>();

		for (int i = 0; i < 5; i++) {
			dots[i] = (ImageView) findViewById(dotsId[i]);
			dots[i].setEnabled(false);
		}

		for (int i = 0; i < 4; i++) {
			ImageView imgView = new ImageView(this);
			imgView.setImageResource(picsId[i]);
			// imgView.setAdjustViewBounds(true);
			imgView.setScaleType(ScaleType.FIT_CENTER);
			pics[i] = imgView;
			views.add(imgView);
		}
		views.add(finalView);

		updateDots();
		pager.setAdapter(new InicialPagerAdapter(this, views));
		pager.setOnPageChangeListener(this);

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// btn_enter.setVisibility(View.GONE);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int whitch) {
		currPic = whitch;
		updateDots();
		if (currPic == 4) {
			btn_enter.setVisibility(View.VISIBLE);
			Animation animation = AnimationUtils.loadAnimation(
					InicialActivity.this, R.anim.ini_enter_anim);
			animation.setFillAfter(true);
			btn_enter.setAnimation(animation);
		}
	}

	private void updateDots() {
		for (int i = 0; i < 5; i++) {
			dots[i].setEnabled(i == currPic);
		}
	}

}
