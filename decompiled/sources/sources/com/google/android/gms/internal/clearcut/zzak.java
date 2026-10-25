package com.google.android.gms.internal.clearcut;

import android.content.SharedPreferences;
import android.util.Log;

/* loaded from: classes4.dex */
final class zzak extends zzae<String> {
    public zzak(zzao r2, String r3, String r4) {
        super(r2, r3, r4, null);
    }

    private final String zzc(SharedPreferences r4) {
        return r4.getString(this.zzds, null);
    L5:
        e = move-exception;
        String r3 = String.valueOf(this.zzds);
        if (r3.length() == 0) goto L9;
        String r32 = "Invalid string value in SharedPreferences for ".concat(r3);
    L10:
        Log.e("PhenotypeFlag", r32, e);
        return null;
    L9:
        r32 = new String("Invalid string value in SharedPreferences for ");
        goto L10
    }

    @Override // com.google.android.gms.internal.clearcut.zzae
    public final /* synthetic */ String zza(SharedPreferences r1) {
        return zzc(r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzae
    public final /* synthetic */ String zzb(String r1) {
        return r1;
    }
}
