package com.android.dx.io.instructions;

/* loaded from: classes4.dex */
public abstract class BaseCodeCursor implements CodeCursor {
    private final AddressMap baseAddressMap;
    private int cursor;

    public BaseCodeCursor() {
        this.baseAddressMap = new AddressMap();
        this.cursor = 0;
    }

    public final void advance(int r2) {
        this.cursor += r2;
    }

    @Override // com.android.dx.io.instructions.CodeCursor
    public final int baseAddressForCursor() {
        int r0 = this.baseAddressMap.get(this.cursor);
        if (r0 < 0) goto L6;
        return r0;
    L6:
        return this.cursor;
    }

    @Override // com.android.dx.io.instructions.CodeCursor
    public final int cursor() {
        return this.cursor;
    }

    @Override // com.android.dx.io.instructions.CodeCursor
    public final void setBaseAddress(int r1, int r2) {
        this.baseAddressMap.put(r1, r2);
    }
}
