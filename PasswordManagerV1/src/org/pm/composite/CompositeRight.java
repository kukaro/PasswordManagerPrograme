package org.pm.composite;

import java.util.ArrayList;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.pm.database.Database;
import org.pm.dialog.DatabaseDialog;

public class CompositeRight extends Composite {
	private static CompositeRight cr;
	private Image img1;
	private Image img2;
	private int count = 0;
	private GC gc;
	private ArrayList<Database> databaseArr;

	public CompositeRight(Composite parent, int style) {
		super(parent, style);
		cr = this;
		setBackground(new Color(null, 0x33, 0x99, 0xFF));
		databaseArr = new ArrayList<Database>();
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent pe) {
				int x = 0;
				int y = 50;
				gc = pe.gc;
				img1 = new Image(pe.display, "src/org/pm/resource/box_sm150.png");
				img2 = new Image(pe.display, "src/org/pm/resource/box_empty150.png");
				for (int i = 0; i < 3; i++) {
					if (i < count) {
						gc.drawImage(img1, x, y);
						gc.drawText(databaseArr.get(i).getName(), x + 75-databaseArr.get(i).getName().length(), y + 50);
					} else
						gc.drawImage(img2, x, y);
					y += 150;
				}
				x += 200;
				y = 50;
				for (int i = 3; i < 6; i++) {
					if (i < count){
						gc.drawImage(img1, x, y);
						gc.drawText(databaseArr.get(i).getName(), x + 75-databaseArr.get(i).getName().length(), y + 50);
					}	
					else
						gc.drawImage(img2, x, y);
					y += 150;
				}
				x += 200;
				y = 50;
				for (int i = 6; i < 9; i++) {
					if (i < count){
						gc.drawImage(img1, x, y);
						gc.drawText(databaseArr.get(i).getName(), x + 75-databaseArr.get(i).getName().length(), y + 50);
					}	
					else
						gc.drawImage(img2, x, y);
					y += 150;
				}
			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent me) {
				int x = 0;
				int y = 50;
				for (int i = 0; i < 3; i++) {
					if (i < count && me.x >= x && me.x < x + 200 && me.y >= y && me.y < y + 150) {
						DatabaseDialog dd = new DatabaseDialog(parent.getShell(), databaseArr.get(i));
						dd.open();
					}
					y += 150;
				}
				x += 200;
				y = 50;
				for (int i = 3; i < 6; i++) {
					if (i < count && me.x >= x && me.x < x + 200 && me.y >= y && me.y < y + 150) {
						DatabaseDialog dd = new DatabaseDialog(parent.getShell(), databaseArr.get(i));
						dd.open();
					}
					y += 150;
				}
				x += 200;
				y = 50;
				for (int i = 6; i < 9; i++) {
					if (i < count && me.x >= x && me.x < x + 200 && me.y >= y && me.y < y + 150) {
						DatabaseDialog dd = new DatabaseDialog(parent.getShell(), databaseArr.get(i));
						dd.open();
					}
					y += 150;
				}
			}

			@Override
			public void mouseDown(MouseEvent me) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
	}

	public GC getGC() {
		return gc;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void addDatabase(Database db) {
		databaseArr.add(db);
		count++;
	}
	static CompositeRight getCR(){
		return cr;
	}
}
