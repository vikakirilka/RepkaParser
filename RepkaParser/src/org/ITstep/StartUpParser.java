package org.ITstep;

public class StartUpParser {
	
	private final String searchUr;
	
	
	public StartUpParser(String keyword) {
		searchUr =  "https://m.repka.ua/catalogsearch/result/?q=" + keyword;
		
	}
	protected void runStartUpParser() {
		 NavigationParser navParser = new NavigationParser(searchUr);
		  navParser.runParser();

		
	}


}
