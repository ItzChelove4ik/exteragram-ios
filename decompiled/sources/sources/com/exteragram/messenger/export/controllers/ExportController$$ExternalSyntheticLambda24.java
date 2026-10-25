package com.exteragram.messenger.export.controllers;

import com.exteragram.messenger.export.api.ApiWrap$DownloadProgress;
import com.exteragram.messenger.export.controllers.ExportController;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportController$$ExternalSyntheticLambda24 implements Utilities.Callback {
    public final /* synthetic */ ExportController f$0;
    public final /* synthetic */ ApiWrap$DownloadProgress f$1;

    public /* synthetic */ ExportController$$ExternalSyntheticLambda24(ExportController r1, ApiWrap$DownloadProgress r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ExportController.$r8$lambda$IaUuSoZUK_XMpLghjgYflpuOeVA(this.f$0, this.f$1, (ExportController.ProcessingState) r2);
    }
}
