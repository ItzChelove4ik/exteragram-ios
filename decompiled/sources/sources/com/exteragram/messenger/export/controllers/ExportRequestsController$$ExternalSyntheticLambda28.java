package com.exteragram.messenger.export.controllers;

import com.exteragram.messenger.export.api.ApiWrap$SessionsList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda28 implements Utilities.Callback2 {
    public final /* synthetic */ ApiWrap$SessionsList f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda28(ApiWrap$SessionsList r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        ExportRequestsController.$r8$lambda$zUhnSPX6kGtTskTPxh0MMvePxT4(this.f$0, this.f$1, (TLObject) r2, (TLRPC.TL_error) r3);
    }
}
