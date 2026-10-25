package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MetricAffectingSpan;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class QuoteSpan implements LeadingMarginSpan {
    public static int COLLAPSE_LINES = 3;
    public boolean adaptLineHeight;
    private final Paint backgroundPaint;
    private final Path backgroundPath;
    private final float[] backgroundPathRadii;
    public QuoteCollapseButton collapseButton;
    public QuoteCollapsedPart collapsedSpan;
    private int color;
    public final boolean edit;
    public int end;
    public boolean first;
    public boolean isCollapsing;
    public boolean last;
    private final Paint linePaint;
    private final Path linePath;
    private final float[] linePathRadii;
    private SpannableString newline;
    private final Drawable quoteDrawable;
    public boolean rtl;
    public boolean singleLine;
    public int start;
    public final QuoteStyleSpan styleSpan;

    /* loaded from: classes7.dex */
    public static class Block {
        public final int bottom;
        public RectF collapseButtonBounds;
        public final TextPaint paint;
        public final QuoteSpan span;
        public final int top;
        public final View view;
        public final int width;

        public Block(View r7, Layout r8, Spanned r9, QuoteSpan r10) {
            this.view = r7;
            this.span = r10;
            this.paint = r8.getPaint();
            r10.start = r9.getSpanStart(r10);
            int r0 = r9.getSpanEnd(r10);
            r10.end = r0;
            if ((r0 - 1) >= 0) goto L5;
        L11:
            int r92 = r8.getLineForOffset(r10.start);
            int r02 = r8.getLineForOffset(r10.end);
            if ((r02 - r92) >= 1) goto L14;
            boolean r1 = true;
        L15:
            r10.singleLine = r1;
            if (r92 > 0) goto L18;
            boolean r12 = true;
        L19:
            r10.first = r12;
            if ((r02 + 1) < r8.getLineCount()) goto L22;
            boolean r13 = true;
        L23:
            r10.last = r13;
            if (r10.edit == false) goto L42;
            int r14 = r8.getLineTop(r92);
            if (r10.singleLine == false) goto L29;
            int r5 = 0;
        L33:
            this.top = r14 + AndroidUtilities.dp(3 - r5);
            int r15 = r8.getLineBottom(r02);
            if (r10.singleLine == false) goto L37;
            int r52 = 0;
        L41:
            this.bottom = r15 - AndroidUtilities.dp(2 - r52);
        L51:
            r10.rtl = false;
            float r3 = 0.0f;
        L52:
            if (r92 > r02) goto L57;
            r3 = Math.max(r3, r8.getLineRight(r92));
            if (r8.getLineLeft(r92) <= 0.0f) goto L56;
            r10.rtl = true;
        L56:
            r92 = r92 + 1;
            goto L52
        L57:
            this.width = (int) Math.ceil(r3);
            if (r10.edit == false) goto L67;
            if (r7 != null) goto L61;
            return;
        L61:
            if (r10.collapseButton != null) goto L69;
            r10.collapseButton = new QuoteCollapseButton(r7);
            return;
        L69:
            return;
        L67:
            return;
        L37:
            if (r10.last == false) goto L39;
            int r53 = 2;
        L40:
            r52 = r53 + 3;
            goto L41
        L39:
            r53 = 0;
            goto L40
        L29:
            if (r10.first == false) goto L31;
            int r54 = 2;
        L32:
            r5 = r54 + 3;
            goto L33
        L31:
            r54 = 0;
            goto L32
        L42:
            int r16 = r8.getLineTop(r92);
            if (r10.singleLine == false) goto L45;
            int r55 = 1;
        L46:
            this.top = r16 + AndroidUtilities.dp(3 - r55);
            int r17 = r8.getLineBottom(r02);
            if (r10.singleLine == false) goto L49;
            int r56 = 1;
        L50:
            this.bottom = r17 - AndroidUtilities.dp(2 - r56);
            goto L51
        L49:
            r56 = 2;
            goto L50
        L45:
            r55 = 2;
            goto L46
        L22:
            r13 = false;
            goto L23
        L18:
            r12 = false;
            goto L19
        L14:
            r1 = false;
            goto L15
        L5:
            if (r0 >= r9.length()) goto L11;
            if (r9.charAt(r10.end) == '\n') goto L11;
            if (r9.charAt(r10.end - 1) != '\n') goto L11;
            r10.end--;
            goto L11
        }

        public int buttonWidth() {
            QuoteCollapseButton r1 = this.span.collapseButton;
            if (r1 == null) goto L7;
            return r1.width();
        L7:
            return AndroidUtilities.dp(23.66f) + (AndroidUtilities.dp(3.333f) * 2);
        }

        public void draw(Canvas r23, float r24, int r25, int r26, float r27, TextPaint r28) {
            this.span.setColor(r26);
            if (this.span.edit == false) goto L5;
            int r1 = r25;
        L7:
            if (r1 < (r25 * 0.95d)) goto L9;
            int r9 = r25;
        L10:
            r23.save();
            r23.translate(0.0f, r24);
            RectF r11 = AndroidUtilities.rectTmp;
            r11.set(0.0f, this.top, r9, this.bottom);
            float[] r12 = QuoteSpan.m12278$$Nest$fgetbackgroundPathRadii(this.span);
            float[] r3 = QuoteSpan.m12278$$Nest$fgetbackgroundPathRadii(this.span);
            float[] r4 = QuoteSpan.m12278$$Nest$fgetbackgroundPathRadii(this.span);
            QuoteSpan.m12278$$Nest$fgetbackgroundPathRadii(this.span)[7] = 0.0f;
            r4[6] = 0.0f;
            r3[1] = 0.0f;
            r12[0] = 0.0f;
            float[] r13 = QuoteSpan.m12278$$Nest$fgetbackgroundPathRadii(this.span);
            float[] r32 = QuoteSpan.m12278$$Nest$fgetbackgroundPathRadii(this.span);
            float[] r42 = QuoteSpan.m12278$$Nest$fgetbackgroundPathRadii(this.span);
            float[] r5 = QuoteSpan.m12278$$Nest$fgetbackgroundPathRadii(this.span);
            float r7 = AndroidUtilities.dp(4.0f);
            r5[5] = r7;
            r42[4] = r7;
            r32[3] = r7;
            r13[2] = r7;
            QuoteSpan.m12277$$Nest$fgetbackgroundPath(this.span).rewind();
            Path r14 = QuoteSpan.m12277$$Nest$fgetbackgroundPath(this.span);
            float[] r33 = QuoteSpan.m12278$$Nest$fgetbackgroundPathRadii(this.span);
            Path.Direction r43 = Path.Direction.CW;
            r14.addRoundRect(r11, r33, r43);
            r23.drawPath(QuoteSpan.m12277$$Nest$fgetbackgroundPath(this.span), QuoteSpan.m12276$$Nest$fgetbackgroundPaint(this.span));
            QuoteSpan r15 = this.span;
            if (r15.edit == true) goto L13;
        L20:
            char r242 = 7;
            Path.Direction r122 = r43;
        L21:
            r11.set(-AndroidUtilities.dp(3.0f), this.top, 0.0f, this.bottom);
            float[] r16 = QuoteSpan.m12282$$Nest$fgetlinePathRadii(this.span);
            float[] r34 = QuoteSpan.m12282$$Nest$fgetlinePathRadii(this.span);
            float[] r44 = QuoteSpan.m12282$$Nest$fgetlinePathRadii(this.span);
            float[] r52 = QuoteSpan.m12282$$Nest$fgetlinePathRadii(this.span);
            float r6 = AndroidUtilities.dp(4.0f);
            r52[r242] = r6;
            r44[6] = r6;
            r34[1] = r6;
            r16[0] = r6;
            float[] r17 = QuoteSpan.m12282$$Nest$fgetlinePathRadii(this.span);
            float[] r35 = QuoteSpan.m12282$$Nest$fgetlinePathRadii(this.span);
            float[] r45 = QuoteSpan.m12282$$Nest$fgetlinePathRadii(this.span);
            QuoteSpan.m12282$$Nest$fgetlinePathRadii(this.span)[5] = 0.0f;
            r45[4] = 0.0f;
            r35[3] = 0.0f;
            r17[2] = 0.0f;
            QuoteSpan.m12281$$Nest$fgetlinePath(this.span).rewind();
            QuoteSpan.m12281$$Nest$fgetlinePath(this.span).addRoundRect(r11, QuoteSpan.m12282$$Nest$fgetlinePathRadii(this.span), r122);
            r23.drawPath(QuoteSpan.m12281$$Nest$fgetlinePath(this.span), QuoteSpan.m12280$$Nest$fgetlinePaint(this.span));
            if (this.span.rtl == true) goto L27;
            int r18 = (int) (((this.top + this.bottom) - QuoteSpan.m12283$$Nest$fgetquoteDrawable(r1).getIntrinsicHeight()) / 2.0f);
            if (r18 <= (this.top + AndroidUtilities.dp(8.0f))) goto L26;
            r18 = this.top + AndroidUtilities.dp(4.0f);
        L26:
            QuoteSpan.m12283$$Nest$fgetquoteDrawable(this.span).setBounds((r9 - QuoteSpan.m12283$$Nest$fgetquoteDrawable(this.span).getIntrinsicWidth()) - AndroidUtilities.dp(4.0f), r18, r9 - AndroidUtilities.dp(4.0f), QuoteSpan.m12283$$Nest$fgetquoteDrawable(this.span).getIntrinsicHeight() + r18);
            QuoteSpan.m12283$$Nest$fgetquoteDrawable(this.span).setAlpha((int) (255.0f * r27));
            QuoteSpan.m12283$$Nest$fgetquoteDrawable(this.span).draw(r23);
        L27:
            r23.restore();
            return;
        L13:
            if (this.view == null) goto L20;
            if (r15.collapseButton == null) goto L20;
            if (this.collapseButtonBounds != null) goto L19;
            this.collapseButtonBounds = new RectF();
        L19:
            int r19 = AndroidUtilities.dp(3.333f);
            QuoteSpan r36 = this.span;
            r242 = 7;
            r122 = r43;
            r36.collapseButton.draw(r23, this.collapseButtonBounds, r9 - r19, this.bottom - r19, r26, r36.isCollapsing, hasButton());
            goto L21
        L9:
            r9 = r1;
            goto L10
        L5:
            r1 = this.width + AndroidUtilities.dp(32.0f);
            goto L7
        }

        public boolean hasButton() {
            if (this.span.edit == true) goto L5;
            return false;
        L5:
            if ((this.bottom - this.top) <= ((this.paint.getTextSize() * 1.3f) * QuoteSpan.COLLAPSE_LINES)) goto L10;
            return true;
        L10:
            return false;
        }
    }

    /* loaded from: classes7.dex */
    public static class ExpandDrawable extends Drawable {
        private int alpha;
        private final AnimatedFloat animatedState;
        private final Paint paint;
        private final Path path;
        private boolean state;
        private final View view;

        public ExpandDrawable(View r11) {
            Paint r0 = new Paint(1);
            this.paint = r0;
            Path r1 = new Path();
            this.path = r1;
            this.alpha = 255;
            this.view = r11;
            r0.setStyle(Paint.Style.STROKE);
            r0.setStrokeCap(Paint.Cap.ROUND);
            r0.setStrokeJoin(Paint.Join.ROUND);
            r0.setStrokeWidth(AndroidUtilities.dp(1.0f));
            this.animatedState = new AnimatedFloat(r11, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
            float r10 = AndroidUtilities.dpf2(4.66f);
            float r112 = AndroidUtilities.dpf2(2.16f);
            r1.rewind();
            r1.moveTo(r10 / 2.0f, 0.0f);
            float r102 = (-r10) / 2.0f;
            r1.lineTo(r102, 0.0f);
            float r02 = r102 + r112;
            r1.lineTo(r02, -r112);
            r1.moveTo(r102, 0.0f);
            r1.lineTo(r02, r112);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r7) {
            int r0 = getBounds().centerX();
            int r1 = getBounds().centerY();
            float r2 = this.animatedState.set(this.state);
            float r3 = AndroidUtilities.dpf2(2.51f);
            r7.save();
            r7.translate(r0, r1);
            r7.save();
            r7.translate(r3, r3);
            r7.rotate(45.0f);
            r7.scale(AndroidUtilities.lerp(-1.0f, 1.0f, r2), 1.0f);
            r7.drawPath(this.path, this.paint);
            r7.restore();
            r7.save();
            float r32 = -r3;
            r7.translate(r32, r32);
            r7.rotate(225.0f);
            r7.scale(AndroidUtilities.lerp(-1.0f, 1.0f, r2), 1.0f);
            r7.drawPath(this.path, this.paint);
            r7.restore();
            r7.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            Paint r0 = this.paint;
            this.alpha = r2;
            r0.setAlpha(r2);
        }

        public void setColor(int r2) {
            this.paint.setColor(r2);
            this.paint.setAlpha(this.alpha);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public void setState(boolean r2) {
            if (this.state == r2) goto L6;
            this.state = r2;
            this.view.invalidate();
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static class QuoteButtonNewLineSpan extends CharacterStyle {
        public QuoteButtonNewLineSpan() {
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
        }
    }

    /* loaded from: classes7.dex */
    public static class QuoteCollapsedPart extends CharacterStyle {
        private final QuoteSpan span;

        public QuoteCollapsedPart(QuoteSpan r1) {
            this.span = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            r3.setColor(Theme.blendOver(Theme.multAlpha(r3.getColor(), 0.55f), Theme.multAlpha(QuoteSpan.m12279$$Nest$fgetcolor(this.span), 0.4f)));
        }
    }

    public static class QuoteStyleSpan extends MetricAffectingSpan implements LineHeightSpan {
        public QuoteSpan span;

        public QuoteStyleSpan() {
        }

        @Override // android.text.style.LineHeightSpan
        public void chooseHeight(CharSequence r2, int r3, int r4, int r5, int r6, Paint.FontMetricsInt r7) {
            QuoteSpan r22 = this.span;
            if (r22.adaptLineHeight == false) goto L23;
            int r62 = 2;
            if (r22.singleLine == false) goto L7;
            int r52 = 7;
        L9:
            if (r3 > r22.start) goto L20;
            int r32 = r7.ascent;
            if (r22.last == false) goto L13;
            int r23 = 2;
        L14:
            r7.ascent = r32 - AndroidUtilities.dp(r23 + r52);
            int r24 = r7.top;
            if (this.span.last == true) goto L18;
            r62 = 0;
        L18:
            r7.top = r24 - AndroidUtilities.dp(r62 + r52);
            goto L20
        L13:
            r23 = 0;
        L20:
            if (r4 < this.span.end) goto L24;
            float r25 = r52;
            r7.descent += AndroidUtilities.dp(r25);
            r7.bottom += AndroidUtilities.dp(r25);
            return;
        L24:
            return;
        L7:
            r52 = 2;
            goto L9
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            if (r1 != null) goto L5;
            return;
        L5:
            if (this.span.edit == false) goto L7;
            float r0 = 16.0f;
        L8:
            r1.setTextSize(AndroidUtilities.dp(r0));
            return;
        L7:
            r0 = SharedConfig.fontSize - 2;
            goto L8
        }

        @Override // android.text.style.MetricAffectingSpan
        public void updateMeasureState(TextPaint r2) {
            if (this.span.edit == false) goto L5;
            float r0 = 16.0f;
        L6:
            r2.setTextSize(AndroidUtilities.dp(r0));
            if (this.span.edit == false) goto L9;
            float r1 = 1.1f;
        L10:
            r2.setTextScaleX(r1);
            return;
        L9:
            r1 = 1.0f;
            goto L10
        L5:
            r0 = SharedConfig.fontSize - 2;
            goto L6
        }
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12276$$Nest$fgetbackgroundPaint(QuoteSpan r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetbackgroundPath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m12277$$Nest$fgetbackgroundPath(QuoteSpan r0) {
        return r0.backgroundPath;
    }

    /* renamed from: -$$Nest$fgetbackgroundPathRadii, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m12278$$Nest$fgetbackgroundPathRadii(QuoteSpan r0) {
        return r0.backgroundPathRadii;
    }

    /* renamed from: -$$Nest$fgetcolor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12279$$Nest$fgetcolor(QuoteSpan r0) {
        return r0.color;
    }

    /* renamed from: -$$Nest$fgetlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12280$$Nest$fgetlinePaint(QuoteSpan r0) {
        return r0.linePaint;
    }

    /* renamed from: -$$Nest$fgetlinePath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m12281$$Nest$fgetlinePath(QuoteSpan r0) {
        return r0.linePath;
    }

    /* renamed from: -$$Nest$fgetlinePathRadii, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m12282$$Nest$fgetlinePathRadii(QuoteSpan r0) {
        return r0.linePathRadii;
    }

    /* renamed from: -$$Nest$fgetquoteDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m12283$$Nest$fgetquoteDrawable(QuoteSpan r0) {
        return r0.quoteDrawable;
    }

    static {
    }

    public QuoteSpan(boolean r5, boolean r6, QuoteStyleSpan r7) {
        this.adaptLineHeight = true;
        Paint r1 = new Paint(1);
        this.backgroundPaint = r1;
        this.backgroundPathRadii = new float[8];
        this.backgroundPath = new Path();
        Paint r3 = new Paint(1);
        this.linePaint = r3;
        this.linePathRadii = new float[8];
        this.linePath = new Path();
        this.color = -1;
        this.edit = r5;
        this.styleSpan = r7;
        this.isCollapsing = r6;
        this.quoteDrawable = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.mini_quote).mutate();
        r3.setColor(this.color);
        r1.setColor(ColorUtils.setAlphaComponent(this.color, 30));
    }

    public static void mergeQuotes(SpannableStringBuilder r11, ArrayList<TLRPC.MessageEntity> r12) {
        if (r12 == null) goto L76;
        if (r11 == null) goto L79;
        TreeSet r0 = new TreeSet();
        HashMap r1 = new HashMap();
        int r3 = 0;
    L6:
        int r5 = 1;
        if (r3 >= r12.size()) goto L26;
        TLRPC.MessageEntity r4 = r12.get(r3);
        if ((r4.offset + r4.length) > r11.length()) goto L25;
        int r6 = r4.offset;
        int r7 = r4.length + r6;
        if ((r4 instanceof TLRPC.TL_messageEntityBlockquote) == false) goto L25;
        r0.add(Integer.valueOf(r6));
        r0.add(Integer.valueOf(r7));
        Integer r8 = Integer.valueOf(r6);
        if (r1.containsKey(Integer.valueOf(r6)) == false) goto L16;
        int r62 = ((Integer) r1.get(Integer.valueOf(r6))).intValue();
    L18:
        if (r4.collapsed == false) goto L20;
        r5 = 16;
    L20:
        r1.put(r8, Integer.valueOf(r62 | r5));
        Integer r42 = Integer.valueOf(r7);
        if (r1.containsKey(Integer.valueOf(r7)) == false) goto L23;
        int r52 = ((Integer) r1.get(Integer.valueOf(r7))).intValue();
    L24:
        r1.put(r42, Integer.valueOf(r52 | 2));
        goto L25
    L23:
        r52 = 0;
        goto L24
    L16:
        r62 = 0;
    L25:
        r3 = r3 + 1;
        goto L6
    L26:
        Iterator r122 = r0.iterator();
        int r02 = 0;
        int r32 = 0;
        boolean r43 = false;
    L28:
        if (r122.hasNext() == false) goto L59;
        Integer r63 = (Integer) r122.next();
        int r72 = r63.intValue();
        int r64 = ((Integer) r1.get(r63)).intValue();
        if (r02 == r72) goto L48;
        int r82 = r72 - 1;
        if (r82 >= 0) goto L34;
    L38:
        int r83 = r72;
    L39:
        if (r32 <= 0) goto L41;
        putQuoteToEditable(r11, r02, r83, r43);
    L41:
        r02 = r72 + 1;
        if (r02 < r11.length()) goto L44;
    L46:
        r02 = r72;
        goto L48
    L44:
        if (r11.charAt(r72) != '\n') goto L46;
    L34:
        if (r82 >= r11.length()) goto L38;
        if (r11.charAt(r82) != '\n') goto L38;
        r83 = r72 - 1;
    L48:
        if ((r64 & 2) == 0) goto L51;
        r32 = r32 - 1;
    L51:
        if ((r64 & 1) != 0) goto L54;
        if ((r64 & 16) == 0) goto L28;
    L54:
        r32 = r32 + 1;
        if ((r64 & 16) != 0) goto L56;
        r43 = false;
        goto L28
    L56:
        r43 = true;
        goto L28
    L59:
        if (r02 >= r11.length()) goto L77;
        if (r32 <= 0) goto L78;
        putQuoteToEditable(r11, r02, r11.length(), r43);
        return;
    L78:
        return;
    L77:
        return;
    L79:
        return;
    }

    public static void normalizeQuotes(Editable r12) {
        if (r12 == null) goto L70;
        TreeSet r0 = new TreeSet();
        HashMap r1 = new HashMap();
        QuoteStyleSpan[] r2 = (QuoteStyleSpan[]) r12.getSpans(0, r12.length(), QuoteStyleSpan.class);
        int r3 = 0;
    L5:
        int r6 = 1;
        if (r3 >= r2.length) goto L19;
        QuoteStyleSpan r5 = r2[r3];
        int r7 = r12.getSpanStart(r5);
        int r8 = r12.getSpanEnd(r5);
        r0.add(Integer.valueOf(r7));
        Integer r9 = Integer.valueOf(r7);
        if (r1.containsKey(Integer.valueOf(r7)) == false) goto L10;
        int r72 = ((Integer) r1.get(Integer.valueOf(r7))).intValue();
    L12:
        if (r5.span.isCollapsing == false) goto L14;
        r6 = 16;
    L14:
        r1.put(r9, Integer.valueOf(r6 | r72));
        r0.add(Integer.valueOf(r8));
        Integer r62 = Integer.valueOf(r8);
        if (r1.containsKey(Integer.valueOf(r8)) == false) goto L17;
        int r73 = ((Integer) r1.get(Integer.valueOf(r8))).intValue();
    L18:
        r1.put(r62, Integer.valueOf(r73 | 2));
        r12.removeSpan(r5);
        r12.removeSpan(r5.span);
        r3 = r3 + 1;
        goto L5
    L17:
        r73 = 0;
        goto L18
    L10:
        r72 = 0;
        goto L12
    L19:
        Iterator r02 = r0.iterator();
        int r22 = 0;
        int r32 = 0;
        boolean r52 = false;
    L21:
        if (r02.hasNext() == false) goto L52;
        Integer r74 = (Integer) r02.next();
        int r82 = r74.intValue();
        int r75 = ((Integer) r1.get(r74)).intValue();
        if (r22 == r82) goto L41;
        int r92 = r82 - 1;
        if (r92 >= 0) goto L27;
    L31:
        int r93 = r82;
    L32:
        if (r32 <= 0) goto L34;
        putQuoteToEditable(r12, r22, r93, r52);
    L34:
        r22 = r82 + 1;
        if (r22 < r12.length()) goto L37;
    L39:
        r22 = r82;
        goto L41
    L37:
        if (r12.charAt(r82) != '\n') goto L39;
    L27:
        if (r92 >= r12.length()) goto L31;
        if (r12.charAt(r92) != '\n') goto L31;
        r93 = r82 - 1;
    L41:
        if ((r75 & 2) == 0) goto L44;
        r32 = r32 - 1;
    L44:
        if ((r75 & 1) != 0) goto L47;
        if ((r75 & 16) == 0) goto L21;
    L47:
        r32 = r32 + 1;
        if ((r75 & 16) != 0) goto L49;
        r52 = false;
        goto L21
    L49:
        r52 = true;
        goto L21
    L52:
        if (r22 >= r12.length()) goto L68;
        if (r32 <= 0) goto L69;
        putQuoteToEditable(r12, r22, r12.length(), r52);
        return;
    L69:
        return;
    L68:
        return;
    }

    public static boolean onTouch(MotionEvent r11, int r12, ArrayList<Block> r13, Runnable r14) {
        if (r13 != null) goto L5;
        return false;
    L5:
        int r1 = r13.size();
        boolean r2 = false;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L39;
        Block r4 = r13.get(r3);
        r3 = r3 + 1;
        Block r42 = r4;
        QuoteCollapseButton r5 = r42.span.collapseButton;
        if (r42.hasButton() == true) goto L10;
    L12:
        boolean r6 = false;
    L14:
        if (r11.getAction() != 0) goto L18;
        if (r5 == null) goto L33;
        r5.setPressed(r6);
    L33:
        if (r5 != null) goto L35;
    L36:
        if (r2 == true) goto L37;
        r2 = false;
    L37:
        r2 = true;
        goto L6
    L35:
        if (r5.isPressed() == true) goto L37;
    L18:
        if (r11.getAction() != 1) goto L30;
        if (r5 != null) goto L21;
    L27:
        if (r5 == null) goto L33;
        r5.setPressed(false);
        goto L33
    L21:
        if (r5.isPressed() == false) goto L27;
        if (r6 == false) goto L27;
        r42.span.isCollapsing = !r2.isCollapsing;
        if (r14 == null) goto L26;
        r14.run();
    L26:
        r2 = true;
        goto L27
    L30:
        if (r11.getAction() != 3) goto L33;
        if (r5 == null) goto L33;
        r5.setPressed(false);
        goto L33
    L10:
        if (r42.collapseButtonBounds.contains(r11.getX(), r11.getY() - r12) == false) goto L12;
        r6 = true;
        goto L14
    L39:
        return r2;
    }

    public static int putQuote(Spannable r3, int r4, int r5, boolean r6) {
        if (r3 != null) goto L5;
        return -1;
    L5:
        QuoteSpan[] r1 = (QuoteSpan[]) r3.getSpans(r4, r5, QuoteSpan.class);
        if (r1 != null) goto L8;
    L10:
        int r42 = Utilities.clamp(r4, r3.length(), 0);
        int r52 = Utilities.clamp(r5, r3.length(), 0);
        QuoteStyleSpan r0 = new QuoteStyleSpan();
        QuoteSpan r2 = new QuoteSpan(false, r6, r0);
        r0.span = r2;
        r2.start = r42;
        r2.end = r52;
        r3.setSpan(r0, r42, r52, 33);
        r3.setSpan(r2, r42, r52, 33);
        return r52;
    L8:
        if (r1.length <= 0) goto L10;
        return -1;
    }

    public static int putQuoteToEditable(Editable r6, int r7, int r8, boolean r9) {
        if (r6 != null) goto L5;
        return -1;
    L5:
        int r72 = Utilities.clamp(r7, r6.length(), 0);
        int r82 = Utilities.clamp(r8, r6.length(), 0);
        if (r72 > 0) goto L8;
    L10:
        int r3 = r82 + 1;
        if (r82 < r6.length()) goto L13;
    L14:
        r6.insert(r82, "\n");
    L15:
        QuoteStyleSpan r0 = new QuoteStyleSpan();
        QuoteSpan r2 = new QuoteSpan(true, r9, r0);
        r0.span = r2;
        r2.start = r72;
        r2.end = r82;
        r6.setSpan(r2, Utilities.clamp(r72, r6.length(), 0), Utilities.clamp(r82, r6.length(), 0), 33);
        r6.setSpan(r0, Utilities.clamp(r72, r6.length(), 0), Utilities.clamp(r82, r6.length(), 0), 33);
        r6.insert(Utilities.clamp(r82, r6.length(), 0), "\ufeff");
        r6.delete(Utilities.clamp(r82, r6.length(), 0), Utilities.clamp(r3, r6.length(), 0));
        return r3;
    L13:
        if (r6.charAt(r82) == '\n') goto L15;
    L8:
        if (r6.charAt(r72 - 1) == '\n') goto L10;
        r6.insert(r72, "\n");
        r72 = r72 + 1;
        r82 = r82 + 1;
        goto L10
    }

    public static CharSequence stripNewlineHacks(CharSequence r5) {
        if (r5 != null) goto L6;
        return null;
    L6:
        if ((r5 instanceof Spanned) == true) goto L8;
        return r5;
    L8:
        SpannableStringBuilder r0 = new SpannableStringBuilder(r5);
        QuoteButtonNewLineSpan[] r52 = (QuoteButtonNewLineSpan[]) r0.getSpans(0, r0.length(), QuoteButtonNewLineSpan.class);
        int r1 = r52.length - 1;
    L9:
        if (r1 < 0) goto L11;
        QuoteButtonNewLineSpan r2 = r52[r1];
        int r3 = r0.getSpanStart(r2);
        int r4 = r0.getSpanEnd(r2);
        r0.removeSpan(r2);
        r0.delete(r3, r4);
        r1 = r1 - 1;
        goto L9
    L11:
        return r0;
    }

    public static ArrayList<Block> updateQuoteBlocks(View r17, Layout r18, ArrayList<Block> r19, boolean[] r20) {
        if (r18 != null) goto L7;
        if (r19 == null) goto L104;
        r19.clear();
        return r19;
    L104:
        return r19;
    L7:
        CharSequence r2 = r18.getText();
        if (r2 != null) goto L10;
    L102:
        if (r19 == null) goto L104;
        r19.clear();
        goto L104
    L10:
        if ((r2 instanceof Spannable) == false) goto L102;
        Spannable r3 = (Spannable) r2;
        if (r19 == null) goto L15;
        r19.clear();
    L15:
        boolean r6 = false;
        QuoteSpan[] r4 = (QuoteSpan[]) r3.getSpans(0, r3.length(), QuoteSpan.class);
        ArrayList<Block> r5 = r19;
        int r7 = 0;
    L17:
        if (r7 >= r4.length) goto L101;
        QuoteSpan r8 = r4[r7];
        boolean r9 = r8.last;
        Block r10 = new Block(r17, r18, r3, r8);
        QuoteSpan r82 = r10.span;
        if (r82.edit == false) goto L92;
        int r83 = r82.start;
        if (r83 == 0) goto L29;
        if (r2.charAt(r83 - 1) == '\n') goto L29;
        r3.removeSpan(r4[r7]);
        r3.removeSpan(r4[r7].styleSpan);
        Object r84 = r4[r7].collapsedSpan;
        if (r84 == null) goto L27;
        r3.removeSpan(r84);
    L27:
        boolean r16 = r6;
    L100:
        r7 = r7 + 1;
        r6 = r16;
    L29:
        if (r10.span.end == r2.length()) goto L42;
        if (r2.charAt(r10.span.end) == '\n') goto L42;
        int r85 = r10.span.end;
    L34:
        if (r85 > r2.length()) goto L40;
        if (r85 == r2.length()) goto L40;
        if (r2.charAt(r85) == '\n') goto L40;
        r85 = r85 + 1;
    L40:
        r3.removeSpan(r4[r7]);
        r3.removeSpan(r4[r7].styleSpan);
        r3.setSpan(r4[r7], r10.span.start, r85, 33);
        r3.setSpan(r4[r7].styleSpan, r10.span.start, r85, 33);
        r10 = new Block(r17, r18, r3, r4[r7]);
    L42:
        if ((r3 instanceof SpannableStringBuilder) == false) goto L78;
        SpannableStringBuilder r86 = (SpannableStringBuilder) r3;
        int r13 = r10.span.end;
        if ((r13 - 1) >= 0) goto L46;
    L48:
        boolean r11 = r6;
    L50:
        if (r10.hasButton() == false) goto L56;
        int r132 = r10.span.end;
        if ((r132 - 2) < 0) goto L56;
        if ((r18.getLineRight(r18.getLineForOffset(r132 - 1)) - AndroidUtilities.dp(12.0f)) <= (r10.width - r10.buttonWidth())) goto L56;
        boolean r133 = true;
    L57:
        if (r11 == r133) goto L78;
        int r134 = r10.span.end;
        if (r11 == false) goto L61;
        int r112 = r134 - 1;
        r86.delete(r134 - 1, r134);
        r16 = r6;
    L72:
        r10.span.end = Math.min(r112, r3.length());
        r3.removeSpan(r4[r7]);
        r3.removeSpan(r4[r7].styleSpan);
        QuoteSpan r62 = r10.span;
        int r87 = r62.start;
        int r63 = r62.end;
        if (r87 <= r63) goto L75;
        r87 = r63;
    L75:
        r3.setSpan(r4[r7], r87, r63, 33);
        r3.setSpan(r4[r7].styleSpan, r87, r63, 33);
        if (r20 == null) goto L79;
        r20[r16 ? 1 : 0] = true;
    L79:
        Object r64 = r10.span.collapsedSpan;
        if (r64 == null) goto L82;
        r3.removeSpan(r64);
    L82:
        QuoteSpan r65 = r10.span;
        if (r65.isCollapsing == false) goto L93;
        int r66 = r18.getLineStart(Math.min(r18.getLineForOffset(r65.start) + COLLAPSE_LINES, r18.getLineCount()));
        int r88 = r10.span.end;
        if (r66 <= r3.length()) goto L87;
        r66 = r3.length();
    L87:
        if (r66 >= r88) goto L93;
        QuoteSpan r113 = r10.span;
        if (r113.collapsedSpan != null) goto L91;
        r113.collapsedSpan = new QuoteCollapsedPart(r10.span);
    L91:
        r3.setSpan(r10.span.collapsedSpan, r66, r88, 33);
    L93:
        if (r5 != null) goto L96;
        r5 = new ArrayList();
    L96:
        if (r4[r7].last == r9) goto L99;
        if (r20 == null) goto L99;
        r20[r16 ? 1 : 0] = true;
    L99:
        r5.add(r10);
        goto L100
    L61:
        r112 = r134 + 2;
        if (Selection.getSelectionStart(r86) == r10.span.end) goto L64;
    L66:
        boolean r135 = r6;
    L67:
        QuoteSpan r15 = r10.span;
        r16 = r6;
        r86.insert(r15.end, r15.getNewlineHack());
        if (r135 == false) goto L72;
        int r67 = Selection.getSelectionStart(r86);
        int r136 = r10.span.end;
        if (r67 == r136) goto L72;
        Selection.setSelection(r86, r136, r136);
        goto L72
    L64:
        if (Selection.getSelectionStart(r86) != Selection.getSelectionEnd(r86)) goto L66;
        r135 = true;
    L56:
        r133 = r6;
        goto L57
    L46:
        if (r86.charAt(r13 - 1) != '\n') goto L48;
        r11 = true;
    L78:
        r16 = r6;
        goto L79
    L92:
        r16 = r6;
        goto L93
    L101:
        return r5;
    }

    public static ArrayList<Block> updateQuoteBlocksSpanned(Layout r6, ArrayList<Block> r7) {
        if (r6 != null) goto L6;
        if (r7 == null) goto L24;
        r7.clear();
        return r7;
    L24:
        return r7;
    L6:
        CharSequence r0 = r6.getText();
        if (r0 != null) goto L9;
    L22:
        if (r7 == null) goto L24;
        r7.clear();
        goto L24
    L9:
        if ((r0 instanceof Spanned) == false) goto L22;
        Spanned r02 = (Spanned) r0;
        if (r7 == null) goto L14;
        r7.clear();
    L14:
        int r3 = 0;
        QuoteSpan[] r1 = (QuoteSpan[]) r02.getSpans(0, r02.length(), QuoteSpan.class);
    L16:
        if (r3 >= r1.length) goto L21;
        QuoteSpan r2 = r1[r3];
        boolean r4 = r2.last;
        Block r42 = new Block(null, r6, r02, r2);
        if (r7 != null) goto L20;
        r7 = new ArrayList();
    L20:
        r7.add(r42);
        r3 = r3 + 1;
        goto L16
    L21:
        return r7;
    }

    @Override // android.text.style.LeadingMarginSpan
    public void drawLeadingMargin(Canvas r1, Paint r2, int r3, int r4, int r5, int r6, int r7, CharSequence r8, int r9, int r10, boolean r11, Layout r12) {
    }

    @Override // android.text.style.LeadingMarginSpan
    public int getLeadingMargin(boolean r1) {
        if (this.adaptLineHeight == false) goto L5;
        float r0 = 8.0f;
    L7:
        return AndroidUtilities.dp(r0);
    L5:
        r0 = 10.0f;
        goto L7
    }

    public SpannableString getNewlineHack() {
        if (this.newline != null) goto L6;
        SpannableString r0 = new SpannableString("\n");
        this.newline = r0;
        r0.setSpan(new QuoteButtonNewLineSpan(), 0, this.newline.length(), 33);
    L6:
        return this.newline;
    }

    public void setColor(int r4) {
        if (this.color == r4) goto L6;
        Drawable r0 = this.quoteDrawable;
        this.color = r4;
        r0.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.SRC_IN));
        this.linePaint.setColor(r4);
        this.backgroundPaint.setColor(ColorUtils.setAlphaComponent(r4, 30));
        return;
    }
}
