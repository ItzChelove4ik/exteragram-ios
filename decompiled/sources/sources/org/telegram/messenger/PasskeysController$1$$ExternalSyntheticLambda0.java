package org.telegram.messenger;

import org.telegram.messenger.PasskeysController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class PasskeysController$1$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ Utilities.Callback3 f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ PasskeysController$1$$ExternalSyntheticLambda0(AlertDialog r1, Utilities.Callback3 r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        PasskeysController.AnonymousClass1.$r8$lambda$gx4vqzEbMkPbuY3kEBWRgHgji2Q(this.f$0, this.f$1, this.f$2, (TLRPC.auth_Authorization) r7, (TLRPC.TL_error) r8);
    }
}
