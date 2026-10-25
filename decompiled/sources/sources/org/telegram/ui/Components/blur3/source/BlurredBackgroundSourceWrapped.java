package org.telegram.ui.Components.blur3.source;

import android.graphics.Canvas;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawableSource;

/* loaded from: classes3.dex */
public class BlurredBackgroundSourceWrapped implements BlurredBackgroundSource {
    private BlurredBackgroundSource sourceInternal;

    public BlurredBackgroundSourceWrapped() {
    }

    @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
    public BlurredBackgroundDrawable createDrawable() {
        return new BlurredBackgroundDrawableSource(this);
    }

    @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
    public void draw(Canvas r1, float r2, float r3, float r4, float r5) {
        BlurredBackgroundSource r0 = this.sourceInternal;
        if (r0 == null) goto L6;
        r0.draw(r1, r2, r3, r4, r5);
        return;
    }

    public BlurredBackgroundSource getSource() {
        return this.sourceInternal;
    }

    public void setSource(BlurredBackgroundSource r1) {
        this.sourceInternal = r1;
    }
}
