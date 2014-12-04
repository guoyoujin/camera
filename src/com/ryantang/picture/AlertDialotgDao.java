package com.ryantang.picture;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * AlertDialog 对话框
 * 
 * @author zsl
 * @version 2014-8-8 16:41:16
 */
@SuppressLint("NewApi")
public class AlertDialotgDao {
	/**
	 * 创建一个普通的Alertdialog
	 * 
	 * @param view
	 *            要构建的视图
	 */
	public AlertDialog addView(View view, Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context,
				AlertDialog.THEME_HOLO_LIGHT);
		builder.setView(view);
		AlertDialog dialog = builder.create();
		return dialog;
	};

	/**
	 * 创建一个带有两个button的AlertDialog
	 * 
	 * @param view
	 *            view
	 * @param context
	 *            上下文
	 * @param okName
	 *            右边按钮的文字
	 * @param cencalName
	 *            左边的按钮的文字
	 * @param okListener
	 *            右边按钮的点击事件
	 * @param cencalListener
	 *            左边按钮的点击事件
	 * @return
	 */
	@SuppressLint("NewApi")
	public AlertDialog addViewtTwoButton(View view, Context context,
			String okName, String cencalName,
			DialogInterface.OnClickListener okListener,
			DialogInterface.OnClickListener cencalListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context,
				AlertDialog.THEME_HOLO_LIGHT);
		builder.setView(view);
		builder.setNegativeButton(cencalName, cencalListener);
		builder.setNeutralButton(okName, okListener);
		AlertDialog dialog = builder.create();
		return dialog;
	}

	/**
	 * 选项弹出框
	 * 
	 * @param context
	 *            上下文
	 * @param title
	 *            标题
	 * @param okName
	 *            按钮名称
	 * @param submenuitem
	 *            选项List<String>
	 * @param itemClickListener
	 *            item点击事件
	 * @param okClickListener
	 *            按钮点击事件
	 * @return AlertDialog
	 */
	@SuppressLint("NewApi")
	public AlertDialog addViewtSelectItem(Context context, String title,
			String okName, List<String> submenuitem,
			OnItemClickListener itemClickListener,
			DialogInterface.OnClickListener okClickListener) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.alertdialogselect, null);
		TextView tv_title = (TextView) view
				.findViewById(R.id.alertdialogselect_tv_title);
		tv_title.setText(title);
		ListView contentListView = (ListView) view
				.findViewById(R.id.alertdialogselect_listView);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
				R.layout.alertdialogselect_item, R.id.submenu_item__tv_name,
				submenuitem);
		contentListView.setAdapter(arrayAdapter);
		contentListView.setOnItemClickListener(itemClickListener);
		AlertDialog.Builder builder = new AlertDialog.Builder(context,
				AlertDialog.THEME_HOLO_LIGHT);
		builder.setView(view);
		builder.setNeutralButton(okName, okClickListener);
		AlertDialog dialog = builder.create();
		return dialog;
	}

	
	
	

}
