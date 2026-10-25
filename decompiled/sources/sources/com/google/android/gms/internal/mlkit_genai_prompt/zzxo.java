package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.google.mlkit.genai.common.GenAiException;
import com.google.mlkit.genai.prompt.CachedContext;
import com.google.mlkit.genai.prompt.Caches;
import com.google.mlkit.genai.prompt.CreateCachedContextRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzxo implements Caches {
    private final Context zza;
    private final zzwl zzb;
    private final zzxs zzc;

    public zzxo(Context r1, zzwl r2, zzxs r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.mlkit.genai.prompt.Caches
    public final Object create(CreateCachedContextRequest r19, Continuation<? super CachedContext> r20) {
        if ((r20 instanceof zzxk) == false) goto L8;
        zzxk r2 = (zzxk) r20;
        int r3 = r2.zzc;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L8;
        r2.zzc = r3 - Integer.MIN_VALUE;
    L7:
        zzxk r8 = r2;
        Object r1 = r8.zza;
        Object r22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r32 = r8.zzc;
        if (r32 == 0) goto L20;
        if (r32 == 1) goto L19;
        if (r32 == 2) goto L18;
        if (r32 != 3) goto L16;
        ResultKt.throwOnFailure(r1);
        return r1;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        String r33 = r8.zzf;
        String r4 = r8.zze;
        CreateCachedContextRequest r5 = r8.zzd;
        ResultKt.throwOnFailure(r1);
        String r12 = r33;
    L26:
        ParcelFileDescriptor r13 = (ParcelFileDescriptor) r1;
        if (r13 == null) goto L33;
        zzwl r0 = this.zzb;
        zzaal r11 = new zzaal(r12, r13, null, 4, null);
        r8.zzd = null;
        r8.zze = null;
        r8.zzf = null;
        r8.zzc = 3;
        Object r02 = zzwl.zzc(r0, r5, r11, r8);
        if (r02 == r22) goto L34;
        return r02;
    L34:
        return r22;
    L33:
        throw new GenAiException("Failed to open file descriptor to create cache for name: ".concat(String.valueOf(r4)), null, -103);
    L19:
        String r34 = r8.zzf;
        String r52 = r8.zze;
        CreateCachedContextRequest r6 = r8.zzd;
        ResultKt.throwOnFailure(r1);
        Object r53 = r1;
        CreateCachedContextRequest r14 = r6;
        String r62 = r34;
        String r35 = r52;
    L23:
        zzxs r7 = this.zzc;
        Context r112 = this.zza;
        String r54 = (String) r53;
        r8.zzd = r14;
        r8.zze = r35;
        r8.zzf = r62;
        r8.zzc = 2;
        String r55 = r35;
        Object r36 = zzxs.zzd(r7, r112, r55, r62, r54, r8);
        if (r36 == r22) goto L34;
        r4 = r55;
        r12 = r62;
        r5 = r14;
        r1 = r36;
        goto L26
    L20:
        ResultKt.throwOnFailure(r1);
        String r15 = r19.getName();
        String r37 = r19.getPromptPrefix().getTextString();
        zzwl r63 = this.zzb;
        r8.zzd = r19;
        r8.zze = r15;
        r8.zzf = r37;
        r8.zzc = 1;
        r53 = r63.zzz(r8);
        if (r53 == r22) goto L34;
        r62 = r37;
        r35 = r15;
        r14 = r19;
    L8:
        r2 = new zzxk(this, r20);
        goto L7
    }

    @Override // com.google.mlkit.genai.prompt.Caches
    public final Object delete(String r7, Continuation<? super Boolean> r8) {
        if ((r8 instanceof zzxl) == false) goto L7;
        zzxl r0 = (zzxl) r8;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L17;
        if (r2 == 1) goto L16;
        if (r2 != 2) goto L14;
        ResultKt.throwOnFailure(r82);
        return r82;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        ResultKt.throwOnFailure(r82);
    L19:
        zzxt r83 = (zzxt) r82;
        if (r83 == null) goto L26;
        zzxs r72 = this.zzc;
        Context r6 = this.zza;
        r0.zzc = 2;
        Object r62 = zzxs.zze(r72, r6, r83, r0);
        if (r62 == r12) goto L27;
        return r62;
    L27:
        return r12;
    L26:
        return Boxing.boxBoolean(false);
    L17:
        ResultKt.throwOnFailure(r82);
        zzxs r84 = this.zzc;
        Context r22 = this.zza;
        r0.zzc = 1;
        r82 = zzxs.zza(r84, r22, r7, null, r0);
        if (r82 == r12) goto L27;
    L7:
        r0 = new zzxl(this, r8);
        goto L8
    }

    @Override // com.google.mlkit.genai.prompt.Caches
    public final Object list(Continuation<? super List<CachedContext>> r5) {
        if ((r5 instanceof zzxn) == false) goto L7;
        zzxn r0 = (zzxn) r5;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r52 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r52);
    L16:
        Collection r4 = ((Map) r52).values();
        ArrayList r53 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r4, 10));
        Iterator r42 = r4.iterator();
    L18:
        if (r42.hasNext() == false) goto L20;
        r53.add(CachedContext.Companion.zza(((zzxt) r42.next()).zza()));
        goto L18
    L20:
        return r53;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r52);
        zzxs r54 = this.zzc;
        Context r43 = this.zza;
        r0.zzc = 1;
        r52 = zzxs.zzb(r54, r43, r0);
        if (r52 != r12) goto L16;
        return r12;
    L7:
        r0 = new zzxn(this, r5);
        goto L8
    }
}
