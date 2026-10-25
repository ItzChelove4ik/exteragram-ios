package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public abstract class Table {
    protected ByteBuffer bb;
    protected int bb_pos;
    Utf8 utf8;
    private int vtable_size;
    private int vtable_start;

    public Table() {
        this.utf8 = Utf8.getDefault();
    }

    public int __indirect(int r1) {
        return r1 + this.bb.getInt(r1);
    }

    public int __offset(int r2) {
        if (r2 < this.vtable_size) goto L5;
        return 0;
    L5:
        return this.bb.getShort(this.vtable_start + r2);
    }

    public void __reset(int r1, ByteBuffer r2) {
        this.bb = r2;
        if (r2 == null) goto L6;
        this.bb_pos = r1;
        int r12 = r1 - r2.getInt(r1);
        this.vtable_start = r12;
        this.vtable_size = this.bb.getShort(r12);
        return;
    L6:
        this.bb_pos = 0;
        this.vtable_start = 0;
        this.vtable_size = 0;
    }

    public int __vector(int r2) {
        int r22 = r2 + this.bb_pos;
        return (r22 + this.bb.getInt(r22)) + 4;
    }

    public int __vector_len(int r2) {
        int r22 = r2 + this.bb_pos;
        int r23 = r22 + this.bb.getInt(r22);
        return this.bb.getInt(r23);
    }
}
