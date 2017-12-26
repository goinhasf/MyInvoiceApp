package com.dev.kiko.myinvoice.model.invoice;

/**
 * Created by kikogoinhas on 17/12/2017.
 */

import java.util.ArrayList;

/**
 * This class will be used to store data about the items items included in the invoice.
 */
public class InvoiceTable {

    // A list containing the items in the invoice
    private ArrayList<InvoiceItem> items;


    /**
     * Default Constructor
     *
     */

    public InvoiceTable() {

        items = new ArrayList<>();
    }

    /**
     * Adds an item to the table
     * @param item      The item to be added
     * @return          Returns true if added successfully and false if otherwise.
     */
    public boolean addItem(InvoiceItem item) {
        return items.add(item);
    }

    /**
     * Deletes an item from the invoice table
     * @param item      The item to be deleted
     * @return          Returns true if deleted successfully and false if otherwise.
     */
    public boolean deleteItem(InvoiceItem item) {
        return items.remove(item);
    }

    /**
     * Returns the item at the specified position
     * @param index     The index of the item in the list.
     * @return
     */
    public InvoiceItem getItem(int index) {
        return items.get(index);
    }

    /**
     * Returns the number of items in the table.
     * @return          Returns the number of items in the table.
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * This class represents the items in the invoice table
     */
    private class InvoiceItem {

        // The number of items
        private int quantity;
        // The description of the item
        private String description;
        // The price of a single item
        private double unitPrice;


        /**
         *
         * @param quantity      The number of items of this description
         * @param description   The description of the item
         * @param unitPrice     The price of each item
         */
        public InvoiceItem(int quantity, String description, double unitPrice) {

            this.quantity = quantity;
            this.description = description;
            this.unitPrice = unitPrice;

        }

        /**
         * Returns the total price of the invoice item
         * @return returns the total price of the invoice item.
         */
        public double total(){
            return quantity*unitPrice;
        }


    }

}
