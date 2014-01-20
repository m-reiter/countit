package de.mreiter.countit;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity implements OnSharedPreferenceChangeListener {

	SharedPreferences prefs;
	Counters counters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);

		counters = new Counters(this, prefs);

		// create our main Layout
		generateView();

	}

	private void generateView() {
		CountersView mainLayout = new CountersView(this, counters);
		// create layout parameters for it
		LayoutParams mainLayoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		// set it as main view
		setContentView(mainLayout, mainLayoutParams);
	}
	
	protected void onPause() {
		super.onPause();

		counters.save(prefs);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_resetall:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.confirm_reset);
			builder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							counters.zero();

						}
					});
			builder.setNegativeButton(R.string.cancel, null);

			AlertDialog dialog = builder.create();
			dialog.show();

			return true;
		case R.id.action_settings:
			Intent i = new Intent(this, SettingsActivity.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (key.equals("pref_nCounters")) {
			counters.setLength(prefs.getString(key, ""));
			generateView();
		} else if ((key.length() > 4) && (key.substring(0,5).equals("label"))) {
			int i=Integer.parseInt(key.substring(5));
			counters.counter(i).setName(prefs.getString(key, ""));
		}
	}

}
