package org.telegram.messenger;

import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class LocaleController$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ LocaleController f$0;
    public final /* synthetic */ LocaleController.LocaleInfo f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ LocaleController$$ExternalSyntheticLambda2(LocaleController r1, LocaleController.LocaleInfo r2, int r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        LocaleController.m4244$r8$lambda$t45j5nZcXoa7lSmpb3Rb3jT6q4(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
