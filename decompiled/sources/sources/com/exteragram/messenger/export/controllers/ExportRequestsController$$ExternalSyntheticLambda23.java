package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda23 implements Utilities.Callback2 {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ TLRPC.InputPeer f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ int f$5;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda23(ExportRequestsController r1, TLRPC.InputPeer r2, int r3, int r4, int r5, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r9, Object r10) {
        ExportRequestsController.$r8$lambda$u5ZlOsOMxKIfjKUcocdojvJTbR4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TLObject) r9, (TLRPC.TL_error) r10);
    }
}
