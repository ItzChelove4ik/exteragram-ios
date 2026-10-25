package org.webrtc;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.webrtc.DataChannel;
import org.webrtc.MediaStreamTrack;
import org.webrtc.RtpTransceiver;

/* loaded from: classes6.dex */
public class PeerConnection {
    private final List<MediaStream> localStreams;
    private final long nativePeerConnection;
    private List<RtpReceiver> receivers;
    private List<RtpSender> senders;
    private List<RtpTransceiver> transceivers;

    public enum AdapterType extends Enum<AdapterType> {
        private static final /* synthetic */ AdapterType[] $VALUES = null;
        public static final AdapterType ADAPTER_TYPE_ANY = null;
        private static final Map<Integer, AdapterType> BY_BITMASK = null;
        public static final AdapterType CELLULAR = null;
        public static final AdapterType CELLULAR_2G = null;
        public static final AdapterType CELLULAR_3G = null;
        public static final AdapterType CELLULAR_4G = null;
        public static final AdapterType CELLULAR_5G = null;
        public static final AdapterType ETHERNET = null;
        public static final AdapterType LOOPBACK = null;
        public static final AdapterType UNKNOWN = null;
        public static final AdapterType VPN = null;
        public static final AdapterType WIFI = null;
        public final Integer bitMask;

        private static /* synthetic */ AdapterType[] $values() {
            return new AdapterType[]{UNKNOWN, ETHERNET, WIFI, CELLULAR, VPN, LOOPBACK, ADAPTER_TYPE_ANY, CELLULAR_2G, CELLULAR_3G, CELLULAR_4G, CELLULAR_5G};
        }

        static {
            int r1 = 0;
            UNKNOWN = new AdapterType("UNKNOWN", 0, 0);
            ETHERNET = new AdapterType("ETHERNET", 1, 1);
            WIFI = new AdapterType("WIFI", 2, 2);
            CELLULAR = new AdapterType("CELLULAR", 3, 4);
            VPN = new AdapterType("VPN", 4, 8);
            LOOPBACK = new AdapterType("LOOPBACK", 5, 16);
            ADAPTER_TYPE_ANY = new AdapterType("ADAPTER_TYPE_ANY", 6, 32);
            CELLULAR_2G = new AdapterType("CELLULAR_2G", 7, 64);
            CELLULAR_3G = new AdapterType("CELLULAR_3G", 8, 128);
            CELLULAR_4G = new AdapterType("CELLULAR_4G", 9, 256);
            CELLULAR_5G = new AdapterType("CELLULAR_5G", 10, 512);
            $VALUES = $values();
            BY_BITMASK = new HashMap();
            AdapterType[] r0 = values();
            int r2 = r0.length;
        L3:
            if (r1 >= r2) goto L5;
            AdapterType r3 = r0[r1];
            BY_BITMASK.put(r3.bitMask, r3);
            r1 = r1 + 1;
            goto L3
        }

        AdapterType(String r1, int r2, Integer r3) {
            this.bitMask = r3;
        }

        @CalledByNative("AdapterType")
        public static AdapterType fromNativeIndex(int r1) {
            return BY_BITMASK.get(Integer.valueOf(r1));
        }

        public static AdapterType valueOf(String r1) {
            return (AdapterType) Enum.valueOf(AdapterType.class, r1);
        }

        public static AdapterType[] values() {
            return (AdapterType[]) $VALUES.clone();
        }
    }

    public enum BundlePolicy extends Enum<BundlePolicy> {
        private static final /* synthetic */ BundlePolicy[] $VALUES = null;
        public static final BundlePolicy BALANCED = null;
        public static final BundlePolicy MAXBUNDLE = null;
        public static final BundlePolicy MAXCOMPAT = null;

        private static /* synthetic */ BundlePolicy[] $values() {
            return new BundlePolicy[]{BALANCED, MAXBUNDLE, MAXCOMPAT};
        }

        static {
            BALANCED = new BundlePolicy("BALANCED", 0);
            MAXBUNDLE = new BundlePolicy("MAXBUNDLE", 1);
            MAXCOMPAT = new BundlePolicy("MAXCOMPAT", 2);
            $VALUES = $values();
        }

        BundlePolicy(String r1, int r2) {
        }

        public static BundlePolicy valueOf(String r1) {
            return (BundlePolicy) Enum.valueOf(BundlePolicy.class, r1);
        }

        public static BundlePolicy[] values() {
            return (BundlePolicy[]) $VALUES.clone();
        }
    }

    public enum CandidateNetworkPolicy extends Enum<CandidateNetworkPolicy> {
        private static final /* synthetic */ CandidateNetworkPolicy[] $VALUES = null;
        public static final CandidateNetworkPolicy ALL = null;
        public static final CandidateNetworkPolicy LOW_COST = null;

        private static /* synthetic */ CandidateNetworkPolicy[] $values() {
            return new CandidateNetworkPolicy[]{ALL, LOW_COST};
        }

        static {
            ALL = new CandidateNetworkPolicy("ALL", 0);
            LOW_COST = new CandidateNetworkPolicy("LOW_COST", 1);
            $VALUES = $values();
        }

        CandidateNetworkPolicy(String r1, int r2) {
        }

        public static CandidateNetworkPolicy valueOf(String r1) {
            return (CandidateNetworkPolicy) Enum.valueOf(CandidateNetworkPolicy.class, r1);
        }

        public static CandidateNetworkPolicy[] values() {
            return (CandidateNetworkPolicy[]) $VALUES.clone();
        }
    }

    public enum ContinualGatheringPolicy extends Enum<ContinualGatheringPolicy> {
        private static final /* synthetic */ ContinualGatheringPolicy[] $VALUES = null;
        public static final ContinualGatheringPolicy GATHER_CONTINUALLY = null;
        public static final ContinualGatheringPolicy GATHER_ONCE = null;

        private static /* synthetic */ ContinualGatheringPolicy[] $values() {
            return new ContinualGatheringPolicy[]{GATHER_ONCE, GATHER_CONTINUALLY};
        }

        static {
            GATHER_ONCE = new ContinualGatheringPolicy("GATHER_ONCE", 0);
            GATHER_CONTINUALLY = new ContinualGatheringPolicy("GATHER_CONTINUALLY", 1);
            $VALUES = $values();
        }

        ContinualGatheringPolicy(String r1, int r2) {
        }

        public static ContinualGatheringPolicy valueOf(String r1) {
            return (ContinualGatheringPolicy) Enum.valueOf(ContinualGatheringPolicy.class, r1);
        }

        public static ContinualGatheringPolicy[] values() {
            return (ContinualGatheringPolicy[]) $VALUES.clone();
        }
    }

    public enum IceConnectionState extends Enum<IceConnectionState> {
        private static final /* synthetic */ IceConnectionState[] $VALUES = null;
        public static final IceConnectionState CHECKING = null;
        public static final IceConnectionState CLOSED = null;
        public static final IceConnectionState COMPLETED = null;
        public static final IceConnectionState CONNECTED = null;
        public static final IceConnectionState DISCONNECTED = null;
        public static final IceConnectionState FAILED = null;
        public static final IceConnectionState NEW = null;

        private static /* synthetic */ IceConnectionState[] $values() {
            return new IceConnectionState[]{NEW, CHECKING, CONNECTED, COMPLETED, FAILED, DISCONNECTED, CLOSED};
        }

        static {
            NEW = new IceConnectionState("NEW", 0);
            CHECKING = new IceConnectionState("CHECKING", 1);
            CONNECTED = new IceConnectionState("CONNECTED", 2);
            COMPLETED = new IceConnectionState("COMPLETED", 3);
            FAILED = new IceConnectionState("FAILED", 4);
            DISCONNECTED = new IceConnectionState("DISCONNECTED", 5);
            CLOSED = new IceConnectionState("CLOSED", 6);
            $VALUES = $values();
        }

        IceConnectionState(String r1, int r2) {
        }

        @CalledByNative("IceConnectionState")
        public static IceConnectionState fromNativeIndex(int r1) {
            return values()[r1];
        }

        public static IceConnectionState valueOf(String r1) {
            return (IceConnectionState) Enum.valueOf(IceConnectionState.class, r1);
        }

        public static IceConnectionState[] values() {
            return (IceConnectionState[]) $VALUES.clone();
        }
    }

    public enum IceGatheringState extends Enum<IceGatheringState> {
        private static final /* synthetic */ IceGatheringState[] $VALUES = null;
        public static final IceGatheringState COMPLETE = null;
        public static final IceGatheringState GATHERING = null;
        public static final IceGatheringState NEW = null;

        private static /* synthetic */ IceGatheringState[] $values() {
            return new IceGatheringState[]{NEW, GATHERING, COMPLETE};
        }

        static {
            NEW = new IceGatheringState("NEW", 0);
            GATHERING = new IceGatheringState("GATHERING", 1);
            COMPLETE = new IceGatheringState("COMPLETE", 2);
            $VALUES = $values();
        }

        IceGatheringState(String r1, int r2) {
        }

        @CalledByNative("IceGatheringState")
        public static IceGatheringState fromNativeIndex(int r1) {
            return values()[r1];
        }

        public static IceGatheringState valueOf(String r1) {
            return (IceGatheringState) Enum.valueOf(IceGatheringState.class, r1);
        }

        public static IceGatheringState[] values() {
            return (IceGatheringState[]) $VALUES.clone();
        }
    }

    public static class IceServer {
        public final String hostname;
        public final String password;
        public final List<String> tlsAlpnProtocols;
        public final TlsCertPolicy tlsCertPolicy;
        public final List<String> tlsEllipticCurves;

        @Deprecated
        public final String uri;
        public final List<String> urls;
        public final String username;

        public static class Builder {
            private String hostname;
            private String password;
            private List<String> tlsAlpnProtocols;
            private TlsCertPolicy tlsCertPolicy;
            private List<String> tlsEllipticCurves;
            private final List<String> urls;
            private String username;

            private Builder(List<String> r3) {
                this.username = _UrlKt.FRAGMENT_ENCODE_SET;
                this.password = _UrlKt.FRAGMENT_ENCODE_SET;
                this.tlsCertPolicy = TlsCertPolicy.TLS_CERT_POLICY_SECURE;
                this.hostname = _UrlKt.FRAGMENT_ENCODE_SET;
                if (r3 != null) goto L5;
            L8:
                Native$$ExternalSyntheticBUOutline5.m("urls == null || urls.isEmpty(): ", r3);
                throw null;
            L5:
                if (r3.isEmpty() == true) goto L8;
                this.urls = r3;
            }

            public /* synthetic */ Builder(List r1, PeerConnectionIA r2) {
                this(r1);
            }

            public IceServer createIceServer() {
                return new IceServer(this.urls.get(0), this.urls, this.username, this.password, this.tlsCertPolicy, this.hostname, this.tlsAlpnProtocols, this.tlsEllipticCurves, null);
            }

            public Builder setHostname(String r1) {
                this.hostname = r1;
                return this;
            }

            public Builder setPassword(String r1) {
                this.password = r1;
                return this;
            }

            public Builder setTlsAlpnProtocols(List<String> r1) {
                this.tlsAlpnProtocols = r1;
                return this;
            }

            public Builder setTlsCertPolicy(TlsCertPolicy r1) {
                this.tlsCertPolicy = r1;
                return this;
            }

            public Builder setTlsEllipticCurves(List<String> r1) {
                this.tlsEllipticCurves = r1;
                return this;
            }

            public Builder setUsername(String r1) {
                this.username = r1;
                return this;
            }
        }

        @Deprecated
        public IceServer(String r2) {
            this(r2, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
        }

        @Deprecated
        public IceServer(String r2, String r3, String r4) {
            this(r2, r3, r4, TlsCertPolicy.TLS_CERT_POLICY_SECURE);
        }

        @Deprecated
        public IceServer(String r7, String r8, String r9, TlsCertPolicy r10) {
            this(r7, r8, r9, r10, _UrlKt.FRAGMENT_ENCODE_SET);
        }

        @Deprecated
        public IceServer(String r10, String r11, String r12, TlsCertPolicy r13, String r14) {
            this(r10, Collections.singletonList(r10), r11, r12, r13, r14, null, null);
        }

        private IceServer(String r4, List<String> r5, String r6, String r7, TlsCertPolicy r8, String r9, List<String> r10, List<String> r11) {
            if (r4 == null) goto L26;
            if (r5 == null) goto L26;
            if (r5.isEmpty() == true) goto L26;
            Iterator<String> r1 = r5.iterator();
        L9:
            if (r1.hasNext() == false) goto L15;
            if (r1.next() != null) goto L9;
            Native$$ExternalSyntheticBUOutline5.m("urls element is null: ", r5);
            throw null;
        L15:
            if (r6 == null) goto L24;
            if (r7 == null) goto L22;
            if (r9 == null) goto L20;
            this.uri = r4;
            this.urls = r5;
            this.username = r6;
            this.password = r7;
            this.tlsCertPolicy = r8;
            this.hostname = r9;
            this.tlsAlpnProtocols = r10;
            this.tlsEllipticCurves = r11;
            return;
        L20:
            f$$ExternalSyntheticBUOutline1.m("hostname == null");
            throw null;
        L22:
            f$$ExternalSyntheticBUOutline1.m("password == null");
            throw null;
        L24:
            f$$ExternalSyntheticBUOutline1.m("username == null");
            throw null;
        L26:
            f$$ExternalSyntheticBUOutline1.m("uri == null || urls == null || urls.isEmpty()");
            throw null;
        }

        public /* synthetic */ IceServer(String r1, List r2, String r3, String r4, TlsCertPolicy r5, String r6, List r7, List r8, PeerConnectionIA r9) {
            this(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        public static Builder builder(String r2) {
            return new Builder(Collections.singletonList(r2), null);
        }

        public static Builder builder(List<String> r2) {
            return new Builder(r2, null);
        }

        public boolean equals(Object r5) {
            if (r5 != null) goto L6;
            return false;
        L6:
            if (r5 != this) goto L9;
            return true;
        L9:
            if ((r5 instanceof IceServer) == true) goto L11;
            return false;
        L11:
            IceServer r52 = (IceServer) r5;
            if (this.uri.equals(r52.uri) == true) goto L14;
        L28:
            return false;
        L14:
            if (this.urls.equals(r52.urls) == false) goto L28;
            if (this.username.equals(r52.username) == false) goto L28;
            if (this.password.equals(r52.password) == false) goto L28;
            if (this.tlsCertPolicy.equals(r52.tlsCertPolicy) == false) goto L28;
            if (this.hostname.equals(r52.hostname) == false) goto L28;
            if (this.tlsAlpnProtocols.equals(r52.tlsAlpnProtocols) == false) goto L28;
            if (this.tlsEllipticCurves.equals(r52.tlsEllipticCurves) == false) goto L28;
            return true;
        }

        @CalledByNative("IceServer")
        public String getHostname() {
            return this.hostname;
        }

        @CalledByNative("IceServer")
        public String getPassword() {
            return this.password;
        }

        @CalledByNative("IceServer")
        public List<String> getTlsAlpnProtocols() {
            return this.tlsAlpnProtocols;
        }

        @CalledByNative("IceServer")
        public TlsCertPolicy getTlsCertPolicy() {
            return this.tlsCertPolicy;
        }

        @CalledByNative("IceServer")
        public List<String> getTlsEllipticCurves() {
            return this.tlsEllipticCurves;
        }

        @CalledByNative("IceServer")
        public List<String> getUrls() {
            return this.urls;
        }

        @CalledByNative("IceServer")
        public String getUsername() {
            return this.username;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.uri, this.urls, this.username, this.password, this.tlsCertPolicy, this.hostname, this.tlsAlpnProtocols, this.tlsEllipticCurves});
        }

        public String toString() {
            return this.urls + " [" + this.username + ":" + this.password + "] [" + this.tlsCertPolicy + "] [" + this.hostname + "] [" + this.tlsAlpnProtocols + "] [" + this.tlsEllipticCurves + "]";
        }
    }

    public enum IceTransportsType extends Enum<IceTransportsType> {
        private static final /* synthetic */ IceTransportsType[] $VALUES = null;
        public static final IceTransportsType ALL = null;
        public static final IceTransportsType NOHOST = null;
        public static final IceTransportsType NONE = null;
        public static final IceTransportsType RELAY = null;

        private static /* synthetic */ IceTransportsType[] $values() {
            return new IceTransportsType[]{NONE, RELAY, NOHOST, ALL};
        }

        static {
            NONE = new IceTransportsType("NONE", 0);
            RELAY = new IceTransportsType("RELAY", 1);
            NOHOST = new IceTransportsType("NOHOST", 2);
            ALL = new IceTransportsType("ALL", 3);
            $VALUES = $values();
        }

        IceTransportsType(String r1, int r2) {
        }

        public static IceTransportsType valueOf(String r1) {
            return (IceTransportsType) Enum.valueOf(IceTransportsType.class, r1);
        }

        public static IceTransportsType[] values() {
            return (IceTransportsType[]) $VALUES.clone();
        }
    }

    public enum KeyType extends Enum<KeyType> {
        private static final /* synthetic */ KeyType[] $VALUES = null;
        public static final KeyType ECDSA = null;
        public static final KeyType RSA = null;

        private static /* synthetic */ KeyType[] $values() {
            return new KeyType[]{RSA, ECDSA};
        }

        static {
            RSA = new KeyType("RSA", 0);
            ECDSA = new KeyType("ECDSA", 1);
            $VALUES = $values();
        }

        KeyType(String r1, int r2) {
        }

        public static KeyType valueOf(String r1) {
            return (KeyType) Enum.valueOf(KeyType.class, r1);
        }

        public static KeyType[] values() {
            return (KeyType[]) $VALUES.clone();
        }
    }

    public interface Observer {
        @CalledByNative("Observer")
        void onAddStream(MediaStream r1);

        @CalledByNative("Observer")
        default void onAddTrack(RtpReceiver r1, MediaStream[] r2) {
        }

        @CalledByNative("Observer")
        default void onConnectionChange(PeerConnectionState r1) {
        }

        @CalledByNative("Observer")
        void onDataChannel(DataChannel r1);

        @CalledByNative("Observer")
        void onIceCandidate(IceCandidate r1);

        @CalledByNative("Observer")
        default void onIceCandidateError(IceCandidateErrorEvent r1) {
        }

        @CalledByNative("Observer")
        void onIceCandidatesRemoved(IceCandidate[] r1);

        @CalledByNative("Observer")
        void onIceConnectionChange(IceConnectionState r1);

        @CalledByNative("Observer")
        void onIceConnectionReceivingChange(boolean r1);

        @CalledByNative("Observer")
        void onIceGatheringChange(IceGatheringState r1);

        @CalledByNative("Observer")
        void onRemoveStream(MediaStream r1);

        @CalledByNative("Observer")
        default void onRemoveTrack(RtpReceiver r1) {
        }

        @CalledByNative("Observer")
        void onRenegotiationNeeded();

        @CalledByNative("Observer")
        default void onSelectedCandidatePairChanged(CandidatePairChangeEvent r1) {
        }

        @CalledByNative("Observer")
        void onSignalingChange(SignalingState r1);

        @CalledByNative("Observer")
        default void onStandardizedIceConnectionChange(IceConnectionState r1) {
        }

        @CalledByNative("Observer")
        default void onTrack(RtpTransceiver r1) {
        }
    }

    public enum PeerConnectionState extends Enum<PeerConnectionState> {
        private static final /* synthetic */ PeerConnectionState[] $VALUES = null;
        public static final PeerConnectionState CLOSED = null;
        public static final PeerConnectionState CONNECTED = null;
        public static final PeerConnectionState CONNECTING = null;
        public static final PeerConnectionState DISCONNECTED = null;
        public static final PeerConnectionState FAILED = null;
        public static final PeerConnectionState NEW = null;

        private static /* synthetic */ PeerConnectionState[] $values() {
            return new PeerConnectionState[]{NEW, CONNECTING, CONNECTED, DISCONNECTED, FAILED, CLOSED};
        }

        static {
            NEW = new PeerConnectionState("NEW", 0);
            CONNECTING = new PeerConnectionState("CONNECTING", 1);
            CONNECTED = new PeerConnectionState("CONNECTED", 2);
            DISCONNECTED = new PeerConnectionState("DISCONNECTED", 3);
            FAILED = new PeerConnectionState("FAILED", 4);
            CLOSED = new PeerConnectionState("CLOSED", 5);
            $VALUES = $values();
        }

        PeerConnectionState(String r1, int r2) {
        }

        @CalledByNative("PeerConnectionState")
        public static PeerConnectionState fromNativeIndex(int r1) {
            return values()[r1];
        }

        public static PeerConnectionState valueOf(String r1) {
            return (PeerConnectionState) Enum.valueOf(PeerConnectionState.class, r1);
        }

        public static PeerConnectionState[] values() {
            return (PeerConnectionState[]) $VALUES.clone();
        }
    }

    public enum PortPrunePolicy extends Enum<PortPrunePolicy> {
        private static final /* synthetic */ PortPrunePolicy[] $VALUES = null;
        public static final PortPrunePolicy KEEP_FIRST_READY = null;
        public static final PortPrunePolicy NO_PRUNE = null;
        public static final PortPrunePolicy PRUNE_BASED_ON_PRIORITY = null;

        private static /* synthetic */ PortPrunePolicy[] $values() {
            return new PortPrunePolicy[]{NO_PRUNE, PRUNE_BASED_ON_PRIORITY, KEEP_FIRST_READY};
        }

        static {
            NO_PRUNE = new PortPrunePolicy("NO_PRUNE", 0);
            PRUNE_BASED_ON_PRIORITY = new PortPrunePolicy("PRUNE_BASED_ON_PRIORITY", 1);
            KEEP_FIRST_READY = new PortPrunePolicy("KEEP_FIRST_READY", 2);
            $VALUES = $values();
        }

        PortPrunePolicy(String r1, int r2) {
        }

        public static PortPrunePolicy valueOf(String r1) {
            return (PortPrunePolicy) Enum.valueOf(PortPrunePolicy.class, r1);
        }

        public static PortPrunePolicy[] values() {
            return (PortPrunePolicy[]) $VALUES.clone();
        }
    }

    public static class RTCConfiguration {
        public boolean activeResetSrtpParams;
        public boolean audioJitterBufferFastAccelerate;
        public int audioJitterBufferMaxPackets;
        public BundlePolicy bundlePolicy;
        public CandidateNetworkPolicy candidateNetworkPolicy;
        public RtcCertificatePem certificate;
        public ContinualGatheringPolicy continualGatheringPolicy;
        public CryptoOptions cryptoOptions;
        public boolean disableIPv6OnWifi;
        public boolean enableCpuOveruseDetection;
        public boolean enableDscp;
        public boolean enableImplicitRollback;
        public int iceBackupCandidatePairPingInterval;
        public int iceCandidatePoolSize;
        public Integer iceCheckIntervalStrongConnectivityMs;
        public Integer iceCheckIntervalWeakConnectivityMs;
        public Integer iceCheckMinInterval;
        public int iceConnectionReceivingTimeout;
        public List<IceServer> iceServers;
        public IceTransportsType iceTransportsType;
        public Integer iceUnwritableMinChecks;
        public Integer iceUnwritableTimeMs;
        public KeyType keyType;
        public int maxIPv6Networks;
        public AdapterType networkPreference;
        public boolean offerExtmapAllowMixed;
        public boolean presumeWritableWhenFullyRelayed;

        @Deprecated
        public boolean pruneTurnPorts;
        public RtcpMuxPolicy rtcpMuxPolicy;
        public Integer screencastMinBitrate;
        public SdpSemantics sdpSemantics;
        public Integer stableWritableConnectionPingIntervalMs;
        public Integer stunCandidateKeepaliveIntervalMs;
        public boolean surfaceIceCandidatesOnIceTransportTypeChanged;
        public boolean suspendBelowMinBitrate;
        public TcpCandidatePolicy tcpCandidatePolicy;
        public TurnCustomizer turnCustomizer;
        public String turnLoggingId;
        public PortPrunePolicy turnPortPrunePolicy;

        public RTCConfiguration(List<IceServer> r4) {
            this.iceTransportsType = IceTransportsType.ALL;
            this.bundlePolicy = BundlePolicy.BALANCED;
            this.rtcpMuxPolicy = RtcpMuxPolicy.REQUIRE;
            this.tcpCandidatePolicy = TcpCandidatePolicy.ENABLED;
            this.candidateNetworkPolicy = CandidateNetworkPolicy.ALL;
            this.iceServers = r4;
            this.audioJitterBufferMaxPackets = 50;
            this.audioJitterBufferFastAccelerate = false;
            this.iceConnectionReceivingTimeout = -1;
            this.iceBackupCandidatePairPingInterval = -1;
            this.keyType = KeyType.ECDSA;
            this.continualGatheringPolicy = ContinualGatheringPolicy.GATHER_ONCE;
            this.iceCandidatePoolSize = 0;
            this.pruneTurnPorts = false;
            this.turnPortPrunePolicy = PortPrunePolicy.NO_PRUNE;
            this.presumeWritableWhenFullyRelayed = false;
            this.surfaceIceCandidatesOnIceTransportTypeChanged = false;
            this.iceCheckIntervalStrongConnectivityMs = null;
            this.iceCheckIntervalWeakConnectivityMs = null;
            this.iceCheckMinInterval = null;
            this.iceUnwritableTimeMs = null;
            this.iceUnwritableMinChecks = null;
            this.stunCandidateKeepaliveIntervalMs = null;
            this.stableWritableConnectionPingIntervalMs = null;
            this.disableIPv6OnWifi = false;
            this.maxIPv6Networks = 5;
            this.enableDscp = false;
            this.enableCpuOveruseDetection = true;
            this.suspendBelowMinBitrate = false;
            this.screencastMinBitrate = null;
            this.networkPreference = AdapterType.UNKNOWN;
            this.sdpSemantics = SdpSemantics.UNIFIED_PLAN;
            this.activeResetSrtpParams = false;
            this.cryptoOptions = null;
            this.turnLoggingId = null;
            this.enableImplicitRollback = false;
            this.offerExtmapAllowMixed = true;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getActiveResetSrtpParams() {
            return this.activeResetSrtpParams;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getAudioJitterBufferFastAccelerate() {
            return this.audioJitterBufferFastAccelerate;
        }

        @CalledByNative("RTCConfiguration")
        public int getAudioJitterBufferMaxPackets() {
            return this.audioJitterBufferMaxPackets;
        }

        @CalledByNative("RTCConfiguration")
        public BundlePolicy getBundlePolicy() {
            return this.bundlePolicy;
        }

        @CalledByNative("RTCConfiguration")
        public CandidateNetworkPolicy getCandidateNetworkPolicy() {
            return this.candidateNetworkPolicy;
        }

        @CalledByNative("RTCConfiguration")
        public RtcCertificatePem getCertificate() {
            return this.certificate;
        }

        @CalledByNative("RTCConfiguration")
        public ContinualGatheringPolicy getContinualGatheringPolicy() {
            return this.continualGatheringPolicy;
        }

        @CalledByNative("RTCConfiguration")
        public CryptoOptions getCryptoOptions() {
            return this.cryptoOptions;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getDisableIPv6OnWifi() {
            return this.disableIPv6OnWifi;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getEnableCpuOveruseDetection() {
            return this.enableCpuOveruseDetection;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getEnableDscp() {
            return this.enableDscp;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getEnableImplicitRollback() {
            return this.enableImplicitRollback;
        }

        @CalledByNative("RTCConfiguration")
        public int getIceBackupCandidatePairPingInterval() {
            return this.iceBackupCandidatePairPingInterval;
        }

        @CalledByNative("RTCConfiguration")
        public int getIceCandidatePoolSize() {
            return this.iceCandidatePoolSize;
        }

        @CalledByNative("RTCConfiguration")
        public Integer getIceCheckIntervalStrongConnectivity() {
            return this.iceCheckIntervalStrongConnectivityMs;
        }

        @CalledByNative("RTCConfiguration")
        public Integer getIceCheckIntervalWeakConnectivity() {
            return this.iceCheckIntervalWeakConnectivityMs;
        }

        @CalledByNative("RTCConfiguration")
        public Integer getIceCheckMinInterval() {
            return this.iceCheckMinInterval;
        }

        @CalledByNative("RTCConfiguration")
        public int getIceConnectionReceivingTimeout() {
            return this.iceConnectionReceivingTimeout;
        }

        @CalledByNative("RTCConfiguration")
        public List<IceServer> getIceServers() {
            return this.iceServers;
        }

        @CalledByNative("RTCConfiguration")
        public IceTransportsType getIceTransportsType() {
            return this.iceTransportsType;
        }

        @CalledByNative("RTCConfiguration")
        public Integer getIceUnwritableMinChecks() {
            return this.iceUnwritableMinChecks;
        }

        @CalledByNative("RTCConfiguration")
        public Integer getIceUnwritableTimeout() {
            return this.iceUnwritableTimeMs;
        }

        @CalledByNative("RTCConfiguration")
        public KeyType getKeyType() {
            return this.keyType;
        }

        @CalledByNative("RTCConfiguration")
        public int getMaxIPv6Networks() {
            return this.maxIPv6Networks;
        }

        @CalledByNative("RTCConfiguration")
        public AdapterType getNetworkPreference() {
            return this.networkPreference;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getOfferExtmapAllowMixed() {
            return this.offerExtmapAllowMixed;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getPresumeWritableWhenFullyRelayed() {
            return this.presumeWritableWhenFullyRelayed;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getPruneTurnPorts() {
            return this.pruneTurnPorts;
        }

        @CalledByNative("RTCConfiguration")
        public RtcpMuxPolicy getRtcpMuxPolicy() {
            return this.rtcpMuxPolicy;
        }

        @CalledByNative("RTCConfiguration")
        public Integer getScreencastMinBitrate() {
            return this.screencastMinBitrate;
        }

        @CalledByNative("RTCConfiguration")
        public SdpSemantics getSdpSemantics() {
            return this.sdpSemantics;
        }

        @CalledByNative("RTCConfiguration")
        public Integer getStableWritableConnectionPingIntervalMs() {
            return this.stableWritableConnectionPingIntervalMs;
        }

        @CalledByNative("RTCConfiguration")
        public Integer getStunCandidateKeepaliveInterval() {
            return this.stunCandidateKeepaliveIntervalMs;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getSurfaceIceCandidatesOnIceTransportTypeChanged() {
            return this.surfaceIceCandidatesOnIceTransportTypeChanged;
        }

        @CalledByNative("RTCConfiguration")
        public boolean getSuspendBelowMinBitrate() {
            return this.suspendBelowMinBitrate;
        }

        @CalledByNative("RTCConfiguration")
        public TcpCandidatePolicy getTcpCandidatePolicy() {
            return this.tcpCandidatePolicy;
        }

        @CalledByNative("RTCConfiguration")
        public TurnCustomizer getTurnCustomizer() {
            return this.turnCustomizer;
        }

        @CalledByNative("RTCConfiguration")
        public String getTurnLoggingId() {
            return this.turnLoggingId;
        }

        @CalledByNative("RTCConfiguration")
        public PortPrunePolicy getTurnPortPrunePolicy() {
            return this.turnPortPrunePolicy;
        }
    }

    public enum RtcpMuxPolicy extends Enum<RtcpMuxPolicy> {
        private static final /* synthetic */ RtcpMuxPolicy[] $VALUES = null;
        public static final RtcpMuxPolicy NEGOTIATE = null;
        public static final RtcpMuxPolicy REQUIRE = null;

        private static /* synthetic */ RtcpMuxPolicy[] $values() {
            return new RtcpMuxPolicy[]{NEGOTIATE, REQUIRE};
        }

        static {
            NEGOTIATE = new RtcpMuxPolicy("NEGOTIATE", 0);
            REQUIRE = new RtcpMuxPolicy("REQUIRE", 1);
            $VALUES = $values();
        }

        RtcpMuxPolicy(String r1, int r2) {
        }

        public static RtcpMuxPolicy valueOf(String r1) {
            return (RtcpMuxPolicy) Enum.valueOf(RtcpMuxPolicy.class, r1);
        }

        public static RtcpMuxPolicy[] values() {
            return (RtcpMuxPolicy[]) $VALUES.clone();
        }
    }

    public enum SdpSemantics extends Enum<SdpSemantics> {
        private static final /* synthetic */ SdpSemantics[] $VALUES = null;

        @Deprecated
        public static final SdpSemantics PLAN_B = null;
        public static final SdpSemantics UNIFIED_PLAN = null;

        private static /* synthetic */ SdpSemantics[] $values() {
            return new SdpSemantics[]{PLAN_B, UNIFIED_PLAN};
        }

        static {
            PLAN_B = new SdpSemantics("PLAN_B", 0);
            UNIFIED_PLAN = new SdpSemantics("UNIFIED_PLAN", 1);
            $VALUES = $values();
        }

        SdpSemantics(String r1, int r2) {
        }

        public static SdpSemantics valueOf(String r1) {
            return (SdpSemantics) Enum.valueOf(SdpSemantics.class, r1);
        }

        public static SdpSemantics[] values() {
            return (SdpSemantics[]) $VALUES.clone();
        }
    }

    public enum SignalingState extends Enum<SignalingState> {
        private static final /* synthetic */ SignalingState[] $VALUES = null;
        public static final SignalingState CLOSED = null;
        public static final SignalingState HAVE_LOCAL_OFFER = null;
        public static final SignalingState HAVE_LOCAL_PRANSWER = null;
        public static final SignalingState HAVE_REMOTE_OFFER = null;
        public static final SignalingState HAVE_REMOTE_PRANSWER = null;
        public static final SignalingState STABLE = null;

        private static /* synthetic */ SignalingState[] $values() {
            return new SignalingState[]{STABLE, HAVE_LOCAL_OFFER, HAVE_LOCAL_PRANSWER, HAVE_REMOTE_OFFER, HAVE_REMOTE_PRANSWER, CLOSED};
        }

        static {
            STABLE = new SignalingState("STABLE", 0);
            HAVE_LOCAL_OFFER = new SignalingState("HAVE_LOCAL_OFFER", 1);
            HAVE_LOCAL_PRANSWER = new SignalingState("HAVE_LOCAL_PRANSWER", 2);
            HAVE_REMOTE_OFFER = new SignalingState("HAVE_REMOTE_OFFER", 3);
            HAVE_REMOTE_PRANSWER = new SignalingState("HAVE_REMOTE_PRANSWER", 4);
            CLOSED = new SignalingState("CLOSED", 5);
            $VALUES = $values();
        }

        SignalingState(String r1, int r2) {
        }

        @CalledByNative("SignalingState")
        public static SignalingState fromNativeIndex(int r1) {
            return values()[r1];
        }

        public static SignalingState valueOf(String r1) {
            return (SignalingState) Enum.valueOf(SignalingState.class, r1);
        }

        public static SignalingState[] values() {
            return (SignalingState[]) $VALUES.clone();
        }
    }

    public enum TcpCandidatePolicy extends Enum<TcpCandidatePolicy> {
        private static final /* synthetic */ TcpCandidatePolicy[] $VALUES = null;
        public static final TcpCandidatePolicy DISABLED = null;
        public static final TcpCandidatePolicy ENABLED = null;

        private static /* synthetic */ TcpCandidatePolicy[] $values() {
            return new TcpCandidatePolicy[]{ENABLED, DISABLED};
        }

        static {
            ENABLED = new TcpCandidatePolicy("ENABLED", 0);
            DISABLED = new TcpCandidatePolicy("DISABLED", 1);
            $VALUES = $values();
        }

        TcpCandidatePolicy(String r1, int r2) {
        }

        public static TcpCandidatePolicy valueOf(String r1) {
            return (TcpCandidatePolicy) Enum.valueOf(TcpCandidatePolicy.class, r1);
        }

        public static TcpCandidatePolicy[] values() {
            return (TcpCandidatePolicy[]) $VALUES.clone();
        }
    }

    public enum TlsCertPolicy extends Enum<TlsCertPolicy> {
        private static final /* synthetic */ TlsCertPolicy[] $VALUES = null;
        public static final TlsCertPolicy TLS_CERT_POLICY_INSECURE_NO_CHECK = null;
        public static final TlsCertPolicy TLS_CERT_POLICY_SECURE = null;

        private static /* synthetic */ TlsCertPolicy[] $values() {
            return new TlsCertPolicy[]{TLS_CERT_POLICY_SECURE, TLS_CERT_POLICY_INSECURE_NO_CHECK};
        }

        static {
            TLS_CERT_POLICY_SECURE = new TlsCertPolicy("TLS_CERT_POLICY_SECURE", 0);
            TLS_CERT_POLICY_INSECURE_NO_CHECK = new TlsCertPolicy("TLS_CERT_POLICY_INSECURE_NO_CHECK", 1);
            $VALUES = $values();
        }

        TlsCertPolicy(String r1, int r2) {
        }

        public static TlsCertPolicy valueOf(String r1) {
            return (TlsCertPolicy) Enum.valueOf(TlsCertPolicy.class, r1);
        }

        public static TlsCertPolicy[] values() {
            return (TlsCertPolicy[]) $VALUES.clone();
        }
    }

    public PeerConnection(long r2) {
        this.localStreams = new ArrayList();
        this.senders = new ArrayList();
        this.receivers = new ArrayList();
        this.transceivers = new ArrayList();
        this.nativePeerConnection = r2;
    }

    public PeerConnection(NativePeerConnectionFactory r3) {
        this(r3.createNativePeerConnection());
    }

    public static long createNativePeerConnectionObserver(Observer r2) {
        return nativeCreatePeerConnectionObserver(r2);
    }

    private native boolean nativeAddIceCandidate(String r1, int r2, String r3);

    private native void nativeAddIceCandidateWithObserver(String r1, int r2, String r3, AddIceObserver r4);

    private native boolean nativeAddLocalStream(long r1);

    private native RtpSender nativeAddTrack(long r1, List<String> r3);

    private native RtpTransceiver nativeAddTransceiverOfType(MediaStreamTrack.MediaType r1, RtpTransceiver.RtpTransceiverInit r2);

    private native RtpTransceiver nativeAddTransceiverWithTrack(long r1, RtpTransceiver.RtpTransceiverInit r3);

    private native void nativeClose();

    private native PeerConnectionState nativeConnectionState();

    private native void nativeCreateAnswer(SdpObserver r1, MediaConstraints r2);

    private native DataChannel nativeCreateDataChannel(String r1, DataChannel.Init r2);

    private native void nativeCreateOffer(SdpObserver r1, MediaConstraints r2);

    private static native long nativeCreatePeerConnectionObserver(Observer r0);

    private native RtpSender nativeCreateSender(String r1, String r2);

    private static native void nativeFreeOwnedPeerConnection(long r0);

    private native RtcCertificatePem nativeGetCertificate();

    private native SessionDescription nativeGetLocalDescription();

    private native long nativeGetNativePeerConnection();

    private native List<RtpReceiver> nativeGetReceivers();

    private native SessionDescription nativeGetRemoteDescription();

    private native List<RtpSender> nativeGetSenders();

    private native List<RtpTransceiver> nativeGetTransceivers();

    private native IceConnectionState nativeIceConnectionState();

    private native IceGatheringState nativeIceGatheringState();

    private native void nativeNewGetStats(RTCStatsCollectorCallback r1);

    private native void nativeNewGetStatsReceiver(long r1, RTCStatsCollectorCallback r3);

    private native void nativeNewGetStatsSender(long r1, RTCStatsCollectorCallback r3);

    private native boolean nativeOldGetStats(StatsObserver r1, long r2);

    private native boolean nativeRemoveIceCandidates(IceCandidate[] r1);

    private native void nativeRemoveLocalStream(long r1);

    private native boolean nativeRemoveTrack(long r1);

    private native void nativeRestartIce();

    private native void nativeSetAudioPlayout(boolean r1);

    private native void nativeSetAudioRecording(boolean r1);

    private native boolean nativeSetBitrate(Integer r1, Integer r2, Integer r3);

    private native boolean nativeSetConfiguration(RTCConfiguration r1);

    private native void nativeSetLocalDescription(SdpObserver r1, SessionDescription r2);

    private native void nativeSetLocalDescriptionAutomatically(SdpObserver r1);

    private native void nativeSetRemoteDescription(SdpObserver r1, SessionDescription r2);

    private native SignalingState nativeSignalingState();

    private native boolean nativeStartRtcEventLog(int r1, int r2);

    private native void nativeStopRtcEventLog();

    public void addIceCandidate(IceCandidate r3, AddIceObserver r4) {
        nativeAddIceCandidateWithObserver(r3.sdpMid, r3.sdpMLineIndex, r3.sdp, r4);
    }

    public boolean addIceCandidate(IceCandidate r3) {
        return nativeAddIceCandidate(r3.sdpMid, r3.sdpMLineIndex, r3.sdp);
    }

    public boolean addStream(MediaStream r3) {
        if (nativeAddLocalStream(r3.getNativeMediaStream()) == true) goto L6;
        return false;
    L6:
        this.localStreams.add(r3);
        return true;
    }

    public RtpSender addTrack(MediaStreamTrack r2) {
        return addTrack(r2, Collections.EMPTY_LIST);
    }

    public RtpSender addTrack(MediaStreamTrack r4, List<String> r5) {
        if (r4 == null) goto L11;
        if (r5 == null) goto L11;
        RtpSender r42 = nativeAddTrack(r4.getNativeMediaStreamTrack(), r5);
        if (r42 == null) goto L9;
        this.senders.add(r42);
        return r42;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("C++ addTrack failed.");
        return null;
    L11:
        f$$ExternalSyntheticBUOutline2.m("No MediaStreamTrack specified in addTrack.");
        return null;
    }

    public RtpTransceiver addTransceiver(MediaStreamTrack.MediaType r2) {
        return addTransceiver(r2, new RtpTransceiver.RtpTransceiverInit());
    }

    public RtpTransceiver addTransceiver(MediaStreamTrack.MediaType r2, RtpTransceiver.RtpTransceiverInit r3) {
        if (r2 == null) goto L12;
        if (r3 != null) goto L6;
        r3 = new RtpTransceiver.RtpTransceiverInit();
    L6:
        RtpTransceiver r22 = nativeAddTransceiverOfType(r2, r3);
        if (r22 == null) goto L10;
        this.transceivers.add(r22);
        return r22;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("C++ addTransceiver failed.");
        return null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("No MediaType specified for addTransceiver.");
        return null;
    }

    public RtpTransceiver addTransceiver(MediaStreamTrack r2) {
        return addTransceiver(r2, new RtpTransceiver.RtpTransceiverInit());
    }

    public RtpTransceiver addTransceiver(MediaStreamTrack r4, RtpTransceiver.RtpTransceiverInit r5) {
        if (r4 == null) goto L12;
        if (r5 != null) goto L6;
        r5 = new RtpTransceiver.RtpTransceiverInit();
    L6:
        RtpTransceiver r42 = nativeAddTransceiverWithTrack(r4.getNativeMediaStreamTrack(), r5);
        if (r42 == null) goto L10;
        this.transceivers.add(r42);
        return r42;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("C++ addTransceiver failed.");
        return null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("No MediaStreamTrack specified for addTransceiver.");
        return null;
    }

    public void close() {
        nativeClose();
    }

    public PeerConnectionState connectionState() {
        return nativeConnectionState();
    }

    public void createAnswer(SdpObserver r1, MediaConstraints r2) {
        nativeCreateAnswer(r1, r2);
    }

    public DataChannel createDataChannel(String r1, DataChannel.Init r2) {
        return nativeCreateDataChannel(r1, r2);
    }

    public void createOffer(SdpObserver r1, MediaConstraints r2) {
        nativeCreateOffer(r1, r2);
    }

    public RtpSender createSender(String r1, String r2) {
        RtpSender r12 = nativeCreateSender(r1, r2);
        if (r12 == null) goto L5;
        this.senders.add(r12);
    L5:
        return r12;
    }

    public void dispose() {
        close();
        Iterator<MediaStream> r0 = this.localStreams.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        MediaStream r1 = r0.next();
        nativeRemoveLocalStream(r1.getNativeMediaStream());
        r1.dispose();
        goto L4
    L6:
        this.localStreams.clear();
        Iterator<RtpSender> r02 = this.senders.iterator();
    L8:
        if (r02.hasNext() == false) goto L10;
        r02.next().dispose();
        goto L8
    L10:
        this.senders.clear();
        Iterator<RtpReceiver> r03 = this.receivers.iterator();
    L12:
        if (r03.hasNext() == false) goto L14;
        r03.next().dispose();
        goto L12
    L14:
        Iterator<RtpTransceiver> r04 = this.transceivers.iterator();
    L16:
        if (r04.hasNext() == false) goto L18;
        r04.next().dispose();
        goto L16
    L18:
        this.transceivers.clear();
        this.receivers.clear();
        nativeFreeOwnedPeerConnection(this.nativePeerConnection);
    }

    public RtcCertificatePem getCertificate() {
        return nativeGetCertificate();
    }

    public SessionDescription getLocalDescription() {
        return nativeGetLocalDescription();
    }

    @CalledByNative
    public long getNativeOwnedPeerConnection() {
        return this.nativePeerConnection;
    }

    public long getNativePeerConnection() {
        return nativeGetNativePeerConnection();
    }

    public List<RtpReceiver> getReceivers() {
        Iterator<RtpReceiver> r0 = this.receivers.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().dispose();
        goto L4
    L6:
        List<RtpReceiver> r02 = nativeGetReceivers();
        this.receivers = r02;
        return Collections.unmodifiableList(r02);
    }

    public SessionDescription getRemoteDescription() {
        return nativeGetRemoteDescription();
    }

    public List<RtpSender> getSenders() {
        Iterator<RtpSender> r0 = this.senders.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().dispose();
        goto L4
    L6:
        List<RtpSender> r02 = nativeGetSenders();
        this.senders = r02;
        return Collections.unmodifiableList(r02);
    }

    public void getStats(RTCStatsCollectorCallback r1) {
        nativeNewGetStats(r1);
    }

    public void getStats(RtpReceiver r3, RTCStatsCollectorCallback r4) {
        nativeNewGetStatsReceiver(r3.getNativeRtpReceiver(), r4);
    }

    public void getStats(RtpSender r3, RTCStatsCollectorCallback r4) {
        nativeNewGetStatsSender(r3.getNativeRtpSender(), r4);
    }

    @Deprecated
    public boolean getStats(StatsObserver r3, MediaStreamTrack r4) {
        if (r4 != null) goto L4;
        long r0 = 0;
    L6:
        return nativeOldGetStats(r3, r0);
    L4:
        r0 = r4.getNativeMediaStreamTrack();
        goto L6
    }

    public List<RtpTransceiver> getTransceivers() {
        Iterator<RtpTransceiver> r0 = this.transceivers.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().dispose();
        goto L4
    L6:
        List<RtpTransceiver> r02 = nativeGetTransceivers();
        this.transceivers = r02;
        return Collections.unmodifiableList(r02);
    }

    public IceConnectionState iceConnectionState() {
        return nativeIceConnectionState();
    }

    public IceGatheringState iceGatheringState() {
        return nativeIceGatheringState();
    }

    public boolean removeIceCandidates(IceCandidate[] r1) {
        return nativeRemoveIceCandidates(r1);
    }

    public void removeStream(MediaStream r3) {
        nativeRemoveLocalStream(r3.getNativeMediaStream());
        this.localStreams.remove(r3);
    }

    public boolean removeTrack(RtpSender r3) {
        if (r3 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("No RtpSender specified for removeTrack.");
        return false;
    L4:
        return nativeRemoveTrack(r3.getNativeRtpSender());
    }

    public void restartIce() {
        nativeRestartIce();
    }

    public void setAudioPlayout(boolean r1) {
        nativeSetAudioPlayout(r1);
    }

    public void setAudioRecording(boolean r1) {
        nativeSetAudioRecording(r1);
    }

    public boolean setBitrate(Integer r1, Integer r2, Integer r3) {
        return nativeSetBitrate(r1, r2, r3);
    }

    public boolean setConfiguration(RTCConfiguration r1) {
        return nativeSetConfiguration(r1);
    }

    public void setLocalDescription(SdpObserver r1) {
        nativeSetLocalDescriptionAutomatically(r1);
    }

    public void setLocalDescription(SdpObserver r1, SessionDescription r2) {
        nativeSetLocalDescription(r1, r2);
    }

    public void setRemoteDescription(SdpObserver r1, SessionDescription r2) {
        nativeSetRemoteDescription(r1, r2);
    }

    public SignalingState signalingState() {
        return nativeSignalingState();
    }

    public boolean startRtcEventLog(int r1, int r2) {
        return nativeStartRtcEventLog(r1, r2);
    }

    public void stopRtcEventLog() {
        nativeStopRtcEventLog();
    }
}
