package com.google.mlkit.genai.prompt;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/google/mlkit/genai/prompt/TextPart;", "Lcom/google/mlkit/genai/prompt/Part;", _UrlKt.FRAGMENT_ENCODE_SET, "textString", "<init>", "(Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getTextString", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class TextPart extends Part {
    private final String zza;

    public TextPart(String r2) {
        super(null);
        this.zza = r2;
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof TextPart) == true) goto L9;
        return false;
    L9:
        if (Intrinsics.areEqual(this.zza, ((TextPart) r4).zza) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public final String getTextString() {
        return this.zza;
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public String toString() {
        String r2 = this.zza;
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 21);
        r1.append("TextPart(textString=");
        r1.append(r2);
        r1.append(")");
        return r1.toString();
    }
}
