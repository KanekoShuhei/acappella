package acappella.domain;

public class Band {
	
	private int id;
	private String name;
	private int popularity;
	private String twitter;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}	

}
