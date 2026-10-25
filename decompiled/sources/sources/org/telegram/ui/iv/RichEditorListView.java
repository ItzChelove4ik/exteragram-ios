package org.telegram.ui.iv;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.webkit.MimeTypeMap;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AppGlobalConfig;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.FloatingToolbar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ReplyMessageLine;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.iv.RichAudioCell;
import org.telegram.ui.iv.RichButtonRowCell;
import org.telegram.ui.iv.RichDetailsCell;
import org.telegram.ui.iv.RichDetailsEndCell;
import org.telegram.ui.iv.RichDividerCell;
import org.telegram.ui.iv.RichDocumentCell;
import org.telegram.ui.iv.RichEditor;
import org.telegram.ui.iv.RichEditorHistory;
import org.telegram.ui.iv.RichMapCell;
import org.telegram.ui.iv.RichMathCell;
import org.telegram.ui.iv.RichMediaCell;
import org.telegram.ui.iv.RichMediaConverter;
import org.telegram.ui.iv.RichMediaUploader;
import org.telegram.ui.iv.RichQuoteAuthorCell;
import org.telegram.ui.iv.RichTableCell;
import org.telegram.ui.iv.RichTextCell;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes7.dex */
public class RichEditorListView extends UniversalRecyclerView {
    private static final ArrayList<Long> EMPTY_QUOTE_IDS = null;
    RichTableCell activeCellSelectionTable;
    private boolean adaptiveLinkDialogs;
    private boolean allowTapAboveContent;
    private final RichAudioCell.Delegate audioDelegate;
    private int bottomInset;
    private final RichButtonRowCell.Delegate buttonRowDelegate;
    private final RichTextCell.Delegate cellDelegate;
    private boolean cellSelectionDragActive;
    private TL_iv.pageTableCell cellSelectionDragAnchor;
    private TL_iv.pageTableCell cellSelectionDragEnd;
    private final RichTableCell.CellSelectionListener cellSelectionListener;
    private boolean clearDraggedMultiCellSelectionOnMenuDismiss;
    private final IdentityHashMap<MediaUploadState, RichMediaConverter> converters;
    private int currentAccount;
    private Delegate delegate;
    private final RichDetailsCell.Delegate detailsDelegate;
    private final RichDividerCell.Delegate dividerDelegate;
    private final RichDocumentCell.Delegate documentDelegate;
    private int dotSelectedCol;
    private int dotSelectedRow;
    private boolean draggingOverTrash;
    private BlockRow draggingRow;
    private int emojiPadding;
    private MessageObject fileRefParentObject;
    private RichEditText focusedEditText;
    RichEditorHistory history;
    private final RichEditorHistory.Delegate historyDelegate;
    private final ViewTreeObserver.OnGlobalFocusChangeListener imeFocusListener;
    private int imeInset;
    private ArrayList<BlockRow> initialSelectionRows;
    final ArrayList<BlockRow> itemRows;
    private int lastExternalImageId;
    private int lastListHeight;
    private long lastTapDownTime;
    private float lastTapDownX;
    private float lastTapDownY;
    TL_iv.RichMessage loadedRichMessage;
    private boolean longPressConsumed;
    private Runnable longPressRunnable;
    private final RichMapCell.Delegate mapDelegate;
    private final RichMathCell.Delegate mathDelegate;
    private final RichMediaCell.Delegate mediaDelegate;
    BlockRow pendingInsertRow;
    BlockRow pendingMediaRow;
    private boolean pendingTapDismiss;
    private float pendingTapRawX;
    private float pendingTapRawY;
    private boolean pressMoved;
    private View pressTarget;
    private float pressX;
    private float pressY;
    private final RichQuoteAuthorCell.Delegate quoteAuthorDelegate;
    final HashMap<Long, TL_iv.RichText> quoteAuthors;
    private ReplyMessageLine quoteLine;
    private Theme.ResourcesProvider resourcesProvider;
    private int restoreFocusCell;
    private int restoreFocusChildPosition;
    private int restoreFocusOffset;
    final ArrayList<BlockRow> rows;
    private boolean suppressSpansChanged;
    private ItemOptions tableCellMenu;
    private final RichTableCell.Delegate tableDelegate;
    TextSelectionHelper.ArticleTextSelectionHelper textSelectionHelper;
    TextSelectionHelper.TextSelectionOverlay textSelectionOverlay;
    private final IdentityHashMap<MediaUploadState, RichMediaUploader> uploaders;

    /* renamed from: org.telegram.ui.iv.RichEditorListView$1, reason: invalid class name */
    public class AnonymousClass1 extends TextSelectionHelper.ArticleTextSelectionHelper {
        final /* synthetic */ RichEditorListView this$0;
        final /* synthetic */ Delegate val$delegate;

        public AnonymousClass1(RichEditorListView r1, Delegate r2) {
            this.this$0 = r1;
            this.val$delegate = r2;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public boolean canCut() {
            return true;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public boolean canPaste() {
            return true;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public boolean forceShowSelectAll() {
            return !RichEditorListView.m21823$$Nest$misWholeDocumentSelected(this.this$0);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public int getParentBottomPadding() {
            return this.this$0.getPaddingBottom();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public int getParentTopPadding() {
            return this.this$0.getPaddingTop();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public boolean onCopyOverride() {
            RichEditorListView.m21811$$Nest$mcopyHelperSelection(this.this$0);
            return true;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void onCutAction() {
            RichEditorListView.m21812$$Nest$mcutHelperSelection(this.this$0);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleTextSelectionHelper, org.telegram.ui.Cells.TextSelectionHelper
        public void onOffsetChanged() {
            super.onOffsetChanged();
            this.val$delegate.onSelectionChanged();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void onPasteAction() {
            RichEditorListView.m21832$$Nest$mpasteAtHelperSelection(this.this$0);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public boolean onSelectAllOverride() {
            if (expandSelectionToWholeCurrentBlock() == false) goto L7;
            return true;
        L7:
            return RichEditorListView.m21850$$Nest$mtryEscalateSelectAll(this.this$0);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void onTapToDismiss(float r3, float r4) {
            RichEditorListView.m21798$$Nest$fputpendingTapDismiss(this.this$0, true);
            RichEditorListView.m21799$$Nest$fputpendingTapRawX(this.this$0, r3);
            RichEditorListView.m21800$$Nest$fputpendingTapRawY(this.this$0, r4);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$10, reason: invalid class name */
    public class AnonymousClass10 implements RichMathCell.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass10(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichMathCell.Delegate
        public TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper() {
            return this.this$0.getTextSelectionHelper();
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$11, reason: invalid class name */
    public class AnonymousClass11 implements RichQuoteAuthorCell.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass11(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichQuoteAuthorCell.Delegate
        public TL_iv.RichText getQuoteAuthor(long r1) {
            return this.this$0.quoteAuthors.get(Long.valueOf(r1));
        }

        @Override // org.telegram.ui.iv.RichQuoteAuthorCell.Delegate
        public TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper() {
            return this.this$0.getTextSelectionHelper();
        }

        @Override // org.telegram.ui.iv.RichQuoteAuthorCell.Delegate
        public void onQuoteAuthorEnter(BlockRow r1) {
            RichEditorListView.m21819$$Nest$minsertParagraphAfterQuote(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichQuoteAuthorCell.Delegate
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            RichEditorListView.m21833$$Nest$mprepareEditText(this.this$0, r2);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).makeEditTextFocusable(r2, r3);
        }

        @Override // org.telegram.ui.iv.RichQuoteAuthorCell.Delegate
        public void setQuoteAuthor(long r2, TL_iv.RichText r4) {
            if (r4 != null) goto L4;
        L8:
            this.this$0.quoteAuthors.remove(Long.valueOf(r2));
            return;
        L4:
            if ((r4 instanceof TL_iv.textEmpty) == true) goto L8;
            this.this$0.quoteAuthors.put(Long.valueOf(r2), r4);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$12, reason: invalid class name */
    public class AnonymousClass12 implements RichTableCell.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass12(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichTableCell.Delegate
        public TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper() {
            return this.this$0.getTextSelectionHelper();
        }

        @Override // org.telegram.ui.iv.RichTableCell.Delegate
        public void onLockedInsert(CharSequence r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            if (r2.length() <= 0) goto L8;
            RichEditorListView.m21837$$Nest$mreplaceHelperSelectionWith(this.this$0, r2.toString());
            return;
        }

        @Override // org.telegram.ui.iv.RichTableCell.Delegate
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            RichEditorListView.m21833$$Nest$mprepareEditText(this.this$0, r2);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).makeEditTextFocusable(r2, r3);
        }

        @Override // org.telegram.ui.iv.RichTableCell.Delegate
        public boolean onSelectAll(BlockRow r1) {
            return RichEditorListView.m21850$$Nest$mtryEscalateSelectAll(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichTableCell.Delegate
        public void onSpansChanged(BlockRow r1) {
            RichEditorListView.m21828$$Nest$monCellSpansChanged(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichTableCell.Delegate
        public void onTextChanged(BlockRow r1) {
            RichEditorHistory r12 = this.this$0.history;
            if (r12 == null) goto L5;
            r12.onTyping();
        L5:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichTableCell.Delegate
        public void onTextWillChange(BlockRow r1, int r2, int r3) {
            RichEditorHistory r0 = this.this$0.history;
            if (r0 == null) goto L6;
            r0.onBeforeChange(r2, r3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$13, reason: invalid class name */
    public class AnonymousClass13 implements RichDetailsCell.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass13(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichDetailsCell.Delegate
        public TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper() {
            return this.this$0.getTextSelectionHelper();
        }

        @Override // org.telegram.ui.iv.RichDetailsCell.Delegate
        public void onLockedInsert(CharSequence r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            if (r2.length() <= 0) goto L8;
            RichEditorListView.m21837$$Nest$mreplaceHelperSelectionWith(this.this$0, r2.toString());
            return;
        }

        @Override // org.telegram.ui.iv.RichDetailsCell.Delegate
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            RichEditorListView.m21833$$Nest$mprepareEditText(this.this$0, r2);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).makeEditTextFocusable(r2, r3);
        }

        @Override // org.telegram.ui.iv.RichDetailsCell.Delegate
        public boolean onSelectAll(BlockRow r1) {
            return RichEditorListView.m21850$$Nest$mtryEscalateSelectAll(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichDetailsCell.Delegate
        public void onSpansChanged(BlockRow r1) {
            RichEditorListView.m21828$$Nest$monCellSpansChanged(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichDetailsCell.Delegate
        public void onTitleBackspace(BlockRow r1) {
            RichEditorListView.m21813$$Nest$mdeleteDetails(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichDetailsCell.Delegate
        public void onTitleChanged(BlockRow r1) {
            RichEditorHistory r12 = this.this$0.history;
            if (r12 == null) goto L5;
            r12.onTyping();
        L5:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichDetailsCell.Delegate
        public void onTitleEnter(BlockRow r1) {
            RichEditorListView.m21830$$Nest$monDetailsTitleEnter(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichDetailsCell.Delegate
        public void onToggle(BlockRow r1) {
            RichEditorListView.m21847$$Nest$mtoggleDetails(this.this$0, r1);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$14, reason: invalid class name */
    public class AnonymousClass14 implements RichTextCell.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass14(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public int getListPaddingBottom(BlockRow r2) {
            int r22 = this.this$0.rows.indexOf(r2);
            if (r22 < 0) goto L11;
            int r23 = r22 + 1;
            if (r23 >= this.this$0.rows.size()) goto L11;
            if (this.this$0.rows.get(r23).level <= 0) goto L11;
            float r1 = 5.0f;
        L10:
            return AndroidUtilities.dp(r1);
        L11:
            r1 = 11.0f;
            goto L10
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public int getListPaddingTop(BlockRow r2) {
            int r22 = this.this$0.rows.indexOf(r2);
            if (r22 > 0) goto L5;
        L9:
            float r1 = 8.0f;
        L8:
            return AndroidUtilities.dp(r1);
        L5:
            if (this.this$0.rows.get(r22 - 1).level <= 0) goto L9;
            r1 = 2.0f;
            goto L8
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public int getOrderedListMarkerWidth(BlockRow r6, Paint r7) {
            int r0 = this.this$0.rows.indexOf(r6);
            if (r0 < 0) goto L36;
            int r1 = r6.level;
            if (r1 <= 0) goto L36;
            if (r6.num <= 0) goto L36;
            int r62 = r0;
        L10:
            if (r62 <= 0) goto L18;
            BlockRow r2 = this.this$0.rows.get(r62 - 1);
            int r3 = r2.level;
            if (r3 < r1) goto L18;
            if (r3 != r1) goto L17;
            if (r2.num <= 0) goto L18;
        L17:
            r62 = r62 - 1;
        L18:
            r0 = r0 + 1;
            if (r0 >= this.this$0.rows.size()) goto L25;
            BlockRow r22 = this.this$0.rows.get(r0);
            int r32 = r22.level;
            if (r32 < r1) goto L25;
            if (r32 != r1) goto L18;
            if (r22.num > 0) goto L18;
        L25:
            Paint r23 = new Paint(r7);
            r23.setTypeface(AndroidUtilities.bold());
            float r72 = 0.0f;
        L26:
            if (r62 >= r0) goto L34;
            BlockRow r33 = this.this$0.rows.get(r62);
            if (r33.level != r1) goto L32;
            if (r33.num <= 0) goto L32;
            r72 = Math.max(r72, r23.measureText(r33.num + "."));
        L32:
            r62 = r62 + 1;
            goto L26
        L34:
            return Math.max(AndroidUtilities.dp(28.0f), ((int) Math.ceil(r72)) + AndroidUtilities.dp(10.0f));
        L36:
            return super.getOrderedListMarkerWidth(r6, r7);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper() {
            return this.this$0.getTextSelectionHelper();
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onBackspace(BlockRow r2) {
            RichEditorListView.m21825$$Nest$monCellBackspaceAtStart(this.this$0, r2, true);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public boolean onBackspaceAtStart(BlockRow r2) {
            return RichEditorListView.m21825$$Nest$monCellBackspaceAtStart(this.this$0, r2, false);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onCheckboxToggle(BlockRow r1, boolean r2) {
            RichEditorListView.m21829$$Nest$monChecklistToggle(this.this$0, r1, r2);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onCommand(BlockRow r1, int r2) {
            this.this$0.handleSlashCommand(r1, r2);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onEnter(BlockRow r1) {
            RichEditorListView.m21826$$Nest$monCellEnter(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public boolean onIndent(BlockRow r1, boolean r2) {
            return this.this$0.onCellIndent(r1, r2);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onLanguageClick(BlockRow r1, View r2) {
            RichEditorListView.m21831$$Nest$monLanguageClick(this.this$0, r1, r2);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onLockedInsert(CharSequence r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            if (r2.length() <= 0) goto L8;
            RichEditorListView.m21837$$Nest$mreplaceHelperSelectionWith(this.this$0, r2.toString());
            return;
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public boolean onPaste(BlockRow r1, RichEditText r2) {
            return RichEditorListView.m21827$$Nest$monCellPaste(this.this$0, r1, r2);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onQuoteAuthorEnter(BlockRow r1) {
            RichEditorListView.m21824$$Nest$monCaptionEnter(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            RichEditorListView.m21833$$Nest$mprepareEditText(this.this$0, r2);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).makeEditTextFocusable(r2, r3);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public boolean onSelectAll(BlockRow r1) {
            return RichEditorListView.m21850$$Nest$mtryEscalateSelectAll(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onSlashSuggest(RichTextCell r1, String r2) {
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onSlashSuggest(r1, r2);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onSpansChanged(BlockRow r1) {
            RichEditorListView.m21828$$Nest$monCellSpansChanged(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onTextChanged(BlockRow r1) {
            RichEditorHistory r12 = this.this$0.history;
            if (r12 == null) goto L5;
            r12.onTyping();
        L5:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onTextWillChange(BlockRow r1, int r2, int r3) {
            RichEditorHistory r0 = this.this$0.history;
            if (r0 == null) goto L6;
            r0.onBeforeChange(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichTextCell.Delegate
        public void onTransform(BlockRow r2, TL_iv.PageBlock r3, int r4, int r5, boolean r6, boolean r7) {
            boolean r0 = r3 instanceof TL_iv.pageBlockBlockquote;
            RichEditorListView r1 = this.this$0;
            if (r0 == false) goto L6;
            r1.applyQuote(r2);
            return;
        L6:
            RichEditorListView.m21849$$Nest$mtransformRow(r1, r2, r3, r4, r5, r6, r7);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$15, reason: invalid class name */
    public class AnonymousClass15 implements RichEditorHistory.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass15(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichEditorHistory.Delegate
        public RichEditorHistory.FocusState captureFocus() {
            return RichEditorListView.m21808$$Nest$mcaptureFocusState(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorHistory.Delegate
        public ArrayList<BlockRow> getRows() {
            return this.this$0.rows;
        }

        @Override // org.telegram.ui.iv.RichEditorHistory.Delegate
        public void onHistoryChanged() {
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onHistoryChanged();
        }

        @Override // org.telegram.ui.iv.RichEditorHistory.Delegate
        public void restoreRows(List<BlockRow> r1, RichEditorHistory.FocusState r2) {
            RichEditorListView.m21841$$Nest$mrestoreFromHistory(this.this$0, r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$16, reason: invalid class name */
    public class AnonymousClass16 implements RichMediaUploader.Listener {
        final /* synthetic */ RichEditorListView this$0;
        final /* synthetic */ MediaUploadState val$media;
        final /* synthetic */ String val$path;
        final /* synthetic */ BlockRow val$row;

        public AnonymousClass16(RichEditorListView r1, MediaUploadState r2, BlockRow r3, String r4) {
            this.this$0 = r1;
            this.val$media = r2;
            this.val$row = r3;
            this.val$path = r4;
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onDocumentUploaded(TLRPC.Document r4) {
            r4.localPath = this.val$path;
            FileLoader.getInstance(RichEditorListView.m21787$$Nest$fgetcurrentAccount(this.this$0)).setLocalPathTo(r4, this.val$path);
            MediaUploadState r0 = this.val$media;
            r0.document = r4;
            r0.state = 2;
            TL_iv.PageBlock r02 = this.val$row.block;
            if ((r02 instanceof TL_iv.pageBlockDocument) == false) goto L5;
            long r1 = r4.id;
            ((TL_iv.pageBlockDocument) r02).document_id = r1;
        L5:
            RichEditorListView.m21797$$Nest$fgetuploaders(this.this$0).remove(this.val$media);
            this.this$0.adapter.update(false);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onError() {
            this.val$media.state = 3;
            RichEditorListView.m21797$$Nest$fgetuploaders(this.this$0).remove(this.val$media);
            this.this$0.rows.remove(this.val$row);
            this.this$0.adapter.update(true);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onProgress(float r2) {
            this.val$media.progress = r2;
            RichEditorListView.m21821$$Nest$minvalidateDocumentCell(this.this$0, this.val$row);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$17, reason: invalid class name */
    public class AnonymousClass17 implements RichMediaUploader.Listener {
        final /* synthetic */ RichEditorListView this$0;
        final /* synthetic */ MediaUploadState val$media;
        final /* synthetic */ BlockRow val$row;

        public AnonymousClass17(RichEditorListView r1, MediaUploadState r2, BlockRow r3) {
            this.this$0 = r1;
            this.val$media = r2;
            this.val$row = r3;
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onAudioUploaded(TLRPC.Document r4) {
            MediaUploadState r0 = this.val$media;
            r0.document = r4;
            r0.audioDisplayDocument = r4;
            r0.state = 2;
            TL_iv.PageBlock r02 = this.val$row.block;
            if ((r02 instanceof TL_iv.pageBlockAudio) == false) goto L5;
            long r1 = r4.id;
            ((TL_iv.pageBlockAudio) r02).audio_id = r1;
        L5:
            RichEditorListView.m21797$$Nest$fgetuploaders(this.this$0).remove(this.val$media);
            this.this$0.adapter.update(false);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onError() {
            this.val$media.state = 3;
            RichEditorListView.m21797$$Nest$fgetuploaders(this.this$0).remove(this.val$media);
            int r0 = this.this$0.rows.indexOf(this.val$row);
            if (r0 < 0) goto L5;
            this.this$0.rows.remove(r0);
            this.this$0.adapter.update(true);
        L5:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onProgress(float r2) {
            this.val$media.progress = r2;
            RichEditorListView.m21820$$Nest$minvalidateAudioCell(this.this$0, this.val$row);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$18, reason: invalid class name */
    public class AnonymousClass18 implements RichMediaConverter.Listener {
        final /* synthetic */ RichEditorListView this$0;
        final /* synthetic */ MediaUploadState val$media;
        final /* synthetic */ BlockRow val$row;

        public AnonymousClass18(RichEditorListView r1, MediaUploadState r2, BlockRow r3) {
            this.this$0 = r1;
            this.val$media = r2;
            this.val$row = r3;
        }

        @Override // org.telegram.ui.iv.RichMediaConverter.Listener
        public void onDone(String r9, int r10, int r11, int r12) {
            RichEditorListView.m21786$$Nest$fgetconverters(this.this$0).remove(this.val$media);
            MediaUploadState r0 = this.val$media;
            r0.isVideo = true;
            r0.localPath = r9;
            if (r10 <= 0) goto L5;
            r0.width = r10;
        L5:
            if (r11 <= 0) goto L7;
            r0.height = r11;
        L7:
            r0.duration = r12;
            r0.orientation = 0;
            r0.invert = 0;
            r0.progress = 0.0f;
            RichEditorListView.m21834$$Nest$mrefreshMediaCell(this.this$0, this.val$row);
            RichEditorListView r02 = this.this$0;
            BlockRow r1 = this.val$row;
            MediaUploadState r2 = this.val$media;
            RichEditorListView.m21844$$Nest$mstartMediaUpload(r02, r1, r2, r9, true, r2.width, r2.height, r12);
        }

        @Override // org.telegram.ui.iv.RichMediaConverter.Listener
        public void onError() {
            RichEditorListView.m21786$$Nest$fgetconverters(this.this$0).remove(this.val$media);
            MediaUploadState r0 = this.val$media;
            r0.state = 3;
            RichEditorListView.m21835$$Nest$mremoveMediaFromRow(this.this$0, this.val$row, r0);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMediaConverter.Listener
        public void onProgress(float r2) {
            this.val$media.progress = r2;
            RichEditorListView.m21822$$Nest$minvalidateMediaCell(this.this$0, this.val$row);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$19, reason: invalid class name */
    public class AnonymousClass19 implements RichMediaUploader.Listener {
        final /* synthetic */ RichEditorListView this$0;
        final /* synthetic */ MediaUploadState val$media;
        final /* synthetic */ BlockRow val$row;

        public AnonymousClass19(RichEditorListView r1, MediaUploadState r2, BlockRow r3) {
            this.this$0 = r1;
            this.val$media = r2;
            this.val$row = r3;
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onError() {
            this.val$media.state = 3;
            RichEditorListView.m21797$$Nest$fgetuploaders(this.this$0).remove(this.val$media);
            RichEditorListView.m21835$$Nest$mremoveMediaFromRow(this.this$0, this.val$row, this.val$media);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onPhotoUploaded(TLRPC.Photo r4) {
            MediaUploadState r0 = this.val$media;
            r0.photo = r4;
            r0.state = 2;
            TLRPC.PhotoSize r02 = FileLoader.getClosestPhotoSizeWithSize(r4.sizes, AndroidUtilities.getPhotoSize());
            if (r02 == null) goto L9;
            int r1 = r02.w;
            if (r1 <= 0) goto L9;
            int r03 = r02.h;
            if (r03 <= 0) goto L9;
            MediaUploadState r2 = this.val$media;
            r2.width = r1;
            r2.height = r03;
        L9:
            TL_iv.PageBlock r04 = RichEditorListView.m21853$$Nest$smitemBlockFor(this.val$row, this.val$media);
            if ((r04 instanceof TL_iv.pageBlockPhoto) == false) goto L12;
            long r12 = r4.id;
            ((TL_iv.pageBlockPhoto) r04).photo_id = r12;
        L12:
            RichEditorListView.m21797$$Nest$fgetuploaders(this.this$0).remove(this.val$media);
            RichEditorListView.m21834$$Nest$mrefreshMediaCell(this.this$0, this.val$row);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onProgress(float r2) {
            this.val$media.progress = r2;
            RichEditorListView.m21822$$Nest$minvalidateMediaCell(this.this$0, this.val$row);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onVideoUploaded(TLRPC.Document r4) {
            MediaUploadState r0 = this.val$media;
            r0.document = r4;
            r0.state = 2;
            TL_iv.PageBlock r02 = RichEditorListView.m21853$$Nest$smitemBlockFor(this.val$row, r0);
            if ((r02 instanceof TL_iv.pageBlockVideo) == false) goto L5;
            long r1 = r4.id;
            ((TL_iv.pageBlockVideo) r02).video_id = r1;
        L5:
            RichEditorListView.m21797$$Nest$fgetuploaders(this.this$0).remove(this.val$media);
            RichEditorListView.m21834$$Nest$mrefreshMediaCell(this.this$0, this.val$row);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMediaUploader.Listener
        public void onWidthHeightResolved(int r2, int r3) {
            if (r2 <= 0) goto L5;
            if (r3 <= 0) goto L5;
            MediaUploadState r0 = this.val$media;
            r0.width = r2;
            r0.height = r3;
        L5:
            RichEditorListView.m21822$$Nest$minvalidateMediaCell(this.this$0, this.val$row);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$2, reason: invalid class name */
    public class AnonymousClass2 extends TextSelectionHelper.Callback {
        final /* synthetic */ RichEditorListView this$0;
        final /* synthetic */ Delegate val$delegate;

        public static /* synthetic */ void $r8$lambda$Z8qwlW5CGQWmKJN1L2jMZZGEOfA(AnonymousClass2 r0, int r1, int r2, int r3) {
            r0.lambda$onStateChanged$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$poh8PbCCKx2jQBN6AgiWTnosrUI(AnonymousClass2 r0, float r1, float r2, int r3, int r4, int r5) {
            r0.lambda$onStateChanged$0(r1, r2, r3, r4, r5);
        }

        public AnonymousClass2(RichEditorListView r1, Delegate r2) {
            this.this$0 = r1;
            this.val$delegate = r2;
        }

        private /* synthetic */ void lambda$onStateChanged$0(float r2, float r3, int r4, int r5, int r6) {
            if (RichEditorListView.m21840$$Nest$mrestoreFocusAtScreenPoint(this.this$0, r2, r3) == true) goto L7;
            if (r4 < 0) goto L8;
            RichEditorListView.m21839$$Nest$mrestoreFocusAt(this.this$0, r4, r5, r6);
            return;
        L8:
            return;
        }

        private /* synthetic */ void lambda$onStateChanged$1(int r1, int r2, int r3) {
            RichEditorListView.m21839$$Nest$mrestoreFocusAt(this.this$0, r1, r2, r3);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.Callback
        public void onStateChanged(boolean r8) {
            this.val$delegate.onSelectionChanged();
            RichEditorListView r0 = this.this$0;
            if (r8 == false) goto L6;
            RichEditorListView.m21801$$Nest$fputrestoreFocusCell(r0, r0.textSelectionHelper.getAnchorCell());
            RichEditorListView r82 = this.this$0;
            RichEditorListView.m21803$$Nest$fputrestoreFocusOffset(r82, r82.textSelectionHelper.getAnchorOffset());
            RichEditorListView r83 = this.this$0;
            RichEditorListView.m21802$$Nest$fputrestoreFocusChildPosition(r83, r83.textSelectionHelper.getAnchorChildPosition());
            RichEditorListView.m21842$$Nest$msetEditTextsLocked(this.this$0, true);
            RichEditorListView.m21816$$Nest$mhideEditTextActionModes(this.this$0);
            RichEditorListView.m21814$$Nest$mfinishEditTextActionModes(this.this$0);
            return;
        L6:
            int r4 = RichEditorListView.m21794$$Nest$fgetrestoreFocusCell(r0);
            int r6 = RichEditorListView.m21796$$Nest$fgetrestoreFocusOffset(this.this$0);
            int r5 = RichEditorListView.m21795$$Nest$fgetrestoreFocusChildPosition(this.this$0);
            RichEditorListView.m21801$$Nest$fputrestoreFocusCell(this.this$0, -1);
            RichEditorListView.m21803$$Nest$fputrestoreFocusOffset(this.this$0, -1);
            RichEditorListView.m21802$$Nest$fputrestoreFocusChildPosition(this.this$0, 0);
            boolean r84 = RichEditorListView.m21790$$Nest$fgetpendingTapDismiss(this.this$0);
            float r2 = RichEditorListView.m21791$$Nest$fgetpendingTapRawX(this.this$0);
            float r3 = RichEditorListView.m21792$$Nest$fgetpendingTapRawY(this.this$0);
            RichEditorListView.m21798$$Nest$fputpendingTapDismiss(this.this$0, false);
            RichEditorListView.m21842$$Nest$msetEditTextsLocked(this.this$0, false);
            RichEditorListView.m21814$$Nest$mfinishEditTextActionModes(this.this$0);
            if (r84 == false) goto L10;
            this.this$0.post(new RichEditorListView$2$$ExternalSyntheticLambda0(this, r2, r3, r4, r5, r6));
            return;
        L10:
            RichEditorListView r7 = this.this$0;
            if (r4 < 0) goto L14;
            r7.post(new RichEditorListView$2$$ExternalSyntheticLambda1(this, r4, r5, r6));
            return;
        L14:
            View r72 = r7.findFocus();
            if ((r72 instanceof RichEditText) == false) goto L18;
            this.this$0.post(new RichEditorListView$2$$ExternalSyntheticLambda2((RichEditText) r72));
            return;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ RichEditorListView this$0;
        final /* synthetic */ Delegate val$delegate;

        public AnonymousClass3(RichEditorListView r1, Delegate r2) {
            this.this$0 = r1;
            this.val$delegate = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 0) goto L5;
            this.this$0.textSelectionHelper.stopScrolling();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            Delegate r12 = this.val$delegate;
            if (r12 == null) goto L5;
            r12.onListScrolled(r3);
        L5:
            this.this$0.textSelectionHelper.onParentScrolled();
            RichEditorListView.m21817$$Nest$mhideKeyboardIfFocusScrolledAway(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$4, reason: invalid class name */
    public class AnonymousClass4 implements SelectionEdit {
        final /* synthetic */ RichEditorListView this$0;
        final /* synthetic */ int val$eOff;
        final /* synthetic */ int val$endRowIdx;
        final /* synthetic */ int val$sOff;
        final /* synthetic */ int val$startRowIdx;

        /* renamed from: $r8$lambda$P-JL4yYQKJMP_AlfstKcdN-7WAo, reason: not valid java name */
        public static /* synthetic */ void m21854$r8$lambda$PJL4yYQKJMP_AlfstKcdN7WAo(AnonymousClass4 r0, BlockRow r1) {
            r0.lambda$replaceWith$0(r1);
        }

        public AnonymousClass4(RichEditorListView r1, int r2, int r3, int r4, int r5) {
            this.this$0 = r1;
            this.val$startRowIdx = r2;
            this.val$endRowIdx = r3;
            this.val$sOff = r4;
            this.val$eOff = r5;
        }

        private /* synthetic */ void lambda$replaceWith$0(BlockRow r1) {
            if (r1 != null) goto L4;
            View r0 = null;
        L6:
            if ((r0 instanceof RichTextCell) == false) goto L9;
            RichTextCell r02 = (RichTextCell) r0;
            r02.requestEditFocus();
            r02.getEditText().setSelection(r02.getEditText().length());
            return;
        L9:
            return;
        L4:
            r0 = this.this$0.findViewByItemObject(r1);
            goto L6
        }

        @Override // org.telegram.ui.iv.RichEditorListView.SelectionEdit
        public TL_iv.RichMessage extractRichMessage() {
            BlockRow r0 = this.this$0.rows.get(this.val$startRowIdx);
            BlockRow r1 = this.this$0.rows.get(this.val$endRowIdx);
            TL_iv.PageBlock r2 = r0.block;
            TL_iv.PageBlock r3 = r1.block;
            RichEditorListView r4 = this.this$0;
            int r5 = this.val$sOff;
            if (this.val$startRowIdx != this.val$endRowIdx) goto L5;
            int r6 = this.val$eOff;
        L6:
            TL_iv.PageBlock r42 = RichEditorListView.m21843$$Nest$msliceClone(r4, r0, r5, r6);
            if (this.val$startRowIdx != this.val$endRowIdx) goto L9;
            TL_iv.PageBlock r52 = null;
        L10:
            if (r42 == null) goto L12;
            r0.block = r42;
        L12:
            if (r52 == null) goto L20;
            r1.block = r52;
        L20:
            ArrayList<TL_iv.PageBlock> r43 = RichEditorListView.m21815$$Nest$mflattenRange(this.this$0, this.val$startRowIdx, this.val$endRowIdx + 1, false);     // Catch: Throwable -> L17
            ArrayList<TLRPC.Photo> r53 = RichEditorListView.m21810$$Nest$mcollectMediaPhotos(this.this$0, this.val$startRowIdx, this.val$endRowIdx);     // Catch: Throwable -> L17
            ArrayList<TLRPC.Document> r8 = RichEditorListView.m21809$$Nest$mcollectMediaDocuments(this.this$0, this.val$startRowIdx, this.val$endRowIdx);     // Catch: Throwable -> L17
            r0.block = r2;
            r1.block = r3;
            TL_iv.RichMessage r02 = new TL_iv.RichMessage();
            r02.blocks = r43;
            r02.photos = r53;
            r02.documents = r8;
            return r02;
        L17:
            th = move-exception;
            r0.block = r2;
            r1.block = r3;
            throw th;
        L9:
            r52 = RichEditorListView.m21843$$Nest$msliceClone(this.this$0, r1, 0, this.val$eOff);
            goto L10
        L5:
            r6 = -1;
            goto L6
        }

        @Override // org.telegram.ui.iv.RichEditorListView.SelectionEdit
        public void replaceWith(TL_iv.RichMessage r9) {
            if (r9 != null) goto L5;
            return;
        L5:
            if (this.val$startRowIdx < this.this$0.rows.size()) goto L7;
            return;
        L7:
            if (this.val$endRowIdx >= this.this$0.rows.size()) goto L67;
            RichEditorHistory r0 = this.this$0.history;
            if (r0 == null) goto L12;
            r0.flush();
        L12:
            TextSelectionHelper.ArticleTextSelectionHelper r02 = this.this$0.textSelectionHelper;
            if (r02 == null) goto L15;
            r02.clear();
        L15:
            BlockRow r03 = this.this$0.rows.get(this.val$startRowIdx);
            BlockRow r1 = this.this$0.rows.get(this.val$endRowIdx);
            boolean r2 = RichEditorListView.m21852$$Nest$smisFormattable(r03.block);
            CharSequence r3 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r2 == false) goto L18;
            CharSequence r22 = RichEditorListView.m21845$$Nest$mstyledTextOf(this.this$0, r03);
        L20:
            if (RichEditorListView.m21852$$Nest$smisFormattable(r1.block) == false) goto L22;
            r3 = RichEditorListView.m21845$$Nest$mstyledTextOf(this.this$0, r1);
        L22:
            SpannableStringBuilder r4 = new SpannableStringBuilder(r22.subSequence(0, Math.max(0, Math.min(this.val$sOff, r22.length()))));
            SpannableStringBuilder r23 = new SpannableStringBuilder(r3.subSequence(Math.max(0, Math.min(this.val$eOff, r3.length())), r3.length()));
            ArrayList r32 = new ArrayList();
            RichEditorListView.flattenBlocks(r32, r9.blocks);
            if (r32.isEmpty() == false) goto L26;
            SpannableStringBuilder r12 = new SpannableStringBuilder(r4);
            r12.append(r23);
            TL_iv.pageBlockParagraph r24 = new TL_iv.pageBlockParagraph();
            RichTextCell.applyStyledTextToBlock(r24, r12);
            r32.add(new BlockRow(r24, r03.level, r03.num));
        L37:
            RichEditorListView r04 = this.this$0;
            TL_iv.RichMessage r13 = r04.loadedRichMessage;
            if (r13 != null) goto L40;
            r04.loadedRichMessage = r9;
        L46:
            int r92 = 0;
        L48:
            if (r92 >= r32.size()) goto L50;
            RichEditorListView.m21838$$Nest$mresolveLoadedMedia(this.this$0, (BlockRow) r32.get(r92));
            r92 = r92 + 1;
            goto L48
        L50:
            int r93 = this.val$endRowIdx;
        L51:
            int r05 = this.val$startRowIdx;
            RichEditorListView r14 = this.this$0;
            if (r93 < r05) goto L54;
            r14.rows.remove(r93);
            r93 = r93 - 1;
            goto L51
        L54:
            r14.rows.addAll(r05, r32);
            RichEditorListView.m21836$$Nest$mrenumberAllRuns(this.this$0);
            this.this$0.adapter.update(false);
            RichEditorHistory r94 = this.this$0.history;
            if (r94 == null) goto L57;
            r94.record();
        L57:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
            if (r32.isEmpty() == false) goto L60;
            BlockRow r95 = null;
        L61:
            this.this$0.post(new RichEditorListView$4$$ExternalSyntheticLambda0(this, r95));
            return;
        L60:
            r95 = (BlockRow) r32.get(r32.size() - 1);
            goto L61
        L40:
            ArrayList<TLRPC.Photo> r06 = r9.photos;
            if (r06 == null) goto L43;
            r13.photos.addAll(r06);
        L43:
            ArrayList<TLRPC.Document> r96 = r9.documents;
            if (r96 == null) goto L46;
            this.this$0.loadedRichMessage.documents.addAll(r96);
            goto L46
        L26:
            if (r4.length() <= 0) goto L32;
            BlockRow r5 = (BlockRow) r32.get(0);
            if (RichEditorListView.m21852$$Nest$smisFormattable(r5.block) == false) goto L30;
            SpannableStringBuilder r07 = new SpannableStringBuilder(r4);
            r07.append(RichTextCell.readStyledText(r5.block));
            RichTextCell.applyStyledTextToBlock(r5.block, r07);
            goto L32
        L30:
            TL_iv.pageBlockParagraph r52 = new TL_iv.pageBlockParagraph();
            RichTextCell.applyStyledTextToBlock(r52, r4);
            r32.add(0, new BlockRow(r52, r03.level, r03.num));
        L32:
            if (r23.length() <= 0) goto L37;
            BlockRow r08 = (BlockRow) r32.get(r32.size() - 1);
            if (RichEditorListView.m21852$$Nest$smisFormattable(r08.block) == false) goto L36;
            SpannableStringBuilder r15 = new SpannableStringBuilder(RichTextCell.readStyledText(r08.block));
            r15.append(r23);
            RichTextCell.applyStyledTextToBlock(r08.block, r15);
            goto L37
        L36:
            TL_iv.pageBlockParagraph r09 = new TL_iv.pageBlockParagraph();
            RichTextCell.applyStyledTextToBlock(r09, r23);
            r32.add(new BlockRow(r09, r1.level, r1.num));
            goto L37
        L18:
            r22 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L20
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$5, reason: invalid class name */
    public class AnonymousClass5 implements RichButtonRowCell.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass5(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichButtonRowCell.Delegate
        public void onAddButton(BlockRow r5, View r6) {
            RichEditorListView.m21818$$Nest$mhideTextSelectionUi(this.this$0, false);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onBlockButtonEditRequested(new BlockButtonEdit(this.this$0, r5, -1, null), r6);
        }

        @Override // org.telegram.ui.iv.RichButtonRowCell.Delegate
        public void onCycleButtonStyle(BlockRow r2, int r3) {
            TL_iv.PageBlock r22 = r2.block;
            if ((r22 instanceof TL_iv.pageBlockButtonRow) == false) goto L19;
            TL_iv.pageBlockButtonRow r23 = (TL_iv.pageBlockButtonRow) r22;
            if (r3 >= 0) goto L8;
            return;
        L8:
            if (r3 >= r23.buttons.size()) goto L20;
            RichEditorHistory r0 = this.this$0.history;
            if (r0 == null) goto L13;
            r0.flush();
        L13:
            RichEditorListView.m21851$$Nest$smcycleButtonStyle(r23.buttons.get(r3));
            this.this$0.adapter.update(false);
            RichEditorHistory r24 = this.this$0.history;
            if (r24 == null) goto L16;
            r24.record();
        L16:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
            return;
        L20:
            return;
        }

        @Override // org.telegram.ui.iv.RichButtonRowCell.Delegate
        public void onEditButton(BlockRow r4, int r5, View r6) {
            RichEditorListView.m21818$$Nest$mhideTextSelectionUi(this.this$0, false);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onBlockButtonEditRequested(new BlockButtonEdit(this.this$0, r4, r5, null), r6);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$6, reason: invalid class name */
    public class AnonymousClass6 implements RichMediaCell.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass6(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper() {
            return this.this$0.getTextSelectionHelper();
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public ItemOptions makeMenu(View r1) {
            return RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).makeMenu(r1);
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onAddMedia(BlockRow r2) {
            RichEditorListView r1 = this.this$0;
            r1.pendingMediaRow = r2;
            RichEditorListView.m21788$$Nest$fgetdelegate(r1).onOpenAttachRequest(74, 0);
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onCancelUpload(BlockRow r1, MediaUploadState r2) {
            RichEditorListView.m21807$$Nest$mcancelMediaUpload(this.this$0, r1, r2);
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onCaptionChanged(BlockRow r1) {
            RichEditorHistory r12 = this.this$0.history;
            if (r12 == null) goto L5;
            r12.onTyping();
        L5:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onCaptionEnter(BlockRow r1) {
            RichEditorListView.m21824$$Nest$monCaptionEnter(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onCaptionLockedInsert(CharSequence r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            if (r2.length() <= 0) goto L8;
            RichEditorListView.m21837$$Nest$mreplaceHelperSelectionWith(this.this$0, r2.toString());
            return;
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public boolean onCaptionSelectAll(BlockRow r1) {
            return RichEditorListView.m21850$$Nest$mtryEscalateSelectAll(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onCaptionSpansChanged(BlockRow r1) {
            RichEditorListView.m21828$$Nest$monCellSpansChanged(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onCaptionWillChange(BlockRow r1, int r2, int r3) {
            RichEditorHistory r0 = this.this$0.history;
            if (r0 == null) goto L6;
            r0.onBeforeChange(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onDeleteMedia(BlockRow r1, MediaUploadState r2) {
            RichEditorListView.m21807$$Nest$mcancelMediaUpload(this.this$0, r1, r2);
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onMediaPick(BlockRow r2) {
            RichEditorListView r1 = this.this$0;
            r1.pendingMediaRow = r2;
            RichEditorListView.m21788$$Nest$fgetdelegate(r1).onOpenAttachRequest(74, 0);
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            RichEditorListView.m21833$$Nest$mprepareEditText(this.this$0, r2);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).makeEditTextFocusable(r2, r3);
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onSwitchMode(BlockRow r1) {
            RichEditorListView.m21846$$Nest$mswitchGalleryMode(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichMediaCell.Delegate
        public void onToggleSpoiler(BlockRow r1, MediaUploadState r2) {
            RichEditorListView.m21848$$Nest$mtoggleMediaSpoiler(this.this$0, r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$7, reason: invalid class name */
    public class AnonymousClass7 implements RichAudioCell.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass7(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichAudioCell.Delegate
        public TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper() {
            return this.this$0.getTextSelectionHelper();
        }

        @Override // org.telegram.ui.iv.RichAudioCell.Delegate
        public void onCancelUpload(BlockRow r1) {
            RichEditorListView.m21805$$Nest$mcancelAudioUpload(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichAudioCell.Delegate
        public void onCaptionChanged(BlockRow r1) {
            RichEditorHistory r12 = this.this$0.history;
            if (r12 == null) goto L5;
            r12.onTyping();
        L5:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichAudioCell.Delegate
        public void onCaptionEnter(BlockRow r1) {
            RichEditorListView.m21824$$Nest$monCaptionEnter(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichAudioCell.Delegate
        public void onCaptionLockedInsert(CharSequence r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            if (r2.length() <= 0) goto L8;
            RichEditorListView.m21837$$Nest$mreplaceHelperSelectionWith(this.this$0, r2.toString());
            return;
        }

        @Override // org.telegram.ui.iv.RichAudioCell.Delegate
        public boolean onCaptionSelectAll(BlockRow r1) {
            return RichEditorListView.m21850$$Nest$mtryEscalateSelectAll(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichAudioCell.Delegate
        public void onCaptionSpansChanged(BlockRow r1) {
            RichEditorListView.m21828$$Nest$monCellSpansChanged(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichAudioCell.Delegate
        public void onCaptionWillChange(BlockRow r1, int r2, int r3) {
            RichEditorHistory r0 = this.this$0.history;
            if (r0 == null) goto L6;
            r0.onBeforeChange(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichAudioCell.Delegate
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            RichEditorListView.m21833$$Nest$mprepareEditText(this.this$0, r2);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).makeEditTextFocusable(r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$8, reason: invalid class name */
    public class AnonymousClass8 implements RichDocumentCell.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass8(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichDocumentCell.Delegate
        public MessageObject getFileRefParentObject() {
            return RichEditorListView.m21789$$Nest$fgetfileRefParentObject(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichDocumentCell.Delegate
        public TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper() {
            return this.this$0.getTextSelectionHelper();
        }

        @Override // org.telegram.ui.iv.RichDocumentCell.Delegate
        public void onCancelUpload(BlockRow r1) {
            RichEditorListView.m21806$$Nest$mcancelDocumentUpload(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichDocumentCell.Delegate
        public void onCaptionChanged(BlockRow r1) {
            RichEditorHistory r12 = this.this$0.history;
            if (r12 == null) goto L5;
            r12.onTyping();
        L5:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichDocumentCell.Delegate
        public void onCaptionEnter(BlockRow r1) {
            RichEditorListView.m21824$$Nest$monCaptionEnter(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichDocumentCell.Delegate
        public void onCaptionLockedInsert(CharSequence r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            if (r2.length() <= 0) goto L8;
            RichEditorListView.m21837$$Nest$mreplaceHelperSelectionWith(this.this$0, r2.toString());
            return;
        }

        @Override // org.telegram.ui.iv.RichDocumentCell.Delegate
        public boolean onCaptionSelectAll(BlockRow r1) {
            return RichEditorListView.m21850$$Nest$mtryEscalateSelectAll(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichDocumentCell.Delegate
        public void onCaptionSpansChanged(BlockRow r1) {
            RichEditorListView.m21828$$Nest$monCellSpansChanged(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichDocumentCell.Delegate
        public void onCaptionWillChange(BlockRow r1, int r2, int r3) {
            RichEditorHistory r0 = this.this$0.history;
            if (r0 == null) goto L6;
            r0.onBeforeChange(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichDocumentCell.Delegate
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            RichEditorListView.m21833$$Nest$mprepareEditText(this.this$0, r2);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).makeEditTextFocusable(r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorListView$9, reason: invalid class name */
    public class AnonymousClass9 implements RichMapCell.Delegate {
        final /* synthetic */ RichEditorListView this$0;

        public AnonymousClass9(RichEditorListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichMapCell.Delegate
        public TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper() {
            return this.this$0.getTextSelectionHelper();
        }

        @Override // org.telegram.ui.iv.RichMapCell.Delegate
        public void onCaptionChanged(BlockRow r1) {
            RichEditorHistory r12 = this.this$0.history;
            if (r12 == null) goto L5;
            r12.onTyping();
        L5:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
        }

        @Override // org.telegram.ui.iv.RichMapCell.Delegate
        public void onCaptionEnter(BlockRow r1) {
            RichEditorListView.m21824$$Nest$monCaptionEnter(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichMapCell.Delegate
        public void onCaptionLockedInsert(CharSequence r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            if (r2.length() <= 0) goto L8;
            RichEditorListView.m21837$$Nest$mreplaceHelperSelectionWith(this.this$0, r2.toString());
            return;
        }

        @Override // org.telegram.ui.iv.RichMapCell.Delegate
        public boolean onCaptionSelectAll(BlockRow r1) {
            return RichEditorListView.m21850$$Nest$mtryEscalateSelectAll(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichMapCell.Delegate
        public void onCaptionSpansChanged(BlockRow r1) {
            RichEditorListView.m21828$$Nest$monCellSpansChanged(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichMapCell.Delegate
        public void onCaptionWillChange(BlockRow r1, int r2, int r3) {
            RichEditorHistory r0 = this.this$0.history;
            if (r0 == null) goto L6;
            r0.onBeforeChange(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichMapCell.Delegate
        public void onPickLocation(BlockRow r1) {
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onOpenLocationRequest(r1);
        }

        @Override // org.telegram.ui.iv.RichMapCell.Delegate
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            RichEditorListView.m21833$$Nest$mprepareEditText(this.this$0, r2);
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).makeEditTextFocusable(r2, r3);
        }
    }

    public class BlockButtonEdit {
        private final int index;
        private final BlockRow row;
        final /* synthetic */ RichEditorListView this$0;

        private BlockButtonEdit(RichEditorListView r1, BlockRow r2, int r3) {
            this.this$0 = r1;
            this.row = r2;
            this.index = r3;
        }

        public /* synthetic */ BlockButtonEdit(RichEditorListView r1, BlockRow r2, int r3, RichEditorListViewIA r4) {
            this(r1, r2, r3);
        }

        private TL_keyboard.PageButton getButton() {
            TL_iv.pageBlockButtonRow r0 = getRowBlock();
            if (r0 == null) goto L10;
            int r1 = this.index;
            if (r1 >= 0) goto L7;
            return null;
        L7:
            if (r1 < r0.buttons.size()) goto L9;
            return null;
        L9:
            return r0.buttons.get(this.index);
        L10:
            return null;
        }

        private TL_iv.pageBlockButtonRow getRowBlock() {
            BlockRow r1 = this.row;
            if (r1 == null) goto L8;
            TL_iv.PageBlock r12 = r1.block;
            if ((r12 instanceof TL_iv.pageBlockButtonRow) == true) goto L7;
            return null;
        L7:
            return (TL_iv.pageBlockButtonRow) r12;
        L8:
            return null;
        }

        public void apply(String r6, TL_keyboard.InlineButtonType r7) {
            if (TextUtils.isEmpty(r6) == false) goto L5;
            return;
        L5:
            if (RichInlineButtonSpan.isSupported(r7) == false) goto L37;
            TL_iv.pageBlockButtonRow r0 = getRowBlock();
            if (r0 == null) goto L38;
            int r1 = this.index;
            if (r1 >= 0) goto L13;
        L15:
            boolean r12 = false;
        L16:
            if (r12 == false) goto L18;
        L20:
            RichEditorHistory r3 = this.this$0.history;
            if (r3 == null) goto L23;
            r3.flush();
        L23:
            if (r12 == false) goto L25;
            TL_keyboard.PageButton r32 = r0.buttons.get(this.index);
        L26:
            r32.text = RichTextStyle.fromSpannable(r6);
            r32.type = r7;
            if (r32.style != null) goto L29;
            r32.style = new TL_keyboard.RichButtonStyle();
        L29:
            if (r12 == true) goto L31;
            r0.buttons.add(r32);
        L31:
            this.this$0.adapter.update(false);
            RichEditorHistory r62 = this.this$0.history;
            if (r62 == null) goto L34;
            r62.record();
        L34:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
            return;
        L25:
            r32 = new TL_keyboard.PageButton();
            goto L26
        L18:
            if (r0.buttons.size() < 8) goto L20;
            return;
        L13:
            if (r1 >= r0.buttons.size()) goto L15;
            r12 = true;
            goto L16
        L38:
            return;
        }

        public void delete() {
            TL_iv.pageBlockButtonRow r0 = getRowBlock();
            if (r0 == null) goto L17;
            int r1 = this.index;
            if (r1 >= 0) goto L7;
            return;
        L7:
            if (r1 >= r0.buttons.size()) goto L19;
            RichEditorHistory r12 = this.this$0.history;
            if (r12 == null) goto L12;
            r12.flush();
        L12:
            r0.buttons.remove(this.index);
            this.this$0.adapter.update(false);
            RichEditorHistory r02 = this.this$0.history;
            if (r02 == null) goto L15;
            r02.record();
        L15:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
            return;
        L19:
            return;
        }

        public boolean exists() {
            TL_iv.pageBlockButtonRow r0 = getRowBlock();
            if (r0 == null) goto L10;
            int r1 = this.index;
            if (r1 >= 0) goto L7;
            return false;
        L7:
            if (r1 >= r0.buttons.size()) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        public String getLabel() {
            TL_keyboard.PageButton r0 = getButton();
            if (r0 != null) goto L7;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            return RichTextStyle.plainOf(r0.text);
        }

        public TL_keyboard.InlineButtonType getType() {
            TL_keyboard.PageButton r0 = getButton();
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.type;
        }

        public long getUserId() {
            TL_keyboard.InlineButtonType r2 = getType();
            if ((r2 instanceof TL_keyboard.TL_inlineButtonTypeUserProfile) == true) goto L5;
            return 0;
        L5:
            return ((TL_keyboard.TL_inlineButtonTypeUserProfile) r2).user_id;
        }
    }

    public interface Delegate {
        void makeEditTextFocusable(RichEditText r1, boolean r2);

        ItemOptions makeMenu(View r1);

        void onBlockButtonEditRequested(BlockButtonEdit r1, View r2);

        void onContentChanged();

        void onHistoryChanged();

        void onInlineButtonEditRequested(InlineButtonEdit r1, View r2);

        void onListLayoutUpdated();

        void onListScrolled(int r1);

        void onOpenAttachRequest(int r1, int r2);

        void onOpenLocationRequest(BlockRow r1);

        void onReorderEnd();

        boolean onReorderMove(float r1, float r2);

        void onReorderStart();

        void onSelectionChanged();

        void onSlashSuggest(RichTextCell r1, String r2);
    }

    public class InlineButtonEdit {
        private final RichEditText editText;
        private final RichInlineButtonSpan existingSpan;
        private final int from;
        private final TL_iv.RichText label;
        final /* synthetic */ RichEditorListView this$0;
        private final int to;

        private InlineButtonEdit(RichEditorListView r1, RichEditText r2, int r3, int r4, RichInlineButtonSpan r5) {
            this.this$0 = r1;
            this.editText = r2;
            this.from = r3;
            this.to = r4;
            this.existingSpan = r5;
            if (r5 != null) goto L5;
        L8:
            this.label = RichTextStyle.fromSpannable(new SpannableStringBuilder(r2.getText().subSequence(r3, r4)));
            return;
        L5:
            if (r5.getButton() == null) goto L8;
            this.label = r5.getButton().text;
        }

        public /* synthetic */ InlineButtonEdit(RichEditorListView r1, RichEditText r2, int r3, int r4, RichInlineButtonSpan r5, RichEditorListViewIA r6) {
            this(r1, r2, r3, r4, r5);
        }

        public void apply(TL_keyboard.InlineButtonType r7) {
            if (RichInlineButtonSpan.isSupported(r7) == false) goto L48;
            Spannable r0 = this.editText.getText();
            if (r0 != null) goto L8;
            return;
        L8:
            if (this.from >= 0) goto L10;
            return;
        L10:
            if (this.to <= r0.length()) goto L12;
            return;
        L12:
            if (this.from >= this.to) goto L49;
            RichEditorHistory r1 = this.this$0.history;
            if (r1 == null) goto L17;
            r1.flush();
        L17:
            TextSelectionHelper.ArticleTextSelectionHelper r12 = this.this$0.textSelectionHelper;
            if (r12 == null) goto L20;
            r12.clear();
        L20:
            this.editText.setLocked(false);
            RichInlineButtonSpan[] r13 = (RichInlineButtonSpan[]) r0.getSpans(this.from, this.to, RichInlineButtonSpan.class);
            int r3 = r13.length;
            int r4 = 0;
        L21:
            if (r4 >= r3) goto L23;
            r0.removeSpan(r13[r4]);
            r4 = r4 + 1;
            goto L21
        L23:
            RichTextStyle.removeLink(r0, this.from, this.to);
            RichTextStyle.removeDate(r0, this.from, this.to);
            RichInlineButtonSpan r14 = this.existingSpan;
            if (r14 != null) goto L26;
        L28:
            TL_iv.textButton r15 = new TL_iv.textButton();
        L29:
            r15.text = this.label;
            r15.type = r7;
            if (r15.style != null) goto L32;
            r15.style = new TL_keyboard.RichButtonStyle();
        L32:
            RichInlineButtonSpan r72 = new RichInlineButtonSpan(r15);
            r72.bind(this.editText, RichEditorListView.m21787$$Nest$fgetcurrentAccount(this.this$0), RichEditorListView.m21793$$Nest$fgetresourcesProvider(this.this$0));
            r0.setSpan(r72, this.from, this.to, 33);
            r72.removeNestedReplacementSpans(r0);
            RichEditText r73 = this.editText;
            r73.setSelection(Math.min(this.to, r73.length()));
            RichEditorListView.m21804$$Nest$fputsuppressSpansChanged(this.this$0, true);
            this.editText.notifyInlineContentChanged();     // Catch: Throwable -> L39
            RichEditorListView.m21804$$Nest$fputsuppressSpansChanged(this.this$0, false);
            RichEditorHistory r74 = this.this$0.history;
            if (r74 == null) goto L37;
            r74.record();
        L37:
            RichEditorListView.m21788$$Nest$fgetdelegate(this.this$0).onContentChanged();
            return;
        L39:
            th = move-exception;
            RichEditorListView.m21804$$Nest$fputsuppressSpansChanged(this.this$0, false);
            throw th;
        L26:
            if (r14.getButton() == null) goto L28;
            r15 = this.existingSpan.getButton();
            goto L29
        L49:
            return;
        }

        public void dismissSelectionUi() {
            RichEditorListView.m21818$$Nest$mhideTextSelectionUi(this.this$0, true);
        }

        public String getLabel() {
            return RichTextStyle.plainOf(this.label);
        }

        public TL_keyboard.InlineButtonType getType() {
            RichInlineButtonSpan r0 = this.existingSpan;
            if (r0 != null) goto L5;
            return null;
        L5:
            if (r0.getButton() != null) goto L8;
            return null;
        L8:
            return this.existingSpan.getButton().type;
        }

        public void hideSelectionUi() {
            RichEditorListView.m21818$$Nest$mhideTextSelectionUi(this.this$0, false);
        }

        public void showInputDialog(String r1, String r2, String r3, boolean r4, boolean r5, EditTextCaption.InputDialogCallback r6) {
            this.editText.showInputDialog(r1, r2, r3, r4, r5, r6);
        }
    }

    public interface SelectionEdit {
        TL_iv.RichMessage extractRichMessage();

        void replaceWith(TL_iv.RichMessage r1);
    }

    /* renamed from: $r8$lambda$-R1mLv3Mt1O4kiscFWIEE7sSXGk, reason: not valid java name */
    public static /* synthetic */ void m21744$r8$lambda$R1mLv3Mt1O4kiscFWIEE7sSXGk(RichTableCell r2, int r3, RichEditor.Button[] r4, View r5) {
        r2.applyHorizontalAlign(r3);
        int r52 = 0;
    L4:
        if (r52 >= 3) goto L10;
        RichEditor.Button r0 = r4[r52];
        if (r52 != r3) goto L8;
        boolean r1 = true;
    L9:
        r0.setSelected(r1);
        r52 = r52 + 1;
        goto L4
    L8:
        r1 = false;
        goto L9
    }

    /* renamed from: $r8$lambda$14y9UOX2acK_d2o7MEHp-kriiqg, reason: not valid java name */
    public static /* synthetic */ void m21745$r8$lambda$14y9UOX2acK_d2o7MEHpkriiqg(RichEditorListView r0, int r1, float r2, RichEditText r3, int r4, int r5, Runnable r6, String r7) {
        r0.lambda$insertInlineMath$12(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$1NzJMr0e92O2iVo2y2oOhK9FbVc(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$73(r1);
    }

    public static /* synthetic */ void $r8$lambda$1PJfX7k0Id0cy_YHziFmEYEO_4I(RichEditorListView r0, RichTableCell r1) {
        r0.lambda$showTableCellMenu$28(r1);
    }

    /* renamed from: $r8$lambda$1jrt5NX59K4WT-LwNmQBJjC-mAI, reason: not valid java name */
    public static /* synthetic */ void m21746$r8$lambda$1jrt5NX59K4WTLwNmQBJjCmAI(RichEditorListView r0, RichTableCell r1) {
        r0.lambda$new$22(r1);
    }

    /* renamed from: $r8$lambda$2gwrS1jXvNtcXvLXq-SILM4zUZU, reason: not valid java name */
    public static /* synthetic */ void m21747$r8$lambda$2gwrS1jXvNtcXvLXqSILM4zUZU(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onCellEnter$51(r1);
    }

    /* renamed from: $r8$lambda$3IvT-kFTb9W3w9rzEN6h_Y13QGg, reason: not valid java name */
    public static /* synthetic */ void m21748$r8$lambda$3IvTkFTb9W3w9rzEN6h_Y13QGg(RichEditorListView r0, RichTableCell r1) {
        r0.lambda$showTableCellMenu$27(r1);
    }

    public static /* synthetic */ void $r8$lambda$3Z_gMKkRGcCvLW5Tc9OsJBihMqU(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onLanguageClick$61(r1);
    }

    public static /* synthetic */ void $r8$lambda$3l9YihfXVXdEf9VhDC5eae8WCaw(RichEditorListView r0, int r1) {
        r0.lambda$onMathClicked$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$4SOWImOuljpeFd7CmzsXvW0PlUM(RichEditorListView r0, int r1, int r2, int r3) {
        r0.lambda$tryExtendSelectionAcrossCells$81(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$4pPqXbBYixSPNrYgQKSvgGMhsSU(RichEditorListView r0, RichEditText r1, RichInlineButtonSpan r2, boolean r3) {
        r0.lambda$prepareEditText$17(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$6HDtkFuLwK5v9eidtctjsaHqc74(RichEditorListView r0, int r1, String r2, boolean r3, int r4, int r5, int r6, int r7, int r8, BlockRow r9) {
        r0.lambda$attachExternalMedia$45(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$8EdSyb50QqyzDGfUG5XghPaboJI(RichEditorListView r0, int r1) {
        r0.lambda$onMathClicked$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$8Idf_18mBxdJj7ajSOIbfXY2knA(RichEditorListView r0, BlockRow r1, int r2) {
        r0.lambda$onCellBackspaceAtStart$60(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8cWr0B0XNsaHy2kOsKX_Av_6MV4(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onCellBackspaceAtStart$55(r1);
    }

    /* renamed from: $r8$lambda$92-79eJivRmRIzoGkXD9xoCsa_4, reason: not valid java name */
    public static /* synthetic */ void m21749$r8$lambda$9279eJivRmRIzoGkXD9xoCsa_4(RichEditorListView r0, RichTableCell r1) {
        r0.lambda$showTableCellMenu$32(r1);
    }

    public static /* synthetic */ void $r8$lambda$9I0te9caBMDlpQMIDPFLSLbJhsU(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$72(r1);
    }

    public static /* synthetic */ void $r8$lambda$BHcqZ71xaJ7H4kA_Dfpc6SBypmU(RichEditorListView r0, RichEditorHistory.FocusState r1) {
        r0.lambda$restoreFromHistory$39(r1);
    }

    /* renamed from: $r8$lambda$C8P11SOe-ahtNk1MzoSfwMth8m0, reason: not valid java name */
    public static /* synthetic */ void m21750$r8$lambda$C8P11SOeahtNk1MzoSfwMth8m0(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onCellBackspaceAtStart$57(r1);
    }

    /* renamed from: $r8$lambda$CLedFpIXP_reNSoW9-Ar60eK6Do, reason: not valid java name */
    public static /* synthetic */ void m21751$r8$lambda$CLedFpIXP_reNSoW9Ar60eK6Do(RichEditorListView r0, BlockRow r1, String r2) {
        r0.lambda$onLanguageClick$62(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$CqN939CsSg9suhx0KZ57y_4Qm0A(RichEditorListView r0, Uri r1) {
        r0.lambda$attachDocument$43(r1);
    }

    /* renamed from: $r8$lambda$CybUfYa0mocY3G-uvQO092oVjvw, reason: not valid java name */
    public static /* synthetic */ void m21752$r8$lambda$CybUfYa0mocY3GuvQO092oVjvw(ItemOptions r0, TableModel r1, RichTableCell r2, ActionBarMenuSubItem[] r3) {
        r0.dontDismiss();
        boolean r02 = !r1.block.bordered;
        r2.applyBordered(r02);
        r3[0].setChecked(r02);
    }

    public static /* synthetic */ void $r8$lambda$DIIeZLAvRRxELOg_XfqL01Nzd6A(RichEditText r2, int r3) {
        r2.requestEditFocus();
        r2.setSelection(Math.max(0, Math.min(r3, r2.length())));
    }

    public static /* synthetic */ void $r8$lambda$DqwKooP9xieIxk9tozqjSrJCklc(RichEditorListView r0, int r1, int r2) {
        r0.lambda$applyInitialSelection$4(r1, r2);
    }

    /* renamed from: $r8$lambda$E1fa-QUJCyQkGw7vROEyGYzcZTM, reason: not valid java name */
    public static /* synthetic */ void m21753$r8$lambda$E1faQUJCyQkGw7vROEyGYzcZTM(RichEditorListView r0, RecyclerView.ItemAnimator r1) {
        r0.lambda$onCellBackspaceAtStart$59(r1);
    }

    public static /* synthetic */ void $r8$lambda$EY3b3fedSIS5IkYEUo4OzLPIcLM(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$78(r1);
    }

    /* renamed from: $r8$lambda$F4QLQguBylVIpWLkyH_-4-G701s, reason: not valid java name */
    public static /* synthetic */ void m21754$r8$lambda$F4QLQguBylVIpWLkyH_4G701s(RichEditorListView[] r1, ArrayList r2, UniversalAdapter r3) {
        RichEditorListView r12 = r1[0];
        if (r12 == null) goto L6;
        r12.fillItems(r2, r3);
        return;
    }

    /* renamed from: $r8$lambda$FWLLACc2-hByxArnJOJZhy0sM1w, reason: not valid java name */
    public static /* synthetic */ void m21755$r8$lambda$FWLLACc2hByxArnJOJZhy0sM1w(RichEditText r2, int r3) {
        r2.requestEditFocus();
        r2.setSelection(Math.max(0, Math.min(r3, r2.length())));
    }

    public static /* synthetic */ void $r8$lambda$GAZucynl797XrYk2HQU9GhA1FkA(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$76(r1);
    }

    public static /* synthetic */ void $r8$lambda$Gr3MGndUuvTDbIv7Z9rlNLgQhYM(RichEditorListView r0, int r1, ArrayList r2) {
        r0.onRowsReordered(r1, r2);
    }

    /* renamed from: $r8$lambda$HPB1VUm6nAgwtxVz1P6Lvbo-PxA, reason: not valid java name */
    public static /* synthetic */ void m21756$r8$lambda$HPB1VUm6nAgwtxVz1P6LvboPxA(RichEditorListView r0, TL_iv.pageBlockMath r1, String r2) {
        r0.lambda$openMathEditor$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$HhdO03eGBTnaiKwCufo5ZcAoHCU(RichEditorListView r0, Uri r1, boolean r2, String r3, int r4, BlockRow r5) {
        r0.lambda$attachExternalMedia$46(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$Ih7Abho-zStsHPdHwfg6o46qu7E, reason: not valid java name */
    public static /* synthetic */ void m21757$r8$lambda$Ih7AbhozStsHPdHwfg6o46qu7E(RichEditorListView r0, BlockRow r1, int r2) {
        r0.lambda$spliceBlocksInto$82(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IpmsjOQczIhFtoNOpWnSBKe1ljw(RichEditorListView r0, BlockRow r1) {
        r0.lambda$insertPreparedRow$44(r1);
    }

    public static /* synthetic */ void $r8$lambda$JB0s8TQ1bxR2CIzNaNdrsMM00tE(RichEditorListView r0, RichTableCell r1) {
        r0.lambda$showTableCellMenu$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$JNrWORQbEHchuzeA0OGP5bucerA(RichEditorListView r0, RichTableCell r1) {
        r0.lambda$showTableCellMenu$30(r1);
    }

    /* renamed from: $r8$lambda$Jjnlt_7hiY0zD1X8U-8pmQHzdoU, reason: not valid java name */
    public static /* synthetic */ void m21758$r8$lambda$Jjnlt_7hiY0zD1X8U8pmQHzdoU(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$70(r1);
    }

    /* renamed from: $r8$lambda$KM9he--uL71moFHEX3MNBG1JlhU, reason: not valid java name */
    public static /* synthetic */ void m21759$r8$lambda$KM9heuL71moFHEX3MNBG1JlhU(RichEditText r1, int r2) {
        r1.requestEditFocus();
        r1.setSelection(Math.min(r2, r1.length()));
    }

    /* renamed from: $r8$lambda$LypLLg_BvAlu8-fAoqwAPJACchQ, reason: not valid java name */
    public static /* synthetic */ void m21760$r8$lambda$LypLLg_BvAlu8fAoqwAPJACchQ(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onCaptionEnter$48(r1);
    }

    /* renamed from: $r8$lambda$MNzk4LVyxKlEpmxAnd-fokJfQ6I, reason: not valid java name */
    public static /* synthetic */ void m21761$r8$lambda$MNzk4LVyxKlEpmxAndfokJfQ6I(RichEditorListView r0, BlockRow r1) {
        r0.lambda$toggleQuoteOnSelection$64(r1);
    }

    public static /* synthetic */ void $r8$lambda$MZWR0gmlmfXBF07e6s09a3a0UVk(RichEditorListView r0, int r1, int r2) {
        r0.lambda$onMathClicked$6(r1, r2);
    }

    /* renamed from: $r8$lambda$O-LH69tJA41hrVPJQ2wfJk1HUJE, reason: not valid java name */
    public static /* synthetic */ void m21762$r8$lambda$OLH69tJA41hrVPJQ2wfJk1HUJE(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryEscalateSelectionFromCaret$79(r1);
    }

    public static /* synthetic */ void $r8$lambda$OQQYgOOJ7ALv7t2uiuPoqvQMHSY(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onDetailsTitleEnter$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$Q9vORuWZdiOkGUmG1XhvaWMwYwQ(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onCellEnter$49(r1);
    }

    /* renamed from: $r8$lambda$QtcTdGJUTLG-x_lKmmvIcPlcRjg, reason: not valid java name */
    public static /* synthetic */ void m21763$r8$lambda$QtcTdGJUTLGx_lKmmvIcPlcRjg(RichEditorListView r0, int r1) {
        r0.lambda$onMathClicked$9(r1);
    }

    /* renamed from: $r8$lambda$R77XqUMmJP4rvDfHa-04uCmaMlA, reason: not valid java name */
    public static /* synthetic */ void m21764$r8$lambda$R77XqUMmJP4rvDfHa04uCmaMlA(RichEditorListView r0, BlockRow r1) {
        r0.lambda$focusForDraftInternal$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$RLyHp8EciY1NgMnDphzoPPznQbk(RichEditorListView r0, BlockRow r1, int r2) {
        r0.lambda$restoreCaret$67(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$RzpXwqRVgWIgSzuWyEiurlNbMHo(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onCellBackspaceAtStart$56(r1);
    }

    /* renamed from: $r8$lambda$SsvgohL4I6uNqDcHlbr-i7lEqBM, reason: not valid java name */
    public static /* synthetic */ boolean m21765$r8$lambda$SsvgohL4I6uNqDcHlbri7lEqBM(RichEditorListView r0) {
        return r0.focusForDraftInternal();
    }

    /* renamed from: $r8$lambda$T2KYm-frLyWAGrtkiFQ8_8lO1cw, reason: not valid java name */
    public static /* synthetic */ void m21766$r8$lambda$T2KYmfrLyWAGrtkiFQ8_8lO1cw(RichEditText r2, int r3) {
        r2.requestEditFocus();
        r2.setSelection(Math.max(0, Math.min(r3, r2.length())));
    }

    /* renamed from: $r8$lambda$TWa-bbO5Rtp5eeE40Wbb4sU5yZU, reason: not valid java name */
    public static /* synthetic */ void m21767$r8$lambda$TWabbO5Rtp5eeE40Wbb4sU5yZU(RichTableCell r2, int r3, RichEditor.Button[] r4, View r5) {
        r2.applyVerticalAlign(r3);
        int r52 = 0;
    L4:
        if (r52 >= 3) goto L10;
        RichEditor.Button r0 = r4[r52];
        if (r52 != r3) goto L8;
        boolean r1 = true;
    L9:
        r0.setSelected(r1);
        r52 = r52 + 1;
        goto L4
    L8:
        r1 = false;
        goto L9
    }

    /* renamed from: $r8$lambda$V-rWidhdVWsASFQuJF2NSj5ZlJY, reason: not valid java name */
    public static /* synthetic */ void m21768$r8$lambda$VrWidhdVWsASFQuJF2NSj5ZlJY(RichEditorListView r0, BlockRow r1) {
        r0.lambda$applyQuote$65(r1);
    }

    /* renamed from: $r8$lambda$VJRba_2Ij-4IPLrHc-gaYk0PK8A, reason: not valid java name */
    public static /* synthetic */ void m21769$r8$lambda$VJRba_2Ij4IPLrHcgaYk0PK8A(ItemOptions r0, TableModel r1, RichTableCell r2, ActionBarMenuSubItem[] r3) {
        r0.dontDismiss();
        boolean r02 = !r1.block.compact;
        r2.applyCompact(r02);
        r3[1].setChecked(r02);
    }

    /* renamed from: $r8$lambda$XvQnd0kyohtvNuIOxR19Ie8-ixU, reason: not valid java name */
    public static /* synthetic */ void m21770$r8$lambda$XvQnd0kyohtvNuIOxR19Ie8ixU(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$68(r1);
    }

    public static /* synthetic */ void $r8$lambda$Ycu286bM9o7zqwqPkaqOd5xdhkI(RichEditorListView r0, RichTableCell r1, boolean r2) {
        r0.lambda$showTableCellMenu$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Z4XLG7Cgct7baB4J55PDhFdNVcw(RichEditorListView r0, BlockRow r1) {
        r0.lambda$deleteDetails$20(r1);
    }

    public static /* synthetic */ void $r8$lambda$_00SKdFCnW0pc3Jng3ziWkRx0i8(RichEditorListView r0, String r1) {
        r0.lambda$attachDocument$42(r1);
    }

    public static /* synthetic */ void $r8$lambda$aBDTD5zf1EyO_3UBO_27M4pDOkE(RichEditorListView r0, View r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$aVDggCD-wua2c4KuHAvqD-17JAI, reason: not valid java name */
    public static /* synthetic */ void m21771$r8$lambda$aVDggCDwua2c4KuHAvqD17JAI(RichEditorListView r0) {
        r0.lambda$refreshSelectionHighlight$5();
    }

    public static /* synthetic */ void $r8$lambda$bRuz2QXJ8b4Mf5jQRvMH3qCCPXU(RichEditorListView r0) {
        r0.lambda$handleSelectionTouch$13();
    }

    public static /* synthetic */ void $r8$lambda$bY1F28Vza1kkUUGX8PNC95yhnUk(RichEditorListView r0, BlockRow r1) {
        r0.lambda$insertParagraphAfterQuote$18(r1);
    }

    /* renamed from: $r8$lambda$dD1GsX1tzCx9n-OYHymfRK_Ysqg, reason: not valid java name */
    public static /* synthetic */ void m21772$r8$lambda$dD1GsX1tzCx9nOYHymfRK_Ysqg(RichEditorListView r0, BlockRow r1) {
        r0.lambda$transformRow$66(r1);
    }

    /* renamed from: $r8$lambda$ev7qvzu3nhFaXt4G-B6-4eZygZI, reason: not valid java name */
    public static /* synthetic */ void m21773$r8$lambda$ev7qvzu3nhFaXt4GB64eZygZI(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onCellBackspaceAtStart$54(r1);
    }

    /* renamed from: $r8$lambda$gXLntewoFX5ZBlI9tbY0-C1YOUI, reason: not valid java name */
    public static /* synthetic */ void m21774$r8$lambda$gXLntewoFX5ZBlI9tbY0C1YOUI(RichTableCell r1, TL_iv.pageTableCell r2, int r3) {
        RichTableCellHost r12 = r1.getGrid().hostForAnchor(r2);
        if (r12 != null) goto L5;
        return;
    L5:
        r12.editText.requestEditFocus();
        int r22 = r12.editText.length();
        r12.editText.setSelection(Math.max(0, Math.min(r3, r22)));
    }

    /* renamed from: $r8$lambda$hkYSgSvJkzMpf-kA-vWm8t7sMsg, reason: not valid java name */
    public static /* synthetic */ void m21775$r8$lambda$hkYSgSvJkzMpfkAvWm8t7sMsg(RichEditorListView r0, RichTableCell r1) {
        r0.lambda$showTableCellMenu$33(r1);
    }

    public static /* synthetic */ void $r8$lambda$i71gNpfQgW4N5pgIbLn_B2gQKEc(RichEditorListView r0, RichTableCell r1) {
        r0.lambda$showTableCellMenu$34(r1);
    }

    /* renamed from: $r8$lambda$idfrdp6GA5fASbLWZ63AEL-l0-A, reason: not valid java name */
    public static /* synthetic */ void m21776$r8$lambda$idfrdp6GA5fASbLWZ63AELl0A(RichEditorListView r0, RecyclerView.ItemAnimator r1) {
        r0.lambda$onCellEnter$52(r1);
    }

    /* renamed from: $r8$lambda$k4RxSKrXlh9JB-iyulEAbmaE_Bo, reason: not valid java name */
    public static /* synthetic */ void m21777$r8$lambda$k4RxSKrXlh9JBiyulEAbmaE_Bo(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onTapBelowContent$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$kE66A9dGN_82SqutVHJLmAKZanc(RichEditorListView r0) {
        r0.lambda$showSelectedUrlDialog$10();
    }

    public static /* synthetic */ void $r8$lambda$l3A3peloUwB9oPG5Q_wTuiSUH5Q(RichEditorListView[] r1, UItem r2, View r3, Integer r4, Float r5, Float r6) {
        RichEditorListView r12 = r1[0];
        if (r12 == null) goto L6;
        r12.onItemClick(r2, r3, r4.intValue(), r5.floatValue(), r6.floatValue());
        return;
    }

    /* renamed from: $r8$lambda$nQ8p1i7jqk2zHNVH65iAt-qVwOI, reason: not valid java name */
    public static /* synthetic */ void m21778$r8$lambda$nQ8p1i7jqk2zHNVH65iAtqVwOI(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryEscalateSelectionFromCaret$80(r1);
    }

    public static /* synthetic */ void $r8$lambda$omq7LVwXwd60KjKIsDCSG3Lot3M(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onCellEnter$53(r1);
    }

    public static /* synthetic */ void $r8$lambda$pkzLPxfqBs9gdYtBsYIOcVzyHiE(RichEditorListView r0, BlockRow r1, int r2) {
        r0.lambda$onCellBackspaceAtStart$58(r1, r2);
    }

    /* renamed from: $r8$lambda$q-RzYNhqvxhJYhmBqECNz4iodZk, reason: not valid java name */
    public static /* synthetic */ void m21779$r8$lambda$qRzYNhqvxhJYhmBqECNz4iodZk(RichEditorListView r0, int r1) {
        r0.lambda$onLayout$3(r1);
    }

    /* renamed from: $r8$lambda$s-bwvRIk48E5cmRKUiaar1ZeXDg, reason: not valid java name */
    public static /* synthetic */ void m21780$r8$lambda$sbwvRIk48E5cmRKUiaar1ZeXDg(RichEditorListView r0, BlockRow r1) {
        r0.lambda$removeSelectedBlocks$84(r1);
    }

    public static /* synthetic */ void $r8$lambda$sYTkmYgplJKg9ON9EAf8WUQ_Nkk(RichEditorListView r0, String r1) {
        r0.lambda$openLatexEditorAndAdd$38(r1);
    }

    public static /* synthetic */ void $r8$lambda$sq_lS2HCEwBmwQMuR7aCDxcVlmk(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$75(r1);
    }

    public static /* synthetic */ void $r8$lambda$svc8ypWVj9w_B6psr7P74Prmiuk(RichEditorListView r0, BlockRow r1) {
        r0.lambda$addBlock$40(r1);
    }

    public static /* synthetic */ void $r8$lambda$t8ei7mjHph3QRUWx3WUEwdMrkHU(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$71(r1);
    }

    public static /* synthetic */ void $r8$lambda$tAg9gEbo7sNRJqZSnPCVe44rT_o(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onCellEnter$50(r1);
    }

    public static /* synthetic */ void $r8$lambda$tZ5DxV61fIc0MZUTFGDV8oCbzH4(RichEditorListView r0, RichTableCell r1) {
        r0.lambda$showTableCellMenu$26(r1);
    }

    /* renamed from: $r8$lambda$teu9j0cx7oOZguCyxeNkh0C-1mM, reason: not valid java name */
    public static /* synthetic */ void m21781$r8$lambda$teu9j0cx7oOZguCyxeNkh0C1mM(RichEditorListView r0, ItemOptions r1, RichTableCell r2) {
        r0.lambda$showTableCellMenu$37(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$tquYHwM007gs6IPgMfTfwGwZwjE(RichEditText r2, int r3) {
        r2.requestEditFocus();
        r2.setSelection(Math.max(0, Math.min(r3, r2.length())));
    }

    /* renamed from: $r8$lambda$uHrXwlnnOscoXVlvXT8l-rBhqVo, reason: not valid java name */
    public static /* synthetic */ void m21782$r8$lambda$uHrXwlnnOscoXVlvXT8lrBhqVo(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$77(r1);
    }

    public static /* synthetic */ void $r8$lambda$wLc82rdoOiBOTDxlsTvpKxRO0cc(RichEditorListView r0, BlockRow r1) {
        r0.lambda$onTapAboveContent$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$wrLg5SdJflIce5P4kF_Ps3bhEyQ(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$69(r1);
    }

    /* renamed from: $r8$lambda$xlinW4-cy4yPJ17n1YvAOBz_b4Q, reason: not valid java name */
    public static /* synthetic */ void m21783$r8$lambda$xlinW4cy4yPJ17n1YvAOBz_b4Q(RichEditorListView r0, BlockRow r1) {
        r0.lambda$tryPlainArrowAcrossCells$74(r1);
    }

    /* renamed from: $r8$lambda$xqVA0PNOKV78JbjJJ0yb028U-5M, reason: not valid java name */
    public static /* synthetic */ void m21784$r8$lambda$xqVA0PNOKV78JbjJJ0yb028U5M(RichEditorListView r0, BlockRow r1, int r2) {
        r0.lambda$applyEditRange$83(r1, r2);
    }

    /* renamed from: $r8$lambda$z5VVRzwmutpLzFlb3mGg1lrd-vU, reason: not valid java name */
    public static /* synthetic */ void m21785$r8$lambda$z5VVRzwmutpLzFlb3mGg1lrdvU(RichEditorListView r0, BlockRow r1) {
        r0.lambda$insertDetails$41(r1);
    }

    public static /* synthetic */ void $r8$lambda$zCSApFhKX_Duy6YYnbEKBeRwSGI(RichEditorListView r0, RichTableCell r1) {
        r0.lambda$showTableCellMenu$31(r1);
    }

    public static /* synthetic */ void $r8$lambda$zLoqUXznFdsUXT0BIRv5W1WuG98(RichEditorListView r0, BlockRow r1, BlockRow r2) {
        r0.lambda$toggleQuoteOnSelection$63(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zhH5FCego3cXLwhEiShk7rmjFxI(RichEditorListView r0, BlockRow r1, int r2) {
        r0.lambda$deleteAcrossDetails$87(r1, r2);
    }

    /* renamed from: -$$Nest$fgetconverters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IdentityHashMap m21786$$Nest$fgetconverters(RichEditorListView r0) {
        return r0.converters;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21787$$Nest$fgetcurrentAccount(RichEditorListView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21788$$Nest$fgetdelegate(RichEditorListView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetfileRefParentObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m21789$$Nest$fgetfileRefParentObject(RichEditorListView r0) {
        return r0.fileRefParentObject;
    }

    /* renamed from: -$$Nest$fgetpendingTapDismiss, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21790$$Nest$fgetpendingTapDismiss(RichEditorListView r0) {
        return r0.pendingTapDismiss;
    }

    /* renamed from: -$$Nest$fgetpendingTapRawX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21791$$Nest$fgetpendingTapRawX(RichEditorListView r0) {
        return r0.pendingTapRawX;
    }

    /* renamed from: -$$Nest$fgetpendingTapRawY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21792$$Nest$fgetpendingTapRawY(RichEditorListView r0) {
        return r0.pendingTapRawY;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m21793$$Nest$fgetresourcesProvider(RichEditorListView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetrestoreFocusCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21794$$Nest$fgetrestoreFocusCell(RichEditorListView r0) {
        return r0.restoreFocusCell;
    }

    /* renamed from: -$$Nest$fgetrestoreFocusChildPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21795$$Nest$fgetrestoreFocusChildPosition(RichEditorListView r0) {
        return r0.restoreFocusChildPosition;
    }

    /* renamed from: -$$Nest$fgetrestoreFocusOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21796$$Nest$fgetrestoreFocusOffset(RichEditorListView r0) {
        return r0.restoreFocusOffset;
    }

    /* renamed from: -$$Nest$fgetuploaders, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IdentityHashMap m21797$$Nest$fgetuploaders(RichEditorListView r0) {
        return r0.uploaders;
    }

    /* renamed from: -$$Nest$fputpendingTapDismiss, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21798$$Nest$fputpendingTapDismiss(RichEditorListView r0, boolean r1) {
        r0.pendingTapDismiss = r1;
    }

    /* renamed from: -$$Nest$fputpendingTapRawX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21799$$Nest$fputpendingTapRawX(RichEditorListView r0, float r1) {
        r0.pendingTapRawX = r1;
    }

    /* renamed from: -$$Nest$fputpendingTapRawY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21800$$Nest$fputpendingTapRawY(RichEditorListView r0, float r1) {
        r0.pendingTapRawY = r1;
    }

    /* renamed from: -$$Nest$fputrestoreFocusCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21801$$Nest$fputrestoreFocusCell(RichEditorListView r0, int r1) {
        r0.restoreFocusCell = r1;
    }

    /* renamed from: -$$Nest$fputrestoreFocusChildPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21802$$Nest$fputrestoreFocusChildPosition(RichEditorListView r0, int r1) {
        r0.restoreFocusChildPosition = r1;
    }

    /* renamed from: -$$Nest$fputrestoreFocusOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21803$$Nest$fputrestoreFocusOffset(RichEditorListView r0, int r1) {
        r0.restoreFocusOffset = r1;
    }

    /* renamed from: -$$Nest$fputsuppressSpansChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21804$$Nest$fputsuppressSpansChanged(RichEditorListView r0, boolean r1) {
        r0.suppressSpansChanged = r1;
    }

    /* renamed from: -$$Nest$mcancelAudioUpload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21805$$Nest$mcancelAudioUpload(RichEditorListView r0, BlockRow r1) {
        r0.cancelAudioUpload(r1);
    }

    /* renamed from: -$$Nest$mcancelDocumentUpload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21806$$Nest$mcancelDocumentUpload(RichEditorListView r0, BlockRow r1) {
        r0.cancelDocumentUpload(r1);
    }

    /* renamed from: -$$Nest$mcancelMediaUpload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21807$$Nest$mcancelMediaUpload(RichEditorListView r0, BlockRow r1, MediaUploadState r2) {
        r0.cancelMediaUpload(r1, r2);
    }

    /* renamed from: -$$Nest$mcaptureFocusState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichEditorHistory.FocusState m21808$$Nest$mcaptureFocusState(RichEditorListView r0) {
        return r0.captureFocusState();
    }

    /* renamed from: -$$Nest$mcollectMediaDocuments, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m21809$$Nest$mcollectMediaDocuments(RichEditorListView r0, int r1, int r2) {
        return r0.collectMediaDocuments(r1, r2);
    }

    /* renamed from: -$$Nest$mcollectMediaPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m21810$$Nest$mcollectMediaPhotos(RichEditorListView r0, int r1, int r2) {
        return r0.collectMediaPhotos(r1, r2);
    }

    /* renamed from: -$$Nest$mcopyHelperSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21811$$Nest$mcopyHelperSelection(RichEditorListView r0) {
        r0.copyHelperSelection();
    }

    /* renamed from: -$$Nest$mcutHelperSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21812$$Nest$mcutHelperSelection(RichEditorListView r0) {
        r0.cutHelperSelection();
    }

    /* renamed from: -$$Nest$mdeleteDetails, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21813$$Nest$mdeleteDetails(RichEditorListView r0, BlockRow r1) {
        r0.deleteDetails(r1);
    }

    /* renamed from: -$$Nest$mfinishEditTextActionModes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21814$$Nest$mfinishEditTextActionModes(RichEditorListView r0) {
        r0.finishEditTextActionModes();
    }

    /* renamed from: -$$Nest$mflattenRange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m21815$$Nest$mflattenRange(RichEditorListView r0, int r1, int r2, boolean r3) {
        return r0.flattenRange(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mhideEditTextActionModes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21816$$Nest$mhideEditTextActionModes(RichEditorListView r0) {
        r0.hideEditTextActionModes();
    }

    /* renamed from: -$$Nest$mhideKeyboardIfFocusScrolledAway, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21817$$Nest$mhideKeyboardIfFocusScrolledAway(RichEditorListView r0) {
        r0.hideKeyboardIfFocusScrolledAway();
    }

    /* renamed from: -$$Nest$mhideTextSelectionUi, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21818$$Nest$mhideTextSelectionUi(RichEditorListView r0, boolean r1) {
        r0.hideTextSelectionUi(r1);
    }

    /* renamed from: -$$Nest$minsertParagraphAfterQuote, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21819$$Nest$minsertParagraphAfterQuote(RichEditorListView r0, BlockRow r1) {
        r0.insertParagraphAfterQuote(r1);
    }

    /* renamed from: -$$Nest$minvalidateAudioCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21820$$Nest$minvalidateAudioCell(RichEditorListView r0, BlockRow r1) {
        r0.invalidateAudioCell(r1);
    }

    /* renamed from: -$$Nest$minvalidateDocumentCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21821$$Nest$minvalidateDocumentCell(RichEditorListView r0, BlockRow r1) {
        r0.invalidateDocumentCell(r1);
    }

    /* renamed from: -$$Nest$minvalidateMediaCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21822$$Nest$minvalidateMediaCell(RichEditorListView r0, BlockRow r1) {
        r0.invalidateMediaCell(r1);
    }

    /* renamed from: -$$Nest$misWholeDocumentSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21823$$Nest$misWholeDocumentSelected(RichEditorListView r0) {
        return r0.isWholeDocumentSelected();
    }

    /* renamed from: -$$Nest$monCaptionEnter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21824$$Nest$monCaptionEnter(RichEditorListView r0, BlockRow r1) {
        r0.onCaptionEnter(r1);
    }

    /* renamed from: -$$Nest$monCellBackspaceAtStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21825$$Nest$monCellBackspaceAtStart(RichEditorListView r0, BlockRow r1, boolean r2) {
        return r0.onCellBackspaceAtStart(r1, r2);
    }

    /* renamed from: -$$Nest$monCellEnter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21826$$Nest$monCellEnter(RichEditorListView r0, BlockRow r1) {
        r0.onCellEnter(r1);
    }

    /* renamed from: -$$Nest$monCellPaste, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21827$$Nest$monCellPaste(RichEditorListView r0, BlockRow r1, RichEditText r2) {
        return r0.onCellPaste(r1, r2);
    }

    /* renamed from: -$$Nest$monCellSpansChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21828$$Nest$monCellSpansChanged(RichEditorListView r0) {
        r0.onCellSpansChanged();
    }

    /* renamed from: -$$Nest$monChecklistToggle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21829$$Nest$monChecklistToggle(RichEditorListView r0, BlockRow r1, boolean r2) {
        r0.onChecklistToggle(r1, r2);
    }

    /* renamed from: -$$Nest$monDetailsTitleEnter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21830$$Nest$monDetailsTitleEnter(RichEditorListView r0, BlockRow r1) {
        r0.onDetailsTitleEnter(r1);
    }

    /* renamed from: -$$Nest$monLanguageClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21831$$Nest$monLanguageClick(RichEditorListView r0, BlockRow r1, View r2) {
        r0.onLanguageClick(r1, r2);
    }

    /* renamed from: -$$Nest$mpasteAtHelperSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21832$$Nest$mpasteAtHelperSelection(RichEditorListView r0) {
        r0.pasteAtHelperSelection();
    }

    /* renamed from: -$$Nest$mprepareEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21833$$Nest$mprepareEditText(RichEditorListView r0, RichEditText r1) {
        r0.prepareEditText(r1);
    }

    /* renamed from: -$$Nest$mrefreshMediaCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21834$$Nest$mrefreshMediaCell(RichEditorListView r0, BlockRow r1) {
        r0.refreshMediaCell(r1);
    }

    /* renamed from: -$$Nest$mremoveMediaFromRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21835$$Nest$mremoveMediaFromRow(RichEditorListView r0, BlockRow r1, MediaUploadState r2) {
        r0.removeMediaFromRow(r1, r2);
    }

    /* renamed from: -$$Nest$mrenumberAllRuns, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21836$$Nest$mrenumberAllRuns(RichEditorListView r0) {
        r0.renumberAllRuns();
    }

    /* renamed from: -$$Nest$mreplaceHelperSelectionWith, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21837$$Nest$mreplaceHelperSelectionWith(RichEditorListView r0, String r1) {
        r0.replaceHelperSelectionWith(r1);
    }

    /* renamed from: -$$Nest$mresolveLoadedMedia, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21838$$Nest$mresolveLoadedMedia(RichEditorListView r0, BlockRow r1) {
        r0.resolveLoadedMedia(r1);
    }

    /* renamed from: -$$Nest$mrestoreFocusAt, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21839$$Nest$mrestoreFocusAt(RichEditorListView r0, int r1, int r2, int r3) {
        r0.restoreFocusAt(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mrestoreFocusAtScreenPoint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21840$$Nest$mrestoreFocusAtScreenPoint(RichEditorListView r0, float r1, float r2) {
        return r0.restoreFocusAtScreenPoint(r1, r2);
    }

    /* renamed from: -$$Nest$mrestoreFromHistory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21841$$Nest$mrestoreFromHistory(RichEditorListView r0, List r1, RichEditorHistory.FocusState r2) {
        r0.restoreFromHistory(r1, r2);
    }

    /* renamed from: -$$Nest$msetEditTextsLocked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21842$$Nest$msetEditTextsLocked(RichEditorListView r0, boolean r1) {
        r0.setEditTextsLocked(r1);
    }

    /* renamed from: -$$Nest$msliceClone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_iv.PageBlock m21843$$Nest$msliceClone(RichEditorListView r0, BlockRow r1, int r2, int r3) {
        return r0.sliceClone(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mstartMediaUpload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21844$$Nest$mstartMediaUpload(RichEditorListView r0, BlockRow r1, MediaUploadState r2, String r3, boolean r4, int r5, int r6, int r7) {
        r0.startMediaUpload(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: -$$Nest$mstyledTextOf, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m21845$$Nest$mstyledTextOf(RichEditorListView r0, BlockRow r1) {
        return r0.styledTextOf(r1);
    }

    /* renamed from: -$$Nest$mswitchGalleryMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21846$$Nest$mswitchGalleryMode(RichEditorListView r0, BlockRow r1) {
        r0.switchGalleryMode(r1);
    }

    /* renamed from: -$$Nest$mtoggleDetails, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21847$$Nest$mtoggleDetails(RichEditorListView r0, BlockRow r1) {
        r0.toggleDetails(r1);
    }

    /* renamed from: -$$Nest$mtoggleMediaSpoiler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21848$$Nest$mtoggleMediaSpoiler(RichEditorListView r0, BlockRow r1, MediaUploadState r2) {
        r0.toggleMediaSpoiler(r1, r2);
    }

    /* renamed from: -$$Nest$mtransformRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21849$$Nest$mtransformRow(RichEditorListView r0, BlockRow r1, TL_iv.PageBlock r2, int r3, int r4, boolean r5, boolean r6) {
        r0.transformRow(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: -$$Nest$mtryEscalateSelectAll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21850$$Nest$mtryEscalateSelectAll(RichEditorListView r0) {
        return r0.tryEscalateSelectAll();
    }

    /* renamed from: -$$Nest$smcycleButtonStyle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21851$$Nest$smcycleButtonStyle(TL_keyboard.PageButton r0) {
        cycleButtonStyle(r0);
    }

    /* renamed from: -$$Nest$smisFormattable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21852$$Nest$smisFormattable(TL_iv.PageBlock r0) {
        return isFormattable(r0);
    }

    /* renamed from: -$$Nest$smitemBlockFor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_iv.PageBlock m21853$$Nest$smitemBlockFor(BlockRow r0, MediaUploadState r1) {
        return itemBlockFor(r0, r1);
    }

    static {
        EMPTY_QUOTE_IDS = new ArrayList();
    }

    public RichEditorListView(Context r8, int r9, Theme.ResourcesProvider r10, Delegate r11) {
        this(r8, r9, r10, r11, new RichEditorListView[1]);
    }

    private RichEditorListView(Context r10, int r11, Theme.ResourcesProvider r12, Delegate r13, RichEditorListView[] r14) {
        super(r10, r11, 0, false, new RichEditorListView$$ExternalSyntheticLambda34(r14), new RichEditorListView$$ExternalSyntheticLambda35(r14), null, r12);
        this.adaptiveLinkDialogs = true;
        this.rows = new ArrayList();
        this.quoteAuthors = new HashMap();
        this.restoreFocusCell = -1;
        this.restoreFocusOffset = -1;
        this.restoreFocusChildPosition = 0;
        this.imeFocusListener = new RichEditorListView$$ExternalSyntheticLambda36(this);
        this.allowTapAboveContent = true;
        this.buttonRowDelegate = new AnonymousClass5(this);
        this.dividerDelegate = new RichEditorListView$$ExternalSyntheticLambda37(this);
        this.mediaDelegate = new AnonymousClass6(this);
        this.audioDelegate = new AnonymousClass7(this);
        this.documentDelegate = new AnonymousClass8(this);
        this.uploaders = new IdentityHashMap();
        this.converters = new IdentityHashMap();
        this.mapDelegate = new AnonymousClass9(this);
        this.mathDelegate = new AnonymousClass10(this);
        this.quoteAuthorDelegate = new AnonymousClass11(this);
        this.tableDelegate = new AnonymousClass12(this);
        this.detailsDelegate = new AnonymousClass13(this);
        this.dotSelectedRow = -1;
        this.dotSelectedCol = -1;
        this.cellSelectionListener = new RichEditorListView$$ExternalSyntheticLambda38(this);
        this.cellDelegate = new AnonymousClass14(this);
        AnonymousClass15 r122 = new AnonymousClass15(this);
        this.historyDelegate = r122;
        this.itemRows = new ArrayList();
        this.lastExternalImageId = -1;
        r14[0] = this;
        this.currentAccount = r11;
        this.resourcesProvider = r12;
        this.delegate = r13;
        this.adapter.setApplyBackground(false);
        setClipToPadding(false);
        setClipChildren(false);
        listenReorder(new RichEditorListView$$ExternalSyntheticLambda39(this));
        setReorderLongPressEnabled(false);
        allowReorder(true);
        AnonymousClass1 r9 = new AnonymousClass1(this, r13);
        this.textSelectionHelper = r9;
        r9.setParentView(this);
        TextSelectionHelper.ArticleTextSelectionHelper r92 = this.textSelectionHelper;
        r92.layoutManager = this.layoutManager;
        TextSelectionHelper<Cell>.TextSelectionOverlay r93 = r92.getOverlayView(r10);
        this.textSelectionOverlay = r93;
        AndroidUtilities.removeFromParent(r93);
        this.textSelectionHelper.setCallback(new AnonymousClass2(this, r13));
        addOnScrollListener(new AnonymousClass3(this, r13));
        this.history = new RichEditorHistory(r122);
    }

    private static void addListItemRow(ArrayList<BlockRow> r1, TL_iv.RichText r2, int r3, int r4, boolean r5, boolean r6) {
        TL_iv.pageBlockParagraph r0 = new TL_iv.pageBlockParagraph();
        if (r2 != null) goto L6;
        r2 = new TL_iv.textEmpty();
    L6:
        r0.text = r2;
        BlockRow r22 = new BlockRow(r0, r3, r4);
        r22.checkbox = r5;
        r22.checked = r6;
        r1.add(r22);
    }

    private BlockRow adjacentAuthorRow(BlockRow r4, int r5) {
        int r42 = this.itemRows.indexOf(r4);
        if (r42 >= 0) goto L5;
        return null;
    L5:
        int r43 = r42 + r5;
        if (r43 >= 0) goto L8;
    L13:
        return null;
    L8:
        if (r43 >= this.itemRows.size()) goto L13;
        BlockRow r3 = this.itemRows.get(r43);
        if (r3.authorQuoteId == 0) goto L13;
        return r3;
    }

    private void applyEditInsideAuthor(BlockRow r6, int r7, int r8, String[] r9) {
        View r62 = findViewByItemObject(r6);
        if ((r62 instanceof RichTextCell) == true) goto L5;
        return;
    L5:
        RichTextCell r63 = (RichTextCell) r62;
        RichEditText r0 = r63.getAuthorEditText();
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L8;
        r1.flush();
    L8:
        StringBuilder r12 = new StringBuilder();
        int r3 = 0;
    L10:
        if (r3 >= r9.length) goto L14;
        if (r3 <= 0) goto L13;
        r12.append(' ');
    L13:
        r12.append(r9[r3]);
        r3 = r3 + 1;
        goto L10
    L14:
        String r92 = r12.toString();
        SpannableStringBuilder r13 = new SpannableStringBuilder(r0.getText());
        int r32 = r13.length();
        int r4 = Math.max(0, Math.min(Math.min(r7, r8), r32));
        r13.replace(r4, Math.max(0, Math.min(Math.max(r7, r8), r32)), r92);
        r0.setTextSilently(r13);
        r0.invalidateEffects();
        r63.persistAuthor();
        int r42 = r4 + r92.length();
        this.textSelectionHelper.clear();
        RichEditorHistory r64 = this.history;
        if (r64 == null) goto L17;
        r64.record();
    L17:
        this.delegate.onContentChanged();
        post(new RichEditorListView$$ExternalSyntheticLambda64(r0, r42));
    }

    private void applyEditInsideCaption(BlockRow r6, int r7, int r8, String[] r9) {
        KeyEvent.Callback r62 = findViewByItemObject(r6);
        if ((r62 instanceof RichCaptionHost) == true) goto L5;
        return;
    L5:
        RichCaptionHost r63 = (RichCaptionHost) r62;
        RichEditText r0 = r63.getCaptionEditText();
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L8;
        r1.flush();
    L8:
        StringBuilder r12 = new StringBuilder();
        int r3 = 0;
    L10:
        if (r3 >= r9.length) goto L14;
        if (r3 <= 0) goto L13;
        r12.append(' ');
    L13:
        r12.append(r9[r3]);
        r3 = r3 + 1;
        goto L10
    L14:
        String r92 = r12.toString();
        SpannableStringBuilder r13 = new SpannableStringBuilder(r0.getText());
        int r32 = r13.length();
        int r4 = Math.max(0, Math.min(Math.min(r7, r8), r32));
        r13.replace(r4, Math.max(0, Math.min(Math.max(r7, r8), r32)), r92);
        r0.setTextSilently(r13);
        r0.invalidateEffects();
        r63.persistCaption();
        int r42 = r4 + r92.length();
        this.textSelectionHelper.clear();
        RichEditorHistory r64 = this.history;
        if (r64 == null) goto L17;
        r64.record();
    L17:
        post(new RichEditorListView$$ExternalSyntheticLambda67(r0, r42));
    }

    private void applyEditInsideDetails(BlockRow r6, int r7, int r8, String[] r9) {
        View r0 = findViewByItemObject(r6);
        if ((r0 instanceof RichDetailsCell) == true) goto L5;
        return;
    L5:
        if ((r6.block instanceof TL_iv.pageBlockDetails) == false) goto L25;
        RichEditText r02 = ((RichDetailsCell) r0).getEditText();
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L10;
        r1.flush();
    L10:
        StringBuilder r12 = new StringBuilder();
        int r3 = 0;
    L12:
        if (r3 >= r9.length) goto L16;
        if (r3 <= 0) goto L15;
        r12.append(' ');
    L15:
        r12.append(r9[r3]);
        r3 = r3 + 1;
        goto L12
    L16:
        String r92 = r12.toString();
        SpannableStringBuilder r13 = new SpannableStringBuilder(r02.getText());
        int r32 = r13.length();
        int r4 = Math.max(0, Math.min(Math.min(r7, r8), r32));
        r13.replace(r4, Math.max(0, Math.min(Math.max(r7, r8), r32)), r92);
        r02.setTextSilently(r13);
        r02.invalidateEffects();
        ((TL_iv.pageBlockDetails) r6.block).title = RichTextStyle.fromSpannable(r13);
        int r42 = r4 + r92.length();
        this.textSelectionHelper.clear();
        RichEditorHistory r62 = this.history;
        if (r62 == null) goto L19;
        r62.record();
    L19:
        post(new RichEditorListView$$ExternalSyntheticLambda62(r02, r42));
        return;
    }

    private void applyEditInsideTable(BlockRow r9, int r10, int r11, int r12, int r13, String[] r14) {
        View r0 = findViewByItemObject(r9);
        if ((r0 instanceof RichTableCell) == false) goto L69;
        RichTableCell r3 = (RichTableCell) r0;
        TableModel r02 = r3.getModel();
        if (r02 == null) goto L70;
        if (r10 != 0) goto L12;
        if (r12 != 0) goto L12;
        applyEditInsideTableTitle(r3, r9, r11, r13, r14);
        return;
    L12:
        int r5 = r11;
        int r102 = r10 - 1;
        int r122 = r12 - 1;
        int r8 = r02.anchors().size();
        if (r102 < 0) goto L66;
        if (r102 >= r8) goto L67;
        if (r122 < 0) goto L68;
        if (r122 >= r8) goto L71;
        RichEditorHistory r82 = this.history;
        if (r82 == null) goto L21;
        r82.flush();
    L21:
        if (r102 > r122) goto L26;
        if (r102 != r122) goto L25;
        if (r5 > r13) goto L26;
    L25:
        int r112 = r5;
        r5 = r13;
    L27:
        StringBuilder r83 = new StringBuilder();
        int r132 = 0;
    L29:
        if (r132 >= r14.length) goto L33;
        if (r132 <= 0) goto L32;
        r83.append('\n');
    L32:
        r83.append(r14[r132]);
        r132 = r132 + 1;
        goto L29
    L33:
        String r84 = r83.toString();
        TL_iv.pageTableCell r133 = r02.anchors().get(r102);
        TL_iv.pageTableCell r142 = r02.anchors().get(r122);
        if (r102 != r122) goto L40;
        String r103 = TableModel.readPlainText(r133);
        int r113 = Math.max(0, Math.min(r112, r103.length()));
        String r92 = r103.substring(0, r113) + r84 + r103.substring(Math.max(0, Math.min(r5, r103.length())));
        TableModel.applyPlainText(r133, r92);
        RichTableCellHost r104 = r3.getGrid().hostForAnchor(r133);
        if (r104 == null) goto L38;
        r104.editText.setTextSilently(r92);
    L38:
        int r85 = r84.length();
    L39:
        int r114 = r113 + r85;
        this.textSelectionHelper.clear();
        RichEditorHistory r86 = this.history;
        if (r86 == null) goto L55;
        r86.record();
    L55:
        post(new RichEditorListView$$ExternalSyntheticLambda68(r3, r133, r114));
        return;
    L40:
        String r1 = TableModel.readPlainText(r133);
        r113 = Math.max(0, Math.min(r112, r1.length()));
        String r15 = r1.substring(0, r113).concat(r84);
        TableModel.applyPlainText(r133, r15);
        RichTableCellHost r4 = r3.getGrid().hostForAnchor(r133);
        if (r4 == null) goto L43;
        r4.editText.setTextSilently(r15);
    L43:
        r102 = r102 + 1;
        if (r102 >= r122) goto L48;
        TL_iv.pageTableCell r16 = r02.anchors().get(r102);
        TableModel.applyPlainText(r16, _UrlKt.FRAGMENT_ENCODE_SET);
        RichTableCellHost r17 = r3.getGrid().hostForAnchor(r16);
        if (r17 == null) goto L43;
        r17.editText.setTextSilently(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L43
    L48:
        String r105 = TableModel.readPlainText(r142);
        String r93 = r105.substring(Math.max(0, Math.min(r5, r105.length())));
        TableModel.applyPlainText(r142, r93);
        RichTableCellHost r106 = r3.getGrid().hostForAnchor(r142);
        if (r106 == null) goto L51;
        r106.editText.setTextSilently(r93);
    L51:
        r85 = r84.length();
    L26:
        r122 = r102;
        r102 = r122;
        r112 = r13;
        goto L27
    L71:
        return;
    L68:
        return;
    L67:
        return;
    L66:
        return;
    L70:
        return;
    }

    private void applyEditInsideTableTitle(RichTableCell r6, BlockRow r7, int r8, int r9, String[] r10) {
        TL_iv.PageBlock r72 = r7.block;
        if ((r72 instanceof TL_iv.pageBlockTable) == true) goto L5;
        return;
    L5:
        TL_iv.pageBlockTable r73 = (TL_iv.pageBlockTable) r72;
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L8;
        r0.flush();
    L8:
        String r02 = RichTextStyle.plainOf(r73.title);
        int r1 = Math.max(0, Math.min(Math.min(r8, r9), r02.length()));
        int r82 = Math.max(0, Math.min(Math.max(r8, r9), r02.length()));
        StringBuilder r92 = new StringBuilder();
        int r3 = 0;
    L10:
        if (r3 >= r10.length) goto L14;
        if (r3 <= 0) goto L13;
        r92.append(' ');
    L13:
        r92.append(r10[r3]);
        r3 = r3 + 1;
        goto L10
    L14:
        String r93 = r92.toString();
        String r83 = r02.substring(0, r1) + r93 + r02.substring(r82);
        TL_iv.textPlain r102 = new TL_iv.textPlain();
        r102.text = r83;
        r73.title = r102;
        RichEditText r62 = r6.getTitleEditText();
        r62.setTextSilently(r83);
        r62.invalidateEffects();
        int r12 = r1 + r93.length();
        this.textSelectionHelper.clear();
        RichEditorHistory r74 = this.history;
        if (r74 == null) goto L17;
        r74.record();
    L17:
        this.delegate.onContentChanged();
        post(new RichEditorListView$$ExternalSyntheticLambda78(r62, r12));
    }

    private boolean applyEditRange(int r14, int r15, int r16, int r17, String[] r18) {
        int r1 = this.rows.indexOf(rowForCell(r14));
        int r3 = this.rows.indexOf(rowForCell(r16));
        if (r1 < 0) goto L98;
        if (r3 < 0) goto L98;
        if (r1 >= this.rows.size()) goto L98;
        if (r3 >= this.rows.size()) goto L98;
        BlockRow r0 = this.rows.get(r1);
        BlockRow r6 = this.rows.get(r3);
        if (r1 == r3) goto L13;
    L16:
        int r8 = r1;
    L17:
        if (r8 > r3) goto L26;
        if (this.rows.get(r8).detailsEnd == true) goto L24;
        if (isDetailsHeader(this.rows.get(r8)) == true) goto L24;
        r8 = r8 + 1;
    L24:
        deleteAcrossDetails(r1, r15, r3, r17, r18);
        return true;
    L26:
        RichEditorHistory r9 = this.history;
        if (r9 == null) goto L29;
        r9.flush();
    L29:
        TL_iv.PageBlock r92 = r0.block;
        boolean r10 = r92 instanceof TL_iv.pageBlockTable;
        boolean r11 = r6.block instanceof TL_iv.pageBlockTable;
        if (r10 == true) goto L93;
        if (r11 == true) goto L93;
        boolean r93 = hasCaption(r92);
        boolean r102 = hasCaption(r6.block);
        if (r93 == true) goto L87;
        if (r102 == true) goto L87;
        if (r1 == r3) goto L39;
    L46:
        String r94 = RichTextCell.readPlainText(r0.block);
        if (r1 != r3) goto L49;
        String r62 = r94;
    L50:
        int r4 = Math.max(0, Math.min(r15, r94.length()));
        int r5 = Math.max(0, Math.min(r17, r62.length()));
        String r42 = r94.substring(0, r4);
        String r52 = r62.substring(r5);
        if (r18.length <= 1) goto L53;
        RichTextCell.applyTextToBlock(r0.block, r42 + r18[0]);
        if (r3 <= r1) goto L65;
    L63:
        if (r3 <= r1) goto L65;
        this.rows.remove(r3);
        r3 = r3 - 1;
    L65:
        int r32 = 1;
    L67:
        if (r32 >= (r18.length - 1)) goto L72;
        TL_iv.pageBlockParagraph r43 = new TL_iv.pageBlockParagraph();
        applyPlainText(r43, r18[r32]);
        ArrayList<BlockRow> r63 = this.rows;
        int r95 = r1 + r32;
        int r112 = r0.level;
        int r12 = r0.num;
        if (r12 <= 0) goto L71;
        r12 = r12 + r32;
    L71:
        r63.add(r95, new BlockRow(r43, r112, r12));
        r32 = r32 + 1;
        goto L67
    L72:
        String r33 = r18[r18.length - 1];
        TL_iv.pageBlockParagraph r44 = new TL_iv.pageBlockParagraph();
        applyPlainText(r44, r33 + r52);
        ArrayList<BlockRow> r53 = this.rows;
        int r64 = (r18.length + r1) - 1;
        int r103 = r0.level;
        int r02 = r0.num;
        if (r02 <= 0) goto L75;
        r02 = (r02 + r18.length) - 1;
    L75:
        r53.add(r64, new BlockRow(r44, r103, r02));
        r1 = (r1 + r18.length) - 1;
        int r03 = r33.length();
    L76:
        renumberAllRuns();
        if (r1 >= 0) goto L79;
    L81:
        BlockRow r13 = null;
    L82:
        this.textSelectionHelper.clear();
        this.adapter.update(false);
        RichEditorHistory r142 = this.history;
        if (r142 == null) goto L85;
        r142.record();
    L85:
        post(new RichEditorListView$$ExternalSyntheticLambda30(this, r13, r03));
        return true;
    L79:
        if (r1 >= this.rows.size()) goto L81;
        r13 = this.rows.get(r1);
        goto L82
    L53:
        if (r18.length != 0) goto L55;
        String r65 = _UrlKt.FRAGMENT_ENCODE_SET;
    L56:
        RichTextCell.applyTextToBlock(r0.block, r42 + r65 + r52);
        if (r3 <= r1) goto L60;
    L58:
        if (r3 <= r1) goto L60;
        this.rows.remove(r3);
        r3 = r3 - 1;
    L60:
        r03 = r42.length() + r65.length();
        goto L76
    L55:
        r65 = r18[0];
        goto L56
    L49:
        r62 = RichTextCell.readPlainText(r6.block);
        goto L50
    L39:
        if (RichTextCell.isQuoteBlock(r0.block) == false) goto L46;
        if (this.textSelectionHelper.getStartChildPosition() != 1) goto L46;
        if (this.textSelectionHelper.getEndChildPosition() != 1) goto L46;
        applyEditInsideAuthor(r0, r15, r17, r18);
        return true;
    L87:
        if (r93 == false) goto L92;
        if (r102 == false) goto L92;
        if (r1 != r3) goto L92;
        applyEditInsideCaption(r0, r15, r17, r18);
        return true;
    L92:
        return false;
    L93:
        if (r10 == false) goto L98;
        if (r11 == false) goto L98;
        if (r1 != r3) goto L98;
        applyEditInsideTable(r0, this.textSelectionHelper.getStartChildPosition(), r15, this.textSelectionHelper.getEndChildPosition(), r17, r18);
        return true;
    L13:
        if (isDetailsHeader(r0) == false) goto L16;
        applyEditInsideDetails(r0, r15, r17, r18);
        return true;
    L98:
        return false;
    }

    private void applyInPlaceUpdateKeepingFocus() {
        this.adapter.updateWithoutNotify();
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L9;
        View r1 = getChildAt(r0);
        if ((r1 instanceof RichTextCell) == false) goto L8;
        ((RichTextCell) r1).rebindInPlace();
    L8:
        r0 = r0 + 1;
        goto L4
    }

    private boolean applyInitialSelectionInternal(int r9, int r10) {
        ArrayList<BlockRow> r0 = this.initialSelectionRows;
        if (r0 != null) goto L5;
    L35:
        return true;
    L5:
        if (r0.isEmpty() == true) goto L35;
        int r02 = Math.max(0, Math.min(r9, r10));
        int r92 = Math.max(0, Math.max(r9, r10));
        int[] r102 = mapInitialOffset(r02);
        int[] r3 = mapInitialOffset(r92);
        BlockRow r4 = this.initialSelectionRows.get(r102[0]);
        if (r102[0] == r3[0]) goto L31;
        if (r02 == r92) goto L31;
        View r93 = findViewByItemObject(r4);
        View r03 = findViewByItemObject(this.initialSelectionRows.get(r3[0]));
        if ((r93 instanceof RichTextCell) == true) goto L14;
    L30:
        return false;
    L14:
        if ((r03 instanceof RichTextCell) == false) goto L30;
        int r42 = 0;
    L18:
        if (r42 >= this.rows.size()) goto L20;
        this.textSelectionHelper.cacheText(r42, RichTextCell.readPlainText(this.rows.get(r42).block), null);
        r42 = r42 + 1;
        goto L18
    L20:
        RichTextCell r94 = (RichTextCell) r93;
        int r43 = r94.getEditText().length();
        if (r102[1] < r43) goto L25;
        r43 = Math.max(0, r43 - 1);
    L25:
        if (this.textSelectionHelper.selectRangeOf(r94, r102[1], r43) == true) goto L28;
        r94.requestEditFocus();
        return true;
    L28:
        this.textSelectionHelper.extendSelectionTo((RichTextCell) r03, r3[1]);
        return true;
    L31:
        View r8 = findViewByItemObject(r4);
        if ((r8 instanceof RichTextCell) == true) goto L34;
        return false;
    L34:
        RichTextCell r82 = (RichTextCell) r8;
        r82.requestEditFocus();
        RichEditText r83 = r82.getEditText();
        int r95 = r83.length();
        r83.setSelection(Math.min(r102[1], r95), Math.min(r3[1], r95));
        goto L35
    }

    private static void applyPlainText(TL_iv.PageBlock r1, String r2) {
        TL_iv.textPlain r0 = new TL_iv.textPlain();
        r0.text = r2;
        if ((r1 instanceof TL_iv.pageBlockParagraph) == false) goto L6;
        ((TL_iv.pageBlockParagraph) r1).text = r0;
        return;
    }

    private void attachDocument(TLRPC.Document r11, String r12) {
        if (r11 == null) goto L33;
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L7;
        r0.flush();
    L7:
        TL_iv.pageBlockDocument r02 = new TL_iv.pageBlockDocument();
        BlockRow r1 = new BlockRow(r02);
        MediaUploadState r2 = new MediaUploadState();
        r1.media = r2;
        r2.isDocument = true;
        long r4 = r11.id;
        if (r4 != 0) goto L10;
    L15:
        boolean r6 = true;
    L16:
        if (r6 == true) goto L18;
        r2.document = r11;
        r2.state = 2;
        r02.document_id = r4;
    L25:
        insertPreparedRow(r1);
        if (r6 == false) goto L28;
        startDocumentUpload(r1, r12, r11);
    L28:
        RichEditorHistory r112 = this.history;
        if (r112 == null) goto L31;
        r112.record();
    L31:
        this.delegate.onContentChanged();
        return;
    L18:
        if (TextUtils.isEmpty(r12) == false) goto L20;
        return;
    L20:
        if (new File(r12).exists() == false) goto L34;
        MediaUploadState r03 = r1.media;
        r03.document = r11;
        r03.localPath = r12;
        r03.state = 1;
        r03.progress = 0.0f;
        goto L25
    L34:
        return;
    L10:
        if (r11.dc_id == 0) goto L15;
        if (r11.access_hash == 0) goto L15;
        r6 = false;
        goto L16
    }

    private void beginCellSelection(RichTableCell r2) {
        RichTableCell r0 = this.activeCellSelectionTable;
        if (r0 == null) goto L6;
        if (r0 == r2) goto L6;
        r0.clearCellSelection();
    L6:
        this.activeCellSelectionTable = r2;
        this.clearDraggedMultiCellSelectionOnMenuDismiss = false;
        this.dotSelectedCol = -1;
        this.dotSelectedRow = -1;
        r2.setCellSelectionListener(this.cellSelectionListener);
        TextSelectionHelper.ArticleTextSelectionHelper r22 = this.textSelectionHelper;
        if (r22 != null) goto L9;
    L11:
        setEditTextsLocked(true);
        return;
    L9:
        if (r22.isInSelectionMode() == false) goto L11;
        this.textSelectionHelper.clear();
        goto L11
    }

    private InlineButtonEdit beginInlineButtonEdit() {
        if (canCreateInlineButtonOnSelection() == true) goto L6;
        return null;
    L6:
        if (isTableSelection() == false) goto L13;
        int r0 = this.textSelectionHelper.getStartCell();
        int r2 = this.textSelectionHelper.getStartChildPosition();
        if (r2 == this.textSelectionHelper.getEndChildPosition()) goto L10;
        return null;
    L10:
        RichEditText r02 = tableEditText(r0, r2);
    L11:
        RichEditText r4 = r02;
        if (r4 != null) goto L27;
        return null;
    L27:
        int r03 = r4.length();
        return new InlineButtonEdit(this, r4, Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r03)), Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r03)), null, null);
    L13:
        if (isDetailsSelection() == false) goto L16;
        r02 = detailsEditText(this.textSelectionHelper.getStartCell());
        goto L11
    L16:
        if (isCaptionSelection() == false) goto L19;
        r02 = captionEditText(this.textSelectionHelper.getStartCell());
        goto L11
    L19:
        if (isQuoteAuthorSelection() == false) goto L21;
        r02 = quoteAuthorEditText(this.textSelectionHelper.getStartCell());
        goto L11
    L21:
        RichTextCell r04 = singleSelectionCell();
        if (r04 != null) goto L24;
        return null;
    L24:
        r02 = r04.getEditText();
        goto L11
    }

    private int blockTextLength(int r2) {
        RichEditText r0 = detailsEditText(r2);
        if (r0 != null) goto L5;
        RichTextCell r02 = cellAt(r2);
        if (r02 != null) goto L9;
        BlockRow r1 = rowForCell(r2);
        if (r1 != null) goto L15;
        return 0;
    L15:
        return RichTextCell.readPlainText(r1.block).length();
    L9:
        return r02.getEditText().length();
    L5:
        return r0.length();
    }

    private TL_iv.PageBlock buildListBlock(int r20, int r21, boolean r22, int[] r23, int r24, int r25) {
        RichEditorListView r0 = this;
        int r5 = r24;
        int r6 = r25;
        if (r22 == false) goto L5;
        TL_iv.pageBlockOrderedList r10 = new TL_iv.pageBlockOrderedList();
    L6:
        if (r22 == false) goto L8;
        TL_iv.pageBlockList r11 = null;
    L9:
        int r1 = r20;
        int r13 = 1;
    L11:
        if (r1 >= r5) goto L81;
        BlockRow r15 = r0.rows.get(r1);
        if (r15.quoteIds.size() > r6) goto L81;
        int r2 = r15.level;
        if (r2 < r21) goto L81;
        if (r2 == r21) goto L20;
    L25:
        if (r2 > r21) goto L81;
        boolean r16 = isNonText(r15.block);
        if (r16 == true) goto L35;
        TL_iv.PageBlock r26 = r15.block;
        if (r26 != null) goto L32;
        TL_iv.RichText r27 = null;
    L33:
        if (r27 != null) goto L36;
        r27 = new TL_iv.textEmpty();
    L36:
        r1 = r1 + 1;
        ArrayList<TL_iv.PageBlock> r3 = new ArrayList();
        if (r16 == false) goto L39;
        r0.emitLeafBlock(r15, r3);
    L39:
        if (r1 >= r5) goto L63;
        BlockRow r4 = r0.rows.get(r1);
        if (r4.quoteIds.size() > r6) goto L42;
        TL_iv.RichText r9 = r27;
        int r28 = r4.level;
        if (r28 < r21) goto L43;
        if (r28 == r21) goto L49;
        if (r4.num <= 0) goto L55;
        ArrayList<TL_iv.PageBlock> r42 = r3;
        boolean r32 = true;
    L56:
        int[] r18 = {r1};
        ArrayList<TL_iv.PageBlock> r12 = r42;
        TL_iv.PageBlock r29 = r0.buildListBlock(r1, r28, r32, r18, r5, r6);
        if (r29 == null) goto L59;
        r12.add(r29);
    L59:
        int r02 = r18[0];
        if (r02 <= r1) goto L65;
        r5 = r24;
        r6 = r25;
        r1 = r02;
        r27 = r9;
        r3 = r12;
        r0 = this;
    L65:
        if (r16 == false) goto L67;
    L72:
        ArrayList<TL_iv.PageBlock> r210 = new ArrayList();
        if (r16 == true) goto L75;
        TL_iv.pageBlockParagraph r33 = new TL_iv.pageBlockParagraph();
        r33.text = r9;
        r210.add(r33);
    L75:
        r210.addAll(r12);
        if (r210.isEmpty() == true) goto L80;
        if (r22 == false) goto L79;
        TL_iv.TL_pageListOrderedItemBlocks r34 = new TL_iv.TL_pageListOrderedItemBlocks();
        r34.num = r13 + ".";
        r34.blocks = r210;
        r34.checkbox = r15.checkbox;
        r34.checked = r15.checked;
        r10.items.add(r34);
    L71:
        r13 = r13 + 1;
        goto L80
    L79:
        TL_iv.TL_pageListItemBlocks r03 = new TL_iv.TL_pageListItemBlocks();
        r03.blocks = r210;
        r03.checkbox = r15.checkbox;
        r03.checked = r15.checked;
        r11.items.add(r03);
    L80:
        r0 = this;
        r5 = r24;
        r6 = r25;
        goto L11
    L67:
        if (r12.isEmpty() == false) goto L72;
        if (r22 == false) goto L70;
        TL_iv.TL_pageListOrderedItemText r211 = new TL_iv.TL_pageListOrderedItemText();
        r211.num = r13 + ".";
        r211.text = r9;
        r211.checkbox = r15.checkbox;
        r211.checked = r15.checked;
        r10.items.add(r211);
        goto L71
    L70:
        TL_iv.TL_pageListItemText r04 = new TL_iv.TL_pageListItemText();
        r04.text = r9;
        r04.checkbox = r15.checkbox;
        r04.checked = r15.checked;
        r11.items.add(r04);
        goto L71
    L55:
        r42 = r3;
        r32 = false;
        goto L56
    L49:
        if (isNonText(r4.block) == false) goto L43;
        r0.emitLeafBlock(r4, r3);
        r1 = r1 + 1;
        r27 = r9;
    L43:
        r12 = r3;
        goto L65
    L42:
        r9 = r27;
        goto L43
    L63:
        r9 = r27;
        r12 = r3;
        goto L65
    L32:
        r27 = r26.text;
        goto L33
    L35:
        r27 = null;
        goto L36
    L20:
        if (r15.num <= 0) goto L22;
        boolean r35 = true;
    L23:
        if (r35 == r22) goto L25;
    L22:
        r35 = false;
    L81:
        r23[0] = r1;
        if (r22 == false) goto L88;
        if (r10.items.isEmpty() == false) goto L86;
        return null;
    L86:
        return r10;
    L88:
        if (r11.items.isEmpty() == false) goto L90;
        return null;
    L90:
        return r11;
    L8:
        r11 = new TL_iv.pageBlockList();
        goto L9
    L5:
        r10 = null;
        goto L6
    }

    private TL_iv.PageBlock buildQuoteBlock(ArrayList<TL_iv.PageBlock> r2, long r3) {
        if (r2 != null) goto L4;
        return null;
    L4:
        if (r2.isEmpty() == true) goto L20;
        TL_iv.RichText r1 = quoteAuthor(r3);
        if (r2.size() == 1) goto L9;
    L16:
        TL_iv.pageBlockBlockquoteBlocks r32 = new TL_iv.pageBlockBlockquoteBlocks();
        r32.blocks = r2;
        r32.caption = r1;
        return r32;
    L9:
        if ((r2.get(0) instanceof TL_iv.pageBlockParagraph) == false) goto L16;
        TL_iv.pageBlockBlockquote r4 = new TL_iv.pageBlockBlockquote();
        if (r2.get(0).text == null) goto L13;
        TL_iv.RichText r22 = r2.get(0).text;
    L14:
        r4.text = r22;
        r4.caption = r1;
        return r4;
    L13:
        r22 = new TL_iv.textEmpty();
        goto L14
    L20:
        return null;
    }

    private String buildSelectionHtml() {
        int r0 = this.rows.indexOf(rowForCell(this.textSelectionHelper.getStartCell()));
        int r1 = this.rows.indexOf(rowForCell(this.textSelectionHelper.getEndCell()));
        int r2 = this.textSelectionHelper.getStartOffset();
        int r3 = this.textSelectionHelper.getEndOffset();
        if (r0 < 0) goto L24;
        if (r1 < 0) goto L24;
        if (r0 >= this.rows.size()) goto L24;
        if (r1 >= this.rows.size()) goto L24;
        if (r0 <= r1) goto L13;
        int r8 = r0;
        int r7 = r1;
    L12:
        int r10 = r2;
        int r9 = r3;
    L25:
        String r02 = RichHtml.serialize(this.rows, r7, r8, r9, r10, this.quoteAuthors);     // Catch: Exception -> L22
        if (TextUtils.isEmpty(r02) == false) goto L20;
        return null;
    L20:
        RichMediaClipboard.set(collectMediaPhotos(r7, r8), collectMediaDocuments(r7, r8));     // Catch: Exception -> L22
        return r02;
    L22:
        e = move-exception;
        FileLog.e(e);
        goto L24
    L13:
        if (r0 != r1) goto L16;
        if (r2 <= r3) goto L16;
        r7 = r0;
        r8 = r1;
    L16:
        r7 = r0;
        r8 = r1;
        r9 = r2;
        r10 = r3;
    L24:
        return null;
    }

    private boolean canNonTextJoinList(int r2) {
        if (r2 > 0) goto L4;
        return false;
    L4:
        if (r2 < this.rows.size()) goto L6;
        return false;
    L6:
        if (isNonText(this.rows.get(r2).block) == true) goto L8;
        return false;
    L8:
        if (this.rows.get(r2 - 1).level < 1) goto L14;
        return true;
    L14:
        return false;
    }

    private void cancelAudioUpload(BlockRow r3) {
        RichMediaUploader r0 = this.uploaders.remove(r3.media);
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        int r32 = this.rows.indexOf(r3);
        if (r32 < 0) goto L13;
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L10;
        r02.flush();
    L10:
        this.rows.remove(r32);
        this.adapter.update(true);
        RichEditorHistory r33 = this.history;
        if (r33 == null) goto L13;
        r33.record();
    L13:
        this.delegate.onContentChanged();
    }

    private void cancelDocumentUpload(BlockRow r3) {
        RichMediaUploader r0 = this.uploaders.remove(r3.media);
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L8;
        r02.flush();
    L8:
        this.rows.remove(r3);
        this.adapter.update(true);
        RichEditorHistory r32 = this.history;
        if (r32 == null) goto L11;
        r32.record();
    L11:
        this.delegate.onContentChanged();
    }

    private void cancelMediaUpload(BlockRow r4) {
        Iterator<MediaUploadState> r0 = mediasOf(r4).iterator();
    L4:
        if (r0.hasNext() == false) goto L11;
        MediaUploadState r1 = r0.next();
        RichMediaUploader r2 = this.uploaders.remove(r1);
        if (r2 == null) goto L8;
        r2.cancel();
    L8:
        RichMediaConverter r12 = this.converters.remove(r1);
        if (r12 == null) goto L4;
        r12.cancel();
        goto L4
    L11:
        int r42 = this.rows.indexOf(r4);
        if (r42 < 0) goto L19;
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L16;
        r02.flush();
    L16:
        this.rows.remove(r42);
        this.adapter.update(true);
        RichEditorHistory r43 = this.history;
        if (r43 == null) goto L19;
        r43.record();
    L19:
        this.delegate.onContentChanged();
    }

    private void cancelMediaUpload(BlockRow r2, MediaUploadState r3) {
        if (r3 != null) goto L5;
        cancelMediaUpload(r2);
        return;
    L5:
        RichMediaUploader r0 = this.uploaders.remove(r3);
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        RichMediaConverter r02 = this.converters.remove(r3);
        if (r02 == null) goto L11;
        r02.cancel();
    L11:
        RichEditorHistory r03 = this.history;
        if (r03 == null) goto L14;
        r03.flush();
    L14:
        removeMediaFromRow(r2, r3);
        RichEditorHistory r22 = this.history;
        if (r22 == null) goto L17;
        r22.record();
    L17:
        this.delegate.onContentChanged();
    }

    private boolean captionSelectionHasFormattable() {
        RichEditText r0 = captionEditText(this.textSelectionHelper.getStartCell());
        if (r0 != null) goto L5;
        return false;
    L5:
        int r2 = this.textSelectionHelper.getStartOffset();
        int r5 = this.textSelectionHelper.getEndOffset();
        if (Math.max(0, Math.min(Math.min(r2, r5), r0.length())) >= Math.max(0, Math.min(Math.max(r2, r5), r0.length()))) goto L9;
        return true;
    L9:
        return false;
    }

    private int captureCaret(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichTextCell) == false) goto L8;
        RichTextCell r02 = (RichTextCell) r0;
        if (r02.getEditText().isFocused() == true) goto L7;
        return -1;
    L7:
        return r02.getEditText().getSelectionEnd();
    L8:
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.view.View, android.widget.TextView, org.telegram.ui.iv.RichEditText] */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r1v1, types: [org.telegram.ui.iv.RichTableCell] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.view.View, org.telegram.ui.iv.RichEditorListView] */
    private RichEditorHistory.FocusState captureFocusState() {
        View r0 = findFocus();
        if ((r0 instanceof RichEditText) == false) goto L5;
        ?? r02 = (RichEditText) r0;
        int r5 = r02.getSelectionStart();
        int r6 = r02.getSelectionEnd();
        ?? r1 = findTableCellAncestor(r02);
        if (r1 != 0) goto L9;
    L21:
        RichCaptionHost r7 = findCaptionHostAncestor(r02);
        if (r7 == null) goto L27;
        if (r7.getRow() == null) goto L27;
        return new RichEditorHistory.FocusState(r7.getRow().id, -1, r5, r6);
    L27:
        if (r02 == 0) goto L36;
        if ((r02 instanceof RichTextCell) == true) goto L36;
        Object r72 = r02.getParent();
        if ((r72 instanceof View) == false) goto L34;
        View r73 = (View) r72;
    L33:
        r02 = r73;
        goto L27
    L34:
        r73 = null;
    L36:
        if ((r02 instanceof RichTextCell) == false) goto L42;
        RichTextCell r03 = (RichTextCell) r02;
        if (r03.getRow() == null) goto L42;
        return new RichEditorHistory.FocusState(r03.getRow().id, -1, r5, r6);
    L42:
        return RichEditorHistory.FocusState.NONE;
    L9:
        if (r1.getRow() == null) goto L21;
        if (r02 == r1.getTitleEditText()) goto L13;
        RichTableCellHost r04 = r1.findHostContaining(r02);
        if (r04 == null) goto L18;
        int r05 = r1.childPosForAnchor(r04.cell);
    L17:
        int r4 = r05;
        return new RichEditorHistory.FocusState(r1.getRow().id, r4, r5, r6);
    L18:
        r05 = -1;
        goto L17
    L13:
        return new RichEditorHistory.FocusState(r1.getRow().id, r1.titleChildPos(), r5, r6);
    L5:
        return RichEditorHistory.FocusState.NONE;
    }

    private static int caretEndOf(BlockRow r3) {
        if (r3 != null) goto L5;
        return 0;
    L5:
        boolean r1 = isDetailsHeader(r3);
        TL_iv.PageBlock r2 = r3.block;
        if (r1 == false) goto L13;
        CharSequence r32 = RichTextStyle.toSpannable(((TL_iv.pageBlockDetails) r2).title);
        if (r32 != null) goto L11;
        return 0;
    L11:
        return r32.length();
    L13:
        if (isTextBearing(r2) == false) goto L19;
        CharSequence r33 = RichTextCell.readStyledText(r3.block);
        if (r33 != null) goto L18;
        return 0;
    L18:
        return r33.length();
    L19:
        return 0;
    }

    private void cascadeOutdent(int r4) {
        BlockRow r0 = this.rows.get(r4);
        int r1 = r0.level;
        if (r1 <= 0) goto L14;
        int r2 = r1 - 1;
        r0.level = r2;
        if (r2 != 0) goto L8;
        r0.num = 0;
        r0.checkbox = false;
        r0.checked = false;
    L8:
        r4 = r4 + 1;
        if (r4 >= this.rows.size()) goto L18;
        BlockRow r02 = this.rows.get(r4);
        int r22 = r02.level;
        if (r22 <= r1) goto L19;
        r02.level = r22 - 1;
        goto L8
    L19:
        return;
    L18:
        return;
    }

    private RichTextCell cellAt(int r2) {
        if (r2 >= 0) goto L5;
        return null;
    L5:
        View r1 = this.layoutManager.findViewByPosition(r2);
        if ((r1 instanceof RichTextCell) == true) goto L8;
        return null;
    L8:
        return (RichTextCell) r1;
    }

    private RichTextCell cellForEditText(RichEditText r1) {
        ViewParent r0 = r1.getParent();
    L3:
        if (r0 == null) goto L9;
        if ((r0 instanceof RichTextCell) == true) goto L7;
        r0 = r0.getParent();
        goto L3
    L7:
        return (RichTextCell) r0;
    L9:
        return null;
    }

    private static int clearMaskFor(int r2) {
        if (r2 != 4) goto L7;
        return 114971;
    L7:
        if (r2 != 16384) goto L11;
        return 32772;
    L11:
        if (r2 != 32768) goto L14;
        return LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD;
    L14:
        return 4;
    }

    private void clearRowText(BlockRow r3) {
        if (r3 == null) goto L9;
        RichTextCell.applyTextToBlock(r3.block, _UrlKt.FRAGMENT_ENCODE_SET);
        View r2 = findViewByItemObject(r3);
        if ((r2 instanceof RichTextCell) == false) goto L8;
        ((RichTextCell) r2).getEditText().setTextSilently(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L8:
        return;
    }

    private static TL_iv.PageBlock cloneBlock(TL_iv.PageBlock r3) {
        if (r3 != null) goto L10;
    L8:
        TL_iv.pageBlockParagraph r32 = new TL_iv.pageBlockParagraph();
        r32.text = new TL_iv.textEmpty();
        return r32;
    L10:
        ensureSerializable(r3);     // Catch: Throwable -> L6
        SerializedData r0 = new SerializedData(r3.getObjectSize());     // Catch: Throwable -> L6
        r3.serializeToStream(r0);     // Catch: Throwable -> L6
        SerializedData r33 = new SerializedData(r0.toByteArray());     // Catch: Throwable -> L6
        TL_iv.PageBlock r1 = TL_iv.PageBlock.TLdeserialize(r33, r33.readInt32(true), true);     // Catch: Throwable -> L6
        r0.cleanup();     // Catch: Throwable -> L6
        r33.cleanup();     // Catch: Throwable -> L6
        if (r1 == null) goto L8;
        return r1;
    L6:
        th = move-exception;
        FileLog.e(th);
        goto L8
    }

    private boolean collapseSingleBlockQuotes() {
        HashMap r0 = new HashMap();
        int r2 = 0;
    L4:
        if (r2 >= this.rows.size()) goto L13;
        BlockRow r3 = this.rows.get(r2);
        if (r3.detailsEnd == true) goto L12;
        int r5 = 0;
    L10:
        if (r5 >= r3.quoteIds.size()) goto L12;
        r0.merge(r3.quoteIds.get(r5), 1, new RichEditorListView$$ExternalSyntheticLambda65());
        r5 = r5 + 1;
    L12:
        r2 = r2 + 1;
        goto L4
    L13:
        int r22 = 0;
        boolean r32 = false;
    L15:
        if (r22 >= this.rows.size()) goto L36;
        BlockRow r52 = this.rows.get(r22);
        if (r52.quoteIds.size() != 1) goto L35;
        if ((r52.block instanceof TL_iv.pageBlockParagraph) == false) goto L35;
        Long r6 = r52.quoteIds.get(0);
        r6.longValue();
        Integer r7 = (Integer) r0.get(r6);
        if (r7 == null) goto L35;
        if (r7.intValue() != 1) goto L35;
        TL_iv.pageBlockBlockquote r33 = new TL_iv.pageBlockBlockquote();
        TL_iv.RichText r72 = r52.block.text;
        if (r72 != null) goto L30;
        r72 = new TL_iv.textEmpty();
    L30:
        r33.text = r72;
        TL_iv.RichText r62 = this.quoteAuthors.remove(r6);
        if (r62 != null) goto L34;
        r62 = new TL_iv.textEmpty();
    L34:
        r33.caption = r62;
        r52.block = r33;
        r52.quoteIds.clear();
        r32 = true;
    L35:
        r22 = r22 + 1;
        goto L15
    L36:
        return r32;
    }

    private ArrayList<TLRPC.Document> collectMediaDocuments(int r7, int r8) {
        ArrayList<TLRPC.Document> r0 = new ArrayList();
        HashSet r1 = new HashSet();
    L3:
        if (r7 > r8) goto L17;
        if (r7 >= this.rows.size()) goto L17;
        Iterator<MediaUploadState> r2 = mediasOf(this.rows.get(r7)).iterator();
    L8:
        if (r2.hasNext() == false) goto L16;
        MediaUploadState r3 = r2.next();
        if (r3.isReady() == false) goto L8;
        TLRPC.Document r4 = r3.document;
        if (r4 == null) goto L8;
        if (r1.add(Long.valueOf(r4.id)) == false) goto L8;
        r0.add(r3.document);
        goto L8
    L16:
        r7 = r7 + 1;
    L17:
        return r0;
    }

    private ArrayList<TLRPC.Photo> collectMediaPhotos(int r7, int r8) {
        ArrayList<TLRPC.Photo> r0 = new ArrayList();
        HashSet r1 = new HashSet();
    L3:
        if (r7 > r8) goto L17;
        if (r7 >= this.rows.size()) goto L17;
        Iterator<MediaUploadState> r2 = mediasOf(this.rows.get(r7)).iterator();
    L8:
        if (r2.hasNext() == false) goto L16;
        MediaUploadState r3 = r2.next();
        if (r3.isReady() == false) goto L8;
        TLRPC.Photo r4 = r3.photo;
        if (r4 == null) goto L8;
        if (r1.add(Long.valueOf(r4.id)) == false) goto L8;
        r0.add(r3.photo);
        goto L8
    L16:
        r7 = r7 + 1;
    L17:
        return r0;
    }

    private static boolean computeAllSelected(TableModel r5, Set<TL_iv.pageTableCell> r6) {
        if (r6.isEmpty() == false) goto L5;
    L19:
        return false;
    L5:
        if (r5.rowCount <= 0) goto L19;
        if (r5.colCount <= 0) goto L19;
        HashSet r0 = new HashSet();
        int r2 = 0;
    L11:
        if (r2 >= r5.rowCount) goto L18;
        int r3 = 0;
    L14:
        if (r3 >= r5.colCount) goto L16;
        r0.add(r5.grid[r2][r3]);
        r3 = r3 + 1;
        goto L14
    L16:
        r2 = r2 + 1;
        goto L11
    L18:
        return r0.equals(new HashSet(r6));
    }

    private static boolean computeCanMerge(TableModel r9, Set<TL_iv.pageTableCell> r10) {
        Iterator<TL_iv.pageTableCell> r0 = r10.iterator();
        int r1 = Integer.MAX_VALUE;
        int r3 = -1;
        int r4 = -1;
        int r2 = Integer.MAX_VALUE;
    L4:
        if (r0.hasNext() == false) goto L6;
        TL_iv.pageTableCell r5 = r0.next();
        int r6 = r9.anchorRowOf(r5);
        int r7 = r9.anchorColOf(r5);
        int r8 = TableModel.spanRow(r5);
        int r52 = TableModel.spanCol(r5);
        r1 = Math.min(r1, r6);
        r2 = Math.min(r2, r7);
        r3 = Math.max(r3, (r6 + r8) - 1);
        r4 = Math.max(r4, (r7 + r52) - 1);
        goto L4
    L6:
        HashSet r02 = new HashSet();
    L7:
        if (r1 > r3) goto L22;
        int r53 = r2;
    L9:
        if (r53 > r4) goto L20;
        if (r1 < 0) goto L18;
        if (r53 < 0) goto L30;
        if (r1 >= r9.rowCount) goto L31;
        if (r53 >= r9.colCount) goto L32;
        r02.add(r9.grid[r1][r53]);
        r53 = r53 + 1;
        goto L9
    L32:
        return false;
    L31:
        return false;
    L30:
        return false;
    L18:
        return false;
    L20:
        r1 = r1 + 1;
        goto L7
    L22:
        return r02.equals(new HashSet(r10));
    }

    private static boolean computeHasSpan(TL_iv.pageTableCell r2) {
        if (TableModel.spanCol(r2) <= 1) goto L5;
    L9:
        return true;
    L5:
        if (TableModel.spanRow(r2) > 1) goto L9;
        return false;
    }

    private static boolean computeSpansFullColumns(TableModel r5, Set<TL_iv.pageTableCell> r6) {
        HashSet r0 = new HashSet();
        Iterator<TL_iv.pageTableCell> r1 = r6.iterator();
    L4:
        if (r1.hasNext() == false) goto L7;
        r0.add(Integer.valueOf(r5.anchorColOf(r1.next())));
        goto L4
    L7:
        if (r0.isEmpty() == false) goto L9;
        return false;
    L9:
        Iterator r02 = r0.iterator();
    L11:
        if (r02.hasNext() == false) goto L28;
        int r12 = ((Integer) r02.next()).intValue();
        if (r12 < 0) goto L27;
        if (r12 >= r5.colCount) goto L27;
        int r3 = 0;
    L19:
        if (r3 >= r5.rowCount) goto L11;
        if (r5.anchorC[r3][r12] != r12) goto L22;
        if (r6.contains(r5.grid[r3][r12]) == false) goto L25;
        r3 = r3 + 1;
        goto L19
    L25:
        return false;
    L22:
        return false;
    L27:
        return false;
    L28:
        return true;
    }

    private static boolean computeSpansFullRows(TableModel r5, Set<TL_iv.pageTableCell> r6) {
        HashSet r0 = new HashSet();
        Iterator<TL_iv.pageTableCell> r1 = r6.iterator();
    L4:
        if (r1.hasNext() == false) goto L7;
        r0.add(Integer.valueOf(r5.anchorRowOf(r1.next())));
        goto L4
    L7:
        if (r0.isEmpty() == false) goto L9;
        return false;
    L9:
        Iterator r02 = r0.iterator();
    L11:
        if (r02.hasNext() == false) goto L28;
        int r12 = ((Integer) r02.next()).intValue();
        if (r12 < 0) goto L27;
        if (r12 >= r5.rowCount) goto L27;
        int r3 = 0;
    L19:
        if (r3 >= r5.colCount) goto L11;
        if (r5.anchorR[r12][r3] != r12) goto L22;
        if (r6.contains(r5.grid[r12][r3]) == false) goto L25;
        r3 = r3 + 1;
        goto L19
    L25:
        return false;
    L22:
        return false;
    L27:
        return false;
    L28:
        return true;
    }

    private String copyDocumentUriToCache(Uri r10) {
        Uri r4 = r10;
        Cursor r102 = getContext().getContentResolver().query(r4, new String[]{"_display_name"}, null, null, null);     // Catch: Exception -> L19
        if (r102 != null) goto L73;
    L21:
        String r3 = null;
    L22:
        if (r102 == null) goto L31;
        r102.close();     // Catch: Exception -> L25
    L25:
        e = move-exception;
        Exception r103 = e;
    L29:
        FileLog.e(r103);
    L31:
        if (TextUtils.isEmpty(r3) == false) goto L33;
        r3 = "document_" + SharedConfig.getLastLocalId();
    L33:
        String r104 = r3.replace('/', '_').replace('\\', '_');
        InputStream r9 = getContext().getContentResolver().openInputStream(r4);     // Catch: Exception -> L39
        if (r9 != null) goto L82;
        if (r9 == null) goto L41;
        r9.close();     // Catch: Exception -> L39
    L41:
        return null;
    L82:
        File r0 = new File(FileLoader.getDirectory(4), "rich_document_" + Math.abs(r4.hashCode()) + "_" + r104);     // Catch: Throwable -> L47
        FileOutputStream r105 = new FileOutputStream(r0);     // Catch: Throwable -> L47
        AndroidUtilities.copyFile(r9, r105);     // Catch: Throwable -> L49
        r105.close();     // Catch: Throwable -> L47
        String r106 = r0.getAbsolutePath();     // Catch: Throwable -> L47
        r9.close();     // Catch: Exception -> L39
        return r106;
    L49:
        th = move-exception;
        r105.close();     // Catch: Throwable -> L53
    L84:
        throw th;     // Catch: Throwable -> L47
    L53:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L47
        throw th;     // Catch: Throwable -> L47
    L47:
        th = move-exception;
        r9.close();     // Catch: Throwable -> L59
    L85:
        throw th;     // Catch: Exception -> L39
    L59:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L39
        throw th;     // Catch: Exception -> L39
    L39:
        e = move-exception;
        FileLog.e(e);
        return null;
    L73:
    L11:
        th = move-exception;
        r102.close();     // Catch: Throwable -> L15
    L83:
        throw th;     // Catch: Exception -> L19
    L15:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L19
        throw th;     // Catch: Exception -> L19
    L8:
        if (r102.moveToFirst() == false) goto L21;
        r3 = r102.getString(0);     // Catch: Throwable -> L11
        goto L22
    L19:
        e = e;
    L20:
        r103 = e;
        r3 = null;
    L27:
        e = e;
        r4 = r10;
        goto L20
    }

    private void copyHelperSelection() {
        CharSequence r0 = this.textSelectionHelper.getSelectedTextPublic();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.length() == 0) goto L10;
        writeSelectionToClipboard(r0);
        return;
    }

    private String copyUriToCache(Uri r5, boolean r6, String r7, int r8) {
        Context r4 = getContext();
        InputStream r1 = null;
        if (r4 != null) goto L72;
        return null;
    L72:
        InputStream r42 = r4.getContentResolver().openInputStream(r5);     // Catch: Throwable -> L32 Exception -> L34
        if (r42 != null) goto L10;
        if (r42 != null) goto L68;
    L9:
        return null;
    L68:
        r42.close();     // Catch: Exception -> L49
        goto L9
    L10:
        if (r7 != null) goto L74;
        String r52 = null;
    L19:
        if (TextUtils.isEmpty(r52) == false) goto L23;
        if (r6 == false) goto L22;
        r52 = "mp4";
        goto L23
    L22:
        r52 = "jpg";
    L23:
        File r62 = new File(FileLoader.getDirectory(4), "rich_external_" + (-r8) + "_" + SharedConfig.getLastLocalId() + "." + r52);     // Catch: Throwable -> L12 Exception -> L15
        FileOutputStream r53 = new FileOutputStream(r62);     // Catch: Throwable -> L12 Exception -> L15
    L15:
        e = e;
        FileOutputStream r63 = null;
    L66:
        FileLog.e(e);     // Catch: Throwable -> L42
        if (r42 != null) goto L62;
    L39:
        if (r63 != null) goto L58;
    L41:
        return null;
    L58:
        r63.close();     // Catch: Exception -> L55
        goto L41
    L62:
        r42.close();     // Catch: Exception -> L54
    L42:
        th = th;
    L14:
        r1 = r42;
    L44:
        if (r1 != null) goto L64;
    L46:
        if (r63 == null) goto L78;
        r63.close();     // Catch: Exception -> L53
        throw th;
    L79:
        throw th;
    L78:
        throw th;
    L64:
        r1.close();     // Catch: Exception -> L52
    L12:
        th = th;
        r63 = null;
        goto L14
    L76:
        AndroidUtilities.copyFile(r42, r53);     // Catch: Throwable -> L28 Exception -> L30
        String r64 = r62.getAbsolutePath();     // Catch: Throwable -> L28 Exception -> L30
        r42.close();     // Catch: Exception -> L50
    L56:
        r53.close();     // Catch: Exception -> L51
    L27:
        return r64;
    L30:
        e = move-exception;
        r63 = r53;
        e = e;
    L28:
        th = move-exception;
        r63 = r53;
        th = th;
        goto L14
    L74:
        r52 = MimeTypeMap.getSingleton().getExtensionFromMimeType(r7);     // Catch: Throwable -> L12 Exception -> L15
        goto L19
    L34:
        e = e;
        r42 = null;
        r63 = null;
    L32:
        th = th;
        r63 = null;
        goto L44
    }

    private void cutHelperSelection() {
        CharSequence r0 = this.textSelectionHelper.getSelectedTextPublic();
        if (r0 != null) goto L5;
    L7:
        deleteHelperSelection();
        return;
    L5:
        if (r0.length() <= 0) goto L7;
        writeSelectionToClipboard(r0);
        goto L7
    }

    private static void cycleButtonStyle(TL_keyboard.PageButton r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        TL_keyboard.RichButtonStyle r0 = r7.style;
        boolean r3 = true;
        if (r0 != null) goto L7;
    L9:
        if (r0 != null) goto L11;
    L13:
        if (r0 != null) goto L15;
    L17:
        char r5 = 1;
    L18:
        if (r0 != null) goto L21;
        r0 = new TL_keyboard.RichButtonStyle();
    L21:
        r0.flags = 0;
        if (r5 != 1) goto L24;
        boolean r6 = true;
    L25:
        r0.bg_primary = r6;
        if (r5 != 2) goto L28;
        boolean r2 = true;
    L29:
        r0.bg_danger = r2;
        if (r5 == 3) goto L33;
        r3 = false;
    L33:
        r0.bg_success = r3;
        r0.link = false;
        r7.style = r0;
        return;
    L28:
        r2 = false;
        goto L29
    L24:
        r6 = false;
        goto L25
    L15:
        if (r0.bg_success == false) goto L17;
        r5 = 0;
        goto L18
    L11:
        if (r0.bg_danger == false) goto L13;
        r5 = 3;
        goto L18
    L7:
        if (r0.bg_primary == false) goto L9;
        r5 = 2;
        goto L18
    }

    private void cycleInlineButtonStyle(RichEditText r9, RichInlineButtonSpan r10, int r11, int r12) {
        TL_iv.textButton r0 = r10.getButton();
        if (r0 == null) goto L45;
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L8;
        r1.flush();
    L8:
        TL_keyboard.RichButtonStyle r13 = r0.style;
        boolean r4 = true;
        if (r13 != null) goto L11;
    L13:
        if (r13 != null) goto L15;
    L17:
        if (r13 != null) goto L19;
    L21:
        char r6 = 1;
    L22:
        if (r13 != null) goto L25;
        r13 = new TL_keyboard.RichButtonStyle();
    L25:
        r13.flags = 0;
        if (r6 != 1) goto L28;
        boolean r7 = true;
    L29:
        r13.bg_primary = r7;
        if (r6 != 2) goto L32;
        boolean r3 = true;
    L33:
        r13.bg_danger = r3;
        if (r6 == 3) goto L37;
        r4 = false;
    L37:
        r13.bg_success = r4;
        r13.link = false;
        r0.style = r13;
        Editable r14 = r9.getText();
        if (r14 != null) goto L40;
        return;
    L40:
        if (r14.getSpanStart(r10) < 0) goto L46;
        r10.detach(r9);
        r14.removeSpan(r10);
        RichInlineButtonSpan r102 = new RichInlineButtonSpan(r0);
        r102.bind(r9, this.currentAccount, this.resourcesProvider);
        r14.setSpan(r102, r11, r12, 33);
        r9.notifyInlineContentChanged();
        this.delegate.onContentChanged();
        return;
    L46:
        return;
    L32:
        r3 = false;
        goto L33
    L28:
        r7 = false;
        goto L29
    L19:
        if (r13.bg_success == false) goto L21;
        r6 = 0;
        goto L22
    L15:
        if (r13.bg_danger == false) goto L17;
        r6 = 3;
        goto L22
    L11:
        if (r13.bg_primary == false) goto L13;
        r6 = 2;
        goto L22
    }

    private void deleteAcrossDetails(int r11, int r12, int r13, int r14, String[] r15) {
        int r0 = r11;
    L3:
        if (r0 <= 0) goto L7;
        if (detailsDepthBefore(r0) <= 0) goto L7;
        r0 = r0 - 1;
    L7:
        int r1 = r13;
    L8:
        int r2 = r1 + 1;
        if (r2 >= this.rows.size()) goto L13;
        if (detailsDepthBefore(r2) <= 0) goto L13;
        r1 = r2;
    L13:
        BlockRow r3 = this.rows.get(r11);
        BlockRow r4 = this.rows.get(r13);
        if (r0 == r11) goto L16;
    L21:
        boolean r112 = true;
    L22:
        if (r1 == r13) goto L24;
    L29:
        boolean r132 = true;
    L30:
        StringBuilder r7 = new StringBuilder();
        int r8 = 0;
    L32:
        if (r8 >= r15.length) goto L36;
        if (r8 <= 0) goto L35;
        r7.append(' ');
    L35:
        r7.append(r15[r8]);
        r8 = r8 + 1;
        goto L32
    L36:
        String r152 = r7.toString();
        RichEditorHistory r72 = this.history;
        if (r72 == null) goto L39;
        r72.flush();
    L39:
        if (r112 == true) goto L45;
        String r113 = RichTextCell.readPlainText(r3.block);
        String r114 = r113.substring(0, Math.max(0, Math.min(r12, r113.length())));
        if (r132 == true) goto L43;
        String r122 = RichTextCell.readPlainText(r4.block);
        String r123 = r122.substring(Math.max(0, Math.min(r14, r122.length())));
    L44:
        RichTextCell.applyTextToBlock(r3.block, r114 + r152 + r123);
        this.rows.subList(r0 + 1, r2).clear();
        int r115 = r114.length() + r152.length();
    L49:
        if (this.rows.isEmpty() == false) goto L51;
        r3 = new BlockRow(new TL_iv.pageBlockParagraph());
        this.rows.add(r3);
        r115 = 0;
    L51:
        renumberAllRuns();
        this.textSelectionHelper.clear();
        this.adapter.update(false);
        RichEditorHistory r124 = this.history;
        if (r124 == null) goto L54;
        r124.record();
    L54:
        post(new RichEditorListView$$ExternalSyntheticLambda70(this, r3, r115));
        return;
    L43:
        r123 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L44
    L45:
        if (r132 == true) goto L47;
        String r116 = RichTextCell.readPlainText(r4.block);
        RichTextCell.applyTextToBlock(r4.block, r152.concat(r116.substring(Math.max(0, Math.min(r14, r116.length())))));
        this.rows.subList(r0, r1).clear();
        r115 = r152.length();
        r3 = r4;
        goto L49
    L47:
        this.rows.subList(r0, r2).clear();
        TL_iv.pageBlockParagraph r117 = new TL_iv.pageBlockParagraph();
        applyPlainText(r117, r152);
        r3 = new BlockRow(r117);
        ArrayList<BlockRow> r118 = this.rows;
        r118.add(Math.min(r0, r118.size()), r3);
        r115 = r152.length();
        goto L49
    L24:
        if (isDetailsHeader(r4) == true) goto L29;
        if (r4.detailsEnd == true) goto L29;
        r132 = false;
        goto L30
    L16:
        if (isDetailsHeader(r3) == true) goto L21;
        if (r3.detailsEnd == true) goto L21;
        r112 = false;
        goto L22
    }

    private void deleteDetails(BlockRow r3) {
        int r0 = this.rows.indexOf(r3);
        if (r0 >= 0) goto L5;
        return;
    L5:
        if (isDetailsHeader(r3) == false) goto L41;
        int r32 = matchingDetailsEnd(r0);
        if (r32 < this.rows.size()) goto L10;
        r32 = this.rows.size() - 1;
    L10:
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L13;
        r1.flush();
    L13:
        if (r32 < r0) goto L15;
        this.rows.remove(r32);
        r32 = r32 - 1;
        goto L13
    L15:
        BlockRow r33 = null;
        if (r0 <= 0) goto L18;
        BlockRow r02 = this.rows.get(r0 - 1);
    L19:
        if (r02 == null) goto L29;
        if (r02.detailsEnd == true) goto L29;
        if (isDetailsHeader(r02) == true) goto L29;
        if (isNonText(r02.block) == true) goto L29;
        r33 = r02;
    L29:
        if (this.rows.isEmpty() == false) goto L31;
        r33 = new BlockRow(new TL_iv.pageBlockParagraph());
        this.rows.add(r33);
    L31:
        this.adapter.update(false);
        RichEditorHistory r03 = this.history;
        if (r03 == null) goto L34;
        r03.record();
    L34:
        if (r33 == null) goto L40;
        post(new RichEditorListView$$ExternalSyntheticLambda72(this, r33));
        return;
    L40:
        return;
    L18:
        r02 = null;
        goto L19
    }

    private void deleteHelperSelection() {
        int r2 = this.textSelectionHelper.getStartCell();
        int r3 = this.textSelectionHelper.getStartOffset();
        int r4 = this.textSelectionHelper.getEndCell();
        if (applyEditRange(r2, r3, r4, this.textSelectionHelper.getEndOffset(), new String[]{_UrlKt.FRAGMENT_ENCODE_SET}) == true) goto L6;
        removeSelectedBlocks(r2, r4);
        return;
    }

    private static boolean demotesToParagraph(TL_iv.PageBlock r1) {
        if (isHeading(r1) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockPreformatted) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == true) goto L14;
        return false;
    L14:
        return true;
    }

    private int detailsDepthBefore(int r4) {
        int r0 = 0;
        int r1 = 0;
    L3:
        if (r0 >= r4) goto L13;
        if (r0 >= this.rows.size()) goto L13;
        if (isDetailsHeader(this.rows.get(r0)) == false) goto L10;
        r1 = r1 + 1;
    L12:
        r0 = r0 + 1;
        goto L3
    L10:
        if (this.rows.get(r0).detailsEnd == false) goto L12;
        r1 = r1 - 1;
    L13:
        return r1;
    }

    private RichEditText detailsEditText(int r3) {
        if (isDetailsHeader(rowForCell(r3)) == true) goto L5;
        return null;
    L5:
        View r2 = selectableAt(r3);
        if ((r2 instanceof RichDetailsCell) == true) goto L8;
        return null;
    L8:
        return ((RichDetailsCell) r2).getEditText();
    }

    private void dismissTableCellMenu() {
        ItemOptions r0 = this.tableCellMenu;
        if (r0 == null) goto L6;
        this.tableCellMenu = null;
        r0.dismiss();
        return;
    }

    private static int distinctSelectedCols(TableModel r2, Set<TL_iv.pageTableCell> r3) {
        HashSet r0 = new HashSet();
        Iterator<TL_iv.pageTableCell> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L7;
        r0.add(Integer.valueOf(r2.anchorColOf(r32.next())));
        goto L4
    L7:
        return r0.size();
    }

    private static int distinctSelectedRows(TableModel r2, Set<TL_iv.pageTableCell> r3) {
        HashSet r0 = new HashSet();
        Iterator<TL_iv.pageTableCell> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L7;
        r0.add(Integer.valueOf(r2.anchorRowOf(r32.next())));
        goto L4
    L7:
        return r0.size();
    }

    private void drawQuoteBars(Canvas r21) {
        int r8 = getChildCount();
        if (r8 == 0) goto L58;
        int r1 = 0;
        int r10 = 0;
    L6:
        if (r1 >= r8) goto L13;
        BlockRow r2 = rowOfChild(getChildAt(r1));
        if (r2 == null) goto L12;
        if (r2.quoteIds.size() <= r10) goto L12;
        r10 = r2.quoteIds.size();
    L12:
        r1 = r1 + 1;
        goto L6
    L13:
        if (r10 != 0) goto L16;
        return;
    L16:
        if (this.quoteLine != null) goto L18;
        ReplyMessageLine r22 = new ReplyMessageLine(this);
        this.quoteLine = r22;
        r22.check(null, null, null, this.resourcesProvider, 1);
        RichBlockChrome.applyEditorQuoteColor(this.quoteLine, this.resourcesProvider);
    L18:
        int r23 = 0;
    L19:
        if (r23 >= r10) goto L68;
        long r13 = 0;
        float r5 = 1.0f;
        BlockRow r6 = null;
        BlockRow r7 = null;
        int r15 = 0;
        float r3 = 0.0f;
        float r4 = 0.0f;
        boolean r12 = false;
    L21:
        if (r15 > r8) goto L57;
        if (r15 >= r8) goto L29;
        View r11 = getChildAt(r15);
        BlockRow r122 = rowOfChild(r11);
        if (r122 != null) goto L26;
    L28:
        int r17 = r8;
        Long r9 = null;
    L31:
        if (r12 == false) goto L36;
        if (r9 != null) goto L34;
    L35:
        BlockRow r14 = r6;
        int r62 = quoteBgVinset(r23, r14, true);
        BlockRow r16 = r14;
        BlockRow r18 = r7;
        BlockRow r19 = r18;
        drawQuoteContainer(r21, r23, r3, r4, r5, r62, quoteBgVinset(r23, r18, false));
        r12 = false;
    L38:
        if (r11 == null) goto L55;
        if (r9 == null) goto L55;
        if (r12 == true) goto L42;
        r13 = r9.longValue();
        r3 = Float.MAX_VALUE;
        r4 = -3.4028235E38f;
        r6 = r122;
        r12 = true;
        r5 = 1.0f;
    L43:
        if (r122 != null) goto L45;
    L48:
        float r72 = r11.getY();
        float r92 = r11.getHeight() + r72;
        if (r72 >= r3) goto L52;
        r3 = r72;
    L52:
        if (r92 <= r4) goto L54;
        r4 = r92;
    L54:
        r5 = Math.min(r5, r11.getAlpha());
    L47:
        r7 = r122;
    L56:
        r15 = r15 + 1;
        r8 = r17;
        goto L21
    L45:
        if (r122 == this.draggingRow) goto L47;
    L42:
        r6 = r16;
    L55:
        r6 = r16;
        r7 = r19;
        goto L56
    L34:
        if (r9.longValue() != r13) goto L35;
    L36:
        r16 = r6;
        r19 = r7;
        goto L38
    L26:
        if (r23 >= r122.quoteIds.size()) goto L28;
        r9 = r122.quoteIds.get(r23);
        r17 = r8;
        goto L31
    L29:
        r17 = r8;
        r9 = null;
        r11 = null;
        r122 = null;
        goto L31
    L57:
        r23 = r23 + 1;
        r8 = r8;
        goto L19
    L68:
        return;
    }

    private void drawQuoteContainer(Canvas r6, int r7, float r8, float r9, float r10, int r11, int r12) {
        if ((r9 - r8) <= AndroidUtilities.dp(4.0f)) goto L10;
        int r72 = r7 * AndroidUtilities.dp(16.0f);
        int r1 = AndroidUtilities.dp(16.0f) + r72;
        int r2 = (getWidth() - AndroidUtilities.dp(16.0f)) - r72;
        if ((r2 - r1) > AndroidUtilities.dp(8.0f)) goto L8;
        return;
    L8:
        float r73 = (float) Math.floor(SharedConfig.bubbleRadius / 3.0f);
        RectF r82 = AndroidUtilities.rectTmp;
        r82.set(r1, r11 + r8, r2, r9 - r12);
        this.quoteLine.drawBackground(r6, r82, r73, r73, r73, r10);
        this.quoteLine.drawLine(r6, r82, r10);
        return;
    }

    private void emitLeafBlock(BlockRow r10, ArrayList<TL_iv.PageBlock> r11) {
        TL_iv.PageBlock r9 = r10.block;
        if ((r9 instanceof TL_iv.pageBlockDivider) == false) goto L7;
        r11.add(r9);
        return;
    L7:
        if ((r9 instanceof TL_iv.pageBlockPhoto) == false) goto L20;
        MediaUploadState r92 = r10.media;
        if (r92 != null) goto L11;
        return;
    L11:
        if (r92.isReady() == false) goto L123;
        TL_iv.PageBlock r93 = r10.block;
        if (((TL_iv.pageBlockPhoto) r93).photo_id != 0) goto L15;
        return;
    L15:
        if (r93.caption != null) goto L17;
        r93.caption = new TL_iv.PageCaption();
        r10.block.caption.text = new TL_iv.textEmpty();
        r10.block.caption.credit = new TL_iv.textEmpty();
    L17:
        r11.add(r10.block);
        return;
    L123:
        return;
    L20:
        if ((r9 instanceof TL_iv.pageBlockVideo) == false) goto L32;
        MediaUploadState r94 = r10.media;
        if (r94 != null) goto L24;
        return;
    L24:
        if (r94.isReady() == false) goto L126;
        TL_iv.PageBlock r95 = r10.block;
        if (((TL_iv.pageBlockVideo) r95).video_id != 0) goto L28;
        return;
    L28:
        if (r95.caption != null) goto L30;
        r95.caption = new TL_iv.PageCaption();
        r10.block.caption.text = new TL_iv.textEmpty();
        r10.block.caption.credit = new TL_iv.textEmpty();
    L30:
        r11.add(r10.block);
        return;
    L126:
        return;
    L32:
        boolean r96 = isGallery(r9);
        TL_iv.PageBlock r0 = r10.block;
        if (r96 == false) goto L59;
        ArrayList<TL_iv.PageBlock> r97 = galleryItems(r0);
        List<MediaUploadState> r02 = mediasOf(r10);
        ArrayList<TL_iv.PageBlock> r3 = new ArrayList();
        int r5 = 0;
    L35:
        if (r97 == null) goto L47;
        if (r5 >= r97.size()) goto L47;
        if (r5 >= r02.size()) goto L47;
        TL_iv.PageBlock r6 = r97.get(r5);
        if (r02.get(r5).isReady() == false) goto L45;
        if (mediaIdOf(r6) == 0) goto L45;
        RichCaptionController.ensureCaption(r6);
        r3.add(r6);
    L45:
        r5 = r5 + 1;
    L47:
        if (r3.size() < 2) goto L55;
        if ((r10.block instanceof TL_iv.pageBlockSlideshow) == false) goto L51;
        TL_iv.pageBlockSlideshow r98 = new TL_iv.pageBlockSlideshow();
        r98.items = r3;
        r98.caption = r10.block.caption;
        TL_iv.PageBlock r99 = r98;
    L52:
        RichCaptionController.ensureCaption(r99);
        r11.add(r99);
        return;
    L51:
        TL_iv.pageBlockCollage r910 = new TL_iv.pageBlockCollage();
        r910.items = r3;
        r910.caption = r10.block.caption;
        r99 = r910;
        goto L52
    L55:
        if (r3.size() != 1) goto L128;
        r11.add(r3.get(0));
        return;
    L128:
        return;
    L59:
        if ((r0 instanceof TL_iv.pageBlockAudio) == false) goto L72;
        MediaUploadState r911 = r10.media;
        if (r911 != null) goto L63;
        return;
    L63:
        if (r911.isReady() == false) goto L130;
        TL_iv.PageBlock r912 = r10.block;
        if (((TL_iv.pageBlockAudio) r912).audio_id != 0) goto L67;
        return;
    L67:
        if (r912.caption != null) goto L69;
        r912.caption = new TL_iv.PageCaption();
        r10.block.caption.text = new TL_iv.textEmpty();
        r10.block.caption.credit = new TL_iv.textEmpty();
    L69:
        r11.add(r10.block);
        return;
    L130:
        return;
    L72:
        if ((r0 instanceof TL_iv.pageBlockDocument) == false) goto L86;
        MediaUploadState r913 = r10.media;
        if (r913 == null) goto L78;
        TLRPC.Document r32 = r913.document;
        if (r32 == null) goto L78;
        ((TL_iv.pageBlockDocument) r0).document_id = r32.id;
    L78:
        if (r913 != null) goto L80;
        return;
    L80:
        if (r913.isReady() == false) goto L133;
        TL_iv.PageBlock r914 = r10.block;
        if (((TL_iv.pageBlockDocument) r914).document_id == 0) goto L134;
        RichCaptionController.ensureCaption(r914);
        r11.add(r10.block);
        return;
    L134:
        return;
    L133:
        return;
    L86:
        if ((r0 instanceof TL_iv.pageBlockMap) == false) goto L95;
        TL_iv.pageBlockMap r03 = (TL_iv.pageBlockMap) r0;
        if (RichMapCell.hasGeo(r03) == true) goto L90;
        return;
    L90:
        if (r03.caption != null) goto L92;
        TL_iv.PageCaption r915 = new TL_iv.PageCaption();
        r03.caption = r915;
        r915.text = new TL_iv.textEmpty();
        r03.caption.credit = new TL_iv.textEmpty();
    L92:
        r11.add(r03);
        return;
    L95:
        if ((r0 instanceof TL_iv.pageBlockMath) == false) goto L101;
        if (TextUtils.isEmpty(((TL_iv.pageBlockMath) r0).source) == true) goto L136;
        r11.add(r10.block);
        return;
    L136:
        return;
    L101:
        if ((r0 instanceof TL_iv.pageBlockTable) == false) goto L107;
        TL_iv.pageBlockTable r04 = (TL_iv.pageBlockTable) r0;
        TableModel.normalizeForSend(r04);
        if (tableHasText(r04) == false) goto L137;
        r11.add(r04);
        return;
    L137:
        return;
    L107:
        if ((r0 instanceof TL_iv.pageBlockButtonRow) == false) goto L114;
        TL_iv.pageBlockButtonRow r05 = (TL_iv.pageBlockButtonRow) r0;
        ArrayList<TL_keyboard.PageButton> r916 = r05.buttons;
        if (r916 != null) goto L111;
        return;
    L111:
        if (r916.isEmpty() == true) goto L139;
        r11.add(r05);
        return;
    L139:
        return;
    L114:
        r11.add(r0);
    }

    private static boolean endsWithOwnParagraph(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockPreformatted) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TL_iv.pageBlockPullquote) == true) goto L14;
        return false;
    L14:
        return true;
    }

    private static void ensureSerializable(TL_iv.PageBlock r2) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.text != null) goto L8;
        r2.text = new TL_iv.textEmpty();
    L8:
        if ((r2 instanceof TL_iv.pageBlockPreformatted) == false) goto L14;
        TL_iv.pageBlockPreformatted r0 = (TL_iv.pageBlockPreformatted) r2;
        if (r0.language != null) goto L14;
        r0.language = _UrlKt.FRAGMENT_ENCODE_SET;
        return;
    L14:
        if ((r2 instanceof TL_iv.pageBlockBlockquote) == false) goto L20;
        TL_iv.pageBlockBlockquote r02 = (TL_iv.pageBlockBlockquote) r2;
        if (r02.caption != null) goto L20;
        r02.caption = new TL_iv.textEmpty();
        return;
    L20:
        if ((r2 instanceof TL_iv.pageBlockPullquote) == false) goto L25;
        TL_iv.pageBlockPullquote r22 = (TL_iv.pageBlockPullquote) r2;
        if (r22.caption != null) goto L26;
        r22.caption = new TL_iv.textEmpty();
        return;
    L26:
        return;
    }

    private void exitCellSelectionMode() {
        if (this.cellSelectionDragActive == false) goto L5;
        requestDisallowInterceptTouchEvent(false);
    L5:
        this.cellSelectionDragActive = false;
        this.clearDraggedMultiCellSelectionOnMenuDismiss = false;
        this.cellSelectionDragAnchor = null;
        this.cellSelectionDragEnd = null;
        dismissTableCellMenu();
        RichTableCell r2 = this.activeCellSelectionTable;
        if (r2 == null) goto L8;
        r2.clearCellSelection();
        this.activeCellSelectionTable = null;
    L8:
        this.dotSelectedCol = -1;
        this.dotSelectedRow = -1;
        setEditTextsLocked(false);
    }

    public static void expandBlockquoteBlocks(ArrayList<BlockRow> r6, TL_iv.pageBlockBlockquoteBlocks r7, Map<Long, TL_iv.RichText> r8) {
        long r0 = RichContainer.newId();
        int r2 = r6.size();
        flattenBlocks(r6, r7.blocks, r8);
    L4:
        if (r2 >= r6.size()) goto L6;
        r6.get(r2).quoteIds.add(0, Long.valueOf(r0));
        r2 = r2 + 1;
        goto L4
    L6:
        if (r8 == null) goto L14;
        TL_iv.RichText r62 = r7.caption;
        if (r62 != null) goto L10;
        return;
    L10:
        if ((r62 instanceof TL_iv.textEmpty) == true) goto L16;
        r8.put(Long.valueOf(r0), r7.caption);
        return;
    L16:
        return;
    }

    private static void expandItemBlocks(ArrayList<BlockRow> r9, ArrayList<TL_iv.PageBlock> r10, int r11, boolean r12, int r13, boolean r14, boolean r15) {
        boolean r0 = false;
        if (r10 == null) goto L23;
        int r6 = 0;
    L6:
        if (r6 >= r10.size()) goto L23;
        TL_iv.PageBlock r7 = r10.get(r6);
        if ((r7 instanceof TL_iv.pageBlockList) == true) goto L19;
        if ((r7 instanceof TL_iv.pageBlockOrderedList) == true) goto L19;
        if (r0 == false) goto L14;
        r9.add(new BlockRow(r7, r11, r12 ? 1 : 0));
    L22:
        r6 = r6 + 1;
        goto L6
    L14:
        if ((r7 instanceof TL_iv.pageBlockParagraph) == false) goto L16;
        addListItemRow(r9, ((TL_iv.pageBlockParagraph) r7).text, r11, r13, r14, r15);
    L17:
        r0 = true;
        goto L22
    L16:
        BlockRow r02 = new BlockRow(r7, r11, r13);
        r02.checkbox = r14;
        r02.checked = r15;
        r9.add(r02);
    L19:
        if (r0 == true) goto L21;
        addListItemRow(r9, null, r11, r13, r14, r15);
        r0 = true;
    L21:
        expandListBlock(r9, r7, r11 + 1);
    L23:
        if (r0 == true) goto L30;
        addListItemRow(r9, null, r11, r13, r14, r15);
        return;
    }

    private static void expandListBlock(ArrayList<BlockRow> r16, TL_iv.PageBlock r17, int r18) {
        int r2 = 0;
        if ((r17 instanceof TL_iv.pageBlockOrderedList) == false) goto L13;
        ArrayList<TL_iv.PageListOrderedItem> r0 = ((TL_iv.pageBlockOrderedList) r17).items;
        int r1 = r0.size();
        int r6 = 1;
    L5:
        if (r2 >= r1) goto L21;
        TL_iv.PageListOrderedItem r3 = r0.get(r2);
        r2 = r2 + 1;
        TL_iv.PageListOrderedItem r32 = r3;
        if ((r32 instanceof TL_iv.TL_pageListOrderedItemText) == true) goto L8;
        if ((r32 instanceof TL_iv.TL_pageListOrderedItemBlocks) == false) goto L5;
        int r7 = r6;
        expandItemBlocks(r16, ((TL_iv.TL_pageListOrderedItemBlocks) r32).blocks, r18, true, r7, r32.checkbox, r32.checked);
        r6 = r7;
    L12:
        r6 = r6 + 1;
        goto L5
    L8:
        addListItemRow(r16, ((TL_iv.TL_pageListOrderedItemText) r32).text, r18, r6, r32.checkbox, r32.checked);
        goto L12
    L21:
        return;
    L13:
        ArrayList<TL_iv.PageListItem> r02 = ((TL_iv.pageBlockList) r17).items;
        int r12 = r02.size();
    L14:
        if (r2 >= r12) goto L36;
        TL_iv.PageListItem r33 = r02.get(r2);
        r2 = r2 + 1;
        TL_iv.PageListItem r34 = r33;
        if ((r34 instanceof TL_iv.TL_pageListItemText) == true) goto L17;
        if ((r34 instanceof TL_iv.TL_pageListItemBlocks) == false) goto L14;
        expandItemBlocks(r16, ((TL_iv.TL_pageListItemBlocks) r34).blocks, r18, false, 0, r34.checkbox, r34.checked);
        goto L14
    L17:
        addListItemRow(r16, ((TL_iv.TL_pageListItemText) r34).text, r18, 0, r34.checkbox, r34.checked);
        goto L14
    }

    private void fillItems(ArrayList<UItem> r14, UniversalAdapter r15) {
        assignContainers();
        this.itemRows.clear();
        HashMap r0 = new HashMap();
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r2 >= this.rows.size()) goto L16;
        BlockRow r4 = this.rows.get(r2);
        if (r4.detailsEnd == true) goto L15;
        int r6 = 0;
    L10:
        if (r6 >= r4.quoteIds.size()) goto L13;
        r0.merge(r4.quoteIds.get(r6), 1, new RichEditorListView$$ExternalSyntheticLambda65());
        r6 = r6 + 1;
        goto L10
    L13:
        if ((r4.block instanceof TL_iv.pageBlockParagraph) == false) goto L15;
        r3 = r3 + 1;
    L15:
        r2 = r2 + 1;
        goto L4
    L16:
        if (r3 != 1) goto L18;
        boolean r22 = true;
    L19:
        ArrayList r32 = new ArrayList();
        BlockRow r62 = null;
        int r7 = 0;
        boolean r8 = false;
        int r9 = -1;
    L21:
        if (r7 >= this.rows.size()) goto L115;
        BlockRow r10 = this.rows.get(r7);
        if (r10.detailsEnd == false) goto L38;
        if (r9 == (-1)) goto L26;
        boolean r11 = true;
    L28:
        if (r32.isEmpty() == true) goto L30;
        r32.remove(r32.size() - 1);
    L30:
        if (r9 != (-1)) goto L32;
    L34:
        if (r11 == true) goto L114;
        if (r8 == false) goto L37;
        r15.reorderSectionEnd();
        r8 = false;
    L37:
        r14.add(RichDetailsEndCell.Factory.of(r10));
        this.itemRows.add(r10);
    L114:
        r7 = r7 + 1;
        goto L21
    L32:
        if (r32.size() >= r9) goto L34;
        r9 = -1;
        goto L114
    L26:
        r11 = false;
        goto L28
    L38:
        if (r9 == (-1)) goto L40;
        boolean r112 = true;
    L42:
        if (isDetailsHeader(r10) == false) goto L55;
        if (r112 == true) goto L54;
        boolean r113 = ((TL_iv.pageBlockDetails) r10.block).open;
        if (r32.isEmpty() == false) goto L49;
        if (r113 == true) goto L49;
        if (r8 == true) goto L51;
        r15.reorderSectionStart();
        r8 = true;
    L51:
        r14.add(RichDetailsCell.Factory.of(r10, this.detailsDelegate));
        this.itemRows.add(r10);
        if (r113 == true) goto L54;
        r9 = r32.size() + 1;
    L49:
        if (r8 == false) goto L51;
        r15.reorderSectionEnd();
        r8 = false;
    L54:
        r32.add(r10);
        goto L114
    L55:
        if (r112 == true) goto L114;
        if (r62 != null) goto L59;
    L62:
        if (r62 == null) goto L65;
        injectClosingQuoteAuthors(r62, r10.quoteIds, r0, r14);
    L65:
        if (r32.isEmpty() == false) goto L68;
        if (r8 == true) goto L70;
        r15.reorderSectionStart();
        r8 = true;
    L70:
        TL_iv.PageBlock r63 = r10.block;
        if ((r63 instanceof TL_iv.pageBlockDivider) == false) goto L74;
        r14.add(RichDividerCell.Factory.of(r10, this.dividerDelegate));
    L113:
        this.itemRows.add(r10);
        r62 = r10;
        goto L114
    L74:
        if (isMedia(r63) == false) goto L76;
        r14.add(RichMediaCell.Factory.of(r10, this.mediaDelegate));
        goto L113
    L76:
        TL_iv.PageBlock r64 = r10.block;
        if ((r64 instanceof TL_iv.pageBlockAudio) == false) goto L80;
        r14.add(RichAudioCell.Factory.of(r10, this.audioDelegate));
        goto L113
    L80:
        if ((r64 instanceof TL_iv.pageBlockDocument) == false) goto L83;
        r14.add(RichDocumentCell.Factory.of(r10, this.documentDelegate));
        goto L113
    L83:
        if ((r64 instanceof TL_iv.pageBlockMap) == false) goto L86;
        r14.add(RichMapCell.Factory.of(r10, this.mapDelegate));
        goto L113
    L86:
        if ((r64 instanceof TL_iv.pageBlockMath) == false) goto L89;
        r14.add(RichMathCell.Factory.of(r10, this.mathDelegate));
        goto L113
    L89:
        if ((r64 instanceof TL_iv.pageBlockTable) == false) goto L92;
        r14.add(RichTableCell.Factory.of(r10, this.tableDelegate));
        goto L113
    L92:
        if ((r64 instanceof TL_iv.pageBlockButtonRow) == false) goto L94;
        r14.add(RichButtonRowCell.Factory.of(r10, this.buttonRowDelegate));
        goto L113
    L94:
        if (r7 != 0) goto L96;
        boolean r114 = true;
    L97:
        r10.firstBlock = r114;
        if (r22 == true) goto L100;
    L102:
        boolean r65 = false;
    L103:
        r10.singleParagraph = r65;
        if (this.rows.size() != 2) goto L111;
        if (r7 != 1) goto L111;
        if ((r10.block instanceof TL_iv.pageBlockParagraph) == false) goto L111;
        if ((this.rows.get(0).block instanceof TL_iv.pageBlockHeading1) == false) goto L111;
        boolean r66 = true;
    L112:
        r14.add(RichTextCell.Factory.of(r10, this.cellDelegate, r66));
    L111:
        r66 = false;
        goto L112
    L100:
        if ((r64 instanceof TL_iv.pageBlockParagraph) == false) goto L102;
        r65 = true;
        goto L103
    L96:
        r114 = false;
        goto L97
    L68:
        if (r8 == false) goto L70;
        r15.reorderSectionEnd();
        r8 = false;
        goto L70
    L59:
        if (willInjectAuthors(r62, r10.quoteIds, r0) == false) goto L62;
        if (r8 == false) goto L62;
        r15.reorderSectionEnd();
        r8 = false;
        goto L62
    L40:
        r112 = false;
        goto L42
    L115:
        if (r8 == false) goto L117;
        r15.reorderSectionEnd();
    L117:
        if (r62 == null) goto L119;
        injectClosingQuoteAuthors(r62, EMPTY_QUOTE_IDS, r0, r14);
    L119:
        markQuoteEdges();
        return;
    L18:
        r22 = false;
        goto L19
    }

    /* JADX WARN: Multi-variable type inference failed */
    private RichCaptionHost findCaptionHostAncestor(View r1) {
        if ((r1 instanceof RichCaptionHost) == true) goto L5;
        ViewParent r0 = r1.getParent();
    L7:
        if (r0 == null) goto L13;
        if ((r0 instanceof RichCaptionHost) == true) goto L11;
        r0 = r0.getParent();
        goto L7
    L11:
        return (RichCaptionHost) r0;
    L13:
        return null;
    L5:
        return (RichCaptionHost) r1;
    }

    private View findCellUnder(int r5, int r6) {
        int r62 = r6 - getTop();
        RichTableCell r0 = this.activeCellSelectionTable;
        if (r0 != null) goto L6;
        r0 = findFocusedTableCell();
    L6:
        if (r0 != null) goto L8;
    L14:
        int r02 = 0;
    L16:
        if (r02 >= getChildCount()) goto L27;
        View r1 = getChildAt(r02);
        if (r62 < r1.getTop()) goto L26;
        if (r62 >= r1.getBottom()) goto L26;
        if (r5 < r1.getLeft()) goto L26;
        if (r5 >= r1.getRight()) goto L26;
        return r1;
    L26:
        r02 = r02 + 1;
        goto L16
    L27:
        return null;
    L8:
        if (r0.getParent() != this) goto L14;
        int r12 = r5 - r0.getLeft();
        int r2 = r62 - r0.getTop();
        if (r0.findRowHandleAt(r12, r2) < 0) goto L12;
    L13:
        return r0;
    L12:
        if (r0.findColHandleAt(r12, r2) < 0) goto L14;
        goto L13
    }

    private RichQuoteAuthorCell findFocusedAuthorCell() {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L11;
        View r1 = getChildAt(r0);
        if ((r1 instanceof RichQuoteAuthorCell) == false) goto L10;
        RichQuoteAuthorCell r12 = (RichQuoteAuthorCell) r1;
        if (r12.authorEditText.isFocused() == false) goto L10;
        return r12;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return null;
    }

    private BlockRow findFocusedNavRow() {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L28;
        KeyEvent.Callback r1 = getChildAt(r0);
        if ((r1 instanceof RichTextCell) == false) goto L16;
        RichTextCell r12 = (RichTextCell) r1;
        if (r12.getEditText().isFocused() == true) goto L14;
        if (r12.isAuthorVisible() == false) goto L27;
        if (r12.isAuthorFocused() == true) goto L14;
    L27:
        r0 = r0 + 1;
    L14:
        return r12.getRow();
    L16:
        if ((r1 instanceof RichCaptionHost) == false) goto L22;
        RichCaptionHost r13 = (RichCaptionHost) r1;
        if (r13.getCaptionEditText().isFocused() == false) goto L27;
        return r13.getRow();
    L22:
        if ((r1 instanceof RichDetailsCell) == false) goto L27;
        RichDetailsCell r14 = (RichDetailsCell) r1;
        if (r14.getEditText().isFocused() == false) goto L27;
        return r14.getRow();
    L28:
        return null;
    }

    private TLRPC.Document findLoadedDocument(long r7) {
        if (r7 != 0) goto L6;
        return null;
    L6:
        int r0 = 0;
        int r1 = 0;
    L8:
        if (r1 >= this.rows.size()) goto L20;
        Iterator<MediaUploadState> r2 = mediasOf(this.rows.get(r1)).iterator();
    L11:
        if (r2.hasNext() == false) goto L19;
        MediaUploadState r3 = r2.next();
        if (r3 == null) goto L11;
        TLRPC.Document r32 = r3.document;
        if (r32 == null) goto L11;
        if (r32.id != r7) goto L11;
        return r32;
    L19:
        r1 = r1 + 1;
        goto L8
    L20:
        TL_iv.RichMessage r6 = this.loadedRichMessage;
        if (r6 == null) goto L32;
        ArrayList<TLRPC.Document> r62 = r6.documents;
        if (r62 == null) goto L32;
        int r12 = r62.size();
    L25:
        if (r0 >= r12) goto L32;
        TLRPC.Document r22 = r62.get(r0);
        r0 = r0 + 1;
        TLRPC.Document r23 = r22;
        if (r23 == null) goto L25;
        if (r23.id != r7) goto L25;
        return r23;
    L32:
        return RichMediaClipboard.document(r7);
    }

    private TLRPC.Photo findLoadedPhoto(long r7) {
        if (r7 != 0) goto L6;
        return null;
    L6:
        int r0 = 0;
        int r1 = 0;
    L8:
        if (r1 >= this.rows.size()) goto L20;
        Iterator<MediaUploadState> r2 = mediasOf(this.rows.get(r1)).iterator();
    L11:
        if (r2.hasNext() == false) goto L19;
        MediaUploadState r3 = r2.next();
        if (r3 == null) goto L11;
        TLRPC.Photo r32 = r3.photo;
        if (r32 == null) goto L11;
        if (r32.id != r7) goto L11;
        return r32;
    L19:
        r1 = r1 + 1;
        goto L8
    L20:
        TL_iv.RichMessage r6 = this.loadedRichMessage;
        if (r6 == null) goto L32;
        ArrayList<TLRPC.Photo> r62 = r6.photos;
        if (r62 == null) goto L32;
        int r12 = r62.size();
    L25:
        if (r0 >= r12) goto L32;
        TLRPC.Photo r22 = r62.get(r0);
        r0 = r0 + 1;
        TLRPC.Photo r23 = r22;
        if (r23 == null) goto L25;
        if (r23.id != r7) goto L25;
        return r23;
    L32:
        return RichMediaClipboard.photo(r7);
    }

    private int findNextNavigableRow(int r3, int r4) {
    L2:
        if (r3 < 0) goto L19;
        if (r3 >= this.rows.size()) goto L29;
        BlockRow r0 = this.rows.get(r3);
        if (r0.detailsEnd == true) goto L17;
        if (isHiddenByCollapse(r3) == true) goto L17;
        TL_iv.PageBlock r02 = r0.block;
        if (isNonText(r02) == false) goto L18;
        if (hasCaption(r02) == true) goto L18;
        if ((r02 instanceof TL_iv.pageBlockTable) == false) goto L17;
    L18:
        return r3;
    L17:
        r3 = r3 + r4;
        goto L2
    L29:
        return -1;
    L19:
        return -1;
    }

    private int findTableAnchorAbove(TableModel r2, int r3) {
        if (r3 >= 0) goto L5;
        return -1;
    L5:
        if (r3 >= r2.anchors().size()) goto L13;
        TL_iv.pageTableCell r32 = r2.anchors().get(r3);
        int r0 = r2.anchorRowOf(r32);
        int r33 = r2.anchorColOf(r32);
        int r02 = r0 - 1;
        if (r02 >= 0) goto L11;
        return -1;
    L11:
        return r2.flatIndexOfAnchor(r2.grid[r02][Math.min(r33, r2.colCount - 1)]);
    L13:
        return -1;
    }

    private int findTableAnchorBelow(TableModel r4, int r5) {
        if (r5 >= 0) goto L5;
        return -1;
    L5:
        if (r5 >= r4.anchors().size()) goto L13;
        TL_iv.pageTableCell r52 = r4.anchors().get(r5);
        int r0 = r4.anchorRowOf(r52);
        int r1 = r4.anchorColOf(r52);
        int r02 = r0 + Math.max(1, TableModel.spanRow(r52));
        if (r02 < r4.rowCount) goto L11;
        return -1;
    L11:
        return r4.flatIndexOfAnchor(r4.grid[r02][Math.min(r1, r4.colCount - 1)]);
    L13:
        return -1;
    }

    private RichTableCell findTableCellAncestor(View r2) {
        if (r2 != null) goto L5;
        ViewParent r22 = null;
    L6:
        if (r22 == null) goto L12;
        if ((r22 instanceof RichTableCell) == true) goto L10;
        r22 = r22.getParent();
        goto L6
    L10:
        return (RichTableCell) r22;
    L12:
        return null;
    L5:
        r22 = r2.getParent();
        goto L6
    }

    private void finishCellSelectionDrag(boolean r5) {
        RichTableCell r0 = this.activeCellSelectionTable;
        boolean r2 = false;
        if (this.cellSelectionDragActive == false) goto L5;
        requestDisallowInterceptTouchEvent(false);
    L5:
        this.cellSelectionDragActive = false;
        this.cellSelectionDragAnchor = null;
        this.cellSelectionDragEnd = null;
        if (r0 != null) goto L8;
    L10:
        this.clearDraggedMultiCellSelectionOnMenuDismiss = r2;
        if (r5 == false) goto L17;
        if (r0 != null) goto L14;
        return;
    L14:
        if (r0.hasCellSelection() == false) goto L19;
        showTableCellMenu(r0);
        return;
    L19:
        return;
    L17:
        return;
    L8:
        if (r0.getSelectedCells().size() <= 1) goto L10;
        r2 = true;
        goto L10
    }

    private void finishEditTextActionModes() {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L15;
        KeyEvent.Callback r1 = getChildAt(r0);
        if ((r1 instanceof RichTextCell) == false) goto L9;
        ((RichTextCell) r1).finishActionModes();
    L14:
        r0 = r0 + 1;
        goto L4
    L9:
        if ((r1 instanceof RichCaptionHost) == false) goto L12;
        ((RichCaptionHost) r1).getCaptionEditText().finishActionMode();
        goto L14
    L12:
        if ((r1 instanceof RichDetailsCell) == false) goto L14;
        ((RichDetailsCell) r1).getEditText().finishActionMode();
        goto L14
    }

    public static void flattenBlocks(ArrayList<BlockRow> r1, ArrayList<TL_iv.PageBlock> r2) {
        flattenBlocks(r1, r2, null);
    }

    public static void flattenBlocks(ArrayList<BlockRow> r4, ArrayList<TL_iv.PageBlock> r5, Map<Long, TL_iv.RichText> r6) {
        if (r5 == null) goto L19;
        int r0 = r5.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L33;
        TL_iv.PageBlock r2 = r5.get(r1);
        r1 = r1 + 1;
        TL_iv.PageBlock r22 = r2;
        if ((r22 instanceof TL_iv.pageBlockList) == true) goto L18;
        if ((r22 instanceof TL_iv.pageBlockOrderedList) == true) goto L18;
        if ((r22 instanceof TL_iv.pageBlockDetails) == true) goto L13;
        if ((r22 instanceof TL_iv.pageBlockBlockquoteBlocks) == true) goto L16;
        r4.add(new BlockRow(r22));
        goto L5
    L16:
        expandBlockquoteBlocks(r4, (TL_iv.pageBlockBlockquoteBlocks) r22, r6);
        goto L5
    L13:
        flattenDetails(r4, (TL_iv.pageBlockDetails) r22, r6);
    L18:
        expandListBlock(r4, r22, 1);
        goto L5
    L33:
        return;
    }

    private static void flattenDetails(ArrayList<BlockRow> r1, TL_iv.pageBlockDetails r2, Map<Long, TL_iv.RichText> r3) {
        if (r2.title != null) goto L5;
        r2.title = new TL_iv.textEmpty();
    L5:
        r1.add(new BlockRow(r2));
        int r0 = r1.size();
        flattenBlocks(r1, r2.blocks, r3);
        if (r1.size() != r0) goto L8;
        r1.add(new BlockRow(new TL_iv.pageBlockParagraph()));
    L8:
        r1.add(newDetailsEndRow());
    }

    public static ArrayList<BlockRow> flattenForCopy(TL_iv.RichMessage r1) {
        return flattenForCopy(r1, null);
    }

    public static ArrayList<BlockRow> flattenForCopy(TL_iv.RichMessage r1, Map<Long, TL_iv.RichText> r2) {
        ArrayList<BlockRow> r0 = new ArrayList();
        if (r1 == null) goto L5;
        flattenBlocks(r0, r1.blocks, r2);
    L5:
        return r0;
    }

    private ArrayList<TL_iv.PageBlock> flattenRange(int r2, int r3, boolean r4) {
        return flattenRange(r2, r3, r4, 0);
    }

    private ArrayList<TL_iv.PageBlock> flattenRange(int r9, int r10, boolean r11, int r12) {
        ArrayList<TL_iv.PageBlock> r0 = new ArrayList();
    L3:
        int r2 = r9;
    L5:
        if (r2 >= r10) goto L42;
        BlockRow r3 = this.rows.get(r2);
        if (r3.detailsEnd == true) goto L8;
        if (r3.quoteIds.size() > r12) goto L11;
        if (isDetailsHeader(r3) == true) goto L23;
        int r32 = r3.level;
        if (r32 > 0) goto L30;
        this.emitLeafBlock(r3, r0);
        r2 = r2 + 1;
        int r6 = r10;
        int r7 = r12;
        RichEditorListView r102 = this;
    L41:
        RichEditorListView this = r102;
        r10 = r6;
        r12 = r7;
        goto L5
    L30:
        int[] r5 = {r2};
        if (r3.num <= 0) goto L33;
        boolean r4 = true;
        r6 = r10;
        r7 = r12;
        RichEditorListView r1 = this;
    L34:
        TL_iv.PageBlock r8 = r1.buildListBlock(r2, r32, r4, r5, r6, r7);
        r102 = r1;
        if (r8 == null) goto L37;
        r0.add(r8);
    L37:
        int r82 = r5[0];
        if (r82 > 0) goto L40;
        r2 = r6;
        goto L41
    L40:
        r2 = r82;
        goto L41
    L33:
        r4 = false;
        r1 = this;
        r6 = r10;
        r7 = r12;
        goto L34
    L23:
        TL_iv.pageBlockDetails r92 = (TL_iv.pageBlockDetails) r3.block;
        if (r92.title != null) goto L26;
        r92.title = new TL_iv.textEmpty();
    L26:
        int r33 = this.matchingDetailsEnd(r2);
        r92.blocks = this.flattenRange(r2 + 1, Math.min(r33, r10), true, r12);
        r0.add(r92);
        r2 = r33 + 1;
        goto L5
    L11:
        long r34 = r3.quoteIds.get(r12).longValue();
        r9 = r2 + 1;
    L12:
        if (r9 >= r10) goto L18;
        BlockRow r52 = this.rows.get(r9);
        if (r52.quoteIds.size() <= r12) goto L18;
        if (r52.quoteIds.get(r12).longValue() != r34) goto L18;
        r9 = r9 + 1;
    L18:
        TL_iv.PageBlock r13 = this.buildQuoteBlock(this.flattenRange(r2, r9, true, r12 + 1), r34);
        if (r13 == null) goto L3;
        r0.add(r13);
        goto L3
    L8:
        r2 = r2 + 1;
        goto L5
    L42:
        if (r11 == false) goto L44;
    L53:
        return r0;
    L44:
        if (r0.isEmpty() == true) goto L49;
        if (isEmptyTextBlock(r0.get(0)) == false) goto L49;
        r0.remove(0);
    L49:
        if (r0.isEmpty() == true) goto L53;
        if (isEmptyTextBlock(r0.get(r0.size() - 1)) == false) goto L53;
        r0.remove(r0.size() - 1);
        goto L49
    }

    private boolean focusForDraftInternal() {
        if (this.rows.isEmpty() == false) goto L5;
        BlockRow r0 = null;
    L7:
        if (r0 != null) goto L9;
    L20:
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L23;
        r02.flush();
    L23:
        BlockRow r03 = new BlockRow(new TL_iv.pageBlockParagraph());
        this.rows.add(r03);
        this.adapter.update(false);
        RichEditorHistory r2 = this.history;
        if (r2 == null) goto L26;
        r2.record();
    L26:
        post(new RichEditorListView$$ExternalSyntheticLambda69(this, r03));
    L27:
        return true;
    L9:
        if (isNonText(r0.block) == true) goto L20;
        if (r0.detailsEnd == true) goto L20;
        if (isDetailsHeader(r0) == true) goto L20;
        if (endsWithOwnParagraph(r0.block) == true) goto L20;
        if ((findViewByItemObject(r0) instanceof RichTextCell) == true) goto L19;
        return false;
    L19:
        focusRowAtEnd(r0);
        goto L27
    L5:
        ArrayList<BlockRow> r04 = this.rows;
        r0 = r04.get(r04.size() - 1);
        goto L7
    }

    private void focusFromHistory(RichEditorHistory.FocusState r4) {
        int r0 = indexOfRowId(r4.rowId);
        if (r0 < 0) goto L23;
        KeyEvent.Callback r3 = this.layoutManager.findViewByPosition(r0);
        if ((r3 instanceof RichTextCell) == false) goto L10;
        RichTextCell r32 = (RichTextCell) r3;
        r32.requestEditFocus();
        RichEditText r33 = r32.getEditText();
        int r02 = r33.length();
        r33.setSelection(Math.max(0, Math.min(r4.selStart, r02)), Math.max(0, Math.min(r4.selEnd, r02)));
        return;
    L10:
        if ((r3 instanceof RichTableCell) == false) goto L19;
        RichTableCell r34 = (RichTableCell) r3;
        RichEditText r03 = r34.editTextForChildPos(r4.childIndex);
        if (r03 != null) goto L14;
        r03 = r34.editTextForChildPos(r34.titleChildPos());
    L14:
        if (r03 == null) goto L24;
        r03.requestEditFocus();
        int r35 = r03.length();
        r03.setSelection(Math.max(0, Math.min(r4.selStart, r35)), Math.max(0, Math.min(r4.selEnd, r35)));
        return;
    L24:
        return;
    L19:
        if ((r3 instanceof RichCaptionHost) == false) goto L22;
        RichEditText r36 = ((RichCaptionHost) r3).getCaptionEditText();
        r36.requestEditFocus();
        int r04 = r36.length();
        r36.setSelection(Math.max(0, Math.min(r4.selStart, r04)), Math.max(0, Math.min(r4.selEnd, r04)));
        return;
    L22:
        return;
    }

    private void focusItemRow(BlockRow r3, boolean r4) {
        View r0 = findViewByItemObject(r3);
        if ((r0 instanceof RichQuoteAuthorCell) == false) goto L10;
        RichEditText r2 = ((RichQuoteAuthorCell) r0).authorEditText;
        r2.requestEditFocus();
        if (r4 == false) goto L7;
        int r32 = r2.length();
    L8:
        r2.setSelection(r32);
        return;
    L7:
        r32 = 0;
        goto L8
    L10:
        focusNavRow(r3, r4);
    }

    private void focusNavRow(BlockRow r4, boolean r5) {
        View r0 = findViewByItemObject(r4);
        if ((r0 instanceof RichTableCell) == false) goto L6;
        ((RichTableCell) r0).focusEdgeCell(r5);
        return;
    L6:
        if (r5 == true) goto L8;
    L13:
        RichEditText r02 = navEditTextOf(r0);
        if (r02 != null) goto L17;
        focusRow(r4);
        return;
    L17:
        r02.requestEditFocus();
        if (r5 == false) goto L21;
        r02.setSelection(r02.length());
        return;
    L21:
        return;
    L8:
        if ((r0 instanceof RichTextCell) == false) goto L13;
        RichTextCell r1 = (RichTextCell) r0;
        if (r1.isAuthorVisible() == false) goto L13;
        r1.focusAuthorEnd();
    }

    private void focusRow(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichTextCell) == false) goto L7;
        ((RichTextCell) r0).requestEditFocus();
        return;
    L7:
        if ((r0 instanceof RichDetailsCell) == false) goto L10;
        ((RichDetailsCell) r0).requestEditFocus();
        return;
    }

    private void focusRowAtEnd(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichTextCell) == false) goto L7;
        RichTextCell r02 = (RichTextCell) r0;
        r02.requestEditFocus();
        RichEditText r03 = r02.getEditText();
        r03.setSelection(r03.length());
        return;
    L7:
        if ((r0 instanceof RichDetailsCell) == false) goto L10;
        RichDetailsCell r04 = (RichDetailsCell) r0;
        r04.requestEditFocus();
        RichEditText r05 = r04.getEditText();
        r05.setSelection(r05.length());
        return;
    }

    private void focusRowAtStart(BlockRow r2) {
        View r1 = findViewByItemObject(r2);
        if ((r1 instanceof RichTextCell) == false) goto L7;
        RichTextCell r12 = (RichTextCell) r1;
        r12.requestEditFocus();
        r12.getEditText().setSelection(0);
        return;
    L7:
        if ((r1 instanceof RichDetailsCell) == false) goto L10;
        RichDetailsCell r13 = (RichDetailsCell) r1;
        r13.requestEditFocus();
        r13.getEditText().setSelection(0);
        return;
    }

    private RichEditText focusedNavEditText(View r2) {
        if ((r2 instanceof RichTextCell) == false) goto L13;
        RichTextCell r22 = (RichTextCell) r2;
        if (r22.isAuthorVisible() == false) goto L11;
        if (r22.isAuthorFocused() == false) goto L11;
        return r22.getAuthorEditText();
    L11:
        return r22.getEditText();
    L13:
        return navEditTextOf(r2);
    }

    private RichTableCell focusedTitleTable() {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L11;
        View r1 = getChildAt(r0);
        if ((r1 instanceof RichTableCell) == false) goto L10;
        RichTableCell r12 = (RichTableCell) r1;
        if (r12.getTitleEditText().isFocused() == false) goto L10;
        return r12;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return null;
    }

    public static ArrayList<TL_iv.PageBlock> galleryItems(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockCollage) == false) goto L7;
        return ((TL_iv.pageBlockCollage) r1).items;
    L7:
        if ((r1 instanceof TL_iv.pageBlockSlideshow) == true) goto L9;
        return null;
    L9:
        return ((TL_iv.pageBlockSlideshow) r1).items;
    }

    private void gcQuoteAuthors() {
        if (this.quoteAuthors.isEmpty() == false) goto L5;
        return;
    L5:
        HashSet r0 = new HashSet();
        int r1 = 0;
    L7:
        if (r1 >= this.rows.size()) goto L9;
        r0.addAll(this.rows.get(r1).quoteIds);
        r1 = r1 + 1;
        goto L7
    L9:
        this.quoteAuthors.keySet().retainAll(r0);
    }

    private boolean handleTableHandleTap(RichTableCell r3, int r4, int r5) {
        int r0 = r3.findRowHandleAt(r4, r5);
        if (r0 < 0) goto L11;
        int r42 = r3.rowHandleEnd(r0);
        if (r3 == this.activeCellSelectionTable) goto L7;
    L9:
        beginCellSelection(r3);
        r3.selectWholeRows(r0, r42);
        this.dotSelectedRow = r0;
    L10:
        return true;
    L7:
        if (r3.selectionContainsWholeRows(r0, r42) == false) goto L9;
        showTableCellMenu(r3);
        goto L10
    L11:
        int r43 = r3.findColHandleAt(r4, r5);
        if (r43 < 0) goto L20;
        int r52 = r3.colHandleEnd(r43);
        if (r3 == this.activeCellSelectionTable) goto L16;
    L18:
        beginCellSelection(r3);
        r3.selectWholeColumns(r43, r52);
        this.dotSelectedCol = r43;
    L19:
        return true;
    L16:
        if (r3.selectionContainsWholeColumns(r43, r52) == false) goto L18;
        showTableCellMenu(r3);
        goto L19
    L20:
        return false;
    }

    private static boolean hasCaption(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockMap) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockAudio) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TL_iv.pageBlockDocument) == false) goto L9;
        return true;
    L9:
        if (isMedia(r1) == true) goto L17;
        return false;
    L17:
        return true;
    }

    private boolean hasFocusedEdit() {
        return findFocus() instanceof RichEditText;
    }

    private static boolean hasInlineButton(Editable r6, int r7, int r8) {
        if (r6 == null) goto L15;
        if (r7 >= r8) goto L15;
        RichInlineButtonSpan[] r1 = (RichInlineButtonSpan[]) r6.getSpans(r7, r8, RichInlineButtonSpan.class);
        int r2 = r1.length;
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L15;
        RichInlineButtonSpan r4 = r1[r3];
        if (r6.getSpanStart(r4) >= r8) goto L14;
        if (r6.getSpanEnd(r4) <= r7) goto L14;
        return true;
    L14:
        r3 = r3 + 1;
    L15:
        return false;
    }

    private void hideEditTextActionModes() {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L18;
        KeyEvent.Callback r1 = getChildAt(r0);
        if ((r1 instanceof RichTextCell) == false) goto L9;
        ((RichTextCell) r1).hideActionModes();
    L17:
        r0 = r0 + 1;
        goto L4
    L9:
        if ((r1 instanceof RichTableCell) == false) goto L12;
        ((RichTableCell) r1).hideActionModes();
        goto L17
    L12:
        if ((r1 instanceof RichCaptionHost) == false) goto L15;
        ((RichCaptionHost) r1).getCaptionEditText().hideActionMode();
        goto L17
    L15:
        if ((r1 instanceof RichDetailsCell) == false) goto L17;
        ((RichDetailsCell) r1).getEditText().hideActionMode();
        goto L17
    }

    private void hideKeyboardIfFocusScrolledAway() {
        RichEditText r0 = this.focusedEditText;
        if (r0 != null) goto L5;
        return;
    L5:
        View r1 = findContainingItemView(r0);
        if (r1 != null) goto L8;
        return;
    L8:
        if (r1.getBottom() > 0) goto L10;
    L11:
        this.focusedEditText = null;
        r0.clearFocus();
        AndroidUtilities.hideKeyboard(this);
        return;
    L10:
        if (r1.getTop() >= getHeight()) goto L11;
    }

    private void hideTextSelectionUi(boolean r2) {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 == null) goto L7;
        if (r2 == false) goto L6;
        r0.clear();
        goto L7
    L6:
        r0.hideActionsMenu();
    L7:
        if (r2 == false) goto L10;
        finishEditTextActionModes();
        return;
    L10:
        hideEditTextActionModes();
    }

    private boolean indentKeepsDepth(int r2) {
        AppGlobalConfig r0 = MessagesController.getInstance(this.currentAccount).config;
        if ((this.rows.get(r2).level + 1) > (r0.richMessageMaxDepth.get() - 6)) goto L5;
        return true;
    L5:
        return false;
    }

    private boolean indentRow(int r4, boolean r5, boolean r6) {
        int r0 = 0;
        if (r4 >= 0) goto L5;
    L51:
        return false;
    L5:
        if (r4 >= this.rows.size()) goto L51;
        BlockRow r1 = this.rows.get(r4);
        if (r5 == false) goto L15;
        if (r1.level > 0) goto L12;
        return false;
    L12:
        cascadeOutdent(r4);
        return true;
    L15:
        if (r1.level != 0) goto L40;
        boolean r52 = isListableText(r1.block);
        if (r52 == true) goto L22;
        if (canNonTextJoinList(r4) == true) goto L22;
        return false;
    L22:
        if (indentKeepsDepth(r4) == true) goto L24;
        return false;
    L24:
        if (r4 <= 0) goto L26;
        BlockRow r3 = this.rows.get(r4 - 1);
    L27:
        if (r52 == false) goto L34;
        r1.level = 1;
        if (r3 != null) goto L31;
    L33:
        r1.num = r0;
    L39:
        return true;
    L31:
        if (r3.num <= 0) goto L33;
        r0 = 1;
        goto L33
    L34:
        r1.level = r3.level;
        if (r3.num <= 0) goto L37;
        int r32 = 1;
    L38:
        r1.num = r32;
        r1.checkbox = false;
        r1.checked = false;
        goto L39
    L37:
        r32 = 0;
        goto L38
    L26:
        r3 = null;
        goto L27
    L40:
        if (r6 == true) goto L47;
        if (r4 != 0) goto L44;
        return false;
    L44:
        if (this.rows.get(r4 - 1).level >= r1.level) goto L47;
        return false;
    L47:
        if (indentKeepsDepth(r4) == true) goto L49;
        return false;
    L49:
        r1.level++;
        return true;
    }

    private int indexOfRowId(long r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.rows.size()) goto L9;
        if (this.rows.get(r0).id == r4) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    private void injectClosingQuoteAuthors(BlockRow r8, ArrayList<Long> r9, Map<Long, Integer> r10, ArrayList<UItem> r11) {
        int r0 = r8.quoteIds.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r2 >= r9.size()) goto L9;
        if (r8.quoteIds.get(r2).equals(r9.get(r2)) == false) goto L9;
        r2 = r2 + 1;
    L9:
        int r02 = r0 - 1;
    L10:
        if (r02 < r2) goto L24;
        Long r92 = r8.quoteIds.get(r02);
        long r3 = r92.longValue();
        Integer r5 = r10.get(r92);
        if (r5 == null) goto L23;
        if (r5.intValue() >= 2) goto L19;
        if (r02 != 0) goto L19;
        if (this.quoteAuthors.containsKey(r92) == false) goto L23;
    L19:
        BlockRow r93 = new BlockRow(new TL_iv.pageBlockParagraph());
        r93.authorQuoteId = r3;
        int r32 = 0;
    L20:
        if (r32 > r02) goto L22;
        r93.quoteIds.add(r8.quoteIds.get(r32));
        r32 = r32 + 1;
        goto L20
    L22:
        r11.add(RichQuoteAuthorCell.Factory.of(r93, this.quoteAuthorDelegate));
        this.itemRows.add(r93);
    L23:
        r02 = r02 - 1;
        goto L10
    }

    private void insertInlineMath(RichEditText r11, int r12, int r13, Runnable r14) {
        String r0 = MathSpan.sourceAt(r11.getText(), r12, r13);
        if (r0 != null) goto L6;
        r0 = r11.getText().subSequence(r12, r13).toString();
    L6:
        float r5 = AndroidUtilities.dp(SharedConfig.fontSize + 4);
        ChatAttachAlertRichLayout.showEditLatexSheet(getContext(), r0, new RichEditorListView$$ExternalSyntheticLambda66(this, Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), r5, r11, r12, r13, r14), this.resourcesProvider);
    }

    private void insertParagraphAfterQuote(BlockRow r8) {
        long r0 = r8.authorQuoteId;
        if (r0 == 0) goto L28;
        int r3 = -1;
        int r4 = 0;
    L7:
        if (r4 >= this.rows.size()) goto L12;
        if (this.rows.get(r4).quoteIds.contains(Long.valueOf(r0)) == false) goto L11;
        r3 = r4;
    L11:
        r4 = r4 + 1;
        goto L7
    L12:
        if (r3 >= 0) goto L14;
        return;
    L14:
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L17;
        r02.flush();
    L17:
        BlockRow r03 = new BlockRow(new TL_iv.pageBlockParagraph());
        r03.quoteIds.addAll(r8.quoteIds);
        if (r03.quoteIds.isEmpty() == true) goto L20;
        r03.quoteIds.remove(r8.size() - 1);
    L20:
        this.rows.add(r3 + 1, r03);
        renumberAllRuns();
        this.adapter.update(false);
        RichEditorHistory r82 = this.history;
        if (r82 == null) goto L23;
        r82.record();
    L23:
        post(new RichEditorListView$$ExternalSyntheticLambda81(this, r03));
        return;
    }

    private void insertPreparedRow(BlockRow r10) {
        BlockRow r0 = findFocusedRow();
        if (r0 != null) goto L5;
        r0 = this.pendingInsertRow;
    L5:
        if (r0 == null) goto L7;
        int r1 = this.rows.indexOf(r0);
    L8:
        boolean r2 = false;
        if (r1 >= 0) goto L11;
    L18:
        boolean r4 = false;
    L19:
        if (r1 >= 0) goto L21;
    L23:
        if (r2 == false) goto L25;
        r10.quoteIds.clear();
        r10.quoteIds.addAll(r0.quoteIds);
    L25:
        if (r1 < 0) goto L33;
        TL_iv.PageBlock r5 = r0.block;
        if ((r5 instanceof TL_iv.pageBlockParagraph) == false) goto L33;
        if (r0.media != null) goto L33;
        if (RichTextCell.readPlainText(r5).isEmpty() == false) goto L33;
        this.rows.set(r1, r10);
    L37:
        this.pendingInsertRow = r10;
        int r02 = this.rows.indexOf(r10);
        if (r02 < (this.rows.size() - 1)) goto L40;
    L41:
        BlockRow r12 = new BlockRow(new TL_iv.pageBlockParagraph());
        if (r2 == false) goto L44;
        r12.quoteIds.addAll(r10.quoteIds);
    L44:
        this.rows.add(r02 + 1, r12);
    L45:
        this.adapter.update(!r4);
        int r102 = this.rows.indexOf(r10) + 1;
        if (r102 > 0) goto L48;
        return;
    L48:
        if (r102 < this.rows.size()) goto L50;
        return;
    L50:
        if (isNonText(this.rows.get(r102).block) == true) goto L55;
        post(new RichEditorListView$$ExternalSyntheticLambda73(this, this.rows.get(r102)));
        return;
    L55:
        return;
    L40:
        if (isNonText(this.rows.get(r02 + 1).block) == false) goto L45;
    L33:
        ArrayList<BlockRow> r03 = this.rows;
        if (r1 < 0) goto L36;
        r03.add(r1 + 1, r10);
        goto L37
    L36:
        r03.add(r10);
        goto L37
    L21:
        if (r0.quoteIds.isEmpty() == true) goto L23;
        r2 = true;
        goto L23
    L11:
        if ((r0.block instanceof TL_iv.pageBlockBlockquote) == false) goto L18;
        long r42 = RichContainer.newId();
        TL_iv.RichText r6 = ((TL_iv.pageBlockBlockquote) r0.block).caption;
        if (r6 != null) goto L15;
    L17:
        r0.quoteIds.add(Long.valueOf(r42));
        r0.block = new TL_iv.pageBlockParagraph();
        r4 = true;
        goto L19
    L15:
        if ((r6 instanceof TL_iv.textEmpty) == true) goto L17;
        this.quoteAuthors.put(Long.valueOf(r42), r6);
        goto L17
    L7:
        r1 = -1;
        goto L8
    }

    private void invalidateAudioCell(BlockRow r2) {
        View r1 = findViewByItemObject(r2);
        if ((r1 instanceof RichAudioCell) == false) goto L6;
        ((RichAudioCell) r1).updateButtonState(false);
        r1.invalidate();
        return;
    }

    private void invalidateDocumentCell(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichDocumentCell) == false) goto L6;
        ((RichDocumentCell) r0).refreshUploadState();
        return;
    }

    private void invalidateMediaCell(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichMediaCell) == false) goto L6;
        r0.requestLayout();
        r0.invalidate();
        return;
    }

    private static boolean isArrowKey(int r1) {
        if (r1 != 21) goto L5;
        return true;
    L5:
        if (r1 != 22) goto L7;
        return true;
    L7:
        if (r1 != 19) goto L9;
        return true;
    L9:
        if (r1 == 20) goto L17;
        return false;
    L17:
        return true;
    }

    public static boolean isDetailsHeader(BlockRow r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if ((r0.block instanceof TL_iv.pageBlockDetails) == false) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean isDetailsSelection() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
    L13:
        return false;
    L5:
        if (r0.isInSelectionMode() == false) goto L13;
        int r02 = this.textSelectionHelper.getStartCell();
        if (r02 != this.textSelectionHelper.getEndCell()) goto L13;
        if (isDetailsHeader(rowForCell(r02)) == false) goto L13;
        return true;
    }

    private boolean isDotSelection() {
        if (this.dotSelectedRow < 0) goto L5;
        return true;
    L5:
        if (this.dotSelectedCol >= 0) goto L11;
        return false;
    L11:
        return true;
    }

    private static boolean isEmptyTextBlock(TL_iv.PageBlock r2) {
        if ((r2 instanceof TL_iv.pageBlockBlockquote) == false) goto L5;
    L17:
        return false;
    L5:
        if ((r2 instanceof TL_iv.pageBlockBlockquoteBlocks) == true) goto L17;
        if ((r2 instanceof TL_iv.pageBlockPullquote) == true) goto L17;
        if (isNonText(r2) == true) goto L17;
        if ((r2 instanceof TL_iv.pageBlockDetails) == true) goto L17;
        if (RichTextCell.readPlainText(r2).isEmpty() == false) goto L17;
        return true;
    }

    private boolean isFirstOfInnermostQuote(int r7) {
        BlockRow r0 = this.rows.get(r7);
        if (r0.quoteIds.isEmpty() == false) goto L5;
        return false;
    L5:
        int r1 = r0.quoteIds.size() - 1;
        long r4 = r0.quoteIds.get(r1).longValue();
        if (r7 != 0) goto L8;
        return true;
    L8:
        BlockRow r6 = this.rows.get(r7 - 1);
        if (r6.quoteIds.size() > r1) goto L11;
    L14:
        return true;
    L11:
        if (r6.quoteIds.get(r1).longValue() != r4) goto L14;
        return false;
    }

    private static boolean isFormattable(TL_iv.PageBlock r1) {
        if (isNonText(r1) == false) goto L5;
        return false;
    L5:
        if ((r1 instanceof TL_iv.pageBlockDetails) == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isGallery(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockCollage) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockSlideshow) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isHeading(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockHeading1) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockHeading2) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TL_iv.pageBlockHeading3) == false) goto L9;
        return true;
    L9:
        if ((r1 instanceof TL_iv.pageBlockHeading4) == false) goto L11;
        return true;
    L11:
        if ((r1 instanceof TL_iv.pageBlockHeading5) == false) goto L13;
        return true;
    L13:
        if ((r1 instanceof TL_iv.pageBlockHeading6) == true) goto L23;
        return false;
    L23:
        return true;
    }

    private boolean isHiddenByCollapse(int r8) {
        int r2 = 0;
        int r4 = 0;
        int r3 = -1;
    L3:
        if (r2 >= r8) goto L18;
        BlockRow r5 = this.rows.get(r2);
        if (isDetailsHeader(r5) == false) goto L12;
        r4 = r4 + 1;
        if (r3 != (-1)) goto L17;
        if (((TL_iv.pageBlockDetails) r5.block).open == true) goto L17;
        r3 = r4;
    L17:
        r2 = r2 + 1;
        goto L3
    L12:
        if (r5.detailsEnd == false) goto L17;
        if (r3 == (-1)) goto L16;
        if (r4 != r3) goto L16;
        r3 = -1;
    L16:
        r4 = r4 - 1;
        goto L17
    L18:
        if (r3 == (-1)) goto L21;
        return true;
    L21:
        return false;
    }

    private boolean isInList(View r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (findContainingItemView(r1) == null) goto L9;
        return true;
    L9:
        return false;
    }

    private static boolean isInlineFormattable(TL_iv.PageBlock r1) {
        if (isFormattable(r1) == true) goto L5;
        return false;
    L5:
        if ((r1 instanceof TL_iv.pageBlockPreformatted) == true) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isLastOfInnermostQuote(int r7) {
        BlockRow r0 = this.rows.get(r7);
        if (r0.quoteIds.isEmpty() == false) goto L5;
        return false;
    L5:
        int r1 = r0.quoteIds.size() - 1;
        long r4 = r0.quoteIds.get(r1).longValue();
        int r72 = r7 + 1;
        if (r72 < this.rows.size()) goto L8;
        return true;
    L8:
        BlockRow r6 = this.rows.get(r72);
        if (r6.quoteIds.size() > r1) goto L11;
    L14:
        return true;
    L11:
        if (r6.quoteIds.get(r1).longValue() != r4) goto L14;
        return false;
    }

    private static boolean isListableText(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockParagraph) == false) goto L5;
        return true;
    L5:
        if (isHeading(r1) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TL_iv.pageBlockPreformatted) == false) goto L9;
        return true;
    L9:
        if ((r1 instanceof TL_iv.pageBlockFooter) == true) goto L17;
        return false;
    L17:
        return true;
    }

    private static boolean isMedia(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockPhoto) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockVideo) == false) goto L7;
        return true;
    L7:
        if (isGallery(r1) == true) goto L14;
        return false;
    L14:
        return true;
    }

    private static boolean isNonText(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockDivider) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockPhoto) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TL_iv.pageBlockVideo) == false) goto L9;
        return true;
    L9:
        if ((r1 instanceof TL_iv.pageBlockCollage) == false) goto L11;
        return true;
    L11:
        if ((r1 instanceof TL_iv.pageBlockSlideshow) == false) goto L13;
        return true;
    L13:
        if ((r1 instanceof TL_iv.pageBlockAudio) == false) goto L15;
        return true;
    L15:
        if ((r1 instanceof TL_iv.pageBlockDocument) == false) goto L17;
        return true;
    L17:
        if ((r1 instanceof TL_iv.pageBlockMath) == false) goto L19;
        return true;
    L19:
        if ((r1 instanceof TL_iv.pageBlockMap) == false) goto L21;
        return true;
    L21:
        if ((r1 instanceof TL_iv.pageBlockTable) == false) goto L23;
        return true;
    L23:
        if ((r1 instanceof TL_iv.pageBlockButtonRow) == false) goto L25;
        return true;
    L25:
        if ((r1 instanceof TL_iv.pageBlockList) == false) goto L27;
        return true;
    L27:
        if ((r1 instanceof TL_iv.pageBlockOrderedList) == true) goto L44;
        return false;
    L44:
        return true;
    }

    private static boolean isPlainParagraphRow(BlockRow r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.detailsEnd == false) goto L6;
        return false;
    L6:
        if (isDetailsHeader(r1) == false) goto L8;
        return false;
    L8:
        if ((r1.block instanceof TL_iv.pageBlockParagraph) == true) goto L10;
        return false;
    L10:
        if (r1.level == 0) goto L12;
        return false;
    L12:
        if (r1.num == 0) goto L14;
        return false;
    L14:
        if (r1.checkbox == false) goto L16;
        return false;
    L16:
        if (r1.quoteIds.isEmpty() == false) goto L27;
        return true;
    L27:
        return false;
    }

    private boolean isPressOnEmptyEditText(View r1, int r2, int r3) {
        if ((r1 instanceof RichTextCell) == false) goto L7;
        return ((RichTextCell) r1).isPressOnEmptyEditText(r2, r3);
    L7:
        if ((r1 instanceof RichDetailsCell) == true) goto L9;
        return false;
    L9:
        return ((RichDetailsCell) r1).isPressOnEmptyEditText(r2, r3);
    }

    private boolean isRangeQuoted(int r5, int r6) {
        if (r5 >= 0) goto L5;
    L23:
        return false;
    L5:
        if (r6 >= this.rows.size()) goto L23;
        if (r5 > r6) goto L23;
        boolean r1 = false;
    L9:
        if (r5 > r6) goto L22;
        BlockRow r2 = this.rows.get(r5);
        if (r2.detailsEnd == true) goto L21;
        if (isDetailsHeader(r2) == true) goto L21;
        if (r2.quoteIds.isEmpty() == true) goto L18;
    L20:
        r1 = true;
        goto L21
    L18:
        if (RichTextCell.isQuoteBlock(r2.block) == true) goto L20;
        return false;
    L21:
        r5 = r5 + 1;
        goto L9
    L22:
        return r1;
    }

    private boolean isTapAboveContent(float r5, float r6) {
        if (this.allowTapAboveContent == true) goto L6;
        return false;
    L6:
        if (r5 >= getLeft()) goto L8;
    L26:
        return false;
    L8:
        if (r5 > getRight()) goto L26;
        int r52 = 0;
    L12:
        if (r52 >= getChildCount()) goto L17;
        View r0 = getChildAt(r52);
        if (getChildAdapterPosition(r0) == 0) goto L15;
        r52 = r52 + 1;
        goto L12
    L15:
        int r53 = getTop() + r0.getTop();
    L18:
        if (r53 != Integer.MAX_VALUE) goto L21;
        return false;
    L21:
        if (r6 < (getTop() + AndroidUtilities.dp(60.0f))) goto L26;
        if (r6 > r53) goto L26;
        return true;
    L17:
        r53 = Integer.MAX_VALUE;
        goto L18
    }

    private boolean isTapBelowContent(float r6, float r7) {
        View r0 = (View) getParent();
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r6 >= getLeft()) goto L8;
    L20:
        return false;
    L8:
        if (r6 > getRight()) goto L20;
        int r62 = getTop() + getPaddingTop();
        int r2 = 0;
    L12:
        if (r2 >= getChildCount()) goto L14;
        r62 = Math.max(r62, getTop() + getChildAt(r2).getBottom());
        r2 = r2 + 1;
        goto L12
    L14:
        int r02 = (r0.getHeight() - AndroidUtilities.dp(60.0f)) - Math.max(Math.max(this.emojiPadding, this.bottomInset), this.imeInset);
        if (r7 < r62) goto L20;
        if (r7 > r02) goto L20;
        return true;
    }

    private static boolean isTextBearing(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockParagraph) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockPreformatted) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == false) goto L9;
        return true;
    L9:
        if ((r1 instanceof TL_iv.pageBlockPullquote) == false) goto L11;
        return true;
    L11:
        if (isHeading(r1) == true) goto L20;
        return false;
    L20:
        return true;
    }

    private boolean isWholeDocumentSelected() {
        if (this.rows.isEmpty() == false) goto L5;
    L30:
        return false;
    L5:
        if (this.textSelectionHelper.isInSelectionMode() == false) goto L30;
        if (this.textSelectionHelper.getStartCell() != 0) goto L30;
        if (this.textSelectionHelper.getStartChildPosition() != 0) goto L30;
        if (this.textSelectionHelper.getStartOffset() > 0) goto L30;
        int r0 = this.rows.size() - 1;
        if (this.textSelectionHelper.getEndCell() == r0) goto L17;
        return false;
    L17:
        BlockRow r02 = this.rows.get(r0);
        if (RichTextCell.isQuoteBlock(r02.block) == false) goto L20;
        String r3 = RichTextCell.readAuthorPlain(r02.block);
    L21:
        boolean r4 = !r3.isEmpty();
        if (this.textSelectionHelper.getEndChildPosition() == r4) goto L24;
        return false;
    L24:
        if (r4 != true) goto L26;
        int r03 = r3.length();
    L28:
        if (this.textSelectionHelper.getEndOffset() < r03) goto L30;
        return true;
    L26:
        r03 = RichTextCell.readPlainText(r02.block).length();
        goto L28
    L20:
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L21
    }

    private boolean isWholeTableSelected(RichTableCell r4) {
        int[] r42 = wholeTableEnd(r4);
        if (r42 != null) goto L6;
        return false;
    L6:
        if (this.textSelectionHelper.getStartChildPosition() == 0) goto L8;
    L14:
        return false;
    L8:
        if (this.textSelectionHelper.getStartOffset() != 0) goto L14;
        if (this.textSelectionHelper.getEndChildPosition() != r42[0]) goto L14;
        if (this.textSelectionHelper.getEndOffset() != r42[1]) goto L14;
        return true;
    }

    private static TL_iv.PageBlock itemBlockFor(BlockRow r2, MediaUploadState r3) {
        boolean r0 = isGallery(r2.block);
        TL_iv.PageBlock r1 = r2.block;
        if (r0 == false) goto L16;
        ArrayList<TL_iv.PageBlock> r02 = galleryItems(r1);
        ArrayList<MediaUploadState> r22 = r2.medias;
        if (r22 == null) goto L7;
        int r23 = r22.indexOf(r3);
    L8:
        if (r23 < 0) goto L14;
        if (r02 != null) goto L11;
        return null;
    L11:
        if (r23 < r02.size()) goto L13;
        return null;
    L13:
        return r02.get(r23);
    L14:
        return null;
    L7:
        r23 = -1;
        goto L8
    L16:
        return r1;
    }

    private /* synthetic */ void lambda$addBlock$40(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichTextCell) == false) goto L6;
        RichTextCell r02 = (RichTextCell) r0;
        r02.requestEditFocus();
        r02.getEditText().setSelection(0);
        return;
    }

    private /* synthetic */ void lambda$applyEditRange$83(BlockRow r1, int r2) {
        if (r1 != null) goto L4;
        View r0 = null;
    L6:
        if ((r0 instanceof RichTextCell) == false) goto L9;
        RichTextCell r02 = (RichTextCell) r0;
        r02.requestEditFocus();
        int r12 = r02.getEditText().length();
        r02.getEditText().setSelection(Math.min(r2, r12));
        return;
    L9:
        return;
    L4:
        r0 = findViewByItemObject(r1);
        goto L6
    }

    private /* synthetic */ void lambda$applyInitialSelection$4(int r1, int r2) {
        applyInitialSelectionInternal(r1, r2);
    }

    private /* synthetic */ void lambda$applyQuote$65(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$attachDocument$42(String r1) {
        attachDocument(r1);
    }

    private /* synthetic */ void lambda$attachDocument$43(Uri r3) {
        String r0 = AndroidUtilities.getPath(r3);     // Catch: Exception -> L4
    L7:
        if (TextUtils.isEmpty(r0) == false) goto L9;
    L10:
        r0 = copyDocumentUriToCache(r3);
    L12:
        if (TextUtils.isEmpty(r0) == false) goto L14;
        return;
    L14:
        if (new File(r0).exists() == false) goto L20;
        AndroidUtilities.runOnUIThread(new RichEditorListView$$ExternalSyntheticLambda60(this, r0));
        return;
    L20:
        return;
    L9:
        if (new File(r0).exists() == true) goto L12;
    L4:
        e = move-exception;
        FileLog.e(e);
        r0 = null;
        goto L7
    }

    private /* synthetic */ void lambda$attachExternalMedia$45(int r14, String r15, boolean r16, int r17, int r18, int r19, int r20, int r21, BlockRow r22) {
        if (r16 == false) goto L5;
        int r7 = r17;
    L6:
        MediaController.PhotoEntry r1 = new MediaController.PhotoEntry(0, r14, 0, r15, r7, r16, r18, r19, 0);
        r1.setOrientation(r20, r21);
        if (r22 != null) goto L9;
    L12:
        attachMedia(r1);
        return;
    L9:
        if (isMedia(r22.block) == false) goto L12;
        addMediaToRow(r22, r1);
        return;
    L5:
        r7 = 0;
        goto L6
    }

    private /* synthetic */ void lambda$attachExternalMedia$46(Uri r15, boolean r16, String r17, int r18, BlockRow r19) {
        MediaMetadataRetriever r1 = null;
        String r0 = AndroidUtilities.getPath(r15);     // Catch: Exception -> L5
    L7:
        if (r0 != null) goto L9;
    L12:
        r0 = copyUriToCache(r15, r16, r17, r18);
    L11:
        String r5 = r0;
        if (r5 != null) goto L15;
        return;
    L15:
        if (new File(r5).exists() == false) goto L101;
        int r152 = 0;
        if (r16 == true) goto L80;
        int r12 = 0;
        int r4 = 0;
        int r7 = 0;
    L55:
        if (r16 == false) goto L92;
        int r10 = 0;
        int r11 = 0;
        int r9 = r12;
    L70:
        AndroidUtilities.runOnUIThread(new RichEditorListView$$ExternalSyntheticLambda59(this, r18, r5, r16, r7, r4, r9, r10, r11, r19));
        return;
    L92:
        BitmapFactory.Options r02 = new BitmapFactory.Options();     // Catch: Exception -> L58
        r02.inJustDecodeBounds = true;     // Catch: Exception -> L58
        BitmapFactory.decodeFile(r5, r02);     // Catch: Exception -> L58
        r4 = r02.outWidth;     // Catch: Exception -> L58
        r12 = r02.outHeight;     // Catch: Exception -> L58
    L94:
        Pair<Integer, Integer> r03 = AndroidUtilities.getImageOrientation(r5);     // Catch: Exception -> L66
        int r2 = ((Integer) r03.first).intValue();     // Catch: Exception -> L66
        r152 = ((Integer) r03.second).intValue();     // Catch: Exception -> L64
    L62:
        r11 = r152;
        r9 = r12;
        r10 = r2;
    L64:
        e = e;
    L68:
        FileLog.e(e);
    L66:
        e = e;
        r2 = 0;
    L58:
        e = move-exception;
        FileLog.e(e);
        goto L94
    L80:
        MediaMetadataRetriever r22 = new MediaMetadataRetriever();     // Catch: Throwable -> L43 Exception -> L45
        r22.setDataSource(r5);     // Catch: Throwable -> L24 Exception -> L26
        String r04 = r22.extractMetadata(18);     // Catch: Throwable -> L24 Exception -> L26
        String r13 = r22.extractMetadata(19);     // Catch: Throwable -> L24 Exception -> L26
        String r3 = r22.extractMetadata(9);     // Catch: Throwable -> L24 Exception -> L26
        if (r04 == null) goto L28;
        r4 = Integer.parseInt(r04);     // Catch: Throwable -> L24 Exception -> L26
    L29:
        if (r13 != null) goto L88;
        r12 = 0;
    L35:
        if (r3 != null) goto L77;
        int r05 = 0;
    L85:
        r22.release();     // Catch: Exception -> L72
    L42:
        r7 = r05;
        goto L55
    L77:
        r05 = (int) Math.ceil(Long.parseLong(r3) / 1000.0d);
        goto L85
    L38:
        e = e;
        int r23 = r12;
        r1 = r22;
    L47:
        FileLog.e(e);     // Catch: Throwable -> L43
        if (r1 != null) goto L83;
    L50:
        r7 = 0;
        r12 = r23;
        goto L55
    L83:
        r1.release();     // Catch: Exception -> L73
        goto L50
    L88:
        r12 = Integer.parseInt(r13);     // Catch: Throwable -> L24 Exception -> L32
    L32:
        e = e;
        r1 = r22;
        r23 = 0;
        goto L47
    L28:
        r4 = 0;
    L24:
        th = move-exception;
        Throwable r14 = th;
        r1 = r22;
    L51:
        if (r1 == null) goto L98;
        r1.release();     // Catch: Exception -> L74
        throw r14;
    L99:
        throw r14;
    L98:
        throw r14;
    L26:
        e = e;
        r4 = 0;
        r1 = r22;
        r23 = 0;
    L45:
        e = e;
        r23 = 0;
        r4 = 0;
    L43:
        th = move-exception;
        r14 = th;
        goto L51
    L101:
        return;
    L9:
        if (new File(r0).exists() == true) goto L11;
    L5:
        e = move-exception;
        FileLog.e(e);
        r0 = null;
        goto L7
    }

    private /* synthetic */ void lambda$deleteAcrossDetails$87(BlockRow r2, int r3) {
        View r1 = findViewByItemObject(r2);
        if ((r1 instanceof RichTextCell) == false) goto L6;
        RichTextCell r12 = (RichTextCell) r1;
        r12.requestEditFocus();
        int r22 = r12.getEditText().length();
        r12.getEditText().setSelection(Math.max(0, Math.min(r3, r22)));
        return;
    }

    private /* synthetic */ void lambda$deleteDetails$20(BlockRow r1) {
        focusRowAtEnd(r1);
    }

    private /* synthetic */ void lambda$focusForDraftInternal$15(BlockRow r1) {
        focusRowAtEnd(r1);
    }

    private /* synthetic */ void lambda$handleSelectionTouch$13() {
        if (this.pressTarget != null) goto L6;
        return;
    L6:
        if (this.textSelectionHelper.isInSelectionMode() == false) goto L9;
        return;
    L9:
        if (tryStartTextSelection(this.pressTarget, this.pressX, this.pressY) == false) goto L12;
        this.longPressConsumed = true;
        return;
    L12:
        int r0 = (int) ((this.pressX - this.pressTarget.getLeft()) - getLeft());
        int r2 = (int) ((this.pressY - this.pressTarget.getTop()) - getTop());
        View r3 = this.pressTarget;
        if ((r3 instanceof RichButtonRowCell) == true) goto L15;
    L18:
        View r32 = this.pressTarget;
        if ((r32 instanceof RichTableCell) == false) goto L33;
        RichTableCell r33 = (RichTableCell) r32;
        if (handleTableHandleTap(r33, r0, r2) == true) goto L40;
        TL_iv.pageTableCell r02 = r33.findCellAt(r0, r2);
        if (r02 == null) goto L30;
        startCellSelectionDrag(r33, r02);
        r33.performHapticFeedback(0);     // Catch: Exception -> L38
    L29:
        this.longPressConsumed = true;
        return;
    L30:
        startBlockDrag(this.pressTarget);
        return;
    L40:
        r33.performHapticFeedback(0);     // Catch: Exception -> L39
    L23:
        this.longPressConsumed = true;
        return;
    L33:
        if (isPressOnEmptyEditText(r32, r0, r2) == false) goto L36;
        this.longPressConsumed = true;
        return;
    L36:
        startBlockDrag(this.pressTarget);
        return;
    L15:
        if (((RichButtonRowCell) r3).isPressOnButton(r0, r2) == false) goto L18;
        this.longPressConsumed = true;
    }

    private /* synthetic */ void lambda$insertDetails$41(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichDetailsCell) == false) goto L6;
        ((RichDetailsCell) r0).requestEditFocus();
        return;
    }

    private /* synthetic */ void lambda$insertInlineMath$12(int r3, float r4, RichEditText r5, int r6, int r7, Runnable r8, String r9) {
        if (TextUtils.isEmpty(r9) == true) goto L19;
        MathSpan r32 = MathSpan.create(r9, r3, r4);
        if (r32 != null) goto L8;
        return;
    L8:
        RichEditorHistory r42 = this.history;
        if (r42 == null) goto L11;
        r42.flush();
    L11:
        TextSelectionHelper.ArticleTextSelectionHelper r43 = this.textSelectionHelper;
        if (r43 == null) goto L14;
        r43.clear();
    L14:
        r5.setLocked(false);
        SpannableString r92 = new SpannableString(" ");
        r92.setSpan(r32, 0, 1, 33);
        Editable r33 = r5.getText();
        int r44 = Math.max(0, Math.min(r6, r33.length()));
        r33.replace(r44, Math.max(r44, Math.min(r7, r33.length())), r92);
        r8.run();
        RichEditorHistory r34 = this.history;
        if (r34 == null) goto L17;
        r34.record();
    L17:
        this.delegate.onContentChanged();
        int r35 = Math.min(r44 + 1, r5.length());
        r5.requestEditFocus();
        post(new RichEditorListView$$ExternalSyntheticLambda74(r5, r35));
        return;
    }

    private /* synthetic */ void lambda$insertParagraphAfterQuote$18(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$insertPreparedRow$44(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichTextCell) == false) goto L6;
        RichTextCell r02 = (RichTextCell) r0;
        r02.requestEditFocus();
        r02.getEditText().setSelection(0);
        return;
    }

    private /* synthetic */ void lambda$new$2(View r1, View r2) {
        doNotDetachViews(isInList(r2));
        if ((r2 instanceof RichEditText) == false) goto L6;
        this.focusedEditText = (RichEditText) r2;
        return;
    }

    private /* synthetic */ void lambda$new$22(RichTableCell r2) {
        if (r2 == this.activeCellSelectionTable) goto L6;
        return;
    L6:
        if (r2.hasCellSelection() == true) goto L10;
        exitCellSelectionMode();
        return;
    L10:
        if (this.cellSelectionDragActive == true) goto L13;
        showTableCellMenu(r2);
        return;
    }

    private /* synthetic */ void lambda$onCaptionEnter$48(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichTextCell) == false) goto L6;
        RichTextCell r02 = (RichTextCell) r0;
        r02.requestEditFocus();
        r02.getEditText().setSelection(0);
        return;
    }

    private /* synthetic */ void lambda$onCellBackspaceAtStart$54(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$onCellBackspaceAtStart$55(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$onCellBackspaceAtStart$56(BlockRow r1) {
        focusRowAtEnd(r1);
    }

    private /* synthetic */ void lambda$onCellBackspaceAtStart$57(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$onCellBackspaceAtStart$58(BlockRow r1, int r2) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichTextCell) == false) goto L6;
        RichTextCell r02 = (RichTextCell) r0;
        r02.requestEditFocus();
        r02.getEditText().setSelection(Math.max(0, Math.min(r2, r02.getEditText().length())));
        return;
    }

    private /* synthetic */ void lambda$onCellBackspaceAtStart$59(RecyclerView.ItemAnimator r1) {
        setItemAnimator(r1);
    }

    private /* synthetic */ void lambda$onCellBackspaceAtStart$60(BlockRow r2, int r3) {
        View r1 = findViewByItemObject(r2);
        if ((r1 instanceof RichTextCell) == false) goto L6;
        RichTextCell r12 = (RichTextCell) r1;
        r12.requestEditFocus();
        int r22 = r12.getEditText().length();
        r12.getEditText().setSelection(Math.max(0, Math.min(r3, r22)));
        return;
    }

    private /* synthetic */ void lambda$onCellEnter$49(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$onCellEnter$50(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$onCellEnter$51(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$onCellEnter$52(RecyclerView.ItemAnimator r1) {
        setItemAnimator(r1);
    }

    private /* synthetic */ void lambda$onCellEnter$53(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichTextCell) == false) goto L6;
        RichTextCell r02 = (RichTextCell) r0;
        r02.requestEditFocus();
        r02.getEditText().setSelection(0);
        return;
    }

    private /* synthetic */ void lambda$onDetailsTitleEnter$21(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$onLanguageClick$61(BlockRow r2) {
        updateLanguageOf(r2, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    private /* synthetic */ void lambda$onLanguageClick$62(BlockRow r1, String r2) {
        updateLanguageOf(r1, r2);
    }

    private /* synthetic */ void lambda$onLayout$3(int r2) {
        scrollBy(0, r2);
    }

    private /* synthetic */ void lambda$onMathClicked$6(int r1, int r2) {
        persistTableCell(r1, r2);
    }

    private /* synthetic */ void lambda$onMathClicked$7(int r1) {
        persistDetailsTitle(r1);
    }

    private /* synthetic */ void lambda$onMathClicked$8(int r1) {
        persistCaption(r1);
    }

    private /* synthetic */ void lambda$onMathClicked$9(int r1) {
        persistQuoteAuthor(r1);
    }

    private /* synthetic */ void lambda$onTapAboveContent$14(BlockRow r1) {
        focusRowAtStart(r1);
    }

    private /* synthetic */ void lambda$onTapBelowContent$16(BlockRow r1) {
        focusRowAtEnd(r1);
    }

    private /* synthetic */ void lambda$openLatexEditorAndAdd$38(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
        return;
    L5:
        TL_iv.pageBlockMath r0 = new TL_iv.pageBlockMath();
        r0.source = r2;
        addBlock(r0);
    }

    private /* synthetic */ void lambda$openMathEditor$19(TL_iv.pageBlockMath r2, String r3) {
        if (TextUtils.equals(r3, r2.source) == false) goto L5;
        return;
    L5:
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L8;
        r0.flush();
    L8:
        r2.source = r3;
        this.adapter.update(false);
        RichEditorHistory r22 = this.history;
        if (r22 == null) goto L11;
        r22.record();
    L11:
        this.delegate.onContentChanged();
    }

    private /* synthetic */ void lambda$prepareEditText$17(RichEditText r11, RichInlineButtonSpan r12, boolean r13) {
        Editable r0 = r11.getText();
        int r1 = -1;
        if (r0 != null) goto L5;
        int r6 = -1;
    L6:
        if (r0 != null) goto L8;
    L7:
        int r7 = r1;
        if (r6 < 0) goto L17;
        if (r7 <= r6) goto L18;
        if (r13 == true) goto L15;
        cycleInlineButtonStyle(r11, r12, r6, r7);
        return;
    L15:
        hideTextSelectionUi(false);
        this.delegate.onInlineButtonEditRequested(new InlineButtonEdit(this, r11, r6, r7, r12, null), r11);
        return;
    L18:
        return;
    L17:
        return;
    L8:
        r1 = r0.getSpanEnd(r12);
        goto L7
    L5:
        r6 = r0.getSpanStart(r12);
        goto L6
    }

    private /* synthetic */ void lambda$refreshSelectionHighlight$5() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isInSelectionMode() == false) goto L26;
        int r02 = 0;
    L9:
        if (r02 >= getChildCount()) goto L20;
        View r1 = getChildAt(r02);
        if ((r1 instanceof RichTextCell) == false) goto L13;
    L18:
        r1.invalidate();
    L19:
        r02 = r02 + 1;
        goto L9
    L13:
        if ((r1 instanceof RichTableCell) == true) goto L18;
        if ((r1 instanceof RichCaptionHost) == true) goto L18;
        if ((r1 instanceof RichDetailsCell) == false) goto L19;
    L20:
        this.textSelectionHelper.invalidate();
        return;
    }

    private /* synthetic */ void lambda$removeSelectedBlocks$84(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$restoreCaret$67(BlockRow r2, int r3) {
        View r1 = findViewByItemObject(r2);
        if ((r1 instanceof RichTextCell) == false) goto L6;
        RichTextCell r12 = (RichTextCell) r1;
        r12.requestEditFocus();
        int r22 = r12.getEditText().length();
        r12.getEditText().setSelection(Math.max(0, Math.min(r3, r22)));
        return;
    }

    private /* synthetic */ void lambda$restoreFromHistory$39(RichEditorHistory.FocusState r1) {
        focusFromHistory(r1);
    }

    private /* synthetic */ void lambda$showSelectedUrlDialog$10() {
        hideTextSelectionUi(true);
    }

    private /* synthetic */ void lambda$showTableCellMenu$25(RichTableCell r1, boolean r2) {
        r1.applyHeaderToggle(!r2);
        exitCellSelectionMode();
    }

    private /* synthetic */ void lambda$showTableCellMenu$26(RichTableCell r1) {
        r1.applyMergeFromSelection();
        exitCellSelectionMode();
    }

    private /* synthetic */ void lambda$showTableCellMenu$27(RichTableCell r1) {
        r1.applyUnmergeFromSelection();
        exitCellSelectionMode();
    }

    private /* synthetic */ void lambda$showTableCellMenu$28(RichTableCell r2) {
        r2.applyInsertColumnFromSelection(true);
        exitCellSelectionMode();
    }

    private /* synthetic */ void lambda$showTableCellMenu$29(RichTableCell r2) {
        r2.applyInsertColumnFromSelection(false);
        exitCellSelectionMode();
    }

    private /* synthetic */ void lambda$showTableCellMenu$30(RichTableCell r2) {
        r2.applyInsertRowFromSelection(true);
        exitCellSelectionMode();
    }

    private /* synthetic */ void lambda$showTableCellMenu$31(RichTableCell r2) {
        r2.applyInsertRowFromSelection(false);
        exitCellSelectionMode();
    }

    private /* synthetic */ void lambda$showTableCellMenu$32(RichTableCell r1) {
        r1.applyDeleteColumnsFromSelection();
        exitCellSelectionMode();
    }

    private /* synthetic */ void lambda$showTableCellMenu$33(RichTableCell r1) {
        r1.applyDeleteRowsFromSelection();
        exitCellSelectionMode();
    }

    private /* synthetic */ void lambda$showTableCellMenu$34(RichTableCell r2) {
        BlockRow r22 = r2.getRow();
        exitCellSelectionMode();
        if (r22 == null) goto L14;
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L7;
        r0.flush();
    L7:
        removeRow(r22);
        RichEditorHistory r23 = this.history;
        if (r23 == null) goto L10;
        r23.record();
    L10:
        Delegate r1 = this.delegate;
        if (r1 == null) goto L15;
        r1.onContentChanged();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$showTableCellMenu$37(ItemOptions r2, RichTableCell r3) {
        if (this.tableCellMenu != r2) goto L16;
        this.tableCellMenu = null;
        if (this.clearDraggedMultiCellSelectionOnMenuDismiss == true) goto L8;
        return;
    L8:
        if (this.activeCellSelectionTable == r3) goto L10;
        return;
    L10:
        if (r3.hasCellSelection() == false) goto L15;
        exitCellSelectionMode();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$spliceBlocksInto$82(BlockRow r1, int r2) {
        if (r1 != null) goto L4;
        View r0 = null;
    L6:
        if ((r0 instanceof RichTextCell) == false) goto L9;
        RichTextCell r02 = (RichTextCell) r0;
        r02.requestEditFocus();
        int r12 = r02.getEditText().length();
        r02.getEditText().setSelection(Math.min(r2, r12));
        return;
    L9:
        return;
    L4:
        r0 = findViewByItemObject(r1);
        goto L6
    }

    private /* synthetic */ void lambda$toggleQuoteOnSelection$63(BlockRow r1, BlockRow r2) {
        reselectRowRange(r1, r2);
    }

    private /* synthetic */ void lambda$toggleQuoteOnSelection$64(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$transformRow$66(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichTextCell) == false) goto L7;
        RichTextCell r02 = (RichTextCell) r0;
        r02.requestEditFocus();
        int r12 = r02.getEditText().length();
        r02.getEditText().setSelection(r12);
        return;
    L7:
        if ((r0 instanceof RichTableCell) == false) goto L14;
        RichTableCell r03 = (RichTableCell) r0;
        if (r03.getGrid().getChildCount() <= 0) goto L15;
        View r04 = r03.getGrid().getChildAt(0);
        if ((r04 instanceof RichTableCellHost) == false) goto L16;
        ((RichTableCellHost) r04).editText.requestEditFocus();
        return;
    L16:
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$tryEscalateSelectionFromCaret$79(BlockRow r2) {
        KeyEvent.Callback r22 = findViewByItemObject(r2);
        if ((r22 instanceof TextSelectionHelper.ArticleSelectableView) == false) goto L6;
        this.textSelectionHelper.extendSelectionTo((TextSelectionHelper.ArticleSelectableView) r22, 0);
        return;
    }

    private /* synthetic */ void lambda$tryEscalateSelectionFromCaret$80(BlockRow r2) {
        KeyEvent.Callback r22 = findViewByItemObject(r2);
        if ((r22 instanceof TextSelectionHelper.ArticleSelectableView) == true) goto L5;
        return;
    L5:
        if ((r22 instanceof RichTextCell) == false) goto L7;
        int r0 = ((RichTextCell) r22).getEditText().length();
    L8:
        this.textSelectionHelper.extendSelectionTo((TextSelectionHelper.ArticleSelectableView) r22, r0);
        return;
    L7:
        r0 = 0;
        goto L8
    }

    private /* synthetic */ void lambda$tryExtendSelectionAcrossCells$81(int r2, int r3, int r4) {
        KeyEvent.Callback r22 = selectableAt(r2);
        if ((r22 instanceof TextSelectionHelper.ArticleSelectableView) == false) goto L6;
        this.textSelectionHelper.extendSelectionTo((TextSelectionHelper.ArticleSelectableView) r22, r3, r4);
        return;
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$68(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$69(BlockRow r2) {
        focusNavRow(r2, false);
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$70(BlockRow r2) {
        focusNavRow(r2, true);
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$71(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$72(BlockRow r2) {
        focusItemRow(r2, false);
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$73(BlockRow r2) {
        focusItemRow(r2, true);
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$74(BlockRow r2) {
        focusItemRow(r2, true);
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$75(BlockRow r1) {
        focusRow(r1);
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$76(BlockRow r2) {
        focusNavRow(r2, false);
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$77(BlockRow r2) {
        focusItemRow(r2, true);
    }

    private /* synthetic */ void lambda$tryPlainArrowAcrossCells$78(BlockRow r2) {
        focusNavRow(r2, true);
    }

    private int[] mapInitialOffset(int r5) {
        int r0 = 0;
        int r1 = 0;
    L3:
        int r2 = this.initialSelectionRows.size();
        ArrayList<BlockRow> r3 = this.initialSelectionRows;
        if (r0 >= r2) goto L10;
        int r22 = RichTextCell.readPlainText(r3.get(r0).block).length();
        if (r5 <= (r1 + r22)) goto L8;
        r1 = r1 + (r22 + 1);
        r0 = r0 + 1;
        goto L3
    L8:
        return new int[]{r0, r5 - r1};
    L10:
        int r52 = r3.size() - 1;
        return new int[]{r52, RichTextCell.readPlainText(this.initialSelectionRows.get(r52).block).length()};
    }

    private void markQuoteEdges() {
        int r1 = 0;
    L4:
        if (r1 >= this.itemRows.size()) goto L25;
        BlockRow r2 = this.itemRows.get(r1);
        r2.quoteFirst = false;
        r2.quoteLast = false;
        r2.quoteTopEdge = 0;
        r2.quoteBottomEdge = 0;
        int r3 = r2.quoteIds.size();
        if (r3 == 0) goto L24;
        BlockRow r4 = null;
        if (r1 <= 0) goto L11;
        BlockRow r5 = this.itemRows.get(r1 - 1);
    L12:
        int r6 = r1 + 1;
        if (r6 >= this.itemRows.size()) goto L15;
        r4 = this.itemRows.get(r6);
    L15:
        r2.quoteTopEdge = r3 - quoteCommonPrefixLen(r2, r5);
        int r32 = r3 - quoteCommonPrefixLen(r2, r4);
        r2.quoteBottomEdge = r32;
        boolean r52 = true;
        if (r2.quoteTopEdge <= 0) goto L18;
        boolean r42 = true;
    L19:
        r2.quoteFirst = r42;
        if (r32 > 0) goto L23;
        r52 = false;
    L23:
        r2.quoteLast = r52;
        goto L24
    L18:
        r42 = false;
        goto L19
    L11:
        r5 = null;
    L24:
        r1 = r1 + 1;
        goto L4
    }

    private int matchingDetailsEnd(int r4) {
        int r0 = 1;
        int r42 = r4 + 1;
    L3:
        int r1 = this.rows.size();
        ArrayList<BlockRow> r2 = this.rows;
        if (r42 >= r1) goto L15;
        BlockRow r12 = r2.get(r42);
        if (isDetailsHeader(r12) == false) goto L9;
        r0 = r0 + 1;
    L13:
        r42 = r42 + 1;
        goto L3
    L9:
        if (r12.detailsEnd == false) goto L13;
        r0 = r0 - 1;
        if (r0 != 0) goto L13;
        return r42;
    L15:
        return r2.size();
    }

    private static long mediaIdOf(TL_iv.PageBlock r2) {
        if ((r2 instanceof TL_iv.pageBlockPhoto) == false) goto L7;
        return ((TL_iv.pageBlockPhoto) r2).photo_id;
    L7:
        if ((r2 instanceof TL_iv.pageBlockVideo) == true) goto L9;
        return 0;
    L9:
        return ((TL_iv.pageBlockVideo) r2).video_id;
    }

    public static List<MediaUploadState> mediasOf(BlockRow r1) {
        if (r1 != null) goto L6;
        return Collections.EMPTY_LIST;
    L6:
        if (isGallery(r1.block) == false) goto L12;
        ArrayList<MediaUploadState> r12 = r1.medias;
        if (r12 == null) goto L11;
        return r12;
    L11:
        return Collections.EMPTY_LIST;
    L12:
        MediaUploadState r13 = r1.media;
        if (r13 == null) goto L17;
        return Collections.singletonList(r13);
    L17:
        return Collections.EMPTY_LIST;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private RichEditText navEditTextOf(View r1) {
        if ((r1 instanceof RichTextCell) == false) goto L7;
        return ((RichTextCell) r1).getEditText();
    L7:
        if ((r1 instanceof RichCaptionHost) == false) goto L11;
        return ((RichCaptionHost) r1).getCaptionEditText();
    L11:
        if ((r1 instanceof RichDetailsCell) == true) goto L13;
        return null;
    L13:
        return ((RichDetailsCell) r1).getEditText();
    }

    public static TL_iv.pageBlockBlockquote newBlockquote() {
        TL_iv.pageBlockBlockquote r0 = new TL_iv.pageBlockBlockquote();
        r0.caption = new TL_iv.textEmpty();
        return r0;
    }

    private static BlockRow newDetailsEndRow() {
        BlockRow r0 = new BlockRow(new TL_iv.pageBlockParagraph());
        r0.detailsEnd = true;
        return r0;
    }

    public static TL_iv.pageBlockPullquote newPullquote() {
        TL_iv.pageBlockPullquote r0 = new TL_iv.pageBlockPullquote();
        r0.caption = new TL_iv.textEmpty();
        return r0;
    }

    private static TL_iv.PageBlock newSameTypeBlock(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockHeading1) == false) goto L7;
        return new TL_iv.pageBlockHeading1();
    L7:
        if ((r1 instanceof TL_iv.pageBlockHeading2) == false) goto L11;
        return new TL_iv.pageBlockHeading2();
    L11:
        if ((r1 instanceof TL_iv.pageBlockHeading3) == false) goto L15;
        return new TL_iv.pageBlockHeading3();
    L15:
        if ((r1 instanceof TL_iv.pageBlockHeading4) == false) goto L19;
        return new TL_iv.pageBlockHeading4();
    L19:
        if ((r1 instanceof TL_iv.pageBlockHeading5) == false) goto L23;
        return new TL_iv.pageBlockHeading5();
    L23:
        if ((r1 instanceof TL_iv.pageBlockHeading6) == false) goto L27;
        return new TL_iv.pageBlockHeading6();
    L27:
        if ((r1 instanceof TL_iv.pageBlockBlockquote) == false) goto L31;
        return new TL_iv.pageBlockBlockquote();
    L31:
        if ((r1 instanceof TL_iv.pageBlockPullquote) == false) goto L35;
        return new TL_iv.pageBlockPullquote();
    L35:
        if ((r1 instanceof TL_iv.pageBlockPreformatted) == false) goto L39;
        TL_iv.pageBlockPreformatted r0 = new TL_iv.pageBlockPreformatted();
        r0.language = ((TL_iv.pageBlockPreformatted) r1).language;
        return r0;
    L39:
        if ((r1 instanceof TL_iv.pageBlockFooter) == false) goto L43;
        return new TL_iv.pageBlockFooter();
    L43:
        return new TL_iv.pageBlockParagraph();
    }

    private static MediaUploadState newUploadingMedia(MediaController.PhotoEntry r4, String r5) {
        MediaUploadState r0 = new MediaUploadState();
        r0.isVideo = r4.isVideo;
        r0.localPath = r5;
        r0.width = r4.width;
        r0.height = r4.height;
        r0.duration = r4.duration;
        String r1 = r4.imagePath;
        int r3 = 0;
        if (r1 == null) goto L8;
        if (r5 == null) goto L8;
        if (r5.equals(r1) == false) goto L8;
        boolean r52 = true;
    L9:
        if (r52 == false) goto L11;
        int r12 = 0;
    L12:
        r0.orientation = r12;
        if (r52 == true) goto L16;
        r3 = r4.invert;
    L16:
        r0.invert = r3;
        r0.state = 1;
        r0.progress = 0.0f;
        return r0;
    L11:
        r12 = r4.orientation;
    L8:
        r52 = false;
        goto L9
    }

    private BlockRow nextNavItemRow(int r6, int r7) {
    L2:
        if (r6 < 0) goto L18;
        if (r6 >= this.itemRows.size()) goto L28;
        BlockRow r0 = this.itemRows.get(r6);
        if (r0.detailsEnd == true) goto L7;
        if (r0.authorQuoteId != 0) goto L17;
        TL_iv.PageBlock r1 = r0.block;
        if (isNonText(r1) == false) goto L17;
        if (hasCaption(r1) == true) goto L17;
        if ((r1 instanceof TL_iv.pageBlockTable) == false) goto L7;
    L17:
        return r0;
    L7:
        r6 = r6 + r7;
        goto L2
    L28:
        return null;
    L18:
        return null;
    }

    private void normalizeLevels() {
        int r1 = 0;
    L4:
        if (r1 >= this.rows.size()) goto L32;
        BlockRow r2 = this.rows.get(r1);
        if (r2.detailsEnd == true) goto L31;
        if (isDetailsHeader(r2) == true) goto L31;
        if (r1 <= 0) goto L12;
        BlockRow r3 = this.rows.get(r1 - 1);
    L13:
        if (r3 == null) goto L15;
        int r4 = Math.max(0, r3.level);
    L17:
        if (isNonText(r2.block) == false) goto L25;
        r2.level = r4;
        if (r4 <= 0) goto L29;
        if (r3.num <= 0) goto L23;
        int r32 = 1;
    L24:
        r2.num = r32;
        r2.checkbox = false;
        r2.checked = false;
        goto L29
    L23:
        r32 = 0;
    L29:
        if (r2.level > 0) goto L31;
        r2.level = 0;
        r2.num = 0;
        r2.checkbox = false;
        r2.checked = false;
        goto L31
    L25:
        int r42 = r4 + 1;
        if (r2.level <= r42) goto L29;
        r2.level = r42;
        goto L29
    L15:
        r4 = 0;
        goto L17
    L12:
        r3 = null;
    L31:
        r1 = r1 + 1;
        goto L4
    }

    private boolean normalizeNestedQuotes() {
        int r0 = 0;
        boolean r1 = false;
    L4:
        if (r0 >= this.rows.size()) goto L17;
        BlockRow r2 = this.rows.get(r0);
        if (r2.quoteIds.isEmpty() == true) goto L16;
        if (RichTextCell.isQuoteBlock(r2.block) == false) goto L16;
        long r3 = RichContainer.newId();
        TL_iv.RichText r12 = RichTextCell.extractCaption(r2.block);
        if (r12 != null) goto L13;
    L15:
        r2.quoteIds.add(Long.valueOf(r3));
        TL_iv.pageBlockParagraph r13 = new TL_iv.pageBlockParagraph();
        r13.text = r2.block.text;
        r2.block = r13;
        r1 = true;
        goto L16
    L13:
        if ((r12 instanceof TL_iv.textEmpty) == true) goto L15;
        this.quoteAuthors.put(Long.valueOf(r3), r12);
    L16:
        r0 = r0 + 1;
        goto L4
    L17:
        return r1;
    }

    private boolean normalizeQuoteMembership(BlockRow r6) {
        if (r6 != null) goto L5;
        return false;
    L5:
        int r1 = this.rows.indexOf(r6);
        if (r1 >= 0) goto L8;
        return false;
    L8:
        if (r1 <= 0) goto L10;
        ArrayList<Long> r2 = this.rows.get(r1 - 1).quoteIds;
    L11:
        int r12 = r1 + 1;
        if (r12 >= this.rows.size()) goto L14;
        ArrayList<Long> r5 = this.rows.get(r12).quoteIds;
    L16:
        if (r2.size() >= r5.size()) goto L20;
        r2 = r5;
    L20:
        if (r6.quoteIds.equals(r2) == false) goto L22;
        return false;
    L22:
        r6.quoteIds.clear();
        r6.quoteIds.addAll(r2);
        return true;
    L14:
        r5 = EMPTY_QUOTE_IDS;
        goto L16
    L10:
        r2 = EMPTY_QUOTE_IDS;
        goto L11
    }

    private void onCaptionEnter(BlockRow r4) {
        int r0 = this.rows.indexOf(r4);
        if (r0 >= 0) goto L5;
        return;
    L5:
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L8;
        r1.flush();
    L8:
        BlockRow r12 = new BlockRow(new TL_iv.pageBlockParagraph());
        r12.quoteIds.addAll(r4.quoteIds);
        this.rows.add(r0 + 1, r12);
        renumberAllRuns();
        this.adapter.update(false);
        RichEditorHistory r42 = this.history;
        if (r42 == null) goto L11;
        r42.record();
    L11:
        post(new RichEditorListView$$ExternalSyntheticLambda76(this, r12));
    }

    private boolean onCellBackspaceAtStart(BlockRow r12, boolean r13) {
        int r0 = this.rows.indexOf(r12);
        if (r0 >= 0) goto L6;
        return false;
    L6:
        if (demotesToParagraph(r12.block) == false) goto L10;
        turnInto(r12, new TL_iv.pageBlockParagraph(), 0, 0, false, false);
        return true;
    L10:
        if (r12.level <= 0) goto L26;
        RichEditorHistory r11 = this.history;
        if (r11 == null) goto L14;
        r11.flush();
    L14:
        int r112 = captureCaret(r12);
        cascadeOutdent(r0);
        renumberAllRuns();
        if (hasFocusedEdit() == false) goto L20;
        applyInPlaceUpdateKeepingFocus();
        RichEditorHistory r122 = this.history;
        if (r122 == null) goto L19;
        r122.record();
    L19:
        restoreCaret(r12, r112);
    L24:
        return true;
    L20:
        this.adapter.update(false);
        RichEditorHistory r113 = this.history;
        if (r113 == null) goto L23;
        r113.record();
    L23:
        post(new RichEditorListView$$ExternalSyntheticLambda87(this, r12));
        goto L24
    L26:
        if (r12.quoteIds.isEmpty() == true) goto L40;
        if (r13 == false) goto L40;
        if (isFirstOfInnermostQuote(r0) == false) goto L31;
    L32:
        RichEditorHistory r114 = this.history;
        if (r114 == null) goto L35;
        r114.flush();
    L35:
        ArrayList<Long> r115 = r12.quoteIds;
        r115.remove(r115.size() - 1);
        collapseSingleBlockQuotes();
        renumberAllRuns();
        this.adapter.update(false);
        RichEditorHistory r116 = this.history;
        if (r116 == null) goto L38;
        r116.record();
    L38:
        post(new RichEditorListView$$ExternalSyntheticLambda88(this, r12));
        return true;
    L31:
        if (isLastOfInnermostQuote(r0) == false) goto L32;
    L40:
        if (r0 > 0) goto L42;
        return false;
    L42:
        int r123 = r0 - 1;
        BlockRow r117 = this.rows.get(r123);
        if (isDetailsHeader(r117) == false) goto L45;
    L87:
        if (r13 == true) goto L89;
    L101:
        return true;
    L89:
        if (isDetailsHeader(r117) == false) goto L94;
        int r124 = r0 + 1;
        if (r124 >= this.rows.size()) goto L101;
        if (this.rows.get(r124).detailsEnd == true) goto L101;
    L94:
        RichEditorHistory r125 = this.history;
        if (r125 == null) goto L97;
        r125.flush();
    L97:
        this.rows.remove(r0);
        renumberAllRuns();
        this.adapter.update(false);
        RichEditorHistory r126 = this.history;
        if (r126 == null) goto L100;
        r126.record();
    L100:
        post(new RichEditorListView$$ExternalSyntheticLambda89(this, r117));
        goto L101
    L45:
        if (r117.detailsEnd == true) goto L87;
        boolean r132 = isNonText(r117.block);
        RichEditorHistory r2 = this.history;
        if (r132 == false) goto L56;
        if (r2 == null) goto L51;
        r2.flush();
    L51:
        this.rows.remove(r123);
        renumberAllRuns();
        this.adapter.update(false);
        RichEditorHistory r118 = this.history;
        if (r118 == null) goto L54;
        r118.record();
    L54:
        post(new RichEditorListView$$ExternalSyntheticLambda90(this, r12));
        return true;
    L56:
        if (r2 == null) goto L58;
        r2.flush();
    L58:
        View r127 = findViewByItemObject(r117);
        int r133 = this.itemRows.indexOf(r12);
        if ((r127 instanceof RichTextCell) == false) goto L82;
        if (r133 < 0) goto L82;
        RichTextCell r128 = (RichTextCell) r127;
        RichEditText r22 = r128.getEditText();
        int r6 = r22.length();
        r22.appendSilently(RichTextCell.readStyledText(r12.block));
        RichTextCell.applyStyledTextToBlock(r117.block, r22.getText());
        View r7 = findViewByItemObject(r12);
        if ((r7 instanceof RichTextCell) == false) goto L64;
        ((RichTextCell) r7).getEditText().deleteToEndSilently(0);
    L64:
        r128.requestEditFocus();
        r22.setSelection(Math.max(0, Math.min(r6, r22.length())));
        this.rows.remove(r0);
        boolean r129 = collapseSingleBlockQuotes();
        renumberAllRuns();
        UniversalAdapter r23 = this.adapter;
        if (r129 == false) goto L71;
        r23.update(false);
        RichEditorHistory r1210 = this.history;
        if (r1210 == null) goto L69;
        r1210.record();
    L69:
        post(new RichEditorListView$$ExternalSyntheticLambda91(this, r117, Math.max(0, r6)));
        return true;
    L71:
        r23.updateWithoutNotify();
        refreshVisibleListPaddingAround(r0);
        RecyclerView.ItemAnimator r1211 = getItemAnimator();
        setItemAnimator(null);
        this.adapter.notifyItemRemoved(r133);
        if (r117.num > 0) goto L76;
        if (r12.num > 0) goto L76;
    L78:
        post(new RichEditorListView$$ExternalSyntheticLambda92(this, r1211));
        RichEditorHistory r119 = this.history;
        if (r119 == null) goto L81;
        r119.record();
    L81:
        return true;
    L76:
        if (r133 >= this.itemRows.size()) goto L78;
        this.adapter.notifyItemRangeChanged(r133, this.itemRows.size() - r133);
    L82:
        CharSequence r1212 = RichTextCell.readStyledText(r117.block);
        int r134 = r1212.length();
        SpannableStringBuilder r24 = new SpannableStringBuilder(r1212);
        r24.append(RichTextCell.readStyledText(r12.block));
        RichTextCell.applyStyledTextToBlock(r117.block, r24);
        this.rows.remove(r0);
        collapseSingleBlockQuotes();
        renumberAllRuns();
        this.adapter.update(false);
        RichEditorHistory r1213 = this.history;
        if (r1213 == null) goto L85;
        r1213.record();
    L85:
        post(new RichEditorListView$$ExternalSyntheticLambda93(this, r117, r134));
        return true;
    }

    private void onCellEnter(BlockRow r14) {
        int r0 = this.rows.indexOf(r14);
        if (r0 >= 0) goto L5;
        return;
    L5:
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L8;
        r1.flush();
    L8:
        View r12 = findViewByItemObject(r14);
        boolean r2 = r12 instanceof RichTextCell;
        if (r2 == false) goto L11;
        RichEditText r3 = ((RichTextCell) r12).getEditText();
        CharSequence r4 = r3.getText();
        int r32 = r3.getSelectionEnd();
    L12:
        if (r32 >= 0) goto L14;
    L15:
        r32 = r4.length();
    L17:
        if (r4.length() == 0) goto L19;
    L32:
        CharSequence r5 = r4.subSequence(0, r32);
        CharSequence r42 = r4.subSequence(r32, r4.length());
        if ((r14.block instanceof TL_iv.pageBlockBlockquote) == false) goto L40;
        long r8 = RichContainer.newId();
        TL_iv.RichText r10 = ((TL_iv.pageBlockBlockquote) r14.block).caption;
        if (r10 != null) goto L37;
    L39:
        r14.quoteIds.add(Long.valueOf(r8));
        r14.block = new TL_iv.pageBlockParagraph();
        boolean r82 = true;
    L41:
        RichTextCell.applyStyledTextToBlock(r14.block, r5);
        TL_iv.pageBlockParagraph r52 = new TL_iv.pageBlockParagraph();
        RichTextCell.applyStyledTextToBlock(r52, r42);
        int r43 = r14.num;
        if (r43 <= 0) goto L44;
        r43 = r43 + 1;
    L44:
        BlockRow r9 = new BlockRow(r52, r14.level, r43);
        r9.checkbox = r14.checkbox;
        r9.quoteIds.addAll(r14.quoteIds);
        int r02 = r0 + 1;
        this.rows.add(r02, r9);
        renumberAllRuns();
        if (r82 == false) goto L51;
        this.adapter.update(false);
        RichEditorHistory r142 = this.history;
        if (r142 == null) goto L49;
        r142.record();
    L49:
        post(new RichEditorListView$$ExternalSyntheticLambda84(this, r9));
        return;
    L51:
        if (r2 == false) goto L53;
        ((RichTextCell) r12).getEditText().deleteToEndSilently(r32);
    L53:
        this.adapter.updateWithoutNotify();
        refreshVisibleListPaddingAround(r02);
        int r03 = this.itemRows.indexOf(r9);
        if (r03 >= 0) goto L56;
        this.adapter.notifyDataSetChanged();
    L62:
        RichEditorHistory r143 = this.history;
        if (r143 == null) goto L65;
        r143.record();
    L65:
        post(new RichEditorListView$$ExternalSyntheticLambda86(this, r9));
        return;
    L56:
        RecyclerView.ItemAnimator r13 = getItemAnimator();
        setItemAnimator(null);
        this.adapter.notifyItemInserted(r03);
        if (r14.num <= 0) goto L61;
        int r144 = r03 + 1;
        if (r144 >= this.itemRows.size()) goto L61;
        this.adapter.notifyItemRangeChanged(r144, (this.itemRows.size() - r03) - 1);
    L61:
        post(new RichEditorListView$$ExternalSyntheticLambda85(this, r13));
        goto L62
    L37:
        if ((r10 instanceof TL_iv.textEmpty) == true) goto L39;
        this.quoteAuthors.put(Long.valueOf(r8), r10);
        goto L39
    L40:
        r82 = false;
        goto L41
    L19:
        if (r14.quoteIds.isEmpty() == true) goto L26;
        ArrayList<Long> r04 = r14.quoteIds;
        r04.remove(r04.size() - 1);
        renumberAllRuns();
        this.adapter.update(false);
        RichEditorHistory r05 = this.history;
        if (r05 == null) goto L23;
        r05.record();
    L23:
        post(new RichEditorListView$$ExternalSyntheticLambda82(this, r14));
        return;
    L26:
        if (r14.level <= 0) goto L32;
        cascadeOutdent(r0);
        renumberAllRuns();
        this.adapter.update(false);
        RichEditorHistory r06 = this.history;
        if (r06 == null) goto L30;
        r06.record();
    L30:
        post(new RichEditorListView$$ExternalSyntheticLambda83(this, r14));
        return;
    L14:
        if (r32 <= r4.length()) goto L17;
    L11:
        r4 = RichTextCell.readStyledText(r14.block);
        r32 = r4.length();
        goto L12
    }

    private boolean onCellPaste(BlockRow r10, RichEditText r11) {
        if (r10 == null) goto L47;
        if (r11 == null) goto L47;
        ClipboardManager r0 = (ClipboardManager) getContext().getSystemService("clipboard");
        if (r0 == null) goto L47;
        if (r0.hasPrimaryClip() == false) goto L47;
        ClipData r02 = r0.getPrimaryClip();
        if (r02 == null) goto L47;
        if (r02.getItemCount() == 0) goto L47;
        if (r02.getDescription() == null) goto L47;
        if (r02.getDescription().hasMimeType("text/html") == false) goto L47;
        String r03 = r02.getItemAt(0).getHtmlText();     // Catch: Exception -> L48
        if (TextUtils.isEmpty(r03) == false) goto L24;
        return false;
    L24:
        HashMap r2 = new HashMap();
        List<BlockRow> r8 = resolvePastedMedia(RichHtml.parse(r03, r2));     // Catch: Exception -> L45
        if (r8 != null) goto L28;
    L44:
        return false;
    L28:
        if (r8.isEmpty() == true) goto L44;
        if (r8.size() == 1) goto L33;
    L35:
        int r4 = this.rows.indexOf(r10);
        if (r4 >= 0) goto L38;
        return false;
    L38:
        int r6 = Math.max(0, Math.min(r11.getSelectionStart(), r11.getSelectionEnd()));
        boolean r9 = spliceBlocksInto(r4, r4, r6, Math.max(r6, Math.max(r11.getSelectionStart(), r11.getSelectionEnd())), r8);
        if (r9 == true) goto L41;
    L43:
        return r9;
    L41:
        if (r2.isEmpty() == true) goto L43;
        this.quoteAuthors.putAll(r2);
        goto L43
    L33:
        if (isPlainParagraphRow(r8.get(0)) == false) goto L35;
        return false;
    L45:
        e = move-exception;
        FileLog.e(e);
    L47:
        return false;
    }

    private void onCellSpansChanged() {
        if (this.suppressSpansChanged == false) goto L5;
        return;
    L5:
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L8;
        r0.record();
    L8:
        this.delegate.onSelectionChanged();
        refreshSelectionHighlight();
    }

    private void onChecklistToggle(BlockRow r1, boolean r2) {
        if (r1 == null) goto L9;
        r1.checked = r2;
        RichEditorHistory r12 = this.history;
        if (r12 == null) goto L8;
        r12.flush();
        this.history.record();
        return;
    L8:
        return;
    }

    private void onDateClickedAuthor() {
        int r0 = this.textSelectionHelper.getStartCell();
        RichEditText r1 = quoteAuthorEditText(r0);
        if (r1 == null) goto L19;
        int r2 = r1.length();
        int r3 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        int r22 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        if (r3 < r22) goto L8;
        return;
    L8:
        RichEditorHistory r4 = this.history;
        if (r4 == null) goto L12;
        r4.flush();
    L12:
        if (RichTextStyle.hasLink(r1.getText(), r3, r22) == false) goto L17;
        RichTextStyle.removeLink(r1.getText(), r3, r22);
        r1.invalidateEffects();
        persistQuoteAuthor(r0);
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L16;
        r02.record();
    L16:
        refreshSelectionHighlight();
    L17:
        r1.setSelectionOverride(r3, r22);
        r1.makeSelectedDate();
        return;
    }

    private void onDateClickedCaption() {
        int r0 = this.textSelectionHelper.getStartCell();
        RichEditText r1 = captionEditText(r0);
        if (r1 == null) goto L19;
        int r2 = r1.length();
        int r3 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        int r22 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        if (r3 < r22) goto L8;
        return;
    L8:
        RichEditorHistory r4 = this.history;
        if (r4 == null) goto L12;
        r4.flush();
    L12:
        if (RichTextStyle.hasLink(r1.getText(), r3, r22) == false) goto L17;
        RichTextStyle.removeLink(r1.getText(), r3, r22);
        r1.invalidateEffects();
        persistCaption(r0);
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L16;
        r02.record();
    L16:
        refreshSelectionHighlight();
    L17:
        r1.setSelectionOverride(r3, r22);
        r1.makeSelectedDate();
        return;
    }

    private void onDateClickedDetails() {
        int r0 = this.textSelectionHelper.getStartCell();
        RichEditText r1 = detailsEditText(r0);
        if (r1 == null) goto L19;
        int r2 = r1.length();
        int r3 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        int r22 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        if (r3 < r22) goto L8;
        return;
    L8:
        RichEditorHistory r4 = this.history;
        if (r4 == null) goto L12;
        r4.flush();
    L12:
        if (RichTextStyle.hasLink(r1.getText(), r3, r22) == false) goto L17;
        RichTextStyle.removeLink(r1.getText(), r3, r22);
        r1.invalidateEffects();
        persistDetailsTitle(r0);
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L16;
        r02.record();
    L16:
        refreshSelectionHighlight();
    L17:
        r1.setSelectionOverride(r3, r22);
        r1.makeSelectedDate();
        return;
    }

    private void onDateClickedTable() {
        int r0 = this.textSelectionHelper.getStartCell();
        int r1 = this.textSelectionHelper.getStartChildPosition();
        if (r1 != this.textSelectionHelper.getEndChildPosition()) goto L22;
        RichEditText r2 = tableEditText(r0, r1);
        if (r2 == null) goto L23;
        int r3 = r2.length();
        int r4 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r3));
        int r32 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r3));
        if (r4 < r32) goto L11;
        return;
    L11:
        RichEditorHistory r5 = this.history;
        if (r5 == null) goto L15;
        r5.flush();
    L15:
        if (RichTextStyle.hasLink(r2.getText(), r4, r32) == false) goto L20;
        RichTextStyle.removeLink(r2.getText(), r4, r32);
        r2.invalidateEffects();
        persistTableCell(r0, r1);
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L19;
        r02.record();
    L19:
        refreshSelectionHighlight();
    L20:
        r2.setSelectionOverride(r4, r32);
        r2.makeSelectedDate();
        return;
    L23:
        return;
    }

    private void onDetailsTitleEnter(BlockRow r4) {
        int r0 = this.rows.indexOf(r4);
        if (r0 < 0) goto L19;
        TL_iv.pageBlockDetails r42 = (TL_iv.pageBlockDetails) r4.block;
        if (r42.open == true) goto L8;
        r42.open = true;
        this.adapter.update(true);
    L8:
        int r02 = r0 + 1;
        if (r02 < this.rows.size()) goto L11;
        return;
    L11:
        if (this.rows.get(r02).detailsEnd == false) goto L13;
        return;
    L13:
        if (isDetailsHeader(this.rows.get(r02)) == true) goto L18;
        post(new RichEditorListView$$ExternalSyntheticLambda77(this, this.rows.get(r02)));
        return;
    L18:
        return;
    }

    private void onFormattingClickedAuthor(int r10) {
        int r0 = this.textSelectionHelper.getStartCell();
        RichEditText r1 = quoteAuthorEditText(r0);
        if (r1 == null) goto L29;
        int r2 = r1.length();
        int r3 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        int r22 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        if (r3 < r22) goto L9;
        return;
    L9:
        if ((r1.getCurrentStyle(r3, r22) & r10) != 0) goto L11;
        boolean r5 = true;
    L12:
        if (r5 == false) goto L14;
        int r7 = clearMaskFor(r10);
    L15:
        RichEditorHistory r8 = this.history;
        if (r8 == null) goto L18;
        r8.flush();
    L18:
        this.suppressSpansChanged = true;
        if (r5 == false) goto L23;
        if (r7 == 0) goto L22;
        r1.removeStyle(r7, r3, r22);
    L22:
        r1.addStyle(r10, r3, r22);
    L24:
        r1.invalidateEffects();
        r1.requestLayout();
        persistQuoteAuthor(r0);
        this.suppressSpansChanged = false;
        RichEditorHistory r102 = this.history;
        if (r102 == null) goto L27;
        r102.record();
    L27:
        this.delegate.onSelectionChanged();
        refreshSelectionHighlight();
        return;
    L23:
        r1.removeStyle(r10, r3, r22);
        goto L24
    L14:
        r7 = 0;
        goto L15
    L11:
        r5 = false;
        goto L12
    }

    private void onFormattingClickedCaption(int r10) {
        int r0 = this.textSelectionHelper.getStartCell();
        RichEditText r1 = captionEditText(r0);
        if (r1 == null) goto L29;
        int r2 = r1.length();
        int r3 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        int r22 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        if (r3 < r22) goto L9;
        return;
    L9:
        if ((r1.getCurrentStyle(r3, r22) & r10) != 0) goto L11;
        boolean r5 = true;
    L12:
        if (r5 == false) goto L14;
        int r7 = clearMaskFor(r10);
    L15:
        RichEditorHistory r8 = this.history;
        if (r8 == null) goto L18;
        r8.flush();
    L18:
        this.suppressSpansChanged = true;
        if (r5 == false) goto L23;
        if (r7 == 0) goto L22;
        r1.removeStyle(r7, r3, r22);
    L22:
        r1.addStyle(r10, r3, r22);
    L24:
        r1.invalidateEffects();
        r1.requestLayout();
        persistCaption(r0);
        this.suppressSpansChanged = false;
        RichEditorHistory r102 = this.history;
        if (r102 == null) goto L27;
        r102.record();
    L27:
        this.delegate.onSelectionChanged();
        refreshSelectionHighlight();
        return;
    L23:
        r1.removeStyle(r10, r3, r22);
        goto L24
    L14:
        r7 = 0;
        goto L15
    L11:
        r5 = false;
        goto L12
    }

    private void onFormattingClickedDetails(int r9) {
        int r0 = this.textSelectionHelper.getStartCell();
        RichEditText r1 = detailsEditText(r0);
        if (r1 == null) goto L27;
        int r2 = r1.length();
        int r3 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        int r22 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        if (r3 < r22) goto L9;
        return;
    L9:
        if ((r1.getCurrentStyle(r3, r22) & r9) != 0) goto L11;
        boolean r5 = true;
    L12:
        RichEditorHistory r7 = this.history;
        if (r7 == null) goto L15;
        r7.flush();
    L15:
        this.suppressSpansChanged = true;
        if (r5 == false) goto L21;
        int r52 = clearMaskFor(r9);
        if (r52 == 0) goto L20;
        r1.removeStyle(r52, r3, r22);
    L20:
        r1.addStyle(r9, r3, r22);
    L22:
        this.suppressSpansChanged = false;
        persistDetailsTitle(r0);
        r1.invalidateEffects();
        r1.requestLayout();
        RichEditorHistory r92 = this.history;
        if (r92 == null) goto L25;
        r92.record();
    L25:
        this.delegate.onSelectionChanged();
        refreshSelectionHighlight();
        return;
    L21:
        r1.removeStyle(r9, r3, r22);
        goto L22
    L11:
        r5 = false;
        goto L12
    }

    private void onFormattingClickedTable(int r18) {
        int r2 = this.textSelectionHelper.getStartCell();
        int r3 = this.textSelectionHelper.getStartChildPosition();
        int r5 = this.textSelectionHelper.getEndChildPosition();
        int r4 = this.textSelectionHelper.getStartOffset();
        int r6 = this.textSelectionHelper.getEndOffset();
        boolean r7 = isStyleFullyAppliedTable(r18, r2, r3, r4, r5, r6);
        if (r7 == true) goto L5;
        int r9 = clearMaskFor(r18);
    L6:
        RichEditorHistory r10 = this.history;
        if (r10 == null) goto L9;
        r10.flush();
    L9:
        this.suppressSpansChanged = true;
        int r11 = r3;
        boolean r12 = false;
    L10:
        if (r11 > r5) goto L33;
        RichEditText r13 = tableEditText(r2, r11);
        if (r13 == null) goto L32;
        int r14 = r13.length();
        if (r11 != r3) goto L17;
        int r15 = r4;
    L18:
        if (r11 != r5) goto L20;
        int r16 = r6;
    L21:
        if (r3 != r5) goto L23;
        r15 = Math.min(r4, r6);
        r16 = Math.max(r4, r6);
    L23:
        int r152 = Math.max(0, Math.min(r15, r14));
        int r102 = Math.max(0, Math.min(r16, r14));
        if (r152 >= r102) goto L32;
        if (r7 == true) goto L30;
        if (r9 == 0) goto L29;
        r13.removeStyle(r9, r152, r102);
    L29:
        r13.addStyle(r18, r152, r102);
    L31:
        r13.invalidateEffects();
        r13.requestLayout();
        persistTableCell(r2, r11);
        r12 = true;
        goto L32
    L30:
        r13.removeStyle(r18, r152, r102);
        goto L31
    L20:
        r16 = r14;
        goto L21
    L17:
        r15 = 0;
    L32:
        r11 = r11 + 1;
        goto L10
    L33:
        this.suppressSpansChanged = false;
        if (r12 == false) goto L38;
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L38;
        r1.record();
    L38:
        this.delegate.onSelectionChanged();
        if (r12 == false) goto L46;
        refreshSelectionHighlight();
        return;
    L46:
        return;
    L5:
        r9 = 0;
        goto L6
    }

    private void onItemClick(UItem r1, View r2, int r3, float r4, float r5) {
        if ((r2 instanceof RichTextCell) == false) goto L6;
        ((RichTextCell) r2).requestEditFocus();
        return;
    }

    private void onLanguageClick(BlockRow r9, View r10) {
        if (r9 != null) goto L4;
        return;
    L4:
        if ((r9.block instanceof TL_iv.pageBlockPreformatted) == false) goto L19;
        Set<String> r0 = CodeHighlighting.getLanguages();
        if (r0 == null) goto L20;
        ArrayList r1 = new ArrayList(r0);
        Collections.sort(r1);
        TL_iv.pageBlockPreformatted r02 = (TL_iv.pageBlockPreformatted) r9.block;
        ItemOptions r102 = this.delegate.makeMenu(r10);
        r102.setScrimViewBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(3.0f), Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider)));
        r102.allowShowingOnTopOfKeyboard();
        r102.setMaxHeight(AndroidUtilities.dp(350.0f));
        r102.addChecked(TextUtils.isEmpty(r02.language), LocaleController.getString(R.string.ArticleNone), new RichEditorListView$$ExternalSyntheticLambda79(this, r9));
        if (TextUtils.isEmpty(r02.language) == true) goto L12;
        r102.addChecked(true, MessageObject.TextLayoutBlock.capitalizeLanguage(r02.language), null);
    L12:
        r102.addGap();
        int r2 = r1.size();
        int r3 = 0;
    L13:
        if (r3 >= r2) goto L15;
        Object r4 = r1.get(r3);
        r3 = r3 + 1;
        String r42 = (String) r4;
        r102.addChecked(TextUtils.equals(r42, r02.language), MessageObject.TextLayoutBlock.capitalizeLanguage(r42), new RichEditorListView$$ExternalSyntheticLambda80(this, r9, r42));
        goto L13
    L15:
        r102.show();
        return;
    L20:
        return;
    }

    private void onLinkClickedAuthor() {
        int r0 = this.textSelectionHelper.getStartCell();
        RichEditText r1 = quoteAuthorEditText(r0);
        if (r1 == null) goto L27;
        int r2 = r1.length();
        int r3 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        int r22 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        if (r3 < r22) goto L8;
        return;
    L8:
        boolean r5 = RichTextStyle.hasLink(r1.getText(), r3, r22);
        RichEditorHistory r6 = this.history;
        if (r5 == false) goto L17;
        if (r6 == null) goto L12;
        r6.flush();
    L12:
        RichTextStyle.removeLink(r1.getText(), r3, r22);
        r1.invalidateEffects();
        persistQuoteAuthor(r0);
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L15;
        r02.record();
    L15:
        this.delegate.onSelectionChanged();
        refreshSelectionHighlight();
        return;
    L17:
        if (r6 == null) goto L20;
        r6.flush();
    L20:
        if (RichTextStyle.hasDate(r1.getText(), r3, r22) == false) goto L25;
        RichTextStyle.removeDate(r1.getText(), r3, r22);
        r1.invalidateEffects();
        persistQuoteAuthor(r0);
        RichEditorHistory r03 = this.history;
        if (r03 == null) goto L24;
        r03.record();
    L24:
        refreshSelectionHighlight();
    L25:
        r1.setSelectionOverride(r3, r22);
        hideTextSelectionUi(false);
        showSelectedUrlDialog(r1);
        return;
    }

    private void onLinkClickedCaption() {
        int r0 = this.textSelectionHelper.getStartCell();
        RichEditText r1 = captionEditText(r0);
        if (r1 == null) goto L27;
        int r2 = r1.length();
        int r3 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        int r22 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        if (r3 < r22) goto L8;
        return;
    L8:
        boolean r5 = RichTextStyle.hasLink(r1.getText(), r3, r22);
        RichEditorHistory r6 = this.history;
        if (r5 == false) goto L17;
        if (r6 == null) goto L12;
        r6.flush();
    L12:
        RichTextStyle.removeLink(r1.getText(), r3, r22);
        r1.invalidateEffects();
        persistCaption(r0);
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L15;
        r02.record();
    L15:
        this.delegate.onSelectionChanged();
        refreshSelectionHighlight();
        return;
    L17:
        if (r6 == null) goto L20;
        r6.flush();
    L20:
        if (RichTextStyle.hasDate(r1.getText(), r3, r22) == false) goto L25;
        RichTextStyle.removeDate(r1.getText(), r3, r22);
        r1.invalidateEffects();
        persistCaption(r0);
        RichEditorHistory r03 = this.history;
        if (r03 == null) goto L24;
        r03.record();
    L24:
        refreshSelectionHighlight();
    L25:
        r1.setSelectionOverride(r3, r22);
        hideTextSelectionUi(false);
        showSelectedUrlDialog(r1);
        return;
    }

    private void onLinkClickedDetails() {
        int r0 = this.textSelectionHelper.getStartCell();
        RichEditText r1 = detailsEditText(r0);
        if (r1 == null) goto L27;
        int r2 = r1.length();
        int r3 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        int r22 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r2));
        if (r3 < r22) goto L8;
        return;
    L8:
        boolean r5 = RichTextStyle.hasLink(r1.getText(), r3, r22);
        RichEditorHistory r6 = this.history;
        if (r5 == false) goto L17;
        if (r6 == null) goto L12;
        r6.flush();
    L12:
        RichTextStyle.removeLink(r1.getText(), r3, r22);
        r1.invalidateEffects();
        persistDetailsTitle(r0);
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L15;
        r02.record();
    L15:
        this.delegate.onSelectionChanged();
        refreshSelectionHighlight();
        return;
    L17:
        if (r6 == null) goto L20;
        r6.flush();
    L20:
        if (RichTextStyle.hasDate(r1.getText(), r3, r22) == false) goto L25;
        RichTextStyle.removeDate(r1.getText(), r3, r22);
        r1.invalidateEffects();
        persistDetailsTitle(r0);
        RichEditorHistory r03 = this.history;
        if (r03 == null) goto L24;
        r03.record();
    L24:
        refreshSelectionHighlight();
    L25:
        r1.setSelectionOverride(r3, r22);
        hideTextSelectionUi(false);
        showSelectedUrlDialog(r1);
        return;
    }

    private void onLinkClickedTable() {
        int r0 = this.textSelectionHelper.getStartCell();
        int r1 = this.textSelectionHelper.getStartChildPosition();
        if (r1 != this.textSelectionHelper.getEndChildPosition()) goto L30;
        RichEditText r2 = tableEditText(r0, r1);
        if (r2 == null) goto L31;
        int r3 = r2.length();
        int r4 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r3));
        int r32 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r3));
        if (r4 < r32) goto L11;
        return;
    L11:
        boolean r6 = RichTextStyle.hasLink(r2.getText(), r4, r32);
        RichEditorHistory r7 = this.history;
        if (r6 == false) goto L20;
        if (r7 == null) goto L15;
        r7.flush();
    L15:
        RichTextStyle.removeLink(r2.getText(), r4, r32);
        r2.invalidateEffects();
        persistTableCell(r0, r1);
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L18;
        r02.record();
    L18:
        this.delegate.onSelectionChanged();
        refreshSelectionHighlight();
        return;
    L20:
        if (r7 == null) goto L23;
        r7.flush();
    L23:
        if (RichTextStyle.hasDate(r2.getText(), r4, r32) == false) goto L28;
        RichTextStyle.removeDate(r2.getText(), r4, r32);
        r2.invalidateEffects();
        persistTableCell(r0, r1);
        RichEditorHistory r03 = this.history;
        if (r03 == null) goto L27;
        r03.record();
    L27:
        refreshSelectionHighlight();
    L28:
        r2.setSelectionOverride(r4, r32);
        hideTextSelectionUi(false);
        showSelectedUrlDialog(r2);
        return;
    L31:
        return;
    }

    private void onRowsReordered(int r11, ArrayList<UItem> r12) {
        ArrayList r112 = new ArrayList(r12.size());
        int r0 = r12.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        UItem r3 = r12.get(r2);
        r2 = r2 + 1;
        Object r32 = r3.object;
        if ((r32 instanceof BlockRow) == false) goto L3;
        r112.add((BlockRow) r32);
        goto L3
    L8:
        if (r112.size() < 2) goto L69;
        ArrayList r122 = new ArrayList();
        int r02 = r112.size();
        int r22 = Integer.MAX_VALUE;
        int r33 = -1;
        int r4 = 0;
        int r5 = 0;
    L11:
        if (r5 >= r02) goto L26;
        Object r6 = r112.get(r5);
        r5 = r5 + 1;
        BlockRow r62 = (BlockRow) r6;
        int r7 = this.rows.indexOf(r62);
        if (r7 < 0) goto L71;
        int r8 = r7 + 1;
        if (isDetailsHeader(r62) == false) goto L24;
        if (((TL_iv.pageBlockDetails) r62.block).open == true) goto L24;
        int r63 = matchingDetailsEnd(r7);
        if (r63 < this.rows.size()) goto L23;
        int r64 = this.rows.size();
    L22:
        r8 = r64;
        goto L24
    L23:
        r64 = r63 + 1;
    L24:
        r122.add(new ArrayList(this.rows.subList(r7, r8)));
        r22 = Math.min(r22, r7);
        r33 = Math.max(r33, r8);
        r4 = r4 + (r8 - r7);
        goto L11
    L71:
        return;
    L26:
        if (r4 != (r33 - r22)) goto L70;
        ArrayList r113 = new ArrayList(r4);
        int r03 = r122.size();
        int r34 = 0;
    L29:
        if (r34 >= r03) goto L31;
        Object r42 = r122.get(r34);
        r34 = r34 + 1;
        r113.addAll((ArrayList) r42);
        goto L29
    L31:
        int r123 = 0;
    L33:
        if (r123 >= r113.size()) goto L72;
        if (this.rows.get(r22 + r123) != r113.get(r123)) goto L36;
        r123 = r123 + 1;
        goto L33
    L36:
        RichEditorHistory r124 = this.history;
        if (r124 == null) goto L40;
        r124.flush();
    L40:
        if (r1 >= r113.size()) goto L42;
        this.rows.set(r22 + r1, (BlockRow) r113.get(r1));
        r1 = r1 + 1;
        goto L40
    L42:
        boolean r114 = normalizeQuoteMembership(this.draggingRow);
        boolean r125 = collapseSingleBlockQuotes();
        normalizeLevels();
        renumberAllRuns();
        assignContainers();
        if (r114 == true) goto L47;
        if (r125 == true) goto L47;
        resyncInsetCells(true);
    L48:
        RichEditorHistory r10 = this.history;
        if (r10 == null) goto L53;
        r10.record();
        return;
    L53:
        return;
    L47:
        this.adapter.update(true);
        resyncInsetCells(true);
        goto L48
    L72:
        return;
    L70:
        return;
    }

    private void onTapAboveContent() {
        if (this.rows.isEmpty() == false) goto L5;
        BlockRow r0 = null;
    L6:
        if (r0 != null) goto L8;
    L17:
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L20;
        r02.flush();
    L20:
        BlockRow r03 = new BlockRow(new TL_iv.pageBlockParagraph());
        this.rows.add(0, r03);
        this.adapter.update(false);
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L23;
        r1.record();
    L23:
        post(new RichEditorListView$$ExternalSyntheticLambda55(this, r03));
        return;
    L8:
        if (isNonText(r0.block) == true) goto L17;
        if (r0.detailsEnd == true) goto L17;
        if (isDetailsHeader(r0) == true) goto L17;
        if (endsWithOwnParagraph(r0.block) == true) goto L17;
        focusRowAtStart(r0);
        return;
    L5:
        r0 = this.rows.get(0);
        goto L6
    }

    private void onTapBelowContent() {
        if (this.rows.isEmpty() == false) goto L5;
        BlockRow r0 = null;
    L6:
        if (r0 != null) goto L8;
    L19:
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L22;
        r02.flush();
    L22:
        BlockRow r03 = new BlockRow(new TL_iv.pageBlockParagraph());
        this.rows.add(r03);
        this.adapter.update(false);
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L25;
        r1.record();
    L25:
        post(new RichEditorListView$$ExternalSyntheticLambda61(this, r03));
        return;
    L8:
        if (r0.quoteIds.isEmpty() == false) goto L19;
        if (isNonText(r0.block) == true) goto L19;
        if (r0.detailsEnd == true) goto L19;
        if (isDetailsHeader(r0) == true) goto L19;
        if (endsWithOwnParagraph(r0.block) == true) goto L19;
        focusRowAtEnd(r0);
        return;
    L5:
        r0 = this.rows.get(r0.size() - 1);
        goto L6
    }

    private void openLatexEditorAndAdd() {
        ChatAttachAlertRichLayout.showEditLatexSheet(getContext(), _UrlKt.FRAGMENT_ENCODE_SET, new RichEditorListView$$ExternalSyntheticLambda56(this), this.resourcesProvider);
    }

    private void openMathEditor(BlockRow r4) {
        if (r4 == null) goto L12;
        TL_iv.PageBlock r42 = r4.block;
        if ((r42 instanceof TL_iv.pageBlockMath) == false) goto L13;
        TL_iv.pageBlockMath r43 = (TL_iv.pageBlockMath) r42;
        Context r0 = getContext();
        if (TextUtils.isEmpty(r43.source) == false) goto L9;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        ChatAttachAlertRichLayout.showEditLatexSheet(r0, r1, new RichEditorListView$$ExternalSyntheticLambda57(this, r43), this.resourcesProvider);
        return;
    L9:
        r1 = r43.source;
        goto L10
    L13:
        return;
    }

    private void pasteAtHelperSelection() {
        ClipboardManager r0 = (ClipboardManager) getContext().getSystemService("clipboard");
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.hasPrimaryClip() == false) goto L46;
        ClipData r02 = r0.getPrimaryClip();
        if (r02 != null) goto L10;
        return;
    L10:
        if (r02.getItemCount() == 0) goto L47;
        ClipData.Item r1 = r02.getItemAt(0);
        if (r02.getDescription() != null) goto L15;
    L18:
        String r03 = null;
    L20:
        if (TextUtils.isEmpty(r03) == false) goto L41;
    L33:
        CharSequence r04 = r1.coerceToText(getContext());
        if (r04 != null) goto L36;
        r04 = _UrlKt.FRAGMENT_ENCODE_SET;
    L36:
        applyEditRange(this.textSelectionHelper.getStartCell(), this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndCell(), this.textSelectionHelper.getEndOffset(), r04.toString().split("\n", -1));
        return;
    L41:
        HashMap r2 = new HashMap();     // Catch: Exception -> L31
        List<BlockRow> r05 = resolvePastedMedia(RichHtml.parse(r03, r2));     // Catch: Exception -> L31
        if (r05 == null) goto L33;
        if (r05.isEmpty() == true) goto L33;
        if (pasteBlocksAtSelection(r05) == false) goto L33;
        if (r2.isEmpty() == true) goto L45;
        this.quoteAuthors.putAll(r2);     // Catch: Exception -> L31
        return;
    L45:
        return;
    L31:
        e = move-exception;
        FileLog.e(e);
        goto L33
    L15:
        if (r02.getDescription().hasMimeType("text/html") == false) goto L18;
        r03 = r1.getHtmlText();     // Catch: Exception -> L38
    L47:
        return;
    }

    private boolean pasteBlocksAtSelection(List<BlockRow> r9) {
        return spliceBlocksInto(this.rows.indexOf(rowForCell(this.textSelectionHelper.getStartCell())), this.rows.indexOf(rowForCell(this.textSelectionHelper.getEndCell())), this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset(), r9);
    }

    private void persistCaption(int r1) {
        KeyEvent.Callback r0 = selectableAt(r1);
        if ((r0 instanceof RichCaptionHost) == false) goto L6;
        ((RichCaptionHost) r0).persistCaption();
        return;
    }

    private void persistDetailsTitle(int r2) {
        BlockRow r0 = rowForCell(r2);
        RichEditText r1 = detailsEditText(r2);
        if (isDetailsHeader(r0) == false) goto L8;
        if (r1 == null) goto L9;
        TL_iv.pageBlockDetails r22 = (TL_iv.pageBlockDetails) r0.block;
        r22.title = RichTextStyle.fromSpannable(r1.getText());
        return;
    L9:
        return;
    }

    private void persistQuoteAuthor(int r1) {
        View r0 = selectableAt(r1);
        if ((r0 instanceof RichTextCell) == false) goto L6;
        ((RichTextCell) r0).persistAuthor();
        return;
    }

    private void persistTableCell(int r1, int r2) {
        View r0 = selectableAt(r1);
        if ((r0 instanceof RichTableCell) == false) goto L17;
        RichTableCell r02 = (RichTableCell) r0;
        if (r2 != r02.titleChildPos()) goto L9;
        r02.persistTitleFromEditor();
        return;
    L9:
        TL_iv.pageTableCell r12 = r02.anchorForChildPos(r2);
        if (r12 == null) goto L18;
        RichTableCellHost r03 = r02.getGrid().hostForAnchor(r12);
        if (r03 == null) goto L16;
        TableModel.applyStyledText(r12, r03.editText.getText());
        return;
    L16:
        return;
    L18:
        return;
    }

    private boolean placeCaretAtScreenPoint(RichEditText r4, float r5, float r6) {
        if (r4 != null) goto L5;
    L21:
        return false;
    L5:
        if (r4.getVisibility() != 0) goto L21;
        r4.getLocationOnScreen(new int[2]);
        float r52 = r5 - r0[0];
        float r62 = r6 - r0[1];
        if (r52 < 0.0f) goto L21;
        if (r62 < 0.0f) goto L21;
        if (r52 > r4.getWidth()) goto L21;
        if (r62 > r4.getHeight()) goto L21;
        int r53 = r4.getOffsetForPosition(r52, r62);
        if (r53 >= 0) goto L19;
        r53 = 0;
    L19:
        r4.requestEditFocusRebuild();
        r4.setSelection(Math.max(0, Math.min(r53, r4.length())));
        return true;
    }

    private void prepareEditText(RichEditText r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        r2.adaptiveCreateLinkDialog = this.adaptiveLinkDialogs;
        r2.setInlineButtonContext(this.currentAccount);
        r2.setInlineButtonClickListener(new RichEditorListView$$ExternalSyntheticLambda71(this));
    }

    private int prevTextOffset(int r2) {
        View r1 = selectableAt(r2);
        if ((r1 instanceof RichTextCell) == false) goto L8;
        Layout r12 = ((RichTextCell) r1).getEditText().getLayout();
        if (r12 == null) goto L8;
        return r12.getText().length();
    L8:
        return 0;
    }

    private TL_iv.RichText quoteAuthor(long r1) {
        TL_iv.RichText r0 = this.quoteAuthors.get(Long.valueOf(r1));
        if (r0 == null) goto L6;
        return r0;
    L6:
        return new TL_iv.textEmpty();
    }

    private boolean quoteAuthorSelectionHasFormattable() {
        RichEditText r0 = quoteAuthorEditText(this.textSelectionHelper.getStartCell());
        if (r0 != null) goto L5;
        return false;
    L5:
        int r2 = this.textSelectionHelper.getStartOffset();
        int r5 = this.textSelectionHelper.getEndOffset();
        if (Math.max(0, Math.min(Math.min(r2, r5), r0.length())) >= Math.max(0, Math.min(Math.max(r2, r5), r0.length()))) goto L9;
        return true;
    L9:
        return false;
    }

    private int quoteBgVinset(int r1, BlockRow r2, boolean r3) {
        if (r2 == null) goto L5;
        if (r3 == false) goto L8;
        int r32 = r2.quoteTopEdge;
    L9:
        int r22 = r2.quoteIds.size() - r32;
        return AndroidUtilities.dp(2.0f) + (Math.max(0, r1 - r22) * AndroidUtilities.dp(16.0f));
    L8:
        r32 = r2.quoteBottomEdge;
        goto L9
    L5:
        return AndroidUtilities.dp(2.0f);
    }

    private static int quoteCommonPrefixLen(BlockRow r4, BlockRow r5) {
        int r0 = 0;
        if (r4 == null) goto L11;
        if (r5 == null) goto L11;
        int r1 = Math.min(r4.quoteIds.size(), r5.quoteIds.size());
    L7:
        if (r0 >= r1) goto L11;
        if (r4.quoteIds.get(r0).equals(r5.quoteIds.get(r0)) == false) goto L11;
        r0 = r0 + 1;
    L11:
        return r0;
    }

    private boolean rangeIndent(int r5, int r6, boolean r7) {
        if (r5 < 0) goto L40;
        if (r6 < r5) goto L40;
        if (r6 >= this.rows.size()) goto L40;
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L12;
        r1.flush();
    L12:
        if (r7 == false) goto L19;
        boolean r72 = false;
    L14:
        if (r6 < r5) goto L31;
        if (indentRow(r6, true, true) == false) goto L18;
        r72 = true;
    L18:
        r6 = r6 - 1;
    L31:
        if (r72 == false) goto L39;
        renumberAllRuns();
        if (hasFocusedEdit() == false) goto L35;
        applyInPlaceUpdateKeepingFocus();
    L36:
        RichEditorHistory r4 = this.history;
        if (r4 == null) goto L39;
        r4.record();
        goto L39
    L35:
        this.adapter.update(false);
    L39:
        return r72;
    L19:
        BlockRow r73 = this.rows.get(r5);
        if (r73.level < 1) goto L25;
        if (r5 != 0) goto L23;
    L24:
        return false;
    L23:
        if (this.rows.get(r5 - 1).level < r73.level) goto L24;
    L25:
        r72 = false;
    L26:
        if (r5 > r6) goto L31;
        if (indentRow(r5, false, true) == false) goto L30;
        r72 = true;
    L30:
        r5 = r5 + 1;
    L40:
        return false;
    }

    private void refreshMediaCell(BlockRow r1) {
        View r0 = findViewByItemObject(r1);
        if ((r0 instanceof RichMediaCell) == false) goto L6;
        ((RichMediaCell) r0).refresh();
        return;
    }

    private void refreshSelectionHighlight() {
        post(new RichEditorListView$$ExternalSyntheticLambda29(this));
    }

    private void refreshVisibleListPaddingAround(int r4) {
        int r0 = Math.max(0, r4 - 1);
        int r42 = Math.min(this.rows.size() - 1, r4 + 1);
    L3:
        if (r0 > r42) goto L11;
        BlockRow r1 = this.rows.get(r0);
        if (r1.level <= 0) goto L10;
        View r12 = findViewByItemObject(r1);
        if ((r12 instanceof RichTextCell) == false) goto L10;
        ((RichTextCell) r12).refreshListVerticalPadding();
    L10:
        r0 = r0 + 1;
        goto L3
    }

    private void removeMediaFromRow(BlockRow r4, MediaUploadState r5) {
        if (r4 != null) goto L5;
        return;
    L5:
        if (isGallery(r4.block) == false) goto L26;
        ArrayList<TL_iv.PageBlock> r0 = galleryItems(r4.block);
        ArrayList<MediaUploadState> r1 = r4.medias;
        if (r1 == null) goto L9;
        int r52 = r1.indexOf(r5);
    L10:
        if (r52 < 0) goto L17;
        r4.medias.remove(r52);
        if (r0 == null) goto L17;
        if (r52 >= r0.size()) goto L17;
        r0.remove(r52);
    L17:
        if (r4.medias.isEmpty() == false) goto L21;
        removeRow(r4);
        return;
    L21:
        if (r4.medias.size() != 1) goto L24;
        TL_iv.PageCaption r53 = r4.block.caption;
        TL_iv.PageBlock r02 = r0.get(0);
        r4.block = r02;
        r02.caption = r53;
        r4.media = r4.medias.get(0);
        r4.medias = null;
        this.adapter.update(true);
        refreshMediaCell(r4);
        return;
    L24:
        this.adapter.update(true);
        refreshMediaCell(r4);
        return;
    L9:
        r52 = -1;
        goto L10
    L26:
        removeRow(r4);
    }

    private void removeRow(BlockRow r2) {
        int r22 = this.rows.indexOf(r2);
        if (r22 < 0) goto L6;
        this.rows.remove(r22);
        this.adapter.update(true);
        return;
    }

    private void removeSelectedBlocks(int r9, int r10) {
        if (r9 <= r10) goto L4;
        r10 = r9;
        r9 = r10;
    L4:
        ArrayList r0 = new ArrayList();
        int r92 = Math.max(0, r9);
    L5:
        if (r92 > r10) goto L16;
        if (r92 >= this.itemRows.size()) goto L16;
        BlockRow r2 = this.itemRows.get(r92);
        if (r2.authorQuoteId != 0) goto L14;
        if (this.rows.indexOf(r2) < 0) goto L14;
        r0.add(r2);
    L14:
        r92 = r92 + 1;
    L16:
        if (r0.isEmpty() == false) goto L18;
        return;
    L18:
        RichEditorHistory r93 = this.history;
        if (r93 == null) goto L21;
        r93.flush();
    L21:
        int r94 = this.rows.indexOf(r0.get(0));
        this.rows.removeAll(r0);
        if (this.rows.isEmpty() == false) goto L24;
        this.rows.add(new BlockRow(new TL_iv.pageBlockParagraph()));
    L24:
        gcQuoteAuthors();
        collapseSingleBlockQuotes();
        normalizeNestedQuotes();
        renumberAllRuns();
        TextSelectionHelper.ArticleTextSelectionHelper r102 = this.textSelectionHelper;
        if (r102 == null) goto L27;
        r102.clear();
    L27:
        this.adapter.update(false);
        RichEditorHistory r103 = this.history;
        if (r103 == null) goto L30;
        r103.record();
    L30:
        post(new RichEditorListView$$ExternalSyntheticLambda26(this, this.rows.get(Math.max(0, Math.min(r94, r10.size() - 1)))));
    }

    private void renumberAllRuns() {
        int r0 = 0;
    L4:
        if (r0 >= this.rows.size()) goto L29;
        BlockRow r1 = this.rows.get(r0);
        if (r1.level <= 0) goto L28;
        if (r1.num <= 0) goto L28;
        if (isNonText(r1.block) == true) goto L28;
        int r2 = r1.level;
        int r3 = r0 - 1;
        int r4 = 1;
    L14:
        if (r3 < 0) goto L27;
        BlockRow r5 = this.rows.get(r3);
        int r6 = r5.level;
        if (r6 < r2) goto L27;
        if (r6 != r2) goto L26;
        if (isNonText(r5.block) == true) goto L26;
        if (r5.num <= 0) goto L27;
        r4 = r4 + 1;
    L26:
        r3 = r3 - 1;
    L27:
        r1.num = r4;
    L28:
        r0 = r0 + 1;
        goto L4
    }

    private void replaceHelperSelectionWith(String r8) {
        applyEditRange(this.textSelectionHelper.getStartCell(), this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndCell(), this.textSelectionHelper.getEndOffset(), r8.split("\n", -1));
    }

    private void reselectRowRange(BlockRow r8, BlockRow r9) {
        if (this.textSelectionHelper == null) goto L24;
        if (r8 == null) goto L25;
        if (r9 == null) goto L27;
        int r82 = this.itemRows.indexOf(r8);
        int r92 = this.itemRows.indexOf(r9);
        if (r82 < 0) goto L26;
        if (r92 < 0) goto L28;
        int r0 = 0;
    L13:
        if (r0 >= this.itemRows.size()) goto L19;
        BlockRow r1 = this.itemRows.get(r0);
        long r2 = r1.authorQuoteId;
        TextSelectionHelper.ArticleTextSelectionHelper r5 = this.textSelectionHelper;
        if (r2 == 0) goto L17;
        r5.cacheText(r0, RichTextStyle.plainOf(this.quoteAuthors.get(Long.valueOf(r2))), null);
    L18:
        r0 = r0 + 1;
        goto L13
    L17:
        r5.cacheText(r0, RichTextCell.readPlainText(r1.block), null);
        goto L18
    L19:
        this.textSelectionHelper.selectAllBlocksRange(Math.min(r82, r92), Math.max(r82, r92));
        return;
    L28:
        return;
    L26:
        return;
    L27:
        return;
    L25:
        return;
    }

    private MediaUploadState resolveBlockMedia(TL_iv.PageBlock r7) {
        if ((r7 instanceof TL_iv.pageBlockAudio) == false) goto L10;
        TLRPC.Document r6 = findLoadedDocument(((TL_iv.pageBlockAudio) r7).audio_id);
        if (r6 != null) goto L7;
        return null;
    L7:
        MediaUploadState r72 = new MediaUploadState();
        r72.isAudio = true;
        r72.state = 2;
        r72.document = r6;
        r72.audioDisplayDocument = r6;
        return r72;
    L10:
        if ((r7 instanceof TL_iv.pageBlockDocument) == false) goto L17;
        TLRPC.Document r62 = findLoadedDocument(((TL_iv.pageBlockDocument) r7).document_id);
        if (r62 != null) goto L14;
        return null;
    L14:
        MediaUploadState r73 = new MediaUploadState();
        r73.isDocument = true;
        r73.state = 2;
        r73.document = r62;
        return r73;
    L17:
        if ((r7 instanceof TL_iv.pageBlockVideo) == false) goto L31;
        TL_iv.pageBlockVideo r74 = (TL_iv.pageBlockVideo) r7;
        TLRPC.Document r63 = findLoadedDocument(r74.video_id);
        if (r63 != null) goto L21;
        return null;
    L21:
        MediaUploadState r0 = new MediaUploadState();
        r0.isVideo = true;
        r0.state = 2;
        r0.document = r63;
        r0.hasSpoiler = r74.spoiler;
        int r75 = 0;
    L23:
        if (r75 >= r63.attributes.size()) goto L29;
        if ((r63.attributes.get(r75) instanceof TLRPC.TL_documentAttributeVideo) == true) goto L26;
        r75 = r75 + 1;
        goto L23
    L26:
        TLRPC.TL_documentAttributeVideo r64 = (TLRPC.TL_documentAttributeVideo) r63.attributes.get(r75);
        r0.width = r64.w;
        r0.height = r64.h;
        r0.duration = (int) r64.duration;
        return r0;
    L29:
        return r0;
    L31:
        if ((r7 instanceof TL_iv.pageBlockPhoto) == false) goto L39;
        TL_iv.pageBlockPhoto r76 = (TL_iv.pageBlockPhoto) r7;
        TLRPC.Photo r65 = findLoadedPhoto(r76.photo_id);
        if (r65 != null) goto L35;
        return null;
    L35:
        MediaUploadState r02 = new MediaUploadState();
        r02.state = 2;
        r02.photo = r65;
        r02.hasSpoiler = r76.spoiler;
        TLRPC.PhotoSize r66 = FileLoader.getClosestPhotoSizeWithSize(r65.sizes, AndroidUtilities.getPhotoSize());
        if (r66 == null) goto L38;
        r02.width = r66.w;
        r02.height = r66.h;
    L38:
        return r02;
    L39:
        return null;
    }

    private void resolveLoadedMedia(BlockRow r5) {
        if (this.loadedRichMessage == null) goto L27;
        if (r5 == null) goto L28;
        TL_iv.PageBlock r0 = r5.block;
        if (r0 == null) goto L31;
        boolean r02 = isGallery(r0);
        TL_iv.PageBlock r1 = r5.block;
        if (r02 == false) goto L20;
        ArrayList<TL_iv.PageBlock> r03 = galleryItems(r1);
        r5.medias = new ArrayList();
        if (r03 == null) goto L29;
        int r12 = 0;
    L14:
        if (r12 >= r03.size()) goto L32;
        MediaUploadState r2 = resolveBlockMedia(r03.get(r12));
        ArrayList<MediaUploadState> r3 = r5.medias;
        if (r2 != null) goto L19;
        r2 = new MediaUploadState();
    L19:
        r3.add(r2);
        r12 = r12 + 1;
        goto L14
    L32:
        return;
    L29:
        return;
    L20:
        MediaUploadState r4 = resolveBlockMedia(r1);
        if (r4 == null) goto L30;
        r5.media = r4;
        return;
    L30:
        return;
    L31:
        return;
    L28:
        return;
    }

    private List<BlockRow> resolvePastedMedia(List<BlockRow> r10) {
        if (r10 != null) goto L5;
        return null;
    L5:
        ArrayList r0 = new ArrayList(r10.size());
        Iterator<BlockRow> r102 = r10.iterator();
    L7:
        if (r102.hasNext() == false) goto L37;
        BlockRow r1 = r102.next();
        boolean r2 = isGallery(r1.block);
        TL_iv.PageBlock r3 = r1.block;
        if (r2 == true) goto L10;
        if ((r3 instanceof TL_iv.pageBlockPhoto) == true) goto L33;
        if ((r3 instanceof TL_iv.pageBlockVideo) == true) goto L33;
        if ((r3 instanceof TL_iv.pageBlockAudio) == true) goto L33;
        if ((r3 instanceof TL_iv.pageBlockDocument) == true) goto L33;
        r0.add(r1);
    L33:
        MediaUploadState r22 = resolveBlockMedia(r3);
        if (r22 == null) goto L7;
        r1.media = r22;
        r0.add(r1);
        goto L7
    L10:
        ArrayList<TL_iv.PageBlock> r23 = galleryItems(r3);
        ArrayList r32 = new ArrayList();
        ArrayList<MediaUploadState> r4 = new ArrayList();
        if (r23 == null) goto L20;
        int r5 = r23.size();
        int r6 = 0;
    L13:
        if (r6 >= r5) goto L18;
        TL_iv.PageBlock r7 = r23.get(r6);
        r6 = r6 + 1;
        TL_iv.PageBlock r72 = r7;
        MediaUploadState r8 = resolveBlockMedia(r72);
        if (r8 == null) goto L13;
        r32.add(r72);
        r4.add(r8);
        goto L13
    L18:
        r23.clear();
        r23.addAll(r32);
    L20:
        if (r4.isEmpty() == true) goto L7;
        r1.medias = r4;
        r0.add(r1);
        goto L7
    L37:
        return r0;
    }

    private void restoreCaret(BlockRow r2, int r3) {
        if (r3 >= 0) goto L4;
        return;
    L4:
        post(new RichEditorListView$$ExternalSyntheticLambda3(this, r2, r3));
    }

    private void restoreFocusAt(int r2, int r3, int r4) {
        if (r2 < 0) goto L28;
        KeyEvent.Callback r1 = this.layoutManager.findViewByPosition(r2);
        if ((r1 instanceof RichTextCell) == false) goto L15;
        RichTextCell r12 = (RichTextCell) r1;
        if (r3 == 1) goto L9;
    L11:
        RichEditText r13 = r12.getEditText();
    L12:
        r13.requestEditFocusRebuild();
        r13.setSelection(Math.max(0, Math.min(r4, r13.length())));
        return;
    L9:
        if (r12.isAuthorVisible() == false) goto L11;
        r13 = r12.getAuthorEditText();
        goto L12
    L15:
        if ((r1 instanceof RichTableCell) == false) goto L24;
        RichTableCell r14 = (RichTableCell) r1;
        RichEditText r22 = r14.editTextForChildPos(r3);
        if (r22 != null) goto L19;
        r22 = r14.editTextForChildPos(r14.titleChildPos());
    L19:
        if (r22 == null) goto L29;
        r22.requestEditFocusRebuild();
        r22.setSelection(Math.max(0, Math.min(r4, r22.length())));
        return;
    L29:
        return;
    L24:
        if ((r1 instanceof RichCaptionHost) == false) goto L27;
        RichEditText r15 = ((RichCaptionHost) r1).getCaptionEditText();
        r15.requestEditFocusRebuild();
        r15.setSelection(Math.max(0, Math.min(r4, r15.length())));
        return;
    L27:
        return;
    }

    private boolean restoreFocusAtScreenPoint(float r6, float r7) {
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L26;
        KeyEvent.Callback r2 = getChildAt(r1);
        if ((r2 instanceof RichTextCell) == false) goto L16;
        RichTextCell r22 = (RichTextCell) r2;
        if (placeCaretAtScreenPoint(r22.getEditText(), r6, r7) == true) goto L9;
        if (r22.isAuthorVisible() == false) goto L25;
        if (placeCaretAtScreenPoint(r22.getAuthorEditText(), r6, r7) == false) goto L25;
        return true;
    L25:
        r1 = r1 + 1;
        goto L4
    L9:
        return true;
    L16:
        if ((r2 instanceof RichCaptionHost) == false) goto L21;
        if (placeCaretAtScreenPoint(((RichCaptionHost) r2).getCaptionEditText(), r6, r7) == false) goto L25;
        return true;
    L21:
        if ((r2 instanceof RichDetailsCell) == false) goto L25;
        if (placeCaretAtScreenPoint(((RichDetailsCell) r2).getEditText(), r6, r7) == false) goto L25;
        return true;
    L26:
        return false;
    }

    private void restoreFromHistory(List<BlockRow> r5, RichEditorHistory.FocusState r6) {
        this.textSelectionHelper.clear();
        this.rows.clear();
        this.rows.addAll(r5);
        renumberAllRuns();
        this.adapter.update(false);
        if (r6 != null) goto L5;
    L7:
        this.delegate.onContentChanged();
        return;
    L5:
        if (r6.rowId < 0) goto L7;
        post(new RichEditorListView$$ExternalSyntheticLambda75(this, r6));
        goto L7
    }

    private void resyncInsetCells(boolean r4) {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L9;
        KeyEvent.Callback r1 = getChildAt(r0);
        if ((r1 instanceof RichInsetCell) == false) goto L8;
        ((RichInsetCell) r1).resyncBlockInset(r4);
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        invalidate();
    }

    private BlockRow rowFromHolder(RecyclerView.ViewHolder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        int r22 = r2.getAdapterPosition();
        if (r22 >= 0) goto L8;
        return null;
    L8:
        UItem r1 = this.adapter.getItem(r22);
        if (r1 == null) goto L14;
        Object r12 = r1.object;
        if ((r12 instanceof BlockRow) == false) goto L14;
        return (BlockRow) r12;
    L14:
        return null;
    }

    private BlockRow rowOfChild(View r2) {
        int r22 = getChildAdapterPosition(r2);
        if (r22 >= 0) goto L5;
        return null;
    L5:
        if (r22 < this.itemRows.size()) goto L7;
        return null;
    L7:
        return this.itemRows.get(r22);
    }

    private boolean selectCurrentUnit() {
        RichEditText r0 = findFocusedEditText();
        if (r0 != null) goto L5;
    L35:
        return false;
    L5:
        if (r0.getText() == null) goto L35;
        int r2 = r0.getText().length();
        if (r2 > 0) goto L10;
        return false;
    L10:
        RichTableCell r3 = findTableCellAncestor(r0);
        if (r3 != null) goto L13;
        RichCaptionHost r32 = findCaptionHostAncestor(r0);
        if ((r32 instanceof TextSelectionHelper.ArticleSelectableView) == false) goto L27;
        r0.setSelection(r0.getSelectionEnd());
        return this.textSelectionHelper.selectRangeOf((TextSelectionHelper.ArticleSelectableView) r32, 0, 0, r2);
    L27:
        RichTextCell r33 = cellForEditText(r0);
        if (r33 == null) goto L35;
        if (r0 != r33.getAuthorEditText()) goto L32;
        int r4 = 1;
    L33:
        r0.setSelection(r0.getSelectionEnd());
        return this.textSelectionHelper.selectRangeOf(r33, r4, 0, r2);
    L32:
        r4 = 0;
        goto L33
    L13:
        if (r0 != r3.getTitleEditText()) goto L15;
        int r42 = r3.titleChildPos();
    L19:
        if (r42 >= 0) goto L21;
        return false;
    L21:
        r0.setSelection(r0.getSelectionEnd());
        return this.textSelectionHelper.selectRangeOf(r3, r42, 0, r2);
    L15:
        RichTableCellHost r43 = r3.findHostContaining(r0);
        if (r43 == null) goto L18;
        r42 = r3.childPosForAnchor(r43.cell);
        goto L19
    L18:
        r42 = -1;
        goto L19
    }

    private boolean selectWholeTable(RichTableCell r9) {
        int[] r0 = wholeTableEnd(r9);
        if (r0 != null) goto L6;
        return false;
    L6:
        return this.textSelectionHelper.selectChildRange(r9, 0, 0, r0[0], r0[1]);
    }

    private View selectableAt(int r1) {
        if (r1 >= 0) goto L6;
        return null;
    L6:
        return this.layoutManager.findViewByPosition(r1);
    }

    private int[] selectedRowRange() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
    L13:
        BlockRow r02 = findFocusedRow();
        if (r02 == null) goto L16;
        int r4 = this.rows.indexOf(r02);
    L17:
        if (r4 >= 0) goto L19;
        return null;
    L19:
        return new int[]{r4, r4};
    L16:
        r4 = -1;
        goto L17
    L5:
        if (r0.isInSelectionMode() == false) goto L13;
        int r03 = this.rows.indexOf(rowForCell(this.textSelectionHelper.getStartCell()));
        int r42 = this.rows.indexOf(rowForCell(this.textSelectionHelper.getEndCell()));
        if (r03 < 0) goto L12;
        if (r42 < 0) goto L12;
        return new int[]{Math.min(r03, r42), Math.max(r03, r42)};
    L12:
        return null;
    }

    private int[] selectionRowRange() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        int r02 = r0.getStartCell();
        int r2 = this.textSelectionHelper.getEndCell();
        if (r02 < 0) goto L21;
        if (r2 < 0) goto L21;
        BlockRow r03 = rowForCell(r02);
        BlockRow r22 = rowForCell(r2);
        if (r03 == null) goto L21;
        if (r22 == null) goto L21;
        int r04 = this.rows.indexOf(r03);
        int r5 = this.rows.indexOf(r22);
        if (r04 < 0) goto L21;
        if (r5 < 0) goto L21;
        if (r04 <= r5) goto L20;
        r04 = r5;
        r5 = r04;
    L20:
        return new int[]{r04, r5};
    L21:
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setEditTextsLocked(boolean r4) {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L21;
        View r1 = getChildAt(r0);
        if ((r1 instanceof RichTextCell) == false) goto L9;
        ((RichTextCell) r1).setLocked(r4);
    L20:
        r0 = r0 + 1;
        goto L4
    L9:
        if ((r1 instanceof RichTableCell) == false) goto L12;
        ((RichTableCell) r1).setLocked(r4);
        goto L20
    L12:
        if ((r1 instanceof RichCaptionHost) == false) goto L15;
        ((RichCaptionHost) r1).getCaptionEditText().setLocked(r4);
        goto L20
    L15:
        if ((r1 instanceof RichDetailsCell) == false) goto L18;
        ((RichDetailsCell) r1).setLocked(r4);
        goto L20
    L18:
        if ((r1 instanceof RichDividerCell) == false) goto L20;
        r1.invalidate();
        goto L20
    }

    private void showSelectedUrlDialog(RichEditText r2) {
        r2.adaptiveCreateLinkDialog = this.adaptiveLinkDialogs;
        r2.makeSelectedUrl(new RichEditorListView$$ExternalSyntheticLambda31(this));
    }

    private RichTextCell singleSelectionCell() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
    L16:
        return null;
    L5:
        if (r0.isInSelectionMode() == false) goto L16;
        int r02 = this.textSelectionHelper.getStartCell();
        int r2 = this.textSelectionHelper.getEndCell();
        BlockRow r3 = rowForCell(r02);
        if (r02 != r2) goto L16;
        if (r3 == null) goto L16;
        if (isFormattable(r3.block) == true) goto L15;
        return null;
    L15:
        return cellAt(r02);
    }

    private CharSequence singleSelectionText(int r5, int r6, int r7, int r8) {
        if (r5 == r7) goto L6;
        return null;
    L6:
        if (isDetailsHeader(rowForCell(r5)) == false) goto L13;
        RichEditText r4 = detailsEditText(r5);
        if (r4 != null) goto L10;
        return null;
    L10:
        return r4.getText();
    L13:
        if (isQuoteAuthorSelection() == false) goto L19;
        RichEditText r42 = quoteAuthorEditText(r5);
        if (r42 != null) goto L17;
        return null;
    L17:
        return r42.getText();
    L19:
        BlockRow r72 = rowForCell(r5);
        if (r72 != null) goto L22;
    L33:
        return null;
    L22:
        if (isFormattable(r72.block) == false) goto L33;
        int r1 = blockTextLength(r5);
        if (Math.max(0, Math.min(Math.min(r6, r8), r1)) < Math.max(0, Math.min(Math.max(r6, r8), r1))) goto L27;
        return null;
    L27:
        RichTextCell r43 = cellAt(r5);
        if (r43 == null) goto L32;
        return r43.getEditText().getText();
    L32:
        return RichTextCell.readStyledText(r72.block);
    }

    private TL_iv.PageBlock sliceClone(BlockRow r3, int r4, int r5) {
        if (isFormattable(r3.block) == true) goto L6;
        return null;
    L6:
        CharSequence r2 = styledTextOf(r3);
        int r0 = r2.length();
        int r42 = Math.max(0, Math.min(r4, r0));
        if (r5 < 0) goto L10;
        r0 = Math.max(0, Math.min(r5, r0));
    L10:
        SpannableStringBuilder r52 = new SpannableStringBuilder(r2.subSequence(Math.min(r42, r0), Math.max(r42, r0)));
        TL_iv.PageBlock r22 = cloneBlock(r3.block);
        RichTextCell.applyStyledTextToBlock(r22, r52);
        return r22;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v8, types: [java.lang.CharSequence] */
    private boolean spliceBlocksInto(int r9, int r10, int r11, int r12, List<BlockRow> r13) {
        if (r9 < 0) goto L72;
        if (r10 < 0) goto L72;
        if (r9 >= this.rows.size()) goto L72;
        if (r10 >= this.rows.size()) goto L72;
        if (r9 <= r10) goto L13;
        r10 = r9;
        r9 = r10;
    L12:
        r12 = r11;
        r11 = r12;
    L16:
        int r1 = r9;
    L17:
        ArrayList<BlockRow> r2 = this.rows;
        if (r1 > r10) goto L32;
        BlockRow r22 = r2.get(r1);
        if (r22.detailsEnd == true) goto L31;
        if (isDetailsHeader(r22) == true) goto L31;
        if (isNonText(r22.block) == true) goto L31;
        if (hasCaption(r22.block) == true) goto L31;
        if ((r22.block instanceof TL_iv.pageBlockTable) == true) goto L31;
        r1 = r1 + 1;
    L31:
        return false;
    L32:
        BlockRow r14 = r2.get(r9);
        BlockRow r23 = this.rows.get(r10);
        CharSequence r3 = RichTextCell.readStyledText(r14.block);
        if (r9 != r10) goto L35;
        CharSequence r4 = r3;
    L36:
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r3 != null) goto L39;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L39:
        if (r4 != null) goto L41;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L41:
        int r112 = Math.max(0, Math.min(r11, r3.length()));
        int r122 = Math.max(0, Math.min(r12, r4.length()));
        CharSequence r113 = r3.subSequence(0, r112);
        CharSequence r123 = r4.subSequence(r122, r4.length());
        RichEditorHistory r32 = this.history;
        if (r32 == null) goto L45;
        r32.flush();
    L45:
        if (r13.size() == 1) goto L47;
    L55:
        ArrayList r33 = new ArrayList(r13);
        if (r113.length() <= 0) goto L58;
        TL_iv.PageBlock r52 = newSameTypeBlock(r14.block);
        RichTextCell.applyStyledTextToBlock(r52, r113);
        r33.add(0, new BlockRow(r52, r14.level, r14.num));
    L58:
        r14 = r13.get(r13.size() - 1);
        int r114 = caretEndOf(r14);
        if (r123.length() <= 0) goto L61;
        TL_iv.PageBlock r132 = newSameTypeBlock(r23.block);
        RichTextCell.applyStyledTextToBlock(r132, r123);
        r33.add(new BlockRow(r132, r23.level, r23.num));
    L61:
        if (r10 < r9) goto L63;
        this.rows.remove(r10);
        r10 = r10 - 1;
        goto L61
    L63:
        int r102 = 0;
    L65:
        if (r102 >= r33.size()) goto L67;
        this.rows.add(r9 + r102, (BlockRow) r33.get(r102));
        r102 = r102 + 1;
    L67:
        renumberAllRuns();
        this.textSelectionHelper.clear();
        this.adapter.update(false);
        RichEditorHistory r92 = this.history;
        if (r92 == null) goto L70;
        r92.record();
    L70:
        post(new RichEditorListView$$ExternalSyntheticLambda32(this, r14, r114));
        return true;
    L47:
        if (isPlainParagraphRow(r13.get(0)) == false) goto L55;
        ?? r133 = RichTextCell.readStyledText(r13.get(0).block);
        if (r133 == 0) goto L52;
        r5 = r133;
    L52:
        SpannableStringBuilder r134 = new SpannableStringBuilder(r113);
        r134.append(r5);
        r114 = r134.length();
        r134.append(r123);
        RichTextCell.applyStyledTextToBlock(r14.block, r134);
    L53:
        if (r10 <= r9) goto L67;
        this.rows.remove(r10);
        r10 = r10 - 1;
        goto L53
    L35:
        r4 = RichTextCell.readStyledText(r23.block);
        goto L36
    L13:
        if (r9 != r10) goto L16;
        if (r11 <= r12) goto L16;
    L72:
        return false;
    }

    private void startAudioUpload(BlockRow r4, String r5, TLRPC.Document r6) {
        MediaUploadState r0 = r4.media;
        RichMediaUploader r1 = this.uploaders.remove(r0);
        if (r1 == null) goto L5;
        r1.cancel();
    L5:
        RichMediaUploader r42 = RichMediaUploader.forAudio(this.currentAccount, r5, r6, new AnonymousClass17(this, r0, r4));
        this.uploaders.put(r0, r42);
        r42.start();
    }

    private void startBlockDrag(View r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (this.itemTouchHelper != null) goto L6;
        return;
    L6:
        if (isReorderAllowed() == false) goto L29;
        RecyclerView.ViewHolder r0 = getChildViewHolder(r4);
        if (r0 == null) goto L30;
        int r1 = r0.getAdapterPosition();
        if (r1 >= 0) goto L14;
        return;
    L14:
        if (this.adapter.isReorderItem(r1) == false) goto L31;
        this.longPressConsumed = true;
        if (this.textSelectionHelper.isInSelectionMode() == false) goto L24;
        this.textSelectionHelper.clear();
    L24:
        r4.performHapticFeedback(0);     // Catch: Exception -> L23
    L21:
        this.itemTouchHelper.startDrag(r0);
        return;
    L31:
        return;
    L30:
        return;
    }

    private void startCellSelectionDrag(RichTableCell r2, TL_iv.pageTableCell r3) {
        dismissTableCellMenu();
        beginCellSelection(r2);
        stopScroll();
        requestDisallowInterceptTouchEvent(true);
        this.cellSelectionDragActive = true;
        this.cellSelectionDragAnchor = r3;
        this.cellSelectionDragEnd = r3;
        r2.selectCellRectangle(r3, r3);
    }

    private void startDocumentUpload(BlockRow r4, String r5, TLRPC.Document r6) {
        MediaUploadState r0 = r4.media;
        RichMediaUploader r1 = this.uploaders.remove(r0);
        if (r1 == null) goto L5;
        r1.cancel();
    L5:
        RichMediaUploader r42 = RichMediaUploader.forDocument(this.currentAccount, r5, r6, new AnonymousClass16(this, r0, r4, r5));
        this.uploaders.put(r0, r42);
        r42.start();
    }

    private void startMediaConvertAndUpload(BlockRow r4, MediaUploadState r5, MediaController.PhotoEntry r6) {
        RichMediaUploader r0 = this.uploaders.remove(r5);
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        RichMediaConverter r02 = this.converters.remove(r5);
        if (r02 == null) goto L8;
        r02.cancel();
    L8:
        r5.state = 1;
        r5.progress = 0.0f;
        RichMediaConverter r03 = new RichMediaConverter(this.currentAccount, r6, new AnonymousClass18(this, r5, r4));
        this.converters.put(r5, r03);
        r03.start();
    }

    private void startMediaUpload(BlockRow r10, MediaUploadState r11, String r12, boolean r13, int r14, int r15, int r16) {
        RichMediaUploader r0 = this.uploaders.remove(r11);
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        RichMediaUploader r1 = new RichMediaUploader(this.currentAccount, r12, r13, r14, r15, r16, new AnonymousClass19(this, r11, r10));
        this.uploaders.put(r11, r1);
        r1.start();
    }

    private CharSequence styledTextOf(BlockRow r2) {
        View r1 = findViewByItemObject(r2);
        if ((r1 instanceof RichTextCell) == false) goto L7;
        return ((RichTextCell) r1).getEditText().getText();
    L7:
        return RichTextCell.readStyledText(r2.block);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void switchGalleryMode(BlockRow r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (isGallery(r4.block) == false) goto L30;
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L9;
        r0.flush();
    L9:
        ArrayList<TL_iv.PageBlock> r02 = galleryItems(r4.block);
        TL_iv.PageBlock r1 = r4.block;
        TL_iv.PageCaption r2 = r1.caption;
        if ((r1 instanceof TL_iv.pageBlockSlideshow) == false) goto L16;
        TL_iv.pageBlockCollage r12 = new TL_iv.pageBlockCollage();
        if (r02 != null) goto L15;
        r02 = new ArrayList();
    L15:
        r12.items = r02;
        r12.caption = r2;
        TL_iv.pageBlockSlideshow r13 = r12;
    L21:
        r4.block = r13;
        RichEditorHistory r03 = this.history;
        if (r03 == null) goto L24;
        r03.record();
    L24:
        View r3 = findViewByItemObject(r4);
        if ((r3 instanceof RichMediaCell) == false) goto L29;
        ((RichMediaCell) r3).onModeChanged();
        return;
    L29:
        return;
    L16:
        TL_iv.pageBlockSlideshow r14 = new TL_iv.pageBlockSlideshow();
        if (r02 != null) goto L20;
        r02 = new ArrayList();
    L20:
        r14.items = r02;
        r14.caption = r2;
        r13 = r14;
        goto L21
    }

    private static boolean tableHasText(TL_iv.pageBlockTable r6) {
        TL_iv.RichText r0 = r6.title;
        if (r0 == null) goto L8;
        if (TextUtils.isEmpty(RichTextStyle.plainOf(r0)) == true) goto L8;
        return true;
    L8:
        if (r6.rows != null) goto L10;
        return false;
    L10:
        int r02 = 0;
    L12:
        if (r02 >= r6.rows.size()) goto L21;
        TL_iv.pageTableRow r3 = r6.rows.get(r02);
        int r4 = 0;
    L15:
        if (r4 >= r3.cells.size()) goto L20;
        if (TableModel.readPlainText(r3.cells.get(r4)).isEmpty() == false) goto L18;
        r4 = r4 + 1;
        goto L15
    L18:
        return true;
    L20:
        r02 = r02 + 1;
        goto L12
    L21:
        return false;
    }

    private View tableMenuAnchor(RichTableCell r9, boolean r10) {
        TableModel r8 = r9.getModel();
        if (r8 == null) goto L29;
        int r0 = Integer.MAX_VALUE;
        if (r10 == false) goto L8;
        int r1 = -1;
    L9:
        Iterator<TL_iv.pageTableCell> r2 = r9.getSelectedCells().iterator();
        TL_iv.pageTableCell r3 = null;
    L11:
        if (r2.hasNext() == false) goto L24;
        TL_iv.pageTableCell r4 = r2.next();
        int r5 = r8.anchorColOf(r4);
        int r6 = r8.anchorRowOf(r4);
        if (r10 == false) goto L15;
        r6 = (r6 + TableModel.spanRow(r4)) - 1;
    L15:
        if (r10 == true) goto L16;
        if (r6 < r1) goto L23;
        if (r6 != r1) goto L11;
        if (r5 >= r0) goto L11;
    L23:
        r3 = r4;
        r0 = r5;
        r1 = r6;
        goto L11
    L16:
        if (r6 > r1) goto L23;
        if (r6 != r1) goto L11;
        if (r5 >= r0) goto L11;
    L24:
        if (r3 == null) goto L29;
        RichTableCellHost r82 = r9.getGrid().hostForAnchor(r3);
        if (r82 == null) goto L29;
        return r82;
    L8:
        r1 = Integer.MAX_VALUE;
    L29:
        return r9;
    }

    private boolean tableSelectionHasFormattable() {
        int r0 = this.textSelectionHelper.getStartCell();
        int r1 = this.textSelectionHelper.getStartChildPosition();
        int r2 = this.textSelectionHelper.getEndChildPosition();
        int r3 = this.textSelectionHelper.getStartOffset();
        int r4 = this.textSelectionHelper.getEndOffset();
        int r5 = r1;
    L4:
        if (r5 > r2) goto L22;
        RichEditText r7 = tableEditText(r0, r5);
        if (r7 == null) goto L21;
        int r72 = r7.length();
        if (r5 != r1) goto L11;
        int r8 = r3;
    L12:
        if (r5 != r2) goto L14;
        int r9 = r4;
    L15:
        if (r1 != r2) goto L18;
        r8 = Math.min(r3, r4);
        r9 = Math.max(r3, r4);
    L18:
        if (Math.max(0, Math.min(r8, r72)) >= Math.max(0, Math.min(r9, r72))) goto L21;
        return true;
    L14:
        r9 = r72;
        goto L15
    L11:
        r8 = 0;
    L21:
        r5 = r5 + 1;
        goto L4
    L22:
        return false;
    }

    private void toggleDetails(BlockRow r3) {
        if (isDetailsHeader(r3) == false) goto L13;
        TL_iv.pageBlockDetails r32 = (TL_iv.pageBlockDetails) r3.block;
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L8;
        r0.flush();
    L8:
        r32.open = !r32.open;
        this.adapter.update(true);
        RichEditorHistory r2 = this.history;
        if (r2 == null) goto L12;
        r2.record();
        return;
    L12:
        return;
    }

    private void toggleMediaSpoiler(BlockRow r3, MediaUploadState r4) {
        if (r3 == null) goto L19;
        if (r4 == null) goto L20;
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L8;
        r0.flush();
    L8:
        r4.hasSpoiler = !r4.hasSpoiler;
        TL_iv.PageBlock r02 = itemBlockFor(r3, r4);
        if ((r02 instanceof TL_iv.pageBlockPhoto) == false) goto L12;
        ((TL_iv.pageBlockPhoto) r02).spoiler = r4.hasSpoiler;
    L14:
        refreshMediaCell(r3);
        RichEditorHistory r32 = this.history;
        if (r32 == null) goto L17;
        r32.record();
    L17:
        this.delegate.onContentChanged();
        return;
    L12:
        if ((r02 instanceof TL_iv.pageBlockVideo) == false) goto L14;
        ((TL_iv.pageBlockVideo) r02).spoiler = r4.hasSpoiler;
        goto L14
    L20:
        return;
    }

    private void transformRow(BlockRow r3, TL_iv.PageBlock r4, int r5, int r6, boolean r7, boolean r8) {
        int r0 = this.rows.indexOf(r3);
        if (r0 < 0) goto L48;
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L8;
        r1.flush();
    L8:
        r3.block = r4;
        r3.level = r5;
        r3.num = r6;
        r3.checkbox = r7;
        r3.checked = r8;
        renumberAllRuns();
        boolean r52 = r4 instanceof TL_iv.pageBlockTable;
        if (r52 == false) goto L16;
        int r02 = r0 + 1;
        if (r02 < this.rows.size()) goto L13;
    L14:
        this.rows.add(r02, new BlockRow(new TL_iv.pageBlockParagraph()));
    L28:
        BlockRow r62 = r3;
    L29:
        if (r62 != r3) goto L42;
        if (r52 == true) goto L42;
        if (isNonText(r4) == true) goto L42;
        if (hasFocusedEdit() == false) goto L42;
        applyInPlaceUpdateKeepingFocus();
        RichEditorHistory r42 = this.history;
        if (r42 == null) goto L38;
        r42.record();
    L38:
        View r2 = findViewByItemObject(r3);
        if ((r2 instanceof RichTextCell) == false) goto L47;
        RichTextCell r22 = (RichTextCell) r2;
        r22.requestEditFocus();
        r22.getEditText().setSelection(r22.getEditText().length());
        return;
    L47:
        return;
    L42:
        this.adapter.update(false);
        RichEditorHistory r32 = this.history;
        if (r32 == null) goto L45;
        r32.record();
    L45:
        post(new RichEditorListView$$ExternalSyntheticLambda58(this, r62));
        return;
    L13:
        if (isNonText(this.rows.get(r02).block) == false) goto L28;
    L16:
        if (isNonText(r4) == false) goto L28;
        if (isMedia(r4) == true) goto L20;
    L22:
        int r03 = r0 + 1;
        if (r03 < this.rows.size()) goto L25;
    L27:
        r62 = new BlockRow(new TL_iv.pageBlockParagraph());
        this.rows.add(r03, r62);
        goto L29
    L25:
        if (isNonText(this.rows.get(r03).block) == true) goto L27;
        r62 = this.rows.get(r03);
        goto L29
    L20:
        if (r3.media != null) goto L22;
        r3.media = new MediaUploadState();
        goto L22
    }

    private boolean tryEscalateSelectAll() {
        if (this.rows.isEmpty() == false) goto L6;
        return false;
    L6:
        if (this.textSelectionHelper.isInSelectionMode() == true) goto L11;
        if (selectCurrentUnit() == false) goto L13;
        return true;
    L13:
        int r0 = 0;
    L14:
        int r3 = this.itemRows.size();
        ArrayList<BlockRow> r4 = this.itemRows;
        if (r0 >= r3) goto L25;
        BlockRow r32 = r4.get(r0);
        long r7 = r32.authorQuoteId;
        TextSelectionHelper.ArticleTextSelectionHelper r5 = this.textSelectionHelper;
        if (r7 == 0) goto L19;
        r5.cacheText(r0, RichTextStyle.plainOf(this.quoteAuthors.get(Long.valueOf(r7))), null);
    L24:
        r0 = r0 + 1;
        goto L14
    L19:
        r5.cacheText(r0, RichTextCell.readPlainText(r32.block), null);
        if (RichTextCell.isQuoteBlock(r32.block) == false) goto L24;
        String r33 = RichTextCell.readAuthorPlain(r32.block);
        if (r33.isEmpty() == true) goto L24;
        this.textSelectionHelper.cacheChildText(r0, 1, r33);
        goto L24
    L25:
        int r02 = r4.size() - 1;
        BlockRow r34 = this.itemRows.get(r02);
        if (r34.authorQuoteId == 0) goto L28;
    L30:
        String r35 = _UrlKt.FRAGMENT_ENCODE_SET;
    L31:
        boolean r42 = r35.isEmpty();
        TextSelectionHelper.ArticleTextSelectionHelper r9 = this.textSelectionHelper;
        if (r42 == true) goto L34;
        r9.selectAllBlocksRange(0, r02, 1, r35.length());
    L35:
        return true;
    L34:
        r9.selectAllBlocksRange(0, r02);
        goto L35
    L28:
        if (RichTextCell.isQuoteBlock(r34.block) == false) goto L30;
        r35 = RichTextCell.readAuthorPlain(r34.block);
        goto L31
    L11:
        if (tryEscalateWithinTable() == false) goto L13;
        return true;
    }

    private boolean tryEscalateSelectionFromCaret(int r12) {
        BlockRow r0 = findFocusedRow();
        int r1 = 0;
        if (r0 != null) goto L5;
        return false;
    L5:
        int r2 = this.rows.indexOf(r0);
        if (r2 >= 0) goto L8;
        return false;
    L8:
        View r02 = findViewByItemObject(r0);
        if ((r02 instanceof RichTextCell) == true) goto L11;
        return false;
    L11:
        RichTextCell r03 = (RichTextCell) r02;
        RichEditText r3 = r03.getEditText();
        if (r3.getLayout() != null) goto L14;
        return false;
    L14:
        int r4 = r3.getSelectionEnd();
        int r5 = r3.length();
        switch(r12) {
            case 19: goto L20;
            case 20: goto L17;
            case 21: goto L20;
            case 22: goto L17;
            default: goto L16;
        };
    L16:
        return false;
    L17:
        if (r4 >= r5) goto L19;
        return false;
    L19:
        boolean r122 = true;
    L23:
        if (r122 == false) goto L25;
        int r22 = r2 + 1;
    L26:
        if (r22 >= 0) goto L28;
    L62:
        return false;
    L28:
        if (r22 >= this.rows.size()) goto L62;
        BlockRow r42 = this.rows.get(r22);
        int r7 = 0;
    L32:
        if (r7 >= this.rows.size()) goto L34;
        this.textSelectionHelper.cacheText(r7, RichTextCell.readPlainText(this.rows.get(r7).block), null);
        r7 = r7 + 1;
        goto L32
    L34:
        if (r5 != 0) goto L36;
        return false;
    L36:
        int r32 = Math.max(0, Math.min(r3.getSelectionStart(), r5));
        TextSelectionHelper.ArticleTextSelectionHelper r72 = this.textSelectionHelper;
        if (r122 == false) goto L48;
        if (r32 < r5) goto L42;
        r5 = r5 - 1;
    L42:
        if (r72.selectRangeOf(r03, r32, r5) == true) goto L44;
        return false;
    L44:
        KeyEvent.Callback r123 = findViewByItemObject(r42);
        if ((r123 instanceof TextSelectionHelper.ArticleSelectableView) == false) goto L47;
        this.textSelectionHelper.extendSelectionTo((TextSelectionHelper.ArticleSelectableView) r123, 0);
    L61:
        return true;
    L47:
        scrollToPosition(r22);
        post(new RichEditorListView$$ExternalSyntheticLambda53(this, r42));
        goto L61
    L48:
        if (r32 <= 0) goto L50;
        int r124 = 0;
    L52:
        if (r72.selectRangeOf(r03, r32, r124) == true) goto L54;
        return false;
    L54:
        KeyEvent.Callback r125 = findViewByItemObject(r42);
        if ((r125 instanceof TextSelectionHelper.ArticleSelectableView) == true) goto L57;
        scrollToPosition(r22);
        post(new RichEditorListView$$ExternalSyntheticLambda54(this, r42));
        goto L61
    L57:
        if ((r125 instanceof RichTextCell) == false) goto L59;
        r1 = ((RichTextCell) r125).getEditText().length();
    L59:
        this.textSelectionHelper.extendSelectionTo((TextSelectionHelper.ArticleSelectableView) r125, r1);
        goto L61
    L50:
        r124 = 1;
        goto L52
    L25:
        r22 = r2 - 1;
        goto L26
    L20:
        if (r4 <= 0) goto L22;
        return false;
    L22:
        r122 = false;
        goto L23
    }

    private boolean tryEscalateWithinTable() {
        int r0 = this.textSelectionHelper.getStartCell();
        if (r0 == this.textSelectionHelper.getEndCell()) goto L5;
        return false;
    L5:
        View r02 = selectableAt(r0);
        if ((r02 instanceof RichTableCell) == true) goto L8;
        return false;
    L8:
        RichTableCell r03 = (RichTableCell) r02;
        if (isWholeTableSelected(r03) == false) goto L12;
        return false;
    L12:
        return selectWholeTable(r03);
    }

    private boolean tryExtendSelectionAcrossCells(int r14, boolean r15) {
        int r0 = this.textSelectionHelper.getAnchorCell();
        int r1 = this.textSelectionHelper.getAnchorChildPosition();
        int r2 = this.textSelectionHelper.getAnchorOffset();
        int r3 = this.textSelectionHelper.getStartCell();
        int r4 = this.textSelectionHelper.getStartChildPosition();
        int r5 = this.textSelectionHelper.getStartOffset();
        int r6 = this.textSelectionHelper.getEndCell();
        int r7 = this.textSelectionHelper.getEndChildPosition();
        int r8 = this.textSelectionHelper.getEndOffset();
        if (r0 != r3) goto L7;
        if (r1 != r4) goto L7;
        if (r2 != r5) goto L7;
        r3 = r6;
        r4 = r7;
        r5 = r8;
    L7:
        View r02 = selectableAt(r3);
        int r12 = 0;
        if (r02 != null) goto L11;
        return false;
    L11:
        if ((r02 instanceof RichTableCell) == false) goto L96;
        RichTableCell r03 = (RichTableCell) r02;
        TableModel r22 = r03.getModel();
        if (r22 != null) goto L15;
    L94:
        return false;
    L15:
        if (r22.anchors().isEmpty() == true) goto L94;
        int r72 = r22.anchors().size() + 1;
        if (r4 < 0) goto L22;
        if (r4 >= r72) goto L22;
        int r82 = r4;
    L23:
        RichEditText r9 = r03.editTextForChildPos(r82);
        Layout r10 = null;
        if (r9 == null) goto L26;
        Layout r92 = r9.getLayout();
    L27:
        if (r92 != null) goto L29;
        return false;
    L29:
        CharSequence r11 = r92.getText();
        int r122 = r11.length();
        switch(r14) {
            case 19: goto L78;
            case 20: goto L60;
            case 21: goto L44;
            case 22: goto L32;
            default: goto L31;
        };
    L31:
        int r152 = r3;
        r12 = r5;
    L157:
        if (r152 != r3) goto L161;
        if (r4 != r82) goto L161;
        if (r12 != r5) goto L161;
        return true;
    L161:
        if (r152 == r3) goto L163;
        scrollToPosition(r152);
    L163:
        KeyEvent.Callback r142 = selectableAt(r152);
        if ((r142 instanceof TextSelectionHelper.ArticleSelectableView) == true) goto L168;
        post(new RichEditorListView$$ExternalSyntheticLambda52(this, r152, r4, r12));
        return true;
    L168:
        return this.textSelectionHelper.extendSelectionTo((TextSelectionHelper.ArticleSelectableView) r142, r4, r12);
    L32:
        if (r15 == false) goto L34;
        int r143 = wordRight(r11, r5);
    L35:
        if (r143 != r5) goto L43;
        if (r5 < r122) goto L43;
        int r153 = r82 + 1;
        if (r153 >= r72) goto L40;
    L39:
        r4 = r153;
    L72:
        r152 = r3;
        goto L157
    L40:
        r152 = r3 + 1;
        if (r152 >= this.rows.size()) goto L43;
    L76:
        r4 = 0;
    L43:
        r12 = r143;
        goto L72
    L34:
        r143 = Math.min(r122, r5 + 1);
        goto L35
    L44:
        if (r15 == false) goto L46;
        r143 = wordLeft(r11, r5);
    L47:
        if (r143 != r5) goto L43;
        if (r5 > 0) goto L43;
        r153 = r82 - 1;
        if (r153 < 0) goto L56;
        RichEditText r144 = r03.editTextForChildPos(r153);
        if (r144 == null) goto L54;
        r10 = r144.getLayout();
    L54:
        if (r10 == null) goto L39;
        r12 = r10.getText().length();
        goto L39
    L56:
        r152 = r3 - 1;
        if (r152 < 0) goto L43;
        int r145 = prevTextOffset(r152);
    L59:
        r4 = 0;
        r12 = r145;
        goto L157
    L46:
        r143 = Math.max(0, r5 - 1);
        goto L47
    L60:
        int r146 = r92.getLineForOffset(r5) + 1;
        if (r146 >= r92.getLineCount()) goto L63;
        r12 = r92.getOffsetForHorizontal(r146, r92.getPrimaryHorizontal(r5));
        goto L72
    L63:
        if (r82 != 0) goto L69;
        if (r72 <= 1) goto L66;
        r152 = r3;
        r4 = 1;
        goto L157
    L66:
        r152 = r3 + 1;
        if (r152 < this.rows.size()) goto L76;
    L77:
        r152 = r3;
        r12 = r122;
        goto L157
    L69:
        int r147 = findTableAnchorBelow(r22, r82 - 1);
        if (r147 < 0) goto L73;
        r4 = r147 + 1;
        goto L72
    L73:
        int r148 = r3 + 1;
        if (r148 >= this.rows.size()) goto L77;
        r152 = r148;
        goto L76
    L78:
        int r149 = r92.getLineForOffset(r5) - 1;
        if (r149 < 0) goto L81;
        r12 = r92.getOffsetForHorizontal(r149, r92.getPrimaryHorizontal(r5));
        goto L72
    L81:
        if (r82 != 0) goto L85;
        r152 = r3 - 1;
        if (r152 < 0) goto L72;
        r145 = prevTextOffset(r152);
        goto L59
    L85:
        int r1410 = findTableAnchorAbove(r22, r82 - 1);
        if (r1410 < 0) goto L93;
        int r1411 = r1410 + 1;
        RichEditText r154 = r03.editTextForChildPos(r1411);
        if (r154 == null) goto L90;
        r10 = r154.getLayout();
    L90:
        if (r10 == null) goto L92;
        r12 = r10.getText().length();
    L92:
        r4 = r1411;
        goto L72
    L93:
        r143 = r03.getTitleEditText().length();
        r4 = 0;
        goto L43
    L26:
        r92 = null;
    L22:
        r82 = 0;
        goto L23
    L96:
        if ((r02 instanceof RichTextCell) == false) goto L138;
        Layout r04 = ((RichTextCell) r02).getEditText().getLayout();
        if (r04 != null) goto L100;
        return false;
    L100:
        CharSequence r23 = r04.getText();
        int r73 = r23.length();
        switch(r14) {
            case 19: goto L130;
            case 20: goto L123;
            case 21: goto L113;
            case 22: goto L103;
            default: goto L102;
        };
    L102:
        r152 = r3;
        r12 = r5;
    L136:
        r82 = r4;
        goto L157
    L103:
        if (r15 == false) goto L105;
        int r1412 = wordRight(r23, r5);
    L106:
        if (r1412 != r5) goto L111;
        if (r5 < r73) goto L111;
        r152 = r3 + 1;
        if (r152 < this.rows.size()) goto L136;
    L111:
        r12 = r1412;
    L112:
        r152 = r3;
        goto L136
    L105:
        r1412 = Math.min(r73, r5 + 1);
        goto L106
    L113:
        if (r15 == false) goto L116;
        int r1413 = wordLeft(r23, r5);
    L115:
        r12 = r1413;
        if (r12 != r5) goto L112;
        if (r5 > 0) goto L112;
        int r1414 = r3 - 1;
        if (r1414 < 0) goto L112;
        r12 = prevTextOffset(r1414);
    L122:
        r152 = r1414;
        goto L136
    L116:
        r1413 = Math.max(0, r5 - 1);
        goto L115
    L123:
        int r1415 = r04.getLineForOffset(r5) + 1;
        if (r1415 >= r04.getLineCount()) goto L126;
        r12 = r04.getOffsetForHorizontal(r1415, r04.getPrimaryHorizontal(r5));
        goto L112
    L126:
        r1414 = r3 + 1;
        if (r1414 < this.rows.size()) goto L122;
        r152 = r3;
        r12 = r73;
        goto L136
    L130:
        int r1416 = r04.getLineForOffset(r5) - 1;
        if (r1416 < 0) goto L133;
        r12 = r04.getOffsetForHorizontal(r1416, r04.getPrimaryHorizontal(r5));
        goto L112
    L133:
        r1414 = r3 - 1;
        if (r1414 < 0) goto L112;
        r12 = prevTextOffset(r1414);
        goto L122
    L138:
        if ((r02 instanceof RichDividerCell) == false) goto L140;
    L149:
        switch(r14) {
            case 19: goto L154;
            case 20: goto L151;
            case 21: goto L154;
            case 22: goto L151;
            default: goto L102;
        };
    L151:
        r1414 = r3 + 1;
        if (r1414 >= this.rows.size()) goto L102;
    L154:
        r1414 = r3 - 1;
        if (r1414 < 0) goto L102;
        r12 = prevTextOffset(r1414);
        goto L122
    L140:
        if ((r02 instanceof RichMediaCell) == true) goto L149;
        if ((r02 instanceof RichAudioCell) == true) goto L149;
        if ((r02 instanceof RichMapCell) == true) goto L149;
        if ((r02 instanceof RichMathCell) == true) goto L149;
        return false;
    }

    private boolean tryPlainArrowAcrossCells(int r10) {
        if (r10 != 20) goto L5;
        boolean r102 = true;
    L6:
        RichTableCell r0 = focusedTitleTable();
        if (r0 == null) goto L25;
        int r4 = this.rows.indexOf(r0.getRow());
        if (r4 >= 0) goto L11;
        return false;
    L11:
        if (r102 == true) goto L13;
        int r103 = findNextNavigableRow(r4 - 1, -1);
        if (r103 >= 0) goto L23;
        return false;
    L23:
        post(new RichEditorListView$$ExternalSyntheticLambda44(this, this.rows.get(r103)));
        return true;
    L13:
        if (r0.focusFirstCell() == false) goto L15;
        return true;
    L15:
        int r104 = findNextNavigableRow(r4 + 1, 1);
        if (r104 >= 0) goto L18;
        BlockRow r105 = new BlockRow(new TL_iv.pageBlockParagraph());
        this.rows.add(r105);
        this.adapter.update(false);
        post(new RichEditorListView$$ExternalSyntheticLambda41(this, r105));
    L19:
        return true;
    L18:
        post(new RichEditorListView$$ExternalSyntheticLambda43(this, this.rows.get(r104)));
        goto L19
    L25:
        RichQuoteAuthorCell r02 = findFocusedAuthorCell();
        if (r02 == null) goto L53;
        int r42 = this.itemRows.indexOf(r02.getRow());
        if (r42 >= 0) goto L30;
        return false;
    L30:
        Layout r5 = r02.authorEditText.getLayout();
        if (r5 == null) goto L33;
        int r03 = r5.getLineForOffset(r02.authorEditText.getSelectionEnd());
    L34:
        if (r102 == false) goto L45;
        if (r5 != null) goto L37;
    L39:
        BlockRow r106 = nextNavItemRow(r42 + 1, 1);
        if (r106 != null) goto L43;
        BlockRow r107 = new BlockRow(new TL_iv.pageBlockParagraph());
        this.rows.add(r107);
        this.adapter.update(false);
        post(new RichEditorListView$$ExternalSyntheticLambda45(this, r107));
        return true;
    L43:
        post(new RichEditorListView$$ExternalSyntheticLambda46(this, r106));
        return true;
    L37:
        if (r03 >= (r5.getLineCount() - 1)) goto L39;
        return false;
    L45:
        if (r5 == null) goto L48;
        if (r03 <= 0) goto L48;
        return false;
    L48:
        BlockRow r108 = nextNavItemRow(r42 - 1, -1);
        if (r108 != null) goto L51;
        return false;
    L51:
        post(new RichEditorListView$$ExternalSyntheticLambda47(this, r108));
        return true;
    L33:
        r03 = 0;
        goto L34
    L53:
        BlockRow r04 = findFocusedNavRow();
        if (r04 != null) goto L56;
        return false;
    L56:
        int r43 = this.rows.indexOf(r04);
        if (r43 >= 0) goto L59;
        return false;
    L59:
        View r52 = findViewByItemObject(r04);
        if ((r52 instanceof RichTextCell) == false) goto L81;
        RichTextCell r6 = (RichTextCell) r52;
        if (r6.isAuthorVisible() == false) goto L81;
        if (r102 == true) goto L65;
    L72:
        if (r102 == true) goto L81;
        if (r6.isAuthorFocused() == false) goto L81;
        Layout r7 = r6.getAuthorEditText().getLayout();
        if (r7 != null) goto L78;
    L79:
        r6.focusBodyFromAuthor();
        return true;
    L78:
        if (r7.getLineForOffset(r6.getAuthorEditText().getSelectionEnd()) > 0) goto L81;
    L65:
        if (r6.isAuthorFocused() == true) goto L72;
        Layout r72 = r6.getEditText().getLayout();
        if (r72 != null) goto L69;
    L70:
        r6.focusAuthorFromBody();
        return true;
    L69:
        if (r72.getLineForOffset(r6.getEditText().getSelectionEnd()) >= (r72.getLineCount() - 1)) goto L70;
    L81:
        RichEditText r53 = focusedNavEditText(r52);
        if (r53 != null) goto L84;
        return false;
    L84:
        Layout r62 = r53.getLayout();
        if (r62 != null) goto L87;
        return false;
    L87:
        int r54 = r62.getLineForOffset(r53.getSelectionEnd());
        if (r102 == true) goto L90;
        if (r54 <= 0) goto L103;
        return false;
    L103:
        BlockRow r109 = adjacentAuthorRow(r04, -1);
        if (r109 == null) goto L107;
        post(new RichEditorListView$$ExternalSyntheticLambda51(this, r109));
        return true;
    L107:
        int r1010 = findNextNavigableRow(r43 - 1, -1);
        if (r1010 >= 0) goto L110;
        return false;
    L110:
        post(new RichEditorListView$$ExternalSyntheticLambda42(this, this.rows.get(r1010)));
        return true;
    L90:
        if (r54 >= (r62.getLineCount() - 1)) goto L92;
        return false;
    L92:
        BlockRow r1011 = adjacentAuthorRow(r04, 1);
        if (r1011 == null) goto L96;
        post(new RichEditorListView$$ExternalSyntheticLambda48(this, r1011));
        return true;
    L96:
        int r1012 = findNextNavigableRow(r43 + 1, 1);
        if (r1012 >= 0) goto L99;
        BlockRow r1013 = new BlockRow(new TL_iv.pageBlockParagraph());
        this.rows.add(r1013);
        this.adapter.update(false);
        post(new RichEditorListView$$ExternalSyntheticLambda49(this, r1013));
    L100:
        return true;
    L99:
        post(new RichEditorListView$$ExternalSyntheticLambda50(this, this.rows.get(r1012)));
        goto L100
    L5:
        r102 = false;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean tryStartTextSelection(View r4, float r5, float r6) {
        if (r4 != 0) goto L5;
    L39:
        return false;
    L5:
        if (this.textSelectionHelper.isInSelectionMode() == true) goto L39;
        int r52 = (int) ((r5 - r4.getLeft()) - getLeft());
        int r62 = (int) ((r6 - r4.getTop()) - getTop());
        if ((r4 instanceof RichTextCell) == false) goto L14;
        RichTextCell r42 = (RichTextCell) r4;
        if (r42.isPressOnText(r52, r62) == false) goto L39;
        this.textSelectionHelper.setMaybeView(r52, r62, r42);
        this.textSelectionHelper.trySelect(r42);
        return true;
    L14:
        if ((r4 instanceof RichTableCell) == false) goto L22;
        RichTableCell r43 = (RichTableCell) r4;
        if (r43.isPressOnText(r52, r62) == false) goto L18;
    L19:
        this.textSelectionHelper.setMaybeView(r52, r62, r43);
        this.textSelectionHelper.trySelect(r43);
        return true;
    L18:
        if (r43.isPressOnTitle(r52, r62) == false) goto L39;
    L22:
        if ((r4 instanceof RichCaptionHost) == false) goto L28;
        if (((RichCaptionHost) r4).isPressOnCaption(r52, r62) == false) goto L39;
        this.textSelectionHelper.setMaybeView(r52, r62, r4);
        this.textSelectionHelper.trySelect(r4);
        return true;
    L28:
        if ((r4 instanceof RichDetailsCell) == false) goto L34;
        RichDetailsCell r44 = (RichDetailsCell) r4;
        if (r44.isPressOnText(r52, r62) == false) goto L39;
        this.textSelectionHelper.setMaybeView(r52, r62, r44);
        this.textSelectionHelper.trySelect(r44);
        return true;
    L34:
        if ((r4 instanceof RichMathCell) == false) goto L39;
        if (((RichMathCell) r4).isPressOnMath(r52, r62) == false) goto L39;
        this.textSelectionHelper.setMaybeView(r52, r62, r4);
        this.textSelectionHelper.trySelect(r4);
        return true;
    }

    private BlockRow turnIntoTarget() {
        BlockRow r0 = findFocusedRow();
        if (r0 != null) goto L12;
        TextSelectionHelper.ArticleTextSelectionHelper r1 = this.textSelectionHelper;
        if (r1 == null) goto L12;
        if (r1.isInSelectionMode() == false) goto L12;
        int r12 = this.textSelectionHelper.getStartCell();
        if (r12 != this.textSelectionHelper.getEndCell()) goto L12;
        return rowForCell(r12);
    L12:
        return r0;
    }

    private void updateCellSelectionDrag(float r3, float r4) {
        RichTableCell r0 = this.activeCellSelectionTable;
        if (this.cellSelectionDragActive == false) goto L15;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.pressTarget != r0) goto L18;
        TL_iv.pageTableCell r32 = r0.findCellAt((int) ((r3 - r0.getLeft()) - getLeft()), (int) ((r4 - r0.getTop()) - getTop()));
        if (r32 != null) goto L11;
        return;
    L11:
        if (r32 == this.cellSelectionDragEnd) goto L19;
        this.cellSelectionDragEnd = r32;
        r0.selectCellRectangle(this.cellSelectionDragAnchor, r32);
        return;
    L19:
        return;
    L18:
        return;
    }

    private void updateLanguageOf(BlockRow r3, String r4) {
        if (r3 == null) goto L19;
        TL_iv.PageBlock r0 = r3.block;
        if ((r0 instanceof TL_iv.pageBlockPreformatted) == false) goto L21;
        TL_iv.pageBlockPreformatted r02 = (TL_iv.pageBlockPreformatted) r0;
        if (TextUtils.equals(r02.language, r4) == true) goto L22;
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L12;
        r1.flush();
    L12:
        r02.language = r4;
        RichTextCell r32 = cellAt(this.rows.indexOf(r3));
        if (r32 == null) goto L15;
        r32.updateLanguage();
    L15:
        RichEditorHistory r2 = this.history;
        if (r2 == null) goto L20;
        r2.record();
        return;
    L20:
        return;
    L22:
        return;
    L21:
        return;
    }

    private int[] wholeTableEnd(RichTableCell r2) {
        int r1 = r2.childCount() - 1;
    L3:
        if (r1 < 0) goto L9;
        int r0 = r2.childTextLength(r1);
        if (r0 > 0) goto L7;
        r1 = r1 - 1;
        goto L3
    L7:
        return new int[]{r1, r0};
    L9:
        return null;
    }

    private boolean willInjectAuthors(BlockRow r7, ArrayList<Long> r8, Map<Long, Integer> r9) {
        int r0 = r7.quoteIds.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r2 >= r8.size()) goto L9;
        if (r7.quoteIds.get(r2).equals(r8.get(r2)) == false) goto L9;
        r2 = r2 + 1;
    L9:
        int r02 = r0 - 1;
    L10:
        if (r02 < r2) goto L20;
        Long r3 = r7.quoteIds.get(r02);
        r3.longValue();
        Integer r4 = r9.get(r3);
        if (r4 == null) goto L19;
        if (r4.intValue() >= 2) goto L18;
        if (r02 > 0) goto L18;
        if (this.quoteAuthors.containsKey(r3) == false) goto L19;
    L18:
        return true;
    L19:
        r02 = r02 - 1;
        goto L10
    L20:
        return false;
    }

    private static int wordLeft(CharSequence r2, int r3) {
        BreakIterator r0 = BreakIterator.getWordInstance();
        r0.setText(r2.toString());
        int r22 = r0.preceding(Math.max(0, Math.min(r3, r2.length())));
        if (r22 != (-1)) goto L5;
        return 0;
    L5:
        return r22;
    }

    private static int wordRight(CharSequence r2, int r3) {
        BreakIterator r0 = BreakIterator.getWordInstance();
        r0.setText(r2.toString());
        int r32 = r0.following(Math.min(r3, r2.length()));
        if (r32 == (-1)) goto L5;
        return r32;
    L5:
        return r2.length();
    }

    private void writeSelectionToClipboard(CharSequence r3) {
        String r0 = buildSelectionHtml();
        ClipboardManager r2 = (ClipboardManager) getContext().getSystemService("clipboard");     // Catch: Exception -> L12
        if (r2 != null) goto L7;
        return;
    L7:
        if (r0 == null) goto L9;
        ClipData r32 = ClipData.newHtmlText("label", r3, r0);     // Catch: Exception -> L12
    L10:
        r2.setPrimaryClip(r32);     // Catch: Exception -> L12
        return;
    L9:
        r32 = ClipData.newPlainText("label", r3);     // Catch: Exception -> L12
    L12:
        e = move-exception;
        FileLog.e(e);
    }

    public void addBlock(TL_iv.PageBlock r8) {
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L5;
        r0.flush();
    L5:
        BlockRow r02 = findFocusedRow();
        if (r02 != null) goto L8;
        r02 = this.pendingInsertRow;
    L8:
        if (r02 == null) goto L10;
        int r1 = this.rows.indexOf(r02);
    L11:
        if (r1 < 0) goto L21;
        if ((r02.block instanceof TL_iv.pageBlockBlockquote) == false) goto L21;
        long r2 = RichContainer.newId();
        TL_iv.RichText r4 = ((TL_iv.pageBlockBlockquote) r02.block).caption;
        if (r4 != null) goto L17;
    L19:
        r02.quoteIds.add(Long.valueOf(r2));
        r02.block = new TL_iv.pageBlockParagraph();
        goto L21
    L17:
        if ((r4 instanceof TL_iv.textEmpty) == true) goto L19;
        this.quoteAuthors.put(Long.valueOf(r2), r4);
    L21:
        if (r1 >= 0) goto L23;
    L25:
        boolean r42 = false;
    L26:
        if (r1 < 0) goto L34;
        TL_iv.PageBlock r5 = r02.block;
        if ((r5 instanceof TL_iv.pageBlockParagraph) == false) goto L34;
        if (r02.media != null) goto L34;
        if (RichTextCell.readPlainText(r5).isEmpty() == false) goto L34;
        r02.block = r8;
    L42:
        this.pendingInsertRow = r02;
        if (hasCaption(r8) == false) goto L54;
        int r82 = this.rows.indexOf(r02) + 1;
        if (r82 < this.rows.size()) goto L47;
    L50:
        BlockRow r12 = new BlockRow(new TL_iv.pageBlockParagraph());
        if (r42 == false) goto L53;
        r12.quoteIds.addAll(r02.quoteIds);
    L53:
        this.rows.add(r82, r12);
        r02 = r12;
        goto L54
    L47:
        if (isNonText(this.rows.get(r82).block) == true) goto L50;
        r02 = this.rows.get(r82);
    L54:
        this.adapter.update(false);
        RichEditorHistory r83 = this.history;
        if (r83 == null) goto L57;
        r83.record();
    L57:
        post(new RichEditorListView$$ExternalSyntheticLambda40(this, r02));
        return;
    L34:
        BlockRow r52 = new BlockRow(r8);
        if (r42 == false) goto L37;
        r52.quoteIds.addAll(r02.quoteIds);
    L37:
        ArrayList<BlockRow> r03 = this.rows;
        if (r1 < 0) goto L40;
        r03.add(r1 + 1, r52);
    L41:
        r02 = r52;
        goto L42
    L40:
        r03.add(r52);
        goto L41
    L23:
        if (r02.quoteIds.isEmpty() == true) goto L25;
        r42 = true;
        goto L26
    L10:
        r1 = -1;
        goto L11
    }

    public void addMediaToRow(BlockRow r10, MediaController.PhotoEntry r11) {
        if (r10 == null) goto L46;
        if (r11 != null) goto L6;
        return;
    L6:
        if (isMedia(r10.block) == false) goto L48;
        String r0 = r11.imagePath;
        if (r0 == null) goto L11;
    L10:
        String r4 = r0;
        if (TextUtils.isEmpty(r4) == true) goto L49;
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L18;
        r02.flush();
    L18:
        MediaUploadState r3 = newUploadingMedia(r11, r4);
        boolean r03 = RichMediaConverter.hasAnimatedMediaEntities(r11);
        if (r11.isVideo == true) goto L23;
        if (r03 == true) goto L23;
        TL_iv.PageBlock r1 = new TL_iv.pageBlockPhoto();
    L25:
        if (isGallery(r10.block) == false) goto L30;
        galleryItems(r10.block).add(r1);
        if (r10.medias != null) goto L29;
        r10.medias = new ArrayList();
    L29:
        r10.medias.add(r3);
    L37:
        this.adapter.update(false);
        refreshMediaCell(r10);
        if (r03 == false) goto L40;
        startMediaConvertAndUpload(r10, r3, r11);
        RichEditorListView r12 = this;
    L41:
        RichEditorHistory r9 = r12.history;
        if (r9 == null) goto L44;
        r9.record();
    L44:
        r12.delegate.onContentChanged();
        return;
    L40:
        r12 = this;
        r12.startMediaUpload(r10, r3, r4, r11.isVideo, r11.width, r11.height, r11.duration);
        goto L41
    L30:
        MediaUploadState r2 = r10.media;
        if (r2 != null) goto L33;
    L36:
        r10.block = r1;
        r10.media = r3;
        goto L37
    L33:
        if (r2.state == 0) goto L36;
        TL_iv.pageBlockCollage r22 = new TL_iv.pageBlockCollage();
        r22.caption = r10.block.caption;
        RichCaptionController.ensureCaption(r22);
        ArrayList<TL_iv.PageBlock> r5 = new ArrayList();
        r22.items = r5;
        r5.add(r10.block);
        r22.items.add(r1);
        ArrayList<MediaUploadState> r13 = new ArrayList();
        r10.medias = r13;
        r13.add(r10.media);
        r10.medias.add(r3);
        r10.media = null;
        r10.block = r22;
    L23:
        r1 = new TL_iv.pageBlockVideo();
        goto L25
    L49:
        return;
    L11:
        r0 = r11.path;
        goto L10
    L48:
        return;
    }

    public void addRichMessage(TL_iv.RichMessage r5) {
        if (r5 == null) goto L45;
        ArrayList<TL_iv.PageBlock> r0 = r5.blocks;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isEmpty() == true) goto L48;
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L11;
        r02.flush();
    L11:
        TL_iv.RichMessage r03 = this.loadedRichMessage;
        if (r03 != null) goto L14;
        this.loadedRichMessage = r5;
    L20:
        ArrayList r04 = new ArrayList();
        flattenBlocks(r04, r5.blocks, this.quoteAuthors);
        if (r04.isEmpty() == true) goto L49;
        int r1 = 0;
    L25:
        if (r1 >= r04.size()) goto L27;
        resolveLoadedMedia((BlockRow) r04.get(r1));
        r1 = r1 + 1;
        goto L25
    L27:
        int r12 = this.rows.size();
        BlockRow r2 = findFocusedRow();
        if (r2 == null) goto L37;
        int r3 = this.rows.indexOf(r2);
        if (r3 < 0) goto L37;
        TL_iv.PageBlock r13 = r2.block;
        if ((r13 instanceof TL_iv.pageBlockParagraph) == true) goto L34;
    L36:
        r12 = r3 + 1;
        goto L37
    L34:
        if (RichTextCell.readPlainText(r13).isEmpty() == false) goto L36;
        this.rows.remove(r3);
        r12 = r3;
    L37:
        this.rows.addAll(r12, r04);
        normalizeNestedQuotes();
        renumberAllRuns();
        this.adapter.update(false);
        RichEditorHistory r52 = this.history;
        if (r52 == null) goto L40;
        r52.record();
    L40:
        Delegate r4 = this.delegate;
        if (r4 == null) goto L47;
        r4.onContentChanged();
        return;
    L47:
        return;
    L49:
        return;
    L14:
        ArrayList<TLRPC.Photo> r14 = r5.photos;
        if (r14 == null) goto L17;
        r03.photos.addAll(r14);
    L17:
        ArrayList<TLRPC.Document> r05 = r5.documents;
        if (r05 == null) goto L20;
        this.loadedRichMessage.documents.addAll(r05);
        goto L20
    L48:
        return;
    }

    public void applyInitialSelection(int r2, int r3) {
        if (applyInitialSelectionInternal(r2, r3) == true) goto L6;
        post(new RichEditorListView$$ExternalSyntheticLambda0(this, r2, r3));
        return;
    }

    public void applyQuote(BlockRow r8) {
        if (r8 != null) goto L4;
        r8 = turnIntoTarget();
    L4:
        if (r8 != null) goto L6;
        return;
    L6:
        if (this.rows.indexOf(r8) >= 0) goto L8;
        return;
    L8:
        if (isDetailsHeader(r8) == false) goto L10;
        return;
    L10:
        if (r8.detailsEnd == true) goto L49;
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L16;
        r0.flush();
    L16:
        if (r8.quoteIds.isEmpty() == true) goto L18;
    L20:
        boolean r02 = false;
    L21:
        if (r02 == false) goto L24;
        r8.block = newBlockquote();
    L32:
        renumberAllRuns();
        if (r02 == true) goto L35;
    L41:
        this.adapter.update(false);
        RichEditorHistory r03 = this.history;
        if (r03 == null) goto L44;
        r03.record();
    L44:
        post(new RichEditorListView$$ExternalSyntheticLambda94(this, r8));
        return;
    L35:
        if (hasFocusedEdit() == false) goto L41;
        applyInPlaceUpdateKeepingFocus();
        RichEditorHistory r04 = this.history;
        if (r04 == null) goto L39;
        r04.record();
    L39:
        focusRow(r8);
        return;
    L24:
        if (RichTextCell.isQuoteBlock(r8.block) == false) goto L31;
        long r2 = RichContainer.newId();
        TL_iv.RichText r4 = RichTextCell.extractCaption(r8.block);
        if (r4 != null) goto L28;
    L30:
        r8.quoteIds.add(Long.valueOf(r2));
        goto L31
    L28:
        if ((r4 instanceof TL_iv.textEmpty) == true) goto L30;
        this.quoteAuthors.put(Long.valueOf(r2), r4);
    L31:
        r8.block = new TL_iv.pageBlockParagraph();
        r8.quoteIds.add(Long.valueOf(RichContainer.newId()));
        goto L32
    L18:
        if (RichTextCell.isQuoteBlock(r8.block) == true) goto L20;
        r02 = true;
        goto L21
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0 */
    /* JADX WARN: Type inference failed for: r19v1, types: [int] */
    /* JADX WARN: Type inference failed for: r19v6 */
    /* JADX WARN: Type inference failed for: r19v7 */
    public void assignContainers() {
        RichEditorListView r0 = this;
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        boolean r6 = false;
        int r7 = 0;
    L4:
        if (r7 >= r0.rows.size()) goto L78;
        BlockRow r8 = r0.rows.get(r7);
        r8.path.clear();
        r8.itemStart = r6;
        if (r8.detailsEnd == true) goto L8;
        int r9 = r6 ? 1 : 0;
    L13:
        if (r9 >= r8.quoteIds.size()) goto L15;
        r8.path.add(RichContainer.quote(r8.quoteIds.get(r9).longValue()));
        r9 = r9 + 1;
        goto L13
    L15:
        int r92 = r6 ? 1 : 0;
    L17:
        if (r92 >= r1.size()) goto L19;
        r8.path.add((RichContainer) r1.get(r92));
        r92 = r92 + 1;
        goto L17
    L19:
        int r93 = Math.max(r6 ? 1 : 0, r8.level);
        if (r93 != 0) goto L24;
        r2.clear();
        r3.clear();
        r4.clear();
        r5.clear();
    L22:
        boolean r19 = r6;
        if (isDetailsHeader(r8) == false) goto L75;
        r1.add(RichContainer.details(RichContainer.newId(), ((TL_iv.pageBlockDetails) r8.block).open));
        r2.clear();
        r3.clear();
        r4.clear();
        r5.clear();
    L75:
        if (RichTextCell.isQuoteBlock(r8.block) == false) goto L77;
        r8.path.add(RichContainer.quote(RichContainer.newId()));
    L77:
        r7 = r7 + 1;
        r0 = this;
        r6 = r19;
        goto L4
    L24:
        if (r8.num <= 0) goto L26;
        boolean r11 = true;
    L28:
        if (r2.size() <= r93) goto L30;
        r2.remove(r2.size() - 1);
        r3.remove(r3.size() - 1);
        r4.remove(r4.size() - 1);
        r5.remove(r5.size() - 1);
        goto L28
    L30:
        int r12 = 1;
    L31:
        if (r12 > r93) goto L22;
        int r13 = r12 - 1;
        if (r12 != r93) goto L35;
        boolean r14 = true;
    L37:
        if (r13 >= r2.size()) goto L39;
        char r15 = 1;
    L40:
        if (r15 == 0) goto L45;
        if (r14 == false) goto L45;
        Boolean r16 = (Boolean) r3.get(r13);
        char r192 = r6 ? 1 : 0;
        char r152 = r15;
        ?? r193 = r192;
        if (r16.booleanValue() == r11) goto L47;
        r152 = r192;
        r193 = r192;
    L47:
        if (r152 == 0) goto L49;
    L56:
        long r20 = ((Long) r2.get(r13)).longValue();
        if (r14 == true) goto L59;
        boolean r162 = r11;
        int r02 = r12;
        r8.path.add(RichContainer.list(r20, 0, ((Boolean) r3.get(r13)).booleanValue(), false, false));
    L70:
        r12 = r02 + 1;
        r11 = r162;
        r6 = r193;
        goto L31
    L59:
        if (((Long) r5.get(r13)).longValue() != 0) goto L61;
    L64:
        char r62 = 1;
    L65:
        if (r62 == 0) goto L67;
        int r63 = ((Integer) r4.get(r13)).intValue() + 1;
        r4.set(r13, Integer.valueOf(r63));
        long r142 = r8.id;
        r5.set(r13, Long.valueOf(r142));
        r8.itemStart = true;
    L68:
        r162 = r11;
        r02 = r12;
        RichContainer r112 = RichContainer.list(r20, r142, r162, r8.checkbox, r8.checked);
        r112.itemNum = r63;
        r8.path.add(r112);
        goto L70
    L67:
        r63 = ((Integer) r4.get(r13)).intValue();
        r142 = ((Long) r5.get(r13)).longValue();
        goto L68
    L61:
        if (isNonText(r8.block) == false) goto L64;
        r62 = r193;
    L49:
        if (r2.size() <= r13) goto L51;
        r2.remove(r2.size() - 1);
        r3.remove(r3.size() - 1);
        r4.remove(r4.size() - 1);
        r5.remove(r5.size() - 1);
        goto L49
    L51:
        r2.add(Long.valueOf(RichContainer.newId()));
        if (r14 == false) goto L54;
        boolean r64 = r11;
    L55:
        r3.add(Boolean.valueOf(r64));
        r4.add(Integer.valueOf(r193));
        r5.add(0L);
        goto L56
    L54:
        r64 = r193;
    L45:
        r193 = r6 ? 1 : 0;
        r152 = r15;
        goto L47
    L39:
        r15 = r6 ? 1 : 0;
        goto L40
    L35:
        r14 = r6 ? 1 : 0;
        goto L37
    L26:
        r11 = r6 ? 1 : 0;
        goto L28
    L8:
        if (r1.isEmpty() == true) goto L10;
        r1.remove(r1.size() - 1);
    L10:
        r2.clear();
        r3.clear();
        r4.clear();
        r5.clear();
        r19 = r6 ? 1 : 0;
        goto L77
    }

    public void attachAudio(MessageObject r12) {
        if (r12 == null) goto L37;
        TLRPC.Document r0 = r12.getDocument();
        if (r0 == null) goto L38;
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L10;
        r1.flush();
    L10:
        TL_iv.pageBlockAudio r13 = new TL_iv.pageBlockAudio();
        BlockRow r2 = new BlockRow(r13);
        MediaUploadState r3 = new MediaUploadState();
        r2.media = r3;
        r3.isAudio = true;
        long r5 = r0.id;
        if (r5 != 0) goto L13;
    L18:
        boolean r7 = true;
    L19:
        if (r7 == false) goto L28;
        TLRPC.Message r122 = r12.messageOwner;
        if (r122 == null) goto L23;
        String r123 = r122.attachPath;
    L25:
        if (TextUtils.isEmpty(r123) == false) goto L27;
        return;
    L27:
        MediaUploadState r14 = r2.media;
        r14.audioDisplayDocument = r0;
        r14.localPath = r123;
        r14.state = 1;
        r14.progress = 0.0f;
    L29:
        insertPreparedRow(r2);
        if (r7 == false) goto L32;
        startAudioUpload(r2, r2.media.localPath, r0);
    L32:
        RichEditorHistory r124 = this.history;
        if (r124 == null) goto L35;
        r124.record();
    L35:
        this.delegate.onContentChanged();
        return;
    L23:
        r123 = null;
        goto L25
    L28:
        r3.document = r0;
        r3.audioDisplayDocument = r0;
        r3.state = 2;
        r13.audio_id = r5;
        goto L29
    L13:
        if (r0.dc_id == 0) goto L18;
        if (r0.access_hash == 0) goto L18;
        r7 = false;
        goto L19
    L38:
        return;
    }

    public void attachDocument(Uri r3) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (getContext() != null) goto L7;
        return;
    L7:
        Utilities.globalQueue.postRunnable(new RichEditorListView$$ExternalSyntheticLambda28(this, r3));
    }

    public void attachDocument(String r5) {
        if (TextUtils.isEmpty(r5) == true) goto L19;
        File r0 = new File(r5);
        if (r0.exists() == true) goto L8;
        return;
    L8:
        TLRPC.TL_document r1 = new TLRPC.TL_document();
        r1.id = 0;
        r1.dc_id = 0;
        r1.size = r0.length();
        String r02 = r0.getName();
        int r2 = r02.lastIndexOf(46);
        if (r2 < 0) goto L13;
        int r22 = r2 + 1;
        if (r22 >= r02.length()) goto L13;
        String r23 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(r02.substring(r22).toLowerCase());
    L15:
        if (TextUtils.isEmpty(r23) == false) goto L17;
        r23 = "application/octet-stream";
    L17:
        r1.mime_type = r23;
        TLRPC.TL_documentAttributeFilename r24 = new TLRPC.TL_documentAttributeFilename();
        r24.file_name = r02;
        r1.attributes.add(r24);
        attachDocument(r1, r5);
        return;
    L13:
        r23 = null;
        goto L15
    }

    public void attachDocument(MessageObject r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.getDocument() == null) goto L13;
        TLRPC.Document r0 = r2.getDocument();
        TLRPC.Message r22 = r2.messageOwner;
        if (r22 == null) goto L9;
        String r23 = r22.attachPath;
    L10:
        attachDocument(r0, r23);
        return;
    L9:
        r23 = null;
        goto L10
    }

    public void attachExternalMedia(Uri r9) {
        if (r9 == null) goto L20;
        BlockRow r6 = this.pendingMediaRow;
        String r1 = null;
        this.pendingMediaRow = null;
        Context r0 = getContext();
        if (r0 != null) goto L18;
        return;
    L18:
        r1 = r0.getContentResolver().getType(r9);     // Catch: Exception -> L9
    L8:
        String r4 = r1;
        if (r4 == null) goto L15;
        boolean r02 = r4.startsWith(MediaStreamTrack.VIDEO_TRACK_KIND);
    L14:
        boolean r3 = r02;
        int r5 = this.lastExternalImageId;
        this.lastExternalImageId = r5 - 1;
        Utilities.globalQueue.postRunnable(new RichEditorListView$$ExternalSyntheticLambda33(this, r9, r3, r4, r5, r6));
        return;
    L15:
        r02 = r9.toString().contains(MediaStreamTrack.VIDEO_TRACK_KIND);
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public void attachMedia(MediaController.PhotoEntry r10) {
        if (r10 == null) goto L29;
        String r0 = r10.imagePath;
        if (r0 == null) goto L7;
    L6:
        String r4 = r0;
        if (TextUtils.isEmpty(r4) == false) goto L11;
        return;
    L11:
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L14;
        r02.flush();
    L14:
        boolean r03 = RichMediaConverter.hasAnimatedMediaEntities(r10);
        if (r10.isVideo == true) goto L19;
        if (r03 == true) goto L19;
        TL_iv.PageBlock r1 = new TL_iv.pageBlockPhoto();
    L20:
        BlockRow r2 = new BlockRow(r1);
        r2.media = newUploadingMedia(r10, r4);
        insertPreparedRow(r2);
        MediaUploadState r3 = r2.media;
        if (r03 == false) goto L23;
        startMediaConvertAndUpload(r2, r3, r10);
        RichEditorListView r12 = this;
    L24:
        RichEditorHistory r9 = r12.history;
        if (r9 == null) goto L27;
        r9.record();
    L27:
        r12.delegate.onContentChanged();
        return;
    L23:
        r12 = this;
        r12.startMediaUpload(r2, r3, r4, r10.isVideo, r10.width, r10.height, r10.duration);
    L19:
        r1 = new TL_iv.pageBlockVideo();
        goto L20
    L7:
        r0 = r10.path;
        goto L6
    }

    public SelectionEdit beginSelectionEdit() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
    L28:
        return null;
    L5:
        if (r0.isInSelectionMode() == false) goto L28;
        if (isQuoteAuthorSelection() == false) goto L10;
        return null;
    L10:
        int r02 = this.rows.indexOf(rowForCell(this.textSelectionHelper.getStartCell()));
        int r2 = this.rows.indexOf(rowForCell(this.textSelectionHelper.getEndCell()));
        if (r02 < 0) goto L28;
        if (r2 < 0) goto L28;
        if (r02 <= r2) goto L16;
        int r6 = r02;
        int r5 = r2;
    L17:
        int r03 = r5;
    L18:
        if (r03 > r6) goto L27;
        BlockRow r22 = this.rows.get(r03);
        if (r22.detailsEnd == true) goto L25;
        if (isDetailsHeader(r22) == true) goto L25;
        r03 = r03 + 1;
    L25:
        return null;
    L27:
        return new AnonymousClass4(this, r5, r6, this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset());
    L16:
        r5 = r02;
        r6 = r2;
        goto L17
    }

    public TL_iv.RichMessage buildDraftRichMessage() {
        if (hasAnyText() == true) goto L5;
        return null;
    L5:
        ArrayList<TL_iv.PageBlock> r0 = flattenRowsToBlocks();
        if (r0.isEmpty() == false) goto L8;
        return null;
    L8:
        TL_iv.RichMessage r1 = new TL_iv.RichMessage();
        r1.blocks = r0;
        r1.photos = collectPhotos();
        r1.documents = collectDocuments();
        return r1;
    }

    public boolean canCreateInlineButtonOnSelection() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
    L37:
        return false;
    L5:
        if (r0.isInSelectionMode() == false) goto L37;
        if (this.textSelectionHelper.getStartCell() == this.textSelectionHelper.getEndCell()) goto L11;
        return false;
    L11:
        if (isTableSelection() == false) goto L17;
        int r02 = this.textSelectionHelper.getStartCell();
        int r2 = this.textSelectionHelper.getStartChildPosition();
        if (r2 == this.textSelectionHelper.getEndChildPosition()) goto L15;
        return false;
    L15:
        RichEditText r03 = tableEditText(r02, r2);
    L29:
        if (r03 != null) goto L31;
        return false;
    L31:
        int r22 = r03.length();
        int r3 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r22));
        int r5 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r22));
        if (r3 >= r5) goto L37;
        if (hasInlineButton(r03.getText(), r3, r5) == true) goto L37;
        return true;
    L17:
        if (isDetailsSelection() == false) goto L20;
        r03 = detailsEditText(this.textSelectionHelper.getStartCell());
        goto L29
    L20:
        if (isCaptionSelection() == false) goto L23;
        r03 = captionEditText(this.textSelectionHelper.getStartCell());
        goto L29
    L23:
        if (isQuoteAuthorSelection() == false) goto L25;
        r03 = quoteAuthorEditText(this.textSelectionHelper.getStartCell());
        goto L29
    L25:
        RichTextCell r04 = singleSelectionCell();
        if (r04 != null) goto L28;
        return false;
    L28:
        r03 = r04.getEditText();
        goto L29
    }

    public boolean canIndentRow(int r5) {
        if (r5 >= 0) goto L5;
    L21:
        return false;
    L5:
        if (r5 >= this.rows.size()) goto L21;
        BlockRow r1 = this.rows.get(r5);
        if (r1.level == 0) goto L10;
        if (r5 <= 0) goto L21;
        if (this.rows.get(r5 - 1).level < r1.level) goto L21;
    L18:
        if (indentKeepsDepth(r5) == false) goto L21;
        return true;
    L10:
        if (isListableText(r1.block) == true) goto L18;
        if (canNonTextJoinList(r5) == false) goto L21;
        goto L21
    }

    public boolean canIndentSelection() {
        int[] r0 = selectionRowRange();
        if (r0 == null) goto L5;
        int r2 = r0[0];
    L8:
        if (r2 > r0[1]) goto L13;
        if (canIndentTarget(this.rows.get(r2)) == true) goto L11;
        r2 = r2 + 1;
        goto L8
    L11:
        return true;
    L13:
        return false;
    L5:
        return canIndentTarget(currentBlockRow());
    }

    public boolean canIndentTarget(BlockRow r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        int r32 = this.rows.indexOf(r3);
        if (r32 >= 0) goto L8;
    L11:
        return false;
    L8:
        if (canIndentRow(r32) == false) goto L11;
        return true;
    }

    public boolean canOutdentRow(int r3) {
        if (r3 >= 0) goto L5;
    L11:
        return false;
    L5:
        if (r3 >= this.rows.size()) goto L11;
        if (this.rows.get(r3).level <= 0) goto L11;
        return true;
    }

    public boolean canOutdentSelection() {
        int[] r0 = selectionRowRange();
        if (r0 == null) goto L5;
        int r2 = r0[0];
    L8:
        if (r2 > r0[1]) goto L13;
        if (canOutdentTarget(this.rows.get(r2)) == true) goto L11;
        r2 = r2 + 1;
        goto L8
    L11:
        return true;
    L13:
        return false;
    L5:
        return canOutdentTarget(currentBlockRow());
    }

    public boolean canOutdentTarget(BlockRow r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        int r1 = this.rows.indexOf(r3);
        if (r1 >= 0) goto L8;
    L13:
        return false;
    L8:
        if (r3.level <= 0) goto L13;
        if (canOutdentRow(r1) == false) goto L13;
        return true;
    }

    public boolean canRedo() {
        RichEditorHistory r0 = this.history;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.canRedo() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean canUndo() {
        RichEditorHistory r0 = this.history;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.canUndo() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public RichEditText captionEditText(int r1) {
        KeyEvent.Callback r0 = selectableAt(r1);
        if ((r0 instanceof RichCaptionHost) == true) goto L5;
        return null;
    L5:
        return ((RichCaptionHost) r0).getCaptionEditText();
    }

    public void clearContent() {
        destroy();
        this.loadedRichMessage = null;
        this.pendingMediaRow = null;
        this.pendingInsertRow = null;
        this.history = new RichEditorHistory(this.historyDelegate);
        seedEmptyArticle();
        this.history.resetBaseline();
        Delegate r2 = this.delegate;
        if (r2 == null) goto L6;
        r2.onHistoryChanged();
        return;
    }

    public ArrayList<TLRPC.Document> collectDocuments() {
        ArrayList<TLRPC.Document> r0 = new ArrayList();
        HashSet r1 = new HashSet();
        int r2 = 0;
    L4:
        if (r2 >= this.rows.size()) goto L29;
        BlockRow r3 = this.rows.get(r2);
        if ((r3.block instanceof TL_iv.pageBlockDocument) == false) goto L14;
        MediaUploadState r4 = r3.media;
        if (r4 == null) goto L14;
        if (r4.isReady() == false) goto L14;
        TLRPC.Document r42 = r3.media.document;
        if (r42 == null) goto L14;
        TL_iv.pageBlockDocument r5 = (TL_iv.pageBlockDocument) r3.block;
        r5.document_id = r42.id;
    L14:
        Iterator<MediaUploadState> r32 = mediasOf(r3).iterator();
    L16:
        if (r32.hasNext() == false) goto L28;
        MediaUploadState r43 = r32.next();
        if (r43.isReady() == false) goto L16;
        TLRPC.Document r52 = r43.document;
        if (r52 == null) goto L16;
        long r6 = r52.id;
        if (r6 == 0) goto L16;
        if (r52.access_hash == 0) goto L16;
        if (r1.add(Long.valueOf(r6)) == false) goto L16;
        r0.add(r43.document);
        goto L16
    L28:
        r2 = r2 + 1;
        goto L4
    L29:
        return r0;
    }

    public ArrayList<TLRPC.Photo> collectPhotos() {
        ArrayList<TLRPC.Photo> r0 = new ArrayList();
        HashSet r1 = new HashSet();
        int r2 = 0;
    L4:
        if (r2 >= this.rows.size()) goto L16;
        Iterator<MediaUploadState> r3 = mediasOf(this.rows.get(r2)).iterator();
    L7:
        if (r3.hasNext() == false) goto L15;
        MediaUploadState r4 = r3.next();
        if (r4.isReady() == false) goto L7;
        TLRPC.Photo r5 = r4.photo;
        if (r5 == null) goto L7;
        if (r1.add(Long.valueOf(r5.id)) == false) goto L7;
        r0.add(r4.photo);
        goto L7
    L15:
        r2 = r2 + 1;
        goto L4
    L16:
        return r0;
    }

    public void convertToSimple() {
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L5;
        r0.record();
    L5:
        CharSequence r02 = RichMessageConvert.rowsToSimpleMessage(this.rows);
        destroy();
        this.rows.clear();
        this.quoteAuthors.clear();
        this.loadedRichMessage = null;
        flattenBlocks(this.rows, RichMessageConvert.blocksFromCharSequence(r02), this.quoteAuthors);
        this.adapter.update(false);
        RichEditorHistory r03 = this.history;
        if (r03 == null) goto L8;
        r03.record();
    L8:
        Delegate r04 = this.delegate;
        if (r04 == null) goto L12;
        r04.onContentChanged();
        this.delegate.onHistoryChanged();
        return;
    }

    public BlockRow currentBlockRow() {
        BlockRow r0 = findFocusedRow();
        if (r0 == null) goto L5;
        return r0;
    L5:
        TextSelectionHelper.ArticleTextSelectionHelper r02 = this.textSelectionHelper;
        if (r02 == null) goto L12;
        int r03 = r02.getStartCell();
        if (r03 < 0) goto L14;
        BlockRow r1 = rowForCell(r03);
        if (r1 == null) goto L15;
        return r1;
    L15:
        return null;
    L14:
        return null;
    L12:
        return null;
    }

    public boolean deselectIfAny() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 == null) goto L9;
        if (r0.isInSelectionMode() == false) goto L9;
        this.textSelectionHelper.clear();
        return true;
    L9:
        if (this.activeCellSelectionTable == null) goto L12;
        exitCellSelectionMode();
        return true;
    L12:
        return false;
    }

    public void destroy() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 == null) goto L5;
        r0.clear(true);
    L5:
        exitCellSelectionMode();
        hideEditTextActionModes();
        Iterator<RichMediaUploader> r02 = this.uploaders.values().iterator();
    L7:
        if (r02.hasNext() == false) goto L9;
        r02.next().cancel();
        goto L7
    L9:
        this.uploaders.clear();
        Iterator<RichMediaConverter> r03 = this.converters.values().iterator();
    L11:
        if (r03.hasNext() == false) goto L13;
        r03.next().cancel();
        goto L11
    L13:
        this.converters.clear();
    }

    @Override // org.telegram.ui.Components.UniversalRecyclerView, org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        drawQuoteBars(r1);
        super.dispatchDraw(r1);
    }

    public void enterCellSelectionMode(RichTableCell r1, TL_iv.pageTableCell r2) {
        beginCellSelection(r1);
        r1.addCellToSelection(r2);
    }

    public int findExceededLimit() {
        AppGlobalConfig r0 = MessagesController.getInstance(this.currentAccount).config;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.rows.size()) goto L7;
        r2 = r2 + mediasOf(this.rows.get(r1)).size();
        r1 = r1 + 1;
        goto L4
    L7:
        return RichEditorLimits.measure(flattenRowsToBlocks(), r2).findExceeded(r0);
    }

    public RichEditText findFocusedEditText() {
        View r0 = findFocus();
        if ((r0 instanceof RichEditText) == true) goto L5;
        int r02 = 0;
    L8:
        if (r02 >= getChildCount()) goto L14;
        View r1 = getChildAt(r02);
        if ((r1 instanceof RichTextCell) == true) goto L12;
        r02 = r02 + 1;
        goto L8
    L12:
        return ((RichTextCell) r1).getEditText();
    L14:
        return null;
    L5:
        return (RichEditText) r0;
    }

    public BlockRow findFocusedRow() {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L16;
        View r1 = getChildAt(r0);
        if ((r1 instanceof RichTextCell) == false) goto L15;
        RichTextCell r12 = (RichTextCell) r1;
        if (r12.getEditText().isFocused() == true) goto L14;
        if (r12.isAuthorVisible() == false) goto L15;
        if (r12.isAuthorFocused() == false) goto L15;
    L14:
        return r12.getRow();
    L15:
        r0 = r0 + 1;
        goto L4
    L16:
        return null;
    }

    public RichTableCell findFocusedTableCell() {
        View r0 = findFocus();
        if ((r0 instanceof RichEditText) == true) goto L5;
        return null;
    L5:
        return findTableCellAncestor(r0);
    }

    public ArrayList<TL_iv.PageBlock> flattenRowsToBlocks() {
        return flattenRange(0, this.rows.size(), false);
    }

    public void focusForDraft() {
        if (focusForDraftInternal() == true) goto L6;
        post(new RichEditorListView$$ExternalSyntheticLambda4(this));
        return;
    }

    public TL_iv.pageTableCell focusedCellOf(RichTableCell r3) {
        View r2 = findFocus();
        if ((r2 instanceof RichEditText) == true) goto L5;
        return null;
    L5:
        RichTableCellHost r22 = r3.findHostContaining(r2);
        if (r22 != null) goto L8;
        return null;
    L8:
        return r22.cell;
    }

    public RichEditText getFocusedEditTextOrNull() {
        View r1 = findFocus();
        if ((r1 instanceof RichEditText) == true) goto L5;
        return null;
    L5:
        return (RichEditText) r1;
    }

    public RichMapCell.Delegate getMapDelegate() {
        return this.mapDelegate;
    }

    public View getOverlayView() {
        return this.textSelectionOverlay;
    }

    public TextSelectionHelper.ArticleTextSelectionHelper getTextSelectionHelper() {
        return this.textSelectionHelper;
    }

    public boolean handleKeyEvent(KeyEvent r11) {
        boolean r1 = false;
        if (r11.getAction() == 0) goto L5;
        return false;
    L5:
        int r0 = r11.getKeyCode();
        boolean r2 = r11.isShiftPressed();
        boolean r3 = r11.isCtrlPressed();
        boolean r4 = r11.isAltPressed();
        boolean r5 = this.textSelectionHelper.isInSelectionMode();
        if (r3 == false) goto L22;
        if (r4 == true) goto L22;
        RichEditorHistory r7 = this.history;
        if (r7 == null) goto L22;
        if (r0 != 54) goto L15;
        if (r2 == true) goto L15;
        r7.undo();
        return true;
    L15:
        if (r0 != 54) goto L18;
        if (r2 == false) goto L18;
    L19:
        r7.redo();
        return true;
    L18:
        if (r0 == 53) goto L19;
    L22:
        if (r3 == false) goto L59;
        if (r4 == true) goto L59;
        int r8 = 4;
        if (r2 == true) goto L42;
        if (r0 != 30) goto L29;
        boolean r9 = false;
        r8 = 1;
    L52:
        if (r9 == false) goto L55;
        onLinkClicked();
        return true;
    L55:
        if (r8 == 0) goto L59;
        onFormattingClicked(r8);
        return true;
    L29:
        if (r0 != 33) goto L31;
    L50:
        r9 = false;
        goto L52
    L31:
        if (r0 != 37) goto L33;
        r8 = 2;
        goto L50
    L33:
        if (r0 != 39) goto L35;
        r8 = 0;
        r9 = true;
        goto L52
    L35:
        if (r0 != 49) goto L48;
        r8 = 16;
    L48:
        r8 = 0;
        r9 = false;
        goto L52
    L42:
        if (r0 == 41) goto L50;
        if (r0 != 44) goto L46;
        r8 = 256;
        goto L50
    L46:
        if (r0 == 47) goto L49;
        if (r0 != 52) goto L48;
    L49:
        r8 = 8;
    L59:
        if (r0 != 111) goto L63;
        if (r5 == false) goto L63;
        this.textSelectionHelper.clear();
        return true;
    L63:
        if (r5 == false) goto L97;
        if (r3 == false) goto L70;
        if (r2 == true) goto L70;
        if (r0 != 31) goto L70;
        copyHelperSelection();
        return true;
    L70:
        if (r3 == false) goto L75;
        if (r2 == true) goto L75;
        if (r0 != 52) goto L75;
        cutHelperSelection();
        return true;
    L75:
        if (r3 == false) goto L81;
        if (r0 != 50) goto L81;
        pasteAtHelperSelection();
        return true;
    L81:
        if (r0 != 67) goto L83;
    L95:
        deleteHelperSelection();
        return true;
    L83:
        if (r0 == 112) goto L95;
        if (r0 != 66) goto L89;
        replaceHelperSelectionWith("\n");
        return true;
    L89:
        if (r3 == true) goto L97;
        if (r4 == true) goto L97;
        int r112 = r11.getUnicodeChar(r11.getMetaState());
        if (r112 < 32) goto L97;
        replaceHelperSelectionWith(String.valueOf((char) r112));
        return true;
    L97:
        if (r5 == true) goto L104;
        if (r2 == false) goto L104;
        if (isArrowKey(r0) == false) goto L104;
        if (tryEscalateSelectionFromCaret(r0) == false) goto L104;
        return true;
    L104:
        if (r5 == false) goto L117;
        if (r2 == false) goto L117;
        if (isArrowKey(r0) == false) goto L117;
        if (r3 == true) goto L112;
        if (r4 == true) goto L112;
        boolean r113 = false;
    L114:
        if (tryExtendSelectionAcrossCells(r0, r113) == false) goto L117;
        return true;
    L112:
        r113 = true;
    L117:
        if (r5 == false) goto L141;
        if (r2 == true) goto L141;
        if (r3 == true) goto L141;
        if (r4 == true) goto L141;
        if (isArrowKey(r0) == false) goto L141;
        if (r0 == 22) goto L126;
        if (r0 == 20) goto L126;
    L127:
        TextSelectionHelper.ArticleTextSelectionHelper r114 = this.textSelectionHelper;
        if (r1 == false) goto L130;
        int r115 = r114.getEndCell();
    L131:
        this.restoreFocusCell = r115;
        TextSelectionHelper.ArticleTextSelectionHelper r116 = this.textSelectionHelper;
        if (r1 == false) goto L134;
        int r117 = r116.getEndOffset();
    L135:
        this.restoreFocusOffset = r117;
        TextSelectionHelper.ArticleTextSelectionHelper r118 = this.textSelectionHelper;
        if (r1 == false) goto L138;
        int r119 = r118.getEndChildPosition();
    L139:
        this.restoreFocusChildPosition = r119;
        this.textSelectionHelper.clear();
        return true;
    L138:
        r119 = r118.getStartChildPosition();
        goto L139
    L134:
        r117 = r116.getStartOffset();
        goto L135
    L130:
        r115 = r114.getStartCell();
    L126:
        r1 = true;
    L141:
        if (r3 == true) goto L143;
    L150:
        if (r5 == true) goto L161;
        if (r2 == true) goto L161;
        if (r3 == true) goto L161;
        if (r4 == true) goto L161;
        if (r0 == 20) goto L158;
        if (r0 != 19) goto L161;
    L158:
        if (tryPlainArrowAcrossCells(r0) == false) goto L161;
        return true;
    L161:
        if (r5 == true) goto L180;
        if (r3 == true) goto L180;
        if (r4 == true) goto L180;
        if (r0 != 61) goto L180;
        View r32 = findFocus();
        if ((r32 instanceof RichEditText) == false) goto L180;
        RichTableCell r42 = findTableCellAncestor(r32);
        if (r42 == null) goto L180;
        if (r32 != r42.getTitleEditText()) goto L175;
        if (r2 == true) goto L180;
        if (r42.focusFirstCell() == false) goto L180;
        return true;
    L175:
        RichTableCellHost r33 = r42.findHostContaining(r32);
        if (r33 == null) goto L180;
        if (r42.moveFocusByTab(r33, r2) == false) goto L180;
        return true;
    L180:
        if (r0 != 61) goto L194;
        if (r5 == false) goto L194;
        int r1110 = this.textSelectionHelper.getStartCell();
        int r02 = this.textSelectionHelper.getEndCell();
        if (r1110 < 0) goto L193;
        if (r02 < r1110) goto L193;
        if (r1110 != r02) goto L189;
        BlockRow r1111 = rowForCell(r1110);
        if (r1111 == null) goto L193;
        onCellIndent(r1111, r2);
        goto L193
    L189:
        int r1112 = this.rows.indexOf(rowForCell(r1110));
        int r03 = this.rows.indexOf(rowForCell(r02));
        if (r1112 < 0) goto L193;
        if (r03 < r1112) goto L193;
        rangeIndent(r1112, r03, r2);
    L193:
        return true;
    L194:
        return false;
    L143:
        if (r0 != 29) goto L150;
        if (this.textSelectionHelper.expandSelectionToWholeCurrentBlock() == false) goto L148;
        return true;
    L148:
        if (tryEscalateSelectAll() == false) goto L150;
        return true;
    }

    public boolean handleSelectionTouch(MotionEvent r10) {
        int r0 = r10.getAction();
        if (r0 != 0) goto L5;
        this.pressX = r10.getX();
        float r02 = r10.getY();
        this.pressY = r02;
        this.pressMoved = false;
        this.longPressConsumed = false;
        this.pressTarget = findCellUnder((int) this.pressX, (int) r02);
        long r3 = r10.getEventTime();
        if (this.textSelectionHelper.isInSelectionMode() == false) goto L102;
    L108:
        boolean r102 = false;
    L109:
        this.lastTapDownTime = r3;
        float r03 = this.pressX;
        this.lastTapDownX = r03;
        float r32 = this.pressY;
        this.lastTapDownY = r32;
        if (r102 == false) goto L116;
        if (tryStartTextSelection(this.pressTarget, r03, r32) == false) goto L116;
        this.longPressConsumed = true;
        this.lastTapDownTime = 0;
        this.textSelectionHelper.finishOneTouchSelection();
        return true;
    L116:
        if (this.pressTarget == null) goto L121;
        Runnable r103 = this.longPressRunnable;
        if (r103 == null) goto L120;
        removeCallbacks(r103);
    L120:
        RichEditorListView$$ExternalSyntheticLambda27 r104 = new RichEditorListView$$ExternalSyntheticLambda27(this);
        this.longPressRunnable = r104;
        postDelayed(r104, ViewConfiguration.getLongPressTimeout());
    L121:
        return false;
    L102:
        if ((r3 - this.lastTapDownTime) > ViewConfiguration.getDoubleTapTimeout()) goto L108;
        if (Math.abs(this.pressX - this.lastTapDownX) > AndroidUtilities.dp(24.0f)) goto L108;
        if (Math.abs(this.pressY - this.lastTapDownY) > AndroidUtilities.dp(24.0f)) goto L108;
        r102 = true;
        goto L109
    L5:
        if (r0 != 1) goto L7;
        Runnable r04 = this.longPressRunnable;
        if (r04 == null) goto L33;
        removeCallbacks(r04);
        this.longPressRunnable = null;
    L33:
        if (this.cellSelectionDragActive == false) goto L37;
        updateCellSelectionDrag(r10.getX(), r10.getY());
        finishCellSelectionDrag(true);
        this.pressTarget = null;
        this.longPressConsumed = false;
        return true;
    L37:
        if (this.pressMoved == true) goto L46;
        if (this.longPressConsumed == true) goto L46;
        View r05 = this.pressTarget;
        if ((r05 instanceof RichTableCell) == false) goto L46;
        if (handleTableHandleTap((RichTableCell) r05, (int) ((r10.getX() - r0.getLeft()) - getLeft()), (int) ((r10.getY() - r0.getTop()) - getTop())) == false) goto L46;
        this.pressTarget = null;
        this.longPressConsumed = false;
    L46:
        if (this.pressMoved == true) goto L62;
        if (this.longPressConsumed == true) goto L62;
        RichTableCell r06 = this.activeCellSelectionTable;
        if (r06 == null) goto L62;
        View r2 = this.pressTarget;
        if (r2 != r06) goto L59;
        RichTableCell r22 = (RichTableCell) r2;
        TL_iv.pageTableCell r07 = r22.findCellAt((int) ((r10.getX() - r22.getLeft()) - getLeft()), (int) ((r10.getY() - r22.getTop()) - getTop()));
        if (r07 == null) goto L62;
        if (isDotSelection() == false) goto L58;
        exitCellSelectionMode();
        goto L62
    L58:
        r22.toggleCellSelection(r07);
        goto L62
    L59:
        if (r2 == null) goto L62;
        exitCellSelectionMode();
    L62:
        if (this.pressMoved == true) goto L75;
        if (this.longPressConsumed == true) goto L75;
        if (this.pressTarget != null) goto L75;
        if (this.textSelectionHelper.isInSelectionMode() == true) goto L75;
        if (this.activeCellSelectionTable != null) goto L75;
        if (isTapBelowContent(r10.getX(), r10.getY()) == false) goto L75;
        onTapBelowContent();
    L75:
        if (this.pressMoved == true) goto L88;
        if (this.longPressConsumed == true) goto L88;
        if (this.pressTarget != null) goto L88;
        if (this.textSelectionHelper.isInSelectionMode() == true) goto L88;
        if (this.activeCellSelectionTable != null) goto L88;
        if (isTapAboveContent(r10.getX(), r10.getY()) == false) goto L88;
        onTapAboveContent();
    L88:
        if (this.pressMoved == false) goto L90;
    L98:
        this.pressTarget = null;
        this.longPressConsumed = false;
        goto L121
    L90:
        if (this.longPressConsumed == true) goto L98;
        if ((this.pressTarget instanceof RichMathCell) == false) goto L98;
        if (this.textSelectionHelper.isInSelectionMode() == true) goto L98;
        if (this.activeCellSelectionTable != null) goto L98;
        openMathEditor(((RichMathCell) this.pressTarget).getRow());
        goto L98
    L7:
        if (r0 == 2) goto L19;
        if (r0 != 3) goto L121;
        Runnable r105 = this.longPressRunnable;
        if (r105 == null) goto L15;
        removeCallbacks(r105);
        this.longPressRunnable = null;
    L15:
        if (this.cellSelectionDragActive == false) goto L17;
        exitCellSelectionMode();
    L17:
        this.pressTarget = null;
        this.longPressConsumed = false;
        goto L121
    L19:
        if (this.cellSelectionDragActive == true) goto L21;
    L24:
        float r08 = r10.getX() - this.pressX;
        float r106 = r10.getY() - this.pressY;
        if (((r08 * r08) + (r106 * r106)) <= (AndroidUtilities.dp(8.0f) * AndroidUtilities.dp(8.0f))) goto L121;
        this.pressMoved = true;
        Runnable r107 = this.longPressRunnable;
        if (r107 == null) goto L121;
        removeCallbacks(r107);
        this.longPressRunnable = null;
        goto L121
    L21:
        if (this.pressTarget != this.activeCellSelectionTable) goto L24;
        updateCellSelectionDrag(r10.getX(), r10.getY());
        this.pressMoved = true;
        return true;
    }

    public void handleSlashCommand(BlockRow r9, int r10) {
        if (r10 != 7) goto L6;
        transformRow(r9, new TL_iv.pageBlockButtonRow(), 0, 0, false, false);
        return;
    L6:
        this.pendingMediaRow = null;
        this.pendingInsertRow = r9;
        RichEditorHistory r8 = this.history;
        if (r8 == null) goto L9;
        r8.flush();
    L9:
        clearRowText(r9);
        RichEditorHistory r82 = this.history;
        if (r82 == null) goto L13;
        r82.record();
    L13:
        switch(r10) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L17;
            case 6: goto L15;
            default: goto L14;
        };
    L14:
        return;
    L15:
        insertDetails();
        return;
    L17:
        this.delegate.onOpenAttachRequest(74, 1);
        return;
    L19:
        openLatexEditorAndAdd();
        return;
    L21:
        this.delegate.onOpenAttachRequest(74, 6);
        return;
    L23:
        this.delegate.onOpenAttachRequest(74, 3);
    }

    public boolean hasAnyText() {
        int r1 = 0;
    L4:
        if (r1 >= this.rows.size()) goto L43;
        BlockRow r2 = this.rows.get(r1);
        if (RichTextCell.readPlainText(r2.block).isEmpty() == false) goto L7;
        if (isMedia(r2.block) == true) goto L14;
        TL_iv.PageBlock r3 = r2.block;
        if ((r3 instanceof TL_iv.pageBlockAudio) == true) goto L14;
        if ((r3 instanceof TL_iv.pageBlockDocument) == true) goto L14;
    L22:
        TL_iv.PageBlock r32 = r2.block;
        if ((r32 instanceof TL_iv.pageBlockMath) == true) goto L25;
    L27:
        TL_iv.PageBlock r33 = r2.block;
        if ((r33 instanceof TL_iv.pageBlockMap) == true) goto L30;
    L32:
        TL_iv.PageBlock r34 = r2.block;
        if ((r34 instanceof TL_iv.pageBlockTable) == true) goto L35;
    L37:
        TL_iv.PageBlock r22 = r2.block;
        if ((r22 instanceof TL_iv.pageBlockButtonRow) == false) goto L42;
        if (((TL_iv.pageBlockButtonRow) r22).buttons.isEmpty() == true) goto L42;
        return true;
    L42:
        r1 = r1 + 1;
        goto L4
    L35:
        if (tableHasText((TL_iv.pageBlockTable) r34) == false) goto L37;
        return true;
    L30:
        if (RichMapCell.hasGeo((TL_iv.pageBlockMap) r33) == false) goto L32;
        return true;
    L25:
        if (TextUtils.isEmpty(((TL_iv.pageBlockMath) r32).source) == true) goto L27;
        return true;
    L14:
        Iterator<MediaUploadState> r35 = mediasOf(r2).iterator();
    L16:
        if (r35.hasNext() == false) goto L22;
        MediaUploadState r5 = r35.next();
        if (r5.isReady() == true) goto L21;
        if (r5.isPending() == false) goto L16;
    L21:
        return true;
    L7:
        return true;
    L43:
        return false;
    }

    public boolean hasPendingUploads() {
        int r1 = 0;
    L4:
        if (r1 >= this.rows.size()) goto L13;
        Iterator<MediaUploadState> r2 = mediasOf(this.rows.get(r1)).iterator();
    L7:
        if (r2.hasNext() == false) goto L12;
        if (r2.next().isPending() == false) goto L7;
        return true;
    L12:
        r1 = r1 + 1;
        goto L4
    L13:
        return false;
    }

    public boolean indentSelection(boolean r6) {
        int[] r0 = selectionRowRange();
        if (r0 == null) goto L9;
        int r3 = r0[0];
        int r4 = r0[1];
        if (r3 == r4) goto L9;
        return rangeIndent(r3, r4, r6);
    L9:
        if (r0 != null) goto L11;
        BlockRow r02 = currentBlockRow();
    L12:
        if (r02 != null) goto L14;
    L16:
        return false;
    L14:
        if (onCellIndent(r02, r6) == false) goto L16;
        return true;
    L11:
        r02 = this.rows.get(r0[0]);
        goto L12
    }

    public void insertDetails() {
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L5;
        r0.flush();
    L5:
        TL_iv.pageBlockDetails r02 = new TL_iv.pageBlockDetails();
        r02.open = true;
        r02.title = new TL_iv.textEmpty();
        BlockRow r1 = new BlockRow(r02);
        BlockRow r03 = new BlockRow(new TL_iv.pageBlockParagraph());
        BlockRow r2 = newDetailsEndRow();
        BlockRow r3 = findFocusedRow();
        if (r3 == null) goto L8;
        int r4 = this.rows.indexOf(r3);
    L9:
        if (r3 == null) goto L19;
        TL_iv.PageBlock r5 = r3.block;
        if ((r5 instanceof TL_iv.pageBlockParagraph) == false) goto L19;
        if (r3.media != null) goto L19;
        if (r3.detailsEnd == true) goto L19;
        if (RichTextCell.readPlainText(r5).isEmpty() == false) goto L19;
        this.rows.remove(r4);
    L22:
        this.rows.add(r4, r2);
        this.rows.add(r4, r03);
        this.rows.add(r4, r1);
        this.adapter.update(false);
        RichEditorHistory r04 = this.history;
        if (r04 == null) goto L25;
        r04.record();
    L25:
        post(new RichEditorListView$$ExternalSyntheticLambda63(this, r1));
        return;
    L19:
        if (r4 < 0) goto L21;
        r4 = r4 + 1;
        goto L22
    L21:
        r4 = this.rows.size();
        goto L22
    L8:
        r4 = -1;
        goto L9
    }

    public boolean isCaptionSelection() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
    L15:
        return false;
    L5:
        if (r0.isInSelectionMode() == false) goto L15;
        int r02 = this.textSelectionHelper.getStartCell();
        if (r02 != this.textSelectionHelper.getEndCell()) goto L15;
        if (r02 < 0) goto L15;
        if (r02 >= this.itemRows.size()) goto L15;
        return selectableAt(r02) instanceof RichCaptionHost;
    }

    public boolean isDateApplied(int r1, int r2, int r3, int r4) {
        CharSequence r0 = singleSelectionText(r1, r2, r3, r4);
        if (r0 != null) goto L6;
        return false;
    L6:
        return RichTextStyle.hasDate(r0, Math.max(0, Math.min(r2, r4)), Math.max(r2, r4));
    }

    public boolean isInSelectionMode() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isInSelectionMode() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isLinkApplied(int r1, int r2, int r3, int r4) {
        CharSequence r0 = singleSelectionText(r1, r2, r3, r4);
        if (r0 != null) goto L6;
        return false;
    L6:
        return RichTextStyle.hasLink(r0, Math.max(0, Math.min(r2, r4)), Math.max(r2, r4));
    }

    public boolean isLossy() {
        return RichMessageConvert.isLossy(this.rows, this.quoteAuthors);
    }

    public boolean isQuoteAuthorSelection() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
    L23:
        return false;
    L5:
        if (r0.isInSelectionMode() == false) goto L23;
        int r02 = this.textSelectionHelper.getStartCell();
        if (r02 != this.textSelectionHelper.getEndCell()) goto L23;
        if (r02 < 0) goto L23;
        if (r02 >= this.itemRows.size()) goto L23;
        if (this.textSelectionHelper.getStartChildPosition() != 1) goto L23;
        if (this.textSelectionHelper.getEndChildPosition() != 1) goto L23;
        BlockRow r4 = rowForCell(r02);
        if (r4 == null) goto L23;
        if (RichTextCell.isQuoteBlock(r4.block) == false) goto L23;
        return true;
    }

    @Override // org.telegram.ui.Components.UniversalRecyclerView
    public boolean isReorderRemoving() {
        return this.draggingOverTrash;
    }

    public boolean isSelectionAllHeadings() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
    L23:
        return false;
    L5:
        if (r0.isInSelectionMode() == false) goto L23;
        int r02 = this.textSelectionHelper.getStartCell();
        int r2 = this.textSelectionHelper.getEndCell();
        if (r02 < 0) goto L23;
        if (r2 < 0) goto L23;
        if (r2 < r02) goto L23;
        boolean r3 = false;
    L13:
        if (r02 > r2) goto L22;
        BlockRow r4 = rowForCell(r02);
        if (r4 == null) goto L21;
        if (isHeading(r4.block) == false) goto L19;
        r3 = true;
        goto L21
    L19:
        return false;
    L21:
        r02 = r02 + 1;
        goto L13
    L22:
        return r3;
    }

    public boolean isSelectionQuoted() {
        int[] r0 = selectedRowRange();
        if (r0 != null) goto L5;
    L7:
        return false;
    L5:
        if (isRangeQuoted(r0[0], r0[1]) == false) goto L7;
        return true;
    }

    public boolean isSimpleConvertible() {
        if (hasAnyText() == true) goto L5;
        return false;
    L5:
        if (RichMessageConvert.isLossy(this.rows, this.quoteAuthors) == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isStyleFullyApplied(int r8, int r9, int r10, int r11, int r12) {
        if (isDetailsSelection() == false) goto L14;
        RichEditText r7 = detailsEditText(r9);
        if (r7 != null) goto L7;
        return false;
    L7:
        int r92 = Math.max(0, Math.min(Math.min(r10, r12), r7.length()));
        int r102 = Math.max(0, Math.min(Math.max(r10, r12), r7.length()));
        if (r92 < r102) goto L10;
    L12:
        return false;
    L10:
        if ((r7.getCurrentStyle(r92, r102) & r8) == 0) goto L12;
        return true;
    L14:
        if (isQuoteAuthorSelection() == false) goto L24;
        RichEditText r72 = quoteAuthorEditText(r9);
        if (r72 != null) goto L18;
        return false;
    L18:
        int r93 = r72.length();
        int r112 = Math.max(0, Math.min(Math.min(r10, r12), r93));
        int r94 = Math.max(0, Math.min(Math.max(r10, r12), r93));
        if (r112 < r94) goto L21;
    L23:
        return false;
    L21:
        if ((r72.getCurrentStyle(r112, r94) & r8) == 0) goto L23;
        return true;
    L24:
        int r0 = r9;
        boolean r3 = false;
    L25:
        if (r0 > r11) goto L52;
        BlockRow r4 = rowForCell(r0);
        if (r4 == null) goto L51;
        if (isInlineFormattable(r4.block) == false) goto L51;
        int r42 = blockTextLength(r0);
        if (r0 != r9) goto L34;
        int r5 = r10;
    L35:
        if (r0 != r11) goto L37;
        int r6 = r12;
    L38:
        int r52 = Math.max(0, Math.min(r5, r42));
        int r43 = Math.max(0, Math.min(r6, r42));
        if (r52 >= r43) goto L51;
        RichTextCell r32 = cellAt(r0);
        if (r32 != null) goto L44;
        boolean r33 = RichTextStyle.hasStyle(RichTextCell.readStyledText(rowForCell(r0).block), r52, r43, r8);
    L48:
        if (r33 == false) goto L49;
        r3 = true;
        goto L51
    L49:
        return false;
    L44:
        if ((r32.getStyleDelegate().getCurrentStyle(r52, r43) & r8) == 0) goto L46;
        r33 = true;
        goto L48
    L46:
        r33 = false;
        goto L48
    L37:
        r6 = r42;
        goto L38
    L34:
        r5 = 0;
    L51:
        r0 = r0 + 1;
        goto L25
    L52:
        return r3;
    }

    public boolean isStyleFullyAppliedTable(int r8, int r9, int r10, int r11, int r12, int r13) {
        int r1 = r10;
        boolean r2 = false;
    L3:
        if (r1 > r12) goto L24;
        RichEditText r3 = tableEditText(r9, r1);
        if (r3 == null) goto L23;
        int r4 = r3.length();
        if (r1 != r10) goto L10;
        int r5 = r11;
    L11:
        if (r1 != r12) goto L13;
        int r6 = r13;
    L14:
        if (r10 != r12) goto L16;
        r5 = Math.min(r11, r13);
        r6 = Math.max(r11, r13);
    L16:
        int r52 = Math.max(0, Math.min(r5, r4));
        int r42 = Math.max(0, Math.min(r6, r4));
        if (r52 >= r42) goto L23;
        if ((r3.getCurrentStyle(r52, r42) & r8) == 0) goto L21;
        r2 = true;
        goto L23
    L21:
        return false;
    L13:
        r6 = r4;
        goto L14
    L10:
        r5 = 0;
    L23:
        r1 = r1 + 1;
        goto L3
    L24:
        return r2;
    }

    public boolean isTableSelection() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
    L14:
        return false;
    L5:
        if (r0.isInSelectionMode() == false) goto L14;
        int r02 = this.textSelectionHelper.getStartCell();
        int r2 = this.textSelectionHelper.getEndCell();
        BlockRow r3 = rowForCell(r02);
        if (r02 != r2) goto L14;
        if (r3 == null) goto L14;
        if ((r3.block instanceof TL_iv.pageBlockTable) == false) goto L14;
        return true;
    }

    public boolean isWithinLimits() {
        if (findExceededLimit() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void loadHtml(CharSequence r3, String r4, CharSequence r5) {
        if (r4 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r3) == true) goto L7;
        flattenBlocks(this.rows, RichMessageConvert.blocksFromCharSequence(r3), this.quoteAuthors);
    L7:
        List<BlockRow> r32 = resolvePastedMedia(RichHtml.parse(r4, this.quoteAuthors));
        if (r32 == null) goto L11;
        this.rows.addAll(r32);
    L11:
        if (TextUtils.isEmpty(r5) == true) goto L13;
        flattenBlocks(this.rows, RichMessageConvert.blocksFromCharSequence(r5), this.quoteAuthors);
    L13:
        normalizeNestedQuotes();
        this.adapter.update(false);
    }

    public void loadRichMessage(TL_iv.RichMessage r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        this.loadedRichMessage = r3;
        flattenBlocks(this.rows, r3.blocks, this.quoteAuthors);
        normalizeNestedQuotes();
        int r0 = 0;
    L6:
        if (r0 >= this.rows.size()) goto L8;
        resolveLoadedMedia(this.rows.get(r0));
        r0 = r0 + 1;
        goto L6
    L8:
        this.adapter.update(false);
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.imeFocusListener);
    }

    public boolean onCellIndent(BlockRow r5, boolean r6) {
        int r0 = this.rows.indexOf(r5);
        if (r0 >= 0) goto L5;
        return false;
    L5:
        int r2 = captureCaret(r5);
        RichEditorHistory r3 = this.history;
        if (r3 == null) goto L9;
        r3.flush();
    L9:
        if (indentRow(r0, r6, false) == true) goto L11;
        return false;
    L11:
        renumberAllRuns();
        if (hasFocusedEdit() == false) goto L14;
        applyInPlaceUpdateKeepingFocus();
    L15:
        RichEditorHistory r62 = this.history;
        if (r62 == null) goto L18;
        r62.record();
    L18:
        restoreCaret(r5, r2);
        return true;
    L14:
        this.adapter.update(false);
        goto L15
    }

    public void onDateClicked() {
        if (isTableSelection() == false) goto L7;
        onDateClickedTable();
        return;
    L7:
        if (isDetailsSelection() == false) goto L11;
        onDateClickedDetails();
        return;
    L11:
        if (isCaptionSelection() == false) goto L15;
        onDateClickedCaption();
        return;
    L15:
        if (isQuoteAuthorSelection() == false) goto L18;
        onDateClickedAuthor();
        return;
    L18:
        RichTextCell r0 = singleSelectionCell();
        if (r0 == null) goto L35;
        int r1 = r0.getEditText().length();
        int r2 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r1));
        int r12 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r1));
        if (r2 < r12) goto L24;
        return;
    L24:
        RichEditorHistory r3 = this.history;
        if (r3 == null) goto L28;
        r3.flush();
    L28:
        if (RichTextStyle.hasLink(r0.getEditText().getText(), r2, r12) == false) goto L33;
        RichTextStyle.removeLink(r0.getEditText().getText(), r2, r12);
        r0.getEditText().invalidateEffects();
        r0.persistStyle();
        RichEditorHistory r32 = this.history;
        if (r32 == null) goto L32;
        r32.record();
    L32:
        refreshSelectionHighlight();
    L33:
        r0.getEditText().setSelectionOverride(r2, r12);
        r0.getEditText().makeSelectedDate();
        return;
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.imeFocusListener);
        doNotDetachViews(false);
    }

    public void onFormattingClicked(int r17) {
        TextSelectionHelper.ArticleTextSelectionHelper r1 = this.textSelectionHelper;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.isInSelectionMode() == true) goto L8;
        return;
    L8:
        if (isTableSelection() == false) goto L12;
        onFormattingClickedTable(r17);
        return;
    L12:
        if (isDetailsSelection() == false) goto L16;
        onFormattingClickedDetails(r17);
        return;
    L16:
        if (isCaptionSelection() == false) goto L20;
        onFormattingClickedCaption(r17);
        return;
    L20:
        if (isQuoteAuthorSelection() == false) goto L23;
        onFormattingClickedAuthor(r17);
        return;
    L23:
        int r2 = this.textSelectionHelper.getStartCell();
        int r4 = this.textSelectionHelper.getEndCell();
        int r3 = this.textSelectionHelper.getStartOffset();
        int r5 = this.textSelectionHelper.getEndOffset();
        if (r2 < 0) goto L85;
        if (r4 < 0) goto L86;
        if (r4 >= r2) goto L28;
        return;
    L28:
        if (r4 >= this.itemRows.size()) goto L90;
        boolean r6 = isStyleFullyApplied(r17, r2, r3, r4, r5);
        boolean r7 = !r6;
        if (r6 == true) goto L33;
        int r9 = clearMaskFor(r17);
    L34:
        RichEditorHistory r10 = this.history;
        if (r10 == null) goto L37;
        r10.flush();
    L37:
        this.suppressSpansChanged = true;
        int r11 = r2;
        boolean r12 = false;
    L38:
        if (r11 > r4) goto L70;
        BlockRow r13 = rowForCell(r11);
        if (r13 == null) goto L69;
        if (isInlineFormattable(r13.block) == false) goto L69;
        int r14 = blockTextLength(r11);
        if (r11 != r2) goto L47;
        int r15 = r3;
    L48:
        if (r11 != r4) goto L50;
        int r102 = r5;
    L51:
        int r152 = Math.max(0, Math.min(r15, r14));
        int r103 = Math.max(0, Math.min(r102, r14));
        if (r152 >= r103) goto L69;
        RichTextCell r122 = cellAt(r11);
        if (r122 == null) goto L63;
        FloatingToolbar.StyleDelegate r132 = r122.getStyleDelegate();
        if (r6 == true) goto L61;
        if (r9 == 0) goto L60;
        r132.removeStyle(r9, r152, r103);
    L60:
        r132.addStyle(r17, r152, r103);
    L62:
        r122.getEditText().invalidateEffects();
        r122.getEditText().requestLayout();
        r122.persistStyle();
    L68:
        r12 = true;
        goto L69
    L61:
        r132.removeStyle(r17, r152, r103);
        goto L62
    L63:
        SpannableStringBuilder r123 = new SpannableStringBuilder(RichTextCell.readStyledText(r13.block));
        if (r6 == true) goto L67;
        if (r9 == 0) goto L67;
        RichTextStyle.setStyle(r123, r152, r103, r9, false);
    L67:
        RichTextStyle.setStyle(r123, r152, r103, r17, r7);
        RichTextCell.applyStyledTextToBlock(r13.block, r123);
        goto L68
    L50:
        r102 = r14;
        goto L51
    L47:
        r15 = 0;
    L69:
        r11 = r11 + 1;
        goto L38
    L70:
        this.suppressSpansChanged = false;
        if (r12 == false) goto L75;
        RichEditorHistory r16 = this.history;
        if (r16 == null) goto L75;
        r16.record();
    L75:
        this.delegate.onSelectionChanged();
        if (r12 == false) goto L88;
        refreshSelectionHighlight();
        return;
    L88:
        return;
    L33:
        r9 = 0;
        goto L34
    L90:
        return;
    L86:
        return;
    }

    public void onInlineButtonClicked(View r3) {
        InlineButtonEdit r0 = beginInlineButtonEdit();
        if (r0 == null) goto L6;
        hideTextSelectionUi(false);
        this.delegate.onInlineButtonEditRequested(r0, r3);
        return;
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        int r0 = r7 - r5;
        int r1 = this.lastListHeight;
        if (r1 <= 0) goto L6;
        if (r0 >= r1) goto L6;
        boolean r12 = true;
    L7:
        super.onLayout(r3, r4, r5, r6, r7);
        if (r12 == false) goto L17;
        View r32 = findFocus();
        if (r32 != null) goto L12;
        View r33 = null;
    L13:
        if (r33 == null) goto L17;
        int r34 = (r33.getBottom() + AndroidUtilities.dp(8.0f)) - (r0 - getPaddingBottom());
        if (r34 <= 0) goto L17;
        post(new RichEditorListView$$ExternalSyntheticLambda5(this, r34));
        goto L17
    L12:
        r33 = findContainingItemView(r32);
    L17:
        this.lastListHeight = r0;
        return;
    L6:
        r12 = false;
        goto L7
    }

    @Override // org.telegram.ui.Components.UniversalRecyclerView
    public void onLayoutUpdate() {
        super.onLayoutUpdate();
        Delegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onListLayoutUpdated();
        return;
    }

    public void onLinkClicked() {
        if (isTableSelection() == false) goto L7;
        onLinkClickedTable();
        return;
    L7:
        if (isDetailsSelection() == false) goto L11;
        onLinkClickedDetails();
        return;
    L11:
        if (isCaptionSelection() == false) goto L15;
        onLinkClickedCaption();
        return;
    L15:
        if (isQuoteAuthorSelection() == false) goto L18;
        onLinkClickedAuthor();
        return;
    L18:
        RichTextCell r0 = singleSelectionCell();
        if (r0 == null) goto L43;
        int r1 = r0.getEditText().length();
        int r2 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r1));
        int r12 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r1));
        if (r2 < r12) goto L24;
        return;
    L24:
        boolean r4 = RichTextStyle.hasLink(r0.getEditText().getText(), r2, r12);
        RichEditorHistory r5 = this.history;
        if (r4 == false) goto L33;
        if (r5 == null) goto L28;
        r5.flush();
    L28:
        RichTextStyle.removeLink(r0.getEditText().getText(), r2, r12);
        r0.getEditText().invalidateEffects();
        r0.persistStyle();
        RichEditorHistory r02 = this.history;
        if (r02 == null) goto L31;
        r02.record();
    L31:
        this.delegate.onSelectionChanged();
        refreshSelectionHighlight();
        return;
    L33:
        if (r5 == null) goto L36;
        r5.flush();
    L36:
        if (RichTextStyle.hasDate(r0.getEditText().getText(), r2, r12) == false) goto L41;
        RichTextStyle.removeDate(r0.getEditText().getText(), r2, r12);
        r0.getEditText().invalidateEffects();
        r0.persistStyle();
        RichEditorHistory r42 = this.history;
        if (r42 == null) goto L40;
        r42.record();
    L40:
        refreshSelectionHighlight();
    L41:
        r0.getEditText().setSelectionOverride(r2, r12);
        hideTextSelectionUi(false);
        showSelectedUrlDialog(r0.getEditText());
        return;
    }

    public void onMathClicked() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isInSelectionMode() == true) goto L8;
        return;
    L8:
        if (isTableSelection() == false) goto L14;
        int r02 = this.textSelectionHelper.getStartCell();
        int r1 = this.textSelectionHelper.getStartChildPosition();
        if (r1 != this.textSelectionHelper.getEndChildPosition()) goto L35;
        RichEditText r2 = tableEditText(r02, r1);
        Runnable r3 = new RichEditorListView$$ExternalSyntheticLambda6(this, r02, r1);
    L26:
        if (r2 == null) goto L37;
        int r03 = r2.length();
        int r12 = Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r03));
        int r04 = Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r03));
        if (r12 > r04) goto L38;
        insertInlineMath(r2, r12, r04, r3);
        return;
    L38:
        return;
    L37:
        return;
    L35:
        return;
    L14:
        if (isDetailsSelection() == false) goto L17;
        int r05 = this.textSelectionHelper.getStartCell();
        r2 = detailsEditText(r05);
        r3 = new RichEditorListView$$ExternalSyntheticLambda7(this, r05);
        goto L26
    L17:
        if (isCaptionSelection() == false) goto L20;
        int r06 = this.textSelectionHelper.getStartCell();
        r2 = captionEditText(r06);
        r3 = new RichEditorListView$$ExternalSyntheticLambda8(this, r06);
        goto L26
    L20:
        if (isQuoteAuthorSelection() == false) goto L22;
        int r07 = this.textSelectionHelper.getStartCell();
        r2 = quoteAuthorEditText(r07);
        r3 = new RichEditorListView$$ExternalSyntheticLambda9(this, r07);
        goto L26
    L22:
        RichTextCell r08 = singleSelectionCell();
        if (r08 == null) goto L36;
        r2 = r08.getEditText();
        r3 = new RichEditorListView$$ExternalSyntheticLambda10(r08);
        goto L26
    }

    @Override // org.telegram.ui.Components.UniversalRecyclerView
    public void onReorderEnd(RecyclerView.ViewHolder r1) {
        Delegate r12 = this.delegate;
        if (r12 == null) goto L5;
        r12.onReorderEnd();
    L5:
        resyncInsetCells(true);
    }

    @Override // org.telegram.ui.Components.UniversalRecyclerView
    public void onReorderMoved(RecyclerView.ViewHolder r5) {
        if (this.delegate == null) goto L8;
        if (r5 == null) goto L9;
        View r52 = r5.itemView;
        int[] r0 = new int[2];
        r52.getLocationOnScreen(r0);
        this.draggingOverTrash = this.delegate.onReorderMove(r0[0] + (r52.getWidth() / 2.0f), r0[1] + r52.getHeight());
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.Components.UniversalRecyclerView
    public void onReorderRemove(RecyclerView.ViewHolder r3) {
        BlockRow r0 = this.draggingRow;
        this.draggingRow = null;
        this.draggingOverTrash = false;
        if (r0 == null) goto L14;
        RichEditorHistory r32 = this.history;
        if (r32 == null) goto L7;
        r32.flush();
    L7:
        removeRow(r0);
        RichEditorHistory r33 = this.history;
        if (r33 == null) goto L10;
        r33.record();
    L10:
        Delegate r2 = this.delegate;
        if (r2 == null) goto L17;
        r2.onContentChanged();
        return;
    L17:
        return;
    L14:
        if (r3 == null) goto L18;
        r3.itemView.setTranslationX(0.0f);
        r3.itemView.setTranslationY(0.0f);
        return;
    }

    @Override // org.telegram.ui.Components.UniversalRecyclerView
    public void onReorderStart(RecyclerView.ViewHolder r1) {
        this.draggingRow = rowFromHolder(r1);
        this.draggingOverTrash = false;
        Delegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onReorderStart();
        return;
    }

    public RichEditText quoteAuthorEditText(int r1) {
        View r0 = selectableAt(r1);
        if ((r0 instanceof RichTextCell) == true) goto L5;
        return null;
    L5:
        return ((RichTextCell) r0).getAuthorEditText();
    }

    public void redo() {
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L6;
        r0.redo();
        return;
    }

    public void resetHistoryBaseline() {
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L6;
        r0.resetBaseline();
        return;
    }

    public BlockRow rowForCell(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.itemRows.size()) goto L6;
        return null;
    L6:
        return this.itemRows.get(r2);
    }

    public void seedEmptyArticle() {
        this.rows.clear();
        this.rows.add(new BlockRow(new TL_iv.pageBlockHeading1()));
        this.rows.add(new BlockRow(new TL_iv.pageBlockParagraph()));
        this.adapter.update(false);
    }

    public boolean selectionHasInlineFormattable() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.textSelectionHelper;
        if (r0 != null) goto L5;
    L56:
        return false;
    L5:
        if (r0.isInSelectionMode() == false) goto L56;
        if (isTableSelection() == false) goto L12;
        return tableSelectionHasFormattable();
    L12:
        if (isDetailsSelection() == false) goto L21;
        RichEditText r02 = detailsEditText(this.textSelectionHelper.getStartCell());
        if (r02 != null) goto L17;
        return false;
    L17:
        if (Math.max(0, Math.min(Math.min(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r02.length())) >= Math.max(0, Math.min(Math.max(this.textSelectionHelper.getStartOffset(), this.textSelectionHelper.getEndOffset()), r02.length()))) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (isCaptionSelection() == false) goto L25;
        return captionSelectionHasFormattable();
    L25:
        if (isQuoteAuthorSelection() == true) goto L27;
        int r03 = this.textSelectionHelper.getStartCell();
        int r3 = this.textSelectionHelper.getEndCell();
        int r4 = this.textSelectionHelper.getStartOffset();
        int r5 = this.textSelectionHelper.getEndOffset();
        if (r03 < 0) goto L56;
        if (r3 < 0) goto L56;
        if (r3 < r03) goto L56;
        if (r3 >= this.itemRows.size()) goto L56;
        int r6 = r03;
    L36:
        if (r6 > r3) goto L56;
        BlockRow r7 = rowForCell(r6);
        if (r7 == null) goto L55;
        if (r7.authorQuoteId == 0) goto L43;
    L45:
        int r72 = blockTextLength(r6);
        if (r6 != r03) goto L48;
        int r8 = r4;
    L49:
        if (r6 != r3) goto L51;
        int r9 = r5;
    L53:
        if (Math.max(0, Math.min(r8, r72)) >= Math.max(0, Math.min(r9, r72))) goto L55;
        return true;
    L51:
        r9 = r72;
        goto L53
    L48:
        r8 = 0;
        goto L49
    L43:
        if (isInlineFormattable(r7.block) == true) goto L45;
    L55:
        r6 = r6 + 1;
        goto L36
    L27:
        return quoteAuthorSelectionHasFormattable();
    }

    public void setAdaptiveLinkDialogs(boolean r1) {
        this.adaptiveLinkDialogs = r1;
    }

    public void setAllowTapAboveContent(boolean r1) {
        this.allowTapAboveContent = r1;
    }

    public void setFileRefParentObject(MessageObject r1) {
        this.fileRefParentObject = r1;
    }

    public void setInitialText(CharSequence r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        this.initialSelectionRows = new ArrayList();
        int r0 = this.rows.size();
        flattenBlocks(this.rows, RichMessageConvert.blocksFromCharSequence(r4), this.quoteAuthors);
    L6:
        if (r0 >= this.rows.size()) goto L8;
        this.initialSelectionRows.add(this.rows.get(r0));
        r0 = r0 + 1;
        goto L6
    L8:
        this.adapter.update(false);
    }

    public void setInsets(int r1, int r2, int r3) {
        this.bottomInset = r1;
        this.imeInset = r2;
        this.emojiPadding = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r3v8, types: [org.telegram.ui.Components.ItemOptions] */
    /* JADX WARN: Type inference failed for: r5v4, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    public void showTableCellMenu(RichTableCell r31) {
        TableModel r2 = r31.getModel();
        if (r2 == null) goto L136;
        Set<TL_iv.pageTableCell> r3 = r31.getSelectedCells();
        if (r3.isEmpty() == false) goto L8;
        return;
    L8:
        int r4 = r3.size();
        if (r4 >= 2) goto L11;
    L13:
        int r8 = 0;
    L14:
        if (r4 == 1) goto L16;
    L18:
        int r9 = 0;
    L19:
        boolean r10 = computeSpansFullRows(r2, r3);
        boolean r11 = computeSpansFullColumns(r2, r3);
        boolean r12 = computeAllSelected(r2, r3);
        if (r12 == true) goto L22;
    L27:
        boolean r13 = true;
    L28:
        if (r10 == false) goto L33;
        if (r12 == true) goto L33;
        if (distinctSelectedRows(r2, r3) >= r2.rowCount) goto L33;
        int r14 = 1;
    L34:
        if (r11 == false) goto L39;
        if (r12 == true) goto L39;
        if (distinctSelectedCols(r2, r3) >= r2.colCount) goto L39;
        int r32 = 1;
    L40:
        if (r13 == false) goto L45;
        if (r11 == false) goto L45;
        boolean r16 = true;
        boolean r162 = r16;
        if (r2.colCount >= MessagesController.getInstance(this.currentAccount).config.richMessageMaxTableCols.get()) goto L46;
        boolean r6 = true;
        boolean r163 = r16;
    L47:
        if (r13 == false) goto L50;
        if (r10 == false) goto L50;
        boolean r132 = r163;
    L51:
        dismissTableCellMenu();
        int r15 = (r8 + 1) + r9;
        if (r6 == false) goto L54;
        int r17 = 2;
    L55:
        int r152 = r15 + r17;
        if (r132 == false) goto L58;
        int r172 = 2;
    L59:
        int r153 = ((r152 + r172) + r32) + r14;
        if (r12 == false) goto L62;
        int r18 = 3;
    L63:
        int r154 = ((r153 + r18) * 48) + 68;
        if (r12 == false) goto L66;
        int r19 = 8;
    L67:
        int r155 = AndroidUtilities.dp(r154 + r19);
        View r5 = tableMenuAnchor(r31, false);
        int r21 = r32;
        int[] r33 = new int[2];
        r5.getLocationOnScreen(r33);
        if (((r33[r163] - r155) - AndroidUtilities.dp(8.0f)) >= (AndroidUtilities.statusBarHeight + AndroidUtilities.dp(8.0f))) goto L71;
        r5 = tableMenuAnchor(r31, r163);
    L71:
        ?? r34 = this.delegate.makeMenu(r5);
        r34.setDimAlpha(0);
        r34.setDrawScrim(false);
        r34.allowShowingOnTopOfKeyboard();
        ?? r52 = new LinearLayout(getContext());
        r52.setMinimumWidth(AndroidUtilities.dp(208.0f));
        r52.setOrientation(1);
        TextView r156 = new TextView(getContext());
        r156.setText(LocaleController.getString(R.string.ArticleAlignment));
        boolean r22 = r6;
        r156.setTextSize(1, 12.0f);
        r156.setGravity(17);
        r156.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        r52.addView(r156, LayoutHelper.createLinear(-1, -2, 0.0f, 5.0f, 0.0f, 4.0f));
        LinearLayout r7 = new LinearLayout(getContext());
        int r25 = r8;
        int r26 = r9;
        r7.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        r7.setOrientation(0);
        r52.addView(r7, LayoutHelper.createLinear(-2, -2, 17));
        RichEditor.Button r82 = new RichEditor.Button(getContext(), R.drawable.iv_align_horiz_left, this.resourcesProvider).setRoundRadius(4).setAccent(false);
        int r92 = Theme.key_actionBarDefaultSubmenuBackground;
        RichEditor.Button r83 = r82.setBackgroundColorKey(r92);
        r7.addView(r83, LayoutHelper.createLinear(32, 32));
        RichEditor.Button r62 = new RichEditor.Button(getContext(), R.drawable.iv_align_horiz_middle, this.resourcesProvider).setRoundRadius(4).setAccent(false).setBackgroundColorKey(r92);
        r7.addView(r62, LayoutHelper.createLinear(32, 32));
        RichEditor.Button r112 = new RichEditor.Button(getContext(), R.drawable.iv_align_horiz_right, this.resourcesProvider).setRoundRadius(4).setAccent(false).setBackgroundColorKey(r92);
        RichEditor.Button[] r63 = {r83, r62, r112};
        r7.addView(r112, LayoutHelper.createLinear(32, 32));
        r7.addView(new Space(getContext()), LayoutHelper.createLinear(8, 0));
        RichEditor.Button r84 = new RichEditor.Button(getContext(), R.drawable.iv_align_vert_top, this.resourcesProvider).setRoundRadius(4).setAccent(false).setBackgroundColorKey(r92);
        r7.addView(r84, LayoutHelper.createLinear(32, 32));
        boolean r192 = r132;
        RichEditor.Button r102 = new RichEditor.Button(getContext(), R.drawable.iv_align_vert_middle, this.resourcesProvider).setRoundRadius(4).setAccent(false).setBackgroundColorKey(r92);
        r7.addView(r102, LayoutHelper.createLinear(32, 32));
        int r23 = r14;
        RichEditor.Button r93 = new RichEditor.Button(getContext(), R.drawable.iv_align_vert_bottom, this.resourcesProvider).setRoundRadius(4).setAccent(false).setBackgroundColorKey(r92);
        RichEditor.Button[] r85 = {r84, r102, r93};
        r7.addView(r93, LayoutHelper.createLinear(32, 32));
        r34.addView(r52);
        int r53 = r31.commonHorizontalAlign();
        int r72 = r31.commonVerticalAlign();
        int r94 = 0;
    L72:
        if (r94 >= 3) goto L78;
        RichEditor.Button r113 = r63[r94];
        if (r94 != r53) goto L76;
        boolean r122 = true;
    L77:
        r113.setSelected(r122);
        r94 = r94 + 1;
        goto L72
    L76:
        r122 = false;
        goto L77
    L78:
        int r54 = 0;
    L79:
        if (r54 >= 3) goto L85;
        RichEditor.Button r95 = r85[r54];
        if (r54 != r72) goto L83;
        boolean r114 = true;
    L84:
        r95.setSelected(r114);
        r54 = r54 + 1;
        goto L79
    L83:
        r114 = false;
        goto L84
    L85:
        int r55 = 0;
    L86:
        if (r55 >= 3) goto L88;
        r63[r55].setOnClickListener(new RichEditorListView$$ExternalSyntheticLambda11(r31, r55, r63));
        r55 = r55 + 1;
        goto L86
    L88:
        int r56 = 0;
    L89:
        if (r56 >= 3) goto L91;
        r85[r56].setOnClickListener(new RichEditorListView$$ExternalSyntheticLambda17(r31, r56, r85));
        r56 = r56 + 1;
        goto L89
    L91:
        r34.addSpaceGap();
        r34.setMinWidth(Opcodes.ADD_INT_LIT16);
        boolean r57 = r31.allSelectedHeader();
        if (r57 == false) goto L94;
        String r42 = LocaleController.getString(R.string.ArticleRemoveHighlight);
    L107:
        if (r57 == false) goto L109;
        int r64 = R.drawable.iv_table_highlight_remove;
    L110:
        r34.add(r64, r42, new RichEditorListView$$ExternalSyntheticLambda18(this, r31, r57));
        if (r25 == 0) goto L113;
        r34.add(R.drawable.iv_table_merge, LocaleController.getString(R.string.ArticleMergeCells), new RichEditorListView$$ExternalSyntheticLambda19(this, r31));
    L113:
        if (r26 == 0) goto L115;
        r34.add(R.drawable.iv_table_unmerge, LocaleController.getString(R.string.ArticleSplitCells), new RichEditorListView$$ExternalSyntheticLambda20(this, r31));
    L115:
        if (r22 == false) goto L117;
        r34.add(R.drawable.iv_table_insert_left, LocaleController.getString(R.string.ArticleInsertLeft), new RichEditorListView$$ExternalSyntheticLambda21(this, r31));
        r34.add(R.drawable.iv_table_insert_right, LocaleController.getString(R.string.ArticleInsertRight), new RichEditorListView$$ExternalSyntheticLambda22(this, r31));
    L117:
        if (r192 == false) goto L119;
        r34.add(R.drawable.iv_table_insert_top, LocaleController.getString(R.string.ArticleInsertAbove), new RichEditorListView$$ExternalSyntheticLambda23(this, r31));
        r34.add(R.drawable.iv_table_insert_bottom, LocaleController.getString(R.string.ArticleInsertBelow), new RichEditorListView$$ExternalSyntheticLambda24(this, r31));
    L119:
        if (r21 == 0) goto L121;
        boolean r73 = true;
        r34.add(R.drawable.iv_table_remove, LocaleController.getString(R.string.ArticleDeleteColumn), true, new RichEditorListView$$ExternalSyntheticLambda25(this, r31));
    L122:
        if (r23 == 0) goto L124;
        r34.add(R.drawable.iv_table_remove, LocaleController.getString(R.string.ArticleDeleteRow), r73, new RichEditorListView$$ExternalSyntheticLambda12(this, r31));
    L124:
        if (r12 == false) goto L126;
        r34.add(R.drawable.iv_table_remove, LocaleController.getString(R.string.ArticleDeleteTable), r73, new RichEditorListView$$ExternalSyntheticLambda13(this, r31));
        r34.addSpaceGap();
        r34.addChecked(r2.block.bordered, LocaleController.getString(R.string.ArticleTableBordered), new RichEditorListView$$ExternalSyntheticLambda14(r34, r2, r31, r4));
        r34.addChecked(r2.block.compact, LocaleController.getString(R.string.ArticleTableCompact), new RichEditorListView$$ExternalSyntheticLambda15(r34, r2, r31, r4));
        ActionBarMenuSubItem[] r43 = {r34.getLast(), r34.getLast()};
    L126:
        r34.setOnDismiss(new RichEditorListView$$ExternalSyntheticLambda16(this, r34, r31));
        this.tableCellMenu = r34;
        r34.show();
        return;
    L121:
        r73 = true;
        goto L122
    L109:
        r64 = R.drawable.iv_table_highlight;
        goto L110
    L94:
        if (r12 == false) goto L96;
    L106:
        r42 = LocaleController.getString(R.string.ArticleHighlightCells);
        goto L107
    L96:
        if (r4 <= 1) goto L100;
        if (r11 == true) goto L100;
        if (r10 == false) goto L106;
    L100:
        if (r11 == false) goto L103;
        int r44 = R.string.ArticleHighlightColumn;
    L102:
        r42 = LocaleController.getString(r44);
        goto L107
    L103:
        if (r10 == false) goto L105;
        r44 = R.string.ArticleHighlightRow;
        goto L102
    L105:
        r44 = R.string.ArticleHighlightCell;
        goto L102
    L66:
        r19 = 0;
        goto L67
    L62:
        r18 = 0;
        goto L63
    L58:
        r172 = 0;
        goto L59
    L54:
        r17 = 0;
    L50:
        r132 = false;
    L46:
        r6 = false;
        r163 = r162;
    L45:
        r162 = true;
    L39:
        r32 = 0;
    L33:
        r14 = 0;
        goto L34
    L22:
        if (r2.rowCount == 1) goto L24;
    L26:
        r13 = false;
        goto L28
    L24:
        if (r2.colCount != 1) goto L26;
    L16:
        if (computeHasSpan(r3.iterator().next()) == false) goto L18;
        r9 = 1;
        goto L19
    L11:
        if (computeCanMerge(r2, r3) == false) goto L13;
        r8 = 1;
        goto L14
    }

    public RichEditText tableEditText(int r1, int r2) {
        View r0 = selectableAt(r1);
        if ((r0 instanceof RichTableCell) == true) goto L7;
        return null;
    L7:
        return ((RichTableCell) r0).editTextForChildPos(r2);
    }

    public CharSequence toSimpleMessage() {
        return RichMessageConvert.rowsToSimpleMessage(this.rows);
    }

    public void toggleQuoteOnSelection() {
        int[] r0 = selectedRowRange();
        if (r0 == null) goto L73;
        int r2 = r0[0];
        int r02 = r0[1];
        if (r2 >= 0) goto L8;
        return;
    L8:
        if (r02 >= this.rows.size()) goto L72;
        if (r2 > r02) goto L74;
        RichEditorHistory r4 = this.history;
        if (r4 == null) goto L15;
        r4.flush();
    L15:
        if (isRangeQuoted(r2, r02) == false) goto L30;
        int r42 = r2;
    L17:
        if (r42 > r02) goto L47;
        BlockRow r5 = this.rows.get(r42);
        if (r5.detailsEnd == true) goto L29;
        if (isDetailsHeader(r5) == true) goto L29;
        if (RichTextCell.isQuoteBlock(r5.block) == false) goto L27;
        TL_iv.pageBlockParagraph r6 = new TL_iv.pageBlockParagraph();
        r6.text = r5.block.text;
        r5.block = r6;
        goto L29
    L27:
        if (r5.quoteIds.isEmpty() == true) goto L29;
        ArrayList<Long> r52 = r5.quoteIds;
        r52.remove(r52.size() - 1);
    L29:
        r42 = r42 + 1;
    L47:
        collapseSingleBlockQuotes();
        normalizeNestedQuotes();
        renumberAllRuns();
        TextSelectionHelper.ArticleTextSelectionHelper r43 = this.textSelectionHelper;
        if (r43 != null) goto L50;
    L52:
        boolean r44 = false;
    L53:
        ArrayList<BlockRow> r53 = this.rows;
        BlockRow r22 = r53.get(Math.max(0, Math.min(r2, r53.size() - 1)));
        ArrayList<BlockRow> r54 = this.rows;
        BlockRow r03 = r54.get(Math.max(0, Math.min(r02, r54.size() - 1)));
        this.adapter.update(false);
        RichEditorHistory r1 = this.history;
        if (r1 == null) goto L56;
        r1.record();
    L56:
        if (r44 == false) goto L59;
        post(new RichEditorListView$$ExternalSyntheticLambda1(this, r22, r03));
        return;
    L59:
        post(new RichEditorListView$$ExternalSyntheticLambda2(this, r22));
        return;
    L50:
        if (r43.isInSelectionMode() == false) goto L52;
        r44 = true;
        goto L53
    L30:
        long r45 = RichContainer.newId();
        int r62 = r2;
    L31:
        if (r62 > r02) goto L47;
        BlockRow r7 = this.rows.get(r62);
        if (r7.detailsEnd == true) goto L46;
        if (isDetailsHeader(r7) == true) goto L46;
        if (RichTextCell.isQuoteBlock(r7.block) == false) goto L45;
        long r8 = RichContainer.newId();
        TL_iv.RichText r10 = RichTextCell.extractCaption(r7.block);
        if (r10 != null) goto L42;
    L44:
        r7.quoteIds.add(Long.valueOf(r8));
        TL_iv.pageBlockParagraph r82 = new TL_iv.pageBlockParagraph();
        r82.text = r7.block.text;
        r7.block = r82;
        goto L45
    L42:
        if ((r10 instanceof TL_iv.textEmpty) == true) goto L44;
        this.quoteAuthors.put(Long.valueOf(r8), r10);
    L45:
        r7.quoteIds.add(0, Long.valueOf(r45));
    L46:
        r62 = r62 + 1;
        goto L31
    L74:
        return;
    L72:
        return;
    }

    public void turnInto(BlockRow r8, TL_iv.PageBlock r9, int r10, int r11, boolean r12, boolean r13) {
        if (r8 != null) goto L4;
        r8 = turnIntoTarget();
    L4:
        BlockRow r1 = r8;
        if (r1 == null) goto L32;
        if (r9 != null) goto L8;
        return;
    L8:
        if (this.rows.indexOf(r1) >= 0) goto L11;
        return;
    L11:
        if (isDetailsHeader(r1) == false) goto L13;
        return;
    L13:
        if (r1.detailsEnd == true) goto L36;
        TL_iv.PageBlock r82 = r1.block;
        if (r9 != r82) goto L18;
    L30:
        transformRow(r1, r9, r10, r11, r12, r13);
        return;
    L18:
        if (isFormattable(r82) == false) goto L30;
        if (isFormattable(r9) == false) goto L30;
        RichTextCell r83 = cellAt(this.rows.indexOf(r1));
        if (r83 == null) goto L24;
        CharSequence r84 = r83.getEditText().getText();
    L25:
        RichTextCell.applyStyledTextToBlock(r9, r84);
        TL_iv.RichText r85 = RichTextCell.extractCaption(r1.block);
        if (r85 == null) goto L30;
        if (RichTextCell.extractCaption(r9) == null) goto L30;
        RichTextCell.setCaption(r9, r85);
        goto L30
    L24:
        r84 = RichTextCell.readStyledText(r1.block);
        goto L25
    L36:
        return;
    }

    public void turnIntoKeepList(BlockRow r8, TL_iv.PageBlock r9) {
        if (r8 != null) goto L4;
        r8 = turnIntoTarget();
    L4:
        BlockRow r1 = r8;
        if (r1 != null) goto L7;
        return;
    L7:
        turnInto(r1, r9, r1.level, r1.num, r1.checkbox, r1.checked);
    }

    public void turnIntoList(BlockRow r8, int r9) {
        if (r8 != null) goto L4;
        r8 = turnIntoTarget();
    L4:
        BlockRow r1 = r8;
        if (r1 != null) goto L7;
        return;
    L7:
        if (r9 != 0) goto L10;
        turnInto(r1, r1.block, 0, 0, false, false);
        return;
    L10:
        int r3 = Math.max(1, r1.level);
        if (r9 != 2) goto L13;
        int r4 = Math.max(1, r1.num);
    L15:
        if (r9 != 3) goto L17;
        boolean r5 = true;
    L18:
        TL_iv.PageBlock r7 = r1.block;
        if ((r7 instanceof TL_iv.pageBlockParagraph) == true) goto L22;
        r7 = new TL_iv.pageBlockParagraph();
    L22:
        if (r5 == true) goto L24;
    L27:
        boolean r6 = false;
    L28:
        turnInto(r1, r7, r3, r4, r5, r6);
        return;
    L24:
        if (r1.checked == false) goto L27;
        r6 = true;
        goto L28
    L17:
        r5 = false;
        goto L18
    L13:
        r4 = 0;
        goto L15
    }

    public void undo() {
        RichEditorHistory r0 = this.history;
        if (r0 == null) goto L6;
        r0.undo();
        return;
    }
}
