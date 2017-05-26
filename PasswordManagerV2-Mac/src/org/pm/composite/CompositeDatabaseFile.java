package org.pm.composite;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.pm.composite.showdb.CompositeShowDatabase;
import org.pm.database.Database;
import org.pm.main.PasswordManager;
import org.pm.util.PmColor;

public class CompositeDatabaseFile extends Composite {
	/*
	 * Public Property
	 */
	public CompositeDatabaseFile compoDBFile;
	public int width;
	public int height;
	public int posX;
	public int posY;

	/*
	 * Instance Field
	 */
	private GC gc;
	private Image img;
	private InputStream isFileNot;
	private InputStream isFileOpen;
	private InputStream isBackGround;
	private ImageData idBackGround;
	private ImageData idFileNot;
	private ImageData idFileOpen;
	private Database db;
	private boolean saved;
	private Label nameLbl;
	private Label recordLbl;
	private Label savedLbl;

	/*
	 * Initialize Block
	 */
	{
		compoDBFile = this;
		width = CompositeSelectDatabase.width * 8 / 10;
		height = CompositeSelectDatabase.height * 1 / 8;
		posX = CompositeSelectDatabase.width * 1 / 10;
		posY = CompositeSelectDatabase.height * 1 / 8;
		isFileNot = getClass().getResourceAsStream("/org/pm/resource/FILE_NOT.png");
		isFileOpen = getClass().getResourceAsStream("/org/pm/resource/FILE_OPEN444.png");
		isBackGround = getClass().getResourceAsStream("/org/pm/resource/DarkBlueRectRound.png");
		idFileNot = new ImageData(isFileNot).scaledTo(width / 5, height);
		idFileOpen = new ImageData(isFileOpen).scaledTo(width / 5, height);
		idBackGround = new ImageData(isBackGround).scaledTo(width, height);
		saved = false;
	}

	/**
	 * Creater CompositeDatabaseFile: Set personal property(ex:
	 * width,height,posX,posY,image resource) Has three Parameter
	 * 
	 * @param parent
	 * @param style
	 * @param db
	 */
	public CompositeDatabaseFile(Composite parent, int style, Database db) {
		super(parent, style);
		this.db = db;
		setLayout(null);
		setBackground(this.getDisplay().getSystemColor(SWT.COLOR_TRANSPARENT));
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent pe) {
				gc = pe.gc;
				img = new Image(pe.display, idBackGround);
				gc.drawImage(img, 0, 0);
				img = new Image(pe.display, idFileOpen);
				gc.drawImage(img, 0, 0);
			}
		});
		nameLbl = new Label(this, SWT.NONE);
		nameLbl.setBounds(width * 1 / 5, 0, width * 4 / 5, height / 2);
		nameLbl.setText(Messages.getString("CompositeDatabaseFile.name") + ":" + db.getName());
		nameLbl.setForeground(PmColor.WHITE);
		nameLbl.setFont(new Font(this.getDisplay(), "Arial", 14, SWT.BOLD));
		nameLbl.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				PasswordManager.getCompositeShowDatabase().setVisible(!PasswordManager.getCompositeShowDatabase().getVisible());
				CompositeShowDatabase.LoadDB(db);
				PasswordManager.getCompositeShowDatabaseShadow().setVisible(!PasswordManager.getCompositeShowDatabaseShadow().getVisible());
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {		
			}
		});
		recordLbl = new Label(this, SWT.NONE);
		recordLbl.setBounds(width * 1 / 5, height / 2, width * 2 / 5, height / 2);
		recordLbl.setText(Messages.getString("CompositeDatabaseFile.record") + ":" + db.Size());
		recordLbl.setForeground(PmColor.WHITE);
		recordLbl.setFont(new Font(this.getDisplay(), "Arial", 14, SWT.BOLD));
		recordLbl.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				PasswordManager.getCompositeShowDatabase().setVisible(!PasswordManager.getCompositeShowDatabase().getVisible());
				CompositeShowDatabase.LoadDB(db);
				PasswordManager.getCompositeShowDatabaseShadow().setVisible(!PasswordManager.getCompositeShowDatabaseShadow().getVisible());
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {		
			}
		});
		savedLbl = new Label(this, SWT.NONE);
		savedLbl.setBounds(width * 3 / 5, height / 2, width * 2 / 5, height / 2);
		savedLbl.setText(Messages.getString("CompositeDatabaseFile.saved") + ":" + saved);
		savedLbl.setForeground(PmColor.WHITE);
		savedLbl.setVisible(false);
		savedLbl.setFont(new Font(this.getDisplay(), "Arial", 14, SWT.BOLD));
		savedLbl.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				PasswordManager.getCompositeShowDatabase().setVisible(!PasswordManager.getCompositeShowDatabase().getVisible());
				CompositeShowDatabase.LoadDB(db);
				PasswordManager.getCompositeShowDatabaseShadow().setVisible(!PasswordManager.getCompositeShowDatabaseShadow().getVisible());
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {		
			}
		});
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				PasswordManager.getCompositeShowDatabase().setVisible(!PasswordManager.getCompositeShowDatabase().getVisible());
				CompositeShowDatabase.LoadDB(db);
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

	/**
	 * Creater CompositeDatabaseFile: Set personal property(ex:
	 * width,height,posX,posY,image resource) Has four Parameter
	 * 
	 * @param parent
	 * @param style
	 * @param db
	 * @param posX
	 * @param posY
	 */
	public CompositeDatabaseFile(Composite parent, int style, Database db, int posX, int posY) {
		this(parent, style, db);
		this.posX = posX;
		this.posY = posY;
	}
	public void setAllText(){
		nameLbl.setText(Messages.getString("CompositeDatabaseFile.name") + ":" + db.getName());
		recordLbl.setText(Messages.getString("CompositeDatabaseFile.record") + ":" + db.Size());
	}
}
