package com.exteragram.messenger.export.output.json;

import com.exteragram.messenger.export.api.ApiWrap$Message;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class JsonContext$$ExternalSyntheticLambda24 implements Utilities.Callback {
    public final /* synthetic */ ApiWrap$Message f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ JsonContext$$ExternalSyntheticLambda24(ApiWrap$Message r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        JsonContext.$r8$lambda$V1GPUI3Ji8IitWs016dE88zBqRg(this.f$0, this.f$1, (String) r2);
    }
}
