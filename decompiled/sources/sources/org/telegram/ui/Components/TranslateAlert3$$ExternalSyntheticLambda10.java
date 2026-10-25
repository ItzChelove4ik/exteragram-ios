package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class TranslateAlert3$$ExternalSyntheticLambda10 implements Utilities.Callback2 {
    public final /* synthetic */ TranslateAlert3 f$0;

    public /* synthetic */ TranslateAlert3$$ExternalSyntheticLambda10(TranslateAlert3 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        TranslateAlert3.$r8$lambda$sIfaKWO6YFaUqQgfkztbB5Fnlpc(this.f$0, (TLRPC.TL_messages_translateResult) r1, (TLRPC.TL_error) r2);
    }
}
