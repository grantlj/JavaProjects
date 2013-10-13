package com.mid.shouhuo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class SDUtil {

	public static final String BASE_URL = "/shouhuo/";
	public static final String PORTRAIT_FILENAME = "portrait.jpg";

	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static Bitmap getPortrait() {
		File portraitFile = new File(Environment.getExternalStorageDirectory(),
				SDUtil.BASE_URL + SDUtil.PORTRAIT_FILENAME);
		Bitmap bitmap = BitmapFactory.decodeFile(portraitFile.getPath());
		System.out.println(portraitFile.getPath());
		return bitmap;
	}

	public static void savePortrait(Bitmap bitmap) {
		File dir = new File(Environment.getExternalStorageDirectory(),
				SDUtil.BASE_URL);
		if (!dir.exists()) {
			dir.mkdir();
		}

		File portraitFile = new File(dir, SDUtil.PORTRAIT_FILENAME);
		try {
			if (!portraitFile.exists()) {
				portraitFile.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(portraitFile);
			bitmap.compress(CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
