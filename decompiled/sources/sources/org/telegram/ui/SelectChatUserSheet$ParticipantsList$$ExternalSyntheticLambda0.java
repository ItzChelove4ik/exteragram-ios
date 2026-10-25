package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.SelectChatUserSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectChatUserSheet$ParticipantsList$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ SelectChatUserSheet.ParticipantsList f$0;

    public /* synthetic */ SelectChatUserSheet$ParticipantsList$$ExternalSyntheticLambda0(SelectChatUserSheet.ParticipantsList r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        SelectChatUserSheet.ParticipantsList.$r8$lambda$ZXBel_Twkmis7L4JjHa6vEDsPqk(this.f$0, (TLRPC.channels_ChannelParticipants) r1, (TLRPC.TL_error) r2);
    }
}
