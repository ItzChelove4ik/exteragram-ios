package com.google.android.material.progressindicator;

import android.content.ContentResolver;
import android.provider.Settings;

/* loaded from: classes5.dex */
public class AnimatorDurationScaleProvider {
    public AnimatorDurationScaleProvider() {
    }

    public float getSystemAnimatorDurationScale(ContentResolver r2) {
        return Settings.Global.getFloat(r2, "animator_duration_scale", 1.0f);
    }
}
