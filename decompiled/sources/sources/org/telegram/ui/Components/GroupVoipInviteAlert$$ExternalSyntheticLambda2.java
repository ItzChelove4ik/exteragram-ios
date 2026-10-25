package org.telegram.ui.Components;

import java.util.Comparator;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;

/* loaded from: classes7.dex */
public final /* synthetic */ class GroupVoipInviteAlert$$ExternalSyntheticLambda2 implements Comparator {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ GroupVoipInviteAlert$$ExternalSyntheticLambda2(MessagesController r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.util.Comparator
    public final int compare(Object r2, Object r3) {
        return GroupVoipInviteAlert.$r8$lambda$Jxjb1g4dHVPktfHUABWJR3wxpuE(this.f$0, this.f$1, (TLObject) r2, (TLObject) r3);
    }
}
