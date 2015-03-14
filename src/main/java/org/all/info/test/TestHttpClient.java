package org.all.info.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Мартюк on 03.03.2015.
 */
public class TestHttpClient {

    private static Logger LOG = LogManager.getLogger(TestHttpClient.class);

    private static JSONParser parser = new JSONParser();

    public static void main(String[] args) throws IOException {

        String URL = "http://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/v1/?language=en_us&key=A4876DA2088DCB48058C61EC6200F143&format=json";
        getPageContent(URL);

    }

    public static JSONObject getPageContent(String url) {
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(
                    url);
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            JSONObject jsonObject = (JSONObject) parser.parse(br);

//            StringBuilder stringBuilder = new StringBuilder();
//            String output;
//            LOG.info("Output from Server .... \n");
//            while ((output = br.readLine()) != null) {
//                stringBuilder.append(br.readLine());
//            }
//            LOG.info(stringBuilder);

            httpClient.getConnectionManager().shutdown();

//            return stringBuilder.toString();
            return jsonObject;
        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
