package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$7$$ExternalSyntheticLambda10 implements MessagesController.IsInChatCheckedCallback {
    public final /* synthetic */ ProfileActivity.AnonymousClass7 f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ DialogsActivity f$2;

    public /* synthetic */ ProfileActivity$7$$ExternalSyntheticLambda10(ProfileActivity.AnonymousClass7 r1, long r2, DialogsActivity r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.messenger.MessagesController.IsInChatCheckedCallback
    public final void run(boolean r8, TLRPC.TL_chatAdminRights r9, String r10) {
        ProfileActivity.AnonymousClass7.$r8$lambda$JbVA2_sKdEtaj6J4aA9lbaQF9Jo(this.f$0, this.f$1, this.f$2, r8, r9, r10);
    }
}
