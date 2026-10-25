package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import androidx.core.os.TraceCompat;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

/* loaded from: classes.dex */
final class GapWorker implements Runnable {
    static final ThreadLocal<GapWorker> sGapWorker = null;
    static Comparator<Task> sTaskComparator;
    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList<RecyclerView> mRecyclerViews;
    private ArrayList<Task> mTasks;

    /* renamed from: androidx.recyclerview.widget.GapWorker$1, reason: invalid class name */
    public class AnonymousClass1 implements Comparator<Task> {
        public AnonymousClass1() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Task r6, Task r7) {
            RecyclerView r5 = r6.view;
            if (r5 != null) goto L5;
            boolean r2 = true;
        L7:
            if (r7.view != null) goto L9;
            boolean r3 = true;
        L11:
            if (r2 == r3) goto L15;
            if (r5 != null) goto L14;
            return 1;
        L14:
            return -1;
        L15:
            boolean r52 = r6.immediate;
            if (r52 == r7.immediate) goto L20;
            if (r52 == false) goto L19;
            return -1;
        L19:
            return 1;
        L20:
            int r53 = r7.viewVelocity - r6.viewVelocity;
            if (r53 == 0) goto L23;
            return r53;
        L23:
            int r54 = r6.distanceToItem - r7.distanceToItem;
            if (r54 == 0) goto L26;
            return r54;
        L26:
            return 0;
        L9:
            r3 = false;
            goto L11
        L5:
            r2 = false;
            goto L7
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Task r1, Task r2) {
            return compare2(r1, r2);
        }
    }

    @SuppressLint({"VisibleForTests"})
    public static class LayoutPrefetchRegistryImpl implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {
        int mCount;
        int[] mPrefetchArray;
        int mPrefetchDx;
        int mPrefetchDy;

        public LayoutPrefetchRegistryImpl() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry
        public void addPosition(int r6, int r7) {
            if (r6 < 0) goto L14;
            if (r7 < 0) goto L12;
            int r0 = this.mCount;
            int r1 = r0 * 2;
            int[] r2 = this.mPrefetchArray;
            if (r2 != null) goto L8;
            int[] r02 = new int[4];
            this.mPrefetchArray = r02;
            Arrays.fill(r02, -1);
        L10:
            int[] r03 = this.mPrefetchArray;
            r03[r1] = r6;
            r03[r1 + 1] = r7;
            this.mCount++;
            return;
        L8:
            if (r1 < r2.length) goto L10;
            int[] r04 = new int[r0 * 4];
            this.mPrefetchArray = r04;
            System.arraycopy(r2, 0, r04, 0, r2.length);
            goto L10
        L12:
            f$$ExternalSyntheticBUOutline1.m("Pixel distance must be non-negative");
            return;
        L14:
            f$$ExternalSyntheticBUOutline1.m("Layout positions must be non-negative");
        }

        public void clearPrefetchPositions() {
            int[] r0 = this.mPrefetchArray;
            if (r0 == null) goto L5;
            Arrays.fill(r0, -1);
        L5:
            this.mCount = 0;
        }

        public void collectPrefetchPositionsFromView(RecyclerView r5, boolean r6) {
            this.mCount = 0;
            int[] r0 = this.mPrefetchArray;
            if (r0 == null) goto L5;
            Arrays.fill(r0, -1);
        L5:
            RecyclerView.LayoutManager r02 = r5.mLayout;
            if (r5.mAdapter == null) goto L21;
            if (r02 != null) goto L9;
            return;
        L9:
            if (r02.isItemPrefetchEnabled() == false) goto L23;
            if (r6 == false) goto L15;
            if (r5.mAdapterHelper.hasPendingUpdates() == true) goto L17;
            r02.collectInitialPrefetchPositions(r5.mAdapter.getItemCount(), this);
        L17:
            int r4 = this.mCount;
            if (r4 <= r02.mPrefetchMaxCountObserved) goto L24;
            r02.mPrefetchMaxCountObserved = r4;
            r02.mPrefetchMaxObservedInInitialPrefetch = r6;
            r5.mRecycler.updateViewCacheSize();
            return;
        L24:
            return;
        L15:
            if (r5.hasPendingAdapterUpdates() == true) goto L17;
            r02.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, r5.mState, this);
            goto L17
        L23:
            return;
        }

        public boolean lastPrefetchIncludedPosition(int r5) {
            if (this.mPrefetchArray == null) goto L11;
            int r0 = this.mCount * 2;
            int r2 = 0;
        L5:
            if (r2 >= r0) goto L11;
            if (this.mPrefetchArray[r2] == r5) goto L8;
            r2 = r2 + 2;
            goto L5
        L8:
            return true;
        L11:
            return false;
        }

        public void setPrefetchVector(int r1, int r2) {
            this.mPrefetchDx = r1;
            this.mPrefetchDy = r2;
        }
    }

    /* loaded from: classes4.dex */
    public static class Task {
        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        public Task() {
        }

        public void clear() {
            this.immediate = false;
            this.viewVelocity = 0;
            this.distanceToItem = 0;
            this.view = null;
            this.position = 0;
        }
    }

    public static /* synthetic */ void $r8$lambda$AzUCWB2uRWYN3md5QS0GYsl7qPU(RecyclerView r1) {
        if (r1.getAdapter() == null) goto L6;
        r1.getAdapter().notifyDataSetChanged();
        return;
    }

    static {
        sGapWorker = new ThreadLocal();
        sTaskComparator = new AnonymousClass1();
    }

    public GapWorker() {
        this.mRecyclerViews = new ArrayList();
        this.mTasks = new ArrayList();
    }

    private void buildTaskList() {
        int r0 = this.mRecyclerViews.size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L8;
        RecyclerView r4 = this.mRecyclerViews.get(r2);
        if (r4.getWindowVisibility() != 0) goto L7;
        r4.mPrefetchRegistry.collectPrefetchPositionsFromView(r4, false);
        r3 = r3 + r4.mPrefetchRegistry.mCount;
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        this.mTasks.ensureCapacity(r3);
        int r22 = 0;
        int r32 = 0;
    L9:
        if (r22 >= r0) goto L26;
        RecyclerView r42 = this.mRecyclerViews.get(r22);
        if (r42.getWindowVisibility() != 0) goto L25;
        LayoutPrefetchRegistryImpl r5 = r42.mPrefetchRegistry;
        int r6 = Math.abs(r5.mPrefetchDx) + Math.abs(r5.mPrefetchDy);
        int r7 = 0;
    L15:
        if (r7 >= (r5.mCount * 2)) goto L25;
        if (r32 < this.mTasks.size()) goto L19;
        Task r8 = new Task();
        this.mTasks.add(r8);
    L20:
        int[] r9 = r5.mPrefetchArray;
        int r10 = r9[r7 + 1];
        if (r10 > r6) goto L23;
        boolean r11 = true;
    L24:
        r8.immediate = r11;
        r8.viewVelocity = r6;
        r8.distanceToItem = r10;
        r8.view = r42;
        r8.position = r9[r7];
        r32 = r32 + 1;
        r7 = r7 + 2;
        goto L15
    L23:
        r11 = false;
        goto L24
    L19:
        r8 = this.mTasks.get(r32);
    L25:
        r22 = r22 + 1;
        goto L9
    L26:
        Collections.sort(this.mTasks, sTaskComparator);
    }

    private void flushTaskWithDeadline(Task r4, long r5) {
        if (r4.immediate == false) goto L5;
        long r0 = LongCompanionObject.MAX_VALUE;
    L6:
        RecyclerView.ViewHolder r42 = prefetchPositionWithDeadline(r4.view, r4.position, r0);
        if (r42 != null) goto L9;
        return;
    L9:
        if (r42.mNestedRecyclerView != null) goto L11;
        return;
    L11:
        if (r42.isBound() == true) goto L13;
        return;
    L13:
        if (r42.isInvalid() == true) goto L19;
        prefetchInnerRecyclerViewWithDeadline(r42.mNestedRecyclerView.get(), r5);
        return;
    L19:
        return;
    L5:
        r0 = r5;
        goto L6
    }

    private void flushTasksWithDeadline(long r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTasks.size()) goto L9;
        Task r1 = this.mTasks.get(r0);
        if (r1.view == null) goto L12;
        flushTaskWithDeadline(r1, r4);
        r1.clear();
        r0 = r0 + 1;
        goto L4
    L12:
        return;
    }

    public static boolean isPrefetchPositionAttached(RecyclerView r5, int r6) {
        int r0 = r5.mChildHelper.getUnfilteredChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        RecyclerView.ViewHolder r3 = RecyclerView.getChildViewHolderInt(r5.mChildHelper.getUnfilteredChildAt(r2));
        if (r3.mPosition != r6) goto L10;
        if (r3.isInvalid() == true) goto L10;
        return true;
    L10:
        r2 = r2 + 1;
        goto L3
    L11:
        return false;
    }

    private void prefetchInnerRecyclerViewWithDeadline(RecyclerView r4, long r5) {
        if (r4 != null) goto L5;
        return;
    L5:
        if (r4.mDataSetHasChangedAfterLayout == true) goto L7;
    L9:
        LayoutPrefetchRegistryImpl r0 = r4.mPrefetchRegistry;
        r0.collectPrefetchPositionsFromView(r4, true);
        if (r0.mCount != 0) goto L22;
        return;
    L22:
        TraceCompat.beginSection("RV Nested Prefetch");     // Catch: Throwable -> L18
        r4.mState.prepareForNestedPrefetch(r4.mAdapter);     // Catch: Throwable -> L18
        int r1 = 0;
    L12:
        if (r1 >= (r0.mCount * 2)) goto L16;
        prefetchPositionWithDeadline(r4, r0.mPrefetchArray[r1], r5);     // Catch: Throwable -> L18
        r1 = r1 + 2;
        goto L12
    L16:
        TraceCompat.endSection();
        return;
    L18:
        th = move-exception;
        TraceCompat.endSection();
        throw th;
    L7:
        if (r4.mChildHelper.getUnfilteredChildCount() == 0) goto L9;
        r4.removeAndRecycleViews();
        goto L9
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView r3, int r4, long r5) {
        if (isPrefetchPositionAttached(r3, r4) == false) goto L5;
        return null;
    L5:
        RecyclerView.Recycler r2 = r3.mRecycler;
        r3.onEnterLayoutOrScroll();     // Catch: Throwable -> L13 Exception -> L15
        RecyclerView.ViewHolder r42 = r2.tryGetViewHolderForPositionByDeadline(r4, false, r5);     // Catch: Throwable -> L13 Exception -> L15
        if (r42 != null) goto L9;
    L18:
        r3.onExitLayoutOrScroll(false);
        return r42;
    L9:
        if (r42.isBound() == true) goto L11;
    L17:
        r2.addViewHolderToRecycledViewPool(r42, false);     // Catch: Throwable -> L13 Exception -> L15
        goto L18
    L11:
        if (r42.isInvalid() == true) goto L17;
        r2.recycleView(r42.itemView);     // Catch: Throwable -> L13 Exception -> L15
    L13:
        th = move-exception;
        r3.onExitLayoutOrScroll(false);
        throw th;
    L15:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L13
        AndroidUtilities.runOnUIThread(new GapWorker$$ExternalSyntheticLambda0(r3));     // Catch: Throwable -> L13
        r3.onExitLayoutOrScroll(false);
        return null;
    }

    public void add(RecyclerView r1) {
        this.mRecyclerViews.add(r1);
    }

    public void postFromTraversal(RecyclerView r5, int r6, int r7) {
        if (r5.isAttachedToWindow() == true) goto L5;
    L7:
        r5.mPrefetchRegistry.setPrefetchVector(r6, r7);
        return;
    L5:
        if (this.mPostTimeNs != 0) goto L7;
        this.mPostTimeNs = r5.getNanoTime();
        r5.post(this);
        goto L7
    }

    public void prefetch(long r1) {
        buildTaskList();
        flushTasksWithDeadline(r1);
    }

    public void remove(RecyclerView r1) {
        this.mRecyclerViews.remove(r1);
    }

    @Override // java.lang.Runnable
    public void run() {
        TraceCompat.beginSection("RV Prefetch");     // Catch: Throwable -> L12
        if (this.mRecyclerViews.isEmpty() == false) goto L7;
    L5:
        this.mPostTimeNs = 0;
        TraceCompat.endSection();
        return;
    L7:
        int r2 = this.mRecyclerViews.size();     // Catch: Throwable -> L12
        int r3 = 0;
        long r4 = 0;
    L8:
        if (r3 >= r2) goto L16;
        RecyclerView r6 = this.mRecyclerViews.get(r3);     // Catch: Throwable -> L12
        if (r6.getWindowVisibility() != 0) goto L14;
        r4 = Math.max(r6.getDrawingTime(), r4);     // Catch: Throwable -> L12
    L14:
        r3 = r3 + 1;     // Catch: Throwable -> L12
        goto L8
    L16:
        if (r4 == 0) goto L5;
        prefetch(TimeUnit.MILLISECONDS.toNanos(r4) + this.mFrameIntervalNs);     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        this.mPostTimeNs = 0;
        TraceCompat.endSection();
        throw th;
    }
}
