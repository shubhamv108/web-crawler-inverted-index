package utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpClient {

    public static String invoke(URL url) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            return parse(connection.getInputStream());
        }
        throw new RuntimeException("Unknown response while invoking url");
    }

    static String parse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String inputLine = null;
        while ((inputLine = reader.readLine()) != null)
            builder.append(inputLine);
        return builder.toString();
    }

}
