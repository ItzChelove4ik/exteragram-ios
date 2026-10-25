package org.telegram.messenger.voip;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda115 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TL_phone.PhoneCall f$2;
    public final /* synthetic */ TL_phone.exportGroupCallInvite f$3;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda115(VoIPService r1, TLObject r2, TL_phone.PhoneCall r3, TL_phone.exportGroupCallInvite r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.$r8$lambda$Uidj1P3stPhlo1WRSQ3lEbFj0Vk(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
