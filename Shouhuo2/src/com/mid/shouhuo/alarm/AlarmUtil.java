package com.mid.shouhuo.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mid.shouhuo.obj.Plan;

public class AlarmUtil {

	private static final String TAG = "[AlarmUtil]";

	private Context context;
	private AlarmManager manager;

	public AlarmUtil(Context context) {
		this.context = context;
		this.manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
	}

	public void setAlarm(Plan p) {
		Intent intent = new Intent(context, AlarmActivity.class);
		intent.putExtra("plan", p);
		PendingIntent pi = PendingIntent.getActivity(context, 1, intent, 0);
		manager.set(AlarmManager.RTC_WAKEUP, p.getAlarmTime(), pi);
		Log.v(TAG, "Alarm has been set successfully!");
	}

	public void cancelAlarm(Plan p) {
		Intent intent = new Intent(context, AlarmActivity.class);
		intent.putExtra("plan", p);
		PendingIntent pi = PendingIntent.getActivity(context, 1, intent, 0);
		manager.cancel(pi);
		Log.v(TAG, "Alarm has been canceled successfully!");
	}
}
