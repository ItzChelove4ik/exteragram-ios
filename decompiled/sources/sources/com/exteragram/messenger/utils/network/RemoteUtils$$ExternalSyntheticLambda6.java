package com.exteragram.messenger.utils.network;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ AtomicReference f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda6(AtomicBoolean r1, AtomicReference r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        RemoteUtils.$r8$lambda$VPbxsBgeVDZRlr6n6eb9kvQGVxM(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
