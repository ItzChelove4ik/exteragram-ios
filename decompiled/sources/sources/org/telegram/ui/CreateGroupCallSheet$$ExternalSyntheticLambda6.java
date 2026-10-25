package org.telegram.ui;

import java.util.HashSet;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public final /* synthetic */ class CreateGroupCallSheet$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ CreateGroupCallSheet f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ ButtonWithCounterView f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ HashSet f$4;
    public final /* synthetic */ TLRPC.TL_error f$5;

    public /* synthetic */ CreateGroupCallSheet$$ExternalSyntheticLambda6(CreateGroupCallSheet r1, TLObject r2, ButtonWithCounterView r3, boolean r4, HashSet r5, TLRPC.TL_error r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CreateGroupCallSheet.$r8$lambda$k9ZDZxAEKQ6AZA4BrETRtbw9iRM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
