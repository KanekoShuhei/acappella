package acappella.controller.form;

import javax.validation.constraints.NotNull;

public class VideoForm {

	@NotNull
	private String youtubeId;
	
	@NotNull
	private String bandId;
	
	public String getYoutubeId() {
		return youtubeId;
	}
	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}
	public String getBandId() {
		return bandId;
	}
	public void setBandId(String bandId) {
		this.bandId = bandId;
	}
	
}
