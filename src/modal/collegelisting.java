package modal;

public class collegelisting {
	public collegelisting (String link, int visit) {
		listing_link=link;
		visited=visit;
	}
	public collegelisting(){
		
	}
	
	public String getListing_link() {
		return listing_link;
	}
	
	public void setListing_link(String listing_link) {
		this.listing_link = listing_link;
	}
	public void setVisited(int visited) {
		this.visited = visited;
	}
	public int getVisited() {
		return visited;
	}
	private String listing_link;
	private int visited;
	
}
