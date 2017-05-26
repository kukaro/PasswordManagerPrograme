package org.pm.composite;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_NAME = "org.pm.composite.messages";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	private static final ResourceBundle RESOURCE_BUNDLE_KR = ResourceBundle.getBundle(BUNDLE_NAME+"_kr");

	private static boolean KoreanMode;

	{
		KoreanMode = false;
	}

	private Messages() {

	}

	public static String getString(String key) {
		try {
			if(KoreanMode==true) return RESOURCE_BUNDLE_KR.getString(key);
			else return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	public static void SwapMode(){
		KoreanMode = !KoreanMode;
	}
}
