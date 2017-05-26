package org.pm.composite.PasswordVerify;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.pm.database.Database;
import org.pm.main.PasswordManager;
import org.pm.util.PmColor;

public class CompositePasswordVerify extends Composite {
	/*
	 * GUI Field
	 */
	private static CompositePasswordVerifyTitle compoPVTitle;
	private static CompositePasswordVerifyClose compoPVClose;
	private static CompositePasswordVerifyAdd compoPVAdd;
	private static Text passwordTxt;

	/*
	 * Public Property
	 */
	public static CompositePasswordVerify self;
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
	public CompositePasswordVerify(Composite parent, int style) {
		super(parent, style);
		setBackground(PmColor.VIOLET);
		setLayout(null);
		self = this;
		passwordTxt = new Text(this, SWT.CENTER|SWT.PASSWORD);
		passwordTxt.setBounds(width * 3 / 8, height * 8 / 20, width * 4 / 8, height * 2 / 20);
		passwordTxt.setBackground(PmColor.COMP1);
		compoPVTitle = new CompositePasswordVerifyTitle(this, SWT.NONE);
		compoPVTitle.setBounds(CompositePasswordVerifyTitle.posX, CompositePasswordVerifyTitle.posY,
				CompositePasswordVerifyTitle.width, CompositePasswordVerifyTitle.height);
		compoPVAdd = new CompositePasswordVerifyAdd(this, SWT.NONE);
		compoPVAdd.setBounds(CompositePasswordVerifyAdd.posX, CompositePasswordVerifyAdd.posY, CompositePasswordVerifyAdd.width,
				CompositePasswordVerifyAdd.height);
		compoPVClose = new CompositePasswordVerifyClose(this, SWT.NONE);
		compoPVClose.setBounds(CompositePasswordVerifyClose.posX, CompositePasswordVerifyClose.posY,
				CompositePasswordVerifyClose.width, CompositePasswordVerifyClose.height);
	}

	static CompositePasswordVerify getSelf() {
		return self;
	}
	
	static void CleanTxt(){
		passwordTxt.setText("");
	}
	static String getPassword(){
		return passwordTxt.getText();
	}
}
