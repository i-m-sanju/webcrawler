package webcrawler;


import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BuildCrawler {
	public static void main(String[] arg) {
		new BuildCrawler().getLinks("https://www.shiksha.com/");
		
		
		
	}
	private HashSet<String> links;
	public BuildCrawler(){
		links= new HashSet<String>();
	}	
	public void getLinks(String url) {
		
		if(!links.contains(url)) {
			try {
				if(links.add(url)) {
					System.out.println(url);
				}
				
				Document document = Jsoup.connect(url).get(); //gets HTML content on the url
				Elements linksonpage = document.select("a[href]");
				
				
				for (Element page: linksonpage) {
					try {
						Thread.sleep(500);
						getLinks(page.attr("abs:href"));
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
			} catch (IOException e) {
				System.err.println("For " + url + "':" + e.getMessage());
			}
			
			
		}
		
	}
	

}
