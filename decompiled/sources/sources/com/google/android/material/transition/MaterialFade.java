package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionValues;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes5.dex */
public final class MaterialFade extends MaterialVisibility<FadeProvider> {
    private static final float DEFAULT_FADE_END_THRESHOLD_ENTER = 0.3f;
    private static final float DEFAULT_START_SCALE = 0.8f;
    private static final int DEFAULT_THEMED_INCOMING_DURATION_ATTR = 0;
    private static final int DEFAULT_THEMED_INCOMING_EASING_ATTR = 0;
    private static final int DEFAULT_THEMED_OUTGOING_DURATION_ATTR = 0;
    private static final int DEFAULT_THEMED_OUTGOING_EASING_ATTR = 0;

    static {
        DEFAULT_THEMED_INCOMING_DURATION_ATTR = R.attr.motionDurationMedium4;
        DEFAULT_THEMED_OUTGOING_DURATION_ATTR = R.attr.motionDurationShort3;
        DEFAULT_THEMED_INCOMING_EASING_ATTR = R.attr.motionEasingEmphasizedDecelerateInterpolator;
        DEFAULT_THEMED_OUTGOING_EASING_ATTR = R.attr.motionEasingEmphasizedAccelerateInterpolator;
    }

    public MaterialFade() {
        super(createPrimaryAnimatorProvider(), createSecondaryAnimatorProvider());
    }

    private static FadeProvider createPrimaryAnimatorProvider() {
        FadeProvider r0 = new FadeProvider();
        r0.setIncomingEndThreshold(DEFAULT_FADE_END_THRESHOLD_ENTER);
        return r0;
    }

    private static VisibilityAnimatorProvider createSecondaryAnimatorProvider() {
        ScaleProvider r0 = new ScaleProvider();
        r0.setScaleOnDisappear(false);
        r0.setIncomingStartScale(DEFAULT_START_SCALE);
        return r0;
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ void addAdditionalAnimatorProvider(VisibilityAnimatorProvider r1) {
        super.addAdditionalAnimatorProvider(r1);
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ void clearAdditionalAnimatorProvider() {
        super.clearAdditionalAnimatorProvider();
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public TimeInterpolator getDefaultEasingInterpolator(boolean r1) {
        return AnimationUtils.LINEAR_INTERPOLATOR;
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public int getDurationThemeAttrResId(boolean r1) {
        if (r1 == false) goto L6;
        return DEFAULT_THEMED_INCOMING_DURATION_ATTR;
    L6:
        return DEFAULT_THEMED_OUTGOING_DURATION_ATTR;
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public int getEasingThemeAttrResId(boolean r1) {
        if (r1 == false) goto L6;
        return DEFAULT_THEMED_INCOMING_EASING_ATTR;
    L6:
        return DEFAULT_THEMED_OUTGOING_EASING_ATTR;
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ VisibilityAnimatorProvider getPrimaryAnimatorProvider() {
        return super.getPrimaryAnimatorProvider();
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return super.getSecondaryAnimatorProvider();
    }

    @Override // com.google.android.material.transition.MaterialVisibility, androidx.transition.Transition
    public /* bridge */ /* synthetic */ boolean isSeekingSupported() {
        return super.isSeekingSupported();
    }

    @Override // com.google.android.material.transition.MaterialVisibility, androidx.transition.Visibility
    public /* bridge */ /* synthetic */ Animator onAppear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
        return super.onAppear(r1, r2, r3, r4);
    }

    @Override // com.google.android.material.transition.MaterialVisibility, androidx.transition.Visibility
    public /* bridge */ /* synthetic */ Animator onDisappear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
        return super.onDisappear(r1, r2, r3, r4);
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ boolean removeAdditionalAnimatorProvider(VisibilityAnimatorProvider r1) {
        return super.removeAdditionalAnimatorProvider(r1);
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ void setSecondaryAnimatorProvider(VisibilityAnimatorProvider r1) {
        super.setSecondaryAnimatorProvider(r1);
    }
}
