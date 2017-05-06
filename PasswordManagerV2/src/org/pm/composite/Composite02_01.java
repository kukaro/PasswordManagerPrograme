package org.pm.composite;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.pm.action.LoadDatabaseAction;
import org.pm.action.NewDatabaseAction;
import org.pm.main.PasswordManager;
import org.pm.util.PmColor;

public class Composite02_01 extends Composite {
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
		width = PasswordManager.width * 13 / 16 / 2;
		height = PasswordManager.height * 9 / 12 / 2;
		// width = PasswordManager.width*12/16/3;
		// height = PasswordManager.height*9/12/2;
		posX = PasswordManager.width * 1 / 16;
		posY = PasswordManager.height * 2 / 12 + Composite01_01.height;
	}

	/**
	 * Creater Composite02_01: Set personal
	 * property(ex:width,height,posX,posY,image resource)
	 * 
	 * @param parent
	 * @param style
	 */
	public Composite02_01(Composite parent, int style) {
		super(parent, style);
		setBackground(PmColor.COMP4);
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent pe) {
				gc = pe.gc;
				img = new Image(pe.display, "src/org/pm/resource/ExportData.png");
				Image imgScale = new Image(pe.display, img.getImageData().scaledTo(width * 3 / 4, height * 3 / 4));
				gc.drawImage(imgScale, width / 8, 0);
			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent me) {
				LoadDatabaseAction.Run();
			}

			@Override
			public void mouseDown(MouseEvent arg0) {

			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {

			}
		});
	}

}
