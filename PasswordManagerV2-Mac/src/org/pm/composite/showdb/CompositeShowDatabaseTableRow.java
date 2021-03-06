package org.pm.composite.showdb;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.pm.database.Record;
import org.pm.util.PmColor;

public class CompositeShowDatabaseTableRow extends Composite {
	/*
	 * GUI Field
	 */
	private Text IDTxt;
	private Text PasswordTxt;
	private Text URLTxt;

	/*
	 * Public Property
	 */
	public int width;
	public int height;
	public int posX;
	public int posY;
	public Record r;

	/*
	 * Initialize Block
	 */
	{
		width = CompositeShowDatabase.width * 12 / 15;
		height = CompositeShowDatabase.height * 1 / 15;
		posX = 0;
		posY = 0;
	}

	public CompositeShowDatabaseTableRow(Composite parent, int style, Record r) {
		super(parent, style);
		this.r = r;
		setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_TRANSPARENT));
		IDTxt = new Text(this, SWT.READ_ONLY);
		IDTxt.setBackground(PmColor.WHITE);
		IDTxt.setBounds(2, 2, width * 1 / 4, height);
		IDTxt.setText(r.getID());
		IDTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				r.setID(IDTxt.getText());
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		PasswordTxt = new Text(this, SWT.READ_ONLY);
		PasswordTxt.setBackground(PmColor.WHITE);
		PasswordTxt.setBounds(2 + width * 1 / 4 + 2, 2, width * 1 / 4, height);
		PasswordTxt.setText(r.getPassword());
		PasswordTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				r.setPassword(PasswordTxt.getText());
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		URLTxt = new Text(this, SWT.READ_ONLY);
		URLTxt.setBackground(PmColor.WHITE);
		URLTxt.setBounds((2 + width * 1 / 4) * 2 + 2, 2, width * 2 / 4, height);
		URLTxt.setText(r.getURL());
		URLTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				r.setURL(URLTxt.getText());
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
	}

	public void setPose(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void invEditable() {
		IDTxt.setEditable(!IDTxt.getEditable());
		PasswordTxt.setEditable(!PasswordTxt.getEditable());
		URLTxt.setEditable(!URLTxt.getEditable());
	}
}
