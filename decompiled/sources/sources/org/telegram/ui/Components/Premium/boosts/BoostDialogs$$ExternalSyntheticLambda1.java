package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostDialogs$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ MessageObject f$0;

    public /* synthetic */ BoostDialogs$$ExternalSyntheticLambda1(MessageObject r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        BoostDialogs.m12073$r8$lambda$gpY5WECZOxGjtBvgYQyOdQKExk(this.f$0, (TLRPC.payments_GiveawayInfo) r1);
    }
}
