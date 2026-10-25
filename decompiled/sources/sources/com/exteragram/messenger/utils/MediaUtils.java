package com.exteragram.messenger.utils;

import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes4.dex */
public abstract class MediaUtils {
    private static final String[] GEO_TAGS = null;
    private static final String[] GEO_TAGS_API_24 = null;
    private static final String[] MOTION_PHOTO_XMP_MARKERS = null;

    static {
        GEO_TAGS = new String[]{"GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSAltitude", "GPSAltitudeRef", "GPSTimeStamp", "GPSDateStamp", "GPSProcessingMethod"};
        GEO_TAGS_API_24 = new String[]{"GPSAreaInformation", "GPSDOP", "GPSMeasureMode", "GPSSpeedRef", "GPSSpeed", "GPSStatus", "GPSDestLatitude", "GPSDestLatitudeRef", "GPSDestLongitude", "GPSDestLongitudeRef", "GPSDestBearing", "GPSDestBearingRef", "GPSDestDistance", "GPSDestDistanceRef"};
        MOTION_PHOTO_XMP_MARKERS = new String[]{"Camera:MotionPhoto", "GCamera:MotionPhoto", "Camera:MicroVideo", "GCamera:MicroVideo", "Camera:MotionPhotoPresentationTimestampUs", "GCamera:MotionPhotoPresentationTimestampUs", "Camera:MicroVideoPresentationTimestampUs", "GCamera:MicroVideoPresentationTimestampUs", "Camera:MicroVideoOffset", "GCamera:MicroVideoOffset", "Container:Directory", "GContainer:Directory"};
    }

    private static List<String> getApi24GeoTags() {
        return Arrays.asList(GEO_TAGS_API_24);
    }

    public static String getPhotoPlatform(JpegFingerprint r9) {
        if (r9 != null) goto L5;
    L53:
        return null;
    L5:
        if (r9.jfifUnits < 0) goto L53;
        if (r9.iccVersion == 1072) goto L10;
    L11:
        String r1 = r9.iccDescription;
        if (r1 != null) goto L14;
    L16:
        boolean r12 = false;
    L17:
        int r2 = r9.jfifUnits;
        if (r2 != 0) goto L35;
        if (r9.jfifDensityX == 1) goto L22;
        return null;
    L22:
        if (r12 == false) goto L25;
        return "Android";
    L25:
        if (r9.iccLength != 0) goto L33;
        int r92 = r9.quality;
        if (r92 != 72) goto L31;
        return "iOS";
    L31:
        if (r92 != 75) goto L33;
        return "macOS";
    L33:
        return null;
    L35:
        if (r2 != 1) goto L50;
        if (r9.jfifDensityX != 72) goto L50;
        if (r12 == false) goto L40;
        return "Android";
    L40:
        int r13 = r9.iccVersion;
        if (r13 != 576) goto L44;
        return "macOS";
    L44:
        if (r13 == 1088) goto L46;
    L48:
        return null;
    L46:
        if (r9.iccLength != 480) goto L48;
        return "Desktop";
    L50:
        if (r9.iccVersion != 576) goto L52;
        return "macOS";
    L52:
        return "Desktop";
    L14:
        if (r1.startsWith("Google/Skia/") == false) goto L16;
    L15:
        r12 = true;
        goto L17
    L10:
        if (r9.iccYear == 2016) goto L15;
        goto L11
    }

    public static boolean removeGeolocation(String r8, String r9) {
        File r0 = new File(r8);
        if (r0.exists() == true) goto L5;
        return false;
    L5:
        File r1 = new File(r9);
        if (AndroidUtilities.copyFile(r0, r1) == false) goto L20;
        ArrayList r82 = new ArrayList(Arrays.asList(GEO_TAGS));
        r82.addAll(getApi24GeoTags());
        ExifInterface r92 = new ExifInterface(r1.getAbsolutePath());
        int r02 = r82.size();
        boolean r3 = false;
        int r4 = 0;
    L9:
        if (r4 >= r02) goto L15;
        Object r6 = r82.get(r4);
        r4 = r4 + 1;
        String r62 = (String) r6;
        if (r92.getAttribute(r62) == null) goto L9;
        r92.setAttribute(r62, null);     // Catch: Exception -> L22
        r3 = true;
        goto L9
    L15:
        if (r3 == true) goto L23;
    L19:
        return false;
    L23:
        r92.saveAttributes();     // Catch: IOException -> L18
        return true;
    L18:
        r1.delete();
        goto L19
    L20:
        r1.delete();
        throw new IOException("failed to copy " + r8 + " to " + r9);
    }
}
