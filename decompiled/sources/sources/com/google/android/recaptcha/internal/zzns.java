package com.google.android.recaptcha.internal;

import android.content.Context;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.ranges.CharRange;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzns implements zzne {
    private final Context zza;
    private final Mutex zzb;
    private final String zzc;

    public zzns(Context r2, zzop r3) {
        this.zza = r2;
        this.zzb = MutexKt.Mutex$default(false, 1, null);
        this.zzc = "rce_";
    }

    @Override // com.google.android.recaptcha.internal.zzne
    public final Object zza(String r8, Continuation r9) {
        if ((r9 instanceof zznp) == false) goto L7;
        zznp r0 = (zznp) r9;
        int r1 = r0.zzd;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzd = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.zzb;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzd;
        boolean r4 = true;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        Mutex r82 = (Mutex) r0.zza;
        String r02 = r0.zze;
        ResultKt.throwOnFailure(r92);
        Mutex r93 = r82;
        r8 = r02;
    L32:
        File r22 = this.zza.getCacheDir();     // Catch: Exception -> L31 Throwable -> L23
        String r7 = this.zzc;     // Catch: Exception -> L31 Throwable -> L23
        StringBuilder r6 = new StringBuilder(r7.length() + String.valueOf(r8).length());     // Catch: Exception -> L31 Throwable -> L23
        r6.append(r7);     // Catch: Exception -> L31 Throwable -> L23
        r6.append(r8);     // Catch: Exception -> L31 Throwable -> L23
        File r13 = new File(r22, r6.toString());     // Catch: Exception -> L31 Throwable -> L23
        if (r13.exists() == false) goto L22;
        if (r13.delete() == false) goto L22;
    L25:
        Boolean r72 = Boxing.boxBoolean(r4);     // Catch: Throwable -> L23
        r93.unlock(null);
        return r72;
    L23:
        th = move-exception;
        r93.unlock(null);
        throw th;
    L22:
        r4 = false;
        goto L25
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r92);
        r93 = this.zzb;
        r0.zze = r8;
        r0.zza = r93;
        r0.zzd = 1;
        if (r93.lock(null, r0) != r12) goto L32;
        return r12;
    L7:
        r0 = new zznp(this, r9);
        goto L8
    }

    @Override // com.google.android.recaptcha.internal.zzoo
    public final Object zzb(String r17, String r18, Continuation r19) {
        if ((r19 instanceof zznr) == false) goto L7;
        zznr r2 = (zznr) r19;
        int r3 = r2.zzd;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L7;
        r2.zzd = r3 - Integer.MIN_VALUE;
    L8:
        Object r1 = r2.zzb;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r4 = r2.zzd;
        if (r4 == 0) goto L14;
        if (r4 != 1) goto L12;
        Mutex r33 = (Mutex) r2.zza;
        String r42 = r2.zzf;
        String r22 = r2.zze;
        ResultKt.throwOnFailure(r1);
    L29:
        CharRange r12 = new CharRange('A', 'z');     // Catch: Throwable -> L21
        ArrayList r5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r12, 10));     // Catch: Throwable -> L21
        Iterator<Character> r13 = r12.iterator();     // Catch: Throwable -> L21
    L19:
        if (r13.hasNext() == false) goto L23;
        r5.add(Boxing.boxChar(((CharIterator) r13).nextChar()));     // Catch: Throwable -> L21
        goto L19
    L23:
        String r14 = CollectionsKt.joinToString$default(CollectionsKt.shuffled(r5).subList(0, 8), _UrlKt.FRAGMENT_ENCODE_SET, null, null, 0, null, null, 62, null);     // Catch: Throwable -> L21
        StringBuilder r7 = new StringBuilder(String.valueOf(r22).length() + String.valueOf(r14).length());     // Catch: Throwable -> L21
        r7.append(r22);     // Catch: Throwable -> L21
        r7.append(r14);     // Catch: Throwable -> L21
        String r15 = r7.toString();     // Catch: Throwable -> L21
        Context r72 = this.zza;     // Catch: Throwable -> L21
        File r8 = r72.getCacheDir();     // Catch: Throwable -> L21
        String r0 = this.zzc;     // Catch: Throwable -> L21
        StringBuilder r10 = new StringBuilder(r0.length() + r15.length());     // Catch: Throwable -> L21
        r10.append(r0);     // Catch: Throwable -> L21
        r10.append(r15);     // Catch: Throwable -> L21
        File r52 = new File(r8, r10.toString());     // Catch: Throwable -> L21
        zzop.zzb(r52, r42.getBytes(StandardCharsets.UTF_8));     // Catch: Throwable -> L21
        File r43 = r72.getCacheDir();     // Catch: Throwable -> L21
        StringBuilder r82 = new StringBuilder(r0.length() + String.valueOf(r22).length());     // Catch: Throwable -> L21
        r82.append(r0);     // Catch: Throwable -> L21
        r82.append(r22);     // Catch: Throwable -> L21
        r52.renameTo(new File(r43, r82.toString()));     // Catch: Throwable -> L21
        r33.unlock(null);
        return Unit.INSTANCE;
    L21:
        th = move-exception;
        r33.unlock(null);
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r1);
        Mutex r16 = this.zzb;
        r2.zze = r17;
        r2.zzf = r18;
        r2.zza = r16;
        r2.zzd = 1;
        if (r16.lock(null, r2) == r32) goto L28;
        r33 = r16;
        r22 = r17;
        r42 = r18;
        goto L29
    L28:
        return r32;
    L7:
        r2 = new zznr(this, r19);
        goto L8
    }

    @Override // com.google.android.recaptcha.internal.zzoo
    public final Object zzc(String r6, Continuation r7) {
        if ((r7 instanceof zznq) == false) goto L7;
        zznq r0 = (zznq) r7;
        int r1 = r0.zzd;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzd = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.zzb;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzd;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        Mutex r62 = (Mutex) r0.zza;
        String r02 = r0.zze;
        ResultKt.throwOnFailure(r72);
        Mutex r73 = r62;
        r6 = r02;
    L28:
        File r13 = this.zza.getCacheDir();     // Catch: Throwable -> L20
        String r5 = this.zzc;     // Catch: Throwable -> L20
        StringBuilder r3 = new StringBuilder(r5.length() + String.valueOf(r6).length());     // Catch: Throwable -> L20
        r3.append(r5);     // Catch: Throwable -> L20
        r3.append(r6);     // Catch: Throwable -> L20
        File r03 = new File(r13, r3.toString());     // Catch: Throwable -> L20
        if (r03.exists() == false) goto L22;
        String r04 = new String(zzop.zza(r03), StandardCharsets.UTF_8);     // Catch: Throwable -> L20
    L23:
        r73.unlock(null);
        return r04;
    L22:
        r04 = null;
    L20:
        th = move-exception;
        r73.unlock(null);
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r72);
        r73 = this.zzb;
        r0.zze = r6;
        r0.zza = r73;
        r0.zzd = 1;
        if (r73.lock(null, r0) != r12) goto L28;
        return r12;
    L7:
        r0 = new zznq(this, r7);
        goto L8
    }
}
