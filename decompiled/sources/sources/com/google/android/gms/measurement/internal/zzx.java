package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.chaquo.python.internal.Common;
import java.util.Iterator;
import kotlin.time.DurationKt;

/* loaded from: classes5.dex */
public final class zzx {
    private final zzic zza;

    public zzx(zzic r1) {
        this.zza = r1;
    }

    public final void zza() {
        zzic r0 = this.zza;
        r0.zzaX().zzg();
        if (zze() == true) goto L6;
        return;
    L6:
        if (zzd() == false) goto L8;
        r0.zzd().zzr.zzb(null);
        Bundle r12 = new Bundle();
        r12.putString("source", "(not set)");
        r12.putString("medium", "(not set)");
        r12.putString("_cis", "intent");
        r12.putLong("_cc", 1);
        r0.zzj().zzE("auto", "_cmpx", r12);
    L21:
        r0.zzd().zzs.zzb(0);
        return;
    L8:
        String r122 = r0.zzd().zzr.zza();
        if (TextUtils.isEmpty(r122) == false) goto L11;
        r0.zzaW().zzc().zza("Cache still valid but referrer not found");
    L20:
        r0.zzd().zzr.zzb(null);
        goto L21
    L11:
        long r3 = r0.zzd().zzs.zza() / DurationKt.MILLIS_IN_HOUR;
        Uri r123 = Uri.parse(r122);
        Bundle r7 = new Bundle();
        Pair r8 = new Pair(r123.getPath(), r7);
        Iterator<String> r9 = r123.getQueryParameterNames().iterator();
    L13:
        if (r9.hasNext() == false) goto L15;
        String r10 = r9.next();
        r7.putString(r10, r123.getQueryParameter(r10));
        goto L13
    L15:
        ((Bundle) r8.second).putLong("_cc", (r3 - 1) * DurationKt.MILLIS_IN_HOUR);
        Object r124 = r8.first;
        if (r124 != null) goto L18;
        String r125 = Common.ASSET_APP;
    L19:
        r0.zzj().zzE(r125, "_cmp", (Bundle) r8.second);
        goto L20
    L18:
        r125 = (String) r124;
        goto L19
    }

    public final void zzb(String r4, Bundle r5) {
        zzic r3 = this.zza;
        r3.zzaX().zzg();
        if (r3.zzB() == false) goto L5;
        return;
    L5:
        if (r5.isEmpty() == false) goto L7;
        String r42 = null;
    L13:
        if (TextUtils.isEmpty(r42) == true) goto L18;
        r3.zzd().zzr.zzb(r42);
        r3.zzd().zzs.zzb(r3.zzba().currentTimeMillis());
        return;
    L18:
        return;
    L7:
        Uri.Builder r0 = new Uri.Builder();
        r0.path(r4);
        Iterator<String> r43 = r5.keySet().iterator();
    L9:
        if (r43.hasNext() == false) goto L11;
        String r1 = r43.next();
        r0.appendQueryParameter(r1, r5.getString(r1));
        goto L9
    L11:
        r42 = r0.build().toString();
        goto L13
    }

    public final void zzc() {
        if (zze() == true) goto L5;
        return;
    L5:
        if (zzd() == false) goto L9;
        this.zza.zzd().zzr.zzb(null);
        return;
    }

    public final boolean zzd() {
        if (zze() == true) goto L5;
        return false;
    L5:
        zzic r6 = this.zza;
        if ((r6.zzba().currentTimeMillis() - r6.zzd().zzs.zza()) <= r6.zzc().zzl(null, zzfy.zzai)) goto L9;
        return true;
    L9:
        return false;
    }

    public final boolean zze() {
        if (this.zza.zzd().zzs.zza() <= 0) goto L6;
        return true;
    L6:
        return false;
    }
}
