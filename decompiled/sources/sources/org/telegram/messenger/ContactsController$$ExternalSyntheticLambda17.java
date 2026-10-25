package org.telegram.messenger;

import java.text.Collator;
import java.util.Comparator;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ContactsController$$ExternalSyntheticLambda17 implements Comparator {
    public final /* synthetic */ ContactsController f$0;
    public final /* synthetic */ Collator f$1;

    public /* synthetic */ ContactsController$$ExternalSyntheticLambda17(ContactsController r1, Collator r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.util.Comparator
    public final int compare(Object r2, Object r3) {
        return ContactsController.$r8$lambda$luv2pvYNpBak6rdoMFwhTSDLbSg(this.f$0, this.f$1, (TLRPC.TL_contact) r2, (TLRPC.TL_contact) r3);
    }
}
