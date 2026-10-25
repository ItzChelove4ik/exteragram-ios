package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.util.FixedSizeList;

/* loaded from: classes.dex */
public final class CatchTable extends FixedSizeList implements Comparable<CatchTable> {
    public static final CatchTable EMPTY = null;

    /* loaded from: classes4.dex */
    public static class Entry implements Comparable<Entry> {
        private final int end;
        private final CatchHandlerList handlers;
        private final int start;

        public Entry(int r3, int r4, CatchHandlerList r5) {
            if (r3 < 0) goto L13;
            if (r4 > r3) goto L6;
            f$$ExternalSyntheticBUOutline1.m("end <= start");
            throw null;
        L6:
            if (r5.isMutable() == true) goto L9;
            this.start = r3;
            this.end = r4;
            this.handlers = r5;
            return;
        L9:
            f$$ExternalSyntheticBUOutline1.m("handlers.isMutable()");
            throw null;
        L13:
            f$$ExternalSyntheticBUOutline1.m("start < 0");
            throw null;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(Entry r5) {
            int r0 = this.start;
            int r1 = r5.start;
            if (r0 >= r1) goto L6;
            return -1;
        L6:
            if (r0 <= r1) goto L8;
            return 1;
        L8:
            int r02 = this.end;
            int r12 = r5.end;
            if (r02 >= r12) goto L11;
            return -1;
        L11:
            if (r02 <= r12) goto L14;
            return 1;
        L14:
            return this.handlers.compareTo2(r5.handlers);
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Entry r1) {
            return compareTo2(r1);
        }

        public boolean equals(Object r3) {
            if ((r3 instanceof Entry) == true) goto L5;
        L8:
            return false;
        L5:
            if (compareTo2((Entry) r3) != 0) goto L8;
            return true;
        }

        public int getEnd() {
            return this.end;
        }

        public CatchHandlerList getHandlers() {
            return this.handlers;
        }

        public int getStart() {
            return this.start;
        }

        public int hashCode() {
            return (((this.start * 31) + this.end) * 31) + this.handlers.hashCode();
        }
    }

    static {
        EMPTY = new CatchTable(0);
    }

    public CatchTable(int r1) {
        super(r1);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(CatchTable r8) {
        if (this != r8) goto L5;
        return 0;
    L5:
        int r1 = size();
        int r2 = r8.size();
        int r3 = Math.min(r1, r2);
        int r4 = 0;
    L6:
        if (r4 >= r3) goto L11;
        int r5 = get(r4).compareTo2(r8.get(r4));
        if (r5 != 0) goto L9;
        r4 = r4 + 1;
        goto L6
    L9:
        return r5;
    L11:
        if (r1 >= r2) goto L14;
        return -1;
    L14:
        if (r1 <= r2) goto L17;
        return 1;
    L17:
        return 0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(CatchTable r1) {
        return compareTo2(r1);
    }

    public Entry get(int r1) {
        return (Entry) get0(r1);
    }

    public void set(int r1, Entry r2) {
        set0(r1, r2);
    }
}
