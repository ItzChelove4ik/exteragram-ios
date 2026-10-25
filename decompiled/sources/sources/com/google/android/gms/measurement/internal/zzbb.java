package com.google.android.gms.measurement.internal;

import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class zzbb extends zzjf {
    private long zza;
    private String zzb;

    public zzbb(zzic r1) {
        super(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzjf
    public final boolean zza() {
        Calendar r0 = Calendar.getInstance();
        this.zza = (r0.get(15) + r0.get(16)) / 60000;
        Locale r02 = Locale.getDefault();
        String r1 = r02.getLanguage();
        Locale r2 = Locale.ENGLISH;
        String r12 = r1.toLowerCase(r2);
        String r03 = r02.getCountry().toLowerCase(r2);
        StringBuilder r4 = new StringBuilder((String.valueOf(r12).length() + 1) + String.valueOf(r03).length());
        r4.append(r12);
        r4.append("-");
        r4.append(r03);
        this.zzb = r4.toString();
        return false;
    }

    public final long zzb() {
        zzw();
        return this.zza;
    }

    public final String zzc() {
        zzw();
        return this.zzb;
    }
}
