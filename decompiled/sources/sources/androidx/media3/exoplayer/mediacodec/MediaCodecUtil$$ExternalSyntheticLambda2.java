package androidx.media3.exoplayer.mediacodec;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaCodecUtil$$ExternalSyntheticLambda2 implements MediaCodecUtil.ScoreProvider {
    public final /* synthetic */ Format f$0;

    public /* synthetic */ MediaCodecUtil$$ExternalSyntheticLambda2(Format r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.ScoreProvider
    public final int getScore(Object r1) {
        return MediaCodecUtil.$r8$lambda$Z8dAbbPXfdC1pwNXxAZccc0tgoo(this.f$0, (MediaCodecInfo) r1);
    }
}
