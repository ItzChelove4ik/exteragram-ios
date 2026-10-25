package org.telegram.ui.Adapters;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class SearchAdapterHelper$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ SearchAdapterHelper f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ SearchAdapterHelper$$ExternalSyntheticLambda0(SearchAdapterHelper r1, String r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        SearchAdapterHelper.$r8$lambda$RtrlhroMtXSvAXmig9EimMIatTo(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
