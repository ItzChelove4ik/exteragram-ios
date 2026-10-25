package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.FloatingToolbar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.CheckBoxBase;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.QuoteCollapseButton;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ReplyMessageLine;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.iv.RichEditText;
import org.telegram.ui.iv.RichEditor;

/* loaded from: classes7.dex */
public class RichTextCell extends FrameLayout implements Theme.Colorable, TextSelectionHelper.ArticleSelectableView {
    private boolean applyingCollapsedDecoration;
    private final RichEditText authorEditText;
    private final Paint bgPaint;
    private final TextView bullet;
    private final CheckBoxView checkBoxView;
    private QuoteCollapseButton collapseButton;
    private final RectF collapseButtonBounds;
    private boolean collapseButtonPressed;
    private int collapseExtraHeight;
    private CharacterStyle collapsedPart;
    private int collapsedPartEnd;
    private int collapsedPartStart;
    private BlockRow currentRow;
    private Delegate delegate;
    private final RichEditText editText;
    private boolean forceHint;
    private int highlightGeneration;
    private Runnable highlightScheduled;
    private String highlightedSnapshot;
    private boolean hijackingAuthorSelection;
    private boolean hijackingSelection;
    private final View indentSpacer;
    private LinearLayout languageButton;
    private ImageView languageButtonIcon;
    private TextView languageButtonText;
    private Drawable quoteIcon;
    private ReplyMessageLine quoteLine;
    private final Theme.ResourcesProvider resourcesProvider;
    private final LinearLayout row;
    private boolean showCommandBackground;
    private final ArrayList<TextSelectionHelper.TextLayoutBlock> tmpBlocks;

    /* renamed from: org.telegram.ui.iv.RichTextCell$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        private final Paint markerPaint;
        final /* synthetic */ RichTextCell this$0;

        public AnonymousClass1(RichTextCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.markerPaint = new Paint(1);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r6) {
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) != null) goto L5;
        L12:
            super.onDraw(r6);
            return;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0).level <= 0) goto L12;
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0).num != 0) goto L12;
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0).checkbox == true) goto L12;
            this.markerPaint.setColor(getCurrentTextColor());
            float r0 = AndroidUtilities.dpf2(4.3f) / 2.0f;
            r6.drawCircle(getWidth() / 2.0f, getBaseline() - (getTextSize() * 0.35f), r0, this.markerPaint);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichTextCell$2, reason: invalid class name */
    public class AnonymousClass2 implements RichEditText.Listener {
        final /* synthetic */ RichTextCell this$0;

        public static /* synthetic */ void $r8$lambda$GA8OTJiOajUi8S2gNiqBSmUjmVE(AnonymousClass2 r0, BlockRow r1, Transform r2) {
            r0.lambda$onTextChanged$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$UX3INXTWOh1nx7pcpHvGj8XlAgU(AnonymousClass2 r0, BlockRow r1, int r2) {
            r0.lambda$onTextChanged$0(r1, r2);
        }

        /* renamed from: $r8$lambda$WIQdO6Fo1EBlf-w-BKKAEiqAN8g, reason: not valid java name */
        public static /* synthetic */ void m21928$r8$lambda$WIQdO6Fo1EBlfwBKKAEiqAN8g(AnonymousClass2 r0, RichEditText r1, int r2, TextSelectionHelper.ArticleTextSelectionHelper r3, int r4) {
            r0.lambda$onSelectionChanged$2(r1, r2, r3, r4);
        }

        public AnonymousClass2(RichTextCell r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onSelectionChanged$2(RichEditText r5, int r6, TextSelectionHelper.ArticleTextSelectionHelper r7, int r8) {
            if (r5.length() >= r6) goto L5;
            return;
        L5:
            if (r5.getSelectionStart() == r5.getSelectionEnd()) goto L17;
            boolean r0 = r7.isInSelectionMode();
            RichTextCell r1 = this.this$0;
            if (r0 == false) goto L12;
            RichTextCell.m21914$$Nest$fputhijackingSelection(r1, true);
            r5.setSelection(r6);
            RichTextCell.m21914$$Nest$fputhijackingSelection(this.this$0, false);
            return;
        L12:
            if (r7.selectRangeOf(r1, r8, r6) == false) goto L16;
            RichTextCell.m21914$$Nest$fputhijackingSelection(this.this$0, true);
            r5.setSelection(r6);
            RichTextCell.m21914$$Nest$fputhijackingSelection(this.this$0, false);
            return;
        L16:
            return;
        }

        private /* synthetic */ void lambda$onTextChanged$0(BlockRow r3, int r4) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            RichTextCell.applyTextToBlock(r3.block, _UrlKt.FRAGMENT_ENCODE_SET);
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onCommand(r3, r4);
        }

        private /* synthetic */ void lambda$onTextChanged$1(BlockRow r8, Transform r9) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onTransform(r8, r9.block, r9.level, r9.num, r9.checkbox, r9.checked);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onBackspaceAtStart(RichEditText r1) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) != null) goto L7;
            return false;
        L7:
            return RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onBackspaceAtStart(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onBackspaceOnEmpty(RichEditText r1) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) == null) goto L9;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onBackspace(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onEnterPressed(RichEditText r9) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) == null) goto L30;
            String r0 = r9.getText().toString();
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onSlashSuggest(this.this$0, null);
            String r02 = RichTextCell.m21927$$Nest$smslashQuery(r0);
            if (r02 == null) goto L13;
            ArrayList<RichCommand> r03 = RichCommand.match(r02);
            if (r03.isEmpty() == true) goto L13;
            this.this$0.selectCommand(r03.get(0));
            return;
        L13:
            int r04 = RichTextCell.m21923$$Nest$smmatchCommand(r9.getText().toString());
            if (r04 == 0) goto L17;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onCommand(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0), r04);
            return;
        L17:
            Transform r92 = RichTextCell.m21924$$Nest$smmatchEnterTrigger(r9.getText().toString(), RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
            RichTextCell r05 = this.this$0;
            if (r92 == null) goto L22;
            RichTextCell.m21907$$Nest$fgetdelegate(r05).onTransform(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0), r92.block, r92.level, r92.num, r92.checkbox, r92.checked);
            return;
        L22:
            if ((RichTextCell.m21906$$Nest$fgetcurrentRow(r05).block instanceof TL_iv.pageBlockPullquote) == true) goto L24;
        L27:
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onEnter(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
            return;
        L24:
            if (RichTextCell.m21908$$Nest$fgeteditText(this.this$0).length() <= 0) goto L27;
            RichTextCell.m21916$$Nest$mensureAuthorVisibleAndFocus(this.this$0);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onLockedInsert(RichEditText r1, CharSequence r2) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onLockedInsert(r2);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onPaste(RichEditText r2) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) != null) goto L7;
            return false;
        L7:
            return RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onPaste(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0), r2);
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onRequestWindowFocusable(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onSelectAll(RichEditText r1) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) != null) goto L7;
            return false;
        L7:
            return RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onSelectAll(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onSelectionChanged(RichEditText r8, int r9, int r10) {
            if (RichTextCell.m21910$$Nest$fgethijackingSelection(this.this$0) == true) goto L13;
            if (r9 != r10) goto L6;
            return;
        L6:
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L15;
            TextSelectionHelper.ArticleTextSelectionHelper r5 = RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
            if (r5 == null) goto L16;
            this.this$0.post(new RichTextCell$2$$ExternalSyntheticLambda0(this, r8, r10, r5, r9));
            return;
        L16:
            return;
        L15:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onTab(RichEditText r1, boolean r2) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) != null) goto L7;
            return false;
        L7:
            return RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onIndent(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0), r2);
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextChanged(RichEditText r3, Editable r4) {
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) == null) goto L32;
            RichTextCell.m21919$$Nest$msizeHeaderEmojiToText(this.this$0, r4);
            RichTextCell.m21922$$Nest$mupdateListNumberStyle(this.this$0);
            RichTextCell.applyStyledTextToBlock(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0).block, r4);
            RichTextCell.m21918$$Nest$mscheduleHighlight(this.this$0);
            RichTextCell.m21920$$Nest$mupdateAuthorVisibility(this.this$0);
            RichTextCell.m21917$$Nest$mresetCollapsedIfTooShort(this.this$0);
            RichTextCell.m21921$$Nest$mupdateCollapsedDecoration(this.this$0);
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L9;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onTextChanged(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
        L9:
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L11;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onSlashSuggest(this.this$0, RichTextCell.m21927$$Nest$smslashQuery(r4.toString()));
        L11:
            int r32 = RichTextCell.m21925$$Nest$smmatchMarkdownCommand(r4.toString(), RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
            if (r32 != 0) goto L14;
        L16:
            Transform r33 = RichTextCell.m21926$$Nest$smmatchMarkdownTrigger(r4.toString(), RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
            if (r33 != null) goto L19;
        L22:
            if (RichTextCell.m21912$$Nest$fgetshowCommandBackground(this.this$0) == false) goto L24;
        L29:
            this.this$0.invalidate();
            return;
        L24:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) != null) goto L26;
            return;
        L26:
            if ((RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0).block instanceof TL_iv.pageBlockPullquote) == true) goto L29;
            return;
        L19:
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L22;
            BlockRow r42 = RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0);
            this.this$0.post(new RichTextCell$2$$ExternalSyntheticLambda2(this, r42, r33));
            goto L22
        L14:
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L16;
            BlockRow r43 = RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0);
            this.this$0.post(new RichTextCell$2$$ExternalSyntheticLambda1(this, r43, r32));
            goto L22
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextWillChange(RichEditText r1, int r2, int r3) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) == null) goto L9;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onTextWillChange(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0), r2, r3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichTextCell$3, reason: invalid class name */
    public class AnonymousClass3 implements RichEditText.Listener {
        final /* synthetic */ RichTextCell this$0;

        public static /* synthetic */ void $r8$lambda$HuwYvgrZZiBZUdYiPVxpQoXU1N8(AnonymousClass3 r0, RichEditText r1, int r2, TextSelectionHelper.ArticleTextSelectionHelper r3, int r4) {
            r0.lambda$onSelectionChanged$0(r1, r2, r3, r4);
        }

        public AnonymousClass3(RichTextCell r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onSelectionChanged$0(RichEditText r5, int r6, TextSelectionHelper.ArticleTextSelectionHelper r7, int r8) {
            if (r5.length() >= r6) goto L5;
            return;
        L5:
            if (r5.getSelectionStart() == r5.getSelectionEnd()) goto L17;
            boolean r0 = r7.isInSelectionMode();
            RichTextCell r1 = this.this$0;
            if (r0 == false) goto L12;
            RichTextCell.m21913$$Nest$fputhijackingAuthorSelection(r1, true);
            r5.setSelection(r6);
            RichTextCell.m21913$$Nest$fputhijackingAuthorSelection(this.this$0, false);
            return;
        L12:
            if (r7.selectRangeOf(r1, 1, r8, r6) == false) goto L16;
            RichTextCell.m21913$$Nest$fputhijackingAuthorSelection(this.this$0, true);
            r5.setSelection(r6);
            RichTextCell.m21913$$Nest$fputhijackingAuthorSelection(this.this$0, false);
            return;
        L16:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onBackspaceAtStart(RichEditText r1) {
            RichTextCell.m21908$$Nest$fgeteditText(this.this$0).requestEditFocus();
            RichTextCell.m21908$$Nest$fgeteditText(this.this$0).setSelection(RichTextCell.m21908$$Nest$fgeteditText(this.this$0).length());
            return true;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onBackspaceOnEmpty(RichEditText r1) {
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onEnterPressed(RichEditText r1) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) == null) goto L9;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onQuoteAuthorEnter(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onLockedInsert(RichEditText r1, CharSequence r2) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onLockedInsert(r2);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onRequestWindowFocusable(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public boolean onSelectAll(RichEditText r1) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) != null) goto L7;
            return false;
        L7:
            return RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onSelectAll(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onSelectionChanged(RichEditText r8, int r9, int r10) {
            if (RichTextCell.m21909$$Nest$fgethijackingAuthorSelection(this.this$0) == true) goto L13;
            if (r9 != r10) goto L6;
            return;
        L6:
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L15;
            TextSelectionHelper.ArticleTextSelectionHelper r5 = RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
            if (r5 == null) goto L16;
            r8.post(new RichTextCell$3$$ExternalSyntheticLambda0(this, r8, r10, r5, r9));
            return;
        L16:
            return;
        L15:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextChanged(RichEditText r1, Editable r2) {
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) == null) goto L20;
            this.this$0.persistAuthor();
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) == null) goto L8;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onTextChanged(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0));
        L8:
            boolean r12 = RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0).block instanceof TL_iv.pageBlockPullquote;
            RichTextCell r22 = this.this$0;
            if (r12 == false) goto L13;
            r22.invalidate();
            return;
        L13:
            if ((RichTextCell.m21906$$Nest$fgetcurrentRow(r22).block instanceof TL_iv.pageBlockBlockquote) == false) goto L18;
            this.this$0.invalidate();
            if (RichTextCell.m21915$$Nest$mcollapseButtonExtraHeightChanged(this.this$0) == false) goto L19;
            this.this$0.requestLayout();
            return;
        L19:
            return;
        L18:
            return;
        }

        @Override // org.telegram.ui.iv.RichEditText.Listener
        public void onTextWillChange(RichEditText r1, int r2, int r3) {
            if (RichTextCell.m21907$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            if (RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0) == null) goto L9;
            RichTextCell.m21907$$Nest$fgetdelegate(this.this$0).onTextWillChange(RichTextCell.m21906$$Nest$fgetcurrentRow(this.this$0), r2, r3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichTextCell$4, reason: invalid class name */
    public class AnonymousClass4 implements TextSelectionHelper.TextLayoutBlock {
        final /* synthetic */ RichTextCell this$0;
        final /* synthetic */ Layout val$layout;
        final /* synthetic */ int val$textX;
        final /* synthetic */ int val$textY;

        public AnonymousClass4(RichTextCell r1, Layout r2, int r3, int r4) {
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

    /* renamed from: org.telegram.ui.iv.RichTextCell$5, reason: invalid class name */
    public class AnonymousClass5 implements TextSelectionHelper.TextLayoutBlock {
        final /* synthetic */ RichTextCell this$0;
        final /* synthetic */ Layout val$aLayout;
        final /* synthetic */ int val$ax;
        final /* synthetic */ int val$ay;

        public AnonymousClass5(RichTextCell r1, Layout r2, int r3, int r4) {
            this.this$0 = r1;
            this.val$aLayout = r2;
            this.val$ax = r3;
            this.val$ay = r4;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public Layout getLayout() {
            return this.val$aLayout;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getRow() {
            return 1;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public CharSequence getText() {
            return this.val$aLayout.getText();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getX() {
            return this.val$ax;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getY() {
            return this.val$ay;
        }
    }

    public static class CheckBoxView extends View {
        private final CheckBoxBase checkBox;

        public CheckBoxView(Context r2, Theme.ResourcesProvider r3) {
            super(r2);
            CheckBoxBase r22 = new CheckBoxBase(this, 20, r3);
            this.checkBox = r22;
            r22.setColor(Theme.key_telegram_color, Theme.key_dialogCheckboxSquareDisabled, Theme.key_checkboxCheck);
            r22.setBackgroundType(10);
            r22.setDrawUnchecked(true);
            r22.setCustomRadius(AndroidUtilities.dp(5.0f));
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.checkBox.onAttachedToWindow();
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.checkBox.onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            int r0 = AndroidUtilities.dp(20.0f);
            int r1 = (getWidth() - r0) / 2;
            int r2 = (getHeight() - r0) / 2;
            this.checkBox.setBounds(r1, r2, r0, r0);
            this.checkBox.draw(r5);
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(24.0f));
        }

        public void setChecked(boolean r1, boolean r2) {
            this.checkBox.setChecked(r1, r2);
        }
    }

    public class CollapsedTextPart extends CharacterStyle {
        final /* synthetic */ RichTextCell this$0;

        private CollapsedTextPart(RichTextCell r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ CollapsedTextPart(RichTextCell r1, RichTextCellIA r2) {
            this(r1);
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            int r2 = Theme.getColor(Theme.key_featuredStickers_addButton, RichTextCell.m21911$$Nest$fgetresourcesProvider(this.this$0));
            r3.setColor(Theme.blendOver(Theme.multAlpha(r3.getColor(), 0.55f), Theme.multAlpha(r2, 0.4f)));
        }
    }

    public interface Delegate {
        int getListPaddingBottom(BlockRow r1);

        int getListPaddingTop(BlockRow r1);

        default int getOrderedListMarkerWidth(BlockRow r3, Paint r4) {
            StringBuilder r2 = new StringBuilder();
            if (r3 == null) goto L5;
            int r32 = r3.num;
        L6:
            r2.append(r32);
            r2.append(".");
            String r22 = r2.toString();
            return Math.max(AndroidUtilities.dp(28.0f), ((int) Math.ceil(r4.measureText(r22))) + AndroidUtilities.dp(10.0f));
        L5:
            r32 = 1;
            goto L6
        }

        TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper();

        void onBackspace(BlockRow r1);

        boolean onBackspaceAtStart(BlockRow r1);

        void onCheckboxToggle(BlockRow r1, boolean r2);

        void onCommand(BlockRow r1, int r2);

        void onEnter(BlockRow r1);

        boolean onIndent(BlockRow r1, boolean r2);

        void onLanguageClick(BlockRow r1, View r2);

        void onLockedInsert(CharSequence r1);

        boolean onPaste(BlockRow r1, RichEditText r2);

        void onQuoteAuthorEnter(BlockRow r1);

        void onRequestWindowFocusable(RichEditText r1, boolean r2);

        boolean onSelectAll(BlockRow r1);

        void onSlashSuggest(RichTextCell r1, String r2);

        void onSpansChanged(BlockRow r1);

        void onTextChanged(BlockRow r1);

        void onTextWillChange(BlockRow r1, int r2, int r3);

        void onTransform(BlockRow r1, TL_iv.PageBlock r2, int r3, int r4, boolean r5, boolean r6);
    }

    public static final class Factory extends UItem.UItemFactory<RichTextCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(BlockRow r1, Delegate r2, boolean r3) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            r0.object2 = r2;
            r0.red = r3;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            ((RichTextCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2, r2.red);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichTextCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            RichTextCell r0 = new RichTextCell(r1, r5);
            r0.setBackground(new RichEditor.DraggingDrawable(Theme.getColor(Theme.key_windowBackgroundWhite, r5)));
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public static class Transform {
        final TL_iv.PageBlock block;
        final boolean checkbox;
        final boolean checked;
        final int level;
        final int num;

        public Transform(TL_iv.PageBlock r7, int r8, int r9) {
            this(r7, r8, r9, false, false);
        }

        public Transform(TL_iv.PageBlock r1, int r2, int r3, boolean r4, boolean r5) {
            this.block = r1;
            this.level = r2;
            this.num = r3;
            this.checkbox = r4;
            this.checked = r5;
        }
    }

    /* renamed from: $r8$lambda$8G1LpYCEOlDIKQeO_-FXwi-Oyf4, reason: not valid java name */
    public static /* synthetic */ void m21902$r8$lambda$8G1LpYCEOlDIKQeO_FXwiOyf4(RichTextCell r0) {
        r0.runHighlight();
    }

    /* renamed from: $r8$lambda$As9vyLuMMUidz-kZWKV042Lsy7Q, reason: not valid java name */
    public static /* synthetic */ void m21903$r8$lambda$As9vyLuMMUidzkZWKV042Lsy7Q(RichTextCell r0, View r1, boolean r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$BILVUuPGpg4_-GkrPX51nlY2rsE, reason: not valid java name */
    public static /* synthetic */ void m21904$r8$lambda$BILVUuPGpg4_GkrPX51nlY2rsE(RichTextCell r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$GilXHkTRxa3MrTkfQX0YJY8IfXA(RichTextCell r0, float r1) {
        r0.lambda$focusAuthorFromBody$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$TiqsKIbaZfriEmyshD0vUu2nzPM(RichTextCell r0, View r1) {
        r0.lambda$updateLanguageButton$4(r1);
    }

    /* renamed from: $r8$lambda$WlQ7-4KoGXP_hHDx2kZV7wXg4-E, reason: not valid java name */
    public static /* synthetic */ boolean m21905$r8$lambda$WlQ74KoGXP_hHDx2kZV7wXg4E(View r0) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$c_Cy8cTOalt9cl38gb7mTlN75zI(RichTextCell r0, float r1) {
        r0.lambda$focusBodyFromAuthor$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$hPX4msRg2pC8MuWYZPkyTqUyKCE(RichTextCell r0, int r1, BlockRow r2, String r3, SpannableString r4) {
        r0.lambda$runHighlight$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$l1XkrDlLOTbPsvcr5IUoE4Sz9hI(RichTextCell r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$p0yDMNcEX9A2L4ZQWrDVxWVRGro(RichTextCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlockRow m21906$$Nest$fgetcurrentRow(RichTextCell r0) {
        return r0.currentRow;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21907$$Nest$fgetdelegate(RichTextCell r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichEditText m21908$$Nest$fgeteditText(RichTextCell r0) {
        return r0.editText;
    }

    /* renamed from: -$$Nest$fgethijackingAuthorSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21909$$Nest$fgethijackingAuthorSelection(RichTextCell r0) {
        return r0.hijackingAuthorSelection;
    }

    /* renamed from: -$$Nest$fgethijackingSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21910$$Nest$fgethijackingSelection(RichTextCell r0) {
        return r0.hijackingSelection;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m21911$$Nest$fgetresourcesProvider(RichTextCell r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetshowCommandBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21912$$Nest$fgetshowCommandBackground(RichTextCell r0) {
        return r0.showCommandBackground;
    }

    /* renamed from: -$$Nest$fputhijackingAuthorSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21913$$Nest$fputhijackingAuthorSelection(RichTextCell r0, boolean r1) {
        r0.hijackingAuthorSelection = r1;
    }

    /* renamed from: -$$Nest$fputhijackingSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21914$$Nest$fputhijackingSelection(RichTextCell r0, boolean r1) {
        r0.hijackingSelection = r1;
    }

    /* renamed from: -$$Nest$mcollapseButtonExtraHeightChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21915$$Nest$mcollapseButtonExtraHeightChanged(RichTextCell r0) {
        return r0.collapseButtonExtraHeightChanged();
    }

    /* renamed from: -$$Nest$mensureAuthorVisibleAndFocus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21916$$Nest$mensureAuthorVisibleAndFocus(RichTextCell r0) {
        r0.ensureAuthorVisibleAndFocus();
    }

    /* renamed from: -$$Nest$mresetCollapsedIfTooShort, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21917$$Nest$mresetCollapsedIfTooShort(RichTextCell r0) {
        r0.resetCollapsedIfTooShort();
    }

    /* renamed from: -$$Nest$mscheduleHighlight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21918$$Nest$mscheduleHighlight(RichTextCell r0) {
        r0.scheduleHighlight();
    }

    /* renamed from: -$$Nest$msizeHeaderEmojiToText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21919$$Nest$msizeHeaderEmojiToText(RichTextCell r0, CharSequence r1) {
        r0.sizeHeaderEmojiToText(r1);
    }

    /* renamed from: -$$Nest$mupdateAuthorVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21920$$Nest$mupdateAuthorVisibility(RichTextCell r0) {
        r0.updateAuthorVisibility();
    }

    /* renamed from: -$$Nest$mupdateCollapsedDecoration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21921$$Nest$mupdateCollapsedDecoration(RichTextCell r0) {
        r0.updateCollapsedDecoration();
    }

    /* renamed from: -$$Nest$mupdateListNumberStyle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21922$$Nest$mupdateListNumberStyle(RichTextCell r0) {
        r0.updateListNumberStyle();
    }

    /* renamed from: -$$Nest$smmatchCommand, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21923$$Nest$smmatchCommand(String r0) {
        return matchCommand(r0);
    }

    /* renamed from: -$$Nest$smmatchEnterTrigger, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Transform m21924$$Nest$smmatchEnterTrigger(String r0, BlockRow r1) {
        return matchEnterTrigger(r0, r1);
    }

    /* renamed from: -$$Nest$smmatchMarkdownCommand, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21925$$Nest$smmatchMarkdownCommand(String r0, BlockRow r1) {
        return matchMarkdownCommand(r0, r1);
    }

    /* renamed from: -$$Nest$smmatchMarkdownTrigger, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Transform m21926$$Nest$smmatchMarkdownTrigger(String r0, BlockRow r1) {
        return matchMarkdownTrigger(r0, r1);
    }

    /* renamed from: -$$Nest$smslashQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21927$$Nest$smslashQuery(String r0) {
        return slashQuery(r0);
    }

    public RichTextCell(Context r10, Theme.ResourcesProvider r11) {
        super(r10);
        this.tmpBlocks = new ArrayList();
        this.collapseButtonBounds = new RectF();
        this.collapsedPartStart = -1;
        this.collapsedPartEnd = -1;
        this.bgPaint = new Paint(1);
        this.resourcesProvider = r11;
        setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(4.66f));
        setClipToPadding(false);
        LinearLayout r4 = new LinearLayout(r10);
        this.row = r4;
        r4.setOrientation(0);
        View r5 = new View(r10);
        this.indentSpacer = r5;
        r4.addView(r5, new LinearLayout.LayoutParams(0, -2));
        AnonymousClass1 r52 = new AnonymousClass1(this, r10);
        this.bullet = r52;
        r52.setGravity(NavigationBarView.ITEM_GRAVITY_START_CENTER);
        r52.setPaddingRelative(AndroidUtilities.dp(6.0f), 0, 0, 0);
        r52.setSingleLine(true);
        r52.setIncludeFontPadding(false);
        r52.setTextSize(1, 16.0f);
        r4.addView(r52, LayoutHelper.createLinear(18, -2));
        CheckBoxView r2 = new CheckBoxView(r10, r11);
        this.checkBoxView = r2;
        r2.setVisibility(8);
        r2.setOnClickListener(new RichTextCell$$ExternalSyntheticLambda0(this));
        r4.addView(r2, LayoutHelper.createLinear(18, -2));
        RichEditText r1 = new RichEditText(r10, r11);
        this.editText = r1;
        r1.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        r1.setListener(new AnonymousClass2(this));
        r1.setDelegate(new RichTextCell$$ExternalSyntheticLambda1(this));
        r1.setOnFocusChangeListener(new RichTextCell$$ExternalSyntheticLambda2(this));
        r4.addView(r1, LayoutHelper.createLinear(0, -2, 1.0f));
        addView(r4, LayoutHelper.createFrame(-1, -2, 51));
        RichEditText r42 = new RichEditText(r10, r11);
        this.authorEditText = r42;
        r42.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(1.0f));
        r42.setAllowNewlines(false);
        r42.setInputType(147457);
        r42.setListener(new AnonymousClass3(this));
        r42.setDelegate(new RichTextCell$$ExternalSyntheticLambda3(this));
        r42.setVisibility(8);
        addView(r42, LayoutHelper.createFrame(-1, -2, 51));
        updateColors();
    }

    private void applyAuthorStyle(TL_iv.PageBlock r4) {
        this.authorEditText.setTextSize(1, Math.max(8, SharedConfig.fontSize - 2));
        this.authorEditText.setTypeface(AndroidUtilities.bold());
        this.authorEditText.setTextColorKey(Theme.key_featuredStickers_addButton);
        this.authorEditText.setAccentHint(true);
        this.authorEditText.setHint(LocaleController.getString(R.string.ArticleHintAuthor));
        boolean r42 = r4 instanceof TL_iv.pageBlockPullquote;
        RichEditText r3 = this.authorEditText;
        if (r42 == false) goto L6;
        r3.setGravity(49);
        return;
    L6:
        r3.setGravity(8388659);
    }

    private void applyColorSpans(Editable r7, SpannableString r8) {
        int r0 = 0;
        CodeHighlighting.ColorSpan[] r6 = (CodeHighlighting.ColorSpan[]) r7.getSpans(0, r7.length(), CodeHighlighting.ColorSpan.class);
        int r2 = 0;
    L4:
        if (r2 >= r6.length) goto L6;
        r7.removeSpan(r6[r2]);
        r2 = r2 + 1;
        goto L4
    L6:
        CodeHighlighting.ColorSpan[] r62 = (CodeHighlighting.ColorSpan[]) r8.getSpans(0, r8.length(), CodeHighlighting.ColorSpan.class);
        int r1 = r7.length();
    L8:
        if (r0 >= r62.length) goto L16;
        int r22 = r8.getSpanStart(r62[r0]);
        int r3 = r8.getSpanEnd(r62[r0]);
        if (r22 < 0) goto L15;
        if (r3 > r1) goto L15;
        if (r22 >= r3) goto L15;
        r7.setSpan(r62[r0], r22, r3, 33);
    L15:
        r0 = r0 + 1;
        goto L8
    }

    private void applyListDecoration(BlockRow r7) {
        int r0 = r7.level;
        View r1 = this.indentSpacer;
        if (r0 > 0) goto L6;
        r1.setVisibility(8);
        this.bullet.setVisibility(8);
        this.checkBoxView.setVisibility(8);
        return;
    L6:
        LinearLayout.LayoutParams r12 = (LinearLayout.LayoutParams) r1.getLayoutParams();
        r12.width = (r0 - 1) * AndroidUtilities.dp(24.0f);
        this.indentSpacer.setLayoutParams(r12);
        View r13 = this.indentSpacer;
        if (r0 <= 1) goto L9;
        int r02 = 0;
    L10:
        r13.setVisibility(r02);
        if (r7.checkbox == false) goto L14;
        this.bullet.setVisibility(8);
        this.checkBoxView.setVisibility(0);
        this.checkBoxView.setChecked(r7.checked, false);
        return;
    L14:
        this.checkBoxView.setVisibility(8);
        this.bullet.setVisibility(0);
        LinearLayout.LayoutParams r03 = (LinearLayout.LayoutParams) this.bullet.getLayoutParams();
        if (r7.num != 0) goto L17;
        int r14 = AndroidUtilities.dp(18.0f);
    L22:
        if (r03.width == r14) goto L24;
        r03.width = r14;
        this.bullet.setLayoutParams(r03);
    L24:
        TextView r6 = this.bullet;
        if (r7.num != 0) goto L27;
        String r72 = _UrlKt.FRAGMENT_ENCODE_SET;
    L28:
        r6.setText(r72);
        return;
    L27:
        r72 = r7.num + ".";
        goto L28
    L17:
        Delegate r15 = this.delegate;
        if (r15 == null) goto L20;
        r14 = r15.getOrderedListMarkerWidth(r7, this.bullet.getPaint());
        goto L22
    L20:
        int r16 = AndroidUtilities.dp(28.0f);
        TextPaint r3 = this.bullet.getPaint();
        r14 = Math.max(r16, ((int) Math.ceil(r3.measureText(r7.num + "."))) + AndroidUtilities.dp(10.0f));
        goto L22
    L9:
        r02 = 8;
        goto L10
    }

    private void applyQuoteInset(BlockRow r7) {
        int r0 = RichBlockChrome.quoteInset(r7);
        int r1 = RichBlockChrome.quoteInsetEnd(r7);
        if (r0 > 0) goto L6;
        if (r1 > 0) goto L6;
        return;
    L6:
        boolean r2 = r7.block instanceof TL_iv.pageBlockPreformatted;
        int r3 = getPaddingTop();
        int r4 = getPaddingBottom();
        if (r7.quoteFirst == false) goto L14;
        int r32 = RichBlockChrome.quoteTopPad(r7);
        if (r2 == false) goto L11;
        int r5 = AndroidUtilities.dp(24.0f);
    L12:
        r3 = r32 + r5;
        goto L14
    L11:
        r5 = 0;
    L14:
        if (r7.quoteLast == false) goto L16;
        r4 = RichBlockChrome.quoteBottomPad(r7);
    L16:
        if (r2 == false) goto L19;
        r0 = r0 + AndroidUtilities.dp(8.0f);
        r1 = r1 + AndroidUtilities.dp(8.0f);
    L19:
        if (RichBlockChrome.rtl() == false) goto L22;
        setPadding(getPaddingLeft() + r1, r3, getPaddingRight() + r0, r4);
        return;
    L22:
        setPadding(getPaddingLeft() + r0, r3, getPaddingRight() + r1, r4);
    }

    private void applyStyle(TL_iv.PageBlock r12) {
        int r0 = SharedConfig.fontSize;
        this.editText.setCenterEmptyHint(false);
        this.editText.setHint(getHint());
        this.editText.setTextColorKey(Theme.key_windowBackgroundWhiteBlackText);
        this.editText.setLineSpacing(0.0f, 1.0f);
        if ((r12 instanceof TL_iv.pageBlockPreformatted) == false) goto L7;
        setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(31.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(19.0f));
        this.editText.setInputType(655505);
        this.editText.setAllowNewlines(true);
        this.editText.setSoftEnterNewline(false);
        this.editText.setGravity(8388659);
        this.editText.setTextSize(1, r0 - 1);
        this.editText.setTypeface(Typeface.MONOSPACE);
        RichEditText r122 = this.editText;
        r122.setLineSpacing(r122.getPaint().getFontSpacing() * 0.3f, 1.0f);
        this.editText.setAccentHint(false);
        return;
    L7:
        if ((r12 instanceof TL_iv.pageBlockBlockquote) == false) goto L10;
        this.editText.setInputType(147457);
        this.editText.setAllowNewlines(false);
        this.editText.setSoftEnterNewline(false);
        this.editText.setGravity(8388659);
        setPadding(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(16.0f));
        this.editText.setTextSize(1, Math.max(8, r0 - 2));
        this.editText.setTypeface(AndroidUtilities.regular());
        this.editText.setAccentHint(true);
        return;
    L10:
        boolean r1 = r12 instanceof TL_iv.pageBlockPullquote;
        RichEditText r8 = this.editText;
        if (r1 == false) goto L14;
        r8.setInputType(147457);
        this.editText.setAllowNewlines(false);
        this.editText.setSoftEnterNewline(true);
        this.editText.setGravity(49);
        this.editText.setCenterEmptyHint(true);
        setPadding(AndroidUtilities.dp(40.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(40.0f), AndroidUtilities.dp(16.0f));
        this.editText.setTextSize(1, Math.max(8, r0 - 2));
        this.editText.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_ITALIC));
        this.editText.setAccentHint(true);
        return;
    L14:
        r8.setInputType(147457);
        this.editText.setAllowNewlines(false);
        this.editText.setSoftEnterNewline(false);
        this.editText.setGravity(8388659);
        boolean r13 = r12 instanceof TL_iv.pageBlockHeading1;
        if (r13 == false) goto L17;
    L28:
        boolean r3 = true;
    L29:
        int r7 = AndroidUtilities.dp(16.0f);
        if (r3 == false) goto L32;
        float r9 = 11.0f;
    L33:
        int r92 = AndroidUtilities.dp(r9);
        int r10 = AndroidUtilities.dp(16.0f);
        if (r3 == false) goto L36;
        float r32 = 7.0f;
    L37:
        setPadding(r7, r92, r10, AndroidUtilities.dp(r32));
        BlockRow r33 = this.currentRow;
        if (r33 != null) goto L40;
    L50:
        if (r13 == false) goto L53;
        this.editText.setTextSize(1, r0 + 3);
        this.editText.setTypeface(AndroidUtilities.bold());
    L83:
        this.editText.setAccentHint(false);
        return;
    L53:
        if ((r12 instanceof TL_iv.pageBlockHeading2) == false) goto L56;
        this.editText.setTextSize(1, r0 + 2);
        this.editText.setTypeface(AndroidUtilities.bold());
        goto L83
    L56:
        if ((r12 instanceof TL_iv.pageBlockHeading3) == false) goto L59;
        this.editText.setTextSize(1, r0 + 1);
        this.editText.setTypeface(AndroidUtilities.bold());
        goto L83
    L59:
        if ((r12 instanceof TL_iv.pageBlockHeading4) == false) goto L62;
        this.editText.setTextSize(1, r0);
        this.editText.setTypeface(AndroidUtilities.bold());
        goto L83
    L62:
        if ((r12 instanceof TL_iv.pageBlockHeading5) == false) goto L65;
        this.editText.setTextSize(1, r0 - 1);
        this.editText.setTypeface(AndroidUtilities.bold());
        goto L83
    L65:
        if ((r12 instanceof TL_iv.pageBlockHeading6) == false) goto L68;
        this.editText.setTextSize(1, r0 - 2);
        this.editText.setTypeface(AndroidUtilities.bold());
        goto L83
    L68:
        if ((r12 instanceof TL_iv.pageBlockFooter) == false) goto L71;
        this.editText.setTextSize(1, r0 - 2);
        this.editText.setTypeface(AndroidUtilities.regular());
        this.editText.setTextColorKey(Theme.key_chat_inReplyMessageText);
        goto L83
    L71:
        if ((r12 instanceof TL_iv.pageBlockParagraph) == false) goto L77;
        BlockRow r123 = this.currentRow;
        if (r123 == null) goto L77;
        if (r123.quoteIds.isEmpty() == true) goto L77;
        boolean r124 = true;
    L78:
        RichEditText r14 = this.editText;
        if (r124 == false) goto L81;
        float r125 = Math.max(8, r0 - 2);
    L82:
        r14.setTextSize(1, r125);
        this.editText.setTypeface(AndroidUtilities.regular());
        goto L83
    L81:
        r125 = r0;
    L77:
        r124 = false;
        goto L78
    L40:
        if (r33.level <= 0) goto L50;
        int r34 = AndroidUtilities.dp(16.0f);
        Delegate r72 = this.delegate;
        if (r72 == null) goto L44;
        int r73 = r72.getListPaddingTop(this.currentRow);
    L45:
        int r5 = AndroidUtilities.dp(16.0f);
        Delegate r93 = this.delegate;
        if (r93 == null) goto L48;
        int r82 = r93.getListPaddingBottom(this.currentRow);
    L49:
        setPadding(r34, r73, r5, r82);
        goto L50
    L48:
        r82 = AndroidUtilities.dp(11.0f);
        goto L49
    L44:
        r73 = AndroidUtilities.dp(8.0f);
        goto L45
    L36:
        r32 = 4.66f;
        goto L37
    L32:
        r9 = 5.0f;
        goto L33
    L17:
        if ((r12 instanceof TL_iv.pageBlockHeading2) == true) goto L28;
        if ((r12 instanceof TL_iv.pageBlockHeading3) == true) goto L28;
        if ((r12 instanceof TL_iv.pageBlockHeading4) == true) goto L28;
        if ((r12 instanceof TL_iv.pageBlockHeading5) == true) goto L28;
        if ((r12 instanceof TL_iv.pageBlockHeading6) == true) goto L28;
        r3 = false;
        goto L29
    }

    public static void applyStyledTextToBlock(TL_iv.PageBlock r0, CharSequence r1) {
        setRichText(r0, RichTextStyle.fromSpannable(r1));
    }

    public static void applyTextToBlock(TL_iv.PageBlock r1, String r2) {
        TL_iv.textPlain r0 = new TL_iv.textPlain();
        r0.text = r2;
        r1.text = r0;
    }

    private void bindAuthor(TL_iv.PageBlock r3) {
        if (isQuoteBlock(r3) == true) goto L6;
        this.authorEditText.setVisibility(8);
        return;
    L6:
        ensureCaption(r3);
        applyAuthorStyle(r3);
        String r0 = readAuthorPlain(r3);
        if (String.valueOf(this.authorEditText.getText()).equals(r0) == true) goto L9;
        CharSequence r32 = Emoji.replaceEmoji(readAuthorStyled(r3), this.authorEditText.getPaint().getFontMetricsInt(), false);
        this.authorEditText.setTextSilently(r32);
        this.authorEditText.invalidateEffects();
    L9:
        updateAuthorVisibility();
    }

    private static float caretX(RichEditText r2) {
        Layout r0 = r2.getLayout();
        if (r0 != null) goto L7;
        return 0.0f;
    L7:
        return r0.getPrimaryHorizontal(Math.max(0, Math.min(r2.getSelectionEnd(), r2.length())));
    }

    private void clearHighlight() {
        Editable r3 = this.editText.getText();
        if (r3 == null) goto L9;
        int r2 = 0;
        CodeHighlighting.ColorSpan[] r0 = (CodeHighlighting.ColorSpan[]) r3.getSpans(0, r3.length(), CodeHighlighting.ColorSpan.class);
    L7:
        if (r2 >= r0.length) goto L11;
        r3.removeSpan(r0[r2]);
        r2 = r2 + 1;
        goto L7
    L11:
        return;
    }

    private int collapseButtonExtraHeight(int r9, int r10) {
        if (hasCollapseButton() == true) goto L5;
    L27:
        return 0;
    L5:
        if (this.authorEditText.getVisibility() != 0) goto L27;
        Layout r0 = this.authorEditText.getLayout();
        if (r0 == null) goto L27;
        if (r0.getLineCount() <= 0) goto L27;
        ensureCollapseButton();
        boolean r3 = true;
        int r2 = r0.getLineCount() - 1;
        int r4 = getPaddingTop() + this.row.getMeasuredHeight();
        float r5 = (getPaddingLeft() + this.authorEditText.getPaddingLeft()) + r0.getLineRight(r2);
        float r6 = (this.authorEditText.getPaddingTop() + r4) + r0.getLineTop(r2);
        float r02 = (r4 + this.authorEditText.getPaddingTop()) + r0.getLineBottom(r2);
        int r22 = AndroidUtilities.dp(3.333f);
        float r92 = ((r9 - AndroidUtilities.dp(16.0f)) - r22) - this.collapseButton.width();
        int r42 = r10 - r22;
        float r7 = r42 - this.collapseButton.height();
        float r43 = r42;
        if (r5 <= r92) goto L15;
        boolean r93 = true;
    L17:
        if (r02 > r7) goto L19;
    L21:
        r3 = false;
    L22:
        if (r93 == false) goto L27;
        if (r3 == false) goto L27;
        return (int) Math.ceil(Math.max(0.0f, (((r02 + AndroidUtilities.dp(4.0f)) + r8) + r22) - r10));
    L19:
        if (r6 >= r43) goto L21;
    L15:
        r93 = false;
        goto L17
    }

    private boolean collapseButtonExtraHeightChanged() {
        int r0 = getMeasuredWidth();
        if (r0 > 0) goto L6;
        return true;
    L6:
        if (collapseButtonExtraHeight(r0, ((getPaddingTop() + this.row.getMeasuredHeight()) + this.authorEditText.getMeasuredHeight()) + getPaddingBottom()) == this.collapseExtraHeight) goto L8;
        return true;
    L8:
        return false;
    }

    private void drawCollapseButton(Canvas r12) {
        if (isBlockquote() == true) goto L5;
        return;
    L5:
        ensureCollapseButton();
        TL_iv.pageBlockBlockquote r0 = (TL_iv.pageBlockBlockquote) this.currentRow.block;
        int r8 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
        int r1 = AndroidUtilities.dp(3.333f);
        this.collapseButton.draw(r12, this.collapseButtonBounds, (getWidth() - AndroidUtilities.dp(16.0f)) - r1, (getHeight() - AndroidUtilities.dp(8.0f)) - r1, r8, r0.collapsed, hasCollapseButton());
    }

    private void ensureAuthorVisibleAndFocus() {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L6;
        ensureCaption(r0.block);
    L6:
        if (this.authorEditText.getVisibility() == 0) goto L8;
        this.authorEditText.setVisibility(0);
        requestLayout();
    L8:
        this.authorEditText.requestEditFocus();
        RichEditText r2 = this.authorEditText;
        r2.setSelection(r2.length());
    }

    public static void ensureCaption(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == false) goto L9;
        TL_iv.pageBlockBlockquote r12 = (TL_iv.pageBlockBlockquote) r1;
        if (r12.caption != null) goto L14;
        r12.caption = new TL_iv.textEmpty();
        return;
    L14:
        return;
    L9:
        if ((r1 instanceof TL_iv.pageBlockPullquote) == false) goto L15;
        TL_iv.pageBlockPullquote r13 = (TL_iv.pageBlockPullquote) r1;
        if (r13.caption != null) goto L16;
        r13.caption = new TL_iv.textEmpty();
        return;
    L16:
        return;
    }

    private void ensureCollapseButton() {
        if (this.collapseButton != null) goto L6;
        this.collapseButton = new QuoteCollapseButton(this);
        return;
    }

    public static TL_iv.RichText extractCaption(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == false) goto L7;
        return ((TL_iv.pageBlockBlockquote) r1).caption;
    L7:
        if ((r1 instanceof TL_iv.pageBlockPullquote) == true) goto L9;
        return null;
    L9:
        return ((TL_iv.pageBlockPullquote) r1).caption;
    }

    private String getHint() {
        BlockRow r3 = this.currentRow;
        if (r3 != null) goto L5;
        return null;
    L5:
        TL_iv.PageBlock r1 = r3.block;
        if ((r1 instanceof TL_iv.pageBlockHeading1) == false) goto L14;
        if (r3.firstBlock == false) goto L10;
        int r32 = R.string.ArticleHintTitle;
    L12:
        return LocaleController.getString(r32);
    L10:
        r32 = R.string.ArticleHeading1;
        goto L12
    L14:
        if ((r1 instanceof TL_iv.pageBlockHeading2) == false) goto L18;
        return LocaleController.getString(R.string.ArticleHeading2);
    L18:
        if ((r1 instanceof TL_iv.pageBlockHeading3) == false) goto L22;
        return LocaleController.getString(R.string.ArticleHeading3);
    L22:
        if ((r1 instanceof TL_iv.pageBlockHeading4) == false) goto L26;
        return LocaleController.getString(R.string.ArticleHeading4);
    L26:
        if ((r1 instanceof TL_iv.pageBlockHeading5) == false) goto L30;
        return LocaleController.getString(R.string.ArticleHeading5);
    L30:
        if ((r1 instanceof TL_iv.pageBlockHeading6) == false) goto L34;
        return LocaleController.getString(R.string.ArticleHeading6);
    L34:
        if ((r1 instanceof TL_iv.pageBlockPreformatted) == false) goto L38;
        return LocaleController.getString(R.string.ArticleHintCode);
    L38:
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == false) goto L42;
        return LocaleController.getString(R.string.ArticleHintQuote);
    L42:
        if ((r1 instanceof TL_iv.pageBlockPullquote) == false) goto L46;
        return LocaleController.getString(R.string.ArticleHintQuote);
    L46:
        if (r3.singleParagraph == true) goto L48;
        return null;
    L48:
        return LocaleController.getString(R.string.ArticleHintText);
    }

    private boolean hasCollapseButton() {
        if (isBlockquote() == true) goto L5;
        return false;
    L5:
        Layout r2 = this.editText.getLayout();
        if (r2 != null) goto L8;
    L11:
        return false;
    L8:
        if (r2.getLineCount() <= QuoteSpan.COLLAPSE_LINES) goto L11;
        return true;
    }

    private static boolean insideEmpty(RichEditText r2, int r3, int r4, int r5, int r6) {
        if (r2.length() == 0) goto L5;
        return false;
    L5:
        if (r5 >= r3) goto L7;
    L13:
        return false;
    L7:
        if (r5 > (r3 + r2.getWidth())) goto L13;
        if (r6 < r4) goto L13;
        if (r6 > (r4 + r2.getHeight())) goto L13;
        return true;
    }

    private boolean isBlockquote() {
        BlockRow r0 = this.currentRow;
        if (r0 != null) goto L5;
        return false;
    L5:
        if ((r0.block instanceof TL_iv.pageBlockBlockquote) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isHighlightableCode() {
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L10;
        TL_iv.PageBlock r12 = r1.block;
        if ((r12 instanceof TL_iv.pageBlockPreformatted) == true) goto L7;
        return false;
    L7:
        if (TextUtils.isEmpty(((TL_iv.pageBlockPreformatted) r12).language) == true) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public static boolean isQuoteBlock(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockPullquote) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private /* synthetic */ void lambda$focusAuthorFromBody$7(float r4) {
        Layout r0 = this.authorEditText.getLayout();
        int r1 = this.authorEditText.length();
        if (r0 == null) goto L5;
        r1 = r0.getOffsetForHorizontal(0, r4);
    L5:
        RichEditText r3 = this.authorEditText;
        r3.setSelection(Math.max(0, Math.min(r1, r3.length())));
    }

    private /* synthetic */ void lambda$focusBodyFromAuthor$8(float r4) {
        Layout r0 = this.editText.getLayout();
        int r1 = this.editText.length();
        if (r0 == null) goto L5;
        r1 = r0.getOffsetForHorizontal(Math.max(0, r0.getLineCount() - 1), r4);
    L5:
        RichEditText r3 = this.editText;
        r3.setSelection(Math.max(0, Math.min(r1, r3.length())));
    }

    private /* synthetic */ void lambda$new$0(View r3) {
        BlockRow r32 = this.currentRow;
        if (r32 != null) goto L5;
        return;
    L5:
        if (r32.checkbox == false) goto L13;
        boolean r0 = !r32.checked;
        r32.checked = r0;
        this.checkBoxView.setChecked(r0, true);
        Delegate r33 = this.delegate;
        if (r33 == null) goto L12;
        BlockRow r2 = this.currentRow;
        r33.onCheckboxToggle(r2, r2.checked);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$new$1() {
        if (this.applyingCollapsedDecoration == false) goto L6;
        return;
    L6:
        if (this.currentRow == null) goto L14;
        updateListNumberStyle();
        applyStyledTextToBlock(this.currentRow.block, this.editText.getText());
        Delegate r0 = this.delegate;
        if (r0 == null) goto L12;
        r0.onSpansChanged(this.currentRow);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$new$2(View r2, boolean r3) {
        this.editText.setHint(getHint());
        if (r3 == true) goto L8;
        Delegate r22 = this.delegate;
        if (r22 == null) goto L9;
        r22.onSlashSuggest(this, null);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$3() {
        if (this.currentRow == null) goto L10;
        persistAuthor();
        Delegate r0 = this.delegate;
        if (r0 == null) goto L9;
        r0.onSpansChanged(this.currentRow);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$runHighlight$6(int r2, BlockRow r3, String r4, SpannableString r5) {
        if (r2 == this.highlightGeneration) goto L5;
        return;
    L5:
        if (this.currentRow != r3) goto L13;
        Editable r22 = this.editText.getText();
        if (TextUtils.equals(r4, r22) == false) goto L14;
        applyColorSpans(r22, r5);
        this.highlightedSnapshot = r4;
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$updateLanguageButton$4(View r2) {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onLanguageClick(this.currentRow, r2);
        return;
    }

    private static int matchCommand(String r2) {
        if (r2 != null) goto L5;
        return 0;
    L5:
        String r22 = r2.trim().toLowerCase();
        if (r22.equals("/img") == false) goto L8;
        return 4;
    L8:
        if (r22.equals("/pic") == false) goto L10;
        return 4;
    L10:
        if (r22.equals("/image") == false) goto L12;
        return 4;
    L12:
        if (r22.equals("/picture") == false) goto L14;
        return 4;
    L14:
        if (r22.equals("/photo") == false) goto L17;
        return 4;
    L17:
        if (r22.equals("/vid") == false) goto L19;
        return 5;
    L19:
        if (r22.equals("/video") == false) goto L22;
        return 5;
    L22:
        if (r22.equals("/audio") == false) goto L24;
        return 1;
    L24:
        if (r22.equals("/music") == false) goto L27;
        return 1;
    L27:
        if (r22.equals("/map") == false) goto L29;
        return 2;
    L29:
        if (r22.equals("/location") == false) goto L31;
        return 2;
    L31:
        if (r22.equals("/loc") == false) goto L34;
        return 2;
    L34:
        if (r22.equals("/latex") == false) goto L36;
        return 3;
    L36:
        if (r22.equals("/equation") == false) goto L38;
        return 3;
    L38:
        if (r22.equals("/math") == false) goto L41;
        return 3;
    L41:
        if (r22.equals("/toggle") == false) goto L43;
        return 6;
    L43:
        if (r22.equals("/details") == false) goto L46;
        return 6;
    L46:
        if (r22.equals("/button") == false) goto L49;
        return 7;
    L49:
        return 0;
    }

    private static Transform matchEnterTrigger(String r7, BlockRow r8) {
        if (r7 == null) goto L76;
        if (r8 == null) goto L76;
        String r72 = r7.trim();
        int r3 = 2;
        if (r72.length() != 3) goto L20;
        char r1 = r72.charAt(0);
        if (r1 == '-') goto L15;
        if (r1 == '*') goto L15;
        if (r1 != '_') goto L20;
    L15:
        if (r72.charAt(1) != r1) goto L20;
        if (r72.charAt(2) != r1) goto L20;
        return new Transform(new TL_iv.pageBlockDivider(), 0, 0);
    L20:
        String r73 = r72.toLowerCase();
        if (r73.length() != 3) goto L33;
        if (r73.charAt(0) != '/') goto L33;
        if (r73.charAt(1) != 'h') goto L33;
        char r12 = r73.charAt(2);
        if (r12 < '1') goto L33;
        if (r12 > '6') goto L33;
        return new Transform(newHeading(r12 - '0'), r8.level, r8.num);
    L33:
        if (r73.equals("/code") == true) goto L75;
        if (r73.equals("/pre") == true) goto L75;
        if (r73.equals("/preformatted") == true) goto L75;
        if (r73.equals("/footer") == false) goto L44;
        return new Transform(new TL_iv.pageBlockFooter(), 0, 0);
    L44:
        if (r73.equals("/quote") == true) goto L73;
        if (r73.equals("/blockquote") == true) goto L73;
        if (r73.equals("/pullquote") == false) goto L53;
        return new Transform(newPullquote(), 0, 0);
    L53:
        if (r73.equals("/table") == true) goto L59;
        if (r73.startsWith("/table ") == true) goto L59;
        return null;
    L59:
        if (r73.length() <= 7) goto L69;
        String r74 = r73.substring(7).trim();
        int r82 = r74.indexOf(120);
        if (r82 >= 0) goto L63;
        r82 = r74.indexOf(88);
    L63:
        if (r82 <= 0) goto L69;
        int r0 = Math.max(1, Math.min(20, Integer.parseInt(r74.substring(0, r82).trim())));     // Catch: NumberFormatException -> L68
        r3 = Math.max(1, Math.min(20, Integer.parseInt(r74.substring(r82 + 1).trim())));     // Catch: NumberFormatException -> L77
    L67:
        int r75 = r3;
        r3 = r0;
    L71:
        return new Transform(newEmptyTable(r3, r75), 0, 0);
    L68:
        r0 = 2;
    L69:
        r75 = 2;
    L73:
        return new Transform(newBlockquote(), 0, 0);
    L75:
        return new Transform(new TL_iv.pageBlockPreformatted(), 0, 0);
    L76:
        return null;
    }

    private static int matchMarkdownCommand(String r2, BlockRow r3) {
        if (r3 == null) goto L15;
        if (r2 == null) goto L15;
        if (r3.level != 0) goto L15;
        if ((r3.block instanceof TL_iv.pageBlockParagraph) == false) goto L15;
        if (r2.equals("> ") == false) goto L15;
        return 6;
    L15:
        return 0;
    }

    private static Transform matchMarkdownTrigger(String r14, BlockRow r15) {
        if (r15 != null) goto L5;
        return null;
    L5:
        if (r14 != null) goto L7;
        return null;
    L7:
        int r1 = r14.length();
        if (r1 < 2) goto L120;
        int r3 = r1 - 1;
        if (r14.charAt(r3) != ' ') goto L120;
        TL_iv.PageBlock r4 = r15.block;
        boolean r6 = r4 instanceof TL_iv.pageBlockParagraph;
        int r9 = 0;
        if ((r4 instanceof TL_iv.pageBlockHeading1) == false) goto L15;
    L26:
        boolean r42 = true;
    L28:
        if (r14.charAt(0) != '#') goto L44;
        if (r6 == true) goto L31;
        if (r42 == false) goto L44;
    L31:
        int r12 = 0;
    L32:
        if (r9 >= r3) goto L37;
        if (r14.charAt(r9) != '#') goto L36;
        r12 = r12 + 1;
        r9 = r9 + 1;
        goto L32
    L36:
        return null;
    L37:
        if (r12 >= 1) goto L39;
    L43:
        return null;
    L39:
        if (r12 > 6) goto L43;
        return new Transform(newHeading(r12), r15.level, r15.num);
    L44:
        if (r6 == true) goto L47;
        return null;
    L47:
        if (r15.level != 0) goto L62;
        if (r1 != 2) goto L62;
        char r32 = r14.charAt(0);
        if (r32 == '-') goto L59;
        if (r32 == '*') goto L59;
        if (r32 == '+') goto L59;
        if (r32 != '|') goto L62;
        return new Transform(newBlockquote(), 0, 0);
    L59:
        TL_iv.pageBlockParagraph r142 = new TL_iv.pageBlockParagraph();
        applyTextToBlock(r142, _UrlKt.FRAGMENT_ENCODE_SET);
        return new Transform(r142, 1, 0);
    L62:
        if (r15.level != 0) goto L71;
        if (r1 != 3) goto L71;
        if (r14.charAt(0) != '[') goto L71;
        if (r14.charAt(1) != ']') goto L71;
        return newChecklistItem(false);
    L71:
        if (r15.level != 0) goto L88;
        if (r1 != 4) goto L88;
        if (r14.charAt(0) != '[') goto L88;
        if (r14.charAt(2) != ']') goto L88;
        char r33 = r14.charAt(1);
        if (r33 != ' ') goto L82;
        return newChecklistItem(false);
    L82:
        if (r33 == 'x') goto L86;
        if (r33 != 'X') goto L88;
    L86:
        return newChecklistItem(true);
    L88:
        if (r15.level != 0) goto L99;
        if (r1 != 3) goto L99;
        if (Character.isDigit(r14.charAt(0)) == false) goto L99;
        char r34 = r14.charAt(1);
        if (r34 != '.') goto L95;
    L96:
        TL_iv.pageBlockParagraph r143 = new TL_iv.pageBlockParagraph();
        applyTextToBlock(r143, _UrlKt.FRAGMENT_ENCODE_SET);
        return new Transform(r143, 1, 1);
    L95:
        if (r34 == ')') goto L96;
    L99:
        if (r15.level != 0) goto L120;
        if (r1 != 4) goto L120;
        char r152 = r14.charAt(0);
        if (r152 == '-') goto L107;
        if (r152 == '*') goto L107;
        if (r152 == '_') goto L107;
    L113:
        if (r152 != '`') goto L120;
        if (r14.charAt(1) != '`') goto L120;
        if (r14.charAt(2) != '`') goto L120;
        return new Transform(new TL_iv.pageBlockPreformatted(), 0, 0);
    L107:
        if (r14.charAt(1) != r152) goto L113;
        if (r14.charAt(2) != r152) goto L113;
        return new Transform(new TL_iv.pageBlockDivider(), 0, 0);
    L15:
        if ((r4 instanceof TL_iv.pageBlockHeading2) == true) goto L26;
        if ((r4 instanceof TL_iv.pageBlockHeading3) == true) goto L26;
        if ((r4 instanceof TL_iv.pageBlockHeading4) == true) goto L26;
        if ((r4 instanceof TL_iv.pageBlockHeading5) == true) goto L26;
        if ((r4 instanceof TL_iv.pageBlockHeading6) == true) goto L26;
        r42 = false;
    L120:
        return null;
    }

    public static TL_iv.pageBlockBlockquote newBlockquote() {
        TL_iv.pageBlockBlockquote r0 = new TL_iv.pageBlockBlockquote();
        r0.caption = new TL_iv.textEmpty();
        return r0;
    }

    private static Transform newChecklistItem(boolean r6) {
        TL_iv.pageBlockParagraph r1 = new TL_iv.pageBlockParagraph();
        applyTextToBlock(r1, _UrlKt.FRAGMENT_ENCODE_SET);
        return new Transform(r1, 1, 0, true, r6);
    }

    public static TL_iv.pageBlockTable newEmptyTable(int r7, int r8) {
        TL_iv.pageBlockTable r0 = new TL_iv.pageBlockTable();
        r0.bordered = true;
        r0.striped = false;
        r0.title = new TL_iv.textEmpty();
        r0.rows = new ArrayList();
        int r2 = 0;
    L3:
        if (r2 >= r7) goto L8;
        TL_iv.pageTableRow r3 = new TL_iv.pageTableRow();
        r3.cells = new ArrayList();
        int r4 = 0;
    L5:
        if (r4 >= r8) goto L7;
        r3.cells.add(TableModel.newEmptyCell());
        r4 = r4 + 1;
        goto L5
    L7:
        r0.rows.add(r3);
        r2 = r2 + 1;
        goto L3
    L8:
        return r0;
    }

    private static TL_iv.PageBlock newHeading(int r0) {
        switch(r0) {
            case 1: goto L16;
            case 2: goto L14;
            case 3: goto L12;
            case 4: goto L10;
            case 5: goto L8;
            case 6: goto L6;
            default: goto L3;
        };
    L3:
        return null;
    L6:
        return new TL_iv.pageBlockHeading6();
    L8:
        return new TL_iv.pageBlockHeading5();
    L10:
        return new TL_iv.pageBlockHeading4();
    L12:
        return new TL_iv.pageBlockHeading3();
    L14:
        return new TL_iv.pageBlockHeading2();
    L16:
        return new TL_iv.pageBlockHeading1();
    }

    public static TL_iv.pageBlockPullquote newPullquote() {
        TL_iv.pageBlockPullquote r0 = new TL_iv.pageBlockPullquote();
        r0.caption = new TL_iv.textEmpty();
        return r0;
    }

    private static boolean pressOnLayout(RichEditText r3, int r4, int r5, int r6, int r7) {
        Layout r0 = r3.getLayout();
        if (r0 != null) goto L5;
    L23:
        return false;
    L5:
        if (r3.length() == 0) goto L23;
        int r62 = r6 - (r4 + r3.getPaddingLeft());
        int r72 = r7 - (r5 + r3.getPaddingTop());
        if (r72 < 0) goto L23;
        if (r72 >= r0.getHeight()) goto L23;
        int r42 = r0.getLineForVertical(r72);
        if (r42 < 0) goto L23;
        if (r42 >= r0.getLineCount()) goto L23;
        int r52 = AndroidUtilities.dp(24.0f);
        int r32 = Math.max(0, (r3.getWidth() - r3.getPaddingLeft()) - r3.getPaddingRight());
        float r53 = r52;
        float r73 = Math.max(0.0f, r0.getLineLeft(r42) - r53);
        float r33 = Math.min(r32, r0.getLineRight(r42) + r53);
        float r43 = r62;
        if (r43 < r73) goto L23;
        if (r43 > r33) goto L23;
        return true;
    }

    public static String readAuthorPlain(TL_iv.PageBlock r0) {
        return RichTextStyle.plainOf(extractCaption(r0));
    }

    public static CharSequence readAuthorStyled(TL_iv.PageBlock r0) {
        return RichTextStyle.toSpannable(extractCaption(r0));
    }

    public static String readPlainText(TL_iv.PageBlock r0) {
        if (r0 != null) goto L6;
        return null;
    L6:
        return RichTextStyle.plainOf(r0.text);
    }

    public static CharSequence readStyledText(TL_iv.PageBlock r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return RichTextStyle.toSpannable(r1.text, r1);
    }

    private void resetCollapsedIfTooShort() {
        if (isBlockquote() == true) goto L5;
        return;
    L5:
        if (hasCollapseButton() == true) goto L9;
        ((TL_iv.pageBlockBlockquote) this.currentRow.block).collapsed = false;
        return;
    }

    private void runHighlight() {
        this.highlightScheduled = null;
        if (isHighlightableCode() == false) goto L10;
        String r0 = this.editText.getText().toString();
        if (r0.equals(this.highlightedSnapshot) == false) goto L8;
        return;
    L8:
        BlockRow r1 = this.currentRow;
        String r2 = ((TL_iv.pageBlockPreformatted) r1.block).language;
        int r3 = this.highlightGeneration + 1;
        this.highlightGeneration = r3;
        CodeHighlighting.highlightEditable(r0, r2, new RichTextCell$$ExternalSyntheticLambda7(this, r3, r1, r0));
        return;
    }

    private void scheduleHighlight() {
        Runnable r0 = this.highlightScheduled;
        if (r0 == null) goto L6;
        removeCallbacks(r0);
        this.highlightScheduled = null;
    L6:
        if (isHighlightableCode() == true) goto L9;
        this.highlightGeneration++;
        clearHighlight();
        this.highlightedSnapshot = null;
        return;
    L9:
        RichTextCell$$ExternalSyntheticLambda6 r02 = new RichTextCell$$ExternalSyntheticLambda6(this);
        this.highlightScheduled = r02;
        postDelayed(r02, 100);
    }

    public static void setCaption(TL_iv.PageBlock r1, TL_iv.RichText r2) {
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == false) goto L7;
        ((TL_iv.pageBlockBlockquote) r1).caption = r2;
        return;
    L7:
        if ((r1 instanceof TL_iv.pageBlockPullquote) == false) goto L10;
        ((TL_iv.pageBlockPullquote) r1).caption = r2;
        return;
    }

    private static void setRichText(TL_iv.PageBlock r0, TL_iv.RichText r1) {
        r0.text = r1;
    }

    private void sizeHeaderEmojiToText(CharSequence r9) {
        BlockRow r0 = this.currentRow;
        if (r0 != null) goto L5;
        return;
    L5:
        if (RichEditorListView.isHeading(r0.block) == true) goto L7;
        return;
    L7:
        if ((r9 instanceof Spanned) == false) goto L19;
        Paint.FontMetricsInt r02 = this.editText.getPaint().getFontMetricsInt();
        int r8 = Math.max(1, Math.round((this.editText.getTextSize() * 0.85f) / 1.2f));
        Spanned r2 = (Spanned) r9;
        int r5 = 0;
        Emoji.EmojiSpan[] r3 = (Emoji.EmojiSpan[]) r2.getSpans(0, r9.length(), Emoji.EmojiSpan.class);
        int r4 = r3.length;
        int r6 = 0;
    L10:
        if (r6 >= r4) goto L12;
        r3[r6].scale = 0.85f;
        r6 = r6 + 1;
        goto L10
    L12:
        AnimatedEmojiSpan[] r92 = (AnimatedEmojiSpan[]) r2.getSpans(0, r9.length(), AnimatedEmojiSpan.class);
        int r1 = r92.length;
    L13:
        if (r5 >= r1) goto L20;
        AnimatedEmojiSpan r22 = r92[r5];
        r22.replaceFontMetrics(r02);
        r22.setSize(r8);
        r5 = r5 + 1;
        goto L13
    L20:
        return;
    }

    private static String slashQuery(String r4) {
        if (r4 != null) goto L5;
    L21:
        return null;
    L5:
        if (r4.isEmpty() == true) goto L21;
        int r1 = 0;
        if (r4.charAt(0) != '/') goto L21;
    L10:
        if (r1 >= r4.length()) goto L20;
        char r2 = r4.charAt(r1);
        if (r2 == ' ') goto L19;
        if (r2 == '\n') goto L19;
        if (r2 == '\t') goto L19;
        r1 = r1 + 1;
    L19:
        return null;
    L20:
        return r4;
    }

    private void syncLiveListVerticalPadding() {
        BlockRow r0 = this.currentRow;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.level <= 0) goto L22;
        Delegate r1 = this.delegate;
        if (r1 == null) goto L24;
        int r02 = r1.getListPaddingTop(r0);
        int r12 = this.delegate.getListPaddingBottom(this.currentRow);
        BlockRow r2 = this.currentRow;
        if (r2.quoteFirst == false) goto L12;
        r02 = RichBlockChrome.quoteTopPad(r2);
    L12:
        BlockRow r22 = this.currentRow;
        if (r22.quoteLast == false) goto L16;
        r12 = RichBlockChrome.quoteBottomPad(r22);
    L16:
        if (r02 == getPaddingTop()) goto L18;
    L19:
        setPadding(getPaddingLeft(), r02, getPaddingRight(), r12);
        return;
    L18:
        if (r12 != getPaddingBottom()) goto L19;
        return;
    L24:
        return;
    }

    private void toggleCollapsed() {
        if (isBlockquote() == false) goto L10;
        ((TL_iv.pageBlockBlockquote) this.currentRow.block).collapsed = !r0.collapsed;
        updateCollapsedDecoration();
        invalidate();
        Delegate r0 = this.delegate;
        if (r0 == null) goto L9;
        r0.onTextChanged(this.currentRow);
        return;
    L9:
        return;
    }

    private void updateAuthorVisibility() {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L15;
        if (isQuoteBlock(r0.block) == false) goto L15;
        if (this.editText.length() > 0) goto L11;
        if (this.authorEditText.length() <= 0) goto L15;
    L11:
        if (this.authorEditText.getVisibility() == 0) goto L21;
        this.authorEditText.setVisibility(0);
        requestLayout();
        return;
    L21:
        return;
    L15:
        if (this.authorEditText.getVisibility() != 8) goto L17;
        return;
    L17:
        if (this.authorEditText.isFocused() == false) goto L19;
        this.editText.requestFocus();
    L19:
        this.authorEditText.setVisibility(8);
        requestLayout();
    }

    private void updateCollapsedDecoration() {
        if (this.editText.getText() == null) goto L39;
        Editable r0 = this.editText.getText();
        int r2 = -1;
        if (isBlockquote() == true) goto L8;
    L15:
        int r3 = -1;
    L18:
        if (r2 == this.collapsedPartStart) goto L20;
    L22:
        this.applyingCollapsedDecoration = true;
        CharacterStyle r4 = this.collapsedPart;     // Catch: Throwable -> L26
        if (r4 == null) goto L28;
        r0.removeSpan(r4);     // Catch: Throwable -> L26
    L28:
        if (r2 >= 0) goto L30;
    L33:
        this.applyingCollapsedDecoration = false;
        this.collapsedPartStart = r2;
        this.collapsedPartEnd = r3;
        return;
    L30:
        if (this.collapsedPart != null) goto L32;
        this.collapsedPart = new CollapsedTextPart(this, null);     // Catch: Throwable -> L26
    L32:
        r0.setSpan(this.collapsedPart, r2, r3, 33);     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        this.applyingCollapsedDecoration = false;
        throw th;
    L20:
        if (r3 != this.collapsedPartEnd) goto L22;
        return;
    L8:
        if (((TL_iv.pageBlockBlockquote) this.currentRow.block).collapsed == false) goto L15;
        Layout r1 = this.editText.getLayout();
        if (r1 == null) goto L15;
        int r32 = r1.getLineCount();
        int r42 = QuoteSpan.COLLAPSE_LINES;
        if (r32 <= r42) goto L15;
        int r12 = r1.getLineStart(r42);
        r3 = r0.length();
        if (r12 >= r3) goto L15;
        r2 = r12;
        goto L18
    }

    private void updateLanguageButton(TL_iv.PageBlock r10, boolean r11) {
        boolean r0 = r10 instanceof TL_iv.pageBlockPreformatted;
        LinearLayout r1 = this.languageButton;
        if (r0 == true) goto L7;
        if (r1 == null) goto L23;
        r1.setVisibility(8);
        return;
    L23:
        return;
    L7:
        if (r1 == null) goto L11;
        if (r11 == false) goto L11;
        AndroidUtilities.removeFromParent(r1);
        this.languageButton = null;
    L11:
        if (this.languageButton != null) goto L13;
        LinearLayout r112 = new LinearLayout(getContext());
        this.languageButton = r112;
        r112.setOrientation(0);
        this.languageButton.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f)));
        this.languageButton.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        addView(this.languageButton, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, -15.0f, -5.0f, 0.0f));
        TextView r113 = new TextView(getContext());
        this.languageButtonText = r113;
        r113.setTextSize(1, 12.0f);
        this.languageButtonText.setGravity(17);
        this.languageButton.addView(this.languageButtonText, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 0, 0));
        ImageView r114 = new ImageView(getContext());
        this.languageButtonIcon = r114;
        r114.setImageResource(R.drawable.arrows_select);
        this.languageButton.addView(this.languageButtonIcon, LayoutHelper.createLinear(16, 16, 16, 0.0f, 0.66f, 0.0f, 0.0f));
        CodeHighlighting.prepare();
        this.languageButton.setOnClickListener(new RichTextCell$$ExternalSyntheticLambda4(this));
        this.languageButton.setOnLongClickListener(new RichTextCell$$ExternalSyntheticLambda5());
    L13:
        String r102 = ((TL_iv.pageBlockPreformatted) r10).language;
        int r115 = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider);
        if (TextUtils.isEmpty(r102) == false) goto L16;
        float r12 = 0.5f;
    L17:
        int r116 = Theme.multAlpha(r115, r12);
        this.languageButtonIcon.setColorFilter(new PorterDuffColorFilter(r116, PorterDuff.Mode.SRC_IN));
        this.languageButtonText.setTextColor(r116);
        boolean r117 = TextUtils.isEmpty(r102);
        TextView r13 = this.languageButtonText;
        if (r117 == false) goto L20;
        r13.setText(LocaleController.getString(R.string.ArticleHintLanguage));
    L21:
        this.languageButton.setVisibility(0);
        return;
    L20:
        r13.setText(MessageObject.TextLayoutBlock.capitalizeLanguage(r102));
        goto L21
    L16:
        r12 = 0.75f;
        goto L17
    }

    private void updateListNumberStyle() {
        BlockRow r0 = this.currentRow;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L11:
        TextView r02 = this.bullet;
        if (r1 == false) goto L14;
        Typeface r12 = AndroidUtilities.bold();
    L15:
        r02.setTypeface(r12);
        BlockRow r03 = this.currentRow;
        if (r03 != null) goto L18;
        return;
    L18:
        if (r03.num <= 0) goto L22;
        applyListDecoration(r03);
        return;
    L22:
        return;
    L14:
        r12 = null;
        goto L15
    L5:
        if (r0.num <= 0) goto L11;
        if (this.editText.length() <= 0) goto L11;
        if ((this.editText.getCurrentStyle(0, 1) & 1) == 0) goto L11;
        r1 = true;
        goto L11
    }

    public void bind(BlockRow r3, Delegate r4, boolean r5) {
        this.currentRow = r3;
        this.delegate = r4;
        this.forceHint = r5;
        this.collapsedPartEnd = -1;
        this.collapsedPartStart = -1;
        this.editText.setBlock(r3.block);
        applyStyle(r3.block);
        applyQuoteInset(r3);
        applyListDecoration(r3);
        updateLanguageButton(r3.block, false);
        String r42 = readPlainText(r3.block);
        if (String.valueOf(this.editText.getText()).equals(r42) == true) goto L12;
        CharSequence r43 = readStyledText(r3.block);
        if (RichEditorListView.isHeading(r3.block) == false) goto L7;
        SpannableString r0 = new SpannableString(r43);
        RichTextStyle.setStyle(r0, 0, r0.length(), 1, false);
        RichTextStyle.setStyle(r0, 0, r0.length(), 2, false);
        r43 = r0;
    L7:
        Paint.FontMetricsInt r02 = this.editText.getPaint().getFontMetricsInt();
        if (RichEditorListView.isHeading(r3.block) == false) goto L10;
        float r1 = 0.85f;
    L11:
        CharSequence r44 = Emoji.replaceEmoji(r43, r02, false, r1);
        this.editText.setTextSilently(r44);
        sizeHeaderEmojiToText(this.editText.getText());
        this.editText.invalidateEffects();
        this.highlightedSnapshot = null;
        goto L12
    L10:
        r1 = 1.0f;
    L12:
        sizeHeaderEmojiToText(this.editText.getText());
        updateListNumberStyle();
        bindAuthor(r3.block);
        scheduleHighlight();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r21) {
        BlockRow r1 = this.currentRow;
        float r9 = 0.0f;
        int r10 = 0;
        if (r1 == null) goto L28;
        if ((r1.block instanceof TL_iv.pageBlockPreformatted) == false) goto L28;
        this.bgPaint.setColor(Theme.getColor(Theme.key_chat_inArticleCodeBackground, this.resourcesProvider));
        int r12 = RichBlockChrome.quoteInset(this.currentRow);
        int r3 = RichBlockChrome.quoteInsetEnd(this.currentRow);
        int r4 = getWidth();
        if (r12 > 0) goto L11;
        if (r3 > 0) goto L11;
        int r2 = 0;
    L20:
        if (r2 <= 0) goto L22;
    L25:
        int r13 = AndroidUtilities.dp(8.0f);
    L26:
        float r6 = r13;
        r21.drawRoundRect(r2, AndroidUtilities.dp(7.0f), r4, getHeight() - AndroidUtilities.dp(7.0f), r6, r6, this.bgPaint);
        Canvas r22 = r21;
    L74:
        if (this.showCommandBackground == false) goto L86;
        float r14 = getWidth();
        float r32 = getHeight();
        Layout r42 = this.editText.getLayout();
        float r5 = 0.0f;
        if (r42 == null) goto L82;
        int r62 = 0;
    L79:
        if (r62 >= r42.getLineCount()) goto L82;
        r32 = Math.min(r32, (getPaddingTop() + this.editText.getPaddingTop()) + r42.getLineTop(r62));
        r14 = Math.min(r14, ((this.row.getLeft() + this.editText.getLeft()) + this.editText.getPaddingLeft()) + r42.getLineLeft(r62));
        r9 = Math.max(r9, ((this.row.getLeft() + this.editText.getLeft()) + this.editText.getPaddingLeft()) + r42.getLineRight(r62));
        r5 = Math.max(r32, (getPaddingTop() + this.editText.getPaddingTop()) + r42.getLineBottom(r62));
        r62 = r62 + 1;
    L82:
        if (r14 >= r9) goto L86;
        if (r32 >= r5) goto L86;
        float r15 = r14 - AndroidUtilities.dp(4.0f);
        this.bgPaint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), 0.05f));
        Canvas r43 = r22;
        r43.drawRoundRect(r15, r32 - AndroidUtilities.dp(2.0f), r9 + AndroidUtilities.dp(4.0f), r5 + AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.bgPaint);
        r22 = r43;
    L86:
        Delegate r16 = this.delegate;
        if (r16 == null) goto L89;
        TextSelectionHelper.ArticleTextSelectionHelper r17 = r16.getSelectionHelper();
    L90:
        if (r17 == null) goto L95;
        this.tmpBlocks.clear();
        fillTextLayoutBlocks(this.tmpBlocks);
    L93:
        if (r10 >= this.tmpBlocks.size()) goto L95;
        TextSelectionHelper.TextLayoutBlock r33 = this.tmpBlocks.get(r10);
        r22.save();
        r22.translate(r33.getX(), r33.getY());
        r17.draw(r22, this, r10);
        r22.restore();
        r10 = r10 + 1;
    L95:
        super.dispatchDraw(r21);
        drawCollapseButton(r21);
        return;
    L89:
        r17 = null;
        goto L90
    L22:
        if (r4 < getWidth()) goto L25;
        r13 = 0;
    L11:
        int r44 = AndroidUtilities.dp(16.0f) + r12;
        int r18 = AndroidUtilities.dp(16.0f) + r3;
        if (RichBlockChrome.rtl() == false) goto L14;
        r2 = r18;
    L15:
        int r34 = getWidth();
        if (RichBlockChrome.rtl() == true) goto L19;
        r44 = r18;
    L19:
        r4 = r34 - r44;
        goto L20
    L14:
        r2 = r44;
    L28:
        if (r1 != null) goto L30;
    L35:
        r22 = r21;
        if (r1 == null) goto L74;
        if ((r1.block instanceof TL_iv.pageBlockPullquote) == false) goto L74;
        if (this.quoteLine != null) goto L43;
        ReplyMessageLine r142 = new ReplyMessageLine(this);
        this.quoteLine = r142;
        r142.check(null, null, null, this.resourcesProvider, 1);
        RichBlockChrome.applyEditorQuoteColor(this.quoteLine, this.resourcesProvider);
    L43:
        if (this.quoteIcon != null) goto L45;
        Drawable r19 = getContext().getResources().getDrawable(R.drawable.mini_quote).mutate();
        this.quoteIcon = r19;
        r19.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
    L45:
        Layout r110 = this.editText.getLayout();
        float r35 = getWidth();
        if (r110 == null) goto L54;
        if (TextUtils.isEmpty(r110.getText()) == true) goto L54;
        float r52 = 0.0f;
        int r45 = 0;
    L51:
        if (r45 >= r110.getLineCount()) goto L58;
        r35 = Math.min(r35, ((this.row.getLeft() + this.editText.getLeft()) + this.editText.getPaddingLeft()) + r110.getLineLeft(r45));
        r52 = Math.max(r52, ((this.row.getLeft() + this.editText.getLeft()) + this.editText.getPaddingLeft()) + r110.getLineRight(r45));
        r45 = r45 + 1;
    L58:
        if (this.authorEditText.getVisibility() != 0) goto L71;
        Layout r111 = this.authorEditText.getLayout();
        if (r111 == null) goto L68;
        if (TextUtils.isEmpty(r111.getText()) == true) goto L68;
        int r46 = 0;
    L65:
        if (r46 >= r111.getLineCount()) goto L71;
        r35 = Math.min(r35, (this.authorEditText.getLeft() + this.authorEditText.getPaddingLeft()) + r111.getLineLeft(r46));
        r52 = Math.max(r52, (this.authorEditText.getLeft() + this.authorEditText.getPaddingLeft()) + r111.getLineRight(r46));
        r46 = r46 + 1;
    L68:
        if (this.authorEditText.getHint() == null) goto L71;
        float r112 = this.authorEditText.getPaint().measureText(this.authorEditText.getHint().toString());
        r35 = Math.min(r35, ((getWidth() - r112) / 2.0f) + AndroidUtilities.dp(2.0f));
        r52 = Math.max(r52, ((getWidth() + r112) / 2.0f) + AndroidUtilities.dp(2.0f));
    L71:
        if (r35 >= r52) goto L74;
        float r143 = r35 - AndroidUtilities.dp(30.0f);
        float r152 = r52 + AndroidUtilities.dp(30.0f);
        float r47 = (float) Math.floor(SharedConfig.bubbleRadius / 2.0f);
        int r113 = AndroidUtilities.dp(8.0f);
        int r36 = getHeight() - AndroidUtilities.dp(8.0f);
        RectF r53 = AndroidUtilities.rectTmp;
        r53.set(r143, r113, r152, r36);
        this.quoteLine.drawBackground(r22, r53, r47, r47, r47, 1.0f);
        r22.save();
        int r37 = (int) r143;
        this.quoteIcon.setBounds(AndroidUtilities.dp(8.0f) + r37, r113 + AndroidUtilities.dp(7.0f), (r37 + AndroidUtilities.dp(8.0f)) + this.quoteIcon.getIntrinsicWidth(), (r113 + AndroidUtilities.dp(7.0f)) + this.quoteIcon.getIntrinsicHeight());
        r22.scale(-1.0f, -1.0f, this.quoteIcon.getBounds().centerX(), this.quoteIcon.getBounds().centerY());
        this.quoteIcon.draw(r22);
        r22.restore();
        r22.save();
        int r38 = (int) r152;
        this.quoteIcon.setBounds((r38 - AndroidUtilities.dp(8.0f)) - this.quoteIcon.getIntrinsicWidth(), (r36 - AndroidUtilities.dp(7.0f)) - this.quoteIcon.getIntrinsicHeight(), r38 - AndroidUtilities.dp(8.0f), r36 - AndroidUtilities.dp(7.0f));
        r22.scale(1.0f, -1.0f, this.quoteIcon.getBounds().centerX(), this.quoteIcon.getBounds().centerY());
        this.quoteIcon.draw(r22);
        r22.restore();
    L54:
        if (this.editText.getHint() == null) goto L56;
        float r114 = this.editText.getPaint().measureText(this.editText.getHint().toString());
        r35 = Math.min(r35, ((getWidth() - r114) / 2.0f) + AndroidUtilities.dp(2.0f));
        r52 = Math.max(0.0f, ((getWidth() + r114) / 2.0f) + AndroidUtilities.dp(2.0f));
        goto L58
    L56:
        r52 = 0.0f;
        goto L58
    L30:
        if ((r1.block instanceof TL_iv.pageBlockBlockquote) == false) goto L35;
        if (this.quoteLine != null) goto L34;
        ReplyMessageLine r39 = new ReplyMessageLine(this);
        this.quoteLine = r39;
        r39.check(null, null, null, this.resourcesProvider, 1);
        RichBlockChrome.applyEditorQuoteColor(this.quoteLine, this.resourcesProvider);
    L34:
        RectF r310 = AndroidUtilities.rectTmp;
        r310.set(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f), getWidth() - AndroidUtilities.dp(16.0f), getHeight() - AndroidUtilities.dp(8.0f));
        float r48 = (float) Math.floor(SharedConfig.bubbleRadius / 3.0f);
        r22 = r21;
        this.quoteLine.drawBackground(r22, r310, r48, r48, r48, 1.0f);
        this.quoteLine.drawLine(r22, r310, 1.0f);
        goto L74
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r6) {
        if (hasCollapseButton() == false) goto L33;
        if (this.collapseButton == null) goto L33;
        boolean r0 = this.collapseButtonBounds.contains(r6.getX(), r6.getY());
        int r1 = r6.getActionMasked();
        if (r1 != 0) goto L9;
        if (r0 == false) goto L33;
        this.collapseButtonPressed = true;
        this.collapseButton.setPressed(true);
        return true;
    L9:
        if (r1 == 1) goto L24;
        if (r1 == 2) goto L20;
        if (r1 != 3) goto L33;
        if (this.collapseButtonPressed == false) goto L33;
        this.collapseButtonPressed = false;
        this.collapseButton.setPressed(false);
        return true;
    L20:
        if (this.collapseButtonPressed == false) goto L33;
        this.collapseButton.setPressed(r0);
        return true;
    L24:
        if (this.collapseButtonPressed == false) goto L33;
        this.collapseButtonPressed = false;
        this.collapseButton.setPressed(false);
        if (r0 == false) goto L28;
        toggleCollapsed();
    L28:
        return true;
    L33:
        return super.dispatchTouchEvent(r6);
    }

    @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
    public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r5) {
        Layout r0 = this.editText.getLayout();
        if (r0 == null) goto L6;
        r5.add(new AnonymousClass4(this, r0, (this.row.getLeft() + this.editText.getLeft()) + this.editText.getPaddingLeft(), (this.row.getTop() + this.editText.getTop()) + this.editText.getPaddingTop()));
    L6:
        if (this.authorEditText.getVisibility() != 0) goto L11;
        Layout r02 = this.authorEditText.getLayout();
        if (r02 == null) goto L12;
        r5.add(new AnonymousClass5(this, r02, this.authorEditText.getLeft() + this.authorEditText.getPaddingLeft(), this.authorEditText.getTop() + this.authorEditText.getPaddingTop()));
        return;
    L12:
        return;
    }

    public void finishActionModes() {
        this.editText.finishActionMode();
        this.authorEditText.finishActionMode();
    }

    public void focusAuthorEnd() {
        ensureAuthorVisibleAndFocus();
    }

    public void focusAuthorFromBody() {
        float r0 = caretX(this.editText);
        if (this.authorEditText.getVisibility() == 0) goto L5;
        this.authorEditText.setVisibility(0);
        requestLayout();
    L5:
        this.authorEditText.requestEditFocus();
        this.authorEditText.post(new RichTextCell$$ExternalSyntheticLambda9(this, r0));
    }

    public void focusBodyFromAuthor() {
        float r0 = caretX(this.authorEditText);
        this.editText.requestEditFocus();
        this.editText.post(new RichTextCell$$ExternalSyntheticLambda8(this, r0));
    }

    public RichEditText getAuthorEditText() {
        return this.authorEditText;
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

    public FloatingToolbar.StyleDelegate getStyleDelegate() {
        return this.editText;
    }

    public void hideActionModes() {
        this.editText.hideActionMode();
        this.authorEditText.hideActionMode();
    }

    public boolean isAuthorFocused() {
        return this.authorEditText.isFocused();
    }

    public boolean isAuthorVisible() {
        if (this.authorEditText.getVisibility() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isPressOnEmptyEditText(int r5, int r6) {
        if (insideEmpty(this.editText, this.row.getLeft() + this.editText.getLeft(), this.row.getTop() + this.editText.getTop(), r5, r6) == false) goto L6;
        return true;
    L6:
        if (this.authorEditText.getVisibility() != 0) goto L10;
        RichEditText r0 = this.authorEditText;
        if (insideEmpty(r0, r0.getLeft(), this.authorEditText.getTop(), r5, r6) == false) goto L12;
        return true;
    L12:
        return false;
    L10:
        return false;
    }

    public boolean isPressOnText(int r5, int r6) {
        if (pressOnLayout(this.editText, this.row.getLeft() + this.editText.getLeft(), this.row.getTop() + this.editText.getTop(), r5, r6) == false) goto L6;
        return true;
    L6:
        if (this.authorEditText.getVisibility() != 0) goto L10;
        RichEditText r0 = this.authorEditText;
        if (pressOnLayout(r0, r0.getLeft(), this.authorEditText.getTop(), r5, r6) == false) goto L12;
        return true;
    L12:
        return false;
    L10:
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        updateCollapsedDecoration();
        if (this.authorEditText.getVisibility() != 8) goto L6;
        super.onLayout(r3, r4, r5, r6, r7);
        return;
    L6:
        int r32 = getPaddingLeft();
        int r42 = getPaddingTop();
        LinearLayout r52 = this.row;
        r52.layout(r32, r42, r52.getMeasuredWidth() + r32, this.row.getMeasuredHeight() + r42);
        int r43 = r42 + this.row.getMeasuredHeight();
        RichEditText r53 = this.authorEditText;
        r53.layout(r32, r43, r53.getMeasuredWidth() + r32, this.authorEditText.getMeasuredHeight() + r43);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        syncLiveListVerticalPadding();
        int r62 = View.MeasureSpec.getSize(r6);
        if (this.authorEditText.getVisibility() != 8) goto L6;
        this.collapseExtraHeight = 0;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30), r7);
        return;
    L6:
        int r72 = Math.max(0, (r62 - getPaddingLeft()) - getPaddingRight());
        this.row.measure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.authorEditText.measure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        int r73 = ((getPaddingTop() + this.row.getMeasuredHeight()) + this.authorEditText.getMeasuredHeight()) + getPaddingBottom();
        int r0 = collapseButtonExtraHeight(r62, r73);
        this.collapseExtraHeight = r0;
        setMeasuredDimension(r62, r73 + r0);
    }

    public void persistAuthor() {
        BlockRow r0 = this.currentRow;
        if (r0 != null) goto L5;
        return;
    L5:
        if (isQuoteBlock(r0.block) == false) goto L10;
        setCaption(this.currentRow.block, RichTextStyle.fromSpannable(this.authorEditText.getText()));
        return;
    }

    public void persistStyle() {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L6;
        applyStyledTextToBlock(r0.block, this.editText.getText());
        return;
    }

    public void rebindInPlace() {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L9;
        Delegate r1 = this.delegate;
        if (r1 == null) goto L10;
        bind(r0, r1, this.forceHint);
        return;
    L10:
        return;
    }

    public void refreshListVerticalPadding() {
        syncLiveListVerticalPadding();
    }

    public void requestEditFocus() {
        this.editText.requestEditFocus();
    }

    public void selectCommand(RichCommand r10) {
        if (this.delegate != null) goto L5;
        return;
    L5:
        if (this.currentRow == null) goto L30;
        if (r10 != null) goto L8;
        return;
    L8:
        if (r10.commands.isEmpty() == true) goto L32;
        Iterator<String> r102 = r10.commands.iterator();
    L12:
        if (r102.hasNext() == false) goto L33;
        String r0 = r102.next();
        int r1 = matchCommand(r0);
        if (r1 != 0) goto L15;
        Transform r12 = matchEnterTrigger(r0, this.currentRow);
        if (r12 != null) goto L20;
        r12 = matchMarkdownTrigger(r0 + " ", this.currentRow);
    L20:
        if (r12 == null) goto L12;
        this.delegate.onTransform(this.currentRow, r12.block, r12.level, r12.num, r12.checkbox, r12.checked);
        return;
    L15:
        this.delegate.onCommand(this.currentRow, r1);
        return;
    L33:
        return;
    L32:
        return;
    }

    public void setLocked(boolean r2) {
        this.editText.setLocked(r2);
        this.authorEditText.setLocked(r2);
    }

    public void setShowCommandBackground(boolean r2) {
        if (this.showCommandBackground != r2) goto L5;
        return;
    L5:
        this.showCommandBackground = r2;
        invalidate();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.editText.updateColors();
        RichEditText r0 = this.authorEditText;
        if (r0 == null) goto L5;
        r0.updateColors();
    L5:
        this.bullet.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        Drawable r02 = this.quoteIcon;
        if (r02 == null) goto L8;
        r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
    L8:
        ReplyMessageLine r03 = this.quoteLine;
        if (r03 == null) goto L12;
        RichBlockChrome.applyEditorQuoteColor(r03, this.resourcesProvider);
        return;
    }

    public void updateLanguage() {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L6;
        updateLanguageButton(r0.block, true);
        this.highlightedSnapshot = null;
        scheduleHighlight();
        return;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == true) goto L11;
        QuoteCollapseButton r1 = this.collapseButton;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.verifyDrawable(r2) == true) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }
}
