package de.mreiter.countit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

@SuppressLint("ViewConstructor")
public class CounterButton extends Button implements OnClickListener,
		OnLongClickListener {

	CounterState counter;

	// create layout parameters for buttons
	LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
			0, LayoutParams.MATCH_PARENT, 1f);
	
	public CounterButton(Context context, CounterState counter) {
		super(context);
		this.counter = counter;
		this.setContentDescription(counter.getName());
		buttonLayoutParams.setMargins(1, 1, 1, 1);
		this.setLayoutParams(buttonLayoutParams);
		this.setBackgroundResource(R.drawable.mybuttons);
		this.setTextColor(Color.WHITE);
		this.setTextSize(34);
		this.setGravity(Gravity.CENTER);
		this.setLayoutParams(buttonLayoutParams);

		this.setOnClickListener(this);
		this.setOnLongClickListener(this);
	}

	public void draw(Canvas canvas) {

		String value = String.valueOf(counter.getValue());
		String label = " "+counter.getName()+" ";
		
		int lValue = value.length();
		int lLabel = label.length();
		
		SpannableString text = new SpannableString(value+"\n"+label);
		
		text.setSpan(new StyleSpan(Typeface.BOLD),0,lValue,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		text.setSpan(new RelativeSizeSpan(0.45f), lValue+1,lValue+lLabel+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		this.setText(text);
		
		super.draw(canvas);

	}

	public void onClick(View v) {
		counter.increment();
	}

	public boolean onLongClick(View v) {

		Resources res = getContext().getResources();
		String counterString = res.getString(R.string.counter);

		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		ButtonDialogView view = new ButtonDialogView(getContext(), counter);
		builder.setTitle(counterString+" "+counter.getPosition());
		builder.setPositiveButton(R.string.ok,view);
		builder.setNegativeButton(R.string.cancel, null);
		builder.setView(view);
		
		builder.create().show();

		return true;
	}

}