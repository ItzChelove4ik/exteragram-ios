package androidx.media3.extractor.text;

import androidx.media3.common.util.Consumer;
import com.google.common.collect.ImmutableList;

/* loaded from: classes4.dex */
public final /* synthetic */ class SubtitleParser$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ ImmutableList.Builder f$0;

    public /* synthetic */ SubtitleParser$$ExternalSyntheticLambda0(ImmutableList.Builder r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r1) {
        this.f$0.add((CuesWithTiming) r1);
    }
}
