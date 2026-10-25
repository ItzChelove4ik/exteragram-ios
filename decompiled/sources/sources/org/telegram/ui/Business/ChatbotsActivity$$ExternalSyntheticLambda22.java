package org.telegram.ui.Business;

import java.util.ArrayList;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatbotsActivity$$ExternalSyntheticLambda22 implements Runnable {
    public final /* synthetic */ ChatbotsActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ int[] f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ TLRPC.User f$6;

    public /* synthetic */ ChatbotsActivity$$ExternalSyntheticLambda22(ChatbotsActivity r1, TLRPC.TL_error r2, TLObject r3, int[] r4, ArrayList r5, boolean r6, TLRPC.User r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatbotsActivity.$r8$lambda$Zwv56h0lc2aJRGN9be2wnhqis6M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
