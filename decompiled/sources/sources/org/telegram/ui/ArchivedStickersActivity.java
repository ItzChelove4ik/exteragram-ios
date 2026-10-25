package org.telegram.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.ArchivedStickerSetCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StickersAlert;

/* loaded from: classes6.dex */
public class ArchivedStickersActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private int archiveInfoRow;
    private int currentType;
    private Runnable doOnTransitionEnd;
    private EmptyTextProgressView emptyView;
    private boolean endReached;
    private boolean firstLoaded;
    private final LongSparseArray<TLRPC.StickerSetCovered> installingStickerSets;
    private boolean isInTransition;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private HashSet<Long> loadedSets;
    private boolean loadingStickers;
    private int rowCount;
    private ArrayList<TLRPC.StickerSetCovered> sets;
    private int stickersEndRow;
    private int stickersLoadingRow;
    private int stickersShadowRow;
    private int stickersStartRow;

    /* renamed from: org.telegram.ui.ArchivedStickersActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ArchivedStickersActivity this$0;

        public AnonymousClass1(ArchivedStickersActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ArchivedStickersActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements StickersAlert.StickersAlertInstallDelegate {
        final /* synthetic */ ArchivedStickersActivity this$0;
        final /* synthetic */ TLRPC.StickerSetCovered val$stickerSet;
        final /* synthetic */ View val$view;

        public AnonymousClass2(ArchivedStickersActivity r1, View r2, TLRPC.StickerSetCovered r3) {
            this.this$0 = r1;
            this.val$view = r2;
            this.val$stickerSet = r3;
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertInstallDelegate
        public void onStickerSetInstalled() {
            ((ArchivedStickerSetCell) this.val$view).setDrawProgress(true, true);
            LongSparseArray r0 = ArchivedStickersActivity.m5973$$Nest$fgetinstallingStickerSets(this.this$0);
            TLRPC.StickerSetCovered r3 = this.val$stickerSet;
            r0.put(r3.set.id, r3);
        }

        @Override // org.telegram.ui.Components.StickersAlert.StickersAlertInstallDelegate
        public void onStickerSetUninstalled() {
        }
    }

    /* renamed from: org.telegram.ui.ArchivedStickersActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ArchivedStickersActivity this$0;

        public AnonymousClass3(ArchivedStickersActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (ArchivedStickersActivity.m5975$$Nest$fgetloadingStickers(this.this$0) == false) goto L5;
            return;
        L5:
            if (ArchivedStickersActivity.m5972$$Nest$fgetendReached(this.this$0) == false) goto L7;
            return;
        L7:
            if (ArchivedStickersActivity.m5974$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition() <= (ArchivedStickersActivity.m5979$$Nest$fgetstickersLoadingRow(this.this$0) - 2)) goto L12;
            ArchivedStickersActivity.m5982$$Nest$mgetStickers(this.this$0);
            return;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ ArchivedStickersActivity this$0;

        /* renamed from: $r8$lambda$4sWeuujqpkjg6L8nA-KYKItk9VA, reason: not valid java name */
        public static /* synthetic */ void m5983$r8$lambda$4sWeuujqpkjg6L8nAKYKItk9VA(ListAdapter r0, TLRPC.StickerSetCovered r1, ArchivedStickerSetCell r2, boolean r3) {
            r0.lambda$onBindViewHolder$0(r1, r2, r3);
        }

        public ListAdapter(ArchivedStickersActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(TLRPC.StickerSetCovered r9, ArchivedStickerSetCell r10, boolean r11) {
            int r0 = 1;
            if (r11 == false) goto L8;
            r10.setChecked(false, false, false);
            if (ArchivedStickersActivity.m5973$$Nest$fgetinstallingStickerSets(this.this$0).indexOfKey(r9.set.id) < 0) goto L7;
            return;
        L7:
            r10.setDrawProgress(true, true);
            ArchivedStickersActivity.m5973$$Nest$fgetinstallingStickerSets(this.this$0).put(r9.set.id, r9);
        L8:
            MediaDataController r1 = MediaDataController.getInstance(ArchivedStickersActivity.access$100(this.this$0));
            Activity r2 = this.this$0.getParentActivity();
            if (r11 == true) goto L11;
        L10:
            int r4 = r0;
            r1.toggleStickerSet(r2, r9, r4, this.this$0, false, false);
            return;
        L11:
            r0 = 2;
            goto L10
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ArchivedStickersActivity.m5976$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 < ArchivedStickersActivity.m5981$$Nest$fgetstickersStartRow(this.this$0)) goto L8;
            if (r3 >= ArchivedStickersActivity.m5978$$Nest$fgetstickersEndRow(this.this$0)) goto L8;
            return 0;
        L8:
            if (r3 != ArchivedStickersActivity.m5979$$Nest$fgetstickersLoadingRow(this.this$0)) goto L12;
            return 1;
        L12:
            if (r3 != ArchivedStickersActivity.m5980$$Nest$fgetstickersShadowRow(this.this$0)) goto L14;
            return 2;
        L14:
            if (r3 == ArchivedStickersActivity.m5970$$Nest$fgetarchiveInfoRow(this.this$0)) goto L19;
            return 0;
        L19:
            return 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            if (getItemViewType(r8) != 0) goto L19;
            int r82 = r8 - ArchivedStickersActivity.m5981$$Nest$fgetstickersStartRow(this.this$0);
            ArchivedStickerSetCell r72 = (ArchivedStickerSetCell) r7.itemView;
            TLRPC.StickerSetCovered r0 = (TLRPC.StickerSetCovered) ArchivedStickersActivity.m5977$$Nest$fgetsets(this.this$0).get(r82);
            boolean r2 = true;
            if (r82 == (ArchivedStickersActivity.m5977$$Nest$fgetsets(this.this$0).size() - 1)) goto L7;
            boolean r83 = true;
        L8:
            r72.setStickersSet(r0, r83);
            boolean r84 = MediaDataController.getInstance(ArchivedStickersActivity.access$000(this.this$0)).isStickerPackInstalled(r0.set.id);
            r72.setChecked(r84, false, false);
            ArchivedStickersActivity r1 = this.this$0;
            if (r84 == false) goto L12;
            ArchivedStickersActivity.m5973$$Nest$fgetinstallingStickerSets(r1).remove(r0.set.id);
            r72.setDrawProgress(false, false);
        L16:
            r72.setOnCheckedChangeListener(new ArchivedStickersActivity$ListAdapter$$ExternalSyntheticLambda0(this, r0));
            return;
        L12:
            if (ArchivedStickersActivity.m5973$$Nest$fgetinstallingStickerSets(r1).indexOfKey(r0.set.id) >= 0) goto L15;
            r2 = false;
        L15:
            r72.setDrawProgress(r2, false);
            goto L16
        L7:
            r83 = false;
            goto L8
        L19:
            if (getItemViewType(r8) != 2) goto L31;
            TextInfoPrivacyCell r73 = (TextInfoPrivacyCell) r7.itemView;
            if (r8 != ArchivedStickersActivity.m5970$$Nest$fgetarchiveInfoRow(this.this$0)) goto L29;
            r73.setTopPadding(17);
            r73.setBottomPadding(10);
            if (ArchivedStickersActivity.m5971$$Nest$fgetcurrentType(this.this$0) != 5) goto L26;
            int r6 = R.string.ArchivedEmojiInfo;
        L27:
            r73.setText(LocaleController.getString(r6));
            return;
        L26:
            r6 = R.string.ArchivedStickersInfo;
            goto L27
        L29:
            r73.setTopPadding(10);
            r73.setBottomPadding(17);
            r73.setText(null);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 == 0) goto L11;
            if (r3 != 1) goto L6;
            FrameLayout r22 = new LoadingCell(this.mContext);
        L9:
            FrameLayout r1 = r22;
        L12:
            r1.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r1);
        L6:
            if (r3 == 2) goto L8;
            r1 = null;
            goto L12
        L8:
            r22 = new TextInfoPrivacyCell(this.mContext);
            goto L9
        L11:
            FrameLayout r32 = new ArchivedStickerSetCell(this.mContext, true);
            r32.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r1 = r32;
            goto L12
        }
    }

    public static /* synthetic */ void $r8$lambda$4Jgu4OnllPallQLBLpKdvSRBf1A(ArchivedStickersActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$getStickers$1(r1, r2);
    }

    /* renamed from: $r8$lambda$BBrDZALfOPMaj-G9kR1iSSRd8jE, reason: not valid java name */
    public static /* synthetic */ void m5968$r8$lambda$BBrDZALfOPMajG9kR1iSSRd8jE(ArchivedStickersActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$getStickers$2(r1, r2);
    }

    /* renamed from: $r8$lambda$WRzsn-eRhv2sV_YgBG78FTFZUn0, reason: not valid java name */
    public static /* synthetic */ void m5969$r8$lambda$WRzsneRhv2sV_YgBG78FTFZUn0(ArchivedStickersActivity r0, View r1, int r2) {
        r0.lambda$createView$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$a5MTcYlrfF6_vBzC3Y_GVBvzfUw(ArchivedStickersActivity r0, TLRPC.TL_messages_archivedStickers r1) {
        r0.lambda$processResponse$3(r1);
    }

    /* renamed from: -$$Nest$fgetarchiveInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5970$$Nest$fgetarchiveInfoRow(ArchivedStickersActivity r0) {
        return r0.archiveInfoRow;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5971$$Nest$fgetcurrentType(ArchivedStickersActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5972$$Nest$fgetendReached(ArchivedStickersActivity r0) {
        return r0.endReached;
    }

    /* renamed from: -$$Nest$fgetinstallingStickerSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m5973$$Nest$fgetinstallingStickerSets(ArchivedStickersActivity r0) {
        return r0.installingStickerSets;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m5974$$Nest$fgetlayoutManager(ArchivedStickersActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetloadingStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5975$$Nest$fgetloadingStickers(ArchivedStickersActivity r0) {
        return r0.loadingStickers;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5976$$Nest$fgetrowCount(ArchivedStickersActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5977$$Nest$fgetsets(ArchivedStickersActivity r0) {
        return r0.sets;
    }

    /* renamed from: -$$Nest$fgetstickersEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5978$$Nest$fgetstickersEndRow(ArchivedStickersActivity r0) {
        return r0.stickersEndRow;
    }

    /* renamed from: -$$Nest$fgetstickersLoadingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5979$$Nest$fgetstickersLoadingRow(ArchivedStickersActivity r0) {
        return r0.stickersLoadingRow;
    }

    /* renamed from: -$$Nest$fgetstickersShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5980$$Nest$fgetstickersShadowRow(ArchivedStickersActivity r0) {
        return r0.stickersShadowRow;
    }

    /* renamed from: -$$Nest$fgetstickersStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5981$$Nest$fgetstickersStartRow(ArchivedStickersActivity r0) {
        return r0.stickersStartRow;
    }

    /* renamed from: -$$Nest$mgetStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5982$$Nest$mgetStickers(ArchivedStickersActivity r0) {
        r0.getStickers();
    }

    public ArchivedStickersActivity(int r2) {
        this.installingStickerSets = new LongSparseArray();
        this.loadedSets = new HashSet();
        this.sets = new ArrayList();
        this.currentType = r2;
    }

    public static /* synthetic */ int access$000(ArchivedStickersActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(ArchivedStickersActivity r0) {
        return r0.currentAccount;
    }

    private void getStickers() {
        if (this.loadingStickers == false) goto L5;
        return;
    L5:
        if (this.endReached == true) goto L30;
        boolean r0 = true;
        this.loadingStickers = true;
        EmptyTextProgressView r1 = this.emptyView;
        if (r1 != null) goto L10;
    L12:
        ListAdapter r12 = this.listAdapter;
        if (r12 == null) goto L15;
        r12.notifyDataSetChanged();
    L15:
        TLRPC.TL_messages_getArchivedStickers r13 = new TLRPC.TL_messages_getArchivedStickers();
        if (this.sets.isEmpty() == false) goto L18;
        long r2 = 0;
    L19:
        r13.offset_id = r2;
        r13.limit = 15;
        int r22 = this.currentType;
        if (r22 != 1) goto L22;
        boolean r4 = true;
    L23:
        r13.masks = r4;
        if (r22 == 5) goto L27;
        r0 = false;
    L27:
        r13.emojis = r0;
        getConnectionsManager().bindRequestToGuid(getConnectionsManager().sendRequest(r13, new ArchivedStickersActivity$$ExternalSyntheticLambda0(this)), this.classGuid);
        return;
    L22:
        r4 = false;
        goto L23
    L18:
        ArrayList<TLRPC.StickerSetCovered> r23 = this.sets;
        r2 = r23.get(r23.size() - 1).set.id;
        goto L19
    L10:
        if (this.firstLoaded == true) goto L12;
        r1.showProgress();
        goto L12
    }

    private /* synthetic */ void lambda$createView$0(View r10, int r11) {
        if (r11 >= this.stickersStartRow) goto L5;
        return;
    L5:
        if (r11 < this.stickersEndRow) goto L7;
        return;
    L7:
        if (getParentActivity() == null) goto L17;
        TLRPC.StickerSetCovered r112 = this.sets.get(r11 - this.stickersStartRow);
        if (r112.set.id == 0) goto L12;
        TLRPC.InputStickerSet r0 = new TLRPC.TL_inputStickerSetID();
        r0.id = r112.set.id;
    L11:
        TLRPC.InputStickerSet r5 = r0;
        r5.access_hash = r112.set.access_hash;
        StickersAlert r2 = new StickersAlert(getParentActivity(), this, r5, null, null, false);
        r2.setInstallDelegate(new AnonymousClass2(this, r10, r112));
        showDialog(r2);
        return;
    L12:
        r0 = new TLRPC.TL_inputStickerSetShortName();
        r0.short_name = r112.set.short_name;
        goto L11
    }

    private /* synthetic */ void lambda$getStickers$1(TLRPC.TL_error r1, TLObject r2) {
        if (r1 != null) goto L5;
        processResponse((TLRPC.TL_messages_archivedStickers) r2);
        return;
    }

    private /* synthetic */ void lambda$getStickers$2(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ArchivedStickersActivity$$ExternalSyntheticLambda2(this, r3, r2));
    }

    private /* synthetic */ void lambda$processResponse$3(TLRPC.TL_messages_archivedStickers r1) {
        processResponse(r1);
    }

    private void processResponse(TLRPC.TL_messages_archivedStickers r9) {
        if (this.isInTransition == true) goto L21;
        ArrayList<TLRPC.StickerSetCovered> r92 = r9.sets;
        int r0 = r92.size();
        int r2 = 0;
        int r3 = 0;
    L5:
        if (r3 >= r0) goto L11;
        TLRPC.StickerSetCovered r4 = r92.get(r3);
        r3 = r3 + 1;
        TLRPC.StickerSetCovered r42 = r4;
        if (this.loadedSets.contains(Long.valueOf(r42.set.id)) == true) goto L5;
        this.loadedSets.add(Long.valueOf(r42.set.id));
        this.sets.add(r42);
        r2 = r2 + 1;
        goto L5
    L11:
        if (r2 > 0) goto L13;
        boolean r02 = true;
    L14:
        this.endReached = r02;
        this.loadingStickers = false;
        this.firstLoaded = true;
        EmptyTextProgressView r93 = this.emptyView;
        if (r93 == null) goto L17;
        r93.showTextView();
    L17:
        updateRows();
        ListAdapter r8 = this.listAdapter;
        if (r8 == null) goto L28;
        r8.notifyDataSetChanged();
        return;
    L28:
        return;
    L13:
        r02 = false;
        goto L14
    L21:
        this.doOnTransitionEnd = new ArchivedStickersActivity$$ExternalSyntheticLambda3(this, r9);
    }

    private void updateRows() {
        this.rowCount = 0;
        if (this.sets.isEmpty() == true) goto L17;
        int r0 = this.currentType;
        if (r0 != 0) goto L7;
    L10:
        int r02 = this.rowCount;
        this.rowCount = r02 + 1;
    L11:
        this.archiveInfoRow = r02;
        int r03 = this.rowCount;
        this.stickersStartRow = r03;
        this.stickersEndRow = r03 + this.sets.size();
        int r04 = this.rowCount + this.sets.size();
        this.rowCount = r04;
        if (this.endReached == true) goto L15;
        this.rowCount = r04 + 1;
        this.stickersLoadingRow = r04;
        this.stickersShadowRow = -1;
        return;
    L15:
        this.rowCount = r04 + 1;
        this.stickersShadowRow = r04;
        this.stickersLoadingRow = -1;
        return;
    L7:
        if (r0 == 5) goto L10;
        r02 = -1;
        goto L11
    L17:
        this.archiveInfoRow = -1;
        this.stickersStartRow = -1;
        this.stickersEndRow = -1;
        this.stickersLoadingRow = -1;
        this.stickersShadowRow = -1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        int r0 = this.currentType;
        if (r0 != 0) goto L5;
        this.actionBar.setTitle(LocaleController.getString(R.string.ArchivedStickers));
    L9:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.listAdapter = new ListAdapter(this, r8);
        FrameLayout r02 = new FrameLayout(r8);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        EmptyTextProgressView r2 = new EmptyTextProgressView(r8);
        this.emptyView = r2;
        if (this.currentType != 0) goto L12;
        r2.setText(LocaleController.getString(R.string.ArchivedStickersEmpty));
    L13:
        r02.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        boolean r22 = this.loadingStickers;
        EmptyTextProgressView r5 = this.emptyView;
        if (r22 == false) goto L16;
        r5.showProgress();
    L17:
        RecyclerListView r23 = new RecyclerListView(r8);
        this.listView = r23;
        r23.setFocusable(true);
        this.listView.setEmptyView(this.emptyView);
        RecyclerListView r24 = this.listView;
        LinearLayoutManager r52 = new LinearLayoutManager(r8, 1, false);
        this.layoutManager = r52;
        r24.setLayoutManager(r52);
        this.listView.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        r02.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new ArchivedStickersActivity$$ExternalSyntheticLambda1(this));
        this.listView.setOnScrollListener(new AnonymousClass3(this));
        return this.fragmentView;
    L16:
        r5.showTextView();
        goto L17
    L12:
        r2.setText(LocaleController.getString(R.string.ArchivedMasksEmpty));
        goto L13
    L5:
        ActionBar r25 = this.actionBar;
        if (r0 != 5) goto L8;
        r25.setTitle(LocaleController.getString(R.string.ArchivedEmojiPacks));
        goto L9
    L8:
        r25.setTitle(LocaleController.getString(R.string.ArchivedMasks));
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r8, int r9, Object... r10) {
        if (r8 != NotificationCenter.needAddArchivedStickers) goto L20;
        ArrayList r82 = new ArrayList((List) r10[0]);
        int r92 = r82.size() - 1;
    L5:
        if (r92 < 0) goto L14;
        int r102 = this.sets.size();
        int r0 = 0;
    L7:
        if (r0 >= r102) goto L12;
        if (this.sets.get(r0).set.id == ((TLRPC.StickerSetCovered) r82.get(r92)).set.id) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        r82.remove(r92);
    L12:
        r92 = r92 - 1;
        goto L5
    L14:
        if (r82.isEmpty() == true) goto L34;
        this.sets.addAll(0, r82);
        updateRows();
        ListAdapter r93 = this.listAdapter;
        if (r93 == null) goto L43;
        r93.notifyItemRangeInserted(this.stickersStartRow, r82.size());
        return;
    L43:
        return;
    L34:
        return;
    L20:
        if (r8 != NotificationCenter.stickersDidLoad) goto L44;
        RecyclerListView r83 = this.listView;
        if (r83 == null) goto L45;
        int r84 = r83.getChildCount();
        int r94 = 0;
    L24:
        if (r94 >= r84) goto L46;
        View r103 = this.listView.getChildAt(r94);
        if ((r103 instanceof ArchivedStickerSetCell) == false) goto L33;
        ArchivedStickerSetCell r104 = (ArchivedStickerSetCell) r103;
        TLRPC.StickerSetCovered r2 = r104.getStickersSet();
        if (r2 == null) goto L33;
        boolean r3 = MediaDataController.getInstance(this.currentAccount).isStickerPackInstalled(r2.set.id);
        if (r3 == false) goto L32;
        this.installingStickerSets.remove(r2.set.id);
        r104.setDrawProgress(false, true);
    L32:
        r104.setChecked(r3, true, false);
    L33:
        r94 = r94 + 1;
        goto L24
    L46:
        return;
    L45:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ArchivedStickerSetCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        EmptyTextProgressView r12 = this.emptyView;
        int r13 = ThemeDescription.FLAG_PROGRESSBAR;
        int r18 = Theme.key_progressCircle;
        r1.add(new ThemeDescription(r12, r13, null, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LoadingCell.class}, new String[]{"progressBar"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ArchivedStickerSetCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ArchivedStickerSetCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        int r20 = Theme.key_featuredStickers_removeButtonText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ArchivedStickerSetCell.class}, new String[]{"deleteButton"}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{ArchivedStickerSetCell.class}, new String[]{"deleteButton"}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ArchivedStickerSetCell.class}, new String[]{"addButton"}, null, null, null, Theme.key_featuredStickers_buttonText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE, new Class[]{ArchivedStickerSetCell.class}, new String[]{"addButton"}, null, null, null, Theme.key_featuredStickers_addButton));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{ArchivedStickerSetCell.class}, new String[]{"addButton"}, null, null, null, Theme.key_featuredStickers_addButtonPressed));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        getStickers();
        updateRows();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.needAddArchivedStickers);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.stickersDidLoad);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.needAddArchivedStickers);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.stickersDidLoad);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        this.isInTransition = false;
        Runnable r12 = this.doOnTransitionEnd;
        if (r12 == null) goto L6;
        r12.run();
        this.doOnTransitionEnd = null;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        this.isInTransition = true;
    }
}
