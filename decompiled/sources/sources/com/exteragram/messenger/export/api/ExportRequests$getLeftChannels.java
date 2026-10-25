package com.exteragram.messenger.export.api;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ExportRequests$getLeftChannels extends TLObject {
    public static int constructor = -2092831552;
    public int offset;

    static {
    }

    public ExportRequests$getLeftChannels() {
    }

    @Override // org.telegram.tgnet.TLObject
    public /* bridge */ /* synthetic */ TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
        return deserializeResponse(r1, r2, r3);
    }

    @Override // org.telegram.tgnet.TLObject
    public TLRPC.messages_Chats deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
        return TLRPC.messages_Chats.TLdeserialize(r1, r2, r3);
    }

    @Override // org.telegram.tgnet.TLObject
    public void serializeToStream(OutputSerializedData r2) {
        r2.writeInt32(constructor);
        r2.writeInt32(this.offset);
    }
}
