package pt.inescid.microVerum.common.domain;

import java.util.Calendar;

public class Ping {
	
	private String message;
	private long ts;
	
	
	
	public Ping(String name) {
		super();
		this.message = "Hello, " + name;
		this.ts = Calendar.getInstance().getTimeInMillis();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String name) {
		this.message = "Hello, " + name;
	}
	public long getTs() {
		return ts;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
	
	
	

}
