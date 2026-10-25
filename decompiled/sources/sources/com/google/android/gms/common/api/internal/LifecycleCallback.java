package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes4.dex */
public abstract class LifecycleCallback {
    protected final LifecycleFragment mLifecycleFragment;

    public LifecycleCallback(LifecycleFragment r1) {
        this.mLifecycleFragment = r1;
    }

    public static LifecycleFragment getFragment(Activity r1) {
        return getFragment(new LifecycleActivity(r1));
    }

    public static LifecycleFragment getFragment(LifecycleActivity r1) {
        if (r1.zza() == false) goto L7;
        return zzd.zza(r1.zzd());
    L7:
        if (r1.zzb() == true) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Can't get fragment for unexpected activity.");
        return null;
    L9:
        return zza.zza(r1.zzc());
    }

    public void dump(String r1, FileDescriptor r2, PrintWriter r3, String[] r4) {
    }

    public Activity getActivity() {
        Activity r0 = this.mLifecycleFragment.getLifecycleActivity();
        Preconditions.checkNotNull(r0);
        return r0;
    }

    public void onActivityResult(int r1, int r2, Intent r3) {
    }

    public void onCreate(Bundle r1) {
    }

    public void onDestroy() {
    }

    public void onResume() {
    }

    public void onSaveInstanceState(Bundle r1) {
    }

    public void onStart() {
    }

    public void onStop() {
    }
}
