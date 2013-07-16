package com.luckofwise.zipsearch.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;
import com.luckofwise.zipsearch.R;
import com.luckofwise.zipsearch.data.Location;

@EViewGroup(R.layout.row_location)
public class LocationRowView extends LinearLayout {

	@ViewById
	TextView textViewPostal;

	@ViewById
	TextView textViewAddress;

	public LocationRowView(Context context) {
		super(context);
	}

	public void bind(Location location) {
		textViewPostal.setText(location.getPostal());
		textViewAddress.setText(location.getAddress());
	}
}
