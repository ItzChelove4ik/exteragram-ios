package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda38 implements Utilities.Callback {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda38(TranslateController r1, MessageObject r2, String r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        TranslateController.m5026$r8$lambda$Ii0Eo4MEvUycC5zsKiV1SAagDw(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.TL_textWithEntities) r7);
    }
}
