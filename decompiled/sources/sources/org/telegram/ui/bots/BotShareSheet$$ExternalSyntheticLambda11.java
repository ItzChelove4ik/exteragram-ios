package org.telegram.ui.bots;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotShareSheet$$ExternalSyntheticLambda11 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ BotShareSheet f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_messages_preparedInlineMessage f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ BaseFragment f$4;
    public final /* synthetic */ Utilities.Callback2 f$5;

    public /* synthetic */ BotShareSheet$$ExternalSyntheticLambda11(BotShareSheet r1, int r2, TLRPC.TL_messages_preparedInlineMessage r3, long r4, BaseFragment r6, Utilities.Callback2 r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r16, ArrayList r17, CharSequence r18, boolean r19, boolean r20, int r21, int r22, TopicsFragment r23) {
        return BotShareSheet.$r8$lambda$0fiLho5qdRFd06VbO76C_qE5xfk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r16, r17, r18, r19, r20, r21, r22, r23);
    }
}
