package com.sun.jna;

/* loaded from: classes5.dex */
public abstract class PointerType implements NativeMapped {
    private Pointer pointer;

    public PointerType() {
        this.pointer = Pointer.NULL;
    }

    public PointerType(Pointer r1) {
        this.pointer = r1;
    }

    public boolean equals(Object r4) {
        if (r4 != this) goto L6;
        return true;
    L6:
        if ((r4 instanceof PointerType) == false) goto L14;
        Pointer r42 = ((PointerType) r4).getPointer();
        Pointer r3 = this.pointer;
        if (r3 != null) goto L13;
        if (r42 != null) goto L11;
        return true;
    L11:
        return false;
    L13:
        return r3.equals(r42);
    L14:
        return false;
    }

    @Override // com.sun.jna.NativeMapped
    public Object fromNative(Object r1, FromNativeContext r2) {
        if (r1 != null) goto L5;
        return null;
    L5:
        PointerType r0 = (PointerType) Klass.newInstance(getClass());
        r0.pointer = (Pointer) r1;
        return r0;
    }

    public Pointer getPointer() {
        return this.pointer;
    }

    public int hashCode() {
        Pointer r0 = this.pointer;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.hashCode();
    }

    @Override // com.sun.jna.NativeMapped
    public Class<?> nativeType() {
        return Pointer.class;
    }

    public void setPointer(Pointer r1) {
        this.pointer = r1;
    }

    @Override // com.sun.jna.NativeMapped
    public Object toNative() {
        return getPointer();
    }

    public String toString() {
        if (this.pointer != null) goto L7;
        return "NULL";
    L7:
        return this.pointer.toString() + " (" + super.toString() + ")";
    }
}
