package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import com.google.common.base.Function;

/* loaded from: classes4.dex */
public final /* synthetic */ class CueEncoder$$ExternalSyntheticLambda0 implements Function {
    public /* synthetic */ CueEncoder$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object r1) {
        return ((Cue) r1).toSerializableBundle();
    }
}
