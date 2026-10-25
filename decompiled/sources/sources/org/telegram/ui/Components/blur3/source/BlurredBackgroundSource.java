package org.telegram.ui.Components.blur3.source;

import android.graphics.Canvas;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;

/* loaded from: classes3.dex */
public interface BlurredBackgroundSource {
    BlurredBackgroundDrawable createDrawable();

    default void dispatchOnDrawablesRelativePositionChange() {
    }

    void draw(Canvas r1, float r2, float r3, float r4, float r5);
}
