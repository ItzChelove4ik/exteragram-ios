package org.telegram.tgnet;

import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public final /* synthetic */ class Vector$$ExternalSyntheticLambda3 implements Utilities.Callback {
    public final /* synthetic */ OutputSerializedData f$0;

    public /* synthetic */ Vector$$ExternalSyntheticLambda3(OutputSerializedData r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.writeInt32(((Integer) r1).intValue());
    }
}
