package org.telegram.tgnet.tl;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes.dex */
public final /* synthetic */ class TL_iv$pageBlockRelatedArticles$$ExternalSyntheticLambda0 implements Vector.TLDeserializer {
    public /* synthetic */ TL_iv$pageBlockRelatedArticles$$ExternalSyntheticLambda0() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TL_iv.pageRelatedArticle.TLdeserialize(r1, r2, r3);
    }
}
