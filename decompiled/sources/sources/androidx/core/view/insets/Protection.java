package androidx.core.view.insets;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.Insets;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public abstract class Protection {
    private static final Interpolator DEFAULT_INTERPOLATOR_FADE_IN = null;
    private static final Interpolator DEFAULT_INTERPOLATOR_FADE_OUT = null;
    private static final Interpolator DEFAULT_INTERPOLATOR_MOVE_IN = null;
    private static final Interpolator DEFAULT_INTERPOLATOR_MOVE_OUT = null;
    private final Attributes mAttributes;
    private Object mController;
    private Insets mInsets;
    private Insets mInsetsIgnoringVisibility;
    private final int mSide;
    private float mSystemAlpha;
    private float mSystemInsetAmount;
    private float mUserAlpha;
    private ValueAnimator mUserAlphaAnimator;
    private float mUserInsetAmount;
    private ValueAnimator mUserInsetAmountAnimator;

    public static class Attributes {
        private float mAlpha;
        private Callback mCallback;
        private Drawable mDrawable;
        private int mHeight;
        private Insets mMargin;
        private float mTranslationX;
        private float mTranslationY;
        private boolean mVisible;
        private int mWidth;

        public interface Callback {
            void onAlphaChanged(float r1);

            void onDrawableChanged(Drawable r1);

            void onHeightChanged(int r1);

            void onMarginChanged(Insets r1);

            void onTranslationXChanged(float r1);

            void onTranslationYChanged(float r1);

            void onVisibilityChanged(boolean r1);

            void onWidthChanged(int r1);
        }

        public Attributes() {
            this.mWidth = -1;
            this.mHeight = -1;
            this.mMargin = Insets.NONE;
            this.mVisible = false;
            this.mDrawable = null;
            this.mTranslationX = 0.0f;
            this.mTranslationY = 0.0f;
            this.mAlpha = 1.0f;
        }

        public static /* synthetic */ void access$000(Attributes r0, Insets r1) {
            r0.setMargin(r1);
        }

        public static /* synthetic */ void access$100(Attributes r0, int r1) {
            r0.setWidth(r1);
        }

        public static /* synthetic */ void access$200(Attributes r0, int r1) {
            r0.setHeight(r1);
        }

        public static /* synthetic */ void access$300(Attributes r0, boolean r1) {
            r0.setVisible(r1);
        }

        public static /* synthetic */ void access$400(Attributes r0, float r1) {
            r0.setAlpha(r1);
        }

        public static /* synthetic */ int access$500(Attributes r0) {
            return r0.mWidth;
        }

        public static /* synthetic */ void access$600(Attributes r0, float r1) {
            r0.setTranslationX(r1);
        }

        public static /* synthetic */ int access$700(Attributes r0) {
            return r0.mHeight;
        }

        public static /* synthetic */ void access$800(Attributes r0, float r1) {
            r0.setTranslationY(r1);
        }

        public static /* synthetic */ void access$900(Attributes r0, Drawable r1) {
            r0.setDrawable(r1);
        }

        private void setAlpha(float r2) {
            if (this.mAlpha == r2) goto L8;
            this.mAlpha = r2;
            Callback r1 = this.mCallback;
            if (r1 == null) goto L9;
            r1.onAlphaChanged(r2);
            return;
        L9:
            return;
        }

        private void setDrawable(Drawable r1) {
            this.mDrawable = r1;
            Callback r0 = this.mCallback;
            if (r0 == null) goto L6;
            r0.onDrawableChanged(r1);
            return;
        }

        private void setHeight(int r2) {
            if (this.mHeight == r2) goto L8;
            this.mHeight = r2;
            Callback r1 = this.mCallback;
            if (r1 == null) goto L9;
            r1.onHeightChanged(r2);
            return;
        L9:
            return;
        }

        private void setMargin(Insets r2) {
            if (this.mMargin.equals(r2) == true) goto L8;
            this.mMargin = r2;
            Callback r1 = this.mCallback;
            if (r1 == null) goto L9;
            r1.onMarginChanged(r2);
            return;
        L9:
            return;
        }

        private void setTranslationX(float r2) {
            if (this.mTranslationX == r2) goto L8;
            this.mTranslationX = r2;
            Callback r1 = this.mCallback;
            if (r1 == null) goto L9;
            r1.onTranslationXChanged(r2);
            return;
        L9:
            return;
        }

        private void setTranslationY(float r2) {
            if (this.mTranslationY == r2) goto L8;
            this.mTranslationY = r2;
            Callback r1 = this.mCallback;
            if (r1 == null) goto L9;
            r1.onTranslationYChanged(r2);
            return;
        L9:
            return;
        }

        private void setVisible(boolean r2) {
            if (this.mVisible == r2) goto L8;
            this.mVisible = r2;
            Callback r1 = this.mCallback;
            if (r1 == null) goto L9;
            r1.onVisibilityChanged(r2);
            return;
        L9:
            return;
        }

        private void setWidth(int r2) {
            if (this.mWidth == r2) goto L8;
            this.mWidth = r2;
            Callback r1 = this.mCallback;
            if (r1 == null) goto L9;
            r1.onWidthChanged(r2);
            return;
        L9:
            return;
        }

        public float getAlpha() {
            return this.mAlpha;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public Insets getMargin() {
            return this.mMargin;
        }

        public float getTranslationX() {
            return this.mTranslationX;
        }

        public float getTranslationY() {
            return this.mTranslationY;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public boolean isVisible() {
            return this.mVisible;
        }

        public void setCallback(Callback r2) {
            if (this.mCallback == null) goto L8;
            if (r2 == null) goto L8;
            Segment$$ExternalSyntheticBUOutline1.m("Trying to overwrite the existing callback. Did you send one protection to multiple ProtectionLayouts?");
            return;
        L8:
            this.mCallback = r2;
        }
    }

    static {
        DEFAULT_INTERPOLATOR_MOVE_IN = new PathInterpolator(0.0f, 0.0f, 0.0f, 1.0f);
        DEFAULT_INTERPOLATOR_MOVE_OUT = new PathInterpolator(0.6f, 0.0f, 1.0f, 1.0f);
        DEFAULT_INTERPOLATOR_FADE_IN = new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);
        DEFAULT_INTERPOLATOR_FADE_OUT = new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
    }

    public Protection(int r2) {
        this.mAttributes = new Attributes();
        Insets r0 = Insets.NONE;
        this.mInsets = r0;
        this.mInsetsIgnoringVisibility = r0;
        this.mSystemAlpha = 1.0f;
        this.mUserAlpha = 1.0f;
        this.mSystemInsetAmount = 1.0f;
        this.mUserInsetAmount = 1.0f;
        this.mController = null;
        this.mUserAlphaAnimator = null;
        this.mUserInsetAmountAnimator = null;
        if (r2 != 1) goto L5;
    L13:
        this.mSide = r2;
        return;
    L5:
        if (r2 == 2) goto L13;
        if (r2 == 4) goto L13;
        if (r2 == 8) goto L13;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unexpected side: ", r2);
        throw null;
    }

    private void updateAlpha() {
        Attributes.access$400(this.mAttributes, this.mSystemAlpha * this.mUserAlpha);
    }

    private void updateInsetAmount() {
        float r0 = this.mUserInsetAmount * this.mSystemInsetAmount;
        int r1 = this.mSide;
        if (r1 != 1) goto L5;
        Attributes.access$600(this.mAttributes, (-(1.0f - r0)) * Attributes.access$500(r4));
        return;
    L5:
        if (r1 != 2) goto L7;
        Attributes.access$800(this.mAttributes, (-(1.0f - r0)) * Attributes.access$700(r4));
        return;
    L7:
        if (r1 != 4) goto L9;
        Attributes.access$600(this.mAttributes, (1.0f - r0) * Attributes.access$500(r4));
        return;
    L9:
        if (r1 == 8) goto L11;
        return;
    L11:
        Attributes.access$800(this.mAttributes, (1.0f - r0) * Attributes.access$700(r4));
    }

    public abstract void dispatchColorHint(int r1);

    public Insets dispatchInsets(Insets r1, Insets r2, Insets r3) {
        this.mInsets = r1;
        this.mInsetsIgnoringVisibility = r2;
        Attributes.access$000(this.mAttributes, r3);
        return updateLayout();
    }

    public Attributes getAttributes() {
        return this.mAttributes;
    }

    public Object getController() {
        return this.mController;
    }

    public int getSide() {
        return this.mSide;
    }

    public abstract int getThickness(int r1);

    public boolean occupiesCorners() {
        return false;
    }

    public void setController(Object r1) {
        this.mController = r1;
    }

    public void setDrawable(Drawable r1) {
        Attributes.access$900(this.mAttributes, r1);
    }

    public void setSystemAlpha(float r1) {
        this.mSystemAlpha = r1;
        updateAlpha();
    }

    public void setSystemInsetAmount(float r1) {
        this.mSystemInsetAmount = r1;
        updateInsetAmount();
    }

    public void setSystemVisible(boolean r1) {
        Attributes.access$300(this.mAttributes, r1);
    }

    public Insets updateLayout() {
        Insets r0 = Insets.NONE;
        int r1 = this.mSide;
        boolean r2 = false;
        if (r1 != 1) goto L5;
        int r12 = this.mInsets.left;
        Attributes.access$100(this.mAttributes, getThickness(this.mInsetsIgnoringVisibility.left));
        if (occupiesCorners() == false) goto L23;
        r0 = Insets.of(getThickness(r12), 0, 0, 0);
    L23:
        if (r12 <= 0) goto L25;
        r2 = true;
    L25:
        setSystemVisible(r2);
        float r22 = 0.0f;
        if (r12 <= 0) goto L28;
        float r4 = 1.0f;
    L29:
        setSystemAlpha(r4);
        if (r12 <= 0) goto L32;
        r22 = 1.0f;
    L32:
        setSystemInsetAmount(r22);
        return r0;
    L28:
        r4 = 0.0f;
        goto L29
    L5:
        if (r1 != 2) goto L7;
        r12 = this.mInsets.top;
        Attributes.access$200(this.mAttributes, getThickness(this.mInsetsIgnoringVisibility.top));
        if (occupiesCorners() == false) goto L23;
        r0 = Insets.of(0, getThickness(r12), 0, 0);
        goto L23
    L7:
        if (r1 != 4) goto L9;
        r12 = this.mInsets.right;
        Attributes.access$100(this.mAttributes, getThickness(this.mInsetsIgnoringVisibility.right));
        if (occupiesCorners() == false) goto L23;
        r0 = Insets.of(0, 0, getThickness(r12), 0);
        goto L23
    L9:
        if (r1 == 8) goto L11;
        r12 = 0;
        goto L23
    L11:
        r12 = this.mInsets.bottom;
        Attributes.access$200(this.mAttributes, getThickness(this.mInsetsIgnoringVisibility.bottom));
        if (occupiesCorners() == false) goto L23;
        r0 = Insets.of(0, 0, 0, getThickness(r12));
        goto L23
    }
}
