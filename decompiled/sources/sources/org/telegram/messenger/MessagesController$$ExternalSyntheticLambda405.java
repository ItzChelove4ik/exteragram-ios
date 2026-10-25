package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda405 implements Utilities.Callback2 {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ TLRPC.TL_messages_editChatDefaultBannedRights f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda405(MessagesController r1, long r2, BaseFragment r4, TLRPC.TL_messages_editChatDefaultBannedRights r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r9, Object r10) {
        MessagesController.m4523$r8$lambda$EUZV7mo0aDGaOTJgkUJRYe83Io(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TLRPC.Updates) r9, (TLRPC.TL_error) r10);
    }
}
