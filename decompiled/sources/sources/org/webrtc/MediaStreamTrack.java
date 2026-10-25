package org.webrtc;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class MediaStreamTrack {
    public static final String AUDIO_TRACK_KIND = "audio";
    public static final String VIDEO_TRACK_KIND = "video";
    private long nativeTrack;

    public enum MediaType extends Enum<MediaType> {
        private static final /* synthetic */ MediaType[] $VALUES = null;
        public static final MediaType MEDIA_TYPE_AUDIO = null;
        public static final MediaType MEDIA_TYPE_VIDEO = null;
        private final int nativeIndex;

        private static /* synthetic */ MediaType[] $values() {
            return new MediaType[]{MEDIA_TYPE_AUDIO, MEDIA_TYPE_VIDEO};
        }

        static {
            MEDIA_TYPE_AUDIO = new MediaType("MEDIA_TYPE_AUDIO", 0, 0);
            MEDIA_TYPE_VIDEO = new MediaType("MEDIA_TYPE_VIDEO", 1, 1);
            $VALUES = $values();
        }

        MediaType(String r1, int r2, int r3) {
            this.nativeIndex = r3;
        }

        @CalledByNative("MediaType")
        public static MediaType fromNativeIndex(int r5) {
            MediaType[] r0 = values();
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L8;
            MediaType r3 = r0[r2];
            if (r3.getNative() == r5) goto L6;
            r2 = r2 + 1;
            goto L3
        L6:
            return r3;
        L8:
            CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown native media type: ", r5);
            return null;
        }

        public static MediaType valueOf(String r1) {
            return (MediaType) Enum.valueOf(MediaType.class, r1);
        }

        public static MediaType[] values() {
            return (MediaType[]) $VALUES.clone();
        }

        @CalledByNative("MediaType")
        public int getNative() {
            return this.nativeIndex;
        }
    }

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State ENDED = null;
        public static final State LIVE = null;

        private static /* synthetic */ State[] $values() {
            return new State[]{LIVE, ENDED};
        }

        static {
            LIVE = new State("LIVE", 0);
            ENDED = new State("ENDED", 1);
            $VALUES = $values();
        }

        State(String r1, int r2) {
        }

        @CalledByNative("State")
        public static State fromNativeIndex(int r1) {
            return values()[r1];
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public MediaStreamTrack(long r3) {
        if (r3 == 0) goto L6;
        this.nativeTrack = r3;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("nativeTrack may not be null");
        throw null;
    }

    private void checkMediaStreamTrackExists() {
        if (this.nativeTrack == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("MediaStreamTrack has been disposed.");
    }

    public static MediaStreamTrack createMediaStreamTrack(long r3) {
        if (r3 != 0) goto L5;
        return null;
    L5:
        String r0 = nativeGetKind(r3);
        if (r0.equals(AUDIO_TRACK_KIND) == false) goto L10;
        return new AudioTrack(r3);
    L10:
        if (r0.equals(VIDEO_TRACK_KIND) == true) goto L12;
        return null;
    L12:
        return new VideoTrack(r3);
    }

    private static native boolean nativeGetEnabled(long r0);

    private static native String nativeGetId(long r0);

    private static native String nativeGetKind(long r0);

    private static native State nativeGetState(long r0);

    private static native boolean nativeSetEnabled(long r0, boolean r2);

    public void dispose() {
        checkMediaStreamTrackExists();
        JniCommon.nativeReleaseRef(this.nativeTrack);
        this.nativeTrack = 0;
    }

    public boolean enabled() {
        checkMediaStreamTrackExists();
        return nativeGetEnabled(this.nativeTrack);
    }

    public long getNativeMediaStreamTrack() {
        checkMediaStreamTrackExists();
        return this.nativeTrack;
    }

    public String id() {
        checkMediaStreamTrackExists();
        return nativeGetId(this.nativeTrack);
    }

    public String kind() {
        checkMediaStreamTrackExists();
        return nativeGetKind(this.nativeTrack);
    }

    public boolean setEnabled(boolean r3) {
        checkMediaStreamTrackExists();
        return nativeSetEnabled(this.nativeTrack, r3);
    }

    public State state() {
        checkMediaStreamTrackExists();
        return nativeGetState(this.nativeTrack);
    }
}
