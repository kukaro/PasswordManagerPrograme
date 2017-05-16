package org.pm.composite.PasswordVerify;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CompositePasswordVerifyTitle extends Composite {
	/*
	 * GUI Field
	 */
	private Label titleLbl;

	/*
	 * Public Property
	 */
	public static CompositePasswordVerifyTitle self;
	public static int width;
	public static int height;
	public static int posX;
	public static int posY;

	/*
	 * Instance Field
	 */

	/*
	 * Initialize Block
	 */
	{
		width = CompositePasswordVerify.width;
		height = CompositePasswordVerify.height * 1 / 10;
		posX = 0;
		posY = 0;
	}

	public CompositePasswordVerifyTitle(Composite parent, int style) {
		super(parent, style);
		self = this;
		titleLbl = new Label(this, SWT.CENTER);
		titleLbl.setBounds(0, 7, width, height);
		titleLbl.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_TRANSPARENT));
		titleLbl.setText(Messages.getString("CompositePasswordVerify.title"));
		titleLbl.setFont(new Font(this.getDisplay(), "Arial", 18, SWT.BOLD));
	}
}
