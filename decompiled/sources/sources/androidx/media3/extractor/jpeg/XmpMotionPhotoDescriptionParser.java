package androidx.media3.extractor.jpeg;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.extractor.jpeg.MotionPhotoDescription;
import com.google.common.collect.ImmutableList;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes4.dex */
public abstract class XmpMotionPhotoDescriptionParser {
    private static final String[] DESCRIPTION_MICRO_VIDEO_OFFSET_ATTRIBUTE_NAMES = null;
    private static final String[] DESCRIPTION_MOTION_PHOTO_PRESENTATION_TIMESTAMP_ATTRIBUTE_NAMES = null;
    private static final String[] MOTION_PHOTO_ATTRIBUTE_NAMES = null;

    static {
        MOTION_PHOTO_ATTRIBUTE_NAMES = new String[]{"Camera:MotionPhoto", "GCamera:MotionPhoto", "Camera:MicroVideo", "GCamera:MicroVideo"};
        DESCRIPTION_MOTION_PHOTO_PRESENTATION_TIMESTAMP_ATTRIBUTE_NAMES = new String[]{"Camera:MotionPhotoPresentationTimestampUs", "GCamera:MotionPhotoPresentationTimestampUs", "Camera:MicroVideoPresentationTimestampUs", "GCamera:MicroVideoPresentationTimestampUs"};
        DESCRIPTION_MICRO_VIDEO_OFFSET_ATTRIBUTE_NAMES = new String[]{"Camera:MicroVideoOffset", "GCamera:MicroVideoOffset"};
    }

    public static MotionPhotoDescription parse(String r1) {
        return parseInternal(r1);
    L4:
        Log.w("MotionPhotoXmpParser", "Ignoring unexpected XMP metadata");
        return null;
    }

    private static MotionPhotoDescription parseInternal(String r6) {
        XmlPullParser r0 = XmlPullParserFactory.newInstance().newPullParser();
        r0.setInput(new StringReader(r6));
        r0.next();
        if (XmlPullParserUtil.isStartTag(r0, "x:xmpmeta") == false) goto L25;
        ImmutableList<MotionPhotoDescription.ContainerItem> r1 = ImmutableList.of();
        long r3 = -9223372036854775807L;
    L5:
        r0.next();
        if (XmlPullParserUtil.isStartTag(r0, "rdf:Description") == false) goto L12;
        if (parseMotionPhotoFlagFromDescription(r0) == false) goto L9;
        r3 = parseMotionPhotoPresentationTimestampUsFromDescription(r0);
        r1 = parseMicroVideoOffsetFromDescription(r0);
    L18:
        if (XmlPullParserUtil.isEndTag(r0, "x:xmpmeta") == false) goto L5;
        if (r1.isEmpty() == false) goto L23;
        return null;
    L23:
        return new MotionPhotoDescription(r3, r1);
    L9:
        return null;
    L12:
        if (XmlPullParserUtil.isStartTag(r0, "Container:Directory") == false) goto L15;
        r1 = parseMotionPhotoV1Directory(r0, "Container", "Item");
        goto L18
    L15:
        if (XmlPullParserUtil.isStartTag(r0, "GContainer:Directory") == false) goto L18;
        r1 = parseMotionPhotoV1Directory(r0, "GContainer", "GContainerItem");
        goto L18
    L25:
        throw ParserException.createForMalformedContainer("Couldn't find xmp metadata", null);
    }

    private static ImmutableList<MotionPhotoDescription.ContainerItem> parseMicroVideoOffsetFromDescription(XmlPullParser r11) {
        String[] r0 = DESCRIPTION_MICRO_VIDEO_OFFSET_ATTRIBUTE_NAMES;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L10;
        String r3 = XmlPullParserUtil.getAttributeValue(r11, r0[r2]);
        if (r3 != null) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        long r7 = Long.parseLong(r3);
        return ImmutableList.of(new MotionPhotoDescription.ContainerItem("image/jpeg", "Primary", 0, 0), new MotionPhotoDescription.ContainerItem("video/mp4", "MotionPhoto", r7, 0));
    L10:
        return ImmutableList.of();
    }

    private static boolean parseMotionPhotoFlagFromDescription(XmlPullParser r5) {
        String[] r0 = MOTION_PHOTO_ATTRIBUTE_NAMES;
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L11;
        String r4 = XmlPullParserUtil.getAttributeValue(r5, r0[r3]);
        if (r4 != null) goto L7;
        r3 = r3 + 1;
        goto L3
    L7:
        if (Integer.parseInt(r4) != 1) goto L9;
        return true;
    L9:
        return false;
    L11:
        return false;
    }

    private static long parseMotionPhotoPresentationTimestampUsFromDescription(XmlPullParser r7) {
        String[] r0 = DESCRIPTION_MOTION_PHOTO_PRESENTATION_TIMESTAMP_ATTRIBUTE_NAMES;
        int r1 = r0.length;
        int r2 = 0;
    L4:
        if (r2 >= r1) goto L12;
        String r5 = XmlPullParserUtil.getAttributeValue(r7, r0[r2]);
        if (r5 != null) goto L7;
        r2 = r2 + 1;
        goto L4
    L7:
        long r02 = Long.parseLong(r5);
        if (r02 != (-1)) goto L10;
        return -9223372036854775807L;
    L10:
        return r02;
    L12:
        return -9223372036854775807L;
    }

    private static ImmutableList<MotionPhotoDescription.ContainerItem> parseMotionPhotoV1Directory(XmlPullParser r13, String r14, String r15) {
        ImmutableList.Builder r0 = ImmutableList.builder();
        String r1 = r14 + ":Item";
        String r142 = r14 + ":Directory";
    L3:
        r13.next();
        if (XmlPullParserUtil.isStartTag(r13, r1) == false) goto L19;
        String r7 = XmlPullParserUtil.getAttributeValue(r13, r15 + ":Mime");
        String r8 = XmlPullParserUtil.getAttributeValue(r13, r15 + ":Semantic");
        String r2 = XmlPullParserUtil.getAttributeValue(r13, r15 + ":Length");
        String r3 = XmlPullParserUtil.getAttributeValue(r13, r15 + ":Padding");
        if (r7 == null) goto L17;
        if (r8 == null) goto L17;
        long r4 = 0;
        if (r2 == null) goto L12;
        long r9 = Long.parseLong(r2);
    L13:
        if (r3 == null) goto L15;
        r4 = Long.parseLong(r3);
    L15:
        r0.add(new MotionPhotoDescription.ContainerItem(r7, r8, r9, r4));
        goto L19
    L12:
        r9 = 0;
    L17:
        return ImmutableList.of();
    L19:
        if (XmlPullParserUtil.isEndTag(r13, r142) == false) goto L3;
        return r0.build();
    }
}
