package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import com.android.dx.io.Opcodes;
import java.io.File;
import okhttp3.internal.http.HttpStatusCodesKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class ThemePreviewDrawable extends BitmapDrawable {
    private DocumentObject.ThemeDocument themeDocument;

    /* renamed from: org.telegram.ui.Components.ThemePreviewDrawable$1, reason: invalid class name */
    public class AnonymousClass1 extends MessageDrawable {
        final /* synthetic */ SparseIntArray val$colors;

        public AnonymousClass1(int r1, boolean r2, boolean r3, SparseIntArray r4) {
            this.val$colors = r4;
            super(r1, r2, r3);
        }

        @Override // org.telegram.ui.ActionBar.MessageDrawable
        public int getColor(int r1) {
            return this.val$colors.get(r1);
        }

        @Override // org.telegram.ui.ActionBar.MessageDrawable
        public int getCurrentColor(int r1) {
            return this.val$colors.get(r1);
        }
    }

    public ThemePreviewDrawable(File r1, DocumentObject.ThemeDocument r2) {
        super(createPreview(r1, r2));
        this.themeDocument = r2;
    }

    private static Bitmap createPreview(File r29, DocumentObject.ThemeDocument r30) {
        new RectF();
        Paint r8 = new Paint();
        Bitmap r2 = Bitmaps.createBitmap(560, 678, Bitmap.Config.ARGB_8888);
        Canvas r5 = new Canvas(r2);
        SparseIntArray r6 = Theme.getThemeFileValues(null, r30.baseTheme.assetName, null);
        SparseIntArray r9 = r6.clone();
        r30.accent.fillAccentColors(r6, r9);
        int r62 = Theme.getPreviewColor(r9, Theme.key_actionBarDefault);
        int r10 = Theme.getPreviewColor(r9, Theme.key_actionBarDefaultIcon);
        int r11 = Theme.getPreviewColor(r9, Theme.key_chat_messagePanelBackground);
        int r12 = Theme.getPreviewColor(r9, Theme.key_chat_messagePanelIcons);
        int r13 = Theme.getPreviewColor(r9, Theme.key_chat_inBubble);
        int r14 = Theme.getPreviewColor(r9, Theme.key_chat_outBubble);
        int r17 = r9.get(Theme.key_chat_wallpaper);
        int r18 = r9.get(Theme.key_chat_wallpaper_gradient_to1);
        int r19 = r9.get(Theme.key_chat_wallpaper_gradient_to2);
        int r20 = r9.get(Theme.key_chat_wallpaper_gradient_to3);
        Integer r15 = Integer.valueOf(r9.get(Theme.key_chat_wallpaper_gradient_rotation));
        Drawable r3 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.preview_back).mutate();
        Theme.setDrawableColor(r3, r10);
        Drawable r4 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.preview_dots).mutate();
        Theme.setDrawableColor(r4, r10);
        Drawable r102 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.preview_smile).mutate();
        Theme.setDrawableColor(r102, r12);
        Drawable r22 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.preview_mic).mutate();
        Theme.setDrawableColor(r22, r12);
        int r122 = 2;
        MessageDrawable[] r7 = new MessageDrawable[2];
        int r42 = 0;
    L3:
        boolean r72 = true;
        if (r42 >= r122) goto L14;
        if (r42 != 1) goto L8;
    L7:
        Drawable r28 = r3;
        int r16 = r13;
        AnonymousClass1 r123 = new AnonymousClass1(2, r72, false, r9);
        r7[r42] = r123;
        if (r42 != 1) goto L12;
        int r32 = r14;
    L13:
        Theme.setDrawableColor(r123, r32);
        r42 = r42 + 1;
        r13 = r16;
        r3 = r28;
        r122 = 2;
        goto L3
    L12:
        r32 = r16;
        goto L13
    L8:
        r72 = false;
        goto L7
    L14:
        Drawable r282 = r3;
        if (r19 == 0) goto L17;
        int r33 = r17;
        int r43 = r18;
        MotionBackgroundDrawable r124 = new MotionBackgroundDrawable(r17, r18, r19, r20, true);
        BitmapDrawable r73 = null;
    L18:
        int r34 = AndroidUtilities.getPatternColor(AndroidUtilities.getAverageColor(r33, r43));
        if (r73 == null) goto L21;
        boolean r142 = false;
        r73.setBounds(0, 120, r2.getWidth(), r2.getHeight() - 120);
        r73.draw(r5);
    L22:
        if (r29 != null) goto L24;
        Bitmap r74 = null;
    L51:
        if (r74 != null) goto L54;
        if (r124 == null) goto L54;
        boolean r143 = false;
        r124.setBounds(0, 120, r2.getWidth(), r2.getHeight() - 120);
        r124.draw(r5);
    L55:
        r8.setColor(r62);
        boolean r24 = r143;
        r5.drawRect(0.0f, 0.0f, r2.getWidth(), 120.0f, r8);
        if (r282 == null) goto L58;
        int r44 = (120 - r282.getIntrinsicHeight()) / 2;
        r282.setBounds(13, r44, r282.getIntrinsicWidth() + 13, r282.getIntrinsicHeight() + r44);
        r282.draw(r5);
    L58:
        if (r4 == null) goto L60;
        int r0 = (r2.getWidth() - r4.getIntrinsicWidth()) - 10;
        int r45 = (120 - r4.getIntrinsicHeight()) / 2;
        r4.setBounds(r0, r45, r4.getIntrinsicWidth() + r0, r4.getIntrinsicHeight() + r45);
        r4.draw(r5);
    L60:
        r7[1].setBounds(161, Opcodes.ADD_INT_LIT8, r2.getWidth() - 20, HttpStatusCodesKt.HTTP_PERM_REDIRECT);
        r7[1].setTop(0, 560, 522, false, false);
        r7[1].draw(r5);
        r7[1].setBounds(161, 430, r2.getWidth() - 20, 522);
        r7[1].setTop(430, 560, 522, false, false);
        r7[1].draw(r5);
        r7[r24 ? 1 : 0].setBounds(20, 323, 399, 415);
        r7[r24 ? 1 : 0].setTop(323, 560, 522, false, false);
        r7[r24 ? 1 : 0].draw(r5);
        r8.setColor(r11);
        r5.drawRect(0.0f, r2.getHeight() - 120, r2.getWidth(), r2.getHeight(), r8);
        if (r102 == null) goto L63;
        int r1 = (r2.getHeight() - 120) + ((120 - r102.getIntrinsicHeight()) / 2);
        r102.setBounds(22, r1, r102.getIntrinsicWidth() + 22, r102.getIntrinsicHeight() + r1);
        r102.draw(r5);
    L63:
        if (r22 == null) goto L65;
        int r110 = (r2.getWidth() - r22.getIntrinsicWidth()) - 22;
        int r02 = (r2.getHeight() - 120) + ((120 - r22.getIntrinsicHeight()) / 2);
        r22.setBounds(r110, r02, r22.getIntrinsicWidth() + r110, r22.getIntrinsicHeight() + r02);
        r22.draw(r5);
    L65:
        return r2;
    L54:
        r143 = false;
        goto L55
    L24:
        if ("application/x-tgwallpattern".equals(r30.mime_type) == false) goto L27;
        Bitmap r03 = SvgHelper.getBitmap(r29, 560, 678, r142);
        float r162 = 678.0f;
        float r172 = 560.0f;
    L26:
        r74 = r03;
        if (r74 == null) goto L51;
        if (r124 == null) goto L46;
        r124.setPatternBitmap((int) (r30.accent.patternIntensity * 100.0f), r74);
        r124.setBounds(0, 120, r2.getWidth(), r2.getHeight() - 120);
        r124.draw(r5);
        goto L51
    L46:
        Paint r04 = new Paint(2);
        if (r30.accent.patternIntensity < 0.0f) goto L49;
        r04.setColorFilter(new PorterDuffColorFilter(r34, PorterDuff.Mode.SRC_IN));
    L49:
        r04.setAlpha(255);
        float r111 = Math.max(r172 / r74.getWidth(), r162 / r74.getHeight());
        int r35 = (int) (r74.getWidth() * r111);
        r5.save();
        r5.translate((560 - r35) / 2, (678 - ((int) (r74.getHeight() * r111))) / 2);
        r5.scale(r111, r111);
        r5.drawBitmap(r74, 0.0f, 0.0f, r04);
        r5.restore();
        goto L51
    L27:
        BitmapFactory.Options r46 = new BitmapFactory.Options();
        r46.inSampleSize = 1;
        r46.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(r29.getAbsolutePath(), r46);
        float r152 = r46.outWidth;
        r162 = 678.0f;
        float r75 = r46.outHeight;
        r172 = 560.0f;
        float r132 = Math.min(r152 / 560.0f, r75 / 678.0f);
        if (r132 >= 1.2f) goto L30;
        r132 = 1.0f;
    L30:
        r46.inJustDecodeBounds = false;
        if (r132 > 1.0f) goto L33;
    L41:
        r46.inSampleSize = (int) r132;
    L42:
        r03 = BitmapFactory.decodeFile(r29.getAbsolutePath(), r46);
        goto L26
    L33:
        if (r152 <= 560.0f) goto L35;
    L36:
        int r76 = 1;
    L37:
        int r144 = r76 * 2;
        if ((r76 * 4) >= r132) goto L39;
        r76 = r144;
        goto L37
    L39:
        r46.inSampleSize = r144;
        goto L42
    L35:
        if (r75 <= 678.0f) goto L41;
    L21:
        r142 = false;
        goto L22
    L17:
        r33 = r17;
        r43 = r18;
        r73 = BackgroundGradientDrawable.createDitheredGradientBitmapDrawable(r15.intValue(), new int[]{r33, r43}, r2.getWidth(), r2.getHeight() - 120);
        r124 = null;
        goto L18
    }
}
