package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda3 implements Utilities.Callback2 {
    public final /* synthetic */ MediaDataController f$0;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda3(MediaDataController r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        MediaDataController.$r8$lambda$0qsbiyJdK83D8IJyvE7btxB1wus(this.f$0, (TLRPC.contacts_TopPeers) r1, (TLRPC.TL_error) r2);
    }
}
