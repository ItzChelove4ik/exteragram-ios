package com.google.mlkit.genai.prompt;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/google/mlkit/genai/prompt/CountTokensResponse;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "totalTokens", "<init>", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "I", "getTotalTokens", "Companion", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CountTokensResponse {
    public static final Companion Companion = null;
    private final int zza;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001¨\u0006\b"}, d2 = {"Lcom/google/mlkit/genai/prompt/CountTokensResponse$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Lcom/google/mlkit/genai/prompt/CountTokensResponse;", "totalTokens", _UrlKt.FRAGMENT_ENCODE_SET, "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CountTokensResponse(int r1) {
        this.zza = r1;
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof CountTokensResponse) == true) goto L9;
        return false;
    L9:
        if (this.zza == ((CountTokensResponse) r4).zza) goto L11;
        return false;
    L11:
        return true;
    }

    public final int getTotalTokens() {
        return this.zza;
    }

    public int hashCode() {
        return Integer.hashCode(this.zza);
    }

    public String toString() {
        int r2 = this.zza;
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 33);
        r1.append("CountTokensResponse(totalTokens=");
        r1.append(r2);
        r1.append(")");
        return r1.toString();
    }
}
