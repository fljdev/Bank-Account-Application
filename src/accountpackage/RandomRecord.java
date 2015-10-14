package accountpackage;

import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomRecord extends Record {

	public RandomRecord() {
		super();

	}

	public RandomRecord(int accountId, String accountNumber, String surname,
			String firstName, String accountType, double balance,
			double overdraft, int position, double rate) {
		super(accountId, accountNumber, surname, firstName, accountType,
				balance, overdraft, position, rate);
	}
	
	
	
	

	/*
	 * This method will take a record object as a whole, then write the values
	 * stored for each attribute, in the case of Strings, the writeString method
	 * will be called by this method
	 * 
	 * 
	 * 
	 */
	public void write(RandomAccessFile file) throws IOException {

		file.writeInt(getAccountId());
		writeString(file, getAccountNumber());
		writeString(file, getSurname());
		writeString(file, getFirstName());
		writeString(file, getAccountType());
		file.writeDouble(getBalance());
		file.writeDouble(getOverdraft());
		file.writeInt(getPosition());
		file.writeDouble(getRate());

	}
	
	
	/*
	 * in reading a file back in, just do the opposite to what
	 * was done to write
	 * 
	 */
	
	public void read(RandomAccessFile file)throws IOException{
		
		setAccountId(file.readInt());
		setAccountNumber(readString(file));
		setSurname(readString(file));
		setFirstName(readString(file));
		setAccountType(readString(file));
		setBalance(file.readDouble());
		setOverdraft(file.readDouble());
		setPosition(file.readInt());
		setRate(file.readDouble());
	}

	/*
	 * This method will read the characters back from the file, one at a time
	 * and place them into a char array(like a String) which must be the exact
	 * same size as the buffer in the write method. the last char. by default,
	 * is the "\0" escape symbol, so when this is found, it will be replaced by
	 * a "" empty string
	 * 
	 * 
	 * 
	 * 
	 */
	public String readString(RandomAccessFile file) throws IOException {
		char stringBeingRead[] = new char[8];
		char temp;// temp will hold each individual char as they are being read
					// in
		for (int i = 0; i < stringBeingRead.length; i++) {

			temp = file.readChar();
			if (temp == ('\0')) {
				temp = ' ';
			}
			stringBeingRead[i] = temp;
		}
		return new String(stringBeingRead);
	}

	/*
	 * This method will take each char of a String and write them to file one
	 * char at a time by passing them through a buffer
	 */
	
	
	
	
	public void writeString(RandomAccessFile file, String str)
			throws IOException {
		StringBuffer buff = null;

		if (str != null) {
			buff = new StringBuffer(str);
		} else {
			buff = new StringBuffer(8);
		}

		buff.setLength(8);
		file.writeChars(buff.toString());
	}

}