package org.telegram.ui.Stars;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$10;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ Utilities.Callback2 f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ String f$7;
    public final /* synthetic */ long f$8;
    public final /* synthetic */ Object f$9;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda1(StarsController r1, TLObject r2, TLRPC.TL_error r3, Utilities.Callback2 r4, Context r5, Theme.ResourcesProvider r6, long r7, String r9, long r10, Object r12, TLRPC.TL_textWithEntities r13) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r12;
        this.f$10 = r13;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.$r8$lambda$9_sKfZIxU4nB2WcYAzpSXUAMAkE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10);
    }
}
