package com.google.android.gms.internal.measurement;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import com.chaquo.python.internal.Common;
import com.google.common.collect.ImmutableList;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzrz {
    private final String zza;
    private String zzb;
    private String zzc;
    private final Account zzd;
    private String zze;
    private final ImmutableList.Builder zzf;

    public /* synthetic */ zzrz(Context r3, byte[] r4) {
        this.zzb = "files";
        this.zzc = Common.ABI_COMMON;
        this.zzd = zzsa.zza;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = ImmutableList.builder();
        if (r3 == null) goto L5;
        boolean r0 = true;
    L6:
        zzsq.zza(r0, "Context cannot be null", new Object[0]);
        this.zza = r3.getPackageName();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public final zzrz zza() {
        zzsa.zzb("directboot-files");
        this.zzb = "directboot-files";
        return this;
    }

    public final zzrz zzb(String r1) {
        zzsa.zzc(r1);
        this.zzc = r1;
        return this;
    }

    public final zzrz zzc(String r2) {
        if (r2.startsWith("/") == false) goto L5;
        r2 = r2.substring(1);
    L5:
        Account r0 = zzsa.zza;
        this.zze = r2;
        return this;
    }

    public final Uri zzd() {
        String r0 = this.zzb;
        String r1 = this.zzc;
        Account r2 = zzrv.zza;
        Account r22 = this.zzd;
        if (r22.type.indexOf(58) != (-1)) goto L5;
        boolean r3 = true;
    L6:
        zzsq.zza(r3, "Account type contains ':'.", new Object[0]);
        if (r22.type.indexOf(47) != (-1)) goto L9;
        boolean r32 = true;
    L10:
        zzsq.zza(r32, "Account type contains '/'.", new Object[0]);
        if (r22.name.indexOf(47) != (-1)) goto L13;
        boolean r33 = true;
    L14:
        zzsq.zza(r33, "Account name contains '/'.", new Object[0]);
        if (zzrv.zza.equals(r22) == false) goto L17;
        String r23 = "shared";
    L18:
        String r34 = this.zze;
        StringBuilder r6 = new StringBuilder((((((String.valueOf(r0).length() + 2) + String.valueOf(r1).length()) + 1) + r23.length()) + 1) + String.valueOf(r34).length());
        r6.append("/");
        r6.append(r0);
        r6.append("/");
        r6.append(r1);
        r6.append("/");
        r6.append(r23);
        r6.append("/");
        r6.append(r34);
        String r02 = r6.toString();
        return new Uri.Builder().scheme("android").authority(this.zza).path(r02).encodedFragment(zzsp.zzb(this.zzf.build())).build();
    L17:
        String r35 = r22.type;
        String r24 = r22.name;
        StringBuilder r7 = new StringBuilder((String.valueOf(r35).length() + 1) + String.valueOf(r24).length());
        r7.append(r35);
        r7.append(":");
        r7.append(r24);
        r23 = r7.toString();
        goto L18
    L13:
        r33 = false;
        goto L14
    L9:
        r32 = false;
        goto L10
    L5:
        r3 = false;
        goto L6
    }
}
