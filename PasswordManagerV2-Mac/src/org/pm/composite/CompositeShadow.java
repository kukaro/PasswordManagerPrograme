
package org.pm.composite;

import org.eclipse.swt.widgets.Composite;
import org.pm.main.PasswordManager;
import org.pm.util.PmColor;

public final class CompositeShadow extends Composite {
	/*
	 * Public Property
	 */
	public static int width;
	public static int height;
	
	/*
	 * Initialize Block
	 */
	{
		width = PasswordManager.width*13/16/2;
		height = PasswordManager.height*9/12/2;
	}
	/**
	 * Creater Composite01_01:
	 * Set personal property(ex: width,height,posX,posY,image resource)
	 * @param parent
	 * @param style
	 */
	public CompositeShadow(Composite parent, int style) {
		super(parent, style);
		setBackground(PmColor.BLACK);
	}
}
