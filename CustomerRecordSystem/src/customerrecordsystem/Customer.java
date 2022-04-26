/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerrecordsystem;

/**
 *
 * @author sezin
 */
public class Customer {

    String namesurname;
    int id;
    int listofpurchases;

    public int getId() {
        return id;
    }
    String gender;
    int age;

    public Customer(String namesurname, int id, int listofpurchases, String gender, int age) {
        this.namesurname = namesurname;
        this.id = id;
        this.listofpurchases = listofpurchases;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return ("Customer: " + namesurname + "," + id + "," + listofpurchases + "," + gender + "," + age);
    }

}
