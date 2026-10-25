package org.telegram.messenger;

import android.content.SharedPreferences;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ContactsController$$ExternalSyntheticLambda57 implements RequestDelegate {
    public final /* synthetic */ ContactsController f$0;
    public final /* synthetic */ SharedPreferences.Editor f$1;

    public /* synthetic */ ContactsController$$ExternalSyntheticLambda57(ContactsController r1, SharedPreferences.Editor r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ContactsController.m3951$r8$lambda$FoBaAXvnt6Jb3kEdF1M1ph5aKY(this.f$0, this.f$1, r2, r3);
    }
}
