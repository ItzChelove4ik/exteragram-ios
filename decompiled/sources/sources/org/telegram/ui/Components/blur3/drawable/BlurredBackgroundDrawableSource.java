package org.telegram.ui.Components.blur3.drawable;

import android.graphics.Canvas;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;

/* loaded from: classes3.dex */
public class BlurredBackgroundDrawableSource extends BlurredBackgroundDrawable {
    private final BlurredBackgroundSource source;

    public BlurredBackgroundDrawableSource(BlurredBackgroundSource r1) {
        this.source = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r2) {
        drawSource(r2, this.source);
    }

    @Override // org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable
    public BlurredBackgroundSource getSource() {
        return this.source;
    }
}
