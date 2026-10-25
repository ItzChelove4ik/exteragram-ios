package org.telegram.ui.Components.Paint;

import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.SystemFonts;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public class PaintTypeface {
    public static final List<PaintTypeface> BUILT_IN_FONTS = null;
    public static final PaintTypeface IMPACT = null;
    public static final PaintTypeface MW_BOLD = null;
    public static final PaintTypeface ROBOTO_CONDENSED = null;
    public static final PaintTypeface ROBOTO_ITALIC = null;
    public static final PaintTypeface ROBOTO_MEDIUM = null;
    public static final PaintTypeface ROBOTO_MONO = null;
    public static final PaintTypeface ROBOTO_SERIF = null;
    public static boolean loadingTypefaces;
    private static final List<String> preferable = null;
    private static List<PaintTypeface> typefaces;
    private final Font font;
    private final String key;
    private final LazyTypeface lazyTypeface;
    private final String name;
    private final String nameKey;
    private final Typeface typeface;

    public static class Family {
        String family;
        ArrayList<FontData> fonts;

        public Family() {
            this.fonts = new ArrayList();
        }

        public FontData getBold() {
            int r0 = 0;
        L4:
            if (r0 >= this.fonts.size()) goto L10;
            if ("Bold".equalsIgnoreCase(this.fonts.get(r0).subfamily) == true) goto L8;
            r0 = r0 + 1;
            goto L4
        L8:
            return this.fonts.get(r0);
        L10:
            return null;
        }

        public FontData getRegular() {
            int r1 = 0;
        L4:
            if (r1 >= this.fonts.size()) goto L9;
            if ("Regular".equalsIgnoreCase(this.fonts.get(r1).subfamily) == true) goto L7;
            r1 = r1 + 1;
            goto L4
        L7:
            FontData r12 = this.fonts.get(r1);
        L10:
            if (r12 == null) goto L12;
        L15:
            return r12;
        L12:
            if (this.fonts.isEmpty() == true) goto L15;
            return this.fonts.get(0);
        L9:
            r12 = null;
            goto L10
        }
    }

    public static class FontData {
        String family;
        Font font;
        String subfamily;

        public FontData() {
        }

        public String getName() {
            if ("Regular".equals(this.subfamily) == true) goto L10;
            if (TextUtils.isEmpty(this.subfamily) == true) goto L10;
            return this.family + " " + this.subfamily;
        L10:
            return this.family;
        }
    }

    public static class LazyTypeface {
        private final LazyTypefaceLoader loader;
        private Typeface typeface;

        public interface LazyTypefaceLoader {
            Typeface load();
        }

        public LazyTypeface(LazyTypefaceLoader r1) {
            this.loader = r1;
        }

        public Typeface get() {
            if (this.typeface != null) goto L6;
            this.typeface = this.loader.load();
        L6:
            return this.typeface;
        }
    }

    public static class NameRecord {
        final int encodingID;
        final int languageID;
        final int nameID;
        final int nameLength;
        final int platformID;
        final int stringOffset;

        public NameRecord(RandomAccessFile r2) {
            this.platformID = r2.readUnsignedShort();
            this.encodingID = r2.readUnsignedShort();
            this.languageID = r2.readUnsignedShort();
            this.nameID = r2.readUnsignedShort();
            this.nameLength = r2.readUnsignedShort();
            this.stringOffset = r2.readUnsignedShort();
        }

        public String read(RandomAccessFile r3, int r4) {
            r3.seek(r4 + this.stringOffset);
            byte[] r42 = new byte[this.nameLength];
            r3.read(r42);
            if (this.encodingID != 1) goto L5;
            Charset r2 = StandardCharsets.UTF_16BE;
        L7:
            return new String(r42, r2);
        L5:
            r2 = StandardCharsets.UTF_8;
            goto L7
        }
    }

    public static /* synthetic */ Typeface $r8$lambda$JUJgajfi8Ms7qhcTRcm9BaSm19E() {
        return AndroidUtilities.getTypeface("fonts/impact.ttf");
    }

    public static /* synthetic */ Typeface $r8$lambda$UY2m7Ss85E27X2Gvwc2kETPnlvc() {
        return AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD);
    }

    public static /* synthetic */ Typeface $r8$lambda$XOihfhSe9uc80bRvU8lQig7nnc0() {
        return AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_MERRIWEATHER_BOLD);
    }

    /* renamed from: $r8$lambda$dZgwVuJNRUqX-p5yU8D9RD4m_Tg, reason: not valid java name */
    public static /* synthetic */ Typeface m11353$r8$lambda$dZgwVuJNRUqXp5yU8D9RD4m_Tg() {
        return AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MONO);
    }

    public static /* synthetic */ Typeface $r8$lambda$nlmtI_jkm1Hh1rloOzDcVYgLUFE() {
        return AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM_ITALIC);
    }

    /* renamed from: $r8$lambda$uHiyhdBVm0IHOhLb-ZgqUwvUtTU, reason: not valid java name */
    public static /* synthetic */ void m11354$r8$lambda$uHiyhdBVm0IHOhLbZgqUwvUtTU(ArrayList r2) {
        typefaces = r2;
        loadingTypefaces = false;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.customTypefacesLoaded, new Object[0]);
    }

    /* renamed from: $r8$lambda$xF8pzPmoceFYrbPwhWPXQbF-yRo, reason: not valid java name */
    public static /* synthetic */ Typeface m11355$r8$lambda$xF8pzPmoceFYrbPwhWPXQbFyRo(Font r0) {
        return Typeface.createFromFile(r0.getFile());
    }

    /* renamed from: $r8$lambda$xegphmUkNCeVe-Ylwnj_t6QcROA, reason: not valid java name */
    public static /* synthetic */ Typeface m11356$r8$lambda$xegphmUkNCeVeYlwnj_t6QcROA() {
        return Typeface.create("serif", 1);
    }

    public static /* synthetic */ void $r8$lambda$ydR0iYIi4oyIZSgzx6sh8DoVZvI() {
        ArrayList r0 = new ArrayList(BUILT_IN_FONTS);
        if (Build.VERSION.SDK_INT < 29) goto L26;
        Iterator<Font> r1 = SystemFonts.getAvailableFonts().iterator();
        HashMap r2 = new HashMap();
    L6:
        if (r1.hasNext() == false) goto L16;
        Font r3 = r1.next();
        if (r3.getFile().getName().contains("Noto") == true) goto L6;
        FontData r32 = parseFont(r3);
        if (r32 == null) goto L6;
        Family r4 = (Family) r2.get(r32.family);
        if (r4 != null) goto L15;
        r4 = new Family();
        String r5 = r32.family;
        r4.family = r5;
        r2.put(r5, r4);
    L15:
        r4.fonts.add(r32);
        goto L6
    L16:
        Iterator<String> r12 = preferable.iterator();
    L18:
        if (r12.hasNext() == false) goto L26;
        Family r33 = (Family) r2.get(r12.next());
        if (r33 == null) goto L18;
        FontData r42 = r33.getBold();
        if (r42 != null) goto L24;
        r42 = r33.getRegular();
    L24:
        if (r42 == null) goto L18;
        r0.add(new PaintTypeface(r42.font, r42.getName()));
    L26:
        AndroidUtilities.runOnUIThread(new PaintTypeface$$ExternalSyntheticLambda8(r0));
    }

    static {
        PaintTypeface r0 = new PaintTypeface("roboto", "PhotoEditorTypefaceRoboto", new LazyTypeface(new PaintTypeface$$ExternalSyntheticLambda0()));
        ROBOTO_MEDIUM = r0;
        PaintTypeface r1 = new PaintTypeface("italic", "PhotoEditorTypefaceItalic", new LazyTypeface(new PaintTypeface$$ExternalSyntheticLambda1()));
        ROBOTO_ITALIC = r1;
        PaintTypeface r2 = new PaintTypeface("serif", "PhotoEditorTypefaceSerif", new LazyTypeface(new PaintTypeface$$ExternalSyntheticLambda2()));
        ROBOTO_SERIF = r2;
        PaintTypeface r3 = new PaintTypeface("condensed", "PhotoEditorTypefaceCondensed", new LazyTypeface(new PaintTypeface$$ExternalSyntheticLambda3()));
        ROBOTO_CONDENSED = r3;
        PaintTypeface r4 = new PaintTypeface("mono", "PhotoEditorTypefaceMono", new LazyTypeface(new PaintTypeface$$ExternalSyntheticLambda4()));
        ROBOTO_MONO = r4;
        PaintTypeface r6 = new PaintTypeface("mw_bold", "PhotoEditorTypefaceMerriweather", new LazyTypeface(new PaintTypeface$$ExternalSyntheticLambda5()));
        MW_BOLD = r6;
        PaintTypeface r5 = new PaintTypeface("impact", "PhotoEditorTypefaceImpact", new LazyTypeface(new PaintTypeface$$ExternalSyntheticLambda6()));
        IMPACT = r5;
        BUILT_IN_FONTS = Arrays.asList(new PaintTypeface[]{r0, r1, r2, r3, r4, r5, r6});
        preferable = Arrays.asList(new String[]{"Google Sans", "Dancing Script", "Carrois Gothic SC", "Cutive Mono", "Droid Sans Mono", "Coming Soon"});
    }

    public PaintTypeface(Font r2, String r3) {
        this.key = r3;
        this.name = r3;
        this.nameKey = null;
        this.typeface = null;
        this.lazyTypeface = new LazyTypeface(new PaintTypeface$$ExternalSyntheticLambda9(r2));
        this.font = r2;
    }

    public PaintTypeface(String r1, String r2, LazyTypeface r3) {
        this.key = r1;
        this.nameKey = r2;
        this.name = null;
        this.typeface = null;
        this.lazyTypeface = r3;
        this.font = null;
    }

    public static PaintTypeface find(String r5) {
        if (r5 != null) goto L5;
    L16:
        return null;
    L5:
        if (TextUtils.isEmpty(r5) == true) goto L16;
        List<PaintTypeface> r1 = get();
        int r2 = 0;
    L9:
        if (r2 >= r1.size()) goto L16;
        PaintTypeface r3 = r1.get(r2);
        if (r3 == null) goto L15;
        if (TextUtils.equals(r5, r3.key) == false) goto L15;
        return r3;
    L15:
        r2 = r2 + 1;
        goto L9
    }

    public static List<PaintTypeface> get() {
        List<PaintTypeface> r0 = typefaces;
        if (r0 != null) goto L6;
        load();
        return BUILT_IN_FONTS;
    L6:
        return r0;
    }

    private static void load() {
        if (typefaces == null) goto L5;
        return;
    L5:
        if (loadingTypefaces == true) goto L10;
        loadingTypefaces = true;
        Utilities.themeQueue.postRunnable(new PaintTypeface$$ExternalSyntheticLambda7());
        return;
    }

    public static FontData parseFont(Font r9) {
        RandomAccessFile r0 = null;
        if (r9 != null) goto L5;
        return null;
    L5:
        File r1 = r9.getFile();
        if (r1 != null) goto L59;
        return null;
    L59:
        RandomAccessFile r2 = new RandomAccessFile(r1, "r");     // Catch: Throwable -> L33 Exception -> L35
        int r12 = r2.readInt();     // Catch: Throwable -> L23 Exception -> L25
        if (r12 != 65536) goto L13;
    L16:
        int r13 = r2.readUnsignedShort();     // Catch: Throwable -> L23 Exception -> L25
        r2.skipBytes(6);     // Catch: Throwable -> L23 Exception -> L25
        int r3 = 0;
        int r4 = 0;
    L17:
        if (r4 >= r13) goto L48;
        int r5 = r2.readInt();     // Catch: Throwable -> L23 Exception -> L25
        r2.skipBytes(4);     // Catch: Throwable -> L23 Exception -> L25
        int r6 = r2.readInt();     // Catch: Throwable -> L23 Exception -> L25
        r2.readInt();     // Catch: Throwable -> L23 Exception -> L25
        if (r5 == 1851878757) goto L20;
        r4 = r4 + 1;
        goto L17
    L20:
        r2.seek(r6 + 2);     // Catch: Throwable -> L23 Exception -> L25
        int r14 = r2.readUnsignedShort();     // Catch: Throwable -> L23 Exception -> L25
        int r42 = r2.readUnsignedShort();     // Catch: Throwable -> L23 Exception -> L25
        HashMap r52 = new HashMap();     // Catch: Throwable -> L23 Exception -> L25
    L21:
        if (r3 >= r14) goto L27;
        NameRecord r7 = new NameRecord(r2);     // Catch: Throwable -> L23 Exception -> L25
        r52.put(Integer.valueOf(r7.nameID), r7);     // Catch: Throwable -> L23 Exception -> L25
        r3 = r3 + 1;     // Catch: Throwable -> L23 Exception -> L25
        goto L21
    L27:
        FontData r15 = new FontData();     // Catch: Throwable -> L23 Exception -> L25
        r15.font = r9;     // Catch: Throwable -> L23 Exception -> L25
        int r62 = r6 + r42;     // Catch: Throwable -> L23 Exception -> L25
        r15.family = parseString(r2, r62, (NameRecord) r52.get(1));     // Catch: Throwable -> L23 Exception -> L25
        r15.subfamily = parseString(r2, r62, (NameRecord) r52.get(2));     // Catch: Throwable -> L23 Exception -> L25
        r2.close();     // Catch: Exception -> L45
    L29:
        return r15;
    L48:
        r2.close();     // Catch: Exception -> L46
    L40:
        return null;
    L13:
        if (r12 == 1330926671) goto L16;
        r2.close();     // Catch: Exception -> L44
    L15:
        return null;
    L25:
        e = e;
    L37:
        FileLog.e(e);     // Catch: Throwable -> L23
        if (r2 == null) goto L40;
    L23:
        th = th;
        r0 = r2;
    L41:
        if (r0 != null) goto L55;
    L43:
        throw th;
    L55:
        r0.close();     // Catch: Exception -> L47
        goto L43
    L35:
        e = e;
        r2 = null;
    L33:
        th = th;
        goto L41
    }

    private static String parseString(RandomAccessFile r0, int r1, NameRecord r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        return r2.read(r0, r1);
    }

    public String getKey() {
        return this.key;
    }

    public String getName() {
        String r0 = this.name;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return LocaleController.getString(this.nameKey);
    }

    public Typeface getTypeface() {
        LazyTypeface r0 = this.lazyTypeface;
        if (r0 == null) goto L7;
        return r0.get();
    L7:
        return this.typeface;
    }
}
