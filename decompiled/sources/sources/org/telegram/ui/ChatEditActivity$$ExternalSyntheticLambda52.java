package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditActivity$$ExternalSyntheticLambda52 implements Utilities.Callback {
    public final /* synthetic */ ChatEditActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ AlertDialog f$4;

    public /* synthetic */ ChatEditActivity$$ExternalSyntheticLambda52(ChatEditActivity r1, boolean r2, boolean[] r3, long r4, AlertDialog r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        ChatEditActivity.$r8$lambda$wW2CR3PVQHIygxvKLuvcugrwb7I(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TL_stories.TL_premium_boostsStatus) r8);
    }
}
