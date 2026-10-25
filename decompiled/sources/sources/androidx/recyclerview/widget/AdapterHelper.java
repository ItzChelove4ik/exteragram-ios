package androidx.recyclerview.widget;

import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import androidx.recyclerview.widget.OpReorderer;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.telegram.messenger.BuildVars;

/* loaded from: classes.dex */
class AdapterHelper implements OpReorderer.Callback {
    private final ArrayList<String> lastNotifies;
    final Callback mCallback;
    final boolean mDisableRecycler;
    private int mExistingUpdateTypes;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList<UpdateOp> mPendingUpdates;
    final ArrayList<UpdateOp> mPostponedList;
    private Pools$Pool<UpdateOp> mUpdateOpPool;

    public interface Callback {
        RecyclerView.ViewHolder findViewHolder(int r1);

        void markViewHoldersUpdated(int r1, int r2, Object r3);

        void offsetPositionsForAdd(int r1, int r2);

        void offsetPositionsForMove(int r1, int r2);

        void offsetPositionsForRemovingInvisible(int r1, int r2);

        void offsetPositionsForRemovingLaidOutOrNewView(int r1, int r2);

        void onDispatchFirstPass(UpdateOp r1);

        void onDispatchSecondPass(UpdateOp r1);
    }

    public static class UpdateOp {
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        public UpdateOp(int r1, int r2, int r3, Object r4) {
            this.cmd = r1;
            this.positionStart = r2;
            this.itemCount = r3;
            this.payload = r4;
        }

        public String cmdToString() {
            int r1 = this.cmd;
            if (r1 != 1) goto L5;
            return "add";
        L5:
            if (r1 != 2) goto L7;
            return "rm";
        L7:
            if (r1 != 4) goto L9;
            return "up";
        L9:
            if (r1 == 8) goto L12;
            return "??";
        L12:
            return "mv";
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L36:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L36;
            UpdateOp r52 = (UpdateOp) r5;
            int r2 = this.cmd;
            if (r2 == r52.cmd) goto L14;
            return false;
        L14:
            if (r2 != 8) goto L23;
            if (Math.abs(this.itemCount - this.positionStart) != 1) goto L23;
            if (this.itemCount != r52.positionStart) goto L23;
            if (this.positionStart != r52.itemCount) goto L23;
            return true;
        L23:
            if (this.itemCount == r52.itemCount) goto L26;
            return false;
        L26:
            if (this.positionStart == r52.positionStart) goto L28;
            return false;
        L28:
            Object r4 = this.payload;
            Object r53 = r52.payload;
            if (r4 != null) goto L31;
            if (r53 == null) goto L35;
            return false;
        L35:
            return true;
        L31:
            if (r4.equals(r53) == true) goto L35;
            return false;
        }

        public int hashCode() {
            return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + ",p:" + this.payload + "]";
        }
    }

    public AdapterHelper(Callback r2) {
        this(r2, false);
    }

    public AdapterHelper(Callback r3, boolean r4) {
        this.mUpdateOpPool = new Pools$SimplePool(30);
        this.mPendingUpdates = new ArrayList();
        this.mPostponedList = new ArrayList();
        this.mExistingUpdateTypes = 0;
        if (BuildVars.DEBUG_VERSION == false) goto L5;
        ArrayList<String> r0 = new ArrayList();
    L6:
        this.lastNotifies = r0;
        this.mCallback = r3;
        this.mDisableRecycler = r4;
        this.mOpReorderer = new OpReorderer(this);
        return;
    L5:
        r0 = null;
        goto L6
    }

    private void applyAdd(UpdateOp r1) {
        postponeAndUpdateViewHolders(r1);
    }

    private void applyMove(UpdateOp r1) {
        postponeAndUpdateViewHolders(r1);
    }

    private void applyRemove(UpdateOp r11) {
        int r0 = r11.positionStart;
        int r1 = r11.itemCount + r0;
        char r3 = 65535;
        int r4 = r0;
        int r5 = 0;
    L4:
        if (r4 >= r1) goto L23;
        if (this.mCallback.findViewHolder(r4) == null) goto L8;
    L14:
        if (r3 != 0) goto L16;
        dispatchAndUpdateViewHolders(obtainUpdateOp(2, r0, r5, null));
        boolean r32 = true;
    L17:
        char r6 = 1;
    L18:
        if (r32 == false) goto L20;
        r4 = r4 - r5;
        r1 = r1 - r5;
        r5 = 1;
    L21:
        r4 = r4 + 1;
        r3 = r6;
        goto L4
    L20:
        r5 = r5 + 1;
        goto L21
    L16:
        r32 = false;
        goto L17
    L8:
        if (canFindInPreLayout(r4) == true) goto L14;
        if (r3 != 1) goto L12;
        postponeAndUpdateViewHolders(obtainUpdateOp(2, r0, r5, null));
        r32 = true;
    L13:
        r6 = 0;
        goto L18
    L12:
        r32 = false;
        goto L13
    L23:
        if (r5 == r11.itemCount) goto L25;
        recycleUpdateOp(r11);
        r11 = obtainUpdateOp(2, r0, r5, null);
    L25:
        if (r3 != 0) goto L28;
        dispatchAndUpdateViewHolders(r11);
        return;
    L28:
        postponeAndUpdateViewHolders(r11);
    }

    private void applyUpdate(UpdateOp r10) {
        int r0 = r10.positionStart;
        int r1 = r10.itemCount + r0;
        int r5 = 0;
        boolean r4 = -1;
        int r3 = r0;
    L4:
        if (r0 >= r1) goto L18;
        if (this.mCallback.findViewHolder(r0) == null) goto L8;
    L13:
        if (r4 == true) goto L15;
        dispatchAndUpdateViewHolders(obtainUpdateOp(4, r3, r5, r10.payload));
        r3 = r0;
        r5 = 0;
    L15:
        r4 = true;
    L16:
        r5 = r5 + 1;
        r0 = r0 + 1;
        goto L4
    L8:
        if (canFindInPreLayout(r0) == true) goto L13;
        if (r4 != true) goto L12;
        postponeAndUpdateViewHolders(obtainUpdateOp(4, r3, r5, r10.payload));
        r3 = r0;
        r5 = 0;
    L12:
        r4 = false;
        goto L16
    L18:
        if (r5 == r10.itemCount) goto L20;
        Object r02 = r10.payload;
        recycleUpdateOp(r10);
        r10 = obtainUpdateOp(4, r3, r5, r02);
    L20:
        if (r4 == true) goto L23;
        dispatchAndUpdateViewHolders(r10);
        return;
    L23:
        postponeAndUpdateViewHolders(r10);
    }

    private boolean canFindInPreLayout(int r8) {
        int r0 = this.mPostponedList.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L17;
        UpdateOp r3 = this.mPostponedList.get(r2);
        int r4 = r3.cmd;
        if (r4 == 8) goto L7;
        if (r4 != 1) goto L16;
        int r42 = r3.positionStart;
        int r32 = r3.itemCount + r42;
    L11:
        if (r42 >= r32) goto L16;
        if (findPositionOffset(r42, r2 + 1) == r8) goto L14;
        r42 = r42 + 1;
        goto L11
    L14:
        return true;
    L16:
        r2 = r2 + 1;
        goto L3
    L7:
        if (findPositionOffset(r3.itemCount, r2 + 1) != r8) goto L16;
        return true;
    L17:
        return false;
    }

    private void dispatchAndUpdateViewHolders(UpdateOp r12) {
        int r0 = r12.cmd;
        if (r0 != 1) goto L5;
    L34:
        f$$ExternalSyntheticBUOutline1.m("should not dispatch add or move for pre layout");
        return;
    L5:
        if (r0 == 8) goto L34;
        int r02 = updatePositionWithPostponed(r12.positionStart, r0);
        int r2 = r12.positionStart;
        int r3 = r12.cmd;
        if (r3 == 2) goto L12;
        if (r3 != 4) goto L10;
        int r32 = 1;
    L13:
        int r6 = 1;
        int r7 = 1;
    L15:
        if (r6 >= r12.itemCount) goto L30;
        int r8 = updatePositionWithPostponed(r12.positionStart + (r32 * r6), r12.cmd);
        int r9 = r12.cmd;
        if (r9 == 2) goto L23;
        if (r9 == 4) goto L21;
    L25:
        UpdateOp r03 = obtainUpdateOp(r9, r02, r7, r12.payload);
        dispatchFirstPassAndUpdateViewHolders(r03, r2);
        recycleUpdateOp(r03);
        if (r12.cmd != 4) goto L28;
        r2 = r2 + r7;
    L28:
        r7 = 1;
        r02 = r8;
    L29:
        r6 = r6 + 1;
        goto L15
    L21:
        if (r8 != (r02 + 1)) goto L25;
    L24:
        r7 = r7 + 1;
        goto L29
    L23:
        if (r8 != r02) goto L25;
    L30:
        Object r1 = r12.payload;
        recycleUpdateOp(r12);
        if (r7 <= 0) goto L39;
        UpdateOp r122 = obtainUpdateOp(r12.cmd, r02, r7, r1);
        dispatchFirstPassAndUpdateViewHolders(r122, r2);
        recycleUpdateOp(r122);
        return;
    L39:
        return;
    L10:
        Native$$ExternalSyntheticBUOutline5.m("op should be remove or update.", r12);
        return;
    L12:
        r32 = 0;
        goto L13
    }

    private void postponeAndUpdateViewHolders(UpdateOp r3) {
        this.mPostponedList.add(r3);
        int r0 = r3.cmd;
        if (r0 != 1) goto L5;
        this.mCallback.offsetPositionsForAdd(r3.positionStart, r3.itemCount);
        return;
    L5:
        if (r0 != 2) goto L7;
        this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(r3.positionStart, r3.itemCount);
        return;
    L7:
        if (r0 != 4) goto L9;
        this.mCallback.markViewHoldersUpdated(r3.positionStart, r3.itemCount, r3.payload);
        return;
    L9:
        if (r0 != 8) goto L12;
        this.mCallback.offsetPositionsForMove(r3.positionStart, r3.itemCount);
        return;
    L12:
        Native$$ExternalSyntheticBUOutline5.m("Unknown update op type for ", r3);
    }

    private int updatePositionWithPostponed(int r9, int r10) {
        int r0 = this.mPostponedList.size() - 1;
    L3:
        ArrayList<UpdateOp> r2 = this.mPostponedList;
        if (r0 < 0) goto L39;
        UpdateOp r22 = r2.get(r0);
        int r4 = r22.cmd;
        int r5 = r22.positionStart;
        if (r4 != 8) goto L29;
        int r3 = r22.itemCount;
        if (r5 >= r3) goto L10;
        int r7 = r3;
        int r42 = r5;
    L11:
        if (r9 < r42) goto L24;
        if (r9 > r7) goto L24;
        if (r42 != r5) goto L19;
        if (r10 != 1) goto L16;
        r22.itemCount = r3 + 1;
    L18:
        r9 = r9 + 1;
    L38:
        r0 = r0 - 1;
        goto L3
    L16:
        if (r10 != 2) goto L18;
        r22.itemCount = r3 - 1;
        goto L18
    L19:
        if (r10 != 1) goto L21;
        r22.positionStart = r5 + 1;
    L23:
        r9 = r9 - 1;
        goto L38
    L21:
        if (r10 != 2) goto L23;
        r22.positionStart = r5 - 1;
    L24:
        if (r9 >= r5) goto L38;
        if (r10 != 1) goto L27;
        r22.positionStart = r5 + 1;
        r22.itemCount = r3 + 1;
        goto L38
    L27:
        if (r10 != 2) goto L38;
        r22.positionStart = r5 - 1;
        r22.itemCount = r3 - 1;
        goto L38
    L10:
        r42 = r3;
        r7 = r5;
        goto L11
    L29:
        if (r5 > r9) goto L34;
        if (r4 != 1) goto L32;
        r9 = r9 - r22.itemCount;
        goto L38
    L32:
        if (r4 != 2) goto L38;
        r9 = r9 + r22.itemCount;
        goto L38
    L34:
        if (r10 != 1) goto L36;
        r22.positionStart = r5 + 1;
        goto L38
    L36:
        if (r10 != 2) goto L38;
        r22.positionStart = r5 - 1;
        goto L38
    L39:
        int r102 = r2.size() - 1;
    L40:
        if (r102 < 0) goto L50;
        UpdateOp r02 = this.mPostponedList.get(r102);
        int r1 = r02.cmd;
        int r23 = r02.itemCount;
        if (r1 == 8) goto L44;
        if (r23 > 0) goto L49;
        this.mPostponedList.remove(r102);
        recycleUpdateOp(r02);
    L49:
        r102 = r102 - 1;
        goto L40
    L44:
        if (r23 == r02.positionStart) goto L46;
        if (r23 >= 0) goto L49;
    L46:
        this.mPostponedList.remove(r102);
        recycleUpdateOp(r02);
        goto L49
    L50:
        return r9;
    }

    public int applyPendingUpdatesToPosition(int r6) {
        int r0 = this.mPendingUpdates.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L30;
        UpdateOp r2 = this.mPendingUpdates.get(r1);
        int r3 = r2.cmd;
        if (r3 == 1) goto L27;
        if (r3 != 2) goto L9;
        int r32 = r2.positionStart;
        if (r32 > r6) goto L29;
        int r22 = r2.itemCount;
        if ((r32 + r22) > r6) goto L23;
        r6 = r6 - r22;
        goto L29
    L23:
        return -1;
    L29:
        r1 = r1 + 1;
        goto L3
    L9:
        if (r3 != 8) goto L29;
        int r33 = r2.positionStart;
        if (r33 != r6) goto L14;
        r6 = r2.itemCount;
        goto L29
    L14:
        if (r33 >= r6) goto L17;
        r6 = r6 - 1;
    L17:
        if (r2.itemCount > r6) goto L29;
        r6 = r6 + 1;
        goto L29
    L27:
        if (r2.positionStart > r6) goto L29;
        r6 = r6 + r2.itemCount;
        goto L29
    L30:
        return r6;
    }

    public void consumePostponedUpdates() {
        int r0 = this.mPostponedList.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        this.mCallback.onDispatchSecondPass(this.mPostponedList.get(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    public void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int r0 = this.mPendingUpdates.size();
        int r2 = 0;
    L3:
        ArrayList<UpdateOp> r3 = this.mPendingUpdates;
        if (r2 >= r0) goto L22;
        UpdateOp r32 = r3.get(r2);
        int r4 = r32.cmd;
        if (r4 != 1) goto L8;
        this.mCallback.onDispatchSecondPass(r32);
        this.mCallback.offsetPositionsForAdd(r32.positionStart, r32.itemCount);
    L18:
        Runnable r33 = this.mOnItemProcessedCallback;
        if (r33 == null) goto L21;
        r33.run();
    L21:
        r2 = r2 + 1;
        goto L3
    L8:
        if (r4 != 2) goto L10;
        this.mCallback.onDispatchSecondPass(r32);
        this.mCallback.offsetPositionsForRemovingInvisible(r32.positionStart, r32.itemCount);
        goto L18
    L10:
        if (r4 != 4) goto L12;
        this.mCallback.onDispatchSecondPass(r32);
        this.mCallback.markViewHoldersUpdated(r32.positionStart, r32.itemCount, r32.payload);
        goto L18
    L12:
        if (r4 != 8) goto L18;
        this.mCallback.onDispatchSecondPass(r32);
        this.mCallback.offsetPositionsForMove(r32.positionStart, r32.itemCount);
        goto L18
    L22:
        recycleUpdateOpsAndClearList(r3);
        this.mExistingUpdateTypes = 0;
    }

    public void dispatchFirstPassAndUpdateViewHolders(UpdateOp r3, int r4) {
        this.mCallback.onDispatchFirstPass(r3);
        int r0 = r3.cmd;
        if (r0 != 2) goto L5;
        this.mCallback.offsetPositionsForRemovingInvisible(r4, r3.itemCount);
        return;
    L5:
        if (r0 != 4) goto L8;
        this.mCallback.markViewHoldersUpdated(r4, r3.itemCount, r3.payload);
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("only remove and update ops can be dispatched in first pass");
    }

    public int findPositionOffset(int r2) {
        return findPositionOffset(r2, 0);
    }

    public int findPositionOffset(int r6, int r7) {
        int r0 = this.mPostponedList.size();
    L3:
        if (r7 >= r0) goto L25;
        UpdateOp r1 = this.mPostponedList.get(r7);
        int r2 = r1.cmd;
        int r3 = r1.positionStart;
        if (r2 != 8) goto L13;
        if (r3 != r6) goto L8;
        r6 = r1.itemCount;
    L24:
        r7 = r7 + 1;
        goto L3
    L8:
        if (r3 >= r6) goto L11;
        r6 = r6 - 1;
    L11:
        if (r1.itemCount > r6) goto L24;
        r6 = r6 + 1;
        goto L24
    L13:
        if (r3 > r6) goto L24;
        if (r2 != 2) goto L22;
        int r12 = r1.itemCount;
        if (r6 < (r3 + r12)) goto L18;
        r6 = r6 - r12;
        goto L24
    L18:
        return -1;
    L22:
        if (r2 != 1) goto L24;
        r6 = r6 + r1.itemCount;
        goto L24
    L25:
        return r6;
    }

    public String getLastNotifies() {
        if (this.lastNotifies != null) goto L6;
        return null;
    L6:
        StringBuilder r0 = new StringBuilder();
        int r1 = this.lastNotifies.size() - 1;
    L7:
        if (r1 < 0) goto L13;
        if (r1 >= (this.lastNotifies.size() - 1)) goto L11;
        r0.append("\n\n");
    L11:
        r0.append(this.lastNotifies.get(r1));
        r1 = r1 - 1;
        goto L7
    L13:
        return r0.toString();
    }

    public boolean hasAnyUpdateTypes(int r1) {
        if ((this.mExistingUpdateTypes & r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasPendingUpdates() {
        if (this.mPendingUpdates.size() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasUpdates() {
        if (this.mPostponedList.isEmpty() == false) goto L5;
        return false;
    L5:
        if (this.mPendingUpdates.isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public void logNotify(String r8) {
        if (this.lastNotifies != null) goto L5;
        return;
    L5:
        int r2 = 0;
        if (this.lastNotifies.size() <= 5) goto L8;
        this.lastNotifies.remove(0);
        goto L5
    L8:
        StringBuilder r0 = new StringBuilder();
        r0.append(new Date().toString());
        r0.append("  ");
        r0.append(r8);
        r0.append("\n");
        StackTraceElement[] r3 = new Exception().getStackTrace();
        int r4 = 0;
    L10:
        if (r2 >= r3.length) goto L18;
        if (r4 >= 5) goto L18;
        String r5 = r3[r2].toString();
        if (r5.startsWith("androidx.recyclerview.widget.") == false) goto L16;
        if (r4 != 0) goto L16;
    L17:
        r2 = r2 + 1;
    L16:
        r0.append("\n");
        r0.append(r5);
        r0.append("\n");
        r4 = r4 + 1;
    L18:
        this.lastNotifies.add(r0.toString());
    }

    @Override // androidx.recyclerview.widget.OpReorderer.Callback
    public UpdateOp obtainUpdateOp(int r1, int r2, int r3, Object r4) {
        UpdateOp r0 = this.mUpdateOpPool.acquire();
        if (r0 == null) goto L5;
        r0.cmd = r1;
        r0.positionStart = r2;
        r0.itemCount = r3;
        r0.payload = r4;
        return r0;
    L5:
        return new UpdateOp(r1, r2, r3, r4);
    }

    public boolean onItemRangeChanged(int r5, int r6, Object r7) {
        if (r6 >= 1) goto L6;
        return false;
    L6:
        if (BuildVars.DEBUG_VERSION == false) goto L8;
        logNotify("onItemRangeChanged(" + r5 + ", " + r6 + ", " + r7 + ")");
    L8:
        this.mPendingUpdates.add(obtainUpdateOp(4, r5, r6, r7));
        this.mExistingUpdateTypes |= 4;
        if (this.mPendingUpdates.size() != 1) goto L11;
        return true;
    L11:
        return false;
    }

    public boolean onItemRangeInserted(int r5, int r6) {
        if (r6 >= 1) goto L6;
        return false;
    L6:
        if (BuildVars.DEBUG_VERSION == false) goto L8;
        logNotify("onItemRangeInserted(" + r5 + ", " + r6 + ")");
    L8:
        this.mPendingUpdates.add(obtainUpdateOp(1, r5, r6, null));
        this.mExistingUpdateTypes |= 1;
        if (this.mPendingUpdates.size() != 1) goto L11;
        return true;
    L11:
        return false;
    }

    public boolean onItemRangeMoved(int r5, int r6, int r7) {
        if (r5 != r6) goto L6;
        return false;
    L6:
        if (r7 == 1) goto L8;
        f$$ExternalSyntheticBUOutline1.m("Moving more than 1 item is not supported yet");
        return false;
    L8:
        if (BuildVars.DEBUG_VERSION == false) goto L10;
        logNotify("onItemRangeMoved(" + r5 + ", " + r6 + ", " + r7 + ")");
    L10:
        this.mPendingUpdates.add(obtainUpdateOp(8, r5, r6, null));
        this.mExistingUpdateTypes |= 8;
        if (this.mPendingUpdates.size() != 1) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean onItemRangeRemoved(int r6, int r7) {
        if (r7 >= 1) goto L6;
        return false;
    L6:
        if (BuildVars.DEBUG_VERSION == false) goto L8;
        logNotify("onItemRangeRemoved(" + r6 + ", " + r7 + ")");
    L8:
        this.mPendingUpdates.add(obtainUpdateOp(2, r6, r7, null));
        this.mExistingUpdateTypes |= 2;
        if (this.mPendingUpdates.size() != 1) goto L11;
        return true;
    L11:
        return false;
    }

    public void preProcess() {
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int r0 = this.mPendingUpdates.size();
        int r1 = 0;
    L3:
        ArrayList<UpdateOp> r2 = this.mPendingUpdates;
        if (r1 >= r0) goto L22;
        UpdateOp r22 = r2.get(r1);
        int r3 = r22.cmd;
        if (r3 != 1) goto L8;
        applyAdd(r22);
    L18:
        Runnable r23 = this.mOnItemProcessedCallback;
        if (r23 == null) goto L21;
        r23.run();
    L21:
        r1 = r1 + 1;
        goto L3
    L8:
        if (r3 != 2) goto L10;
        applyRemove(r22);
        goto L18
    L10:
        if (r3 != 4) goto L12;
        applyUpdate(r22);
        goto L18
    L12:
        if (r3 != 8) goto L18;
        applyMove(r22);
        goto L18
    L22:
        r2.clear();
    }

    @Override // androidx.recyclerview.widget.OpReorderer.Callback
    public void recycleUpdateOp(UpdateOp r2) {
        if (this.mDisableRecycler == true) goto L6;
        r2.payload = null;
        this.mUpdateOpPool.release(r2);
        return;
    }

    public void recycleUpdateOpsAndClearList(List<UpdateOp> r4) {
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        recycleUpdateOp(r4.get(r1));
        r1 = r1 + 1;
        goto L3
    L5:
        r4.clear();
    }

    public void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }
}
