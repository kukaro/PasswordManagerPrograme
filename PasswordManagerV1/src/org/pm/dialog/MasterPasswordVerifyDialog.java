package org.pm.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.pm.composite.CompositeLeft;
import org.pm.util.SHA256;

public class MasterPasswordVerifyDialog extends Dialog {
	private Label informationLbl;
	private Text masterPasswordTxt;
	private Button YesBtn;
	private String password;
	private String mp;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public MasterPasswordVerifyDialog(Shell parentShell,String password) {
		super(parentShell);
		this.password = password;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		parent.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		container.setLayout(null);
		container.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		informationLbl = new Label(container, SWT.NONE);
		informationLbl.setText(Messages.getString("MasterPasswordVerifyDialog.Information"));
		informationLbl.setBounds(75, 25, 200, 20);
		informationLbl.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		masterPasswordTxt = new Text(container, SWT.PASSWORD);
		masterPasswordTxt.setBounds(50, 50, 200, 20);
		masterPasswordTxt.setBackground(new Color(null, 0x33, 0x66, 0xFF));
		masterPasswordTxt.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				mp = masterPasswordTxt.getText();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		YesBtn = createButton(parent, IDialogConstants.YES_ID, Messages.getString("MasterPasswordVerifyDialog.YesBtn.Label"), true);
		YesBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					if(password.equals(SHA256.encrypt(mp)))
						CompositeLeft.getCL().setMatch(true);
					else
						CompositeLeft.getCL().setMatch(false);
					CompositeLeft.MasterPasswordVerifyDialogClose();
					break;
				}
			}
		});
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(300, 200);
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("MasterPasswordVerifyDialog.Title"));
	}
	
}
