package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.FixedSizeList;
import com.android.dx.util.Hex;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class CatchHandlerList extends FixedSizeList implements Comparable<CatchHandlerList> {
    public static final CatchHandlerList EMPTY = null;

    /* loaded from: classes4.dex */
    public static class Entry implements Comparable<Entry> {
        private final CstType exceptionType;
        private final int handler;

        public Entry(CstType r2, int r3) {
            if (r3 < 0) goto L9;
            if (r2 == null) goto L7;
            this.handler = r3;
            this.exceptionType = r2;
            return;
        L7:
            f$$ExternalSyntheticBUOutline2.m("exceptionType == null");
            throw null;
        L9:
            f$$ExternalSyntheticBUOutline1.m("handler < 0");
            throw null;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(Entry r3) {
            int r0 = this.handler;
            int r1 = r3.handler;
            if (r0 >= r1) goto L6;
            return -1;
        L6:
            if (r0 <= r1) goto L10;
            return 1;
        L10:
            return this.exceptionType.compareTo2(r3.exceptionType);
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

        public CstType getExceptionType() {
            return this.exceptionType;
        }

        public int getHandler() {
            return this.handler;
        }

        public int hashCode() {
            return (this.handler * 31) + this.exceptionType.hashCode();
        }
    }

    static {
        EMPTY = new CatchHandlerList(0);
    }

    public CatchHandlerList(int r1) {
        super(r1);
    }

    public boolean catchesAll() {
        int r0 = size();
        if (r0 != 0) goto L7;
        return false;
    L7:
        return get(r0 - 1).getExceptionType().equals(CstType.OBJECT);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(CatchHandlerList r8) {
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
    public /* bridge */ /* synthetic */ int compareTo(CatchHandlerList r1) {
        return compareTo2(r1);
    }

    public Entry get(int r1) {
        return (Entry) get0(r1);
    }

    public void set(int r1, Entry r2) {
        set0(r1, r2);
    }

    public void set(int r2, CstType r3, int r4) {
        set0(r2, new Entry(r3, r4));
    }

    @Override // com.android.dx.util.FixedSizeList, com.android.dx.util.ToHuman
    public String toHuman() {
        return toHuman(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public String toHuman(String r5, String r6) {
        StringBuilder r0 = new StringBuilder(100);
        int r1 = size();
        r0.append(r5);
        r0.append(r6);
        r0.append("catch ");
        int r62 = 0;
    L3:
        if (r62 >= r1) goto L15;
        Entry r2 = get(r62);
        if (r62 == 0) goto L8;
        r0.append(",\n");
        r0.append(r5);
        r0.append("  ");
    L8:
        if (r62 == (r1 - 1)) goto L10;
    L12:
        r0.append(r2.getExceptionType().toHuman());
    L13:
        r0.append(" -> ");
        r0.append(Hex.u2or4(r2.getHandler()));
        r62 = r62 + 1;
        goto L3
    L10:
        if (catchesAll() == false) goto L12;
        r0.append("<any>");
        goto L13
    L15:
        return r0.toString();
    }
}
