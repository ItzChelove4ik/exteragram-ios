package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class MessagePrivateSeenView$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;
    public final /* synthetic */ ButtonWithCounterView f$2;
    public final /* synthetic */ BottomSheet f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ MessagePrivateSeenView$$ExternalSyntheticLambda3(Context r1, Theme.ResourcesProvider r2, ButtonWithCounterView r3, BottomSheet r4, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        MessagePrivateSeenView.m11297$r8$lambda$jHYwNYYn0fwy3WW39eS9NTIabg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
