package androidx.camera.core.impl;

import android.util.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.telegram.messenger.MediaController;

/* loaded from: classes3.dex */
public interface EncoderProfilesProxy {

    public static abstract class AudioProfileProxy {
        public AudioProfileProxy() {
        }

        public static AudioProfileProxy create(int r7, String r8, int r9, int r10, int r11, int r12) {
            return new AutoValue_EncoderProfilesProxy_AudioProfileProxy(r7, r8, r9, r10, r11, r12);
        }

        public abstract int getBitrate();

        public abstract int getChannels();

        public abstract int getCodec();

        public abstract String getMediaType();

        public abstract int getProfile();

        public abstract int getSampleRate();
    }

    public static abstract class ImmutableEncoderProfilesProxy implements EncoderProfilesProxy {
        public ImmutableEncoderProfilesProxy() {
        }

        public static ImmutableEncoderProfilesProxy create(int r2, int r3, List<AudioProfileProxy> r4, List<VideoProfileProxy> r5) {
            return new AutoValue_EncoderProfilesProxy_ImmutableEncoderProfilesProxy(r2, r3, Collections.unmodifiableList(new ArrayList(r4)), Collections.unmodifiableList(new ArrayList(r5)));
        }
    }

    public static abstract class VideoProfileProxy {
        public VideoProfileProxy() {
        }

        public static VideoProfileProxy create(int r11, String r12, int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20) {
            return new AutoValue_EncoderProfilesProxy_VideoProfileProxy(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
        }

        public abstract int getBitDepth();

        public abstract int getBitrate();

        public abstract int getChromaSubsampling();

        public abstract int getCodec();

        public abstract int getFrameRate();

        public abstract int getHdrFormat();

        public abstract int getHeight();

        public abstract String getMediaType();

        public abstract int getProfile();

        public Size getResolution() {
            return new Size(getWidth(), getHeight());
        }

        public abstract int getWidth();
    }

    static String getAudioCodecMimeType(int r0) {
        switch(r0) {
            case 1: goto L13;
            case 2: goto L11;
            case 3: goto L9;
            case 4: goto L9;
            case 5: goto L9;
            case 6: goto L7;
            case 7: goto L5;
            default: goto L3;
        };
    L3:
        return "audio/none";
    L5:
        return "audio/opus";
    L7:
        return "audio/vorbis";
    L9:
        return MediaController.AUDIO_MIME_TYPE;
    L11:
        return "audio/amr-wb";
    L13:
        return "audio/3gpp";
    }

    static int getRequiredAudioProfile(int r2) {
        if (r2 != 3) goto L5;
        return 2;
    L5:
        if (r2 == 4) goto L11;
        if (r2 == 5) goto L9;
        return -1;
    L9:
        return 39;
    L11:
        return 5;
    }

    static String getVideoCodecMimeType(int r0) {
        switch(r0) {
            case 1: goto L19;
            case 2: goto L17;
            case 3: goto L15;
            case 4: goto L13;
            case 5: goto L11;
            case 6: goto L9;
            case 7: goto L7;
            case 8: goto L5;
            default: goto L3;
        };
    L3:
        return "video/none";
    L5:
        return "video/av01";
    L7:
        return "video/dolby-vision";
    L9:
        return "video/x-vnd.on2.vp9";
    L11:
        return "video/hevc";
    L13:
        return "video/x-vnd.on2.vp8";
    L15:
        return "video/mp4v-es";
    L17:
        return MediaController.VIDEO_MIME_TYPE;
    L19:
        return "video/3gpp";
    }

    List<AudioProfileProxy> getAudioProfiles();

    int getDefaultDurationSeconds();

    int getRecommendedFileFormat();

    List<VideoProfileProxy> getVideoProfiles();
}
