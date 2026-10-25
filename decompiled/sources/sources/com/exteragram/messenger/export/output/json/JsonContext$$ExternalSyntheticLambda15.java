package com.exteragram.messenger.export.output.json;

import com.exteragram.messenger.export.api.ApiWrap$Image;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class JsonContext$$ExternalSyntheticLambda15 implements Utilities.Callback {
    public final /* synthetic */ Utilities.Callback3 f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ JsonContext$$ExternalSyntheticLambda15(Utilities.Callback3 r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        JsonContext.m1350$r8$lambda$0JEATGwgrDcrUlHLnU4tlxdjgY(this.f$0, this.f$1, (ApiWrap$Image) r2);
    }
}
