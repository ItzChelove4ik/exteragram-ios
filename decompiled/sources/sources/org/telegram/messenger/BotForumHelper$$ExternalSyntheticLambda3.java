package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class BotForumHelper$$ExternalSyntheticLambda3 implements Utilities.Callback2 {
    public final /* synthetic */ BotForumHelper f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ BotForumHelper$$ExternalSyntheticLambda3(BotForumHelper r1, long r2, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        BotForumHelper.m3898$r8$lambda$dDYpsdbSCGNgX5PYoeLWu5vfBg(this.f$0, this.f$1, this.f$2, (TLRPC.Updates) r7, (TLRPC.TL_error) r8);
    }
}
