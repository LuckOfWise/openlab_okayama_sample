package com.luckofwise.zipsearch.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.luckofwise.zipsearch.data.Location;
import com.luckofwise.zipsearch.view.LocationRowView;
import com.luckofwise.zipsearch.view.LocationRowView_;

@EBean
public class LocationAdapter extends BaseAdapter {

	List<Location> mLocationList = new ArrayList<Location>();

	@RootContext
	Context context;

	public void setData(List<Location> LocationList) {
		mLocationList = LocationList;
	}

	@Override
	public int getCount() {
		return mLocationList.size();
	}

	@Override
	public Location getItem(int position) {
		return mLocationList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LocationRowView LocationRowView;
		if (convertView == null) {
			LocationRowView = LocationRowView_.build(context);
		} else {
			LocationRowView = (LocationRowView) convertView;
		}
		LocationRowView.bind(getItem(position));
		return LocationRowView;
	}

}