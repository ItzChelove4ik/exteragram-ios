package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$$ExternalSyntheticLambda10 implements Utilities.Callback2 {
    public final /* synthetic */ LinkManager f$0;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$1;

    public /* synthetic */ LinkManager$$ExternalSyntheticLambda10(LinkManager r1, TLRPC.TL_messages_requestUrlAuth r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        LinkManager.$r8$lambda$P_2YKEX1Md8AKf0Uaq6mDBX3rys(this.f$0, this.f$1, (TLRPC.UrlAuthResult) r2, (TLRPC.TL_error) r3);
    }
}
