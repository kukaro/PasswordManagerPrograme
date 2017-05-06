package org.pm.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

 
public class CSVRead {
 
    private String fileName;
	private CSVReader reader;
 
    public CSVRead(String fileName) {this.fileName = fileName;}
 
    public List<String []> readCsv() {
    	List<String[]> data = new ArrayList<String[]>();
        try {
            reader = new CSVReader(new FileReader(fileName));
            // UTF-8
            // CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"), ",", '"', 1);
            String[] s;
            while ((s = reader.readNext()) != null) {
                data.add(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return data;
    }
}