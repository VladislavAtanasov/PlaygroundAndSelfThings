package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class WebCrawler {

	private Map<String, Boolean> visited;
	private boolean URLfound = false;

	public WebCrawler() {
		visited = new HashMap<>();
	}

	private static List<String> getAllLinks(String content) {
		ArrayList<String> resultList = new ArrayList<>();
		String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			resultList.add(matcher.group(1));
		}
		return resultList;
	}

	public void crawl(URL url, String word) throws UnsupportedOperationException, IOException {
		InputStream reader = getInputStream(url);
		List<String> links = getLinksOrNull(url, word, reader);
		if (links == null) {
			System.out.println(url.toString());
			return;
		} else {
			for (String link : links) {
				if (link.startsWith(url.toString()) && visited.get(link) == null) {
					URL newUrl = new URL(link);
					crawl(newUrl, word);
				}
			}
		}
	}

	private List<String> getLinksOrNull(URL url, String word, InputStream reader) throws IOException {
		List<String> links = null;
		String result = findNeedle(word, reader);
		visited.put(url.toString(), true);
		if (!URLfound) {
			links = getAllLinks(result);
		}

		return links;

	}

	private String findNeedle(String word, InputStream reader) throws IOException {
		StringBuilder responseString = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(reader));) {
			String line;
			while ((line = br.readLine()) != null) {
				responseString.append(line);
				// System.out.println(line);
				if (line.contains(word)) {
					this.URLfound = true;
				}
			}
		}
		return responseString.toString();
	}

	private InputStream getInputStream(URL url) throws IOException, ClientProtocolException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet method = new HttpGet(url.toString());
		CloseableHttpResponse response = client.execute(method);
		InputStream reader = response.getEntity().getContent();
		return reader;
	}

	public static void main(String[] args) throws UnsupportedOperationException, IOException {
		long start = System.currentTimeMillis();
		WebCrawler web = new WebCrawler();
		// web.crawl(new URL("http://ebusiness.free.bg/"), "разплащания");
		web.crawl(new URL("http://gong.bg/"),
				"португалецът ще иска в тима Гарет Бейл, Лука Модрич и Рафаел Варан от Реал Мадрид. ");
		System.out.println((System.currentTimeMillis() - start) / 1000.0);
	}
}
