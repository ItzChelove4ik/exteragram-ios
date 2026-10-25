package org.telegram.ui;

import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.SharedPhotoVideoCell2;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.ExtendedGridLayoutManager;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public class SelectStoriesBottomSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    private UniversalAdapter adapter;
    private final ButtonWithCounterView button;
    private final FrameLayout buttonContainer;
    private final int columnsCount;
    private final long dialogId;
    private int id;
    private final ExtendedGridLayoutManager layoutManager;
    private final HashMap<Integer, TL_stories.StoryItem> selectedStoriesIds;
    private final StoriesController.StoriesList storiesList;

    /* renamed from: org.telegram.ui.SelectStoriesBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ SelectStoriesBottomSheet this$0;

        public AnonymousClass1(SelectStoriesBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r3) {
            UniversalAdapter r0 = SelectStoriesBottomSheet.m18777$$Nest$fgetadapter(this.this$0);
            SelectStoriesBottomSheet r1 = this.this$0;
            if (r0 == null) goto L5;
            UItem r32 = SelectStoriesBottomSheet.m18777$$Nest$fgetadapter(r1).getItem(r3 - 1);
            if (r32 == null) goto L13;
            int r33 = r32.spanCount;
            if (r33 == (-1)) goto L13;
            return r33;
        L13:
            return SelectStoriesBottomSheet.m18778$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        L5:
            return SelectStoriesBottomSheet.m18778$$Nest$fgetlayoutManager(r1).getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.SelectStoriesBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SelectStoriesBottomSheet this$0;

        public AnonymousClass2(SelectStoriesBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            SelectStoriesBottomSheet.m18779$$Nest$mcheckLoadMoreScroll(this.this$0);
        }
    }

    /* renamed from: $r8$lambda$5ucNwKKvwbRukCTD6ieG9ZK-ksw, reason: not valid java name */
    public static /* synthetic */ boolean m18775$r8$lambda$5ucNwKKvwbRukCTD6ieG9ZKksw(SelectStoriesBottomSheet r0, View r1, int r2) {
        return r0.onItemClick(r1, r2);
    }

    /* renamed from: $r8$lambda$J5yRZ_WrHQceBk3-TvaGwkrHncs, reason: not valid java name */
    public static /* synthetic */ void m18776$r8$lambda$J5yRZ_WrHQceBk3TvaGwkrHncs(SelectStoriesBottomSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$o9t7SaFLGScjujIO8YIpcvjSS3M(SelectStoriesBottomSheet r0, Utilities.Callback r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$sy6Jx_1PmppPQAb7xV5RGMeZIZk(SelectStoriesBottomSheet r0, View r1, int r2) {
        return r0.onItemClick(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalAdapter m18777$$Nest$fgetadapter(SelectStoriesBottomSheet r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ExtendedGridLayoutManager m18778$$Nest$fgetlayoutManager(SelectStoriesBottomSheet r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$mcheckLoadMoreScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18779$$Nest$mcheckLoadMoreScroll(SelectStoriesBottomSheet r0) {
        r0.checkLoadMoreScroll();
    }

    public SelectStoriesBottomSheet(BaseFragment r11, long r12, int r14, Utilities.Callback<ArrayList<TL_stories.StoryItem>> r15) {
        super(r11, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING);
        this.selectedStoriesIds = new HashMap();
        this.dialogId = r12;
        this.columnsCount = r14;
        StoriesController.StoriesList r112 = MessagesController.getInstance(r11.getCurrentAccount()).getStoriesController().getStoriesList(r12, 1);
        this.storiesList = r112;
        r112.load(false, 30);
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        fixNavigationBar();
        setSlidingActionBar();
        FrameLayout r113 = new FrameLayout(getContext());
        this.buttonContainer = r113;
        r113.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
        int r122 = this.backgroundPaddingLeft;
        r113.setPadding(r122, 0, r122, 0);
        this.containerView.addView(r113, LayoutHelper.createFrame(-1, -2.0f, 87, 0.0f, 0.0f, 0.0f, 0.0f));
        View r123 = new View(getContext());
        r123.setBackgroundColor(Theme.getColor(Theme.key_divider, this.resourcesProvider));
        r113.addView(r123, LayoutHelper.createFrame(-1.0f, 1.0f / AndroidUtilities.density, 55));
        ButtonWithCounterView r124 = new ButtonWithCounterView(getContext(), this.resourcesProvider);
        this.button = r124;
        r124.setText(LocaleController.getString(R.string.StoriesAlbumMenuAddStories), false);
        r124.setEnabled(false);
        r124.setOnClickListener(new SelectStoriesBottomSheet$$ExternalSyntheticLambda0(this, r15));
        r113.addView(r124, LayoutHelper.createFrame(-1, 48.0f, 119, 10.0f, (1.0f / AndroidUtilities.density) + 10.0f, 10.0f, 10.0f));
        ExtendedGridLayoutManager r114 = new ExtendedGridLayoutManager(getContext(), r14);
        this.layoutManager = r114;
        r114.setSpanSizeLookup(new AnonymousClass1(this));
        RecyclerListView r125 = this.recyclerListView;
        int r13 = this.backgroundPaddingLeft;
        r125.setPadding(r13, 0, r13, 0);
        this.recyclerListView.setSelectorType(9);
        this.recyclerListView.setSelectorDrawableColor(0);
        this.recyclerListView.setLayoutManager(r114);
        this.recyclerListView.setOnItemClickListener(new SelectStoriesBottomSheet$$ExternalSyntheticLambda1(this));
        this.recyclerListView.setOnItemLongClickListener(new SelectStoriesBottomSheet$$ExternalSyntheticLambda2(this));
        this.recyclerListView.setOnScrollListener(new AnonymousClass2(this));
        this.adapter.update(true);
    }

    private void checkLoadMoreScroll() {
        int r0 = this.layoutManager.findFirstVisibleItemPosition();
        if (r0 != (-1)) goto L5;
        int r1 = 0;
    L6:
        StoriesController.StoriesList r4 = this.storiesList;
        if (r4 == null) goto L12;
        int r02 = r0 + r1;
        int r12 = r4.getLoadedCount();
        int r42 = this.columnsCount;
        if (r02 <= (r12 - r42)) goto L13;
        int r03 = Math.max(1, r42 / 2);
        int r13 = this.columnsCount;
        this.storiesList.load(false, Math.min(100, (r03 * r13) * r13));
        return;
    L13:
        return;
    L12:
        return;
    L5:
        r1 = Math.abs(this.layoutManager.findLastVisibleItemPosition() - r0) + 1;
        goto L6
    }

    private void fillItems(ArrayList<UItem> r9, UniversalAdapter r10) {
        if (this.storiesList != null) goto L5;
        return;
    L5:
        r9.add(UItem.asSpace(AndroidUtilities.dp(16.0f)));
        int r102 = this.columnsCount;
        ArrayList<MessageObject> r0 = this.storiesList.messageObjects;
        int r1 = r0.size();
        int r2 = 0;
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L12;
        MessageObject r5 = r0.get(r3);
        r3 = r3 + 1;
        MessageObject r52 = r5;
        r9.add(StoryCellFactory.asStory(0, r52, this.columnsCount, true).setChecked(this.selectedStoriesIds.containsKey(Integer.valueOf(r52.getId()))).setSpanCount(1));
        r102 = r102 - 1;
        if (r102 != 0) goto L7;
        r102 = this.columnsCount;
        goto L7
    L12:
        if (this.storiesList.isLoading() == true) goto L15;
        if (this.storiesList.isFull() == false) goto L15;
    L20:
        r9.add(UItem.asSpace(AndroidUtilities.dp(68.0f)));
        return;
    L15:
        if (r102 > 0) goto L17;
        int r02 = this.columnsCount;
    L18:
        if (r2 >= r02) goto L20;
        r2 = r2 + 1;
        r9.add(UItem.asFlicker(r2, 34).setSpanCount(1));
        goto L15
    L17:
        r02 = r102;
        goto L18
    }

    private /* synthetic */ void lambda$new$0(Utilities.Callback r2, View r3) {
        if (this.storiesList.getCount() != 0) goto L5;
        return;
    L5:
        r2.run(new ArrayList(this.selectedStoriesIds.values()));
        dismiss();
    }

    private boolean onItemClick(View r7, int r8) {
        UniversalAdapter r0 = this.adapter;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r8 != 0) goto L7;
        return false;
    L7:
        UItem r82 = r0.getItem(r8 - 1);
        if (r82 != null) goto L10;
        return false;
    L10:
        Object r02 = r82.object;
        if ((r02 instanceof MessageObject) == false) goto L17;
        MessageObject r03 = (MessageObject) r02;
        int r3 = r03.getId();
        boolean r4 = this.selectedStoriesIds.containsKey(Integer.valueOf(r3));
        HashMap<Integer, TL_stories.StoryItem> r5 = this.selectedStoriesIds;
        if (r4 == false) goto L15;
        r5.remove(Integer.valueOf(r3));
        r82.checked = false;
        ((SharedPhotoVideoCell2) r7).setChecked(false, true);
    L16:
        this.button.setEnabled(!this.selectedStoriesIds.isEmpty());
        this.button.setCount(this.selectedStoriesIds.size(), true);
        goto L17
    L15:
        r5.put(Integer.valueOf(r3), r03.storyItem);
        r82.checked = true;
        ((SharedPhotoVideoCell2) r7).setChecked(true, true);
    L17:
        return true;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r8) {
        UniversalAdapter r0 = new UniversalAdapter(r8, getContext(), this.currentAccount, 0, new SelectStoriesBottomSheet$$ExternalSyntheticLambda3(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.storiesListUpdated) goto L5;
        return;
    L5:
        if (((StoriesController.StoriesList) r3[0]) != this.storiesList) goto L9;
        this.adapter.update(false);
        checkLoadMoreScroll();
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.StoriesAlbumMenuAddStories);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.id = this.storiesList.link();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesListUpdated);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.storiesList.unlink(this.id);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesListUpdated);
    }
}
