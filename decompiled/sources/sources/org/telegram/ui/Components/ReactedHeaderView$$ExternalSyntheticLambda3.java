package org.telegram.ui.Components;

import java.util.List;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class ReactedHeaderView$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ ReactedHeaderView f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ List f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ ReactedHeaderView$$ExternalSyntheticLambda3(ReactedHeaderView r1, List r2, List r3, List r4, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        ReactedHeaderView.m12292$r8$lambda$BSZSHO70oq4bTYJR3rHB5PTT0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
