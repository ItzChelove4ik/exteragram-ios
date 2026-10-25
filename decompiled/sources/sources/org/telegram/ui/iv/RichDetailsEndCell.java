package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes7.dex */
public class RichDetailsEndCell extends View implements Theme.Colorable {
    private BlockRow currentRow;
    private final Paint paint;
    private final Theme.ResourcesProvider resourcesProvider;

    public static final class Factory extends UItem.UItemFactory<RichDetailsEndCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(BlockRow r1) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            ((RichDetailsEndCell) r1).bind((BlockRow) r2.object);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichDetailsEndCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new RichDetailsEndCell(r1, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public RichDetailsEndCell(Context r1, Theme.ResourcesProvider r2) {
        super(r1);
        this.paint = new Paint();
        this.resourcesProvider = r2;
        updateColors();
    }

    public void bind(BlockRow r1) {
        this.currentRow = r1;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public BlockRow getRow() {
        return this.currentRow;
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        r9.drawRect(0.0f, AndroidUtilities.dp(6.0f), getMeasuredWidth(), AndroidUtilities.dp(6.0f) + 1, this.paint);
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(6.0f) + 1);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.paint.setColor(Theme.getColor(Theme.key_chat_inArticleDetailsLine, this.resourcesProvider));
    }
}
