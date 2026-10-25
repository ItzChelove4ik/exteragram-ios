package org.telegram.ui;

import android.view.KeyEvent;
import android.widget.TextView;
import org.telegram.ui.Cells.PollEditTextCell;
import org.telegram.ui.PollCreateActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PollCreateActivity$ListAdapter$$ExternalSyntheticLambda1 implements TextView.OnEditorActionListener {
    public final /* synthetic */ PollCreateActivity.ListAdapter f$0;
    public final /* synthetic */ PollEditTextCell f$1;

    public /* synthetic */ PollCreateActivity$ListAdapter$$ExternalSyntheticLambda1(PollCreateActivity.ListAdapter r1, PollEditTextCell r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView r2, int r3, KeyEvent r4) {
        return PollCreateActivity.ListAdapter.$r8$lambda$yKPkJ8UNwtQGOLndgh2Wqe3bi1M(this.f$0, this.f$1, r2, r3, r4);
    }
}
