package org.telegram.tgnet;

import org.telegram.tgnet.TLObject;

/* loaded from: classes.dex */
public abstract class TLMethod<T extends TLObject> extends TLObject {
    public TLMethod() {
    }

    @Override // org.telegram.tgnet.TLObject
    public final TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
        return deserializeResponseT(r1, r2, r3);
    }

    public abstract T deserializeResponseT(InputSerializedData r1, int r2, boolean r3);
}
