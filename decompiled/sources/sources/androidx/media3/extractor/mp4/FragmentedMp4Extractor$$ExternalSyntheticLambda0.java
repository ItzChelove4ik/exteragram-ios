package androidx.media3.extractor.mp4;

import com.google.common.base.Function;

/* loaded from: classes4.dex */
public final /* synthetic */ class FragmentedMp4Extractor$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ FragmentedMp4Extractor f$0;

    public /* synthetic */ FragmentedMp4Extractor$$ExternalSyntheticLambda0(FragmentedMp4Extractor r1) {
        this.f$0 = r1;
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object r1) {
        return this.f$0.modifyTrack((Track) r1);
    }
}
