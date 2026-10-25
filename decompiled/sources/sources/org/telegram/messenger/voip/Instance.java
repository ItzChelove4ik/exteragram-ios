package org.telegram.messenger.voip;

import androidx.media3.common.util.Util;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.voip.NativeInstance;
import org.webrtc.ContextUtils;
import org.webrtc.VideoSink;

/* loaded from: classes6.dex */
public final class Instance {
    public static final int AUDIO_STATE_ACTIVE = 1;
    public static final int AUDIO_STATE_MUTED = 0;
    public static final int DATA_SAVING_ALWAYS = 2;
    public static final int DATA_SAVING_MOBILE = 1;
    public static final int DATA_SAVING_NEVER = 0;
    public static final int DATA_SAVING_ROAMING = 3;
    public static final int ENDPOINT_TYPE_INET = 0;
    public static final int ENDPOINT_TYPE_LAN = 1;
    public static final int ENDPOINT_TYPE_TCP_RELAY = 3;
    public static final int ENDPOINT_TYPE_UDP_RELAY = 2;
    public static final String ERROR_AUDIO_IO = "ERROR_AUDIO_IO";
    public static final String ERROR_CONNECTION_SERVICE = "ERROR_CONNECTION_SERVICE";
    public static final String ERROR_INCOMPATIBLE = "ERROR_INCOMPATIBLE";
    public static final String ERROR_INSECURE_UPGRADE = "ERROR_INSECURE_UPGRADE";
    public static final String ERROR_LOCALIZED = "ERROR_LOCALIZED";
    public static final String ERROR_PEER_OUTDATED = "ERROR_PEER_OUTDATED";
    public static final String ERROR_PRIVACY = "ERROR_PRIVACY";
    public static final String ERROR_TIMEOUT = "ERROR_TIMEOUT";
    public static final String ERROR_UNKNOWN = "ERROR_UNKNOWN";
    public static final int NET_TYPE_3G = 3;
    public static final int NET_TYPE_DIALUP = 10;
    public static final int NET_TYPE_EDGE = 2;
    public static final int NET_TYPE_ETHERNET = 7;
    public static final int NET_TYPE_GPRS = 1;
    public static final int NET_TYPE_HSPA = 4;
    public static final int NET_TYPE_LTE = 5;
    public static final int NET_TYPE_OTHER_HIGH_SPEED = 8;
    public static final int NET_TYPE_OTHER_LOW_SPEED = 9;
    public static final int NET_TYPE_OTHER_MOBILE = 11;
    public static final int NET_TYPE_UNKNOWN = 0;
    public static final int NET_TYPE_WIFI = 6;
    public static final int PEER_CAP_GROUP_CALLS = 1;
    public static final int STATE_ESTABLISHED = 3;
    public static final int STATE_FAILED = 4;
    public static final int STATE_RECONNECTING = 5;
    public static final int STATE_WAIT_INIT = 1;
    public static final int STATE_WAIT_INIT_ACK = 2;
    public static final int VIDEO_STATE_ACTIVE = 2;
    public static final int VIDEO_STATE_INACTIVE = 0;
    public static final int VIDEO_STATE_PAUSED = 1;
    private static int bufferSize;
    private static ServerConfig globalServerConfig;
    private static NativeInstance instance;

    public static final class Candidate {
        public final String component;
        public final String foundation;
        public final String generation;
        public final String id;
        public final String ip;
        public final String network;
        public final String port;
        public final String priority;
        public final String protocol;
        public final String relAddr;
        public final String relPort;
        public final String tcpType;
        public final String type;

        public Candidate(String r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13) {
            this.port = r1;
            this.protocol = r2;
            this.network = r3;
            this.generation = r4;
            this.id = r5;
            this.component = r6;
            this.foundation = r7;
            this.priority = r8;
            this.ip = r9;
            this.type = r10;
            this.tcpType = r11;
            this.relAddr = r12;
            this.relPort = r13;
        }

        public String toString() {
            return "Candidate{port=" + this.port + ", protocol=" + this.protocol + ", network=" + this.network + ", generation=" + this.generation + ", id=" + this.id + ", component=" + this.component + ", foundation=" + this.foundation + ", priority=" + this.priority + ", ip=" + this.ip + ", type=" + this.type + ", tcpType=" + this.tcpType + ", relAddr=" + this.relAddr + ", relPort=" + this.relPort + '}';
        }
    }

    public static final class Config {
        public final String customParameters;
        public final int dataSaving;
        public final boolean enableAec;
        public final boolean enableAgc;
        public final boolean enableCallUpgrade;
        public final boolean enableNs;
        public final boolean enableP2p;
        public final boolean enableSm;
        public final double initializationTimeout;
        public final String logPath;
        public final int maxApiLayer;
        public final double receiveTimeout;
        public final String statsLogPath;

        public Config(double r1, double r3, int r5, boolean r6, boolean r7, boolean r8, boolean r9, boolean r10, boolean r11, String r12, String r13, int r14, String r15) {
            this.initializationTimeout = r1;
            this.receiveTimeout = r3;
            this.dataSaving = r5;
            this.enableP2p = r6;
            this.enableAec = r7;
            this.enableNs = r8;
            this.enableAgc = r9;
            this.enableCallUpgrade = r10;
            this.logPath = r12;
            this.statsLogPath = r13;
            this.maxApiLayer = r14;
            this.enableSm = r11;
            this.customParameters = r15;
        }

        public String toString() {
            return "Config{initializationTimeout=" + this.initializationTimeout + ", receiveTimeout=" + this.receiveTimeout + ", dataSaving=" + this.dataSaving + ", enableP2p=" + this.enableP2p + ", enableAec=" + this.enableAec + ", enableNs=" + this.enableNs + ", enableAgc=" + this.enableAgc + ", enableCallUpgrade=" + this.enableCallUpgrade + ", logPath='" + this.logPath + "', statsLogPath='" + this.statsLogPath + "', maxApiLayer=" + this.maxApiLayer + ", enableSm=" + this.enableSm + ", customParameters=" + this.customParameters + '}';
        }
    }

    public static final class EncryptionKey {
        public final boolean isOutgoing;
        public final byte[] value;

        public EncryptionKey(byte[] r1, boolean r2) {
            this.value = r1;
            this.isOutgoing = r2;
        }

        public String toString() {
            return "EncryptionKey{value=" + Arrays.toString(this.value) + ", isOutgoing=" + this.isOutgoing + '}';
        }
    }

    public static final class Endpoint {
        public final long id;
        public final String ipv4;
        public final String ipv6;
        public final boolean isRtc;
        public final String password;
        public final byte[] peerTag;
        public final int port;
        public int reflectorId;
        public final boolean stun;
        public final boolean tcp;
        public final boolean turn;
        public final int type;
        public final String username;

        public Endpoint(boolean r1, long r2, String r4, String r5, int r6, int r7, byte[] r8, boolean r9, boolean r10, String r11, String r12, boolean r13) {
            this.isRtc = r1;
            this.id = r2;
            this.ipv4 = r4;
            this.ipv6 = r5;
            this.port = r6;
            this.type = r7;
            this.peerTag = r8;
            this.turn = r9;
            this.stun = r10;
            if (r1 == false) goto L5;
            this.username = r11;
            this.password = r12;
        L8:
            this.tcp = r13;
            return;
        L5:
            if (r8 == null) goto L7;
            this.username = "reflector";
            this.password = Util.toHexString(r8);
            goto L8
        L7:
            this.username = null;
            this.password = null;
            goto L8
        }

        public String toString() {
            return "Endpoint{id=" + this.id + ", ipv4='" + this.ipv4 + "', ipv6='" + this.ipv6 + "', port=" + this.port + ", type=" + this.type + ", peerTag=" + Arrays.toString(this.peerTag) + ", turn=" + this.turn + ", stun=" + this.stun + ", username=" + this.username + ", password=" + this.password + ", tcp=" + this.tcp + '}';
        }
    }

    public static final class FinalState {
        public String debugLog;
        public final boolean isRatingSuggested;
        public final byte[] persistentState;
        public final TrafficStats trafficStats;

        public FinalState(byte[] r1, String r2, TrafficStats r3, boolean r4) {
            this.persistentState = r1;
            this.debugLog = r2;
            this.trafficStats = r3;
            this.isRatingSuggested = r4;
        }

        public String toString() {
            return "FinalState{persistentState=" + Arrays.toString(this.persistentState) + ", debugLog='" + this.debugLog + "', trafficStats=" + this.trafficStats + ", isRatingSuggested=" + this.isRatingSuggested + '}';
        }
    }

    public static final class Fingerprint {
        public final String fingerprint;
        public final String hash;
        public final String setup;

        public Fingerprint(String r1, String r2, String r3) {
            this.hash = r1;
            this.setup = r2;
            this.fingerprint = r3;
        }

        public String toString() {
            return "Fingerprint{hash=" + this.hash + ", setup=" + this.setup + ", fingerprint=" + this.fingerprint + '}';
        }
    }

    public interface OnRemoteMediaStateUpdatedListener {
        void onMediaStateUpdated(int r1, int r2);
    }

    public interface OnSignalBarsUpdatedListener {
        void onSignalBarsUpdated(int r1);
    }

    public interface OnSignalingDataListener {
        void onSignalingData(byte[] r1);
    }

    public interface OnStateUpdatedListener {
        void onStateUpdated(int r1, boolean r2);
    }

    public static final class Proxy {
        public final String host;
        public final String login;
        public final String password;
        public final int port;

        public Proxy(String r1, int r2, String r3, String r4) {
            this.host = r1;
            this.port = r2;
            this.login = r3;
            this.password = r4;
        }

        public String toString() {
            return "Proxy{host='" + this.host + "', port=" + this.port + ", login='" + this.login + "', password='" + this.password + "'}";
        }
    }

    public static final class ServerConfig {
        public final boolean enableStunMarking;
        public final boolean enable_h264_decoder;
        public final boolean enable_h264_encoder;
        public final boolean enable_h265_decoder;
        public final boolean enable_h265_encoder;
        public final boolean enable_vp8_decoder;
        public final boolean enable_vp8_encoder;
        public final boolean enable_vp9_decoder;
        public final boolean enable_vp9_encoder;
        public final double hangupUiTimeout;
        private final JSONObject jsonObject;
        public final boolean useSystemAec;
        public final boolean useSystemNs;

        /* renamed from: -$$Nest$fgetjsonObject, reason: not valid java name */
        public static /* bridge */ /* synthetic */ JSONObject m5278$$Nest$fgetjsonObject(ServerConfig r0) {
            return r0.jsonObject;
        }

        private ServerConfig(JSONObject r5) {
            this.jsonObject = r5;
            this.useSystemNs = r5.optBoolean("use_system_ns", true);
            this.useSystemAec = r5.optBoolean("use_system_aec", true);
            this.enableStunMarking = r5.optBoolean("voip_enable_stun_marking", false);
            this.hangupUiTimeout = r5.optDouble("hangup_ui_timeout", 5.0d);
            this.enable_vp8_encoder = r5.optBoolean("enable_vp8_encoder", true);
            this.enable_vp8_decoder = r5.optBoolean("enable_vp8_decoder", true);
            this.enable_vp9_encoder = r5.optBoolean("enable_vp9_encoder", true);
            this.enable_vp9_decoder = r5.optBoolean("enable_vp9_decoder", true);
            this.enable_h265_encoder = r5.optBoolean("enable_h265_encoder", true);
            this.enable_h265_decoder = r5.optBoolean("enable_h265_decoder", true);
            this.enable_h264_encoder = r5.optBoolean("enable_h264_encoder", true);
            this.enable_h264_decoder = r5.optBoolean("enable_h264_decoder", true);
        }

        public /* synthetic */ ServerConfig(JSONObject r1, InstanceIA r2) {
            this(r1);
        }

        public String getString(String r2) {
            return getString(r2, _UrlKt.FRAGMENT_ENCODE_SET);
        }

        public String getString(String r1, String r2) {
            return this.jsonObject.optString(r1, r2);
        }
    }

    public static final class TrafficStats {
        public final long bytesReceivedMobile;
        public final long bytesReceivedWifi;
        public final long bytesSentMobile;
        public final long bytesSentWifi;

        public TrafficStats(long r1, long r3, long r5, long r7) {
            this.bytesSentWifi = r1;
            this.bytesReceivedWifi = r3;
            this.bytesSentMobile = r5;
            this.bytesReceivedMobile = r7;
        }

        public String toString() {
            return "TrafficStats{bytesSentWifi=" + this.bytesSentWifi + ", bytesReceivedWifi=" + this.bytesReceivedWifi + ", bytesSentMobile=" + this.bytesSentMobile + ", bytesReceivedMobile=" + this.bytesReceivedMobile + '}';
        }
    }

    static {
        globalServerConfig = new ServerConfig(new JSONObject(), null);
    }

    private Instance() {
    }

    private static void checkHasDelegate() {
        if (instance == null) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("tgvoip version is not set");
    }

    public static void destroyInstance() {
        instance = null;
    }

    public static int getConnectionMaxLayer() {
        return 92;
    }

    public static ServerConfig getGlobalServerConfig() {
        return globalServerConfig;
    }

    public static NativeInstance makeInstance(String r1, Config r2, String r3, Endpoint[] r4, Proxy r5, int r6, EncryptionKey r7, VideoSink r8, long r9, NativeInstance.AudioLevelsCallback r11) {
        if ("2.4.4".equals(r1) == true) goto L5;
        ContextUtils.initialize(ApplicationLoader.applicationContext);
    L5:
        instance = NativeInstance.make(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11);
        setGlobalServerConfig(ServerConfig.m5278$$Nest$fgetjsonObject(globalServerConfig).toString());
        setBufferSize(bufferSize);
        return instance;
    }

    public static void setBufferSize(int r1) {
        bufferSize = r1;
        NativeInstance r0 = instance;
        if (r0 == null) goto L6;
        r0.setBufferSize(r1);
        return;
    }

    public static void setGlobalServerConfig(String r3) {
        globalServerConfig = new ServerConfig(new JSONObject(r3), null);     // Catch: JSONException -> L6
        NativeInstance r0 = instance;     // Catch: JSONException -> L6
        if (r0 == null) goto L13;
        r0.setGlobalServerConfig(r3);     // Catch: JSONException -> L6
        return;
    L13:
        return;
    L6:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("failed to parse tgvoip server config", e);
        return;
    }
}
