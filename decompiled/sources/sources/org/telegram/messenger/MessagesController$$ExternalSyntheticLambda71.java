package org.telegram.messenger;

import android.content.Context;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda71 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ MessagesStorage.BooleanCallback f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ AlertDialog f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;
    public final /* synthetic */ BaseFragment f$5;
    public final /* synthetic */ TLRPC.TL_channels_convertToGigagroup f$6;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda71(MessagesController r1, MessagesStorage.BooleanCallback r2, Context r3, AlertDialog r4, TLRPC.TL_error r5, BaseFragment r6, TLRPC.TL_channels_convertToGigagroup r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4606$r8$lambda$jqlHepFFtD4v_NsrhoDxZJ1XLk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
