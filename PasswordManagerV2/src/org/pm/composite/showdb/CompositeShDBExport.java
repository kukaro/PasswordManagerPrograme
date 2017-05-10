package org.pm.composite.showdb;

import java.io.InputStream;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.pm.database.Database;
import org.pm.util.CSVWrite;

public class CompositeShDBExport extends Composite {

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
	private CSVWrite csvwriter;

	/*
	 * Initialize Block
	 */
	{
		width = CompositeShowDatabase.width * 1 / 6;
		height = CompositeShowDatabase.height * 1 / 6;
		posX = CompositeShowDatabase.width * 2 / 10 + CompositeShowDatabase.width * 2 / 30;
		posY = CompositeShowDatabase.height * 3 / 4;
		is = getClass().getResourceAsStream("/org/pm/resource/export.png");
		id = new ImageData(is).scaledTo(width, height);
	}

	/**
	 * Creator CompositeNDBExport
	 * 
	 * @param parent
	 * @param style
	 */
	CompositeShDBExport(Composite parent, int style) {
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
				Database temp = CompositeShowDatabase.getDB();
				csvwriter = new CSVWrite(temp.getName()+".csv");
				ArrayList<String[]> data = new ArrayList<String[]>();
				data.add(new String[]{temp.getName(),temp.getPassword()});
				for(int i=0;i<temp.Size();i++){
					data.add(new String[]{temp.getRecord(i).getID(),temp.getRecord(i).getPassword(),temp.getRecord(i).getURL()});
				}
				csvwriter.writeCsv(data);
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
