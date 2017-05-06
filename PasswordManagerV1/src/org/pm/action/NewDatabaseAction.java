package org.pm.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.pm.action.Messages;
import org.pm.dialog.NewDatabaseDialog;
import org.pm.main.PasswordManager;

public class NewDatabaseAction extends Action {
	public NewDatabaseAction() {
		super(Messages.getString("NewDatabase.Label"));
		setAccelerator(SWT.CTRL | 'N');
	}

	public void run() {
		PasswordManager app = PasswordManager.getApp();
		NewDatabaseDialog nd = new NewDatabaseDialog(app.getShell());
		nd.open();
		app.getCR().redraw();
		app.getCR().update();
	}
}
