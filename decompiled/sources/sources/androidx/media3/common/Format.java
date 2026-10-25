package androidx.media3.common;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.math.DoubleMath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes3.dex */
public final class Format {
    private static final Format DEFAULT = null;
    private static final String FIELD_ACCESSIBILITY_CHANNEL = null;
    private static final String FIELD_AUXILIARY_TRACK_TYPE = null;
    private static final String FIELD_AVERAGE_BITRATE = null;
    private static final String FIELD_CHANNEL_COUNT = null;
    private static final String FIELD_CODECS = null;
    private static final String FIELD_COLOR_INFO = null;
    private static final String FIELD_CONTAINER_MIME_TYPE = null;
    private static final String FIELD_CRYPTO_TYPE = null;
    private static final String FIELD_DECODED_HEIGHT = null;
    private static final String FIELD_DECODED_WIDTH = null;
    private static final String FIELD_DRM_INIT_DATA = null;
    private static final String FIELD_ENCODER_DELAY = null;
    private static final String FIELD_ENCODER_PADDING = null;
    private static final String FIELD_FRAME_RATE = null;
    private static final String FIELD_HEIGHT = null;
    private static final String FIELD_ID = null;
    private static final String FIELD_INITIALIZATION_DATA = null;
    private static final String FIELD_LABEL = null;
    private static final String FIELD_LABELS = null;
    private static final String FIELD_LANGUAGE = null;
    private static final String FIELD_MAX_INPUT_SIZE = null;
    private static final String FIELD_MAX_SUB_LAYERS = null;
    private static final String FIELD_PCM_ENCODING = null;
    private static final String FIELD_PEAK_BITRATE = null;
    private static final String FIELD_PIXEL_WIDTH_HEIGHT_RATIO = null;
    private static final String FIELD_PROJECTION_DATA = null;
    private static final String FIELD_ROLE_FLAGS = null;
    private static final String FIELD_ROTATION_DEGREES = null;
    private static final String FIELD_SAMPLE_MIME_TYPE = null;
    private static final String FIELD_SAMPLE_RATE = null;
    private static final String FIELD_SELECTION_FLAGS = null;
    private static final String FIELD_STEREO_MODE = null;
    private static final String FIELD_SUBSAMPLE_OFFSET_US = null;
    private static final String FIELD_TILE_COUNT_HORIZONTAL = null;
    private static final String FIELD_TILE_COUNT_VERTICAL = null;
    private static final String FIELD_WIDTH = null;
    private static final String UNUSED_FIELD_METADATA = null;
    public final int accessibilityChannel;
    public final int auxiliaryTrackType;
    public final int averageBitrate;
    public final int bitrate;
    public boolean cached;
    public final int channelCount;
    public final String codecs;
    public final ColorInfo colorInfo;
    public final String containerMimeType;
    public final int cryptoType;
    public final int cueReplacementBehavior;
    public int currentAccount;
    public final Object customData;
    public final int decodedHeight;
    public final int decodedWidth;
    public String documentFilename;
    public long documentId;
    public final DrmInitData drmInitData;
    public final int encoderDelay;
    public final int encoderPadding;
    public final float frameRate;
    public final boolean hasPrerollSamples;
    private int hashCode;
    public final int height;
    public final String id;
    public final List<byte[]> initializationData;
    public final String label;
    public final List<Label> labels;
    public final String language;
    public final int maxInputSize;
    public final int maxNumReorderSamples;
    public final int maxSubLayers;
    public final Metadata metadata;
    public final int pcmEncoding;
    public final int peakBitrate;
    public final float pixelWidthHeightRatio;
    public final byte[] projectionData;
    public final int roleFlags;
    public final int rotationDegrees;
    public final String sampleMimeType;
    public final int sampleRate;
    public final int selectionFlags;
    public final int stereoMode;
    public final long subsampleOffsetUs;
    public final int tileCountHorizontal;
    public final int tileCountVertical;
    public final int width;

    /* renamed from: androidx.media3.common.Format$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private int accessibilityChannel;
        private int auxiliaryTrackType;
        private int averageBitrate;
        public boolean cached;
        private int channelCount;
        private String codecs;
        private ColorInfo colorInfo;
        private String containerMimeType;
        private int cryptoType;
        private int cueReplacementBehavior;
        public int currentAccount;
        private Object customData;
        private int decodedHeight;
        private int decodedWidth;
        public String documentFilename;
        public long documentId;
        private DrmInitData drmInitData;
        private int encoderDelay;
        private int encoderPadding;
        private float frameRate;
        private boolean hasPrerollSamples;
        private int height;
        private String id;
        private List<byte[]> initializationData;
        private String label;
        private List<Label> labels;
        private String language;
        private int maxInputSize;
        private int maxNumReorderSamples;
        private int maxSubLayers;
        private Metadata metadata;
        private int pcmEncoding;
        private int peakBitrate;
        private float pixelWidthHeightRatio;
        private byte[] projectionData;
        private int roleFlags;
        private int rotationDegrees;
        private String sampleMimeType;
        private int sampleRate;
        private int selectionFlags;
        private int stereoMode;
        private long subsampleOffsetUs;
        private int tileCountHorizontal;
        private int tileCountVertical;
        private int width;

        public Builder() {
            this.labels = ImmutableList.of();
            this.averageBitrate = -1;
            this.peakBitrate = -1;
            this.maxInputSize = -1;
            this.maxNumReorderSamples = -1;
            this.subsampleOffsetUs = LongCompanionObject.MAX_VALUE;
            this.width = -1;
            this.height = -1;
            this.decodedWidth = -1;
            this.decodedHeight = -1;
            this.frameRate = -1.0f;
            this.pixelWidthHeightRatio = 1.0f;
            this.stereoMode = -1;
            this.maxSubLayers = -1;
            this.channelCount = -1;
            this.sampleRate = -1;
            this.pcmEncoding = -1;
            this.accessibilityChannel = -1;
            this.cueReplacementBehavior = 1;
            this.tileCountHorizontal = -1;
            this.tileCountVertical = -1;
            this.cryptoType = 0;
            this.auxiliaryTrackType = 0;
            this.cached = false;
        }

        private Builder(Format r3) {
            this.id = r3.id;
            this.label = r3.label;
            this.labels = r3.labels;
            this.language = r3.language;
            this.selectionFlags = r3.selectionFlags;
            this.roleFlags = r3.roleFlags;
            this.averageBitrate = r3.averageBitrate;
            this.peakBitrate = r3.peakBitrate;
            this.codecs = r3.codecs;
            this.metadata = r3.metadata;
            this.customData = r3.customData;
            this.containerMimeType = r3.containerMimeType;
            this.sampleMimeType = r3.sampleMimeType;
            this.maxInputSize = r3.maxInputSize;
            this.maxNumReorderSamples = r3.maxNumReorderSamples;
            this.initializationData = r3.initializationData;
            this.drmInitData = r3.drmInitData;
            this.subsampleOffsetUs = r3.subsampleOffsetUs;
            this.hasPrerollSamples = r3.hasPrerollSamples;
            this.width = r3.width;
            this.height = r3.height;
            this.decodedWidth = r3.decodedWidth;
            this.decodedHeight = r3.decodedHeight;
            this.frameRate = r3.frameRate;
            this.rotationDegrees = r3.rotationDegrees;
            this.pixelWidthHeightRatio = r3.pixelWidthHeightRatio;
            this.projectionData = r3.projectionData;
            this.stereoMode = r3.stereoMode;
            this.colorInfo = r3.colorInfo;
            this.maxSubLayers = r3.maxSubLayers;
            this.channelCount = r3.channelCount;
            this.sampleRate = r3.sampleRate;
            this.pcmEncoding = r3.pcmEncoding;
            this.encoderDelay = r3.encoderDelay;
            this.encoderPadding = r3.encoderPadding;
            this.accessibilityChannel = r3.accessibilityChannel;
            this.cueReplacementBehavior = r3.cueReplacementBehavior;
            this.tileCountHorizontal = r3.tileCountHorizontal;
            this.tileCountVertical = r3.tileCountVertical;
            this.cryptoType = r3.cryptoType;
            this.cached = r3.cached;
            this.documentId = r3.documentId;
            this.currentAccount = r3.currentAccount;
            this.documentFilename = r3.documentFilename;
        }

        public /* synthetic */ Builder(Format r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ List access$100(Builder r0) {
            return r0.labels;
        }

        public static /* synthetic */ String access$1000(Builder r0) {
            return r0.codecs;
        }

        public static /* synthetic */ Metadata access$1100(Builder r0) {
            return r0.metadata;
        }

        public static /* synthetic */ Object access$1200(Builder r0) {
            return r0.customData;
        }

        public static /* synthetic */ String access$1300(Builder r0) {
            return r0.containerMimeType;
        }

        public static /* synthetic */ String access$1400(Builder r0) {
            return r0.sampleMimeType;
        }

        public static /* synthetic */ int access$1500(Builder r0) {
            return r0.maxInputSize;
        }

        public static /* synthetic */ int access$1600(Builder r0) {
            return r0.maxNumReorderSamples;
        }

        public static /* synthetic */ List access$1700(Builder r0) {
            return r0.initializationData;
        }

        public static /* synthetic */ DrmInitData access$1800(Builder r0) {
            return r0.drmInitData;
        }

        public static /* synthetic */ long access$1900(Builder r2) {
            return r2.subsampleOffsetUs;
        }

        public static /* synthetic */ String access$200(Builder r0) {
            return r0.label;
        }

        public static /* synthetic */ boolean access$2000(Builder r0) {
            return r0.hasPrerollSamples;
        }

        public static /* synthetic */ int access$2100(Builder r0) {
            return r0.width;
        }

        public static /* synthetic */ int access$2200(Builder r0) {
            return r0.height;
        }

        public static /* synthetic */ int access$2300(Builder r0) {
            return r0.decodedWidth;
        }

        public static /* synthetic */ int access$2400(Builder r0) {
            return r0.decodedHeight;
        }

        public static /* synthetic */ float access$2500(Builder r0) {
            return r0.frameRate;
        }

        public static /* synthetic */ int access$2600(Builder r0) {
            return r0.rotationDegrees;
        }

        public static /* synthetic */ float access$2700(Builder r0) {
            return r0.pixelWidthHeightRatio;
        }

        public static /* synthetic */ byte[] access$2800(Builder r0) {
            return r0.projectionData;
        }

        public static /* synthetic */ int access$2900(Builder r0) {
            return r0.stereoMode;
        }

        public static /* synthetic */ String access$300(Builder r0) {
            return r0.id;
        }

        public static /* synthetic */ ColorInfo access$3000(Builder r0) {
            return r0.colorInfo;
        }

        public static /* synthetic */ int access$3100(Builder r0) {
            return r0.maxSubLayers;
        }

        public static /* synthetic */ int access$3200(Builder r0) {
            return r0.channelCount;
        }

        public static /* synthetic */ int access$3300(Builder r0) {
            return r0.sampleRate;
        }

        public static /* synthetic */ int access$3400(Builder r0) {
            return r0.pcmEncoding;
        }

        public static /* synthetic */ int access$3500(Builder r0) {
            return r0.encoderDelay;
        }

        public static /* synthetic */ int access$3600(Builder r0) {
            return r0.encoderPadding;
        }

        public static /* synthetic */ int access$3700(Builder r0) {
            return r0.accessibilityChannel;
        }

        public static /* synthetic */ int access$3800(Builder r0) {
            return r0.cueReplacementBehavior;
        }

        public static /* synthetic */ int access$3900(Builder r0) {
            return r0.tileCountHorizontal;
        }

        public static /* synthetic */ String access$400(Builder r0) {
            return r0.language;
        }

        public static /* synthetic */ int access$4000(Builder r0) {
            return r0.tileCountVertical;
        }

        public static /* synthetic */ int access$4100(Builder r0) {
            return r0.cryptoType;
        }

        public static /* synthetic */ int access$500(Builder r0) {
            return r0.selectionFlags;
        }

        public static /* synthetic */ int access$600(Builder r0) {
            return r0.auxiliaryTrackType;
        }

        public static /* synthetic */ int access$700(Builder r0) {
            return r0.roleFlags;
        }

        public static /* synthetic */ int access$800(Builder r0) {
            return r0.averageBitrate;
        }

        public static /* synthetic */ int access$900(Builder r0) {
            return r0.peakBitrate;
        }

        public Format build() {
            return new Format(this, null);
        }

        public Builder setAccessibilityChannel(int r1) {
            this.accessibilityChannel = r1;
            return this;
        }

        public Builder setAuxiliaryTrackType(int r1) {
            this.auxiliaryTrackType = r1;
            return this;
        }

        public Builder setAverageBitrate(int r1) {
            this.averageBitrate = r1;
            return this;
        }

        public Builder setCached(boolean r1) {
            this.cached = r1;
            return this;
        }

        public Builder setChannelCount(int r1) {
            this.channelCount = r1;
            return this;
        }

        public Builder setCodecs(String r1) {
            this.codecs = r1;
            return this;
        }

        public Builder setColorInfo(ColorInfo r1) {
            this.colorInfo = r1;
            return this;
        }

        public Builder setContainerMimeType(String r1) {
            this.containerMimeType = MimeTypes.normalizeMimeType(r1);
            return this;
        }

        public Builder setCryptoType(int r1) {
            this.cryptoType = r1;
            return this;
        }

        public Builder setCueReplacementBehavior(int r1) {
            this.cueReplacementBehavior = r1;
            return this;
        }

        public Builder setCurrentAccount(int r1) {
            this.currentAccount = r1;
            return this;
        }

        public Builder setCurrentAccount(String r1) {
            this.currentAccount = Integer.parseInt(r1);     // Catch: Exception -> L4
        L3:
            return this;
        }

        public Builder setCustomData(Object r1) {
            this.customData = r1;
            return this;
        }

        public Builder setDecodedHeight(int r1) {
            this.decodedHeight = r1;
            return this;
        }

        public Builder setDecodedWidth(int r1) {
            this.decodedWidth = r1;
            return this;
        }

        public Builder setDocumentFilename(String r1) {
            this.documentFilename = r1;
            return this;
        }

        public Builder setDocumentId(long r1) {
            this.documentId = r1;
            return this;
        }

        public Builder setDocumentId(String r3) {
            this.documentId = Long.parseLong(r3);     // Catch: Exception -> L4
        L3:
            return this;
        }

        public Builder setDrmInitData(DrmInitData r1) {
            this.drmInitData = r1;
            return this;
        }

        public Builder setEncoderDelay(int r1) {
            this.encoderDelay = r1;
            return this;
        }

        public Builder setEncoderPadding(int r1) {
            this.encoderPadding = r1;
            return this;
        }

        public Builder setFrameRate(float r1) {
            this.frameRate = r1;
            return this;
        }

        public Builder setHasPrerollSamples(boolean r1) {
            this.hasPrerollSamples = r1;
            return this;
        }

        public Builder setHeight(int r1) {
            this.height = r1;
            return this;
        }

        public Builder setId(int r1) {
            this.id = Integer.toString(r1);
            return this;
        }

        public Builder setId(String r1) {
            this.id = r1;
            return this;
        }

        public Builder setInitializationData(List<byte[]> r1) {
            this.initializationData = r1;
            return this;
        }

        public Builder setLabel(String r1) {
            this.label = r1;
            return this;
        }

        public Builder setLabels(List<Label> r1) {
            this.labels = ImmutableList.copyOf(r1);
            return this;
        }

        public Builder setLanguage(String r1) {
            this.language = r1;
            return this;
        }

        public Builder setMaxInputSize(int r1) {
            this.maxInputSize = r1;
            return this;
        }

        public Builder setMaxNumReorderSamples(int r1) {
            this.maxNumReorderSamples = r1;
            return this;
        }

        public Builder setMaxSubLayers(int r1) {
            this.maxSubLayers = r1;
            return this;
        }

        public Builder setMetadata(Metadata r1) {
            this.metadata = r1;
            return this;
        }

        public Builder setPcmEncoding(int r1) {
            this.pcmEncoding = r1;
            return this;
        }

        public Builder setPeakBitrate(int r1) {
            this.peakBitrate = r1;
            return this;
        }

        public Builder setPixelWidthHeightRatio(float r1) {
            this.pixelWidthHeightRatio = r1;
            return this;
        }

        public Builder setProjectionData(byte[] r1) {
            this.projectionData = r1;
            return this;
        }

        public Builder setRoleFlags(int r1) {
            this.roleFlags = r1;
            return this;
        }

        public Builder setRotationDegrees(int r1) {
            this.rotationDegrees = r1;
            return this;
        }

        public Builder setSampleMimeType(String r1) {
            this.sampleMimeType = MimeTypes.normalizeMimeType(r1);
            return this;
        }

        public Builder setSampleRate(int r1) {
            this.sampleRate = r1;
            return this;
        }

        public Builder setSelectionFlags(int r1) {
            this.selectionFlags = r1;
            return this;
        }

        public Builder setStereoMode(int r1) {
            this.stereoMode = r1;
            return this;
        }

        public Builder setSubsampleOffsetUs(long r1) {
            this.subsampleOffsetUs = r1;
            return this;
        }

        public Builder setTileCountHorizontal(int r1) {
            this.tileCountHorizontal = r1;
            return this;
        }

        public Builder setTileCountVertical(int r1) {
            this.tileCountVertical = r1;
            return this;
        }

        public Builder setWidth(int r1) {
            this.width = r1;
            return this;
        }
    }

    public static /* synthetic */ String $r8$lambda$z_VUNOJXhP_SAQRpjoNhPprwYvA(Label r2) {
        return r2.language + ": " + r2.value;
    }

    static {
        DEFAULT = new Builder().build();
        FIELD_ID = Util.intToStringMaxRadix(0);
        FIELD_LABEL = Util.intToStringMaxRadix(1);
        FIELD_LANGUAGE = Util.intToStringMaxRadix(2);
        FIELD_SELECTION_FLAGS = Util.intToStringMaxRadix(3);
        FIELD_ROLE_FLAGS = Util.intToStringMaxRadix(4);
        FIELD_AVERAGE_BITRATE = Util.intToStringMaxRadix(5);
        FIELD_PEAK_BITRATE = Util.intToStringMaxRadix(6);
        FIELD_CODECS = Util.intToStringMaxRadix(7);
        UNUSED_FIELD_METADATA = Util.intToStringMaxRadix(8);
        FIELD_CONTAINER_MIME_TYPE = Util.intToStringMaxRadix(9);
        FIELD_SAMPLE_MIME_TYPE = Util.intToStringMaxRadix(10);
        FIELD_MAX_INPUT_SIZE = Util.intToStringMaxRadix(11);
        FIELD_INITIALIZATION_DATA = Util.intToStringMaxRadix(12);
        FIELD_DRM_INIT_DATA = Util.intToStringMaxRadix(13);
        FIELD_SUBSAMPLE_OFFSET_US = Util.intToStringMaxRadix(14);
        FIELD_WIDTH = Util.intToStringMaxRadix(15);
        FIELD_HEIGHT = Util.intToStringMaxRadix(16);
        FIELD_FRAME_RATE = Util.intToStringMaxRadix(17);
        FIELD_ROTATION_DEGREES = Util.intToStringMaxRadix(18);
        FIELD_PIXEL_WIDTH_HEIGHT_RATIO = Util.intToStringMaxRadix(19);
        FIELD_PROJECTION_DATA = Util.intToStringMaxRadix(20);
        FIELD_STEREO_MODE = Util.intToStringMaxRadix(21);
        FIELD_COLOR_INFO = Util.intToStringMaxRadix(22);
        FIELD_CHANNEL_COUNT = Util.intToStringMaxRadix(23);
        FIELD_SAMPLE_RATE = Util.intToStringMaxRadix(24);
        FIELD_PCM_ENCODING = Util.intToStringMaxRadix(25);
        FIELD_ENCODER_DELAY = Util.intToStringMaxRadix(26);
        FIELD_ENCODER_PADDING = Util.intToStringMaxRadix(27);
        FIELD_ACCESSIBILITY_CHANNEL = Util.intToStringMaxRadix(28);
        FIELD_CRYPTO_TYPE = Util.intToStringMaxRadix(29);
        FIELD_TILE_COUNT_HORIZONTAL = Util.intToStringMaxRadix(30);
        FIELD_TILE_COUNT_VERTICAL = Util.intToStringMaxRadix(31);
        FIELD_LABELS = Util.intToStringMaxRadix(32);
        FIELD_AUXILIARY_TRACK_TYPE = Util.intToStringMaxRadix(33);
        FIELD_MAX_SUB_LAYERS = Util.intToStringMaxRadix(34);
        FIELD_DECODED_WIDTH = Util.intToStringMaxRadix(35);
        FIELD_DECODED_HEIGHT = Util.intToStringMaxRadix(36);
    }

    private Format(Builder r8) {
        this.id = Builder.access$300(r8);
        String r0 = Util.normalizeLanguageCode(Builder.access$400(r8));
        this.language = r0;
        if (Builder.access$100(r8).isEmpty() == false) goto L8;
        if (Builder.access$200(r8) == null) goto L8;
        this.labels = ImmutableList.of(new Label(r0, Builder.access$200(r8)));
        this.label = Builder.access$200(r8);
    L13:
        this.selectionFlags = Builder.access$500(r8);
        int r2 = 0;
        if (Builder.access$600(r8) != 0) goto L16;
    L19:
        boolean r02 = true;
    L20:
        Assertions.checkState(r02, "Auxiliary track type must only be set to a value other than AUXILIARY_TRACK_TYPE_UNDEFINED only when ROLE_FLAG_AUXILIARY is set");
        this.roleFlags = Builder.access$700(r8);
        this.auxiliaryTrackType = Builder.access$600(r8);
        int r03 = Builder.access$800(r8);
        this.averageBitrate = r03;
        int r3 = Builder.access$900(r8);
        this.peakBitrate = r3;
        if (r3 == (-1)) goto L23;
        r03 = r3;
    L23:
        this.bitrate = r03;
        this.codecs = Builder.access$1000(r8);
        this.metadata = Builder.access$1100(r8);
        this.customData = Builder.access$1200(r8);
        this.cached = r8.cached;
        this.documentId = r8.documentId;
        this.currentAccount = r8.currentAccount;
        this.documentFilename = r8.documentFilename;
        this.containerMimeType = Builder.access$1300(r8);
        this.sampleMimeType = Builder.access$1400(r8);
        this.maxInputSize = Builder.access$1500(r8);
        this.maxNumReorderSamples = Builder.access$1600(r8);
        if (Builder.access$1700(r8) != null) goto L26;
        List<byte[]> r04 = Collections.EMPTY_LIST;
    L27:
        this.initializationData = r04;
        DrmInitData r05 = Builder.access$1800(r8);
        this.drmInitData = r05;
        this.subsampleOffsetUs = Builder.access$1900(r8);
        this.hasPrerollSamples = Builder.access$2000(r8);
        this.width = Builder.access$2100(r8);
        this.height = Builder.access$2200(r8);
        this.decodedWidth = Builder.access$2300(r8);
        this.decodedHeight = Builder.access$2400(r8);
        this.frameRate = Builder.access$2500(r8);
        if (Builder.access$2600(r8) != (-1)) goto L30;
        int r32 = 0;
    L31:
        this.rotationDegrees = r32;
        if (Builder.access$2700(r8) != (-1.0f)) goto L34;
        float r33 = 1.0f;
    L35:
        this.pixelWidthHeightRatio = r33;
        this.projectionData = Builder.access$2800(r8);
        this.stereoMode = Builder.access$2900(r8);
        this.colorInfo = Builder.access$3000(r8);
        this.maxSubLayers = Builder.access$3100(r8);
        this.channelCount = Builder.access$3200(r8);
        this.sampleRate = Builder.access$3300(r8);
        this.pcmEncoding = Builder.access$3400(r8);
        if (Builder.access$3500(r8) != (-1)) goto L38;
        int r34 = 0;
    L39:
        this.encoderDelay = r34;
        if (Builder.access$3600(r8) == (-1)) goto L43;
        r2 = Builder.access$3600(r8);
    L43:
        this.encoderPadding = r2;
        this.accessibilityChannel = Builder.access$3700(r8);
        this.cueReplacementBehavior = Builder.access$3800(r8);
        this.tileCountHorizontal = Builder.access$3900(r8);
        this.tileCountVertical = Builder.access$4000(r8);
        if (Builder.access$4100(r8) != 0) goto L48;
        if (r05 == null) goto L48;
        this.cryptoType = 1;
        return;
    L48:
        this.cryptoType = Builder.access$4100(r8);
        return;
    L38:
        r34 = Builder.access$3500(r8);
        goto L39
    L34:
        r33 = Builder.access$2700(r8);
        goto L35
    L30:
        r32 = Builder.access$2600(r8);
        goto L31
    L26:
        r04 = Builder.access$1700(r8);
        goto L27
    L16:
        if ((Builder.access$700(r8) & 32768) != 0) goto L19;
        r02 = false;
    L8:
        if (Builder.access$100(r8).isEmpty() == false) goto L10;
    L12:
        Assertions.checkState(isLabelPartOfLabels(r8));
        this.labels = Builder.access$100(r8);
        this.label = Builder.access$200(r8);
        goto L13
    L10:
        if (Builder.access$200(r8) != null) goto L12;
        this.labels = Builder.access$100(r8);
        this.label = getDefaultLabel(Builder.access$100(r8), r0);
        goto L13
    }

    public /* synthetic */ Format(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    private static <T> T defaultIfNull(T r0, T r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        return r1;
    }

    public static Format fromBundle(Bundle r6) {
        Builder r0 = new Builder();
        BundleCollectionUtil.ensureClassLoader(r6);
        String r1 = r6.getString(FIELD_ID);
        Format r2 = DEFAULT;
        r0.setId((String) defaultIfNull(r1, r2.id)).setLabel((String) defaultIfNull(r6.getString(FIELD_LABEL), r2.label));
        ArrayList r12 = r6.getParcelableArrayList(FIELD_LABELS);
        if (r12 != null) goto L5;
        ImmutableList r13 = ImmutableList.of();
    L6:
        r0.setLabels(r13).setLanguage((String) defaultIfNull(r6.getString(FIELD_LANGUAGE), r2.language)).setSelectionFlags(r6.getInt(FIELD_SELECTION_FLAGS, r2.selectionFlags)).setRoleFlags(r6.getInt(FIELD_ROLE_FLAGS, r2.roleFlags)).setAuxiliaryTrackType(r6.getInt(FIELD_AUXILIARY_TRACK_TYPE, r2.auxiliaryTrackType)).setAverageBitrate(r6.getInt(FIELD_AVERAGE_BITRATE, r2.averageBitrate)).setPeakBitrate(r6.getInt(FIELD_PEAK_BITRATE, r2.peakBitrate)).setCodecs((String) defaultIfNull(r6.getString(FIELD_CODECS), r2.codecs)).setContainerMimeType((String) defaultIfNull(r6.getString(FIELD_CONTAINER_MIME_TYPE), r2.containerMimeType)).setSampleMimeType((String) defaultIfNull(r6.getString(FIELD_SAMPLE_MIME_TYPE), r2.sampleMimeType)).setMaxInputSize(r6.getInt(FIELD_MAX_INPUT_SIZE, r2.maxInputSize));
        ArrayList r14 = new ArrayList();
        int r22 = 0;
    L7:
        byte[] r3 = r6.getByteArray(keyForInitializationData(r22));
        if (r3 == null) goto L9;
        r14.add(r3);
        r22 = r22 + 1;
        goto L7
    L9:
        Builder r15 = r0.setInitializationData(r14).setDrmInitData((DrmInitData) r6.getParcelable(FIELD_DRM_INIT_DATA));
        String r23 = FIELD_SUBSAMPLE_OFFSET_US;
        Format r32 = DEFAULT;
        r15.setSubsampleOffsetUs(r6.getLong(r23, r32.subsampleOffsetUs)).setWidth(r6.getInt(FIELD_WIDTH, r32.width)).setHeight(r6.getInt(FIELD_HEIGHT, r32.height)).setDecodedWidth(r6.getInt(FIELD_DECODED_WIDTH, r32.decodedWidth)).setDecodedHeight(r6.getInt(FIELD_DECODED_HEIGHT, r32.decodedHeight)).setFrameRate(r6.getFloat(FIELD_FRAME_RATE, r32.frameRate)).setRotationDegrees(r6.getInt(FIELD_ROTATION_DEGREES, r32.rotationDegrees)).setPixelWidthHeightRatio(r6.getFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, r32.pixelWidthHeightRatio)).setProjectionData(r6.getByteArray(FIELD_PROJECTION_DATA)).setStereoMode(r6.getInt(FIELD_STEREO_MODE, r32.stereoMode)).setMaxSubLayers(r6.getInt(FIELD_MAX_SUB_LAYERS, r32.maxSubLayers));
        Bundle r16 = r6.getBundle(FIELD_COLOR_INFO);
        if (r16 == null) goto L12;
        r0.setColorInfo(ColorInfo.fromBundle(r16));
    L12:
        r0.setChannelCount(r6.getInt(FIELD_CHANNEL_COUNT, r32.channelCount)).setSampleRate(r6.getInt(FIELD_SAMPLE_RATE, r32.sampleRate)).setPcmEncoding(r6.getInt(FIELD_PCM_ENCODING, r32.pcmEncoding)).setEncoderDelay(r6.getInt(FIELD_ENCODER_DELAY, r32.encoderDelay)).setEncoderPadding(r6.getInt(FIELD_ENCODER_PADDING, r32.encoderPadding)).setAccessibilityChannel(r6.getInt(FIELD_ACCESSIBILITY_CHANNEL, r32.accessibilityChannel)).setTileCountHorizontal(r6.getInt(FIELD_TILE_COUNT_HORIZONTAL, r32.tileCountHorizontal)).setTileCountVertical(r6.getInt(FIELD_TILE_COUNT_VERTICAL, r32.tileCountVertical)).setCryptoType(r6.getInt(FIELD_CRYPTO_TYPE, r32.cryptoType));
        return r0.build();
    L5:
        r13 = BundleCollectionUtil.fromBundleList(new Format$$ExternalSyntheticLambda2(), r12);
        goto L6
    }

    private static String getDefaultLabel(List<Label> r3, String r4) {
        Iterator<Label> r0 = r3.iterator();
    L4:
        if (r0.hasNext() == false) goto L10;
        Label r1 = r0.next();
        if (TextUtils.equals(r1.language, r4) == false) goto L4;
        return r1.value;
    L10:
        return r3.get(0).value;
    }

    private static boolean isLabelPartOfLabels(Builder r5) {
        if (Builder.access$100(r5).isEmpty() == true) goto L5;
    L7:
        int r2 = 0;
    L9:
        if (r2 >= Builder.access$100(r5).size()) goto L14;
        if (((Label) Builder.access$100(r5).get(r2)).value.equals(Builder.access$200(r5)) == true) goto L12;
        r2 = r2 + 1;
        goto L9
    L12:
        return true;
    L14:
        return false;
    L5:
        if (Builder.access$200(r5) != null) goto L7;
        return true;
    }

    private static String keyForInitializationData(int r2) {
        return FIELD_INITIALIZATION_DATA + "_" + Integer.toString(r2, 36);
    }

    public static String toLogString(Format r10) {
        if (r10 != null) goto L5;
        return "null";
    L5:
        Joiner r0 = Joiner.on(',');
        StringBuilder r1 = new StringBuilder();
        r1.append("id=");
        r1.append(r10.id);
        r1.append(", mimeType=");
        r1.append(r10.sampleMimeType);
        if (r10.containerMimeType == null) goto L9;
        r1.append(", container=");
        r1.append(r10.containerMimeType);
    L9:
        if (r10.bitrate == (-1)) goto L12;
        r1.append(", bitrate=");
        r1.append(r10.bitrate);
    L12:
        if (r10.codecs == null) goto L15;
        r1.append(", codecs=");
        r1.append(r10.codecs);
    L15:
        if (r10.drmInitData == null) goto L38;
        LinkedHashSet r2 = new LinkedHashSet();
        int r4 = 0;
    L17:
        DrmInitData r5 = r10.drmInitData;
        if (r4 >= r5.schemeDataCount) goto L36;
        UUID r52 = r5.get(r4).uuid;
        if (r52.equals(C.COMMON_PSSH_UUID) == false) goto L23;
        r2.add("cenc");
    L35:
        r4 = r4 + 1;
        goto L17
    L23:
        if (r52.equals(C.CLEARKEY_UUID) == false) goto L26;
        r2.add("clearkey");
        goto L35
    L26:
        if (r52.equals(C.PLAYREADY_UUID) == false) goto L29;
        r2.add("playready");
        goto L35
    L29:
        if (r52.equals(C.WIDEVINE_UUID) == false) goto L32;
        r2.add("widevine");
        goto L35
    L32:
        if (r52.equals(C.UUID_NIL) == false) goto L34;
        r2.add("universal");
        goto L35
    L34:
        r2.add("unknown (" + r52 + ")");
        goto L35
    L36:
        r1.append(", drm=[");
        r0.appendTo(r1, r2);
        r1.append(']');
    L38:
        if (r10.width == (-1)) goto L43;
        if (r10.height == (-1)) goto L43;
        r1.append(", res=");
        r1.append(r10.width);
        r1.append("x");
        r1.append(r10.height);
    L43:
        if (r10.decodedWidth == (-1)) goto L48;
        if (r10.decodedHeight == (-1)) goto L48;
        r1.append(", decRes=");
        r1.append(r10.decodedWidth);
        r1.append("x");
        r1.append(r10.decodedHeight);
    L48:
        if (DoubleMath.fuzzyEquals(r10.pixelWidthHeightRatio, 1.0d, 0.001d) == true) goto L50;
        r1.append(", par=");
        r1.append(Util.formatInvariant("%.3f", new Object[]{Float.valueOf(r10.pixelWidthHeightRatio)}));
    L50:
        ColorInfo r22 = r10.colorInfo;
        if (r22 == null) goto L56;
        if (r22.isValid() == false) goto L56;
        r1.append(", color=");
        r1.append(r10.colorInfo.toLogString());
    L56:
        if (r10.frameRate == (-1.0f)) goto L59;
        r1.append(", fps=");
        r1.append(r10.frameRate);
    L59:
        if (r10.maxSubLayers == (-1)) goto L62;
        r1.append(", maxSubLayers=");
        r1.append(r10.maxSubLayers);
    L62:
        if (r10.channelCount == (-1)) goto L65;
        r1.append(", channels=");
        r1.append(r10.channelCount);
    L65:
        if (r10.sampleRate == (-1)) goto L68;
        r1.append(", sample_rate=");
        r1.append(r10.sampleRate);
    L68:
        if (r10.language == null) goto L71;
        r1.append(", language=");
        r1.append(r10.language);
    L71:
        if (r10.labels.isEmpty() == true) goto L74;
        r1.append(", labels=[");
        r0.appendTo(r1, Lists.transform(r10.labels, new Format$$ExternalSyntheticLambda0()));
        r1.append("]");
    L74:
        if (r10.selectionFlags == 0) goto L77;
        r1.append(", selectionFlags=[");
        r0.appendTo(r1, Util.getSelectionFlagStrings(r10.selectionFlags));
        r1.append("]");
    L77:
        if (r10.roleFlags == 0) goto L80;
        r1.append(", roleFlags=[");
        r0.appendTo(r1, Util.getRoleFlagStrings(r10.roleFlags));
        r1.append("]");
    L80:
        if (r10.customData == null) goto L83;
        r1.append(", customData=");
        r1.append(r10.customData);
    L83:
        if ((r10.roleFlags & 32768) == 0) goto L86;
        r1.append(", auxiliaryTrackType=");
        r1.append(Util.getAuxiliaryTrackTypeString(r10.auxiliaryTrackType));
    L86:
        return r1.toString();
    }

    public Builder buildUpon() {
        return new Builder(this, null);
    }

    public Format copyWithCryptoType(int r1) {
        return buildUpon().setCryptoType(r1).build();
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L93:
        return false;
    L8:
        if (Format.class != r7.getClass()) goto L93;
        Format r72 = (Format) r7;
        int r2 = this.hashCode;
        if (r2 == 0) goto L17;
        int r3 = r72.hashCode;
        if (r3 == 0) goto L17;
        if (r2 == r3) goto L17;
        return false;
    L17:
        if (this.selectionFlags != r72.selectionFlags) goto L93;
        if (this.roleFlags != r72.roleFlags) goto L93;
        if (this.auxiliaryTrackType != r72.auxiliaryTrackType) goto L93;
        if (this.averageBitrate != r72.averageBitrate) goto L93;
        if (this.peakBitrate != r72.peakBitrate) goto L93;
        if (this.maxInputSize != r72.maxInputSize) goto L93;
        if (this.subsampleOffsetUs != r72.subsampleOffsetUs) goto L93;
        if (this.width != r72.width) goto L93;
        if (this.height != r72.height) goto L93;
        if (this.decodedWidth != r72.decodedWidth) goto L93;
        if (this.decodedHeight != r72.decodedHeight) goto L93;
        if (this.rotationDegrees != r72.rotationDegrees) goto L93;
        if (this.stereoMode != r72.stereoMode) goto L93;
        if (this.maxSubLayers != r72.maxSubLayers) goto L93;
        if (this.channelCount != r72.channelCount) goto L93;
        if (this.sampleRate != r72.sampleRate) goto L93;
        if (this.pcmEncoding != r72.pcmEncoding) goto L93;
        if (this.encoderDelay != r72.encoderDelay) goto L93;
        if (this.encoderPadding != r72.encoderPadding) goto L93;
        if (this.accessibilityChannel != r72.accessibilityChannel) goto L93;
        if (this.tileCountHorizontal != r72.tileCountHorizontal) goto L93;
        if (this.tileCountVertical != r72.tileCountVertical) goto L93;
        if (this.cryptoType != r72.cryptoType) goto L93;
        if (Float.compare(this.frameRate, r72.frameRate) != 0) goto L93;
        if (Float.compare(this.pixelWidthHeightRatio, r72.pixelWidthHeightRatio) != 0) goto L93;
        if (Objects.equals(this.id, r72.id) == false) goto L93;
        if (Objects.equals(this.label, r72.label) == false) goto L93;
        if (this.labels.equals(r72.labels) == false) goto L93;
        if (Objects.equals(this.codecs, r72.codecs) == false) goto L93;
        if (Objects.equals(this.containerMimeType, r72.containerMimeType) == false) goto L93;
        if (Objects.equals(this.sampleMimeType, r72.sampleMimeType) == false) goto L93;
        if (Objects.equals(this.language, r72.language) == false) goto L93;
        if (Arrays.equals(this.projectionData, r72.projectionData) == false) goto L93;
        if (Objects.equals(this.metadata, r72.metadata) == false) goto L93;
        if (Objects.equals(this.colorInfo, r72.colorInfo) == false) goto L93;
        if (Objects.equals(this.drmInitData, r72.drmInitData) == false) goto L93;
        if (initializationDataEquals(r72) == false) goto L93;
        if (Objects.equals(this.customData, r72.customData) == false) goto L93;
        return true;
    }

    public int getPixelCount() {
        int r0 = this.width;
        if (r0 == (-1)) goto L9;
        int r2 = this.height;
        if (r2 == (-1)) goto L9;
        return r0 * r2;
    L9:
        return -1;
    }

    public int hashCode() {
        if (this.hashCode != 0) goto L38;
        String r0 = this.id;
        int r1 = 0;
        if (r0 != null) goto L7;
        int r02 = 0;
    L8:
        int r2 = (527 + r02) * 31;
        String r03 = this.label;
        if (r03 != null) goto L11;
        int r04 = 0;
    L12:
        int r22 = (((r2 + r04) * 31) + this.labels.hashCode()) * 31;
        String r05 = this.language;
        if (r05 != null) goto L15;
        int r06 = 0;
    L16:
        int r23 = (((((((((((r22 + r06) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31) + this.auxiliaryTrackType) * 31) + this.averageBitrate) * 31) + this.peakBitrate) * 31;
        String r07 = this.codecs;
        if (r07 != null) goto L19;
        int r08 = 0;
    L20:
        int r24 = (r23 + r08) * 31;
        Metadata r09 = this.metadata;
        if (r09 != null) goto L23;
        int r010 = 0;
    L24:
        int r25 = (r24 + r010) * 31;
        Object r011 = this.customData;
        if (r011 != null) goto L27;
        int r012 = 0;
    L28:
        int r26 = (r25 + r012) * 31;
        String r013 = this.containerMimeType;
        if (r013 != null) goto L31;
        int r014 = 0;
    L32:
        int r27 = (r26 + r014) * 31;
        String r015 = this.sampleMimeType;
        if (r015 == null) goto L36;
        r1 = r015.hashCode();
    L36:
        this.hashCode = ((((((((((((((((((((((((((((((((((((((((r27 + r1) * 31) + this.maxInputSize) * 31) + ((int) this.subsampleOffsetUs)) * 31) + this.width) * 31) + this.height) * 31) + this.decodedWidth) * 31) + this.decodedHeight) * 31) + Float.floatToIntBits(this.frameRate)) * 31) + this.rotationDegrees) * 31) + Float.floatToIntBits(this.pixelWidthHeightRatio)) * 31) + this.stereoMode) * 31) + this.maxSubLayers) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + this.pcmEncoding) * 31) + this.encoderDelay) * 31) + this.encoderPadding) * 31) + this.accessibilityChannel) * 31) + this.tileCountHorizontal) * 31) + this.tileCountVertical) * 31) + this.cryptoType;
        goto L38
    L31:
        r014 = r013.hashCode();
        goto L32
    L27:
        r012 = r011.hashCode();
        goto L28
    L23:
        r010 = r09.hashCode();
        goto L24
    L19:
        r08 = r07.hashCode();
        goto L20
    L15:
        r06 = r05.hashCode();
        goto L16
    L11:
        r04 = r03.hashCode();
        goto L12
    L7:
        r02 = r0.hashCode();
    L38:
        return this.hashCode;
    }

    public boolean initializationDataEquals(Format r5) {
        if (this.initializationData.size() == r5.initializationData.size()) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.initializationData.size()) goto L12;
        if (Arrays.equals(this.initializationData.get(r0), r5.initializationData.get(r0)) == false) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putString(FIELD_ID, this.id);
        r0.putString(FIELD_LABEL, this.label);
        r0.putParcelableArrayList(FIELD_LABELS, BundleCollectionUtil.toBundleArrayList(this.labels, new Format$$ExternalSyntheticLambda1()));
        r0.putString(FIELD_LANGUAGE, this.language);
        r0.putInt(FIELD_SELECTION_FLAGS, this.selectionFlags);
        r0.putInt(FIELD_ROLE_FLAGS, this.roleFlags);
        int r1 = this.auxiliaryTrackType;
        if (r1 == DEFAULT.auxiliaryTrackType) goto L5;
        r0.putInt(FIELD_AUXILIARY_TRACK_TYPE, r1);
    L5:
        r0.putInt(FIELD_AVERAGE_BITRATE, this.averageBitrate);
        r0.putInt(FIELD_PEAK_BITRATE, this.peakBitrate);
        r0.putString(FIELD_CODECS, this.codecs);
        r0.putString(FIELD_CONTAINER_MIME_TYPE, this.containerMimeType);
        r0.putString(FIELD_SAMPLE_MIME_TYPE, this.sampleMimeType);
        r0.putInt(FIELD_MAX_INPUT_SIZE, this.maxInputSize);
        int r12 = 0;
    L7:
        if (r12 >= this.initializationData.size()) goto L9;
        r0.putByteArray(keyForInitializationData(r12), this.initializationData.get(r12));
        r12 = r12 + 1;
        goto L7
    L9:
        r0.putParcelable(FIELD_DRM_INIT_DATA, this.drmInitData);
        r0.putLong(FIELD_SUBSAMPLE_OFFSET_US, this.subsampleOffsetUs);
        r0.putInt(FIELD_WIDTH, this.width);
        r0.putInt(FIELD_HEIGHT, this.height);
        r0.putInt(FIELD_DECODED_WIDTH, this.decodedWidth);
        r0.putInt(FIELD_DECODED_HEIGHT, this.decodedHeight);
        r0.putFloat(FIELD_FRAME_RATE, this.frameRate);
        r0.putInt(FIELD_ROTATION_DEGREES, this.rotationDegrees);
        r0.putFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, this.pixelWidthHeightRatio);
        r0.putByteArray(FIELD_PROJECTION_DATA, this.projectionData);
        r0.putInt(FIELD_STEREO_MODE, this.stereoMode);
        ColorInfo r13 = this.colorInfo;
        if (r13 == null) goto L12;
        r0.putBundle(FIELD_COLOR_INFO, r13.toBundle());
    L12:
        r0.putInt(FIELD_MAX_SUB_LAYERS, this.maxSubLayers);
        r0.putInt(FIELD_CHANNEL_COUNT, this.channelCount);
        r0.putInt(FIELD_SAMPLE_RATE, this.sampleRate);
        r0.putInt(FIELD_PCM_ENCODING, this.pcmEncoding);
        r0.putInt(FIELD_ENCODER_DELAY, this.encoderDelay);
        r0.putInt(FIELD_ENCODER_PADDING, this.encoderPadding);
        r0.putInt(FIELD_ACCESSIBILITY_CHANNEL, this.accessibilityChannel);
        r0.putInt(FIELD_TILE_COUNT_HORIZONTAL, this.tileCountHorizontal);
        r0.putInt(FIELD_TILE_COUNT_VERTICAL, this.tileCountVertical);
        r0.putInt(FIELD_CRYPTO_TYPE, this.cryptoType);
        return r0;
    }

    public String toString() {
        return "Format(" + this.id + ", " + this.label + ", " + this.containerMimeType + ", " + this.sampleMimeType + ", " + this.codecs + ", " + this.bitrate + ", " + this.language + ", [" + this.width + ", " + this.height + ", " + this.frameRate + ", " + this.colorInfo + "], [" + this.channelCount + ", " + this.sampleRate + "])";
    }

    public Format withManifestFormatInfo(Format r15) {
        if (this != r15) goto L4;
        return this;
    L4:
        int r0 = MimeTypes.getTrackType(this.sampleMimeType);
        String r1 = r15.id;
        int r2 = r15.tileCountHorizontal;
        int r3 = r15.tileCountVertical;
        String r4 = r15.label;
        if (r4 != null) goto L9;
        r4 = this.label;
    L9:
        if (r15.labels.isEmpty() == true) goto L11;
        List<Label> r5 = r15.labels;
    L12:
        String r6 = this.language;
        if (r0 == 3) goto L15;
        if (r0 == 1) goto L15;
    L18:
        int r7 = this.averageBitrate;
        if (r7 != (-1)) goto L21;
        r7 = r15.averageBitrate;
    L21:
        int r10 = this.peakBitrate;
        if (r10 != (-1)) goto L24;
        r10 = r15.peakBitrate;
    L24:
        String r9 = this.codecs;
        if (r9 != null) goto L29;
        String r11 = Util.getCodecsOfType(r15.codecs, r0);
        if (Util.splitCodecs(r11).length != 1) goto L29;
        r9 = r11;
    L29:
        Metadata r8 = this.metadata;
        Metadata r112 = r15.metadata;
        if (r8 == null) goto L33;
        r112 = r8.copyWithAppendedEntriesFrom(r112);
    L33:
        float r82 = this.frameRate;
        if (r82 == (-1.0f)) goto L36;
    L38:
        int r02 = this.selectionFlags | r15.selectionFlags;
        int r12 = this.roleFlags | r15.roleFlags;
        DrmInitData r152 = DrmInitData.createSessionCreationData(r15.drmInitData, this.drmInitData);
        return buildUpon().setId(r1).setLabel(r4).setLabels(r5).setLanguage(r6).setSelectionFlags(r02).setRoleFlags(r12).setAverageBitrate(r7).setPeakBitrate(r10).setCodecs(r9).setMetadata(r112).setDrmInitData(r152).setFrameRate(r82).setTileCountHorizontal(r2).setTileCountVertical(r3).build();
    L36:
        if (r0 != 2) goto L38;
        r82 = r15.frameRate;
    L15:
        String r72 = r15.language;
        if (r72 == null) goto L18;
        r6 = r72;
        goto L18
    L11:
        r5 = this.labels;
        goto L12
    }
}
