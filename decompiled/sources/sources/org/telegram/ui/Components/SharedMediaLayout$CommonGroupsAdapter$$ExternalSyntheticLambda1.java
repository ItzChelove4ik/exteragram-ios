package org.telegram.ui.Components;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.SharedMediaLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$CommonGroupsAdapter$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SharedMediaLayout.CommonGroupsAdapter f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ SharedMediaLayout$CommonGroupsAdapter$$ExternalSyntheticLambda1(SharedMediaLayout.CommonGroupsAdapter r1, TLRPC.TL_error r2, TLObject r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SharedMediaLayout.CommonGroupsAdapter.$r8$lambda$zJeW8FJUhjM6DYp1hem7fvC2Kv0(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
