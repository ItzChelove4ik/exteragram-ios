package org.telegram.ui.Components;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda200 implements TextView.OnEditorActionListener {
    public final /* synthetic */ EditTextBoldCursor f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MessagesStorage.StringCallback f$2;
    public final /* synthetic */ AlertDialog[] f$3;
    public final /* synthetic */ View f$4;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda200(EditTextBoldCursor r1, int r2, MessagesStorage.StringCallback r3, AlertDialog[] r4, View r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView r9, int r10, KeyEvent r11) {
        return AlertsCreator.$r8$lambda$AwoAOrUvysrgnd0Ax8bTi1NNwP0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10, r11);
    }
}
