package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class ProximitySheet extends FrameLayout {
    private int backgroundPaddingLeft;
    private Paint backgroundPaint;
    private TextView buttonTextView;
    private ViewGroup containerView;
    private AnimatorSet currentAnimation;
    private AnimatorSet currentSheetAnimation;
    private int currentSheetAnimationType;
    private TLRPC.User currentUser;
    private LinearLayout customView;
    private boolean dismissed;
    private TextView infoTextView;
    private NumberPicker kmPicker;
    private NumberPicker mPicker;
    private boolean maybeStartTracking;
    private Runnable onDismissCallback;
    private onRadiusPickerChange onRadiusChange;
    private Interpolator openInterpolator;
    private boolean radiusSet;
    private Rect rect;
    private boolean startedTracking;
    private int startedTrackingPointerId;
    private int startedTrackingX;
    private int startedTrackingY;
    private int totalWidth;
    private int touchSlop;
    private boolean useFastDismiss;
    private boolean useHardwareLayer;
    private boolean useImperialSystem;
    private VelocityTracker velocityTracker;

    /* renamed from: org.telegram.ui.Components.ProximitySheet$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ ProximitySheet this$0;

        public AnonymousClass1(ProximitySheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.ProximitySheet$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ ProximitySheet this$0;

        public AnonymousClass2(ProximitySheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.ignoreLayout = false;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            this.ignoreLayout = true;
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            int r02 = 3;
        L6:
            ProximitySheet.m12256$$Nest$fgetkmPicker(this.this$0).setItemCount(r02);
            ProximitySheet.m12257$$Nest$fgetmPicker(this.this$0).setItemCount(r02);
            ViewGroup.LayoutParams r1 = ProximitySheet.m12256$$Nest$fgetkmPicker(this.this$0).getLayoutParams();
            r1.height = AndroidUtilities.dp(54.0f) * r02;
            ViewGroup.LayoutParams r12 = ProximitySheet.m12257$$Nest$fgetmPicker(this.this$0).getLayoutParams();
            r12.height = AndroidUtilities.dp(54.0f) * r02;
            this.ignoreLayout = false;
            ProximitySheet.m12263$$Nest$fputtotalWidth(this.this$0, View.MeasureSpec.getSize(r5));
            if (ProximitySheet.m12258$$Nest$fgettotalWidth(this.this$0) == 0) goto L9;
            this.this$0.updateText(false, false);
        L9:
            super.onMeasure(r5, r6);
            return;
        L5:
            r02 = 5;
            goto L6
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.ProximitySheet$3, reason: invalid class name */
    public class AnonymousClass3 extends TextView {
        final /* synthetic */ ProximitySheet this$0;

        public AnonymousClass3(ProximitySheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.ProximitySheet$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ ProximitySheet this$0;

        public AnonymousClass4(ProximitySheet r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ProximitySheet.m12254$$Nest$fgetcurrentAnimation(this.this$0) != null) goto L5;
        L7:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
            return;
        L5:
            if (ProximitySheet.m12254$$Nest$fgetcurrentAnimation(this.this$0).equals(r2) == false) goto L7;
            ProximitySheet.m12260$$Nest$fputcurrentAnimation(this.this$0, null);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.ProximitySheet$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ ProximitySheet this$0;

        public AnonymousClass5(ProximitySheet r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (ProximitySheet.m12255$$Nest$fgetcurrentSheetAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (ProximitySheet.m12255$$Nest$fgetcurrentSheetAnimation(this.this$0).equals(r2) == false) goto L9;
            ProximitySheet.m12261$$Nest$fputcurrentSheetAnimation(this.this$0, null);
            ProximitySheet.m12262$$Nest$fputcurrentSheetAnimationType(this.this$0, 0);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (ProximitySheet.m12255$$Nest$fgetcurrentSheetAnimation(this.this$0) != null) goto L5;
        L9:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
            return;
        L5:
            if (ProximitySheet.m12255$$Nest$fgetcurrentSheetAnimation(this.this$0).equals(r3) == false) goto L9;
            ProximitySheet.m12261$$Nest$fputcurrentSheetAnimation(this.this$0, null);
            ProximitySheet.m12262$$Nest$fputcurrentSheetAnimationType(this.this$0, 0);
            if (ProximitySheet.m12259$$Nest$fgetuseHardwareLayer(this.this$0) == false) goto L9;
            this.this$0.setLayerType(0, null);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.ProximitySheet$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ ProximitySheet this$0;

        /* renamed from: $r8$lambda$igI7MtwRUWxg9YwumAHvrqd-FiU, reason: not valid java name */
        public static /* synthetic */ void m12265$r8$lambda$igI7MtwRUWxg9YwumAHvrqdFiU(AnonymousClass6 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass6(ProximitySheet r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            ProximitySheet.m12264$$Nest$mdismissInternal(this.this$0);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (ProximitySheet.m12255$$Nest$fgetcurrentSheetAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (ProximitySheet.m12255$$Nest$fgetcurrentSheetAnimation(this.this$0).equals(r2) == false) goto L9;
            ProximitySheet.m12261$$Nest$fputcurrentSheetAnimation(this.this$0, null);
            ProximitySheet.m12262$$Nest$fputcurrentSheetAnimationType(this.this$0, 0);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ProximitySheet.m12255$$Nest$fgetcurrentSheetAnimation(this.this$0) != null) goto L5;
        L7:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
            return;
        L5:
            if (ProximitySheet.m12255$$Nest$fgetcurrentSheetAnimation(this.this$0).equals(r2) == false) goto L7;
            ProximitySheet.m12261$$Nest$fputcurrentSheetAnimation(this.this$0, null);
            ProximitySheet.m12262$$Nest$fputcurrentSheetAnimationType(this.this$0, 0);
            AndroidUtilities.runOnUIThread(new ProximitySheet$6$$ExternalSyntheticLambda0(this));
            goto L7
        }
    }

    public interface onRadiusPickerChange {
        boolean run(boolean r1, int r2);
    }

    public static /* synthetic */ String $r8$lambda$0scmXSvdjaVtGywq05uMPo9WBxY(ProximitySheet r0, int r1) {
        return r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$61puM2f0gP4PJMfUVoNuNNpDmrg(ProximitySheet r0, onRadiusPickerChange r1, View r2) {
        r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$PRHImrHr_Zeyhb6grvjVFDIYOKc(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ String $r8$lambda$QVsOlRc_uX1ApaygQYng54FMXXE(ProximitySheet r0, int r1) {
        return r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$zFWSrf682uBdJVFhamcF2BOD4Jg(ProximitySheet r0, NumberPicker r1, int r2, int r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetcurrentAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m12254$$Nest$fgetcurrentAnimation(ProximitySheet r0) {
        return r0.currentAnimation;
    }

    /* renamed from: -$$Nest$fgetcurrentSheetAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m12255$$Nest$fgetcurrentSheetAnimation(ProximitySheet r0) {
        return r0.currentSheetAnimation;
    }

    /* renamed from: -$$Nest$fgetkmPicker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NumberPicker m12256$$Nest$fgetkmPicker(ProximitySheet r0) {
        return r0.kmPicker;
    }

    /* renamed from: -$$Nest$fgetmPicker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NumberPicker m12257$$Nest$fgetmPicker(ProximitySheet r0) {
        return r0.mPicker;
    }

    /* renamed from: -$$Nest$fgettotalWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12258$$Nest$fgettotalWidth(ProximitySheet r0) {
        return r0.totalWidth;
    }

    /* renamed from: -$$Nest$fgetuseHardwareLayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12259$$Nest$fgetuseHardwareLayer(ProximitySheet r0) {
        return r0.useHardwareLayer;
    }

    /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12260$$Nest$fputcurrentAnimation(ProximitySheet r0, AnimatorSet r1) {
        r0.currentAnimation = r1;
    }

    /* renamed from: -$$Nest$fputcurrentSheetAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12261$$Nest$fputcurrentSheetAnimation(ProximitySheet r0, AnimatorSet r1) {
        r0.currentSheetAnimation = r1;
    }

    /* renamed from: -$$Nest$fputcurrentSheetAnimationType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12262$$Nest$fputcurrentSheetAnimationType(ProximitySheet r0, int r1) {
        r0.currentSheetAnimationType = r1;
    }

    /* renamed from: -$$Nest$fputtotalWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12263$$Nest$fputtotalWidth(ProximitySheet r0, int r1) {
        r0.totalWidth = r1;
    }

    /* renamed from: -$$Nest$mdismissInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12264$$Nest$mdismissInternal(ProximitySheet r0) {
        r0.dismissInternal();
    }

    public ProximitySheet(Context r19, TLRPC.User r20, onRadiusPickerChange r21, onRadiusPickerChange r22, Runnable r23) {
        super(r19);
        this.velocityTracker = null;
        this.startedTrackingPointerId = -1;
        this.maybeStartTracking = false;
        this.startedTracking = false;
        this.currentAnimation = null;
        this.rect = new Rect();
        this.backgroundPaint = new Paint();
        this.useHardwareLayer = true;
        this.openInterpolator = CubicBezierInterpolator.EASE_OUT_QUINT;
        setWillNotDraw(false);
        this.onDismissCallback = r23;
        this.touchSlop = ViewConfiguration.get(r19).getScaledTouchSlop();
        Rect r5 = new Rect();
        Drawable r6 = r19.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        r6.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground), PorterDuff.Mode.MULTIPLY));
        r6.getPadding(r5);
        this.backgroundPaddingLeft = r5.left;
        AnonymousClass1 r7 = new AnonymousClass1(this, getContext());
        this.containerView = r7;
        r7.setBackgroundDrawable(r6);
        this.containerView.setPadding(this.backgroundPaddingLeft, (AndroidUtilities.dp(8.0f) + r5.top) - 1, this.backgroundPaddingLeft, 0);
        this.containerView.setVisibility(4);
        addView(this.containerView, 0, LayoutHelper.createFrame(-1, -2, 80));
        this.useImperialSystem = LocaleController.getUseImperialSystemType();
        this.currentUser = r20;
        this.onRadiusChange = r21;
        NumberPicker r52 = new NumberPicker(r19);
        this.kmPicker = r52;
        r52.setTextOffset(AndroidUtilities.dp(10.0f));
        this.kmPicker.setItemCount(5);
        NumberPicker r53 = new NumberPicker(r19);
        this.mPicker = r53;
        r53.setItemCount(5);
        this.mPicker.setTextOffset(-AndroidUtilities.dp(10.0f));
        AnonymousClass2 r54 = new AnonymousClass2(this, r19);
        this.customView = r54;
        r54.setOrientation(1);
        FrameLayout r55 = new FrameLayout(r19);
        this.customView.addView(r55, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r62 = new TextView(r19);
        r62.setText(LocaleController.getString(R.string.LocationNotifiation));
        r62.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r62.setTextSize(1, 20.0f);
        r62.setTypeface(AndroidUtilities.bold());
        r55.addView(r62, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r62.setOnTouchListener(new ProximitySheet$$ExternalSyntheticLambda0());
        LinearLayout r56 = new LinearLayout(r19);
        r56.setOrientation(0);
        r56.setWeightSum(1.0f);
        this.customView.addView(r56, LayoutHelper.createLinear(-1, -2));
        System.currentTimeMillis();
        FrameLayout r63 = new FrameLayout(r19);
        this.infoTextView = new TextView(r19);
        this.buttonTextView = new AnonymousClass3(this, r19);
        r56.addView(this.kmPicker, LayoutHelper.createLinear(0, 270, 0.5f));
        this.kmPicker.setFormatter(new ProximitySheet$$ExternalSyntheticLambda1(this));
        this.kmPicker.setMinValue(0);
        this.kmPicker.setMaxValue(10);
        this.kmPicker.setWrapSelectorWheel(false);
        this.kmPicker.setTextOffset(AndroidUtilities.dp(20.0f));
        ProximitySheet$$ExternalSyntheticLambda2 r1 = new ProximitySheet$$ExternalSyntheticLambda2(this);
        this.kmPicker.setOnValueChangedListener(r1);
        this.mPicker.setMinValue(0);
        this.mPicker.setMaxValue(10);
        this.mPicker.setWrapSelectorWheel(false);
        this.mPicker.setTextOffset(-AndroidUtilities.dp(20.0f));
        r56.addView(this.mPicker, LayoutHelper.createLinear(0, 270, 0.5f));
        this.mPicker.setFormatter(new ProximitySheet$$ExternalSyntheticLambda3(this));
        this.mPicker.setOnValueChangedListener(r1);
        this.kmPicker.setValue(0);
        this.mPicker.setValue(6);
        this.customView.addView(r63, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 16));
        this.buttonTextView.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        this.buttonTextView.setGravity(17);
        this.buttonTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.buttonTextView.setTextSize(1, 14.0f);
        this.buttonTextView.setMaxLines(2);
        this.buttonTextView.setTypeface(AndroidUtilities.bold());
        this.buttonTextView.setBackgroundDrawable(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{4.0f}));
        r63.addView(this.buttonTextView, LayoutHelper.createFrame(-1, 48.0f));
        this.buttonTextView.setOnClickListener(new ProximitySheet$$ExternalSyntheticLambda4(this, r22));
        this.infoTextView.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        this.infoTextView.setGravity(17);
        this.infoTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray2));
        this.infoTextView.setTextSize(1, 14.0f);
        this.infoTextView.setAlpha(0.0f);
        this.infoTextView.setScaleX(0.5f);
        this.infoTextView.setScaleY(0.5f);
        r63.addView(this.infoTextView, LayoutHelper.createFrame(-1, 48.0f));
        this.containerView.addView(this.customView, LayoutHelper.createFrame(-1, -2, 51));
    }

    private void cancelCurrentAnimation() {
        AnimatorSet r0 = this.currentAnimation;
        if (r0 == null) goto L6;
        r0.cancel();
        this.currentAnimation = null;
        return;
    }

    private void cancelSheetAnimation() {
        AnimatorSet r0 = this.currentSheetAnimation;
        if (r0 == null) goto L6;
        r0.cancel();
        this.currentSheetAnimation = null;
        this.currentSheetAnimationType = 0;
        return;
    }

    private void checkDismiss(float r8, float r9) {
        if (this.containerView.getTranslationY() >= AndroidUtilities.getPixelsInCM(0.8f, false)) goto L9;
        if (r9 >= 3500.0f) goto L7;
    L12:
        AnimatorSet r82 = new AnimatorSet();
        this.currentAnimation = r82;
        r82.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.containerView, View.TRANSLATION_Y, new float[]{0.0f})});
        this.currentAnimation.setDuration((int) ((Math.max(0.0f, r0) / AndroidUtilities.getPixelsInCM(0.8f, false)) * 150.0f));
        this.currentAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.currentAnimation.addListener(new AnonymousClass4(this));
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        this.currentAnimation.start();
        return;
    L7:
        if (Math.abs(r9) < Math.abs(r8)) goto L12;
    L9:
        if (r9 < 0.0f) goto L11;
    L14:
        this.useFastDismiss = true;
        dismiss();
        return;
    L11:
        if (Math.abs(r9) < 3500.0f) goto L14;
        goto L12
    }

    private void dismissInternal() {
        if ((getParent() instanceof ViewGroup) == false) goto L5;
        ((ViewGroup) getParent()).removeView(this);
    L5:
        this.onDismissCallback.run();
    }

    private /* synthetic */ String lambda$new$1(int r2) {
        if (this.useImperialSystem == false) goto L7;
        return LocaleController.formatString("MilesShort", R.string.MilesShort, new Object[]{Integer.valueOf(r2)});
    L7:
        return LocaleController.formatString("KMetersShort", R.string.KMetersShort, new Object[]{Integer.valueOf(r2)});
    }

    private /* synthetic */ void lambda$new$2(NumberPicker r1, int r2, int r3) {
        performHapticFeedback(3, 2);     // Catch: Exception -> L6
    L4:
        updateText(true, true);
    }

    private /* synthetic */ String lambda$new$3(int r2) {
        if (this.useImperialSystem == false) goto L12;
        if (r2 == 1) goto L6;
        if (r2 <= 1) goto L10;
        r2 = r2 - 1;
    L10:
        return String.format(Locale.US, ".%d", new Object[]{Integer.valueOf(r2)});
    L6:
        return LocaleController.formatString("FootsShort", R.string.FootsShort, new Object[]{250});
    L12:
        if (r2 == 1) goto L14;
        if (r2 <= 1) goto L18;
        r2 = r2 - 1;
    L18:
        return LocaleController.formatString("MetersShort", R.string.MetersShort, new Object[]{Integer.valueOf(r2 * 100)});
    L14:
        return LocaleController.formatString("MetersShort", R.string.MetersShort, new Object[]{50});
    }

    private /* synthetic */ void lambda$new$4(onRadiusPickerChange r2, View r3) {
        if (this.buttonTextView.getTag() == null) goto L6;
        return;
    L6:
        if (r2.run(true, (int) Math.max(1.0f, getValue())) == false) goto L9;
        dismiss();
        return;
    }

    private void startOpenAnimation() {
        if (this.dismissed == false) goto L5;
        return;
    L5:
        this.containerView.setVisibility(0);
        if (this.useHardwareLayer == false) goto L8;
        setLayerType(2, null);
    L8:
        this.containerView.setTranslationY(r0.getMeasuredHeight());
        this.currentSheetAnimationType = 1;
        AnimatorSet r2 = new AnimatorSet();
        this.currentSheetAnimation = r2;
        r2.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.containerView, View.TRANSLATION_Y, new float[]{0.0f})});
        this.currentSheetAnimation.setDuration(400);
        this.currentSheetAnimation.setStartDelay(20);
        this.currentSheetAnimation.setInterpolator(this.openInterpolator);
        this.currentSheetAnimation.addListener(new AnonymousClass5(this));
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        this.currentSheetAnimation.start();
    }

    public void dismiss() {
        if (this.dismissed == false) goto L5;
        return;
    L5:
        this.dismissed = true;
        cancelSheetAnimation();
        this.currentSheetAnimationType = 2;
        AnimatorSet r1 = new AnimatorSet();
        this.currentSheetAnimation = r1;
        r1.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.containerView, View.TRANSLATION_Y, new float[]{r2.getMeasuredHeight() + AndroidUtilities.dp(10.0f)})});
        if (this.useFastDismiss == false) goto L8;
        float r0 = this.containerView.getMeasuredHeight();
        this.currentSheetAnimation.setDuration(Math.max(60, (int) (((r0 - this.containerView.getTranslationY()) * 250.0f) / r0)));
        this.useFastDismiss = false;
    L9:
        this.currentSheetAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.currentSheetAnimation.addListener(new AnonymousClass6(this));
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        this.currentSheetAnimation.start();
        return;
    L8:
        this.currentSheetAnimation.setDuration(250);
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (this.dismissed == false) goto L7;
        return true;
    L7:
        return super.dispatchTouchEvent(r2);
    }

    public View getCustomView() {
        return this.customView;
    }

    public boolean getRadiusSet() {
        return this.radiusSet;
    }

    public float getValue() {
        float r0 = this.kmPicker.getValue() * 1000;
        int r1 = this.mPicker.getValue();
        boolean r3 = this.useImperialSystem;
        if (r3 == false) goto L10;
        if (r1 != 1) goto L7;
        float r12 = 47.349f;
    L6:
        float r02 = r0 + r12;
        if (r3 == true) goto L16;
        return r02;
    L16:
        return r02 * 1.60934f;
    L7:
        if (r1 <= 1) goto L9;
    L8:
        r1 = r1 - 1;
    L9:
        r12 = r1 * 100;
        goto L6
    L10:
        if (r1 != 1) goto L12;
        r12 = 50.0f;
        goto L6
    L12:
        if (r1 <= 1) goto L9;
        goto L9
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (this.dismissed == false) goto L5;
    L9:
        return true;
    L5:
        if (processTouchEvent(r3, true) == true) goto L9;
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
        int r122 = r12 - r10;
        int r82 = r122 - this.containerView.getMeasuredHeight();
        int r92 = r11 - r9;
        int r102 = (r92 - this.containerView.getMeasuredWidth()) / 2;
        ViewGroup r0 = this.containerView;
        r0.layout(r102, r82, r0.getMeasuredWidth() + r102, this.containerView.getMeasuredHeight() + r82);
        int r83 = getChildCount();
        int r103 = 0;
    L3:
        if (r103 >= r83) goto L30;
        View r02 = getChildAt(r103);
        if (r02.getVisibility() == 8) goto L29;
        if (r02 == this.containerView) goto L29;
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) r02.getLayoutParams();
        int r2 = r02.getMeasuredWidth();
        int r3 = r02.getMeasuredHeight();
        int r4 = r1.gravity;
        if (r4 != (-1)) goto L12;
        r4 = 51;
    L12:
        int r5 = r4 & 112;
        int r42 = r4 & 7;
        if (r42 != 1) goto L15;
        int r43 = ((r92 - r2) / 2) + r1.leftMargin;
        int r6 = r1.rightMargin;
    L18:
        int r44 = r43 - r6;
    L21:
        if (r5 != 16) goto L23;
        int r52 = ((r122 - r3) / 2) + r1.topMargin;
        int r13 = r1.bottomMargin;
    L26:
        int r14 = r52 - r13;
    L28:
        r02.layout(r44, r14, r2 + r44, r3 + r14);
        goto L29
    L23:
        if (r5 == 80) goto L25;
        r14 = r1.topMargin;
        goto L28
    L25:
        r52 = r122 - r3;
        r13 = r1.bottomMargin;
        goto L26
    L15:
        if (r42 == 5) goto L17;
        r44 = r1.leftMargin;
        goto L21
    L17:
        r43 = r11 - r2;
        r6 = r1.rightMargin;
    L29:
        r103 = r103 + 1;
        goto L3
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r10, int r11) {
        int r102 = View.MeasureSpec.getSize(r10);
        int r112 = View.MeasureSpec.getSize(r11);
        getRootView();
        getWindowVisibleDisplayFrame(this.rect);
        setMeasuredDimension(r102, r112);
        this.containerView.measure(View.MeasureSpec.makeMeasureSpec((this.backgroundPaddingLeft * 2) + r102, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r112, Integer.MIN_VALUE));
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        View r4 = this.getChildAt(r1);
        if (r4.getVisibility() != 8) goto L7;
    L8:
        ProximitySheet r3 = this;
    L10:
        r1 = r1 + 1;
        ProximitySheet this = r3;
        goto L3
    L7:
        if (r4 == this.containerView) goto L8;
        r3 = this;
        r3.measureChildWithMargins(r4, View.MeasureSpec.makeMeasureSpec(r102, TLObject.FLAG_30), 0, View.MeasureSpec.makeMeasureSpec(r112, TLObject.FLAG_30), 0);
        goto L10
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        if (this.dismissed == false) goto L5;
        return true;
    L5:
        if (processTouchEvent(r2, false) == true) goto L10;
        return false;
    L10:
        return true;
    }

    public boolean processTouchEvent(MotionEvent r7, boolean r8) {
        if (this.dismissed == false) goto L6;
        return false;
    L6:
        if (r7 != null) goto L8;
    L29:
        float r3 = 0.0f;
        if (r7 != null) goto L32;
    L56:
        if (r7 == null) goto L66;
        if (r7.getPointerId(0) == this.startedTrackingPointerId) goto L60;
    L79:
        if (r8 == true) goto L83;
        if (this.maybeStartTracking == false) goto L83;
    L84:
        return true;
    L83:
        if (this.startedTracking == true) goto L84;
        return false;
    L60:
        if (r7.getAction() == 3) goto L66;
        if (r7.getAction() == 1) goto L66;
        if (r7.getAction() != 6) goto L79;
    L66:
        if (this.velocityTracker != null) goto L68;
        this.velocityTracker = VelocityTracker.obtain();
    L68:
        this.velocityTracker.computeCurrentVelocity(1000);
        float r72 = this.containerView.getTranslationY();
        if (this.startedTracking == false) goto L71;
    L74:
        checkDismiss(this.velocityTracker.getXVelocity(), this.velocityTracker.getYVelocity());
        this.startedTracking = false;
    L75:
        VelocityTracker r73 = this.velocityTracker;
        if (r73 == null) goto L78;
        r73.recycle();
        this.velocityTracker = null;
    L78:
        this.startedTrackingPointerId = -1;
        goto L79
    L71:
        if (r72 != 0.0f) goto L74;
        this.maybeStartTracking = false;
        this.startedTracking = false;
        goto L75
    L32:
        if (r7.getAction() != 2) goto L56;
        if (r7.getPointerId(0) != this.startedTrackingPointerId) goto L56;
        if (this.velocityTracker != null) goto L38;
        this.velocityTracker = VelocityTracker.obtain();
    L38:
        float r0 = Math.abs((int) (r7.getX() - this.startedTrackingX));
        float r4 = ((int) r7.getY()) - this.startedTrackingY;
        this.velocityTracker.addMovement(r7);
        if (this.maybeStartTracking == false) goto L50;
        if (this.startedTracking == true) goto L50;
        if (r4 <= 0.0f) goto L50;
        if ((r4 / 3.0f) <= Math.abs(r0)) goto L50;
        if (Math.abs(r4) < this.touchSlop) goto L50;
        this.startedTrackingY = (int) r7.getY();
        this.maybeStartTracking = false;
        this.startedTracking = true;
        requestDisallowInterceptTouchEvent(true);
    L50:
        if (this.startedTracking == false) goto L79;
        float r02 = this.containerView.getTranslationY() + r4;
        if (r02 < 0.0f) goto L55;
        r3 = r02;
    L55:
        this.containerView.setTranslationY(r3);
        this.startedTrackingY = (int) r7.getY();
        goto L79
    L8:
        if (r7.getAction() == 0) goto L12;
        if (r7.getAction() != 2) goto L29;
    L12:
        if (this.startedTracking == true) goto L29;
        if (this.maybeStartTracking == true) goto L29;
        if (r7.getPointerCount() != 1) goto L29;
        this.startedTrackingX = (int) r7.getX();
        int r03 = (int) r7.getY();
        this.startedTrackingY = r03;
        if (r03 >= this.containerView.getTop()) goto L20;
    L27:
        requestDisallowInterceptTouchEvent(true);
        dismiss();
        return true;
    L20:
        if (this.startedTrackingX < this.containerView.getLeft()) goto L27;
        if (this.startedTrackingX > this.containerView.getRight()) goto L27;
        this.startedTrackingPointerId = r7.getPointerId(0);
        this.maybeStartTracking = true;
        cancelCurrentAnimation();
        VelocityTracker r74 = this.velocityTracker;
        if (r74 == null) goto L79;
        r74.clear();
        goto L79
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean r2) {
        if (this.maybeStartTracking == true) goto L5;
    L7:
        super.requestDisallowInterceptTouchEvent(r2);
        return;
    L5:
        if (this.startedTracking == true) goto L7;
        onTouchEvent(null);
        goto L7
    }

    public void setRadiusSet() {
        this.radiusSet = true;
    }

    public void show() {
        this.dismissed = false;
        cancelSheetAnimation();
        this.containerView.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x + (this.backgroundPaddingLeft * 2), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.y, Integer.MIN_VALUE));
        startOpenAnimation();
        updateText(true, false);
    }

    public void updateText(boolean r9, boolean r10) {
        float r102 = getValue();
        String r0 = LocaleController.formatDistance(r102, 2, Boolean.valueOf(this.useImperialSystem));
        if (this.onRadiusChange.run(r9, (int) r102) == true) goto L12;
        if (this.currentUser == null) goto L12;
        this.infoTextView.setText(LocaleController.formatString("LocationNotifiationCloser", R.string.LocationNotifiationCloser, new Object[]{r0}));
        if (this.buttonTextView.getTag() != null) goto L19;
        this.buttonTextView.setTag(1);
        this.buttonTextView.animate().setDuration(180).alpha(0.0f).scaleX(0.5f).scaleY(0.5f).start();
        this.infoTextView.animate().setDuration(180).alpha(1.0f).scaleX(1.0f).scaleY(1.0f).start();
        return;
    L19:
        return;
    L12:
        if (this.currentUser != null) goto L14;
        this.buttonTextView.setText(LocaleController.formatString("LocationNotifiationButtonGroup", R.string.LocationNotifiationButtonGroup, new Object[]{r0}));
    L16:
        if (this.buttonTextView.getTag() == null) goto L20;
        this.buttonTextView.setTag(null);
        this.buttonTextView.animate().setDuration(180).alpha(1.0f).scaleX(1.0f).scaleY(1.0f).start();
        this.infoTextView.animate().setDuration(180).alpha(0.0f).scaleX(0.5f).scaleY(0.5f).start();
        return;
    L20:
        return;
    L14:
        String r92 = LocaleController.getString(R.string.LocationNotifiationButtonUser);
        int r93 = (int) (((this.totalWidth - AndroidUtilities.dp(94.0f)) * 1.5f) - ((int) Math.ceil(this.buttonTextView.getPaint().measureText(r92))));
        this.buttonTextView.setText(LocaleController.formatString("LocationNotifiationButtonUser", R.string.LocationNotifiationButtonUser, new Object[]{TextUtils.ellipsize(UserObject.getFirstName(this.currentUser), this.buttonTextView.getPaint(), Math.max(AndroidUtilities.dp(10.0f), r93), TextUtils.TruncateAt.END), r0}));
        goto L16
    }
}
