package org.telegram.messenger.voip;

import android.content.SharedPreferences;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda84 implements RequestDelegate {
    public final /* synthetic */ SharedPreferences f$0;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda84(SharedPreferences r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        VoIPService.$r8$lambda$ky0Ni3_eN3vaYNjuGuhVV0p6TQM(this.f$0, r1, r2);
    }
}
