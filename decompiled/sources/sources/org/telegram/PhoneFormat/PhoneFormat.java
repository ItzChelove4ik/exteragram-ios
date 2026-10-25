package org.telegram.PhoneFormat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;

/* loaded from: classes.dex */
public class PhoneFormat {
    private static volatile PhoneFormat Instance;
    public ByteBuffer buffer;
    public HashMap<String, ArrayList<String>> callingCodeCountries;
    public HashMap<String, CallingCodeInfo> callingCodeData;
    public HashMap<String, Integer> callingCodeOffsets;
    public HashMap<String, String> countryCallingCode;
    public byte[] data;
    public String defaultCallingCode;
    public String defaultCountry;
    private boolean initialzed;

    static {
    }

    public PhoneFormat() {
        this.initialzed = false;
        init(null);
    }

    public static PhoneFormat getInstance() {
        PhoneFormat r0 = Instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(PhoneFormat.class);
        PhoneFormat r02 = Instance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new PhoneFormat();     // Catch: Throwable -> L9
        Instance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(PhoneFormat.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static String strip(String r3) {
        StringBuilder r0 = new StringBuilder(r3);
        int r32 = r0.length() - 1;
    L3:
        if (r32 < 0) goto L9;
        if ("0123456789+*#".contains(r0.substring(r32, r32 + 1)) == true) goto L7;
        r0.deleteCharAt(r32);
    L7:
        r32 = r32 - 1;
        goto L3
    L9:
        return r0.toString();
    }

    public static String stripExceptNumbers(String r1) {
        return stripExceptNumbers(r1, false);
    }

    public static String stripExceptNumbers(String r2, boolean r3) {
        if (r2 != null) goto L5;
        return null;
    L5:
        StringBuilder r0 = new StringBuilder(r2);
        if (r3 == false) goto L8;
        String r22 = "0123456789+";
    L9:
        int r32 = r0.length() - 1;
    L10:
        if (r32 < 0) goto L16;
        if (r22.contains(r0.substring(r32, r32 + 1)) == true) goto L14;
        r0.deleteCharAt(r32);
    L14:
        r32 = r32 - 1;
        goto L10
    L16:
        return r0.toString();
    L8:
        r22 = "0123456789";
        goto L9
    }

    public CallingCodeInfo callingCodeInfo(String r18) {
        PhoneFormat r0 = this;
        CallingCodeInfo r2 = r0.callingCodeData.get(r18);
        if (r2 != null) goto L32;
        Integer r3 = r0.callingCodeOffsets.get(r18);
        if (r3 == null) goto L32;
        byte[] r22 = r0.data;
        int r32 = r3.intValue();
        CallingCodeInfo r4 = new CallingCodeInfo();
        r4.callingCode = r18;
        r4.countries = r0.callingCodeCountries.get(r18);
        r0.callingCodeData.put(r18, r4);
        short r1 = r0.value16(r32);
        short r5 = r0.value16(r32 + 4);
        short r6 = r0.value16(r32 + 8);
        int r7 = r32 + 12;
        ArrayList<String> r8 = new ArrayList(5);
    L7:
        String r10 = r0.valueString(r7);
        boolean r12 = true;
        if (r10.length() == 0) goto L10;
        r8.add(r10);
        r7 = r7 + (r10.length() + 1);
        goto L7
    L10:
        r4.trunkPrefixes = r8;
        int r72 = r7 + 1;
        ArrayList<String> r82 = new ArrayList(5);
    L11:
        String r9 = r0.valueString(r72);
        if (r9.length() == 0) goto L14;
        r82.add(r9);
        r72 = r72 + (r9.length() + 1);
        goto L11
    L14:
        r4.intlPrefixes = r82;
        ArrayList<RuleSet> r73 = new ArrayList(r6);
        int r33 = r32 + r1;
        int r92 = r33;
        int r83 = 0;
    L15:
        if (r83 >= r6) goto L30;
        RuleSet r102 = new RuleSet();
        r102.matchLen = r0.value16(r92);
        short r11 = r0.value16(r92 + 2);
        r92 = r92 + 4;
        ArrayList<PhoneRule> r13 = new ArrayList(r11);
        int r14 = 0;
    L17:
        if (r14 >= r11) goto L29;
        PhoneRule r15 = new PhoneRule();
        r15.minVal = r0.value32(r92);
        r15.maxVal = r0.value32(r92 + 4);
        r15.byte8 = r22[r92 + 8];
        r15.maxLen = r22[r92 + 9];
        r15.otherFlag = r22[r92 + 10];
        r15.prefixLen = r22[r92 + 11];
        r15.flag12 = r22[r92 + 12];
        r15.flag13 = r22[r92 + 13];
        short r16 = r0.value16(r92 + 14);
        r92 = r92 + 16;
        String r17 = r0.valueString((r33 + r5) + r16);
        r15.format = r17;
        int r19 = r17.indexOf("[[");
        if (r19 == (-1)) goto L21;
        int r02 = r15.format.indexOf("]]");
        r15.format = String.format("%s%s", new Object[]{r15.format.substring(0, r19), r15.format.substring(r02 + 2)});
    L21:
        r13.add(r15);
        if (r15.hasIntlPrefix == false) goto L24;
        boolean r03 = true;
        r102.hasRuleWithIntlPrefix = true;
    L26:
        if (r15.hasTrunkPrefix == false) goto L28;
        r102.hasRuleWithTrunkPrefix = r03;
    L28:
        r14 = r14 + 1;
        r12 = r03;
        r0 = this;
        goto L17
    L24:
        r03 = true;
        goto L26
    L29:
        r102.rules = r13;
        r73.add(r102);
        r83 = r83 + 1;
        r0 = this;
        goto L15
    L30:
        r4.ruleSets = r73;
        return r4;
    L32:
        return r2;
    }

    public CallingCodeInfo findCallingCodeInfo(String r5) {
        CallingCodeInfo r0 = null;
        int r2 = 0;
    L4:
        if (r2 >= 3) goto L9;
        if (r2 >= r5.length()) goto L9;
        r2 = r2 + 1;
        r0 = callingCodeInfo(r5.substring(0, r2));
        if (r0 == null) goto L4;
    L9:
        return r0;
    }

    public String format(String r4) {
        if (this.initialzed == true) goto L30;
    L15:
        return r4;
    L30:
        String r1 = strip(r4);     // Catch: Exception -> L11
        if (r1.startsWith("+") == false) goto L13;
        String r12 = r1.substring(1);     // Catch: Exception -> L11
        CallingCodeInfo r3 = findCallingCodeInfo(r12);     // Catch: Exception -> L11
        if (r3 == null) goto L15;
        return "+" + r3.format(r12);
    L13:
        CallingCodeInfo r0 = callingCodeInfo(this.defaultCallingCode);     // Catch: Exception -> L11
        if (r0 == null) goto L15;
        String r2 = r0.matchingAccessCode(r1);     // Catch: Exception -> L11
        if (r2 == null) goto L27;
        String r02 = r1.substring(r2.length());     // Catch: Exception -> L11
        CallingCodeInfo r32 = findCallingCodeInfo(r02);     // Catch: Exception -> L11
        if (r32 == null) goto L22;
        r02 = r32.format(r02);     // Catch: Exception -> L11
    L22:
        if (r02.length() != 0) goto L25;
        return r2;
    L25:
        return String.format("%s %s", new Object[]{r2, r02});
    L27:
        return r0.format(r1);
    L11:
        e = move-exception;
        FileLog.e(e);
        return r4;
    }

    public void init(String r8) {
        ByteArrayOutputStream r0 = null;
        InputStream r1 = ApplicationLoader.applicationContext.getAssets().open("PhoneFormats.dat");     // Catch: Throwable -> L34 Exception -> L36
        ByteArrayOutputStream r2 = new ByteArrayOutputStream();     // Catch: Throwable -> L30 Exception -> L32
        byte[] r3 = new byte[1024];     // Catch: Throwable -> L10 Exception -> L12
    L7:
        int r5 = r1.read(r3, 0, 1024);     // Catch: Throwable -> L10 Exception -> L12
        if (r5 == (-1)) goto L14;
        r2.write(r3, 0, r5);     // Catch: Throwable -> L10 Exception -> L12
        goto L7
    L14:
        byte[] r02 = r2.toByteArray();     // Catch: Throwable -> L10 Exception -> L12
        this.data = r02;     // Catch: Throwable -> L10 Exception -> L12
        ByteBuffer r03 = ByteBuffer.wrap(r02);     // Catch: Throwable -> L10 Exception -> L12
        this.buffer = r03;     // Catch: Throwable -> L10 Exception -> L12
        r03.order(ByteOrder.LITTLE_ENDIAN);     // Catch: Throwable -> L10 Exception -> L12
        r2.close();     // Catch: Exception -> L17
    L71:
        r1.close();     // Catch: Exception -> L21
    L23:
        if (r8 != null) goto L25;
    L27:
        this.defaultCountry = Locale.getDefault().getCountry().toLowerCase();
    L28:
        this.callingCodeOffsets = new HashMap(255);
        this.callingCodeCountries = new HashMap(255);
        this.callingCodeData = new HashMap(10);
        this.countryCallingCode = new HashMap(255);
        parseDataHeader();
        this.initialzed = true;
        return;
    L25:
        if (r8.length() == 0) goto L27;
        this.defaultCountry = r8;
    L21:
        e = move-exception;
        FileLog.e(e);
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L71
    L12:
        e = e;
        r0 = r2;
    L38:
        e.printStackTrace();     // Catch: Throwable -> L30
        if (r0 != null) goto L63;
    L44:
        if (r1 == null) goto L81;
        r1.close();     // Catch: Exception -> L47
        return;
    L47:
        e = move-exception;
        FileLog.e(e);
        return;
    L81:
        return;
    L63:
        r0.close();     // Catch: Exception -> L42
    L42:
        e = move-exception;
        FileLog.e(e);
    L10:
        th = th;
        r0 = r2;
    L50:
        if (r0 != null) goto L74;
    L55:
        if (r1 == null) goto L83;
        r1.close();     // Catch: Exception -> L58
        throw th;
    L58:
        e = move-exception;
        FileLog.e(e);
        throw th;
    L83:
        throw th;
    L74:
        r0.close();     // Catch: Exception -> L53
    L53:
        e = move-exception;
        FileLog.e(e);
    L32:
        e = e;
    L30:
        th = th;
        goto L50
    L36:
        e = e;
        r1 = null;
    L34:
        th = th;
        r1 = null;
        goto L50
    }

    public void parseDataHeader() {
        int r0 = 0;
        int r1 = value32(0);
        int r3 = 4;
        int r2 = (r1 * 12) + 4;
    L3:
        if (r0 >= r1) goto L11;
        String r4 = valueString(r3);
        String r5 = valueString(r3 + 4);
        int r6 = value32(r3 + 8) + r2;
        r3 = r3 + 12;
        if (r5.equals(this.defaultCountry) == false) goto L7;
        this.defaultCallingCode = r4;
    L7:
        this.countryCallingCode.put(r5, r4);
        this.callingCodeOffsets.put(r4, Integer.valueOf(r6));
        ArrayList<String> r62 = this.callingCodeCountries.get(r4);
        if (r62 != null) goto L10;
        r62 = new ArrayList();
        this.callingCodeCountries.put(r4, r62);
    L10:
        r62.add(r5);
        r0 = r0 + 1;
        goto L3
    L11:
        String r02 = this.defaultCallingCode;
        if (r02 == null) goto L18;
        callingCodeInfo(r02);
        return;
    }

    public short value16(int r3) {
        if ((r3 + 2) > this.data.length) goto L6;
        this.buffer.position(r3);
        return this.buffer.getShort();
    L6:
        return 0;
    }

    public int value32(int r3) {
        if ((r3 + 4) > this.data.length) goto L6;
        this.buffer.position(r3);
        return this.buffer.getInt();
    L6:
        return 0;
    }

    public String valueString(int r5) {
        int r1 = r5;
    L18:
        byte[] r2 = this.data;     // Catch: Exception -> L12
        if (r1 >= r2.length) goto L15;
        if (r2[r1] == 0) goto L7;
        r1 = r1 + 1;
        goto L18
    L7:
        int r12 = r1 - r5;     // Catch: Exception -> L12
        if (r5 != r12) goto L10;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        return new String(r2, r5, r12);
    L15:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        e = move-exception;
        e.printStackTrace();
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
