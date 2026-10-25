package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.CallLogActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$9$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CallLogActivity.AnonymousClass9 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ CallLogActivity$9$$ExternalSyntheticLambda2(CallLogActivity.AnonymousClass9 r1, TLObject r2, int r3, TLRPC.User r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CallLogActivity.AnonymousClass9.$r8$lambda$YGNELXTi2_NiypRWqvBZe54Ojzc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
