package com.exteragram.messenger.export.api;

import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ExportRequests$TL_inputTakeoutFileLocation extends TLRPC.InputFileLocation {
    public ExportRequests$TL_inputTakeoutFileLocation() {
    }

    @Override // org.telegram.tgnet.TLObject
    public void serializeToStream(OutputSerializedData r1) {
        r1.writeInt32(700340377);
    }
}
