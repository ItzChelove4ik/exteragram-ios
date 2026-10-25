package com.google.android.material.color;

/* loaded from: classes5.dex */
public class ColorContrastOptions {
    private final int highContrastThemeOverlayResourceId;
    private final int mediumContrastThemeOverlayResourceId;

    /* renamed from: com.google.android.material.color.ColorContrastOptions$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Builder {
        private int highContrastThemeOverlayResourceId;
        private int mediumContrastThemeOverlayResourceId;

        public Builder() {
        }

        public static /* synthetic */ int access$000(Builder r0) {
            return r0.mediumContrastThemeOverlayResourceId;
        }

        public static /* synthetic */ int access$100(Builder r0) {
            return r0.highContrastThemeOverlayResourceId;
        }

        public ColorContrastOptions build() {
            return new ColorContrastOptions(this, null);
        }

        public Builder setHighContrastThemeOverlay(int r1) {
            this.highContrastThemeOverlayResourceId = r1;
            return this;
        }

        public Builder setMediumContrastThemeOverlay(int r1) {
            this.mediumContrastThemeOverlayResourceId = r1;
            return this;
        }
    }

    private ColorContrastOptions(Builder r2) {
        this.mediumContrastThemeOverlayResourceId = Builder.access$000(r2);
        this.highContrastThemeOverlayResourceId = Builder.access$100(r2);
    }

    public /* synthetic */ ColorContrastOptions(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public int getHighContrastThemeOverlay() {
        return this.highContrastThemeOverlayResourceId;
    }

    public int getMediumContrastThemeOverlay() {
        return this.mediumContrastThemeOverlayResourceId;
    }
}
