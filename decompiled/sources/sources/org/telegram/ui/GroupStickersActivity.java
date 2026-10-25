package org.telegram.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.StickerSetCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.StickersAlert;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.VerticalPositionAutoAnimator;

/* loaded from: classes6.dex */
public class GroupStickersActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private AddEmojiCell addEmojiCell;
    private int addEmojiPackHintRow;
    private int addEmojiPackRow;
    private int addEmojiPackTitleRow;
    private final long chatId;
    private int currentEmojiPackRow;
    private FrameLayout emptyFrameView;
    private StickerEmptyView emptyView;
    private int headerRow;
    private TLRPC.ChatFull info;
    private int infoRow;
    private boolean isEmoji;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private FlickerLoadingView loadingView;
    private boolean removeStickerSet;
    private int rowCount;
    private SearchAdapter searchAdapter;
    private ActionBarMenuItem searchItem;
    private boolean searching;
    private TLRPC.TL_messages_stickerSet selectedStickerSet;
    private int selectedStickerSetIndex;
    private int stickersEndRow;
    private int stickersStartRow;

    /* renamed from: org.telegram.ui.GroupStickersActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ GroupStickersActivity this$0;

        public AnonymousClass1(GroupStickersActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupStickersActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ GroupStickersActivity this$0;

        public AnonymousClass2(GroupStickersActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            if (GroupStickersActivity.m15453$$Nest$fgetsearching(this.this$0) == false) goto L6;
            SearchAdapter.m15477$$Nest$monSearchStickers(GroupStickersActivity.m15451$$Nest$fgetsearchAdapter(this.this$0), null);
            GroupStickersActivity.m15460$$Nest$fputsearching(this.this$0, false);
            GroupStickersActivity.m15449$$Nest$fgetlistView(this.this$0).setAdapter(GroupStickersActivity.m15448$$Nest$fgetlistAdapter(this.this$0));
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r2) {
            String r22 = r2.getText().toString();
            SearchAdapter.m15477$$Nest$monSearchStickers(GroupStickersActivity.m15451$$Nest$fgetsearchAdapter(this.this$0), r22);
            boolean r23 = !TextUtils.isEmpty(r22);
            if (r23 == GroupStickersActivity.m15453$$Nest$fgetsearching(this.this$0)) goto L12;
            GroupStickersActivity.m15460$$Nest$fputsearching(this.this$0, r23);
            if (GroupStickersActivity.m15449$$Nest$fgetlistView(this.this$0) == null) goto L13;
            RecyclerListView r24 = GroupStickersActivity.m15449$$Nest$fgetlistView(this.this$0);
            boolean r0 = GroupStickersActivity.m15453$$Nest$fgetsearching(this.this$0);
            GroupStickersActivity r1 = this.this$0;
            if (r0 == false) goto L9;
            RecyclerView.Adapter r12 = GroupStickersActivity.m15451$$Nest$fgetsearchAdapter(r1);
        L10:
            r24.setAdapter(r12);
            return;
        L9:
            r12 = GroupStickersActivity.m15448$$Nest$fgetlistAdapter(r1);
            goto L10
        L13:
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupStickersActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GroupStickersActivity this$0;

        public AnonymousClass3(GroupStickersActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupStickersActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements StickersAlert.StickersAlertCustomButtonDelegate {
        final /* synthetic */ GroupStickersActivity this$0;
        final /* synthetic */ boolean val$isSelected;
        final /* synthetic */ TLRPC.TL_messages_stickerSet val$stickerSet;

        /* renamed from: $r8$lambda$7NaCTqrg81mM4CTPw-8SzBTu1Wc, reason: not valid java name */
        public static /* synthetic */ void m15467$r8$lambda$7NaCTqrg81mM4CTPw8SzBTu1Wc(AnonymousClass4 r0) {
            r0.lambda$onCustomButtonPressed$0();
        }

        public AnonymousClass4(GroupStickersActivity r1, boolean r2, TLRPC.TL_messages_stickerSet r3) {
            this.this$0 = r1;
            this.val$isSelected = r2;
            this.val$stickerSet = r3;
        }

        private /* synthetic */ void lambda$onCustomButtonPressed$0() {
            BulletinFactory.of(this.this$0).createSimpleBulletin(R.raw.done, LocaleController.getString(R.string.GroupsEmojiPackUpdated)).show();
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertCustomButtonDelegate
        public int getCustomButtonColorKey() {
            if (this.val$isSelected == false) goto L5;
            return -1;
        L5:
            return Theme.key_featuredStickers_addButton;
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertCustomButtonDelegate
        public int getCustomButtonRippleColorKey() {
            if (this.val$isSelected == false) goto L5;
            return -1;
        L5:
            return Theme.key_featuredStickers_addButtonPressed;
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertCustomButtonDelegate
        public String getCustomButtonText() {
            boolean r0 = GroupStickersActivity.m15446$$Nest$fgetisEmoji(this.this$0);
            boolean r1 = this.val$isSelected;
            if (r0 == false) goto L9;
            if (r1 == false) goto L6;
            int r12 = R.string.RemoveGroupEmojiPackSet;
        L8:
            return LocaleController.getString(r12);
        L6:
            r12 = R.string.SetAsGroupEmojiPackSet;
            goto L8
        L9:
            if (r1 == false) goto L11;
            int r13 = R.string.RemoveGroupStickerSet;
        L13:
            return LocaleController.getString(r13);
        L11:
            r13 = R.string.SetAsGroupStickerSet;
            goto L13
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertCustomButtonDelegate
        public int getCustomButtonTextColorKey() {
            if (this.val$isSelected == false) goto L7;
            return Theme.key_text_RedBold;
        L7:
            return Theme.key_featuredStickers_buttonText;
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertCustomButtonDelegate
        public boolean onCustomButtonPressed() {
            int r0 = GroupStickersActivity.m15447$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition();
            RecyclerListView.Holder r1 = (RecyclerListView.Holder) GroupStickersActivity.m15449$$Nest$fgetlistView(this.this$0).findViewHolderForAdapterPosition(r0);
            if (r1 == null) goto L5;
            int r12 = r1.itemView.getTop();
        L6:
            int r3 = GroupStickersActivity.m15455$$Nest$fgetselectedStickerSetIndex(this.this$0);
            boolean r4 = this.val$isSelected;
            GroupStickersActivity r5 = this.this$0;
            if (r4 == false) goto L9;
            GroupStickersActivity.m15461$$Nest$fputselectedStickerSet(r5, null);
            GroupStickersActivity.m15459$$Nest$fputremoveStickerSet(this.this$0, true);
        L11:
            if (GroupStickersActivity.m15446$$Nest$fgetisEmoji(this.this$0) == false) goto L13;
            AndroidUtilities.runOnUIThread(new GroupStickersActivity$4$$ExternalSyntheticLambda0(this), 350);
        L13:
            GroupStickersActivity.m15466$$Nest$mupdateSelectedStickerSetIndex(this.this$0);
            GroupStickersActivity r42 = this.this$0;
            GroupStickersActivity.m15465$$Nest$mupdateCurrentPackVisibility(r42, GroupStickersActivity.m15454$$Nest$fgetselectedStickerSet(r42), true);
            if (r3 == (-1)) goto L26;
            if (GroupStickersActivity.m15453$$Nest$fgetsearching(this.this$0) == true) goto L24;
            int r52 = 0;
        L19:
            if (r52 >= GroupStickersActivity.m15449$$Nest$fgetlistView(this.this$0).getChildCount()) goto L24;
            View r8 = GroupStickersActivity.m15449$$Nest$fgetlistView(this.this$0).getChildAt(r52);
            if (GroupStickersActivity.m15449$$Nest$fgetlistView(this.this$0).getChildViewHolder(r8).getAdapterPosition() == (GroupStickersActivity.m15457$$Nest$fgetstickersStartRow(this.this$0) + r3)) goto L22;
            r52 = r52 + 1;
            goto L19
        L22:
            ((StickerSetCell) r8).setChecked(false, true);
        L24:
            GroupStickersActivity.m15448$$Nest$fgetlistAdapter(this.this$0).notifyItemChanged(r3);
        L26:
            if (GroupStickersActivity.m15455$$Nest$fgetselectedStickerSetIndex(this.this$0) != (-1)) goto L28;
        L37:
            if (r12 == Integer.MAX_VALUE) goto L42;
            if (GroupStickersActivity.m15446$$Nest$fgetisEmoji(this.this$0) == true) goto L42;
            GroupStickersActivity.m15447$$Nest$fgetlayoutManager(this.this$0).scrollToPositionWithOffset(r0 + 1, r12);
        L42:
            if (GroupStickersActivity.m15453$$Nest$fgetsearching(this.this$0) == false) goto L44;
            GroupStickersActivity.m15452$$Nest$fgetsearchItem(this.this$0).setSearchFieldText(_UrlKt.FRAGMENT_ENCODE_SET, false);
            GroupStickersActivity.access$000(this.this$0).closeSearchField(true);
        L44:
            return true;
        L28:
            if (GroupStickersActivity.m15453$$Nest$fgetsearching(this.this$0) == true) goto L36;
            int r32 = 0;
        L31:
            if (r32 >= GroupStickersActivity.m15449$$Nest$fgetlistView(this.this$0).getChildCount()) goto L36;
            View r43 = GroupStickersActivity.m15449$$Nest$fgetlistView(this.this$0).getChildAt(r32);
            if (GroupStickersActivity.m15449$$Nest$fgetlistView(this.this$0).getChildViewHolder(r43).getAdapterPosition() == (GroupStickersActivity.m15457$$Nest$fgetstickersStartRow(this.this$0) + GroupStickersActivity.m15455$$Nest$fgetselectedStickerSetIndex(this.this$0))) goto L34;
            r32 = r32 + 1;
            goto L31
        L34:
            ((StickerSetCell) r43).setChecked(true, true);
        L36:
            GroupStickersActivity.m15448$$Nest$fgetlistAdapter(this.this$0).notifyItemChanged(GroupStickersActivity.m15455$$Nest$fgetselectedStickerSetIndex(this.this$0));
            goto L37
        L9:
            GroupStickersActivity.m15461$$Nest$fputselectedStickerSet(r5, this.val$stickerSet);
            GroupStickersActivity.m15459$$Nest$fputremoveStickerSet(this.this$0, false);
            goto L11
        L5:
            r12 = Integer.MAX_VALUE;
            goto L6
        }
    }

    public class AddEmojiCell extends LinearLayout {
        private final EditTextCaption editText;
        private Runnable lastCallback;
        private String lastQuery;
        private boolean needDivider;
        private int reqId;
        private final TextWatcher textWatcher;
        final /* synthetic */ GroupStickersActivity this$0;

        /* renamed from: org.telegram.ui.GroupStickersActivity$AddEmojiCell$1, reason: invalid class name */
        public class AnonymousClass1 implements TextWatcher {
            final /* synthetic */ AddEmojiCell this$1;

            public static /* synthetic */ void $r8$lambda$AfJ97XXuBM3B1Pz0EZyk9LQ2aE0(AnonymousClass1 r0, String r1) {
                r0.lambda$afterTextChanged$2(r1);
            }

            public static /* synthetic */ void $r8$lambda$CPo4Y9zQm4EHR3LJ3cCZvY2xX0k(AnonymousClass1 r0, TLObject r1) {
                r0.lambda$afterTextChanged$0(r1);
            }

            public static /* synthetic */ void $r8$lambda$cK2YCR6DRHtn7IzGYuVycwK4znQ(AnonymousClass1 r0, String r1, TLObject r2, TLRPC.TL_error r3) {
                r0.lambda$afterTextChanged$1(r1, r2, r3);
            }

            public AnonymousClass1(AddEmojiCell r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$afterTextChanged$0(TLObject r1) {
                AddEmojiCell r0 = this.this$1;
                if (r1 == null) goto L6;
                GroupStickersActivity.m15464$$Nest$mselectSetAfterSearch(r0.this$0, (TLRPC.TL_messages_stickerSet) r1);
                return;
            L6:
                GroupStickersActivity.m15464$$Nest$mselectSetAfterSearch(r0.this$0, null);
            }

            private /* synthetic */ void lambda$afterTextChanged$1(String r1, TLObject r2, TLRPC.TL_error r3) {
                if (Objects.equals(AddEmojiCell.m15469$$Nest$fgetlastQuery(this.this$1), r1) == true) goto L5;
                return;
            L5:
                AndroidUtilities.runOnUIThread(new GroupStickersActivity$AddEmojiCell$1$$ExternalSyntheticLambda2(this, r2));
            }

            private /* synthetic */ void lambda$afterTextChanged$2(String r5) {
                AddEmojiCell.m15472$$Nest$fputlastQuery(this.this$1, r5);
                TLRPC.TL_messages_getStickerSet r0 = new TLRPC.TL_messages_getStickerSet();
                TLRPC.TL_inputStickerSetShortName r1 = new TLRPC.TL_inputStickerSetShortName();
                r0.stickerset = r1;
                r1.short_name = r5;
                AddEmojiCell r12 = this.this$1;
                AddEmojiCell.m15473$$Nest$fputreqId(r12, r12.this$0.getConnectionsManager().sendRequest(r0, new GroupStickersActivity$AddEmojiCell$1$$ExternalSyntheticLambda1(this, r5), 66));
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r4) {
                String r42 = r4.toString().trim();
                if (AddEmojiCell.m15470$$Nest$fgetreqId(this.this$1) == 0) goto L6;
                this.this$1.this$0.getConnectionsManager().cancelRequest(AddEmojiCell.m15470$$Nest$fgetreqId(this.this$1), true);
                AddEmojiCell.m15473$$Nest$fputreqId(this.this$1, 0);
            L6:
                if (AddEmojiCell.m15468$$Nest$fgetlastCallback(this.this$1) == null) goto L8;
                AndroidUtilities.cancelRunOnUIThread(AddEmojiCell.m15468$$Nest$fgetlastCallback(this.this$1));
            L8:
                AddEmojiCell.m15472$$Nest$fputlastQuery(this.this$1, null);
                boolean r0 = r42.isEmpty();
                AddEmojiCell r2 = this.this$1;
                if (r0 == false) goto L12;
                GroupStickersActivity.m15464$$Nest$mselectSetAfterSearch(r2.this$0, null);
                return;
            L12:
                GroupStickersActivity$AddEmojiCell$1$$ExternalSyntheticLambda0 r02 = new GroupStickersActivity$AddEmojiCell$1$$ExternalSyntheticLambda0(this, r42);
                AddEmojiCell.m15471$$Nest$fputlastCallback(r2, r02);
                AndroidUtilities.runOnUIThread(r02, 300);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: -$$Nest$fgetlastCallback, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m15468$$Nest$fgetlastCallback(AddEmojiCell r0) {
            return r0.lastCallback;
        }

        /* renamed from: -$$Nest$fgetlastQuery, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m15469$$Nest$fgetlastQuery(AddEmojiCell r0) {
            return r0.lastQuery;
        }

        /* renamed from: -$$Nest$fgetreqId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15470$$Nest$fgetreqId(AddEmojiCell r0) {
            return r0.reqId;
        }

        /* renamed from: -$$Nest$fputlastCallback, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15471$$Nest$fputlastCallback(AddEmojiCell r0, Runnable r1) {
            r0.lastCallback = r1;
        }

        /* renamed from: -$$Nest$fputlastQuery, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15472$$Nest$fputlastQuery(AddEmojiCell r0, String r1) {
            r0.lastQuery = r1;
        }

        /* renamed from: -$$Nest$fputreqId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15473$$Nest$fputreqId(AddEmojiCell r0, int r1) {
            r0.reqId = r1;
        }

        public AddEmojiCell(GroupStickersActivity r11, Context r12) {
            this.this$0 = r11;
            super(r12);
            this.textWatcher = new AnonymousClass1(this);
            TextView r112 = new TextView(r12);
            r112.setTextSize(1, 16.0f);
            r112.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
            r112.setText("t.me/addemoji/");
            EditTextCaption r2 = new EditTextCaption(r12, null);
            this.editText = r2;
            r2.setLines(1);
            r2.setSingleLine(true);
            r2.setInputType(16384);
            r2.setTextSize(1, 16.0f);
            r2.setTextColor(Theme.getColor(Theme.key_chat_messagePanelText));
            r2.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkOut));
            r2.setHighlightColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight));
            int r122 = Theme.key_chat_messagePanelHint;
            r2.setHintColor(Theme.getColor(r122));
            r2.setHintTextColor(Theme.getColor(r122));
            r2.setCursorColor(Theme.getColor(Theme.key_chat_messagePanelCursor));
            r2.setHandlesColor(Theme.getColor(Theme.key_chat_TextSelectionCursor));
            r2.setBackground(null);
            r2.setHint(LocaleController.getString(R.string.AddEmojiPackLinkHint));
            addView(r112, LayoutHelper.createLinear(-2, -2, 16, 20, 0, 0, 0));
            addView(r2, LayoutHelper.createLinear(-1, -2, 16, -4, 0, 0, 0));
            setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            setPadding(0, AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(5.0f));
            setWillNotDraw(false);
        }

        public void bind(boolean r2, TLRPC.TL_messages_stickerSet r3) {
            this.needDivider = r2;
            this.editText.removeTextChangedListener(this.textWatcher);
            if (r3 != null) goto L5;
            this.editText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        L6:
            this.editText.addTextChangedListener(this.textWatcher);
            return;
        L5:
            String r22 = r3.set.short_name;
            this.editText.setText(r22);
            this.editText.setSelection(r22.length());
            goto L6
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onDraw(Canvas r8) {
            if (this.needDivider == false) goto L6;
            r8.drawLine(AndroidUtilities.dp(20.0f), getHeight() - 1, getWidth() - getPaddingRight(), getHeight() - 1, Theme.dividerPaint);
            return;
        }

        public void setNeedDivider(boolean r1) {
            this.needDivider = r1;
            invalidate();
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private final Context mContext;
        final /* synthetic */ GroupStickersActivity this$0;

        /* renamed from: org.telegram.ui.GroupStickersActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends URLSpanNoUnderline {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, String r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
            public void onClick(View r3) {
                MessagesController.getInstance(GroupStickersActivity.access$300(this.this$1.this$0)).openByUserName("stickers", this.this$1.this$0, 1);
            }
        }

        public static /* synthetic */ void $r8$lambda$PDufu0SBxezILqlhjBSFQCGJ2vQ(ListAdapter r0, View r1) {
            r0.lambda$onBindViewHolder$0(r1);
        }

        public ListAdapter(GroupStickersActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(View r1) {
            GroupStickersActivity.m15464$$Nest$mselectSetAfterSearch(this.this$0, null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return GroupStickersActivity.m15450$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 < GroupStickersActivity.m15457$$Nest$fgetstickersStartRow(this.this$0)) goto L7;
            if (r3 >= GroupStickersActivity.m15456$$Nest$fgetstickersEndRow(this.this$0)) goto L7;
        L8:
            return 0;
        L7:
            if (r3 == GroupStickersActivity.m15441$$Nest$fgetcurrentEmojiPackRow(this.this$0)) goto L8;
            if (r3 != GroupStickersActivity.m15443$$Nest$fgetheaderRow(this.this$0)) goto L12;
            return 4;
        L12:
            if (r3 != GroupStickersActivity.m15440$$Nest$fgetaddEmojiPackTitleRow(this.this$0)) goto L15;
            return 4;
        L15:
            if (r3 != GroupStickersActivity.m15445$$Nest$fgetinfoRow(this.this$0)) goto L17;
            return 1;
        L17:
            if (r3 != GroupStickersActivity.m15438$$Nest$fgetaddEmojiPackHintRow(this.this$0)) goto L20;
            return 1;
        L20:
            if (r3 != GroupStickersActivity.m15439$$Nest$fgetaddEmojiPackRow(this.this$0)) goto L23;
            return 5;
        L23:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            int r0 = r9.getItemViewType();
            boolean r1 = true;
            if (r0 == 0) goto L43;
            if (r0 != 1) goto L6;
            int r02 = GroupStickersActivity.m15445$$Nest$fgetinfoRow(this.this$0);
            GroupStickersActivity r12 = this.this$0;
            if (r10 != r02) goto L40;
            if (GroupStickersActivity.m15446$$Nest$fgetisEmoji(r12) == false) goto L31;
            int r102 = R.string.ChooseEmojiPackMy;
        L32:
            String r103 = LocaleController.getString(r102);
            int r13 = r103.indexOf("@stickers");
            if (r13 != (-1)) goto L64;
            ((TextInfoPrivacyCell) r9.itemView).setText(r103);
            return;
        L64:
            SpannableStringBuilder r2 = new SpannableStringBuilder(r103);     // Catch: Exception -> L36
            r2.setSpan(new AnonymousClass1(this, "@stickers"), r13, r13 + 9, 18);     // Catch: Exception -> L36
            ((TextInfoPrivacyCell) r9.itemView).setText(r2);     // Catch: Exception -> L36
            return;
        L36:
            e = move-exception;
            FileLog.e(e);
            ((TextInfoPrivacyCell) r9.itemView).setText(r103);
            return;
        L31:
            r102 = R.string.ChooseStickerSetMy;
            goto L32
        L40:
            if (r10 != GroupStickersActivity.m15438$$Nest$fgetaddEmojiPackHintRow(r12)) goto L68;
            ((TextInfoPrivacyCell) r9.itemView).setText(LocaleController.getString(R.string.AddGroupEmojiPackHint));
            return;
        L68:
            return;
        L6:
            if (r0 != 4) goto L8;
            int r03 = GroupStickersActivity.m15440$$Nest$fgetaddEmojiPackTitleRow(this.this$0);
            View r92 = r9.itemView;
            if (r10 != r03) goto L20;
            ((HeaderCell) r92).setText(LocaleController.getString(R.string.AddEmojiPackHeader));
            return;
        L20:
            HeaderCell r93 = (HeaderCell) r92;
            if (GroupStickersActivity.m15446$$Nest$fgetisEmoji(this.this$0) == false) goto L23;
            int r8 = R.string.ChooseEmojiPackHeader;
        L24:
            r93.setText(LocaleController.getString(r8));
            return;
        L23:
            r8 = R.string.ChooseStickerSetHeader;
            goto L24
        L8:
            if (r0 != 5) goto L69;
            AddEmojiCell r94 = (AddEmojiCell) r9.itemView;
            if (GroupStickersActivity.m15441$$Nest$fgetcurrentEmojiPackRow(this.this$0) > 0) goto L14;
            r1 = false;
        L14:
            r94.bind(r1, GroupStickersActivity.m15454$$Nest$fgetselectedStickerSet(this.this$0));
            return;
        L69:
            return;
        L43:
            StickerSetCell r95 = (StickerSetCell) r9.itemView;
            if (r10 != GroupStickersActivity.m15441$$Nest$fgetcurrentEmojiPackRow(this.this$0)) goto L47;
            r95.setChecked(false, false);
            r95.setStickersSet(GroupStickersActivity.m15454$$Nest$fgetselectedStickerSet(this.this$0), false);
            r95.setDeleteAction(new GroupStickersActivity$ListAdapter$$ExternalSyntheticLambda0(this));
            return;
        L47:
            ArrayList<TLRPC.TL_messages_stickerSet> r04 = MediaDataController.getInstance(GroupStickersActivity.access$200(this.this$0)).getStickerSets(GroupStickersActivity.m15463$$Nest$mgetStickerSetType(this.this$0));
            int r104 = r10 - GroupStickersActivity.m15457$$Nest$fgetstickersStartRow(this.this$0);
            TLRPC.TL_messages_stickerSet r3 = r04.get(r104);
            TLRPC.TL_messages_stickerSet r4 = r04.get(r104);
            if (r104 == (r04.size() - 1)) goto L50;
            boolean r105 = true;
        L51:
            r95.setStickersSet(r4, r105);
            r95.setDeleteAction(null);
            TLRPC.TL_messages_stickerSet r106 = GroupStickersActivity.m15454$$Nest$fgetselectedStickerSet(this.this$0);
            GroupStickersActivity r05 = this.this$0;
            if (r106 == null) goto L55;
            long r42 = GroupStickersActivity.m15454$$Nest$fgetselectedStickerSet(r05).set.id;
        L59:
            if (r3.set.id == r42) goto L62;
            r1 = false;
        L62:
            r95.setChecked(r1, false);
            return;
        L55:
            if (GroupStickersActivity.m15462$$Nest$mgetStickerSet(r05, GroupStickersActivity.m15444$$Nest$fgetinfo(r05)) == null) goto L57;
            GroupStickersActivity r82 = this.this$0;
            r42 = GroupStickersActivity.m15462$$Nest$mgetStickerSet(r82, GroupStickersActivity.m15444$$Nest$fgetinfo(r82)).id;
            goto L59
        L57:
            r42 = 0;
            goto L59
        L50:
            r105 = false;
            goto L51
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L4;
            View r32 = new StickerSetCell(this.mContext, 3);
            r32.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        L11:
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r32);
        L4:
            if (r4 != 1) goto L6;
            r32 = new TextInfoPrivacyCell(this.mContext);
            r32.setBackground(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
            goto L11
        L6:
            if (r4 == 5) goto L8;
            r32 = new HeaderCell(this.mContext);
            r32.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L11
        L8:
            GroupStickersActivity.m15458$$Nest$fputaddEmojiCell(this.this$0, new AddEmojiCell(this.this$0, this.mContext));
            r32 = GroupStickersActivity.m15437$$Nest$fgetaddEmojiCell(this.this$0);
            goto L11
        }
    }

    public class SearchAdapter extends RecyclerListView.SelectionAdapter {
        private Runnable lastCallback;
        private String lastQuery;
        private List<TLRPC.TL_messages_stickerSet> localSearchEntries;
        private Context mContext;
        private int reqId;
        private List<TLRPC.TL_messages_stickerSet> searchEntries;
        final /* synthetic */ GroupStickersActivity this$0;

        public static /* synthetic */ void $r8$lambda$28pG1GL3rPyOIn6BZVHmDxdbeJ4(SearchAdapter r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$onSearchStickers$1(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$IA_kDTC_pNvOitXmZh_vZpwyHLI(SearchAdapter r0, List r1, List r2, String r3) {
            r0.lambda$onSearchStickers$0(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$mK90z8Owk3xNoAtyf-CDSozd8x4, reason: not valid java name */
        public static /* synthetic */ void m15474$r8$lambda$mK90z8Owk3xNoAtyfCDSozd8x4(SearchAdapter r0, String r1) {
            r0.lambda$onSearchStickers$2(r1);
        }

        /* renamed from: -$$Nest$fgetlocalSearchEntries, reason: not valid java name */
        public static /* bridge */ /* synthetic */ List m15475$$Nest$fgetlocalSearchEntries(SearchAdapter r0) {
            return r0.localSearchEntries;
        }

        /* renamed from: -$$Nest$fgetsearchEntries, reason: not valid java name */
        public static /* bridge */ /* synthetic */ List m15476$$Nest$fgetsearchEntries(SearchAdapter r0) {
            return r0.searchEntries;
        }

        /* renamed from: -$$Nest$monSearchStickers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15477$$Nest$monSearchStickers(SearchAdapter r0, String r1) {
            r0.onSearchStickers(r1);
        }

        public SearchAdapter(GroupStickersActivity r1, Context r2) {
            this.this$0 = r1;
            this.searchEntries = new ArrayList();
            this.localSearchEntries = new ArrayList();
            this.mContext = r2;
            setHasStableIds(true);
        }

        private void changeBackgroundColor(String r2) {
            if (GroupStickersActivity.m15446$$Nest$fgetisEmoji(this.this$0) == false) goto L10;
            boolean r22 = TextUtils.isEmpty(r2);
            GroupStickersActivity r0 = this.this$0;
            if (r22 == true) goto L8;
            GroupStickersActivity.m15449$$Nest$fgetlistView(r0).setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            return;
        L8:
            GroupStickersActivity.m15449$$Nest$fgetlistView(r0).setBackground(null);
            return;
        }

        private /* synthetic */ void lambda$onSearchStickers$0(List r1, List r2, String r3) {
            this.searchEntries = r1;
            this.localSearchEntries = r2;
            notifyDataSetChanged();
            GroupStickersActivity.m15442$$Nest$fgetemptyView(this.this$0).title.setVisibility(8);
            GroupStickersActivity.m15442$$Nest$fgetemptyView(this.this$0).subtitle.setText(LocaleController.formatString(R.string.ChooseStickerNoResultsFound, new Object[]{r3}));
            GroupStickersActivity.m15442$$Nest$fgetemptyView(this.this$0).showProgress(false, true);
        }

        private /* synthetic */ void lambda$onSearchStickers$1(String r7, String r8, TLObject r9, TLRPC.TL_error r10) {
            if (Objects.equals(this.lastQuery, r7) == true) goto L6;
            return;
        L6:
            if ((r9 instanceof TLRPC.TL_messages_foundStickerSets) == false) goto L35;
            ArrayList r72 = new ArrayList();
            ArrayList<TLRPC.StickerSetCovered> r92 = ((TLRPC.TL_messages_foundStickerSets) r9).sets;
            int r102 = r92.size();
            int r0 = 0;
            int r1 = 0;
        L8:
            if (r1 >= r102) goto L14;
            TLRPC.StickerSetCovered r2 = r92.get(r1);
            r1 = r1 + 1;
            TLRPC.StickerSetCovered r22 = r2;
            TLRPC.TL_messages_stickerSet r3 = new TLRPC.TL_messages_stickerSet();
            r3.set = r22.set;
            r3.documents = r22.covers;
            if (GroupStickersActivity.m15446$$Nest$fgetisEmoji(this.this$0) == false) goto L13;
            if (r3.set.emojis == false) goto L8;
        L13:
            r72.add(r3);
            goto L8
        L14:
            String r93 = r8.toLowerCase(Locale.ROOT).trim();
            ArrayList r103 = new ArrayList();
            ArrayList<TLRPC.TL_messages_stickerSet> r12 = MediaDataController.getInstance(GroupStickersActivity.access$100(this.this$0)).getStickerSets(GroupStickersActivity.m15463$$Nest$mgetStickerSetType(this.this$0));
            int r23 = r12.size();
        L15:
            if (r0 >= r23) goto L21;
            TLRPC.TL_messages_stickerSet r32 = r12.get(r0);
            r0 = r0 + 1;
            TLRPC.TL_messages_stickerSet r33 = r32;
            String r4 = r33.set.short_name;
            Locale r5 = Locale.ROOT;
            if (r4.toLowerCase(r5).contains(r93) == true) goto L20;
            if (r33.set.title.toLowerCase(r5).contains(r93) == false) goto L15;
        L20:
            r103.add(r33);
            goto L15
        L21:
            AndroidUtilities.runOnUIThread(new GroupStickersActivity$SearchAdapter$$ExternalSyntheticLambda2(this, r72, r103, r8));
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private /* synthetic */ void lambda$onSearchStickers$2(String r4) {
            this.lastQuery = r4;
            if (GroupStickersActivity.m15446$$Nest$fgetisEmoji(this.this$0) == false) goto L5;
            TLRPC.TL_messages_searchEmojiStickerSets r0 = new TLRPC.TL_messages_searchEmojiStickerSets();
            r0.q = r4;
            TLRPC.TL_messages_searchStickerSets r02 = r0;
        L6:
            this.reqId = this.this$0.getConnectionsManager().sendRequest(r02, new GroupStickersActivity$SearchAdapter$$ExternalSyntheticLambda1(this, r4, r4), 66);
            return;
        L5:
            TLRPC.TL_messages_searchStickerSets r03 = new TLRPC.TL_messages_searchStickerSets();
            r03.q = r4;
            r02 = r03;
            goto L6
        }

        @SuppressLint({"NotifyDataSetChanged"})
        private void onSearchStickers(String r5) {
            changeBackgroundColor(r5);
            if (this.reqId == 0) goto L5;
            this.this$0.getConnectionsManager().cancelRequest(this.reqId, true);
            this.reqId = 0;
        L5:
            Runnable r0 = this.lastCallback;
            if (r0 == null) goto L8;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.lastCallback = null;
        L8:
            this.lastQuery = null;
            int r02 = getItemCount();
            if (r02 <= 0) goto L11;
            this.searchEntries.clear();
            this.localSearchEntries.clear();
            notifyItemRangeRemoved(0, r02);
        L11:
            boolean r03 = TextUtils.isEmpty(r5);
            GroupStickersActivity r3 = this.this$0;
            if (r03 == false) goto L15;
            GroupStickersActivity.m15442$$Nest$fgetemptyView(r3).setVisibility(8);
            GroupStickersActivity.m15442$$Nest$fgetemptyView(this.this$0).showProgress(false, true);
            return;
        L15:
            int r04 = GroupStickersActivity.m15442$$Nest$fgetemptyView(r3).getVisibility();
            GroupStickersActivity r32 = this.this$0;
            if (r04 == 0) goto L18;
            GroupStickersActivity.m15442$$Nest$fgetemptyView(r32).setVisibility(0);
            GroupStickersActivity.m15442$$Nest$fgetemptyView(this.this$0).showProgress(true, false);
        L19:
            GroupStickersActivity$SearchAdapter$$ExternalSyntheticLambda0 r05 = new GroupStickersActivity$SearchAdapter$$ExternalSyntheticLambda0(this, r5);
            this.lastCallback = r05;
            AndroidUtilities.runOnUIThread(r05, 300);
            return;
        L18:
            GroupStickersActivity.m15442$$Nest$fgetemptyView(r32).showProgress(true, true);
            goto L19
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return (this.searchEntries.size() + this.localSearchEntries.size()) + (!this.localSearchEntries.isEmpty() ? 1 : 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r3) {
            if (getItemViewType(r3) == 0) goto L5;
            return -1;
        L5:
            if (r3 <= this.searchEntries.size()) goto L7;
            List<TLRPC.TL_messages_stickerSet> r0 = this.localSearchEntries;
        L9:
            if (r3 <= this.searchEntries.size()) goto L12;
            r3 = (r3 - this.searchEntries.size()) - 1;
        L12:
            return r0.get(r3).set.id;
        L7:
            r0 = this.searchEntries;
            goto L9
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (this.searchEntries.size() != r1) goto L6;
            return 1;
        L6:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (getItemViewType(r1.getAdapterPosition()) != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            if (getItemViewType(r9) == 0) goto L5;
            return;
        L5:
            boolean r2 = true;
            if (r9 <= this.searchEntries.size()) goto L8;
            boolean r0 = true;
        L9:
            if (r0 == false) goto L11;
            List<TLRPC.TL_messages_stickerSet> r3 = this.localSearchEntries;
        L12:
            if (r0 == false) goto L14;
            r9 = (r9 - this.searchEntries.size()) - 1;
        L14:
            StickerSetCell r82 = (StickerSetCell) r8.itemView;
            TLRPC.TL_messages_stickerSet r4 = r3.get(r9);
            if (r9 == (r3.size() - 1)) goto L17;
            boolean r92 = true;
        L18:
            r82.setStickersSet(r4, r92, !r0);
            String r93 = this.lastQuery;
            if (r93 == null) goto L21;
            String r94 = r93.toLowerCase(Locale.ROOT);
        L22:
            r82.setSearchQuery(r4, r94, this.this$0.getResourceProvider());
            TLRPC.TL_messages_stickerSet r95 = GroupStickersActivity.m15454$$Nest$fgetselectedStickerSet(this.this$0);
            GroupStickersActivity r02 = this.this$0;
            if (r95 == null) goto L26;
            long r5 = GroupStickersActivity.m15454$$Nest$fgetselectedStickerSet(r02).set.id;
        L30:
            if (r4.set.id == r5) goto L33;
            r2 = false;
        L33:
            r82.setChecked(r2, false);
            return;
        L26:
            if (GroupStickersActivity.m15462$$Nest$mgetStickerSet(r02, GroupStickersActivity.m15444$$Nest$fgetinfo(r02)) == null) goto L28;
            GroupStickersActivity r7 = this.this$0;
            r5 = GroupStickersActivity.m15462$$Nest$mgetStickerSet(r7, GroupStickersActivity.m15444$$Nest$fgetinfo(r7)).id;
            goto L30
        L28:
            r5 = 0;
            goto L30
        L21:
            r94 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L22
        L17:
            r92 = false;
            goto L18
        L11:
            r3 = this.searchEntries;
            goto L12
        L8:
            r0 = false;
            goto L9
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (r10 == 0) goto L8;
            HeaderCell r0 = new HeaderCell(this.mContext, Theme.key_windowBackgroundWhiteGrayText4, 21, 0, 0, false, this.this$0.getResourceProvider());
            Drawable r92 = Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow);
            CombinedDrawable r102 = new CombinedDrawable(new ColorDrawable(this.this$0.getThemedColor(Theme.key_windowBackgroundGray)), r92);
            r102.setFullsize(true);
            r0.setBackground(r102);
            if (GroupStickersActivity.m15446$$Nest$fgetisEmoji(this.this$0) == false) goto L6;
            int r8 = R.string.ChooseStickerMyEmojiPacks;
        L7:
            r0.setText(LocaleController.getString(r8));
            StickerSetCell r02 = r0;
        L9:
            r02.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r02);
        L6:
            r8 = R.string.ChooseStickerMyStickerSets;
            goto L7
        L8:
            StickerSetCell r03 = new StickerSetCell(this.mContext, 3);
            r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r02 = r03;
            goto L9
        }
    }

    public static /* synthetic */ void $r8$lambda$D1F9Qr8yOdmm9Vhy3tWtULDsrXs(GroupStickersActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$saveStickerSet$2(r1, r2);
    }

    /* renamed from: $r8$lambda$KQdGtLg3QRryq_lRm-1SBQAQUvc, reason: not valid java name */
    public static /* synthetic */ void m15436$r8$lambda$KQdGtLg3QRryq_lRm1SBQAQUvc(GroupStickersActivity r0, TLRPC.TL_error r1) {
        r0.lambda$saveStickerSet$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$R_SEBqyPnb1kFfhXBUVrSlJ5OwU(GroupStickersActivity r0, View r1, int r2) {
        r0.lambda$createView$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetaddEmojiCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AddEmojiCell m15437$$Nest$fgetaddEmojiCell(GroupStickersActivity r0) {
        return r0.addEmojiCell;
    }

    /* renamed from: -$$Nest$fgetaddEmojiPackHintRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15438$$Nest$fgetaddEmojiPackHintRow(GroupStickersActivity r0) {
        return r0.addEmojiPackHintRow;
    }

    /* renamed from: -$$Nest$fgetaddEmojiPackRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15439$$Nest$fgetaddEmojiPackRow(GroupStickersActivity r0) {
        return r0.addEmojiPackRow;
    }

    /* renamed from: -$$Nest$fgetaddEmojiPackTitleRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15440$$Nest$fgetaddEmojiPackTitleRow(GroupStickersActivity r0) {
        return r0.addEmojiPackTitleRow;
    }

    /* renamed from: -$$Nest$fgetcurrentEmojiPackRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15441$$Nest$fgetcurrentEmojiPackRow(GroupStickersActivity r0) {
        return r0.currentEmojiPackRow;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m15442$$Nest$fgetemptyView(GroupStickersActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetheaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15443$$Nest$fgetheaderRow(GroupStickersActivity r0) {
        return r0.headerRow;
    }

    /* renamed from: -$$Nest$fgetinfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m15444$$Nest$fgetinfo(GroupStickersActivity r0) {
        return r0.info;
    }

    /* renamed from: -$$Nest$fgetinfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15445$$Nest$fgetinfoRow(GroupStickersActivity r0) {
        return r0.infoRow;
    }

    /* renamed from: -$$Nest$fgetisEmoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15446$$Nest$fgetisEmoji(GroupStickersActivity r0) {
        return r0.isEmoji;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m15447$$Nest$fgetlayoutManager(GroupStickersActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m15448$$Nest$fgetlistAdapter(GroupStickersActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m15449$$Nest$fgetlistView(GroupStickersActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15450$$Nest$fgetrowCount(GroupStickersActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchAdapter m15451$$Nest$fgetsearchAdapter(GroupStickersActivity r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m15452$$Nest$fgetsearchItem(GroupStickersActivity r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15453$$Nest$fgetsearching(GroupStickersActivity r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fgetselectedStickerSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_messages_stickerSet m15454$$Nest$fgetselectedStickerSet(GroupStickersActivity r0) {
        return r0.selectedStickerSet;
    }

    /* renamed from: -$$Nest$fgetselectedStickerSetIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15455$$Nest$fgetselectedStickerSetIndex(GroupStickersActivity r0) {
        return r0.selectedStickerSetIndex;
    }

    /* renamed from: -$$Nest$fgetstickersEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15456$$Nest$fgetstickersEndRow(GroupStickersActivity r0) {
        return r0.stickersEndRow;
    }

    /* renamed from: -$$Nest$fgetstickersStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15457$$Nest$fgetstickersStartRow(GroupStickersActivity r0) {
        return r0.stickersStartRow;
    }

    /* renamed from: -$$Nest$fputaddEmojiCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15458$$Nest$fputaddEmojiCell(GroupStickersActivity r0, AddEmojiCell r1) {
        r0.addEmojiCell = r1;
    }

    /* renamed from: -$$Nest$fputremoveStickerSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15459$$Nest$fputremoveStickerSet(GroupStickersActivity r0, boolean r1) {
        r0.removeStickerSet = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15460$$Nest$fputsearching(GroupStickersActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$fputselectedStickerSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15461$$Nest$fputselectedStickerSet(GroupStickersActivity r0, TLRPC.TL_messages_stickerSet r1) {
        r0.selectedStickerSet = r1;
    }

    /* renamed from: -$$Nest$mgetStickerSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.StickerSet m15462$$Nest$mgetStickerSet(GroupStickersActivity r0, TLRPC.ChatFull r1) {
        return r0.getStickerSet(r1);
    }

    /* renamed from: -$$Nest$mgetStickerSetType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15463$$Nest$mgetStickerSetType(GroupStickersActivity r0) {
        return r0.getStickerSetType();
    }

    /* renamed from: -$$Nest$mselectSetAfterSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15464$$Nest$mselectSetAfterSearch(GroupStickersActivity r0, TLRPC.TL_messages_stickerSet r1) {
        r0.selectSetAfterSearch(r1);
    }

    /* renamed from: -$$Nest$mupdateCurrentPackVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15465$$Nest$mupdateCurrentPackVisibility(GroupStickersActivity r0, TLRPC.TL_messages_stickerSet r1, boolean r2) {
        r0.updateCurrentPackVisibility(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateSelectedStickerSetIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15466$$Nest$mupdateSelectedStickerSetIndex(GroupStickersActivity r0) {
        r0.updateSelectedStickerSetIndex();
    }

    public GroupStickersActivity(long r2) {
        this.selectedStickerSetIndex = -1;
        this.chatId = r2;
    }

    public GroupStickersActivity(long r2, boolean r4) {
        this.selectedStickerSetIndex = -1;
        this.chatId = r2;
        this.isEmoji = r4;
    }

    public static /* synthetic */ ActionBar access$000(GroupStickersActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$100(GroupStickersActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(GroupStickersActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(GroupStickersActivity r0) {
        return r0.currentAccount;
    }

    private TLRPC.StickerSet getStickerSet(TLRPC.ChatFull r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if (this.isEmoji == false) goto L10;
        return r1.emojiset;
    L10:
        return r1.stickerset;
    }

    private int getStickerSetType() {
        if (this.isEmoji == false) goto L6;
        return 5;
    L6:
        return 0;
    }

    private /* synthetic */ void lambda$createView$0(View r5, int r6) {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (this.searching == false) goto L16;
        if (r6 <= SearchAdapter.m15476$$Nest$fgetsearchEntries(this.searchAdapter).size()) goto L12;
        onStickerSetClicked(((StickerSetCell) r5).isChecked(), (TLRPC.TL_messages_stickerSet) SearchAdapter.m15475$$Nest$fgetlocalSearchEntries(this.searchAdapter).get((r6 - SearchAdapter.m15476$$Nest$fgetsearchEntries(this.searchAdapter).size()) - 1), false);
        return;
    L12:
        if (r6 == SearchAdapter.m15476$$Nest$fgetsearchEntries(this.searchAdapter).size()) goto L24;
        onStickerSetClicked(((StickerSetCell) r5).isChecked(), (TLRPC.TL_messages_stickerSet) SearchAdapter.m15476$$Nest$fgetsearchEntries(this.searchAdapter).get(r6), true);
        return;
    L24:
        return;
    L16:
        if (r6 < this.stickersStartRow) goto L21;
        if (r6 >= this.stickersEndRow) goto L21;
        TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(this.currentAccount).getStickerSets(getStickerSetType()).get(r6 - this.stickersStartRow);
        onStickerSetClicked(((StickerSetCell) r5).isChecked(), r0, false);
    L21:
        if (r6 != this.currentEmojiPackRow) goto L25;
        onStickerSetClicked(true, this.selectedStickerSet, false);
        return;
    }

    private /* synthetic */ void lambda$saveStickerSet$1(TLRPC.TL_error r6) {
        if (r6 != null) goto L21;
        TLRPC.TL_messages_stickerSet r62 = this.selectedStickerSet;
        if (r62 != null) goto L7;
        setStickerSet(null);
    L8:
        updateSelectedStickerSetIndex();
        boolean r63 = this.isEmoji;
        TLRPC.ChatFull r1 = this.info;
        if (r63 == false) goto L15;
        if (r1.emojiset == null) goto L13;
        r1.flags2 |= 1024;
    L18:
        MessagesStorage.getInstance(this.currentAccount).updateChatInfo(this.info, false);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoad, new Object[]{this.info, 0, Boolean.TRUE, Boolean.FALSE});
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.groupPackUpdated, new Object[]{Long.valueOf(this.info.id), Boolean.valueOf(this.isEmoji)});
        finishFragment();
        return;
    L13:
        r1.flags2 &= -1025;
        goto L18
    L15:
        if (r1.stickerset != null) goto L17;
        r1.flags |= 256;
        goto L18
    L17:
        r1.flags &= -257;
        goto L18
    L7:
        setStickerSet(r62.set);
        MediaDataController.getInstance(this.currentAccount).putGroupStickerSet(this.selectedStickerSet);
        goto L8
    L21:
        if (getParentActivity() == null) goto L24;
        Toast.makeText(getParentActivity(), LocaleController.getString(R.string.ErrorOccurred) + "\n" + r6.text, 0).show();
        return;
    }

    private /* synthetic */ void lambda$saveStickerSet$2(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new GroupStickersActivity$$ExternalSyntheticLambda2(this, r2));
    }

    private void onStickerSetClicked(boolean r11, TLRPC.TL_messages_stickerSet r12, boolean r13) {
        if (r13 == false) goto L5;
        TLRPC.TL_inputStickerSetShortName r1 = new TLRPC.TL_inputStickerSetShortName();
        r1.short_name = r12.set.short_name;
        TLRPC.TL_inputStickerSetShortName r6 = r1;
    L6:
        Activity r4 = getParentActivity();
        if (r13 == true) goto L9;
        TLRPC.TL_messages_stickerSet r7 = r12;
    L10:
        StickersAlert r3 = new StickersAlert(r4, this, r6, r7, null, false);
        r3.setCustomButtonDelegate(new AnonymousClass4(this, r11, r12));
        AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
        r3.show();
        return;
    L9:
        r7 = null;
        goto L10
    L5:
        r6 = null;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void saveStickerSet() {
        TLRPC.ChatFull r0 = this.info;
        if (r0 != null) goto L5;
        return;
    L5:
        if (getStickerSet(r0) == null) goto L11;
        TLRPC.TL_messages_stickerSet r02 = this.selectedStickerSet;
        if (r02 == null) goto L11;
        if (r02.set.id != getStickerSet(this.info).id) goto L11;
        return;
    L11:
        if (getStickerSet(this.info) != null) goto L16;
        if (this.selectedStickerSet != null) goto L16;
        return;
    L16:
        if (this.isEmoji == false) goto L21;
        TLRPC.TL_channels_setEmojiStickers r03 = new TLRPC.TL_channels_setEmojiStickers();
        r03.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(this.chatId);
        if (this.removeStickerSet == false) goto L20;
        r03.stickerset = new TLRPC.TL_inputStickerSetEmpty();
        TLRPC.TL_channels_setStickers r04 = r03;
    L25:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r04, new GroupStickersActivity$$ExternalSyntheticLambda1(this));
        return;
    L20:
        TLRPC.TL_inputStickerSetID r1 = new TLRPC.TL_inputStickerSetID();
        r03.stickerset = r1;
        TLRPC.StickerSet r2 = this.selectedStickerSet.set;
        r1.id = r2.id;
        r1.access_hash = r2.access_hash;
        r04 = r03;
        goto L25
    L21:
        TLRPC.TL_channels_setStickers r05 = new TLRPC.TL_channels_setStickers();
        r05.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(this.chatId);
        if (this.removeStickerSet == false) goto L24;
        r05.stickerset = new TLRPC.TL_inputStickerSetEmpty();
        r04 = r05;
        goto L25
    L24:
        MessagesController.getEmojiSettings(this.currentAccount).edit().remove("group_hide_stickers_" + this.info.id).apply();
        TLRPC.TL_inputStickerSetID r12 = new TLRPC.TL_inputStickerSetID();
        r05.stickerset = r12;
        TLRPC.StickerSet r22 = this.selectedStickerSet.set;
        r12.id = r22.id;
        r12.access_hash = r22.access_hash;
        r04 = r05;
        goto L25
    }

    private void selectSetAfterSearch(TLRPC.TL_messages_stickerSet r8) {
        int r0 = this.selectedStickerSetIndex;
        int r1 = 0;
        if (r8 == null) goto L5;
        this.selectedStickerSet = r8;
        this.removeStickerSet = false;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.done, LocaleController.getString(R.string.GroupsEmojiPackUpdated)).show();
    L9:
        updateSelectedStickerSetIndex();
        updateCurrentPackVisibility(this.selectedStickerSet, false);
        if (r0 == (-1)) goto L22;
        if (this.searching == true) goto L20;
        int r3 = 0;
    L15:
        if (r3 >= this.listView.getChildCount()) goto L20;
        View r4 = this.listView.getChildAt(r3);
        if (this.listView.getChildViewHolder(r4).getAdapterPosition() == (this.stickersStartRow + r0)) goto L18;
        r3 = r3 + 1;
        goto L15
    L18:
        ((StickerSetCell) r4).setChecked(false, true);
    L20:
        this.listAdapter.notifyItemChanged(this.stickersStartRow + r0);
    L22:
        if (this.selectedStickerSetIndex != (-1)) goto L24;
        return;
    L24:
        if (this.searching == false) goto L26;
    L32:
        this.listAdapter.notifyItemChanged(this.stickersStartRow + this.selectedStickerSetIndex);
        return;
    L26:
        if (r1 >= this.listView.getChildCount()) goto L32;
        View r82 = this.listView.getChildAt(r1);
        if (this.listView.getChildViewHolder(r82).getAdapterPosition() == (this.stickersStartRow + this.selectedStickerSetIndex)) goto L29;
        r1 = r1 + 1;
        goto L26
    L29:
        ((StickerSetCell) r82).setChecked(true, true);
        return;
    L5:
        if (this.selectedStickerSet == null) goto L7;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.done, LocaleController.getString(R.string.GroupsEmojiPackUpdated)).show();
    L7:
        this.selectedStickerSet = null;
        this.removeStickerSet = true;
        goto L9
    }

    private void setStickerSet(TLRPC.StickerSet r2) {
        boolean r0 = this.isEmoji;
        TLRPC.ChatFull r1 = this.info;
        if (r0 == false) goto L6;
        r1.emojiset = r2;
        return;
    L6:
        r1.stickerset = r2;
    }

    private void updateCurrentPackVisibility(TLRPC.TL_messages_stickerSet r5, boolean r6) {
        if (this.isEmoji == true) goto L5;
        return;
    L5:
        int r0 = this.currentEmojiPackRow;
        boolean r1 = true;
        if (r5 != null) goto L8;
        if (r0 > 0) goto L22;
        r1 = false;
    L22:
        this.selectedStickerSet = null;
        if (r1 == false) goto L27;
        this.listAdapter.notifyItemRemoved(r0);
        if (r6 == false) goto L27;
        this.listAdapter.notifyItemChanged(this.addEmojiPackRow);
    L27:
        updateRows(false);
        this.addEmojiCell.setNeedDivider(false);
        return;
    L8:
        if (r0 != (-1)) goto L10;
        boolean r02 = true;
    L11:
        this.selectedStickerSet = r5;
        updateRows(false);
        ListAdapter r52 = this.listAdapter;
        if (r02 == false) goto L14;
        r52.notifyItemInserted(this.currentEmojiPackRow);
    L15:
        if (r6 == false) goto L17;
        this.listAdapter.notifyItemChanged(this.addEmojiPackRow);
    L17:
        this.addEmojiCell.setNeedDivider(true);
        return;
    L14:
        r52.notifyItemChanged(this.currentEmojiPackRow);
        goto L15
    L10:
        r02 = false;
        goto L11
    }

    private void updateRows() {
        updateRows(true);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void updateRows(boolean r4) {
        this.addEmojiPackTitleRow = -1;
        this.addEmojiPackRow = -1;
        this.currentEmojiPackRow = -1;
        this.addEmojiPackHintRow = -1;
        this.rowCount = 0;
        if (this.isEmoji == false) goto L8;
        this.addEmojiPackTitleRow = 0;
        int r2 = 1 + 1;
        this.rowCount = r2;
        this.addEmojiPackRow = 1;
        if (this.selectedStickerSet == null) goto L7;
        this.rowCount = r2 + 1;
        this.currentEmojiPackRow = r2;
    L7:
        int r1 = this.rowCount;
        this.rowCount = r1 + 1;
        this.addEmojiPackHintRow = r1;
    L8:
        ArrayList<TLRPC.TL_messages_stickerSet> r12 = MediaDataController.getInstance(this.currentAccount).getStickerSets(getStickerSetType());
        if (r12.isEmpty() == true) goto L11;
        int r0 = this.rowCount;
        int r22 = r0 + 1;
        this.rowCount = r22;
        this.headerRow = r0;
        this.stickersStartRow = r22;
        this.stickersEndRow = r22 + r12.size();
        this.rowCount += r12.size();
    L12:
        int r02 = this.rowCount;
        this.rowCount = r02 + 1;
        this.infoRow = r02;
        updateSelectedStickerSetIndex();
        if (r4 == false) goto L18;
        ListAdapter r3 = this.listAdapter;
        if (r3 == null) goto L19;
        r3.notifyDataSetChanged();
        return;
    L19:
        return;
    L18:
        return;
    L11:
        this.headerRow = -1;
        this.stickersStartRow = -1;
        this.stickersEndRow = -1;
        goto L12
    }

    private void updateSelectedStickerSetIndex() {
        ArrayList<TLRPC.TL_messages_stickerSet> r0 = MediaDataController.getInstance(this.currentAccount).getStickerSets(getStickerSetType());
        this.selectedStickerSetIndex = -1;
        if (this.removeStickerSet == false) goto L5;
    L4:
        long r4 = 0;
    L12:
        if (r4 == 0) goto L21;
        int r1 = 0;
    L15:
        if (r1 >= r0.size()) goto L24;
        if (r0.get(r1).set.id == r4) goto L18;
        r1 = r1 + 1;
        goto L15
    L18:
        this.selectedStickerSetIndex = r1;
        return;
    L24:
        return;
    L21:
        return;
    L5:
        TLRPC.TL_messages_stickerSet r12 = this.selectedStickerSet;
        if (r12 == null) goto L9;
        r4 = r12.set.id;
        goto L12
    L9:
        if (getStickerSet(this.info) == null) goto L4;
        r4 = getStickerSet(this.info).id;
        goto L12
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        ActionBar r0 = this.actionBar;
        if (this.isEmoji == false) goto L5;
        int r2 = R.string.GroupEmojiPack;
    L6:
        r0.setTitle(LocaleController.getString(r2));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenuItem r02 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search);
        this.searchItem = r02;
        r02.setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem.setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.listAdapter = new ListAdapter(this, r8);
        this.searchAdapter = new SearchAdapter(this, r8);
        FrameLayout r03 = new FrameLayout(r8);
        this.fragmentView = r03;
        r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listView = new RecyclerListView(r8);
        DefaultItemAnimator r22 = new DefaultItemAnimator();
        r22.setDurations(200);
        r22.setSupportsChangeAnimations(true);
        this.listView.setItemAnimator(r22);
        LinearLayoutManager r23 = new LinearLayoutManager(r8);
        this.layoutManager = r23;
        r23.setOrientation(1);
        this.listView.setLayoutManager(this.layoutManager);
        FrameLayout r24 = new FrameLayout(r8);
        this.emptyFrameView = r24;
        r24.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        FlickerLoadingView r25 = new FlickerLoadingView(r8, getResourceProvider());
        this.loadingView = r25;
        r25.setViewType(19);
        this.loadingView.setIsSingleCell(true);
        this.loadingView.setItemsCount((int) Math.ceil(AndroidUtilities.displaySize.y / AndroidUtilities.dpf2(58.0f)));
        this.emptyFrameView.addView(this.loadingView, LayoutHelper.createFrame(-1, -1.0f));
        StickerEmptyView r26 = new StickerEmptyView(r8, this.loadingView, 1);
        this.emptyView = r26;
        VerticalPositionAutoAnimator.attach(r26);
        this.emptyFrameView.addView(this.emptyView);
        r03.addView(this.emptyFrameView);
        this.emptyFrameView.setVisibility(8);
        this.listView.setEmptyView(this.emptyFrameView);
        r03.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new GroupStickersActivity$$ExternalSyntheticLambda0(this));
        this.listView.setOnScrollListener(new AnonymousClass3(this));
        return this.fragmentView;
    L5:
        r2 = R.string.GroupStickers;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.stickersDidLoad) goto L9;
        if (((Integer) r5[0]).intValue() != getStickerSetType()) goto L27;
        updateRows();
        return;
    L27:
        return;
    L9:
        if (r3 != NotificationCenter.chatInfoDidLoad) goto L20;
        TLRPC.ChatFull r32 = (TLRPC.ChatFull) r5[0];
        if (r32.id == this.chatId) goto L13;
        return;
    L13:
        if (this.info == null) goto L15;
    L17:
        this.info = r32;
        updateRows();
        return;
    L15:
        if (getStickerSet(r32) == null) goto L17;
        this.selectedStickerSet = MediaDataController.getInstance(this.currentAccount).getGroupStickerSetById(getStickerSet(r32));
        goto L17
    L20:
        if (r3 != NotificationCenter.groupStickersDidLoad) goto L29;
        long r33 = ((Long) r5[0]).longValue();
        if (getStickerSet(this.info) != null) goto L24;
        return;
    L24:
        if (getStickerSet(this.info).id != r33) goto L31;
        updateRows();
        return;
    L31:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{StickerSetCell.class, TextSettingsCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        ActionBar r3 = this.actionBar;
        int r4 = ThemeDescription.FLAG_BACKGROUND;
        int r19 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r3, r4, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        int r192 = Theme.key_windowBackgroundGrayShadow;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r192));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LINKCOLOR, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        int r33 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r33));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, r192));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{StickerSetCell.class}, new String[]{"textView"}, null, null, null, r33));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{StickerSetCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{StickerSetCell.class}, new String[]{"optionsButton"}, null, null, null, Theme.key_stickers_menuSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{StickerSetCell.class}, new String[]{"optionsButton"}, null, null, null, Theme.key_stickers_menu));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        MediaDataController.getInstance(this.currentAccount).checkStickers(getStickerSetType());
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.stickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.groupStickersDidLoad);
        updateRows();
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.stickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupStickersDidLoad);
        if (this.selectedStickerSet == null) goto L5;
    L8:
        saveStickerSet();
        return;
    L5:
        if (this.removeStickerSet == true) goto L8;
    }

    public void setInfo(TLRPC.ChatFull r2) {
        this.info = r2;
        if (getStickerSet(r2) == null) goto L6;
        this.selectedStickerSet = MediaDataController.getInstance(this.currentAccount).getGroupStickerSetById(getStickerSet(this.info));
        return;
    }
}
