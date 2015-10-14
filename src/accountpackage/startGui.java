package accountpackage;

import java.io.*;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class startGui extends JFrame {
	private static JPanel panel;
	private static JMenuBar menuBar;
	private static JMenu navigate, records, transactions, file, exit;
	private static JMenuItem first, last, previous, next, findBy, listAll,
			findByNo;
	private static JMenuItem create, modify, delete, setOverdraft, setInterest;
	private static JMenuItem deposit, withdraw, calcInterest;
	private static JMenuItem open, save, saveAs, close;
	private static JButton nextItem, prevItem, firstItem, lastItem;

	private static JLabel headL, accIdL, accNoL, sNameL, fNameL, typeL,
			balanceL, overdraftL, interestRateL;

	public static JTextField accIdT;
	public static JTextField accNoT;
	public static JTextField sNameT;
	public static JTextField fNameT;
	public static JTextField typeT;
	public static JTextField balanceT;
	public static JTextField overdraftT;

	public static JTextField interestRateT;

	private static JPanel buttonPanel;

	private static int currentItem = 0;
	
	private static Record[] recA;

	private static Record rec1, rec2, rec3, rec4;

	public startGui(Record[] rec) {
		recA = rec;
		setTitle("Account Record program");
		panel = new JPanel(new MigLayout());
		panel.setBackground(Color.CYAN);

		getContentPane().add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(setUpTheMenu());

	
		if (Driver.howManyAccounts() != 0) {
			currentItem = -1;
			boolean foundOne = false;
			int x = 0;
			while (!foundOne) {
				if (recA[x] != null) {
					currentItem = x;
					foundOne = true;
				} else {
					x++;
				}
			}
			displayDetails(currentItem);
		}
		setSize(450, 300);
		setLocation(360, 200);
		setResizable(true);
		setVisible(true);
	}

	public static void displayDetails(int currentItem) {
		accIdT.setText(String.valueOf(recA[currentItem].getAccountId()));
		accNoT.setText(recA[currentItem].getAccountNumber());
		sNameT.setText(recA[currentItem].getSurname());
		fNameT.setText(recA[currentItem].getFirstName());
		typeT.setText(recA[currentItem].getAccountType());
		balanceT.setText(String.valueOf(recA[currentItem].getBalance()));
		overdraftT.setText(String.valueOf(recA[currentItem].getOverdraft()));
		interestRateT
				.setText(String.valueOf(recA[currentItem].getRate()) + "%");

	}

	public JMenuBar setUpTheMenu() {
		JMenuBar menuBar = new JMenuBar();

		navigate = new JMenu("Navigate");
		navigateMenuListener navigateListen = new navigateMenuListener();
		menuBar.add(navigate);

		records = new JMenu("Records");
		recordsMenuListener recordsListen = new recordsMenuListener();
		menuBar.add(records);

		transactions = new JMenu("Transactions");
		transactionMenuListener transactionListen = new transactionMenuListener();
		menuBar.add(transactions);

		file = new JMenu("File");
		fileMenuListener fileListen = new fileMenuListener();
		menuBar.add(file);

		exit = new JMenu("Exit");
		exitMenuListener exitListen = new exitMenuListener();
		menuBar.add(exit);

		first = new JMenuItem("First");
		first.addActionListener(navigateListen);
		last = new JMenuItem("Last");
		last.addActionListener(navigateListen);
		next = new JMenuItem("Next");
		next.addActionListener(navigateListen);
		previous = new JMenuItem("Previous");
		previous.addActionListener(navigateListen);
		findBy = new JMenuItem("Find By Surname");
		findBy.addActionListener(navigateListen);
		findByNo = new JMenuItem("Find by Acc. No");
		findByNo.addActionListener(navigateListen);
		listAll = new JMenuItem("List All");
		listAll.addActionListener(navigateListen);

		navigate.add(first);
		navigate.add(last);
		navigate.add(next);
		navigate.add(previous);
		navigate.addSeparator();
		navigate.add(findBy);
		navigate.add(findByNo);
		navigate.add(listAll);

		create = new JMenuItem("Create");
		create.addActionListener(recordsListen);
		modify = new JMenuItem("Modify");
		modify.addActionListener(recordsListen);
		delete = new JMenuItem("Delete");
		delete.addActionListener(recordsListen);
		setOverdraft = new JMenuItem("Set Overdraft");
		setOverdraft.addActionListener(recordsListen);
		setInterest = new JMenuItem("Set interest rate");
		setInterest.addActionListener(recordsListen);
		records.add(create);
		records.add(modify);
		records.add(delete);
		records.add(setOverdraft);
		records.add(setInterest);

		deposit = new JMenuItem("Deposit");
		deposit.addActionListener(transactionListen);
		withdraw = new JMenuItem("Withdraw");
		withdraw.addActionListener(transactionListen);
		calcInterest = new JMenuItem("Calculate Interest");
		calcInterest.addActionListener(transactionListen);
		transactions.add(deposit);
		transactions.add(withdraw);
		transactions.add(calcInterest);

		open = new JMenuItem("Open");
		open.addActionListener(fileListen);
		save = new JMenuItem("Save");
		save.addActionListener(fileListen);
		saveAs = new JMenuItem("Save As");
		saveAs.addActionListener(fileListen);
		file.add(open);
		file.add(save);
		file.add(saveAs);

		close = new JMenuItem("Close Program");
		close.addActionListener(exitListen);
		exit.add(close);

		buttonPanel = new JPanel(new GridLayout(1, 4));
		bottomButtonsListener bbl = new bottomButtonsListener();
		nextItem = new JButton("next");
		nextItem.addActionListener(bbl);
		prevItem = new JButton("prev");
		prevItem.addActionListener(bbl);
		firstItem = new JButton("first");
		firstItem.addActionListener(bbl);
		lastItem = new JButton("last");
		lastItem.addActionListener(bbl);
		buttonPanel.add(firstItem);
		buttonPanel.add(prevItem);
		buttonPanel.add(nextItem);
		buttonPanel.add(lastItem);
		add(buttonPanel, BorderLayout.SOUTH);

		headL = new JLabel("Account Record");
		panel.add(headL, "growx, pushx, wrap");

		accIdL = new JLabel("Account ID ");
		panel.add(accIdL);
		accIdT = new JTextField(15);
		accIdT.setEditable(false);
		panel.add(accIdT, "growx, pushx, wrap");

		accNoL = new JLabel("Account Number ");
		panel.add(accNoL);
		accNoT = new JTextField(15);
		accNoT.setEditable(false);
		panel.add(accNoT, "growx, pushx, wrap");

		sNameL = new JLabel("Surname");
		panel.add(sNameL);
		sNameT = new JTextField(15);
		sNameT.setEditable(false);
		panel.add(sNameT, "growx, pushx, wrap");

		fNameL = new JLabel("Forname");
		panel.add(fNameL);
		fNameT = new JTextField(15);
		fNameT.setEditable(false);
		panel.add(fNameT, "growx, pushx, wrap");

		typeL = new JLabel("Acc. Type");
		panel.add(typeL);
		typeT = new JTextField(15);
		typeT.setEditable(false);
		panel.add(typeT, "growx, pushx, wrap");

		interestRateL = new JLabel("Interest % rate");
		panel.add(interestRateL);
		interestRateT = new JTextField(10);
		interestRateT.setEditable(false);
		panel.add(interestRateT, "growx, pushx, wrap");

		balanceL = new JLabel("Balance");
		panel.add(balanceL);
		balanceT = new JTextField(15);
		balanceT.setEditable(false);
		panel.add(balanceT, "growx, pushx, wrap");

		overdraftL = new JLabel("Overdraft");
		panel.add(overdraftL);
		overdraftT = new JTextField(15);
		overdraftT.setEditable(false);
		panel.add(overdraftT, "growx, pushx, wrap");

		return menuBar;
	}// end setUpMenu method

	class navigateMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equalsIgnoreCase("Find By Surname")) {
				String command = "surname";
				new Navigate(recA, command);
			} else if (e.getActionCommand().equalsIgnoreCase("Find by Acc. No")) {
				String command = "acc";
				new Navigate(recA, command);

			} else if (e.getActionCommand().equalsIgnoreCase("List All")) {
				TableView tab = new TableView(recA);
				// listAll();

			} else if (e.getActionCommand().equalsIgnoreCase("First")) {
				int f=-1;
				int i =0;
				
				try{
				while(f<0){
					if(recA[i]!=null){
						f=i;
					}else{
						i++;
					}
				}
				}catch(Exception ex){}
				if (Driver.howManyAccounts() != 0) {
					try {
						
						displayDetails(f);
					} catch (Exception x) {
					}
				}

			} else if (e.getActionCommand().equalsIgnoreCase("Last")) {
				if (Driver.howManyAccounts() != 0) {
					boolean endReached = false;
					try {
						int i = 24;
						do {
							if (recA[i] != null) {
								endReached = true;
							} else {
								i--;
							}
						} while (!endReached);
						displayDetails(i);
					} catch (Exception x) {
					}
				}
			} else if (e.getActionCommand().equalsIgnoreCase("Next")) {
				if (Driver.howManyAccounts() != 0) {
					try {
						boolean nextFound = false;
						do {
							if (currentItem == (Driver.SIZE - 1)) {
								currentItem = 0;
							} else {
								currentItem++;
							}

							if (recA[currentItem] != null) {
								displayDetails(currentItem);
								nextFound = true;
							}
						} while (!nextFound);
					} catch (Exception x) {
					}
				}
			} else if (e.getActionCommand().equalsIgnoreCase("Previous")) {
				if (Driver.howManyAccounts() != 0) {
					boolean previousFound = false;

					try {
						do {
							if (currentItem == 0) {
								currentItem = (Driver.SIZE - 1);
							} else {
								currentItem--;
							}

							if (recA[currentItem] != null) {
								displayDetails(currentItem);
								previousFound = true;
							}
						} while (!previousFound);
					} catch (Exception ex) {
					}
				}
			}
		}// end actionPerformed
	}// end inner class

	class recordsMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Create")) {
				new CreateRecord(recA);

			} else if (e.getActionCommand().equals("Modify")) {
				// modify();

				try {
					Record aRec = recA[currentItem];
					new Modify(aRec);
				} catch (Exception x) {
				}
			} else if (e.getActionCommand().equals("Delete")) {
				delete();
			} else if (e.getActionCommand().equals("Set Overdraft")) {
				if(recA[currentItem].getAccountType().equals("Current")){
					setOverdraft();
				}else{
					JOptionPane.showMessageDialog(null, "OverDraft only applies to Current Accounts");
				}
				
			} else if (e.getActionCommand().equals("Set interest rate")) {
				
				
					if(recA[currentItem].getAccountType().equals("Deposit")){
						setInterestRate();
					}else{
						JOptionPane.showMessageDialog(null, "Interest only applies to Deposit Accounts");
					}
			
			}
		}// end actionPerformed
	}// end inner class

	class transactionMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("Deposit")) {
				deposit();
			} else if (e.getActionCommand().equals("Withdraw")) {
				withdraw();
			} else if (e.getActionCommand().equals("Calculate Interest")) {
				calculateInterest();
			}

		}// end actionPerformed
	}// end inner class

	class fileMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String but = "";
			if (e.getSource() == save) {
				System.out.println("save pressed");
				but = "save";
				try {
					new FileClass(recA, but);
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			} else if (e.getSource() == saveAs) {
				System.out.println("save as pressed");
				but = "saveAs";
				try {
					new FileClass(recA, but);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (e.getSource() == open) {

				System.out.println("open pressed");
				but = "open";
				try {
					new FileClass(null, but);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}// end actionPerformed
	}// end inner class

	class exitMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}// end actionPerformed
	}// end inner class

	class bottomButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("next")) {
				if (Driver.howManyAccounts() != 0) {
					try {
						boolean nextFound = false;
						do {
							if (currentItem == (Driver.SIZE - 1)) {
								currentItem = 0;
							} else {
								currentItem++;
							}

							if (recA[currentItem] != null) {
								displayDetails(currentItem);
								nextFound = true;
							}
						} while (!nextFound);
					} catch (Exception x) {
					}
				}

			} else if (e.getActionCommand().equals("prev")) {
				if (Driver.howManyAccounts() != 0) {

					try {
						boolean previousFound = false;

						do {
							if (currentItem == 0) {
								currentItem = (Driver.SIZE - 1);
							} else {
								currentItem--;
							}

							if (recA[currentItem] != null) {
								displayDetails(currentItem);
								previousFound = true;
							}
						} while (!previousFound);
					} catch (Exception x) {
					}
				}
			} else if (e.getActionCommand().equals("first")) {
				int f=-1;
				int i =0;
				
				try{
				while(f<0){
					if(recA[i]!=null){
						f=i;
					}else{
						i++;
					}
				}
				}catch(Exception ex){}
				if (Driver.howManyAccounts() != 0) {
					
					
					try {
						displayDetails(f);
					} catch (Exception x) {
						System.out.println("nothing there");
					}
					
				}

			} else if (e.getActionCommand().equals("last")) {
				if (Driver.howManyAccounts() != 0) {
					try {
						boolean endReached = false;
						int i = 24;
						do {
							if (recA[i] != null) {
								endReached = true;
							} else {
								i--;
							}
						} while (!endReached);
						displayDetails(i);
					} catch (Exception x) {
					}
				}
			}
		}// end actionPerformed
	}// end inner class

	public void delete() {

		try {
			boolean foundAnother = false;
			String message = recA[currentItem].getFirstName() + " "
					+ recA[currentItem].getSurname() + "'s account, with id : "
					+ recA[currentItem].getAccountId() + " has been deleted";
			JOptionPane.showMessageDialog(null, message);
			recA[currentItem] = null;
			accIdT.setText("");
			accNoT.setText("");
			sNameT.setText("");
			fNameT.setText("");
			typeT.setText("");
			balanceT.setText("");
			overdraftT.setText("");

			while (!foundAnother && Driver.howManyAccounts() != 0) {

				if (currentItem == 24) {
					currentItem = 0;
				} else {
					currentItem++;
				}

				if (recA[currentItem] != null) {
					displayDetails(currentItem);
					foundAnother = true;
				}
			}// end while

		} catch (Exception x) {
		}

	}

	public void setOverdraft() {

		String input = JOptionPane
				.showInputDialog("Enter New Overdraft Amount");
		try {
			double overD = Double.parseDouble(input);
			recA[currentItem].setOverdraft(overD);
			overdraftT.setText(input);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(panel,
					"Valid Numeric Value not entered!!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception x) {
		}

	}

	public void setInterestRate() {
		System.out.println("set interest pressed");
		String newRateString;
		double newRate = 0.0;

		if (recA[currentItem].getAccountType().equals("Deposit")) {

			newRateString = JOptionPane
					.showInputDialog("Enter new interest rate");

			try {
				newRate = Double.parseDouble(newRateString);
				recA[currentItem].setRate(newRate);
				JOptionPane.showMessageDialog(null, "Rate updated to "
						+ newRate + "%");
				displayDetails(currentItem);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(panel,
						"Valid Numeric Value not entered!!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception x) {
			}
		}// end if

		else {
			JOptionPane.showMessageDialog(null,
					"Only Deposit Accounts Have Interest");
		}
	}

	// Transactions
	// Dialogs***********************************************************8

	public void deposit() {

		try {

			String depositAmount = JOptionPane
					.showInputDialog("Enter Deposit Amount");
			try {
				double deposit = Double.parseDouble(depositAmount);
				double currentBalance = recA[currentItem].getBalance();
				double newBalance = (currentBalance + deposit);

				recA[currentItem].setBalance(newBalance);
				balanceT.setText(String.valueOf(newBalance));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(panel, "Numeric Value Needed!!",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception x) {
		}

	}

	public void withdraw() {
		System.out.println("withdraw");

		try {
			String withdrawAmount = JOptionPane
					.showInputDialog("Enter Amount you want to withdraw");
			try {
				double withdraw = Double.parseDouble(withdrawAmount);
				double currentBalance = recA[currentItem].getBalance();
				double newBalance = (currentBalance - withdraw);

				if (withdraw <= currentBalance) {
					recA[currentItem].setBalance(newBalance);
					balanceT.setText(String.valueOf(newBalance));
				} else if (withdraw <= (currentBalance + recA[currentItem]
						.getOverdraft())) {

					recA[currentItem].setBalance(0);
					balanceT.setText(String.valueOf(0));

					double oldOverD = recA[currentItem].getOverdraft();
					double overDraftNeeded = withdraw - currentBalance;
					double newOverD = oldOverD - overDraftNeeded;
					recA[currentItem].setOverdraft(newOverD);
					overdraftT.setText(String.valueOf(newOverD));

				} else {
					JOptionPane.showMessageDialog(panel,
							"You Do not have the fudns for this transaction!!",
							"Warning", JOptionPane.WARNING_MESSAGE);
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(panel, "Numeric Value Needed!!",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception x) {
		}
	}

	public void calculateInterest() {
		System.out.println("calc int");

		try {
			if (recA[currentItem].getAccountType().equals("Deposit")) {
				double balance = recA[currentItem].getBalance();
				double rate = recA[currentItem].getRate() / 100;
				double balanceAfterInterest = balance + (balance * rate);
				recA[currentItem].setBalance(balanceAfterInterest);
				displayDetails(currentItem);
			}// end if

			else {
				JOptionPane.showMessageDialog(null,
						"Current Account, no interest to calculate");
			}

		} catch (Exception x) {
		}
	}

}