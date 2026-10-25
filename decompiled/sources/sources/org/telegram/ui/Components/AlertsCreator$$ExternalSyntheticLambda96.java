package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda96 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ MessageObject.GroupedMessages f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ MessageObject f$5;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda96(MessageObject.GroupedMessages r1, int r2, BaseFragment r3, int r4, int r5, MessageObject r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r9, int r10) {
        AlertsCreator.m8740$r8$lambda$V2mFkNc8sl91jWWCM4aHt1POOc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
