package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.Layout;
import android.view.View;
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
import org.telegram.ui.iv.RichEditText;

/* loaded from: classes7.dex */
class RichCaptionController {
    final RichEditText editText;
    private boolean hijackingSelection;
    private final Host host;
    private final Theme.ResourcesProvider resourcesProvider;

    /* renamed from: org.telegram.ui.iv.RichCaptionController$1, reason: invalid class name */
    public class AnonymousClass1 implements RichEditText.Listener {
        final /* synthetic */ RichCaptionController this$0;
        final /* synthetic */ Host val$host;

        public static /* synthetic */ void $r8$lambda$w6_LxS4F2LvhhwRaHuZl_eT7AMI(AnonymousClass1 r0, RichEditText r1, int r2, TextSelectionHelper.ArticleTextSelectionHelper r3, Host r4, int r5) {
            r0.lambda$onSelectionChanged$0(r1, r2, r3, r4, r5);
        }

        public AnonymousClass1(RichCaptionController r1, Host r2) {
            this.this$0 = r1;
            this.val$host = r2;
        }

        private /* synthetic */ void lambda$onSelectionChanged$0(RichEditText r3, int r4, TextSelectionHelper.ArticleTextSelectionHelper r5, Host r6, int r7) {
            if (r3.length() >= r4) goto L5;
            return;
        L5:
            if (r3.getSelectionStart() != r3.getSelectionEnd()) goto L8;
            return;
        L8:
            if (r5.selectRangeOf(r6.cell(), 0, r7, r4) == false) goto L12;
            RichCaptionController.m21635$$Nest$fputhijackingSelection(this.this$0, true);
            r3.setSelection(r4);
            RichCaptionController.m21635$$Nest$fputhijackingSelection(this.this$0, false);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onEnterPressed(RichEditText r1) {
            this.val$host.onCaptionEnter();
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onLockedInsert(RichEditText r1, CharSequence r2) {
            this.val$host.onCaptionLockedInsert(r2);
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onRequestWindowFocusable(RichEditText r1, boolean r2) {
            this.val$host.onRequestWindowFocusable(r1, r2);
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onSelectAll(RichEditText r1) {
            return this.val$host.onCaptionSelectAll();
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onSelectionChanged(RichEditText r9, int r10, int r11) {
            if (RichCaptionController.m21633$$Nest$fgethijackingSelection(this.this$0) == true) goto L16;
            if (r10 == r11) goto L17;
            TextSelectionHelper.ArticleTextSelectionHelper r5 = this.val$host.selectionHelper();
            if (r5 != null) goto L10;
            return;
        L10:
            if (r5.isInSelectionMode() == true) goto L12;
        L14:
            r9.post(new RichCaptionController$1$$ExternalSyntheticLambda0(this, r9, r11, r5, this.val$host, r10));
            return;
        L12:
            if (r5.getSelectedCell() != this.val$host.cell()) goto L14;
            return;
        L17:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextChanged(RichEditText r1, Editable r2) {
            this.this$0.persist();
            this.val$host.onCaptionChanged();
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextWillChange(RichEditText r1, int r2, int r3) {
            this.val$host.onCaptionWillChange(r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichCaptionController$2, reason: invalid class name */
    public class AnonymousClass2 implements TextSelectionHelper.TextLayoutBlock {
        final /* synthetic */ RichCaptionController this$0;
        final /* synthetic */ Layout val$layout;
        final /* synthetic */ int val$textX;
        final /* synthetic */ int val$textY;

        public AnonymousClass2(RichCaptionController r1, Layout r2, int r3, int r4) {
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
            BlockRow r0 = RichCaptionController.m21634$$Nest$fgethost(this.this$0).currentRow();
            if (r0 == null) goto L12;
            TL_iv.PageBlock r02 = r0.block;
            if (r02 == null) goto L14;
            TL_iv.PageCaption r03 = r02.caption;
            if (r03 == null) goto L15;
            TL_iv.RichText r04 = r03.text;
            if (r04 != null) goto L11;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L11:
            return RichTextStyle.toSpannable(r04);
        L15:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L14:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            return _UrlKt.FRAGMENT_ENCODE_SET;
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

    public interface Host {
        TextSelectionHelper.ArticleSelectableView cell();

        BlockRow currentRow();

        void onCaptionChanged();

        void onCaptionEnter();

        void onCaptionLockedInsert(CharSequence r1);

        boolean onCaptionSelectAll();

        void onCaptionSpansChanged();

        void onCaptionWillChange(int r1, int r2);

        void onRequestWindowFocusable(RichEditText r1, boolean r2);

        TextSelectionHelper.ArticleTextSelectionHelper selectionHelper();
    }

    public static /* synthetic */ void $r8$lambda$aAz4d_xHZSVppEuQojAfWVmB6Wg(RichCaptionController r0, Host r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgethijackingSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21633$$Nest$fgethijackingSelection(RichCaptionController r0) {
        return r0.hijackingSelection;
    }

    /* renamed from: -$$Nest$fgethost, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Host m21634$$Nest$fgethost(RichCaptionController r0) {
        return r0.host;
    }

    /* renamed from: -$$Nest$fputhijackingSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21635$$Nest$fputhijackingSelection(RichCaptionController r0, boolean r1) {
        r0.hijackingSelection = r1;
    }

    public RichCaptionController(Context r4, Theme.ResourcesProvider r5, Host r6) {
        this.resourcesProvider = r5;
        this.host = r6;
        RichEditText r0 = new RichEditText(r4, r5);
        this.editText = r0;
        r0.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        r0.setAllowNewlines(false);
        r0.setInputType(147457);
        r0.setGravity(8388659);
        r0.setTextSize(1, Math.max(8, SharedConfig.fontSize - 2));
        r0.setHint(LocaleController.getString(R.string.AddCaption));
        r0.setListener(new AnonymousClass1(this, r6));
        r0.setDelegate(new RichCaptionController$$ExternalSyntheticLambda0(this, r6));
        applyColors();
    }

    public static void ensureCaption(TL_iv.PageBlock r2) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.caption != null) goto L7;
        r2.caption = new TL_iv.PageCaption();
    L7:
        TL_iv.PageCaption r0 = r2.caption;
        if (r0.text != null) goto L10;
        r0.text = new TL_iv.textEmpty();
    L10:
        TL_iv.PageCaption r22 = r2.caption;
        if (r22.credit != null) goto L14;
        r22.credit = new TL_iv.textEmpty();
        return;
    }

    private /* synthetic */ void lambda$new$0(Host r1) {
        persist();
        r1.onCaptionSpansChanged();
    }

    public void applyColors() {
        this.editText.updateColors();
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider);
        this.editText.setTextColor(Theme.multAlpha(r0, 0.5f));
        this.editText.setHintTextColor(Theme.multAlpha(r0, 0.35f));
    }

    public void bind() {
        BlockRow r0 = this.host.currentRow();
        if (r0 == null) goto L11;
        TL_iv.PageBlock r1 = r0.block;
        if (r1 == null) goto L13;
        ensureCaption(r1);
        TL_iv.RichText r02 = r0.block.caption.text;
        String r12 = RichTextStyle.plainOf(r02);
        if (String.valueOf(this.editText.getText()).equals(r12) == true) goto L12;
        this.editText.setTextSilently(RichTextStyle.toSpannable(r02));
        this.editText.invalidateEffects();
        return;
    L12:
        return;
    L13:
        return;
    }

    public void drawSelection(Canvas r5) {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.host.selectionHelper();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.editText.getLayout() == null) goto L10;
        r5.save();
        r5.translate(this.editText.getLeft() + this.editText.getPaddingLeft(), this.editText.getTop() + this.editText.getPaddingTop());
        r0.draw(r5, this.host.cell(), 0);
        r5.restore();
        return;
    }

    public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r5) {
        Layout r0 = this.editText.getLayout();
        if (r0 != null) goto L5;
        return;
    L5:
        r5.add(new AnonymousClass2(this, r0, this.editText.getLeft() + this.editText.getPaddingLeft(), this.editText.getTop() + this.editText.getPaddingTop()));
    }

    public boolean isPressOnCaption(int r5, int r6) {
        Layout r0 = this.editText.getLayout();
        if (r0 != null) goto L5;
        return false;
    L5:
        int r52 = r5 - (this.editText.getLeft() + this.editText.getPaddingLeft());
        int r62 = r6 - (this.editText.getTop() + this.editText.getPaddingTop());
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

    public void layout(int r3, int r4, int r5, int r6) {
        int r32 = r3 + AndroidUtilities.dp(16.0f);
        int r52 = (r5 - r4) - AndroidUtilities.dp(16.0f);
        this.editText.layout(r32, r6, Math.max(r32, r52), this.editText.getMeasuredHeight() + r6);
    }

    public int measure(int r2, int r3, int r4) {
        int r32 = Math.max(0, ((r4 - r2) - r3) - (AndroidUtilities.dp(16.0f) * 2));
        this.editText.measure(View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        return this.editText.getMeasuredHeight();
    }

    public void persist() {
        BlockRow r0 = this.host.currentRow();
        if (r0 == null) goto L9;
        TL_iv.PageBlock r1 = r0.block;
        if (r1 == null) goto L10;
        ensureCaption(r1);
        TL_iv.PageCaption r02 = r0.block.caption;
        r02.text = RichTextStyle.fromSpannable(this.editText.getText());
        return;
    L10:
        return;
    }
}
