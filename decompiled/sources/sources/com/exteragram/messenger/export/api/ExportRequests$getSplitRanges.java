package com.exteragram.messenger.export.api;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.Vector;

/* loaded from: classes4.dex */
public class ExportRequests$getSplitRanges extends TLObject {
    public ExportRequests$getSplitRanges() {
    }

    @Override // org.telegram.tgnet.TLObject
    public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
        Vector r0 = new Vector(new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda0());
        r0.readParams(r1, r3);
        return r0;
    }

    @Override // org.telegram.tgnet.TLObject
    public void serializeToStream(OutputSerializedData r1) {
        r1.writeInt32(486505992);
    }
}
