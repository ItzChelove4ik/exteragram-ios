package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SelectAudioAlert$$ExternalSyntheticLambda6 implements Utilities.Callback2 {
    public final /* synthetic */ SelectAudioAlert f$0;

    public /* synthetic */ SelectAudioAlert$$ExternalSyntheticLambda6(SelectAudioAlert r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        SelectAudioAlert.$r8$lambda$Qo65yDJ9jlnDuZFP3JkLCtfwgeE(this.f$0, (TLRPC.messages_BotResults) r1, (TLRPC.TL_error) r2);
    }
}
