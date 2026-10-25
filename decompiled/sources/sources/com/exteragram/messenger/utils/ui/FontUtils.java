package com.exteragram.messenger.utils.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.SystemFonts;
import android.os.Build;
import androidx.camera.core.streamsharing.StreamSharing$$ExternalSyntheticBackport0;
import com.google.android.gms.cast.MediaError;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;

/* loaded from: classes.dex */
public abstract class FontUtils {
    private static final int CANVAS_SIZE = 0;
    private static final Paint PAINT = null;
    private static final String TEST_TEXT = null;
    private static volatile Boolean italicSupported;
    public static boolean loadSystemEmojiFailed;
    private static volatile Boolean mediumWeightSupported;
    private static Typeface systemEmojiTypeface;
    private static Typeface systemGoogleSans;
    private static boolean systemGoogleSansLoaded;
    private static Typeface systemGoogleSansMedium;
    private static boolean systemGoogleSansMediumLoaded;
    private static volatile Boolean usePixelGoogleSans;

    static {
        int r0 = AndroidUtilities.dp(20.0f);
        CANVAS_SIZE = r0;
        loadSystemEmojiFailed = false;
        mediumWeightSupported = null;
        italicSupported = null;
        usePixelGoogleSans = null;
        Paint r3 = new Paint();
        PAINT = r3;
        r3.setTextSize(r0);
        r3.setAntiAlias(true);
        r3.setSubpixelText(false);
        r3.setFakeBoldText(false);
        String r02 = "en";
    L9:
        e = move-exception;
        FileLog.e(e);
    L12:
        if (FontUtils$$ExternalSyntheticBackport3.m("zh", "ja", "ko").contains(r02) == false) goto L15;
        TEST_TEXT = "你好";
    L30:
        systemGoogleSans = null;
        systemGoogleSansLoaded = false;
        systemGoogleSansMedium = null;
        systemGoogleSansMediumLoaded = false;
        return;
    L15:
        if (StreamSharing$$ExternalSyntheticBackport0.m("ar", "fa").contains(r02) == false) goto L18;
        TEST_TEXT = "مرحبا";
        goto L30
    L18:
        if ("iw".equals(r02) == false) goto L21;
        TEST_TEXT = "שלום";
        goto L30
    L21:
        if ("th".equals(r02) == false) goto L24;
        TEST_TEXT = "สวัสดี";
        goto L30
    L24:
        if ("hi".equals(r02) == false) goto L27;
        TEST_TEXT = "नमस्ते";
        goto L30
    L27:
        if (FontUtils$$ExternalSyntheticBackport4.m("ru", "uk", "ky", "be", "sr").contains(r02) == false) goto L29;
        TEST_TEXT = "Привет";
        goto L30
    L29:
        TEST_TEXT = "R";
        goto L30
    L4:
        if (LocaleController.getInstance() == null) goto L12;
        if (LocaleController.getInstance().getCurrentLocale() == null) goto L12;
        r02 = LocaleController.getInstance().getCurrentLocale().getLanguage();     // Catch: Exception -> L9
        goto L12
    }

    private static Typeface createWeightedSansTypeface(int r6, boolean r7) {
        int r2 = 1;
        if (r6 < 500) goto L19;
        if (r6 >= 700) goto L19;
        Typeface r0 = getSystemGoogleSansMedium();
        if (r0 == null) goto L19;
        if (r7 == false) goto L15;
        if (Build.VERSION.SDK_INT < 28) goto L13;
        return Typeface.create(r0, r6, true);
    L13:
        return Typeface.create(r0, 2);
    L15:
        if (Build.VERSION.SDK_INT >= 28) goto L17;
        return r0;
    L17:
        return Typeface.create(r0, r6, false);
    L19:
        Typeface r02 = getBaseSystemTypeface();
        if (Build.VERSION.SDK_INT >= 28) goto L22;
        if (r6 < 700) goto L28;
        if (r7 == false) goto L27;
        r2 = 3;
    L27:
        return Typeface.create(r02, r2);
    L28:
        if (r7 == true) goto L30;
        return r02;
    L30:
        return Typeface.create(r02, 2);
    L22:
        return Typeface.create(r02, r6, r7);
    }

    private static boolean differsFromRegular(Typeface r1, boolean r2) {
        return rendersDifferently(createWeightedSansTypeface(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, r2), r1);
    }

    private static Typeface getBaseSystemTypeface() {
        if (shouldUsePixelGoogleSans() == true) goto L7;
        return Typeface.create("sans-serif", 0);
    L7:
        if (systemGoogleSansLoaded == true) goto L21;
        systemGoogleSansLoaded = true;
        String[] r0 = {"google-sans-text", "google-sans"};
        int r3 = 0;
    L10:
        if (r3 >= 2) goto L17;
        String r4 = r0[r3];
        Typeface r5 = Typeface.create(r4, 0);
        if (rendersDifferently(r5, Typeface.DEFAULT) == true) goto L13;
        r3 = r3 + 1;
        goto L10
    L13:
        systemGoogleSans = r5;
        FileLog.d("system google sans alias = " + r4);
        return systemGoogleSans;
    L17:
        if (Build.VERSION.SDK_INT < 29) goto L21;
        File r02 = getGoogleSansFromSystemApi();
        if (r02 == null) goto L21;
        systemGoogleSans = Typeface.createFromFile(r02);
        FileLog.d("system google sans file = " + r02.getAbsolutePath());
    L21:
        Typeface r03 = systemGoogleSans;
        if (r03 == null) goto L25;
        return r03;
    L25:
        return Typeface.create("sans-serif", 0);
    }

    private static File getFontFallback() {
        String[] r0 = {"/system/fonts/SamsungColorEmoji.ttf", "/system/fonts/NotoColorEmoji.ttf", "/system/fonts/AndroidEmoji.ttf"};
        int r1 = 0;
    L4:
        if (r1 >= 3) goto L10;
        String r2 = r0[r1];
        File r3 = new File(r2);
        if (r3.exists() == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        FileLog.d("emoji font file fallback = " + r2);
        return r3;
    L10:
        return null;
    }

    public static Typeface getFontFromAssets(String r2) {
        if (Build.VERSION.SDK_INT < 26) goto L13;
        FontUtils$$ExternalSyntheticApiModelOutline1.m();
        Typeface.Builder r0 = FontUtils$$ExternalSyntheticApiModelOutline0.m(ApplicationLoader.applicationContext.getAssets(), r2);
        if (r2.contains("medium") == false) goto L8;
        r0.setWeight(700);
    L8:
        if (r2.contains("italic") == false) goto L11;
        r0.setItalic(true);
    L11:
        return r0.build();
    L13:
        return Typeface.createFromAsset(ApplicationLoader.applicationContext.getAssets(), r2);
    }

    private static File getFontFromFontsXml() {
        BufferedReader r0 = new BufferedReader(new FileReader("/system/etc/fonts.xml"));     // Catch: Exception -> L37
    L4:
        boolean r2 = false;
    L41:
        String r3 = r0.readLine();     // Catch: Throwable -> L12
        if (r3 == null) goto L30;
        String r32 = r3.trim();     // Catch: Throwable -> L12
        if (r32.startsWith("<family") == false) goto L15;
        if (r32.contains("ignore=\"true\"") == false) goto L15;
        r2 = true;
    L15:
        if (r32.startsWith("</family>") == true) goto L4;
        if (r32.startsWith("<font") == false) goto L41;
        if (r2 == true) goto L41;
        int r4 = r32.indexOf(">");     // Catch: Throwable -> L12
        int r5 = r32.indexOf("<", 1);     // Catch: Throwable -> L12
        if (r4 <= 0) goto L41;
        if (r5 <= 0) goto L41;
        String r33 = r32.substring(r4 + 1, r5);     // Catch: Throwable -> L12
        if (r33.toLowerCase().contains("emoji") == false) goto L41;
        File r42 = new File("/system/fonts/" + r33);     // Catch: Throwable -> L12
        if (r42.exists() == false) goto L41;
        FileLog.d("emoji font file fonts.xml = " + r33);     // Catch: Throwable -> L12
        r0.close();     // Catch: Exception -> L37
        return r42;
    L30:
        r0.close();     // Catch: Exception -> L37
        return null;
    L12:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L34
    L36:
        throw th;     // Catch: Exception -> L37
    L34:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L37
    L37:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private static File getFontFromSystemApi() {
        Iterator<Font> r1 = SystemFonts.getAvailableFonts().iterator();     // Catch: Exception -> L15
        File r2 = null;
    L4:
        if (r1.hasNext() == false) goto L17;
        File r3 = FontUtils$$ExternalSyntheticApiModelOutline2.m(r1.next()).getFile();     // Catch: Exception -> L15
        if (r3 == null) goto L4;
        String r4 = r3.getName().toLowerCase();     // Catch: Exception -> L15
        if (r4.contains("samsungcoloremoji") == true) goto L10;
        if (r2 != null) goto L4;
        if (r4.contains("emoji") == false) goto L4;
        r2 = r3;
        goto L4
    L10:
        return r3;
    L17:
        return r2;
    L15:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private static File getGoogleSansFromSystemApi() {
        Iterator<Font> r0 = SystemFonts.getAvailableFonts().iterator();     // Catch: Exception -> L24
    L3:
        if (r0.hasNext() == false) goto L47;
        File r1 = FontUtils$$ExternalSyntheticApiModelOutline2.m(r0.next()).getFile();     // Catch: Exception -> L24
        if (r1 == null) goto L3;
        String r2 = r1.getName().toLowerCase();     // Catch: Exception -> L24
        if (r2.contains("googlesanstext") == true) goto L16;
        if (r2.contains("google-sans-text") == true) goto L16;
        if (r2.contains("googlesans") == true) goto L16;
        if (r2.contains("google-sans") == false) goto L3;
    L16:
        if (r2.contains("medium") == true) goto L3;
        if (r2.contains("bold") == true) goto L3;
        if (r2.contains("italic") == true) goto L3;
        if (r2.contains("condensed") == true) goto L3;
        return r1;
    L47:
        return null;
    L24:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static File getSystemEmojiFontPath() {
        if (Build.VERSION.SDK_INT < 29) goto L7;
        File r0 = getFontFromSystemApi();
        if (r0 == null) goto L7;
        return r0;
    L7:
        File r02 = getFontFallback();
        if (r02 == null) goto L11;
        return r02;
    L11:
        return getFontFromFontsXml();
    }

    public static Typeface getSystemEmojiTypeface() {
        if (loadSystemEmojiFailed == true) goto L13;
        if (systemEmojiTypeface != null) goto L13;
        File r0 = getSystemEmojiFontPath();
        if (r0 == null) goto L10;
        systemEmojiTypeface = Typeface.createFromFile(r0);
    L10:
        if (systemEmojiTypeface != null) goto L13;
        loadSystemEmojiFailed = true;
    L13:
        return systemEmojiTypeface;
    }

    private static Typeface getSystemGoogleSansMedium() {
        if (shouldUsePixelGoogleSans() == true) goto L7;
        return null;
    L7:
        if (systemGoogleSansMediumLoaded == true) goto L17;
        systemGoogleSansMediumLoaded = true;
        String[] r0 = {"variable-title-medium-emphasized", "variable-title-medium"};
        int r2 = 0;
    L10:
        if (r2 >= 2) goto L17;
        String r3 = r0[r2];
        Typeface r4 = Typeface.create(r3, 0);
        if (rendersDifferently(r4, Typeface.DEFAULT) == true) goto L13;
        r2 = r2 + 1;
        goto L10
    L13:
        systemGoogleSansMedium = r4;
        FileLog.d("system google sans medium alias = " + r3);
        return systemGoogleSansMedium;
    L17:
        return systemGoogleSansMedium;
    }

    public static Typeface getSystemTypeface(String r4) {
        r4.getClass();
        char r3 = 65535;
        switch(r4.hashCode()) {
            case -1974412401: goto L30;
            case -424823932: goto L26;
            case 1326726365: goto L22;
            case 1500251732: goto L18;
            case 1689237634: goto L14;
            case 1794366221: goto L10;
            case 2025530182: goto L6;
            default: goto L34;
        };
    L6:
        if (r4.equals(AndroidUtilities.TYPEFACE_ROBOTO_EXTRA_BOLD) == false) goto L34;
        r3 = 6;
        goto L34
    L10:
        if (r4.equals(AndroidUtilities.TYPEFACE_ROBOTO_REGULAR) == false) goto L34;
        r3 = 5;
        goto L34
    L14:
        if (r4.equals(AndroidUtilities.TYPEFACE_ROBOTO_MONO) == false) goto L34;
        r3 = 4;
        goto L34
    L18:
        if (r4.equals(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM) == false) goto L34;
        r3 = 3;
        goto L34
    L22:
        if (r4.equals(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD) == false) goto L34;
        r3 = 2;
        goto L34
    L26:
        if (r4.equals(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM_ITALIC) == false) goto L34;
        r3 = 1;
        goto L34
    L30:
        if (r4.equals(AndroidUtilities.TYPEFACE_ROBOTO_ITALIC) == false) goto L34;
        r3 = 0;
    L34:
        switch(r3) {
            case 0: goto L50;
            case 1: goto L48;
            case 2: goto L46;
            case 3: goto L44;
            case 4: goto L42;
            case 5: goto L40;
            case 6: goto L38;
            default: goto L35;
        };
    L35:
        return null;
    L38:
        return resolveSansTypeface(800, false);
    L40:
        return resolveSansTypeface(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, false);
    L42:
        return Typeface.MONOSPACE;
    L44:
        return resolveSansTypeface(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, false);
    L46:
        return Typeface.create("sans-serif-condensed", 1);
    L48:
        return resolveSansTypeface(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, true);
    L50:
        return resolveSansTypeface(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, true);
    }

    private static boolean hasSimilarMetrics(Typeface r5, Typeface r6) {
        Paint r0 = new Paint();
        r0.setTextSize(100.0f);
        float[] r2 = new float[25];
        float[] r3 = new float[25];
        r0.setTypeface(r5);
        r0.getTextWidths("Hamburgefontsiv0123456789", r2);
        r0.setTypeface(r6);
        r0.getTextWidths("Hamburgefontsiv0123456789", r3);
        int r62 = 0;
    L3:
        if (r62 >= 25) goto L11;
        float r02 = r3[r62];
        if (r02 == 0.0f) goto L10;
        if ((Math.abs(r2[r62] - r02) / r3[r62]) > 0.01f) goto L10;
        r62 = r62 + 1;
    L10:
        return false;
    L11:
        return true;
    }

    private static boolean isGooglePixelDevice() {
        if ("google".equalsIgnoreCase(Build.MANUFACTURER) == false) goto L10;
        String r0 = Build.MODEL;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.toLowerCase(Locale.US).startsWith("pixel") == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public static boolean isItalicSupported() {
        if (italicSupported != null) goto L16;
        monitor-enter(FontUtils.class);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (italicSupported != null) goto L11;
        italicSupported = Boolean.valueOf(rendersDifferently(createWeightedSansTypeface(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, false), createWeightedSansTypeface(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, true)));     // Catch: Throwable -> L9
        FileLog.d("italicSupported = " + italicSupported);     // Catch: Throwable -> L9
    L11:
        monitor-exit(FontUtils.class);     // Catch: Throwable -> L9
    L16:
        return italicSupported.booleanValue();
    }

    public static boolean isMediumWeightSupported() {
        if (mediumWeightSupported != null) goto L16;
        monitor-enter(FontUtils.class);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (mediumWeightSupported != null) goto L11;
        mediumWeightSupported = Boolean.valueOf(supportsMediumWeight());     // Catch: Throwable -> L9
        FileLog.d("mediumWeightSupported = " + mediumWeightSupported);     // Catch: Throwable -> L9
    L11:
        monitor-exit(FontUtils.class);     // Catch: Throwable -> L9
    L16:
        return mediumWeightSupported.booleanValue();
    }

    private static boolean rendersDifferently(Typeface r7, Typeface r8) {
        Canvas r0 = new Canvas();
        int r1 = CANVAS_SIZE;
        Bitmap.Config r3 = Bitmap.Config.ARGB_8888;
        Bitmap r2 = Bitmap.createBitmap(r1 * 2, r1, r3);
        Bitmap r32 = Bitmap.createBitmap(r1 * 2, r1, r3);
        Paint r4 = PAINT;
        monitor-enter(r4);
        r0.setBitmap(r2);     // Catch: Throwable -> L8
        r4.setTypeface(r7);     // Catch: Throwable -> L8
        String r72 = TEST_TEXT;     // Catch: Throwable -> L8
        r0.drawText(r72, 0.0f, r1, r4);     // Catch: Throwable -> L8
        r0.setBitmap(r32);     // Catch: Throwable -> L8
        r4.setTypeface(r8);     // Catch: Throwable -> L8
        r0.drawText(r72, 0.0f, r1, r4);     // Catch: Throwable -> L8
        r4.setTypeface(null);     // Catch: Throwable -> L8
        monitor-exit(r4);     // Catch: Throwable -> L8
        boolean r73 = !r2.sameAs(r32);
        AndroidUtilities.recycleBitmaps(StreamSharing$$ExternalSyntheticBackport0.m(r2, r32));
        return r73;
    L8:
        th = move-exception;
        throw th;
    }

    private static Typeface resolveSansTypeface(int r6, boolean r7) {
        if (Build.VERSION.SDK_INT < 28) goto L9;
        Typeface r0 = createWeightedSansTypeface(r6, r7);
        if (r6 != 400) goto L7;
    L8:
        return r0;
    L7:
        if (differsFromRegular(r0, r7) == true) goto L8;
    L9:
        Typeface r02 = getBaseSystemTypeface();
        int r2 = 1;
        int r4 = 0;
        if (r6 < 800) goto L21;
        if (r7 == false) goto L13;
        r4 = 2;
    L13:
        Typeface r62 = Typeface.create("sans-serif-black", r4);
        if (differsFromRegular(r62, r7) == false) goto L16;
        return r62;
    L16:
        if (r7 == false) goto L19;
        r2 = 3;
    L19:
        return Typeface.create(r02, r2);
    L21:
        if (r6 >= 500) goto L23;
        if (r7 == false) goto L41;
        r4 = 2;
    L41:
        return Typeface.create(r02, r4);
    L23:
        if (r6 >= 700) goto L29;
        Typeface r63 = getSystemGoogleSansMedium();
        if (r63 == null) goto L29;
        if (differsFromRegular(r63, r7) == false) goto L29;
        return r63;
    L29:
        if (r7 == false) goto L31;
        r4 = 2;
    L31:
        Typeface r64 = Typeface.create("sans-serif-medium", r4);
        if (differsFromRegular(r64, r7) == false) goto L34;
        return r64;
    L34:
        if (r7 == false) goto L37;
        r2 = 3;
    L37:
        return Typeface.create(r02, r2);
    }

    private static boolean shouldUsePixelGoogleSans() {
        if (isGooglePixelDevice() == true) goto L6;
        return false;
    L6:
        if (usePixelGoogleSans != null) goto L19;
        monitor-enter(FontUtils.class);
    L12:
        th = move-exception;
        throw th;
    L10:
        if (usePixelGoogleSans != null) goto L14;
        usePixelGoogleSans = Boolean.valueOf(hasSimilarMetrics(Typeface.create("sans-serif", 0), getFontFromAssets(AndroidUtilities.TYPEFACE_ROBOTO_REGULAR)));     // Catch: Throwable -> L12
        FileLog.d("usePixelGoogleSans = " + usePixelGoogleSans);     // Catch: Throwable -> L12
    L14:
        monitor-exit(FontUtils.class);     // Catch: Throwable -> L12
    L19:
        return usePixelGoogleSans.booleanValue();
    }

    private static boolean supportsMediumWeight() {
        if (Build.VERSION.SDK_INT < 28) goto L9;
        if (differsFromRegular(createWeightedSansTypeface(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, false), false) == false) goto L9;
        return true;
    L9:
        return differsFromRegular(Typeface.create("sans-serif-medium", 0), false);
    }
}
