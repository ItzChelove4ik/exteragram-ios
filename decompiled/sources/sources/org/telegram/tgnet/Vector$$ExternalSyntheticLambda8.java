package org.telegram.tgnet;

import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class Vector$$ExternalSyntheticLambda8 implements Utilities.Callback {
    public final /* synthetic */ OutputSerializedData f$0;

    public /* synthetic */ Vector$$ExternalSyntheticLambda8(OutputSerializedData r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.writeString((String) r1);
    }
}
