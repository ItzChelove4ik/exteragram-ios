package ru.noties.jlatexmath.awt.font;

import android.graphics.Paint;
import android.graphics.Rect;
import ru.noties.jlatexmath.awt.Font;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.geom.Rectangle2D;

/* loaded from: classes7.dex */
public class TextLayout {
    private final Rectangle2D bounds;
    private final char[] chars;
    private final Font font;

    public TextLayout(String r3, Font r4, FontRenderContext r5) {
        this.chars = r3.toCharArray();
        this.font = r4;
        Paint r52 = new Paint(1);
        r52.setTypeface(r4.typeface());
        r52.setTextSize(r4.size());
        r52.getTextBounds(r3, 0, r3.length(), new Rect());
        this.bounds = new Rectangle2D.Float(r4.left, r4.top, r4.width(), r4.height());
    }

    public void draw(Graphics2D r10, int r11, int r12) {
        Font r0 = r10.getFont();
        Font r1 = this.font;
        if (r1 == r0) goto L5;
        boolean r2 = true;
    L6:
        if (r2 == false) goto L8;
        r10.setFont(r1);
    L8:
        char[] r4 = this.chars;
        r10.drawChars(r4, 0, r4.length, r11, r12);
        if (r2 == false) goto L12;
        r10.setFont(r0);
        return;
    L12:
        return;
    L5:
        r2 = false;
        goto L6
    }

    public Rectangle2D getBounds() {
        return this.bounds;
    }
}
