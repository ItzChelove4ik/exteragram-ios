package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes4.dex */
public final class zak extends zap {
    private final SparseArray zad;

    private zak(LifecycleFragment r2) {
        super(r2, GoogleApiAvailability.getInstance());
        this.zad = new SparseArray();
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public static zak zaa(LifecycleActivity r2) {
        LifecycleFragment r22 = LifecycleCallback.getFragment(r2);
        zak r0 = (zak) r22.getCallbackOrNull("AutoManageHelper", zak.class);
        if (r0 == null) goto L6;
        return r0;
    L6:
        return new zak(r22);
    }

    private final zaj zai(int r2) {
        SparseArray r1 = this.zad;
        if (r1.size() > r2) goto L7;
        return null;
    L7:
        return (zaj) r1.get(r1.keyAt(r2));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void dump(String r5, FileDescriptor r6, PrintWriter r7, String[] r8) {
        int r0 = 0;
    L4:
        if (r0 >= this.zad.size()) goto L9;
        zaj r1 = zai(r0);
        if (r1 == null) goto L8;
        r7.append(r5).append("GoogleApiClient #").print(r1.zaa);
        r7.println(":");
        String r2 = String.valueOf(r5);
        r1.zab.dump(r2.concat("  "), r6, r7, r8);
    L8:
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStart() {
        super.onStart();
        SparseArray r0 = this.zad;
        boolean r1 = this.zaa;
        String r2 = String.valueOf(r0);
        int r3 = String.valueOf(r1).length();
        int r32 = r3 + 9;
        StringBuilder r5 = new StringBuilder(r32 + r2.length());
        r5.append("onStart ");
        r5.append(r1);
        r5.append(" ");
        r5.append(r2);
        Log.d("AutoManageHelper", r5.toString());
        if (this.zab.get() != null) goto L11;
        int r12 = 0;
    L6:
        if (r12 >= r0.size()) goto L15;
        zaj r22 = zai(r12);
        if (r22 == null) goto L10;
        r22.zab.connect();
    L10:
        r12 = r12 + 1;
        goto L6
    L15:
        return;
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        super.onStop();
        int r0 = 0;
    L4:
        if (r0 >= this.zad.size()) goto L9;
        zaj r1 = zai(r0);
        if (r1 == null) goto L8;
        r1.zab.disconnect();
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public final void zab(int r9, GoogleApiClient r10, GoogleApiClient.OnConnectionFailedListener r11) {
        Preconditions.checkNotNull(r10, "GoogleApiClient instance cannot be null");
        SparseArray r0 = this.zad;
        int r1 = r0.indexOfKey(r9);
        StringBuilder r3 = new StringBuilder(String.valueOf(r9).length() + 43);
        r3.append("Already managing a GoogleApiClient with id ");
        r3.append(r9);
        String r2 = r3.toString();
        if (r1 >= 0) goto L5;
        boolean r12 = true;
    L6:
        Preconditions.checkState(r12, r2);
        zam r13 = (zam) this.zab.get();
        boolean r22 = this.zaa;
        String r4 = String.valueOf(r13);
        int r5 = String.valueOf(r9).length();
        StringBuilder r32 = new StringBuilder((((r5 + 32) + String.valueOf(r22).length()) + 1) + r4.length());
        r32.append("starting AutoManage for client ");
        r32.append(r9);
        r32.append(" ");
        r32.append(r22);
        r32.append(" ");
        r32.append(r4);
        Log.d("AutoManageHelper", r32.toString());
        zaj r23 = new zaj(this, r9, r10, r11);
        r10.registerConnectionFailedListener(r23);
        r0.put(r9, r23);
        if (this.zaa == false) goto L11;
        if (r13 != null) goto L12;
        Log.d("AutoManageHelper", "connecting ".concat(r10.toString()));
        r10.connect();
        return;
    L12:
        return;
    L11:
        return;
    L5:
        r12 = false;
        goto L6
    }

    public final void zac(int r2) {
        SparseArray r1 = this.zad;
        zaj r0 = (zaj) r1.get(r2);
        r1.remove(r2);
        if (r0 == null) goto L6;
        GoogleApiClient r12 = r0.zab;
        r12.unregisterConnectionFailedListener(r0);
        r12.disconnect();
        return;
    }

    @Override // com.google.android.gms.common.api.internal.zap
    public final void zad(ConnectionResult r3, int r4) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (r4 >= 0) goto L6;
        Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
        return;
    L6:
        zaj r0 = (zaj) this.zad.get(r4);
        if (r0 == null) goto L12;
        zac(r4);
        GoogleApiClient.OnConnectionFailedListener r2 = r0.zac;
        if (r2 == null) goto L13;
        r2.onConnectionFailed(r3);
        return;
    L13:
        return;
    }

    @Override // com.google.android.gms.common.api.internal.zap
    public final void zae() {
        int r0 = 0;
    L4:
        if (r0 >= this.zad.size()) goto L9;
        zaj r1 = zai(r0);
        if (r1 == null) goto L8;
        r1.zab.connect();
    L8:
        r0 = r0 + 1;
        goto L4
    }
}
