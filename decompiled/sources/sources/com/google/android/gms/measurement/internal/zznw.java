package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
final /* synthetic */ class zznw implements Runnable {
    private final /* synthetic */ zznx zza;

    public /* synthetic */ zznw(zznx r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zznx r6 = this.zza;
        zzoc r0 = r6.zzc.zza;
        r0.zzg();
        zzic r1 = r0.zzu;
        r1.zzaW().zzj().zza("Application going to the background");
        r1.zzd().zzn.zzb(true);
        r0.zzh(true);
        if (r1.zzc().zzv() == true) goto L5;
        long r2 = r6.zzb;
        zzoa r4 = r0.zzb;
        r4.zzd(false, false, r2);
        r4.zzb(r2);
    L5:
        long r22 = r6.zza;
        r1.zzaW().zzi().zzb("Application backgrounded at: timestamp_millis", Long.valueOf(r22));
        zzic r62 = r0.zzu;
        zzlj r02 = r62.zzj();
        r02.zzg();
        zzic r23 = r02.zzu;
        r02.zzb();
        zznl r03 = r23.zzt();
        r03.zzg();
        r03.zzb();
        if (r03.zzK() == true) goto L9;
    L10:
        r23.zzt().zzF();
    L12:
        if (r1.zzc().zzp(null, zzfy.zzaN) == true) goto L14;
        return;
    L14:
        if (r1.zzk().zzad(r1.zzaZ().getPackageName(), r1.zzc().zzz()) == false) goto L16;
        long r24 = 1000;
    L17:
        r1.zzaW().zzk().zzb("[sgtm] Scheduling batch upload with minimum latency in millis", Long.valueOf(r24));
        r62.zzx().zzh(r24);
        return;
    L16:
        r24 = r1.zzc().zzl(r1.zzaZ().getPackageName(), zzfy.zzD);
        goto L17
    L9:
        if (r03.zzu.zzk().zzak() < 242600) goto L12;
        goto L10
    }
}
