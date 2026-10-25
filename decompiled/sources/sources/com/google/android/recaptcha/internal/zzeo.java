package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.ranges.RangesKt;

/* loaded from: classes5.dex */
public final class zzeo extends zzcl {
    private final List zza;
    private zzaer zzb;
    private final Map zzc;

    public zzeo(List r1) {
        this.zza = r1;
        this.zzc = new LinkedHashMap();
    }

    public static final /* synthetic */ void zzi(zzeo r0, zzaer r1) {
        r0.zzb = r1;
    }

    public static final /* synthetic */ List zzj(zzeo r0) {
        return r0.zza;
    }

    public static final /* synthetic */ zzaku zzk(zzeo r0, String r1) {
        return r0.zzl(r1);
    }

    private final zzaku zzl(String r6) {
        List r0 = (List) this.zzc.remove(r6);
        zzaer r2 = this.zzb;
        if (r2 != null) goto L5;
        r2 = null;
    L5:
        zzhx r1 = new zzhx(r2);
        if (r0 == null) goto L11;
        List r02 = r0;
        Map r3 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r02, 10)), 16));
        Iterator r03 = r02.iterator();
    L9:
        if (r03.hasNext() == false) goto L12;
        zzex r22 = (zzex) r03.next();
        r3.put(Integer.valueOf(r22.zzb()), r22);
    L12:
        zzalt r5 = zzm(r3, r1, r6);
        zzakt r04 = zzaku.zza();
        r04.zza(r6);
        zzakl r62 = zzakm.zza();
        r62.zza(r5);
        r62.zzb(zzaer.zzs(r1.zza(), 0, 12));
        r04.zzc(r62);
        return (zzaku) r04.zzp();
    L11:
        r3 = MapsKt.emptyMap();
        goto L12
    }

    private final zzalt zzm(Map r7, zzhx r8, String r9) {
        zzals r0 = zzalt.zzd();
        r0.zza(r9);
        List r6 = this.zza;
        ArrayList r92 = new ArrayList();
        Iterator r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L8;
        Object r1 = r62.next();
        if (((zzev) r1).zzb() == false) goto L4;
        r92.add(r1);
        goto L4
    L8:
        int r63 = r92.size();
        int r12 = 0;
    L9:
        if (r12 >= r63) goto L13;
        Object r2 = r92.get(r12);
        r12 = r12 + 1;
        zzev r22 = (zzev) r2;
        if (r7.containsKey(Integer.valueOf(r22.zza())) == true) goto L9;
        int r23 = r22.zza();
        zzalw r4 = zzalx.zza();
        r4.zza(r23);
        r4.zzb(13);
        r4.zzc(27);
        r0.zzb(zzn(r8, new zzep(r23, (zzalx) r4.zzp())));
        goto L9
    L13:
        Collection r64 = r7.values();
        ArrayList r72 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r64, 10));
        Iterator r65 = r64.iterator();
    L15:
        if (r65.hasNext() == false) goto L17;
        r72.add(zzn(r8, (zzex) r65.next()));
        goto L15
    L17:
        r0.zzc(r72);
        return (zzalt) r0.zzp();
    }

    private static final zzalr zzn(zzhx r4, zzex r5) {
        zzalp r0 = zzalr.zzi();
        r0.zzh(3);
        byte[] r1 = BigInteger.valueOf(r5.zzb()).toByteArray();
        zzaer r2 = zzaer.zza;
        r0.zzg(zzaer.zzs(r1, 0, r1.length));
        if ((r5 instanceof zzeq) == false) goto L6;
        zzamb r12 = ((zzeq) r5).zza();
        byte[] r42 = r4.zzb(r12.zzw(), r5.zzb());
        r0.zze(zzaer.zzs(r42, 0, r42.length));
    L9:
        return (zzalr) r0.zzp();
    L6:
        if ((r5 instanceof zzep) == false) goto L10;
        zzalx r13 = ((zzep) r5).zza();
        byte[] r43 = r4.zzb(r13.zzw(), r5.zzb());
        r0.zzf(zzaer.zzs(r43, 0, r43.length));
        goto L9
    L10:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final Object zzb(zzakg r2, Continuation r3) {
        return new zzmc(new zzen(r2, this, null));
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final Object zzc(String r2, zzaka r3, Continuation r4) {
        return new zzmc(new zzel(this, r2, r3, null));
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final Object zzd(String r1, Continuation r2) {
        return zzl(r1);
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final int zzf() {
        return 34;
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final int zzg() {
        return 35;
    }

    public final Map zzh() {
        return this.zzc;
    }
}
