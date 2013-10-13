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

import com.mid.shouhuo.obj.Harvest;
import com.mid.shouhuo.obj.SingleDay;
import com.mid.shouhuo.util.DateUtil;

public class HarvestDbAdapter {

	private String TAG = "HarvestDbAdapter";

	public class Constant {
		public static final String DB_NAME = "harvest_db";
		public static final String TABLE_NAME = "harvest";
		public static final String SINGLE_DAY_TABLE = "single_day";

		public static final String _ID = "_id";
		public static final String CREATED = "created"; // 格式xxxxxxxx年月日（24小时制）
		public static final String TITLE = "title";
		public static final String CONTENT = "content";
		public static final String IS_DAILY = "is_daily";
		public static final String DAYS = "days";

		public static final String DATE = "date";
	}

	public static final int VERSION = 1;

	public static final String DATEBASE_CREATE = "create table "
			+ Constant.TABLE_NAME + " ( " + Constant._ID
			+ " integer primary key autoincrement, " + Constant.CREATED
			+ " text not null, " + Constant.TITLE + " text not null, "
			+ Constant.CONTENT + " text, " + Constant.IS_DAILY
			+ " integer not null, " + Constant.DAYS + " integer );";

	public static final String SINGLE_DAY_CREATE = "create table "
			+ Constant.SINGLE_DAY_TABLE + " ( " + Constant._ID
			+ " integer primary key autoincrement, " + Constant.DATE
			+ " text );";

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
			db.execSQL(SINGLE_DAY_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS" + Constant.TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS" + Constant.SINGLE_DAY_TABLE);
			onCreate(db);
		}

	}

	public HarvestDbAdapter(Context context) {
		this.context = context;
		this.helper = new HarvestDbHelper(this.context);
	}

	public HarvestDbAdapter open() {
		db = helper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public long insert(Harvest h) {
		long id = -1;
		ContentValues values = new ContentValues();
		if (h.isDaily()) {
			values.put(Constant.DAYS,
					new DateUtil(context).getDays(h.getBeginDate()));
		}
		values.put(Constant.CONTENT, h.getContent());
		values.put(Constant.CREATED, h.getCreated());
		values.put(Constant.TITLE, h.getTitle());
		values.put(Constant.IS_DAILY, h.isDaily() ? 1 : 0);
		Log.v(TAG, "insert: " + h.toString());

		// singleday处理
		if (!containsDay(h.getCreated())) {
			ContentValues sdbvalues = new ContentValues();
			sdbvalues.put(Constant.DATE, h.getCreated());
			db.insert(Constant.SINGLE_DAY_TABLE, null, sdbvalues);
			// Log.v(TAG, "添加SingleDay");
		}

		id = db.insert(Constant.TABLE_NAME, null, values);
		return id;
	}

	public boolean update(Harvest h) {
		long row = h.get_id();
		ContentValues values = new ContentValues();
		if (h.isDaily()) {
			values.put(Constant.DAYS,
					new DateUtil(context).getDays(h.getBeginDate()));
		}
		values.put(Constant.CONTENT, h.getContent());
		values.put(Constant.CREATED, h.getCreated());
		values.put(Constant.TITLE, h.getTitle());
		values.put(Constant.IS_DAILY, h.isDaily() ? 1 : 0);
		return db.update(Constant.TABLE_NAME, values, Constant._ID + " = ?",
				new String[] { row + "" }) > 0;
	}

	public boolean delete(long id) {

		Harvest h = query(id);

		boolean flag = false;

		int row = db.delete(Constant.TABLE_NAME, Constant._ID + " = ?",
				new String[] { id + "" });

		flag = row > 0;

		if (flag) {

			Log.v(TAG, "h.getCreated: " + h.getCreated());
			if (!containsDate(h.getCreated())) {
				flag = db.delete(Constant.SINGLE_DAY_TABLE, Constant.DATE
						+ " = ?", new String[] { h.getCreated() }) > 0;
				Log.v(TAG, "flag: " + flag);
			}
		}

		return flag;

	}

	public boolean deleteAll() {
		return db.delete(Constant.TABLE_NAME, null, null) > 0;
	}

	public Harvest query(long id) throws SQLException {
		Cursor cursor;
		Harvest h = new Harvest();
		cursor = db.query(true, Constant.TABLE_NAME, null, Constant._ID
				+ " = ?", new String[] { id + "" }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			h.set_id(id);
			h.setTitle(cursor.getString(cursor.getColumnIndex(Constant.TITLE)));
			h.setContent(cursor.getString(cursor
					.getColumnIndex(Constant.CONTENT)));
			h.setDaily(cursor.getInt(cursor.getColumnIndex(Constant.IS_DAILY)));
			h.setCreated(cursor.getString(cursor
					.getColumnIndex(Constant.CREATED)));
			h.setDays(cursor.getInt(cursor.getColumnIndex(Constant.DAYS)));
		}
		return h;
	}

	public List<Harvest> query(String date) throws SQLException {
		Cursor cursor;
		List<Harvest> list = new ArrayList<Harvest>();
		cursor = db.query(true, Constant.TABLE_NAME, null, Constant.CREATED
				+ " = ?", new String[] { date }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToPosition(cursor.getCount());
			while (cursor.moveToPrevious()) {
				Harvest h = new Harvest();
				h.set_id(cursor.getLong(cursor.getColumnIndex(Constant._ID)));
				h.setTitle(cursor.getString(cursor
						.getColumnIndex(Constant.TITLE)));
				h.setContent(cursor.getString(cursor
						.getColumnIndex(Constant.CONTENT)));
				h.setDaily(cursor.getInt(cursor
						.getColumnIndex(Constant.IS_DAILY)));
				h.setCreated(cursor.getString(cursor
						.getColumnIndex(Constant.CREATED)));
				h.setDays(cursor.getInt(cursor.getColumnIndex(Constant.DAYS)));
				list.add(h);
			}
		}
		return list;
	}

	public boolean containsDay(String date) {
		// Log.v(TAG, "containsDay( " + date + " )");
		Cursor cursor = null;
		cursor = db.query(true, Constant.SINGLE_DAY_TABLE, null, Constant.DATE
				+ " = ?", new String[] { date }, null, null, null, null);

		// Log.v(TAG, "return: " + (cursor.getCount() > 0));
		return cursor.getCount() > 0;
	}

	public boolean containsDate(String date) {
		Cursor cursor = null;

		cursor = db.query(true, Constant.TABLE_NAME, null, Constant.CREATED
				+ " = ?", new String[] { date }, null, null, null, null);

		return cursor.getCount() > 0;
	}

	public boolean contains(Harvest h) {

		Cursor cursor;
		cursor = db.query(true, Constant.TABLE_NAME, null, Constant.CREATED
				+ " = ? and " + Constant.TITLE + " = ?",
				new String[] { h.getCreated(), h.getTitle() }, null, null,
				null, null);

		Log.v(TAG, "count: " + cursor.getCount());

		return cursor.getCount() > 0;

	}

	public List<SingleDay> getDays() {
		List<SingleDay> list = new ArrayList<SingleDay>();
		Cursor cursor = null;
		cursor = db.query(true, Constant.SINGLE_DAY_TABLE, null, null, null,
				null, null, Constant.DATE, null);
		if (cursor != null) {
			cursor.moveToPosition(cursor.getCount()); // 获得倒序的时间，由新到旧
			// cursor.moveToLast();
			while (cursor.moveToPrevious()) {
				SingleDay sd = new SingleDay();
				sd.setDate(cursor.getString(cursor
						.getColumnIndex(Constant.DATE)));
				list.add(sd);
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

	public int getTodayCount() {
		Cursor cursor;
		cursor = db.query(true, Constant.TABLE_NAME,
				new String[] { Constant._ID }, Constant.CREATED + " = ?",
				new String[] { new DateUtil(context).getToday() }, null, null,
				null, null);
		if (cursor != null) {
			return cursor.getCount();
		}
		return 0;
	}
}
