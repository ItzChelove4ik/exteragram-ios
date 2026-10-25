package androidx.media3.exoplayer.mediacodec;

import android.annotation.SuppressLint;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.telegram.messenger.MediaController;

@SuppressLint({"InlinedApi"})
/* loaded from: classes4.dex */
public abstract class MediaCodecUtil {
    private static final HashMap<CodecKey, List<MediaCodecInfo>> decoderInfosCache = null;
    private static int maxH264DecodableFrameSize;

    /* renamed from: androidx.media3.exoplayer.mediacodec.MediaCodecUtil$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class CodecKey {
        public final String mimeType;
        public final boolean secure;
        public final boolean tunneling;

        public CodecKey(String r1, boolean r2, boolean r3) {
            this.mimeType = r1;
            this.secure = r2;
            this.tunneling = r3;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L17:
            return false;
        L8:
            if (r5.getClass() != CodecKey.class) goto L17;
            CodecKey r52 = (CodecKey) r5;
            if (TextUtils.equals(this.mimeType, r52.mimeType) == false) goto L17;
            if (this.secure != r52.secure) goto L17;
            if (this.tunneling != r52.tunneling) goto L17;
            return true;
        }

        public int hashCode() {
            int r0 = (this.mimeType.hashCode() + 31) * 31;
            int r3 = 1237;
            if (this.secure == false) goto L5;
            int r2 = 1231;
        L6:
            int r02 = (r0 + r2) * 31;
            if (this.tunneling == false) goto L10;
            r3 = 1231;
        L10:
            return r02 + r3;
        L5:
            r2 = 1237;
            goto L6
        }
    }

    public static class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable r2) {
            super("Failed to query underlying media codecs", r2);
        }

        public /* synthetic */ DecoderQueryException(Throwable r1, AnonymousClass1 r2) {
            this(r1);
        }
    }

    public interface MediaCodecListCompat {
        int getCodecCount();

        android.media.MediaCodecInfo getCodecInfoAt(int r1);

        boolean isFeatureRequired(String r1, String r2, MediaCodecInfo.CodecCapabilities r3);

        boolean isFeatureSupported(String r1, String r2, MediaCodecInfo.CodecCapabilities r3);

        boolean secureDecodersExplicit();
    }

    public static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        private android.media.MediaCodecInfo[] mediaCodecInfos;

        public MediaCodecListCompatV21(boolean r1, boolean r2, boolean r3) {
            if (r1 == true) goto L8;
            if (r2 == true) goto L8;
            if (r3 == true) goto L8;
            int r12 = 0;
        L9:
            this.codecKind = r12;
            return;
        L8:
            r12 = 1;
            goto L9
        }

        @EnsuresNonNull({"mediaCodecInfos"})
        private void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos != null) goto L6;
            this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            return;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public android.media.MediaCodecInfo getCodecInfoAt(int r1) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[r1];
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean isFeatureRequired(String r1, String r2, MediaCodecInfo.CodecCapabilities r3) {
            return r3.isFeatureRequired(r1);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean isFeatureSupported(String r1, String r2, MediaCodecInfo.CodecCapabilities r3) {
            return r3.isFeatureSupported(r1);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean secureDecodersExplicit() {
            return true;
        }
    }

    public interface ScoreProvider<T> {
        int getScore(T r1);
    }

    public static /* synthetic */ int $r8$lambda$E5E12Pu4XTSi9W7eQh7w_l8iuGE(ScoreProvider r0, Object r1, Object r2) {
        return r0.getScore(r2) - r0.getScore(r1);
    }

    public static /* synthetic */ int $r8$lambda$HIwwfMhWN6vIS6F9FpBKO2gBSjA(MediaCodecInfo r1) {
        if (r1.softwareOnly == false) goto L5;
        int r0 = 2;
    L7:
        return r0 + (!r1.vendor ? 1 : 0);
    L5:
        r0 = 0;
        goto L7
    }

    public static /* synthetic */ int $r8$lambda$Z8dAbbPXfdC1pwNXxAZccc0tgoo(Format r0, MediaCodecInfo r1) {
        return r1.isFormatFunctionallySupported(r0) ? 1 : 0;
    }

    public static /* synthetic */ int $r8$lambda$jEgPfEqH7iCdJdHG3m68r3I9vbE(MediaCodecInfo r2) {
        String r22 = r2.name;
        if (r22.startsWith("OMX.google") == false) goto L5;
        return 1;
    L5:
        if (r22.startsWith("c2.android") == false) goto L8;
        return 1;
    L8:
        if (Build.VERSION.SDK_INT < 26) goto L10;
        return 0;
    L10:
        if (r22.equals("OMX.MTK.AUDIO.DECODER.RAW") == false) goto L17;
        return -1;
    L17:
        return 0;
    }

    static {
        decoderInfosCache = new HashMap();
        maxH264DecodableFrameSize = -1;
    }

    private static void applyWorkarounds(String r11, List<MediaCodecInfo> r12) {
        if ("audio/raw".equals(r11) == false) goto L15;
        if (Build.VERSION.SDK_INT < 26) goto L7;
    L13:
        sortByScore(r12, new MediaCodecUtil$$ExternalSyntheticLambda1());
        goto L15
    L7:
        if (Build.DEVICE.equals("R9") == false) goto L13;
        if (r12.size() != 1) goto L13;
        if (r12.get(0).name.equals("OMX.MTK.AUDIO.DECODER.RAW") == false) goto L13;
        r12.add(MediaCodecInfo.newInstance("OMX.google.raw.decoder", "audio/raw", "audio/raw", null, false, true, false, false, false));
    L15:
        if (Build.VERSION.SDK_INT < 32) goto L17;
        return;
    L17:
        if (r12.size() > 1) goto L19;
        return;
    L19:
        if ("OMX.qti.audio.decoder.flac".equals(r12.get(0).name) == false) goto L24;
        r12.add(r12.remove(0));
        return;
    }

    public static String getAlternativeCodecMimeType(Format r3) {
        if ("audio/eac3-joc".equals(r3.sampleMimeType) == false) goto L7;
        return "audio/eac3";
    L7:
        if ("video/dolby-vision".equals(r3.sampleMimeType) == false) goto L25;
        Pair<Integer, Integer> r0 = getCodecProfileAndLevel(r3);
        if (r0 == null) goto L25;
        int r02 = ((Integer) r0.first).intValue();
        if (r02 != 16) goto L13;
    L23:
        return "video/hevc";
    L13:
        if (r02 == 256) goto L23;
        if (r02 != 512) goto L20;
        return MediaController.VIDEO_MIME_TYPE;
    L20:
        if (r02 != 1024) goto L25;
        return "video/av01";
    L25:
        if ("video/mv-hevc".equals(r3.sampleMimeType) == false) goto L27;
        return "video/hevc";
    L27:
        return null;
    }

    public static List<MediaCodecInfo> getAlternativeDecoderInfos(MediaCodecSelector r0, Format r1, boolean r2, boolean r3) {
        String r12 = getAlternativeCodecMimeType(r1);
        if (r12 != null) goto L7;
        return ImmutableList.of();
    L7:
        return r0.getDecoderInfos(r12, r2, r3);
    }

    private static String getCodecMimeType(android.media.MediaCodecInfo r4, String r5, String r6) {
        String[] r42 = r4.getSupportedTypes();
        int r0 = r42.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        String r2 = r42[r1];
        if (r2.equalsIgnoreCase(r6) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r2;
    L9:
        if (r6.equals("video/dolby-vision") == false) goto L21;
        if ("OMX.MS.HEVCDV.Decoder".equals(r5) == false) goto L15;
        return "video/hevcdv";
    L15:
        if ("OMX.RTK.video.decoder".equals(r5) == false) goto L17;
        return "video/dv_hevc";
    L17:
        if ("OMX.realtek.video.decoder.tunneled".equals(r5) == true) goto L50;
        return null;
    L50:
        return "video/dv_hevc";
    L21:
        if (r6.equals("video/mv-hevc") == false) goto L29;
        if ("c2.qti.mvhevc.decoder".equals(r5) == false) goto L25;
        return "video/x-mvhevc";
    L25:
        if ("c2.qti.mvhevc.decoder.secure".equals(r5) == false) goto L52;
        return "video/x-mvhevc";
    L52:
        return null;
    L29:
        if (r6.equals("audio/alac") == false) goto L35;
        if ("OMX.lge.alac.decoder".equals(r5) == false) goto L35;
        return "audio/x-lg-alac";
    L35:
        if (r6.equals("audio/flac") == false) goto L41;
        if ("OMX.lge.flac.decoder".equals(r5) == false) goto L41;
        return "audio/x-lg-flac";
    L41:
        if (r6.equals("audio/ac3") == true) goto L43;
        return null;
    L43:
        if ("OMX.lge.ac3.decoder".equals(r5) == false) goto L54;
        return "audio/lg-ac3";
    L54:
        return null;
    }

    @Deprecated
    public static Pair<Integer, Integer> getCodecProfileAndLevel(Format r0) {
        return CodecSpecificDataUtil.getCodecProfileAndLevel(r0);
    }

    public static MediaCodecInfo getDecoderInfo(String r0, boolean r1, boolean r2) {
        List<MediaCodecInfo> r02 = getDecoderInfos(r0, r1, r2);
        if (r02.isEmpty() == false) goto L7;
        return null;
    L7:
        return r02.get(0);
    }

    public static synchronized List<MediaCodecInfo> getDecoderInfos(String r5, boolean r6, boolean r7) {
        monitor-enter(MediaCodecUtil.class);
        CodecKey r1 = new CodecKey(r5, r6, r7);     // Catch: Throwable -> L11
        HashMap<CodecKey, List<MediaCodecInfo>> r2 = decoderInfosCache;     // Catch: Throwable -> L11
        List<MediaCodecInfo> r3 = r2.get(r1);     // Catch: Throwable -> L11
        if (r3 == null) goto L8;
        monitor-exit(MediaCodecUtil.class);
        return r3;
    L8:
        ArrayList<MediaCodecInfo> r72 = getDecoderInfosInternal(r1, new MediaCodecListCompatV21(r6, r7, r5.equals("video/mv-hevc")));     // Catch: Throwable -> L11
        if (r6 == false) goto L13;
        r72.isEmpty();     // Catch: Throwable -> L11
    L13:
        applyWorkarounds(r5, r72);     // Catch: Throwable -> L11
        ImmutableList r52 = ImmutableList.copyOf(r72);     // Catch: Throwable -> L11
        r2.put(r1, r52);     // Catch: Throwable -> L11
        monitor-exit(MediaCodecUtil.class);
        return r52;
    L11:
        th = move-exception;
        throw th;
    }

    private static ArrayList<MediaCodecInfo> getDecoderInfosInternal(CodecKey r16, MediaCodecListCompat r17) {
        MediaCodecListCompat r1 = r17;
        ArrayList<MediaCodecInfo> r4 = new ArrayList();     // Catch: Exception -> L62
        String r6 = r16.mimeType;     // Catch: Exception -> L62
        int r14 = r1.getCodecCount();     // Catch: Exception -> L62
        boolean r15 = r1.secureDecodersExplicit();     // Catch: Exception -> L62
        int r5 = 0;
    L4:
        if (r5 >= r14) goto L61;
        android.media.MediaCodecInfo r7 = r1.getCodecInfoAt(r5);     // Catch: Exception -> L62
        if (isAlias(r7) == false) goto L8;
        int r12 = r5;
    L56:
        r5 = r12 + 1;
        r1 = r17;
        goto L4
    L8:
        int r8 = r5;
        String r52 = r7.getName();     // Catch: Exception -> L62
        if (isCodecUsableDecoder(r7, r52, r15, r6) == true) goto L11;
    L10:
        r12 = r8;
        goto L56
    L11:
        String r9 = getCodecMimeType(r7, r52, r6);     // Catch: Exception -> L62
        if (r9 == null) goto L10;
        MediaCodecInfo.CodecCapabilities r82 = r7.getCapabilitiesForType(r9);     // Catch: Exception -> L57
        boolean r11 = r1.isFeatureSupported("tunneled-playback", r9, r82);     // Catch: Exception -> L57
        boolean r122 = r1.isFeatureRequired("tunneled-playback", r9, r82);     // Catch: Exception -> L57
        boolean r13 = r16.tunneling;     // Catch: Exception -> L57
        if (r13 == true) goto L18;
        if (r122 == false) goto L18;
    L20:
        r12 = r8;
    L18:
        if (r13 == false) goto L21;
        if (r11 == false) goto L20;
    L21:
        boolean r112 = r1.isFeatureSupported("secure-playback", r9, r82);     // Catch: Exception -> L57
        boolean r123 = r1.isFeatureRequired("secure-playback", r9, r82);     // Catch: Exception -> L57
        boolean r132 = r16.secure;     // Catch: Exception -> L57
        if (r132 == true) goto L24;
        if (r123 == true) goto L20;
    L24:
        if (r132 == false) goto L73;
        if (r112 == false) goto L20;
    L73:
        boolean r92 = isHardwareAccelerated(r7, r6);     // Catch: Exception -> L34
        boolean r10 = isSoftwareOnly(r7, r6);     // Catch: Exception -> L34
        boolean r72 = isVendor(r7);     // Catch: Exception -> L34
        if (r15 == true) goto L31;
    L36:
        if (r15 == false) goto L38;
    L45:
        String r73 = r9;
        r12 = r8;
        if (r15 == true) goto L56;
        if (r112 == false) goto L56;
        r4.add(MediaCodecInfo.newInstance(r52 + ".secure", r6, r73, r82, r92, r10, r72, false, true));     // Catch: Exception -> L52
        return r4;
    L52:
        e = e;
        r52 = r52;
    L59:
        Log.e("MediaCodecUtil", "Failed to query codec " + r52 + " (" + r73 + ")");     // Catch: Exception -> L62
        throw e;     // Catch: Exception -> L62
    L54:
        e = e;
        goto L59
    L38:
        if (r16.secure == true) goto L45;
    L33:
        r73 = r9;
        r12 = r8;
        r4.add(MediaCodecInfo.newInstance(r52, r6, r73, r82, r92, r10, r72, false, false));     // Catch: Exception -> L43
    L43:
        e = e;
        goto L59
    L31:
        if (r16.secure == r112) goto L33;
    L34:
        e = e;
        r73 = r9;
    L57:
        e = e;
        r73 = r9;
        goto L59
    L61:
        return r4;
    L62:
        e = move-exception;
        throw new DecoderQueryException(e, null);
    }

    @RequiresNonNull({"#2.sampleMimeType"})
    public static List<MediaCodecInfo> getDecoderInfosSoftMatch(MediaCodecSelector r1, Format r2, boolean r3, boolean r4) {
        List<MediaCodecInfo> r0 = r1.getDecoderInfos(r2.sampleMimeType, r3, r4);
        List<MediaCodecInfo> r12 = getAlternativeDecoderInfos(r1, r2, r3, r4);
        return ImmutableList.builder().addAll(r0).addAll(r12).build();
    }

    public static List<MediaCodecInfo> getDecoderInfosSortedByFormatSupport(List<MediaCodecInfo> r1, Format r2) {
        ArrayList r0 = new ArrayList(r1);
        sortByScore(r0, new MediaCodecUtil$$ExternalSyntheticLambda2(r2));
        return r0;
    }

    public static List<MediaCodecInfo> getDecoderInfosSortedBySoftwareOnly(List<MediaCodecInfo> r1) {
        ArrayList r0 = new ArrayList(r1);
        sortByScore(r0, new MediaCodecUtil$$ExternalSyntheticLambda0());
        return ImmutableList.copyOf(r0);
    }

    public static MediaCodecInfo getDecryptOnlyDecoderInfo() {
        return getDecoderInfo("audio/raw", false, false);
    }

    public static Pair<Integer, Integer> getHevcBaseLayerCodecProfileAndLevel(Format r3) {
        String r0 = NalUnitUtil.getH265BaseLayerCodecsString(r3.initializationData);
        if (r0 != null) goto L7;
        return null;
    L7:
        return CodecSpecificDataUtil.getHevcProfileAndLevel(r0, Util.split(r0.trim(), "\\."), r3.colorInfo);
    }

    private static boolean isAlias(android.media.MediaCodecInfo r2) {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
        return false;
    L5:
        if (isAliasV29(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isAliasV29(android.media.MediaCodecInfo r0) {
        return r0.isAlias();
    }

    private static boolean isCodecUsableDecoder(android.media.MediaCodecInfo r0, String r1, boolean r2, String r3) {
        if (r0.isEncoder() == true) goto L10;
        if (r2 == false) goto L6;
        return true;
    L6:
        if (r1.endsWith(".secure") == true) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public static boolean isHardwareAccelerated(android.media.MediaCodecInfo r2, String r3) {
        if (Build.VERSION.SDK_INT < 29) goto L7;
        return isHardwareAcceleratedV29(r2);
    L7:
        return !isSoftwareOnly(r2, r3);
    }

    private static boolean isHardwareAcceleratedV29(android.media.MediaCodecInfo r0) {
        return r0.isHardwareAccelerated();
    }

    private static boolean isSoftwareOnly(android.media.MediaCodecInfo r2, String r3) {
        if (Build.VERSION.SDK_INT < 29) goto L7;
        return isSoftwareOnlyV29(r2);
    L7:
        if (MimeTypes.isAudio(r3) == false) goto L9;
        return true;
    L9:
        String r22 = Ascii.toLowerCase(r2.getName());
        if (r22.startsWith("arc.") == false) goto L13;
        return false;
    L13:
        if (r22.startsWith("omx.google.") == false) goto L15;
    L32:
        return true;
    L15:
        if (r22.startsWith("omx.ffmpeg.") == true) goto L32;
        if (r22.startsWith("omx.sec.") == false) goto L21;
        if (r22.contains(".sw.") == true) goto L32;
    L21:
        if (r22.equals("omx.qcom.video.decoder.hevcswvdec") == true) goto L32;
        if (r22.startsWith("c2.android.") == true) goto L32;
        if (r22.startsWith("c2.google.") == true) goto L32;
        if (r22.startsWith("omx.") == false) goto L29;
    L31:
        return false;
    L29:
        if (r22.startsWith("c2.") == true) goto L31;
        goto L31
    }

    private static boolean isSoftwareOnlyV29(android.media.MediaCodecInfo r0) {
        return r0.isSoftwareOnly();
    }

    private static boolean isVendor(android.media.MediaCodecInfo r2) {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
        String r22 = Ascii.toLowerCase(r2.getName());
        if (r22.startsWith("omx.google.") == false) goto L9;
        return false;
    L9:
        if (r22.startsWith("c2.android.") == false) goto L11;
        return false;
    L11:
        if (r22.startsWith("c2.google.") == true) goto L17;
        return true;
    L17:
        return false;
    L5:
        return isVendorV29(r2);
    }

    private static boolean isVendorV29(android.media.MediaCodecInfo r0) {
        return r0.isVendor();
    }

    private static <T> void sortByScore(List<T> r1, ScoreProvider<T> r2) {
        Collections.sort(r1, new MediaCodecUtil$$ExternalSyntheticLambda3(r2));
    }
}
