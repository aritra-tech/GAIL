package com.geekymusketeers.gail;

public class Users {
    public String name,email, id, contact, gender, designation, officeaddress, base64;
//    public String name, id;


    public Users() { }

    public Users(String Name, String Email, String ID, String Contact, String Gender, String Designation, String OfficeAddress, String Base64){
        this.name = Name;
        this.email = Email;
        this.id = ID;
        this.gender = Gender;
        this.contact = Contact;
        this.designation = Designation;
        this.officeaddress = OfficeAddress;
        this.base64 = Base64;
    }

//    public Users(String Name, String ID){
//        this.name = Name;
//        this.id = ID;
//    }
}
