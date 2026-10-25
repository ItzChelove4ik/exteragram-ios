package com.google.mlkit.nl.languageid;

import com.google.android.gms.common.internal.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public class LanguageIdentificationOptions {
    public static final LanguageIdentificationOptions zza = null;
    private final Float zzb;
    private final Executor zzc;

    public static class Builder {
        private Float zza;
        private Executor zzb;

        public Builder() {
        }

        public LanguageIdentificationOptions build() {
            return new LanguageIdentificationOptions(this.zza, this.zzb, null);
        }
    }

    static {
        zza = new Builder().build();
    }

    public /* synthetic */ LanguageIdentificationOptions(Float r1, Executor r2, zza r3) {
        this.zzb = r1;
        this.zzc = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof LanguageIdentificationOptions) == true) goto L8;
        return false;
    L8:
        LanguageIdentificationOptions r52 = (LanguageIdentificationOptions) r5;
        if (Objects.equal(r52.zzb, this.zzb) == true) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equal(r52.zzc, this.zzc) == false) goto L13;
        return true;
    }

    public Float getConfidenceThreshold() {
        return this.zzb;
    }

    public Executor getExecutor() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zzb, this.zzc});
    }
}
