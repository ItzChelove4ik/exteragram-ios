package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class NewContactBottomSheet$$ExternalSyntheticLambda20 implements RequestDelegate {
    public final /* synthetic */ NewContactBottomSheet f$0;
    public final /* synthetic */ TLRPC.TL_inputPhoneContact f$1;
    public final /* synthetic */ TLRPC.TL_contacts_importContacts f$2;

    public /* synthetic */ NewContactBottomSheet$$ExternalSyntheticLambda20(NewContactBottomSheet r1, TLRPC.TL_inputPhoneContact r2, TLRPC.TL_contacts_importContacts r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        NewContactBottomSheet.m16241$r8$lambda$y7B7niSVWLnDL3UwrdJIU4kY(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
