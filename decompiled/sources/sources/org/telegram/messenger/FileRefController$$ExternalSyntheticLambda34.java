package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class FileRefController$$ExternalSyntheticLambda34 implements Utilities.Callback2 {
    public final /* synthetic */ FileRefController f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ FileRefController$$ExternalSyntheticLambda34(FileRefController r1, String r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        FileRefController.$r8$lambda$lWmCoDpnqzY9rdEGmNMTbbgNGXM(this.f$0, this.f$1, this.f$2, (TLRPC.TL_help_appUpdate) r3, (TLRPC.TL_error) r4);
    }
}
