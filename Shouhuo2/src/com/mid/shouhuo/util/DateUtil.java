package com.mid.shouhuo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.util.Log;

public class DateUtil {

	private static String TAG = "DateUtil";

	private static String endTime;
	private static SPUtil sp;

	public DateUtil(Context context) {
		sp = new SPUtil(context);
		endTime = sp.getEndTime();
	}

	public String getToday() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHss", Locale.getDefault());
		String nowTime = sdf.format(c.getTime());

		Log.v(TAG, "nowTime: " + nowTime + "    endTime: " + endTime);

		if (Integer.parseInt(nowTime) < Integer.parseInt(endTime)) {
			// 未到结束时间
			c.add(Calendar.DAY_OF_MONTH, -1);
		}

		sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		String today = sdf.format(c.getTime());

		return today;
	}

	public String getYestoday() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",
				Locale.getDefault());
		c.add(Calendar.DAY_OF_MONTH, -1);
		String yestoday = sdf.format(c.getTime());

		return yestoday;
	}

	public String formatDate(String date) {

		StringBuffer sb = new StringBuffer();
		if (Integer.parseInt(date.substring(0, 4)) < Calendar.getInstance()
				.get(Calendar.YEAR)) {
			sb.append(date.substring(0, 4) + "年");
		}
		sb.append(date.substring(4, 6) + "月");
		sb.append(date.substring(6) + "日");

		return sb.toString();

	}

	public String getDate(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);

		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);

		return hour + ":" + min;
	}

	public int getDays(String startDay) {

		Calendar start = getDate(startDay);

		Calendar end = getDate(getToday());

		long s = start.getTimeInMillis() / 1000 / 60 / 60 / 24;
		long e = end.getTimeInMillis() / 1000 / 60 / 60 / 24;

		return (int) (e - s + 1);
	}

	public Calendar getDate(String dateStr) {
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(dateStr.substring(0, 4)),
				Integer.parseInt(dateStr.substring(4, 6)),
				Integer.parseInt(dateStr.substring(6, 8)));
		return c;
	}

	public static int getMaxDay(int year, int month) {
		int num = 0;
		if (month == 2) {
			if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
				num = 29;
			} else {
				num = 28;
			}
		} else {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month - 1);
			num = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		// System.out.println(year + "年" + month + "月有" + num + "天");
		return num;
	}
}
