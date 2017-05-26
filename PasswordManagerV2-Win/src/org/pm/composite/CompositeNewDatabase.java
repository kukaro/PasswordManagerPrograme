package org.pm.composite;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.pm.composite.Messages;
import org.pm.main.PasswordManager;
import org.pm.util.PmColor;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

public class CompositeNewDatabase extends Composite {
	/*
	 * GUI Field
	 */
	private Label newDBLbl;
	private Label nameLbl;
	private Label passwordLbl;
	private Label passwordVerifiedLbl;
	private Label stateLbl;
	private CompositeNDBClose ndbCloseComp;
	private CompositeNDBSubmit ndbSubmitComp;
	private Text nameTxt;
	private Text passwordTxt;
	private Text passwordVerifiedTxt;
	
	/*
	 * Public Property
	 */
	public static CompositeNewDatabase compoNewDB;
	public static int width;
	public static int height;
	public static int posX;
	public static int posY;

	/*
	 * Static Field
	 */
	private static Zxcvbn zx;
	private static Strength st;
	private String ranks;
	private int ranki;
	
	/*
	 * Instance Field
	 */
	private GC gc;
	private Image imgTitle;
	private Image imgScaleOKNO;
	private boolean passwordMatch;
	private InputStream isOK;
	private InputStream isNO;
	private InputStream isTitle;
	private ImageData idOK;
	private ImageData idNO;
	private ImageData idTitle;
	

	/*
	 * Initialize Block
	 */
	{
		width = PasswordManager.width * 3 / 5;
		height = PasswordManager.height * 3 / 5;
		posX = PasswordManager.width / 5;
		posY = PasswordManager.height / 5;
		zx = new Zxcvbn();
		isOK = getClass().getResourceAsStream("/org/pm/resource/ok.png");
		isNO = getClass().getResourceAsStream("/org/pm/resource/no.png");
		isTitle = getClass().getResourceAsStream("/org/pm/resource/NewDB.png");
		idOK = new ImageData(isOK).scaledTo(width * 1 / 6, height * 1 / 6);
		idNO = new ImageData(isNO).scaledTo(width * 1 / 6, height * 1 / 6);
		idTitle = new ImageData(isTitle).scaledTo(width * 2 / 5, height * 2 / 5);;
	}

	/**
	 * Creater CompositeNewDatabase: Set personal property(ex:
	 * width,height,posX,posY,image resource)
	 * 
	 * @param parent
	 * @param style
	 */
	public CompositeNewDatabase(Composite parent, int style) {
		super(parent, style);
		setBackground(PmColor.GLAY);
		setLayout(null);
		compoNewDB = this;
		newDBLbl = new Label(this, SWT.LEFT);
		newDBLbl.setText(Messages.getString("NewDatabaseComposite.title"));
		newDBLbl.setBounds(width * 2 / 5 + 20, 20, 150, 20);
		newDBLbl.setFont(new Font(this.getDisplay(), "Arial", 16, SWT.BOLD));
		newDBLbl.setBackground(PmColor.GLAY);
		nameLbl = new Label(this, SWT.CENTER);
		nameLbl.setText(Messages.getString("NewDatabaseComposite.name"));
		nameLbl.setBounds(50, height * 2 / 5, 100, 20);
		nameLbl.setBackground(PmColor.COMP3);
		passwordLbl = new Label(this, SWT.CENTER);
		passwordLbl.setText(Messages.getString("NewDatabaseComposite.Password"));
		passwordLbl.setBounds(50, height * 2 / 5 + 20 + height * 2 / 5 * 2 / 10, 100, 20);
		passwordLbl.setBackground(PmColor.COMP3);
		passwordVerifiedLbl = new Label(this, SWT.CENTER);
		passwordVerifiedLbl.setText(Messages.getString("NewDatabaseComposite.Password.Verify"));
		passwordVerifiedLbl.setBounds(50, height * 2 / 5 + 40 + height * 2 / 5 * 4 / 10, 100, 20);
		passwordVerifiedLbl.setBackground(PmColor.COMP3);
		stateLbl = new Label(this, SWT.CENTER);
		stateLbl.setBounds(width * 2 / 10, height * 3 / 4, width * 2 / 5, height * 1 / 5);
		stateLbl.setText("\nInsert Password\nPlease");
		stateLbl.setBackground(PmColor.GLAY);
		ndbSubmitComp = new CompositeNDBSubmit(this, SWT.NONE);
		ndbSubmitComp.setBounds(CompositeNDBSubmit.posX, CompositeNDBSubmit.posY, CompositeNDBSubmit.width, CompositeNDBSubmit.height);
		ndbCloseComp = new CompositeNDBClose(this, SWT.NONE);
		ndbCloseComp.setBounds(CompositeNDBClose.posX, CompositeNDBClose.posY, CompositeNDBClose.width, CompositeNDBClose.height);
		nameTxt = new Text(this, SWT.NONE);
		nameTxt.setBounds(150, height * 2 / 5, 160, 20);
		nameTxt.setBackground(PmColor.COMP1);
		passwordTxt = new Text(this, SWT.PASSWORD);
		passwordTxt.setBounds(150, height * 2 / 5 + 20 + height * 2 / 5 * 2 / 10, 160, 20);
		passwordTxt.setBackground(PmColor.COMP1);
		passwordTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (passwordTxt.getText().equals(passwordVerifiedTxt.getText())) {
					st = zx.measure(passwordTxt.getText());
					ranki = st.getScore();
					passwordMatch = true;
					compoNewDB.redraw();
				} else {
					st = zx.measure(passwordTxt.getText());
					ranki = st.getScore();
					passwordMatch = false;
					compoNewDB.redraw();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		passwordVerifiedTxt = new Text(this, SWT.PASSWORD);
		passwordVerifiedTxt.setBounds(150, height * 2 / 5 + 40 + height * 2 / 5 * 4 / 10, 160, 20);
		passwordVerifiedTxt.setBackground(PmColor.COMP1);
		passwordVerifiedTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (passwordTxt.getText().equals(passwordVerifiedTxt.getText())) {
					st = zx.measure(passwordTxt.getText());
					ranki = st.getScore();
					passwordMatch = true;
					compoNewDB.redraw();
				} else {
					st = zx.measure(passwordTxt.getText());
					ranki = st.getScore();
					passwordMatch = false;
					compoNewDB.redraw();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent pe) {
				gc = pe.gc;
				imgTitle = new Image(pe.display, idTitle);
				if (passwordMatch == true) {
					imgScaleOKNO = new Image(pe.display, idOK);
					if (ranki == 0)
						ranks = "Weak";
					else if (ranki == 1)
						ranks = "Fair";
					else if (ranki == 2)
						ranks = "Good";
					else if (ranki == 3)
						ranks = "Strong";
					else
						ranks = "Very strong";
					if (ranki == 0) {
						stateLbl.setText("\nPassword is Matched\nBut strength is week");
						imgScaleOKNO = new Image(pe.display,idNO);
					} else
						stateLbl.setText("\nPassword is matched\nStrength : " + ranks);
				} else {
					imgScaleOKNO = new Image(pe.display, idNO);
					stateLbl.setText("\nPassword miss matching\nMatching Please");
				}
				gc.drawImage(imgTitle, 50, 0);
				gc.drawImage(imgScaleOKNO, width / 15, height * 3 / 4);
			}
		});
	}

	public void EraseTxt() {
		nameTxt.setText("");
		passwordTxt.setText("");
		passwordVerifiedTxt.setText("");
	}
	
	String getID(){
		return nameTxt.getText();
	}
	
	String getPassword(){
		return passwordTxt.getText();
	}
	
	static CompositeNewDatabase getSelf(){
		return compoNewDB;
	}
	
	String getPassStrength(){
		return ranks;
	}
	
	public void setAllText(){
		newDBLbl.setText(Messages.getString("NewDatabaseComposite.title"));
		nameLbl.setText(Messages.getString("NewDatabaseComposite.name"));
		passwordLbl.setText(Messages.getString("NewDatabaseComposite.Password"));
		passwordVerifiedLbl.setText(Messages.getString("NewDatabaseComposite.Password.Verify"));
	}
}
