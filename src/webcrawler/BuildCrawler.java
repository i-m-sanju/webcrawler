package webcrawler;
import java.io.IOException;
//package connecting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection; 
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient; 
import modal.*;

public class BuildCrawler {
	public static void main(String[] arg) {
		new BuildCrawler().getLinks("https://www.kccitm.edu.in/");

	}
	
	
	private ArrayList<String> links;
	public BuildCrawler(){
		links= new ArrayList<String>();
	}	
	public void getLinks(String url) {
		ArrayList<collegelisting> list = new ArrayList<collegelisting>();
		ArrayList<String> link = new ArrayList<String>();
		
		collegelisting c = new collegelisting();		
		
		MongoClient mongo = new MongoClient( "localhost" , 27017 ); 

	    MongoDatabase database = mongo.getDatabase("siksha");  
	    MongoCollection<Document> collection = database.getCollection("sampleconnection");
//			
			
			
		collegelisting r= new collegelisting();
		if(!links.contains(url)) {
			try {
				if(links.add(url)) {
					System.out.println(url);

					
					
					c.setListing_link(url);
					
					String json = new Gson().toJson(c);
					System.out.println(json);
					list.add(c);
					List<Document> a = new ArrayList<Document>();
					Document adoc = new Document();
					adoc.append("Url", c.getListing_link());
//					adoc.append("phone", c.getVisited());
					a.add(adoc);
					collection.insertOne(adoc);
				}
			
				org.jsoup.nodes.Document document = Jsoup.connect(url).get(); //gets HTML content on the url
//				Elements nav = document.select("li.g_levl");
				Elements linksonpage = document.select("a[href]");
				System.out.println("Hello");
//				System.out.println(linksonpage);
				
			
				
				
				for (Element header: linksonpage) {
					try {
						Thread.sleep(500);
						String url2=header.attr("href");

					url2 = url2.contains("https://www.kccitm.edu.in")? null : "https://www.kccitm.edu.in"+url2;
						
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
