package com.dev.kiko.myinvoice.manager;

import android.graphics.Bitmap;

import com.dev.kiko.myinvoice.model.invoice.Invoice;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by kikogoinhas on 17/12/2017.
 */

public class MasterManager {

    public interface InvoiceManager {

        void scheduleInvoice();
        boolean saveInvoice();
        boolean sendInvoice();
        boolean createInvoice();
        boolean deleteInvoice();
        ArrayList<Invoice> downloadInvoices();

    }

    public interface MyInvoiceUserManager {

        void signIn(FirebaseUser user);
        void signOut(FirebaseUser user);
        boolean isUserConnected(FirebaseUser user);
        void createNewAccount(String email, String password);

    }

    public interface CompanyManager {

        boolean isUserConnected();
        boolean changeName(String newName);
        boolean changeLogo(Bitmap newLogo);
        boolean changeEmail(String newEmail);
        boolean changeAddress(String newAddress);
        boolean changeTelephone(String newTelephone);
        boolean changeFax(String newFax);
        void updateInvoices();
        ArrayList<Invoice> getAllInvoices();

    }


}
