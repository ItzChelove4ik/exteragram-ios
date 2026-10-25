package com.google.android.gms.internal.measurement;

import android.net.Uri;
import android.util.Pair;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zztt {
    private final ConcurrentMap zza;
    private final Executor zzb;
    private final zzru zzc;
    private final AsyncFunction zzd;
    private final Map zze;
    private final zzvc zzf;

    public zztt(Executor r1, zzru r2, zzvc r3, Map r4, zzvf r5) {
        this.zza = new ConcurrentHashMap();
        this.zzb = (Executor) Preconditions.checkNotNull(r1);
        this.zzc = (zzru) Preconditions.checkNotNull(r2);
        this.zzf = (zzvc) Preconditions.checkNotNull(r3);
        this.zze = (Map) Preconditions.checkNotNull(r4);
        Preconditions.checkArgument(!r1.isEmpty());
        this.zzd = zzts.zza;
    }

    public final zztp zza(zztr r18) {
        ConcurrentMap r7 = this.zza;
        Uri r8 = r18.zza();
        Pair r1 = (Pair) r7.get(r8);
        boolean r9 = false;
        if (r1 != null) goto L25;
        Uri r12 = r18.zza();
        Preconditions.checkArgument(r12.isHierarchical(), "Uri must be hierarchical: %s", r12);
        String r2 = Strings.nullToEmpty(r12.getLastPathSegment());
        int r4 = r2.lastIndexOf(46);
        if (r4 != (-1)) goto L7;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        Preconditions.checkArgument(r22.equals("pb"), "Uri extension must be .pb: %s", r12);
        Preconditions.checkArgument(true, "Proto schema cannot be null");
        if (r18.zzc() == null) goto L11;
        boolean r13 = true;
    L12:
        Preconditions.checkArgument(r13, "Handler cannot be null");
        zzuw r14 = (zzuw) this.zze.get("singleproc");
        if (r14 == null) goto L15;
        boolean r42 = true;
    L16:
        Preconditions.checkArgument(r42, "No XDataStoreVariantFactory registered for ID %s", "singleproc");
        String r23 = Strings.nullToEmpty(r18.zza().getLastPathSegment());
        int r3 = r23.lastIndexOf(46);
        if (r3 == (-1)) goto L19;
        r23 = r23.substring(0, r3);
    L19:
        String r32 = r23;
        ListenableFuture r142 = Futures.transformAsync(Futures.immediateFuture(r18.zza()), this.zzd, MoreExecutors.directExecutor());
        Executor r43 = this.zzb;
        zzru r5 = this.zzc;
        zzti r6 = zzti.zza;
        zztr r24 = r18;
        zztp r11 = new zztp(r14.zzb(r24, r32, r43, r5, r6), this.zzf, r142, false, r14.zza(r6));
        ImmutableList r0 = r24.zzd();
        if (r0.isEmpty() == true) goto L22;
        r11.zza(zzto.zza(r0, r43));
    L22:
        r1 = Pair.create(r11, r24);
        Pair r02 = (Pair) r7.putIfAbsent(r8, r1);
        if (r02 == null) goto L26;
        r1 = r02;
    L26:
        zztp r03 = (zztp) r1.first;
        zztr r15 = (zztr) r1.second;
        if (r24.equals(r15) == false) goto L29;
        return r03;
    L29:
        String r04 = Strings.lenientFormat("ProtoDataStoreConfig<%s> doesn't match previous call [uri=%s] [%s]", new Object[]{r24.zzb().getClass().getSimpleName(), r24.zza()});
        Preconditions.checkArgument(r24.zza().equals(r15.zza()), r04, "uri");
        Preconditions.checkArgument(r24.zzb().equals(r15.zzb()), r04, "schema");
        Preconditions.checkArgument(r24.zzc().equals(r15.zzc()), r04, "handler");
        Preconditions.checkArgument(r24.zzd().equals(r15.zzd()), r04, "migrations");
        Preconditions.checkArgument(r24.zze().equals(r15.zze()), r04, "variantConfig");
        if (r24.zzf() != r15.zzf()) goto L32;
        r9 = true;
    L32:
        Preconditions.checkArgument(r9, r04, "useGeneratedExtensionRegistry");
        r15.zzg();
        Preconditions.checkArgument(true, r04, "enableTracing");
        f$$ExternalSyntheticBUOutline1.m(Strings.lenientFormat(r04, new Object[]{"unknown"}));
        return null;
    L15:
        r42 = false;
        goto L16
    L11:
        r13 = false;
        goto L12
    L7:
        r22 = r2.substring(r4 + 1);
        goto L8
    L25:
        r24 = r18;
        goto L26
    }
}
