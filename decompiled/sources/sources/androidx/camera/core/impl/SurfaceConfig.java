package androidx.camera.core.impl;

import android.util.Size;
import androidx.camera.core.internal.utils.SizeUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0015\b\u0086\b\u0018\u0000 '2\u00020\u0001:\u0004'()*B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0000¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010$\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010\u0018¨\u0006+"}, d2 = {"Landroidx/camera/core/impl/SurfaceConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/SurfaceConfig$ConfigType;", "configType", "Landroidx/camera/core/impl/SurfaceConfig$ConfigSize;", "configSize", "Landroidx/camera/core/impl/StreamUseCase;", "streamUseCase", "<init>", "(Landroidx/camera/core/impl/SurfaceConfig$ConfigType;Landroidx/camera/core/impl/SurfaceConfig$ConfigSize;Landroidx/camera/core/impl/StreamUseCase;)V", "other", _UrlKt.FRAGMENT_ENCODE_SET, "isSupported", "(Landroidx/camera/core/impl/SurfaceConfig;)Z", "Landroidx/camera/core/impl/SurfaceSizeDefinition;", "definition", "Landroid/util/Size;", "getResolution", "(Landroidx/camera/core/impl/SurfaceSizeDefinition;)Landroid/util/Size;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/core/impl/SurfaceConfig$ConfigType;", "getConfigType", "()Landroidx/camera/core/impl/SurfaceConfig$ConfigType;", "Landroidx/camera/core/impl/SurfaceConfig$ConfigSize;", "getConfigSize", "()Landroidx/camera/core/impl/SurfaceConfig$ConfigSize;", "Landroidx/camera/core/impl/StreamUseCase;", "getStreamUseCase", "()Landroidx/camera/core/impl/StreamUseCase;", "imageFormat", "I", "getImageFormat", "Companion", "ConfigType", "ConfigSource", "ConfigSize", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSurfaceConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SurfaceConfig.kt\nandroidx/camera/core/impl/SurfaceConfig\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,385:1\n1222#2,2:386\n1252#2,4:388\n*S KotlinDebug\n*F\n+ 1 SurfaceConfig.kt\nandroidx/camera/core/impl/SurfaceConfig\n*L\n61#1:386,2\n61#1:388,4\n*E\n"})
/* loaded from: classes3.dex */
public final class SurfaceConfig {
    private static final Map<Integer, ConfigType> CONFIG_TYPES_BY_IMAGE_FORMAT = null;
    public static final Companion Companion = null;

    @JvmField
    public static final StreamUseCase DEFAULT_STREAM_USE_CASE = null;
    private static final ConfigSize[] FEATURE_COMBO_QUERY_SUPPORTED_SIZES = null;
    private static final Map<ConfigType, Integer> IMAGE_FORMATS_BY_CONFIG_TYPE = null;
    private final ConfigSize configSize;
    private final ConfigType configType;
    private final int imageFormat;
    private final StreamUseCase streamUseCase;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\u0005H\u0007J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\rH\u0007J>\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u0013\u001a\u00020\u0005H\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/camera/core/impl/SurfaceConfig$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DEFAULT_STREAM_USE_CASE", "Landroidx/camera/core/impl/StreamUseCase;", "FEATURE_COMBO_QUERY_SUPPORTED_SIZES", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/SurfaceConfig$ConfigSize;", "[Landroidx/camera/core/impl/SurfaceConfig$ConfigSize;", "IMAGE_FORMATS_BY_CONFIG_TYPE", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/SurfaceConfig$ConfigType;", _UrlKt.FRAGMENT_ENCODE_SET, "CONFIG_TYPES_BY_IMAGE_FORMAT", "create", "Landroidx/camera/core/impl/SurfaceConfig;", "type", "size", "streamUseCase", "getConfigType", "imageFormat", "transformSurfaceConfig", "Landroid/util/Size;", "surfaceSizeDefinition", "Landroidx/camera/core/impl/SurfaceSizeDefinition;", "cameraMode", "configSource", "Landroidx/camera/core/impl/SurfaceConfig$ConfigSource;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ SurfaceConfig create$default(Companion r0, ConfigType r1, ConfigSize r2, StreamUseCase r3, int r4, Object r5) {
            if ((r4 & 4) == 0) goto L6;
            r3 = SurfaceConfig.DEFAULT_STREAM_USE_CASE;
        L6:
            return r0.create(r1, r2, r3);
        }

        public static /* synthetic */ SurfaceConfig transformSurfaceConfig$default(Companion r7, int r8, Size r9, SurfaceSizeDefinition r10, int r11, ConfigSource r12, StreamUseCase r13, int r14, Object r15) {
            if ((r14 & 8) == 0) goto L5;
            r11 = 0;
        L5:
            int r4 = r11;
            if ((r14 & 16) == 0) goto L8;
            r12 = ConfigSource.CAPTURE_SESSION_TABLES;
        L8:
            ConfigSource r5 = r12;
            if ((r14 & 32) == 0) goto L12;
            r13 = SurfaceConfig.DEFAULT_STREAM_USE_CASE;
        L12:
            return r7.transformSurfaceConfig(r8, r9, r10, r4, r5, r13);
        }

        @JvmStatic
        @JvmOverloads
        public final SurfaceConfig create(ConfigType r1, ConfigSize r2, StreamUseCase r3) {
            return new SurfaceConfig(r1, r2, r3);
        }

        @JvmStatic
        public final ConfigType getConfigType(int r1) {
            ConfigType r0 = (ConfigType) SurfaceConfig.access$getCONFIG_TYPES_BY_IMAGE_FORMAT$cp().get(Integer.valueOf(r1));
            if (r0 == null) goto L5;
            return r0;
        L5:
            return ConfigType.PRIV;
        }

        @JvmStatic
        @JvmOverloads
        public final SurfaceConfig transformSurfaceConfig(int r5, Size r6, SurfaceSizeDefinition r7, int r8, ConfigSource r9, StreamUseCase r10) {
            ConfigType r0 = getConfigType(r5);
            ConfigSize r1 = ConfigSize.NOT_SUPPORT;
            int r2 = SizeUtil.getArea(r6);
            if (r8 != 1) goto L11;
            if (r2 > SizeUtil.getArea(r7.getS720pSize(r5))) goto L8;
            r1 = ConfigSize.S720P_16_9;
        L44:
            return create(r0, r1, r10);
        L8:
            if (r2 > SizeUtil.getArea(r7.getS1440pSize(r5))) goto L44;
            r1 = ConfigSize.S1440P_4_3;
            goto L44
        L11:
            if (r9 != ConfigSource.FEATURE_COMBINATION_TABLE) goto L24;
            Size r52 = r7.getMaximumSize(r5);
            ConfigSize[] r72 = SurfaceConfig.access$getFEATURE_COMBO_QUERY_SUPPORTED_SIZES$cp();
            int r82 = r72.length;
            int r92 = 0;
        L13:
            if (r92 >= r82) goto L19;
            ConfigSize r22 = r72[r92];
            if (Intrinsics.areEqual(r6, r22.getRelatedFixedSize()) == true) goto L16;
            r92 = r92 + 1;
            goto L13
        L16:
            r1 = r22;
        L19:
            if (r1 != ConfigSize.NOT_SUPPORT) goto L44;
            if (Intrinsics.areEqual(r6, r52) == false) goto L44;
            r1 = ConfigSize.MAXIMUM;
            goto L44
        L24:
            if (r2 > SizeUtil.getArea(r7.getAnalysisSize())) goto L27;
            r1 = ConfigSize.VGA;
            goto L44
        L27:
            if (r2 > SizeUtil.getArea(r7.getPreviewSize())) goto L30;
            r1 = ConfigSize.PREVIEW;
            goto L44
        L30:
            if (r2 > SizeUtil.getArea(r7.getRecordSize())) goto L32;
            r1 = ConfigSize.RECORD;
            goto L44
        L32:
            Size r62 = r7.getMaximumSize(r5);
            Size r53 = r7.getUltraMaximumSize(r5);
            if (r62 == null) goto L37;
            if (r2 <= SizeUtil.getArea(r62)) goto L37;
        L39:
            if (r53 == null) goto L44;
            if (r2 > SizeUtil.getArea(r53)) goto L44;
            r1 = ConfigSize.ULTRA_MAXIMUM;
        L37:
            if (r8 == 2) goto L39;
            r1 = ConfigSize.MAXIMUM;
            goto L44
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a¨\u0006\u001b"}, d2 = {"Landroidx/camera/core/impl/SurfaceConfig$ConfigSize;", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "relatedFixedSize", "Landroid/util/Size;", "<init>", "(Ljava/lang/String;IILandroid/util/Size;)V", "getId", "()I", "getRelatedFixedSize", "()Landroid/util/Size;", "VGA", "X_VGA", "S720P_16_9", "PREVIEW", "S1080P_4_3", "S1080P_16_9", "S1440P_4_3", "S1440P_16_9", "UHD", "RECORD", "MAXIMUM", "MAXIMUM_4_3", "MAXIMUM_16_9", "ULTRA_MAXIMUM", "NOT_SUPPORT", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum ConfigSize extends Enum<ConfigSize> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ ConfigSize[] $VALUES = null;
        public static final ConfigSize MAXIMUM = null;
        public static final ConfigSize MAXIMUM_16_9 = null;
        public static final ConfigSize MAXIMUM_4_3 = null;
        public static final ConfigSize NOT_SUPPORT = null;
        public static final ConfigSize PREVIEW = null;
        public static final ConfigSize RECORD = null;
        public static final ConfigSize S1080P_16_9 = null;
        public static final ConfigSize S1080P_4_3 = null;
        public static final ConfigSize S1440P_16_9 = null;
        public static final ConfigSize S1440P_4_3 = null;
        public static final ConfigSize S720P_16_9 = null;
        public static final ConfigSize UHD = null;
        public static final ConfigSize ULTRA_MAXIMUM = null;
        public static final ConfigSize VGA = null;
        public static final ConfigSize X_VGA = null;
        private final int id;
        private final Size relatedFixedSize;

        private static final /* synthetic */ ConfigSize[] $values() {
            return new ConfigSize[]{VGA, X_VGA, S720P_16_9, PREVIEW, S1080P_4_3, S1080P_16_9, S1440P_4_3, S1440P_16_9, UHD, RECORD, MAXIMUM, MAXIMUM_4_3, MAXIMUM_16_9, ULTRA_MAXIMUM, NOT_SUPPORT};
        }

        static {
            VGA = new ConfigSize("VGA", 0, 0, new Size(640, 480));
            X_VGA = new ConfigSize("X_VGA", 1, 1, new Size(1024, 768));
            S720P_16_9 = new ConfigSize("S720P_16_9", 2, 2, new Size(1280, 720));
            PREVIEW = new ConfigSize("PREVIEW", 3, 3, null, 2, null);
            S1080P_4_3 = new ConfigSize("S1080P_4_3", 4, 4, new Size(1440, 1080));
            S1080P_16_9 = new ConfigSize("S1080P_16_9", 5, 5, new Size(1920, 1080));
            S1440P_4_3 = new ConfigSize("S1440P_4_3", 6, 6, new Size(1920, 1440));
            S1440P_16_9 = new ConfigSize("S1440P_16_9", 7, 7, new Size(2560, 1440));
            UHD = new ConfigSize("UHD", 8, 8, new Size(3840, 2160));
            RECORD = new ConfigSize("RECORD", 9, 9, null, 2, null);
            MAXIMUM = new ConfigSize("MAXIMUM", 10, 10, null, 2, null);
            MAXIMUM_4_3 = new ConfigSize("MAXIMUM_4_3", 11, 11, null, 2, null);
            MAXIMUM_16_9 = new ConfigSize("MAXIMUM_16_9", 12, 12, null, 2, null);
            ULTRA_MAXIMUM = new ConfigSize("ULTRA_MAXIMUM", 13, 13, null, 2, null);
            NOT_SUPPORT = new ConfigSize("NOT_SUPPORT", 14, 14, null, 2, null);
            ConfigSize[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        ConfigSize(String r1, int r2, int r3, Size r4) {
            this.id = r3;
            this.relatedFixedSize = r4;
        }

        /* synthetic */ ConfigSize(String r1, int r2, int r3, Size r4, int r5, DefaultConstructorMarker r6) {
            if ((r5 & 2) == 0) goto L5;
            r4 = null;
        L5:
            this(r1, r2, r3, r4);
        }

        public static ConfigSize valueOf(String r1) {
            return (ConfigSize) Enum.valueOf(ConfigSize.class, r1);
        }

        public static ConfigSize[] values() {
            return (ConfigSize[]) $VALUES.clone();
        }

        public final int getId() {
            return this.id;
        }

        public final Size getRelatedFixedSize() {
            return this.relatedFixedSize;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/camera/core/impl/SurfaceConfig$ConfigSource;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "FEATURE_COMBINATION_TABLE", "CAPTURE_SESSION_TABLES", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum ConfigSource extends Enum<ConfigSource> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ ConfigSource[] $VALUES = null;
        public static final ConfigSource CAPTURE_SESSION_TABLES = null;
        public static final ConfigSource FEATURE_COMBINATION_TABLE = null;

        private static final /* synthetic */ ConfigSource[] $values() {
            return new ConfigSource[]{FEATURE_COMBINATION_TABLE, CAPTURE_SESSION_TABLES};
        }

        static {
            FEATURE_COMBINATION_TABLE = new ConfigSource("FEATURE_COMBINATION_TABLE", 0);
            CAPTURE_SESSION_TABLES = new ConfigSource("CAPTURE_SESSION_TABLES", 1);
            ConfigSource[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        ConfigSource(String r1, int r2) {
        }

        public static ConfigSource valueOf(String r1) {
            return (ConfigSource) Enum.valueOf(ConfigSource.class, r1);
        }

        public static ConfigSource[] values() {
            return (ConfigSource[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/camera/core/impl/SurfaceConfig$ConfigType;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "PRIV", "YUV", "JPEG", "JPEG_R", "RAW", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum ConfigType extends Enum<ConfigType> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ ConfigType[] $VALUES = null;
        public static final ConfigType JPEG = null;
        public static final ConfigType JPEG_R = null;
        public static final ConfigType PRIV = null;
        public static final ConfigType RAW = null;
        public static final ConfigType YUV = null;

        private static final /* synthetic */ ConfigType[] $values() {
            return new ConfigType[]{PRIV, YUV, JPEG, JPEG_R, RAW};
        }

        static {
            PRIV = new ConfigType("PRIV", 0);
            YUV = new ConfigType("YUV", 1);
            JPEG = new ConfigType("JPEG", 2);
            JPEG_R = new ConfigType("JPEG_R", 3);
            RAW = new ConfigType("RAW", 4);
            ConfigType[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        ConfigType(String r1, int r2) {
        }

        public static ConfigType valueOf(String r1) {
            return (ConfigType) Enum.valueOf(ConfigType.class, r1);
        }

        public static ConfigType[] values() {
            return (ConfigType[]) $VALUES.clone();
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[ConfigSize.values().length];
            r0[ConfigSize.PREVIEW.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
        L21:
            r0[ConfigSize.RECORD.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
        L31:
            r0[ConfigSize.MAXIMUM.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
        L23:
            r0[ConfigSize.MAXIMUM_4_3.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
        L25:
            r0[ConfigSize.MAXIMUM_16_9.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
        L19:
            r0[ConfigSize.ULTRA_MAXIMUM.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
        L27:
            r0[ConfigSize.NOT_SUPPORT.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
        L10:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        Companion = new Companion(null);
        DEFAULT_STREAM_USE_CASE = StreamUseCase.DEFAULT;
        FEATURE_COMBO_QUERY_SUPPORTED_SIZES = new ConfigSize[]{ConfigSize.S720P_16_9, ConfigSize.S1080P_4_3, ConfigSize.S1080P_16_9, ConfigSize.S1440P_16_9, ConfigSize.UHD, ConfigSize.X_VGA};
        Map<ConfigType, Integer> r0 = MapsKt.mapOf(new Pair[]{TuplesKt.to(ConfigType.YUV, 35), TuplesKt.to(ConfigType.JPEG, 256), TuplesKt.to(ConfigType.JPEG_R, 4101), TuplesKt.to(ConfigType.RAW, 32), TuplesKt.to(ConfigType.PRIV, 34)});
        IMAGE_FORMATS_BY_CONFIG_TYPE = r0;
        Set<Map.Entry<ConfigType, Integer>> r02 = r0.entrySet();
        LinkedHashMap r2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r02, 10)), 16));
        Iterator<T> r03 = r02.iterator();
    L4:
        if (r03.hasNext() == false) goto L6;
        Map.Entry r1 = (Map.Entry) r03.next();
        r2.put(Integer.valueOf(((Number) r1.getValue()).intValue()), (ConfigType) r1.getKey());
        goto L4
    L6:
        CONFIG_TYPES_BY_IMAGE_FORMAT = r2;
    }

    public SurfaceConfig(ConfigType r1, ConfigSize r2, StreamUseCase r3) {
        this.configType = r1;
        this.configSize = r2;
        this.streamUseCase = r3;
        Integer r12 = IMAGE_FORMATS_BY_CONFIG_TYPE.get(r1);
        if (r12 == null) goto L5;
        int r13 = r12.intValue();
    L6:
        this.imageFormat = r13;
        return;
    L5:
        r13 = 0;
        goto L6
    }

    public static final /* synthetic */ Map access$getCONFIG_TYPES_BY_IMAGE_FORMAT$cp() {
        return CONFIG_TYPES_BY_IMAGE_FORMAT;
    }

    public static final /* synthetic */ ConfigSize[] access$getFEATURE_COMBO_QUERY_SUPPORTED_SIZES$cp() {
        return FEATURE_COMBO_QUERY_SUPPORTED_SIZES;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof SurfaceConfig) == true) goto L8;
        return false;
    L8:
        SurfaceConfig r52 = (SurfaceConfig) r5;
        if (this.configType == r52.configType) goto L12;
        return false;
    L12:
        if (this.configSize == r52.configSize) goto L15;
        return false;
    L15:
        if (this.streamUseCase == r52.streamUseCase) goto L17;
        return false;
    L17:
        return true;
    }

    public final ConfigSize getConfigSize() {
        return this.configSize;
    }

    public final int getImageFormat() {
        return this.imageFormat;
    }

    public final Size getResolution(SurfaceSizeDefinition r3) {
        ConfigSize r0 = this.configSize;
        switch(WhenMappings.$EnumSwitchMapping$0[r0.ordinal()]) {
            case 1: goto L19;
            case 2: goto L17;
            case 3: goto L15;
            case 4: goto L13;
            case 5: goto L11;
            case 6: goto L9;
            case 7: goto L6;
            default: goto L5;
        };
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Not supported config size");
        return null;
    L5:
        return this.configSize.getRelatedFixedSize();
    L9:
        return r3.getUltraMaximumSize(this.imageFormat);
    L11:
        return r3.getMaximum16x9Size(this.imageFormat);
    L13:
        return r3.getMaximum4x3Size(this.imageFormat);
    L15:
        return r3.getMaximumSize(this.imageFormat);
    L17:
        return r3.getRecordSize();
    L19:
        return r3.getPreviewSize();
    }

    public final StreamUseCase getStreamUseCase() {
        return this.streamUseCase;
    }

    public int hashCode() {
        return (((this.configType.hashCode() * 31) + this.configSize.hashCode()) * 31) + this.streamUseCase.hashCode();
    }

    public final boolean isSupported(SurfaceConfig r4) {
        if (r4.configSize.getId() <= this.configSize.getId()) goto L6;
        return false;
    L6:
        if (r4.configType == this.configType) goto L8;
        return false;
    L8:
        StreamUseCase r3 = this.streamUseCase;
        StreamUseCase r0 = StreamUseCase.DEFAULT;
        if (r3 == r0) goto L14;
        StreamUseCase r42 = r4.streamUseCase;
        if (r42 == r0) goto L16;
        if (r42 == r3) goto L17;
        return false;
    L17:
        return true;
    L16:
        return true;
    L14:
        return true;
    }

    public String toString() {
        return "SurfaceConfig(configType=" + this.configType + ", configSize=" + this.configSize + ", streamUseCase=" + this.streamUseCase + ')';
    }
}
