package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.BasicStroke;
import ru.noties.jlatexmath.awt.Color;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.Stroke;
import ru.noties.jlatexmath.awt.geom.AffineTransform;

/* loaded from: classes5.dex */
public class GeoGebraLogoBox extends Box {
    private static final Color blue = null;
    private static final Color gray = null;
    private static final BasicStroke st = null;

    static {
        gray = new Color(102, 102, 102);
        blue = new Color(153, 153, 255);
        st = new BasicStroke(3.8f, 0, 0, 4.0f);
    }

    public GeoGebraLogoBox(float r2, float r3) {
        this.depth = 0.0f;
        this.height = r3;
        this.width = r2;
        this.shift = 0.0f;
    }

    private static void drawCircle(Graphics2D r7, float r8, float r9) {
        r7.setColor(blue);
        r7.translate(r8, r9);
        r7.fillArc(0, 0, 8, 8, 0, 360);
        r7.setColor(Color.BLACK);
        r7.drawArc(0, 0, 8, 8, 0, 360);
        r7.translate(-r8, -r9);
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r11, float r12, float r13) {
        AffineTransform r7 = r11.getTransform();
        Color r8 = r11.getColor();
        Stroke r9 = r11.getStroke();
        float r2 = this.height;
        r11.translate(((0.25f * r2) / 2.15f) + r12, r13 - (r2 * 0.81395346f));
        r11.setColor(gray);
        r11.setStroke(st);
        float r0 = this.height;
        r11.scale((r0 * 0.05f) / 2.15f, (r0 * 0.05f) / 2.15f);
        r11.rotate(-0.4537856055185257d, 20.5d, 17.5d);
        r11.drawArc(0, 0, 43, 32, 0, 360);
        r11.rotate(0.4537856055185257d, 20.5d, 17.5d);
        r11.setStroke(r9);
        drawCircle(r11, 16.0f, -5.0f);
        drawCircle(r11, -1.0f, 7.0f);
        drawCircle(r11, 5.0f, 28.0f);
        drawCircle(r11, 27.0f, 24.0f);
        drawCircle(r11, 36.0f, 3.0f);
        r11.setStroke(r9);
        r11.setTransform(r7);
        r11.setColor(r8);
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return 0;
    }
}
