package org.webrtc;

import java.util.List;
import java.util.Map;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
public class RtpCapabilities {
    public List<CodecCapability> codecs;
    public List<HeaderExtensionCapability> headerExtensions;

    public static class CodecCapability {
        public Integer clockRate;
        public MediaStreamTrack.MediaType kind;
        public String mimeType;
        public String name;
        public Integer numChannels;
        public Map<String, String> parameters;
        public int preferredPayloadType;

        public CodecCapability() {
        }

        @CalledByNative("CodecCapability")
        public CodecCapability(int r1, String r2, MediaStreamTrack.MediaType r3, Integer r4, Integer r5, String r6, Map<String, String> r7) {
            this.preferredPayloadType = r1;
            this.name = r2;
            this.kind = r3;
            this.clockRate = r4;
            this.numChannels = r5;
            this.parameters = r7;
            this.mimeType = r6;
        }

        @CalledByNative("CodecCapability")
        public Integer getClockRate() {
            return this.clockRate;
        }

        @CalledByNative("CodecCapability")
        public MediaStreamTrack.MediaType getKind() {
            return this.kind;
        }

        @CalledByNative("CodecCapability")
        public String getName() {
            return this.name;
        }

        @CalledByNative("CodecCapability")
        public Integer getNumChannels() {
            return this.numChannels;
        }

        @CalledByNative("CodecCapability")
        public Map getParameters() {
            return this.parameters;
        }

        @CalledByNative("CodecCapability")
        public int getPreferredPayloadType() {
            return this.preferredPayloadType;
        }
    }

    public static class HeaderExtensionCapability {
        private final boolean preferredEncrypted;
        private final int preferredId;
        private final String uri;

        @CalledByNative("HeaderExtensionCapability")
        public HeaderExtensionCapability(String r1, int r2, boolean r3) {
            this.uri = r1;
            this.preferredId = r2;
            this.preferredEncrypted = r3;
        }

        @CalledByNative("HeaderExtensionCapability")
        public boolean getPreferredEncrypted() {
            return this.preferredEncrypted;
        }

        @CalledByNative("HeaderExtensionCapability")
        public int getPreferredId() {
            return this.preferredId;
        }

        @CalledByNative("HeaderExtensionCapability")
        public String getUri() {
            return this.uri;
        }
    }

    @CalledByNative
    public RtpCapabilities(List<CodecCapability> r1, List<HeaderExtensionCapability> r2) {
        this.headerExtensions = r2;
        this.codecs = r1;
    }

    @CalledByNative
    public List<CodecCapability> getCodecs() {
        return this.codecs;
    }

    @CalledByNative
    public List<HeaderExtensionCapability> getHeaderExtensions() {
        return this.headerExtensions;
    }
}
