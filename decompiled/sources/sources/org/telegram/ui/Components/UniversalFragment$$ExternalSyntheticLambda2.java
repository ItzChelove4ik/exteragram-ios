package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class UniversalFragment$$ExternalSyntheticLambda2 implements Utilities.Callback5Return {
    public final /* synthetic */ UniversalFragment f$0;

    public /* synthetic */ UniversalFragment$$ExternalSyntheticLambda2(UniversalFragment r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback5Return
    public final Object run(Object r1, Object r2, Object r3, Object r4, Object r5) {
        return Boolean.valueOf(this.f$0.onLongClick((UItem) r1, (View) r2, ((Integer) r3).intValue(), ((Float) r4).floatValue(), ((Float) r5).floatValue()));
    }
}
