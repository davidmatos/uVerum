package pt.inescid.microVerum.common.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class Request {

	@Id
	public String id;
	public long ts;
	public String url;
	public String request;
	public String from;
	public String to;
	public String globalId;
	public String method;
	public Map<String, String> query;

	public Request(String url, String request, String from, String to, String globalId, String method,
			Map<String, String> query) {
		super();
		// this.id = id;
		this.ts = Calendar.getInstance().getTimeInMillis();
		this.url = url;
		this.request = request;
		this.from = from;
		this.to = to;
		this.globalId = globalId;
		this.method = method;
		this.query = query;
	}

	public Request(String globalId, String from, String to, String requestString) {
		this.ts = Calendar.getInstance().getTimeInMillis();
		this.request = requestString;
		this.globalId = globalId;
		this.from = from;
		this.to = to;
		String[] requestLines = requestString.split("\n");

		for (int i = 0; i < requestLines.length; i++) {

			String line = requestLines[i];

			if (line.startsWith("GET ") || line.startsWith("POST ") || line.startsWith("PUT ")) {
				String[] parts = line.split(" ");
				this.method = parts[0];
				this.url = parts[1];
			}

			if (line.startsWith("GET ")) {
				// get the parameters form the url

				String[] parameters = line.substring(line.indexOf("?") + 1).split("&");
				if (line.indexOf(" ") > 0) {
					parameters = line.substring(line.indexOf("?") + 1, line.lastIndexOf(" ")).split("&");
				}
				this.query = new HashMap<String, String>();
				for (int j = 0; j < parameters.length; j++) {
					String[] parts = parameters[j].split("=");
					if(parts.length==2) {						
						this.query.put(parts[0], parts[1]);
					}
				}
			}

		}

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

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	@Override
	public String toString() {

		return "{id: " + id + ",\n" + "ts: " + ts + ",\n" + "url: " + url + ",\n" + "request: " + request + ",\n"
				+ "from: " + from + ",\n" + "to: " + to + ",\n" + "globalId: " + globalId + ",\n" + "method: " + method
				+ ",\n" + "query: " + query.toString() + "}";

	}

}
