package com.google.android.gms.common.api.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.common.zzg;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzc {
    private final Map zza;
    private int zzb;
    private Bundle zzc;

    public zzc() {
        this.zza = Collections.synchronizedMap(new ArrayMap());
        this.zzb = 0;
    }

    public final LifecycleCallback zza(String r1, Class r2) {
        return (LifecycleCallback) r2.cast(this.zza.get(r1));
    }

    public final void zzb(String r3, LifecycleCallback r4) {
        Map r0 = this.zza;
        if (r0.containsKey(r3) == true) goto L8;
        r0.put(r3, r4);
        if (this.zzb <= 0) goto L10;
        new zzg(Looper.getMainLooper()).post(new zzb(this, r4, r3));
        return;
    L10:
        return;
    L8:
        StringBuilder r02 = new StringBuilder(String.valueOf(r3).length() + 59);
        r02.append("LifecycleCallback with tag ");
        r02.append(r3);
        r02.append(" already added to this fragment.");
        throw new IllegalArgumentException(r02.toString());
    }

    public final void zze(Bundle r3) {
        this.zzb = 1;
        this.zzc = r3;
        Iterator r2 = this.zza.entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L10;
        Map.Entry r0 = (Map.Entry) r2.next();
        LifecycleCallback r1 = (LifecycleCallback) r0.getValue();
        if (r3 == null) goto L8;
        Bundle r02 = r3.getBundle((String) r0.getKey());
    L9:
        r1.onCreate(r02);
        goto L4
    L8:
        r02 = null;
        goto L9
    }

    public final void zzf() {
        this.zzb = 2;
        Iterator r1 = this.zza.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((LifecycleCallback) r1.next()).onStart();
        goto L4
    }

    public final void zzg() {
        this.zzb = 3;
        Iterator r1 = this.zza.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((LifecycleCallback) r1.next()).onResume();
        goto L4
    }

    public final void zzh(int r2, int r3, Intent r4) {
        Iterator r1 = this.zza.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((LifecycleCallback) r1.next()).onActivityResult(r2, r3, r4);
        goto L4
    }

    public final void zzi(Bundle r4) {
        if (r4 == null) goto L8;
        Iterator r3 = this.zza.entrySet().iterator();
    L6:
        if (r3.hasNext() == false) goto L10;
        Map.Entry r0 = (Map.Entry) r3.next();
        Bundle r1 = new Bundle();
        ((LifecycleCallback) r0.getValue()).onSaveInstanceState(r1);
        r4.putBundle((String) r0.getKey(), r1);
        goto L6
    L10:
        return;
    }

    public final void zzj() {
        this.zzb = 4;
        Iterator r1 = this.zza.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((LifecycleCallback) r1.next()).onStop();
        goto L4
    }

    public final void zzk() {
        this.zzb = 5;
        Iterator r1 = this.zza.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((LifecycleCallback) r1.next()).onDestroy();
        goto L4
    }

    public final void zzl(String r2, FileDescriptor r3, PrintWriter r4, String[] r5) {
        Iterator r1 = this.zza.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((LifecycleCallback) r1.next()).dump(r2, r3, r4, r5);
        goto L4
    }

    public final /* synthetic */ int zzm() {
        return this.zzb;
    }

    public final /* synthetic */ Bundle zzn() {
        return this.zzc;
    }
}
