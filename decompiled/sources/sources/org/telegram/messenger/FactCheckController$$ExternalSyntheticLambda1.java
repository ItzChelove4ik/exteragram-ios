package org.telegram.messenger;

import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class FactCheckController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ FactCheckController f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_getFactCheck f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ HashMap f$4;

    public /* synthetic */ FactCheckController$$ExternalSyntheticLambda1(FactCheckController r1, TLObject r2, TLRPC.TL_getFactCheck r3, ArrayList r4, HashMap r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FactCheckController.$r8$lambda$sZwNbzSKTw4HXRYJF_5OjVglHcQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
