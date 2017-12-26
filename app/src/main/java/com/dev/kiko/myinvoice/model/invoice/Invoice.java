package com.dev.kiko.myinvoice.model.invoice;

import java.util.ArrayList;

/**
 * Created by kikogoinhas on 15/12/2017.
 */

public class Invoice {

    public enum InvoiceType{
        OUTSTANDING,
        PAID,
        ESTIMATE;
    }
    // The table used to store item data
    private InvoiceTable invoiceTable;
    // Destination contact
    private Contact destinationContact;
    // The type of invoice
    private InvoiceType invoiceType;

    /**
     * Default Constructor for a new empty Invoice
     */
    public Invoice() {

        invoiceTable = new InvoiceTable();
    }

    /**
     * This constructor creates an invoice given an existing contact
     * @param destination   The contact which the invoice is directed to.
     * @param type  The type of invoice. It's either OUTSTANDING, PAID or ESTIMATE.
     */
    public Invoice(Contact destination, InvoiceType type) {

        destinationContact = destination;
        invoiceType = type;
        invoiceTable = new InvoiceTable();
    }

    /**
     * Gets the invoice table of this invoice
     * @return  Returns the invoice table of this invoice.
     */
    public InvoiceTable getInvoiceTable() {
        return invoiceTable;
    }
}
