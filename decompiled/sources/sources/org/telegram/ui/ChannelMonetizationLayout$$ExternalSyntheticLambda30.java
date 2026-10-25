package org.telegram.ui;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelMonetizationLayout$$ExternalSyntheticLambda30 implements RequestDelegate {
    public final /* synthetic */ ChannelMonetizationLayout f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ ChannelMonetizationLayout$$ExternalSyntheticLambda30(ChannelMonetizationLayout r1, Context r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChannelMonetizationLayout.$r8$lambda$3vksFnduStG984F4e7FfDJTgRuc(this.f$0, this.f$1, r2, r3);
    }
}
