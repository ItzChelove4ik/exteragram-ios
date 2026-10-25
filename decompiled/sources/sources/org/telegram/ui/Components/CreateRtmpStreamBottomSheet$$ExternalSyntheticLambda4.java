package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ CreateRtmpStreamBottomSheet f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;

    public /* synthetic */ CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda4(CreateRtmpStreamBottomSheet r1, ButtonWithCounterView r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        CreateRtmpStreamBottomSheet.m10061$r8$lambda$GgUqod3ZRTeuzXkEj8PWSewxhM(this.f$0, this.f$1, r2, r3);
    }
}
