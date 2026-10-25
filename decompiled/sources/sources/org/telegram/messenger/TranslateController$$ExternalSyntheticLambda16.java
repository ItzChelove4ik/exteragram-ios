package org.telegram.messenger;

import java.util.Set;
import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda16 implements RequestDelegate {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ TranslateController.PendingTranslation f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Set f$4;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda16(TranslateController r1, TranslateController.PendingTranslation r2, boolean r3, long r4, Set r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        TranslateController.m5028$r8$lambda$QwfRixmzOArC6NScDsAiwzbhLw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
