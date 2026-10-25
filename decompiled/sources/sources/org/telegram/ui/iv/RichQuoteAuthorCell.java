package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.Layout;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.iv.RichEditText;

/* loaded from: classes7.dex */
public class RichQuoteAuthorCell extends RichBlockCell implements Theme.Colorable, TextSelectionHelper.ArticleSelectableView {
    final RichEditText authorEditText;
    private Delegate delegate;
    private boolean hijackingSelection;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ArrayList<TextSelectionHelper.TextLayoutBlock> tmpBlocks;

    /* renamed from: org.telegram.ui.iv.RichQuoteAuthorCell$1, reason: invalid class name */
    public class AnonymousClass1 implements RichEditText.Listener {
        final /* synthetic */ RichQuoteAuthorCell this$0;

        /* renamed from: $r8$lambda$uNQWzXwVh-lBogIvPxQ8AVa2S-s, reason: not valid java name */
        public static /* synthetic */ void m21895$r8$lambda$uNQWzXwVhlBogIvPxQ8AVa2Ss(AnonymousClass1 r0, RichEditText r1, int r2, TextSelectionHelper.ArticleTextSelectionHelper r3, int r4) {
            r0.lambda$onSelectionChanged$0(r1, r2, r3, r4);
        }

        public AnonymousClass1(RichQuoteAuthorCell r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onSelectionChanged$0(RichEditText r5, int r6, TextSelectionHelper.ArticleTextSelectionHelper r7, int r8) {
            if (r5.length() >= r6) goto L5;
            return;
        L5:
            if (r5.getSelectionStart() == r5.getSelectionEnd()) goto L17;
            boolean r0 = r7.isInSelectionMode();
            RichQuoteAuthorCell r1 = this.this$0;
            if (r0 == false) goto L12;
            RichQuoteAuthorCell.m21893$$Nest$fputhijackingSelection(r1, true);
            r5.setSelection(r6);
            RichQuoteAuthorCell.m21893$$Nest$fputhijackingSelection(this.this$0, false);
            return;
        L12:
            if (r7.selectRangeOf(r1, 0, r8, r6) == false) goto L16;
            RichQuoteAuthorCell.m21893$$Nest$fputhijackingSelection(this.this$0, true);
            r5.setSelection(r6);
            RichQuoteAuthorCell.m21893$$Nest$fputhijackingSelection(this.this$0, false);
            return;
        L16:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onEnterPressed(RichEditText r2) {
            if (RichQuoteAuthorCell.m21891$$Nest$fgetdelegate(this.this$0) == null) goto L8;
            RichQuoteAuthorCell r22 = this.this$0;
            if (r22.currentRow == null) goto L9;
            RichQuoteAuthorCell.m21891$$Nest$fgetdelegate(r22).onQuoteAuthorEnter(this.this$0.currentRow);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            if (RichQuoteAuthorCell.m21891$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichQuoteAuthorCell.m21891$$Nest$fgetdelegate(this.this$0).onRequestWindowFocusable(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onSelectionChanged(RichEditText r8, int r9, int r10) {
            if (RichQuoteAuthorCell.m21892$$Nest$fgethijackingSelection(this.this$0) == true) goto L13;
            if (r9 != r10) goto L6;
            return;
        L6:
            if (RichQuoteAuthorCell.m21891$$Nest$fgetdelegate(this.this$0) == null) goto L15;
            TextSelectionHelper.ArticleTextSelectionHelper r5 = RichQuoteAuthorCell.m21891$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
            if (r5 == null) goto L16;
            r8.post(new RichQuoteAuthorCell$1$$ExternalSyntheticLambda0(this, r8, r10, r5, r9));
            return;
        L16:
            return;
        L15:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextChanged(RichEditText r1, Editable r2) {
            RichQuoteAuthorCell.m21894$$Nest$mpersist(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichQuoteAuthorCell$2, reason: invalid class name */
    public class AnonymousClass2 implements TextSelectionHelper.TextLayoutBlock {
        final /* synthetic */ RichQuoteAuthorCell this$0;
        final /* synthetic */ Layout val$layout;
        final /* synthetic */ int val$textX;
        final /* synthetic */ int val$textY;

        public AnonymousClass2(RichQuoteAuthorCell r1, Layout r2, int r3, int r4) {
            this.this$0 = r1;
            this.val$layout = r2;
            this.val$textX = r3;
            this.val$textY = r4;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public Layout getLayout() {
            return this.val$layout;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getRow() {
            return 0;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public CharSequence getText() {
            return this.val$layout.getText();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getX() {
            return this.val$textX;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getY() {
            return this.val$textY;
        }
    }

    public interface Delegate {
        TL_iv.RichText getQuoteAuthor(long r1);

        TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper();

        void onQuoteAuthorEnter(BlockRow r1);

        void onRequestWindowFocusable(RichEditText r1, boolean r2);

        void setQuoteAuthor(long r1, TL_iv.RichText r3);
    }

    public static final class Factory extends UItem.UItemFactory<RichQuoteAuthorCell> {
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
            ((RichQuoteAuthorCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichQuoteAuthorCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new RichQuoteAuthorCell(r1, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$TKoN4mCBFHXNIty6apewCR4WelE(RichQuoteAuthorCell r0) {
        r0.persist();
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21891$$Nest$fgetdelegate(RichQuoteAuthorCell r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgethijackingSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21892$$Nest$fgethijackingSelection(RichQuoteAuthorCell r0) {
        return r0.hijackingSelection;
    }

    /* renamed from: -$$Nest$fputhijackingSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21893$$Nest$fputhijackingSelection(RichQuoteAuthorCell r0, boolean r1) {
        r0.hijackingSelection = r1;
    }

    /* renamed from: -$$Nest$mpersist, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21894$$Nest$mpersist(RichQuoteAuthorCell r0) {
        r0.persist();
    }

    public RichQuoteAuthorCell(Context r6, Theme.ResourcesProvider r7) {
        super(r6);
        this.tmpBlocks = new ArrayList();
        this.resourcesProvider = r7;
        setBlockPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        RichEditText r0 = new RichEditText(r6, r7);
        this.authorEditText = r0;
        r0.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(1.0f));
        r0.setAllowNewlines(false);
        r0.setInputType(147457);
        r0.setGravity(8388659);
        r0.setTextSize(1, Math.max(8, SharedConfig.fontSize - 2));
        r0.setTypeface(AndroidUtilities.bold());
        r0.setTextColorKey(Theme.key_featuredStickers_addButton);
        r0.setAccentHint(true);
        r0.setHint(LocaleController.getString(R.string.ArticleHintAuthor));
        r0.setListener(new AnonymousClass1(this));
        r0.setDelegate(new RichQuoteAuthorCell$$ExternalSyntheticLambda0(this));
        addView(r0, LayoutHelper.createFrame(-1, -2, 51));
        updateColors();
    }

    private void persist() {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L9;
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L10;
        r0.setQuoteAuthor(r1.authorQuoteId, RichTextStyle.fromSpannable(this.authorEditText.getText()));
        return;
    L10:
        return;
    }

    public void bind(BlockRow r5, Delegate r6) {
        this.currentRow = r5;
        this.delegate = r6;
        setBlockPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        bindBlockInset(r5);
        if (r6 == null) goto L5;
        TL_iv.RichText r52 = r6.getQuoteAuthor(r5.authorQuoteId);
    L6:
        String r62 = RichTextStyle.plainOf(r52);
        if (String.valueOf(this.authorEditText.getText()).equals(r62) == true) goto L10;
        this.authorEditText.setTextSilently(Emoji.replaceEmoji(RichTextStyle.toSpannable(r52), this.authorEditText.getPaint().getFontMetricsInt(), false));
        this.authorEditText.invalidateEffects();
        return;
    L10:
        return;
    L5:
        r52 = null;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L5;
        TextSelectionHelper.ArticleTextSelectionHelper r02 = r0.getSelectionHelper();
    L6:
        if (r02 == null) goto L11;
        this.tmpBlocks.clear();
        fillTextLayoutBlocks(this.tmpBlocks);
        int r1 = 0;
    L9:
        if (r1 >= this.tmpBlocks.size()) goto L11;
        TextSelectionHelper.TextLayoutBlock r2 = this.tmpBlocks.get(r1);
        r5.save();
        r5.translate(r2.getX(), r2.getY());
        r02.draw(r5, this, r1);
        r5.restore();
        r1 = r1 + 1;
    L11:
        super.dispatchDraw(r5);
        return;
    L5:
        r02 = null;
        goto L6
    }

    @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
    public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r5) {
        Layout r0 = this.authorEditText.getLayout();
        if (r0 != null) goto L5;
        return;
    L5:
        r5.add(new AnonymousClass2(this, r0, this.authorEditText.getLeft() + this.authorEditText.getPaddingLeft(), this.authorEditText.getTop() + this.authorEditText.getPaddingTop()));
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public BlockRow getRow() {
        return this.currentRow;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.authorEditText.updateColors();
        RichEditText r0 = this.authorEditText;
        int r1 = Theme.key_featuredStickers_addButton;
        r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        this.authorEditText.setHintTextColor(Theme.multAlpha(Theme.getColor(r1, this.resourcesProvider), 0.5f));
    }
}
