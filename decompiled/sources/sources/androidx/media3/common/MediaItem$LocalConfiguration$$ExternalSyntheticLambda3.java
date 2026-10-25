package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.MediaItem;
import com.google.common.base.Function;

/* loaded from: classes3.dex */
public final /* synthetic */ class MediaItem$LocalConfiguration$$ExternalSyntheticLambda3 implements Function {
    public /* synthetic */ MediaItem$LocalConfiguration$$ExternalSyntheticLambda3() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object r1) {
        return MediaItem.SubtitleConfiguration.fromBundle((Bundle) r1);
    }
}
