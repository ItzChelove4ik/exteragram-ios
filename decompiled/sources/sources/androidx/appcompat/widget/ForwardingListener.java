package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.appcompat.view.menu.ShowableListMenu;

/* loaded from: classes3.dex */
public abstract class ForwardingListener implements View.OnTouchListener, View.OnAttachStateChangeListener {
    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    final View mSrc;
    private final int mTapTimeout;
    private final int[] mTmpLocation;
    private Runnable mTriggerLongPress;

    public class DisallowIntercept implements Runnable {
        final /* synthetic */ ForwardingListener this$0;

        public DisallowIntercept(ForwardingListener r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent r1 = this.this$0.mSrc.getParent();
            if (r1 == null) goto L6;
            r1.requestDisallowInterceptTouchEvent(true);
            return;
        }
    }

    public class TriggerLongPress implements Runnable {
        final /* synthetic */ ForwardingListener this$0;

        public TriggerLongPress(ForwardingListener r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.onLongPress();
        }
    }

    public ForwardingListener(View r3) {
        this.mTmpLocation = new int[2];
        this.mSrc = r3;
        r3.setLongClickable(true);
        r3.addOnAttachStateChangeListener(this);
        this.mScaledTouchSlop = ViewConfiguration.get(r3.getContext()).getScaledTouchSlop();
        int r32 = ViewConfiguration.getTapTimeout();
        this.mTapTimeout = r32;
        this.mLongPressTimeout = (r32 + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    private void clearCallbacks() {
        Runnable r0 = this.mTriggerLongPress;
        if (r0 == null) goto L5;
        this.mSrc.removeCallbacks(r0);
    L5:
        Runnable r02 = this.mDisallowIntercept;
        if (r02 == null) goto L9;
        this.mSrc.removeCallbacks(r02);
        return;
    }

    private boolean onTouchForwarded(MotionEvent r5) {
        View r0 = this.mSrc;
        ShowableListMenu r1 = getPopup();
        if (r1 != null) goto L5;
    L21:
        return false;
    L5:
        if (r1.isShowing() == false) goto L21;
        DropDownListView r12 = (DropDownListView) r1.getListView();
        if (r12 == null) goto L21;
        if (r12.isShown() == false) goto L21;
        MotionEvent r3 = MotionEvent.obtainNoHistory(r5);
        toGlobalMotionEvent(r0, r3);
        toLocalMotionEvent(r12, r3);
        boolean r4 = r12.onForwardedEvent(r3, this.mActivePointerId);
        r3.recycle();
        int r52 = r5.getActionMasked();
        if (r52 != 1) goto L15;
    L17:
        boolean r53 = false;
    L18:
        if (r4 == false) goto L21;
        if (r53 == false) goto L21;
        return true;
    L15:
        if (r52 == 3) goto L17;
        r53 = true;
        goto L18
    }

    private boolean onTouchObserved(MotionEvent r6) {
        View r0 = this.mSrc;
        if (r0.isEnabled() == true) goto L5;
        return false;
    L5:
        int r1 = r6.getActionMasked();
        if (r1 != 0) goto L8;
        this.mActivePointerId = r6.getPointerId(0);
        if (this.mDisallowIntercept != null) goto L24;
        this.mDisallowIntercept = new DisallowIntercept(this);
    L24:
        r0.postDelayed(this.mDisallowIntercept, this.mTapTimeout);
        if (this.mTriggerLongPress != null) goto L27;
        this.mTriggerLongPress = new TriggerLongPress(this);
    L27:
        r0.postDelayed(this.mTriggerLongPress, this.mLongPressTimeout);
    L28:
        return false;
    L8:
        if (r1 != 1) goto L10;
    L20:
        clearCallbacks();
        goto L28
    L10:
        if (r1 != 2) goto L12;
        int r12 = r6.findPointerIndex(this.mActivePointerId);
        if (r12 < 0) goto L28;
        if (pointInView(r0, r6.getX(r12), r6.getY(r12), this.mScaledTouchSlop) == true) goto L28;
        clearCallbacks();
        r0.getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    L12:
        if (r1 == 3) goto L20;
        goto L20
    }

    private static boolean pointInView(View r2, float r3, float r4, float r5) {
        float r0 = -r5;
        if (r3 >= r0) goto L5;
        return false;
    L5:
        if (r4 >= r0) goto L7;
        return false;
    L7:
        if (r3 < ((r2.getRight() - r2.getLeft()) + r5)) goto L9;
        return false;
    L9:
        if (r4 >= ((r2.getBottom() - r2.getTop()) + r5)) goto L16;
        return true;
    L16:
        return false;
    }

    private boolean toGlobalMotionEvent(View r2, MotionEvent r3) {
        r2.getLocationOnScreen(this.mTmpLocation);
        r3.offsetLocation(r1[0], r1[1]);
        return true;
    }

    private boolean toLocalMotionEvent(View r2, MotionEvent r3) {
        r2.getLocationOnScreen(this.mTmpLocation);
        r3.offsetLocation(-r1[0], -r1[1]);
        return true;
    }

    public abstract ShowableListMenu getPopup();

    public abstract boolean onForwardingStarted();

    public boolean onForwardingStopped() {
        ShowableListMenu r1 = getPopup();
        if (r1 != null) goto L5;
        return true;
    L5:
        if (r1.isShowing() == false) goto L10;
        r1.dismiss();
        return true;
    L10:
        return true;
    }

    public void onLongPress() {
        clearCallbacks();
        View r0 = this.mSrc;
        if (r0.isEnabled() == true) goto L5;
        return;
    L5:
        if (r0.isLongClickable() == false) goto L8;
        return;
    L8:
        if (onForwardingStarted() == false) goto L14;
        r0.getParent().requestDisallowInterceptTouchEvent(true);
        long r3 = SystemClock.uptimeMillis();
        MotionEvent r1 = MotionEvent.obtain(r3, r3, 3, 0.0f, 0.0f, 0);
        r0.onTouchEvent(r1);
        r1.recycle();
        this.mForwarding = true;
        return;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View r11, MotionEvent r12) {
        boolean r112 = this.mForwarding;
        if (r112 == false) goto L12;
        if (onTouchForwarded(r12) == false) goto L7;
    L10:
        boolean r122 = true;
    L19:
        this.mForwarding = r122;
        if (r122 == true) goto L24;
        if (r112 == true) goto L24;
        return false;
    L24:
        return true;
    L7:
        if (onForwardingStopped() == false) goto L10;
        r122 = false;
        goto L19
    L12:
        if (onTouchObserved(r12) == true) goto L14;
    L16:
        r122 = false;
    L17:
        if (r122 == false) goto L19;
        long r2 = SystemClock.uptimeMillis();
        MotionEvent r22 = MotionEvent.obtain(r2, r2, 3, 0.0f, 0.0f, 0);
        this.mSrc.onTouchEvent(r22);
        r22.recycle();
        goto L19
    L14:
        if (onForwardingStarted() == false) goto L16;
        r122 = true;
        goto L17
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View r1) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View r1) {
        this.mForwarding = false;
        this.mActivePointerId = -1;
        Runnable r12 = this.mDisallowIntercept;
        if (r12 == null) goto L6;
        this.mSrc.removeCallbacks(r12);
        return;
    }
}
