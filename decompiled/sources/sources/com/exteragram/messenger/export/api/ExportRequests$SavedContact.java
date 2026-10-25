package com.exteragram.messenger.export.api;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public class ExportRequests$SavedContact extends TLObject {
    public int date;
    public String first_name;
    public String last_name;
    public String phone;

    public ExportRequests$SavedContact() {
    }

    public static ExportRequests$SavedContact TLdeserialize(InputSerializedData r0, int r1, boolean r2) {
        ExportRequests$SavedContact r12 = new ExportRequests$SavedContact();
        r12.readParams(r0, r2);
        return r12;
    }

    @Override // org.telegram.tgnet.TLObject
    public void readParams(InputSerializedData r2, boolean r3) {
        this.phone = r2.readString(r3);
        this.first_name = r2.readString(r3);
        this.last_name = r2.readString(r3);
        this.date = r2.readInt32(r3);
    }
}
