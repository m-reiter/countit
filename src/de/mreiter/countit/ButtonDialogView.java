package de.mreiter.countit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

@SuppressLint("ViewConstructor")
public class ButtonDialogView extends LinearLayout implements OnClickListener {

	CounterState counter;
	EditText edLabel;
	EditText edValue;
	Integer value;

	public ButtonDialogView(Context context, CounterState counter) {
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);

		this.counter = counter;
		value = counter.getValue();

		Resources res = getContext().getResources();
		
		edLabel = new EditText(getContext());
		edLabel.setText(counter.getName());
		edLabel.setSingleLine();
		edLabel.setHint(R.string.label);
		edLabel.setTextSize(22);
		this.addView(edLabel);

		edValue = new EditText(getContext());
		edValue.setText(String.valueOf(value));
		edValue.setSingleLine();
		edValue.setHint("0");
		edValue.setTextSize(30);
		edValue.setTypeface(null, Typeface.BOLD);
		edValue.setContentDescription(res.getString(R.string.value));
		edValue.setInputType(InputType.TYPE_CLASS_NUMBER);
		edValue.setGravity(Gravity.CENTER);
		this.addView(edValue);
		
		LinearLayout buttons = new LinearLayout(getContext());
		
		LayoutParams buttonLayoutParams = new LayoutParams(
				0, LayoutParams.MATCH_PARENT, 1);

//		Button minus = new Button(getContext());
//		minus.setText(Html.fromHtml("<big><b>�</b></big>"));
		ImageButton minus = new ImageButton(getContext());
		minus.setImageResource(R.drawable.ic_minus);
		minus.setContentDescription(res.getString(R.string.decrease));
		minus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				value--;
				edValue.setText(String.valueOf(value));
			}
		});
		ImageButton reset = new ImageButton(getContext());
		reset.setImageResource(R.drawable.ic_reset);
		reset.setContentDescription(res.getString(R.string.reset));
		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				value = 0;
				edValue.setText(String.valueOf(value));
			}
		});
//		Button plus = new Button(getContext());
//		plus.setText(Html.fromHtml("<big><b>+</b></big>"));
		ImageButton plus = new ImageButton(getContext());
		plus.setImageResource(R.drawable.ic_plus);
		plus.setContentDescription(res.getString(R.string.increase));
		plus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				value++;
				edValue.setText(String.valueOf(value));
			}
		});
		
		buttons.addView(minus,buttonLayoutParams);
		buttons.addView(reset,buttonLayoutParams);
		buttons.addView(plus,buttonLayoutParams);
		this.addView(buttons);

	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		counter.setName(edLabel.getText().toString());
		String valueString = edValue.getText().toString();
		if (valueString.equals("")) {
			counter.setValue(0);
		} else {
			counter.setValue(Integer.parseInt(valueString));
		}
	}

}
