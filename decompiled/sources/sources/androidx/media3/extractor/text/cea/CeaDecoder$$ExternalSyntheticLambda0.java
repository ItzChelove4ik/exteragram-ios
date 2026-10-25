package androidx.media3.extractor.text.cea;

import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.extractor.text.cea.CeaDecoder;

/* loaded from: classes4.dex */
public final /* synthetic */ class CeaDecoder$$ExternalSyntheticLambda0 implements DecoderOutputBuffer.Owner {
    public final /* synthetic */ CeaDecoder f$0;

    public /* synthetic */ CeaDecoder$$ExternalSyntheticLambda0(CeaDecoder r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.decoder.DecoderOutputBuffer.Owner
    public final void releaseOutputBuffer(DecoderOutputBuffer r1) {
        this.f$0.releaseOutputBuffer((CeaDecoder.CeaOutputBuffer) r1);
    }
}
