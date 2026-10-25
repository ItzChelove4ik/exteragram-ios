package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditTypeActivity$$ExternalSyntheticLambda22 implements RequestDelegate {
    public final /* synthetic */ ChatEditTypeActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.TL_channels_checkUsername f$2;

    public /* synthetic */ ChatEditTypeActivity$$ExternalSyntheticLambda22(ChatEditTypeActivity r1, String r2, TLRPC.TL_channels_checkUsername r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChatEditTypeActivity.$r8$lambda$Lam26LIOpU_qAQiQXdDyOLNe4Vw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
