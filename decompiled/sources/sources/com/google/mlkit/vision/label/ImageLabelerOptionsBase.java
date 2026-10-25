package com.google.mlkit.vision.label;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public abstract class ImageLabelerOptionsBase implements MultiFlavorDetectorCreator.DetectorOptions<ImageLabeler> {
    private final Executor zza;
    private final float zzb;

    public static abstract class Builder<B extends Builder<B>> {
        private float zza;
        private Executor zzb;

        public Builder() {
            this.zza = -1.0f;
        }

        public static /* bridge */ /* synthetic */ float zza(Builder r0) {
            return r0.zza;
        }

        public static /* bridge */ /* synthetic */ Executor zzb(Builder r0) {
            return r0.zzb;
        }

        public B setConfidenceThreshold(float r3) {
            boolean r1 = false;
            if (Float.compare(r3, 0.0f) >= 0) goto L5;
        L7:
            Preconditions.checkArgument(r1, "Confidence Threshold should be in range [0.0f, 1.0f].");
            this.zza = r3;
            return this;
        L5:
            if (Float.compare(r3, 1.0f) > 0) goto L7;
            r1 = true;
            goto L7
        }
    }

    public ImageLabelerOptionsBase(Builder<?> r2) {
        this.zzb = Builder.zza(r2);
        this.zza = Builder.zzb(r2);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ImageLabelerOptionsBase) == true) goto L8;
        return false;
    L8:
        ImageLabelerOptionsBase r52 = (ImageLabelerOptionsBase) r5;
        if (getClass().equals(r52.getClass()) == true) goto L11;
    L15:
        return false;
    L11:
        if (Float.compare(this.zzb, r52.zzb) != 0) goto L15;
        if (Objects.equal(r52.zza, this.zza) == false) goto L15;
        return true;
    }

    public float getConfidenceThreshold() {
        return this.zzb;
    }

    public Executor getExecutor() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{getClass(), Float.valueOf(this.zzb), this.zza});
    }
}
