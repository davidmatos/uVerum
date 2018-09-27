package pt.inescid.microverum.microVerumAgentProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Logger;

import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import pt.inescid.microverum.microVerumAgentProxy.config.MicroVerumAgentConstants;
import pt.inescid.microverum.microVerumAgentProxy.log.LogWriter;

public class MicroVerumAgent {

	public static Logger logger = Logger.getLogger("microVerumAgent");
	
	public HttpProxyServer proxy;

	public int localPort;
	public String serviceHostName;
	public int servicePort;
	
	public String microVerumLogAddress;
	public int microVerumLogPort;
	
	public boolean logMode = MicroVerumAgentConstants.LOG_ASYNC;
	
	private static MicroVerumAgent instance = null;
	
	public static MicroVerumAgent getInstance() {
		return instance; 
	}

	public MicroVerumAgent(int localPort, String serviceHostName, int servicePort, String microVerumLogAddress, int microVerumLogPort) {
		this.serviceHostName = serviceHostName;
		this.servicePort = servicePort;
		this.localPort = localPort;
		this.microVerumLogAddress = microVerumLogAddress;
		this.microVerumLogPort = microVerumLogPort;
		
	}

	public void startProxy() {
		instance = this;
		logger.info("Starting microVerumAgent");
		
		this.proxy = DefaultHttpProxyServer.bootstrap().withPort(localPort)
				.withFiltersSource(new HttpFiltersSourceAdapter() {
					public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
						return new HttpFiltersAdapter(originalRequest) {
							@Override
							public HttpResponse clientToProxyRequest(HttpObject httpObject) {
								// TODO: implement your filtering here
								return null;
							}

							@Override
							public HttpObject serverToProxyResponse(HttpObject httpObject) {
								// TODO: implement your filtering here
								return httpObject;
							}
						};
					}
				}).start();

	}

	public void stopProxy() {
		this.proxy.stop();
	}

	class RectifyHTTPFilter extends HttpFiltersSourceAdapter {

		@Override
		public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
			return new HttpFiltersAdapter(originalRequest) {
				@Override
				public HttpResponse clientToProxyRequest(HttpObject httpObject) {

					if (originalRequest.getUri().contains("favicon")) {
						return null;
					}

					// AsyncLogWriter.getInstance().addLogHttpRequest(originalRequest.toString(),
					// originalRequest.getUri());
					originalRequest.setUri(serviceHostName + originalRequest.getUri());

					LogWriter.getInstance().logRequest(originalRequest.toString());

					URL obj = null;
					try {
						obj = new URL(originalRequest.getUri());
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}

					HttpURLConnection con = null;
					try {
						con = (HttpURLConnection) obj.openConnection();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					// optional default is GET
					try {
						con.setRequestMethod(originalRequest.getMethod().name());
					} catch (ProtocolException e1) {
						e1.printStackTrace();
					}

					// add request header
					// con.setRequestProperty("User-Agent", originalRequest.g);
					int responseCode = 0;
					try {
						responseCode = con.getResponseCode();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					BufferedReader in = null;
					InputStreamReader isr = null;
					try {
						isr = new InputStreamReader(con.getInputStream());

						in = new BufferedReader(isr);

					} catch (IOException e1) {
						e1.printStackTrace();
					}
					String inputLine;
					StringBuffer response = new StringBuffer();

					try {
						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						in.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					ByteBuf buffer = null;
					try {
						buffer = Unpooled.wrappedBuffer(response.toString()
								.replaceAll(serviceHostName, "http://localhost:" + localPort).getBytes("UTF-8"));
						// buffer = Unpooled.wrappedBuffer( response.toString().getBytes("UTF-8"));

					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					HttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
							buffer);
					HttpHeaders.setContentLength(httpResponse, buffer.readableBytes());

					HttpHeaders.setHeader(httpResponse, HttpHeaders.Names.CONTENT_TYPE,
							con.getHeaderField(HttpHeaders.Names.CONTENT_TYPE));

					

					return httpResponse;
				}

				@Override
				public HttpObject serverToProxyResponse(HttpObject httpObject) {
					return httpObject;
				}
			};
		}

	}
	
	
	
	public void setLogMode(boolean logMode) {
		this.logMode = logMode;
	}

}
