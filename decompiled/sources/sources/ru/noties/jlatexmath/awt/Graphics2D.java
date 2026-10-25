package ru.noties.jlatexmath.awt;

import ru.noties.jlatexmath.awt.RenderingHints;
import ru.noties.jlatexmath.awt.font.FontRenderContext;
import ru.noties.jlatexmath.awt.geom.AffineTransform;
import ru.noties.jlatexmath.awt.geom.Line2D;
import ru.noties.jlatexmath.awt.geom.Rectangle2D;
import ru.noties.jlatexmath.awt.geom.RoundRectangle2D;

/* loaded from: classes7.dex */
public interface Graphics2D extends Graphics {
    void draw(Line2D.Float r1);

    void draw(Rectangle2D.Float r1);

    void draw(RoundRectangle2D.Float r1);

    void drawArc(int r1, int r2, int r3, int r4, int r5, int r6);

    void drawChars(char[] r1, int r2, int r3, int r4, int r5);

    void fill(Rectangle2D.Float r1);

    void fillArc(int r1, int r2, int r3, int r4, int r5, int r6);

    void fillRect(int r1, int r2, int r3, int r4);

    Color getColor();

    Font getFont();

    FontRenderContext getFontRenderContext();

    RenderingHints getRenderingHints();

    Stroke getStroke();

    AffineTransform getTransform();

    void rotate(double r1);

    void rotate(double r1, double r3, double r5);

    void scale(double r1, double r3);

    void setColor(Color r1);

    void setFont(Font r1);

    void setRenderingHint(RenderingHints.Key r1, Object r2);

    void setRenderingHints(RenderingHints r1);

    void setStroke(Stroke r1);

    void setTransform(AffineTransform r1);

    void translate(double r1, double r3);
}
