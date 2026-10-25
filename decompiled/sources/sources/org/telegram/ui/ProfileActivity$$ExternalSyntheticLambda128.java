package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda128 implements MessagesController.ErrorDelegate {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ BaseFragment f$3;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda128(ProfileActivity r1, boolean[] r2, boolean r3, BaseFragment r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MessagesController.ErrorDelegate
    public final boolean run(TLRPC.TL_error r4) {
        return ProfileActivity.m17832$r8$lambda$moTL5DvSemoRat9aVh01PE_4f4(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
