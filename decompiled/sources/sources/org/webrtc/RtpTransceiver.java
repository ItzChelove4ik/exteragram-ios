package org.webrtc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.webrtc.MediaStreamTrack;
import org.webrtc.RtpCapabilities;
import org.webrtc.RtpParameters;

/* loaded from: classes6.dex */
public class RtpTransceiver {
    private RtpReceiver cachedReceiver;
    private RtpSender cachedSender;
    private long nativeRtpTransceiver;

    public enum RtpTransceiverDirection extends Enum<RtpTransceiverDirection> {
        private static final /* synthetic */ RtpTransceiverDirection[] $VALUES = null;
        public static final RtpTransceiverDirection INACTIVE = null;
        public static final RtpTransceiverDirection RECV_ONLY = null;
        public static final RtpTransceiverDirection SEND_ONLY = null;
        public static final RtpTransceiverDirection SEND_RECV = null;
        public static final RtpTransceiverDirection STOPPED = null;
        private final int nativeIndex;

        private static /* synthetic */ RtpTransceiverDirection[] $values() {
            return new RtpTransceiverDirection[]{SEND_RECV, SEND_ONLY, RECV_ONLY, INACTIVE, STOPPED};
        }

        static {
            SEND_RECV = new RtpTransceiverDirection("SEND_RECV", 0, 0);
            SEND_ONLY = new RtpTransceiverDirection("SEND_ONLY", 1, 1);
            RECV_ONLY = new RtpTransceiverDirection("RECV_ONLY", 2, 2);
            INACTIVE = new RtpTransceiverDirection("INACTIVE", 3, 3);
            STOPPED = new RtpTransceiverDirection("STOPPED", 4, 4);
            $VALUES = $values();
        }

        RtpTransceiverDirection(String r1, int r2, int r3) {
            this.nativeIndex = r3;
        }

        @CalledByNative("RtpTransceiverDirection")
        public static RtpTransceiverDirection fromNativeIndex(int r5) {
            RtpTransceiverDirection[] r0 = values();
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L8;
            RtpTransceiverDirection r3 = r0[r2];
            if (r3.getNativeIndex() == r5) goto L6;
            r2 = r2 + 1;
            goto L3
        L6:
            return r3;
        L8:
            CharCodeKt$$ExternalSyntheticBUOutline0.m("Uknown native RtpTransceiverDirection type", r5);
            return null;
        }

        public static RtpTransceiverDirection valueOf(String r1) {
            return (RtpTransceiverDirection) Enum.valueOf(RtpTransceiverDirection.class, r1);
        }

        public static RtpTransceiverDirection[] values() {
            return (RtpTransceiverDirection[]) $VALUES.clone();
        }

        @CalledByNative("RtpTransceiverDirection")
        public int getNativeIndex() {
            return this.nativeIndex;
        }
    }

    public static final class RtpTransceiverInit {
        private final RtpTransceiverDirection direction;
        private final List<RtpParameters.Encoding> sendEncodings;
        private final List<String> streamIds;

        public RtpTransceiverInit() {
            this(RtpTransceiverDirection.SEND_RECV);
        }

        public RtpTransceiverInit(RtpTransceiverDirection r2) {
            List r0 = Collections.EMPTY_LIST;
            this(r2, r0, r0);
        }

        public RtpTransceiverInit(RtpTransceiverDirection r2, List<String> r3) {
            this(r2, r3, Collections.EMPTY_LIST);
        }

        public RtpTransceiverInit(RtpTransceiverDirection r1, List<String> r2, List<RtpParameters.Encoding> r3) {
            this.direction = r1;
            this.streamIds = new ArrayList(r2);
            this.sendEncodings = new ArrayList(r3);
        }

        @CalledByNative("RtpTransceiverInit")
        public int getDirectionNativeIndex() {
            return this.direction.getNativeIndex();
        }

        @CalledByNative("RtpTransceiverInit")
        public List<RtpParameters.Encoding> getSendEncodings() {
            return new ArrayList(this.sendEncodings);
        }

        @CalledByNative("RtpTransceiverInit")
        public List<String> getStreamIds() {
            return new ArrayList(this.streamIds);
        }
    }

    @CalledByNative
    public RtpTransceiver(long r2) {
        this.nativeRtpTransceiver = r2;
        this.cachedSender = nativeGetSender(r2);
        this.cachedReceiver = nativeGetReceiver(r2);
    }

    private void checkRtpTransceiverExists() {
        if (this.nativeRtpTransceiver == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("RtpTransceiver has been disposed.");
    }

    private static native RtpTransceiverDirection nativeCurrentDirection(long r0);

    private static native RtpTransceiverDirection nativeDirection(long r0);

    private static native MediaStreamTrack.MediaType nativeGetMediaType(long r0);

    private static native String nativeGetMid(long r0);

    private static native RtpReceiver nativeGetReceiver(long r0);

    private static native RtpSender nativeGetSender(long r0);

    private static native void nativeSetCodecPreferences(long r0, List<RtpCapabilities.CodecCapability> r2);

    private static native boolean nativeSetDirection(long r0, RtpTransceiverDirection r2);

    private static native void nativeStopInternal(long r0);

    private static native void nativeStopStandard(long r0);

    private static native boolean nativeStopped(long r0);

    @CalledByNative
    public void dispose() {
        checkRtpTransceiverExists();
        this.cachedSender.dispose();
        this.cachedReceiver.dispose();
        JniCommon.nativeReleaseRef(this.nativeRtpTransceiver);
        this.nativeRtpTransceiver = 0;
    }

    public RtpTransceiverDirection getCurrentDirection() {
        checkRtpTransceiverExists();
        return nativeCurrentDirection(this.nativeRtpTransceiver);
    }

    public RtpTransceiverDirection getDirection() {
        checkRtpTransceiverExists();
        return nativeDirection(this.nativeRtpTransceiver);
    }

    public MediaStreamTrack.MediaType getMediaType() {
        checkRtpTransceiverExists();
        return nativeGetMediaType(this.nativeRtpTransceiver);
    }

    public String getMid() {
        checkRtpTransceiverExists();
        return nativeGetMid(this.nativeRtpTransceiver);
    }

    public RtpReceiver getReceiver() {
        return this.cachedReceiver;
    }

    public RtpSender getSender() {
        return this.cachedSender;
    }

    public boolean isStopped() {
        checkRtpTransceiverExists();
        return nativeStopped(this.nativeRtpTransceiver);
    }

    public void setCodecPreferences(List<RtpCapabilities.CodecCapability> r3) {
        checkRtpTransceiverExists();
        nativeSetCodecPreferences(this.nativeRtpTransceiver, r3);
    }

    public boolean setDirection(RtpTransceiverDirection r3) {
        checkRtpTransceiverExists();
        return nativeSetDirection(this.nativeRtpTransceiver, r3);
    }

    public void stop() {
        checkRtpTransceiverExists();
        nativeStopInternal(this.nativeRtpTransceiver);
    }

    public void stopInternal() {
        checkRtpTransceiverExists();
        nativeStopInternal(this.nativeRtpTransceiver);
    }

    public void stopStandard() {
        checkRtpTransceiverExists();
        nativeStopStandard(this.nativeRtpTransceiver);
    }
}
