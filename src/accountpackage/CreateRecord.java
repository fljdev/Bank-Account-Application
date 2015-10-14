package accountpackage;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.util.ArrayList;

public class CreateRecord extends JFrame {
	private JDialog createDialog, modifyDialog, deleteDialog,
			setOverdraftDialog, setInterestRateDialog;
	private JPanel createPanel;
	private JLabel accountIdLbl, accountNoLbl, surnameLbl, firstNameLbl;
	private JLabel accTypeLbl, balanceLbl, overdraftLbl;
	private JTextField accountIdTf, accountNoTf, surnameTf, firstNameTf;
	private JTextField balanceTf, overdraftTf;
	private JComboBox accTypeCb;
	private String[] accTypes = { "Current", "Deposit" };
	private JButton createAccountButton;
	private ArrayList<String> accNumbers = new ArrayList<String>();
	private ArrayList<JTextField> textfields = new ArrayList<JTextField>();

	int justCreated = -1;

	public int getJustCreated() {
		return justCreated;
	}

	public void setJustCreated(int justCreated) {
		this.justCreated = justCreated;
	}

	Record[] recA;
	Hash h = new Hash();
	

	public CreateRecord(Record[] rec) {
		recA = rec;
		System.out.println(recA.length + " is the size that came in");
		createRecordDialog();
	}// end const.

	public void createRecordDialog() {

		accNumbers.add("12345678");

		createDialog = new JDialog();
		createDialog.setBounds(350, 100, 350, 250);
		createPanel = new JPanel(new MigLayout());
		createDialog.setTitle("Create a Record");
		createPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		createDialog.add(createPanel);
		createDialog.setVisible(true);

		accountIdLbl = new JLabel("Account ID ");
		accountNoLbl = new JLabel("Account no. (8 digits) ");
		surnameLbl = new JLabel("Surname         ");
		firstNameLbl = new JLabel("First name   ");
		accTypeLbl = new JLabel("Account Type  ");
		balanceLbl = new JLabel("Balance    ");
		overdraftLbl = new JLabel("Overdraft  ");

		accountIdTf = new JTextField(20);
		int i=++Driver.autoId;
		accountIdTf.setText(String.valueOf(i));
		// accountIdTf.setText("100000");
		accountIdTf.setEditable(false);

		accountNoTf = new JTextField(20);
		surnameTf = new JTextField(20);
		firstNameTf = new JTextField(20);
		accTypeCb = new JComboBox<String>(accTypes);
		balanceTf = new JTextField(20);
		balanceTf.setText("0.0");
		overdraftTf = new JTextField(20);
		overdraftTf.setText("0.0");

		createAccountButton = new JButton("Create Account");

		createPanel.add(accountIdLbl);
		createPanel.add(accountIdTf, "growx, pushx, wrap");
		textfields.add(accountIdTf);

		createPanel.add(accountNoLbl);
		createPanel.add(accountNoTf, "growx, pushx, wrap");
		textfields.add(accountNoTf);

		createPanel.add(surnameLbl);
		createPanel.add(surnameTf, "growx, pushx, wrap");
		textfields.add(surnameTf);

		createPanel.add(firstNameLbl);
		createPanel.add(firstNameTf, "growx, pushx, wrap");
		textfields.add(firstNameTf);

		createPanel.add(accTypeLbl);
		createPanel.add(accTypeCb, "growx, pushx, wrap");

		createPanel.add(balanceLbl);
		createPanel.add(balanceTf, "growx, pushx, wrap");
		textfields.add(balanceTf);

		createPanel.add(overdraftLbl);
		createPanel.add(overdraftTf, "growx, pushx, wrap");
		textfields.add(overdraftTf);

		createPanel.add(createAccountButton);

		createButtonClicked clicked = new createButtonClicked();
		createAccountButton.addActionListener(clicked);

		accTypeCb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				Object item = event.getItem();

				if (item.equals("Deposit")) {
					overdraftTf.setEditable(false);
					overdraftTf.setText("N/A");
				}
				if (item.equals("Current")) {
					overdraftTf.setEditable(true);
					overdraftTf.setText("0.0");

				}
			}
		});
	}

	class createButtonClicked implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			Record record = null;
			boolean matchFound = false;
			int badCount = 0;
			int pos = -1;
			double rate = 0.0;

			int accountId = Integer.parseInt(accountIdTf.getText());
			String accNum = accountNoTf.getText();
			if (accNum.equals("")) {
				badCount++;
			}
			String surname = surnameTf.getText();
			if (surname.equals("")) {
				badCount++;
			}
			String firstName = firstNameTf.getText();
			if (firstName.equals("")) {
				badCount++;
			}
			Object type = accTypeCb.getSelectedItem();
			double balance = Double.parseDouble(balanceTf.getText());

			double overdraft = 0;
			if (type.equals("Current")) {
				overdraft = Double.parseDouble(overdraftTf.getText());
				rate = 0.0;
			} else if (type.equals("Deposit")) {
				overdraft = 0.0;
				rate = Driver.INTEREST;

			}

			System.out.println("bad count = " + badCount);

			for (String checkForMatch : accNumbers) {
				if (checkForMatch.equalsIgnoreCase(accNum)) {
					matchFound = true;
				}
			}

			if (badCount == 0) {
				if (!matchFound) {
					if (accNum.length() == 8) {

						boolean slotOk = false;

						do {
							pos = h.getH(accNum);
							if (recA[pos] != null) {
								pos++;
							} else {
								slotOk = true;
							}

						} while (!slotOk);

						record = new Record(accountId, accNum, surname,
								firstName, (String) type, balance, overdraft,
								pos, rate);
						setJustCreated(pos);

						System.out.println("created and added to clot " + pos);

						recA[pos] = record;
						if (recA[pos].getAccountType().equals("Deposit")) {

						}
						System.out.println(record.toString());
						System.out.println("trying to set just created = "
								+ pos);
						startGui.displayDetails(pos);
						createDialog.dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								"Acc No. must be 8 digits long",
								"Length Error!!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Acc No. must be unique", "Duplicate number!!",
							JOptionPane.ERROR_MESSAGE);
				}
			}// end if badCount
			else {
				JOptionPane.showMessageDialog(null,
						"All fields must be filled in", "Empty Fields!!",
						JOptionPane.ERROR_MESSAGE);
			}
		}// end actionPerformed

	}// end inner class

}// end class