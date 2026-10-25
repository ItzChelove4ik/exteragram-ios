package org.telegram.messenger;

import java.text.Collator;
import java.util.Comparator;
import org.telegram.messenger.ContactsController;

/* loaded from: classes.dex */
public final /* synthetic */ class ContactsController$$ExternalSyntheticLambda51 implements Comparator {
    public final /* synthetic */ Collator f$0;

    public /* synthetic */ ContactsController$$ExternalSyntheticLambda51(Collator r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return ContactsController.$r8$lambda$yGcrFYDPChyOJMc3cE_7E1f7VFk(this.f$0, (ContactsController.Contact) r1, (ContactsController.Contact) r2);
    }
}
