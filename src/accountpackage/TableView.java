package accountpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableView extends JFrame {
	private JPanel topPanel;
	private JTable table;
	private JScrollPane scrollPane;
	private ArrayList<Integer> posArray;

	int tableSize = 0;

	public TableView(Record[] rec) {
		for (int i = 0; i < 25; i++) {
			if (rec[i] != null)
				tableSize++;
		}

		System.out.println("Table size that came in was " + tableSize);
		setTitle("List All Table");
		setSize(700, 50 + (tableSize * 16));
		setBackground(Color.cyan);

		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);

		String columnNames[] = { "Account ID", "Account No", "Surname",
				"Firstname", "Acc Type", "balance", "overdrafr" };

		String dataValues[][] = new String[tableSize][7];

		posArray = new ArrayList<Integer>();
		for (int i = 0; i < 25; i++) {
			if (rec[i] != null) {
				System.out.println("position reads " + i);
				// System.out.println(rec[i].getPosition());
				posArray.add(rec[i].getPosition());
			}
		}
		System.out.println(posArray.toString());

		for (int i = 0; i < tableSize; i++) {

			dataValues[i][0] = String.valueOf(rec[posArray.get(i)]
					.getAccountId());
			dataValues[i][1] = String.valueOf(rec[posArray.get(i)]
					.getAccountNumber());
			dataValues[i][2] = rec[posArray.get(i)].getSurname();
			dataValues[i][3] = rec[posArray.get(i)].getFirstName();
			dataValues[i][4] = rec[posArray.get(i)].getAccountType();
			dataValues[i][5] = String
					.valueOf(rec[posArray.get(i)].getBalance());
			dataValues[i][6] = String.valueOf(rec[posArray.get(i)]
					.getOverdraft());

		}

		// Create a new table instance
		table = new JTable(dataValues, columnNames);
		table.setAutoCreateRowSorter(true);

		// Add the table to a scrolling pane
		scrollPane = new JScrollPane(table);
		topPanel.add(scrollPane, BorderLayout.CENTER);
		setLocation(280, 200);
		setVisible(true);

	}
}