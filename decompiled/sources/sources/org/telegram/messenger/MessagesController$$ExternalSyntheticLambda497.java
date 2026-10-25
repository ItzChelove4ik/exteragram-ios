package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda497 implements Utilities.Callback2 {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda497(MessagesController r1, long r2, Utilities.Callback2 r4, boolean r5, long r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r10, Object r11) {
        MessagesController.m4540$r8$lambda$MEEGcFZkv_Q3iG74RPKWKWBFp4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TLRPC.Bool) r10, (TLRPC.TL_error) r11);
    }
}
