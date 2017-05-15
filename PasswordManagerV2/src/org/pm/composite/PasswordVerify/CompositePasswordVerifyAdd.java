package org.pm.composite.PasswordVerify;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.pm.composite.Composite01_02;
import org.pm.database.Database;
import org.pm.database.Record;
import org.pm.main.PasswordManager;
import org.pm.util.AES256Util;
import org.pm.util.CSVRead;
import org.pm.util.SHA256;

public class CompositePasswordVerifyAdd extends Composite {
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
	private static AES256Util aes;

	/*
	 * Instance Field
	 */
	private GC gc;
	private Image img;

	/*
	 * Initialize Block
	 */
	{
		width = CompositePasswordVerify.width * 1 / 6;
		height = CompositePasswordVerify.height * 1 / 6;
		posX = CompositePasswordVerify.width * 6 / 10;
		posY = CompositePasswordVerify.height * 3 / 4;
		is = getClass().getResourceAsStream("/org/pm/resource/check.png");
		id = new ImageData(is).scaledTo(width, height);
	}

	/**
	 * Creator CompositeNDBSubmit
	 * 
	 * @param parent
	 * @param style
	 */
	CompositePasswordVerifyAdd(Composite parent, int style) {
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
				String pw = CompositePasswordVerify.getPassword();
				try {
					aes = new AES256Util(SHA256.encrypt(pw));
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				CSVRead csvreader = new CSVRead(Composite01_02.getFilepath());
				List<String[]> data = csvreader.readCsv();
				try {

					if (aes.aesEncode(SHA256.encrypt(pw)).equals(data.get(0)[1])) {
						System.out.println(aes.aesEncode(SHA256.encrypt(pw)).equals(data.get(0)[1]));
						csvreader = new CSVRead(Composite01_02.getFilepath());
						Database db = null;
						for (int i = 0; i < data.size(); i++) {
							if (i == 0) {
								db = new Database(aes.aesDecode(data.get(i)[0]), aes.aesDecode(data.get(i)[1]),true);
								System.out.println(data.get(i)[0] + data.get(i)[1]);
							} else {
								db.AddRecord(new Record(aes.aesDecode(data.get(i)[0]), aes.aesDecode(data.get(i)[1]),
										aes.aesDecode(data.get(i)[2])));
								System.out.println(data.get(i)[0] + data.get(i)[1] + data.get(i)[2]);;
							}
						}
						PasswordManager.getDB().add(db);
					} else {
						Composite01_02.setMatch(false);
					}
				} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
						| NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException
						| BadPaddingException e) {
					e.printStackTrace();
				}
				CompositePasswordVerify.CleanTxt();
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
}
