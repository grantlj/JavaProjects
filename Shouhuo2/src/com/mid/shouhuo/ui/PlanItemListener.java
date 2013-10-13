package com.mid.shouhuo.ui;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.mid.shouhuo.obj.Plan;
import com.mid.shouhuo.util.DialogUtil;

public class PlanItemListener implements OnItemClickListener,
		OnItemLongClickListener {

	// private final String TAG = "[PlanItemListener]";

	private Context context;

	public PlanItemListener(Context context) {
		this.context = context;
		System.out.println("Listener constructor!");
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Plan p = (Plan) parent.getItemAtPosition(position);
		new DialogUtil(context).showConfirmFinish(p);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {

		Plan p = (Plan) parent.getItemAtPosition(position);

		new DialogUtil(context).showContextMenu(p);

		return true;
	}

}
