package com.google.mlkit.genai.prompt;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/google/mlkit/genai/prompt/Candidate;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "text", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "Companion", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Candidate {
    public static final Companion Companion = null;
    private final String zza;
    private final Integer zzb;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001¨\u0006\u000b"}, d2 = {"Lcom/google/mlkit/genai/prompt/Candidate$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Lcom/google/mlkit/genai/prompt/Candidate;", "text", _UrlKt.FRAGMENT_ENCODE_SET, "finishReason", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;Ljava/lang/Integer;)Lcom/google/mlkit/genai/prompt/Candidate;", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
        }

        public static final Candidate zza(String r2, Integer r3) {
            return new Candidate(r2, r3, null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public /* synthetic */ Candidate(String r1, Integer r2, DefaultConstructorMarker r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final String getText() {
        return this.zza;
    }
}
