package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes4.dex */
public final class zaaa {
    private final Map zaa;
    private final Map zab;

    public zaaa() {
        this.zaa = Collections.synchronizedMap(new WeakHashMap());
        this.zab = Collections.synchronizedMap(new WeakHashMap());
    }

    private final void zah(boolean r4, Status r5) {
        Map r0 = this.zaa;
        monitor-enter(r0);
        HashMap r1 = new HashMap(r0);     // Catch: Throwable -> L30
        monitor-exit(r0);     // Catch: Throwable -> L30
        Map r3 = this.zab;
        monitor-enter(r3);
        HashMap r02 = new HashMap(r3);     // Catch: Throwable -> L27
        monitor-exit(r3);     // Catch: Throwable -> L27
        Iterator r32 = r1.entrySet().iterator();
    L12:
        if (r32.hasNext() == false) goto L18;
        Map.Entry r12 = (Map.Entry) r32.next();
        if (r4 == true) goto L17;
        if (((Boolean) r12.getValue()).booleanValue() == false) goto L12;
    L17:
        ((BasePendingResult) r12.getKey()).forceFailureUnlessReady(r5);
        goto L12
    L18:
        Iterator r33 = r02.entrySet().iterator();
    L20:
        if (r33.hasNext() == false) goto L26;
        Map.Entry r03 = (Map.Entry) r33.next();
        if (r4 == true) goto L25;
        if (((Boolean) r03.getValue()).booleanValue() == false) goto L20;
    L25:
        ((TaskCompletionSource) r03.getKey()).trySetException(new ApiException(r5));
        goto L20
    L26:
        return;
    L27:
        th = move-exception;
        throw th;
    L30:
        th = move-exception;
        throw th;
    }

    public final void zaa(BasePendingResult r2, boolean r3) {
        Boolean r32 = Boolean.valueOf(r3);
        this.zaa.put(r2, r32);
        r2.addStatusListener(new zay(this, r2));
    }

    public final void zab(TaskCompletionSource r2, boolean r3) {
        Boolean r32 = Boolean.valueOf(r3);
        this.zab.put(r2, r32);
        r2.getTask().addOnCompleteListener(new zaz(this, r2));
    }

    public final boolean zac() {
        if (this.zaa.isEmpty() == true) goto L5;
        return true;
    L5:
        if (this.zab.isEmpty() == false) goto L11;
        return false;
    L11:
        return true;
    }

    public final void zad() {
        zah(false, GoogleApiManager.zaa);
    }

    public final void zae(int r4, String r5) {
        StringBuilder r0 = new StringBuilder("The connection to Google Play services was lost");
        if (r4 != 1) goto L6;
        r0.append(" due to service disconnection.");
    L8:
        if (r5 == null) goto L10;
        r0.append(" Last reason for disconnect: ");
        r0.append(r5);
    L10:
        zah(true, new Status(20, r0.toString()));
        return;
    L6:
        if (r4 != 3) goto L8;
        r0.append(" due to dead object exception.");
        goto L8
    }

    public final /* synthetic */ Map zaf() {
        return this.zaa;
    }

    public final /* synthetic */ Map zag() {
        return this.zab;
    }
}
