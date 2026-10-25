package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoPickerActivity$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ PhotoPickerActivity f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ PhotoPickerActivity$$ExternalSyntheticLambda7(PhotoPickerActivity r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PhotoPickerActivity.m16797$r8$lambda$QEFfUM9ShBtqBNAGTbKOEmZMpM(this.f$0, this.f$1, r2, r3);
    }
}
