package accountpackage;

import javax.swing.*;

public class Driver extends JFrame {
	protected final static double INTEREST = 5.75;
	protected final static int SIZE = 25;
	public static int autoId = 1100;
	public static Record[] recordArray = new Record[SIZE];

	public Driver() {
		new startGui(recordArray);
	}

	public static int howManyAccounts() {
		int n = 0;
		for (int i = 0; i <= 24; i++) {
			if (recordArray[i] != null) {
				n++;
			}
		}
		return n;
	}

	public static void main(String[] args) {
		new Driver();

	}// end main
}// end Driver