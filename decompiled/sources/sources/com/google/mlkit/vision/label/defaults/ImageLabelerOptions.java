package com.google.mlkit.vision.label.defaults;

import com.google.mlkit.vision.label.ImageLabelerOptionsBase;

/* loaded from: classes5.dex */
public class ImageLabelerOptions extends ImageLabelerOptionsBase {
    public static final ImageLabelerOptions DEFAULT_OPTIONS = null;

    public static class Builder extends ImageLabelerOptionsBase.Builder<Builder> {
        public Builder() {
            setConfidenceThreshold(0.5f);
        }

        public ImageLabelerOptions build() {
            return new ImageLabelerOptions(this, null);
        }

        @Override // com.google.mlkit.vision.label.ImageLabelerOptionsBase.Builder
        public Builder setConfidenceThreshold(float r1) {
            return (Builder) super.setConfidenceThreshold(r1);
        }
    }

    static {
        DEFAULT_OPTIONS = new Builder().build();
    }

    public /* synthetic */ ImageLabelerOptions(Builder r1, zza r2) {
        super(r1);
    }
}
