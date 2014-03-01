package businesslayer;

import datalyaer.DbAdapter;

public class Manipulator {
	public static boolean insert(String word, String pron, String meaning, String synon, String opposit, String sample, long date) {
		//if every thing is ok:
		try {
		DbAdapter.insertOp(word, pron, meaning, synon, opposit, sample, date);
		return true;
		} catch (Exception e) {
			e.printStackTrace();//Should get removed before the deployment
			return false;
		}
	}
}
