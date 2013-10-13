package com.mid.shouhuo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class HarvestItemListView extends ListView{

	public HarvestItemListView(Context context) {
		super(context);
	}
	
	public HarvestItemListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	

	public HarvestItemListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
