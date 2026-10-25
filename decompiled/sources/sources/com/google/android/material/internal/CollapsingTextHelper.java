package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import androidx.core.math.MathUtils;
import androidx.core.text.TextDirectionHeuristicCompat;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.util.Preconditions;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.StaticLayoutBuilderCompat;
import com.google.android.material.resources.CancelableFontCallback;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TypefaceUtils;

/* loaded from: classes5.dex */
public final class CollapsingTextHelper {
    private static final boolean DEBUG_DRAW = false;
    private static final Paint DEBUG_DRAW_PAINT = null;
    private static final String ELLIPSIS_NORMAL = "…";
    private static final float FADE_MODE_THRESHOLD_FRACTION_RELATIVE = 0.5f;
    private static final int ONE_LINE = 1;
    public static final int SEMITRANSPARENT_MAGENTA = 1090453759;
    private static final String TAG = "CollapsingTextHelper";
    private boolean alignBaselineAtBottom;
    private boolean boundsChanged;
    private final Rect collapsedBounds;
    private Rect collapsedBoundsForPlacement;
    private float collapsedDrawX;
    private float collapsedDrawY;
    private CancelableFontCallback collapsedFontCallback;
    private int collapsedHeight;
    private float collapsedLetterSpacing;
    private int collapsedMaxLines;
    private ColorStateList collapsedShadowColor;
    private float collapsedShadowDx;
    private float collapsedShadowDy;
    private float collapsedShadowRadius;
    private float collapsedTextBlend;
    private ColorStateList collapsedTextColor;
    private int collapsedTextGravity;
    private float collapsedTextSize;
    private float collapsedTextWidth;
    private Typeface collapsedTypeface;
    private Typeface collapsedTypefaceBold;
    private Typeface collapsedTypefaceDefault;
    private final RectF currentBounds;
    private float currentDrawX;
    private float currentDrawY;
    private float currentLetterSpacing;
    private int currentMaxLines;
    private int currentOffsetY;
    private int currentShadowColor;
    private float currentShadowDx;
    private float currentShadowDy;
    private float currentShadowRadius;
    private float currentTextSize;
    private Typeface currentTypeface;
    private final Rect expandedBounds;
    private float expandedDrawX;
    private float expandedDrawY;
    private CancelableFontCallback expandedFontCallback;
    private float expandedFraction;
    private int expandedHeight;
    private float expandedLetterSpacing;
    private int expandedLineCount;
    private int expandedMaxLines;
    private ColorStateList expandedShadowColor;
    private float expandedShadowDx;
    private float expandedShadowDy;
    private float expandedShadowRadius;
    private float expandedTextBlend;
    private ColorStateList expandedTextColor;
    private int expandedTextGravity;
    private float expandedTextSize;
    private Typeface expandedTypeface;
    private Typeface expandedTypefaceBold;
    private Typeface expandedTypefaceDefault;
    private boolean fadeModeEnabled;
    private float fadeModeStartFraction;
    private float fadeModeThresholdFraction;
    private int hyphenationFrequency;
    private boolean isRtl;
    private boolean isRtlTextDirectionHeuristicsEnabled;
    private float lineSpacingAdd;
    private float lineSpacingMultiplier;
    private TimeInterpolator positionInterpolator;
    private float scale;
    private int[] state;
    private StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer;
    private CharSequence text;
    private StaticLayout textLayout;
    private final TextPaint textPaint;
    private TimeInterpolator textSizeInterpolator;
    private CharSequence textToDraw;
    private CharSequence textToDrawCollapsed;
    private TextUtils.TruncateAt titleTextEllipsize;
    private final TextPaint tmpPaint;
    private final View view;

    /* renamed from: com.google.android.material.internal.CollapsingTextHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements CancelableFontCallback.ApplyFont {
        final /* synthetic */ CollapsingTextHelper this$0;

        public AnonymousClass1(CollapsingTextHelper r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.resources.CancelableFontCallback.ApplyFont
        public void apply(Typeface r1) {
            this.this$0.setCollapsedTypeface(r1);
        }
    }

    /* renamed from: com.google.android.material.internal.CollapsingTextHelper$2, reason: invalid class name */
    public class AnonymousClass2 implements CancelableFontCallback.ApplyFont {
        final /* synthetic */ CollapsingTextHelper this$0;

        public AnonymousClass2(CollapsingTextHelper r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.resources.CancelableFontCallback.ApplyFont
        public void apply(Typeface r1) {
            this.this$0.setExpandedTypeface(r1);
        }
    }

    static {
    }

    public CollapsingTextHelper(View r3) {
        this.expandedTextGravity = 16;
        this.collapsedTextGravity = 16;
        this.expandedTextSize = 15.0f;
        this.collapsedTextSize = 15.0f;
        this.titleTextEllipsize = TextUtils.TruncateAt.END;
        this.isRtlTextDirectionHeuristicsEnabled = true;
        this.expandedMaxLines = 1;
        this.collapsedMaxLines = 1;
        this.lineSpacingAdd = 0.0f;
        this.lineSpacingMultiplier = 1.0f;
        this.hyphenationFrequency = StaticLayoutBuilderCompat.DEFAULT_HYPHENATION_FREQUENCY;
        this.collapsedHeight = -1;
        this.expandedHeight = -1;
        this.view = r3;
        TextPaint r0 = new TextPaint(129);
        this.textPaint = r0;
        this.tmpPaint = new TextPaint(r0);
        this.collapsedBounds = new Rect();
        this.expandedBounds = new Rect();
        this.currentBounds = new RectF();
        this.fadeModeThresholdFraction = calculateFadeModeThresholdFraction();
        maybeUpdateFontWeightAdjustment(r3.getContext().getResources().getConfiguration());
    }

    private static int blendARGB(int r5, int r6, float r7) {
        float r0 = 1.0f - r7;
        return Color.argb(Math.round((Color.alpha(r5) * r0) + (Color.alpha(r6) * r7)), Math.round((Color.red(r5) * r0) + (Color.red(r6) * r7)), Math.round((Color.green(r5) * r0) + (Color.green(r6) * r7)), Math.round((Color.blue(r5) * r0) + (Color.blue(r6) * r7)));
    }

    private void calculateBaseOffsets(boolean r11) {
        calculateUsingTextSize(1.0f, r11);
        if (this.textToDraw != null) goto L5;
    L10:
        CharSequence r0 = this.textToDrawCollapsed;
        float r1 = 0.0f;
        if (r0 == null) goto L13;
        this.collapsedTextWidth = measureTextWidth(this.textPaint, r0);
    L14:
        int r02 = Gravity.getAbsoluteGravity(this.collapsedTextGravity, this.isRtl ? 1 : 0);
        Rect r2 = this.collapsedBoundsForPlacement;
        if (r2 != null) goto L18;
        r2 = this.collapsedBounds;
    L18:
        int r3 = r02 & 112;
        if (r3 == 48) goto L23;
        if (r3 == 80) goto L22;
        this.collapsedDrawY = r2.centerY() - ((this.textPaint.descent() - this.textPaint.ascent()) / 2.0f);
    L24:
        int r03 = r02 & 8388615;
        if (r03 == 1) goto L29;
        if (r03 == 5) goto L28;
        this.collapsedDrawX = r2.left;
    L31:
        if (this.collapsedTextWidth > this.collapsedBounds.width()) goto L34;
        float r04 = this.collapsedDrawX;
        float r05 = r04 + Math.max(0.0f, this.collapsedBounds.left - r04);
        this.collapsedDrawX = r05;
        this.collapsedDrawX = r05 + Math.min(0.0f, this.collapsedBounds.right - (this.collapsedTextWidth + r05));
    L34:
        if (getCollapsedFullSingleLineHeight() > this.collapsedBounds.height()) goto L36;
        float r06 = this.collapsedDrawY;
        float r07 = r06 + Math.max(0.0f, this.collapsedBounds.top - r06);
        this.collapsedDrawY = r07;
        this.collapsedDrawY = r07 + Math.min(0.0f, this.collapsedBounds.bottom - (getCollapsedTextHeight() + r07));
    L36:
        calculateUsingTextSize(0.0f, r11);
        StaticLayout r112 = this.textLayout;
        if (r112 == null) goto L39;
        float r113 = r112.getHeight();
    L40:
        StaticLayout r08 = this.textLayout;
        if (r08 != null) goto L43;
    L45:
        CharSequence r09 = this.textToDraw;
        if (r09 == null) goto L48;
        float r010 = measureTextWidth(this.textPaint, r09);
    L49:
        StaticLayout r22 = this.textLayout;
        if (r22 == null) goto L52;
        int r23 = r22.getLineCount();
    L53:
        this.expandedLineCount = r23;
        int r24 = Gravity.getAbsoluteGravity(this.expandedTextGravity, this.isRtl ? 1 : 0);
        int r9 = r24 & 112;
        if (r9 == 48) goto L62;
        Rect r5 = this.expandedBounds;
        if (r9 == 80) goto L58;
        this.expandedDrawY = r5.centerY() - (r113 / 2.0f);
    L63:
        int r114 = r24 & 8388615;
        if (r114 == 1) goto L69;
        Rect r12 = this.expandedBounds;
        if (r114 == 5) goto L68;
        this.expandedDrawX = r12.left;
    L70:
        setInterpolatedTextSize(this.expandedFraction);
        return;
    L68:
        this.expandedDrawX = r12.right - r010;
        goto L70
    L69:
        this.expandedDrawX = this.expandedBounds.centerX() - (r010 / 2.0f);
        goto L70
    L58:
        float r4 = r5.bottom - r113;
        if (this.alignBaselineAtBottom == false) goto L61;
        r1 = this.textPaint.descent();
    L61:
        this.expandedDrawY = r4 + r1;
        goto L63
    L62:
        this.expandedDrawY = this.expandedBounds.top;
        goto L63
    L52:
        r23 = 0;
        goto L53
    L48:
        r010 = 0.0f;
        goto L49
    L43:
        if (this.expandedMaxLines <= 1) goto L45;
        r010 = r08.getWidth();
        goto L49
    L39:
        r113 = 0.0f;
        goto L40
    L28:
        this.collapsedDrawX = r2.right - this.collapsedTextWidth;
        goto L31
    L29:
        this.collapsedDrawX = r2.centerX() - (this.collapsedTextWidth / 2.0f);
        goto L31
    L22:
        this.collapsedDrawY = r2.bottom + this.textPaint.ascent();
        goto L24
    L23:
        this.collapsedDrawY = r2.top;
        goto L24
    L13:
        this.collapsedTextWidth = 0.0f;
        goto L14
    L5:
        if (this.textLayout == null) goto L10;
        boolean r011 = shouldTruncateCollapsedToSingleLine();
        CharSequence r13 = this.textToDraw;
        if (r011 == false) goto L9;
        r13 = TextUtils.ellipsize(r13, this.textPaint, this.textLayout.getWidth(), this.titleTextEllipsize);
    L9:
        this.textToDrawCollapsed = r13;
        goto L10
    }

    private void calculateCurrentOffsets() {
        calculateOffsets(this.expandedFraction);
    }

    private float calculateFadeModeTextAlpha(float r5) {
        float r0 = this.fadeModeThresholdFraction;
        if (r5 > r0) goto L7;
        return AnimationUtils.lerp(1.0f, 0.0f, this.fadeModeStartFraction, r0, r5);
    L7:
        return AnimationUtils.lerp(0.0f, 1.0f, r0, 1.0f, r5);
    }

    private float calculateFadeModeThresholdFraction() {
        float r2 = this.fadeModeStartFraction;
        return r2 + ((1.0f - r2) * FADE_MODE_THRESHOLD_FRACTION_RELATIVE);
    }

    private boolean calculateIsRtl(CharSequence r3) {
        boolean r0 = isDefaultIsRtl();
        if (this.isRtlTextDirectionHeuristicsEnabled == true) goto L5;
        return r0;
    L5:
        return isTextDirectionHeuristicsIsRtl(r3, r0);
    }

    private void calculateOffsets(float r7) {
        interpolateBounds(r7);
        if (this.fadeModeEnabled == true) goto L5;
        this.currentDrawX = lerp(this.expandedDrawX, this.collapsedDrawX, r7, this.positionInterpolator);
        this.currentDrawY = lerp(this.expandedDrawY, this.collapsedDrawY, r7, this.positionInterpolator);
        setInterpolatedTextSize(r7);
        float r0 = this.collapsedLetterSpacing;
        float r3 = this.expandedLetterSpacing;
        TextPaint r5 = this.textPaint;
        if (r0 == r3) goto L11;
        r5.setLetterSpacing(lerp(r3, r0, r7, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
    L12:
        float r02 = r7;
    L13:
        TimeInterpolator r4 = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        setCollapsedTextBlend(1.0f - lerp(0.0f, 1.0f, 1.0f - r7, r4));
        setExpandedTextBlend(lerp(1.0f, 0.0f, r7, r4));
        ColorStateList r1 = this.collapsedTextColor;
        ColorStateList r2 = this.expandedTextColor;
        TextPaint r32 = this.textPaint;
        if (r1 == r2) goto L16;
        r32.setColor(blendARGB(getCurrentExpandedTextColor(), getCurrentCollapsedTextColor(), r02));
    L17:
        this.currentShadowRadius = lerp(this.expandedShadowRadius, this.collapsedShadowRadius, r7, null);
        this.currentShadowDx = lerp(this.expandedShadowDx, this.collapsedShadowDx, r7, null);
        this.currentShadowDy = lerp(this.expandedShadowDy, this.collapsedShadowDy, r7, null);
        int r03 = blendARGB(getCurrentColor(this.expandedShadowColor), getCurrentColor(this.collapsedShadowColor), r7);
        this.currentShadowColor = r03;
        this.textPaint.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, r03);
        if (this.fadeModeEnabled == false) goto L22;
        this.textPaint.setAlpha((int) (calculateFadeModeTextAlpha(r7) * this.textPaint.getAlpha()));
        if (Build.VERSION.SDK_INT < 31) goto L22;
        TextPaint r72 = this.textPaint;
        r72.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, MaterialColors.compositeARGBWithAlpha(this.currentShadowColor, r72.getAlpha()));
    L22:
        this.view.postInvalidateOnAnimation();
        return;
    L16:
        r32.setColor(getCurrentCollapsedTextColor());
        goto L17
    L11:
        r5.setLetterSpacing(r0);
        goto L12
    L5:
        if (r7 >= this.fadeModeThresholdFraction) goto L7;
        this.currentDrawX = this.expandedDrawX;
        this.currentDrawY = this.expandedDrawY;
        setInterpolatedTextSize(0.0f);
        r02 = 0.0f;
        goto L13
    L7:
        this.currentDrawX = this.collapsedDrawX;
        this.currentDrawY = this.collapsedDrawY - Math.max(0, this.currentOffsetY);
        setInterpolatedTextSize(1.0f);
        r02 = 1.0f;
        goto L13
    }

    private void calculateUsingTextSize(float r2) {
        calculateUsingTextSize(r2, false);
    }

    private void calculateUsingTextSize(float r12, boolean r13) {
        if (this.text == null) goto L96;
        float r0 = this.collapsedBounds.width();
        float r1 = this.expandedBounds.width();
        float r2 = 1.0f;
        if (isClose(r12, 1.0f) == true) goto L8;
        float r3 = this.expandedTextSize;
        float r5 = this.expandedLetterSpacing;
        Typeface r6 = this.expandedTypeface;
        if (isClose(r12, 0.0f) == false) goto L27;
        this.scale = 1.0f;
    L28:
        float r7 = this.collapsedTextSize / this.expandedTextSize;
        float r8 = r1 * r7;
        if (r13 == false) goto L31;
    L38:
        float r132 = r3;
        float r32 = r5;
    L40:
        if (r12 >= FADE_MODE_THRESHOLD_FRACTION_RELATIVE) goto L42;
        int r122 = this.expandedMaxLines;
    L43:
        boolean r52 = false;
        if (r1 <= 0.0f) goto L82;
        if (this.currentTextSize == r132) goto L48;
        boolean r02 = true;
    L50:
        if (this.currentLetterSpacing == r32) goto L52;
        boolean r72 = true;
    L54:
        if (this.currentTypeface == r6) goto L56;
        boolean r82 = true;
    L58:
        if (this.textLayout != null) goto L60;
    L62:
        boolean r9 = false;
    L64:
        if (this.currentMaxLines == r122) goto L66;
        boolean r10 = true;
    L67:
        if (r02 == true) goto L76;
        if (r72 == true) goto L76;
        if (r9 == true) goto L76;
        if (r82 == true) goto L76;
        if (r10 == true) goto L76;
        if (this.boundsChanged == true) goto L76;
        boolean r03 = false;
    L77:
        this.currentTextSize = r132;
        this.currentLetterSpacing = r32;
        this.currentTypeface = r6;
        this.boundsChanged = false;
        this.currentMaxLines = r122;
        TextPaint r133 = this.textPaint;
        if (this.scale == 1.0f) goto L80;
        r52 = true;
    L80:
        r133.setLinearText(r52);
        r52 = r03;
    L76:
        r03 = true;
        goto L77
    L66:
        r10 = false;
        goto L67
    L60:
        if (r1 == r9.getWidth()) goto L62;
        r9 = true;
        goto L64
    L56:
        r82 = false;
        goto L58
    L52:
        r72 = false;
        goto L54
    L48:
        r02 = false;
    L82:
        if (this.textToDraw == null) goto L86;
        if (r52 == true) goto L86;
        return;
    L86:
        this.textPaint.setTextSize(this.currentTextSize);
        this.textPaint.setTypeface(this.currentTypeface);
        this.textPaint.setLetterSpacing(this.currentLetterSpacing);
        this.isRtl = calculateIsRtl(this.text);
        if (shouldDrawMultiline() == false) goto L89;
        int r62 = r122;
    L90:
        TextPaint r73 = this.textPaint;
        CharSequence r83 = this.text;
        if (shouldTruncateCollapsedToSingleLine() == true) goto L94;
        r2 = this.scale;
    L94:
        StaticLayout r11 = createStaticLayout(r62, r73, r83, r1 * r2, this.isRtl);
        this.textLayout = r11;
        this.textToDraw = r11.getText();
        return;
    L89:
        r62 = 1;
        goto L90
    L42:
        r122 = this.collapsedMaxLines;
        goto L43
    L31:
        if (this.fadeModeEnabled == true) goto L38;
        if (r8 <= r0) goto L38;
        if (shouldTruncateCollapsedToSingleLine() == false) goto L38;
        r1 = Math.min(r0 / r7, r1);
        goto L38
    L27:
        this.scale = lerp(this.expandedTextSize, this.collapsedTextSize, r12, this.textSizeInterpolator) / this.expandedTextSize;
        goto L28
    L8:
        if (shouldTruncateCollapsedToSingleLine() == false) goto L10;
        r132 = this.collapsedTextSize;
    L12:
        if (shouldTruncateCollapsedToSingleLine() == false) goto L14;
        r32 = this.collapsedLetterSpacing;
    L16:
        if (shouldTruncateCollapsedToSingleLine() == false) goto L18;
        float r53 = 1.0f;
    L19:
        this.scale = r53;
        if (shouldTruncateCollapsedToSingleLine() == true) goto L23;
        r0 = r1;
    L23:
        r6 = this.collapsedTypeface;
        r1 = r0;
        goto L40
    L18:
        r53 = lerp(this.expandedTextSize, this.collapsedTextSize, r12, this.textSizeInterpolator) / this.expandedTextSize;
        goto L19
    L14:
        r32 = this.expandedLetterSpacing;
        goto L16
    L10:
        r132 = this.expandedTextSize;
        goto L12
    }

    private StaticLayout createStaticLayout(int r2, TextPaint r3, CharSequence r4, float r5, boolean r6) {
        if (r2 != 1) goto L5;
        Layout.Alignment r0 = Layout.Alignment.ALIGN_NORMAL;     // Catch: StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException -> L8
    L6:
        StaticLayout r1 = StaticLayoutBuilderCompat.obtain(r4, r3, (int) r5).setEllipsize(this.titleTextEllipsize).setIsRtl(r6).setAlignment(r0).setIncludePad(false).setMaxLines(r2).setLineSpacing(this.lineSpacingAdd, this.lineSpacingMultiplier).setHyphenationFrequency(this.hyphenationFrequency).setStaticLayoutBuilderConfigurer(this.staticLayoutBuilderConfigurer).build();     // Catch: StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException -> L8
    L11:
        return (StaticLayout) Preconditions.checkNotNull(r1);
    L5:
        r0 = getMultilineTextLayoutAlignment();     // Catch: StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException -> L8
    L8:
        e = move-exception;
        Log.e(TAG, e.getCause().getMessage(), e);
        r1 = null;
        goto L11
    }

    private void drawMultilineTransition(Canvas r12, float r13, float r14) {
        int r0 = this.textPaint.getAlpha();
        r12.translate(r13, r14);
        if (this.fadeModeEnabled == true) goto L9;
        this.textPaint.setAlpha((int) (this.expandedTextBlend * r0));
        if (Build.VERSION.SDK_INT < 31) goto L7;
        TextPaint r1 = this.textPaint;
        r1.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, MaterialColors.compositeARGBWithAlpha(this.currentShadowColor, r1.getAlpha()));
    L7:
        this.textLayout.draw(r12);
    L9:
        if (this.fadeModeEnabled == true) goto L11;
        this.textPaint.setAlpha((int) (this.collapsedTextBlend * r0));
    L11:
        int r15 = Build.VERSION.SDK_INT;
        if (r15 < 31) goto L14;
        TextPaint r4 = this.textPaint;
        r4.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, MaterialColors.compositeARGBWithAlpha(this.currentShadowColor, r4.getAlpha()));
    L14:
        int r42 = this.textLayout.getLineBaseline(0);
        CharSequence r5 = this.textToDrawCollapsed;
        float r8 = r42;
        r12.drawText(r5, 0, r5.length(), 0.0f, r8, this.textPaint);
        if (r15 < 31) goto L18;
        this.textPaint.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, this.currentShadowColor);
    L18:
        if (this.fadeModeEnabled == true) goto L24;
        String r16 = this.textToDrawCollapsed.toString().trim();
        if (r16.endsWith(ELLIPSIS_NORMAL) == false) goto L22;
        r16 = r16.substring(0, r16.length() - 1);
    L22:
        String r43 = r16;
        this.textPaint.setAlpha(r0);
        r12.drawText(r43, 0, Math.min(this.textLayout.getLineEnd(0), r43.length()), 0.0f, r8, this.textPaint);
        return;
    }

    private float getCollapsedTextLeftBound(int r3, int r4) {
        if (r4 != 17) goto L5;
    L24:
        float r32 = r3 / 2.0f;
        float r2 = this.collapsedTextWidth / 2.0f;
    L16:
        return r32 - r2;
    L5:
        if ((r4 & 7) == 1) goto L24;
        if ((r4 & 8388613) != 8388613) goto L10;
    L20:
        boolean r33 = this.isRtl;
        Rect r42 = this.collapsedBounds;
        if (r33 == false) goto L23;
        int r22 = r42.left;
    L19:
        return r22;
    L23:
        r32 = r42.right;
        r2 = this.collapsedTextWidth;
        goto L16
    L10:
        if ((r4 & 5) == 5) goto L20;
        boolean r34 = this.isRtl;
        Rect r43 = this.collapsedBounds;
        if (r34 == false) goto L17;
        r32 = r43.right;
        r2 = this.collapsedTextWidth;
        goto L16
    L17:
        r22 = r43.left;
        goto L19
    }

    private float getCollapsedTextRightBound(RectF r3, int r4, int r5) {
        if (r5 != 17) goto L5;
    L24:
        float r32 = r4 / 2.0f;
        float r2 = this.collapsedTextWidth / 2.0f;
    L19:
        return r32 + r2;
    L5:
        if ((r5 & 7) == 1) goto L24;
        if ((r5 & 8388613) == 8388613) goto L21;
        if ((r5 & 5) == 5) goto L21;
        if (this.isRtl == false) goto L17;
        int r22 = this.collapsedBounds.right;
    L16:
        return r22;
    L17:
        r32 = r3.left;
        r2 = this.collapsedTextWidth;
    L21:
        if (this.isRtl == false) goto L23;
        r32 = r3.left;
        r2 = this.collapsedTextWidth;
        goto L19
    L23:
        r22 = this.collapsedBounds.right;
        goto L16
    }

    private int getCurrentColor(ColorStateList r2) {
        if (r2 != null) goto L5;
        return 0;
    L5:
        int[] r1 = this.state;
        if (r1 == null) goto L10;
        return r2.getColorForState(r1, 0);
    L10:
        return r2.getDefaultColor();
    }

    private int getCurrentExpandedTextColor() {
        return getCurrentColor(this.expandedTextColor);
    }

    private Layout.Alignment getMultilineTextLayoutAlignment() {
        int r0 = Gravity.getAbsoluteGravity(this.expandedTextGravity, this.isRtl ? 1 : 0) & 7;
        if (r0 == 1) goto L17;
        boolean r2 = this.isRtl;
        if (r0 == 5) goto L11;
        if (r2 == false) goto L10;
        return Layout.Alignment.ALIGN_OPPOSITE;
    L10:
        return Layout.Alignment.ALIGN_NORMAL;
    L11:
        if (r2 == false) goto L15;
        return Layout.Alignment.ALIGN_NORMAL;
    L15:
        return Layout.Alignment.ALIGN_OPPOSITE;
    L17:
        return Layout.Alignment.ALIGN_CENTER;
    }

    private void getTextPaintCollapsed(TextPaint r2) {
        r2.setTextSize(this.collapsedTextSize);
        r2.setTypeface(this.collapsedTypeface);
        r2.setLetterSpacing(this.collapsedLetterSpacing);
    }

    private void getTextPaintExpanded(TextPaint r2) {
        r2.setTextSize(this.expandedTextSize);
        r2.setTypeface(this.expandedTypeface);
        r2.setLetterSpacing(this.expandedLetterSpacing);
    }

    private void interpolateBounds(float r5) {
        boolean r0 = this.fadeModeEnabled;
        RectF r1 = this.currentBounds;
        if (r0 == true) goto L5;
        r1.left = lerp(this.expandedBounds.left, this.collapsedBounds.left, r5, this.positionInterpolator);
        this.currentBounds.top = lerp(this.expandedDrawY, this.collapsedDrawY, r5, this.positionInterpolator);
        this.currentBounds.right = lerp(this.expandedBounds.right, this.collapsedBounds.right, r5, this.positionInterpolator);
        this.currentBounds.bottom = lerp(this.expandedBounds.bottom, this.collapsedBounds.bottom, r5, this.positionInterpolator);
        return;
    L5:
        if (r5 >= this.fadeModeThresholdFraction) goto L7;
        Rect r4 = this.expandedBounds;
    L8:
        r1.set(r4);
        return;
    L7:
        r4 = this.collapsedBounds;
        goto L8
    }

    private static boolean isClose(float r0, float r1) {
        if (Math.abs(r0 - r1) >= 1.0E-5f) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isDefaultIsRtl() {
        if (this.view.getLayoutDirection() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private boolean isTextDirectionHeuristicsIsRtl(CharSequence r2, boolean r3) {
        if (r3 == false) goto L4;
        TextDirectionHeuristicCompat r1 = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
    L6:
        return r1.isRtl(r2, 0, r2.length());
    L4:
        r1 = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        goto L6
    }

    private static float lerp(float r0, float r1, float r2, TimeInterpolator r3) {
        if (r3 == null) goto L5;
        r2 = r3.getInterpolation(r2);
    L5:
        return AnimationUtils.lerp(r0, r1, r2);
    }

    private float measureTextWidth(TextPaint r2, CharSequence r3) {
        return r2.measureText(r3, 0, r3.length());
    }

    private static boolean rectEquals(Rect r1, int r2, int r3, int r4, int r5) {
        if (r1.left == r2) goto L5;
        return false;
    L5:
        if (r1.top == r3) goto L7;
        return false;
    L7:
        if (r1.right == r4) goto L9;
        return false;
    L9:
        if (r1.bottom != r5) goto L16;
        return true;
    L16:
        return false;
    }

    private void setCollapsedTextBlend(float r1) {
        this.collapsedTextBlend = r1;
        this.view.postInvalidateOnAnimation();
    }

    private boolean setCollapsedTypefaceInternal(Typeface r2) {
        CancelableFontCallback r0 = this.collapsedFontCallback;
        if (r0 == null) goto L6;
        r0.cancel();
    L6:
        if (this.collapsedTypefaceDefault == r2) goto L12;
        this.collapsedTypefaceDefault = r2;
        Typeface r22 = TypefaceUtils.maybeCopyWithFontWeightAdjustment(this.view.getContext().getResources().getConfiguration(), r2);
        this.collapsedTypefaceBold = r22;
        if (r22 != null) goto L10;
        r22 = this.collapsedTypefaceDefault;
    L10:
        this.collapsedTypeface = r22;
        return true;
    L12:
        return false;
    }

    private void setExpandedTextBlend(float r1) {
        this.expandedTextBlend = r1;
        this.view.postInvalidateOnAnimation();
    }

    private boolean setExpandedTypefaceInternal(Typeface r2) {
        CancelableFontCallback r0 = this.expandedFontCallback;
        if (r0 == null) goto L6;
        r0.cancel();
    L6:
        if (this.expandedTypefaceDefault == r2) goto L12;
        this.expandedTypefaceDefault = r2;
        Typeface r22 = TypefaceUtils.maybeCopyWithFontWeightAdjustment(this.view.getContext().getResources().getConfiguration(), r2);
        this.expandedTypefaceBold = r22;
        if (r22 != null) goto L10;
        r22 = this.expandedTypefaceDefault;
    L10:
        this.expandedTypeface = r22;
        return true;
    L12:
        return false;
    }

    private void setInterpolatedTextSize(float r1) {
        calculateUsingTextSize(r1);
        this.view.postInvalidateOnAnimation();
    }

    private boolean shouldDrawMultiline() {
        if (this.expandedMaxLines > 1) goto L7;
        if (this.collapsedMaxLines > 1) goto L7;
        return false;
    L7:
        if (this.isRtl == true) goto L9;
    L13:
        return true;
    L9:
        if (this.fadeModeEnabled == true) goto L13;
        return false;
    }

    private boolean shouldTruncateCollapsedToSingleLine() {
        if (this.collapsedMaxLines != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public void draw(Canvas r6) {
        int r0 = r6.save();
        if (this.textToDraw != null) goto L5;
        return;
    L5:
        if (this.currentBounds.width() > 0.0f) goto L7;
        return;
    L7:
        if (this.currentBounds.height() <= 0.0f) goto L27;
        this.textPaint.setTextSize(this.currentTextSize);
        float r1 = this.currentDrawX;
        float r2 = this.currentDrawY;
        float r3 = this.scale;
        if (r3 == 1.0f) goto L14;
        if (this.fadeModeEnabled == true) goto L14;
        r6.scale(r3, r3, r1, r2);
    L14:
        if (shouldDrawMultiline() == true) goto L16;
    L22:
        r6.translate(r1, r2);
        this.textLayout.draw(r6);
    L23:
        r6.restoreToCount(r0);
        return;
    L16:
        if (shouldTruncateCollapsedToSingleLine() == false) goto L22;
        if (this.fadeModeEnabled == true) goto L20;
    L21:
        drawMultilineTransition(r6, this.currentDrawX - this.textLayout.getLineStart(0), r2);
        goto L23
    L20:
        if (this.expandedFraction <= this.fadeModeThresholdFraction) goto L22;
    }

    public float getCollapsedFullSingleLineHeight() {
        getTextPaintCollapsed(this.tmpPaint);
        return (-this.tmpPaint.ascent()) + this.tmpPaint.descent();
    }

    public float getCollapsedSingleLineHeight() {
        getTextPaintCollapsed(this.tmpPaint);
        return -this.tmpPaint.ascent();
    }

    public void getCollapsedTextBottomTextBounds(RectF r3, int r4, int r5) {
        this.isRtl = calculateIsRtl(this.text);
        r3.left = Math.max(getCollapsedTextLeftBound(r4, r5), this.collapsedBounds.left);
        r3.top = this.collapsedBounds.top;
        r3.right = Math.min(getCollapsedTextRightBound(r3, r4, r5), this.collapsedBounds.right);
        r3.bottom = this.collapsedBounds.top + getCollapsedTextHeight();
        if (this.textLayout != null) goto L5;
        return;
    L5:
        if (shouldTruncateCollapsedToSingleLine() == true) goto L13;
        float r42 = this.textLayout.getLineWidth(r4.getLineCount() - 1) * (this.collapsedTextSize / this.expandedTextSize);
        if (this.isRtl == false) goto L10;
        r3.left = r3.right - r42;
        return;
    L10:
        r3.right = r3.left + r42;
        return;
    }

    public ColorStateList getCollapsedTextColor() {
        return this.collapsedTextColor;
    }

    public int getCollapsedTextGravity() {
        return this.collapsedTextGravity;
    }

    public float getCollapsedTextHeight() {
        int r0 = this.collapsedHeight;
        if (r0 == (-1)) goto L7;
        return r0;
    L7:
        return getCollapsedSingleLineHeight();
    }

    public float getCollapsedTextSize() {
        return this.collapsedTextSize;
    }

    public Typeface getCollapsedTypeface() {
        Typeface r0 = this.collapsedTypeface;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return Typeface.DEFAULT;
    }

    public int getCurrentCollapsedTextColor() {
        return getCurrentColor(this.collapsedTextColor);
    }

    public int getExpandedLineCount() {
        return this.expandedLineCount;
    }

    public int getExpandedMaxLines() {
        return this.expandedMaxLines;
    }

    public ColorStateList getExpandedTextColor() {
        return this.expandedTextColor;
    }

    public float getExpandedTextFullSingleLineHeight() {
        getTextPaintExpanded(this.tmpPaint);
        return (-this.tmpPaint.ascent()) + this.tmpPaint.descent();
    }

    public int getExpandedTextGravity() {
        return this.expandedTextGravity;
    }

    public float getExpandedTextHeight() {
        int r0 = this.expandedHeight;
        if (r0 == (-1)) goto L7;
        return r0;
    L7:
        return getExpandedTextSingleLineHeight();
    }

    public float getExpandedTextSingleLineHeight() {
        getTextPaintExpanded(this.tmpPaint);
        return -this.tmpPaint.ascent();
    }

    public float getExpandedTextSize() {
        return this.expandedTextSize;
    }

    public Typeface getExpandedTypeface() {
        Typeface r0 = this.expandedTypeface;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return Typeface.DEFAULT;
    }

    public float getExpansionFraction() {
        return this.expandedFraction;
    }

    public float getFadeModeThresholdFraction() {
        return this.fadeModeThresholdFraction;
    }

    public int getHyphenationFrequency() {
        return this.hyphenationFrequency;
    }

    public int getLineCount() {
        StaticLayout r0 = this.textLayout;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getLineCount();
    }

    public float getLineSpacingAdd() {
        return this.textLayout.getSpacingAdd();
    }

    public float getLineSpacingMultiplier() {
        return this.textLayout.getSpacingMultiplier();
    }

    public TimeInterpolator getPositionInterpolator() {
        return this.positionInterpolator;
    }

    public CharSequence getText() {
        return this.text;
    }

    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.titleTextEllipsize;
    }

    public boolean isRtlTextDirectionHeuristicsEnabled() {
        return this.isRtlTextDirectionHeuristicsEnabled;
    }

    public final boolean isStateful() {
        ColorStateList r0 = this.collapsedTextColor;
        if (r0 != null) goto L5;
    L6:
        ColorStateList r1 = this.expandedTextColor;
        if (r1 != null) goto L9;
        return false;
    L9:
        if (r1.isStateful() == false) goto L15;
        return true;
    L15:
        return false;
    L5:
        if (r0.isStateful() == false) goto L6;
        return true;
    }

    public void maybeUpdateFontWeightAdjustment(Configuration r3) {
        if (Build.VERSION.SDK_INT < 31) goto L20;
        Typeface r0 = this.collapsedTypefaceDefault;
        if (r0 == null) goto L7;
        this.collapsedTypefaceBold = TypefaceUtils.maybeCopyWithFontWeightAdjustment(r3, r0);
    L7:
        Typeface r02 = this.expandedTypefaceDefault;
        if (r02 == null) goto L10;
        this.expandedTypefaceBold = TypefaceUtils.maybeCopyWithFontWeightAdjustment(r3, r02);
    L10:
        Typeface r32 = this.collapsedTypefaceBold;
        if (r32 != null) goto L14;
        r32 = this.collapsedTypefaceDefault;
    L14:
        this.collapsedTypeface = r32;
        Typeface r33 = this.expandedTypefaceBold;
        if (r33 != null) goto L18;
        r33 = this.expandedTypefaceDefault;
    L18:
        this.expandedTypeface = r33;
        recalculate(true);
        return;
    }

    public void recalculate() {
        recalculate(false);
    }

    public void recalculate(boolean r2) {
        if (this.view.getHeight() > 0) goto L5;
    L6:
        if (r2 == false) goto L9;
    L7:
        calculateBaseOffsets(r2);
        calculateCurrentOffsets();
        return;
    L9:
        return;
    L5:
        if (this.view.getWidth() > 0) goto L7;
        goto L6
    }

    public void setCollapsedAndExpandedTextColor(ColorStateList r2) {
        if (this.collapsedTextColor == r2) goto L5;
    L8:
        this.collapsedTextColor = r2;
        this.expandedTextColor = r2;
        recalculate();
        return;
    L5:
        if (this.expandedTextColor != r2) goto L8;
    }

    public void setCollapsedBounds(int r2, int r3, int r4, int r5) {
        if (rectEquals(this.collapsedBounds, r2, r3, r4, r5) == true) goto L6;
        this.collapsedBounds.set(r2, r3, r4, r5);
        this.boundsChanged = true;
        return;
    }

    public void setCollapsedBounds(Rect r4) {
        setCollapsedBounds(r4.left, r4.top, r4.right, r4.bottom);
    }

    public void setCollapsedBoundsForOffsets(int r3, int r4, int r5, int r6) {
        if (this.collapsedBoundsForPlacement != null) goto L6;
        this.collapsedBoundsForPlacement = new Rect(r3, r4, r5, r6);
        this.boundsChanged = true;
    L6:
        if (rectEquals(this.collapsedBoundsForPlacement, r3, r4, r5, r6) == true) goto L9;
        this.collapsedBoundsForPlacement.set(r3, r4, r5, r6);
        this.boundsChanged = true;
        return;
    }

    public void setCollapsedMaxLines(int r2) {
        if (r2 == this.collapsedMaxLines) goto L6;
        this.collapsedMaxLines = r2;
        recalculate();
        return;
    }

    public void setCollapsedTextAppearance(int r4) {
        TextAppearance r0 = new TextAppearance(this.view.getContext(), r4);
        if (r0.getTextColor() == null) goto L6;
        this.collapsedTextColor = r0.getTextColor();
    L6:
        if (r0.getTextSize() == 0.0f) goto L8;
        this.collapsedTextSize = r0.getTextSize();
    L8:
        ColorStateList r42 = r0.shadowColor;
        if (r42 == null) goto L11;
        this.collapsedShadowColor = r42;
    L11:
        this.collapsedShadowDx = r0.shadowDx;
        this.collapsedShadowDy = r0.shadowDy;
        this.collapsedShadowRadius = r0.shadowRadius;
        this.collapsedLetterSpacing = r0.letterSpacing;
        CancelableFontCallback r43 = this.collapsedFontCallback;
        if (r43 == null) goto L14;
        r43.cancel();
    L14:
        this.collapsedFontCallback = new CancelableFontCallback(new AnonymousClass1(this), r0.getFallbackFont());
        r0.getFontAsync(this.view.getContext(), this.collapsedFontCallback);
        recalculate();
    }

    public void setCollapsedTextColor(ColorStateList r2) {
        if (this.collapsedTextColor == r2) goto L6;
        this.collapsedTextColor = r2;
        recalculate();
        return;
    }

    public void setCollapsedTextGravity(int r2) {
        if (this.collapsedTextGravity == r2) goto L6;
        this.collapsedTextGravity = r2;
        recalculate();
        return;
    }

    public void setCollapsedTextSize(float r2) {
        if (this.collapsedTextSize == r2) goto L6;
        this.collapsedTextSize = r2;
        recalculate();
        return;
    }

    public void setCollapsedTypeface(Typeface r1) {
        if (setCollapsedTypefaceInternal(r1) == false) goto L6;
        recalculate();
        return;
    }

    public void setCurrentOffsetY(int r1) {
        this.currentOffsetY = r1;
    }

    public void setExpandedBounds(int r7, int r8, int r9, int r10) {
        setExpandedBounds(r7, r8, r9, r10, true);
    }

    public void setExpandedBounds(int r2, int r3, int r4, int r5, boolean r6) {
        if (rectEquals(this.expandedBounds, r2, r3, r4, r5) == true) goto L5;
    L8:
        this.expandedBounds.set(r2, r3, r4, r5);
        this.boundsChanged = true;
        this.alignBaselineAtBottom = r6;
        return;
    L5:
        if (r6 != this.alignBaselineAtBottom) goto L8;
    }

    public void setExpandedBounds(Rect r4) {
        setExpandedBounds(r4.left, r4.top, r4.right, r4.bottom);
    }

    public void setExpandedLetterSpacing(float r2) {
        if (this.expandedLetterSpacing == r2) goto L6;
        this.expandedLetterSpacing = r2;
        recalculate();
        return;
    }

    public void setExpandedMaxLines(int r2) {
        if (r2 == this.expandedMaxLines) goto L6;
        this.expandedMaxLines = r2;
        recalculate();
        return;
    }

    public void setExpandedTextAppearance(int r4) {
        TextAppearance r0 = new TextAppearance(this.view.getContext(), r4);
        if (r0.getTextColor() == null) goto L6;
        this.expandedTextColor = r0.getTextColor();
    L6:
        if (r0.getTextSize() == 0.0f) goto L8;
        this.expandedTextSize = r0.getTextSize();
    L8:
        ColorStateList r42 = r0.shadowColor;
        if (r42 == null) goto L11;
        this.expandedShadowColor = r42;
    L11:
        this.expandedShadowDx = r0.shadowDx;
        this.expandedShadowDy = r0.shadowDy;
        this.expandedShadowRadius = r0.shadowRadius;
        this.expandedLetterSpacing = r0.letterSpacing;
        CancelableFontCallback r43 = this.expandedFontCallback;
        if (r43 == null) goto L14;
        r43.cancel();
    L14:
        this.expandedFontCallback = new CancelableFontCallback(new AnonymousClass2(this), r0.getFallbackFont());
        r0.getFontAsync(this.view.getContext(), this.expandedFontCallback);
        recalculate();
    }

    public void setExpandedTextColor(ColorStateList r2) {
        if (this.expandedTextColor == r2) goto L6;
        this.expandedTextColor = r2;
        recalculate();
        return;
    }

    public void setExpandedTextGravity(int r2) {
        if (this.expandedTextGravity == r2) goto L6;
        this.expandedTextGravity = r2;
        recalculate();
        return;
    }

    public void setExpandedTextSize(float r2) {
        if (this.expandedTextSize == r2) goto L6;
        this.expandedTextSize = r2;
        recalculate();
        return;
    }

    public void setExpandedTypeface(Typeface r1) {
        if (setExpandedTypefaceInternal(r1) == false) goto L6;
        recalculate();
        return;
    }

    public void setExpansionFraction(float r3) {
        float r32 = MathUtils.clamp(r3, 0.0f, 1.0f);
        if (r32 == this.expandedFraction) goto L6;
        this.expandedFraction = r32;
        calculateCurrentOffsets();
        return;
    }

    public void setFadeModeEnabled(boolean r1) {
        this.fadeModeEnabled = r1;
    }

    public void setFadeModeStartFraction(float r1) {
        this.fadeModeStartFraction = r1;
        this.fadeModeThresholdFraction = calculateFadeModeThresholdFraction();
    }

    public void setHyphenationFrequency(int r1) {
        this.hyphenationFrequency = r1;
    }

    public void setLineSpacingAdd(float r1) {
        this.lineSpacingAdd = r1;
    }

    public void setLineSpacingMultiplier(float r1) {
        this.lineSpacingMultiplier = r1;
    }

    public void setPositionInterpolator(TimeInterpolator r1) {
        this.positionInterpolator = r1;
        recalculate();
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean r1) {
        this.isRtlTextDirectionHeuristicsEnabled = r1;
    }

    public final boolean setState(int[] r1) {
        this.state = r1;
        if (isStateful() == false) goto L6;
        recalculate();
        return true;
    L6:
        return false;
    }

    public void setStaticLayoutBuilderConfigurer(StaticLayoutBuilderConfigurer r2) {
        if (this.staticLayoutBuilderConfigurer == r2) goto L6;
        this.staticLayoutBuilderConfigurer = r2;
        recalculate(true);
        return;
    }

    public void setText(CharSequence r2) {
        if (r2 != null) goto L4;
    L7:
        this.text = r2;
        this.textToDraw = null;
        recalculate();
        return;
    L4:
        if (TextUtils.equals(this.text, r2) == false) goto L7;
    }

    public void setTextSizeInterpolator(TimeInterpolator r1) {
        this.textSizeInterpolator = r1;
        recalculate();
    }

    public void setTitleTextEllipsize(TextUtils.TruncateAt r1) {
        this.titleTextEllipsize = r1;
        recalculate();
    }

    public void setTypefaces(Typeface r2) {
        boolean r0 = setCollapsedTypefaceInternal(r2);
        boolean r22 = setExpandedTypefaceInternal(r2);
        if (r0 == true) goto L7;
        if (r22 == true) goto L7;
        return;
    L7:
        recalculate();
    }

    public void updateTextHeights(int r12) {
        getTextPaintCollapsed(this.tmpPaint);
        float r9 = r12;
        this.collapsedHeight = createStaticLayout(this.collapsedMaxLines, this.tmpPaint, this.text, r9 * (this.collapsedTextSize / this.expandedTextSize), this.isRtl).getHeight();
        getTextPaintExpanded(this.tmpPaint);
        this.expandedHeight = createStaticLayout(this.expandedMaxLines, this.tmpPaint, this.text, r9, this.isRtl).getHeight();
    }
}
