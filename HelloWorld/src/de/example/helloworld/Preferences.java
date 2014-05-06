package de.example.helloworld;

import android.app.Activity;
import android.os.Bundle;

public class Preferences extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new PreferencesFragment())
				.commit();
	}
}
