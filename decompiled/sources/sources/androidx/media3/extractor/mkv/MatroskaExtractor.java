package androidx.media3.extractor.mkv;

import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.DolbyVisionConfig;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.HevcConfig;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.telegram.messenger.MediaController;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public class MatroskaExtractor implements Extractor {

    @Deprecated
    public static final ExtractorsFactory FACTORY = null;
    private static final byte[] SSA_DIALOGUE_FORMAT = null;
    private static final byte[] SSA_PREFIX = null;
    private static final byte[] SUBRIP_PREFIX = null;
    private static final Map<String, Integer> TRACK_NAME_TO_ROTATION_DEGREES = null;
    private static final byte[] VTT_PREFIX = null;
    private static final UUID WAVE_SUBFORMAT_PCM = null;
    private int blockAdditionalId;
    private long blockDurationUs;
    private int blockFlags;
    private long blockGroupDiscardPaddingNs;
    private boolean blockHasReferenceBlock;
    private int blockSampleCount;
    private int blockSampleIndex;
    private int[] blockSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private boolean haveOutputSample;
    private boolean isWebm;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final boolean parseSubtitlesDuringExtraction;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final ParsableByteArray subtitleSample;
    private final ParsableByteArray supplementalData;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    /* renamed from: androidx.media3.extractor.mkv.MatroskaExtractor$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public final class InnerEbmlProcessor implements EbmlProcessor {
        final /* synthetic */ MatroskaExtractor this$0;

        private InnerEbmlProcessor(MatroskaExtractor r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ InnerEbmlProcessor(MatroskaExtractor r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void binaryElement(int r1, int r2, ExtractorInput r3) {
            this.this$0.binaryElement(r1, r2, r3);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void endMasterElement(int r1) {
            this.this$0.endMasterElement(r1);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void floatElement(int r1, double r2) {
            this.this$0.floatElement(r1, r2);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public int getElementType(int r1) {
            return this.this$0.getElementType(r1);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void integerElement(int r1, long r2) {
            this.this$0.integerElement(r1, r2);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public boolean isLevel1Element(int r1) {
            return this.this$0.isLevel1Element(r1);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void startMasterElement(int r1, long r2, long r4) {
            this.this$0.startMasterElement(r1, r2, r4);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void stringElement(int r1, String r2) {
            this.this$0.stringElement(r1, r2);
        }
    }

    public static final class Track {
        public int audioBitDepth;
        public int bitsPerChannel;
        private int blockAddIdType;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public byte[] dolbyVisionConfigBytes;
        public DrmInitData drmInitData;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        public boolean isWebm;
        private String language;
        public int maxBlockAdditionId;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public float projectionPosePitch;
        public float projectionPoseRoll;
        public float projectionPoseYaw;
        public int projectionType;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        public Track() {
            this.width = -1;
            this.height = -1;
            this.bitsPerChannel = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionType = -1;
            this.projectionPoseYaw = 0.0f;
            this.projectionPosePitch = 0.0f;
            this.projectionPoseRoll = 0.0f;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = 200;
            this.primaryRChromaticityX = -1.0f;
            this.primaryRChromaticityY = -1.0f;
            this.primaryGChromaticityX = -1.0f;
            this.primaryGChromaticityY = -1.0f;
            this.primaryBChromaticityX = -1.0f;
            this.primaryBChromaticityY = -1.0f;
            this.whitePointChromaticityX = -1.0f;
            this.whitePointChromaticityY = -1.0f;
            this.maxMasteringLuminance = -1.0f;
            this.minMasteringLuminance = -1.0f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.flagDefault = true;
            this.language = "eng";
        }

        public static /* synthetic */ void access$100(Track r0) {
            r0.assertOutputInitialized();
        }

        public static /* synthetic */ int access$200(Track r0) {
            return r0.blockAddIdType;
        }

        public static /* synthetic */ int access$202(Track r0, int r1) {
            r0.blockAddIdType = r1;
            return r1;
        }

        public static /* synthetic */ String access$302(Track r0, String r1) {
            r0.language = r1;
            return r1;
        }

        public static /* synthetic */ boolean access$400(Track r0, boolean r1) {
            return r0.samplesHaveSupplementalData(r1);
        }

        @EnsuresNonNull({"output"})
        private void assertOutputInitialized() {
            Assertions.checkNotNull(this.output);
        }

        @EnsuresNonNull({"codecPrivate"})
        private byte[] getCodecPrivate(String r2) {
            byte[] r1 = this.codecPrivate;
            if (r1 == null) goto L6;
            return r1;
        L6:
            throw ParserException.createForMalformedContainer("Missing CodecPrivate for codec " + r2, null);
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX != (-1.0f)) goto L5;
            return null;
        L5:
            if (this.primaryRChromaticityY != (-1.0f)) goto L7;
            return null;
        L7:
            if (this.primaryGChromaticityX != (-1.0f)) goto L9;
            return null;
        L9:
            if (this.primaryGChromaticityY != (-1.0f)) goto L11;
            return null;
        L11:
            if (this.primaryBChromaticityX != (-1.0f)) goto L13;
            return null;
        L13:
            if (this.primaryBChromaticityY != (-1.0f)) goto L15;
            return null;
        L15:
            if (this.whitePointChromaticityX != (-1.0f)) goto L17;
            return null;
        L17:
            if (this.whitePointChromaticityY != (-1.0f)) goto L19;
            return null;
        L19:
            if (this.maxMasteringLuminance != (-1.0f)) goto L21;
            return null;
        L21:
            if (this.minMasteringLuminance == (-1.0f)) goto L35;
            byte[] r0 = new byte[25];
            ByteBuffer r1 = ByteBuffer.wrap(r0).order(ByteOrder.LITTLE_ENDIAN);
            r1.put((byte) 0);
            r1.putShort((short) ((this.primaryRChromaticityX * 50000.0f) + 0.5f));
            r1.putShort((short) ((this.primaryRChromaticityY * 50000.0f) + 0.5f));
            r1.putShort((short) ((this.primaryGChromaticityX * 50000.0f) + 0.5f));
            r1.putShort((short) ((this.primaryGChromaticityY * 50000.0f) + 0.5f));
            r1.putShort((short) ((this.primaryBChromaticityX * 50000.0f) + 0.5f));
            r1.putShort((short) ((this.primaryBChromaticityY * 50000.0f) + 0.5f));
            r1.putShort((short) ((this.whitePointChromaticityX * 50000.0f) + 0.5f));
            r1.putShort((short) ((this.whitePointChromaticityY * 50000.0f) + 0.5f));
            r1.putShort((short) (this.maxMasteringLuminance + 0.5f));
            r1.putShort((short) (this.minMasteringLuminance + 0.5f));
            r1.putShort((short) this.maxContentLuminance);
            r1.putShort((short) this.maxFrameAverageLuminance);
            return r0;
        L35:
            return null;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray r6) {
            r6.skipBytes(16);     // Catch: ArrayIndexOutOfBoundsException -> L31
            long r2 = r6.readLittleEndianUnsignedInt();     // Catch: ArrayIndexOutOfBoundsException -> L31
            if (r2 != 1482049860) goto L8;
            return new Pair("video/divx", null);
        L8:
            if (r2 != 859189832) goto L12;
            return new Pair("video/3gpp", null);
        L12:
            if (r2 != 826496599) goto L29;
            int r0 = r6.getPosition() + 20;     // Catch: ArrayIndexOutOfBoundsException -> L31
            byte[] r62 = r6.getData();     // Catch: ArrayIndexOutOfBoundsException -> L31
        L15:
            if (r0 >= (r62.length - 4)) goto L28;
            if (r62[r0] != 0) goto L26;
            if (r62[r0 + 1] != 0) goto L26;
            if (r62[r0 + 2] != 1) goto L26;
            if (r62[r0 + 3] != 15) goto L26;
            return new Pair("video/wvc1", Collections.singletonList(Arrays.copyOfRange(r62, r0, r62.length)));
        L26:
            r0 = r0 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L31
            goto L15
        L28:
            throw ParserException.createForMalformedContainer("Failed to find FourCC VC1 initialization data", null);     // Catch: ArrayIndexOutOfBoundsException -> L31
        L29:
            Log.w("MatroskaExtractor", "Unknown FourCC. Setting mimeType to video/x-unknown");
            return new Pair("video/x-unknown", null);
        L32:
            throw ParserException.createForMalformedContainer("Error parsing FourCC private data", null);
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray r8) {
            int r0 = r8.readLittleEndianUnsignedShort();     // Catch: ArrayIndexOutOfBoundsException -> L14
            if (r0 != 1) goto L6;
            return true;
        L6:
            if (r0 != 65534) goto L13;
            r8.setPosition(24);     // Catch: ArrayIndexOutOfBoundsException -> L14
            if (r8.readLong() != MatroskaExtractor.access$700().getMostSignificantBits()) goto L13;
            if (r8.readLong() != MatroskaExtractor.access$700().getLeastSignificantBits()) goto L13;
            return true;
        L13:
            return false;
        L15:
            throw ParserException.createForMalformedContainer("Error parsing MS/ACM codec private", null);
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] r11) {
            if (r11[0] != 2) goto L28;
            int r6 = 0;
            int r5 = 1;
        L6:
            int r7 = r11[r5];     // Catch: ArrayIndexOutOfBoundsException -> L29
            if ((r7 & 255) != 255) goto L9;
            r6 = r6 + 255;     // Catch: ArrayIndexOutOfBoundsException -> L29
            r5 = r5 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L29
            goto L6
        L9:
            int r52 = r5 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L29
            int r62 = r6 + (r7 & 255);     // Catch: ArrayIndexOutOfBoundsException -> L29
            int r72 = 0;
        L10:
            int r8 = r11[r52];     // Catch: ArrayIndexOutOfBoundsException -> L29
            if ((r8 & 255) != 255) goto L13;
            r72 = r72 + 255;     // Catch: ArrayIndexOutOfBoundsException -> L29
            r52 = r52 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L29
            goto L10
        L13:
            int r53 = r52 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L29
            int r73 = r72 + (r8 & 255);     // Catch: ArrayIndexOutOfBoundsException -> L29
            if (r11[r53] != 1) goto L26;
            byte[] r3 = new byte[r62];     // Catch: ArrayIndexOutOfBoundsException -> L29
            System.arraycopy(r11, r53, r3, 0, r62);     // Catch: ArrayIndexOutOfBoundsException -> L29
            int r54 = r53 + r62;     // Catch: ArrayIndexOutOfBoundsException -> L29
            if (r11[r54] != 3) goto L24;
            int r55 = r54 + r73;     // Catch: ArrayIndexOutOfBoundsException -> L29
            if (r11[r55] != 5) goto L22;
            byte[] r63 = new byte[r11.length - r55];     // Catch: ArrayIndexOutOfBoundsException -> L29
            System.arraycopy(r11, r55, r63, 0, r11.length - r55);     // Catch: ArrayIndexOutOfBoundsException -> L29
            ArrayList r112 = new ArrayList(2);     // Catch: ArrayIndexOutOfBoundsException -> L29
            r112.add(r3);     // Catch: ArrayIndexOutOfBoundsException -> L29
            r112.add(r63);     // Catch: ArrayIndexOutOfBoundsException -> L29
            return r112;
        L22:
            throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);     // Catch: ArrayIndexOutOfBoundsException -> L29
        L24:
            throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);     // Catch: ArrayIndexOutOfBoundsException -> L29
        L26:
            throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);     // Catch: ArrayIndexOutOfBoundsException -> L29
        L28:
            throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);     // Catch: ArrayIndexOutOfBoundsException -> L29
        L30:
            throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
        }

        private boolean samplesHaveSupplementalData(boolean r3) {
            if ("A_OPUS".equals(this.codecId) == false) goto L6;
            return r3;
        L6:
            if (this.maxBlockAdditionId <= 0) goto L9;
            return true;
        L9:
            return false;
        }

        @EnsuresNonNull({"this.output"})
        @RequiresNonNull({"codecId"})
        public void initializeOutput(ExtractorOutput r20, int r21) {
            String r1 = this.codecId;
            r1.getClass();
            switch(r1.hashCode()) {
                case -2095576542: goto L138;
                case -2095575984: goto L134;
                case -1985379776: goto L130;
                case -1784763192: goto L126;
                case -1730367663: goto L122;
                case -1482641358: goto L118;
                case -1482641357: goto L114;
                case -1373388978: goto L110;
                case -933872740: goto L106;
                case -538363189: goto L102;
                case -538363109: goto L98;
                case -425012669: goto L94;
                case -356037306: goto L90;
                case 62923557: goto L86;
                case 62923603: goto L82;
                case 62927045: goto L78;
                case 82318131: goto L74;
                case 82338133: goto L70;
                case 82338134: goto L66;
                case 99146302: goto L62;
                case 444813526: goto L58;
                case 542569478: goto L54;
                case 635596514: goto L50;
                case 725948237: goto L46;
                case 725957860: goto L42;
                case 738597099: goto L38;
                case 738614379: goto L34;
                case 855502857: goto L30;
                case 1045209816: goto L26;
                case 1422270023: goto L22;
                case 1809237540: goto L18;
                case 1950749482: goto L14;
                case 1950789798: goto L10;
                case 1951062397: goto L6;
                default: goto L4;
            };
        L4:
            char r12 = 65535;
        L141:
            String r17 = "audio/raw";
            ColorInfo r8 = null;
            switch(r12) {
                case 0: goto L215;
                case 1: goto L215;
                case 2: goto L208;
                case 3: goto L206;
                case 4: goto L205;
                case 5: goto L204;
                case 6: goto L202;
                case 7: goto L200;
                case 8: goto L198;
                case 9: goto L215;
                case 10: goto L197;
                case 11: goto L196;
                case 12: goto L195;
                case 13: goto L194;
                case 14: goto L193;
                case 15: goto L192;
                case 16: goto L187;
                case 17: goto L186;
                case 18: goto L181;
                case 19: goto L180;
                case 20: goto L179;
                case 21: goto L192;
                case 22: goto L176;
                case 23: goto L165;
                case 24: goto L159;
                case 25: goto L158;
                case 26: goto L158;
                case 27: goto L156;
                case 28: goto L155;
                case 29: goto L154;
                case 30: goto L153;
                case 31: goto L151;
                case 32: goto L148;
                case 33: goto L145;
                default: goto L144;
            };
        L145:
            List<byte[]> r13 = new ArrayList(3);
            r13.add(getCodecPrivate(this.codecId));
            ByteBuffer r3 = ByteBuffer.allocate(8);
            ByteOrder r4 = ByteOrder.LITTLE_ENDIAN;
            r13.add(r3.order(r4).putLong(this.codecDelayNs).array());
            r13.add(ByteBuffer.allocate(8).order(r4).putLong(this.seekPreRollNs).array());
            r17 = "audio/opus";
            int r42 = 5760;
            String r32 = null;
        L147:
            int r9 = -1;
        L221:
            if (this.dolbyVisionConfigBytes == null) goto L225;
            DolbyVisionConfig r11 = DolbyVisionConfig.parse(new ParsableByteArray(this.dolbyVisionConfigBytes));
            if (r11 == null) goto L225;
            r32 = r11.codecs;
            r17 = "video/dolby-vision";
        L225:
            String r112 = r17;
            boolean r122 = this.flagDefault;
            if (this.flagForced == false) goto L228;
            int r7 = 2;
        L229:
            int r72 = r7 | (r122 ? 1 : 0);
            Format.Builder r123 = new Format.Builder();
            if (MimeTypes.isAudio(r112) == false) goto L233;
            r123.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(r9);
            int r5 = 1;
        L297:
            if (this.name != null) goto L299;
        L301:
            Format.Builder r2 = r123.setId(r21);
            if (this.isWebm == false) goto L304;
            String r6 = "video/webm";
        L305:
            Format r14 = r2.setContainerMimeType(r6).setSampleMimeType(r112).setMaxInputSize(r42).setLanguage(this.language).setSelectionFlags(r72).setInitializationData(r13).setCodecs(r32).setDrmInitData(this.drmInitData).build();
            TrackOutput r22 = r20.track(this.number, r5);
            this.output = r22;
            r22.format(r14);
            return;
        L304:
            r6 = "video/x-matroska";
            goto L305
        L299:
            if (MatroskaExtractor.access$600().containsKey(this.name) == true) goto L301;
            r123.setLabel(this.name);
            goto L301
        L233:
            if (MimeTypes.isVideo(r112) == false) goto L281;
            if (this.displayUnit != 0) goto L243;
            int r23 = this.displayWidth;
            int r52 = -1;
            if (r23 != (-1)) goto L239;
            r23 = this.width;
        L239:
            this.displayWidth = r23;
            int r24 = this.displayHeight;
            if (r24 != (-1)) goto L242;
            r24 = this.height;
        L242:
            this.displayHeight = r24;
        L245:
            if (this.displayWidth != r52) goto L247;
        L249:
            float r25 = -1.0f;
        L251:
            if (this.hasColorInfo == false) goto L254;
            r8 = new ColorInfo.Builder().setColorSpace(this.colorSpace).setColorRange(this.colorRange).setColorTransfer(this.colorTransfer).setHdrStaticInfo(getHdrStaticInfo()).setLumaBitdepth(this.bitsPerChannel).setChromaBitdepth(this.bitsPerChannel).build();
        L254:
            if (this.name == null) goto L259;
            if (MatroskaExtractor.access$600().containsKey(this.name) == false) goto L259;
            r52 = ((Integer) MatroskaExtractor.access$600().get(this.name)).intValue();
        L259:
            if (this.projectionType == 0) goto L261;
        L279:
            r123.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(r25).setRotationDegrees(r52).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(r8);
            r5 = 2;
            goto L297
        L261:
            if (Float.compare(this.projectionPoseYaw, 0.0f) != 0) goto L279;
            if (Float.compare(this.projectionPosePitch, 0.0f) != 0) goto L279;
            if (Float.compare(this.projectionPoseRoll, 0.0f) != 0) goto L268;
            r52 = 0;
            goto L279
        L268:
            if (Float.compare(this.projectionPoseRoll, 90.0f) != 0) goto L271;
            r52 = 90;
            goto L279
        L271:
            if (Float.compare(this.projectionPoseRoll, -180.0f) != 0) goto L273;
        L278:
            r52 = 180;
            goto L279
        L273:
            if (Float.compare(this.projectionPoseRoll, 180.0f) == 0) goto L278;
            if (Float.compare(this.projectionPoseRoll, -90.0f) != 0) goto L279;
            r52 = 270;
            goto L279
        L247:
            if (this.displayHeight == r52) goto L249;
            r25 = (this.height * r2) / (this.width * r6);
            goto L251
        L243:
            r52 = -1;
            goto L245
        L281:
            if ("application/x-subrip".equals(r112) == false) goto L283;
        L295:
            r5 = 3;
            goto L297
        L283:
            if ("text/x-ssa".equals(r112) == true) goto L295;
            if ("text/vtt".equals(r112) == true) goto L295;
            if ("application/vobsub".equals(r112) == true) goto L295;
            if ("application/pgs".equals(r112) == true) goto L295;
            if ("application/dvbsubs".equals(r112) == true) goto L295;
            throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
        L228:
            r7 = 0;
            goto L229
        L148:
            r13 = Collections.singletonList(getCodecPrivate(this.codecId));
            r17 = "audio/flac";
        L149:
            r32 = null;
        L150:
            r42 = -1;
            goto L147
        L151:
            r17 = "audio/eac3";
        L152:
            r13 = null;
            r32 = null;
            goto L150
        L153:
            r17 = "video/mpeg2";
            goto L152
        L154:
            r17 = "application/x-subrip";
            goto L152
        L155:
            r17 = "text/vtt";
            goto L152
        L156:
            HevcConfig r15 = HevcConfig.parse(new ParsableByteArray(getCodecPrivate(this.codecId)));
            List<byte[]> r33 = r15.initializationData;
            this.nalUnitLengthFieldLength = r15.nalUnitLengthFieldLength;
            String r16 = r15.codecs;
            r17 = "video/hevc";
        L157:
            List<byte[]> r43 = r33;
            r32 = r16;
            r13 = r43;
            goto L150
        L158:
            r13 = ImmutableList.of(MatroskaExtractor.access$500(), getCodecPrivate(this.codecId));
            r17 = "text/x-ssa";
            goto L149
        L159:
            int r18 = Util.getPcmEncoding(this.audioBitDepth);
            if (r18 != 0) goto L163;
            Log.w("MatroskaExtractor", "Unsupported little endian PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to audio/x-unknown");
        L162:
            r13 = null;
            r32 = null;
            r17 = "audio/x-unknown";
        L163:
            r9 = r18;
            r13 = null;
            r32 = null;
        L164:
            r42 = -1;
            goto L221
        L165:
            int r19 = this.audioBitDepth;
            if (r19 != 8) goto L168;
            r13 = null;
            r32 = null;
            r9 = 3;
            goto L164
        L168:
            if (r19 != 16) goto L170;
            r18 = 268435456;
            goto L163
        L170:
            if (r19 != 24) goto L172;
            r18 = 1342177280;
            goto L163
        L172:
            if (r19 != 32) goto L174;
            r18 = 1610612736;
            goto L163
        L174:
            Log.w("MatroskaExtractor", "Unsupported big endian PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to audio/x-unknown");
            goto L162
        L179:
            r17 = "video/x-unknown";
            goto L152
        L180:
            r13 = null;
            r32 = null;
            r17 = "application/pgs";
            goto L150
        L181:
            byte[] r110 = this.codecPrivate;
            if (r110 != null) goto L184;
            r13 = null;
        L185:
            r17 = "video/x-vnd.on2.vp9";
            goto L149
        L184:
            r13 = ImmutableList.of(r110);
            goto L185
        L186:
            r17 = "video/x-vnd.on2.vp8";
            goto L152
        L187:
            byte[] r111 = this.codecPrivate;
            if (r111 != null) goto L190;
            r13 = null;
        L191:
            r17 = "video/av01";
            goto L149
        L190:
            r13 = ImmutableList.of(r111);
            goto L191
        L192:
            r17 = "audio/vnd.dts";
            goto L152
        L193:
            r17 = "audio/ac3";
            goto L152
        L194:
            r13 = Collections.singletonList(getCodecPrivate(this.codecId));
            AacUtil.Config r34 = AacUtil.parseAudioSpecificConfig(this.codecPrivate);
            this.sampleRate = r34.sampleRateHz;
            this.channelCount = r34.channelCount;
            r32 = r34.codecs;
            r17 = MediaController.AUDIO_MIME_TYPE;
            goto L150
        L195:
            r17 = "audio/vnd.dts.hd";
            goto L152
        L196:
            r13 = ImmutableList.of(getCodecPrivate(this.codecId));
            r32 = null;
            r17 = "application/vobsub";
            goto L150
        L197:
            AvcConfig r113 = AvcConfig.parse(new ParsableByteArray(getCodecPrivate(this.codecId)));
            r33 = r113.initializationData;
            this.nalUnitLengthFieldLength = r113.nalUnitLengthFieldLength;
            r16 = r113.codecs;
            r17 = MediaController.VIDEO_MIME_TYPE;
            goto L157
        L198:
            byte[] r35 = new byte[4];
            System.arraycopy(getCodecPrivate(this.codecId), 0, r35, 0, 4);
            r13 = ImmutableList.of(r35);
            r32 = null;
            r17 = "application/dvbsubs";
        L199:
            r42 = -1;
            goto L147
        L200:
            Pair<String, List<byte[]>> r114 = parseFourCcPrivate(new ParsableByteArray(getCodecPrivate(this.codecId)));
            r17 = (String) r114.first;
            r13 = (List) r114.second;
        L201:
            r32 = null;
            goto L199
        L202:
            r17 = "audio/mpeg";
        L203:
            r13 = null;
            r32 = null;
            r42 = 4096;
            goto L147
        L204:
            r17 = "audio/mpeg-L2";
            goto L203
        L205:
            r13 = parseVorbisCodecPrivate(getCodecPrivate(this.codecId));
            r17 = "audio/vorbis";
            r42 = 8192;
            r32 = null;
            goto L147
        L206:
            this.trueHdSampleRechunker = new TrueHdSampleRechunker();
            r17 = "audio/true-hd";
            r13 = null;
            r32 = null;
            goto L199
        L215:
            byte[] r115 = this.codecPrivate;
            if (r115 != null) goto L218;
            r13 = null;
        L219:
            r17 = "video/mp4v-es";
            goto L201
        L218:
            r13 = Collections.singletonList(r115);
            goto L219
        L144:
            throw ParserException.createForMalformedContainer("Unrecognized codec identifier.", null);
        L176:
            if (this.audioBitDepth != 32) goto L178;
            r13 = null;
            r32 = null;
            r42 = -1;
            r9 = 4;
            goto L221
        L178:
            Log.w("MatroskaExtractor", "Unsupported floating point PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to audio/x-unknown");
            goto L162
        L208:
            if (parseMsAcmCodecPrivate(new ParsableByteArray(getCodecPrivate(this.codecId))) == false) goto L214;
            int r116 = Util.getPcmEncoding(this.audioBitDepth);
            if (r116 != 0) goto L213;
            Log.w("MatroskaExtractor", "Unsupported PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to audio/x-unknown");
        L212:
            r13 = null;
            r32 = null;
            r17 = "audio/x-unknown";
            goto L199
        L213:
            r9 = r116;
            r13 = null;
            r32 = null;
            r42 = -1;
            goto L221
        L214:
            Log.w("MatroskaExtractor", "Non-PCM MS/ACM is unsupported. Setting mimeType to audio/x-unknown");
            goto L212
        L6:
            if (r1.equals("A_OPUS") == false) goto L4;
            r12 = '!';
            goto L141
        L10:
            if (r1.equals("A_FLAC") == false) goto L4;
            r12 = ' ';
            goto L141
        L14:
            if (r1.equals("A_EAC3") == false) goto L4;
            r12 = 31;
            goto L141
        L18:
            if (r1.equals("V_MPEG2") == false) goto L4;
            r12 = 30;
            goto L141
        L22:
            if (r1.equals("S_TEXT/UTF8") == false) goto L4;
            r12 = 29;
            goto L141
        L26:
            if (r1.equals("S_TEXT/WEBVTT") == false) goto L4;
            r12 = 28;
            goto L141
        L30:
            if (r1.equals("V_MPEGH/ISO/HEVC") == false) goto L4;
            r12 = 27;
            goto L141
        L34:
            if (r1.equals("S_TEXT/SSA") == false) goto L4;
            r12 = 26;
            goto L141
        L38:
            if (r1.equals("S_TEXT/ASS") == false) goto L4;
            r12 = 25;
            goto L141
        L42:
            if (r1.equals("A_PCM/INT/LIT") == false) goto L4;
            r12 = 24;
            goto L141
        L46:
            if (r1.equals("A_PCM/INT/BIG") == false) goto L4;
            r12 = 23;
            goto L141
        L50:
            if (r1.equals("A_PCM/FLOAT/IEEE") == false) goto L4;
            r12 = 22;
            goto L141
        L54:
            if (r1.equals("A_DTS/EXPRESS") == false) goto L4;
            r12 = 21;
            goto L141
        L58:
            if (r1.equals("V_THEORA") == false) goto L4;
            r12 = 20;
            goto L141
        L62:
            if (r1.equals("S_HDMV/PGS") == false) goto L4;
            r12 = 19;
            goto L141
        L66:
            if (r1.equals("V_VP9") == false) goto L4;
            r12 = 18;
            goto L141
        L70:
            if (r1.equals("V_VP8") == false) goto L4;
            r12 = 17;
            goto L141
        L74:
            if (r1.equals("V_AV1") == false) goto L4;
            r12 = 16;
            goto L141
        L78:
            if (r1.equals("A_DTS") == false) goto L4;
            r12 = 15;
            goto L141
        L82:
            if (r1.equals("A_AC3") == false) goto L4;
            r12 = 14;
            goto L141
        L86:
            if (r1.equals("A_AAC") == false) goto L4;
            r12 = '\r';
            goto L141
        L90:
            if (r1.equals("A_DTS/LOSSLESS") == false) goto L4;
            r12 = '\f';
            goto L141
        L94:
            if (r1.equals("S_VOBSUB") == false) goto L4;
            r12 = 11;
            goto L141
        L98:
            if (r1.equals("V_MPEG4/ISO/AVC") == false) goto L4;
            r12 = '\n';
            goto L141
        L102:
            if (r1.equals("V_MPEG4/ISO/ASP") == false) goto L4;
            r12 = '\t';
            goto L141
        L106:
            if (r1.equals("S_DVBSUB") == false) goto L4;
            r12 = '\b';
            goto L141
        L110:
            if (r1.equals("V_MS/VFW/FOURCC") == false) goto L4;
            r12 = 7;
            goto L141
        L114:
            if (r1.equals("A_MPEG/L3") == false) goto L4;
            r12 = 6;
            goto L141
        L118:
            if (r1.equals("A_MPEG/L2") == false) goto L4;
            r12 = 5;
            goto L141
        L122:
            if (r1.equals("A_VORBIS") == false) goto L4;
            r12 = 4;
            goto L141
        L126:
            if (r1.equals("A_TRUEHD") == false) goto L4;
            r12 = 3;
            goto L141
        L130:
            if (r1.equals("A_MS/ACM") == false) goto L4;
            r12 = 2;
            goto L141
        L134:
            if (r1.equals("V_MPEG4/ISO/SP") == false) goto L4;
            r12 = 1;
            goto L141
        L138:
            if (r1.equals("V_MPEG4/ISO/AP") == false) goto L4;
            r12 = 0;
            goto L141
        }

        @RequiresNonNull({"output"})
        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker r0 = this.trueHdSampleRechunker;
            if (r0 == null) goto L6;
            r0.outputPendingSampleMetadata(this.output, this.cryptoData);
            return;
        }

        public void reset() {
            TrueHdSampleRechunker r0 = this.trueHdSampleRechunker;
            if (r0 == null) goto L6;
            r0.reset();
            return;
        }
    }

    /* renamed from: $r8$lambda$kMhMytRa6Vcol9U_-_NkwwlQDB4, reason: not valid java name */
    public static /* synthetic */ Extractor[] m856$r8$lambda$kMhMytRa6Vcol9U__NkwwlQDB4() {
        return new Extractor[]{new MatroskaExtractor(SubtitleParser.Factory.UNSUPPORTED, 2)};
    }

    static {
        FACTORY = new MatroskaExtractor$$ExternalSyntheticLambda0();
        SUBRIP_PREFIX = new byte[]{49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
        SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
        SSA_PREFIX = new byte[]{68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
        VTT_PREFIX = new byte[]{87, 69, 66, 86, 84, 84, 10, 10, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 10};
        WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
        HashMap r0 = new HashMap();
        r0.put("htc_video_rotA-000", 0);
        r0.put("htc_video_rotA-090", 90);
        r0.put("htc_video_rotA-180", 180);
        r0.put("htc_video_rotA-270", 270);
        TRACK_NAME_TO_ROTATION_DEGREES = Collections.unmodifiableMap(r0);
    }

    public MatroskaExtractor(EbmlReader r5, int r6, SubtitleParser.Factory r7) {
        this.segmentContentPosition = -1;
        this.timecodeScale = -9223372036854775807L;
        this.durationTimecode = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = -9223372036854775807L;
        this.reader = r5;
        r5.init(new InnerEbmlProcessor(this, null));
        this.subtitleParserFactory = r7;
        boolean r72 = false;
        if ((r6 & 1) != 0) goto L5;
        boolean r52 = true;
    L6:
        this.seekForCuesEnabled = r52;
        if ((r6 & 2) != 0) goto L9;
        r72 = true;
    L9:
        this.parseSubtitlesDuringExtraction = r72;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
        this.supplementalData = new ParsableByteArray();
        this.blockSampleSizes = new int[1];
        return;
    L5:
        r52 = false;
        goto L6
    }

    public MatroskaExtractor(SubtitleParser.Factory r2, int r3) {
        this(new DefaultEbmlReader(), r3, r2);
    }

    public static /* synthetic */ byte[] access$500() {
        return SSA_DIALOGUE_FORMAT;
    }

    public static /* synthetic */ Map access$600() {
        return TRACK_NAME_TO_ROTATION_DEGREES;
    }

    public static /* synthetic */ UUID access$700() {
        return WAVE_SUBFORMAT_PCM;
    }

    @EnsuresNonNull({"cueTimesUs", "cueClusterPositions"})
    private void assertInCues(int r2) {
        if (this.cueTimesUs == null) goto L8;
        if (this.cueClusterPositions == null) goto L8;
        return;
    L8:
        throw ParserException.createForMalformedContainer("Element " + r2 + " must be in a Cues", null);
    }

    @EnsuresNonNull({"currentTrack"})
    private void assertInTrackEntry(int r2) {
        if (this.currentTrack == null) goto L6;
        return;
    L6:
        throw ParserException.createForMalformedContainer("Element " + r2 + " must be in a TrackEntry", null);
    }

    @EnsuresNonNull({"extractorOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.extractorOutput);
    }

    private SeekMap buildSeekMap(LongArray r12, LongArray r13) {
        if (this.segmentContentPosition == (-1)) goto L30;
        if (this.durationUs == (-9223372036854775807L)) goto L30;
        if (r12 == null) goto L30;
        if (r12.size() == 0) goto L30;
        if (r13 == null) goto L30;
        if (r13.size() != r12.size()) goto L30;
        int r0 = r12.size();
        int[] r1 = new int[r0];
        long[] r2 = new long[r0];
        long[] r3 = new long[r0];
        long[] r4 = new long[r0];
        int r5 = 0;
        int r6 = 0;
    L14:
        if (r6 >= r0) goto L16;
        r4[r6] = r12.get(r6);
        r2[r6] = this.segmentContentPosition + r13.get(r6);
        r6 = r6 + 1;
    L16:
        int r122 = r0 - 1;
        if (r5 >= r122) goto L19;
        int r123 = r5 + 1;
        r1[r5] = (int) (r2[r123] - r2[r5]);
        r3[r5] = r4[r123] - r4[r5];
        r5 = r123;
        goto L16
    L19:
        int r132 = r122;
    L20:
        if (r132 <= 0) goto L24;
        if (r4[r132] <= this.durationUs) goto L24;
        r132 = r132 - 1;
    L24:
        r1[r132] = (int) ((this.segmentContentPosition + this.segmentContentSize) - r2[r132]);
        r3[r132] = this.durationUs - r4[r132];
        if (r132 >= r122) goto L28;
        Log.w("MatroskaExtractor", "Discarding trailing cue points with timestamps greater than total duration");
        int r133 = r132 + 1;
        r1 = Arrays.copyOf(r1, r133);
        r2 = Arrays.copyOf(r2, r133);
        r3 = Arrays.copyOf(r3, r133);
        r4 = Arrays.copyOf(r4, r133);
    L28:
        return new ChunkIndex(r1, r2, r3, r4);
    L30:
        return new SeekMap.Unseekable(this.durationUs);
    }

    @RequiresNonNull({"#1.output"})
    private void commitSampleToOutput(Track r18, long r19, int r21, int r22, int r23) {
        TrueHdSampleRechunker r2 = r18.trueHdSampleRechunker;
        if (r2 == null) goto L6;
        r2.sampleMetadata(r18.output, r19, r21, r22, r23, r18.cryptoData);
    L35:
        this.haveOutputSample = true;
        return;
    L6:
        if ("S_TEXT/UTF8".equals(r18.codecId) == true) goto L14;
        if ("S_TEXT/ASS".equals(r18.codecId) == true) goto L14;
        if ("S_TEXT/SSA".equals(r18.codecId) == true) goto L14;
        if ("S_TEXT/WEBVTT".equals(r18.codecId) == true) goto L14;
    L19:
        int r24 = r22;
    L29:
        if ((r21 & 268435456) == 0) goto L34;
        int r3 = this.blockSampleCount;
        ParsableByteArray r4 = this.supplementalData;
        if (r3 <= 1) goto L33;
        r4.reset(0);
        goto L34
    L33:
        int r32 = r4.limit();
        r18.output.sampleData(this.supplementalData, r32, 2);
        r24 = r24 + r32;
    L34:
        TrackOutput r10 = r18.output;
        r10.sampleMetadata(r19, r21, r24, r23, r18.cryptoData);
    L14:
        if (this.blockSampleCount <= 1) goto L16;
        Log.w("MatroskaExtractor", "Skipping subtitle sample in laced block.");
        goto L19
    L16:
        long r42 = this.blockDurationUs;
        if (r42 != (-9223372036854775807L)) goto L20;
        Log.w("MatroskaExtractor", "Skipping subtitle sample with no duration.");
        goto L19
    L20:
        setSubtitleEndTime(r18.codecId, r42, this.subtitleSample.getData());
        int r25 = this.subtitleSample.getPosition();
    L22:
        if (r25 >= this.subtitleSample.limit()) goto L27;
        if (this.subtitleSample.getData()[r25] == 0) goto L25;
        r25 = r25 + 1;
        goto L22
    L25:
        this.subtitleSample.setLimit(r25);
    L27:
        TrackOutput r26 = r18.output;
        ParsableByteArray r33 = this.subtitleSample;
        r26.sampleData(r33, r33.limit());
        r24 = r22 + this.subtitleSample.limit();
        goto L29
    }

    private static int[] ensureArrayCapacity(int[] r1, int r2) {
        if (r1 != null) goto L6;
        return new int[r2];
    L6:
        if (r1.length < r2) goto L9;
        return r1;
    L9:
        return new int[Math.max(r1.length * 2, r2)];
    }

    private int finishWriteSampleData() {
        int r0 = this.sampleBytesWritten;
        resetWriteSampleData();
        return r0;
    }

    private static byte[] formatSubtitleTimecode(long r7, String r9, long r10) {
        if (r7 == (-9223372036854775807L)) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        int r2 = (int) (r7 / 3600000000L);
        long r72 = r7 - (r2 * 3600000000L);
        int r3 = (int) (r72 / 60000000);
        long r73 = r72 - (r3 * 60000000);
        int r4 = (int) (r73 / 1000000);
        return Util.getUtf8Bytes(String.format(Locale.US, r9, new Object[]{Integer.valueOf(r2), Integer.valueOf(r3), Integer.valueOf(r4), Integer.valueOf((int) ((r73 - (r4 * 1000000)) / r10))}));
    L5:
        r0 = false;
        goto L6
    }

    private static boolean isCodecSupported(String r4) {
        r4.getClass();
        char r3 = 65535;
        switch(r4.hashCode()) {
            case -2095576542: goto L138;
            case -2095575984: goto L134;
            case -1985379776: goto L130;
            case -1784763192: goto L126;
            case -1730367663: goto L122;
            case -1482641358: goto L118;
            case -1482641357: goto L114;
            case -1373388978: goto L110;
            case -933872740: goto L106;
            case -538363189: goto L102;
            case -538363109: goto L98;
            case -425012669: goto L94;
            case -356037306: goto L90;
            case 62923557: goto L86;
            case 62923603: goto L82;
            case 62927045: goto L78;
            case 82318131: goto L74;
            case 82338133: goto L70;
            case 82338134: goto L66;
            case 99146302: goto L62;
            case 444813526: goto L58;
            case 542569478: goto L54;
            case 635596514: goto L50;
            case 725948237: goto L46;
            case 725957860: goto L42;
            case 738597099: goto L38;
            case 738614379: goto L34;
            case 855502857: goto L30;
            case 1045209816: goto L26;
            case 1422270023: goto L22;
            case 1809237540: goto L18;
            case 1950749482: goto L14;
            case 1950789798: goto L10;
            case 1951062397: goto L6;
            default: goto L141;
        };
    L141:
        switch(r3) {
            case 0: goto L143;
            case 1: goto L143;
            case 2: goto L143;
            case 3: goto L143;
            case 4: goto L143;
            case 5: goto L143;
            case 6: goto L143;
            case 7: goto L143;
            case 8: goto L143;
            case 9: goto L143;
            case 10: goto L143;
            case 11: goto L143;
            case 12: goto L143;
            case 13: goto L143;
            case 14: goto L143;
            case 15: goto L143;
            case 16: goto L143;
            case 17: goto L143;
            case 18: goto L143;
            case 19: goto L143;
            case 20: goto L143;
            case 21: goto L143;
            case 22: goto L143;
            case 23: goto L143;
            case 24: goto L143;
            case 25: goto L143;
            case 26: goto L143;
            case 27: goto L143;
            case 28: goto L143;
            case 29: goto L143;
            case 30: goto L143;
            case 31: goto L143;
            case 32: goto L143;
            case 33: goto L143;
            default: goto L142;
        };
    L142:
        return false;
    L143:
        return true;
    L6:
        if (r4.equals("A_OPUS") == false) goto L141;
        r3 = '!';
        goto L141
    L10:
        if (r4.equals("A_FLAC") == false) goto L141;
        r3 = ' ';
        goto L141
    L14:
        if (r4.equals("A_EAC3") == false) goto L141;
        r3 = 31;
        goto L141
    L18:
        if (r4.equals("V_MPEG2") == false) goto L141;
        r3 = 30;
        goto L141
    L22:
        if (r4.equals("S_TEXT/UTF8") == false) goto L141;
        r3 = 29;
        goto L141
    L26:
        if (r4.equals("S_TEXT/WEBVTT") == false) goto L141;
        r3 = 28;
        goto L141
    L30:
        if (r4.equals("V_MPEGH/ISO/HEVC") == false) goto L141;
        r3 = 27;
        goto L141
    L34:
        if (r4.equals("S_TEXT/SSA") == false) goto L141;
        r3 = 26;
        goto L141
    L38:
        if (r4.equals("S_TEXT/ASS") == false) goto L141;
        r3 = 25;
        goto L141
    L42:
        if (r4.equals("A_PCM/INT/LIT") == false) goto L141;
        r3 = 24;
        goto L141
    L46:
        if (r4.equals("A_PCM/INT/BIG") == false) goto L141;
        r3 = 23;
        goto L141
    L50:
        if (r4.equals("A_PCM/FLOAT/IEEE") == false) goto L141;
        r3 = 22;
        goto L141
    L54:
        if (r4.equals("A_DTS/EXPRESS") == false) goto L141;
        r3 = 21;
        goto L141
    L58:
        if (r4.equals("V_THEORA") == false) goto L141;
        r3 = 20;
        goto L141
    L62:
        if (r4.equals("S_HDMV/PGS") == false) goto L141;
        r3 = 19;
        goto L141
    L66:
        if (r4.equals("V_VP9") == false) goto L141;
        r3 = 18;
        goto L141
    L70:
        if (r4.equals("V_VP8") == false) goto L141;
        r3 = 17;
        goto L141
    L74:
        if (r4.equals("V_AV1") == false) goto L141;
        r3 = 16;
        goto L141
    L78:
        if (r4.equals("A_DTS") == false) goto L141;
        r3 = 15;
        goto L141
    L82:
        if (r4.equals("A_AC3") == false) goto L141;
        r3 = 14;
        goto L141
    L86:
        if (r4.equals("A_AAC") == false) goto L141;
        r3 = '\r';
        goto L141
    L90:
        if (r4.equals("A_DTS/LOSSLESS") == false) goto L141;
        r3 = '\f';
        goto L141
    L94:
        if (r4.equals("S_VOBSUB") == false) goto L141;
        r3 = 11;
        goto L141
    L98:
        if (r4.equals("V_MPEG4/ISO/AVC") == false) goto L141;
        r3 = '\n';
        goto L141
    L102:
        if (r4.equals("V_MPEG4/ISO/ASP") == false) goto L141;
        r3 = '\t';
        goto L141
    L106:
        if (r4.equals("S_DVBSUB") == false) goto L141;
        r3 = '\b';
        goto L141
    L110:
        if (r4.equals("V_MS/VFW/FOURCC") == false) goto L141;
        r3 = 7;
        goto L141
    L114:
        if (r4.equals("A_MPEG/L3") == false) goto L141;
        r3 = 6;
        goto L141
    L118:
        if (r4.equals("A_MPEG/L2") == false) goto L141;
        r3 = 5;
        goto L141
    L122:
        if (r4.equals("A_VORBIS") == false) goto L141;
        r3 = 4;
        goto L141
    L126:
        if (r4.equals("A_TRUEHD") == false) goto L141;
        r3 = 3;
        goto L141
    L130:
        if (r4.equals("A_MS/ACM") == false) goto L141;
        r3 = 2;
        goto L141
    L134:
        if (r4.equals("V_MPEG4/ISO/SP") == false) goto L141;
        r3 = 1;
        goto L141
    L138:
        if (r4.equals("V_MPEG4/ISO/AP") == false) goto L141;
        r3 = 0;
        goto L141
    }

    private boolean maybeSeekForCues(PositionHolder r6, long r7) {
        if (this.seekForCues == false) goto L7;
        this.seekPositionAfterBuildingCues = r7;
        r6.position = this.cuesContentPosition;
        this.seekForCues = false;
        return true;
    L7:
        if (this.sentSeekMap == false) goto L12;
        long r72 = this.seekPositionAfterBuildingCues;
        if (r72 == (-1)) goto L12;
        r6.position = r72;
        this.seekPositionAfterBuildingCues = -1;
        return true;
    L12:
        return false;
    }

    private void readScratch(ExtractorInput r4, int r5) {
        if (this.scratch.limit() < r5) goto L6;
        return;
    L6:
        if (this.scratch.capacity() >= r5) goto L8;
        ParsableByteArray r0 = this.scratch;
        r0.ensureCapacity(Math.max(r0.capacity() * 2, r5));
    L8:
        r4.readFully(this.scratch.getData(), this.scratch.limit(), r5 - this.scratch.limit());
        this.scratch.setLimit(r5);
    }

    private void resetWriteSampleData() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset(0);
    }

    private long scaleTimecodeToUs(long r7) {
        long r2 = this.timecodeScale;
        if (r2 == (-9223372036854775807L)) goto L7;
        return Util.scaleLargeTimestamp(r7, r2, 1000);
    L7:
        throw ParserException.createForMalformedContainer("Can't scale timecode prior to timecodeScale being set.", null);
    }

    private static void setSubtitleEndTime(String r5, long r6, byte[] r8) {
        r5.getClass();
        char r2 = 65535;
        switch(r5.hashCode()) {
            case 738597099: goto L18;
            case 738614379: goto L14;
            case 1045209816: goto L10;
            case 1422270023: goto L6;
            default: goto L22;
        };
    L6:
        if (r5.equals("S_TEXT/UTF8") == false) goto L22;
        r2 = 3;
        goto L22
    L10:
        if (r5.equals("S_TEXT/WEBVTT") == false) goto L22;
        r2 = 2;
        goto L22
    L14:
        if (r5.equals("S_TEXT/SSA") == false) goto L22;
        r2 = 1;
        goto L22
    L18:
        if (r5.equals("S_TEXT/ASS") == false) goto L22;
        r2 = 0;
    L22:
        switch(r2) {
            case 0: goto L27;
            case 1: goto L27;
            case 2: goto L26;
            case 3: goto L25;
            default: goto L23;
        };
    L23:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L25:
        byte[] r52 = formatSubtitleTimecode(r6, "%02d:%02d:%02d,%03d", 1000);
        int r62 = 19;
    L28:
        System.arraycopy(r52, 0, r8, r62, r52.length);
        return;
    L26:
        r52 = formatSubtitleTimecode(r6, "%02d:%02d:%02d.%03d", 1000);
        r62 = 25;
        goto L28
    L27:
        r52 = formatSubtitleTimecode(r6, "%01d:%02d:%02d:%02d", NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        r62 = 21;
        goto L28
    }

    @RequiresNonNull({"#2.output"})
    private int writeSampleData(ExtractorInput r12, Track r13, int r14, boolean r15) {
        if ("S_TEXT/UTF8".equals(r13.codecId) == false) goto L7;
        writeSubtitleSampleData(r12, SUBRIP_PREFIX, r14);
        return finishWriteSampleData();
    L7:
        if ("S_TEXT/ASS".equals(r13.codecId) == false) goto L9;
    L95:
        writeSubtitleSampleData(r12, SSA_PREFIX, r14);
        return finishWriteSampleData();
    L9:
        if ("S_TEXT/SSA".equals(r13.codecId) == true) goto L95;
        if ("S_TEXT/WEBVTT".equals(r13.codecId) == false) goto L15;
        writeSubtitleSampleData(r12, VTT_PREFIX, r14);
        return finishWriteSampleData();
    L15:
        TrackOutput r0 = r13.output;
        boolean r4 = true;
        if (this.sampleEncodingHandled == false) goto L18;
    L68:
        int r142 = r14 + this.sampleStrippedBytes.limit();
        if ("V_MPEG4/ISO/AVC".equals(r13.codecId) == false) goto L71;
    L83:
        byte[] r152 = this.nalLength.getData();
        r152[0] = 0;
        r152[1] = 0;
        r152[2] = 0;
        int r1 = r13.nalUnitLengthFieldLength;
        int r3 = 4 - r1;
    L85:
        if (this.sampleBytesRead >= r142) goto L91;
        int r42 = this.sampleCurrentNalBytesRemaining;
        if (r42 == 0) goto L88;
        int r43 = writeToOutput(r12, r0, r42);
        this.sampleBytesRead += r43;
        this.sampleBytesWritten += r43;
        this.sampleCurrentNalBytesRemaining -= r43;
        goto L85
    L88:
        writeToTarget(r12, r152, r3, r1);
        this.sampleBytesRead += r1;
        this.nalLength.setPosition(0);
        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
        this.nalStartCode.setPosition(0);
        r0.sampleData(this.nalStartCode, 4);
        this.sampleBytesWritten += 4;
    L91:
        if ("A_VORBIS".equals(r13.codecId) == false) goto L94;
        this.vorbisNumPageSamples.setPosition(0);
        r0.sampleData(this.vorbisNumPageSamples, 4);
        this.sampleBytesWritten += 4;
    L94:
        return finishWriteSampleData();
    L71:
        if ("V_MPEGH/ISO/HEVC".equals(r13.codecId) == true) goto L83;
        if (r13.trueHdSampleRechunker == null) goto L80;
        if (this.sampleStrippedBytes.limit() == 0) goto L79;
        r4 = false;
    L79:
        Assertions.checkState(r4);
        r13.trueHdSampleRechunker.startSample(r12);
    L80:
        int r153 = this.sampleBytesRead;
        if (r153 >= r142) goto L91;
        int r154 = writeToOutput(r12, r0, r142 - r153);
        this.sampleBytesRead += r154;
        this.sampleBytesWritten += r154;
        goto L80
    L18:
        if (r13.hasContentEncryption == false) goto L61;
        this.blockFlags &= -1073741825;
        int r6 = 128;
        if (this.sampleSignalByteRead == true) goto L26;
        r12.readFully(this.scratch.getData(), 0, 1);
        this.sampleBytesRead++;
        if ((this.scratch.getData()[0] & ByteCompanionObject.MIN_VALUE) == 128) goto L25;
        this.sampleSignalByte = this.scratch.getData()[0];
        this.sampleSignalByteRead = true;
        goto L26
    L25:
        throw ParserException.createForMalformedContainer("Extension bit is set in signal byte", null);
    L26:
        byte r16 = this.sampleSignalByte;
        if ((r16 & 1) != 1) goto L65;
        if ((r16 & 2) != 2) goto L31;
        boolean r17 = true;
    L32:
        this.blockFlags |= TLObject.FLAG_30;
        if (this.sampleInitializationVectorRead == true) goto L39;
        r12.readFully(this.encryptionInitializationVector.getData(), 0, 8);
        this.sampleBytesRead += 8;
        this.sampleInitializationVectorRead = true;
        byte[] r7 = this.scratch.getData();
        if (r17 == true) goto L38;
        r6 = 0;
    L38:
        r7[0] = (byte) (r6 | 8);
        this.scratch.setPosition(0);
        r0.sampleData(this.scratch, 1, 1);
        this.sampleBytesWritten++;
        this.encryptionInitializationVector.setPosition(0);
        r0.sampleData(this.encryptionInitializationVector, 8, 1);
        this.sampleBytesWritten += 8;
    L39:
        if (r17 == false) goto L65;
        if (this.samplePartitionCountRead == true) goto L43;
        r12.readFully(this.scratch.getData(), 0, 1);
        this.sampleBytesRead++;
        this.scratch.setPosition(0);
        this.samplePartitionCount = this.scratch.readUnsignedByte();
        this.samplePartitionCountRead = true;
    L43:
        int r18 = this.samplePartitionCount * 4;
        this.scratch.reset(r18);
        r12.readFully(this.scratch.getData(), 0, r18);
        this.sampleBytesRead += r18;
        short r19 = (short) ((this.samplePartitionCount / 2) + 1);
        int r62 = (r19 * 6) + 2;
        ByteBuffer r72 = this.encryptionSubsampleDataBuffer;
        if (r72 != null) goto L46;
    L47:
        this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(r62);
    L48:
        this.encryptionSubsampleDataBuffer.position(0);
        this.encryptionSubsampleDataBuffer.putShort(r19);
        int r110 = 0;
        int r73 = 0;
    L49:
        int r8 = this.samplePartitionCount;
        if (r110 >= r8) goto L56;
        int r82 = this.scratch.readUnsignedIntToInt();
        int r9 = r110 % 2;
        ByteBuffer r10 = this.encryptionSubsampleDataBuffer;
        if (r9 != 0) goto L54;
        r10.putShort((short) (r82 - r73));
    L55:
        r110 = r110 + 1;
        r73 = r82;
        goto L49
    L54:
        r10.putInt(r82 - r73);
        goto L55
    L56:
        int r111 = (r14 - this.sampleBytesRead) - r73;
        int r83 = r8 % 2;
        ByteBuffer r74 = this.encryptionSubsampleDataBuffer;
        if (r83 != 1) goto L59;
        r74.putInt(r111);
    L60:
        this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), r62);
        r0.sampleData(this.encryptionSubsampleData, r62, 1);
        this.sampleBytesWritten += r62;
        goto L65
    L59:
        r74.putShort((short) r111);
        this.encryptionSubsampleDataBuffer.putInt(0);
        goto L60
    L46:
        if (r72.capacity() >= r62) goto L48;
    L31:
        r17 = false;
    L65:
        if (Track.access$400(r13, r15) == false) goto L67;
        this.blockFlags |= 268435456;
        this.supplementalData.reset(0);
        int r155 = (this.sampleStrippedBytes.limit() + r14) - this.sampleBytesRead;
        this.scratch.reset(4);
        this.scratch.getData()[0] = (byte) ((r155 >> 24) & 255);
        this.scratch.getData()[1] = (byte) ((r155 >> 16) & 255);
        this.scratch.getData()[2] = (byte) ((r155 >> 8) & 255);
        this.scratch.getData()[3] = (byte) (r155 & 255);
        r0.sampleData(this.scratch, 4, 2);
        this.sampleBytesWritten += 4;
    L67:
        this.sampleEncodingHandled = true;
        goto L68
    L61:
        byte[] r112 = r13.sampleStrippedBytes;
        if (r112 == null) goto L65;
        this.sampleStrippedBytes.reset(r112, r112.length);
        goto L65
    }

    private void writeSubtitleSampleData(ExtractorInput r5, byte[] r6, int r7) {
        int r0 = r6.length + r7;
        int r1 = this.subtitleSample.capacity();
        ParsableByteArray r2 = this.subtitleSample;
        if (r1 >= r0) goto L5;
        r2.reset(Arrays.copyOf(r6, r0 + r7));
    L6:
        r5.readFully(this.subtitleSample.getData(), r6.length, r7);
        this.subtitleSample.setPosition(0);
        this.subtitleSample.setLimit(r0);
        return;
    L5:
        System.arraycopy(r6, 0, r2.getData(), 0, r6.length);
        goto L6
    }

    private int writeToOutput(ExtractorInput r2, TrackOutput r3, int r4) {
        int r0 = this.sampleStrippedBytes.bytesLeft();
        if (r0 <= 0) goto L7;
        int r22 = Math.min(r4, r0);
        r3.sampleData(this.sampleStrippedBytes, r22);
        return r22;
    L7:
        return r3.sampleData(r2, r4, false);
    }

    private void writeToTarget(ExtractorInput r3, byte[] r4, int r5, int r6) {
        int r0 = Math.min(r6, this.sampleStrippedBytes.bytesLeft());
        r3.readFully(r4, r5 + r0, r6 - r0);
        if (r0 <= 0) goto L6;
        this.sampleStrippedBytes.readBytes(r4, r5, r0);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void binaryElement(int r25, int r26, ExtractorInput r27) {
        ExtractorInput r7 = r27;
        int r8 = 0;
        int r9 = 1;
        if (r25 == 161) goto L38;
        if (r25 == 163) goto L38;
        if (r25 == 165) goto L34;
        if (r25 != 16877) goto L10;
        handleBlockAddIDExtraData(getCurrentTrack(r25), r7, r26);
        return;
    L10:
        if (r25 != 16981) goto L12;
        assertInTrackEntry(r25);
        byte[] r1 = new byte[r26];
        this.currentTrack.sampleStrippedBytes = r1;
        r7.readFully(r1, 0, r26);
        return;
    L12:
        if (r25 != 18402) goto L14;
        byte[] r3 = new byte[r26];
        r7.readFully(r3, 0, r26);
        getCurrentTrack(r25).cryptoData = new TrackOutput.CryptoData(1, r3, 0, 0);
        return;
    L14:
        if (r25 != 21419) goto L16;
        Arrays.fill(this.seekEntryIdBytes.getData(), (byte) 0);
        r7.readFully(this.seekEntryIdBytes.getData(), 4 - r26, r26);
        this.seekEntryIdBytes.setPosition(0);
        this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
        return;
    L16:
        if (r25 != 25506) goto L18;
        assertInTrackEntry(r25);
        byte[] r12 = new byte[r26];
        this.currentTrack.codecPrivate = r12;
        r7.readFully(r12, 0, r26);
        return;
    L18:
        if (r25 != 30322) goto L22;
        assertInTrackEntry(r25);
        byte[] r13 = new byte[r26];
        this.currentTrack.projectionData = r13;
        r7.readFully(r13, 0, r26);
        return;
    L22:
        throw ParserException.createForMalformedContainer("Unexpected id: " + r25, null);
    L34:
        if (this.blockState != 2) goto L120;
        handleBlockAdditionalData(this.tracks.get(this.blockTrackNumber), this.blockAdditionalId, r7, r26);
        return;
    L120:
        return;
    L38:
        int r10 = 8;
        if (this.blockState != 0) goto L41;
        this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(r7, false, true, 8);
        this.blockTrackNumberLength = this.varintReader.getLastLength();
        this.blockDurationUs = -9223372036854775807L;
        this.blockState = 1;
        this.scratch.reset(0);
    L41:
        Track r32 = this.tracks.get(this.blockTrackNumber);
        if (r32 != null) goto L45;
        r7.skipFully(r26 - this.blockTrackNumberLength);
        this.blockState = 0;
        return;
    L45:
        Track.access$100(r32);
        if (this.blockState != 1) goto L109;
        readScratch(r7, 3);
        int r122 = (this.scratch.getData()[2] & 6) >> 1;
        if (r122 != 0) goto L52;
        this.blockSampleCount = 1;
        int[] r5 = ensureArrayCapacity(this.blockSampleSizes, 1);
        this.blockSampleSizes = r5;
        r5[0] = (r26 - this.blockTrackNumberLength) - 3;
    L50:
        int r16 = 0;
        int r17 = 1;
    L97:
        this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((this.scratch.getData()[r16] << 8) | (this.scratch.getData()[r17] & UByte.MAX_VALUE));
        if (r32.type != 2) goto L100;
    L105:
        int r2 = r17;
    L106:
        this.blockFlags = r2;
        this.blockState = 2;
        this.blockSampleIndex = r16;
        int r22 = 163;
    L110:
        if (r25 != r22) goto L117;
    L111:
        int r14 = this.blockSampleIndex;
        if (r14 >= this.blockSampleCount) goto L114;
        int r52 = writeSampleData(r27, r32, this.blockSampleSizes[r14], false);
        Track r15 = r32;
        commitSampleToOutput(r15, this.blockTimeUs + ((this.blockSampleIndex * r32.defaultSampleDurationNs) / 1000), this.blockFlags, r52, 0);
        this.blockSampleIndex++;
        r32 = r15;
        goto L111
    L114:
        this.blockState = 0;
        return;
    L117:
        int r23 = this.blockSampleIndex;
        if (r23 >= this.blockSampleCount) goto L134;
        int[] r33 = this.blockSampleSizes;
        boolean r53 = r17;
        r33[r23] = writeSampleData(r27, r32, r33[r23], r53);
        this.blockSampleIndex += r53 ? 1 : 0;
        goto L117
    L134:
        return;
    L100:
        if (r25 == 163) goto L102;
    L104:
        r2 = r16;
        goto L106
    L102:
        if ((this.scratch.getData()[2] & ByteCompanionObject.MIN_VALUE) != 128) goto L104;
    L52:
        readScratch(r7, 4);
        int r152 = (this.scratch.getData()[3] & UByte.MAX_VALUE) + 1;
        this.blockSampleCount = r152;
        int[] r142 = ensureArrayCapacity(this.blockSampleSizes, r152);
        this.blockSampleSizes = r142;
        if (r122 != 2) goto L55;
        int r24 = (r26 - this.blockTrackNumberLength) - 4;
        int r54 = this.blockSampleCount;
        Arrays.fill(r142, 0, r54, r24 / r54);
        goto L50
    L55:
        if (r122 != 1) goto L65;
        int r55 = 0;
        int r11 = 0;
        int r143 = 4;
    L57:
        int r123 = this.blockSampleCount;
        int r153 = r123 - 1;
        r17 = r9;
        int[] r92 = this.blockSampleSizes;
        if (r55 >= r153) goto L64;
        r92[r55] = 0;
    L60:
        int r93 = r143 + 1;
        readScratch(r7, r93);
        int r124 = this.scratch.getData()[r143] & UByte.MAX_VALUE;
        int[] r144 = this.blockSampleSizes;
        int r154 = r144[r55] + r124;
        r144[r55] = r154;
        if (r124 != 255) goto L62;
        r143 = r93;
        goto L60
    L62:
        r11 = r11 + r154;
        r55 = r55 + 1;
        r143 = r93;
        r9 = r17;
        goto L57
    L64:
        r92[r123 - 1] = ((r26 - this.blockTrackNumberLength) - r143) - r11;
        r16 = 0;
        goto L97
    L65:
        r17 = 1;
        if (r122 != 3) goto L108;
        int r94 = 0;
        int r112 = 0;
        int r145 = 4;
    L68:
        int r125 = this.blockSampleCount;
        int r155 = r125 - 1;
        r16 = r8;
        int[] r82 = this.blockSampleSizes;
        if (r94 >= r155) goto L96;
        r82[r94] = r16;
        int r83 = r145 + 1;
        readScratch(r7, r83);
        if (this.scratch.getData()[r145] == 0) goto L95;
        int r126 = r16;
    L73:
        if (r126 >= r10) goto L82;
        int r156 = 1 << (7 - r126);
        int r18 = r10;
        if ((this.scratch.getData()[r145] & r156) != 0) goto L76;
        r126 = r126 + 1;
        r7 = r27;
        r10 = r18;
        goto L73
    L76:
        r83 = r83 + r126;
        readScratch(r7, r83);
        int r19 = r145 + 1;
        long r146 = (this.scratch.getData()[r145] & UByte.MAX_VALUE) & (~r156);
        int r102 = r19;
    L77:
        if (r102 >= r83) goto L79;
        r146 = (r146 << r18) | (this.scratch.getData()[r102] & UByte.MAX_VALUE);
        r102 = r102 + 1;
        goto L77
    L79:
        if (r94 <= 0) goto L84;
        r146 = r146 - ((1 << ((r126 * 7) + 6)) - 1);
    L84:
        if (r146 < (-2147483648L)) goto L93;
        if (r146 > 2147483647L) goto L93;
        int r4 = (int) r146;
        int[] r6 = this.blockSampleSizes;
        if (r94 == 0) goto L91;
        r4 = r4 + r6[r94 - 1];
    L91:
        r6[r94] = r4;
        r112 = r112 + r4;
        r94 = r94 + 1;
        r7 = r27;
        r145 = r83;
        r8 = r16;
        r10 = r18;
    L93:
        throw ParserException.createForMalformedContainer("EBML lacing sample size out of range.", null);
    L82:
        r18 = r10;
        r146 = 0;
        goto L84
    L95:
        throw ParserException.createForMalformedContainer("No valid varint length mask found", null);
    L96:
        r82[r125 - 1] = ((r26 - this.blockTrackNumberLength) - r145) - r112;
        goto L97
    L108:
        throw ParserException.createForMalformedContainer("Unexpected lacing value: " + r122, null);
    L109:
        r17 = 1;
        r22 = 163;
        goto L110
    }

    public void endMasterElement(int r9) {
        assertInitialized();
        if (r9 == 160) goto L70;
        if (r9 != 174) goto L7;
        Track r92 = (Track) Assertions.checkStateNotNull(this.currentTrack);
        String r0 = r92.codecId;
        if (r0 == null) goto L68;
        if (isCodecSupported(r0) == false) goto L65;
        r92.initializeOutput(this.extractorOutput, r92.number);
        this.tracks.put(r92.number, r92);
    L65:
        this.currentTrack = null;
        return;
    L68:
        throw ParserException.createForMalformedContainer("CodecId is missing in TrackEntry element", null);
    L7:
        if (r9 != 19899) goto L9;
        int r93 = this.seekEntryId;
        if (r93 == (-1)) goto L59;
        long r3 = this.seekEntryPosition;
        if (r3 == (-1)) goto L59;
        if (r93 != 475249515) goto L99;
        this.cuesContentPosition = r3;
        return;
    L99:
        return;
    L59:
        throw ParserException.createForMalformedContainer("Mandatory element SeekID or SeekPosition not found", null);
    L9:
        if (r9 != 25152) goto L11;
        assertInTrackEntry(r9);
        Track r94 = this.currentTrack;
        if (r94.hasContentEncryption == true) goto L46;
        return;
    L46:
        if (r94.cryptoData == null) goto L50;
        r94.drmInitData = new DrmInitData(new DrmInitData.SchemeData[]{new DrmInitData.SchemeData(C.UUID_NIL, "video/webm", this.currentTrack.cryptoData.encryptionKey)});
        return;
    L50:
        throw ParserException.createForMalformedContainer("Encrypted Track found but ContentEncKeyID was not found", null);
    L11:
        if (r9 != 28032) goto L13;
        assertInTrackEntry(r9);
        Track r8 = this.currentTrack;
        if (r8.hasContentEncryption == true) goto L39;
        return;
    L39:
        if (r8.sampleStrippedBytes != null) goto L42;
        return;
    L42:
        throw ParserException.createForMalformedContainer("Combining encryption and compression is not supported", null);
    L13:
        if (r9 == 357149030) goto L30;
        if (r9 == 374648427) goto L24;
        if (r9 == 475249515) goto L19;
        return;
    L19:
        if (this.sentSeekMap == true) goto L21;
        this.extractorOutput.seekMap(buildSeekMap(this.cueTimesUs, this.cueClusterPositions));
        this.sentSeekMap = true;
    L21:
        this.cueTimesUs = null;
        this.cueClusterPositions = null;
        return;
    L24:
        if (this.tracks.size() == 0) goto L28;
        this.extractorOutput.endTracks();
        return;
    L28:
        throw ParserException.createForMalformedContainer("No valid tracks were found", null);
    L30:
        if (this.timecodeScale != (-9223372036854775807L)) goto L32;
        this.timecodeScale = 1000000;
    L32:
        long r02 = this.durationTimecode;
        if (r02 == (-9223372036854775807L)) goto L71;
        this.durationUs = scaleTimecodeToUs(r02);
        return;
    L71:
        return;
    L70:
        if (this.blockState != 2) goto L100;
        Track r1 = this.tracks.get(this.blockTrackNumber);
        Track.access$100(r1);
        if (this.blockGroupDiscardPaddingNs > 0) goto L75;
    L77:
        int r03 = 0;
        int r2 = 0;
    L79:
        if (r03 >= this.blockSampleCount) goto L81;
        r2 = r2 + this.blockSampleSizes[r03];
        r03 = r03 + 1;
        goto L79
    L81:
        int r7 = 0;
    L83:
        if (r7 >= this.blockSampleCount) goto L90;
        long r32 = this.blockTimeUs + ((r1.defaultSampleDurationNs * r7) / 1000);
        int r04 = this.blockFlags;
        if (r7 != 0) goto L89;
        if (this.blockHasReferenceBlock == true) goto L89;
        r04 = r04 | 1;
    L89:
        int r5 = this.blockSampleSizes[r7];
        int r6 = r2 - r5;
        commitSampleToOutput(r1, r32, r04, r5, r6);
        r7 = r7 + 1;
        r2 = r6;
        goto L83
    L90:
        this.blockState = 0;
        return;
    L75:
        if ("A_OPUS".equals(r1.codecId) == false) goto L77;
        this.supplementalData.reset(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.blockGroupDiscardPaddingNs).array());
        goto L77
    }

    public void floatElement(int r2, double r3) {
        if (r2 != 181) goto L5;
        Track r1 = getCurrentTrack(r2);
        r1.sampleRate = (int) r3;
        return;
    L5:
        if (r2 == 17545) goto L35;
        switch(r2) {
            case 21969: goto L33;
            case 21970: goto L31;
            case 21971: goto L29;
            case 21972: goto L27;
            case 21973: goto L25;
            case 21974: goto L23;
            case 21975: goto L21;
            case 21976: goto L19;
            case 21977: goto L17;
            case 21978: goto L15;
            default: goto L7;
        };
    L7:
        switch(r2) {
            case 30323: goto L13;
            case 30324: goto L11;
            case 30325: goto L9;
            default: goto L8;
        };
    L8:
        return;
    L9:
        Track r12 = getCurrentTrack(r2);
        r12.projectionPoseRoll = (float) r3;
        return;
    L11:
        Track r13 = getCurrentTrack(r2);
        r13.projectionPosePitch = (float) r3;
        return;
    L13:
        Track r14 = getCurrentTrack(r2);
        r14.projectionPoseYaw = (float) r3;
        return;
    L15:
        Track r15 = getCurrentTrack(r2);
        r15.minMasteringLuminance = (float) r3;
        return;
    L17:
        Track r16 = getCurrentTrack(r2);
        r16.maxMasteringLuminance = (float) r3;
        return;
    L19:
        Track r17 = getCurrentTrack(r2);
        r17.whitePointChromaticityY = (float) r3;
        return;
    L21:
        Track r18 = getCurrentTrack(r2);
        r18.whitePointChromaticityX = (float) r3;
        return;
    L23:
        Track r19 = getCurrentTrack(r2);
        r19.primaryBChromaticityY = (float) r3;
        return;
    L25:
        Track r110 = getCurrentTrack(r2);
        r110.primaryBChromaticityX = (float) r3;
        return;
    L27:
        Track r111 = getCurrentTrack(r2);
        r111.primaryGChromaticityY = (float) r3;
        return;
    L29:
        Track r112 = getCurrentTrack(r2);
        r112.primaryGChromaticityX = (float) r3;
        return;
    L31:
        Track r113 = getCurrentTrack(r2);
        r113.primaryRChromaticityY = (float) r3;
        return;
    L33:
        Track r114 = getCurrentTrack(r2);
        r114.primaryRChromaticityX = (float) r3;
        return;
    L35:
        this.durationTimecode = (long) r3;
    }

    public Track getCurrentTrack(int r1) {
        assertInTrackEntry(r1);
        return this.currentTrack;
    }

    public int getElementType(int r1) {
        switch(r1) {
            case 131: goto L13;
            case 134: goto L11;
            case 136: goto L13;
            case 155: goto L13;
            case 159: goto L13;
            case 160: goto L9;
            case 161: goto L7;
            case 163: goto L7;
            case 165: goto L7;
            case 166: goto L9;
            case 174: goto L9;
            case 176: goto L13;
            case 179: goto L13;
            case 181: goto L5;
            case 183: goto L9;
            case 186: goto L13;
            case 187: goto L9;
            case 215: goto L13;
            case 224: goto L9;
            case 225: goto L9;
            case 231: goto L13;
            case 238: goto L13;
            case 241: goto L13;
            case 251: goto L13;
            case 16868: goto L9;
            case 16871: goto L13;
            case 16877: goto L7;
            case 16980: goto L13;
            case 16981: goto L7;
            case 17026: goto L11;
            case 17029: goto L13;
            case 17143: goto L13;
            case 17545: goto L5;
            case 18401: goto L13;
            case 18402: goto L7;
            case 18407: goto L9;
            case 18408: goto L13;
            case 19899: goto L9;
            case 20529: goto L13;
            case 20530: goto L13;
            case 20532: goto L9;
            case 20533: goto L9;
            case 21358: goto L11;
            case 21419: goto L7;
            case 21420: goto L13;
            case 21432: goto L13;
            case 21680: goto L13;
            case 21682: goto L13;
            case 21690: goto L13;
            case 21930: goto L13;
            case 21936: goto L9;
            case 21938: goto L13;
            case 21945: goto L13;
            case 21946: goto L13;
            case 21947: goto L13;
            case 21948: goto L13;
            case 21949: goto L13;
            case 21968: goto L9;
            case 21969: goto L5;
            case 21970: goto L5;
            case 21971: goto L5;
            case 21972: goto L5;
            case 21973: goto L5;
            case 21974: goto L5;
            case 21975: goto L5;
            case 21976: goto L5;
            case 21977: goto L5;
            case 21978: goto L5;
            case 21998: goto L13;
            case 22186: goto L13;
            case 22203: goto L13;
            case 25152: goto L9;
            case 25188: goto L13;
            case 25506: goto L7;
            case 28032: goto L9;
            case 30113: goto L9;
            case 30114: goto L13;
            case 30320: goto L9;
            case 30321: goto L13;
            case 30322: goto L7;
            case 30323: goto L5;
            case 30324: goto L5;
            case 30325: goto L5;
            case 2274716: goto L11;
            case 2352003: goto L13;
            case 2807729: goto L13;
            case 290298740: goto L9;
            case 357149030: goto L9;
            case 374648427: goto L9;
            case 408125543: goto L9;
            case 440786851: goto L9;
            case 475249515: goto L9;
            case 524531317: goto L9;
            default: goto L3;
        };
    L3:
        return 0;
    L5:
        return 5;
    L7:
        return 4;
    L9:
        return 1;
    L11:
        return 3;
    L13:
        return 2;
    }

    public void handleBlockAddIDExtraData(Track r2, ExtractorInput r3, int r4) {
        if (Track.access$200(r2) != 1685485123) goto L5;
    L9:
        byte[] r1 = new byte[r4];
        r2.dolbyVisionConfigBytes = r1;
        r3.readFully(r1, 0, r4);
        return;
    L5:
        if (Track.access$200(r2) == 1685480259) goto L9;
        r3.skipFully(r4);
    }

    public void handleBlockAdditionalData(Track r2, int r3, ExtractorInput r4, int r5) {
        if (r3 == 4) goto L5;
    L8:
        r4.skipFully(r5);
        return;
    L5:
        if ("V_VP9".equals(r2.codecId) == false) goto L8;
        this.supplementalData.reset(r5);
        r4.readFully(this.supplementalData.getData(), 0, r5);
    }

    @Override // androidx.media3.extractor.Extractor
    public final void init(ExtractorOutput r3) {
        if (this.parseSubtitlesDuringExtraction == false) goto L5;
        r3 = new SubtitleTranscodingExtractorOutput(r3, this.subtitleParserFactory);
    L5:
        this.extractorOutput = r3;
    }

    public void integerElement(int r9, long r10) {
        if (r9 == 20529) goto L153;
        if (r9 == 20530) goto L148;
        boolean r0 = false;
        switch(r9) {
            case 131: goto L145;
            case 136: goto L140;
            case 155: goto L138;
            case 159: goto L136;
            case 176: goto L134;
            case 179: goto L132;
            case 186: goto L130;
            case 215: goto L128;
            case 231: goto L126;
            case 238: goto L124;
            case 241: goto L121;
            case 251: goto L118;
            case 16871: goto L116;
            case 16980: goto L112;
            case 17029: goto L105;
            case 17143: goto L100;
            case 18401: goto L95;
            case 18408: goto L90;
            case 21420: goto L87;
            case 21432: goto L72;
            case 21680: goto L70;
            case 21682: goto L68;
            case 21690: goto L66;
            case 21930: goto L61;
            case 21938: goto L59;
            case 21998: goto L57;
            case 22186: goto L55;
            case 22203: goto L53;
            case 25188: goto L51;
            case 30114: goto L49;
            case 30321: goto L35;
            case 2352003: goto L33;
            case 2807729: goto L31;
            default: goto L9;
        };
    L31:
        this.timecodeScale = r10;
        return;
    L33:
        getCurrentTrack(r9).defaultSampleDurationNs = (int) r10;
        return;
    L35:
        assertInTrackEntry(r9);
        int r92 = (int) r10;
        if (r92 == 0) goto L47;
        if (r92 == 1) goto L45;
        if (r92 == 2) goto L43;
        if (r92 != 3) goto L162;
        this.currentTrack.projectionType = 3;
        return;
    L162:
        return;
    L43:
        this.currentTrack.projectionType = 2;
        return;
    L45:
        this.currentTrack.projectionType = 1;
        return;
    L47:
        this.currentTrack.projectionType = 0;
        return;
    L49:
        this.blockGroupDiscardPaddingNs = r10;
        return;
    L51:
        getCurrentTrack(r9).audioBitDepth = (int) r10;
        return;
    L53:
        getCurrentTrack(r9).seekPreRollNs = r10;
        return;
    L55:
        getCurrentTrack(r9).codecDelayNs = r10;
        return;
    L57:
        getCurrentTrack(r9).maxBlockAdditionId = (int) r10;
        return;
    L59:
        assertInTrackEntry(r9);
        Track r8 = this.currentTrack;
        r8.hasColorInfo = true;
        r8.bitsPerChannel = (int) r10;
        return;
    L61:
        Track r82 = getCurrentTrack(r9);
        if (r10 != 1) goto L64;
        r0 = true;
    L64:
        r82.flagForced = r0;
        return;
    L66:
        getCurrentTrack(r9).displayHeight = (int) r10;
        return;
    L68:
        getCurrentTrack(r9).displayUnit = (int) r10;
        return;
    L70:
        getCurrentTrack(r9).displayWidth = (int) r10;
        return;
    L72:
        int r102 = (int) r10;
        assertInTrackEntry(r9);
        if (r102 == 0) goto L85;
        if (r102 == 1) goto L83;
        if (r102 != 3) goto L77;
        this.currentTrack.stereoMode = 1;
        return;
    L77:
        if (r102 != 15) goto L163;
        this.currentTrack.stereoMode = 3;
        return;
    L163:
        return;
    L83:
        this.currentTrack.stereoMode = 2;
        return;
    L85:
        this.currentTrack.stereoMode = 0;
        return;
    L87:
        this.seekEntryPosition = r10 + this.segmentContentPosition;
        return;
    L116:
        Track.access$202(getCurrentTrack(r9), (int) r10);
        return;
    L118:
        this.blockHasReferenceBlock = true;
        return;
    L124:
        this.blockAdditionalId = (int) r10;
        return;
    L126:
        this.clusterTimecodeUs = scaleTimecodeToUs(r10);
        return;
    L128:
        getCurrentTrack(r9).number = (int) r10;
        return;
    L130:
        getCurrentTrack(r9).height = (int) r10;
        return;
    L132:
        assertInCues(r9);
        this.cueTimesUs.add(scaleTimecodeToUs(r10));
        return;
    L134:
        getCurrentTrack(r9).width = (int) r10;
        return;
    L136:
        getCurrentTrack(r9).channelCount = (int) r10;
        return;
    L138:
        this.blockDurationUs = scaleTimecodeToUs(r10);
        return;
    L140:
        Track r83 = getCurrentTrack(r9);
        if (r10 != 1) goto L143;
        r0 = true;
    L143:
        r83.flagDefault = r0;
        return;
    L145:
        getCurrentTrack(r9).type = (int) r10;
        return;
    L9:
        switch(r9) {
            case 21945: goto L23;
            case 21946: goto L19;
            case 21947: goto L15;
            case 21948: goto L13;
            case 21949: goto L11;
            default: goto L160;
        };
    L11:
        getCurrentTrack(r9).maxFrameAverageLuminance = (int) r10;
        return;
    L13:
        getCurrentTrack(r9).maxContentLuminance = (int) r10;
        return;
    L15:
        assertInTrackEntry(r9);
        this.currentTrack.hasColorInfo = true;
        int r93 = ColorInfo.isoColorPrimariesToColorSpace((int) r10);
        if (r93 == (-1)) goto L154;
        this.currentTrack.colorSpace = r93;
        return;
    L154:
        return;
    L19:
        assertInTrackEntry(r9);
        int r94 = ColorInfo.isoTransferCharacteristicsToColorTransfer((int) r10);
        if (r94 == (-1)) goto L157;
        this.currentTrack.colorTransfer = r94;
        return;
    L157:
        return;
    L23:
        assertInTrackEntry(r9);
        int r95 = (int) r10;
        if (r95 == 1) goto L29;
        if (r95 != 2) goto L161;
        this.currentTrack.colorRange = 1;
        return;
    L161:
        return;
    L29:
        this.currentTrack.colorRange = 2;
        return;
    L160:
        return;
    L90:
        if (r10 != 1) goto L93;
        return;
    L93:
        throw ParserException.createForMalformedContainer("AESSettingsCipherMode " + r10 + " not supported", null);
    L95:
        if (r10 != 5) goto L98;
        return;
    L98:
        throw ParserException.createForMalformedContainer("ContentEncAlgo " + r10 + " not supported", null);
    L100:
        if (r10 != 1) goto L103;
        return;
    L103:
        throw ParserException.createForMalformedContainer("EBMLReadVersion " + r10 + " not supported", null);
    L105:
        if (r10 < 1) goto L110;
        if (r10 > 2) goto L110;
        return;
    L110:
        throw ParserException.createForMalformedContainer("DocTypeReadVersion " + r10 + " not supported", null);
    L112:
        if (r10 != 3) goto L115;
        return;
    L115:
        throw ParserException.createForMalformedContainer("ContentCompAlgo " + r10 + " not supported", null);
    L121:
        if (this.seenClusterPositionForCurrentCuePoint == true) goto L158;
        assertInCues(r9);
        this.cueClusterPositions.add(r10);
        this.seenClusterPositionForCurrentCuePoint = true;
        return;
    L158:
        return;
    L148:
        if (r10 != 1) goto L151;
        return;
    L151:
        throw ParserException.createForMalformedContainer("ContentEncodingScope " + r10 + " not supported", null);
    L153:
        if (r10 != 0) goto L156;
        return;
    L156:
        throw ParserException.createForMalformedContainer("ContentEncodingOrder " + r10 + " not supported", null);
    }

    public boolean isLevel1Element(int r1) {
        if (r1 != 357149030) goto L5;
        return true;
    L5:
        if (r1 != 524531317) goto L7;
        return true;
    L7:
        if (r1 != 475249515) goto L9;
        return true;
    L9:
        if (r1 == 374648427) goto L17;
        return false;
    L17:
        return true;
    }

    @Override // androidx.media3.extractor.Extractor
    public final int read(ExtractorInput r6, PositionHolder r7) {
        int r0 = 0;
        this.haveOutputSample = false;
        boolean r2 = true;
    L3:
        if (r2 == false) goto L11;
        if (this.haveOutputSample == true) goto L11;
        r2 = this.reader.read(r6);
        if (r2 == false) goto L3;
        if (maybeSeekForCues(r7, r6.getPosition()) == false) goto L3;
        return 1;
    L11:
        if (r2 == false) goto L13;
        return 0;
    L13:
        if (r0 >= this.tracks.size()) goto L15;
        Track r62 = this.tracks.valueAt(r0);
        Track.access$100(r62);
        r62.outputPendingSampleMetadata();
        r0 = r0 + 1;
        goto L13
    L15:
        return -1;
    }

    @Override // androidx.media3.extractor.Extractor
    public final void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r1, long r3) {
        this.clusterTimecodeUs = -9223372036854775807L;
        int r12 = 0;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetWriteSampleData();
    L4:
        if (r12 >= this.tracks.size()) goto L6;
        this.tracks.valueAt(r12).reset();
        r12 = r12 + 1;
        goto L4
    }

    @Override // androidx.media3.extractor.Extractor
    public final boolean sniff(ExtractorInput r1) {
        return new Sniffer().sniff(r1);
    }

    public void startMasterElement(int r6, long r7, long r9) {
        assertInitialized();
        if (r6 != 160) goto L5;
        this.blockHasReferenceBlock = false;
        this.blockGroupDiscardPaddingNs = 0;
        return;
    L5:
        if (r6 != 174) goto L7;
        Track r62 = new Track();
        this.currentTrack = r62;
        r62.isWebm = this.isWebm;
        return;
    L7:
        if (r6 != 187) goto L9;
        this.seenClusterPositionForCurrentCuePoint = false;
        return;
    L9:
        if (r6 != 19899) goto L11;
        this.seekEntryId = -1;
        this.seekEntryPosition = -1;
        return;
    L11:
        if (r6 != 20533) goto L13;
        getCurrentTrack(r6).hasContentEncryption = true;
        return;
    L13:
        if (r6 != 21968) goto L15;
        getCurrentTrack(r6).hasColorInfo = true;
        return;
    L15:
        if (r6 != 408125543) goto L17;
        long r3 = this.segmentContentPosition;
        if (r3 != (-1)) goto L36;
    L40:
        this.segmentContentPosition = r7;
        this.segmentContentSize = r9;
        return;
    L36:
        if (r3 == r7) goto L40;
        throw ParserException.createForMalformedContainer("Multiple Segment elements not supported", null);
    L17:
        if (r6 != 475249515) goto L19;
        this.cueTimesUs = new LongArray();
        this.cueClusterPositions = new LongArray();
        return;
    L19:
        if (r6 == 524531317) goto L22;
        return;
    L22:
        if (this.sentSeekMap == false) goto L24;
        return;
    L24:
        if (this.seekForCuesEnabled == true) goto L26;
    L29:
        this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
        this.sentSeekMap = true;
        return;
    L26:
        if (this.cuesContentPosition == (-1)) goto L29;
        this.seekForCues = true;
    }

    public void stringElement(int r2, String r3) {
        if (r2 != 134) goto L5;
        getCurrentTrack(r2).codecId = r3;
        return;
    L5:
        if (r2 == 17026) goto L16;
        if (r2 != 21358) goto L9;
        getCurrentTrack(r2).name = r3;
        return;
    L9:
        if (r2 == 2274716) goto L11;
        return;
    L11:
        Track.access$302(getCurrentTrack(r2), r3);
        return;
    L16:
        if ("webm".equals(r3) == false) goto L18;
    L22:
        this.isWebm = Objects.equals(r3, "webm");
        return;
    L18:
        if ("matroska".equals(r3) == true) goto L22;
        throw ParserException.createForMalformedContainer("DocType " + r3 + " not supported", null);
    }
}
