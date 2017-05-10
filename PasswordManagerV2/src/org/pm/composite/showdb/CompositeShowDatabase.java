package org.pm.composite.showdb;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.pm.database.Database;
import org.pm.database.Record;
import org.pm.main.PasswordManager;
import org.pm.util.PmColor;

public class CompositeShowDatabase extends Composite {
	/*
	 * GUI Field
	 */
	private static CompositeShowDatabaseTitle compoTblTitle;
	private static CompositeShowDatabaseTableHead compoTblHead;
	private static CompositeShowDatabaseTableBody compoTblBody;
	private static CompositeShDBClose compoShDBClose;
	private static CompositeShDBAdd compoShDBAdd;
	private static CompositeShDBEdit compoShDBEdit;

	/*
	 * Public Property
	 */
	public static CompositeShowDatabase compoShowDB;
	public static int width;
	public static int height;
	public static int posX;
	public static int posY;

	/*
	 * Static Field
	 */
	private static Database db;

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
	}

	/**
	 * Creater CompositeShowDatabase : Set personal property(ex:
	 * width,height,posX,posY,image resource)
	 * 
	 * @param parent
	 * @param style
	 */
	public CompositeShowDatabase(Composite parent, int style) {
		super(parent, style);
		setBackground(PmColor.DAISY);
		setLayout(null);
		compoShowDB = this;
		/*
		 * Test Case
		 */
		compoTblTitle = new CompositeShowDatabaseTitle(this, SWT.NONE, "INIT");
		compoTblTitle.setBounds(CompositeShowDatabaseTitle.posX, CompositeShowDatabaseTitle.posY,
				CompositeShowDatabaseTitle.width, CompositeShowDatabaseTitle.height);
		compoTblHead = new CompositeShowDatabaseTableHead(this, SWT.NONE);
		compoTblHead.setBounds(CompositeShowDatabaseTableHead.posX, CompositeShowDatabaseTableHead.posY,
				CompositeShowDatabaseTableHead.width, CompositeShowDatabaseTableHead.height);
		compoTblHead.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_TRANSPARENT));
		compoTblBody = new CompositeShowDatabaseTableBody(this, SWT.H_SCROLL | SWT.V_SCROLL);
		compoTblBody.setBounds(CompositeShowDatabaseTableBody.posX, CompositeShowDatabaseTableBody.posY,
				CompositeShowDatabaseTableBody.width, CompositeShowDatabaseTableBody.height);
		compoShDBClose = new CompositeShDBClose(this, SWT.NONE);
		compoShDBClose.setBounds(CompositeShDBClose.posX, CompositeShDBClose.posY, CompositeShDBClose.width,
				CompositeShDBClose.height);
		compoShDBAdd = new CompositeShDBAdd(this, SWT.NONE);
		compoShDBAdd.setBounds(CompositeShDBAdd.posX, CompositeShDBAdd.posY, CompositeShDBAdd.width,
				CompositeShDBAdd.height);
		compoShDBEdit = new CompositeShDBEdit(this, SWT.NONE);
		compoShDBEdit.setBounds(CompositeShDBEdit.posX, CompositeShDBEdit.posY, CompositeShDBEdit.width,
				CompositeShDBEdit.height);
		
	}

	public static CompositeShowDatabase getSelf() {
		return compoShowDB;
	}

	public static void LoadDB(Database db) {
		CompositeShowDatabase.db = db;
		CompositeShowDatabase.getCompoShowDatabaseTitle().setText(db.getName());
		CompositeShowDatabaseTableBody.LoadDB(db);
	}

	static CompositeShowDatabaseTitle getCompoShowDatabaseTitle() {
		return compoTblTitle;
	}

	public static void AddRecord(Record r){
		db.AddRecord(r);
	}
	
	public static Database getDB(){
		return db;
	}
}
