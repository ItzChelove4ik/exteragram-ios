package org.webrtc;

import java.util.List;
import java.util.Map;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
public class RtpParameters {
    public final List<Codec> codecs;
    public DegradationPreference degradationPreference;
    public final List<Encoding> encodings;
    private final List<HeaderExtension> headerExtensions;
    private final Rtcp rtcp;
    public final String transactionId;

    public static class Codec {
        public Integer clockRate;
        MediaStreamTrack.MediaType kind;
        public String name;
        public Integer numChannels;
        public Map<String, String> parameters;
        public int payloadType;

        @CalledByNative("Codec")
        public Codec(int r1, String r2, MediaStreamTrack.MediaType r3, Integer r4, Integer r5, Map<String, String> r6) {
            this.payloadType = r1;
            this.name = r2;
            this.kind = r3;
            this.clockRate = r4;
            this.numChannels = r5;
            this.parameters = r6;
        }

        @CalledByNative("Codec")
        public Integer getClockRate() {
            return this.clockRate;
        }

        @CalledByNative("Codec")
        public MediaStreamTrack.MediaType getKind() {
            return this.kind;
        }

        @CalledByNative("Codec")
        public String getName() {
            return this.name;
        }

        @CalledByNative("Codec")
        public Integer getNumChannels() {
            return this.numChannels;
        }

        @CalledByNative("Codec")
        public Map getParameters() {
            return this.parameters;
        }

        @CalledByNative("Codec")
        public int getPayloadType() {
            return this.payloadType;
        }
    }

    public enum DegradationPreference extends Enum<DegradationPreference> {
        private static final /* synthetic */ DegradationPreference[] $VALUES = null;
        public static final DegradationPreference BALANCED = null;
        public static final DegradationPreference DISABLED = null;
        public static final DegradationPreference MAINTAIN_FRAMERATE = null;
        public static final DegradationPreference MAINTAIN_RESOLUTION = null;

        private static /* synthetic */ DegradationPreference[] $values() {
            return new DegradationPreference[]{DISABLED, MAINTAIN_FRAMERATE, MAINTAIN_RESOLUTION, BALANCED};
        }

        static {
            DISABLED = new DegradationPreference("DISABLED", 0);
            MAINTAIN_FRAMERATE = new DegradationPreference("MAINTAIN_FRAMERATE", 1);
            MAINTAIN_RESOLUTION = new DegradationPreference("MAINTAIN_RESOLUTION", 2);
            BALANCED = new DegradationPreference("BALANCED", 3);
            $VALUES = $values();
        }

        DegradationPreference(String r1, int r2) {
        }

        @CalledByNative("DegradationPreference")
        public static DegradationPreference fromNativeIndex(int r1) {
            return values()[r1];
        }

        public static DegradationPreference valueOf(String r1) {
            return (DegradationPreference) Enum.valueOf(DegradationPreference.class, r1);
        }

        public static DegradationPreference[] values() {
            return (DegradationPreference[]) $VALUES.clone();
        }
    }

    public static class Encoding {
        public boolean active;
        public boolean adaptiveAudioPacketTime;
        public double bitratePriority;
        public Integer maxBitrateBps;
        public Integer maxFramerate;
        public Integer minBitrateBps;
        public int networkPriority;
        public Integer numTemporalLayers;
        public String rid;
        public Double scaleResolutionDownBy;
        public Long ssrc;

        @CalledByNative("Encoding")
        public Encoding(String r1, boolean r2, double r3, int r5, Integer r6, Integer r7, Integer r8, Integer r9, Double r10, Long r11, boolean r12) {
            this.rid = r1;
            this.active = r2;
            this.bitratePriority = r3;
            this.networkPriority = r5;
            this.maxBitrateBps = r6;
            this.minBitrateBps = r7;
            this.maxFramerate = r8;
            this.numTemporalLayers = r9;
            this.scaleResolutionDownBy = r10;
            this.ssrc = r11;
            this.adaptiveAudioPacketTime = r12;
        }

        public Encoding(String r3, boolean r4, Double r5) {
            this.bitratePriority = 1.0d;
            this.networkPriority = 0;
            this.rid = r3;
            this.active = r4;
            this.scaleResolutionDownBy = r5;
        }

        @CalledByNative("Encoding")
        public boolean getActive() {
            return this.active;
        }

        @CalledByNative("Encoding")
        public boolean getAdaptivePTime() {
            return this.adaptiveAudioPacketTime;
        }

        @CalledByNative("Encoding")
        public double getBitratePriority() {
            return this.bitratePriority;
        }

        @CalledByNative("Encoding")
        public Integer getMaxBitrateBps() {
            return this.maxBitrateBps;
        }

        @CalledByNative("Encoding")
        public Integer getMaxFramerate() {
            return this.maxFramerate;
        }

        @CalledByNative("Encoding")
        public Integer getMinBitrateBps() {
            return this.minBitrateBps;
        }

        @CalledByNative("Encoding")
        public int getNetworkPriority() {
            return this.networkPriority;
        }

        @CalledByNative("Encoding")
        public Integer getNumTemporalLayers() {
            return this.numTemporalLayers;
        }

        @CalledByNative("Encoding")
        public String getRid() {
            return this.rid;
        }

        @CalledByNative("Encoding")
        public Double getScaleResolutionDownBy() {
            return this.scaleResolutionDownBy;
        }

        @CalledByNative("Encoding")
        public Long getSsrc() {
            return this.ssrc;
        }
    }

    public static class HeaderExtension {
        private final boolean encrypted;
        private final int id;
        private final String uri;

        @CalledByNative("HeaderExtension")
        public HeaderExtension(String r1, int r2, boolean r3) {
            this.uri = r1;
            this.id = r2;
            this.encrypted = r3;
        }

        @CalledByNative("HeaderExtension")
        public boolean getEncrypted() {
            return this.encrypted;
        }

        @CalledByNative("HeaderExtension")
        public int getId() {
            return this.id;
        }

        @CalledByNative("HeaderExtension")
        public String getUri() {
            return this.uri;
        }
    }

    public static class Rtcp {
        private final String cname;
        private final boolean reducedSize;

        @CalledByNative("Rtcp")
        public Rtcp(String r1, boolean r2) {
            this.cname = r1;
            this.reducedSize = r2;
        }

        @CalledByNative("Rtcp")
        public String getCname() {
            return this.cname;
        }

        @CalledByNative("Rtcp")
        public boolean getReducedSize() {
            return this.reducedSize;
        }
    }

    @CalledByNative
    public RtpParameters(String r1, DegradationPreference r2, Rtcp r3, List<HeaderExtension> r4, List<Encoding> r5, List<Codec> r6) {
        this.transactionId = r1;
        this.degradationPreference = r2;
        this.rtcp = r3;
        this.headerExtensions = r4;
        this.encodings = r5;
        this.codecs = r6;
    }

    @CalledByNative
    public List<Codec> getCodecs() {
        return this.codecs;
    }

    @CalledByNative
    public DegradationPreference getDegradationPreference() {
        return this.degradationPreference;
    }

    @CalledByNative
    public List<Encoding> getEncodings() {
        return this.encodings;
    }

    @CalledByNative
    public List<HeaderExtension> getHeaderExtensions() {
        return this.headerExtensions;
    }

    @CalledByNative
    public Rtcp getRtcp() {
        return this.rtcp;
    }

    @CalledByNative
    public String getTransactionId() {
        return this.transactionId;
    }
}
