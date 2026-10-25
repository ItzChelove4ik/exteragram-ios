package org.telegram.messenger;

import android.content.Context;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class SecretChatHelper$$ExternalSyntheticLambda14 implements Runnable {
    public final /* synthetic */ SecretChatHelper f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ AlertDialog f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ byte[] f$4;
    public final /* synthetic */ TLRPC.User f$5;

    public /* synthetic */ SecretChatHelper$$ExternalSyntheticLambda14(SecretChatHelper r1, Context r2, AlertDialog r3, TLObject r4, byte[] r5, TLRPC.User r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SecretChatHelper.$r8$lambda$UcyhURz9Wt8Gk_Y3ixWPYm509po(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
