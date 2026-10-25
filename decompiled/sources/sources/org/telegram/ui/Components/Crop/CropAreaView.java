package org.telegram.ui.Components.Crop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.SystemClock;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.BubbleActivity;

/* loaded from: classes7.dex */
public class CropAreaView extends ViewGroup {
    private Control activeControl;
    private RectF actualRect;
    private Animator animator;
    private Paint bitmapPaint;
    private RectF bottomEdge;
    private RectF bottomLeftCorner;
    private float bottomPadding;
    private RectF bottomRightCorner;
    private Bitmap circleBitmap;
    private Paint dimPaint;
    private boolean dimVisibile;
    private Paint eraserPaint;
    private float frameAlpha;
    private Paint framePaint;
    private boolean frameVisible;
    private boolean freeform;
    private Animator gridAnimator;
    private float gridProgress;
    private GridType gridType;
    private Paint handlePaint;
    private boolean inBubbleMode;
    private AccelerateDecelerateInterpolator interpolator;
    private boolean isDragging;
    private long lastUpdateTime;
    public float left;
    private RectF leftEdge;
    private Paint linePaint;
    private AreaViewListener listener;
    private float lockAspectRatio;
    private float minWidth;
    private float overrideDimAlpha;
    private float overrideFrameAlpha;
    private GridType previousGridType;
    private int previousX;
    private int previousY;
    private RectF rightEdge;
    public float rotate;
    public float scale;
    private Paint shadowPaint;
    private float sidePadding;
    public int size;
    private String subtitle;
    private StaticLayout subtitleLayout;
    TextPaint subtitlePaint;
    private RectF targetRect;
    private RectF tempRect;
    public float top;
    private RectF topEdge;
    private RectF topLeftCorner;
    private float topPadding;
    private RectF topRightCorner;
    public float tx;
    public float ty;

    /* renamed from: org.telegram.ui.Components.Crop.CropAreaView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ CropAreaView this$0;

        public AnonymousClass1(CropAreaView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            CropAreaView.m10067$$Nest$fputgridAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.Crop.CropAreaView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ CropAreaView this$0;
        final /* synthetic */ RectF val$targetRect;

        public AnonymousClass2(CropAreaView r1, RectF r2) {
            this.this$0 = r1;
            this.val$targetRect = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.setActualRect(this.val$targetRect);
            CropAreaView.m10066$$Nest$fputanimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.Crop.CropAreaView$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control = null;

        static {
            int[] r0 = new int[Control.values().length];
            $SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control = r0;
            r0[Control.TOP_LEFT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
        L20:
            $SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.TOP_RIGHT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
        L24:
            $SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.BOTTOM_LEFT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
        L34:
            $SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.BOTTOM_RIGHT.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
        L22:
            $SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.TOP.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
        L26:
            $SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.LEFT.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
        L28:
            $SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.RIGHT.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
        L30:
            $SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[Control.BOTTOM.ordinal()] = 8;     // Catch: NoSuchFieldError -> L19
            return;
        }
    }

    public interface AreaViewListener {
        void onAreaChange();

        void onAreaChangeBegan();

        void onAreaChangeEnded();
    }

    public enum Control extends Enum<Control> {
        private static final /* synthetic */ Control[] $VALUES = null;
        public static final Control BOTTOM = null;
        public static final Control BOTTOM_LEFT = null;
        public static final Control BOTTOM_RIGHT = null;
        public static final Control LEFT = null;
        public static final Control NONE = null;
        public static final Control RIGHT = null;
        public static final Control TOP = null;
        public static final Control TOP_LEFT = null;
        public static final Control TOP_RIGHT = null;

        private static /* synthetic */ Control[] $values() {
            return new Control[]{NONE, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, TOP, LEFT, BOTTOM, RIGHT};
        }

        static {
            NONE = new Control("NONE", 0);
            TOP_LEFT = new Control("TOP_LEFT", 1);
            TOP_RIGHT = new Control("TOP_RIGHT", 2);
            BOTTOM_LEFT = new Control("BOTTOM_LEFT", 3);
            BOTTOM_RIGHT = new Control("BOTTOM_RIGHT", 4);
            TOP = new Control("TOP", 5);
            LEFT = new Control("LEFT", 6);
            BOTTOM = new Control("BOTTOM", 7);
            RIGHT = new Control("RIGHT", 8);
            $VALUES = $values();
        }

        Control(String r1, int r2) {
        }

        public static Control valueOf(String r1) {
            return (Control) Enum.valueOf(Control.class, r1);
        }

        public static Control[] values() {
            return (Control[]) $VALUES.clone();
        }
    }

    public enum GridType extends Enum<GridType> {
        private static final /* synthetic */ GridType[] $VALUES = null;
        public static final GridType MAJOR = null;
        public static final GridType MINOR = null;
        public static final GridType NONE = null;

        private static /* synthetic */ GridType[] $values() {
            return new GridType[]{NONE, MINOR, MAJOR};
        }

        static {
            NONE = new GridType("NONE", 0);
            MINOR = new GridType("MINOR", 1);
            MAJOR = new GridType("MAJOR", 2);
            $VALUES = $values();
        }

        GridType(String r1, int r2) {
        }

        public static GridType valueOf(String r1) {
            return (GridType) Enum.valueOf(GridType.class, r1);
        }

        public static GridType[] values() {
            return (GridType[]) $VALUES.clone();
        }
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10066$$Nest$fputanimator(CropAreaView r0, Animator r1) {
        r0.animator = r1;
    }

    /* renamed from: -$$Nest$fputgridAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10067$$Nest$fputgridAnimator(CropAreaView r0, Animator r1) {
        r0.gridAnimator = r1;
    }

    public CropAreaView(Context r5) {
        super(r5);
        this.topLeftCorner = new RectF();
        this.topRightCorner = new RectF();
        this.bottomLeftCorner = new RectF();
        this.bottomRightCorner = new RectF();
        this.topEdge = new RectF();
        this.leftEdge = new RectF();
        this.bottomEdge = new RectF();
        this.rightEdge = new RectF();
        this.actualRect = new RectF();
        this.tempRect = new RectF();
        this.overrideDimAlpha = -1.0f;
        this.frameAlpha = 1.0f;
        this.overrideFrameAlpha = -1.0f;
        this.interpolator = new AccelerateDecelerateInterpolator();
        this.freeform = true;
        this.targetRect = new RectF();
        this.rotate = 0.0f;
        this.scale = 1.0f;
        this.tx = 0.0f;
        this.ty = 0.0f;
        this.inBubbleMode = r5 instanceof BubbleActivity;
        this.frameVisible = true;
        this.dimVisibile = true;
        this.sidePadding = AndroidUtilities.dp(16.0f);
        this.minWidth = AndroidUtilities.dp(32.0f);
        this.gridType = GridType.NONE;
        Paint r52 = new Paint();
        this.dimPaint = r52;
        r52.setColor(2130706432);
        Paint r53 = new Paint();
        this.shadowPaint = r53;
        Paint.Style r2 = Paint.Style.FILL;
        r53.setStyle(r2);
        this.shadowPaint.setColor(436207616);
        this.shadowPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        Paint r54 = new Paint();
        this.linePaint = r54;
        r54.setStyle(r2);
        this.linePaint.setColor(-1);
        this.linePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        Paint r55 = new Paint();
        this.handlePaint = r55;
        r55.setStyle(r2);
        this.handlePaint.setColor(-1);
        Paint r56 = new Paint();
        this.framePaint = r56;
        r56.setStyle(r2);
        this.framePaint.setColor(-1291845633);
        Paint r57 = new Paint(1);
        this.eraserPaint = r57;
        r57.setColor(0);
        this.eraserPaint.setStyle(r2);
        this.eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint r58 = new Paint(2);
        this.bitmapPaint = r58;
        r58.setColor(-1);
        setWillNotDraw(false);
    }

    private void constrainRectByHeight(RectF r2, float r3) {
        float r1 = r2.height();
        float r0 = r2.left;
        r2.right = r0 + (r3 * r1);
        r2.bottom = r2.top + r1;
    }

    private void constrainRectByWidth(RectF r2, float r3) {
        float r1 = r2.width();
        float r32 = r1 / r3;
        r2.right = r2.left + r1;
        r2.bottom = r2.top + r32;
    }

    @Keep
    private float getGridProgress() {
        return this.gridProgress;
    }

    @Keep
    private void setCropBottom(float r2) {
        this.actualRect.bottom = r2;
        invalidate();
    }

    @Keep
    private void setCropLeft(float r2) {
        this.actualRect.left = r2;
        invalidate();
    }

    @Keep
    private void setCropRight(float r2) {
        this.actualRect.right = r2;
        invalidate();
    }

    @Keep
    private void setCropTop(float r2) {
        this.actualRect.top = r2;
        invalidate();
    }

    @Keep
    private void setGridProgress(float r1) {
        this.gridProgress = r1;
        invalidate();
    }

    private void updateSubtitle() {
        if (this.subtitle != null) goto L5;
        this.subtitleLayout = null;
        return;
    L5:
        if (this.subtitlePaint != null) goto L7;
        TextPaint r0 = new TextPaint();
        this.subtitlePaint = r0;
        r0.setColor(ColorUtils.setAlphaComponent(-1, 120));
        this.subtitlePaint.setTextSize(AndroidUtilities.dp(13.0f));
        this.subtitlePaint.setTextAlign(Paint.Align.CENTER);
    L7:
        this.subtitleLayout = new StaticLayout(this.subtitle, this.subtitlePaint, getMeasuredWidth() - AndroidUtilities.dp(120.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    }

    public void calculateRect(RectF r13, float r14) {
        if (this.inBubbleMode == true) goto L5;
        float r0 = AndroidUtilities.statusBarHeight;
    L6:
        float r1 = ((getMeasuredHeight() - this.bottomPadding) - this.topPadding) - r0;
        float r2 = getMeasuredWidth() / r1;
        float r3 = Math.min(getMeasuredWidth(), r1) - (this.sidePadding * 2.0f);
        float r4 = getMeasuredWidth();
        float r6 = this.sidePadding;
        float r42 = r4 - (r6 * 2.0f);
        float r62 = r1 - (r6 * 2.0f);
        float r7 = getMeasuredWidth() / 2.0f;
        float r02 = (r0 + this.topPadding) + (r1 / 2.0f);
        if (Math.abs(1.0f - r14) >= 1.0E-4d) goto L10;
        float r32 = r3 / 2.0f;
        float r12 = r7 - r32;
        float r142 = r02 - r32;
        float r72 = r7 + r32;
        float r03 = r02 + r32;
    L16:
        r13.set(r12, r142, r72, r03);
        return;
    L10:
        if ((r14 - r2) > 1.0E-4d) goto L15;
        float r122 = r62 * r14;
        if (r122 > r42) goto L15;
        float r123 = r122 / 2.0f;
        float r143 = r7 - r123;
        float r63 = r62 / 2.0f;
        float r15 = r02 - r63;
        r72 = r7 + r123;
        r03 = r02 + r63;
        r12 = r143;
        r142 = r15;
    L15:
        float r124 = r42 / 2.0f;
        float r16 = r7 - r124;
        float r43 = (r42 / r14) / 2.0f;
        r142 = r02 - r43;
        r72 = r7 + r124;
        r03 = r02 + r43;
        r12 = r16;
        goto L16
    L5:
        r0 = 0.0f;
        goto L6
    }

    public void fill(RectF r6, Animator r7, boolean r8) {
        if (r8 == false) goto L8;
        Animator r82 = this.animator;
        if (r82 == null) goto L6;
        r82.cancel();
        this.animator = null;
    L6:
        AnimatorSet r83 = new AnimatorSet();
        this.animator = r83;
        r83.setDuration(300);
        ObjectAnimator r1 = ObjectAnimator.ofFloat(this, "cropLeft", new float[]{r6.left});
        r1.setInterpolator(this.interpolator);
        ObjectAnimator r12 = ObjectAnimator.ofFloat(this, "cropTop", new float[]{r6.top});
        r12.setInterpolator(this.interpolator);
        ObjectAnimator r13 = ObjectAnimator.ofFloat(this, "cropRight", new float[]{r6.right});
        r13.setInterpolator(this.interpolator);
        ObjectAnimator r14 = ObjectAnimator.ofFloat(this, "cropBottom", new float[]{r6.bottom});
        r14.setInterpolator(this.interpolator);
        r7.setInterpolator(this.interpolator);
        r83.playTogether(new Animator[]{r1, r12, r13, r14, r7});
        r83.addListener(new AnonymousClass2(this, r6));
        r83.start();
        return;
    L8:
        setActualRect(r6);
    }

    public float getAspectRatio() {
        RectF r2 = this.actualRect;
        return (r2.right - r2.left) / (r2.bottom - r2.top);
    }

    @Keep
    public float getCropBottom() {
        return this.actualRect.bottom;
    }

    public float getCropCenterX() {
        RectF r1 = this.actualRect;
        return (r1.left + r1.right) / 2.0f;
    }

    public float getCropCenterY() {
        RectF r1 = this.actualRect;
        return (r1.top + r1.bottom) / 2.0f;
    }

    public float getCropHeight() {
        RectF r1 = this.actualRect;
        return r1.bottom - r1.top;
    }

    @Keep
    public float getCropLeft() {
        return this.actualRect.left;
    }

    public void getCropRect(RectF r1) {
        r1.set(this.actualRect);
    }

    @Keep
    public float getCropRight() {
        return this.actualRect.right;
    }

    @Keep
    public float getCropTop() {
        return this.actualRect.top;
    }

    public float getCropWidth() {
        RectF r1 = this.actualRect;
        return r1.right - r1.left;
    }

    public Interpolator getInterpolator() {
        return this.interpolator;
    }

    public float getLockAspectRatio() {
        return this.lockAspectRatio;
    }

    public RectF getTargetRectToFill() {
        return getTargetRectToFill(getAspectRatio());
    }

    public RectF getTargetRectToFill(float r2) {
        calculateRect(this.targetRect, r2);
        return this.targetRect;
    }

    public boolean isDragging() {
        return this.isDragging;
    }

    @Override // android.view.View
    public void onDraw(Canvas r29) {
        if (this.freeform == false) goto L43;
        int r12 = AndroidUtilities.dp(2.0f / this.scale);
        int r7 = AndroidUtilities.dp(16.0f / this.scale);
        int r14 = AndroidUtilities.dp(3.0f / this.scale);
        RectF r2 = this.actualRect;
        float r4 = r2.left;
        int r15 = ((int) r4) - r12;
        float r5 = r2.top;
        int r6 = ((int) r5) - r12;
        int r3 = (int) (r2.right - r4);
        int r42 = r12 * 2;
        int r17 = r3 + r42;
        int r18 = ((int) (r2.bottom - r5)) + r42;
        r29.save();
        r29.translate(this.tx, this.ty);
        float r22 = this.scale;
        float r32 = (r17 / 2) + r15;
        float r43 = (r18 / 2) + r6;
        r29.scale(r22, r22, r32, r43);
        r29.rotate(this.rotate, r32, r43);
        if (this.dimVisibile == false) goto L11;
        int r23 = (-getWidth()) * 4;
        int r44 = (-getHeight()) * 4;
        int r52 = getWidth() * 4;
        int r8 = getHeight() * 4;
        float r33 = this.overrideDimAlpha;
        Paint r9 = this.dimPaint;
        if (r33 < 0.0f) goto L9;
        r9.setAlpha((int) (r33 * 255.0f));
    L10:
        float r24 = r23;
        float r34 = r44;
        float r45 = r52;
        int r92 = r6;
        float r19 = 255.0f;
        int r11 = 4;
        r29.drawRect(r24, r34, r45, 0.0f, this.dimPaint);
        r29.drawRect(r24, 0.0f, 0.0f, getHeight(), this.dimPaint);
        r29.drawRect(getWidth(), 0.0f, r45, getHeight(), this.dimPaint);
        r29.drawRect(r24, getHeight(), r45, r8, this.dimPaint);
        float r35 = r92 + r12;
        r29.drawRect(0.0f, 0.0f, getWidth(), r35, this.dimPaint);
        float r53 = (r92 + r18) - r12;
        r29.drawRect(0.0f, r35, r15 + r12, r53, this.dimPaint);
        r29.drawRect((r15 + r17) - r12, r35, getWidth(), r53, this.dimPaint);
        r29.drawRect(0.0f, r53, getWidth(), getHeight(), this.dimPaint);
    L13:
        if (this.frameVisible == false) goto L93;
        int r82 = r14 - r12;
        int r1 = r14 * 2;
        int r16 = r17 - r1;
        int r20 = r18 - r1;
        GridType r13 = this.gridType;
        if (r13 == GridType.NONE) goto L18;
    L20:
        float r25 = this.overrideFrameAlpha;
        Paint r46 = this.shadowPaint;
        if (r25 < 0.0f) goto L23;
        r46.setAlpha((int) ((this.gridProgress * 26.0f) * r25));
        this.linePaint.setAlpha((int) ((this.gridProgress * 178.0f) * this.overrideFrameAlpha));
        this.framePaint.setAlpha((int) (this.overrideFrameAlpha * 178.0f));
        this.handlePaint.setAlpha((int) (this.overrideFrameAlpha * r19));
    L24:
        int r10 = r15 + r82;
        float r26 = r10;
        float r36 = r92 + r82;
        int r172 = r15 + r17;
        float r47 = r172 - r82;
        GridType r132 = r13;
        r29.drawRect(r26, r36, r47, r6 + r12, this.framePaint);
        float r48 = r10 + r12;
        int r102 = r92 + r18;
        float r54 = r102 - r82;
        r29.drawRect(r26, r36, r48, r54, this.framePaint);
        r29.drawRect(r26, r8 - r12, r47, r54, this.framePaint);
        r29.drawRect(r4 - r12, r36, r47, r54, this.framePaint);
        int r83 = 0;
    L26:
        if (r83 >= 3) goto L42;
        if (r132 != GridType.MINOR) goto L38;
        int r110 = 1;
    L30:
        if (r110 >= r11) goto L41;
        if (r83 != 2) goto L35;
        if (r110 != 3) goto L35;
        int r173 = r110;
    L36:
        r110 = r173 + 1;
        r11 = 4;
    L35:
        int r27 = r15 + r14;
        int r37 = r16 / 3;
        float r38 = (((r37 / 3) * r110) + r27) + (r37 * r83);
        int r49 = r92 + r14;
        float r39 = r49;
        float r62 = r49 + r20;
        r173 = r110;
        r29.drawLine(r38, r39, r38, r62, this.shadowPaint);
        r29.drawLine(r38, r39, r38, r62, this.linePaint);
        int r111 = r20 / 3;
        float r28 = r27;
        float r310 = (r49 + ((r111 / 3) * r173)) + (r111 * r83);
        float r410 = r27 + r16;
        r29.drawLine(r28, r310, r410, r310, this.shadowPaint);
        r29.drawLine(r28, r310, r410, r310, this.linePaint);
    L41:
        r83 = r83 + 1;
        r11 = 4;
        goto L26
    L38:
        if (r132 != GridType.MAJOR) goto L41;
        if (r83 <= 0) goto L41;
        int r112 = r15 + r14;
        float r210 = ((r16 / 3) * r83) + r112;
        int r122 = r92 + r14;
        float r311 = r122;
        float r55 = r122 + r20;
        r29.drawLine(r210, r311, r210, r55, this.shadowPaint);
        r29.drawLine(r210, r311, r210, r55, this.linePaint);
        float r211 = r112;
        float r312 = r122 + ((r20 / 3) * r83);
        float r411 = r112 + r16;
        r29.drawLine(r211, r312, r411, r312, this.shadowPaint);
        r29.drawLine(r211, r312, r411, r312, this.linePaint);
        goto L41
    L42:
        float r212 = r15;
        float r313 = r92;
        float r412 = r15 + r7;
        float r56 = r92 + r14;
        r29.drawRect(r212, r313, r412, r56, this.handlePaint);
        float r413 = r15 + r14;
        float r57 = r92 + r7;
        r29.drawRect(r212, r313, r413, r57, this.handlePaint);
        float r213 = r172 - r7;
        float r414 = r172;
        r29.drawRect(r213, r313, r414, r56, this.handlePaint);
        float r214 = r172 - r14;
        r29.drawRect(r214, r313, r414, r57, this.handlePaint);
        float r314 = r102 - r14;
        float r58 = r102;
        r29.drawRect(r212, r314, r412, r58, this.handlePaint);
        float r315 = r102 - r7;
        r29.drawRect(r212, r315, r413, r58, this.handlePaint);
        r29.drawRect(r213, r314, r414, r58, this.handlePaint);
        r29.drawRect(r214, r315, r414, r58, this.handlePaint);
        r29.restore();
    L73:
        if (this.frameAlpha >= 1.0f) goto L92;
        long r316 = SystemClock.elapsedRealtime();
        long r59 = r316 - this.lastUpdateTime;
        if (r59 <= 17) goto L77;
        r59 = 17;
    L77:
        this.lastUpdateTime = r316;
        float r113 = this.frameAlpha + (r59 / 180.0f);
        this.frameAlpha = r113;
        if (r113 <= 1.0f) goto L80;
        this.frameAlpha = 1.0f;
    L80:
        invalidate();
        return;
    L92:
        return;
    L23:
        r46.setAlpha((int) ((this.gridProgress * 26.0f) * this.frameAlpha));
        this.linePaint.setAlpha((int) ((this.gridProgress * 178.0f) * this.frameAlpha));
        this.framePaint.setAlpha((int) (this.frameAlpha * 178.0f));
        this.handlePaint.setAlpha((int) (this.frameAlpha * r19));
        goto L24
    L18:
        if (this.gridProgress <= 0.0f) goto L20;
        r13 = this.previousGridType;
        goto L20
    L93:
        return;
    L9:
        r9.setAlpha((int) (255.0f - (this.frameAlpha * 127.0f)));
        goto L10
    L11:
        r92 = r6;
        r19 = 255.0f;
        r11 = 4;
        goto L13
    L43:
        float r114 = getMeasuredWidth() - (this.sidePadding * 2.0f);
        float r215 = getMeasuredHeight() - this.bottomPadding;
        if (this.inBubbleMode == true) goto L46;
        int r317 = AndroidUtilities.statusBarHeight;
    L47:
        float r216 = ((r215 - r317) - this.topPadding) - (this.sidePadding * 2.0f);
        this.size = (int) Math.min(r114, r216);
        Bitmap r318 = this.circleBitmap;
        if (r318 != null) goto L50;
    L51:
        Bitmap r319 = this.circleBitmap;
        if (r319 == null) goto L54;
        boolean r84 = true;
    L56:
        if (r319 == null) goto L83;
        r319.recycle();
        this.circleBitmap = null;
    L83:
        int r320 = this.size;     // Catch: Throwable -> L82
        this.circleBitmap = Bitmap.createBitmap(r320, r320, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L82
        Canvas r321 = new Canvas(this.circleBitmap);     // Catch: Throwable -> L82
        int r510 = this.size;     // Catch: Throwable -> L82
        r321.drawRect(0.0f, 0.0f, r510, r510, this.dimPaint);     // Catch: Throwable -> L82
        int r511 = this.size;     // Catch: Throwable -> L82
        r321.drawCircle(r511 / 2, r511 / 2, r511 / 2, this.eraserPaint);     // Catch: Throwable -> L82
        r321.setBitmap(null);     // Catch: Throwable -> L82
        if (r84 == true) goto L62;
        this.frameAlpha = 0.0f;     // Catch: Throwable -> L82
        this.lastUpdateTime = SystemClock.elapsedRealtime();     // Catch: Throwable -> L82
    L62:
        if (this.circleBitmap == null) goto L73;
        this.bitmapPaint.setAlpha((int) (this.frameAlpha * 255.0f));
        this.dimPaint.setAlpha((int) (this.frameAlpha * 127.0f));
        float r322 = this.sidePadding;
        int r415 = this.size;
        float r115 = ((r114 - r415) / 2.0f) + r322;
        this.left = r115;
        float r323 = r322 + ((r216 - r415) / 2.0f);
        if (this.inBubbleMode == true) goto L66;
        int r93 = AndroidUtilities.statusBarHeight;
    L67:
        float r324 = r323 + r93;
        this.top = r324;
        float r85 = r115 + r415;
        float r94 = r324 + r415;
        r29.drawRect(0.0f, 0.0f, getWidth(), (int) this.top, this.dimPaint);
        float r512 = (int) r94;
        r29.drawRect(0.0f, (int) this.top, (int) this.left, r512, this.dimPaint);
        r29.drawRect((int) r85, (int) this.top, getWidth(), r512, this.dimPaint);
        r29.drawRect(0.0f, r512, getWidth(), getHeight(), this.dimPaint);
        r29.drawBitmap(this.circleBitmap, (int) this.left, (int) this.top, this.bitmapPaint);
        if (getMeasuredHeight() <= getMeasuredWidth()) goto L73;
        if (this.subtitleLayout == null) goto L73;
        r29.save();
        r29.translate(getMeasuredWidth() / 2.0f, r94 + AndroidUtilities.dp(16.0f));
        this.subtitleLayout.draw(r29);
        r29.restore();
        goto L73
    L66:
        r93 = 0;
        goto L67
    L54:
        r84 = false;
        goto L56
    L50:
        if (r318.getWidth() == this.size) goto L62;
    L46:
        r317 = 0;
        goto L47
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        if (this.isDragging == false) goto L7;
        return false;
    L7:
        return super.onInterceptTouchEvent(r2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        updateSubtitle();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        int r0 = (int) (r8.getX() - ((ViewGroup) getParent()).getX());
        int r1 = (int) (r8.getY() - ((ViewGroup) getParent()).getY());
        if (this.inBubbleMode == true) goto L5;
        float r2 = AndroidUtilities.statusBarHeight;
    L6:
        int r82 = r8.getActionMasked();
        boolean r5 = false;
        if (r82 == 0) goto L9;
        if (r82 != 1) goto L44;
    L138:
        this.isDragging = false;
        updateStatusShow(false);
        Control r83 = this.activeControl;
        Control r02 = Control.NONE;
        if (r83 != r02) goto L141;
        return false;
    L141:
        this.activeControl = r02;
        AreaViewListener r7 = this.listener;
        if (r7 == null) goto L144;
        r7.onAreaChangeEnded();
    L144:
        return true;
    L44:
        if (r82 == 3) goto L138;
        if (r82 == 2) goto L49;
        return false;
    L49:
        if (this.activeControl != Control.NONE) goto L51;
        return false;
    L51:
        this.tempRect.set(this.actualRect);
        float r84 = r0 - this.previousX;
        float r6 = r1 - this.previousY;
        this.previousX = r0;
        this.previousY = r1;
        if (Math.abs(r84) <= Math.abs(r6)) goto L55;
        r5 = true;
    L55:
        switch(AnonymousClass3.$SwitchMap$org$telegram$ui$Components$Crop$CropAreaView$Control[this.activeControl.ordinal()]) {
            case 1: goto L88;
            case 2: goto L81;
            case 3: goto L74;
            case 4: goto L69;
            case 5: goto L66;
            case 6: goto L63;
            case 7: goto L60;
            case 8: goto L57;
            default: goto L95;
        };
    L57:
        RectF r85 = this.tempRect;
        r85.bottom += r6;
        float r03 = this.lockAspectRatio;
        if (r03 <= 0.0f) goto L95;
        constrainRectByHeight(r85, r03);
        goto L95
    L60:
        RectF r04 = this.tempRect;
        r04.right += r84;
        float r86 = this.lockAspectRatio;
        if (r86 <= 0.0f) goto L95;
        constrainRectByWidth(r04, r86);
        goto L95
    L63:
        RectF r05 = this.tempRect;
        r05.left += r84;
        float r87 = this.lockAspectRatio;
        if (r87 <= 0.0f) goto L95;
        constrainRectByWidth(r05, r87);
        goto L95
    L66:
        RectF r88 = this.tempRect;
        r88.top += r6;
        float r06 = this.lockAspectRatio;
        if (r06 <= 0.0f) goto L95;
        constrainRectByHeight(r88, r06);
        goto L95
    L69:
        RectF r07 = this.tempRect;
        r07.right += r84;
        r07.bottom += r6;
        float r89 = this.lockAspectRatio;
        if (r89 <= 0.0f) goto L95;
        if (r5 == false) goto L73;
        constrainRectByWidth(r07, r89);
        goto L95
    L73:
        constrainRectByHeight(r07, r89);
        goto L95
    L74:
        RectF r08 = this.tempRect;
        r08.left += r84;
        r08.bottom += r6;
        if (this.lockAspectRatio <= 0.0f) goto L95;
        float r810 = r08.width();
        RectF r09 = this.tempRect;
        if (r5 == false) goto L79;
        constrainRectByWidth(r09, this.lockAspectRatio);
    L80:
        RectF r010 = this.tempRect;
        r010.left -= r010.width() - r810;
        goto L95
    L79:
        constrainRectByHeight(r09, this.lockAspectRatio);
        goto L80
    L81:
        RectF r011 = this.tempRect;
        r011.right += r84;
        r011.top += r6;
        if (this.lockAspectRatio <= 0.0f) goto L95;
        float r811 = r011.height();
        RectF r012 = this.tempRect;
        if (r5 == false) goto L86;
        constrainRectByWidth(r012, this.lockAspectRatio);
    L87:
        RectF r013 = this.tempRect;
        r013.top -= r013.width() - r811;
        goto L95
    L86:
        constrainRectByHeight(r012, this.lockAspectRatio);
        goto L87
    L88:
        RectF r014 = this.tempRect;
        r014.left += r84;
        r014.top += r6;
        if (this.lockAspectRatio <= 0.0f) goto L95;
        float r812 = r014.width();
        float r015 = this.tempRect.height();
        RectF r12 = this.tempRect;
        if (r5 == false) goto L93;
        constrainRectByWidth(r12, this.lockAspectRatio);
    L94:
        RectF r13 = this.tempRect;
        r13.left -= r13.width() - r812;
        RectF r813 = this.tempRect;
        r813.top -= r813.width() - r015;
        goto L95
    L93:
        constrainRectByHeight(r12, this.lockAspectRatio);
    L95:
        RectF r814 = this.tempRect;
        float r016 = r814.left;
        float r14 = this.sidePadding;
        if (r016 >= r14) goto L102;
        float r017 = this.lockAspectRatio;
        if (r017 <= 0.0f) goto L100;
        r814.bottom = r814.top + ((r814.right - r14) / r017);
    L100:
        r814.left = r14;
    L106:
        float r22 = r2 + this.topPadding;
        float r815 = this.sidePadding;
        float r23 = r22 + r815;
        float r018 = this.bottomPadding + r815;
        RectF r816 = this.tempRect;
        if (r816.top >= r23) goto L113;
        float r019 = this.lockAspectRatio;
        if (r019 <= 0.0f) goto L111;
        r816.right = r816.left + ((r816.bottom - r23) * r019);
    L111:
        r816.top = r23;
    L117:
        float r817 = this.tempRect.width();
        float r020 = this.minWidth;
        if (r817 >= r020) goto L120;
        RectF r818 = this.tempRect;
        r818.right = r818.left + r020;
    L120:
        float r819 = this.tempRect.height();
        float r021 = this.minWidth;
        if (r819 >= r021) goto L123;
        RectF r820 = this.tempRect;
        r820.bottom = r820.top + r021;
    L123:
        float r821 = this.lockAspectRatio;
        if (r821 <= 0.0f) goto L133;
        RectF r022 = this.tempRect;
        if (r821 >= 1.0f) goto L130;
        float r822 = r022.width();
        float r023 = this.minWidth;
        if (r822 > r023) goto L133;
        RectF r823 = this.tempRect;
        r823.right = r823.left + r023;
        r823.bottom = r823.top + (r823.width() / this.lockAspectRatio);
        goto L133
    L130:
        float r824 = r022.height();
        float r024 = this.minWidth;
        if (r824 > r024) goto L133;
        RectF r825 = this.tempRect;
        r825.bottom = r825.top + r024;
        r825.right = r825.left + (r825.height() * this.lockAspectRatio);
    L133:
        setActualRect(this.tempRect);
        AreaViewListener r72 = this.listener;
        if (r72 == null) goto L136;
        r72.onAreaChange();
    L136:
        return true;
    L113:
        if (r816.bottom <= (getHeight() - r018)) goto L117;
        this.tempRect.bottom = getHeight() - r018;
        if (this.lockAspectRatio <= 0.0f) goto L117;
        RectF r826 = this.tempRect;
        r826.right = r826.left + (r826.height() * this.lockAspectRatio);
        goto L117
    L102:
        if (r814.right <= (getWidth() - this.sidePadding)) goto L106;
        this.tempRect.right = getWidth() - this.sidePadding;
        if (this.lockAspectRatio <= 0.0f) goto L106;
        RectF r827 = this.tempRect;
        r827.bottom = r827.top + (r827.width() / this.lockAspectRatio);
        goto L106
    L9:
        if (this.freeform == false) goto L40;
        float r24 = r0;
        float r3 = r1;
        if (this.topLeftCorner.contains(r24, r3) == false) goto L14;
        this.activeControl = Control.TOP_LEFT;
    L34:
        this.previousX = r0;
        this.previousY = r1;
        setGridType(GridType.MAJOR, false);
        this.isDragging = true;
        updateStatusShow(true);
        AreaViewListener r73 = this.listener;
        if (r73 == null) goto L37;
        r73.onAreaChangeBegan();
    L37:
        return true;
    L14:
        if (this.topRightCorner.contains(r24, r3) == false) goto L17;
        this.activeControl = Control.TOP_RIGHT;
        goto L34
    L17:
        if (this.bottomLeftCorner.contains(r24, r3) == false) goto L20;
        this.activeControl = Control.BOTTOM_LEFT;
        goto L34
    L20:
        if (this.bottomRightCorner.contains(r24, r3) == false) goto L23;
        this.activeControl = Control.BOTTOM_RIGHT;
        goto L34
    L23:
        if (this.leftEdge.contains(r24, r3) == false) goto L26;
        this.activeControl = Control.LEFT;
        goto L34
    L26:
        if (this.topEdge.contains(r24, r3) == false) goto L29;
        this.activeControl = Control.TOP;
        goto L34
    L29:
        if (this.rightEdge.contains(r24, r3) == false) goto L32;
        this.activeControl = Control.RIGHT;
        goto L34
    L32:
        if (this.bottomEdge.contains(r24, r3) == false) goto L38;
        this.activeControl = Control.BOTTOM;
        goto L34
    L38:
        this.activeControl = Control.NONE;
        return false;
    L40:
        this.activeControl = Control.NONE;
        return false;
    L5:
        r2 = 0.0f;
        goto L6
    }

    public void resetAnimator() {
        Animator r0 = this.animator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.animator = null;
        return;
    }

    public void setActualRect(float r2) {
        calculateRect(this.actualRect, r2);
        updateTouchAreas();
        invalidate();
    }

    public void setActualRect(RectF r2) {
        this.actualRect.set(r2);
        updateTouchAreas();
        invalidate();
    }

    public void setBitmap(int r1, int r2, boolean r3, boolean r4) {
        this.freeform = r4;
        if (r3 == false) goto L5;
        float r22 = r2 / r1;
    L6:
        if (r4 == true) goto L8;
        r22 = 1.0f;
        this.lockAspectRatio = 1.0f;
    L8:
        setActualRect(r22);
        return;
    L5:
        r22 = r1 / r2;
        goto L6
    }

    public void setBottomPadding(float r1) {
        this.bottomPadding = r1;
    }

    public void setDimAlpha(float r1) {
        this.overrideDimAlpha = r1;
    }

    public void setDimVisibility(boolean r1) {
        this.dimVisibile = r1;
    }

    public void setFrameAlpha(float r1) {
        this.overrideFrameAlpha = r1;
    }

    public void setFrameVisibility(boolean r2, boolean r3) {
        this.frameVisible = r2;
        float r0 = 1.0f;
        if (r2 == false) goto L8;
        if (r3 == false) goto L6;
        r0 = 0.0f;
    L6:
        this.frameAlpha = r0;
        this.lastUpdateTime = SystemClock.elapsedRealtime();
        invalidate();
        return;
    L8:
        this.frameAlpha = 1.0f;
    }

    public void setFreeform(boolean r1) {
        this.freeform = r1;
    }

    public void setGridType(GridType r5, boolean r6) {
        Animator r0 = this.gridAnimator;
        if (r0 == null) goto L8;
        if (r6 == true) goto L6;
    L7:
        r0.cancel();
        this.gridAnimator = null;
        goto L8
    L6:
        if (this.gridType != r5) goto L7;
    L8:
        GridType r02 = this.gridType;
        if (r02 != r5) goto L11;
        return;
    L11:
        this.previousGridType = r02;
        this.gridType = r5;
        GridType r03 = GridType.NONE;
        if (r5 != r03) goto L14;
        float r1 = 0.0f;
    L15:
        if (r6 == true) goto L18;
        this.gridProgress = r1;
        invalidate();
        return;
    L18:
        ObjectAnimator r62 = ObjectAnimator.ofFloat(this, "gridProgress", new float[]{this.gridProgress, r1});
        this.gridAnimator = r62;
        r62.setDuration(200);
        this.gridAnimator.addListener(new AnonymousClass1(this));
        if (r5 != r03) goto L21;
        this.gridAnimator.setStartDelay(200);
    L21:
        this.gridAnimator.start();
        return;
    L14:
        r1 = 1.0f;
        goto L15
    }

    public void setIsVideo(boolean r1) {
        if (r1 == false) goto L4;
        float r12 = 64.0f;
    L5:
        this.minWidth = AndroidUtilities.dp(r12);
        return;
    L4:
        r12 = 32.0f;
        goto L5
    }

    public void setListener(AreaViewListener r1) {
        this.listener = r1;
    }

    public void setLockedAspectRatio(float r1) {
        this.lockAspectRatio = r1;
    }

    public void setRotationScaleTranslation(float r1, float r2, float r3, float r4) {
        this.rotate = r1;
        this.scale = r2;
        this.tx = r3;
        this.ty = r4;
        invalidate();
    }

    public void setSubtitle(String r1) {
        this.subtitle = r1;
        if (getMeasuredWidth() <= 0) goto L6;
        updateSubtitle();
        return;
    }

    public void setTopPadding(float r1) {
        this.topPadding = r1;
    }

    public void updateStatusShow(boolean r2) {
        View r1 = ((Activity) getContext()).getWindow().getDecorView();     // Catch: Exception -> L8
        int r0 = r1.getSystemUiVisibility();     // Catch: Exception -> L8
        if (r2 == false) goto L5;
        int r22 = r0 | 4;     // Catch: Exception -> L8
    L6:
        r1.setSystemUiVisibility(r22);     // Catch: Exception -> L8
        return;
    L5:
        r22 = r0 & (-5);     // Catch: Exception -> L8
    }

    public void updateTouchAreas() {
        int r0 = AndroidUtilities.dp(16.0f);
        RectF r1 = this.topLeftCorner;
        RectF r2 = this.actualRect;
        float r3 = r2.left;
        float r02 = r0;
        float r22 = r2.top;
        r1.set(r3 - r02, r22 - r02, r3 + r02, r22 + r02);
        RectF r12 = this.topRightCorner;
        RectF r23 = this.actualRect;
        float r32 = r23.right;
        float r24 = r23.top;
        r12.set(r32 - r02, r24 - r02, r32 + r02, r24 + r02);
        RectF r13 = this.bottomLeftCorner;
        RectF r25 = this.actualRect;
        float r33 = r25.left;
        float r26 = r25.bottom;
        r13.set(r33 - r02, r26 - r02, r33 + r02, r26 + r02);
        RectF r14 = this.bottomRightCorner;
        RectF r27 = this.actualRect;
        float r34 = r27.right;
        float r28 = r27.bottom;
        r14.set(r34 - r02, r28 - r02, r34 + r02, r28 + r02);
        RectF r15 = this.topEdge;
        RectF r29 = this.actualRect;
        float r35 = r29.left + r02;
        float r4 = r29.top;
        r15.set(r35, r4 - r02, r29.right - r02, r4 + r02);
        RectF r16 = this.leftEdge;
        RectF r210 = this.actualRect;
        float r36 = r210.left;
        r16.set(r36 - r02, r210.top + r02, r36 + r02, r210.bottom - r02);
        RectF r17 = this.rightEdge;
        RectF r211 = this.actualRect;
        float r37 = r211.right;
        r17.set(r37 - r02, r211.top + r02, r37 + r02, r211.bottom - r02);
        RectF r18 = this.bottomEdge;
        RectF r6 = this.actualRect;
        float r212 = r6.left + r02;
        float r38 = r6.bottom;
        r18.set(r212, r38 - r02, r6.right - r02, r38 + r02);
    }
}
