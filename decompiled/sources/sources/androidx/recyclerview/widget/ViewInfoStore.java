package androidx.recyclerview.widget;

import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.FileLog;

/* loaded from: classes.dex */
class ViewInfoStore {
    final ArrayMap<RecyclerView.ViewHolder, InfoRecord> mLayoutHolderMap;
    final LongSparseArray<RecyclerView.ViewHolder> mOldChangedHolders;

    public static class InfoRecord {
        static Pools$Pool<InfoRecord> sPool;
        int flags;
        RecyclerView.ItemAnimator.ItemHolderInfo postInfo;
        RecyclerView.ItemAnimator.ItemHolderInfo preInfo;

        static {
            sPool = new Pools$SimplePool(20);
        }

        private InfoRecord() {
        }

        public static void drainCache() {
        L3:
            if (sPool.acquire() != null) goto L3;
        }

        public static InfoRecord obtain() {
            InfoRecord r0 = sPool.acquire();
            if (r0 == null) goto L5;
            return r0;
        L5:
            return new InfoRecord();
        }

        public static void recycle(InfoRecord r1) {
            r1.flags = 0;
            r1.preInfo = null;
            r1.postInfo = null;
            sPool.release(r1);
        }
    }

    public interface ProcessCallback {
        void processAppeared(RecyclerView.ViewHolder r1, RecyclerView.ItemAnimator.ItemHolderInfo r2, RecyclerView.ItemAnimator.ItemHolderInfo r3);

        void processDisappeared(RecyclerView.ViewHolder r1, RecyclerView.ItemAnimator.ItemHolderInfo r2, RecyclerView.ItemAnimator.ItemHolderInfo r3);

        void processPersistent(RecyclerView.ViewHolder r1, RecyclerView.ItemAnimator.ItemHolderInfo r2, RecyclerView.ItemAnimator.ItemHolderInfo r3);

        void unused(RecyclerView.ViewHolder r1);
    }

    public ViewInfoStore() {
        this.mLayoutHolderMap = new ArrayMap();
        this.mOldChangedHolders = new LongSparseArray();
    }

    private RecyclerView.ItemAnimator.ItemHolderInfo popFromLayoutStep(RecyclerView.ViewHolder r5, int r6) {
        int r52 = this.mLayoutHolderMap.indexOfKey(r5);
        if (r52 >= 0) goto L5;
        return null;
    L5:
        InfoRecord r1 = this.mLayoutHolderMap.valueAt(r52);
        if (r1 == null) goto L20;
        int r2 = r1.flags;
        if ((r2 & r6) == 0) goto L20;
        int r22 = r2 & (~r6);
        r1.flags = r22;
        if (r6 != 4) goto L13;
        RecyclerView.ItemAnimator.ItemHolderInfo r62 = r1.preInfo;
    L16:
        if ((r22 & 12) != 0) goto L18;
        this.mLayoutHolderMap.removeAt(r52);
        InfoRecord.recycle(r1);
    L18:
        return r62;
    L13:
        if (r6 != 8) goto L19;
        r62 = r1.postInfo;
        goto L16
    L19:
        f$$ExternalSyntheticBUOutline1.m("Must provide flag PRE or POST");
    L20:
        return null;
    }

    public void addToAppearedInPreLayoutHolders(RecyclerView.ViewHolder r2, RecyclerView.ItemAnimator.ItemHolderInfo r3) {
        InfoRecord r0 = this.mLayoutHolderMap.get(r2);
        if (r0 != null) goto L5;
        r0 = InfoRecord.obtain();
        this.mLayoutHolderMap.put(r2, r0);
    L5:
        r0.flags |= 2;
        r0.preInfo = r3;
    }

    public void addToDisappearedInLayout(RecyclerView.ViewHolder r2) {
        InfoRecord r0 = this.mLayoutHolderMap.get(r2);
        if (r0 != null) goto L5;
        r0 = InfoRecord.obtain();
        this.mLayoutHolderMap.put(r2, r0);
    L5:
        r0.flags |= 1;
    }

    public void addToOldChangeHolders(long r1, RecyclerView.ViewHolder r3) {
        this.mOldChangedHolders.put(r1, r3);
    }

    public void addToPostLayout(RecyclerView.ViewHolder r2, RecyclerView.ItemAnimator.ItemHolderInfo r3) {
        InfoRecord r0 = this.mLayoutHolderMap.get(r2);
        if (r0 != null) goto L5;
        r0 = InfoRecord.obtain();
        this.mLayoutHolderMap.put(r2, r0);
    L5:
        r0.postInfo = r3;
        r0.flags |= 8;
    }

    public void addToPreLayout(RecyclerView.ViewHolder r2, RecyclerView.ItemAnimator.ItemHolderInfo r3) {
        InfoRecord r0 = this.mLayoutHolderMap.get(r2);
        if (r0 != null) goto L5;
        r0 = InfoRecord.obtain();
        this.mLayoutHolderMap.put(r2, r0);
    L5:
        r0.preInfo = r3;
        r0.flags |= 4;
    }

    public void clear() {
        this.mLayoutHolderMap.clear();
        this.mOldChangedHolders.clear();
    }

    public RecyclerView.ViewHolder getFromOldChangeHolders(long r1) {
        return this.mOldChangedHolders.get(r1);
    }

    public boolean isDisappearing(RecyclerView.ViewHolder r1) {
        InfoRecord r0 = this.mLayoutHolderMap.get(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if ((r0.flags & 1) == 0) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean isInPreLayout(RecyclerView.ViewHolder r1) {
        InfoRecord r0 = this.mLayoutHolderMap.get(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if ((r0.flags & 4) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public void onDetach() {
        InfoRecord.drainCache();
    }

    public void onViewDetached(RecyclerView.ViewHolder r1) {
        removeFromDisappearedInLayout(r1);
    }

    public RecyclerView.ItemAnimator.ItemHolderInfo popFromPostLayout(RecyclerView.ViewHolder r2) {
        return popFromLayoutStep(r2, 8);
    }

    public RecyclerView.ItemAnimator.ItemHolderInfo popFromPreLayout(RecyclerView.ViewHolder r2) {
        return popFromLayoutStep(r2, 4);
    }

    public void process(ProcessCallback r8) {
        int r0 = this.mLayoutHolderMap.size() - 1;
    L3:
        if (r0 < 0) goto L34;
        RecyclerView.ViewHolder r1 = this.mLayoutHolderMap.keyAt(r0);
        InfoRecord r3 = this.mLayoutHolderMap.removeAt(r0);     // Catch: Exception -> L7
    L9:
        if (r3 == null) goto L33;
        int r4 = r3.flags;
        if ((r4 & 3) != 3) goto L15;
        r8.unused(r1);
    L32:
        InfoRecord.recycle(r3);
        goto L33
    L15:
        if ((r4 & 1) == 0) goto L21;
        RecyclerView.ItemAnimator.ItemHolderInfo r2 = r3.preInfo;
        if (r2 != null) goto L19;
        r8.unused(r1);
        goto L32
    L19:
        r8.processDisappeared(r1, r2, r3.postInfo);
        goto L32
    L21:
        if ((r4 & 14) != 14) goto L24;
        r8.processAppeared(r1, r3.preInfo, r3.postInfo);
        goto L32
    L24:
        if ((r4 & 12) != 12) goto L27;
        r8.processPersistent(r1, r3.preInfo, r3.postInfo);
        goto L32
    L27:
        if ((r4 & 4) == 0) goto L30;
        r8.processDisappeared(r1, r3.preInfo, null);
        goto L32
    L30:
        if ((r4 & 8) == 0) goto L32;
        r8.processAppeared(r1, r3.preInfo, r3.postInfo);
    L33:
        r0 = r0 - 1;
    L7:
        e = move-exception;
        FileLog.e(e);
        r3 = null;
        goto L9
    }

    public void removeFromDisappearedInLayout(RecyclerView.ViewHolder r1) {
        InfoRecord r0 = this.mLayoutHolderMap.get(r1);
        if (r0 != null) goto L5;
        return;
    L5:
        r0.flags &= -2;
    }

    public void removeViewHolder(RecyclerView.ViewHolder r3) {
        int r0 = this.mOldChangedHolders.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (r3 == this.mOldChangedHolders.valueAt(r0)) goto L6;
        r0 = r0 - 1;
        goto L3
    L6:
        this.mOldChangedHolders.removeAt(r0);
    L8:
        InfoRecord r02 = this.mLayoutHolderMap.get(r3);
        if (r02 == null) goto L14;
        this.mLayoutHolderMap.remove(r3);
        InfoRecord.recycle(r02);
        return;
    }
}
