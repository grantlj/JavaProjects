package com.mid.shouhuo.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {
	private SharedPreferences sp;
	private Context context;

	public SPUtil(Context context) {
		this.context = context;
		sp = this.context.getSharedPreferences("SP", Context.MODE_PRIVATE);
	}

	public String getEndTime() {
		return sp.getString("end_time", "0000");
	}

	public boolean setEndTime(String time) {
		return sp.edit().putString("end_time", time).commit();
	}

	public boolean isHasInitialed() {
		return sp.getBoolean("has_initialed", false);
	}

	public boolean initial(boolean initialed) {
		sp.edit().putBoolean("has_initialed", initialed).commit();
		if (initialed) {
			return sp.edit()
					.putLong("initial_time", System.currentTimeMillis())
					.commit();
		} else {
			return sp.edit().clear().commit();
		}
	}

	public long getInitialTime() {
		return sp.getLong("initial_time", -1);
	}

	public String getMotto() {
		return sp.getString("motto", "人生在勤，\n不索何获！");
	}

	public boolean setMotto(String motto) {
		return sp.edit().putString("motto", motto).commit();
	}

	public String getPortrait() {
		return sp.getString("portrait_url", null);
	}

	public boolean setPortrait(String url) {
		return sp.edit().putString("portrait_url", url).commit();
	}

	public int getVolume() {
		return sp.getInt("alarm_volume", 9);
	}

	public boolean setVolume(int v) {
		if (v < 0 || v > 10) {
			return false;
		} else {
			return sp.edit().putInt("alarm_volume", v).commit();
		}
	}

	public boolean getIsVibrate() {
		return sp.getBoolean("alarm_vibrate", true);
	}

	public boolean setVibrate(boolean v) {
		return sp.edit().putBoolean("alarm_vibrate", v).commit();
	}

}
