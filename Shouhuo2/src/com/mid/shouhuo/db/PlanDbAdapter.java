package com.mid.shouhuo.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mid.shouhuo.obj.Plan;

public class PlanDbAdapter {

	private String TAG = "HarvestDbAdapter";

	public class Constant {
		public static final String DB_NAME = "plan_db";
		public static final String TABLE_NAME = "plan";

		public static final String _ID = "_id";
		public static final String CREATED = "created"; // 格式xxxxxxxx-xxxx年月日-时分（24小时制）
		public static final String TITLE = "title";
		public static final String REMARK = "remak";
		public static final String IS_DAILY = "is_daily";
		public static final String TODAY_FINISH = "today_finish";
		public static final String BEGIN_DATE = "begin_date"; // 格式xxxxxxxx-xxxx年月日-时分（24小时制）
		public static final String HAS_ALARM = "has_alarm";
		public static final String ALARM_TIME = "alarm_time";
		public static final String DAYS = "days";
	}

	public static final int VERSION = 1;

	public static final String DATEBASE_CREATE = "create table "
			+ Constant.TABLE_NAME + " ( " + Constant._ID
			+ " integer primary key autoincrement, " + Constant.CREATED
			+ " text not null, " + Constant.TITLE + " text not null, "
			+ Constant.REMARK + " text, " + Constant.IS_DAILY
			+ " integer not null, " + Constant.DAYS + " integer, "
			+ Constant.TODAY_FINISH + " integer not null, "
			+ Constant.BEGIN_DATE + " text, " + Constant.HAS_ALARM
			+ " integer not null, " + Constant.ALARM_TIME + " integer );";

	private final Context context;
	private HarvestDbHelper helper;
	private SQLiteDatabase db;

	private class HarvestDbHelper extends SQLiteOpenHelper {

		public HarvestDbHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		public HarvestDbHelper(Context context, String name) {
			super(context, name, null, VERSION);
		}

		public HarvestDbHelper(Context context) {
			super(context, Constant.DB_NAME, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATEBASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS" + Constant.TABLE_NAME);
			onCreate(db);
		}

	}

	public PlanDbAdapter(Context context) {
		this.context = context;
		this.helper = new HarvestDbHelper(this.context);
	}

	public PlanDbAdapter open() {
		db = helper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public long insert(Plan p) {
		long id = -1;
		ContentValues values = new ContentValues();
		values.put(Constant.REMARK, p.getRemark());
		values.put(Constant.CREATED, p.getCreated());
		values.put(Constant.TITLE, p.getTitle());
		int daily = p.isDaily() ? Plan.Daily.DAILY : Plan.Daily.ONCE;
		values.put(Constant.IS_DAILY, daily);
		values.put(Constant.DAYS, p.getDays());
		/*
		 * int today_finish = p.isTodayFinish() ? Plan.TodayFinish.FINISH :
		 * Plan.TodayFinish.UNFINISH;
		 */
		values.put(Constant.TODAY_FINISH, Plan.TodayFinish.UNFINISH);
		values.put(Constant.BEGIN_DATE, p.getBeginDate());
		values.put(Constant.HAS_ALARM, p.isHasAlarm() ? 1 : 0);
		values.put(Constant.ALARM_TIME, p.getAlarmTime());
		Log.v(TAG, "insert: " + p.toString());
		id = db.insert(Constant.TABLE_NAME, null, values);
		return id;
	}

	public long update(Plan p) {
		long row = p.get_id();
		ContentValues values = new ContentValues();
		values.put(Constant.REMARK, p.getRemark());
		values.put(Constant.CREATED, p.getCreated());
		values.put(Constant.TITLE, p.getTitle());
		int daily = p.isDaily() ? Plan.Daily.DAILY : Plan.Daily.ONCE;
		values.put(Constant.IS_DAILY, daily);
		values.put(Constant.DAYS, p.getDays());
		/*
		 * int today_finish = p.isTodayFinish() ? Plan.TodayFinish.FINISH :
		 * Plan.TodayFinish.UNFINISH;
		 */
		values.put(Constant.TODAY_FINISH, Plan.TodayFinish.UNFINISH);
		values.put(Constant.BEGIN_DATE, p.getBeginDate());
		values.put(Constant.HAS_ALARM, p.isHasAlarm() ? 1 : 0);
		values.put(Constant.ALARM_TIME, p.getAlarmTime());
		Log.v(TAG, "insert: " + p.toString());
		row = db.update(Constant.TABLE_NAME, values, Constant._ID + " = ?",
				new String[] { row + "" });
		return row;
	}

	public boolean delete(long id) {
		return db.delete(Constant.TABLE_NAME, Constant._ID + " = ?",
				new String[] { id + "" }) > 0;
	}

	public boolean deleteAll() {
		return db.delete(Constant.TABLE_NAME, null, null) > 0;
	}

	public Plan query(long id) throws SQLException {
		Cursor cursor;
		Plan p = new Plan();
		cursor = db.query(true, Constant.TABLE_NAME, null, Constant._ID
				+ " = ?", new String[] { id + "" }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			p.set_id(id);
			p.setTitle(cursor.getString(cursor.getColumnIndex(Constant.TITLE)));
			p.setRemark(cursor.getString(cursor.getColumnIndex(Constant.REMARK)));
			p.setDaily(cursor.getInt(cursor.getColumnIndex(Constant.IS_DAILY)) == Plan.Daily.DAILY);
			p.setDays(cursor.getInt(cursor.getColumnIndex(Constant.DAYS)));
			p.setTodayFinish(cursor.getInt(cursor
					.getColumnIndex(Constant.TODAY_FINISH)) == Plan.TodayFinish.FINISH);
			p.setCreated(cursor.getString(cursor
					.getColumnIndex(Constant.CREATED)));
			p.setBeginDate(cursor.getString(cursor
					.getColumnIndex(Constant.BEGIN_DATE)));
			p.setHasAlarm(cursor.getInt(cursor
					.getColumnIndex(Constant.HAS_ALARM)) > 0);
			p.setAlarmTime(cursor.getLong(cursor
					.getColumnIndex(Constant.ALARM_TIME)));
		}
		return p;
	}

	public List<Plan> query(boolean isDaily) throws SQLException {
		Cursor cursor;
		List<Plan> list = new ArrayList<Plan>();
		int daily = isDaily ? Plan.Daily.DAILY : Plan.Daily.ONCE;
		cursor = db.query(true, Constant.TABLE_NAME, null, Constant.IS_DAILY
				+ " = ?", new String[] { daily + "" }, null, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				Plan p = new Plan();
				p.set_id(cursor.getLong(cursor.getColumnIndex(Constant._ID)));
				p.setTitle(cursor.getString(cursor
						.getColumnIndex(Constant.TITLE)));
				p.setRemark(cursor.getString(cursor
						.getColumnIndex(Constant.REMARK)));
				p.setDaily(cursor.getInt(cursor
						.getColumnIndex(Constant.IS_DAILY)) == Plan.Daily.DAILY);
				p.setDays(cursor.getInt(cursor.getColumnIndex(Constant.DAYS)));
				p.setTodayFinish(cursor.getInt(cursor
						.getColumnIndex(Constant.TODAY_FINISH)) == Plan.TodayFinish.FINISH);
				p.setCreated(cursor.getString(cursor
						.getColumnIndex(Constant.CREATED)));
				p.setBeginDate(cursor.getString(cursor
						.getColumnIndex(Constant.BEGIN_DATE)));
				p.setHasAlarm(cursor.getInt(cursor
						.getColumnIndex(Constant.HAS_ALARM)) > 0);
				p.setAlarmTime(cursor.getLong(cursor
						.getColumnIndex(Constant.ALARM_TIME)));
				list.add(p);
			}
		}
		return list;
	}

	public int getCount() {
		Cursor cursor;
		cursor = db.query(true, Constant.TABLE_NAME,
				new String[] { Constant._ID }, null, null, null, null, null,
				null);
		if (cursor != null) {
			return cursor.getCount();
		}
		return 0;
	}
}
