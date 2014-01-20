package de.mreiter.countit;

public class CounterState {
	
	private int value;
	private int position;
	private String name;

	public CounterState() {
		value = 0;
		name = "";
	}
	
	public CounterState(int value, String name, int position) {
		this.value = value;
		this.name = name;
		this.position = position;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void increment() {
		value++;
	}

}
