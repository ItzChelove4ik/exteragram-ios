package com.google.android.material.color;

import android.app.Activity;
import android.graphics.Bitmap;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.color.utilities.QuantizerCelebi;
import com.google.android.material.color.utilities.Score;

/* loaded from: classes5.dex */
public class DynamicColorsOptions {
    private static final DynamicColors.Precondition ALWAYS_ALLOW = null;
    private static final DynamicColors.OnAppliedCallback NO_OP_CALLBACK = null;
    private Integer contentBasedSeedColor;
    private final DynamicColors.OnAppliedCallback onAppliedCallback;
    private final DynamicColors.Precondition precondition;
    private final int themeOverlay;

    /* renamed from: com.google.android.material.color.DynamicColorsOptions$1, reason: invalid class name */
    public class AnonymousClass1 implements DynamicColors.Precondition {
        public AnonymousClass1() {
        }

        @Override // com.google.android.material.color.DynamicColors.Precondition
        public boolean shouldApplyDynamicColors(Activity r1, int r2) {
            return true;
        }
    }

    /* renamed from: com.google.android.material.color.DynamicColorsOptions$2, reason: invalid class name */
    public class AnonymousClass2 implements DynamicColors.OnAppliedCallback {
        public AnonymousClass2() {
        }

        @Override // com.google.android.material.color.DynamicColors.OnAppliedCallback
        public void onApplied(Activity r1) {
        }
    }

    public static class Builder {
        private Bitmap contentBasedSourceBitmap;
        private Integer contentBasedSourceColor;
        private DynamicColors.OnAppliedCallback onAppliedCallback;
        private DynamicColors.Precondition precondition;
        private int themeOverlay;

        public Builder() {
            this.precondition = DynamicColorsOptions.access$500();
            this.onAppliedCallback = DynamicColorsOptions.access$600();
        }

        public static /* synthetic */ int access$000(Builder r0) {
            return r0.themeOverlay;
        }

        public static /* synthetic */ DynamicColors.Precondition access$100(Builder r0) {
            return r0.precondition;
        }

        public static /* synthetic */ DynamicColors.OnAppliedCallback access$200(Builder r0) {
            return r0.onAppliedCallback;
        }

        public static /* synthetic */ Integer access$300(Builder r0) {
            return r0.contentBasedSourceColor;
        }

        public static /* synthetic */ Bitmap access$400(Builder r0) {
            return r0.contentBasedSourceBitmap;
        }

        public DynamicColorsOptions build() {
            return new DynamicColorsOptions(this, null);
        }

        public Builder setContentBasedSource(int r2) {
            this.contentBasedSourceBitmap = null;
            this.contentBasedSourceColor = Integer.valueOf(r2);
            return this;
        }

        public Builder setContentBasedSource(Bitmap r1) {
            this.contentBasedSourceBitmap = r1;
            this.contentBasedSourceColor = null;
            return this;
        }

        public Builder setOnAppliedCallback(DynamicColors.OnAppliedCallback r1) {
            this.onAppliedCallback = r1;
            return this;
        }

        public Builder setPrecondition(DynamicColors.Precondition r1) {
            this.precondition = r1;
            return this;
        }

        public Builder setThemeOverlay(int r1) {
            this.themeOverlay = r1;
            return this;
        }
    }

    static {
        ALWAYS_ALLOW = new AnonymousClass1();
        NO_OP_CALLBACK = new AnonymousClass2();
    }

    private DynamicColorsOptions(Builder r2) {
        this.themeOverlay = Builder.access$000(r2);
        this.precondition = Builder.access$100(r2);
        this.onAppliedCallback = Builder.access$200(r2);
        if (Builder.access$300(r2) == null) goto L7;
        this.contentBasedSeedColor = Builder.access$300(r2);
        return;
    L7:
        if (Builder.access$400(r2) == null) goto L10;
        this.contentBasedSeedColor = Integer.valueOf(extractSeedColorFromImage(Builder.access$400(r2)));
        return;
    }

    public /* synthetic */ DynamicColorsOptions(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public static /* synthetic */ DynamicColors.Precondition access$500() {
        return ALWAYS_ALLOW;
    }

    public static /* synthetic */ DynamicColors.OnAppliedCallback access$600() {
        return NO_OP_CALLBACK;
    }

    private static int extractSeedColorFromImage(Bitmap r8) {
        int r3 = r8.getWidth();
        int r7 = r8.getHeight();
        int[] r1 = new int[r3 * r7];
        r8.getPixels(r1, 0, r3, 0, 0, r3, r7);
        return Score.score(QuantizerCelebi.quantize(r1, 128)).get(0).intValue();
    }

    public Integer getContentBasedSeedColor() {
        return this.contentBasedSeedColor;
    }

    public DynamicColors.OnAppliedCallback getOnAppliedCallback() {
        return this.onAppliedCallback;
    }

    public DynamicColors.Precondition getPrecondition() {
        return this.precondition;
    }

    public int getThemeOverlay() {
        return this.themeOverlay;
    }
}
