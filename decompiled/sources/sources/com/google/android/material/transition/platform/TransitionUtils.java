package com.google.android.material.transition.platform;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.transition.PathMotion;
import android.transition.PatternPathMotion;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.TypedValue;
import android.view.View;
import androidx.core.graphics.PathParser;
import androidx.lifecycle.Lifecycle$Event$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
class TransitionUtils {
    static final int NO_ATTR_RES_ID = 0;
    static final int NO_DURATION = -1;
    private static final int PATH_TYPE_ARC = 1;
    private static final int PATH_TYPE_LINEAR = 0;
    private static final RectF transformAlphaRectF = null;

    /* renamed from: com.google.android.material.transition.platform.TransitionUtils$1, reason: invalid class name */
    public class AnonymousClass1 implements CornerSizeBinaryOperator {
        final /* synthetic */ RectF val$endBounds;
        final /* synthetic */ float val$endFraction;
        final /* synthetic */ float val$fraction;
        final /* synthetic */ RectF val$startBounds;
        final /* synthetic */ float val$startFraction;

        public AnonymousClass1(RectF r1, RectF r2, float r3, float r4, float r5) {
            this.val$startBounds = r1;
            this.val$endBounds = r2;
            this.val$startFraction = r3;
            this.val$endFraction = r4;
            this.val$fraction = r5;
        }

        @Override // com.google.android.material.transition.platform.TransitionUtils.CornerSizeBinaryOperator
        public CornerSize apply(CornerSize r3, CornerSize r4) {
            return new AbsoluteCornerSize(TransitionUtils.lerp(r3.getCornerSize(this.val$startBounds), r4.getCornerSize(this.val$endBounds), this.val$startFraction, this.val$endFraction, this.val$fraction));
        }
    }

    public interface CornerSizeBinaryOperator {
        CornerSize apply(CornerSize r1, CornerSize r2);
    }

    /* renamed from: $r8$lambda$xiXAgDycS-IjhJm4n2m4u2lpJbA, reason: not valid java name */
    public static /* synthetic */ CornerSize m1913$r8$lambda$xiXAgDycSIjhJm4n2m4u2lpJbA(RectF r0, CornerSize r1) {
        return RelativeCornerSize.createFromCornerSize(r0, r1);
    }

    static {
        transformAlphaRectF = new RectF();
    }

    private TransitionUtils() {
    }

    public static float calculateArea(RectF r1) {
        return r1.width() * r1.height();
    }

    public static ShapeAppearanceModel convertToRelativeCornerSizes(ShapeAppearanceModel r1, RectF r2) {
        return r1.withTransformedCornerSizes(new TransitionUtils$$ExternalSyntheticLambda0(r2));
    }

    public static Shader createColorShader(int r8) {
        return new LinearGradient(0.0f, 0.0f, 0.0f, 0.0f, r8, r8, Shader.TileMode.CLAMP);
    }

    public static <T> T defaultIfNull(T r0, T r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        return r1;
    }

    public static View findAncestorById(View r2, int r3) {
        String r0 = r2.getResources().getResourceName(r3);
    L3:
        if (r2 == null) goto L10;
        if (r2.getId() == r3) goto L6;
        Object r22 = r2.getParent();
        if ((r22 instanceof View) == false) goto L10;
        r2 = (View) r22;
        goto L3
    L6:
        return r2;
    L10:
        Lifecycle$Event$$ExternalSyntheticBUOutline0.m(r0, " is not a valid ancestor");
        return null;
    }

    public static View findDescendantOrAncestorById(View r1, int r2) {
        View r0 = r1.findViewById(r2);
        if (r0 == null) goto L6;
        return r0;
    L6:
        return findAncestorById(r1, r2);
    }

    public static RectF getLocationInWindow(View r4) {
        int[] r0 = new int[2];
        r4.getLocationInWindow(r0);
        int r1 = r0[0];
        int r02 = r0[1];
        return new RectF(r1, r02, r4.getWidth() + r1, r4.getHeight() + r02);
    }

    public static RectF getRelativeBounds(View r4) {
        return new RectF(r4.getLeft(), r4.getTop(), r4.getRight(), r4.getBottom());
    }

    public static Rect getRelativeBoundsRect(View r4) {
        return new Rect(r4.getLeft(), r4.getTop(), r4.getRight(), r4.getBottom());
    }

    private static boolean isShapeAppearanceSignificant(ShapeAppearanceModel r2, RectF r3) {
        if (r2.getTopLeftCornerSize().getCornerSize(r3) == 0.0f) goto L5;
        return true;
    L5:
        if (r2.getTopRightCornerSize().getCornerSize(r3) == 0.0f) goto L7;
        return true;
    L7:
        if (r2.getBottomRightCornerSize().getCornerSize(r3) == 0.0f) goto L9;
        return true;
    L9:
        if (r2.getBottomLeftCornerSize().getCornerSize(r3) != 0.0f) goto L17;
        return false;
    L17:
        return true;
    }

    public static float lerp(float r0, float r1, float r2) {
        return r0 + (r2 * (r1 - r0));
    }

    public static float lerp(float r6, float r7, float r8, float r9, float r10) {
        return lerp(r6, r7, r8, r9, r10, false);
    }

    public static float lerp(float r0, float r1, float r2, float r3, float r4, boolean r5) {
        if (r5 == false) goto L10;
        if (r4 < 0.0f) goto L8;
        if (r4 <= 1.0f) goto L10;
    L8:
        return lerp(r0, r1, r4);
    L10:
        if (r4 >= r2) goto L13;
        return r0;
    L13:
        if (r4 <= r3) goto L16;
        return r1;
    L16:
        return lerp(r0, r1, (r4 - r2) / (r3 - r2));
    }

    public static int lerp(int r1, int r2, float r3, float r4, float r5) {
        if (r5 >= r3) goto L6;
        return r1;
    L6:
        if (r5 <= r4) goto L9;
        return r2;
    L9:
        return (int) lerp(r1, r2, (r5 - r3) / (r4 - r3));
    }

    public static ShapeAppearanceModel lerp(ShapeAppearanceModel r7, ShapeAppearanceModel r8, RectF r9, RectF r10, float r11, float r12, float r13) {
        if (r13 >= r11) goto L6;
        return r7;
    L6:
        if (r13 <= r12) goto L9;
        return r8;
    L9:
        return transformCornerSizes(r7, r8, r9, new AnonymousClass1(r9, r10, r11, r12, r13));
    }

    public static void maybeAddTransition(TransitionSet r0, Transition r1) {
        if (r1 == null) goto L5;
        r0.addTransition(r1);
        return;
    }

    public static boolean maybeApplyThemeDuration(Transition r4, Context r5, int r6) {
        if (r6 != 0) goto L4;
        return false;
    L4:
        if (r4.getDuration() != (-1)) goto L11;
        int r52 = MotionUtils.resolveThemeDuration(r5, r6, -1);
        if (r52 == (-1)) goto L12;
        r4.setDuration(r52);
        return true;
    L12:
        return false;
    L11:
        return false;
    }

    public static boolean maybeApplyThemeInterpolator(Transition r1, Context r2, int r3, TimeInterpolator r4) {
        if (r3 != 0) goto L4;
        return false;
    L4:
        if (r1.getInterpolator() != null) goto L9;
        r1.setInterpolator(MotionUtils.resolveThemeInterpolator(r2, r3, r4));
        return true;
    L9:
        return false;
    }

    public static boolean maybeApplyThemePath(Transition r0, Context r1, int r2) {
        if (r2 == 0) goto L7;
        PathMotion r12 = resolveThemePath(r1, r2);
        if (r12 == null) goto L9;
        r0.setPathMotion(r12);
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    public static void maybeRemoveTransition(TransitionSet r0, Transition r1) {
        if (r1 == null) goto L5;
        r0.removeTransition(r1);
        return;
    }

    public static PathMotion resolveThemePath(Context r3, int r4) {
        TypedValue r0 = new TypedValue();
        if (r3.getTheme().resolveAttribute(r4, r0, true) == false) goto L19;
        int r32 = r0.type;
        if (r32 != 16) goto L15;
        int r33 = r0.data;
        if (r33 != 0) goto L9;
        return null;
    L9:
        if (r33 == 1) goto L11;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid motion path type: ", r33);
        return null;
    L11:
        return new MaterialArcMotion();
    L15:
        if (r32 == 3) goto L17;
        f$$ExternalSyntheticBUOutline1.m("Motion path theme attribute must either be an enum value or path data string");
        goto L19
    L17:
        return new PatternPathMotion(PathParser.createPathFromPathData(String.valueOf(r0.string)));
    L19:
        return null;
    }

    private static int saveLayerAlphaCompat(Canvas r1, Rect r2, int r3) {
        RectF r0 = transformAlphaRectF;
        r0.set(r2);
        return r1.saveLayerAlpha(r0, r3);
    }

    public static void transform(Canvas r1, Rect r2, float r3, float r4, float r5, int r6, CanvasCompat.CanvasOperation r7) {
        if (r6 > 0) goto L4;
        return;
    L4:
        int r0 = r1.save();
        r1.translate(r3, r4);
        r1.scale(r5, r5);
        if (r6 >= 255) goto L7;
        saveLayerAlphaCompat(r1, r2, r6);
    L7:
        r7.run(r1);
        r1.restoreToCount(r0);
    }

    public static ShapeAppearanceModel transformCornerSizes(ShapeAppearanceModel r2, ShapeAppearanceModel r3, RectF r4, CornerSizeBinaryOperator r5) {
        if (isShapeAppearanceSignificant(r2, r4) == false) goto L5;
        ShapeAppearanceModel r42 = r2;
    L7:
        return r42.toBuilder().setTopLeftCornerSize(r5.apply(r2.getTopLeftCornerSize(), r3.getTopLeftCornerSize())).setTopRightCornerSize(r5.apply(r2.getTopRightCornerSize(), r3.getTopRightCornerSize())).setBottomLeftCornerSize(r5.apply(r2.getBottomLeftCornerSize(), r3.getBottomLeftCornerSize())).setBottomRightCornerSize(r5.apply(r2.getBottomRightCornerSize(), r3.getBottomRightCornerSize())).build();
    L5:
        r42 = r3;
        goto L7
    }
}
