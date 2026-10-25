package com.google.protobuf;

/* loaded from: classes5.dex */
public enum ProtoSyntax extends Enum<ProtoSyntax> {
    private static final /* synthetic */ ProtoSyntax[] $VALUES = null;
    public static final ProtoSyntax PROTO2 = null;
    public static final ProtoSyntax PROTO3 = null;

    static {
        ProtoSyntax r0 = new ProtoSyntax("PROTO2", 0);
        PROTO2 = r0;
        ProtoSyntax r1 = new ProtoSyntax("PROTO3", 1);
        PROTO3 = r1;
        $VALUES = new ProtoSyntax[]{r0, r1};
    }

    ProtoSyntax(String r1, int r2) {
    }

    public static ProtoSyntax valueOf(String r1) {
        return (ProtoSyntax) Enum.valueOf(ProtoSyntax.class, r1);
    }

    public static ProtoSyntax[] values() {
        return (ProtoSyntax[]) $VALUES.clone();
    }
}
