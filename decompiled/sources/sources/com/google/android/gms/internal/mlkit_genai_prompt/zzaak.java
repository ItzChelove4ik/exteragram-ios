package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;
import com.google.mlkit.genai.common.GenAiException;
import com.google.mlkit.genai.common.StreamingCallback;
import com.google.mlkit.genai.prompt.GenerateContentRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzaak extends zzaur implements AutoCloseable {
    private final ConcurrentHashMap zza;

    public zzaak(int r2, ExecutorService r3, zzaty r4) {
        super(r3, r2, -1, r4);
        CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(zzp()));
        this.zza = new ConcurrentHashMap();
    }

    public static /* synthetic */ Object zzb(zzaak r4, Continuation r5) {
        if ((r5 instanceof zzaai) == false) goto L7;
        zzaai r0 = (zzaai) r5;
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
        r0 = new zzaai(r4, r5);
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.concurrent.ConcurrentHashMap] */
    /* JADX WARN: Type inference failed for: r5v4, types: [com.google.mlkit.genai.prompt.GenerateContentRequest, java.lang.Object] */
    public static /* synthetic */ Object zzc(zzaak r4, zzaan r5, zzaal r6, StreamingCallback r7, Continuation r8) {
        if ((r8 instanceof zzaaj) == false) goto L7;
        zzaaj r0 = (zzaaj) r8;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        zzaan r52 = r0.zze;
        zzaak r42 = r0.zzd;
        ResultKt.throwOnFailure(r82);     // Catch: Throwable -> L14
        zzaak r43 = r42;
        zzaan r53 = r52;
    L22:
        r4 = r43.zza;
        r5 = r53.zza();
        r4.remove(r5);
        return r82;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r82);
        r4.zza.put(r5.zza(), r6);
        r0.zzd = r4;     // Catch: Throwable -> L14
        r0.zze = r5;     // Catch: Throwable -> L14
        r0.zzc = 1;     // Catch: Throwable -> L14
        r82 = r4.zzI(r5, r7, r0);     // Catch: Throwable -> L14
        r43 = r4;
        r53 = r5;
        if (r82 != r12) goto L22;
        return r12;
    L14:
        th = move-exception;
        r4.zza.remove(r5.zza());
        throw th;
    L7:
        r0 = new zzaaj(r4, r8);
        goto L8
    }

    private static final zzhu zzg(zzaal r5) {
        String r0 = r5.zza();
        if (r0.length() == 0) goto L15;
        if (r0.length() > 32000) goto L13;
        ParcelFileDescriptor r1 = r5.zzc();
        if (r5.zzc() == null) goto L11;
        zzhs r52 = zzhs.zzj(0, r0);
        zzht r02 = zzhu.zzc();
        r02.zza(CollectionsKt.listOf(r52));
        r02.zzb(r1);
        return r02.zzc();
    L11:
        throw new GenAiException("File descriptor to read is null", null, -103);
    L13:
        throw new GenAiException("Prompt prefix length exceeds the limit. Please check the countTokens API.", null, 12);
    L15:
        throw new GenAiException("Prompt prefix is empty", null, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaur
    public final Object zza(Continuation r1) {
        return zzb(this, r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaun
    public final zzdp zzd(zzdi r1, zzdq r2) {
        zzii r0 = zzij.zzd(r1);
        r0.zzc(r2);
        return zzig.zzd(r0.zzd());
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaun
    public final /* bridge */ /* synthetic */ Object zze(Object r2, StreamingCallback r3) {
        zzaan r22 = (zzaan) r2;
        GenerateContentRequest r0 = r22.zza();
        zzaal r1 = (zzaal) this.zza.get(r0);
        if (r1 == null) goto L7;
        return zzys.zze(r22, r3, zzg(r1));
    L7:
        throw new GenAiException("Prefix parameters not found for request", null, -103);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaun
    public final /* bridge */ /* synthetic */ Object zzf(Object r1) {
        return zzys.zzd((zzic) r1);
    }
}
