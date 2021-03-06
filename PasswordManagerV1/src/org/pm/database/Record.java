package org.pm.database;

public class Record {
	private String ID;
	private String Password;
	private String URL;

	public Record(String ID, String Password, String URL) {
		this.ID = ID;
		this.Password = Password;
		this.URL = URL;
	}

	public Record getRecord() {
		return new Record(ID, Password, URL);
	}
	
	public String getID(){
		return ID;
	}
	
	public String getPassword(){
		return Password;
	}
	
	public String getURL(){
		return URL;
	}
}
