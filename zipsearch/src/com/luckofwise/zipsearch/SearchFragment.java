package com.luckofwise.zipsearch;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.OrmLiteDao;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.j256.ormlite.dao.Dao;
import com.luckofwise.zipsearch.adapter.LocationAdapter;
import com.luckofwise.zipsearch.data.DatabaseHelper;
import com.luckofwise.zipsearch.data.Location;
import com.luckofwise.zipsearch.data.ResponseContainer;
import com.luckofwise.zipsearch.rest.RestClient;

@EFragment(R.layout.fragment_search)
public class SearchFragment extends Fragment {

	@ViewById
	EditText editTextKeyword;
	@ViewById
	ListView listViewLocations;

	@RestService
	RestClient mClient;

	@Bean
	LocationAdapter mLocationAdapter;

	@OrmLiteDao(helper = DatabaseHelper.class, model = Location.class)
	Dao<Location, Long> mLocationDao;
	
	@AfterViews
	void init() {
		mLocationAdapter.setData(new ArrayList<Location>());
		listViewLocations.setAdapter(mLocationAdapter);
		editTextKeyword.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    search();
                    return true;
                }
				return false;
			}
		});
	}

	@Background
	void search() {
		if (editTextKeyword.length() > 0) {
			ResponseContainer res = mClient.getResponseContainer(editTextKeyword.getText().toString());
			mLocationAdapter.setData(res.getResponse().getLocations());
			showLocations();
		}
	}
	
	@UiThread
	void showLocations() {
		mLocationAdapter.notifyDataSetInvalidated();
	}
	
	@ItemClick
	void listViewLocations(Location location){
		try {
			mLocationDao.create(location);
			Toast.makeText(getActivity(), "お気に入りへ登録しました！", Toast.LENGTH_SHORT).show();
		} catch (SQLException e) {
			Toast.makeText(getActivity(), "お気に入りの登録に失敗しましたorz", Toast.LENGTH_SHORT).show();
		}
	}
}
