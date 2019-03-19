import java.util.Scanner;
import java.io.*;

/**
 * Main Class of the contacts book program
 */

public class Main {
	static Scanner sc = new Scanner(System.in);

	/**
	 * @param {String []} args
	 *
	 * @return {Void}
	 */
	public static void main (String [] args) {
		// Contain all the contacts
	 	String [] [] contacts = new String [100] [5];

		// This indicates how many registers are in the contacts book
	 	int index = 0;

		// In order to keep the program repeating the process
		while(true){
			// Option For The Actions
			int option;
			System.out.println("Menu \n Which option would you desire to use \n\t1. Add Contact\n\t 2. Delete Contact \n\t 3. Edit Contacts  \n\t 4. Search Contact \n\t 5. Display Contacts \n\t 6. Exit and export contacts ");
			option = Integer.parseInt(sc.nextLine());
			// Additional Check for preventing doing actions in a empty book
			if ( index <= 0 && ( option >= 2 && option <=5 ) ) {
				System.out.println("The contact book is empty");
				continue;
			} else {
				// Additional Check for preventing to overfill the array
				if ( index > 99 ) {
					System.out.println("The contact book is full");
					continue;
				}
			}
			switch (option) {
				// Add Contact
				case 1:
					addContact(contacts, index);
					index = index(contacts, index);
					System.out.println(index);
					break;
				// Delete Contact
				case 2:
					deleteContact(contacts,index);
					index = index(contacts, index);
					System.out.println(index);
					break;
				// Edit Contact
				case 3:
					editContact(contacts, index);
					break;
				// Search Contact
				case 4:
					searchContact(contacts, index);
					break;
				// Display Contacts
				case 5:
					displayContacts(contacts, index);
					break;
				// Exit the program
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
	 * Add a contact after give the requested information calling the writeDataMethod to do so
	 *
	 * @param {String [] []} contacts Current book of contacts
 	 * @param {int} index contacts in the book
	 *
	 * @return {String [] []} contacts The full array that is consisted of each register after a contact is added
	 */
	static String [] [] addContact (String [] [] contacts, int index) {
		int auxindex = 0;
		String [] contact = writeData();
		for (int i = 0; i < index ; i++) {
			System.out.println(contact[0] + " - " + contacts[i][0]);
			System.out.println(contact[0].toLowerCase().compareTo(contacts[i][0].toLowerCase()));
			if ( contact[0].toLowerCase().compareTo(contacts[i][0].toLowerCase()) < 0) {
				for (int j = index-1; j >= i; j--) {
					for (int k = 0; k < 5; k++) {
						contacts [j + 1] [k] = contacts[j][k];
					}
				}
				auxindex = i;
				break;
			} else {
				auxindex++;
			}
		}
		for (int i = 0; i < 5; i++) {
			contacts [auxindex] [i] = contact[i];
		}
		print(contacts);

		System.out.println("The contact " + contact[0] + " " + contact[1] + " " + contact[2] + " has been added");
		return contacts;
	}

	/**
	 * Deletes a contact. First you have to search the contact, then you just type the index that the searched gave you
	 *
	 * @param {String [] []} contacts Current book of contacts
 	 * @param {int} index contacts in the book
	 *
	 * @return {String [] []} contacts The full array that is consisted of each register after a contact is deleted
	 */
	static String [] [] deleteContact (String [] [] contacts, int index) {

		if (searchContact(contacts, index) > 0) {
			System.out.println("Write the number that matches with the contact you want to delete");
			int a = Integer.parseInt(sc.nextLine());
			String [] contact = new String [5];
			for (int i = 0; i < 5; i++) {
				contact[i] = contacts [a] [i];
			}
			for (int j = a; j <= index; j++){
				for (int k = 0; k < 5; k++) {
					contacts [j] [k] = contacts[j + 1][k];
				}
			}
			print(contacts);
			System.out.println("The contact " + contact[0]+ " " +contact[1]+ " " + contact[2]+ " has been removed");
		} else {
			System.out.println("There are not contacts with the given information");
		}
		return contacts;
	}

	/**
	 * Edits a contacts; you have to search the contat that you want to edit
	 *
	 * @param {String [] []} contacts Current book of contacts
 	 * @param {int} index contacts in the book
	 *
	 * @return {String [] []} contacts The full array that is consisted of each register after a contact is edited
	 */
	static String [] [] editContact (String [] [] contacts, int index) {

		if (searchContact(contacts, index) > 0) {
			System.out.println("Write the number that matches with the contact you want to edit");
			int a = Integer.parseInt(sc.nextLine());
			String [] contact = writeData();
			for (int i = 0; i < 5; i++) {
				contacts [a] [i] = contact[i];
			}
		} else {
			System.out.println("There are not contacts with the given information");
		}
		return contacts;
	}

	/**
	 * Search the contacts you want, this search can be used by any category. Name, Surnames, Phone and Address. Also this method return the number of contacts that matches the param
	 *
	 * @param {String [] []} contacts Current book of contacts
 	 * @param {int} index contacts in the book
	 *
	 * @return { int } auxiliarIndex Number of register that match with the pattern
	 */
	static int searchContact (String [] [] contacts, int index) {
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
		System.out.println("Write the " + categories[option-1]+ " that you want to search");
		String search = sc.nextLine();
		for (int i = 0; i < index; i++){
			if (contacts[i][option-1].toLowerCase().contains(search.toLowerCase())) {
				System.out.println("| " + i+ "\t" +contacts[i][0]+ "\t | \t\t" + contacts[i][1] + " " + contacts[i][2] + "\t\t | " +contacts[i][3]+ " | \t " + contacts[i][4]+ "\t |");
				auxiliarIndex++;
			}
		}
		if (auxiliarIndex == 0) {
			System.out.println("The are not contacts to display");
		}
		return auxiliarIndex;
	}

	/**
	 * End the program
	 *
	 * @return {Void}
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
	 *
	 * @return {String []} contact A contacts is created in order to perform a/an add/edit/delete action in the respective method
	 */
	static String [] writeData () {

		// [0]: name, [1]: Parental Surname, [2]: Maternal Surname, [3]: Telephone Number, [4]: Address
	 	String [] contact = new String [5];
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
		return contact;

	}

	/**
	 * Display all contacts
	 *
	 * @param {String [] []} contacts Current book of contacts
	 * @param {int} index contacts in the book
	 *
	 * @return {Void}
	 */
	static void displayContacts (String [] [] contacts, int index) {
		// This method prints the whole contacts [] [], which contains all the contacts
		System.out.println("| \tName\t | \t\tLastname\t\t | Telephone Number | \t Address \t |");
		for (int i = 0; i < index; i++) {
			System.out.println("| \t" + contacts[i][0] + "\t | \t\t" + contacts[i][1] + " " + contacts[i][2] + "\t\t | " + contacts[i][3]+ " | \t " + contacts[i][4] + "\t |");
		}


	}

	/**
	* Debug method after an action is done
	*
	* @@param {String [] []} contacts Current book of contacts
	*
	* @return {Void}
	*/
	static void print(String [] [] contacts){
		for (int i = 0; i < 10; i++) {
			System.out.println("| \t" + contacts[i][0] + "\t | \t\t" + contacts[i][1] + " " + contacts[i][2] + "\t\t | " + contacts[i][3] + " | \t " + contacts[i][4] + "\t |");
		}
	}

	/**
	* Provides the stability of the size of the records, also avoids that a overflow or underflow is commited
	*
	* @param {String [] []} contacts The current
	*
	* @return {int} Quantity of contacts that the book has
	*/
	static int index(String [] [] contacts, int index){
		return ( contacts [index] [0] != null ) ? index + 1 : index - 1;
	}

}
