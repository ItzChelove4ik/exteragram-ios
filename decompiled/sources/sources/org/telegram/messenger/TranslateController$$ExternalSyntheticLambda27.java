package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda27 implements Utilities.Callback2 {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda27(TranslateController r1, int r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        TranslateController.$r8$lambda$M5M9EgLyOwp87rRJR4Cbd58ID74(this.f$0, this.f$1, this.f$2, (TLRPC.TL_textWithEntities) r3, (TLRPC.TL_error) r4);
    }
}
