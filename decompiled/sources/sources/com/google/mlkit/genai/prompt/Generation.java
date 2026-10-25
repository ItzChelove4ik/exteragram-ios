package com.google.mlkit.genai.prompt;

import com.google.android.gms.internal.mlkit_genai_prompt.zzaah;
import com.google.android.gms.internal.mlkit_genai_prompt.zzaak;
import com.google.android.gms.internal.mlkit_genai_prompt.zzaam;
import com.google.android.gms.internal.mlkit_genai_prompt.zzwf;
import com.google.android.gms.internal.mlkit_genai_prompt.zzwl;
import com.google.android.gms.internal.mlkit_genai_prompt.zzye;
import com.google.android.gms.internal.mlkit_genai_prompt.zzyj;
import com.google.android.gms.internal.mlkit_genai_prompt.zzyt;
import com.google.android.gms.internal.mlkit_genai_prompt.zzzy;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/mlkit/genai/prompt/Generation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/mlkit/genai/prompt/GenerationConfig;", "options", "Lcom/google/mlkit/genai/prompt/GenerativeModel;", "getClient", "(Lcom/google/mlkit/genai/prompt/GenerationConfig;)Lcom/google/mlkit/genai/prompt/GenerativeModel;", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Generation {
    public static final Generation INSTANCE = null;

    static {
        INSTANCE = new Generation();
    }

    private Generation() {
    }

    public final GenerativeModel getClient(GenerationConfig r10) {
        int r9 = zzyt.zzb(r10, zzye.zza);
        ExecutorService r1 = r10.getWorkerExecutor();
        zzaam r4 = zzaam.zza;
        zzaah r2 = new zzaah(r9, r1, new zzyj(r10, r4));
        zzwl r3 = new zzwl(zzyt.zzb(r10, zzye.zzb), r10.getWorkerExecutor(), new zzwf());
        int r12 = zzyt.zzb(r10, zzye.zzc);
        ExecutorService r5 = r10.getWorkerExecutor();
        zzaam r7 = zzaam.zzb;
        return new zzzy(r10, r2, r3, new zzaak(r12, r5, new zzyj(r10, r7)), new zzaah(zzyt.zzb(r10, zzye.zzd), r10.getWorkerExecutor(), new zzyj(r10, r4)), new zzaak(zzyt.zzb(r10, zzye.zze), r10.getWorkerExecutor(), new zzyj(r10, r7)));
    }
}
