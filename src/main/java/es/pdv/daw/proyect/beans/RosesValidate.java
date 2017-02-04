package es.pdv.daw.proyect.beans;

import org.springframework.stereotype.Component;

@Component
public class RosesValidate {
	
	private String message;
	
	private boolean existRosal;

	public RosesValidate() {
		super();

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isExistRosal() {
		return existRosal;
	}

	public void setExistRosal(boolean existRosal) {
		this.existRosal = existRosal;
	}
	
	
	
}
