package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzd extends Fragment implements LifecycleFragment {
    private static final WeakHashMap zza = null;
    private final zzc zzb;

    static {
        zza = new WeakHashMap();
    }

    public zzd() {
        this.zzb = new zzc();
    }

    public static zzd zza(FragmentActivity r5) {
        FragmentManager r1 = r5.getSupportFragmentManager();
        WeakHashMap r2 = zza;
        WeakReference r3 = (WeakReference) r2.get(r5);
        if (r3 == null) goto L17;
        zzd r32 = (zzd) r3.get();
        if (r32 == null) goto L17;
        return r32;
    L17:
        zzd r33 = (zzd) r1.findFragmentByTag("SLifecycleFragmentImpl");     // Catch: ClassCastException -> L14
        if (r33 != null) goto L10;
    L11:
        r33 = new zzd();
        r1.beginTransaction().add(r33, "SLifecycleFragmentImpl").commitAllowingStateLoss();
    L12:
        r2.put(r5, new WeakReference(r33));
        return r33;
    L10:
        if (r33.isRemoving() == false) goto L12;
    L14:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Fragment with tag SLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
        return null;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void addCallback(String r1, LifecycleCallback r2) {
        this.zzb.zzb(r1, r2);
    }

    @Override // androidx.fragment.app.Fragment
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

    @Override // androidx.fragment.app.Fragment
    public final void onActivityResult(int r1, int r2, Intent r3) {
        super.onActivityResult(r1, r2, r3);
        this.zzb.zzh(r1, r2, r3);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle r1) {
        super.onCreate(r1);
        this.zzb.zze(r1);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.zzb.zzk();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zzb.zzg();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle r1) {
        super.onSaveInstanceState(r1);
        this.zzb.zzi(r1);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStart() {
        super.onStart();
        this.zzb.zzf();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        this.zzb.zzj();
    }
}
