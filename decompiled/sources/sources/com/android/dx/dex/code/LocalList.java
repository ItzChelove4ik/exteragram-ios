package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecSet;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.util.FixedSizeList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class LocalList extends FixedSizeList {
    private static final boolean DEBUG = false;
    public static final LocalList EMPTY = null;

    /* loaded from: classes4.dex */
    public enum Disposition extends Enum<Disposition> {
        private static final /* synthetic */ Disposition[] $VALUES = null;
        public static final Disposition END_CLOBBERED_BY_NEXT = null;
        public static final Disposition END_CLOBBERED_BY_PREV = null;
        public static final Disposition END_MOVED = null;
        public static final Disposition END_REPLACED = null;
        public static final Disposition END_SIMPLY = null;
        public static final Disposition START = null;

        static {
            Disposition r0 = new Disposition("START", 0);
            START = r0;
            Disposition r1 = new Disposition("END_SIMPLY", 1);
            END_SIMPLY = r1;
            Disposition r2 = new Disposition("END_REPLACED", 2);
            END_REPLACED = r2;
            Disposition r3 = new Disposition("END_MOVED", 3);
            END_MOVED = r3;
            Disposition r4 = new Disposition("END_CLOBBERED_BY_PREV", 4);
            END_CLOBBERED_BY_PREV = r4;
            Disposition r5 = new Disposition("END_CLOBBERED_BY_NEXT", 5);
            END_CLOBBERED_BY_NEXT = r5;
            $VALUES = new Disposition[]{r0, r1, r2, r3, r4, r5};
        }

        Disposition(String r1, int r2) {
        }

        public static Disposition valueOf(String r1) {
            return (Disposition) Enum.valueOf(Disposition.class, r1);
        }

        public static Disposition[] values() {
            return (Disposition[]) $VALUES.clone();
        }
    }

    /* loaded from: classes4.dex */
    public static class Entry implements Comparable<Entry> {
        private final int address;
        private final Disposition disposition;
        private final RegisterSpec spec;
        private final CstType type;

        public Entry(int r3, Disposition r4, RegisterSpec r5) {
            if (r3 < 0) goto L15;
            if (r4 != null) goto L17;
            f$$ExternalSyntheticBUOutline2.m("disposition == null");
            throw null;
        L17:
        L11:
            f$$ExternalSyntheticBUOutline2.m("spec == null");
            throw null;
        L6:
            if (r5.getLocalItem() == null) goto L10;
            this.address = r3;
            this.disposition = r4;
            this.spec = r5;
            this.type = CstType.intern(r5.getType());
            return;
        L10:
            throw new NullPointerException("spec.getLocalItem() == null");     // Catch: NullPointerException -> L11
        L15:
            f$$ExternalSyntheticBUOutline1.m("address < 0");
            throw null;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(Entry r5) {
            int r0 = this.address;
            int r1 = r5.address;
            if (r0 >= r1) goto L6;
            return -1;
        L6:
            if (r0 <= r1) goto L8;
            return 1;
        L8:
            boolean r02 = isStart();
            if (r02 == r5.isStart()) goto L14;
            if (r02 == false) goto L12;
            return 1;
        L12:
            return -1;
        L14:
            return this.spec.compareTo2(r5.spec);
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Entry r1) {
            return compareTo2(r1);
        }

        public boolean equals(Object r3) {
            if ((r3 instanceof Entry) == true) goto L6;
            return false;
        L6:
            if (compareTo2((Entry) r3) != 0) goto L9;
            return true;
        L9:
            return false;
        }

        public int getAddress() {
            return this.address;
        }

        public Disposition getDisposition() {
            return this.disposition;
        }

        public CstString getName() {
            return this.spec.getLocalItem().getName();
        }

        public int getRegister() {
            return this.spec.getReg();
        }

        public RegisterSpec getRegisterSpec() {
            return this.spec;
        }

        public CstString getSignature() {
            return this.spec.getLocalItem().getSignature();
        }

        public CstType getType() {
            return this.type;
        }

        public boolean isStart() {
            if (this.disposition != Disposition.START) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean matches(Entry r1) {
            return matches(r1.spec);
        }

        public boolean matches(RegisterSpec r1) {
            return this.spec.equalsUsingSimpleType(r1);
        }

        public String toString() {
            return Integer.toHexString(this.address) + " " + this.disposition + " " + this.spec;
        }

        public Entry withDisposition(Disposition r3) {
            if (r3 != this.disposition) goto L6;
            return this;
        L6:
            return new Entry(this.address, r3, this.spec);
        }
    }

    public static class MakeState {
        private int[] endIndices;
        private final int lastAddress;
        private int nullResultCount;
        private RegisterSpecSet regs;
        private final ArrayList<Entry> result;

        public MakeState(int r2) {
            this.result = new ArrayList(r2);
            this.nullResultCount = 0;
            this.regs = null;
            this.endIndices = null;
            this.lastAddress = 0;
        }

        private void aboutToProcess(int r6, int r7) {
            int[] r0 = this.endIndices;
            if (r0 != null) goto L5;
            boolean r3 = true;
        L6:
            int r4 = this.lastAddress;
            if (r6 != r4) goto L10;
            if (r3 == true) goto L10;
            return;
        L10:
            if (r6 < r4) goto L21;
            if (r3 == false) goto L13;
        L16:
            int r72 = r7 + 1;
            RegisterSpecSet r62 = new RegisterSpecSet(r72);
            int[] r73 = new int[r72];
            Arrays.fill(r73, -1);
            if (r3 == true) goto L19;
            r62.putAll(this.regs);
            int[] r02 = this.endIndices;
            System.arraycopy(r02, 0, r73, 0, r02.length);
        L19:
            this.regs = r62;
            this.endIndices = r73;
            return;
        L13:
            if (r7 >= r0.length) goto L16;
            return;
        L21:
            GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
            return;
        L5:
            r3 = false;
            goto L6
        }

        private void add(int r4, Disposition r5, RegisterSpec r6) {
            int r0 = r6.getReg();
            this.result.add(new Entry(r4, r5, r6));
            Disposition r42 = Disposition.START;
            RegisterSpecSet r1 = this.regs;
            if (r5 != r42) goto L6;
            r1.put(r6);
            this.endIndices[r0] = -1;
            return;
        L6:
            r1.remove(r6);
            this.endIndices[r0] = this.result.size() - 1;
        }

        private void addOrUpdateEnd(int r4, Disposition r5, RegisterSpec r6) {
            if (r5 == Disposition.START) goto L14;
            int r0 = r6.getReg();
            int r02 = this.endIndices[r0];
            if (r02 < 0) goto L12;
            Entry r1 = this.result.get(r02);
            if (r1.getAddress() != r4) goto L12;
            if (r1.getRegisterSpec().equals(r6) == false) goto L12;
            this.result.set(r02, r1.withDisposition(r5));
            this.regs.remove(r6);
            return;
        L12:
            endLocal(r4, r6, r5);
            return;
        L14:
            GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
        }

        private boolean checkForEmptyRange(int r6, RegisterSpec r7) {
            int r0 = this.result.size() - 1;
        L3:
            boolean r2 = false;
            if (r0 < 0) goto L15;
            Entry r3 = this.result.get(r0);
            if (r3 == null) goto L14;
            if (r3.getAddress() != r6) goto L10;
            if (r3.matches(r7) == false) goto L14;
        L10:
            return false;
        L14:
            r0 = r0 - 1;
        L15:
            this.regs.remove(r7);
            Entry r4 = null;
            this.result.set(r0, null);
            this.nullResultCount++;
            int r72 = r7.getReg();
        L16:
            r0 = r0 - 1;
            if (r0 < 0) goto L24;
            r4 = this.result.get(r0);
            if (r4 == null) goto L16;
            if (r4.getRegisterSpec().getReg() != r72) goto L16;
            r2 = true;
        L24:
            if (r2 == false) goto L28;
            this.endIndices[r72] = r0;
            if (r4.getAddress() != r6) goto L28;
            this.result.set(r0, r4.withDisposition(Disposition.END_SIMPLY));
        L28:
            return true;
        }

        private static RegisterSpec filterSpec(RegisterSpec r2) {
            if (r2 != null) goto L4;
            return r2;
        L4:
            if (r2.getType() == Type.KNOWN_NULL) goto L6;
            return r2;
        L6:
            return r2.withType(Type.OBJECT);
        }

        public void endLocal(int r2, RegisterSpec r3) {
            endLocal(r2, r3, Disposition.END_SIMPLY);
        }

        public void endLocal(int r3, RegisterSpec r4, Disposition r5) {
            int r0 = r4.getReg();
            RegisterSpec r42 = filterSpec(r4);
            aboutToProcess(r3, r0);
            if (this.endIndices[r0] < 0) goto L6;
            return;
        L6:
            if (checkForEmptyRange(r3, r42) == false) goto L8;
            return;
        L8:
            add(r3, r5, r42);
        }

        public LocalList finish() {
            int r1 = 0;
            aboutToProcess(Integer.MAX_VALUE, 0);
            int r0 = this.result.size();
            int r2 = r0 - this.nullResultCount;
            if (r2 == 0) goto L5;
            Entry[] r3 = new Entry[r2];
            ArrayList<Entry> r8 = this.result;
            if (r0 != r2) goto L9;
            r8.toArray(r3);
        L14:
            Arrays.sort(r3);
            LocalList r82 = new LocalList(r2);
        L15:
            if (r1 >= r2) goto L17;
            r82.set(r1, r3[r1]);
            r1 = r1 + 1;
            goto L15
        L17:
            r82.setImmutable();
            return r82;
        L9:
            int r02 = r8.size();
            int r4 = 0;
            int r5 = 0;
        L10:
            if (r5 >= r02) goto L14;
            Entry r6 = r8.get(r5);
            r5 = r5 + 1;
            Entry r62 = r6;
            if (r62 == null) goto L10;
            r3[r4] = r62;
            r4 = r4 + 1;
            goto L10
        L5:
            return LocalList.EMPTY;
        }

        public void snapshot(int r6, RegisterSpecSet r7) {
            int r0 = r7.getMaxSize();
            aboutToProcess(r6, r0 - 1);
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L14;
            RegisterSpec r2 = this.regs.get(r1);
            RegisterSpec r3 = filterSpec(r7.get(r1));
            if (r2 != null) goto L8;
            if (r3 == null) goto L13;
            startLocal(r6, r3);
        L13:
            r1 = r1 + 1;
            goto L3
        L8:
            if (r3 != null) goto L11;
            endLocal(r6, r2);
            goto L13
        L11:
            if (r3.equalsUsingSimpleType(r2) == true) goto L13;
            endLocal(r6, r2);
            startLocal(r6, r3);
            goto L13
        }

        public void startLocal(int r5, RegisterSpec r6) {
            int r0 = r6.getReg();
            RegisterSpec r62 = filterSpec(r6);
            aboutToProcess(r5, r0);
            RegisterSpec r1 = this.regs.get(r0);
            if (r62.equalsUsingSimpleType(r1) == false) goto L5;
            return;
        L5:
            RegisterSpec r2 = this.regs.findMatchingLocal(r62);
            if (r2 == null) goto L8;
            addOrUpdateEnd(r5, Disposition.END_MOVED, r2);
        L8:
            int r22 = this.endIndices[r0];
            if (r1 == null) goto L11;
            add(r5, Disposition.END_REPLACED, r1);
        L19:
            if (r0 <= 0) goto L26;
            RegisterSpec r12 = this.regs.get(r0 - 1);
            if (r12 == null) goto L26;
            if (r12.isCategory2() == false) goto L26;
            addOrUpdateEnd(r5, Disposition.END_CLOBBERED_BY_NEXT, r12);
        L26:
            if (r62.isCategory2() == false) goto L30;
            RegisterSpec r02 = this.regs.get(r0 + 1);
            if (r02 == null) goto L30;
            addOrUpdateEnd(r5, Disposition.END_CLOBBERED_BY_PREV, r02);
        L30:
            add(r5, Disposition.START, r62);
            return;
        L11:
            if (r22 < 0) goto L19;
            Entry r13 = this.result.get(r22);
            if (r13.getAddress() != r5) goto L19;
            if (r13.matches(r62) == false) goto L18;
            this.result.set(r22, null);
            this.nullResultCount++;
            this.regs.put(r62);
            this.endIndices[r0] = -1;
            return;
        L18:
            this.result.set(r22, r13.withDisposition(Disposition.END_REPLACED));
            goto L19
        }
    }

    static {
        EMPTY = new LocalList(0);
    }

    public LocalList(int r1) {
        super(r1);
    }

    private static void debugVerify(LocalList r5) {
        debugVerify0(r5);     // Catch: RuntimeException -> L4
        return;
    L4:
        e = move-exception;
        int r1 = r5.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L8;
        System.err.println(r5.get(r2));
        r2 = r2 + 1;
        goto L6
    L8:
        throw e;
    }

    private static void debugVerify0(LocalList r12) {
        int r0 = r12.size();
        Entry[] r1 = new Entry[65536];
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L55;
        Entry r7 = r12.get(r3);
        int r4 = r7.getRegister();
        if (r7.isStart() == false) goto L15;
        Entry r9 = r1[r4];
        if (r9 != null) goto L9;
    L13:
        r1[r4] = r7;
    L40:
        r3 = r3 + 1;
        goto L3
    L9:
        if (r7.matches(r9) == false) goto L13;
        LocalList$$ExternalSyntheticBUOutline0.m("redundant start at ", Integer.toHexString(r7.getAddress()), ": got ", r7, "; had ", r9);
        return;
    L15:
        if (r1[r4] == null) goto L41;
        int r5 = r7.getAddress();
        int r8 = r3 + 1;
        boolean r92 = false;
    L17:
        if (r8 >= r0) goto L33;
        Entry r10 = r12.get(r8);
        if (r10.getAddress() != r5) goto L33;
        if (r10.getRegisterSpec().getReg() != r4) goto L32;
        if (r10.isStart() == false) goto L30;
        if (r7.getDisposition() != Disposition.END_REPLACED) goto L28;
        r92 = true;
        goto L32
    L28:
        Instance$$ExternalSyntheticBUOutline0.m("improperly marked end at ", Integer.toHexString(r5));
        return;
    L30:
        Instance$$ExternalSyntheticBUOutline0.m("redundant end at ", Integer.toHexString(r5));
        return;
    L32:
        r8 = r8 + 1;
    L33:
        if (r92 == false) goto L35;
    L39:
        r1[r4] = null;
        goto L40
    L35:
        if (r7.getDisposition() != Disposition.END_REPLACED) goto L39;
        Instance$$ExternalSyntheticBUOutline0.m("improper end replacement claim at ", Integer.toHexString(r5));
        return;
    L41:
        Instance$$ExternalSyntheticBUOutline0.m("redundant end at ", Integer.toHexString(r7.getAddress()));
        return;
    }

    public static LocalList make(DalvInsnList r5) {
        int r0 = r5.size();
        MakeState r1 = new MakeState(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        DalvInsn r3 = r5.get(r2);
        if ((r3 instanceof LocalSnapshot) == false) goto L8;
        RegisterSpecSet r4 = ((LocalSnapshot) r3).getLocals();
        r1.snapshot(r3.getAddress(), r4);
    L10:
        r2 = r2 + 1;
        goto L3
    L8:
        if ((r3 instanceof LocalStart) == false) goto L10;
        RegisterSpec r42 = ((LocalStart) r3).getLocal();
        r1.startLocal(r3.getAddress(), r42);
        goto L10
    L12:
        return r1.finish();
    }

    public void debugPrint(PrintStream r4, String r5) {
        int r0 = size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4.print(r5);
        r4.println(get(r1));
        r1 = r1 + 1;
        goto L3
    }

    public Entry get(int r1) {
        return (Entry) get0(r1);
    }

    public void set(int r1, Entry r2) {
        set0(r1, r2);
    }
}
