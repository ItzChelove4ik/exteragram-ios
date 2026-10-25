package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;
import com.google.mlkit.genai.common.GenAiException;
import com.google.mlkit.genai.common.StreamingCallback;
import com.google.mlkit.genai.prompt.CreateCachedContextRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzwl extends zzaur implements AutoCloseable {
    private final ConcurrentHashMap zza;
    private final ConcurrentHashMap zzb;

    public zzwl(int r2, ExecutorService r3, zzaty r4) {
        super(r3, r2, -1, r4);
        this.zza = new ConcurrentHashMap();
        this.zzb = new ConcurrentHashMap();
        CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(zzp()));
    }

    public static /* synthetic */ Object zzb(zzwl r4, Continuation r5) {
        if ((r5 instanceof zzwg) == false) goto L7;
        zzwg r0 = (zzwg) r5;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r52 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L13;
        ResultKt.throwOnFailure(r52);     // Catch: GenAiException -> L20
        return r52;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r52);
        r0.zzc = 1;     // Catch: GenAiException -> L20
        Object r42 = super.zza(r0);     // Catch: GenAiException -> L20
        if (r42 != r12) goto L19;
        return r12;
    L19:
        return r42;
    L21:
        return Boxing.boxInt(0);
    L7:
        r0 = new zzwg(r4, r5);
        goto L8
    }

    public static /* synthetic */ Object zzc(zzwl r2, CreateCachedContextRequest r3, zzaal r4, Continuation r5) {
        return CoroutineScopeKt.coroutineScope(new zzwk(r4, r2, r3, null), r5);
    }

    public static final /* synthetic */ ConcurrentHashMap zzg(zzwl r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ ConcurrentHashMap zzh(zzwl r0) {
        return r0.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaur
    public final Object zza(Continuation r1) {
        return zzb(this, r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaun
    public final zzdp zzd(zzdi r1, zzdq r2) {
        zzet r0 = zzeu.zzd(r1);
        r0.zzc(r2);
        return zzer.zzd(r0.zzd());
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaun
    public final /* bridge */ /* synthetic */ Object zze(Object r6, StreamingCallback r7) {
        CreateCachedContextRequest r62 = (CreateCachedContextRequest) r6;
        zzaal r72 = (zzaal) this.zzb.get(r62);
        if (r72 == null) goto L19;
        String r2 = r72.zza();
        if (r2.length() == 0) goto L17;
        if (r2.length() > 32000) goto L15;
        ParcelFileDescriptor r73 = r72.zzb();
        if (r73 == null) goto L13;
        zzem r0 = zzeo.zzq();
        r0.zzi(r2);
        r0.zzl(2);
        r0.zza(r73);
        r0.zzm(new zzwh(this, r62));
        return r0.zzk();
    L13:
        throw new GenAiException("File descriptor to write is null", null, -103);
    L15:
        throw new GenAiException("Prompt prefix length exceeds the limit. Please check the countTokens API.", null, 12);
    L17:
        throw new GenAiException("Prompt prefix is empty", null, 0);
    L19:
        throw new GenAiException("Prefix parameters not found for request", null, -103);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaun
    public final /* bridge */ /* synthetic */ Object zzf(Object r1) {
        zzep r12 = (zzep) r1;
        return Boolean.TRUE;
    }
}
