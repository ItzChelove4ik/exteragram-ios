package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public final /* synthetic */ class PasskeysActivity$$ExternalSyntheticLambda10 implements Utilities.Callback2 {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ BottomSheet f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ PasskeysActivity$$ExternalSyntheticLambda10(ButtonWithCounterView r1, Context r2, BottomSheet r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        PasskeysActivity.$r8$lambda$YbuzlWVIPDdYORbpgGojRqxLWg4(this.f$0, this.f$1, this.f$2, this.f$3, (TL_account.Passkey) r7, (String) r8);
    }
}
