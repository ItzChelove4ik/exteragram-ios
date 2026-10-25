package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.android.dx.io.Opcodes;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.GenericProvider;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.wallpaper.WallpaperGiftPatternPosition;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.PatternCell$$ExternalSyntheticApiModelOutline0;
import org.telegram.ui.Components.blur3.utils.BitmapChangeTracker;

/* loaded from: classes3.dex */
public class MotionBackgroundDrawable extends Drawable {
    private static final boolean useLegacyBitmap = false;
    private static final boolean useSoftLight = false;
    private int alpha;
    private GenericProvider<MotionBackgroundDrawable, Float> animationProgressProvider;
    private float backgroundAlpha;
    private int bitmapHeight;
    private BitmapShader bitmapShader;
    private int bitmapWidth;
    private final int[] colors;
    private Bitmap currentBitmap;
    private boolean disableGradientShaderScaling;
    private boolean fastAnimation;
    private final BitmapChangeTracker giftChangeTracker;
    private ImageReceiver giftImageReceiver;
    private List<WallpaperGiftPatternPosition> giftPatternPositions;
    private int giftPosition;
    private Canvas gradientCanvas;
    private GradientDrawable gradientDrawable;
    private Bitmap gradientFromBitmap;
    private Canvas gradientFromCanvas;
    private BitmapShader gradientShader;
    private final Bitmap[] gradientToBitmap;
    private boolean ignoreInterpolator;
    private float indeterminateSpeedScale;
    private int intensity;
    private final CubicBezierInterpolator interpolator;
    public boolean isAttached;
    private boolean isIndeterminateAnimation;
    public boolean isPreview;
    private long lastUpdateTime;
    private Matrix matrix;
    private MotionBackgroundPaint motionBackgroundPaint;
    private final Paint paint;
    private final Paint paint2;
    private final Paint paint3;
    private WeakReference<View> parentView;
    private float patternAlpha;
    private Bitmap patternAlphaInverted;
    private Bitmap patternBitmap;
    private final BitmapChangeTracker patternChangeTracker;
    private int patternColor;
    private ColorFilter patternColorFilter;
    private Bitmap patternGiftBitmap;
    private int patternInvertedLastAlpha;
    private int patternInvertedLastPosition;
    private Bitmap patternWithGiftBitmap;
    private Canvas patternWithGiftCanvas;
    private final BitmapChangeTracker patternWithGiftChangeTracker;
    private Paint patternWithGiftPaint;
    private int phase;
    public float posAnimationProgress;
    private boolean postInvalidateParent;
    private final RectF rect;
    private boolean rotatingPreview;
    private boolean rotationBack;
    private int roundRadius;
    private int translationY;
    private final Runnable updateAnimationRunnable;

    static {
        int r0 = Build.VERSION.SDK_INT;
        boolean r2 = false;
        if (r0 >= 28) goto L5;
        boolean r1 = true;
    L6:
        useLegacyBitmap = r1;
        if (r0 < 29) goto L9;
        r2 = true;
    L9:
        useSoftLight = r2;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public MotionBackgroundDrawable() {
        this.colors = new int[]{-12423849, -531317, -7888252, -133430};
        this.interpolator = new CubicBezierInterpolator(0.33d, 0.0d, 0.0d, 1.0d);
        this.posAnimationProgress = 1.0f;
        this.rect = new RectF();
        this.gradientToBitmap = new Bitmap[3];
        this.paint = new Paint(2);
        this.paint2 = new Paint(2);
        this.paint3 = new Paint();
        this.intensity = 100;
        this.gradientDrawable = new GradientDrawable();
        this.updateAnimationRunnable = new MotionBackgroundDrawable$$ExternalSyntheticLambda0(this);
        this.patternAlpha = 1.0f;
        this.backgroundAlpha = 1.0f;
        this.alpha = 255;
        this.indeterminateSpeedScale = 1.0f;
        this.bitmapWidth = 60;
        this.bitmapHeight = 80;
        this.giftPosition = -1;
        this.patternColor = -16777216;
        this.patternChangeTracker = new BitmapChangeTracker();
        this.giftChangeTracker = new BitmapChangeTracker();
        this.patternWithGiftChangeTracker = new BitmapChangeTracker();
        init();
    }

    public MotionBackgroundDrawable(int r9, int r10, int r11, int r12, int r13, boolean r14) {
        this(r9, r10, r11, r12, r13, r14, false);
    }

    public MotionBackgroundDrawable(int r11, int r12, int r13, int r14, int r15, boolean r16, boolean r17) {
        this.colors = new int[]{-12423849, -531317, -7888252, -133430};
        this.interpolator = new CubicBezierInterpolator(0.33d, 0.0d, 0.0d, 1.0d);
        this.posAnimationProgress = 1.0f;
        this.rect = new RectF();
        this.gradientToBitmap = new Bitmap[3];
        this.paint = new Paint(2);
        this.paint2 = new Paint(2);
        this.paint3 = new Paint();
        this.intensity = 100;
        this.gradientDrawable = new GradientDrawable();
        this.updateAnimationRunnable = new MotionBackgroundDrawable$$ExternalSyntheticLambda0(this);
        this.patternAlpha = 1.0f;
        this.backgroundAlpha = 1.0f;
        this.alpha = 255;
        this.indeterminateSpeedScale = 1.0f;
        this.bitmapWidth = 60;
        this.bitmapHeight = 80;
        this.giftPosition = -1;
        this.patternColor = -16777216;
        this.patternChangeTracker = new BitmapChangeTracker();
        this.giftChangeTracker = new BitmapChangeTracker();
        this.patternWithGiftChangeTracker = new BitmapChangeTracker();
        if (r17 == false) goto L5;
        this.bitmapWidth = 80;
        this.bitmapHeight = 80;
    L5:
        this.isPreview = r16;
        setColors(r11, r12, r13, r14, r15, false);
        init();
    }

    public MotionBackgroundDrawable(int r8, int r9, int r10, int r11, boolean r12) {
        this(r8, r9, r10, r11, 0, r12);
    }

    private void checkLegacyForNegativeIntensity(int r5) {
        if (this.patternBitmap != null) goto L5;
        return;
    L5:
        Bitmap r0 = getOrBuildPatternWithGiftBitmap();
        if (this.patternWithGiftChangeTracker.isInvalidated(r0) == false) goto L8;
    L11:
        int r1 = this.patternBitmap.getWidth();
        int r2 = this.patternBitmap.getHeight();
        this.patternInvertedLastAlpha = r5;
        Bitmap r3 = this.patternAlphaInverted;
        if (r3 != null) goto L14;
    L17:
        this.patternAlphaInverted = Bitmap.createBitmap(r1, r2, Bitmap.Config.ARGB_8888);
    L18:
        Utilities.applyAlphaInvert(r0, this.patternAlphaInverted, r5);
    L19:
        this.patternWithGiftChangeTracker.set(r0);
        return;
    L14:
        if (r3.getWidth() != r1) goto L17;
        if (this.patternAlphaInverted.getHeight() == r2) goto L18;
    L8:
        if (this.patternAlphaInverted == null) goto L11;
        if (this.patternInvertedLastAlpha == r5) goto L19;
        goto L11
    }

    private void drawGiftImage(Canvas r3, int r4, float r5, float r6, float r7, float r8) {
        List<WallpaperGiftPatternPosition> r0 = this.giftPatternPositions;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.giftImageReceiver == null) goto L12;
        if (r4 >= 0) goto L8;
        return;
    L8:
        if (r4 >= r0.size()) goto L14;
        WallpaperGiftPatternPosition r42 = this.giftPatternPositions.get(r4);
        r3.save();
        r3.translate(r5, r6);
        r3.scale(r7, r8);
        r3.concat(r42.matrix);
        this.giftImageReceiver.setImageCoords(r42.rect);
        this.giftImageReceiver.draw(r3);
        r3.restore();
        return;
    L14:
        return;
    }

    private void drawGiftImageForLegacyNegativeIntensity(Canvas r1, RectF r2, int r3) {
        drawGiftImageForPositiveIntensity(r1, r2, r3);
    }

    private void drawGiftImageForNegativeIntensity(Canvas r8, float r9, float r10, float r11) {
        drawGiftImage(r8, this.giftPosition, r9, r10, r11, r11);
    }

    private void drawGiftImageForPositiveIntensity(Canvas r10, RectF r11, int r12) {
        if (this.giftPatternPositions != null) goto L5;
        return;
    L5:
        if (this.patternBitmap == null) goto L9;
        drawGiftImage(r10, r12, r11.left, r11.top, r11.width() / this.patternBitmap.getWidth(), r11.height() / this.patternBitmap.getHeight());
        return;
    }

    private void drawGiftPatterns(Canvas r5, Paint r6, int r7) {
        if (this.patternGiftBitmap != null) goto L5;
        return;
    L5:
        if (this.giftPatternPositions == null) goto L17;
        int r0 = 0;
    L8:
        if (r0 >= this.giftPatternPositions.size()) goto L18;
        if (r0 == r7) goto L12;
        WallpaperGiftPatternPosition r1 = this.giftPatternPositions.get(r0);
        r5.save();
        r5.concat(r1.matrix);
        r5.drawBitmap(this.patternGiftBitmap, null, r1.rect, r6);
        r5.restore();
    L12:
        r0 = r0 + 1;
        goto L8
    L18:
        return;
    }

    private Bitmap getOrBuildPatternWithGiftBitmap() {
        Bitmap r0 = this.patternBitmap;
        if (r0 != null) goto L7;
        return null;
    L7:
        if (this.patternGiftBitmap != null) goto L9;
        return r0;
    L9:
        boolean r02 = this.patternChangeTracker.isInvalidated(r0);
        boolean r1 = this.giftChangeTracker.isInvalidated(this.patternGiftBitmap);
        boolean r4 = false;
        if (this.patternInvertedLastPosition == this.giftPosition) goto L12;
        boolean r2 = true;
    L13:
        if (r02 == true) goto L16;
        if (r1 == true) goto L16;
        if (r2 == true) goto L16;
    L17:
        Bitmap r03 = this.patternWithGiftBitmap;
        if (r03 == null) goto L21;
        if (r4 == true) goto L21;
        return r03;
    L21:
        int r04 = this.patternBitmap.getWidth();
        int r12 = this.patternBitmap.getHeight();
        Bitmap r22 = this.patternWithGiftBitmap;
        if (r22 != null) goto L24;
    L27:
        this.patternWithGiftBitmap = Bitmap.createBitmap(r04, r12, Bitmap.Config.ARGB_8888);
        this.patternWithGiftCanvas = new Canvas(this.patternWithGiftBitmap);
    L28:
        Bitmap.Config r05 = this.patternBitmap.getConfig();
        if (r05 != Bitmap.Config.ARGB_8888) goto L32;
        Utilities.copyBitmaps(this.patternBitmap, this.patternWithGiftBitmap);
    L35:
        if (this.patternWithGiftPaint != null) goto L37;
        Paint r06 = new Paint(3);
        this.patternWithGiftPaint = r06;
        r06.setAlpha(Opcodes.SUB_DOUBLE_2ADDR);
    L37:
        drawGiftPatterns(this.patternWithGiftCanvas, this.patternWithGiftPaint, this.giftPosition);
        this.patternInvertedLastPosition = this.giftPosition;
        this.patternChangeTracker.set(this.patternBitmap);
        this.giftChangeTracker.set(this.patternGiftBitmap);
        return this.patternWithGiftBitmap;
    L32:
        if (r05 != Bitmap.Config.ALPHA_8) goto L35;
        Utilities.expandAlphaToBlack(this.patternBitmap, this.patternWithGiftBitmap);
        goto L35
    L24:
        if (r22.getWidth() != r04) goto L27;
        if (this.patternWithGiftBitmap.getHeight() == r12) goto L28;
    L16:
        r4 = true;
        goto L17
    L12:
        r2 = false;
        goto L13
    }

    public static int getPatternColor(int r1, int r2, int r3, int r4) {
        if (isDark(r1, r2, r3, r4) == false) goto L11;
        if (useSoftLight == true) goto L8;
        return Integer.MAX_VALUE;
    L8:
        return -1;
    L11:
        if (useSoftLight == true) goto L17;
        int r12 = AndroidUtilities.getAverageColor(r3, AndroidUtilities.getAverageColor(r1, r2));
        if (r4 == 0) goto L16;
        r12 = AndroidUtilities.getAverageColor(r4, r12);
    L16:
        return (AndroidUtilities.getPatternColor(r12, true) & 16777215) | 1677721600;
    L17:
        return -16777216;
    }

    private void init() {
        Bitmap r0 = Bitmap.createBitmap(this.bitmapWidth, this.bitmapHeight, Bitmap.Config.ARGB_8888);
        this.currentBitmap = r0;
        r0.setHasAlpha(false);
        int r02 = 0;
    L4:
        if (r02 >= 3) goto L6;
        this.gradientToBitmap[r02] = Bitmap.createBitmap(this.bitmapWidth, this.bitmapHeight, Bitmap.Config.ARGB_8888);
        this.gradientToBitmap[r02].setHasAlpha(false);
        r02 = r02 + 1;
        goto L4
    L6:
        this.gradientCanvas = new Canvas(this.currentBitmap);
        Bitmap r03 = Bitmap.createBitmap(this.bitmapWidth, this.bitmapHeight, Bitmap.Config.ARGB_8888);
        this.gradientFromBitmap = r03;
        r03.setHasAlpha(false);
        this.gradientFromCanvas = new Canvas(this.gradientFromBitmap);
        Utilities.generateGradient(this.currentBitmap, this.phase, this.interpolator.getInterpolation(this.posAnimationProgress), this.colors);
        if (useSoftLight == false) goto L11;
        this.paint2.setBlendMode(PatternCell$$ExternalSyntheticApiModelOutline0.m());
        return;
    }

    private void invalidateParent() {
        invalidateSelf();
        WeakReference<View> r0 = this.parentView;
        if (r0 == null) goto L8;
        if (r0.get() == null) goto L8;
        this.parentView.get().invalidate();
    L8:
        if (this.postInvalidateParent == false) goto L11;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.invalidateMotionBackground, new Object[0]);
        updateAnimation();
        AndroidUtilities.cancelRunOnUIThread(this.updateAnimationRunnable);
        AndroidUtilities.runOnUIThread(this.updateAnimationRunnable, 16);
        return;
    }

    public static boolean isDark(int r0, int r1, int r2, int r3) {
        int r02 = AndroidUtilities.getAverageColor(r0, r1);
        if (r2 == 0) goto L5;
        r02 = AndroidUtilities.getAverageColor(r02, r2);
    L5:
        if (r3 == 0) goto L8;
        r02 = AndroidUtilities.getAverageColor(r02, r3);
    L8:
        if (AndroidUtilities.RGBtoHSB(Color.red(r02), Color.green(r02), Color.blue(r02))[2] >= 0.3f) goto L11;
        return true;
    L11:
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r19) {
        Rect r2 = getBounds();
        r19.save();
        Bitmap r5 = getOrBuildPatternWithGiftBitmap();
        if (r5 == null) goto L6;
        int r3 = r2.top;
    L5:
        float r32 = r3;
        int r4 = this.currentBitmap.getWidth();
        int r6 = this.currentBitmap.getHeight();
        float r7 = r2.width();
        float r8 = r2.height();
        float r42 = r4;
        float r62 = r6;
        float r9 = Math.max(r7 / r42, r8 / r62);
        float r43 = r42 * r9;
        float r63 = r62 * r9;
        float r92 = (r7 - r43) / 2.0f;
        float r11 = (r8 - r63) / 2.0f;
        if (this.isPreview == false) goto L11;
        int r12 = r2.left;
        r92 = r92 + r12;
        int r13 = r2.top;
        r11 = r11 + r13;
        r19.clipRect(r12, r13, r2.right, r2.bottom);
    L11:
        if (Build.VERSION.SDK_INT >= 28) goto L13;
    L18:
        boolean r122 = false;
    L20:
        if (this.intensity >= 0) goto L53;
        if (r122 == false) goto L24;
    L27:
        if (r5 != null) goto L29;
    L70:
        r19.restore();
        updateAnimation();
        return;
    L29:
        if (useLegacyBitmap == false) goto L36;
        checkLegacyForNegativeIntensity((((int) (this.alpha * this.patternAlpha)) * (-this.intensity)) / 100);
        int r22 = r5.getWidth();
        float r23 = r22;
        float r33 = r5.getHeight();
        float r52 = Math.max(r7 / r23, r8 / r33);
        float r24 = r23 * r52;
        float r34 = r33 * r52;
        float r72 = (r7 - r24) / 2.0f;
        float r82 = (r8 - r34) / 2.0f;
        this.rect.set(r72, r82, r24 + r72, r34 + r82);
        if (this.patternAlphaInverted == null) goto L33;
        r19.drawBitmap(this.currentBitmap, null, this.rect, this.paint);
        r19.drawBitmap(this.patternAlphaInverted, null, this.rect, this.paint);
    L34:
        drawGiftImageForLegacyNegativeIntensity(r19, this.rect, this.giftPosition);
        goto L70
    L33:
        r19.drawColor(ColorUtils.setAlphaComponent(-16777216, (int) (this.alpha * this.backgroundAlpha)));
        goto L34
    L36:
        if (this.matrix != null) goto L38;
        this.matrix = new Matrix();
    L38:
        this.matrix.reset();
        this.matrix.setTranslate(r92, r11 + r32);
        float r44 = 1.0f / Math.min(this.currentBitmap.getWidth() / r2.width(), this.currentBitmap.getHeight() / r2.height());
        this.matrix.preScale(r44, r44);
        this.bitmapShader.setLocalMatrix(this.matrix);
        this.matrix.reset();
        float r45 = r5.getWidth();
        float r64 = r5.getHeight();
        float r93 = Math.max(r7 / r45, r8 / r64);
        float r112 = (r7 - (r45 * r93)) / 2.0f;
        float r132 = ((r8 - (r64 * r93)) / 2.0f) + r32;
        this.matrix.setTranslate((int) r112, (int) r132);
        if (this.disableGradientShaderScaling == true) goto L41;
    L46:
        this.matrix.preScale(r93, r93);
        float r14 = r93;
    L47:
        this.gradientShader.setLocalMatrix(this.matrix);
        this.paint2.setColorFilter(null);
        this.paint2.setAlpha((int) (((Math.abs(this.intensity) / 100.0f) * this.alpha) * this.patternAlpha));
        this.rect.set(r2.left, r2.top, r2.right, r2.bottom);
        if (r122 == false) goto L50;
        Paint r25 = this.motionBackgroundPaint.getPaint(this.currentBitmap, r5, this.patternColor, (int) (this.alpha * this.patternAlpha), this.intensity, r19.isHardwareAccelerated());
        this.motionBackgroundPaint.applyPatternMatrix(this.matrix);
        this.motionBackgroundPaint.applyGradientMatrix(this.rect);
        RectF r35 = this.rect;
        int r46 = this.roundRadius;
        r19.drawRoundRect(r35, r46, r46, r25);
    L51:
        drawGiftImageForNegativeIntensity(r19, r112, r132, r14);
        goto L70
    L50:
        RectF r26 = this.rect;
        int r36 = this.roundRadius;
        r19.drawRoundRect(r26, r36, r36, this.paint2);
        goto L51
    L41:
        if (r93 > 1.4f) goto L46;
        if (r93 < 0.8f) goto L46;
        r14 = 1.0f;
        goto L47
    L24:
        if (useLegacyBitmap == false) goto L26;
        if (r5 != null) goto L27;
    L26:
        r19.drawColor(ColorUtils.setAlphaComponent(-16777216, (int) (this.alpha * this.backgroundAlpha)));
        goto L27
    L53:
        if (this.roundRadius == 0) goto L57;
        this.matrix.reset();
        this.matrix.setTranslate(r92, r11);
        float r37 = 1.0f / Math.min(this.currentBitmap.getWidth() / r2.width(), this.currentBitmap.getHeight() / r2.height());
        this.matrix.preScale(r37, r37);
        this.bitmapShader.setLocalMatrix(this.matrix);
        this.rect.set(r2.left, r2.top, r2.right, r2.bottom);
        if (r122 == true) goto L64;
        RectF r27 = this.rect;
        int r38 = this.roundRadius;
        r19.drawRoundRect(r27, r38, r38, this.paint);
    L64:
        if (r5 == null) goto L70;
        float r28 = r5.getWidth();
        float r39 = r5.getHeight();
        float r47 = Math.max(r7 / r28, r8 / r39);
        float r29 = r28 * r47;
        float r310 = r39 * r47;
        float r73 = (r7 - r29) / 2.0f;
        float r83 = (r8 - r310) / 2.0f;
        this.rect.set(r73, r83, r29 + r73, r310 + r83);
        this.paint2.setColorFilter(this.patternColorFilter);
        this.paint2.setAlpha((int) (((Math.abs(this.intensity) / 100.0f) * this.alpha) * this.patternAlpha));
        if (r122 == false) goto L68;
        Paint r210 = this.motionBackgroundPaint.getPaint(this.currentBitmap, r5, this.patternColor, (int) (this.alpha * this.patternAlpha), this.intensity, r19.isHardwareAccelerated());
        this.motionBackgroundPaint.applyPatternMatrix(this.rect);
        this.motionBackgroundPaint.applyGradientMatrix(this.rect);
        r19.drawRect(this.rect, r210);
    L69:
        this.paint2.setAlpha((int) ((((Math.abs(this.intensity) / 100.0f) * this.alpha) * this.patternAlpha) * 0.8f));
        drawGiftImageForPositiveIntensity(r19, this.rect, this.giftPosition);
        goto L70
    L68:
        r19.drawBitmap(r5, null, this.rect, this.paint2);
        goto L69
    L57:
        r19.translate(0.0f, r32);
        GradientDrawable r211 = this.gradientDrawable;
        if (r211 == null) goto L60;
        r211.setBounds((int) r92, (int) r11, (int) (r92 + r43), (int) (r11 + r63));
        this.gradientDrawable.setAlpha((int) (this.backgroundAlpha * 255.0f));
        this.gradientDrawable.draw(r19);
        goto L64
    L60:
        this.rect.set(r92, r11, r43 + r92, r63 + r11);
        Paint r212 = this.paint;
        int r311 = r212.getAlpha();
        r212.setAlpha((int) (r311 * this.backgroundAlpha));
        if (r122 == true) goto L63;
        r19.drawBitmap(this.currentBitmap, null, this.rect, r212);
    L63:
        r212.setAlpha(r311);
        goto L64
    L13:
        if (this.motionBackgroundPaint == null) goto L18;
        if (this.currentBitmap == null) goto L18;
        if (r5 == null) goto L18;
        r122 = true;
        goto L20
    L6:
        r3 = this.translationY;
        goto L5
    }

    public void generateNextGradient() {
        int r0 = -1;
    L4:
        if (r0 >= 3) goto L10;
        int r1 = r0 + 1;
        float r2 = r1 / 3.0f;
        if (r0 >= 0) goto L8;
        Bitmap r02 = this.gradientFromBitmap;
    L9:
        Utilities.generateGradient(r02, this.phase, r2, this.colors);
        r0 = r1;
        goto L4
    L8:
        r02 = this.gradientToBitmap[r0];
        goto L9
    }

    public Bitmap getBitmap() {
        return this.currentBitmap;
    }

    public BitmapShader getBitmapShader() {
        return this.bitmapShader;
    }

    public int[] getColors() {
        return this.colors;
    }

    public int getIntensity() {
        return this.intensity;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Bitmap r0 = this.patternBitmap;
        if (r0 == null) goto L7;
        return r0.getHeight();
    L7:
        return super.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Bitmap r0 = this.patternBitmap;
        if (r0 == null) goto L7;
        return r0.getWidth();
    L7:
        return super.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public Bitmap getPatternBitmap() {
        return this.patternBitmap;
    }

    public int getPatternColor() {
        int[] r4 = this.colors;
        return getPatternColor(r4[0], r4[1], r4[2], r4[3]);
    }

    public int getPhase() {
        return this.phase;
    }

    public float getPosAnimationProgress() {
        return this.posAnimationProgress;
    }

    public boolean hasPattern() {
        if (this.patternBitmap == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isIndeterminateAnimation() {
        return this.isIndeterminateAnimation;
    }

    public boolean isOneColor() {
        int[] r4 = this.colors;
        int r1 = r4[0];
        if (r1 == r4[1]) goto L5;
    L9:
        return false;
    L5:
        if (r1 != r4[2]) goto L9;
        if (r1 != r4[3]) goto L9;
        return true;
    }

    public void onAttachedToWindow() {
        this.isAttached = true;
        ImageReceiver r1 = this.giftImageReceiver;
        if (r1 == null) goto L6;
        r1.onAttachedToWindow();
        return;
    }

    public void onDetachedFromWindow() {
        this.isAttached = false;
        ImageReceiver r1 = this.giftImageReceiver;
        if (r1 == null) goto L6;
        r1.onDetachedFromWindow();
        return;
    }

    public void rotatePreview(boolean r3) {
        if (this.posAnimationProgress >= 1.0f) goto L5;
        return;
    L5:
        this.rotatingPreview = true;
        this.posAnimationProgress = 0.0f;
        this.rotationBack = r3;
        invalidateParent();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.alpha = r2;
        this.paint.setAlpha(r2);
        this.paint2.setAlpha(r2);
    }

    public void setAnimationProgressProvider(GenericProvider<MotionBackgroundDrawable, Float> r1) {
        this.animationProgressProvider = r1;
        updateAnimation();
    }

    public void setBackgroundAlpha(float r1) {
        this.backgroundAlpha = r1;
        invalidateParent();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setColors(int r8, int r9, int r10, int r11) {
        setColors(r8, r9, r10, r11, 0, true);
    }

    public void setColors(int r6, int r7, int r8, int r9, int r10, boolean r11) {
        if (this.isPreview == false) goto L7;
        if (r8 != 0) goto L7;
        if (r9 != 0) goto L7;
        this.gradientDrawable = new GradientDrawable(BackgroundGradientDrawable.getGradientOrientation(r10), new int[]{r6, r7});
    L8:
        int[] r102 = this.colors;
        if (r102[0] == r6) goto L11;
    L17:
        r102[0] = r6;
        r102[1] = r7;
        r102[2] = r8;
        r102[3] = r9;
        Bitmap r62 = this.currentBitmap;
        if (r62 == null) goto L23;
        Utilities.generateGradient(r62, this.phase, this.interpolator.getInterpolation(this.posAnimationProgress), this.colors);
        if (r11 == false) goto L24;
        invalidateParent();
        return;
    L24:
        return;
    L23:
        return;
    L11:
        if (r102[1] != r7) goto L17;
        if (r102[2] != r8) goto L17;
        if (r102[3] != r9) goto L17;
        return;
    L7:
        this.gradientDrawable = null;
        goto L8
    }

    public void setColors(int r3, int r4, int r5, int r6, Bitmap r7) {
        int[] r0 = this.colors;
        r0[0] = r3;
        r0[1] = r4;
        r0[2] = r5;
        r0[3] = r6;
        Utilities.generateGradient(r7, this.phase, this.interpolator.getInterpolation(this.posAnimationProgress), this.colors);
    }

    public void setFastRenderAllowed() {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return;
    L5:
        if (this.motionBackgroundPaint == null) goto L7;
        return;
    L7:
        if (SharedConfig.fastWallpaperDisabled == true) goto L12;
        this.motionBackgroundPaint = new MotionBackgroundPaint();
        return;
    }

    public void setGiftDrawable(TLRPC.Document r9) {
        if (this.giftImageReceiver != null) goto L10;
        ImageReceiver r0 = new ImageReceiver();
        this.giftImageReceiver = r0;
        r0.setAlpha(0.5f);
        WeakReference<View> r02 = this.parentView;
        if (r02 == null) goto L8;
        this.giftImageReceiver.setParentView(r02.get());
    L8:
        if (this.isAttached == false) goto L10;
        this.giftImageReceiver.onAttachedToWindow();
    L10:
        this.giftImageReceiver.setImage(ImageLocation.getForDocument(r9), "80_80", null, null, null, 0);
        this.giftImageReceiver.setAutoRepeatCount(1);
        this.giftImageReceiver.setAutoRepeat(1);
    }

    public void setGiftPatternBitmap(Bitmap r1) {
        this.patternGiftBitmap = r1;
        invalidateParent();
    }

    public void setGiftPatternRandomSeed(long r2) {
        if (this.giftPatternPositions == null) goto L6;
        this.giftPosition = new Random(r2).nextInt(this.giftPatternPositions.size());
        return;
    }

    public void setIndeterminateAnimation(boolean r4) {
        if (r4 == false) goto L4;
    L6:
        this.isIndeterminateAnimation = r4;
        return;
    L4:
        if (this.isIndeterminateAnimation == false) goto L6;
        this.posAnimationProgress = 1.0f - ((this.posAnimationProgress - (((int) (r0 / 0.125f)) * 0.125f)) / 0.125f);
        this.ignoreInterpolator = true;
        goto L6
    }

    public void setIndeterminateSpeedScale(float r1) {
        this.indeterminateSpeedScale = r1;
    }

    public void setParentView(View r2) {
        this.parentView = new WeakReference(r2);
        ImageReceiver r1 = this.giftImageReceiver;
        if (r1 == null) goto L6;
        r1.setParentView(r2);
        return;
    }

    public void setPatternAlpha(float r1) {
        this.patternAlpha = r1;
        invalidateParent();
    }

    public void setPatternBitmap(int r3) {
        setPatternBitmap(r3, this.patternBitmap, true);
    }

    public void setPatternBitmap(int r2, Bitmap r3) {
        setPatternBitmap(r2, r3, true);
    }

    public void setPatternBitmap(int r3, Bitmap r4, boolean r5) {
        this.intensity = r3;
        this.patternBitmap = r4;
        if (r4 != null) goto L6;
        return;
    L6:
        if (useSoftLight == false) goto L11;
        Paint r42 = this.paint2;
        if (r3 < 0) goto L10;
        r42.setBlendMode(PatternCell$$ExternalSyntheticApiModelOutline0.m());
        goto L11
    L10:
        r42.setBlendMode(null);
    L11:
        if (r3 >= 0) goto L19;
        if (useLegacyBitmap == true) goto L16;
        Bitmap r43 = this.currentBitmap;
        Shader.TileMode r0 = Shader.TileMode.CLAMP;
        this.bitmapShader = new BitmapShader(r43, r0, r0);
        Bitmap r44 = this.patternBitmap;
        Shader.TileMode r02 = Shader.TileMode.REPEAT;
        this.gradientShader = new BitmapShader(r44, r02, r02);
        this.disableGradientShaderScaling = r5;
        this.paint2.setShader(new ComposeShader(this.bitmapShader, this.gradientShader, PorterDuff.Mode.DST_IN));
        this.paint2.setFilterBitmap(true);
        this.matrix = new Matrix();
        return;
    L16:
        this.paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        return;
    L19:
        if (useLegacyBitmap == false) goto L22;
        this.paint2.setXfermode(null);
        return;
    }

    public void setPatternColorFilter(int r3) {
        this.patternColor = r3;
        this.patternColorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
        invalidateParent();
    }

    public void setPatternGiftPositions(List<WallpaperGiftPatternPosition> r1) {
        this.giftPatternPositions = r1;
    }

    public void setPhase(int r4) {
        this.phase = r4;
        if (r4 >= 0) goto L6;
        this.phase = 0;
    L8:
        Utilities.generateGradient(this.currentBitmap, this.phase, this.interpolator.getInterpolation(this.posAnimationProgress), this.colors);
        return;
    L6:
        if (r4 <= 7) goto L8;
        this.phase = 7;
        goto L8
    }

    public void setPosAnimationProgress(float r1) {
        this.posAnimationProgress = r1;
        updateAnimation();
    }

    public void setPostInvalidateParent(boolean r1) {
        this.postInvalidateParent = r1;
    }

    public void setRoundRadius(int r3) {
        this.roundRadius = r3;
        this.matrix = new Matrix();
        Bitmap r0 = this.currentBitmap;
        Shader.TileMode r1 = Shader.TileMode.CLAMP;
        BitmapShader r32 = new BitmapShader(r0, r1, r1);
        this.bitmapShader = r32;
        this.paint.setShader(r32);
        invalidateParent();
    }

    public void setTranslationY(int r1) {
        this.translationY = r1;
    }

    public void switchToNextPosition() {
        switchToNextPosition(false);
    }

    public void switchToNextPosition(boolean r4) {
        if (this.posAnimationProgress >= 1.0f) goto L5;
    L12:
        invalidateParent();
        return;
    L5:
        if (LiteMode.isEnabled(32) == false) goto L12;
        this.rotatingPreview = false;
        this.rotationBack = false;
        this.fastAnimation = r4;
        this.posAnimationProgress = 0.0f;
        int r0 = this.phase - 1;
        this.phase = r0;
        if (r0 >= 0) goto L10;
        this.phase = 7;
    L10:
        invalidateParent();
        this.gradientFromCanvas.drawBitmap(this.currentBitmap, 0.0f, 0.0f, null);
        generateNextGradient();
    }

    public void switchToPrevPosition(boolean r4) {
        if (this.posAnimationProgress >= 1.0f) goto L5;
        return;
    L5:
        this.rotatingPreview = false;
        this.fastAnimation = r4;
        this.rotationBack = true;
        this.posAnimationProgress = 0.0f;
        invalidateParent();
        Utilities.generateGradient(this.gradientFromBitmap, this.phase, 0.0f, this.colors);
        generateNextGradient();
    }

    public void updateAnimation() {
        long r0 = SystemClock.elapsedRealtime();
        long r2 = r0 - this.lastUpdateTime;
        if (r2 <= 20) goto L5;
        r2 = 17;
    L5:
        this.lastUpdateTime = r0;
        if (r2 <= 1) goto L145;
        boolean r02 = this.isIndeterminateAnimation;
        if (r02 == true) goto L11;
    L13:
        float r5 = this.posAnimationProgress;
        if (r5 >= 1.0f) goto L144;
        boolean r8 = true;
        if (this.postInvalidateParent == false) goto L18;
    L21:
        boolean r6 = true;
    L23:
        if (r02 == false) goto L29;
        float r52 = r5 + ((r2 / 12000.0f) * this.indeterminateSpeedScale);
        this.posAnimationProgress = r52;
        if (r52 < 1.0f) goto L27;
        this.posAnimationProgress = 0.0f;
    L27:
        float r03 = this.posAnimationProgress;
        int r3 = (int) (r03 / 0.125f);
        this.phase = r3;
        float r04 = 1.0f - ((r03 - (r3 * 0.125f)) / 0.125f);
    L132:
        if (r8 == false) goto L135;
        Utilities.generateGradient(this.currentBitmap, this.phase, r04, this.colors);
    L142:
        invalidateParent();
        return;
    L135:
        if (r04 == 1.0f) goto L141;
        int r32 = (int) (r04 / 0.33333334f);
        Canvas r4 = this.gradientCanvas;
        if (r32 != 0) goto L139;
        r4.drawBitmap(this.gradientFromBitmap, 0.0f, 0.0f, null);
    L140:
        this.paint3.setAlpha((int) (((r04 - (r32 * 0.33333334f)) / 0.33333334f) * 255.0f));
        this.gradientCanvas.drawBitmap(this.gradientToBitmap[r32], 0.0f, 0.0f, this.paint3);
        goto L142
    L139:
        r4.drawBitmap(this.gradientToBitmap[r32 - 1], 0.0f, 0.0f, null);
        goto L140
    L141:
        this.gradientCanvas.drawBitmap(this.gradientToBitmap[2], 0.0f, 0.0f, this.paint3);
        goto L142
    L29:
        if (this.rotatingPreview == false) goto L100;
        float r05 = this.interpolator.getInterpolation(r5);
        if (r05 > 0.25f) goto L34;
        char r06 = 0;
    L40:
        GenericProvider<MotionBackgroundDrawable, Float> r11 = this.animationProgressProvider;
        if (r11 == null) goto L43;
        this.posAnimationProgress = r11.provide(this).floatValue();
    L49:
        if (this.posAnimationProgress <= 1.0f) goto L52;
        this.posAnimationProgress = 1.0f;
    L52:
        if (this.animationProgressProvider == null) goto L54;
    L56:
        float r22 = this.posAnimationProgress;
    L58:
        if (this.ignoreInterpolator == true) goto L60;
    L64:
        if (r06 == 0) goto L66;
    L67:
        if (r06 == 1) goto L69;
    L70:
        if (r06 == 2) goto L72;
    L82:
        if (r22 > 0.25f) goto L85;
    L83:
        float r23 = r22 / 0.25f;
        if (this.rotationBack == false) goto L98;
        r04 = 1.0f - r23;
        if (this.posAnimationProgress < 1.0f) goto L99;
        int r07 = this.phase + 1;
        this.phase = r07;
        if (r07 <= 7) goto L131;
        this.phase = 0;
    L131:
        r04 = 1.0f;
    L99:
        r8 = r6;
        goto L132
    L98:
        r04 = r23;
        goto L99
    L85:
        if (r22 > 0.5f) goto L88;
        r22 = r22 - 0.25f;
        goto L83
    L88:
        if (r22 > 0.75f) goto L90;
        r22 = r22 - 0.5f;
        goto L83
    L90:
        r22 = r22 - 0.75f;
        goto L83
    L72:
        if (r22 <= 0.75f) goto L82;
    L73:
        boolean r08 = this.rotationBack;
        int r33 = this.phase;
        if (r08 == false) goto L78;
        int r34 = r33 + 1;
        this.phase = r34;
        if (r34 <= 7) goto L82;
        this.phase = 0;
        goto L82
    L78:
        int r35 = r33 - 1;
        this.phase = r35;
        if (r35 >= 0) goto L82;
        this.phase = 7;
        goto L82
    L69:
        if (r22 > 0.5f) goto L73;
    L66:
        if (r22 > 0.25f) goto L73;
    L60:
        if (r22 != 0.0f) goto L62;
    L63:
        this.ignoreInterpolator = false;
        goto L64
    L62:
        if (r22 != 1.0f) goto L64;
    L54:
        if (this.ignoreInterpolator == true) goto L56;
        r22 = this.interpolator.getInterpolation(this.posAnimationProgress);
        goto L58
    L43:
        float r112 = this.posAnimationProgress;
        float r24 = r2;
        if (this.rotationBack == false) goto L46;
        float r36 = 1000.0f;
    L47:
        this.posAnimationProgress = r112 + (r24 / r36);
        goto L49
    L46:
        r36 = 2000.0f;
        goto L47
    L34:
        if (r05 > 0.5f) goto L37;
        r06 = 1;
        goto L40
    L37:
        if (r05 > 0.75f) goto L39;
        r06 = 2;
        goto L40
    L39:
        r06 = 3;
        goto L40
    L100:
        GenericProvider<MotionBackgroundDrawable, Float> r09 = this.animationProgressProvider;
        if (r09 == null) goto L103;
        this.posAnimationProgress = r09.provide(this).floatValue();
    L109:
        if (this.posAnimationProgress <= 1.0f) goto L112;
        this.posAnimationProgress = 1.0f;
    L112:
        if (this.animationProgressProvider == null) goto L114;
    L116:
        r04 = this.posAnimationProgress;
    L118:
        if (this.ignoreInterpolator == false) goto L125;
        if (r04 != 0.0f) goto L122;
    L123:
        this.ignoreInterpolator = false;
        goto L125
    L122:
        if (r04 == 1.0f) goto L123;
    L125:
        if (this.rotationBack == false) goto L99;
        r04 = 1.0f - r04;
        if (this.posAnimationProgress < 1.0f) goto L99;
        int r010 = this.phase + 1;
        this.phase = r010;
        if (r010 <= 7) goto L131;
        this.phase = 0;
        goto L131
    L114:
        if (this.ignoreInterpolator == true) goto L116;
        r04 = this.interpolator.getInterpolation(this.posAnimationProgress);
        goto L118
    L103:
        float r011 = r2;
        if (this.fastAnimation == false) goto L106;
        float r25 = 300.0f;
    L107:
        this.posAnimationProgress = r5 + (r011 / r25);
        goto L109
    L106:
        r25 = 500.0f;
        goto L107
    L18:
        if (this.rotatingPreview == true) goto L21;
        r6 = false;
        goto L23
    L144:
        return;
    L11:
        if (this.posAnimationProgress != 1.0f) goto L13;
        this.posAnimationProgress = 0.0f;
        goto L13
    }
}
