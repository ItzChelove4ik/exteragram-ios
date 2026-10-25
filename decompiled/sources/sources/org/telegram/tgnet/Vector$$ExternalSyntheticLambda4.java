package org.telegram.tgnet;

import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public final /* synthetic */ class Vector$$ExternalSyntheticLambda4 implements Utilities.CallbackReturn {
    public final /* synthetic */ InputSerializedData f$0;

    public /* synthetic */ Vector$$ExternalSyntheticLambda4(InputSerializedData r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.CallbackReturn
    public final Object run(Object r1) {
        return this.f$0.readString(((Boolean) r1).booleanValue());
    }
}
