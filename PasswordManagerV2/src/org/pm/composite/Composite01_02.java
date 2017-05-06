package org.pm.composite;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.pm.main.PasswordManager;
import org.pm.util.PmColor;

public class Composite01_02 extends Composite {
	/*
	 * Public Property
	 */
	public static int width;
	public static int height;
	public static int posX;
	public static int posY;
	
	/*
	 * Instance Field
	 */
	private GC gc;
	private Image img;
	
	/*
	 * Initialize Block
	 */
	{
		width = PasswordManager.width*13/16/2;
		height = PasswordManager.height*9/12/2;
		posX = PasswordManager.width*2/16+Composite01_01.width;
		posY = PasswordManager.height/12;
		
	}
	/**
	 * Creater Composite01_02:
	 * Set personal property(ex: width,height,posX,posY,image resource)
	 * 
	 * @param parent
	 * @param style
	 */
	public Composite01_02(Composite parent, int style) {
		super(parent, style);
		setBackground(PmColor.COMP3);
		setLayout(null);
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent pe) {
				gc = pe.gc;
				img = new Image(pe.display, "src/org/pm/resource/OpenDB.png");
				Image imgScale = new Image(pe.display, img.getImageData().scaledTo(width*3/4, height*3/4));
				gc.drawImage(imgScale, width/8, 0);
			}
		});
	}
	
}
