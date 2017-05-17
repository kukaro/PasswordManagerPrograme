package org.pm.database;

import java.util.ArrayList;

import org.pm.util.SHA256;

public class Database {
	private String name;
	private String password;
	private int recordCounter;
	private boolean changed;
	private ArrayList<Record> recordArr;

	public Database(String name, String password) {
		this.name = name;
		this.password = SHA256.encrypt(password);
		this.recordArr = new ArrayList<Record>();
		this.changed = true;
		recordCounter = 0;
	}
	public Database(String name, String password,boolean load) {
		this.name = name;
		if(load==true){
			this.password = password;
		}
		else{
			this.password = SHA256.encrypt(password);
		}
		this.recordArr = new ArrayList<Record>();
		this.changed = true;
		recordCounter = 0;
	}

	/**
	 * Must insert to new Instance.
	 * 
	 * @param newRecord
	 */
	public void AddRecord(Record newRecord) {
		changed = true;
		recordCounter++;
		recordArr.add(newRecord);
	}

	/**
	 * getRecord fisrt model : not use
	 * 
	 * @param index
	 * @return
	 */
	/*public Record getRecord(int index) {
		Record tmp = recordArr.get(index);
		return new Record(tmp.getID(), tmp.getPassword(), tmp.getURL());
	}*/
	
	/**
	 * getRecord second model : use
	 * 
	 * @param index
	 * @return
	 */
	public Record getRecord(int index) {
		return recordArr.get(index);
	}

	public int Size() {
		return recordCounter;
	}

	public String getName() {
		return name;
	}
	public String getPassword(){
		return password;
	}
	public boolean getChanged(){
		return changed;
	}
	public void invChanged(){
		changed = !changed;
	}
}
