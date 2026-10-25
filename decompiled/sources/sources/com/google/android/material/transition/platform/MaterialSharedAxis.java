package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class MaterialSharedAxis extends MaterialVisibility<VisibilityAnimatorProvider> {
    private static final int DEFAULT_THEMED_DURATION_ATTR = 0;
    private static final int DEFAULT_THEMED_EASING_ATTR = 0;
    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;
    private final int axis;
    private final boolean forward;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Axis {
    }

    static {
        DEFAULT_THEMED_DURATION_ATTR = R.attr.motionDurationLong1;
        DEFAULT_THEMED_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
    }

    public MaterialSharedAxis(int r3, boolean r4) {
        super(createPrimaryAnimatorProvider(r3, r4), createSecondaryAnimatorProvider());
        this.axis = r3;
        this.forward = r4;
    }

    private static VisibilityAnimatorProvider createPrimaryAnimatorProvider(int r1, boolean r2) {
        if (r1 == 0) goto L18;
        if (r1 == 1) goto L12;
        if (r1 == 2) goto L8;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid axis: ", r1);
        return null;
    L8:
        return new ScaleProvider(r2);
    L12:
        if (r2 == false) goto L14;
        int r22 = 80;
    L16:
        return new SlideDistanceProvider(r22);
    L14:
        r22 = 48;
        goto L16
    L18:
        if (r2 == false) goto L20;
        int r23 = 8388613;
    L22:
        return new SlideDistanceProvider(r23);
    L20:
        r23 = PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
        goto L22
    }

    private static VisibilityAnimatorProvider createSecondaryAnimatorProvider() {
        return new FadeThroughProvider();
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    public /* bridge */ /* synthetic */ void addAdditionalAnimatorProvider(VisibilityAnimatorProvider r1) {
        super.addAdditionalAnimatorProvider(r1);
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    public /* bridge */ /* synthetic */ void clearAdditionalAnimatorProvider() {
        super.clearAdditionalAnimatorProvider();
    }

    public int getAxis() {
        return this.axis;
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    public int getDurationThemeAttrResId(boolean r1) {
        return DEFAULT_THEMED_DURATION_ATTR;
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    public int getEasingThemeAttrResId(boolean r1) {
        return DEFAULT_THEMED_EASING_ATTR;
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    public /* bridge */ /* synthetic */ VisibilityAnimatorProvider getPrimaryAnimatorProvider() {
        return super.getPrimaryAnimatorProvider();
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    public /* bridge */ /* synthetic */ VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return super.getSecondaryAnimatorProvider();
    }

    public boolean isForward() {
        return this.forward;
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility, android.transition.Visibility
    public /* bridge */ /* synthetic */ Animator onAppear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
        return super.onAppear(r1, r2, r3, r4);
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility, android.transition.Visibility
    public /* bridge */ /* synthetic */ Animator onDisappear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
        return super.onDisappear(r1, r2, r3, r4);
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    public /* bridge */ /* synthetic */ boolean removeAdditionalAnimatorProvider(VisibilityAnimatorProvider r1) {
        return super.removeAdditionalAnimatorProvider(r1);
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    public /* bridge */ /* synthetic */ void setSecondaryAnimatorProvider(VisibilityAnimatorProvider r1) {
        super.setSecondaryAnimatorProvider(r1);
    }
}
