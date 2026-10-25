package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.util.FixedSizeList;

/* loaded from: classes.dex */
public final class PositionList extends FixedSizeList {
    public static final PositionList EMPTY = null;
    public static final int IMPORTANT = 3;
    public static final int LINES = 2;
    public static final int NONE = 1;

    /* loaded from: classes4.dex */
    public static class Entry {
        private final int address;
        private final SourcePosition position;

        public Entry(int r2, SourcePosition r3) {
            if (r2 < 0) goto L9;
            if (r3 == null) goto L7;
            this.address = r2;
            this.position = r3;
            return;
        L7:
            f$$ExternalSyntheticBUOutline2.m("position == null");
            throw null;
        L9:
            f$$ExternalSyntheticBUOutline1.m("address < 0");
            throw null;
        }

        public int getAddress() {
            return this.address;
        }

        public SourcePosition getPosition() {
            return this.position;
        }
    }

    static {
        EMPTY = new PositionList(0);
    }

    public PositionList(int r1) {
        super(r1);
    }

    public static PositionList make(DalvInsnList r13, int r14) {
        if (r14 == 1) goto L31;
        if (r14 == 2) goto L10;
        if (r14 == 3) goto L10;
        f$$ExternalSyntheticBUOutline1.m("bogus howMuch");
        return null;
    L10:
        SourcePosition r1 = SourcePosition.NO_INFO;
        int r3 = r13.size();
        Entry[] r4 = new Entry[r3];
        int r5 = 0;
        SourcePosition r8 = r1;
        int r6 = 0;
        int r7 = 0;
        boolean r9 = false;
    L11:
        if (r6 >= r3) goto L25;
        DalvInsn r10 = r13.get(r6);
        if ((r10 instanceof CodeAddress) == false) goto L15;
        r9 = true;
    L24:
        r6 = r6 + 1;
        goto L11
    L15:
        SourcePosition r11 = r10.getPosition();
        if (r11.equals(r1) == true) goto L24;
        if (r11.sameLine(r8) == true) goto L24;
        if (r14 != 3) goto L23;
        if (r9 == false) goto L24;
    L23:
        r4[r7] = new Entry(r10.getAddress(), r11);
        r7 = r7 + 1;
        r9 = false;
        r8 = r11;
        goto L24
    L25:
        PositionList r132 = new PositionList(r7);
    L26:
        if (r5 >= r7) goto L28;
        r132.set(r5, r4[r5]);
        r5 = r5 + 1;
        goto L26
    L28:
        r132.setImmutable();
        return r132;
    L31:
        return EMPTY;
    }

    public Entry get(int r1) {
        return (Entry) get0(r1);
    }

    public void set(int r1, Entry r2) {
        set0(r1, r2);
    }
}
