package org.pm.main;

import java.util.ArrayList;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.pm.action.NewDatabaseAction;
import org.pm.composite.CompositeLeft;
import org.pm.composite.CompositeRight;
import org.pm.database.Database;

public class PasswordManager extends ApplicationWindow {
	/*
	 * static Field
	 */
	private static int height;
	private static int width;
	private static PasswordManager app;
	public static final String APP_NAME = "PasswordManager";

	/*
	 * Instance Field
	 */
	private ArrayList<Database> databaseArr;

	/*
	 * GUI Field
	 */
	private NewDatabaseAction newDatabaseAction;
	private CompositeRight cr;
	private CompositeLeft cl;

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
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		parent.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		app = this;
		databaseArr = null;
		Init();
		container.setLayout(null);
		container.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		cr = new CompositeRight(container, SWT.NONE);
		cr.setBounds(200, 0, 600, 600);
		cl = new CompositeLeft(container, SWT.NONE);
		cl.setBounds(0, 0, 200, 600);

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		newDatabaseAction = new NewDatabaseAction();
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
		/*
		 * Make Menu
		 */
		menuManager.add(menuManagerDatabase);
		menuManager.add(menuManagerEdit);
		/*
		 * Make Menu Detail
		 */
		menuManagerDatabase.add(newDatabaseAction);
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
	 * Create the image resources
	 */

	/**
	 * Init Field
	 */
	private void Init() {
		width = 800;
		height = 600;
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

	public CompositeRight getCR() {
		return cr;
	}
}
