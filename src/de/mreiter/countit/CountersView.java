package de.mreiter.countit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.widget.LinearLayout;

@SuppressLint("ViewConstructor")
public class CountersView extends LinearLayout {

	public CountersView(Context context, Counters counters) {
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
		this.setBackgroundColor(Color.BLACK);

		int i,j;
		int columns, rows;
		int longerSide = 0;
		int shorterSide = 0;
		
		Resources res = getResources();
		
		int[] nCounterValues = res.getIntArray(R.array.nCounterValues);
		int[] longerSides = res.getIntArray(R.array.longerSides);
		int[] shorterSides = res.getIntArray(R.array.shorterSides);

		int nCounters = counters.length();
		
		for (i = 0; i < nCounterValues.length; i++) {
			if (nCounterValues[i] == nCounters) {
				longerSide = longerSides[i];
				shorterSide = shorterSides[i];
				break;
			}
		}

		Configuration config = res.getConfiguration();
		if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
			// portrait layout
			columns = shorterSide;
			rows = longerSide;
		} else {
			// landscape layout
			columns = longerSide;
			rows = shorterSide;
		}

		CounterButton[] myButtons = new CounterButton[nCounters];
		LinearLayout[] myRows = new LinearLayout[rows];

		// create layout parameters for rows
		LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 0, 1f);

		for (i = 0; i < rows; i++) {

			myRows[i] = new LinearLayout(this.getContext());
			myRows[i].setLayoutParams(rowLayoutParams);
			this.addView(myRows[i]);

			for (j = 0; j < columns; j++) {
				int n = i * columns + j;
				myButtons[n] = new CounterButton(this.getContext(),counters.counter(n));
				myRows[i].addView(myButtons[n]);
			}
		}

	}

}
