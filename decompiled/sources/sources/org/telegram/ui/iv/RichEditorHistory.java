package org.telegram.ui.iv;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_keyboard;

/* loaded from: classes7.dex */
public class RichEditorHistory {
    private Snapshot baseline;
    private final Runnable commitRunnable;
    private final Delegate delegate;
    private boolean dirty;
    private final ArrayDeque<Snapshot> redoStack;
    private boolean restoring;
    private final ArrayDeque<Snapshot> undoStack;

    public interface Delegate {
        FocusState captureFocus();

        ArrayList<BlockRow> getRows();

        void onHistoryChanged();

        void restoreRows(List<BlockRow> r1, FocusState r2);
    }

    public static final class FocusState {
        public static final FocusState NONE = null;
        public final int childIndex;
        public final long rowId;
        public final int selEnd;
        public final int selStart;

        static {
            NONE = new FocusState(-1, -1, 0, 0);
        }

        public FocusState(long r1, int r3, int r4, int r5) {
            this.rowId = r1;
            this.childIndex = r3;
            this.selStart = r4;
            this.selEnd = r5;
        }
    }

    public static final class RowState {
        final byte[] blockData;
        final boolean checkbox;
        final boolean checked;
        final boolean detailsEnd;
        final long id;
        final int level;
        final MediaUploadState media;
        final ArrayList<MediaUploadState> medias;
        final int num;
        final ArrayList<Long> quoteIds;

        public RowState(long r1, byte[] r3, int r4, int r5, boolean r6, boolean r7, boolean r8, MediaUploadState r9, ArrayList<MediaUploadState> r10, ArrayList<Long> r11) {
            this.id = r1;
            this.blockData = r3;
            this.level = r4;
            this.num = r5;
            this.checkbox = r6;
            this.checked = r7;
            this.detailsEnd = r8;
            this.media = r9;
            this.medias = r10;
            this.quoteIds = r11;
        }
    }

    public static final class Snapshot {
        final FocusState focus;
        final RowState[] rows;

        public Snapshot(RowState[] r1, FocusState r2) {
            this.rows = r1;
            this.focus = r2;
        }
    }

    public static /* synthetic */ void $r8$lambda$YmsGXRQGmFPAXRQRbb5MV0wrrVY(RichEditorHistory r0) {
        r0.commit();
    }

    public RichEditorHistory(Delegate r2) {
        this.undoStack = new ArrayDeque();
        this.redoStack = new ArrayDeque();
        this.commitRunnable = new RichEditorHistory$$ExternalSyntheticLambda0(this);
        this.delegate = r2;
        this.baseline = capture();
    }

    private void applyRestore(Snapshot r13) {
        this.dirty = false;
        this.restoring = true;
        ArrayList r1 = new ArrayList(r13.rows.length);
        RowState[] r2 = r13.rows;
        int r3 = r2.length;
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L12;
        RowState r5 = r2[r4];
        BlockRow r6 = new BlockRow(deserializeBlock(r5.blockData), r5.level, r5.num, r5.id);
        r6.checkbox = r5.checkbox;
        r6.checked = r5.checked;
        r6.detailsEnd = r5.detailsEnd;
        r6.media = r5.media;
        if (r5.medias == null) goto L7;
        ArrayList<MediaUploadState> r7 = new ArrayList(r5.medias);
    L8:
        r6.medias = r7;
        ArrayList<Long> r52 = r5.quoteIds;
        if (r52 == null) goto L11;
        r6.quoteIds.addAll(r52);
    L11:
        r1.add(r6);
        r4 = r4 + 1;
        goto L3
    L7:
        r7 = null;
        goto L8
    L12:
        this.delegate.restoreRows(r1, r13.focus);
        this.restoring = false;
        this.delegate.onHistoryChanged();
    }

    private Snapshot capture() {
        ArrayList<BlockRow> r1 = this.delegate.getRows();
        HashMap r2 = new HashMap();
        Snapshot r3 = this.baseline;
        int r4 = 0;
        if (r3 == null) goto L7;
        RowState[] r32 = r3.rows;
        int r5 = r32.length;
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L7;
        RowState r7 = r32[r6];
        r2.put(Long.valueOf(r7.id), r7);
        r6 = r6 + 1;
    L7:
        RowState[] r33 = new RowState[r1.size()];
    L9:
        if (r4 >= r1.size()) goto L39;
        BlockRow r52 = r1.get(r4);
        byte[] r10 = serializeBlock(r52.block);
        RowState r62 = (RowState) r2.get(Long.valueOf(r52.id));
        if (r62 != null) goto L13;
    L31:
        long r8 = r52.id;
        int r11 = r52.level;
        int r12 = r52.num;
        boolean r13 = r52.checkbox;
        boolean r14 = r52.checked;
        boolean r15 = r52.detailsEnd;
        MediaUploadState r63 = r52.media;
        ArrayList<BlockRow> r19 = r1;
        if (r52.medias == null) goto L35;
        HashMap r20 = r2;
        ArrayList r16 = new ArrayList(r52.medias);
    L36:
        r33[r4] = new RowState(r8, r10, r11, r12, r13, r14, r15, r63, r16, new ArrayList(r52.quoteIds));
    L37:
        r4 = r4 + 1;
        r1 = r19;
        r2 = r20;
        goto L9
    L35:
        r20 = r2;
        r16 = null;
        goto L36
    L13:
        if (r62.level != r52.level) goto L31;
        if (r62.num != r52.num) goto L31;
        if (r62.checkbox != r52.checkbox) goto L31;
        if (r62.checked != r52.checked) goto L31;
        if (r62.detailsEnd != r52.detailsEnd) goto L31;
        if (r62.media != r52.media) goto L31;
        if (sameMedias(r62.medias, r52.medias) == false) goto L31;
        if (r62.quoteIds.equals(r52.quoteIds) == false) goto L31;
        if (Arrays.equals(r62.blockData, r10) == false) goto L31;
        r33[r4] = r62;
        r19 = r1;
        r20 = r2;
        goto L37
    L39:
        return new Snapshot(r33, this.delegate.captureFocus());
    }

    private void commit() {
        AndroidUtilities.cancelRunOnUIThread(this.commitRunnable);
        if (this.dirty == true) goto L5;
        return;
    L5:
        if (this.restoring == true) goto L18;
        Snapshot r0 = capture();
        this.dirty = false;
        if (sameAs(this.baseline, r0) == true) goto L19;
        this.undoStack.addLast(this.baseline);
    L12:
        if (this.undoStack.size() <= 150) goto L14;
        this.undoStack.removeFirst();
        goto L12
    L14:
        this.redoStack.clear();
        this.baseline = r0;
        this.delegate.onHistoryChanged();
        return;
    L19:
        return;
    }

    private static TL_iv.PageBlock deserializeBlock(byte[] r2) {
        SerializedData r0 = new SerializedData(r2);     // Catch: Throwable -> L5
        TL_iv.PageBlock r22 = TL_iv.PageBlock.TLdeserialize(r0, r0.readInt32(true), true);     // Catch: Throwable -> L5
        r0.cleanup();     // Catch: Throwable -> L5
        if (r22 == null) goto L7;
        return r22;
    L7:
        TL_iv.pageBlockParagraph r23 = new TL_iv.pageBlockParagraph();
        r23.text = new TL_iv.textEmpty();
        return r23;
    L5:
        th = move-exception;
        FileLog.e(th);
        goto L7
    }

    private static TL_iv.PageCaption emptyCaption() {
        TL_iv.PageCaption r0 = new TL_iv.PageCaption();
        r0.text = emptyRichText();
        r0.credit = emptyRichText();
        return r0;
    }

    private static TL_iv.RichText emptyRichText() {
        return new TL_iv.textEmpty();
    }

    private static void normalize(TL_iv.PageBlock r8) {
        if (r8 != null) goto L5;
        return;
    L5:
        if (r8.text != null) goto L8;
        r8.text = emptyRichText();
    L8:
        if (r8.caption != null) goto L10;
        r8.caption = emptyCaption();
    L10:
        int r1 = 0;
        if ((r8 instanceof TL_iv.pageBlockBlockquote) == false) goto L16;
        TL_iv.pageBlockBlockquote r0 = (TL_iv.pageBlockBlockquote) r8;
        if (r0.caption != null) goto L32;
        r0.caption = emptyRichText();
    L32:
        if ((r8 instanceof TL_iv.pageBlockPreformatted) == false) goto L38;
        TL_iv.pageBlockPreformatted r82 = (TL_iv.pageBlockPreformatted) r8;
        if (r82.language != null) goto L100;
        r82.language = _UrlKt.FRAGMENT_ENCODE_SET;
        return;
    L100:
        return;
    L38:
        if ((r8 instanceof TL_iv.pageBlockMath) == false) goto L44;
        TL_iv.pageBlockMath r83 = (TL_iv.pageBlockMath) r8;
        if (r83.source != null) goto L122;
        r83.source = _UrlKt.FRAGMENT_ENCODE_SET;
        return;
    L122:
        return;
    L44:
        if ((r8 instanceof TL_iv.pageBlockMap) == false) goto L50;
        TL_iv.pageBlockMap r84 = (TL_iv.pageBlockMap) r8;
        if (r84.geo != null) goto L123;
        r84.geo = new TLRPC.TL_geoPointEmpty();
        return;
    L123:
        return;
    L50:
        if ((r8 instanceof TL_iv.pageBlockTable) == false) goto L73;
        TL_iv.pageBlockTable r85 = (TL_iv.pageBlockTable) r8;
        if (r85.title != null) goto L55;
        r85.title = emptyRichText();
    L55:
        if (r85.rows != null) goto L57;
        r85.rows = new ArrayList();
    L57:
        ArrayList<TL_iv.pageTableRow> r86 = r85.rows;
        int r02 = r86.size();
        int r2 = 0;
    L58:
        if (r2 >= r02) goto L126;
        TL_iv.pageTableRow r3 = r86.get(r2);
        r2 = r2 + 1;
        TL_iv.pageTableRow r32 = r3;
        if (r32 == null) goto L58;
        if (r32.cells != null) goto L65;
        r32.cells = new ArrayList();
    L65:
        ArrayList<TL_iv.pageTableCell> r33 = r32.cells;
        int r4 = r33.size();
        int r5 = 0;
    L66:
        if (r5 >= r4) goto L58;
        TL_iv.pageTableCell r6 = r33.get(r5);
        r5 = r5 + 1;
        TL_iv.pageTableCell r62 = r6;
        if (r62 == null) goto L66;
        if (r62.text != null) goto L66;
        r62.text = emptyRichText();
        goto L66
    L126:
        return;
    L73:
        if ((r8 instanceof TL_iv.pageBlockButtonRow) == false) goto L85;
        TL_iv.pageBlockButtonRow r87 = (TL_iv.pageBlockButtonRow) r8;
        if (r87.buttons != null) goto L77;
        r87.buttons = new ArrayList();
    L77:
        ArrayList<TL_keyboard.PageButton> r88 = r87.buttons;
        int r03 = r88.size();
    L78:
        if (r1 >= r03) goto L127;
        TL_keyboard.PageButton r22 = r88.get(r1);
        r1 = r1 + 1;
        TL_keyboard.PageButton r23 = r22;
        if (r23 == null) goto L78;
        if (r23.text != null) goto L78;
        r23.text = emptyRichText();
        goto L78
    L127:
        return;
    L85:
        if ((r8 instanceof TL_iv.pageBlockCollage) == false) goto L93;
        TL_iv.pageBlockCollage r89 = (TL_iv.pageBlockCollage) r8;
        if (r89.items != null) goto L89;
        r89.items = new ArrayList();
    L89:
        ArrayList<TL_iv.PageBlock> r810 = r89.items;
        int r04 = r810.size();
    L90:
        if (r1 >= r04) goto L128;
        TL_iv.PageBlock r24 = r810.get(r1);
        r1 = r1 + 1;
        normalize(r24);
        goto L90
    L128:
        return;
    L93:
        if ((r8 instanceof TL_iv.pageBlockSlideshow) == false) goto L124;
        TL_iv.pageBlockSlideshow r811 = (TL_iv.pageBlockSlideshow) r8;
        if (r811.items != null) goto L97;
        r811.items = new ArrayList();
    L97:
        ArrayList<TL_iv.PageBlock> r812 = r811.items;
        int r05 = r812.size();
    L98:
        if (r1 >= r05) goto L129;
        TL_iv.PageBlock r25 = r812.get(r1);
        r1 = r1 + 1;
        normalize(r25);
        goto L98
    L129:
        return;
    L124:
        return;
    L16:
        if ((r8 instanceof TL_iv.pageBlockBlockquoteBlocks) == false) goto L27;
        TL_iv.pageBlockBlockquoteBlocks r06 = (TL_iv.pageBlockBlockquoteBlocks) r8;
        if (r06.blocks != null) goto L20;
        r06.blocks = new ArrayList();
    L20:
        ArrayList<TL_iv.PageBlock> r26 = r06.blocks;
        int r34 = r26.size();
        int r42 = 0;
    L21:
        if (r42 >= r34) goto L24;
        TL_iv.PageBlock r52 = r26.get(r42);
        r42 = r42 + 1;
        normalize(r52);
        goto L21
    L24:
        if (r06.caption != null) goto L32;
        r06.caption = emptyRichText();
        goto L32
    L27:
        if ((r8 instanceof TL_iv.pageBlockPullquote) == false) goto L32;
        TL_iv.pageBlockPullquote r07 = (TL_iv.pageBlockPullquote) r8;
        if (r07.caption != null) goto L32;
        r07.caption = emptyRichText();
        goto L32
    }

    private static boolean sameAs(Snapshot r4, Snapshot r5) {
        if (r4 == null) goto L18;
        if (r5 == null) goto L18;
        if (r4.rows.length == r5.rows.length) goto L9;
        return false;
    L9:
        int r1 = 0;
    L10:
        RowState[] r2 = r4.rows;
        if (r1 >= r2.length) goto L16;
        if (r2[r1] != r5.rows[r1]) goto L14;
        r1 = r1 + 1;
        goto L10
    L14:
        return false;
    L16:
        return true;
    L18:
        return false;
    }

    private static boolean sameMedias(ArrayList<MediaUploadState> r5, ArrayList<MediaUploadState> r6) {
        if (r5 != r6) goto L6;
        return true;
    L6:
        if (r5 == null) goto L19;
        if (r6 == null) goto L19;
        if (r5.size() != r6.size()) goto L19;
        int r2 = 0;
    L13:
        if (r2 >= r5.size()) goto L18;
        if (r5.get(r2) != r6.get(r2)) goto L16;
        r2 = r2 + 1;
        goto L13
    L16:
        return false;
    L18:
        return true;
    L19:
        return false;
    }

    private static byte[] serializeBlock(TL_iv.PageBlock r2) {
        normalize(r2);
        SerializedData r0 = new SerializedData(r2.getObjectSize());
        r2.serializeToStream(r0);
        byte[] r22 = r0.toByteArray();
        r0.cleanup();
        return r22;
    }

    public boolean canRedo() {
        return !this.redoStack.isEmpty();
    }

    public boolean canUndo() {
        if (this.dirty == false) goto L5;
        return true;
    L5:
        if (this.undoStack.isEmpty() == false) goto L11;
        return false;
    L11:
        return true;
    }

    public void flush() {
        AndroidUtilities.cancelRunOnUIThread(this.commitRunnable);
        commit();
    }

    public void onBeforeChange(int r2, int r3) {
        if (this.restoring == false) goto L6;
        return;
    L6:
        if (r2 > 16) goto L10;
        if (r3 > 16) goto L10;
        return;
    L10:
        flush();
    }

    public void onTyping() {
        if (this.restoring == false) goto L5;
        return;
    L5:
        this.dirty = true;
        AndroidUtilities.cancelRunOnUIThread(this.commitRunnable);
        AndroidUtilities.runOnUIThread(this.commitRunnable, 800);
        this.delegate.onHistoryChanged();
    }

    public void record() {
        if (this.restoring == false) goto L5;
        return;
    L5:
        AndroidUtilities.cancelRunOnUIThread(this.commitRunnable);
        this.dirty = true;
        commit();
    }

    public void redo() {
        flush();
        if (this.redoStack.isEmpty() == false) goto L5;
        return;
    L5:
        this.undoStack.addLast(this.baseline);
        Snapshot r0 = this.redoStack.removeLast();
        this.baseline = r0;
        applyRestore(r0);
    }

    public void resetBaseline() {
        AndroidUtilities.cancelRunOnUIThread(this.commitRunnable);
        this.undoStack.clear();
        this.redoStack.clear();
        this.baseline = capture();
        this.dirty = false;
        this.delegate.onHistoryChanged();
    }

    public void undo() {
        flush();
        if (this.undoStack.isEmpty() == false) goto L5;
        return;
    L5:
        this.redoStack.addLast(this.baseline);
        Snapshot r0 = this.undoStack.removeLast();
        this.baseline = r0;
        applyRestore(r0);
    }
}
