package org.telegram.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.CacheControlActivity;
import org.telegram.ui.CachedMediaLayout;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NestedSizeNotifierLayout;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StorageDiagramView;
import org.telegram.ui.Storage.CacheModel;

/* loaded from: classes6.dex */
public class DialogCacheBottomSheet extends BottomSheetWithRecyclerListView {
    private CacheControlActivity.ClearCacheButton button;
    private final Delegate cacheDelegate;
    private final CacheModel cacheModel;
    CachedMediaLayout cachedMediaLayout;
    CheckBoxCell[] checkBoxes;
    private final StorageDiagramView circleDiagramView;
    private StorageDiagramView.ClearViewData[] clearViewData;
    long dialogId;
    CacheControlActivity.DialogFileEntities entities;
    LinearLayout linearLayout;

    /* renamed from: org.telegram.ui.DialogCacheBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ DialogCacheBottomSheet this$0;

        public AnonymousClass1(DialogCacheBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (DialogCacheBottomSheet.m14310$$Nest$fgetcacheModel(this.this$0).isEmpty() == false) goto L6;
            return 1;
        L6:
            return 3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return r1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            if (r5 != 0) goto L5;
            View r3 = this.this$0.linearLayout;
        L9:
            return new RecyclerListView.Holder(r3);
        L5:
            if (r5 != 2) goto L7;
            View r42 = this.this$0.cachedMediaLayout;
            RecyclerView.LayoutParams r52 = new RecyclerView.LayoutParams(-1, -2);
            ((ViewGroup.MarginLayoutParams) r52).leftMargin = DialogCacheBottomSheet.access$000(this.this$0);
            ((ViewGroup.MarginLayoutParams) r52).rightMargin = DialogCacheBottomSheet.access$100(this.this$0);
            r42.setLayoutParams(r52);
            r3 = r42;
            goto L9
        L7:
            TextInfoPrivacyCell r32 = new TextInfoPrivacyCell(r4.getContext());
            r32.setFixedSize(12);
            CombinedDrawable r53 = new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray)), Theme.getThemedDrawableByKey(r4.getContext(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
            r53.setFullsize(true);
            r32.setBackgroundDrawable(r53);
            r3 = r32;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.DialogCacheBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends StorageDiagramView {
        final /* synthetic */ DialogCacheBottomSheet this$0;
        final /* synthetic */ Delegate val$delegate;

        public AnonymousClass2(DialogCacheBottomSheet r1, Context r2, long r3, Delegate r5) {
            this.this$0 = r1;
            this.val$delegate = r5;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.StorageDiagramView
        public void onAvatarClick() {
            this.val$delegate.onAvatarClick();
        }
    }

    /* renamed from: org.telegram.ui.DialogCacheBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends CachedMediaLayout {
        final /* synthetic */ DialogCacheBottomSheet this$0;

        public AnonymousClass3(DialogCacheBottomSheet r1, Context r2, BaseFragment r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.CachedMediaLayout, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec((DialogCacheBottomSheet.access$200(this.this$0) - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight, TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.DialogCacheBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 implements CachedMediaLayout.Delegate {
        final /* synthetic */ DialogCacheBottomSheet this$0;
        final /* synthetic */ CacheModel val$cacheModel;

        public AnonymousClass4(DialogCacheBottomSheet r1, CacheModel r2) {
            this.this$0 = r1;
            this.val$cacheModel = r2;
        }

        @Override // org.telegram.ui.CachedMediaLayout.Delegate
        public void clear() {
        }

        @Override // org.telegram.ui.CachedMediaLayout.Delegate
        public void clearSelection() {
        }

        @Override // org.telegram.ui.CachedMediaLayout.Delegate
        public void dismiss() {
            this.this$0.dismiss();
        }

        @Override // org.telegram.ui.CachedMediaLayout.Delegate
        public void onItemSelected(CacheControlActivity.DialogFileEntities r2, CacheModel.FileInfo r3, boolean r4) {
            if (r3 == null) goto L5;
            this.val$cacheModel.toggleSelect(r3);
            this.this$0.cachedMediaLayout.updateVisibleRows();
            DialogCacheBottomSheet.m14312$$Nest$msyncCheckBoxes(this.this$0);
            long r22 = DialogCacheBottomSheet.m14311$$Nest$fgetcircleDiagramView(this.this$0).updateDescription();
            DialogCacheBottomSheet.m14309$$Nest$fgetbutton(this.this$0).setSize(true, r22);
            DialogCacheBottomSheet.m14311$$Nest$fgetcircleDiagramView(this.this$0).update(true);
            return;
        }
    }

    /* renamed from: org.telegram.ui.DialogCacheBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ DialogCacheBottomSheet this$0;

        public AnonymousClass5(DialogCacheBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            DialogCacheBottomSheet r0 = this.this$0;
            if (r0.nestedSizeNotifierLayout == null) goto L6;
            r0.setShowShadow(!r1.isPinnedToTop());
            return;
        }
    }

    public interface Delegate {
        void cleanupDialogFiles(CacheControlActivity.DialogFileEntities r1, StorageDiagramView.ClearViewData[] r2, CacheModel r3);

        void onAvatarClick();
    }

    public static /* synthetic */ void $r8$lambda$0l0BqCQctOPFKqFQlIuWZMpzPjI(DialogCacheBottomSheet r0, CacheModel r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$4Alk4P-k4eGFWFQ4EoubrRUaUDI, reason: not valid java name */
    public static /* synthetic */ void m14307$r8$lambda$4Alk4Pk4eGFWFQ4EoubrRUaUDI(DialogCacheBottomSheet r0, AlertDialog r1, int r2) {
        r0.lambda$createButton$1(r1, r2);
    }

    /* renamed from: $r8$lambda$MAMy-AJUjkrSax1QKcvRX5Kf788, reason: not valid java name */
    public static /* synthetic */ void m14308$r8$lambda$MAMyAJUjkrSax1QKcvRX5Kf788(DialogCacheBottomSheet r0, AlertDialog r1, int r2) {
        r0.lambda$createButton$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$mLY4j0l0To_2pHesC1RFLcqJRXs(DialogCacheBottomSheet r0, View r1) {
        r0.lambda$createButton$3(r1);
    }

    /* renamed from: -$$Nest$fgetbutton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CacheControlActivity.ClearCacheButton m14309$$Nest$fgetbutton(DialogCacheBottomSheet r0) {
        return r0.button;
    }

    /* renamed from: -$$Nest$fgetcacheModel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CacheModel m14310$$Nest$fgetcacheModel(DialogCacheBottomSheet r0) {
        return r0.cacheModel;
    }

    /* renamed from: -$$Nest$fgetcircleDiagramView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StorageDiagramView m14311$$Nest$fgetcircleDiagramView(DialogCacheBottomSheet r0) {
        return r0.circleDiagramView;
    }

    /* renamed from: -$$Nest$msyncCheckBoxes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14312$$Nest$msyncCheckBoxes(DialogCacheBottomSheet r0) {
        r0.syncCheckBoxes();
    }

    public DialogCacheBottomSheet(CacheControlActivity r21, CacheControlActivity.DialogFileEntities r22, CacheModel r23, Delegate r24) {
        super(r21, false, false, !r23.isEmpty(), null);
        int r9 = 8;
        this.clearViewData = new StorageDiagramView.ClearViewData[8];
        this.checkBoxes = new CheckBoxCell[8];
        this.cacheDelegate = r24;
        this.entities = r22;
        this.cacheModel = r23;
        this.dialogId = r22.dialogId;
        boolean r10 = false;
        this.allowNestedScroll = false;
        updateTitle();
        setAllowNestedScroll(true);
        this.topPadding = 0.2f;
        Context r11 = r21.getContext();
        fixNavigationBar();
        setApplyBottomPadding(false);
        LinearLayout r1 = new LinearLayout(r11);
        this.linearLayout = r1;
        r1.setOrientation(1);
        AnonymousClass2 r0 = new AnonymousClass2(this, getContext(), r22.dialogId, r24);
        this.circleDiagramView = r0;
        this.linearLayout.addView(r0, LayoutHelper.createLinear(-2, -2, 1, 0, 16, 0, 16));
        CheckBoxCell r3 = null;
        int r2 = 0;
    L4:
        if (r2 >= r9) goto L34;
        if (r2 != 0) goto L8;
        String r12 = LocaleController.getString(R.string.LocalPhotoCache);
        int r13 = Theme.key_statisticChartLine_lightblue;
    L25:
        CacheControlActivity.FileEntities r14 = r22.entitiesByType.get(r2);
        if (r14 == null) goto L28;
        boolean r18 = r10;
        long r92 = r14.totalSize;
    L29:
        StorageDiagramView.ClearViewData[] r15 = this.clearViewData;
        if (r92 <= 0) goto L32;
        r15[r2] = new StorageDiagramView.ClearViewData(this.circleDiagramView);
        StorageDiagramView.ClearViewData r32 = this.clearViewData[r2];
        r32.size = r92;
        r32.colorKey = r13;
        r3 = new CheckBoxCell(r11, 4, 21, null);
        r3.setTag(Integer.valueOf(r2));
        r3.setBackgroundDrawable(Theme.getSelectorDrawable(r18));
        this.linearLayout.addView(r3, LayoutHelper.createLinear(-1, 50));
        r3.setText(r12, AndroidUtilities.formatFileSize(r92), true, true);
        r3.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r3.setCheckBoxColor(r13, Theme.key_windowBackgroundWhiteGrayIcon, Theme.key_checkboxCheck);
        r3.setOnClickListener(new DialogCacheBottomSheet$$ExternalSyntheticLambda1(this, r23));
        this.checkBoxes[r2] = r3;
    L33:
        r2 = r2 + 1;
        r10 = r18;
        r9 = 8;
        goto L4
    L32:
        r15[r2] = null;
        this.checkBoxes[r2] = null;
        goto L33
    L28:
        r18 = r10;
        r92 = 0;
        goto L29
    L8:
        if (r2 != 1) goto L11;
        r12 = LocaleController.getString(R.string.LocalVideoCache);
        r13 = Theme.key_statisticChartLine_blue;
        goto L25
    L11:
        if (r2 != 2) goto L14;
        r12 = LocaleController.getString(R.string.LocalDocumentCache);
        r13 = Theme.key_statisticChartLine_green;
        goto L25
    L14:
        if (r2 != 3) goto L16;
        r12 = LocaleController.getString(R.string.LocalMusicCache);
        r13 = Theme.key_statisticChartLine_red;
        goto L25
    L16:
        if (r2 != 4) goto L19;
        r12 = LocaleController.getString(R.string.LocalAudioCache);
        r13 = Theme.key_statisticChartLine_lightgreen;
        goto L25
    L19:
        if (r2 != 5) goto L22;
        r12 = LocaleController.getString(R.string.LocalStickersCache);
        r13 = Theme.key_statisticChartLine_orange;
        goto L25
    L22:
        if (r2 != 7) goto L24;
        r12 = LocaleController.getString(R.string.LocalStoriesCache);
        r13 = Theme.key_statisticChartLine_indigo;
        goto L25
    L24:
        r12 = LocaleController.getString(R.string.LocalMiscellaneousCache);
        r13 = Theme.key_statisticChartLine_purple;
        goto L25
    L34:
        boolean r182 = r10;
        if (r3 == null) goto L37;
        r3.setNeedDivider(r182);
    L37:
        this.circleDiagramView.setData(r23, this.clearViewData);
        AnonymousClass3 r16 = new AnonymousClass3(this, getContext(), r21);
        this.cachedMediaLayout = r16;
        r16.setBottomPadding(AndroidUtilities.dp(80.0f));
        this.cachedMediaLayout.setCacheModel(r23);
        this.cachedMediaLayout.setDelegate(new AnonymousClass4(this, r23));
        NestedSizeNotifierLayout r17 = this.nestedSizeNotifierLayout;
        if (r17 == null) goto L40;
        r17.setChildLayout(this.cachedMediaLayout);
    L42:
        if (this.button == null) goto L48;
        this.button.setSize(true, this.circleDiagramView.calculateSize());
        return;
    L48:
        return;
    L40:
        createButton();
        this.linearLayout.addView(this.button, LayoutHelper.createLinear(-1, 72, 80));
        goto L42
    }

    public static /* synthetic */ int access$000(DialogCacheBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$100(DialogCacheBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$200(DialogCacheBottomSheet r0) {
        return r0.contentHeight;
    }

    private void createButton() {
        CacheControlActivity.ClearCacheButton r0 = new CacheControlActivity.ClearCacheButton(getContext());
        this.button = r0;
        r0.button.setOnClickListener(new DialogCacheBottomSheet$$ExternalSyntheticLambda0(this));
        StorageDiagramView r02 = this.circleDiagramView;
        if (r02 == null) goto L6;
        long r03 = r02.calculateSize();
        this.button.setSize(true, r03);
        return;
    }

    private /* synthetic */ void lambda$createButton$1(AlertDialog r1, int r2) {
        dismiss();
    }

    private /* synthetic */ void lambda$createButton$2(AlertDialog r2, int r3) {
        dismiss();
        this.cacheDelegate.cleanupDialogFiles(this.entities, this.clearViewData, this.cacheModel);
    }

    private /* synthetic */ void lambda$createButton$3(View r3) {
        AlertDialog.Builder r32 = new AlertDialog.Builder(getContext());
        r32.setTitle(LocaleController.getString(R.string.ClearCache));
        r32.setMessage(LocaleController.getString(R.string.ClearCacheForChat));
        r32.setNegativeButton(LocaleController.getString(R.string.Cancel), new DialogCacheBottomSheet$$ExternalSyntheticLambda2(this));
        r32.setPositiveButton(LocaleController.getString(R.string.Clear), new DialogCacheBottomSheet$$ExternalSyntheticLambda3(this));
        AlertDialog r2 = r32.create();
        r2.show();
        r2.redPositive();
    }

    private /* synthetic */ void lambda$new$0(CacheModel r5, View r6) {
        int r0 = 0;
    L3:
        StorageDiagramView.ClearViewData[] r1 = this.clearViewData;
        if (r0 >= r1.length) goto L9;
        StorageDiagramView.ClearViewData r12 = r1[r0];
        if (r12 == null) goto L8;
        boolean r13 = r12.clear;
    L8:
        r0 = r0 + 1;
        goto L3
    L9:
        CheckBoxCell r62 = (CheckBoxCell) r6;
        int r02 = ((Integer) r62.getTag()).intValue();
        this.clearViewData[r02].setClear(!r1.clear);
        r62.setChecked(this.clearViewData[r02].clear, true);
        r5.allFilesSelcetedByType(r02, this.clearViewData[r02].clear);
        this.cachedMediaLayout.update();
        this.button.setSize(true, this.circleDiagramView.updateDescription());
        this.circleDiagramView.update(true);
    }

    private void syncCheckBoxes() {
        CheckBoxCell r0 = this.checkBoxes[0];
        if (r0 == null) goto L5;
        StorageDiagramView.ClearViewData r1 = this.clearViewData[0];
        boolean r3 = this.cacheModel.allPhotosSelected;
        r1.clear = r3;
        r0.setChecked(r3, true);
    L5:
        CheckBoxCell r02 = this.checkBoxes[1];
        if (r02 == null) goto L8;
        StorageDiagramView.ClearViewData r12 = this.clearViewData[1];
        boolean r32 = this.cacheModel.allVideosSelected;
        r12.clear = r32;
        r02.setChecked(r32, true);
    L8:
        CheckBoxCell r03 = this.checkBoxes[2];
        if (r03 == null) goto L11;
        StorageDiagramView.ClearViewData r13 = this.clearViewData[2];
        boolean r33 = this.cacheModel.allDocumentsSelected;
        r13.clear = r33;
        r03.setChecked(r33, true);
    L11:
        CheckBoxCell r04 = this.checkBoxes[3];
        if (r04 == null) goto L14;
        StorageDiagramView.ClearViewData r14 = this.clearViewData[3];
        boolean r34 = this.cacheModel.allMusicSelected;
        r14.clear = r34;
        r04.setChecked(r34, true);
    L14:
        CheckBoxCell r05 = this.checkBoxes[4];
        if (r05 == null) goto L18;
        StorageDiagramView.ClearViewData r15 = this.clearViewData[4];
        boolean r4 = this.cacheModel.allVoiceSelected;
        r15.clear = r4;
        r05.setChecked(r4, true);
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView, org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r1) {
        return new AnonymousClass1(this);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return getBaseFragment().getMessagesController().getFullName(this.dialogId);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onViewCreated(FrameLayout r4) {
        super.onViewCreated(r4);
        this.recyclerListView.addOnScrollListener(new AnonymousClass5(this));
        if (this.nestedSizeNotifierLayout == null) goto L6;
        createButton();
        r4.addView(this.button, LayoutHelper.createFrame(-1, 72, 80));
        return;
    }
}
