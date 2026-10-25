package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class MessagePrivateSeenView$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ TLRPC.TL_error f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;
    public final /* synthetic */ ButtonWithCounterView f$3;
    public final /* synthetic */ BottomSheet f$4;
    public final /* synthetic */ Runnable f$5;

    public /* synthetic */ MessagePrivateSeenView$$ExternalSyntheticLambda4(TLRPC.TL_error r1, Context r2, Theme.ResourcesProvider r3, ButtonWithCounterView r4, BottomSheet r5, Runnable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagePrivateSeenView.m11298$r8$lambda$mFKiuqa7A9qEAjUuAbSEUUzIag(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
