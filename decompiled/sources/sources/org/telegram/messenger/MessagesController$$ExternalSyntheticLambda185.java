package org.telegram.messenger;

import android.content.Context;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda185 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ AlertDialog f$2;
    public final /* synthetic */ MessagesStorage.BooleanCallback f$3;
    public final /* synthetic */ BaseFragment f$4;
    public final /* synthetic */ TLRPC.TL_channels_convertToGigagroup f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda185(MessagesController r1, Context r2, AlertDialog r3, MessagesStorage.BooleanCallback r4, BaseFragment r5, TLRPC.TL_channels_convertToGigagroup r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        MessagesController.m4605$r8$lambda$jKUQKNioWixnD5tFViYziiyeQU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
