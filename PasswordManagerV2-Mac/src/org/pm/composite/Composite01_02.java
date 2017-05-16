package org.pm.composite;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.pm.database.Database;
import org.pm.database.Record;
import org.pm.main.PasswordManager;
import org.pm.util.AES256Util;
import org.pm.util.CSVRead;
import org.pm.util.PmColor;

public class Composite01_02 extends Composite {
	/*
	 * Public Property
	 */
	public static int width;
	public static int height;
	public static int posX;
	public static int posY;

	/*
	 * Static Field
	 */
	private static AES256Util aes;
	private static CSVRead csvreader;
	private static boolean match;
	private static String password;
	private static String filepath;
	private static Composite01_02 self;

	/*
	 * Instance Field
	 */
	private GC gc;
	private Image img;
	

	/*
	 * Initialize Block
	 */
	{
		width = PasswordManager.width * 13 / 16 / 2;
		height = PasswordManager.height * 9 / 12 / 2;
		posX = PasswordManager.width * 2 / 16 + Composite01_01.width;
		posY = PasswordManager.height / 12;
	}

	/**
	 * Creater Composite01_02: Set personal property(ex:
	 * width,height,posX,posY,image resource)
	 * 
	 * @param parent
	 * @param style
	 */
	public Composite01_02(Composite parent, int style) {
		super(parent, style);
		setBackground(PmColor.COMP3);
		setLayout(null);
		match = false;
		self = this;
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent pe) {
				gc = pe.gc;
				img = new Image(pe.display, "src/org/pm/resource/OpenDB.png");
				Image imgScale = new Image(pe.display, img.getImageData().scaledTo(width * 3 / 4, height * 3 / 4));
				gc.drawImage(imgScale, width / 8, 0);
			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
				PasswordManager app = PasswordManager.getApp();
				FileDialog fdd = new FileDialog(app.getShell());
				fdd.setFilterExtensions(new String[] { "*.csv" });
				filepath = fdd.open();
				PasswordManager.getCompositePasswordVerify()
						.setVisible(!PasswordManager.getCompositePasswordVerify().getVisible());
				PasswordManager.getCompositePasswordVerifyShadow()
						.setVisible(!PasswordManager.getCompositePasswordVerifyShadow().getVisible());
				/*if(match == true){
					csvreader = new CSVRead(filepath);
					List<String[]> data = csvreader.readCsv();
					Database db = null;
					for (int i = 0; i < data.size(); i++) {
						if (i == 0) {
							db = new Database(data.get(i)[0], data.get(i)[1]);
							System.out.println(data.get(i)[0] + data.get(i)[1]);
						} else {
							db.AddRecord(new Record(data.get(i)[0], data.get(i)[1], data.get(i)[2]));
							System.out.println(data.get(i)[0] + data.get(i)[1] + data.get(i)[2]);
						}
					}
				}*/
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	public static Composite01_02 getSelf(){
		return self;
	}
	public static void setMatch(boolean match){
		Composite01_02.match = match;
	}

	public static void setPassword(String password){
		Composite01_02.password = password;
	}
	
	public static String getFilepath(){
		return filepath;
	}
}
