package webcrawler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import modal.collegelisting;

public class BuildCrawler {
	public static void main(String[] arg) {
		new BuildCrawler().getLinks("https://www.shiksha.com/");
		
		
		
	}
	
	
	private HashSet<String> links;
	public BuildCrawler(){
		links= new HashSet<String>();
	}	
	public void getLinks(String url) {
		ArrayList<collegelisting> list = new ArrayList<collegelisting>();
		
		collegelisting r= new collegelisting();
		if(!links.contains(url)) {
			try {
				if(links.add(url)) {
					System.out.println(url);
					list.add(r);
					
				}
				
				Document document = Jsoup.connect(url).get(); //gets HTML content on the url
				Elements nav = document.select("li.g_levl");
				Elements linksonpage = document.select("div.submenu2 a[href]");
				System.out.println("Hello");
//				System.out.println(linksonpage);
				
				
				
				for (Element header: linksonpage) {
					try {
						Thread.sleep(500);
						String url2=header.attr("href");

					url2 = url2.contains("https://www.shiksha.com")? null : "https://www.shiksha.com"+url2;
						
						getLinks(url2);
						
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
