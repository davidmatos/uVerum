package pt.inescid.microVerum.Bubble.domain;

public class Request {

	private String id;
	private long ts;
	private String url;
	private String request;
	private String from;
	private String to;
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
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	
	
}
