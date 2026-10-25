package com.android.dx.cf.attrib;

import com.android.dx.cf.code.BootstrapMethodsList;

/* loaded from: classes4.dex */
public class AttBootstrapMethods extends BaseAttribute {
    private static final int ATTRIBUTE_HEADER_BYTES = 8;
    public static final String ATTRIBUTE_NAME = "BootstrapMethods";
    private static final int BOOTSTRAP_ARGUMENT_BYTES = 2;
    private static final int BOOTSTRAP_METHOD_BYTES = 4;
    private final BootstrapMethodsList bootstrapMethods;
    private final int byteLength;

    public AttBootstrapMethods(BootstrapMethodsList r4) {
        super(ATTRIBUTE_NAME);
        this.bootstrapMethods = r4;
        int r0 = (r4.size() * 4) + 8;
        int r1 = 0;
    L4:
        if (r1 >= r4.size()) goto L6;
        r0 = r0 + (r4.get(r1).getBootstrapMethodArguments().size() * 2);
        r1 = r1 + 1;
        goto L4
    L6:
        this.byteLength = r0;
    }

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return this.byteLength;
    }

    public BootstrapMethodsList getBootstrapMethods() {
        return this.bootstrapMethods;
    }
}
