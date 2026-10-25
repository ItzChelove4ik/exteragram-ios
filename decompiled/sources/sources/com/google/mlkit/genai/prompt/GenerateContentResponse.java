package com.google.mlkit.genai.prompt;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \b2\u00020\u0001:\u0001\bR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/google/mlkit/genai/prompt/GenerateContentResponse;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/mlkit/genai/prompt/Candidate;", "candidates", "Ljava/util/List;", "getCandidates", "()Ljava/util/List;", "Companion", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class GenerateContentResponse {
    public static final Companion Companion = null;
    private final List zza;
    private final List zzb;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001¨\u0006\n"}, d2 = {"Lcom/google/mlkit/genai/prompt/GenerateContentResponse$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;", "candidates", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/mlkit/genai/prompt/Candidate;", "thoughtProcess", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
        }

        public static final GenerateContentResponse zzb(List r2, List r3) {
            return new GenerateContentResponse(r2, r3, null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    private GenerateContentResponse(List r1, List r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public /* synthetic */ GenerateContentResponse(List r1, List r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    public final List<Candidate> getCandidates() {
        return this.zza;
    }
}
