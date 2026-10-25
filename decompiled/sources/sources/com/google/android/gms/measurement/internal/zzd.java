package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zzd extends zzf {
    private final Map zza;
    private final Map zzb;
    private long zzc;

    public zzd(zzic r1) {
        super(r1);
        this.zzb = new ArrayMap();
        this.zza = new ArrayMap();
    }

    private final void zzh(long r3, zzlu r5) {
        if (r5 != null) goto L6;
        this.zzu.zzaW().zzk().zza("Not logging ad exposure. No active activity");
        return;
    L6:
        if (r3 >= 1000) goto L9;
        this.zzu.zzaW().zzk().zzb("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(r3));
        return;
    L9:
        Bundle r0 = new Bundle();
        r0.putLong("_xt", r3);
        zzpp.zzay(r5, r0, true);
        this.zzu.zzj().zzE("am", "_xa", r0);
    }

    private final void zzi(String r3, long r4, zzlu r6) {
        if (r6 != null) goto L6;
        this.zzu.zzaW().zzk().zza("Not logging ad unit exposure. No active activity");
        return;
    L6:
        if (r4 >= 1000) goto L9;
        this.zzu.zzaW().zzk().zzb("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(r4));
        return;
    L9:
        Bundle r0 = new Bundle();
        r0.putString("_ai", r3);
        r0.putLong("_xt", r4);
        zzpp.zzay(r6, r0, true);
        this.zzu.zzj().zzE("am", "_xu", r0);
    }

    private final void zzj(long r5) {
        Map r0 = this.zza;
        Iterator r1 = r0.keySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L7;
        r0.put((String) r1.next(), Long.valueOf(r5));
        goto L4
    L7:
        if (r0.isEmpty() == true) goto L11;
        this.zzc = r5;
        return;
    }

    public final void zza(String r3, long r4) {
        if (r3 != null) goto L4;
    L8:
        this.zzu.zzaW().zzb().zza("Ad unit id must be a non-empty string");
        return;
    L4:
        if (r3.length() == 0) goto L8;
        this.zzu.zzaX().zzj(new zza(this, r3, r4));
    }

    public final void zzb(String r3, long r4) {
        if (r3 != null) goto L4;
    L8:
        this.zzu.zzaW().zzb().zza("Ad unit id must be a non-empty string");
        return;
    L4:
        if (r3.length() == 0) goto L8;
        this.zzu.zzaX().zzj(new zzb(this, r3, r4));
    }

    public final void zzc(long r7) {
        zzlu r0 = this.zzu.zzs().zzh(false);
        Map r1 = this.zza;
        Iterator r2 = r1.keySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L7;
        String r3 = (String) r2.next();
        zzi(r3, r7 - ((Long) r1.get(r3)).longValue(), r0);
        goto L4
    L7:
        if (r1.isEmpty() == true) goto L9;
        zzh(r7 - this.zzc, r0);
    L9:
        zzj(r7);
    }

    public final /* synthetic */ void zzd(String r5, long r6) {
        zzg();
        Preconditions.checkNotEmpty(r5);
        Map r0 = this.zzb;
        if (r0.isEmpty() == false) goto L5;
        this.zzc = r6;
    L5:
        Integer r1 = (Integer) r0.get(r5);
        if (r1 == null) goto L10;
        r0.put(r5, Integer.valueOf(r1.intValue() + 1));
        return;
    L10:
        if (r0.size() < 100) goto L13;
        this.zzu.zzaW().zze().zza("Too many ads visible");
        return;
    L13:
        r0.put(r5, 1);
        this.zza.put(r5, Long.valueOf(r6));
    }

    public final /* synthetic */ void zze(String r6, long r7) {
        zzg();
        Preconditions.checkNotEmpty(r6);
        Map r0 = this.zzb;
        Integer r1 = (Integer) r0.get(r6);
        zzic r2 = this.zzu;
        if (r1 == null) goto L20;
        zzlu r22 = r2.zzs().zzh(false);
        int r12 = r1.intValue() - 1;
        if (r12 != 0) goto L18;
        r0.remove(r6);
        Map r13 = this.zza;
        Long r3 = (Long) r13.get(r6);
        if (r3 != null) goto L9;
        this.zzu.zzaW().zzb().zza("First ad unit exposure time was never set");
    L11:
        if (r0.isEmpty() == false) goto L22;
        long r02 = this.zzc;
        if (r02 != 0) goto L16;
        this.zzu.zzaW().zzb().zza("First ad exposure time was never set");
        return;
    L16:
        zzh(r7 - r02, r22);
        this.zzc = 0;
        return;
    L22:
        return;
    L9:
        long r32 = r7 - r3.longValue();
        r13.remove(r6);
        zzi(r6, r32, r22);
        goto L11
    L18:
        r0.put(r6, Integer.valueOf(r12));
        return;
    L20:
        r2.zzaW().zzb().zzb("Call to endAdUnitExposure for unknown ad unit id", r6);
    }

    public final /* synthetic */ void zzf(long r1) {
        zzj(r1);
    }
}
