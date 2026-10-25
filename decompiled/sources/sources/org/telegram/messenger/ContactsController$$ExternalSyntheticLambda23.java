package org.telegram.messenger;

import android.content.SharedPreferences;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final /* synthetic */ class ContactsController$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ ContactsController f$0;
    public final /* synthetic */ SharedPreferences.Editor f$1;
    public final /* synthetic */ TLObject f$2;

    public /* synthetic */ ContactsController$$ExternalSyntheticLambda23(ContactsController r1, SharedPreferences.Editor r2, TLObject r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ContactsController.m3964$r8$lambda$fsPGeYDOzu_e9TgPeVmBNZ96zw(this.f$0, this.f$1, this.f$2);
    }
}
