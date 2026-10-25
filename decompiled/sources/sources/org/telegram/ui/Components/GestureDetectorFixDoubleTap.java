package org.telegram.ui.Components;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import c.f$$ExternalSyntheticBUOutline1;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class GestureDetectorFixDoubleTap {
    private final GestureDetectorCompatImpl mImpl;

    public interface GestureDetectorCompatImpl {
        boolean onTouchEvent(MotionEvent r1);

        void setIsLongpressEnabled(boolean r1);

        void setLongpressDuration(long r1);
    }

    public static class GestureDetectorCompatImplBase implements GestureDetectorCompatImpl {
        private static final int TAP_TIMEOUT = 0;
        private boolean mAlwaysInBiggerTapRegion;
        private boolean mAlwaysInTapRegion;
        MotionEvent mCurrentDownEvent;
        boolean mDeferConfirmSingleTap;
        GestureDetector.OnDoubleTapListener mDoubleTapListener;
        private int mDoubleTapSlopSquare;
        private float mDownFocusX;
        private float mDownFocusY;
        private final Handler mHandler;
        private boolean mInLongPress;
        private boolean mIsDoubleTapping;
        private boolean mIsLongpressEnabled;
        private float mLastFocusX;
        private float mLastFocusY;
        final OnGestureListener mListener;
        private long mLongpressDuration;
        private int mMaximumFlingVelocity;
        private int mMinimumFlingVelocity;
        private MotionEvent mPreviousUpEvent;
        boolean mStillDown;
        private int mTouchSlopSquare;
        private VelocityTracker mVelocityTracker;

        public class GestureHandler extends Handler {
            final /* synthetic */ GestureDetectorCompatImplBase this$0;

            public GestureHandler(GestureDetectorCompatImplBase r1) {
                this.this$0 = r1;
            }

            public GestureHandler(GestureDetectorCompatImplBase r1, Handler r2) {
                this.this$0 = r1;
                super(r2.getLooper());
            }

            @Override // android.os.Handler
            public void handleMessage(Message r4) {
                int r0 = r4.what;
                if (r0 != 1) goto L5;
                GestureDetectorCompatImplBase r3 = this.this$0;
                r3.mListener.onShowPress(r3.mCurrentDownEvent);
                return;
            L5:
                if (r0 != 2) goto L7;
                this.this$0.dispatchLongPress();
                return;
            L7:
                if (r0 != 3) goto L16;
                GestureDetectorCompatImplBase r32 = this.this$0;
                GestureDetector.OnDoubleTapListener r42 = r32.mDoubleTapListener;
                if (r42 != null) goto L11;
                return;
            L11:
                if (r32.mStillDown == true) goto L14;
                r42.onSingleTapConfirmed(r32.mCurrentDownEvent);
                return;
            L14:
                r32.mDeferConfirmSingleTap = true;
                return;
            L16:
                MVEL$$ExternalSyntheticBUOutline0.m("Unknown message ", r4);
            }
        }

        static {
            TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
        }

        public GestureDetectorCompatImplBase(Context r3, OnGestureListener r4, Handler r5) {
            this.mLongpressDuration = ViewConfiguration.getLongPressTimeout();
            if (r5 == null) goto L5;
            this.mHandler = new GestureHandler(this, r5);
        L6:
            this.mListener = r4;
            if (r4 == null) goto L9;
            setOnDoubleTapListener(r4);
        L9:
            init(r3);
            return;
        L5:
            this.mHandler = new GestureHandler(this);
            goto L6
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
            if (this.mInLongPress == false) goto L6;
            this.mInLongPress = false;
            return;
        }

        private void cancelTaps() {
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(3);
            this.mIsDoubleTapping = false;
            this.mAlwaysInTapRegion = false;
            this.mAlwaysInBiggerTapRegion = false;
            this.mDeferConfirmSingleTap = false;
            if (this.mInLongPress == false) goto L6;
            this.mInLongPress = false;
            return;
        }

        private void init(Context r4) {
            if (r4 != null) goto L4;
            f$$ExternalSyntheticBUOutline1.m("Context must not be null");
            return;
        L4:
            if (this.mListener == null) goto L7;
            this.mIsLongpressEnabled = true;
            ViewConfiguration r42 = ViewConfiguration.get(r4);
            int r0 = r42.getScaledTouchSlop();
            int r1 = r42.getScaledDoubleTapSlop();
            this.mMinimumFlingVelocity = r42.getScaledMinimumFlingVelocity();
            this.mMaximumFlingVelocity = r42.getScaledMaximumFlingVelocity();
            this.mTouchSlopSquare = r0 * r0;
            this.mDoubleTapSlopSquare = r1 * r1;
            return;
        L7:
            f$$ExternalSyntheticBUOutline1.m("OnGestureListener must not be null");
        }

        private boolean isConsideredDoubleTap(MotionEvent r7, MotionEvent r8, MotionEvent r9) {
            if (this.mAlwaysInBiggerTapRegion == true) goto L6;
            return false;
        L6:
            if ((r9.getEventTime() - r8.getEventTime()) <= 220) goto L8;
            return false;
        L8:
            int r82 = ((int) r7.getX()) - ((int) r9.getX());
            int r72 = ((int) r7.getY()) - ((int) r9.getY());
            if (((r82 * r82) + (r72 * r72)) >= this.mDoubleTapSlopSquare) goto L12;
            return true;
        L12:
            return false;
        }

        public void dispatchLongPress() {
            this.mHandler.removeMessages(3);
            this.mDeferConfirmSingleTap = false;
            this.mInLongPress = true;
            this.mListener.onLongPress(this.mCurrentDownEvent);
        }

        @Override // org.telegram.ui.Components.GestureDetectorFixDoubleTap.GestureDetectorCompatImpl
        public boolean onTouchEvent(MotionEvent r13) {
            int r0 = r13.getAction();
            if (this.mVelocityTracker != null) goto L5;
            this.mVelocityTracker = VelocityTracker.obtain();
        L5:
            this.mVelocityTracker.addMovement(r13);
            int r02 = r0 & 255;
            if (r02 != 6) goto L8;
            boolean r4 = true;
        L9:
            if (r4 == false) goto L11;
            int r5 = r13.getActionIndex();
        L12:
            int r6 = r13.getPointerCount();
            int r8 = 0;
            float r9 = 0.0f;
            float r10 = 0.0f;
        L13:
            if (r8 >= r6) goto L18;
            if (r5 == r8) goto L17;
            r9 = r9 + r13.getX(r8);
            r10 = r10 + r13.getY(r8);
        L17:
            r8 = r8 + 1;
            goto L13
        L18:
            if (r4 == false) goto L20;
            int r42 = r6 - 1;
        L21:
            float r43 = r42;
            float r92 = r9 / r43;
            float r102 = r10 / r43;
            if (r02 == 0) goto L99;
            if (r02 == 1) goto L69;
            if (r02 == 2) goto L45;
            if (r02 != 3) goto L28;
            cancel();
            return false;
        L28:
            if (r02 == 5) goto L40;
            if (r02 != 6) goto L66;
            this.mLastFocusX = r92;
            this.mDownFocusX = r92;
            this.mLastFocusY = r102;
            this.mDownFocusY = r102;
            this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
            int r03 = r13.getActionIndex();
            int r1 = r13.getPointerId(r03);
            float r2 = this.mVelocityTracker.getXVelocity(r1);
            float r12 = this.mVelocityTracker.getYVelocity(r1);
            int r44 = 0;
        L32:
            if (r44 >= r6) goto L66;
            if (r44 == r03) goto L39;
            int r52 = r13.getPointerId(r44);
            if (((this.mVelocityTracker.getXVelocity(r52) * r2) + (this.mVelocityTracker.getYVelocity(r52) * r12)) >= 0.0f) goto L39;
            this.mVelocityTracker.clear();
            return false;
        L39:
            r44 = r44 + 1;
        L66:
            return false;
        L40:
            this.mLastFocusX = r92;
            this.mDownFocusX = r92;
            this.mLastFocusY = r102;
            this.mDownFocusY = r102;
            cancelTaps();
            return false;
        L45:
            if (this.mInLongPress == true) goto L66;
            float r04 = this.mLastFocusX - r92;
            float r14 = this.mLastFocusY - r102;
            if (this.mIsDoubleTapping == false) goto L52;
            return this.mDoubleTapListener.onDoubleTapEvent(r13);
        L52:
            if (this.mAlwaysInTapRegion == false) goto L62;
            int r62 = (int) (r92 - this.mDownFocusX);
            int r7 = (int) (r102 - this.mDownFocusY);
            int r63 = (r62 * r62) + (r7 * r7);
            if (r63 <= this.mTouchSlopSquare) goto L56;
            boolean r132 = this.mListener.onScroll(this.mCurrentDownEvent, r13, r04, r14);
            this.mLastFocusX = r92;
            this.mLastFocusY = r102;
            this.mAlwaysInTapRegion = false;
            this.mHandler.removeMessages(3);
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
        L58:
            if (r63 <= this.mTouchSlopSquare) goto L60;
            this.mAlwaysInBiggerTapRegion = false;
        L60:
            return r132;
        L56:
            r132 = false;
            goto L58
        L62:
            if (Math.abs(r04) < 1.0f) goto L64;
        L67:
            boolean r133 = this.mListener.onScroll(this.mCurrentDownEvent, r13, r04, r14);
            this.mLastFocusX = r92;
            this.mLastFocusY = r102;
            return r133;
        L64:
            if (Math.abs(r14) < 1.0f) goto L66;
        L69:
            this.mStillDown = false;
            MotionEvent r05 = MotionEvent.obtain(r13);
            if (this.mIsDoubleTapping == false) goto L73;
            boolean r134 = this.mDoubleTapListener.onDoubleTapEvent(r13);
        L90:
            MotionEvent r15 = this.mPreviousUpEvent;
            if (r15 == null) goto L93;
            r15.recycle();
        L93:
            this.mPreviousUpEvent = r05;
            VelocityTracker r06 = this.mVelocityTracker;
            if (r06 == null) goto L96;
            r06.recycle();
            this.mVelocityTracker = null;
        L96:
            this.mIsDoubleTapping = false;
            this.mDeferConfirmSingleTap = false;
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            return r134;
        L73:
            if (this.mInLongPress == false) goto L76;
            this.mHandler.removeMessages(3);
            this.mInLongPress = false;
        L88:
            r134 = false;
            goto L90
        L76:
            if (this.mAlwaysInTapRegion == false) goto L83;
            boolean r16 = this.mListener.onSingleTapUp(r13);
            if (this.mDeferConfirmSingleTap == false) goto L82;
            GestureDetector.OnDoubleTapListener r53 = this.mDoubleTapListener;
            if (r53 == null) goto L82;
            r53.onSingleTapConfirmed(r13);
        L82:
            r134 = r16;
            goto L90
        L83:
            VelocityTracker r17 = this.mVelocityTracker;
            int r54 = r13.getPointerId(0);
            r17.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
            float r64 = r17.getYVelocity(r54);
            float r18 = r17.getXVelocity(r54);
            if (Math.abs(r64) <= this.mMinimumFlingVelocity) goto L86;
        L89:
            r134 = this.mListener.onFling(this.mCurrentDownEvent, r13, r18, r64);
            goto L90
        L86:
            if (Math.abs(r18) <= this.mMinimumFlingVelocity) goto L88;
        L99:
            if (this.mDoubleTapListener != null) goto L101;
        L114:
            boolean r07 = false;
        L115:
            this.mLastFocusX = r92;
            this.mDownFocusX = r92;
            this.mLastFocusY = r102;
            this.mDownFocusY = r102;
            MotionEvent r19 = this.mCurrentDownEvent;
            if (r19 == null) goto L118;
            r19.recycle();
        L118:
            this.mCurrentDownEvent = MotionEvent.obtain(r13);
            this.mAlwaysInTapRegion = true;
            this.mAlwaysInBiggerTapRegion = true;
            this.mStillDown = true;
            this.mInLongPress = false;
            this.mDeferConfirmSingleTap = false;
            if (this.mIsLongpressEnabled == false) goto L121;
            this.mHandler.removeMessages(2);
            this.mHandler.sendEmptyMessageAtTime(2, (this.mCurrentDownEvent.getDownTime() + TAP_TIMEOUT) + this.mLongpressDuration);
        L121:
            this.mHandler.sendEmptyMessageAtTime(1, this.mCurrentDownEvent.getDownTime() + TAP_TIMEOUT);
            return this.mListener.onDown(r13) | r07;
        L101:
            if (this.mListener.hasDoubleTap(r13) == false) goto L114;
            boolean r08 = this.mHandler.hasMessages(3);
            if (r08 == false) goto L105;
            this.mHandler.removeMessages(3);
        L105:
            MotionEvent r110 = this.mCurrentDownEvent;
            if (r110 == null) goto L113;
            MotionEvent r65 = this.mPreviousUpEvent;
            if (r65 == null) goto L113;
            if (r08 == false) goto L113;
            if (isConsideredDoubleTap(r110, r65, r13) == false) goto L113;
            this.mIsDoubleTapping = true;
            r07 = this.mDoubleTapListener.onDoubleTap(this.mCurrentDownEvent) | this.mDoubleTapListener.onDoubleTapEvent(r13);
        L113:
            this.mHandler.sendEmptyMessageDelayed(3, 220);
            goto L114
        L20:
            r42 = r6;
            goto L21
        L11:
            r5 = -1;
            goto L12
        L8:
            r4 = false;
            goto L9
        }

        @Override // org.telegram.ui.Components.GestureDetectorFixDoubleTap.GestureDetectorCompatImpl
        public void setIsLongpressEnabled(boolean r1) {
            this.mIsLongpressEnabled = r1;
        }

        @Override // org.telegram.ui.Components.GestureDetectorFixDoubleTap.GestureDetectorCompatImpl
        public void setLongpressDuration(long r1) {
            this.mLongpressDuration = r1;
        }

        public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener r1) {
            this.mDoubleTapListener = r1;
        }
    }

    public static class OnGestureListener extends GestureDetector.SimpleOnGestureListener {
        public OnGestureListener() {
        }

        public abstract boolean hasDoubleTap(MotionEvent r1);
    }

    public GestureDetectorFixDoubleTap(Context r2, OnGestureListener r3) {
        this(r2, r3, null);
    }

    public GestureDetectorFixDoubleTap(Context r2, OnGestureListener r3, Handler r4) {
        this.mImpl = new GestureDetectorCompatImplBase(r2, r3, r4);
    }

    public boolean onTouchEvent(MotionEvent r1) {
        return this.mImpl.onTouchEvent(r1);
    }

    public void setIsLongpressEnabled(boolean r1) {
        this.mImpl.setIsLongpressEnabled(r1);
    }

    public void setLongpressDuration(long r1) {
        this.mImpl.setLongpressDuration(r1);
    }
}
