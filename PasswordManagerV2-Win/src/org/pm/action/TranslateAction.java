package org.pm.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.pm.composite.PasswordVerify.CompositePasswordVerifyTitle;
import org.pm.composite.addRecord.CompositeAddRecord;
import org.pm.composite.addRecord.CompositeAddRecordTitle;
import org.pm.composite.showdb.CompositeShowDatabaseTableHead;
import org.pm.composite.showdb.CompositeShowDatabaseTitle;
import org.pm.main.PasswordManager;

public class TranslateAction extends Action {

	public static TranslateAction act;

	public TranslateAction() {
		super(Messages.getString("Translate.Label"));
		act = this;
		setAccelerator(SWT.CTRL | 'A');
	}

	public void run() {
		org.pm.composite.Messages.SwapMode();
		PasswordManager.getCompoNewDatabase().setAllText();
		PasswordManager.getCompoSelectDatabase().setAllText();
		org.pm.composite.showdb.Messages.SwapMode();
		CompositeShowDatabaseTableHead.self.setAllText();
		CompositeShowDatabaseTitle.self.setAllText();
		org.pm.composite.addRecord.Messages.SwapMode();
		CompositeAddRecord.self.setAllText();
		CompositeAddRecordTitle.self.setAllText();
		org.pm.composite.PasswordVerify.Messages.SwapMode();
		CompositePasswordVerifyTitle.self.setAllText();
	}

	public static void Run() {
		act.run();
	}

}
