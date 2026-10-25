package com.google.android.gms.auth.api.signin;

import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

/* loaded from: classes4.dex */
final /* synthetic */ class zaa implements Comparator {
    static final /* synthetic */ zaa zaa = null;

    static {
        zaa = new zaa();
    }

    private /* synthetic */ zaa() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object r1, Object r2) {
        Parcelable.Creator<GoogleSignInAccount> r0 = GoogleSignInAccount.CREATOR;
        return ((Scope) r1).getScopeUri().compareTo(((Scope) r2).getScopeUri());
    }
}
