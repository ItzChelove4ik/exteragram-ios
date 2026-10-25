package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.util.UidVerifier;

/* loaded from: classes4.dex */
public final class zbt extends zbo {
    private final Context zba;

    public zbt(Context r1) {
        this.zba = r1;
    }

    private final void zbd() {
        if (UidVerifier.isGooglePlayServicesUid(this.zba, Binder.getCallingUid()) == false) goto L5;
        return;
    L5:
        int r0 = Binder.getCallingUid();
        StringBuilder r2 = new StringBuilder(String.valueOf(r0).length() + 41);
        r2.append("Calling UID ");
        r2.append(r0);
        r2.append(" is not Google Play services.");
        throw new SecurityException(r2.toString());
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zbp
    public final void zbb() {
        zbd();
        Context r3 = this.zba;
        Storage r0 = Storage.getInstance(r3);
        GoogleSignInAccount r1 = r0.getSavedDefaultGoogleSignInAccount();
        GoogleSignInOptions r2 = GoogleSignInOptions.DEFAULT_SIGN_IN;
        if (r1 == null) goto L5;
        r2 = r0.getSavedDefaultGoogleSignInOptions();
    L5:
        GoogleSignInClient r32 = GoogleSignIn.getClient(r3, r2);
        if (r1 == null) goto L9;
        r32.revokeAccess();
        return;
    L9:
        r32.signOut();
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zbp
    public final void zbc() {
        zbd();
        zbn.zba(this.zba).zbb();
    }
}
