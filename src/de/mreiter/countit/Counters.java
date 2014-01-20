package de.mreiter.countit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class Counters {

	private int maxCounters;
	private int nCounters;
	private CounterState[] counters;

	public Counters(Context context, SharedPreferences prefs) {

		int i;

		Resources res = context.getResources();
		maxCounters = res.getInteger(R.integer.maxCounters);
		String defCounters = res.getString(R.string.defaultCounters);

		nCounters = Integer.parseInt(prefs.getString("pref_nCounters", defCounters));

		counters = new CounterState[maxCounters];
		for (i = 0; i < maxCounters; i++) {
			counters[i] = new CounterState(prefs.getInt("" + i, 0),
					prefs.getString("label" + i, ""),i+1);
		}
	}

	public int length() {
		return nCounters;
	}
	
	public void setLength(String nCounters) {
		this.nCounters = Integer.parseInt(nCounters);
	}

	public CounterState counter(int i) {
		return counters[i];
	}

	public void zero() {
		for (int i = 0; i < maxCounters; i++) {
			counters[i].setValue(0);
		}
		
	}
	
	public void save(SharedPreferences prefs) {
		SharedPreferences.Editor ed = prefs.edit();

//		ed.putString("pref_nCounters", String.valueOf(nCounters));
		for (int i = 0; i < maxCounters; i++) {
			ed.putInt("" + i, counters[i].getValue());
			ed.putString("label" + i, counters[i].getName());
		}
		ed.commit();

	}

}