package com.exteragram.messenger.export.api;

import org.mvel2.math.MathProcessor$$ExternalSyntheticBUOutline0;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ExportRequests$InvokeWithMessagesRange extends TLObject {
    public TLObject query;
    public TLRPC.TL_messageRange range;

    public ExportRequests$InvokeWithMessagesRange() {
    }

    @Override // org.telegram.tgnet.TLObject
    public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
        if (r2 != 364538944) goto L5;
        TLObject r0 = new TLRPC.TL_messages_dialogs();
    L13:
        r0.readParams(r1, r3);
        return r0;
    L5:
        if (r2 != 1143203525) goto L7;
        r0 = new TLRPC.TL_messages_getHistory();
        goto L13
    L7:
        if (r2 != 1910543603) goto L9;
        r0 = new TLRPC.TL_messages_dialogsSlice();
        goto L13
    L9:
        MathProcessor$$ExternalSyntheticBUOutline0.m("unknown constructor: ", r2);
        return null;
    }

    @Override // org.telegram.tgnet.TLObject
    public void serializeToStream(OutputSerializedData r2) {
        r2.writeInt32(911373810);
        this.range.serializeToStream(r2);
        this.query.serializeToStream(r2);
    }
}
