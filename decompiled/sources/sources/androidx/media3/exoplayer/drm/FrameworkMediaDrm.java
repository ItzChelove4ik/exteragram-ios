package androidx.media3.exoplayer.drm;

import android.annotation.SuppressLint;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.metrics.LogSessionId;
import android.os.Build;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.analytics.PlayerId$LogSessionIdApi31$$ExternalSyntheticApiModelOutline0;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class FrameworkMediaDrm implements ExoMediaDrm {
    public static final ExoMediaDrm.Provider DEFAULT_PROVIDER = null;
    private final MediaDrm mediaDrm;
    private int referenceCount;
    private final UUID uuid;

    public static class Api31 {
        public static boolean requiresSecureDecoder(MediaDrm r0, String r1, int r2) {
            return r0.requiresSecureDecoder(r1, r2);
        }

        public static void setLogSessionIdOnMediaDrmSession(MediaDrm r1, byte[] r2, PlayerId r3) {
            LogSessionId r32 = r3.getLogSessionId();
            if (r32.equals(PlayerId$LogSessionIdApi31$$ExternalSyntheticApiModelOutline0.m()) == true) goto L6;
            FrameworkMediaDrm$Api31$$ExternalSyntheticApiModelOutline0.m(Assertions.checkNotNull(r1.getPlaybackComponent(r2))).setLogSessionId(r32);
            return;
        }
    }

    public static /* synthetic */ ExoMediaDrm $r8$lambda$DzWbix_Ud4a3ekWyswo40Jo03Ks(UUID r2) {
        return newInstance(r2);
    L4:
        Log.e("FrameworkMediaDrm", "Failed to instantiate a FrameworkMediaDrm for uuid: " + r2 + ".");
        return new DummyExoMediaDrm();
    }

    public static /* synthetic */ void $r8$lambda$FTMrYBm0_nl4l9MpEYMiZMFYRQE(FrameworkMediaDrm r0, ExoMediaDrm.OnEventListener r1, MediaDrm r2, byte[] r3, int r4, int r5, byte[] r6) {
        r0.getClass();
        r1.onEvent(r0, r3, r4, r5, r6);
    }

    static {
        DEFAULT_PROVIDER = new FrameworkMediaDrm$$ExternalSyntheticLambda0();
    }

    private FrameworkMediaDrm(UUID r4) {
        Assertions.checkNotNull(r4);
        Assertions.checkArgument(!C.COMMON_PSSH_UUID.equals(r4), "Use C.CLEARKEY_UUID instead");
        this.uuid = r4;
        MediaDrm r0 = new MediaDrm(adjustUuid(r4));
        this.mediaDrm = r0;
        this.referenceCount = 1;
        if (C.WIDEVINE_UUID.equals(r4) == true) goto L5;
        return;
    L5:
        if (needsForceWidevineL3Workaround() == false) goto L9;
        forceWidevineL3(r0);
        return;
    }

    private static byte[] addLaUrlAttributeIfMissing(byte[] r7) {
        ParsableByteArray r0 = new ParsableByteArray(r7);
        int r1 = r0.readLittleEndianInt();
        short r2 = r0.readLittleEndianShort();
        short r3 = r0.readLittleEndianShort();
        if (r2 != 1) goto L14;
        if (r3 != 1) goto L14;
        short r5 = r0.readLittleEndianShort();
        Charset r6 = StandardCharsets.UTF_16LE;
        String r02 = r0.readString(r5, r6);
        if (r02.contains("<LA_URL>") == false) goto L9;
        return r7;
    L9:
        int r72 = r02.indexOf("</DATA>");
        if (r72 != (-1)) goto L12;
        Log.w("FrameworkMediaDrm", "Could not find the </DATA> tag. Skipping LA_URL workaround.");
    L12:
        String r73 = r02.substring(0, r72) + "<LA_URL>https://x</LA_URL>" + r02.substring(r72);
        int r12 = r1 + 52;
        ByteBuffer r03 = ByteBuffer.allocate(r12);
        r03.order(ByteOrder.LITTLE_ENDIAN);
        r03.putInt(r12);
        r03.putShort(r2);
        r03.putShort(r3);
        r03.putShort((short) (r73.length() * 2));
        r03.put(r73.getBytes(r6));
        return r03.array();
    L14:
        Log.i("FrameworkMediaDrm", "Unexpected record count or type. Skipping LA_URL workaround.");
        return r7;
    }

    private String adjustLicenseServerUrl(String r4) {
        if ("<LA_URL>https://x</LA_URL>".equals(r4) == false) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (Build.VERSION.SDK_INT >= 33) goto L8;
    L14:
        return r4;
    L8:
        if ("https://default.url".equals(r4) == false) goto L14;
        String r3 = getPropertyString("version");
        if (Objects.equals(r3, "1.2") == false) goto L12;
    L13:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if (Objects.equals(r3, "aidl-1") == false) goto L14;
        goto L13
    }

    private static byte[] adjustRequestData(UUID r1, byte[] r2) {
        if (C.CLEARKEY_UUID.equals(r1) == true) goto L5;
        return r2;
    L5:
        return ClearKeyUtil.adjustRequestData(r2);
    }

    private static byte[] adjustRequestInitData(UUID r3, byte[] r4) {
        UUID r0 = C.PLAYREADY_UUID;
        if (r0.equals(r3) == false) goto L10;
        byte[] r1 = PsshAtomUtil.parseSchemeSpecificData(r4, r3);
        if (r1 == null) goto L8;
        r4 = r1;
    L8:
        r4 = PsshAtomUtil.buildPsshAtom(r0, addLaUrlAttributeIfMissing(r4));
    L10:
        if (cdmRequiresCommonPsshUuid(r3) == false) goto L15;
        PsshAtomUtil.PsshAtom r12 = PsshAtomUtil.parsePsshAtom(r4);
        if (r12 == null) goto L15;
        r4 = PsshAtomUtil.buildPsshAtom(C.COMMON_PSSH_UUID, r12.keyIds, r12.schemeData);
    L15:
        if (r0.equals(r3) == true) goto L17;
    L29:
        return r4;
    L17:
        if ("Amazon".equals(Build.MANUFACTURER) == false) goto L29;
        String r02 = Build.MODEL;
        if ("AFTB".equals(r02) == false) goto L21;
    L26:
        byte[] r32 = PsshAtomUtil.parseSchemeSpecificData(r4, r3);
        if (r32 == null) goto L29;
        return r32;
    L21:
        if ("AFTS".equals(r02) == true) goto L26;
        if ("AFTM".equals(r02) == true) goto L26;
        if ("AFTT".equals(r02) == false) goto L29;
        goto L26
    }

    private static String adjustRequestMimeType(UUID r2, String r3) {
        if (Build.VERSION.SDK_INT < 26) goto L5;
    L12:
        return r3;
    L5:
        if (C.CLEARKEY_UUID.equals(r2) == false) goto L12;
        if ("video/mp4".equals(r3) == false) goto L9;
        return "cenc";
    L9:
        if ("audio/mp4".equals(r3) == false) goto L12;
        return "cenc";
    }

    private static UUID adjustUuid(UUID r1) {
        if (cdmRequiresCommonPsshUuid(r1) == true) goto L5;
        return r1;
    L5:
        return C.COMMON_PSSH_UUID;
    }

    private static boolean cdmRequiresCommonPsshUuid(UUID r2) {
        if (Build.VERSION.SDK_INT < 27) goto L5;
        return false;
    L5:
        if (Objects.equals(r2, C.CLEARKEY_UUID) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static void forceWidevineL3(MediaDrm r2) {
        r2.setPropertyString("securityLevel", "L3");
    }

    private static DrmInitData.SchemeData getSchemeData(UUID r8, List<DrmInitData.SchemeData> r9) {
        if (C.WIDEVINE_UUID.equals(r8) == true) goto L7;
        return r9.get(0);
    L7:
        if (Build.VERSION.SDK_INT >= 28) goto L9;
    L26:
        int r82 = 0;
    L28:
        if (r82 >= r9.size()) goto L34;
        DrmInitData.SchemeData r1 = r9.get(r82);
        if (PsshAtomUtil.parseVersion((byte[]) Assertions.checkNotNull(r1.data)) == 1) goto L31;
        r82 = r82 + 1;
        goto L28
    L31:
        return r1;
    L34:
        return r9.get(0);
    L9:
        if (r9.size() <= 1) goto L26;
        DrmInitData.SchemeData r83 = r9.get(0);
        int r12 = 0;
        int r3 = 0;
    L12:
        if (r12 >= r9.size()) goto L20;
        DrmInitData.SchemeData r4 = r9.get(r12);
        byte[] r5 = (byte[]) Assertions.checkNotNull(r4.data);
        if (Objects.equals(r4.mimeType, r83.mimeType) == false) goto L26;
        if (Objects.equals(r4.licenseServerUrl, r83.licenseServerUrl) == false) goto L26;
        if (PsshAtomUtil.isPsshAtom(r5) == false) goto L26;
        r3 = r3 + r5.length;
        r12 = r12 + 1;
        goto L12
    L20:
        byte[] r13 = new byte[r3];
        int r2 = 0;
        int r32 = 0;
    L22:
        if (r2 >= r9.size()) goto L25;
        byte[] r42 = (byte[]) Assertions.checkNotNull(r9.get(r2).data);
        int r52 = r42.length;
        System.arraycopy(r42, 0, r13, r32, r52);
        r32 = r32 + r52;
        r2 = r2 + 1;
        goto L22
    L25:
        return r83.copyWithData(r13);
    }

    private boolean isMediaDrmRequiresSecureDecoderImplemented() {
        if (this.uuid.equals(C.WIDEVINE_UUID) == false) goto L17;
        String r2 = getPropertyString("version");
        if (r2.startsWith("v5.") == false) goto L7;
        return false;
    L7:
        if (r2.startsWith("14.") == false) goto L9;
        return false;
    L9:
        if (r2.startsWith("15.") == false) goto L11;
        return false;
    L11:
        if (r2.startsWith("16.0") == true) goto L20;
        return true;
    L20:
        return false;
    L17:
        return this.uuid.equals(C.CLEARKEY_UUID);
    }

    private static boolean needsForceWidevineL3Workaround() {
        return "ASUS_Z00AD".equals(Build.MODEL);
    }

    public static FrameworkMediaDrm newInstance(UUID r2) {
        return new FrameworkMediaDrm(r2);
    L7:
        e = move-exception;
        throw new UnsupportedDrmException(1, e);
    L4:
        e = move-exception;
        throw new UnsupportedDrmException(2, e);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public void closeSession(byte[] r1) {
        this.mediaDrm.closeSession(r1);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public /* bridge */ /* synthetic */ CryptoConfig createCryptoConfig(byte[] r1) {
        return createCryptoConfig(r1);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public FrameworkCryptoConfig createCryptoConfig(byte[] r2) {
        return new FrameworkCryptoConfig(adjustUuid(this.uuid), r2);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public int getCryptoType() {
        return 2;
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    @SuppressLint({"WrongConstant"})
    public ExoMediaDrm.KeyRequest getKeyRequest(byte[] r7, List<DrmInitData.SchemeData> r8, int r9, HashMap<String, String> r10) {
        if (r8 == null) goto L4;
        DrmInitData.SchemeData r82 = getSchemeData(this.uuid, r8);
        byte[] r0 = adjustRequestInitData(this.uuid, (byte[]) Assertions.checkNotNull(r82.data));
        byte[] r2 = r0;
        String r3 = adjustRequestMimeType(this.uuid, r82.mimeType);
    L5:
        MediaDrm.KeyRequest r72 = this.mediaDrm.getKeyRequest(r7, r2, r3, r9, r10);
        byte[] r92 = adjustRequestData(this.uuid, r72.getData());
        String r6 = adjustLicenseServerUrl(r72.getDefaultUrl());
        if (TextUtils.isEmpty(r6) == false) goto L12;
        if (r82 == null) goto L12;
        if (TextUtils.isEmpty(r82.licenseServerUrl) == true) goto L12;
        r6 = r82.licenseServerUrl;
    L12:
        return new ExoMediaDrm.KeyRequest(r92, r6, r72.getRequestType());
    L4:
        r82 = null;
        r2 = null;
        r3 = null;
        goto L5
    }

    public String getPropertyString(String r1) {
        return this.mediaDrm.getPropertyString(r1);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public ExoMediaDrm.ProvisionRequest getProvisionRequest() {
        MediaDrm.ProvisionRequest r2 = this.mediaDrm.getProvisionRequest();
        return new ExoMediaDrm.ProvisionRequest(r2.getData(), r2.getDefaultUrl());
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public byte[] openSession() {
        return this.mediaDrm.openSession();
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public byte[] provideKeyResponse(byte[] r3, byte[] r4) {
        if (C.CLEARKEY_UUID.equals(this.uuid) == false) goto L6;
        r4 = ClearKeyUtil.adjustResponseData(r4);
    L6:
        return this.mediaDrm.provideKeyResponse(r3, r4);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public void provideProvisionResponse(byte[] r1) {
        this.mediaDrm.provideProvisionResponse(r1);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public Map<String, String> queryKeyStatus(byte[] r1) {
        return this.mediaDrm.queryKeyStatus(r1);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public synchronized void release() {
        monitor-enter(this);
        int r0 = this.referenceCount - 1;
        this.referenceCount = r0;     // Catch: Throwable -> L7
        if (r0 != 0) goto L9;
        this.mediaDrm.release();     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public boolean requiresSecureDecoder(byte[] r4, String r5) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
    L8:
        MediaCrypto r0 = null;
        MediaCrypto r1 = new MediaCrypto(adjustUuid(this.uuid), r4);     // Catch: Throwable -> L16 MediaCryptoException -> L26
        boolean r3 = r1.requiresSecureDecoderComponent(r5);     // Catch: Throwable -> L13 MediaCryptoException -> L15
        r1.release();
        return r3;
    L15:
        r0 = r1;
    L13:
        th = th;
        r0 = r1;
    L23:
        if (r0 == null) goto L25;
        r0.release();
    L25:
        throw th;
    L16:
        th = th;
    L19:
        boolean r32 = !this.uuid.equals(C.CLEARKEY_UUID);
        if (r0 == null) goto L22;
        r0.release();
    L22:
        return r32;
    L5:
        if (isMediaDrmRequiresSecureDecoderImplemented() == false) goto L8;
        MediaDrm r33 = this.mediaDrm;
        return Api31.requiresSecureDecoder(r33, r5, r33.getSecurityLevel(r4));
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public void restoreKeys(byte[] r1, byte[] r2) {
        this.mediaDrm.restoreKeys(r1, r2);
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public void setOnEventListener(ExoMediaDrm.OnEventListener r3) {
        MediaDrm r0 = this.mediaDrm;
        if (r3 != null) goto L5;
        FrameworkMediaDrm$$ExternalSyntheticLambda1 r2 = null;
    L6:
        r0.setOnEventListener(r2);
        return;
    L5:
        r2 = new FrameworkMediaDrm$$ExternalSyntheticLambda1(this, r3);
        goto L6
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm
    public void setPlayerIdForSession(byte[] r3, PlayerId r4) {
        if (Build.VERSION.SDK_INT < 31) goto L10;
        Api31.setLogSessionIdOnMediaDrmSession(this.mediaDrm, r3, r4);     // Catch: UnsupportedOperationException -> L6
        return;
    L6:
        Log.w("FrameworkMediaDrm", "setLogSessionId failed.");
        return;
    }
}
