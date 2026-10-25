package com.exteragram.messenger.export.controllers;

import com.exteragram.messenger.export.api.ApiWrap$DownloadProgress;
import com.exteragram.messenger.export.controllers.ExportController;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportController$$ExternalSyntheticLambda26 implements Utilities.Callback {
    public final /* synthetic */ ExportController f$0;
    public final /* synthetic */ ApiWrap$DownloadProgress f$1;

    public /* synthetic */ ExportController$$ExternalSyntheticLambda26(ExportController r1, ApiWrap$DownloadProgress r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ExportController.m1311$r8$lambda$4pgjlL5vAJ3NQrAMql0spwlQxU(this.f$0, this.f$1, (ExportController.ProcessingState) r2);
    }
}
