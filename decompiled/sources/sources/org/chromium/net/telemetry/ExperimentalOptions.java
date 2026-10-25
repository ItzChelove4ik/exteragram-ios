package org.chromium.net.telemetry;

import android.util.Log;
import androidx.camera.core.impl.Quirks$$ExternalSyntheticBackport0;
import com.exteragram.messenger.backup.PreferencesUtils$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes5.dex */
public final class ExperimentalOptions {
    private static final String TAG = "ExperimentalOptions";
    public static final Set<String> VALID_CONNECTION_OPTIONS = null;
    private JSONObject mJson;

    static {
        VALID_CONNECTION_OPTIONS = PreferencesUtils$$ExternalSyntheticBackport0.m(new String[]{"CHLO", "SHLO", "SCFG", "REJ", "CETV", "PRST", "SCUP", "ALPN", "P256", "C255", "AESG", "CC20", "QBIC", "AFCW", "IFW5", "IFW6", "IFW7", "IFW8", "IFW9", "IFWA", "TBBR", "1RTT", "2RTT", "LRTT", "BBS1", "BBS2", "BBS3", "BBS4", "BBS5", "BBRR", "BBR1", "BBR2", "BBR3", "BBR4", "BBR5", "BBR9", "BBRA", "BBRB", "BBRS", "BBQ1", "BBQ2", "BBQ3", "BBQ5", "BBQ6", "BBQ7", "BBQ8", "BBQ9", "BBQ0", "RENO", "TPCC", "BYTE", "IW03", "IW10", "IW20", "IW50", "B2ON", "B2NA", "B2NE", "B2RP", "B2LO", "B2HR", "B2SL", "B2H2", "B2RC", "BSAO", "B2DL", "B201", "B202", "B203", "B204", "B205", "B206", "B207", "NTLP", "1TLP", "1RTO", "NRTO", "TIME", "ATIM", "MIN1", "MIN4", "MAD0", "MAD2", "MAD3", "1ACK", "AKD3", "AKDU", "AFFE", "AFF1", "AFF2", "SSLR", "NPRR", "2RTO", "3RTO", "4RTO", "5RTO", "6RTO", "CBHD", "NBHD", "CONH", "LFAK", "STMP", "EACK", "ILD0", "ILD1", "ILD2", "ILD3", "ILD4", "RUNT", "NSTP", "NRTT", "1PTO", "2PTO", "6PTO", "7PTO", "8PTO", "PTOS", "PTOA", "PEB1", "PEB2", "PVS1", "PAG1", "PAG2", "PSDA", "PLE1", "PLE2", "APTO", "ELDT", "RVCM", "TCID", "MPTH", "NCMR", "DFER", "NPCO", "BWRE", "BWMX", "BWID", "BWI1", "BWRS", "BWS2", "BWS3", "BWS4", "BWS5", "BWS6", "BWP0", "BWP1", "BWP2", "BWP3", "BWP4", "BWG4", "BWG7", "BWG8", "BWS7", "BWM3", "BWM4", "ICW1", "DTOS", "FIDT", "3AFF", "10AF", "MTUH", "MTUL", "NSLC", "NCHP", "NBPE", "X509", "X59R", "CHID", "VER ", "NONC", "NONP", "KEXS", "AEAD", "COPT", "CLOP", "ICSL", "MIBS", "MIUS", "ADE ", "IRTT", "TRTT", "SNI ", "PUBS", "SCID", "ORBT", "PDMD", "PROF", "CCRT", "EXPY", "STTL", "SFCW", "CFCW", "UAID", "XLCT", "QLVE", "PDP1", "PDP2", "PDP3", "PDP5", "QNZ2", "MAD", "IGNP", "SRWP", "ROWF", "ROWR", "GSR0", "GSR1", "GSR2", "GSR3", "NRES", "INVC", "GWCH", "YTCH", "ACH0", "RREJ", "CADR", "ASAD", "SRST", "CIDK", "CIDS", "RNON", "RSEQ", "PAD ", "EPID", "SNO0", "STK0", "CRT255", "CSCT"});
    }

    public ExperimentalOptions(String r2) {
        this.mJson = new JSONObject();
        if (isNullOrEmpty(r2) == true) goto L13;
        this.mJson = (JSONObject) new JSONTokener(r2).nextValue();     // Catch: Throwable -> L6
        return;
    L6:
        e = move-exception;
        String r22 = TAG;
        if (Log.isLoggable(r22, 2) == false) goto L14;
        Log.v(r22, String.format("Experimental options could not be parsed, using default values. Error: %s", new Object[]{e.getMessage()}));
        return;
    L14:
        return;
    }

    private <T> T getOrDefault(String r2, T r3, Class<T> r4) {
        if (this.mJson.length() != 0) goto L14;
    L13:
        return r3;
    L14:
        return r4.cast(this.mJson.get(r2));
    L7:
        e = move-exception;
        String r42 = TAG;
        if (Log.isLoggable(r42, 2) == false) goto L13;
        Log.v(r42, String.format("Failed to get %s options: %s", new Object[]{r2, e.getMessage()}));
        goto L13
    }

    private <T> T getOrDefault(String r5, String r6, T r7, Class<T> r8) {
        if (this.mJson.length() != 0) goto L26;
        return r7;
    L26:
        JSONObject r4 = this.mJson.getJSONObject(r5);     // Catch: JSONException -> L8
    L13:
        if (r4 != null) goto L15;
        return r7;
    L15:
        if (r4.length() == 0) goto L33;
        return r8.cast(r4.get(r6));
    L19:
        e = move-exception;
        String r52 = TAG;
        if (Log.isLoggable(r52, 2) == false) goto L31;
        Log.v(r52, String.format("Failed to get %s options: %s", new Object[]{r6, e.getMessage()}));
        return r7;
    L31:
        return r7;
    L33:
        return r7;
    L8:
        e = move-exception;
        String r2 = TAG;
        if (Log.isLoggable(r2, 2) == false) goto L12;
        Log.v(r2, String.format("Failed to get %s options: %s", new Object[]{r5, e.getMessage()}));
    L12:
        r4 = null;
        goto L13
    }

    private boolean isNullOrEmpty(String r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if (r1.isEmpty() == true) goto L10;
        return false;
    L10:
        return true;
    }

    private String parseExperimentalOptionsString(String r7) {
        if (isNullOrEmpty(r7) == false) goto L5;
        return r7;
    L5:
        ArrayList r6 = new ArrayList();
        String[] r72 = r7.split(",", -1);
        int r0 = r72.length;
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L12;
        String r3 = r72[r2];
        if (VALID_CONNECTION_OPTIONS.contains(r3.toUpperCase(Locale.ROOT).trim()) == false) goto L10;
        r6.add(r3);
    L10:
        r2 = r2 + 1;
        goto L6
    L12:
        return Quirks$$ExternalSyntheticBackport0.m(",", r6);
    }

    public OptionalBoolean getAllowPortMigration() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("QUIC", "allow_port_migration", null, Boolean.class));
    }

    public OptionalBoolean getAsyncDnsEnableOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("AsyncDNS", "enable", null, Boolean.class));
    }

    public OptionalBoolean getCloseSessionsOnIpChangeOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("QUIC", "close_sessions_on_ip_change", null, Boolean.class));
    }

    public String getConnectionOptionsOption() {
        return parseExperimentalOptionsString((String) getOrDefault("QUIC", "connection_options", null, String.class));
    }

    public OptionalBoolean getDisableBidirectionalStreamsOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("QUIC", "disable_bidirectional_streams", null, Boolean.class));
    }

    public OptionalBoolean getDisableIpv6OnWifiOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("disable_ipv6_on_wifi", null, Boolean.class));
    }

    public OptionalBoolean getEnableSocketRecvOptimizationOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("QUIC", "enable_socket_recv_optimization", null, Boolean.class));
    }

    public OptionalBoolean getGoawaySessionsOnIpChangeOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("QUIC", "goaway_sessions_on_ip_change", null, Boolean.class));
    }

    public String getHostWhitelist() {
        return (String) getOrDefault("QUIC", "host_whitelist", null, String.class);
    }

    public int getIdleConnectionTimeoutSecondsOption() {
        return ((Integer) getOrDefault("QUIC", "idle_connection_timeout_seconds", -1, Integer.class)).intValue();
    }

    public int getMaxIdleTimeBeforeCryptoHandshakeSecondsOption() {
        return ((Integer) getOrDefault("QUIC", "max_idle_time_before_crypto_handshake_seconds", -1, Integer.class)).intValue();
    }

    public int getMaxServerConfigsStoredInPropertiesOption() {
        return ((Integer) getOrDefault("QUIC", "max_server_configs_stored_in_properties", -1, Integer.class)).intValue();
    }

    public int getMaxTimeBeforeCryptoHandshakeSecondsOption() {
        return ((Integer) getOrDefault("QUIC", "max_time_before_crypto_handshake_seconds", -1, Integer.class)).intValue();
    }

    public OptionalBoolean getMigrateSessionsEarlyV2() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("QUIC", "migrate_sessions_early_v2", null, Boolean.class));
    }

    public OptionalBoolean getMigrateSessionsOnNetworkChangeV2Option() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("QUIC", "migrate_sessions_on_network_change_v2", null, Boolean.class));
    }

    public OptionalBoolean getRaceStaleDnsOnConnection() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("QUIC", "race_stale_dns_on_connection", null, Boolean.class));
    }

    public OptionalBoolean getStaleDnsAllowOtherNetworkOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("StaleDNS", "allow_other_network", null, Boolean.class));
    }

    public int getStaleDnsDelayMillisOption() {
        return ((Integer) getOrDefault("StaleDNS", "delay_ms", -1, Integer.class)).intValue();
    }

    public OptionalBoolean getStaleDnsEnableOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("StaleDNS", "enable", null, Boolean.class));
    }

    public int getStaleDnsMaxExpiredTimeMillisOption() {
        return ((Integer) getOrDefault("StaleDNS", "max_expired_time_ms", -1, Integer.class)).intValue();
    }

    public int getStaleDnsMaxStaleUsesOption() {
        return ((Integer) getOrDefault("StaleDNS", "max_stale_uses", -1, Integer.class)).intValue();
    }

    public int getStaleDnsPersistDelayMillisOption() {
        return ((Integer) getOrDefault("StaleDNS", "persist_delay_ms", -1, Integer.class)).intValue();
    }

    public OptionalBoolean getStaleDnsPersistToDiskOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("StaleDNS", "persist_to_disk", null, Boolean.class));
    }

    public OptionalBoolean getStaleDnsUseStaleOnNameNotResolvedOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("StaleDNS", "use_stale_on_name_not_resolved", null, Boolean.class));
    }

    public OptionalBoolean getStoreServerConfigsInPropertiesOption() {
        return OptionalBoolean.fromBoolean((Boolean) getOrDefault("QUIC", "store_server_configs_in_properties", null, Boolean.class));
    }

    public String getUserAgentId() {
        return (String) getOrDefault("QUIC", "user_agent_id", null, String.class);
    }
}
