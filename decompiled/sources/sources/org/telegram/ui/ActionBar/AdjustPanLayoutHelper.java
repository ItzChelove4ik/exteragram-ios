package org.telegram.ui.ActionBar;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsAnimation$Callback;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import androidx.core.view.WindowInsetsAnimationCompat$Impl30$ProxyCallback$$ExternalSyntheticApiModelOutline0;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.recyclerview.ChatListItemAnimator;

/* loaded from: classes3.dex */
public abstract class AdjustPanLayoutHelper {
    public static boolean USE_ANDROID11_INSET_ANIMATOR = false;
    public static final Interpolator keyboardInterpolator = null;
    private boolean animationInProgress;
    ValueAnimator animator;
    boolean checkHierarchyHeight;
    private ViewGroup contentView;
    private Runnable delayedAnimationRunnable;
    private boolean enabled;
    float from;
    private boolean ignoreOnce;
    boolean inverse;
    boolean isKeyboardVisible;
    protected float keyboardSize;
    private boolean needDelay;
    AnimationNotificationsLocker notificationsLocker;
    ViewTreeObserver.OnPreDrawListener onPreDrawListener;
    private final View parent;
    View parentForListener;
    int previousContentHeight;
    int previousHeight;
    int previousStartOffset;
    private View resizableView;
    private View resizableViewToSet;
    public boolean showingKeyboard;
    long startAfter;
    float to;
    private boolean useInsetsAnimator;
    private boolean usingInsetAnimator;
    ArrayList<View> viewsToHeightSet;

    /* renamed from: org.telegram.ui.ActionBar.AdjustPanLayoutHelper$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ AdjustPanLayoutHelper this$0;

        public AnonymousClass1(AdjustPanLayoutHelper r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ValueAnimator r0 = this.this$0.animator;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isRunning() == true) goto L9;
            this.this$0.animator.start();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AdjustPanLayoutHelper$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ AdjustPanLayoutHelper this$0;

        public AnonymousClass2(AdjustPanLayoutHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            int r0 = AdjustPanLayoutHelper.m5554$$Nest$fgetparent(this.this$0).getHeight();
            int r1 = r0 - this.this$0.startOffset();
            AdjustPanLayoutHelper r2 = this.this$0;
            int r3 = r2.previousHeight;
            boolean r5 = true;
            if (r1 == (r3 - r2.previousStartOffset)) goto L28;
            if (r0 == r3) goto L28;
            if (r2.animator != null) goto L28;
            if (r2.heightAnimationEnabled() == true) goto L11;
        L25:
            AdjustPanLayoutHelper r12 = this.this$0;
            r12.previousHeight = r0;
            r12.previousContentHeight = AdjustPanLayoutHelper.m5553$$Nest$fgetcontentView(r12).getHeight();
            AdjustPanLayoutHelper r02 = this.this$0;
            r02.previousStartOffset = r02.startOffset();
            AdjustPanLayoutHelper.m5556$$Nest$fputusingInsetAnimator(this.this$0, false);
            return true;
        L11:
            if (Math.abs(this.this$0.previousHeight - r0) < AndroidUtilities.dp(20.0f)) goto L25;
            AdjustPanLayoutHelper r13 = this.this$0;
            if (r13.previousHeight != (-1)) goto L16;
        L23:
            AdjustPanLayoutHelper r14 = this.this$0;
            r14.previousHeight = r0;
            r14.previousContentHeight = AdjustPanLayoutHelper.m5553$$Nest$fgetcontentView(r14).getHeight();
            AdjustPanLayoutHelper r7 = this.this$0;
            r7.previousStartOffset = r7.startOffset();
            return false;
        L16:
            if (r13.previousContentHeight != AdjustPanLayoutHelper.m5553$$Nest$fgetcontentView(r13).getHeight()) goto L23;
            AdjustPanLayoutHelper r15 = this.this$0;
            if (r0 < AdjustPanLayoutHelper.m5553$$Nest$fgetcontentView(r15).getBottom()) goto L21;
            r5 = false;
        L21:
            r15.isKeyboardVisible = r5;
            AdjustPanLayoutHelper r16 = this.this$0;
            AdjustPanLayoutHelper.m5557$$Nest$manimateHeight(r16, r16.previousHeight, r0, r16.isKeyboardVisible);
            AdjustPanLayoutHelper r17 = this.this$0;
            r17.previousHeight = r0;
            r17.previousContentHeight = AdjustPanLayoutHelper.m5553$$Nest$fgetcontentView(r17).getHeight();
            AdjustPanLayoutHelper r72 = this.this$0;
            r72.previousStartOffset = r72.startOffset();
            return false;
        L28:
            if (r2.animator != null) goto L30;
            r2.previousHeight = r0;
            r2.previousContentHeight = AdjustPanLayoutHelper.m5553$$Nest$fgetcontentView(r2).getHeight();
            AdjustPanLayoutHelper r03 = this.this$0;
            r03.previousStartOffset = r03.startOffset();
            AdjustPanLayoutHelper.m5556$$Nest$fputusingInsetAnimator(this.this$0, false);
        L30:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AdjustPanLayoutHelper$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ AdjustPanLayoutHelper this$0;

        public AnonymousClass3(AdjustPanLayoutHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (AdjustPanLayoutHelper.m5555$$Nest$fgetusingInsetAnimator(this.this$0) == true) goto L6;
            this.this$0.stopTransition();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.AdjustPanLayoutHelper$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends WindowInsetsAnimation$Callback {
        final /* synthetic */ AdjustPanLayoutHelper this$0;

        public AnonymousClass4(AdjustPanLayoutHelper r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        public void onEnd(WindowInsetsAnimation r2) {
            if (AdjustPanLayoutHelper.m5552$$Nest$fgetanimationInProgress(this.this$0) == true) goto L5;
            return;
        L5:
            if (AndroidUtilities.screenRefreshRate < 90.0f) goto L10;
            this.this$0.stopTransition();
            return;
        }

        public WindowInsets onProgress(WindowInsets r6, List<WindowInsetsAnimation> r7) {
            if (AdjustPanLayoutHelper.m5552$$Nest$fgetanimationInProgress(this.this$0) == true) goto L5;
        L18:
            return r6;
        L5:
            if (AndroidUtilities.screenRefreshRate < 90.0f) goto L18;
            Iterator<WindowInsetsAnimation> r72 = r7.iterator();
        L9:
            if (r72.hasNext() == false) goto L13;
            WindowInsetsAnimation r0 = WindowInsetsAnimationCompat$Impl30$ProxyCallback$$ExternalSyntheticApiModelOutline0.m(r72.next());
            if ((r0.getTypeMask() & WindowInsetsCompat.Type.ime()) == 0) goto L9;
        L14:
            if (r0 == null) goto L18;
            long r1 = SystemClock.elapsedRealtime();
            AdjustPanLayoutHelper r73 = this.this$0;
            if (r1 < r73.startAfter) goto L18;
            AdjustPanLayoutHelper.m5556$$Nest$fputusingInsetAnimator(r73, true);
            this.this$0.updateTransition(r0.getInterpolatedFraction());
            goto L18
        L13:
            r0 = null;
            goto L14
        }
    }

    /* renamed from: $r8$lambda$kbgU-Mceo4kaVDBMyrt7WVLsBYs, reason: not valid java name */
    public static /* synthetic */ void m5551$r8$lambda$kbgUMceo4kaVDBMyrt7WVLsBYs(AdjustPanLayoutHelper r0, ValueAnimator r1) {
        r0.lambda$animateHeight$0(r1);
    }

    /* renamed from: -$$Nest$fgetanimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5552$$Nest$fgetanimationInProgress(AdjustPanLayoutHelper r0) {
        return r0.animationInProgress;
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m5553$$Nest$fgetcontentView(AdjustPanLayoutHelper r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetparent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m5554$$Nest$fgetparent(AdjustPanLayoutHelper r0) {
        return r0.parent;
    }

    /* renamed from: -$$Nest$fgetusingInsetAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5555$$Nest$fgetusingInsetAnimator(AdjustPanLayoutHelper r0) {
        return r0.usingInsetAnimator;
    }

    /* renamed from: -$$Nest$fputusingInsetAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5556$$Nest$fputusingInsetAnimator(AdjustPanLayoutHelper r0, boolean r1) {
        r0.usingInsetAnimator = r1;
    }

    /* renamed from: -$$Nest$manimateHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5557$$Nest$manimateHeight(AdjustPanLayoutHelper r0, int r1, int r2, boolean r3) {
        r0.animateHeight(r1, r2, r3);
    }

    static {
        keyboardInterpolator = ChatListItemAnimator.DEFAULT_INTERPOLATOR;
    }

    public AdjustPanLayoutHelper(View r2) {
        this(r2, USE_ANDROID11_INSET_ANIMATOR);
    }

    public AdjustPanLayoutHelper(View r2, boolean r3) {
        this.usingInsetAnimator = false;
        this.delayedAnimationRunnable = new AnonymousClass1(this);
        this.previousHeight = -1;
        this.previousContentHeight = -1;
        this.previousStartOffset = -1;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.viewsToHeightSet = new ArrayList();
        this.onPreDrawListener = new AnonymousClass2(this);
        this.enabled = true;
        this.useInsetsAnimator = r3;
        this.parent = r2;
        AndroidUtilities.runOnUIThread(new AdjustPanLayoutHelper$$ExternalSyntheticLambda0(this));
    }

    private void animateHeight(int r3, int r4, boolean r5) {
        if (this.ignoreOnce == false) goto L7;
        this.ignoreOnce = false;
        return;
    L7:
        if (this.enabled == true) goto L9;
        return;
    L9:
        startTransition(r3, r4, r5);
        this.animator.addUpdateListener(new AdjustPanLayoutHelper$$ExternalSyntheticLambda1(this));
        this.animator.addListener(new AnonymousClass3(this));
        this.animator.setDuration(250);
        this.animator.setInterpolator(keyboardInterpolator);
        this.notificationsLocker.lock();
        if (this.needDelay == false) goto L13;
        this.needDelay = false;
        this.startAfter = SystemClock.elapsedRealtime() + 100;
        AndroidUtilities.runOnUIThread(this.delayedAnimationRunnable, 100);
        return;
    L13:
        this.animator.start();
        this.startAfter = -1;
    }

    private View findResizableView(View r2) {
        View r1 = this.resizableViewToSet;
        if (r1 == null) goto L6;
        return r1;
    L6:
        if (r2 == null) goto L13;
        if ((r2.getParent() instanceof DrawerLayoutContainer) == true) goto L9;
        if ((r2.getParent() instanceof View) == false) goto L13;
        r2 = (View) r2.getParent();
        goto L6
    L9:
        return r2;
    L13:
        return null;
    }

    private Activity getActivity(Context r2) {
        if ((r2 instanceof Activity) == false) goto L7;
        return (Activity) r2;
    L7:
        if ((r2 instanceof ContextThemeWrapper) == true) goto L9;
        return null;
    L9:
        return getActivity(((ContextThemeWrapper) r2).getBaseContext());
    }

    private /* synthetic */ void lambda$animateHeight$0(ValueAnimator r2) {
        if (this.usingInsetAnimator == true) goto L6;
        updateTransition(((Float) r2.getAnimatedValue()).floatValue());
        return;
    }

    private void setupNewCallback() {
        View r0 = this.resizableView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setWindowInsetsAnimationCallback(new AnonymousClass4(this, 1));
    }

    public boolean animationInProgress() {
        return this.animationInProgress;
    }

    public boolean applyTranslation() {
        return true;
    }

    public void delayAnimation() {
        this.needDelay = true;
    }

    public void getViewsToSetHeight(View r2) {
        this.viewsToHeightSet.clear();
    L3:
        if (r2 == null) goto L11;
        this.viewsToHeightSet.add(r2);
        if (r2 == this.resizableView) goto L19;
        if ((r2.getParent() instanceof View) == true) goto L9;
        r2 = null;
        goto L3
    L9:
        r2 = (View) r2.getParent();
        goto L3
    L19:
        return;
    }

    public abstract boolean heightAnimationEnabled();

    public void ignoreOnce() {
        this.ignoreOnce = true;
    }

    public void onAttach() {
        onDetach();
        Activity r0 = getActivity(this.parent.getContext());
        if (r0 == null) goto L5;
        this.contentView = (ViewGroup) ((ViewGroup) r0.getWindow().getDecorView()).findViewById(R.id.content);
    L5:
        View r02 = findResizableView(this.parent);
        this.resizableView = r02;
        if (r02 == null) goto L9;
        this.parentForListener = r02;
        r02.getViewTreeObserver().addOnPreDrawListener(this.onPreDrawListener);
    L9:
        if (this.useInsetsAnimator == true) goto L11;
        return;
    L11:
        if (Build.VERSION.SDK_INT < 30) goto L15;
        setupNewCallback();
        return;
    }

    public void onDetach() {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        View r02 = this.parentForListener;
        if (r02 == null) goto L8;
        r02.getViewTreeObserver().removeOnPreDrawListener(this.onPreDrawListener);
        this.parentForListener = null;
    L8:
        View r03 = this.parent;
        if (r03 != null) goto L11;
        return;
    L11:
        if (this.useInsetsAnimator == true) goto L13;
        return;
    L13:
        if (Build.VERSION.SDK_INT < 30) goto L18;
        r03.setWindowInsetsAnimationCallback(null);
        return;
    }

    public void onPanTranslationUpdate(float r1, float r2, boolean r3) {
    }

    public void onTransitionEnd() {
    }

    public void onTransitionStart(boolean r1, int r2) {
    }

    public void onTransitionStart(boolean r1, int r2, int r3) {
        onTransitionStart(r1, r3);
    }

    public void runDelayedAnimation() {
        AndroidUtilities.cancelRunOnUIThread(this.delayedAnimationRunnable);
        this.delayedAnimationRunnable.run();
    }

    public void setResizableView(FrameLayout r1) {
        this.resizableViewToSet = r1;
    }

    public void setViewHeight(int r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.viewsToHeightSet.size()) goto L6;
        this.viewsToHeightSet.get(r0).getLayoutParams().height = r3;
        this.viewsToHeightSet.get(r0).requestLayout();
        r0 = r0 + 1;
        goto L4
    }

    public int startOffset() {
        return 0;
    }

    public void startTransition(int r7, int r8, boolean r9) {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        int r02 = startOffset();
        getViewsToSetHeight(this.parent);
        if (this.checkHierarchyHeight == false) goto L10;
        Object r1 = this.parent.getParent();
        if ((r1 instanceof View) == false) goto L10;
        int r12 = ((View) r1).getHeight() - r8;
    L11:
        LaunchActivity r3 = LaunchActivity.instance;
        if (r3 != null) goto L14;
    L16:
        int r32 = 0;
    L18:
        if (applyTranslation() == false) goto L20;
        setViewHeight(Math.max(r7, (r12 + r8) + r32));
    L20:
        this.resizableView.requestLayout();
        onTransitionStart(r9, r7, r8);
        float r13 = r8 - r7;
        this.keyboardSize = Math.abs(r13);
        this.animationInProgress = true;
        if (r8 > r7) goto L23;
        boolean r5 = true;
    L24:
        this.showingKeyboard = r5;
        if (r8 <= r7) goto L31;
        float r14 = r13 - r02;
        if (applyTranslation() == false) goto L29;
        this.parent.setTranslationY(-r14);
    L29:
        onPanTranslationUpdate(r14, 1.0f, r9);
        this.from = -r14;
        this.to = -r32;
        this.inverse = true;
    L34:
        this.animator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.usingInsetAnimator = false;
        return;
    L31:
        if (applyTranslation() == false) goto L33;
        this.parent.setTranslationY(this.previousStartOffset);
    L33:
        onPanTranslationUpdate(-this.previousStartOffset, 0.0f, r9);
        this.to = -this.previousStartOffset;
        this.from = r13;
        this.inverse = false;
        goto L34
    L23:
        r5 = false;
        goto L24
    L14:
        if (r3.getBottomSheetTabs() == null) goto L16;
        r32 = LaunchActivity.instance.getBottomSheetTabs().getExpandedHeight();
    L10:
        r12 = 0;
        goto L11
    }

    public void stopTransition() {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.animationInProgress = false;
        this.usingInsetAnimator = false;
        this.notificationsLocker.unlock();
        this.animator = null;
        setViewHeight(-1);
        this.viewsToHeightSet.clear();
        this.resizableView.requestLayout();
        boolean r02 = this.isKeyboardVisible;
        if (r02 == false) goto L8;
        float r2 = 1.0f;
    L9:
        onPanTranslationUpdate(0.0f, r2, r02);
        if (applyTranslation() == false) goto L12;
        this.parent.setTranslationY(0.0f);
    L12:
        onTransitionEnd();
        return;
    L8:
        r2 = 0.0f;
        goto L9
    }

    public void updateTransition(float r4) {
        if (this.inverse == false) goto L5;
        r4 = 1.0f - r4;
    L5:
        float r0 = (int) ((this.from * r4) + (this.to * (1.0f - r4)));
        if (applyTranslation() == false) goto L8;
        this.parent.setTranslationY(r0);
    L8:
        onPanTranslationUpdate(-r0, r4, this.isKeyboardVisible);
    }
}
