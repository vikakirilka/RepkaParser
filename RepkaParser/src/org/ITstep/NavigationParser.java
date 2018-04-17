package org.ITstep;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NavigationParser extends Thread {
	
	private final String navPageUrl;
	
	public void runParser() {
		Document doc = null;
		try {
			 doc = Jsoup.connect(navPageUrl).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> pageUrls = new ArrayList<>();
		pageUrls.add(navPageUrl);
		if(!doc.getElementsByAttributeValueMatching("class", "pagination_container").isEmpty()) {
			Element pageContainer = doc.getElementsByAttributeValueMatching("class", "pagination_container").first();
			Elements liElements =  pageContainer.getElementsByTag("li");
			for (Element element : liElements) {
				if(!element.hasAttr("class")) {
					String link = element.getElementsByTag("a").first().attr("href");
					pageUrls.add(link);
					//GoodParser parser = new GoodParser(link);
				}
			}
			
		}
		for(String link: pageUrls) {
			GoodParser parser = new GoodParser(link);
			parser.start();
		}
	}
	
	public NavigationParser(String navPageUrl) {
		this.navPageUrl = navPageUrl;

	}

}
