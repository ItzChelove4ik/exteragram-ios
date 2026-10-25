package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@DynamiteApi
/* loaded from: classes4.dex */
public class FlagProviderImpl extends com.google.android.gms.flags.zzd {
    private boolean zza;
    private SharedPreferences zzb;

    public FlagProviderImpl() {
        this.zza = false;
    }

    @Override // com.google.android.gms.flags.zze
    public boolean getBooleanFlagValue(String r1, boolean r2, int r3) {
        if (this.zza == true) goto L5;
        return r2;
    L5:
        SharedPreferences r0 = this.zzb;
        Boolean r22 = Boolean.valueOf(r2);
        r22 = (Boolean) com.google.android.gms.internal.flags.zzd.zza(new zza(r0, r1, r22));     // Catch: Exception -> L8
    L15:
        return r22.booleanValue();
    L8:
        e = move-exception;
        String r02 = String.valueOf(e.getMessage());
        if (r02.length() == 0) goto L12;
        String r03 = "Flag value not available, returning default: ".concat(r02);
    L13:
        Log.w("FlagDataUtils", r03);
        goto L15
    L12:
        r03 = new String("Flag value not available, returning default: ");
        goto L13
    }

    @Override // com.google.android.gms.flags.zze
    public int getIntFlagValue(String r1, int r2, int r3) {
        if (this.zza == true) goto L5;
        return r2;
    L5:
        SharedPreferences r0 = this.zzb;
        Integer r22 = Integer.valueOf(r2);
        r22 = (Integer) com.google.android.gms.internal.flags.zzd.zza(new zzb(r0, r1, r22));     // Catch: Exception -> L8
    L15:
        return r22.intValue();
    L8:
        e = move-exception;
        String r02 = String.valueOf(e.getMessage());
        if (r02.length() == 0) goto L12;
        String r03 = "Flag value not available, returning default: ".concat(r02);
    L13:
        Log.w("FlagDataUtils", r03);
        goto L15
    L12:
        r03 = new String("Flag value not available, returning default: ");
        goto L13
    }

    @Override // com.google.android.gms.flags.zze
    public long getLongFlagValue(String r1, long r2, int r4) {
        if (this.zza == true) goto L5;
        return r2;
    L5:
        SharedPreferences r0 = this.zzb;
        Long r22 = Long.valueOf(r2);
        r22 = (Long) com.google.android.gms.internal.flags.zzd.zza(new zzc(r0, r1, r22));     // Catch: Exception -> L8
    L15:
        return r22.longValue();
    L8:
        e = move-exception;
        String r02 = String.valueOf(e.getMessage());
        if (r02.length() == 0) goto L12;
        String r03 = "Flag value not available, returning default: ".concat(r02);
    L13:
        Log.w("FlagDataUtils", r03);
        goto L15
    L12:
        r03 = new String("Flag value not available, returning default: ");
        goto L13
    }

    @Override // com.google.android.gms.flags.zze
    public String getStringFlagValue(String r1, String r2, int r3) {
        if (this.zza == true) goto L15;
        return r2;
    L15:
        return (String) com.google.android.gms.internal.flags.zzd.zza(new zzd(this.zzb, r1, r2));
    L8:
        e = move-exception;
        String r0 = String.valueOf(e.getMessage());
        if (r0.length() == 0) goto L12;
        String r02 = "Flag value not available, returning default: ".concat(r0);
    L13:
        Log.w("FlagDataUtils", r02);
        return r2;
    L12:
        r02 = new String("Flag value not available, returning default: ");
        goto L13
    }

    @Override // com.google.android.gms.flags.zze
    public void init(IObjectWrapper r3) {
        Context r32 = (Context) ObjectWrapper.unwrap(r3);
        if (this.zza == true) goto L17;
        this.zzb = zzf.zza(r32.createPackageContext("com.google.android.gms", 0));     // Catch: Exception -> L7 PackageManager.NameNotFoundException -> L14
        this.zza = true;     // Catch: Exception -> L7 PackageManager.NameNotFoundException -> L14
        return;
    L7:
        e = move-exception;
        String r2 = String.valueOf(e.getMessage());
        if (r2.length() == 0) goto L11;
        String r22 = "Could not retrieve sdk flags, continuing with defaults: ".concat(r2);
    L12:
        Log.w("FlagProviderImpl", r22);
        return;
    L11:
        r22 = new String("Could not retrieve sdk flags, continuing with defaults: ");
    L18:
        return;
    }
}
