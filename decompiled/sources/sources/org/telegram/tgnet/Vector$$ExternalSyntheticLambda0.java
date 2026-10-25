package org.telegram.tgnet;

import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public final /* synthetic */ class Vector$$ExternalSyntheticLambda0 implements Utilities.CallbackReturn {
    public final /* synthetic */ InputSerializedData f$0;

    public /* synthetic */ Vector$$ExternalSyntheticLambda0(InputSerializedData r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.CallbackReturn
    public final Object run(Object r1) {
        return Long.valueOf(this.f$0.readInt64(((Boolean) r1).booleanValue()));
    }
}
