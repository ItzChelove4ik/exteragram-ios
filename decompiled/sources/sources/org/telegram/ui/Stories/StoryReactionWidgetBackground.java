package org.telegram.ui.Stories;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class StoryReactionWidgetBackground extends Drawable {
    private final int STYLE_FILLED;
    private final int STYLE_TRANSCLUENT;
    int alpha;
    Paint backgroundPaint;
    private boolean mirror;
    private final View parent;
    Path path;
    float[] points;
    AnimatedFloat progressToMirrored;
    Paint shadowPaint;
    int style;
    private Paint xRefPaint;

    public StoryReactionWidgetBackground(View r6) {
        this.STYLE_FILLED = 0;
        this.STYLE_TRANSCLUENT = 1;
        this.alpha = 255;
        this.points = new float[15];
        this.path = new Path();
        this.parent = r6;
        this.progressToMirrored = new AnimatedFloat(r6, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        Paint r62 = new Paint(1);
        this.shadowPaint = r62;
        r62.setShadowLayer(AndroidUtilities.dp(4.0f), 0.0f, 0.0f, 1593835520);
        Paint r63 = new Paint(1);
        this.backgroundPaint = r63;
        r63.setColor(-1);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r21) {
        int r3 = 0;
        this.points[0] = getBounds().centerX();
        int r4 = 1;
        this.points[1] = getBounds().centerY();
        int r5 = 2;
        this.points[2] = getBounds().height() / 2.0f;
        this.points[3] = getBounds().left + (getBounds().width() * 1.027f);
        this.points[4] = getBounds().top + (getBounds().height() * 0.956f);
        this.points[5] = getBounds().height() * 0.055f;
        this.points[6] = getBounds().left + (getBounds().width() * 0.843f);
        this.points[7] = getBounds().top + (getBounds().height() * 0.812f);
        this.points[8] = getBounds().height() * 0.132f;
        this.points[9] = getBounds().left + (getBounds().width() * (-0.02699995f));
        this.points[10] = getBounds().top + (getBounds().height() * 0.956f);
        this.points[11] = getBounds().height() * 0.055f;
        this.points[12] = getBounds().left + (getBounds().width() * 0.157f);
        this.points[13] = getBounds().top + (getBounds().height() * 0.812f);
        this.points[14] = getBounds().height() * 0.132f;
        AnimatedFloat r1 = this.progressToMirrored;
        if (this.mirror == false) goto L5;
        float r2 = 1.0f;
    L6:
        float r12 = r1.set(r2);
        int r22 = this.style;
        if (r22 != 0) goto L9;
        this.backgroundPaint.setColor(-1);
    L15:
        if (this.alpha == 255) goto L17;
    L20:
        r21.saveLayerAlpha(getBounds().left - (getBounds().width() * 0.2f), getBounds().top, getBounds().right + (getBounds().width() * 0.2f), getBounds().bottom + (getBounds().height() * 0.2f), this.alpha, 31);
    L21:
        this.path.rewind();
        int r23 = 0;
    L22:
        if (r23 >= r5) goto L53;
        if (this.style != r4) goto L27;
        if (r23 != 0) goto L27;
        int r16 = r5;
    L52:
        r23 = r23 + 1;
        r5 = r16;
        r3 = 0;
        r4 = 1;
    L27:
        if (r23 != 0) goto L29;
        Paint r11 = this.shadowPaint;
    L30:
        if (r23 != 0) goto L32;
        int r122 = r4;
    L33:
        int r13 = r3;
    L34:
        if (r13 >= 5) goto L51;
        if (r13 == r4) goto L37;
        if (r13 == r5) goto L37;
        if (r13 == 3) goto L40;
        if (r13 == 4) goto L40;
        Path r14 = this.path;
        float[] r15 = this.points;
        int r162 = r13 * 3;
        float r32 = r15[r162];
        float r42 = r15[r162 + 1];
        float r152 = r15[r162 + 2];
        int r163 = r5;
        r14.addCircle(r32, r42, r152 - r122, Path.Direction.CW);
    L50:
        r13 = r13 + 1;
        r5 = r163;
        r4 = 1;
    L40:
        r163 = r5;
        if (r12 == 0.0f) goto L50;
        Path r33 = this.path;
        float[] r43 = this.points;
        int r52 = r13 * 3;
        r33.addCircle(r43[r52], r43[r52 + 1], (r43[r52 + 2] * r12) - r122, Path.Direction.CW);
    L37:
        r163 = r5;
        if (r12 == 1.0f) goto L50;
        Path r34 = this.path;
        float[] r44 = this.points;
        int r53 = r13 * 3;
        r34.addCircle(r44[r53], r44[r53 + 1], (r44[r53 + 2] * (1.0f - r12)) - r122, Path.Direction.CW);
        goto L50
    L51:
        r16 = r5;
        r21.drawPath(this.path, r11);
        goto L52
    L32:
        r122 = r3;
        goto L33
    L29:
        r11 = this.backgroundPaint;
        goto L30
    L53:
        r21.restore();
        return;
    L17:
        if (this.style == 1) goto L20;
        r21.save();
        goto L21
    L9:
        if (r22 != 1) goto L15;
        if (this.xRefPaint != null) goto L13;
        Paint r24 = new Paint(1);
        this.xRefPaint = r24;
        r24.setColor(-16777216);
        this.xRefPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.xRefPaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
    L13:
        this.backgroundPaint.setColor(ColorUtils.setAlphaComponent(-16777216, 127));
        goto L15
    L5:
        r2 = 0.0f;
        goto L6
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public boolean isDarkStyle() {
        if (this.style != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public void nextStyle() {
        int r0 = this.style + 1;
        this.style = r0;
        if (r0 < 2) goto L6;
        this.style = 0;
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setMirror(boolean r1, boolean r2) {
        this.mirror = r1;
        if (r2 == true) goto L10;
        AnimatedFloat r0 = this.progressToMirrored;
        if (r1 == false) goto L7;
        float r12 = 1.0f;
    L8:
        r0.set(r12, true);
        return;
    L7:
        r12 = 0.0f;
        goto L8
    L10:
        this.parent.invalidate();
    }

    public void updateShadowLayer(float r4) {
        this.shadowPaint.setShadowLayer(AndroidUtilities.dp(2.0f) / r4, 0.0f, AndroidUtilities.dpf2(0.7f) / r4, ColorUtils.setAlphaComponent(-16777216, 45));
    }
}
