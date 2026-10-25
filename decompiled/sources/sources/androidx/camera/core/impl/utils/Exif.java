package androidx.camera.core.impl.utils;

import android.location.Location;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.exifinterface.media.ExifInterface;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class Exif {
    private static final List<String> ALL_EXIF_TAGS = null;
    private static final ThreadLocal<SimpleDateFormat> DATETIME_FORMAT = null;
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = null;
    private static final List<String> DO_NOT_COPY_EXIF_TAGS = null;
    private static final String TAG = "Exif";
    private static final ThreadLocal<SimpleDateFormat> TIME_FORMAT = null;
    private final ExifInterface mExifInterface;
    private boolean mRemoveTimestamp;

    /* renamed from: androidx.camera.core.impl.utils.Exif$1, reason: invalid class name */
    public class AnonymousClass1 extends ThreadLocal<SimpleDateFormat> {
        public AnonymousClass1() {
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ SimpleDateFormat initialValue() {
            return initialValue2();
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue, reason: avoid collision after fix types in other method */
        public SimpleDateFormat initialValue2() {
            return new SimpleDateFormat("yyyy:MM:dd", Locale.US);
        }
    }

    /* renamed from: androidx.camera.core.impl.utils.Exif$2, reason: invalid class name */
    public class AnonymousClass2 extends ThreadLocal<SimpleDateFormat> {
        public AnonymousClass2() {
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ SimpleDateFormat initialValue() {
            return initialValue2();
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue, reason: avoid collision after fix types in other method */
        public SimpleDateFormat initialValue2() {
            return new SimpleDateFormat("HH:mm:ss", Locale.US);
        }
    }

    /* renamed from: androidx.camera.core.impl.utils.Exif$3, reason: invalid class name */
    public class AnonymousClass3 extends ThreadLocal<SimpleDateFormat> {
        public AnonymousClass3() {
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ SimpleDateFormat initialValue() {
            return initialValue2();
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue, reason: avoid collision after fix types in other method */
        public SimpleDateFormat initialValue2() {
            return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
        }
    }

    public static final class Speed {

        public static final class Converter {
            final double mMph;

            public Converter(double r1) {
                this.mMph = r1;
            }

            public double toMetersPerSecond() {
                return this.mMph / 2.23694d;
            }
        }

        public static Converter fromKilometersPerHour(double r3) {
            return new Converter(r3 * 0.621371d);
        }

        public static Converter fromKnots(double r3) {
            return new Converter(r3 * 1.15078d);
        }

        public static Converter fromMilesPerHour(double r1) {
            return new Converter(r1);
        }
    }

    static {
        DATE_FORMAT = new AnonymousClass1();
        TIME_FORMAT = new AnonymousClass2();
        DATETIME_FORMAT = new AnonymousClass3();
        ALL_EXIF_TAGS = getAllExifTags();
        DO_NOT_COPY_EXIF_TAGS = Arrays.asList(new String[]{"ImageWidth", "ImageLength", "PixelXDimension", "PixelYDimension", "Compression", "JPEGInterchangeFormat", "JPEGInterchangeFormatLength", "ThumbnailImageLength", "ThumbnailImageWidth", "ThumbnailOrientation"});
    }

    private Exif(ExifInterface r2) {
        this.mRemoveTimestamp = false;
        this.mExifInterface = r2;
    }

    private static Date convertFromExifDate(String r1) {
        return DATE_FORMAT.get().parse(r1);
    }

    private static Date convertFromExifDateTime(String r1) {
        return DATETIME_FORMAT.get().parse(r1);
    }

    private static Date convertFromExifTime(String r1) {
        return TIME_FORMAT.get().parse(r1);
    }

    public static Exif createFromFile(File r0) {
        return createFromFileString(r0.toString());
    }

    public static Exif createFromFileString(String r2) {
        return new Exif(new ExifInterface(r2));
    }

    public static Exif createFromImageProxy(ImageProxy r1) {
        ByteBuffer r12 = r1.getPlanes()[0].getBuffer();
        r12.rewind();
        byte[] r0 = new byte[r12.capacity()];
        r12.get(r0);
        return createFromInputStream(new ByteArrayInputStream(r0));
    }

    public static Exif createFromInputStream(InputStream r2) {
        return new Exif(new ExifInterface(r2));
    }

    public static List<String> getAllExifTags() {
        return Arrays.asList(new String[]{"ImageWidth", "ImageLength", "BitsPerSample", "Compression", "PhotometricInterpretation", "Orientation", "SamplesPerPixel", "PlanarConfiguration", "YCbCrSubSampling", "YCbCrPositioning", "XResolution", "YResolution", "ResolutionUnit", "StripOffsets", "RowsPerStrip", "StripByteCounts", "JPEGInterchangeFormat", "JPEGInterchangeFormatLength", "TransferFunction", "WhitePoint", "PrimaryChromaticities", "YCbCrCoefficients", "ReferenceBlackWhite", "DateTime", "ImageDescription", "Make", "Model", "Software", "Artist", "Copyright", "ExifVersion", "FlashpixVersion", "ColorSpace", "Gamma", "PixelXDimension", "PixelYDimension", "ComponentsConfiguration", "CompressedBitsPerPixel", "MakerNote", "UserComment", "RelatedSoundFile", "DateTimeOriginal", "DateTimeDigitized", "OffsetTime", "OffsetTimeOriginal", "OffsetTimeDigitized", "SubSecTime", "SubSecTimeOriginal", "SubSecTimeDigitized", "ExposureTime", "FNumber", "ExposureProgram", "SpectralSensitivity", "PhotographicSensitivity", "OECF", "SensitivityType", "StandardOutputSensitivity", "RecommendedExposureIndex", "ISOSpeed", "ISOSpeedLatitudeyyy", "ISOSpeedLatitudezzz", "ShutterSpeedValue", "ApertureValue", "BrightnessValue", "ExposureBiasValue", "MaxApertureValue", "SubjectDistance", "MeteringMode", "LightSource", "Flash", "SubjectArea", "FocalLength", "FlashEnergy", "SpatialFrequencyResponse", "FocalPlaneXResolution", "FocalPlaneYResolution", "FocalPlaneResolutionUnit", "SubjectLocation", "ExposureIndex", "SensingMethod", "FileSource", "SceneType", "CFAPattern", "CustomRendered", "ExposureMode", "WhiteBalance", "DigitalZoomRatio", "FocalLengthIn35mmFilm", "SceneCaptureType", "GainControl", "Contrast", "Saturation", "Sharpness", "DeviceSettingDescription", "SubjectDistanceRange", "ImageUniqueID", "CameraOwnerName", "BodySerialNumber", "LensSpecification", "LensMake", "LensModel", "LensSerialNumber", "GPSVersionID", "GPSLatitudeRef", "GPSLatitude", "GPSLongitudeRef", "GPSLongitude", "GPSAltitudeRef", "GPSAltitude", "GPSTimeStamp", "GPSSatellites", "GPSStatus", "GPSMeasureMode", "GPSDOP", "GPSSpeedRef", "GPSSpeed", "GPSTrackRef", "GPSTrack", "GPSImgDirectionRef", "GPSImgDirection", "GPSMapDatum", "GPSDestLatitudeRef", "GPSDestLatitude", "GPSDestLongitudeRef", "GPSDestLongitude", "GPSDestBearingRef", "GPSDestBearing", "GPSDestDistanceRef", "GPSDestDistance", "GPSProcessingMethod", "GPSAreaInformation", "GPSDateStamp", "GPSDifferential", "GPSHPositioningError", "InteroperabilityIndex", "ThumbnailImageLength", "ThumbnailImageWidth", "ThumbnailOrientation", "DNGVersion", "DefaultCropSize", "ThumbnailImage", "PreviewImageStart", "PreviewImageLength", "AspectFrame", "SensorBottomBorder", "SensorLeftBorder", "SensorRightBorder", "SensorTopBorder", "ISO", "JpgFromRaw", "Xmp", "NewSubfileType", "SubfileType"});
    }

    private long parseTimestamp(String r3) {
        if (r3 != null) goto L8;
        return -1;
    L8:
        return convertFromExifDateTime(r3).getTime();
    L7:
        return -1;
    }

    private long parseTimestamp(String r3, String r4) {
        if (r3 != null) goto L6;
        if (r4 != null) goto L6;
        return -1;
    L6:
        if (r4 == null) goto L16;
        if (r3 != null) goto L15;
        return convertFromExifTime(r4).getTime();
    L13:
        return -1;
    L15:
        return parseTimestamp(r3 + " " + r4);
    L16:
        return convertFromExifDate(r3).getTime();
    L9:
        return -1;
    }

    public void copyToCroppedImage(Exif r7) {
        ArrayList r0 = new ArrayList(ALL_EXIF_TAGS);
        r0.removeAll(DO_NOT_COPY_EXIF_TAGS);
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        Object r3 = r0.get(r2);
        r2 = r2 + 1;
        String r32 = (String) r3;
        String r4 = this.mExifInterface.getAttribute(r32);
        String r5 = r7.mExifInterface.getAttribute(r32);
        if (r4 == null) goto L3;
        if (r4.equals(r5) == true) goto L3;
        r7.mExifInterface.setAttribute(r32, r4);
        goto L3
    }

    public String getDescription() {
        return this.mExifInterface.getAttribute("ImageDescription");
    }

    public int getHeight() {
        return this.mExifInterface.getAttributeInt("ImageLength", 0);
    }

    public Location getLocation() {
        String r0 = this.mExifInterface.getAttribute("GPSProcessingMethod");
        double[] r1 = this.mExifInterface.getLatLong();
        double r5 = this.mExifInterface.getAltitude(0.0d);
        double r7 = this.mExifInterface.getAttributeDouble("GPSSpeed", 0.0d);
        String r2 = this.mExifInterface.getAttribute("GPSSpeedRef");
        if (r2 != null) goto L5;
        r2 = "K";
    L5:
        long r10 = parseTimestamp(this.mExifInterface.getAttribute("GPSDateStamp"), this.mExifInterface.getAttribute("GPSTimeStamp"));
        if (r1 != null) goto L9;
        return null;
    L9:
        if (r0 != null) goto L11;
        r0 = TAG;
    L11:
        Location r14 = new Location(r0);
        r14.setLatitude(r1[0]);
        r14.setLongitude(r1[1]);
        if (r5 == 0.0d) goto L15;
        r14.setAltitude(r5);
    L15:
        if (r7 == 0.0d) goto L33;
        int r02 = r2.hashCode();
        if (r02 != 75) goto L19;
        r2.equals("K");
    L30:
        double r03 = Speed.fromKilometersPerHour(r7).toMetersPerSecond();
    L31:
        r14.setSpeed((float) r03);
        goto L33
    L19:
        if (r02 == 77) goto L27;
        if (r02 != 78) goto L30;
        if (r2.equals("N") == false) goto L30;
        r03 = Speed.fromKnots(r7).toMetersPerSecond();
        goto L31
    L27:
        if (r2.equals("M") == false) goto L30;
        r03 = Speed.fromMilesPerHour(r7).toMetersPerSecond();
    L33:
        if (r10 == (-1)) goto L35;
        r14.setTime(r10);
    L35:
        return r14;
    }

    public int getOrientation() {
        return this.mExifInterface.getAttributeInt("Orientation", 0);
    }

    public int getRotation() {
        switch(getOrientation()) {
            case 3: goto L9;
            case 4: goto L9;
            case 5: goto L8;
            case 6: goto L7;
            case 7: goto L7;
            case 8: goto L6;
            default: goto L4;
        };
    L4:
        return 0;
    L6:
        return 270;
    L7:
        return 90;
    L8:
        return 270;
    L9:
        return 180;
    }

    public long getTimestamp() {
        long r0 = parseTimestamp(this.mExifInterface.getAttribute("DateTimeOriginal"));
        if (r0 != (-1)) goto L5;
        return -1;
    L5:
        String r6 = this.mExifInterface.getAttribute("SubSecTimeOriginal");
        if (r6 == null) goto L19;
        long r2 = Long.parseLong(r6);     // Catch: NumberFormatException -> L14
    L9:
        if (r2 <= 1000) goto L13;
        r2 = r2 / 10;     // Catch: NumberFormatException -> L14
    L13:
        return r0 + r2;
    L20:
        return r0;
    L19:
        return r0;
    }

    public int getWidth() {
        return this.mExifInterface.getAttributeInt("ImageWidth", 0);
    }

    public boolean isFlippedHorizontally() {
        if (getOrientation() == 2) goto L6;
        return false;
    L6:
        return true;
    }

    public boolean isFlippedVertically() {
        int r2 = getOrientation();
        if (r2 != 4) goto L5;
    L10:
        return true;
    L5:
        if (r2 == 5) goto L10;
        if (r2 == 7) goto L10;
        return false;
    }

    public void rotate(int r10) {
        if ((r10 % 90) == 0) goto L6;
        Logger.w(TAG, String.format(Locale.US, "Can only rotate in right angles (eg. 0, 90, 180, 270). %d is unsupported.", new Object[]{Integer.valueOf(r10)}));
        this.mExifInterface.setAttribute("Orientation", String.valueOf(0));
        return;
    L6:
        int r102 = r10 % 360;
        int r0 = getOrientation();
    L8:
        if (r102 >= 0) goto L18;
        r102 = r102 + 90;
        switch(r0) {
            case 2: goto L17;
            case 3: goto L12;
            case 4: goto L16;
            case 5: goto L15;
            case 6: goto L14;
            case 7: goto L13;
            case 8: goto L12;
            default: goto L11;
        };
    L12:
        r0 = 6;
        goto L8
    L13:
        r0 = 2;
        goto L8
    L14:
        r0 = 1;
        goto L8
    L15:
        r0 = 4;
        goto L8
    L16:
        r0 = 7;
        goto L8
    L17:
        r0 = 5;
        goto L8
    L11:
        r0 = 8;
    L18:
        if (r102 <= 0) goto L29;
        r102 = r102 - 90;
        switch(r0) {
            case 2: goto L28;
            case 3: goto L27;
            case 4: goto L26;
            case 5: goto L25;
            case 6: goto L24;
            case 7: goto L23;
            case 8: goto L22;
            default: goto L21;
        };
    L22:
        r0 = 1;
        goto L18
    L23:
        r0 = 4;
        goto L18
    L24:
        r0 = 3;
        goto L18
    L25:
        r0 = 2;
        goto L18
    L26:
        r0 = 5;
        goto L18
    L27:
        r0 = 8;
        goto L18
    L28:
        r0 = 7;
        goto L18
    L21:
        r0 = 6;
        goto L18
    L29:
        this.mExifInterface.setAttribute("Orientation", String.valueOf(r0));
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "Exif{width=%s, height=%s, rotation=%d, isFlippedVertically=%s, isFlippedHorizontally=%s, location=%s, timestamp=%s, description=%s}", new Object[]{Integer.valueOf(getWidth()), Integer.valueOf(getHeight()), Integer.valueOf(getRotation()), Boolean.valueOf(isFlippedVertically()), Boolean.valueOf(isFlippedHorizontally()), getLocation(), Long.valueOf(getTimestamp()), getDescription()});
    }
}
