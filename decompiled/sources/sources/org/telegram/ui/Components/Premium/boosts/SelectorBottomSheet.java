package org.telegram.ui.Components.Premium.boosts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.boosts.adapters.SelectorAdapter;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorBtnCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorCountryCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorHeaderCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorSearchCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorUserCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;

/* loaded from: classes7.dex */
public class SelectorBottomSheet extends BottomSheetWithRecyclerListView {
    private final ButtonWithCounterView actionButton;
    private final HashMap<Long, TLObject> allSelectedObjects;
    private final Paint backgroundPaint;
    private final SelectorBtnCell buttonContainer;
    private final List<String> countriesLetters;
    private final List<TLRPC.TL_help_country> countriesList;
    private final Map<String, List<TLRPC.TL_help_country>> countriesMap;
    private final TLRPC.Chat currentChat;
    private final SelectorHeaderCell headerView;
    private final ArrayList<SelectorAdapter.Item> items;
    private int listPaddingTop;
    private final ArrayList<SelectorAdapter.Item> oldItems;
    private Runnable onCloseClick;
    private final HashSet<Long> openedIds;
    private final ArrayList<TLRPC.InputPeer> peers;
    private String query;
    private final Runnable remoteSearchRunnable;
    private final SelectorSearchCell searchField;
    private final GraySectionCell sectionCell;
    private final HashSet<Long> selectedIds;
    private SelectedObjectsListener selectedObjectsListener;
    private SelectorAdapter selectorAdapter;
    private final AnimatedFloat statusBarT;
    private int top;
    private int type;
    private final ArrayList<TLRPC.InputPeer> users;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ SelectorBottomSheet this$0;

        public AnonymousClass1(SelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            String r0 = SelectorBottomSheet.m12128$$Nest$fgetquery(this.this$0);
            if (r0 == null) goto L6;
            SelectorBottomSheet r3 = this.this$0;
            SelectorBottomSheet.m12133$$Nest$mloadData(r3, SelectorBottomSheet.m12131$$Nest$fgettype(r3), false, r0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends SelectorSearchCell {
        final /* synthetic */ SelectorBottomSheet this$0;

        public AnonymousClass2(SelectorBottomSheet r1, Context r2, Theme.ResourcesProvider r3, Runnable r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            SelectorBottomSheet.m12132$$Nest$fputlistPaddingTop(this.this$0, getMeasuredHeight() + AndroidUtilities.dp(78.0f));
            SelectorBottomSheet.m12130$$Nest$fgetselectorAdapter(this.this$0).notifyChangedLast();
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SelectorBottomSheet this$0;

        public AnonymousClass3(SelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(SelectorBottomSheet.m12129$$Nest$fgetsearchField(this.this$0).getEditText());
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.ItemDecoration {
        final /* synthetic */ SelectorBottomSheet this$0;

        public AnonymousClass4(SelectorBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            super.getItemOffsets(r1, r2, r3, r4);
            if (r3.getChildAdapterPosition(r2) != SelectorBottomSheet.m12126$$Nest$fgetitems(this.this$0).size()) goto L6;
            r1.bottom = SelectorBottomSheet.m12127$$Nest$fgetlistPaddingTop(this.this$0);
            return;
        }
    }

    public interface SelectedObjectsListener {
        void onChatsSelected(List<TLRPC.Chat> r1, boolean r2);

        void onCountrySelected(List<TLRPC.TL_help_country> r1);

        default void onShowToast(String r1) {
        }

        void onUsersSelected(List<TLRPC.User> r1);
    }

    /* renamed from: $r8$lambda$B1hda79q_iWasqZR1s4dCzgm-54, reason: not valid java name */
    public static /* synthetic */ void m12121$r8$lambda$B1hda79q_iWasqZR1s4dCzgm54(SelectorBottomSheet r0) {
        r0.lambda$hasChanges$10();
    }

    public static /* synthetic */ void $r8$lambda$CdttPqPp7V2G8LOPnkfoECS89_g(SelectorBottomSheet r0, long r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$FvfjbBHPl6i4YCVkw2A206QPrcs(SelectorBottomSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$QWG1uXVmOMKDLYoIwxx9VA17kjg(SelectorBottomSheet r0) {
        r0.lambda$new$4();
    }

    /* renamed from: $r8$lambda$THs9oVrfVE5VaapqawNbEm-yvxQ, reason: not valid java name */
    public static /* synthetic */ void m12122$r8$lambda$THs9oVrfVE5VaapqawNbEmyvxQ(SelectorBottomSheet r0) {
        r0.clearSearchAfterSelectChannel();
    }

    public static /* synthetic */ void $r8$lambda$VbZIraNmrHLkpsLfXQTfKumBOqo(SelectorBottomSheet r0, boolean r1, Pair r2) {
        r0.lambda$loadData$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$W7602hd2_J6fSsI_ayxmAGonvOo(SelectorBottomSheet r0, View r1) {
        r0.lambda$updateSectionCell$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$_E_3IyDsSQdg7OxT38Xz64arJHM(SelectorBottomSheet r0, String r1) {
        r0.onSearch(r1);
    }

    public static /* synthetic */ void $r8$lambda$a14LdBSS8Wli3eh_q3iBaLrW8y4(SelectorBottomSheet r0, String r1, List r2) {
        r0.lambda$loadData$8(r1, r2);
    }

    /* renamed from: $r8$lambda$fW17ZfnBYdtZ-N5AVCgd8leepOs, reason: not valid java name */
    public static /* synthetic */ void m12123$r8$lambda$fW17ZfnBYdtZN5AVCgd8leepOs(SelectorBottomSheet r0) {
        r0.lambda$hasChanges$11();
    }

    public static /* synthetic */ void $r8$lambda$h73rEpCZXV7rjwgvt4RZjK8S0GI(SelectorBottomSheet r0, View r1, int r2, float r3, float r4) {
        r0.lambda$new$5(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$kYc63HN5PlEAJgi8tLtTCe-Jqe4, reason: not valid java name */
    public static /* synthetic */ void m12124$r8$lambda$kYc63HN5PlEAJgi8tLtTCeJqe4(SelectorBottomSheet r0, List r1) {
        r0.lambda$loadData$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$r5GSNsJTyhigy4exFm2URA1HHqc(SelectorBottomSheet r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$sU38lb_u3KzPHLSK79n4IifqIZQ(SelectorBottomSheet r0) {
        r0.lambda$prepare$12();
    }

    public static /* synthetic */ void $r8$lambda$yDYvRNIRHEZGq2LZADo28TJ5oSI(SelectorBottomSheet r0, boolean r1, List r2) {
        r0.lambda$loadData$7(r1, r2);
    }

    /* renamed from: $r8$lambda$ykoR9-PaN8J-hPad0mwPHmiB1XA, reason: not valid java name */
    public static /* synthetic */ void m12125$r8$lambda$ykoR9PaN8JhPad0mwPHmiB1XA(SelectorBottomSheet r0) {
        r0.lambda$new$2();
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12126$$Nest$fgetitems(SelectorBottomSheet r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetlistPaddingTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12127$$Nest$fgetlistPaddingTop(SelectorBottomSheet r0) {
        return r0.listPaddingTop;
    }

    /* renamed from: -$$Nest$fgetquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m12128$$Nest$fgetquery(SelectorBottomSheet r0) {
        return r0.query;
    }

    /* renamed from: -$$Nest$fgetsearchField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelectorSearchCell m12129$$Nest$fgetsearchField(SelectorBottomSheet r0) {
        return r0.searchField;
    }

    /* renamed from: -$$Nest$fgetselectorAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelectorAdapter m12130$$Nest$fgetselectorAdapter(SelectorBottomSheet r0) {
        return r0.selectorAdapter;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12131$$Nest$fgettype(SelectorBottomSheet r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fputlistPaddingTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12132$$Nest$fputlistPaddingTop(SelectorBottomSheet r0, int r1) {
        r0.listPaddingTop = r1;
    }

    /* renamed from: -$$Nest$mloadData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12133$$Nest$mloadData(SelectorBottomSheet r0, int r1, boolean r2, String r3) {
        r0.loadData(r1, r2, r3);
    }

    public SelectorBottomSheet(BaseFragment r21, boolean r22, long r23) {
        super(r21, r22, false);
        this.backgroundPaint = new Paint(1);
        this.oldItems = new ArrayList();
        ArrayList<SelectorAdapter.Item> r2 = new ArrayList();
        this.items = r2;
        this.selectedIds = new HashSet();
        this.openedIds = new HashSet();
        this.peers = new ArrayList();
        this.users = new ArrayList();
        this.countriesMap = new HashMap();
        this.countriesLetters = new ArrayList();
        this.countriesList = new ArrayList();
        this.allSelectedObjects = new LinkedHashMap();
        this.listPaddingTop = AndroidUtilities.dp(134.0f);
        this.remoteSearchRunnable = new AnonymousClass1(this);
        this.backgroundPaddingLeft = 0;
        this.currentChat = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r23));
        ((ViewGroup) this.actionBar.getParent()).removeView(this.actionBar);
        ViewGroup r7 = this.containerView;
        CubicBezierInterpolator r12 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.statusBarT = new AnimatedFloat(r7, 0, 350, r12);
        SelectorHeaderCell r4 = new SelectorHeaderCell(getContext(), this.resourcesProvider);
        this.headerView = r4;
        r4.setOnCloseClickListener(new SelectorBottomSheet$$ExternalSyntheticLambda3(this));
        r4.setText(getTitle());
        r4.setCloseImageVisible(true);
        r4.backDrawable.setRotation(0.0f, false);
        AnonymousClass2 r5 = new AnonymousClass2(this, getContext(), this.resourcesProvider, null);
        this.searchField = r5;
        int r6 = Theme.key_dialogBackground;
        r5.setBackgroundColor(getThemedColor(r6));
        r5.setOnSearchTextChange(new SelectorBottomSheet$$ExternalSyntheticLambda4(this));
        this.sectionCell = new GraySectionCell(getContext(), this.resourcesProvider);
        updateSection();
        ViewGroup r72 = this.containerView;
        int r9 = this.backgroundPaddingLeft;
        r72.addView(r4, LayoutHelper.createFrameMarginPx(-1, -2.0f, 55, r9, 0, r9, 0));
        ViewGroup r42 = this.containerView;
        int r73 = this.backgroundPaddingLeft;
        r42.addView(r5, LayoutHelper.createFrameMarginPx(-1, -2.0f, 55, r73, 0, r73, 0));
        SelectorBtnCell r43 = new SelectorBtnCell(getContext(), this.resourcesProvider, null);
        this.buttonContainer = r43;
        r43.setClickable(true);
        r43.setOrientation(1);
        r43.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        r43.setBackgroundColor(Theme.getColor(r6, this.resourcesProvider));
        ButtonWithCounterView r52 = new ButtonWithCounterView(getContext(), this.resourcesProvider);
        this.actionButton = r52;
        r52.setOnClickListener(new SelectorBottomSheet$$ExternalSyntheticLambda5(this));
        r43.addView(r52, LayoutHelper.createLinear(-1, 48, 87));
        ViewGroup r53 = this.containerView;
        int r62 = this.backgroundPaddingLeft;
        r53.addView(r43, LayoutHelper.createFrameMarginPx(-1, -2.0f, 87, r62, 0, r62, 0));
        this.selectorAdapter.setData(r2, this.recyclerListView);
        RecyclerListView r24 = this.recyclerListView;
        int r44 = this.backgroundPaddingLeft;
        r24.setPadding(r44, 0, r44, AndroidUtilities.dp(60.0f));
        this.recyclerListView.addOnScrollListener(new AnonymousClass3(this));
        this.recyclerListView.setOnItemClickListener(new SelectorBottomSheet$$ExternalSyntheticLambda6(this));
        DefaultItemAnimator r25 = new DefaultItemAnimator();
        r25.setDurations(350);
        r25.setInterpolator(r12);
        r25.setDelayAnimations(false);
        r25.setSupportsChangeAnimations(false);
        this.recyclerListView.setItemAnimator(r25);
        this.recyclerListView.addItemDecoration(new AnonymousClass4(this));
        updateList(false, true);
        loadData(1, true, null);
        loadData(3, true, null);
    }

    private void clearSearchAfterSelectChannel() {
        if (isSearching() == false) goto L6;
        this.query = null;
        this.searchField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        AndroidUtilities.cancelRunOnUIThread(this.remoteSearchRunnable);
        this.peers.clear();
        this.peers.addAll(BoostRepository.getMyChannels(this.currentChat.id));
        updateList(false, false);
        updateList(true, true);
        return;
    }

    private void drawFilledStatusBar(Canvas r7, int r8) {
        this.backgroundPaint.setColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
        int r82 = Math.max(0, r8);
        AnimatedFloat r1 = this.statusBarT;
        if (r82 >= AndroidUtilities.statusBarHeight) goto L5;
        boolean r2 = true;
    L6:
        int r83 = AndroidUtilities.lerp(r82, 0, r1.set(r2));
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(this.backgroundPaddingLeft, r83, this.containerView.getWidth() - this.backgroundPaddingLeft, this.containerView.getHeight() + AndroidUtilities.dp(14.0f));
        float r84 = AndroidUtilities.dp(14.0f) * (1.0f - this.statusBarT.get());
        r7.drawRoundRect(r0, r84, r84, this.backgroundPaint);
        return;
    L5:
        r2 = false;
        goto L6
    }

    private boolean isSearching() {
        return !TextUtils.isEmpty(this.query);
    }

    private /* synthetic */ void lambda$hasChanges$10() {
        save(true);
    }

    private /* synthetic */ void lambda$hasChanges$11() {
        this.selectedIds.clear();
        this.openedIds.clear();
        dismiss();
    }

    private /* synthetic */ void lambda$loadData$6(List r2) {
        if (isSearching() == true) goto L5;
        return;
    L5:
        this.peers.clear();
        this.peers.addAll(r2);
        updateList(true, true);
        scrollToTop(true);
    }

    private /* synthetic */ void lambda$loadData$7(boolean r2, List r3) {
        if (r2 == false) goto L5;
        this.users.addAll(r3);
    L5:
        if (this.type != 1) goto L8;
        this.peers.clear();
        this.peers.addAll(r3);
        updateList(true, true);
        scrollToTop(true);
        return;
    }

    private /* synthetic */ void lambda$loadData$8(String r1, List r2) {
        this.countriesList.addAll(r2);
    }

    private /* synthetic */ void lambda$loadData$9(boolean r2, Pair r3) {
        if (r2 == false) goto L5;
        this.countriesMap.putAll((Map) r3.first);
        this.countriesLetters.addAll((Collection) r3.second);
        this.countriesMap.forEach(new SelectorBottomSheet$$ExternalSyntheticLambda15(this));
    L5:
        if (this.type != 3) goto L8;
        updateList(true, true);
        scrollToTop(true);
        return;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        save(false);
    }

    private /* synthetic */ void lambda$new$1() {
        updateList(true, false);
    }

    private /* synthetic */ void lambda$new$2() {
        updateList(true, false);
    }

    private /* synthetic */ void lambda$new$3(long r4) {
        this.selectedIds.remove(Long.valueOf(r4));
        this.searchField.updateSpans(true, this.selectedIds, new SelectorBottomSheet$$ExternalSyntheticLambda14(this), null);
        updateList(true, false);
    }

    private /* synthetic */ void lambda$new$4() {
        updateList(true, false);
    }

    private /* synthetic */ void lambda$new$5(View r11, int r12, float r13, float r14) {
        if ((r11 instanceof TextCell) == false) goto L6;
        this.allSelectedObjects.clear();
        save(true);
    L39:
        if ((r11 instanceof SelectorCountryCell) == false) goto L56;
        long r112 = ((SelectorCountryCell) r11).getCountry().default_name.hashCode();
        boolean r3 = this.selectedIds.contains(Long.valueOf(r112));
        HashSet<Long> r4 = this.selectedIds;
        if (r3 == false) goto L43;
        r4.remove(Long.valueOf(r112));
    L45:
        if (this.selectedIds.size() == (BoostRepository.giveawayCountriesMax() + 1)) goto L47;
    L50:
        this.searchField.updateSpans(true, this.selectedIds, new SelectorBottomSheet$$ExternalSyntheticLambda13(this), this.countriesList);
        if (isSearching() == false) goto L54;
        this.query = null;
        this.searchField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        updateList(false, false);
        updateList(true, true);
        return;
    L54:
        updateList(true, false);
        return;
    L47:
        if (this.type != 3) goto L50;
        this.selectedIds.remove(Long.valueOf(r112));
        showMaximumUsersToast();
        return;
    L43:
        r4.add(Long.valueOf(r112));
        goto L45
    L56:
        return;
    L6:
        if ((r11 instanceof SelectorUserCell) == false) goto L39;
        SelectorUserCell r122 = (SelectorUserCell) r11;
        TLRPC.User r32 = r122.getUser();
        TLRPC.Chat r123 = r122.getChat();
        if (r32 == null) goto L10;
        long r42 = r32.id;
    L11:
        boolean r6 = this.selectedIds.contains(Long.valueOf(r42));
        HashSet<Long> r7 = this.selectedIds;
        if (r6 == false) goto L14;
        r7.remove(Long.valueOf(r42));
    L20:
        if (this.selectedIds.size() != 11) goto L24;
        if (this.type != 1) goto L24;
    L27:
        this.selectedIds.remove(Long.valueOf(r42));
        showMaximumUsersToast();
        return;
    L24:
        if (this.selectedIds.size() == (BoostRepository.giveawayAddPeersMax() + 1)) goto L26;
    L29:
        this.searchField.updateSpans(true, this.selectedIds, new SelectorBottomSheet$$ExternalSyntheticLambda10(this), null);
        updateList(true, false);
        if (r123 != null) goto L32;
    L36:
        if (r123 == null) goto L39;
        clearSearchAfterSelectChannel();
        goto L39
    L32:
        if (ChatObject.isPublic(r123) == true) goto L36;
        if (this.selectedIds.contains(Long.valueOf(r42)) == false) goto L36;
        BoostDialogs.showPrivateChannelAlert(r123, getBaseFragment().getContext(), this.resourcesProvider, new SelectorBottomSheet$$ExternalSyntheticLambda11(this, r42), new SelectorBottomSheet$$ExternalSyntheticLambda12(this));
        goto L39
    L26:
        if (this.type != 2) goto L29;
    L14:
        r7.add(Long.valueOf(r42));
        HashMap<Long, TLObject> r62 = this.allSelectedObjects;
        Long r72 = Long.valueOf(r42);
        if (r32 != null) goto L18;
        r32 = r123;
    L18:
        r62.put(r72, r32);
        goto L20
    L10:
        r42 = -r123.id;
        goto L11
    }

    private /* synthetic */ void lambda$prepare$12() {
        updateList(true, false);
    }

    private /* synthetic */ void lambda$updateSectionCell$13(View r2) {
        this.selectedIds.clear();
        this.searchField.spansContainer.removeAllSpans(true);
        updateList(true, false);
    }

    private void loadData(int r14, boolean r15, String r16) {
        if (r14 != 1) goto L5;
        BoostRepository.loadChatParticipants(this.currentChat.id, 0, r16, 0, 50, new SelectorBottomSheet$$ExternalSyntheticLambda8(this, r15));
        return;
    L5:
        if (r14 != 2) goto L7;
        BoostRepository.searchChats(this.currentChat.id, 0, r16, 50, new SelectorBottomSheet$$ExternalSyntheticLambda7(this));
        return;
    L7:
        if (r14 == 3) goto L9;
        return;
    L9:
        BoostRepository.loadCountries(new SelectorBottomSheet$$ExternalSyntheticLambda9(this, r15));
    }

    public static boolean matchLocal(TLObject r4, String r5) {
        if (TextUtils.isEmpty(r5) == false) goto L6;
        return true;
    L6:
        if ((r4 instanceof TLRPC.TL_help_country) == false) goto L17;
        TLRPC.TL_help_country r42 = (TLRPC.TL_help_country) r4;
        String r0 = AndroidUtilities.translitSafe(r42.default_name).toLowerCase();
        if (r0.startsWith(r5) == false) goto L10;
    L16:
        return true;
    L10:
        if (r0.contains(" " + r5) == true) goto L16;
        String r43 = AndroidUtilities.translitSafe(r42.iso2).toLowerCase();
        if (r43.startsWith(r5) == true) goto L16;
        if (r43.contains(" " + r5) == true) goto L16;
        return false;
    L17:
        return false;
    }

    private void onSearch(String r6) {
        this.query = r6;
        int r62 = this.type;
        if (r62 != 1) goto L5;
        AndroidUtilities.cancelRunOnUIThread(this.remoteSearchRunnable);
        AndroidUtilities.runOnUIThread(this.remoteSearchRunnable, 350);
        return;
    L5:
        if (r62 != 2) goto L7;
        boolean r63 = isSearching();
        Runnable r3 = this.remoteSearchRunnable;
        if (r63 == true) goto L15;
        AndroidUtilities.cancelRunOnUIThread(r3);
        this.peers.clear();
        this.peers.addAll(BoostRepository.getMyChannels(this.currentChat.id));
        updateItems(false, true);
        scrollToTop(true);
        return;
    L15:
        AndroidUtilities.cancelRunOnUIThread(r3);
        AndroidUtilities.runOnUIThread(this.remoteSearchRunnable, 350);
        return;
    L7:
        if (r62 == 3) goto L9;
        return;
    L9:
        updateItems(false, true);
        scrollToTop(true);
    }

    private void save(boolean r7) {
        if (this.selectedIds.size() != 0) goto L6;
        if (r7 == true) goto L6;
        return;
    L6:
        int r72 = this.type;
        if (r72 != 1) goto L9;
        ArrayList r73 = new ArrayList();
        Iterator<TLObject> r0 = this.allSelectedObjects.values().iterator();
    L37:
        if (r0.hasNext() == false) goto L43;
        TLObject r1 = r0.next();
        if ((r1 instanceof TLRPC.User) == false) goto L37;
        TLRPC.User r12 = (TLRPC.User) r1;
        if (this.selectedIds.contains(Long.valueOf(r12.id)) == false) goto L37;
        r73.add(r12);
        goto L37
    L43:
        SelectedObjectsListener r6 = this.selectedObjectsListener;
        if (r6 == null) goto L68;
        r6.onUsersSelected(r73);
        return;
    L68:
        return;
    L9:
        if (r72 != 2) goto L11;
        ArrayList r74 = new ArrayList();
        Iterator<TLObject> r13 = this.allSelectedObjects.values().iterator();
    L25:
        if (r13.hasNext() == false) goto L31;
        TLObject r2 = r13.next();
        if ((r2 instanceof TLRPC.Chat) == false) goto L25;
        TLRPC.Chat r22 = (TLRPC.Chat) r2;
        if (this.selectedIds.contains(Long.valueOf(-r22.id)) == false) goto L25;
        r74.add(r22);
        goto L25
    L31:
        SelectedObjectsListener r62 = this.selectedObjectsListener;
        if (r62 == null) goto L67;
        r62.onChatsSelected(r74, true);
        return;
    L67:
        return;
    L11:
        if (r72 != 3) goto L70;
        ArrayList r75 = new ArrayList();
        Iterator<TLRPC.TL_help_country> r02 = this.countriesList.iterator();
    L15:
        if (r02.hasNext() == false) goto L19;
        TLRPC.TL_help_country r14 = r02.next();
        if (this.selectedIds.contains(Long.valueOf(r14.default_name.hashCode())) == false) goto L15;
        r75.add(r14);
        goto L15
    L19:
        SelectedObjectsListener r63 = this.selectedObjectsListener;
        if (r63 == null) goto L66;
        r63.onCountrySelected(r75);
        return;
    L66:
        return;
    }

    private void showMaximumUsersToast() {
        int r0 = this.type;
        if (r0 != 1) goto L5;
        String r02 = LocaleController.getString(R.string.BoostingSelectUpToWarningUsers);
    L12:
        SelectedObjectsListener r3 = this.selectedObjectsListener;
        if (r3 == null) goto L16;
        r3.onShowToast(r02);
        return;
    L16:
        return;
    L5:
        if (r0 != 2) goto L7;
        r02 = LocaleController.formatPluralString("BoostingSelectUpToWarningChannelsGroupsPlural", (int) BoostRepository.giveawayAddPeersMax(), new Object[0]);
        goto L12
    L7:
        if (r0 == 3) goto L9;
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L12
    L9:
        r02 = LocaleController.formatPluralString("BoostingSelectUpToWarningCountriesPlural", (int) BoostRepository.giveawayCountriesMax(), new Object[0]);
        goto L12
    }

    private void updateActionButton(boolean r5) {
        boolean r1 = false;
        this.actionButton.setShowZero(false);
        int r0 = this.type;
        if (r0 != 1) goto L5;
        String r02 = LocaleController.getString(R.string.BoostingSaveRecipients);
    L11:
        this.actionButton.setText(r02, r5);
        this.actionButton.setCount(this.selectedIds.size(), r5);
        ButtonWithCounterView r52 = this.actionButton;
        if (this.selectedIds.size() <= 0) goto L14;
        r1 = true;
    L14:
        r52.setEnabled(r1);
        return;
    L5:
        if (r0 != 2) goto L7;
    L9:
        r02 = LocaleController.getString(R.string.Save);
        goto L11
    L7:
        if (r0 == 3) goto L9;
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L11
    }

    private void updateCheckboxes(boolean r8) {
        int r0 = 0;
    L4:
        if (r0 >= this.recyclerListView.getChildCount()) goto L23;
        View r1 = this.recyclerListView.getChildAt(r0);
        if ((r1 instanceof SelectorUserCell) == false) goto L20;
        int r2 = this.recyclerListView.getChildAdapterPosition(r1) - 1;
        if (r2 < 0) goto L22;
        if (r2 >= this.items.size()) goto L22;
        SelectorAdapter.Item r22 = this.items.get(r2);
        SelectorUserCell r4 = (SelectorUserCell) r1;
        r4.setChecked(r22.checked, r8);
        TLRPC.Chat r23 = r22.chat;
        float r5 = 1.0f;
        if (r23 != null) goto L15;
        r4.setCheckboxAlpha(1.0f, r8);
        goto L20
    L15:
        if (this.selectorAdapter.getParticipantsCount(r23) <= 200) goto L17;
        r5 = 0.3f;
    L17:
        r4.setCheckboxAlpha(r5, r8);
    L22:
        r0 = r0 + 1;
    L20:
        if ((r1 instanceof SelectorCountryCell) == false) goto L22;
        SelectorCountryCell r12 = (SelectorCountryCell) r1;
        r12.setChecked(this.selectedIds.contains(Long.valueOf(r12.getCountry().default_name.hashCode())), true);
        goto L22
    }

    private void updateList(boolean r1, boolean r2) {
        updateItems(r1, r2);
        updateCheckboxes(r1);
        updateActionButton(r1);
    }

    private void updateSection() {
        int r0 = this.type;
        if (r0 == 1) goto L12;
        if (r0 != 2) goto L7;
        String r02 = LocaleController.formatPluralString("BoostingSelectUpToGroupChannelPlural", (int) BoostRepository.giveawayAddPeersMax(), new Object[0]);
        this.sectionCell.setLayerHeight(32);
    L16:
        this.sectionCell.setText(r02);
        return;
    L7:
        if (r0 == 3) goto L9;
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L16
    L9:
        r02 = LocaleController.formatPluralString("BoostingSelectUpToCountriesPlural", (int) BoostRepository.giveawayCountriesMax(), new Object[0]);
        this.sectionCell.setLayerHeight(1);
        goto L16
    L12:
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == false) goto L14;
        String r03 = "Subscribers";
    L15:
        r02 = LocaleController.formatPluralStringComma(r03, Math.max(0, this.selectorAdapter.getParticipantsCount(this.currentChat) - 1));
        this.sectionCell.setLayerHeight(32);
        goto L16
    L14:
        r03 = "Members";
        goto L15
    }

    private void updateSectionCell(boolean r3) {
        if (this.selectedIds.size() > 0) goto L5;
    L8:
        GraySectionCell r2 = this.sectionCell;
        if (r3 == false) goto L12;
        r2.setRightText(null);
        return;
    L12:
        r2.setRightText(null, null);
        return;
    L5:
        if (this.type == 3) goto L8;
        this.sectionCell.setRightText(LocaleController.getString(R.string.UsersDeselectAll), true, new SelectorBottomSheet$$ExternalSyntheticLambda2(this));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r4) {
        SelectorAdapter r42 = new SelectorAdapter(getContext(), true, this.resourcesProvider);
        this.selectorAdapter = r42;
        return r42;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        Runnable r0 = this.onCloseClick;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        AndroidUtilities.cancelRunOnUIThread(this.remoteSearchRunnable);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        int r1 = this.type;
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L13;
        if (r1 == 3) goto L11;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        return LocaleController.getString(R.string.BoostingSelectCountry);
    L13:
        return LocaleController.getString(R.string.BoostingAddChannelOrGroup);
    L15:
        return LocaleController.getString(R.string.GiftPremium);
    }

    public int getTop() {
        int r0 = this.top;
        if (this.statusBarT.get() != 1.0f) goto L5;
        int r2 = AndroidUtilities.statusBarHeight;
    L7:
        return Math.max(0, r0 - r2);
    L5:
        r2 = 0;
        goto L7
    }

    public boolean hasChanges() {
        if (this.selectedIds.size() == this.openedIds.size()) goto L5;
    L11:
        BoostDialogs.showUnsavedChanges(this.type, getContext(), this.resourcesProvider, new SelectorBottomSheet$$ExternalSyntheticLambda0(this), new SelectorBottomSheet$$ExternalSyntheticLambda1(this));
        return true;
    L5:
        if (this.openedIds.containsAll(this.selectedIds) == false) goto L11;
        if (this.selectedIds.containsAll(this.openedIds) == false) goto L11;
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onConfigurationChanged(Configuration r2) {
        super.onConfigurationChanged(r2);
        updateItems(false, true);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onPreDraw(Canvas r3, int r4, float r5) {
        this.top = r4;
        this.headerView.setTranslationY(Math.max(r4, AndroidUtilities.statusBarHeight + (((this.headerView.getMeasuredHeight() - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(40.0f)) / 2.0f)));
        this.searchField.setTranslationY(this.headerView.getTranslationY() + this.headerView.getMeasuredHeight());
        this.recyclerListView.setTranslationY((this.headerView.getMeasuredHeight() + this.searchField.getMeasuredHeight()) - AndroidUtilities.dp(16.0f));
        drawFilledStatusBar(r3, r4);
    }

    public void prepare(List<TLObject> r6, int r7) {
        this.type = r7;
        this.query = null;
        this.openedIds.clear();
        this.selectedIds.clear();
        this.peers.clear();
        this.allSelectedObjects.clear();
        if (r7 != 1) goto L5;
        this.peers.addAll(this.users);
    L9:
        if (r6 == null) goto L30;
        Iterator<TLObject> r62 = r6.iterator();
    L12:
        if (r62.hasNext() == false) goto L30;
        TLObject r72 = r62.next();
        if ((r72 instanceof TLRPC.TL_inputPeerChat) == false) goto L16;
        long r1 = -((TLRPC.TL_inputPeerChat) r72).chat_id;
    L18:
        if ((r72 instanceof TLRPC.TL_inputPeerChannel) == false) goto L21;
        r1 = -((TLRPC.TL_inputPeerChannel) r72).channel_id;
    L21:
        if ((r72 instanceof TLRPC.Chat) == false) goto L24;
        r1 = -((TLRPC.Chat) r72).id;
    L24:
        if ((r72 instanceof TLRPC.User) == false) goto L27;
        r1 = ((TLRPC.User) r72).id;
    L27:
        if ((r72 instanceof TLRPC.TL_help_country) == false) goto L29;
        r1 = ((TLRPC.TL_help_country) r72).default_name.hashCode();
    L29:
        this.selectedIds.add(Long.valueOf(r1));
        this.allSelectedObjects.put(Long.valueOf(r1), r72);
        goto L12
    L16:
        r1 = 0;
    L30:
        this.openedIds.addAll(this.selectedIds);
        this.searchField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.searchField.spansContainer.removeAllSpans(false);
        this.searchField.updateSpans(false, this.selectedIds, new SelectorBottomSheet$$ExternalSyntheticLambda16(this), this.countriesList);
        updateSection();
        updateList(false, true);
        this.headerView.setText(getTitle());
        updateActionButton(false);
        scrollToTop(false);
        return;
    L5:
        if (r7 != 2) goto L9;
        this.peers.addAll(BoostRepository.getMyChannels(this.currentChat.id));
        goto L9
    }

    public void scrollToTop(boolean r4) {
        if (r4 == false) goto L5;
        LinearSmoothScrollerCustom r42 = new LinearSmoothScrollerCustom(getContext(), 2, 0.6f);
        r42.setTargetPosition(1);
        r42.setOffset(AndroidUtilities.dp(38.0f));
        this.recyclerListView.getLayoutManager().startSmoothScroll(r42);
        return;
    L5:
        this.recyclerListView.scrollToPosition(0);
    }

    public void setOnCloseClick(Runnable r1) {
        this.onCloseClick = r1;
    }

    public void setSelectedObjectsListener(SelectedObjectsListener r1) {
        this.selectedObjectsListener = r1;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void updateItems(boolean r11, boolean r12) {
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        if (this.type != 1) goto L5;
        this.items.add(SelectorAdapter.Item.asButton(1, R.drawable.menu_random, LocaleController.getString(R.string.GiveawayChooseUsersRandomly)));
    L5:
        this.items.add(SelectorAdapter.Item.asCustom(this.sectionCell));
        if (this.type != 3) goto L22;
        Iterator<String> r0 = this.countriesLetters.iterator();
        int r1 = 0;
    L9:
        if (r0.hasNext() == false) goto L23;
        String r3 = r0.next();
        ArrayList r4 = new ArrayList();
        Iterator<TLRPC.TL_help_country> r5 = this.countriesMap.get(r3).iterator();
    L12:
        if (r5.hasNext() == false) goto L20;
        TLRPC.TL_help_country r6 = r5.next();
        if (isSearching() == false) goto L18;
        if (matchLocal(r6, AndroidUtilities.translitSafe(this.query).toLowerCase()) == false) goto L12;
    L18:
        r1 = r1 + AndroidUtilities.dp(44.0f);
        r4.add(SelectorAdapter.Item.asCountry(r6, this.selectedIds.contains(Long.valueOf(r6.default_name.hashCode()))));
        goto L12
    L20:
        if (r4.isEmpty() == true) goto L9;
        r1 = r1 + AndroidUtilities.dp(32.0f);
        this.items.add(SelectorAdapter.Item.asLetter(r3.toUpperCase()));
        this.items.addAll(r4);
    L23:
        ArrayList<TLRPC.InputPeer> r02 = this.peers;
        int r32 = r02.size();
        int r42 = 0;
    L24:
        if (r42 >= r32) goto L27;
        TLRPC.InputPeer r52 = r02.get(r42);
        r42 = r42 + 1;
        TLRPC.InputPeer r53 = r52;
        r1 = r1 + AndroidUtilities.dp(56.0f);
        this.items.add(SelectorAdapter.Item.asPeer(r53, this.selectedIds.contains(Long.valueOf(DialogObject.getPeerDialogId(r53)))));
        goto L24
    L27:
        if (this.items.isEmpty() == false) goto L29;
        this.items.add(SelectorAdapter.Item.asNoUsers());
        r1 = r1 + AndroidUtilities.dp(150.0f);
    L29:
        this.items.add(SelectorAdapter.Item.asPad(Math.max(0, ((int) (AndroidUtilities.displaySize.y * 0.6f)) - r1)));
        updateSectionCell(r11);
        if (r12 == false) goto L50;
        SelectorAdapter r122 = this.selectorAdapter;
        if (r122 == null) goto L51;
        if (r11 == false) goto L36;
        r122.setItems(this.oldItems, this.items);
        return;
    L36:
        r122.notifyDataSetChanged();
        return;
    L51:
        return;
    L50:
        return;
    L22:
        r1 = 0;
        goto L23
    }
}
