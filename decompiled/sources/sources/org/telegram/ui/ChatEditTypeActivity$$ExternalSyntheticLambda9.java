package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditTypeActivity$$ExternalSyntheticLambda9 implements RequestDelegate {
    public final /* synthetic */ ChatEditTypeActivity f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ChatEditTypeActivity$$ExternalSyntheticLambda9(ChatEditTypeActivity r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChatEditTypeActivity.$r8$lambda$seh81AbmKw8csda9JhXV4Vnlm2Q(this.f$0, this.f$1, r2, r3);
    }
}
