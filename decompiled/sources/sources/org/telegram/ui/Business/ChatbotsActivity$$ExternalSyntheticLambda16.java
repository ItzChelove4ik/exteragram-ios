package org.telegram.ui.Business;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatbotsActivity$$ExternalSyntheticLambda16 implements RequestDelegate {
    public final /* synthetic */ ChatbotsActivity f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.User f$4;

    public /* synthetic */ ChatbotsActivity$$ExternalSyntheticLambda16(ChatbotsActivity r1, int[] r2, ArrayList r3, boolean r4, TLRPC.User r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        ChatbotsActivity.$r8$lambda$ByccFaO8uO43vhAnQ3bbFkz67_4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
