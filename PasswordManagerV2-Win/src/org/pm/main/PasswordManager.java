package org.pm.main;

import java.util.ArrayList;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.pm.action.LoadDatabaseAction;
import org.pm.action.NewDatabaseAction;
import org.pm.action.TranslateAction;
import org.pm.composite.Composite01_01;
import org.pm.composite.Composite01_02;
import org.pm.composite.Composite02_01;
import org.pm.composite.Composite02_02;
import org.pm.composite.CompositeNewDatabase;
import org.pm.composite.CompositeSelectDatabase;
import org.pm.composite.CompositeShadow;
import org.pm.composite.PasswordVerify.CompositePasswordVerify;
import org.pm.composite.addRecord.CompositeAddRecord;
import org.pm.composite.showdb.CompositeShowDatabase;
import org.pm.database.Database;
import org.pm.util.PmColor;
import org.pm.util.PmValue;

public class PasswordManager extends ApplicationWindow {
	/*
	 * public Property
	 */
	public static int height;
	public static int width;
	public static final String APP_NAME = "PasswordManager";

	/*
	 * static Field
	 */
	private static PasswordManager app;
	private static NewDatabaseAction newDatabaseAction;
	private static LoadDatabaseAction loadDatabaseAction;
	private static TranslateAction translateAction;
	private static ArrayList<Database> db;

	/*
	 * Instance Field
	 */
	// private GC gc;
	// private Image img;

	/*
	 * GUI Field
	 */
	private static CompositeNewDatabase compoND;
	private static CompositeSelectDatabase compoSD;
	private static CompositeShowDatabase compoShD;
	private static CompositeAddRecord compoAR;
	private static CompositePasswordVerify compoPV;
	private static Composite01_01 compo0101;
	private static Composite01_02 compo0102;
	private static Composite02_01 compo0201;
	private static Composite02_02 compo0202;
	private static CompositeShadow composND;
	private static CompositeShadow composSD;
	private static CompositeShadow composShD;
	private static CompositeShadow composAR;
	private static CompositeShadow composPV;
	private static CompositeShadow compos0101;
	private static CompositeShadow compos0102;
	private static CompositeShadow compos0201;
	private static CompositeShadow compos0202;

	/*
	 * Initialize Block
	 */
	{
		db = new ArrayList<Database>();
		width = 600;
		height = 650;
	}

	/**
	 * Create the application window.
	 */
	public PasswordManager() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		// addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@SuppressWarnings("static-access")
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		app = this;
		container.setLayout(null);
		container.setBackground(PmColor.COMP1);
		this.getShell().setSize(620,720);

		/*
		 * Global Composite
		 */
		compoPV = new CompositePasswordVerify(container, SWT.NONE);
		compoPV.setBounds(compoPV.posX, compoPV.posY, compoPV.width, compoPV.height);
		compoPV.setVisible(false);
		composPV = new CompositeShadow(container, SWT.NONE);
		composPV.setBounds(compoPV.posX - PmValue.SHADOW, compoPV.posY - PmValue.SHADOW,
				compoPV.width + PmValue.SHADOW * 2, compoPV.height + PmValue.SHADOW * 2);
		composPV.setVisible(false);
		compoAR = new CompositeAddRecord(container, SWT.NONE);
		compoAR.setBounds(compoAR.posX, compoAR.posY, compoAR.width, compoAR.height);
		compoAR.setVisible(false);
		composAR = new CompositeShadow(container, SWT.NONE);
		composAR.setBounds(compoAR.posX - PmValue.SHADOW, compoAR.posY - PmValue.SHADOW,
				compoAR.width + PmValue.SHADOW * 2, compoAR.height + PmValue.SHADOW * 2);
		composAR.setVisible(false);
		compoShD = new CompositeShowDatabase(container, SWT.NONE);
		compoShD.setBounds(compoShD.posX, compoShD.posY, compoShD.width, compoShD.height);
		compoShD.setVisible(false);
		composShD = new CompositeShadow(container, SWT.NONE);
		composShD.setBounds(compoShD.posX - PmValue.SHADOW, compoShD.posY - PmValue.SHADOW,
				compoShD.width + PmValue.SHADOW * 2, compoShD.height + PmValue.SHADOW * 2);
		composShD.setVisible(false);
		compoND = new CompositeNewDatabase(container, SWT.NONE);
		compoND.setBounds(compoND.posX, compoND.posY, compoND.width, compoND.height);
		compoND.setVisible(false);
		composND = new CompositeShadow(container, SWT.NONE);
		composND.setBounds(compoND.posX - PmValue.SHADOW, compoND.posY - PmValue.SHADOW,
				compoND.width + PmValue.SHADOW * 2, compoND.height + PmValue.SHADOW * 2);
		composND.setVisible(false);
		compoSD = new CompositeSelectDatabase(container, SWT.NONE);
		compoSD.setBounds(compoSD.posX, compoSD.posY, compoSD.width, compoSD.height);
		compoSD.setVisible(false);
		composSD = new CompositeShadow(container, SWT.NONE);
		composSD.setBounds(compoSD.posX - PmValue.SHADOW, compoSD.posY - PmValue.SHADOW,
				compoSD.width + PmValue.SHADOW * 2, compoSD.height + PmValue.SHADOW * 2);
		composSD.setVisible(false);
		/*
		 * Instance Composite
		 */
		compo0101 = new Composite01_01(container, SWT.NONE);
		compo0101.setBounds(Composite01_01.posX, Composite01_01.posY, Composite01_01.width, Composite01_01.height);
		compos0101 = new CompositeShadow(container, SWT.NONE);
		compos0101.setBounds(Composite01_01.posX + PmValue.SHADOW, Composite01_01.posY + PmValue.SHADOW,
				Composite01_01.width + PmValue.SHADOW, Composite01_01.height + PmValue.SHADOW);
		compo0102 = new Composite01_02(container, SWT.NONE);
		compo0102.setBounds(Composite01_02.posX, Composite01_02.posY, Composite01_02.width, Composite01_02.height);
		compos0102 = new CompositeShadow(container, SWT.NONE);
		compos0102.setBounds(Composite01_02.posX + PmValue.SHADOW, Composite01_02.posY + PmValue.SHADOW,
				Composite01_02.width + PmValue.SHADOW, Composite01_02.height + PmValue.SHADOW);
		compo0201 = new Composite02_01(container, SWT.NONE);
		compo0201.setBounds(Composite02_01.posX, Composite02_01.posY, Composite02_01.width, Composite02_01.height);
		compos0201 = new CompositeShadow(container, SWT.NONE);
		compos0201.setBounds(Composite02_01.posX + PmValue.SHADOW, Composite02_01.posY + PmValue.SHADOW,
				Composite02_01.width + PmValue.SHADOW, Composite02_01.height + PmValue.SHADOW);
		compo0202 = new Composite02_02(container, SWT.NONE);
		compo0202.setBounds(Composite02_02.posX, Composite02_02.posY, Composite02_02.width, Composite02_02.height);
		compos0202 = new CompositeShadow(container, SWT.NONE);
		compos0202.setBounds(Composite02_02.posX + PmValue.SHADOW, Composite02_02.posY + PmValue.SHADOW,
				Composite02_02.width + PmValue.SHADOW, Composite02_02.height + PmValue.SHADOW);

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		newDatabaseAction = new NewDatabaseAction();
		loadDatabaseAction = new LoadDatabaseAction();
		translateAction = new TranslateAction();
	}

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager(Messages.getString("PasswordManager.Menu.Menu.Label"));
		MenuManager menuManagerDatabase = new MenuManager(Messages.getString("PasswordManager.Menu.Database.Label"));
		MenuManager menuManagerEdit = new MenuManager(Messages.getString("PasswordManager.Menu.Edit.Label"));
		MenuManager menuManagerTrans = new MenuManager(Messages.getString("PasswordManager.Menu.Trans.Label"));
		/*
		 * Make Menu
		 */
		menuManager.add(menuManagerDatabase);
		menuManager.add(menuManagerEdit);
		menuManager.add(menuManagerTrans);
		/*
		 * Make Menu Detail
		 */
		menuManagerDatabase.add(newDatabaseAction);
		menuManagerDatabase.add(loadDatabaseAction);
		menuManagerDatabase.add(translateAction);
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * 
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			PasswordManager window = new PasswordManager();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(
				Messages.getString("PasswordManager.Title") + " " + Messages.getString("PasswordManager.Version"));
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(width, height);
	}

	public static PasswordManager getApp() {
		return app;
	}

	public static CompositeNewDatabase getCompoNewDatabase() {
		return compoND;
	}

	public static CompositeShadow getCompoNewDatabaseShadow() {
		return composND;
	}

	public static CompositeSelectDatabase getCompoSelectDatabase() {
		return compoSD;
	}

	public static CompositeShadow getCompoSelectDatabaseShadow() {
		return composSD;
	}

	public static CompositeShowDatabase getCompositeShowDatabase() {
		return compoShD;
	}

	public static CompositeShadow getCompositeShowDatabaseShadow() {
		return composShD;
	}

	public static CompositeAddRecord getCompositeAddRecord() {
		return compoAR;
	}

	public static CompositeShadow getCompositeAddRecordShadow() {
		return composAR;
	}
	public static CompositePasswordVerify getCompositePasswordVerify() {
		return compoPV;
	}

	public static CompositeShadow getCompositePasswordVerifyShadow() {
		return composPV;
	}
	public static void setDB(String ID, String Password) {
		db.add(new Database(ID, Password));
		// System.out.println(db.size());
	}

	public static ArrayList<Database> getDB() {
		return db;
	}
}
