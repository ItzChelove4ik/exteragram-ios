package com.google.mlkit.genai.prompt;

import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \r2\u00020\u0001:\u0002\f\rR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0086\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/google/mlkit/genai/prompt/GenerationConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "workerExecutor", "Ljava/util/concurrent/ExecutorService;", "modelConfig", "Lcom/google/mlkit/genai/prompt/ModelConfig;", "<init>", "(Ljava/util/concurrent/ExecutorService;Lcom/google/mlkit/genai/prompt/ModelConfig;)V", "getWorkerExecutor", "()Ljava/util/concurrent/ExecutorService;", "getModelConfig", "()Lcom/google/mlkit/genai/prompt/ModelConfig;", "Builder", "Companion", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class GenerationConfig {
    public static final Companion Companion = null;
    private final ExecutorService zza;
    private final ModelConfig zzb;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/google/mlkit/genai/prompt/GenerationConfig$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Lcom/google/mlkit/genai/prompt/GenerationConfig;", "build", "()Lcom/google/mlkit/genai/prompt/GenerationConfig;", "Lcom/google/mlkit/genai/prompt/ModelConfig;", "modelConfig", "Lcom/google/mlkit/genai/prompt/ModelConfig;", "getModelConfig", "()Lcom/google/mlkit/genai/prompt/ModelConfig;", "setModelConfig", "(Lcom/google/mlkit/genai/prompt/ModelConfig;)V", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Builder {
        private ExecutorService zza;
        private ModelConfig zzb;

        public Builder() {
            this.zzb = ModelConfig.Companion.getDEFAULT();
        }

        public final GenerationConfig build() {
            return new GenerationConfig(this.zza, this.zzb, null);
        }

        public final void setModelConfig(ModelConfig r1) {
            this.zzb = r1;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/mlkit/genai/prompt/GenerationConfig$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
        }
    }

    static {
        Companion = new Companion(null);
    }

    public /* synthetic */ GenerationConfig(ExecutorService r1, ModelConfig r2, DefaultConstructorMarker r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final ModelConfig getModelConfig() {
        return this.zzb;
    }

    public final ExecutorService getWorkerExecutor() {
        return this.zza;
    }
}
