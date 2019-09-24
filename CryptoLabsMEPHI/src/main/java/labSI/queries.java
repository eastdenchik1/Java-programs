package labSI;

import Manager.Manager;
import org.json.JSONWriter;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

public class queries {

    final Manager manager = new Manager();

    @Test
    public void bothMethods() {
        System.out.println("*************************************************************");
        System.out.println("GET Запрос на фиксированном ключе с фиксированным счетчиком: ");
        constant();
        System.out.println("*************************************************************");
        System.out.println("GET Запрос на фиксированном ключе со случайным счетчиком: ");
        random();
        System.out.println("*************************************************************");
    }

    @Test
    public void constant() {
        try {
            Properties props = manager.init_properties("labSI.properties");
            String urlGet = "http://" + props.getProperty("IP") + "/api/StreamIntegrity/" + props.getProperty("NAME_ID") + "/" + props.getProperty("CHALLENGE_ID") + "/noentropy";
            String response = GET(urlGet, props);
            System.out.println("[RESPONSE]: " + response);
            byte[] TMP_BYTES = Base64.getDecoder().decode(response);
            byte[] IV = Arrays.copyOfRange(TMP_BYTES, 0, Integer.parseInt(props.getProperty("IV_LEN")));
            byte[] TMP_BYTES_2 = Arrays.copyOfRange(TMP_BYTES, Integer.parseInt(props.getProperty("IV_LEN")), TMP_BYTES.length);
            byte[] TMP_BYTES_3 = xor(props.getProperty("PLAIN_TEXT").getBytes("UTF-8"), TMP_BYTES_2);
            byte[] TMP_BYTES_4 = xor(TMP_BYTES_3, props.getProperty("TOKEN").getBytes("utf-8"));
            byte[] TMP_BYTES_5 = Concat(IV, TMP_BYTES_4);
            int LEN_SENDING_BYTES = Integer.parseInt(props.getProperty("IV_LEN")) + props.getProperty("TOKEN").getBytes("utf-8").length;
            byte[] SEND_BYTES = Arrays.copyOfRange(TMP_BYTES_5, 0, LEN_SENDING_BYTES);
            String SEND_STRING = Base64.getEncoder().encodeToString(SEND_BYTES);
            String urlPost = "http://" + props.getProperty("IP") + "/api/StreamIntegrity/" + props.getProperty("NAME_ID") + "/" + props.getProperty("CHALLENGE_ID");
            String response2 = POST(urlPost,  SEND_STRING, props);
            System.out.println(response2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void random() {
        try {
            Properties props = manager.init_properties("labSI.properties");
            String urlGet = "http://" + props.getProperty("IP") + "/api/StreamIntegrity/" + props.getProperty("NAME_ID") + "/" + props.getProperty("CHALLENGE_ID");
            String response = GET(urlGet, props);
            System.out.println("[RESPONSE]: " + response);
            byte[] TMP_BYTES = Base64.getDecoder().decode(response);
            byte[] IV = Arrays.copyOfRange(TMP_BYTES, 0, Integer.parseInt(props.getProperty("IV_LEN")));
            byte[] TMP_BYTES_2 = Arrays.copyOfRange(TMP_BYTES, Integer.parseInt(props.getProperty("IV_LEN")), TMP_BYTES.length);
            byte[] TMP_BYTES_3 = xor(props.getProperty("PLAIN_TEXT").getBytes("UTF-8"), TMP_BYTES_2);
            byte[] TMP_BYTES_4 = xor(TMP_BYTES_3, props.getProperty("TOKEN").getBytes("utf-8"));
            byte[] TMP_BYTES_5 = Concat(IV, TMP_BYTES_4);
            int LEN_SENDING_BYTES = Integer.parseInt(props.getProperty("IV_LEN")) + props.getProperty("TOKEN").getBytes("utf-8").length;
            byte[] SEND_BYTES = Arrays.copyOfRange(TMP_BYTES_5, 0, LEN_SENDING_BYTES);
            String SEND_STRING = Base64.getEncoder().encodeToString(SEND_BYTES);
            String urlPost = "http://" + props.getProperty("IP") + "/api/StreamIntegrity/" + props.getProperty("NAME_ID") + "/" + props.getProperty("CHALLENGE_ID");
            String response2 = POST(urlPost,  SEND_STRING, props);
            System.out.println(response2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] xor(byte[] a, byte[] b) {
        int aLen = a.length;
        int bLen = b.length;
        int min = 0;
        int size = aLen > bLen ? aLen : bLen;
        byte[] c = new byte[size];

        if (size == aLen) {
            min = bLen;
            System.arraycopy(a, min, c, min, size - min);
        } else {
            min = aLen;
            System.arraycopy(b, min, c, min, size - min);
        }
        for (int i = 0; i < min; i++) {
            c[i] = (byte) (a[i] ^ b[i]);
        }
        return c;
    }

    private static String POST(String urlString, String data, Properties props) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", props.getProperty("USER_AGENT"));
        connection.setRequestProperty("Accept-Language", props.getProperty("LANGUAGE"));
        connection.setRequestProperty("Content-Type", props.getProperty("CONTENT_TYPE"));
        connection.setRequestProperty("Charset", props.getProperty("CHARSET"));

        String jsonData = JSONWriter.valueToString(data);

        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(jsonData);
        wr.flush();
        wr.close();

        int responseCode = connection.getResponseCode();
        System.out.println("SEND POST REQUEST: " + url);
        System.out.println("POST DATA: " + jsonData);
        System.out.println("RESPONSE CODE: " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String output;
        StringBuilder response = new StringBuilder();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        return response.toString();

    }

    private static String GET(String urlString, Properties props) throws Exception  {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", props.getProperty("USER_AGENT"));
        connection.setRequestProperty("Accept-Language", props.getProperty("LANGUAGE"));
        connection.setRequestProperty("Content-Type", props.getProperty("CONTENT_TYPE"));
        connection.setRequestProperty("Charset", props.getProperty("CHARSET"));

        int responseCode = connection.getResponseCode();
        System.out.println("SEND GET REQUEST: " + url);
        System.out.println("RESPONSE CODE: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        StringBuilder response = new StringBuilder();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        return response.toString();
    }

    private  <T> byte[] Concat(byte[] a, byte[] b){
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        byte[] c = (byte[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

}
