package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$ListAdapter$13$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ ProfileActivity.ListAdapter.AnonymousClass13 f$0;
    public final /* synthetic */ TLRPC.TL_username f$1;

    public /* synthetic */ ProfileActivity$ListAdapter$13$$ExternalSyntheticLambda0(ProfileActivity.ListAdapter.AnonymousClass13 r1, TLRPC.TL_username r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ProfileActivity.ListAdapter.AnonymousClass13.$r8$lambda$yJy34C32ow8Pt9ud2uMpG46hm3c(this.f$0, this.f$1, r2, r3);
    }
}
