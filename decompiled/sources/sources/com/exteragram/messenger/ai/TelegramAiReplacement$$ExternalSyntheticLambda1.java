package com.exteragram.messenger.ai;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class TelegramAiReplacement$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ TelegramAiReplacement$$ExternalSyntheticLambda1(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        TelegramAiReplacement.$r8$lambda$fIYiAZvLeJg9cGEjBrrQSuw5KDg(this.f$0, (TLRPC.TL_textWithEntities) r1);
    }
}
