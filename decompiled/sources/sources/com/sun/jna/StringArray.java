package com.sun.jna;

import com.sun.jna.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class StringArray extends Memory implements Function.PostCallRead {
    private String encoding;
    private List<NativeString> natives;
    private Object[] original;

    public StringArray(WString[] r2) {
        this(r2, "--WIDE-STRING--");
    }

    private StringArray(Object[] r6, String r7) {
        super((r6.length + 1) * Native.POINTER_SIZE);
        this.natives = new ArrayList();
        this.original = r6;
        this.encoding = r7;
        int r0 = 0;
    L3:
        Pointer r2 = null;
        if (r0 >= r6.length) goto L9;
        Object r1 = r6[r0];
        if (r1 == null) goto L8;
        NativeString r22 = new NativeString(r1.toString(), r7);
        this.natives.add(r22);
        r2 = r22.getPointer();
    L8:
        setPointer(Native.POINTER_SIZE * r0, r2);
        r0 = r0 + 1;
        goto L3
    L9:
        setPointer(Native.POINTER_SIZE * r6.length, null);
    }

    public StringArray(String[] r2) {
        this(r2, false);
    }

    public StringArray(String[] r1, String r2) {
        this(r1, r2);
    }

    public StringArray(String[] r1, boolean r2) {
        if (r2 == false) goto L4;
        String r22 = "--WIDE-STRING--";
    L5:
        this(r1, r22);
        return;
    L4:
        r22 = Native.getDefaultStringEncoding();
        goto L5
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.sun.jna.WString] */
    @Override // com.sun.jna.Function.PostCallRead
    public void read() {
        boolean r0 = this.original instanceof WString[];
        boolean r1 = "--WIDE-STRING--".equals(this.encoding);
        int r2 = 0;
    L4:
        if (r2 >= this.original.length) goto L15;
        Pointer r3 = getPointer(Native.POINTER_SIZE * r2);
        if (r3 != null) goto L8;
        String r32 = null;
    L14:
        this.original[r2] = r32;
        r2 = r2 + 1;
        goto L4
    L8:
        if (r1 == false) goto L10;
        r32 = r3.getWideString(0);
    L11:
        if (r0 == false) goto L14;
        r32 = new WString(r32);
        goto L14
    L10:
        r32 = r3.getString(0, this.encoding);
        goto L11
    }

    @Override // com.sun.jna.Memory, com.sun.jna.Pointer
    public String toString() {
        if ("--WIDE-STRING--".equals(this.encoding) == false) goto L5;
        String r0 = "const wchar_t*[]";
    L7:
        return r0 + Arrays.asList(this.original);
    L5:
        r0 = "const char*[]";
        goto L7
    }
}
