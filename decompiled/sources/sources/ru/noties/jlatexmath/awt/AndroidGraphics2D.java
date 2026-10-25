package ru.noties.jlatexmath.awt;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import okio.Segment$$ExternalSyntheticBUOutline1;
import ru.noties.jlatexmath.awt.RenderingHints;
import ru.noties.jlatexmath.awt.font.FontRenderContext;
import ru.noties.jlatexmath.awt.geom.AffineTransform;
import ru.noties.jlatexmath.awt.geom.Line2D;
import ru.noties.jlatexmath.awt.geom.Rectangle2D;
import ru.noties.jlatexmath.awt.geom.RoundRectangle2D;

/* loaded from: classes7.dex */
public class AndroidGraphics2D implements Graphics2D {
    private Canvas canvas;
    private Color color;
    private Font font;
    private final Paint paint;
    private final RectF rectF;
    private Stroke stroke;
    private AffineTransform transform;

    public AndroidGraphics2D() {
        this.rectF = new RectF();
        Paint r0 = new Paint(1);
        this.paint = r0;
        r0.setStrokeCap(Paint.Cap.BUTT);
        r0.setStrokeJoin(Paint.Join.MITER);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void draw(Line2D.Float r9) {
        this.paint.setStyle(Paint.Style.STROKE);
        this.canvas.drawLine((float) r9.x1, (float) r9.y1, (float) r9.x2, (float) r9.y2, this.paint);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void draw(Rectangle2D.Float r9) {
        this.paint.setStyle(Paint.Style.STROKE);
        Canvas r2 = this.canvas;
        float r3 = r9.x;
        float r4 = r9.y;
        r2.drawRect(r3, r4, r3 + r9.w, r4 + r9.h, this.paint);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void draw(RoundRectangle2D.Float r6) {
        this.paint.setStyle(Paint.Style.STROKE);
        RectF r0 = this.rectF;
        float r1 = r6.x;
        float r2 = r6.y;
        r0.set(r1, r2, r6.width + r1, r6.height + r2);
        this.canvas.drawRoundRect(this.rectF, r6.arcwidth, r6.archeight, this.paint);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void drawArc(int r10, int r11, int r12, int r13, int r14, int r15) {
        this.paint.setStyle(Paint.Style.STROKE);
        this.rectF.set(r10, r11, r10 + r12, r11 + r13);
        this.canvas.drawArc(this.rectF, r14, r15, false, this.paint);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void drawChars(char[] r10, int r11, int r12, int r13, int r14) {
        Font r0 = this.font;
        if (r0 == null) goto L5;
        this.paint.setTypeface(r0.typeface());
        this.paint.setTextSize(this.font.size());
    L5:
        this.canvas.drawText(r10, r11, r12, r13, r14, this.paint);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void fill(Rectangle2D.Float r9) {
        this.paint.setStyle(Paint.Style.FILL);
        Canvas r2 = this.canvas;
        float r3 = r9.x;
        float r4 = r9.y;
        r2.drawRect(r3, r4, r3 + r9.w, r4 + r9.h, this.paint);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void fillArc(int r10, int r11, int r12, int r13, int r14, int r15) {
        this.paint.setStyle(Paint.Style.FILL);
        this.rectF.set(r10, r11, r10 + r12, r11 + r13);
        this.canvas.drawArc(this.rectF, r14, r15, false, this.paint);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void fillRect(int r9, int r10, int r11, int r12) {
        this.paint.setStyle(Paint.Style.FILL);
        this.canvas.drawRect(r9, r10, r9 + r11, r10 + r12, this.paint);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public Color getColor() {
        if (this.color != null) goto L6;
        this.color = new Color(this.paint.getColor());
    L6:
        return this.color;
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public Font getFont() {
        return this.font;
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public FontRenderContext getFontRenderContext() {
        return null;
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public RenderingHints getRenderingHints() {
        return null;
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public Stroke getStroke() {
        if (this.stroke != null) goto L6;
        this.stroke = new BasicStroke(this.paint.getStrokeWidth(), 0, 0, this.paint.getStrokeMiter());
    L6:
        return this.stroke;
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public AffineTransform getTransform() {
        AffineTransform r0 = this.transform.save();
        this.transform = r0;
        return r0;
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void rotate(double r1) {
        this.canvas.rotate((float) Math.toDegrees(r1));
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void rotate(double r1, double r3, double r5) {
        this.canvas.rotate((float) Math.toDegrees(r1), (float) r3, (float) r5);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void scale(double r1, double r3) {
        this.transform.scale(r1, r3);
    }

    public void setCanvas(Canvas r1) {
        this.canvas = r1;
        this.transform = AffineTransform.create(r1);
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void setColor(Color r1) {
        this.color = r1;
        this.paint.setColor(r1.getColorInt());
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void setFont(Font r1) {
        this.font = r1;
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void setRenderingHint(RenderingHints.Key r1, Object r2) {
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void setRenderingHints(RenderingHints r1) {
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void setStroke(Stroke r1) {
        this.stroke = r1;
        this.paint.setStrokeWidth(r1.width());
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void setTransform(AffineTransform r3) {
        if (this.canvas != r3.getCanvas()) goto L6;
        this.transform = r3.restore();
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Supplied transform has different Canvas attached");
    }

    @Override // ru.noties.jlatexmath.awt.Graphics2D
    public void translate(double r1, double r3) {
        this.transform.translate((float) r1, (float) r3);
    }
}
