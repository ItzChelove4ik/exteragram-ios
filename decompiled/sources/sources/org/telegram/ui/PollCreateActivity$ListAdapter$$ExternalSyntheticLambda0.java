package org.telegram.ui;

import android.view.View;

/* loaded from: classes6.dex */
public final /* synthetic */ class PollCreateActivity$ListAdapter$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ PollCreateActivity f$0;

    public /* synthetic */ PollCreateActivity$ListAdapter$$ExternalSyntheticLambda0(PollCreateActivity r1) {
        this.f$0 = r1;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r1) {
        this.f$0.deleteItem(r1);
    }
}
