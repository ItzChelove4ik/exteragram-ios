package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zza extends Fragment implements LifecycleFragment {
    private static final WeakHashMap zza = null;
    private final zzc zzb;

    static {
        zza = new WeakHashMap();
    }

    public zza() {
        this.zzb = new zzc();
    }

    public static zza zza(Activity r4) {
        WeakHashMap r1 = zza;
        WeakReference r2 = (WeakReference) r1.get(r4);
        if (r2 == null) goto L18;
        zza r22 = (zza) r2.get();
        if (r22 == null) goto L18;
        return r22;
    L18:
        zza r23 = (zza) r4.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");     // Catch: ClassCastException -> L15
        if (r23 != null) goto L11;
    L12:
        r23 = new zza();
        r4.getFragmentManager().beginTransaction().add(r23, "LifecycleFragmentImpl").commitAllowingStateLoss();
    L13:
        r1.put(r4, new WeakReference(r23));
        return r23;
    L11:
        if (r23.isRemoving() == false) goto L13;
    L15:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", e);
        return null;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void addCallback(String r1, LifecycleCallback r2) {
        this.zzb.zzb(r1, r2);
    }

    @Override // android.app.Fragment
    public final void dump(String r1, FileDescriptor r2, PrintWriter r3, String[] r4) {
        super.dump(r1, r2, r3, r4);
        this.zzb.zzl(r1, r2, r3, r4);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final <T extends LifecycleCallback> T getCallbackOrNull(String r1, Class<T> r2) {
        return (T) this.zzb.zza(r1, r2);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final Activity getLifecycleActivity() {
        return getActivity();
    }

    @Override // android.app.Fragment
    public final void onActivityResult(int r1, int r2, Intent r3) {
        super.onActivityResult(r1, r2, r3);
        this.zzb.zzh(r1, r2, r3);
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle r1) {
        super.onCreate(r1);
        this.zzb.zze(r1);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.zzb.zzk();
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zzb.zzg();
    }

    @Override // android.app.Fragment
    public final void onSaveInstanceState(Bundle r1) {
        super.onSaveInstanceState(r1);
        this.zzb.zzi(r1);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        this.zzb.zzf();
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        this.zzb.zzj();
    }
}
