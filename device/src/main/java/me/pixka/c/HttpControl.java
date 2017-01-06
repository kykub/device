package me.pixka.c;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;

@Controller
public class HttpControl {

	public String get(String s) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet(s);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);

		try {
			System.out.println(response1.getStatusLine());
			HttpEntity entity1 = response1.getEntity();

			String re = EntityUtils.toString(entity1);
			EntityUtils.consume(entity1);
			return re;
		} finally {
			response1.close();
		}

		
	}

	public void json(String h, String t, String mac, String host) throws IOException {
		/*
		 * JSONObject json = new JSONObject(); json.put("t", t); json.put("h",
		 * h); json.put("mac", mac);
		 * 
		 * 
		 * CloseableHttpClient httpclient = HttpClients.createDefault(); try {
		 * HttpPost request = new HttpPost(host); StringEntity params = new
		 * StringEntity(json.toString()); request.addHeader("content-type",
		 * "application/json"); request.addHeader("Accept","application/json");
		 * request.setEntity(params); CloseableHttpResponse re =
		 * httpclient.execute(request); } catch (Exception e) {
		 * e.printStackTrace(); } finally { httpclient.close(); }
		 */
	}
}
