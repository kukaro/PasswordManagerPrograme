package org.pm.composite.showdb;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.pm.util.PmColor;

public class CompositeShowDatabaseTableHead extends Composite {
	/*
	 * GUI Field
	 */
	private Label IDLbl;
	private Label PasswordLbl;
	private Label URLLbl;

	/*
	 * Public Property
	 */
	public static int width;
	public static int height;
	public static int posX;
	public static int posY;
	/*
	 * Initialize Block
	 */
	{
		width = CompositeShowDatabase.width * 12 / 15;
		height = CompositeShowDatabase.height * 1 / 15;
		posX = CompositeShowDatabase.width * 3 / 15 / 2;
		posY = CompositeShowDatabase.height * 3 / 15;
	}

	public CompositeShowDatabaseTableHead(Composite parent, int style) {
		super(parent, style);
		IDLbl = new Label(this, SWT.CENTER);
		IDLbl.setBackground(PmColor.WHITE);
		IDLbl.setBounds(2, 2, width * 1 / 4, height);
		IDLbl.setText(Messages.getString("CompositeShowDatabaseTableHead.ID"));
		PasswordLbl = new Label(this, SWT.CENTER);
		PasswordLbl.setBackground(PmColor.WHITE);
		PasswordLbl.setBounds(2 + width * 1 / 4 + 2, 2, width * 1 / 4, height);
		PasswordLbl.setText(Messages.getString("CompositeShowDatabaseTableHead.Password"));
		URLLbl = new Label(this, SWT.CENTER);
		URLLbl.setBackground(PmColor.WHITE);
		URLLbl.setBounds((2 + width * 1 / 4) * 2 + 2, 2, width * 2 / 4, height);
		URLLbl.setText(Messages.getString("CompositeShowDatabaseTableHead.URL"));
	}

}
