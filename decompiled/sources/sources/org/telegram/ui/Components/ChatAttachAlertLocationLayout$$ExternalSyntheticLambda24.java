package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertLocationLayout$$ExternalSyntheticLambda24 implements Utilities.Callback {
    public final /* synthetic */ ChatAttachAlertLocationLayout f$0;
    public final /* synthetic */ ChatActivity f$1;
    public final /* synthetic */ TLRPC.TL_messageMediaGeo f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;

    public /* synthetic */ ChatAttachAlertLocationLayout$$ExternalSyntheticLambda24(ChatAttachAlertLocationLayout r1, ChatActivity r2, TLRPC.TL_messageMediaGeo r3, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        ChatAttachAlertLocationLayout.m9632$r8$lambda$Ypb1NXoWWyGxig6v9NamFoVADw(this.f$0, this.f$1, this.f$2, this.f$3, (Long) r4);
    }
}
