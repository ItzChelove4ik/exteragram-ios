package com.google.android.gms.internal.clearcut;

import android.content.SharedPreferences;
import android.util.Log;

/* loaded from: classes4.dex */
final class zzaj extends zzae<Boolean> {
    public zzaj(zzao r2, String r3, Boolean r4) {
        super(r2, r3, r4, null);
    }

    private final Boolean zzb(SharedPreferences r3) {
        return Boolean.valueOf(r3.getBoolean(this.zzds, false));
    L4:
        e = move-exception;
        String r2 = String.valueOf(this.zzds);
        if (r2.length() == 0) goto L8;
        String r22 = "Invalid boolean value in SharedPreferences for ".concat(r2);
    L9:
        Log.e("PhenotypeFlag", r22, e);
        return null;
    L8:
        r22 = new String("Invalid boolean value in SharedPreferences for ");
        goto L9
    }

    @Override // com.google.android.gms.internal.clearcut.zzae
    public final /* synthetic */ Boolean zza(SharedPreferences r1) {
        return zzb(r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzae
    public final /* synthetic */ Boolean zzb(String r3) {
        if (zzy.zzcr.matcher(r3).matches() == false) goto L7;
        return Boolean.TRUE;
    L7:
        if (zzy.zzcs.matcher(r3).matches() == true) goto L9;
        String r2 = this.zzds;
        StringBuilder r1 = new StringBuilder((String.valueOf(r2).length() + 28) + String.valueOf(r3).length());
        r1.append("Invalid boolean value for ");
        r1.append(r2);
        r1.append(": ");
        r1.append(r3);
        Log.e("PhenotypeFlag", r1.toString());
        return null;
    L9:
        return Boolean.FALSE;
    }
}
