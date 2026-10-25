package org.telegram.ui.Gifts;

import android.text.style.ClickableSpan;
import android.widget.TextView;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftSheet$$ExternalSyntheticLambda25 implements LinkSpanDrawable.LinksTextView.OnLinkPress {
    public final /* synthetic */ GiftSheet f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ TextView f$3;

    public /* synthetic */ GiftSheet$$ExternalSyntheticLambda25(GiftSheet r1, AlertDialog r2, Utilities.Callback r3, TextView r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView.OnLinkPress
    public final void run(ClickableSpan r4) {
        GiftSheet.m14905$r8$lambda$KyyFQavT26rquqXRPj0aUhv404(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
