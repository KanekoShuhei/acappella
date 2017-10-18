package acappella.controller.form;

import org.hibernate.validator.constraints.NotBlank;

public class BandForm {

	@NotBlank
	private String name;
	
	@NotBlank
	private String twitter;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	
}
