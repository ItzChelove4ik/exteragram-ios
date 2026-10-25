package org.telegram.ui.Stars;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda77 implements Utilities.Callback {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean[] f$3;
    public final /* synthetic */ Utilities.Callback2 f$4;
    public final /* synthetic */ Context f$5;
    public final /* synthetic */ Theme.ResourcesProvider f$6;
    public final /* synthetic */ TLRPC.ChatInvite f$7;
    public final /* synthetic */ String f$8;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda77(StarsController r1, long r2, int r4, boolean[] r5, Utilities.Callback2 r6, Context r7, Theme.ResourcesProvider r8, TLRPC.ChatInvite r9, String r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r12) {
        StarsController.$r8$lambda$w3uCbCgIVymCvMeyjOt1SK6qFro(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, (Utilities.Callback) r12);
    }
}
