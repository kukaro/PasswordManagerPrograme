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

	public Record getRecord(int index) {
		Record tmp = recordArr.get(index);
		return new Record(tmp.getID(), tmp.getPassword(), tmp.getURL());
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
	public boolean changed(){
		return changed;
	}
}
