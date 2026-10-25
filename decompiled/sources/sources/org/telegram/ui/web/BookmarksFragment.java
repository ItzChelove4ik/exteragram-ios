package org.telegram.ui.web;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalFragment;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.web.AddressBarList;
import org.telegram.ui.web.WebMetadataCache;

/* loaded from: classes6.dex */
public class BookmarksFragment extends UniversalFragment {
    private final HashSet<String> addedUrls;
    private final Runnable closeToTabs;
    private ActionBarMenuItem gotoItem;
    public AddressBarList.BookmarksList list;
    private String query;
    private ActionBarMenuItem searchItem;
    public AddressBarList.BookmarksList searchList;
    public HashSet<Integer> selected;
    private NumberTextView selectedCount;
    private final Utilities.Callback<String> whenClicked;

    /* renamed from: org.telegram.ui.web.BookmarksFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ BookmarksFragment this$0;

        public static /* synthetic */ void $r8$lambda$jRavSu1nuJIA_feq_rh8X4U3_FE(View r1) {
            if ((r1 instanceof AddressBarList.BookmarkView) == false) goto L6;
            ((AddressBarList.BookmarkView) r1).setChecked(false);
            return;
        }

        public AnonymousClass1(BookmarksFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L11;
            boolean r22 = BookmarksFragment.access$000(this.this$0).isActionModeShowed();
            BookmarksFragment r0 = this.this$0;
            if (r22 == false) goto L8;
            BookmarksFragment.access$100(r0).hideActionMode();
            this.this$0.selected.clear();
            AndroidUtilities.forEachViews(this.this$0.listView, new BookmarksFragment$1$$ExternalSyntheticLambda0());
            return;
        L8:
            r0.finishFragment();
            return;
        L11:
            if (r2 != R.id.menu_delete) goto L15;
            this.this$0.deleteSelectedMessages();
            return;
        L15:
            if (r2 != R.id.menu_link) goto L18;
            this.this$0.gotoMessage();
            return;
        }
    }

    /* renamed from: org.telegram.ui.web.BookmarksFragment$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        private Runnable applySearch;
        final /* synthetic */ BookmarksFragment this$0;

        public static /* synthetic */ void $r8$lambda$2F7EikPxYPGhht6DOtu4Z3vxf_U(AnonymousClass2 r0) {
            r0.lambda$$0();
        }

        public static /* synthetic */ void $r8$lambda$tXYQB1a2aEJ2Mh6B8AJNC2AhaDU(BookmarksFragment r0) {
            BookmarksFragment.m21951$$Nest$mupdateWithOffset(r0);
        }

        public AnonymousClass2(BookmarksFragment r1) {
            this.this$0 = r1;
            this.applySearch = new BookmarksFragment$2$$ExternalSyntheticLambda1(this);
        }

        private /* synthetic */ void lambda$$0() {
            AddressBarList.BookmarksList r0 = this.this$0.searchList;
            if (r0 == null) goto L6;
            r0.load();
            return;
        }

        private void scheduleSearch() {
            AndroidUtilities.cancelRunOnUIThread(this.applySearch);
            AndroidUtilities.runOnUIThread(this.applySearch, 500);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            BookmarksFragment.m21950$$Nest$fputquery(this.this$0, null);
            AndroidUtilities.cancelRunOnUIThread(this.applySearch);
            AddressBarList.BookmarksList r0 = this.this$0.searchList;
            if (r0 == null) goto L5;
            r0.detach();
            this.this$0.searchList = null;
        L5:
            UniversalRecyclerView r02 = this.this$0.listView;
            if (r02 == null) goto L9;
            r02.adapter.update(true);
            this.this$0.listView.layoutManager.scrollToPositionWithOffset(0, 0);
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r8) {
            boolean r0 = !TextUtils.isEmpty(BookmarksFragment.m21949$$Nest$fgetquery(this.this$0));
            String r82 = r8.getText().toString();
            if (TextUtils.equals(BookmarksFragment.m21949$$Nest$fgetquery(this.this$0), r82) == true) goto L8;
            BookmarksFragment.m21950$$Nest$fputquery(this.this$0, r82);
            AddressBarList.BookmarksList r2 = this.this$0.searchList;
            if (r2 == null) goto L7;
            r2.detach();
        L7:
            BookmarksFragment r22 = this.this$0;
            r22.searchList = new AddressBarList.BookmarksList(BookmarksFragment.access$200(r22), r82, new BookmarksFragment$2$$ExternalSyntheticLambda0(this.this$0));
            this.this$0.searchList.attach();
            scheduleSearch();
        L8:
            UniversalRecyclerView r23 = this.this$0.listView;
            if (r23 == null) goto L14;
            r23.adapter.update(true);
            if (r0 == (!TextUtils.isEmpty(r82))) goto L15;
            this.this$0.listView.layoutManager.scrollToPositionWithOffset(0, 0);
            return;
        L15:
            return;
        }
    }

    /* renamed from: org.telegram.ui.web.BookmarksFragment$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ BookmarksFragment this$0;

        public AnonymousClass3(BookmarksFragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (this.this$0.listView.canScrollVertically(1) == true) goto L10;
            boolean r12 = TextUtils.isEmpty(BookmarksFragment.m21949$$Nest$fgetquery(this.this$0));
            BookmarksFragment r22 = this.this$0;
            if (r12 == false) goto L7;
            r22.list.load();
            goto L10
        L7:
            AddressBarList.BookmarksList r13 = r22.searchList;
            if (r13 == null) goto L10;
            r13.load();
        L10:
            BookmarksFragment r0 = this.this$0;
            if (r0.listView.scrollingByUser == false) goto L14;
            AndroidUtilities.hideKeyboard(r0.fragmentView);
            return;
        }
    }

    /* renamed from: $r8$lambda$0Ef0Z-7zGzug8jwvT6D_zo0-aJ0, reason: not valid java name */
    public static /* synthetic */ boolean m21948$r8$lambda$0Ef0Z7zGzug8jwvT6D_zo0aJ0(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$9pLNWt4i1abWYTli34IBZry2f6c(long r1, int r3) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L6;
        r0.presentFragment(ChatActivity.of(r1, r3));
        return;
    }

    public static /* synthetic */ void $r8$lambda$kE63AZelEckBmGanR8oj4N8TS7w(BookmarksFragment r0, HashSet r1, AlertDialog r2, int r3) {
        r0.lambda$deleteSelectedMessages$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$tXYQB1a2aEJ2Mh6B8AJNC2AhaDU(BookmarksFragment r0) {
        r0.updateWithOffset();
    }

    /* renamed from: -$$Nest$fgetquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21949$$Nest$fgetquery(BookmarksFragment r0) {
        return r0.query;
    }

    /* renamed from: -$$Nest$fputquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21950$$Nest$fputquery(BookmarksFragment r0, String r1) {
        r0.query = r1;
    }

    /* renamed from: -$$Nest$mupdateWithOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21951$$Nest$mupdateWithOffset(BookmarksFragment r0) {
        r0.updateWithOffset();
    }

    public BookmarksFragment(Runnable r4, Utilities.Callback<String> r5) {
        this.list = new AddressBarList.BookmarksList(this.currentAccount, new BookmarksFragment$$ExternalSyntheticLambda1(this));
        this.selected = new HashSet();
        this.addedUrls = new HashSet();
        this.closeToTabs = r4;
        this.whenClicked = r5;
    }

    public static /* synthetic */ ActionBar access$000(BookmarksFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(BookmarksFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$200(BookmarksFragment r0) {
        return r0.currentAccount;
    }

    private /* synthetic */ void lambda$deleteSelectedMessages$0(HashSet r10, AlertDialog r11, int r12) {
        long r4 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        MessagesController.getInstance(this.currentAccount).deleteMessages(new ArrayList(r10), null, null, r4, 0, true, 0);
        this.list.delete(new ArrayList(r10));
        AddressBarList.BookmarksList r112 = this.searchList;
        if (r112 == null) goto L5;
        r112.delete(new ArrayList(r10));
    L5:
        this.selected.clear();
        this.actionBar.hideActionMode();
        this.listView.adapter.update(true);
    }

    public static boolean matches(String r5, String r6) {
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

    private void updateWithOffset() {
        int r0 = -1;
        int r2 = 0;
    L4:
        if (r2 >= this.listView.getChildCount()) goto L9;
        View r02 = this.listView.getChildAt(r2);
        int r3 = this.listView.getChildAdapterPosition(r02);
        if (r3 >= 0) goto L8;
        r2 = r2 + 1;
        r0 = r3;
        goto L4
    L8:
        int r22 = r02.getTop();
        r0 = r3;
    L10:
        this.listView.adapter.update(true);
        UniversalRecyclerView r5 = this.listView;
        if (r0 < 0) goto L14;
        r5.layoutManager.scrollToPositionWithOffset(r0, r22);
        return;
    L14:
        r5.layoutManager.scrollToPositionWithOffset(0, 0);
        return;
    L9:
        r22 = 0;
        goto L10
    }

    public void clickSelect(UItem r4, View r5) {
        AddressBarList.BookmarkView r52 = (AddressBarList.BookmarkView) r5;
        MessageObject r42 = (MessageObject) r4.object2;
        boolean r1 = false;
        if (isSelected(r42) == false) goto L5;
        setSelected(r42, false);
        r52.setChecked(false);
    L6:
        this.selectedCount.setNumber(this.selected.size(), true);
        boolean r43 = this.selected.isEmpty();
        ActionBar r53 = this.actionBar;
        if (r43 == false) goto L9;
        r53.hideActionMode();
    L10:
        ActionBarMenuItem r44 = this.gotoItem;
        if (this.selected.size() != 1) goto L13;
        r1 = true;
    L13:
        AndroidUtilities.updateViewShow(r44, r1, true, true);
        return;
    L9:
        r53.showActionMode();
        goto L10
    L5:
        setSelected(r42, true);
        r52.setChecked(true);
        goto L6
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
        this.selectedCount.setOnTouchListener(new BookmarksFragment$$ExternalSyntheticLambda0());
        r03.addView(this.selectedCount, LayoutHelper.createLinear(0, -1, 1.0f, 65, 0, 0, 0));
        this.gotoItem = r03.addItemWithWidth(R.id.menu_link, R.drawable.msg_message, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.AccDescrGoToMessage));
        r03.addItemWithWidth(R.id.menu_delete, R.drawable.msg_delete, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Delete));
        ActionBarMenuItem r04 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search, getResourceProvider()).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem = r04;
        r04.setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.searchItem.setContentDescription(LocaleController.getString(R.string.Search));
        EditTextBoldCursor r05 = this.searchItem.getSearchField();
        r05.setTextColor(getThemedColor(r12));
        r05.setHintTextColor(getThemedColor(Theme.key_player_time));
        r05.setCursorColor(getThemedColor(r12));
        this.listView.addOnScrollListener(new AnonymousClass3(this));
        StickerEmptyView r06 = new StickerEmptyView(r13, null, 1);
        r06.title.setText(LocaleController.getString(R.string.WebNoBookmarks));
        r06.subtitle.setVisibility(8);
        r06.showProgress(false, false);
        r06.setAnimateLayoutChange(true);
        ((FrameLayout) this.fragmentView).addView(r06, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setEmptyView(r06);
        return this.fragmentView;
    }

    public void deleteSelectedMessages() {
        HashSet r0 = new HashSet();
        ArrayList r1 = new ArrayList();
        HashSet r2 = new HashSet();
        Iterator<Integer> r3 = this.selected.iterator();
    L3:
        MessageObject r5 = null;
        int r6 = 0;
        if (r3.hasNext() == false) goto L24;
        int r4 = r3.next().intValue();
        ArrayList<MessageObject> r7 = this.list.links;
        int r8 = r7.size();
        int r9 = 0;
    L6:
        if (r9 >= r8) goto L12;
        MessageObject r10 = r7.get(r9);
        r9 = r9 + 1;
        MessageObject r102 = r10;
        if (r102 == null) goto L6;
        if (r102.getId() != r4) goto L6;
        r5 = r102;
    L12:
        AddressBarList.BookmarksList r72 = this.searchList;
        if (r72 == null) goto L22;
        if (r5 != null) goto L22;
        ArrayList<MessageObject> r73 = r72.links;
        int r82 = r73.size();
    L16:
        if (r6 >= r82) goto L22;
        MessageObject r92 = r73.get(r6);
        r6 = r6 + 1;
        MessageObject r93 = r92;
        if (r93 == null) goto L16;
        if (r93.getId() != r4) goto L16;
        r5 = r93;
    L22:
        if (r5 == null) goto L3;
        r1.add(r5);
        r2.add(Integer.valueOf(r5.getId()));
        r0.add(AddressBarList.getLink(r5));
        goto L3
    L24:
        AlertDialog.Builder r02 = new AlertDialog.Builder(getContext(), getResourceProvider()).setTitle(LocaleController.formatPluralString("DeleteOptionsTitle", r2.size(), new Object[0]));
        if (r2.size() != 1) goto L27;
        String r12 = "AreYouSureUnsaveSingleMessage";
    L28:
        r02.setMessage(LocaleController.getString(r12)).setPositiveButton(LocaleController.getString(R.string.Delete), new BookmarksFragment$$ExternalSyntheticLambda3(this, r2)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
        return;
    L27:
        r12 = "AreYouSureUnsaveFewMessages";
        goto L28
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void fillItems(ArrayList<UItem> r14, UniversalAdapter r15) {
        this.addedUrls.clear();
        boolean r152 = TextUtils.isEmpty(this.query);
        AddressBarList.BookmarksList r0 = this.list;
        if (r152 == false) goto L20;
        ArrayList<MessageObject> r153 = r0.links;
        int r02 = r153.size();
        int r7 = 0;
    L5:
        if (r7 >= r02) goto L18;
        MessageObject r8 = r153.get(r7);
        r7 = r7 + 1;
        MessageObject r82 = r8;
        String r9 = AddressBarList.getLink(r82);
        if (TextUtils.isEmpty(r9) == true) goto L5;
        if (r9.startsWith("#") == true) goto L5;
        if (r9.startsWith("$") == true) goto L5;
        if (r9.startsWith("@") == true) goto L5;
        this.addedUrls.add(r9);
        r14.add(AddressBarList.BookmarkView.Factory.as(r82, false).setChecked(isSelected(r82)));
        goto L5
    L18:
        if (this.list.endReached == true) goto L80;
        r14.add(UItem.asFlicker(r14.size(), 32));
        r14.add(UItem.asFlicker(r14.size(), 32));
        r14.add(UItem.asFlicker(r14.size(), 32));
    L80:
        if (r14.isEmpty() == true) goto L120;
        r14.add(UItem.asShadow(null));
        return;
    L120:
        return;
    L20:
        ArrayList<MessageObject> r154 = r0.links;
        int r03 = r154.size();
        int r72 = 0;
    L21:
        if (r72 >= r03) goto L63;
        MessageObject r83 = r154.get(r72);
        r72 = r72 + 1;
        MessageObject r84 = r83;
        String r92 = AddressBarList.getLink(r84);
        if (TextUtils.isEmpty(r92) == true) goto L21;
        if (r92.startsWith("#") == true) goto L21;
        if (r92.startsWith("$") == true) goto L21;
        if (r92.startsWith("@") == true) goto L21;
        this.addedUrls.add(r92);
        String r93 = AndroidUtilities.getHostAuthority(r92, true);
        WebMetadataCache.WebMetadata r10 = WebMetadataCache.getInstance().get(r93);
        if (r84 == null) goto L39;
        TLRPC.Message r11 = r84.messageOwner;
        if (r11 == null) goto L39;
        TLRPC.MessageMedia r112 = r11.media;
        if (r112 == null) goto L39;
        TLRPC.WebPage r113 = r112.webpage;
    L40:
        if (r113 != null) goto L42;
    L44:
        if (r10 != null) goto L46;
    L49:
        String r102 = null;
    L50:
        if (r113 != null) goto L52;
    L54:
        String r114 = null;
    L56:
        if (matches(r93, this.query) == true) goto L62;
        if (matches(r102, this.query) == true) goto L62;
        if (matches(r114, this.query) == false) goto L21;
    L62:
        r14.add(AddressBarList.BookmarkView.Factory.as(r84, false, this.query).setChecked(isSelected(r84)));
        goto L21
    L52:
        if (TextUtils.isEmpty(r113.title) == true) goto L54;
        r114 = r113.title;
        goto L56
    L46:
        if (TextUtils.isEmpty(r10.sitename) == true) goto L49;
        r102 = r10.sitename;
        goto L50
    L42:
        if (TextUtils.isEmpty(r113.site_name) == true) goto L44;
        r102 = r113.site_name;
    L39:
        r113 = null;
        goto L40
    L63:
        ArrayList<MessageObject> r155 = this.searchList.links;
        int r04 = r155.size();
        int r73 = 0;
    L64:
        if (r73 >= r04) goto L77;
        MessageObject r85 = r155.get(r73);
        r73 = r73 + 1;
        MessageObject r86 = r85;
        String r94 = AddressBarList.getLink(r86);
        if (TextUtils.isEmpty(r94) == true) goto L64;
        if (r94.startsWith("#") == true) goto L64;
        if (r94.startsWith("$") == true) goto L64;
        if (r94.startsWith("@") == true) goto L64;
        this.addedUrls.add(r94);
        r14.add(AddressBarList.BookmarkView.Factory.as(r86, false, this.query).setChecked(isSelected(r86)));
        goto L64
    L77:
        if (this.searchList.endReached == true) goto L80;
        r14.add(UItem.asFlicker(r14.size(), 32));
        r14.add(UItem.asFlicker(r14.size(), 32));
        r14.add(UItem.asFlicker(r14.size(), 32));
        goto L80
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.WebBookmarks);
    }

    public void gotoMessage() {
        if (this.selected.size() == 1) goto L5;
        return;
    L5:
        long r0 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        int r2 = this.selected.iterator().next().intValue();
        finishFragment();
        Runnable r3 = this.closeToTabs;
        if (r3 == null) goto L8;
        r3.run();
    L8:
        AndroidUtilities.runOnUIThread(new BookmarksFragment$$ExternalSyntheticLambda2(r0, r2), 80);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (AndroidUtilities.computePerceivedBrightness(getThemedColor(Theme.key_windowBackgroundWhite)) <= 0.721f) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isSelected(MessageObject r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (this.selected.contains(Integer.valueOf(r1.getId())) == false) goto L9;
        return true;
    L9:
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
        this.whenClicked.run(AddressBarList.getLink((MessageObject) r1.object2));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.list.attach();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        this.list.detach();
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (r1.instanceOf(AddressBarList.BookmarkView.Factory.class) == false) goto L6;
        clickSelect(r1, r2);
        return true;
    L6:
        return false;
    }

    public void setSelected(MessageObject r1, boolean r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        HashSet<Integer> r0 = this.selected;
        if (r2 == false) goto L8;
        r0.add(Integer.valueOf(r1.getId()));
        return;
    L8:
        r0.remove(Integer.valueOf(r1.getId()));
    }
}
