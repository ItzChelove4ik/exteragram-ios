package androidx.media3.exoplayer.mediacodec;

import java.util.List;

/* loaded from: classes4.dex */
public interface MediaCodecSelector {
    public static final MediaCodecSelector DEFAULT = null;
    public static final MediaCodecSelector PREFER_SOFTWARE = null;

    /* renamed from: $r8$lambda$tAvnMilKxJP4aNHjyi5Lyfr-8F4, reason: not valid java name */
    static /* synthetic */ List m833$r8$lambda$tAvnMilKxJP4aNHjyi5Lyfr8F4(String r1, boolean r2, boolean r3) {
        return MediaCodecUtil.getDecoderInfosSortedBySoftwareOnly(DEFAULT.getDecoderInfos(r1, r2, r3));
    }

    static {
        DEFAULT = new MediaCodecSelector$$ExternalSyntheticLambda0();
        PREFER_SOFTWARE = new MediaCodecSelector$$ExternalSyntheticLambda1();
    }

    List<MediaCodecInfo> getDecoderInfos(String r1, boolean r2, boolean r3);
}
