import java.util.Scanner;
import java.io.*;
public class Main {
	static String [] [] contacts = new String [100] [5];
  static String [] contact = new String [5];
  static int index = 0;
  static Scanner sc = new Scanner(System.in);

	public static void main(String [] args) {
      boolean finish = true;
      while(finish){

      int option;
      System.out.println("Menu \n Which option would you desire to use \n\t1. Add Contact\n\t 2. Delete Contact \n\t 3. Edit Contacts  \n\t 4. Search Contact \n\t 5. Display Contacts \n\t 6. Exit and export contacts ");
      option = Integer.parseInt(sc.nextLine());

      switch(option){
        case 1:
            if(index<99){
              addContact();
            }else{
              System.out.println("The contact book is full");
            }
          break;
        case 2:
            if(index>0){
              deleteContact();
            }else{
              System.out.println("The contact book is empty");
            }
          break;
        case 3:
            editContact();
          break;
        case 4:
            searchContact();
          break;
        case 5:
            displayContacts();
          break;
        case 6:
            exit();
          break;
          default:
            break;
        }

    }

	}

  static void addContact(){
    boolean flag = false;
    writeData();
    for(int i=0;i<index && !flag;i++){
      if(contact[0].compareTo(contacts[i][0])>0){
        for(int j=index;j>=i;j--){
          contacts[j+1]=contacts[j];
        }
        contacts [i] = contact;
        flag = true;
        index++;
      }
    }
    if(!flag){
      contacts [index] = contact;
      index++;
    }
  }

  static void deleteContact(){
    searchContact();
    System.out.println("Write the number that matches with the contact you want to delete");
    int a = Integer.parseInt(sc.nextLine());
    contacts [a] = contact;
    for(int j=a;j<=index;j++){
      contacts[j]=contacts[j+1];
    }
    index--;
  }

  static void editContact(){
    searchContact();
    System.out.println("Write the number that matches with the contact you want to edit");
    int a = Integer.parseInt(sc.nextLine());
    writeData();
    contacts [a] = contact;

  }

  static void searchContact(){
    int option;
    System.out.println("Do you want to search by... Press\n\t1.Name\n\t 2. Parental Sumames \n\t 3. Maternal Sumames  \n\t 4. Telephone Number \n\t 5. Address");
    option = Integer.parseInt(sc.nextLine());

    if(option > 6 && option < 1 ){
      System.out.println("Invalid option, the search would be done by name");
      option = 1;
    }
    int auxiliarIndex = 0;
    String [] categories = {
      "Name", "Parental Sumame", "Maternal Sumame", "Telephone Number", "Address"
    };
    System.out.println("Write the "+ categories[option-1]+" that you want to search");
    String search = sc.nextLine();
    for(int i=0;i<index;i++){
      if(contacts[i][option-1].toLowerCase().contains(search.toLowerCase())){
        System.out.println("| " + i+ "\t"+contacts[i][0]+"\t | \t\t"+ contacts[i][1] +" "+ contacts[i][2] +"\t\t | "+contacts[i][3]+" | \t "+ contacts[i][4]+"\t |");
        auxiliarIndex++;
      }
    }if(auxiliarIndex==0){
      System.out.println("The are not contacts to display");
    }
  }

  static void exit(){

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

  static void writeData(){
    System.out.println("Write name");
    contact[0] = sc.nextLine();
    System.out.println("Write your Parental Sumame");
    contact[1] = sc.nextLine();
    System.out.println("Write your Maternal Sumame");
    contact[2] = sc.nextLine();
    System.out.println("Write your Telephone Number");
    contact[3] = sc.nextLine();
    System.out.println("Write your full address");
    contact[4] = sc.nextLine();

  }
  static void displayContacts(){
    System.out.println("| \tName\t | \t\tLastname\t\t | Telephone Number | \t Address \t |");
    for(int i=0;i<index;i++){
      System.out.println("| \t"+contacts[i][0]+"\t | \t\t"+ contacts[i][1] +" "+ contacts[i][2] +"\t\t | "+contacts[i][3]+" | \t "+ contacts[i][4]+"\t |");
    }
  }

}
