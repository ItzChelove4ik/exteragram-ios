package androidx.media3.exoplayer.hls.playlist;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.hls.HlsTrackMetadataEntry;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class HlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private static final Pattern REGEX_ACCOUNT = null;
    private static final Pattern REGEX_ASSET_LIST_URI = null;
    private static final Pattern REGEX_ASSET_URI = null;
    private static final Pattern REGEX_ATTR_BYTERANGE = null;
    private static final Pattern REGEX_ATTR_DURATION = null;
    private static final Pattern REGEX_ATTR_DURATION_PREFIXED = null;
    private static final Pattern REGEX_AUDIO = null;
    private static final Pattern REGEX_AUTOSELECT = null;
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = null;
    private static final Pattern REGEX_BANDWIDTH = null;
    private static final Pattern REGEX_BYTERANGE = null;
    private static final Pattern REGEX_BYTERANGE_LENGTH = null;
    private static final Pattern REGEX_BYTERANGE_START = null;
    private static final Pattern REGEX_CACHED = null;
    private static final Pattern REGEX_CAN_BLOCK_RELOAD = null;
    private static final Pattern REGEX_CAN_SKIP_DATE_RANGES = null;
    private static final Pattern REGEX_CAN_SKIP_UNTIL = null;
    private static final Pattern REGEX_CHANNELS = null;
    private static final Pattern REGEX_CHARACTERISTICS = null;
    private static final Pattern REGEX_CLASS = null;
    private static final Pattern REGEX_CLIENT_DEFINED_ATTRIBUTE_PREFIX = null;
    private static final Pattern REGEX_CLOSED_CAPTIONS = null;
    private static final Pattern REGEX_CODECS = null;
    private static final Pattern REGEX_CONTENT_MAY_VARY = null;
    private static final Pattern REGEX_CUE = null;
    private static final Pattern REGEX_DEFAULT = null;
    private static final Pattern REGEX_DOC_FILENAME = null;
    private static final Pattern REGEX_DOC_ID = null;
    private static final Pattern REGEX_END_DATE = null;
    private static final Pattern REGEX_END_ON_NEXT = null;
    private static final Pattern REGEX_FORCED = null;
    private static final Pattern REGEX_FRAME_RATE = null;
    private static final Pattern REGEX_GAP = null;
    private static final Pattern REGEX_GROUP_ID = null;
    private static final Pattern REGEX_HOLD_BACK = null;
    private static final Pattern REGEX_ID = null;
    private static final Pattern REGEX_IMPORT = null;
    private static final Pattern REGEX_INDEPENDENT = null;
    private static final Pattern REGEX_INSTREAM_ID = null;
    private static final Pattern REGEX_IV = null;
    private static final Pattern REGEX_KEYFORMAT = null;
    private static final Pattern REGEX_KEYFORMATVERSIONS = null;
    private static final Pattern REGEX_LANGUAGE = null;
    private static final Pattern REGEX_LAST_MSN = null;
    private static final Pattern REGEX_LAST_PART = null;
    private static final Pattern REGEX_MEDIA_DURATION = null;
    private static final Pattern REGEX_MEDIA_SEQUENCE = null;
    private static final Pattern REGEX_MEDIA_TITLE = null;
    private static final Pattern REGEX_METHOD = null;
    private static final Pattern REGEX_MIME = null;
    private static final Pattern REGEX_NAME = null;
    private static final Pattern REGEX_PART_HOLD_BACK = null;
    private static final Pattern REGEX_PART_TARGET_DURATION = null;
    private static final Pattern REGEX_PLANNED_DURATION = null;
    private static final Pattern REGEX_PLAYLIST_TYPE = null;
    private static final Pattern REGEX_PLAYOUT_LIMIT = null;
    private static final Pattern REGEX_PRECISE = null;
    private static final Pattern REGEX_PRELOAD_HINT_TYPE = null;
    private static final Pattern REGEX_RESOLUTION = null;
    private static final Pattern REGEX_RESTRICT = null;
    private static final Pattern REGEX_RESUME_OFFSET = null;
    private static final Pattern REGEX_SKIPPED_SEGMENTS = null;
    private static final Pattern REGEX_SNAP = null;
    private static final Pattern REGEX_START_DATE = null;
    private static final Pattern REGEX_SUBTITLES = null;
    private static final Pattern REGEX_SUPPLEMENTAL_CODECS = null;
    private static final Pattern REGEX_TARGET_DURATION = null;
    private static final Pattern REGEX_TIMELINE_OCCUPIES = null;
    private static final Pattern REGEX_TIMELINE_STYLE = null;
    private static final Pattern REGEX_TIME_OFFSET = null;
    private static final Pattern REGEX_TYPE = null;
    private static final Pattern REGEX_URI = null;
    private static final Pattern REGEX_VALUE = null;
    private static final Pattern REGEX_VARIABLE_REFERENCE = null;
    private static final Pattern REGEX_VERSION = null;
    private static final Pattern REGEX_VIDEO = null;
    private static final Pattern REGEX_VIDEO_RANGE = null;
    private final HlsMultivariantPlaylist multivariantPlaylist;
    private final HlsMediaPlaylist previousMediaPlaylist;

    public static final class DeltaUpdateException extends IOException {
        public DeltaUpdateException() {
        }
    }

    public static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> r1, BufferedReader r2) {
            this.extraLines = r1;
            this.reader = r2;
        }

        @EnsuresNonNullIf(expression = {"next"}, result = true)
        public boolean hasNext() {
            if (this.next == null) goto L6;
            return true;
        L6:
            if (this.extraLines.isEmpty() == true) goto L9;
            this.next = (String) Assertions.checkNotNull(this.extraLines.poll());
            return true;
        L9:
            String r0 = this.reader.readLine();
            this.next = r0;
            if (r0 == null) goto L14;
            String r02 = r0.trim();
            this.next = r02;
            if (r02.isEmpty() == true) goto L9;
            return true;
        L14:
            return false;
        }

        public String next() {
            if (hasNext() == false) goto L6;
            String r0 = this.next;
            this.next = null;
            return r0;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    static {
        REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
        REGEX_VIDEO = Pattern.compile("VIDEO=\"((?:.|\f)+?)\"");
        REGEX_AUDIO = Pattern.compile("AUDIO=\"((?:.|\f)+?)\"");
        REGEX_SUBTITLES = Pattern.compile("SUBTITLES=\"((?:.|\f)+?)\"");
        REGEX_CLOSED_CAPTIONS = Pattern.compile("CLOSED-CAPTIONS=\"((?:.|\f)+?)\"");
        REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
        REGEX_CHANNELS = Pattern.compile("CHANNELS=\"((?:.|\f)+?)\"");
        REGEX_VIDEO_RANGE = Pattern.compile("VIDEO-RANGE=(SDR|PQ|HLG)");
        REGEX_CODECS = Pattern.compile("CODECS=\"((?:.|\f)+?)\"");
        REGEX_SUPPLEMENTAL_CODECS = Pattern.compile("SUPPLEMENTAL-CODECS=\"((?:.|\f)+?)\"");
        REGEX_MIME = Pattern.compile("MIME=\"(.+?)\"");
        REGEX_CACHED = Pattern.compile("CACHED=\"(.+?)\"");
        REGEX_DOC_ID = Pattern.compile("DOCID=\"(.+?)\"");
        REGEX_DOC_FILENAME = Pattern.compile("DOCFILENAME=\"(.+?)\"");
        REGEX_ACCOUNT = Pattern.compile("ACCOUNT=\"(.+?)\"");
        REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
        REGEX_FRAME_RATE = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
        REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
        REGEX_ATTR_DURATION = Pattern.compile("DURATION=([\\d\\.]+)\\b");
        REGEX_ATTR_DURATION_PREFIXED = Pattern.compile("[:,]DURATION=([\\d\\.]+)\\b");
        REGEX_PART_TARGET_DURATION = Pattern.compile("PART-TARGET=([\\d\\.]+)\\b");
        REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
        REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
        REGEX_CAN_SKIP_UNTIL = Pattern.compile("CAN-SKIP-UNTIL=([\\d\\.]+)\\b");
        REGEX_CAN_SKIP_DATE_RANGES = compileBooleanAttrPattern("CAN-SKIP-DATERANGES");
        REGEX_SKIPPED_SEGMENTS = Pattern.compile("SKIPPED-SEGMENTS=(\\d+)\\b");
        REGEX_HOLD_BACK = Pattern.compile("[:|,]HOLD-BACK=([\\d\\.]+)\\b");
        REGEX_PART_HOLD_BACK = Pattern.compile("PART-HOLD-BACK=([\\d\\.]+)\\b");
        REGEX_CAN_BLOCK_RELOAD = compileBooleanAttrPattern("CAN-BLOCK-RELOAD");
        REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
        REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
        REGEX_MEDIA_TITLE = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
        REGEX_LAST_MSN = Pattern.compile("LAST-MSN=(\\d+)\\b");
        REGEX_LAST_PART = Pattern.compile("LAST-PART=(\\d+)\\b");
        REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
        REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
        REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
        REGEX_BYTERANGE_START = Pattern.compile("BYTERANGE-START=(\\d+)\\b");
        REGEX_BYTERANGE_LENGTH = Pattern.compile("BYTERANGE-LENGTH=(\\d+)\\b");
        REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
        REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"((?:.|\f)+?)\"");
        REGEX_KEYFORMATVERSIONS = Pattern.compile("KEYFORMATVERSIONS=\"((?:.|\f)+?)\"");
        REGEX_URI = Pattern.compile("URI=\"((?:.|\f)+?)\"");
        REGEX_IV = Pattern.compile("IV=([^,.*]+)");
        REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
        REGEX_PRELOAD_HINT_TYPE = Pattern.compile("TYPE=(PART|MAP)");
        REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"((?:.|\f)+?)\"");
        REGEX_NAME = Pattern.compile("NAME=\"((?:.|\f)+?)\"");
        REGEX_GROUP_ID = Pattern.compile("GROUP-ID=\"((?:.|\f)+?)\"");
        REGEX_CHARACTERISTICS = Pattern.compile("CHARACTERISTICS=\"((?:.|\f)+?)\"");
        REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
        REGEX_AUTOSELECT = compileBooleanAttrPattern("AUTOSELECT");
        REGEX_DEFAULT = compileBooleanAttrPattern(PluginsConstants.Strategy.DEFAULT);
        REGEX_FORCED = compileBooleanAttrPattern("FORCED");
        REGEX_INDEPENDENT = compileBooleanAttrPattern("INDEPENDENT");
        REGEX_GAP = compileBooleanAttrPattern("GAP");
        REGEX_PRECISE = compileBooleanAttrPattern("PRECISE");
        REGEX_VALUE = Pattern.compile("VALUE=\"((?:.|\f)+?)\"");
        REGEX_IMPORT = Pattern.compile("IMPORT=\"((?:.|\f)+?)\"");
        REGEX_ID = Pattern.compile("[:,]ID=\"((?:.|\f)+?)\"");
        REGEX_CLASS = Pattern.compile("CLASS=\"((?:.|\f)+?)\"");
        REGEX_START_DATE = Pattern.compile("START-DATE=\"((?:.|\f)+?)\"");
        REGEX_CUE = Pattern.compile("CUE=\"((?:.|\f)+?)\"");
        REGEX_END_DATE = Pattern.compile("END-DATE=\"((?:.|\f)+?)\"");
        REGEX_PLANNED_DURATION = Pattern.compile("PLANNED-DURATION=([\\d\\.]+)\\b");
        REGEX_END_ON_NEXT = compileBooleanAttrPattern("END-ON-NEXT");
        REGEX_ASSET_URI = Pattern.compile("X-ASSET-URI=\"((?:.|\f)+?)\"");
        REGEX_ASSET_LIST_URI = Pattern.compile("X-ASSET-LIST=\"((?:.|\f)+?)\"");
        REGEX_RESUME_OFFSET = Pattern.compile("X-RESUME-OFFSET=(-?[\\d\\.]+)\\b");
        REGEX_PLAYOUT_LIMIT = Pattern.compile("X-PLAYOUT-LIMIT=([\\d\\.]+)\\b");
        REGEX_SNAP = Pattern.compile("X-SNAP=\"((?:.|\f)+?)\"");
        REGEX_RESTRICT = Pattern.compile("X-RESTRICT=\"((?:.|\f)+?)\"");
        REGEX_CONTENT_MAY_VARY = Pattern.compile("X-CONTENT-MAY-VARY=\"((?:.|\f)+?)\"");
        REGEX_TIMELINE_OCCUPIES = Pattern.compile("X-TIMELINE-OCCUPIES=\"((?:.|\f)+?)\"");
        REGEX_TIMELINE_STYLE = Pattern.compile("X-TIMELINE-STYLE=\"((?:.|\f)+?)\"");
        REGEX_VARIABLE_REFERENCE = Pattern.compile("\\{\\$([a-zA-Z0-9\\-_]+)\\}");
        REGEX_CLIENT_DEFINED_ATTRIBUTE_PREFIX = Pattern.compile("\\b(X-[A-Z0-9-]+)=");
    }

    public HlsPlaylistParser() {
        this(HlsMultivariantPlaylist.EMPTY, null);
    }

    public HlsPlaylistParser(HlsMultivariantPlaylist r1, HlsMediaPlaylist r2) {
        this.multivariantPlaylist = r1;
        this.previousMediaPlaylist = r2;
    }

    private static boolean checkPlaylistHeader(BufferedReader r4) {
        int r0 = r4.read();
        if (r0 == 239) goto L5;
    L11:
        int r02 = skipIgnorableWhitespace(r4, true, r0);
        int r1 = 0;
    L13:
        if (r1 >= 7) goto L19;
        if (r02 != "#EXTM3U".charAt(r1)) goto L16;
        r02 = r4.read();
        r1 = r1 + 1;
        goto L13
    L16:
        return false;
    L19:
        return Util.isLinebreak(skipIgnorableWhitespace(r4, false, r02));
    L5:
        if (r4.read() == 187) goto L7;
    L10:
        return false;
    L7:
        if (r4.read() != 191) goto L10;
        r0 = r4.read();
        goto L11
    }

    private static Pattern compileBooleanAttrPattern(String r1) {
        return Pattern.compile(r1 + "=(NO|YES)");
    }

    private static DrmInitData getPlaylistProtectionSchemes(String r4, DrmInitData.SchemeData[] r5) {
        DrmInitData.SchemeData[] r0 = new DrmInitData.SchemeData[r5.length];
        int r1 = 0;
    L4:
        if (r1 >= r5.length) goto L7;
        r0[r1] = r5[r1].copyWithData(null);
        r1 = r1 + 1;
        goto L4
    L7:
        return new DrmInitData(r4, r0);
    }

    private static String getSegmentEncryptionIV(long r0, String r2, String r3) {
        if (r2 != null) goto L5;
        return null;
    L5:
        if (r3 == null) goto L8;
        return r3;
    L8:
        return Long.toHexString(r0);
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithAudioGroup(ArrayList<HlsMultivariantPlaylist.Variant> r3, String r4) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L9;
        HlsMultivariantPlaylist.Variant r1 = r3.get(r0);
        if (r4.equals(r1.audioGroupId) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithSubtitleGroup(ArrayList<HlsMultivariantPlaylist.Variant> r3, String r4) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L9;
        HlsMultivariantPlaylist.Variant r1 = r3.get(r0);
        if (r4.equals(r1.subtitleGroupId) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithVideoGroup(ArrayList<HlsMultivariantPlaylist.Variant> r3, String r4) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L9;
        HlsMultivariantPlaylist.Variant r1 = r3.get(r0);
        if (r4.equals(r1.videoGroupId) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    private static boolean isDolbyVisionFormat(String r1, String r2, String r3, String r4) {
        if (MimeTypes.isDolbyVisionCodec(r2, r3) == true) goto L6;
        return false;
    L6:
        if (r3 != null) goto L8;
        return true;
    L8:
        if (r1 == null) goto L25;
        if (r4 == null) goto L25;
        if (r1.equals("PQ") == false) goto L16;
        if (r4.equals("db1p") == true) goto L16;
    L23:
        return false;
    L16:
        if (r1.equals("SDR") == false) goto L20;
        if (r4.equals("db2g") == false) goto L23;
    L20:
        if (r1.equals("HLG") == true) goto L22;
    L24:
        return true;
    L22:
        if (r4.startsWith("db4") == true) goto L24;
    L25:
        return false;
    }

    private static HlsMediaPlaylist.ClientDefinedAttribute parseClientDefinedAttribute(String r4, String r5, Map<String, String> r6) {
        String r0 = r5 + "=";
        int r1 = r4.indexOf(r0) + r0.length();
        if (r4.length() != (r1 + 1)) goto L5;
        int r02 = 1;
    L6:
        String r03 = r4.substring(r1, r02 + r1);
        if (r03.startsWith("\"") == false) goto L11;
        return new HlsMediaPlaylist.ClientDefinedAttribute(r5, parseStringAttr(r4, Pattern.compile(r5 + "=\"((?:.|\f)+?)\""), r6), 0);
    L11:
        if (r03.equals("0x") == true) goto L18;
        if (r03.equals("0X") == true) goto L18;
        return new HlsMediaPlaylist.ClientDefinedAttribute(r5, parseDoubleAttr(r4, Pattern.compile(r5 + "=([\\d\\.]+)\\b")));
    L18:
        return new HlsMediaPlaylist.ClientDefinedAttribute(r5, parseStringAttr(r4, Pattern.compile(r5 + "=(0[xX][A-F0-9]+)"), r6), 1);
    L5:
        r02 = 2;
        goto L6
    }

    private static double parseDoubleAttr(String r1, Pattern r2) {
        return Double.parseDouble(parseStringAttr(r1, r2, Collections.EMPTY_MAP));
    }

    private static DrmInitData.SchemeData parseDrmSchemeData(String r6, String r7, Map<String, String> r8) {
        String r0 = parseOptionalStringAttr(r6, REGEX_KEYFORMATVERSIONS, "1", r8);
        if ("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed".equals(r7) == false) goto L7;
        String r62 = parseStringAttr(r6, REGEX_URI, r8);
        return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "video/mp4", Base64.decode(r62.substring(r62.indexOf(44)), 0));
    L7:
        if ("com.widevine".equals(r7) == false) goto L11;
        return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "hls", Util.getUtf8Bytes(r6));
    L11:
        if ("com.microsoft.playready".equals(r7) == true) goto L13;
        return null;
    L13:
        if ("1".equals(r0) == false) goto L18;
        String r63 = parseStringAttr(r6, REGEX_URI, r8);
        byte[] r64 = Base64.decode(r63.substring(r63.indexOf(44)), 0);
        UUID r72 = C.PLAYREADY_UUID;
        return new DrmInitData.SchemeData(r72, "video/mp4", PsshAtomUtil.buildPsshAtom(r72, r64));
    L18:
        return null;
    }

    private static String parseEncryptionScheme(String r1) {
        if ("SAMPLE-AES-CENC".equals(r1) == false) goto L5;
        return "cenc";
    L5:
        if ("SAMPLE-AES-CTR".equals(r1) == true) goto L11;
        return "cbcs";
    L11:
        return "cenc";
    }

    private static int parseIntAttr(String r1, Pattern r2) {
        return Integer.parseInt(parseStringAttr(r1, r2, Collections.EMPTY_MAP));
    }

    private static long parseLongAttr(String r1, Pattern r2) {
        return Long.parseLong(parseStringAttr(r1, r2, Collections.EMPTY_MAP));
    }

    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:96)
        */
    private static androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist parseMediaPlaylist(androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist r91, androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist r92, androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser.LineIterator r93, java.lang.String r94) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x006c: CONST (r15v1 ?? I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (0 ??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[DECLARE_VAR] in method: androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser.parseMediaPlaylist(androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist, androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist, androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser$LineIterator, java.lang.String):androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist, file: classes4.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            Method dump skipped, instructions count: 2954
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser.parseMediaPlaylist(androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist, androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist, androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser$LineIterator, java.lang.String):androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist");
    }

    private static HlsMultivariantPlaylist parseMultivariantPlaylist(LineIterator r39, String r40) {
        String r1 = r40;
        HashMap r0 = new HashMap();
        HashMap r11 = new HashMap();
        ArrayList r2 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        ArrayList r6 = new ArrayList();
        ArrayList r7 = new ArrayList();
        ArrayList r3 = new ArrayList();
        ArrayList r12 = new ArrayList();
        ArrayList r8 = new ArrayList();
        boolean r10 = false;
        boolean r13 = false;
    L3:
        ArrayList r17 = r7;
        if (r39.hasNext() == false) goto L79;
        String r14 = r39.next();
        if (r14.startsWith("#EXT") == false) goto L8;
        r8.add(r14);
    L8:
        boolean r72 = r14.startsWith("#EXT-X-I-FRAME-STREAM-INF");
        if (r14.startsWith("#EXT-X-DEFINE") == false) goto L12;
        r11.put(parseStringAttr(r14, REGEX_NAME, r11), parseStringAttr(r14, REGEX_VALUE, r11));
    L26:
        ArrayList r22 = r2;
        HashMap r23 = r0;
        ArrayList r02 = r22;
        ArrayList r36 = r3;
        ArrayList r35 = r4;
        ArrayList r34 = r5;
        ArrayList r33 = r6;
        ArrayList r222 = r8;
        ArrayList r31 = r12;
    L76:
        HashMap r32 = r23;
        r2 = r02;
        r0 = r32;
        r7 = r17;
        r8 = r222;
        r12 = r31;
        r6 = r33;
        r5 = r34;
        r4 = r35;
        r3 = r36;
        goto L3
    L12:
        if (r14.equals("#EXT-X-INDEPENDENT-SEGMENTS") == false) goto L15;
        ArrayList r132 = r2;
        r23 = r0;
        r02 = r132;
        r36 = r3;
        r35 = r4;
        r34 = r5;
        r33 = r6;
        r222 = r8;
        r31 = r12;
        r13 = true;
        goto L76
    L15:
        if (r14.startsWith("#EXT-X-MEDIA") == false) goto L18;
        r3.add(r14);
        goto L26
    L18:
        if (r14.startsWith("#EXT-X-SESSION-KEY") == false) goto L23;
        DrmInitData.SchemeData r73 = parseDrmSchemeData(r14, parseOptionalStringAttr(r14, REGEX_KEYFORMAT, "identity", r11), r11);
        if (r73 == null) goto L26;
        r12.add(new DrmInitData(parseEncryptionScheme(parseStringAttr(r14, REGEX_METHOD, r11)), new DrmInitData.SchemeData[]{r73}));
        goto L26
    L23:
        if (r14.startsWith("#EXT-X-STREAM-INF") == true) goto L27;
        if (r72 == false) goto L26;
    L27:
        boolean r102 = r10 | r14.contains("CLOSED-CAPTIONS=NONE");
        if (r72 == false) goto L31;
        int r74 = 16384;
    L30:
        r222 = r8;
        int r82 = parseIntAttr(r14, REGEX_BANDWIDTH);
        r31 = r12;
        int r103 = parseOptionalIntAttr(r14, REGEX_AVERAGE_BANDWIDTH, -1);
        String r122 = parseOptionalStringAttr(r14, REGEX_VIDEO_RANGE, r11);
        boolean r322 = r13;
        String r133 = parseOptionalStringAttr(r14, REGEX_CODECS, r11);
        r33 = r6;
        String r62 = parseOptionalStringAttr(r14, REGEX_SUPPLEMENTAL_CODECS, r11);
        r34 = r5;
        if (r62 == null) goto L39;
        String[] r63 = Util.split(Util.splitAtFirst(r62, ",")[0], "/");
        String r9 = r63[0];
        if (r63.length <= 1) goto L37;
        String r92 = r63[1];
        r36 = r3;
        r35 = r4;
        String r64 = r9;
        int r42 = 2;
    L40:
        String r37 = Util.getCodecsOfType(r133, r42);
        if (isDolbyVisionFormat(r122, r37, r64, r92) == false) goto L49;
        if (r64 != null) goto L45;
        r64 = r37;
    L45:
        String r38 = Util.getCodecsWithoutType(r133, r42);
        if (r38 == null) goto L48;
        r133 = r64 + "," + r38;
        goto L49
    L48:
        r133 = r64;
    L49:
        String r310 = parseOptionalStringAttr(r14, REGEX_MIME, r11);
        boolean r43 = TextUtils.equals(parseOptionalStringAttr(r14, REGEX_CACHED, r11), "true");
        String r52 = parseOptionalStringAttr(r14, REGEX_DOC_ID, r11);
        String r65 = parseOptionalStringAttr(r14, REGEX_DOC_FILENAME, r11);
        String r93 = parseOptionalStringAttr(r14, REGEX_ACCOUNT, r11);
        String r123 = parseOptionalStringAttr(r14, REGEX_RESOLUTION, r11);
        if (r123 == null) goto L59;
        HashMap r372 = r0;
        String[] r03 = Util.split(r123, "x");
        int r124 = Integer.parseInt(r03[0]);
        int r04 = Integer.parseInt(r03[1]);
        if (r124 <= 0) goto L56;
        if (r04 <= 0) goto L56;
        int r19 = r124;
    L57:
        int r125 = r04;
        int r05 = r19;
    L58:
        ArrayList r382 = r2;
        String r24 = parseOptionalStringAttr(r14, REGEX_FRAME_RATE, r11);
        if (r24 == null) goto L64;
        float r25 = Float.parseFloat(r24);
    L65:
        String r26 = parseOptionalStringAttr(r14, REGEX_VIDEO, r11);
        String r27 = parseOptionalStringAttr(r14, REGEX_AUDIO, r11);
        String r28 = parseOptionalStringAttr(r14, REGEX_SUBTITLES, r11);
        String r29 = parseOptionalStringAttr(r14, REGEX_CLOSED_CAPTIONS, r11);
        if (r72 == false) goto L70;
        Uri r94 = UriUtil.resolveToUri(r1, parseStringAttr(r14, REGEX_URI, r11));
    L68:
        Uri r242 = r94;
        HlsMultivariantPlaylist.Variant r232 = new HlsMultivariantPlaylist.Variant(r242, new Format.Builder().setId(r382.size()).setContainerMimeType("application/x-mpegURL").setCodecs(r133).setSampleMimeType(r310).setAverageBitrate(r103).setPeakBitrate(r82).setWidth(r05).setHeight(r125).setFrameRate(r25).setRoleFlags(r74).setCached(r43).setDocumentId(r52).setDocumentFilename(r65).setCurrentAccount(r93).build(), r26, r27, r28, r29);
        r02 = r382;
        r02.add(r232);
        r23 = r372;
        ArrayList r311 = (ArrayList) r23.get(r242);
        if (r311 != null) goto L75;
        r311 = new ArrayList();
        r23.put(r242, r311);
    L75:
        r311.add(new HlsTrackMetadataEntry.VariantInfo(r103, r82, r26, r27, r28, r29));
        r10 = r102;
        r13 = r322;
        goto L76
    L70:
        if (r39.hasNext() == false) goto L78;
        r94 = UriUtil.resolveToUri(r1, replaceVariableReferences(r39.next(), r11));
        goto L68
    L78:
        throw ParserException.createForMalformedManifest("#EXT-X-STREAM-INF must be followed by another line", null);
    L64:
        r25 = -1.0f;
    L56:
        r04 = -1;
        r19 = -1;
        goto L57
    L59:
        r372 = r0;
        r05 = -1;
        r125 = -1;
        goto L58
    L37:
        r36 = r3;
        r35 = r4;
        r64 = r9;
        r42 = 2;
    L38:
        r92 = null;
        goto L40
    L39:
        r36 = r3;
        r35 = r4;
        r42 = 2;
        r64 = null;
        goto L38
    L31:
        r74 = 0;
        goto L30
    L79:
        ArrayList r223 = r2;
        HashMap r210 = r0;
        ArrayList r362 = r3;
        ArrayList r352 = r4;
        ArrayList r342 = r5;
        ArrayList r332 = r6;
        ArrayList r224 = r8;
        ArrayList r312 = r12;
        boolean r323 = r13;
        ArrayList r313 = new ArrayList();
        HashSet r44 = new HashSet();
        int r53 = 0;
    L81:
        if (r53 >= r223.size()) goto L91;
        HlsMultivariantPlaylist.Variant r66 = (HlsMultivariantPlaylist.Variant) r223.get(r53);
        if (r44.add(r66.url) == false) goto L90;
        if (r66.format.metadata != null) goto L87;
        boolean r75 = true;
    L88:
        Assertions.checkState(r75);
        r313.add(r66.copyWithFormat(r66.format.buildUpon().setMetadata(new Metadata(new Metadata.Entry[]{new HlsTrackMetadataEntry(null, null, (List) Assertions.checkNotNull((ArrayList) r210.get(r66.url)))})).build()));
        goto L90
    L87:
        r75 = false;
    L90:
        r53 = r53 + 1;
        goto L81
    L91:
        ArrayList r211 = null;
        Format r83 = null;
        int r45 = 0;
    L93:
        if (r45 >= r362.size()) goto L164;
        ArrayList r54 = r362;
        String r67 = (String) r54.get(r45);
        String r76 = parseStringAttr(r67, REGEX_GROUP_ID, r11);
        String r134 = parseStringAttr(r67, REGEX_NAME, r11);
        Format.Builder r142 = new Format.Builder();
        StringBuilder r126 = new StringBuilder();
        r126.append(r76);
        ArrayList r392 = r211;
        r126.append(":");
        r126.append(r134);
        Format.Builder r212 = r142.setId(r126.toString()).setLabel(r134).setContainerMimeType("application/x-mpegURL").setSelectionFlags(parseSelectionFlags(r67)).setRoleFlags(parseRoleFlags(r67, r11)).setLanguage(parseOptionalStringAttr(r67, REGEX_LANGUAGE, r11));
        String r127 = parseOptionalStringAttr(r67, REGEX_URI, r11);
        if (r127 != null) goto L97;
        Uri r128 = null;
    L98:
        ArrayList r21 = r313;
        Metadata r143 = new Metadata(new Metadata.Entry[]{new HlsTrackMetadataEntry(r76, r134, Collections.EMPTY_LIST)});
        String r15 = parseStringAttr(r67, REGEX_TYPE, r11);
        r15.getClass();
        switch(r15.hashCode()) {
            case -959297733: goto L114;
            case -333210994: goto L110;
            case 62628790: goto L106;
            case 81665115: goto L102;
            default: goto L100;
        };
    L100:
        char r16 = 65535;
    L117:
        switch(r16) {
            case 0: goto L152;
            case 1: goto L144;
            case 2: goto L125;
            case 3: goto L119;
            default: goto L118;
        };
    L118:
        ArrayList r68 = r332;
        ArrayList r314 = r342;
    L162:
        r211 = r392;
    L163:
        r45 = r45 + 1;
        r1 = r40;
        r342 = r314;
        r362 = r54;
        r332 = r68;
        r313 = r21;
        goto L93
    L119:
        HlsMultivariantPlaylist.Variant r18 = getVariantWithVideoGroup(r223, r76);
        if (r18 == null) goto L122;
        Format r110 = r18.format;
        String r315 = Util.getCodecsOfType(r110.codecs, 2);
        r212.setCodecs(r315).setSampleMimeType(MimeTypes.getMediaMimeType(r315)).setWidth(r110.width).setHeight(r110.height).setFrameRate(r110.frameRate);
    L122:
        if (r128 == null) goto L118;
        r212.setMetadata(r143);
        r352.add(new HlsMultivariantPlaylist.Rendition(r128, r212.build(), r76, r134));
        goto L118
    L125:
        ArrayList r316 = r352;
        HlsMultivariantPlaylist.Variant r111 = getVariantWithAudioGroup(r223, r76);
        if (r111 == null) goto L128;
        r352 = r316;
        HlsMultivariantPlaylist.Variant r233 = r111;
        String r317 = Util.getCodecsOfType(r111.format.codecs, 1);
        r212.setCodecs(r317);
        String r318 = MimeTypes.getMediaMimeType(r317);
    L129:
        String r112 = parseOptionalStringAttr(r67, REGEX_CHANNELS, r11);
        if (r112 == null) goto L136;
        r212.setChannelCount(Integer.parseInt(Util.splitAtFirst(r112, "/")[0]));
        if ("audio/eac3".equals(r318) == false) goto L136;
        if (r112.endsWith("/JOC") == false) goto L136;
        r212.setCodecs("ec+3");
        r318 = "audio/eac3-joc";
    L136:
        r212.setSampleMimeType(r318);
        if (r128 == null) goto L140;
        r212.setMetadata(r143);
        r314 = r342;
        r314.add(new HlsMultivariantPlaylist.Rendition(r128, r212.build(), r76, r134));
    L139:
        r68 = r332;
        goto L162
    L140:
        r314 = r342;
        if (r233 == null) goto L139;
        Format r113 = r212.build();
        r211 = r392;
        r83 = r113;
    L143:
        r68 = r332;
        goto L163
    L128:
        r233 = r111;
        r352 = r316;
        r318 = null;
        goto L129
    L144:
        r314 = r342;
        String r114 = parseStringAttr(r67, REGEX_INSTREAM_ID, r11);
        if (r114.startsWith("CC") == false) goto L147;
        int r115 = Integer.parseInt(r114.substring(2));
        String r77 = "application/cea-608";
    L148:
        if (r392 != null) goto L150;
        ArrayList r129 = new ArrayList();
    L151:
        r212.setSampleMimeType(r77).setAccessibilityChannel(r115);
        r129.add(r212.build());
        r211 = r129;
        goto L143
    L150:
        r129 = r392;
        goto L151
    L147:
        r115 = Integer.parseInt(r114.substring(7));
        r77 = "application/cea-708";
        goto L148
    L152:
        r314 = r342;
        HlsMultivariantPlaylist.Variant r116 = getVariantWithSubtitleGroup(r223, r76);
        if (r116 == null) goto L155;
        String r117 = Util.getCodecsOfType(r116.format.codecs, 3);
        r212.setCodecs(r117);
        String r118 = MimeTypes.getMediaMimeType(r117);
    L156:
        if (r118 != null) goto L158;
        r118 = "text/vtt";
    L158:
        r212.setSampleMimeType(r118).setMetadata(r143);
        if (r128 == null) goto L161;
        r68 = r332;
        r68.add(new HlsMultivariantPlaylist.Rendition(r128, r212.build(), r76, r134));
        goto L162
    L161:
        r68 = r332;
        Log.w("HlsPlaylistParser", "EXT-X-MEDIA tag with missing mandatory URI attribute: skipping");
        goto L162
    L155:
        r118 = null;
        goto L156
    L102:
        if (r15.equals("VIDEO") == false) goto L100;
        r16 = 3;
        goto L117
    L106:
        if (r15.equals("AUDIO") == false) goto L100;
        r16 = 2;
        goto L117
    L110:
        if (r15.equals("CLOSED-CAPTIONS") == false) goto L100;
        r16 = 1;
        goto L117
    L114:
        if (r15.equals("SUBTITLES") == false) goto L100;
        r16 = 0;
        goto L117
    L97:
        r128 = UriUtil.resolveToUri(r1, r127);
        goto L98
    L164:
        ArrayList r393 = r211;
        ArrayList r213 = r313;
        ArrayList r69 = r332;
        ArrayList r319 = r342;
        if (r10 == false) goto L167;
        List r95 = Collections.EMPTY_LIST;
    L169:
        return new HlsMultivariantPlaylist(r40, r224, r213, r352, r319, r69, r17, r83, r95, r323, r11, r312);
    L167:
        r95 = r393;
        goto L169
    }

    private static boolean parseOptionalBooleanAttribute(String r0, Pattern r1, boolean r2) {
        Matcher r02 = r1.matcher(r0);
        if (r02.find() == true) goto L5;
        return r2;
    L5:
        return "YES".equals(r02.group(1));
    }

    private static double parseOptionalDoubleAttr(String r0, Pattern r1, double r2) {
        Matcher r02 = r1.matcher(r0);
        if (r02.find() == true) goto L5;
        return r2;
    L5:
        return Double.parseDouble((String) Assertions.checkNotNull(r02.group(1)));
    }

    private static int parseOptionalIntAttr(String r0, Pattern r1, int r2) {
        Matcher r02 = r1.matcher(r0);
        if (r02.find() == true) goto L5;
        return r2;
    L5:
        return Integer.parseInt((String) Assertions.checkNotNull(r02.group(1)));
    }

    private static long parseOptionalLongAttr(String r0, Pattern r1, long r2) {
        Matcher r02 = r1.matcher(r0);
        if (r02.find() == true) goto L5;
        return r2;
    L5:
        return Long.parseLong((String) Assertions.checkNotNull(r02.group(1)));
    }

    private static String parseOptionalStringAttr(String r0, Pattern r1, String r2, Map<String, String> r3) {
        Matcher r02 = r1.matcher(r0);
        if (r02.find() == false) goto L6;
        r2 = (String) Assertions.checkNotNull(r02.group(1));
    L6:
        if (r3.isEmpty() == true) goto L11;
        if (r2 == null) goto L11;
        return replaceVariableReferences(r2, r3);
    L11:
        return r2;
    }

    private static String parseOptionalStringAttr(String r1, Pattern r2, Map<String, String> r3) {
        return parseOptionalStringAttr(r1, r2, null, r3);
    }

    private static int parseRoleFlags(String r1, Map<String, String> r2) {
        String r12 = parseOptionalStringAttr(r1, REGEX_CHARACTERISTICS, r2);
        int r0 = 0;
        if (TextUtils.isEmpty(r12) == false) goto L5;
        return 0;
    L5:
        String[] r13 = Util.split(r12, ",");
        if (Util.contains(r13, "public.accessibility.describes-video") == false) goto L9;
        r0 = 512;
    L9:
        if (Util.contains(r13, "public.accessibility.transcribes-spoken-dialog") == false) goto L12;
        r0 = r0 | 4096;
    L12:
        if (Util.contains(r13, "public.accessibility.describes-music-and-sound") == false) goto L15;
        r0 = r0 | 1024;
    L15:
        if (Util.contains(r13, "public.easy-to-read") == true) goto L17;
        return r0;
    L17:
        return r0 | 8192;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    private static int parseSelectionFlags(String r3) {
        boolean r0 = parseOptionalBooleanAttribute(r3, REGEX_DEFAULT, false);
        ?? r02 = r0;
        if (parseOptionalBooleanAttribute(r3, REGEX_FORCED, false) == false) goto L6;
        r02 = (r0 ? 1 : 0) | 2;
    L6:
        if (parseOptionalBooleanAttribute(r3, REGEX_AUTOSELECT, false) == true) goto L8;
        return r02;
    L8:
        return r02 | 4;
    }

    private static HlsMediaPlaylist.ServerControl parseServerControl(String r19) {
        double r4 = parseOptionalDoubleAttr(r19, REGEX_CAN_SKIP_UNTIL, -9.223372036854776E18d);
        long r8 = -9223372036854775807L;
        if (r4 != (-9.223372036854776E18d)) goto L5;
        long r11 = -9223372036854775807L;
    L6:
        boolean r13 = parseOptionalBooleanAttribute(r19, REGEX_CAN_SKIP_DATE_RANGES, false);
        double r14 = parseOptionalDoubleAttr(r19, REGEX_HOLD_BACK, -9.223372036854776E18d);
        if (r14 != (-9.223372036854776E18d)) goto L9;
        long r142 = -9223372036854775807L;
    L10:
        double r16 = parseOptionalDoubleAttr(r19, REGEX_PART_HOLD_BACK, -9.223372036854776E18d);
        if (r16 == (-9.223372036854776E18d)) goto L15;
        r8 = (long) (r16 * 1000000.0d);
    L15:
        return new HlsMediaPlaylist.ServerControl(r11, r13, r142, r8, parseOptionalBooleanAttribute(r19, REGEX_CAN_BLOCK_RELOAD, false));
    L9:
        r142 = (long) (r14 * 1000000.0d);
        goto L10
    L5:
        r11 = (long) (r4 * 1000000.0d);
        goto L6
    }

    private static String parseStringAttr(String r1, Pattern r2, Map<String, String> r3) {
        String r32 = parseOptionalStringAttr(r1, r2, r3);
        if (r32 == null) goto L6;
        return r32;
    L6:
        throw ParserException.createForMalformedManifest("Couldn't match " + r2.pattern() + " in " + r1, null);
    }

    private static long parseTimeSecondsToUs(String r2, Pattern r3) {
        return new BigDecimal(parseStringAttr(r2, r3, Collections.EMPTY_MAP)).multiply(new BigDecimal(1000000)).longValue();
    }

    private static String replaceVariableReferences(String r3, Map<String, String> r4) {
        Matcher r32 = REGEX_VARIABLE_REFERENCE.matcher(r3);
        StringBuffer r0 = new StringBuffer();
    L4:
        if (r32.find() == false) goto L8;
        String r1 = r32.group(1);
        if (r4.containsKey(r1) == false) goto L4;
        r32.appendReplacement(r0, Matcher.quoteReplacement(r4.get(r1)));
        goto L4
    L8:
        r32.appendTail(r0);
        return r0.toString();
    }

    private static int skipIgnorableWhitespace(BufferedReader r1, boolean r2, int r3) {
    L3:
        if (r3 == (-1)) goto L10;
        if (Character.isWhitespace(r3) == false) goto L10;
        if (r2 == true) goto L9;
        if (Util.isLinebreak(r3) == true) goto L10;
    L9:
        r3 = r1.read();
    L10:
        return r3;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public HlsPlaylist parse(Uri r5, InputStream r6) {
        BufferedReader r0 = new BufferedReader(new InputStreamReader(r6));
        ArrayDeque r62 = new ArrayDeque();
    L15:
        th = move-exception;
        Util.closeQuietly(r0);
        throw th;
    L4:
        if (checkPlaylistHeader(r0) == false) goto L41;
    L5:
        String r1 = r0.readLine();     // Catch: Throwable -> L15
        if (r1 == null) goto L38;
        String r12 = r1.trim();     // Catch: Throwable -> L15
        if (r12.isEmpty() == true) goto L5;
        if (r12.startsWith("#EXT-X-STREAM-INF") == true) goto L12;
        if (r12.startsWith("#EXT-X-TARGETDURATION") == true) goto L35;
        if (r12.startsWith("#EXT-X-MEDIA-SEQUENCE") == true) goto L35;
        if (r12.startsWith("#EXTINF") == true) goto L35;
        if (r12.startsWith("#EXT-X-KEY") == true) goto L35;
        if (r12.startsWith("#EXT-X-BYTERANGE") == true) goto L35;
        if (r12.equals("#EXT-X-DISCONTINUITY") == true) goto L35;
        if (r12.equals("#EXT-X-DISCONTINUITY-SEQUENCE") == true) goto L35;
        if (r12.equals("#EXT-X-ENDLIST") == true) goto L35;
        r62.add(r12);     // Catch: Throwable -> L15
    L35:
        r62.add(r12);     // Catch: Throwable -> L15
        HlsMediaPlaylist r4 = parseMediaPlaylist(this.multivariantPlaylist, this.previousMediaPlaylist, new LineIterator(r62, r0), r5.toString());     // Catch: Throwable -> L15
        Util.closeQuietly(r0);
        return r4;
    L12:
        r62.add(r12);     // Catch: Throwable -> L15
        HlsMultivariantPlaylist r42 = parseMultivariantPlaylist(new LineIterator(r62, r0), r5.toString());     // Catch: Throwable -> L15
        Util.closeQuietly(r0);
        return r42;
    L38:
        Util.closeQuietly(r0);
        throw ParserException.createForMalformedManifest("Failed to parse the playlist, could not identify any tags.", null);
    L41:
        throw ParserException.createForMalformedManifest("Input does not start with the #EXTM3U header.", null);     // Catch: Throwable -> L15
    }

    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public /* bridge */ /* synthetic */ HlsPlaylist parse(Uri r1, InputStream r2) {
        return parse(r1, r2);
    }
}
