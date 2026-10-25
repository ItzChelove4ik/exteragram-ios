package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public final class zaab extends zap {
    private final ArraySet zad;
    private final GoogleApiManager zae;

    public zaab(LifecycleFragment r1, GoogleApiManager r2, GoogleApiAvailability r3) {
        super(r1, r3);
        this.zad = new ArraySet();
        this.zae = r2;
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    public static void zaa(Activity r2, GoogleApiManager r3, ApiKey r4) {
        LifecycleFragment r22 = LifecycleCallback.getFragment(r2);
        zaab r0 = (zaab) r22.getCallbackOrNull("ConnectionlessLifecycleHelper", zaab.class);
        if (r0 != null) goto L5;
        r0 = new zaab(r22, r3, GoogleApiAvailability.getInstance());
    L5:
        Preconditions.checkNotNull(r4, "ApiKey cannot be null");
        r0.zad.add(r4);
        r3.zae(r0);
    }

    private final void zac() {
        if (this.zad.isEmpty() == true) goto L6;
        this.zae.zae(this);
        return;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onResume() {
        super.onResume();
        zac();
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStart() {
        super.onStart();
        zac();
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        super.onStop();
        this.zae.zaf(this);
    }

    public final ArraySet zab() {
        return this.zad;
    }

    @Override // com.google.android.gms.common.api.internal.zap
    public final void zad(ConnectionResult r1, int r2) {
        this.zae.zaq(r1, r2);
    }

    @Override // com.google.android.gms.common.api.internal.zap
    public final void zae() {
        this.zae.zai();
    }
}
