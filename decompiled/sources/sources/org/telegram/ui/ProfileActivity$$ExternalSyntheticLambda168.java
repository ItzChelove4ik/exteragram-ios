package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ShareAlert;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda168 implements Runnable {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_username f$2;
    public final /* synthetic */ ShareAlert f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda168(ProfileActivity r1, TLObject r2, TLRPC.TL_username r3, ShareAlert r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileActivity.$r8$lambda$H_QhY2w6NLn3VS6L6jlwEyuGJp0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
