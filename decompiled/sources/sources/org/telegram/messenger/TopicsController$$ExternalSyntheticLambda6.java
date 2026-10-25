package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class TopicsController$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ TopicsController f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ ArrayList f$3;

    public /* synthetic */ TopicsController$$ExternalSyntheticLambda6(TopicsController r1, BaseFragment r2, long r3, ArrayList r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        TopicsController.$r8$lambda$KLR66BBxLcJGrPz29XRPGhWsUi0(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
