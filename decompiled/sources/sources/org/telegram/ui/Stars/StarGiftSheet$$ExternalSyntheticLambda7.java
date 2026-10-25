package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TLRPC.TL_messageActionStarGift f$1;
    public final /* synthetic */ AlertDialog f$2;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda7(StarGiftSheet r1, TLRPC.TL_messageActionStarGift r2, AlertDialog r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        StarGiftSheet.$r8$lambda$rz4Rz9ELrVUzSuWE7VDkEK5YEe0(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
