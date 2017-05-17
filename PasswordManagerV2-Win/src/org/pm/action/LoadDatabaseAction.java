package org.pm.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.pm.action.Messages;
import org.pm.main.PasswordManager;

public class LoadDatabaseAction extends Action {
	public static LoadDatabaseAction act;
	public LoadDatabaseAction() {
		super(Messages.getString("LoadDatabase.Label"));
		act = this;
		setAccelerator(SWT.CTRL | 'L');
	}

	public void run() {
		PasswordManager.getCompoSelectDatabase().setVisible(!PasswordManager.getCompoSelectDatabase().getVisible());
		PasswordManager.getCompoSelectDatabaseShadow().setVisible(!PasswordManager.getCompoSelectDatabaseShadow().getVisible());
		PasswordManager.getCompoSelectDatabase().redraw();
		PasswordManager.getCompoSelectDatabase().update();
	}
	
	public static void Run(){
		act.run();
	}
}
