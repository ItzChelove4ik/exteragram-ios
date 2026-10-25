package com.exteragram.messenger.export.api;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public class ExportRequests$InitTakeoutSession extends TLObject {
    public boolean contacts;
    public long file_max_size;
    public boolean files;
    public int flags;
    public boolean message_channels;
    public boolean message_chats;
    public boolean message_megagroups;
    public boolean message_users;

    public ExportRequests$InitTakeoutSession() {
    }

    @Override // org.telegram.tgnet.TLObject
    public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
        return ExportRequests$Takeout.TLdeserialize(r1, r2, r3);
    }

    @Override // org.telegram.tgnet.TLObject
    public void serializeToStream(OutputSerializedData r3) {
        r3.writeInt32(-1896617296);
        boolean r0 = this.contacts;
        int r1 = this.flags;
        if (r0 == false) goto L5;
        int r02 = r1 | 1;
    L6:
        this.flags = r02;
        if (this.message_users == false) goto L9;
        int r03 = r02 | 2;
    L10:
        this.flags = r03;
        if (this.message_chats == false) goto L13;
        int r04 = r03 | 4;
    L14:
        this.flags = r04;
        if (this.message_megagroups == false) goto L17;
        int r05 = r04 | 8;
    L18:
        this.flags = r05;
        if (this.message_channels == false) goto L21;
        int r06 = r05 | 16;
    L22:
        this.flags = r06;
        if (this.files == false) goto L25;
        int r07 = r06 | 32;
    L26:
        this.flags = r07;
        r3.writeInt32(r07);
        if (this.files == false) goto L30;
        r3.writeInt64(this.file_max_size);
        return;
    L30:
        return;
    L25:
        r07 = r06 & (-33);
        goto L26
    L21:
        r06 = r05 & (-17);
        goto L22
    L17:
        r05 = r04 & (-9);
        goto L18
    L13:
        r04 = r03 & (-5);
        goto L14
    L9:
        r03 = r02 & (-3);
        goto L10
    L5:
        r02 = r1 & (-2);
        goto L6
    }
}
