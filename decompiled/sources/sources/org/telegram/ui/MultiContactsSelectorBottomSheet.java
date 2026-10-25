package org.telegram.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;
import org.telegram.ui.Components.Premium.boosts.adapters.SelectorAdapter;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorBtnCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorHeaderCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorSearchCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorUserCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;

/* loaded from: classes6.dex */
public class MultiContactsSelectorBottomSheet extends BottomSheetWithRecyclerListView {
    private static MultiContactsSelectorBottomSheet instance;
    private final ButtonWithCounterView actionButton;
    private final HashMap<Long, TLRPC.User> allSelectedObjects;
    private final SelectorBtnCell buttonContainer;
    private final List<TLRPC.TL_contact> contacts;
    private final List<String> contactsLetters;
    private final Map<String, List<TLRPC.TL_contact>> contactsMap;
    private Boolean filterBots;
    private Boolean filterPremium;
    private final List<TLRPC.User> foundUsers;
    private final SelectorHeaderCell headerView;
    private final List<TLRPC.TL_topPeer> hints;
    private final ArrayList<SelectorAdapter.Item> items;
    private int lastRequestId;
    private int listPaddingTop;
    private int maxCount;
    private final ArrayList<SelectorAdapter.Item> oldItems;
    private String query;
    private float recipientsBtnExtraSpace;
    private ReplacementSpan recipientsBtnSpaceSpan;
    private final Runnable remoteSearchRunnable;
    private final SelectorSearchCell searchField;
    private final View sectionCell;
    private final HashSet<Long> selectedIds;
    private SelectorAdapter selectorAdapter;
    private SelectorListener selectorListener;

    /* renamed from: org.telegram.ui.MultiContactsSelectorBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ MultiContactsSelectorBottomSheet this$0;

        public AnonymousClass1(MultiContactsSelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            String r0 = MultiContactsSelectorBottomSheet.m16225$$Nest$fgetquery(this.this$0);
            if (r0 == null) goto L6;
            MultiContactsSelectorBottomSheet.m16232$$Nest$mloadData(this.this$0, r0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.MultiContactsSelectorBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends ReplacementSpan {
        final /* synthetic */ MultiContactsSelectorBottomSheet this$0;

        public AnonymousClass2(MultiContactsSelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return (int) MultiContactsSelectorBottomSheet.m16226$$Nest$fgetrecipientsBtnExtraSpace(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.MultiContactsSelectorBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends SelectorHeaderCell {
        final /* synthetic */ MultiContactsSelectorBottomSheet this$0;

        public AnonymousClass3(MultiContactsSelectorBottomSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorHeaderCell
        public int getHeaderHeight() {
            if (getResources().getConfiguration().orientation != 2) goto L7;
            return AndroidUtilities.dp(48.0f);
        L7:
            return AndroidUtilities.dp(54.0f);
        }
    }

    /* renamed from: org.telegram.ui.MultiContactsSelectorBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends SelectorSearchCell {
        private boolean isKeyboardVisible;
        final /* synthetic */ MultiContactsSelectorBottomSheet this$0;

        public AnonymousClass4(MultiContactsSelectorBottomSheet r1, Context r2, Theme.ResourcesProvider r3, Runnable r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            MultiContactsSelectorBottomSheet.m16229$$Nest$fputlistPaddingTop(this.this$0, getMeasuredHeight() + AndroidUtilities.dp(64.0f));
            MultiContactsSelectorBottomSheet.m16228$$Nest$fgetselectorAdapter(this.this$0).notifyChangedLast();
            if (this.isKeyboardVisible == this.this$0.isKeyboardVisible()) goto L8;
            boolean r12 = this.this$0.isKeyboardVisible();
            this.isKeyboardVisible = r12;
            if (r12 == false) goto L9;
            this.this$0.scrollToTop(true);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.MultiContactsSelectorBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends View {
        final /* synthetic */ MultiContactsSelectorBottomSheet this$0;

        public AnonymousClass5(MultiContactsSelectorBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r2) {
            r2.drawColor(MultiContactsSelectorBottomSheet.access$000(this.this$0, Theme.key_graySection));
        }
    }

    /* renamed from: org.telegram.ui.MultiContactsSelectorBottomSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends ButtonWithCounterView {
        final /* synthetic */ MultiContactsSelectorBottomSheet this$0;

        public AnonymousClass6(MultiContactsSelectorBottomSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stories.recorder.ButtonWithCounterView
        public float calculateCounterWidth(float r3, float r4) {
            if (MultiContactsSelectorBottomSheet.m16226$$Nest$fgetrecipientsBtnExtraSpace(this.this$0) != 0.0f) goto L5;
            boolean r42 = true;
        L6:
            MultiContactsSelectorBottomSheet.m16230$$Nest$fputrecipientsBtnExtraSpace(this.this$0, r3);
            if (r42 == false) goto L9;
            MultiContactsSelectorBottomSheet.m16231$$Nest$mcreateRecipientsBtnSpaceSpan(this.this$0);
            MultiContactsSelectorBottomSheet.m16233$$Nest$mupdateActionButton(this.this$0, false);
        L9:
            return r3;
        L5:
            r42 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.MultiContactsSelectorBottomSheet$7, reason: invalid class name */
    public class AnonymousClass7 extends RecyclerView.OnScrollListener {
        final /* synthetic */ MultiContactsSelectorBottomSheet this$0;

        public AnonymousClass7(MultiContactsSelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(MultiContactsSelectorBottomSheet.m16227$$Nest$fgetsearchField(this.this$0).getEditText());
            return;
        }
    }

    /* renamed from: org.telegram.ui.MultiContactsSelectorBottomSheet$8, reason: invalid class name */
    public class AnonymousClass8 extends RecyclerView.ItemDecoration {
        final /* synthetic */ MultiContactsSelectorBottomSheet this$0;

        public AnonymousClass8(MultiContactsSelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            super.getItemOffsets(r1, r2, r3, r4);
            if (r3.getChildAdapterPosition(r2) != MultiContactsSelectorBottomSheet.m16223$$Nest$fgetitems(this.this$0).size()) goto L6;
            r1.bottom = MultiContactsSelectorBottomSheet.m16224$$Nest$fgetlistPaddingTop(this.this$0);
            return;
        }
    }

    public interface SelectorListener {
        void onUserSelected(List<Long> r1);
    }

    /* renamed from: $r8$lambda$-_4F8HYyGTbSOCR-JpbuL_dRXKw, reason: not valid java name */
    public static /* synthetic */ void m16219$r8$lambda$_4F8HYyGTbSOCRJpbuL_dRXKw(MultiContactsSelectorBottomSheet r0) {
        r0.lambda$new$3();
    }

    /* renamed from: $r8$lambda$EhOEGlQl_RG1LoPlP8-KlLcHg9A, reason: not valid java name */
    public static /* synthetic */ void m16220$r8$lambda$EhOEGlQl_RG1LoPlP8KlLcHg9A(MultiContactsSelectorBottomSheet r0) {
        r0.lambda$new$5();
    }

    public static /* synthetic */ void $r8$lambda$NIM91mn38x5PhScaoacbnkziPVc(MultiContactsSelectorBottomSheet r0, int r1, View r2, int r3, float r4, float r5) {
        r0.lambda$new$4(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$UyVgzZ3SbXJrL9opater24pwEp8(MultiContactsSelectorBottomSheet r0, View r1) {
        r0.lambda$updateSectionCell$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$_kp_5j53VlfMfmOMDPzmT08hoZ8(MultiContactsSelectorBottomSheet r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$glDhMeOM0SAXMm6LWvhpRIHDmhw(MultiContactsSelectorBottomSheet r0, String r1, List r2) {
        r0.lambda$loadData$1(r1, r2);
    }

    /* renamed from: $r8$lambda$o-8PEIAmdNFBuM4MQPV8KGDaF3k, reason: not valid java name */
    public static /* synthetic */ void m16221$r8$lambda$o8PEIAmdNFBuM4MQPV8KGDaF3k(MultiContactsSelectorBottomSheet r0, HashSet r1, List r2) {
        r0.lambda$loadData$0(r1, r2);
    }

    /* renamed from: $r8$lambda$pp8IJHXyk53TdFoDpsszcj1E-zU, reason: not valid java name */
    public static /* synthetic */ void m16222$r8$lambda$pp8IJHXyk53TdFoDpsszcj1EzU(MultiContactsSelectorBottomSheet r0, String r1) {
        r0.onSearch(r1);
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16223$$Nest$fgetitems(MultiContactsSelectorBottomSheet r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetlistPaddingTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16224$$Nest$fgetlistPaddingTop(MultiContactsSelectorBottomSheet r0) {
        return r0.listPaddingTop;
    }

    /* renamed from: -$$Nest$fgetquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16225$$Nest$fgetquery(MultiContactsSelectorBottomSheet r0) {
        return r0.query;
    }

    /* renamed from: -$$Nest$fgetrecipientsBtnExtraSpace, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m16226$$Nest$fgetrecipientsBtnExtraSpace(MultiContactsSelectorBottomSheet r0) {
        return r0.recipientsBtnExtraSpace;
    }

    /* renamed from: -$$Nest$fgetsearchField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelectorSearchCell m16227$$Nest$fgetsearchField(MultiContactsSelectorBottomSheet r0) {
        return r0.searchField;
    }

    /* renamed from: -$$Nest$fgetselectorAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelectorAdapter m16228$$Nest$fgetselectorAdapter(MultiContactsSelectorBottomSheet r0) {
        return r0.selectorAdapter;
    }

    /* renamed from: -$$Nest$fputlistPaddingTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16229$$Nest$fputlistPaddingTop(MultiContactsSelectorBottomSheet r0, int r1) {
        r0.listPaddingTop = r1;
    }

    /* renamed from: -$$Nest$fputrecipientsBtnExtraSpace, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16230$$Nest$fputrecipientsBtnExtraSpace(MultiContactsSelectorBottomSheet r0, float r1) {
        r0.recipientsBtnExtraSpace = r1;
    }

    /* renamed from: -$$Nest$mcreateRecipientsBtnSpaceSpan, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16231$$Nest$mcreateRecipientsBtnSpaceSpan(MultiContactsSelectorBottomSheet r0) {
        r0.createRecipientsBtnSpaceSpan();
    }

    /* renamed from: -$$Nest$mloadData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16232$$Nest$mloadData(MultiContactsSelectorBottomSheet r0, String r1) {
        r0.loadData(r1);
    }

    /* renamed from: -$$Nest$mupdateActionButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16233$$Nest$mupdateActionButton(MultiContactsSelectorBottomSheet r0, boolean r1) {
        r0.updateActionButton(r1);
    }

    public MultiContactsSelectorBottomSheet(BaseFragment r24, boolean r25, int r26, Boolean r27, Boolean r28, SelectorListener r29) {
        super(r24, r25, false, false, r24.getResourceProvider());
        this.oldItems = new ArrayList();
        ArrayList<SelectorAdapter.Item> r1 = new ArrayList();
        this.items = r1;
        HashSet<Long> r2 = new HashSet();
        this.selectedIds = r2;
        ArrayList r3 = new ArrayList();
        this.contacts = r3;
        ArrayList r4 = new ArrayList();
        this.hints = r4;
        this.foundUsers = new ArrayList();
        HashMap r5 = new HashMap();
        this.contactsMap = r5;
        ArrayList r7 = new ArrayList();
        this.contactsLetters = r7;
        this.allSelectedObjects = new LinkedHashMap();
        this.listPaddingTop = AndroidUtilities.dp(120.0f);
        this.lastRequestId = -1;
        this.remoteSearchRunnable = new AnonymousClass1(this);
        this.maxCount = r26;
        this.filterBots = r27;
        this.filterPremium = r28;
        this.selectorListener = r29;
        this.actionBar.setTitle(getTitle());
        AnonymousClass3 r9 = new AnonymousClass3(this, getContext(), this.resourcesProvider);
        this.headerView = r9;
        r9.setOnCloseClickListener(new MultiContactsSelectorBottomSheet$$ExternalSyntheticLambda0(this));
        r9.setText(getTitle());
        r9.setCloseImageVisible(false);
        r9.backDrawable.setRotation(0.0f, false);
        createRecipientsBtnSpaceSpan();
        AnonymousClass4 r11 = new AnonymousClass4(this, getContext(), this.resourcesProvider, null);
        this.searchField = r11;
        int r12 = Theme.key_dialogBackground;
        r11.setBackgroundColor(getThemedColor(r12));
        r11.setOnSearchTextChange(new MultiContactsSelectorBottomSheet$$ExternalSyntheticLambda1(this));
        r11.setHintText(LocaleController.getString(R.string.Search), false);
        AnonymousClass5 r13 = new AnonymousClass5(this, getContext());
        this.sectionCell = r13;
        ViewGroup r15 = this.containerView;
        int r8 = this.backgroundPaddingLeft;
        r15.addView(r9, 0, LayoutHelper.createFrameMarginPx(-1, -2.0f, 55, r8, 0, r8, 0));
        ViewGroup r82 = this.containerView;
        int r152 = this.backgroundPaddingLeft;
        r82.addView(r11, LayoutHelper.createFrameMarginPx(-1, -2.0f, 55, r152, 0, r152, 0));
        ViewGroup r83 = this.containerView;
        int r153 = this.backgroundPaddingLeft;
        r83.addView(r13, LayoutHelper.createFrameMarginPx(-1, 1.0f, 55, r153, 0, r153, 0));
        SelectorBtnCell r84 = new SelectorBtnCell(getContext(), this.resourcesProvider, null);
        this.buttonContainer = r84;
        r84.setClickable(true);
        r84.setOrientation(1);
        r84.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        r84.setBackgroundColor(Theme.getColor(r12, this.resourcesProvider));
        AnonymousClass6 r10 = new AnonymousClass6(this, getContext(), this.resourcesProvider);
        this.actionButton = r10;
        r10.setOnClickListener(new MultiContactsSelectorBottomSheet$$ExternalSyntheticLambda2(this));
        r84.addView(r10, LayoutHelper.createLinear(-1, 48, 87));
        ViewGroup r102 = this.containerView;
        int r154 = this.backgroundPaddingLeft;
        r102.addView(r84, LayoutHelper.createFrameMarginPx(-1, -2.0f, 87, r154, 0, r154, 0));
        this.selectorAdapter.setData(r1, this.recyclerListView);
        RecyclerListView r14 = this.recyclerListView;
        int r85 = this.backgroundPaddingLeft;
        r14.setPadding(r85, 0, r85, AndroidUtilities.dp(60.0f));
        this.recyclerListView.addOnScrollListener(new AnonymousClass7(this));
        this.recyclerListView.setOnItemClickListener(new MultiContactsSelectorBottomSheet$$ExternalSyntheticLambda3(this, r26));
        DefaultItemAnimator r16 = new DefaultItemAnimator();
        r16.setDurations(350);
        r16.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r16.setDelayAnimations(false);
        r16.setSupportsChangeAnimations(false);
        this.recyclerListView.setItemAnimator(r16);
        this.recyclerListView.addItemDecoration(new AnonymousClass8(this));
        r11.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r11.spansContainer.removeAllSpans(false);
        r11.updateSpans(false, r2, new MultiContactsSelectorBottomSheet$$ExternalSyntheticLambda4(this), null);
        r9.setText(getTitle());
        updateActionButton(false);
        r3.addAll(ContactsController.getInstance(this.currentAccount).contacts);
        r5.putAll(ContactsController.getInstance(this.currentAccount).usersSectionsDict);
        r7.addAll(ContactsController.getInstance(this.currentAccount).sortedUsersSectionsArray);
        r4.addAll(MediaDataController.getInstance(this.currentAccount).hints);
        Boolean r17 = this.filterBots;
        if (r17 != null) goto L5;
    L7:
        updateList(false, true);
        fixNavigationBar();
        return;
    L5:
        if (r17.booleanValue() == false) goto L7;
        r4.addAll(MediaDataController.getInstance(this.currentAccount).webapps);
        goto L7
    }

    public static /* synthetic */ int access$000(MultiContactsSelectorBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    private void clearSearchAfterSelect() {
        if (isSearching() == false) goto L6;
        this.query = null;
        this.searchField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        AndroidUtilities.cancelRunOnUIThread(this.remoteSearchRunnable);
        updateItems(true, true);
        return;
    }

    private void createRecipientsBtnSpaceSpan() {
        this.recipientsBtnSpaceSpan = new AnonymousClass2(this);
    }

    private boolean filter(TLRPC.User r4) {
        if (r4 != null) goto L6;
        return false;
    L6:
        if (this.filterBots != null) goto L8;
    L10:
        Boolean r3 = this.filterPremium;
        if (r3 != null) goto L13;
        return true;
    L13:
        if (r4.premium == r3.booleanValue()) goto L17;
        return false;
    L17:
        return true;
    L8:
        if (UserObject.isBot(r4) == this.filterBots.booleanValue()) goto L10;
        return false;
    }

    private boolean isSearching() {
        return !TextUtils.isEmpty(this.query);
    }

    private /* synthetic */ void lambda$loadData$0(HashSet r4, List r5) {
        if (r5 == null) goto L13;
        Iterator r52 = r5.iterator();
    L5:
        if (r52.hasNext() == false) goto L13;
        TLRPC.User r0 = (TLRPC.User) r52.next();
        if (r0 == null) goto L5;
        if (r4.contains(Long.valueOf(r0.id)) == true) goto L5;
        if (filter(r0) == false) goto L5;
        this.foundUsers.add(r0);
        r4.add(Long.valueOf(r0.id));
    L13:
        updateList(true, true);
    }

    private /* synthetic */ void lambda$loadData$1(String r5, List r6) {
        HashSet r0 = new HashSet();
        this.foundUsers.clear();
        if (r6 == null) goto L14;
        Iterator r62 = r6.iterator();
    L6:
        if (r62.hasNext() == false) goto L14;
        TLRPC.User r1 = (TLRPC.User) r62.next();
        if (r1 == null) goto L6;
        if (r0.contains(Long.valueOf(r1.id)) == true) goto L6;
        if (filter(r1) == false) goto L6;
        this.foundUsers.add(r1);
        r0.add(Long.valueOf(r1.id));
    L14:
        Boolean r63 = this.filterBots;
        if (r63 != null) goto L17;
    L20:
        updateList(true, true);
        return;
    L17:
        if (r63.booleanValue() == false) goto L20;
        this.lastRequestId = BoostRepository.searchContacts(r5, true, new MultiContactsSelectorBottomSheet$$ExternalSyntheticLambda8(this, r0));
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        next();
    }

    private /* synthetic */ void lambda$new$3() {
        updateList(true, false);
    }

    private /* synthetic */ void lambda$new$4(int r2, View r3, int r4, float r5, float r6) {
        if ((r3 instanceof SelectorUserCell) == false) goto L14;
        TLRPC.User r32 = ((SelectorUserCell) r3).getUser();
        long r42 = r32.id;
        boolean r62 = this.selectedIds.contains(Long.valueOf(r42));
        HashSet<Long> r0 = this.selectedIds;
        if (r62 == false) goto L7;
        r0.remove(Long.valueOf(r42));
    L9:
        if (this.selectedIds.size() != (r2 + 1)) goto L12;
        this.selectedIds.remove(Long.valueOf(r42));
        showMaximumUsersToast();
        return;
    L12:
        this.searchField.updateSpans(true, this.selectedIds, new MultiContactsSelectorBottomSheet$$ExternalSyntheticLambda6(this), null);
        updateList(true, false);
        clearSearchAfterSelect();
        return;
    L7:
        r0.add(Long.valueOf(r42));
        this.allSelectedObjects.put(Long.valueOf(r42), r32);
        goto L9
    }

    private /* synthetic */ void lambda$new$5() {
        updateList(true, false);
    }

    private /* synthetic */ void lambda$updateSectionCell$6(View r2) {
        this.selectedIds.clear();
        this.searchField.spansContainer.removeAllSpans(true);
        updateList(true, false);
    }

    private void loadData(String r4) {
        boolean r1 = true;
        if (this.lastRequestId < 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.lastRequestId, true);
        this.lastRequestId = -1;
    L5:
        Boolean r0 = this.filterBots;
        if (r0 != null) goto L8;
    L10:
        r1 = false;
    L11:
        BoostRepository.searchContactsLocally(r4, r1, new MultiContactsSelectorBottomSheet$$ExternalSyntheticLambda7(this, r4));
        return;
    L8:
        if (r0.booleanValue() == false) goto L10;
        goto L10
    }

    private void next() {
        if (this.selectedIds.size() != 0) goto L5;
        return;
    L5:
        if (this.selectorListener == null) goto L21;
        ArrayList r0 = new ArrayList();
        Iterator<TLRPC.User> r1 = this.allSelectedObjects.values().iterator();
    L9:
        if (r1.hasNext() == false) goto L13;
        TLRPC.User r2 = r1.next();
        if (this.selectedIds.contains(Long.valueOf(r2.id)) == false) goto L9;
        r0.add(Long.valueOf(r2.id));
        goto L9
    L13:
        this.selectorListener.onUserSelected(r0);
        dismiss();
        return;
    }

    private void onSearch(String r3) {
        this.query = r3;
        AndroidUtilities.cancelRunOnUIThread(this.remoteSearchRunnable);
        AndroidUtilities.runOnUIThread(this.remoteSearchRunnable, 100);
    }

    public static MultiContactsSelectorBottomSheet open(Boolean r7, Boolean r8, int r9, SelectorListener r10) {
        BaseFragment r1 = LaunchActivity.getLastFragment();
        if (r1 != null) goto L6;
        return null;
    L6:
        MultiContactsSelectorBottomSheet r0 = instance;
        if (r0 == null) goto L9;
        return r0;
    L9:
        MultiContactsSelectorBottomSheet r02 = new MultiContactsSelectorBottomSheet(r1, true, r9, r7, r8, r10);
        r02.show();
        instance = r02;
        return r02;
    }

    private void showMaximumUsersToast() {
        String r0 = LocaleController.formatPluralString("BotMultiContactsSelectorLimit", this.maxCount, new Object[0]);
        BulletinFactory.of(this.container, this.resourcesProvider).createSimpleBulletin(R.raw.chats_infotip, r0).show(true);
        this.container.performHapticFeedback(3, 2);     // Catch: Exception -> L5
        return;
    }

    private void updateActionButton(boolean r7) {
        this.actionButton.setShowZero(false);
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        if (this.selectedIds.size() != 0) goto L14;
        r0.append("d").setSpan(this.recipientsBtnSpaceSpan, 0, 1, 33);
        Boolean r2 = this.filterBots;
        if (r2 != null) goto L7;
    L13:
        r0.append(LocaleController.getString(R.string.ChooseUsers));
    L15:
        this.actionButton.setCount(this.selectedIds.size(), true);
        this.actionButton.setText(r0, r7, false);
        this.actionButton.setEnabled(true);
        return;
    L7:
        if (r2.booleanValue() == false) goto L13;
        if (this.maxCount <= 1) goto L11;
        int r22 = R.string.ChooseBots;
    L12:
        r0.append(LocaleController.getString(r22));
        goto L15
    L11:
        r22 = R.string.ChooseBot;
        goto L12
    L14:
        r0.append(LocaleController.getString(R.string.GiftPremiumProceedBtn));
        goto L15
    }

    private void updateCheckboxes(boolean r10) {
        int r3 = -1;
        int r2 = 0;
        int r4 = 0;
    L4:
        if (r2 >= this.recyclerListView.getChildCount()) goto L26;
        View r5 = this.recyclerListView.getChildAt(r2);
        if ((r5 instanceof SelectorUserCell) == false) goto L25;
        int r6 = this.recyclerListView.getChildAdapterPosition(r5);
        if (r6 <= 0) goto L25;
        if (r3 != (-1)) goto L12;
        r3 = r6;
    L12:
        int r42 = r6 - 1;
        if (r42 >= 0) goto L15;
    L24:
        r4 = r6;
        goto L25
    L15:
        if (r42 >= this.items.size()) goto L24;
        SelectorAdapter.Item r43 = this.items.get(r42);
        SelectorUserCell r52 = (SelectorUserCell) r5;
        r52.setChecked(r43.checked, r10);
        TLRPC.Chat r44 = r43.chat;
        float r7 = 1.0f;
        if (r44 != null) goto L20;
        r52.setCheckboxAlpha(1.0f, r10);
        goto L24
    L20:
        if (this.selectorAdapter.getParticipantsCount(r44) <= 200) goto L22;
        r7 = 0.3f;
    L22:
        r52.setCheckboxAlpha(r7, r10);
    L25:
        r2 = r2 + 1;
        goto L4
    L26:
        if (r10 == false) goto L33;
        this.selectorAdapter.notifyItemRangeChanged(0, r3);
        SelectorAdapter r9 = this.selectorAdapter;
        r9.notifyItemRangeChanged(r4, r9.getItemCount() - r4);
        return;
    }

    private void updateList(boolean r1, boolean r2) {
        updateItems(r1, r2);
        updateCheckboxes(r1);
        updateActionButton(r1);
    }

    private void updateSectionCell(boolean r2) {
        HashSet<Long> r22 = this.selectedIds;
        if (r22 != null) goto L5;
        return;
    L5:
        int r23 = r22.size();
        SelectorAdapter r0 = this.selectorAdapter;
        if (r23 <= 0) goto L9;
        r0.setTopSectionClickListener(new MultiContactsSelectorBottomSheet$$ExternalSyntheticLambda5(this));
        return;
    L9:
        r0.setTopSectionClickListener(null);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r4) {
        SelectorAdapter r42 = new SelectorAdapter(getContext(), true, this.resourcesProvider);
        this.selectorAdapter = r42;
        r42.setGreenSelector(true);
        return this.selectorAdapter;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        AndroidUtilities.hideKeyboard(this.searchField.getEditText());
        super.dismiss();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        instance = null;
        AndroidUtilities.cancelRunOnUIThread(this.remoteSearchRunnable);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        Boolean r0 = this.filterBots;
        if (r0 == null) goto L13;
        if (r0.booleanValue() == false) goto L13;
        if (this.maxCount <= 1) goto L9;
        int r1 = R.string.ChooseBots;
    L11:
        return LocaleController.getString(r1);
    L9:
        r1 = R.string.ChooseBot;
    L13:
        return LocaleController.getString(R.string.ChooseUsers);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onConfigurationChanged(Configuration r2) {
        super.onConfigurationChanged(r2);
        updateItems(false, true);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onPreDraw(Canvas r2, int r3, float r4) {
        this.headerView.setTranslationY(Math.max(r3, AndroidUtilities.statusBarHeight + (((this.headerView.getMeasuredHeight() - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(40.0f)) / 2.0f)) + AndroidUtilities.dp(8.0f));
        this.searchField.setTranslationY(this.headerView.getTranslationY() + this.headerView.getMeasuredHeight());
        this.sectionCell.setTranslationY(this.searchField.getTranslationY() + this.searchField.getMeasuredHeight());
        this.recyclerListView.setTranslationY(((this.headerView.getMeasuredHeight() + this.searchField.getMeasuredHeight()) + this.sectionCell.getMeasuredHeight()) - AndroidUtilities.dp(8.0f));
    }

    public void scrollToTop(boolean r4) {
        if (r4 == false) goto L5;
        LinearSmoothScrollerCustom r42 = new LinearSmoothScrollerCustom(getContext(), 2, 0.6f);
        r42.setTargetPosition(1);
        r42.setOffset(AndroidUtilities.dp(36.0f));
        this.recyclerListView.getLayoutManager().startSmoothScroll(r42);
        return;
    L5:
        this.recyclerListView.scrollToPosition(0);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void updateItems(boolean r17, boolean r18) {
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        if (isSearching() == false) goto L9;
        Iterator<TLRPC.User> r1 = this.foundUsers.iterator();
        int r4 = 0;
    L6:
        if (r1.hasNext() == false) goto L67;
        TLRPC.User r5 = r1.next();
        r4 = r4 + AndroidUtilities.dp(56.0f);
        this.items.add(SelectorAdapter.Item.asUser(r5, this.selectedIds.contains(Long.valueOf(r5.id))));
    L67:
        if (this.items.isEmpty() == false) goto L69;
        this.items.add(SelectorAdapter.Item.asNoUsers());
        r4 = r4 + AndroidUtilities.dp(150.0f);
    L69:
        this.items.add(SelectorAdapter.Item.asPad(Math.max(0, ((int) (AndroidUtilities.displaySize.y * 0.6f)) - r4)));
        updateSectionCell(r17);
        if (r18 == false) goto L113;
        SelectorAdapter r12 = this.selectorAdapter;
        if (r12 == null) goto L114;
        if (r17 == false) goto L76;
        r12.setItems(this.oldItems, this.items);
        return;
    L76:
        r12.notifyDataSetChanged();
        return;
    L114:
        return;
    L113:
        return;
    L9:
        if (this.hints.isEmpty() == true) goto L29;
        ArrayList r13 = new ArrayList();
        Iterator<TLRPC.TL_topPeer> r52 = this.hints.iterator();
        int r6 = 0;
    L12:
        if (r52.hasNext() == false) goto L27;
        TLRPC.User r7 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r52.next().peer.user_id));
        if (r7.self == true) goto L12;
        if (r7.bot == true) goto L12;
        if (UserObject.isService(r7.id) == true) goto L12;
        if (UserObject.isDeleted(r7) == true) goto L12;
        if (filter(r7) == false) goto L12;
        r6 = r6 + AndroidUtilities.dp(56.0f);
        r13.add(SelectorAdapter.Item.asUser(r7, this.selectedIds.contains(Long.valueOf(r7.id))));
        goto L12
    L27:
        if (r13.isEmpty() == true) goto L30;
        r6 = r6 + AndroidUtilities.dp(32.0f);
        this.items.add(SelectorAdapter.Item.asTopSection(LocaleController.getString(R.string.GiftPremiumFrequentContacts)));
        this.items.addAll(r13);
    L30:
        long r72 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        Boolean r14 = this.filterBots;
        if (r14 != null) goto L33;
    L46:
        Iterator<String> r15 = this.contactsLetters.iterator();
    L48:
        if (r15.hasNext() == false) goto L65;
        String r53 = r15.next();
        ArrayList r9 = new ArrayList();
        List<TLRPC.TL_contact> r10 = this.contactsMap.get(r53);
        if (r10 == null) goto L48;
        Iterator<TLRPC.TL_contact> r102 = r10.iterator();
    L54:
        if (r102.hasNext() == false) goto L63;
        TLRPC.TL_contact r11 = r102.next();
        if (r11.user_id == r72) goto L54;
        TLRPC.User r112 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r11.user_id));
        if (filter(r112) == false) goto L54;
        r6 = r6 + AndroidUtilities.dp(56.0f);
        r9.add(SelectorAdapter.Item.asUser(r112, this.selectedIds.contains(Long.valueOf(r112.id))));
        goto L54
    L63:
        if (r9.isEmpty() == true) goto L48;
        r6 = r6 + AndroidUtilities.dp(32.0f);
        this.items.add(SelectorAdapter.Item.asLetter(r53.toUpperCase()));
        this.items.addAll(r9);
        goto L48
    L65:
        r4 = r6;
        goto L67
    L33:
        if (r14.booleanValue() == false) goto L46;
        ArrayList r16 = new ArrayList();
        ArrayList<TLRPC.Dialog> r54 = MessagesController.getInstance(this.currentAccount).getAllDialogs();
        int r92 = r54.size();
        int r103 = 0;
    L35:
        if (r103 >= r92) goto L44;
        TLRPC.Dialog r113 = r54.get(r103);
        r103 = r103 + 1;
        TLRPC.Dialog r114 = r113;
        if (r114.id < 0) goto L35;
        TLRPC.User r115 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r114.id));
        if (filter(r115) == false) goto L35;
        r6 = r6 + AndroidUtilities.dp(56.0f);
        r16.add(SelectorAdapter.Item.asUser(r115, this.selectedIds.contains(Long.valueOf(r115.id))));
        goto L35
    L44:
        if (r16.isEmpty() == true) goto L46;
        r6 = r6 + AndroidUtilities.dp(32.0f);
        this.items.add(SelectorAdapter.Item.asTopSection(LocaleController.getString(R.string.SearchApps)));
        this.items.addAll(r16);
        goto L46
    L29:
        r6 = 0;
        goto L30
    }
}
