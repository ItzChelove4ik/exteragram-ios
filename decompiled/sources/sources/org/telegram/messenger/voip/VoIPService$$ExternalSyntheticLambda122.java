package org.telegram.messenger.voip;

import java.util.ArrayList;
import org.telegram.messenger.ChatObject;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda122 implements ChatObject.Call.OnParticipantsLoad {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int[] f$2;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda122(VoIPService r1, long r2, int[] r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.messenger.ChatObject.Call.OnParticipantsLoad
    public final void onLoad(ArrayList r4) {
        VoIPService.$r8$lambda$txW2W0IbOfDRAb7E4Jq2LhSQWI8(this.f$0, this.f$1, this.f$2, r4);
    }
}
