package org.telegram.ui.Adapters;

import java.util.ArrayList;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsSearchAdapter$$ExternalSyntheticLambda31 implements Runnable {
    public final /* synthetic */ DialogsSearchAdapter f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ TLObject f$5;
    public final /* synthetic */ TLRPC.TL_messages_search f$6;
    public final /* synthetic */ ArrayList f$7;

    public /* synthetic */ DialogsSearchAdapter$$ExternalSyntheticLambda31(DialogsSearchAdapter r1, int r2, int r3, TLRPC.TL_error r4, String r5, TLObject r6, TLRPC.TL_messages_search r7, ArrayList r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DialogsSearchAdapter.$r8$lambda$K89pScLPxz8A7hNs50DvhqLPPtU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
