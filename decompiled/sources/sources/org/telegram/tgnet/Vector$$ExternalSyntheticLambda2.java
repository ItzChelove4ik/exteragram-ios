package org.telegram.tgnet;

import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public final /* synthetic */ class Vector$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ OutputSerializedData f$0;

    public /* synthetic */ Vector$$ExternalSyntheticLambda2(OutputSerializedData r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        this.f$0.writeInt64(((Long) r3).longValue());
    }
}
