package com.google.protobuf;

/* loaded from: classes5.dex */
final class RawMessageInfo implements MessageInfo {
    private final MessageLite defaultInstance;
    private final int flags;
    private final String info;
    private final Object[] objects;

    public RawMessageInfo(MessageLite r4, String r5, Object[] r6) {
        this.defaultInstance = r4;
        this.info = r5;
        this.objects = r6;
        char r42 = r5.charAt(0);
        if (r42 >= 55296) goto L6;
        this.flags = r42;
        return;
    L6:
        int r43 = r42 & 8191;
        int r0 = 13;
        int r1 = 1;
    L7:
        int r2 = r1 + 1;
        char r12 = r5.charAt(r1);
        if (r12 < 55296) goto L10;
        r43 = r43 | ((r12 & 8191) << r0);
        r0 = r0 + 13;
        r1 = r2;
        goto L7
    L10:
        this.flags = r43 | (r12 << r0);
    }

    @Override // com.google.protobuf.MessageInfo
    public MessageLite getDefaultInstance() {
        return this.defaultInstance;
    }

    public Object[] getObjects() {
        return this.objects;
    }

    public String getStringInfo() {
        return this.info;
    }

    @Override // com.google.protobuf.MessageInfo
    public ProtoSyntax getSyntax() {
        if ((this.flags & 1) != 1) goto L7;
        return ProtoSyntax.PROTO2;
    L7:
        return ProtoSyntax.PROTO3;
    }

    @Override // com.google.protobuf.MessageInfo
    public boolean isMessageSetWireFormat() {
        if ((this.flags & 2) != 2) goto L6;
        return true;
    L6:
        return false;
    }
}
