package org.telegram.messenger;

import android.content.Context;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda22 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ AlertDialog f$2;
    public final /* synthetic */ MessagesStorage.LongCallback f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ Runnable f$5;
    public final /* synthetic */ BaseFragment f$6;
    public final /* synthetic */ TLRPC.TL_messages_migrateChat f$7;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda22(MessagesController r1, Context r2, AlertDialog r3, MessagesStorage.LongCallback r4, long r5, Runnable r7, BaseFragment r8, TLRPC.TL_messages_migrateChat r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        MessagesController.$r8$lambda$CDEDDZSmM8hDx9HvKh1wviJIHm0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r12, r13);
    }
}
