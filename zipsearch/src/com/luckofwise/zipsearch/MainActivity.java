package com.luckofwise.zipsearch;

import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends SherlockFragmentActivity implements TabListener {

	private String[] locations;

	@AfterViews
	void afterViews() {
		locations = getResources().getStringArray(R.array.locations);
		configureActionBar();
	}

	private void configureActionBar() {
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (String location : locations) {
			Tab tab = getSupportActionBar().newTab();
			tab.setText(location);
			tab.setTabListener(this);
			getSupportActionBar().addTab(tab);
		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		if (tab.getText().equals("検索")) {
			ft.replace(android.R.id.content, new SearchFragment_());
		} else {
			ft.replace(android.R.id.content, new FavoriteFragment_());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater();
		return true;
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}
}
