package org.pm.composite.addRecord;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.pm.composite.showdb.CompositeShDBClose;
import org.pm.database.Database;
import org.pm.main.PasswordManager;
import org.pm.util.PmColor;

public class CompositeAddRecord extends Composite {
	/*
	 * GUI Field
	 */
	private static CompositeAddRecordTitle compoARTitle;
	private static CompositeAddRecordClose compoARClose;
	private static CompositeAddRecordAdd compoARAdd;
	private static Label IDLbl;
	private static Label passwordLbl;
	private static Label URLLbl;
	private static Text IDTxt;
	private static Text passwordTxt;
	private static Text URLTxt;

	/*
	 * Public Property
	 */
	public static CompositeAddRecord compoAR;
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
		posX = PasswordManager.width / 5 - 20;
		posY = PasswordManager.height / 5 - 20;
	}

	/**
	 * Creater CompositeShowDatabase : Set personal property(ex:
	 * width,height,posX,posY,image resource)
	 * 
	 * @param parent
	 * @param style
	 */
	public CompositeAddRecord(Composite parent, int style) {
		super(parent, style);
		setBackground(PmColor.VIOLET);
		setLayout(null);
		compoAR = this;
		IDLbl = new Label(this, SWT.CENTER);
		IDLbl.setBounds(width * 1 / 8, height * 5 / 20, width * 2 / 8, height * 2 / 20);
		IDLbl.setText(Messages.getString("CompositeAddRecord.ID"));
		IDLbl.setBackground(PmColor.BLUE);
		IDTxt = new Text(this, SWT.CENTER);
		IDTxt.setBounds(width * 3 / 8, height * 5 / 20, width * 4 / 8, height * 2 / 20);
		IDTxt.setBackground(PmColor.COMP1);
		passwordLbl = new Label(this, SWT.CENTER);
		passwordLbl.setBounds(width * 1 / 8, height * 8 / 20, width * 2 / 8, height * 2 / 20);
		passwordLbl.setText(Messages.getString("CompositeAddRecord.password"));
		passwordLbl.setBackground(PmColor.BLUE);
		passwordTxt = new Text(this, SWT.CENTER);
		passwordTxt.setBounds(width * 3 / 8, height * 8 / 20, width * 4 / 8, height * 2 / 20);
		passwordTxt.setBackground(PmColor.COMP1);
		URLLbl = new Label(this, SWT.CENTER);
		URLLbl.setBounds(width * 1 / 8, height * 11 / 20, width * 2 / 8, height * 2 / 20);
		URLLbl.setText(Messages.getString("CompositeAddRecord.URL"));
		URLLbl.setBackground(PmColor.BLUE);
		URLTxt = new Text(this, SWT.CENTER);
		URLTxt.setBounds(width * 3 / 8, height * 11 / 20, width * 4 / 8, height * 2 / 20);
		URLTxt.setBackground(PmColor.COMP1);
		compoARTitle = new CompositeAddRecordTitle(this, SWT.NONE);
		compoARTitle.setBounds(CompositeAddRecordTitle.posX, CompositeAddRecordTitle.posY,
				CompositeAddRecordTitle.width, CompositeAddRecordTitle.height);
		compoARAdd = new CompositeAddRecordAdd(this, SWT.NONE);
		compoARAdd.setBounds(CompositeAddRecordAdd.posX, CompositeAddRecordAdd.posY, CompositeAddRecordAdd.width,
				CompositeAddRecordAdd.height);
		compoARClose = new CompositeAddRecordClose(this, SWT.NONE);
		compoARClose.setBounds(CompositeAddRecordClose.posX, CompositeAddRecordClose.posY,
				CompositeAddRecordClose.width, CompositeAddRecordClose.height);
	}

	static CompositeAddRecord getSelf() {
		return compoAR;
	}
	
	public static String getID(){
		return IDTxt.getText();
	}
	
	public static String getPassword(){
		return passwordTxt.getText();
	}
	
	public static String getURL(){
		return URLTxt.getText();
	}
	
	public static void CleanTxt(){
		IDTxt.setText("");
		passwordTxt.setText("");
		URLTxt.setText("");
	}
	
}
