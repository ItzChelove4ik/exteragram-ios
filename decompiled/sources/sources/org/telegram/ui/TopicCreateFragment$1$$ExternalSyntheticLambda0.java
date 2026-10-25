package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.TopicCreateFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class TopicCreateFragment$1$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ TopicCreateFragment.AnonymousClass1 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ AlertDialog f$2;

    public /* synthetic */ TopicCreateFragment$1$$ExternalSyntheticLambda0(TopicCreateFragment.AnonymousClass1 r1, String r2, AlertDialog r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        TopicCreateFragment.AnonymousClass1.$r8$lambda$iNFPvgVNVpBR8EQajc7nBAHNcns(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
