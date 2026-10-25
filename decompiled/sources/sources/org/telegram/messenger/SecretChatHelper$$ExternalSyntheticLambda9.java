package org.telegram.messenger;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class SecretChatHelper$$ExternalSyntheticLambda9 implements RequestDelegate {
    public final /* synthetic */ SecretChatHelper f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ AlertDialog f$2;
    public final /* synthetic */ TLRPC.User f$3;

    public /* synthetic */ SecretChatHelper$$ExternalSyntheticLambda9(SecretChatHelper r1, Context r2, AlertDialog r3, TLRPC.User r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        SecretChatHelper.m4896$r8$lambda$iMge1erIwjdXjPHfeorR9kRCuk(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
