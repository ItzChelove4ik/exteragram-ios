package org.telegram.ui.web;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.TimeZone;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalFragment;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.web.AddressBarList;
import org.telegram.ui.web.BrowserHistory;
import org.telegram.ui.web.WebMetadataCache;

/* loaded from: classes6.dex */
public class HistoryFragment extends UniversalFragment {
    private final Runnable closeToTabs;
    private StickerEmptyView emptyView;
    private ArrayList<BrowserHistory.Entry> history;
    private String query;
    private ActionBarMenuItem searchItem;
    private boolean searchLoading;
    private final ArrayList<BrowserHistory.Entry> searchResults;
    public HashSet<Integer> selected;
    private NumberTextView selectedCount;
    private final Utilities.Callback<BrowserHistory.Entry> whenClicked;

    /* renamed from: org.telegram.ui.web.HistoryFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ HistoryFragment this$0;

        public static /* synthetic */ void $r8$lambda$qwC3jfjaz04jNZi9WGFAK8NwxN8(View r1) {
            if ((r1 instanceof AddressBarList.BookmarkView) == false) goto L6;
            ((AddressBarList.BookmarkView) r1).setChecked(false);
            return;
        }

        public AnonymousClass1(HistoryFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L10;
            boolean r22 = HistoryFragment.access$000(this.this$0).isActionModeShowed();
            HistoryFragment r0 = this.this$0;
            if (r22 == false) goto L8;
            HistoryFragment.access$100(r0).hideActionMode();
            this.this$0.selected.clear();
            AndroidUtilities.forEachViews(this.this$0.listView, new HistoryFragment$1$$ExternalSyntheticLambda0());
            return;
        L8:
            r0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.web.HistoryFragment$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        private Runnable applySearch;
        final /* synthetic */ HistoryFragment this$0;

        public static /* synthetic */ void $r8$lambda$3GKJVWHkhlbHKQ3g8foLD18zeDE(AnonymousClass2 r0, ArrayList r1, String r2) {
            r0.lambda$$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$OLsxHaJywOSuzMF7bY6SvQPI9Qw(AnonymousClass2 r0) {
            r0.lambda$$2();
        }

        public static /* synthetic */ void $r8$lambda$oDOsnr8QNG0teFwV6FPEW1i8z6o(AnonymousClass2 r0, ArrayList r1) {
            r0.lambda$$0(r1);
        }

        public AnonymousClass2(HistoryFragment r1) {
            this.this$0 = r1;
            this.applySearch = new HistoryFragment$2$$ExternalSyntheticLambda0(this);
        }

        private /* synthetic */ void lambda$$0(ArrayList r2) {
            HistoryFragment.m22049$$Nest$fgetsearchResults(this.this$0).clear();
            HistoryFragment.m22049$$Nest$fgetsearchResults(this.this$0).addAll(r2);
            HistoryFragment.m22051$$Nest$fputsearchLoading(this.this$0, false);
            UniversalRecyclerView r1 = this.this$0.listView;
            if (r1 == null) goto L6;
            r1.adapter.update(true);
            return;
        }

        private /* synthetic */ void lambda$$1(ArrayList r5, String r6) {
            ArrayList r0 = new ArrayList();
            int r1 = 0;
        L4:
            if (r1 >= r5.size()) goto L15;
            BrowserHistory.Entry r2 = (BrowserHistory.Entry) r5.get(r1);
            if (matches(r2.url, r6) == true) goto L13;
            WebMetadataCache.WebMetadata r3 = r2.meta;
            if (r3 == null) goto L14;
            if (matches(r3.title, r6) == true) goto L13;
            if (matches(r2.meta.sitename, r6) == true) goto L13;
        L14:
            r1 = r1 + 1;
        L13:
            r0.add(r2);
            goto L14
        L15:
            AndroidUtilities.runOnUIThread(new HistoryFragment$2$$ExternalSyntheticLambda2(this, r0));
        }

        private /* synthetic */ void lambda$$2() {
            ArrayList r0 = new ArrayList(HistoryFragment.m22047$$Nest$fgethistory(this.this$0));
            String r1 = HistoryFragment.m22048$$Nest$fgetquery(this.this$0);
            Utilities.searchQueue.postRunnable(new HistoryFragment$2$$ExternalSyntheticLambda1(this, r0, r1));
        }

        private void scheduleSearch() {
            HistoryFragment.m22051$$Nest$fputsearchLoading(this.this$0, true);
            AndroidUtilities.cancelRunOnUIThread(this.applySearch);
            AndroidUtilities.runOnUIThread(this.applySearch, 500);
        }

        public boolean matches(String r5, String r6) {
            if (r5 == null) goto L22;
            if (r6 == null) goto L22;
            String r52 = r5.toLowerCase();
            String r62 = r6.toLowerCase();
            if (r52.startsWith(r62) == false) goto L9;
        L21:
            return true;
        L9:
            if (r52.contains(" " + r62) == true) goto L21;
            if (r52.contains("." + r62) == true) goto L21;
            String r53 = AndroidUtilities.translitSafe(r52);
            String r63 = AndroidUtilities.translitSafe(r62);
            if (r53.startsWith(r63) == true) goto L21;
            if (r53.contains(" " + r63) == true) goto L21;
            if (r53.contains("." + r63) == true) goto L21;
            return false;
        L22:
            return false;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            HistoryFragment.m22050$$Nest$fputquery(this.this$0, null);
            HistoryFragment.m22051$$Nest$fputsearchLoading(this.this$0, false);
            AndroidUtilities.cancelRunOnUIThread(this.applySearch);
            UniversalRecyclerView r0 = this.this$0.listView;
            if (r0 == null) goto L5;
            r0.adapter.update(true);
            this.this$0.listView.layoutManager.scrollToPositionWithOffset(0, 0);
        L5:
            SpoilersTextView r02 = HistoryFragment.m22046$$Nest$fgetemptyView(this.this$0).title;
            if (TextUtils.isEmpty(HistoryFragment.m22048$$Nest$fgetquery(this.this$0)) == false) goto L8;
            int r3 = R.string.WebNoHistory;
        L9:
            r02.setText(LocaleController.getString(r3));
            return;
        L8:
            r3 = R.string.WebNoSearchedHistory;
            goto L9
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r5) {
            boolean r0 = !TextUtils.isEmpty(HistoryFragment.m22048$$Nest$fgetquery(this.this$0));
            String r52 = r5.getText().toString();
            if (TextUtils.equals(HistoryFragment.m22048$$Nest$fgetquery(this.this$0), r52) == true) goto L9;
            HistoryFragment.m22050$$Nest$fputquery(this.this$0, r52);
            scheduleSearch();
            SpoilersTextView r2 = HistoryFragment.m22046$$Nest$fgetemptyView(this.this$0).title;
            if (TextUtils.isEmpty(r52) == false) goto L7;
            int r3 = R.string.WebNoHistory;
        L8:
            r2.setText(LocaleController.getString(r3));
            goto L9
        L7:
            r3 = R.string.WebNoSearchedHistory;
        L9:
            UniversalRecyclerView r22 = this.this$0.listView;
            if (r22 == null) goto L15;
            r22.adapter.update(true);
            if (r0 == (!TextUtils.isEmpty(r52))) goto L16;
            this.this$0.listView.layoutManager.scrollToPositionWithOffset(0, 0);
            return;
        L16:
            return;
        }
    }

    /* renamed from: org.telegram.ui.web.HistoryFragment$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ HistoryFragment this$0;

        public AnonymousClass3(HistoryFragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            HistoryFragment r0 = this.this$0;
            if (r0.listView.scrollingByUser == false) goto L6;
            AndroidUtilities.hideKeyboard(r0.fragmentView);
            return;
        }
    }

    public static /* synthetic */ boolean $r8$lambda$Qov0B6kozvKjSbGfcOAhy0fAaYk(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$d-fezr-CrkurZHf4eC545ZVcVD8, reason: not valid java name */
    public static /* synthetic */ void m22045$r8$lambda$dfezrCrkurZHf4eC545ZVcVD8(HistoryFragment r0, ArrayList r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m22046$$Nest$fgetemptyView(HistoryFragment r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgethistory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m22047$$Nest$fgethistory(HistoryFragment r0) {
        return r0.history;
    }

    /* renamed from: -$$Nest$fgetquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m22048$$Nest$fgetquery(HistoryFragment r0) {
        return r0.query;
    }

    /* renamed from: -$$Nest$fgetsearchResults, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m22049$$Nest$fgetsearchResults(HistoryFragment r0) {
        return r0.searchResults;
    }

    /* renamed from: -$$Nest$fputquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22050$$Nest$fputquery(HistoryFragment r0, String r1) {
        r0.query = r1;
    }

    /* renamed from: -$$Nest$fputsearchLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22051$$Nest$fputsearchLoading(HistoryFragment r0, boolean r1) {
        r0.searchLoading = r1;
    }

    public HistoryFragment(Runnable r2, Utilities.Callback<BrowserHistory.Entry> r3) {
        this.history = BrowserHistory.getHistory(new HistoryFragment$$ExternalSyntheticLambda0(this));
        this.searchResults = new ArrayList();
        this.selected = new HashSet();
        this.closeToTabs = r2;
        this.whenClicked = r3;
    }

    public static /* synthetic */ ActionBar access$000(HistoryFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(HistoryFragment r0) {
        return r0.actionBar;
    }

    private /* synthetic */ void lambda$new$0(ArrayList r1) {
        this.history = r1;
        if (this.listView.isAttachedToWindow() == false) goto L6;
        this.listView.adapter.update(true);
        return;
    }

    public void clickSelect(UItem r1, View r2) {
    }

    @Override // org.telegram.ui.Components.UniversalFragment, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r13) {
        this.fragmentView = super.createView(r13);
        ActionBar r0 = this.actionBar;
        int r1 = Theme.key_windowBackgroundWhite;
        r0.setBackgroundColor(getThemedColor(r1));
        this.actionBar.setActionModeColor(Theme.getColor(r1));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        ActionBar r02 = this.actionBar;
        int r12 = Theme.key_windowBackgroundWhiteBlackText;
        r02.setTitleColor(getThemedColor(r12));
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarActionModeDefaultSelector), false);
        this.actionBar.setItemsColor(getThemedColor(r12), false);
        this.actionBar.setItemsColor(getThemedColor(r12), true);
        this.actionBar.setCastShadows(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r03 = this.actionBar.createActionMode();
        NumberTextView r2 = new NumberTextView(r03.getContext());
        this.selectedCount = r2;
        r2.setTextSize(18);
        this.selectedCount.setTypeface(AndroidUtilities.bold());
        this.selectedCount.setTextColor(getThemedColor(Theme.key_actionBarActionModeDefaultIcon));
        this.selectedCount.setOnTouchListener(new HistoryFragment$$ExternalSyntheticLambda1());
        r03.addView(this.selectedCount, LayoutHelper.createLinear(0, -1, 1.0f, 65, 0, 0, 0));
        ActionBarMenuItem r04 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search, getResourceProvider()).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem = r04;
        r04.setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.searchItem.setContentDescription(LocaleController.getString(R.string.Search));
        EditTextBoldCursor r05 = this.searchItem.getSearchField();
        r05.setTextColor(getThemedColor(r12));
        r05.setHintTextColor(getThemedColor(Theme.key_player_time));
        r05.setCursorColor(getThemedColor(r12));
        StickerEmptyView r06 = new StickerEmptyView(r13, null, 1);
        this.emptyView = r06;
        SpoilersTextView r132 = r06.title;
        if (TextUtils.isEmpty(this.query) == false) goto L5;
        int r07 = R.string.WebNoHistory;
    L6:
        r132.setText(LocaleController.getString(r07));
        this.emptyView.subtitle.setVisibility(8);
        this.emptyView.showProgress(false, false);
        this.emptyView.setAnimateLayoutChange(true);
        ((FrameLayout) this.fragmentView).addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setEmptyView(this.emptyView);
        this.listView.addOnScrollListener(new AnonymousClass3(this));
        return this.fragmentView;
    L5:
        r07 = R.string.WebNoSearchedHistory;
        goto L6
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void fillItems(ArrayList<UItem> r12, UniversalAdapter r13) {
        Calendar r132 = Calendar.getInstance();
        r132.setTimeZone(TimeZone.getDefault());
        int r6 = 0;
        if (TextUtils.isEmpty(this.query) == false) goto L12;
        ArrayList<BrowserHistory.Entry> r0 = this.history;
        if (r0 == null) goto L22;
        int r02 = r0.size() - 1;
    L7:
        if (r02 < 0) goto L22;
        BrowserHistory.Entry r7 = this.history.get(r02);
        r132.setTimeInMillis(r7.time);
        int r8 = ((r132.get(1) * 10000) + (r132.get(2) * 100)) + r132.get(5);
        if (r6 == r8) goto L11;
        r12.add(UItem.asGraySection(LocaleController.formatDateChat(r7.time / 1000)));
        r6 = r8;
    L11:
        r12.add(AddressBarList.BookmarkView.Factory.as(r7, this.query));
        r02 = r02 - 1;
    L22:
        if (r12.isEmpty() == true) goto L31;
        r12.add(UItem.asShadow(null));
        return;
    L31:
        return;
    L12:
        int r03 = this.searchResults.size() - 1;
    L13:
        if (r03 < 0) goto L19;
        BrowserHistory.Entry r72 = this.searchResults.get(r03);
        r132.setTimeInMillis(r72.time);
        int r82 = ((r132.get(1) * 10000) + (r132.get(2) * 100)) + r132.get(5);
        if (r6 == r82) goto L17;
        r12.add(UItem.asGraySection(LocaleController.formatDateChat(r72.time / 1000)));
        r6 = r82;
    L17:
        r12.add(AddressBarList.BookmarkView.Factory.as(r72, this.query));
        r03 = r03 - 1;
        goto L13
    L19:
        if (this.searchLoading == false) goto L22;
        r12.add(UItem.asFlicker(32));
        r12.add(UItem.asFlicker(32));
        r12.add(UItem.asFlicker(32));
        goto L22
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.WebHistory);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (AndroidUtilities.computePerceivedBrightness(getThemedColor(Theme.key_windowBackgroundWhite)) <= 0.721f) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (r1.instanceOf(AddressBarList.BookmarkView.Factory.class) == true) goto L5;
        return;
    L5:
        if (this.actionBar.isActionModeShowed() == false) goto L8;
        clickSelect(r1, r2);
        return;
    L8:
        finishFragment();
        this.whenClicked.run((BrowserHistory.Entry) r1.object2);
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        return false;
    }
}
