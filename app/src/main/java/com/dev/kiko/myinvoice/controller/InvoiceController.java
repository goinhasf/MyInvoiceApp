package com.dev.kiko.myinvoice.controller;

import android.content.Context;

import com.dev.kiko.myinvoice.manager.MasterManager;
import com.dev.kiko.myinvoice.model.Company;
import com.dev.kiko.myinvoice.model.invoice.Invoice;

import java.util.ArrayList;

/**
 * Created by kikogoinhas on 17/12/2017.
 */

/**
 * This class models an object that controls all transactions between the model and the Activity's UI
 */
public class InvoiceController implements MasterManager.InvoiceManager {


    // The context
    private Context mContext;
    // The company object containing the invoices
    private Company company;

    /**
     * Default Constructor
     * @param context   The context of this application
     * @param company   The company object containing the invoices.
     */
    public InvoiceController(Context context, Company company) {
        mContext = context;
        this.company = company;
    }

    @Override
    public void scheduleInvoice() {

    }

    @Override
    public boolean saveInvoice() {
        return false;
    }

    @Override
    public boolean sendInvoice() {
        return false;
    }

    @Override
    public boolean createInvoice() {
        return false;
    }

    @Override
    public boolean deleteInvoice() {
        return false;
    }

    @Override
    public ArrayList<Invoice> downloadInvoices() {
        return null;
    }
}
