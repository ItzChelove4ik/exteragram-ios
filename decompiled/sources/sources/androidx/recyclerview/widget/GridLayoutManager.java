package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.car.app.AppInfo$$ExternalSyntheticBUOutline0;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public class GridLayoutManager extends LinearLayoutManager {
    protected int[] mCachedBorders;
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    View[] mSet;
    int mSpanCount;
    SpanSizeLookup mSpanSizeLookup;
    private boolean mUsingSpansToEstimateScrollBarDimensions;

    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        public DefaultSpanSizeLookup() {
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanIndex(int r1, int r2) {
            return r1 % r2;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r1) {
            return 1;
        }
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {
        int mSpanIndex;
        public int mSpanSize;

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(Context r1, AttributeSet r2) {
            super(r1, r2);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams r1) {
            super(r1);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public int getSpanIndex() {
            return this.mSpanIndex;
        }

        public int getSpanSize() {
            return this.mSpanSize;
        }
    }

    public static abstract class SpanSizeLookup {
        private boolean mCacheSpanGroupIndices;
        private boolean mCacheSpanIndices;
        final SparseIntArray mSpanGroupIndexCache;
        final SparseIntArray mSpanIndexCache;

        public SpanSizeLookup() {
            this.mSpanIndexCache = new SparseIntArray();
            this.mSpanGroupIndexCache = new SparseIntArray();
            this.mCacheSpanIndices = false;
            this.mCacheSpanGroupIndices = false;
        }

        public static int findFirstKeyLessThan(SparseIntArray r4, int r5) {
            int r0 = r4.size() - 1;
            int r1 = 0;
        L3:
            if (r1 > r0) goto L8;
            int r2 = (r1 + r0) >>> 1;
            if (r4.keyAt(r2) < r5) goto L6;
            r0 = r2 - 1;
            goto L3
        L6:
            r1 = r2 + 1;
            goto L3
        L8:
            int r12 = r1 - 1;
            if (r12 >= 0) goto L11;
            return -1;
        L11:
            if (r12 < r4.size()) goto L13;
            return -1;
        L13:
            return r4.keyAt(r12);
        }

        public int getCachedSpanGroupIndex(int r3, int r4) {
            if (this.mCacheSpanGroupIndices == false) goto L5;
            int r0 = this.mSpanGroupIndexCache.get(r3, -1);
            if (r0 == (-1)) goto L9;
            return r0;
        L9:
            int r42 = getSpanGroupIndex(r3, r4);
            this.mSpanGroupIndexCache.put(r3, r42);
            return r42;
        L5:
            return getSpanGroupIndex(r3, r4);
        }

        public int getCachedSpanIndex(int r3, int r4) {
            if (this.mCacheSpanIndices == false) goto L5;
            int r0 = this.mSpanIndexCache.get(r3, -1);
            if (r0 == (-1)) goto L9;
            return r0;
        L9:
            int r42 = getSpanIndex(r3, r4);
            this.mSpanIndexCache.put(r3, r42);
            return r42;
        L5:
            return getSpanIndex(r3, r4);
        }

        public int getSpanGroupIndex(int r7, int r8) {
            if (this.mCacheSpanGroupIndices == false) goto L9;
            int r0 = findFirstKeyLessThan(this.mSpanGroupIndexCache, r7);
            if (r0 == (-1)) goto L9;
            int r2 = this.mSpanGroupIndexCache.get(r0);
            int r3 = r0 + 1;
            int r4 = getCachedSpanIndex(r0, r8) + getSpanSize(r0);
            if (r4 != r8) goto L10;
            r2 = r2 + 1;
            r4 = 0;
        L10:
            int r02 = getSpanSize(r7);
        L11:
            if (r3 >= r7) goto L19;
            int r5 = getSpanSize(r3);
            r4 = r4 + r5;
            if (r4 != r8) goto L15;
            r2 = r2 + 1;
            r4 = 0;
        L17:
            r3 = r3 + 1;
            goto L11
        L15:
            if (r4 <= r8) goto L17;
            r2 = r2 + 1;
            r4 = r5;
            goto L17
        L19:
            if ((r4 + r02) > r8) goto L21;
            return r2;
        L21:
            return r2 + 1;
        L9:
            r2 = 0;
            r3 = 0;
            r4 = 0;
            goto L10
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x002b -> B:17:0x0030). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x002d -> B:17:0x0030). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x002f -> B:17:0x0030). Please report as a decompilation issue!!! */
        public int getSpanIndex(int r6, int r7) {
            int r0 = getSpanSize(r6);
            if (r0 != r7) goto L6;
            return 0;
        L6:
            if (this.mCacheSpanIndices == false) goto L10;
            int r2 = findFirstKeyLessThan(this.mSpanIndexCache, r6);
            if (r2 < 0) goto L10;
            int r3 = this.mSpanIndexCache.get(r2) + getSpanSize(r2);
        L17:
            r2 = r2 + 1;
        L11:
            if (r2 >= r6) goto L19;
            int r4 = getSpanSize(r2);
            r3 = r3 + r4;
            if (r3 != r7) goto L15;
            r3 = 0;
            goto L17
        L15:
            if (r3 <= r7) goto L17;
            r3 = r4;
            goto L17
        L19:
            if ((r0 + r3) > r7) goto L21;
            return r3;
        L21:
            return 0;
        L10:
            r2 = 0;
            r3 = 0;
            goto L11
        }

        public abstract int getSpanSize(int r1);

        public void invalidateSpanGroupIndexCache() {
            this.mSpanGroupIndexCache.clear();
        }

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }
    }

    public GridLayoutManager(Context r1, int r2) {
        super(r1);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        setSpanCount(r2);
    }

    public GridLayoutManager(Context r1, int r2, int r3, boolean r4) {
        super(r1, r3, r4);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        setSpanCount(r2);
    }

    private void cachePreLayoutSpanMapping() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        LayoutParams r2 = (LayoutParams) getChildAt(r1).getLayoutParams();
        int r3 = r2.getViewLayoutPosition();
        this.mPreLayoutSpanSizeCache.put(r3, r2.getSpanSize());
        this.mPreLayoutSpanIndexCache.put(r3, r2.getSpanIndex());
        r1 = r1 + 1;
        goto L3
    }

    private void calculateItemBorders(int r3) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, r3);
    }

    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }

    private int computeScrollOffsetWithSpanInfo(RecyclerView.State r10) {
        if (getChildCount() != 0) goto L5;
    L19:
        return 0;
    L5:
        if (r10.getItemCount() == 0) goto L19;
        ensureLayoutState();
        boolean r0 = isSmoothScrollbarEnabled();
        View r2 = findFirstVisibleChildClosestToStart(!r0, true);
        View r4 = findFirstVisibleChildClosestToEnd(!r0, true);
        if (r2 == null) goto L19;
        if (r4 == null) goto L19;
        int r5 = this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(r2), this.mSpanCount);
        int r6 = this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(r4), this.mSpanCount);
        int r7 = Math.min(r5, r6);
        int r52 = Math.max(r5, r6);
        int r102 = this.mSpanSizeLookup.getCachedSpanGroupIndex(r10.getItemCount() - 1, this.mSpanCount) + 1;
        if (this.mShouldReverseLayout == false) goto L14;
        int r103 = Math.max(0, (r102 - r52) - 1);
    L15:
        if (r0 == true) goto L17;
        return r103;
    L17:
        int r02 = Math.abs(this.mOrientationHelper.getDecoratedEnd(r4) - this.mOrientationHelper.getDecoratedStart(r2));
        int r1 = this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(r2), this.mSpanCount);
        return Math.round((r103 * (r02 / ((this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(r4), this.mSpanCount) - r1) + 1))) + (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(r2)));
    L14:
        r103 = Math.max(0, r7);
        goto L15
    }

    private int computeScrollRangeWithSpanInfo(RecyclerView.State r7) {
        if (getChildCount() != 0) goto L5;
    L17:
        return 0;
    L5:
        if (r7.getItemCount() == 0) goto L17;
        ensureLayoutState();
        View r0 = findFirstVisibleChildClosestToStart(!isSmoothScrollbarEnabled(), true);
        View r3 = findFirstVisibleChildClosestToEnd(!isSmoothScrollbarEnabled(), true);
        if (r0 == null) goto L17;
        if (r3 == null) goto L17;
        if (isSmoothScrollbarEnabled() == false) goto L14;
        int r1 = this.mOrientationHelper.getDecoratedEnd(r3) - this.mOrientationHelper.getDecoratedStart(r0);
        int r02 = this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(r0), this.mSpanCount);
        int r32 = this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(r3), this.mSpanCount);
        return (int) ((r1 / ((r32 - r02) + 1)) * (this.mSpanSizeLookup.getCachedSpanGroupIndex(r7.getItemCount() - 1, this.mSpanCount) + 1));
    L14:
        return this.mSpanSizeLookup.getCachedSpanGroupIndex(r7.getItemCount() - 1, this.mSpanCount) + 1;
    }

    private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler r5, RecyclerView.State r6, LinearLayoutManager.AnchorInfo r7, int r8) {
        if (r8 != 1) goto L5;
        boolean r82 = true;
    L6:
        int r1 = getSpanIndex(r5, r6, r7.mPosition);
        if (r82 == false) goto L13;
    L8:
        if (r1 <= 0) goto L12;
        int r83 = r7.mPosition;
        if (r83 <= 0) goto L24;
        int r84 = r83 - 1;
        r7.mPosition = r84;
        r1 = getSpanIndex(r5, r6, r84);
        goto L8
    L24:
        return;
    L12:
        return;
    L13:
        int r85 = r6.getItemCount() - 1;
        int r0 = r7.mPosition;
    L14:
        if (r0 >= r85) goto L18;
        int r2 = r0 + 1;
        int r3 = getSpanIndex(r5, r6, r2);
        if (r3 <= r1) goto L18;
        r0 = r2;
        r1 = r3;
    L18:
        r7.mPosition = r0;
        return;
    L5:
        r82 = false;
        goto L6
    }

    private void ensureViewSet() {
        View[] r0 = this.mSet;
        if (r0 != null) goto L5;
    L8:
        this.mSet = new View[this.mSpanCount];
        return;
    L5:
        if (r0.length != this.mSpanCount) goto L8;
    }

    private int getSpanGroupIndex(RecyclerView.Recycler r1, RecyclerView.State r2, int r3) {
        if (r2.isPreLayout() == false) goto L5;
        int r12 = r1.convertPreLayoutPositionToPostLayout(r3);
        if (r12 != (-1)) goto L11;
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + r3);
        return 0;
    L11:
        return this.mSpanSizeLookup.getCachedSpanGroupIndex(r12, this.mSpanCount);
    L5:
        return this.mSpanSizeLookup.getCachedSpanGroupIndex(r3, this.mSpanCount);
    }

    private int getSpanIndex(RecyclerView.Recycler r2, RecyclerView.State r3, int r4) {
        if (r3.isPreLayout() == false) goto L5;
        int r32 = this.mPreLayoutSpanIndexCache.get(r4, -1);
        if (r32 == (-1)) goto L9;
        return r32;
    L9:
        int r22 = r2.convertPreLayoutPositionToPostLayout(r4);
        if (r22 != (-1)) goto L14;
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + r4);
        return 0;
    L14:
        return this.mSpanSizeLookup.getCachedSpanIndex(r22, this.mSpanCount);
    L5:
        return this.mSpanSizeLookup.getCachedSpanIndex(r4, this.mSpanCount);
    }

    private void guessMeasurement(float r2, int r3) {
        calculateItemBorders(Math.max(Math.round(r2 * this.mSpanCount), r3));
    }

    private void updateMeasurements() {
        if (getOrientation() != 1) goto L6;
        int r0 = getWidth() - getPaddingRight();
        int r1 = getPaddingLeft();
    L7:
        calculateItemBorders(r0 - r1);
        return;
    L6:
        r0 = getHeight() - getPaddingBottom();
        r1 = getPaddingTop();
        goto L7
    }

    public void assignSpans(RecyclerView.Recycler r5, RecyclerView.State r6, int r7, boolean r8) {
        int r0 = 0;
        if (r8 == false) goto L5;
        int r1 = 1;
        int r82 = r7;
        int r72 = 0;
    L6:
        if (r72 == r82) goto L8;
        View r2 = this.mSet[r72];
        LayoutParams r3 = (LayoutParams) r2.getLayoutParams();
        int r22 = getSpanSize(r5, r6, getPosition(r2));
        r3.mSpanSize = r22;
        r3.mSpanIndex = r0;
        r0 = r0 + r22;
        r72 = r72 + r1;
        goto L6
    L8:
        return;
    L5:
        r72 = r7 - 1;
        r82 = -1;
        r1 = -1;
        goto L6
    }

    public int[] calculateItemBorders(int[] r5, int r6, int r7) {
        int r4 = 1;
        if (r5 != null) goto L5;
    L8:
        r5 = new int[r6 + 1];
    L9:
        int r0 = 0;
        r5[0] = 0;
        int r1 = r7 / r6;
        int r72 = r7 % r6;
        int r2 = 0;
    L10:
        if (r4 > r6) goto L18;
        r0 = r0 + r72;
        if (r0 > 0) goto L14;
    L16:
        int r3 = r1;
    L17:
        r2 = r2 + r3;
        r5[r4] = r2;
        r4 = r4 + 1;
        goto L10
    L14:
        if ((r6 - r0) >= r72) goto L16;
        r3 = r1 + 1;
        r0 = r0 - r6;
        goto L17
    L18:
        return r5;
    L5:
        if (r5.length != (r6 + 1)) goto L8;
        if (r5[r5.length - 1] == r7) goto L9;
        goto L8
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams r1) {
        return r1 instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void collectPrefetchPositionsForLayoutState(RecyclerView.State r6, LinearLayoutManager.LayoutState r7, RecyclerView.LayoutManager.LayoutPrefetchRegistry r8) {
        int r0 = this.mSpanCount;
        int r2 = 0;
    L4:
        if (r2 >= this.mSpanCount) goto L9;
        if (r7.hasMore(r6) == false) goto L13;
        if (r0 <= 0) goto L14;
        int r3 = r7.mCurrentPosition;
        r8.addPosition(r3, Math.max(0, r7.mScrollingOffset));
        r0 = r0 - this.mSpanSizeLookup.getSpanSize(r3);
        r7.mCurrentPosition += r7.mItemDirection;
        r2 = r2 + 1;
        goto L4
    L14:
        return;
    L13:
        return;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State r2) {
        if (this.mUsingSpansToEstimateScrollBarDimensions == false) goto L7;
        return computeScrollOffsetWithSpanInfo(r2);
    L7:
        return super.computeHorizontalScrollOffset(r2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State r2) {
        if (this.mUsingSpansToEstimateScrollBarDimensions == false) goto L7;
        return computeScrollRangeWithSpanInfo(r2);
    L7:
        return super.computeHorizontalScrollRange(r2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State r2) {
        if (this.mUsingSpansToEstimateScrollBarDimensions == false) goto L7;
        return computeScrollOffsetWithSpanInfo(r2);
    L7:
        return super.computeVerticalScrollOffset(r2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State r2) {
        if (this.mUsingSpansToEstimateScrollBarDimensions == false) goto L7;
        return computeScrollRangeWithSpanInfo(r2);
    L7:
        return super.computeVerticalScrollRange(r2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public View findReferenceChild(RecyclerView.Recycler r8, RecyclerView.State r9, int r10, int r11, int r12) {
        ensureLayoutState();
        int r0 = this.mOrientationHelper.getStartAfterPadding();
        int r1 = this.mOrientationHelper.getEndAfterPadding();
        if (r11 <= r10) goto L5;
        int r2 = 1;
    L6:
        View r3 = null;
        View r4 = null;
    L7:
        if (r10 == r11) goto L27;
        View r5 = getChildAt(r10);
        int r6 = getPosition(r5);
        if (r6 < 0) goto L26;
        if (r6 >= r12) goto L26;
        if (getSpanIndex(r8, r9, r6) != 0) goto L26;
        if (((RecyclerView.LayoutParams) r5.getLayoutParams()).isItemRemoved() == false) goto L19;
        if (r4 != null) goto L26;
        r4 = r5;
        goto L26
    L19:
        if (this.mOrientationHelper.getDecoratedStart(r5) < r1) goto L21;
    L24:
        if (r3 != null) goto L26;
        r3 = r5;
        goto L26
    L21:
        if (this.mOrientationHelper.getDecoratedEnd(r5) < r0) goto L24;
        return r5;
    L26:
        r10 = r10 + r2;
        goto L7
    L27:
        if (r3 == null) goto L29;
        return r3;
    L29:
        return r4;
    L5:
        r2 = -1;
        goto L6
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation != 0) goto L7;
        return new LayoutParams(-2, -1);
    L7:
        return new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context r1, AttributeSet r2) {
        return new LayoutParams(r1, r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        if ((r1 instanceof ViewGroup.MarginLayoutParams) == false) goto L7;
        return new LayoutParams((ViewGroup.MarginLayoutParams) r1);
    L7:
        return new LayoutParams(r1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler r3, RecyclerView.State r4) {
        if (this.mOrientation != 1) goto L7;
        return this.mSpanCount;
    L7:
        if (r4.getItemCount() >= 1) goto L11;
        return 0;
    L11:
        return getSpanGroupIndex(r3, r4, r4.getItemCount() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler r3, RecyclerView.State r4) {
        if (this.mOrientation != 0) goto L7;
        return this.mSpanCount;
    L7:
        if (r4.getItemCount() >= 1) goto L11;
        return 0;
    L11:
        return getSpanGroupIndex(r3, r4, r4.getItemCount() - 1) + 1;
    }

    public int getSpaceForSpanRange(int r3, int r4) {
        if (this.mOrientation == 1) goto L5;
    L8:
        int[] r2 = this.mCachedBorders;
        return r2[r4 + r3] - r2[r3];
    L5:
        if (isLayoutRTL() == false) goto L8;
        int[] r0 = this.mCachedBorders;
        int r22 = this.mSpanCount;
        return r0[r22 - r3] - r0[(r22 - r3) - r4];
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public int getSpanSize(RecyclerView.Recycler r2, RecyclerView.State r3, int r4) {
        if (r3.isPreLayout() == false) goto L5;
        int r32 = this.mPreLayoutSpanSizeCache.get(r4, -1);
        if (r32 == (-1)) goto L9;
        return r32;
    L9:
        int r22 = r2.convertPreLayoutPositionToPostLayout(r4);
        if (r22 != (-1)) goto L14;
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + r4);
        return 1;
    L14:
        return this.mSpanSizeLookup.getSpanSize(r22);
    L5:
        return this.mSpanSizeLookup.getSpanSize(r4);
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void layoutChunk(RecyclerView.Recycler r24, RecyclerView.State r25, LinearLayoutManager.LayoutState r26, LinearLayoutManager.LayoutChunkResult r27) {
        GridLayoutManager r0 = this;
        int r4 = r0.mOrientationHelper.getModeInOther();
        if (r4 == 1073741824) goto L5;
        boolean r9 = true;
    L7:
        if (r0.getChildCount() <= 0) goto L9;
        int r10 = r0.mCachedBorders[r0.mSpanCount];
    L10:
        if (r9 == false) goto L13;
        r0.updateMeasurements();
    L13:
        if (r26.mItemDirection != 1) goto L15;
        boolean r11 = true;
    L16:
        int r12 = r0.mSpanCount;
        if (r11 == true) goto L19;
        r12 = r0.getSpanIndex(r24, r25, r26.mCurrentPosition) + r0.getSpanSize(r24, r25, r26.mCurrentPosition);
    L19:
        int r13 = 0;
    L21:
        if (r13 >= r0.mSpanCount) goto L36;
        if (r26.hasMore(r25) == false) goto L36;
        if (r12 <= 0) goto L36;
        int r14 = r26.mCurrentPosition;
        int r15 = r0.getSpanSize(r24, r25, r14);
        if (r15 > r0.mSpanCount) goto L34;
        r12 = r12 - r15;
        if (r12 < 0) goto L36;
        View r8 = r26.next(r24);
        if (r8 == null) goto L36;
        r0.mSet[r13] = r8;
        r13 = r13 + 1;
        goto L21
    L34:
        AppInfo$$ExternalSyntheticBUOutline0.m("Item at position ", r14, " requires ", r15, " spans but GridLayoutManager has only ", r0.mSpanCount, " spans.");
        return;
    L36:
        if (r13 != 0) goto L39;
        r27.mFinished = true;
        return;
    L39:
        r0.assignSpans(r24, r25, r13, r11);
        float r1 = 0.0f;
        int r2 = 0;
        int r82 = 0;
    L40:
        if (r2 >= r13) goto L56;
        View r122 = r0.mSet[r2];
        if (r26.mScrapList != null) goto L46;
        if (r11 == false) goto L45;
        r0.addView(r122);
    L49:
        r0.calculateItemDecorationsForChild(r122, r0.mDecorInsets);
        r0.measureChild(r122, r4, false);
        int r142 = r0.mOrientationHelper.getDecoratedMeasurement(r122);
        if (r142 <= r82) goto L52;
        r82 = r142;
    L52:
        LayoutParams r143 = (LayoutParams) r122.getLayoutParams();
        float r123 = (r0.mOrientationHelper.getDecoratedMeasurementInOther(r122) * 1.0f) / r143.mSpanSize;
        if (r123 <= r1) goto L55;
        r1 = r123;
    L55:
        r2 = r2 + 1;
        goto L40
    L45:
        r0.addView(r122, 0);
        goto L49
    L46:
        if (r11 == false) goto L48;
        r0.addDisappearingView(r122);
        goto L49
    L48:
        r0.addDisappearingView(r122, 0);
        goto L49
    L56:
        if (r9 == false) goto L63;
        r0.guessMeasurement(r1, r10);
        int r16 = 0;
        r82 = 0;
    L58:
        if (r16 >= r13) goto L63;
        View r22 = r0.mSet[r16];
        r0.measureChild(r22, TLObject.FLAG_30, true);
        int r23 = r0.mOrientationHelper.getDecoratedMeasurement(r22);
        if (r23 <= r82) goto L62;
        r82 = r23;
    L62:
        r16 = r16 + 1;
    L63:
        int r17 = 0;
    L64:
        if (r17 >= r13) goto L74;
        View r28 = r0.mSet[r17];
        if (r0.mOrientationHelper.getDecoratedMeasurement(r28) == r82) goto L73;
        LayoutParams r42 = (LayoutParams) r28.getLayoutParams();
        Rect r92 = r42.mDecorInsets;
        int r102 = ((r92.top + r92.bottom) + ((ViewGroup.MarginLayoutParams) r42).topMargin) + ((ViewGroup.MarginLayoutParams) r42).bottomMargin;
        int r112 = ((r92.left + r92.right) + ((ViewGroup.MarginLayoutParams) r42).leftMargin) + ((ViewGroup.MarginLayoutParams) r42).rightMargin;
        int r93 = r0.getSpaceForSpanRange(r42.mSpanIndex, r42.mSpanSize);
        if (r0.mOrientation != 1) goto L70;
        int r43 = RecyclerView.LayoutManager.getChildMeasureSpec(r93, TLObject.FLAG_30, r112, ((ViewGroup.MarginLayoutParams) r42).width, false);
        int r94 = View.MeasureSpec.makeMeasureSpec(r82 - r102, TLObject.FLAG_30);
    L71:
        r0.measureChildWithDecorationsAndMargin(r28, r43, r94, true);
        goto L73
    L70:
        int r113 = View.MeasureSpec.makeMeasureSpec(r82 - r112, TLObject.FLAG_30);
        r94 = RecyclerView.LayoutManager.getChildMeasureSpec(r93, TLObject.FLAG_30, r102, ((ViewGroup.MarginLayoutParams) r42).height, false);
        r43 = r113;
    L73:
        r17 = r17 + 1;
        goto L64
    L74:
        r27.mConsumed = r82;
        int r18 = r0.mOrientation;
        int r29 = r26.mLayoutDirection;
        if (r18 != 1) goto L81;
        int r19 = r26.mOffset;
        if (r29 != (-1)) goto L80;
        int r210 = r19 - r82;
        int r83 = r19;
        r19 = r210;
    L79:
        int r211 = 0;
        int r3 = 0;
    L86:
        int r44 = r83;
        int r84 = 0;
    L87:
        View[] r5 = r0.mSet;
        if (r84 >= r13) goto L103;
        int r95 = r19;
        View r110 = r5[r84];
        LayoutParams r103 = (LayoutParams) r110.getLayoutParams();
        if (r0.mOrientation == 1) goto L92;
        int r45 = r0.getPaddingTop() + r0.mCachedBorders[r103.mSpanIndex];
        int r52 = r0.mOrientationHelper.getDecoratedMeasurementInOther(r110) + r45;
        int r46 = r3;
        int r32 = r45;
    L97:
        r0.layoutDecoratedWithMargins(r110, r211, r32, r46, r52);
        r19 = r32;
        r3 = r46;
        r44 = r52;
        if (r103.isItemRemoved() == false) goto L100;
    L101:
        r27.mIgnoreConsumed = true;
    L102:
        r27.mFocusable = r110.hasFocusable() | r27.mFocusable;
        r84 = r84 + 1;
        r0 = this;
        goto L87
    L100:
        if (r103.isItemChanged() == false) goto L102;
    L92:
        if (r0.isLayoutRTL() == false) goto L95;
        int r33 = r0.mCachedBorders[r0.mSpanCount - r103.mSpanIndex] + r0.getPaddingLeft();
        r211 = r33 - r0.mOrientationHelper.getDecoratedMeasurementInOther(r110);
    L94:
        r52 = r44;
        r46 = r33;
        r32 = r95;
        goto L97
    L95:
        r211 = r0.getPaddingLeft() + r0.mCachedBorders[r103.mSpanIndex];
        r33 = r0.mOrientationHelper.getDecoratedMeasurementInOther(r110) + r211;
        goto L94
    L103:
        Arrays.fill(r5, null);
        return;
    L80:
        r83 = r82 + r19;
        goto L79
    L81:
        int r111 = r26.mOffset;
        if (r29 != (-1)) goto L85;
        r211 = r111 - r82;
        r3 = r111;
        r19 = 0;
    L84:
        r83 = r19;
        goto L86
    L85:
        int r85 = r82 + r111;
        r211 = r111;
        r19 = 0;
        r3 = r85;
        goto L84
    L15:
        r11 = false;
        goto L16
    L9:
        r10 = 0;
        goto L10
    L5:
        r9 = false;
        goto L7
    }

    public void measureChild(View r9, int r10, boolean r11) {
        LayoutParams r0 = (LayoutParams) r9.getLayoutParams();
        Rect r1 = r0.mDecorInsets;
        int r2 = ((r1.top + r1.bottom) + ((ViewGroup.MarginLayoutParams) r0).topMargin) + ((ViewGroup.MarginLayoutParams) r0).bottomMargin;
        int r3 = ((r1.left + r1.right) + ((ViewGroup.MarginLayoutParams) r0).leftMargin) + ((ViewGroup.MarginLayoutParams) r0).rightMargin;
        int r12 = getSpaceForSpanRange(r0.mSpanIndex, r0.mSpanSize);
        if (this.mOrientation != 1) goto L5;
        int r102 = RecyclerView.LayoutManager.getChildMeasureSpec(r12, r10, r3, ((ViewGroup.MarginLayoutParams) r0).width, false);
        int r02 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), r2, ((ViewGroup.MarginLayoutParams) r0).height, true);
    L6:
        measureChildWithDecorationsAndMargin(r9, r102, r02, r11);
        return;
    L5:
        int r103 = RecyclerView.LayoutManager.getChildMeasureSpec(r12, r10, r2, ((ViewGroup.MarginLayoutParams) r0).height, false);
        int r03 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), r3, ((ViewGroup.MarginLayoutParams) r0).width, true);
        r02 = r103;
        r102 = r03;
        goto L6
    }

    public void measureChildWithDecorationsAndMargin(View r2, int r3, int r4, boolean r5) {
        RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
        if (r5 == false) goto L5;
        boolean r1 = shouldReMeasureChild(r2, r3, r4, r0);
    L6:
        if (r1 == false) goto L9;
        r2.measure(r3, r4);
        return;
    L9:
        return;
    L5:
        r1 = shouldMeasureChild(r2, r3, r4, r0);
        goto L6
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void onAnchorReady(RecyclerView.Recycler r2, RecyclerView.State r3, LinearLayoutManager.AnchorInfo r4, int r5) {
        super.onAnchorReady(r2, r3, r4, r5);
        updateMeasurements();
        if (r3.getItemCount() > 0) goto L5;
    L7:
        ensureViewSet();
        return;
    L5:
        if (r3.isPreLayout() == true) goto L7;
        ensureAnchorIsInCorrectSpan(r2, r3, r4, r5);
        goto L7
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View r24, int r25, RecyclerView.Recycler r26, RecyclerView.State r27) {
        RecyclerView.Recycler r1 = r26;
        RecyclerView.State r2 = r27;
        View r3 = findContainingItemView(r24);
        View r4 = null;
        if (r3 != null) goto L5;
        return null;
    L5:
        LayoutParams r5 = (LayoutParams) r3.getLayoutParams();
        int r6 = r5.mSpanIndex;
        int r52 = r5.mSpanSize + r6;
        if (super.onFocusSearchFailed(r24, r25, r26, r27) != null) goto L9;
        return null;
    L9:
        if (convertFocusDirectionToLayoutDirection(r25) != 1) goto L11;
        boolean r7 = true;
    L13:
        if (r7 == this.mShouldReverseLayout) goto L15;
        int r72 = getChildCount() - 1;
        int r10 = -1;
        int r12 = -1;
    L17:
        if (this.mOrientation == 1) goto L19;
    L21:
        boolean r13 = false;
    L22:
        int r14 = getSpanGroupIndex(r1, r2, r72);
        int r15 = -1;
        int r16 = -1;
        int r8 = 0;
        int r17 = 0;
        int r11 = r72;
        View r73 = null;
    L23:
        if (r11 == r10) goto L26;
        int r9 = getSpanGroupIndex(r1, r2, r11);
        View r18 = getChildAt(r11);
        if (r18 == r3) goto L26;
        if (r18.hasFocusable() == false) goto L35;
        if (r9 == r14) goto L35;
        if (r4 != null) goto L26;
        View r182 = r3;
        View r21 = r4;
    L33:
        View r19 = r73;
        int r20 = r8;
    L34:
        int r42 = r16;
        int r74 = r17;
    L76:
        r16 = r42;
        r17 = r74;
        r73 = r19;
    L74:
        r8 = r20;
        r4 = r21;
    L77:
        r11 = r11 + r12;
        r1 = r26;
        r2 = r27;
        r3 = r182;
    L35:
        LayoutParams r92 = (LayoutParams) r18.getLayoutParams();
        int r22 = r92.mSpanIndex;
        r182 = r3;
        int r32 = r92.mSpanSize + r22;
        if (r18.hasFocusable() == false) goto L41;
        if (r22 != r6) goto L41;
        if (r32 != r52) goto L41;
        return r18;
    L41:
        if (r18.hasFocusable() == false) goto L44;
        if (r4 != null) goto L44;
    L46:
        r21 = r4;
    L47:
        r19 = r73;
        r20 = r8;
        r42 = r16;
        r74 = r17;
    L70:
        boolean r82 = r18.hasFocusable();
        int r93 = r92.mSpanIndex;
        if (r82 == false) goto L73;
        r8 = Math.min(r32, r52) - Math.max(r22, r6);
        r16 = r42;
        r17 = r74;
        r15 = r93;
        r73 = r19;
        r4 = r18;
        goto L77
    L73:
        r17 = Math.min(r32, r52) - Math.max(r22, r6);
        r73 = r18;
        r16 = r93;
    L44:
        if (r18.hasFocusable() == true) goto L48;
        if (r73 == null) goto L46;
    L48:
        r21 = r4;
        int r43 = Math.min(r32, r52) - Math.max(r22, r6);
        if (r18.hasFocusable() == false) goto L58;
        if (r43 > r8) goto L47;
        if (r43 != r8) goto L33;
        if (r22 <= r15) goto L55;
        boolean r44 = true;
    L56:
        if (r13 != r44) goto L33;
    L55:
        r44 = false;
        goto L56
    L58:
        if (r21 != null) goto L33;
        r19 = r73;
        r20 = r8;
        boolean r83 = true;
        if (isViewPartiallyVisible(r18, false, true) == false) goto L34;
        r74 = r17;
        if (r43 <= r74) goto L64;
        r42 = r16;
        goto L70
    L64:
        if (r43 != r74) goto L75;
        r42 = r16;
        if (r22 > r42) goto L69;
        r83 = false;
    L69:
        if (r13 != r83) goto L76;
    L75:
        r42 = r16;
    L26:
        View r212 = r4;
        View r192 = r73;
        if (r212 == null) goto L80;
        return r212;
    L80:
        return r192;
    L19:
        if (isLayoutRTL() == false) goto L21;
        r13 = true;
        goto L22
    L15:
        r10 = getChildCount();
        r12 = 1;
        r72 = 0;
        goto L17
    L11:
        r7 = false;
        goto L13
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler r8, RecyclerView.State r9, View r10, AccessibilityNodeInfoCompat r11) {
        ViewGroup.LayoutParams r0 = r10.getLayoutParams();
        if ((r0 instanceof LayoutParams) == true) goto L6;
        super.onInitializeAccessibilityNodeInfoForItem(r10, r11);
        return;
    L6:
        LayoutParams r02 = (LayoutParams) r0;
        int r1 = getSpanGroupIndex(r8, r9, r02.getViewLayoutPosition());
        if (this.mOrientation != 0) goto L16;
        int r12 = r02.getSpanIndex();
        int r2 = r02.getSpanSize();
        if (this.mSpanCount > 1) goto L11;
    L13:
        boolean r5 = false;
    L14:
        r11.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(r12, r2, r1, 1, r5, false));
        return;
    L11:
        if (r02.getSpanSize() != this.mSpanCount) goto L13;
        r5 = true;
        goto L14
    L16:
        int r82 = r02.getSpanIndex();
        int r4 = r02.getSpanSize();
        if (this.mSpanCount > 1) goto L19;
    L21:
        boolean r52 = false;
    L22:
        r11.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(r1, 1, r82, r4, r52, false));
        return;
    L19:
        if (r02.getSpanSize() != this.mSpanCount) goto L21;
        r52 = true;
        goto L22
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView r1, int r2, int r3) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView r1) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView r1, int r2, int r3, int r4) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView r1, int r2, int r3) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView r1, int r2, int r3, Object r4) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler r2, RecyclerView.State r3) {
        if (r3.isPreLayout() == false) goto L5;
        cachePreLayoutSpanMapping();
    L5:
        super.onLayoutChildren(r2, r3);
        clearPreLayoutSpanMappingCache();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State r1) {
        super.onLayoutCompleted(r1);
        this.mPendingSpanCountChange = false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int r1, RecyclerView.Recycler r2, RecyclerView.State r3) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollHorizontallyBy(r1, r2, r3);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int r1, RecyclerView.Recycler r2, RecyclerView.State r3) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollVerticallyBy(r1, r2, r3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void setMeasuredDimension(Rect r5, int r6, int r7) {
        if (this.mCachedBorders != null) goto L5;
        super.setMeasuredDimension(r5, r6, r7);
    L5:
        int r0 = getPaddingLeft() + getPaddingRight();
        int r1 = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation != 1) goto L8;
        int r52 = RecyclerView.LayoutManager.chooseSize(r7, r5.height() + r1, getMinimumHeight());
        int[] r72 = this.mCachedBorders;
        int r62 = RecyclerView.LayoutManager.chooseSize(r6, r72[r72.length - 1] + r0, getMinimumWidth());
    L9:
        setMeasuredDimension(r62, r52);
        return;
    L8:
        r62 = RecyclerView.LayoutManager.chooseSize(r6, r5.width() + r0, getMinimumWidth());
        int[] r53 = this.mCachedBorders;
        r52 = RecyclerView.LayoutManager.chooseSize(r7, r53[r53.length - 1] + r1, getMinimumHeight());
        goto L9
    }

    public void setSpanCount(int r2) {
        if (r2 != this.mSpanCount) goto L5;
        return;
    L5:
        this.mPendingSpanCountChange = true;
        if (r2 < 1) goto L9;
        this.mSpanCount = r2;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        requestLayout();
        return;
    L9:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Span count should be at least 1. Provided ", r2);
    }

    public void setSpanSizeLookup(SpanSizeLookup r1) {
        this.mSpanSizeLookup = r1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void setStackFromEnd(boolean r1) {
        if (r1 == true) goto L5;
        super.setStackFromEnd(false);
        return;
    L5:
        ByteString$$ExternalSyntheticBUOutline0.m("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState == null) goto L5;
        return false;
    L5:
        if (this.mPendingSpanCountChange == true) goto L10;
        return true;
    L10:
        return false;
    }
}
