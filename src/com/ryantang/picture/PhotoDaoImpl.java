package com.ryantang.picture;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

/**
 * 调用系统的相机和相册的封装的方法
 * 
 * @author zsl
 * @date 2014-10-28 上午9:43:28
 * @version v1.5
 */
public class PhotoDaoImpl {
	public static PhotoDaoImpl photoDaoImpl;
	private static Context context;
	// imageUri
	private Uri imageFileUri;
	private int TYPE_FILE_IMAGE = 1;
	public static int SYSTEM_CAMERA_REQUESTCODE = 1;
	public static int PICTURE = 2;
	public String imagePathName = "tongxinyilian_photo";

	private List<File> files = new ArrayList<File>();

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	// 单例模式
	public static PhotoDaoImpl getInstance(Context context1) {
		context = context1;
		if (photoDaoImpl != null) {
			return photoDaoImpl;
		} else {
			return new PhotoDaoImpl();
		}
	}

	public Uri getImageFileUri() {
		return imageFileUri;
	}

	public void setImageFileUri(Uri imageFileUri) {
		this.imageFileUri = imageFileUri;
	}

	// 调用相机
	public Intent openImageCaptuer() {
		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		imageFileUri = getOutFileUri(TYPE_FILE_IMAGE);// 得到一个File Uri
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		return intent;
	}

	public Intent openPictures() {
		Intent picture = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		return picture;
	}

	// -----------------------生成Uri---------------------------------------
	// 得到输出文件的URI
	private Uri getOutFileUri(int fileType) {
		return Uri.fromFile(getOutFile(fileType));
	}

	// 生成输出文件
	private File getOutFile(int fileType) {

		String storageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_REMOVED.equals(storageState)) {
			Toast.makeText(context, "SD卡不存在", Toast.LENGTH_SHORT).show();
			return null;
		}

		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				imagePathName);
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				return null;
			}
		}
		File file = new File(getFilePath(mediaStorageDir, fileType));
		files.clear();
		files.add(file);
		return file;
	}

	// 生成输出文件路径
	private String getFilePath(File mediaStorageDir, int fileType) {
		String filePath = mediaStorageDir.getPath() + File.separator;
		if (fileType == TYPE_FILE_IMAGE) {
			filePath += ("tongxin.jpg");
		}
		// else if (fileType == TYPE_FILE_VEDIO) {
		// filePath += ("VIDEO_" + timeStamp + ".mp4");
		// }
		else {
			return null;
		}
		return filePath;
	}

	public List<File> getfiles(String uri) {
		try {
			
			File file = new File(uri);
			files.clear();
			files.add(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return files;
	}

	// --------------------------Uri转换为bitmap----------------------------

	public Bitmap getBitmap() {

		return getBitmapFromUri(imageFileUri);
	}

	public Bitmap getBitmapFromUri(Uri uri) {
		try {
			// 读取uri所在的图片
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(
					context.getContentResolver(), uri);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ------------------------bitmap转换为byte[]---------------------
	public byte[] forBitmapGetByte(Bitmap bitmap) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();// 初始化一个流对象
		bitmap.compress(CompressFormat.PNG, 50, output);// 把bitmap100%高质量压缩 到
														// output对象里
		// bitmap.recycle();//自由选择是否进行回收

		byte[] result = output.toByteArray();// 转换成功了
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 获得一个16进制的字符串
	public String getJingZhi(Bitmap bitmap) {
		String photoStr = byte2hex(forBitmapGetByte(bitmap));
		return photoStr;
	}

	/**
	 * 二进制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				sb.append("0" + stmp);
			} else {
				sb.append(stmp);
			}

		}
		return sb.toString();
	}

	/**
	 * bitmap转换为file
	 * 
	 * @return
	 */
	public File getFileByBitmap(Bitmap bitmap) {
		File file = new File(Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				imagePathName+"1122.jpg");// 将要保存图片的路径
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(file));
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
			bos.flush();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}
