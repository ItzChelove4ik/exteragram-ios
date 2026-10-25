package org.telegram.ui.ActionBar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public class RoundVideoShadow extends Drawable {
    Paint paint;

    public RoundVideoShadow() {
        Paint r0 = new Paint(1);
        this.paint = r0;
        r0.setShadowLayer(AndroidUtilities.dp(4.0f), 0.0f, 0.0f, 1593835520);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r5) {
        float r2 = (getBounds().width() - AndroidUtilities.dp(8.0f)) / 2.0f;
        r5.drawCircle(getBounds().centerX(), getBounds().centerY() - AndroidUtilities.dp(1.0f), r2, this.paint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.paint.setAlpha(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
