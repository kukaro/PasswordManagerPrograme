package org.pm.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.pm.database.Database;
import org.pm.database.Record;

public class AddRecordDialog extends Dialog {
	/*
	 * Instance Field
	 */
	private Database db;
	/*
	 * GUI Field
	 */
	private Label nameLbl;
	private Label passwordLbl;
	private Label URLLbl;
	private Text nameTxt;
	private Text passwordTxt;
	private Text URLTxt;
	private Button OKBtn;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public AddRecordDialog(Shell parentShell, Database db) {
		super(parentShell);
		this.db = db;
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
		nameLbl.setText(Messages.getString("AddRecordDialog.name") + " :");
		nameLbl.setBounds(50, 50, 100, 20);
		nameLbl.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		passwordLbl = new Label(container, SWT.NONE);
		passwordLbl.setText(Messages.getString("AddRecordDialog.Password") + " :");
		passwordLbl.setBounds(50, 100, 100, 20);
		passwordLbl.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		URLLbl = new Label(container, SWT.NONE);
		URLLbl.setText(Messages.getString("AddRecordDialog.URL") + " :");
		URLLbl.setBounds(50, 150, 100, 20);
		URLLbl.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		nameTxt = new Text(container, SWT.NONE);
		nameTxt.setBounds(150, 50, 250, 20);
		nameTxt.setBackground(new Color(null, 0x33, 0x66, 0xFF));
		passwordTxt = new Text(container, SWT.NONE);
		passwordTxt.setBounds(150, 100, 250, 20);
		passwordTxt.setBackground(new Color(null, 0x33, 0x66, 0xFF));
		URLTxt = new Text(container, SWT.NONE);
		URLTxt.setBounds(150, 150, 250, 20);
		URLTxt.setBackground(new Color(null, 0x33, 0x66, 0xFF));
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
		OKBtn = createButton(parent, IDialogConstants.YES_ID, Messages.getString("AddRecordDialog.OKBtn.Label"), true);
		OKBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					if (nameTxt.getText() != "" && passwordTxt.getText() != "" && URLTxt.getText() != "")
						db.AddRecord(new Record(nameTxt.getText(), passwordTxt.getText(), URLTxt.getText()));
					TableItem item = new TableItem(DatabaseDialog.getTable(), SWT.NONE);
					item.setText(new String[] { nameTxt.getText(), passwordTxt.getText(), URLTxt.getText() });
					DatabaseDialog.AddRecordDialogClose();
					break;
				}
			}
		});
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
		shell.setText(Messages.getString("AddRecordDialog.Title"));
	}
}
