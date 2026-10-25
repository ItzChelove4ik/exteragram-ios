package org.telegram.ui.Adapters;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class MentionsAdapter$$ExternalSyntheticLambda1 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ MentionsAdapter f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ MentionsAdapter$$ExternalSyntheticLambda1(MentionsAdapter r1, boolean[] r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        MentionsAdapter.$r8$lambda$QA5nm5P_UjpFCA3LEpoZLZ9cWX0(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
