package com.exteragram.messenger.export.controllers;

import com.exteragram.messenger.export.api.ApiWrap$FileProgress;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda32 implements Utilities.CallbackReturn {
    public final /* synthetic */ ExportRequestsController f$0;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda32(ExportRequestsController r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.CallbackReturn
    public final Object run(Object r1) {
        return Boolean.valueOf(this.f$0.loadUserpicProgress((ApiWrap$FileProgress) r1));
    }
}
