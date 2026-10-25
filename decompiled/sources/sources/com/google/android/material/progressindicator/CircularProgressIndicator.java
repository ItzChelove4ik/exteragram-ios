package com.google.android.material.progressindicator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class CircularProgressIndicator extends BaseProgressIndicator<CircularProgressIndicatorSpec> {
    public static final int DEF_STYLE_RES = 0;
    public static final int INDETERMINATE_ANIMATION_TYPE_ADVANCE = 0;
    public static final int INDETERMINATE_ANIMATION_TYPE_RETREAT = 1;
    public static final int INDICATOR_DIRECTION_CLOCKWISE = 0;
    public static final int INDICATOR_DIRECTION_COUNTERCLOCKWISE = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface IndeterminateAnimationType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IndicatorDirection {
    }

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_CircularProgressIndicator;
    }

    public CircularProgressIndicator(Context r2) {
        this(r2, null);
    }

    public CircularProgressIndicator(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicator(Context r2, AttributeSet r3, int r4) {
        super(r2, r3, r4, DEF_STYLE_RES);
        initializeDrawables();
        this.initialized = true;
    }

    private void initializeDrawables() {
        CircularDrawingDelegate r0 = new CircularDrawingDelegate((CircularProgressIndicatorSpec) this.spec);
        setIndeterminateDrawable(IndeterminateDrawable.createCircularDrawable(getContext(), (CircularProgressIndicatorSpec) this.spec, r0));
        setProgressDrawable(DeterminateDrawable.createCircularDrawable(getContext(), (CircularProgressIndicatorSpec) this.spec, r0));
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public /* bridge */ /* synthetic */ BaseProgressIndicatorSpec createSpec(Context r1, AttributeSet r2) {
        return createSpec(r1, r2);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public CircularProgressIndicatorSpec createSpec(Context r1, AttributeSet r2) {
        return new CircularProgressIndicatorSpec(r1, r2);
    }

    public int getIndeterminateAnimationType() {
        return ((CircularProgressIndicatorSpec) this.spec).indeterminateAnimationType;
    }

    public int getIndicatorDirection() {
        return ((CircularProgressIndicatorSpec) this.spec).indicatorDirection;
    }

    public int getIndicatorInset() {
        return ((CircularProgressIndicatorSpec) this.spec).indicatorInset;
    }

    public int getIndicatorSize() {
        return ((CircularProgressIndicatorSpec) this.spec).indicatorSize;
    }

    public void setIndeterminateAnimationType(int r3) {
        if (((CircularProgressIndicatorSpec) this.spec).indeterminateAnimationType != r3) goto L6;
        return;
    L6:
        if (visibleToUser() == true) goto L8;
    L12:
        S r0 = this.spec;
        ((CircularProgressIndicatorSpec) r0).indeterminateAnimationType = r3;
        ((CircularProgressIndicatorSpec) r0).validateSpec();
        if (r3 != 1) goto L15;
        IndeterminateAnimatorDelegate<ObjectAnimator> r32 = new CircularIndeterminateRetreatAnimatorDelegate(getContext(), (CircularProgressIndicatorSpec) this.spec);
    L16:
        getIndeterminateDrawable().setAnimatorDelegate(r32);
        registerSwitchIndeterminateModeCallback();
        invalidate();
        return;
    L15:
        r32 = new CircularIndeterminateAdvanceAnimatorDelegate((CircularProgressIndicatorSpec) this.spec);
        goto L16
    L8:
        if (isIndeterminate() == false) goto L12;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
    }

    public void setIndicatorDirection(int r2) {
        ((CircularProgressIndicatorSpec) this.spec).indicatorDirection = r2;
        invalidate();
    }

    public void setIndicatorInset(int r3) {
        S r0 = this.spec;
        if (((CircularProgressIndicatorSpec) r0).indicatorInset == r3) goto L6;
        ((CircularProgressIndicatorSpec) r0).indicatorInset = r3;
        invalidate();
        return;
    }

    public void setIndicatorSize(int r3) {
        int r32 = Math.max(r3, getTrackThickness() * 2);
        S r0 = this.spec;
        if (((CircularProgressIndicatorSpec) r0).indicatorSize == r32) goto L6;
        ((CircularProgressIndicatorSpec) r0).indicatorSize = r32;
        ((CircularProgressIndicatorSpec) r0).validateSpec();
        requestLayout();
        invalidate();
        return;
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackThickness(int r1) {
        super.setTrackThickness(r1);
        ((CircularProgressIndicatorSpec) this.spec).validateSpec();
    }
}
