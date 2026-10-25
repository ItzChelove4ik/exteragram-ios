package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes7.dex */
public class RichMathCell extends RichBlockCell implements Theme.Colorable, TextSelectionHelper.ArticleSelectableView {
    private Bitmap bitmap;
    private Delegate delegate;
    private final ImageView image;
    private int paintColor;
    private final int[] rect;
    private final Theme.ResourcesProvider resourcesProvider;
    private final HorizontalScrollView scrollView;
    private final Paint selectionPaint;

    public interface Delegate {
        TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper();
    }

    public static final class Factory extends UItem.UItemFactory<RichMathCell> {
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
            ((RichMathCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichMathCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new RichMathCell(r1, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public RichMathCell(Context r7, Theme.ResourcesProvider r8) {
        super(r7);
        this.selectionPaint = new Paint(1);
        this.paintColor = 0;
        this.rect = new int[4];
        this.resourcesProvider = r8;
        setWillNotDraw(false);
        setBlockPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(6.0f));
        ImageView r82 = new ImageView(r7);
        this.image = r82;
        FrameLayout r2 = new FrameLayout(r7);
        r2.addView(r82, new FrameLayout.LayoutParams(-2, -2, 17));
        HorizontalScrollView r83 = new HorizontalScrollView(r7);
        this.scrollView = r83;
        r83.setHorizontalScrollBarEnabled(false);
        r83.setClipToPadding(false);
        r83.setPadding(0, 0, 0, 0);
        r83.setFillViewport(true);
        r83.addView(r2, new FrameLayout.LayoutParams(-2, -2));
        addView(r83, LayoutHelper.createFrame(-1, -2, 16));
        updateColors();
    }

    private String getSource() {
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L8;
        TL_iv.PageBlock r12 = r1.block;
        if ((r12 instanceof TL_iv.pageBlockMath) == true) goto L7;
        return null;
    L7:
        return ((TL_iv.pageBlockMath) r12).source;
    L8:
        return null;
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

    private boolean isScrollable() {
        if (this.bitmap != null) goto L5;
        return false;
    L5:
        if (this.image.getWidth() <= this.scrollView.getWidth()) goto L10;
        return true;
    L10:
        return false;
    }

    private void selectionRect(int[] r10) {
        int r0 = getPaddingTop();
        int r1 = getHeight() - getPaddingBottom();
        if (isScrollable() == false) goto L6;
        r10[0] = getPaddingLeft();
        r10[1] = r0;
        r10[2] = getWidth() - getPaddingRight();
        r10[3] = r1;
        return;
    L6:
        Bitmap r2 = this.bitmap;
        if (r2 == null) goto L9;
        int r22 = r2.getWidth();
    L10:
        int r9 = (getWidth() - r22) / 2;
        r10[0] = r9 - AndroidUtilities.dp(4.0f);
        r10[1] = r0;
        r10[2] = (r9 + r22) + AndroidUtilities.dp(4.0f);
        r10[3] = r1;
        return;
    L9:
        r22 = Math.max(1, getWidth() / 2);
        goto L10
    }

    public void bind(BlockRow r1, Delegate r2) {
        this.currentRow = r1;
        this.delegate = r2;
        bindBlockInset(r1);
        rebuild();
    }

    @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
    public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r5) {
        selectionRect(this.rect);
        int[] r4 = this.rect;
        r5.add(RichBlockSelection.of(r4[0], r4[1], r4[2], r4[3]));
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public BlockRow getRow() {
        return this.currentRow;
    }

    public boolean isPressOnMath(int r3, int r4) {
        if (this.bitmap != null) goto L5;
        return false;
    L5:
        int[] r0 = new int[4];
        selectionRect(r0);
        if (r3 >= r0[0]) goto L8;
    L14:
        return false;
    L8:
        if (r3 > r0[2]) goto L14;
        if (r4 < r0[1]) goto L14;
        if (r4 > r0[3]) goto L14;
        return true;
    }

    @Override // android.view.View
    public void onDraw(Canvas r11) {
        if (this.paintColor == Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider)) goto L6;
        updateColors();
    L6:
        if (this.bitmap != null) goto L8;
        return;
    L8:
        if (isCellSelected() == false) goto L12;
        selectionRect(this.rect);
        int[] r0 = this.rect;
        r11.drawRoundRect(r0[0], r0[1], r0[2], r0[3], AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.selectionPaint);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
    }

    public void rebuild() {
        this.bitmap = null;
        this.scrollView.scrollTo(0, 0);
        String r0 = getSource();
        if (TextUtils.isEmpty(r0) == true) goto L7;
        Latex r02 = Latex.render(r0, AndroidUtilities.dp(SharedConfig.fontSize + 4), false);
        if (r02 == null) goto L7;
        this.bitmap = r02.bitmap;
    L7:
        this.image.setImageBitmap(this.bitmap);
        invalidate();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.selectionPaint.setColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, this.resourcesProvider));
        this.paintColor = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider);
        this.image.setColorFilter(new PorterDuffColorFilter(this.paintColor, PorterDuff.Mode.SRC_IN));
        invalidate();
    }
}
