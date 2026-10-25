package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.text.Collator;
import java.util.Comparator;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class ContactsController$$ExternalSyntheticLambda40 implements Comparator {
    public final /* synthetic */ LongSparseArray f$0;
    public final /* synthetic */ Collator f$1;

    public /* synthetic */ ContactsController$$ExternalSyntheticLambda40(LongSparseArray r1, Collator r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.util.Comparator
    public final int compare(Object r2, Object r3) {
        return ContactsController.m3955$r8$lambda$KkK_FcxfCxCbUA2tkb9JlJ5g(this.f$0, this.f$1, (TLRPC.TL_contact) r2, (TLRPC.TL_contact) r3);
    }
}
