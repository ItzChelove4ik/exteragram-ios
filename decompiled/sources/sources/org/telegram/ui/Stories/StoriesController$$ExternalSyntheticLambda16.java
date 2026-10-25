package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda16 implements RequestDelegate {
    public final /* synthetic */ StoriesController f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ Utilities.Callback f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda16(StoriesController r1, boolean r2, long r3, Utilities.Callback r5, Theme.ResourcesProvider r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        StoriesController.m19688$r8$lambda$G4dzkWYU4PWFP_XrVPQtMJNBPo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
