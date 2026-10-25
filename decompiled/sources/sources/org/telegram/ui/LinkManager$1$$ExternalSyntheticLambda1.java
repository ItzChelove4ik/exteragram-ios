package org.telegram.ui;

import java.util.HashSet;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LinkManager;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$1$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ LinkManager.AnonymousClass1 f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ HashSet f$2;

    public /* synthetic */ LinkManager$1$$ExternalSyntheticLambda1(LinkManager.AnonymousClass1 r1, boolean r2, HashSet r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        LinkManager.AnonymousClass1.m15691$r8$lambda$OD_x5XwfvoUOkRtM9AnSeOQUYE(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
