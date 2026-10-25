package com.exteragram.messenger.export.api;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ExportRequests$FinishTakeoutSession extends TLObject {
    public static int constructor = 489050862;
    public int flags;
    public boolean success;

    static {
    }

    public ExportRequests$FinishTakeoutSession() {
    }

    @Override // org.telegram.tgnet.TLObject
    public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.Bool.TLdeserialize(r1, r2, r3);
    }

    @Override // org.telegram.tgnet.TLObject
    public void serializeToStream(OutputSerializedData r3) {
        r3.writeInt32(constructor);
        boolean r0 = this.success;
        int r1 = this.flags;
        if (r0 == false) goto L5;
        int r02 = r1 | 1;
    L6:
        this.flags = r02;
        r3.writeInt32(r02);
        return;
    L5:
        r02 = r1 & (-2);
        goto L6
    }
}
