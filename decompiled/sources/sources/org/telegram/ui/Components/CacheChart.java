package org.telegram.ui.Components;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.timepicker.TimeModel;
import com.google.zxing.common.detector.MathUtils;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.Premium.StarParticlesView;

/* loaded from: classes7.dex */
public abstract class CacheChart extends View {
    private static final int[] DEFAULT_COLORS = null;
    private static final int[] DEFAULT_PARTICLES = null;
    private static Long loadedStart;
    private static long particlesStart;
    private static Long start;
    private final AnimatedTextView.AnimatedTextDrawable bottomCompleteText;
    private final AnimatedTextView.AnimatedTextDrawable bottomText;
    private RectF chartBounds;
    private RectF chartInnerBounds;
    private RectF chartMeasureBounds;
    private final int[] colorKeys;
    private boolean complete;
    private StarParticlesView.Drawable completeDrawable;
    private AnimatedFloat completeFloat;
    private LinearGradient completeGradient;
    private Matrix completeGradientMatrix;
    private Paint completePaint;
    private Paint completePaintStroke;
    private Path completePath;
    private RectF completePathBounds;
    private LinearGradient completeTextGradient;
    private Matrix completeTextGradientMatrix;
    private boolean interceptTouch;
    private boolean isAttached;
    private boolean loading;
    private Paint loadingBackgroundPaint;
    public AnimatedFloat loadingFloat;
    private final int[] particles;
    private RectF roundingRect;
    private final int sectionsCount;
    private Sector[] sectors;
    private float[] segmentsTmp;
    private int selectedIndex;
    private final boolean svgParticles;
    private float[] tempFloat;
    private int[] tempPercents;
    private final AnimatedTextView.AnimatedTextDrawable topCompleteText;
    private final AnimatedTextView.AnimatedTextDrawable topText;
    private final int type;

    public class Sector {
        float angleCenter;
        AnimatedFloat angleCenterAnimated;
        float angleSize;
        AnimatedFloat angleSizeAnimated;
        Paint cut;
        RadialGradient gradient;
        Matrix gradientMatrix;
        int gradientWidth;
        private float lastAngleCenter;
        private float lastAngleSize;
        private float lastCx;
        private float lastCy;
        private float lastRounding;
        private float lastThickness;
        private float lastWidth;
        Paint paint;
        Bitmap particle;
        Paint particlePaint;
        float particlesAlpha;
        AnimatedFloat particlesAlphaAnimated;
        Path path;
        RectF pathBounds;
        RectF rectF;
        boolean selected;
        AnimatedFloat selectedAnimated;
        AnimatedTextView.AnimatedTextDrawable text;
        float textAlpha;
        AnimatedFloat textAlphaAnimated;
        float textScale;
        AnimatedFloat textScaleAnimated;
        final /* synthetic */ CacheChart this$0;
        Paint uncut;

        public Sector(CacheChart r17) {
            this.this$0 = r17;
            Paint r1 = new Paint(3);
            this.particlePaint = r1;
            int r8 = -1;
            r1.setColor(-1);
            CubicBezierInterpolator r15 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.angleCenterAnimated = new AnimatedFloat(r17, 650, r15);
            this.angleSizeAnimated = new AnimatedFloat(r17, 650, r15);
            CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT;
            this.textAlphaAnimated = new AnimatedFloat(r17, 0, 150, r7);
            this.textScale = 1.0f;
            this.textScaleAnimated = new AnimatedFloat(r17, 0, 150, r7);
            this.text = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.particlesAlphaAnimated = new AnimatedFloat(r17, 0, 150, r7);
            this.selectedAnimated = new AnimatedFloat(r17, 0, 200, r15);
            AnimatedTextView.AnimatedTextDrawable r12 = this.text;
            if (Theme.isCurrentThemeMonet() == false) goto L5;
            r8 = Theme.getColor(Theme.key_windowBackgroundGray);
        L5:
            r12.setTextColor(r8);
            this.text.setAnimationProperties(0.35f, 0, 200, r15);
            this.text.setTypeface(AndroidUtilities.bold());
            this.text.setTextSize(AndroidUtilities.dp(15.0f));
            this.text.setGravity(17);
            this.path = new Path();
            this.paint = new Paint(1);
            this.pathBounds = new RectF();
            this.uncut = new Paint(1);
            Paint r2 = new Paint(1);
            this.cut = r2;
            r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            this.particlePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
            this.rectF = new RectF();
        }

        private void drawParticles(Canvas r28, float r29, float r30, float r31, float r32, float r33, float r34, float r35, float r36, float r37, float r38) {
            if (r38 > 0.0f) goto L5;
            return;
        L5:
            if (LiteMode.isEnabled(LiteMode.FLAGS_CHAT) == false) goto L18;
            long r3 = System.currentTimeMillis();
            float r5 = (float) Math.sqrt(2.0d);
            if (CacheChart.m9102$$Nest$sfgetparticlesStart() >= 0) goto L10;
            CacheChart.m9103$$Nest$sfputparticlesStart(r3);
        L10:
            float r39 = (r3 - CacheChart.m9102$$Nest$sfgetparticlesStart()) / 10000.0f;
            Bitmap r4 = this.particle;
            if (r4 == null) goto L17;
            int r42 = r4.getWidth();
            float r7 = r42;
            float r6 = AndroidUtilities.dpf2(15.0f) / r7;
            float r10 = 7.0f;
            int r9 = (int) Math.floor((r33 % 360.0f) / 7.0f);
            int r8 = (int) Math.ceil((r34 % 360.0f) / 7.0f);
        L13:
            if (r9 > r8) goto L19;
            float r12 = (float) (((100.0f + r39) * (((Math.sin(2000.0f * r11) + 1.0d) * 0.25d) + 1.0d)) % 1.0d);
            float r13 = r7 * r5;
            float r20 = r39;
            double r2 = AndroidUtilities.lerp(r35 - r13, r36 + r13, r12);
            float r22 = (float) (r29 + (Math.cos(CacheChart.m9106$$Nest$smtoRad(r11)) * r2));
            int r23 = r42;
            float r310 = (float) (r30 + (Math.sin(CacheChart.m9106$$Nest$smtoRad(r11)) * r2));
            this.particlePaint.setAlpha((int) (Math.max(0.0f, Math.min(1.0f, (((0.65f * r38) * ((Math.abs(r12 - 0.5f) * (-1.75f)) + 1.0f)) * ((((float) (Math.sin(r12 * 3.141592653589793d) - 1.0d)) * 0.25f) + 1.0f)) * AndroidUtilities.lerp(1.0f, Math.min(MathUtils.distance(r22, r310, r31, r32) / AndroidUtilities.dpf2(64.0f), 1.0f), r37))) * 255.0f));
            float r43 = ((float) ((((((float) (Math.sin(r12) - 1.0d)) * 0.25f) + 1.0f) * 0.75f) * (((Math.sin(r9 * r10) + 1.0d) * 0.25d) + 0.800000011920929d))) * r6;
            r28.save();
            r28.translate(r22, r310);
            r28.scale(r43, r43);
            float r311 = -(r23 >> 1);
            r28.drawBitmap(this.particle, r311, r311, this.particlePaint);
            r28.restore();
            r9 = r9 + 1;
            r5 = r5;
            r39 = r20;
            r42 = r23;
            r10 = 7.0f;
            goto L13
        L19:
            return;
        L17:
            return;
        }

        private void setGradientBounds(float r1, float r2, float r3, float r4) {
            this.gradientMatrix.reset();
            this.gradientMatrix.setTranslate(r1, r2);
            this.gradient.setLocalMatrix(this.gradientMatrix);
        }

        private void setupPath(RectF r28, RectF r29, float r30, float r31, float r32) {
            float r3 = Math.min(Math.min(r32, (r28.width() - r29.width()) / 4.0f), (float) (((r31 / 180.0f) * 3.141592653589793d) * (r29.width() / 2.0f)));
            float r5 = (r28.width() - r29.width()) / 2.0f;
            if (this.lastAngleCenter == r30) goto L5;
        L17:
            this.lastAngleCenter = r30;
            this.lastAngleSize = r31;
            this.lastRounding = r3;
            this.lastThickness = r5;
            this.lastWidth = r28.width();
            this.lastCx = r28.centerX();
            this.lastCy = r28.centerY();
            float r52 = r30 - r31;
            float r1 = r30 + r31;
            int r9 = 1;
            if (r3 <= 0.0f) goto L20;
            boolean r6 = true;
        L21:
            float r13 = r3 * 2.0f;
            float r17 = (r3 / ((float) ((r28.width() - r13) * 3.141592653589793d))) * 360.0f;
            float r7 = (r3 / ((float) ((r29.width() + r13) * 3.141592653589793d))) * 360.0f;
            if (r31 <= 175.0f) goto L24;
            r9 = 0;
        L24:
            float r72 = r7 + (r9 * 0.5f);
            float r2 = (r28.width() / 2.0f) - r3;
            float r92 = (r29.width() / 2.0f) + r3;
            this.path.rewind();
            float r18 = r1 - r52;
            if (r18 >= 0.5f) goto L27;
            return;
        L27:
            if (r6 == false) goto L29;
            float r322 = 180.0f;
            float r19 = r52;
            double r4 = r2;
            float r21 = 2.0f;
            float r312 = r92;
            float r16 = r3;
            CacheChart.m9104$$Nest$smsetCircleBounds(CacheChart.m9101$$Nest$fgetroundingRect(this.this$0), r28.centerX() + (Math.cos(CacheChart.m9106$$Nest$smtoRad(r20)) * r4), r28.centerY() + (r4 * Math.sin(CacheChart.m9106$$Nest$smtoRad(r20))), r16);
            this.path.arcTo(CacheChart.m9101$$Nest$fgetroundingRect(this.this$0), (r19 + r17) - 90.0f, 90.0f);
        L30:
            this.path.arcTo(r28, r19 + r17, r18 - (r17 * r21));
            if (r6 == false) goto L33;
            double r12 = r2;
            CacheChart.m9104$$Nest$smsetCircleBounds(CacheChart.m9101$$Nest$fgetroundingRect(this.this$0), r28.centerX() + (Math.cos(CacheChart.m9106$$Nest$smtoRad(r2)) * r12), r28.centerY() + (r12 * Math.sin(CacheChart.m9106$$Nest$smtoRad(r2))), r16);
            this.path.arcTo(CacheChart.m9101$$Nest$fgetroundingRect(this.this$0), r1 - r17, 90.0f);
            float r93 = r312;
            double r122 = r93;
            CacheChart.m9104$$Nest$smsetCircleBounds(CacheChart.m9101$$Nest$fgetroundingRect(this.this$0), r29.centerX() + (Math.cos(CacheChart.m9106$$Nest$smtoRad(r2)) * r122), r29.centerY() + (r122 * Math.sin(CacheChart.m9106$$Nest$smtoRad(r2))), r16);
            this.path.arcTo(CacheChart.m9101$$Nest$fgetroundingRect(this.this$0), (r1 - r72) + 90.0f, 90.0f);
        L34:
            this.path.arcTo(r29, r1 - r72, -(r18 - (r72 * r21)));
            if (r6 == false) goto L37;
            double r53 = r93;
            CacheChart.m9104$$Nest$smsetCircleBounds(CacheChart.m9101$$Nest$fgetroundingRect(this.this$0), r29.centerX() + (Math.cos(CacheChart.m9106$$Nest$smtoRad(r3)) * r53), r29.centerY() + (r53 * Math.sin(CacheChart.m9106$$Nest$smtoRad(r3))), r16);
            this.path.arcTo(CacheChart.m9101$$Nest$fgetroundingRect(this.this$0), (r19 + r72) + r322, 90.0f);
        L37:
            this.path.close();
            this.path.computeBounds(this.pathBounds, false);
            return;
        L33:
            r93 = r312;
            goto L34
        L29:
            r16 = r3;
            r322 = 180.0f;
            r19 = r52;
            r312 = r92;
            r21 = 2.0f;
            goto L30
        L20:
            r6 = false;
            goto L21
        L5:
            if (this.lastAngleSize != r31) goto L17;
            if (this.lastRounding != r3) goto L17;
            if (this.lastThickness != r5) goto L17;
            if (this.lastWidth != r28.width()) goto L17;
            if (this.lastCx != r28.centerX()) goto L17;
            if (this.lastCy != r28.centerY()) goto L17;
        }

        public void draw(Canvas r21, RectF r22, RectF r23, float r24, float r25, float r26, float r27, float r28) {
            AnimatedFloat r2 = this.selectedAnimated;
            if (this.selected == false) goto L5;
            float r3 = 1.0f;
        L6:
            float r29 = r2.set(r3);
            this.rectF.set(r22);
            this.rectF.inset((-AndroidUtilities.dp(9.0f)) * r29, r29 * (-AndroidUtilities.dp(9.0f)));
            float r4 = (float) (this.rectF.centerX() + ((Math.cos(CacheChart.m9106$$Nest$smtoRad(r24)) * (this.rectF.width() + r23.width())) / 4.0d));
            float r5 = (float) (this.rectF.centerY() + ((Math.sin(CacheChart.m9106$$Nest$smtoRad(r24)) * (this.rectF.width() + r23.width())) / 4.0d));
            float r10 = r28 * (this.textAlphaAnimated.set(this.textAlpha) * r27);
            float r7 = this.particlesAlphaAnimated.set(this.particlesAlpha);
            this.paint.setAlpha((int) (r27 * 255.0f));
            float r210 = r25 * 2.0f;
            RectF r1 = this.rectF;
            if (r210 < 359.0f) goto L9;
            r21.saveLayerAlpha(r1, 255, 31);
            r21.drawCircle(this.rectF.centerX(), this.rectF.centerY(), this.rectF.width() / 2.0f, this.uncut);
            r21.drawRect(this.rectF, this.paint);
            drawParticles(r21, this.rectF.centerX(), this.rectF.centerY(), r4, r5, 0.0f, 359.0f, r23.width() / 2.0f, this.rectF.width() / 2.0f, r10, Math.max(0.0f, (r28 / 0.75f) - 0.75f) * r7);
            r21.drawCircle(r23.centerX(), r23.centerY(), r23.width() / 2.0f, this.cut);
            r21.restore();
            Canvas r12 = r21;
            float r42 = r4;
            float r102 = r10;
        L10:
            float r211 = this.textScaleAnimated.set(this.textScale);
            CacheChart.m9105$$Nest$smsetCircleBounds(CacheChart.m9101$$Nest$fgetroundingRect(this.this$0), r42, r5, 0.0f);
            if (r211 == 1.0f) goto L13;
            r12.save();
            r12.scale(r211, r211, CacheChart.m9101$$Nest$fgetroundingRect(this.this$0).centerX(), CacheChart.m9101$$Nest$fgetroundingRect(this.this$0).centerY());
        L13:
            this.text.setAlpha((int) (r102 * 255.0f));
            this.text.setBounds((int) CacheChart.m9101$$Nest$fgetroundingRect(this.this$0).left, (int) CacheChart.m9101$$Nest$fgetroundingRect(this.this$0).top, (int) CacheChart.m9101$$Nest$fgetroundingRect(this.this$0).right, (int) CacheChart.m9101$$Nest$fgetroundingRect(this.this$0).bottom);
            this.text.draw(r12);
            if (r211 == 1.0f) goto L17;
            r12.restore();
            return;
        L17:
            return;
        L9:
            setupPath(r1, r23, r24, r25, r26);
            setGradientBounds(this.rectF.centerX(), r22.centerY(), this.rectF.width() / 2.0f, r24);
            r21.saveLayerAlpha(this.rectF, 255, 31);
            r21.drawPath(this.path, this.uncut);
            r21.drawRect(this.rectF, this.paint);
            r42 = r4;
            r5 = r5;
            r12 = r21;
            r102 = r10;
            drawParticles(r12, this.rectF.centerX(), this.rectF.centerY(), r42, r5, r24 - r25, r24 + r25, r23.width() / 2.0f, this.rectF.width() / 2.0f, r102, Math.max(0.0f, (r28 / 0.75f) - 0.75f) * r7);
            r12.restore();
            goto L10
        L5:
            r3 = 0.0f;
            goto L6
        }
    }

    public static class SegmentSize {
        int index;
        public boolean selected;
        public long size;

        public SegmentSize() {
        }

        public static SegmentSize of(long r1, boolean r3) {
            SegmentSize r0 = new SegmentSize();
            r0.size = r1;
            r0.selected = r3;
            return r0;
        }
    }

    public static /* synthetic */ int $r8$lambda$F1itvNXkHMowpMO6Vdwm17HfEig(SegmentSize r2, SegmentSize r3) {
        return Long.compare(r2.size, r3.size);
    }

    /* renamed from: -$$Nest$fgetroundingRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m9101$$Nest$fgetroundingRect(CacheChart r0) {
        return r0.roundingRect;
    }

    /* renamed from: -$$Nest$sfgetparticlesStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m9102$$Nest$sfgetparticlesStart() {
        return particlesStart;
    }

    /* renamed from: -$$Nest$sfputparticlesStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9103$$Nest$sfputparticlesStart(long r0) {
        particlesStart = r0;
    }

    /* renamed from: -$$Nest$smsetCircleBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9104$$Nest$smsetCircleBounds(RectF r0, double r1, double r3, float r5) {
        setCircleBounds(r0, r1, r3, r5);
    }

    /* renamed from: -$$Nest$smsetCircleBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9105$$Nest$smsetCircleBounds(RectF r0, float r1, float r2, float r3) {
        setCircleBounds(r0, r1, r2, r3);
    }

    /* renamed from: -$$Nest$smtoRad, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9106$$Nest$smtoRad(float r0) {
        return toRad(r0);
    }

    static {
        int r0 = Theme.key_statisticChartLine_lightblue;
        int r1 = Theme.key_statisticChartLine_blue;
        int r2 = Theme.key_statisticChartLine_green;
        int r3 = Theme.key_statisticChartLine_purple;
        int r4 = Theme.key_statisticChartLine_lightgreen;
        int r5 = Theme.key_statisticChartLine_red;
        int r6 = Theme.key_statisticChartLine_orange;
        int r7 = Theme.key_statisticChartLine_cyan;
        int r9 = Theme.key_statisticChartLine_golden;
        DEFAULT_COLORS = new int[]{r0, r1, r2, r3, r4, r5, r6, r7, r3, r9, r9};
        int r12 = R.raw.cache_photos;
        int r22 = R.raw.cache_videos;
        int r32 = R.raw.cache_documents;
        int r42 = R.raw.cache_music;
        int r72 = R.raw.cache_stickers;
        int r8 = R.raw.cache_profile_photos;
        int r92 = R.raw.cache_other;
        DEFAULT_PARTICLES = new int[]{r12, r22, r32, r42, r22, r42, r72, r8, r92, r92, r32};
        particlesStart = -1;
    }

    public CacheChart(Context r7) {
        this(r7, 11, DEFAULT_COLORS, 0, DEFAULT_PARTICLES);
    }

    public CacheChart(Context r33, int r34, int[] r35, int r36, int[] r37) {
        super(r33);
        this.chartMeasureBounds = new RectF();
        this.chartBounds = new RectF();
        this.chartInnerBounds = new RectF();
        boolean r4 = true;
        this.loading = true;
        CubicBezierInterpolator r12 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.loadingFloat = new AnimatedFloat(this, 750, r12);
        this.complete = false;
        this.completeFloat = new AnimatedFloat(this, 650, r12);
        this.segmentsTmp = new float[2];
        this.roundingRect = new RectF();
        this.loadingBackgroundPaint = new Paint(1);
        this.completePath = new Path();
        this.completePaintStroke = new Paint(1);
        this.completePaint = new Paint(1);
        AnimatedTextView.AnimatedTextDrawable r6 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.topText = r6;
        AnimatedTextView.AnimatedTextDrawable r14 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.bottomText = r14;
        AnimatedTextView.AnimatedTextDrawable r15 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.topCompleteText = r15;
        AnimatedTextView.AnimatedTextDrawable r7 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.bottomCompleteText = r7;
        this.interceptTouch = true;
        this.selectedIndex = -1;
        setLayerType(2, null);
        this.sectionsCount = r34;
        this.colorKeys = r35;
        this.particles = r37;
        this.type = r36;
        if (r36 == 0) goto L6;
        r4 = false;
    L6:
        this.svgParticles = r4;
        this.sectors = new Sector[r34];
        Paint r1 = this.loadingBackgroundPaint;
        Paint.Style r3 = Paint.Style.STROKE;
        r1.setStyle(r3);
        this.loadingBackgroundPaint.setColor(Theme.getColor(Theme.key_listSelector));
        this.completePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Shader.TileMode r31 = Shader.TileMode.CLAMP;
        this.completeGradient = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(200.0f), new int[]{7263574, -9513642, -12469647, 4307569}, new float[]{0.0f, 0.07f, 0.93f, 1.0f}, r31);
        this.completeTextGradient = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(200.0f), new int[]{7263574, -9513642, -12469647, 4307569}, new float[]{0.0f, 0.07f, 0.93f, 1.0f}, r31);
        this.completeGradientMatrix = new Matrix();
        this.completeTextGradientMatrix = new Matrix();
        this.completePaintStroke.setShader(this.completeGradient);
        this.completePaint.setShader(this.completeGradient);
        this.completePaintStroke.setStyle(r3);
        this.completePaintStroke.setStrokeCap(Paint.Cap.ROUND);
        this.completePaintStroke.setStrokeJoin(Paint.Join.ROUND);
        r6.setAnimationProperties(0.2f, 0, 450, r12);
        r6.setScaleProperty(0.6f);
        r6.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r6.setTypeface(AndroidUtilities.bold());
        r6.setTextSize(AndroidUtilities.dp(32.0f));
        r6.setGravity(17);
        r14.setAnimationProperties(0.6f, 0, 450, r12);
        r14.setScaleProperty(0.6f);
        r14.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        r14.setTextSize(AndroidUtilities.dp(12.0f));
        r14.setGravity(17);
        r15.setAnimationProperties(0.2f, 0, 450, r12);
        r15.setScaleProperty(0.6f);
        r15.getPaint().setShader(this.completeTextGradient);
        r15.setTypeface(AndroidUtilities.bold());
        r15.setTextSize(AndroidUtilities.dp(32.0f));
        r15.setGravity(17);
        r7.setAnimationProperties(0.6f, 0, 450, r12);
        r7.setScaleProperty(0.6f);
        r7.getPaint().setShader(this.completeTextGradient);
        r7.setTypeface(AndroidUtilities.bold());
        r7.setTextSize(AndroidUtilities.dp(12.0f));
        r7.setGravity(17);
        int r5 = 0;
    L7:
        Sector[] r13 = this.sectors;
        if (r5 >= r13.length) goto L10;
        Sector r32 = new Sector(this);
        r13[r5] = r32;
        int r16 = Theme.blendOver(Theme.getColor(r35[r5]), ConnectionsManager.FileTypeAudio);
        int r42 = Theme.blendOver(Theme.getColor(r35[r5]), 822083583);
        r32.gradientWidth = AndroidUtilities.dp(50.0f);
        RadialGradient r142 = new RadialGradient(0.0f, 0.0f, AndroidUtilities.dp(86.0f), new int[]{r42, r16}, new float[]{0.3f, 1.0f}, Shader.TileMode.CLAMP);
        r32.gradient = r142;
        Matrix r17 = new Matrix();
        r32.gradientMatrix = r17;
        r142.setLocalMatrix(r17);
        r32.paint.setShader(r32.gradient);
        r5 = r5 + 1;
        goto L7
    }

    private boolean drawAnimatedText(Canvas r2, AnimatedTextView.AnimatedTextDrawable r3, float r4, float r5, float r6, float r7) {
        if (r7 > 0.0f) goto L5;
        return false;
    L5:
        r3.setAlpha((int) (r7 * 255.0f));
        r3.setBounds(0, 0, 0, 0);
        r2.save();
        r2.translate(r4, r5);
        r2.scale(r6, r6);
        r3.draw(r2);
        r2.restore();
        return r3.isAnimating();
    }

    private static void setCircleBounds(RectF r0, double r1, double r3, float r5) {
        setCircleBounds(r0, (float) r1, (float) r3, r5);
    }

    private static void setCircleBounds(RectF r2, float r3, float r4, float r5) {
        r2.set(r3 - r5, r4 - r5, r3 + r5, r4 + r5);
    }

    private static float toRad(float r4) {
        return (float) ((r4 / 180.0f) * 3.141592653589793d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    public void dispatchDraw(Canvas r27) {
        AnimatedFloat r2 = this.loadingFloat;
        float r10 = 1.0f;
        float r11 = 0.0f;
        if (this.loading == false) goto L5;
        float r3 = 1.0f;
    L6:
        float r12 = r2.set(r3);
        AnimatedFloat r22 = this.completeFloat;
        if (this.complete == false) goto L9;
        float r32 = 1.0f;
    L10:
        float r13 = r22.set(r32);
        this.chartBounds.set(this.chartMeasureBounds);
        float r23 = AndroidUtilities.lerp(0.0f, AndroidUtilities.dpf2(padInsideDp()), r13);
        this.chartBounds.inset(r23, r23);
        this.chartInnerBounds.set(this.chartBounds);
        float r15 = AndroidUtilities.lerp(AndroidUtilities.dpf2(38.0f), AndroidUtilities.dpf2(10.0f), Math.max(r12, r13));
        this.chartInnerBounds.inset(r15, r15);
        float r7 = AndroidUtilities.lerp(0, AndroidUtilities.dp(60.0f), r12);
        if (start != null) goto L13;
        start = Long.valueOf(System.currentTimeMillis());
    L13:
        boolean r24 = this.loading;
        if (r24 == false) goto L16;
    L18:
        if (r24 == true) goto L20;
    L22:
        Long r25 = loadedStart;
        if (r25 != null) goto L25;
        long r4 = System.currentTimeMillis();
    L26:
        float r16 = (r4 - start.longValue()) * 0.6f;
        CircularProgressDrawable.getSegments(r16 % 5400.0f, this.segmentsTmp);
        float[] r26 = this.segmentsTmp;
        float r42 = r26[0];
        float r28 = r26[1];
        if (r12 <= 0.0f) goto L29;
        this.loadingBackgroundPaint.setStrokeWidth(r15);
        int r8 = this.loadingBackgroundPaint.getAlpha();
        int r19 = 0;
        this.loadingBackgroundPaint.setAlpha((int) (r8 * r12));
        int r20 = 1;
        float r21 = 10.0f;
        r27.drawCircle(this.chartBounds.centerX(), this.chartBounds.centerY(), (this.chartBounds.width() - r15) / 2.0f, this.loadingBackgroundPaint);
        this.loadingBackgroundPaint.setAlpha(r8);
    L30:
        if (r12 <= 0.0f) goto L32;
    L35:
        int r33 = r20;
    L36:
        int r82 = r33;
        int r14 = r19;
    L37:
        Sector[] r34 = this.sectors;
        if (r14 >= r34.length) goto L63;
        Sector r35 = r34[r14];
        CircularProgressDrawable.getSegments((r16 + (r14 * 80)) % 5400.0f, this.segmentsTmp);
        float r5 = Math.min(Math.max(this.segmentsTmp[r19], r42), r28);
        float r6 = Math.min(Math.max(this.segmentsTmp[r20], r42), r28);
        if (r12 >= r10) goto L42;
    L44:
        float r9 = (r5 + r6) / 2.0f;
        float r52 = Math.abs(r6 - r5) / 2.0f;
        if (r12 > r11) goto L48;
        float r92 = r35.angleCenterAnimated.set(r35.angleCenter);
        float r62 = r35.angleSizeAnimated.set(r35.angleSize);
        float r53 = r92;
        float r222 = r10;
        float r102 = r12;
    L53:
        if (r35.angleCenterAnimated.isInProgress() == false) goto L55;
    L60:
        int r112 = r20;
    L61:
        float r192 = r42;
        int r122 = r20;
        float r202 = r28;
        r35.draw(r27, this.chartBounds, this.chartInnerBounds, r53, r62, r7, r222 - r13, r222 - r102);
        r82 = r112;
    L62:
        r14 = r14 + 1;
        r42 = r192;
        r28 = r202;
        r11 = 0.0f;
        r19 = 0;
        r20 = r122;
        r12 = r102;
        r10 = r222;
        goto L37
    L55:
        if (r35.angleSizeAnimated.isInProgress() == true) goto L60;
        if (r82 != 0) goto L60;
        r112 = r19;
        goto L61
    L48:
        if (r12 >= r10) goto L51;
        r222 = r10;
        r102 = r12;
        r9 = AndroidUtilities.lerp(r35.angleCenterAnimated.set(r35.angleCenter) + (((float) Math.floor(r28 / 360.0f)) * 360.0f), r9, r102);
        r52 = AndroidUtilities.lerp(r35.angleSizeAnimated.set(r35.angleSize), r52, r102);
    L50:
        r62 = r52;
        r53 = r9;
        goto L53
    L51:
        r222 = r10;
        r102 = r12;
        goto L50
    L42:
        if (r5 < r6) goto L44;
        r192 = r42;
        r222 = r10;
        r102 = r12;
        r122 = r20;
        r202 = r28;
        goto L62
    L63:
        float r223 = r10;
        float r103 = r12;
        boolean r123 = r20;
        int r1 = this.type;
        if (r1 != 0) goto L73;
        float r63 = (r223 - r103) * (r223 - r13);
        if (drawAnimatedText(r27, this.topText, this.chartBounds.centerX(), this.chartBounds.centerY() - AndroidUtilities.dpf2(5.0f), 1.0f, r63) == true) goto L71;
        if (r82 != 0) goto L71;
    L71:
        drawAnimatedText(r27, this.bottomText, this.chartBounds.centerX(), this.chartBounds.centerY() + AndroidUtilities.dpf2(22.0f), 1.0f, r63);
    L94:
        if (r13 <= 0.0f) goto L120;
        if (this.completeDrawable != null) goto L98;
        StarParticlesView.Drawable r0 = new StarParticlesView.Drawable(25);
        this.completeDrawable = r0;
        r0.type = 100;
        r0.roundEffect = r123;
        r0.useRotate = r123;
        r0.useBlur = false;
        r0.checkBounds = r123;
        r0.size1 = 18;
        r0.distributionAlgorithm = false;
        r0.excludeRadius = AndroidUtilities.dp(80.0f);
        StarParticlesView.Drawable r02 = this.completeDrawable;
        r02.k3 = 0.85f;
        r02.k2 = 0.85f;
        r02.k1 = 0.85f;
        r02.init();
    L102:
        float r03 = Math.min(getMeasuredHeight(), Math.min(getMeasuredWidth(), AndroidUtilities.dp(150.0f)));
        this.completeDrawable.rect.set(0.0f, 0.0f, r03, r03);
        this.completeDrawable.rect.offset((getMeasuredWidth() - this.completeDrawable.rect.width()) / 2.0f, (getMeasuredHeight() - this.completeDrawable.rect.height()) / 2.0f);
        this.completeDrawable.rect2.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.completeDrawable.resetPositions();
    L103:
        r27.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        this.completeDrawable.onDraw(r27, r13);
        int r64 = (int) (r13 * 255.0f);
        this.completePaint.setAlpha(r64);
        r27.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.completePaint);
        r27.restore();
        this.completePaintStroke.setStrokeWidth(r15);
        this.completePaintStroke.setAlpha(r64);
        r27.drawCircle(this.chartBounds.centerX(), this.chartBounds.centerY(), (this.chartBounds.width() - r15) / 2.0f, this.completePaintStroke);
        RectF r04 = this.completePathBounds;
        if (r04 == null) goto L108;
        if (r04.equals(this.chartMeasureBounds) == false) goto L108;
    L117:
        if (this.type != 0) goto L120;
        this.completePaintStroke.setStrokeWidth(AndroidUtilities.dpf2(r21));
        r27.drawPath(this.completePath, this.completePaintStroke);
    L108:
        if (this.completePathBounds != null) goto L110;
        this.completePathBounds = new RectF();
    L110:
        this.completePathBounds.set(this.chartMeasureBounds);
        this.completePath.rewind();
        int r05 = this.type;
        if (r05 != 0) goto L113;
        this.completePath.moveTo(this.chartBounds.width() * 0.348f, this.chartBounds.height() * 0.538f);
        this.completePath.lineTo(this.chartBounds.width() * 0.447f, this.chartBounds.height() * 0.636f);
        this.completePath.lineTo(this.chartBounds.width() * 0.678f, this.chartBounds.height() * 0.402f);
    L115:
        Path r06 = this.completePath;
        RectF r29 = this.chartBounds;
        r06.offset(r29.left, r29.top);
        goto L117
    L113:
        if (r05 != r123) goto L115;
        this.completePath.moveTo(this.chartBounds.width() * 0.2929f, this.chartBounds.height() * 0.4369f);
        this.completePath.lineTo(this.chartBounds.width() * 0.381f, this.chartBounds.height() * 0.35f);
        this.completePath.lineTo(this.chartBounds.width() * 0.4691f, this.chartBounds.height() * 0.4369f);
        this.completePath.moveTo(this.chartBounds.width() * 0.381f, this.chartBounds.height() * 0.35f);
        this.completePath.lineTo(this.chartBounds.width() * 0.381f, this.chartBounds.height() * 0.6548f);
        this.completePath.moveTo(this.chartBounds.width() * 0.5214f, this.chartBounds.height() * 0.5821f);
        this.completePath.lineTo(this.chartBounds.width() * 0.6095f, this.chartBounds.height() * 0.669f);
        this.completePath.lineTo(this.chartBounds.width() * 0.6976f, this.chartBounds.height() * 0.5821f);
        this.completePath.moveTo(this.chartBounds.width() * 0.6095f, this.chartBounds.height() * 0.669f);
        this.completePath.lineTo(this.chartBounds.width() * 0.6095f, this.chartBounds.height() * 0.3643f);
        goto L115
    L98:
        RectF r07 = this.completePathBounds;
        if (r07 == null) goto L102;
        if (r07.equals(this.chartMeasureBounds) == true) goto L103;
    L120:
        if (this.isAttached == false) goto L126;
        invalidate();
        return;
    L126:
        return;
    L73:
        if (r1 != r123) goto L94;
        float r104 = r223 - r103;
        float r36 = this.chartBounds.centerX() - AndroidUtilities.lerp(0.0f, AndroidUtilities.dpf2(4.0f), r13);
        float r43 = this.chartBounds.centerY() - AndroidUtilities.lerp(AndroidUtilities.dpf2(5.0f), 0.0f, r13);
        float r54 = AndroidUtilities.lerp(r223, 2.25f, r13);
        float r65 = r104 * r13;
        if (drawAnimatedText(r27, this.topCompleteText, r36, r43, r54, r65) == true) goto L79;
        if (r82 != 0) goto L79;
        boolean r83 = false;
    L80:
        float r66 = r104 * (r223 - r13);
        if (drawAnimatedText(r27, this.topText, r36, r43, r54, r66) == true) goto L85;
        if (r83 == true) goto L85;
        boolean r84 = false;
    L86:
        float r37 = this.chartBounds.centerX() + AndroidUtilities.lerp(0.0f, AndroidUtilities.dpf2(26.0f), r13);
        float r44 = this.chartBounds.centerY() + AndroidUtilities.lerp(AndroidUtilities.dpf2(22.0f), -AndroidUtilities.dpf2(18.0f), r13);
        float r55 = AndroidUtilities.lerp(1.0f, 1.4f, r13);
        if (drawAnimatedText(r27, this.bottomCompleteText, r37, r44, r55, r65) == true) goto L92;
        if (r84 == true) goto L92;
    L92:
        drawAnimatedText(r27, this.bottomText, r37, r44, r55, r66);
    L85:
        r84 = r123 ? 1 : 0;
    L79:
        r83 = r123 ? 1 : 0;
        goto L80
    L32:
        if (r13 > 0.0f) goto L35;
        r33 = r19;
        goto L36
    L29:
        r19 = 0;
        r20 = 1;
        r21 = 10.0f;
        goto L30
    L25:
        r4 = r25.longValue();
        goto L26
    L20:
        if (loadedStart == null) goto L22;
        loadedStart = null;
        goto L22
    L16:
        if (loadedStart != null) goto L18;
        loadedStart = Long.valueOf(System.currentTimeMillis());
        goto L22
    L9:
        r32 = 0.0f;
        goto L10
    L5:
        r3 = 0.0f;
        goto L6
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent r8) {
        float r2 = MathUtils.distance(this.chartBounds.centerX(), this.chartBounds.centerY(), r8.getX(), r8.getY());
        float r0 = (float) ((Math.atan2(r1 - this.chartBounds.centerY(), r0 - this.chartBounds.centerX()) / 3.141592653589793d) * 180.0d);
        if (r0 >= 0.0f) goto L5;
        r0 = r0 + 360.0f;
    L5:
        boolean r4 = false;
        if (r2 > (this.chartInnerBounds.width() / 2.0f)) goto L8;
    L18:
        int r1 = -1;
    L20:
        if (r8.getAction() != 0) goto L32;
        setSelected(r1);
        if (r1 < 0) goto L30;
        if (r1 == (-1)) goto L25;
        r4 = true;
    L25:
        onSectionDown(r1, r4);
        if (getParent() == null) goto L30;
        if (this.interceptTouch == false) goto L30;
        getParent().requestDisallowInterceptTouchEvent(true);
    L30:
        return true;
    L32:
        if (r8.getAction() != 2) goto L39;
        if (r1 == (-1)) goto L35;
        r4 = true;
    L35:
        onSectionDown(r1, r4);
        setSelected(r1);
        if (r1 == (-1)) goto L50;
        return true;
    L50:
        return super.dispatchTouchEvent(r8);
    L39:
        if (r8.getAction() != 1) goto L47;
        if (r1 == (-1)) goto L42;
        onSectionClick(r1);
        boolean r02 = true;
    L43:
        setSelected(-1);
        onSectionDown(r1, false);
        if (r02 == false) goto L50;
        return true;
    L42:
        r02 = false;
        goto L43
    L47:
        if (r8.getAction() != 3) goto L50;
        setSelected(-1);
        onSectionDown(r1, false);
        goto L50
    L8:
        if (r2 >= ((this.chartBounds.width() / 2.0f) + AndroidUtilities.dp(14.0f))) goto L18;
        r1 = 0;
    L10:
        Sector[] r22 = this.sectors;
        if (r1 >= r22.length) goto L18;
        Sector r23 = r22[r1];
        float r3 = r23.angleCenter;
        float r24 = r23.angleSize;
        if (r0 < (r3 - r24)) goto L17;
        if (r0 <= (r3 + r24)) goto L20;
    L17:
        r1 = r1 + 1;
        goto L10
    }

    public int heightDp() {
        return 200;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttached = true;
        int r0 = 0;
    L3:
        Sector[] r1 = this.sectors;
        if (r0 >= r1.length) goto L12;
        Sector r12 = r1[r0];
        if (r12.particle != null) goto L11;
        if (this.svgParticles == false) goto L10;
        r12.particle = SvgHelper.getBitmap(this.particles[r0], AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), -1);
        goto L11
    L10:
        r12.particle = BitmapFactory.decodeResource(getContext().getResources(), this.particles[r0]);
    L11:
        r0 = r0 + 1;
        goto L3
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        requestLayout();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int r0 = 0;
        this.isAttached = false;
    L3:
        Sector[] r1 = this.sectors;
        if (r0 >= r1.length) goto L9;
        Bitmap r12 = r1[r0].particle;
        if (r12 == null) goto L8;
        r12.recycle();
        this.sectors[r0].particle = null;
    L8:
        r0 = r0 + 1;
        goto L3
    }

    @Override // android.view.View
    public void onMeasure(int r7, int r8) {
        int r72 = View.MeasureSpec.getSize(r7);
        int r82 = AndroidUtilities.dp(heightDp());
        int r0 = AndroidUtilities.dp(172.0f);
        this.chartMeasureBounds.set((r72 - r0) / 2.0f, (r82 - r0) / 2.0f, (r72 + r0) / 2.0f, (r0 + r82) / 2.0f);
        this.completeGradientMatrix.reset();
        this.completeGradientMatrix.setTranslate(this.chartMeasureBounds.left, 0.0f);
        this.completeGradient.setLocalMatrix(this.completeGradientMatrix);
        this.completeTextGradientMatrix.reset();
        Matrix r02 = this.completeTextGradientMatrix;
        RectF r1 = this.chartMeasureBounds;
        r02.setTranslate(r1.left, -r1.centerY());
        this.completeTextGradient.setLocalMatrix(this.completeTextGradientMatrix);
        StarParticlesView.Drawable r03 = this.completeDrawable;
        if (r03 == null) goto L5;
        r03.rect.set(0.0f, 0.0f, AndroidUtilities.dp(140.0f), AndroidUtilities.dp(140.0f));
        this.completeDrawable.rect.offset((getMeasuredWidth() - this.completeDrawable.rect.width()) / 2.0f, (getMeasuredHeight() - this.completeDrawable.rect.height()) / 2.0f);
        this.completeDrawable.rect2.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.completeDrawable.resetPositions();
    L5:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30));
    }

    public void onSectionClick(int r1) {
    }

    public abstract void onSectionDown(int r1, boolean r2);

    public int padInsideDp() {
        return 0;
    }

    public void setInterceptTouch(boolean r1) {
        this.interceptTouch = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setSegments(long r25, boolean r27, SegmentSize... r28) {
        SegmentSize[] r2 = r28;
        long r5 = 0;
        float r8 = 0.0f;
        int r9 = 0;
        boolean r10 = true;
        if (r2 == null) goto L166;
        if (r2.length == 0) goto L166;
        this.loading = false;
        if (r27 == true) goto L10;
        this.loadingFloat.set(0.0f, true);
    L10:
        SpannableString r11 = new SpannableString("%");
        int r12 = r2.length;
        long r14 = 0;
        int r13 = 0;
        float r16 = 1.0f;
    L12:
        if (r13 >= r2.length) goto L28;
        if (r2[r13] != null) goto L16;
        SegmentSize r7 = new SegmentSize();
        r2[r13] = r7;
        r7.size = r5;
    L16:
        SegmentSize r72 = r2[r13];
        r72.index = r13;
        long r17 = r5;
        if (r72 != null) goto L19;
    L21:
        if (r72 != null) goto L23;
    L26:
        r12 = r12 - 1;
    L27:
        r13 = r13 + 1;
        r5 = r17;
        goto L12
    L23:
        if (r72.size <= r17) goto L26;
        if (r72.selected == true) goto L27;
    L19:
        if (r72.selected == false) goto L21;
        r14 = r14 + r72.size;
        goto L21
    L28:
        long r172 = r5;
        if (r14 > r172) goto L50;
        this.loading = false;
        if (r25 > r172) goto L33;
        boolean r22 = true;
    L34:
        this.complete = r22;
        if (r27 == true) goto L41;
        this.loadingFloat.set(0.0f, true);
        AnimatedFloat r23 = this.completeFloat;
        if (this.complete == false) goto L39;
        float r73 = 1.0f;
    L40:
        r23.set(r73, true);
        goto L41
    L39:
        r73 = 0.0f;
    L41:
        this.topCompleteText.setText(this.topText.getText(), false);
        this.topText.setText(MVEL.VERSION_SUB, r27);
        this.topCompleteText.setText(MVEL.VERSION_SUB, r27);
        this.bottomCompleteText.setText(this.bottomText.getText(), false);
        this.bottomText.setText("KB", r27);
        this.bottomCompleteText.setText("KB", r27);
    L42:
        Sector[] r24 = this.sectors;
        if (r9 >= r24.length) goto L48;
        Sector r26 = r24[r9];
        r26.textAlpha = 0.0f;
        if (r27 == true) goto L47;
        r26.textAlphaAnimated.set(0.0f, true);
    L47:
        r9 = r9 + 1;
        goto L42
    L48:
        invalidate();
        return;
    L33:
        r22 = false;
        goto L34
    L50:
        float r52 = 0.0f;
        int r3 = 0;
        int r4 = 0;
    L52:
        if (r3 >= r2.length) goto L66;
        SegmentSize r6 = r2[r3];
        if (r6 != null) goto L56;
    L57:
        float r132 = r8;
        float r252 = 0.02f;
        float r62 = r132;
    L61:
        if (r62 <= r132) goto L65;
        if (r62 >= r252) goto L65;
        r4 = r4 + 1;
        r52 = r52 + r62;
    L65:
        r3 = r3 + 1;
        r8 = r132;
        goto L52
    L56:
        if (r6.selected == false) goto L57;
        r132 = r8;
        r252 = 0.02f;
        r62 = r6.size / r14;
        goto L61
    L66:
        float r133 = r8;
        Math.min(r2.length, this.sectors.length);
        int[] r32 = this.tempPercents;
        if (r32 != null) goto L69;
    L70:
        this.tempPercents = new int[r2.length];
    L71:
        float[] r33 = this.tempFloat;
        if (r33 != null) goto L74;
    L75:
        this.tempFloat = new float[r2.length];
    L76:
        int r34 = 0;
    L77:
        int r63 = r2.length;
        float[] r74 = this.tempFloat;
        if (r34 >= r63) goto L87;
        SegmentSize r64 = r2[r34];
        if (r64 != null) goto L82;
    L83:
        int r19 = r9;
        boolean r82 = r10;
        float r65 = r133;
    L86:
        r74[r34] = r65;
        r34 = r34 + 1;
        r10 = r82;
        r9 = r19;
        goto L77
    L82:
        if (r64.selected == false) goto L83;
        r19 = r9;
        r82 = r10;
        r65 = r64.size / r14;
        goto L86
    L87:
        boolean r192 = r9;
        boolean r83 = r10;
        AndroidUtilities.roundPercents(r74, this.tempPercents);
        if (this.type != 0) goto L97;
        Arrays.sort(r2, new CacheChart$$ExternalSyntheticLambda0());
        int r35 = r192 ? 1 : 0;
    L91:
        if (r35 > r2.length) goto L97;
        SegmentSize r66 = r2[r35];
        if (r66.index == (r2.length - (r83 ? 1 : 0))) goto L94;
        r35 = r35 + 1;
        goto L91
    L94:
        SegmentSize r75 = r2[r192 ? 1 : 0];
        r2[r192 ? 1 : 0] = r66;
        r2[r35] = r75;
    L97:
        if (r12 >= 2) goto L99;
        r12 = r192 ? 1 : 0;
    L99:
        float r67 = 2.0f;
        float r76 = 360.0f - (r12 * 2.0f);
        float r36 = r133;
        int r92 = r192 ? 1 : 0;
        int r102 = r92;
    L101:
        if (r92 >= r2.length) goto L146;
        SegmentSize r122 = r2[r92];
        float r262 = r67;
        int r68 = r122.index;
        if (r122 != null) goto L105;
    L106:
        float r18 = r36;
        float r29 = r133;
    L109:
        SpannableStringBuilder r37 = new SpannableStringBuilder();
        r37.append(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.tempPercents[r68])}));
        r37.append(r11);
        Sector r84 = this.sectors[r68];
        float r123 = r133;
        long r20 = r14;
        if (r29 > 0.05d) goto L112;
    L114:
        float r134 = r123;
    L115:
        r84.textAlpha = r134;
        if (r29 >= 0.08f) goto L118;
    L121:
        float r142 = 0.85f;
    L122:
        r84.textScale = r142;
        r84.particlesAlpha = r16;
        if (r27 == true) goto L125;
        r84.textAlphaAnimated.set(r134, true);
        Sector r85 = this.sectors[r68];
        r85.textScaleAnimated.set(r85.textScale, true);
        Sector r86 = this.sectors[r68];
        r86.particlesAlphaAnimated.set(r86.particlesAlpha, true);
    L125:
        Sector r135 = this.sectors[r68];
        if (r135.textAlpha <= r123) goto L129;
        r135.text.setText(r37, r27);
    L129:
        if (r29 < 0.02f) goto L131;
    L133:
        r16 = 1.0f;
        float r210 = r29 * (1.0f - ((r4 * 0.02f) - r52));
    L134:
        float r38 = (r18 * r76) + (r102 * r262);
        float r143 = r38 + (r210 * r76);
        Sector[] r15 = this.sectors;
        if (r210 > r123) goto L140;
        Sector r211 = r15[r68];
        r211.angleCenter = (r38 + r143) / r262;
        r211.angleSize = Math.abs(r143 - r38) / r262;
        Sector r212 = this.sectors[r68];
        r212.textAlpha = r123;
        if (r27 == true) goto L139;
        r212.angleCenterAnimated.set(r212.angleCenter, true);
        Sector r213 = this.sectors[r68];
        r213.angleSizeAnimated.set(r213.angleSize, true);
        Sector r214 = this.sectors[r68];
        r214.textAlphaAnimated.set(r214.textAlpha, true);
    L139:
        r36 = r18;
        r83 = true;
    L145:
        r92 = r92 + 1;
        r67 = r262;
        r2 = r28;
        r14 = r20;
        r133 = 0.0f;
        goto L101
    L140:
        Sector r124 = r15[r68];
        r124.angleCenter = (r38 + r143) / r262;
        r124.angleSize = Math.abs(r143 - r38) / r262;
        if (r27 == true) goto L143;
        Sector r39 = this.sectors[r68];
        r83 = true;
        r39.angleCenterAnimated.set(r39.angleCenter, true);
        Sector r310 = this.sectors[r68];
        r310.angleSizeAnimated.set(r310.angleSize, true);
    L144:
        r36 = r18 + r210;
        r102 = r102 + 1;
        goto L145
    L143:
        r83 = true;
        goto L144
    L131:
        if (r29 <= r123) goto L133;
        r210 = 0.02f;
        r16 = 1.0f;
        goto L134
    L118:
        if (this.tempPercents[r68] >= 100) goto L121;
        r142 = r16;
        goto L122
    L112:
        if (r29 >= r16) goto L114;
        r134 = r16;
        goto L115
    L105:
        if (r122.selected == false) goto L106;
        r18 = r36;
        r29 = r122.size / r14;
        goto L109
    L146:
        String[] r215 = AndroidUtilities.formatFileSize(r14, r83, r83).split(" ");
        int r311 = r215.length;
        String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r311 <= 0) goto L149;
        String r312 = r215[r192 ? 1 : 0];
    L151:
        if (r312.length() >= 4) goto L153;
    L155:
        this.topText.setText(r312, r27);
        AnimatedTextView.AnimatedTextDrawable r313 = this.bottomText;
        if (r215.length <= 1) goto L158;
        r42 = r215[1];
    L158:
        r313.setText(r42, r27);
        if (this.completeFloat.get() <= 0.0f) goto L161;
        this.topCompleteText.setText(this.topText.getText(), r27);
        this.bottomCompleteText.setText(this.bottomText.getText(), r27);
    L161:
        this.complete = r192;
        if (r27 == true) goto L164;
        this.completeFloat.set(0.0f, true);
    L164:
        invalidate();
        return;
    L153:
        if (r14 >= 1073741824) goto L155;
        r312 = r312.split("\\.")[r192 ? 1 : 0];
        goto L155
    L149:
        r312 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L151
    L74:
        if (r33.length == r2.length) goto L76;
    L69:
        if (r32.length == r2.length) goto L71;
    L166:
        this.loading = false;
        if (r25 != 0) goto L169;
        boolean r216 = true;
    L170:
        this.complete = r216;
        if (r27 == true) goto L177;
        this.loadingFloat.set(0.0f, true);
        AnimatedFloat r217 = this.completeFloat;
        if (this.complete == false) goto L175;
        float r77 = 1.0f;
    L176:
        r217.set(r77, true);
        goto L177
    L175:
        r77 = 0.0f;
    L177:
        this.topCompleteText.setText(this.topText.getText(), false);
        this.topText.setText(MVEL.VERSION_SUB, r27);
        this.topCompleteText.setText(MVEL.VERSION_SUB, r27);
        this.bottomCompleteText.setText(this.bottomText.getText(), false);
        this.bottomText.setText("KB", r27);
        this.bottomCompleteText.setText("KB", r27);
        int r93 = 0;
    L178:
        Sector[] r218 = this.sectors;
        if (r93 >= r218.length) goto L185;
        Sector r219 = r218[r93];
        r219.textAlpha = 0.0f;
        if (r27 == true) goto L184;
        r219.textAlphaAnimated.set(0.0f, true);
    L184:
        r93 = r93 + 1;
        goto L178
    L185:
        invalidate();
        return;
    L169:
        r216 = false;
        goto L170
    }

    public void setSelected(int r6) {
        if (r6 != this.selectedIndex) goto L5;
        return;
    L5:
        int r1 = 0;
    L6:
        Sector[] r2 = this.sectors;
        if (r1 >= r2.length) goto L17;
        if (r6 == r1) goto L10;
    L12:
        Sector r22 = r2[r1];
        if (r6 != r1) goto L15;
        boolean r3 = true;
    L16:
        r22.selected = r3;
        r1 = r1 + 1;
        goto L6
    L15:
        r3 = false;
        goto L16
    L10:
        if (r2[r1].angleSize > 0.0f) goto L12;
        r6 = -1;
        goto L12
    L17:
        this.selectedIndex = r6;
        invalidate();
    }
}
