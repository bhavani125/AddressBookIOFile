package com.bridgelabz;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AddressBook {
    public static final ArrayList<Contacts> contactArrayList = new ArrayList<>();
    public static Map<String, Contacts> nameHashMap = new HashMap<String, Contacts>();
    public static Map<String, Contacts> cityHashMap = new HashMap<String, Contacts>();
    public static Map<String, Contacts> stateHashMap = new HashMap<String, Contacts>();
    static Scanner sc = new Scanner(System.in);
    static AddressBook addressBook = new AddressBook();

    public static boolean addContact(Contacts contact) {
        List<Contacts> checkByName = searchByName(contact.getFirstName());
        for (Contacts equalName : checkByName) {
            if (equalName.equals(contact))
                return false;
        }
        //Calling contactArrayList object here
        contactArrayList.add(contact);
        nameHashMap.put(contact.getFirstName(), contact);
        cityHashMap.put(contact.getCity(), contact);
        stateHashMap.put(contact.getState(), contact);
        return true;
    }
    //Creating searchByName method
    public static List<Contacts> searchByName(String name) {
        //stream and lambda for find filter given name from arraylist
        return contactArrayList.stream().filter(person -> person.getFirstName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
    //Creating searchByCity method
    public static List<Contacts> searchByCity(String city) {
        return contactArrayList.stream().filter(person -> person.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
    //Creating  searchByState method
    public static List<Contacts> searchByState(String state) {
        return contactArrayList.stream().filter(person -> person.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }
    //Creating viewByName method
    public static void viewByName(Map<String, Contacts> nameHashMap) {
        nameHashMap.entrySet().stream().forEach(e ->
                System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }
    //Creating viewByCity method
    public static void viewByCity(Map<String, Contacts> cityHashMap) {
        cityHashMap.entrySet().stream().forEach(e ->
                System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }
    //method for view contact by State
    public static void viewByState(Map<String, Contacts> stateHashMap) {
        stateHashMap.entrySet().stream().forEach(e ->
                System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }
    //Creating method to sort contact by name, city, state
    public List<Contacts> sortBy(Function<? super Contacts, ? extends String> key) {
        return contactArrayList.stream().sorted(Comparator.comparing(key))
                .collect(Collectors.toList());
    }
    //Creating  sortByZip method
    public List<Contacts> sortByZip(Function<? super Contacts, ? extends Long> key) {
        return contactArrayList.stream().sorted(Comparator.comparing(key))
                .collect(Collectors.toList());
    }
    //Creating  editContact method
    public static boolean editContact(Contacts current, Contacts editedContact) {
        if (!contactArrayList.contains(current)) {
            return false;
        }
        contactArrayList.remove(current);
        contactArrayList.add(editedContact);
        return true;
    }
    //Creating  deleteContact method
    public static boolean deleteContact(Contacts contacts) {
        contactArrayList.remove(contacts);
        return true;
    }

    //for showing output details
    @Override
    public String toString() {
        if (contactArrayList.isEmpty())
            return "No contacts found!";
        String result = new String();
        for (int i = 0; i < contactArrayList.size(); i++) {
            result += " " + contactArrayList.get(i);
        }
        return result;
    }

    //Creating   addContact method
    public static Contacts readContact() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter City: ");
        String city = sc.nextLine();
        System.out.print("Enter State: ");
        String state = sc.nextLine();
        System.out.print("Enter Zip Code: ");
        Long zip = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter Phone Number: ");
        Long phoneNum = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter Email ID: ");
        String email = sc.nextLine();
        return new Contacts(firstName, lastName, address, city, state, zip, phoneNum, email);
    }
    //Creating addressBookOptions  method for showing option for contacts
    public void addressBookOptions(AddressBook addressBook) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n Address Book Options");
            System.out.println("1) Add contact details\n2)To Edit contact details\n3)To Delete contact details\n" +
                    "4)To Show contacts details\n5)To Sort Address Book\n6)ToBack to main menu");
            System.out.print("Enter Your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    //calling addContact with passing method readContact
                    if (addContact(readContact()))
                        System.out.println("Contact is Added Successfully");
                    else
                        System.out.println("Contact Already Exist");
                    break;
                case 2:
                    System.out.println("Enter First name to edit contact: ");
                    String name = sc.nextLine();
                    List<Contacts> equalName = searchByName(name);
                    //if not match found
                    if (equalName.isEmpty())
                        System.out.println("Data Not Found");
                    //if only one equal match found
                    else if (equalName.size() == 1) {
                        editContact(equalName.get(0), readContact());
                        System.out.println("Contact data modified");
                    } else {
                        //if more than one firstname match found in equal name list
                        equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
                        System.out.println("Enter index to edit : ");
                        int i = sc.nextInt();
                        sc.nextLine();
                        editContact(equalName.get(i), readContact());
                        System.out.println("Contact Modified....!");
                    }
                    break;
                case 3:
                    System.out.println("Enter First name to delete contact: ");
                    name = sc.nextLine();
                    equalName = searchByName(name);
                    if (equalName.isEmpty())
                        System.out.println("Data Not Found.....!");
                    else if (equalName.size() == 1) {
                        deleteContact(equalName.get(0));
                        System.out.println("Contact data deleted....!");
                    } else {
                        equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
                        System.out.println("Enter an index to delete");
                        int index = sc.nextInt();
                        sc.nextLine();
                        deleteContact(equalName.get(index));
                        System.out.println("Contact data deleted....!");
                    }
                    break;
                case 4://calling toString method for showing details
                    System.out.println(toString()); //
                    break;
                case 5:
                    sortByOption();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid");
                    break;
            }
        }
    }
    //Creating searchByOptions method
    public static void searchByOptions() {
        System.out.println("1. Search By name");
        System.out.println("2. Search By city");
        System.out.println("3. Search By state");
        System.out.println("4. Back");
        System.out.print("Enter Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                contactArrayList.forEach(book -> addressBook.searchByName(name).forEach(System.out::println));
                break;
            case 2:
                System.out.print("Enter city: ");
                String city = sc.nextLine();
                contactArrayList.forEach(book -> addressBook.searchByCity(city).forEach(System.out::println));
                break;
            case 3:
                System.out.print("Enter state: ");
                String state = sc.nextLine();
                contactArrayList.forEach(book -> addressBook.searchByState(state).forEach(System.out::println));
                break;
            case 4:
                return;
            default:
                System.out.println("invalid");
        }
    }

    //Creating  viewByOption method
    public static void viewByOption(Map<String, AddressBook> addressBookMap) {
        System.out.println("1. View By name");
        System.out.println("2. View By city");
        System.out.println("3. View By state");
        System.out.println("4. Back");
        System.out.print("Enter Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                viewByName(nameHashMap);
                break;
            case 2:
                viewByCity(cityHashMap);
                break;
            case 3:
                viewByState(stateHashMap);
                break;
            case 4:
                return;
            default:
                System.out.println("invalid");
        }
    }
    //Creating countByOption method
    public static void countByOption() {
        System.out.println("1. Count City ");
        System.out.println("2. Count State");
        System.out.println("3. Back ");
        System.out.println("Enter Your Choice : ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                Map<String, Long> countCity = contactArrayList.stream().collect(Collectors.groupingBy(e -> e.getCity(), Collectors.counting()));
                System.out.println(countCity + "\n");
                break;
            case 2:
                Map<String, Long> countState = contactArrayList.stream().collect(Collectors.groupingBy(e -> e.getState(), Collectors.counting()));
                System.out.println(countState + "\n");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid");
        }
    }
    //Creating  sortByOption method
    public static void sortByOption() {
        System.out.println("1. By first name");
        System.out.println("2. By last name");
        System.out.println("3. By city");
        System.out.println("4. By state");
        System.out.println("5. By zip");
        System.out.println("6. Back");
        System.out.print("Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                addressBook.sortBy(Contacts::getFirstName).forEach(System.out::println);
                break;
            case 2:
                addressBook.sortBy(Contacts::getLastName).forEach(System.out::println);
                break;
            case 3:
                addressBook.sortBy(Contacts::getCity).forEach(System.out::println);
                break;
            case 4:
                addressBook.sortBy(Contacts::getState).forEach(System.out::println);
                break;
            case 5:
                addressBook.sortByZip(Contacts::getZip).forEach(System.out::println);
                break;
            case 6:
                return;
            default:
                System.out.println("invalid");
        }
    }
}