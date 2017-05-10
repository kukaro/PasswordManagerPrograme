package org.pm.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVWriter;
 
 
public class CSVWrite {
	private CSVWriter cw;
    private String filename;
 
    public CSVWrite(String fileName) {
    	this.filename = fileName;
    }
 
    public void writeCsv(List<String[]> data) {
        try {
            cw = new CSVWriter(new FileWriter(filename), ',', '"');
            Iterator<String[]> it = data.iterator();
            try {
                while (it.hasNext()) {
                    String[] s = (String[]) it.next();
                    cw.writeNext(s);
                }
            } finally {
                cw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}