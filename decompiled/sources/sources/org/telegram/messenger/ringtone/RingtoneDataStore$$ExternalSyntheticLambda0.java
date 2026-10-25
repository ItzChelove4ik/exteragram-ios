package org.telegram.messenger.ringtone;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class RingtoneDataStore$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ RingtoneDataStore f$0;

    public /* synthetic */ RingtoneDataStore$$ExternalSyntheticLambda0(RingtoneDataStore r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        RingtoneDataStore.$r8$lambda$dWXM_IuuAUKgUYPTRnnb9Ibffb0(this.f$0, r1, r2);
    }
}
