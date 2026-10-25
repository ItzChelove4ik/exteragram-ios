package org.telegram.ui.Components.Crop;

import android.content.Context;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class CropGestureDetector {
    private int mActivePointerId;
    private int mActivePointerIndex;
    private ScaleGestureDetector mDetector;
    private boolean mIsDragging;
    private float mLastTouchX;
    private float mLastTouchY;
    private CropGestureListener mListener;
    private final float mMinimumVelocity;
    private final float mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private boolean started;
    private long touchTime;

    /* renamed from: org.telegram.ui.Components.Crop.CropGestureDetector$1, reason: invalid class name */
    public class AnonymousClass1 implements ScaleGestureDetector.OnScaleGestureListener {
        final /* synthetic */ CropGestureDetector this$0;

        public AnonymousClass1(CropGestureDetector r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector r3) {
            float r0 = r3.getScaleFactor();
            if (Float.isNaN(r0) == false) goto L5;
            return false;
        L5:
            if (Float.isInfinite(r0) == true) goto L11;
            CropGestureDetector.m10068$$Nest$fgetmListener(this.this$0).onScale(r0, r3.getFocusX(), r3.getFocusY());
            return true;
        L11:
            return false;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector r1) {
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector r1) {
        }
    }

    public interface CropGestureListener {
        void onDrag(float r1, float r2);

        void onFling(float r1, float r2, float r3, float r4);

        void onScale(float r1, float r2, float r3);

        void onTapUp();
    }

    /* renamed from: -$$Nest$fgetmListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CropGestureListener m10068$$Nest$fgetmListener(CropGestureDetector r0) {
        return r0.mListener;
    }

    public CropGestureDetector(Context r3) {
        this.mMinimumVelocity = ViewConfiguration.get(r3).getScaledMinimumFlingVelocity();
        this.mTouchSlop = AndroidUtilities.dp(1.0f);
        this.mActivePointerId = -1;
        this.mActivePointerIndex = 0;
        this.mDetector = new ScaleGestureDetector(r3, new AnonymousClass1(this));
    }

    public float getActiveX(MotionEvent r1) {
        return r1.getX(this.mActivePointerIndex);
    L5:
        return r1.getX();
    }

    public float getActiveY(MotionEvent r1) {
        return r1.getY(this.mActivePointerIndex);
    L5:
        return r1.getY();
    }

    public boolean isDragging() {
        return this.mIsDragging;
    }

    public boolean isScaling() {
        return this.mDetector.isInProgress();
    }

    public boolean onTouchEvent(MotionEvent r10) {
        this.mDetector.onTouchEvent(r10);
        int r0 = r10.getAction() & 255;
        boolean r4 = false;
        if (r0 == 0) goto L21;
        if (r0 == 1) goto L16;
        if (r0 == 3) goto L16;
        if (r0 != 6) goto L22;
        int r02 = (65280 & r10.getAction()) >> 8;
        if (r10.getPointerId(r02) != this.mActivePointerId) goto L22;
        if (r02 != 0) goto L13;
        int r03 = 1;
    L14:
        this.mActivePointerId = r10.getPointerId(r03);
        this.mLastTouchX = r10.getX(r03);
        this.mLastTouchY = r10.getY(r03);
        goto L22
    L13:
        r03 = 0;
    L22:
        int r04 = this.mActivePointerId;
        if (r04 != (-1)) goto L26;
        r04 = 0;
    L26:
        this.mActivePointerIndex = r10.findPointerIndex(r04);
        int r05 = r10.getAction();
        if (r05 == 0) goto L51;
        if (r05 == 1) goto L39;
        if (r05 == 2) goto L51;
        if (r05 != 3) goto L68;
        VelocityTracker r102 = this.mVelocityTracker;
        if (r102 == null) goto L37;
        r102.recycle();
        this.mVelocityTracker = null;
    L37:
        this.started = false;
        this.mIsDragging = false;
    L68:
        return true;
    L39:
        if (this.mIsDragging == true) goto L41;
    L46:
        VelocityTracker r103 = this.mVelocityTracker;
        if (r103 == null) goto L49;
        r103.recycle();
        this.mVelocityTracker = null;
    L49:
        this.started = false;
        goto L68
    L41:
        if (this.mVelocityTracker == null) goto L45;
        this.mLastTouchX = getActiveX(r10);
        this.mLastTouchY = getActiveY(r10);
        this.mVelocityTracker.addMovement(r10);
        this.mVelocityTracker.computeCurrentVelocity(1000);
        float r104 = this.mVelocityTracker.getXVelocity();
        float r06 = this.mVelocityTracker.getYVelocity();
        if (Math.max(Math.abs(r104), Math.abs(r06)) < this.mMinimumVelocity) goto L45;
        this.mListener.onFling(this.mLastTouchX, this.mLastTouchY, -r104, -r06);
    L45:
        this.mIsDragging = false;
    L51:
        if (this.started == true) goto L57;
        VelocityTracker r07 = VelocityTracker.obtain();
        this.mVelocityTracker = r07;
        if (r07 == null) goto L55;
        r07.addMovement(r10);
    L55:
        this.mLastTouchX = getActiveX(r10);
        this.mLastTouchY = getActiveY(r10);
        this.mIsDragging = false;
        this.started = true;
        return true;
    L57:
        float r08 = getActiveX(r10);
        float r1 = getActiveY(r10);
        float r2 = r08 - this.mLastTouchX;
        float r5 = r1 - this.mLastTouchY;
        if (this.mIsDragging == true) goto L64;
        if (((float) Math.sqrt((r2 * r2) + (r5 * r5))) < this.mTouchSlop) goto L62;
        r4 = true;
    L62:
        this.mIsDragging = r4;
    L64:
        if (this.mIsDragging == false) goto L68;
        this.mListener.onDrag(r2, r5);
        this.mLastTouchX = r08;
        this.mLastTouchY = r1;
        VelocityTracker r9 = this.mVelocityTracker;
        if (r9 == null) goto L68;
        r9.addMovement(r10);
    L16:
        if (this.mIsDragging == false) goto L18;
    L20:
        this.mActivePointerId = -1;
        goto L22
    L18:
        if ((SystemClock.elapsedRealtime() - this.touchTime) >= 800) goto L20;
        this.mListener.onTapUp();
        goto L20
    L21:
        this.mActivePointerId = r10.getPointerId(0);
        this.touchTime = SystemClock.elapsedRealtime();
        goto L22
    }

    public void setOnGestureListener(CropGestureListener r1) {
        this.mListener = r1;
    }
}
