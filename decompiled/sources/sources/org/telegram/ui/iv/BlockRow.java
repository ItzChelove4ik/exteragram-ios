package org.telegram.ui.iv;

import java.util.ArrayList;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes7.dex */
public class BlockRow {
    private static long ID_GEN = 1;
    public long authorQuoteId;
    public TL_iv.PageBlock block;
    public boolean checkbox;
    public boolean checked;
    public boolean detailsEnd;
    public boolean firstBlock;
    public final long id;
    public boolean itemStart;
    public int level;
    public MediaUploadState media;
    public ArrayList<MediaUploadState> medias;
    public int num;
    public final ArrayList<RichContainer> path;
    public int quoteBottomEdge;
    public boolean quoteFirst;
    public final ArrayList<Long> quoteIds;
    public boolean quoteLast;
    public int quoteTopEdge;
    public boolean singleParagraph;
    public boolean titleAutoBold;
    public boolean titleAutoBoldInitialized;

    static {
    }

    public BlockRow(TL_iv.PageBlock r2) {
        this(r2, 0, 0);
    }

    public BlockRow(TL_iv.PageBlock r7, int r8, int r9) {
        long r4 = ID_GEN;
        ID_GEN = 1 + r4;
        this(r7, r8, r9, r4);
    }

    public BlockRow(TL_iv.PageBlock r2, int r3, int r4, long r5) {
        this.path = new ArrayList();
        this.quoteIds = new ArrayList();
        this.block = r2;
        this.level = r3;
        this.num = r4;
        this.id = r5;
    }

    public boolean isChecklist() {
        if (this.level > 0) goto L5;
        return false;
    L5:
        if (this.checkbox == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isInList() {
        if (this.level <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isOrdered() {
        if (this.num <= 0) goto L6;
        return true;
    L6:
        return false;
    }
}
