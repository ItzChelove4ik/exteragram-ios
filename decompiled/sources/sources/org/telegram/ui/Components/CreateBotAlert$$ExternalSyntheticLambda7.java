package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class CreateBotAlert$$ExternalSyntheticLambda7 implements Utilities.Callback2 {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ String[] f$1;
    public final /* synthetic */ String[] f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TextInfoPrivacyCell f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;
    public final /* synthetic */ int[] f$6;

    public /* synthetic */ CreateBotAlert$$ExternalSyntheticLambda7(ButtonWithCounterView r1, String[] r2, String[] r3, String r4, TextInfoPrivacyCell r5, Theme.ResourcesProvider r6, int[] r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r10, Object r11) {
        CreateBotAlert.$r8$lambda$GmKpaDpTm5NvpHF_qD44TjE4Y10(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, (TLRPC.Bool) r10, (TLRPC.TL_error) r11);
    }
}
