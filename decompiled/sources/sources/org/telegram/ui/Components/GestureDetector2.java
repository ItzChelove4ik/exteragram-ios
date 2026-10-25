package org.telegram.ui.Components;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import c.f$$ExternalSyntheticBUOutline2;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;

/* loaded from: classes7.dex */
public class GestureDetector2 {
    public static final int DOUBLE_TAP_TIMEOUT = 0;
    private static final int LONGPRESS_TIMEOUT = 0;
    private static final int TAP_TIMEOUT = 0;
    private boolean mAlwaysInBiggerTapRegion;
    private boolean mAlwaysInTapRegion;
    private MotionEvent mCurrentDownEvent;
    private MotionEvent mCurrentMotionEvent;
    private boolean mDeferConfirmSingleTap;
    private OnDoubleTapListener mDoubleTapListener;
    private int mDoubleTapSlopSquare;
    private int mDoubleTapTouchSlopSquare;
    private float mDownFocusX;
    private float mDownFocusY;
    private final Handler mHandler;
    private boolean mIgnoreNextUpEvent;
    private boolean mInContextClick;
    private boolean mInLongPress;
    private boolean mIsDoubleTapping;
    private boolean mIsLongpressEnabled;
    private float mLastFocusX;
    private float mLastFocusY;
    private final OnGestureListener mListener;
    private int mMaximumFlingVelocity;
    private int mMinimumFlingVelocity;
    private MotionEvent mPreviousUpEvent;
    private boolean mStillDown;
    private int mTouchSlopSquare;
    private VelocityTracker mVelocityTracker;

    public class GestureHandler extends Handler {
        final /* synthetic */ GestureDetector2 this$0;

        public GestureHandler(GestureDetector2 r1) {
            this.this$0 = r1;
        }

        public GestureHandler(GestureDetector2 r1, Handler r2) {
            this.this$0 = r1;
            super(r2.getLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message r4) {
            int r0 = r4.what;
            if (r0 != 1) goto L5;
            GestureDetector2.m10825$$Nest$fgetmListener(this.this$0).onShowPress(GestureDetector2.m10823$$Nest$fgetmCurrentDownEvent(this.this$0));
            return;
        L5:
            if (r0 != 2) goto L7;
            GestureDetector2.m10828$$Nest$mdispatchLongPress(this.this$0);
            return;
        L7:
            if (r0 == 3) goto L9;
            MVEL$$ExternalSyntheticBUOutline0.m("Unknown message ", r4);
            return;
        L9:
            if (GestureDetector2.m10824$$Nest$fgetmDoubleTapListener(this.this$0) == null) goto L22;
            boolean r42 = GestureDetector2.m10826$$Nest$fgetmStillDown(this.this$0);
            GestureDetector2 r02 = this.this$0;
            if (r42 == true) goto L14;
            GestureDetector2.m10824$$Nest$fgetmDoubleTapListener(r02).onSingleTapConfirmed(GestureDetector2.m10823$$Nest$fgetmCurrentDownEvent(this.this$0));
            return;
        L14:
            GestureDetector2.m10827$$Nest$fputmDeferConfirmSingleTap(r02, true);
            return;
        }
    }

    /* loaded from: classes3.dex */
    public interface OnDoubleTapListener {
        boolean canDoubleTap(MotionEvent r1);

        boolean onDoubleTap(MotionEvent r1);

        boolean onDoubleTapEvent(MotionEvent r1);

        boolean onSingleTapConfirmed(MotionEvent r1);
    }

    /* loaded from: classes3.dex */
    public interface OnGestureListener {
        boolean onDown(MotionEvent r1);

        boolean onFling(MotionEvent r1, MotionEvent r2, float r3, float r4);

        void onLongPress(MotionEvent r1);

        boolean onScroll(MotionEvent r1, MotionEvent r2, float r3, float r4);

        void onShowPress(MotionEvent r1);

        boolean onSingleTapUp(MotionEvent r1);

        void onUp(MotionEvent r1);
    }

    /* renamed from: -$$Nest$fgetmCurrentDownEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MotionEvent m10823$$Nest$fgetmCurrentDownEvent(GestureDetector2 r0) {
        return r0.mCurrentDownEvent;
    }

    /* renamed from: -$$Nest$fgetmDoubleTapListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OnDoubleTapListener m10824$$Nest$fgetmDoubleTapListener(GestureDetector2 r0) {
        return r0.mDoubleTapListener;
    }

    /* renamed from: -$$Nest$fgetmListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OnGestureListener m10825$$Nest$fgetmListener(GestureDetector2 r0) {
        return r0.mListener;
    }

    /* renamed from: -$$Nest$fgetmStillDown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10826$$Nest$fgetmStillDown(GestureDetector2 r0) {
        return r0.mStillDown;
    }

    /* renamed from: -$$Nest$fputmDeferConfirmSingleTap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10827$$Nest$fputmDeferConfirmSingleTap(GestureDetector2 r0, boolean r1) {
        r0.mDeferConfirmSingleTap = r1;
    }

    /* renamed from: -$$Nest$mdispatchLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10828$$Nest$mdispatchLongPress(GestureDetector2 r0) {
        r0.dispatchLongPress();
    }

    static {
        LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
        TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
        DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    }

    public GestureDetector2(Context r2, OnGestureListener r3) {
        this(r2, r3, null);
    }

    public GestureDetector2(Context r2, OnGestureListener r3, Handler r4) {
        if (r4 == null) goto L5;
        this.mHandler = new GestureHandler(this, r4);
    L6:
        this.mListener = r3;
        if ((r3 instanceof OnDoubleTapListener) == false) goto L9;
        setOnDoubleTapListener((OnDoubleTapListener) r3);
    L9:
        init(r2);
        return;
    L5:
        this.mHandler = new GestureHandler(this);
        goto L6
    }

    @Deprecated
    public GestureDetector2(OnGestureListener r2) {
        this(null, r2, null);
    }

    private void cancel() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(3);
        this.mVelocityTracker.recycle();
        this.mVelocityTracker = null;
        this.mIsDoubleTapping = false;
        this.mStillDown = false;
        this.mAlwaysInTapRegion = false;
        this.mAlwaysInBiggerTapRegion = false;
        this.mDeferConfirmSingleTap = false;
        this.mInLongPress = false;
        this.mInContextClick = false;
        this.mIgnoreNextUpEvent = false;
    }

    private void cancelTaps() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(3);
        this.mIsDoubleTapping = false;
        this.mAlwaysInTapRegion = false;
        this.mAlwaysInBiggerTapRegion = false;
        this.mDeferConfirmSingleTap = false;
        this.mInLongPress = false;
        this.mInContextClick = false;
        this.mIgnoreNextUpEvent = false;
    }

    private void dispatchLongPress() {
        this.mHandler.removeMessages(3);
        this.mDeferConfirmSingleTap = false;
        this.mInLongPress = true;
        this.mListener.onLongPress(this.mCurrentDownEvent);
    }

    private void init(Context r5) {
        if (this.mListener == null) goto L10;
        this.mIsLongpressEnabled = true;
        if (r5 != null) goto L7;
        int r52 = ViewConfiguration.getTouchSlop();
        this.mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
        this.mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
        int r0 = 100;
        int r1 = r52;
    L8:
        this.mTouchSlopSquare = r52 * r52;
        this.mDoubleTapTouchSlopSquare = r1 * r1;
        this.mDoubleTapSlopSquare = r0 * r0;
        return;
    L7:
        ViewConfiguration r53 = ViewConfiguration.get(r5);
        int r02 = r53.getScaledTouchSlop();
        r1 = r53.getScaledTouchSlop();
        int r2 = r53.getScaledDoubleTapSlop();
        this.mMinimumFlingVelocity = r53.getScaledMinimumFlingVelocity();
        this.mMaximumFlingVelocity = r53.getScaledMaximumFlingVelocity();
        r52 = r02;
        r0 = r2;
        goto L8
    L10:
        f$$ExternalSyntheticBUOutline2.m("OnGestureListener must not be null");
    }

    private boolean isConsideredDoubleTap(MotionEvent r7, MotionEvent r8, MotionEvent r9) {
        if (this.mAlwaysInBiggerTapRegion == true) goto L5;
        return false;
    L5:
        long r2 = r9.getEventTime() - r8.getEventTime();
        if (r2 <= DOUBLE_TAP_TIMEOUT) goto L8;
    L14:
        return false;
    L8:
        if (r2 < 40) goto L14;
        int r82 = ((int) r7.getX()) - ((int) r9.getX());
        int r72 = ((int) r7.getY()) - ((int) r9.getY());
        if (((r82 * r82) + (r72 * r72)) >= this.mDoubleTapSlopSquare) goto L14;
        return true;
    }

    public boolean onTouchEvent(MotionEvent r20) {
        int r2 = r20.getAction();
        MotionEvent r3 = this.mCurrentMotionEvent;
        if (r3 == null) goto L5;
        r3.recycle();
    L5:
        this.mCurrentMotionEvent = MotionEvent.obtain(r20);
        if (this.mVelocityTracker != null) goto L8;
        this.mVelocityTracker = VelocityTracker.obtain();
    L8:
        this.mVelocityTracker.addMovement(r20);
        int r22 = r2 & 255;
        boolean r4 = true;
        if (r22 != 6) goto L11;
        boolean r6 = true;
    L12:
        if (r6 == false) goto L14;
        int r7 = r20.getActionIndex();
    L15:
        int r8 = r20.getPointerCount();
        int r10 = 0;
        float r11 = 0.0f;
        float r12 = 0.0f;
    L16:
        if (r10 >= r8) goto L21;
        if (r7 == r10) goto L20;
        r11 = r11 + r20.getX(r10);
        r12 = r12 + r20.getY(r10);
    L20:
        r10 = r10 + 1;
        goto L16
    L21:
        if (r6 == false) goto L23;
        int r62 = r8 - 1;
    L24:
        float r63 = r62;
        float r112 = r11 / r63;
        float r122 = r12 / r63;
        if (r22 != 0) goto L27;
        this.mDeferConfirmSingleTap = false;
        OnDoubleTapListener r23 = this.mDoubleTapListener;
        if (r23 != null) goto L139;
    L153:
        boolean r24 = false;
    L154:
        this.mLastFocusX = r112;
        this.mDownFocusX = r112;
        this.mLastFocusY = r122;
        this.mDownFocusY = r122;
        MotionEvent r32 = this.mCurrentDownEvent;
        if (r32 == null) goto L157;
        r32.recycle();
    L157:
        this.mCurrentDownEvent = MotionEvent.obtain(r20);
        this.mAlwaysInTapRegion = true;
        this.mAlwaysInBiggerTapRegion = true;
        this.mStillDown = true;
        this.mInLongPress = false;
        if (this.mIsLongpressEnabled == false) goto L160;
        this.mHandler.removeMessages(2);
        Handler r33 = this.mHandler;
        r33.sendMessageDelayed(r33.obtainMessage(2, 0, 0), ViewConfiguration.getLongPressTimeout());
    L160:
        this.mHandler.sendEmptyMessageAtTime(1, this.mCurrentDownEvent.getDownTime() + TAP_TIMEOUT);
        return this.mListener.onDown(r20) | r24;
    L139:
        if (r23.canDoubleTap(r20) == false) goto L152;
        boolean r25 = this.mHandler.hasMessages(3);
        if (r25 == false) goto L143;
        this.mHandler.removeMessages(3);
    L143:
        MotionEvent r34 = this.mCurrentDownEvent;
        if (r34 == null) goto L151;
        MotionEvent r42 = this.mPreviousUpEvent;
        if (r42 == null) goto L151;
        if (r25 == false) goto L151;
        if (isConsideredDoubleTap(r34, r42, r20) == false) goto L151;
        this.mIsDoubleTapping = true;
        r24 = this.mDoubleTapListener.onDoubleTap(this.mCurrentDownEvent) | this.mDoubleTapListener.onDoubleTapEvent(r20);
    L151:
        this.mHandler.sendEmptyMessageDelayed(3, DOUBLE_TAP_TIMEOUT);
        goto L153
    L152:
        this.mDeferConfirmSingleTap = true;
        goto L153
    L27:
        if (r22 == 1) goto L100;
        if (r22 == 2) goto L48;
        if (r22 != 3) goto L31;
        cancel();
        return false;
    L31:
        if (r22 == 5) goto L43;
        if (r22 != 6) goto L99;
        this.mLastFocusX = r112;
        this.mDownFocusX = r112;
        this.mLastFocusY = r122;
        this.mDownFocusY = r122;
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
        int r26 = r20.getActionIndex();
        int r35 = r20.getPointerId(r26);
        float r43 = this.mVelocityTracker.getXVelocity(r35);
        float r36 = this.mVelocityTracker.getYVelocity(r35);
        int r64 = 0;
    L35:
        if (r64 >= r8) goto L99;
        if (r64 == r26) goto L42;
        int r72 = r20.getPointerId(r64);
        if (((this.mVelocityTracker.getXVelocity(r72) * r43) + (this.mVelocityTracker.getYVelocity(r72) * r36)) >= 0.0f) goto L42;
        this.mVelocityTracker.clear();
        return false;
    L42:
        r64 = r64 + 1;
    L99:
        return false;
    L43:
        this.mLastFocusX = r112;
        this.mDownFocusX = r112;
        this.mLastFocusY = r122;
        this.mDownFocusY = r122;
        cancelTaps();
        return false;
    L48:
        if (this.mInLongPress == true) goto L99;
        if (this.mInContextClick == true) goto L99;
        int r27 = Build.VERSION.SDK_INT;
        if (r27 < 29) goto L55;
        int r82 = r20.getClassification();
    L56:
        boolean r9 = this.mHandler.hasMessages(2);
        float r102 = this.mLastFocusX - r112;
        float r13 = this.mLastFocusY - r122;
        if (this.mIsDoubleTapping == false) goto L66;
        OnDoubleTapListener r65 = this.mDoubleTapListener;
        if (r65 != null) goto L61;
    L63:
        r4 = false;
    L64:
        int r1 = 29;
        int r17 = r82;
        boolean r18 = r9;
    L93:
        if (r27 >= r1) goto L95;
    L98:
        return r4;
    L95:
        if (r17 != 2) goto L98;
        if (r18 == false) goto L98;
        this.mHandler.removeMessages(2);
        Handler r0 = this.mHandler;
        r0.sendMessage(r0.obtainMessage(2, 0, 0));
        goto L98
    L61:
        if (r65.onDoubleTapEvent(r20) == false) goto L63;
    L66:
        if (this.mAlwaysInTapRegion == false) goto L86;
        int r14 = (int) (r112 - this.mDownFocusX);
        int r15 = (int) (r122 - this.mDownFocusY);
        int r142 = (r14 * r14) + (r15 * r15);
        int r152 = this.mTouchSlopSquare;
        if (r27 < 29) goto L71;
        if (r82 != 1) goto L71;
        boolean r16 = true;
    L72:
        if (r9 == false) goto L78;
        if (r16 == false) goto L78;
        if (r142 <= r152) goto L76;
        this.mHandler.removeMessages(2);
        r17 = r82;
        r18 = r9;
        long r83 = ViewConfiguration.getLongPressTimeout();
        Handler r37 = this.mHandler;
        r37.sendMessageDelayed(r37.obtainMessage(2, 0, 0), (long) (r83 * 2.0f));
    L77:
        r152 = (int) (r152 * 4.0f);
    L79:
        if (r142 <= r152) goto L81;
        boolean r19 = this.mListener.onScroll(this.mCurrentDownEvent, r20, r102, r13);
        this.mLastFocusX = r112;
        this.mLastFocusY = r122;
        this.mAlwaysInTapRegion = false;
        this.mHandler.removeMessages(3);
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        r4 = r19;
    L83:
        if (r142 <= this.mDoubleTapTouchSlopSquare) goto L85;
        this.mAlwaysInBiggerTapRegion = false;
    L85:
        r1 = 29;
        goto L93
    L81:
        r4 = false;
        goto L83
    L76:
        r17 = r82;
        r18 = r9;
    L78:
        r17 = r82;
        r18 = r9;
    L71:
        r16 = false;
        goto L72
    L86:
        r17 = r82;
        r18 = r9;
        if (Math.abs(r102) < 1.0f) goto L89;
    L92:
        r4 = this.mListener.onScroll(this.mCurrentDownEvent, r20, r102, r13);
        this.mLastFocusX = r112;
        this.mLastFocusY = r122;
        goto L85
    L89:
        if (Math.abs(r13) >= 1.0f) goto L92;
        r4 = false;
        goto L85
    L55:
        r82 = 0;
        goto L56
    L100:
        this.mStillDown = false;
        this.mListener.onUp(r20);
        MotionEvent r28 = MotionEvent.obtain(r20);
        if (this.mIsDoubleTapping == false) goto L108;
        OnDoubleTapListener r38 = this.mDoubleTapListener;
        if (r38 != null) goto L105;
    L127:
        boolean r110 = false;
    L128:
        MotionEvent r39 = this.mPreviousUpEvent;
        if (r39 == null) goto L131;
        r39.recycle();
    L131:
        this.mPreviousUpEvent = r28;
        VelocityTracker r29 = this.mVelocityTracker;
        if (r29 == null) goto L134;
        r29.recycle();
        this.mVelocityTracker = null;
    L134:
        this.mIsDoubleTapping = false;
        this.mDeferConfirmSingleTap = false;
        this.mIgnoreNextUpEvent = false;
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        return r110;
    L105:
        if (r38.onDoubleTapEvent(r20) == false) goto L127;
        r110 = true;
        goto L128
    L108:
        if (this.mInLongPress == false) goto L111;
        this.mHandler.removeMessages(3);
        this.mInLongPress = false;
        goto L127
    L111:
        if (this.mAlwaysInTapRegion == false) goto L121;
        if (this.mIgnoreNextUpEvent == true) goto L121;
        boolean r310 = this.mListener.onSingleTapUp(r20);
        if (this.mDeferConfirmSingleTap == false) goto L119;
        OnDoubleTapListener r44 = this.mDoubleTapListener;
        if (r44 == null) goto L119;
        r44.onSingleTapConfirmed(r20);
    L119:
        r110 = r310;
    L121:
        if (this.mIgnoreNextUpEvent == true) goto L127;
        VelocityTracker r311 = this.mVelocityTracker;
        int r45 = r20.getPointerId(0);
        r311.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
        float r66 = r311.getYVelocity(r45);
        float r312 = r311.getXVelocity(r45);
        if (Math.abs(r66) <= this.mMinimumFlingVelocity) goto L125;
    L126:
        r110 = this.mListener.onFling(this.mCurrentDownEvent, r20, r312, r66);
        goto L128
    L125:
        if (Math.abs(r312) <= this.mMinimumFlingVelocity) goto L127;
    L23:
        r62 = r8;
        goto L24
    L14:
        r7 = -1;
        goto L15
    L11:
        r6 = false;
        goto L12
    }

    public void setIsLongpressEnabled(boolean r1) {
        this.mIsLongpressEnabled = r1;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener r1) {
        this.mDoubleTapListener = r1;
    }
}
