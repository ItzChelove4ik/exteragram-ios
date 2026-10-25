package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;

/* loaded from: classes5.dex */
public final class zzsp {
    private final String zza;
    private final zzaer zzb;
    private final zzsn zzc;
    private final zzso zzd;
    private final Integer zze;

    private zzsp(String r1, zzaer r2, zzsn r3, zzso r4, Integer r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    public static zzsp zza(String r6, zzaer r7, zzsn r8, zzso r9, Integer r10) {
        if (r10 != null) goto L5;
    L9:
        int r1 = 0;
        if (r10 != null) goto L17;
        if (r9.equals(zzso.zzd) == true) goto L17;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot have null idRequirement unless OutputPrefixType is RAW");
        return null;
    L17:
        if (r1 >= r6.length()) goto L26;
        char r0 = r6.charAt(r1);
        if (r0 < '!') goto L23;
        if (r0 > '~') goto L23;
        r1 = r1 + 1;
    L23:
        int r82 = r6.length();
        StringBuilder r92 = new StringBuilder((r82 + 50) + String.valueOf(r1).length());
        r92.append("typeURL ");
        r92.append(r6);
        r92.append(" contains non-ascii character at position ");
        r92.append(r1);
        throw new GeneralSecurityException(r92.toString());
    L26:
        return new zzsp(r6, r7, r8, r9, r10);
    L5:
        if (r9.equals(zzso.zzd) == false) goto L9;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot set idRequirement for OutputPrefixType RAW");
        return null;
    }

    public final String zzb() {
        return this.zza;
    }

    public final zzaer zzc() {
        return this.zzb;
    }

    public final zzsn zzd() {
        return this.zzc;
    }

    public final zzso zze() {
        return this.zzd;
    }

    public final Integer zzf() {
        return this.zze;
    }
}
