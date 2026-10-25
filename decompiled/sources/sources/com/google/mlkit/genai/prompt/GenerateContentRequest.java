package com.google.mlkit.genai.prompt;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import com.google.mlkit.genai.prompt.Content;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0002-,R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u000e\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\u000f\u0010\u000bR\u0017\u0010\u0010\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u0010\u0010\t\u001a\u0004\b\u0011\u0010\u000bR\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010#\u001a\u0004\u0018\u00010\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010(\u001a\u00020'8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+¨\u0006."}, d2 = {"Lcom/google/mlkit/genai/prompt/GenerateContentRequest;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "temperature", "F", "getTemperature", "()F", _UrlKt.FRAGMENT_ENCODE_SET, "seed", "I", "getSeed", "()I", "topK", "getTopK", "candidateCount", "getCandidateCount", "maxOutputTokens", "getMaxOutputTokens", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/mlkit/genai/prompt/Content;", "contents", "Ljava/util/List;", "getContents", "()Ljava/util/List;", "Lcom/google/mlkit/genai/prompt/PromptPrefix;", "promptPrefix", "Lcom/google/mlkit/genai/prompt/PromptPrefix;", "getPromptPrefix", "()Lcom/google/mlkit/genai/prompt/PromptPrefix;", _UrlKt.FRAGMENT_ENCODE_SET, "cachedContextName", "Ljava/lang/String;", "getCachedContextName", "()Ljava/lang/String;", "Lcom/google/mlkit/genai/prompt/SystemInstruction;", "systemInstruction", "Lcom/google/mlkit/genai/prompt/SystemInstruction;", "getSystemInstruction", "()Lcom/google/mlkit/genai/prompt/SystemInstruction;", _UrlKt.FRAGMENT_ENCODE_SET, "enableThinking", "Z", "getEnableThinking", "()Z", "Companion", "Builder", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class GenerateContentRequest {
    public static final Companion Companion = null;
    private final float zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final List zzf;
    private final PromptPrefix zzg;
    private final String zzh;
    private final SystemInstruction zzi;
    private final boolean zzj;

    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\fB\u0019\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\u000fB!\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010!\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010'\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010\"\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R$\u0010*\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010\"\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R$\u0010-\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010\"\u001a\u0004\b.\u0010$\"\u0004\b/\u0010&R$\u00101\u001a\u0004\u0018\u0001008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R$\u00108\u001a\u0004\u0018\u0001078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\"\u0010?\u001a\u00020>8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010D¨\u0006E"}, d2 = {"Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/mlkit/genai/prompt/Content;", "contents", "<init>", "(Ljava/util/List;)V", "Lcom/google/mlkit/genai/prompt/TextPart;", "text", "(Lcom/google/mlkit/genai/prompt/TextPart;)V", "Lcom/google/mlkit/genai/prompt/ImagePart;", "image", "(Lcom/google/mlkit/genai/prompt/ImagePart;Lcom/google/mlkit/genai/prompt/TextPart;)V", "Lcom/google/mlkit/genai/prompt/SystemInstruction;", "systemInstruction", "(Lcom/google/mlkit/genai/prompt/SystemInstruction;Lcom/google/mlkit/genai/prompt/TextPart;)V", "(Lcom/google/mlkit/genai/prompt/SystemInstruction;Lcom/google/mlkit/genai/prompt/ImagePart;Lcom/google/mlkit/genai/prompt/TextPart;)V", "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;", "build", "()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;", "Lcom/google/mlkit/genai/prompt/SystemInstruction;", "getSystemInstruction", "()Lcom/google/mlkit/genai/prompt/SystemInstruction;", "setSystemInstruction", "(Lcom/google/mlkit/genai/prompt/SystemInstruction;)V", _UrlKt.FRAGMENT_ENCODE_SET, "temperature", "Ljava/lang/Float;", "getTemperature", "()Ljava/lang/Float;", "setTemperature", "(Ljava/lang/Float;)V", _UrlKt.FRAGMENT_ENCODE_SET, "seed", "Ljava/lang/Integer;", "getSeed", "()Ljava/lang/Integer;", "setSeed", "(Ljava/lang/Integer;)V", "topK", "getTopK", "setTopK", "candidateCount", "getCandidateCount", "setCandidateCount", "maxOutputTokens", "getMaxOutputTokens", "setMaxOutputTokens", "Lcom/google/mlkit/genai/prompt/PromptPrefix;", "promptPrefix", "Lcom/google/mlkit/genai/prompt/PromptPrefix;", "getPromptPrefix", "()Lcom/google/mlkit/genai/prompt/PromptPrefix;", "setPromptPrefix", "(Lcom/google/mlkit/genai/prompt/PromptPrefix;)V", _UrlKt.FRAGMENT_ENCODE_SET, "cachedContextName", "Ljava/lang/String;", "getCachedContextName", "()Ljava/lang/String;", "setCachedContextName", "(Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "enableThinking", "Z", "getEnableThinking", "()Z", "setEnableThinking", "(Z)V", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Builder {
        private final List zza;
        private SystemInstruction zzb;
        private Float zzc;
        private Integer zzd;
        private Integer zze;
        private Integer zzf;
        private Integer zzg;
        private PromptPrefix zzh;
        private String zzi;
        private boolean zzj;

        public Builder(ImagePart r2, TextPart r3) {
            Content.Builder r0 = Content.Companion.builder();
            r0.addPart(r2);
            r0.addPart(r3);
            this.zza = CollectionsKt.listOf(r0.build());
        }

        public Builder(SystemInstruction r1, ImagePart r2, TextPart r3) {
            this.zzb = r1;
            Content.Builder r12 = Content.Companion.builder();
            r12.addPart(r2);
            r12.addPart(r3);
            this.zza = CollectionsKt.listOf(r12.build());
        }

        public Builder(SystemInstruction r1, TextPart r2) {
            this.zzb = r1;
            Content.Builder r12 = Content.Companion.builder();
            r12.addPart(r2);
            this.zza = CollectionsKt.listOf(r12.build());
        }

        public Builder(TextPart r2) {
            Content.Builder r0 = Content.Companion.builder();
            r0.addPart(r2);
            this.zza = CollectionsKt.listOf(r0.build());
        }

        public Builder(List<Content> r1) {
            this.zza = r1;
        }

        public final GenerateContentRequest build() {
            List r6 = this.zza;
            List r0 = r6;
            if (r0 != null) goto L5;
        L89:
            f$$ExternalSyntheticBUOutline1.m("contents cannot be empty");
            return null;
        L5:
            if (r0.isEmpty() == true) goto L89;
            Float r02 = this.zzc;
            float r2 = 0.0f;
            if (r02 == null) goto L15;
            float r3 = r02.floatValue();
            if (r3 >= 0.0f) goto L11;
        L13:
            f$$ExternalSyntheticBUOutline1.m("temperature must be between 0 and 1");
            return null;
        L11:
            if (r3 > 1.0f) goto L13;
            r2 = r02.floatValue();
        L15:
            Integer r03 = this.zzd;
            boolean r32 = false;
            if (r03 != null) goto L18;
            int r04 = 0;
        L23:
            Integer r4 = this.zze;
            if (r4 != null) goto L26;
            int r42 = 3;
        L31:
            Integer r5 = this.zzf;
            if (r5 == null) goto L40;
            int r8 = r5.intValue();
            if (r8 > 0) goto L36;
        L38:
            f$$ExternalSyntheticBUOutline1.m("candidateCount must be between 1 and 8");
            return null;
        L36:
            if (r8 > 8) goto L38;
            int r52 = r5.intValue();
        L41:
            Integer r82 = this.zzg;
            int r9 = 4096;
            if (r82 == null) goto L49;
            int r10 = r82.intValue();
            if (r10 <= 0) goto L47;
            if (r10 > 4096) goto L47;
            r9 = r82.intValue();
        L47:
            zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r10).length() + 52, "maxOutputTokens must be between 1 and 4096, but was ", r10);
            return null;
        L49:
            if (r6 == null) goto L69;
            List r83 = r6;
            if ((r83 instanceof Collection) == true) goto L53;
        L55:
            Iterator r84 = r83.iterator();
        L57:
            if (r84.hasNext() == false) goto L69;
            List<Part> r102 = ((Content) r84.next()).getParts();
            if ((r102 instanceof Collection) == false) goto L63;
            if (r102.isEmpty() == true) goto L57;
        L63:
            Iterator<T> r103 = r102.iterator();
        L65:
            if (r103.hasNext() == false) goto L57;
            if ((((Part) r103.next()) instanceof ImagePart) == false) goto L65;
            r32 = true;
            goto L69
        L53:
            if (r83.isEmpty() == false) goto L55;
        L69:
            PromptPrefix r7 = this.zzh;
            if (r7 == null) goto L75;
            if (r32 == false) goto L75;
            f$$ExternalSyntheticBUOutline1.m("promptPrefix is not supported for image input");
            return null;
        L75:
            if (r7 != null) goto L77;
        L81:
            String r85 = this.zzi;
            if (r85 == null) goto L88;
            if (r32 == false) goto L88;
            f$$ExternalSyntheticBUOutline1.m("cacheContextName is not supported for image input");
            return null;
        L88:
            return new GenerateContentRequest(r2, r04, r42, r52, r9, r6, r7, r85, this.zzb, this.zzj, null);
        L77:
            if (this.zzi == null) goto L81;
            f$$ExternalSyntheticBUOutline1.m("promptPrefix and cacheContextName cannot be set at the same time");
            return null;
        L40:
            r52 = 1;
            goto L41
        L26:
            if (r4.intValue() <= 0) goto L28;
            r42 = r4.intValue();
            goto L31
        L28:
            f$$ExternalSyntheticBUOutline1.m("topK must be positive");
            return null;
        L18:
            if (r03.intValue() < 0) goto L20;
            r04 = r03.intValue();
            goto L23
        L20:
            f$$ExternalSyntheticBUOutline1.m("seed must be non-negative");
            return null;
        }

        public final void setCachedContextName(String r1) {
            this.zzi = r1;
        }

        public final void setCandidateCount(Integer r1) {
            this.zzf = r1;
        }

        public final void setEnableThinking(boolean r1) {
            this.zzj = r1;
        }

        public final void setMaxOutputTokens(Integer r1) {
            this.zzg = r1;
        }

        public final void setPromptPrefix(PromptPrefix r1) {
            this.zzh = r1;
        }

        public final void setSeed(Integer r1) {
            this.zzd = r1;
        }

        public final void setSystemInstruction(SystemInstruction r1) {
            this.zzb = r1;
        }

        public final void setTemperature(Float r1) {
            this.zzc = r1;
        }

        public final void setTopK(Integer r1) {
            this.zze = r1;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
        }
    }

    static {
        Companion = new Companion(null);
    }

    public /* synthetic */ GenerateContentRequest(float r1, int r2, int r3, int r4, int r5, List r6, PromptPrefix r7, String r8, SystemInstruction r9, boolean r10, DefaultConstructorMarker r11) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r10;
    }

    public final String getCachedContextName() {
        return this.zzh;
    }

    public final int getCandidateCount() {
        return this.zzd;
    }

    public final List<Content> getContents() {
        return this.zzf;
    }

    public final boolean getEnableThinking() {
        return this.zzj;
    }

    public final int getMaxOutputTokens() {
        return this.zze;
    }

    public final PromptPrefix getPromptPrefix() {
        return this.zzg;
    }

    public final int getSeed() {
        return this.zzb;
    }

    public final SystemInstruction getSystemInstruction() {
        return this.zzi;
    }

    public final float getTemperature() {
        return this.zza;
    }

    public final int getTopK() {
        return this.zzc;
    }

    public final Builder zza(ImagePart r1, TextPart r2) {
        Builder r12 = new Builder(this.zzf);
        r12.setTemperature(Float.valueOf(this.zza));
        r12.setSeed(Integer.valueOf(this.zzb));
        r12.setTopK(Integer.valueOf(this.zzc));
        r12.setCandidateCount(Integer.valueOf(this.zzd));
        r12.setMaxOutputTokens(Integer.valueOf(this.zze));
        r12.setPromptPrefix(this.zzg);
        r12.setCachedContextName(this.zzh);
        r12.setSystemInstruction(this.zzi);
        r12.setEnableThinking(this.zzj);
        return r12;
    }
}
