package com.exteragram.messenger.utils;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChatActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class IntentsController$$ExternalSyntheticLambda12 implements Utilities.Callback {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ ChatActivity f$1;

    public /* synthetic */ IntentsController$$ExternalSyntheticLambda12(AlertDialog r1, ChatActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        IntentsController.$r8$lambda$lhotcLqZBvtmStQ6Sg5mx092EVM(this.f$0, this.f$1, (TLRPC.Chat) r2);
    }
}
