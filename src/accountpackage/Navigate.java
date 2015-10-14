package accountpackage;

import javax.swing.JOptionPane;

public class Navigate {

	private Record[] recA;

	public Navigate(Record[] rec, String command) {
		recA = rec;

		if (command.equals("surname")) {
			findBySurname();
		} else if (command.equals("acc")) {
			findByAccount();
		}
	}

	public void findByAccount() {
		System.out.println("find by account pressed");
		String acc = JOptionPane
				.showInputDialog("Enter Account no. of account you want to seer");
		boolean foundMatch = false;
		int position = -1;

		for (int i = 0; i < 25; i++) {
			if (recA[i] != null) {
				if (recA[i].getAccountNumber().equalsIgnoreCase(acc)) {
					foundMatch = true;
					position = i;
				}
			}
		}
		if (foundMatch) {
			startGui.displayDetails(position);
		} else {
			JOptionPane.showMessageDialog(null, "No Account with the number "
					+ acc);
		}

	}

	public void findBySurname() {
		System.out.println("find by surname pressed");
		String sname = JOptionPane
				.showInputDialog("Enter Surname of Account Holder");
		boolean foundMatch = false;
		int position = 0;
		int x=-1;

		for (int i = 0; i < 25; i++) {
			
			if (recA[i]!= null) {
				
				System.out.println("found "+recA[i].getSurname());
				System.out.println("looking for "+sname);
				
				if(sname.equalsIgnoreCase(recA[i].getSurname().trim())){
					foundMatch=true;
					position=i;
				}
			}
		}
		
		if(foundMatch) {
			startGui.displayDetails(position);
		} else {
			JOptionPane.showMessageDialog(null,
					"No Account holder with the name " + sname);
			
		}

	}

}