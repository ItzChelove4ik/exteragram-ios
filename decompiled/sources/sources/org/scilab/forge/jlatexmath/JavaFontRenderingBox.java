package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Font;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.font.TextLayout;
import ru.noties.jlatexmath.awt.geom.Rectangle2D;

/* loaded from: classes5.dex */
public class JavaFontRenderingBox extends Box {
    private static Font font;
    private float size;
    private TextLayout text;

    static {
        font = new Font("Serif", 0, 10);
    }

    public JavaFontRenderingBox(String r7, int r8, float r9) {
        this(r7, r8, r9, font, true);
    }

    public JavaFontRenderingBox(String r1, int r2, float r3, Font r4, boolean r5) {
        this.size = r3;
        TextLayout r52 = new TextLayout(r1, r4.deriveFont(r2), null);
        this.text = r52;
        Rectangle2D r12 = r52.getBounds();
        this.height = ((-r12.getY()) * r3) / 10.0f;
        this.depth = ((r12.getHeight() * r3) / 10.0f) - this.height;
        this.width = (((r12.getWidth() + r12.getX()) + 0.4f) * r3) / 10.0f;
    }

    public static void setFont(String r3) {
        font = new Font(r3, 0, 10);
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r8, float r9, float r10) {
        drawDebug(r8, r9, r10);
        r8.translate(r9, r10);
        float r0 = this.size;
        r8.scale(r0 * 0.1d, r0 * 0.1d);
        this.text.draw(r8, 0, 0);
        float r7 = this.size;
        r8.scale(10.0f / r7, 10.0f / r7);
        r8.translate(-r9, -r10);
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return 0;
    }
}
