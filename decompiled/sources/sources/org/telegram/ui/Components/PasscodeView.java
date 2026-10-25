package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.SystemClock;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Property;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FingerprintController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.support.fingerprint.FingerprintManagerCompat;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.KeyboardNotifier;

/* loaded from: classes7.dex */
public class PasscodeView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private static final int[] ids = null;
    private final int BUTTON_SIZE;
    private final int BUTTON_X_MARGIN;
    private final int BUTTON_Y_MARGIN;
    private SpringAnimation backgroundAnimationSpring;
    private Drawable backgroundDrawable;
    private FrameLayout backgroundFrameLayout;
    private int backgroundFrameLayoutColor;
    private LinkedList<Boolean> backgroundSpringNextQueue;
    private LinkedList<Runnable> backgroundSpringQueue;
    private View border;
    private ImageView checkImage;
    private Runnable checkRunnable;
    private PasscodeViewDelegate delegate;
    private ImageView fingerprintImage;
    private PasscodeButton fingerprintView;
    private RLottieImageView imageView;
    private int imageY;
    private ArrayList<InnerAnimator> innerAnimators;
    private int keyboardHeight;
    private KeyboardNotifier keyboardNotifier;
    private int lastValue;
    private ArrayList<FrameLayout> numberFrameLayouts;
    private FrameLayout numbersContainer;
    public FrameLayout numbersFrameLayout;
    private FrameLayout numbersTitleContainer;
    private TextView passcodeTextView;
    private EditTextBoldCursor passwordEditText;
    private AnimatingTextView passwordEditText2;
    private FrameLayout passwordFrameLayout;
    private ValueAnimator pinAnimator;
    private boolean pinShown;
    private int[] pos;
    private Rect rect;
    int resumeCount;
    private TextView retryTextView;
    private int shiftDp;
    private float shownT;
    private TextView subtitleView;

    /* renamed from: org.telegram.ui.Components.PasscodeView$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        private Paint paint;
        final /* synthetic */ PasscodeView this$0;

        public AnonymousClass1(PasscodeView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.paint = new Paint();
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            if (PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0) != null) goto L5;
            super.onDraw(r8);
        L14:
            r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.paint);
            return;
        L5:
            if ((PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0) instanceof MotionBackgroundDrawable) == false) goto L7;
        L12:
            PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0).setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0).draw(r8);
            goto L14
        L7:
            if ((PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0) instanceof ColorDrawable) == true) goto L12;
            if ((PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0) instanceof GradientDrawable) == true) goto L12;
            float r0 = Math.max(getMeasuredWidth() / PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0).getIntrinsicWidth(), (getMeasuredHeight() + PasscodeView.m11638$$Nest$fgetkeyboardHeight(this.this$0)) / PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0).getIntrinsicHeight());
            int r1 = (int) Math.ceil(PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0).getIntrinsicWidth() * r0);
            int r02 = (int) Math.ceil(PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0).getIntrinsicHeight() * r0);
            int r2 = (getMeasuredWidth() - r1) / 2;
            int r3 = ((getMeasuredHeight() - r02) + PasscodeView.m11638$$Nest$fgetkeyboardHeight(this.this$0)) / 2;
            PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0).setBounds(r2, r3, r1 + r2, r02 + r3);
            PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0).draw(r8);
            goto L14
        }

        @Override // android.view.View
        public void setBackgroundColor(int r1) {
            this.paint.setColor(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.PasscodeView$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ PasscodeView this$0;

        public static /* synthetic */ void $r8$lambda$Y9NK8LAY1cyUUfPje2IUZvyXuAM(AnonymousClass2 r0, boolean r1, MotionBackgroundDrawable r2) {
            r0.lambda$beforeTextChanged$0(r1, r2);
        }

        public AnonymousClass2(PasscodeView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$beforeTextChanged$0(boolean r2, MotionBackgroundDrawable r3) {
            if (r2 == false) goto L5;
            r3.switchToNextPosition(true);
        L6:
            PasscodeView.m11646$$Nest$manimateBackground(this.this$0, r3);
            return;
        L5:
            r3.switchToPrevPosition(true);
            goto L6
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            if (PasscodeView.m11640$$Nest$fgetpasswordEditText(this.this$0).length() == 4) goto L5;
            return;
        L5:
            if (SharedConfig.passcodeType != 0) goto L9;
            PasscodeView.m11649$$Nest$mprocessDone(this.this$0, false);
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r4, int r5, int r6, int r7) {
            if ((PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0) instanceof MotionBackgroundDrawable) == false) goto L32;
            MotionBackgroundDrawable r42 = (MotionBackgroundDrawable) PasscodeView.m11630$$Nest$fgetbackgroundDrawable(this.this$0);
            r42.setAnimationProgressProvider(null);
            float r52 = r42.getPosAnimationProgress();
            int r0 = 0;
            boolean r1 = true;
            if (r6 != 0) goto L8;
            if (r7 != 1) goto L8;
            r42.switchToNextPosition(true);
            boolean r62 = true;
        L12:
            if (r1 == false) goto L42;
            PasscodeView r72 = this.this$0;
            if (r52 < 1.0f) goto L17;
            PasscodeView.m11646$$Nest$manimateBackground(r72, r42);
            return;
        L17:
            PasscodeView.m11633$$Nest$fgetbackgroundSpringQueue(r72).offer(new PasscodeView$2$$ExternalSyntheticLambda0(this, r62, r42));
            PasscodeView.m11632$$Nest$fgetbackgroundSpringNextQueue(this.this$0).offer(Boolean.valueOf(r62));
            ArrayList r43 = new ArrayList();
            ArrayList r53 = new ArrayList();
            int r73 = 0;
        L19:
            if (r73 >= PasscodeView.m11633$$Nest$fgetbackgroundSpringQueue(this.this$0).size()) goto L24;
            Runnable r12 = (Runnable) PasscodeView.m11633$$Nest$fgetbackgroundSpringQueue(this.this$0).get(r73);
            if (((Boolean) PasscodeView.m11632$$Nest$fgetbackgroundSpringNextQueue(this.this$0).get(r73)).booleanValue() == r62) goto L23;
            r43.add(r12);
            r53.add(Integer.valueOf(r73));
        L23:
            r73 = r73 + 1;
            goto L19
        L24:
            int r63 = r43.size();
            int r74 = 0;
        L25:
            if (r74 >= r63) goto L27;
            Object r13 = r43.get(r74);
            r74 = r74 + 1;
            PasscodeView.m11633$$Nest$fgetbackgroundSpringQueue(this.this$0).remove((Runnable) r13);
            goto L25
        L27:
            int r44 = r53.size();
        L28:
            if (r0 >= r44) goto L43;
            Object r64 = r53.get(r0);
            r0 = r0 + 1;
            int r65 = ((Integer) r64).intValue();
            if (r65 >= PasscodeView.m11632$$Nest$fgetbackgroundSpringNextQueue(this.this$0).size()) goto L28;
            PasscodeView.m11632$$Nest$fgetbackgroundSpringNextQueue(this.this$0).remove(r65);
            goto L28
        L43:
            return;
        L42:
            return;
        L8:
            if (r6 != 1) goto L11;
            if (r7 != 0) goto L11;
            r42.switchToPrevPosition(true);
            r62 = false;
        L11:
            r62 = false;
            r1 = false;
            goto L12
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.PasscodeView$3, reason: invalid class name */
    public class AnonymousClass3 implements ActionMode.Callback {
        final /* synthetic */ PasscodeView this$0;

        public AnonymousClass3(PasscodeView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.PasscodeView$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ PasscodeView this$0;

        public AnonymousClass4(PasscodeView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if ((getParent() instanceof View) == false) goto L10;
            float r12 = Math.min(((View) getParent()).getHeight() / getHeight(), 1.0f);
            setPivotX(getWidth() / 2.0f);
            if (((FrameLayout.LayoutParams) getLayoutParams()).gravity != 17) goto L7;
            float r22 = getHeight() / 2.0f;
        L8:
            setPivotY(r22);
            setScaleX(r12);
            setScaleY(r12);
            return;
        L7:
            r22 = 0.0f;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Components.PasscodeView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ PasscodeView this$0;

        public AnonymousClass5(PasscodeView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.setVisibility(8);
            this.this$0.onHidden();
            PasscodeView r22 = this.this$0;
            PasscodeView.m11645$$Nest$fputshownT(r22, 0.0f);
            r22.onAnimationUpdate(0.0f);
            this.this$0.setAlpha(0.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.PasscodeView$6, reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ PasscodeView this$0;

        public AnonymousClass6(PasscodeView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            PasscodeView.m11647$$Nest$mcheckRetryTextView(this.this$0);
            AndroidUtilities.runOnUIThread(PasscodeView.m11634$$Nest$fgetcheckRunnable(this.this$0), 100);
        }
    }

    /* renamed from: org.telegram.ui.Components.PasscodeView$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ PasscodeView this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass7(PasscodeView r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r6) {
            if (this.val$show == false) goto L5;
            float r62 = 1.0f;
        L6:
            this.this$0.numbersFrameLayout.setScaleX(AndroidUtilities.lerp(0.8f, 1.0f, r62));
            this.this$0.numbersFrameLayout.setScaleY(AndroidUtilities.lerp(0.8f, 1.0f, r62));
            this.this$0.numbersFrameLayout.setAlpha(AndroidUtilities.lerp(0.0f, 1.0f, r62));
            PasscodeView.m11639$$Nest$fgetpasscodeTextView(this.this$0).setScaleX(AndroidUtilities.lerp(1.0f, 0.9f, r62));
            PasscodeView.m11639$$Nest$fgetpasscodeTextView(this.this$0).setScaleY(AndroidUtilities.lerp(1.0f, 0.9f, r62));
            PasscodeView.m11639$$Nest$fgetpasscodeTextView(this.this$0).setAlpha(AndroidUtilities.lerp(1.0f, 0.0f, r62));
            PasscodeView.m11641$$Nest$fgetpasswordEditText2(this.this$0).setAlpha(AndroidUtilities.lerp(0.0f, 1.0f, r62));
            return;
        L5:
            r62 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.PasscodeView$8, reason: invalid class name */
    public class AnonymousClass8 extends BiometricPrompt.AuthenticationCallback {
        final /* synthetic */ PasscodeView this$0;

        public AnonymousClass8(PasscodeView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
        public void onAuthenticationError(int r3, CharSequence r4) {
            FileLog.d("PasscodeView onAuthenticationError " + r3 + " \"" + r4 + "\"");
            PasscodeView.m11650$$Nest$mshowPin(this.this$0, true);
        }

        @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
        public void onAuthenticationFailed() {
            FileLog.d("PasscodeView onAuthenticationFailed");
            PasscodeView.m11650$$Nest$mshowPin(this.this$0, true);
        }

        @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult r1) {
            FileLog.d("PasscodeView onAuthenticationSucceeded");
            PasscodeView.m11649$$Nest$mprocessDone(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.Components.PasscodeView$9, reason: invalid class name */
    public class AnonymousClass9 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ PasscodeView this$0;
        final /* synthetic */ Runnable val$onShow;
        final /* synthetic */ int val$x;
        final /* synthetic */ int val$y;

        /* renamed from: org.telegram.ui.Components.PasscodeView$9$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass9 this$1;
            final /* synthetic */ AnimatorSet val$animatorSetInner;

            public AnonymousClass1(AnonymousClass9 r1, AnimatorSet r2) {
                this.this$1 = r1;
                this.val$animatorSetInner = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                AnimatorSet r0 = this.val$animatorSetInner;
                if (r0 == null) goto L6;
                r0.start();
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.PasscodeView$9$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass9 this$1;

            public AnonymousClass2(AnonymousClass9 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                PasscodeView r0 = this.this$1.this$0;
                PasscodeView.m11645$$Nest$fputshownT(r0, 1.0f);
                r0.onAnimationUpdate(1.0f);
            }
        }

        /* renamed from: org.telegram.ui.Components.PasscodeView$9$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass9 this$1;

            public AnonymousClass3(AnonymousClass9 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                Runnable r22 = this.this$1.val$onShow;
                if (r22 == null) goto L6;
                r22.run();
            L6:
                if (SharedConfig.passcodeType == 1) goto L8;
                return;
            L8:
                if (PasscodeView.m11643$$Nest$fgetretryTextView(this.this$1.this$0).getVisibility() != 0) goto L10;
                return;
            L10:
                if (PasscodeView.m11640$$Nest$fgetpasswordEditText(this.this$1.this$0) == null) goto L15;
                PasscodeView.m11640$$Nest$fgetpasswordEditText(this.this$1.this$0).requestFocus();
                AndroidUtilities.showKeyboard(PasscodeView.m11640$$Nest$fgetpasswordEditText(this.this$1.this$0));
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$T2lFla0KVowE_0q4GnKEt1LX2Bo(AnonymousClass9 r0, double r1, ValueAnimator r3) {
            r0.lambda$onGlobalLayout$1(r1, r3);
        }

        public static /* synthetic */ void $r8$lambda$aVDnF4q8jq8QpKTapcp0ie4oOlw(AnonymousClass9 r0) {
            r0.lambda$onGlobalLayout$0();
        }

        public static /* synthetic */ void $r8$lambda$dZsDXZ4vIRcgNjrmkCWBEwFUUo8(AnonymousClass9 r0, ValueAnimator r1) {
            r0.lambda$onGlobalLayout$2(r1);
        }

        public AnonymousClass9(PasscodeView r1, int r2, int r3, Runnable r4) {
            this.this$0 = r1;
            this.val$x = r2;
            this.val$y = r3;
            this.val$onShow = r4;
        }

        private /* synthetic */ void lambda$onGlobalLayout$0() {
            PasscodeView.m11635$$Nest$fgetimageView(this.this$0).performHapticFeedback(3, 2);     // Catch: Exception -> L4
            return;
        }

        private /* synthetic */ void lambda$onGlobalLayout$1(double r4, ValueAnimator r6) {
            double r42 = r4 * r6.getAnimatedFraction();
            int r62 = 0;
        L4:
            if (r62 >= PasscodeView.m11637$$Nest$fgetinnerAnimators(this.this$0).size()) goto L10;
            InnerAnimator r0 = (InnerAnimator) PasscodeView.m11637$$Nest$fgetinnerAnimators(this.this$0).get(r62);
            if (InnerAnimator.m11658$$Nest$fgetstartRadius(r0) > r42) goto L9;
            InnerAnimator.m11657$$Nest$fgetanimatorSet(r0).start();
            PasscodeView.m11637$$Nest$fgetinnerAnimators(this.this$0).remove(r62);
            r62 = r62 - 1;
        L9:
            r62 = r62 + 1;
            goto L4
        }

        private /* synthetic */ void lambda$onGlobalLayout$2(ValueAnimator r1) {
            PasscodeView r0 = this.this$0;
            float r12 = ((Float) r1.getAnimatedValue()).floatValue();
            PasscodeView.m11645$$Nest$fputshownT(r0, r12);
            r0.onAnimationUpdate(r12);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            float r2 = 1.0f;
            this.this$0.setAlpha(1.0f);
            this.this$0.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            char r3 = 0;
            PasscodeView.m11635$$Nest$fgetimageView(this.this$0).getAnimatedDrawable().setCurrentFrame(0, false);
            PasscodeView.m11635$$Nest$fgetimageView(this.this$0).getAnimatedDrawable().setCustomEndFrame(37);
            PasscodeView.m11635$$Nest$fgetimageView(this.this$0).playAnimation();
            int r4 = 1;
            PasscodeView.m11650$$Nest$mshowPin(this.this$0, true);
            AndroidUtilities.runOnUIThread(new PasscodeView$9$$ExternalSyntheticLambda0(this), 350);
            AnimatorSet r1 = new AnimatorSet();
            ArrayList r5 = new ArrayList();
            Point r6 = AndroidUtilities.displaySize;
            int r7 = r6.x;
            int r62 = r6.y + AndroidUtilities.statusBarHeight;
            int r8 = this.val$x;
            int r9 = (r7 - r8) * (r7 - r8);
            int r82 = this.val$y;
            double r83 = Math.sqrt(r9 + ((r62 - r82) * (r62 - r82)));
            int r10 = this.val$x;
            int r11 = this.val$y;
            double r102 = Math.sqrt((r10 * r10) + ((r62 - r11) * (r62 - r11)));
            int r63 = this.val$x;
            int r12 = this.val$y;
            double r122 = Math.sqrt((r63 * r63) + (r12 * r12));
            int r64 = this.val$x;
            int r14 = (r7 - r64) * (r7 - r64);
            int r65 = this.val$y;
            double r84 = Math.max(Math.max(Math.max(r83, r102), r122), Math.sqrt(r14 + (r65 * r65)));
            PasscodeView.m11637$$Nest$fgetinnerAnimators(this.this$0).clear();
            int r66 = this.this$0.numbersFrameLayout.getChildCount();
            int r103 = 0;
        L3:
            PasscodeView r112 = this.this$0;
            if (r103 >= r66) goto L28;
            View r113 = r112.numbersFrameLayout.getChildAt(r103);
            r113.setScaleX(0.7f);
            r113.setScaleY(0.7f);
            r113.setAlpha(0.0f);
            InnerAnimator r142 = new InnerAnimator(null);
            float r16 = r2;
            r113.getLocationInWindow(PasscodeView.m11642$$Nest$fgetpos(this.this$0));
            int r22 = PasscodeView.m11642$$Nest$fgetpos(this.this$0)[r3] + (r113.getMeasuredWidth() / 2);
            char r17 = r3;
            int r32 = PasscodeView.m11642$$Nest$fgetpos(this.this$0)[r4] + (r113.getMeasuredHeight() / 2);
            int r15 = this.val$x;
            int r19 = (r15 - r22) * (r15 - r22);
            int r23 = this.val$y;
            InnerAnimator.m11660$$Nest$fputstartRadius(r142, ((float) Math.sqrt(r19 + ((r23 - r32) * (r23 - r32)))) - AndroidUtilities.dp(40.0f));
            if (r103 == (-1)) goto L8;
            AnimatorSet r152 = new AnimatorSet();
            Property r33 = View.SCALE_X;
            float[] r123 = new float[r4];
            r123[r17] = r16;
            ObjectAnimator r34 = ObjectAnimator.ofFloat(r113, r33, r123);
            Property r124 = View.SCALE_Y;
            float[] r24 = new float[r4];
            r24[r17] = r16;
            ObjectAnimator r25 = ObjectAnimator.ofFloat(r113, r124, r24);
            Animator[] r125 = new Animator[2];
            r125[r17] = r34;
            r125[r4] = r25;
            r152.playTogether(r125);
            r152.setDuration(140);
            r152.setInterpolator(new DecelerateInterpolator());
        L9:
            InnerAnimator.m11659$$Nest$fputanimatorSet(r142, new AnimatorSet());
            AnimatorSet r26 = InnerAnimator.m11657$$Nest$fgetanimatorSet(r142);
            Property r35 = View.SCALE_X;
            float r18 = 0.9f;
            int r21 = r4;
            if (r103 != (-1)) goto L12;
            float r20 = 0.9f;
        L13:
            float r222 = 1.04f;
            if (r103 != (-1)) goto L16;
            float r232 = r16;
        L17:
            float[] r126 = new float[2];
            r126[r17] = r20;
            r126[r21] = r232;
            ObjectAnimator r36 = ObjectAnimator.ofFloat(r113, r35, r126);
            Property r127 = View.SCALE_Y;
            if (r103 == (-1)) goto L21;
            r18 = 0.6f;
        L21:
            if (r103 != (-1)) goto L23;
            r222 = r16;
        L23:
            float[] r42 = new float[2];
            r42[r17] = r18;
            r42[r21] = r222;
            ObjectAnimator r43 = ObjectAnimator.ofFloat(r113, r127, r42);
            ObjectAnimator r37 = ObjectAnimator.ofFloat(r113, View.ALPHA, new float[]{0.0f, 1.0f});
            Animator[] r114 = new Animator[3];
            r114[r17] = r36;
            r114[r21] = r43;
            r114[2] = r37;
            r26.playTogether(r114);
            InnerAnimator.m11657$$Nest$fgetanimatorSet(r142).addListener(new AnonymousClass1(this, r152));
            AnimatorSet r27 = InnerAnimator.m11657$$Nest$fgetanimatorSet(r142);
            if (r103 != (-1)) goto L26;
            long r38 = 232;
        L27:
            r27.setDuration(r38);
            InnerAnimator.m11657$$Nest$fgetanimatorSet(r142).setInterpolator(new DecelerateInterpolator());
            PasscodeView.m11637$$Nest$fgetinnerAnimators(this.this$0).add(r142);
            r103 = r103 + 1;
            r2 = r16;
            r3 = r17;
            r4 = r21;
            goto L3
        L26:
            r38 = 200;
            goto L27
        L16:
            r232 = 1.04f;
            goto L17
        L12:
            r20 = 0.6f;
            goto L13
        L8:
            r152 = null;
            goto L9
        L28:
            float r162 = r2;
            char r172 = r3;
            int r212 = r4;
            r5.add(ObjectAnimator.ofFloat(PasscodeView.m11631$$Nest$fgetbackgroundFrameLayout(r112), View.ALPHA, new float[]{0.0f, 1.0f}));
            ValueAnimator r28 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r5.add(r28);
            r28.addUpdateListener(new PasscodeView$9$$ExternalSyntheticLambda1(this, r84));
            CubicBezierInterpolator r29 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r1.setInterpolator(r29);
            r1.setDuration(500);
            float[] r44 = new float[2];
            r44[r172] = PasscodeView.m11644$$Nest$fgetshownT(this.this$0);
            r44[r212] = r162;
            ValueAnimator r39 = ValueAnimator.ofFloat(r44);
            r39.addUpdateListener(new PasscodeView$9$$ExternalSyntheticLambda2(this));
            r39.addListener(new AnonymousClass2(this));
            r39.setDuration(420);
            r39.setInterpolator(r29);
            r5.add(r39);
            r1.playTogether(r5);
            r1.addListener(new AnonymousClass3(this));
            r1.start();
            AnimatorSet r13 = new AnimatorSet();
            r13.setDuration(332);
            if (AndroidUtilities.isTablet() == false) goto L31;
        L38:
            float r210 = r7 / 2.0f;
            int r45 = AndroidUtilities.dp(29.0f);
        L37:
            float r211 = r210 - r45;
            RLottieImageView r46 = PasscodeView.m11635$$Nest$fgetimageView(this.this$0);
            Property r52 = View.TRANSLATION_X;
            float[] r72 = new float[2];
            r72[r172] = this.val$x - AndroidUtilities.dp(29.0f);
            r72[r212] = r211;
            ObjectAnimator r213 = ObjectAnimator.ofFloat(r46, r52, r72);
            RLottieImageView r47 = PasscodeView.m11635$$Nest$fgetimageView(this.this$0);
            Property r53 = View.TRANSLATION_Y;
            float r310 = this.val$y - AndroidUtilities.dp(29.0f);
            float r67 = PasscodeView.m11636$$Nest$fgetimageY(this.this$0);
            float[] r73 = new float[2];
            r73[r172] = r310;
            r73[r212] = r67;
            ObjectAnimator r311 = ObjectAnimator.ofFloat(r47, r53, r73);
            ObjectAnimator r48 = ObjectAnimator.ofFloat(PasscodeView.m11635$$Nest$fgetimageView(this.this$0), View.SCALE_X, new float[]{0.5f, 1.0f});
            ObjectAnimator r0 = ObjectAnimator.ofFloat(PasscodeView.m11635$$Nest$fgetimageView(this.this$0), View.SCALE_Y, new float[]{0.5f, 1.0f});
            Animator[] r54 = new Animator[4];
            r54[r172] = r213;
            r54[r212] = r311;
            r54[2] = r48;
            r54[3] = r0;
            r13.playTogether(r54);
            r13.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            r13.start();
            return;
        L31:
            if (this.this$0.getContext().getResources().getConfiguration().orientation != 2) goto L38;
            if (SharedConfig.passcodeType != 0) goto L35;
            float r214 = r7 / 2.0f;
        L36:
            r210 = r214 / 2.0f;
            r45 = AndroidUtilities.dp(30.0f);
            goto L37
        L35:
            r214 = r7;
            goto L36
        }
    }

    public class AnimatingTextView extends FrameLayout {
        private ArrayList<TextView> characterTextViews;
        private AnimatorSet currentAnimation;
        private Runnable dotRunnable;
        private ArrayList<TextView> dotTextViews;
        private StringBuilder stringBuilder;
        final /* synthetic */ PasscodeView this$0;

        /* renamed from: org.telegram.ui.Components.PasscodeView$AnimatingTextView$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ AnimatingTextView this$1;
            final /* synthetic */ int val$newPos;

            /* renamed from: org.telegram.ui.Components.PasscodeView$AnimatingTextView$1$1, reason: invalid class name and collision with other inner class name */
            public class C00531 extends AnimatorListenerAdapter {
                final /* synthetic */ AnonymousClass1 this$2;

                public C00531(AnonymousClass1 r1) {
                    this.this$2 = r1;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r2) {
                    if (AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$2.this$1) != null) goto L5;
                    return;
                L5:
                    if (AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$2.this$1).equals(r2) == false) goto L9;
                    AnimatingTextView.m11655$$Nest$fputcurrentAnimation(this.this$2.this$1, null);
                    return;
                }
            }

            public AnonymousClass1(AnimatingTextView r1, int r2) {
                this.this$1 = r1;
                this.val$newPos = r2;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (AnimatingTextView.m11653$$Nest$fgetdotRunnable(this.this$1) == this) goto L5;
                return;
            L5:
                ArrayList r0 = new ArrayList();
                TextView r1 = (TextView) AnimatingTextView.m11651$$Nest$fgetcharacterTextViews(this.this$1).get(this.val$newPos);
                Property r2 = View.SCALE_X;
                r0.add(ObjectAnimator.ofFloat(r1, r2, new float[]{0.0f}));
                Property r4 = View.SCALE_Y;
                r0.add(ObjectAnimator.ofFloat(r1, r4, new float[]{0.0f}));
                Property r7 = View.ALPHA;
                r0.add(ObjectAnimator.ofFloat(r1, r7, new float[]{0.0f}));
                TextView r12 = (TextView) AnimatingTextView.m11654$$Nest$fgetdotTextViews(this.this$1).get(this.val$newPos);
                r0.add(ObjectAnimator.ofFloat(r12, r2, new float[]{1.0f}));
                r0.add(ObjectAnimator.ofFloat(r12, r4, new float[]{1.0f}));
                r0.add(ObjectAnimator.ofFloat(r12, r7, new float[]{1.0f}));
                AnimatingTextView.m11655$$Nest$fputcurrentAnimation(this.this$1, new AnimatorSet());
                AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$1).setDuration(150);
                AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$1).playTogether(r0);
                AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$1).addListener(new C00531(this));
                AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$1).start();
            }
        }

        /* renamed from: org.telegram.ui.Components.PasscodeView$AnimatingTextView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnimatingTextView this$1;

            public AnonymousClass2(AnimatingTextView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$1) != null) goto L5;
                return;
            L5:
                if (AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$1).equals(r2) == false) goto L9;
                AnimatingTextView.m11655$$Nest$fputcurrentAnimation(this.this$1, null);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.PasscodeView$AnimatingTextView$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ AnimatingTextView this$1;

            public AnonymousClass3(AnimatingTextView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$1) != null) goto L5;
                return;
            L5:
                if (AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$1).equals(r2) == false) goto L9;
                AnimatingTextView.m11655$$Nest$fputcurrentAnimation(this.this$1, null);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.PasscodeView$AnimatingTextView$4, reason: invalid class name */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ AnimatingTextView this$1;

            public AnonymousClass4(AnimatingTextView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$1) != null) goto L5;
                return;
            L5:
                if (AnimatingTextView.m11652$$Nest$fgetcurrentAnimation(this.this$1).equals(r2) == false) goto L9;
                AnimatingTextView.m11655$$Nest$fputcurrentAnimation(this.this$1, null);
                return;
            }
        }

        /* renamed from: -$$Nest$fgetcharacterTextViews, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m11651$$Nest$fgetcharacterTextViews(AnimatingTextView r0) {
            return r0.characterTextViews;
        }

        /* renamed from: -$$Nest$fgetcurrentAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatorSet m11652$$Nest$fgetcurrentAnimation(AnimatingTextView r0) {
            return r0.currentAnimation;
        }

        /* renamed from: -$$Nest$fgetdotRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m11653$$Nest$fgetdotRunnable(AnimatingTextView r0) {
            return r0.dotRunnable;
        }

        /* renamed from: -$$Nest$fgetdotTextViews, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m11654$$Nest$fgetdotTextViews(AnimatingTextView r0) {
            return r0.dotTextViews;
        }

        /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11655$$Nest$fputcurrentAnimation(AnimatingTextView r0, AnimatorSet r1) {
            r0.currentAnimation = r1;
        }

        /* renamed from: -$$Nest$meraseAllCharacters, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11656$$Nest$meraseAllCharacters(AnimatingTextView r0, boolean r1) {
            r0.eraseAllCharacters(r1);
        }

        public AnimatingTextView(PasscodeView r12, Context r13) {
            this.this$0 = r12;
            super(r13);
            this.characterTextViews = new ArrayList(4);
            this.dotTextViews = new ArrayList(4);
            this.stringBuilder = new StringBuilder(4);
            int r122 = 0;
        L3:
            if (r122 >= 4) goto L5;
            TextView r1 = new TextView(r13);
            r1.setTextColor(-1);
            r1.setTypeface(AndroidUtilities.bold());
            r1.setTextSize(1, 36.0f);
            r1.setGravity(17);
            r1.setAlpha(0.0f);
            r1.setPivotX(AndroidUtilities.dp(25.0f));
            r1.setPivotY(AndroidUtilities.dp(25.0f));
            addView(r1, LayoutHelper.createFrame(50, 50, 51));
            this.characterTextViews.add(r1);
            TextView r14 = new TextView(r13);
            r14.setTextColor(-1);
            r14.setTypeface(AndroidUtilities.bold());
            r14.setTextSize(1, 36.0f);
            r14.setGravity(17);
            r14.setAlpha(0.0f);
            r14.setText("•");
            r14.setPivotX(AndroidUtilities.dp(25.0f));
            r14.setPivotY(AndroidUtilities.dp(25.0f));
            addView(r14, LayoutHelper.createFrame(50, 50, 51));
            this.dotTextViews.add(r14);
            r122 = r122 + 1;
            goto L3
        }

        private void eraseAllCharacters(boolean r11) {
            if (this.stringBuilder.length() != 0) goto L5;
            return;
        L5:
            Runnable r0 = this.dotRunnable;
            if (r0 == null) goto L8;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.dotRunnable = null;
        L8:
            AnimatorSet r02 = this.currentAnimation;
            if (r02 == null) goto L11;
            r02.cancel();
            this.currentAnimation = null;
        L11:
            StringBuilder r03 = this.stringBuilder;
            int r2 = 0;
            r03.delete(0, r03.length());
            if (r11 == false) goto L23;
            ArrayList r112 = new ArrayList();
            int r3 = 0;
        L14:
            if (r3 >= 4) goto L22;
            TextView r4 = this.characterTextViews.get(r3);
            float r5 = r4.getAlpha();
            Property r7 = View.ALPHA;
            Property r8 = View.SCALE_Y;
            Property r9 = View.SCALE_X;
            if (r5 == 0.0f) goto L18;
            r112.add(ObjectAnimator.ofFloat(r4, r9, new float[]{0.0f}));
            r112.add(ObjectAnimator.ofFloat(r4, r8, new float[]{0.0f}));
            r112.add(ObjectAnimator.ofFloat(r4, r7, new float[]{0.0f}));
        L18:
            TextView r42 = this.dotTextViews.get(r3);
            if (r42.getAlpha() == 0.0f) goto L21;
            r112.add(ObjectAnimator.ofFloat(r42, r9, new float[]{0.0f}));
            r112.add(ObjectAnimator.ofFloat(r42, r8, new float[]{0.0f}));
            r112.add(ObjectAnimator.ofFloat(r42, r7, new float[]{0.0f}));
        L21:
            r3 = r3 + 1;
            goto L14
        L22:
            AnimatorSet r04 = new AnimatorSet();
            this.currentAnimation = r04;
            r04.setDuration(150);
            this.currentAnimation.playTogether(r112);
            this.currentAnimation.addListener(new AnonymousClass4(this));
            this.currentAnimation.start();
        L25:
            PasscodeView.m11648$$Nest$mcheckTitle(this.this$0);
            return;
        L23:
            if (r2 >= 4) goto L25;
            this.characterTextViews.get(r2).setAlpha(0.0f);
            this.dotTextViews.get(r2).setAlpha(0.0f);
            r2 = r2 + 1;
            goto L23
        }

        private int getXForTextView(int r4) {
            return (((getMeasuredWidth() - (this.stringBuilder.length() * AndroidUtilities.dp(30.0f))) / 2) + (r4 * AndroidUtilities.dp(30.0f))) - AndroidUtilities.dp(10.0f);
        }

        public void appendCharacter(String r14) {
            if (this.stringBuilder.length() != 4) goto L30;
            return;
        L30:
            performHapticFeedback(3);     // Catch: Exception -> L8
        L10:
            ArrayList r0 = new ArrayList();
            int r2 = this.stringBuilder.length();
            this.stringBuilder.append(r14);
            TextView r3 = this.characterTextViews.get(r2);
            r3.setText(r14);
            r3.setTranslationX(getXForTextView(r2));
            Property r5 = View.SCALE_X;
            r0.add(ObjectAnimator.ofFloat(r3, r5, new float[]{0.0f, 1.0f}));
            Property r6 = View.SCALE_Y;
            r0.add(ObjectAnimator.ofFloat(r3, r6, new float[]{0.0f, 1.0f}));
            Property r7 = View.ALPHA;
            r0.add(ObjectAnimator.ofFloat(r3, r7, new float[]{0.0f, 1.0f}));
            Property r12 = View.TRANSLATION_Y;
            r0.add(ObjectAnimator.ofFloat(r3, r12, new float[]{AndroidUtilities.dp(20.0f), 0.0f}));
            TextView r32 = this.dotTextViews.get(r2);
            r32.setTranslationX(getXForTextView(r2));
            r32.setAlpha(0.0f);
            r0.add(ObjectAnimator.ofFloat(r32, r5, new float[]{0.0f, 1.0f}));
            r0.add(ObjectAnimator.ofFloat(r32, r6, new float[]{0.0f, 1.0f}));
            r0.add(ObjectAnimator.ofFloat(r32, r12, new float[]{AndroidUtilities.dp(20.0f), 0.0f}));
            int r142 = r2 + 1;
        L11:
            if (r142 >= 4) goto L19;
            TextView r33 = this.characterTextViews.get(r142);
            if (r33.getAlpha() == 0.0f) goto L15;
            r0.add(ObjectAnimator.ofFloat(r33, r5, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r33, r6, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r33, r7, new float[]{0.0f}));
        L15:
            TextView r34 = this.dotTextViews.get(r142);
            if (r34.getAlpha() == 0.0f) goto L18;
            r0.add(ObjectAnimator.ofFloat(r34, r5, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r34, r6, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r34, r7, new float[]{0.0f}));
        L18:
            r142 = r142 + 1;
            goto L11
        L19:
            Runnable r143 = this.dotRunnable;
            if (r143 == null) goto L22;
            AndroidUtilities.cancelRunOnUIThread(r143);
        L22:
            AnonymousClass1 r144 = new AnonymousClass1(this, r2);
            this.dotRunnable = r144;
            AndroidUtilities.runOnUIThread(r144, 1500);
            int r145 = 0;
        L23:
            if (r145 >= r2) goto L25;
            TextView r1 = this.characterTextViews.get(r145);
            Property r35 = View.TRANSLATION_X;
            r0.add(ObjectAnimator.ofFloat(r1, r35, new float[]{getXForTextView(r145)}));
            r0.add(ObjectAnimator.ofFloat(r1, r5, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r1, r6, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r1, r7, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r1, r12, new float[]{0.0f}));
            TextView r13 = this.dotTextViews.get(r145);
            r0.add(ObjectAnimator.ofFloat(r13, r35, new float[]{getXForTextView(r145)}));
            r0.add(ObjectAnimator.ofFloat(r13, r5, new float[]{1.0f}));
            r0.add(ObjectAnimator.ofFloat(r13, r6, new float[]{1.0f}));
            r0.add(ObjectAnimator.ofFloat(r13, r7, new float[]{1.0f}));
            r0.add(ObjectAnimator.ofFloat(r13, r12, new float[]{0.0f}));
            r145 = r145 + 1;
            goto L23
        L25:
            AnimatorSet r146 = this.currentAnimation;
            if (r146 == null) goto L28;
            r146.cancel();
        L28:
            AnimatorSet r147 = new AnimatorSet();
            this.currentAnimation = r147;
            r147.setDuration(150);
            this.currentAnimation.playTogether(r0);
            this.currentAnimation.addListener(new AnonymousClass2(this));
            this.currentAnimation.start();
            PasscodeView.m11648$$Nest$mcheckTitle(this.this$0);
            return;
        L8:
            e = move-exception;
            FileLog.e(e);
            goto L10
        }

        public boolean eraseLastCharacter() {
            if (this.stringBuilder.length() != 0) goto L36;
            return false;
        L36:
            performHapticFeedback(3);     // Catch: Exception -> L8
        L10:
            ArrayList r0 = new ArrayList();
            int r2 = this.stringBuilder.length() - 1;
            if (r2 == 0) goto L13;
            this.stringBuilder.deleteCharAt(r2);
        L13:
            int r4 = r2;
        L14:
            Property r6 = View.TRANSLATION_X;
            if (r4 >= 4) goto L23;
            TextView r5 = this.characterTextViews.get(r4);
            float r7 = r5.getAlpha();
            Property r9 = View.TRANSLATION_Y;
            Property r10 = View.ALPHA;
            Property r11 = View.SCALE_Y;
            Property r12 = View.SCALE_X;
            if (r7 == 0.0f) goto L19;
            r0.add(ObjectAnimator.ofFloat(r5, r12, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r5, r11, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r5, r10, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r5, r9, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r5, r6, new float[]{getXForTextView(r4)}));
        L19:
            TextView r52 = this.dotTextViews.get(r4);
            if (r52.getAlpha() == 0.0f) goto L22;
            r0.add(ObjectAnimator.ofFloat(r52, r12, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r52, r11, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r52, r10, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r52, r9, new float[]{0.0f}));
            r0.add(ObjectAnimator.ofFloat(r52, r6, new float[]{getXForTextView(r4)}));
        L22:
            r4 = r4 + 1;
            goto L14
        L23:
            if (r2 != 0) goto L25;
            this.stringBuilder.deleteCharAt(r2);
        L25:
            int r42 = 0;
        L26:
            if (r42 >= r2) goto L28;
            r0.add(ObjectAnimator.ofFloat(this.characterTextViews.get(r42), r6, new float[]{getXForTextView(r42)}));
            r0.add(ObjectAnimator.ofFloat(this.dotTextViews.get(r42), r6, new float[]{getXForTextView(r42)}));
            r42 = r42 + 1;
            goto L26
        L28:
            Runnable r1 = this.dotRunnable;
            if (r1 == null) goto L31;
            AndroidUtilities.cancelRunOnUIThread(r1);
            this.dotRunnable = null;
        L31:
            AnimatorSet r13 = this.currentAnimation;
            if (r13 == null) goto L34;
            r13.cancel();
        L34:
            AnimatorSet r14 = new AnimatorSet();
            this.currentAnimation = r14;
            r14.setDuration(150);
            this.currentAnimation.playTogether(r0);
            this.currentAnimation.addListener(new AnonymousClass3(this));
            this.currentAnimation.start();
            PasscodeView.m11648$$Nest$mcheckTitle(this.this$0);
            return true;
        L8:
            e = move-exception;
            FileLog.e(e);
            goto L10
        }

        public String getString() {
            return this.stringBuilder.toString();
        }

        public int length() {
            return this.stringBuilder.length();
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
            Runnable r0 = this.dotRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.dotRunnable = null;
        L5:
            AnimatorSet r02 = this.currentAnimation;
            if (r02 == null) goto L8;
            r02.cancel();
            this.currentAnimation = null;
        L8:
            int r03 = 0;
        L10:
            if (r03 >= 4) goto L16;
            int r1 = this.stringBuilder.length();
            ArrayList<TextView> r2 = this.characterTextViews;
            if (r03 >= r1) goto L14;
            TextView r12 = r2.get(r03);
            r12.setAlpha(0.0f);
            r12.setScaleX(1.0f);
            r12.setScaleY(1.0f);
            r12.setTranslationY(0.0f);
            r12.setTranslationX(getXForTextView(r03));
            TextView r13 = this.dotTextViews.get(r03);
            r13.setAlpha(1.0f);
            r13.setScaleX(1.0f);
            r13.setScaleY(1.0f);
            r13.setTranslationY(0.0f);
            r13.setTranslationX(getXForTextView(r03));
        L15:
            r03 = r03 + 1;
            goto L10
        L14:
            r2.get(r03).setAlpha(0.0f);
            this.dotTextViews.get(r03).setAlpha(0.0f);
            goto L15
        L16:
            super.onLayout(r6, r7, r8, r9, r10);
        }
    }

    public static class InnerAnimator {
        private AnimatorSet animatorSet;
        private float startRadius;

        /* renamed from: -$$Nest$fgetanimatorSet, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatorSet m11657$$Nest$fgetanimatorSet(InnerAnimator r0) {
            return r0.animatorSet;
        }

        /* renamed from: -$$Nest$fgetstartRadius, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11658$$Nest$fgetstartRadius(InnerAnimator r0) {
            return r0.startRadius;
        }

        /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11659$$Nest$fputanimatorSet(InnerAnimator r0, AnimatorSet r1) {
            r0.animatorSet = r1;
        }

        /* renamed from: -$$Nest$fputstartRadius, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11660$$Nest$fputstartRadius(InnerAnimator r0, float r1) {
            r0.startRadius = r1;
        }

        private InnerAnimator() {
        }

        public /* synthetic */ InnerAnimator(PasscodeViewIA r1) {
            this();
        }
    }

    public static class PasscodeButton extends FrameLayout {
        private final ImageView imageView;
        private final TextView textView1;
        private final TextView textView2;

        public PasscodeButton(Context r11) {
            super(r11);
            ImageView r0 = new ImageView(r11);
            this.imageView = r0;
            r0.setScaleType(ImageView.ScaleType.CENTER);
            r0.setImageResource(R.drawable.fingerprint);
            addView(r0, LayoutHelper.createFrame(-1, -1, 119));
            TextView r02 = new TextView(r11);
            this.textView1 = r02;
            r02.setTypeface(AndroidUtilities.bold());
            r02.setTextColor(-1);
            r02.setTextSize(1, 26.0f);
            r02.setGravity(17);
            addView(r02, LayoutHelper.createFrame(-1, -2.0f, 17, 0.0f, -5.33f, 0.0f, 0.0f));
            TextView r03 = new TextView(r11);
            this.textView2 = r03;
            r03.setTypeface(AndroidUtilities.bold());
            r03.setTextSize(1, 10.0f);
            r03.setTextColor(Integer.MAX_VALUE);
            r03.setGravity(17);
            addView(r03, LayoutHelper.createFrame(-1, -2.0f, 17, 0.0f, 14.0f, 0.0f, 0.0f));
        }

        public static String letter(int r0) {
            if (r0 == 0) goto L22;
            switch(r0) {
                case 2: goto L20;
                case 3: goto L18;
                case 4: goto L16;
                case 5: goto L14;
                case 6: goto L12;
                case 7: goto L10;
                case 8: goto L8;
                case 9: goto L6;
                default: goto L4;
            };
        L4:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            return "WXYZ";
        L8:
            return "TUV";
        L10:
            return "PQRS";
        L12:
            return "MNO";
        L14:
            return "JKL";
        L16:
            return "GHI";
        L18:
            return "DEF";
        L20:
            return "ABC";
        L22:
            return "+";
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setClassName("android.widget.Button");
        }

        public void setImage(int r3) {
            this.imageView.setVisibility(0);
            this.textView1.setVisibility(8);
            this.textView2.setVisibility(8);
            this.imageView.setImageResource(r3);
        }

        public void setNum(int r4) {
            this.imageView.setVisibility(8);
            this.textView1.setVisibility(0);
            this.textView2.setVisibility(0);
            this.textView1.setText(_UrlKt.FRAGMENT_ENCODE_SET + r4);
            this.textView2.setText(letter(r4));
        }
    }

    public interface PasscodeViewDelegate {
        void didAcceptedPassword(PasscodeView r1);
    }

    /* renamed from: $r8$lambda$3_-Q0by5QGjA0qmAjLsqM3KfUVg, reason: not valid java name */
    public static /* synthetic */ void m11622$r8$lambda$3_Q0by5QGjA0qmAjLsqM3KfUVg(PasscodeView r0, MotionBackgroundDrawable r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        r0.lambda$animateBackground$8(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$9mkqn0Uq3dZvijWEFSVBxPJDHvI(PasscodeView r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$ES7Ii9gkV2TRjs5CsyVu9FqOfag(MotionBackgroundDrawable r0, DynamicAnimation r1, float r2, float r3) {
        r0.updateAnimation();
    }

    public static /* synthetic */ void $r8$lambda$EVErmssFX0GrkO5iWZFUNShw1t4(PasscodeView r0, boolean r1, MotionBackgroundDrawable r2) {
        r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$H5Dfq1BJIWmILnuepA0ZvnztQII(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$J-id_N5MJTG275S_rFaYa0xgAKA, reason: not valid java name */
    public static /* synthetic */ void m11623$r8$lambda$Jid_N5MJTG275S_rFaYa0xgAKA(PasscodeView r0, Integer r1) {
        r0.lambda$onAttachedToWindow$13(r1);
    }

    /* renamed from: $r8$lambda$OsSY-TnWO7Tr42YKctKsR__D4CA, reason: not valid java name */
    public static /* synthetic */ void m11624$r8$lambda$OsSYTnWO7Tr42YKctKsR__D4CA(PasscodeView r0, ValueAnimator r1) {
        r0.lambda$processDone$10(r1);
    }

    /* renamed from: $r8$lambda$XPV-C5MfgtvYNVIyv7rxO9Na0BY, reason: not valid java name */
    public static /* synthetic */ boolean m11625$r8$lambda$XPVC5MfgtvYNVIyv7rxO9Na0BY(PasscodeView r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$new$0(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$XwH3rO00AdjVez4H4tXn9ep16PU(Integer r0, Integer r1) {
        return r1.intValue() - r0.intValue();
    }

    public static /* synthetic */ void $r8$lambda$Y7oqhnwzouQZDzhEKzECM9wVXdU(PasscodeView r0) {
        r0.lambda$processDone$11();
    }

    /* renamed from: $r8$lambda$Ya_3eTf1xXGX-XBnIZBqjqm5THE, reason: not valid java name */
    public static /* synthetic */ void m11626$r8$lambda$Ya_3eTf1xXGXXBnIZBqjqm5THE(PasscodeView r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: $r8$lambda$aSmqjS28PW-bRillMRdaosqgeR0, reason: not valid java name */
    public static /* synthetic */ void m11627$r8$lambda$aSmqjS28PWbRillMRdaosqgeR0(PasscodeView r0) {
        r0.lambda$onResume$12();
    }

    /* renamed from: $r8$lambda$cfn43g3u10-jCyGpYS99YogUzuI, reason: not valid java name */
    public static /* synthetic */ void m11628$r8$lambda$cfn43g3u10jCyGpYS99YogUzuI(PasscodeView r0, View r1) {
        r0.lambda$new$6(r1);
    }

    /* renamed from: $r8$lambda$kl2AU-pCKPzTW66Xvmtoanaghrk, reason: not valid java name */
    public static /* synthetic */ boolean m11629$r8$lambda$kl2AUpCKPzTW66Xvmtoanaghrk(PasscodeView r0, View r1) {
        return r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$tJfN9VuIdWN5tr7qxUd0JXmjLwY(PasscodeView r0, ValueAnimator r1) {
        r0.lambda$showPin$14(r1);
    }

    public static /* synthetic */ Float $r8$lambda$vudPI5KWfxjpDdhLX42Lk1slYC0(FloatValueHolder r0, MotionBackgroundDrawable r1) {
        return Float.valueOf(r0.getValue() / 100.0f);
    }

    /* renamed from: -$$Nest$fgetbackgroundDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m11630$$Nest$fgetbackgroundDrawable(PasscodeView r0) {
        return r0.backgroundDrawable;
    }

    /* renamed from: -$$Nest$fgetbackgroundFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11631$$Nest$fgetbackgroundFrameLayout(PasscodeView r0) {
        return r0.backgroundFrameLayout;
    }

    /* renamed from: -$$Nest$fgetbackgroundSpringNextQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkedList m11632$$Nest$fgetbackgroundSpringNextQueue(PasscodeView r0) {
        return r0.backgroundSpringNextQueue;
    }

    /* renamed from: -$$Nest$fgetbackgroundSpringQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkedList m11633$$Nest$fgetbackgroundSpringQueue(PasscodeView r0) {
        return r0.backgroundSpringQueue;
    }

    /* renamed from: -$$Nest$fgetcheckRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11634$$Nest$fgetcheckRunnable(PasscodeView r0) {
        return r0.checkRunnable;
    }

    /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m11635$$Nest$fgetimageView(PasscodeView r0) {
        return r0.imageView;
    }

    /* renamed from: -$$Nest$fgetimageY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11636$$Nest$fgetimageY(PasscodeView r0) {
        return r0.imageY;
    }

    /* renamed from: -$$Nest$fgetinnerAnimators, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11637$$Nest$fgetinnerAnimators(PasscodeView r0) {
        return r0.innerAnimators;
    }

    /* renamed from: -$$Nest$fgetkeyboardHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11638$$Nest$fgetkeyboardHeight(PasscodeView r0) {
        return r0.keyboardHeight;
    }

    /* renamed from: -$$Nest$fgetpasscodeTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m11639$$Nest$fgetpasscodeTextView(PasscodeView r0) {
        return r0.passcodeTextView;
    }

    /* renamed from: -$$Nest$fgetpasswordEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m11640$$Nest$fgetpasswordEditText(PasscodeView r0) {
        return r0.passwordEditText;
    }

    /* renamed from: -$$Nest$fgetpasswordEditText2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatingTextView m11641$$Nest$fgetpasswordEditText2(PasscodeView r0) {
        return r0.passwordEditText2;
    }

    /* renamed from: -$$Nest$fgetpos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m11642$$Nest$fgetpos(PasscodeView r0) {
        return r0.pos;
    }

    /* renamed from: -$$Nest$fgetretryTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m11643$$Nest$fgetretryTextView(PasscodeView r0) {
        return r0.retryTextView;
    }

    /* renamed from: -$$Nest$fgetshownT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11644$$Nest$fgetshownT(PasscodeView r0) {
        return r0.shownT;
    }

    /* renamed from: -$$Nest$fputshownT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11645$$Nest$fputshownT(PasscodeView r0, float r1) {
        r0.shownT = r1;
    }

    /* renamed from: -$$Nest$manimateBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11646$$Nest$manimateBackground(PasscodeView r0, MotionBackgroundDrawable r1) {
        r0.animateBackground(r1);
    }

    /* renamed from: -$$Nest$mcheckRetryTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11647$$Nest$mcheckRetryTextView(PasscodeView r0) {
        r0.checkRetryTextView();
    }

    /* renamed from: -$$Nest$mcheckTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11648$$Nest$mcheckTitle(PasscodeView r0) {
        r0.checkTitle();
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11649$$Nest$mprocessDone(PasscodeView r0, boolean r1) {
        r0.processDone(r1);
    }

    /* renamed from: -$$Nest$mshowPin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11650$$Nest$mshowPin(PasscodeView r0, boolean r1) {
        r0.showPin(r1);
    }

    static {
        ids = new int[]{R.id.passcode_btn_0, R.id.passcode_btn_1, R.id.passcode_btn_2, R.id.passcode_btn_3, R.id.passcode_btn_4, R.id.passcode_btn_5, R.id.passcode_btn_6, R.id.passcode_btn_7, R.id.passcode_btn_8, R.id.passcode_btn_9, R.id.passcode_btn_backspace, R.id.passcode_btn_fingerprint};
    }

    public PasscodeView(Context r22) {
        super(r22);
        this.BUTTON_X_MARGIN = 28;
        this.BUTTON_Y_MARGIN = 16;
        this.BUTTON_SIZE = 60;
        this.keyboardHeight = 0;
        this.rect = new Rect();
        this.backgroundSpringQueue = new LinkedList();
        this.backgroundSpringNextQueue = new LinkedList();
        this.innerAnimators = new ArrayList();
        this.shiftDp = -12;
        this.checkRunnable = new AnonymousClass6(this);
        this.resumeCount = 0;
        this.pinShown = true;
        this.pos = new int[2];
        setWillNotDraw(false);
        setVisibility(8);
        AnonymousClass1 r5 = new AnonymousClass1(this, r22);
        this.backgroundFrameLayout = r5;
        r5.setWillNotDraw(false);
        addView(this.backgroundFrameLayout, LayoutHelper.createFrame(-1, -1.0f));
        RLottieImageView r52 = new RLottieImageView(r22);
        this.imageView = r52;
        r52.setAnimation(R.raw.passcode_lock, 58, 58);
        this.imageView.setAutoRepeat(false);
        addView(this.imageView, LayoutHelper.createFrame(58, 58, 51));
        FrameLayout r53 = new FrameLayout(r22);
        this.passwordFrameLayout = r53;
        this.backgroundFrameLayout.addView(r53, LayoutHelper.createFrame(-1, -1.0f));
        TextView r54 = new TextView(r22);
        this.passcodeTextView = r54;
        r54.setTextColor(-1);
        this.passcodeTextView.setTextSize(1, 18.33f);
        this.passcodeTextView.setGravity(1);
        this.passcodeTextView.setTypeface(AndroidUtilities.bold());
        this.passcodeTextView.setAlpha(0.0f);
        this.passwordFrameLayout.addView(this.passcodeTextView, LayoutHelper.createFrame(-2, -2.0f, 81, 0.0f, 0.0f, 0.0f, 128.0f));
        TextView r55 = new TextView(r22);
        this.retryTextView = r55;
        r55.setTextColor(-1);
        this.retryTextView.setTextSize(1, 15.0f);
        this.retryTextView.setGravity(1);
        this.retryTextView.setVisibility(4);
        this.backgroundFrameLayout.addView(this.retryTextView, LayoutHelper.createFrame(-2, -2, 17));
        AnimatingTextView r56 = new AnimatingTextView(this, r22);
        this.passwordEditText2 = r56;
        this.passwordFrameLayout.addView(r56, LayoutHelper.createFrame(-1, -2.0f, 81, 70.0f, 0.0f, 70.0f, 46.0f));
        EditTextBoldCursor r57 = new EditTextBoldCursor(r22);
        this.passwordEditText = r57;
        r57.setTextSize(1, 36.0f);
        this.passwordEditText.setTextColor(-1);
        this.passwordEditText.setMaxLines(1);
        this.passwordEditText.setLines(1);
        this.passwordEditText.setGravity(1);
        this.passwordEditText.setSingleLine(true);
        this.passwordEditText.setImeOptions(6);
        this.passwordEditText.setTypeface(Typeface.DEFAULT);
        this.passwordEditText.setBackgroundDrawable(null);
        this.passwordEditText.setCursorColor(-1);
        this.passwordEditText.setCursorSize(AndroidUtilities.dp(32.0f));
        this.passwordFrameLayout.addView(this.passwordEditText, LayoutHelper.createFrame(-1, -2.0f, 81, 70.0f, 0.0f, 70.0f, 0.0f));
        this.passwordEditText.setOnEditorActionListener(new PasscodeView$$ExternalSyntheticLambda1(this));
        this.passwordEditText.addTextChangedListener(new AnonymousClass2(this));
        this.passwordEditText.setCustomSelectionActionModeCallback(new AnonymousClass3(this));
        ImageView r58 = new ImageView(r22);
        this.checkImage = r58;
        r58.setImageResource(R.drawable.passcode_check);
        ImageView r59 = this.checkImage;
        ImageView.ScaleType r10 = ImageView.ScaleType.CENTER;
        r59.setScaleType(r10);
        this.checkImage.setBackgroundResource(R.drawable.bar_selector_lock);
        this.passwordFrameLayout.addView(this.checkImage, LayoutHelper.createFrame(60, 60.0f, 85, 0.0f, 0.0f, 10.0f, 4.0f));
        this.checkImage.setContentDescription(LocaleController.getString(R.string.Done));
        this.checkImage.setOnClickListener(new PasscodeView$$ExternalSyntheticLambda2(this));
        ImageView r510 = new ImageView(r22);
        this.fingerprintImage = r510;
        r510.setImageResource(R.drawable.fingerprint);
        this.fingerprintImage.setScaleType(r10);
        this.fingerprintImage.setBackgroundResource(R.drawable.bar_selector_lock);
        this.passwordFrameLayout.addView(this.fingerprintImage, LayoutHelper.createFrame(60, 60.0f, 83, 10.0f, 0.0f, 0.0f, 4.0f));
        this.fingerprintImage.setContentDescription(LocaleController.getString(R.string.AccDescrFingerprint));
        this.fingerprintImage.setOnClickListener(new PasscodeView$$ExternalSyntheticLambda3(this));
        View r511 = new View(r22);
        this.border = r511;
        r511.setBackgroundColor(822083583);
        this.passwordFrameLayout.addView(this.border, LayoutHelper.createFrame(-1.0f, 1.0f / AndroidUtilities.density, 87));
        FrameLayout r512 = new FrameLayout(r22);
        this.numbersContainer = r512;
        this.backgroundFrameLayout.addView(r512, LayoutHelper.createFrame(-1, -1, 51));
        AnonymousClass4 r513 = new AnonymousClass4(this, r22);
        this.numbersFrameLayout = r513;
        this.numbersContainer.addView(r513, LayoutHelper.createFrame(-2, -2, 17));
        FrameLayout r514 = new FrameLayout(r22);
        this.numbersTitleContainer = r514;
        this.numbersFrameLayout.addView(r514, LayoutHelper.createFrame(-2, -2, 49));
        TextView r515 = new TextView(r22);
        r515.setTextSize(1, 15.0f);
        r515.setTypeface(AndroidUtilities.bold());
        r515.setTextColor(-1);
        r515.setText(LocaleController.getString(R.string.UnlockToUse));
        this.numbersTitleContainer.addView(r515, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r516 = new TextView(r22);
        this.subtitleView = r516;
        r516.setTextSize(1, 14.0f);
        this.subtitleView.setTextColor(-1);
        this.subtitleView.setText(LocaleController.getString(R.string.EnterPINorFingerprint));
        this.numbersTitleContainer.addView(this.subtitleView, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 23.0f, 0.0f, 0.0f));
        this.numberFrameLayouts = new ArrayList(10);
        int r4 = 0;
    L3:
        int r7 = 11;
        if (r4 >= 12) goto L21;
        PasscodeButton r6 = new PasscodeButton(r22);
        ScaleStateListAnimator.apply(r6, 0.15f, 1.5f);
        r6.setTag(Integer.valueOf(r4));
        if (r4 != 11) goto L8;
        r6.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(30.0f), 0, 654311423));
        r6.setImage(R.drawable.filled_clear);
        r6.setOnLongClickListener(new PasscodeView$$ExternalSyntheticLambda4(this));
        r6.setContentDescription(LocaleController.getString(R.string.AccDescrBackspace));
        setNextFocus(r6, R.id.passcode_btn_0);
    L20:
        r6.setId(ids[r4]);
        r6.setOnClickListener(new PasscodeView$$ExternalSyntheticLambda5(this));
        this.numberFrameLayouts.add(r6);
        r4 = r4 + 1;
        goto L3
    L8:
        if (r4 != 10) goto L10;
        this.fingerprintView = r6;
        r6.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(30.0f), 0, 654311423));
        r6.setContentDescription(LocaleController.getString(R.string.AccDescrFingerprint));
        r6.setImage(R.drawable.fingerprint);
        setNextFocus(r6, R.id.passcode_btn_1);
        goto L20
    L10:
        r6.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(30.0f), 654311423, 1291845631));
        r6.setContentDescription(r4 + _UrlKt.FRAGMENT_ENCODE_SET);
        r6.setNum(r4);
        if (r4 != 0) goto L14;
        setNextFocus(r6, R.id.passcode_btn_backspace);
        goto L20
    L14:
        if (r4 == 9) goto L16;
        setNextFocus(r6, ids[r4 + 1]);
        goto L20
    L16:
        if (hasFingerprint() == false) goto L18;
        setNextFocus(r6, R.id.passcode_btn_fingerprint);
        goto L20
    L18:
        setNextFocus(r6, R.id.passcode_btn_0);
    L21:
        if (r7 < 0) goto L23;
        this.numbersFrameLayout.addView(this.numberFrameLayouts.get(r7), LayoutHelper.createFrame(60, 60, 51));
        r7 = r7 - 1;
        goto L21
    L23:
        checkFingerprintButton();
    }

    private void animateBackground(MotionBackgroundDrawable r4) {
        SpringAnimation r0 = this.backgroundAnimationSpring;
        if (r0 != null) goto L5;
    L7:
        FloatValueHolder r02 = new FloatValueHolder(0.0f);
        r4.setAnimationProgressProvider(new PasscodeView$$ExternalSyntheticLambda13(r02));
        SpringAnimation r03 = new SpringAnimation(r02).setSpring(new SpringForce(100.0f).setStiffness(300.0f).setDampingRatio(1.0f));
        this.backgroundAnimationSpring = r03;
        r03.addEndListener(new PasscodeView$$ExternalSyntheticLambda14(this, r4));
        this.backgroundAnimationSpring.addUpdateListener(new PasscodeView$$ExternalSyntheticLambda15(r4));
        this.backgroundAnimationSpring.start();
        return;
    L5:
        if (r0.isRunning() == false) goto L7;
        this.backgroundAnimationSpring.cancel();
        goto L7
    }

    private void checkFingerprint() {
        Activity r0 = AndroidUtilities.findActivity(getContext());
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.fingerprintView.getVisibility() == 0) goto L7;
        return;
    L7:
        if (ApplicationLoader.mainInterfacePaused == false) goto L9;
        return;
    L9:
        if ((r0 instanceof LaunchActivity) == true) goto L11;
    L23:
    L20:
        e = move-exception;
        FileLog.e(e);
        return;
    L13:
        if (BiometricManager.from(getContext()).canAuthenticate(15) == 0) goto L15;
        return;
    L15:
        if (FingerprintController.isKeyReady() == true) goto L17;
        return;
    L17:
        if (FingerprintController.checkDeviceFingerprintsChanged() == true) goto L31;
        Executor r02 = ContextCompat.getMainExecutor(getContext());     // Catch: Exception -> L20
        new BiometricPrompt(LaunchActivity.instance, r02, new AnonymousClass8(this)).authenticate(new BiometricPrompt.PromptInfo.Builder().setTitle(LocaleController.getString(R.string.UnlockToUse)).setNegativeButtonText(LocaleController.getString(R.string.UsePIN)).setAllowedAuthenticators(15).build());     // Catch: Exception -> L20
        showPin(false);     // Catch: Exception -> L20
        return;
    L31:
        return;
    L11:
        if (((LaunchActivity) r0).allowShowFingerprintDialog(this) == true) goto L23;
    }

    private void checkFingerprintButton() {
        boolean r3 = false;
        if (AndroidUtilities.findActivity(getContext()) != null) goto L5;
    L23:
        this.fingerprintView.setVisibility(8);
    L25:
        if (SharedConfig.passcodeType != 1) goto L27;
        this.fingerprintImage.setVisibility(this.fingerprintView.getVisibility());
    L27:
        TextView r5 = this.subtitleView;
        if (r3 == false) goto L30;
        int r0 = R.string.EnterPINorFingerprint;
    L31:
        r5.setText(LocaleController.getString(r0));
        return;
    L30:
        r0 = R.string.EnterPIN;
        goto L31
    L5:
        if (SharedConfig.useFingerprintLock == false) goto L23;
        FingerprintManagerCompat r02 = FingerprintManagerCompat.from(ApplicationLoader.applicationContext);     // Catch: Throwable -> L18
        if (r02.isHardwareDetected() == true) goto L9;
    L20:
        this.fingerprintView.setVisibility(8);     // Catch: Throwable -> L18
        goto L25
    L9:
        if (r02.hasEnrolledFingerprints() == false) goto L20;
        if (FingerprintController.isKeyReady() == false) goto L20;
        if (FingerprintController.checkDeviceFingerprintsChanged() == true) goto L20;
        this.fingerprintView.setVisibility(0);     // Catch: Throwable -> L16
        r3 = true;
    L16:
        th = th;
        r3 = true;
    L22:
        FileLog.e(th);
        this.fingerprintView.setVisibility(8);
    L18:
        th = th;
        goto L22
    }

    private void checkRetryTextView() {
        long r0 = SystemClock.elapsedRealtime();
        if (r0 <= SharedConfig.lastUptimeMillis) goto L7;
        long r5 = SharedConfig.passcodeRetryInMs - (r0 - SharedConfig.lastUptimeMillis);
        SharedConfig.passcodeRetryInMs = r5;
        if (r5 >= 0) goto L7;
        SharedConfig.passcodeRetryInMs = 0;
    L7:
        SharedConfig.lastUptimeMillis = r0;
        SharedConfig.saveConfig();
        long r02 = SharedConfig.passcodeRetryInMs;
        if (r02 <= 0) goto L17;
        int r03 = Math.max(1, (int) Math.ceil(r02 / 1000.0d));
        if (r03 == this.lastValue) goto L13;
        this.retryTextView.setText(LocaleController.formatString(R.string.TooManyTries, new Object[]{LocaleController.formatPluralString("Seconds", r03, new Object[0])}));
        this.lastValue = r03;
    L13:
        if (this.retryTextView.getVisibility() == 0) goto L15;
        this.retryTextView.setVisibility(0);
        this.passwordFrameLayout.setVisibility(4);
        showPin(false);
        AndroidUtilities.hideKeyboard(this.passwordEditText);
    L15:
        AndroidUtilities.cancelRunOnUIThread(this.checkRunnable);
        AndroidUtilities.runOnUIThread(this.checkRunnable, 100);
        return;
    L17:
        AndroidUtilities.cancelRunOnUIThread(this.checkRunnable);
        if (this.retryTextView.getVisibility() != 0) goto L23;
        this.retryTextView.setVisibility(4);
        this.passwordFrameLayout.setVisibility(0);
        showPin(true);
        if (SharedConfig.passcodeType != 1) goto L24;
        AndroidUtilities.showKeyboard(this.passwordEditText);
        return;
    L24:
        return;
    }

    private void checkTitle() {
        AnimatingTextView r0 = this.passwordEditText2;
        if (r0 != null) goto L5;
    L8:
        boolean r02 = true;
    L9:
        FrameLayout r1 = this.numbersTitleContainer;
        if (r1 == null) goto L24;
        r1.animate().cancel();
        ViewPropertyAnimator r4 = this.numbersTitleContainer.animate();
        float r12 = 1.0f;
        if (r02 == false) goto L14;
        float r2 = 0.0f;
    L15:
        ViewPropertyAnimator r42 = r4.alpha(r2);
        if (r02 == false) goto L18;
        float r3 = 0.8f;
    L19:
        ViewPropertyAnimator r43 = r42.scaleX(r3);
        if (r02 == false) goto L22;
        r12 = 0.8f;
    L22:
        r43.scaleY(r12).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320).start();
        return;
    L18:
        r3 = 1.0f;
        goto L19
    L14:
        r2 = 1.0f;
        goto L15
    L24:
        return;
    L5:
        if (r0.length() > 0) goto L8;
        r02 = false;
        goto L9
    }

    private boolean hasFingerprint() {
        if (AndroidUtilities.findActivity(getContext()) != null) goto L5;
    L20:
        return false;
    L5:
        if (SharedConfig.useFingerprintLock == false) goto L20;
        FingerprintManagerCompat r2 = FingerprintManagerCompat.from(ApplicationLoader.applicationContext);     // Catch: Throwable -> L16
        if (r2.isHardwareDetected() == true) goto L9;
    L18:
        return false;
    L9:
        if (r2.hasEnrolledFingerprints() == false) goto L18;
        if (FingerprintController.isKeyReady() == false) goto L18;
        if (FingerprintController.checkDeviceFingerprintsChanged() == true) goto L18;
        return true;
    L16:
        th = move-exception;
        FileLog.e(th);
        goto L20
    }

    private /* synthetic */ void lambda$animateBackground$8(MotionBackgroundDrawable r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        this.backgroundAnimationSpring = null;
        r1.setAnimationProgressProvider(null);
        if (r3 == true) goto L8;
        r1.setPosAnimationProgress(1.0f);
        if (this.backgroundSpringQueue.isEmpty() == true) goto L9;
        this.backgroundSpringQueue.poll().run();
        this.backgroundSpringNextQueue.poll();
        return;
    L9:
        return;
    }

    private /* synthetic */ boolean lambda$new$0(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        processDone(false);
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        processDone(false);
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        checkFingerprint();
    }

    private /* synthetic */ boolean lambda$new$3(View r2) {
        this.passwordEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        AnimatingTextView.m11656$$Nest$meraseAllCharacters(this.passwordEditText2, true);
        Drawable r1 = this.backgroundDrawable;
        if ((r1 instanceof MotionBackgroundDrawable) == false) goto L5;
        ((MotionBackgroundDrawable) r1).switchToPrevPosition(true);
    L5:
        return true;
    }

    private /* synthetic */ void lambda$new$4(boolean r2, MotionBackgroundDrawable r3) {
        if (r2 == false) goto L5;
        r3.switchToNextPosition(true);
    L6:
        animateBackground(r3);
        return;
    L5:
        r3.switchToPrevPosition(true);
        goto L6
    }

    private /* synthetic */ void lambda$new$6(View r7) {
        if (this.pinShown == false) goto L64;
        int r72 = ((Integer) r7.getTag()).intValue();
        int r0 = 0;
        switch(r72) {
            case 0: goto L19;
            case 1: goto L18;
            case 2: goto L17;
            case 3: goto L16;
            case 4: goto L15;
            case 5: goto L14;
            case 6: goto L13;
            case 7: goto L12;
            case 8: goto L11;
            case 9: goto L10;
            case 10: goto L9;
            case 11: goto L8;
            default: goto L20;
        };
    L8:
        boolean r1 = this.passwordEditText2.eraseLastCharacter();
    L22:
        if (this.passwordEditText2.length() != 4) goto L25;
        processDone(false);
    L25:
        if (r72 == 11) goto L65;
        Drawable r2 = this.backgroundDrawable;
        if ((r2 instanceof MotionBackgroundDrawable) == false) goto L56;
        MotionBackgroundDrawable r22 = (MotionBackgroundDrawable) r2;
        r22.setAnimationProgressProvider(null);
        float r3 = r22.getPosAnimationProgress();
        boolean r5 = true;
        if (r72 != 10) goto L35;
        if (r1 == false) goto L33;
        r22.switchToPrevPosition(true);
    L34:
        boolean r73 = false;
    L36:
        if (r5 == true) goto L38;
        return;
    L38:
        if (r3 < 1.0f) goto L41;
        animateBackground(r22);
        return;
    L41:
        this.backgroundSpringQueue.offer(new PasscodeView$$ExternalSyntheticLambda10(this, r73, r22));
        this.backgroundSpringNextQueue.offer(Boolean.valueOf(r73));
        ArrayList r12 = new ArrayList();
        ArrayList r23 = new ArrayList();
        int r32 = 0;
    L43:
        if (r32 >= this.backgroundSpringQueue.size()) goto L50;
        Runnable r4 = this.backgroundSpringQueue.get(r32);
        Boolean r52 = this.backgroundSpringNextQueue.get(r32);
        if (r52 == null) goto L49;
        if (r52.booleanValue() == r73) goto L49;
        r12.add(r4);
        r23.add(Integer.valueOf(r32));
    L49:
        r32 = r32 + 1;
        goto L43
    L50:
        int r74 = r12.size();
        int r33 = 0;
    L51:
        if (r33 >= r74) goto L53;
        Object r42 = r12.get(r33);
        r33 = r33 + 1;
        this.backgroundSpringQueue.remove((Runnable) r42);
        goto L51
    L53:
        Collections.sort(r23, new PasscodeView$$ExternalSyntheticLambda11());
        int r75 = r23.size();
    L54:
        if (r0 >= r75) goto L66;
        Object r13 = r23.get(r0);
        r0 = r0 + 1;
        this.backgroundSpringNextQueue.remove(((Integer) r13).intValue());
        goto L54
    L66:
        return;
    L33:
        r5 = false;
        goto L34
    L35:
        r22.switchToNextPosition(true);
        r73 = true;
        goto L36
    L56:
        return;
    L65:
        return;
    L9:
        checkFingerprint();
        goto L20
    L10:
        this.passwordEditText2.appendCharacter("9");
        goto L20
    L11:
        this.passwordEditText2.appendCharacter("8");
        goto L20
    L12:
        this.passwordEditText2.appendCharacter("7");
        goto L20
    L13:
        this.passwordEditText2.appendCharacter("6");
        goto L20
    L14:
        this.passwordEditText2.appendCharacter("5");
        goto L20
    L15:
        this.passwordEditText2.appendCharacter("4");
        goto L20
    L16:
        this.passwordEditText2.appendCharacter("3");
        goto L20
    L17:
        this.passwordEditText2.appendCharacter("2");
        goto L20
    L18:
        this.passwordEditText2.appendCharacter("1");
        goto L20
    L19:
        this.passwordEditText2.appendCharacter(MVEL.VERSION_SUB);
    L20:
        r1 = false;
        goto L22
    }

    private /* synthetic */ void lambda$onAttachedToWindow$13(Integer r9) {
        if (getContext() != null) goto L6;
        return;
    L6:
        if (getContext().getResources().getConfiguration().orientation != 2) goto L8;
        boolean r0 = true;
    L9:
        int r92 = r9.intValue() - AndroidUtilities.navigationBarHeight;
        if (SharedConfig.passcodeType != 1) goto L24;
        ViewPropertyAnimator r1 = this.passwordFrameLayout.animate();
        float r4 = 1.0f;
        if (r92 > AndroidUtilities.dp(20.0f)) goto L14;
        float r3 = 0.0f;
    L18:
        ViewPropertyAnimator r02 = r1.translationY(r3).setDuration(320);
        CubicBezierInterpolator r12 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r02.setInterpolator(r12).start();
        ViewPropertyAnimator r8 = this.imageView.animate();
        if (r92 <= AndroidUtilities.dp(20.0f)) goto L22;
        r4 = 0.0f;
    L22:
        r8.alpha(r4).setDuration(320).setInterpolator(r12);
        return;
    L14:
        float r6 = 2.0f;
        float r32 = (getHeight() - r92) / 2.0f;
        float r7 = this.passwordFrameLayout.getHeight();
        if (r0 == false) goto L17;
        r6 = 1.0f;
    L17:
        r3 = (r32 - (r7 / r6)) - this.passwordFrameLayout.getTop();
        goto L18
    L24:
        return;
    L8:
        r0 = false;
        goto L9
    }

    private /* synthetic */ void lambda$onResume$12() {
        if (this.retryTextView.getVisibility() == 0) goto L8;
        EditTextBoldCursor r0 = this.passwordEditText;
        if (r0 == null) goto L9;
        r0.requestFocus();
        AndroidUtilities.showKeyboard(this.passwordEditText);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$processDone$10(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.shownT = r12;
        onAnimationUpdate(r12);
        setAlpha(this.shownT);
    }

    private /* synthetic */ void lambda$processDone$11() {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{this.shownT, 0.0f});
        r0.addUpdateListener(new PasscodeView$$ExternalSyntheticLambda12(this));
        r0.addListener(new AnonymousClass5(this));
        r0.setDuration(420);
        r0.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r0.start();
    }

    private /* synthetic */ void lambda$showPin$14(ValueAnimator r6) {
        float r62 = ((Float) r6.getAnimatedValue()).floatValue();
        this.numbersFrameLayout.setScaleX(AndroidUtilities.lerp(0.8f, 1.0f, r62));
        this.numbersFrameLayout.setScaleY(AndroidUtilities.lerp(0.8f, 1.0f, r62));
        this.numbersFrameLayout.setAlpha(AndroidUtilities.lerp(0.0f, 1.0f, r62));
        this.passcodeTextView.setScaleX(AndroidUtilities.lerp(1.0f, 0.9f, r62));
        this.passcodeTextView.setScaleY(AndroidUtilities.lerp(1.0f, 0.9f, r62));
        this.passcodeTextView.setAlpha(AndroidUtilities.lerp(1.0f, 0.0f, r62));
        this.passwordEditText2.setAlpha(AndroidUtilities.lerp(0.0f, 1.0f, r62));
    }

    private void onPasscodeError() {
        BotWebViewVibrationEffect.NOTIFICATION_ERROR.vibrate();
        shakeTextView(2.0f, 0);
    }

    private void processDone(boolean r7) {
        if (r7 == false) goto L4;
    L30:
        SharedConfig.badPasscodeTries = 0;
        this.passwordEditText.clearFocus();
        AndroidUtilities.hideKeyboard(this.passwordEditText);
        if (FingerprintController.isKeyReady() == true) goto L33;
    L35:
        SharedConfig.appLocked = false;
        SharedConfig.saveConfig();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetPasscode, new Object[0]);
        setOnTouchListener(null);
        PasscodeViewDelegate r0 = this.delegate;
        if (r0 == null) goto L38;
        r0.didAcceptedPassword(this);
    L38:
        this.imageView.getAnimatedDrawable().setCustomEndFrame(71);
        this.imageView.getAnimatedDrawable().setCurrentFrame(37, false);
        this.imageView.playAnimation();
        AndroidUtilities.runOnUIThread(new PasscodeView$$ExternalSyntheticLambda9(this));
        return;
    L33:
        if (FingerprintController.checkDeviceFingerprintsChanged() == false) goto L35;
        FingerprintController.deleteInvalidKey();
        goto L35
    L4:
        if (SharedConfig.passcodeRetryInMs > 0) goto L42;
        int r72 = SharedConfig.passcodeType;
        if (r72 != 0) goto L9;
        String r73 = this.passwordEditText2.getString();
    L13:
        if (r73.length() != 0) goto L17;
        onPasscodeError();
        return;
    L17:
        if (SharedConfig.checkPasscode(r73) == true) goto L30;
        SharedConfig.increaseBadPasscodeTries();
        if (SharedConfig.passcodeRetryInMs <= 0) goto L21;
        checkRetryTextView();
    L21:
        this.passwordEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        AnimatingTextView.m11656$$Nest$meraseAllCharacters(this.passwordEditText2, true);
        onPasscodeError();
        Drawable r74 = this.backgroundDrawable;
        if ((r74 instanceof MotionBackgroundDrawable) == false) goto L40;
        MotionBackgroundDrawable r75 = (MotionBackgroundDrawable) r74;
        SpringAnimation r6 = this.backgroundAnimationSpring;
        if (r6 == null) goto L27;
        r6.cancel();
        r75.setPosAnimationProgress(1.0f);
    L27:
        if (r75.getPosAnimationProgress() < 1.0f) goto L41;
        r75.rotatePreview(true);
        return;
    L41:
        return;
    L40:
        return;
    L9:
        if (r72 != 1) goto L11;
        r73 = this.passwordEditText.getText().toString();
        goto L13
    L11:
        r73 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    }

    private void setNextFocus(View r1, int r2) {
        r1.setNextFocusForwardId(r2);
        r1.setAccessibilityTraversalBefore(r2);
    }

    private void shakeTextView(float r1, int r2) {
        if (r2 != 6) goto L5;
        return;
    L5:
        FrameLayout r12 = this.numbersTitleContainer;
        int r22 = -this.shiftDp;
        this.shiftDp = r22;
        AndroidUtilities.shakeViewSpring(r12, r22);
    }

    private void showPin(boolean r5) {
        ValueAnimator r0 = this.pinAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.pinShown = r5;
        float r02 = this.numbersFrameLayout.getAlpha();
        if (r5 == false) goto L8;
        float r1 = 1.0f;
    L9:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.pinAnimator = r03;
        r03.addUpdateListener(new PasscodeView$$ExternalSyntheticLambda7(this));
        this.pinAnimator.addListener(new AnonymousClass7(this, r5));
        this.pinAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.pinAnimator.setDuration(320);
        this.pinAnimator.start();
        return;
    L8:
        r1 = 0.0f;
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.didGenerateFingerprintKeyPair) goto L11;
        checkFingerprintButton();
        if (((Boolean) r4[0]).booleanValue() == true) goto L7;
        return;
    L7:
        if (SharedConfig.appLocked == false) goto L17;
        checkFingerprint();
        return;
    L17:
        return;
    L11:
        if (r2 == NotificationCenter.passcodeDismissed) goto L13;
        return;
    L13:
        if (r4[0] == this) goto L19;
        setVisibility(8);
        return;
    }

    public void onAnimationUpdate(float r1) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didGenerateFingerprintKeyPair);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.passcodeDismissed);
        if (this.keyboardNotifier == null) goto L5;
        return;
    L5:
        if ((getParent() instanceof View) == false) goto L9;
        this.keyboardNotifier = new KeyboardNotifier((View) getParent(), new PasscodeView$$ExternalSyntheticLambda6(this));
        return;
    }

    public boolean onBackPressed() {
        KeyboardNotifier r0 = this.keyboardNotifier;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.keyboardVisible() == false) goto L10;
        AndroidUtilities.hideKeyboard(this.passwordEditText);
        return false;
    L10:
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didGenerateFingerprintKeyPair);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.passcodeDismissed);
    }

    public void onHidden() {
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
        View r0 = getRootView();
        int r1 = (r0.getHeight() - AndroidUtilities.statusBarHeight) - AndroidUtilities.getViewInset(r0);
        getWindowVisibleDisplayFrame(this.rect);
        Rect r02 = this.rect;
        this.keyboardHeight = r1 - (r02.bottom - r02.top);
        if (SharedConfig.passcodeType == 1) goto L5;
    L13:
        super.onLayout(r6, r7, r8, r9, r10);
        this.passcodeTextView.getLocationInWindow(this.pos);
        if (AndroidUtilities.isTablet() == false) goto L16;
    L19:
        RLottieImageView r62 = this.imageView;
        int r82 = this.pos[1] - AndroidUtilities.dp(100.0f);
        this.imageY = r82;
        r62.setTranslationY(r82);
        return;
    L16:
        if (getContext().getResources().getConfiguration().orientation != 2) goto L19;
        RLottieImageView r63 = this.imageView;
        int r83 = this.pos[1] - AndroidUtilities.dp(100.0f);
        this.imageY = r83;
        r63.setTranslationY(r83);
        return;
    L5:
        if (AndroidUtilities.isTablet() == true) goto L9;
        if (getContext().getResources().getConfiguration().orientation == 2) goto L13;
    L9:
        if (this.passwordFrameLayout.getTag() == null) goto L11;
        int r03 = ((Integer) this.passwordFrameLayout.getTag()).intValue();
    L12:
        FrameLayout.LayoutParams r3 = (FrameLayout.LayoutParams) this.passwordFrameLayout.getLayoutParams();
        r3.topMargin = ((r03 + r3.height) - (this.keyboardHeight / 2)) - AndroidUtilities.statusBarHeight;
        this.passwordFrameLayout.setLayoutParams(r3);
        goto L13
    L11:
        r03 = 0;
        goto L12
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r20, int r21) {
        int r1 = View.MeasureSpec.getSize(r20);
        int r2 = AndroidUtilities.displaySize.y;
        int r3 = AndroidUtilities.dp(28.0f);
        int r4 = AndroidUtilities.dp(16.0f);
        int r5 = AndroidUtilities.dp(60.0f);
        int r9 = 0;
        if (AndroidUtilities.isTablet() == false) goto L5;
    L7:
        boolean r6 = false;
    L8:
        View r10 = this.border;
        if (r10 != null) goto L11;
    L15:
        RLottieImageView r7 = this.imageView;
        float r13 = 82.0f;
        if (r6 == true) goto L18;
        r7.setTranslationX((r1 / 2.0f) - AndroidUtilities.dp(29.0f));
        int r72 = AndroidUtilities.statusBarHeight;
        if (AndroidUtilities.isTablet() == true) goto L33;
        int r11 = 0;
    L40:
        FrameLayout.LayoutParams r12 = (FrameLayout.LayoutParams) this.passwordFrameLayout.getLayoutParams();
        int r14 = r2 / 3;
        if (SharedConfig.passcodeType != 0) goto L43;
        int r16 = AndroidUtilities.dp(40.0f);
    L44:
        r12.height = r14 + r16;
        r12.width = r1;
        r12.topMargin = r72;
        r12.leftMargin = r11;
        this.passwordFrameLayout.setTag(Integer.valueOf(r72));
        this.passwordFrameLayout.setLayoutParams(r12);
        int r73 = r12.topMargin + r12.height;
        FrameLayout.LayoutParams r122 = (FrameLayout.LayoutParams) this.numbersFrameLayout.getLayoutParams();
        r122.height = (AndroidUtilities.dp(82.0f) + (r5 * 4)) + (Math.max(0, 3) * r4);
        r122.width = (r5 * 3) + (Math.max(0, 2) * r3);
        if (AndroidUtilities.isTablet() == false) goto L47;
        r122.gravity = 17;
    L48:
        this.numbersFrameLayout.setLayoutParams(r122);
        int r102 = r2 - r122.height;
        FrameLayout.LayoutParams r123 = (FrameLayout.LayoutParams) this.numbersContainer.getLayoutParams();
        r123.leftMargin = r11;
        if (AndroidUtilities.isTablet() == false) goto L51;
        r123.topMargin = (r2 - r102) / 2;
    L52:
        r123.width = r1;
        r123.height = -1;
        this.numbersContainer.setLayoutParams(r123);
    L53:
        if (r6 == false) goto L55;
        r13 = 52.0f;
    L55:
        int r15 = AndroidUtilities.dp(r13);
    L57:
        if (r9 >= 12) goto L68;
        int r22 = 10;
        if (r9 == 0) goto L67;
        if (r9 != 10) goto L64;
        r22 = 11;
        goto L67
    L64:
        if (r9 != 11) goto L66;
        r22 = 9;
        goto L67
    L66:
        r22 = r9 - 1;
    L67:
        FrameLayout r74 = this.numberFrameLayouts.get(r9);
        FrameLayout.LayoutParams r8 = (FrameLayout.LayoutParams) r74.getLayoutParams();
        r8.topMargin = ((r5 + r4) * (r22 / 3)) + r15;
        r8.leftMargin = (r5 + r3) * (r22 % 3);
        r74.setLayoutParams(r8);
        r9 = r9 + 1;
        goto L57
    L68:
        super.onMeasure(r20, r21);
        return;
    L51:
        r123.topMargin = r73;
        goto L52
    L47:
        r122.gravity = 49;
        goto L48
    L43:
        r16 = 0;
        goto L44
    L33:
        if (r1 <= AndroidUtilities.dp(498.0f)) goto L35;
        r11 = (r1 - AndroidUtilities.dp(498.0f)) / 2;
        r1 = AndroidUtilities.dp(498.0f);
    L37:
        if (r2 <= AndroidUtilities.dp(528.0f)) goto L40;
        r72 = (r2 - AndroidUtilities.dp(528.0f)) / 2;
        r2 = AndroidUtilities.dp(528.0f);
        goto L40
    L35:
        r11 = 0;
        goto L37
    L18:
        if (SharedConfig.passcodeType != 0) goto L20;
        float r162 = 40.0f;
        float r112 = r1 / 2.0f;
    L21:
        r7.setTranslationX((r112 / 2.0f) - AndroidUtilities.dp(29.0f));
        FrameLayout.LayoutParams r75 = (FrameLayout.LayoutParams) this.passwordFrameLayout.getLayoutParams();
        if (SharedConfig.passcodeType != 0) goto L24;
        int r113 = r1 / 2;
    L25:
        r75.width = r113;
        r75.height = AndroidUtilities.dp(180.0f);
        int r114 = (r2 - AndroidUtilities.dp(140.0f)) / 2;
        if (SharedConfig.passcodeType != 0) goto L28;
        int r124 = AndroidUtilities.dp(r162);
    L29:
        r75.topMargin = r114 + r124;
        this.passwordFrameLayout.setLayoutParams(r75);
        FrameLayout.LayoutParams r76 = (FrameLayout.LayoutParams) this.numbersContainer.getLayoutParams();
        r76.height = r2;
        int r17 = r1 / 2;
        r76.leftMargin = r17;
        r76.topMargin = AndroidUtilities.statusBarHeight;
        r76.width = r17;
        this.numbersContainer.setLayoutParams(r76);
        FrameLayout.LayoutParams r18 = (FrameLayout.LayoutParams) this.numbersFrameLayout.getLayoutParams();
        r18.height = (AndroidUtilities.dp(82.0f) + (r5 * 4)) + (Math.max(0, 3) * r4);
        r18.width = (r5 * 3) + (Math.max(0, 2) * r3);
        r18.gravity = 17;
        this.numbersFrameLayout.setLayoutParams(r18);
        goto L53
    L28:
        r124 = 0;
        goto L29
    L24:
        r113 = r1;
        goto L25
    L20:
        r162 = 40.0f;
        r112 = r1;
        goto L21
    L11:
        if (SharedConfig.passcodeType != 1) goto L13;
        int r77 = 0;
    L14:
        r10.setVisibility(r77);
        goto L15
    L13:
        r77 = 8;
        goto L14
    L5:
        if (getContext().getResources().getConfiguration().orientation != 2) goto L7;
        r6 = true;
        goto L8
    }

    public void onPause() {
        AndroidUtilities.cancelRunOnUIThread(this.checkRunnable);
    }

    public void onResume() {
        checkRetryTextView();
        if (this.retryTextView.getVisibility() != 0) goto L5;
        return;
    L5:
        if (SharedConfig.passcodeType != 1) goto L10;
        EditTextBoldCursor r0 = this.passwordEditText;
        if (r0 == null) goto L9;
        r0.requestFocus();
        AndroidUtilities.showKeyboard(this.passwordEditText);
    L9:
        AndroidUtilities.runOnUIThread(new PasscodeView$$ExternalSyntheticLambda0(this), 200);
    L10:
        checkFingerprint();
    }

    public void onShow(boolean r8, boolean r9) {
        onShow(r8, r9, -1, -1, null, null);
    }

    public void onShow(boolean r14, boolean r15, int r16, int r17, Runnable r18, Runnable r19) {
        checkFingerprintButton();
        checkRetryTextView();
        Activity r0 = AndroidUtilities.findActivity(getContext());
        if (SharedConfig.passcodeType != 1) goto L10;
        if (r15 == true) goto L15;
        if (this.retryTextView.getVisibility() == 0) goto L15;
        EditTextBoldCursor r02 = this.passwordEditText;
        if (r02 == null) goto L15;
        r02.requestFocus();
        AndroidUtilities.showKeyboard(this.passwordEditText);
    L15:
        if (getVisibility() != 0) goto L17;
        return;
    L17:
        setTranslationY(0.0f);
        this.backgroundDrawable = null;
        this.backgroundFrameLayoutColor = 0;
        if ((Theme.getCachedWallpaper() instanceof MotionBackgroundDrawable) == false) goto L21;
        boolean r3 = !Theme.isCurrentThemeDark();
        this.backgroundDrawable = Theme.getCachedWallpaper();
        FrameLayout r6 = this.backgroundFrameLayout;
        this.backgroundFrameLayoutColor = -1090519040;
        r6.setBackgroundColor(-1090519040);
    L47:
        Drawable r5 = this.backgroundDrawable;
        if ((r5 instanceof MotionBackgroundDrawable) == false) goto L63;
        MotionBackgroundDrawable r52 = (MotionBackgroundDrawable) r5;
        int[] r62 = r52.getColors();
        if (r3 == false) goto L56;
        int[] r32 = new int[r62.length];
        int r7 = 0;
    L53:
        if (r7 >= r62.length) goto L55;
        r32[r7] = Theme.adaptHSV(r62[r7], 0.14f, 0.0f);
        r7 = r7 + 1;
        goto L53
    L55:
        r62 = r32;
    L56:
        this.backgroundDrawable = new MotionBackgroundDrawable(r62[0], r62[1], r62[2], r62[3], false);
        if (r52.hasPattern() == true) goto L59;
    L61:
        FrameLayout r33 = this.backgroundFrameLayout;
        this.backgroundFrameLayoutColor = 570425344;
        r33.setBackgroundColor(570425344);
    L62:
        ((MotionBackgroundDrawable) this.backgroundDrawable).setParentView(this.backgroundFrameLayout);
        goto L63
    L59:
        if (r52.getIntensity() >= 0) goto L61;
        FrameLayout r34 = this.backgroundFrameLayout;
        this.backgroundFrameLayoutColor = 2130706432;
        r34.setBackgroundColor(2130706432);
    L63:
        this.passcodeTextView.setText(LocaleController.getString(R.string.AppLocked));
        int r35 = SharedConfig.passcodeType;
        if (r35 == 0) goto L66;
        if (r35 != 1) goto L71;
        this.passwordEditText.setFilters(new InputFilter[0]);
        this.passwordEditText.setInputType(129);
        this.numbersFrameLayout.setVisibility(8);
        this.passwordEditText.setFocusable(true);
        this.passwordEditText.setFocusableInTouchMode(true);
        this.passwordEditText.setVisibility(0);
        this.passwordEditText2.setVisibility(8);
        this.checkImage.setVisibility(0);
        this.fingerprintImage.setVisibility(this.fingerprintView.getVisibility());
    L71:
        setVisibility(0);
        this.passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.passwordEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        AnimatingTextView.m11656$$Nest$meraseAllCharacters(this.passwordEditText2, false);
        if (r15 == false) goto L74;
        setAlpha(0.0f);
        getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass9(this, r16, r17, r18));
        requestLayout();
    L77:
        setOnTouchListener(new PasscodeView$$ExternalSyntheticLambda8());
        return;
    L74:
        setAlpha(1.0f);
        this.shownT = 1.0f;
        onAnimationUpdate(1.0f);
        this.imageView.setScaleX(1.0f);
        this.imageView.setScaleY(1.0f);
        this.imageView.stopAnimation();
        this.imageView.getAnimatedDrawable().setCurrentFrame(38, false);
        if (r18 == null) goto L77;
        r18.run();
        goto L77
    L66:
        if (this.retryTextView.getVisibility() == 0) goto L68;
        this.numbersFrameLayout.setVisibility(0);
    L68:
        this.passwordEditText.setVisibility(8);
        this.passwordEditText2.setVisibility(0);
        this.checkImage.setVisibility(8);
        this.fingerprintImage.setVisibility(8);
        goto L71
    L21:
        if (Theme.isCustomTheme() == false) goto L34;
        if ("CJz3BZ6YGEYBAAAABboWp6SAv04".equals(Theme.getSelectedBackgroundSlug()) == true) goto L34;
        if ("qeZWES8rGVIEAAAARfWlK1lnfiI".equals(Theme.getSelectedBackgroundSlug()) == true) goto L34;
        BackgroundGradientDrawable r36 = Theme.getCurrentGradientWallpaper();
        this.backgroundDrawable = r36;
        if (r36 != null) goto L29;
        this.backgroundDrawable = Theme.getCachedWallpaper();
    L29:
        boolean r37 = this.backgroundDrawable instanceof BackgroundGradientDrawable;
        FrameLayout r63 = this.backgroundFrameLayout;
        if (r37 == false) goto L32;
        this.backgroundFrameLayoutColor = 570425344;
        r63.setBackgroundColor(570425344);
    L46:
        r3 = false;
        goto L47
    L32:
        this.backgroundFrameLayoutColor = -1090519040;
        r63.setBackgroundColor(-1090519040);
    L34:
        if ("d".equals(Theme.getSelectedBackgroundSlug()) == false) goto L36;
    L45:
        FrameLayout r38 = this.backgroundFrameLayout;
        this.backgroundFrameLayoutColor = -11436898;
        r38.setBackgroundColor(-11436898);
        goto L46
    L36:
        if (Theme.isPatternWallpaper() == true) goto L45;
        Drawable r39 = Theme.getCachedWallpaper();
        this.backgroundDrawable = r39;
        if ((r39 instanceof BackgroundGradientDrawable) == false) goto L41;
        FrameLayout r310 = this.backgroundFrameLayout;
        this.backgroundFrameLayoutColor = 570425344;
        r310.setBackgroundColor(570425344);
        goto L46
    L41:
        FrameLayout r72 = this.backgroundFrameLayout;
        if (r39 == null) goto L44;
        this.backgroundFrameLayoutColor = -1090519040;
        r72.setBackgroundColor(-1090519040);
        goto L46
    L44:
        this.backgroundFrameLayoutColor = -11436898;
        r72.setBackgroundColor(-11436898);
        goto L46
    L10:
        if (r0 == null) goto L15;
        View r1 = r0.getCurrentFocus();
        if (r1 == null) goto L15;
        r1.clearFocus();
        AndroidUtilities.hideKeyboard(r0.getCurrentFocus());
        goto L15
    }

    public void setDelegate(PasscodeViewDelegate r1) {
        this.delegate = r1;
    }
}
