package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import com.google.protobuf.MessageSchema$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR = null;

    /* renamed from: androidx.recyclerview.widget.DiffUtil$1, reason: invalid class name */
    public class AnonymousClass1 implements Comparator<Snake> {
        public AnonymousClass1() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Snake r2, Snake r3) {
            int r1 = r2.x - r3.x;
            if (r1 == 0) goto L5;
            return r1;
        L5:
            return r2.y - r3.y;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Snake r1, Snake r2) {
            return compare2(r1, r2);
        }
    }

    public static abstract class Callback {
        public Callback() {
        }

        public abstract boolean areContentsTheSame(int r1, int r2);

        public abstract boolean areItemsTheSame(int r1, int r2);

        public Object getChangePayload(int r1, int r2) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    public static class DiffResult {
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;

        public DiffResult(Callback r1, List<Snake> r2, int[] r3, int[] r4, boolean r5) {
            this.mSnakes = r2;
            this.mOldItemStatuses = r3;
            this.mNewItemStatuses = r4;
            Arrays.fill(r3, 0);
            Arrays.fill(r4, 0);
            this.mCallback = r1;
            this.mOldListSize = r1.getOldListSize();
            this.mNewListSize = r1.getNewListSize();
            this.mDetectMoves = r5;
            addRootSnake();
            findMatchingItems();
        }

        private void addRootSnake() {
            if (this.mSnakes.isEmpty() == false) goto L5;
            Snake r0 = null;
        L6:
            if (r0 != null) goto L8;
        L13:
            Snake r02 = new Snake();
            r02.x = 0;
            r02.y = 0;
            r02.removal = false;
            r02.size = 0;
            r02.reverse = false;
            this.mSnakes.add(0, r02);
            return;
        L8:
            if (r0.x != 0) goto L13;
            if (r0.y != 0) goto L13;
            return;
        L5:
            r0 = this.mSnakes.get(0);
            goto L6
        }

        private void dispatchAdditions(List<PostponedUpdate> r7, ListUpdateCallback r8, int r9, int r10, int r11) {
            if (this.mDetectMoves == true) goto L6;
            r8.onInserted(r9, r10);
            return;
        L6:
            int r102 = r10 - 1;
        L7:
            if (r102 < 0) goto L27;
            int r2 = r11 + r102;
            int r1 = this.mNewItemStatuses[r2];
            int r3 = r1 & 31;
            if (r3 != 0) goto L11;
            r8.onInserted(r9, 1);
            Iterator<PostponedUpdate> r12 = r7.iterator();
        L24:
            if (r12.hasNext() == false) goto L26;
            r12.next().currentPos++;
        L26:
            r102 = r102 - 1;
            goto L7
        L11:
            if (r3 != 4) goto L13;
        L19:
            int r13 = r1 >> 5;
            r8.onMoved(removePostponedUpdate(r7, r13, true).currentPos, r9);
            if (r3 != 4) goto L26;
            r8.onChanged(r9, 1, this.mCallback.getChangePayload(r13, r2));
            goto L26
        L13:
            if (r3 == 8) goto L19;
            if (r3 != 16) goto L17;
            r7.add(new PostponedUpdate(r2, r9, false));
            goto L26
        L17:
            MessageSchema$$ExternalSyntheticBUOutline0.m("unknown flag for pos ", r2, " ", Long.toBinaryString(r3));
            return;
        }

        private void dispatchRemovals(List<PostponedUpdate> r9, ListUpdateCallback r10, int r11, int r12, int r13) {
            if (this.mDetectMoves == true) goto L6;
            r10.onRemoved(r11, r12);
            return;
        L6:
            int r122 = r12 - 1;
        L7:
            if (r122 < 0) goto L27;
            int r2 = r13 + r122;
            int r1 = this.mOldItemStatuses[r2];
            int r3 = r1 & 31;
            if (r3 != 0) goto L11;
            r10.onRemoved(r11 + r122, 1);
            Iterator<PostponedUpdate> r14 = r9.iterator();
        L24:
            if (r14.hasNext() == false) goto L26;
            r14.next().currentPos--;
        L26:
            r122 = r122 - 1;
            goto L7
        L11:
            if (r3 != 4) goto L13;
        L19:
            int r15 = r1 >> 5;
            PostponedUpdate r5 = removePostponedUpdate(r9, r15, false);
            r10.onMoved(r11 + r122, r5.currentPos - 1);
            if (r3 != 4) goto L26;
            r10.onChanged(r5.currentPos - 1, 1, this.mCallback.getChangePayload(r2, r15));
            goto L26
        L13:
            if (r3 == 8) goto L19;
            if (r3 != 16) goto L17;
            r9.add(new PostponedUpdate(r2, r11 + r122, true));
            goto L26
        L17:
            MessageSchema$$ExternalSyntheticBUOutline0.m("unknown flag for pos ", r2, " ", Long.toBinaryString(r3));
            return;
        }

        private void findAddition(int r3, int r4, int r5) {
            if (this.mOldItemStatuses[r3 - 1] == 0) goto L5;
            return;
        L5:
            findMatchingItem(r3, r4, r5, false);
        }

        private boolean findMatchingItem(int r9, int r10, int r11, boolean r12) {
            if (r12 == false) goto L4;
            r10 = r10 - 1;
            int r1 = r9;
            int r0 = r10;
        L5:
            if (r11 < 0) goto L29;
            Snake r2 = this.mSnakes.get(r11);
            int r3 = r2.x;
            int r4 = r2.size;
            int r32 = r3 + r4;
            int r5 = r2.y + r4;
            int r42 = 4;
            if (r12 == false) goto L18;
            int r13 = r1 - 1;
        L9:
            if (r13 < r32) goto L28;
            if (this.mCallback.areItemsTheSame(r13, r0) == true) goto L13;
            r13 = r13 - 1;
            goto L9
        L13:
            if (this.mCallback.areContentsTheSame(r13, r0) == false) goto L15;
            r42 = 8;
        L15:
            this.mNewItemStatuses[r0] = (r13 << 5) | 16;
            this.mOldItemStatuses[r13] = (r0 << 5) | r42;
            return true;
        L28:
            r1 = r2.x;
            r10 = r2.y;
            r11 = r11 - 1;
            goto L5
        L18:
            int r102 = r10 - 1;
        L19:
            if (r102 < r5) goto L28;
            if (this.mCallback.areItemsTheSame(r0, r102) == true) goto L23;
            r102 = r102 - 1;
            goto L19
        L23:
            if (this.mCallback.areContentsTheSame(r0, r102) == false) goto L25;
            r42 = 8;
        L25:
            int r92 = r9 - 1;
            this.mOldItemStatuses[r92] = (r102 << 5) | 16;
            this.mNewItemStatuses[r102] = (r92 << 5) | r42;
            return true;
        L29:
            return false;
        L4:
            r0 = r9 - 1;
            r1 = r0;
            goto L5
        }

        private void findMatchingItems() {
            int r0 = this.mOldListSize;
            int r1 = this.mNewListSize;
            int r2 = this.mSnakes.size() - 1;
        L3:
            if (r2 < 0) goto L19;
            Snake r4 = this.mSnakes.get(r2);
            int r5 = r4.x;
            int r6 = r4.size;
            int r52 = r5 + r6;
            int r7 = r4.y + r6;
            if (this.mDetectMoves == false) goto L10;
        L6:
            if (r0 <= r52) goto L8;
            findAddition(r0, r1, r2);
            r0 = r0 - 1;
        L8:
            if (r1 <= r7) goto L10;
            findRemoval(r0, r1, r2);
            r1 = r1 - 1;
        L10:
            int r02 = 0;
        L11:
            int r12 = r4.size;
            int r53 = r4.x;
            if (r02 >= r12) goto L18;
            int r54 = r53 + r02;
            int r13 = r4.y + r02;
            if (this.mCallback.areContentsTheSame(r54, r13) == false) goto L16;
            int r62 = 1;
        L17:
            this.mOldItemStatuses[r54] = (r13 << 5) | r62;
            this.mNewItemStatuses[r13] = (r54 << 5) | r62;
            r02 = r02 + 1;
            goto L11
        L16:
            r62 = 2;
            goto L17
        L18:
            r1 = r4.y;
            r2 = r2 - 1;
            r0 = r53;
            goto L3
        }

        private void findRemoval(int r3, int r4, int r5) {
            if (this.mNewItemStatuses[r4 - 1] == 0) goto L5;
            return;
        L5:
            findMatchingItem(r3, r4, r5, true);
        }

        private static PostponedUpdate removePostponedUpdate(List<PostponedUpdate> r5, int r6, boolean r7) {
            int r0 = r5.size() - 1;
        L3:
            if (r0 < 0) goto L18;
            PostponedUpdate r2 = r5.get(r0);
            if (r2.posInOwnerList != r6) goto L17;
            if (r2.removal != r7) goto L17;
            r5.remove(r0);
        L10:
            if (r0 >= r5.size()) goto L16;
            PostponedUpdate r62 = r5.get(r0);
            int r3 = r62.currentPos;
            if (r7 == false) goto L14;
            int r4 = 1;
        L15:
            r62.currentPos = r3 + r4;
            r0 = r0 + 1;
            goto L10
        L14:
            r4 = -1;
            goto L15
        L16:
            return r2;
        L17:
            r0 = r0 - 1;
            goto L3
        L18:
            return null;
        }

        public void dispatchUpdatesTo(ListUpdateCallback r13) {
            if ((r13 instanceof BatchingListUpdateCallback) == false) goto L5;
            BatchingListUpdateCallback r3 = (BatchingListUpdateCallback) r13;
        L6:
            ArrayList r2 = new ArrayList();
            int r132 = this.mOldListSize;
            int r0 = this.mNewListSize;
            int r8 = this.mSnakes.size() - 1;
        L7:
            if (r8 < 0) goto L21;
            Snake r9 = this.mSnakes.get(r8);
            int r10 = r9.size;
            int r4 = r9.x + r10;
            int r11 = r9.y + r10;
            if (r4 >= r132) goto L11;
            DiffResult r1 = this;
            r1.dispatchRemovals(r2, r3, r4, r132 - r4, r4);
        L12:
            if (r11 >= r0) goto L14;
            r1.dispatchAdditions(r2, r3, r4, r0 - r11, r11);
        L14:
            int r102 = r10 - 1;
        L15:
            if (r102 < 0) goto L20;
            int[] r12 = r1.mOldItemStatuses;
            int r133 = r9.x;
            if ((r12[r133 + r102] & 31) != 2) goto L19;
            r3.onChanged(r133 + r102, 1, r1.mCallback.getChangePayload(r133 + r102, r9.y + r102));
        L19:
            r102 = r102 - 1;
            goto L15
        L20:
            r132 = r9.x;
            r0 = r9.y;
            r8 = r8 - 1;
            DiffResult this = r1;
            goto L7
        L11:
            r1 = this;
            goto L12
        L21:
            r3.dispatchLastEvent();
            return;
        L5:
            r3 = new BatchingListUpdateCallback(r13);
            goto L6
        }

        public void dispatchUpdatesTo(RecyclerView.Adapter r2) {
            dispatchUpdatesTo(new AdapterListUpdateCallback(r2));
        }
    }

    public static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int r1, int r2, boolean r3) {
            this.posInOwnerList = r1;
            this.currentPos = r2;
            this.removal = r3;
        }
    }

    public static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range() {
        }

        public Range(int r1, int r2, int r3, int r4) {
            this.oldListStart = r1;
            this.oldListEnd = r2;
            this.newListStart = r3;
            this.newListEnd = r4;
        }
    }

    public static class Snake {
        boolean removal;
        boolean reverse;
        int size;
        int x;
        int y;

        public Snake() {
        }
    }

    static {
        SNAKE_COMPARATOR = new AnonymousClass1();
    }

    public static DiffResult calculateDiff(Callback r1) {
        return calculateDiff(r1, true);
    }

    public static DiffResult calculateDiff(Callback r13, boolean r14) {
        int r0 = r13.getOldListSize();
        int r1 = r13.getNewListSize();
        ArrayList r4 = new ArrayList();
        ArrayList r2 = new ArrayList();
        r2.add(new Range(0, r0, 0, r1));
        int r12 = (r0 + r1) + Math.abs(r0 - r1);
        int r02 = r12 * 2;
        int[] r5 = new int[r02];
        int[] r6 = new int[r02];
        ArrayList r03 = new ArrayList();
    L4:
        if (r2.isEmpty() == true) goto L31;
        Range r15 = (Range) r2.remove(r2.size() - 1);
        int[] r11 = r6;
        int[] r10 = r5;
        Callback r52 = r13;
        Snake r132 = diffPartial(r52, r15.oldListStart, r15.oldListEnd, r15.newListStart, r15.newListEnd, r10, r11, r12);
        r5 = r10;
        if (r132 != null) goto L8;
        r03.add(r15);
    L30:
        r13 = r52;
        r6 = r11;
        goto L4
    L8:
        if (r132.size <= 0) goto L10;
        r4.add(r132);
    L10:
        r132.x += r15.oldListStart;
        r132.y += r15.newListStart;
        if (r03.isEmpty() == false) goto L13;
        Range r62 = new Range();
    L14:
        r62.oldListStart = r15.oldListStart;
        r62.newListStart = r15.newListStart;
        if (r132.reverse == false) goto L17;
        r62.oldListEnd = r132.x;
        r62.newListEnd = r132.y;
    L21:
        r2.add(r62);
        if (r132.reverse == false) goto L27;
        boolean r63 = r132.removal;
        int r7 = r132.x;
        if (r63 == false) goto L26;
        int r64 = r132.size;
        r15.oldListStart = (r7 + r64) + 1;
        r15.newListStart = r132.y + r64;
    L28:
        r2.add(r15);
        goto L30
    L26:
        int r65 = r132.size;
        r15.oldListStart = r7 + r65;
        r15.newListStart = (r132.y + r65) + 1;
        goto L28
    L27:
        int r66 = r132.x;
        int r72 = r132.size;
        r15.oldListStart = r66 + r72;
        r15.newListStart = r132.y + r72;
        goto L28
    L17:
        boolean r73 = r132.removal;
        int r8 = r132.x;
        if (r73 == false) goto L20;
        r62.oldListEnd = r8 - 1;
        r62.newListEnd = r132.y;
        goto L21
    L20:
        r62.oldListEnd = r8;
        r62.newListEnd = r132.y - 1;
        goto L21
    L13:
        r62 = (Range) r03.remove(r03.size() - 1);
        goto L14
    L31:
        Callback r3 = r13;
        Collections.sort(r4, SNAKE_COMPARATOR);
        return new DiffResult(r3, r4, r5, r6, r14);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Snake diffPartial(Callback r17, int r18, int r19, int r20, int r21, int[] r22, int[] r23, int r24) {
        int[] r1 = r22;
        int r3 = r19 - r18;
        int r4 = r21 - r20;
        int r6 = 1;
        if (r3 < 1) goto L5;
        if (r4 < 1) goto L5;
        int r7 = r3 - r4;
        int r8 = ((r3 + r4) + 1) / 2;
        int r9 = (r24 - r8) - 1;
        int r10 = (r24 + r8) + 1;
        boolean r11 = false;
        Arrays.fill(r1, r9, r10, 0);
        Arrays.fill(r23, r9 + r7, r10 + r7, r3);
        if ((r7 % 2) == 0) goto L9;
        boolean r92 = true;
    L10:
        int r102 = 0;
    L11:
        if (r102 > r8) goto L66;
        int r12 = -r102;
        int r13 = r12;
    L13:
        if (r13 > r102) goto L39;
        if (r13 == r12) goto L20;
        if (r13 == r102) goto L19;
        int r14 = r24 + r13;
        if (r1[r14 - 1] < r1[r14 + r6]) goto L20;
    L19:
        int r142 = r1[(r24 + r13) - r6] + r6;
        boolean r15 = r6;
    L21:
        int r5 = r142 - r13;
    L22:
        if (r142 >= r3) goto L27;
        if (r5 >= r4) goto L27;
        int r212 = r6;
        if (r17.areItemsTheSame(r18 + r142, r20 + r5) == false) goto L28;
        r142 = r142 + 1;
        r5 = r5 + 1;
        r6 = r212;
    L28:
        int r52 = r24 + r13;
        r1[r52] = r142;
        if (r92 == false) goto L38;
        if (r13 < ((r7 - r102) + 1)) goto L38;
        if (r13 > ((r7 + r102) - 1)) goto L38;
        if (r142 < r23[r52]) goto L38;
        Snake r0 = new Snake();
        int r2 = r23[r52];
        r0.x = r2;
        r0.y = r2 - r13;
        r0.size = r1[r52] - r2;
        r0.removal = r15;
        r0.reverse = false;
        return r0;
    L38:
        r13 = r13 + 2;
        r6 = r212;
        r11 = false;
    L27:
        r212 = r6;
    L20:
        r142 = r1[(r24 + r13) + r6];
        r15 = r11;
        goto L21
    L39:
        int r213 = r6;
        boolean r53 = r11;
        int r62 = r12;
    L40:
        if (r62 > r102) goto L65;
        int r112 = r62 + r7;
        if (r112 != (r102 + r7)) goto L44;
    L49:
        int r132 = r23[(r24 + r112) - 1];
        boolean r143 = r53;
    L50:
        int r152 = r132 - r112;
    L51:
        if (r132 <= 0) goto L56;
        if (r152 <= 0) goto L56;
        if (r17.areItemsTheSame((r18 + r132) - 1, (r20 + r152) - 1) == false) goto L56;
        r132 = r132 - 1;
        r152 = r152 - 1;
    L56:
        int r16 = r24 + r112;
        r23[r16] = r132;
        if (r92 == true) goto L64;
        if (r112 < r12) goto L64;
        if (r112 > r102) goto L64;
        if (r22[r16] < r132) goto L64;
        Snake r02 = new Snake();
        int r25 = r23[r16];
        r02.x = r25;
        r02.y = r25 - r112;
        r02.size = r22[r16] - r25;
        r02.removal = r143;
        r02.reverse = r213;
        return r02;
    L64:
        r62 = r62 + 2;
        r213 = r213;
        r53 = false;
        goto L40
    L44:
        if (r112 == (r12 + r7)) goto L48;
        int r133 = r24 + r112;
        if (r23[r133 - 1] < r23[r133 + 1]) goto L49;
    L48:
        r132 = r23[(r24 + r112) + 1] - 1;
        r143 = r213;
        goto L50
    L65:
        r102 = r102 + 1;
        r6 = r213;
        r11 = false;
        r1 = r22;
        goto L11
    L66:
        Segment$$ExternalSyntheticBUOutline1.m("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
        return null;
    L9:
        r92 = false;
    L5:
        return null;
    }
}
