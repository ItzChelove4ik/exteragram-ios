package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class zzxg {
    private final ExecutorService zza;
    private final String zzb;
    private final zzxj zzc;
    private final Mutex zzd;
    private final Map zze;
    private final Mutex zzf;
    private Job zzg;
    private final CoroutineScope zzh;

    public zzxg(ExecutorService r3, String r4, zzxj r5) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        this.zzd = MutexKt.Mutex$default(false, 1, null);
        this.zze = new LinkedHashMap();
        this.zzf = MutexKt.Mutex$default(false, 1, null);
        this.zzh = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(r3));
    }

    public static /* synthetic */ Object zza(zzxg r2, Context r3, String r4, Continuation r5) {
        if (r4.length() != 0) goto L6;
        return null;
    L6:
        return r2.zzt(r3, new zzwx(r4, null), r5);
    }

    public static /* synthetic */ Object zzb(zzxg r2, Context r3, Continuation r4) {
        return r2.zzt(r3, new zzwy(null), r4);
    }

    public static /* synthetic */ Object zzc(zzxg r6, Context r7, zzxi r8, Continuation r9) {
        Object r62 = r6.zzu(r7, new zzxb(r8.zza(), r8, r6, r7, null), r9);
        if (r62 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r62;
    L6:
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object zzd(zzxg r3, Context r4, zzxi r5, Continuation r6) {
        return BuildersKt.withContext(r3.zzh.getCoroutineContext(), new zzwu(r5, r3, r4, null), r6);
    }

    public static /* synthetic */ Object zze(zzxg r7, Context r8, zzxi r9, Function1 r10, Continuation r11) {
        return BuildersKt.withContext(r7.zzh.getCoroutineContext(), new zzww(r9, r7, r8, r10, null), r11);
    }

    public static /* synthetic */ Object zzf(zzxg r0, Context r1, zzxi r2, Continuation r3) {
        return r0.zzw(r1, r2.zza(), r3);
    }

    public static final /* synthetic */ boolean zzh(zzxg r5, Context r6, zzxi r7) {
        File r1 = new File(r5.zzz(r6), r7.zzb());
        if (r1.exists() == true) goto L14;
    L13:
        return false;
    L14:
    L8:
        e = move-exception;
        Log.e("CacheFileManager", "Failed to delete cache file: ".concat(String.valueOf(r7.zzb())), e);
        goto L13
    L5:
        if (r1.delete() == true) goto L10;
        String r52 = r7.zzb();     // Catch: SecurityException -> L8
        StringBuilder r4 = new StringBuilder(String.valueOf(r52).length() + 30);     // Catch: SecurityException -> L8
        r4.append("Failed to delete cache file: ");     // Catch: SecurityException -> L8
        r4.append(r52);     // Catch: SecurityException -> L8
        r4.append(".");     // Catch: SecurityException -> L8
        Log.w("CacheFileManager", r4.toString());     // Catch: SecurityException -> L8
        return false;
    L10:
        return true;
    }

    public static final /* synthetic */ File zzi(zzxg r0, Context r1) {
        return r0.zzz(r1);
    }

    public static final /* synthetic */ Object zzj(zzxg r0, Context r1, String r2, Continuation r3) {
        return r0.zzw(r1, r2, r3);
    }

    public static final /* synthetic */ Object zzk(zzxg r0, Context r1, Function2 r2, Continuation r3) {
        return r0.zzu(r1, r2, r3);
    }

    public static final /* synthetic */ Object zzl(zzxg r0, Context r1, Continuation r2) {
        return r0.zzs(r1, r2);
    }

    public static final /* synthetic */ Object zzm(zzxg r0, Context r1, Function2 r2, Continuation r3) {
        return r0.zzt(null, null, r3);
    }

    public static final /* synthetic */ Object zzn(zzxg r0, Context r1, Map r2, Continuation r3) {
        return r0.zzy(r1, r2, r3);
    }

    public static final /* synthetic */ Object zzo(zzxg r0, Function2 r1, Continuation r2) {
        return r0.zzv(null, r2);
    }

    public static final /* synthetic */ Object zzp(zzxg r0, Context r1, Continuation r2) {
        return r0.zzx(null, r2);
    }

    public static final /* synthetic */ File zzq(zzxg r1, Context r2) {
        return new File(r1.zzz(r2), "index");
    }

    public static final /* synthetic */ Map zzr(zzxg r7, JSONArray r8) {
        LinkedHashMap r0 = new LinkedHashMap();
        int r1 = r8.length();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        JSONObject r3 = r8.getJSONObject(r2);
        zzxi r32 = r7.zzc.zza(r3);
        if (r0.containsKey(r32.zza()) == false) goto L7;
        String r4 = r32.zza();
        StringBuilder r6 = new StringBuilder(String.valueOf(r4).length() + 36);
        r6.append("Duplicate cache name: ");
        r6.append(r4);
        r6.append(". Overwriting.");
        Log.w("CacheFileManager", r6.toString());
    L7:
        r0.put(r32.zza(), r32);
        r2 = r2 + 1;
        goto L3
    L8:
        return r0;
    }

    private final Object zzs(Context r7, Continuation r8) {
        if ((r8 instanceof zzws) == false) goto L7;
        zzws r0 = (zzws) r8;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        ResultKt.throwOnFailure(r82);
    L22:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r82);
    L18:
        Function2 r72 = new zzwt((Map) r82, null);
        r0.zzc = 2;
        if (zzv(r72, r0) != r12) goto L22;
    L23:
        return r12;
    L16:
        ResultKt.throwOnFailure(r82);
        r0.zzc = 1;
        r82 = BuildersKt.withContext(this.zzh.getCoroutineContext(), new zzwz(this, r7, null), r0);
        if (r82 == r12) goto L23;
    L7:
        r0 = new zzws(this, r8);
        goto L8
    }

    private final Object zzt(Context r9, Function2 r10, Continuation r11) {
        if ((r11 instanceof zzxa) == false) goto L7;
        zzxa r0 = (zzxa) r11;
        int r1 = r0.zze;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zze = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.zzc;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zze;
        if (r2 == 0) goto L22;
        if (r2 == 1) goto L21;
        if (r2 == 2) goto L20;
        if (r2 != 3) goto L18;
        Mutex r8 = (Mutex) r0.zza;
        ResultKt.throwOnFailure(r112);     // Catch: Throwable -> L16
    L30:
        r8.unlock(null);
        return r112;
    L16:
        Throwable th = th;
    L34:
        r8.unlock(null);
        throw th;
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L20:
        Mutex r92 = (Mutex) r0.zzb;
        r10 = (Function2) r0.zza;
        ResultKt.throwOnFailure(r112);
    L37:
        Map r82 = this.zze;     // Catch: Throwable -> L32
        r0.zza = r92;     // Catch: Throwable -> L32
        r0.zzb = null;     // Catch: Throwable -> L32
        r0.zze = 3;     // Catch: Throwable -> L32
        r112 = r10.invoke(r82, r0);     // Catch: Throwable -> L32
        if (r112 == r12) goto L36;
        r8 = r92;
    L36:
        return r12;
    L32:
        th = move-exception;
        Mutex r7 = r92;
        th = th;
        r8 = r7;
        goto L34
    L21:
        r10 = (Function2) r0.zza;
        ResultKt.throwOnFailure(r112);
    L24:
        r92 = this.zzd;
        r0.zza = r10;
        r0.zzb = r92;
        r0.zze = 2;
        if (r92.lock(null, r0) == r12) goto L36;
    L22:
        ResultKt.throwOnFailure(r112);
        r0.zza = r10;
        r0.zze = 1;
        if (zzx(r9, r0) == r12) goto L36;
    L7:
        r0 = new zzxa(this, r11);
        goto L8
    }

    private final Object zzu(Context r7, Function2 r8, Continuation r9) {
        if ((r9 instanceof zzxc) == false) goto L7;
        zzxc r0 = (zzxc) r9;
        int r1 = r0.zze;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zze = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.zzc;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zze;
        if (r2 == 0) goto L17;
        if (r2 == 1) goto L16;
        if (r2 != 2) goto L14;
        ResultKt.throwOnFailure(r92);
        return r92;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        r8 = (Function2) r0.zzb;
        r7 = (Context) r0.zza;
        ResultKt.throwOnFailure(r92);
    L19:
        Function2 r93 = new zzxd(r8, this, r7, null);
        r0.zza = null;
        r0.zzb = null;
        r0.zze = 2;
        Object r6 = zzv(r93, r0);
        if (r6 == r12) goto L23;
        return r6;
    L23:
        return r12;
    L17:
        ResultKt.throwOnFailure(r92);
        r0.zza = r7;
        r0.zzb = r8;
        r0.zze = 1;
        if (zzx(r7, r0) == r12) goto L23;
    L7:
        r0 = new zzxc(this, r9);
        goto L8
    }

    private final Object zzv(Function2 r8, Continuation r9) {
        if ((r9 instanceof zzxe) == false) goto L7;
        zzxe r0 = (zzxe) r9;
        int r1 = r0.zze;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zze = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.zzc;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zze;
        if (r2 == 0) goto L20;
        if (r2 == 1) goto L19;
        if (r2 != 2) goto L17;
        Mutex r7 = (Mutex) r0.zza;
        ResultKt.throwOnFailure(r92);     // Catch: Throwable -> L15
    L27:
        r7.unlock(null);
        return r92;
    L15:
        Throwable th = th;
    L31:
        r7.unlock(null);
        throw th;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        Mutex r82 = (Mutex) r0.zzb;
        Function2 r22 = (Function2) r0.zza;
        ResultKt.throwOnFailure(r92);
    L36:
        Map r72 = this.zze;     // Catch: Throwable -> L29
        r0.zza = r82;     // Catch: Throwable -> L29
        r0.zzb = null;     // Catch: Throwable -> L29
        r0.zze = 2;     // Catch: Throwable -> L29
        r92 = r22.invoke(r72, r0);     // Catch: Throwable -> L29
        if (r92 == r12) goto L33;
        r7 = r82;
    L33:
        return r12;
    L29:
        th = move-exception;
        Mutex r6 = r82;
        th = th;
        r7 = r6;
        goto L31
    L20:
        ResultKt.throwOnFailure(r92);
        Mutex r93 = this.zzd;
        r0.zza = r8;
        r0.zzb = r93;
        r0.zze = 1;
        if (r93.lock(null, r0) == r12) goto L33;
        r22 = r8;
        r82 = r93;
    L7:
        r0 = new zzxe(this, r9);
        goto L8
    }

    private final Object zzw(Context r4, String r5, Continuation r6) {
        return BuildersKt.withContext(this.zzh.getCoroutineContext(), new zzwp(this, r4, r5, null), r6);
    }

    private final Object zzx(Context r13, Continuation r14) {
        if ((r14 instanceof zzwq) == false) goto L7;
        zzwq r0 = (zzwq) r14;
        int r1 = r0.zze;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zze = r1 - Integer.MIN_VALUE;
    L8:
        Object r142 = r0.zzc;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zze;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        ResultKt.throwOnFailure(r142);
    L30:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        Mutex r132 = (Mutex) r0.zzb;
        Context r22 = (Context) r0.zza;
        ResultKt.throwOnFailure(r142);
        Mutex r143 = r132;
        r13 = r22;
    L36:
    L21:
        th = move-exception;
        r143.unlock(null);
        throw th;
    L19:
        if (this.zzg != null) goto L23;
        this.zzg = BuildersKt.launch$default(this.zzh, null, null, new zzwr(this, r13, null), 3, null);     // Catch: Throwable -> L21
    L23:
        Unit r133 = Unit.INSTANCE;     // Catch: Throwable -> L21
        r143.unlock(null);
        Job r122 = this.zzg;
        if (r122 == null) goto L32;
        r0.zza = null;
        r0.zzb = null;
        r0.zze = 2;
        if (r122.join(r0) != r12) goto L30;
    L35:
        return r12;
    L32:
        return Unit.INSTANCE;
    L16:
        ResultKt.throwOnFailure(r142);
        r143 = this.zzf;
        r0.zza = r13;
        r0.zzb = r143;
        r0.zze = 1;
        if (r143.lock(null, r0) == r12) goto L35;
    L7:
        r0 = new zzwq(this, r14);
        goto L8
    }

    private final Object zzy(Context r4, Map r5, Continuation r6) {
        Object r3 = BuildersKt.withContext(this.zzh.getCoroutineContext(), new zzxf(this, r4, r5, null), r6);
        if (r3 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r3;
    L6:
        return Unit.INSTANCE;
    }

    private final File zzz(Context r2) {
        File r0 = new File(r2.getApplicationContext().getFilesDir(), this.zzb);
        if (r0.exists() == true) goto L5;
        r0.mkdirs();
    L5:
        return r0;
    }
}
