package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

/* loaded from: classes4.dex */
final class zac implements Comparator {
    public zac() {
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object r1, Object r2) {
        return ((Scope) r1).getScopeUri().compareTo(((Scope) r2).getScopeUri());
    }
}
