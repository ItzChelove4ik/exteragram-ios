package org.telegram.ui.web;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class AddressBarList$$ExternalSyntheticLambda1 implements Utilities.Callback2 {
    public final /* synthetic */ AddressBarList f$0;

    public /* synthetic */ AddressBarList$$ExternalSyntheticLambda1(AddressBarList r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
