package com.bridgelabz;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.*;

public class AddressBookMain {
    //Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Creating object for AddressBook Class
        AddressBook addressBook = new AddressBook();
        Map<String, AddressBook> addressBookMap = new HashMap<String, AddressBook>();
        while (true) {
            System.out.println("\nWelcome to Address Book System");
            System.out.println("1)To New Address Book\n2)To Select Address Book\n3)To Delete Address Book\n" +
                    "4)To Search Contact Data\n5)To View Contact Data\n6)To Count Contacts\n7)To Write data\n8)To Read data\n " +
                    "9)To Write data into CsvFile\n10)To Read data into CsvFile\n11)Exit");
            System.out.print("Enter Your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter new address Book name: ");
                    String bookName = sc.next();
                    sc.nextLine();
                    //adding bookName as a key and value is allocating memory for addressbook obj
                    addressBookMap.put(bookName, new AddressBook());
                    //calling addressbookOptions method
                    addressBook.addressBookOptions(addressBookMap.get(bookName));
                    break;
                case 2:
                    System.out.println("the available Address Books : ");
                    //retrieving keys from hashmap to show addressBookList
                    Set keys = addressBookMap.keySet();
                    Iterator i = keys.iterator();
                    while (i.hasNext()) {
                        System.out.println(i.next());
                    }
                    System.out.println("Enter Address Book name you want to Open : ");
                    String name = sc.nextLine();
                    System.out.println("Current Address Book is : " + name);
                    addressBook.addressBookOptions(addressBookMap.get(name));
                    break;
                case 3:
                    System.out.println("List of available Address Book : ");
                    Set keys1 = addressBookMap.keySet();
                    Iterator i1 = keys1.iterator();
                    while (i1.hasNext()) {
                        System.out.println(i1.next());
                    }
                    System.out.println("Enter Address Book name to be delete: ");
                    name = sc.nextLine();
                    addressBookMap.remove(name);
                    break;
                case 4:
                    addressBook.searchByOptions();
                    break;
                case 5:
                    addressBook.viewByOption(addressBookMap);
                    break;
                case 6:
                    addressBook.countByOption();
                    break;
                case 7:
                    AddressBookFileIO addressBookFileIO = new AddressBookFileIO();
                    addressBookFileIO.writeData(addressBookMap);
                    break;
                case 8:
                    AddressBookFileIO addressBookFileIOS = new AddressBookFileIO();
                    System.out.println(addressBookFileIOS.readData());
                    break;
                case 9:
                    try {
                        AddressBookCSV.writeDataToCSV();
                    }catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                        e.printStackTrace();
                    }
                    break;
                case 10:
                    try {
                        AddressBookCSV.readDataFromCSV();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 11:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
}
