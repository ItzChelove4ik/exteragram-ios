package org.telegram.ui.iv;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.TextStyleSpan;

/* loaded from: classes7.dex */
public abstract class RichMessageConvert {
    private static <T> T blockSpan(Spanned r5, int r6, int r7, Class<T> r8) {
        Object[] r82 = r5.getSpans(r6, Math.max(r6, r7), r8);
        int r0 = r82.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        T r2 = (T) r82[r1];
        int r3 = r5.getSpanStart(r2);
        int r4 = r5.getSpanEnd(r2);
        if (r3 > r6) goto L8;
        if (r4 < r7) goto L8;
        return r2;
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ArrayList<TL_iv.PageBlock> blocksFromCharSequence(CharSequence r17) {
        ArrayList<TL_iv.PageBlock> r1 = new ArrayList();
        if (r17 != null) goto L7;
        r1.add(emptyParagraph());
        return r1;
    L7:
        if ((r17 instanceof Spanned) == false) goto L9;
        Spanned r2 = (Spanned) r17;
    L10:
        int[] r4 = lineStarts(r17);
        int r5 = r17.length();
        int r7 = 0;
    L12:
        if (r7 >= r4.length) goto L60;
        int r8 = r4[r7];
        r7 = r7 + 1;
        boolean r10 = true;
        if (r7 >= r4.length) goto L16;
        int r9 = r4[r7] - 1;
    L18:
        if (r2 != null) goto L20;
        CodeHighlighting.Span r12 = null;
    L22:
        if (r2 == null) goto L26;
        if (r12 != null) goto L26;
        QuoteSpan r14 = (QuoteSpan) blockSpan(r2, r8, r9, QuoteSpan.class);
    L27:
        if (r12 != null) goto L30;
        if (r14 != 0) goto L30;
        r1.add(paragraph(r17.subSequence(r8, r9)));
    L30:
        if (r12 == null) goto L32;
        CodeHighlighting.Span r15 = r12;
    L34:
        if (r7 >= r4.length) goto L45;
        int r3 = r4[r7];
        int r6 = r7 + 1;
        boolean r16 = r10;
        if (r6 >= r4.length) goto L38;
        int r102 = r4[r6] - 1;
    L39:
        if (r12 == null) goto L41;
        Object r32 = blockSpan(r2, r3, r102, CodeHighlighting.Span.class);
    L42:
        if (r32 != r15) goto L46;
        r7 = r6;
        r9 = r102;
        r10 = r16;
    L46:
        CharSequence r33 = r17.subSequence(r8, r9);
        if (r12 != null) goto L48;
        TL_iv.pageBlockBlockquote r62 = new TL_iv.pageBlockBlockquote();
        r62.text = RichTextStyle.fromSpannable(r33);
        r62.caption = new TL_iv.textEmpty();
        if (r14 != 0) goto L55;
    L57:
        boolean r103 = false;
    L58:
        r62.collapsed = r103;
        r1.add(r62);
        goto L12
    L55:
        if (r14.isCollapsing == false) goto L57;
        r103 = r16;
        goto L58
    L48:
        TL_iv.pageBlockPreformatted r63 = new TL_iv.pageBlockPreformatted();
        r63.text = RichTextStyle.fromSpannable(r33);
        String r34 = r12.lng;
        if (r34 != null) goto L51;
        r34 = _UrlKt.FRAGMENT_ENCODE_SET;
    L51:
        r63.language = r34;
        r1.add(r63);
        goto L12
    L41:
        r32 = blockSpan(r2, r3, r102, QuoteSpan.class);
        goto L42
    L38:
        r102 = r5;
        goto L39
    L45:
        r16 = r10;
        goto L46
    L32:
        r15 = r14;
    L26:
        r14 = 0;
        goto L27
    L20:
        r12 = (CodeHighlighting.Span) blockSpan(r2, r8, r9, CodeHighlighting.Span.class);
        goto L22
    L16:
        r9 = r5;
        goto L18
    L60:
        if (r1.isEmpty() == false) goto L62;
        r1.add(emptyParagraph());
    L62:
        return r1;
    L9:
        r2 = null;
        goto L10
    }

    public static CharSequence blocksToCharSequence(List<TL_iv.PageBlock> r1) {
        ArrayList r0 = new ArrayList();
        collectBlocks(r0, r1);
        return join(r0);
    }

    private static CharSequence bold(CharSequence r1) {
        return styled(r1, 1);
    }

    private static TL_iv.RichText captionOf(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == false) goto L7;
        return ((TL_iv.pageBlockBlockquote) r1).caption;
    L7:
        if ((r1 instanceof TL_iv.pageBlockPullquote) == true) goto L9;
        return null;
    L9:
        return ((TL_iv.pageBlockPullquote) r1).caption;
    }

    private static CharSequence captionText(TL_iv.PageBlock r4) {
        if (r4.caption != null) goto L5;
        return null;
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        CharSequence r2 = RichTextStyle.toSpannable(r4.caption.text);
        if (TextUtils.isEmpty(r2) == true) goto L8;
        r0.append(r2);
    L8:
        CharSequence r42 = RichTextStyle.toSpannable(r4.caption.credit);
        if (TextUtils.isEmpty(r42) == true) goto L15;
        if (r0.length() <= 0) goto L13;
        r0.append('\n');
    L13:
        r0.append(r42);
    L15:
        if (r0.length() <= 0) goto L17;
        return r0;
    L17:
        return null;
    }

    private static void collectBlocks(ArrayList<CharSequence> r1, List<TL_iv.PageBlock> r2) {
        if (r2 == null) goto L10;
        Iterator<TL_iv.PageBlock> r22 = r2.iterator();
    L6:
        if (r22.hasNext() == false) goto L16;
        CharSequence r0 = renderBlock(r22.next());
        if (r0 == null) goto L6;
        r1.add(r0);
        goto L6
    L16:
        return;
    }

    private static TL_iv.pageBlockParagraph emptyParagraph() {
        TL_iv.pageBlockParagraph r0 = new TL_iv.pageBlockParagraph();
        r0.text = new TL_iv.textEmpty();
        return r0;
    }

    private static boolean hasText(TL_iv.RichText r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if ((r1 instanceof TL_iv.textEmpty) == false) goto L6;
        return false;
    L6:
        if (RichTextStyle.plainOf(r1).isEmpty() == true) goto L12;
        return true;
    L12:
        return false;
    }

    private static boolean inlineLossy(TL_iv.RichText r5) {
        if (r5 != null) goto L5;
    L33:
        return false;
    L5:
        if ((r5 instanceof TL_iv.textEmpty) == true) goto L33;
        if ((r5 instanceof TL_iv.textPlain) == true) goto L33;
        if ((r5 instanceof TL_iv.textCustomEmoji) == true) goto L33;
        if ((r5 instanceof TL_iv.textConcat) == false) goto L20;
        ArrayList<TL_iv.RichText> r52 = ((TL_iv.textConcat) r5).texts;
        int r1 = r52.size();
        int r3 = 0;
    L14:
        if (r3 >= r1) goto L18;
        TL_iv.RichText r4 = r52.get(r3);
        r3 = r3 + 1;
        if (inlineLossy(r4) == false) goto L14;
        return true;
    L18:
        return false;
    L20:
        if ((r5 instanceof TL_iv.textMarked) == false) goto L22;
    L32:
        return true;
    L22:
        if ((r5 instanceof TL_iv.textSubscript) == true) goto L32;
        if ((r5 instanceof TL_iv.textSuperscript) == true) goto L32;
        if ((r5 instanceof TL_iv.textMath) == true) goto L32;
        if ((r5 instanceof TL_iv.textButton) == true) goto L32;
        return inlineLossy(r5.text);
    }

    private static boolean isHeading(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockTitle) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockSubtitle) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TL_iv.pageBlockHeader) == false) goto L9;
        return true;
    L9:
        if ((r1 instanceof TL_iv.pageBlockSubheader) == false) goto L11;
        return true;
    L11:
        if ((r1 instanceof TL_iv.pageBlockHeading1) == false) goto L13;
        return true;
    L13:
        if ((r1 instanceof TL_iv.pageBlockHeading2) == false) goto L15;
        return true;
    L15:
        if ((r1 instanceof TL_iv.pageBlockHeading3) == false) goto L17;
        return true;
    L17:
        if ((r1 instanceof TL_iv.pageBlockHeading4) == false) goto L19;
        return true;
    L19:
        if ((r1 instanceof TL_iv.pageBlockHeading5) == false) goto L21;
        return true;
    L21:
        if ((r1 instanceof TL_iv.pageBlockHeading6) == true) goto L35;
        return false;
    L35:
        return true;
    }

    public static boolean isLossy(List<BlockRow> r1) {
        return isLossy(r1, null);
    }

    public static boolean isLossy(List<BlockRow> r2, Map<Long, TL_iv.RichText> r3) {
        if (r2 != null) goto L5;
        return false;
    L5:
        Iterator<BlockRow> r22 = r2.iterator();
    L7:
        if (r22.hasNext() == false) goto L12;
        if (rowLossy(r22.next(), r3) == false) goto L7;
        return true;
    L12:
        return false;
    }

    private static boolean isQuoteLeaf(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockPullquote) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private static CharSequence join(ArrayList<CharSequence> r3) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L8;
        if (r1 <= 0) goto L7;
        r0.append('\n');
    L7:
        r0.append(r3.get(r1));
        r1 = r1 + 1;
        goto L4
    L8:
        return r0;
    }

    private static int[] lineStarts(CharSequence r5) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
        r0.add(0);
        int r2 = 0;
    L4:
        if (r2 >= r5.length()) goto L9;
        if (r5.charAt(r2) != '\n') goto L8;
        r0.add(Integer.valueOf(r2 + 1));
    L8:
        r2 = r2 + 1;
        goto L4
    L9:
        int r52 = r0.size();
        int[] r22 = new int[r52];
    L10:
        if (r1 >= r52) goto L12;
        r22[r1] = ((Integer) r0.get(r1)).intValue();
        r1 = r1 + 1;
        goto L10
    L12:
        return r22;
    }

    private static CharSequence listItemText(TL_iv.PageListItem r1) {
        if ((r1 instanceof TL_iv.TL_pageListItemText) == false) goto L7;
        return RichTextStyle.toSpannable(((TL_iv.TL_pageListItemText) r1).text);
    L7:
        if ((r1 instanceof TL_iv.TL_pageListItemBlocks) == true) goto L9;
        return null;
    L9:
        return blocksToCharSequence(((TL_iv.TL_pageListItemBlocks) r1).blocks);
    }

    private static CharSequence mono(CharSequence r1) {
        return styled(r1, 4);
    }

    private static CharSequence orderedItemText(TL_iv.PageListOrderedItem r1) {
        if ((r1 instanceof TL_iv.TL_pageListOrderedItemText) == false) goto L7;
        return RichTextStyle.toSpannable(((TL_iv.TL_pageListOrderedItemText) r1).text);
    L7:
        if ((r1 instanceof TL_iv.TL_pageListOrderedItemBlocks) == true) goto L9;
        return null;
    L9:
        return blocksToCharSequence(((TL_iv.TL_pageListOrderedItemBlocks) r1).blocks);
    }

    private static TL_iv.pageBlockParagraph paragraph(CharSequence r1) {
        TL_iv.pageBlockParagraph r0 = new TL_iv.pageBlockParagraph();
        r0.text = RichTextStyle.fromSpannable(r1);
        return r0;
    }

    private static CharSequence prefixed(String r1, CharSequence r2) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(r1);
        if (r2 != null) goto L5;
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        r0.append(r2);
        return r0;
    }

    private static CharSequence quoted(CharSequence r1, TL_iv.RichText r2) {
        if (r1 != null) goto L5;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder(r1);
        CharSequence r12 = RichTextStyle.toSpannable(r2);
        if (TextUtils.isEmpty(r12) == true) goto L12;
        if (r0.length() <= 0) goto L10;
        r0.append('\n');
    L10:
        r0.append("— ").append(r12);
    L12:
        if (r0.length() != 0) goto L15;
        return null;
    L15:
        QuoteSpan.putQuote(r0, 0, r0.length(), false);
        return r0;
    }

    private static CharSequence renderBlock(TL_iv.PageBlock r7) {
        if (r7 != null) goto L6;
        return null;
    L6:
        if ((r7 instanceof TL_iv.pageBlockPreformatted) == false) goto L9;
        SpannableStringBuilder r0 = new SpannableStringBuilder(RichTextStyle.toSpannable(r7.text, r7));
        r0.setSpan(new CodeHighlighting.Span(true, 0, null, ((TL_iv.pageBlockPreformatted) r7).language, r0.toString()), 0, r0.length(), 33);
        return r0;
    L9:
        boolean r1 = r7 instanceof TL_iv.pageBlockBlockquote;
        if (r1 == false) goto L12;
    L74:
        if (r1 == false) goto L76;
        TL_iv.RichText r02 = ((TL_iv.pageBlockBlockquote) r7).caption;
    L78:
        return quoted(RichTextStyle.toSpannable(r7.text, r7), r02);
    L76:
        r02 = ((TL_iv.pageBlockPullquote) r7).caption;
        goto L78
    L12:
        if ((r7 instanceof TL_iv.pageBlockPullquote) == true) goto L74;
        if ((r7 instanceof TL_iv.pageBlockBlockquoteBlocks) == false) goto L19;
        TL_iv.pageBlockBlockquoteBlocks r72 = (TL_iv.pageBlockBlockquoteBlocks) r7;
        ArrayList r03 = new ArrayList();
        collectBlocks(r03, r72.blocks);
        return quoted(join(r03), r72.caption);
    L19:
        if ((r7 instanceof TL_iv.pageBlockDetails) == false) goto L29;
        TL_iv.pageBlockDetails r73 = (TL_iv.pageBlockDetails) r7;
        ArrayList r12 = new ArrayList();
        CharSequence r2 = RichTextStyle.toSpannable(r73.title);
        if (TextUtils.isEmpty(r2) == true) goto L23;
        r12.add(r2);
    L23:
        collectBlocks(r12, r73.blocks);
        if (r12.isEmpty() == false) goto L27;
        return null;
    L27:
        return join(r12);
    L29:
        if ((r7 instanceof TL_iv.pageBlockList) == false) goto L33;
        return renderList(((TL_iv.pageBlockList) r7).items);
    L33:
        if ((r7 instanceof TL_iv.pageBlockOrderedList) == false) goto L37;
        return renderOrderedList(((TL_iv.pageBlockOrderedList) r7).items);
    L37:
        if ((r7 instanceof TL_iv.pageBlockTable) == false) goto L41;
        return renderTable((TL_iv.pageBlockTable) r7);
    L41:
        if ((r7 instanceof TL_iv.pageBlockMath) == false) goto L48;
        String r74 = ((TL_iv.pageBlockMath) r7).source;
        if (TextUtils.isEmpty(r74) == false) goto L46;
        return null;
    L46:
        return mono(new SpannableStringBuilder(r74));
    L48:
        if ((r7 instanceof TL_iv.pageBlockDivider) == false) goto L52;
        return "——————————";
    L52:
        if (isHeading(r7) == false) goto L58;
        CharSequence r75 = bold(RichTextStyle.toSpannable(r7.text, r7));
        if (TextUtils.isEmpty(r75) == false) goto L56;
        return null;
    L56:
        return r75;
    L58:
        if ((r7 instanceof TL_iv.pageBlockAuthorDate) == false) goto L62;
        return RichTextStyle.toSpannable(((TL_iv.pageBlockAuthorDate) r7).author);
    L62:
        if ((r7 instanceof TL_iv.pageBlockParagraph) == true) goto L73;
        if ((r7 instanceof TL_iv.pageBlockFooter) == true) goto L73;
        if ((r7 instanceof TL_iv.pageBlockKicker) == true) goto L73;
        if ((r7 instanceof TL_iv.pageBlockThinking) == true) goto L73;
        return captionText(r7);
    L73:
        return RichTextStyle.toSpannable(r7.text, r7);
    }

    private static CharSequence renderLeaf(BlockRow r7, boolean r8) {
        TL_iv.RichText r0 = null;
        if (r8 == false) goto L9;
        TL_iv.PageBlock r82 = r7.block;
        if (r82 == null) goto L8;
        r0 = r82.text;
    L8:
        CharSequence r83 = RichTextStyle.toSimpleSpannable(r0, r82);
    L15:
        if ((r7.block instanceof TL_iv.pageBlockPreformatted) == false) goto L18;
        SpannableStringBuilder r02 = new SpannableStringBuilder(r83);
        r02.setSpan(new CodeHighlighting.Span(true, 0, null, ((TL_iv.pageBlockPreformatted) r7.block).language, r02.toString()), 0, r02.length(), 33);
        return r02;
    L18:
        return r83;
    L9:
        TL_iv.PageBlock r84 = r7.block;
        if (r84 == null) goto L13;
        r0 = r84.text;
    L13:
        r83 = RichTextStyle.toSpannable(r0, r84);
        goto L15
    }

    private static CharSequence renderList(ArrayList<TL_iv.PageListItem> r7) {
        if (r7 != null) goto L5;
    L25:
        return null;
    L5:
        if (r7.isEmpty() == true) goto L25;
        ArrayList r1 = new ArrayList();
        int r2 = r7.size();
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L21;
        TL_iv.PageListItem r4 = r7.get(r3);
        r3 = r3 + 1;
        TL_iv.PageListItem r42 = r4;
        CharSequence r5 = listItemText(r42);
        if (r5 == null) goto L8;
        if (r42.checkbox == true) goto L15;
        String r43 = "•  ";
    L19:
        r1.add(prefixed(r43, r5));
        goto L8
    L15:
        if (r42.checked == false) goto L17;
        r43 = "☑  ";
        goto L19
    L17:
        r43 = "☐  ";
        goto L19
    L21:
        if (r1.isEmpty() == false) goto L24;
        return null;
    L24:
        return join(r1);
    }

    private static CharSequence renderOrderedList(ArrayList<TL_iv.PageListOrderedItem> r8) {
        if (r8 != null) goto L5;
    L22:
        return null;
    L5:
        if (r8.isEmpty() == true) goto L22;
        ArrayList r1 = new ArrayList();
        int r2 = r8.size();
        int r3 = 1;
        int r4 = 0;
    L8:
        if (r4 >= r2) goto L18;
        TL_iv.PageListOrderedItem r5 = r8.get(r4);
        r4 = r4 + 1;
        TL_iv.PageListOrderedItem r52 = r5;
        CharSequence r6 = orderedItemText(r52);
        if (r6 == null) goto L11;
        if (TextUtils.isEmpty(r52.num) == true) goto L15;
        String r53 = r52.num;
    L16:
        r1.add(prefixed(r53 + ".  ", r6));
        goto L11
    L15:
        r53 = String.valueOf(r3);
    L11:
        r3 = r3 + 1;
        goto L8
    L18:
        if (r1.isEmpty() == false) goto L21;
        return null;
    L21:
        return join(r1);
    }

    private static CharSequence renderTable(TL_iv.pageBlockTable r8) {
        ArrayList r0 = new ArrayList();
        CharSequence r1 = RichTextStyle.toSpannable(r8.title);
        if (TextUtils.isEmpty(r1) == true) goto L5;
        r0.add(r1);
    L5:
        ArrayList<TL_iv.pageTableRow> r82 = r8.rows;
        if (r82 == null) goto L22;
        int r12 = r82.size();
        int r3 = 0;
    L8:
        if (r3 >= r12) goto L22;
        TL_iv.pageTableRow r4 = r82.get(r3);
        r3 = r3 + 1;
        TL_iv.pageTableRow r42 = r4;
        ArrayList<TL_iv.pageTableCell> r5 = r42.cells;
        if (r5 == null) goto L8;
        if (r5.isEmpty() == true) goto L8;
        SpannableStringBuilder r52 = new SpannableStringBuilder();
        int r6 = 0;
    L16:
        if (r6 >= r42.cells.size()) goto L20;
        if (r6 <= 0) goto L19;
        r52.append("  |  ");
    L19:
        r52.append(RichTextStyle.toSpannable(r42.cells.get(r6).text));
        r6 = r6 + 1;
        goto L16
    L20:
        r0.add(r52);
    L22:
        if (r0.isEmpty() == false) goto L26;
        return null;
    L26:
        return join(r0);
    }

    private static boolean rowLossy(BlockRow r6, Map<Long, TL_iv.RichText> r7) {
        int r0 = 0;
        if (r6 != null) goto L6;
        return false;
    L6:
        if (r6.detailsEnd == false) goto L9;
        return true;
    L9:
        if (r6.level <= 0) goto L11;
        return true;
    L11:
        TL_iv.PageBlock r1 = r6.block;
        if ((r1 instanceof TL_iv.pageBlockPullquote) == false) goto L14;
        return true;
    L14:
        boolean r3 = r1 instanceof TL_iv.pageBlockBlockquote;
        if ((r6.quoteIds.size() + (r3 ? 1 : 0)) <= 1) goto L17;
        return true;
    L17:
        if (r3 == true) goto L19;
    L21:
        if (r7 == null) goto L30;
        if (r6.quoteIds.isEmpty() == true) goto L30;
        ArrayList<Long> r62 = r6.quoteIds;
        int r4 = r62.size();
    L25:
        if (r0 >= r4) goto L30;
        Long r5 = r62.get(r0);
        r0 = r0 + 1;
        if (hasText(r7.get(r5)) == false) goto L25;
        return true;
    L30:
        if ((r1 instanceof TL_iv.pageBlockParagraph) == true) goto L37;
        if ((r1 instanceof TL_iv.pageBlockPreformatted) == true) goto L37;
        if (r3 == true) goto L37;
        return true;
    L37:
        return inlineLossy(r1.text);
    L19:
        if (hasText(captionOf(r1)) == false) goto L21;
        return true;
    }

    public static CharSequence rowsToCharSequence(List<BlockRow> r1) {
        return rowsToCharSequence(r1, false);
    }

    private static CharSequence rowsToCharSequence(List<BlockRow> r9, boolean r10) {
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L3:
        if (r9 == null) goto L41;
        if (r2 >= r9.size()) goto L41;
        BlockRow r3 = r9.get(r2);
        if (r3.quoteIds.isEmpty() == false) goto L8;
        if (isQuoteLeaf(r3.block) == false) goto L39;
        TL_iv.PageBlock r4 = r3.block;
        if (r10 == false) goto L27;
        CharSequence r42 = RichTextStyle.toSimpleSpannable(r4.text, r4);
    L28:
        SpannableStringBuilder r5 = new SpannableStringBuilder(r42);
        TL_iv.PageBlock r32 = r3.block;
        if ((r32 instanceof TL_iv.pageBlockBlockquote) == true) goto L31;
    L33:
        boolean r33 = false;
    L35:
        if (r5.length() <= 0) goto L37;
        QuoteSpan.putQuote(r5, 0, r5.length(), r33);
    L37:
        r0.add(r5);
    L38:
        r2 = r2 + 1;
        goto L3
    L31:
        if (((TL_iv.pageBlockBlockquote) r32).collapsed == false) goto L33;
        r33 = true;
        goto L35
    L27:
        r42 = RichTextStyle.toSpannable(r4.text, r4);
        goto L28
    L39:
        r0.add(renderLeaf(r3, r10));
        goto L38
    L8:
        long r34 = r3.quoteIds.get(0).longValue();
        SpannableStringBuilder r52 = new SpannableStringBuilder();
        int r6 = r2;
    L10:
        if (r6 >= r9.size()) goto L19;
        if (r9.get(r6).quoteIds.isEmpty() == true) goto L19;
        if (r9.get(r6).quoteIds.get(0).longValue() != r34) goto L19;
        if (r6 <= r2) goto L17;
        r52.append('\n');
    L17:
        r52.append(renderLeaf(r9.get(r6), r10));
        r6 = r6 + 1;
    L19:
        if (r52.length() <= 0) goto L21;
        QuoteSpan.putQuote(r52, 0, r52.length(), false);
    L21:
        r0.add(r52);
        r2 = r6;
    L41:
        return join(r0);
    }

    public static CharSequence rowsToSimpleMessage(List<BlockRow> r4) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(rowsToCharSequence(r4, true));
        int r42 = r0.length();
        int r2 = 0;
        RichTextStyle.setStyle(r0, 0, r42, 65536, false);
        RichTextStyle.setStyle(r0, 0, r42, 16384, false);
        RichTextStyle.setStyle(r0, 0, r42, 32768, false);
        MathSpan[] r43 = (MathSpan[]) r0.getSpans(0, r42, MathSpan.class);
        int r1 = r43.length;
    L3:
        if (r2 >= r1) goto L5;
        r0.removeSpan(r43[r2]);
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    private static CharSequence styled(CharSequence r3, int r4) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return r3;
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder(r3);
        TextStyleSpan.TextStyleRun r32 = new TextStyleSpan.TextStyleRun();
        r32.flags = r4;
        r0.setSpan(new TextStyleSpan(r32), 0, r0.length(), 33);
        return r0;
    }

    public static CharSequence toCharSequence(TL_iv.RichMessage r0) {
        if (r0 != null) goto L4;
        ArrayList<TL_iv.PageBlock> r02 = null;
    L6:
        return blocksToCharSequence(r02);
    L4:
        r02 = r0.blocks;
        goto L6
    }
}
