package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.WindowManager;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.VoIPFragment;
import org.webrtc.OrientationHelper;

/* loaded from: classes7.dex */
public abstract class VoIPWindowView extends FrameLayout {
    Activity activity;
    boolean finished;
    protected boolean lockOnScreen;
    private AnimationNotificationsLocker notificationsLocker;
    boolean runEnterTransition;
    boolean startDragging;
    float startX;
    float startY;
    VelocityTracker velocityTracker;

    /* renamed from: org.telegram.ui.Components.voip.VoIPWindowView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPWindowView this$0;

        public AnonymousClass1(VoIPWindowView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            VoIPWindowView.m13997$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            AndroidUtilities.unlockOrientation(this.this$0.activity);
            if (this.this$0.getParent() == null) goto L11;
            WindowManager r32 = (WindowManager) this.this$0.activity.getSystemService("window");
            this.this$0.setVisibility(8);
            r32.removeView(this.this$0);     // Catch: Exception -> L8
        L6:
            OrientationHelper.cameraRotationDisabled = false;
            return;
        }
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m13997$$Nest$fgetnotificationsLocker(VoIPWindowView r0) {
        return r0.notificationsLocker;
    }

    public VoIPWindowView(Activity r2, boolean r3) {
        super(r2);
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.activity = r2;
        setSystemUiVisibility(1792);
        AndroidUtilities.lockOrientation(r2, 1);
        OrientationHelper.cameraRotationDisabled = true;
        if (r3 == true) goto L6;
        this.runEnterTransition = true;
        return;
    }

    public WindowManager.LayoutParams createWindowLayoutParams() {
        WindowManager.LayoutParams r2 = new WindowManager.LayoutParams();
        r2.height = -1;
        r2.format = -2;
        r2.width = -1;
        r2.gravity = 51;
        r2.type = 99;
        r2.screenOrientation = 1;
        AndroidUtilities.applyEdgeToEdgeLayoutParams(r2);
        r2.flags = -2144665216;
        return r2;
    }

    public void finish() {
        finish(330);
    }

    public void finish(long r3) {
        if (this.finished == true) goto L13;
        this.finished = true;
        VoIPFragment.clearInstance();
        if (this.lockOnScreen == true) goto L11;
        int r0 = UserConfig.selectedAccount;
        this.notificationsLocker.lock();
        animate().translationY(getMeasuredHeight()).alpha(0.0f).setListener(new AnonymousClass1(this)).setDuration(r3).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        return;
    L11:
        ((WindowManager) this.activity.getSystemService("window")).removeView(this);     // Catch: Exception -> L10
        return;
    L14:
        return;
    }

    public void finishImmediate() {
        if (getParent() == null) goto L6;
        AndroidUtilities.unlockOrientation(this.activity);
        WindowManager r0 = (WindowManager) this.activity.getSystemService("window");
        setVisibility(8);
        r0.removeView(this);
        OrientationHelper.cameraRotationDisabled = false;
        return;
    }

    public boolean isLockOnScreen() {
        return this.lockOnScreen;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return onTouchEvent(r1);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        if (this.runEnterTransition == true) goto L6;
        this.runEnterTransition = true;
        startEnterTransition();
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (this.lockOnScreen == false) goto L6;
        return false;
    L6:
        if (r8.getAction() != 0) goto L11;
        this.startX = r8.getX();
        this.startY = r8.getY();
        if (this.velocityTracker != null) goto L10;
        this.velocityTracker = VelocityTracker.obtain();
    L10:
        this.velocityTracker.clear();
    L48:
        return false;
    L11:
        float r5 = 0.0f;
        if (r8.getAction() != 2) goto L33;
        float r0 = r8.getX() - this.startX;
        float r1 = r8.getY() - this.startY;
        if (this.startDragging == true) goto L21;
        if (Math.abs(r1) <= AndroidUtilities.getPixelsInCM(0.4f, true)) goto L21;
        if ((Math.abs(r1) / 3.0f) <= r0) goto L21;
        this.startY = r8.getY();
        this.startDragging = true;
        r1 = 0.0f;
    L21:
        if (this.startDragging == false) goto L31;
        if (r1 < 0.0f) goto L27;
        r5 = r1;
    L27:
        if (this.velocityTracker != null) goto L29;
        this.velocityTracker = VelocityTracker.obtain();
    L29:
        this.velocityTracker.addMovement(r8);
        setTranslationY(r5);
    L31:
        return this.startDragging;
    L33:
        if (r8.getAction() != 1) goto L35;
    L36:
        float r82 = getTranslationY();
        if (this.velocityTracker != null) goto L39;
        this.velocityTracker = VelocityTracker.obtain();
    L39:
        this.velocityTracker.computeCurrentVelocity(1000);
        float r02 = this.velocityTracker.getXVelocity();
        float r2 = this.velocityTracker.getYVelocity();
        if (r82 < (getMeasuredHeight() / 3.0f)) goto L42;
    L46:
        float r83 = getMeasuredHeight() - getTranslationY();
        finish(Math.max((int) ((200.0f / getMeasuredHeight()) * r83), 50));
    L47:
        this.startDragging = false;
        goto L48
    L42:
        if (r02 >= 3500.0f) goto L44;
    L45:
        animate().translationY(0.0f).start();
        goto L47
    L44:
        if (r02 >= r2) goto L46;
    L35:
        if (r8.getAction() != 3) goto L48;
        goto L36
    }

    public void requestFullscreen(boolean r1) {
        if (r1 == false) goto L5;
        setSystemUiVisibility(getSystemUiVisibility() | 4);
        return;
    L5:
        setSystemUiVisibility(getSystemUiVisibility() & (-5));
    }

    public void setLockOnScreen(boolean r1) {
        this.lockOnScreen = r1;
    }

    public void startEnterTransition() {
        if (this.lockOnScreen == true) goto L6;
        setTranslationY(getMeasuredHeight());
        setAlpha(0.0f);
        animate().translationY(0.0f).alpha(1.0f).setDuration(330).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        return;
    }
}
