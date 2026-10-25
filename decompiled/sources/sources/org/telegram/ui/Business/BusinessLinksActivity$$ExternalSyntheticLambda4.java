package org.telegram.ui.Business;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Components.EditTextBoldCursor;

/* loaded from: classes6.dex */
public final /* synthetic */ class BusinessLinksActivity$$ExternalSyntheticLambda4 implements TextView.OnEditorActionListener {
    public final /* synthetic */ EditTextBoldCursor f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TL_account.TL_businessChatLink f$2;
    public final /* synthetic */ AlertDialog[] f$3;
    public final /* synthetic */ View f$4;

    public /* synthetic */ BusinessLinksActivity$$ExternalSyntheticLambda4(EditTextBoldCursor r1, int r2, TL_account.TL_businessChatLink r3, AlertDialog[] r4, View r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView r9, int r10, KeyEvent r11) {
        return BusinessLinksActivity.$r8$lambda$DtBQAB1IVKEeN5sJZ6_I4zgsmCc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10, r11);
    }
}
