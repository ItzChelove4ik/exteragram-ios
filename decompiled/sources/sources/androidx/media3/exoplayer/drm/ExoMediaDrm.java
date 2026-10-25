package androidx.media3.exoplayer.drm;

import androidx.media3.common.DrmInitData;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.exoplayer.analytics.PlayerId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes4.dex */
public interface ExoMediaDrm {

    public static final class KeyRequest {
        private final byte[] data;
        private final String licenseServerUrl;
        private final int requestType;

        public KeyRequest(byte[] r1, String r2, int r3) {
            this.data = r1;
            this.licenseServerUrl = r2;
            this.requestType = r3;
        }

        public byte[] getData() {
            return this.data;
        }

        public String getLicenseServerUrl() {
            return this.licenseServerUrl;
        }
    }

    public interface OnEventListener {
        void onEvent(ExoMediaDrm r1, byte[] r2, int r3, int r4, byte[] r5);
    }

    public interface Provider {
        ExoMediaDrm acquireExoMediaDrm(UUID r1);
    }

    public static final class ProvisionRequest {
        private final byte[] data;
        private final String defaultUrl;

        public ProvisionRequest(byte[] r1, String r2) {
            this.data = r1;
            this.defaultUrl = r2;
        }

        public byte[] getData() {
            return this.data;
        }

        public String getDefaultUrl() {
            return this.defaultUrl;
        }
    }

    void closeSession(byte[] r1);

    CryptoConfig createCryptoConfig(byte[] r1);

    int getCryptoType();

    KeyRequest getKeyRequest(byte[] r1, List<DrmInitData.SchemeData> r2, int r3, HashMap<String, String> r4);

    ProvisionRequest getProvisionRequest();

    byte[] openSession();

    byte[] provideKeyResponse(byte[] r1, byte[] r2);

    void provideProvisionResponse(byte[] r1);

    Map<String, String> queryKeyStatus(byte[] r1);

    void release();

    boolean requiresSecureDecoder(byte[] r1, String r2);

    void restoreKeys(byte[] r1, byte[] r2);

    void setOnEventListener(OnEventListener r1);

    default void setPlayerIdForSession(byte[] r1, PlayerId r2) {
    }
}
