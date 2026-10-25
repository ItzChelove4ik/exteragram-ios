package org.telegram.ui.iv;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertRichLayout$$ExternalSyntheticLambda4 implements View.OnClickListener {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ String[] f$3;
    public final /* synthetic */ BottomSheet f$4;

    public /* synthetic */ ChatAttachAlertRichLayout$$ExternalSyntheticLambda4(ButtonWithCounterView r1, boolean[] r2, Utilities.Callback r3, String[] r4, BottomSheet r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r7) {
        ChatAttachAlertRichLayout.m21580$r8$lambda$Zjled5bofGN3SKK241Q7f6xf4U(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
