package androidx.media3.extractor.text.ttml;

import android.annotation.SuppressLint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.HorizontalTextInVerticalContextSpan;
import androidx.media3.common.text.RubySpan;
import androidx.media3.common.text.SpanUtil;
import androidx.media3.common.text.TextEmphasisSpan;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.Map;

/* loaded from: classes4.dex */
abstract class TtmlRenderUtil {
    @SuppressLint({"WrongConstant"})
    public static void applyStylesToSpan(Spannable r8, int r9, int r10, TtmlStyle r11, TtmlNode r12, Map<String, TtmlStyle> r13, int r14) {
        if (r11.getStyle() == (-1)) goto L6;
        r8.setSpan(new StyleSpan(r11.getStyle()), r9, r10, 33);
    L6:
        if (r11.isLinethrough() == false) goto L9;
        r8.setSpan(new StrikethroughSpan(), r9, r10, 33);
    L9:
        if (r11.isUnderline() == false) goto L12;
        r8.setSpan(new UnderlineSpan(), r9, r10, 33);
    L12:
        if (r11.hasFontColor() == false) goto L15;
        SpanUtil.addOrReplaceSpan(r8, new ForegroundColorSpan(r11.getFontColor()), r9, r10, 33);
    L15:
        if (r11.hasBackgroundColor() == false) goto L18;
        SpanUtil.addOrReplaceSpan(r8, new BackgroundColorSpan(r11.getBackgroundColor()), r9, r10, 33);
    L18:
        if (r11.getFontFamily() == null) goto L21;
        SpanUtil.addOrReplaceSpan(r8, new TypefaceSpan(r11.getFontFamily()), r9, r10, 33);
    L21:
        if (r11.getTextEmphasis() == null) goto L35;
        TextEmphasis r0 = (TextEmphasis) Assertions.checkNotNull(r11.getTextEmphasis());
        int r6 = r0.markShape;
        if (r6 != (-1)) goto L30;
        if (r14 == 2) goto L28;
        if (r14 == 1) goto L28;
        r6 = 1;
    L29:
        int r142 = 1;
    L31:
        int r02 = r0.position;
        if (r02 != (-2)) goto L34;
        r02 = 1;
    L34:
        SpanUtil.addOrReplaceSpan(r8, new TextEmphasisSpan(r6, r142, r02), r9, r10, 33);
    L28:
        r6 = 3;
        goto L29
    L30:
        r142 = r0.markFill;
    L35:
        int r143 = r11.getRubyType();
        if (r143 == 2) goto L42;
        if (r143 != 3) goto L39;
    L41:
        r8.setSpan(new DeleteTextSpan(), r9, r10, 33);
    L63:
        if (r11.getTextCombine() == false) goto L65;
        SpanUtil.addOrReplaceSpan(r8, new HorizontalTextInVerticalContextSpan(), r9, r10, 33);
    L65:
        int r122 = r11.getFontSizeUnit();
        if (r122 == 1) goto L74;
        if (r122 == 2) goto L72;
        if (r122 == 3) goto L70;
        return;
    L70:
        SpanUtil.addInheritedRelativeSizeSpan(r8, r11.getFontSize() / 100.0f, r9, r10, 33);
        return;
    L72:
        SpanUtil.addOrReplaceSpan(r8, new RelativeSizeSpan(r11.getFontSize()), r9, r10, 33);
        return;
    L74:
        SpanUtil.addOrReplaceSpan(r8, new AbsoluteSizeSpan((int) r11.getFontSize(), true), r9, r10, 33);
        return;
    L39:
        if (r143 == 4) goto L41;
    L42:
        TtmlNode r123 = findRubyContainerNode(r12, r13);
        if (r123 == null) goto L63;
        TtmlNode r144 = findRubyTextNode(r123, r13);
        if (r144 == null) goto L63;
        if (r144.getChildCount() == 1) goto L51;
    L61:
        Log.i("TtmlRenderUtil", "Skipping rubyText node without exactly one text child.");
        goto L63
    L51:
        if (r144.getChild(0).text == null) goto L61;
        String r03 = (String) Util.castNonNull(r144.getChild(0).text);
        TtmlStyle r145 = resolveStyle(r144.style, r144.getStyleIds(), r13);
        if (r145 == null) goto L55;
        int r146 = r145.getRubyPosition();
    L56:
        if (r146 != (-1)) goto L60;
        TtmlStyle r124 = resolveStyle(r123.style, r123.getStyleIds(), r13);
        if (r124 == null) goto L60;
        r146 = r124.getRubyPosition();
    L60:
        r8.setSpan(new RubySpan(r03, r146), r9, r10, 33);
        goto L63
    L55:
        r146 = -1;
        goto L56
    }

    public static String applyTextElementSpacePolicy(String r2) {
        return r2.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", " ").replaceAll("[ \t\\x0B\f\r]+", " ");
    }

    public static void endParagraph(SpannableStringBuilder r3) {
        int r0 = r3.length() - 1;
    L3:
        if (r0 < 0) goto L7;
        if (r3.charAt(r0) != ' ') goto L7;
        r0 = r0 - 1;
    L7:
        if (r0 >= 0) goto L9;
        return;
    L9:
        if (r3.charAt(r0) == '\n') goto L15;
        r3.append('\n');
        return;
    }

    private static TtmlNode findRubyContainerNode(TtmlNode r2, Map<String, TtmlStyle> r3) {
    L2:
        if (r2 == null) goto L9;
        TtmlStyle r0 = resolveStyle(r2.style, r2.getStyleIds(), r3);
        if (r0 == null) goto L8;
        if (r0.getRubyType() != 1) goto L8;
        return r2;
    L8:
        r2 = r2.parent;
        goto L2
    L9:
        return null;
    }

    private static TtmlNode findRubyTextNode(TtmlNode r3, Map<String, TtmlStyle> r4) {
        ArrayDeque r0 = new ArrayDeque();
        r0.push(r3);
    L4:
        if (r0.isEmpty() == true) goto L13;
        TtmlNode r32 = (TtmlNode) r0.pop();
        TtmlStyle r1 = resolveStyle(r32.style, r32.getStyleIds(), r4);
        if (r1 != null) goto L8;
    L10:
        int r12 = r32.getChildCount() - 1;
    L11:
        if (r12 < 0) goto L4;
        r0.push(r32.getChild(r12));
        r12 = r12 - 1;
        goto L11
    L8:
        if (r1.getRubyType() != 3) goto L10;
        return r32;
    L13:
        return null;
    }

    public static TtmlStyle resolveStyle(TtmlStyle r3, String[] r4, Map<String, TtmlStyle> r5) {
        int r0 = 0;
        if (r3 != null) goto L17;
        if (r4 != null) goto L8;
        return null;
    L8:
        if (r4.length != 1) goto L12;
        return r5.get(r4[0]);
    L12:
        if (r4.length <= 1) goto L28;
        TtmlStyle r32 = new TtmlStyle();
        int r1 = r4.length;
    L14:
        if (r0 >= r1) goto L16;
        r32.chain(r5.get(r4[r0]));
        r0 = r0 + 1;
        goto L14
    L16:
        return r32;
    L28:
        return r3;
    L17:
        if (r4 != null) goto L19;
    L22:
        if (r4 == null) goto L28;
        if (r4.length <= 1) goto L28;
        int r12 = r4.length;
    L26:
        if (r0 >= r12) goto L28;
        r3.chain(r5.get(r4[r0]));
        r0 = r0 + 1;
        goto L26
    L19:
        if (r4.length != 1) goto L22;
        return r3.chain(r5.get(r4[0]));
    }
}
