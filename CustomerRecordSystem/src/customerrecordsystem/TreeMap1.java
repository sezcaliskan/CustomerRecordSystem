/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerrecordsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author sezin
 */
public class TreeMap1 {

    static Scanner s = new Scanner(System.in);
    static ArrayList<Integer> idlist;
    static ArrayList<String> namelist;
    static ArrayList<Integer> purchaselist;
    static ArrayList<String> genderlist;
    static ArrayList<Integer> agelist;
    static TreeMap<Integer, Customer> m = new TreeMap<Integer, Customer>(); // with id
    static TreeMap<String, Customer> m2 = new TreeMap<String, Customer>();// with name
    static TreeMap<Integer, Customer> m3 = new TreeMap<>(); // with age

    public static void main(String[] args) throws FileNotFoundException, IOException {

        namelist = new ArrayList<>();
        idlist = new ArrayList<>();
        purchaselist = new ArrayList<>();
        genderlist = new ArrayList<>();
        agelist = new ArrayList<>();

        // to find how many lines there are in the text(gonna use it for constructing the maps)
        System.out.println("kullanmak istediğiniz text fileın adresini giriniz");
        String adres = s.nextLine();

        readfromFile(adres);

        for (int i = 0; i < namelist.size(); i++) {
            Customer customerfromtextfile = new Customer(namelist.get(i), idlist.get(i), purchaselist.get(i), genderlist.get(i), agelist.get(i));

            m.put(idlist.get(i), customerfromtextfile);

        }

        for (int i = 0; i < namelist.size(); i++) {
            Customer customerfromtextfile = new Customer(namelist.get(i), idlist.get(i), purchaselist.get(i), genderlist.get(i), agelist.get(i));
            m2.put(namelist.get(i), customerfromtextfile);
        }

        for (int i = 0; i < namelist.size(); i++) {
            Customer customerfromtextfile = new Customer(namelist.get(i), idlist.get(i), purchaselist.get(i), genderlist.get(i), agelist.get(i));
            m3.put(agelist.get(i), customerfromtextfile);
        }

        // Displaying the TreeMap 
        System.out.println("Here is the list of customer data: " + m.toString()); // customer data listi hep aynı arama yaptığımız key farklı
        System.out.println("choose what you want to do!");
        System.out.println("for searching a key enter 0. for range search a enter 1.");
        System.out.println("for removing a customer enter 2. for adding a customer enter 3");

        int choice = s.nextInt();

        if (choice == 0) {
            System.out.println("choose which key you want to search with id ,namesurname or age.");
            String firstchoice = s.next();
            switch (firstchoice) {
                case "id":
                    searchWithid();
                    break;
                case "namesurname":
                    searchWithname();
                    break;
                case "age":
                    searchWithage();
                    break;
                default:
                    break;
            }

        }
        if (choice == 1) {
            System.out.println("choose which key you want to rangesearch with (id ,namesurname or age.)");
            String firstchoice = s.next();
            switch (firstchoice) {
                case "id": {
                    System.out.println("first enter the start key second enter the end key ");
                    int startkey = s.nextInt();
                    int endkey = s.nextInt();
                    searchRangesWithid(startkey, endkey);
                    break;
                }
                case "name": {
                    System.out.println("enter the max name");
                    String startname = "a";
                    String endname = s.next();
                    searchRangesWithname(startname, endname);
                    break;
                }
                case "age": {
                    System.out.println("enter the start key and the end key");
                    int startkey = s.nextInt();
                    int endkey = s.nextInt();
                    searchRangesWithage(startkey, endkey);
                    break;
                }
                default:
                    break;
            }
        }
        if (choice == 2) {
            System.out.println("choose which key you want to remove customer with (id ,namesurname or age.)");
            String firstchoice = s.next();
            switch (firstchoice) {
                case "id":
                    System.out.println("enter the key value that you wanna remove");
                    int removekey = s.nextInt();
                    removeCustomerWithid(removekey);
                    System.out.println("new map: " + m.toString());
                    break;
                case "name":
                    System.out.println("enter the key namesurname(ofthecustomer) that you wanna remove");
                    String removename = s.next();
                    removeCustomerWithname(removename);
                    System.out.println("newmaplist: " + m2.toString());
                    break;
                case "age":
                    System.out.println("enter the  age of the customer that you wanna remove from list  ");
                    int addkey = s.nextInt();
                    removeCustomerWithage(addkey);
                    break;
                default:
                    break;
            }
        }

        if (choice == 3) {
            System.out.println("choose which key you want to add customer with (id ,namesurname or age.)");
            String firstchoice = s.next();
            switch (firstchoice) {
                case "id":
                    System.out.println("enter the id that you wanna add the new customer with");
                    int addwid = s.nextInt();
                    addCustomerWithid(addwid);
                    System.out.println("here is the new map: " + m.toString());
                    break;
                case "name":
                    System.out.println("enter the name that you wanna add the new customer with(which will be our key)");
                    String addname = s.next();
                    addCustomerWithname(addname);
                    System.out.println("new mapping:" + m2.toString());
                    break;
                case "age":
                    System.out.println("enter the age that you wanna add the new customer with(age will be the key");
                    int addwage = s.nextInt();
                    addCustomerWithage(addwage);
                    break;
                default:
                    break;
            }
        }
    }

    public static void readfromFile(String adres) throws FileNotFoundException, IOException {

        // adres = "C:\\Users\\sezin\\Documents\\customerdata.txt"; //normalde bunu kullanıca girecek ama denemek için ben belirledim şimdilik
        BufferedReader reader = new BufferedReader(new FileReader(
                adres));
        // Read lines from file.
        while (true) {
            String newline = reader.readLine();
            if (newline == null) {
                break;
            }

            String[] parts = newline.split(",");

            int intids;
            int intpurchase;
            int intage;
            String name = parts[0];
            String gender = parts[3];
            try {
                intids = Integer.parseInt(parts[1]);
                intpurchase = Integer.parseInt(parts[2]);
                intage = Integer.parseInt(parts[4]);
            } catch (NumberFormatException e) {
                intids = 0;
                intpurchase = 0;
                intage = 0;

            }

            idlist.add(intids);
            purchaselist.add(intpurchase);
            agelist.add(intage);
            namelist.add(name);
            genderlist.add(gender);
        }
        reader.close();
    }

    public static void searchWithid() {
        System.out.println("enter the customer id that you want to search for.");
        int keyid = s.nextInt();
        if (m.containsKey(keyid) == true) {

            System.out.println("yes there is a customer with that id: " + m.get(keyid));

        } else {
            System.out.println("no there isn't a customer with that id ");
        }

    }

    public static void searchWithname() {
        System.out.println("enter the customer name that you want to search for.");
        String keyname = s.next();
        if (m2.containsKey(keyname) == true) {

            System.out.println("yes there is a customer with a name : " + m2.get(keyname));

        } else {
            System.out.println("no there isn't a customer with that name ");
        }

    }

    public static void searchWithage() {
        System.out.println("enter the customer age that you want to search for.");
        int keyage = s.nextInt();
        if (m3.containsKey(keyage) == true) {
            System.out.println("yes there is a customer with that age: " + m3.get(keyage));
        } else {
            System.out.println("no there isn't a customer with that age ");
        }

    }

    public static void searchRangesWithid(int startkey, int endkey) {

        if (m.subMap(startkey, endkey).isEmpty()) {
            System.out.println("there isnt any customer in that range");

        } else {

            System.out.println("yes there are customers with id number in that range");
            System.out.println("their data: " + m.subMap(startkey, endkey).toString());
        }
    }

    public static void searchRangesWithname(String startname, String endname) {

        for (int i = 0; i < m2.size(); i++) {
            if (endname.length() > namelist.get(i).length()) {
                System.out.println("yes there are names in that range :" + namelist.get(i));

            } else {
                System.out.println("there are no names in that range");
            }

        }

    }

    public static void searchRangesWithage(int startkey, int endkey) {

        if (m3.subMap(startkey, endkey).isEmpty()) {
            System.out.println("there isnt any customer in that range");

        } else {

            System.out.println("yes there are customers with that age in that range");
            System.out.println("their data: " + m3.subMap(startkey, endkey).toString());
        }
    }

    public static void removeCustomerWithid(int removekey) {
        if (m.containsKey(removekey)) {
            m.remove(removekey);
            System.out.println("you removed the key");
        } else {
            System.out.println("there isnt a cstomer with that id");
        }
        System.out.println("new mapping :" + m.toString());
    }

    public static void removeCustomerWithname(String removename) {
        if (m2.containsKey(removename)) {
            m2.remove(removename);
            System.out.println("you removed the customer with name" + removename);
        } else {
            System.out.println("there isnt someone with that name");
        }
    }

    public static void removeCustomerWithage(int removekey) {
        if (m3.containsKey(removekey)) {
            m3.remove(removekey);
            System.out.println("you removed the key");
        } else {
            System.out.println("there isnt a cstomer with that id");
        }
        System.out.println("new mapping :" + m3.toString());
    }

    public static void addCustomerWithid(int addwid) {
        System.out.println("enter the customer iname,numofpurchases,gender age");

        String name = s.next();
        int purchases = s.nextInt();
        String gender = s.next();
        int age = s.nextInt();

        Customer c = new Customer(name, addwid, purchases, gender, age);
        m.put(addwid, c);

        System.out.println(" you added a new customer to the map");
    }

    public static void addCustomerWithname(String addname) {
        System.out.println("enter the customer id,numofpurchases,gender age");
        int id = s.nextInt();
        int purchases = s.nextInt();
        String gender = s.next();
        int age = s.nextInt();

        Customer c = new Customer(addname, id, purchases, gender, age);
        m2.put(addname, c);

        System.out.println(" you added a new customer to the map" + m2.toString());
    }

    public static void addCustomerWithage(int addwage) throws IOException {
        System.out.println("enter the customer id ,name,numofpurchases,gender");
        int id = s.nextInt();
        String name = s.next();
        int purchases = s.nextInt();
        String gender = s.next();

        Customer c = new Customer(name, id, purchases, gender, addwage);
        m3.put(addwage, c);
        System.out.println("you added a new customer");
        System.out.println("new mapping: " + m3.toString());

        FileWriter fw = new FileWriter("newcustomerdata.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.print(c);
        System.out.println("you created a new file");

    }

}
