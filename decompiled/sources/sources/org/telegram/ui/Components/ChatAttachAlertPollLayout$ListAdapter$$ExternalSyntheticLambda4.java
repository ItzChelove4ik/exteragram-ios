package org.telegram.ui.Components;

import android.view.KeyEvent;
import android.widget.TextView;
import org.telegram.ui.Cells.PollEditTextCell;
import org.telegram.ui.Components.ChatAttachAlertPollLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPollLayout$ListAdapter$$ExternalSyntheticLambda4 implements TextView.OnEditorActionListener {
    public final /* synthetic */ ChatAttachAlertPollLayout.ListAdapter f$0;
    public final /* synthetic */ PollEditTextCell f$1;

    public /* synthetic */ ChatAttachAlertPollLayout$ListAdapter$$ExternalSyntheticLambda4(ChatAttachAlertPollLayout.ListAdapter r1, PollEditTextCell r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView r2, int r3, KeyEvent r4) {
        return ChatAttachAlertPollLayout.ListAdapter.$r8$lambda$aDaygsdqPNW3PQtQ3dM7mDFtIEs(this.f$0, this.f$1, r2, r3, r4);
    }
}
