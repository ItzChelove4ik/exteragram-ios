package org.telegram.ui.Stars;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda147 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ Object f$7;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$8;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda147(StarsController r1, Utilities.Callback2 r2, Context r3, Theme.ResourcesProvider r4, long r5, String r7, long r8, Object r10, TLRPC.TL_textWithEntities r11) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r10;
        this.f$8 = r11;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r14, TLRPC.TL_error r15) {
        StarsController.m19127$r8$lambda$_xLyrYswcPMrlE9pCmD51wG2WU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r14, r15);
    }
}
