package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ String[] f$1;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ BottomSheet f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ Theme.ResourcesProvider f$6;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda1(Context r1, String[] r2, TLRPC.TL_messages_requestUrlAuth r3, int r4, BottomSheet r5, String r6, Theme.ResourcesProvider r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r9) {
        OAuthSheet.m16354$r8$lambda$xnxdbcMFvYfYefG2jCmQJ3S1m4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, (String) r9);
    }
}
