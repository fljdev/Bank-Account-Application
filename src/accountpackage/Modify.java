package accountpackage;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class Modify extends JFrame {
	Record aRec;

	private JTextField surname, forname, balance, overdraft;
	private String[] accTypes = { "Current", "Deposit" };
	private JComboBox accTypeCb;
	private JLabel sName, fName, bal, od, type;
	private JPanel pan;

	public Modify(Record rec) {
		aRec = rec;

		surname = new JTextField(20);
		surname.setText("");
		forname = new JTextField(20);
		forname.setText("");
		balance = new JTextField(20);
		balance.setText("");
		overdraft = new JTextField(20);
		overdraft.setText("");

		sName = new JLabel("Surname");
		fName = new JLabel("Forname");
		type = new JLabel("Acc Type");
		bal = new JLabel("Balance");
		od = new JLabel("Overdraft");

		accTypeCb = new JComboBox<String>(accTypes);

		pan = new JPanel(new MigLayout());

		pan.add(sName);
		pan.add(surname, "growx, pushx, wrap");
		pan.add(fName);
		pan.add(forname, "growx, pushx, wrap");
		pan.add(type);
		pan.add(accTypeCb, "growx, pushx, wrap");
		pan.add(bal);
		pan.add(balance, "growx, pushx, wrap");
		pan.add(od);
		pan.add(overdraft, "growx, pushx, wrap");
		accTypeCb.setSelectedItem(aRec.getAccountType());
		if (aRec.getAccountType().equals("Deposit")) {
			overdraft.setEditable(false);
			overdraft.setText("N/A");
		}

		int result = JOptionPane.showConfirmDialog(null, pan,
				"Modification Options!!", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {

			int i = aRec.getPosition();

			if (!surname.getText().equals("")) {
				aRec.setSurname(surname.getText());
			}
			if (!forname.getText().equals("")) {
				aRec.setFirstName(forname.getText());
			}

			Object type = accTypeCb.getSelectedItem();

			if (type != aRec.getAccountType()) {
				int confirm = JOptionPane
						.showConfirmDialog(null,
								"You are about to change account type, are you certain??");

				if (confirm == 0) {
					aRec.setAccountType((String) type);

					if (aRec.getAccountType().equals("Current")) {
						aRec.setRate(0.0);
					} else if (aRec.getAccountType().equals("Deposit")) {
						aRec.setRate(Driver.INTEREST);
						aRec.setOverdraft(0);

					}
				}
			}

			if (!balance.getText().equals("")) {

				try {
					double d = Double.parseDouble(balance.getText());
					aRec.setBalance(d);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"Balance must be a numeric value");
				}
			}
			if (overdraft.getText().equals("N/A")) {
				aRec.setOverdraft(0);
			} else if (!overdraft.getText().equals("")) {
				try {
					double o = Double.parseDouble(overdraft.getText());
					aRec.setOverdraft(o);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"Overdraft must be a numeric value");
				}
			}

			startGui.displayDetails(i);
			JOptionPane.showMessageDialog(null, "Modifications Stored");
		}
	}
}