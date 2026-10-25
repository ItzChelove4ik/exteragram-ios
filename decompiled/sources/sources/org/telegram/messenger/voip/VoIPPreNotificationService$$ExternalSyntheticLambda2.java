package org.telegram.messenger.voip;

import android.content.Context;
import android.content.Intent;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPPreNotificationService$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ Intent f$0;
    public final /* synthetic */ TL_phone.PhoneCall f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ VoIPPreNotificationService$$ExternalSyntheticLambda2(Intent r1, TL_phone.PhoneCall r2, Context r3, int r4, long r5, boolean r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPPreNotificationService.$r8$lambda$lDZeK9o170qagGUvU23zy7BTSdM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
