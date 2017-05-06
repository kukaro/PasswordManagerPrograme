package org.pm.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.pm.database.Database;
import org.pm.main.PasswordManager;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

public class NewDatabaseDialog extends Dialog {
	/*
	 * GUI Field
	 */
	private Label nameLbl;
	private Label passwordLbl;
	private Label passwordVerifiedLbl;
	private Label informationLbl;
	private Text nameTxt;
	private Text passwordTxt;
	private Text passwordVerifiedTxt;
	private Button OKBtn;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public NewDatabaseDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		parent.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		container.setLayout(null);
		container.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		nameLbl = new Label(container, SWT.NONE);
		nameLbl.setText(Messages.getString("NewDatabaseDialog.name") + " :");
		nameLbl.setBounds(50, 50, 20, 20);
		nameLbl.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		passwordLbl = new Label(container, SWT.NONE);
		passwordLbl.setText(Messages.getString("NewDatabaseDialog.Password") + " :");
		passwordLbl.setBounds(50, 100, 100, 20);
		passwordLbl.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		passwordVerifiedLbl = new Label(container, SWT.NONE);
		passwordVerifiedLbl.setText(Messages.getString("NewDatabaseDialog.Password.Verify") + " :");
		passwordVerifiedLbl.setBounds(50, 150, 100, 20);
		passwordVerifiedLbl.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		nameTxt = new Text(container, SWT.NONE);
		nameTxt.setBounds(150, 50, 200, 20);
		nameTxt.setBackground(new Color(null, 0x33, 0x66, 0xFF));
		informationLbl = new Label(container, SWT.NONE);
		informationLbl.setBounds(50, 200, 300, 20);
		informationLbl.setBackground(new Color(null, 0x33, 0x66, 0xFF));
		passwordTxt = new Text(container, SWT.PASSWORD);
		passwordTxt.setBounds(150, 100, 200, 20);
		passwordTxt.setBackground(new Color(null, 0x33, 0x66, 0xFF));
		passwordVerifiedTxt = new Text(container, SWT.PASSWORD);
		passwordVerifiedTxt.setBounds(150, 150, 200, 20);
		passwordVerifiedTxt.setBackground(new Color(null, 0x33, 0x66, 0xFF));
		passwordVerifiedTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent ke) {
				if (!passwordTxt.getText().equals(passwordVerifiedTxt.getText())) {
					informationLbl.setText("Password was not match");
				} else {
					Zxcvbn zx = new Zxcvbn();
					Strength st = zx.measure(passwordTxt.getText());
					int ranki = st.getScore();
					String ranks;
					if (ranki == 0)
						ranks = "Weak";
					else if (ranki == 1)
						ranks = "Fair";
					else if (ranki == 2)
						ranks = "Good";
					else if (ranki == 3)
						ranks = "Strong";
					else
						ranks = "Very strong";
					informationLbl.setText("Password Strength : " + ranks);
				}
			}
			@Override
			public void keyPressed(KeyEvent ke) {

			}
		});
		passwordTxt.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent ke) {
				if (!passwordTxt.getText().equals(passwordVerifiedTxt.getText())) {
					informationLbl.setText("Password was not match");
				} else {
					Zxcvbn zx = new Zxcvbn();
					Strength st = zx.measure(passwordTxt.getText());
					int ranki = st.getScore();
					String ranks;
					if (ranki == 0)
						ranks = "Weak";
					else if (ranki == 1)
						ranks = "Fair";
					else if (ranki == 2)
						ranks = "Good";
					else if (ranki == 3)
						ranks = "Strong";
					else
						ranks = "Very strong";
					informationLbl.setText("Password Strength : " + ranks);
				}
			}
			@Override
			public void keyPressed(KeyEvent ke) {

			}
		});
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		OKBtn = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		OKBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				if (nameTxt.getText() != "" && passwordTxt.getText() != "" && passwordVerifiedTxt.getText() != ""
						&& passwordTxt.getText().equals(passwordVerifiedTxt.getText())) {
					PasswordManager app = PasswordManager.getApp();
					Database db = new Database(nameTxt.getText(), passwordTxt.getText());
					app.getCR().addDatabase(db);
				}

			}

			@Override
			public void mouseDown(MouseEvent arg0) {

			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {

			}
		});

		/*
		 * OKBtn.addListener(SWT.Selection, new Listener() { public void
		 * handleEvent(Event e) { switch (e.type) { case SWT.Selection:
		 * System.out.println("Button pressed"+nameTxt.getText()); if
		 * (nameTxt.getText() != "" && passwordTxt.getText() != "" &&
		 * passwordVerifiedTxt.getText() != "" &&
		 * passwordTxt.getText().equals(passwordVerifiedTxt.getText())) {
		 * PasswordManager app = PasswordManager.getApp(); Database db = new
		 * Database(nameTxt.getText(), passwordTxt.getText());
		 * app.getCR().addDatabase(db); } break; } } });
		 */

		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("NewDatabaseDialog.Title"));
	}
}
