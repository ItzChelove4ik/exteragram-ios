package org.telegram.ui.Components;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.StateSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.Keep;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.exteragram.messenger.utils.ui.SwitchUiHelper;
import me.vkryl.android.animator.BoolAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BaseCell;

/* loaded from: classes7.dex */
public class Switch extends View {
    private static final FloatPropertyCompat<Switch> PROGRESS_PROPERTY = null;
    private final BoolAnimator animatorIconVisibility;
    private final BoolAnimator animatorPressed;
    private boolean attachedToWindow;
    private boolean bitmapsCreated;
    private ObjectAnimator checkAnimator;
    private SpringAnimation checkSpringAnimator;
    private int colorSet;
    private int drawIconType;
    private boolean drawRipple;
    private ObjectAnimator iconAnimator;
    private Drawable iconDrawable;
    private final float[] iconLines;
    private float iconProgress;
    private boolean isChecked;
    private int lastIconColor;
    private Paint outlinePaint;
    private Bitmap[] overlayBitmap;
    private Canvas[] overlayCanvas;
    private float overlayCx;
    private float overlayCy;
    private Paint overlayEraserPaint;
    private Bitmap overlayMaskBitmap;
    private Canvas overlayMaskCanvas;
    private Paint overlayMaskPaint;
    private float overlayRad;
    private float overrideAlpha;
    private int overrideColorProgress;
    private Paint paint;
    private Paint paint2;
    private int[] pressedState;
    private float progress;
    private RectF rectF;
    private Theme.ResourcesProvider resourcesProvider;
    private RippleDrawable rippleDrawable;
    private Paint ripplePaint;
    private int thumbCheckedColorKey;
    private int thumbColorKey;
    private int trackCheckedColorKey;
    private int trackColorKey;

    /* renamed from: org.telegram.ui.Components.Switch$1, reason: invalid class name */
    public class AnonymousClass1 extends FloatPropertyCompat<Switch> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(Switch r1) {
            return getValue2(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(Switch r1) {
            return r1.getProgress();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(Switch r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(Switch r1, float r2) {
            r1.setProgress(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.Switch$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ Switch this$0;

        public AnonymousClass3(Switch r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            Switch.m13163$$Nest$fputcheckAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.Switch$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ Switch this$0;

        public AnonymousClass4(Switch r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            Switch.m13164$$Nest$fputiconAnimator(this.this$0, null);
        }
    }

    public interface OnCheckedChangeListener {
    }

    /* renamed from: -$$Nest$fputcheckAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13163$$Nest$fputcheckAnimator(Switch r0, ObjectAnimator r1) {
        r0.checkAnimator = r1;
    }

    /* renamed from: -$$Nest$fputiconAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13164$$Nest$fputiconAnimator(Switch r0, ObjectAnimator r1) {
        r0.iconAnimator = r1;
    }

    static {
        PROGRESS_PROPERTY = new AnonymousClass1("progress");
    }

    public Switch(Context r2) {
        this(r2, null);
    }

    public Switch(Context r7, Theme.ResourcesProvider r8) {
        super(r7);
        this.animatorIconVisibility = new BoolAnimator(this, CubicBezierInterpolator.EASE_OUT_QUINT, 380, true);
        this.animatorPressed = SwitchUiHelper.createThumbPressedAnimator(this);
        this.iconLines = new float[8];
        this.iconProgress = 1.0f;
        this.trackColorKey = Theme.key_fill_RedNormal;
        this.trackCheckedColorKey = Theme.key_switch2TrackChecked;
        int r72 = Theme.key_windowBackgroundWhite;
        this.thumbColorKey = r72;
        this.thumbCheckedColorKey = r72;
        this.pressedState = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        this.overrideAlpha = 1.0f;
        this.resourcesProvider = r8;
        this.rectF = new RectF();
        this.paint = new Paint(1);
        Paint r6 = new Paint(1);
        this.paint2 = r6;
        Paint.Style r82 = Paint.Style.STROKE;
        r6.setStyle(r82);
        this.paint2.setStrokeCap(Paint.Cap.ROUND);
        this.paint2.setStrokeWidth(AndroidUtilities.dp(2.0f));
        Paint r62 = new Paint(1);
        this.outlinePaint = r62;
        r62.setStyle(r82);
        setHapticFeedbackEnabled(true);
    }

    private void animateIcon(boolean r3) {
        if (r3 == false) goto L4;
        float r32 = 1.0f;
    L5:
        ObjectAnimator r33 = ObjectAnimator.ofFloat(this, "iconProgress", new float[]{r32});
        this.iconAnimator = r33;
        r33.setDuration(200);
        this.iconAnimator.addListener(new AnonymousClass4(this));
        this.iconAnimator.start();
        return;
    L4:
        r32 = 0.0f;
        goto L5
    }

    private void animateToCheckedState(boolean r4) {
        float r1 = 0.0f;
        if (SwitchUiHelper.isMaterial3SwitchStyle() == false) goto L15;
        ObjectAnimator r0 = this.checkAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.checkAnimator = null;
    L8:
        if (this.checkSpringAnimator != null) goto L10;
        this.checkSpringAnimator = SwitchUiHelper.createThumbSpring(this, PROGRESS_PROPERTY);
    L10:
        SpringAnimation r3 = this.checkSpringAnimator;
        if (r4 == false) goto L13;
        r1 = 1.0f;
    L13:
        r3.animateToFinalPosition(r1);
        return;
    L15:
        cancelCheckAnimator();
        if (r4 == false) goto L18;
        r1 = 1.0f;
    L18:
        ObjectAnimator r42 = ObjectAnimator.ofFloat(this, "progress", new float[]{r1});
        this.checkAnimator = r42;
        r42.setDuration(200);
        this.checkAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.checkAnimator.addListener(new AnonymousClass3(this));
        this.checkAnimator.start();
    }

    private void cancelCheckAnimator() {
        ObjectAnimator r0 = this.checkAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.checkAnimator = null;
    L5:
        SpringAnimation r02 = this.checkSpringAnimator;
        if (r02 == null) goto L9;
        r02.cancel();
        this.checkSpringAnimator = null;
        return;
    }

    private void cancelIconAnimator() {
        ObjectAnimator r0 = this.iconAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.iconAnimator = null;
        return;
    }

    private void checkBitmaps() {
        if (this.overrideColorProgress == 0) goto L33;
        int r0 = SwitchUiHelper.getOverlayPadding();
        int r02 = r0 * 4;
        int r1 = getMeasuredWidth() + r02;
        int r2 = getMeasuredHeight() + r02;
        int r3 = 0;
        if (this.bitmapsCreated == false) goto L17;
        Bitmap[] r03 = this.overlayBitmap;
        if (r03 == null) goto L17;
        Bitmap r04 = r03[0];
        if (r04 == null) goto L17;
        if (r04.getWidth() == r1) goto L14;
    L15:
        destroyBitmaps();
        goto L17
    L14:
        if (this.overlayBitmap[0].getHeight() != r2) goto L15;
    L17:
        if (this.bitmapsCreated == true) goto L31;
        if (r1 <= 0) goto L32;
        if (r2 > 0) goto L28;
        return;
    L28:
        this.overlayBitmap = new Bitmap[2];     // Catch: Throwable -> L27
        this.overlayCanvas = new Canvas[2];     // Catch: Throwable -> L27
    L23:
        if (r3 >= 2) goto L25;
        this.overlayBitmap[r3] = Bitmap.createBitmap(r1, r2, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L27
        this.overlayCanvas[r3] = new Canvas(this.overlayBitmap[r3]);     // Catch: Throwable -> L27
        r3 = r3 + 1;     // Catch: Throwable -> L27
        goto L23
    L25:
        this.overlayMaskBitmap = Bitmap.createBitmap(r1, r2, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L27
        this.overlayMaskCanvas = new Canvas(this.overlayMaskBitmap);     // Catch: Throwable -> L27
        Paint r05 = new Paint(1);     // Catch: Throwable -> L27
        this.overlayEraserPaint = r05;     // Catch: Throwable -> L27
        r05.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));     // Catch: Throwable -> L27
        Paint r06 = new Paint(1);     // Catch: Throwable -> L27
        this.overlayMaskPaint = r06;     // Catch: Throwable -> L27
        r06.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));     // Catch: Throwable -> L27
        this.bitmapsCreated = true;     // Catch: Throwable -> L27
        return;
    L35:
        return;
    L32:
        return;
    L31:
        return;
    }

    private void destroyBitmaps() {
        if (this.bitmapsCreated == true) goto L5;
    L17:
        this.overlayCanvas = null;
        this.overlayMaskCanvas = null;
        this.bitmapsCreated = false;
        return;
    L5:
        if (this.overlayBitmap == null) goto L14;
        int r0 = 0;
    L7:
        Bitmap[] r3 = this.overlayBitmap;
        if (r0 >= r3.length) goto L13;
        Bitmap r32 = r3[r0];
        if (r32 == null) goto L12;
        r32.recycle();
        this.overlayBitmap[r0] = null;
    L12:
        r0 = r0 + 1;
        goto L7
    L13:
        this.overlayBitmap = null;
    L14:
        Bitmap r02 = this.overlayMaskBitmap;
        if (r02 == null) goto L17;
        r02.recycle();
        this.overlayMaskBitmap = null;
        goto L17
    }

    @Keep
    public float getIconProgress() {
        return this.iconProgress;
    }

    @Keep
    public float getProgress() {
        return this.progress;
    }

    public boolean hasIcon() {
        if (this.iconDrawable == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
        destroyBitmaps();
    }

    @Override // android.view.View
    public void onDraw(Canvas r46) {
        if (getVisibility() == 0) goto L6;
        return;
    L6:
        if (this.overrideColorProgress == 0) goto L10;
        checkBitmaps();
        if (this.bitmapsCreated == true) goto L10;
        this.overrideColorProgress = 0;
    L10:
        boolean r8 = SwitchUiHelper.isMaterial3SwitchStyle();
        int r9 = SwitchUiHelper.getOverlayPadding();
        int r10 = SwitchUiHelper.getStateLayerRadius();
        if (r8 == true) goto L13;
    L15:
        boolean r13 = false;
    L17:
        if (r13 == false) goto L19;
        float r2 = -r10;
        Canvas r1 = r46;
        r1.saveLayerAlpha(r2, r2, getMeasuredWidth() + r10, getMeasuredHeight() + r10, (int) (this.overrideAlpha * 255.0f));
    L20:
        int r22 = AndroidUtilities.dp(31.0f);
        if (r8 == false) goto L24;
        float r16 = 8.0f;
        float r17 = 14.0f;
        float r3 = SwitchUiHelper.getThumbCenterX(getMeasuredWidth(), this.progress);
        float r19 = 1.0f;
        float r6 = 0.0f;
        float r20 = 0.0f;
        float r11 = getMeasuredHeight() / 2.0f;
        int r4 = 0;
    L25:
        int r14 = (int) r3;
        int r15 = (int) r11;
        int r28 = 0;
        int r7 = processColor(SwitchUiHelper.getUnselectedTrackColor(this.trackColorKey, this.resourcesProvider));
        int r5 = processColor(Theme.getColor(this.trackCheckedColorKey, this.resourcesProvider));
        int r23 = processColor(SwitchUiHelper.getUnselectedThumbColor(this.trackColorKey, this.thumbColorKey, this.resourcesProvider));
        int r52 = processColor(SwitchUiHelper.getSelectedThumbColor(this.thumbCheckedColorKey, this.resourcesProvider));
        int r12 = 0;
    L26:
        int r31 = r52;
        if (r12 >= 2) goto L81;
        if (r12 == 1) goto L31;
    L33:
        if (r12 != 0) goto L35;
        Canvas r53 = r1;
    L36:
        int r232 = r7;
        if (r12 != 0) goto L40;
        int r72 = r28;
    L39:
        boolean r32 = r8;
        if (r12 != 1) goto L43;
        int r24 = r10;
        this.overlayBitmap[r28].eraseColor(r28);
        this.paint.setColor(-16777216);
        float r102 = r9;
        this.overlayMaskCanvas.drawRect(r102, r102, this.overlayMaskBitmap.getWidth() - r9, this.overlayMaskBitmap.getHeight() - r9, this.paint);
        boolean r33 = r13;
        int r34 = r15;
        int r35 = r14;
        this.overlayMaskCanvas.drawCircle((this.overlayCx - getX()) + r102, (this.overlayCy - getY()) + r102, this.overlayRad, this.overlayEraserPaint);
    L44:
        int r82 = this.overrideColorProgress;
        if (r82 != 1) goto L50;
        if (r12 != 0) goto L48;
    L47:
        float r83 = r20;
    L54:
        if (r12 != 0) goto L67;
        Drawable r103 = this.iconDrawable;
        if (r103 == null) goto L67;
        int r132 = this.lastIconColor;
        if (this.isChecked == false) goto L60;
        int r142 = r5;
    L61:
        if (r132 == r142) goto L67;
        if (this.isChecked == false) goto L65;
        int r143 = r5;
    L66:
        this.lastIconColor = r143;
        r103.setColorFilter(new PorterDuffColorFilter(r143, PorterDuff.Mode.MULTIPLY));
        goto L67
    L65:
        r143 = r232;
        goto L66
    L60:
        r142 = r232;
    L67:
        int r104 = Color.red(r232);
        int r133 = Color.red(r5);
        int r144 = Color.green(r232);
        int r152 = Color.green(r5);
        float r25 = r83;
        int r84 = Color.blue(r232);
        int r26 = Color.blue(r5);
        int r105 = (((int) (r104 + ((r133 - r104) * r25))) & 255) << 16;
        int r85 = ((int) (r84 + ((r26 - r84) * r25))) & 255;
        int r86 = r85 | ((r105 | ((((int) (Color.alpha(r232) + ((Color.alpha(r5) - r13) * r25))) & 255) << 24)) | ((((int) (r144 + ((r152 - r144) * r25))) & 255) << 8));
        this.paint.setColor(r86);
        this.paint2.setColor(r86);
        RectF r87 = this.rectF;
        if (r32 == false) goto L72;
        SwitchUiHelper.setTrackBounds(r87, getMeasuredWidth(), getMeasuredHeight());
        float r73 = r72;
        this.rectF.offset(r73, r73);
        float r74 = this.rectF.height() / 2.0f;
        r53.drawRoundRect(this.rectF, r74, r74, this.paint);
        float r88 = r19 - r25;
        if (r88 <= r20) goto L73;
        float r106 = SwitchUiHelper.getTrackOutlineWidth();
        this.outlinePaint.setColor(Theme.multAlpha(r23, r88));
        this.outlinePaint.setStrokeWidth(r106);
        float r107 = r106 / 2.0f;
        this.rectF.inset(r107, r107);
        float r75 = r74 - r107;
        r53.drawRoundRect(this.rectF, r75, r75, this.outlinePaint);
    L73:
        if (r12 != 0) goto L78;
        RippleDrawable r76 = this.rippleDrawable;
        if (r76 == null) goto L78;
        r76.setBounds(r35 - r24, r34 - r24, r35 + r24, r34 + r24);
        this.rippleDrawable.draw(r53);
    L80:
        r12 = r12 + 1;
        r7 = r232;
        r10 = r24;
        r52 = r31;
        r8 = r32;
        r13 = r33;
        r15 = r34;
        r14 = r35;
        r28 = 0;
    L78:
        if (r12 != 1) goto L80;
        float r89 = -r9;
        r53.drawBitmap(this.overlayMaskBitmap, r89, r89, this.overlayMaskPaint);
        goto L80
    L72:
        r87.set(r4, r6, r4 + r22, AndroidUtilities.dpf2(r17) + r6);
        r53.drawRoundRect(this.rectF, AndroidUtilities.dpf2(7.0f), AndroidUtilities.dpf2(7.0f), this.paint);
        r53.drawCircle(r35 + r72, r34 + r72, AndroidUtilities.dpf2(10.0f), this.paint);
    L48:
        r83 = r19;
        goto L54
    L50:
        if (r82 != 2) goto L53;
        if (r12 != 0) goto L47;
    L53:
        r83 = Utilities.clamp01(this.progress);
        goto L54
    L43:
        r24 = r10;
        r33 = r13;
        r35 = r14;
        r34 = r15;
        goto L44
    L40:
        r72 = r9;
        goto L39
    L35:
        r53 = this.overlayCanvas[r28];
        goto L36
    L31:
        if (this.overrideColorProgress != 0) goto L33;
        r232 = r7;
        r32 = r8;
        r24 = r10;
        r33 = r13;
        r35 = r14;
        r34 = r15;
        goto L80
    L81:
        boolean r322 = r8;
        boolean r332 = r13;
        int r352 = r14;
        int r342 = r15;
        if (this.overrideColorProgress == 0) goto L84;
        float r62 = -r9;
        r1.drawBitmap(this.overlayBitmap[0], r62, r62, null);
    L84:
        int r42 = 2;
        int r108 = 0;
    L85:
        if (r108 >= r42) goto L170;
        if (r108 == 1) goto L89;
    L91:
        if (r108 != 0) goto L93;
        Canvas r43 = r1;
    L94:
        if (r108 != 0) goto L96;
        int r63 = 0;
    L97:
        if (r108 != 1) goto L99;
        this.overlayBitmap[1].eraseColor(0);
    L99:
        int r77 = this.overrideColorProgress;
        if (r77 != 1) goto L105;
        if (r108 != 0) goto L103;
    L102:
        float r78 = r20;
    L109:
        int r810 = Color.red(r23);
        int r122 = Color.red(r31);
        int r134 = Color.green(r23);
        int r145 = Color.green(r31);
        int r153 = Color.blue(r23);
        int r172 = Color.blue(r31);
        int r54 = Color.alpha(r23);
        int r37 = r23;
        int r27 = (int) (r810 + ((r122 - r810) * r78));
        int r55 = (int) (r54 + ((Color.alpha(r31) - r54) * r78));
        int r29 = (r27 & 255) << 16;
        this.paint.setColor(((r29 | ((r55 & 255) << 24)) | ((((int) (r134 + ((r145 - r134) * r78))) & 255) << 8)) | (((int) (r153 + ((r172 - r153) * r78))) & 255));
        if (r322 == false) goto L125;
        int r210 = this.drawIconType;
        if (r210 != 1) goto L114;
    L123:
        float r432 = r19;
    L124:
        float r56 = r63;
        SwitchUiHelper.setThumbBounds(this.rectF, r3 + r56, r11 + r56, this.progress, this.isChecked, r432, this.animatorPressed.getFloatValue());
        float r211 = this.rectF.height() / 2.0f;
        r43.drawRoundRect(this.rectF, r211, r211, this.paint);
    L126:
        if (r108 == 0) goto L128;
        float r222 = r3;
        Canvas r212 = r43;
        float r233 = r11;
        int r64 = r342;
        int r38 = r352;
        int r57 = 2;
    L164:
        int r811 = 1;
    L166:
        if (r108 != r811) goto L168;
        float r44 = -r9;
        r212.drawBitmap(this.overlayMaskBitmap, r44, r44, this.overlayMaskPaint);
    L168:
        r108 = r108 + 1;
        r42 = r57;
        r342 = r64;
        r3 = r222;
        r11 = r233;
        r23 = r37;
        r352 = r38;
        goto L85
    L128:
        if (this.iconDrawable == null) goto L138;
        float r213 = this.animatorIconVisibility.getFloatValue();
        if (r213 <= r20) goto L135;
        int r58 = this.iconDrawable.getIntrinsicWidth();
        int r65 = this.iconDrawable.getIntrinsicHeight();
        if (r322 == false) goto L134;
        r213 = r213 * SwitchUiHelper.getThumbIconScale(r58, r65);
    L134:
        r1.save();
        float r79 = r58 / 2.0f;
        float r123 = r65 / 2.0f;
        r1.translate(r3 - r79, r11 - r123);
        r1.scale(r213, r213, r79, r123);
        this.iconDrawable.setBounds(0, 0, r58, r65);
        this.iconDrawable.draw(r1);
        r1.restore();
    L135:
        r222 = r3;
        r212 = r43;
        r233 = r11;
        r64 = r342;
        r38 = r352;
    L136:
        r57 = 2;
    L137:
        r811 = 1;
        goto L166
    L138:
        int r214 = this.drawIconType;
        int r812 = 1;
        if (r214 != 1) goto L143;
        if (r322 == false) goto L142;
        this.paint2.setStrokeWidth(SwitchUiHelper.getIconStrokeWidth());
        SwitchUiHelper.setCheckIconLines(this.iconLines, r3, r11, this.progress);
        r43.drawLines(this.iconLines, this.paint2);
        goto L135
    L142:
        r812 = 1;
    L143:
        if (r214 != r812) goto L145;
        this.paint2.setStrokeWidth(AndroidUtilities.dp(2.0f));
        int r59 = r352;
        int r215 = (int) (r59 - (AndroidUtilities.dp(10.8f) - (AndroidUtilities.dp(1.3f) * this.progress)));
        r64 = r342;
        int r710 = (int) (r64 - (AndroidUtilities.dp(8.5f) - (AndroidUtilities.dp(0.5f) * this.progress)));
        int r813 = ((int) AndroidUtilities.dpf2(4.6f)) + r215;
        int r124 = (int) (AndroidUtilities.dpf2(9.5f) + r710);
        int r135 = AndroidUtilities.dp(2.0f) + r813;
        int r146 = AndroidUtilities.dp(2.0f) + r124;
        int r154 = ((int) AndroidUtilities.dpf2(7.5f)) + r215;
        int r216 = ((int) AndroidUtilities.dpf2(5.4f)) + r710;
        float r353 = r3;
        int r36 = r154 + AndroidUtilities.dp(7.0f);
        Canvas r223 = r43;
        int r45 = r216 + AndroidUtilities.dp(7.0f);
        r38 = r59;
        float r510 = r154;
        float r814 = r813 - r154;
        float r155 = this.progress;
        r223.drawLine((int) (r510 + (r814 * r155)), (int) (r216 + ((r124 - r216) * r155)), (int) (r36 + ((r135 - r36) * r155)), (int) (r45 + ((r146 - r45) * r155)), this.paint2);
        int r217 = ((int) AndroidUtilities.dpf2(7.5f)) + r215;
        int r39 = ((int) AndroidUtilities.dpf2(12.5f)) + r710;
        r223.drawLine(r217, r39, AndroidUtilities.dp(7.0f) + r217, r39 - AndroidUtilities.dp(7.0f), this.paint2);
        r233 = r11;
        r212 = r223;
        r222 = r353;
        goto L136
    L145:
        Canvas r21 = r43;
        r64 = r342;
        r38 = r352;
        r57 = 2;
        float r354 = r3;
        if (r214 != 2) goto L148;
    L151:
        this.paint2.setAlpha((int) ((r19 - this.iconProgress) * 255.0f));
        Paint r218 = this.paint2;
        if (r322 == false) goto L154;
        float r310 = SwitchUiHelper.getIconStrokeWidth();
    L155:
        r218.setStrokeWidth(r310);
        if (r322 == false) goto L158;
        float r219 = SwitchUiHelper.getClockHandLength(true);
    L159:
        float r234 = r11;
        r21.drawLine(r354, r234, r354, r11 - r219, this.paint2);
        r212 = r21;
        r212.save();
        r212.rotate(this.iconProgress * (-90.0f), r354, r234);
        if (r322 == false) goto L162;
        float r711 = SwitchUiHelper.getClockHandLength(false);
    L163:
        r222 = r354;
        r233 = r234;
        r212.drawLine(r222, r233, r354 + r711, r234, this.paint2);
        r212.restore();
        goto L164
    L162:
        r711 = AndroidUtilities.dp(4.0f);
        goto L163
    L158:
        r219 = AndroidUtilities.dp(5.0f);
        goto L159
    L154:
        r310 = AndroidUtilities.dp(2.0f);
        goto L155
    L148:
        if (this.iconAnimator != null) goto L151;
        r233 = r11;
        r212 = r21;
        r222 = r354;
        goto L137
    L114:
        if (r210 == 2) goto L123;
        if (this.iconAnimator != null) goto L123;
        if (this.iconDrawable == null) goto L121;
        float r220 = this.animatorIconVisibility.getFloatValue();
    L122:
        r432 = r220;
        goto L124
    L121:
        r220 = r20;
        goto L122
    L125:
        r43.drawCircle(r352 + r63, r342 + r63, AndroidUtilities.dp(r16), this.paint);
    L103:
        r78 = r19;
        goto L109
    L105:
        if (r77 != 2) goto L108;
        if (r108 != 0) goto L102;
    L108:
        r78 = Utilities.clamp01(this.progress);
        goto L109
    L96:
        r63 = r9;
        goto L97
    L93:
        r43 = this.overlayCanvas[1];
        goto L94
    L89:
        if (this.overrideColorProgress != 0) goto L91;
        r37 = r23;
        r222 = r3;
        r233 = r11;
        r64 = r342;
        r38 = r352;
        r57 = 2;
        goto L168
    L170:
        if (this.overrideColorProgress == 0) goto L172;
        float r221 = -r9;
        r1.drawBitmap(this.overlayBitmap[1], r221, r221, null);
    L172:
        if (r332 == false) goto L184;
        r1.restore();
        return;
    L184:
        return;
    L24:
        r16 = 8.0f;
        r17 = 14.0f;
        int r311 = (getMeasuredWidth() - r22) / 2;
        float r47 = (getMeasuredHeight() - AndroidUtilities.dpf2(14.0f)) / 2.0f;
        r20 = 0.0f;
        r19 = 1.0f;
        float r66 = (r311 + AndroidUtilities.dp(8.0f)) + ((int) (AndroidUtilities.dp(16.0f) * this.progress));
        r11 = getMeasuredHeight() / 2;
        r4 = r311;
        r3 = r66;
        r6 = r47;
        goto L25
    L19:
        r1 = r46;
        goto L20
    L13:
        if (this.overrideAlpha >= 1.0f) goto L15;
        r13 = true;
        goto L17
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.Switch");
        r2.setCheckable(true);
        r2.setChecked(this.isChecked);
    }

    public int processColor(int r1) {
        return r1;
    }

    @Override // android.view.View
    public void setAlpha(float r3) {
        if (SwitchUiHelper.isMaterial3SwitchStyle() == false) goto L6;
        this.overrideAlpha = r3;
        super.setAlpha(1.0f);
        invalidate();
        return;
    L6:
        this.overrideAlpha = 1.0f;
        super.setAlpha(r3);
    }

    public void setChecked(boolean r2, int r3, boolean r4) {
        if (r2 == this.isChecked) goto L13;
        this.isChecked = r2;
        if (this.attachedToWindow == false) goto L8;
        if (r4 == false) goto L8;
        animateToCheckedState(r2);
    L8:
        cancelCheckAnimator();
        if (r2 == false) goto L11;
        float r22 = 1.0f;
    L12:
        setProgress(r22);
        goto L13
    L11:
        r22 = 0.0f;
    L13:
        setDrawIconType(r3, r4);
    }

    public void setChecked(boolean r2, boolean r3) {
        setChecked(r2, this.drawIconType, r3);
    }

    public void setColors(int r1, int r2, int r3, int r4) {
        this.trackColorKey = r1;
        this.trackCheckedColorKey = r2;
        this.thumbColorKey = r3;
        this.thumbCheckedColorKey = r4;
    }

    public void setDrawIconType(int r1) {
        this.drawIconType = r1;
    }

    public void setDrawIconType(int r2, boolean r3) {
        if (this.drawIconType == r2) goto L18;
        this.drawIconType = r2;
        if (this.attachedToWindow == false) goto L12;
        if (r3 == false) goto L12;
        if (r2 != 0) goto L9;
        boolean r22 = true;
    L10:
        animateIcon(r22);
        return;
    L9:
        r22 = false;
    L12:
        cancelIconAnimator();
        if (r2 != 0) goto L15;
        float r23 = 1.0f;
    L16:
        setIconProgress(r23);
        return;
    L15:
        r23 = 0.0f;
        goto L16
    }

    public void setDrawRipple(boolean r6) {
        if (r6 != this.drawRipple) goto L5;
        return;
    L5:
        this.drawRipple = r6;
        int r1 = 1;
        if (this.rippleDrawable != null) goto L8;
        Paint r0 = new Paint(1);
        this.ripplePaint = r0;
        r0.setColor(-1);
        BaseCell.RippleDrawableSafe r2 = new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{0}), null, null);
        this.rippleDrawable = r2;
        r2.setCallback(this);
    L8:
        this.rippleDrawable.setRadius(SwitchUiHelper.getStateLayerRadius());
        boolean r02 = this.isChecked;
        if (r02 == true) goto L11;
    L12:
        if (r02 == false) goto L14;
    L23:
        if (Build.VERSION.SDK_INT < 28) goto L30;
        if (r6 == false) goto L30;
        RippleDrawable r03 = this.rippleDrawable;
        if (this.isChecked == false) goto L28;
        float r12 = 0.0f;
    L29:
        r03.setHotspot(r12, AndroidUtilities.dp(18.0f));
        goto L30
    L28:
        r12 = AndroidUtilities.dp(100.0f);
    L30:
        RippleDrawable r04 = this.rippleDrawable;
        if (r6 == false) goto L33;
        int[] r13 = this.pressedState;
    L34:
        r04.setState(r13);
        this.animatorPressed.setValue(r6, this.attachedToWindow);
        invalidate();
        return;
    L33:
        r13 = StateSet.NOTHING;
        goto L34
    L14:
        if (this.colorSet == 1) goto L23;
    L15:
        if (r02 == false) goto L17;
        int r05 = Theme.key_switchTrackBlueSelectorChecked;
    L18:
        this.rippleDrawable.setColor(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{processColor(Theme.getColor(r05, this.resourcesProvider))}));
        if (this.isChecked == false) goto L21;
        r1 = 2;
    L21:
        this.colorSet = r1;
        goto L23
    L17:
        r05 = Theme.key_switchTrackBlueSelector;
        goto L18
    L11:
        if (this.colorSet != 2) goto L15;
        goto L12
    }

    public void setIcon(int r4) {
        if (r4 == 0) goto L10;
        Drawable r42 = getResources().getDrawable(r4).mutate();
        this.iconDrawable = r42;
        if (r42 != null) goto L6;
    L11:
        invalidate();
        return;
    L6:
        if (this.isChecked == false) goto L8;
        int r1 = this.trackCheckedColorKey;
    L9:
        int r12 = Theme.getColor(r1, this.resourcesProvider);
        this.lastIconColor = r12;
        r42.setColorFilter(new PorterDuffColorFilter(r12, PorterDuff.Mode.MULTIPLY));
        goto L11
    L8:
        r1 = this.trackColorKey;
        goto L9
    L10:
        this.iconDrawable = null;
        goto L11
    }

    @Keep
    public void setIconProgress(float r2) {
        if (this.iconProgress != r2) goto L5;
        return;
    L5:
        this.iconProgress = r2;
        invalidate();
    }

    public void setIconVisible(boolean r1, boolean r2) {
        this.animatorIconVisibility.setValue(r1, r2);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener r1) {
    }

    public void setOverrideColor(int r2) {
        if (this.overrideColorProgress != r2) goto L5;
        return;
    L5:
        this.overrideColorProgress = r2;
        this.overlayCx = 0.0f;
        this.overlayCy = 0.0f;
        this.overlayRad = 0.0f;
        invalidate();
    }

    public void setOverrideColorProgress(float r1, float r2, float r3) {
        this.overlayCx = r1;
        this.overlayCy = r2;
        this.overlayRad = r3;
        invalidate();
    }

    @Keep
    public void setProgress(float r2) {
        if (this.progress != r2) goto L5;
        return;
    L5:
        this.progress = r2;
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == true) goto L10;
        RippleDrawable r1 = this.rippleDrawable;
        if (r1 == null) goto L8;
        if (r2 == r1) goto L13;
        return false;
    L13:
        return true;
    L8:
        return false;
    L10:
        return true;
    }
}
