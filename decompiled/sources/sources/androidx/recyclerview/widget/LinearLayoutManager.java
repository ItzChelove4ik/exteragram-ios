package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.LiteMode;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.LayoutManager implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider {
    static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    private int addExtraLayoutSpace;
    final AnchorInfo mAnchorInfo;
    private boolean mDisableScroll;
    public boolean mIgnoreTopPadding;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final LayoutChunkResult mLayoutChunkResult;
    private LayoutState mLayoutState;
    int mOrientation;
    OrientationHelper mOrientationHelper;
    SavedState mPendingSavedState;
    int mPendingScrollPosition;
    boolean mPendingScrollPositionBottom;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private int[] mReusableIntPair;
    private boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;
    private boolean needFixEndGap;
    private boolean needFixGap;

    public static class AnchorInfo {
        int mCoordinate;
        boolean mLayoutFromEnd;
        OrientationHelper mOrientationHelper;
        int mPosition;
        boolean mValid;

        public AnchorInfo() {
            reset();
        }

        public void assignCoordinateFromPadding() {
            boolean r0 = this.mLayoutFromEnd;
            OrientationHelper r1 = this.mOrientationHelper;
            if (r0 == false) goto L5;
            int r02 = r1.getEndAfterPadding();
        L6:
            this.mCoordinate = r02;
            return;
        L5:
            r02 = r1.getStartAfterPadding();
            goto L6
        }

        public void assignFromView(View r3, int r4) {
            boolean r0 = this.mLayoutFromEnd;
            OrientationHelper r1 = this.mOrientationHelper;
            if (r0 == false) goto L5;
            this.mCoordinate = r1.getDecoratedEnd(r3) + this.mOrientationHelper.getTotalSpaceChange();
        L6:
            this.mPosition = r4;
            return;
        L5:
            this.mCoordinate = r1.getDecoratedStart(r3);
            goto L6
        }

        public void assignFromViewAndKeepVisibleRect(View r5, int r6) {
            int r0 = this.mOrientationHelper.getTotalSpaceChange();
            if (r0 < 0) goto L6;
            assignFromView(r5, r6);
            return;
        L6:
            this.mPosition = r6;
            boolean r62 = this.mLayoutFromEnd;
            OrientationHelper r1 = this.mOrientationHelper;
            if (r62 == false) goto L14;
            int r63 = (r1.getEndAfterPadding() - r0) - this.mOrientationHelper.getDecoratedEnd(r5);
            this.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - r63;
            if (r63 <= 0) goto L20;
            int r12 = this.mCoordinate - this.mOrientationHelper.getDecoratedMeasurement(r5);
            int r02 = this.mOrientationHelper.getStartAfterPadding();
            int r13 = r12 - (r02 + Math.min(this.mOrientationHelper.getDecoratedStart(r5) - r02, 0));
            if (r13 >= 0) goto L21;
            this.mCoordinate += Math.min(r63, -r13);
            return;
        L21:
            return;
        L20:
            return;
        L14:
            int r64 = r1.getDecoratedStart(r5);
            int r14 = r64 - this.mOrientationHelper.getStartAfterPadding();
            this.mCoordinate = r64;
            if (r14 <= 0) goto L22;
            int r52 = (this.mOrientationHelper.getEndAfterPadding() - Math.min(0, (this.mOrientationHelper.getEndAfterPadding() - r0) - this.mOrientationHelper.getDecoratedEnd(r5))) - (r64 + this.mOrientationHelper.getDecoratedMeasurement(r5));
            if (r52 >= 0) goto L23;
            this.mCoordinate -= Math.min(r14, -r52);
            return;
        L23:
            return;
        }

        public boolean isViewValidAsAnchor(View r1, RecyclerView.State r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r1.getLayoutParams();
            if (r0.isItemRemoved() == false) goto L5;
            return false;
        L5:
            if (r0.getViewLayoutPosition() >= 0) goto L7;
            return false;
        L7:
            if (r0.getViewLayoutPosition() >= r2.getItemCount()) goto L13;
            return true;
        L13:
            return false;
        }

        public void reset() {
            this.mPosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mValid = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + '}';
        }
    }

    public static class LayoutChunkResult {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        public LayoutChunkResult() {
        }

        public void resetInternal() {
            this.mConsumed = 0;
            this.mFinished = false;
            this.mIgnoreConsumed = false;
            this.mFocusable = false;
        }
    }

    public static class LayoutState {
        int mAvailable;
        int mCurrentPosition;
        int mExtraFillSpace;
        boolean mInfinite;
        boolean mIsPreLayout;
        int mItemDirection;
        int mLastScrollDelta;
        int mLayoutDirection;
        int mNoRecycleSpace;
        int mOffset;
        boolean mRecycle;
        List<RecyclerView.ViewHolder> mScrapList;
        int mScrollingOffset;

        public LayoutState() {
            this.mRecycle = true;
            this.mExtraFillSpace = 0;
            this.mNoRecycleSpace = 0;
            this.mIsPreLayout = false;
            this.mScrapList = null;
        }

        private View nextViewFromScrapList() {
            int r0 = this.mScrapList.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L12;
            View r2 = this.mScrapList.get(r1).itemView;
            RecyclerView.LayoutParams r3 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            if (r3.isItemRemoved() == true) goto L11;
            if (this.mCurrentPosition != r3.getViewLayoutPosition()) goto L11;
            assignPositionFromScrapList(r2);
            return r2;
        L11:
            r1 = r1 + 1;
            goto L3
        L12:
            return null;
        }

        public void assignPositionFromScrapList() {
            assignPositionFromScrapList(null);
        }

        public void assignPositionFromScrapList(View r1) {
            View r12 = nextViewInLimitedList(r1);
            if (r12 != null) goto L6;
            this.mCurrentPosition = -1;
            return;
        L6:
            this.mCurrentPosition = ((RecyclerView.LayoutParams) r12.getLayoutParams()).getViewLayoutPosition();
        }

        public boolean hasMore(RecyclerView.State r1) {
            int r0 = this.mCurrentPosition;
            if (r0 >= 0) goto L5;
            return false;
        L5:
            if (r0 >= r1.getItemCount()) goto L10;
            return true;
        L10:
            return false;
        }

        public View next(RecyclerView.Recycler r3) {
            if (this.mScrapList != null) goto L5;
            View r32 = r3.getViewForPosition(this.mCurrentPosition);
            this.mCurrentPosition += this.mItemDirection;
            return r32;
        L5:
            return nextViewFromScrapList();
        }

        public View nextViewInLimitedList(View r8) {
            int r0 = this.mScrapList.size();
            View r1 = null;
            int r2 = Integer.MAX_VALUE;
            int r3 = 0;
        L3:
            if (r3 >= r0) goto L17;
            View r4 = this.mScrapList.get(r3).itemView;
            RecyclerView.LayoutParams r5 = (RecyclerView.LayoutParams) r4.getLayoutParams();
            if (r4 == r8) goto L16;
            if (r5.isItemRemoved() == true) goto L16;
            int r52 = (r5.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection;
            if (r52 < 0) goto L16;
            if (r52 >= r2) goto L16;
            if (r52 == 0) goto L14;
            r1 = r4;
            r2 = r52;
            goto L16
        L14:
            return r4;
        L16:
            r3 = r3 + 1;
            goto L3
        L17:
            return r1;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes4.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        boolean mAnchorLayoutFromEnd;
        int mAnchorOffset;
        int mAnchorPosition;

        /* renamed from: androidx.recyclerview.widget.LinearLayoutManager$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<SavedState> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r1) {
                return new SavedState(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState() {
        }

        public SavedState(Parcel r2) {
            this.mAnchorPosition = r2.readInt();
            this.mAnchorOffset = r2.readInt();
            boolean r0 = true;
            if (r2.readInt() == 1) goto L6;
            r0 = false;
        L6:
            this.mAnchorLayoutFromEnd = r0;
        }

        public SavedState(SavedState r2) {
            this.mAnchorPosition = r2.mAnchorPosition;
            this.mAnchorOffset = r2.mAnchorOffset;
            this.mAnchorLayoutFromEnd = r2.mAnchorLayoutFromEnd;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean hasValidAnchor() {
            if (this.mAnchorPosition < 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            r1.writeInt(this.mAnchorPosition);
            r1.writeInt(this.mAnchorOffset);
            r1.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
        }
    }

    public LinearLayoutManager(Context r3) {
        this(r3, 1, false);
    }

    public LinearLayoutManager(Context r2, int r3, boolean r4) {
        this.mOrientation = 1;
        this.mIgnoreTopPadding = false;
        this.mReverseLayout = false;
        this.mDisableScroll = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionBottom = true;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        this.needFixGap = true;
        this.needFixEndGap = true;
        setOrientation(r3);
        setReverseLayout(r4);
    }

    private int computeScrollExtent(RecyclerView.State r7) {
        if (getChildCount() != 0) goto L6;
        return 0;
    L6:
        ensureLayoutState();
        return ScrollbarHelper.computeScrollExtent(r7, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.State r8) {
        if (getChildCount() != 0) goto L6;
        return 0;
    L6:
        ensureLayoutState();
        return ScrollbarHelper.computeScrollOffset(r8, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.State r7) {
        if (getChildCount() != 0) goto L6;
        return 0;
    L6:
        ensureLayoutState();
        return ScrollbarHelper.computeScrollRange(r7, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private View findFirstPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
    }

    private View findFirstReferenceChild(RecyclerView.Recycler r7, RecyclerView.State r8) {
        return findReferenceChild(r7, r8, 0, getChildCount(), r8.getItemCount());
    }

    private View findLastPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
    }

    private View findLastReferenceChild(RecyclerView.Recycler r8, RecyclerView.State r9) {
        return findReferenceChild(r8, r9, getChildCount() - 1, -1, r9.getItemCount());
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToEnd() {
        if (this.mShouldReverseLayout == false) goto L7;
        return findFirstPartiallyOrCompletelyInvisibleChild();
    L7:
        return findLastPartiallyOrCompletelyInvisibleChild();
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToStart() {
        if (this.mShouldReverseLayout == false) goto L7;
        return findLastPartiallyOrCompletelyInvisibleChild();
    L7:
        return findFirstPartiallyOrCompletelyInvisibleChild();
    }

    private View findReferenceChildClosestToEnd(RecyclerView.Recycler r2, RecyclerView.State r3) {
        if (this.mShouldReverseLayout == false) goto L7;
        return findFirstReferenceChild(r2, r3);
    L7:
        return findLastReferenceChild(r2, r3);
    }

    private View findReferenceChildClosestToStart(RecyclerView.Recycler r2, RecyclerView.State r3) {
        if (this.mShouldReverseLayout == false) goto L7;
        return findLastReferenceChild(r2, r3);
    L7:
        return findFirstReferenceChild(r2, r3);
    }

    private int fixLayoutEndGap(int r3, RecyclerView.Recycler r4, RecyclerView.State r5, boolean r6) {
        if (this.needFixGap == true) goto L5;
    L16:
        return 0;
    L5:
        if (this.needFixEndGap == false) goto L16;
        int r0 = this.mOrientationHelper.getEndAfterPadding() - r3;
        if (r0 <= 0) goto L16;
        int r42 = -scrollBy(-r0, r4, r5);
        int r32 = r3 + r42;
        if (r6 == false) goto L15;
        int r52 = this.mOrientationHelper.getEndAfterPadding() - r32;
        if (r52 <= 0) goto L15;
        this.mOrientationHelper.offsetChildren(r52);
        return r52 + r42;
    L15:
        return r42;
    }

    private int fixLayoutStartGap(int r3, RecyclerView.Recycler r4, RecyclerView.State r5, boolean r6) {
        if (this.needFixGap == true) goto L5;
        return 0;
    L5:
        int r0 = r3 - getStartForFixGap();
        if (r0 <= 0) goto L13;
        int r42 = -scrollBy(r0, r4, r5);
        int r32 = r3 + r42;
        if (r6 == false) goto L14;
        int r33 = r32 - this.mOrientationHelper.getStartAfterPadding();
        if (r33 <= 0) goto L15;
        this.mOrientationHelper.offsetChildren(-r33);
        return r42 - r33;
    L15:
        return r42;
    L14:
        return r42;
    L13:
        return 0;
    }

    private View getChildClosestToEnd() {
        if (this.mShouldReverseLayout == false) goto L5;
        int r0 = 0;
    L7:
        return getChildAt(r0);
    L5:
        r0 = getChildCount() - 1;
        goto L7
    }

    private View getChildClosestToStart() {
        if (this.mShouldReverseLayout == false) goto L5;
        int r0 = getChildCount() - 1;
    L7:
        return getChildAt(r0);
    L5:
        r0 = 0;
        goto L7
    }

    private void layoutForPredictiveAnimations(RecyclerView.Recycler r12, RecyclerView.State r13, int r14, int r15) {
        if (r13.willRunPredictiveAnimations() == true) goto L5;
        return;
    L5:
        if (getChildCount() != 0) goto L7;
        return;
    L7:
        if (r13.isPreLayout() == false) goto L9;
        return;
    L9:
        if (supportsPredictiveItemAnimations() == false) goto L39;
        List<RecyclerView.ViewHolder> r0 = r12.getScrapList();
        int r1 = r0.size();
        int r3 = getPosition(getChildAt(0));
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
    L12:
        if (r4 >= r1) goto L25;
        RecyclerView.ViewHolder r7 = r0.get(r4);
        if (r7.isRemoved() == true) goto L24;
        if (r7.getLayoutPosition() >= r3) goto L19;
        boolean r8 = true;
    L20:
        boolean r9 = this.mShouldReverseLayout;
        OrientationHelper r10 = this.mOrientationHelper;
        if (r8 == r9) goto L23;
        r5 = r5 + r10.getDecoratedMeasurement(r7.itemView);
        goto L24
    L23:
        r6 = r6 + r10.getDecoratedMeasurement(r7.itemView);
        goto L24
    L19:
        r8 = false;
    L24:
        r4 = r4 + 1;
        goto L12
    L25:
        this.mLayoutState.mScrapList = r0;
        if (r5 <= 0) goto L28;
        updateLayoutStateToFillStart(getPosition(getChildClosestToStart()), r14);
        LayoutState r142 = this.mLayoutState;
        r142.mExtraFillSpace = r5;
        r142.mAvailable = 0;
        r142.assignPositionFromScrapList();
        fill(r12, this.mLayoutState, r13, false);
    L28:
        if (r6 <= 0) goto L30;
        updateLayoutStateToFillEnd(getPosition(getChildClosestToEnd()), r15);
        LayoutState r143 = this.mLayoutState;
        r143.mExtraFillSpace = r6;
        r143.mAvailable = 0;
        r143.assignPositionFromScrapList();
        fill(r12, this.mLayoutState, r13, false);
    L30:
        this.mLayoutState.mScrapList = null;
        return;
    }

    private void logChildren() {
        Log.d(TAG, "internal representation of views on the screen");
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L6;
        View r2 = getChildAt(r0);
        Log.d(TAG, "item " + getPosition(r2) + ", coord:" + this.mOrientationHelper.getDecoratedStart(r2));
        r0 = r0 + 1;
        goto L4
    L6:
        Log.d(TAG, "==============");
    }

    private void recycleByLayoutState(RecyclerView.Recycler r4, LayoutState r5) {
        if (r5.mRecycle == true) goto L5;
        return;
    L5:
        if (r5.mInfinite == true) goto L14;
        int r0 = r5.mScrollingOffset;
        int r1 = r5.mNoRecycleSpace;
        if (r5.mLayoutDirection != (-1)) goto L11;
        recycleViewsFromEnd(r4, r0, r1);
        return;
    L11:
        recycleViewsFromStart(r4, r0, r1);
        return;
    }

    private void recycleViewsFromEnd(RecyclerView.Recycler r5, int r6, int r7) {
        int r0 = getChildCount();
        if (r6 < 0) goto L39;
        int r1 = (this.mOrientationHelper.getEnd() - r6) + r7;
        if (this.mShouldReverseLayout == false) goto L23;
        int r72 = 0;
    L8:
        if (r72 >= r0) goto L54;
        View r2 = getChildAt(r72);
        if (r2 == null) goto L22;
        RecyclerView.ViewHolder r3 = this.mRecyclerView.getChildViewHolder(r2);
        if (r3 == null) goto L22;
        if (r3.shouldIgnore() == true) goto L22;
        if (this.mOrientationHelper.getDecoratedStart(r2) < r1) goto L20;
        if (this.mOrientationHelper.getTransformedStartWithDecoration(r2) >= r1) goto L22;
    L20:
        recycleChildren(r5, 0, r72);
        return;
    L22:
        r72 = r72 + 1;
        goto L8
    L54:
        return;
    L23:
        int r02 = r0 - 1;
        int r62 = r02;
    L24:
        if (r62 < 0) goto L55;
        View r73 = getChildAt(r62);
        if (r73 == null) goto L38;
        RecyclerView.ViewHolder r22 = this.mRecyclerView.getChildViewHolder(r73);
        if (r22 == null) goto L38;
        if (r22.shouldIgnore() == true) goto L38;
        if (this.mOrientationHelper.getDecoratedStart(r73) < r1) goto L36;
        if (this.mOrientationHelper.getTransformedStartWithDecoration(r73) >= r1) goto L38;
    L36:
        recycleChildren(r5, r02, r62);
        return;
    L38:
        r62 = r62 - 1;
        goto L24
    L55:
        return;
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation != 1) goto L5;
    L9:
        this.mShouldReverseLayout = this.mReverseLayout;
        return;
    L5:
        if (isLayoutRTL() == false) goto L9;
        this.mShouldReverseLayout = !this.mReverseLayout;
    }

    private boolean updateAnchorFromChildren(RecyclerView.Recycler r5, RecyclerView.State r6, AnchorInfo r7) {
        if (getChildCount() != 0) goto L5;
        return false;
    L5:
        View r0 = getFocusedChild();
        if (r0 == null) goto L12;
        if (r7.isViewValidAsAnchor(r0, r6) == false) goto L12;
        r7.assignFromViewAndKeepVisibleRect(r0, getPosition(r0));
        return true;
    L12:
        if (this.mLastStackFromEnd == this.mStackFromEnd) goto L15;
        return false;
    L15:
        if (r7.mLayoutFromEnd == false) goto L17;
        View r52 = findReferenceChildClosestToEnd(r5, r6);
    L18:
        if (r52 == null) goto L33;
        r7.assignFromView(r52, getPosition(r52));
        if (r6.isPreLayout() == false) goto L22;
    L32:
        return true;
    L22:
        if (supportsPredictiveItemAnimations() == false) goto L32;
        if (this.mOrientationHelper.getDecoratedStart(r52) < this.mOrientationHelper.getEndAfterPadding()) goto L26;
    L27:
        boolean r53 = r7.mLayoutFromEnd;
        OrientationHelper r4 = this.mOrientationHelper;
        if (r53 == false) goto L30;
        int r42 = r4.getEndAfterPadding();
    L31:
        r7.mCoordinate = r42;
        goto L32
    L30:
        r42 = r4.getStartAfterPadding();
        goto L31
    L26:
        if (this.mOrientationHelper.getDecoratedEnd(r52) >= this.mOrientationHelper.getStartAfterPadding()) goto L32;
    L33:
        return false;
    L17:
        r52 = findReferenceChildClosestToStart(r5, r6);
        goto L18
    }

    private boolean updateAnchorFromPendingData(RecyclerView.State r5, AnchorInfo r6) {
        boolean r1 = false;
        if (r5.isPreLayout() == true) goto L60;
        int r0 = this.mPendingScrollPosition;
        if (r0 == (-1)) goto L60;
        if (r0 >= 0) goto L10;
    L59:
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        goto L60
    L10:
        if (r0 >= r5.getItemCount()) goto L59;
        r6.mPosition = this.mPendingScrollPosition;
        SavedState r52 = this.mPendingSavedState;
        if (r52 == null) goto L22;
        if (r52.hasValidAnchor() == false) goto L22;
        boolean r53 = this.mPendingSavedState.mAnchorLayoutFromEnd;
        r6.mLayoutFromEnd = r53;
        OrientationHelper r12 = this.mOrientationHelper;
        if (r53 == false) goto L19;
        r6.mCoordinate = r12.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
    L20:
        return true;
    L19:
        r6.mCoordinate = r12.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
    L22:
        if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) goto L54;
        View r54 = findViewByPosition(this.mPendingScrollPosition);
        if (r54 == null) goto L43;
        if (this.mOrientationHelper.getDecoratedMeasurement(r54) <= this.mOrientationHelper.getTotalSpace()) goto L29;
        r6.assignCoordinateFromPadding();
        return true;
    L29:
        int r2 = this.mOrientationHelper.getDecoratedStart(r54) - this.mOrientationHelper.getStartAfterPadding();
        OrientationHelper r3 = this.mOrientationHelper;
        if (r2 >= 0) goto L34;
        r6.mCoordinate = r3.getStartAfterPadding();
        r6.mLayoutFromEnd = false;
        return true;
    L34:
        if ((r3.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(r54)) >= 0) goto L37;
        r6.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
        r6.mLayoutFromEnd = true;
        return true;
    L37:
        boolean r13 = r6.mLayoutFromEnd;
        OrientationHelper r22 = this.mOrientationHelper;
        if (r13 == false) goto L40;
        int r55 = r22.getDecoratedEnd(r54) + this.mOrientationHelper.getTotalSpaceChange();
    L41:
        r6.mCoordinate = r55;
    L53:
        return true;
    L40:
        r55 = r22.getDecoratedStart(r54);
        goto L41
    L43:
        if (getChildCount() > 0) goto L45;
    L52:
        r6.assignCoordinateFromPadding();
        goto L53
    L45:
        if (this.mPendingScrollPosition >= getPosition(getChildAt(0))) goto L47;
        boolean r56 = true;
    L49:
        if (r56 != this.mPendingScrollPositionBottom) goto L51;
        r1 = true;
    L51:
        r6.mLayoutFromEnd = r1;
        goto L52
    L47:
        r56 = false;
        goto L49
    L54:
        boolean r57 = this.mPendingScrollPositionBottom;
        r6.mLayoutFromEnd = r57;
        OrientationHelper r14 = this.mOrientationHelper;
        if (r57 == false) goto L57;
        r6.mCoordinate = r14.getEndAfterPadding() - this.mPendingScrollPositionOffset;
    L58:
        return true;
    L57:
        r6.mCoordinate = r14.getStartAfterPadding() + this.mPendingScrollPositionOffset;
    L60:
        return false;
    }

    private void updateAnchorInfoForLayout(RecyclerView.Recycler r2, RecyclerView.State r3, AnchorInfo r4) {
        if (updateAnchorFromPendingData(r3, r4) == false) goto L6;
        return;
    L6:
        if (updateAnchorFromChildren(r2, r3, r4) == false) goto L8;
        return;
    L8:
        r4.assignCoordinateFromPadding();
        if (this.mStackFromEnd == false) goto L11;
        int r1 = r3.getItemCount() - 1;
    L12:
        r4.mPosition = r1;
        return;
    L11:
        r1 = firstPosition();
        goto L12
    }

    private void updateLayoutState(int r5, int r6, boolean r7, RecyclerView.State r8) {
        this.mLayoutState.mInfinite = resolveIsInfinite();
        this.mLayoutState.mLayoutDirection = r5;
        int[] r0 = this.mReusableIntPair;
        boolean r1 = false;
        r0[0] = 0;
        int r2 = 1;
        r0[1] = 0;
        calculateExtraLayoutSpace(r8, r0);
        int r82 = Math.max(0, this.mReusableIntPair[0]);
        int r02 = Math.max(0, this.mReusableIntPair[1]);
        if (r5 != 1) goto L5;
        r1 = true;
    L5:
        LayoutState r52 = this.mLayoutState;
        if (r1 == false) goto L8;
        int r3 = r02;
    L9:
        r52.mExtraFillSpace = r3;
        if (r1 == true) goto L13;
        r82 = r02;
    L13:
        r52.mNoRecycleSpace = r82;
        if (r1 == false) goto L19;
        r52.mExtraFillSpace = r3 + this.mOrientationHelper.getEndPadding();
        View r53 = getChildClosestToEnd();
        LayoutState r03 = this.mLayoutState;
        if (this.mShouldReverseLayout == false) goto L18;
        r2 = -1;
    L18:
        r03.mItemDirection = r2;
        int r83 = getPosition(r53);
        LayoutState r12 = this.mLayoutState;
        r03.mCurrentPosition = r83 + r12.mItemDirection;
        r12.mOffset = this.mOrientationHelper.getDecoratedEnd(r53);
        int r54 = this.mOrientationHelper.getDecoratedEnd(r53) - this.mOrientationHelper.getEndAfterPadding();
    L24:
        LayoutState r4 = this.mLayoutState;
        r4.mAvailable = r6;
        if (r7 == false) goto L27;
        r4.mAvailable = r6 - r54;
    L27:
        r4.mScrollingOffset = r54;
        return;
    L19:
        View r55 = getChildClosestToStart();
        this.mLayoutState.mExtraFillSpace += this.mOrientationHelper.getStartAfterPadding();
        LayoutState r04 = this.mLayoutState;
        if (this.mShouldReverseLayout == true) goto L23;
        r2 = -1;
    L23:
        r04.mItemDirection = r2;
        int r84 = getPosition(r55);
        LayoutState r13 = this.mLayoutState;
        r04.mCurrentPosition = r84 + r13.mItemDirection;
        r13.mOffset = this.mOrientationHelper.getDecoratedStart(r55);
        r54 = (-this.mOrientationHelper.getDecoratedStart(r55)) + this.mOrientationHelper.getStartAfterPadding();
        goto L24
    L8:
        r3 = r82;
        goto L9
    }

    private void updateLayoutStateToFillEnd(int r3, int r4) {
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - r4;
        LayoutState r0 = this.mLayoutState;
        if (this.mShouldReverseLayout == false) goto L5;
        int r2 = -1;
    L6:
        r0.mItemDirection = r2;
        r0.mCurrentPosition = r3;
        r0.mLayoutDirection = 1;
        r0.mOffset = r4;
        r0.mScrollingOffset = Integer.MIN_VALUE;
        return;
    L5:
        r2 = 1;
        goto L6
    }

    private void updateLayoutStateToFillEnd(AnchorInfo r2) {
        updateLayoutStateToFillEnd(r2.mPosition, r2.mCoordinate);
    }

    private void updateLayoutStateToFillStart(int r3, int r4) {
        this.mLayoutState.mAvailable = r4 - this.mOrientationHelper.getStartAfterPadding();
        LayoutState r0 = this.mLayoutState;
        r0.mCurrentPosition = r3;
        if (this.mShouldReverseLayout == false) goto L5;
        int r2 = 1;
    L6:
        r0.mItemDirection = r2;
        r0.mLayoutDirection = -1;
        r0.mOffset = r4;
        r0.mScrollingOffset = Integer.MIN_VALUE;
        return;
    L5:
        r2 = -1;
        goto L6
    }

    private void updateLayoutStateToFillStart(AnchorInfo r2) {
        updateLayoutStateToFillStart(r2.mPosition, r2.mCoordinate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void assertNotInLayoutOrScroll(String r2) {
        if (this.mPendingSavedState != null) goto L6;
        super.assertNotInLayoutOrScroll(r2);
        return;
    }

    public void calculateExtraLayoutSpace(RecyclerView.State r3, int[] r4) {
        int r32 = getExtraLayoutSpace(r3);
        if (this.mLayoutState.mLayoutDirection != (-1)) goto L5;
        int r2 = 0;
    L6:
        r4[0] = r32;
        r4[1] = r2;
        return;
    L5:
        r2 = r32;
        r32 = 0;
        goto L6
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        if (this.mDisableScroll == false) goto L5;
        return false;
    L5:
        if (this.mOrientation != 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        if (this.mDisableScroll == false) goto L5;
        return false;
    L5:
        if (this.mOrientation != 1) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectAdjacentPrefetchPositions(int r2, int r3, RecyclerView.State r4, RecyclerView.LayoutManager.LayoutPrefetchRegistry r5) {
        if (this.mOrientation == 0) goto L7;
        r2 = r3;
    L7:
        if (getChildCount() == 0) goto L16;
        if (r2 == 0) goto L17;
        ensureLayoutState();
        if (r2 <= 0) goto L13;
        int r0 = 1;
    L14:
        updateLayoutState(r0, Math.abs(r2), true, r4);
        collectPrefetchPositionsForLayoutState(r4, this.mLayoutState, r5);
        return;
    L13:
        r0 = -1;
        goto L14
    L17:
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectInitialPrefetchPositions(int r6, RecyclerView.LayoutManager.LayoutPrefetchRegistry r7) {
        SavedState r0 = this.mPendingSavedState;
        int r1 = -1;
        if (r0 != null) goto L5;
    L7:
        resolveShouldLayoutReverse();
        boolean r3 = this.mShouldReverseLayout;
        int r02 = this.mPendingScrollPosition;
        if (r02 != (-1)) goto L12;
        if (r3 == false) goto L11;
        r02 = r6 - 1;
        goto L12
    L11:
        r02 = 0;
    L12:
        if (r3 == true) goto L15;
        r1 = 1;
    L15:
        int r32 = 0;
    L17:
        if (r32 >= this.mInitialPrefetchItemCount) goto L21;
        if (r02 < 0) goto L25;
        if (r02 >= r6) goto L26;
        r7.addPosition(r02, 0);
        r02 = r02 + r1;
        r32 = r32 + 1;
        goto L17
    L26:
        return;
    L25:
        return;
    L21:
        return;
    L5:
        if (r0.hasValidAnchor() == false) goto L7;
        SavedState r03 = this.mPendingSavedState;
        r3 = r03.mAnchorLayoutFromEnd;
        r02 = r03.mAnchorPosition;
        goto L12
    }

    public void collectPrefetchPositionsForLayoutState(RecyclerView.State r1, LayoutState r2, RecyclerView.LayoutManager.LayoutPrefetchRegistry r3) {
        int r0 = r2.mCurrentPosition;
        if (r0 >= 0) goto L5;
        return;
    L5:
        if (r0 >= r1.getItemCount()) goto L9;
        r3.addPosition(r0, Math.max(0, r2.mScrollingOffset));
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State r1) {
        return computeScrollExtent(r1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State r1) {
        return computeScrollOffset(r1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State r1) {
        return computeScrollRange(r1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int r4) {
        if (getChildCount() != 0) goto L6;
        return null;
    L6:
        boolean r0 = false;
        int r2 = 1;
        if (r4 >= getPosition(getChildAt(0))) goto L10;
        r0 = true;
    L10:
        if (r0 == this.mShouldReverseLayout) goto L13;
        r2 = -1;
    L13:
        if (this.mOrientation != 0) goto L17;
        return new PointF(r2, 0.0f);
    L17:
        return new PointF(0.0f, r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State r1) {
        return computeScrollExtent(r1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State r1) {
        return computeScrollOffset(r1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State r1) {
        return computeScrollRange(r1);
    }

    public int convertFocusDirectionToLayoutDirection(int r5) {
        if (r5 == 1) goto L39;
        if (r5 == 2) goto L32;
        if (r5 == 17) goto L28;
        if (r5 == 33) goto L24;
        if (r5 == 66) goto L20;
        if (r5 == 130) goto L16;
        return Integer.MIN_VALUE;
    L16:
        if (this.mOrientation != 1) goto L18;
        return 1;
    L18:
        return Integer.MIN_VALUE;
    L20:
        if (this.mOrientation != 0) goto L22;
        return 1;
    L22:
        return Integer.MIN_VALUE;
    L24:
        if (this.mOrientation != 1) goto L26;
        return -1;
    L26:
        return Integer.MIN_VALUE;
    L28:
        if (this.mOrientation != 0) goto L30;
        return -1;
    L30:
        return Integer.MIN_VALUE;
    L32:
        if (this.mOrientation != 1) goto L35;
        return 1;
    L35:
        if (isLayoutRTL() == false) goto L37;
        return -1;
    L37:
        return 1;
    L39:
        if (this.mOrientation != 1) goto L42;
        return -1;
    L42:
        if (isLayoutRTL() == false) goto L44;
        return 1;
    L44:
        return -1;
    }

    public LayoutState createLayoutState() {
        return new LayoutState();
    }

    public void ensureLayoutState() {
        if (this.mLayoutState != null) goto L6;
        this.mLayoutState = createLayoutState();
        return;
    }

    public int fill(RecyclerView.Recycler r8, LayoutState r9, RecyclerView.State r10, boolean r11) {
        int r0 = r9.mAvailable;
        int r1 = r9.mScrollingOffset;
        if (r1 == Integer.MIN_VALUE) goto L7;
        if (r0 >= 0) goto L6;
        r9.mScrollingOffset = r1 + r0;
    L6:
        recycleByLayoutState(r8, r9);
    L7:
        int r12 = r9.mAvailable + r9.mExtraFillSpace;
        LayoutChunkResult r3 = this.mLayoutChunkResult;
    L9:
        if (r9.mInfinite == true) goto L12;
        if (r12 > 0) goto L12;
    L33:
        return r0 - r9.mAvailable;
    L12:
        if (r9.hasMore(r10) == false) goto L33;
        r3.resetInternal();
        layoutChunk(r8, r10, r9, r3);
        if (r3.mFinished == true) goto L33;
        r9.mOffset += r3.mConsumed * r9.mLayoutDirection;
        if (r3.mIgnoreConsumed == true) goto L19;
    L22:
        int r4 = r9.mAvailable;
        int r5 = r3.mConsumed;
        r9.mAvailable = r4 - r5;
        r12 = r12 - r5;
    L23:
        int r42 = r9.mScrollingOffset;
        if (r42 == Integer.MIN_VALUE) goto L29;
        int r43 = r42 + r3.mConsumed;
        r9.mScrollingOffset = r43;
        int r52 = r9.mAvailable;
        if (r52 >= 0) goto L28;
        r9.mScrollingOffset = r43 + r52;
    L28:
        recycleByLayoutState(r8, r9);
    L29:
        if (r11 == false) goto L9;
        if (r3.mFocusable == false) goto L9;
    L19:
        if (r9.mScrapList != null) goto L22;
        if (r10.isPreLayout() == true) goto L23;
        goto L22
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View r0 = findOneVisibleChild(0, getChildCount(), true, false);
        if (r0 != null) goto L7;
        return -1;
    L7:
        return getPosition(r0);
    }

    public View findFirstVisibleChildClosestToEnd(boolean r3, boolean r4) {
        if (this.mShouldReverseLayout == false) goto L7;
        return findOneVisibleChild(0, getChildCount(), r3, r4);
    L7:
        return findOneVisibleChild(getChildCount() - 1, -1, r3, r4);
    }

    public View findFirstVisibleChildClosestToStart(boolean r3, boolean r4) {
        if (this.mShouldReverseLayout == false) goto L7;
        return findOneVisibleChild(getChildCount() - 1, -1, r3, r4);
    L7:
        return findOneVisibleChild(0, getChildCount(), r3, r4);
    }

    public int findFirstVisibleItemPosition() {
        View r0 = findOneVisibleChild(0, getChildCount(), false, true);
        if (r0 != null) goto L7;
        return -1;
    L7:
        return getPosition(r0);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View r0 = findOneVisibleChild(getChildCount() - 1, -1, true, false);
        if (r0 != null) goto L6;
        return -1;
    L6:
        return getPosition(r0);
    }

    public int findLastVisibleItemPosition() {
        View r0 = findOneVisibleChild(getChildCount() - 1, -1, false, true);
        if (r0 != null) goto L6;
        return -1;
    L6:
        return getPosition(r0);
    }

    public View findOnePartiallyOrCompletelyInvisibleChild(int r4, int r5) {
        ensureLayoutState();
        if (r5 > r4) goto L7;
        if (r5 < r4) goto L7;
        return getChildAt(r4);
    L7:
        if (this.mOrientationHelper.getDecoratedStart(getChildAt(r4)) >= this.mOrientationHelper.getStartAfterPadding()) goto L9;
        int r0 = 16644;
        int r1 = LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD;
    L11:
        if (this.mOrientation != 0) goto L15;
        return this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(r4, r5, r0, r1);
    L15:
        return this.mVerticalBoundCheck.findOneViewWithinBoundFlags(r4, r5, r0, r1);
    L9:
        r0 = 4161;
        r1 = 4097;
        goto L11
    }

    public View findOneVisibleChild(int r2, int r3, boolean r4, boolean r5) {
        ensureLayoutState();
        int r0 = 320;
        if (r4 == false) goto L5;
        int r42 = 24579;
    L6:
        if (r5 == true) goto L10;
        r0 = 0;
    L10:
        if (this.mOrientation != 0) goto L14;
        return this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(r2, r3, r42, r0);
    L14:
        return this.mVerticalBoundCheck.findOneViewWithinBoundFlags(r2, r3, r42, r0);
    L5:
        r42 = 320;
        goto L6
    }

    public View findReferenceChild(RecyclerView.Recycler r6, RecyclerView.State r7, int r8, int r9, int r10) {
        ensureLayoutState();
        if (this.mIgnoreTopPadding == false) goto L5;
        int r62 = 0;
    L6:
        int r72 = this.mOrientationHelper.getEndAfterPadding();
        if (r9 <= r8) goto L9;
        int r0 = 1;
    L10:
        View r1 = null;
        View r2 = null;
    L11:
        if (r8 == r9) goto L28;
        View r3 = getChildAt(r8);
        int r4 = getPosition(r3);
        if (r4 < 0) goto L27;
        if (r4 >= r10) goto L27;
        if (((RecyclerView.LayoutParams) r3.getLayoutParams()).isItemRemoved() == false) goto L20;
        if (r2 != null) goto L27;
        r2 = r3;
        goto L27
    L20:
        if (this.mOrientationHelper.getDecoratedStart(r3) < r72) goto L22;
    L25:
        if (r1 != null) goto L27;
        r1 = r3;
        goto L27
    L22:
        if (this.mOrientationHelper.getDecoratedEnd(r3) < r62) goto L25;
        return r3;
    L27:
        r8 = r8 + r0;
        goto L11
    L28:
        if (r1 == null) goto L30;
        return r1;
    L30:
        return r2;
    L9:
        r0 = -1;
        goto L10
    L5:
        r62 = this.mOrientationHelper.getStartAfterPadding();
        goto L6
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View findViewByPosition(int r3) {
        int r0 = getChildCount();
        if (r0 != 0) goto L6;
        return null;
    L6:
        int r1 = r3 - getPosition(getChildAt(0));
        if (r1 < 0) goto L13;
        if (r1 >= r0) goto L13;
        View r02 = getChildAt(r1);
        if (getPosition(r02) != r3) goto L13;
        return r02;
    L13:
        return super.findViewByPosition(r3);
    }

    public int firstPosition() {
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Deprecated
    public int getExtraLayoutSpace(RecyclerView.State r2) {
        boolean r22 = r2.hasTargetScrollPosition();
        int r0 = this.addExtraLayoutSpace;
        if (r22 == true) goto L5;
        return r0;
    L5:
        return r0 + this.mOrientationHelper.getTotalSpace();
    }

    public int getInitialPrefetchItemCount() {
        return this.mInitialPrefetchItemCount;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }

    public int getStartForFixGap() {
        return this.mOrientationHelper.getStartAfterPadding();
    }

    public boolean hasPendingScrollPosition() {
        if (this.mPendingScrollPosition < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isLayoutRTL() {
        if (getLayoutDirection() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    public void layoutChunk(RecyclerView.Recycler r7, RecyclerView.State r8, LayoutState r9, LayoutChunkResult r10) {
        View r1 = r9.next(r7);
        if (r1 != null) goto L6;
        r10.mFinished = true;
        return;
    L6:
        RecyclerView.LayoutParams r82 = (RecyclerView.LayoutParams) r1.getLayoutParams();
        List<RecyclerView.ViewHolder> r0 = r9.mScrapList;
        boolean r2 = this.mShouldReverseLayout;
        if (r0 != null) goto L16;
        if (r9.mLayoutDirection != (-1)) goto L11;
        boolean r02 = true;
    L12:
        if (r2 != r02) goto L14;
        addView(r1);
    L22:
        measureChildWithMargins(r1, 0, 0);
        r10.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(r1);
        if (this.mOrientation == 1) goto L25;
        int r03 = getPaddingTop();
        int r22 = this.mOrientationHelper.getDecoratedMeasurementInOther(r1) + r03;
        int r4 = r9.mLayoutDirection;
        int r92 = r9.mOffset;
        if (r4 != (-1)) goto L37;
        int r42 = r92;
        int r5 = r22;
        int r23 = r92 - r10.mConsumed;
    L36:
        int r3 = r03;
    L31:
        LinearLayoutManager r04 = this;
    L38:
        r04.layoutDecoratedWithMargins(r1, r23, r3, r42, r5);
        if (r82.isItemRemoved() == false) goto L41;
    L42:
        r10.mIgnoreConsumed = true;
    L43:
        r10.mFocusable = r1.hasFocusable();
        return;
    L41:
        if (r82.isItemChanged() == false) goto L43;
    L37:
        r5 = r22;
        r42 = r10.mConsumed + r92;
        r23 = r92;
        goto L36
    L25:
        if (isLayoutRTL() == false) goto L27;
        int r05 = getWidth() - getPaddingRight();
        r23 = r05 - this.mOrientationHelper.getDecoratedMeasurementInOther(r1);
    L28:
        int r43 = r9.mLayoutDirection;
        int r93 = r9.mOffset;
        if (r43 != (-1)) goto L32;
        r3 = r93 - r10.mConsumed;
        r5 = r93;
        r42 = r05;
        goto L31
    L32:
        r42 = r05;
        r5 = r10.mConsumed + r93;
        r04 = this;
        r3 = r93;
        goto L38
    L27:
        r23 = getPaddingLeft();
        r05 = this.mOrientationHelper.getDecoratedMeasurementInOther(r1) + r23;
        goto L28
    L14:
        addView(r1, 0);
        goto L22
    L11:
        r02 = false;
        goto L12
    L16:
        if (r9.mLayoutDirection != (-1)) goto L18;
        boolean r06 = true;
    L19:
        if (r2 != r06) goto L21;
        addDisappearingView(r1);
        goto L22
    L21:
        addDisappearingView(r1, 0);
        goto L22
    L18:
        r06 = false;
        goto L19
    }

    public void onAnchorReady(RecyclerView.Recycler r1, RecyclerView.State r2, AnchorInfo r3, int r4) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView r1, RecyclerView.Recycler r2) {
        super.onDetachedFromWindow(r1, r2);
        if (this.mRecycleChildrenOnDetach == false) goto L6;
        removeAndRecycleAllViews(r2);
        r2.clear();
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View r4, int r5, RecyclerView.Recycler r6, RecyclerView.State r7) {
        resolveShouldLayoutReverse();
        if (getChildCount() != 0) goto L5;
        return null;
    L5:
        int r42 = convertFocusDirectionToLayoutDirection(r5);
        if (r42 != Integer.MIN_VALUE) goto L8;
        return null;
    L8:
        ensureLayoutState();
        updateLayoutState(r42, (int) (this.mOrientationHelper.getTotalSpace() * MAX_SCROLL_FACTOR), false, r7);
        LayoutState r1 = this.mLayoutState;
        r1.mScrollingOffset = Integer.MIN_VALUE;
        r1.mRecycle = false;
        fill(r6, r1, r7, true);
        if (r42 != (-1)) goto L11;
        View r62 = findPartiallyOrCompletelyInvisibleChildClosestToStart();
    L12:
        if (r42 != (-1)) goto L14;
        View r3 = getChildClosestToStart();
    L16:
        if (r3.hasFocusable() == false) goto L20;
        if (r62 != null) goto L19;
        return null;
    L19:
        return r3;
    L20:
        return r62;
    L14:
        r3 = getChildClosestToEnd();
        goto L16
    L11:
        r62 = findPartiallyOrCompletelyInvisibleChildClosestToEnd();
        goto L12
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent r2) {
        super.onInitializeAccessibilityEvent(r2);
        if (getChildCount() <= 0) goto L6;
        r2.setFromIndex(findFirstVisibleItemPosition());
        r2.setToIndex(findLastVisibleItemPosition());
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler r9, RecyclerView.State r10) {
        int r1 = -1;
        if (this.mPendingSavedState != null) goto L7;
        if (this.mPendingScrollPosition != (-1)) goto L7;
    L10:
        SavedState r0 = this.mPendingSavedState;
        if (r0 != null) goto L13;
    L15:
        ensureLayoutState();
        this.mLayoutState.mRecycle = false;
        resolveShouldLayoutReverse();
        View r02 = getFocusedChild();
        AnchorInfo r3 = this.mAnchorInfo;
        if (r3.mValid == true) goto L18;
    L28:
        r3.reset();
        AnchorInfo r03 = this.mAnchorInfo;
        r03.mLayoutFromEnd = this.mShouldReverseLayout ^ this.mStackFromEnd;
        updateAnchorInfoForLayout(r9, r10, r03);
        this.mAnchorInfo.mValid = true;
    L29:
        LayoutState r04 = this.mLayoutState;
        if (r04.mLastScrollDelta < 0) goto L32;
        int r32 = 1;
    L33:
        r04.mLayoutDirection = r32;
        int[] r05 = this.mReusableIntPair;
        r05[0] = 0;
        r05[1] = 0;
        calculateExtraLayoutSpace(r10, r05);
        int r06 = Math.max(0, this.mReusableIntPair[0]) + this.mOrientationHelper.getStartAfterPadding();
        int r33 = Math.max(0, this.mReusableIntPair[1]) + this.mOrientationHelper.getEndPadding();
        if (r10.isPreLayout() == false) goto L49;
        int r4 = this.mPendingScrollPosition;
        if (r4 == (-1)) goto L49;
        if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) goto L49;
        View r42 = findViewByPosition(r4);
        if (r42 == null) goto L49;
        boolean r6 = this.mPendingScrollPositionBottom;
        OrientationHelper r7 = this.mOrientationHelper;
        if (r6 == false) goto L45;
        int r62 = r7.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(r42);
        int r43 = this.mPendingScrollPositionOffset;
    L44:
        int r63 = r62 - r43;
        if (r63 <= 0) goto L48;
        r06 = r06 + r63;
        goto L49
    L48:
        r33 = r33 - r63;
        goto L49
    L45:
        r43 = r7.getDecoratedStart(r42) - this.mOrientationHelper.getStartAfterPadding();
        r62 = this.mPendingScrollPositionOffset;
    L49:
        AnchorInfo r44 = this.mAnchorInfo;
        boolean r64 = r44.mLayoutFromEnd;
        boolean r72 = this.mShouldReverseLayout;
        if (r64 == false) goto L53;
        if (r72 == false) goto L54;
    L52:
        r1 = 1;
    L54:
        onAnchorReady(r9, r10, r44, r1);
        detachAndScrapAttachedViews(r9);
        this.mLayoutState.mInfinite = resolveIsInfinite();
        this.mLayoutState.mIsPreLayout = r10.isPreLayout();
        this.mLayoutState.mNoRecycleSpace = 0;
        AnchorInfo r12 = this.mAnchorInfo;
        if (r12.mLayoutFromEnd == false) goto L62;
        updateLayoutStateToFillStart(r12);
        LayoutState r13 = this.mLayoutState;
        r13.mExtraFillSpace = r06;
        fill(r9, r13, r10, false);
        LayoutState r07 = this.mLayoutState;
        int r14 = r07.mOffset;
        int r45 = r07.mCurrentPosition;
        int r08 = r07.mAvailable;
        if (r08 <= 0) goto L59;
        r33 = r33 + r08;
    L59:
        updateLayoutStateToFillEnd(this.mAnchorInfo);
        LayoutState r09 = this.mLayoutState;
        r09.mExtraFillSpace = r33;
        r09.mCurrentPosition += r09.mItemDirection;
        fill(r9, r09, r10, false);
        LayoutState r010 = this.mLayoutState;
        int r34 = r010.mOffset;
        int r011 = r010.mAvailable;
        if (r011 <= 0) goto L69;
        updateLayoutStateToFillStart(r45, r14);
        LayoutState r15 = this.mLayoutState;
        r15.mExtraFillSpace = r011;
        fill(r9, r15, r10, false);
        r14 = this.mLayoutState.mOffset;
    L69:
        if (getChildCount() > 0) goto L71;
    L75:
        layoutForPredictiveAnimations(r9, r10, r14, r34);
        if (r10.isPreLayout() == true) goto L78;
        this.mOrientationHelper.onLayoutComplete();
    L79:
        this.mLastStackFromEnd = this.mStackFromEnd;
        return;
    L78:
        this.mAnchorInfo.reset();
        goto L79
    L71:
        if ((this.mShouldReverseLayout ^ this.mStackFromEnd) == false) goto L74;
        int r012 = fixLayoutEndGap(r34, r9, r10, true);
        int r16 = r14 + r012;
        int r35 = r34 + r012;
        int r013 = fixLayoutStartGap(r16, r9, r10, false);
    L73:
        r14 = r16 + r013;
        r34 = r35 + r013;
        goto L75
    L74:
        int r014 = fixLayoutStartGap(r14, r9, r10, true);
        r16 = r14 + r014;
        r35 = r34 + r014;
        r013 = fixLayoutEndGap(r35, r9, r10, false);
        goto L73
    L62:
        updateLayoutStateToFillEnd(r12);
        LayoutState r17 = this.mLayoutState;
        r17.mExtraFillSpace = r33;
        fill(r9, r17, r10, false);
        LayoutState r18 = this.mLayoutState;
        r34 = r18.mOffset;
        int r46 = r18.mCurrentPosition;
        int r19 = r18.mAvailable;
        if (r19 <= 0) goto L65;
        r06 = r06 + r19;
    L65:
        updateLayoutStateToFillStart(this.mAnchorInfo);
        LayoutState r110 = this.mLayoutState;
        r110.mExtraFillSpace = r06;
        r110.mCurrentPosition += r110.mItemDirection;
        fill(r9, r110, r10, false);
        LayoutState r015 = this.mLayoutState;
        r14 = r015.mOffset;
        int r016 = r015.mAvailable;
        if (r016 <= 0) goto L69;
        updateLayoutStateToFillEnd(r46, r34);
        LayoutState r36 = this.mLayoutState;
        r36.mExtraFillSpace = r016;
        fill(r9, r36, r10, false);
        r34 = this.mLayoutState.mOffset;
        goto L69
    L53:
        if (r72 == false) goto L52;
    L32:
        r32 = -1;
        goto L33
    L18:
        if (this.mPendingScrollPosition != (-1)) goto L28;
        if (this.mPendingSavedState != null) goto L28;
        if (r02 == null) goto L29;
        if (this.mOrientationHelper.getDecoratedStart(r02) < this.mOrientationHelper.getEndAfterPadding()) goto L26;
    L27:
        this.mAnchorInfo.assignFromViewAndKeepVisibleRect(r02, getPosition(r02));
        goto L29
    L26:
        if (this.mOrientationHelper.getDecoratedEnd(r02) > this.mOrientationHelper.getStartAfterPadding()) goto L29;
    L13:
        if (r0.hasValidAnchor() == false) goto L15;
        this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
    L7:
        if (r10.getItemCount() != 0) goto L10;
        removeAndRecycleAllViews(r9);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State r1) {
        super.onLayoutCompleted(r1);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.reset();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == false) goto L6;
        this.mPendingSavedState = (SavedState) r2;
        requestLayout();
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) goto L5;
        SavedState r0 = new SavedState();
        if (getChildCount() <= 0) goto L14;
        ensureLayoutState();
        boolean r1 = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
        r0.mAnchorLayoutFromEnd = r1;
        if (r1 == false) goto L12;
        View r12 = getChildClosestToEnd();
        r0.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(r12);
        r0.mAnchorPosition = getPosition(r12);
        return r0;
    L12:
        View r13 = getChildClosestToStart();
        r0.mAnchorPosition = getPosition(r13);
        r0.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(r13) - this.mOrientationHelper.getStartAfterPadding();
        return r0;
    L14:
        r0.invalidateAnchor();
        return r0;
    L5:
        return new SavedState(this.mPendingSavedState);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.ViewDropHandler
    public void prepareForDrop(View r4, View r5, int r6, int r7) {
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        ensureLayoutState();
        resolveShouldLayoutReverse();
        int r62 = getPosition(r4);
        int r72 = getPosition(r5);
        if (r62 >= r72) goto L5;
        char r63 = 1;
    L7:
        if (this.mShouldReverseLayout == false) goto L14;
        OrientationHelper r0 = this.mOrientationHelper;
        if (r63 != 1) goto L12;
        scrollToPositionWithOffset(r72, r0.getEndAfterPadding() - (this.mOrientationHelper.getDecoratedStart(r5) + this.mOrientationHelper.getDecoratedMeasurement(r4)));
        return;
    L12:
        scrollToPositionWithOffset(r72, r0.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(r5));
        return;
    L14:
        OrientationHelper r1 = this.mOrientationHelper;
        if (r63 != 65535) goto L18;
        scrollToPositionWithOffset(r72, r1.getDecoratedStart(r5));
        return;
    L18:
        scrollToPositionWithOffset(r72, r1.getDecoratedEnd(r5) - this.mOrientationHelper.getDecoratedMeasurement(r4));
        return;
    L5:
        r63 = 65535;
        goto L7
    }

    public void recycleChildren(RecyclerView.Recycler r1, int r2, int r3) {
        if (r2 == r3) goto L10;
        if (r3 <= r2) goto L8;
        int r32 = r3 - 1;
    L6:
        if (r32 < r2) goto L13;
        removeAndRecycleViewAt(r32, r1);
        r32 = r32 - 1;
        goto L6
    L13:
        return;
    L8:
        if (r2 <= r3) goto L14;
        removeAndRecycleViewAt(r2, r1);
        r2 = r2 - 1;
        goto L8
    L14:
        return;
    }

    public void recycleViewsFromStart(RecyclerView.Recycler r5, int r6, int r7) {
        if (r6 < 0) goto L38;
        int r62 = r6 - r7;
        int r72 = getChildCount();
        if (this.mShouldReverseLayout == false) goto L22;
        int r73 = r72 - 1;
        int r0 = r73;
    L7:
        if (r0 < 0) goto L53;
        View r1 = getChildAt(r0);
        if (r1 == null) goto L21;
        RecyclerView.ViewHolder r2 = this.mRecyclerView.getChildViewHolder(r1);
        if (r2 == null) goto L21;
        if (r2.shouldIgnore() == true) goto L21;
        if (this.mOrientationHelper.getDecoratedEnd(r1) > r62) goto L19;
        if (this.mOrientationHelper.getTransformedEndWithDecoration(r1) <= r62) goto L21;
    L19:
        recycleChildren(r5, r73, r0);
        return;
    L21:
        r0 = r0 - 1;
        goto L7
    L53:
        return;
    L22:
        int r12 = 0;
    L23:
        if (r12 >= r72) goto L54;
        View r22 = getChildAt(r12);
        if (r22 == null) goto L37;
        RecyclerView.ViewHolder r3 = this.mRecyclerView.getChildViewHolder(r22);
        if (r3 == null) goto L37;
        if (r3.shouldIgnore() == true) goto L37;
        if (this.mOrientationHelper.getDecoratedEnd(r22) > r62) goto L35;
        if (this.mOrientationHelper.getTransformedEndWithDecoration(r22) <= r62) goto L37;
    L35:
        recycleChildren(r5, 0, r12);
        return;
    L37:
        r12 = r12 + 1;
        goto L23
    L54:
        return;
    }

    public boolean resolveIsInfinite() {
        if (this.mOrientationHelper.getMode() == 0) goto L5;
        return false;
    L5:
        if (this.mOrientationHelper.getEnd() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    public int scrollBy(int r6, RecyclerView.Recycler r7, RecyclerView.State r8) {
        if (getChildCount() == 0) goto L17;
        if (r6 == 0) goto L17;
        ensureLayoutState();
        this.mLayoutState.mRecycle = true;
        if (r6 <= 0) goto L9;
        int r0 = 1;
    L10:
        int r3 = Math.abs(r6);
        updateLayoutState(r0, r3, true, r8);
        LayoutState r2 = this.mLayoutState;
        int r4 = r2.mScrollingOffset + fill(r7, r2, r8, false);
        if (r4 >= 0) goto L13;
        return 0;
    L13:
        if (r3 <= r4) goto L15;
        r6 = r0 * r4;
    L15:
        this.mOrientationHelper.offsetChildren(-r6);
        this.mLayoutState.mLastScrollDelta = r6;
        return r6;
    L9:
        r0 = -1;
    L17:
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int r3, RecyclerView.Recycler r4, RecyclerView.State r5) {
        if (this.mOrientation != 1) goto L7;
        return 0;
    L7:
        return scrollBy(r3, r4, r5);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int r1) {
        this.mPendingScrollPosition = r1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState r12 = this.mPendingSavedState;
        if (r12 == null) goto L5;
        r12.invalidateAnchor();
    L5:
        requestLayout();
    }

    public void scrollToPositionWithOffset(int r2, int r3) {
        scrollToPositionWithOffset(r2, r3, this.mShouldReverseLayout);
    }

    public void scrollToPositionWithOffset(int r2, int r3, boolean r4) {
        if (this.mPendingScrollPosition == r2) goto L5;
    L9:
        this.mPendingScrollPosition = r2;
        this.mPendingScrollPositionOffset = r3;
        this.mPendingScrollPositionBottom = r4;
        SavedState r22 = this.mPendingSavedState;
        if (r22 == null) goto L12;
        r22.invalidateAnchor();
    L12:
        requestLayout();
        return;
    L5:
        if (this.mPendingScrollPositionOffset != r3) goto L9;
        if (this.mPendingScrollPositionBottom != r4) goto L9;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int r2, RecyclerView.Recycler r3, RecyclerView.State r4) {
        if (this.mOrientation != 0) goto L7;
        return 0;
    L7:
        return scrollBy(r2, r3, r4);
    }

    public void setExtraLayoutSpace(int r1) {
        this.addExtraLayoutSpace = r1;
    }

    public void setInitialPrefetchItemCount(int r1) {
        this.mInitialPrefetchItemCount = r1;
    }

    public void setNeedFixEndGap(boolean r1) {
        this.needFixEndGap = r1;
    }

    public void setNeedFixGap(boolean r1) {
        this.needFixGap = r1;
    }

    public void setOrientation(int r3) {
        if (r3 != 0) goto L4;
    L8:
        assertNotInLayoutOrScroll(null);
        if (r3 == this.mOrientation) goto L11;
    L14:
        OrientationHelper r0 = OrientationHelper.createOrientationHelper(this, r3);
        this.mOrientationHelper = r0;
        this.mAnchorInfo.mOrientationHelper = r0;
        this.mOrientation = r3;
        requestLayout();
        return;
    L11:
        if (this.mOrientationHelper == null) goto L14;
        return;
    L4:
        if (r3 == 1) goto L8;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("invalid orientation:", r3);
    }

    public void setRecycleChildrenOnDetach(boolean r1) {
        this.mRecycleChildrenOnDetach = r1;
    }

    public void setReverseLayout(boolean r2) {
        assertNotInLayoutOrScroll(null);
        if (r2 != this.mReverseLayout) goto L5;
        return;
    L5:
        this.mReverseLayout = r2;
        requestLayout();
    }

    public void setScrollDisabled(boolean r1) {
        this.mDisableScroll = r1;
    }

    public void setSmoothScrollbarEnabled(boolean r1) {
        this.mSmoothScrollbarEnabled = r1;
    }

    public void setStackFromEnd(boolean r2) {
        assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd != r2) goto L5;
        return;
    L5:
        this.mStackFromEnd = r2;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean shouldMeasureTwice() {
        if (getHeightMode() != 1073741824) goto L5;
        return false;
    L5:
        if (getWidthMode() != 1073741824) goto L7;
        return false;
    L7:
        if (hasFlexibleChildInBothOrientations() == false) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
        LinearSmoothScroller r22 = new LinearSmoothScroller(r1.getContext());
        r22.setTargetPosition(r3);
        startSmoothScroll(r22);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState == null) goto L5;
        return false;
    L5:
        if (this.mLastStackFromEnd != this.mStackFromEnd) goto L10;
        return true;
    L10:
        return false;
    }

    public void validateChildOrder() {
        Log.d(TAG, "validating child count " + getChildCount());
        boolean r1 = true;
        if (getChildCount() < 1) goto L43;
        int r2 = getPosition(getChildAt(0));
        int r3 = this.mOrientationHelper.getDecoratedStart(getChildAt(0));
        if (this.mShouldReverseLayout == false) goto L22;
        int r4 = 1;
    L9:
        if (r4 >= getChildCount()) goto L44;
        View r7 = getChildAt(r4);
        int r8 = getPosition(r7);
        int r72 = this.mOrientationHelper.getDecoratedStart(r7);
        if (r8 < r2) goto L12;
        if (r72 > r3) goto L20;
        r4 = r4 + 1;
        goto L9
    L20:
        logChildren();
        GlShader$$ExternalSyntheticBUOutline1.m("detected invalid location");
        return;
    L12:
        logChildren();
        StringBuilder r22 = new StringBuilder("detected invalid position. loc invalid? ");
        if (r72 < r3) goto L16;
        r1 = false;
    L16:
        r22.append(r1);
        throw new RuntimeException(r22.toString());
    L44:
        return;
    L22:
        int r42 = 1;
    L24:
        if (r42 >= getChildCount()) goto L45;
        View r73 = getChildAt(r42);
        int r82 = getPosition(r73);
        int r74 = this.mOrientationHelper.getDecoratedStart(r73);
        if (r82 < r2) goto L27;
        if (r74 < r3) goto L35;
        r42 = r42 + 1;
        goto L24
    L35:
        logChildren();
        GlShader$$ExternalSyntheticBUOutline1.m("detected invalid location");
        return;
    L27:
        logChildren();
        StringBuilder r23 = new StringBuilder("detected invalid position. loc invalid? ");
        if (r74 < r3) goto L31;
        r1 = false;
    L31:
        r23.append(r1);
        throw new RuntimeException(r23.toString());
    L45:
        return;
    }
}
