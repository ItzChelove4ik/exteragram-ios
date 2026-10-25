package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$StoriesList$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ StoriesController.StoriesList f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ StoriesController$StoriesList$$ExternalSyntheticLambda5(StoriesController.StoriesList r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StoriesController.StoriesList.$r8$lambda$XY5tuYON9BOj8BqKbdORqc0JrIg(this.f$0, this.f$1, r2, r3);
    }
}
