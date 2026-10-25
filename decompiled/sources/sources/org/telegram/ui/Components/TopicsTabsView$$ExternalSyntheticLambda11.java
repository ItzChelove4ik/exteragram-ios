package org.telegram.ui.Components;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;

/* loaded from: classes7.dex */
public final /* synthetic */ class TopicsTabsView$$ExternalSyntheticLambda11 implements MessagesController.IsInChatCheckedCallback {
    public final /* synthetic */ TopicsTabsView f$0;
    public final /* synthetic */ ActionBarMenuSubItem f$1;
    public final /* synthetic */ ItemOptions f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ TLRPC.User f$4;
    public final /* synthetic */ TLRPC.Chat f$5;

    public /* synthetic */ TopicsTabsView$$ExternalSyntheticLambda11(TopicsTabsView r1, ActionBarMenuSubItem r2, ItemOptions r3, long r4, TLRPC.User r6, TLRPC.Chat r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // org.telegram.messenger.MessagesController.IsInChatCheckedCallback
    public final void run(boolean r11, TLRPC.TL_chatAdminRights r12, String r13) {
        TopicsTabsView.$r8$lambda$_VH_Iq1d6tJdsCGTb25BD3Z5zBk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r11, r12, r13);
    }
}
