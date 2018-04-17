package org.ITstep;



public class ParserRunner {

	public static void main(String[] args) {
		
		if(args.length == 0) {
			return;
		}
		StartUpParser startUpParser = new StartUpParser(args[0]);
		startUpParser.runStartUpParser();
		

	}

}
