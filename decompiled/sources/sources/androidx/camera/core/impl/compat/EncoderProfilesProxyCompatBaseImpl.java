package androidx.camera.core.impl.compat;

import android.media.CamcorderProfile;
import androidx.camera.core.impl.EncoderProfilesProxy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
abstract class EncoderProfilesProxyCompatBaseImpl {
    public static EncoderProfilesProxy from(CamcorderProfile r3) {
        return EncoderProfilesProxy.ImmutableEncoderProfilesProxy.create(r3.duration, r3.fileFormat, toAudioProfiles(r3), toVideoProfiles(r3));
    }

    private static List<EncoderProfilesProxy.AudioProfileProxy> toAudioProfiles(CamcorderProfile r7) {
        ArrayList r0 = new ArrayList();
        int r1 = r7.audioCodec;
        r0.add(EncoderProfilesProxy.AudioProfileProxy.create(r1, EncoderProfilesProxy.getAudioCodecMimeType(r1), r7.audioBitRate, r7.audioSampleRate, r7.audioChannels, EncoderProfilesProxy.getRequiredAudioProfile(r7.audioCodec)));
        return r0;
    }

    private static List<EncoderProfilesProxy.VideoProfileProxy> toVideoProfiles(CamcorderProfile r11) {
        ArrayList r0 = new ArrayList();
        int r1 = r11.videoCodec;
        r0.add(EncoderProfilesProxy.VideoProfileProxy.create(r1, EncoderProfilesProxy.getVideoCodecMimeType(r1), r11.videoBitRate, r11.videoFrameRate, r11.videoFrameWidth, r11.videoFrameHeight, -1, 8, 0, 0));
        return r0;
    }
}
