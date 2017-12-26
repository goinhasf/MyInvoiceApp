package com.dev.kiko.myinvoice.controller;

import android.content.Context;
import android.graphics.Bitmap;

import com.dev.kiko.myinvoice.manager.MasterManager;
import com.dev.kiko.myinvoice.model.Company;
import com.dev.kiko.myinvoice.model.invoice.Invoice;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by kikogoinhas on 21/12/2017.
 */

public class CompanyController implements MasterManager.CompanyManager{

    // The invoice controller used by the company controller
    private InvoiceController invoiceController;
    // The company being controlled
    private Company company;
    // The only instance of this controller
    private static CompanyController controller;
    // The firebase user
    private FirebaseUser currentUser;

    private CompanyController() {
        currentUser = null;
        // TODO: Check if user is signed in to update invoice and user data.
    }

     /**
     * Returns the instance of the controller for this app.
     * @return Returns the instance of the controller for this app.
     */
    public static CompanyController getInstance() {

        if (controller == null)
            initializeController();

        return controller;
    }

    /**
     * Creates a new instance of the controller.
     *
     */
    private static CompanyController initializeController() {

        controller = new CompanyController();
        return controller;
    }

    @Override
    public boolean isUserConnected() {
        return false;
    }

    @Override
    public boolean changeName(String newName) {
        return false;
    }

    @Override
    public boolean changeLogo(Bitmap newLogo) {
        return false;
    }

    @Override
    public boolean changeEmail(String newEmail) {
        return false;
    }

    @Override
    public boolean changeAddress(String newAddress) {
        return false;
    }

    @Override
    public boolean changeTelephone(String newTelephone) {
        return false;
    }

    @Override
    public boolean changeFax(String newFax) {
        return false;
    }

    @Override
    public void updateInvoices() {
        company.setInvoices(invoiceController.downloadInvoices());
    }

    @Override
    public ArrayList<Invoice> getAllInvoices() {
        return company.getInvoices();
    }

    public InvoiceController getInvoiceController() {
        return invoiceController;
    }

    public Company getCompany() {
        return company;
    }

    /**
     * Set's the company object for the user.
     * @param company       The Company object
     */

    public void setCompany(Company company, Context context) {
        // TODO: Perform networking operations here to upload company to database
        this.company = company;
        invoiceController = new InvoiceController(context, company);
    }

    public void setCurrentUser(FirebaseUser user){
        currentUser = user;
    }
}
