package de.example.helloworld;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class Second extends ListActivity {

	private ArrayList<String> listItems = new ArrayList<String>();
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		Intent i = getIntent();
		String city = i.getStringExtra("city");
		Toast.makeText(this, city, Toast.LENGTH_LONG).show();

		JsonRequest jr = new JsonRequest("http://api.duckduckgo.com/?q=" + city
				+ "&format=json&pretty=1", this, updateDataSuccess,
				updateDataError);
		jr.request();

		adapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.list_item, listItems);
		setListAdapter(adapter);
	}

	private Runnable updateDataSuccess = new Runnable() {
		public void run() {
			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			Boolean showUrls = preferences.getBoolean("showUrls", true);
			int numOfItems = Integer
					.parseInt(preferences.getString("num", "5"));

			DBHandler db = new DBHandler(getApplicationContext());
			ArrayList<Item> items = db.readItems();

			for (int i = 0; i < Math.min(items.size(), numOfItems); i++) {
				if (showUrls) {
					listItems.add(items.get(i).getUrl() + ":\n"
							+ items.get(i).getText());
				} else {
					listItems.add(items.get(i).getText());
				}
			}
			adapter.notifyDataSetChanged();
		}
	};

	private Runnable updateDataError = new Runnable() {
		public void run() {
			Log.d("test", "error");
		}
	};

}
