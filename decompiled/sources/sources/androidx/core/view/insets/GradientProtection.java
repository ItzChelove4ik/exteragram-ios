package androidx.core.view.insets;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.animation.PathInterpolator;

/* loaded from: classes3.dex */
public class GradientProtection extends Protection {
    private static final float[] ALPHAS = null;
    private int mColor;
    private final int[] mColors;
    private final GradientDrawable mDrawable;
    private boolean mHasColor;
    private float mScale;

    static {
        float[] r0 = new float[100];
        ALPHAS = r0;
        PathInterpolator r1 = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
        int r02 = r0.length - 1;
        int r2 = r02;
    L3:
        if (r2 < 0) goto L5;
        ALPHAS[r2] = r1.getInterpolation((r02 - r2) / r02);
        r2 = r2 - 1;
        goto L3
    }

    public GradientProtection(int r3) {
        super(r3);
        GradientDrawable r0 = new GradientDrawable();
        this.mDrawable = r0;
        this.mColors = new int[ALPHAS.length];
        this.mColor = 0;
        this.mScale = 1.2f;
        if (r3 != 1) goto L5;
        r0.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        return;
    L5:
        if (r3 != 2) goto L7;
        r0.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        return;
    L7:
        if (r3 != 4) goto L9;
        r0.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
        return;
    L9:
        if (r3 == 8) goto L11;
        return;
    L11:
        r0.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
    }

    public GradientProtection(int r1, int r2) {
        this(r1);
        setColor(r2);
    }

    private void setColorInner(int r2) {
        if (this.mColor == r2) goto L6;
        this.mColor = r2;
        toColors(r2, this.mColors);
        this.mDrawable.setColors(this.mColors);
        setDrawable(this.mDrawable);
        return;
    }

    private static void toColors(int r5, int[] r6) {
        int r0 = r6.length - 1;
    L3:
        if (r0 < 0) goto L5;
        r6[r0] = Color.argb((int) (ALPHAS[r0] * Color.alpha(r5)), Color.red(r5), Color.green(r5), Color.blue(r5));
        r0 = r0 - 1;
        goto L3
    }

    @Override // androidx.core.view.insets.Protection
    public void dispatchColorHint(int r2) {
        if (this.mHasColor == true) goto L6;
        setColorInner(r2);
        return;
    }

    @Override // androidx.core.view.insets.Protection
    public int getThickness(int r1) {
        return (int) (this.mScale * r1);
    }

    public void setColor(int r2) {
        this.mHasColor = true;
        setColorInner(r2);
    }
}
