package com.luckofwise.zipsearch;

import java.sql.SQLException;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.WindowManager.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.OrmLiteDao;
import com.googlecode.androidannotations.annotations.ViewById;
import com.j256.ormlite.dao.Dao;
import com.luckofwise.zipsearch.adapter.LocationAdapter;
import com.luckofwise.zipsearch.data.DatabaseHelper;
import com.luckofwise.zipsearch.data.Location;

@EFragment(R.layout.fragment_favorite)
public class FavoriteFragment extends Fragment {

	@ViewById
	ListView listViewLocations;

	@Bean
	LocationAdapter mLocationAdapter;

	@OrmLiteDao(helper = DatabaseHelper.class, model = Location.class)
	Dao<Location, Long> mLocationDao;

	@AfterViews
	void init() {
		try {
			mLocationAdapter.setData(mLocationDao.queryForAll());
			listViewLocations.setAdapter(mLocationAdapter);
		} catch (SQLException e) {
		}
	}

	@ItemClick
	void listViewLocations(final Location location) {
		String[] str_items = { "郵便番号をコピー", "住所をコピー", "地図を見る", "お気に入りから削除" };
		new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_launcher).setTitle("操作").setItems(str_items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					copy(location.getPostal());
					break;
				case 1:
					copy(location.getAddress());
					break;
				case 2:
					startMapIntent(location);
					break;
				case 3:
					delete(location);
					break;
				}
			}
		}).show();
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi") 
	void copy(String text) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			getActivity();
			android.text.ClipboardManager cm = (android.text.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
			cm.setText(text);
		} else {
			ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
			Item item = new ClipData.Item(text);
			String[] mimeType = new String[1];
			mimeType[0] = ClipDescription.MIMETYPE_TEXT_PLAIN;
			ClipData cd = new ClipData(new ClipDescription("text_data", mimeType), item);
			cm.setPrimaryClip(cd);
		}
		Toast.makeText(getActivity(), "コピーしました。", Toast.LENGTH_SHORT).show();
	}
	
	void startMapIntent(Location location) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(location.getGeoUri());
		startActivity(intent);
	}
	
	void delete(Location location) {
		try {
			mLocationDao.delete(location);
			mLocationAdapter.setData(mLocationDao.queryForAll());
			mLocationAdapter.notifyDataSetChanged();
			Toast.makeText(getActivity(), "お気に入りから削除しました！", Toast.LENGTH_SHORT).show();
		} catch (SQLException e) {
			Toast.makeText(getActivity(), "お気に入りからの削除に失敗しましたorz", Toast.LENGTH_SHORT).show();
		}
	}
}
