package androidx.media3.extractor.text;

import android.os.Bundle;
import androidx.media3.common.text.Cue;
import com.google.common.base.Function;

/* loaded from: classes4.dex */
public final /* synthetic */ class CueDecoder$$ExternalSyntheticLambda0 implements Function {
    public /* synthetic */ CueDecoder$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object r1) {
        return Cue.fromBundle((Bundle) r1);
    }
}
