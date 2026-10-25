package com.exteragram.messenger.export.api;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public class ExportRequests$Takeout extends TLObject {
    public long id;

    static {
    }

    public ExportRequests$Takeout() {
    }

    public static ExportRequests$Takeout TLdeserialize(InputSerializedData r0, int r1, boolean r2) {
        ExportRequests$Takeout r12 = new ExportRequests$Takeout();
        r12.readParams(r0, r2);
        return r12;
    }

    @Override // org.telegram.tgnet.TLObject
    public void readParams(InputSerializedData r1, boolean r2) {
        this.id = r1.readInt64(r2);
    }
}
