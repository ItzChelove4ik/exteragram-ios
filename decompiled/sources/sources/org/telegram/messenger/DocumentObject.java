package org.telegram.messenger;

import android.graphics.Paint;
import android.graphics.Path;
import java.util.ArrayList;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public class DocumentObject {

    public static class ThemeDocument extends TLRPC.TL_document {
        public Theme.ThemeAccent accent;
        public Theme.ThemeInfo baseTheme;
        public TLRPC.ThemeSettings themeSettings;
        public TLRPC.Document wallpaper;

        public ThemeDocument(TLRPC.ThemeSettings r3) {
            this.themeSettings = r3;
            Theme.ThemeInfo r0 = Theme.getTheme(Theme.getBaseThemeKey(r3));
            this.baseTheme = r0;
            this.accent = r0.createNewAccent(r3);
            TLRPC.WallPaper r32 = this.themeSettings.wallpaper;
            if ((r32 instanceof TLRPC.TL_wallPaper) == false) goto L6;
            TLRPC.Document r33 = ((TLRPC.TL_wallPaper) r32).document;
            this.wallpaper = r33;
            this.id = r33.id;
            this.access_hash = r33.access_hash;
            this.file_reference = r33.file_reference;
            this.user_id = r33.user_id;
            this.date = r33.date;
            this.file_name = r33.file_name;
            this.mime_type = r33.mime_type;
            this.size = r33.size;
            this.thumbs = r33.thumbs;
            this.version = r33.version;
            this.dc_id = r33.dc_id;
            this.key = r33.key;
            this.iv = r33.iv;
            this.attributes = r33.attributes;
            return;
        L6:
            this.id = -2147483648L;
            this.dc_id = Integer.MIN_VALUE;
        }
    }

    public DocumentObject() {
    }

    public static boolean containsPhotoSizeType(ArrayList<TLRPC.PhotoSize> r4, String r5) {
        if (r5 != null) goto L5;
        return false;
    L5:
        int r1 = r4.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        if (r5.equalsIgnoreCase(r4.get(r2).type) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    public static SvgHelper.SvgDrawable getCircleThumb(float r1, int r2, float r3) {
        return getCircleThumb(r1, r2, null, r3);
    }

    public static SvgHelper.SvgDrawable getCircleThumb(float r3, int r4, Theme.ResourcesProvider r5, float r6) {
        SvgHelper.SvgDrawable r52 = new SvgHelper.SvgDrawable();     // Catch: Exception -> L4
        SvgHelper.Circle r0 = new SvgHelper.Circle(256.0f, 256.0f, r3 * 512.0f);     // Catch: Exception -> L4
        r52.commands.add(r0);     // Catch: Exception -> L4
        r52.paints.put(r0, new Paint(1));     // Catch: Exception -> L4
        r52.width = 512;     // Catch: Exception -> L4
        r52.height = 512;     // Catch: Exception -> L4
        r52.setupGradient(r4, r6, false);     // Catch: Exception -> L4
        return r52;
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static SvgHelper.SvgDrawable getSvgRectThumb(int r6, float r7) {
        Path r0 = new Path();
        r0.addRect(0.0f, 0.0f, 512.0f, 512.0f, Path.Direction.CW);
        r0.close();
        SvgHelper.SvgDrawable r1 = new SvgHelper.SvgDrawable();
        r1.commands.add(r0);
        r1.paints.put(r0, new Paint(1));
        r1.width = 512;
        r1.height = 512;
        r1.setupGradient(r6, r7, false);
        return r1;
    }

    public static SvgHelper.SvgDrawable getSvgThumb(int r1, int r2, float r3) {
        SvgHelper.SvgDrawable r12 = SvgHelper.getDrawable(r1, Integer.valueOf(Opcodes.V_PREVIEW));
        if (r12 == null) goto L5;
        r12.setupGradient(r2, r3, false);
    L5:
        return r12;
    }

    public static SvgHelper.SvgDrawable getSvgThumb(ArrayList<TLRPC.PhotoSize> r1, int r2, float r3) {
        return getSvgThumb(r1, r2, r3, false);
    }

    public static SvgHelper.SvgDrawable getSvgThumb(ArrayList<TLRPC.PhotoSize> r9, int r10, float r11, boolean r12) {
        int r0 = r9.size();
        int r1 = 512;
        int r4 = 512;
        TLRPC.TL_photoPathSize r6 = null;
        int r5 = 0;
    L3:
        if (r5 >= r0) goto L12;
        TLRPC.PhotoSize r7 = r9.get(r5);
        if ((r7 instanceof TLRPC.TL_photoPathSize) == false) goto L8;
        r6 = (TLRPC.TL_photoPathSize) r7;
    L11:
        r5 = r5 + 1;
        goto L3
    L8:
        if ((r7 instanceof TLRPC.TL_photoSize) == false) goto L11;
        if (r12 == false) goto L11;
        r1 = r7.w;
        r4 = r7.h;
        goto L11
    L12:
        if (r6 == null) goto L19;
        if (r1 == 0) goto L19;
        if (r4 == 0) goto L19;
        SvgHelper.SvgDrawable r92 = SvgHelper.getDrawableByPath(r6.svgPath, r1, r4);
        if (r92 == null) goto L18;
        r92.setupGradient(r10, r11, false);
    L18:
        return r92;
    L19:
        return null;
    }

    public static SvgHelper.SvgDrawable getSvgThumb(TLRPC.Document r2, int r3, float r4) {
        return getSvgThumb(r2, r3, r4, 1.0f, null);
    }

    public static SvgHelper.SvgDrawable getSvgThumb(TLRPC.Document r7, int r8, float r9, float r10, Theme.ResourcesProvider r11) {
        if (r7 != null) goto L5;
        return null;
    L5:
        int r1 = r7.thumbs.size();
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L26;
        TLRPC.PhotoSize r4 = r7.thumbs.get(r3);
        if ((r4 instanceof TLRPC.TL_photoPathSize) == true) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        int r12 = r7.attributes.size();
        int r32 = 0;
    L10:
        if (r32 >= r12) goto L18;
        TLRPC.DocumentAttribute r5 = r7.attributes.get(r32);
        if ((r5 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L17;
        if ((r5 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L17;
        r32 = r32 + 1;
    L17:
        int r72 = r5.w;
        int r13 = r5.h;
    L19:
        if (r72 == 0) goto L26;
        if (r13 == 0) goto L26;
        SvgHelper.SvgDrawable r73 = SvgHelper.getDrawableByPath(((TLRPC.TL_photoPathSize) r4).svgPath, (int) (r72 * r10), (int) (r13 * r10));
        if (r73 == null) goto L24;
        r73.setupGradient(r8, r11, r9, false);
    L24:
        return r73;
    L18:
        r72 = 512;
        r13 = 512;
    L26:
        return null;
    }
}
