package org.pm.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.pm.composite.Composite01_02;
import org.pm.composite.PasswordVerify.CompositePasswordVerify;
import org.pm.main.PasswordManager;

public class TranslateAction extends Action {

	public static TranslateAction act;

	public TranslateAction() {
		super(Messages.getString("Translate.Label"));
		act = this;
		setAccelerator(SWT.CTRL | 'A');
	}

	public void run() {
		
	}

	public static void Run() {
		act.run();
	}

}
