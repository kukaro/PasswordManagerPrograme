package org.pm.composite.showdb;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.pm.action.NewDatabaseAction;
import org.pm.composite.CompositeShadow;
import org.pm.main.PasswordManager;

public class CompositeShDBClose extends Composite {

	/*
	 * Public Property
	 */
	public static int width;
	public static int height;
	public static int posX;
	public static int posY;

	/*
	 * Static Field
	 */
	private static InputStream is;
	private static ImageData id;

	/*
	 * Instance Field
	 */
	private GC gc;
	private Image img;

	/*
	 * Initialize Block
	 */
	{
		width = CompositeShowDatabase.width * 1 / 6;
		height = CompositeShowDatabase.height * 1 / 6;
		posX = CompositeShowDatabase.width * 8 / 10 - CompositeShowDatabase.width * 1 / 30;
		posY = CompositeShowDatabase.height * 3 / 4;
		is = getClass().getResourceAsStream("/org/pm/resource/Xsign.png");
		id = new ImageData(is).scaledTo(width, height);
	}

	/**
	 * Creator CompositeShDBClose
	 * 
	 * @param parent
	 * @param style
	 */
	CompositeShDBClose(Composite parent, int style) {
		super(parent, style);
		setBackground(this.getDisplay().getSystemColor(SWT.COLOR_TRANSPARENT));
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent pe) {
				gc = pe.gc;
				img = new Image(pe.display, id);
				gc.drawImage(img, 0, 0);
			}
		});
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent me) {
				//CompositeShowDatabaseTableBody.DisposeArr();
				PasswordManager.getCompositeShowDatabase().setVisible(!PasswordManager.getCompositeShowDatabase().getVisible());
				PasswordManager.getCompositeShowDatabaseShadow().setVisible(!PasswordManager.getCompositeShowDatabaseShadow().getVisible());
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
