package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes.dex */
public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
    private int mActionState;
    int mActivePointerId;
    Callback mCallback;
    private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback;
    private List<Integer> mDistances;
    private long mDragScrollStartTimeInMs;
    float mDx;
    float mDy;
    GestureDetectorCompat mGestureDetector;
    float mInitialTouchX;
    float mInitialTouchY;
    private ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
    private float mMaxSwipeVelocity;
    private final RecyclerView.OnItemTouchListener mOnItemTouchListener;
    View mOverdrawChild;
    int mOverdrawChildPosition;
    final List<View> mPendingCleanup;
    List<RecoverAnimation> mRecoverAnimations;
    RecyclerView mRecyclerView;
    final Runnable mScrollRunnable;
    RecyclerView.ViewHolder mSelected;
    int mSelectedFlags;
    private float mSelectedStartX;
    private float mSelectedStartY;
    private int mSlop;
    private List<RecyclerView.ViewHolder> mSwapTargets;
    private float mSwipeEscapeVelocity;
    private final float[] mTmpPosition;
    private Rect mTmpRect;
    VelocityTracker mVelocityTracker;

    /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ItemTouchHelper this$0;

        public AnonymousClass1(ItemTouchHelper r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ItemTouchHelper r0 = this.this$0;
            if (r0.mSelected != null) goto L5;
            return;
        L5:
            if (r0.scrollIfNecessary() == false) goto L12;
            ItemTouchHelper r02 = this.this$0;
            RecyclerView.ViewHolder r1 = r02.mSelected;
            if (r1 == null) goto L9;
            r02.moveIfNecessary(r1);
        L9:
            ItemTouchHelper r03 = this.this$0;
            r03.mRecyclerView.removeCallbacks(r03.mScrollRunnable);
            ViewCompat.postOnAnimation(this.this$0.mRecyclerView, this);
            return;
        }
    }

    /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$2, reason: invalid class name */
    public class AnonymousClass2 implements RecyclerView.OnItemTouchListener {
        final /* synthetic */ ItemTouchHelper this$0;

        public AnonymousClass2(ItemTouchHelper r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView r6, MotionEvent r7) {
            this.this$0.mGestureDetector.onTouchEvent(r7);
            int r62 = r7.getActionMasked();
            if (r62 != 0) goto L13;
            this.this$0.mActivePointerId = r7.getPointerId(0);
            this.this$0.mInitialTouchX = r7.getX();
            this.this$0.mInitialTouchY = r7.getY();
            this.this$0.obtainVelocityTracker();
            ItemTouchHelper r63 = this.this$0;
            if (r63.mSelected != null) goto L22;
            RecoverAnimation r64 = r63.findAnimation(r7);
            if (r64 == null) goto L22;
            ItemTouchHelper r2 = this.this$0;
            r2.mInitialTouchX -= r64.mX;
            r2.mInitialTouchY -= r64.mY;
            r2.endRecoverAnimation(r64.mViewHolder, true);
            if (this.this$0.mPendingCleanup.remove(r64.mViewHolder.itemView) == false) goto L11;
            ItemTouchHelper r22 = this.this$0;
            r22.mCallback.clearView(r22.mRecyclerView, r64.mViewHolder);
        L11:
            this.this$0.select(r64.mViewHolder, r64.mActionState);
            ItemTouchHelper r65 = this.this$0;
            r65.updateDxDy(r7, r65.mSelectedFlags, 0);
        L22:
            VelocityTracker r66 = this.this$0.mVelocityTracker;
            if (r66 == null) goto L26;
            r66.addMovement(r7);
        L26:
            if (this.this$0.mSelected == null) goto L28;
            return true;
        L28:
            return false;
        L13:
            if (r62 == 3) goto L21;
            if (r62 == 1) goto L21;
            int r23 = this.this$0.mActivePointerId;
            if (r23 == (-1)) goto L22;
            int r24 = r7.findPointerIndex(r23);
            if (r24 < 0) goto L22;
            this.this$0.checkSelectForSwipe(r62, r7, r24);
        L21:
            ItemTouchHelper r67 = this.this$0;
            r67.mActivePointerId = -1;
            r67.select(null, 0);
            goto L22
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean r2) {
            if (r2 == true) goto L4;
            return;
        L4:
            this.this$0.select(null, 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView r8, MotionEvent r9) {
            this.this$0.mGestureDetector.onTouchEvent(r9);
            VelocityTracker r82 = this.this$0.mVelocityTracker;
            if (r82 == null) goto L6;
            r82.addMovement(r9);
        L6:
            if (this.this$0.mActivePointerId == (-1)) goto L39;
            int r83 = r9.getActionMasked();
            int r1 = r9.findPointerIndex(this.this$0.mActivePointerId);
            if (r1 < 0) goto L11;
            this.this$0.checkSelectForSwipe(r83, r9, r1);
        L11:
            ItemTouchHelper r2 = this.this$0;
            RecyclerView.ViewHolder r3 = r2.mSelected;
            if (r3 == null) goto L40;
            int r4 = 0;
            if (r83 != 1) goto L17;
        L35:
            this.this$0.select(null, 0);
            this.this$0.mActivePointerId = -1;
            return;
        L17:
            if (r83 != 2) goto L19;
            if (r1 < 0) goto L38;
            r2.updateDxDy(r9, r2.mSelectedFlags, r1);
            this.this$0.moveIfNecessary(r3);
            ItemTouchHelper r84 = this.this$0;
            r84.mRecyclerView.removeCallbacks(r84.mScrollRunnable);
            this.this$0.mScrollRunnable.run();
            this.this$0.mRecyclerView.invalidate();
            return;
        L38:
            return;
        L19:
            if (r83 != 3) goto L21;
            VelocityTracker r85 = r2.mVelocityTracker;
            if (r85 == null) goto L35;
            r85.clear();
            goto L35
        L21:
            if (r83 != 6) goto L41;
            int r86 = r9.getActionIndex();
            int r0 = r9.getPointerId(r86);
            ItemTouchHelper r12 = this.this$0;
            if (r0 != r12.mActivePointerId) goto L37;
            if (r86 != 0) goto L27;
            r4 = 1;
        L27:
            r12.mActivePointerId = r9.getPointerId(r4);
            ItemTouchHelper r7 = this.this$0;
            r7.updateDxDy(r9, r7.mSelectedFlags, r86);
            return;
        L37:
            return;
        L41:
            return;
        L40:
            return;
        }
    }

    /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass3 extends RecoverAnimation {
        final /* synthetic */ ItemTouchHelper this$0;
        final /* synthetic */ RecyclerView.ViewHolder val$prevSelected;
        final /* synthetic */ int val$swipeDir;

        public AnonymousClass3(ItemTouchHelper r1, RecyclerView.ViewHolder r2, int r3, int r4, float r5, float r6, float r7, float r8, int r9, RecyclerView.ViewHolder r10) {
            this.this$0 = r1;
            this.val$swipeDir = r9;
            this.val$prevSelected = r10;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.RecoverAnimation, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            if (this.mOverridden == true) goto L16;
            int r32 = this.val$swipeDir;
            ItemTouchHelper r0 = this.this$0;
            if (r32 > 0) goto L8;
            r0.mCallback.clearView(r0.mRecyclerView, this.val$prevSelected);
        L11:
            ItemTouchHelper r33 = this.this$0;
            View r02 = r33.mOverdrawChild;
            View r2 = this.val$prevSelected.itemView;
            if (r02 != r2) goto L15;
            r33.removeChildDrawingOrderCallbackIfNecessary(r2);
            return;
        L15:
            return;
        L8:
            r0.mPendingCleanup.add(this.val$prevSelected.itemView);
            this.mIsPendingCleanup = true;
            int r34 = this.val$swipeDir;
            if (r34 <= 0) goto L11;
            this.this$0.postDispatchSwipe(this, r34);
            goto L11
        }
    }

    /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$4, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ ItemTouchHelper this$0;
        final /* synthetic */ RecoverAnimation val$anim;
        final /* synthetic */ int val$swipeDir;

        public AnonymousClass4(ItemTouchHelper r1, RecoverAnimation r2, int r3) {
            this.this$0 = r1;
            this.val$anim = r2;
            this.val$swipeDir = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView r0 = this.this$0.mRecyclerView;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isAttachedToWindow() == false) goto L21;
            RecoverAnimation r02 = this.val$anim;
            if (r02.mOverridden == false) goto L9;
            return;
        L9:
            if (r02.mViewHolder.getAdapterPosition() == (-1)) goto L23;
            RecyclerView.ItemAnimator r03 = this.this$0.mRecyclerView.getItemAnimator();
            if (r03 == null) goto L15;
            if (r03.isRunning(null) == false) goto L15;
        L18:
            this.this$0.mRecyclerView.post(this);
            return;
        L15:
            if (this.this$0.hasRunningRecoverAnim() == true) goto L18;
            this.this$0.mCallback.onSwiped(this.val$anim.mViewHolder, this.val$swipeDir);
            return;
        L23:
            return;
        }
    }

    public static abstract class Callback {
        private static final Interpolator sDragScrollInterpolator = null;
        private static final Interpolator sDragViewScrollCapInterpolator = null;
        private int mCachedMaxScrollSpeed;

        /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$Callback$1, reason: invalid class name */
        public class AnonymousClass1 implements Interpolator {
            public AnonymousClass1() {
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float r1) {
                return (((r1 * r1) * r1) * r1) * r1;
            }
        }

        /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$Callback$2, reason: invalid class name */
        public class AnonymousClass2 implements Interpolator {
            public AnonymousClass2() {
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float r2) {
                float r22 = r2 - 1.0f;
                return ((((r22 * r22) * r22) * r22) * r22) + 1.0f;
            }
        }

        static {
            sDragScrollInterpolator = new AnonymousClass1();
            sDragViewScrollCapInterpolator = new AnonymousClass2();
        }

        public Callback() {
            this.mCachedMaxScrollSpeed = -1;
        }

        public static int convertToRelativeDirection(int r3, int r4) {
            int r1 = r3 & 789516;
            if (r1 != 0) goto L5;
            return r3;
        L5:
            int r32 = r3 & (~r1);
            if (r4 != 0) goto L10;
            int r42 = r1 << 2;
        L9:
            return r32 | r42;
        L10:
            int r43 = r1 << 1;
            r32 = r32 | ((-789517) & r43);
            r42 = (r43 & 789516) << 2;
            goto L9
        }

        private int getMaxDragScroll(RecyclerView r2) {
            if (this.mCachedMaxScrollSpeed != (-1)) goto L6;
            this.mCachedMaxScrollSpeed = AndroidUtilities.dp(20.0f);
        L6:
            return this.mCachedMaxScrollSpeed;
        }

        public static int makeFlag(int r0, int r1) {
            return r1 << (r0 * 8);
        }

        public static int makeMovementFlags(int r2, int r3) {
            int r0 = makeFlag(0, r3 | r2);
            int r32 = makeFlag(1, r3) | r0;
            return makeFlag(2, r2) | r32;
        }

        public boolean canDropOver(RecyclerView r1, RecyclerView.ViewHolder r2, RecyclerView.ViewHolder r3) {
            return true;
        }

        public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder r12, List<RecyclerView.ViewHolder> r13, int r14, int r15) {
            int r11 = r12.itemView.getWidth() + r14;
            int r0 = r12.itemView.getHeight() + r15;
            int r1 = r14 - r12.itemView.getLeft();
            int r2 = r15 - r12.itemView.getTop();
            int r3 = r13.size();
            RecyclerView.ViewHolder r4 = null;
            int r5 = -1;
            int r6 = 0;
        L3:
            if (r6 >= r3) goto L38;
            RecyclerView.ViewHolder r7 = r13.get(r6);
            if (r1 <= 0) goto L13;
            int r8 = r7.itemView.getRight() - r11;
            if (r8 >= 0) goto L13;
            if (r7.itemView.getRight() <= r12.itemView.getRight()) goto L13;
            int r82 = Math.abs(r8);
            if (r82 <= r5) goto L13;
            r4 = r7;
            r5 = r82;
        L13:
            if (r1 >= 0) goto L21;
            int r83 = r7.itemView.getLeft() - r14;
            if (r83 <= 0) goto L21;
            if (r7.itemView.getLeft() >= r12.itemView.getLeft()) goto L21;
            int r84 = Math.abs(r83);
            if (r84 <= r5) goto L21;
            r4 = r7;
            r5 = r84;
        L21:
            if (r2 >= 0) goto L29;
            int r85 = r7.itemView.getTop() - r15;
            if (r85 <= 0) goto L29;
            if (r7.itemView.getTop() >= r12.itemView.getTop()) goto L29;
            int r86 = Math.abs(r85);
            if (r86 <= r5) goto L29;
            r4 = r7;
            r5 = r86;
        L29:
            if (r2 <= 0) goto L37;
            int r87 = r7.itemView.getBottom() - r0;
            if (r87 >= 0) goto L37;
            if (r7.itemView.getBottom() <= r12.itemView.getBottom()) goto L37;
            int r88 = Math.abs(r87);
            if (r88 <= r5) goto L37;
            r4 = r7;
            r5 = r88;
        L37:
            r6 = r6 + 1;
            goto L3
        L38:
            return r4;
        }

        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            ItemTouchUIUtilImpl.INSTANCE.clearView(r2.itemView);
        }

        public int convertToAbsoluteDirection(int r3, int r4) {
            int r0 = r3 & 3158064;
            if (r0 != 0) goto L5;
            return r3;
        L5:
            int r32 = r3 & (~r0);
            if (r4 != 0) goto L10;
            int r2 = r0 >> 2;
        L9:
            return r2 | r32;
        L10:
            int r42 = r0 >> 1;
            r32 = r32 | ((-3158065) & r42);
            r2 = (3158064 & r42) >> 2;
            goto L9
        }

        public final int getAbsoluteMovementFlags(RecyclerView r1, RecyclerView.ViewHolder r2) {
            return convertToAbsoluteDirection(getMovementFlags(r1, r2), ViewCompat.getLayoutDirection(r1));
        }

        public long getAnimationDuration(RecyclerView r1, int r2, float r3, float r4) {
            RecyclerView.ItemAnimator r0 = r1.getItemAnimator();
            if (r0 != null) goto L9;
            if (r2 != 8) goto L7;
            return 200;
        L7:
            return 250;
        L9:
            if (r2 != 8) goto L13;
            return r0.getMoveDuration();
        L13:
            return r0.getRemoveDuration();
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getMoveThreshold(RecyclerView.ViewHolder r1) {
            return 0.5f;
        }

        public abstract int getMovementFlags(RecyclerView r1, RecyclerView.ViewHolder r2);

        public float getSwipeEscapeVelocity(float r1) {
            return r1;
        }

        public float getSwipeThreshold(RecyclerView.ViewHolder r1) {
            return 0.5f;
        }

        public float getSwipeVelocityThreshold(float r1) {
            return r1;
        }

        public boolean hasDragFlag(RecyclerView r1, RecyclerView.ViewHolder r2) {
            if ((getAbsoluteMovementFlags(r1, r2) & 16711680) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public int interpolateOutOfBoundsScroll(RecyclerView r2, int r3, int r4, int r5, long r6) {
            int r1 = getMaxDragScroll(r2);
            int r22 = Math.abs(r4);
            int r52 = (int) Math.signum(r4);
            float r0 = 1.0f;
            int r12 = (int) ((r52 * r1) * sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (r22 * 1.0f) / r3)));
            if (r6 > 500) goto L6;
            r0 = r6 / 500.0f;
        L6:
            int r13 = (int) (r12 * sDragScrollInterpolator.getInterpolation(r0));
            if (r13 != 0) goto L13;
            if (r4 <= 0) goto L11;
            return 1;
        L11:
            return -1;
        L13:
            return r13;
        }

        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public boolean isLongPressDragEnabled() {
            return true;
        }

        public void onChildDraw(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
            ItemTouchUIUtilImpl.INSTANCE.onDraw(r1, r2, r3.itemView, r4, r5, r6, r7);
        }

        public void onChildDrawOver(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
            ItemTouchUIUtilImpl.INSTANCE.onDrawOver(r1, r2, r3.itemView, r4, r5, r6, r7);
        }

        public void onDraw(Canvas r13, RecyclerView r14, RecyclerView.ViewHolder r15, List<RecoverAnimation> r16, int r17, float r18, float r19) {
            int r8 = r16.size();
            int r9 = 0;
        L3:
            if (r9 >= r8) goto L5;
            RecoverAnimation r0 = r16.get(r9);
            r0.update();
            int r11 = r13.save();
            onChildDraw(r13, r14, r0.mViewHolder, r0.mX, r0.mY, r0.mActionState, false);
            r13.restoreToCount(r11);
            r9 = r9 + 1;
            goto L3
        L5:
            if (r15 == null) goto L9;
            int r82 = r13.save();
            onChildDraw(r13, r14, r15, r18, r19, r17, true);
            r13.restoreToCount(r82);
            return;
        }

        public void onDrawOver(Canvas r14, RecyclerView r15, RecyclerView.ViewHolder r16, List<RecoverAnimation> r17, int r18, float r19, float r20) {
            int r9 = r17.size();
            boolean r10 = false;
            int r11 = 0;
        L3:
            if (r11 >= r9) goto L5;
            RecoverAnimation r0 = r17.get(r11);
            int r12 = r14.save();
            onChildDrawOver(r14, r15, r0.mViewHolder, r0.mX, r0.mY, r0.mActionState, false);
            r14.restoreToCount(r12);
            r11 = r11 + 1;
            goto L3
        L5:
            if (r16 == null) goto L7;
            int r112 = r14.save();
            onChildDrawOver(r14, r15, r16, r19, r20, r18, true);
            r14.restoreToCount(r112);
        L7:
            int r92 = r9 - 1;
        L8:
            if (r92 < 0) goto L17;
            RecoverAnimation r1 = r17.get(r92);
            boolean r2 = r1.mEnded;
            if (r2 == true) goto L12;
        L14:
            if (r2 == true) goto L16;
            r10 = true;
        L16:
            r92 = r92 - 1;
            goto L8
        L12:
            if (r1.mIsPendingCleanup == true) goto L14;
            r17.remove(r92);
            goto L16
        L17:
            if (r10 == false) goto L25;
            r15.invalidate();
            return;
        }

        public abstract boolean onMove(RecyclerView r1, RecyclerView.ViewHolder r2, RecyclerView.ViewHolder r3);

        /* JADX WARN: Multi-variable type inference failed */
        public void onMoved(RecyclerView r1, RecyclerView.ViewHolder r2, int r3, RecyclerView.ViewHolder r4, int r5, int r6, int r7) {
            RecyclerView.LayoutManager r0 = r1.getLayoutManager();
            if ((r0 instanceof ViewDropHandler) == false) goto L7;
            ((ViewDropHandler) r0).prepareForDrop(r2.itemView, r4.itemView, r6, r7);
            return;
        L7:
            if (r0.canScrollHorizontally() == false) goto L15;
            if (r0.getDecoratedLeft(r4.itemView) > r1.getPaddingLeft()) goto L12;
            r1.scrollToPosition(r5);
        L12:
            if (r0.getDecoratedRight(r4.itemView) < (r1.getWidth() - r1.getPaddingRight())) goto L15;
            r1.scrollToPosition(r5);
        L15:
            if (r0.canScrollVertically() == true) goto L17;
            return;
        L17:
            if (r0.getDecoratedTop(r4.itemView) > r1.getPaddingTop()) goto L20;
            r1.scrollToPosition(r5);
        L20:
            if (r0.getDecoratedBottom(r4.itemView) < (r1.getHeight() - r1.getPaddingBottom())) goto L24;
            r1.scrollToPosition(r5);
            return;
        }

        public void onSelectedChanged(RecyclerView.ViewHolder r1, int r2) {
            if (r1 == null) goto L5;
            ItemTouchUIUtilImpl.INSTANCE.onSelected(r1.itemView);
            return;
        }

        public abstract void onSwiped(RecyclerView.ViewHolder r1, int r2);
    }

    public class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean mShouldReactToLongPress;
        final /* synthetic */ ItemTouchHelper this$0;

        public ItemTouchHelperGestureListener(ItemTouchHelper r1) {
            this.this$0 = r1;
            this.mShouldReactToLongPress = true;
        }

        public void doNotReactToLongPress() {
            this.mShouldReactToLongPress = false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r1) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent r4) {
            if (this.mShouldReactToLongPress == false) goto L22;
            View r0 = this.this$0.findChildView(r4);
            if (r0 == null) goto L18;
            RecyclerView.ViewHolder r02 = this.this$0.mRecyclerView.getChildViewHolder(r0);
            if (r02 == null) goto L19;
            ItemTouchHelper r1 = this.this$0;
            if (r1.mCallback.hasDragFlag(r1.mRecyclerView, r02) == false) goto L23;
            int r12 = r4.getPointerId(0);
            int r2 = this.this$0.mActivePointerId;
            if (r12 != r2) goto L20;
            int r13 = r4.findPointerIndex(r2);
            float r22 = r4.getX(r13);
            float r42 = r4.getY(r13);
            ItemTouchHelper r14 = this.this$0;
            r14.mInitialTouchX = r22;
            r14.mInitialTouchY = r42;
            r14.mDy = 0.0f;
            r14.mDx = 0.0f;
            if (r14.mCallback.isLongPressDragEnabled() == false) goto L21;
            this.this$0.select(r02, 2);
            return;
        L21:
            return;
        L20:
            return;
        L23:
            return;
        L19:
            return;
        L18:
            return;
        }
    }

    /* loaded from: classes4.dex */
    public static class RecoverAnimation implements Animator.AnimatorListener {
        final int mActionState;
        final int mAnimationType;
        boolean mEnded;
        private float mFraction;
        boolean mIsPendingCleanup;
        boolean mOverridden;
        final float mStartDx;
        final float mStartDy;
        final float mTargetX;
        final float mTargetY;
        private final ValueAnimator mValueAnimator;
        final RecyclerView.ViewHolder mViewHolder;
        float mX;
        float mY;

        /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$RecoverAnimation$1, reason: invalid class name */
        public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ RecoverAnimation this$0;

            public AnonymousClass1(RecoverAnimation r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator r1) {
                this.this$0.setFraction(r1.getAnimatedFraction());
            }
        }

        public RecoverAnimation(RecyclerView.ViewHolder r2, int r3, int r4, float r5, float r6, float r7, float r8) {
            this.mOverridden = false;
            this.mEnded = false;
            this.mActionState = r4;
            this.mAnimationType = r3;
            this.mViewHolder = r2;
            this.mStartDx = r5;
            this.mStartDy = r6;
            this.mTargetX = r7;
            this.mTargetY = r8;
            ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.mValueAnimator = r32;
            r32.addUpdateListener(new AnonymousClass1(this));
            r32.setTarget(r2.itemView);
            r32.addListener(this);
            setFraction(0.0f);
        }

        public void cancel() {
            this.mValueAnimator.cancel();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            setFraction(1.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.mEnded == true) goto L5;
            this.mViewHolder.setIsRecyclable(true);
        L5:
            this.mEnded = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator r1) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
        }

        public void setDuration(long r1) {
            this.mValueAnimator.setDuration(r1);
        }

        public void setFraction(float r1) {
            this.mFraction = r1;
        }

        public void start() {
            this.mViewHolder.setIsRecyclable(false);
            this.mValueAnimator.start();
        }

        public void update() {
            float r0 = this.mStartDx;
            float r1 = this.mTargetX;
            if (r0 != r1) goto L5;
            this.mX = this.mViewHolder.itemView.getTranslationX();
        L6:
            float r02 = this.mStartDy;
            float r12 = this.mTargetY;
            if (r02 != r12) goto L10;
            this.mY = this.mViewHolder.itemView.getTranslationY();
            return;
        L10:
            this.mY = r02 + (this.mFraction * (r12 - r02));
            return;
        L5:
            this.mX = r0 + (this.mFraction * (r1 - r0));
            goto L6
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class SimpleCallback extends Callback {
        private int mDefaultDragDirs;
        private int mDefaultSwipeDirs;

        public SimpleCallback(int r1, int r2) {
            this.mDefaultSwipeDirs = r2;
            this.mDefaultDragDirs = r1;
        }

        public int getDragDirs(RecyclerView r1, RecyclerView.ViewHolder r2) {
            return this.mDefaultDragDirs;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r2, RecyclerView.ViewHolder r3) {
            return Callback.makeMovementFlags(getDragDirs(r2, r3), getSwipeDirs(r2, r3));
        }

        public int getSwipeDirs(RecyclerView r1, RecyclerView.ViewHolder r2) {
            return this.mDefaultSwipeDirs;
        }
    }

    public interface ViewDropHandler {
        void prepareForDrop(View r1, View r2, int r3, int r4);
    }

    public ItemTouchHelper(Callback r4) {
        this.mPendingCleanup = new ArrayList();
        this.mTmpPosition = new float[2];
        this.mSelected = null;
        this.mActivePointerId = -1;
        this.mActionState = 0;
        this.mRecoverAnimations = new ArrayList();
        this.mScrollRunnable = new AnonymousClass1(this);
        this.mChildDrawingOrderCallback = null;
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        this.mOnItemTouchListener = new AnonymousClass2(this);
        this.mCallback = r4;
    }

    private void addChildDrawingOrderCallback() {
    }

    private int checkVerticalSwipe(RecyclerView.ViewHolder r8, int r9) {
        if ((r9 & 3) == 0) goto L28;
        int r2 = 1;
        if (this.mDy <= 0.0f) goto L7;
        int r0 = 2;
    L8:
        VelocityTracker r4 = this.mVelocityTracker;
        if (r4 != null) goto L11;
    L23:
        float r1 = this.mRecyclerView.getHeight() * this.mCallback.getSwipeThreshold(r8);
        if ((r9 & r0) != 0) goto L26;
        return 0;
    L26:
        if (Math.abs(this.mDy) <= r1) goto L31;
        return r0;
    L31:
        return 0;
    L11:
        if (this.mActivePointerId <= (-1)) goto L23;
        r4.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
        float r42 = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
        float r5 = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
        if (r5 <= 0.0f) goto L15;
        r2 = 2;
    L15:
        float r12 = Math.abs(r5);
        if ((r2 & r9) == 0) goto L23;
        if (r2 != r0) goto L23;
        if (r12 < this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity)) goto L23;
        if (r12 <= Math.abs(r42)) goto L23;
        return r2;
    L7:
        r0 = 1;
        goto L8
    L28:
        return 0;
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.removeOnChildAttachStateChangeListener(this);
        int r0 = this.mRecoverAnimations.size();
    L3:
        r0 = r0 - 1;
        List<RecoverAnimation> r1 = this.mRecoverAnimations;
        if (r0 < 0) goto L6;
        this.mCallback.clearView(this.mRecyclerView, r1.get(0).mViewHolder);
        goto L3
    L6:
        r1.clear();
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        releaseVelocityTracker();
        stopGestureDetection();
    }

    private List<RecyclerView.ViewHolder> findSwapTargets(RecyclerView.ViewHolder r17) {
        RecyclerView.ViewHolder r1 = r17;
        List<RecyclerView.ViewHolder> r2 = this.mSwapTargets;
        if (r2 != null) goto L5;
        this.mSwapTargets = new ArrayList();
        this.mDistances = new ArrayList();
    L6:
        int r22 = this.mCallback.getBoundingBoxMargin();
        int r3 = Math.round(this.mSelectedStartX + this.mDx) - r22;
        int r4 = Math.round(this.mSelectedStartY + this.mDy) - r22;
        int r23 = r22 * 2;
        int r5 = (r1.itemView.getWidth() + r3) + r23;
        int r6 = (r1.itemView.getHeight() + r4) + r23;
        int r24 = (r3 + r5) / 2;
        int r7 = (r4 + r6) / 2;
        RecyclerView.LayoutManager r8 = this.mRecyclerView.getLayoutManager();
        int r9 = r8.getChildCount();
        int r11 = 0;
    L7:
        if (r11 >= r9) goto L30;
        View r12 = r8.getChildAt(r11);
        if (r12 == r1.itemView) goto L28;
        if (r12.getBottom() < r4) goto L28;
        if (r12.getTop() > r6) goto L28;
        if (r12.getRight() < r3) goto L28;
        if (r12.getLeft() > r5) goto L28;
        RecyclerView.ViewHolder r13 = this.mRecyclerView.getChildViewHolder(r12);
        if (this.mCallback.canDropOver(this.mRecyclerView, this.mSelected, r13) == false) goto L28;
        int r10 = Math.abs(r24 - ((r12.getLeft() + r12.getRight()) / 2));
        int r122 = Math.abs(r7 - ((r12.getTop() + r12.getBottom()) / 2));
        int r102 = (r10 * r10) + (r122 * r122);
        int r123 = this.mSwapTargets.size();
        int r14 = 0;
        int r15 = 0;
    L23:
        if (r14 >= r123) goto L27;
        if (r102 <= this.mDistances.get(r14).intValue()) goto L27;
        r15 = r15 + 1;
        r14 = r14 + 1;
    L27:
        this.mSwapTargets.add(r15, r13);
        this.mDistances.add(r15, Integer.valueOf(r102));
    L28:
        r11 = r11 + 1;
        r1 = r17;
        goto L7
    L30:
        return this.mSwapTargets;
    L5:
        r2.clear();
        this.mDistances.clear();
        goto L6
    }

    private RecyclerView.ViewHolder findSwipedView(MotionEvent r7) {
        RecyclerView.LayoutManager r0 = this.mRecyclerView.getLayoutManager();
        int r1 = this.mActivePointerId;
        if (r1 != (-1)) goto L5;
        return null;
    L5:
        int r12 = r7.findPointerIndex(r1);
        float r2 = r7.getX(r12) - this.mInitialTouchX;
        float r13 = r7.getY(r12) - this.mInitialTouchY;
        float r22 = Math.abs(r2);
        float r14 = Math.abs(r13);
        int r4 = this.mSlop;
        if (r22 >= r4) goto L11;
        if (r14 >= r4) goto L11;
        return null;
    L11:
        if (r22 <= r14) goto L16;
        if (r0.canScrollHorizontally() == false) goto L16;
        return null;
    L16:
        if (r14 > r22) goto L18;
    L20:
        View r72 = findChildView(r7);
        if (r72 != null) goto L24;
        return null;
    L24:
        return this.mRecyclerView.getChildViewHolder(r72);
    L18:
        if (r0.canScrollVertically() == false) goto L20;
        return null;
    }

    private void getSelectedDxDy(float[] r4) {
        if ((this.mSelectedFlags & 12) == 0) goto L5;
        r4[0] = (this.mSelectedStartX + this.mDx) - this.mSelected.itemView.getLeft();
    L7:
        if ((this.mSelectedFlags & 3) == 0) goto L10;
        r4[1] = (this.mSelectedStartY + this.mDy) - this.mSelected.itemView.getTop();
        return;
    L10:
        r4[1] = this.mSelected.itemView.getTranslationY();
        return;
    L5:
        r4[0] = this.mSelected.itemView.getTranslationX();
        goto L7
    }

    private static boolean hitTest(View r1, float r2, float r3, float r4, float r5) {
        if (r2 >= r4) goto L5;
        return false;
    L5:
        if (r2 <= (r4 + r1.getWidth())) goto L7;
        return false;
    L7:
        if (r3 >= r5) goto L9;
        return false;
    L9:
        if (r3 > (r5 + r1.getHeight())) goto L16;
        return true;
    L16:
        return false;
    }

    private void releaseVelocityTracker() {
        VelocityTracker r0 = this.mVelocityTracker;
        if (r0 == null) goto L6;
        r0.recycle();
        this.mVelocityTracker = null;
        return;
    }

    private void setupCallbacks() {
        this.mSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.addOnChildAttachStateChangeListener(this);
        startGestureDetection();
    }

    private void startGestureDetection() {
        this.mItemTouchHelperGestureListener = new ItemTouchHelperGestureListener(this);
        this.mGestureDetector = new GestureDetectorCompat(this.mRecyclerView.getContext(), this.mItemTouchHelperGestureListener);
    }

    private void stopGestureDetection() {
        ItemTouchHelperGestureListener r0 = this.mItemTouchHelperGestureListener;
        if (r0 == null) goto L6;
        r0.doNotReactToLongPress();
        this.mItemTouchHelperGestureListener = null;
    L6:
        if (this.mGestureDetector == null) goto L9;
        this.mGestureDetector = null;
        return;
    }

    private int swipeIfNecessary(RecyclerView.ViewHolder r6) {
        if (this.mActionState != 2) goto L5;
        return 0;
    L5:
        int r0 = this.mCallback.getMovementFlags(this.mRecyclerView, r6);
        int r1 = (this.mCallback.convertToAbsoluteDirection(r0, ViewCompat.getLayoutDirection(this.mRecyclerView)) & 65280) >> 8;
        if (r1 != 0) goto L8;
        return 0;
    L8:
        int r02 = (r0 & 65280) >> 8;
        if (Math.abs(this.mDx) <= Math.abs(this.mDy)) goto L20;
        int r3 = checkHorizontalSwipe(r6, r1);
        if (r3 > 0) goto L13;
        int r5 = checkVerticalSwipe(r6, r1);
        if (r5 <= 0) goto L30;
        return r5;
    L30:
        return 0;
    L13:
        if ((r02 & r3) == 0) goto L15;
        return r3;
    L15:
        return Callback.convertToRelativeDirection(r3, ViewCompat.getLayoutDirection(this.mRecyclerView));
    L20:
        int r32 = checkVerticalSwipe(r6, r1);
        if (r32 <= 0) goto L23;
        return r32;
    L23:
        int r62 = checkHorizontalSwipe(r6, r1);
        if (r62 <= 0) goto L30;
        if ((r02 & r62) == 0) goto L28;
        return r62;
    L28:
        return Callback.convertToRelativeDirection(r62, ViewCompat.getLayoutDirection(this.mRecyclerView));
    }

    public void attachToRecyclerView(RecyclerView r2) {
        RecyclerView r0 = this.mRecyclerView;
        if (r0 == r2) goto L12;
        if (r0 == null) goto L7;
        destroyCallbacks();
    L7:
        this.mRecyclerView = r2;
        if (r2 == null) goto L11;
        r2.getResources();
        this.mSwipeEscapeVelocity = AndroidUtilities.dp(120.0f);
        this.mMaxSwipeVelocity = AndroidUtilities.dp(800.0f);
        setupCallbacks();
        return;
    L11:
        return;
    }

    public int checkHorizontalSwipe(RecyclerView.ViewHolder r8, int r9) {
        if ((r9 & 12) == 0) goto L28;
        int r2 = 4;
        if (this.mDx <= 0.0f) goto L7;
        int r0 = 8;
    L8:
        VelocityTracker r4 = this.mVelocityTracker;
        if (r4 != null) goto L11;
    L23:
        float r1 = this.mRecyclerView.getWidth() * this.mCallback.getSwipeThreshold(r8);
        if ((r9 & r0) != 0) goto L26;
        return 0;
    L26:
        if (Math.abs(this.mDx) <= r1) goto L31;
        return r0;
    L31:
        return 0;
    L11:
        if (this.mActivePointerId <= (-1)) goto L23;
        r4.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
        float r42 = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
        float r5 = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
        if (r42 <= 0.0f) goto L15;
        r2 = 8;
    L15:
        float r12 = Math.abs(r42);
        if ((r2 & r9) == 0) goto L23;
        if (r0 != r2) goto L23;
        if (r12 < this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity)) goto L23;
        if (r12 <= Math.abs(r5)) goto L23;
        return r2;
    L7:
        r0 = 4;
        goto L8
    L28:
        return 0;
    }

    public void checkSelectForSwipe(int r9, MotionEvent r10, int r11) {
        if (this.mSelected == null) goto L5;
        return;
    L5:
        if (r9 == 2) goto L7;
        return;
    L7:
        if (this.mActionState != 2) goto L9;
        return;
    L9:
        if (this.mCallback.isItemViewSwipeEnabled() == true) goto L12;
        return;
    L12:
        if (this.mRecyclerView.getScrollState() == 1) goto L53;
        RecyclerView.ViewHolder r92 = findSwipedView(r10);
        if (r92 == null) goto L54;
        int r2 = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, r92) & 65280) >> 8;
        if (r2 == 0) goto L55;
        float r3 = r10.getX(r11);
        float r112 = r10.getY(r11);
        float r32 = r3 - this.mInitialTouchX;
        float r113 = r112 - this.mInitialTouchY;
        float r4 = Math.abs(r32);
        float r5 = Math.abs(r113);
        int r6 = this.mSlop;
        if (r4 >= r6) goto L26;
        if (r5 >= r6) goto L26;
        return;
    L26:
        if (r4 <= r5) goto L38;
        if (r32 >= 0.0f) goto L33;
        if ((r2 & 4) != 0) goto L33;
        return;
    L33:
        if (r32 > 0.0f) goto L35;
    L47:
        this.mDy = 0.0f;
        this.mDx = 0.0f;
        this.mActivePointerId = r10.getPointerId(0);
        select(r92, 1);
        return;
    L35:
        if ((r2 & 8) != 0) goto L47;
        return;
    L38:
        if (r113 >= 0.0f) goto L43;
        if ((r2 & 1) != 0) goto L43;
        return;
    L43:
        if (r113 <= 0.0f) goto L47;
        if ((r2 & 2) != 0) goto L47;
        return;
    L55:
        return;
    L54:
        return;
    }

    public void clearRecoverAnimations() {
        this.mRecoverAnimations.clear();
    }

    public void endRecoverAnimation(RecyclerView.ViewHolder r4, boolean r5) {
        int r0 = this.mRecoverAnimations.size() - 1;
    L3:
        if (r0 < 0) goto L12;
        RecoverAnimation r1 = this.mRecoverAnimations.get(r0);
        if (r1.mViewHolder == r4) goto L6;
        r0 = r0 - 1;
        goto L3
    L6:
        r1.mOverridden |= r5;
        if (r1.mEnded == true) goto L9;
        r1.cancel();
    L9:
        this.mRecoverAnimations.remove(r0);
        return;
    }

    public RecoverAnimation findAnimation(MotionEvent r5) {
        if (this.mRecoverAnimations.isEmpty() == false) goto L5;
        return null;
    L5:
        View r52 = findChildView(r5);
        int r0 = this.mRecoverAnimations.size() - 1;
    L6:
        if (r0 < 0) goto L11;
        RecoverAnimation r2 = this.mRecoverAnimations.get(r0);
        if (r2.mViewHolder.itemView == r52) goto L9;
        r0 = r0 - 1;
        goto L6
    L9:
        return r2;
    L11:
        return null;
    }

    public View findChildView(MotionEvent r6) {
        float r0 = r6.getX();
        float r62 = r6.getY();
        RecyclerView.ViewHolder r1 = this.mSelected;
        if (r1 == null) goto L7;
        View r12 = r1.itemView;
        if (hitTest(r12, r0, r62, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy) == false) goto L7;
        return r12;
    L7:
        int r13 = this.mRecoverAnimations.size() - 1;
    L8:
        if (r13 < 0) goto L14;
        RecoverAnimation r2 = this.mRecoverAnimations.get(r13);
        View r3 = r2.mViewHolder.itemView;
        if (hitTest(r3, r0, r62, r2.mX, r2.mY) == true) goto L11;
        r13 = r13 - 1;
        goto L8
    L11:
        return r3;
    L14:
        return this.mRecyclerView.findChildViewUnder(r0, r62);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
        r1.setEmpty();
    }

    public boolean hasRunningRecoverAnim() {
        int r0 = this.mRecoverAnimations.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (this.mRecoverAnimations.get(r2).mEnded == false) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public boolean isIdle() {
        if (this.mActionState != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void moveIfNecessary(RecyclerView.ViewHolder r11) {
        if (this.mRecyclerView.isLayoutRequested() == false) goto L6;
        return;
    L6:
        if (this.mActionState != 2) goto L26;
        float r0 = this.mCallback.getMoveThreshold(r11);
        int r8 = (int) (this.mSelectedStartX + this.mDx);
        int r9 = (int) (this.mSelectedStartY + this.mDy);
        if (Math.abs(r9 - r11.itemView.getTop()) < (r11.itemView.getHeight() * r0)) goto L11;
    L13:
        List<RecyclerView.ViewHolder> r02 = findSwapTargets(r11);
        if (r02.size() == 0) goto L28;
        RecyclerView.ViewHolder r6 = this.mCallback.chooseDropTarget(r11, r02, r8, r9);
        if (r6 != null) goto L20;
        this.mSwapTargets.clear();
        this.mDistances.clear();
        return;
    L20:
        int r7 = r6.getAdapterPosition();
        int r5 = r11.getAdapterPosition();
        if (this.mCallback.onMove(this.mRecyclerView, r11, r6) == false) goto L24;
        this.mCallback.onMoved(this.mRecyclerView, r11, r5, r6, r7, r8, r9);
        return;
    L24:
        return;
    L28:
        return;
    L11:
        if (Math.abs(r8 - r11.itemView.getLeft()) >= (r11.itemView.getWidth() * r0)) goto L13;
        return;
    }

    public void obtainVelocityTracker() {
        VelocityTracker r0 = this.mVelocityTracker;
        if (r0 == null) goto L5;
        r0.recycle();
    L5:
        this.mVelocityTracker = VelocityTracker.obtain();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewAttachedToWindow(View r1) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewDetachedFromWindow(View r3) {
        removeChildDrawingOrderCallbackIfNecessary(r3);
        RecyclerView.ViewHolder r32 = this.mRecyclerView.getChildViewHolder(r3);
        if (r32 == null) goto L15;
        RecyclerView.ViewHolder r0 = this.mSelected;
        if (r0 == null) goto L10;
        if (r32 != r0) goto L10;
        select(null, 0);
        return;
    L10:
        endRecoverAnimation(r32, false);
        if (this.mPendingCleanup.remove(r32.itemView) == false) goto L14;
        this.mCallback.clearView(this.mRecyclerView, r32);
        return;
    L14:
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas r10, RecyclerView r11, RecyclerView.State r12) {
        this.mOverdrawChildPosition = -1;
        if (this.mSelected == null) goto L5;
        getSelectedDxDy(this.mTmpPosition);
        float[] r122 = this.mTmpPosition;
        float r0 = r122[0];
        float r8 = r122[1];
        float r7 = r0;
    L6:
        this.mCallback.onDraw(r10, r11, this.mSelected, this.mRecoverAnimations, this.mActionState, r7, r8);
        return;
    L5:
        r7 = 0.0f;
        r8 = 0.0f;
        goto L6
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas r10, RecyclerView r11, RecyclerView.State r12) {
        if (this.mSelected == null) goto L5;
        getSelectedDxDy(this.mTmpPosition);
        float[] r122 = this.mTmpPosition;
        float r0 = r122[0];
        float r8 = r122[1];
        float r7 = r0;
    L6:
        this.mCallback.onDrawOver(r10, r11, this.mSelected, this.mRecoverAnimations, this.mActionState, r7, r8);
        return;
    L5:
        r7 = 0.0f;
        r8 = 0.0f;
        goto L6
    }

    public void postDispatchSwipe(RecoverAnimation r3, int r4) {
        this.mRecyclerView.post(new AnonymousClass4(this, r3, r4));
    }

    public void removeChildDrawingOrderCallbackIfNecessary(View r2) {
        if (r2 != this.mOverdrawChild) goto L8;
        this.mOverdrawChild = null;
        if (this.mChildDrawingOrderCallback == null) goto L9;
        this.mRecyclerView.setChildDrawingOrderCallback(null);
        return;
    L9:
        return;
    }

    public boolean scrollIfNecessary() {
        if (this.mSelected != null) goto L6;
        this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
        return false;
    L6:
        long r4 = System.currentTimeMillis();
        long r6 = this.mDragScrollStartTimeInMs;
        if (r6 != Long.MIN_VALUE) goto L10;
        long r62 = 0;
    L9:
        long r13 = r62;
        RecyclerView.LayoutManager r0 = this.mRecyclerView.getLayoutManager();
        if (this.mTmpRect != null) goto L14;
        this.mTmpRect = new Rect();
    L14:
        r0.calculateItemDecorationsForChild(this.mSelected.itemView, this.mTmpRect);
        if (r0.canScrollHorizontally() == false) goto L25;
        int r63 = (int) (this.mSelectedStartX + this.mDx);
        int r8 = (r63 - this.mTmpRect.left) - this.mRecyclerView.getPaddingLeft();
        float r9 = this.mDx;
        if (r9 >= 0.0f) goto L21;
        if (r8 >= 0) goto L21;
    L19:
        int r11 = r8;
    L27:
        if (r0.canScrollVertically() == false) goto L37;
        int r02 = (int) (this.mSelectedStartY + this.mDy);
        int r64 = (r02 - this.mTmpRect.top) - this.mRecyclerView.getPaddingTop();
        float r82 = this.mDy;
        if (r82 >= 0.0f) goto L33;
        if (r64 >= 0) goto L33;
    L38:
        if (r11 == 0) goto L40;
        r11 = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getWidth(), r11, this.mRecyclerView.getWidth(), r13);
    L40:
        int r03 = r11;
        if (r64 == 0) goto L44;
        r64 = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getHeight(), r64, this.mRecyclerView.getHeight(), r13);
    L44:
        if (r03 != 0) goto L50;
        if (r64 != 0) goto L50;
        this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
        return false;
    L50:
        if (this.mDragScrollStartTimeInMs != Long.MIN_VALUE) goto L52;
        this.mDragScrollStartTimeInMs = r4;
    L52:
        this.mRecyclerView.scrollBy(r03, r64);
        return true;
    L33:
        if (r82 <= 0.0f) goto L37;
        r64 = ((r02 + this.mSelected.itemView.getHeight()) + this.mTmpRect.bottom) - (this.mRecyclerView.getHeight() - this.mRecyclerView.getPaddingBottom());
        if (r64 > 0) goto L38;
    L37:
        r64 = 0;
    L21:
        if (r9 <= 0.0f) goto L25;
        r8 = ((r63 + this.mSelected.itemView.getWidth()) + this.mTmpRect.right) - (this.mRecyclerView.getWidth() - this.mRecyclerView.getPaddingRight());
        if (r8 > 0) goto L19;
    L25:
        r11 = 0;
        goto L27
    L10:
        r62 = r4 - r6;
        goto L9
    }

    public void select(RecyclerView.ViewHolder r19, int r20) {
        if (r19 == this.mSelected) goto L5;
    L7:
        this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
        int r4 = this.mActionState;
        endRecoverAnimation(r19, true);
        this.mActionState = r20;
        if (r20 != 2) goto L13;
        if (r19 == null) goto L11;
        this.mOverdrawChild = r19.itemView;
        addChildDrawingOrderCallback();
        goto L13
    L11:
        f$$ExternalSyntheticBUOutline1.m("Must pass a ViewHolder when dragging");
        return;
    L13:
        int r16 = (1 << ((r20 * 8) + 8)) - 1;
        RecyclerView.ViewHolder r2 = this.mSelected;
        boolean r0 = false;
        if (r2 != null) goto L16;
        boolean r13 = false;
    L44:
        if (r19 == null) goto L48;
        this.mSelectedFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, r19) & r16) >> (this.mActionState * 8);
        this.mSelectedStartX = r19.itemView.getLeft();
        this.mSelectedStartY = r19.itemView.getTop();
        this.mSelected = r19;
        if (r20 != 2) goto L48;
        r19.itemView.performHapticFeedback(r13 ? 1 : 0, 2);     // Catch: Exception -> L58
    L48:
        ViewParent r22 = this.mRecyclerView.getParent();
        if (r22 != null) goto L51;
    L54:
        if (r0 == true) goto L56;
        this.mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
    L56:
        this.mCallback.onSelectedChanged(this.mSelected, this.mActionState);
        this.mRecyclerView.invalidate();
        return;
    L51:
        if (this.mSelected == null) goto L53;
        r13 = true;
    L53:
        r22.requestDisallowInterceptTouchEvent(r13);
        goto L54
    L16:
        if (r2.itemView.getParent() == null) goto L41;
        boolean r3 = shouldSwipeBack();
        if (r4 != 2) goto L20;
        int r9 = 0;
    L21:
        releaseVelocityTracker();
        if (r3 == false) goto L24;
    L23:
        float r7 = 0.0f;
        float r8 = 0.0f;
    L35:
        if (r4 != 2) goto L37;
        int r32 = 8;
    L40:
        getSelectedDxDy(this.mTmpPosition);
        float[] r5 = this.mTmpPosition;
        float r52 = r5[0];
        float r6 = r5[1];
        r13 = false;
        AnonymousClass3 r02 = new AnonymousClass3(this, r2, r32, r4, r52, r6, r7, r8, r9, r2);
        r02.setDuration(this.mCallback.getAnimationDuration(this.mRecyclerView, r32, r7 - r52, r8 - r6));
        this.mRecoverAnimations.add(r02);
        r02.start();
        r0 = true;
    L42:
        this.mSelected = null;
        goto L44
    L37:
        if (r9 <= 0) goto L39;
        r32 = 2;
        goto L40
    L39:
        r32 = 4;
        goto L40
    L24:
        if (r9 == 1) goto L34;
        if (r9 == 2) goto L34;
        if (r9 == 4) goto L33;
        if (r9 == 8) goto L33;
        if (r9 == 16) goto L33;
        if (r9 != 32) goto L23;
    L33:
        r7 = Math.signum(this.mDx) * this.mRecyclerView.getWidth();
        r8 = 0.0f;
    L34:
        r8 = Math.signum(this.mDy) * this.mRecyclerView.getHeight();
        r7 = 0.0f;
        goto L35
    L20:
        r9 = swipeIfNecessary(r2);
        goto L21
    L41:
        r13 = false;
        removeChildDrawingOrderCallbackIfNecessary(r2.itemView);
        this.mCallback.clearView(this.mRecyclerView, r2);
        r0 = false;
        goto L42
    L5:
        if (r20 != this.mActionState) goto L7;
    }

    public boolean shouldSwipeBack() {
        return false;
    }

    public void startDrag(RecyclerView.ViewHolder r4) {
        if (this.mCallback.hasDragFlag(this.mRecyclerView, r4) == true) goto L7;
        Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
        return;
    L7:
        if (r4.itemView.getParent() == this.mRecyclerView) goto L10;
        Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
        return;
    L10:
        obtainVelocityTracker();
        this.mDy = 0.0f;
        this.mDx = 0.0f;
        select(r4, 2);
    }

    public void updateDxDy(MotionEvent r2, int r3, int r4) {
        float r0 = r2.getX(r4);
        float r22 = r2.getY(r4);
        float r02 = r0 - this.mInitialTouchX;
        this.mDx = r02;
        this.mDy = r22 - this.mInitialTouchY;
        if ((r3 & 4) != 0) goto L6;
        this.mDx = Math.max(0.0f, r02);
    L6:
        if ((r3 & 8) != 0) goto L9;
        this.mDx = Math.min(0.0f, this.mDx);
    L9:
        if ((r3 & 1) != 0) goto L12;
        this.mDy = Math.max(0.0f, this.mDy);
    L12:
        if ((r3 & 2) != 0) goto L15;
        this.mDy = Math.min(0.0f, this.mDy);
        return;
    }
}
