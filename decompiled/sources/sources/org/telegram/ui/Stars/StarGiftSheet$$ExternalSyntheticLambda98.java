package org.telegram.ui.Stars;

import android.view.View;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda98 implements Utilities.Callback3 {
    public final /* synthetic */ StarGiftSheet f$0;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda98(StarGiftSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback3
    public final void run(Object r1, Object r2, Object r3) {
        this.f$0.showHint((CharSequence) r1, (View) r2, ((Boolean) r3).booleanValue());
    }
}
