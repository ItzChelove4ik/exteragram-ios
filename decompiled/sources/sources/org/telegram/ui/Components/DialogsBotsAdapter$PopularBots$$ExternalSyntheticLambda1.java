package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.DialogsBotsAdapter;

/* loaded from: classes7.dex */
public final /* synthetic */ class DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ DialogsBotsAdapter.PopularBots f$0;

    public /* synthetic */ DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda1(DialogsBotsAdapter.PopularBots r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        DialogsBotsAdapter.PopularBots.$r8$lambda$8S_CP7Nl2fIoqEWJFsFlmVli3LE(this.f$0, r1, r2);
    }
}
