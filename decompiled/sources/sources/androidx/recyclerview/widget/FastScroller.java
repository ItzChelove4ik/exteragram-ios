package androidx.recyclerview.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;

/* loaded from: classes4.dex */
class FastScroller extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {
    private static final int[] EMPTY_STATE_SET = null;
    private static final int[] PRESSED_STATE_SET = null;
    int mAnimationState;
    private int mDragState;
    private final Runnable mHideRunnable;
    float mHorizontalDragX;
    private final int[] mHorizontalRange;
    int mHorizontalThumbCenterX;
    private final StateListDrawable mHorizontalThumbDrawable;
    private final int mHorizontalThumbHeight;
    int mHorizontalThumbWidth;
    private final Drawable mHorizontalTrackDrawable;
    private final int mHorizontalTrackHeight;
    private final int mMargin;
    private boolean mNeedHorizontalScrollbar;
    private boolean mNeedVerticalScrollbar;
    private final RecyclerView.OnScrollListener mOnScrollListener;
    private RecyclerView mRecyclerView;
    private int mRecyclerViewHeight;
    private int mRecyclerViewWidth;
    private final int mScrollbarMinimumRange;
    final ValueAnimator mShowHideAnimator;
    private int mState;
    float mVerticalDragY;
    private final int[] mVerticalRange;
    int mVerticalThumbCenterY;
    final StateListDrawable mVerticalThumbDrawable;
    int mVerticalThumbHeight;
    private final int mVerticalThumbWidth;
    final Drawable mVerticalTrackDrawable;
    private final int mVerticalTrackWidth;

    /* renamed from: androidx.recyclerview.widget.FastScroller$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ FastScroller this$0;

        public AnonymousClass1(FastScroller r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.hide(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        }
    }

    /* renamed from: androidx.recyclerview.widget.FastScroller$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ FastScroller this$0;

        public AnonymousClass2(FastScroller r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            this.this$0.updateScrollPosition(r1.computeHorizontalScrollOffset(), r1.computeVerticalScrollOffset());
        }
    }

    public class AnimatorListener extends AnimatorListenerAdapter {
        private boolean mCanceled;
        final /* synthetic */ FastScroller this$0;

        public AnimatorListener(FastScroller r1) {
            this.this$0 = r1;
            this.mCanceled = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.mCanceled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (this.mCanceled == false) goto L6;
            this.mCanceled = false;
            return;
        L6:
            float r32 = ((Float) this.this$0.mShowHideAnimator.getAnimatedValue()).floatValue();
            FastScroller r2 = this.this$0;
            if (r32 != 0.0f) goto L10;
            r2.mAnimationState = 0;
            r2.setState(0);
            return;
        L10:
            r2.mAnimationState = 2;
            r2.requestRedraw();
        }
    }

    public class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ FastScroller this$0;

        public AnimatorUpdater(FastScroller r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            int r22 = (int) (((Float) r2.getAnimatedValue()).floatValue() * 255.0f);
            this.this$0.mVerticalThumbDrawable.setAlpha(r22);
            this.this$0.mVerticalTrackDrawable.setAlpha(r22);
            this.this$0.requestRedraw();
        }
    }

    static {
        PRESSED_STATE_SET = new int[]{R.attr.state_pressed};
        EMPTY_STATE_SET = new int[0];
    }

    public FastScroller(RecyclerView r4, StateListDrawable r5, Drawable r6, StateListDrawable r7, Drawable r8, int r9, int r10, int r11) {
        this.mRecyclerViewWidth = 0;
        this.mRecyclerViewHeight = 0;
        this.mNeedVerticalScrollbar = false;
        this.mNeedHorizontalScrollbar = false;
        this.mState = 0;
        this.mDragState = 0;
        this.mVerticalRange = new int[2];
        this.mHorizontalRange = new int[2];
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mShowHideAnimator = r1;
        this.mAnimationState = 0;
        this.mHideRunnable = new AnonymousClass1(this);
        this.mOnScrollListener = new AnonymousClass2(this);
        this.mVerticalThumbDrawable = r5;
        this.mVerticalTrackDrawable = r6;
        this.mHorizontalThumbDrawable = r7;
        this.mHorizontalTrackDrawable = r8;
        this.mVerticalThumbWidth = Math.max(r9, r5.getIntrinsicWidth());
        this.mVerticalTrackWidth = Math.max(r9, r6.getIntrinsicWidth());
        this.mHorizontalThumbHeight = Math.max(r9, r7.getIntrinsicWidth());
        this.mHorizontalTrackHeight = Math.max(r9, r8.getIntrinsicWidth());
        this.mScrollbarMinimumRange = r10;
        this.mMargin = r11;
        r5.setAlpha(255);
        r6.setAlpha(255);
        r1.addListener(new AnimatorListener(this));
        r1.addUpdateListener(new AnimatorUpdater(this));
        attachToRecyclerView(r4);
    }

    private void cancelHide() {
        this.mRecyclerView.removeCallbacks(this.mHideRunnable);
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this);
        this.mRecyclerView.removeOnScrollListener(this.mOnScrollListener);
        cancelHide();
    }

    private void drawHorizontalScrollbar(Canvas r7) {
        int r0 = this.mRecyclerViewHeight;
        int r1 = this.mHorizontalThumbHeight;
        int r2 = this.mHorizontalThumbCenterX;
        int r3 = this.mHorizontalThumbWidth;
        this.mHorizontalThumbDrawable.setBounds(0, 0, r3, r1);
        this.mHorizontalTrackDrawable.setBounds(0, 0, this.mRecyclerViewWidth, this.mHorizontalTrackHeight);
        r7.translate(0.0f, r0 - r1);
        this.mHorizontalTrackDrawable.draw(r7);
        r7.translate(r2 - (r3 / 2), 0.0f);
        this.mHorizontalThumbDrawable.draw(r7);
        r7.translate(-r2, -r0);
    }

    private void drawVerticalScrollbar(Canvas r7) {
        int r0 = this.mRecyclerViewWidth;
        int r1 = this.mVerticalThumbWidth;
        int r02 = r0 - r1;
        int r2 = this.mVerticalThumbCenterY;
        int r3 = this.mVerticalThumbHeight;
        int r22 = r2 - (r3 / 2);
        this.mVerticalThumbDrawable.setBounds(0, 0, r1, r3);
        this.mVerticalTrackDrawable.setBounds(0, 0, this.mVerticalTrackWidth, this.mRecyclerViewHeight);
        if (isLayoutRTL() == false) goto L6;
        this.mVerticalTrackDrawable.draw(r7);
        r7.translate(this.mVerticalThumbWidth, r22);
        r7.scale(-1.0f, 1.0f);
        this.mVerticalThumbDrawable.draw(r7);
        r7.scale(1.0f, 1.0f);
        r7.translate(-this.mVerticalThumbWidth, -r22);
        return;
    L6:
        r7.translate(r02, 0.0f);
        this.mVerticalTrackDrawable.draw(r7);
        r7.translate(0.0f, r22);
        this.mVerticalThumbDrawable.draw(r7);
        r7.translate(-r02, -r22);
    }

    private int[] getHorizontalRange() {
        int[] r0 = this.mHorizontalRange;
        int r1 = this.mMargin;
        r0[0] = r1;
        r0[1] = this.mRecyclerViewWidth - r1;
        return r0;
    }

    private int[] getVerticalRange() {
        int[] r0 = this.mVerticalRange;
        int r1 = this.mMargin;
        r0[0] = r1;
        r0[1] = this.mRecyclerViewHeight - r1;
        return r0;
    }

    private void horizontalScrollTo(float r9) {
        int[] r3 = getHorizontalRange();
        float r2 = Math.max(r3[0], Math.min(r3[1], r9));
        if (Math.abs(this.mHorizontalThumbCenterX - r2) >= 2.0f) goto L5;
        return;
    L5:
        int r8 = scrollTo(this.mHorizontalDragX, r2, r3, this.mRecyclerView.computeHorizontalScrollRange(), this.mRecyclerView.computeHorizontalScrollOffset(), this.mRecyclerViewWidth);
        if (r8 == 0) goto L8;
        this.mRecyclerView.scrollBy(r8, 0);
    L8:
        this.mHorizontalDragX = r2;
    }

    private boolean isLayoutRTL() {
        if (ViewCompat.getLayoutDirection(this.mRecyclerView) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private void resetHideDelay(int r4) {
        cancelHide();
        this.mRecyclerView.postDelayed(this.mHideRunnable, r4);
    }

    private int scrollTo(float r2, float r3, int[] r4, int r5, int r6, int r7) {
        int r1 = r4[1] - r4[0];
        if (r1 != 0) goto L5;
        return 0;
    L5:
        int r52 = r5 - r7;
        int r12 = (int) (((r3 - r2) / r1) * r52);
        int r62 = r6 + r12;
        if (r62 >= r52) goto L9;
        if (r62 < 0) goto L9;
        return r12;
    L9:
        return 0;
    }

    private void setupCallbacks() {
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this);
        this.mRecyclerView.addOnScrollListener(this.mOnScrollListener);
    }

    private void verticalScrollTo(float r9) {
        int[] r3 = getVerticalRange();
        float r2 = Math.max(r3[0], Math.min(r3[1], r9));
        if (Math.abs(this.mVerticalThumbCenterY - r2) >= 2.0f) goto L5;
        return;
    L5:
        int r8 = scrollTo(this.mVerticalDragY, r2, r3, this.mRecyclerView.computeVerticalScrollRange(), this.mRecyclerView.computeVerticalScrollOffset(), this.mRecyclerViewHeight);
        if (r8 == 0) goto L8;
        this.mRecyclerView.scrollBy(0, r8);
    L8:
        this.mVerticalDragY = r2;
    }

    public void attachToRecyclerView(RecyclerView r2) {
        RecyclerView r0 = this.mRecyclerView;
        if (r0 == r2) goto L12;
        if (r0 == null) goto L7;
        destroyCallbacks();
    L7:
        this.mRecyclerView = r2;
        if (r2 == null) goto L11;
        setupCallbacks();
        return;
    L11:
        return;
    }

    public void hide(int r6) {
        int r0 = this.mAnimationState;
        if (r0 == 1) goto L6;
        if (r0 == 2) goto L7;
        return;
    L7:
        this.mAnimationState = 3;
        ValueAnimator r02 = this.mShowHideAnimator;
        r02.setFloatValues(new float[]{((Float) r02.getAnimatedValue()).floatValue(), 0.0f});
        this.mShowHideAnimator.setDuration(r6);
        this.mShowHideAnimator.start();
        return;
    L6:
        this.mShowHideAnimator.cancel();
        goto L7
    }

    public boolean isPointInsideHorizontalThumb(float r3, float r4) {
        if (r4 < (this.mRecyclerViewHeight - this.mHorizontalThumbHeight)) goto L10;
        int r42 = this.mHorizontalThumbCenterX;
        int r2 = this.mHorizontalThumbWidth;
        if (r3 >= (r42 - (r2 / 2))) goto L7;
        return false;
    L7:
        if (r3 > (r42 + (r2 / 2))) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean isPointInsideVerticalThumb(float r3, float r4) {
        if (isLayoutRTL() == false) goto L8;
        if (r3 <= (this.mVerticalThumbWidth / 2)) goto L9;
        return false;
    L9:
        int r32 = this.mVerticalThumbCenterY;
        int r2 = this.mVerticalThumbHeight;
        if (r4 >= (r32 - (r2 / 2))) goto L12;
        return false;
    L12:
        if (r4 > (r32 + (r2 / 2))) goto L19;
        return true;
    L19:
        return false;
    L8:
        if (r3 >= (this.mRecyclerViewWidth - this.mVerticalThumbWidth)) goto L9;
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas r1, RecyclerView r2, RecyclerView.State r3) {
        if (this.mRecyclerViewWidth == this.mRecyclerView.getWidth()) goto L5;
    L16:
        this.mRecyclerViewWidth = this.mRecyclerView.getWidth();
        this.mRecyclerViewHeight = this.mRecyclerView.getHeight();
        setState(0);
        return;
    L5:
        if (this.mRecyclerViewHeight != this.mRecyclerView.getHeight()) goto L16;
        if (this.mAnimationState != 0) goto L10;
        return;
    L10:
        if (this.mNeedVerticalScrollbar == false) goto L13;
        drawVerticalScrollbar(r1);
    L13:
        if (this.mNeedHorizontalScrollbar == false) goto L19;
        drawHorizontalScrollbar(r1);
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView r6, MotionEvent r7) {
        int r62 = this.mState;
        if (r62 != 1) goto L15;
        boolean r63 = isPointInsideVerticalThumb(r7.getX(), r7.getY());
        boolean r3 = isPointInsideHorizontalThumb(r7.getX(), r7.getY());
        if (r7.getAction() != 0) goto L14;
        if (r63 == true) goto L8;
        if (r3 == false) goto L14;
    L8:
        if (r3 == false) goto L10;
        this.mDragState = 1;
        this.mHorizontalDragX = (int) r7.getX();
    L12:
        setState(2);
        return true;
    L10:
        if (r63 == false) goto L12;
        this.mDragState = 2;
        this.mVerticalDragY = (int) r7.getY();
    L14:
        return false;
    L15:
        if (r62 != 2) goto L17;
        return true;
    L17:
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean r1) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView r5, MotionEvent r6) {
        if (this.mState != 0) goto L6;
        return;
    L6:
        if (r6.getAction() != 0) goto L17;
        boolean r52 = isPointInsideVerticalThumb(r6.getX(), r6.getY());
        boolean r2 = isPointInsideHorizontalThumb(r6.getX(), r6.getY());
        if (r52 == true) goto L10;
        if (r2 == true) goto L10;
        return;
    L10:
        if (r2 == false) goto L12;
        this.mDragState = 1;
        this.mHorizontalDragX = (int) r6.getX();
    L14:
        setState(2);
        return;
    L12:
        if (r52 == false) goto L14;
        this.mDragState = 2;
        this.mVerticalDragY = (int) r6.getY();
        goto L14
    L17:
        if (r6.getAction() != 1) goto L23;
        if (this.mState != 2) goto L23;
        this.mVerticalDragY = 0.0f;
        this.mHorizontalDragX = 0.0f;
        setState(1);
        this.mDragState = 0;
        return;
    L23:
        if (r6.getAction() == 2) goto L25;
        return;
    L25:
        if (this.mState != 2) goto L35;
        show();
        if (this.mDragState != 1) goto L30;
        horizontalScrollTo(r6.getX());
    L30:
        if (this.mDragState != 2) goto L36;
        verticalScrollTo(r6.getY());
        return;
    L36:
        return;
    }

    public void requestRedraw() {
        this.mRecyclerView.invalidate();
    }

    public void setState(int r4) {
        if (r4 == 2) goto L5;
    L7:
        if (r4 != 0) goto L9;
        requestRedraw();
    L11:
        if (this.mState != 2) goto L15;
        if (r4 == 2) goto L15;
        this.mVerticalThumbDrawable.setState(EMPTY_STATE_SET);
        resetHideDelay(1200);
    L17:
        this.mState = r4;
        return;
    L15:
        if (r4 != 1) goto L17;
        resetHideDelay(1500);
        goto L17
    L9:
        show();
        goto L11
    L5:
        if (this.mState == 2) goto L7;
        this.mVerticalThumbDrawable.setState(PRESSED_STATE_SET);
        cancelHide();
        goto L7
    }

    public void show() {
        int r0 = this.mAnimationState;
        if (r0 != 0) goto L5;
    L8:
        this.mAnimationState = 1;
        ValueAnimator r1 = this.mShowHideAnimator;
        r1.setFloatValues(new float[]{((Float) r1.getAnimatedValue()).floatValue(), 1.0f});
        this.mShowHideAnimator.setDuration(500);
        this.mShowHideAnimator.setStartDelay(0);
        this.mShowHideAnimator.start();
        return;
    L5:
        if (r0 == 3) goto L7;
        return;
    L7:
        this.mShowHideAnimator.cancel();
        goto L8
    }

    public void updateScrollPosition(int r9, int r10) {
        int r0 = this.mRecyclerView.computeVerticalScrollRange();
        int r1 = this.mRecyclerViewHeight;
        if ((r0 - r1) > 0) goto L5;
    L7:
        boolean r2 = false;
    L8:
        this.mNeedVerticalScrollbar = r2;
        int r22 = this.mRecyclerView.computeHorizontalScrollRange();
        int r5 = this.mRecyclerViewWidth;
        if ((r22 - r5) > 0) goto L11;
    L13:
        boolean r6 = false;
    L14:
        this.mNeedHorizontalScrollbar = r6;
        boolean r7 = this.mNeedVerticalScrollbar;
        if (r7 == true) goto L22;
        if (r6 == true) goto L22;
        if (this.mState == 0) goto L31;
        setState(0);
        return;
    L31:
        return;
    L22:
        if (r7 == false) goto L25;
        float r62 = r1;
        this.mVerticalThumbCenterY = (int) ((r62 * (r10 + (r62 / 2.0f))) / r0);
        this.mVerticalThumbHeight = Math.min(r1, (r1 * r1) / r0);
    L25:
        if (this.mNeedHorizontalScrollbar == false) goto L27;
        float r102 = r5;
        this.mHorizontalThumbCenterX = (int) ((r102 * (r9 + (r102 / 2.0f))) / r22);
        this.mHorizontalThumbWidth = Math.min(r5, (r5 * r5) / r22);
    L27:
        int r92 = this.mState;
        if (r92 == 0) goto L32;
        if (r92 == 1) goto L32;
        return;
    L32:
        setState(1);
        return;
    L11:
        if (r5 < this.mScrollbarMinimumRange) goto L13;
        r6 = true;
        goto L14
    L5:
        if (r1 < this.mScrollbarMinimumRange) goto L7;
        r2 = true;
        goto L8
    }
}
