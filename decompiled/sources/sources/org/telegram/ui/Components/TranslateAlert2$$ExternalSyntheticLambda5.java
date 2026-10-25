package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class TranslateAlert2$$ExternalSyntheticLambda5 implements Utilities.Callback2 {
    public final /* synthetic */ TranslateAlert2 f$0;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$1;

    public /* synthetic */ TranslateAlert2$$ExternalSyntheticLambda5(TranslateAlert2 r1, TLRPC.TL_textWithEntities r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        TranslateAlert2.$r8$lambda$3u54QgPdS6I8Bf51ghDdskVS7v4(this.f$0, this.f$1, (TLRPC.TL_textWithEntities) r2, (TLRPC.TL_error) r3);
    }
}
