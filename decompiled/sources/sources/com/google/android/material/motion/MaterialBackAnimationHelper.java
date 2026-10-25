package com.google.android.material.motion;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.PathInterpolator;
import androidx.activity.BackEventCompat;
import com.google.android.material.R;

/* loaded from: classes5.dex */
public abstract class MaterialBackAnimationHelper<V extends View> {
    private static final int CANCEL_DURATION_DEFAULT = 100;
    private static final int HIDE_DURATION_MAX_DEFAULT = 300;
    private static final int HIDE_DURATION_MIN_DEFAULT = 150;
    private static final String TAG = "MaterialBackHelper";
    private BackEventCompat backEvent;
    protected final int cancelDuration;
    protected final int hideDurationMax;
    protected final int hideDurationMin;
    private final TimeInterpolator progressInterpolator;
    protected final V view;

    public MaterialBackAnimationHelper(V r5) {
        this.progressInterpolator = new PathInterpolator(0.1f, 0.1f, 0.0f, 1.0f);
        this.view = r5;
        Context r52 = r5.getContext();
        this.hideDurationMax = MotionUtils.resolveThemeDuration(r52, R.attr.motionDurationMedium2, 300);
        this.hideDurationMin = MotionUtils.resolveThemeDuration(r52, R.attr.motionDurationShort3, 150);
        this.cancelDuration = MotionUtils.resolveThemeDuration(r52, R.attr.motionDurationShort2, 100);
    }

    public float interpolateProgress(float r1) {
        return this.progressInterpolator.getInterpolation(r1);
    }

    public BackEventCompat onCancelBackProgress() {
        if (this.backEvent != null) goto L5;
        Log.w(TAG, "Must call startBackProgress() and updateBackProgress() before cancelBackProgress()");
    L5:
        BackEventCompat r0 = this.backEvent;
        this.backEvent = null;
        return r0;
    }

    public BackEventCompat onHandleBackInvoked() {
        BackEventCompat r0 = this.backEvent;
        this.backEvent = null;
        return r0;
    }

    public void onStartBackProgress(BackEventCompat r1) {
        this.backEvent = r1;
    }

    public BackEventCompat onUpdateBackProgress(BackEventCompat r3) {
        if (this.backEvent != null) goto L5;
        Log.w(TAG, "Must call startBackProgress() before updateBackProgress()");
    L5:
        BackEventCompat r0 = this.backEvent;
        this.backEvent = r3;
        return r0;
    }
}
