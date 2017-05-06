package org.pm.dialog;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.pm.database.Database;
import org.eclipse.swt.layout.GridData;

public class DatabaseDialog extends Dialog {
	/*
	 * Static Field
	 */
	private static AddRecordDialog ard;
	private static Table table;
	private static BufferedWriter writer;
	/*
	 * Instance Field
	 */
	private Database db;
	/*
	 * GUI Field
	 */
	private Button AddRecordBtn;
	private Button SaveRecordBtn;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public DatabaseDialog(Shell parentShell, Database db) {
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
		container.setBackground(new Color(null, 0x33, 0x99, 0xFF));
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableLayout tl = new TableLayout();
		tl.addColumnData(new ColumnWeightData(25, 75, true));
		tl.addColumnData(new ColumnWeightData(25, 75, true));
		tl.addColumnData(new ColumnWeightData(50, 75, true));
		table.setLayout(tl);

		TableColumn column1 = new TableColumn(table, SWT.CENTER);
		column1.setText("ID");
		TableColumn column2 = new TableColumn(table, SWT.CENTER);
		column2.setText("Password");
		TableColumn column3 = new TableColumn(table, SWT.CENTER);
		column3.setText("URL");

		for (int i = 0; i < db.Size(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(
					new String[] { db.getRecord(i).getID(), db.getRecord(i).getPassword(), db.getRecord(i).getURL() });
		}
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
		SaveRecordBtn = createButton(parent, IDialogConstants.YES_ID,
				Messages.getString("DatabaseDialog.SaveRecordBtn.Label"), false);
		SaveRecordBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					try {
						writer = new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(db.getName()+".csv"), "MS949"));
						writer.write(db.getName()+","+db.getPassword()+","+db.Size()+"\n");
						for(int i=0;i<db.Size();i++){
							writer.write(db.getRecord(i).getID()+","+db.getRecord(i).getPassword()+","+db.getRecord(i).getURL()+"\n");
						}
						writer.close();
					} catch (UnsupportedEncodingException | FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});
		AddRecordBtn = createButton(parent, IDialogConstants.YES_ID,
				Messages.getString("DatabaseDialog.AddRecordBtn.Label"), false);
		AddRecordBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					ard = new AddRecordDialog(parent.getShell(), db);
					ard.open();
					break;
				}
			}
		});
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
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
		shell.setText(Messages.getString("DatabaseDialog.Title") + " : " + db.getName());
	}

	static void AddRecordDialogClose() {
		ard.close();
	}

	static Table getTable() {
		return table;
	}
}
