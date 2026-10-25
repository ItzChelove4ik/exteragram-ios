package org.telegram.ui.bots;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotVerifySheet$$ExternalSyntheticLambda3 implements View.OnClickListener {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ TL_bots.botVerifierSettings f$1;
    public final /* synthetic */ EditTextBoldCursor f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ OutlineTextContainerView f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ long f$7;
    public final /* synthetic */ BottomSheet f$8;
    public final /* synthetic */ Utilities.Callback f$9;

    public /* synthetic */ BotVerifySheet$$ExternalSyntheticLambda3(ButtonWithCounterView r1, TL_bots.botVerifierSettings r2, EditTextBoldCursor r3, int r4, OutlineTextContainerView r5, int r6, long r7, long r9, BottomSheet r11, Utilities.Callback r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r9;
        this.f$8 = r11;
        this.f$9 = r12;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r14) {
        BotVerifySheet.$r8$lambda$5aKZVRDWxSyiakwwXmxo6orSDOA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, r14);
    }
}
