package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzdp extends zzeo {
    final /* synthetic */ Context zza;
    final /* synthetic */ Bundle zzb;
    final /* synthetic */ zzez zzc;

    public zzdp(zzez r1, Context r2, Bundle r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        Context r4 = this.zza;     // Catch: Exception -> L6
        Preconditions.checkNotNull(r4);     // Catch: Exception -> L6
        com.google.firebase.analytics.zzg.zza().zzb();     // Catch: Exception -> L6
        String r5 = com.google.android.gms.measurement.internal.zzhu.zza(r4);     // Catch: Exception -> L6
        Preconditions.checkNotNull(r4);     // Catch: Exception -> L6
        Resources r7 = r4.getResources();     // Catch: Exception -> L6
        if (TextUtils.isEmpty(r5) == false) goto L8;
        r5 = com.google.android.gms.measurement.internal.zzhu.zza(r4);     // Catch: Exception -> L6
    L8:
        Boolean r52 = com.google.android.gms.measurement.internal.zzhu.zzc("google_analytics_force_disable_updates", r7, r5);     // Catch: Exception -> L6
        zzez r6 = this.zzc;     // Catch: Exception -> L6
        if (r52 != null) goto L11;
    L12:
        boolean r72 = true;
    L14:
        r6.zzT(r6.zzc(r4, r72));     // Catch: Exception -> L6
        if (r6.zzS() != null) goto L18;
        Log.w(r6.zzQ(), "Failed to connect to measurement client.");     // Catch: Exception -> L6
        return;
    L18:
        int r73 = DynamiteModule.getLocalVersion(r4, ModuleDescriptor.MODULE_ID);     // Catch: Exception -> L6
        int r0 = DynamiteModule.getRemoteVersion(r4, ModuleDescriptor.MODULE_ID);     // Catch: Exception -> L6
        int r8 = Math.max(r73, r0);     // Catch: Exception -> L6
        if (Boolean.TRUE.equals(r52) == true) goto L21;
        if (r0 < r73) goto L21;
        boolean r14 = false;
    L23:
        long r12 = r8;
        r6.zzU(r12);     // Catch: Exception -> L6
        zzdb r17 = new zzdb(161000, r12, r14, this.zzb, com.google.android.gms.measurement.internal.zzhu.zza(r4));     // Catch: Exception -> L6
        if (r6.zzO() == false) goto L27;
        ((zzcp) Preconditions.checkNotNull(r6.zzS())).initializeWithElapsedTime(ObjectWrapper.wrap(r4), r17, this.zzi, this.zzj);     // Catch: Exception -> L6
        return;
    L27:
        ((zzcp) Preconditions.checkNotNull(r6.zzS())).initialize(ObjectWrapper.wrap(r4), r17, this.zzi);     // Catch: Exception -> L6
        return;
    L21:
        r14 = true;
        goto L23
    L11:
        if (r52.booleanValue() == false) goto L12;
        r72 = false;
    L6:
        e = move-exception;
        this.zzc.zzN(e, true, false);
    }
}
