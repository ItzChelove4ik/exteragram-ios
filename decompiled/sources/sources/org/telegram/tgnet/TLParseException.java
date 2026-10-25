package org.telegram.tgnet;

import org.telegram.messenger.FileLog;

/* loaded from: classes.dex */
public class TLParseException extends RuntimeException {
    private TLParseException(String r1) {
        super(r1);
    }

    public static void doThrowOrLog(InputSerializedData r0, String r1, int r2, boolean r3) {
        if (r0 == null) goto L4;
        TLDataSourceType r02 = r0.getDataSourceType();
    L5:
        TLParseException r12 = new TLParseException(String.format("can't parse magic %x in %s. Source: %s", new Object[]{Integer.valueOf(r2), r1, r02}));
        FileLog.e(r12);
        if (r3 == true) goto L8;
        return;
    L8:
        throw r12;
    L4:
        r02 = null;
        goto L5
    }
}
