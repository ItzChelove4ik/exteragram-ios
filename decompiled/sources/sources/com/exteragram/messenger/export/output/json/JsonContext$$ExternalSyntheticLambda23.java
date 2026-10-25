package com.exteragram.messenger.export.output.json;

import com.exteragram.messenger.export.api.ApiWrap$Message;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class JsonContext$$ExternalSyntheticLambda23 implements Utilities.Callback {
    public final /* synthetic */ ApiWrap$Message f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ Utilities.CallbackReturn f$2;
    public final /* synthetic */ Utilities.Callback2 f$3;

    public /* synthetic */ JsonContext$$ExternalSyntheticLambda23(ApiWrap$Message r1, Utilities.Callback2 r2, Utilities.CallbackReturn r3, Utilities.Callback2 r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        JsonContext.$r8$lambda$hw2aHHYioc0DYTvaVxxcyJjnUJs(this.f$0, this.f$1, this.f$2, this.f$3, (String) r4);
    }
}
