package androidx.media3.exoplayer.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.util.Xml;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Label;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.exoplayer.dash.manifest.SegmentBase;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.Ascii;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.webrtc.MediaStreamTrack;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes4.dex */
public class DashManifestParser extends DefaultHandler implements ParsingLoadable.Parser<DashManifest> {
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = null;
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = null;
    private static final int[] DOLBY_AC4_CHANNEL_CONFIGURATION_MAPPING = null;
    private static final Pattern FRAME_RATE_PATTERN = null;
    private static final int[] MPEG_CHANNEL_CONFIGURATION_MAPPING = null;
    private final XmlPullParserFactory xmlParserFactory;

    public static final class RepresentationInfo {
        public final ImmutableList<BaseUrl> baseUrls;
        public final ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        public final String drmSchemeType;
        public final List<Descriptor> essentialProperties;
        public final Format format;
        public final ArrayList<Descriptor> inbandEventStreams;
        public final long revisionId;
        public final SegmentBase segmentBase;
        public final List<Descriptor> supplementalProperties;

        public RepresentationInfo(Format r1, List<BaseUrl> r2, SegmentBase r3, String r4, ArrayList<DrmInitData.SchemeData> r5, ArrayList<Descriptor> r6, List<Descriptor> r7, List<Descriptor> r8, long r9) {
            this.format = r1;
            this.baseUrls = ImmutableList.copyOf(r2);
            this.segmentBase = r3;
            this.drmSchemeType = r4;
            this.drmSchemeDatas = r5;
            this.inbandEventStreams = r6;
            this.essentialProperties = r7;
            this.supplementalProperties = r8;
            this.revisionId = r9;
        }
    }

    static {
        FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
        CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
        CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
        DOLBY_AC4_CHANNEL_CONFIGURATION_MAPPING = new int[]{2, 1, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2};
        MPEG_CHANNEL_CONFIGURATION_MAPPING = new int[]{-1, 1, 2, 3, 4, 5, 6, 8, 2, 3, 4, 7, 8, 24, 8, 12, 10, 12, 14, 12, 14};
    }

    public DashManifestParser() {
        this.xmlParserFactory = XmlPullParserFactory.newInstance();     // Catch: XmlPullParserException -> L5
        return;
    L5:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Couldn't create XmlPullParserFactory instance", e);
        throw null;
    }

    private long addSegmentTimelineElementsToList(List<SegmentBase.SegmentTimelineElement> r1, long r2, long r4, int r6, long r7) {
        if (r6 < 0) goto L4;
        int r62 = r6 + 1;
    L5:
        int r72 = 0;
    L6:
        if (r72 >= r62) goto L8;
        r1.add(buildSegmentTimelineElement(r2, r4));
        r2 = r2 + r4;
        r72 = r72 + 1;
        goto L6
    L8:
        return r2;
    L4:
        r62 = (int) Util.ceilDivide(r7 - r2, r4);
        goto L5
    }

    private static int checkContentTypeConsistency(int r1, int r2) {
        if (r1 != (-1)) goto L5;
        return r2;
    L5:
        if (r2 != (-1)) goto L7;
        return r1;
    L7:
        if (r1 != r2) goto L9;
        boolean r22 = true;
    L10:
        Assertions.checkState(r22);
        return r1;
    L9:
        r22 = false;
        goto L10
    }

    private static String checkLanguageConsistency(String r0, String r1) {
        if (r0 != null) goto L4;
        return r1;
    L4:
        if (r1 != null) goto L6;
        return r0;
    L6:
        Assertions.checkState(r0.equals(r1));
        return r0;
    }

    private static void fillInClearKeyInformation(ArrayList<DrmInitData.SchemeData> r6) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L11;
        DrmInitData.SchemeData r2 = r6.get(r1);
        if (C.CLEARKEY_UUID.equals(r2.uuid) == false) goto L10;
        String r22 = r2.licenseServerUrl;
        if (r22 == null) goto L10;
        r6.remove(r1);
    L12:
        if (r22 != null) goto L15;
        return;
    L15:
        if (r0 >= r6.size()) goto L31;
        DrmInitData.SchemeData r12 = r6.get(r0);
        if (C.COMMON_PSSH_UUID.equals(r12.uuid) == false) goto L21;
        if (r12.licenseServerUrl != null) goto L21;
        r6.set(r0, new DrmInitData.SchemeData(C.CLEARKEY_UUID, r22, r12.mimeType, r12.data));
    L21:
        r0 = r0 + 1;
        goto L15
    L31:
        return;
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        r22 = null;
        goto L12
    }

    private static void filterRedundantIncompleteSchemeDatas(ArrayList<DrmInitData.SchemeData> r4) {
        int r0 = r4.size() - 1;
    L3:
        if (r0 < 0) goto L14;
        DrmInitData.SchemeData r1 = r4.get(r0);
        if (r1.hasData() == true) goto L13;
        int r2 = 0;
    L8:
        if (r2 >= r4.size()) goto L13;
        if (r4.get(r2).canReplace(r1) == true) goto L11;
        r2 = r2 + 1;
        goto L8
    L11:
        r4.remove(r0);
    L13:
        r0 = r0 - 1;
        goto L3
    }

    private static long getFinalAvailabilityTimeOffset(long r3, long r5) {
        if (r5 == (-9223372036854775807L)) goto L7;
        r3 = r5;
    L7:
        if (r3 != LongCompanionObject.MAX_VALUE) goto L9;
        return -9223372036854775807L;
    L9:
        return r3;
    }

    private static String getSampleMimeType(String r1, String r2) {
        if (MimeTypes.isAudio(r1) == false) goto L7;
        return MimeTypes.getAudioMediaMimeType(r2);
    L7:
        if (MimeTypes.isVideo(r1) == false) goto L11;
        return MimeTypes.getVideoMediaMimeType(r2);
    L11:
        if (MimeTypes.isText(r1) == false) goto L14;
    L15:
        return r1;
    L14:
        if (MimeTypes.isImage(r1) == true) goto L15;
        if ("application/mp4".equals(r1) == false) goto L22;
        String r12 = MimeTypes.getMediaMimeType(r2);
        if ("text/vtt".equals(r12) == false) goto L24;
        return "application/x-mp4-vtt";
    L24:
        return r12;
    L22:
        return null;
    }

    private boolean isDvbProfileDeclared(String[] r5) {
        int r4 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L9;
        if (r5[r1].startsWith("urn:dvb:dash:profile:dvb-dash:") == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public static void maybeSkipTag(XmlPullParser r2) {
        if (XmlPullParserUtil.isStartTag(r2) == false) goto L13;
        int r0 = 1;
    L6:
        if (r0 == 0) goto L22;
        r2.next();
        if (XmlPullParserUtil.isStartTag(r2) == true) goto L9;
        if (XmlPullParserUtil.isEndTag(r2) == false) goto L6;
        r0 = r0 - 1;
        goto L6
    L9:
        r0 = r0 + 1;
        goto L6
    L22:
        return;
    }

    public static int parseCea608AccessibilityChannel(List<Descriptor> r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L15;
        Descriptor r1 = r4.get(r0);
        if ("urn:scte:dash:cc:cea-608:2015".equals(r1.schemeIdUri) == false) goto L14;
        String r2 = r1.value;
        if (r2 == null) goto L14;
        Matcher r22 = CEA_608_ACCESSIBILITY_PATTERN.matcher(r2);
        if (r22.matches() == true) goto L12;
        Log.w("MpdParser", "Unable to parse CEA-608 channel number from: " + r1.value);
        goto L14
    L12:
        return Integer.parseInt(r22.group(1));
    L14:
        r0 = r0 + 1;
        goto L4
    L15:
        return -1;
    }

    public static int parseCea708AccessibilityChannel(List<Descriptor> r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L15;
        Descriptor r1 = r4.get(r0);
        if ("urn:scte:dash:cc:cea-708:2015".equals(r1.schemeIdUri) == false) goto L14;
        String r2 = r1.value;
        if (r2 == null) goto L14;
        Matcher r22 = CEA_708_ACCESSIBILITY_PATTERN.matcher(r2);
        if (r22.matches() == true) goto L12;
        Log.w("MpdParser", "Unable to parse CEA-708 service block number from: " + r1.value);
        goto L14
    L12:
        return Integer.parseInt(r22.group(1));
    L14:
        r0 = r0 + 1;
        goto L4
    L15:
        return -1;
    }

    public static long parseDateTime(XmlPullParser r1, String r2, long r3) {
        String r12 = r1.getAttributeValue(null, r2);
        if (r12 != null) goto L6;
        return r3;
    L6:
        return Util.parseXsDateTime(r12);
    }

    public static Descriptor parseDescriptor(XmlPullParser r4, String r5) {
        String r0 = parseString(r4, "schemeIdUri", _UrlKt.FRAGMENT_ENCODE_SET);
        String r1 = parseString(r4, "value", null);
        String r2 = parseString(r4, "id", null);
    L3:
        r4.next();
        if (XmlPullParserUtil.isEndTag(r4, r5) == false) goto L3;
        return new Descriptor(r0, r1, r2);
    }

    public static int parseDolbyAC4ChannelConfiguration(XmlPullParser r2, String r3) {
        String r22 = r2.getAttributeValue(null, "value");
        if (r22 != null) goto L5;
        return -1;
    L5:
        if (r22.length() != 6) goto L15;
        int r23 = Integer.parseInt(r22, 16);
        if ((8388608 & r23) == 0) goto L12;
        return parseDolbyAc4ObjectBasedChannelConfiguration(r3);
    L12:
        return parseDolbyAc4ChannelBasedChannelConfiguration(r23);
    L15:
        return -1;
    }

    private static int parseDolbyAc4ChannelBasedChannelConfiguration(int r4) {
        int r0 = 0;
        int r1 = 0;
    L3:
        int[] r2 = DOLBY_AC4_CHANNEL_CONFIGURATION_MAPPING;
        if (r0 >= r2.length) goto L6;
        r1 = r1 + (((r4 >> r0) & 1) * r2[r0]);
        r0 = r0 + 1;
        goto L3
    L6:
        if (r1 != 0) goto L9;
        return -1;
    L9:
        return r1;
    }

    private static int parseDolbyAc4ObjectBasedChannelConfiguration(String r4) {
        String[] r42 = Util.splitCodecs(r4);
        if (r42.length != 0) goto L5;
        return -1;
    L5:
        List<String> r43 = Splitter.on('.').splitToList(Ascii.toLowerCase(r42[0].trim()));
        if (r43.size() == 4) goto L8;
    L19:
        return -1;
    L8:
        if (r43.get(0).equals("ac-4") == false) goto L19;
        String r44 = r43.get(3);
        r44.getClass();
        if (r44.equals("03") == false) goto L13;
        return 18;
    L13:
        if (r44.equals("04") == true) goto L15;
        return -1;
    L15:
        return 21;
    }

    public static int parseDolbyChannelConfiguration(XmlPullParser r4) {
        String r42 = r4.getAttributeValue(null, "value");
        if (r42 != null) goto L5;
        return -1;
    L5:
        String r43 = Ascii.toLowerCase(r42);
        r43.getClass();
        switch(r43.hashCode()) {
            case 1596796: goto L25;
            case 2937391: goto L21;
            case 3094034: goto L17;
            case 3094035: goto L13;
            case 3133436: goto L9;
            default: goto L7;
        };
    L7:
        char r44 = 65535;
    L28:
        switch(r44) {
            case 0: goto L37;
            case 1: goto L36;
            case 2: goto L34;
            case 3: goto L32;
            case 4: goto L30;
            default: goto L29;
        };
    L29:
        return -1;
    L30:
        return 8;
    L32:
        return 6;
    L34:
        return 5;
    L36:
        return 2;
    L37:
        return 1;
    L9:
        if (r43.equals("fa01") == false) goto L7;
        r44 = 4;
        goto L28
    L13:
        if (r43.equals("f801") == false) goto L7;
        r44 = 3;
        goto L28
    L17:
        if (r43.equals("f800") == false) goto L7;
        r44 = 2;
        goto L28
    L21:
        if (r43.equals("a000") == false) goto L7;
        r44 = 1;
        goto L28
    L25:
        if (r43.equals("4000") == false) goto L7;
        r44 = 0;
        goto L28
    }

    public static int parseDtsChannelConfiguration(XmlPullParser r2) {
        int r22 = parseInt(r2, "value", -1);
        if (r22 > 0) goto L5;
    L7:
        return -1;
    L5:
        if (r22 >= 33) goto L7;
        return r22;
    }

    public static int parseDtsxChannelConfiguration(XmlPullParser r2) {
        String r22 = r2.getAttributeValue(null, "value");
        if (r22 != null) goto L5;
        return -1;
    L5:
        int r23 = Integer.bitCount(Integer.parseInt(r22, 16));
        if (r23 != 0) goto L8;
        return -1;
    L8:
        return r23;
    }

    public static long parseDuration(XmlPullParser r1, String r2, long r3) {
        String r12 = r1.getAttributeValue(null, r2);
        if (r12 != null) goto L6;
        return r3;
    L6:
        return Util.parseXsDuration(r12);
    }

    public static String parseEac3SupplementalProperties(List<Descriptor> r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L16;
        Descriptor r1 = r5.get(r0);
        String r2 = r1.schemeIdUri;
        if ("tag:dolby.com,2018:dash:EC3_ExtensionType:2018".equals(r2) == false) goto L10;
        if ("JOC".equals(r1.value) == false) goto L10;
        return "audio/eac3-joc";
    L10:
        if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(r2) == false) goto L15;
        if ("ec+3".equals(r1.value) == false) goto L15;
        return "audio/eac3-joc";
    L15:
        r0 = r0 + 1;
        goto L4
    L16:
        return "audio/eac3";
    }

    public static float parseFloat(XmlPullParser r1, String r2, float r3) {
        String r12 = r1.getAttributeValue(null, r2);
        if (r12 != null) goto L6;
        return r3;
    L6:
        return Float.parseFloat(r12);
    }

    public static float parseFrameRate(XmlPullParser r2, float r3) {
        String r22 = r2.getAttributeValue(null, "frameRate");
        if (r22 == null) goto L12;
        Matcher r23 = FRAME_RATE_PATTERN.matcher(r22);
        if (r23.matches() == false) goto L12;
        int r32 = Integer.parseInt(r23.group(1));
        if (TextUtils.isEmpty(r23.group(2)) == true) goto L11;
        return r32 / Integer.parseInt(r2);
    L11:
        return r32;
    L12:
        return r3;
    }

    public static int parseInt(XmlPullParser r1, String r2, int r3) {
        String r12 = r1.getAttributeValue(null, r2);
        if (r12 != null) goto L6;
        return r3;
    L6:
        return Integer.parseInt(r12);
    }

    public static long parseLastSegmentNumberSupplementalProperty(List<Descriptor> r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L10;
        Descriptor r1 = r4.get(r0);
        if (Ascii.equalsIgnoreCase("http://dashif.org/guidelines/last-segment-number", r1.schemeIdUri) == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return Long.parseLong(r1.value);
    L10:
        return -1;
    }

    public static long parseLong(XmlPullParser r1, String r2, long r3) {
        String r12 = r1.getAttributeValue(null, r2);
        if (r12 != null) goto L6;
        return r3;
    L6:
        return Long.parseLong(r12);
    }

    public static int parseMpegChannelConfiguration(XmlPullParser r3) {
        int r32 = parseInt(r3, "value", -1);
        if (r32 < 0) goto L8;
        int[] r0 = MPEG_CHANNEL_CONFIGURATION_MAPPING;
        if (r32 >= r0.length) goto L8;
        return r0[r32];
    L8:
        return -1;
    }

    public static String parseString(XmlPullParser r1, String r2, String r3) {
        String r12 = r1.getAttributeValue(null, r2);
        if (r12 != null) goto L5;
        return r3;
    L5:
        return r12;
    }

    public static String parseText(XmlPullParser r3, String r4) {
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L3:
        r3.next();
        if (r3.getEventType() != 4) goto L6;
        r0 = r3.getText();
    L8:
        if (XmlPullParserUtil.isEndTag(r3, r4) == false) goto L3;
        return r0;
    L6:
        maybeSkipTag(r3);
        goto L8
    }

    public AdaptationSet buildAdaptationSet(long r1, int r3, List<Representation> r4, List<Descriptor> r5, List<Descriptor> r6, List<Descriptor> r7) {
        return new AdaptationSet(r1, r3, r4, r5, r6, r7);
    }

    public EventMessage buildEvent(String r3, String r4, long r5, long r7, byte[] r9) {
        return new EventMessage(r3, r4, r7, r5, r9);
    }

    public EventStream buildEventStream(String r1, String r2, long r3, long[] r5, EventMessage[] r6) {
        return new EventStream(r1, r2, r3, r5, r6);
    }

    public Format buildFormat(String r8, String r9, int r10, int r11, float r12, int r13, int r14, int r15, String r16, List<Descriptor> r17, List<Descriptor> r18, String r19, String r20, String r21, List<Descriptor> r22, List<Descriptor> r23) {
        String r1 = r20;
        String r3 = r19;
        String r4 = getSampleMimeType(r9, r3);
        if ("audio/eac3".equals(r4) == false) goto L8;
        r4 = parseEac3SupplementalProperties(r23);
        if ("audio/eac3-joc".equals(r4) == false) goto L8;
        r3 = "ec+3";
    L8:
        if (MimeTypes.isDolbyVisionCodec(r3, r1) == false) goto L13;
        if (r1 != null) goto L12;
        r1 = r3;
    L12:
        r4 = "video/dolby-vision";
        r3 = r1;
    L13:
        int r110 = parseSelectionFlagsFromRoleDescriptors(r17);
        int r0 = ((parseRoleFlagsFromRoleDescriptors(r17) | parseRoleFlagsFromAccessibilityDescriptors(r18)) | parseRoleFlagsFromProperties(r22)) | parseRoleFlagsFromProperties(r23);
        Pair<Integer, Integer> r7 = parseTileCountFromProperties(r22);
        Format.Builder r82 = new Format.Builder().setId(r8).setContainerMimeType(r9).setSampleMimeType(r4).setCodecs(r3).setPeakBitrate(r15).setSelectionFlags(r110).setRoleFlags(r0).setLanguage(r16);
        int r92 = -1;
        if (r7 == null) goto L16;
        int r02 = ((Integer) r7.first).intValue();
    L17:
        Format.Builder r83 = r82.setTileCountHorizontal(r02);
        if (r7 == null) goto L20;
        int r72 = ((Integer) r7.second).intValue();
    L21:
        Format.Builder r73 = r83.setTileCountVertical(r72);
        if (MimeTypes.isVideo(r4) == false) goto L25;
        r73.setWidth(r10).setHeight(r11).setFrameRate(r12);
    L40:
        return r73.build();
    L25:
        if (MimeTypes.isAudio(r4) == false) goto L28;
        r73.setChannelCount(r13).setSampleRate(r14);
        goto L40
    L28:
        if (MimeTypes.isText(r4) == false) goto L37;
        if ("application/cea-608".equals(r4) == false) goto L33;
        r92 = parseCea608AccessibilityChannel(r18);
    L35:
        r73.setAccessibilityChannel(r92);
        goto L40
    L33:
        if ("application/cea-708".equals(r4) == false) goto L35;
        r92 = parseCea708AccessibilityChannel(r18);
        goto L35
    L37:
        if (MimeTypes.isImage(r4) == false) goto L40;
        r73.setWidth(r10).setHeight(r11);
        goto L40
    L20:
        r72 = -1;
        goto L21
    L16:
        r02 = -1;
        goto L17
    }

    public DashManifest buildMediaPresentationDescription(long r22, long r24, long r26, boolean r28, long r29, long r31, long r33, long r35, ProgramInformation r37, UtcTimingElement r38, ServiceDescriptionElement r39, Uri r40, List<Period> r41) {
        return new DashManifest(r22, r24, r26, r28, r29, r31, r33, r35, r37, r38, r39, r40, r41);
    }

    public Period buildPeriod(String r1, long r2, List<AdaptationSet> r4, List<EventStream> r5, Descriptor r6) {
        return new Period(r1, r2, r4, r5, r6);
    }

    public RangedUri buildRangedUri(String r1, long r2, long r4) {
        return new RangedUri(r1, r2, r4);
    }

    public Representation buildRepresentation(RepresentationInfo r10, String r11, List<Label> r12, String r13, ArrayList<DrmInitData.SchemeData> r14, ArrayList<Descriptor> r15) {
        Format.Builder r9 = r10.format.buildUpon();
        if (r11 != null) goto L5;
    L7:
        r9.setLabels(r12);
    L8:
        String r112 = r10.drmSchemeType;
        if (r112 == null) goto L12;
        r13 = r112;
    L12:
        ArrayList<DrmInitData.SchemeData> r113 = r10.drmSchemeDatas;
        r113.addAll(r14);
        if (r113.isEmpty() == true) goto L15;
        fillInClearKeyInformation(r113);
        filterRedundantIncompleteSchemeDatas(r113);
        r9.setDrmInitData(new DrmInitData(r13, r113));
    L15:
        ArrayList<Descriptor> r5 = r10.inbandEventStreams;
        r5.addAll(r15);
        return Representation.newInstance(r10.revisionId, r9.build(), r10.baseUrls, r10.segmentBase, r5, r10.essentialProperties, r10.supplementalProperties, null);
    L5:
        if (r12.isEmpty() == false) goto L7;
        r9.setLabel(r11);
        goto L8
    }

    public SegmentBase.SegmentList buildSegmentList(RangedUri r19, long r20, long r22, long r24, long r26, List<SegmentBase.SegmentTimelineElement> r28, long r29, List<RangedUri> r31, long r32, long r34) {
        return new SegmentBase.SegmentList(r19, r20, r22, r24, r26, r28, r29, r31, Util.msToUs(r32), Util.msToUs(r34));
    }

    public SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri r22, long r23, long r25, long r27, long r29, long r31, List<SegmentBase.SegmentTimelineElement> r33, long r34, UrlTemplate r36, UrlTemplate r37, long r38, long r40) {
        return new SegmentBase.SegmentTemplate(r22, r23, r25, r27, r29, r31, r33, r34, r36, r37, Util.msToUs(r38), Util.msToUs(r40));
    }

    public SegmentBase.SegmentTimelineElement buildSegmentTimelineElement(long r1, long r3) {
        return new SegmentBase.SegmentTimelineElement(r1, r3);
    }

    public SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri r1, long r2, long r4, long r6, long r8) {
        return new SegmentBase.SingleSegmentBase(r1, r2, r4, r6, r8);
    }

    public UtcTimingElement buildUtcTimingElement(String r1, String r2) {
        return new UtcTimingElement(r1, r2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public DashManifest parse(Uri r4, InputStream r5) {
        XmlPullParser r1 = this.xmlParserFactory.newPullParser();     // Catch: XmlPullParserException -> L9
        r1.setInput(r5, null);     // Catch: XmlPullParserException -> L9
        if (r1.next() != 2) goto L12;
        if ("MPD".equals(r1.getName()) == false) goto L12;
        return parseMediaPresentationDescription(r1, r4);
    L12:
        throw ParserException.createForMalformedManifest("inputStream does not contain a valid media presentation description", null);     // Catch: XmlPullParserException -> L9
    L9:
        e = move-exception;
        throw ParserException.createForMalformedManifest(null, e);
    }

    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public /* bridge */ /* synthetic */ DashManifest parse(Uri r1, InputStream r2) {
        return parse(r1, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.util.ArrayList] */
    public AdaptationSet parseAdaptationSet(XmlPullParser r46, List<BaseUrl> r47, SegmentBase r48, long r49, long r51, long r53, long r55, long r57, boolean r59) {
        DashManifestParser r0 = this;
        XmlPullParser r1 = r46;
        long r30 = parseLong(r1, "id", -1);
        int r2 = parseContentType(r46);
        String r3 = r1.getAttributeValue(null, "mimeType");
        String r5 = r1.getAttributeValue(null, "codecs");
        String r6 = r1.getAttributeValue(null, "scte214:supplementalCodecs");
        String r7 = r1.getAttributeValue(null, "scte214:supplementalProfiles");
        int r8 = parseInt(r1, "width", -1);
        int r10 = parseInt(r1, "height", -1);
        float r11 = parseFrameRate(r1, -1.0f);
        int r12 = parseInt(r1, "audioSamplingRate", -1);
        String r13 = "lang";
        String r14 = r1.getAttributeValue(null, "lang");
        String r32 = r1.getAttributeValue(null, "label");
        ArrayList r15 = new ArrayList();
        ArrayList<DrmInitData.SchemeData> r4 = new ArrayList();
        ArrayList<Descriptor> r22 = new ArrayList();
        ArrayList r18 = r15;
        ArrayList r152 = new ArrayList();
        ArrayList r142 = new ArrayList();
        ArrayList r9 = new ArrayList();
        ArrayList<Descriptor> r21 = r22;
        ArrayList r23 = new ArrayList();
        String r222 = r3;
        ArrayList r232 = new ArrayList();
        long r25 = r53;
        String r20 = r7;
        int r24 = r8;
        int r27 = r10;
        float r28 = r11;
        int r82 = r2;
        String r102 = r14;
        boolean r34 = false;
        int r112 = -1;
        String r35 = null;
        SegmentBase r17 = r48;
        String r19 = r6;
        long r62 = r51;
        ?? r33 = new ArrayList();
    L3:
        r1.next();
        int r482 = r112;
        if (XmlPullParserUtil.isStartTag(r1, "BaseURL") == false) goto L9;
        if (r34 == true) goto L7;
        r62 = r0.parseAvailabilityTimeOffsetUs(r1, r62);
        r34 = true;
    L7:
        r33.addAll(r0.parseBaseUrl(r1, r47, r59));
        ArrayList r54 = r18;
        ArrayList r182 = r9;
        ArrayList r92 = r54;
        r112 = r482;
        r62 = r62;
        List<BaseUrl> r37 = r33;
        ArrayList<DrmInitData.SchemeData> r36 = r4;
        String r42 = r13;
        ArrayList r16 = r152;
        SegmentBase r43 = r17;
        ArrayList r542 = r232;
        ArrayList r38 = r23;
        int r153 = r12;
        ArrayList r172 = r142;
        ArrayList<Descriptor> r26 = r21;
    L8:
        String r143 = r5;
    L68:
        if (XmlPullParserUtil.isEndTag(r1, "AdaptationSet") == true) goto L69;
        ArrayList r02 = r182;
        r18 = r92;
        r9 = r02;
        r0 = this;
        r232 = r542;
        r21 = r26;
        r23 = r38;
        r5 = r143;
        r12 = r153;
        r152 = r16;
        r142 = r172;
        r33 = r37;
        r13 = r42;
        r17 = r43;
        r4 = r36;
        goto L3
    L69:
        ArrayList r03 = new ArrayList(r542.size());
        int r110 = 0;
    L71:
        if (r110 >= r542.size()) goto L74;
        ArrayList<Descriptor> r52 = r26;
        ArrayList r492 = r92;
        String r483 = r32;
        r03.add(buildRepresentation((RepresentationInfo) r542.get(r110), r483, r492, r35, r36, r52));
        r110 = r110 + 1;
        r32 = r483;
        r26 = r52;
        r92 = r492;
        goto L71
    L74:
        return buildAdaptationSet(r30, r82, r03, r16, r182, r38);
    L9:
        long r362 = r62;
        if (XmlPullParserUtil.isStartTag(r1, "ContentProtection") == false) goto L19;
        Pair<String, DrmInitData.SchemeData> r72 = parseContentProtection(r46);
        ArrayList r29 = r33;
        Object r39 = r72.first;
        if (r39 == null) goto L14;
        r35 = (String) r39;
    L14:
        Object r310 = r72.second;
        if (r310 == null) goto L17;
        r4.add((DrmInitData.SchemeData) r310);
    L17:
        ArrayList r543 = r18;
        r182 = r9;
        r92 = r543;
        r112 = r482;
        r38 = r23;
        r42 = r13;
        r16 = r152;
        r26 = r21;
        r542 = r232;
        r62 = r362;
        r36 = r4;
        r153 = r12;
        r43 = r17;
    L18:
        r37 = r29;
        r172 = r142;
        goto L8
    L19:
        r29 = r33;
        if (XmlPullParserUtil.isStartTag(r1, "ContentComponent") == false) goto L24;
        String r73 = checkLanguageConsistency(r102, r1.getAttributeValue(null, r13));
        r82 = checkContentTypeConsistency(r82, parseContentType(r46));
        ArrayList r544 = r18;
        r182 = r9;
        r92 = r544;
        r112 = r482;
        r102 = r73;
    L22:
        r42 = r13;
        r16 = r152;
        r542 = r232;
        r62 = r362;
        r38 = r23;
        r36 = r4;
        r153 = r12;
        r43 = r17;
        r26 = r21;
        goto L18
    L24:
        if (XmlPullParserUtil.isStartTag(r1, "Role") == false) goto L28;
        r142.add(parseDescriptor(r1, "Role"));
    L26:
        r36 = r4;
        r43 = r17;
        r172 = r142;
        r143 = r5;
        long r56 = r25;
        int r252 = r82;
        long r74 = r362;
        ArrayList r545 = r18;
        r182 = r9;
        r92 = r545;
        r42 = r13;
        r16 = r152;
        r542 = r232;
        r37 = r29;
        int r233 = r482;
        r38 = r23;
        r153 = r12;
        r26 = r21;
        String r212 = r102;
    L66:
        r82 = r252;
        r25 = r56;
        r62 = r74;
    L56:
        r102 = r212;
        r112 = r233;
        goto L68
    L28:
        if (XmlPullParserUtil.isStartTag(r1, "AudioChannelConfiguration") == false) goto L31;
        ArrayList r546 = r18;
        r182 = r9;
        r92 = r546;
        r112 = r0.parseAudioChannelConfiguration(r1, r5);
        goto L22
    L31:
        if (XmlPullParserUtil.isStartTag(r1, "Accessibility") == false) goto L34;
        r152.add(parseDescriptor(r1, "Accessibility"));
        goto L26
    L34:
        if (XmlPullParserUtil.isStartTag(r1, "EssentialProperty") == false) goto L37;
        r9.add(parseDescriptor(r1, "EssentialProperty"));
        goto L26
    L37:
        if (XmlPullParserUtil.isStartTag(r1, "SupplementalProperty") == false) goto L40;
        r23.add(parseDescriptor(r1, "SupplementalProperty"));
        goto L26
    L40:
        if (XmlPullParserUtil.isStartTag(r1, "Representation") == true) goto L42;
        r42 = r13;
        r16 = r152;
        ArrayList r382 = r18;
        ArrayList<Descriptor> r392 = r21;
        ArrayList r132 = r23;
        r182 = r9;
        r212 = r102;
        r153 = r12;
        ArrayList r93 = r232;
        r233 = r482;
        XmlPullParser r210 = r1;
        DashManifestParser r111 = r0;
        int r04 = r82;
        r74 = r362;
        r36 = r4;
        r43 = r17;
        r37 = r29;
        r172 = r142;
        r143 = r5;
        r56 = r25;
        if (XmlPullParserUtil.isStartTag(r210, "SegmentBase") == false) goto L51;
        SegmentBase.SingleSegmentBase r311 = r111.parseSegmentBase(r210, (SegmentBase.SingleSegmentBase) r43);
        r1 = r210;
        r43 = r311;
        r25 = r56;
        r62 = r74;
        r542 = r93;
        r38 = r132;
        r102 = r212;
        r112 = r233;
    L46:
        r92 = r382;
        r26 = r392;
        r82 = r04;
        goto L68
    L51:
        if (XmlPullParserUtil.isStartTag(r210, "SegmentList") == false) goto L53;
        long r94 = r111.parseAvailabilityTimeOffsetUs(r210, r56);
        r542 = r93;
        r1 = r46;
        r43 = r111.parseSegmentList(r1, (SegmentBase.SegmentList) r43, r55, r49, r74, r94, r57);
        r62 = r74;
        r38 = r132;
        r112 = r233;
        r82 = r04;
        r26 = r392;
        r25 = r94;
        r102 = r212;
        r92 = r382;
        goto L68
    L53:
        r252 = r04;
        r1 = r210;
        r542 = r93;
        if (XmlPullParserUtil.isStartTag(r1, "SegmentTemplate") == false) goto L57;
        long r103 = r111.parseAvailabilityTimeOffsetUs(r1, r56);
        r38 = r132;
        r43 = r111.parseSegmentTemplate(r1, (SegmentBase.SegmentTemplate) r43, r38, r55, r49, r74, r103, r57);
        r62 = r74;
        r82 = r252;
        r92 = r382;
        r26 = r392;
        r25 = r103;
        goto L56
    L57:
        r38 = r132;
        if (XmlPullParserUtil.isStartTag(r1, "InbandEventStream") == false) goto L60;
        r26 = r392;
        r26.add(parseDescriptor(r1, "InbandEventStream"));
        r92 = r382;
        goto L66
    L60:
        r26 = r392;
        if (XmlPullParserUtil.isStartTag(r1, "Label") == false) goto L63;
        r92 = r382;
        r92.add(parseLabel(r46));
        goto L66
    L63:
        r92 = r382;
        if (XmlPullParserUtil.isStartTag(r1) == false) goto L66;
        parseAdaptationSetChild(r46);
        goto L66
    L42:
        if (r29.isEmpty() == true) goto L44;
        r382 = r18;
        ArrayList r40 = r232;
        List<BaseUrl> r312 = r29;
        SegmentBase r183 = r17;
        ArrayList r173 = r23;
        XmlPullParser r211 = r1;
        DashManifestParser r113 = r0;
        int r05 = r82;
        int r83 = r24;
        long r234 = r362;
        r37 = r312;
        r112 = r482;
        ArrayList r162 = r9;
        r42 = r13;
        String r75 = r20;
        r392 = r21;
        int r95 = r27;
        boolean r292 = r59;
        String r133 = r102;
        String r63 = r19;
        float r104 = r28;
        long r192 = r55;
        long r272 = r57;
        r36 = r4;
        String r44 = r222;
        long r213 = r49;
    L45:
        RepresentationInfo r313 = r113.parseRepresentation(r211, r312, r44, r5, r63, r75, r83, r95, r104, r112, r12, r133, r142, r152, r162, r173, r183, r192, r213, r234, r25, r272, r292);
        r222 = r44;
        r19 = r63;
        r20 = r75;
        r27 = r95;
        r28 = r104;
        String r214 = r133;
        ArrayList r134 = r173;
        r43 = r183;
        r172 = r142;
        r182 = r162;
        r143 = r5;
        r16 = r152;
        r153 = r12;
        r24 = r83;
        r04 = checkContentTypeConsistency(r05, MimeTypes.getTrackType(r313.format.sampleMimeType));
        ArrayList r96 = r40;
        r96.add(r313);
        r1 = r211;
        r62 = r234;
        r542 = r96;
        r38 = r134;
        r102 = r214;
        goto L46
    L44:
        r312 = r47;
        r382 = r18;
        r40 = r232;
        r183 = r17;
        r173 = r23;
        r211 = r1;
        r113 = r0;
        r05 = r82;
        r83 = r24;
        r234 = r362;
        r37 = r29;
        r162 = r9;
        r42 = r13;
        r75 = r20;
        r392 = r21;
        r95 = r27;
        r112 = r482;
        r133 = r102;
        r104 = r28;
        r272 = r57;
        r36 = r4;
        r44 = r222;
        r213 = r49;
        r292 = r59;
        r63 = r19;
        r192 = r55;
        goto L45
    }

    public void parseAdaptationSetChild(XmlPullParser r1) {
        maybeSkipTag(r1);
    }

    public int parseAudioChannelConfiguration(XmlPullParser r3, String r4) {
        String r2 = parseString(r3, "schemeIdUri", null);
        r2.getClass();
        int r1 = -1;
        switch(r2.hashCode()) {
            case -2128649360: goto L34;
            case -2060825028: goto L30;
            case -1352850286: goto L26;
            case -1138141449: goto L22;
            case -986633423: goto L18;
            case -79006963: goto L14;
            case 312179081: goto L10;
            case 2036691300: goto L6;
            default: goto L4;
        };
    L4:
        char r22 = 65535;
    L37:
        switch(r22) {
            case 0: goto L44;
            case 1: goto L43;
            case 2: goto L42;
            case 3: goto L41;
            case 4: goto L40;
            case 5: goto L44;
            case 6: goto L39;
            case 7: goto L41;
            default: goto L45;
        };
    L39:
        r1 = parseDtsxChannelConfiguration(r3);
        goto L45
    L40:
        r1 = parseMpegChannelConfiguration(r3);
        goto L45
    L41:
        r1 = parseDolbyChannelConfiguration(r3);
        goto L45
    L42:
        r1 = parseInt(r3, "value", -1);
        goto L45
    L43:
        r1 = parseDolbyAC4ChannelConfiguration(r3, r4);
        goto L45
    L44:
        r1 = parseDtsChannelConfiguration(r3);
    L45:
        r3.next();
        if (XmlPullParserUtil.isEndTag(r3, "AudioChannelConfiguration") == false) goto L45;
        return r1;
    L6:
        if (r2.equals("urn:dolby:dash:audio_channel_configuration:2011") == false) goto L4;
        r22 = 7;
        goto L37
    L10:
        if (r2.equals("tag:dts.com,2018:uhd:audio_channel_configuration") == false) goto L4;
        r22 = 6;
        goto L37
    L14:
        if (r2.equals("tag:dts.com,2014:dash:audio_channel_configuration:2012") == false) goto L4;
        r22 = 5;
        goto L37
    L18:
        if (r2.equals("urn:mpeg:mpegB:cicp:ChannelConfiguration") == false) goto L4;
        r22 = 4;
        goto L37
    L22:
        if (r2.equals("tag:dolby.com,2014:dash:audio_channel_configuration:2011") == false) goto L4;
        r22 = 3;
        goto L37
    L26:
        if (r2.equals("urn:mpeg:dash:23003:3:audio_channel_configuration:2011") == false) goto L4;
        r22 = 2;
        goto L37
    L30:
        if (r2.equals("tag:dolby.com,2015:dash:audio_channel_configuration:2015") == false) goto L4;
        r22 = 1;
        goto L37
    L34:
        if (r2.equals("urn:dts:dash:audio_channel_configuration:2012") == false) goto L4;
        r22 = 0;
        goto L37
    }

    public long parseAvailabilityTimeOffsetUs(XmlPullParser r2, long r3) {
        String r1 = r2.getAttributeValue(null, "availabilityTimeOffset");
        if (r1 != null) goto L6;
        return r3;
    L6:
        if ("INF".equals(r1) == false) goto L10;
        return LongCompanionObject.MAX_VALUE;
    L10:
        return (long) (Float.parseFloat(r1) * 1000000.0f);
    }

    public List<BaseUrl> parseBaseUrl(XmlPullParser r8, List<BaseUrl> r9, boolean r10) {
        String r7 = r8.getAttributeValue(null, "dvb:priority");
        int r1 = 1;
        if (r7 == null) goto L5;
        int r72 = Integer.parseInt(r7);
    L8:
        String r2 = r8.getAttributeValue(null, "dvb:weight");
        if (r2 == null) goto L11;
        r1 = Integer.parseInt(r2);
    L11:
        String r0 = r8.getAttributeValue(null, "serviceLocation");
        String r82 = parseText(r8, "BaseURL");
        if (UriUtil.isAbsolute(r82) == false) goto L17;
        if (r0 != null) goto L16;
        r0 = r82;
    L16:
        return Lists.newArrayList(new BaseUrl[]{new BaseUrl(r82, r0, r72, r1)});
    L17:
        ArrayList r22 = new ArrayList();
        int r3 = 0;
    L19:
        if (r3 >= r9.size()) goto L27;
        BaseUrl r4 = r9.get(r3);
        String r5 = UriUtil.resolve(r4.url, r82);
        if (r0 != null) goto L23;
        String r6 = r5;
    L24:
        if (r10 == false) goto L26;
        r72 = r4.priority;
        r1 = r4.weight;
        r6 = r4.serviceLocation;
    L26:
        r22.add(new BaseUrl(r5, r6, r72, r1));
        r3 = r3 + 1;
        goto L19
    L23:
        r6 = r0;
        goto L24
    L27:
        return r22;
    L5:
        if (r10 == false) goto L7;
        r72 = 1;
        goto L8
    L7:
        r72 = Integer.MIN_VALUE;
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.util.UUID] */
    /* JADX WARN: Type inference failed for: r3v13, types: [java.util.UUID] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15, types: [java.util.UUID] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v21, types: [java.util.UUID] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.util.UUID] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v4, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.util.UUID] */
    public Pair<String, DrmInitData.SchemeData> parseContentProtection(XmlPullParser r11) {
        DrmInitData.SchemeData r0 = null;
        String r10 = r11.getAttributeValue(null, "schemeIdUri");
        if (r10 == null) goto L41;
        String r102 = Ascii.toLowerCase(r10);
        r102.getClass();
        char r4 = 65535;
        switch(r102.hashCode()) {
            case -1980789791: goto L20;
            case 489446379: goto L16;
            case 755418770: goto L12;
            case 1812765994: goto L8;
            default: goto L23;
        };
    L23:
        switch(r4) {
            case 0: goto L40;
            case 1: goto L39;
            case 2: goto L37;
            case 3: goto L25;
            default: goto L41;
        };
    L25:
        String r103 = r11.getAttributeValue(null, "value");
        String r3 = XmlPullParserUtil.getAttributeValueIgnorePrefix(r11, "default_KID");
        if (TextUtils.isEmpty(r3) == false) goto L28;
    L34:
        Log.w("MpdParser", "Ignoring <ContentProtection> with schemeIdUri=\"urn:mpeg:dash:mp4protection:2011\" (ClearKey) due to missing required default_KID attribute.");
        ?? r32 = null;
    L35:
        String r42 = r32;
    L36:
        String r5 = r42;
        ?? r43 = r42;
    L42:
        r11.next();
        if (XmlPullParserUtil.isStartTag(r11, "clearkey:Laurl") == true) goto L47;
        if (XmlPullParserUtil.isStartTag(r11, "dashif:Laurl") == true) goto L47;
    L50:
        if (XmlPullParserUtil.isStartTag(r11, "ms:laurl") == false) goto L52;
        r5 = r11.getAttributeValue(null, "licenseUrl");
        r43 = r43;
    L71:
        if (XmlPullParserUtil.isEndTag(r11, "ContentProtection") == false) goto L42;
        if (r32 == null) goto L75;
        r0 = new DrmInitData.SchemeData(r32, r5, "video/mp4", r43);
    L75:
        return Pair.create(r103, r0);
    L52:
        if (r43 == 0) goto L54;
    L61:
        if (r43 != 0) goto L69;
        ?? r6 = C.PLAYREADY_UUID;
        if (r6.equals(r32) == false) goto L69;
        if (XmlPullParserUtil.isStartTag(r11, "mspr:pro") == false) goto L69;
        if (r11.next() != 4) goto L69;
        r43 = PsshAtomUtil.buildPsshAtom(r6, Base64.decode(r11.getText(), 0));
    L69:
        maybeSkipTag(r11);
        r43 = r43;
        goto L71
    L54:
        if (XmlPullParserUtil.isStartTagIgnorePrefix(r11, "pssh") == false) goto L61;
        if (r11.next() != 4) goto L61;
        byte[] r33 = Base64.decode(r11.getText(), 0);
        UUID r44 = PsshAtomUtil.parseUuid(r33);
        if (r44 != null) goto L60;
        Log.w("MpdParser", "Skipping malformed cenc:pssh data");
        r32 = r44;
        r43 = 0;
        goto L71
    L60:
        r43 = r33;
        r32 = r44;
    L47:
        if (r11.next() != 4) goto L50;
        r5 = r11.getText();
        r43 = r43;
        goto L71
    L28:
        if ("00000000-0000-0000-0000-000000000000".equals(r3) == true) goto L34;
        String[] r34 = r3.split("\\s+");
        UUID[] r45 = new UUID[r34.length];
        int r52 = 0;
    L31:
        if (r52 >= r34.length) goto L33;
        r45[r52] = UUID.fromString(r34[r52]);
        r52 = r52 + 1;
        goto L31
    L33:
        r32 = C.COMMON_PSSH_UUID;
        r5 = null;
        r43 = PsshAtomUtil.buildPsshAtom(r32, r45, null);
        goto L42
    L37:
        r32 = C.WIDEVINE_UUID;
    L38:
        r103 = null;
        r42 = null;
        goto L36
    L39:
        r32 = C.PLAYREADY_UUID;
        goto L38
    L40:
        r32 = C.CLEARKEY_UUID;
        goto L38
    L8:
        if (r102.equals("urn:mpeg:dash:mp4protection:2011") == false) goto L23;
        r4 = 3;
        goto L23
    L12:
        if (r102.equals("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed") == false) goto L23;
        r4 = 2;
        goto L23
    L16:
        if (r102.equals("urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95") == false) goto L23;
        r4 = 1;
        goto L23
    L20:
        if (r102.equals("urn:uuid:e2719d58-a985-b3c9-781a-b030af78d30e") == false) goto L23;
        r4 = 0;
    L41:
        r103 = null;
        r32 = null;
        goto L35
    }

    public int parseContentType(XmlPullParser r2) {
        String r1 = r2.getAttributeValue(null, "contentType");
        if (TextUtils.isEmpty(r1) == false) goto L6;
        return -1;
    L6:
        if (MediaStreamTrack.AUDIO_TRACK_KIND.equals(r1) == false) goto L10;
        return 1;
    L10:
        if (MediaStreamTrack.VIDEO_TRACK_KIND.equals(r1) == false) goto L14;
        return 2;
    L14:
        if ("text".equals(r1) == false) goto L18;
        return 3;
    L18:
        if ("image".equals(r1) == false) goto L21;
        return 4;
    L21:
        return -1;
    }

    public Pair<Long, EventMessage> parseEvent(XmlPullParser r18, String r19, String r20, long r21, long r23, ByteArrayOutputStream r25) {
        long r7 = parseLong(r18, "id", 0);
        long r9 = parseLong(r18, "duration", -9223372036854775807L);
        long r1 = parseLong(r18, "presentationTime", 0);
        long r92 = Util.scaleLargeTimestamp(r9, 1000, r21);
        long r12 = Util.scaleLargeTimestamp(r1 - r23, 1000000, r21);
        String r3 = parseString(r18, "messageData", null);
        byte[] r0 = parseEventObject(r18, r25);
        Long r13 = Long.valueOf(r12);
        if (r3 == null) goto L7;
        r0 = Util.getUtf8Bytes(r3);
    L7:
        return Pair.create(r13, buildEvent(r19, r20, r7, r92, r0));
    }

    public byte[] parseEventObject(XmlPullParser r5, ByteArrayOutputStream r6) {
        r6.reset();
        XmlSerializer r4 = Xml.newSerializer();
        r4.setOutput(r6, StandardCharsets.UTF_8.name());
        r5.nextToken();
    L4:
        if (XmlPullParserUtil.isEndTag(r5, "Event") == true) goto L23;
        switch(r5.getEventType()) {
            case 0: goto L21;
            case 1: goto L20;
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L12;
            case 7: goto L11;
            case 8: goto L10;
            case 9: goto L9;
            case 10: goto L8;
            default: goto L22;
        };
    L8:
        r4.docdecl(r5.getText());
        goto L22
    L9:
        r4.comment(r5.getText());
        goto L22
    L10:
        r4.processingInstruction(r5.getText());
        goto L22
    L11:
        r4.ignorableWhitespace(r5.getText());
        goto L22
    L12:
        r4.entityRef(r5.getText());
        goto L22
    L13:
        r4.cdsect(r5.getText());
        goto L22
    L14:
        r4.text(r5.getText());
        goto L22
    L15:
        r4.endTag(r5.getNamespace(), r5.getName());
        goto L22
    L16:
        r4.startTag(r5.getNamespace(), r5.getName());
        int r0 = 0;
    L18:
        if (r0 >= r5.getAttributeCount()) goto L22;
        r4.attribute(r5.getAttributeNamespace(r0), r5.getAttributeName(r0), r5.getAttributeValue(r0));
        r0 = r0 + 1;
        goto L18
    L20:
        r4.endDocument();
        goto L22
    L21:
        r4.startDocument(null, Boolean.FALSE);
    L22:
        r5.nextToken();
        goto L4
    L23:
        r4.flush();
        return r6.toByteArray();
    }

    public EventStream parseEventStream(XmlPullParser r12) {
        String r3 = parseString(r12, "schemeIdUri", _UrlKt.FRAGMENT_ENCODE_SET);
        String r4 = parseString(r12, "value", _UrlKt.FRAGMENT_ENCODE_SET);
        long r5 = parseLong(r12, "timescale", 1);
        long r8 = parseLong(r12, "presentationTimeOffset", 0);
        ArrayList r0 = new ArrayList();
        ByteArrayOutputStream r10 = new ByteArrayOutputStream(512);
    L3:
        r12.next();
        if (XmlPullParserUtil.isStartTag(r12, "Event") == false) goto L6;
        DashManifestParser r2 = this;
        long r6 = r5;
        String r52 = r4;
        String r42 = r3;
        XmlPullParser r32 = r12;
        r0.add(r2.parseEvent(r32, r42, r52, r6, r8, r10));
    L8:
        if (XmlPullParserUtil.isEndTag(r32, "EventStream") == true) goto L9;
        DashManifestParser this = r2;
        r12 = r32;
        r3 = r42;
        r4 = r52;
        r5 = r6;
        goto L3
    L9:
        long[] r11 = new long[r0.size()];
        EventMessage[] r82 = new EventMessage[r0.size()];
        int r122 = 0;
    L11:
        if (r122 >= r0.size()) goto L14;
        Pair r1 = (Pair) r0.get(r122);
        r11[r122] = ((Long) r1.first).longValue();
        r82[r122] = (EventMessage) r1.second;
        r122 = r122 + 1;
        goto L11
    L14:
        return r2.buildEventStream(r42, r52, r6, r11, r82);
    L6:
        r2 = this;
        r6 = r5;
        r52 = r4;
        r42 = r3;
        r32 = r12;
        maybeSkipTag(r32);
        goto L8
    }

    public RangedUri parseInitialization(XmlPullParser r3) {
        return parseRangedUrl(r3, "sourceURL", "range");
    }

    public Label parseLabel(XmlPullParser r2) {
        return new Label(r2.getAttributeValue(null, "lang"), parseText(r2, "Label"));
    }

    public DashManifest parseMediaPresentationDescription(XmlPullParser r41, Uri r42) {
        DashManifestParser r0 = this;
        boolean r11 = r0.isDvbProfileDeclared(r0.parseProfiles(r41, "profiles", new String[0]));
        long r7 = parseDateTime(r41, "availabilityStartTime", -9223372036854775807L);
        long r14 = parseDuration(r41, "mediaPresentationDuration", -9223372036854775807L);
        long r16 = parseDuration(r41, "minBufferTime", -9223372036854775807L);
        boolean r18 = "dynamic".equals(r41.getAttributeValue(null, "type"));
        if (r18 == false) goto L5;
        long r19 = parseDuration(r41, "minimumUpdatePeriod", -9223372036854775807L);
    L6:
        if (r18 == false) goto L8;
        long r9 = parseDuration(r41, "timeShiftBufferDepth", -9223372036854775807L);
    L9:
        if (r18 == false) goto L11;
        long r21 = parseDuration(r41, "suggestedPresentationDelay", -9223372036854775807L);
    L12:
        long r23 = parseDateTime(r41, "publishTime", -9223372036854775807L);
        long r5 = 0;
        if (r18 == false) goto L15;
        long r25 = 0;
    L16:
        String r2 = r42.toString();
        String r4 = r42.toString();
        if (r11 == false) goto L19;
        int r13 = 1;
    L20:
        ArrayList r132 = Lists.newArrayList(new BaseUrl[]{new BaseUrl(r2, r4, r13, 1)});
        ArrayList r22 = new ArrayList();
        ArrayList r3 = new ArrayList();
        if (r18 == false) goto L23;
        r5 = -9223372036854775807L;
    L23:
        ArrayList r31 = r132;
        long r12 = r25;
        boolean r252 = false;
        boolean r27 = false;
        ProgramInformation r32 = null;
        UtcTimingElement r33 = null;
        Uri r34 = null;
        ServiceDescriptionElement r35 = null;
    L24:
        r41.next();
        if (XmlPullParserUtil.isStartTag(r41, "BaseURL") == false) goto L30;
        if (r27 == true) goto L28;
        r12 = r0.parseAvailabilityTimeOffsetUs(r41, r12);
        r27 = true;
    L28:
        ArrayList r43 = r31;
        ArrayList r312 = r22;
        r3.addAll(r0.parseBaseUrl(r41, r43, r11));
    L29:
        ArrayList r28 = r3;
        long r24 = r12;
        long r36 = r14;
        ArrayList r122 = r312;
        r31 = r43;
    L64:
        if (XmlPullParserUtil.isEndTag(r41, "MPD") == true) goto L66;
        long r142 = r9;
        long r92 = r16;
        ArrayList r162 = r122;
        r0 = this;
        r18 = r18;
        r19 = r19;
        r12 = r24;
        r22 = r162;
        r3 = r28;
        r16 = r92;
        r9 = r142;
        r14 = r36;
        goto L24
    L66:
        if (r36 == (-9223372036854775807L)) goto L68;
    L71:
        Throwable r15 = null;
        long r44 = r36;
    L76:
        if (r122.isEmpty() == true) goto L80;
        long r112 = r9;
        return buildMediaPresentationDescription(r7, r44, r16, r18, r19, r112, r21, r23, r32, r33, r35, r34, r122);
    L80:
        throw ParserException.createForMalformedManifest("No periods found.", r15);
    L68:
        if (r5 == (-9223372036854775807L)) goto L70;
        r44 = r5;
        r15 = null;
        goto L76
    L70:
        if (r18 == true) goto L71;
        throw ParserException.createForMalformedManifest("Unable to determine duration of static manifest.", null);
    L30:
        r43 = r31;
        r312 = r22;
        if (XmlPullParserUtil.isStartTag(r41, "ProgramInformation") == false) goto L34;
        r32 = parseProgramInformation(r41);
        goto L29
    L34:
        if (XmlPullParserUtil.isStartTag(r41, "UTCTiming") == false) goto L37;
        r33 = parseUtcTiming(r41);
        goto L29
    L37:
        if (XmlPullParserUtil.isStartTag(r41, "Location") == false) goto L40;
        r34 = UriUtil.resolveToUri(r42.toString(), r41.nextText());
        goto L29
    L40:
        if (XmlPullParserUtil.isStartTag(r41, "ServiceDescription") == false) goto L43;
        r35 = parseServiceDescription(r41);
        goto L29
    L43:
        if (XmlPullParserUtil.isStartTag(r41, "Period") == false) goto L62;
        if (r252 == true) goto L62;
        if (r3.isEmpty() == true) goto L49;
        ArrayList r26 = r3;
        r28 = r26;
        r31 = r43;
        long r37 = r5;
        long r52 = r12;
        r122 = r312;
    L50:
        Pair<Period, Long> r29 = parsePeriod(r41, r26, r37, r52, r7, r9, r11);
        Period r02 = (Period) r29.first;
        r36 = r14;
        if (r02.startMs != (-9223372036854775807L)) goto L56;
        if (r18 == false) goto L55;
        long r133 = r37;
        r252 = true;
    L61:
        r24 = r52;
        r5 = r133;
        goto L64
    L55:
        throw ParserException.createForMalformedManifest("Unable to determine start of period " + r122.size(), null);
    L56:
        long r210 = ((Long) r29.second).longValue();
        if (r210 != (-9223372036854775807L)) goto L59;
        r133 = -9223372036854775807L;
    L60:
        r122.add(r02);
        goto L61
    L59:
        r133 = r02.startMs + r210;
        goto L60
    L49:
        r28 = r3;
        r26 = r43;
        r37 = r5;
        r52 = r12;
        r122 = r312;
        r31 = r26;
    L62:
        r28 = r3;
        r36 = r14;
        r31 = r43;
        long r38 = r5;
        long r53 = r12;
        r122 = r312;
        maybeSkipTag(r41);
        r5 = r38;
        r24 = r53;
        goto L64
    L19:
        r13 = Integer.MIN_VALUE;
        goto L20
    L15:
        r25 = -9223372036854775807L;
        goto L16
    L11:
        r21 = -9223372036854775807L;
        goto L12
    L8:
        r9 = -9223372036854775807L;
        goto L9
    L5:
        r19 = -9223372036854775807L;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v17 */
    public Pair<Period, Long> parsePeriod(XmlPullParser r30, List<BaseUrl> r31, long r32, long r34, long r36, long r38, boolean r40) {
        DashManifestParser r0 = this;
        XmlPullParser r1 = r30;
        String r16 = r1.getAttributeValue(null, "id");
        long r17 = parseDuration(r1, "start", r32);
        if (r36 == (-9223372036854775807L)) goto L5;
        long r4 = r36 + r17;
    L6:
        long r6 = parseDuration(r1, "duration", -9223372036854775807L);
        ArrayList r8 = new ArrayList();
        long r21 = -9223372036854775807L;
        ArrayList r13 = new ArrayList();
        boolean r19 = false;
        Descriptor r20 = null;
        long r11 = r34;
        ArrayList r2 = r8;
        SegmentBase r3 = null;
        long r82 = -9223372036854775807L;
        ?? r10 = new ArrayList();
    L7:
        r1.next();
        if (XmlPullParserUtil.isStartTag(r1, "BaseURL") == false) goto L12;
        if (r19 == true) goto L11;
        r11 = r0.parseAvailabilityTimeOffsetUs(r1, r11);
        r19 = true;
    L11:
        r10.addAll(r0.parseBaseUrl(r1, r31, r40));
        ArrayList r24 = r2;
        long r25 = r11;
        ArrayList r33 = r13;
        boolean r112 = r19;
        long r14 = r21;
        Object r23 = null;
        long r12 = r6;
        List<BaseUrl> r212 = r10;
        Descriptor r62 = r20;
        XmlPullParser r7 = r1;
        SegmentBase r102 = r3;
        long r192 = r82;
        long r83 = r4;
    L41:
        if (XmlPullParserUtil.isEndTag(r7, "Period") == true) goto L43;
        r0 = this;
        r1 = r7;
        r4 = r83;
        r3 = r102;
        r82 = r192;
        r10 = r212;
        r2 = r24;
        r20 = r62;
        r19 = r112;
        r6 = r12;
        r21 = r14;
        r11 = r25;
        r13 = r33;
        goto L7
    L43:
        return Pair.create(buildPeriod(r16, r17, r24, r33, r62), Long.valueOf(r12));
    L12:
        ArrayList r322 = r2;
        if (XmlPullParserUtil.isStartTag(r1, "AdaptationSet") == true) goto L15;
        r212 = r10;
        ArrayList r242 = r13;
        long r27 = r11;
        long r103 = r82;
        if (XmlPullParserUtil.isStartTag(r1, "EventStream") == false) goto L25;
        r242.add(parseEventStream(r30));
        SegmentBase r323 = r3;
        r12 = r6;
        r33 = r242;
        r24 = r322;
        r14 = -9223372036854775807L;
        r23 = null;
        r7 = r1;
        long r02 = r27;
    L20:
        r83 = r4;
    L38:
        r25 = r02;
        r62 = r20;
        long r272 = r103;
        r102 = r323;
    L28:
        r112 = r19;
        r192 = r272;
        goto L41
    L25:
        if (XmlPullParserUtil.isStartTag(r1, "SegmentBase") == false) goto L30;
        SegmentBase r35 = r0.parseSegmentBase(r1, null);
        r25 = r27;
        r23 = null;
        r33 = r242;
        r24 = r322;
        r14 = -9223372036854775807L;
        r83 = r4;
        r12 = r6;
        r62 = r20;
        r7 = r1;
    L27:
        r272 = r103;
        r102 = r35;
        goto L28
    L30:
        if (XmlPullParserUtil.isStartTag(r1, "SegmentList") == false) goto L32;
        long r22 = r4;
        long r5 = r6;
        long r9 = r0.parseAvailabilityTimeOffsetUs(r1, -9223372036854775807L);
        r33 = r242;
        r24 = r322;
        r14 = -9223372036854775807L;
        SegmentBase r26 = r0.parseSegmentList(r1, null, r22, r5, r27, r9, r38);
        r25 = r27;
        r23 = null;
        r83 = r22;
        r12 = r5;
        r62 = r20;
        r7 = r1;
        r272 = r9;
        r102 = r26;
        goto L28
    L32:
        r323 = r3;
        r33 = r242;
        r24 = r322;
        r14 = -9223372036854775807L;
        if (XmlPullParserUtil.isStartTag(r1, "SegmentTemplate") == false) goto L35;
        r103 = r0.parseAvailabilityTimeOffsetUs(r1, -9223372036854775807L);
        r23 = null;
        r35 = r0.parseSegmentTemplate(r1, null, ImmutableList.of(), r4, r6, r27, r103, r38);
        r12 = r6;
        r7 = r1;
        r83 = r4;
        r25 = r27;
        r62 = r20;
        goto L27
    L35:
        r23 = null;
        r12 = r6;
        r7 = r1;
        r02 = r27;
        r83 = r4;
        if (XmlPullParserUtil.isStartTag(r7, "AssetIdentifier") == false) goto L39;
        r20 = parseDescriptor(r7, "AssetIdentifier");
        goto L38
    L39:
        maybeSkipTag(r7);
        goto L38
    L15:
        if (r10.isEmpty() == true) goto L18;
        List<BaseUrl> r28 = r10;
        r212 = r28;
    L17:
        ArrayList r243 = r13;
        long r273 = r11;
        long r104 = r4;
        long r42 = r6;
        AdaptationSet r29 = r0.parseAdaptationSet(r1, r28, r3, r42, r273, r82, r104, r38, r40);
        r4 = r104;
        r103 = r82;
        r322.add(r29);
        r323 = r3;
        r12 = r42;
        r33 = r243;
        r23 = null;
        r7 = r1;
        r02 = r273;
        r24 = r322;
        r14 = -9223372036854775807L;
        goto L20
    L18:
        r212 = r10;
        r28 = r31;
        goto L17
    L5:
        r4 = -9223372036854775807L;
        goto L6
    }

    public String[] parseProfiles(XmlPullParser r1, String r2, String[] r3) {
        String r0 = r1.getAttributeValue(null, r2);
        if (r0 != null) goto L6;
        return r3;
    L6:
        return r0.split(",");
    }

    public ProgramInformation parseProgramInformation(XmlPullParser r8) {
        String r0 = null;
        String r5 = parseString(r8, "moreInformationURL", null);
        String r6 = parseString(r8, "lang", null);
        String r7 = null;
        String r1 = null;
    L3:
        r8.next();
        if (XmlPullParserUtil.isStartTag(r8, "Title") == false) goto L8;
        r0 = r8.nextText();
    L6:
        String r3 = r7;
        String r2 = r0;
        String r4 = r1;
        if (XmlPullParserUtil.isEndTag(r8, "ProgramInformation") == true) goto L17;
        r0 = r2;
        r7 = r3;
        r1 = r4;
        goto L3
    L17:
        return new ProgramInformation(r2, r3, r4, r5, r6);
    L8:
        if (XmlPullParserUtil.isStartTag(r8, "Source") == false) goto L11;
        r7 = r8.nextText();
        goto L6
    L11:
        if (XmlPullParserUtil.isStartTag(r8, "Copyright") == false) goto L13;
        r1 = r8.nextText();
        goto L6
    L13:
        maybeSkipTag(r8);
        goto L6
    }

    public RangedUri parseRangedUrl(XmlPullParser r8, String r9, String r10) {
        String r2 = r8.getAttributeValue(null, r9);
        String r82 = r8.getAttributeValue(null, r10);
        long r92 = -1;
        if (r82 == null) goto L8;
        String[] r83 = r82.split("-");
        long r0 = Long.parseLong(r83[0]);
        if (r83.length != 2) goto L10;
        r92 = (Long.parseLong(r83[1]) - r0) + 1;
    L10:
        return buildRangedUri(r2, r0, r92);
    L8:
        r0 = 0;
        goto L10
    }

    public RepresentationInfo parseRepresentation(XmlPullParser r35, List<BaseUrl> r36, String r37, String r38, String r39, String r40, int r41, int r42, float r43, int r44, int r45, String r46, List<Descriptor> r47, List<Descriptor> r48, List<Descriptor> r49, List<Descriptor> r50, SegmentBase r51, long r52, long r54, long r56, long r58, long r60, boolean r62) {
        DashManifestParser r0 = this;
        XmlPullParser r1 = r35;
        String r14 = r1.getAttributeValue(null, "id");
        int r15 = parseInt(r1, "bandwidth", -1);
        String r16 = parseString(r1, "mimeType", r37);
        String r13 = parseString(r1, "codecs", r38);
        String r17 = parseString(r1, "scte214:supplementalCodecs", r39);
        String r18 = parseString(r1, "scte214:supplementalProfiles", r40);
        int r19 = parseInt(r1, "width", r41);
        int r20 = parseInt(r1, "height", r42);
        float r21 = parseFrameRate(r1, r43);
        int r22 = parseInt(r1, "audioSamplingRate", r45);
        ArrayList r2 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList(r49);
        ArrayList r6 = new ArrayList(r50);
        ArrayList r8 = new ArrayList();
        int r23 = r44;
        long r11 = r58;
        String r24 = null;
        boolean r25 = false;
        SegmentBase r3 = r51;
        long r9 = r56;
    L3:
        r1.next();
        ArrayList r392 = r2;
        if (XmlPullParserUtil.isStartTag(r1, "BaseURL") == false) goto L10;
        if (r25 == true) goto L7;
        r9 = r0.parseAvailabilityTimeOffsetUs(r1, r9);
        r25 = true;
    L7:
        SegmentBase r372 = r3;
        String r26 = r14;
        r8.addAll(r0.parseBaseUrl(r1, r36, r62));
        ArrayList r142 = r392;
        XmlPullParser r02 = r1;
        ArrayList r27 = r5;
        ArrayList r32 = r6;
        ArrayList r30 = r8;
        long r28 = r11;
        String r31 = r13;
        int r272 = r15;
        int r7 = r23;
        String r322 = r24;
        boolean r33 = r25;
        SegmentBase r232 = r372;
    L8:
        ArrayList r152 = r4;
        ArrayList r302 = r30;
    L9:
        long r242 = r9;
        ArrayList r303 = r302;
    L45:
        if (XmlPullParserUtil.isEndTag(r02, "Representation") == true) goto L46;
        int r410 = r19;
        ArrayList r192 = r152;
        String r153 = r18;
        ArrayList r182 = r142;
        r19 = r410;
        r4 = r192;
        r5 = r27;
        r6 = r32;
        r2 = r182;
        r3 = r232;
        r9 = r242;
        r11 = r28;
        r8 = r303;
        r13 = r31;
        r25 = r33;
        r24 = r322;
        r23 = r7;
        r17 = r17;
        r18 = r153;
        r14 = r26;
        r15 = r272;
        r1 = r02;
        r0 = this;
        goto L3
    L46:
        ArrayList r193 = r152;
        String r154 = r18;
        ArrayList r183 = r142;
        String r143 = r17;
        ArrayList r172 = r32;
        ArrayList r162 = r27;
        Format r03 = buildFormat(r26, r16, r19, r20, r21, r7, r22, r272, r46, r47, r48, r31, r143, r154, r162, r172);
        if (r232 != null) goto L50;
        r232 = new SegmentBase.SingleSegmentBase();
    L50:
        boolean r29 = r303.isEmpty();
        List<BaseUrl> r304 = r303;
        if (r29 == false) goto L55;
        r304 = r36;
    L55:
        return new RepresentationInfo(r03, r304, r232, r322, r183, r193, r162, r172, -1);
    L10:
        SegmentBase r373 = r3;
        r26 = r14;
        if (XmlPullParserUtil.isStartTag(r1, "AudioChannelConfiguration") == false) goto L14;
        int r34 = r0.parseAudioChannelConfiguration(r1, r13);
        r232 = r373;
        r142 = r392;
        r02 = r1;
        r7 = r34;
        r27 = r5;
        r32 = r6;
        r30 = r8;
        r28 = r11;
        r31 = r13;
        r272 = r15;
        r322 = r24;
        r33 = r25;
        goto L8
    L14:
        if (XmlPullParserUtil.isStartTag(r1, "SegmentBase") == false) goto L17;
        SegmentBase.SingleSegmentBase r310 = r0.parseSegmentBase(r1, (SegmentBase.SingleSegmentBase) r373);
        r142 = r392;
        r02 = r1;
        r27 = r5;
        r302 = r8;
        r28 = r11;
        r31 = r13;
        r272 = r15;
        r7 = r23;
        r322 = r24;
        r33 = r25;
        r232 = r310;
        r152 = r4;
        r32 = r6;
        goto L9
    L17:
        if (XmlPullParserUtil.isStartTag(r1, "SegmentList") == false) goto L19;
        long r72 = r9;
        long r92 = r0.parseAvailabilityTimeOffsetUs(r1, r11);
        r142 = r392;
        r303 = r8;
        r272 = r15;
        r152 = r4;
        SegmentBase.SegmentList r210 = r0.parseSegmentList(r1, (SegmentBase.SegmentList) r373, r52, r54, r72, r92, r60);
        r02 = r1;
        r31 = r13;
        r322 = r24;
        r33 = r25;
        r32 = r6;
        r242 = r72;
        r7 = r23;
        r232 = r210;
        r27 = r5;
        r28 = r92;
        goto L45
    L19:
        r142 = r392;
        ArrayList r282 = r5;
        ArrayList r292 = r6;
        ArrayList r305 = r8;
        long r73 = r9;
        r272 = r15;
        r152 = r4;
        if (XmlPullParserUtil.isStartTag(r1, "SegmentTemplate") == false) goto L22;
        long r10 = r0.parseAvailabilityTimeOffsetUs(r1, r11);
        r31 = r13;
        SegmentBase.SegmentTemplate r211 = r0.parseSegmentTemplate(r1, (SegmentBase.SegmentTemplate) r373, r50, r52, r54, r73, r10, r60);
        r02 = r1;
        r322 = r24;
        r33 = r25;
        r32 = r292;
        r242 = r73;
        r7 = r23;
        r232 = r211;
        r27 = r282;
        r28 = r10;
        r303 = r305;
        goto L45
    L22:
        r02 = r1;
        r31 = r13;
        if (XmlPullParserUtil.isStartTag(r02, "ContentProtection") == false) goto L33;
        Pair<String, DrmInitData.SchemeData> r12 = parseContentProtection(r35);
        Object r212 = r12.first;
        if (r212 == null) goto L27;
        r24 = (String) r212;
    L27:
        Object r110 = r12.second;
        if (r110 == null) goto L30;
        r142.add((DrmInitData.SchemeData) r110);
    L30:
        r322 = r24;
        r33 = r25;
        r27 = r282;
        r32 = r292;
        r242 = r73;
        r28 = r11;
    L31:
        r7 = r23;
        r232 = r373;
        r303 = r305;
        goto L45
    L33:
        if (XmlPullParserUtil.isStartTag(r02, "InbandEventStream") == false) goto L37;
        r152.add(parseDescriptor(r02, "InbandEventStream"));
        r27 = r282;
    L35:
        r32 = r292;
    L43:
        r28 = r11;
        r322 = r24;
        r33 = r25;
        r242 = r73;
        goto L31
    L37:
        if (XmlPullParserUtil.isStartTag(r02, "EssentialProperty") == false) goto L39;
        r27 = r282;
        r27.add(parseDescriptor(r02, "EssentialProperty"));
        goto L35
    L39:
        r27 = r282;
        if (XmlPullParserUtil.isStartTag(r02, "SupplementalProperty") == false) goto L42;
        r32 = r292;
        r32.add(parseDescriptor(r02, "SupplementalProperty"));
        goto L43
    L42:
        r32 = r292;
        maybeSkipTag(r02);
        goto L43
    }

    public int parseRoleFlagsFromAccessibilityDescriptors(List<Descriptor> r6) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= r6.size()) goto L13;
        Descriptor r2 = r6.get(r0);
        if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", r2.schemeIdUri) == false) goto L10;
        int r22 = parseRoleFlagsFromDashRoleScheme(r2.value);
    L8:
        r1 = r1 | r22;
    L12:
        r0 = r0 + 1;
        goto L4
    L10:
        if (Ascii.equalsIgnoreCase("urn:tva:metadata:cs:AudioPurposeCS:2007", r2.schemeIdUri) == false) goto L12;
        r22 = parseTvaAudioPurposeCsValue(r2.value);
        goto L8
    L13:
        return r1;
    }

    public int parseRoleFlagsFromDashRoleScheme(String r7) {
        if (r7 != null) goto L5;
        return 0;
    L5:
        char r5 = 65535;
        switch(r7.hashCode()) {
            case -2060497896: goto L57;
            case -1724546052: goto L53;
            case -1580883024: goto L49;
            case -1574842690: goto L45;
            case -1408024454: goto L41;
            case -1396432756: goto L37;
            case 99825: goto L33;
            case 3343801: goto L29;
            case 3530173: goto L25;
            case 552573414: goto L21;
            case 899152809: goto L17;
            case 1629013393: goto L13;
            case 1855372047: goto L9;
            default: goto L60;
        };
    L60:
        switch(r5) {
            case 0: goto L78;
            case 1: goto L76;
            case 2: goto L74;
            case 3: goto L78;
            case 4: goto L73;
            case 5: goto L78;
            case 6: goto L71;
            case 7: goto L70;
            case 8: goto L68;
            case 9: goto L66;
            case 10: goto L65;
            case 11: goto L63;
            case 12: goto L62;
            default: goto L61;
        };
    L61:
        return 0;
    L62:
        return 4;
    L63:
        return 32;
    L65:
        return 8;
    L66:
        return 64;
    L68:
        return 256;
    L70:
        return 1;
    L71:
        return 16;
    L73:
        return 2;
    L74:
        return 2048;
    L76:
        return 512;
    L78:
        return 128;
    L9:
        if (r7.equals(MediaTrack.ROLE_SUPPLEMENTARY) == false) goto L60;
        r5 = '\f';
        goto L60
    L13:
        if (r7.equals(MediaTrack.ROLE_EMERGENCY) == false) goto L60;
        r5 = 11;
        goto L60
    L17:
        if (r7.equals(MediaTrack.ROLE_COMMENTARY) == false) goto L60;
        r5 = '\n';
        goto L60
    L21:
        if (r7.equals(MediaTrack.ROLE_CAPTION) == false) goto L60;
        r5 = '\t';
        goto L60
    L25:
        if (r7.equals(MediaTrack.ROLE_SIGN) == false) goto L60;
        r5 = '\b';
        goto L60
    L29:
        if (r7.equals(MediaTrack.ROLE_MAIN) == false) goto L60;
        r5 = 7;
        goto L60
    L33:
        if (r7.equals(MediaTrack.ROLE_DUB) == false) goto L60;
        r5 = 6;
        goto L60
    L37:
        if (r7.equals("forced-subtitle") == false) goto L60;
        r5 = 5;
        goto L60
    L41:
        if (r7.equals("alternate") == false) goto L60;
        r5 = 4;
        goto L60
    L45:
        if (r7.equals(MediaTrack.ROLE_FORCED_SUBTITLE) == false) goto L60;
        r5 = 3;
        goto L60
    L49:
        if (r7.equals("enhanced-audio-intelligibility") == false) goto L60;
        r5 = 2;
        goto L60
    L53:
        if (r7.equals(MediaTrack.ROLE_DESCRIPTION) == false) goto L60;
        r5 = 1;
        goto L60
    L57:
        if (r7.equals(MediaTrack.ROLE_SUBTITLE) == false) goto L60;
        r5 = 0;
        goto L60
    }

    public int parseRoleFlagsFromProperties(List<Descriptor> r4) {
        int r3 = 0;
        int r0 = 0;
    L4:
        if (r3 >= r4.size()) goto L9;
        if (Ascii.equalsIgnoreCase("http://dashif.org/guidelines/trickmode", r4.get(r3).schemeIdUri) == false) goto L8;
        r0 = 16384;
    L8:
        r3 = r3 + 1;
        goto L4
    L9:
        return r0;
    }

    public int parseRoleFlagsFromRoleDescriptors(List<Descriptor> r6) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= r6.size()) goto L9;
        Descriptor r2 = r6.get(r0);
        if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", r2.schemeIdUri) == false) goto L8;
        r1 = r1 | parseRoleFlagsFromDashRoleScheme(r2.value);
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r1;
    }

    public SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser r18, SegmentBase.SingleSegmentBase r19) {
        if (r19 == null) goto L5;
        long r4 = r19.timescale;
    L6:
        long r9 = parseLong(r18, "timescale", r4);
        long r42 = 0;
        if (r19 == null) goto L9;
        long r6 = r19.presentationTimeOffset;
    L10:
        long r11 = parseLong(r18, "presentationTimeOffset", r6);
        if (r19 == null) goto L13;
        long r62 = r19.indexStart;
    L14:
        if (r19 == null) goto L16;
        r42 = r19.indexLength;
    L16:
        RangedUri r13 = null;
        String r8 = r18.getAttributeValue(null, "indexRange");
        if (r8 == null) goto L19;
        String[] r43 = r8.split("-");
        r62 = Long.parseLong(r43[0]);
        r42 = (Long.parseLong(r43[1]) - r62) + 1;
    L19:
        long r15 = r42;
        if (r19 == null) goto L22;
        r13 = r19.initialization;
    L22:
        r18.next();
        if (XmlPullParserUtil.isStartTag(r18, "Initialization") == false) goto L26;
        r13 = parseInitialization(r18);
    L25:
        RangedUri r82 = r13;
        if (XmlPullParserUtil.isEndTag(r18, "SegmentBase") == true) goto L30;
        r13 = r82;
        goto L22
    L30:
        return buildSingleSegmentBase(r82, r9, r11, r62, r15);
    L26:
        maybeSkipTag(r18);
        goto L25
    L13:
        r62 = 0;
        goto L14
    L9:
        r6 = 0;
        goto L10
    L5:
        r4 = 1;
        goto L6
    }

    public SegmentBase.SegmentList parseSegmentList(XmlPullParser r25, SegmentBase.SegmentList r26, long r27, long r29, long r31, long r33, long r35) {
        long r2 = 1;
        if (r26 == null) goto L5;
        long r4 = r26.timescale;
    L6:
        long r8 = parseLong(r25, "timescale", r4);
        if (r26 == null) goto L9;
        long r42 = r26.presentationTimeOffset;
    L10:
        long r10 = parseLong(r25, "presentationTimeOffset", r42);
        if (r26 == null) goto L13;
        long r43 = r26.duration;
    L14:
        long r14 = parseLong(r25, "duration", r43);
        if (r26 == null) goto L17;
        r2 = r26.startNumber;
    L17:
        long r12 = parseLong(r25, "startNumber", r2);
        long r17 = getFinalAvailabilityTimeOffset(r31, r33);
        List<RangedUri> r7 = null;
        RangedUri r16 = null;
        List<SegmentBase.SegmentTimelineElement> r19 = null;
    L18:
        r25.next();
        if (XmlPullParserUtil.isStartTag(r25, "Initialization") == false) goto L22;
        r16 = parseInitialization(r25);
        long r22 = r8;
    L31:
        if (XmlPullParserUtil.isEndTag(r25, "SegmentList") == true) goto L32;
        r8 = r22;
        goto L18
    L32:
        if (r26 == null) goto L43;
        if (r16 != null) goto L36;
        r16 = r26.initialization;
    L36:
        if (r19 != null) goto L39;
        r19 = r26.segmentTimeline;
    L39:
        if (r7 != null) goto L43;
        r7 = r26.mediaSegments;
    L43:
        return buildSegmentList(r16, r22, r10, r12, r14, r19, r17, r7, r35, r27);
    L22:
        if (XmlPullParserUtil.isStartTag(r25, "SegmentTimeline") == false) goto L24;
        r22 = r8;
        r19 = parseSegmentTimeline(r25, r22, r29);
        goto L31
    L24:
        r22 = r8;
        if (XmlPullParserUtil.isStartTag(r25, "SegmentURL") == false) goto L29;
        if (r7 != null) goto L28;
        r7 = new ArrayList();
    L28:
        r7.add(parseSegmentUrl(r25));
        goto L31
    L29:
        maybeSkipTag(r25);
        goto L31
    L13:
        r43 = -9223372036854775807L;
        goto L14
    L9:
        r42 = 0;
        goto L10
    L5:
        r4 = 1;
        goto L6
    }

    public SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser r23, SegmentBase.SegmentTemplate r24, List<Descriptor> r25, long r26, long r28, long r30, long r32, long r34) {
        DashManifestParser r0 = this;
        long r2 = 1;
        if (r24 == null) goto L5;
        long r4 = r24.timescale;
    L6:
        long r42 = parseLong(r23, "timescale", r4);
        if (r24 == null) goto L9;
        long r7 = r24.presentationTimeOffset;
    L10:
        long r72 = parseLong(r23, "presentationTimeOffset", r7);
        if (r24 == null) goto L13;
        long r9 = r24.duration;
    L14:
        long r11 = parseLong(r23, "duration", r9);
        if (r24 == null) goto L17;
        r2 = r24.startNumber;
    L17:
        long r92 = parseLong(r23, "startNumber", r2);
        long r13 = parseLastSegmentNumberSupplementalProperty(r25);
        long r15 = getFinalAvailabilityTimeOffset(r30, r32);
        if (r24 == null) goto L20;
        UrlTemplate r3 = r24.mediaTemplate;
    L21:
        UrlTemplate r17 = r0.parseUrlTemplate(r23, "media", r3);
        if (r24 == null) goto L24;
        UrlTemplate r22 = r24.initializationTemplate;
    L25:
        UrlTemplate r18 = r0.parseUrlTemplate(r23, "initialization", r22);
        RangedUri r19 = null;
        List<SegmentBase.SegmentTimelineElement> r20 = null;
    L26:
        r23.next();
        if (XmlPullParserUtil.isStartTag(r23, "Initialization") == false) goto L30;
        r19 = parseInitialization(r23);
        long r27 = r42;
    L34:
        if (XmlPullParserUtil.isEndTag(r23, "SegmentTemplate") == true) goto L35;
        long r43 = r72;
        r0 = this;
        r15 = r15;
        r13 = r13;
        r92 = r92;
        r72 = r43;
        r42 = r27;
        goto L26
    L35:
        if (r24 == null) goto L43;
        if (r19 != null) goto L39;
        r19 = r24.initialization;
    L39:
        if (r20 != null) goto L43;
        r20 = r24.segmentTimeline;
    L43:
        return buildSegmentTemplate(r19, r27, r72, r92, r13, r11, r20, r15, r18, r17, r34, r26);
    L30:
        if (XmlPullParserUtil.isStartTag(r23, "SegmentTimeline") == false) goto L32;
        r27 = r42;
        r20 = r0.parseSegmentTimeline(r23, r27, r28);
        goto L34
    L32:
        r27 = r42;
        maybeSkipTag(r23);
        goto L34
    L24:
        r22 = null;
        goto L25
    L20:
        r3 = null;
        goto L21
    L13:
        r9 = -9223372036854775807L;
        goto L14
    L9:
        r7 = 0;
        goto L10
    L5:
        r4 = 1;
        goto L6
    }

    public List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser r20, long r21, long r23) {
        ArrayList r1 = new ArrayList();
        long r3 = 0;
        long r5 = -9223372036854775807L;
        boolean r2 = false;
        int r7 = 0;
    L3:
        r20.next();
        if (XmlPullParserUtil.isStartTag(r20, "S") == false) goto L12;
        long r8 = parseLong(r20, "t", -9223372036854775807L);
        if (r2 == false) goto L9;
        ArrayList r22 = r1;
        r3 = addSegmentTimelineElementsToList(r22, r3, r5, r7, r8);
        r1 = r22;
    L9:
        if (r8 == (-9223372036854775807L)) goto L11;
        r3 = r8;
    L11:
        r5 = parseLong(r20, "d", -9223372036854775807L);
        r7 = parseInt(r20, "r", 0);
        r2 = true;
    L14:
        if (XmlPullParserUtil.isEndTag(r20, "SegmentTimeline") == false) goto L3;
        if (r2 == false) goto L17;
        addSegmentTimelineElementsToList(r1, r3, r5, r7, Util.scaleLargeTimestamp(r23, r21, 1000));
    L17:
        return r1;
    L12:
        maybeSkipTag(r20);
        goto L14
    }

    public RangedUri parseSegmentUrl(XmlPullParser r3) {
        return parseRangedUrl(r3, "media", "mediaRange");
    }

    public int parseSelectionFlagsFromDashRoleScheme(String r2) {
        if (r2 != null) goto L6;
        return 0;
    L6:
        if (r2.equals(MediaTrack.ROLE_FORCED_SUBTITLE) == false) goto L8;
        return 2;
    L8:
        if (r2.equals("forced-subtitle") == true) goto L12;
        return 0;
    L12:
        return 2;
    }

    public int parseSelectionFlagsFromRoleDescriptors(List<Descriptor> r6) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= r6.size()) goto L9;
        Descriptor r2 = r6.get(r0);
        if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", r2.schemeIdUri) == false) goto L8;
        r1 = r1 | parseSelectionFlagsFromDashRoleScheme(r2.value);
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r1;
    }

    public ServiceDescriptionElement parseServiceDescription(XmlPullParser r22) {
        long r4 = -9223372036854775807L;
        long r6 = -9223372036854775807L;
        long r8 = -9223372036854775807L;
        float r10 = -3.4028235E38f;
        float r11 = -3.4028235E38f;
    L3:
        r22.next();
        if (XmlPullParserUtil.isStartTag(r22, "Latency") == false) goto L8;
        r4 = parseLong(r22, "target", -9223372036854775807L);
        r6 = parseLong(r22, "min", -9223372036854775807L);
        r8 = parseLong(r22, "max", -9223372036854775807L);
    L6:
        long r13 = r4;
        long r15 = r6;
        long r17 = r8;
        float r19 = r10;
        float r20 = r11;
        if (XmlPullParserUtil.isEndTag(r22, "ServiceDescription") == true) goto L13;
        r4 = r13;
        r6 = r15;
        r8 = r17;
        r10 = r19;
        r11 = r20;
        goto L3
    L13:
        return new ServiceDescriptionElement(r13, r15, r17, r19, r20);
    L8:
        if (XmlPullParserUtil.isStartTag(r22, "PlaybackRate") == false) goto L6;
        r10 = parseFloat(r22, "min", -3.4028235E38f);
        r11 = parseFloat(r22, "max", -3.4028235E38f);
        goto L6
    }

    public Pair<Integer, Integer> parseTileCountFromProperties(List<Descriptor> r5) {
        char r4 = 0;
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L17;
        Descriptor r1 = r5.get(r0);
        if (Ascii.equalsIgnoreCase("http://dashif.org/thumbnail_tile", r1.schemeIdUri) == false) goto L8;
    L9:
        String r12 = r1.value;
        if (r12 == null) goto L16;
        String[] r13 = Util.split(r12, "x");
        if (r13.length != 2) goto L16;
        return Pair.create(Integer.valueOf(Integer.parseInt(r13[r4])), Integer.valueOf(Integer.parseInt(r13[1])));
    L16:
        r0 = r0 + 1;
        goto L4
    L8:
        if (Ascii.equalsIgnoreCase("http://dashif.org/guidelines/thumbnail_tile", r1.schemeIdUri) == false) goto L16;
    L17:
        return null;
    }

    public int parseTvaAudioPurposeCsValue(String r5) {
        if (r5 != null) goto L5;
        return 0;
    L5:
        char r3 = 65535;
        switch(r5.hashCode()) {
            case 49: goto L25;
            case 50: goto L21;
            case 51: goto L17;
            case 52: goto L13;
            case 53: goto L28;
            case 54: goto L9;
            default: goto L28;
        };
    L28:
        switch(r3) {
            case 0: goto L36;
            case 1: goto L34;
            case 2: goto L33;
            case 3: goto L31;
            case 4: goto L30;
            default: goto L29;
        };
    L29:
        return 0;
    L30:
        return 1;
    L31:
        return 8;
    L33:
        return 4;
    L34:
        return 2048;
    L36:
        return 512;
    L9:
        if (r5.equals("6") == false) goto L28;
        r3 = 4;
        goto L28
    L13:
        if (r5.equals("4") == false) goto L28;
        r3 = 3;
        goto L28
    L17:
        if (r5.equals("3") == false) goto L28;
        r3 = 2;
        goto L28
    L21:
        if (r5.equals("2") == false) goto L28;
        r3 = 1;
        goto L28
    L25:
        if (r5.equals("1") == false) goto L28;
        r3 = 0;
        goto L28
    }

    public UrlTemplate parseUrlTemplate(XmlPullParser r1, String r2, UrlTemplate r3) {
        String r0 = r1.getAttributeValue(null, r2);
        if (r0 != null) goto L5;
        return r3;
    L5:
        return UrlTemplate.compile(r0);
    }

    public UtcTimingElement parseUtcTiming(XmlPullParser r4) {
        return buildUtcTimingElement(r4.getAttributeValue(null, "schemeIdUri"), r4.getAttributeValue(null, "value"));
    }
}
