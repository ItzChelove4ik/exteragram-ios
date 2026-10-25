package org.telegram.ui;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class PeerColorActivity$$ExternalSyntheticLambda16 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ PeerColorActivity$$ExternalSyntheticLambda16(boolean[] r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r2) {
        PeerColorActivity.$r8$lambda$EJJWxiMyr1tDckZ2sRT08aZBq0c(this.f$0, this.f$1, r2);
    }
}
