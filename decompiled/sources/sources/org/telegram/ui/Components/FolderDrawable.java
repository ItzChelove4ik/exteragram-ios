package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class FolderDrawable extends Drawable {
    private final Drawable drawable;
    private final Paint fillPaint;
    private final Path path;
    private boolean pathInvalidated;
    private final Paint strokePaint;

    public FolderDrawable(Context r4, int r5, int r6) {
        this.pathInvalidated = true;
        this.drawable = r4.getResources().getDrawable(r5);
        if (r6 < 0) goto L6;
        this.path = new Path();
        Paint r42 = new Paint(1);
        this.strokePaint = r42;
        r42.setStyle(Paint.Style.STROKE);
        r42.setColor(-16777216);
        r42.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        r42.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(1.0f)));
        r42.setStrokeCap(Paint.Cap.ROUND);
        r42.setStrokeJoin(Paint.Join.ROUND);
        Paint r43 = new Paint(1);
        this.fillPaint = r43;
        r43.setStyle(Paint.Style.FILL);
        int[] r3 = Theme.keys_avatar_nameInMessage;
        r43.setColor(Theme.getColor(r3[r6 % r3.length]));
        r43.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(1.0f)));
        return;
    L6:
        this.path = null;
        this.strokePaint = null;
        this.fillPaint = null;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r8) {
        if (this.path == null) goto L9;
        r8.saveLayerAlpha(getBounds().left, getBounds().top, getBounds().right, getBounds().bottom, 255);
        this.drawable.setBounds(getBounds());
        this.drawable.draw(r8);
        if (this.pathInvalidated == false) goto L7;
        this.path.rewind();
        this.path.moveTo(x(0.4871f), y(0.6025f));
        this.path.lineTo(x(0.8974f), y(0.6025f));
        this.path.lineTo(x(1.0f), y(0.7564f));
        this.path.lineTo(x(0.8974f), y(0.9102f));
        this.path.lineTo(x(0.4871f), y(0.9102f));
        this.path.close();
        this.pathInvalidated = false;
        this.strokePaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
    L7:
        r8.drawPath(this.path, this.strokePaint);
        r8.drawPath(this.path, this.fillPaint);
        r8.restore();
        return;
    L9:
        this.drawable.setBounds(getBounds());
        this.drawable.draw(r8);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.drawable.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.drawable.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.drawable.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.drawable.setAlpha(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int r1, int r2, int r3, int r4) {
        super.setBounds(r1, r2, r3, r4);
        this.pathInvalidated = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.drawable.setColorFilter(r1);
    }

    public int x(float r2) {
        return AndroidUtilities.lerp(getBounds().left, getBounds().right, r2);
    }

    public int y(float r2) {
        return AndroidUtilities.lerp(getBounds().top, getBounds().bottom, r2);
    }
}
