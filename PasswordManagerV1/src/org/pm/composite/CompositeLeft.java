package org.pm.composite;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.pm.database.Database;
import org.pm.database.Record;
import org.pm.dialog.MasterPasswordVerifyDialog;
import org.pm.dialog.NewDatabaseDialog;
import org.pm.main.PasswordManager;

public class CompositeLeft extends Composite {
	private static BufferedReader reader;
	private static CompositeLeft cl;
	private static MasterPasswordVerifyDialog mpvd;
	private Image newDB;
	private Image openDB;
	private Point newDBPos;
	private Point openDBPos;
	private GC gc;
	private boolean match; // 특별관리 주의. 예로 인하여 MasterPasswordverifyDialog가 제대로 동작함.

	public CompositeLeft(Composite parent, int style) {
		super(parent, style);
		cl = this;
		setBackground(new Color(null, 0xFF, 0xFF, 0xFF));
		newDBPos = new Point(0, 0);
		openDBPos = new Point(0, 200);
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent pe) {
				match = false;
				gc = pe.gc;
				newDB = new Image(pe.display, "src/org/pm/resource/NewDB.png");
				openDB = new Image(pe.display, "src/org/pm/resource/OpenDB.png");
				gc.drawImage(newDB, newDBPos.x, newDBPos.y);
				gc.drawImage(openDB, openDBPos.x, openDBPos.y);

			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent me) {
				if (me.x > newDBPos.x && me.x <= newDBPos.x + 200 && me.y > newDBPos.y && me.y <= newDBPos.y + 200) {
					PasswordManager app = PasswordManager.getApp();
					NewDatabaseDialog nd = new NewDatabaseDialog(app.getShell());
					nd.open();
					app.getCR().redraw();
					app.getCR().update();
				} else if (me.x > openDBPos.x && me.x <= openDBPos.x + 200 && me.y > openDBPos.y
						&& me.y <= openDBPos.y + 200) {
					PasswordManager app = PasswordManager.getApp();
					FileDialog fdd = new FileDialog(app.getShell());
					fdd.setFilterExtensions(new String[] { "*.csv" });
					String filepath = fdd.open();
					String cvsSplitBy = ",";
					String tmp;
					try {
						reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "euc-kr"));
						tmp = reader.readLine();
						String[] field = tmp.split(cvsSplitBy);
						String name = field[0];
						String password = field[1];
						int recordCount = Integer.parseInt(field[2]);
						mpvd = new MasterPasswordVerifyDialog(app.getShell(), password);
						mpvd.open();
						if(match == true){
							Database db = new Database(name, password);
							for (int i = 0; i < recordCount; i++) {
								tmp = reader.readLine();
								field = tmp.split(cvsSplitBy);
								db.AddRecord(new Record(field[0], field[1], field[2]));
							}
							CompositeRight.getCR().addDatabase(db);
							reader.close();
							CompositeRight.getCR().redraw();
						}
					} catch (UnsupportedEncodingException | FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void mouseDown(MouseEvent me) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}

			
		});
	}

	public GC getGC() {
		return gc;
	}
	public static CompositeLeft getCL(){
		return cl;
	}
	public void setMatch(boolean b) {
		match = b;
	}
	public static void MasterPasswordVerifyDialogClose() {
		mpvd.close();
	}
}
