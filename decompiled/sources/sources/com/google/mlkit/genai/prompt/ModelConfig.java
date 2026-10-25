package com.google.mlkit.genai.prompt;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000e\u000fJ\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0086\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0086\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/google/mlkit/genai/prompt/ModelConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "releaseStage", _UrlKt.FRAGMENT_ENCODE_SET, "preference", "<init>", "(II)V", "getReleaseStage", "()I", "getPreference", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", "Builder", "Companion", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ModelConfig {
    public static final Companion Companion = null;
    private static final ModelConfig zzc = null;
    private final int zza;
    private final int zzb;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R(\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0004\b\b\u0010\t\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR*\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\u0012\u0012\u0004\b\u0012\u0010\u0003\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/google/mlkit/genai/prompt/ModelConfig$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Lcom/google/mlkit/genai/prompt/ModelConfig;", "build", "()Lcom/google/mlkit/genai/prompt/ModelConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "releaseStage", "I", "getReleaseStage", "()I", "setReleaseStage", "(I)V", "getReleaseStage$annotations", "value", "getPreference", "setPreference", "getPreference$annotations", "preference", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Builder {
        private int zza;
        private Integer zzb;

        public Builder() {
        }

        public final ModelConfig build() {
            return new ModelConfig(this.zza, getPreference(), null);
        }

        public final int getPreference() {
            Integer r0 = this.zzb;
            if (r0 == null) goto L7;
            return r0.intValue();
        L7:
            if (this.zza == 0) goto L10;
            return 1;
        L10:
            return 2;
        }

        public final void setPreference(int r1) {
            this.zzb = Integer.valueOf(r1);
        }

        public final void setReleaseStage(int r1) {
            this.zza = r1;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001J\b\u0010\u0004\u001a\u00020\u0005H\u0007R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/google/mlkit/genai/prompt/ModelConfig$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "builder", "Lcom/google/mlkit/genai/prompt/ModelConfig$Builder;", PluginsConstants.Strategy.DEFAULT, "Lcom/google/mlkit/genai/prompt/ModelConfig;", "getDEFAULT$annotations", "getDEFAULT", "()Lcom/google/mlkit/genai/prompt/ModelConfig;", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
        }

        @JvmStatic
        public final Builder builder() {
            return new Builder();
        }

        public final ModelConfig getDEFAULT() {
            return ModelConfig.zza();
        }
    }

    static {
        Companion = new Companion(null);
        zzc = new ModelConfig(0, 2);
    }

    private ModelConfig(int r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public /* synthetic */ ModelConfig(int r1, int r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    @JvmStatic
    public static final Builder builder() {
        return Companion.builder();
    }

    public static final /* synthetic */ ModelConfig zza() {
        return zzc;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ModelConfig) == true) goto L8;
        return false;
    L8:
        ModelConfig r52 = (ModelConfig) r5;
        if (this.zza == r52.zza) goto L12;
        return false;
    L12:
        if (this.zzb == r52.zzb) goto L14;
        return false;
    L14:
        return true;
    }

    public final int getPreference() {
        return this.zzb;
    }

    public final int getReleaseStage() {
        return this.zza;
    }

    public int hashCode() {
        return (this.zza * 31) + this.zzb;
    }
}
