package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.HashMap;

/* loaded from: classes5.dex */
public final class zzou extends zzol {
    public zzou(zzpg r1) {
        super(r1);
    }

    private final String zzd(String r5) {
        String r4 = this.zzg.zzh().zzc(r5);
        if (TextUtils.isEmpty(r4) == true) goto L7;
        Uri r52 = Uri.parse((String) zzfy.zzq.zzb(null));
        Uri.Builder r0 = r52.buildUpon();
        String r53 = r52.getAuthority();
        int r1 = String.valueOf(r4).length();
        StringBuilder r3 = new StringBuilder((r1 + 1) + String.valueOf(r53).length());
        r3.append(r4);
        r3.append(".");
        r3.append(r53);
        r0.authority(r3.toString());
        return r0.build().toString();
    L7:
        return (String) zzfy.zzq.zzb(null);
    }

    private static final boolean zzf(String r5) {
        String r0 = (String) zzfy.zzs.zzb(null);
        if (TextUtils.isEmpty(r0) == false) goto L5;
        return false;
    L5:
        String[] r02 = r0.split(",");
        int r1 = r02.length;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L12;
        if (r5.equalsIgnoreCase(r02[r3].trim()) == true) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    public final zzot zza(String r12) {
        zzpg r0 = this.zzg;
        zzh r1 = r0.zzj().zzu(r12);
        zzot r2 = null;
        if (r1 != null) goto L5;
    L55:
        String r11 = zzd(r12);
        zzls r122 = zzls.zza;
        return new zzot(r11, Collections.EMPTY_MAP, r122, null);
    L5:
        if (r1.zzai() == false) goto L55;
        com.google.android.gms.internal.measurement.zzil r3 = com.google.android.gms.internal.measurement.zzis.zzb();
        r3.zzb(2);
        r3.zza((com.google.android.gms.internal.measurement.zzin) Preconditions.checkNotNull(com.google.android.gms.internal.measurement.zzin.zzb(r1.zzaL())));
        String r5 = r1.zzd();
        com.google.android.gms.internal.measurement.zzgl r6 = r0.zzh().zzb(r12);
        if (r6 == null) goto L53;
        zzh r8 = r0.zzj().zzu(r12);
        if (r8 == null) goto L53;
        if (r6.zzp() == false) goto L17;
        if (r6.zzq().zzc() != 100) goto L17;
    L24:
        String r52 = r1.zzc();
        r3.zzb(2);
        com.google.android.gms.internal.measurement.zzgl r02 = r0.zzh().zzb(r1.zzc());
        if (r02 != null) goto L27;
    L48:
        this.zzu.zzaW().zzk().zzb("[sgtm] Missing sgtm_setting in remote config. appId", r52);
        r3.zzc(4);
    L49:
        if (r2 == null) goto L52;
        return r2;
    L52:
        return new zzot(zzd(r12), Collections.EMPTY_MAP, zzls.zza, (com.google.android.gms.internal.measurement.zzis) r3.zzbd());
    L27:
        if (r02.zzp() == false) goto L48;
        HashMap r62 = new HashMap();
        if (TextUtils.isEmpty(r1.zzay()) == true) goto L32;
        r62.put("x-gtm-server-preview", r1.zzay());
    L32:
        String r82 = r02.zzq().zzd();
        com.google.android.gms.internal.measurement.zzin r9 = com.google.android.gms.internal.measurement.zzin.zzb(r1.zzaL());
        if (r9 == null) goto L38;
        if (r9 == com.google.android.gms.internal.measurement.zzin.zzb) goto L38;
        r3.zza(r9);
    L43:
        r02.zzq().zza();
        r02.zzq().zzb();
        zzic r03 = this.zzu;
        r03.zzaV();
        if (TextUtils.isEmpty(r82) == true) goto L46;
        r03.zzaW().zzk().zzb("[sgtm] Eligible for local service direct upload. appId", r52);
        r3.zzb(5);
        r3.zzc(2);
        r2 = new zzot(r82, r62, zzls.zzc, (com.google.android.gms.internal.measurement.zzis) r3.zzbd());
        goto L49
    L46:
        r3.zzc(6);
        r03.zzaW().zzk().zzb("[sgtm] Local service, missing sgtm_server_url", r1.zzc());
    L38:
        if (zzf(r1.zzc()) == false) goto L41;
        r3.zza(com.google.android.gms.internal.measurement.zzin.zzk);
        goto L43
    L41:
        if (TextUtils.isEmpty(r82) == false) goto L47;
        r3.zza(com.google.android.gms.internal.measurement.zzin.zzl);
        goto L43
    L47:
        this.zzu.zzaW().zzk().zzb("[sgtm] Eligible for client side upload. appId", r52);
        r3.zzb(3);
        r3.zza(com.google.android.gms.internal.measurement.zzin.zzb);
        r2 = new zzot(r82, r62, zzls.zzd, (com.google.android.gms.internal.measurement.zzis) r3.zzbd());
    L17:
        if (this.zzu.zzk().zzad(r12, r8.zzay()) == true) goto L24;
        if (TextUtils.isEmpty(r5) == true) goto L53;
        if (Math.abs(r5.hashCode() % 100) < r6.zzq().zzc()) goto L24;
    L53:
        r3.zzc(3);
        return new zzot(zzd(r12), Collections.EMPTY_MAP, zzls.zza, (com.google.android.gms.internal.measurement.zzis) r3.zzbd());
    }

    public final boolean zzc(String r3, com.google.android.gms.internal.measurement.zzin r4) {
        zzg();
        if (r4 == com.google.android.gms.internal.measurement.zzin.zzb) goto L5;
    L14:
        return false;
    L5:
        if (zzf(r3) == true) goto L14;
        com.google.android.gms.internal.measurement.zzgl r2 = this.zzg.zzh().zzb(r3);
        if (r2 == null) goto L14;
        if (r2.zzp() == false) goto L14;
        if (r2.zzq().zzd().isEmpty() == true) goto L14;
        return true;
    }
}
