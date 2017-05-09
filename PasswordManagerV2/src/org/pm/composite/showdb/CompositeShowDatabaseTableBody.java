package org.pm.composite.showdb;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.pm.database.Database;

public class CompositeShowDatabaseTableBody extends ScrolledComposite {
	/*
	 * Public Property
	 */
	public static int width;
	public static int height;
	public static int posX;
	public static int posY;
	public static ArrayList<CompositeShowDatabaseTableRow> compoDBTRArr;
	public static CompositeShowDatabaseTableBody compoShowDBTableBody;

	/*
	 * Static Field
	 */
	private static Database db;

	/*
	 * Initialize Block
	 */
	{
		width = CompositeShowDatabase.width * 12 / 15;
		height = CompositeShowDatabase.height * 7 / 15;
		posX = CompositeShowDatabase.width * 3 / 15 / 2;
		posY = CompositeShowDatabase.height * 4 / 15;
	}

	public CompositeShowDatabaseTableBody(Composite parent, int style) {
		super(parent, style);
		compoShowDBTableBody = this;
		setExpandHorizontal(true);
		setExpandVertical(true);
		setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_TRANSPARENT));
		Repose();
	}

	public static void LoadDB(Database db) {
		if (CompositeShowDatabaseTableBody.db != null)
			if (CompositeShowDatabaseTableBody.db.Size() != 0)
				DisposeArr();
		CompositeShowDatabaseTableBody.db = db;
		compoDBTRArr = new ArrayList<CompositeShowDatabaseTableRow>();
		for (int i = 0; i < db.Size(); i++) {
			compoDBTRArr.add(new CompositeShowDatabaseTableRow(CompositeShowDatabaseTableBody.getSelf(), SWT.NONE,
					db.getRecord(i)));
		}
		Repose();
	}

	public static CompositeShowDatabaseTableBody getSelf() {
		return compoShowDBTableBody;
	}

	public static void DisposeArr() {
		for (int i = 0; i < compoDBTRArr.size(); i++) {
			compoDBTRArr.get(i).dispose();
		}
	}

	private static void Repose() {
		if (db != null)
			for (int i = 0; i < db.Size(); i++) {
				compoDBTRArr.get(i).setBounds(compoDBTRArr.get(i).posX,
						compoDBTRArr.get(i).posY + i * compoDBTRArr.get(i).height, compoDBTRArr.get(i).width,
						compoDBTRArr.get(i).height);
			}
	}
}
