package com.google.android.recaptcha.internal;

import android.app.Application;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzgo extends zzcl {
    public List zza;
    public Deferred zzb;
    private final Application zzc;
    private final zzgz zzd;
    private final zzgp zze;
    private final zzmv zzf;
    private final zzhq zzg;
    private zzd zzh;
    private final HashMap zzi;
    private zzakg zzj;
    private final zzbb zzk;
    private final Mutex zzl;
    private boolean zzm;
    private final zzhr zzn;
    private final zzgy zzo;
    private final zzhu zzp;

    public zzgo(Application r1, zzhu r2, zzgz r3, zzgy r4, zzfw r5, zzgp r6, zzhr r7, zzmv r8, zzhq r9) {
        this.zzc = r1;
        this.zzp = r2;
        this.zzd = r3;
        this.zzo = r4;
        this.zze = r6;
        this.zzn = r7;
        this.zzf = r8;
        this.zzg = r9;
        this.zzi = new HashMap();
        this.zzk = new zzbb(r1, r2.zzc(), new zzav());
        this.zzl = MutexKt.Mutex$default(false, 1, null);
    }

    public static final /* synthetic */ zzhg zzA(zzgo r7, Exception r8) {
        if ((r8 instanceof zzhg) == false) goto L5;
        zzhg r72 = (zzhg) r8;
    L6:
        if (r72 == null) goto L8;
        return r72;
    L8:
        return new zzhg(zzhf.zzb, zzhe.zzal, r8.getMessage(), null, 8, null);
    L5:
        r72 = null;
        goto L6
    }

    public static final /* synthetic */ zzgy zzB(zzgo r0) {
        return r0.zzo;
    }

    public static final /* synthetic */ zzhu zzC(zzgo r0) {
        return r0.zzp;
    }

    private final Deferred zzD(Long r8, Optional r9) {
        return BuildersKt.async$default(this.zzp.zze(), null, null, new zzge(r8, this, r9, null), 3, null);
    }

    private final void zzE(zzmd r12) {
        HashMap r0 = this.zzi;
        Application r2 = this.zzc;
        r0.put(120, r2);
        r0.put(416, "18.9.2");
        r0.put(518, r12.zzb());
        r0.put(620, zzgp.zzb());
        r0.put(722, r12.zza());
        zzhr r1 = this.zzn;
        r0.put(824, Integer.valueOf(r1.zzd() - 2));
        r0.put(926, r2.getPackageName());
        r0.put(1028, Boolean.valueOf(r1.zza()));
        r0.put(1130, Boolean.valueOf(r1.zzb()));
        r0.put(1232, r1.zzc());
        r0.put(2044, zzfu.class.getMethod("zzb", new Class[]{new Object[0].getClass()}));
        zzbb r11 = this.zzk;
        zzfs r122 = new zzfs(r11);
        zzft r22 = new zzft(r11);
        zzfr r3 = new zzfr(r11);
        Iterator r112 = CollectionsKt.listOf(new zzfu[]{r122, r22, r3}).iterator();
    L4:
        if (r112.hasNext() == false) goto L12;
        Object r13 = ((zzfu) r112.next()).zzb(new Object[]{new zzfv()});
        if ((r13 instanceof Boolean) == false) goto L11;
        if (((Boolean) r13).booleanValue() == true) goto L4;
    L11:
        throw new zzhg(zzhf.zzb, zzhe.zzaB, null, null, 12, null);
    L12:
        r0.put(3335, r122);
        r0.put(3576, r22);
        r0.put(3809, r3);
    }

    private final Object zzF(Continuation r12) {
        if ((r12 instanceof zzfy) == false) goto L7;
        zzfy r0 = (zzfy) r12;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r122 = r0.zza;
        Object r13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        List r3 = null;
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L13;
        ResultKt.throwOnFailure(r122);     // Catch: Exception -> L28
    L19:
        Object r11 = ((Result) r122).m2027unboximpl();     // Catch: Exception -> L28
        ResultKt.throwOnFailure(r11);     // Catch: Exception -> L28
        if ((r11 instanceof List) == false) goto L22;
        r3 = (List) r11;     // Catch: Exception -> L28
    L22:
        if (r3 == null) goto L27;
        if (r3.size() < 2) goto L27;
        return r3;
    L27:
        throw new zzhg(zzhf.zzb, zzhe.zzaj, null, null, 12, null);
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r122);
        Deferred r112 = zzD(null, Optional.empty());     // Catch: Exception -> L28
        r0.zzc = 1;     // Catch: Exception -> L28
        r122 = r112.await(r0);     // Catch: Exception -> L28
        if (r122 != r13) goto L19;
        return r13;
    L28:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzaj, e.getMessage(), e);
    L7:
        r0 = new zzfy(this, r12);
        goto L8
    }

    private final void zzG(zzmg r14) {
        Object r13 = this.zzi.get(Integer.valueOf(MediaError.DetailedErrorCode.HLS_NETWORK_KEY_LOAD));
        if ((r13 instanceof List) == false) goto L5;
        List r132 = (List) r13;
    L6:
        if (r132 == null) goto L11;
        List r133 = r132;
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r133, 10));
        Iterator r134 = r133.iterator();
    L9:
        if (r134.hasNext() == false) goto L12;
        r0.add(zzalo.zzd((byte[]) r134.next()));
    L12:
        if (r0 == null) goto L51;
        LinkedHashMap r135 = new LinkedHashMap();
        int r2 = r0.size();
        int r3 = 0;
    L14:
        if (r3 >= r2) goto L19;
        Object r4 = r0.get(r3);
        r3 = r3 + 1;
        Long r5 = Long.valueOf(((zzalo) r4).zza());
        Object r6 = r135.get(r5);
        if (r6 != null) goto L18;
        r6 = new ArrayList();
        r135.put(r5, r6);
    L18:
        ((List) r6).add(r4);
        goto L14
    L19:
        Iterator r136 = r135.values().iterator();
    L21:
        if (r136.hasNext() == false) goto L69;
        List r02 = (List) r136.next();
        Iterator r22 = r02.iterator();
    L23:
        int r42 = 3;
        if (r22.hasNext() == false) goto L28;
        Object r32 = r22.next();
        if (((zzalo) r32).zzg() != 3) goto L23;
    L29:
        zzalo r33 = (zzalo) r32;
        Iterator r03 = r02.iterator();
    L31:
        if (r03.hasNext() == false) goto L35;
        Object r23 = r03.next();
        if (((zzalo) r23).zzg() != 4) goto L31;
    L36:
        zzalo r24 = (zzalo) r23;
        if (r33 == null) goto L21;
        if (r24 == null) goto L21;
        int r04 = r33.zzh() - 2;
        if (r04 == 1) goto L44;
        if (r04 == 2) goto L43;
        int r05 = 2;
    L45:
        zzajh r8 = zzajj.zzd();
        long r9 = r24.zzc() - r33.zzc();
        r8.zzy(r05);
        r8.zza(zzlj.zzb());
        r8.zzc(r14.zzb());
        r8.zzb(r14.zza().zzb());
        r8.zzz(r14.zza().zzd());
        r8.zzw(r33.zzb());
        int r06 = r24.zzi() - 2;
        if (r06 == 1) goto L50;
        if (r06 == 2) goto L49;
        r42 = 2;
        goto L50
    L49:
        r42 = 4;
    L50:
        r8.zzA(r42);
        long r25 = r33.zzc();
        r8.zzh(zzaiq.zzd(r25 / 1000000000, (int) (r25 % 1000000000)));
        r8.zze(zzaio.zzc(r9 / 1000000000, (int) (r9 % 1000000000)));
        r14.zzd(r8, null);
        goto L21
    L43:
        r05 = 46;
        goto L45
    L44:
        r05 = 47;
        goto L45
    L35:
        r23 = null;
        goto L36
    L28:
        r32 = null;
        goto L29
    L69:
        return;
    L51:
        return;
    L11:
        r0 = null;
        goto L12
    L5:
        r132 = null;
        goto L6
    }

    private static final zzaku zzH(String r1, zzalt r2) {
        zzakt r0 = zzaku.zza();
        r0.zza(r1);
        zzakp r12 = zzakq.zza();
        r12.zza(r2);
        r0.zze(r12);
        return (zzaku) r0.zzp();
    }

    public static final /* synthetic */ void zzj(zzgo r0, zzakg r1) {
        r0.zzj = r1;
    }

    public static final /* synthetic */ Object zzk(zzgo r1, zzakg r2, Continuation r3) {
        return new zzmc(new zzgk(r1, r2, null));
    }

    public static final /* synthetic */ Mutex zzl(zzgo r0) {
        return r0.zzl;
    }

    public static final /* synthetic */ boolean zzm(zzgo r0) {
        return r0.zzm;
    }

    public static final /* synthetic */ void zzn(zzgo r0, boolean r1) {
        r0.zzm = true;
    }

    public static final /* synthetic */ zzmv zzo(zzgo r0) {
        return r0.zzf;
    }

    public static final /* synthetic */ zzhq zzp(zzgo r0) {
        return r0.zzg;
    }

    public static final /* synthetic */ zzd zzq(zzgo r0) {
        return r0.zzh;
    }

    public static final /* synthetic */ zzgz zzr(zzgo r0) {
        return r0.zzd;
    }

    public static final /* synthetic */ void zzs(zzgo r3, zzalm r4) {
        zzd r0 = r3.zze.zza();     // Catch: Exception -> L11
        r3.zzh = r0;     // Catch: Exception -> L11
        zzd r1 = null;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.zzb(r4.zza().zzy());     // Catch: Exception -> L11
        zzd r32 = r3.zzh;     // Catch: Exception -> L11
        if (r32 == null) goto L9;
        r1 = r32;
    L9:
        r1.zza();     // Catch: Exception -> L11
        return;
    L11:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzai, e.getMessage(), e);
    }

    public static final /* synthetic */ Object zzt(zzgo r0, Continuation r1) {
        return r0.zzF(r1);
    }

    public static final /* synthetic */ void zzu(zzgo r0, zzmd r1) {
        r0.zzE(r1);
    }

    public static final /* synthetic */ Deferred zzv(zzgo r0, Long r1, Optional r2) {
        return r0.zzD(r1, r2);
    }

    public static final /* synthetic */ HashMap zzw(zzgo r0) {
        return r0.zzi;
    }

    public static final /* synthetic */ zzakg zzx(zzgo r0) {
        return r0.zzj;
    }

    public static final /* synthetic */ void zzy(zzgo r0, zzmg r1) {
        r0.zzG(r1);
    }

    public static final /* synthetic */ zzaku zzz(zzgo r0, String r1, zzalt r2) {
        return zzH(r1, r2);
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final Object zzb(zzakg r2, Continuation r3) {
        return new zzmc(new zzgd(this, r2, null));
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final Object zzc(String r1, zzaka r2, Continuation r3) {
        return new zzmc(new zzgc(this, r1, null));
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final Object zzd(String r1, Continuation r2) {
        zzals r0 = zzalt.zzd();
        r0.zza(r1);
        return zzH(r1, (zzalt) r0.zzp());
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final int zzf() {
        return 43;
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final int zzg() {
        return 44;
    }

    public final List zzh() {
        List r0 = this.zza;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    public final Deferred zzi() {
        Deferred r0 = this.zzb;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }
}
