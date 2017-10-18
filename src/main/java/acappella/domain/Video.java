package acappella.domain;

public class Video {
	
	private int id;
	private String youtubeId;
	private int bandId;
	private String bandName;
	private int likes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYoutubeId() {
		return youtubeId;
	}
	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}
	public int getBandId() {
		return bandId;
	}
	public void setBandId(int bandId) {
		this.bandId = bandId;
	}
	
	public String getBandName() {
		return bandName;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}

}
