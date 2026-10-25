package org.telegram.ui.iv;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.FormattedDateSpan;
import org.telegram.ui.Components.SquigglyLinesSpan;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanReplacement;

/* loaded from: classes7.dex */
public abstract class RichTextStyle {
    private static final int[] STYLE_FLAGS = null;

    public static class Run {
        RichInlineButtonSpan button;
        FormattedDateSpan date;
        long emojiDocId;
        int flags;
        String mathSource;
        String url;

        private Run() {
        }

        public /* synthetic */ Run(RichTextStyleIA r1) {
            this();
        }

        public boolean equals(Run r8) {
            RichInlineButtonSpan r0 = this.button;
            if (r0 == null) goto L5;
        L29:
            if (r0 != null) goto L31;
        L33:
            return false;
        L31:
            if (r0 != r8.button) goto L33;
            return true;
        L5:
            if (r8.button != null) goto L29;
            if (this.emojiDocId == 0) goto L10;
        L28:
            return false;
        L10:
            if (r8.emojiDocId != 0) goto L28;
            if (this.mathSource != null) goto L28;
            if (r8.mathSource != null) goto L28;
            if (this.flags != r8.flags) goto L28;
            String r02 = this.url;
            String r3 = r8.url;
            if (r02 != null) goto L24;
            if (r3 != null) goto L28;
        L26:
            if (this.date != r8.date) goto L28;
            return true;
        L24:
            if (r02.equals(r3) == false) goto L28;
            goto L28
        }
    }

    static {
        STYLE_FLAGS = new int[]{1, 2, 16, 8, 4, 256, 16384, 32768, 65536};
    }

    private static void append(SpannableStringBuilder r4, TL_iv.RichText r5, int r6, TL_iv.PageBlock r7, boolean r8) {
        if (r5 != null) goto L4;
        return;
    L4:
        if ((r5 instanceof TL_iv.textEmpty) == false) goto L7;
        return;
    L7:
        if ((r5 instanceof TL_iv.textConcat) == false) goto L12;
        ArrayList<TL_iv.RichText> r52 = ((TL_iv.textConcat) r5).texts;
        int r0 = r52.size();
        int r1 = 0;
    L9:
        if (r1 >= r0) goto L98;
        TL_iv.RichText r2 = r52.get(r1);
        r1 = r1 + 1;
        append(r4, r2, r6, r7, r8);
        goto L9
    L98:
        return;
    L12:
        if ((r5 instanceof TL_iv.textDiff) == false) goto L26;
        TL_iv.textDiff r53 = (TL_iv.textDiff) r5;
        boolean r02 = isEmpty(r53.text);
        boolean r22 = isEmpty(r53.old_text);
        int r3 = r4.length();
        if (r02 == false) goto L17;
        append(r4, r53.old_text, r6, r7, r8);
        setDiffStyle(r4, r3, 8192);
        return;
    L17:
        append(r4, r53.text, r6, r7, r8);
        if (r22 == false) goto L22;
        setDiffStyle(r4, r3, 4096);
        return;
    L22:
        if (r4.length() <= r3) goto L88;
        r4.setSpan(new SquigglyLinesSpan(), r3, r4.length(), 33);
        return;
    L88:
        return;
    L26:
        if ((r5 instanceof TL_iv.textPlain) == false) goto L30;
        appendLeaf(r4, ((TL_iv.textPlain) r5).text, r6, r7);
        return;
    L30:
        if ((r5 instanceof TL_iv.textCustomEmoji) == false) goto L43;
        TL_iv.textCustomEmoji r54 = (TL_iv.textCustomEmoji) r5;
        String r82 = r54.alt;
        if (r82 != null) goto L34;
    L37:
        CharSequence r83 = "😀";
    L38:
        int r03 = r4.length();
        r4.append(r83);
        AnimatedEmojiSpan r84 = new AnimatedEmojiSpan(r54.document_id, null);
        r84.cacheType = AnimatedEmojiDrawable.getCacheTypeForEnterView();
        r4.setSpan(r84, r03, r4.length(), 33);
        if (r6 == 0) goto L89;
        r4.setSpan(spanFor(r6, r7), r03, r4.length(), 33);
        return;
    L89:
        return;
    L34:
        if (r82.isEmpty() == true) goto L37;
        r83 = r54.alt;
        goto L38
    L43:
        if ((r5 instanceof TL_iv.textUrl) == false) goto L51;
        TL_iv.textUrl r55 = (TL_iv.textUrl) r5;
        int r04 = r4.length();
        append(r4, r55.text, r6, r7, r8);
        if (r4.length() <= r04) goto L90;
        String r56 = r55.url;
        if (r56 == null) goto L91;
        r4.setSpan(linkSpan(r56), r04, r4.length(), 33);
        return;
    L91:
        return;
    L90:
        return;
    L51:
        if ((r5 instanceof TL_iv.textDate) == false) goto L57;
        TL_iv.textDate r57 = (TL_iv.textDate) r5;
        int r05 = r4.length();
        append(r4, r57.text, r6, r7, r8);
        if (r4.length() <= r05) goto L92;
        r4.setSpan(dateSpan(r57, r4.subSequence(r05, r4.length()).toString()), r05, r4.length(), 33);
        return;
    L92:
        return;
    L57:
        if ((r5 instanceof TL_iv.textMath) == false) goto L71;
        TL_iv.textMath r58 = (TL_iv.textMath) r5;
        int r85 = r4.length();
        r4.append(" ");
        Object r06 = MathSpan.create(r58.source, Theme.getColor(Theme.key_windowBackgroundWhiteBlackText), AndroidUtilities.dp(SharedConfig.fontSize + 4));
        if (r06 == null) goto L61;
        r4.setSpan(r06, r85, r4.length(), 33);
    L66:
        if (r4.length() <= r85) goto L93;
        if (r6 == 0) goto L94;
        r4.setSpan(spanFor(r6, r7), r85, r4.length(), 33);
        return;
    L94:
        return;
    L93:
        return;
    L61:
        int r07 = r4.length();
        CharSequence r59 = r58.source;
        if (r59 != null) goto L64;
        r59 = _UrlKt.FRAGMENT_ENCODE_SET;
    L64:
        r4.replace(r85, r07, r59);
        goto L66
    L71:
        if ((r5 instanceof TL_iv.textButton) == false) goto L80;
        TL_iv.textButton r510 = (TL_iv.textButton) r5;
        int r08 = r4.length();
        append(r4, r510.text, r6, r7, r8);
        if (r8 == true) goto L75;
        return;
    L75:
        if (r4.length() > r08) goto L77;
        return;
    L77:
        if (RichInlineButtonSpan.isSupported(r510.type) == false) goto L97;
        r4.setSpan(new RichInlineButtonSpan(r510), r08, r4.length(), 33);
        return;
    L97:
        return;
    L80:
        int r09 = flagOf(r5);
        if (r09 == 0) goto L84;
        append(r4, r5.text, r6 | r09, r7, r8);
        return;
    L84:
        appendLeaf(r4, plainOf(r5), r6, r7);
    }

    private static void appendLeaf(SpannableStringBuilder r1, String r2, int r3, TL_iv.PageBlock r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L12;
        int r0 = r1.length();
        r1.append(r2);
        if (r3 == 0) goto L11;
        r1.setSpan(spanFor(r3, r4), r0, r1.length(), 33);
        return;
    L11:
        return;
    }

    private static void applyRun(Spannable r0, int r1, int r2, int r3, TL_iv.PageBlock r4) {
        if (r1 >= r2) goto L7;
        if (r3 == 0) goto L8;
        r0.setSpan(spanFor(r3, r4), r1, r2, 33);
        return;
    L8:
        return;
    }

    private static TL_iv.textCustomEmoji customEmojiNode(long r1, String r3) {
        TL_iv.textCustomEmoji r0 = new TL_iv.textCustomEmoji();
        r0.document_id = r1;
        if (r3 != null) goto L5;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        r0.alt = r3;
        return r0;
    }

    private static TL_iv.RichText dateNode(FormattedDateSpan r1, TL_iv.RichText r2) {
        TLRPC.TL_messageEntityFormattedDate r12 = r1.entity;
        TL_iv.textDate r0 = new TL_iv.textDate();
        r0.text = r2;
        r0.flags = r12.flags;
        r0.relative = r12.relative;
        r0.short_time = r12.short_time;
        r0.long_time = r12.long_time;
        r0.short_date = r12.short_date;
        r0.long_date = r12.long_date;
        r0.day_of_week = r12.day_of_week;
        r0.date = r12.date;
        return r0;
    }

    private static FormattedDateSpan dateSpan(TL_iv.textDate r2, String r3) {
        TLRPC.TL_messageEntityFormattedDate r0 = new TLRPC.TL_messageEntityFormattedDate();
        r0.flags = r2.flags;
        r0.date = r2.date;
        r0.applyFlags();
        TextStyleSpan.TextStyleRun r22 = new TextStyleSpan.TextStyleRun();
        r22.flags |= 128;
        return new FormattedDateSpan(r3, r22, r0);
    }

    public static int emojiOnlyCount(CharSequence r14) {
        if ((r14 instanceof Spanned) == true) goto L5;
    L38:
        return 0;
    L5:
        if (r14.length() == 0) goto L38;
        Spanned r0 = (Spanned) r14;
        ArrayList r2 = new ArrayList();
        AnimatedEmojiSpan[] r3 = (AnimatedEmojiSpan[]) r0.getSpans(0, r14.length(), AnimatedEmojiSpan.class);
        int r4 = r3.length;
        int r5 = 0;
    L8:
        if (r5 >= r4) goto L10;
        r2.add(r3[r5]);
        r5 = r5 + 1;
        goto L8
    L10:
        Emoji.EmojiSpan[] r42 = (Emoji.EmojiSpan[]) r0.getSpans(0, r14.length(), Emoji.EmojiSpan.class);
        int r52 = r42.length;
        int r6 = 0;
    L11:
        if (r6 >= r52) goto L23;
        Emoji.EmojiSpan r7 = r42[r6];
        int r8 = r0.getSpanStart(r7);
        int r9 = r0.getSpanEnd(r7);
        int r10 = r3.length;
        int r11 = 0;
    L13:
        if (r11 >= r10) goto L20;
        AnimatedEmojiSpan r12 = r3[r11];
        if (r0.getSpanStart(r12) != r8) goto L19;
        if (r0.getSpanEnd(r12) != r9) goto L19;
    L21:
        r6 = r6 + 1;
    L19:
        r11 = r11 + 1;
        goto L13
    L20:
        r2.add(r7);
        goto L21
    L23:
        if (r2.isEmpty() == false) goto L25;
        return 0;
    L25:
        int r32 = 0;
    L27:
        if (r32 >= r14.length()) goto L37;
        int r43 = r2.size();
        int r53 = 0;
    L29:
        if (r53 >= r43) goto L35;
        Object r62 = r2.get(r53);
        r53 = r53 + 1;
        if (r0.getSpanStart(r62) > r32) goto L29;
        if (r0.getSpanEnd(r62) <= r32) goto L29;
        r32 = r32 + 1;
        goto L27
    L35:
        return 0;
    L37:
        return r2.size();
    }

    private static int flagOf(TL_iv.RichText r1) {
        if ((r1 instanceof TL_iv.textBold) == false) goto L7;
        return 1;
    L7:
        if ((r1 instanceof TL_iv.textItalic) == false) goto L11;
        return 2;
    L11:
        if ((r1 instanceof TL_iv.textUnderline) == false) goto L15;
        return 16;
    L15:
        if ((r1 instanceof TL_iv.textStrike) == false) goto L19;
        return 8;
    L19:
        if ((r1 instanceof TL_iv.textFixed) == false) goto L23;
        return 4;
    L23:
        if ((r1 instanceof TL_iv.textSpoiler) == false) goto L27;
        return 256;
    L27:
        if ((r1 instanceof TL_iv.textSubscript) == false) goto L31;
        return 16384;
    L31:
        if ((r1 instanceof TL_iv.textSuperscript) == false) goto L35;
        return 32768;
    L35:
        if ((r1 instanceof TL_iv.textMarked) == false) goto L38;
        return 65536;
    L38:
        return 0;
    }

    private static int flagsBetween(Spanned r3, int r4, int r5) {
        TextStyleSpan[] r32 = (TextStyleSpan[]) r3.getSpans(r4, r5, TextStyleSpan.class);
        int r42 = r32.length;
        int r52 = 0;
        int r0 = 0;
    L3:
        if (r52 >= r42) goto L9;
        int r1 = r32[r52].getStyleFlags();
        if ((r1 & 512) == 0) goto L7;
        r1 = r1 | 256;
    L7:
        r0 = r0 | r1;
        r52 = r52 + 1;
        goto L3
    L9:
        return 114975 & r0;
    }

    public static TL_iv.RichText fromSpannable(CharSequence r11) {
        if (r11 != null) goto L5;
        int r1 = 0;
    L6:
        if (r1 != 0) goto L10;
        return new TL_iv.textEmpty();
    L10:
        if ((r11 instanceof Spanned) == false) goto L12;
        Spanned r2 = (Spanned) r11;
        ArrayList<TL_iv.RichText> r3 = new ArrayList();
        int r5 = 0;
        int r6 = 0;
        Run r7 = null;
    L14:
        if (r5 >= r1) goto L22;
        int r8 = r2.nextSpanTransition(r5, r1, CharacterStyle.class);
        Run r9 = runAt(r2, r5, r8);
        if (r7 != null) goto L19;
    L17:
        r7 = r9;
    L21:
        r5 = r8;
        goto L14
    L19:
        if (r7.equals(r9) == true) goto L21;
        r3.add(wrap(r11.subSequence(r6, r5).toString(), r7));
        r6 = r5;
        goto L17
    L22:
        String r112 = r11.subSequence(r6, r1).toString();
        if (r7 != null) goto L25;
        r7 = new Run(null);
    L25:
        r3.add(wrap(r112, r7));
        if (r3.size() == 1) goto L28;
        TL_iv.textConcat r113 = new TL_iv.textConcat();
        r113.texts = r3;
        return r113;
    L28:
        return r3.get(0);
    L12:
        return plainNode(r11.toString());
    L5:
        r1 = r11.length();
        goto L6
    }

    public static boolean hasDate(CharSequence r3, int r4, int r5) {
        if (r3 != null) goto L5;
        int r1 = 0;
    L6:
        int r42 = Math.max(0, Math.min(r4, r1));
        int r52 = Math.max(0, Math.min(r5, r1));
        if (r42 < r52) goto L9;
    L19:
        return false;
    L9:
        if ((r3 instanceof Spanned) == false) goto L19;
        Spanned r32 = (Spanned) r3;
    L12:
        if (r42 >= r52) goto L17;
        int r2 = r32.nextSpanTransition(r42, r52, FormattedDateSpan.class);
        if (((FormattedDateSpan[]) r32.getSpans(r42, r2, FormattedDateSpan.class)).length == 0) goto L15;
        r42 = r2;
        goto L12
    L15:
        return false;
    L17:
        return true;
    L5:
        r1 = r3.length();
        goto L6
    }

    public static boolean hasLink(CharSequence r3, int r4, int r5) {
        if (r3 != null) goto L5;
        int r1 = 0;
    L6:
        int r42 = Math.max(0, Math.min(r4, r1));
        int r52 = Math.max(0, Math.min(r5, r1));
        if (r42 < r52) goto L9;
    L19:
        return false;
    L9:
        if ((r3 instanceof Spanned) == false) goto L19;
        Spanned r32 = (Spanned) r3;
    L12:
        if (r42 >= r52) goto L17;
        int r2 = r32.nextSpanTransition(r42, r52, URLSpanReplacement.class);
        if (((URLSpanReplacement[]) r32.getSpans(r42, r2, URLSpanReplacement.class)).length == 0) goto L15;
        r42 = r2;
        goto L12
    L15:
        return false;
    L17:
        return true;
    L5:
        r1 = r3.length();
        goto L6
    }

    public static boolean hasStyle(CharSequence r2, int r3, int r4, int r5) {
        if (r2 != null) goto L5;
        int r1 = 0;
    L6:
        int r32 = Math.max(0, Math.min(r3, r1));
        int r42 = Math.max(0, Math.min(r4, r1));
        if (r32 < r42) goto L9;
    L19:
        return false;
    L9:
        if ((r2 instanceof Spanned) == false) goto L19;
        Spanned r22 = (Spanned) r2;
    L12:
        if (r32 >= r42) goto L17;
        int r12 = r22.nextSpanTransition(r32, r42, TextStyleSpan.class);
        if ((flagsBetween(r22, r32, r12) & r5) == 0) goto L15;
        r32 = r12;
        goto L12
    L15:
        return false;
    L17:
        return true;
    L5:
        r1 = r2.length();
        goto L6
    }

    public static boolean isEmpty(TL_iv.RichText r5) {
        if (r5 != null) goto L5;
    L46:
        return true;
    L5:
        if ((r5 instanceof TL_iv.textEmpty) == true) goto L46;
        if ((r5 instanceof TL_iv.textPlain) == false) goto L17;
        String r52 = ((TL_iv.textPlain) r5).text;
        if (r52 != null) goto L12;
    L15:
        return true;
    L12:
        if (r52.isEmpty() == true) goto L15;
        return false;
    L17:
        if ((r5 instanceof TL_iv.textCustomEmoji) == false) goto L20;
        return false;
    L20:
        if ((r5 instanceof TL_iv.textMath) == false) goto L29;
        String r53 = ((TL_iv.textMath) r5).source;
        if (r53 != null) goto L24;
    L27:
        return true;
    L24:
        if (r53.isEmpty() == true) goto L27;
        return false;
    L29:
        if ((r5 instanceof TL_iv.textConcat) == false) goto L37;
        ArrayList<TL_iv.RichText> r54 = ((TL_iv.textConcat) r5).texts;
        int r1 = r54.size();
        int r3 = 0;
    L31:
        if (r3 >= r1) goto L35;
        TL_iv.RichText r4 = r54.get(r3);
        r3 = r3 + 1;
        if (isEmpty(r4) == true) goto L31;
        return false;
    L35:
        return true;
    L37:
        if ((r5 instanceof TL_iv.textDiff) == false) goto L45;
        TL_iv.textDiff r55 = (TL_iv.textDiff) r5;
        if (isEmpty(r55.text) == true) goto L41;
    L43:
        return false;
    L41:
        if (isEmpty(r55.old_text) == false) goto L43;
        return true;
    L45:
        return isEmpty(r5.text);
    }

    public static URLSpanReplacement linkSpan(String r2) {
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags = 1024;
        return new URLSpanReplacement(r2, r0);
    }

    private static TL_iv.textPlain plainNode(String r1) {
        TL_iv.textPlain r0 = new TL_iv.textPlain();
        r0.text = r1;
        return r0;
    }

    public static String plainOf(TL_iv.RichText r4) {
        if (r4 != null) goto L5;
    L39:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        if ((r4 instanceof TL_iv.textEmpty) == true) goto L39;
        if ((r4 instanceof TL_iv.textPlain) == false) goto L14;
        String r42 = ((TL_iv.textPlain) r4).text;
        if (r42 != null) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return r42;
    L14:
        if ((r4 instanceof TL_iv.textCustomEmoji) == false) goto L20;
        String r43 = ((TL_iv.textCustomEmoji) r4).alt;
        if (r43 != null) goto L18;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        return r43;
    L20:
        if ((r4 instanceof TL_iv.textMath) == false) goto L24;
        return " ";
    L24:
        if ((r4 instanceof TL_iv.textConcat) == false) goto L31;
        StringBuilder r0 = new StringBuilder();
        ArrayList<TL_iv.RichText> r44 = ((TL_iv.textConcat) r4).texts;
        int r1 = r44.size();
        int r2 = 0;
    L26:
        if (r2 >= r1) goto L29;
        TL_iv.RichText r3 = r44.get(r2);
        r2 = r2 + 1;
        r0.append(plainOf(r3));
        goto L26
    L29:
        return r0.toString();
    L31:
        if ((r4 instanceof TL_iv.textDiff) == false) goto L38;
        TL_iv.textDiff r45 = (TL_iv.textDiff) r4;
        String r02 = plainOf(r45.text);
        if (isEmpty(r45.text) == true) goto L35;
        return r02;
    L35:
        return plainOf(r45.old_text);
    L38:
        return plainOf(r4.text);
    }

    public static void removeDate(Spannable r2, int r3, int r4) {
        int r0 = r2.length();
        int r1 = 0;
        int r32 = Math.max(0, Math.min(r3, r0));
        int r42 = Math.max(0, Math.min(r4, r0));
        if (r32 >= r42) goto L8;
        FormattedDateSpan[] r33 = (FormattedDateSpan[]) r2.getSpans(r32, r42, FormattedDateSpan.class);
        int r43 = r33.length;
    L6:
        if (r1 >= r43) goto L10;
        r2.removeSpan(r33[r1]);
        r1 = r1 + 1;
        goto L6
    L10:
        return;
    }

    public static void removeLink(Spannable r8, int r9, int r10) {
        int r0 = r8.length();
        int r1 = 0;
        int r92 = Math.max(0, Math.min(r9, r0));
        int r102 = Math.max(0, Math.min(r10, r0));
        if (r92 >= r102) goto L13;
        URLSpanReplacement[] r02 = (URLSpanReplacement[]) r8.getSpans(r92, r102, URLSpanReplacement.class);
        int r2 = r02.length;
    L6:
        if (r1 >= r2) goto L17;
        URLSpanReplacement r3 = r02[r1];
        int r4 = r8.getSpanStart(r3);
        int r5 = r8.getSpanEnd(r3);
        r8.removeSpan(r3);
        if (r4 >= r92) goto L10;
        r8.setSpan(linkSpan(r3.getURL()), r4, r92, 33);
    L10:
        if (r5 <= r102) goto L12;
        r8.setSpan(linkSpan(r3.getURL()), r102, r5, 33);
    L12:
        r1 = r1 + 1;
        goto L6
    L17:
        return;
    }

    private static Run runAt(Spanned r4, int r5, int r6) {
        Run r0 = new Run(null);
        r0.flags = flagsBetween(r4, r5, r6);
        if (((URLSpanMono[]) r4.getSpans(r5, r6, URLSpanMono.class)).length <= 0) goto L5;
        r0.flags |= 4;
    L5:
        URLSpanReplacement[] r1 = (URLSpanReplacement[]) r4.getSpans(r5, r6, URLSpanReplacement.class);
        if (r1.length <= 0) goto L8;
        r0.url = r1[0].getURL();
    L8:
        FormattedDateSpan[] r12 = (FormattedDateSpan[]) r4.getSpans(r5, r6, FormattedDateSpan.class);
        if (r12.length <= 0) goto L11;
        r0.date = r12[0];
    L11:
        AnimatedEmojiSpan[] r13 = (AnimatedEmojiSpan[]) r4.getSpans(r5, r6, AnimatedEmojiSpan.class);
        if (r13.length <= 0) goto L14;
        r0.emojiDocId = r13[0].getDocumentId();
    L14:
        MathSpan[] r14 = (MathSpan[]) r4.getSpans(r5, r6, MathSpan.class);
        if (r14.length <= 0) goto L17;
        r0.mathSource = r14[0].source;
    L17:
        RichInlineButtonSpan[] r42 = (RichInlineButtonSpan[]) r4.getSpans(r5, r6, RichInlineButtonSpan.class);
        if (r42.length <= 0) goto L20;
        r0.button = r42[0];
    L20:
        return r0;
    }

    private static void setDiffStyle(SpannableStringBuilder r2, int r3, int r4) {
        if (r2.length() <= r3) goto L6;
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags = r4;
        r2.setSpan(new TextStyleSpan(r0), r3, r2.length(), 33);
        return;
    }

    public static void setStyle(Spannable r6, int r7, int r8, int r9, boolean r10) {
        setStyle(r6, r7, r8, r9, r10, null);
    }

    public static void setStyle(Spannable r8, int r9, int r10, int r11, boolean r12, TL_iv.PageBlock r13) {
        int r0 = r8.length();
        int r1 = 0;
        int r92 = Math.max(0, Math.min(r9, r0));
        int r102 = Math.max(0, Math.min(r10, r0));
        if (r92 >= r102) goto L25;
        TextStyleSpan[] r2 = (TextStyleSpan[]) r8.getSpans(r92, r102, TextStyleSpan.class);
        int r3 = r2.length;
    L6:
        if (r1 >= r3) goto L12;
        TextStyleSpan r4 = r2[r1];
        int r5 = r8.getSpanStart(r4);
        int r6 = r8.getSpanEnd(r4);
        int r7 = r4.getStyleFlags();
        r8.removeSpan(r4);
        applyRun(r8, r5, r92, r7, r13);
        applyRun(r8, r102, r6, r7, r13);
        int r42 = Math.max(r5, r92);
        int r52 = Math.min(r6, r102);
        if (r12 == false) goto L10;
        int r62 = r7 | r11;
    L11:
        applyRun(r8, r42, r52, r62, r13);
        r1 = r1 + 1;
        goto L6
    L10:
        r62 = (~r11) & r7;
        goto L11
    L12:
        if (r12 == false) goto L18;
    L13:
        if (r92 >= r102) goto L26;
        int r122 = r8.nextSpanTransition(r92, r102, TextStyleSpan.class);
        if (flagsBetween(r8, r92, r122) != 0) goto L17;
        applyRun(r8, r92, r122, r11, r13);
    L17:
        r92 = r122;
        goto L13
    L26:
        return;
    L18:
        return;
    }

    private static TextStyleSpan spanFor(int r1, TL_iv.PageBlock r2) {
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags = r1;
        if ((r2 instanceof TL_iv.pageBlockTitle) == false) goto L5;
    L22:
        boolean r12 = true;
    L23:
        r0.header = r12;
        return new TextStyleSpan(r0);
    L5:
        if ((r2 instanceof TL_iv.pageBlockSubheader) == true) goto L22;
        if ((r2 instanceof TL_iv.pageBlockHeader) == true) goto L22;
        if ((r2 instanceof TL_iv.pageBlockHeading1) == true) goto L22;
        if ((r2 instanceof TL_iv.pageBlockHeading2) == true) goto L22;
        if ((r2 instanceof TL_iv.pageBlockHeading3) == true) goto L22;
        if ((r2 instanceof TL_iv.pageBlockHeading4) == true) goto L22;
        if ((r2 instanceof TL_iv.pageBlockHeading5) == true) goto L22;
        if ((r2 instanceof TL_iv.pageBlockHeading6) == true) goto L22;
        r12 = false;
        goto L23
    }

    public static int stylesFullyCovering(CharSequence r6, int r7, int r8) {
        int[] r0 = STYLE_FLAGS;
        int r1 = r0.length;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r1) goto L8;
        int r4 = r0[r2];
        if (hasStyle(r6, r7, r8, r4) == false) goto L7;
        r3 = r3 | r4;
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        return r3;
    }

    public static CharSequence toSimpleSpannable(TL_iv.RichText r1, TL_iv.PageBlock r2) {
        return toSpannable(r1, r2, false);
    }

    public static CharSequence toSpannable(TL_iv.RichText r1) {
        return toSpannable(r1, null);
    }

    public static CharSequence toSpannable(TL_iv.RichText r1, TL_iv.PageBlock r2) {
        return toSpannable(r1, r2, true);
    }

    private static CharSequence toSpannable(TL_iv.RichText r2, TL_iv.PageBlock r3, boolean r4) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        append(r0, r2, 0, r3, r4);
        return r0;
    }

    private static TL_iv.RichText wrap(String r4, Run r5) {
        RichInlineButtonSpan r0 = r5.button;
        if (r0 == null) goto L7;
        return r0.getButton();
    L7:
        if (r5.mathSource == null) goto L10;
        TL_iv.textMath r42 = new TL_iv.textMath();
        r42.source = r5.mathSource;
        return r42;
    L10:
        long r02 = r5.emojiDocId;
        if (r02 == 0) goto L13;
        TL_iv.RichText r43 = customEmojiNode(r02, r4);
    L14:
        int r03 = r5.flags;
        if ((r03 & 1) == 0) goto L18;
        r43 = wrapOne(new TL_iv.textBold(), r43);
    L18:
        if ((r03 & 2) == 0) goto L21;
        r43 = wrapOne(new TL_iv.textItalic(), r43);
    L21:
        if ((r03 & 16) == 0) goto L24;
        r43 = wrapOne(new TL_iv.textUnderline(), r43);
    L24:
        if ((r03 & 8) == 0) goto L27;
        r43 = wrapOne(new TL_iv.textStrike(), r43);
    L27:
        if ((r03 & 4) == 0) goto L30;
        r43 = wrapOne(new TL_iv.textFixed(), r43);
    L30:
        if ((r03 & 256) == 0) goto L33;
        r43 = wrapOne(new TL_iv.textSpoiler(), r43);
    L33:
        if ((r03 & 16384) == 0) goto L36;
        r43 = wrapOne(new TL_iv.textSubscript(), r43);
    L36:
        if ((32768 & r03) == 0) goto L39;
        r43 = wrapOne(new TL_iv.textSuperscript(), r43);
    L39:
        if ((r03 & 65536) == 0) goto L42;
        r43 = wrapOne(new TL_iv.textMarked(), r43);
    L42:
        if (r5.url == null) goto L44;
        TL_iv.textUrl r04 = new TL_iv.textUrl();
        r04.text = r43;
        r04.url = r5.url;
        r43 = r04;
    L44:
        FormattedDateSpan r52 = r5.date;
        if (r52 != null) goto L47;
        return r43;
    L47:
        return dateNode(r52, r43);
    L13:
        r43 = plainNode(r4);
        goto L14
    }

    private static TL_iv.RichText wrapOne(TL_iv.RichText r0, TL_iv.RichText r1) {
        r0.text = r1;
        return r0;
    }
}
