package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class CreateBotAlert$$ExternalSyntheticLambda8 implements Utilities.Callback2 {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Utilities.Callback f$4;
    public final /* synthetic */ BottomSheet f$5;
    public final /* synthetic */ Theme.ResourcesProvider f$6;
    public final /* synthetic */ Context f$7;
    public final /* synthetic */ TLRPC.User f$8;

    public /* synthetic */ CreateBotAlert$$ExternalSyntheticLambda8(int[] r1, ButtonWithCounterView r2, boolean[] r3, int r4, Utilities.Callback r5, BottomSheet r6, Theme.ResourcesProvider r7, Context r8, TLRPC.User r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r12, Object r13) {
        CreateBotAlert.$r8$lambda$beTIbpFDg1oTOp6ADA6Nl3xYAEw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, (TLRPC.User) r12, (TLRPC.TL_error) r13);
    }
}
