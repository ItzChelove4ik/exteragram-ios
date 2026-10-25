package org.telegram.ui.iv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_keyboard;

/* loaded from: classes7.dex */
public abstract class RichMessageButtonUsers {
    public static ArrayList<TLRPC.InputUser> collect(int r4, ArrayList<TL_iv.PageBlock> r5) {
        LinkedHashSet r0 = new LinkedHashSet();
        if (r5 == null) goto L7;
        int r1 = r5.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L7;
        TL_iv.PageBlock r3 = r5.get(r2);
        r2 = r2 + 1;
        collectBlock(r3, r0);
    L7:
        ArrayList<TLRPC.InputUser> r52 = new ArrayList(r0.size());
        MessagesController r42 = MessagesController.getInstance(r4);
        Iterator r02 = r0.iterator();
    L9:
        if (r02.hasNext() == false) goto L16;
        Long r12 = (Long) r02.next();
        r12.longValue();
        TLRPC.User r13 = r42.getUser(r12);
        if (r13 == null) goto L9;
        TLRPC.InputUser r14 = r42.getInputUser(r13);
        if ((r14 instanceof TLRPC.TL_inputUserEmpty) == true) goto L9;
        r52.add(r14);
        goto L9
    L16:
        return r52;
    }

    private static void collectBlock(TL_iv.PageBlock r7, LinkedHashSet<Long> r8) {
        if (r7 == null) goto L127;
        collectText(r7.text, r8);
        collectCaption(r7.caption, r8);
        int r1 = 0;
        if ((r7 instanceof TL_iv.pageBlockButtonRow) == false) goto L15;
        ArrayList<TL_keyboard.PageButton> r72 = ((TL_iv.pageBlockButtonRow) r7).buttons;
        if (r72 == null) goto L122;
        int r0 = r72.size();
    L9:
        if (r1 >= r0) goto L128;
        TL_keyboard.PageButton r2 = r72.get(r1);
        r1 = r1 + 1;
        TL_keyboard.PageButton r22 = r2;
        if (r22 == null) goto L9;
        collectType(r22.type, r8);
        collectText(r22.text, r8);
        goto L9
    L128:
        return;
    L122:
        return;
    L15:
        if ((r7 instanceof TL_iv.pageBlockBlockquote) == false) goto L19;
        collectText(((TL_iv.pageBlockBlockquote) r7).caption, r8);
        return;
    L19:
        if ((r7 instanceof TL_iv.pageBlockPullquote) == false) goto L23;
        collectText(((TL_iv.pageBlockPullquote) r7).caption, r8);
        return;
    L23:
        if ((r7 instanceof TL_iv.pageBlockBlockquoteBlocks) == false) goto L27;
        TL_iv.pageBlockBlockquoteBlocks r73 = (TL_iv.pageBlockBlockquoteBlocks) r7;
        collectText(r73.caption, r8);
        collectBlocks(r73.blocks, r8);
        return;
    L27:
        if ((r7 instanceof TL_iv.pageBlockDetails) == false) goto L31;
        TL_iv.pageBlockDetails r74 = (TL_iv.pageBlockDetails) r7;
        collectText(r74.title, r8);
        collectBlocks(r74.blocks, r8);
        return;
    L31:
        if ((r7 instanceof TL_iv.pageBlockList) == false) goto L43;
        ArrayList<TL_iv.PageListItem> r75 = ((TL_iv.pageBlockList) r7).items;
        if (r75 == null) goto L123;
        int r02 = r75.size();
    L35:
        if (r1 >= r02) goto L129;
        TL_iv.PageListItem r23 = r75.get(r1);
        r1 = r1 + 1;
        TL_iv.PageListItem r24 = r23;
        if ((r24 instanceof TL_iv.TL_pageListItemText) == true) goto L38;
        if ((r24 instanceof TL_iv.TL_pageListItemBlocks) == false) goto L35;
        collectBlocks(((TL_iv.TL_pageListItemBlocks) r24).blocks, r8);
        goto L35
    L38:
        collectText(((TL_iv.TL_pageListItemText) r24).text, r8);
        goto L35
    L129:
        return;
    L123:
        return;
    L43:
        if ((r7 instanceof TL_iv.pageBlockOrderedList) == false) goto L55;
        ArrayList<TL_iv.PageListOrderedItem> r76 = ((TL_iv.pageBlockOrderedList) r7).items;
        if (r76 == null) goto L124;
        int r03 = r76.size();
    L47:
        if (r1 >= r03) goto L130;
        TL_iv.PageListOrderedItem r25 = r76.get(r1);
        r1 = r1 + 1;
        TL_iv.PageListOrderedItem r26 = r25;
        if ((r26 instanceof TL_iv.TL_pageListOrderedItemText) == true) goto L50;
        if ((r26 instanceof TL_iv.TL_pageListOrderedItemBlocks) == false) goto L47;
        collectBlocks(((TL_iv.TL_pageListOrderedItemBlocks) r26).blocks, r8);
        goto L47
    L50:
        collectText(((TL_iv.TL_pageListOrderedItemText) r26).text, r8);
        goto L47
    L130:
        return;
    L124:
        return;
    L55:
        if ((r7 instanceof TL_iv.pageBlockTable) == false) goto L71;
        TL_iv.pageBlockTable r77 = (TL_iv.pageBlockTable) r7;
        collectText(r77.title, r8);
        ArrayList<TL_iv.pageTableRow> r78 = r77.rows;
        if (r78 == null) goto L125;
        int r04 = r78.size();
        int r27 = 0;
    L59:
        if (r27 >= r04) goto L131;
        TL_iv.pageTableRow r3 = r78.get(r27);
        r27 = r27 + 1;
        TL_iv.pageTableRow r32 = r3;
        if (r32 == null) goto L59;
        ArrayList<TL_iv.pageTableCell> r33 = r32.cells;
        if (r33 == null) goto L59;
        int r4 = r33.size();
        int r5 = 0;
    L66:
        if (r5 >= r4) goto L59;
        TL_iv.pageTableCell r6 = r33.get(r5);
        r5 = r5 + 1;
        TL_iv.pageTableCell r62 = r6;
        if (r62 == null) goto L66;
        collectText(r62.text, r8);
        goto L66
    L131:
        return;
    L125:
        return;
    L71:
        if ((r7 instanceof TL_iv.pageBlockCollage) == false) goto L75;
        collectBlocks(((TL_iv.pageBlockCollage) r7).items, r8);
        return;
    L75:
        if ((r7 instanceof TL_iv.pageBlockSlideshow) == false) goto L79;
        collectBlocks(((TL_iv.pageBlockSlideshow) r7).items, r8);
        return;
    L79:
        if ((r7 instanceof TL_iv.pageBlockEmbedPost) == false) goto L83;
        collectBlocks(((TL_iv.pageBlockEmbedPost) r7).blocks, r8);
        return;
    L83:
        if ((r7 instanceof TL_iv.pageBlockCover) == false) goto L87;
        collectBlock(((TL_iv.pageBlockCover) r7).cover, r8);
        return;
    L87:
        if ((r7 instanceof TL_iv.pageBlockRelatedArticles) == false) goto L126;
        collectText(((TL_iv.pageBlockRelatedArticles) r7).title, r8);
        return;
    L126:
        return;
    }

    private static void collectBlocks(ArrayList<TL_iv.PageBlock> r3, LinkedHashSet<Long> r4) {
        if (r3 == null) goto L7;
        int r0 = r3.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L9;
        TL_iv.PageBlock r2 = r3.get(r1);
        r1 = r1 + 1;
        collectBlock(r2, r4);
        goto L5
    L9:
        return;
    }

    private static void collectCaption(TL_iv.PageCaption r1, LinkedHashSet<Long> r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        collectText(r1.text, r2);
        collectText(r1.credit, r2);
    }

    private static void collectText(TL_iv.RichText r3, LinkedHashSet<Long> r4) {
        if (r3 != null) goto L5;
        return;
    L5:
        if ((r3 instanceof TL_iv.textButton) == false) goto L8;
        collectType(((TL_iv.textButton) r3).type, r4);
    L10:
        collectText(r3.text, r4);
        ArrayList<TL_iv.RichText> r32 = r3.texts;
        if (r32 == null) goto L15;
        int r0 = r32.size();
        int r1 = 0;
    L13:
        if (r1 >= r0) goto L18;
        TL_iv.RichText r2 = r32.get(r1);
        r1 = r1 + 1;
        collectText(r2, r4);
        goto L13
    L18:
        return;
    L15:
        return;
    L8:
        if ((r3 instanceof TL_iv.textDiff) == false) goto L10;
        collectText(((TL_iv.textDiff) r3).old_text, r4);
        goto L10
    }

    private static void collectType(TL_keyboard.InlineButtonType r4, LinkedHashSet<Long> r5) {
        if ((r4 instanceof TL_keyboard.TL_inlineButtonTypeUserProfile) == false) goto L8;
        long r0 = ((TL_keyboard.TL_inlineButtonTypeUserProfile) r4).user_id;
        if (r0 == 0) goto L9;
        r5.add(Long.valueOf(r0));
        return;
    L9:
        return;
    }
}
