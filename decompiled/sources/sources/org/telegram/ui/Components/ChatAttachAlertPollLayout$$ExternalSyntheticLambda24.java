package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.ChatAttachAlertAudioLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPollLayout$$ExternalSyntheticLambda24 implements ChatAttachAlertAudioLayout.AudioSelectDelegate {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ ChatAttachAlert f$1;

    public /* synthetic */ ChatAttachAlertPollLayout$$ExternalSyntheticLambda24(Utilities.Callback r1, ChatAttachAlert r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlertAudioLayout.AudioSelectDelegate
    public final void didSelectAudio(ArrayList r13, CharSequence r14, boolean r15, int r16, int r17, long r18, boolean r20, long r21) {
        ChatAttachAlertPollLayout.$r8$lambda$YbyRLXlAvusUY_jQksdYboAzouA(this.f$0, this.f$1, r13, r14, r15, r16, r17, r18, r20, r21);
    }
}
