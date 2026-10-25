package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Cells.SharedPhotoVideoCell2;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda24 implements Utilities.Callback {
    public final /* synthetic */ SharedMediaLayout f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ MessagesController f$2;
    public final /* synthetic */ Context f$3;
    public final /* synthetic */ SharedPhotoVideoCell2 f$4;
    public final /* synthetic */ float f$5;
    public final /* synthetic */ float f$6;
    public final /* synthetic */ int f$7;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda24(SharedMediaLayout r1, AlertDialog r2, MessagesController r3, Context r4, SharedPhotoVideoCell2 r5, float r6, float r7, int r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r10) {
        SharedMediaLayout.$r8$lambda$BkrhzrL_huCO1Fx4Y7WkDm6SBC0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, (TL_account.contentSettings) r10);
    }
}
