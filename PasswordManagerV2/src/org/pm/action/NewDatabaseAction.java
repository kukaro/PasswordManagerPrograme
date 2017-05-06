package org.pm.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.pm.action.Messages;
import org.pm.main.PasswordManager;

public class NewDatabaseAction extends Action {
	public static NewDatabaseAction act;
	public NewDatabaseAction() {
		super(Messages.getString("NewDatabase.Label"));
		act = this;
		setAccelerator(SWT.CTRL | 'N');
	}

	public void run() {
		if(PasswordManager.getCompoNewDatabase().getVisible()==true){
			PasswordManager.getCompoNewDatabase().EraseTxt();
		}
		PasswordManager.getCompoNewDatabase().setVisible(!PasswordManager.getCompoNewDatabase().getVisible());
		PasswordManager.getCompoNewDatabaseShadow().setVisible(!PasswordManager.getCompoNewDatabaseShadow().getVisible());
	}
	
	public static void Run(){
		act.run();
	}
}
