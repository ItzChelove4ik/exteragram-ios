package org.telegram.ui.Components.blur3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.GlassOutlineStyle;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;

/* loaded from: classes7.dex */
public class StrokeDrawable extends Drawable {
    private float alpha;
    private BlurredBackgroundColorProvider colorProvider;
    public boolean nonRound;
    private int padding;
    private final Paint paintFill;
    private final Paint paintStrokeBottom;
    private final Paint paintStrokeFull;
    private final Paint paintStrokeTop;
    public float radius;
    private final RectF rect;
    protected int strokeColorBottom;
    protected int strokeColorFull;
    protected int strokeColorTop;

    public StrokeDrawable() {
        this.alpha = 1.0f;
        this.rect = new RectF();
        this.paintFill = new Paint(1);
        this.paintStrokeTop = new Paint(1);
        this.paintStrokeBottom = new Paint(1);
        this.paintStrokeFull = new Paint(1);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r11) {
        float r0 = getBounds().centerX();
        float r1 = getBounds().centerY();
        float r2 = (Math.min(getBounds().width(), getBounds().height()) / 2.0f) - this.padding;
        this.rect.set(r0 - r2, r1 - r2, r0 + r2, r1 + r2);
        if (this.nonRound == false) goto L5;
        this.rect.set(getBounds());
        r2 = this.radius;
    L5:
        float r6 = r2;
        if (Color.alpha(this.paintFill.getColor()) <= 0) goto L9;
        r11.drawCircle(r0, r1, r6, this.paintFill);
    L9:
        if (this.strokeColorFull == 0) goto L16;
        float r22 = this.paintStrokeFull.getStrokeWidth() / 2.0f;
        if (this.nonRound == false) goto L13;
        this.rect.inset(r22, r22);
        float r02 = Math.max(0.0f, r6 - r22);
        r11.drawRoundRect(this.rect, r02, r02, this.paintStrokeFull);
    L14:
        Canvas r4 = r11;
    L19:
        if (this.strokeColorFull == 0) goto L21;
        return;
    L21:
        if (this.strokeColorBottom == 0) goto L25;
        BlurredBackgroundDrawable.drawStroke(r4, this.rect, r6, AndroidUtilities.dpf2(0.6666667f), false, this.paintStrokeBottom);
        return;
    L25:
        return;
    L13:
        r11.drawCircle(r0, r1, Math.max(0.0f, r6 - r22), this.paintStrokeFull);
        goto L14
    L16:
        if (this.strokeColorTop == 0) goto L14;
        r4 = r11;
        BlurredBackgroundDrawable.drawStroke(r4, this.rect, r6, AndroidUtilities.dpf2(1.0f), true, this.paintStrokeTop);
        goto L19
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.alpha = r2 / 255.0f;
        updateColors();
    }

    public void setBackgroundColor(int r2) {
        this.paintFill.setColor(r2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setColorProvider(BlurredBackgroundColorProvider r2) {
        this.colorProvider = r2;
        Paint r22 = this.paintStrokeTop;
        Paint.Style r0 = Paint.Style.STROKE;
        r22.setStyle(r0);
        this.paintStrokeBottom.setStyle(r0);
        this.paintStrokeFull.setStyle(r0);
        updateColors();
    }

    public void setPadding(int r1) {
        this.padding = r1;
    }

    public void updateColors() {
        if (this.colorProvider != null) goto L5;
        return;
    L5:
        GlassOutlineStyle r0 = ExteraConfig.getGlassOutlineStyle();
        int r2 = 0;
        if (r0 != GlassOutlineStyle.GLARE) goto L8;
        this.strokeColorTop = Theme.multAlpha(this.colorProvider.getStrokeColorTop(), this.alpha);
        this.strokeColorBottom = Theme.multAlpha(this.colorProvider.getStrokeColorBottom(), this.alpha);
    L10:
        if (r0 != GlassOutlineStyle.SOLID) goto L12;
        r2 = Theme.multAlpha(this.colorProvider.getStrokeColorFull(), this.alpha);
    L12:
        this.strokeColorFull = r2;
        this.paintStrokeTop.setColor(this.strokeColorTop);
        this.paintStrokeTop.setStrokeWidth(AndroidUtilities.dpf2(1.0f));
        this.paintStrokeBottom.setColor(this.strokeColorBottom);
        this.paintStrokeBottom.setStrokeWidth(AndroidUtilities.dpf2(0.6666667f));
        this.paintStrokeFull.setColor(this.strokeColorFull);
        this.paintStrokeFull.setStrokeWidth(AndroidUtilities.dpf2(1.0f));
        return;
    L8:
        this.strokeColorTop = 0;
        this.strokeColorBottom = 0;
        goto L10
    }
}
