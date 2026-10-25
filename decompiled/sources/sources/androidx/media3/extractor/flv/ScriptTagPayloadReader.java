package androidx.media3.extractor.flv;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.DiscardingTrackOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
final class ScriptTagPayloadReader extends TagPayloadReader {
    private long durationUs;
    private long[] keyFrameTagPositions;
    private long[] keyFrameTimesUs;

    public ScriptTagPayloadReader() {
        super(new DiscardingTrackOutput());
        this.durationUs = -9223372036854775807L;
        this.keyFrameTimesUs = new long[0];
        this.keyFrameTagPositions = new long[0];
    }

    private static Boolean readAmfBoolean(ParsableByteArray r1) {
        boolean r0 = true;
        if (r1.readUnsignedByte() == 1) goto L7;
        r0 = false;
    L7:
        return Boolean.valueOf(r0);
    }

    private static Object readAmfData(ParsableByteArray r1, int r2) {
        if (r2 == 0) goto L30;
        if (r2 == 1) goto L28;
        if (r2 == 2) goto L26;
        if (r2 == 3) goto L24;
        if (r2 == 8) goto L22;
        if (r2 == 10) goto L20;
        if (r2 == 11) goto L18;
        return null;
    L18:
        return readAmfDate(r1);
    L20:
        return readAmfStrictArray(r1);
    L22:
        return readAmfEcmaArray(r1);
    L24:
        return readAmfObject(r1);
    L26:
        return readAmfString(r1);
    L28:
        return readAmfBoolean(r1);
    L30:
        return readAmfDouble(r1);
    }

    private static Date readAmfDate(ParsableByteArray r3) {
        Date r0 = new Date((long) readAmfDouble(r3).doubleValue());
        r3.skipBytes(2);
        return r0;
    }

    private static Double readAmfDouble(ParsableByteArray r2) {
        return Double.valueOf(Double.longBitsToDouble(r2.readLong()));
    }

    private static HashMap<String, Object> readAmfEcmaArray(ParsableByteArray r5) {
        int r0 = r5.readUnsignedIntToInt();
        HashMap<String, Object> r1 = new HashMap(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        String r3 = readAmfString(r5);
        Object r4 = readAmfData(r5, readAmfType(r5));
        if (r4 == null) goto L7;
        r1.put(r3, r4);
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        return r1;
    }

    private static HashMap<String, Object> readAmfObject(ParsableByteArray r4) {
        HashMap<String, Object> r0 = new HashMap();
    L3:
        String r1 = readAmfString(r4);
        int r2 = readAmfType(r4);
        if (r2 == 9) goto L5;
        Object r22 = readAmfData(r4, r2);
        if (r22 == null) goto L3;
        r0.put(r1, r22);
        goto L3
    L5:
        return r0;
    }

    private static ArrayList<Object> readAmfStrictArray(ParsableByteArray r4) {
        int r0 = r4.readUnsignedIntToInt();
        ArrayList<Object> r1 = new ArrayList(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        Object r3 = readAmfData(r4, readAmfType(r4));
        if (r3 == null) goto L7;
        r1.add(r3);
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        return r1;
    }

    private static String readAmfString(ParsableByteArray r3) {
        int r0 = r3.readUnsignedShort();
        int r1 = r3.getPosition();
        r3.skipBytes(r0);
        return new String(r3.getData(), r1, r0);
    }

    private static int readAmfType(ParsableByteArray r0) {
        return r0.readUnsignedByte();
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public long[] getKeyFrameTagPositions() {
        return this.keyFrameTagPositions;
    }

    public long[] getKeyFrameTimesUs() {
        return this.keyFrameTimesUs;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parseHeader(ParsableByteArray r1) {
        return true;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parsePayload(ParsableByteArray r10, long r11) {
        if (readAmfType(r10) == 2) goto L6;
        return false;
    L6:
        if ("onMetaData".equals(readAmfString(r10)) == true) goto L9;
        return false;
    L9:
        if (r10.bytesLeft() != 0) goto L12;
        return false;
    L12:
        if (readAmfType(r10) == 8) goto L14;
        return false;
    L14:
        HashMap<String, Object> r102 = readAmfEcmaArray(r10);
        Object r112 = r102.get("duration");
        if ((r112 instanceof Double) == false) goto L19;
        double r113 = ((Double) r112).doubleValue();
        if (r113 <= 0.0d) goto L19;
        this.durationUs = (long) (r113 * 1000000.0d);
    L19:
        Object r103 = r102.get("keyframes");
        if ((r103 instanceof Map) == false) goto L33;
        Map r104 = (Map) r103;
        Object r114 = r104.get("filepositions");
        Object r105 = r104.get("times");
        if ((r114 instanceof List) == false) goto L33;
        if ((r105 instanceof List) == false) goto L33;
        List r115 = (List) r114;
        List r106 = (List) r105;
        int r12 = r106.size();
        this.keyFrameTimesUs = new long[r12];
        this.keyFrameTagPositions = new long[r12];
        int r3 = 0;
    L26:
        if (r3 >= r12) goto L33;
        Object r4 = r115.get(r3);
        Object r5 = r106.get(r3);
        if ((r5 instanceof Double) == false) goto L32;
        if ((r4 instanceof Double) == false) goto L32;
        this.keyFrameTimesUs[r3] = (long) (((Double) r5).doubleValue() * 1000000.0d);
        this.keyFrameTagPositions[r3] = ((Double) r4).longValue();
        r3 = r3 + 1;
    L32:
        this.keyFrameTimesUs = new long[0];
        this.keyFrameTagPositions = new long[0];
    L33:
        return false;
    }
}
