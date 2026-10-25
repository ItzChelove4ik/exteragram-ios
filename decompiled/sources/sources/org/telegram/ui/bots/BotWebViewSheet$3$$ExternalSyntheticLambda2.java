package org.telegram.ui.bots;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.OverlayActionBarLayoutDialog;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;
import org.telegram.ui.bots.BotWebViewSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotWebViewSheet$3$$ExternalSyntheticLambda2 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ BotWebViewSheet.AnonymousClass3 f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ OverlayActionBarLayoutDialog f$3;

    public /* synthetic */ BotWebViewSheet$3$$ExternalSyntheticLambda2(BotWebViewSheet.AnonymousClass3 r1, TLRPC.User r2, String r3, OverlayActionBarLayoutDialog r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r13, ArrayList r14, CharSequence r15, boolean r16, boolean r17, int r18, int r19, TopicsFragment r20) {
        return BotWebViewSheet.AnonymousClass3.$r8$lambda$ayLKtF8em3lyGYQfK3PGCevX_ug(this.f$0, this.f$1, this.f$2, this.f$3, r13, r14, r15, r16, r17, r18, r19, r20);
    }
}
