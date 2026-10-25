package org.telegram.ui.web;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UItem;

/* loaded from: classes6.dex */
public final /* synthetic */ class AddressBarList$$ExternalSyntheticLambda2 implements Utilities.Callback5 {
    public final /* synthetic */ AddressBarList f$0;

    public /* synthetic */ AddressBarList$$ExternalSyntheticLambda2(AddressBarList r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback5
    public final void run(Object r1, Object r2, Object r3, Object r4, Object r5) {
        this.f$0.itemClick((UItem) r1, (View) r2, ((Integer) r3).intValue(), ((Float) r4).floatValue(), ((Float) r5).floatValue());
    }
}
