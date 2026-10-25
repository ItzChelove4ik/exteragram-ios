package org.mvel2.asm;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class Handle {
    private final String descriptor;
    private final boolean isInterface;
    private final String name;
    private final String owner;
    private final int tag;

    @Deprecated
    public Handle(int r8, String r9, String r10, String r11) {
        if (r8 != 9) goto L6;
        boolean r0 = true;
    L7:
        this(r8, r9, r10, r11, r0);
        return;
    L6:
        r0 = false;
        goto L7
    }

    public Handle(int r1, String r2, String r3, String r4, boolean r5) {
        this.tag = r1;
        this.owner = r2;
        this.name = r3;
        this.descriptor = r4;
        this.isInterface = r5;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof Handle) == true) goto L8;
        return false;
    L8:
        Handle r52 = (Handle) r5;
        if (this.tag == r52.tag) goto L11;
    L19:
        return false;
    L11:
        if (this.isInterface != r52.isInterface) goto L19;
        if (this.owner.equals(r52.owner) == false) goto L19;
        if (this.name.equals(r52.name) == false) goto L19;
        if (this.descriptor.equals(r52.descriptor) == false) goto L19;
        return true;
    }

    public String getDesc() {
        return this.descriptor;
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public int getTag() {
        return this.tag;
    }

    public int hashCode() {
        int r0 = this.tag;
        if (this.isInterface == false) goto L5;
        int r1 = 64;
    L7:
        return (r0 + r1) + ((this.owner.hashCode() * this.name.hashCode()) * this.descriptor.hashCode());
    L5:
        r1 = 0;
        goto L7
    }

    public boolean isInterface() {
        return this.isInterface;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append(this.owner);
        r0.append('.');
        r0.append(this.name);
        r0.append(this.descriptor);
        r0.append(" (");
        r0.append(this.tag);
        if (this.isInterface == false) goto L5;
        String r2 = " itf";
    L6:
        r0.append(r2);
        r0.append(')');
        return r0.toString();
    L5:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }
}
