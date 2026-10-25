package com.google.mlkit.genai.prompt;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \n2\u00020\u0001:\u0002\t\nR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/google/mlkit/genai/prompt/Content;", _UrlKt.FRAGMENT_ENCODE_SET, "parts", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/mlkit/genai/prompt/Part;", "<init>", "(Ljava/util/List;)V", "getParts", "()Ljava/util/List;", "Builder", "Companion", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Content {
    public static final Companion Companion = null;
    private final List zza;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/google/mlkit/genai/prompt/Content$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Lcom/google/mlkit/genai/prompt/Part;", "part", "addPart", "(Lcom/google/mlkit/genai/prompt/Part;)Lcom/google/mlkit/genai/prompt/Content$Builder;", "Lcom/google/mlkit/genai/prompt/Content;", "build", "()Lcom/google/mlkit/genai/prompt/Content;", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Builder {
        private final List zza;

        public Builder() {
            this.zza = new ArrayList();
        }

        public final Builder addPart(Part r2) {
            this.zza.add(r2);
            return this;
        }

        public final Content build() {
            List r2 = this.zza;
            if (r2.isEmpty() == false) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Parts cannot be empty");
            return null;
        L5:
            return new Content(CollectionsKt.toList(r2), null);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/google/mlkit/genai/prompt/Content$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "builder", "Lcom/google/mlkit/genai/prompt/Content$Builder;", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
        }

        public final Builder builder() {
            return new Builder();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public /* synthetic */ Content(List r1, DefaultConstructorMarker r2) {
        this.zza = r1;
    }

    public final List<Part> getParts() {
        return this.zza;
    }
}
