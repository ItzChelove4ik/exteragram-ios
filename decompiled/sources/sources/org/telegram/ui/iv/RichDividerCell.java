package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes7.dex */
public class RichDividerCell extends RichBlockCell implements Theme.Colorable, TextSelectionHelper.ArticleSelectableView {
    private boolean blockRtl;
    private Delegate delegate;
    private final Paint paint;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Paint selectionPaint;

    public interface Delegate {
        TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper();
    }

    public static final class Factory extends UItem.UItemFactory<RichDividerCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(BlockRow r1, Delegate r2) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            r0.object2 = r2;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            ((RichDividerCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichDividerCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new RichDividerCell(r1, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public RichDividerCell(Context r2, Theme.ResourcesProvider r3) {
        super(r2);
        this.paint = new Paint(1);
        this.selectionPaint = new Paint(1);
        this.resourcesProvider = r3;
        setWillNotDraw(false);
        updateColors();
    }

    private boolean isCellSelected() {
        Delegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return false;
    L5:
        TextSelectionHelper.ArticleTextSelectionHelper r02 = r0.getSelectionHelper();
        if (r02 != null) goto L8;
    L22:
        return false;
    L8:
        if (r02.isInSelectionMode() == false) goto L22;
        if ((getParent() instanceof RecyclerView) == true) goto L13;
        return false;
    L13:
        int r3 = ((RecyclerView) getParent()).getChildAdapterPosition(this);
        if (r3 >= 0) goto L17;
        return false;
    L17:
        if (r3 < r02.getStartCell()) goto L22;
        if (r3 > r02.getEndCell()) goto L22;
        return true;
    }

    private int regionHi() {
        int r0 = getMeasuredWidth();
        if (this.blockRtl == false) goto L5;
        int r2 = blockInset();
    L7:
        return r0 - r2;
    L5:
        r2 = 0;
        goto L7
    }

    private int regionLo() {
        if (this.blockRtl == false) goto L7;
        return 0;
    L7:
        return blockInset();
    }

    public void bind(BlockRow r1, Delegate r2) {
        this.currentRow = r1;
        this.delegate = r2;
        this.blockRtl = RichBlockChrome.rtl();
        bindBlockInset(r1);
    }

    @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
    public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r4) {
        int r0 = regionLo();
        int r1 = (regionHi() - r0) / 4;
        int r3 = regionHi() - r1;
        r4.add(RichBlockSelection.of((r0 + r1) - AndroidUtilities.dp(12.0f), 0, r3 + AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f)));
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public BlockRow getRow() {
        return this.currentRow;
    }

    @Override // org.telegram.ui.iv.RichBlockCell
    public void onBlockInsetChanged(int r1) {
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        int r0 = regionLo();
        int r1 = (regionHi() - r0) / 4;
        int r02 = r0 + r1;
        int r2 = regionHi() - r1;
        if (isCellSelected() == false) goto L5;
        Canvas r4 = r13;
        r4.drawRoundRect(r02 - AndroidUtilities.dp(12.0f), 0.0f, AndroidUtilities.dp(12.0f) + r2, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.selectionPaint);
    L6:
        float r132 = (AndroidUtilities.dp(12.0f) - AndroidUtilities.dp(1.0f)) / 2.0f;
        RectF r3 = AndroidUtilities.rectTmp;
        r3.set(r02, r132, r2, AndroidUtilities.dp(1.0f) + r132);
        r4.drawRoundRect(r3, AndroidUtilities.dp(0.5f), AndroidUtilities.dp(0.5f), this.paint);
        return;
    L5:
        r4 = r13;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(12.0f));
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.paint.setColor(Theme.getColor(Theme.key_chat_inDivider, this.resourcesProvider));
        this.selectionPaint.setColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, this.resourcesProvider));
    }
}
