package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.SharedAudioCell;
import org.telegram.ui.Cells.SharedDocumentCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.FilteredSearchView;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PremiumPreviewFragment;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class SearchDownloadsContainer extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    DownloadsAdapter adapter;
    boolean checkingFilesExist;
    private final int currentAccount;
    ArrayList<MessageObject> currentLoadingFiles;
    ArrayList<MessageObject> currentLoadingFilesTmp;
    int downloadingFilesEndRow;
    int downloadingFilesHeader;
    int downloadingFilesStartRow;
    StickerEmptyView emptyView;
    private boolean hasCurrentDownload;
    private boolean ignoreRequestLayout;
    RecyclerItemsEnterAnimator itemsEnterAnimator;
    String lastQueryString;
    Runnable lastSearchRunnable;
    private final FlickerLoadingView loadingView;
    private final FilteredSearchView.MessageHashId messageHashIdTmp;
    Activity parentActivity;
    BaseFragment parentFragment;
    int recentFilesEndRow;
    int recentFilesHeader;
    int recentFilesStartRow;
    ArrayList<MessageObject> recentLoadingFiles;
    ArrayList<MessageObject> recentLoadingFilesTmp;
    public final RecyclerListView recyclerListView;
    int rowCount;
    String searchQuery;
    FilteredSearchView.UiCallback uiCallback;

    /* renamed from: org.telegram.ui.Components.SearchDownloadsContainer$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView {
        final /* synthetic */ SearchDownloadsContainer this$0;

        public AnonymousClass1(SearchDownloadsContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            this.this$0.checkItemsFloodWait();
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchDownloadsContainer$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayoutManager {
        final /* synthetic */ SearchDownloadsContainer this$0;

        public AnonymousClass2(SearchDownloadsContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchDownloadsContainer$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SearchDownloadsContainer this$0;

        public AnonymousClass3(SearchDownloadsContainer r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L5;
            AndroidUtilities.hideKeyboard(this.this$0.parentActivity.getCurrentFocus());
        L5:
            this.this$0.checkItemsFloodWait();
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchDownloadsContainer$4, reason: invalid class name */
    public class AnonymousClass4 extends DiffUtil.Callback {
        final /* synthetic */ SearchDownloadsContainer this$0;
        final /* synthetic */ int val$oldDownloadingFilesEndRow;
        final /* synthetic */ int val$oldDownloadingFilesHeader;
        final /* synthetic */ int val$oldDownloadingFilesStartRow;
        final /* synthetic */ ArrayList val$oldDownloadingLoadingFiles;
        final /* synthetic */ int val$oldRecentFilesEndRow;
        final /* synthetic */ int val$oldRecentFilesHeader;
        final /* synthetic */ int val$oldRecentFilesStartRow;
        final /* synthetic */ ArrayList val$oldRecentLoadingFiles;
        final /* synthetic */ int val$oldRowCount;

        public AnonymousClass4(SearchDownloadsContainer r1, int r2, int r3, int r4, int r5, int r6, ArrayList r7, int r8, int r9, ArrayList r10) {
            this.this$0 = r1;
            this.val$oldRowCount = r2;
            this.val$oldDownloadingFilesHeader = r3;
            this.val$oldRecentFilesHeader = r4;
            this.val$oldDownloadingFilesStartRow = r5;
            this.val$oldDownloadingFilesEndRow = r6;
            this.val$oldDownloadingLoadingFiles = r7;
            this.val$oldRecentFilesStartRow = r8;
            this.val$oldRecentFilesEndRow = r9;
            this.val$oldRecentLoadingFiles = r10;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r1, int r2) {
            return areItemsTheSame(r1, r2);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r5, int r6) {
            if (r5 < 0) goto L15;
            if (r6 < 0) goto L15;
            if (r5 != this.val$oldDownloadingFilesHeader) goto L11;
            if (r6 != this.this$0.downloadingFilesHeader) goto L11;
            return true;
        L11:
            if (r5 != this.val$oldRecentFilesHeader) goto L15;
            if (r6 != this.this$0.recentFilesHeader) goto L15;
            return true;
        L15:
            int r1 = this.val$oldDownloadingFilesStartRow;
            MessageObject r2 = null;
            if (r5 >= r1) goto L18;
        L20:
            int r12 = this.val$oldRecentFilesStartRow;
            if (r5 >= r12) goto L23;
        L25:
            MessageObject r52 = null;
        L26:
            SearchDownloadsContainer r4 = this.this$0;
            int r13 = r4.downloadingFilesStartRow;
            if (r6 >= r13) goto L29;
        L31:
            int r14 = r4.recentFilesStartRow;
            if (r6 >= r14) goto L34;
        L37:
            if (r2 == null) goto L46;
            if (r52 == null) goto L46;
            if (r2.getDocument() == null) goto L46;
            if (r52.getDocument() == null) goto L46;
            if (r2.getDocument().id != r52.getDocument().id) goto L46;
            return true;
        L46:
            return false;
        L34:
            if (r6 >= r4.recentFilesEndRow) goto L37;
            r2 = r4.recentLoadingFiles.get(r6 - r14);
            goto L37
        L29:
            if (r6 >= r4.downloadingFilesEndRow) goto L31;
            r2 = r4.currentLoadingFiles.get(r6 - r13);
            goto L37
        L23:
            if (r5 >= this.val$oldRecentFilesEndRow) goto L25;
            r52 = (MessageObject) this.val$oldRecentLoadingFiles.get(r5 - r12);
            goto L26
        L18:
            if (r5 >= this.val$oldDownloadingFilesEndRow) goto L20;
            r52 = (MessageObject) this.val$oldDownloadingLoadingFiles.get(r5 - r1);
            goto L26
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return this.this$0.rowCount;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.val$oldRowCount;
        }
    }

    public class Cell extends FrameLayout {
        SharedDocumentCell sharedDocumentCell;
        final /* synthetic */ SearchDownloadsContainer this$0;

        public Cell(SearchDownloadsContainer r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            SharedDocumentCell r22 = new SharedDocumentCell(r3, 2);
            this.sharedDocumentCell = r22;
            r22.rightDateTextView.setVisibility(8);
            addView(this.sharedDocumentCell);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            this.sharedDocumentCell.onInitializeAccessibilityNodeInfo(r1);
        }
    }

    public class DownloadsAdapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ SearchDownloadsContainer this$0;

        /* renamed from: org.telegram.ui.Components.SearchDownloadsContainer$DownloadsAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends SharedAudioCell {
            final /* synthetic */ DownloadsAdapter this$1;

            public AnonymousClass1(DownloadsAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.SharedAudioCell
            public boolean needPlayMessage(MessageObject r1) {
                return MediaController.getInstance().playMessage(r1);
            }
        }

        /* renamed from: org.telegram.ui.Components.SearchDownloadsContainer$DownloadsAdapter$2, reason: invalid class name */
        public class AnonymousClass2 implements View.OnClickListener {
            final /* synthetic */ DownloadsAdapter this$1;

            public AnonymousClass2(DownloadsAdapter r1) {
                this.this$1 = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View r5) {
                int r0 = 0;
            L3:
                int r1 = this.this$1.this$0.currentLoadingFiles.size();
                DownloadsAdapter r2 = this.this$1;
                if (r0 >= r1) goto L10;
                MessageObject r12 = r2.this$0.currentLoadingFiles.get(r0);
                if (SearchDownloadsContainer.m12559$$Nest$fgethasCurrentDownload(this.this$1.this$0) == false) goto L8;
                AccountInstance.getInstance(UserConfig.selectedAccount).getFileLoader().cancelLoadFile(r12.getDocument());
            L9:
                r0 = r0 + 1;
                goto L3
            L8:
                AccountInstance.getInstance(UserConfig.selectedAccount).getFileLoader().loadFile(r12.getDocument(), r12, 0, 0);
                DownloadController.getInstance(SearchDownloadsContainer.m12558$$Nest$fgetcurrentAccount(this.this$1.this$0)).updateFilesLoadingPriority();
                goto L9
            L10:
                r2.this$0.update(true);
            }
        }

        public static /* synthetic */ void $r8$lambda$QSQivuTsRsjCBiHk1z187aw1Un4(DownloadsAdapter r0, View r1) {
            r0.lambda$onBindViewHolder$0(r1);
        }

        /* renamed from: -$$Nest$mgetMessage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MessageObject m12561$$Nest$mgetMessage(DownloadsAdapter r0, int r1) {
            return r0.getMessage(r1);
        }

        private DownloadsAdapter(SearchDownloadsContainer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ DownloadsAdapter(SearchDownloadsContainer r1, SearchDownloadsContainerIA r2) {
            this(r1);
        }

        private MessageObject getMessage(int r3) {
            SearchDownloadsContainer r2 = this.this$0;
            int r0 = r2.downloadingFilesStartRow;
            if (r3 >= r0) goto L5;
        L8:
            int r02 = r2.recentFilesStartRow;
            if (r3 >= r02) goto L11;
            return null;
        L11:
            if (r3 < r2.recentFilesEndRow) goto L13;
            return null;
        L13:
            return r2.recentLoadingFiles.get(r3 - r02);
        L5:
            if (r3 >= r2.downloadingFilesEndRow) goto L8;
            return r2.currentLoadingFiles.get(r3 - r0);
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(View r1) {
            SearchDownloadsContainer r0 = this.this$0;
            DownloadsInfoBottomSheet.show(r0.parentActivity, r0.parentFragment);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.rowCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            SearchDownloadsContainer r0 = this.this$0;
            if (r3 != r0.downloadingFilesHeader) goto L5;
            return 0;
        L5:
            if (r3 == r0.recentFilesHeader) goto L17;
            MessageObject r2 = getMessage(r3);
            if (r2 != null) goto L11;
            return 1;
        L11:
            if (r2.isMusic() == false) goto L14;
            return 2;
        L14:
            return 1;
        L17:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 1) goto L5;
        L9:
            return true;
        L5:
            if (r2.getItemViewType() == 2) goto L9;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            int r0 = r9.getItemViewType();
            if (r0 != 0) goto L26;
            GraySectionCell r92 = (GraySectionCell) r9.itemView;
            SearchDownloadsContainer r02 = this.this$0;
            if (r10 != r02.downloadingFilesHeader) goto L23;
            String r102 = LocaleController.getString(R.string.Downloading);
            boolean r03 = r92.getText().equals(r102);
            SearchDownloadsContainer r1 = this.this$0;
            if (r03 == false) goto L16;
            if (SearchDownloadsContainer.m12559$$Nest$fgethasCurrentDownload(r1) == false) goto L12;
            int r103 = R.string.PauseAll;
        L13:
            r92.setRightText(LocaleController.getString(r103), SearchDownloadsContainer.m12559$$Nest$fgethasCurrentDownload(this.this$0));
            return;
        L12:
            r103 = R.string.ResumeAll;
            goto L13
        L16:
            if (SearchDownloadsContainer.m12559$$Nest$fgethasCurrentDownload(r1) == false) goto L19;
            int r04 = R.string.PauseAll;
        L20:
            r92.setText(r102, LocaleController.getString(r04), new AnonymousClass2(this));
            return;
        L19:
            r04 = R.string.ResumeAll;
            goto L20
        L23:
            if (r10 != r02.recentFilesHeader) goto L65;
            r92.setText(LocaleController.getString(R.string.RecentlyDownloaded), LocaleController.getString(R.string.Settings), new SearchDownloadsContainer$DownloadsAdapter$$ExternalSyntheticLambda0(this));
            return;
        L65:
            return;
        L26:
            MessageObject r12 = getMessage(r10);
            if (r12 == null) goto L66;
            boolean r3 = false;
            if (this.this$0.uiCallback.actionModeShowing() == false) goto L35;
            SearchDownloadsContainer r2 = this.this$0;
            if (r10 < r2.downloadingFilesStartRow) goto L35;
            if (r10 >= r2.downloadingFilesEndRow) goto L35;
            boolean r104 = true;
        L36:
            if (r0 != 1) goto L51;
            Cell r93 = (Cell) r9.itemView;
            r93.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            if (r93.sharedDocumentCell.getMessage() != null) goto L40;
            int r05 = 0;
        L41:
            r93.sharedDocumentCell.setDocument(r12, true);
            SearchDownloadsContainer.m12560$$Nest$fgetmessageHashIdTmp(this.this$0).set(r93.sharedDocumentCell.getMessage().getId(), r93.sharedDocumentCell.getMessage().getDialogId());
            SharedDocumentCell r22 = r93.sharedDocumentCell;
            SearchDownloadsContainer r8 = this.this$0;
            boolean r82 = r8.uiCallback.isSelected(SearchDownloadsContainer.m12560$$Nest$fgetmessageHashIdTmp(r8));
            if (r05 != r12.getId()) goto L44;
            boolean r5 = true;
        L45:
            r22.setChecked(r82, r5);
            SharedDocumentCell r83 = r93.sharedDocumentCell;
            if (r05 != r12.getId()) goto L48;
            r3 = true;
        L48:
            r83.showReorderIcon(r104, r3);
            return;
        L44:
            r5 = false;
            goto L45
        L40:
            r05 = r93.sharedDocumentCell.getMessage().getId();
            goto L41
        L51:
            if (r0 != 2) goto L67;
            SharedAudioCell r94 = (SharedAudioCell) r9.itemView;
            if (r94.getMessage() != null) goto L55;
            int r06 = 0;
        L56:
            r94.setMessageObject(r12, true);
            SearchDownloadsContainer.m12560$$Nest$fgetmessageHashIdTmp(this.this$0).set(r94.getMessage().getId(), r94.getMessage().getDialogId());
            SearchDownloadsContainer r84 = this.this$0;
            boolean r85 = r84.uiCallback.isSelected(SearchDownloadsContainer.m12560$$Nest$fgetmessageHashIdTmp(r84));
            if (r06 != r12.getId()) goto L59;
            boolean r23 = true;
        L60:
            r94.setChecked(r85, r23);
            if (r06 != r12.getId()) goto L63;
            r3 = true;
        L63:
            r94.showReorderIcon(r104, r3);
            return;
        L59:
            r23 = false;
            goto L60
        L55:
            r06 = r94.getMessage().getId();
            goto L56
        L67:
            return;
        L35:
            r104 = false;
            goto L36
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L5;
            FrameLayout r1 = new GraySectionCell(r2.getContext());
        L9:
            r1.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r1);
        L5:
            if (r3 != 1) goto L8;
            FrameLayout r32 = new Cell(this.this$0, r2.getContext());
        L7:
            r1 = r32;
            goto L9
        L8:
            r32 = new AnonymousClass1(this, r2.getContext());
            goto L7
        }
    }

    public class TouchHelperCallback extends ItemTouchHelper.Callback {
        final /* synthetic */ SearchDownloadsContainer this$0;

        public TouchHelperCallback(SearchDownloadsContainer r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            super.clearView(r1, r2);
            r2.itemView.setPressed(false);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r3, RecyclerView.ViewHolder r4) {
            if (r4.getAdapterPosition() < this.this$0.downloadingFilesStartRow) goto L9;
            if (r4.getAdapterPosition() >= this.this$0.downloadingFilesEndRow) goto L9;
            return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
        L9:
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return this.this$0.uiCallback.actionModeShowing();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDraw(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
            super.onChildDraw(r1, r2, r3, r4, r5, r6, r7);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r6, RecyclerView.ViewHolder r7, RecyclerView.ViewHolder r8) {
            if (r8.getAdapterPosition() >= this.this$0.downloadingFilesStartRow) goto L5;
        L7:
            return false;
        L5:
            if (r8.getAdapterPosition() >= this.this$0.downloadingFilesEndRow) goto L7;
            int r62 = r7.getAdapterPosition();
            int r72 = r8.getAdapterPosition();
            SearchDownloadsContainer r82 = this.this$0;
            int r0 = r82.downloadingFilesStartRow;
            int r2 = r62 - r0;
            int r3 = r72 - r0;
            r82.currentLoadingFiles.indexOf(Integer.valueOf(r62 - r0));
            SearchDownloadsContainer r83 = this.this$0;
            r83.currentLoadingFiles.get(r62 - r83.downloadingFilesStartRow);
            MessageObject r84 = this.this$0.currentLoadingFiles.get(r2);
            MessageObject r02 = this.this$0.currentLoadingFiles.get(r3);
            this.this$0.currentLoadingFiles.set(r2, r02);
            this.this$0.currentLoadingFiles.set(r3, r84);
            DownloadController.getInstance(SearchDownloadsContainer.m12558$$Nest$fgetcurrentAccount(this.this$0)).swapLoadingPriority(r84, r02);
            this.this$0.adapter.notifyItemMoved(r62, r72);
            goto L7
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r3, int r4) {
            if (r4 == 0) goto L4;
            this.this$0.recyclerListView.cancelClickRunnables(false);
            r3.itemView.setPressed(true);
        L4:
            super.onSelectedChanged(r3, r4);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    public static /* synthetic */ void $r8$lambda$349rmtfuGpMWa8m26220rZFRzQA(SearchDownloadsContainer r0, ArrayList r1, ArrayList r2) {
        r0.lambda$checkFilesExist$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ENvljOKw9JTfuOACUgZTaOU4peM(SearchDownloadsContainer r0, ArrayList r1, String r2, ArrayList r3) {
        r0.lambda$update$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$QjuNj8RMUcf1qnveLoBebvhgOpI(SearchDownloadsContainer r0) {
        r0.lambda$checkFilesExist$3();
    }

    public static /* synthetic */ boolean $r8$lambda$S9Yjgmh9NmJVukNt5tLOBnWK4gI(SearchDownloadsContainer r0, View r1, int r2) {
        return r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YjucyarfIINW67sTSnXvcy4GfpU(SearchDownloadsContainer r0, boolean r1) {
        r0.lambda$showPremiumFloodWaitBulletin$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$e8cwffIjoESQQj8Ck27_nKvO09Y(SearchDownloadsContainer r0, String r1, ArrayList r2, ArrayList r3) {
        r0.lambda$update$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$qtAzkqXmpD1m-1y9eY_Z1qXeUWA, reason: not valid java name */
    public static /* synthetic */ void m12557$r8$lambda$qtAzkqXmpD1m1y9eY_Z1qXeUWA(SearchDownloadsContainer r0, int r1, View r2, int r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12558$$Nest$fgetcurrentAccount(SearchDownloadsContainer r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgethasCurrentDownload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12559$$Nest$fgethasCurrentDownload(SearchDownloadsContainer r0) {
        return r0.hasCurrentDownload;
    }

    /* renamed from: -$$Nest$fgetmessageHashIdTmp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FilteredSearchView.MessageHashId m12560$$Nest$fgetmessageHashIdTmp(SearchDownloadsContainer r0) {
        return r0.messageHashIdTmp;
    }

    public SearchDownloadsContainer(BaseFragment r5, int r6) {
        super(r5.getParentActivity());
        this.adapter = new DownloadsAdapter(this, null);
        this.currentLoadingFiles = new ArrayList();
        this.recentLoadingFiles = new ArrayList();
        this.currentLoadingFilesTmp = new ArrayList();
        this.recentLoadingFilesTmp = new ArrayList();
        this.downloadingFilesHeader = -1;
        this.downloadingFilesStartRow = -1;
        this.downloadingFilesEndRow = -1;
        this.recentFilesHeader = -1;
        this.recentFilesStartRow = -1;
        this.recentFilesEndRow = -1;
        this.messageHashIdTmp = new FilteredSearchView.MessageHashId(0, 0);
        this.parentFragment = r5;
        this.parentActivity = r5.getParentActivity();
        this.currentAccount = r6;
        AnonymousClass1 r0 = new AnonymousClass1(this, getContext());
        this.recyclerListView = r0;
        new ItemTouchHelper(new TouchHelperCallback(this)).attachToRecyclerView(r0);
        addView(r0);
        r0.setLayoutManager(new AnonymousClass2(this, r5.getParentActivity()));
        r0.setAdapter(this.adapter);
        r0.setOnScrollListener(new AnonymousClass3(this));
        DefaultItemAnimator r52 = new DefaultItemAnimator();
        r52.setDelayAnimations(false);
        r52.setSupportsChangeAnimations(false);
        r0.setItemAnimator(r52);
        r0.setOnItemClickListener(new SearchDownloadsContainer$$ExternalSyntheticLambda2(this, r6));
        r0.setOnItemLongClickListener(new SearchDownloadsContainer$$ExternalSyntheticLambda3(this));
        this.itemsEnterAnimator = new RecyclerItemsEnterAnimator(r0, true);
        FlickerLoadingView r53 = new FlickerLoadingView(getContext());
        this.loadingView = r53;
        addView(r53);
        r53.setUseHeaderOffset(true);
        r53.setViewType(3);
        r53.setVisibility(8);
        StickerEmptyView r2 = new StickerEmptyView(getContext(), r53, 1);
        this.emptyView = r2;
        addView(r2);
        r0.setEmptyView(this.emptyView);
        FileLoader.getInstance(r6).getCurrentLoadingFiles(this.currentLoadingFiles);
    }

    private void checkFilesExist() {
        if (this.checkingFilesExist == false) goto L5;
        return;
    L5:
        this.checkingFilesExist = true;
        Utilities.searchQueue.postRunnable(new SearchDownloadsContainer$$ExternalSyntheticLambda1(this));
    }

    private boolean isEmptyDownloads() {
        if (DownloadController.getInstance(this.currentAccount).downloadingFiles.isEmpty() == true) goto L5;
        return false;
    L5:
        if (DownloadController.getInstance(this.currentAccount).recentDownloadingFiles.isEmpty() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$checkFilesExist$2(ArrayList r5, ArrayList r6) {
        int r1 = 0;
    L4:
        if (r1 >= r5.size()) goto L7;
        DownloadController.getInstance(this.currentAccount).onDownloadComplete((MessageObject) r5.get(r1));
        r1 = r1 + 1;
        goto L4
    L7:
        if (r6.isEmpty() == true) goto L9;
        DownloadController.getInstance(this.currentAccount).deleteRecentFiles(r6);
    L9:
        this.checkingFilesExist = false;
        update(true);
    }

    private /* synthetic */ void lambda$checkFilesExist$3() {
        ArrayList<MessageObject> r0 = new ArrayList();
        ArrayList<MessageObject> r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        ArrayList r3 = new ArrayList();
        FileLoader.getInstance(this.currentAccount).getCurrentLoadingFiles(r0);
        FileLoader.getInstance(this.currentAccount).getRecentLoadingFiles(r1);
        int r4 = 0;
        int r5 = 0;
    L4:
        if (r5 >= r0.size()) goto L10;
        if (FileLoader.getInstance(this.currentAccount).getPathToMessage(r0.get(r5).messageOwner).exists() == false) goto L8;
        r2.add(r0.get(r5));
    L8:
        r5 = r5 + 1;
    L10:
        if (r4 >= r1.size()) goto L15;
        if (FileLoader.getInstance(this.currentAccount).getPathToMessage(r1.get(r4).messageOwner).exists() == true) goto L14;
        r3.add(r1.get(r4));
    L14:
        r4 = r4 + 1;
        goto L10
    L15:
        AndroidUtilities.runOnUIThread(new SearchDownloadsContainer$$ExternalSyntheticLambda6(this, r2, r3));
    }

    private /* synthetic */ void lambda$new$0(int r13, View r14, int r15) {
        MessageObject r0 = DownloadsAdapter.m12561$$Nest$mgetMessage(this.adapter, r15);
        if (r0 == null) goto L60;
        boolean r2 = false;
        if (this.uiCallback.actionModeShowing() == false) goto L12;
        this.uiCallback.toggleItemSelection(r0, r14, 0);
        this.messageHashIdTmp.set(r0.getId(), r0.getDialogId());
        this.adapter.notifyItemChanged(r15);
        if (this.uiCallback.actionModeShowing() == true) goto L58;
        DownloadsAdapter r12 = this.adapter;
        r12.notifyItemRangeChanged(0, r12.getItemCount());
        return;
    L58:
        return;
    L12:
        if ((r14 instanceof Cell) == false) goto L55;
        SharedDocumentCell r152 = ((Cell) r14).sharedDocumentCell;
        MessageObject r1 = r152.getMessage();
        TLRPC.Document r3 = r1.getDocument();
        if (r152.isLoaded() == false) goto L50;
        if (r1.isRoundVideo() == false) goto L18;
    L47:
        MediaController.getInstance().playMessage(r1);
        return;
    L18:
        if (r1.isVoice() == true) goto L47;
        boolean r132 = r1.canPreviewDocument();
        if (r132 == true) goto L43;
        TLRPC.Message r153 = r1.messageOwner;
        if (r153 != null) goto L25;
    L27:
        boolean r154 = false;
    L28:
        TLRPC.Chat r4 = null;
        if (r0.messageOwner.peer_id.channel_id == 0) goto L31;
        TLRPC.Chat r32 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r0.messageOwner.peer_id.channel_id));
    L32:
        if (r32 == null) goto L34;
    L37:
        if (r32 == null) goto L39;
        r154 = r32.noforwards;
    L39:
        if (r132 == true) goto L41;
        if (r154 == true) goto L41;
    L42:
        r132 = r2;
    L41:
        r2 = true;
        goto L42
    L34:
        if (r0.messageOwner.peer_id.chat_id == 0) goto L36;
        r4 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r0.messageOwner.peer_id.chat_id));
    L36:
        r32 = r4;
        goto L37
    L31:
        r32 = null;
        goto L32
    L25:
        if (r153.noforwards == false) goto L27;
        r154 = true;
    L43:
        if (r132 == false) goto L46;
        PhotoViewer.getInstance().setParentActivity(this.parentFragment);
        ArrayList<MessageObject> r33 = new ArrayList();
        r33.add(r1);
        PhotoViewer.getInstance().setParentActivity(this.parentFragment);
        PhotoViewer.getInstance().openPhoto(r33, 0, 0, 0, 0, new PhotoViewer.EmptyPhotoViewerProvider());
        return;
    L46:
        AndroidUtilities.openDocument(r1, this.parentActivity, this.parentFragment);
    L53:
        update(true);
        goto L55
    L50:
        if (r152.isLoading() == true) goto L52;
        r0.putInDownloadsStore = true;
        AccountInstance.getInstance(UserConfig.selectedAccount).getFileLoader().loadFile(r3, r0, 0, 0);
        r152.updateFileExistIcon(true);
        DownloadController.getInstance(r13).updateFilesLoadingPriority();
        goto L53
    L52:
        AccountInstance.getInstance(UserConfig.selectedAccount).getFileLoader().cancelLoadFile(r3);
        r152.updateFileExistIcon(true);
    L55:
        if ((r14 instanceof SharedAudioCell) == false) goto L59;
        ((SharedAudioCell) r14).didPressedButton();
        return;
    L59:
        return;
    }

    private /* synthetic */ boolean lambda$new$1(View r4, int r5) {
        MessageObject r52 = DownloadsAdapter.m12561$$Nest$mgetMessage(this.adapter, r5);
        if (r52 != null) goto L5;
        return false;
    L5:
        if (this.uiCallback.actionModeShowing() == true) goto L8;
        this.uiCallback.showActionMode();
        DownloadsAdapter r1 = this.adapter;
        r1.notifyItemRangeChanged(0, r1.getItemCount());
    L8:
        if (this.uiCallback.actionModeShowing() == false) goto L16;
        this.uiCallback.toggleItemSelection(r52, r4, 0);
        if (this.uiCallback.actionModeShowing() == true) goto L12;
        DownloadsAdapter r42 = this.adapter;
        r42.notifyItemRangeChanged(0, r42.getItemCount());
    L12:
        this.messageHashIdTmp.set(r52.getId(), r52.getDialogId());
        return true;
    L16:
        return true;
    }

    private /* synthetic */ void lambda$showPremiumFloodWaitBulletin$6(boolean r2) {
        BaseFragment r1 = this.parentFragment;
        if (r2 == false) goto L5;
        String r22 = "upload_speed";
    L6:
        r1.presentFragment(new PremiumPreviewFragment(r22));
        return;
    L5:
        r22 = "download_speed";
        goto L6
    }

    private /* synthetic */ void lambda$update$4(String r2, ArrayList r3, ArrayList r4) {
        if (r2.equals(this.lastQueryString) == true) goto L5;
        return;
    L5:
        if (this.rowCount != 0) goto L7;
        this.itemsEnterAnimator.showItemsAnimated(0);
    L7:
        updateListInternal(true, r3, r4);
        if (this.rowCount != 0) goto L12;
        this.emptyView.showProgress(false, true);
        this.emptyView.title.setText(LocaleController.getString(R.string.SearchEmptyViewTitle2));
        this.emptyView.subtitle.setVisibility(0);
        this.emptyView.subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
        return;
    }

    private /* synthetic */ void lambda$update$5(ArrayList r8, String r9, ArrayList r10) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        int r3 = 0;
    L4:
        if (r3 >= r8.size()) goto L11;
        String r4 = FileLoader.getDocumentFileName(((MessageObject) r8.get(r3)).getDocument());
        if (r4 == null) goto L10;
        if (r4.toLowerCase().contains(r9) == false) goto L10;
        MessageObject r42 = new MessageObject(this.currentAccount, ((MessageObject) r8.get(r3)).messageOwner, false, false);
        r42.mediaExists = ((MessageObject) r8.get(r3)).mediaExists;
        r42.setQuery(this.searchQuery);
        r0.add(r42);
    L10:
        r3 = r3 + 1;
        goto L4
    L11:
        int r82 = 0;
    L13:
        if (r82 >= r10.size()) goto L20;
        String r32 = FileLoader.getDocumentFileName(((MessageObject) r10.get(r82)).getDocument());
        if (r32 == null) goto L19;
        if (r32.toLowerCase().contains(r9) == false) goto L19;
        MessageObject r33 = new MessageObject(this.currentAccount, ((MessageObject) r10.get(r82)).messageOwner, false, false);
        r33.mediaExists = ((MessageObject) r10.get(r82)).mediaExists;
        r33.setQuery(this.searchQuery);
        r1.add(r33);
    L19:
        r82 = r82 + 1;
        goto L13
    L20:
        AndroidUtilities.runOnUIThread(new SearchDownloadsContainer$$ExternalSyntheticLambda5(this, r9, r0, r1));
    }

    private void updateListInternal(boolean r12, ArrayList<MessageObject> r13, ArrayList<MessageObject> r14) {
        if (r12 == false) goto L21;
        int r3 = this.downloadingFilesHeader;
        int r5 = this.downloadingFilesStartRow;
        int r6 = this.downloadingFilesEndRow;
        int r4 = this.recentFilesHeader;
        int r8 = this.recentFilesStartRow;
        int r9 = this.recentFilesEndRow;
        int r2 = this.rowCount;
        ArrayList r7 = new ArrayList(this.currentLoadingFiles);
        ArrayList r10 = new ArrayList(this.recentLoadingFiles);
        updateRows(r13, r14);
        DiffUtil.calculateDiff(new AnonymousClass4(this, r2, r3, r4, r5, r6, r7, r8, r9, r10)).dispatchUpdatesTo(this.adapter);
        int r11 = 0;
    L5:
        if (r11 >= this.recyclerListView.getChildCount()) goto L20;
        View r122 = this.recyclerListView.getChildAt(r11);
        int r132 = this.recyclerListView.getChildAdapterPosition(r122);
        if (r132 < 0) goto L19;
        RecyclerView.ViewHolder r142 = this.recyclerListView.getChildViewHolder(r122);
        if (r142 == null) goto L19;
        if (r142.shouldIgnore() == true) goto L19;
        if ((r122 instanceof GraySectionCell) == false) goto L17;
        this.adapter.onBindViewHolder(r142, r132);
        goto L19
    L17:
        if ((r122 instanceof Cell) == false) goto L19;
        Cell r123 = (Cell) r122;
        r123.sharedDocumentCell.updateFileExistIcon(true);
        this.messageHashIdTmp.set(r123.sharedDocumentCell.getMessage().getId(), r123.sharedDocumentCell.getMessage().getDialogId());
        r123.sharedDocumentCell.setChecked(this.uiCallback.isSelected(this.messageHashIdTmp), true);
    L19:
        r11 = r11 + 1;
        goto L5
    L20:
        return;
    L21:
        updateRows(r13, r14);
        this.adapter.notifyDataSetChanged();
    }

    private void updateRows(ArrayList<MessageObject> r6, ArrayList<MessageObject> r7) {
        this.currentLoadingFiles.clear();
        int r0 = r6.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        MessageObject r3 = r6.get(r2);
        r2 = r2 + 1;
        MessageObject r32 = r3;
        if (r32.isRoundVideo() == true) goto L3;
        if (r32.isVoice() == true) goto L3;
        this.currentLoadingFiles.add(r32);
        goto L3
    L9:
        this.recentLoadingFiles.clear();
        int r62 = r7.size();
        int r02 = 0;
    L10:
        if (r02 >= r62) goto L16;
        MessageObject r22 = r7.get(r02);
        r02 = r02 + 1;
        MessageObject r23 = r22;
        if (r23.isRoundVideo() == true) goto L10;
        if (r23.isVoice() == true) goto L10;
        this.recentLoadingFiles.add(r23);
        goto L10
    L16:
        this.rowCount = 0;
        this.downloadingFilesHeader = -1;
        this.downloadingFilesStartRow = -1;
        this.downloadingFilesEndRow = -1;
        this.recentFilesHeader = -1;
        this.recentFilesStartRow = -1;
        this.recentFilesEndRow = -1;
        this.hasCurrentDownload = false;
        if (this.currentLoadingFiles.isEmpty() == true) goto L26;
        int r63 = this.rowCount;
        int r72 = r63 + 1;
        this.rowCount = r72;
        this.downloadingFilesHeader = r63;
        this.downloadingFilesStartRow = r72;
        int r73 = r72 + this.currentLoadingFiles.size();
        this.rowCount = r73;
        this.downloadingFilesEndRow = r73;
    L20:
        if (r1 >= this.currentLoadingFiles.size()) goto L26;
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(this.currentLoadingFiles.get(r1).getFileName()) == true) goto L23;
        r1 = r1 + 1;
        goto L20
    L23:
        this.hasCurrentDownload = true;
    L26:
        if (this.recentLoadingFiles.isEmpty() == true) goto L45;
        int r64 = this.rowCount;
        int r74 = r64 + 1;
        this.rowCount = r74;
        this.recentFilesHeader = r64;
        this.recentFilesStartRow = r74;
        int r75 = r74 + this.recentLoadingFiles.size();
        this.rowCount = r75;
        this.recentFilesEndRow = r75;
        return;
    }

    public void checkItemsFloodWait() {
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L6;
        return;
    L6:
        if (this.recyclerListView == null) goto L38;
        int r1 = 0;
    L10:
        if (r1 >= this.recyclerListView.getChildCount()) goto L39;
        View r2 = this.recyclerListView.getChildAt(r1);     // Catch: Exception -> L20
        if ((r2 instanceof Cell) == false) goto L27;
        MessageObject r22 = ((Cell) r2).sharedDocumentCell.getMessage();     // Catch: Exception -> L20
        if (r22 == null) goto L27;
        if (FileLoader.getInstance(this.currentAccount).checkLoadCaughtPremiumFloodWait(r22.getFileName()) == false) goto L23;
        showPremiumFloodWaitBulletin(false);     // Catch: Exception -> L20
    L29:
        return;
    L23:
        if (FileLoader.getInstance(this.currentAccount).checkLoadCaughtPremiumFloodWait(r22.getFileName()) == false) goto L27;
        showPremiumFloodWaitBulletin(true);     // Catch: Exception -> L20
    L27:
        r1 = r1 + 1;
    L20:
        e = move-exception;
        FileLog.e(e);
        goto L27
    L39:
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.onDownloadingFilesChanged) goto L10;
        if (getVisibility() != 0) goto L7;
        DownloadController.getInstance(this.currentAccount).clearUnviewedDownloads();
    L7:
        update(true);
        return;
    L10:
        if (r1 != NotificationCenter.premiumFloodWaitReceived) goto L13;
        checkItemsFloodWait();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.onDownloadingFilesChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.premiumFloodWaitReceived);
        if (getVisibility() != 0) goto L5;
        DownloadController.getInstance(this.currentAccount).clearUnviewedDownloads();
    L5:
        checkFilesExist();
        update(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.onDownloadingFilesChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.premiumFloodWaitReceived);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreRequestLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void search(String r1) {
        this.searchQuery = r1;
        update(false);
    }

    public void setKeyboardHeight(int r1, boolean r2) {
        this.emptyView.setKeyboardHeight(r1, r2);
    }

    public void setPagesPaddings(int r2, int r3) {
        setPagesPaddings(r2, r3, false);
    }

    public void setPagesPaddings(int r8, int r9, boolean r10) {
        setClipToPadding(false);
        this.ignoreRequestLayout = r10;
        setPadding(0, r8, 0, r9);
        this.recyclerListView.setPadding(0, r8, 0, r9, r10);
        ViewGroup.MarginLayoutParams r82 = (ViewGroup.MarginLayoutParams) this.recyclerListView.getLayoutParams();
        r82.topMargin = -r8;
        r82.bottomMargin = -r9;
        this.ignoreRequestLayout = false;
    }

    public void setUiCallback(FilteredSearchView.UiCallback r1) {
        this.uiCallback = r1;
    }

    public void showPremiumFloodWaitBulletin(boolean r9) {
        if (this.parentFragment != null) goto L5;
        return;
    L5:
        if (this.recyclerListView.isAttachedToWindow() == false) goto L34;
        long r0 = System.currentTimeMillis();
        if ((r0 - ConnectionsManager.lastPremiumFloodWaitShown) < (MessagesController.getInstance(this.currentAccount).uploadPremiumSpeedupNotifyPeriod * 1000)) goto L35;
        ConnectionsManager.lastPremiumFloodWaitShown = r0;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L13;
        return;
    L13:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == true) goto L36;
        int r02 = this.currentAccount;
        if (r9 == false) goto L18;
        float r03 = MessagesController.getInstance(r02).uploadPremiumSpeedupUpload;
    L19:
        SpannableString r1 = new SpannableString(Double.toString(Math.round(r03 * 10.0f) / 10.0d).replaceAll("\\.0$", _UrlKt.FRAGMENT_ENCODE_SET));
        r1.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r1.length(), 33);
        if (this.parentFragment.hasStoryViewer() == true) goto L37;
        BulletinFactory r04 = BulletinFactory.of(this.parentFragment);
        int r2 = R.raw.speed_limit;
        if (r9 == false) goto L25;
        int r3 = R.string.UploadSpeedLimited;
    L26:
        String r32 = LocaleController.getString(r3);
        if (r9 == false) goto L29;
        int r5 = R.string.UploadSpeedLimitedMessage;
    L30:
        r04.createSimpleBulletin(r2, r32, AndroidUtilities.replaceCharSequence(TimeModel.NUMBER_FORMAT, AndroidUtilities.premiumText(LocaleController.getString(r5), new SearchDownloadsContainer$$ExternalSyntheticLambda4(this, r9)), r1)).setDuration(8000).show(false);
        return;
    L29:
        r5 = R.string.DownloadSpeedLimitedMessage;
        goto L30
    L25:
        r3 = R.string.DownloadSpeedLimited;
        goto L26
    L37:
        return;
    L18:
        r03 = MessagesController.getInstance(r02).uploadPremiumSpeedupDownload;
        goto L19
    L36:
        return;
    L35:
        return;
    }

    public void update(boolean r8) {
        DownloadsAdapter r0 = this.adapter;
        r0.notifyItemRangeChanged(0, r0.getItemCount());
        if (TextUtils.isEmpty(this.searchQuery) == true) goto L16;
        if (isEmptyDownloads() == true) goto L16;
        this.emptyView.setStickerType(1);
        ArrayList<MessageObject> r02 = new ArrayList();
        ArrayList<MessageObject> r2 = new ArrayList();
        FileLoader.getInstance(this.currentAccount).getCurrentLoadingFiles(r02);
        FileLoader.getInstance(this.currentAccount).getRecentLoadingFiles(r2);
        String r3 = this.searchQuery.toLowerCase();
        boolean r4 = r3.equals(this.lastQueryString);
        this.lastQueryString = r3;
        Utilities.searchQueue.cancelRunnable(this.lastSearchRunnable);
        DispatchQueue r5 = Utilities.searchQueue;
        SearchDownloadsContainer$$ExternalSyntheticLambda0 r6 = new SearchDownloadsContainer$$ExternalSyntheticLambda0(this, r02, r3, r2);
        this.lastSearchRunnable = r6;
        if (r4 == false) goto L10;
        long r22 = 0;
    L11:
        r5.postRunnable(r6, r22);
        this.recentLoadingFilesTmp.clear();
        this.currentLoadingFilesTmp.clear();
        if (r4 == true) goto L36;
        this.emptyView.showProgress(true, true);
        updateListInternal(r8, this.currentLoadingFilesTmp, this.recentLoadingFilesTmp);
        return;
    L36:
        return;
    L10:
        r22 = 300;
    L16:
        if (this.rowCount != 0) goto L19;
        this.itemsEnterAnimator.showItemsAnimated(0);
    L19:
        if (this.checkingFilesExist == false) goto L21;
        this.currentLoadingFilesTmp.clear();
        this.recentLoadingFilesTmp.clear();
    L21:
        FileLoader.getInstance(this.currentAccount).getCurrentLoadingFiles(this.currentLoadingFilesTmp);
        FileLoader.getInstance(this.currentAccount).getRecentLoadingFiles(this.recentLoadingFilesTmp);
        int r03 = 0;
    L23:
        if (r03 >= this.currentLoadingFiles.size()) goto L25;
        this.currentLoadingFiles.get(r03).setQuery(null);
        r03 = r03 + 1;
        goto L23
    L25:
        int r04 = 0;
    L27:
        if (r04 >= this.recentLoadingFiles.size()) goto L29;
        this.recentLoadingFiles.get(r04).setQuery(null);
        r04 = r04 + 1;
        goto L27
    L29:
        this.lastQueryString = null;
        updateListInternal(r8, this.currentLoadingFilesTmp, this.recentLoadingFilesTmp);
        if (this.rowCount != 0) goto L32;
        this.emptyView.showProgress(false, false);
        this.emptyView.title.setText(LocaleController.getString(R.string.SearchEmptyViewDownloads));
        this.emptyView.subtitle.setVisibility(8);
    L32:
        this.emptyView.setStickerType(9);
    }
}
