package org.telegram.ui.Gifts;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ExtendedGridLayoutManager;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.BlurredBackgroundWithFadeDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class SelectGiftsBottomSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    private UniversalAdapter adapter;
    private final ButtonWithCounterView button;
    private final FrameLayout buttonContainer;
    private final FrameLayout buttonContainerInternal;
    private final int collectionId;
    private final long dialogId;
    private final BlurredBackgroundDrawableViewFactory iBlur3BackgroundFactory;
    private final BlurredBackgroundDrawableViewFactory iBlur3ButtonFactory;
    private final BlurredBackgroundSourceColor iBlur3SourceBackgroundColor;
    private final BlurredBackgroundSourceColor iBlur3SourceButtonColor;
    private Insets insets;
    private ItemOptions lastMenu;
    private final ExtendedGridLayoutManager layoutManager;
    private final StarsController.GiftsList list;
    private final HashSet<Long> selectedGiftIds;

    /* renamed from: org.telegram.ui.Gifts.SelectGiftsBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ SelectGiftsBottomSheet this$0;
        final /* synthetic */ long val$dialogId;
        final /* synthetic */ ActionBarMenuItem val$other;

        /* renamed from: $r8$lambda$igmXPOb13OjA4_JMRYsA-Aqxp7I, reason: not valid java name */
        public static /* synthetic */ void m15004$r8$lambda$igmXPOb13OjA4_JMRYsAAqxp7I(AnonymousClass1 r0, ActionBarMenuSubItem r1, ActionBarMenuSubItem r2, ActionBarMenuSubItem r3, ActionBarMenuSubItem r4, ActionBarMenuSubItem r5, boolean r6, ActionBarMenuSubItem r7, ActionBarMenuSubItem r8) {
            r0.lambda$onItemClick$0(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        /* renamed from: $r8$lambda$r2IPUIvxw-CHO9x64EolVU1-fkc, reason: not valid java name */
        public static /* synthetic */ void m15005$r8$lambda$r2IPUIvxwCHO9x64EolVU1fkc(AnonymousClass1 r0, Runnable r1, View r2) {
            r0.lambda$onItemClick$1(r1, r2);
        }

        public AnonymousClass1(SelectGiftsBottomSheet r1, ActionBarMenuItem r2, long r3) {
            this.this$0 = r1;
            this.val$other = r2;
            this.val$dialogId = r3;
        }

        private /* synthetic */ void lambda$onItemClick$0(ActionBarMenuSubItem r3, ActionBarMenuSubItem r4, ActionBarMenuSubItem r5, ActionBarMenuSubItem r6, ActionBarMenuSubItem r7, boolean r8, ActionBarMenuSubItem r9, ActionBarMenuSubItem r10) {
            if (r3 != null) goto L4;
        L12:
            r4.setChecked(SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).isInclude_unlimited());
            r5.setChecked(SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).isInclude_limited());
            r6.setChecked(SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).isInclude_upgradable());
            r7.setChecked(SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).isInclude_unique());
            if (r8 == false) goto L16;
            r9.setChecked(SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).isInclude_displayed());
            r10.setChecked(SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).isInclude_hidden());
            return;
        L16:
            return;
        L4:
            if (SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).sort_by_date == false) goto L6;
            int r0 = R.string.Gift2FilterSortByValue;
        L7:
            String r02 = LocaleController.getString(r0);
            if (SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).sort_by_date == false) goto L10;
            int r1 = R.drawable.menu_sort_value;
        L11:
            r3.setTextAndIcon(r02, r1);
            goto L12
        L10:
            r1 = R.drawable.menu_sort_date;
            goto L11
        L6:
            r0 = R.string.Gift2FilterSortByDate;
            goto L7
        }

        private /* synthetic */ void lambda$onItemClick$1(Runnable r3, View r4) {
            SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).sort_by_date = !SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).sort_by_date;
            r3.run();
            SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).invalidate(true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r14) {
            if (r14 != 1) goto L27;
            if (SelectGiftsBottomSheet.m14999$$Nest$fgetlastMenu(this.this$0) == null) goto L7;
            SelectGiftsBottomSheet.m14999$$Nest$fgetlastMenu(this.this$0).dismiss();
        L7:
            SelectGiftsBottomSheet r142 = this.this$0;
            ItemOptions r1 = ItemOptions.makeOptions(r142.container, SelectGiftsBottomSheet.access$000(r142), this.val$other);
            SelectGiftsBottomSheet.m15002$$Nest$fputlastMenu(r142, r1);
            if (this.val$dialogId != UserConfig.getInstance(SelectGiftsBottomSheet.access$100(this.this$0)).getClientUserId()) goto L11;
            boolean r10 = true;
        L14:
            ActionBarMenuSubItem r5 = r1.add();
            r1.addGap();
            ActionBarMenuSubItem r6 = r1.addChecked();
            r6.setText(LocaleController.getString(R.string.Gift2FilterUnlimited));
            ActionBarMenuSubItem r7 = r1.addChecked();
            r7.setText(LocaleController.getString(R.string.Gift2FilterLimited));
            ActionBarMenuSubItem r8 = r1.addChecked();
            r8.setText(LocaleController.getString(R.string.Gift2FilterUpgradable));
            ActionBarMenuSubItem r9 = r1.addChecked();
            r9.setText(LocaleController.getString(R.string.Gift2FilterUnique));
            if (r10 == false) goto L17;
            r1.addGap();
            ActionBarMenuSubItem r143 = r1.addChecked();
            r143.setText(LocaleController.getString(R.string.Gift2FilterDisplayed));
            ActionBarMenuSubItem r3 = r1.addChecked();
            r3.setText(LocaleController.getString(R.string.Gift2FilterHidden));
            ActionBarMenuSubItem r11 = r143;
            ActionBarMenuSubItem r12 = r3;
        L18:
            SelectGiftsBottomSheet$1$$ExternalSyntheticLambda0 r32 = new SelectGiftsBottomSheet$1$$ExternalSyntheticLambda0(this, r5, r6, r7, r8, r9, r10, r11, r12);
            r32.run();
            if (r5 == null) goto L21;
            r5.setOnClickListener(new SelectGiftsBottomSheet$1$$ExternalSyntheticLambda1(this, r32));
        L21:
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r6, SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0), r32, 1);
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r7, SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0), r32, 2);
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r8, SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0), r32, 4);
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r9, SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0), r32, 8);
            if (r10 == false) goto L24;
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r11, SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0), r32, 256);
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r12, SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0), r32, 512);
        L24:
            r1.setOnTopOfScrim().setDismissWithButtons(false).setDimAlpha(0).show();
            return;
        L17:
            r11 = null;
            r12 = null;
            goto L18
        L11:
            if (this.val$dialogId < 0) goto L13;
            r10 = false;
            goto L14
        L13:
            r10 = ChatObject.canUserDoAction(MessagesController.getInstance(SelectGiftsBottomSheet.access$200(this.this$0)).getChat(Long.valueOf(-this.val$dialogId)), 5);
            goto L14
        L27:
            if (r14 != (-1)) goto L30;
            this.this$0.dismiss();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.SelectGiftsBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ SelectGiftsBottomSheet this$0;

        public AnonymousClass2(SelectGiftsBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r3) {
            UniversalAdapter r0 = SelectGiftsBottomSheet.m14998$$Nest$fgetadapter(this.this$0);
            SelectGiftsBottomSheet r1 = this.this$0;
            if (r0 == null) goto L5;
            UItem r32 = SelectGiftsBottomSheet.m14998$$Nest$fgetadapter(r1).getItem(r3 - 1);
            if (r32 == null) goto L13;
            int r33 = r32.spanCount;
            if (r33 == (-1)) goto L13;
            return r33;
        L13:
            return SelectGiftsBottomSheet.m15000$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        L5:
            return SelectGiftsBottomSheet.m15000$$Nest$fgetlayoutManager(r1).getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.Gifts.SelectGiftsBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SelectGiftsBottomSheet this$0;

        public AnonymousClass3(SelectGiftsBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (SelectGiftsBottomSheet.m15003$$Nest$misLoadingVisible(this.this$0) == false) goto L6;
            SelectGiftsBottomSheet.m15001$$Nest$fgetlist(this.this$0).load();
            return;
        }
    }

    /* renamed from: $r8$lambda$G02ojQQsZ12eywehX8r3NjU-oTY, reason: not valid java name */
    public static /* synthetic */ void m14997$r8$lambda$G02ojQQsZ12eywehX8r3NjUoTY(SelectGiftsBottomSheet r0, View r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Lvf1iB4cO9pRz18_rHAf7e_Jpu4(SelectGiftsBottomSheet r0, Utilities.Callback r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$j8wi7bWBYeTziNbGAfufzezqwcY(SelectGiftsBottomSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalAdapter m14998$$Nest$fgetadapter(SelectGiftsBottomSheet r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetlastMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ItemOptions m14999$$Nest$fgetlastMenu(SelectGiftsBottomSheet r0) {
        return r0.lastMenu;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ExtendedGridLayoutManager m15000$$Nest$fgetlayoutManager(SelectGiftsBottomSheet r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlist, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarsController.GiftsList m15001$$Nest$fgetlist(SelectGiftsBottomSheet r0) {
        return r0.list;
    }

    /* renamed from: -$$Nest$fputlastMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15002$$Nest$fputlastMenu(SelectGiftsBottomSheet r0, ItemOptions r1) {
        r0.lastMenu = r1;
    }

    /* renamed from: -$$Nest$misLoadingVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15003$$Nest$misLoadingVisible(SelectGiftsBottomSheet r0) {
        return r0.isLoadingVisible();
    }

    public SelectGiftsBottomSheet(BaseFragment r18, long r19, int r21, Utilities.Callback<ArrayList<TL_stars.SavedStarGift>> r22) {
        super(r18.getParentActivity(), r18, new BottomSheetWithRecyclerListView.Params.Builder().actionBarType(BottomSheetWithRecyclerListView.ActionBarType.SLIDING).edgeToEdge(BottomSheet.EdgeToEdge.V2).resourcesProvider(r18.getResourceProvider()).build());
        this.selectedGiftIds = new HashSet();
        this.insets = Insets.NONE;
        Context r3 = getContext();
        BlurredBackgroundSourceColor r4 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceButtonColor = r4;
        r4.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        BlurredBackgroundDrawableViewFactory r5 = new BlurredBackgroundDrawableViewFactory(r4);
        this.iBlur3ButtonFactory = r5;
        BlurredBackgroundSourceColor r42 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceBackgroundColor = r42;
        int r6 = Theme.key_windowBackgroundGray;
        r42.setColor(getThemedColor(r6));
        BlurredBackgroundDrawableViewFactory r7 = new BlurredBackgroundDrawableViewFactory(r42);
        this.iBlur3BackgroundFactory = r7;
        this.ignoreTouchActionBar = false;
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        setBackgroundColor(getThemedColor(r6));
        setSlidingActionBar();
        this.dialogId = r19;
        this.collectionId = r21;
        this.list = new StarsController.GiftsList(this.currentAccount, r19);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this, this.actionBar.createMenu().addItem(1, R.drawable.ic_ab_other), r19));
        ButtonWithCounterView r1 = new ButtonWithCounterView(getContext(), this.resourcesProvider);
        this.button = r1;
        r1.setText(LocaleController.getString(R.string.Gift2CollectionAddGiftsButton), false);
        r1.setEnabled(false);
        r1.setStateListAnimator(null);
        r1.setRound();
        FrameLayout r2 = new FrameLayout(r3);
        this.buttonContainer = r2;
        int r62 = this.backgroundPaddingLeft;
        r2.setPadding(r62, 0, r62, 0);
        FrameLayout r63 = new FrameLayout(r3);
        this.buttonContainerInternal = r63;
        r63.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r63.addView(r1, LayoutHelper.createFrame(-1, -1.0f));
        r63.setOnClickListener(new SelectGiftsBottomSheet$$ExternalSyntheticLambda1(this, r22));
        r63.setBackground(r5.create(r63).setColorProvider(BlurredBackgroundProviderImpl.premiumButton(this.resourcesProvider)).setRadius(AndroidUtilities.dp(28.0f)).setPadding(AndroidUtilities.dp(5.0f)));
        ScaleStateListAnimator.apply(r63, 0.02f, 1.5f);
        r2.addView(r63, LayoutHelper.createFrame(-1, 64.0f, 80, 4.0f, 0.0f, 4.0f, 0.0f));
        BlurredBackgroundWithFadeDrawable r12 = new BlurredBackgroundWithFadeDrawable(r7.create(r2));
        r12.setFadeHeight(AndroidUtilities.dp(40.0f), true);
        r12.setAlpha(Opcodes.REM_INT_LIT8);
        r2.setBackground(r12);
        this.containerView.addView(r2, LayoutHelper.createFrame(-1, -2, 80));
        ExtendedGridLayoutManager r13 = new ExtendedGridLayoutManager(getContext(), 3);
        this.layoutManager = r13;
        r13.setSpanSizeLookup(new AnonymousClass2(this));
        this.recyclerListView.setPadding(this.backgroundPaddingLeft + AndroidUtilities.dp(9.0f), 0, this.backgroundPaddingLeft + AndroidUtilities.dp(9.0f), 0);
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setSelectorType(9);
        this.recyclerListView.setSelectorDrawableColor(0);
        this.recyclerListView.setLayoutManager(r13);
        this.recyclerListView.setOnItemClickListener(new SelectGiftsBottomSheet$$ExternalSyntheticLambda2(this));
        this.recyclerListView.addOnScrollListener(new AnonymousClass3(this));
        DefaultItemAnimator r14 = new DefaultItemAnimator();
        r14.setSupportsChangeAnimations(false);
        r14.setDelayAnimations(false);
        r14.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r14.setDurations(350);
        this.recyclerListView.setItemAnimator(r14);
        this.adapter.update(true);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starUserGiftsLoaded);
        checkUi_insets();
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(SelectGiftsBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$100(SelectGiftsBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(SelectGiftsBottomSheet r0) {
        return r0.currentAccount;
    }

    private void checkUi_insets() {
        this.recyclerListView.setPadding((this.insets.left + this.backgroundPaddingLeft) + AndroidUtilities.dp(9.0f), 0, (this.insets.right + this.backgroundPaddingLeft) + AndroidUtilities.dp(9.0f), this.insets.bottom);
        FrameLayout r0 = this.buttonContainer;
        Insets r1 = this.insets;
        int r2 = r1.left;
        int r5 = this.backgroundPaddingLeft;
        r0.setPadding(r2 + r5, 0, r1.right + r5, r1.bottom);
    }

    private void fillItems(ArrayList<UItem> r13, UniversalAdapter r14) {
        if (this.list != null) goto L5;
        return;
    L5:
        r13.add(UItem.asSpace(AndroidUtilities.dp(16.0f)));
        StarsController.GiftsList r142 = this.list;
        if (r142.loading == true) goto L8;
    L10:
        ArrayList<TL_stars.SavedStarGift> r143 = this.list.gifts;
        int r0 = r143.size();
        int r4 = 0;
        int r5 = 3;
        int r6 = 0;
    L11:
        if (r6 >= r0) goto L22;
        TL_stars.SavedStarGift r7 = r143.get(r6);
        r6 = r6 + 1;
        TL_stars.SavedStarGift r72 = r7;
        if (r72.collection_id.contains(Integer.valueOf(this.collectionId)) == true) goto L11;
        UItem r8 = GiftSheet.GiftCell.Factory.asStarGift(0, r72, true, true, false);
        HashSet<Long> r9 = this.selectedGiftIds;
        int r10 = r72.msg_id;
        if (r10 != 0) goto L18;
        long r102 = r72.saved_id;
    L19:
        r13.add(r8.setChecked(r9.contains(Long.valueOf(r102))).setSpanCount(1));
        r5 = r5 - 1;
        if (r5 != 0) goto L11;
        r5 = 3;
        goto L11
    L18:
        r102 = r10;
        goto L19
    L22:
        StarsController.GiftsList r12 = this.list;
        if (r12.loading == true) goto L26;
        if (r12.endReached == false) goto L26;
    L31:
        r13.add(UItem.asSpace(AndroidUtilities.dp(68.0f)));
        return;
    L26:
        if (r5 > 0) goto L28;
        int r122 = 3;
    L29:
        if (r4 >= r122) goto L31;
        r4 = r4 + 1;
        r13.add(UItem.asFlicker(r4, 34).setSpanCount(1));
        goto L26
    L28:
        r122 = r5;
        goto L29
    L8:
        if (r142.gifts.isEmpty() == false) goto L10;
        r13.add(UItem.asFlicker(1, 34).setSpanCount(1));
        r13.add(UItem.asFlicker(2, 34).setSpanCount(1));
        r13.add(UItem.asFlicker(3, 34).setSpanCount(1));
        r13.add(UItem.asFlicker(4, 34).setSpanCount(1));
        r13.add(UItem.asFlicker(5, 34).setSpanCount(1));
        r13.add(UItem.asFlicker(6, 34).setSpanCount(1));
        r13.add(UItem.asFlicker(7, 34).setSpanCount(1));
        r13.add(UItem.asFlicker(8, 34).setSpanCount(1));
        r13.add(UItem.asFlicker(9, 34).setSpanCount(1));
        goto L31
    }

    private boolean isLoadingVisible() {
        RecyclerListView r0 = this.recyclerListView;
        if (r0 != null) goto L5;
    L15:
        return false;
    L5:
        if (r0.isAttachedToWindow() == false) goto L15;
        int r02 = 0;
    L9:
        if (r02 >= this.recyclerListView.getChildCount()) goto L15;
        if ((this.recyclerListView.getChildAt(r02) instanceof FlickerLoadingView) == true) goto L12;
        r02 = r02 + 1;
        goto L9
    L12:
        return true;
    }

    private /* synthetic */ void lambda$new$0(Utilities.Callback r10, View r11) {
        if (this.selectedGiftIds.isEmpty() == false) goto L5;
        return;
    L5:
        ArrayList r112 = new ArrayList();
        Iterator<Long> r0 = this.selectedGiftIds.iterator();
    L7:
        if (r0.hasNext() == false) goto L20;
        long r1 = r0.next().longValue();
        ArrayList<TL_stars.SavedStarGift> r3 = this.list.gifts;
        int r4 = r3.size();
        int r5 = 0;
    L9:
        if (r5 >= r4) goto L17;
        TL_stars.SavedStarGift r6 = r3.get(r5);
        r5 = r5 + 1;
        TL_stars.SavedStarGift r62 = r6;
        int r7 = r62.msg_id;
        if (r7 == 0) goto L15;
        if (r7 != r1) goto L15;
    L18:
        if (r62 == null) goto L7;
        r112.add(r62);
    L15:
        if (r62.saved_id != r1) goto L9;
    L17:
        r62 = null;
        goto L18
    L20:
        r10.run(r112);
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(View r7, int r8) {
        UniversalAdapter r0 = this.adapter;
        if (r0 == null) goto L24;
        UItem r82 = r0.getItem(r8 - 1);
        if (r82 == null) goto L22;
        Object r02 = r82.object;
        if ((r02 instanceof TL_stars.SavedStarGift) == false) goto L23;
        TL_stars.SavedStarGift r03 = (TL_stars.SavedStarGift) r02;
        int r2 = r03.msg_id;
        if (r2 != 0) goto L12;
        long r22 = r03.saved_id;
    L13:
        boolean r04 = this.selectedGiftIds.contains(Long.valueOf(r22));
        HashSet<Long> r4 = this.selectedGiftIds;
        boolean r5 = false;
        if (r04 == false) goto L16;
        r4.remove(Long.valueOf(r22));
        ((GiftSheet.GiftCell) r7).setChecked(false, true);
        r82.checked = false;
    L17:
        ButtonWithCounterView r72 = this.button;
        if (this.selectedGiftIds.size() <= 0) goto L20;
        r5 = true;
    L20:
        r72.setEnabled(r5);
        this.button.setCount(this.selectedGiftIds.size(), true);
        return;
    L16:
        r4.add(Long.valueOf(r22));
        ((GiftSheet.GiftCell) r7).setChecked(true, true);
        r82.checked = true;
        goto L17
    L12:
        r22 = r2;
        goto L13
    L23:
        return;
    L22:
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r8) {
        UniversalAdapter r0 = new UniversalAdapter(r8, getContext(), this.currentAccount, 0, new SelectGiftsBottomSheet$$ExternalSyntheticLambda0(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.starUserGiftsLoaded) goto L10;
        UniversalAdapter r12 = this.adapter;
        if (r12 == null) goto L11;
        r12.update(true);
        if (isLoadingVisible() == false) goto L12;
        this.list.load();
        return;
    L12:
        return;
    L11:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starUserGiftsLoaded);
        super.dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.Gift2CollectionAddGiftsTitle);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public WindowInsetsCompat onApplyWindowInsetsToRoot(View r1, WindowInsetsCompat r2) {
        this.insets = AndroidUtilities.getDefaultWindowInsets(r2, false);
        checkUi_insets();
        return WindowInsetsCompat.CONSUMED;
    }
}
