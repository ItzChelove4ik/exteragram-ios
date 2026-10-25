package org.telegram.ui.iv;

/* loaded from: classes7.dex */
public final class RichContainer {
    private static long ID_GEN = 1;
    public boolean checklist;
    public long id;
    public boolean itemChecked;
    public long itemId;
    public int itemNum;
    public boolean open;
    public boolean ordered;
    public final int type;

    static {
    }

    public RichContainer(int r1, long r2) {
        this.type = r1;
        this.id = r2;
    }

    public static RichContainer details(long r2, boolean r4) {
        RichContainer r0 = new RichContainer(2, r2);
        r0.open = r4;
        return r0;
    }

    public static RichContainer list(long r2, long r4, boolean r6, boolean r7, boolean r8) {
        RichContainer r0 = new RichContainer(0, r2);
        r0.itemId = r4;
        r0.ordered = r6;
        r0.checklist = r7;
        r0.itemChecked = r8;
        return r0;
    }

    public static long newId() {
        long r0 = ID_GEN;
        ID_GEN = 1 + r0;
        return r0;
    }

    public static RichContainer quote(long r2) {
        return new RichContainer(1, r2);
    }
}
