package org.telegram.ui;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class NewContactBottomSheet$$ExternalSyntheticLambda21 implements Runnable {
    public final /* synthetic */ NewContactBottomSheet f$0;
    public final /* synthetic */ TLRPC.TL_contacts_importedContacts f$1;
    public final /* synthetic */ TLRPC.TL_inputPhoneContact f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ TLRPC.TL_contacts_importContacts f$4;

    public /* synthetic */ NewContactBottomSheet$$ExternalSyntheticLambda21(NewContactBottomSheet r1, TLRPC.TL_contacts_importedContacts r2, TLRPC.TL_inputPhoneContact r3, TLRPC.TL_error r4, TLRPC.TL_contacts_importContacts r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NewContactBottomSheet.$r8$lambda$SThkTrpozKElu1GdN6D1wWxpYE0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
