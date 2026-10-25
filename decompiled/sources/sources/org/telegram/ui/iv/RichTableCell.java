package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
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
import org.telegram.ui.iv.RichEditText;
import org.telegram.ui.iv.RichEditor;

/* loaded from: classes7.dex */
public class RichTableCell extends RichBlockCell implements Theme.Colorable, TextSelectionHelper.ArticleSelectableView {
    private boolean blockRtl;
    private CellSelectionListener cellSelectionListener;
    private Delegate delegate;
    private final ViewTreeObserver.OnGlobalFocusChangeListener focusInvalidator;
    private final RichTableCellGrid grid;
    private boolean hijackingSelection;
    private TableModel model;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ScrollContent scrollContent;
    private final HorizontalScrollView scrollView;
    private final LinkedHashSet<TL_iv.pageTableCell> selectedCells;
    private final RichEditText titleEditText;
    private final ArrayList<TextSelectionHelper.TextLayoutBlock> tmpBlocks;

    /* renamed from: org.telegram.ui.iv.RichTableCell$1, reason: invalid class name */
    public class AnonymousClass1 implements RichEditText.Listener {
        final /* synthetic */ RichTableCell this$0;

        public static /* synthetic */ void $r8$lambda$TVRynmVT5vMEhjBSt_g9MahyQlI(AnonymousClass1 r0, RichEditText r1, int r2, TextSelectionHelper.ArticleTextSelectionHelper r3, int r4, int r5) {
            r0.lambda$onSelectionChanged$0(r1, r2, r3, r4, r5);
        }

        public AnonymousClass1(RichTableCell r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onSelectionChanged$0(RichEditText r3, int r4, TextSelectionHelper.ArticleTextSelectionHelper r5, int r6, int r7) {
            if (r3.length() >= r4) goto L5;
            return;
        L5:
            if (r3.getSelectionStart() != r3.getSelectionEnd()) goto L8;
            return;
        L8:
            if (r5.selectRangeOf(this.this$0, r6, r7, r4) == false) goto L12;
            RichTableCell.m21899$$Nest$fputhijackingSelection(this.this$0, true);
            r3.setSelection(r4);
            RichTableCell.m21899$$Nest$fputhijackingSelection(this.this$0, false);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onLockedInsert(RichEditText r1, CharSequence r2) {
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichTableCell.m21896$$Nest$fgetdelegate(this.this$0).onLockedInsert(r2);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichTableCell.m21896$$Nest$fgetdelegate(this.this$0).onRequestWindowFocusable(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onSelectAll(RichEditText r2) {
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L8;
            RichTableCell r22 = this.this$0;
            if (r22.currentRow != null) goto L7;
            return false;
        L7:
            return RichTableCell.m21896$$Nest$fgetdelegate(r22).onSelectAll(this.this$0.currentRow);
        L8:
            return false;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onSelectionChanged(RichEditText r9, int r10, int r11) {
            if (RichTableCell.m21898$$Nest$fgethijackingSelection(this.this$0) == true) goto L21;
            if (r10 != r11) goto L6;
            return;
        L6:
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L23;
            TextSelectionHelper.ArticleTextSelectionHelper r5 = RichTableCell.m21896$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
            if (r5 != null) goto L12;
            return;
        L12:
            if (r5.isInSelectionMode() == true) goto L14;
        L16:
            int r6 = this.this$0.titleChildPos();
            if (r6 < 0) goto L26;
            this.this$0.post(new RichTableCell$1$$ExternalSyntheticLambda0(this, r9, r11, r5, r6, r10));
            return;
        L26:
            return;
        L14:
            if (r5.getSelectedCell() != this.this$0) goto L16;
            return;
        L23:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextChanged(RichEditText r1, Editable r2) {
            RichTableCell.m21901$$Nest$mrememberTitleAutoBoldState(this.this$0);
            RichTableCell.m21900$$Nest$mpersistTitle(this.this$0);
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L8;
            RichTableCell r12 = this.this$0;
            if (r12.currentRow == null) goto L9;
            RichTableCell.m21896$$Nest$fgetdelegate(r12).onTextChanged(this.this$0.currentRow);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextWillChange(RichEditText r2, int r3, int r4) {
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L8;
            RichTableCell r22 = this.this$0;
            if (r22.currentRow == null) goto L9;
            RichTableCell.m21896$$Nest$fgetdelegate(r22).onTextWillChange(this.this$0.currentRow, r3, r4);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichTableCell$2, reason: invalid class name */
    public class AnonymousClass2 extends HorizontalScrollView {
        final /* synthetic */ RichTableCell this$0;

        public AnonymousClass2(RichTableCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onScrollChanged(int r1, int r2, int r3, int r4) {
            super.onScrollChanged(r1, r2, r3, r4);
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L9;
            TextSelectionHelper.ArticleTextSelectionHelper r12 = RichTableCell.m21896$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
            if (r12 == null) goto L9;
            if (r12.isInSelectionMode() == false) goto L9;
            r12.invalidate();
        L9:
            invalidate();
        }
    }

    /* renamed from: org.telegram.ui.iv.RichTableCell$3, reason: invalid class name */
    public class AnonymousClass3 implements RichEditText.Listener {
        final /* synthetic */ RichTableCell this$0;
        final /* synthetic */ RichTableCellHost val$host;

        public static /* synthetic */ void $r8$lambda$v0Kl2iprWkgigo8kwIsL0LAVzr0(AnonymousClass3 r0, RichEditText r1, int r2, TextSelectionHelper.ArticleTextSelectionHelper r3, int r4, int r5) {
            r0.lambda$onSelectionChanged$0(r1, r2, r3, r4, r5);
        }

        public AnonymousClass3(RichTableCell r1, RichTableCellHost r2) {
            this.this$0 = r1;
            this.val$host = r2;
        }

        private /* synthetic */ void lambda$onSelectionChanged$0(RichEditText r3, int r4, TextSelectionHelper.ArticleTextSelectionHelper r5, int r6, int r7) {
            if (r3.length() >= r4) goto L5;
            return;
        L5:
            if (r3.getSelectionStart() != r3.getSelectionEnd()) goto L8;
            return;
        L8:
            if (r5.selectRangeOf(this.this$0, r6, r7, r4) == false) goto L12;
            RichTableCell.m21899$$Nest$fputhijackingSelection(this.this$0, true);
            r3.setSelection(r4);
            RichTableCell.m21899$$Nest$fputhijackingSelection(this.this$0, false);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onLockedInsert(RichEditText r1, CharSequence r2) {
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichTableCell.m21896$$Nest$fgetdelegate(this.this$0).onLockedInsert(r2);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichTableCell.m21896$$Nest$fgetdelegate(this.this$0).onRequestWindowFocusable(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onSelectAll(RichEditText r2) {
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L8;
            RichTableCell r22 = this.this$0;
            if (r22.currentRow != null) goto L7;
            return false;
        L7:
            return RichTableCell.m21896$$Nest$fgetdelegate(r22).onSelectAll(this.this$0.currentRow);
        L8:
            return false;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onSelectionChanged(RichEditText r9, int r10, int r11) {
            if (RichTableCell.m21898$$Nest$fgethijackingSelection(this.this$0) == true) goto L21;
            if (r10 != r11) goto L6;
            return;
        L6:
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L23;
            TextSelectionHelper.ArticleTextSelectionHelper r5 = RichTableCell.m21896$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
            if (r5 != null) goto L12;
            return;
        L12:
            if (r5.isInSelectionMode() == true) goto L14;
        L16:
            int r6 = this.this$0.childPosForAnchor(this.val$host.cell);
            if (r6 < 0) goto L26;
            this.this$0.post(new RichTableCell$3$$ExternalSyntheticLambda0(this, r9, r11, r5, r6, r10));
            return;
        L26:
            return;
        L14:
            if (r5.getSelectedCell() != this.this$0) goto L16;
            return;
        L23:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onTab(RichEditText r1, boolean r2) {
            return this.this$0.moveFocusByTab(this.val$host, r2);
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextChanged(RichEditText r1, Editable r2) {
            TL_iv.pageTableCell r12 = this.val$host.cell;
            if (r12 == null) goto L5;
            TableModel.applyStyledText(r12, r2);
        L5:
            RichTableCell.m21897$$Nest$fgetgrid(this.this$0).requestLayout();
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L11;
            RichTableCell r13 = this.this$0;
            if (r13.currentRow == null) goto L12;
            RichTableCell.m21896$$Nest$fgetdelegate(r13).onTextChanged(this.this$0.currentRow);
            return;
        L12:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextWillChange(RichEditText r2, int r3, int r4) {
            if (RichTableCell.m21896$$Nest$fgetdelegate(this.this$0) == null) goto L8;
            RichTableCell r22 = this.this$0;
            if (r22.currentRow == null) goto L9;
            RichTableCell.m21896$$Nest$fgetdelegate(r22).onTextWillChange(this.this$0.currentRow, r3, r4);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichTableCell$4, reason: invalid class name */
    public class AnonymousClass4 implements TextSelectionHelper.TextLayoutBlock {
        final /* synthetic */ RichTableCell this$0;
        final /* synthetic */ Layout val$titleLayout;
        final /* synthetic */ int val$titleX;
        final /* synthetic */ int val$titleY;

        public AnonymousClass4(RichTableCell r1, Layout r2, int r3, int r4) {
            this.this$0 = r1;
            this.val$titleLayout = r2;
            this.val$titleX = r3;
            this.val$titleY = r4;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public Layout getLayout() {
            return this.val$titleLayout;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getRow() {
            return 0;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public CharSequence getText() {
            BlockRow r1 = this.this$0.currentRow;
            if (r1 == null) goto L10;
            TL_iv.PageBlock r12 = r1.block;
            if ((r12 instanceof TL_iv.pageBlockTable) == true) goto L7;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            if (((TL_iv.pageBlockTable) r12).title != null) goto L9;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L9:
            return RichTextStyle.toSpannable(((TL_iv.pageBlockTable) r12).title);
        L10:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getX() {
            return this.val$titleX;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getY() {
            return this.val$titleY;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichTableCell$5, reason: invalid class name */
    public class AnonymousClass5 implements TextSelectionHelper.TextLayoutBlock {
        final /* synthetic */ RichTableCell this$0;
        final /* synthetic */ Layout val$layout;
        final /* synthetic */ int val$rowIndex;
        final /* synthetic */ TL_iv.pageTableCell val$textCell;
        final /* synthetic */ int val$textX;
        final /* synthetic */ int val$textY;

        public AnonymousClass5(RichTableCell r1, Layout r2, int r3, int r4, int r5, TL_iv.pageTableCell r6) {
            this.this$0 = r1;
            this.val$layout = r2;
            this.val$textX = r3;
            this.val$textY = r4;
            this.val$rowIndex = r5;
            this.val$textCell = r6;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public Layout getLayout() {
            return this.val$layout;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getRow() {
            return this.val$rowIndex;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public CharSequence getText() {
            return TableModel.readStyledText(this.val$textCell);
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

    public interface CellSelectionListener {
        void onCellSelectionChanged(RichTableCell r1);
    }

    public interface Delegate {
        TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper();

        void onLockedInsert(CharSequence r1);

        void onRequestWindowFocusable(RichEditText r1, boolean r2);

        boolean onSelectAll(BlockRow r1);

        void onSpansChanged(BlockRow r1);

        void onTextChanged(BlockRow r1);

        void onTextWillChange(BlockRow r1, int r2, int r3);
    }

    public static final class Factory extends UItem.UItemFactory<RichTableCell> {
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
            ((RichTableCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichTableCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            RichTableCell r0 = new RichTableCell(r1, r5);
            r0.setBackground(new RichEditor.DraggingDrawable(Theme.getColor(Theme.key_windowBackgroundWhite, r5)));
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public final class ScrollContent extends ViewGroup {
        private final int startHandleOffset;
        final /* synthetic */ RichTableCell this$0;

        public ScrollContent(RichTableCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.startHandleOffset = AndroidUtilities.dp(16.0f);
            setClipChildren(false);
            setClipToPadding(false);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            int r12 = RichTableCell.m21897$$Nest$fgetgrid(this.this$0).getMeasuredWidth();
            int r22 = RichTableCell.m21897$$Nest$fgetgrid(this.this$0).getMeasuredHeight();
            RichTableCellGrid r32 = RichTableCell.m21897$$Nest$fgetgrid(this.this$0);
            int r0 = this.startHandleOffset;
            r32.layout(-r0, 0, r12 - r0, r22);
        }

        @Override // android.view.View
        public void onMeasure(int r4, int r5) {
            int r42 = Math.max(0, View.MeasureSpec.getSize(r4) + this.startHandleOffset);
            RichTableCell.m21897$$Nest$fgetgrid(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r42, Integer.MIN_VALUE), r5);
            int r43 = RichTableCell.m21897$$Nest$fgetgrid(this.this$0).getMeasuredWidth();
            int r52 = RichTableCell.m21897$$Nest$fgetgrid(this.this$0).getMeasuredHeight();
            setMeasuredDimension(Math.max(0, r43 - this.startHandleOffset), r52);
        }
    }

    public static /* synthetic */ void $r8$lambda$1pG20kDwuckEqfkRweR76uTIVBA(RichTableCell r0, View r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6EB8QmHkaH2mvssrApeYEGKBn3k(RichTableCell r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$Hf8Q5VAdwTzZBxmjlR40w1rQW1A(RichTableCell r0, TL_iv.pageTableCell r1) {
        r0.lambda$focusCellAt$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$skvUOyh3L0dHhek_QSUTlpGYgWY(RichTableCell r0, RichTableCellHost r1) {
        r0.lambda$wireCellListeners$3(r1);
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21896$$Nest$fgetdelegate(RichTableCell r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetgrid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichTableCellGrid m21897$$Nest$fgetgrid(RichTableCell r0) {
        return r0.grid;
    }

    /* renamed from: -$$Nest$fgethijackingSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21898$$Nest$fgethijackingSelection(RichTableCell r0) {
        return r0.hijackingSelection;
    }

    /* renamed from: -$$Nest$fputhijackingSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21899$$Nest$fputhijackingSelection(RichTableCell r0, boolean r1) {
        r0.hijackingSelection = r1;
    }

    /* renamed from: -$$Nest$mpersistTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21900$$Nest$mpersistTitle(RichTableCell r0) {
        r0.persistTitle();
    }

    /* renamed from: -$$Nest$mrememberTitleAutoBoldState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21901$$Nest$mrememberTitleAutoBoldState(RichTableCell r0) {
        r0.rememberTitleAutoBoldState();
    }

    public RichTableCell(Context r12, Theme.ResourcesProvider r13) {
        super(r12);
        this.tmpBlocks = new ArrayList();
        this.selectedCells = new LinkedHashSet();
        this.focusInvalidator = new RichTableCell$$ExternalSyntheticLambda0(this);
        this.resourcesProvider = r13;
        setClipChildren(false);
        setClipToPadding(false);
        RichEditText r1 = new RichEditText(r12, r13);
        this.titleEditText = r1;
        r1.setAllowNewlines(false);
        r1.setInputType(147457);
        r1.setGravity(49);
        r1.setTextSize(1, Math.max(8, SharedConfig.fontSize - 2));
        r1.setIncludeFontPadding(false);
        r1.setMinHeight(0);
        r1.setBackground(null);
        r1.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f), 0);
        r1.setHint(LocaleController.getString(R.string.ArticleTableTitleHint));
        r1.setCenterEmptyHint(true);
        r1.setListener(new AnonymousClass1(this));
        r1.setDelegate(new RichTableCell$$ExternalSyntheticLambda1(this));
        addView(r1);
        AnonymousClass2 r14 = new AnonymousClass2(this, r12);
        this.scrollView = r14;
        r14.setClipChildren(false);
        r14.setClipToPadding(false);
        r14.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        addView(r14, LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, 6.0f, 0.0f, 0.0f));
        RichTableCellGrid r2 = new RichTableCellGrid(r12, r13);
        this.grid = r2;
        ScrollContent r132 = new ScrollContent(this, r12);
        this.scrollContent = r132;
        r132.addView(r2);
        r14.addView(r132, new FrameLayout.LayoutParams(-2, -2));
        setWillNotDraw(false);
    }

    private void bindTitle(boolean r5) {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L16;
        TL_iv.PageBlock r02 = r0.block;
        if ((r02 instanceof TL_iv.pageBlockTable) == false) goto L18;
        TL_iv.pageBlockTable r03 = (TL_iv.pageBlockTable) r02;
        if (r03.title != null) goto L10;
        r03.title = new TL_iv.textEmpty();
    L10:
        String r1 = RichTextStyle.plainOf(r03.title);
        CharSequence r04 = RichTextStyle.toSpannable(r03.title);
        initializeTitleAutoBold(r04);
        this.titleEditText.setAutoBold(this.currentRow.titleAutoBold);
        if (r5 == false) goto L13;
    L14:
        CharSequence r52 = Emoji.replaceEmoji(r04, this.titleEditText.getPaint().getFontMetricsInt(), false);
        this.titleEditText.setTextSilently(r52);
        this.titleEditText.invalidateEffects();
        return;
    L13:
        if (String.valueOf(this.titleEditText.getText()).equals(r1) == false) goto L14;
        return;
    L18:
        return;
    }

    private void focusCellAt(int r3, int r4) {
        TableModel r0 = this.model;
        if (r0 == null) goto L14;
        int r1 = r0.rowCount;
        if (r1 != 0) goto L7;
        return;
    L7:
        if (r0.colCount == 0) goto L16;
        int r32 = Math.max(0, Math.min(r3, r1 - 1));
        TL_iv.pageTableCell r33 = this.model.grid[r32][Math.max(0, Math.min(r4, this.model.colCount - 1))];
        if (r33 == null) goto L17;
        post(new RichTableCell$$ExternalSyntheticLambda4(this, r33));
        return;
    L17:
        return;
    L16:
        return;
    }

    private int gridX(int r2) {
        return (((r2 - this.scrollView.getLeft()) - this.scrollContent.getLeft()) - this.grid.getLeft()) + this.scrollView.getScrollX();
    }

    private int gridY(int r2) {
        return ((r2 - this.scrollView.getTop()) - this.scrollContent.getTop()) - this.grid.getTop();
    }

    private void initializeTitleAutoBold(CharSequence r4) {
        BlockRow r3 = this.currentRow;
        if (r3.titleAutoBoldInitialized == false) goto L5;
        return;
    L5:
        boolean r0 = true;
        r3.titleAutoBoldInitialized = true;
        if (r4.length() != 0) goto L8;
    L11:
        r3.titleAutoBold = r0;
        return;
    L8:
        if ((RichTextStyle.stylesFullyCovering(r4, 0, r4.length()) & 1) != 0) goto L11;
        r0 = false;
        goto L11
    }

    private void invalidateGridForFocus() {
        updateHandleOverlayLayer();
        RichTableCellGrid r0 = this.grid;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$focusCellAt$2(TL_iv.pageTableCell r1) {
        RichTableCellHost r0 = this.grid.hostForAnchor(r1);
        if (r0 != null) goto L5;
        return;
    L5:
        r0.editText.requestEditFocus();
        RichEditText r02 = r0.editText;
        r02.setSelection(r02.length());
    }

    private /* synthetic */ void lambda$new$0() {
        rememberTitleAutoBoldState();
        persistTitle();
        Delegate r0 = this.delegate;
        if (r0 == null) goto L8;
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L9;
        r0.onSpansChanged(r1);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$1(View r1, View r2) {
        invalidateGridForFocus();
    }

    private /* synthetic */ void lambda$wireCellListeners$3(RichTableCellHost r2) {
        TL_iv.pageTableCell r0 = r2.cell;
        if (r0 == null) goto L5;
        TableModel.applyStyledText(r0, r2.editText.getText());
    L5:
        Delegate r22 = this.delegate;
        if (r22 == null) goto L11;
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L12;
        r22.onSpansChanged(r1);
        return;
    L12:
        return;
    }

    private void notifyCellSelectionChanged() {
        updateHandleOverlayLayer();
        CellSelectionListener r0 = this.cellSelectionListener;
        if (r0 == null) goto L6;
        r0.onCellSelectionChanged(this);
        return;
    }

    private void persistTitle() {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L9;
        TL_iv.PageBlock r02 = r0.block;
        if ((r02 instanceof TL_iv.pageBlockTable) == false) goto L10;
        TL_iv.RichText r2 = RichTextStyle.fromSpannable(this.titleEditText.getText());
        ((TL_iv.pageBlockTable) r02).title = r2;
        return;
    L10:
        return;
    }

    private void rememberTitleAutoBoldState() {
        BlockRow r0 = this.currentRow;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.titleAutoBoldInitialized = true;
        r0.titleAutoBold = this.titleEditText.isAutoBold();
    }

    private void updateHandleOverlayLayer() {
        if (this.selectedCells.isEmpty() == true) goto L5;
    L8:
        float r0 = AndroidUtilities.dp(1.0f);
    L9:
        setTranslationZ(r0);
        invalidate();
        this.scrollView.invalidate();
        Object r1 = getParent();
        if ((r1 instanceof View) == false) goto L13;
        ((View) r1).invalidate();
        return;
    L13:
        return;
    L5:
        if (this.grid.hasFocus() == true) goto L8;
        r0 = 0.0f;
        goto L9
    }

    private void wireCellListeners() {
        int r0 = 0;
    L4:
        if (r0 >= this.grid.getChildCount()) goto L10;
        View r1 = this.grid.getChildAt(r0);
        if ((r1 instanceof RichTableCellHost) == false) goto L9;
        RichTableCellHost r12 = (RichTableCellHost) r1;
        r12.editText.setListener(new AnonymousClass3(this, r12));
        r12.editText.setDelegate(new RichTableCell$$ExternalSyntheticLambda3(this, r12));
    L9:
        r0 = r0 + 1;
        goto L4
    }

    public void addCellToSelection(TL_iv.pageTableCell r2) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (this.selectedCells.add(r2) == false) goto L8;
        this.grid.invalidate();
        notifyCellSelectionChanged();
        return;
    }

    public boolean allSelectedHeader() {
        if (this.selectedCells.isEmpty() == false) goto L5;
        return false;
    L5:
        Iterator<TL_iv.pageTableCell> r2 = this.selectedCells.iterator();
    L7:
        if (r2.hasNext() == false) goto L11;
        if (r2.next().header == true) goto L7;
        return false;
    L11:
        return true;
    }

    public TL_iv.pageTableCell anchorForChildPos(int r3) {
        TableModel r0 = this.model;
        if (r0 == null) goto L10;
        if (r3 <= 0) goto L10;
        int r32 = r3 - 1;
        if (r32 >= r0.anchors().size()) goto L10;
        return this.model.anchors().get(r32);
    L10:
        return null;
    }

    public void applyBordered(boolean r3) {
        TableModel r0 = this.model;
        if (r0 == null) goto L13;
        TL_iv.pageBlockTable r02 = r0.block;
        if (r02.bordered == r3) goto L16;
        r02.bordered = r3;
        this.grid.invalidate();
        Delegate r32 = this.delegate;
        if (r32 == null) goto L14;
        BlockRow r2 = this.currentRow;
        if (r2 == null) goto L15;
        r32.onTextChanged(r2);
        return;
    L15:
        return;
    L14:
        return;
    L16:
        return;
    }

    public void applyCompact(boolean r3) {
        TableModel r0 = this.model;
        if (r0 == null) goto L13;
        TL_iv.pageBlockTable r02 = r0.block;
        if (r02.compact == r3) goto L16;
        r02.compact = r3;
        this.grid.refreshCompact();
        this.scrollContent.requestLayout();
        requestLayout();
        Delegate r32 = this.delegate;
        if (r32 == null) goto L14;
        BlockRow r2 = this.currentRow;
        if (r2 == null) goto L15;
        r32.onTextChanged(r2);
        return;
    L15:
        return;
    L14:
        return;
    L16:
        return;
    }

    public boolean applyDeleteColumnsFromSelection() {
        if (this.model != null) goto L5;
    L15:
        return false;
    L5:
        if (this.selectedCells.isEmpty() == true) goto L15;
        HashSet r0 = new HashSet();
        Iterator<TL_iv.pageTableCell> r2 = this.selectedCells.iterator();
        int r3 = Integer.MAX_VALUE;
    L9:
        if (r2.hasNext() == false) goto L11;
        TL_iv.pageTableCell r4 = r2.next();
        int r42 = this.model.anchorColOf(r4);
        r0.add(Integer.valueOf(r42));
        r3 = Math.min(r3, r42);
        goto L9
    L11:
        this.selectedCells.clear();
        boolean r02 = this.model.deleteColumns(r0);
        refreshAfterModelChange();
        if (r02 == false) goto L14;
        focusCellAt(0, r3);
    L14:
        return r02;
    }

    public boolean applyDeleteRowsFromSelection() {
        if (this.model != null) goto L5;
    L15:
        return false;
    L5:
        if (this.selectedCells.isEmpty() == true) goto L15;
        HashSet r0 = new HashSet();
        Iterator<TL_iv.pageTableCell> r2 = this.selectedCells.iterator();
        int r3 = Integer.MAX_VALUE;
    L9:
        if (r2.hasNext() == false) goto L11;
        TL_iv.pageTableCell r4 = r2.next();
        int r42 = this.model.anchorRowOf(r4);
        r0.add(Integer.valueOf(r42));
        r3 = Math.min(r3, r42);
        goto L9
    L11:
        this.selectedCells.clear();
        boolean r02 = this.model.deleteRows(r0);
        refreshAfterModelChange();
        if (r02 == false) goto L14;
        focusCellAt(r3, 0);
    L14:
        return r02;
    }

    public void applyHeaderToggle(boolean r8) {
        Iterator<TL_iv.pageTableCell> r0 = this.selectedCells.iterator();
    L4:
        if (r0.hasNext() == false) goto L23;
        TL_iv.pageTableCell r1 = r0.next();
        RichTableCellHost r2 = this.grid.hostForAnchor(r1);
        if (r2 != null) goto L7;
        SpannableStringBuilder r22 = new SpannableStringBuilder(TableModel.readStyledText(r1));
        if (r22.length() > 0) goto L11;
    L13:
        boolean r3 = false;
    L14:
        TableModel.setHeader(r1, r8);
        if (r8 == true) goto L17;
    L19:
        if (r8 == true) goto L22;
        if (r3 == false) goto L22;
        RichTextStyle.setStyle(r22, 0, r22.length(), 1, false);
    L22:
        TableModel.applyStyledText(r1, r22);
        goto L4
    L17:
        if (r22.length() <= 0) goto L19;
        RichTextStyle.setStyle(r22, 0, r22.length(), 1, true);
        goto L22
    L11:
        if ((RichTextStyle.stylesFullyCovering(r22, 0, r22.length()) & 1) == 0) goto L13;
        r3 = true;
        goto L14
    L7:
        r2.applyHeaderWithDefaultBold(r8);
        goto L4
    L23:
        this.grid.invalidate();
        Delegate r82 = this.delegate;
        if (r82 == null) goto L35;
        BlockRow r7 = this.currentRow;
        if (r7 == null) goto L36;
        r82.onTextChanged(r7);
        return;
    L36:
        return;
    }

    public void applyHorizontalAlign(int r4) {
        Iterator<TL_iv.pageTableCell> r0 = this.selectedCells.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        TL_iv.pageTableCell r1 = r0.next();
        TableModel.setAlign(r1, r4);
        RichTableCellHost r12 = this.grid.hostForAnchor(r1);
        if (r12 == null) goto L4;
        r12.refreshFromCell();
        goto L4
    L8:
        this.grid.invalidate();
        Delegate r42 = this.delegate;
        if (r42 == null) goto L19;
        BlockRow r3 = this.currentRow;
        if (r3 == null) goto L20;
        r42.onTextChanged(r3);
        return;
    L20:
        return;
    }

    public boolean applyInsertColumnFromSelection(boolean r5) {
        if (this.model != null) goto L5;
    L22:
        return false;
    L5:
        if (this.selectedCells.isEmpty() == true) goto L22;
        LinkedHashSet<TL_iv.pageTableCell> r0 = this.selectedCells;
        if (r5 == false) goto L13;
        Iterator<TL_iv.pageTableCell> r52 = r0.iterator();
        int r02 = Integer.MAX_VALUE;
    L11:
        if (r52.hasNext() == false) goto L17;
        r02 = Math.min(r02, this.model.anchorColOf(r52.next()));
    L17:
        this.selectedCells.clear();
        boolean r53 = this.model.insertColumnAt(r02);
        refreshAfterModelChange();
        if (r53 == false) goto L20;
        focusCellAt(0, r02);
    L20:
        notifyCellSelectionChanged();
        return r53;
    L13:
        Iterator<TL_iv.pageTableCell> r54 = r0.iterator();
        r02 = 0;
    L15:
        if (r54.hasNext() == false) goto L17;
        TL_iv.pageTableCell r2 = r54.next();
        r02 = Math.max(r02, this.model.anchorColOf(r2) + TableModel.spanCol(r2));
        goto L15
    }

    public boolean applyInsertRowFromSelection(boolean r5) {
        if (this.model != null) goto L5;
    L22:
        return false;
    L5:
        if (this.selectedCells.isEmpty() == true) goto L22;
        LinkedHashSet<TL_iv.pageTableCell> r0 = this.selectedCells;
        if (r5 == false) goto L13;
        Iterator<TL_iv.pageTableCell> r52 = r0.iterator();
        int r02 = Integer.MAX_VALUE;
    L11:
        if (r52.hasNext() == false) goto L17;
        r02 = Math.min(r02, this.model.anchorRowOf(r52.next()));
    L17:
        this.selectedCells.clear();
        boolean r53 = this.model.insertRowAt(r02);
        refreshAfterModelChange();
        if (r53 == false) goto L20;
        focusCellAt(r02, 0);
    L20:
        notifyCellSelectionChanged();
        return r53;
    L13:
        Iterator<TL_iv.pageTableCell> r54 = r0.iterator();
        r02 = 0;
    L15:
        if (r54.hasNext() == false) goto L17;
        TL_iv.pageTableCell r2 = r54.next();
        r02 = Math.max(r02, this.model.anchorRowOf(r2) + TableModel.spanRow(r2));
        goto L15
    }

    public boolean applyMergeFromSelection() {
        if (this.model != null) goto L5;
        return false;
    L5:
        if (this.selectedCells.size() < 2) goto L20;
        HashSet r0 = new HashSet(this.selectedCells);
        Iterator r1 = r0.iterator();
        int r2 = Integer.MAX_VALUE;
        int r3 = Integer.MAX_VALUE;
    L9:
        if (r1.hasNext() == false) goto L11;
        TL_iv.pageTableCell r4 = (TL_iv.pageTableCell) r1.next();
        r2 = Math.min(r2, this.model.anchorRowOf(r4));
        r3 = Math.min(r3, this.model.anchorColOf(r4));
        goto L9
    L11:
        this.selectedCells.clear();
        boolean r12 = this.model.mergeCells(r0);
        if (r12 == false) goto L15;
        refreshAfterModelChange();
        this.grid.invalidate();
        focusCellAt(r2, r3);
        notifyCellSelectionChanged();
        return r12;
    L15:
        this.selectedCells.addAll(r0);
        return r12;
    L20:
        return false;
    }

    public boolean applyUnmergeFromSelection() {
        if (this.model != null) goto L5;
    L18:
        return false;
    L5:
        if (this.selectedCells.size() != 1) goto L18;
        TL_iv.pageTableCell r0 = this.selectedCells.iterator().next();
        if (TableModel.spanCol(r0) <= 1) goto L10;
    L12:
        int r1 = this.model.anchorRowOf(r0);
        int r2 = this.model.anchorColOf(r0);
        this.selectedCells.clear();
        boolean r3 = this.model.unmergeCell(r0);
        if (r3 == false) goto L16;
        refreshAfterModelChange();
        this.grid.invalidate();
        focusCellAt(r1, r2);
        notifyCellSelectionChanged();
        return r3;
    L16:
        this.selectedCells.add(r0);
        return r3;
    L10:
        if (TableModel.spanRow(r0) > 1) goto L12;
        return false;
    }

    public void applyVerticalAlign(int r4) {
        Iterator<TL_iv.pageTableCell> r0 = this.selectedCells.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        TL_iv.pageTableCell r1 = r0.next();
        TableModel.setVAlign(r1, r4);
        RichTableCellHost r12 = this.grid.hostForAnchor(r1);
        if (r12 == null) goto L4;
        r12.refreshFromCell();
        goto L4
    L8:
        this.grid.invalidate();
        Delegate r42 = this.delegate;
        if (r42 == null) goto L19;
        BlockRow r3 = this.currentRow;
        if (r3 == null) goto L20;
        r42.onTextChanged(r3);
        return;
    L20:
        return;
    }

    public void bind(BlockRow r3, Delegate r4) {
        if (this.currentRow == r3) goto L5;
        boolean r0 = true;
    L6:
        this.currentRow = r3;
        this.delegate = r4;
        this.blockRtl = RichBlockChrome.rtl();
        bindBlockInset(r3);
        TL_iv.PageBlock r32 = r3.block;
        if ((r32 instanceof TL_iv.pageBlockTable) == true) goto L9;
        return;
    L9:
        TableModel r42 = new TableModel((TL_iv.pageBlockTable) r32);
        this.model = r42;
        this.grid.setModel(r42);
        RichTableCellGrid r33 = this.grid;
        LinkedHashSet<TL_iv.pageTableCell> r43 = this.selectedCells;
        Objects.requireNonNull(r43);
        r33.setSelectionProvider(new RichTableCell$$ExternalSyntheticLambda2(r43));
        wireCellListeners();
        bindTitle(r0);
        updateColors();
        this.scrollContent.requestLayout();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public int childCount() {
        TableModel r0 = this.model;
        if (r0 == null) goto L5;
        int r02 = r0.anchors().size();
    L7:
        return r02 + 1;
    L5:
        r02 = 0;
        goto L7
    }

    public int childPosForAnchor(TL_iv.pageTableCell r2) {
        TableModel r1 = this.model;
        if (r1 != null) goto L5;
        return -1;
    L5:
        int r12 = r1.flatIndexOfAnchor(r2);
        if (r12 >= 0) goto L9;
        return -1;
    L9:
        return r12 + 1;
    }

    public int childTextLength(int r1) {
        RichEditText r0 = editTextForChildPos(r1);
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.length();
    }

    public void clearCellSelection() {
        if (this.selectedCells.isEmpty() == false) goto L5;
        return;
    L5:
        this.selectedCells.clear();
        this.grid.invalidate();
        notifyCellSelectionChanged();
    }

    public int colHandleEnd(int r1) {
        return this.grid.colHandleEnd(r1);
    }

    public int commonHorizontalAlign() {
        Iterator<TL_iv.pageTableCell> r3 = this.selectedCells.iterator();
        int r1 = -1;
    L4:
        if (r3.hasNext() == false) goto L10;
        int r2 = TableModel.alignOf(r3.next());
        if (r1 == (-1)) goto L7;
        if (r1 == r2) goto L4;
        return -1;
    L7:
        r1 = r2;
        goto L4
    L10:
        return r1;
    }

    public int commonVerticalAlign() {
        Iterator<TL_iv.pageTableCell> r3 = this.selectedCells.iterator();
        int r1 = -1;
    L4:
        if (r3.hasNext() == false) goto L10;
        int r2 = TableModel.valignOf(r3.next());
        if (r1 == (-1)) goto L7;
        if (r1 == r2) goto L4;
        return -1;
    L7:
        r1 = r2;
        goto L4
    L10:
        return r1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        super.dispatchDraw(r5);
        if (this.model == null) goto L15;
        Delegate r0 = this.delegate;
        if (r0 == null) goto L8;
        TextSelectionHelper.ArticleTextSelectionHelper r02 = r0.getSelectionHelper();
    L9:
        if (r02 == null) goto L17;
        this.tmpBlocks.clear();
        fillTextLayoutBlocks(this.tmpBlocks);
        int r1 = 0;
    L13:
        if (r1 >= this.tmpBlocks.size()) goto L18;
        TextSelectionHelper.TextLayoutBlock r2 = this.tmpBlocks.get(r1);
        r5.save();
        r5.translate(r2.getX(), r2.getY());
        r02.draw(r5, this, r1);
        r5.restore();
        r1 = r1 + 1;
        goto L13
    L18:
        return;
    L17:
        return;
    L8:
        r02 = null;
        goto L9
    }

    public RichEditText editTextForChildPos(int r2) {
        if (r2 == 0) goto L4;
        TL_iv.pageTableCell r22 = anchorForChildPos(r2);
        if (r22 != null) goto L8;
        return null;
    L8:
        RichTableCellHost r1 = this.grid.hostForAnchor(r22);
        if (r1 != null) goto L11;
        return null;
    L11:
        return r1.editText;
    L4:
        return this.titleEditText;
    }

    @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
    public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r11) {
        if (this.model == null) goto L18;
        Layout r0 = this.titleEditText.getLayout();
        if (r0 == null) goto L8;
        r11.add(new AnonymousClass4(this, r0, this.titleEditText.getLeft() + this.titleEditText.getPaddingLeft(), this.titleEditText.getTop() + this.titleEditText.getPaddingTop()));
    L8:
        int r02 = this.model.anchors().size();
        int r1 = 0;
    L9:
        if (r1 >= r02) goto L22;
        TL_iv.pageTableCell r9 = this.model.anchors().get(r1);
        RichTableCellHost r2 = this.grid.hostForAnchor(r9);
        if (r2 != null) goto L13;
    L12:
        RichTableCell r4 = this;
    L17:
        r1 = r1 + 1;
        RichTableCell this = r4;
        goto L9
    L13:
        Layout r5 = r2.editText.getLayout();
        if (r5 == null) goto L12;
        r4 = this;
        r11.add(new AnonymousClass5(r4, r5, (((((this.scrollView.getLeft() + this.scrollContent.getLeft()) + this.grid.getLeft()) - this.scrollView.getScrollX()) + r2.getLeft()) + r2.editText.getLeft()) + r2.editText.getPaddingLeft(), ((((this.scrollView.getTop() + this.scrollContent.getTop()) + this.grid.getTop()) + r2.getTop()) + r2.editText.getTop()) + r2.editText.getPaddingTop(), this.model.anchorRowOf(r9) + 10, r9));
        goto L17
    L22:
        return;
    }

    public TL_iv.pageTableCell findCellAt(int r5, int r6) {
        if (this.model != null) goto L5;
        return null;
    L5:
        int r52 = (((r5 - this.scrollView.getLeft()) - this.scrollContent.getLeft()) - this.grid.getLeft()) + this.scrollView.getScrollX();
        int r62 = ((r6 - this.scrollView.getTop()) - this.scrollContent.getTop()) - this.grid.getTop();
        int r0 = 0;
    L7:
        if (r0 >= this.grid.getChildCount()) goto L22;
        View r2 = this.grid.getChildAt(r0);
        if ((r2 instanceof RichTableCellHost) == false) goto L21;
        RichTableCellHost r22 = (RichTableCellHost) r2;
        if (r52 < r22.getLeft()) goto L21;
        if (r52 >= r22.getRight()) goto L21;
        if (r62 < r22.getTop()) goto L21;
        if (r62 >= r22.getBottom()) goto L21;
        return r22.cell;
    L21:
        r0 = r0 + 1;
        goto L7
    L22:
        return null;
    }

    public int findColHandleAt(int r2, int r3) {
        if (this.model != null) goto L7;
        return -1;
    L7:
        return this.grid.colHandleAtGrid(gridX(r2), gridY(r3));
    }

    public RichTableCellHost findHostContaining(View r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        ViewParent r32 = r3.getParent();
    L6:
        if (r32 == null) goto L14;
        if ((r32 instanceof RichTableCellHost) == true) goto L10;
        if (r32 == this) goto L12;
        r32 = r32.getParent();
        goto L6
    L12:
        return null;
    L10:
        return (RichTableCellHost) r32;
    L14:
        return null;
    }

    public int findRowHandleAt(int r2, int r3) {
        if (this.model != null) goto L7;
        return -1;
    L7:
        return this.grid.rowHandleAtGrid(gridX(r2), gridY(r3));
    }

    public boolean focusEdgeCell(boolean r4) {
        TableModel r0 = this.model;
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r4 == true) goto L10;
        this.titleEditText.requestEditFocus();
        this.titleEditText.setSelection(0);
        return true;
    L10:
        if (r0.anchors().isEmpty() == false) goto L12;
        return false;
    L12:
        TL_iv.pageTableCell r42 = this.model.anchors().get(this.model.anchors().size() - 1);
        RichTableCellHost r3 = this.grid.hostForAnchor(r42);
        if (r3 != null) goto L15;
        return false;
    L15:
        r3.editText.requestEditFocus();
        RichEditText r32 = r3.editText;
        r32.setSelection(r32.length());
        return true;
    }

    public boolean focusFirstCell() {
        TableModel r0 = this.model;
        if (r0 != null) goto L5;
    L12:
        return false;
    L5:
        if (r0.anchors().isEmpty() == true) goto L12;
        RichTableCellHost r2 = this.grid.hostForAnchor(this.model.anchors().get(0));
        if (r2 != null) goto L10;
        return false;
    L10:
        r2.editText.requestEditFocus();
        r2.editText.setSelection(0);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public RichTableCellGrid getGrid() {
        return this.grid;
    }

    public TableModel getModel() {
        return this.model;
    }

    public BlockRow getRow() {
        return this.currentRow;
    }

    public Set<TL_iv.pageTableCell> getSelectedCells() {
        return this.selectedCells;
    }

    public RichEditText getTitleEditText() {
        return this.titleEditText;
    }

    public boolean hasCellSelection() {
        return !this.selectedCells.isEmpty();
    }

    public void hideActionModes() {
        this.titleEditText.hideActionMode();
        int r0 = 0;
    L4:
        if (r0 >= this.grid.getChildCount()) goto L9;
        View r1 = this.grid.getChildAt(r0);
        if ((r1 instanceof RichTableCellHost) == false) goto L8;
        ((RichTableCellHost) r1).editText.hideActionMode();
    L8:
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.view.View, org.telegram.ui.Cells.TextSelectionHelper.SelectableView
    public void invalidate() {
        super.invalidate();
        RichTableCellGrid r0 = this.grid;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public boolean isPressOnText(int r4, int r5) {
        TL_iv.pageTableCell r0 = findCellAt(r4, r5);
        if (r0 != null) goto L5;
        return false;
    L5:
        RichTableCellHost r02 = this.grid.hostForAnchor(r0);
        if (r02 != null) goto L8;
        return false;
    L8:
        int r42 = (((r4 - this.scrollView.getLeft()) - this.scrollContent.getLeft()) - this.grid.getLeft()) + this.scrollView.getScrollX();
        int r52 = ((r5 - this.scrollView.getTop()) - this.scrollContent.getTop()) - this.grid.getTop();
        int r43 = (r42 - r02.getLeft()) - r02.editText.getLeft();
        int r53 = (r52 - r02.getTop()) - r02.editText.getTop();
        Layout r3 = r02.editText.getLayout();
        if (r3 != null) goto L11;
        return false;
    L11:
        int r54 = r53 - r02.editText.getPaddingTop();
        int r44 = r43 - r02.editText.getPaddingLeft();
        if (r54 >= 0) goto L14;
    L27:
        return false;
    L14:
        if (r54 >= r3.getHeight()) goto L27;
        int r55 = r3.getLineForVertical(r54);
        if (r55 < 0) goto L27;
        if (r55 >= r3.getLineCount()) goto L27;
        float r03 = r3.getLineLeft(r55);
        float r32 = r3.getLineRight(r55);
        float r45 = r44;
        if (r45 < r03) goto L27;
        if (r45 > r32) goto L27;
        return true;
    }

    public boolean isPressOnTitle(int r5, int r6) {
        Layout r0 = this.titleEditText.getLayout();
        if (r0 != null) goto L5;
        return false;
    L5:
        int r52 = r5 - (this.titleEditText.getLeft() + this.titleEditText.getPaddingLeft());
        int r62 = r6 - (this.titleEditText.getTop() + this.titleEditText.getPaddingTop());
        if (r62 >= 0) goto L8;
    L21:
        return false;
    L8:
        if (r62 >= r0.getHeight()) goto L21;
        int r4 = r0.getLineForVertical(r62);
        if (r4 < 0) goto L21;
        if (r4 >= r0.getLineCount()) goto L21;
        float r53 = r52;
        if (r53 < r0.getLineLeft(r4)) goto L21;
        if (r53 > r0.getLineRight(r4)) goto L21;
        return true;
    }

    public boolean moveFocusByTab(RichTableCellHost r3, boolean r4) {
        TableModel r0 = this.model;
        if (r0 != null) goto L5;
        return false;
    L5:
        int r32 = r0.anchors().indexOf(r3.cell);
        if (r32 >= 0) goto L9;
        return false;
    L9:
        if (r4 == false) goto L11;
        int r33 = r32 - 1;
    L12:
        if (r33 >= 0) goto L14;
    L21:
        return false;
    L14:
        if (r33 >= this.model.anchors().size()) goto L21;
        RichTableCellHost r2 = this.grid.hostForAnchor(this.model.anchors().get(r33));
        if (r2 != null) goto L19;
        return false;
    L19:
        r2.editText.requestEditFocus();
        RichEditText r22 = r2.editText;
        r22.setSelection(r22.length());
        return true;
    L11:
        r33 = r32 + 1;
        goto L12
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.focusInvalidator);
    }

    @Override // org.telegram.ui.iv.RichBlockCell
    public void onBlockInsetChanged(int r1) {
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.focusInvalidator);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        int r82 = r8 - r6;
        int r52 = blockInset();
        int r62 = RichBlockChrome.insetEndFor(this.currentRow);
        boolean r72 = this.blockRtl;
        if (r72 == false) goto L5;
        int r92 = r62;
    L6:
        if (r72 == true) goto L9;
        r52 = r62;
    L9:
        int r63 = this.titleEditText.getMeasuredHeight();
        int r73 = RichBlockChrome.quoteTopPad(this.currentRow);
        int r83 = r82 - r52;
        int r64 = r63 + r73;
        this.titleEditText.layout(AndroidUtilities.dp(16.0f) + r92, r73, Math.max(AndroidUtilities.dp(16.0f) + r92, r83 - AndroidUtilities.dp(16.0f)), r64);
        int r65 = r64 + AndroidUtilities.dp(9.0f);
        HorizontalScrollView r4 = this.scrollView;
        r4.layout(r92, r65, r83, r4.getMeasuredHeight() + r65);
        return;
    L5:
        r92 = r52;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        int r62 = View.MeasureSpec.getSize(r6);
        int r72 = RichBlockChrome.insetEndFor(this.currentRow);
        int r0 = Math.max(0, (r62 - blockInset()) - r72);
        int r1 = Math.max(0, r0 - (AndroidUtilities.dp(16.0f) * 2));
        this.titleEditText.measure(View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        int r12 = this.titleEditText.getMeasuredHeight();
        this.scrollView.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        setMeasuredDimension(r62, (((RichBlockChrome.quoteTopPad(this.currentRow) + RichBlockChrome.quoteBottomPad(this.currentRow)) + r12) + AndroidUtilities.dp(9.0f)) + this.scrollView.getMeasuredHeight());
    }

    public void persistTitleFromEditor() {
        persistTitle();
    }

    public void refreshAfterModelChange() {
        this.grid.rebindAfterModelChange();
        wireCellListeners();
        Delegate r0 = this.delegate;
        if (r0 == null) goto L8;
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L9;
        r0.onTextChanged(r1);
        return;
    L9:
        return;
    }

    public int rowHandleEnd(int r1) {
        return this.grid.rowHandleEnd(r1);
    }

    public void selectCellRectangle(TL_iv.pageTableCell r12, TL_iv.pageTableCell r13) {
        TableModel r0 = this.model;
        if (r0 == null) goto L42;
        if (r12 == null) goto L50;
        if (r13 == null) goto L54;
        int r02 = r0.anchorRowOf(r12);
        int r1 = this.model.anchorColOf(r12);
        int r2 = this.model.anchorRowOf(r13);
        int r3 = this.model.anchorColOf(r13);
        if (r02 < 0) goto L51;
        if (r1 < 0) goto L52;
        if (r2 < 0) goto L53;
        if (r3 < 0) goto L55;
        int r4 = Math.min(r02, r2);
        int r5 = Math.min(r1, r3);
        int r03 = Math.min(this.model.rowCount - 1, Math.max((r02 + TableModel.spanRow(r12)) - 1, (r2 + TableModel.spanRow(r13)) - 1));
        int r122 = Math.min(this.model.colCount - 1, Math.max((r1 + TableModel.spanCol(r12)) - 1, (r3 + TableModel.spanCol(r13)) - 1));
    L14:
        boolean r132 = false;
        int r14 = r4;
    L15:
        if (r4 > r03) goto L29;
        int r22 = r5;
    L17:
        if (r5 > r122) goto L28;
        TableModel r32 = this.model;
        TL_iv.pageTableCell r6 = r32.grid[r4][r5];
        int r8 = r32.anchorR[r4][r5];
        int r9 = r32.anchorC[r4][r5];
        int r33 = Math.min(r32.rowCount - 1, (TableModel.spanRow(r6) + r8) - 1);
        int r62 = Math.min(this.model.colCount - 1, (TableModel.spanCol(r6) + r9) - 1);
        if (r8 >= r14) goto L21;
        r132 = true;
        r14 = r8;
    L21:
        if (r9 >= r22) goto L23;
        r132 = true;
        r22 = r9;
    L23:
        if (r33 <= r03) goto L25;
        r03 = r33;
        r132 = true;
    L25:
        if (r62 <= r122) goto L27;
        r122 = r62;
        r132 = true;
    L27:
        r5 = r5 + 1;
        goto L17
    L28:
        r4 = r4 + 1;
        r5 = r22;
        goto L15
    L29:
        if (r132 == false) goto L30;
        r4 = r14;
        goto L14
    L30:
        LinkedHashSet r133 = new LinkedHashSet();
    L31:
        if (r14 > r03) goto L37;
        int r23 = r5;
    L33:
        if (r23 > r122) goto L35;
        r133.add(this.model.grid[r14][r23]);
        r23 = r23 + 1;
        goto L33
    L35:
        r14 = r14 + 1;
        goto L31
    L37:
        if (this.selectedCells.equals(r133) == true) goto L56;
        this.selectedCells.clear();
        this.selectedCells.addAll(r133);
        this.grid.invalidate();
        notifyCellSelectionChanged();
        return;
    L56:
        return;
    L55:
        return;
    L53:
        return;
    L52:
        return;
    L51:
        return;
    L54:
        return;
    L50:
        return;
    }

    public void selectWholeColumns(int r4, int r5) {
        TableModel r0 = this.model;
        if (r0 == null) goto L25;
        if (r4 < 0) goto L26;
        if (r5 >= r4) goto L7;
        return;
    L7:
        if (r5 >= r0.colCount) goto L28;
        this.selectedCells.clear();
    L10:
        if (r4 > r5) goto L19;
        int r02 = 0;
    L12:
        TableModel r1 = this.model;
        if (r02 >= r1.rowCount) goto L18;
        TL_iv.pageTableCell r12 = r1.grid[r02][r4];
        if (r12 == null) goto L17;
        this.selectedCells.add(r12);
    L17:
        r02 = r02 + 1;
        goto L12
    L18:
        r4 = r4 + 1;
        goto L10
    L19:
        this.grid.invalidate();
        notifyCellSelectionChanged();
        return;
    L28:
        return;
    L26:
        return;
    }

    public void selectWholeRows(int r4, int r5) {
        TableModel r0 = this.model;
        if (r0 == null) goto L25;
        if (r4 < 0) goto L26;
        if (r5 >= r4) goto L7;
        return;
    L7:
        if (r5 >= r0.rowCount) goto L28;
        this.selectedCells.clear();
    L10:
        if (r4 > r5) goto L19;
        int r02 = 0;
    L12:
        TableModel r1 = this.model;
        if (r02 >= r1.colCount) goto L18;
        TL_iv.pageTableCell r12 = r1.grid[r4][r02];
        if (r12 == null) goto L17;
        this.selectedCells.add(r12);
    L17:
        r02 = r02 + 1;
        goto L12
    L18:
        r4 = r4 + 1;
        goto L10
    L19:
        this.grid.invalidate();
        notifyCellSelectionChanged();
        return;
    L28:
        return;
    L26:
        return;
    }

    public boolean selectionContainsWholeColumns(int r5, int r6) {
        TableModel r0 = this.model;
        if (r0 == null) goto L22;
        if (r5 < 0) goto L22;
        if (r6 < r5) goto L22;
        if (r6 >= r0.colCount) goto L22;
        if (this.selectedCells.isEmpty() == true) goto L22;
    L11:
        if (r5 > r6) goto L20;
        int r02 = 0;
    L13:
        TableModel r2 = this.model;
        if (r02 >= r2.rowCount) goto L19;
        if (this.selectedCells.contains(r2.grid[r02][r5]) == false) goto L17;
        r02 = r02 + 1;
        goto L13
    L17:
        return false;
    L19:
        r5 = r5 + 1;
        goto L11
    L20:
        return true;
    L22:
        return false;
    }

    public boolean selectionContainsWholeRows(int r5, int r6) {
        TableModel r0 = this.model;
        if (r0 == null) goto L22;
        if (r5 < 0) goto L22;
        if (r6 < r5) goto L22;
        if (r6 >= r0.rowCount) goto L22;
        if (this.selectedCells.isEmpty() == true) goto L22;
    L11:
        if (r5 > r6) goto L20;
        int r02 = 0;
    L13:
        TableModel r2 = this.model;
        if (r02 >= r2.colCount) goto L19;
        if (this.selectedCells.contains(r2.grid[r5][r02]) == false) goto L17;
        r02 = r02 + 1;
        goto L13
    L17:
        return false;
    L19:
        r5 = r5 + 1;
        goto L11
    L20:
        return true;
    L22:
        return false;
    }

    public void setCellSelectionListener(CellSelectionListener r1) {
        this.cellSelectionListener = r1;
    }

    public void setLocked(boolean r4) {
        this.titleEditText.setLocked(r4);
        int r0 = 0;
    L4:
        if (r0 >= this.grid.getChildCount()) goto L9;
        View r1 = this.grid.getChildAt(r0);
        if ((r1 instanceof RichTableCellHost) == false) goto L8;
        ((RichTableCellHost) r1).setLocked(r4);
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public int titleChildPos() {
        return 0;
    }

    public void toggleCellSelection(TL_iv.pageTableCell r2) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (this.selectedCells.remove(r2) == true) goto L7;
        this.selectedCells.add(r2);
    L7:
        this.grid.invalidate();
        notifyCellSelectionChanged();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.titleEditText.updateColors();
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider);
        this.titleEditText.setTextColor(r0);
        this.titleEditText.setHintTextColor(Theme.multAlpha(r0, 0.35f));
        int r02 = 0;
    L3:
        int r1 = this.grid.getChildCount();
        RichTableCellGrid r2 = this.grid;
        if (r02 >= r1) goto L9;
        View r12 = r2.getChildAt(r02);
        if ((r12 instanceof RichTableCellHost) == false) goto L8;
        ((RichTableCellHost) r12).editText.updateColors();
    L8:
        r02 = r02 + 1;
        goto L3
    L9:
        r2.applyColors();
    }
}
