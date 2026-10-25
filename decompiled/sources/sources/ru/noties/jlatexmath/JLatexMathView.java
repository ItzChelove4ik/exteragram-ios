package ru.noties.jlatexmath;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import ru.noties.jlatexmath.android.R;
import ru.noties.jlatexmath.awt.Color;

/* loaded from: classes7.dex */
public class JLatexMathView extends View {
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_END = 2;
    public static final int ALIGN_START = 0;
    private int alignHorizontal;
    private int alignVertical;
    private Drawable background;
    private JLatexMathDrawable drawable;
    private float left;
    private float scale;
    private int textColor;
    private int textSize;
    private float top;

    @Retention(RetentionPolicy.CLASS)
    public @interface Align {
    }

    public JLatexMathView(Context r2) {
        super(r2);
        init(r2, null);
    }

    public JLatexMathView(Context r1, AttributeSet r2) {
        super(r1, r2);
        init(r1, r2);
    }

    private static float alignment(int r1, float r2) {
        if (r1 != 0) goto L6;
        return 0.0f;
    L6:
        if (1 == r1) goto L8;
        return r2;
    L8:
        return r2 / 2.0f;
    }

    private void init(Context r6, AttributeSet r7) {
        TypedArray r72 = r6.obtainStyledAttributes(r7, R.styleable.JLatexMathView);
        int r0 = r72.getResourceId(R.styleable.JLatexMathView_jlmv_background, 0);     // Catch: Throwable -> L8
        if (r0 == 0) goto L15;
        String r2 = r6.getResources().getResourceTypeName(r0);     // Catch: Throwable -> L8
        if ("drawable".equals(r2) == false) goto L11;
        Drawable r02 = r72.getDrawable(R.styleable.JLatexMathView_jlmv_background);     // Catch: Throwable -> L8
    L16:
        textSize(r72.getDimensionPixelSize(R.styleable.JLatexMathView_jlmv_textSize, 0)).textColor(r72.getColor(R.styleable.JLatexMathView_jlmv_textColor, Color.black.getColorInt())).background(r02).align(r72.getInteger(R.styleable.JLatexMathView_jlmv_alignVertical, 0), r72.getInteger(R.styleable.JLatexMathView_jlmv_alignHorizontal, 0));     // Catch: Throwable -> L8
        r72.recycle();
        if (isInEditMode() == false) goto L24;
        JLatexMathAndroid.init(r6);
        setLatex("\\begin{array}{l}\\forall\\varepsilon\\in\\mathbb{R}_+^*\\ \\exists\\eta>0\\ |x-x_0|\\leq\\eta\\Longrightarrow|f(x)-f(x_0)|\\leq\\varepsilon\\\\".concat("\\det\\begin{bmatrix}a_{11}&a_{12}&\\cdots&a_{1n}\\\\a_{21}&\\ddots&&\\vdots\\\\\\vdots&&\\ddots&\\vdots\\\\a_{n1}&\\cdots&\\cdots&a_{nn}\\end{bmatrix}\\overset{\\mathrm{def}}{=}\\sum_{\\sigma\\in\\mathfrak{S}_n}\\varepsilon(\\sigma)\\prod_{k=1}^n a_{k\\sigma(k)}\\\\").concat("\\sideset{_\\alpha^\\beta}{_\\gamma^\\delta}{\\begin{pmatrix}a&b\\\\c&d\\end{pmatrix}}\\\\").concat("\\int_0^\\infty{x^{2n} e^{-a x^2}\\,dx} = \\frac{2n-1}{2a} \\int_0^\\infty{x^{2(n-1)} e^{-a x^2}\\,dx} = \\frac{(2n-1)!!}{2^{n+1}} \\sqrt{\\frac{\\pi}{a^{2n+1}}}\\\\").concat("\\int_a^b{f(x)\\,dx} = (b - a) \\sum\\limits_{n = 1}^\\infty  {\\sum\\limits_{m = 1}^{2^n  - 1} {\\left( { - 1} \\right)^{m + 1} } } 2^{ - n} f(a + m\\left( {b - a} \\right)2^{-n} )\\\\").concat("\\int_{-\\pi}^{\\pi} \\sin(\\alpha x) \\sin^n(\\beta x) dx = \\textstyle{\\left \\{ \\begin{array}{cc} (-1)^{(n+1)/2} (-1)^m \\frac{2 \\pi}{2^n} \\binom{n}{m} & n \\mbox{ odd},\\ \\alpha = \\beta (2m-n) \\\\ 0 & \\mbox{otherwise} \\\\ \\end{array} \\right .}\\\\").concat("L = \\int_a^b \\sqrt{ \\left|\\sum_{i,j=1}^ng_{ij}(\\gamma(t))\\left(\\frac{d}{dt}x^i\\circ\\gamma(t)\\right)\\left(\\frac{d}{dt}x^j\\circ\\gamma(t)\\right)\\right|}\\,dt\\\\").concat("\\begin{array}{rl} s &= \\int_a^b\\left\\|\\frac{d}{dt}\\vec{r}\\,(u(t),v(t))\\right\\|\\,dt \\\\ &= \\int_a^b \\sqrt{u'(t)^2\\,\\vec{r}_u\\cdot\\vec{r}_u + 2u'(t)v'(t)\\, \\vec{r}_u\\cdot\\vec{r}_v+ v'(t)^2\\,\\vec{r}_v\\cdot\\vec{r}_v}\\,\\,\\, dt. \\end{array}\\\\").concat("\\end{array}"));
        return;
    L24:
        return;
    L11:
        if ("color".equals(r2) == false) goto L14;
        r02 = new ColorDrawable(r72.getColor(R.styleable.JLatexMathView_jlmv_background, 0));     // Catch: Throwable -> L8
        goto L16
    L14:
        throw new IllegalStateException(String.format("Unexpected background reference: %s is of type: %s. Supported: drawable, color", new Object[]{r6.getResources().getResourceName(r0), r2}));     // Catch: Throwable -> L8
    L15:
        r02 = null;
    L8:
        th = move-exception;
        r72.recycle();
        throw th;
    }

    public JLatexMathView align(@Align int r1, @Align int r2) {
        this.alignVertical = r1;
        this.alignHorizontal = r2;
        return this;
    }

    public JLatexMathView background(Drawable r1) {
        this.background = r1;
        return this;
    }

    public void clear() {
        this.drawable = null;
        requestLayout();
    }

    @Override // android.view.View
    public void onDraw(Canvas r5) {
        super.onDraw(r5);
        if (this.drawable != null) goto L5;
        return;
    L5:
        int r0 = r5.save();
        float r1 = this.left;     // Catch: Throwable -> L9
        if (r1 <= 0.0f) goto L11;
        r5.translate(r1, 0.0f);     // Catch: Throwable -> L9
    L11:
        float r12 = this.top;     // Catch: Throwable -> L9
        if (r12 <= 0.0f) goto L14;
        r5.translate(0.0f, r12);     // Catch: Throwable -> L9
    L14:
        float r13 = this.scale;     // Catch: Throwable -> L9
        if (r13 > 0.0f) goto L17;
    L19:
        this.drawable.draw(r5);     // Catch: Throwable -> L9
        r5.restoreToCount(r0);
        return;
    L17:
        if (Float.compare(r13, 1.0f) == 0) goto L19;
        float r14 = this.scale;     // Catch: Throwable -> L9
        r5.scale(r14, r14);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        r5.restoreToCount(r0);
        throw th;
    }

    @Override // android.view.View
    public void onMeasure(int r11, int r12) {
        if (this.drawable != null) goto L6;
        super.onMeasure(r11, r12);
        return;
    L6:
        int r0 = View.MeasureSpec.getMode(r11);
        int r112 = View.MeasureSpec.getSize(r11);
        int r1 = View.MeasureSpec.getMode(r12);
        int r122 = View.MeasureSpec.getSize(r12);
        int r2 = this.drawable.getIntrinsicWidth();
        int r3 = this.drawable.getIntrinsicHeight();
        int r4 = getPaddingLeft();
        int r5 = getPaddingTop();
        if (1073741824 == r0) goto L13;
        int r7 = (r2 + r4) + getPaddingRight();
        if (r112 <= 0) goto L12;
        r112 = Math.min(r112, r7);
        goto L13
    L12:
        r112 = r7;
    L13:
        if (1073741824 == r1) goto L19;
        int r72 = (r3 + r5) + getPaddingBottom();
        if (r122 <= 0) goto L18;
        r122 = Math.min(r122, r72);
        goto L19
    L18:
        r122 = r72;
    L19:
        int r73 = (r112 - r4) - getPaddingRight();
        int r8 = (r122 - r5) - getPaddingBottom();
        if (r2 >= r73) goto L23;
        if (r3 >= r8) goto L23;
        float r74 = 1.0f;
    L24:
        int r22 = (int) ((r2 * r74) + 0.5f);
        int r32 = (int) ((r3 * r74) + 0.5f);
        if (1073741824 == r0) goto L27;
        r112 = (r22 + r4) + getPaddingRight();
    L27:
        if (1073741824 == r1) goto L29;
        r122 = (r32 + r5) + getPaddingBottom();
    L29:
        float r02 = alignment(this.alignHorizontal, ((r112 - r4) - getPaddingRight()) - r22);
        float r13 = alignment(this.alignVertical, ((r122 - r5) - getPaddingBottom()) - r32);
        this.scale = r74;
        this.left = r4 + r02;
        this.top = r5 + r13;
        setMeasuredDimension(r112, r122);
        return;
    L23:
        r74 = Math.min(r73 / r2, r8 / r3);
        goto L24
    }

    public void setLatex(String r2) {
        setLatexDrawable(JLatexMathDrawable.builder(r2).textSize(this.textSize).color(this.textColor).background(this.background).fitCanvas(false).build());
    }

    public void setLatexDrawable(JLatexMathDrawable r1) {
        this.drawable = r1;
        requestLayout();
    }

    public JLatexMathView textColor(int r1) {
        this.textColor = r1;
        return this;
    }

    public JLatexMathView textSize(int r1) {
        this.textSize = r1;
        return this;
    }
}
