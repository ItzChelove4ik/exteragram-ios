package org.mvel2.asm;

/* loaded from: classes5.dex */
final class CurrentFrame extends Frame {
    public CurrentFrame(Label r1) {
        super(r1);
    }

    @Override // org.mvel2.asm.Frame
    public void execute(int r1, int r2, Symbol r3, SymbolTable r4) {
        super.execute(r1, r2, r3, r4);
        Frame r12 = new Frame(null);
        merge(r4, r12, 0);
        copyFrom(r12);
    }
}
