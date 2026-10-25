package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class HashtagsSearchAdapter$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ HashtagsSearchAdapter f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ HashtagsSearchAdapter$$ExternalSyntheticLambda3(HashtagsSearchAdapter r1, int r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        HashtagsSearchAdapter.m10903$r8$lambda$98xqcYTRI2YFYXs3k4yy1I3DrU(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
