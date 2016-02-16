package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class CrawlerMultithread implements Callable {

	private static Map<String, Boolean> visited = new HashMap<>();
	private boolean URLfound = false;
	private static Set<String> result = new HashSet<>();
	private URL url;
	private String word;

	public CrawlerMultithread(URL url, String word) {
		this.url = url;
		this.word = word;
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

	@Override
	public Set<String> call() throws Exception {
		InputStream reader = getInputStream(url);
		List<String> links = getLinksOrNull(url, word, reader);
		if (links == null) {
			System.out.println(url.toString());
			result.add(url.toString());
			System.out.println(url.toString());
			return result;
		} else {
			for (String link : links) {
				if (link.startsWith(url.toString()) && visited.get(link) == null) {
					URL newUrl = new URL(link);
					FutureTask<Set<String>> futureTask = new FutureTask<>(new CrawlerMultithread(newUrl, word));
					new Thread(futureTask).start();
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		FutureTask<Set<String>> futureTask = new FutureTask<>(
				new CrawlerMultithread(new URL("http://www.fmi.uni-sofia.bg/education"),
						"Студентски и преподавателски мобилности по програмата Еразъм+"));
		FutureTask<Set<String>> futureTask1 = new FutureTask<>(
				new CrawlerMultithread(new URL("http://www.sportal.bg/"), "Намери в Sportal"));

		Thread tr = new Thread(futureTask1);
		tr.start();
		System.out.println(futureTask1.get(Long.MAX_VALUE, TimeUnit.NANOSECONDS));
		System.out.println((System.currentTimeMillis() - start) / 1000.0);
	}
}
