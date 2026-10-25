package org.telegram.ui.Components;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda255 implements TextView.OnEditorActionListener {
    public final /* synthetic */ EditTextBoldCursor f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ AlertDialog[] f$2;
    public final /* synthetic */ View f$3;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda255(EditTextBoldCursor r1, Utilities.Callback r2, AlertDialog[] r3, View r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView r8, int r9, KeyEvent r10) {
        return AlertsCreator.$r8$lambda$ISGHVganXhbap0xqfzUQvvZf8iE(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9, r10);
    }
}
