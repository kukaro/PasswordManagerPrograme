package org.pm.composite;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.pm.composite.Messages;
import org.pm.database.Database;
import org.pm.main.PasswordManager;
import org.pm.util.PmColor;

public class CompositeSelectDatabase extends Composite {
	/*
	 * GUI Field
	 */
	private Label selDBLbl;
	private ArrayList<CompositeDatabaseFile> dbNameCompoArr;

	/*
	 * Public Property
	 */
	public static CompositeSelectDatabase compoSelDB;
	public static int width;
	public static int height;
	public static int posX;
	public static int posY;

	/*
	 * Static Field
	 */
	private static ArrayList<Database> db;

	/*
	 * Instance Field
	 */
	private GC gc;
	private Image img;

	/*
	 * Initialize Block
	 */
	{
		width = PasswordManager.width * 3 / 5;
		height = PasswordManager.height * 3 / 5;
		posX = PasswordManager.width / 5;
		posY = PasswordManager.height / 5;
		db = PasswordManager.getDB();
		dbNameCompoArr = new ArrayList<CompositeDatabaseFile>();
	}

	/**
	 * Creater CompositeNewDatabase : Set personal property(ex:
	 * width,height,posX,posY,image resource)
	 * 
	 * @param parent
	 * @param style
	 */
	public CompositeSelectDatabase(Composite parent, int style) {
		super(parent, style);
		setBackground(PmColor.GLAY);
		setLayout(null);
		compoSelDB = this;
		selDBLbl = new Label(this, SWT.CENTER);
		selDBLbl.setBounds(width * 1 / 5, 20, 200, 20);
		selDBLbl.setFont(new Font(this.getDisplay(), "Arial", 16, SWT.BOLD));
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent pe) {
				selDBLbl.setText(Messages.getString("SelDatabaseComposite.title") + " : count " + db.size());
				for (int i = dbNameCompoArr.size(); i < db.size(); i++) {
					if (dbNameCompoArr.size() == 0) {
						dbNameCompoArr.add(new CompositeDatabaseFile(compoSelDB, SWT.NONE, db.get(i)));
						dbNameCompoArr.get(i).setBounds(dbNameCompoArr.get(i).posX, dbNameCompoArr.get(i).posY,
								dbNameCompoArr.get(i).width, dbNameCompoArr.get(i).height);
					} else {
						dbNameCompoArr.add(new CompositeDatabaseFile(compoSelDB, SWT.NONE, db.get(i),
								dbNameCompoArr.get(i - 1).posX,
								dbNameCompoArr.get(i - 1).posY + dbNameCompoArr.get(i - 1).height));
						dbNameCompoArr.get(i).setBounds(dbNameCompoArr.get(i).posX, dbNameCompoArr.get(i).posY,
								dbNameCompoArr.get(i).width, dbNameCompoArr.get(i).height);
					}
				}
			}
		});
	}

	static CompositeSelectDatabase getSelf() {
		return compoSelDB;
	}
}
