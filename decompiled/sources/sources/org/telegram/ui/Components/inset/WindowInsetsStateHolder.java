package org.telegram.ui.Components.inset;

import android.view.View;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import me.vkryl.android.animator.FactorAnimator;
import me.vkryl.android.animator.VariableFloat;
import me.vkryl.android.animator.VariableRect;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.Components.inset.KeyboardState;
import org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider;

/* loaded from: classes3.dex */
public class WindowInsetsStateHolder implements WindowInsetsProvider, WindowInsetsInAppController, WindowAnimatedInsetsProvider.Listener {
    private int activeAnimations;
    private int animatedImeInset;
    private WindowAnimatedInsetsProvider animatedInsetsProvider;
    private View animatedInsetsProviderTarget;
    private final Runnable closeInAppKeyboard;
    private int inAppKeyboardHeight;
    private int inAppKeyboardState;
    private int inAppKeyboardViewHeight;
    private final FactorAnimator insetsAnimator;
    private final VariableRect insetsImeRect;
    private final VariableRect insetsMaxRect;
    private final KeyboardState keyboardState;
    private final VariableFloat keyboardVisibility;
    private WindowInsetsCompat lastInsets;
    private boolean locked;
    private final AnimationNotificationsLocker locker;
    private final Runnable onUpdateListener;
    public boolean settleViaTreeDispatch;

    /* renamed from: org.telegram.ui.Components.inset.WindowInsetsStateHolder$1, reason: invalid class name */
    public class AnonymousClass1 implements FactorAnimator.Target {
        final /* synthetic */ WindowInsetsStateHolder this$0;
        final /* synthetic */ Runnable val$onUpdateListener;

        public AnonymousClass1(WindowInsetsStateHolder r1, Runnable r2) {
            this.this$0 = r1;
            this.val$onUpdateListener = r2;
        }

        @Override // me.vkryl.android.animator.FactorAnimator.Target
        public void onFactorChangeFinished(int r2, float r3, FactorAnimator r4) {
            boolean r42 = true;
            if (this.this$0.getAnimatedImeBottomInset() != 0.0f) goto L7;
            if (WindowInsetsStateHolder.m13717$$Nest$fgetinAppKeyboardState(this.this$0) != 2) goto L7;
        L8:
            WindowInsetsStateHolder.m13722$$Nest$fputinAppKeyboardState(this.this$0, 1);
            boolean r22 = true;
        L11:
            if (r3 == 1.0f) goto L13;
        L15:
            r42 = r22;
        L16:
            if (r42 == false) goto L18;
            this.val$onUpdateListener.run();
        L18:
            WindowInsetsStateHolder.m13724$$Nest$mcheckAnimationsLocker(this.this$0);
            return;
        L13:
            if (WindowInsetsStateHolder.m13718$$Nest$fgetinAppKeyboardViewHeight(this.this$0) == WindowInsetsStateHolder.m13716$$Nest$fgetinAppKeyboardHeight(this.this$0)) goto L15;
            WindowInsetsStateHolder r23 = this.this$0;
            WindowInsetsStateHolder.m13723$$Nest$fputinAppKeyboardViewHeight(r23, WindowInsetsStateHolder.m13716$$Nest$fgetinAppKeyboardHeight(r23));
        L7:
            if (WindowInsetsStateHolder.m13717$$Nest$fgetinAppKeyboardState(this.this$0) == 3) goto L8;
            r22 = false;
            goto L11
        }

        @Override // me.vkryl.android.animator.FactorAnimator.Target
        public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
            WindowInsetsStateHolder.m13720$$Nest$fgetinsetsMaxRect(this.this$0).applyAnimation(r2);
            WindowInsetsStateHolder.m13719$$Nest$fgetinsetsImeRect(this.this$0).applyAnimation(r2);
            WindowInsetsStateHolder.m13721$$Nest$fgetkeyboardVisibility(this.this$0).applyAnimation(r2);
            this.val$onUpdateListener.run();
        }
    }

    /* renamed from: $r8$lambda$BBI0r-nQ4SIQYrlifaR8OrvWhfE, reason: not valid java name */
    public static /* synthetic */ void m13714$r8$lambda$BBI0rnQ4SIQYrlifaR8OrvWhfE(WindowInsetsStateHolder r0, KeyboardState.State r1) {
        r0.onKeyboardStateChanged(r1);
    }

    /* renamed from: $r8$lambda$_4sXN7z0M-7VXplcBh972Ead80M, reason: not valid java name */
    public static /* synthetic */ void m13715$r8$lambda$_4sXN7z0M7VXplcBh972Ead80M(WindowInsetsStateHolder r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$tSyLquPKZlLA6wDUoKkmoiTlpu0(WindowInsetsStateHolder r0) {
        r0.lambda$onAnimatedInsetsFinished$1();
    }

    /* renamed from: -$$Nest$fgetinAppKeyboardHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13716$$Nest$fgetinAppKeyboardHeight(WindowInsetsStateHolder r0) {
        return r0.inAppKeyboardHeight;
    }

    /* renamed from: -$$Nest$fgetinAppKeyboardState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13717$$Nest$fgetinAppKeyboardState(WindowInsetsStateHolder r0) {
        return r0.inAppKeyboardState;
    }

    /* renamed from: -$$Nest$fgetinAppKeyboardViewHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13718$$Nest$fgetinAppKeyboardViewHeight(WindowInsetsStateHolder r0) {
        return r0.inAppKeyboardViewHeight;
    }

    /* renamed from: -$$Nest$fgetinsetsImeRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VariableRect m13719$$Nest$fgetinsetsImeRect(WindowInsetsStateHolder r0) {
        return r0.insetsImeRect;
    }

    /* renamed from: -$$Nest$fgetinsetsMaxRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VariableRect m13720$$Nest$fgetinsetsMaxRect(WindowInsetsStateHolder r0) {
        return r0.insetsMaxRect;
    }

    /* renamed from: -$$Nest$fgetkeyboardVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VariableFloat m13721$$Nest$fgetkeyboardVisibility(WindowInsetsStateHolder r0) {
        return r0.keyboardVisibility;
    }

    /* renamed from: -$$Nest$fputinAppKeyboardState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13722$$Nest$fputinAppKeyboardState(WindowInsetsStateHolder r0, int r1) {
        r0.inAppKeyboardState = r1;
    }

    /* renamed from: -$$Nest$fputinAppKeyboardViewHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13723$$Nest$fputinAppKeyboardViewHeight(WindowInsetsStateHolder r0, int r1) {
        r0.inAppKeyboardViewHeight = r1;
    }

    /* renamed from: -$$Nest$mcheckAnimationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13724$$Nest$mcheckAnimationsLocker(WindowInsetsStateHolder r0) {
        r0.checkAnimationsLocker();
    }

    public WindowInsetsStateHolder(Runnable r8) {
        this.keyboardVisibility = new VariableFloat(0.0f);
        this.insetsMaxRect = new VariableRect();
        this.insetsImeRect = new VariableRect();
        this.locker = new AnimationNotificationsLocker();
        this.keyboardState = new KeyboardState(new WindowInsetsStateHolder$$ExternalSyntheticLambda0(this));
        this.inAppKeyboardState = 1;
        this.closeInAppKeyboard = new WindowInsetsStateHolder$$ExternalSyntheticLambda1(this);
        this.onUpdateListener = r8;
        this.insetsAnimator = new FactorAnimator(0, new AnonymousClass1(this, r8), AdjustPanLayoutHelper.keyboardInterpolator, 250);
    }

    private void checkAnimationsLocker() {
        boolean r0 = this.insetsAnimator.isAnimating();
        if (this.locked == true) goto L7;
        if (r0 == false) goto L7;
        this.locked = true;
        this.locker.lock();
    L7:
        if (this.locked == false) goto L11;
        if (r0 == true) goto L12;
        this.locked = false;
        this.locker.unlock();
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        if (this.inAppKeyboardHeight == 0) goto L6;
        resetInAppKeyboardHeight(false);
        return;
    }

    private /* synthetic */ void lambda$onAnimatedInsetsFinished$1() {
        int r0 = this.activeAnimations - 1;
        this.activeAnimations = r0;
        if (r0 != 0) goto L10;
        boolean r02 = this.settleViaTreeDispatch;
        View r1 = this.animatedInsetsProviderTarget;
        if (r02 == false) goto L8;
        ViewCompat.requestApplyInsets(r1);
        return;
    L8:
        setInsets(WindowAnimatedInsetsProvider.calculateWindowInsets(r1), false);
        return;
    }

    private void onKeyboardStateChanged(KeyboardState.State r2) {
        if (r2 != KeyboardState.State.STATE_FULLY_VISIBLE) goto L9;
        int r22 = this.inAppKeyboardState;
        if (r22 != 2) goto L7;
    L8:
        this.inAppKeyboardState = 1;
        goto L9
    L7:
        if (r22 == 3) goto L8;
    L9:
        this.onUpdateListener.run();
    }

    private void setInsets(WindowInsetsCompat r11, boolean r12) {
        this.lastInsets = r11;
        int r0 = WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout();
        if (r11 == null) goto L5;
        Insets r02 = Insets.max(r11.getInsets(r0), r11.getInsetsIgnoringVisibility(r0));
    L6:
        if (r11 == null) goto L8;
        Insets r112 = r11.getInsets(WindowInsetsCompat.Type.ime());
    L9:
        KeyboardState.State r1 = this.keyboardState.getState();
        KeyboardState r2 = this.keyboardState;
        if (r112.bottom <= 0) goto L12;
        boolean r3 = true;
    L13:
        KeyboardState.State r22 = r2.setKeyboardVisibility(r3, !r12, false);
        int r32 = this.inAppKeyboardState;
        if (r32 != 2) goto L17;
        this.inAppKeyboardHeight = 0;
    L17:
        if (r32 == 3) goto L19;
    L21:
        Insets r113 = Insets.max(r112, Insets.of(0, 0, 0, this.inAppKeyboardHeight));
        Insets r03 = Insets.max(r02, r113);
        float r33 = 0.0f;
        if (r12 == false) goto L41;
        VariableFloat r122 = this.keyboardVisibility;
        if (r113.bottom <= 0) goto L26;
        float r6 = 1.0f;
    L28:
        if (r122.differs(r6) == false) goto L30;
    L36:
        this.insetsAnimator.cancel();
        this.keyboardVisibility.finishAnimation(false);
        this.insetsMaxRect.finishAnimation(false);
        this.insetsImeRect.finishAnimation(false);
        VariableFloat r123 = this.keyboardVisibility;
        if (r113.bottom <= 0) goto L39;
        float r13 = 1.0f;
    L40:
        r123.setTo(r13);
        this.insetsMaxRect.setTo(r03.left, r03.top, r03.right, r03.bottom);
        this.insetsImeRect.setTo(r113.left, r113.top, r113.right, r113.bottom);
        this.insetsAnimator.forceFactor(0.0f);
        this.insetsAnimator.animateTo(1.0f);
    L45:
        checkAnimationsLocker();
        return;
    L39:
        r13 = 0.0f;
        goto L40
    L30:
        if (this.insetsMaxRect.differs(r03.left, r03.top, r03.right, r03.bottom) == true) goto L36;
        if (this.insetsImeRect.differs(r113.left, r113.top, r113.right, r113.bottom) == true) goto L36;
        if (r1 == r22) goto L45;
        this.onUpdateListener.run();
        goto L45
    L26:
        r6 = 0.0f;
        goto L28
    L41:
        this.insetsAnimator.cancel();
        VariableFloat r124 = this.keyboardVisibility;
        if (r113.bottom <= 0) goto L44;
        r33 = 1.0f;
    L44:
        r124.set(r33);
        this.insetsMaxRect.set(r03.left, r03.top, r03.right, r03.bottom);
        this.insetsImeRect.set(r113.left, r113.top, r113.right, r113.bottom);
        this.onUpdateListener.run();
        goto L45
    L19:
        if (r112.bottom <= 0) goto L21;
        this.inAppKeyboardHeight = 0;
        goto L21
    L12:
        r3 = false;
        goto L13
    L8:
        r112 = Insets.NONE;
        goto L9
    L5:
        r02 = Insets.NONE;
        goto L6
    }

    @Override // org.telegram.ui.Components.inset.WindowInsetsProvider
    public float getAnimatedImeBottomInset() {
        if (this.animatedInsetsProvider == null) goto L9;
        if (this.activeAnimations <= 0) goto L9;
        return Math.max(this.animatedImeInset, this.insetsImeRect.getBottom());
    L9:
        return this.insetsImeRect.getBottom();
    }

    @Override // org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider.Listener
    public View getAnimatedInsetsTargetView() {
        return this.animatedInsetsProviderTarget;
    }

    public float getAnimatedKeyboardVisibility() {
        return this.keyboardVisibility.get();
    }

    @Override // org.telegram.ui.Components.inset.WindowInsetsProvider
    public float getAnimatedMaxBottomInset() {
        if (this.animatedInsetsProvider == null) goto L9;
        if (this.activeAnimations <= 0) goto L9;
        return Math.max(this.animatedImeInset, this.insetsMaxRect.getBottom());
    L9:
        return this.insetsMaxRect.getBottom();
    }

    public int getCurrentMaxBottomInset() {
        if (this.animatedInsetsProvider == null) goto L9;
        if (this.activeAnimations <= 0) goto L9;
        return Math.max(this.animatedImeInset, Math.max(getInsets(WindowInsetsCompat.Type.ime() | WindowInsetsCompat.Type.systemBars()).bottom, this.inAppKeyboardHeight));
    L9:
        return Math.max(getInsets(WindowInsetsCompat.Type.ime() | WindowInsetsCompat.Type.systemBars()).bottom, this.inAppKeyboardHeight);
    }

    @Override // org.telegram.ui.Components.inset.WindowInsetsProvider
    public int getCurrentNavigationBarInset() {
        WindowInsetsCompat r1 = this.lastInsets;
        if (r1 != null) goto L5;
        return 0;
    L5:
        return r1.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
    }

    public int getInAppKeyboardHeight() {
        return this.inAppKeyboardHeight;
    }

    @Override // org.telegram.ui.Components.inset.WindowInsetsProvider
    public int getInAppKeyboardRecommendedViewHeight() {
        return this.inAppKeyboardViewHeight;
    }

    public Insets getInsets(int r1) {
        WindowInsetsCompat r0 = this.lastInsets;
        if (r0 == null) goto L7;
        return r0.getInsets(r1);
    L7:
        return Insets.NONE;
    }

    @Override // org.telegram.ui.Components.inset.WindowInsetsProvider
    public boolean inAppViewIsVisible() {
        if (this.inAppKeyboardState == 1) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider.Listener
    public void onAnimatedInsetsChanged(View r1, WindowInsetsCompat r2) {
        this.animatedImeInset = r2.getInsets(WindowInsetsCompat.Type.ime()).bottom;
        this.onUpdateListener.run();
    }

    @Override // org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider.Listener
    public void onAnimatedInsetsFinished() {
        View r0 = this.animatedInsetsProviderTarget;
        if (r0 == null) goto L6;
        r0.postOnAnimation(new WindowInsetsStateHolder$$ExternalSyntheticLambda2(this));
        return;
    }

    @Override // org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider.Listener
    public void onAnimatedInsetsStarted() {
        this.activeAnimations++;
    }

    @Override // org.telegram.ui.Components.inset.WindowInsetsInAppController
    public void requestInAppKeyboardHeight(int r2) {
        if (this.inAppKeyboardHeight == r2) goto L5;
    L8:
        AndroidUtilities.cancelRunOnUIThread(this.closeInAppKeyboard);
        this.inAppKeyboardViewHeight = Math.max(this.inAppKeyboardHeight, r2);
        this.inAppKeyboardHeight = r2;
        this.inAppKeyboardState = 0;
        setInsets(this.lastInsets);
        return;
    L5:
        if (this.inAppKeyboardState != 0) goto L8;
    }

    @Override // org.telegram.ui.Components.inset.WindowInsetsInAppController
    public void resetInAppKeyboardHeight(boolean r3) {
        if (this.inAppKeyboardHeight == 0) goto L14;
        AndroidUtilities.cancelRunOnUIThread(this.closeInAppKeyboard);
        if (r3 == false) goto L8;
        int r0 = 3;
    L9:
        this.inAppKeyboardState = r0;
        setInsets(this.lastInsets);
        if (r3 == false) goto L13;
        AndroidUtilities.runOnUIThread(this.closeInAppKeyboard, 1000);
        return;
    L13:
        return;
    L8:
        r0 = 2;
        goto L9
    }

    public void setInsets(WindowInsetsCompat r2) {
        if (this.lastInsets == null) goto L5;
        boolean r0 = true;
    L6:
        setInsets(r2, r0);
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void setupAnimatedInsetsProvider(WindowAnimatedInsetsProvider r1, View r2) {
        this.animatedInsetsProvider = r1;
        this.animatedInsetsProviderTarget = r2;
        r1.subscribeToWindowInsetsAnimation(this);
    }
}
