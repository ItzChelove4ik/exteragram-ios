package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.ui.MaterialSliderUiHelper;
import com.google.android.material.slider.Slider;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class SlideChooseView extends FrameLayout {
    private final SeekBarAccessibilityDelegate accessibilityDelegate;
    private boolean allowSlide;
    private Callback callback;
    private int circleSize;
    private final View contentView;
    private int dashedFrom;
    private int gapSize;
    private int lastDash;
    private Drawable[] leftDrawables;
    private Paint linePaint;
    private int lineSize;
    private Slider materialSlider;
    private int minIndex;
    private boolean moving;
    private AnimatedFloat movingAnimatedHolder;
    private boolean needDivider;
    private int[] optionsSizes;
    private String[] optionsStr;
    private Paint paint;
    private final Theme.ResourcesProvider resourcesProvider;
    private int selectedIndex;
    private AnimatedFloat selectedIndexAnimatedHolder;
    private float selectedIndexTouch;
    private int sideSide;
    private boolean startMoving;
    private int startMovingPreset;
    private TextPaint textPaint;
    private boolean touchWasClose;
    private float xTouchDown;
    private float yTouchDown;

    /* renamed from: org.telegram.ui.Components.SlideChooseView$1, reason: invalid class name */
    public class AnonymousClass1 extends IntSeekBarAccessibilityDelegate {
        final /* synthetic */ SlideChooseView this$0;

        public AnonymousClass1(SlideChooseView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
        public CharSequence getContentDescription(View r2) {
            if (SlideChooseView.m13032$$Nest$fgetselectedIndex(this.this$0) < SlideChooseView.m13031$$Nest$fgetoptionsStr(this.this$0).length) goto L5;
            return null;
        L5:
            return SlideChooseView.m13031$$Nest$fgetoptionsStr(this.this$0)[SlideChooseView.m13032$$Nest$fgetselectedIndex(this.this$0)];
        }

        @Override // org.telegram.ui.Components.IntSeekBarAccessibilityDelegate
        public int getMaxValue() {
            return SlideChooseView.m13031$$Nest$fgetoptionsStr(this.this$0).length - 1;
        }

        @Override // org.telegram.ui.Components.IntSeekBarAccessibilityDelegate
        public int getProgress() {
            return SlideChooseView.m13032$$Nest$fgetselectedIndex(this.this$0);
        }

        @Override // org.telegram.ui.Components.IntSeekBarAccessibilityDelegate
        public void setProgress(int r1) {
            SlideChooseView.m13034$$Nest$msetOption(this.this$0, r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.SlideChooseView$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ SlideChooseView this$0;

        public AnonymousClass2(SlideChooseView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r1) {
            SlideChooseView.m13033$$Nest$mdrawContent(this.this$0, r1);
        }
    }

    public interface Callback {
        void onOptionSelected(int r1);

        default void onTouchEnd() {
        }
    }

    /* renamed from: -$$Nest$fgetoptionsStr, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String[] m13031$$Nest$fgetoptionsStr(SlideChooseView r0) {
        return r0.optionsStr;
    }

    /* renamed from: -$$Nest$fgetselectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13032$$Nest$fgetselectedIndex(SlideChooseView r0) {
        return r0.selectedIndex;
    }

    /* renamed from: -$$Nest$mdrawContent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13033$$Nest$mdrawContent(SlideChooseView r0, Canvas r1) {
        r0.drawContent(r1);
    }

    /* renamed from: -$$Nest$msetOption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13034$$Nest$msetOption(SlideChooseView r0, int r1) {
        r0.setOption(r1);
    }

    public SlideChooseView(Context r2) {
        this(r2, null);
    }

    public SlideChooseView(Context r7, Theme.ResourcesProvider r8) {
        super(r7);
        this.dashedFrom = -1;
        this.needDivider = false;
        this.minIndex = Integer.MIN_VALUE;
        CubicBezierInterpolator r3 = CubicBezierInterpolator.DEFAULT;
        this.selectedIndexAnimatedHolder = new AnimatedFloat(this, 120, r3);
        this.movingAnimatedHolder = new AnimatedFloat(this, 150, r3);
        this.touchWasClose = false;
        this.allowSlide = true;
        this.resourcesProvider = r8;
        this.paint = new Paint(1);
        this.textPaint = new TextPaint(1);
        Paint r82 = new Paint(1);
        this.linePaint = r82;
        r82.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
        this.textPaint.setTextSize(AndroidUtilities.dp(13.0f));
        this.accessibilityDelegate = new AnonymousClass1(this);
        AnonymousClass2 r83 = new AnonymousClass2(this, r7);
        this.contentView = r83;
        r83.setImportantForAccessibility(2);
        addView(r83, LayoutHelper.createFrame(-1, -1.0f));
    }

    private boolean canUseMaterialSlider() {
        if (ExteraConfig.getNewSliderStyle() == false) goto L13;
        String[] r0 = this.optionsStr;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.length > 1) goto L9;
        return false;
    L9:
        if (this.dashedFrom < 0) goto L11;
        return false;
    L11:
        if (this.minIndex > 0) goto L18;
        return true;
    L18:
        return false;
    L13:
        return false;
    }

    private void drawContent(Canvas r27) {
        Canvas r1 = r27;
        updateMaterialSliderState();
        boolean r7 = isUsingMaterialSlider();
        float r8 = this.selectedIndexAnimatedHolder.set(this.selectedIndex);
        if (r7 == false) goto L5;
        updateMaterialSliderValue(r8);
    L5:
        AnimatedFloat r2 = this.movingAnimatedHolder;
        float r9 = 0.0f;
        float r10 = 1.0f;
        if (this.moving == false) goto L8;
        float r3 = 1.0f;
    L9:
        float r11 = r2.set(r3);
        int r12 = 2;
        int r13 = (getMeasuredHeight() / 2) + AndroidUtilities.dp(11.0f);
        int r15 = 0;
    L10:
        float r17 = 2.0f;
        if (r15 >= this.optionsStr.length) goto L63;
        int r22 = this.sideSide;
        int r5 = this.lineSize + (this.gapSize * r12);
        int r6 = this.circleSize;
        int r23 = (r22 + ((r5 + r6) * r15)) + (r6 / r12);
        float r52 = r15;
        float r62 = r52 - r8;
        float r32 = Math.max(r9, r10 - Math.abs(r62));
        float r53 = MathUtils.clamp((r8 - r52) + r10, r9, r10);
        int r14 = getThemedColor(Theme.key_switchTrack);
        float r21 = r10;
        int r102 = getThemedColor(Theme.key_switchTrackChecked);
        int r92 = this.minIndex;
        int r222 = r12;
        if (r92 == Integer.MIN_VALUE) goto L16;
        if (r15 > r92) goto L16;
        float r93 = 0.5f;
    L17:
        int r54 = ColorUtils.blendARGB(r14, Theme.multAlpha(r102, r93), r53);
        if (this.allowSlide == true) goto L21;
        r54 = AndroidUtilities.getTransparentColor(r54, 0.5f);
    L21:
        if (r7 == true) goto L34;
        this.paint.setColor(r54);
        this.linePaint.setColor(r54);
        float r103 = r13;
        r1.drawCircle(r23, r103, AndroidUtilities.lerp(this.circleSize / 2.0f, AndroidUtilities.dpf2(6.0f), r32), this.paint);
        if (r15 == 0) goto L34;
        int r55 = (r23 - (this.circleSize / 2)) - this.gapSize;
        int r122 = this.lineSize;
        int r56 = r55 - r122;
        int r142 = this.dashedFrom;
        if (r142 != (-1)) goto L27;
    L33:
        int r25 = r23;
        float r24 = 3.0f;
        float r232 = 0.5f;
        float r94 = r32;
        float r16 = r62 - r21;
        float r104 = MathUtils.clamp(r21 - Math.abs(r16), 0.0f, r21);
        int r18 = (int) (r122 - (AndroidUtilities.dpf2(3.0f) * MathUtils.clamp(r21 - Math.min(Math.abs(r62), Math.abs(r16)), 0.0f, r21)));
        r21 = 1.0f;
        r1 = r27;
        r1.drawRect((int) (r56 + (AndroidUtilities.dpf2(3.0f) * r104)), r103 - AndroidUtilities.dpf2(1.0f), r18 + r2, r103 + AndroidUtilities.dpf2(1.0f), this.paint);
    L35:
        int r26 = this.optionsSizes[r15];
        String r33 = this.optionsStr[r15];
        TextPaint r4 = this.textPaint;
        int r57 = ColorUtils.blendARGB(getThemedColor(Theme.key_windowBackgroundWhiteGrayText), getThemedColor(Theme.key_windowBackgroundWhiteBlueText), r94);
        if (this.allowSlide == false) goto L38;
        float r63 = r21;
    L39:
        r4.setColor(AndroidUtilities.getTransparentColor(r57, r63));
        if (this.leftDrawables == null) goto L52;
        r1.save();
        if (r15 != 0) goto L45;
        r1.translate(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(15.5f));
    L48:
        this.leftDrawables[r15].setColorFilter(this.textPaint.getColor(), PorterDuff.Mode.MULTIPLY);
        this.leftDrawables[r15].draw(r1);
        r1.restore();
        r1.save();
        float r42 = this.leftDrawables[r15].getIntrinsicWidth() / 2.0f;
        if (r15 != 0) goto L51;
        r17 = r24;
    L51:
        r9 = 0.0f;
        r1.translate(r42 - AndroidUtilities.dp(r17), 0.0f);
    L53:
        if (r15 != 0) goto L56;
        r1.drawText(r33, AndroidUtilities.dp(22.0f), AndroidUtilities.dp(28.0f), this.textPaint);
    L60:
        if (this.leftDrawables == null) goto L62;
        r1.restore();
    L62:
        r15 = r15 + 1;
        r10 = r21;
        r12 = 2;
        goto L10
    L56:
        if (r15 != (this.optionsStr.length - 1)) goto L58;
        r1.drawText(r33, (getMeasuredWidth() - r26) - AndroidUtilities.dp(22.0f), AndroidUtilities.dp(28.0f), this.textPaint);
        goto L60
    L58:
        r1.drawText(r33, r25 - (r26 / 2), AndroidUtilities.dp(28.0f), this.textPaint);
        goto L60
    L45:
        if (r15 != (this.optionsStr.length - 1)) goto L47;
        r1.translate(((getMeasuredWidth() - r26) - AndroidUtilities.dp(22.0f)) - AndroidUtilities.dp(10.0f), AndroidUtilities.dp(28.0f) - AndroidUtilities.dp(12.5f));
        goto L48
    L47:
        r1.translate((r25 - (r26 / 2)) - AndroidUtilities.dp(10.0f), AndroidUtilities.dp(28.0f) - AndroidUtilities.dp(12.5f));
        goto L48
    L52:
        r9 = 0.0f;
        goto L53
    L38:
        r63 = r232;
        goto L39
    L27:
        if ((r15 - 1) < r142) goto L33;
        int r43 = (int) (r56 + AndroidUtilities.dpf2(3.0f));
        float r64 = (int) (r122 - AndroidUtilities.dpf2(3.0f));
        float r123 = r64 / AndroidUtilities.dpf2(13.0f);
        if (this.lastDash == r123) goto L31;
        float r65 = (r64 - (AndroidUtilities.dpf2(8.0f) * r123)) / (r123 - r21);
        Paint r143 = this.linePaint;
        r24 = 3.0f;
        r25 = r23;
        float[] r28 = new float[r222];
        r28[0] = AndroidUtilities.dpf2(6.0f);
        r28[1] = r65;
        r143.setPathEffect(new DashPathEffect(r28, 0.0f));
        this.lastDash = (int) r123;
    L32:
        r1 = r27;
        r94 = r32;
        r232 = 0.5f;
        r1.drawLine(AndroidUtilities.dpf2(r21) + r43, r103, (r43 + r5) - AndroidUtilities.dpf2(r21), r103, this.linePaint);
        goto L35
    L31:
        r25 = r23;
        r24 = 3.0f;
    L34:
        r25 = r23;
        r232 = 0.5f;
        r24 = 3.0f;
        r94 = r32;
    L16:
        r93 = r21;
        goto L17
    L63:
        float r212 = r10;
        if (r7 == true) goto L75;
        float r29 = this.sideSide;
        int r34 = this.lineSize + (this.gapSize * 2);
        float r210 = (r29 + ((r34 + r4) * r8)) + (this.circleSize / 2.0f);
        Paint r35 = this.paint;
        int r44 = Theme.key_switchTrackChecked;
        int r58 = ColorUtils.setAlphaComponent(getThemedColor(r44), 80);
        if (this.allowSlide == false) goto L68;
        float r66 = r212;
    L69:
        r35.setColor(AndroidUtilities.getTransparentColor(r58, r66));
        float r36 = r13;
        r1.drawCircle(r210, r36, AndroidUtilities.dp(r11 * 12.0f), this.paint);
        Paint r59 = this.paint;
        int r45 = getThemedColor(r44);
        if (this.allowSlide == false) goto L72;
        float r105 = r212;
    L73:
        r59.setColor(AndroidUtilities.getTransparentColor(r45, r105));
        r1.drawCircle(r210, r36, AndroidUtilities.dp(6.0f), this.paint);
        goto L75
    L72:
        r105 = 0.5f;
        goto L73
    L68:
        r66 = 0.5f;
    L75:
        if (this.needDivider == true) goto L77;
        return;
    L77:
        if (LocaleController.isRTL == true) goto L80;
        r9 = AndroidUtilities.dp(21.0f);
    L80:
        float r211 = getMeasuredHeight() - 1;
        int r46 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L83;
        int r144 = AndroidUtilities.dp(21.0f);
    L84:
        r1.drawLine(r9, r211, r46 - r144, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L83:
        r144 = 0;
        goto L84
    L8:
        r3 = 0.0f;
        goto L9
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void initMaterialSlider(Context r9) {
        Slider r92 = MaterialSliderUiHelper.create(r9);
        this.materialSlider = r92;
        r92.setImportantForAccessibility(2);
        this.materialSlider.setFocusable(false);
        this.materialSlider.setFocusableInTouchMode(false);
        this.materialSlider.setLayoutDirection(0);
        this.materialSlider.setVisibility(8);
        addView(this.materialSlider, 0, LayoutHelper.createFrame(-1, 48.0f, 48, 6.0f, 24.0f, 6.0f, 0.0f));
    }

    private boolean isUsingMaterialSlider() {
        Slider r0 = this.materialSlider;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getVisibility() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    private void setOption(int r2) {
        String[] r0 = this.optionsStr;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.length != 0) goto L8;
        return;
    L8:
        if (this.selectedIndex == r2) goto L10;
        AndroidUtilities.vibrateCursor(this);
    L10:
        this.selectedIndex = r2;
        Callback r02 = this.callback;
        if (r02 == null) goto L13;
        r02.onOptionSelected(r2);
    L13:
        invalidate();
    }

    private void updateMaterialSliderColors() {
        if (this.allowSlide == false) goto L5;
        int r0 = 255;
    L6:
        int r1 = ColorUtils.setAlphaComponent(getThemedColor(Theme.key_switchTrackChecked), r0);
        int r2 = ColorUtils.setAlphaComponent(getThemedColor(Theme.key_switchTrack), r0);
        int r02 = ColorUtils.setAlphaComponent(getThemedColor(Theme.key_windowBackgroundWhite), r0);
        MaterialSliderUiHelper.applyDiscreteColors(this.materialSlider, r1, r2, r02);
        return;
    L5:
        r0 = 128;
        goto L6
    }

    private void updateMaterialSliderState() {
        boolean r0 = canUseMaterialSlider();
        if (r0 == true) goto L5;
    L7:
        Slider r1 = this.materialSlider;
        if (r1 == null) goto L43;
        boolean r2 = false;
        if (r0 == false) goto L13;
        int r3 = 0;
    L15:
        if (r1.getVisibility() == r3) goto L19;
        this.materialSlider.setVisibility(r3);
        View r12 = this.contentView;
        if (r12 == null) goto L19;
        r12.invalidate();
    L19:
        if (r0 == true) goto L21;
        return;
    L21:
        if (isEnabled() == false) goto L26;
        if (this.allowSlide == false) goto L26;
        r2 = true;
    L26:
        if (this.materialSlider.isEnabled() == r2) goto L28;
        this.materialSlider.setEnabled(r2);
    L28:
        float r02 = this.optionsStr.length - 1;
        if (this.materialSlider.getValue() <= r02) goto L32;
        updateMaterialSliderValue(r02);
    L32:
        if (this.materialSlider.getValueFrom() == 0.0f) goto L35;
        this.materialSlider.setValueFrom(0.0f);
    L35:
        if (this.materialSlider.getValueTo() == r02) goto L38;
        this.materialSlider.setValueTo(r02);
    L38:
        if (this.materialSlider.getStepSize() == 0.0f) goto L40;
        this.materialSlider.setStepSize(0.0f);
    L40:
        MaterialSliderUiHelper.applyDiscreteStyle(this.materialSlider, this.optionsStr.length);
        updateMaterialSliderColors();
        return;
    L13:
        r3 = 8;
        goto L15
    L43:
        return;
    L5:
        if (this.materialSlider != null) goto L7;
        initMaterialSlider(getContext());
        goto L7
    }

    private void updateMaterialSliderValue(float r4) {
        Slider r0 = this.materialSlider;
        if (r0 == null) goto L11;
        String[] r3 = this.optionsStr;
        if (r3 != null) goto L7;
        return;
    L7:
        if (r3.length < 2) goto L13;
        MaterialSliderUiHelper.setValue(r0, MathUtils.clamp(r4, 0.0f, r3.length - 1));
        return;
    L13:
        return;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        View r0 = this.contentView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        this.accessibilityDelegate.onInitializeAccessibilityNodeInfoInternal(this, r2);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        updateMaterialSliderState();
        if (isUsingMaterialSlider() == false) goto L5;
        return true;
    L5:
        if (super.onInterceptTouchEvent(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        updateMaterialSliderState();
        super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(74.0f), TLObject.FLAG_30));
        this.circleSize = AndroidUtilities.dp(6.0f);
        this.gapSize = AndroidUtilities.dp(2.0f);
        this.sideSide = AndroidUtilities.dp(22.0f);
        int r42 = getMeasuredWidth();
        int r52 = this.circleSize;
        String[] r0 = this.optionsStr;
        this.lineSize = (((r42 - (r52 * r0.length)) - ((this.gapSize * 2) * (r0.length - 1))) - (this.sideSide * 2)) / Math.max(1, r0.length - 1);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r10) {
        updateMaterialSliderState();
        if (this.allowSlide == true) goto L5;
        return true;
    L5:
        float r0 = r10.getX();
        float r2 = r10.getY();
        float r3 = MathUtils.clamp(((r0 - this.sideSide) + (this.circleSize / 2.0f)) / ((this.lineSize + (this.gapSize * 2)) + r4), 0.0f, this.optionsStr.length - 1);
        if (Math.abs(r3 - Math.round(r3)) >= 0.35f) goto L8;
        boolean r4 = true;
    L9:
        if (r4 == false) goto L11;
        r3 = Math.round(r3);
    L11:
        int r6 = this.minIndex;
        if (r6 == Integer.MIN_VALUE) goto L15;
        r3 = Math.max(r3, r6);
    L15:
        if (r10.getAction() != 0) goto L18;
        this.xTouchDown = r0;
        this.yTouchDown = r2;
        this.selectedIndexTouch = r3;
        this.startMovingPreset = this.selectedIndex;
        this.startMoving = true;
        invalidate();
    L54:
        return true;
    L18:
        if (r10.getAction() != 2) goto L37;
        if (this.moving == true) goto L25;
        if (Math.abs(this.xTouchDown - r0) <= Math.abs(this.yTouchDown - r2)) goto L25;
        getParent().requestDisallowInterceptTouchEvent(true);
    L25:
        if (this.startMoving == false) goto L30;
        if (Math.abs(this.xTouchDown - r0) < AndroidUtilities.touchSlop) goto L30;
        this.moving = true;
        this.startMoving = false;
    L30:
        if (this.moving == false) goto L35;
        this.selectedIndexTouch = r3;
        invalidate();
        if (Math.round(this.selectedIndexTouch) == this.selectedIndex) goto L35;
        if (r4 == false) goto L35;
        setOption(Math.round(this.selectedIndexTouch));
    L35:
        invalidate();
        goto L54
    L37:
        if (r10.getAction() == 1) goto L41;
        if (r10.getAction() != 3) goto L54;
    L41:
        if (this.moving == true) goto L47;
        this.selectedIndexTouch = r3;
        if (r10.getAction() == 1) goto L45;
    L50:
        Callback r102 = this.callback;
        if (r102 == null) goto L53;
        r102.onTouchEnd();
    L53:
        this.startMoving = false;
        this.moving = false;
        invalidate();
        getParent().requestDisallowInterceptTouchEvent(false);
        goto L54
    L45:
        if (Math.round(this.selectedIndexTouch) == this.selectedIndex) goto L50;
        setOption(Math.round(this.selectedIndexTouch));
        goto L50
    L47:
        int r103 = this.selectedIndex;
        if (r103 == this.startMovingPreset) goto L50;
        setOption(r103);
        goto L50
    L8:
        r4 = false;
        goto L9
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int r2, Bundle r3) {
        if (super.performAccessibilityAction(r2, r3) == false) goto L5;
        return true;
    L5:
        if (this.accessibilityDelegate.performAccessibilityActionInternal(this, r2, r3) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void setAllowSlide(boolean r3) {
        this.allowSlide = r3;
        Slider r0 = this.materialSlider;
        if (r0 != null) goto L5;
    L10:
        invalidate();
        return;
    L5:
        if (isEnabled() == false) goto L8;
        if (r3 == false) goto L8;
        boolean r32 = true;
    L9:
        r0.setEnabled(r32);
        updateMaterialSliderColors();
    L8:
        r32 = false;
        goto L9
    }

    public void setCallback(Callback r1) {
        this.callback = r1;
    }

    public void setDashedFrom(int r1) {
        this.dashedFrom = r1;
        updateMaterialSliderState();
        invalidate();
    }

    @Override // android.view.View
    public void setEnabled(boolean r2) {
        super.setEnabled(r2);
        Slider r0 = this.materialSlider;
        if (r0 == null) goto L11;
        if (r2 == true) goto L6;
    L8:
        boolean r1 = false;
    L9:
        r0.setEnabled(r1);
        return;
    L6:
        if (this.allowSlide == false) goto L8;
        r1 = true;
        goto L9
    }

    public void setMinAllowedIndex(int r2) {
        if (r2 == (-1)) goto L8;
        if (this.optionsStr == null) goto L8;
        r2 = Math.min(r2, r0.length - 1);
    L8:
        if (this.minIndex == r2) goto L14;
        this.minIndex = r2;
        if (this.selectedIndex >= r2) goto L12;
        this.selectedIndex = r2;
    L12:
        updateMaterialSliderState();
        invalidate();
        return;
    }

    public void setNeedDivider(boolean r1) {
        this.needDivider = r1;
        invalidate();
    }

    public void setOptions(int r5, Drawable[] r6, String... r7) {
        this.optionsStr = r7;
        this.leftDrawables = r6;
        if (r7.length > 0) goto L6;
        r5 = 0;
    L6:
        this.selectedIndex = r5;
        this.optionsSizes = new int[r7.length];
        int r52 = 0;
    L8:
        if (r52 >= this.optionsStr.length) goto L10;
        this.optionsSizes[r52] = (int) Math.ceil(this.textPaint.measureText(r6[r52]));
        r52 = r52 + 1;
        goto L8
    L10:
        Drawable[] r53 = this.leftDrawables;
        if (r53 == null) goto L15;
        int r62 = r53.length;
        int r72 = 0;
    L13:
        if (r72 >= r62) goto L15;
        Drawable r1 = r53[r72];
        r1.setBounds(0, 0, r1.getIntrinsicWidth(), r1.getIntrinsicHeight());
        r72 = r72 + 1;
    L15:
        updateMaterialSliderState();
        requestLayout();
        invalidate();
    }

    public void setOptions(int r2, String... r3) {
        setOptions(r2, null, r3);
    }
}
