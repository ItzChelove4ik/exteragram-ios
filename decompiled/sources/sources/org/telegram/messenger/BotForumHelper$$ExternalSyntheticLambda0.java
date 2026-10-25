package org.telegram.messenger;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final /* synthetic */ class BotForumHelper$$ExternalSyntheticLambda0 implements MessagesStorage.IntCallback {
    public final /* synthetic */ BotForumHelper f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ long[] f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ BotForumHelper$$ExternalSyntheticLambda0(BotForumHelper r1, TLObject r2, long[] r3, long r4, Runnable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.MessagesStorage.IntCallback
    public final void run(int r8) {
        BotForumHelper.m3897$r8$lambda$2DtSuXBVrDlclz2omVJ1u392_4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8);
    }
}
