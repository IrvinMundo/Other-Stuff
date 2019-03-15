import java.util.Scanner;
import java.io.*;

/**
*Main Class of the contacts book program
*/

public class Main {

	/**

	*@param {String []} contact - It contains the contact that is edited, deleted or added
	*@param {String [][]} contacts - It contains all contacts saved
	*@param {String []} args
	*@param {int} index - Indicates how many contacts the contact book has
	*@param {Scanner} sc - Provides the functions needed for requesting information

	*@return {Void}
	*/

	// Contain all the contacts
	static String [] [] contacts = new String [100] [5];
	// [0]: name, [1]: Parental Surname, [2]: Maternal Surname, [3]: Telephone Number, [4]: Address
	static String [] contact = new String [5];
	//This indicates how many registers are in the contacts book
	static int index = 0;
	static Scanner sc = new Scanner(System.in);

	public static void main (String [] args) {
		//In order to keep the program repeating the process
		while(true){
				//Option For The Actions
				int option;
				System.out.println("Menu \n Which option would you desire to use \n\t1. Add Contact\n\t 2. Delete Contact \n\t 3. Edit Contacts  \n\t 4. Search Contact \n\t 5. Display Contacts \n\t 6. Exit and export contacts ");
				option = Integer.parseInt(sc.nextLine());
				switch (option) {
					//Add Contact
					case 1:
						//Additional Check for preventing to overfill the array
						if (index < 99 ) {
							addContact();
						} else {
							System.out.println("The contact book is full");
						}
						break;
					//Delete Contact
					case 2:
						//Additional Check for preventing doing actions in a empty book
						if (index > 0) {
							deleteContact();
						} else {
							System.out.println("The contact book is empty");
						}
						break;
					//Edit Contact
					case 3:
						//Additional Check for preventing doing actions in a empty book
						if( index > 0) {
							editContact();
						} else {
							System.out.println("The contact book is empty");
						}
						break;
						//Search Contact
					case 4:
						//Additional Check for preventing doing actions in a empty book
						if (index > 0) {
							searchContact();
						} else {
							System.out.println("The contact book is empty");
						}
						break;
					//Display Contacts
					case 5:
						//Additional Check for preventing doing actions in a empty book
						if (index > 0) {
							displayContacts();
						} else {
							System.out.println("The contact book is empty");
						}
						break;
					//Exit the program
					case 6:
						exit();
						break;
					default:
						System.out.println("Invalid option");
						break;
				}

		}

	}

	/**
	*@return {Void}
	*Add a contact after give the requested information calling the writeDataMethod to do so
	*/
	static void addContact () {
		boolean flag = false;
		writeData();
		for (int i = 0; i < index && !flag; i++) {
			if (contact[0].compareTo(contacts[i][0]) > 0) {
				for (int j = index; j>=i; j--) {
					contacts[j+1]=contacts[j];
				}
				contacts [i] = contact;
				flag = true;
				index++;
			}
		}
		if (!flag) {
			contacts [index] = contact;
			index++;
		}
		System.out.println("The contact "+ contact[0]+ " " +contact[1]+ " " + contact[2]+ " has benn added");

	}

	/**
	*@return {Void}
	* Deletes a contact. First you have to search the contact, then you just put the index that the searched gave you
	*/
	static void deleteContact () {

		if (searchContact() > 0) {
			System.out.println("Write the number that matches with the contact you want to delete");
			int a = Integer.parseInt(sc.nextLine());
			contacts [a] = contact;
			for (int j = a; j <= index; j++){
				contacts[j]=contacts[j+1];
			}
			index--;
			System.out.println("The contact "+ contact[0]+ " " +contact[1]+ " " + contact[2]+ " has benn removed");
		} else {
			System.out.println("There are not contacts with the given information");
		}
	}

	/**
	* Edits a contacts; you have to search the contat that you want to edit
	*@return {Void}
	*/
	static void editContact () {

		if (searchContact() > 0) {
			System.out.println("Write the number that matches with the contact you want to edit");
			int a = Integer.parseInt(sc.nextLine());
			writeData();
			contacts [a] = contact;
		} else {
			System.out.println("There are not contacts with the given information");
		}

	}

	/**
	*@@return auxiliarIndex Number of register that match with the pattern
	*Search the contacts you want, this search can be used by any category. Name, Surnames, Phone and Address. Also this method return the number of contacts that matches the param
	*/
	static int searchContact () {
		int option;
		System.out.println("Do you want to search by... Press\n\t1.Name\n\t 2. Parental Surnames \n\t 3. Maternal Surnames  \n\t 4. Telephone Number \n\t 5. Address");
		option = Integer.parseInt(sc.nextLine());

		if (option > 6 && option < 1 ) {
			System.out.println("Invalid option, the search would be done by name");
			option = 1;
		}

		int auxiliarIndex = 0;
		String [] categories = {
			"Name", "Parental Surname", "Maternal Surname", "Telephone Number", "Address"
		};
		System.out.println("Write the "+ categories[option-1]+" that you want to search");
		String search = sc.nextLine();
		for (int i = 0; i < index; i++){
			if (contacts[i][option-1].toLowerCase().contains(search.toLowerCase())) {
				System.out.println("| " + i+ "\t"+contacts[i][0]+"\t | \t\t"+ contacts[i][1] +" "+ contacts[i][2] +"\t\t | "+contacts[i][3]+" | \t "+ contacts[i][4]+"\t |");
				auxiliarIndex++;
			}
		}
		if (auxiliarIndex == 0) {
			System.out.println("The are not contacts to display");
		}
		return auxiliarIndex;
	}

	/**
	*End the program
	^@return {Void}
	*/
	static void exit () {

		/*try {
		File file = new File("contacts.txt");
		if (file.createNewFile()) {
		System.out.println("File created: " + file.getName());
		} else {
		System.out.println("File already exists.");
		}
		} catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
		}*/
		System.exit(0);
	}


	/**
	* Provides the process to retrieve information of the user for adding or editing contacts
	* @@return {Void}
	*/
	static void writeData () {
		System.out.println("Write name");
		contact[0] = sc.nextLine();
		System.out.println("Write your Parental Surname");
		contact[1] = sc.nextLine();
		System.out.println("Write your Maternal Surname");
		contact[2] = sc.nextLine();
		System.out.println("Write your Telephone Number");
		contact[3] = sc.nextLine();
		System.out.println("Write your full address");
		contact[4] = sc.nextLine();

	}

	/**
	*@@return {Void}
	* Display all contacts
	*/
	static void displayContacts () {
		//This method prints the whole contacts [] [], which contains all the contacts
		if (index > 0) {
			System.out.println("| \tName\t | \t\tLastname\t\t | Telephone Number | \t Address \t |");
			for (int i = 0; i < index; i++) {
				System.out.println("| \t" + contacts[i][0] + "\t | \t\t" + contacts[i][1] + " "+ contacts[i][2] + "\t\t | "+ contacts[i][3]+ " | \t "+ contacts[i][4] +"\t |");
			}
		} else {
			System.out.println("The are not contacts to display");
		}

	}

}
