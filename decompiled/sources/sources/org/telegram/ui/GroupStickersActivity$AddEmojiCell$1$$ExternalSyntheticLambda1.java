package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.GroupStickersActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupStickersActivity$AddEmojiCell$1$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ GroupStickersActivity.AddEmojiCell.AnonymousClass1 f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ GroupStickersActivity$AddEmojiCell$1$$ExternalSyntheticLambda1(GroupStickersActivity.AddEmojiCell.AnonymousClass1 r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        GroupStickersActivity.AddEmojiCell.AnonymousClass1.$r8$lambda$cK2YCR6DRHtn7IzGYuVycwK4znQ(this.f$0, this.f$1, r2, r3);
    }
}
