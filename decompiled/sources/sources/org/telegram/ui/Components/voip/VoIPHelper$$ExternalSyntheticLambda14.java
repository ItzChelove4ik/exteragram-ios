package org.telegram.ui.Components.voip;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes7.dex */
public final /* synthetic */ class VoIPHelper$$ExternalSyntheticLambda14 implements RequestDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ File f$2;
    public final /* synthetic */ TL_phone.setCallRating f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ Context f$5;

    public /* synthetic */ VoIPHelper$$ExternalSyntheticLambda14(int r1, boolean[] r2, File r3, TL_phone.setCallRating r4, ArrayList r5, Context r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        VoIPHelper.m13961$r8$lambda$_IMzZcdN3bYb4XsU6s2akXo1Wk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
