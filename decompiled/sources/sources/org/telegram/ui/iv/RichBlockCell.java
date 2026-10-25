package org.telegram.ui.iv;

import android.content.Context;
import android.widget.FrameLayout;

/* loaded from: classes7.dex */
public abstract class RichBlockCell extends FrameLayout implements RichInsetCell {
    private int basePadBottom;
    private int basePadLeft;
    private int basePadRight;
    private int basePadTop;
    private int blockInset;
    protected BlockRow currentRow;
    private final RichBlockInset insetAnim;

    public static /* synthetic */ void $r8$lambda$12MoSetIkGkacVLphJ_5NHXT0H0(RichBlockCell r0, int r1) {
        r0.applyBlockInset(r1);
    }

    public RichBlockCell(Context r1) {
        super(r1);
        this.insetAnim = new RichBlockInset();
    }

    private void applyBlockInset(int r1) {
        this.blockInset = r1;
        onBlockInsetChanged(r1);
    }

    public void bindBlockInset(BlockRow r3) {
        this.insetAnim.apply(r3, new RichBlockCell$$ExternalSyntheticLambda0(this));
    }

    public int blockInset() {
        return this.blockInset;
    }

    public int nestedContentMargin() {
        return 0;
    }

    public void onBlockInsetChanged(int r11) {
        int r0 = RichBlockChrome.insetEndFor(this.currentRow);
        if (r11 > 0) goto L7;
        if (r0 > 0) goto L7;
        int r1 = 0;
    L8:
        BlockRow r2 = this.currentRow;
        if (r2 != null) goto L11;
    L14:
        int r22 = this.basePadTop;
    L13:
        int r7 = r22;
        BlockRow r23 = this.currentRow;
        if (r23 != null) goto L18;
    L21:
        int r24 = this.basePadBottom;
    L20:
        int r9 = r24;
        RichBlockChrome.applyInsetPx(this, r11 + r1, r0 + r1, this.basePadLeft, r7, this.basePadRight, r9);
        return;
    L18:
        if (r23.quoteLast == false) goto L21;
        r24 = RichBlockChrome.quoteBottomPad(r23);
        goto L20
    L11:
        if (r2.quoteFirst == false) goto L14;
        r22 = RichBlockChrome.quoteTopPad(r2);
    L7:
        r1 = nestedContentMargin();
        goto L8
    }

    @Override // org.telegram.ui.iv.RichInsetCell
    public void resyncBlockInset(boolean r4) {
        this.insetAnim.apply(this.currentRow, new RichBlockCell$$ExternalSyntheticLambda0(this), r4);
    }

    public void setBlockPadding(int r7, int r8, int r9, int r10) {
        this.basePadLeft = r7;
        this.basePadTop = r8;
        this.basePadRight = r9;
        this.basePadBottom = r10;
        RichBlockChrome.applyInsetPx(this, this.blockInset, r7, r8, r9, r10);
    }
}
