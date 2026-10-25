package org.telegram.ui.iv;

import java.util.ArrayList;
import org.telegram.messenger.AppGlobalConfig;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_keyboard;

/* loaded from: classes7.dex */
public class RichEditorLimits {
    public int blocks;
    public int depth;
    public int length;
    public int media;
    public int tableCols;

    public RichEditorLimits() {
    }

    private static void addCaption(TL_iv.PageCaption r1, int r2, RichEditorLimits r3) {
        if (r1 != null) goto L4;
        return;
    L4:
        addText(r1.text, r2, r3);
        addText(r1.credit, r2, r3);
    }

    private static void addText(TL_iv.RichText r2, int r3, RichEditorLimits r4) {
        if (r2 == null) goto L9;
        r4.length += RichTextStyle.plainOf(r2).length();
        int r32 = r3 + richTextDepth(r2);
        if (r32 <= r4.depth) goto L8;
        r4.depth = r32;
        return;
    L8:
        return;
    }

    public static RichEditorLimits measure(ArrayList<TL_iv.PageBlock> r3, int r4) {
        RichEditorLimits r0 = new RichEditorLimits();
        r0.media = r4;
        r0.blocks += r3.size();
        int r42 = 0;
    L4:
        if (r42 >= r3.size()) goto L6;
        measureBlock(r3.get(r42), 1, r0);
        r42 = r42 + 1;
        goto L4
    L6:
        return r0;
    }

    private static void measureBlock(TL_iv.PageBlock r7, int r8, RichEditorLimits r9) {
        if (r7 != null) goto L5;
        return;
    L5:
        if (r8 <= r9.depth) goto L7;
        r9.depth = r8;
    L7:
        addText(r7.text, r8, r9);
        if ((r7 instanceof TL_iv.pageBlockBlockquote) == false) goto L12;
        addText(((TL_iv.pageBlockBlockquote) r7).caption, r8, r9);
        return;
    L12:
        if ((r7 instanceof TL_iv.pageBlockPullquote) == false) goto L15;
        addText(((TL_iv.pageBlockPullquote) r7).caption, r8, r9);
        return;
    L15:
        int r1 = 0;
        if ((r7 instanceof TL_iv.pageBlockBlockquoteBlocks) == false) goto L22;
        TL_iv.pageBlockBlockquoteBlocks r72 = (TL_iv.pageBlockBlockquoteBlocks) r7;
        addText(r72.caption, r8, r9);
        r9.blocks += r72.blocks.size();
    L19:
        if (r1 >= r72.blocks.size()) goto L105;
        measureBlock(r72.blocks.get(r1), r8 + 1, r9);
        r1 = r1 + 1;
        goto L19
    L105:
        return;
    L22:
        if ((r7 instanceof TL_iv.pageBlockDetails) == false) goto L28;
        TL_iv.pageBlockDetails r73 = (TL_iv.pageBlockDetails) r7;
        addText(r73.title, r8, r9);
        r9.blocks += r73.blocks.size();
    L25:
        if (r1 >= r73.blocks.size()) goto L106;
        measureBlock(r73.blocks.get(r1), r8 + 1, r9);
        r1 = r1 + 1;
        goto L25
    L106:
        return;
    L28:
        if ((r7 instanceof TL_iv.pageBlockList) == false) goto L34;
        TL_iv.pageBlockList r74 = (TL_iv.pageBlockList) r7;
        r9.blocks += r74.items.size();
    L31:
        if (r1 >= r74.items.size()) goto L107;
        measureListItem(r74.items.get(r1), r8 + 1, r9);
        r1 = r1 + 1;
        goto L31
    L107:
        return;
    L34:
        if ((r7 instanceof TL_iv.pageBlockOrderedList) == false) goto L40;
        TL_iv.pageBlockOrderedList r75 = (TL_iv.pageBlockOrderedList) r7;
        r9.blocks += r75.items.size();
    L37:
        if (r1 >= r75.items.size()) goto L108;
        measureOrderedItem(r75.items.get(r1), r8 + 1, r9);
        r1 = r1 + 1;
        goto L37
    L108:
        return;
    L40:
        if ((r7 instanceof TL_iv.pageBlockTable) == false) goto L58;
        TL_iv.pageBlockTable r76 = (TL_iv.pageBlockTable) r7;
        addText(r76.title, r8, r9);
        ArrayList<TL_iv.pageTableRow> r0 = r76.rows;
        if (r0 == null) goto L101;
        r9.blocks += r0.size();
        int r02 = 0;
    L45:
        if (r02 >= r76.rows.size()) goto L109;
        TL_iv.pageTableRow r2 = r76.rows.get(r02);
        if (r2.cells == null) goto L52;
        int r3 = 0;
        int r4 = 0;
    L50:
        if (r3 >= r2.cells.size()) goto L54;
        TL_iv.pageTableCell r5 = r2.cells.get(r3);
        r4 = r4 + TableModel.spanCol(r5);
        addText(r5.text, r8 + 1, r9);
        r3 = r3 + 1;
    L54:
        if (r4 <= r9.tableCols) goto L56;
        r9.tableCols = r4;
    L56:
        r02 = r02 + 1;
        goto L45
    L52:
        r4 = 0;
        goto L54
    L109:
        return;
    L101:
        return;
    L58:
        if ((r7 instanceof TL_iv.pageBlockButtonRow) == false) goto L67;
        ArrayList<TL_keyboard.PageButton> r77 = ((TL_iv.pageBlockButtonRow) r7).buttons;
        if (r77 == null) goto L102;
        int r03 = r77.size();
    L62:
        if (r1 >= r03) goto L110;
        TL_keyboard.PageButton r22 = r77.get(r1);
        r1 = r1 + 1;
        TL_keyboard.PageButton r23 = r22;
        if (r23 == null) goto L62;
        addText(r23.text, r8 + 1, r9);
        goto L62
    L110:
        return;
    L102:
        return;
    L67:
        if ((r7 instanceof TL_iv.pageBlockCollage) == false) goto L73;
        addCaption(r7.caption, r8, r9);
        ArrayList<TL_iv.PageBlock> r78 = ((TL_iv.pageBlockCollage) r7).items;
    L70:
        if (r1 >= r78.size()) goto L111;
        measureBlock(r78.get(r1), r8 + 1, r9);
        r1 = r1 + 1;
        goto L70
    L111:
        return;
    L73:
        if ((r7 instanceof TL_iv.pageBlockSlideshow) == false) goto L79;
        addCaption(r7.caption, r8, r9);
        ArrayList<TL_iv.PageBlock> r79 = ((TL_iv.pageBlockSlideshow) r7).items;
    L76:
        if (r1 >= r79.size()) goto L112;
        measureBlock(r79.get(r1), r8 + 1, r9);
        r1 = r1 + 1;
        goto L76
    L112:
        return;
    L79:
        if ((r7 instanceof TL_iv.pageBlockMath) == false) goto L84;
        String r710 = ((TL_iv.pageBlockMath) r7).source;
        if (r710 == null) goto L103;
        r9.length += r710.length();
        return;
    L103:
        return;
    L84:
        addCaption(r7.caption, r8, r9);
    }

    private static void measureListItem(TL_iv.PageListItem r2, int r3, RichEditorLimits r4) {
        if ((r2 instanceof TL_iv.TL_pageListItemText) == false) goto L7;
        addText(((TL_iv.TL_pageListItemText) r2).text, r3, r4);
        return;
    L7:
        if ((r2 instanceof TL_iv.TL_pageListItemBlocks) == false) goto L12;
        ArrayList<TL_iv.PageBlock> r22 = ((TL_iv.TL_pageListItemBlocks) r2).blocks;
        int r0 = 0;
    L10:
        if (r0 >= r22.size()) goto L14;
        measureBlock(r22.get(r0), r3, r4);
        r0 = r0 + 1;
        goto L10
    L14:
        return;
    }

    private static void measureOrderedItem(TL_iv.PageListOrderedItem r2, int r3, RichEditorLimits r4) {
        if ((r2 instanceof TL_iv.TL_pageListOrderedItemText) == false) goto L7;
        addText(((TL_iv.TL_pageListOrderedItemText) r2).text, r3, r4);
        return;
    L7:
        if ((r2 instanceof TL_iv.TL_pageListOrderedItemBlocks) == false) goto L12;
        ArrayList<TL_iv.PageBlock> r22 = ((TL_iv.TL_pageListOrderedItemBlocks) r2).blocks;
        int r0 = 0;
    L10:
        if (r0 >= r22.size()) goto L14;
        measureBlock(r22.get(r0), r3, r4);
        r0 = r0 + 1;
        goto L10
    L14:
        return;
    }

    private static int richTextDepth(TL_iv.RichText r4) {
        int r0 = 0;
        if (r4 != null) goto L5;
    L19:
        return 0;
    L5:
        if ((r4 instanceof TL_iv.textEmpty) == true) goto L19;
        if ((r4 instanceof TL_iv.textPlain) == true) goto L19;
        if ((r4 instanceof TL_iv.textCustomEmoji) == true) goto L19;
        if ((r4 instanceof TL_iv.textConcat) == false) goto L18;
        ArrayList<TL_iv.RichText> r42 = ((TL_iv.textConcat) r4).texts;
        int r1 = r42.size();
        int r2 = 0;
    L14:
        if (r2 >= r1) goto L16;
        TL_iv.RichText r3 = r42.get(r2);
        r2 = r2 + 1;
        r0 = Math.max(r0, richTextDepth(r3));
        goto L14
    L16:
        return r0;
    L18:
        return richTextDepth(r4.text) + 1;
    }

    public int findExceeded(AppGlobalConfig r3) {
        if (this.length <= r3.richMessageLengthLimit.get()) goto L7;
        return 1;
    L7:
        if (this.blocks <= r3.richMessageMaxBlocks.get()) goto L11;
        return 2;
    L11:
        if (this.depth <= r3.richMessageMaxDepth.get()) goto L15;
        return 3;
    L15:
        if (this.media <= r3.richMessageMaxMedia.get()) goto L19;
        return 4;
    L19:
        if (this.tableCols <= r3.richMessageMaxTableCols.get()) goto L22;
        return 5;
    L22:
        return 0;
    }
}
