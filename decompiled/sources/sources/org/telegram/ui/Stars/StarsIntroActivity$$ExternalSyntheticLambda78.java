package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$$ExternalSyntheticLambda78 implements RequestDelegate {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ BottomSheet[] f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TLRPC.TL_messages_checkChatInvite f$4;

    public /* synthetic */ StarsIntroActivity$$ExternalSyntheticLambda78(ButtonWithCounterView r1, BottomSheet[] r2, Theme.ResourcesProvider r3, int r4, TLRPC.TL_messages_checkChatInvite r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        StarsIntroActivity.$r8$lambda$KN1YxrmfDGIOr12ZmoytgqnXE7s(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
