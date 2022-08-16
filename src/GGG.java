import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GGG {
    public static void main(String[] args) throws IOException {

        String msg = "https://httpbin.org/post";
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            url = new URL(msg);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            httpURLConnection.setRequestProperty("User-Agent", "Google/5.0");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setConnectTimeout(200);
            httpURLConnection.setReadTimeout(200);
            httpURLConnection.connect();
            String message = "Message from Dell Latitude to the server!!!";


            try {
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(message.getBytes());
            } catch (Exception e) {
                System.out.println(e);
            }

            if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());

                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("result.txt"));
                while (bufferedInputStream.available() != 0) {
                    bufferedOutputStream.write(bufferedInputStream.read());
                }
                bufferedOutputStream.flush();
            }

        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            outputStream.close();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        }


    }
}