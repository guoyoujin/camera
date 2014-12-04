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
 * AlertDialog �Ի���
 * 
 * @author zsl
 * @version 2014-8-8 16:41:16
 */
@SuppressLint("NewApi")
public class AlertDialotgDao {
	/**
	 * ����һ����ͨ��Alertdialog
	 * 
	 * @param view
	 *            Ҫ��������ͼ
	 */
	public AlertDialog addView(View view, Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context,
				AlertDialog.THEME_HOLO_LIGHT);
		builder.setView(view);
		AlertDialog dialog = builder.create();
		return dialog;
	};

	/**
	 * ����һ����������button��AlertDialog
	 * 
	 * @param view
	 *            view
	 * @param context
	 *            ������
	 * @param okName
	 *            �ұ߰�ť������
	 * @param cencalName
	 *            ��ߵİ�ť������
	 * @param okListener
	 *            �ұ߰�ť�ĵ���¼�
	 * @param cencalListener
	 *            ��߰�ť�ĵ���¼�
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
	 * ѡ�����
	 * 
	 * @param context
	 *            ������
	 * @param title
	 *            ����
	 * @param okName
	 *            ��ť����
	 * @param submenuitem
	 *            ѡ��List<String>
	 * @param itemClickListener
	 *            item����¼�
	 * @param okClickListener
	 *            ��ť����¼�
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
