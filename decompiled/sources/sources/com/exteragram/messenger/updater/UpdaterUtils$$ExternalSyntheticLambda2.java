package com.exteragram.messenger.updater;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class UpdaterUtils$$ExternalSyntheticLambda2 implements Utilities.Callback2 {
    public final /* synthetic */ Utilities.Callback2 f$0;

    public /* synthetic */ UpdaterUtils$$ExternalSyntheticLambda2(Utilities.Callback2 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        UpdaterUtils.$r8$lambda$2UAZ2HqXxk8Nz7zqmbeCwTKXcr0(this.f$0, (TLRPC.messages_Messages) r1, (TLRPC.TL_error) r2);
    }
}
