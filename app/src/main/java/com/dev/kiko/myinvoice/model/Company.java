package com.dev.kiko.myinvoice.model;

import android.graphics.Bitmap;

import com.dev.kiko.myinvoice.model.invoice.Contact;
import com.dev.kiko.myinvoice.model.invoice.Invoice;

import java.util.ArrayList;

/**
 * Created by kikogoinhas on 14/12/2017.
 * This class represents an object containing data about the invoices in a company.
 */

public class Company {


    private String companyName;

    private Bitmap companyLogo;

    private String companyEmail;

    private String companyTelephone;

    private String companyWebsite;

    private String companyFax;

    private ArrayList<Invoice> invoices;

    private ArrayList<Contact> contacts;


    /**
     * Default Constructor for this class.
     * @param name  The name of the company
     * @param logo  The logo bitmap image of the company
     */
    public Company(String name, Bitmap logo) {

        companyName = name;
        companyLogo = logo;
        invoices = new ArrayList<>();
        contacts = new ArrayList<>();


    }

    /**
     * Get's the number of invoices.
     * @return returns the total number of invoices of this object.
     */
    public int totalNumOfInvoices(){
        return invoices.size();
    }




    //----------Getters and Setters-------------//

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyLogo(Bitmap companyLogo) {
        this.companyLogo = companyLogo;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public void setInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Bitmap getCompanyLogo() {
        return companyLogo;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getCompanyTelephone() {
        return companyTelephone;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }


}
