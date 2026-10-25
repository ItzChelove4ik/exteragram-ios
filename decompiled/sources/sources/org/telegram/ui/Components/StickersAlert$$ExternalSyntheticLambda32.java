package org.telegram.ui.Components;

import android.widget.TextView;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda32 implements RequestDelegate {
    public final /* synthetic */ StickersAlert f$0;
    public final /* synthetic */ EditTextBoldCursor f$1;
    public final /* synthetic */ TextView f$2;
    public final /* synthetic */ TextView f$3;
    public final /* synthetic */ int[] f$4;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda32(StickersAlert r1, EditTextBoldCursor r2, TextView r3, TextView r4, int[] r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        StickersAlert.m13075$r8$lambda$NCyfpcwg3EKNh8DhMyCVClHuxg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
