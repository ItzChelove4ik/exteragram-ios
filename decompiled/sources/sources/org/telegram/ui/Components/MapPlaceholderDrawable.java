package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class MapPlaceholderDrawable extends Drawable {
    private Paint linePaint;
    private Paint paint;

    public MapPlaceholderDrawable() {
        this(Theme.getCurrentTheme().isDark());
    }

    public MapPlaceholderDrawable(boolean r3) {
        this.paint = new Paint();
        Paint r0 = new Paint();
        this.linePaint = r0;
        r0.setStrokeWidth(AndroidUtilities.dp(1.0f));
        Paint r02 = this.paint;
        if (r3 == false) goto L6;
        r02.setColor(-14865331);
        this.linePaint.setColor(-15854042);
        return;
    L6:
        r02.setColor(-2172970);
        this.linePaint.setColor(-3752002);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r14) {
        r14.drawRect(getBounds(), this.paint);
        int r0 = AndroidUtilities.dp(9.0f);
        int r1 = getBounds().width() / r0;
        int r8 = getBounds().height() / r0;
        int r9 = getBounds().left;
        int r10 = getBounds().top;
        int r11 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        int r12 = r3 + 1;
        float r32 = (r0 * r12) + r9;
        r14.drawLine(r32, r10, r32, getBounds().height() + r10, this.linePaint);
        r3 = r12;
    L5:
        if (r11 >= r8) goto L7;
        r11 = r11 + 1;
        float r4 = (r0 * r11) + r10;
        r14.drawLine(r9, r4, getBounds().width() + r9, r4, this.linePaint);
        goto L5
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
