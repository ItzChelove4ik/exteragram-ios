package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.DialogsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$52$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ DialogsActivity.AnonymousClass52 f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ DialogsActivity$52$$ExternalSyntheticLambda3(DialogsActivity.AnonymousClass52 r1, TLRPC.TL_error r2, TLObject r3, String r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DialogsActivity.AnonymousClass52.$r8$lambda$ndVZJQDAZD2iy5KNXIebmu0J76w(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
