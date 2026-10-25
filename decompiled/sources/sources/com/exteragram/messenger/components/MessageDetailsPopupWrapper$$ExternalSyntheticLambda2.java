package com.exteragram.messenger.components;

import com.exteragram.messenger.components.MessageDetailsPopupWrapper;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;

/* loaded from: classes4.dex */
public final /* synthetic */ class MessageDetailsPopupWrapper$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ MessageDetailsPopupWrapper.Item f$0;
    public final /* synthetic */ ActionBarMenuSubItem f$1;

    public /* synthetic */ MessageDetailsPopupWrapper$$ExternalSyntheticLambda2(MessageDetailsPopupWrapper.Item r1, ActionBarMenuSubItem r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        MessageDetailsPopupWrapper.$r8$lambda$dE2BmWFMp3ZO7Tcpe0bsPv1CgxY(this.f$0, this.f$1, (TLRPC.User) r2);
    }
}
