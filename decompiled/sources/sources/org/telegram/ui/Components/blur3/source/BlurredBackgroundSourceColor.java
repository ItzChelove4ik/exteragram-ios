package org.telegram.ui.Components.blur3.source;

import android.graphics.Canvas;
import android.graphics.Paint;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawableSource;

/* loaded from: classes3.dex */
public class BlurredBackgroundSourceColor implements BlurredBackgroundSource {
    private final Paint paint;

    public BlurredBackgroundSourceColor() {
        this.paint = new Paint(1);
    }

    @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
    public BlurredBackgroundDrawable createDrawable() {
        return new BlurredBackgroundDrawableSource(this);
    }

    @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
    public void draw(Canvas r2, float r3, float r4, float r5, float r6) {
        r2.drawRect(r3, r4, r5, r6, this.paint);
    }

    public int getColor() {
        return this.paint.getColor();
    }

    public void setColor(int r1) {
        this.paint.setColor(r1);
    }
}
