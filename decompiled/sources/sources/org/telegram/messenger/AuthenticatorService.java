package org.telegram.messenger;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

/* loaded from: classes5.dex */
public class AuthenticatorService extends Service {
    private static Authenticator authenticator;

    public static class Authenticator extends AbstractAccountAuthenticator {
        public Authenticator(Context r1) {
            super(r1);
        }

        @Override // android.accounts.AbstractAccountAuthenticator
        public Bundle addAccount(AccountAuthenticatorResponse r1, String r2, String r3, String[] r4, Bundle r5) {
            return null;
        }

        @Override // android.accounts.AbstractAccountAuthenticator
        public Bundle confirmCredentials(AccountAuthenticatorResponse r1, Account r2, Bundle r3) {
            return null;
        }

        @Override // android.accounts.AbstractAccountAuthenticator
        public Bundle editProperties(AccountAuthenticatorResponse r1, String r2) {
            return null;
        }

        @Override // android.accounts.AbstractAccountAuthenticator
        public Bundle getAccountRemovalAllowed(AccountAuthenticatorResponse r1, Account r2) {
            return super.getAccountRemovalAllowed(r1, r2);
        }

        @Override // android.accounts.AbstractAccountAuthenticator
        public Bundle getAuthToken(AccountAuthenticatorResponse r1, Account r2, String r3, Bundle r4) {
            return null;
        }

        @Override // android.accounts.AbstractAccountAuthenticator
        public String getAuthTokenLabel(String r1) {
            return null;
        }

        @Override // android.accounts.AbstractAccountAuthenticator
        public Bundle hasFeatures(AccountAuthenticatorResponse r1, Account r2, String[] r3) {
            return null;
        }

        @Override // android.accounts.AbstractAccountAuthenticator
        public Bundle updateCredentials(AccountAuthenticatorResponse r1, Account r2, String r3, Bundle r4) {
            return null;
        }
    }

    static {
    }

    public AuthenticatorService() {
    }

    public Authenticator getAuthenticator() {
        if (authenticator != null) goto L6;
        authenticator = new Authenticator(this);
    L6:
        return authenticator;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r2) {
        if (r2.getAction().equals("android.accounts.AccountAuthenticator") == true) goto L5;
        return null;
    L5:
        return getAuthenticator().getIBinder();
    }
}
