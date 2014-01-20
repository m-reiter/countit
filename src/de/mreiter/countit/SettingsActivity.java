package de.mreiter.countit;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.Html;

public class SettingsActivity extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		update(prefs);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@SuppressWarnings("deprecation")
	private void update(SharedPreferences prefs) {

		int nCounters = Integer
				.parseInt(prefs.getString("pref_nCounters", "0"));
		Resources res = getApplicationContext().getResources();
		String counter = res.getString(R.string.counter);
		
		findPreference("pref_nCounters").setSummary(String.valueOf(nCounters));

		for (int i = 0; i < res.getInteger(R.integer.maxCounters); i++) {

			Preference pref = findPreference("label" + i);

			if (i > nCounters - 1) {
				pref.setEnabled(false);
			} else {
				pref.setEnabled(true);
			}
			String title = prefs.getString("label" + i, "");

			if (title.equals("")) {
				pref.setTitle(Html.fromHtml("<i>&lt;" + counter + " " + (i + 1)
						+ "&gt;</i>"));
			} else {
				pref.setTitle(title);
			}
		}
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		update(sharedPreferences);
	}
}
