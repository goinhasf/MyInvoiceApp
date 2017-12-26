package com.dev.kiko.myinvoice.model.invoice;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kikogoinhas on 17/12/2017.
 */

public class InvoiceTableAdapter extends RecyclerView.Adapter<InvoiceTableAdapter.InvoiceTableViewHolder> {

    // The invoice to adapt and populate
    private Invoice invoice;
    // The invoice table to populate
    private InvoiceTable table;

    /**
     * The default constructor for this adapter
     * @param invoice       The invoice containing the table to adapt and populate in the recycler view
     */
    public InvoiceTableAdapter(Invoice invoice) {
        this.invoice = invoice;
        table = invoice.getInvoiceTable();

    }

    @Override
    public InvoiceTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(InvoiceTableViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return table.getItemCount();
    }

    /**
     * The view holder class for the InvoiceTableAdapter class.
     */
    public static class InvoiceTableViewHolder extends RecyclerView.ViewHolder {

        public InvoiceTableViewHolder(View itemView) {
            super(itemView);
            // TODO: initialize the views here with findViewByID()
        }
    }

}
