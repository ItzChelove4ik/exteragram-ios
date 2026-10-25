package com.exteragram.messenger.utils.network;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda7 implements Utilities.Callback {
    public final /* synthetic */ TLRPC.TL_messages_search f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ AtomicReference f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ AtomicBoolean f$4;
    public final /* synthetic */ Utilities.Callback2 f$5;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda7(TLRPC.TL_messages_search r1, Runnable r2, AtomicReference r3, int r4, AtomicBoolean r5, Utilities.Callback2 r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        RemoteUtils.$r8$lambda$84O1WLvUC3qDyZTPzn_dgZu6QAQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TLRPC.Chat) r8);
    }
}
