package org.telegram.messenger;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.exteragram.messenger.utils.text.ZalgoFilter;
import com.exteragram.messenger.utils.ui.FontUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketProtocol;
import org.telegram.messenger.CompoundEmoji;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.ColoredImageSpan;

/* loaded from: classes.dex */
public class Emoji {
    private static final String[] DEFAULT_RECENT = null;
    private static final int MAX_RECENT_EMOJI_COUNT = 48;
    public static int bigImgSize;
    public static int drawImgSize;
    private static final Bitmap[][] emojiBmp = null;
    public static final HashMap<String, String> emojiColor = null;
    private static final int[] emojiCounts = null;
    public static boolean emojiDrawingUseAlpha;
    public static float emojiDrawingYOffset;
    public static final HashMap<String, Integer> emojiUseHistory = null;
    private static boolean inited;
    public static final Runnable invalidateUiRunnable = null;
    private static final boolean[][] loadingEmoji = null;
    private static int memoryUsage;
    public static Paint placeholderPaint;
    public static final ArrayList<String> recentEmoji = null;
    private static boolean recentEmojiLoaded;
    public static int recentEmojiVersion;
    private static final HashMap<CharSequence, DrawableInfo> rects = null;

    public static class DrawableInfo {
        public int emojiIndex;
        public byte page;
        public short page2;

        public DrawableInfo(byte r1, short r2, int r3) {
            this.page = r1;
            this.page2 = r2;
            this.emojiIndex = r3;
        }
    }

    public static abstract class EmojiDrawable extends Drawable {
        public boolean fullSize;
        int placeholderColor;

        public EmojiDrawable() {
            this.fullSize = false;
            this.placeholderColor = 268435456;
        }

        public boolean isLoaded() {
            return false;
        }

        public void preload() {
        }
    }

    public static class EmojiSpan extends ImageSpan {
        public boolean drawn;
        public String emoji;
        public Paint.FontMetricsInt fontMetrics;
        public float lastDrawX;
        public float lastDrawY;
        private int minimumLineHeight;
        private boolean preserveFontMetrics;
        public float scale;
        public int size;

        public EmojiSpan(Drawable r1, int r2, Paint.FontMetricsInt r3) {
            super(r1, r2);
            this.scale = 1.0f;
            this.size = AndroidUtilities.dp(20.0f);
            this.fontMetrics = r3;
            if (r3 == null) goto L8;
            int r22 = Math.abs(r3.descent) + Math.abs(this.fontMetrics.ascent);
            this.size = r22;
            if (r22 != 0) goto L9;
            this.size = AndroidUtilities.dp(20.0f);
            return;
        L9:
            return;
        }

        private static void expandFontMetrics(Paint.FontMetricsInt r3, int r4) {
            int r0 = r3.descent;
            int r1 = r3.ascent;
            int r2 = r0 - r1;
            if (r4 > r2) goto L5;
            return;
        L5:
            int r42 = r4 - r2;
            int r22 = (r42 + 1) / 2;
            int r12 = r1 - r22;
            r3.ascent = r12;
            r3.descent = r0 + (r42 - r22);
            r3.top = Math.min(r3.top, r12);
            r3.bottom = Math.max(r3.bottom, r3.descent);
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas r11, CharSequence r12, int r13, int r14, float r15, int r16, int r17, int r18, Paint r19) {
            this.lastDrawX = ((this.scale * this.size) / 2.0f) + r15;
            this.lastDrawY = r16 + ((r18 - r16) / 2.0f);
            boolean r1 = true;
            this.drawn = true;
            if (r19.getAlpha() != 255) goto L5;
        L7:
            boolean r3 = false;
        L8:
            float r6 = Emoji.emojiDrawingYOffset;
            int r7 = this.size;
            float r62 = r6 - ((r7 - (this.scale * r7)) / 2.0f);
            if (r62 == 0.0f) goto L11;
            r11.save();
            r11.translate(0.0f, r62);
        L12:
            super.draw(r11, r12, r13, r14, r15, r16, r17, r18, r19);
            if (r1 == false) goto L15;
            r11.restore();
        L15:
            if (r3 == false) goto L18;
            getDrawable().setAlpha(255);
            return;
        L18:
            return;
        L11:
            r1 = false;
            goto L12
        L5:
            if (Emoji.emojiDrawingUseAlpha == false) goto L7;
            getDrawable().setAlpha(r19.getAlpha());
            r3 = true;
            goto L8
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L17:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L17;
            EmojiSpan r52 = (EmojiSpan) r5;
            if (Float.compare(this.scale, r52.scale) != 0) goto L17;
            if (this.size != r52.size) goto L17;
            if (Objects.equals(this.emoji, r52.emoji) == false) goto L17;
            return true;
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public int getSize(Paint r16, CharSequence r17, int r18, int r19, Paint.FontMetricsInt r20) {
            Paint.FontMetricsInt r0 = r20;
            if (this.preserveFontMetrics == false) goto L6;
            if (r0 == null) goto L6;
            boolean r1 = true;
        L7:
            if (r1 == false) goto L9;
            int r3 = r0.top;
        L10:
            if (r1 == false) goto L12;
            int r4 = r0.ascent;
        L13:
            if (r1 == false) goto L15;
            int r5 = r0.descent;
        L16:
            if (r1 == false) goto L18;
            int r6 = r0.bottom;
        L19:
            if (r1 == false) goto L21;
            int r7 = r0.leading;
        L22:
            if (r0 != null) goto L24;
            r0 = new Paint.FontMetricsInt();
        L24:
            Paint.FontMetricsInt r13 = r0;
            int r02 = (int) (this.scale * this.size);
            Paint.FontMetricsInt r14 = this.fontMetrics;
            if (r14 != null) goto L30;
            int r03 = super.getSize(r16, r17, r18, r19, r13);
            int r9 = AndroidUtilities.dp(8.0f);
            int r10 = AndroidUtilities.dp(10.0f);
            int r11 = (-r10) - r9;
            r13.top = r11;
            int r102 = r10 - r9;
            r13.bottom = r102;
            r13.ascent = r11;
            r13.leading = 0;
            r13.descent = r102;
            if (r1 == false) goto L29;
            r13.top = r3;
            r13.ascent = r4;
            r13.descent = r5;
            r13.bottom = r6;
            r13.leading = r7;
            expandFontMetrics(r13, this.minimumLineHeight);
        L29:
            return r03;
        L30:
            r13.ascent = r14.ascent;
            r13.descent = r14.descent;
            r13.top = r14.top;
            r13.bottom = r14.bottom;
            if (getDrawable() == null) goto L33;
            getDrawable().setBounds(0, 0, r02, r02);
        L33:
            if (r1 == false) goto L35;
            r13.top = r3;
            r13.ascent = r4;
            r13.descent = r5;
            r13.bottom = r6;
            r13.leading = r7;
            expandFontMetrics(r13, this.minimumLineHeight);
        L35:
            return r02;
        L21:
            r7 = 0;
            goto L22
        L18:
            r6 = 0;
            goto L19
        L15:
            r5 = 0;
            goto L16
        L12:
            r4 = 0;
            goto L13
        L9:
            r3 = 0;
        L6:
            r1 = false;
            goto L7
        }

        public void replaceFontMetrics(Paint.FontMetricsInt r2) {
            this.fontMetrics = r2;
            if (r2 == null) goto L8;
            int r22 = Math.abs(r2.descent) + Math.abs(this.fontMetrics.ascent);
            this.size = r22;
            if (r22 != 0) goto L9;
            this.size = AndroidUtilities.dp(20.0f);
            return;
        L9:
            return;
        }

        public void replaceFontMetrics(Paint.FontMetricsInt r1, int r2) {
            this.fontMetrics = r1;
            this.size = r2;
        }

        public EmojiSpan setMinimumLineHeight(int r1) {
            this.minimumLineHeight = r1;
            return this;
        }

        public EmojiSpan setPreserveFontMetrics(boolean r1) {
            this.preserveFontMetrics = r1;
            return this;
        }

        @Override // android.text.style.ReplacementSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r4) {
            if ((getDrawable() instanceof EmojiDrawable) == false) goto L5;
            EmojiDrawable r0 = (EmojiDrawable) getDrawable();
            r0.placeholderColor = 285212671 & r4.getColor();
        L5:
            super.updateDrawState(r4);
        }
    }

    public static class EmojiSpanRange {
        public CharSequence code;
        public int end;
        public int start;

        public EmojiSpanRange(int r1, int r2, CharSequence r3) {
            this.start = r1;
            this.end = r2;
            this.code = r3;
        }
    }

    public static class SimpleEmojiDrawable extends EmojiDrawable {
        private static Paint paint;
        private static Rect rect;
        private static final TextPaint textPaint = null;
        private DrawableInfo info;
        private boolean invert;

        static {
            paint = new Paint(2);
            rect = new Rect();
            textPaint = new TextPaint(1);
        }

        public SimpleEmojiDrawable(DrawableInfo r1, boolean r2) {
            this.info = r1;
            this.invert = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r15) {
            if (SharedConfig.useSystemEmoji == true) goto L9;
            if (isLoaded() == true) goto L9;
            DrawableInfo r0 = this.info;
            Emoji.m3995$$Nest$smloadEmoji(r0.page, r0.page2);
            Emoji.placeholderPaint.setColor(this.placeholderColor);
            Rect r14 = getBounds();
            r15.drawCircle(r14.centerX(), r14.centerY(), r14.width() * 0.4f, Emoji.placeholderPaint);
            return;
        L9:
            if (this.fullSize == false) goto L11;
            Rect r02 = getDrawRect();
        L13:
            if (SharedConfig.useSystemEmoji == false) goto L17;
            String[][] r1 = EmojiData.data;
            DrawableInfo r142 = this.info;
            String r2 = Emoji.fixEmoji(r1[r142.page][r142.emojiIndex]);
            TextPaint r7 = textPaint;
            r7.setTextSize(r02.height() * 0.8f);
            r7.setTypeface(FontUtils.getSystemEmojiTypeface());
            r15.drawText(r2, 0, r2.length(), r02.left, r02.bottom - (r02.height() * 0.225f), r7);
            return;
        L17:
            if (r15.quickReject(r02.left, r02.top, r02.right, r02.bottom, Canvas.EdgeType.AA) == false) goto L19;
            return;
        L19:
            if (this.invert == false) goto L21;
            r15.save();
            r15.scale(-1.0f, 1.0f, r02.centerX(), r02.centerY());
        L21:
            Bitmap[][] r152 = Emoji.m3994$$Nest$sfgetemojiBmp();
            DrawableInfo r22 = this.info;
            r15.drawBitmap(r152[r22.page][r22.page2], null, r02, paint);
            if (this.invert == false) goto L26;
            r15.restore();
            return;
        L26:
            return;
        L11:
            r02 = getBounds();
            goto L13
        }

        public Rect getDrawRect() {
            Rect r0 = getBounds();
            int r1 = r0.centerX();
            int r02 = r0.centerY();
            Rect r2 = rect;
            boolean r4 = this.fullSize;
            if (r4 == false) goto L5;
            int r3 = Emoji.bigImgSize;
        L6:
            r2.left = r1 - (r3 / 2);
            Rect r22 = rect;
            if (r4 == false) goto L9;
            int r32 = Emoji.bigImgSize;
        L10:
            r22.right = r1 + (r32 / 2);
            Rect r12 = rect;
            if (r4 == false) goto L13;
            int r23 = Emoji.bigImgSize;
        L14:
            r12.top = r02 - (r23 / 2);
            Rect r13 = rect;
            if (r4 == false) goto L17;
            int r42 = Emoji.bigImgSize;
        L18:
            r13.bottom = r02 + (r42 / 2);
            return rect;
        L17:
            r42 = Emoji.drawImgSize;
            goto L18
        L13:
            r23 = Emoji.drawImgSize;
            goto L14
        L9:
            r32 = Emoji.drawImgSize;
            goto L10
        L5:
            r3 = Emoji.drawImgSize;
            goto L6
        }

        public DrawableInfo getDrawableInfo() {
            return this.info;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // org.telegram.messenger.Emoji.EmojiDrawable
        public boolean isLoaded() {
            Bitmap[][] r0 = Emoji.m3994$$Nest$sfgetemojiBmp();
            DrawableInfo r2 = this.info;
            if (r0[r2.page][r2.page2] == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.Emoji.EmojiDrawable
        public void preload() {
            if (isLoaded() == true) goto L6;
            DrawableInfo r1 = this.info;
            Emoji.m3995$$Nest$smloadEmoji(r1.page, r1.page2);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            paint.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static /* synthetic */ void $r8$lambda$GJCd0wAjo_k4MPoC4ervNAm2qe0(byte r2, short r3) {
        Bitmap r0 = EmojiPack.getInstance().getEmoji(r2, r3);     // Catch: Exception -> L4
    L6:
        if (r0 == null) goto L8;
        emojiBmp[r2][r3] = r0;
        Runnable r02 = invalidateUiRunnable;
        AndroidUtilities.cancelRunOnUIThread(r02);
        AndroidUtilities.runOnUIThread(r02);
    L8:
        loadingEmoji[r2][r3] = false;
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        r0 = null;
        goto L6
    }

    /* renamed from: $r8$lambda$N2UxhO_RpBZMr-AKOXY3qveXL-I, reason: not valid java name */
    public static /* synthetic */ void m3992$r8$lambda$N2UxhO_RpBZMrAKOXY3qveXLI(Runnable r0, TLRPC.TL_messages_stickerSet r1) {
        r0.run();
    }

    /* renamed from: $r8$lambda$tEFea3Q0DiBPnoNT-35a881s2dE, reason: not valid java name */
    public static /* synthetic */ int m3993$r8$lambda$tEFea3Q0DiBPnoNT35a881s2dE(String r3, String r4) {
        Integer r1 = 0;
        HashMap<String, Integer> r2 = emojiUseHistory;
        Integer r32 = r2.get(r3);
        Integer r42 = r2.get(r4);
        if (r32 != null) goto L5;
        r32 = r1;
    L5:
        if (r42 == null) goto L9;
        r1 = r42;
    L9:
        if (r32.intValue() <= r1.intValue()) goto L13;
        return -1;
    L13:
        if (r32.intValue() >= r1.intValue()) goto L16;
        return 1;
    L16:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$yALq4hWv31dvrmzOOVfuL8C4HaQ() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.emojiLoaded, new Object[0]);
    }

    /* renamed from: -$$Nest$sfgetemojiBmp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap[][] m3994$$Nest$sfgetemojiBmp() {
        return emojiBmp;
    }

    /* renamed from: -$$Nest$smloadEmoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3995$$Nest$smloadEmoji(byte r0, short r1) {
        loadEmoji(r0, r1);
    }

    static {
        rects = new HashMap();
        inited = false;
        String[][] r1 = EmojiData.data;
        emojiCounts = new int[]{r1[0].length, r1[1].length, r1[2].length, r1[3].length, r1[4].length, r1[5].length, r1[6].length, r1[7].length};
        emojiBmp = new Bitmap[8][];
        loadingEmoji = new boolean[8][];
        emojiUseHistory = new HashMap();
        recentEmoji = new ArrayList();
        emojiColor = new HashMap();
        invalidateUiRunnable = new Emoji$$ExternalSyntheticLambda1();
        emojiDrawingUseAlpha = true;
        DEFAULT_RECENT = new String[]{"😂", "😘", "❤", "😍", "😊", "😁", "👍", "☺", "😔", "😄", "😭", "💋", "😒", "😳", "😜", "🙈", "😉", "😃", "😢", "😝", "😱", "😡", "😏", "😞", "😅", "😚", "🙊", "😌", "😀", "😋", "😆", "👌", "😐", "😕"};
        drawImgSize = AndroidUtilities.dp(20.0f);
        if (AndroidUtilities.isTablet() == false) goto L5;
        float r12 = 40.0f;
    L6:
        bigImgSize = AndroidUtilities.dp(r12);
        int r13 = 0;
    L7:
        Bitmap[][] r2 = emojiBmp;
        if (r13 >= r2.length) goto L10;
        int r3 = emojiCounts[r13];
        r2[r13] = new Bitmap[r3];
        loadingEmoji[r13] = new boolean[r3];
        r13 = r13 + 1;
        goto L7
    L10:
        int r14 = 0;
    L12:
        if (r14 >= EmojiData.data.length) goto L18;
        int r22 = 0;
    L14:
        String[] r32 = EmojiData.data[r14];
        if (r22 >= r32.length) goto L17;
        rects.put(r32[r22], new DrawableInfo((byte) r14, (short) r22, r22));
        r22 = r22 + 1;
        goto L14
    L17:
        r14 = r14 + 1;
        goto L12
    L18:
        Paint r15 = new Paint();
        placeholderPaint = r15;
        r15.setColor(0);
        return;
    L5:
        r12 = 34.0f;
        goto L6
    }

    public Emoji() {
    }

    public static void addRecentEmoji(String r4) {
        recentEmojiVersion++;
        HashMap<String, Integer> r0 = emojiUseHistory;
        Integer r1 = r0.get(r4);
        if (r1 != null) goto L6;
        r1 = 0;
    L6:
        if (r1.intValue() == 0) goto L8;
    L10:
        r0.put(r4, Integer.valueOf(r1.intValue() + 1));
        return;
    L8:
        if (r0.size() < 48) goto L10;
        ArrayList<String> r2 = recentEmoji;
        r0.remove(r2.get(r2.size() - 1));
        r2.set(r2.size() - 1, r4);
        goto L10
    }

    public static void clearRecentEmoji() {
        recentEmojiVersion++;
        MessagesController.getGlobalEmojiSettings().edit().putBoolean("filled_default", true).apply();
        emojiUseHistory.clear();
        recentEmoji.clear();
        saveRecentEmoji();
    }

    public static boolean endsWithRightArrow(CharSequence r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.length() > 2) goto L6;
        return false;
    L6:
        if (r2.charAt(r2.length() - 2) == 8205) goto L8;
        return false;
    L8:
        if (r2.charAt(r2.length() - 1) != 10145) goto L14;
        return true;
    L14:
        return false;
    }

    public static String fixEmoji(String r7) {
        int r0 = r7.length();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L47;
        char r3 = r7.charAt(r2);
        if (r3 < 55356) goto L37;
        if (r3 > 55358) goto L37;
        if (r3 == 55356) goto L10;
    L35:
        r2 = r2 + 1;
    L46:
        r2 = r2 + 1;
        goto L3
    L10:
        if (r2 >= (r0 - 1)) goto L35;
        int r32 = r2 + 1;
        char r5 = r7.charAt(r32);
        if (r5 != 56879) goto L14;
    L33:
        StringBuilder r33 = new StringBuilder();
        r2 = r2 + 2;
        r33.append(r7.substring(0, r2));
        r33.append("️");
        r33.append(r7.substring(r2));
        r7 = r33.toString();
    L34:
        r0 = r0 + 1;
        goto L46
    L14:
        if (r5 == 56324) goto L33;
        if (r5 == 56858) goto L33;
        if (r5 == 56703) goto L33;
        if (r5 == 57331) goto L33;
        if (r5 == 57131) goto L33;
        if (r5 == 56385) goto L33;
        if (r5 == 56693) goto L33;
        if (r5 == 57292) goto L33;
        if (r5 == 57291) goto L33;
        r2 = r32;
    L37:
        if (r3 == 8419) goto L47;
        if (r3 < '#') goto L46;
        if (r3 > 12953) goto L46;
        if (EmojiData.emojiToFE0FMap.containsKey(Character.valueOf(r3)) == false) goto L46;
        StringBuilder r34 = new StringBuilder();
        r2 = r2 + 1;
        r34.append(r7.substring(0, r2));
        r34.append("️");
        r34.append(r7.substring(r2));
        r7 = r34.toString();
    L47:
        return r7;
    }

    public static boolean fullyConsistsOfEmojis(CharSequence r2) {
        int[] r1 = new int[1];
        parseEmojis(r2, r1);
        if (r1[0] <= 0) goto L5;
        return true;
    L5:
        return false;
    }

    private static DrawableInfo getDrawableInfo(CharSequence r3) {
        if (endsWithRightArrow(r3) == false) goto L5;
        r3 = r3.subSequence(0, r3.length() - 2);
    L5:
        HashMap<CharSequence, DrawableInfo> r0 = rects;
        DrawableInfo r1 = r0.get(r3);
        if (r1 != null) goto L11;
        CharSequence r32 = EmojiData.emojiAliasMap.get(r3);
        if (r32 == null) goto L11;
        return r0.get(r32);
    L11:
        return r1;
    }

    public static Drawable getEmojiBigDrawable(String r4) {
        EmojiDrawable r0 = CompoundEmoji.getCompoundEmojiDrawable(r4);
        if (r0 == null) goto L5;
        int r3 = drawImgSize;
        r0.setBounds(0, 0, r3, r3);
    L6:
        if (r0 != null) goto L8;
        r0 = getEmojiDrawable(r4);
    L8:
        if (r0 != null) goto L12;
        CharSequence r42 = EmojiData.emojiAliasMap.get(r4);
        if (r42 == null) goto L12;
        r0 = getEmojiDrawable(r42);
    L12:
        if (r0 != null) goto L14;
        return null;
    L14:
        int r43 = bigImgSize;
        r0.setBounds(0, 0, r43, r43);
        r0.fullSize = true;
        return r0;
    L5:
        r0 = null;
        goto L6
    }

    public static EmojiDrawable getEmojiDrawable(CharSequence r3) {
        DrawableInfo r0 = getDrawableInfo(r3);
        if (r0 != null) goto L11;
        if (r3 == null) goto L9;
        CompoundEmoji.CompoundEmojiDrawable r32 = CompoundEmoji.getCompoundEmojiDrawable(r3.toString());
        if (r32 == null) goto L13;
        int r02 = drawImgSize;
        r32.setBounds(0, 0, r02, r02);
        return r32;
    L13:
        return null;
    L9:
        return null;
    L11:
        SimpleEmojiDrawable r2 = new SimpleEmojiDrawable(r0, endsWithRightArrow(r3));
        int r33 = drawImgSize;
        r2.setBounds(0, 0, r33, r33);
        return r2;
    }

    public static void invalidateAll(View r2) {
        if ((r2 instanceof ViewGroup) == false) goto L9;
        ViewGroup r22 = (ViewGroup) r2;
        int r0 = 0;
    L6:
        if (r0 >= r22.getChildCount()) goto L14;
        invalidateAll(r22.getChildAt(r0));
        r0 = r0 + 1;
        goto L6
    L14:
        return;
    L9:
        if ((r2 instanceof TextView) == false) goto L13;
        r2.invalidate();
        return;
    }

    public static boolean isValidEmoji(CharSequence r4) {
        if (TextUtils.isEmpty(r4) == false) goto L5;
        return false;
    L5:
        HashMap<CharSequence, DrawableInfo> r0 = rects;
        DrawableInfo r2 = r0.get(r4);
        if (r2 != null) goto L10;
        CharSequence r42 = EmojiData.emojiAliasMap.get(r4);
        if (r42 == null) goto L10;
        r2 = r0.get(r42);
    L10:
        if (r2 == null) goto L13;
        return true;
    L13:
        return false;
    }

    public static Bitmap loadBitmap(String r4) {
    L17:
        th = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L21;
        FileLog.e("Error loading emoji", th);
    L21:
        return null;
    L5:
        if (AndroidUtilities.density > 1.0f) goto L7;
        int r1 = 2;
    L26:
        InputStream r42 = ApplicationLoader.applicationContext.getAssets().open(r4);     // Catch: Throwable -> L13
        BitmapFactory.Options r2 = new BitmapFactory.Options();     // Catch: Throwable -> L13
        r2.inJustDecodeBounds = false;     // Catch: Throwable -> L13
        r2.inSampleSize = r1;     // Catch: Throwable -> L13
        Bitmap r12 = BitmapFactory.decodeStream(r42, null, r2);     // Catch: Throwable -> L13
        r42.close();     // Catch: Throwable -> L11
        return r12;
    L11:
        th = th;
    L15:
        FileLog.e(th);     // Catch: Throwable -> L17
        return r12;
    L13:
        th = th;
        r12 = null;
        goto L15
    L7:
        r1 = 1;
        goto L26
    }

    private static void loadEmoji(byte r2, short r3) {
        if (emojiBmp[r2][r3] != null) goto L9;
        boolean[] r0 = loadingEmoji[r2];
        if (r0[r3] == true) goto L10;
        r0[r3] = true;
        Utilities.globalQueue.postRunnable(new Emoji$$ExternalSyntheticLambda2(r2, r3));
        return;
    L10:
        return;
    }

    public static void loadRecentEmoji() {
        if (recentEmojiLoaded == true) goto L78;
        boolean r5 = true;
        recentEmojiLoaded = true;
        SharedPreferences r6 = MessagesController.getGlobalEmojiSettings();
        emojiUseHistory.clear();     // Catch: Exception -> L23
        if (r6.contains("emojis") == true) goto L62;
        boolean r16 = true;
        String r4 = r6.getString("emojis2", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L23
        if (r4 == null) goto L40;
        if (r4.length() <= 0) goto L40;
        String[] r42 = r4.split(",");     // Catch: Exception -> L23
        int r52 = r42.length;     // Catch: Exception -> L23
        int r8 = 0;
    L37:
        if (r8 >= r52) goto L40;
        String[] r9 = r42[r8].split("=");     // Catch: Exception -> L23
        emojiUseHistory.put(r9[0], Integer.valueOf(Utilities.parseInt(r9[1])));     // Catch: Exception -> L23
        r8 = r8 + 1;     // Catch: Exception -> L23
    L40:
        if (emojiUseHistory.isEmpty() == true) goto L42;
    L48:
        sortEmoji();     // Catch: Exception -> L23
    L66:
        String r0 = r6.getString("color", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L59
        if (r0 != null) goto L54;
        return;
    L54:
        if (r0.length() <= 0) goto L77;
        String[] r02 = r0.split(",");     // Catch: Exception -> L59
        int r2 = r02.length;     // Catch: Exception -> L59
        int r3 = 0;
    L56:
        if (r3 >= r2) goto L79;
        String[] r43 = r02[r3].split("=");     // Catch: Exception -> L59
        emojiColor.put(r43[0], r43[1]);     // Catch: Exception -> L59
        r3 = r3 + 1;
        goto L56
    L79:
        return;
    L77:
        return;
    L59:
        e = move-exception;
        FileLog.e(e);
        return;
    L42:
        if (r6.getBoolean("filled_default", false) == true) goto L48;
        int r44 = 0;
    L44:
        String[] r53 = DEFAULT_RECENT;     // Catch: Exception -> L23
        if (r44 >= r53.length) goto L47;
        emojiUseHistory.put(r53[r44], Integer.valueOf(r53.length - r44));     // Catch: Exception -> L23
        r44 = r44 + 1;     // Catch: Exception -> L23
        goto L44
    L47:
        r6.edit().putBoolean("filled_default", r16).apply();     // Catch: Exception -> L23
        saveRecentEmoji();     // Catch: Exception -> L23
        goto L48
    L62:
        String r82 = r6.getString("emojis", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L29
        if (r82 != null) goto L11;
    L31:
        r16 = r5;
        r6.edit().remove("emojis").apply();     // Catch: Exception -> L23
        saveRecentEmoji();     // Catch: Exception -> L23
        goto L40
    L11:
        if (r82.length() <= 0) goto L31;
        String[] r83 = r82.split(",");     // Catch: Exception -> L29
        int r92 = r83.length;     // Catch: Exception -> L29
        int r10 = 0;
    L13:
        if (r10 >= r92) goto L31;
        String[] r11 = r83[r10].split("=");     // Catch: Exception -> L29
        long r12 = Utilities.parseLong(r11[0]).longValue();     // Catch: Exception -> L29
        StringBuilder r14 = new StringBuilder();     // Catch: Exception -> L29
        boolean r162 = r5;
        int r15 = 0;
    L17:
        if (r15 >= 4) goto L26;
        r14.insert(0, (char) r12);     // Catch: Exception -> L23
        r12 = r12 >> 16;     // Catch: Exception -> L23
        if (r12 == 0) goto L26;
        r15 = r15 + 1;     // Catch: Exception -> L23
    L26:
        if (r14.length() <= 0) goto L28;
        emojiUseHistory.put(r14.toString(), Integer.valueOf(Utilities.parseInt(r11[r162 ? 1 : 0])));     // Catch: Exception -> L23
    L28:
        r10 = r10 + 1;     // Catch: Exception -> L23
        r5 = r162 ? 1 : 0;
    L29:
        e = e;
    L50:
        FileLog.e(e);
    L23:
        e = e;
        goto L50
    }

    public static ArrayList<EmojiSpanRange> parseEmojis(CharSequence r1) {
        return parseEmojis(r1, null);
    }

    public static ArrayList<EmojiSpanRange> parseEmojis(CharSequence r28, int[] r29) {
        ArrayList<EmojiSpanRange> r1 = new ArrayList();
        if (r28 != null) goto L5;
    L197:
        return r1;
    L5:
        if (r28.length() <= 0) goto L197;
        StringBuilder r2 = new StringBuilder(16);
        int r4 = r28.length();
        int r7 = -1;
        int[] r9 = r29;
        int r13 = -1;
        int r3 = 0;
        int r10 = 0;
        long r11 = 0;
        int r14 = 0;
        boolean r15 = false;
        boolean r17 = false;
    L8:
        if (r10 >= r4) goto L193;
        char r5 = r28.charAt(r10);     // Catch: Exception -> L30
        if (r5 < 55356) goto L15;
        if (r5 > 55358) goto L15;
    L24:
        if (r13 != r7) goto L26;
        r13 = r10;
    L28:
        r2.append(r5);     // Catch: Exception -> L30
        r14 = r14 + 1;     // Catch: Exception -> L30
        r11 = (r11 << 16) | r5;     // Catch: Exception -> L30
    L29:
        boolean r32 = false;
    L86:
        if (r17 == false) goto L118;
        int r6 = r10 + 2;     // Catch: Exception -> L30
        if (r6 >= r4) goto L118;
        int r72 = r10 + 1;     // Catch: Exception -> L30
        char r8 = r28.charAt(r72);     // Catch: Exception -> L30
        boolean r26 = r32;
        if (r8 != 55356) goto L97;
        char r33 = r28.charAt(r6);     // Catch: Exception -> L30
        if (r33 >= 57339) goto L94;
    L117:
        int r34 = r10;
    L119:
        int r73 = r34;
        int r62 = 0;
    L121:
        if (r62 >= 3) goto L168;
        int r82 = r73 + 1;     // Catch: Exception -> L30
        if (r82 >= r4) goto L166;
        char r102 = r28.charAt(r82);     // Catch: Exception -> L30
        int r27 = r34;
        if (r62 != 1) goto L132;
        if (r102 != 8205) goto L167;
        if (r2.length() <= 0) goto L167;
        r2.append(r102);     // Catch: Exception -> L30
        r14 = r14 + 1;     // Catch: Exception -> L30
        r73 = r82;
        r17 = false;
        r26 = false;
    L167:
        r62 = r62 + 1;     // Catch: Exception -> L30
        r34 = r27;
        goto L121
    L132:
        if (r5 == '*') goto L155;
        if (r5 == '#') goto L155;
        if (r5 < '0') goto L141;
        if (r5 <= '9') goto L155;
    L141:
        if (r13 == (-1)) goto L167;
        if (r102 < 65024) goto L167;
        if (r102 > 65039) goto L167;
        r14 = r14 + 1;     // Catch: Exception -> L30
        if (r17 == false) goto L149;
    L153:
        r73 = r82;
        goto L167
    L149:
        if ((r73 + 2) < r4) goto L151;
        boolean r35 = true;
    L152:
        r17 = r35;
        goto L153
    L151:
        r35 = false;
    L155:
        if (r102 < 65024) goto L167;
        if (r102 > 65039) goto L167;
        r14 = r14 + 1;     // Catch: Exception -> L30
        if (r17 == false) goto L161;
    L165:
        r73 = r82;
        r13 = r27;
        r15 = true;
        goto L167
    L161:
        if ((r73 + 2) < r4) goto L163;
        boolean r74 = true;
    L164:
        r17 = r74;
        goto L165
    L163:
        r74 = false;
        goto L164
    L166:
        r27 = r34;
        goto L167
    L168:
        int r272 = r34;
        if (r26 == false) goto L172;
        if (r9 == null) goto L172;
        r9[0] = 0;     // Catch: Exception -> L30
        r9 = null;
    L172:
        if (r17 == false) goto L182;
        int r36 = r73 + 2;     // Catch: Exception -> L30
        if (r36 >= r4) goto L182;
        int r52 = r73 + 1;     // Catch: Exception -> L30
        if (r28.charAt(r52) != 55356) goto L182;
        char r63 = r28.charAt(r36);     // Catch: Exception -> L30
        if (r63 < 57339) goto L182;
        if (r63 > 57343) goto L182;
        r2.append(r28.subSequence(r52, r73 + 3));     // Catch: Exception -> L30
        r14 = r14 + 2;     // Catch: Exception -> L30
        r73 = r36;
    L182:
        if (r17 == false) goto L191;
        if (r9 == null) goto L185;
        r9[0] = r9[0] + 1;     // Catch: Exception -> L30
    L185:
        if (r13 < 0) goto L189;
        int r142 = r14 + r13;     // Catch: Exception -> L30
        if (r142 > r4) goto L189;
        r1.add(new EmojiSpanRange(r13, r142, r2.subSequence(0, r2.length())));     // Catch: Exception -> L30
    L189:
        r2.setLength(0);     // Catch: Exception -> L30
        r13 = -1;
        r14 = 0;
        r15 = false;
        r17 = false;
    L191:
        r10 = r73 + 1;
        r3 = r272;
        r7 = -1;
        goto L8
    L94:
        if (r33 > 57343) goto L117;
        r2.append(r28.subSequence(r72, r10 + 3));     // Catch: Exception -> L30
        r14 = r14 + 2;     // Catch: Exception -> L30
        r34 = r6;
        goto L119
    L97:
        if (r2.length() < 2) goto L117;
        if (r2.charAt(0) != 55356) goto L117;
        if (r2.charAt(1) != 57332) goto L117;
        if (r8 != 56128) goto L117;
    L105:
        if (r72 >= r28.length()) goto L107;
        r2.append(r28.charAt(r72));     // Catch: Exception -> L30
    L107:
        int r64 = r72 + 1;     // Catch: Exception -> L30
        if (r64 >= r28.length()) goto L110;
        r2.append(r28.charAt(r64));     // Catch: Exception -> L30
    L110:
        r14 = r14 + 2;     // Catch: Exception -> L30
        int r65 = r72 + 2;     // Catch: Exception -> L30
        if (r65 >= r28.length()) goto L116;
        if (r28.charAt(r65) != 56128) goto L116;
        r72 = r65;
    L116:
        r10 = r72 + 1;     // Catch: Exception -> L30
    L118:
        r26 = r32;
        goto L117
    L26:
        if (r15 == false) goto L28;
        r13 = r10;
        r14 = 0;
        r15 = false;
    L15:
        if (r11 == 0) goto L33;
        if ((r11 & (-4294967296L)) != 0) goto L33;
        if ((r11 & WebSocketProtocol.PAYLOAD_SHORT_MAX) != 55356) goto L33;
        if (r5 < 56806) goto L33;
        if (r5 <= 56831) goto L24;
    L33:
        if (r2.length() > 0) goto L35;
    L40:
        if (r11 <= 0) goto L46;
        if ((61440 & r5) != 53248) goto L46;
    L43:
        r2.append(r5);     // Catch: Exception -> L30
        r14 = r14 + 1;     // Catch: Exception -> L30
        r11 = 0;
    L44:
        r17 = true;
    L46:
        if (r5 != 8419) goto L58;
        if (r10 <= 0) goto L29;
        char r75 = r28.charAt(r3);     // Catch: Exception -> L30
        if (r75 < '0') goto L53;
        if (r75 > '9') goto L53;
    L56:
        r14 = (r10 - r3) + 1;     // Catch: Exception -> L30
        r2.append(r75);     // Catch: Exception -> L30
        r2.append(r5);     // Catch: Exception -> L30
        r13 = r3;
        r17 = true;
        r15 = false;
    L53:
        if (r75 == '#') goto L56;
        if (r75 != '*') goto L29;
    L58:
        if (r5 == 169) goto L68;
        if (r5 == 174) goto L68;
        if (r5 < 8252) goto L75;
        if (r5 <= 12953) goto L68;
    L75:
        if (r13 == (-1)) goto L78;
        r2.setLength(0);     // Catch: Exception -> L30
        r32 = false;
        r13 = -1;
        r14 = 0;
        r15 = false;
        r17 = false;
        goto L86
    L78:
        if (r5 == 65039) goto L29;
        if (r5 == '\n') goto L29;
        if (r5 == ' ') goto L29;
        if (r5 == '\t') goto L29;
        r32 = true;
    L68:
        if (EmojiData.dataCharsMap.containsKey(Character.valueOf(r5)) == false) goto L75;
        if (r13 != (-1)) goto L72;
        r13 = r10;
    L74:
        r14 = r14 + 1;     // Catch: Exception -> L30
        r2.append(r5);     // Catch: Exception -> L30
        goto L44
    L72:
        if (r15 == false) goto L74;
        r13 = r10;
        r14 = 0;
        r15 = false;
        goto L74
    L35:
        if (r5 == 9792) goto L43;
        if (r5 == 9794) goto L43;
        if (r5 == 9877) goto L43;
    L30:
        e = move-exception;
        FileLog.e(e);
    L193:
        if (r9 == null) goto L197;
        if (r2.length() == 0) goto L197;
        r9[0] = 0;
        goto L197
    }

    public static void preloadEmoji(CharSequence r1) {
        DrawableInfo r12 = getDrawableInfo(r1);
        if (r12 == null) goto L6;
        loadEmoji(r12.page, r12.page2);
        return;
    }

    public static void removeRecentEmoji(String r2) {
        recentEmojiVersion++;
        HashMap<String, Integer> r0 = emojiUseHistory;
        r0.remove(r2);
        ArrayList<String> r1 = recentEmoji;
        r1.remove(r2);
        if (r0.isEmpty() == false) goto L5;
    L8:
        addRecentEmoji(DEFAULT_RECENT[0]);
        return;
    L5:
        if (r1.isEmpty() == true) goto L8;
    }

    public static CharSequence replaceEmoji(CharSequence r1, Paint.FontMetricsInt r2, boolean r3) {
        return replaceEmoji(r1, r2, r3, null);
    }

    public static CharSequence replaceEmoji(CharSequence r7, Paint.FontMetricsInt r8, boolean r9, float r10) {
        return replaceEmoji(r7, r8, r9, null, 0, r10, 0);
    }

    public static CharSequence replaceEmoji(CharSequence r1, Paint.FontMetricsInt r2, boolean r3, int[] r4) {
        return replaceEmoji(r1, r2, r3, r4, 0);
    }

    public static CharSequence replaceEmoji(CharSequence r7, Paint.FontMetricsInt r8, boolean r9, int[] r10, int r11) {
        return replaceEmoji(r7, r8, r9, r10, r11, 1.0f, 0);
    }

    public static CharSequence replaceEmoji(CharSequence r9, Paint.FontMetricsInt r10, boolean r11, int[] r12, int r13, float r14, int r15) {
        if (r9 != null) goto L4;
        return r9;
    L4:
        if (r9.length() == 0) goto L88;
        if (r11 == false) goto L8;
    L10:
        Spannable r112 = Spannable.Factory.getInstance().newSpannable(r9);
    L11:
        ArrayList<EmojiSpanRange> r122 = parseEmojis(r112, r12);
        if (r122.isEmpty() == true) goto L14;
        AnimatedEmojiSpan[] r92 = (AnimatedEmojiSpan[]) r112.getSpans(0, r112.length(), AnimatedEmojiSpan.class);
        ColoredImageSpan[] r0 = (ColoredImageSpan[]) r112.getSpans(0, r112.length(), ColoredImageSpan.class);
        if (SharedConfig.getDevicePerformanceClass() < 2) goto L18;
        int r2 = 100;
    L19:
        int r22 = r2 - r15;
        int r152 = 0;
    L21:
        if (r152 >= r122.size()) goto L68;
        EmojiSpanRange r3 = r122.get(r152);     // Catch: Exception -> L35
        if (r92 != null) goto L25;
    L38:
        if (r0 != null) goto L40;
    L52:
        EmojiDrawable r4 = getEmojiDrawable(r3.code);     // Catch: Exception -> L35
        if (r4 == null) goto L62;
        EmojiSpan r5 = new EmojiSpan(r4, r13, r10);     // Catch: Exception -> L35
        CharSequence r42 = r3.code;     // Catch: Exception -> L35
        if (r42 != null) goto L57;
        String r43 = null;
    L58:
        r5.emoji = r43;     // Catch: Exception -> L35
        r5.scale = r14;     // Catch: Exception -> L35
        r112.setSpan(r5, r3.start, r3.end, 33);     // Catch: Exception -> L35
        goto L62
    L57:
        r43 = r42.toString();     // Catch: Exception -> L35
    L62:
        if (Build.VERSION.SDK_INT < 29) goto L66;
        if ((r152 + 1) >= r22) goto L68;
    L66:
        r152 = r152 + 1;
        goto L21
    L40:
        if (r0.length <= 0) goto L52;
        int r44 = 0;
    L43:
        if (r44 >= r0.length) goto L52;
        ColoredImageSpan r52 = r0[r44];     // Catch: Exception -> L35
        if (r52 == null) goto L51;
        if (r112.getSpanStart(r52) != r3.start) goto L51;
        if (r112.getSpanEnd(r52) == r3.end) goto L66;
    L51:
        r44 = r44 + 1;     // Catch: Exception -> L35
        goto L43
    L25:
        if (r92.length <= 0) goto L38;
        int r45 = r92.length;     // Catch: Exception -> L35
        int r53 = 0;
    L27:
        if (r53 >= r45) goto L38;
        AnimatedEmojiSpan r6 = r92[r53];     // Catch: Exception -> L35
        if (r6 == null) goto L37;
        if (r112.getSpanStart(r6) != r3.start) goto L37;
        if (r112.getSpanEnd(r6) == r3.end) goto L66;
    L37:
        r53 = r53 + 1;     // Catch: Exception -> L35
    L35:
        e = move-exception;
        FileLog.e(e);
    L68:
        return ZalgoFilter.filterSpannable(r112);
    L18:
        r2 = 50;
        goto L19
    L14:
        return ZalgoFilter.filterSpannable(r9);
    L8:
        if ((r9 instanceof Spannable) == false) goto L10;
        r112 = (Spannable) r9;
        goto L11
    L88:
        return r9;
    }

    public static CharSequence replaceWithRestrictedEmoji(CharSequence r16, Paint.FontMetricsInt r17, int r18, Runnable r19) {
        if (SharedConfig.useSystemEmoji == true) goto L65;
        if (r16 == null) goto L65;
        if (r16.length() == 0) goto L65;
        int r3 = UserConfig.selectedAccount;
        TLRPC.TL_inputStickerSetShortName r5 = new TLRPC.TL_inputStickerSetShortName();
        r5.short_name = "RestrictedEmoji";
        MediaDataController r4 = MediaDataController.getInstance(r3);
        if (r19 != null) goto L11;
        Emoji$$ExternalSyntheticLambda3 r9 = null;
    L12:
        TLRPC.TL_messages_stickerSet r2 = r4.getStickerSet(r5, 0, false, true, r9);
        if ((r16 instanceof Spannable) == false) goto L15;
        Spannable r42 = (Spannable) r16;
    L16:
        ArrayList<EmojiSpanRange> r52 = parseEmojis(r42, null);
        if (r52.isEmpty() == false) goto L19;
        return r16;
    L19:
        AnimatedEmojiSpan[] r6 = (AnimatedEmojiSpan[]) r42.getSpans(0, r42.length(), AnimatedEmojiSpan.class);
        if (SharedConfig.getDevicePerformanceClass() < 2) goto L23;
        int r0 = 100;
    L22:
        int r7 = r0;
        int r8 = 0;
    L26:
        if (r8 >= r52.size()) goto L64;
        EmojiSpanRange r02 = r52.get(r8);     // Catch: Exception -> L39
        if (r6 == null) goto L42;
        int r92 = 0;
    L31:
        if (r92 >= r6.length) goto L42;
        AnimatedEmojiSpan r11 = r6[r92];     // Catch: Exception -> L39
        if (r11 == null) goto L41;
        if (r42.getSpanStart(r11) != r02.start) goto L41;
        if (r42.getSpanEnd(r11) != r02.end) goto L41;
    L63:
        r8 = r8 + 1;
    L41:
        r92 = r92 + 1;     // Catch: Exception -> L39
    L42:
        if (r2 == null) goto L48;
        ArrayList<TLRPC.Document> r93 = r2.documents;     // Catch: Exception -> L39
        int r112 = r93.size();     // Catch: Exception -> L39
        int r12 = 0;
    L44:
        if (r12 >= r112) goto L48;
        TLRPC.Document r13 = r93.get(r12);     // Catch: Exception -> L39
        r12 = r12 + 1;     // Catch: Exception -> L39
        TLRPC.Document r132 = r13;     // Catch: Exception -> L39
        if (MessageObject.findAnimatedEmojiEmoticon(r132, null).contains(r02.code) == false) goto L44;
    L49:
        if (r132 == null) goto L51;
        AnimatedEmojiSpan r94 = new AnimatedEmojiSpan(r132, r17);     // Catch: Exception -> L39
    L52:
        r94.emoji = r02.code.toString();     // Catch: Exception -> L39
        r94.cacheType = r18;     // Catch: Exception -> L56
        r42.setSpan(r94, r02.start, r02.end, 33);     // Catch: Exception -> L56
    L59:
        if (Build.VERSION.SDK_INT < 29) goto L63;
        if ((r8 + 1) < r7) goto L63;
    L56:
        e = e;
    L57:
        FileLog.e(e);
        goto L59
    L51:
        r94 = new AnimatedEmojiSpan(0, r17);     // Catch: Exception -> L39
    L48:
        r132 = null;
    L39:
        e = e;
    L64:
        return r42;
    L23:
        r0 = 50;
        goto L22
    L15:
        r42 = Spannable.Factory.getInstance().newSpannable(r16.toString());
        goto L16
    L11:
        r9 = new Emoji$$ExternalSyntheticLambda3(r19);
    L65:
        return r16;
    }

    public static CharSequence replaceWithRestrictedEmoji(CharSequence r1, Paint.FontMetricsInt r2, Runnable r3) {
        return replaceWithRestrictedEmoji(r1, r2, 20, r3);
    }

    public static CharSequence replaceWithRestrictedEmoji(CharSequence r0, TextView r1, Runnable r2) {
        return replaceWithRestrictedEmoji(r0, r1.getPaint().getFontMetricsInt(), r2);
    }

    public static void saveEmojiColors() {
        SharedPreferences r0 = MessagesController.getGlobalEmojiSettings();
        StringBuilder r1 = new StringBuilder();
        Iterator<Map.Entry<String, String>> r2 = emojiColor.entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        Map.Entry<String, String> r3 = r2.next();
        if (r1.length() == 0) goto L8;
        r1.append(",");
    L8:
        r1.append(r3.getKey());
        r1.append("=");
        r1.append(r3.getValue());
        goto L4
    L9:
        r0.edit().putString("color", r1.toString()).apply();
    }

    public static void saveRecentEmoji() {
        SharedPreferences r0 = MessagesController.getGlobalEmojiSettings();
        StringBuilder r1 = new StringBuilder();
        Iterator<Map.Entry<String, Integer>> r2 = emojiUseHistory.entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        Map.Entry<String, Integer> r3 = r2.next();
        if (r1.length() == 0) goto L8;
        r1.append(",");
    L8:
        r1.append(r3.getKey());
        r1.append("=");
        r1.append(r3.getValue());
        goto L4
    L9:
        r0.edit().putString("emojis2", r1.toString()).apply();
    }

    public static void sortEmoji() {
        recentEmojiVersion++;
        recentEmoji.clear();
        Iterator<Map.Entry<String, Integer>> r0 = emojiUseHistory.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        recentEmoji.add(r0.next().getKey());
        goto L4
    L6:
        Collections.sort(recentEmoji, new Emoji$$ExternalSyntheticLambda0());
    L7:
        ArrayList<String> r02 = recentEmoji;
        if (r02.size() <= 48) goto L10;
        r02.remove(r02.size() - 1);
        goto L7
    }
}
