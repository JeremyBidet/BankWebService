import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RESTConverter {
	
	public static final String BASE_URL = "http://api.fixer.io/";
	public static final String ENDPOINT = "latest";

	static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static String[] getCurrencies() {
		
		HttpGet get = new HttpGet(BASE_URL + ENDPOINT);

		try {
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();

			JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
			JSONObject rates = exchangeRates.getJSONObject("rates");
			
			List<String> cur = new ArrayList<String>();
			Iterator<?> it = rates.keys();
			while(it.hasNext()) {
				cur.add((String) it.next());
			}
			
			response.close();
			String[] a = new String[cur.size()];
			return cur.toArray(a);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static double getRateExchange(String source, String target) {

		String params = "";
		if(source != null && target != null) {
			params = "&base="+source+"&symbols="+target;
		}

		HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?" + params);

		try {
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();

			JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
			String base = exchangeRates.getString("base");
			JSONObject rates = exchangeRates.getJSONObject("rates");
			
			double d = rates.getDouble(target);
			
			System.out.println("1 " + base + " = " + d + " " + target);
			
			response.close();
			
			return d;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
