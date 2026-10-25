package androidx.customview.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.gms.cast.MediaError;
import com.sun.jna.Native$$ExternalSyntheticBUOutline2;
import java.util.Arrays;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class ViewDragHelper {
    private static final Interpolator sInterpolator = null;
    private int mActivePointerId;
    private final Callback mCallback;
    private View mCapturedView;
    private final int mDefaultEdgeSize;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private Interpolator mInterpolator;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private final float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private final OverScroller mScroller;
    private final Runnable mSetIdleRunnable;
    private int mTouchSlop;
    private int mTrackingEdges;
    private VelocityTracker mVelocityTracker;

    /* renamed from: androidx.customview.widget.ViewDragHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements Interpolator {
        public AnonymousClass1() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float r2) {
            float r22 = r2 - 1.0f;
            return ((((r22 * r22) * r22) * r22) * r22) + 1.0f;
        }
    }

    /* renamed from: androidx.customview.widget.ViewDragHelper$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ ViewDragHelper this$0;

        public AnonymousClass2(ViewDragHelper r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.setDragState(0);
        }
    }

    /* renamed from: androidx.customview.widget.ViewDragHelper$3, reason: invalid class name */
    public class AnonymousClass3 implements Interpolator {
        final /* synthetic */ ViewDragHelper this$0;

        public AnonymousClass3(ViewDragHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float r1) {
            return ViewDragHelper.access$000(this.this$0).getInterpolation(r1);
        }
    }

    public static abstract class Callback {
        public Callback() {
        }

        public abstract int clampViewPositionHorizontal(View r1, int r2, int r3);

        public int clampViewPositionVertical(View r1, int r2, int r3) {
            return 0;
        }

        public int getOrderedChildIndex(int r1) {
            return r1;
        }

        public int getViewHorizontalDragRange(View r1) {
            return 0;
        }

        public int getViewVerticalDragRange(View r1) {
            return 0;
        }

        public void onEdgeDragStarted(int r1, int r2) {
        }

        public boolean onEdgeLock(int r1) {
            return false;
        }

        public void onEdgeTouched(int r1, int r2) {
        }

        public void onViewCaptured(View r1, int r2) {
        }

        public void onViewDragStateChanged(int r1) {
        }

        public void onViewPositionChanged(View r1, int r2, int r3, int r4, int r5) {
        }

        public abstract void onViewReleased(View r1, float r2, float r3);

        public abstract boolean tryCaptureView(View r1, int r2);
    }

    static {
        sInterpolator = new AnonymousClass1();
    }

    private ViewDragHelper(Context r2, ViewGroup r3, Callback r4) {
        this.mActivePointerId = -1;
        this.mSetIdleRunnable = new AnonymousClass2(this);
        requireNonNull(r3, "Parent view may not be null");
        requireNonNull(r4, "Callback may not be null");
        this.mParentView = r3;
        this.mCallback = r4;
        ViewConfiguration r32 = ViewConfiguration.get(r2);
        int r42 = (int) ((r2.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.mDefaultEdgeSize = r42;
        this.mEdgeSize = r42;
        this.mTouchSlop = r32.getScaledTouchSlop();
        this.mMaxVelocity = r32.getScaledMaximumFlingVelocity();
        this.mMinVelocity = r32.getScaledMinimumFlingVelocity();
        this.mInterpolator = sInterpolator;
        this.mScroller = new OverScroller(r2, new AnonymousClass3(this));
    }

    public static /* synthetic */ Interpolator access$000(ViewDragHelper r0) {
        return r0.mInterpolator;
    }

    private boolean checkNewEdgeDrag(float r4, float r5, int r6, int r7) {
        float r42 = Math.abs(r4);
        float r52 = Math.abs(r5);
        if ((this.mInitialEdgesTouched[r6] & r7) == r7) goto L5;
    L27:
        return false;
    L5:
        if ((this.mTrackingEdges & r7) == 0) goto L27;
        if ((this.mEdgeDragsLocked[r6] & r7) == r7) goto L27;
        if ((this.mEdgeDragsInProgress[r6] & r7) == r7) goto L27;
        int r0 = this.mTouchSlop;
        if (r42 > r0) goto L16;
        if (r52 <= r0) goto L27;
    L16:
        if (r42 >= (r52 * 0.5f)) goto L22;
        if (this.mCallback.onEdgeLock(r7) == false) goto L22;
        int[] r3 = this.mEdgeDragsLocked;
        r3[r6] = r3[r6] | r7;
        return false;
    L22:
        if ((this.mEdgeDragsInProgress[r6] & r7) != 0) goto L27;
        if (r42 <= this.mTouchSlop) goto L27;
        return true;
    }

    private boolean checkTouchSlop(View r5, float r6, float r7) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if (this.mCallback.getViewHorizontalDragRange(r5) <= 0) goto L8;
        boolean r1 = true;
    L10:
        if (this.mCallback.getViewVerticalDragRange(r5) <= 0) goto L12;
        boolean r52 = true;
    L13:
        if (r1 == false) goto L19;
        if (r52 == false) goto L19;
        float r62 = (r6 * r6) + (r7 * r7);
        int r4 = this.mTouchSlop;
        if (r62 <= (r4 * r4)) goto L18;
        return true;
    L18:
        return false;
    L19:
        if (r1 == true) goto L21;
        if (r52 == true) goto L26;
    L28:
        return false;
    L26:
        if (Math.abs(r7) <= this.mTouchSlop) goto L28;
        return true;
    L21:
        if (Math.abs(r6) <= this.mTouchSlop) goto L23;
        return true;
    L23:
        return false;
    L12:
        r52 = false;
        goto L13
    L8:
        r1 = false;
        goto L10
    }

    private float clampMag(float r2, float r3, float r4) {
        float r1 = Math.abs(r2);
        if (r1 >= r3) goto L6;
        return 0.0f;
    L6:
        if (r1 > r4) goto L8;
        return r2;
    L8:
        if (r2 <= 0.0f) goto L11;
        return r4;
    L11:
        return -r4;
    }

    private int clampMag(int r1, int r2, int r3) {
        int r0 = Math.abs(r1);
        if (r0 >= r2) goto L6;
        return 0;
    L6:
        if (r0 <= r3) goto L11;
        if (r1 <= 0) goto L10;
        return r3;
    L10:
        return -r3;
    L11:
        return r1;
    }

    private void clearMotionHistory() {
        float[] r0 = this.mInitialMotionX;
        if (r0 != null) goto L5;
        return;
    L5:
        Arrays.fill(r0, 0.0f);
        Arrays.fill(this.mInitialMotionY, 0.0f);
        Arrays.fill(this.mLastMotionX, 0.0f);
        Arrays.fill(this.mLastMotionY, 0.0f);
        Arrays.fill(this.mInitialEdgesTouched, 0);
        Arrays.fill(this.mEdgeDragsInProgress, 0);
        Arrays.fill(this.mEdgeDragsLocked, 0);
        this.mPointersDown = 0;
    }

    private void clearMotionHistory(int r3) {
        if (this.mInitialMotionX != null) goto L5;
        return;
    L5:
        if (isPointerDown(r3) == false) goto L10;
        this.mInitialMotionX[r3] = 0.0f;
        this.mInitialMotionY[r3] = 0.0f;
        this.mLastMotionX[r3] = 0.0f;
        this.mLastMotionY[r3] = 0.0f;
        this.mInitialEdgesTouched[r3] = 0;
        this.mEdgeDragsInProgress[r3] = 0;
        this.mEdgeDragsLocked[r3] = 0;
        int r0 = this.mPointersDown;
        this.mPointersDown = (~(1 << r3)) & r0;
        return;
    }

    private int computeAxisDuration(int r4, int r5, int r6) {
        if (r4 != 0) goto L5;
        return 0;
    L5:
        int r0 = this.mParentView.getWidth();
        float r1 = r0 / 2;
        float r12 = r1 + (distanceInfluenceForSnapDuration(Math.min(1.0f, Math.abs(r4) / r0)) * r1);
        int r3 = Math.abs(r5);
        if (r3 <= 0) goto L8;
        int r32 = Math.round(Math.abs(r12 / r3) * 1000.0f) * 4;
    L10:
        return Math.min(r32, MediaError.DetailedErrorCode.TEXT_UNKNOWN);
    L8:
        r32 = (int) (((Math.abs(r4) / r6) + 1.0f) * 256.0f);
        goto L10
    }

    private int computeSettleDuration(View r7, int r8, int r9, int r10, int r11) {
        int r102 = clampMag(r10, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int r112 = clampMag(r11, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int r0 = Math.abs(r8);
        int r1 = Math.abs(r9);
        int r2 = Math.abs(r102);
        int r3 = Math.abs(r112);
        int r4 = r2 + r3;
        int r5 = r0 + r1;
        if (r102 == 0) goto L6;
        float r02 = r2;
        float r22 = r4;
    L5:
        float r03 = r02 / r22;
        if (r112 == 0) goto L10;
        float r12 = r3;
        float r23 = r4;
    L9:
        float r13 = r12 / r23;
        return (int) ((computeAxisDuration(r8, r102, this.mCallback.getViewHorizontalDragRange(r7)) * r03) + (computeAxisDuration(r9, r112, this.mCallback.getViewVerticalDragRange(r7)) * r13));
    L10:
        r12 = r1;
        r23 = r5;
        goto L9
    L6:
        r02 = r0;
        r22 = r5;
        goto L5
    }

    public static ViewDragHelper create(ViewGroup r1, float r2, Callback r3) {
        ViewDragHelper r12 = create(r1, r3);
        r12.mTouchSlop = (int) (r12.mTouchSlop * (1.0f / r2));
        return r12;
    }

    public static ViewDragHelper create(ViewGroup r2, Callback r3) {
        return new ViewDragHelper(r2.getContext(), r2, r3);
    }

    private void dispatchViewReleased(float r4, float r5) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, r4, r5);
        this.mReleaseInProgress = false;
        if (this.mDragState != 1) goto L6;
        setDragState(0);
        return;
    }

    private float distanceInfluenceForSnapDuration(float r1) {
        return (float) Math.sin((r1 - 0.5f) * 0.47123894f);
    }

    private void dragTo(int r11, int r12, int r13, int r14) {
        int r0 = this.mCapturedView.getLeft();
        int r1 = this.mCapturedView.getTop();
        if (r13 == 0) goto L5;
        r11 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, r11, r13);
        ViewCompat.offsetLeftAndRight(this.mCapturedView, r11 - r0);
    L5:
        int r6 = r11;
        if (r14 == 0) goto L8;
        r12 = this.mCallback.clampViewPositionVertical(this.mCapturedView, r12, r14);
        ViewCompat.offsetTopAndBottom(this.mCapturedView, r12 - r1);
    L8:
        int r7 = r12;
        if (r13 != 0) goto L13;
        if (r14 != 0) goto L13;
        return;
    L13:
        Callback r4 = this.mCallback;
        View r5 = this.mCapturedView;
        r4.onViewPositionChanged(r5, r6, r7, r6 - r0, r7 - r1);
    }

    private void ensureMotionHistorySizeForId(int r10) {
        float[] r0 = this.mInitialMotionX;
        if (r0 != null) goto L5;
    L8:
        int r102 = r10 + 1;
        float[] r1 = new float[r102];
        float[] r2 = new float[r102];
        float[] r3 = new float[r102];
        float[] r4 = new float[r102];
        int[] r5 = new int[r102];
        int[] r6 = new int[r102];
        int[] r103 = new int[r102];
        if (r0 == null) goto L11;
        System.arraycopy(r0, 0, r1, 0, r0.length);
        float[] r02 = this.mInitialMotionY;
        System.arraycopy(r02, 0, r2, 0, r02.length);
        float[] r03 = this.mLastMotionX;
        System.arraycopy(r03, 0, r3, 0, r03.length);
        float[] r04 = this.mLastMotionY;
        System.arraycopy(r04, 0, r4, 0, r04.length);
        int[] r05 = this.mInitialEdgesTouched;
        System.arraycopy(r05, 0, r5, 0, r05.length);
        int[] r06 = this.mEdgeDragsInProgress;
        System.arraycopy(r06, 0, r6, 0, r06.length);
        int[] r07 = this.mEdgeDragsLocked;
        System.arraycopy(r07, 0, r103, 0, r07.length);
    L11:
        this.mInitialMotionX = r1;
        this.mInitialMotionY = r2;
        this.mLastMotionX = r3;
        this.mLastMotionY = r4;
        this.mInitialEdgesTouched = r5;
        this.mEdgeDragsInProgress = r6;
        this.mEdgeDragsLocked = r103;
        return;
    L5:
        if (r0.length <= r10) goto L8;
    }

    private boolean forceSettleCapturedViewAt(int r11, int r12, int r13, int r14) {
        int r2 = this.mCapturedView.getLeft();
        int r3 = this.mCapturedView.getTop();
        int r4 = r11 - r2;
        int r5 = r12 - r3;
        if (r4 != 0) goto L7;
        if (r5 != 0) goto L7;
        this.mScroller.abortAnimation();
        setDragState(0);
        return false;
    L7:
        int r10 = computeSettleDuration(this.mCapturedView, r4, r5, r13, r14);
        this.mInterpolator = sInterpolator;
        this.mScroller.startScroll(r2, r3, r4, r5, r10);
        setDragState(2);
        return true;
    }

    private boolean forceSettleCapturedViewAt(int r8, int r9, int r10, Interpolator r11) {
        int r2 = this.mCapturedView.getLeft();
        int r3 = this.mCapturedView.getTop();
        int r4 = r8 - r2;
        int r5 = r9 - r3;
        if (r4 != 0) goto L7;
        if (r5 != 0) goto L7;
        this.mScroller.abortAnimation();
        setDragState(0);
        return false;
    L7:
        if (r11 == null) goto L9;
        this.mInterpolator = r11;
    L10:
        this.mScroller.startScroll(r2, r3, r4, r5, r10);
        setDragState(2);
        return true;
    L9:
        this.mInterpolator = sInterpolator;
        goto L10
    }

    private int getEdgesTouched(int r4, int r5) {
        if (r4 >= (this.mParentView.getLeft() + this.mEdgeSize)) goto L5;
        int r0 = 1;
    L7:
        if (r5 >= (this.mParentView.getTop() + this.mEdgeSize)) goto L10;
        r0 = r0 | 4;
    L10:
        if (r4 <= (this.mParentView.getRight() - this.mEdgeSize)) goto L13;
        r0 = r0 | 2;
    L13:
        if (r5 > (this.mParentView.getBottom() - this.mEdgeSize)) goto L15;
        return r0;
    L15:
        return r0 | 8;
    L5:
        r0 = 0;
        goto L7
    }

    private boolean isValidPointerForActionMove(int r1) {
        if (isPointerDown(r1) == true) goto L6;
        return false;
    L6:
        return true;
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.customview.widget.ViewDragHelper$Callback] */
    private void reportNewEdgeDrags(float r3, float r4, int r5) {
        boolean r0 = checkNewEdgeDrag(r3, r4, r5, 1);
        boolean r02 = r0;
        if (checkNewEdgeDrag(r4, r3, r5, 4) == false) goto L5;
        r02 = (r0 ? 1 : 0) | 4;
    L5:
        boolean r03 = r02;
        if (checkNewEdgeDrag(r3, r4, r5, 2) == false) goto L8;
        r03 = (r02 ? 1 : 0) | 2;
    L8:
        ?? r04 = r03;
        if (checkNewEdgeDrag(r4, r3, r5, 8) == false) goto L11;
        r04 = (r03 ? 1 : 0) | 8;
    L11:
        if (r04 == 0) goto L14;
        int[] r32 = this.mEdgeDragsInProgress;
        r32[r5] = r32[r5] | r04;
        this.mCallback.onEdgeDragStarted(r04, r5);
        return;
    }

    private static <T> T requireNonNull(T r0, String r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(r1);
        return null;
    }

    private void saveInitialMotion(float r3, float r4, int r5) {
        ensureMotionHistorySizeForId(r5);
        float[] r0 = this.mInitialMotionX;
        this.mLastMotionX[r5] = r3;
        r0[r5] = r3;
        float[] r02 = this.mInitialMotionY;
        this.mLastMotionY[r5] = r4;
        r02[r5] = r4;
        this.mInitialEdgesTouched[r5] = getEdgesTouched((int) r3, (int) r4);
        this.mPointersDown |= 1 << r5;
    }

    private void saveLastMotion(MotionEvent r7) {
        int r0 = r7.getPointerCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        int r2 = r7.getPointerId(r1);
        if (isValidPointerForActionMove(r2) == false) goto L8;
        float r3 = r7.getX(r1);
        float r4 = r7.getY(r1);
        this.mLastMotionX[r2] = r3;
        this.mLastMotionY[r2] = r4;
    L8:
        r1 = r1 + 1;
        goto L3
    }

    public void abort() {
        cancel();
        if (this.mDragState != 2) goto L5;
        int r0 = this.mScroller.getCurrX();
        int r1 = this.mScroller.getCurrY();
        this.mScroller.abortAnimation();
        int r5 = this.mScroller.getCurrX();
        int r6 = this.mScroller.getCurrY();
        this.mCallback.onViewPositionChanged(this.mCapturedView, r5, r6, r5 - r0, r6 - r1);
    L5:
        this.mInterpolator = sInterpolator;
        setDragState(0);
    }

    public void cancel() {
        this.mActivePointerId = -1;
        clearMotionHistory();
        VelocityTracker r0 = this.mVelocityTracker;
        if (r0 == null) goto L6;
        r0.recycle();
        this.mVelocityTracker = null;
        return;
    }

    public void captureChildView(View r3, int r4) {
        if (r3.getParent() != this.mParentView) goto L6;
        this.mCapturedView = r3;
        this.mActivePointerId = r4;
        this.mCallback.onViewCaptured(r3, r4);
        setDragState(1);
        return;
    L6:
        Native$$ExternalSyntheticBUOutline2.m("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (", this.mParentView, ")");
    }

    public boolean continueSettling(boolean r11) {
        if (this.mDragState != 2) goto L23;
        boolean r0 = this.mScroller.computeScrollOffset();
        int r6 = this.mScroller.getCurrX();
        int r7 = this.mScroller.getCurrY();
        int r8 = r6 - this.mCapturedView.getLeft();
        int r9 = r7 - this.mCapturedView.getTop();
        if (r8 == 0) goto L7;
        ViewCompat.offsetLeftAndRight(this.mCapturedView, r8);
    L7:
        if (r9 == 0) goto L9;
        ViewCompat.offsetTopAndBottom(this.mCapturedView, r9);
    L9:
        if (r8 != 0) goto L11;
        if (r9 != 0) goto L11;
    L12:
        if (r0 == true) goto L14;
    L18:
        if (r0 == true) goto L23;
        if (r11 == false) goto L21;
        this.mParentView.post(this.mSetIdleRunnable);
        goto L23
    L21:
        setDragState(0);
        goto L23
    L14:
        if (r6 != this.mScroller.getFinalX()) goto L18;
        if (r7 != this.mScroller.getFinalY()) goto L18;
        this.mScroller.abortAnimation();
        r0 = false;
    L11:
        this.mCallback.onViewPositionChanged(this.mCapturedView, r6, r7, r8, r9);
    L23:
        if (this.mDragState != 2) goto L26;
        return true;
    L26:
        return false;
    }

    public View findTopChildUnder(int r4, int r5) {
        int r0 = this.mParentView.getChildCount() - 1;
    L3:
        if (r0 < 0) goto L14;
        View r1 = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(r0));
        if (r4 < r1.getLeft()) goto L13;
        if (r4 >= r1.getRight()) goto L13;
        if (r5 < r1.getTop()) goto L13;
        if (r5 >= r1.getBottom()) goto L13;
        return r1;
    L13:
        r0 = r0 - 1;
        goto L3
    L14:
        return null;
    }

    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public int getViewDragState() {
        return this.mDragState;
    }

    public boolean isCapturedViewUnder(int r2, int r3) {
        return isViewUnder(this.mCapturedView, r2, r3);
    }

    public boolean isPointerDown(int r2) {
        if ((this.mPointersDown & (1 << r2)) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isViewUnder(View r2, int r3, int r4) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r3 >= r2.getLeft()) goto L8;
        return false;
    L8:
        if (r3 < r2.getRight()) goto L10;
        return false;
    L10:
        if (r4 >= r2.getTop()) goto L12;
        return false;
    L12:
        if (r4 >= r2.getBottom()) goto L18;
        return true;
    L18:
        return false;
    }

    public void processTouchEvent(MotionEvent r10) {
        int r0 = r10.getActionMasked();
        int r1 = r10.getActionIndex();
        if (r0 != 0) goto L6;
        cancel();
    L6:
        if (this.mVelocityTracker != null) goto L8;
        this.mVelocityTracker = VelocityTracker.obtain();
    L8:
        this.mVelocityTracker.addMovement(r10);
        int r2 = 0;
        if (r0 != 0) goto L11;
        float r02 = r10.getX();
        float r12 = r10.getY();
        int r102 = r10.getPointerId(0);
        View r22 = findTopChildUnder((int) r02, (int) r12);
        saveInitialMotion(r02, r12, r102);
        tryCaptureViewForDrag(r22, r102);
        int r03 = this.mInitialEdgesTouched[r102];
        int r13 = this.mTrackingEdges;
        if ((r03 & r13) == 0) goto L103;
        this.mCallback.onEdgeTouched(r03 & r13, r102);
        return;
    L103:
        return;
    L11:
        if (r0 == 1) goto L82;
        if (r0 == 2) goto L57;
        if (r0 == 3) goto L52;
        if (r0 != 5) goto L19;
        int r04 = r10.getPointerId(r1);
        float r23 = r10.getX(r1);
        float r103 = r10.getY(r1);
        saveInitialMotion(r23, r103, r04);
        if (this.mDragState != 0) goto L48;
        tryCaptureViewForDrag(findTopChildUnder((int) r23, (int) r103), r04);
        int r104 = this.mInitialEdgesTouched[r04];
        int r14 = this.mTrackingEdges;
        if ((r104 & r14) == 0) goto L101;
        this.mCallback.onEdgeTouched(r104 & r14, r04);
        return;
    L101:
        return;
    L48:
        if (isCapturedViewUnder((int) r23, (int) r103) == false) goto L102;
        tryCaptureViewForDrag(this.mCapturedView, r04);
        return;
    L102:
        return;
    L19:
        if (r0 != 6) goto L104;
        int r05 = r10.getPointerId(r1);
        if (this.mDragState == 1) goto L24;
    L39:
        clearMotionHistory(r05);
        return;
    L24:
        if (r05 != this.mActivePointerId) goto L39;
        int r15 = r10.getPointerCount();
    L26:
        if (r2 >= r15) goto L36;
        int r3 = r10.getPointerId(r2);
        if (r3 == this.mActivePointerId) goto L35;
        View r4 = findTopChildUnder((int) r10.getX(r2), (int) r10.getY(r2));
        View r6 = this.mCapturedView;
        if (r4 != r6) goto L35;
        if (tryCaptureViewForDrag(r6, r3) == false) goto L35;
        int r105 = this.mActivePointerId;
    L37:
        if (r105 != (-1)) goto L39;
        releaseViewForPointerUp();
    L35:
        r2 = r2 + 1;
        goto L26
    L36:
        r105 = -1;
        goto L37
    L104:
        return;
    L52:
        if (this.mDragState != 1) goto L54;
        dispatchViewReleased(0.0f, 0.0f);
    L54:
        cancel();
        return;
    L57:
        if (this.mDragState == 1) goto L59;
        int r06 = r10.getPointerCount();
    L66:
        if (r2 >= r06) goto L79;
        int r16 = r10.getPointerId(r2);
        if (isValidPointerForActionMove(r16) == false) goto L78;
        float r42 = r10.getX(r2);
        float r5 = r10.getY(r2);
        float r62 = r42 - this.mInitialMotionX[r16];
        float r7 = r5 - this.mInitialMotionY[r16];
        reportNewEdgeDrags(r62, r7, r16);
        if (this.mDragState == 1) goto L79;
        View r43 = findTopChildUnder((int) r42, (int) r5);
        if (checkTouchSlop(r43, r62, r7) == false) goto L78;
        if (tryCaptureViewForDrag(r43, r16) == true) goto L79;
    L78:
        r2 = r2 + 1;
    L79:
        saveLastMotion(r10);
        return;
    L59:
        if (isValidPointerForActionMove(this.mActivePointerId) == false) goto L105;
        int r07 = r10.findPointerIndex(this.mActivePointerId);
        if (r07 == (-1)) goto L106;
        float r17 = r10.getX(r07);
        float r08 = r10.getY(r07);
        float[] r24 = this.mLastMotionX;
        int r32 = this.mActivePointerId;
        int r18 = (int) (r17 - r24[r32]);
        int r09 = (int) (r08 - this.mLastMotionY[r32]);
        dragTo(this.mCapturedView.getLeft() + r18, this.mCapturedView.getTop() + r09, r18, r09);
        goto L79
    L106:
        return;
    L105:
        return;
    L82:
        if (this.mDragState != 1) goto L84;
        releaseViewForPointerUp();
    L84:
        cancel();
    }

    public void setDragState(int r3) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState == r3) goto L8;
        this.mDragState = r3;
        this.mCallback.onViewDragStateChanged(r3);
        if (this.mDragState != 0) goto L9;
        this.mCapturedView = null;
        return;
    L9:
        return;
    }

    public boolean settleCapturedViewAt(int r4, int r5) {
        if (this.mReleaseInProgress == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        return false;
    L5:
        return forceSettleCapturedViewAt(r4, r5, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
    }

    public boolean shouldInterceptTouchEvent(MotionEvent r18) {
        int r2 = r18.getActionMasked();
        int r3 = r18.getActionIndex();
        if (r2 != 0) goto L6;
        cancel();
    L6:
        if (this.mVelocityTracker != null) goto L8;
        this.mVelocityTracker = VelocityTracker.obtain();
    L8:
        this.mVelocityTracker.addMovement(r18);
        if (r2 == 0) goto L59;
        if (r2 == 1) goto L58;
        if (r2 == 2) goto L30;
        if (r2 == 3) goto L58;
        if (r2 != 5) goto L17;
        int r22 = r18.getPointerId(r3);
        float r7 = r18.getX(r3);
        float r1 = r18.getY(r3);
        saveInitialMotion(r7, r1, r22);
        int r32 = this.mDragState;
        if (r32 != 0) goto L25;
        int r12 = this.mInitialEdgesTouched[r22];
        int r33 = this.mTrackingEdges;
        if ((r12 & r33) == 0) goto L68;
        this.mCallback.onEdgeTouched(r12 & r33, r22);
    L68:
        if (this.mDragState != 1) goto L70;
        return true;
    L70:
        return false;
    L25:
        if (r32 != 2) goto L68;
        View r13 = findTopChildUnder((int) r7, (int) r1);
        if (r13 != this.mCapturedView) goto L68;
        tryCaptureViewForDrag(r13, r22);
        goto L68
    L17:
        if (r2 != 6) goto L68;
        clearMotionHistory(r18.getPointerId(r3));
        goto L68
    L30:
        if (this.mInitialMotionX == null) goto L68;
        if (this.mInitialMotionY == null) goto L68;
        int r23 = r18.getPointerCount();
        int r34 = 0;
    L35:
        if (r34 >= r23) goto L57;
        int r5 = r18.getPointerId(r34);
        if (isValidPointerForActionMove(r5) == false) goto L56;
        float r72 = r18.getX(r34);
        float r8 = r18.getY(r34);
        float r9 = r72 - this.mInitialMotionX[r5];
        float r10 = r8 - this.mInitialMotionY[r5];
        View r73 = findTopChildUnder((int) r72, (int) r8);
        boolean r82 = checkTouchSlop(r73, r9, r10);
        if (r82 == false) goto L49;
        int r11 = r73.getLeft();
        int r122 = (int) r9;
        Callback r14 = this.mCallback;
        int r123 = r14.clampViewPositionHorizontal(r73, r11 + r122, r122);
        int r132 = r73.getTop();
        int r142 = (int) r10;
        Callback r4 = this.mCallback;
        int r42 = r4.clampViewPositionVertical(r73, r132 + r142, r142);
        int r143 = this.mCallback.getViewHorizontalDragRange(r73);
        int r15 = this.mCallback.getViewVerticalDragRange(r73);
        if (r143 == 0) goto L45;
        if (r143 <= 0) goto L49;
        if (r123 != r11) goto L49;
    L45:
        if (r15 == 0) goto L57;
        if (r15 <= 0) goto L49;
        if (r42 == r132) goto L57;
    L49:
        reportNewEdgeDrags(r9, r10, r5);
        if (this.mDragState == 1) goto L57;
        if (r82 == false) goto L56;
        if (tryCaptureViewForDrag(r73, r5) == true) goto L57;
    L56:
        r34 = r34 + 1;
    L57:
        saveLastMotion(r18);
    L58:
        cancel();
        goto L68
    L59:
        float r24 = r18.getX();
        float r35 = r18.getY();
        int r16 = r18.getPointerId(0);
        saveInitialMotion(r24, r35, r16);
        View r25 = findTopChildUnder((int) r24, (int) r35);
        if (r25 == this.mCapturedView) goto L62;
    L64:
        int r26 = this.mInitialEdgesTouched[r16];
        int r36 = this.mTrackingEdges;
        if ((r26 & r36) == 0) goto L68;
        this.mCallback.onEdgeTouched(r26 & r36, r16);
        goto L68
    L62:
        if (this.mDragState != 2) goto L64;
        tryCaptureViewForDrag(r25, r16);
        goto L64
    }

    public boolean smoothSlideViewTo(View r1, int r2, int r3) {
        this.mCapturedView = r1;
        this.mActivePointerId = -1;
        boolean r12 = forceSettleCapturedViewAt(r2, r3, 0, 0);
        if (r12 == false) goto L5;
    L9:
        return r12;
    L5:
        if (this.mDragState != 0) goto L9;
        if (this.mCapturedView == null) goto L9;
        this.mCapturedView = null;
        goto L9
    }

    public boolean smoothSlideViewTo(View r1, int r2, int r3, int r4, Interpolator r5) {
        this.mCapturedView = r1;
        this.mActivePointerId = -1;
        boolean r12 = forceSettleCapturedViewAt(r2, r3, r4, r5);
        if (r12 == false) goto L5;
    L9:
        return r12;
    L5:
        if (this.mDragState != 0) goto L9;
        if (this.mCapturedView == null) goto L9;
        this.mCapturedView = null;
        goto L9
    }

    public boolean tryCaptureViewForDrag(View r3, int r4) {
        if (r3 == this.mCapturedView) goto L5;
    L7:
        if (r3 != null) goto L9;
        return false;
    L9:
        if (this.mCallback.tryCaptureView(r3, r4) == false) goto L14;
        this.mActivePointerId = r4;
        captureChildView(r3, r4);
        return true;
    L14:
        return false;
    L5:
        if (this.mActivePointerId != r4) goto L7;
        return true;
    }
}
