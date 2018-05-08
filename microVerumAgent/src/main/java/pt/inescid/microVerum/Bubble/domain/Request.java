package pt.inescid.microVerum.Bubble.domain;

import java.util.Calendar;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class Request {

	@Id
	private String id;
	private long ts;
	private String url;
	private String request;
	private String from;
	private String to;
	private String globalId;
	private String method;
	private Map<String, String[]> query;
	
	
	
	
	public Request(String url, String request, String from, String to, String globalId, String method, Map<String, String[]> query) {
		super();
		//this.id = id;
		this.ts = Calendar.getInstance().getTimeInMillis();
		this.url = url;
		this.request = request;
		this.from = from;
		this.to= to;
		this.globalId = globalId;
		this.method = method;
		this.query = query;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getTs() {
		return ts;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getGlobalId() {
		return globalId;
	}
	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, String[]> getQuery() {
		return query;
	}
	public void setQuery(Map<String, String[]> query) {
		this.query = query;
	}
	
	
	
	
}