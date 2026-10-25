package androidx.media3.common;

import android.os.Bundle;
import com.google.common.base.Function;

/* loaded from: classes3.dex */
public final /* synthetic */ class MediaItem$LocalConfiguration$$ExternalSyntheticLambda2 implements Function {
    public /* synthetic */ MediaItem$LocalConfiguration$$ExternalSyntheticLambda2() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object r1) {
        return StreamKey.fromBundle((Bundle) r1);
    }
}
