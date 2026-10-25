package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Layout;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.AnimatedArrowDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.iv.RichEditText;

/* loaded from: classes7.dex */
public class RichDetailsCell extends FrameLayout implements Theme.Colorable, TextSelectionHelper.ArticleSelectableView {
    private final AnimatedArrowDrawable arrow;
    private final Drawable.Callback arrowCallback;
    private final View arrowView;
    private BlockRow currentRow;
    private Delegate delegate;
    private final Paint dividerPaint;
    private final RichEditText editText;
    private boolean hijackingSelection;
    private final Theme.ResourcesProvider resourcesProvider;

    /* renamed from: org.telegram.ui.iv.RichDetailsCell$1, reason: invalid class name */
    public class AnonymousClass1 implements Drawable.Callback {
        final /* synthetic */ RichDetailsCell this$0;

        public AnonymousClass1(RichDetailsCell r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r1) {
            RichDetailsCell.m21638$$Nest$fgetarrowView(this.this$0).invalidate();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r1, Runnable r2) {
        }
    }

    /* renamed from: org.telegram.ui.iv.RichDetailsCell$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ RichDetailsCell this$0;

        public AnonymousClass2(RichDetailsCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r3) {
            r3.save();
            r3.translate(AndroidUtilities.dpf2(22.6f), AndroidUtilities.dpf2(21.66f));
            RichDetailsCell.m21637$$Nest$fgetarrow(this.this$0).draw(r3);
            r3.restore();
        }
    }

    /* renamed from: org.telegram.ui.iv.RichDetailsCell$3, reason: invalid class name */
    public class AnonymousClass3 implements RichEditText.Listener {
        final /* synthetic */ RichDetailsCell this$0;

        public static /* synthetic */ void $r8$lambda$DtpwMEw1J2noTYsa2myPKVYM_qo(AnonymousClass3 r0, RichEditText r1, int r2, TextSelectionHelper.ArticleTextSelectionHelper r3, int r4) {
            r0.lambda$onSelectionChanged$0(r1, r2, r3, r4);
        }

        public AnonymousClass3(RichDetailsCell r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onSelectionChanged$0(RichEditText r3, int r4, TextSelectionHelper.ArticleTextSelectionHelper r5, int r6) {
            if (r3.length() >= r4) goto L5;
            return;
        L5:
            if (r3.getSelectionStart() != r3.getSelectionEnd()) goto L8;
            return;
        L8:
            if (r5.selectRangeOf(this.this$0, r6, r4) == false) goto L12;
            RichDetailsCell.m21642$$Nest$fputhijackingSelection(this.this$0, true);
            r3.setSelection(r4);
            RichDetailsCell.m21642$$Nest$fputhijackingSelection(this.this$0, false);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onBackspaceAtStart(RichEditText r2) {
            if (RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0) != null) goto L7;
            return false;
        L7:
            if (r2.length() != 0) goto L13;
            RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0).onTitleBackspace(RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0));
            return true;
        L13:
            return false;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onBackspaceOnEmpty(RichEditText r1) {
            if (RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            if (RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0) == null) goto L9;
            RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0).onTitleBackspace(RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0));
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onEnterPressed(RichEditText r1) {
            if (RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            if (RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0) == null) goto L9;
            RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0).onTitleEnter(RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0));
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onLockedInsert(RichEditText r1, CharSequence r2) {
            if (RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0).onLockedInsert(r2);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            if (RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0).onRequestWindowFocusable(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onSelectAll(RichEditText r1) {
            if (RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0) != null) goto L7;
            return false;
        L7:
            return RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0).onSelectAll(RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0));
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onSelectionChanged(RichEditText r8, int r9, int r10) {
            if (RichDetailsCell.m21641$$Nest$fgethijackingSelection(this.this$0) == true) goto L18;
            if (r9 != r10) goto L6;
            return;
        L6:
            if (RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0) == null) goto L20;
            TextSelectionHelper.ArticleTextSelectionHelper r5 = RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
            if (r5 != null) goto L12;
            return;
        L12:
            if (r5.isInSelectionMode() == true) goto L14;
        L16:
            this.this$0.post(new RichDetailsCell$3$$ExternalSyntheticLambda0(this, r8, r10, r5, r9));
            return;
        L14:
            if (r5.getSelectedCell() != this.this$0) goto L16;
            return;
        L20:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextChanged(RichEditText r1, Editable r2) {
            RichDetailsCell.m21643$$Nest$mrememberAutoBoldState(this.this$0);
            if (RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0) == null) goto L8;
            if ((RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0).block instanceof TL_iv.pageBlockDetails) == false) goto L8;
            TL_iv.pageBlockDetails r12 = (TL_iv.pageBlockDetails) RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0).block;
            r12.title = RichTextStyle.fromSpannable(r2);
        L8:
            if (RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0) != null) goto L10;
            return;
        L10:
            if (RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0) == null) goto L14;
            RichDetailsCell.m21640$$Nest$fgetdelegate(this.this$0).onTitleChanged(RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichDetailsCell$4, reason: invalid class name */
    public class AnonymousClass4 implements TextSelectionHelper.TextLayoutBlock {
        final /* synthetic */ RichDetailsCell this$0;
        final /* synthetic */ Layout val$layout;
        final /* synthetic */ int val$textX;
        final /* synthetic */ int val$textY;

        public AnonymousClass4(RichDetailsCell r1, Layout r2, int r3, int r4) {
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
            if (RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0) != null) goto L5;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            if ((RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0).block instanceof TL_iv.pageBlockDetails) == true) goto L7;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            return RichTextStyle.toSpannable(((TL_iv.pageBlockDetails) RichDetailsCell.m21639$$Nest$fgetcurrentRow(this.this$0).block).title);
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
        TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper();

        void onLockedInsert(CharSequence r1);

        void onRequestWindowFocusable(RichEditText r1, boolean r2);

        boolean onSelectAll(BlockRow r1);

        void onSpansChanged(BlockRow r1);

        void onTitleBackspace(BlockRow r1);

        void onTitleChanged(BlockRow r1);

        void onTitleEnter(BlockRow r1);

        void onToggle(BlockRow r1);
    }

    public static final class Factory extends UItem.UItemFactory<RichDetailsCell> {
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
            ((RichDetailsCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichDetailsCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new RichDetailsCell(r1, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    /* renamed from: $r8$lambda$1ZALsdD55-DOLCc2kll-7BKiQss, reason: not valid java name */
    public static /* synthetic */ void m21636$r8$lambda$1ZALsdD55DOLCc2kll7BKiQss(RichDetailsCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$tyLK5dMnEU56gNAdN08iVtLwsWM(RichDetailsCell r0) {
        r0.lambda$new$1();
    }

    /* renamed from: -$$Nest$fgetarrow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedArrowDrawable m21637$$Nest$fgetarrow(RichDetailsCell r0) {
        return r0.arrow;
    }

    /* renamed from: -$$Nest$fgetarrowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m21638$$Nest$fgetarrowView(RichDetailsCell r0) {
        return r0.arrowView;
    }

    /* renamed from: -$$Nest$fgetcurrentRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlockRow m21639$$Nest$fgetcurrentRow(RichDetailsCell r0) {
        return r0.currentRow;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21640$$Nest$fgetdelegate(RichDetailsCell r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgethijackingSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21641$$Nest$fgethijackingSelection(RichDetailsCell r0) {
        return r0.hijackingSelection;
    }

    /* renamed from: -$$Nest$fputhijackingSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21642$$Nest$fputhijackingSelection(RichDetailsCell r0, boolean r1) {
        r0.hijackingSelection = r1;
    }

    /* renamed from: -$$Nest$mrememberAutoBoldState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21643$$Nest$mrememberAutoBoldState(RichDetailsCell r0) {
        r0.rememberAutoBoldState();
    }

    public RichDetailsCell(Context r10, Theme.ResourcesProvider r11) {
        super(r10);
        this.dividerPaint = new Paint();
        this.resourcesProvider = r11;
        setClipToPadding(false);
        setWillNotDraw(false);
        AnimatedArrowDrawable r1 = new AnimatedArrowDrawable(Theme.getColor(Theme.key_chat_inArticleDetailsArrow, r11), 12.66f, 6.16f, 1.66f);
        this.arrow = r1;
        AnonymousClass1 r2 = new AnonymousClass1(this);
        this.arrowCallback = r2;
        r1.setCallback(r2);
        View r12 = new AnonymousClass2(this, r10);
        this.arrowView = r12;
        r12.setOnClickListener(new RichDetailsCell$$ExternalSyntheticLambda0(this));
        addView(r12, LayoutHelper.createFrame(53, -1, 51));
        RichEditText r13 = new RichEditText(r10, r11);
        this.editText = r13;
        r13.setAllowNewlines(false);
        r13.setTextSize(1, SharedConfig.fontSize);
        r13.setHint(LocaleController.getString(R.string.ArticleHintDetailsTitle));
        r13.setPadding(0, AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(12.66f));
        r13.setListener(new AnonymousClass3(this));
        r13.setDelegate(new RichDetailsCell$$ExternalSyntheticLambda1(this));
        addView(r13, LayoutHelper.createFrame(-1, -2.0f, 51, 53.0f, 0.0f, 16.0f, 0.0f));
        updateColors();
    }

    private void initializeAutoBold(BlockRow r3, CharSequence r4) {
        if (r3.titleAutoBoldInitialized == false) goto L5;
        return;
    L5:
        boolean r2 = true;
        r3.titleAutoBoldInitialized = true;
        if (r4.length() != 0) goto L8;
    L11:
        r3.titleAutoBold = r2;
        return;
    L8:
        if ((RichTextStyle.stylesFullyCovering(r4, 0, r4.length()) & 1) != 0) goto L11;
        r2 = false;
        goto L11
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        Delegate r12 = this.delegate;
        if (r12 == null) goto L8;
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L9;
        r12.onToggle(r0);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$1() {
        rememberAutoBoldState();
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L7;
        TL_iv.PageBlock r02 = r0.block;
        if ((r02 instanceof TL_iv.pageBlockDetails) == false) goto L7;
        TL_iv.RichText r1 = RichTextStyle.fromSpannable(this.editText.getText());
        ((TL_iv.pageBlockDetails) r02).title = r1;
    L7:
        Delegate r03 = this.delegate;
        if (r03 == null) goto L13;
        BlockRow r2 = this.currentRow;
        if (r2 == null) goto L14;
        r03.onSpansChanged(r2);
        return;
    L14:
        return;
    }

    private void rememberAutoBoldState() {
        BlockRow r0 = this.currentRow;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.titleAutoBoldInitialized = true;
        r0.titleAutoBold = this.editText.isAutoBold();
    }

    public void bind(BlockRow r4, Delegate r5) {
        if (this.currentRow == r4) goto L5;
        boolean r0 = true;
    L6:
        this.currentRow = r4;
        this.delegate = r5;
        TL_iv.PageBlock r52 = r4.block;
        if ((r52 instanceof TL_iv.pageBlockDetails) == false) goto L21;
        TL_iv.pageBlockDetails r53 = (TL_iv.pageBlockDetails) r52;
        AnimatedArrowDrawable r1 = this.arrow;
        if (r53.open == false) goto L12;
        float r2 = 0.0f;
    L13:
        r1.setAnimationProgressAnimated(r2);
        CharSequence r12 = RichTextStyle.toSpannable(r53.title);
        initializeAutoBold(r4, r12);
        this.editText.setAutoBold(r4.titleAutoBold);
        if (r0 == false) goto L16;
    L19:
        this.editText.setTextSilently(r12);
        this.editText.invalidateEffects();
        return;
    L16:
        if (String.valueOf(this.editText.getText()).equals(RichTextStyle.plainOf(r53.title)) == false) goto L19;
        return;
    L12:
        r2 = 1.0f;
        goto L13
    L21:
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L5;
        TextSelectionHelper.ArticleTextSelectionHelper r02 = r0.getSelectionHelper();
    L6:
        if (r02 != null) goto L8;
    L10:
        super.dispatchDraw(r5);
        return;
    L8:
        if (this.editText.getLayout() == null) goto L10;
        r5.save();
        r5.translate(this.editText.getLeft() + this.editText.getPaddingLeft(), this.editText.getTop() + this.editText.getPaddingTop());
        r02.draw(r5, this, 0);
        r5.restore();
        goto L10
    L5:
        r02 = null;
        goto L6
    }

    @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
    public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r5) {
        Layout r0 = this.editText.getLayout();
        if (r0 != null) goto L5;
        return;
    L5:
        r5.add(new AnonymousClass4(this, r0, this.editText.getLeft() + this.editText.getPaddingLeft(), this.editText.getTop() + this.editText.getPaddingTop()));
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public RichEditText getEditText() {
        return this.editText;
    }

    public BlockRow getRow() {
        return this.currentRow;
    }

    public boolean isPressOnEmptyEditText(int r4, int r5) {
        if (this.editText.length() == 0) goto L6;
        return false;
    L6:
        if (r4 >= this.editText.getLeft()) goto L8;
    L15:
        return false;
    L8:
        if (r4 > (this.editText.getLeft() + this.editText.getWidth())) goto L15;
        if (r5 < this.editText.getTop()) goto L15;
        if (r5 > (this.editText.getTop() + this.editText.getHeight())) goto L15;
        return true;
    }

    public boolean isPressOnText(int r6, int r7) {
        Layout r0 = this.editText.getLayout();
        if (r0 != null) goto L5;
    L23:
        return false;
    L5:
        if (this.editText.length() == 0) goto L23;
        int r62 = r6 - (this.editText.getLeft() + this.editText.getPaddingLeft());
        int r72 = r7 - (this.editText.getTop() + this.editText.getPaddingTop());
        if (r72 < 0) goto L23;
        if (r72 >= r0.getHeight()) goto L23;
        int r73 = r0.getLineForVertical(r72);
        if (r73 < 0) goto L23;
        if (r73 >= r0.getLineCount()) goto L23;
        int r2 = AndroidUtilities.dp(24.0f);
        int r5 = Math.max(0, (this.editText.getWidth() - this.editText.getPaddingLeft()) - this.editText.getPaddingRight());
        float r22 = r2;
        float r3 = Math.max(0.0f, r0.getLineLeft(r73) - r22);
        float r52 = Math.min(r5, r0.getLineRight(r73) + r22);
        float r63 = r62;
        if (r63 < r3) goto L23;
        if (r63 > r52) goto L23;
        return true;
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L9;
        TL_iv.PageBlock r02 = r0.block;
        if ((r02 instanceof TL_iv.pageBlockDetails) == false) goto L9;
        if (((TL_iv.pageBlockDetails) r02).open == false) goto L9;
        return;
    L9:
        r9.drawRect(0.0f, r0 - 1, getMeasuredWidth(), getMeasuredHeight(), this.dividerPaint);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
    }

    public void requestEditFocus() {
        this.editText.requestEditFocus();
    }

    public void setLocked(boolean r1) {
        this.editText.setLocked(r1);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.editText.updateColors();
        this.arrow.setColor(Theme.getColor(Theme.key_chat_inArticleDetailsArrow, this.resourcesProvider));
        this.dividerPaint.setColor(Theme.getColor(Theme.key_chat_inArticleDetailsLine, this.resourcesProvider));
    }
}
