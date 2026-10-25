package androidx.camera.core.impl.utils;

import android.os.Build;
import android.util.Pair;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureMetaData$FlashState;
import androidx.core.util.Preconditions;
import androidx.mediarouter.media.GlobalMediaRouter;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes3.dex */
public class ExifData {
    private static final String COMPONENTS_CONFIGURATION_YCBCR = null;
    static final ExifTag[] EXIF_POINTER_TAGS = null;
    static final ExifTag[][] EXIF_TAGS = null;
    private static final ExifTag[] IFD_EXIF_TAGS = null;
    static final String[] IFD_FORMAT_NAMES = null;
    private static final ExifTag[] IFD_GPS_TAGS = null;
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS = null;
    private static final ExifTag[] IFD_TIFF_TAGS = null;
    static final HashSet<String> sTagSetForCompatibility = null;
    private final List<Map<String, ExifAttribute>> mAttributes;
    private final ByteOrder mByteOrder;

    /* renamed from: androidx.camera.core.impl.utils.ExifData$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$impl$CameraCaptureMetaData$FlashState = null;

        static {
            int[] r0 = new int[CameraCaptureMetaData$FlashState.values().length];
            $SwitchMap$androidx$camera$core$impl$CameraCaptureMetaData$FlashState = r0;
            r0[CameraCaptureMetaData$FlashState.READY.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$androidx$camera$core$impl$CameraCaptureMetaData$FlashState[CameraCaptureMetaData$FlashState.NONE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$androidx$camera$core$impl$CameraCaptureMetaData$FlashState[CameraCaptureMetaData$FlashState.FIRED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public static final class Builder {
        private static final Pattern DATETIME_PRIMARY_FORMAT_PATTERN = null;
        private static final Pattern DATETIME_SECONDARY_FORMAT_PATTERN = null;
        private static final Pattern GPS_TIMESTAMP_PATTERN = null;
        static final List<HashMap<String, ExifTag>> sExifTagMapsForWriting = null;
        final List<Map<String, ExifAttribute>> mAttributes;
        private final ByteOrder mByteOrder;

        /* renamed from: androidx.camera.core.impl.utils.ExifData$Builder$1, reason: invalid class name */
        public class AnonymousClass1 implements Enumeration<HashMap<String, ExifTag>> {
            int mIfdIndex;

            public AnonymousClass1() {
                this.mIfdIndex = 0;
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                if (this.mIfdIndex >= ExifData.EXIF_TAGS.length) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // java.util.Enumeration
            public /* bridge */ /* synthetic */ HashMap<String, ExifTag> nextElement() {
                return nextElement2();
            }

            @Override // java.util.Enumeration
            /* renamed from: nextElement, reason: avoid collision after fix types in other method */
            public HashMap<String, ExifTag> nextElement2() {
                HashMap<String, ExifTag> r0 = new HashMap();
                ExifTag[] r1 = ExifData.EXIF_TAGS[this.mIfdIndex];
                int r2 = r1.length;
                int r3 = 0;
            L3:
                if (r3 >= r2) goto L5;
                ExifTag r4 = r1[r3];
                r0.put(r4.name, r4);
                r3 = r3 + 1;
                goto L3
            L5:
                this.mIfdIndex++;
                return r0;
            }
        }

        /* renamed from: androidx.camera.core.impl.utils.ExifData$Builder$2, reason: invalid class name */
        public class AnonymousClass2 implements Enumeration<Map<String, ExifAttribute>> {
            int mIfdIndex;
            final /* synthetic */ Builder this$0;

            public AnonymousClass2(Builder r1) {
                this.this$0 = r1;
                this.mIfdIndex = 0;
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                if (this.mIfdIndex >= ExifData.EXIF_TAGS.length) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // java.util.Enumeration
            public /* bridge */ /* synthetic */ Map<String, ExifAttribute> nextElement() {
                return nextElement2();
            }

            @Override // java.util.Enumeration
            /* renamed from: nextElement, reason: avoid collision after fix types in other method */
            public Map<String, ExifAttribute> nextElement2() {
                this.mIfdIndex++;
                return new HashMap();
            }
        }

        /* renamed from: androidx.camera.core.impl.utils.ExifData$Builder$3, reason: invalid class name */
        public class AnonymousClass3 implements Enumeration<Map<String, ExifAttribute>> {
            final Enumeration<Map<String, ExifAttribute>> mMapEnumeration;
            final /* synthetic */ Builder this$0;

            public AnonymousClass3(Builder r1) {
                this.this$0 = r1;
                this.mMapEnumeration = Collections.enumeration(r1.mAttributes);
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.mMapEnumeration.hasMoreElements();
            }

            @Override // java.util.Enumeration
            public /* bridge */ /* synthetic */ Map<String, ExifAttribute> nextElement() {
                return nextElement2();
            }

            @Override // java.util.Enumeration
            /* renamed from: nextElement, reason: avoid collision after fix types in other method */
            public Map<String, ExifAttribute> nextElement2() {
                return new HashMap(this.mMapEnumeration.nextElement());
            }
        }

        static {
            GPS_TIMESTAMP_PATTERN = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
            DATETIME_PRIMARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
            DATETIME_SECONDARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
            sExifTagMapsForWriting = Collections.list(new AnonymousClass1());
        }

        public Builder(ByteOrder r2) {
            this.mAttributes = Collections.list(new AnonymousClass2(this));
            this.mByteOrder = r2;
        }

        private static Pair<Integer, Integer> guessDataFormat(String r13) {
            int r10 = 1;
            if (r13.contains(",") == false) goto L35;
            String[] r132 = r13.split(",", -1);
            Pair<Integer, Integer> r0 = guessDataFormat(r132[0]);
            if (((Integer) r0.first).intValue() != 2) goto L8;
            return r0;
        L8:
            if (r10 >= r132.length) goto L33;
            Pair<Integer, Integer> r1 = guessDataFormat(r132[r10]);
            if (((Integer) r1.first).equals(r0.first) == false) goto L12;
        L15:
            int r2 = ((Integer) r0.first).intValue();
        L17:
            if (((Integer) r0.second).intValue() != (-1)) goto L19;
        L23:
            int r12 = -1;
        L24:
            if (r2 != (-1)) goto L28;
            if (r12 != (-1)) goto L28;
            return new Pair(2, -1);
        L28:
            if (r2 != (-1)) goto L30;
            r0 = new Pair(Integer.valueOf(r12), -1);
        L32:
            r10 = r10 + 1;
            goto L8
        L30:
            if (r12 != (-1)) goto L32;
            r0 = new Pair(Integer.valueOf(r2), -1);
            goto L32
        L19:
            if (((Integer) r1.first).equals(r0.second) == false) goto L21;
        L22:
            r12 = ((Integer) r0.second).intValue();
            goto L24
        L21:
            if (((Integer) r1.second).equals(r0.second) == false) goto L23;
        L12:
            if (((Integer) r1.second).equals(r0.first) == true) goto L15;
            r2 = -1;
            goto L17
        L33:
            return r0;
        L35:
            if (r13.contains("/") == false) goto L72;
            String[] r133 = r13.split("/", -1);
            if (r133.length != 2) goto L55;
            long r7 = (long) Double.parseDouble(r133[0]);     // Catch: NumberFormatException -> L71
            long r9 = (long) Double.parseDouble(r133[1]);     // Catch: NumberFormatException -> L71
            if (r7 < 0) goto L53;
            if (r9 < 0) goto L53;
            if (r7 > 2147483647L) goto L51;
            if (r9 > 2147483647L) goto L51;
            return new Pair(10, 5);
        L51:
            return new Pair(5, -1);
        L53:
            return new Pair(10, -1);
        L55:
            return new Pair(2, -1);
        L72:
            long r14 = Long.parseLong(r13);     // Catch: NumberFormatException -> L67
            if (r14 >= 0) goto L59;
        L62:
            if (r14 >= 0) goto L66;
            return new Pair(9, -1);
        L66:
            return new Pair(4, -1);
        L59:
            if (r14 > WebSocketProtocol.PAYLOAD_SHORT_MAX) goto L62;
            return new Pair(3, 4);
        L67:
            Double.parseDouble(r13);     // Catch: NumberFormatException -> L69
            return new Pair(12, -1);
        L70:
            return new Pair(2, -1);
        }

        private void setAttributeIfMissing(String r3, String r4, List<Map<String, ExifAttribute>> r5) {
            Iterator<Map<String, ExifAttribute>> r0 = r5.iterator();
        L4:
            if (r0.hasNext() == false) goto L8;
            if (r0.next().containsKey(r3) == false) goto L4;
            return;
        L8:
            setAttributeInternal(r3, r4, r5);
        }

        private void setAttributeInternal(String r18, String r19, List<Map<String, ExifAttribute>> r20) {
            String r1 = r18;
            String r2 = r19;
            if ("DateTime".equals(r1) == false) goto L5;
        L8:
            if (r2 == null) goto L19;
            boolean r4 = DATETIME_PRIMARY_FORMAT_PATTERN.matcher(r2).find();
            boolean r8 = DATETIME_SECONDARY_FORMAT_PATTERN.matcher(r2).find();
            if (r2.length() != 19) goto L16;
            if (r4 == true) goto L14;
            if (r8 == false) goto L16;
        L14:
            if (r8 == false) goto L19;
            r2 = r2.replaceAll("-", ":");
        L16:
            Logger.w("ExifData", "Invalid value for " + r1 + " : " + r2);
            return;
        L19:
            if ("ISOSpeedRatings".equals(r1) == false) goto L21;
            r1 = "PhotographicSensitivity";
        L21:
            int r42 = 2;
            int r82 = 1;
            if (r2 != null) goto L24;
        L37:
            int r5 = 0;
            int r6 = 0;
        L39:
            if (r6 >= ExifData.EXIF_TAGS.length) goto L100;
            ExifTag r7 = sExifTagMapsForWriting.get(r6).get(r1);
            if (r7 == null) goto L44;
            if (r2 != null) goto L45;
            r20.get(r6).remove(r1);
            goto L44
        L45:
            Pair<Integer, Integer> r9 = guessDataFormat(r2);
            if (r7.primaryFormat != ((Integer) r9.first).intValue()) goto L48;
        L63:
            int r72 = r7.primaryFormat;
        L64:
            String r92 = "/";
            switch(r72) {
                case 1: goto L98;
                case 2: goto L97;
                case 3: goto L92;
                case 4: goto L87;
                case 5: goto L82;
                case 6: goto L44;
                case 7: goto L97;
                case 8: goto L44;
                case 9: goto L77;
                case 10: goto L72;
                case 11: goto L44;
                case 12: goto L67;
                default: goto L44;
            };
        L67:
            String[] r73 = r2.split(",", -1);
            double[] r93 = new double[r73.length];
            int r10 = r5;
        L69:
            if (r10 >= r73.length) goto L71;
            r93[r10] = Double.parseDouble(r73[r10]);
            r10 = r10 + 1;
            goto L69
        L71:
            r20.get(r6).put(r1, ExifAttribute.createDouble(r93, this.mByteOrder));
            goto L44
        L72:
            String[] r74 = r2.split(",", -1);
            LongRational[] r102 = new LongRational[r74.length];
            int r11 = r5;
        L74:
            if (r11 >= r74.length) goto L76;
            String[] r13 = r74[r11].split(r92, -1);
            r102[r11] = new LongRational((long) Double.parseDouble(r13[r5]), (long) Double.parseDouble(r13[r82]));
            r11 = r11 + 1;
            r5 = r5;
            r82 = r82;
            r92 = r92;
            goto L74
        L76:
            int r192 = r5;
            int r15 = r82;
            r20.get(r6).put(r1, ExifAttribute.createSRational(r102, this.mByteOrder));
        L99:
            r6 = r6 + 1;
            r5 = r192;
            r82 = r15;
            r42 = 2;
            goto L39
        L77:
            r192 = r5;
            r15 = r82;
            String[] r43 = r2.split(",", -1);
            int[] r52 = new int[r43.length];
            int r75 = r192;
        L79:
            if (r75 >= r43.length) goto L81;
            r52[r75] = Integer.parseInt(r43[r75]);
            r75 = r75 + 1;
            goto L79
        L81:
            r20.get(r6).put(r1, ExifAttribute.createSLong(r52, this.mByteOrder));
            goto L99
        L82:
            r192 = r5;
            r15 = r82;
            String[] r44 = r2.split(",", -1);
            LongRational[] r53 = new LongRational[r44.length];
            int r76 = r192;
        L84:
            if (r76 >= r44.length) goto L86;
            String[] r83 = r44[r76].split("/", -1);
            int r112 = r76;
            r53[r112] = new LongRational((long) Double.parseDouble(r83[r192]), (long) Double.parseDouble(r83[r15]));
            r76 = r112 + 1;
            goto L84
        L86:
            r20.get(r6).put(r1, ExifAttribute.createURational(r53, this.mByteOrder));
            goto L99
        L87:
            r192 = r5;
            r15 = r82;
            String[] r45 = r2.split(",", -1);
            long[] r54 = new long[r45.length];
            int r77 = r192;
        L89:
            if (r77 >= r45.length) goto L91;
            r54[r77] = Long.parseLong(r45[r77]);
            r77 = r77 + 1;
            goto L89
        L91:
            r20.get(r6).put(r1, ExifAttribute.createULong(r54, this.mByteOrder));
            goto L99
        L92:
            r192 = r5;
            r15 = r82;
            String[] r46 = r2.split(",", -1);
            int[] r55 = new int[r46.length];
            int r78 = r192;
        L94:
            if (r78 >= r46.length) goto L96;
            r55[r78] = Integer.parseInt(r46[r78]);
            r78 = r78 + 1;
            goto L94
        L96:
            r20.get(r6).put(r1, ExifAttribute.createUShort(r55, this.mByteOrder));
            goto L99
        L97:
            r192 = r5;
            r15 = r82;
            r20.get(r6).put(r1, ExifAttribute.createString(r2));
            goto L99
        L98:
            r192 = r5;
            r15 = r82;
            r20.get(r6).put(r1, ExifAttribute.createByte(r2));
            goto L99
        L48:
            if (r7.primaryFormat == ((Integer) r9.second).intValue()) goto L63;
            int r103 = r7.secondaryFormat;
            if (r103 != (-1)) goto L53;
        L57:
            r72 = r7.primaryFormat;
            if (r72 == r82) goto L64;
            if (r72 == 7) goto L64;
            if (r72 != r42) goto L44;
        L53:
            if (r103 != ((Integer) r9.first).intValue()) goto L55;
        L56:
            r72 = r7.secondaryFormat;
            goto L64
        L55:
            if (r7.secondaryFormat != ((Integer) r9.second).intValue()) goto L57;
        L44:
            r192 = r5;
            r15 = r82;
            goto L99
        L100:
            return;
        L24:
            if (ExifData.sTagSetForCompatibility.contains(r1) == false) goto L37;
            if (r1.equals("GPSTimeStamp") == false) goto L101;
            Matcher r94 = GPS_TIMESTAMP_PATTERN.matcher(r2);
            if (r94.find() == true) goto L31;
            Logger.w("ExifData", "Invalid value for " + r1 + " : " + r2);
            return;
        L31:
            r2 = Integer.parseInt((String) Preconditions.checkNotNull(r94.group(1))) + "/1," + Integer.parseInt((String) Preconditions.checkNotNull(r94.group(2))) + "/1," + Integer.parseInt((String) Preconditions.checkNotNull(r94.group(3))) + "/1";
            goto L37
        L101:
            r2 = new LongRational(Double.parseDouble(r2)).toString();     // Catch: NumberFormatException -> L34
        L34:
            e = move-exception;
            Logger.w("ExifData", "Invalid value for " + r1 + " : " + r2, e);
            return;
        L5:
            if ("DateTimeOriginal".equals(r1) == true) goto L8;
            if ("DateTimeDigitized".equals(r1) == false) goto L19;
            goto L8
        }

        public ExifData build() {
            ArrayList r0 = Collections.list(new AnonymousClass3(this));
            if (r0.get(1).isEmpty() == true) goto L6;
            setAttributeIfMissing("ExposureProgram", String.valueOf(0), r0);
            setAttributeIfMissing("ExifVersion", "0230", r0);
            setAttributeIfMissing("ComponentsConfiguration", ExifData.access$000(), r0);
            setAttributeIfMissing("MeteringMode", String.valueOf(0), r0);
            setAttributeIfMissing("LightSource", String.valueOf(0), r0);
            setAttributeIfMissing("FlashpixVersion", "0100", r0);
            setAttributeIfMissing("FocalPlaneResolutionUnit", String.valueOf(2), r0);
            setAttributeIfMissing("FileSource", String.valueOf(3), r0);
            setAttributeIfMissing("SceneType", String.valueOf(1), r0);
            setAttributeIfMissing("CustomRendered", String.valueOf(0), r0);
            setAttributeIfMissing("SceneCaptureType", String.valueOf(0), r0);
            setAttributeIfMissing("Contrast", String.valueOf(0), r0);
            setAttributeIfMissing("Saturation", String.valueOf(0), r0);
            setAttributeIfMissing("Sharpness", String.valueOf(0), r0);
        L6:
            if (r0.get(2).isEmpty() == true) goto L9;
            setAttributeIfMissing("GPSVersionID", "2300", r0);
            setAttributeIfMissing("GPSSpeedRef", "K", r0);
            setAttributeIfMissing("GPSTrackRef", "T", r0);
            setAttributeIfMissing("GPSImgDirectionRef", "T", r0);
            setAttributeIfMissing("GPSDestBearingRef", "T", r0);
            setAttributeIfMissing("GPSDestDistanceRef", "K", r0);
        L9:
            return new ExifData(this.mByteOrder, r0);
        }

        public Builder setAttribute(String r2, String r3) {
            setAttributeInternal(r2, r3, this.mAttributes);
            return this;
        }

        public Builder setExposureTimeNanos(long r3) {
            return setAttribute("ExposureTime", String.valueOf(r3 / 1.0E9d));
        }

        public Builder setFlashState(CameraCaptureMetaData$FlashState r4) {
            if (r4 != CameraCaptureMetaData$FlashState.UNKNOWN) goto L5;
            return this;
        L5:
            int r0 = AnonymousClass1.$SwitchMap$androidx$camera$core$impl$CameraCaptureMetaData$FlashState[r4.ordinal()];
            if (r0 != 1) goto L8;
            int r42 = 0;
        L17:
            if ((r42 & 1) != 1) goto L20;
            setAttribute("LightSource", String.valueOf(4));
        L20:
            return setAttribute("Flash", String.valueOf(r42));
        L8:
            if (r0 != 2) goto L10;
            r42 = 32;
            goto L17
        L10:
            if (r0 == 3) goto L13;
            Logger.w("ExifData", "Unknown flash state: " + r4);
            return this;
        L13:
            r42 = 1;
            goto L17
        }

        public Builder setFocalLength(float r6) {
            return setAttribute("FocalLength", new LongRational((long) (r6 * 1000.0f), 1000).toString());
        }

        public Builder setImageHeight(int r2) {
            return setAttribute("ImageLength", String.valueOf(r2));
        }

        public Builder setImageWidth(int r2) {
            return setAttribute("ImageWidth", String.valueOf(r2));
        }

        public Builder setIso(int r3) {
            return setAttribute("SensitivityType", String.valueOf(3)).setAttribute("PhotographicSensitivity", String.valueOf(Math.min(65535, r3)));
        }

        public Builder setLensFNumber(float r2) {
            return setAttribute("FNumber", String.valueOf(r2));
        }

        public Builder setOrientationDegrees(int r3) {
            if (r3 != 0) goto L4;
            int r32 = 1;
        L15:
            return setAttribute("Orientation", String.valueOf(r32));
        L4:
            if (r3 != 90) goto L6;
            r32 = 6;
            goto L15
        L6:
            if (r3 != 180) goto L8;
            r32 = 3;
            goto L15
        L8:
            if (r3 == 270) goto L10;
            Logger.w("ExifData", "Unexpected orientation value: " + r3 + ". Must be one of 0, 90, 180, 270.");
            r32 = 0;
            goto L15
        L10:
            r32 = 8;
            goto L15
        }

        public Builder setWhiteBalanceMode(WhiteBalanceMode r2) {
            int r22 = r2.ordinal();
            if (r22 != 0) goto L5;
            String r23 = String.valueOf(0);
        L10:
            return setAttribute("WhiteBalance", r23);
        L5:
            if (r22 == 1) goto L7;
            r23 = null;
            goto L10
        L7:
            r23 = String.valueOf(1);
            goto L10
        }
    }

    public enum WhiteBalanceMode extends Enum<WhiteBalanceMode> {
        private static final /* synthetic */ WhiteBalanceMode[] $VALUES = null;
        public static final WhiteBalanceMode AUTO = null;
        public static final WhiteBalanceMode MANUAL = null;

        private static /* synthetic */ WhiteBalanceMode[] $values() {
            return new WhiteBalanceMode[]{AUTO, MANUAL};
        }

        static {
            AUTO = new WhiteBalanceMode("AUTO", 0);
            MANUAL = new WhiteBalanceMode("MANUAL", 1);
            $VALUES = $values();
        }

        WhiteBalanceMode(String r1, int r2) {
        }

        public static WhiteBalanceMode valueOf(String r1) {
            return (WhiteBalanceMode) Enum.valueOf(WhiteBalanceMode.class, r1);
        }

        public static WhiteBalanceMode[] values() {
            return (WhiteBalanceMode[]) $VALUES.clone();
        }
    }

    static {
        IFD_FORMAT_NAMES = new String[]{_UrlKt.FRAGMENT_ENCODE_SET, "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
        ExifTag[] r1 = {new ExifTag("ImageWidth", 256, 3, 4), new ExifTag("ImageLength", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED, 3, 4), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("Orientation", 274, 3), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4)};
        IFD_TIFF_TAGS = r1;
        ExifTag[] r2 = {new ExifTag("ExposureTime", 33434, 5), new ExifTag("FNumber", 33437, 5), new ExifTag("ExposureProgram", 34850, 3), new ExifTag("PhotographicSensitivity", 34855, 3), new ExifTag("SensitivityType", 34864, 3), new ExifTag("ExifVersion", 36864, 2), new ExifTag("DateTimeOriginal", 36867, 2), new ExifTag("DateTimeDigitized", 36868, 2), new ExifTag("ComponentsConfiguration", 37121, 7), new ExifTag("ShutterSpeedValue", 37377, 10), new ExifTag("ApertureValue", 37378, 5), new ExifTag("BrightnessValue", 37379, 10), new ExifTag("ExposureBiasValue", 37380, 10), new ExifTag("MaxApertureValue", 37381, 5), new ExifTag("MeteringMode", 37383, 3), new ExifTag("LightSource", 37384, 3), new ExifTag("Flash", 37385, 3), new ExifTag("FocalLength", 37386, 5), new ExifTag("SubSecTime", 37520, 2), new ExifTag("SubSecTimeOriginal", 37521, 2), new ExifTag("SubSecTimeDigitized", 37522, 2), new ExifTag("FlashpixVersion", 40960, 7), new ExifTag("ColorSpace", 40961, 3), new ExifTag("PixelXDimension", 40962, 3, 4), new ExifTag("PixelYDimension", 40963, 3, 4), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("FocalPlaneResolutionUnit", 41488, 3), new ExifTag("SensingMethod", 41495, 3), new ExifTag("FileSource", 41728, 7), new ExifTag("SceneType", 41729, 7), new ExifTag("CustomRendered", 41985, 3), new ExifTag("ExposureMode", 41986, 3), new ExifTag("WhiteBalance", 41987, 3), new ExifTag("SceneCaptureType", 41990, 3), new ExifTag("Contrast", 41992, 3), new ExifTag("Saturation", 41993, 3), new ExifTag("Sharpness", 41994, 3)};
        IFD_EXIF_TAGS = r2;
        ExifTag[] r3 = {new ExifTag("GPSVersionID", 0, 1), new ExifTag("GPSLatitudeRef", 1, 2), new ExifTag("GPSLatitude", 2, 5, 10), new ExifTag("GPSLongitudeRef", 3, 2), new ExifTag("GPSLongitude", 4, 5, 10), new ExifTag("GPSAltitudeRef", 5, 1), new ExifTag("GPSAltitude", 6, 5), new ExifTag("GPSTimeStamp", 7, 5), new ExifTag("GPSSpeedRef", 12, 2), new ExifTag("GPSTrackRef", 14, 2), new ExifTag("GPSImgDirectionRef", 16, 2), new ExifTag("GPSDestBearingRef", 23, 2), new ExifTag("GPSDestDistanceRef", 25, 2)};
        IFD_GPS_TAGS = r3;
        EXIF_POINTER_TAGS = new ExifTag[]{new ExifTag("SubIFDPointer", 330, 4), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("InteroperabilityIFDPointer", 40965, 4)};
        ExifTag[] r0 = {new ExifTag("InteroperabilityIndex", 1, 2)};
        IFD_INTEROPERABILITY_TAGS = r0;
        EXIF_TAGS = new ExifTag[][]{r1, r2, r3, r0};
        sTagSetForCompatibility = new HashSet(Arrays.asList(new String[]{"FNumber", "ExposureTime", "GPSTimeStamp"}));
        COMPONENTS_CONFIGURATION_YCBCR = new String(new byte[]{1, 2, 3, 0}, StandardCharsets.UTF_8);
    }

    public ExifData(ByteOrder r3, List<Map<String, ExifAttribute>> r4) {
        if (r4.size() != EXIF_TAGS.length) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0, "Malformed attributes list. Number of IFDs mismatch.");
        this.mByteOrder = r3;
        this.mAttributes = r4;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public static /* synthetic */ String access$000() {
        return COMPONENTS_CONFIGURATION_YCBCR;
    }

    public static Builder builderForDevice() {
        return new Builder(ByteOrder.BIG_ENDIAN).setAttribute("Orientation", String.valueOf(1)).setAttribute("XResolution", "72/1").setAttribute("YResolution", "72/1").setAttribute("ResolutionUnit", String.valueOf(2)).setAttribute("YCbCrPositioning", String.valueOf(1)).setAttribute("Make", Build.MANUFACTURER).setAttribute("Model", Build.MODEL);
    }

    public static ExifData create(ImageProxy r2, int r3) {
        Builder r0 = builderForDevice();
        if (r2.getImageInfo() == null) goto L5;
        r2.getImageInfo().populateExifData(r0);
    L5:
        r0.setOrientationDegrees(r3);
        return r0.setImageWidth(r2.getWidth()).setImageHeight(r2.getHeight()).build();
    }

    public Map<String, ExifAttribute> getAttributes(int r4) {
        Preconditions.checkArgumentInRange(r4, 0, EXIF_TAGS.length, "Invalid IFD index: " + r4 + ". Index should be between [0, EXIF_TAGS.length] ");
        return this.mAttributes.get(r4);
    }

    public ByteOrder getByteOrder() {
        return this.mByteOrder;
    }
}
