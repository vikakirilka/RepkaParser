package org.ITstep;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoodParser extends Thread{
	
	
	private final String pageUrl;
	public GoodParser(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	@Override
	public void run() {
		ArrayList<Good> goods = getGoodList();
		for (Good good : goods) {
			String text = good.getName() + " *** "  + good.getProductLink();
			FileIOManager.writeTextToFile(text);
		}
		System.out.println("Parser for URL: " + pageUrl + "finished!");
	}
	
	 
	 private ArrayList<Good> getGoodList(){
	  ArrayList<Good> goods = new ArrayList<>();
	  Document page = null;
	  try {
	   page = Jsoup.connect(pageUrl).get();
	   
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	  if(page.toString().isEmpty()) {
	   return goods;
	  }
	  String itemPathFilter="product-item-info";
	  //Element items = page.getElementsByAttributeValueContaining("class", itemPathFilter).first();
	  Elements selectedItems = page.getElementsByAttributeValueContaining("class", itemPathFilter);
	  for (Element itemElement : selectedItems) {
	   
	   Good good = new Good();
	   
	   Element itemNameElement = itemElement.getElementsByAttributeValueMatching("class", "product-item-name").first();
	   String goodName = itemNameElement.getElementsByTag("a").first().text();
	   String goodLink = itemNameElement.getElementsByTag("a").first().attr("href");
	   
	   Element itemPriceElement = itemElement.getElementsByAttributeValueContaining("id", "product-price-").first();
	   int price = Integer.parseInt(itemPriceElement.attr("data-price-amount"));
	   
	   Element itemPhotoElement = itemElement.getElementsByAttributeValueContaining("class", "product-item-photo").first().getElementsByTag("a").first().getElementsByTag("img").first();
	   String imgUrl = itemPhotoElement.attr("data-src");
	   good.setName(goodName);
	   good.setProductLink(goodLink);
	   good.setImgUrl(imgUrl);
	   good.setPrice(price);
	   
	   
	   goods.add(good);
	  }
	  return goods;
	 }


}
