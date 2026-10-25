package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class VoIpSwitchLayout extends FrameLayout {
    public int animationDelay;
    private final VoIPBackgroundProvider backgroundProvider;
    private final TextView currentTextView;
    private final TextView newTextView;
    private Type type;
    private VoIpButtonView voIpButtonView;

    /* renamed from: org.telegram.ui.Components.voip.VoIpSwitchLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIpSwitchLayout this$0;
        final /* synthetic */ String val$newText;

        public AnonymousClass1(VoIpSwitchLayout r1, String r2) {
            this.this$0 = r1;
            this.val$newText = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            VoIpSwitchLayout.m14007$$Nest$fgetcurrentTextView(this.this$0).setText(this.val$newText);
            VoIpSwitchLayout.m14007$$Nest$fgetcurrentTextView(this.this$0).setTranslationY(0.0f);
            VoIpSwitchLayout.m14007$$Nest$fgetcurrentTextView(this.this$0).setAlpha(1.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIpSwitchLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIpSwitchLayout this$0;

        public AnonymousClass2(VoIpSwitchLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            VoIpSwitchLayout.m14008$$Nest$fgetnewTextView(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIpSwitchLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIpSwitchLayout this$0;
        final /* synthetic */ VoIpButtonView val$oldVoIpButton;

        public AnonymousClass3(VoIpSwitchLayout r1, VoIpButtonView r2) {
            this.this$0 = r1;
            this.val$oldVoIpButton = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.removeView(this.val$oldVoIpButton);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIpSwitchLayout$4, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$ui$Components$voip$VoIpSwitchLayout$Type = null;

        static {
            int[] r0 = new int[Type.values().length];
            $SwitchMap$org$telegram$ui$Components$voip$VoIpSwitchLayout$Type = r0;
            r0[Type.MICRO.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$org$telegram$ui$Components$voip$VoIpSwitchLayout$Type[Type.CAMERA.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$org$telegram$ui$Components$voip$VoIpSwitchLayout$Type[Type.VIDEO.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L22:
            $SwitchMap$org$telegram$ui$Components$voip$VoIpSwitchLayout$Type[Type.BLUETOOTH.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L16:
            $SwitchMap$org$telegram$ui$Components$voip$VoIpSwitchLayout$Type[Type.SPEAKER.ordinal()] = 5;     // Catch: NoSuchFieldError -> L13
            return;
        }
    }

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type BLUETOOTH = null;
        public static final Type CAMERA = null;
        public static final Type MICRO = null;
        public static final Type SPEAKER = null;
        public static final Type VIDEO = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{MICRO, CAMERA, VIDEO, BLUETOOTH, SPEAKER};
        }

        static {
            MICRO = new Type("MICRO", 0);
            CAMERA = new Type("CAMERA", 1);
            VIDEO = new Type("VIDEO", 2);
            BLUETOOTH = new Type("BLUETOOTH", 3);
            SPEAKER = new Type("SPEAKER", 4);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public static class VoIpButtonView extends View {
        private ValueAnimator animator;
        private final VoIPBackgroundProvider backgroundProvider;
        private final Path clipPath;
        private final Paint darkPaint;
        private boolean isSelectedState;
        private final Paint maskPaint;
        private final int maxRadius;
        private OnBtnClickedListener onBtnClickedListener;
        private float pressedScale;
        private ValueAnimator pressedScaleAnimator;
        private RLottieDrawable selectedIcon;
        private int selectedRadius;
        private RLottieDrawable singleIcon;
        private int singleIconBackgroundAlphaPercent;
        private float startX;
        private float startY;
        private RLottieDrawable unSelectedIcon;
        private int unselectedRadius;
        private final Paint whiteCirclePaint;

        /* renamed from: org.telegram.ui.Components.voip.VoIpSwitchLayout$VoIpButtonView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ VoIpButtonView this$0;

            public AnonymousClass1(VoIpButtonView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                VoIpButtonView.m14020$$Nest$fputunselectedRadius(this.this$0, 0);
                this.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.voip.VoIpSwitchLayout$VoIpButtonView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ VoIpButtonView this$0;

            public AnonymousClass2(VoIpButtonView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                VoIpButtonView.m14017$$Nest$fputselectedRadius(this.this$0, 0);
                this.this$0.invalidate();
            }
        }

        public interface OnBtnClickedListener {
            void onClicked(View r1);
        }

        public static /* synthetic */ void $r8$lambda$5jQg5NInUiI0ccRCFz7C9p_hxAI(VoIpButtonView r0, ValueAnimator r1) {
            r0.lambda$setPressedBtn$3(r1);
        }

        /* renamed from: $r8$lambda$bRN9oi-qiv5fZxNNILRUVpTOTBg, reason: not valid java name */
        public static /* synthetic */ void m14009$r8$lambda$bRN9oiqiv5fZxNNILRUVpTOTBg(VoIpButtonView r0, ValueAnimator r1) {
            r0.lambda$setSelectedState$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$g6UYNAAUrkC7xdQ1O4nMvboUCik(VoIpButtonView r0, ValueAnimator r1) {
            r0.lambda$setSelectedState$2(r1);
        }

        /* renamed from: $r8$lambda$h0QyOT4qZwm3SyNBayH-uvYUPpk, reason: not valid java name */
        public static /* synthetic */ void m14010$r8$lambda$h0QyOT4qZwm3SyNBayHuvYUPpk(VoIpButtonView r0, ValueAnimator r1) {
            r0.lambda$setSelectedState$0(r1);
        }

        /* renamed from: -$$Nest$fgetisSelectedState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m14011$$Nest$fgetisSelectedState(VoIpButtonView r0) {
            return r0.isSelectedState;
        }

        /* renamed from: -$$Nest$fgetonBtnClickedListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ OnBtnClickedListener m14012$$Nest$fgetonBtnClickedListener(VoIpButtonView r0) {
            return r0.onBtnClickedListener;
        }

        /* renamed from: -$$Nest$fgetselectedIcon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieDrawable m14013$$Nest$fgetselectedIcon(VoIpButtonView r0) {
            return r0.selectedIcon;
        }

        /* renamed from: -$$Nest$fgetsingleIcon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieDrawable m14014$$Nest$fgetsingleIcon(VoIpButtonView r0) {
            return r0.singleIcon;
        }

        /* renamed from: -$$Nest$fgetunSelectedIcon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieDrawable m14015$$Nest$fgetunSelectedIcon(VoIpButtonView r0) {
            return r0.unSelectedIcon;
        }

        /* renamed from: -$$Nest$fputselectedIcon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14016$$Nest$fputselectedIcon(VoIpButtonView r0, RLottieDrawable r1) {
            r0.selectedIcon = r1;
        }

        /* renamed from: -$$Nest$fputselectedRadius, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14017$$Nest$fputselectedRadius(VoIpButtonView r0, int r1) {
            r0.selectedRadius = r1;
        }

        /* renamed from: -$$Nest$fputsingleIcon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14018$$Nest$fputsingleIcon(VoIpButtonView r0, RLottieDrawable r1) {
            r0.singleIcon = r1;
        }

        /* renamed from: -$$Nest$fputunSelectedIcon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14019$$Nest$fputunSelectedIcon(VoIpButtonView r0, RLottieDrawable r1) {
            r0.unSelectedIcon = r1;
        }

        /* renamed from: -$$Nest$fputunselectedRadius, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14020$$Nest$fputunselectedRadius(VoIpButtonView r0, int r1) {
            r0.unselectedRadius = r1;
        }

        public VoIpButtonView(Context r5, VoIPBackgroundProvider r6) {
            super(r5);
            Paint r52 = new Paint(1);
            this.maskPaint = r52;
            Paint r1 = new Paint(1);
            this.whiteCirclePaint = r1;
            Paint r2 = new Paint(1);
            this.darkPaint = r2;
            this.clipPath = new Path();
            int r3 = AndroidUtilities.dp(26.0f);
            this.maxRadius = r3;
            this.unselectedRadius = r3;
            this.selectedRadius = 0;
            this.isSelectedState = false;
            this.singleIconBackgroundAlphaPercent = 0;
            this.pressedScale = 1.0f;
            this.backgroundProvider = r6;
            r6.attach(this);
            setLayerType(1, null);
            r1.setColor(-1);
            r52.setColor(-16777216);
            r52.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            r2.setColor(-16777216);
            r2.setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff.Mode.SRC_ATOP));
            r2.setAlpha(35);
        }

        private boolean isAnimating() {
            int r0 = this.unselectedRadius;
            int r1 = this.maxRadius;
            if (r0 == r1) goto L5;
        L7:
            boolean r4 = false;
        L9:
            if (this.selectedRadius != r1) goto L12;
            if (r0 != 0) goto L12;
            boolean r5 = true;
        L13:
            if (r4 == true) goto L16;
            if (r5 == true) goto L16;
            return true;
        L16:
            return false;
        L12:
            r5 = false;
            goto L13
        L5:
            if (this.selectedRadius != 0) goto L7;
            r4 = true;
            goto L9
        }

        private boolean isClick(float r1, float r2, float r3, float r4) {
            float r0 = Math.abs(r1 - r2);
            float r12 = Math.abs(r3 - r4);
            if (r0 <= AndroidUtilities.dp(48.0f)) goto L5;
            return false;
        L5:
            if (r12 > AndroidUtilities.dp(48.0f)) goto L10;
            return true;
        L10:
            return false;
        }

        private /* synthetic */ void lambda$setPressedBtn$3(ValueAnimator r1) {
            this.pressedScale = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        private /* synthetic */ void lambda$setSelectedState$0(ValueAnimator r1) {
            this.singleIconBackgroundAlphaPercent = ((Integer) r1.getAnimatedValue()).intValue();
            invalidate();
        }

        private /* synthetic */ void lambda$setSelectedState$1(ValueAnimator r1) {
            this.selectedRadius = ((Integer) r1.getAnimatedValue()).intValue();
            invalidate();
        }

        private /* synthetic */ void lambda$setSelectedState$2(ValueAnimator r1) {
            this.unselectedRadius = ((Integer) r1.getAnimatedValue()).intValue();
            invalidate();
        }

        private void setPressedBtn(boolean r4) {
            ValueAnimator r0 = this.pressedScaleAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            float r02 = this.pressedScale;
            if (r4 == false) goto L8;
            float r42 = 0.8f;
        L9:
            ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r02, r42});
            this.pressedScaleAnimator = r43;
            r43.addUpdateListener(new VoIpSwitchLayout$VoIpButtonView$$ExternalSyntheticLambda0(this));
            this.pressedScaleAnimator.setDuration(150);
            this.pressedScaleAnimator.start();
            return;
        L8:
            r42 = 1.0f;
            goto L9
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            r11.save();
            float r0 = this.pressedScale;
            r11.scale(r0, r0, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
            float r02 = getWidth() / 2.0f;
            float r1 = getHeight() / 2.0f;
            this.backgroundProvider.setLightTranslation(getX() + ((View) getParent()).getX(), getY() + ((View) ((View) getParent()).getParent()).getY());
            if (this.singleIcon == null) goto L14;
            if (this.singleIconBackgroundAlphaPercent <= 20) goto L8;
            this.darkPaint.setAlpha((int) ((r2 * 35) / 100.0f));
            this.whiteCirclePaint.setAlpha((int) ((this.singleIconBackgroundAlphaPercent * 255) / 100.0f));
            r11.drawCircle(r02, r1, this.maxRadius, this.whiteCirclePaint);
            this.singleIcon.draw(r11, this.maskPaint);
            this.singleIcon.draw(r11, this.darkPaint);
            return;
        L8:
            r11.drawCircle(r02, r1, this.maxRadius, this.backgroundProvider.getLightPaint());
            if (this.backgroundProvider.isReveal() == false) goto L11;
            r11.drawCircle(r02, r1, this.maxRadius, this.backgroundProvider.getRevealPaint());
        L11:
            this.singleIcon.draw(r11);
            return;
        L14:
            if (this.selectedIcon != null) goto L16;
            return;
        L16:
            if (this.unSelectedIcon == null) goto L48;
            int r2 = this.unselectedRadius;
            int r5 = this.maxRadius;
            boolean r6 = false;
            if (r2 == r5) goto L21;
        L23:
            boolean r8 = false;
        L24:
            int r9 = this.selectedRadius;
            if (r9 != r5) goto L28;
            if (r2 != 0) goto L28;
            r6 = true;
        L28:
            if (r9 != r5) goto L32;
            if (r2 <= 0) goto L32;
            if (r2 == r5) goto L32;
            r11.drawCircle(r02, r1, r9, this.whiteCirclePaint);
            r11.drawCircle(r02, r1, this.unselectedRadius, this.maskPaint);
            this.selectedIcon.setAlpha(255);
            this.selectedIcon.draw(r11, this.maskPaint);
            this.selectedIcon.setAlpha(35);
            this.selectedIcon.draw(r11);
            this.clipPath.reset();
            this.clipPath.addCircle(r02, r1, this.unselectedRadius, Path.Direction.CW);
            r11.clipPath(this.clipPath);
            r11.drawCircle(r02, r1, this.unselectedRadius, this.maskPaint);
        L32:
            if (r8 == false) goto L34;
        L35:
            r11.drawCircle(r02, r1, this.unselectedRadius, this.backgroundProvider.getLightPaint());
            if (this.backgroundProvider.isReveal() == false) goto L38;
            r11.drawCircle(r02, r1, this.unselectedRadius, this.backgroundProvider.getRevealPaint());
        L38:
            this.unSelectedIcon.draw(r11);
        L39:
            if (r6 == false) goto L41;
        L44:
            this.clipPath.reset();
            this.clipPath.addCircle(r02, r1, this.selectedRadius, Path.Direction.CW);
            r11.clipPath(this.clipPath);
            r11.drawCircle(r02, r1, this.selectedRadius, this.whiteCirclePaint);
            this.selectedIcon.setAlpha(255);
            this.selectedIcon.draw(r11, this.maskPaint);
            this.selectedIcon.setAlpha(35);
            this.selectedIcon.draw(r11);
        L45:
            r11.restore();
            return;
        L41:
            if (this.selectedRadius <= 0) goto L45;
            if (this.unselectedRadius != this.maxRadius) goto L45;
        L34:
            if (this.unselectedRadius <= 0) goto L39;
        L21:
            if (this.selectedRadius != 0) goto L23;
            r8 = true;
            goto L24
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r5) {
            int r0 = r5.getAction();
            if (r0 != 0) goto L5;
            setPressedBtn(true);
            this.startX = r5.getX();
            this.startY = r5.getY();
        L18:
            return true;
        L5:
            if (r0 != 1) goto L7;
            setPressedBtn(false);
            float r02 = r5.getX();
            float r52 = r5.getY();
            if (isClick(this.startX, r02, this.startY, r52) == false) goto L18;
            if (isAnimating() == true) goto L18;
            OnBtnClickedListener r53 = this.onBtnClickedListener;
            if (r53 == null) goto L18;
            r53.onClicked(this);
            goto L18
        L7:
            if (r0 != 3) goto L18;
            setPressedBtn(false);
            goto L18
        }

        public void setOnBtnClickedListener(OnBtnClickedListener r1) {
            this.onBtnClickedListener = r1;
        }

        public void setSelectedState(boolean r7, boolean r8, Type r9) {
            ValueAnimator r0 = this.animator;
            if (r0 == null) goto L8;
            if (r0.isRunning() == false) goto L8;
            this.animator.removeAllUpdateListeners();
            this.animator.cancel();
            r8 = false;
        L8:
            if (r8 == false) goto L25;
            RLottieDrawable r82 = this.singleIcon;
            ValueAnimator r3 = this.animator;
            if (r82 == null) goto L19;
            if (r3 == null) goto L13;
            r3.removeAllUpdateListeners();
            this.animator.cancel();
        L13:
            if (r7 == false) goto L15;
            ValueAnimator r83 = ValueAnimator.ofInt(new int[]{20, 100});
        L16:
            this.animator = r83;
            r83.addUpdateListener(new VoIpSwitchLayout$VoIpButtonView$$ExternalSyntheticLambda1(this));
            this.animator.setDuration(200);
            this.animator.start();
            if (r9 != Type.CAMERA) goto L32;
            this.singleIcon.setCurrentFrame(0, false);
            this.singleIcon.start();
        L32:
            this.isSelectedState = r7;
            invalidate();
            return;
        L15:
            r83 = ValueAnimator.ofInt(new int[]{100, 20});
            goto L16
        L19:
            if (r3 == null) goto L21;
            r3.removeAllUpdateListeners();
            this.animator.cancel();
        L21:
            ValueAnimator r84 = ValueAnimator.ofInt(new int[]{0, this.maxRadius});
            this.animator = r84;
            int r92 = this.maxRadius;
            if (r7 == false) goto L24;
            this.unselectedRadius = r92;
            r84.addUpdateListener(new VoIpSwitchLayout$VoIpButtonView$$ExternalSyntheticLambda2(this));
            this.animator.addListener(new AnonymousClass1(this));
            this.animator.setDuration(200);
            this.animator.start();
            this.selectedIcon.setCurrentFrame(0, false);
            this.selectedIcon.start();
            goto L32
        L24:
            this.selectedRadius = r92;
            r84.addUpdateListener(new VoIpSwitchLayout$VoIpButtonView$$ExternalSyntheticLambda3(this));
            this.animator.setDuration(200);
            this.animator.addListener(new AnonymousClass2(this));
            this.animator.start();
            goto L32
        L25:
            if (r7 == false) goto L31;
            this.selectedRadius = this.maxRadius;
            this.unselectedRadius = 0;
            this.singleIconBackgroundAlphaPercent = 100;
            if (r9 != Type.VIDEO) goto L29;
        L30:
            this.selectedIcon.setCurrentFrame(r8.getFramesCount() - 1, false);
            goto L32
        L29:
            if (r9 != Type.MICRO) goto L32;
        L31:
            this.selectedRadius = 0;
            this.unselectedRadius = this.maxRadius;
            this.singleIconBackgroundAlphaPercent = 20;
            goto L32
        }
    }

    public static /* synthetic */ void $r8$lambda$aW2_8LyKGx1mtuBJack32KHC5_U(VoIpSwitchLayout r0, int r1) {
        r0.lambda$setType$0(r1);
    }

    /* renamed from: $r8$lambda$hNkGH3bhdx4h5oPb4uG94Ep7E-U, reason: not valid java name */
    public static /* synthetic */ void m14005$r8$lambda$hNkGH3bhdx4h5oPb4uG94Ep7EU(VoIpSwitchLayout r0, int r1) {
        r0.lambda$setType$2(r1);
    }

    /* renamed from: $r8$lambda$u-4HecCBtO9oIPnn9VLCdlxtvTc, reason: not valid java name */
    public static /* synthetic */ void m14006$r8$lambda$u4HecCBtO9oIPnn9VLCdlxtvTc(VoIpSwitchLayout r0, int r1) {
        r0.lambda$setType$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$xJjW83vb1x7Jnfl0AM4vnod8d7c(VoIpSwitchLayout r0, int r1) {
        r0.lambda$setType$1(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m14007$$Nest$fgetcurrentTextView(VoIpSwitchLayout r0) {
        return r0.currentTextView;
    }

    /* renamed from: -$$Nest$fgetnewTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m14008$$Nest$fgetnewTextView(VoIpSwitchLayout r0) {
        return r0.newTextView;
    }

    public VoIpSwitchLayout(Context r13, VoIPBackgroundProvider r14) {
        super(r13);
        this.backgroundProvider = r14;
        setWillNotDraw(true);
        VoIpButtonView r1 = new VoIpButtonView(r13, r14);
        this.voIpButtonView = r1;
        addView(r1, LayoutHelper.createFrame(53.5f, 53.5f, 1));
        TextView r142 = new TextView(r13);
        this.currentTextView = r142;
        r142.setGravity(1);
        r142.setTextSize(1, 11.0f);
        r142.setTextColor(-1);
        r142.setImportantForAccessibility(2);
        addView(r142, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 58.0f, 0.0f, 2.0f));
        TextView r4 = new TextView(r13);
        this.newTextView = r4;
        r4.setGravity(1);
        r4.setTextSize(1, 11.0f);
        r4.setTextColor(-1);
        r4.setImportantForAccessibility(2);
        addView(r4, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 58.0f, 0.0f, 2.0f));
        r142.setVisibility(8);
        r4.setVisibility(8);
    }

    private void attachBtToSpeaker(int r14) {
        VoIpButtonView.m14019$$Nest$fputunSelectedIcon(this.voIpButtonView, new RLottieDrawable(R.raw.bt_to_speaker, r14, r14, true, null));
        VoIpButtonView.m14016$$Nest$fputselectedIcon(this.voIpButtonView, new RLottieDrawable(R.raw.bt_to_speaker, r14, r14, true, null));
        VoIpButtonView.m14013$$Nest$fgetselectedIcon(this.voIpButtonView).setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff.Mode.MULTIPLY));
    }

    private void attachNewButton(int r9, int r10, boolean r11, Type r12) {
        VoIpButtonView r0 = new VoIpButtonView(getContext(), this.backgroundProvider);
        if (r9 != R.raw.camera_flip2) goto L5;
        VoIpButtonView.m14018$$Nest$fputsingleIcon(r0, new RLottieDrawable(r9, r10, r10, true, null));
        VoIpButtonView.m14014$$Nest$fgetsingleIcon(r0).setMasterParent(r0);
    L6:
        r0.setSelectedState(r11, false, r12);
        r0.setAlpha(0.0f);
        r0.setOnBtnClickedListener(VoIpButtonView.m14012$$Nest$fgetonBtnClickedListener(this.voIpButtonView));
        addView(r0, LayoutHelper.createFrame(53.5f, 53.5f, 1));
        VoIpButtonView r102 = this.voIpButtonView;
        this.voIpButtonView = r0;
        r0.animate().alpha(1.0f).setDuration(250).start();
        r102.animate().alpha(0.0f).setDuration(250).setListener(new AnonymousClass3(this, r102)).start();
        return;
    L5:
        VoIpButtonView.m14019$$Nest$fputunSelectedIcon(r0, new RLottieDrawable(r9, r10, r10, true, null));
        VoIpButtonView.m14016$$Nest$fputselectedIcon(r0, new RLottieDrawable(r9, r10, r10, true, null));
        VoIpButtonView.m14013$$Nest$fgetselectedIcon(r0).setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff.Mode.MULTIPLY));
        goto L6
    }

    private void attachSpeakerToBt(int r14) {
        VoIpButtonView.m14019$$Nest$fputunSelectedIcon(this.voIpButtonView, new RLottieDrawable(R.raw.speaker_to_bt, r14, r14, true, null));
        VoIpButtonView.m14016$$Nest$fputselectedIcon(this.voIpButtonView, new RLottieDrawable(R.raw.speaker_to_bt, r14, r14, true, null));
        VoIpButtonView.m14013$$Nest$fgetselectedIcon(this.voIpButtonView).setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff.Mode.MULTIPLY));
    }

    private /* synthetic */ void lambda$setType$0(int r1) {
        attachSpeakerToBt(r1);
    }

    private /* synthetic */ void lambda$setType$1(int r2) {
        AndroidUtilities.runOnUIThread(new VoIpSwitchLayout$$ExternalSyntheticLambda2(this, r2));
    }

    private /* synthetic */ void lambda$setType$2(int r1) {
        attachBtToSpeaker(r1);
    }

    private /* synthetic */ void lambda$setType$3(int r2) {
        AndroidUtilities.runOnUIThread(new VoIpSwitchLayout$$ExternalSyntheticLambda3(this, r2));
    }

    private void setText(Type r5, boolean r6) {
        int r52 = AnonymousClass4.$SwitchMap$org$telegram$ui$Components$voip$VoIpSwitchLayout$Type[r5.ordinal()];
        if (r52 != 1) goto L5;
        if (r6 == false) goto L21;
        String r53 = LocaleController.getString(R.string.VoipUnmute);
    L22:
        setContentDescription(r53);
        if (this.currentTextView.getVisibility() != 8) goto L29;
        if (this.newTextView.getVisibility() != 8) goto L29;
        this.currentTextView.setVisibility(0);
        this.currentTextView.setText(r53);
        this.newTextView.setText(r53);
        return;
    L29:
        if (this.newTextView.getText().equals(r53) == true) goto L31;
    L33:
        this.currentTextView.animate().alpha(0.0f).translationY(-AndroidUtilities.dp(4.0f)).setDuration(140).setListener(new AnonymousClass1(this, r53)).start();
        this.newTextView.setText(r53);
        this.newTextView.setVisibility(0);
        this.newTextView.setAlpha(0.0f);
        this.newTextView.setTranslationY(AndroidUtilities.dp(5.0f));
        this.newTextView.animate().alpha(1.0f).translationY(0.0f).setDuration(150).setListener(new AnonymousClass2(this)).start();
        return;
    L31:
        if (this.currentTextView.getText().equals(r53) == false) goto L33;
        return;
    L21:
        r53 = LocaleController.getString(R.string.VoipMute);
        goto L22
    L5:
        if (r52 != 2) goto L7;
        r53 = LocaleController.getString(R.string.VoipFlip);
        goto L22
    L7:
        if (r52 != 3) goto L9;
        if (r6 == false) goto L17;
        r53 = LocaleController.getString(R.string.VoipStartVideo);
        goto L22
    L17:
        r53 = LocaleController.getString(R.string.VoipStopVideo);
        goto L22
    L9:
        if (r52 != 4) goto L11;
        r53 = LocaleController.getString(R.string.VoipAudioRoutingBluetooth);
        goto L22
    L11:
        if (r52 == 5) goto L13;
        r53 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L22
    L13:
        r53 = LocaleController.getString(R.string.VoipSpeaker);
        goto L22
    }

    public void setOnBtnClickedListener(VoIpButtonView.OnBtnClickedListener r1) {
        this.voIpButtonView.setOnBtnClickedListener(r1);
    }

    public void setType(Type r2, boolean r3) {
        setType(r2, r3, false);
    }

    public void setType(Type r11, boolean r12, boolean r13) {
        boolean r1 = false;
        if (this.type != r11) goto L11;
        if (r12 != VoIpButtonView.m14011$$Nest$fgetisSelectedState(this.voIpButtonView)) goto L11;
        if (getVisibility() == 0) goto L83;
        setVisibility(0);
        return;
    L83:
        return;
    L11:
        if (getVisibility() == 0) goto L13;
        setVisibility(0);
    L13:
        int r4 = AndroidUtilities.dp(53.5f);
        int r0 = AnonymousClass4.$SwitchMap$org$telegram$ui$Components$voip$VoIpSwitchLayout$Type[r11.ordinal()];
        if (r0 == 1) goto L72;
        if (r0 != 2) goto L18;
        Type r02 = this.type;
        if (r02 != Type.SPEAKER) goto L65;
    L70:
        attachNewButton(R.raw.camera_flip2, r4, r12, r11);
    L38:
        boolean r03 = true;
    L75:
        if (r03 == true) goto L81;
        VoIpButtonView r04 = this.voIpButtonView;
        if (this.type == null) goto L80;
        if (r13 == true) goto L80;
        r1 = true;
    L80:
        r04.setSelectedState(r12, r1, r11);
    L81:
        setText(r11, r12);
        this.type = r11;
        return;
    L65:
        if (r02 == Type.BLUETOOTH) goto L70;
        if (r02 == Type.CAMERA) goto L74;
        VoIpButtonView.m14018$$Nest$fputsingleIcon(this.voIpButtonView, new RLottieDrawable(R.raw.camera_flip2, r4, r4, true, null));
        VoIpButtonView.m14014$$Nest$fgetsingleIcon(this.voIpButtonView).setMasterParent(this.voIpButtonView);
    L74:
        r03 = false;
        goto L75
    L18:
        if (r0 == 3) goto L60;
        if (r0 != 4) goto L22;
        Type r05 = this.type;
        if (r05 != Type.SPEAKER) goto L54;
        if (r12 != VoIpButtonView.m14011$$Nest$fgetisSelectedState(this.voIpButtonView)) goto L47;
        r03 = true;
    L48:
        VoIpButtonView r2 = this.voIpButtonView;
        if (r12 == false) goto L51;
        RLottieDrawable r22 = VoIpButtonView.m14013$$Nest$fgetselectedIcon(r2);
    L52:
        r22.setMasterParent(this.voIpButtonView);
        r22.setOnAnimationEndListener(new VoIpSwitchLayout$$ExternalSyntheticLambda1(this, r4));
        r22.start();
        goto L75
    L51:
        r22 = VoIpButtonView.m14015$$Nest$fgetunSelectedIcon(r2);
        goto L52
    L47:
        r03 = false;
        goto L48
    L54:
        if (r05 != Type.CAMERA) goto L57;
        attachNewButton(R.raw.bt_to_speaker, r4, r12, r11);
        goto L38
    L57:
        if (r05 == Type.BLUETOOTH) goto L74;
        attachBtToSpeaker(r4);
        goto L74
    L22:
        if (r0 != 5) goto L74;
        Type r06 = this.type;
        if (r06 != Type.BLUETOOTH) goto L36;
        if (r12 != VoIpButtonView.m14011$$Nest$fgetisSelectedState(this.voIpButtonView)) goto L29;
        r03 = true;
    L30:
        VoIpButtonView r23 = this.voIpButtonView;
        if (r12 == false) goto L33;
        RLottieDrawable r24 = VoIpButtonView.m14013$$Nest$fgetselectedIcon(r23);
    L34:
        r24.setMasterParent(this.voIpButtonView);
        r24.setOnAnimationEndListener(new VoIpSwitchLayout$$ExternalSyntheticLambda0(this, r4));
        r24.start();
        goto L75
    L33:
        r24 = VoIpButtonView.m14015$$Nest$fgetunSelectedIcon(r23);
        goto L34
    L29:
        r03 = false;
        goto L30
    L36:
        if (r06 != Type.CAMERA) goto L40;
        attachNewButton(R.raw.speaker_to_bt, r4, r12, r11);
        goto L38
    L40:
        if (r06 == Type.SPEAKER) goto L74;
        attachSpeakerToBt(r4);
        goto L74
    L60:
        if (this.type == Type.VIDEO) goto L74;
        VoIpButtonView.m14019$$Nest$fputunSelectedIcon(this.voIpButtonView, new RLottieDrawable(R.raw.video_stop, r4, r4, true, null));
        VoIpButtonView.m14016$$Nest$fputselectedIcon(this.voIpButtonView, new RLottieDrawable(R.raw.video_stop, r4, r4, true, null));
        VoIpButtonView.m14013$$Nest$fgetselectedIcon(this.voIpButtonView).setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff.Mode.MULTIPLY));
        VoIpButtonView.m14013$$Nest$fgetselectedIcon(this.voIpButtonView).setMasterParent(this.voIpButtonView);
        goto L74
    L72:
        if (this.type == Type.MICRO) goto L74;
        VoIpButtonView.m14019$$Nest$fputunSelectedIcon(this.voIpButtonView, new RLottieDrawable(R.raw.call_mute, r4, r4, true, null));
        VoIpButtonView.m14016$$Nest$fputselectedIcon(this.voIpButtonView, new RLottieDrawable(R.raw.call_mute, r4, r4, true, null));
        VoIpButtonView.m14013$$Nest$fgetselectedIcon(this.voIpButtonView).setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff.Mode.MULTIPLY));
        VoIpButtonView.m14013$$Nest$fgetselectedIcon(this.voIpButtonView).setMasterParent(this.voIpButtonView);
        goto L74
    }
}
