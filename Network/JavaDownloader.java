package Network;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class JavaDownloader {

	public static void downloadWithHttp() throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet method = new HttpGet(
				"http://images-cdn.moviepilot.com/images/c_fill,h_1600,w_2560/t_mp_quality/zjjlmhlckurabshxpktl/could-darth-vader-really-cameo-in-star-wars-rogue-one-792094.jpg");
		CloseableHttpResponse response = client.execute(method);
		InputStream reader = response.getEntity().getContent();

		try (BufferedInputStream br = new BufferedInputStream(reader);
				FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Homes\\Downloads\\JAVA2Pic.jpg"));) {
			int i;
			while ((i = br.read()) != -1) {
				out.write(i);
				out.flush();
			}
		}
	}

	public static void download() throws URISyntaxException, IOException {
		URL url = new URL(
				"http://images-cdn.moviepilot.com/images/c_fill,h_1600,w_2560/t_mp_quality/zjjlmhlckurabshxpktl/could-darth-vader-really-cameo-in-star-wars-rogue-one-792094.jpg");
		InputStream stream = url.openStream();
		try (BufferedInputStream input = new BufferedInputStream(stream);
				FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Homes\\Downloads\\JAVA3Pic.jpg"));) {
			int i;
			while ((i = input.read()) != -1) {
				out.write(i);
				out.flush();
			}
		}

	}

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		download();
	}

}
