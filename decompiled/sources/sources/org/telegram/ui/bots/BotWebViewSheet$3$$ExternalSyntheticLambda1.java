package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.bots.BotWebViewSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotWebViewSheet$3$$ExternalSyntheticLambda1 implements Utilities.CallbackReturn {
    public final /* synthetic */ TLRPC.User f$0;
    public final /* synthetic */ BulletinFactory.UndoObject f$1;

    public /* synthetic */ BotWebViewSheet$3$$ExternalSyntheticLambda1(TLRPC.User r1, BulletinFactory.UndoObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.CallbackReturn
    public final Object run(Object r2) {
        return BotWebViewSheet.AnonymousClass3.$r8$lambda$3twIhmIucGZKfTd5EjDUYqiHZFY(this.f$0, this.f$1, (BulletinFactory) r2);
    }
}
