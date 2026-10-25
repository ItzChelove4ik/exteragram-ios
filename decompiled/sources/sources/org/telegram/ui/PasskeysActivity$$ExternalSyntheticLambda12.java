package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes6.dex */
public final /* synthetic */ class PasskeysActivity$$ExternalSyntheticLambda12 implements Utilities.Callback2 {
    public final /* synthetic */ BottomSheet f$0;
    public final /* synthetic */ TL_account.Passkey f$1;
    public final /* synthetic */ BulletinFactory f$2;

    public /* synthetic */ PasskeysActivity$$ExternalSyntheticLambda12(BottomSheet r1, TL_account.Passkey r2, BulletinFactory r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        PasskeysActivity.m16390$r8$lambda$IjsLdKNQgpIsFobjMO60p983YE(this.f$0, this.f$1, this.f$2, (TL_account.Passkeys) r3, (TLRPC.TL_error) r4);
    }
}
