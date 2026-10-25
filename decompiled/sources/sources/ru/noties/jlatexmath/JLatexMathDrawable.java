package ru.noties.jlatexmath;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import ru.noties.jlatexmath.awt.AndroidGraphics2D;
import ru.noties.jlatexmath.awt.Color;
import ru.noties.jlatexmath.awt.Insets;

/* loaded from: classes7.dex */
public class JLatexMathDrawable extends Drawable {
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 2;
    private final int align;
    private final Drawable background;
    private final AndroidGraphics2D graphics2D;
    private final TeXIcon icon;
    private final int iconHeight;
    private final int iconWidth;

    @Retention(RetentionPolicy.CLASS)
    public @interface Align {
    }

    public static class Builder {
        private int align;
        private Drawable background;
        private int color;
        private Insets insets;
        private final String latex;
        private float textSize;

        /* renamed from: -$$Nest$fgetalign, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m22456$$Nest$fgetalign(Builder r0) {
            return r0.align;
        }

        /* renamed from: -$$Nest$fgetbackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m22457$$Nest$fgetbackground(Builder r0) {
            return r0.background;
        }

        /* renamed from: -$$Nest$fgetcolor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m22458$$Nest$fgetcolor(Builder r0) {
            return r0.color;
        }

        /* renamed from: -$$Nest$fgetinsets, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Insets m22459$$Nest$fgetinsets(Builder r0) {
            return r0.insets;
        }

        /* renamed from: -$$Nest$fgetlatex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22460$$Nest$fgetlatex(Builder r0) {
            return r0.latex;
        }

        /* renamed from: -$$Nest$fgettextSize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m22461$$Nest$fgettextSize(Builder r0) {
            return r0.textSize;
        }

        public Builder(String r2) {
            this.color = -16777216;
            this.latex = r2;
        }

        public Builder align(@Align int r1) {
            this.align = r1;
            return this;
        }

        public Builder background(int r2) {
            this.background = new ColorDrawable(r2);
            return this;
        }

        public Builder background(Drawable r1) {
            this.background = r1;
            return this;
        }

        public JLatexMathDrawable build() {
            return new JLatexMathDrawable(this);
        }

        public Builder color(int r1) {
            this.color = r1;
            return this;
        }

        @Deprecated
        public Builder fitCanvas(boolean r1) {
            return this;
        }

        public Builder padding(int r2) {
            this.insets = new Insets(r2, r2, r2, r2);
            return this;
        }

        public Builder padding(int r2, int r3, int r4, int r5) {
            this.insets = new Insets(r3, r2, r5, r4);
            return this;
        }

        public Builder textSize(float r1) {
            this.textSize = r1;
            return this;
        }
    }

    public JLatexMathDrawable(Builder r4) {
        TeXIcon r0 = new TeXFormula.TeXIconBuilder(new TeXFormula(Builder.m22460$$Nest$fgetlatex(r4))).setFGColor(new Color(Builder.m22458$$Nest$fgetcolor(r4))).setSize(Builder.m22461$$Nest$fgettextSize(r4)).setStyle(0).build();
        this.icon = r0;
        if (Builder.m22459$$Nest$fgetinsets(r4) == null) goto L5;
        r0.setInsets(Builder.m22459$$Nest$fgetinsets(r4));
    L5:
        this.align = Builder.m22456$$Nest$fgetalign(r4);
        this.background = Builder.m22457$$Nest$fgetbackground(r4);
        this.graphics2D = new AndroidGraphics2D();
        int r42 = r0.getIconWidth();
        this.iconWidth = r42;
        int r02 = r0.getIconHeight();
        this.iconHeight = r02;
        setBounds(0, 0, r42, r02);
    }

    public static Builder builder(String r1) {
        return new Builder(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r11) {
        Rect r0 = getBounds();
        int r1 = r11.save();
        Drawable r2 = this.background;     // Catch: Throwable -> L6
        if (r2 == null) goto L8;
        r2.draw(r11);     // Catch: Throwable -> L6
    L8:
        int r22 = r0.width();     // Catch: Throwable -> L6
        int r02 = r0.height();     // Catch: Throwable -> L6
        int r3 = this.iconWidth;     // Catch: Throwable -> L6
        if (r3 <= r22) goto L11;
    L14:
        float r32 = Math.min(r22 / r3, r02 / this.iconHeight);     // Catch: Throwable -> L6
    L15:
        int r5 = (int) ((this.iconWidth * r32) + 0.5f);     // Catch: Throwable -> L6
        int r03 = (r02 - ((int) ((this.iconHeight * r32) + 0.5f))) / 2;     // Catch: Throwable -> L6
        int r7 = this.align;     // Catch: Throwable -> L6
        if (r7 != 1) goto L18;
        int r23 = (r22 - r5) / 2;     // Catch: Throwable -> L6
    L21:
        if (r03 != 0) goto L23;
        if (r23 != 0) goto L23;
    L25:
        if (Float.compare(r32, 1.0f) == 0) goto L27;
        r11.scale(r32, r32);     // Catch: Throwable -> L6
    L27:
        this.graphics2D.setCanvas(r11);     // Catch: Throwable -> L6
        this.icon.paintIcon(null, this.graphics2D, 0, 0);     // Catch: Throwable -> L6
        r11.restoreToCount(r1);
        return;
    L23:
        r11.translate(r23, r03);     // Catch: Throwable -> L6
        goto L25
    L18:
        if (r7 != 2) goto L20;
        r23 = r22 - r5;     // Catch: Throwable -> L6
        goto L21
    L20:
        r23 = 0;
        goto L21
    L11:
        if (this.iconHeight > r02) goto L14;
        r32 = 1.0f;
    L6:
        th = move-exception;
        r11.restoreToCount(r1);
        throw th;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.iconHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.iconWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -1;
    }

    public TeXIcon icon() {
        return this.icon;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        Drawable r0 = this.background;
        if (r0 == null) goto L6;
        r0.setBounds(r1);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
