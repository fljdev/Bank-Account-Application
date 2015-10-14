package accountpackage;

public class Record {

	private int accountId;
	private String accountNumber;
	private String surname;
	private String firstName;
	private String accountType;
	private double balance = 0.0;
	private double overdraft = 0.0;
	private double rate;
	private int position;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Record() {
		this(0, "", "", "", "", 0.0, 0.0, 0, 0.0);
	}
	


	public Record(int accountId, String accountNumber, String surname,
			String firstName, String accountType, double balance,
			double overdraft, int position, double rate) {

		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.surname = surname;
		this.firstName = firstName;
		this.accountType = accountType;
		this.balance = balance;
		this.overdraft = overdraft;
		this.position = position;
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	@Override
	public String toString() {
		return "Record [accountId=" + accountId + ", accountNumber="
				+ accountNumber + ", surname=" + surname + ", firstName="
				+ firstName + ", accountType=" + accountType + ", balance="
				+ balance + ", overdraft=" + overdraft + ", rate=" + rate
				+ ", position=" + position + "]";
	}

	
}