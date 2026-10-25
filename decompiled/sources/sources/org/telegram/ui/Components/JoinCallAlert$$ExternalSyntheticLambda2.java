package org.telegram.ui.Components;

import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinCallAlert$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ AccountInstance f$2;
    public final /* synthetic */ MessagesStorage.BooleanCallback f$3;

    public /* synthetic */ JoinCallAlert$$ExternalSyntheticLambda2(AlertDialog r1, long r2, AccountInstance r4, MessagesStorage.BooleanCallback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        JoinCallAlert.$r8$lambda$Ve_JMhP7mYnC3dqafaHoLNlPvqA(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
