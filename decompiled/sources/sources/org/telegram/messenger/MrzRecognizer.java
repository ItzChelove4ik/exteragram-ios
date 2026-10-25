package org.telegram.messenger;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.util.Calendar;
import java.util.HashMap;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class MrzRecognizer {

    public static class Result {
        public static final int GENDER_FEMALE = 2;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_UNKNOWN = 0;
        public static final int TYPE_DRIVER_LICENSE = 4;
        public static final int TYPE_ID = 2;
        public static final int TYPE_INTERNAL_PASSPORT = 3;
        public static final int TYPE_PASSPORT = 1;
        public int birthDay;
        public int birthMonth;
        public int birthYear;
        public boolean doesNotExpire;
        public int expiryDay;
        public int expiryMonth;
        public int expiryYear;
        public String firstName;
        public int gender;
        public String issuingCountry;
        public String lastName;
        public boolean mainCheckDigitIsValid;
        public String middleName;
        public String nationality;
        public String number;
        public String rawMRZ;
        public int type;

        public Result() {
        }
    }

    public MrzRecognizer() {
    }

    private static native Rect[][] binarizeAndFindCharacters(Bitmap r0, Bitmap r1);

    private static String capitalize(String r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        char[] r52 = r5.toCharArray();
        int r2 = 0;
        boolean r3 = true;
    L7:
        if (r2 >= r52.length) goto L18;
        if (r3 == true) goto L13;
        if (Character.isLetter(r52[r2]) == false) goto L13;
        r52[r2] = Character.toLowerCase(r52[r2]);
    L16:
        r2 = r2 + 1;
    L13:
        if (r52[r2] != ' ') goto L15;
        r3 = true;
        goto L16
    L15:
        r3 = false;
        goto L16
    L18:
        return new String(r52);
    }

    private static int checksum(String r6) {
        char[] r62 = r6.toCharArray();
        int[] r0 = {7, 3, 1};
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r2 >= r62.length) goto L18;
        char r4 = r62[r2];
        if (r4 < '0') goto L11;
        if (r4 > '9') goto L11;
        int r42 = r4 - '0';
    L16:
        r3 = r3 + (r42 * r0[r2 % 3]);
        r2 = r2 + 1;
    L11:
        if (r4 >= 'A') goto L13;
    L15:
        r42 = 0;
        goto L16
    L13:
        if (r4 > 'Z') goto L15;
        r42 = r4 - '7';
        goto L16
    L18:
        return r3 % 10;
    }

    private static String cyrillicToLatin(String r34) {
        String[] r0 = {"A", "B", "V", "G", "D", "E", "E", "ZH", "Z", "I", "I", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "KH", "TS", "CH", "SH", "SHCH", "IE", "Y", _UrlKt.FRAGMENT_ENCODE_SET, "E", "IU", "IA"};
        int r2 = 0;
        String r1 = r34;
    L4:
        if (r2 >= 33) goto L6;
        int r3 = r2 + 1;
        r1 = r1.replace("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".substring(r2, r3), r0[r2]);
        r2 = r3;
        goto L4
    L6:
        return r1;
    }

    private static native int[] findCornerPoints(Bitmap r0);

    private static HashMap<String, String> getCountriesMap() {
        HashMap<String, String> r0 = new HashMap();
        r0.put("AFG", "AF");
        r0.put("ALA", "AX");
        r0.put("ALB", "AL");
        r0.put("DZA", "DZ");
        r0.put("ASM", "AS");
        r0.put("AND", "AD");
        r0.put("AGO", "AO");
        r0.put("AIA", "AI");
        r0.put("ATA", "AQ");
        r0.put("ATG", "AG");
        r0.put("ARG", "AR");
        r0.put("ARM", "AM");
        r0.put("ABW", "AW");
        r0.put("AUS", "AU");
        r0.put("AUT", "AT");
        r0.put("AZE", "AZ");
        r0.put("BHS", "BS");
        r0.put("BHR", "BH");
        r0.put("BGD", "BD");
        r0.put("BRB", "BB");
        r0.put("BLR", "BY");
        r0.put("BEL", "BE");
        r0.put("BLZ", "BZ");
        r0.put("BEN", "BJ");
        r0.put("BMU", "BM");
        r0.put("BTN", "BT");
        r0.put("BOL", "BO");
        r0.put("BES", "BQ");
        r0.put("BIH", "BA");
        r0.put("BWA", "BW");
        r0.put("BVT", "BV");
        r0.put("BRA", "BR");
        r0.put("IOT", "IO");
        r0.put("BRN", "BN");
        r0.put("BGR", "BG");
        r0.put("BFA", "BF");
        r0.put("BDI", "BI");
        r0.put("CPV", "CV");
        r0.put("KHM", "KH");
        r0.put("CMR", "CM");
        r0.put("CAN", "CA");
        r0.put("CYM", "KY");
        r0.put("CAF", "CF");
        r0.put("TCD", "TD");
        r0.put("CHL", "CL");
        r0.put("CHN", "CN");
        r0.put("CXR", "CX");
        r0.put("CCK", "CC");
        r0.put("COL", "CO");
        r0.put("COM", "KM");
        r0.put("COG", "CG");
        r0.put("COD", "CD");
        r0.put("COK", "CK");
        r0.put("CRI", "CR");
        r0.put("CIV", "CI");
        r0.put("HRV", "HR");
        r0.put("CUB", "CU");
        r0.put("CUW", "CW");
        r0.put("CYP", "CY");
        r0.put("CZE", "CZ");
        r0.put("DNK", "DK");
        r0.put("DJI", "DJ");
        r0.put("DMA", "DM");
        r0.put("DOM", "DO");
        r0.put("ECU", "EC");
        r0.put("EGY", "EG");
        r0.put("SLV", "SV");
        r0.put("GNQ", "GQ");
        r0.put("ERI", "ER");
        r0.put("EST", "EE");
        r0.put("ETH", "ET");
        r0.put("FLK", "FK");
        r0.put("FRO", "FO");
        r0.put("FJI", "FJ");
        r0.put("FIN", "FI");
        r0.put("FRA", "FR");
        r0.put("GUF", "GF");
        r0.put("PYF", "PF");
        r0.put("ATF", "TF");
        r0.put("GAB", "GA");
        r0.put("GMB", "GM");
        r0.put("GEO", "GE");
        r0.put("D<<", "DE");
        r0.put("GHA", "GH");
        r0.put("GIB", "GI");
        r0.put("GRC", "GR");
        r0.put("GRL", "GL");
        r0.put("GRD", "GD");
        r0.put("GLP", "GP");
        r0.put("GUM", "GU");
        r0.put("GTM", "GT");
        r0.put("GGY", "GG");
        r0.put("GIN", "GN");
        r0.put("GNB", "GW");
        r0.put("GUY", "GY");
        r0.put("HTI", "HT");
        r0.put("HMD", "HM");
        r0.put("VAT", "VA");
        r0.put("HND", "HN");
        r0.put("HKG", "HK");
        r0.put("HUN", "HU");
        r0.put("ISL", "IS");
        r0.put("IND", "IN");
        r0.put("IDN", "ID");
        r0.put("IRN", "IR");
        r0.put("IRQ", "IQ");
        r0.put("IRL", "IE");
        r0.put("IMN", "IM");
        r0.put("ISR", "IL");
        r0.put("ITA", "IT");
        r0.put("JAM", "JM");
        r0.put("JPN", "JP");
        r0.put("JEY", "JE");
        r0.put("JOR", "JO");
        r0.put("KAZ", "KZ");
        r0.put("KEN", "KE");
        r0.put("KIR", "KI");
        r0.put("PRK", "KP");
        r0.put("KOR", "KR");
        r0.put("KWT", "KW");
        r0.put("KGZ", "KG");
        r0.put("LAO", "LA");
        r0.put("LVA", "LV");
        r0.put("LBN", "LB");
        r0.put("LSO", "LS");
        r0.put("LBR", "LR");
        r0.put("LBY", "LY");
        r0.put("LIE", "LI");
        r0.put("LTU", "LT");
        r0.put("LUX", "LU");
        r0.put("MAC", "MO");
        r0.put("MKD", "MK");
        r0.put("MDG", "MG");
        r0.put("MWI", "MW");
        r0.put("MYS", "MY");
        r0.put("MDV", "MV");
        r0.put("MLI", "ML");
        r0.put("MLT", "MT");
        r0.put("MHL", "MH");
        r0.put("MTQ", "MQ");
        r0.put("MRT", "MR");
        r0.put("MUS", "MU");
        r0.put("MYT", "YT");
        r0.put("MEX", "MX");
        r0.put("FSM", "FM");
        r0.put("MDA", "MD");
        r0.put("MCO", "MC");
        r0.put("MNG", "MN");
        r0.put("MNE", "ME");
        r0.put("MSR", "MS");
        r0.put("MAR", "MA");
        r0.put("MOZ", "MZ");
        r0.put("MMR", "MM");
        r0.put("NAM", "NA");
        r0.put("NRU", "NR");
        r0.put("NPL", "NP");
        r0.put("NLD", "NL");
        r0.put("NCL", "NC");
        r0.put("NZL", "NZ");
        r0.put("NIC", "NI");
        r0.put("NER", "NE");
        r0.put("NGA", "NG");
        r0.put("NIU", "NU");
        r0.put("NFK", "NF");
        r0.put("MNP", "MP");
        r0.put("NOR", "NO");
        r0.put("OMN", "OM");
        r0.put("PAK", "PK");
        r0.put("PLW", "PW");
        r0.put("PSE", "PS");
        r0.put("PAN", "PA");
        r0.put("PNG", "PG");
        r0.put("PRY", "PY");
        r0.put("PER", "PE");
        r0.put("PHL", "PH");
        r0.put("PCN", "PN");
        r0.put("POL", "PL");
        r0.put("PRT", "PT");
        r0.put("PRI", "PR");
        r0.put("QAT", "QA");
        r0.put("REU", "RE");
        r0.put("ROU", "RO");
        r0.put("RUS", "RU");
        r0.put("RWA", "RW");
        r0.put("BLM", "BL");
        r0.put("SHN", "SH");
        r0.put("KNA", "KN");
        r0.put("LCA", "LC");
        r0.put("MAF", "MF");
        r0.put("SPM", "PM");
        r0.put("VCT", "VC");
        r0.put("WSM", "WS");
        r0.put("SMR", "SM");
        r0.put("STP", "ST");
        r0.put("SAU", "SA");
        r0.put("SEN", "SN");
        r0.put("SRB", "RS");
        r0.put("SYC", "SC");
        r0.put("SLE", "SL");
        r0.put("SGP", "SG");
        r0.put("SXM", "SX");
        r0.put("SVK", "SK");
        r0.put("SVN", "SI");
        r0.put("SLB", "SB");
        r0.put("SOM", "SO");
        r0.put("ZAF", "ZA");
        r0.put("SGS", "GS");
        r0.put("SSD", "SS");
        r0.put("ESP", "ES");
        r0.put("LKA", "LK");
        r0.put("SDN", "SD");
        r0.put("SUR", "SR");
        r0.put("SJM", "SJ");
        r0.put("SWZ", "SZ");
        r0.put("SWE", "SE");
        r0.put("CHE", "CH");
        r0.put("SYR", "SY");
        r0.put("TWN", "TW");
        r0.put("TJK", "TJ");
        r0.put("TZA", "TZ");
        r0.put("THA", "TH");
        r0.put("TLS", "TL");
        r0.put("TGO", "TG");
        r0.put("TKL", "TK");
        r0.put("TON", "TO");
        r0.put("TTO", "TT");
        r0.put("TUN", "TN");
        r0.put("TUR", "TR");
        r0.put("TKM", "TM");
        r0.put("TCA", "TC");
        r0.put("TUV", "TV");
        r0.put("UGA", "UG");
        r0.put("UKR", "UA");
        r0.put("ARE", "AE");
        r0.put("GBR", "GB");
        r0.put("USA", "US");
        r0.put("UMI", "UM");
        r0.put("URY", "UY");
        r0.put("UZB", "UZ");
        r0.put("VUT", "VU");
        r0.put("VEN", "VE");
        r0.put("VNM", "VN");
        r0.put("VGB", "VG");
        r0.put("VIR", "VI");
        r0.put("WLF", "WF");
        r0.put("ESH", "EH");
        r0.put("YEM", "YE");
        r0.put("ZMB", "ZM");
        r0.put("ZWE", "ZW");
        return r0;
    }

    private static int getNumber(char r1) {
        if (r1 != 'O') goto L7;
        return 0;
    L7:
        if (r1 != 'I') goto L11;
        return 1;
    L11:
        if (r1 != 'B') goto L15;
        return 8;
    L15:
        return r1 - '0';
    }

    private static void parseBirthDate(String r4, Result r5) {
        int r0 = Integer.parseInt(r4.substring(0, 2));     // Catch: NumberFormatException -> L10
        r5.birthYear = r0;     // Catch: NumberFormatException -> L10
        int r2 = (Calendar.getInstance().get(1) % 100) - 5;
        int r3 = r5.birthYear;
        if (r0 >= r2) goto L7;
        int r32 = r3 + CastStatusCodes.AUTHENTICATION_FAILED;
    L8:
        r5.birthYear = r32;     // Catch: NumberFormatException -> L10
        r5.birthMonth = Integer.parseInt(r4.substring(2, 4));     // Catch: NumberFormatException -> L10
        r5.birthDay = Integer.parseInt(r4.substring(4));     // Catch: NumberFormatException -> L10
        return;
    L7:
        r32 = r3 + 1900;
    }

    private static void parseExpiryDate(String r2, Result r3) {
        if ("<<<<<<".equals(r2) == false) goto L6;
        r3.doesNotExpire = true;     // Catch: NumberFormatException -> L8
        return;
    L6:
        r3.expiryYear = Integer.parseInt(r2.substring(0, 2)) + CastStatusCodes.AUTHENTICATION_FAILED;     // Catch: NumberFormatException -> L8
        r3.expiryMonth = Integer.parseInt(r2.substring(2, 4));     // Catch: NumberFormatException -> L8
        r3.expiryDay = Integer.parseInt(r2.substring(4));     // Catch: NumberFormatException -> L8
        return;
    }

    private static int parseGender(char r1) {
        if (r1 != 'F') goto L5;
        return 2;
    L5:
        if (r1 == 'M') goto L8;
        return 0;
    L8:
        return 1;
    }

    private static native String performRecognition(Bitmap r0, int r1, int r2, AssetManager r3);

    public static Result recognize(Bitmap r1, boolean r2) {
        if (r2 == false) goto L16;
        Result r0 = recognizeBarcode(r1);
        if (r0 == null) goto L16;
        return r0;
    L16:
        Result r02 = recognizeMRZ(r1);     // Catch: Exception -> L15
        if (r02 == null) goto L9;
        return r02;
    L9:
        if (r2 == true) goto L13;
        Result r12 = recognizeBarcode(r1);
        if (r12 == null) goto L18;
        return r12;
    L18:
        return null;
    L13:
        return null;
    }

    public static Result recognize(byte[] r9, int r10, int r11, int r12) {
        Bitmap r1 = Bitmap.createBitmap(r10, r11, Bitmap.Config.ARGB_8888);
        setYuvBitmapPixels(r1, r9);
        Matrix r6 = new Matrix();
        r6.setRotate(r12);
        int r92 = Math.min(r10, r11);
        int r0 = Math.round(r92 * 0.704f);
        if (r12 != 90) goto L5;
    L8:
        boolean r122 = true;
    L9:
        if (r122 == false) goto L11;
        int r2 = (r10 / 2) - (r0 / 2);
    L12:
        if (r122 == false) goto L14;
        int r3 = 0;
    L15:
        if (r122 == false) goto L17;
        int r4 = r0;
    L18:
        if (r122 == false) goto L20;
        int r5 = r92;
    L22:
        return recognize(Bitmap.createBitmap(r1, r2, r3, r4, r5, r6, false), false);
    L20:
        r5 = r0;
        goto L22
    L17:
        r4 = r92;
        goto L18
    L14:
        r3 = (r11 / 2) - (r0 / 2);
        goto L15
    L11:
        r2 = 0;
        goto L12
    L5:
        if (r12 == 270) goto L8;
        r122 = false;
        goto L9
    }

    private static Result recognizeBarcode(Bitmap r9) {
        BarcodeDetector r0 = new BarcodeDetector.Builder(ApplicationLoader.applicationContext).build();
        if (r9.getWidth() <= 1500) goto L5;
    L6:
        float r3 = 1500.0f / Math.max(r9.getWidth(), r9.getHeight());
        r9 = Bitmap.createScaledBitmap(r9, Math.round(r9.getWidth() * r3), Math.round(r9.getHeight() * r3), true);
    L7:
        SparseArray<Barcode> r92 = r0.detect(new Frame.Builder().setBitmap(r9).build());
        int r02 = 0;
        int r1 = 0;
    L9:
        if (r1 >= r92.size()) goto L59;
        Barcode r32 = r92.valueAt(r1);
        int r4 = r32.valueFormat;
        int r6 = 6;
        int r8 = 4;
        if (r4 != 12) goto L49;
        if (r32.driverLicense == null) goto L49;
        Result r93 = new Result();
        if ("ID".equals(r32.driverLicense.documentType) == false) goto L17;
        int r12 = 2;
    L18:
        r93.type = r12;
        String r13 = r32.driverLicense.issuingCountry;
        r13.getClass();
        if (r13.equals("CAN") == false) goto L21;
        r93.issuingCountry = "CA";
        r93.nationality = "CA";
    L25:
        r93.firstName = capitalize(r32.driverLicense.firstName);
        r93.lastName = capitalize(r32.driverLicense.lastName);
        r93.middleName = capitalize(r32.driverLicense.middleName);
        Barcode.DriverLicense r14 = r32.driverLicense;
        r93.number = r14.licenseNumber;
        String r15 = r14.gender;
        if (r15 == null) goto L35;
        r15.getClass();
        if (r15.equals("1") == false) goto L30;
        r93.gender = 1;
        goto L35
    L30:
        if (r15.equals("2") == false) goto L35;
        r93.gender = 2;
    L35:
        if ("USA".equals(r93.issuingCountry) == false) goto L65;
        r8 = 0;
        r02 = 4;
        r6 = 2;
    L65:
        String r16 = r32.driverLicense.birthDate;     // Catch: NumberFormatException -> L61
        if (r16 != null) goto L40;
    L42:
        String r17 = r32.driverLicense.expiryDate;     // Catch: NumberFormatException -> L61
        if (r17 == null) goto L47;
        if (r17.length() != 8) goto L47;
        r93.expiryYear = Integer.parseInt(r32.driverLicense.expiryDate.substring(r02, r02 + 4));     // Catch: NumberFormatException -> L61
        r93.expiryMonth = Integer.parseInt(r32.driverLicense.expiryDate.substring(r8, r8 + 2));     // Catch: NumberFormatException -> L61
        r93.expiryDay = Integer.parseInt(r32.driverLicense.expiryDate.substring(r6, r6 + 2));     // Catch: NumberFormatException -> L61
        goto L47
    L40:
        if (r16.length() != 8) goto L42;
        r93.birthYear = Integer.parseInt(r32.driverLicense.birthDate.substring(r02, r02 + 4));     // Catch: NumberFormatException -> L61
        r93.birthMonth = Integer.parseInt(r32.driverLicense.birthDate.substring(r8, r8 + 2));     // Catch: NumberFormatException -> L61
        r93.birthDay = Integer.parseInt(r32.driverLicense.birthDate.substring(r6, r6 + 2));     // Catch: NumberFormatException -> L61
    L47:
        return r93;
    L21:
        if (r13.equals("USA") == false) goto L25;
        r93.issuingCountry = "US";
        r93.nationality = "US";
        goto L25
    L17:
        r12 = 4;
    L49:
        if (r4 != 7) goto L58;
        if (r32.format != 2048) goto L58;
        if (r32.rawValue.matches("^[A-Za-z0-9=]+$") == false) goto L58;
        String[] r33 = new String(Base64.decode(r32.rawValue, 0), "windows-1251").split("\\|");     // Catch: Exception -> L62
        if (r33.length < 10) goto L58;
        Result r42 = new Result();     // Catch: Exception -> L62
        r42.type = 4;     // Catch: Exception -> L62
        r42.issuingCountry = "RU";     // Catch: Exception -> L62
        r42.nationality = "RU";     // Catch: Exception -> L62
        r42.number = r33[0];     // Catch: Exception -> L62
        r42.expiryYear = Integer.parseInt(r33[2].substring(0, 4));     // Catch: Exception -> L62
        r42.expiryMonth = Integer.parseInt(r33[2].substring(4, 6));     // Catch: Exception -> L62
        r42.expiryDay = Integer.parseInt(r33[2].substring(6));     // Catch: Exception -> L62
        r42.lastName = capitalize(cyrillicToLatin(r33[3]));     // Catch: Exception -> L62
        r42.firstName = capitalize(cyrillicToLatin(r33[4]));     // Catch: Exception -> L62
        r42.middleName = capitalize(cyrillicToLatin(r33[5]));     // Catch: Exception -> L62
        r42.birthYear = Integer.parseInt(r33[6].substring(0, 4));     // Catch: Exception -> L62
        r42.birthMonth = Integer.parseInt(r33[6].substring(4, 6));     // Catch: Exception -> L62
        r42.birthDay = Integer.parseInt(r33[6].substring(6));     // Catch: Exception -> L62
        return r42;
    L58:
        r1 = r1 + 1;
        goto L9
    L59:
        return null;
    L5:
        if (r9.getHeight() <= 1500) goto L7;
        goto L6
    }

    private static Result recognizeMRZ(Bitmap r34) {
        Bitmap r0 = r34;
        if (r0.getWidth() <= 512) goto L5;
    L8:
        float r4 = 512.0f / Math.max(r0.getWidth(), r0.getHeight());
        Bitmap r1 = Bitmap.createScaledBitmap(r0, Math.round(r0.getWidth() * r4), Math.round(r0.getHeight() * r4), true);
    L9:
        int[] r12 = findCornerPoints(r1);
        float r42 = 1.0f / r4;
        if (r12 == null) goto L33;
        Point r122 = new Point(r12[0], r12[1]);
        Point r13 = new Point(r12[2], r12[3]);
        int r17 = 7;
        Point r14 = new Point(r12[4], r12[5]);
        Point r6 = new Point(r12[6], r12[7]);
        if (r13.x < r122.x) goto L15;
        r14 = r6;
        r6 = r14;
        r13 = r122;
        r122 = r13;
    L15:
        int r20 = 3;
        double r3 = Math.hypot(r122.x - r13.x, r122.y - r13.y);
        int r21 = 6;
        int r22 = 1;
        double r15 = Math.hypot(r14.x - r6.x, r14.y - r6.y);
        double r32 = Math.hypot(r6.x - r13.x, r6.y - r13.y);
        int r25 = 5;
        int r26 = 0;
        double r7 = Math.hypot(r14.x - r122.x, r14.y - r122.y);
        double r10 = r3 / r32;
        double r23 = r3 / r7;
        double r33 = r15 / r32;
        double r16 = r15 / r7;
        if (r10 >= 1.35d) goto L18;
    L32:
        Bitmap r102 = r0;
        int r35 = r22;
    L38:
        Result r02 = null;
        Bitmap r62 = null;
        Rect[][] r72 = null;
        int r18 = r26;
        int r2 = r18;
        int r43 = r2;
    L40:
        if (r18 >= r20) goto L65;
        if (r18 != r35) goto L43;
        Matrix r36 = new Matrix();
        float r63 = r102.getWidth() / 2;
        float r73 = r102.getHeight() / 2;
        char r11 = CharCompanionObject.MIN_VALUE;
        r36.setRotate(1.0f, r63, r73);
        Matrix r152 = r36;
    L48:
        if (r152 == null) goto L50;
        Bitmap r37 = Bitmap.createBitmap(r102, 0, 0, r102.getWidth(), r102.getHeight(), r152, true);
    L51:
        r62 = Bitmap.createBitmap(r37.getWidth(), r37.getHeight(), Bitmap.Config.ALPHA_8);
        r72 = binarizeAndFindCharacters(r37, r62);
        if (r72 == null) goto L53;
        int r38 = r72.length;
        int r112 = r26;
    L55:
        if (r112 >= r38) goto L60;
        Rect[] r123 = r72[r112];
        r2 = Math.max(r123.length, r2);
        if (r123.length <= 0) goto L59;
        r43 = r43 + 1;
    L59:
        r112 = r112 + 1;
        goto L55
    L60:
        int r9 = 2;
        if (r43 < 2) goto L64;
        if (r2 < 30) goto L64;
    L66:
        if (r2 < 30) goto L186;
        if (r43 < r9) goto L186;
        Bitmap r19 = Bitmap.createBitmap(r72[r26].length * 10, r72.length * 15, Bitmap.Config.ALPHA_8);
        Canvas r39 = new Canvas(r19);
        Paint r103 = new Paint(2);
        int r124 = r26;
        Rect r113 = new Rect(r124, r124, 10, 15);
        int r125 = r72.length;
        int r132 = 0;
        int r142 = 0;
    L70:
        if (r132 >= r125) goto L75;
        Rect[] r153 = r72[r132];
        Result r342 = r02;
        int r03 = r153.length;
        int r92 = 0;
        int r182 = 0;
    L72:
        if (r92 >= r03) goto L74;
        Rect r44 = r153[r92];
        int r5 = r182 * 10;
        int r24 = r142 * 15;
        r113.set(r5, r24, r5 + 10, r24 + 15);
        r39.drawBitmap(r62, r44, r113, r103);
        r182 = r182 + 1;
        r92 = r92 + 1;
        r03 = r03;
        goto L72
    L74:
        r142 = r142 + 1;
        r132 = r132 + 1;
        r02 = r342;
        goto L70
    L75:
        Result r343 = r02;
        String r04 = performRecognition(r19, r72.length, r72[0].length, ApplicationLoader.applicationContext.getAssets());
        if (r04 != null) goto L78;
        return r343;
    L78:
        String[] r05 = TextUtils.split(r04, "\n");
        Result r27 = new Result();
        if (r05.length >= 2) goto L81;
        return r343;
    L81:
        if (r05[0].length() >= 30) goto L83;
        return r343;
    L83:
        if (r05[1].length() != r05[0].length()) goto L199;
        r27.rawMRZ = TextUtils.join("\n", r05);
        HashMap<String, String> r110 = getCountriesMap();
        char r310 = r05[0].charAt(0);
        if (r310 != 'P') goto L120;
        r27.type = 1;
        if (r05[0].length() != 44) goto L180;
        int r45 = r25;
        r27.issuingCountry = r05[0].substring(2, r45);
        int r311 = r05[0].indexOf("<<", r21);
        if (r311 == (-1)) goto L95;
        r27.lastName = r05[0].substring(r45, r311).replace(Typography.less, ' ').replace('0', 'O').trim();
        String r312 = r05[0].substring(r311 + 2).replace(Typography.less, ' ').replace('0', 'O').trim();
        r27.firstName = r312;
        if (r312.contains("   ") == false) goto L94;
        String r313 = r27.firstName;
        int r74 = 0;
        r27.firstName = r313.substring(0, r313.indexOf("   "));
    L96:
        String r314 = r05[1].substring(r74, 9).replace(Typography.less, ' ').replace('O', '0').trim();
        if (checksum(r314) != getNumber(r05[1].charAt(9))) goto L99;
        r27.number = r314;
    L99:
        r27.nationality = r05[1].substring(10, 13);
        String r315 = r05[1].substring(13, 19).replace('O', '0').replace('I', '1');
        if (checksum(r315) != getNumber(r05[1].charAt(19))) goto L102;
        parseBirthDate(r315, r27);
    L102:
        r27.gender = parseGender(r05[1].charAt(20));
        String r316 = r05[1].substring(21, 27).replace('O', '0').replace('I', '1');
        if (checksum(r316) != getNumber(r05[1].charAt(27))) goto L105;
    L106:
        parseExpiryDate(r316, r27);
    L108:
        if ("RUS".equals(r27.issuingCountry) == true) goto L110;
    L117:
        r27.firstName = r27.firstName.replace('8', 'B');
        r27.lastName = r27.lastName.replace('8', 'B');
    L118:
        r27.lastName = capitalize(r27.lastName);
        r27.firstName = capitalize(r27.firstName);
        r27.middleName = capitalize(r27.middleName);
        goto L180
    L110:
        if (r05[0].charAt(1) != 'N') goto L117;
        r27.type = 3;
        String[] r317 = r27.firstName.split(" ");
        r27.firstName = cyrillicToLatin(russianPassportTranslit(r317[0]));
        if (r317.length <= 1) goto L114;
        r27.middleName = cyrillicToLatin(russianPassportTranslit(r317[1]));
    L114:
        r27.lastName = cyrillicToLatin(russianPassportTranslit(r27.lastName));
        if (r27.number == null) goto L118;
        r27.number = r27.number.substring(0, 3) + r05[1].charAt(28) + r27.number.substring(3);
        goto L118
    L105:
        if (r05[1].charAt(27) != '<') goto L108;
    L94:
        r74 = 0;
        goto L96
    L95:
        r74 = 0;
    L180:
        if (TextUtils.isEmpty(r27.firstName) == true) goto L182;
    L184:
        r27.issuingCountry = r110.get(r27.issuingCountry);
        r27.nationality = r110.get(r27.nationality);
        return r27;
    L182:
        if (TextUtils.isEmpty(r27.lastName) == false) goto L184;
        return r343;
    L120:
        if (r310 == 'I') goto L127;
        if (r310 == 'A') goto L127;
        if (r310 == 'C') goto L127;
        return r343;
    L127:
        r27.type = 2;
        if (r05.length != 3) goto L148;
        if (r05[0].length() != 30) goto L148;
        if (r05[2].length() != 30) goto L148;
        r27.issuingCountry = r05[0].substring(2, 5);
        String r318 = r05[0].substring(5, 14).replace(Typography.less, ' ').replace('O', '0').trim();
        if (checksum(r318) != (r05[0].charAt(14) - '0')) goto L136;
        r27.number = r318;
    L136:
        String r319 = r05[1].substring(0, 6).replace('O', '0').replace('I', '1');
        if (checksum(r319) != getNumber(r05[1].charAt(6))) goto L139;
        parseBirthDate(r319, r27);
    L139:
        r27.gender = parseGender(r05[1].charAt(r17));
        String r320 = r05[1].substring(8, 14).replace('O', '0').replace('I', '1');
        if (checksum(r320) != getNumber(r05[1].charAt(14))) goto L142;
    L143:
        parseExpiryDate(r320, r27);
    L144:
        r27.nationality = r05[1].substring(15, 18);
        int r321 = r05[2].indexOf("<<");
        if (r321 == (-1)) goto L178;
        r27.lastName = r05[2].substring(0, r321).replace(Typography.less, ' ').trim();
        r27.firstName = r05[2].substring(r321 + 2).replace(Typography.less, ' ').trim();
    L178:
        r27.firstName = capitalize(r27.firstName.replace('0', 'O').replace('8', 'B'));
        r27.lastName = capitalize(r27.lastName.replace('0', 'O').replace('8', 'B'));
        goto L180
    L142:
        if (r05[1].charAt(14) != '<') goto L144;
    L148:
        if (r05.length != 2) goto L178;
        if (r05[0].length() != 36) goto L178;
        String r114 = r05[0].substring(2, 5);
        r27.issuingCountry = r114;
        if ("FRA".equals(r114) == true) goto L154;
    L164:
        int r322 = r05[0].indexOf("<<");
        if (r322 == (-1)) goto L167;
        r27.lastName = r05[0].substring(5, r322).replace(Typography.less, ' ').trim();
        r27.firstName = r05[0].substring(r322 + 2).replace(Typography.less, ' ').trim();
    L167:
        String r323 = r05[1].substring(0, 9).replace(Typography.less, ' ').replace('O', '0').trim();
        if (checksum(r323) != getNumber(r05[1].charAt(9))) goto L170;
        r27.number = r323;
    L170:
        r27.nationality = r05[1].substring(10, 13);
        String r324 = r05[1].substring(13, 19).replace('O', '0').replace('I', '1');
        if (checksum(r324) != getNumber(r05[1].charAt(19))) goto L173;
        parseBirthDate(r324, r27);
    L173:
        r27.gender = parseGender(r05[1].charAt(20));
        String r325 = r05[1].substring(21, 27).replace('O', '0').replace('I', '1');
        if (checksum(r325) != getNumber(r05[1].charAt(27))) goto L176;
    L177:
        parseExpiryDate(r325, r27);
        goto L178
    L176:
        if (r05[1].charAt(27) != '<') goto L178;
    L154:
        if (r310 != 'I') goto L164;
        if (r05[0].charAt(1) != 'D') goto L164;
        r27.nationality = "FRA";
        r27.lastName = r05[0].substring(5, 30).replace(Typography.less, ' ').trim();
        r27.firstName = r05[1].substring(13, 27).replace("<<", ", ").replace(Typography.less, ' ').trim();
        String r326 = r05[1].substring(0, 12).replace('O', '0');
        if (checksum(r326) != getNumber(r05[1].charAt(12))) goto L160;
        r27.number = r326;
    L160:
        String r327 = r05[1].substring(27, 33).replace('O', '0').replace('I', '1');
        if (checksum(r327) != getNumber(r05[1].charAt(33))) goto L163;
        parseBirthDate(r327, r27);
    L163:
        r27.gender = parseGender(r05[1].charAt(34));
        r27.doesNotExpire = true;
        goto L178
    L199:
        return r343;
    L186:
        return null;
    L64:
        r18 = r18 + 1;
        r35 = 1;
        r20 = 3;
        goto L40
    L53:
        return null;
    L50:
        r37 = r102;
        goto L51
    L43:
        if (r18 == 2) goto L46;
        r152 = null;
    L45:
        r11 = CharCompanionObject.MIN_VALUE;
        goto L48
    L46:
        Matrix r328 = new Matrix();
        r328.setRotate(-1.0f, r102.getWidth() / 2, r102.getHeight() / 2);
        r152 = r328;
        goto L45
    L65:
        r9 = 2;
        goto L66
    L18:
        if (r10 > 1.75d) goto L32;
        if (r33 < 1.35d) goto L32;
        if (r33 > 1.75d) goto L32;
        if (r23 < 1.35d) goto L32;
        if (r23 > 1.75d) goto L32;
        if (r16 < 1.35d) goto L32;
        if (r16 > 1.75d) goto L32;
        Bitmap r111 = Bitmap.createBitmap(1024, (int) Math.round(1024.0d / ((((r10 + r23) + r33) + r16) / 4.0d)), Bitmap.Config.ARGB_8888);
        Canvas r28 = new Canvas(r111);
        float[] r143 = {r13.x * r42, r13.y * r42, r122.x * r42, r122.y * r42, r14.x * r42, r14.y * r42, r6.x * r42, r6.y * r42};
        Matrix r272 = new Matrix();
        r272.setPolyToPoly(r143, 0, new float[]{0.0f, 0.0f, r111.getWidth(), 0.0f, r111.getWidth(), r111.getHeight(), 0.0f, r111.getHeight()}, 0, 4);
        r28.drawBitmap(r0, r272, new Paint(2));
        r0 = r111;
        goto L32
    L33:
        r22 = 1;
        r21 = 6;
        r20 = 3;
        r25 = 5;
        r26 = 0;
        r17 = 7;
        if (r0.getWidth() <= 1500) goto L36;
    L37:
        float r29 = 1500.0f / Math.max(r0.getWidth(), r0.getHeight());
        r35 = 1;
        r102 = Bitmap.createScaledBitmap(r0, Math.round(r0.getWidth() * r29), Math.round(r0.getHeight() * r29), true);
        goto L38
    L36:
        if (r0.getHeight() <= 1500) goto L32;
    L5:
        if (r0.getHeight() > 512) goto L8;
        r1 = r0;
        r4 = 1.0f;
        goto L9
    }

    private static String russianPassportTranslit(String r3) {
        char[] r32 = r3.toCharArray();
        int r0 = 0;
    L4:
        if (r0 >= r32.length) goto L10;
        int r1 = "ABVGDE2JZIQKLMNOPRSTUFHC34WXY9678".indexOf(r32[r0]);
        if (r1 == (-1)) goto L8;
        r32[r0] = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".charAt(r1);
    L8:
        r0 = r0 + 1;
        goto L4
    L10:
        return new String(r32);
    }

    private static native void setYuvBitmapPixels(Bitmap r0, byte[] r1);
}
