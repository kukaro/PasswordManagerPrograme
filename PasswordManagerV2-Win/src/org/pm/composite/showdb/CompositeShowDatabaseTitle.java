package org.pm.composite.showdb;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CompositeShowDatabaseTitle extends Composite {
	/*
	 * GUI Field
	 */
	private Label titleLbl;

	/*
	 * Public Property
	 */
	public static CompositeShowDatabaseTitle self;
	public static int width;
	public static int height;
	public static int posX;
	public static int posY;

	/*
	 * Instance Field
	 */
	private String title;

	/*
	 * Initialize Block
	 */
	{
		width = CompositeShowDatabase.width;
		height = CompositeShowDatabase.height * 1 / 10;
		posX = 0;
		posY = 0;
	}

	public CompositeShowDatabaseTitle(Composite parent, int style, String title) {
		super(parent, style);
		this.title = title;
		self = this;
		titleLbl = new Label(this, SWT.CENTER);
		titleLbl.setBounds(0, 7, width, height);
		titleLbl.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_TRANSPARENT));
		titleLbl.setText(Messages.getString("CompositeShowDatabaseTitle.title") + " : " + title);
		titleLbl.setFont(new Font(this.getDisplay(), "Arial", 18, SWT.BOLD));
	}

	void setText(String title){
		this.title = title;
		titleLbl.setText(Messages.getString("CompositeShowDatabaseTitle.title") + " : " + this.title);
	}
	
	public void setAllText(){
		titleLbl.setText(Messages.getString("CompositeShowDatabaseTitle.title") + " : " + title);
	}
}
