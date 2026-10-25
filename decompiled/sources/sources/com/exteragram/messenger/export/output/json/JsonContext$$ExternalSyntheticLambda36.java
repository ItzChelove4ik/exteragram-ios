package com.exteragram.messenger.export.output.json;

import com.exteragram.messenger.export.api.DataTypesUtils;
import java.util.function.Function;

/* loaded from: classes4.dex */
public final /* synthetic */ class JsonContext$$ExternalSyntheticLambda36 implements Function {
    public /* synthetic */ JsonContext$$ExternalSyntheticLambda36() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object r1) {
        return DataTypesUtils.NumberToString(((Long) r1).longValue());
    }
}
