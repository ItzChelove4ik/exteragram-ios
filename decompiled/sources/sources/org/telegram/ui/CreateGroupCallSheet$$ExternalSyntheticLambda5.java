package org.telegram.ui;

import java.util.HashSet;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public final /* synthetic */ class CreateGroupCallSheet$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ CreateGroupCallSheet f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ HashSet f$3;

    public /* synthetic */ CreateGroupCallSheet$$ExternalSyntheticLambda5(CreateGroupCallSheet r1, ButtonWithCounterView r2, boolean r3, HashSet r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        CreateGroupCallSheet.$r8$lambda$pf2ILFXW9gL8WVhAse1BGaGfmL8(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
