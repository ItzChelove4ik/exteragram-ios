package org.telegram.ui.Adapters;

import android.content.Context;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.rop.code.RegisterSpec;
import com.exteragram.messenger.utils.network.RemoteUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HashtagSearchCell;
import org.telegram.ui.Cells.HintDialogCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TopicSearchCell;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.ForegroundColorSpanThemable;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.FilteredSearchView;

/* loaded from: classes3.dex */
public abstract class DialogsSearchAdapter extends RecyclerListView.SelectionAdapter {
    private Runnable cancelShowMoreAnimation;
    private int currentAccount;
    private int currentItemCount;
    private Filter currentMessagesFilter;
    private String currentMessagesQuery;
    public DialogsSearchAdapterDelegate delegate;
    private final DialogsActivity dialogsActivity;
    private int dialogsType;
    private ColoredImageSpan filterArrowsIcon;
    private ArrayList<Long> filterDialogIds;
    private final ArrayList<RecentSearchObject> filtered2RecentSearchObjects;
    private String filteredRecentQuery;
    private final ArrayList<RecentSearchObject> filteredRecentSearchObjects;
    private FilteredSearchView.Delegate filtersDelegate;
    private int folderId;
    private boolean forceLoadingMessages;
    boolean globalSearchCollapsed;
    private RecyclerListView innerListView;
    private DefaultItemAnimator itemAnimator;
    private int lastForumReqId;
    private int lastGlobalSearchId;
    private int lastLocalSearchId;
    private int lastMessagesSearchFilterFlags;
    private int lastMessagesSearchId;
    private String lastMessagesSearchString;
    private int lastReqId;
    private int lastSearchId;
    private String lastSearchText;
    private long lastShowMoreUpdate;
    public int localMessagesLoadingRow;
    private boolean localMessagesSearchEndReached;
    private boolean localTipArchive;
    private ArrayList<FiltersView.DateData> localTipDates;
    private final Context mContext;
    private EmptyLayout messagesEmptyLayout;
    private boolean messagesSearchEndReached;
    private int messagesSectionPosition;
    private int needMessagesSearch;
    private int nextSearchRate;
    boolean phoneCollapsed;
    public ArrayList<MessageObject> publicPosts;
    public String publicPostsHashtag;
    public int publicPostsLastRate;
    public int publicPostsTotalCount;
    private ArrayList<RecentSearchObject> recentSearchObjects;
    private LongSparseArray<RecentSearchObject> recentSearchObjectsById;
    private int reqForumId;
    private int reqId;
    private final Theme.ResourcesProvider resourcesProvider;
    private SearchAdapterHelper searchAdapterHelper;
    private final ArrayList<ContactsController.Contact> searchContacts;
    private final ArrayList<MessageObject> searchForumResultMessages;
    private int searchHashtagRequest;
    private Runnable searchHashtagRunnable;
    private ArrayList<Object> searchResult;
    private final ArrayList<String> searchResultHashtags;
    private final ArrayList<MessageObject> searchResultMessages;
    private ArrayList<CharSequence> searchResultNames;
    private Runnable searchRunnable;
    private Runnable searchRunnable2;
    private final ArrayList<TLRPC.TL_forumTopic> searchTopics;
    private boolean searchWas;
    private final HashSet<byte[]> seenSponsoredPeers;
    private long selfUserId;
    public boolean showMoreAnimation;
    public View showMoreHeader;
    public final ArrayList<TLRPC.TL_sponsoredPeer> sponsoredPeers;
    private String sponsoredQuery;
    private int sponsoredReqId;
    int waitingResponseCount;

    /* renamed from: org.telegram.ui.Adapters.DialogsSearchAdapter$1, reason: invalid class name */
    public class AnonymousClass1 extends SearchAdapterHelper {
        final /* synthetic */ DialogsSearchAdapter this$0;

        public AnonymousClass1(DialogsSearchAdapter r1, boolean r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Adapters.SearchAdapterHelper
        public boolean filter(TLObject r1) {
            return DialogsSearchAdapter.m5897$$Nest$mfilter(this.this$0, r1);
        }
    }

    /* renamed from: org.telegram.ui.Adapters.DialogsSearchAdapter$2, reason: invalid class name */
    public class AnonymousClass2 implements SearchAdapterHelper.SearchAdapterHelperDelegate {
        final /* synthetic */ DialogsSearchAdapter this$0;

        public AnonymousClass2(DialogsSearchAdapter r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
        public boolean canApplySearchResults(int r1) {
            if (r1 != DialogsSearchAdapter.m5891$$Nest$fgetlastSearchId(this.this$0)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
        public void onDataSetChanged(int r4) {
            DialogsSearchAdapter r0 = this.this$0;
            r0.waitingResponseCount--;
            DialogsSearchAdapter.m5895$$Nest$fputlastGlobalSearchId(r0, r4);
            if (DialogsSearchAdapter.m5889$$Nest$fgetlastLocalSearchId(this.this$0) == r4) goto L6;
            DialogsSearchAdapter.m5892$$Nest$fgetsearchResult(this.this$0).clear();
        L6:
            if (DialogsSearchAdapter.m5890$$Nest$fgetlastMessagesSearchId(this.this$0) == r4) goto L8;
            DialogsSearchAdapter.m5894$$Nest$fgetsearchResultMessages(this.this$0).clear();
        L8:
            DialogsSearchAdapter.m5896$$Nest$fputsearchWas(this.this$0, true);
            DialogsSearchAdapter r42 = this.this$0;
            DialogsSearchAdapterDelegate r02 = r42.delegate;
            if (r02 != null) goto L11;
        L15:
            this.this$0.notifyDataSetChanged();
            DialogsSearchAdapterDelegate r3 = this.this$0.delegate;
            if (r3 == null) goto L19;
            r3.runResultsEnterAnimation();
            return;
        L19:
            return;
        L11:
            if (r42.waitingResponseCount <= 0) goto L13;
            boolean r43 = true;
        L14:
            r02.searchStateChanged(r43, true);
            goto L15
        L13:
            r43 = false;
            goto L14
        }

        @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
        public void onSetHashtags(ArrayList<SearchAdapterHelper.HashtagObject> r4, HashMap<String, SearchAdapterHelper.HashtagObject> r5) {
            int r0 = 0;
        L3:
            int r1 = r4.size();
            DialogsSearchAdapter r2 = this.this$0;
            if (r0 >= r1) goto L6;
            DialogsSearchAdapter.m5893$$Nest$fgetsearchResultHashtags(r2).add(r4.get(r0).hashtag);
            r0 = r0 + 1;
            goto L3
        L6:
            DialogsSearchAdapterDelegate r42 = r2.delegate;
            if (r42 != null) goto L9;
        L13:
            this.this$0.notifyDataSetChanged();
            return;
        L9:
            if (r2.waitingResponseCount <= 0) goto L11;
            boolean r02 = true;
        L12:
            r42.searchStateChanged(r02, false);
            goto L13
        L11:
            r02 = false;
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.Adapters.DialogsSearchAdapter$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends DialogCell {
        final /* synthetic */ DialogsSearchAdapter this$0;

        public AnonymousClass3(DialogsSearchAdapter r1, DialogsActivity r2, Context r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Cells.DialogCell
        public boolean isForumCell() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Adapters.DialogsSearchAdapter$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends RecyclerListView {
        final /* synthetic */ DialogsSearchAdapter this$0;

        public AnonymousClass4(DialogsSearchAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (getParent() == null) goto L14;
            if (getParent().getParent() == null) goto L14;
            ViewParent r0 = getParent().getParent();
            boolean r2 = true;
            if (canScrollHorizontally(-1) == false) goto L9;
        L12:
            r0.requestDisallowInterceptTouchEvent(r2);
            goto L14
        L9:
            if (canScrollHorizontally(1) == true) goto L12;
            r2 = false;
        L14:
            return super.onInterceptTouchEvent(r4);
        }
    }

    /* renamed from: org.telegram.ui.Adapters.DialogsSearchAdapter$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends LinearLayoutManager {
        final /* synthetic */ DialogsSearchAdapter this$0;

        public AnonymousClass5(DialogsSearchAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* loaded from: classes6.dex */
    public static class CategoryAdapterRecycler extends RecyclerListView.SelectionAdapter {
        private final int currentAccount;
        private boolean drawChecked;
        private final Context mContext;
        private Theme.ResourcesProvider resourcesProvider;
        private boolean showPremiumBlock;

        public CategoryAdapterRecycler(Context r1, int r2, boolean r3, boolean r4, Theme.ResourcesProvider r5) {
            this.drawChecked = r3;
            this.mContext = r1;
            this.currentAccount = r2;
            this.showPremiumBlock = r4;
            this.resourcesProvider = r5;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return MediaDataController.getInstance(this.currentAccount).hints.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            HintDialogCell r92 = (HintDialogCell) r9.itemView;
            TLRPC.TL_topPeer r102 = MediaDataController.getInstance(this.currentAccount).hints.get(r10);
            new TLRPC.TL_dialog();
            TLRPC.Peer r0 = r102.peer;
            long r1 = r0.user_id;
            TLRPC.User r6 = null;
            if (r1 == 0) goto L5;
            r6 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r102.peer.user_id));
            TLRPC.Chat r103 = null;
        L12:
            r92.setTag(Long.valueOf(r1));
            if (r6 == null) goto L15;
            String r8 = UserObject.getFirstName(r6);
        L21:
            r92.setDialog(r1, true, r8);
            return;
        L15:
            if (r103 != null) goto L17;
            r8 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L21
        L17:
            if (r103.monoforum == false) goto L19;
            r8 = ForumUtilities.getMonoForumTitle(this.currentAccount, r103);
            goto L21
        L19:
            r8 = r103.title;
            goto L21
        L5:
            long r12 = r0.channel_id;
            if (r12 == 0) goto L8;
            r1 = -r12;
            r103 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r102.peer.channel_id));
            goto L12
        L8:
            long r02 = r0.chat_id;
            if (r02 == 0) goto L11;
            r1 = -r02;
            r103 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r102.peer.chat_id));
            goto L12
        L11:
            r1 = 0;
            r103 = null;
            goto L12
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            HintDialogCell r32 = new HintDialogCell(this.mContext, this.drawChecked, this.resourcesProvider);
            if (this.showPremiumBlock == false) goto L5;
            r32.showPremiumBlocked();
        L5:
            r32.setLayoutParams(new RecyclerView.LayoutParams(AndroidUtilities.dp(80.0f), AndroidUtilities.dp(86.0f)));
            return new RecyclerListView.Holder(r32);
        }

        public void setIndex(int r1) {
            notifyDataSetChanged();
        }
    }

    /* loaded from: classes6.dex */
    public static class DialogSearchResult {
        public int date;
        public CharSequence name;
        public TLObject object;

        public DialogSearchResult() {
        }
    }

    public interface DialogsSearchAdapterDelegate {
        void didPressedBlockedDialog(View r1, long r2);

        void didPressedOnSubDialog(long r1);

        long getSearchForumDialogId();

        boolean isSelected(long r1);

        void needClearList();

        void needRemoveHint(long r1);

        void runResultsEnterAnimation();

        void searchStateChanged(boolean r1, boolean r2);
    }

    /* loaded from: classes6.dex */
    public static class EmptyLayout extends LinearLayout {
        private TextView textView;

        public static /* synthetic */ void $r8$lambda$khXgd90JCalItjECRACAFqJGDZY(Runnable r0, View r1) {
            r0.run();
        }

        public EmptyLayout(Context r17, Theme.ResourcesProvider r18, Runnable r19) {
            super(r17);
            setOrientation(1);
            BackupImageView r4 = new BackupImageView(r17);
            r4.setImageDrawable(new RLottieDrawable(R.raw.utyan_empty, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f)));
            addView(r4, LayoutHelper.createLinear(120, 120, 1, 0, 27, 0, 0));
            TextView r42 = new TextView(r17);
            r42.setTextSize(1, 17.0f);
            int r5 = Theme.key_windowBackgroundWhiteBlackText;
            r42.setTextColor(Theme.getColor(r5, r18));
            r42.setTypeface(AndroidUtilities.bold());
            r42.setText(LocaleController.getString(R.string.SearchMessagesFilterEmptyTitle));
            r42.setGravity(17);
            addView(r42, LayoutHelper.createLinear(-1, -2, 1, 0, 8, 0, 9));
            TextView r43 = new TextView(r17);
            this.textView = r43;
            r43.setTextSize(1, 14.0f);
            this.textView.setTextColor(Theme.getColor(r5, r18));
            this.textView.setText(LocaleController.formatString(R.string.SearchMessagesFilterEmptyText, new Object[]{_UrlKt.FRAGMENT_ENCODE_SET}));
            this.textView.setGravity(17);
            addView(this.textView, LayoutHelper.createLinear(-1, -2, 1, 0, 0, 0, 14));
            TextView r44 = new TextView(r17);
            r44.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f));
            r44.setTextSize(1, 14.0f);
            r44.setText(LocaleController.getString(R.string.SearchMessagesFilterEmptySearchAll));
            int r1 = Theme.key_featuredStickers_addButton;
            r44.setTextColor(Theme.getColor(r1, r18));
            r44.setBackground(Theme.createSimpleSelectorRoundRectDrawable(6, 0, Theme.multAlpha(Theme.getColor(r1, r18), 0.15f)));
            r44.setOnClickListener(new DialogsSearchAdapter$EmptyLayout$$ExternalSyntheticLambda0(r19));
            addView(r44, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 38));
        }

        public void setQuery(String r2) {
            this.textView.setText(LocaleController.formatString(R.string.SearchMessagesFilterEmptyText, new Object[]{r2}));
        }
    }

    public enum Filter extends Enum<Filter> {
        private static final /* synthetic */ Filter[] $VALUES = null;
        public static final Filter All = null;
        public static final Filter Channels = null;
        public static final Filter Groups = null;
        public static final Filter Private = null;
        public final int flags;
        public final int strFromResId;
        public final int strResId;

        private static /* synthetic */ Filter[] $values() {
            return new Filter[]{All, Private, Groups, Channels};
        }

        static {
            All = new Filter("All", 0, 0, R.string.SearchMessagesFilterAll, R.string.SearchMessagesFilterAllFrom);
            Private = new Filter("Private", 1, 8, R.string.SearchMessagesFilterPrivate, R.string.SearchMessagesFilterPrivateFrom);
            Groups = new Filter("Groups", 2, 4, R.string.SearchMessagesFilterGroup, R.string.SearchMessagesFilterGroupFrom);
            Channels = new Filter("Channels", 3, 2, R.string.SearchMessagesFilterChannels, R.string.SearchMessagesFilterChannelsFrom);
            $VALUES = $values();
        }

        Filter(String r1, int r2, int r3, int r4, int r5) {
            this.flags = r3;
            this.strResId = r4;
            this.strFromResId = r5;
        }

        public static Filter valueOf(String r1) {
            return (Filter) Enum.valueOf(Filter.class, r1);
        }

        public static Filter[] values() {
            return (Filter[]) $VALUES.clone();
        }
    }

    public interface OnRecentSearchLoaded {
        void setRecentSearch(ArrayList<RecentSearchObject> r1, LongSparseArray<RecentSearchObject> r2);
    }

    /* loaded from: classes6.dex */
    public static class RecentSearchObject {
        public int date;
        public long did;
        public TLObject object;

        public RecentSearchObject() {
        }
    }

    /* renamed from: $r8$lambda$-OJxNF02VTMyuvDMEeWTYTm8-rc, reason: not valid java name */
    public static /* synthetic */ void m5872$r8$lambda$OJxNF02VTMyuvDMEeWTYTm8rc(DialogsSearchAdapter r0, View r1) {
        r0.lambda$onBindViewHolder$26(r1);
    }

    /* renamed from: $r8$lambda$-qjOaRQT58wXpIiA5hKyNVz5llQ, reason: not valid java name */
    public static /* synthetic */ void m5873$r8$lambda$qjOaRQT58wXpIiA5hKyNVz5llQ(DialogsSearchAdapter r0) {
        r0.lambda$onCreateViewHolder$25();
    }

    /* renamed from: $r8$lambda$0XhPRJ8CHLt6pJa025FzOeaZ-KE, reason: not valid java name */
    public static /* synthetic */ void m5874$r8$lambda$0XhPRJ8CHLt6pJa025FzOeaZKE(DialogsSearchAdapter r0, long r1) {
        r0.lambda$removeRecentSearch$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$17fPHjilSOQgfQI4fJMUOtwG40U(DialogsSearchAdapter r0, View r1) {
        r0.lambda$onBindViewHolder$32(r1);
    }

    public static /* synthetic */ void $r8$lambda$1aO1DmUB5brGXlP501dGOZBLuPw(DialogsSearchAdapter r0, long r1) {
        r0.lambda$putRecentSearch$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$41YgsHzzR4GBdwQ0tpdDm3n5qyE(Runnable r0, View r1) {
        r0.run();
    }

    /* renamed from: $r8$lambda$A-IbWbhtdToA_YLX0T3XPSDYiGY, reason: not valid java name */
    public static /* synthetic */ void m5875$r8$lambda$AIbWbhtdToA_YLX0T3XPSDYiGY(DialogsSearchAdapter r0, ArrayList r1, int r2, GraySectionCell r3) {
        r0.lambda$onBindViewHolder$33(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$BE-jvHs7TAqcFMsyxJv98EGR3_o, reason: not valid java name */
    public static /* synthetic */ void m5876$r8$lambda$BEjvHs7TAqcFMsyxJv98EGR3_o(DialogsSearchAdapter r0, int r1, String r2) {
        r0.lambda$searchDialogs$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ekg2IRpgTJznZ85nqTS5_kLVwYo(DialogsSearchAdapter r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$searchDialogs$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FwWKVZ1k4VhO0OHA0GcmFfbMYIA(DialogsSearchAdapter r0, int r1, ArrayList r2, ArrayList r3, ArrayList r4) {
        r0.lambda$updateSearchResults$15(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$HSwg9cHyD-5saYBn5wHtSFEkG1g, reason: not valid java name */
    public static /* synthetic */ void m5877$r8$lambda$HSwg9cHyD5saYBn5wHtSFEkG1g(DialogsSearchAdapter r0, int r1, int r2, TLRPC.TL_error r3, String r4, TLObject r5, TLRPC.TL_messages_searchGlobal r6, ArrayList r7) {
        r0.lambda$searchMessagesInternal$2(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$HZ_-Omniy_ElXzNB6Et7U-PxOFI, reason: not valid java name */
    public static /* synthetic */ void m5878$r8$lambda$HZ_Omniy_ElXzNB6Et7UPxOFI(DialogsSearchAdapter r0, View r1) {
        r0.lambda$onBindViewHolder$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$IeZY6DzfyevAnT9a9okTeQwCnus(int r12, int r13, OnRecentSearchLoaded r14) {
        int r2 = 0;
        SQLiteCursor r0 = MessagesStorage.getInstance(r12).getDatabase().queryFinalized("SELECT did, date FROM search_recent WHERE 1", new Object[0]);     // Catch: Exception -> L59
        ArrayList<Long> r1 = new ArrayList();     // Catch: Exception -> L59
        ArrayList r3 = new ArrayList();     // Catch: Exception -> L59
        ArrayList r4 = new ArrayList();     // Catch: Exception -> L59
        new ArrayList();     // Catch: Exception -> L59
        ArrayList r5 = new ArrayList();     // Catch: Exception -> L59
        LongSparseArray r6 = new LongSparseArray();     // Catch: Exception -> L59
    L4:
        if (r0.next() == false) goto L24;
        long r7 = r0.longValue(0);     // Catch: Exception -> L59
        if (DialogObject.isEncryptedDialog(r7) == true) goto L7;
        if (DialogObject.isUserDialog(r7) == true) goto L16;
        long r9 = -r7;
        if (r3.contains(Long.valueOf(r9)) == true) goto L4;
        r3.add(Long.valueOf(r9));     // Catch: Exception -> L59
    L23:
        RecentSearchObject r92 = new RecentSearchObject();     // Catch: Exception -> L59
        r92.did = r7;     // Catch: Exception -> L59
        r92.date = r0.intValue(1);     // Catch: Exception -> L59
        r5.add(r92);     // Catch: Exception -> L59
        r6.put(r92.did, r92);     // Catch: Exception -> L59
        goto L4
    L16:
        if (r13 == 2) goto L4;
        if (r1.contains(Long.valueOf(r7)) == true) goto L4;
        r1.add(Long.valueOf(r7));     // Catch: Exception -> L59
        goto L23
    L7:
        if (r13 == 0) goto L10;
        if (r13 != 3) goto L4;
    L10:
        int r93 = DialogObject.getEncryptedChatId(r7);     // Catch: Exception -> L59
        if (r4.contains(Integer.valueOf(r93)) == true) goto L4;
        r4.add(Integer.valueOf(r93));     // Catch: Exception -> L59
        goto L23
    L24:
        r0.dispose();     // Catch: Exception -> L59
        ArrayList<TLRPC.User> r132 = new ArrayList();     // Catch: Exception -> L59
        if (r4.isEmpty() == true) goto L35;
        ArrayList<TLRPC.EncryptedChat> r02 = new ArrayList();     // Catch: Exception -> L59
        MessagesStorage.getInstance(r12).getEncryptedChatsInternal(TextUtils.join(",", r4), r02, r1);     // Catch: Exception -> L59
        int r42 = 0;
    L29:
        if (r42 >= r02.size()) goto L35;
        RecentSearchObject r8 = (RecentSearchObject) r6.get(DialogObject.makeEncryptedDialogId(r02.get(r42).id));     // Catch: Exception -> L59
        if (r8 == null) goto L33;
        r8.object = r02.get(r42);     // Catch: Exception -> L59
    L33:
        r42 = r42 + 1;     // Catch: Exception -> L59
    L35:
        if (r3.isEmpty() == true) goto L49;
        ArrayList<TLRPC.Chat> r03 = new ArrayList();     // Catch: Exception -> L59
        MessagesStorage.getInstance(r12).getChatsInternal(TextUtils.join(",", r3), r03);     // Catch: Exception -> L59
        int r32 = 0;
    L38:
        if (r32 >= r03.size()) goto L49;
        TLRPC.Chat r43 = r03.get(r32);     // Catch: Exception -> L59
        long r72 = -r43.id;
        if (r43.migrated_to == null) goto L44;
        RecentSearchObject r44 = (RecentSearchObject) r6.get(r72);     // Catch: Exception -> L59
        r6.remove(r72);     // Catch: Exception -> L59
        if (r44 == null) goto L47;
        r5.remove(r44);     // Catch: Exception -> L59
    L47:
        r32 = r32 + 1;     // Catch: Exception -> L59
        goto L38
    L44:
        RecentSearchObject r73 = (RecentSearchObject) r6.get(r72);     // Catch: Exception -> L59
        if (r73 == null) goto L47;
        r73.object = r43;     // Catch: Exception -> L59
    L49:
        if (r1.isEmpty() == true) goto L57;
        MessagesStorage.getInstance(r12).getUsersInternal(r1, r132);     // Catch: Exception -> L59
    L52:
        if (r2 >= r132.size()) goto L57;
        TLRPC.User r122 = r132.get(r2);     // Catch: Exception -> L59
        RecentSearchObject r04 = (RecentSearchObject) r6.get(r122.id);     // Catch: Exception -> L59
        if (r04 == null) goto L56;
        r04.object = r122;     // Catch: Exception -> L59
    L56:
        r2 = r2 + 1;     // Catch: Exception -> L59
    L57:
        Collections.sort(r5, new DialogsSearchAdapter$$ExternalSyntheticLambda1());     // Catch: Exception -> L59
        AndroidUtilities.runOnUIThread(new DialogsSearchAdapter$$ExternalSyntheticLambda2(r14, r5, r6));     // Catch: Exception -> L59
        return;
    L59:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$K89pScLPxz8A7hNs50DvhqLPPtU(DialogsSearchAdapter r0, int r1, int r2, TLRPC.TL_error r3, String r4, TLObject r5, TLRPC.TL_messages_search r6, ArrayList r7) {
        r0.lambda$searchForumMessagesInternal$0(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ boolean $r8$lambda$KnCqQ0uS188xhr3Qp9S9ow8Tgu4(DialogsSearchAdapter r0, View r1, int r2) {
        return r0.lambda$onCreateViewHolder$24(r1, r2);
    }

    /* renamed from: $r8$lambda$MMVEA3pdRdpRS29ubI-he5fbsAw, reason: not valid java name */
    public static /* synthetic */ void m5879$r8$lambda$MMVEA3pdRdpRS29ubIhe5fbsAw(DialogsSearchAdapter r0) {
        r0.lambda$searchDialogsInternal$12();
    }

    public static /* synthetic */ void $r8$lambda$MslFLJ1yz2I2vOYDeWamGyaNrBA(DialogsSearchAdapter r0, GraySectionCell r1) {
        r0.lambda$onBindViewHolder$30(r1);
    }

    /* renamed from: $r8$lambda$NoOY0Cz1simcTuM9zv8W7KL-mqI, reason: not valid java name */
    public static /* synthetic */ void m5880$r8$lambda$NoOY0Cz1simcTuM9zv8W7KLmqI(DialogsSearchAdapter r0, int r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$searchDialogs$21(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$OXdbibPpjHv38LbdzONvNZzV2mE(DialogsSearchAdapter r0, String r1, int r2, String r3) {
        r0.lambda$searchDialogs$19(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$PYMdipjlU_FWtu1BQnI8nRAhmYM(DialogsSearchAdapter r0, View r1) {
        r0.lambda$onBindViewHolder$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$UkxaQDDMnNIWnZAqNldsPFsIMWw(DialogsSearchAdapter r0, GraySectionCell r1) {
        r0.lambda$onBindViewHolder$35(r1);
    }

    /* renamed from: $r8$lambda$V7C12tjg39Od-cOTbLKUrPlLU78, reason: not valid java name */
    public static /* synthetic */ void m5881$r8$lambda$V7C12tjg39OdcOTbLKUrPlLU78(DialogsSearchAdapter r0, boolean r1, GraySectionCell r2, Filter r3) {
        r0.lambda$onBindViewHolder$34(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$VZMV9FdQaJMqRxeN-_J72fQS67w, reason: not valid java name */
    public static /* synthetic */ void m5882$r8$lambda$VZMV9FdQaJMqRxeN_J72fQS67w(OnRecentSearchLoaded r0, ArrayList r1, LongSparseArray r2) {
        r0.setRecentSearch(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Xj7vBTEabwT2n0OyP9WXSad6l4E(DialogsSearchAdapter r0, String r1, int r2, String r3) {
        r0.lambda$searchDialogsInternal$13(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$YGVf6CnEaWPsv9VpNlZIqnafj-I, reason: not valid java name */
    public static /* synthetic */ void m5883$r8$lambda$YGVf6CnEaWPsv9VpNlZIqnafjI(DialogsSearchAdapter r0, View r1, int r2) {
        r0.lambda$onCreateViewHolder$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bLtq8pS1k8Bu5WHu4RNqb1k6Kew(DialogsSearchAdapter r0, String r1, int r2, int r3, TLRPC.TL_messages_search r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$searchForumMessagesInternal$1(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$d1XDIJGJK5xvj9OFhYceFemgLSw(DialogsSearchAdapter r0, int r1, String r2, String r3) {
        r0.lambda$searchDialogs$18(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$e3DIFwDu9iAIWt0QfY_jhshUUGM(Runnable r0, View r1) {
        r0.run();
    }

    /* renamed from: $r8$lambda$eKfN-FBTn2ShAJEipoNdclTDi_E, reason: not valid java name */
    public static /* synthetic */ void m5884$r8$lambda$eKfNFBTn2ShAJEipoNdclTDi_E(DialogsSearchAdapter r0, int r1) {
        r0.lambda$onBindViewHolder$31(r1);
    }

    public static /* synthetic */ void $r8$lambda$hY8kbpgahiObI09CVX97PHu5IAw(DialogsSearchAdapter r0, long r1, Object r3, int r4) {
        r0.lambda$updateSearchResults$14(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$l8J4WU8F8p8kPmiHDmzZaKVeIlw(DialogsSearchAdapter r0, StringBuilder r1) {
        r0.lambda$clearRecentSearch$10(r1);
    }

    public static /* synthetic */ int $r8$lambda$lqxExGyE7S05spp6PXDQg3CKK0w(RecentSearchObject r0, RecentSearchObject r1) {
        int r02 = r0.date;
        int r12 = r1.date;
        if (r02 >= r12) goto L6;
        return 1;
    L6:
        if (r02 <= r12) goto L9;
        return -1;
    L9:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$n7NE5J4Erxr8k2engKMmCh3DJf4(DialogsSearchAdapter r0, ArrayList r1, LongSparseArray r2) {
        r0.lambda$loadRecentSearch$5(r1, r2);
    }

    /* renamed from: $r8$lambda$nDW-UCkAdXWEuCrcm1z74Mi6sCA, reason: not valid java name */
    public static /* synthetic */ void m5885$r8$lambda$nDWUCkAdXWEuCrcm1z74Mi6sCA(DialogsSearchAdapter r0, String r1, int r2, int r3, TLRPC.TL_messages_searchGlobal r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$searchMessagesInternal$4(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$nhWkMC1AJv0SKsfkmJS5nL3No2g(DialogsSearchAdapter r0, View r1) {
        r0.lambda$onBindViewHolder$27(r1);
    }

    /* renamed from: $r8$lambda$rZKhJZEx_0o5x6XuhFqw3xm-xvs, reason: not valid java name */
    public static /* synthetic */ void m5886$r8$lambda$rZKhJZEx_0o5x6XuhFqw3xmxvs(DialogsSearchAdapter r0, TLObject r1) {
        r0.lambda$searchDialogs$16(r1);
    }

    /* renamed from: $r8$lambda$swmKEqO1gm99W-VIQZYrj7uhC0s, reason: not valid java name */
    public static /* synthetic */ void m5887$r8$lambda$swmKEqO1gm99WVIQZYrj7uhC0s(DialogsSearchAdapter r0, HashSet r1, Runnable r2) {
        r0.lambda$searchMessagesInternal$3(r1, r2);
    }

    /* renamed from: $r8$lambda$ykdR59yznd-Tf8IJgpl6PrZQBP0, reason: not valid java name */
    public static /* synthetic */ void m5888$r8$lambda$ykdR59yzndTf8IJgpl6PrZQBP0(DialogsSearchAdapter r0, int r1, TLObject r2, String r3) {
        r0.lambda$searchDialogs$20(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetlastLocalSearchId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5889$$Nest$fgetlastLocalSearchId(DialogsSearchAdapter r0) {
        return r0.lastLocalSearchId;
    }

    /* renamed from: -$$Nest$fgetlastMessagesSearchId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5890$$Nest$fgetlastMessagesSearchId(DialogsSearchAdapter r0) {
        return r0.lastMessagesSearchId;
    }

    /* renamed from: -$$Nest$fgetlastSearchId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5891$$Nest$fgetlastSearchId(DialogsSearchAdapter r0) {
        return r0.lastSearchId;
    }

    /* renamed from: -$$Nest$fgetsearchResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5892$$Nest$fgetsearchResult(DialogsSearchAdapter r0) {
        return r0.searchResult;
    }

    /* renamed from: -$$Nest$fgetsearchResultHashtags, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5893$$Nest$fgetsearchResultHashtags(DialogsSearchAdapter r0) {
        return r0.searchResultHashtags;
    }

    /* renamed from: -$$Nest$fgetsearchResultMessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5894$$Nest$fgetsearchResultMessages(DialogsSearchAdapter r0) {
        return r0.searchResultMessages;
    }

    /* renamed from: -$$Nest$fputlastGlobalSearchId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5895$$Nest$fputlastGlobalSearchId(DialogsSearchAdapter r0, int r1) {
        r0.lastGlobalSearchId = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5896$$Nest$fputsearchWas(DialogsSearchAdapter r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$mfilter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5897$$Nest$mfilter(DialogsSearchAdapter r0, Object r1) {
        return r0.filter(r1);
    }

    public DialogsSearchAdapter(Context r4, DialogsActivity r5, int r6, int r7, DefaultItemAnimator r8, boolean r9, Theme.ResourcesProvider r10) {
        this.currentMessagesFilter = Filter.All;
        this.searchHashtagRequest = -1;
        this.searchResult = new ArrayList();
        this.publicPosts = new ArrayList();
        this.searchContacts = new ArrayList();
        this.searchTopics = new ArrayList();
        this.searchResultNames = new ArrayList();
        this.searchForumResultMessages = new ArrayList();
        this.searchResultMessages = new ArrayList();
        this.searchResultHashtags = new ArrayList();
        this.sponsoredPeers = new ArrayList();
        this.seenSponsoredPeers = new HashSet();
        this.reqId = 0;
        this.reqForumId = 0;
        this.localMessagesLoadingRow = -1;
        this.showMoreAnimation = false;
        this.currentAccount = UserConfig.selectedAccount;
        this.recentSearchObjects = new ArrayList();
        this.filteredRecentSearchObjects = new ArrayList();
        this.filtered2RecentSearchObjects = new ArrayList();
        this.filteredRecentQuery = null;
        this.recentSearchObjectsById = new LongSparseArray();
        this.localTipDates = new ArrayList();
        this.messagesSectionPosition = -1;
        this.globalSearchCollapsed = true;
        this.phoneCollapsed = true;
        this.itemAnimator = r8;
        this.dialogsActivity = r5;
        this.resourcesProvider = r10;
        AnonymousClass1 r52 = new AnonymousClass1(this, false);
        this.searchAdapterHelper = r52;
        r52.setDelegate(new AnonymousClass2(this));
        this.searchAdapterHelper.setAllowGlobalResults(r9);
        this.mContext = r4;
        this.needMessagesSearch = r6;
        this.dialogsType = r7;
        this.selfUserId = UserConfig.getInstance(this.currentAccount).getClientUserId();
        loadRecentSearch();
        MediaDataController.getInstance(this.currentAccount).loadHints(true);
    }

    private boolean filter(Object r4) {
        if (this.dialogsType == 14) goto L6;
        return true;
    L6:
        if ((r4 instanceof TLRPC.User) == false) goto L14;
        boolean r42 = ((TLRPC.User) r4).bot;
        DialogsActivity r3 = this.dialogsActivity;
        if (r42 == false) goto L12;
        return r3.allowBots;
    L12:
        return r3.allowUsers;
    L14:
        if ((r4 instanceof TLRPC.Chat) == false) goto L35;
        TLRPC.Chat r43 = (TLRPC.Chat) r4;
        if (ChatObject.isChannel(r43) == true) goto L18;
        boolean r44 = ChatObject.isMegagroup(r43);
        DialogsActivity r32 = this.dialogsActivity;
        if (r44 == false) goto L29;
        if (r32.allowGroups == false) goto L24;
    L27:
        return true;
    L24:
        if (r32.allowMegagroups == true) goto L27;
        return false;
    L29:
        if (r32.allowGroups == false) goto L31;
    L34:
        return true;
    L31:
        if (r32.allowLegacyGroups == true) goto L34;
        return false;
    L18:
        return this.dialogsActivity.allowChannels;
    L35:
        return false;
    }

    private CharSequence getFilterFromString(Filter r4) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(LocaleController.getString(r4.strFromResId));
        r0.append(RegisterSpec.PREFIX);
        ColoredImageSpan r42 = new ColoredImageSpan(R.drawable.arrows_select);
        this.filterArrowsIcon = r42;
        r0.setSpan(r42, r0.length() - 1, r0.length(), 33);
        return r0;
    }

    private int globalSearchPosition() {
        int r2 = 0;
        if (this.waitingResponseCount != 3) goto L6;
        return 0;
    L6:
        if (this.publicPosts.isEmpty() == true) goto L9;
        r2 = this.publicPosts.size() + 1;
    L9:
        if (this.searchResultHashtags.isEmpty() == true) goto L13;
        return r2 + (this.searchResultHashtags.size() + 1);
    L13:
        if (isRecentSearchDisplayed() == false) goto L18;
        r2 = r2 + getRecentItemsCount();
        if (this.searchWas == true) goto L18;
        return r2;
    L18:
        if (this.searchTopics.isEmpty() == true) goto L21;
        r2 = (r2 + 1) + this.searchTopics.size();
    L21:
        if (this.searchContacts.isEmpty() == true) goto L23;
        r2 = r2 + (this.searchContacts.size() + 1);
    L23:
        int r0 = this.searchResult.size();
        int r1 = this.searchAdapterHelper.getLocalServerSearch().size();
        int r22 = (r2 + r0) + r1;
        if ((r0 + r1) > 0) goto L26;
        return r22;
    L26:
        if (getRecentItemsCount() > 0) goto L32;
        if (this.searchTopics.isEmpty() == false) goto L32;
        if (this.publicPosts.isEmpty() == false) goto L32;
        return r22;
    L32:
        return r22 + 1;
    }

    private boolean hasHints() {
        if (this.searchWas == false) goto L5;
        return false;
    L5:
        if (MediaDataController.getInstance(this.currentAccount).hints.isEmpty() == false) goto L7;
        return false;
    L7:
        if (this.dialogsType == 14) goto L9;
        return true;
    L9:
        if (this.dialogsActivity.allowUsers == false) goto L16;
        return true;
    L16:
        return false;
    }

    private /* synthetic */ void lambda$clearRecentSearch$10(StringBuilder r3) {
        r3.insert(0, "DELETE FROM search_recent WHERE ");     // Catch: Exception -> L4
        MessagesStorage.getInstance(this.currentAccount).getDatabase().executeFast(r3.toString()).stepThis().dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$loadRecentSearch$5(ArrayList r1, LongSparseArray r2) {
        setRecentSearch(r1, r2);
    }

    private /* synthetic */ void lambda$onBindViewHolder$26(View r1) {
        DialogsSearchAdapterDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.needClearList();
        return;
    }

    private /* synthetic */ void lambda$onBindViewHolder$27(View r1) {
        openPublicPosts();
    }

    private /* synthetic */ void lambda$onBindViewHolder$28(View r1) {
        DialogsSearchAdapterDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.needClearList();
        return;
    }

    private /* synthetic */ void lambda$onBindViewHolder$29(View r1) {
        DialogsSearchAdapterDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.needClearList();
        return;
    }

    private /* synthetic */ void lambda$onBindViewHolder$30(GraySectionCell r3) {
        boolean r0 = this.phoneCollapsed;
        this.phoneCollapsed = !r0;
        if (r0 == true) goto L6;
        int r02 = R.string.ShowMore;
    L7:
        r3.setRightText(LocaleController.getString(r02));
        notifyDataSetChanged();
        return;
    L6:
        r02 = R.string.ShowLess;
        goto L7
    }

    private /* synthetic */ void lambda$onBindViewHolder$31(int r1) {
        notifyItemChanged(r1);
    }

    private /* synthetic */ void lambda$onBindViewHolder$32(View r2) {
        this.showMoreAnimation = false;
        this.showMoreHeader = null;
        if (r2 == null) goto L6;
        r2.invalidate();
        return;
    }

    private /* synthetic */ void lambda$onBindViewHolder$33(ArrayList r12, int r13, GraySectionCell r14) {
        long r0 = SystemClock.elapsedRealtime();
        if ((r0 - this.lastShowMoreUpdate) >= 300) goto L5;
        return;
    L5:
        this.lastShowMoreUpdate = r0;
        int r02 = this.sponsoredPeers.size();
        int r1 = r12.size();
        if (r12.isEmpty() == true) goto L8;
    L10:
        int r122 = r1 + r02;
    L11:
        int r03 = r02 + Math.min(3, r1);
        int r4 = getItemCount();
        if (this.globalSearchCollapsed == false) goto L14;
        int r5 = r03;
    L16:
        if (r4 <= ((r5 + r13) + 1)) goto L18;
        boolean r42 = true;
    L19:
        DefaultItemAnimator r52 = this.itemAnimator;
        if (r52 == null) goto L33;
        long r7 = 200;
        if (r42 == false) goto L24;
        long r9 = 45;
    L25:
        r52.setAddDuration(r9);
        DefaultItemAnimator r53 = this.itemAnimator;
        if (r42 == false) goto L28;
        r7 = 80;
    L28:
        r53.setRemoveDuration(r7);
        DefaultItemAnimator r54 = this.itemAnimator;
        if (r42 == false) goto L31;
        long r72 = 270;
    L32:
        r54.setRemoveDelay(r72);
        goto L33
    L31:
        r72 = 0;
        goto L32
    L24:
        r9 = 200;
    L33:
        this.globalSearchCollapsed = !this.globalSearchCollapsed;
        r14.setRightTextMargin(16);
        if (this.globalSearchCollapsed == false) goto L37;
        int r55 = R.string.ShowMore;
    L38:
        r14.setRightText(LocaleController.getString(r55), this.globalSearchCollapsed);
        this.showMoreHeader = null;
        View r142 = (View) r14.getParent();
        if ((r142 instanceof RecyclerView) == false) goto L52;
        RecyclerView r56 = (RecyclerView) r142;
        if (this.globalSearchCollapsed == true) goto L44;
        int r123 = r13 + r03;
    L43:
        int r124 = r123 + 1;
        int r73 = 0;
    L47:
        if (r73 >= r56.getChildCount()) goto L52;
        View r8 = r56.getChildAt(r73);
        if (r56.getChildAdapterPosition(r8) == r124) goto L50;
        r73 = r73 + 1;
        goto L47
    L50:
        this.showMoreHeader = r8;
        goto L52
    L44:
        r123 = r122 + r13;
    L52:
        int r132 = r13 + r03;
        int r125 = r132 + 1;
        int r04 = Math.max(0, r1 - 3);
        if (this.globalSearchCollapsed == true) goto L55;
        notifyItemChanged(r132);
        notifyItemRangeInserted(r125, r04);
    L59:
        Runnable r126 = this.cancelShowMoreAnimation;
        if (r126 == null) goto L62;
        AndroidUtilities.cancelRunOnUIThread(r126);
    L62:
        if (r42 == false) goto L65;
        this.showMoreAnimation = true;
        DialogsSearchAdapter$$ExternalSyntheticLambda26 r127 = new DialogsSearchAdapter$$ExternalSyntheticLambda26(this, r142);
        this.cancelShowMoreAnimation = r127;
        AndroidUtilities.runOnUIThread(r127, 400);
        return;
    L65:
        this.showMoreAnimation = false;
        return;
    L55:
        notifyItemRangeRemoved(r125, r04);
        if (r42 == false) goto L58;
        AndroidUtilities.runOnUIThread(new DialogsSearchAdapter$$ExternalSyntheticLambda25(this, r132), 350);
        goto L59
    L58:
        notifyItemChanged(r132);
        goto L59
    L37:
        r55 = R.string.ShowLess;
        goto L38
    L18:
        r42 = false;
        goto L19
    L14:
        r5 = r122;
        goto L16
    L8:
        if (this.sponsoredPeers.isEmpty() == false) goto L10;
        r122 = 0;
        goto L11
    }

    private /* synthetic */ void lambda$onBindViewHolder$34(boolean r1, GraySectionCell r2, Filter r3) {
        if (r1 == false) goto L4;
        return;
    L4:
        this.currentMessagesFilter = r3;
        r2.setRightText(getFilterFromString(r3));
        r2.setRightTextMargin(6);
        this.searchResultMessages.clear();
        this.forceLoadingMessages = true;
        notifyDataSetChanged();
        loadMoreSearchMessages();
    }

    private /* synthetic */ void lambda$onBindViewHolder$35(GraySectionCell r10) {
        ItemOptions r0 = ItemOptions.makeOptions(this.dialogsActivity, r10);
        Filter[] r1 = Filter.values();
        int r2 = r1.length;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L9;
        Filter r5 = r1[r4];
        if (r5.flags != this.currentMessagesFilter.flags) goto L7;
        boolean r6 = true;
    L8:
        r0.addChecked(r6, LocaleController.getString(r5.strResId), new DialogsSearchAdapter$$ExternalSyntheticLambda23(this, r6, r10, r5));
        r4 = r4 + 1;
        goto L3
    L7:
        r6 = false;
        goto L8
    L9:
        r0.setGravity(5).setOnTopOfScrim().setDrawScrim(false).setDimAlpha(0).show();
    }

    private /* synthetic */ void lambda$onCreateViewHolder$23(View r3, int r4) {
        if ((r3 instanceof HintDialogCell) == false) goto L10;
        HintDialogCell r42 = (HintDialogCell) r3;
        if (r42.isBlocked() == false) goto L10;
        DialogsSearchAdapterDelegate r2 = this.delegate;
        if (r2 == null) goto L14;
        r2.didPressedBlockedDialog(r3, r42.getDialogId());
        return;
    L14:
        return;
    L10:
        DialogsSearchAdapterDelegate r22 = this.delegate;
        if (r22 == null) goto L15;
        r22.didPressedOnSubDialog(((Long) r3.getTag()).longValue());
        return;
    }

    private /* synthetic */ boolean lambda$onCreateViewHolder$24(View r1, int r2) {
        DialogsSearchAdapterDelegate r0 = this.delegate;
        if (r0 == null) goto L7;
        r0.needRemoveHint(((Long) r1.getTag()).longValue());
        return true;
    L7:
        return true;
    }

    private /* synthetic */ void lambda$onCreateViewHolder$25() {
        this.currentMessagesFilter = Filter.All;
        this.searchResultMessages.clear();
        int r0 = this.messagesSectionPosition;
        if (r0 >= 0) goto L5;
    L7:
        loadMoreSearchMessages();
        return;
    L5:
        if (r0 >= getItemCount()) goto L7;
        notifyItemChanged(this.messagesSectionPosition);
        goto L7
    }

    private /* synthetic */ void lambda$putRecentSearch$9(long r3) {
        SQLitePreparedStatement r2 = MessagesStorage.getInstance(this.currentAccount).getDatabase().executeFast("REPLACE INTO search_recent VALUES(?, ?)");     // Catch: Exception -> L4
        r2.requery();     // Catch: Exception -> L4
        r2.bindLong(1, r3);     // Catch: Exception -> L4
        r2.bindInteger(2, (int) (System.currentTimeMillis() / 1000));     // Catch: Exception -> L4
        r2.step();     // Catch: Exception -> L4
        r2.dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$removeRecentSearch$11(long r3) {
        MessagesStorage.getInstance(this.currentAccount).getDatabase().executeFast("DELETE FROM search_recent WHERE did = " + r3).stepThis().dispose();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$searchDialogs$16(TLObject r5) {
        int r0 = 0;
        this.sponsoredReqId = 0;
        if ((r5 instanceof TLRPC.TL_contacts_sponsoredPeersEmpty) == false) goto L9;
        if (this.sponsoredPeers.isEmpty() == true) goto L19;
        this.sponsoredPeers.clear();
        notifyDataSetChanged();
        return;
    L19:
        return;
    L9:
        if ((r5 instanceof TLRPC.TL_contacts_sponsoredPeers) == false) goto L20;
        TLRPC.TL_contacts_sponsoredPeers r52 = (TLRPC.TL_contacts_sponsoredPeers) r5;
        MessagesController.getInstance(this.currentAccount).putUsers(r52.users, true);
        MessagesController.getInstance(this.currentAccount).putChats(r52.chats, true);
        if (RemoteUtils.getBooleanConfigValue("hide_ads", false).booleanValue() == false) goto L16;
        ArrayList<TLRPC.TL_sponsoredPeer> r1 = r52.peers;
        int r2 = r1.size();
    L13:
        if (r0 >= r2) goto L15;
        TLRPC.TL_sponsoredPeer r3 = r1.get(r0);
        r0 = r0 + 1;
        seenSponsoredPeer(r3);
        goto L13
    L15:
        r52.peers.clear();
    L16:
        this.sponsoredPeers.addAll(r52.peers);
        notifyDataSetChanged();
        return;
    }

    private /* synthetic */ void lambda$searchDialogs$17(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new DialogsSearchAdapter$$ExternalSyntheticLambda24(this, r1));
    }

    private /* synthetic */ void lambda$searchDialogs$18(int r18, String r19, String r20) {
        this.searchRunnable2 = null;
        if (r18 == this.lastSearchId) goto L6;
        return;
    L6:
        if (this.needMessagesSearch == 2) goto L35;
        int r1 = this.dialogsType;
        if (r1 == 6) goto L35;
        if (r1 == 5) goto L35;
        if (this.delegate.getSearchForumDialogId() != 0) goto L35;
        SearchAdapterHelper r12 = this.searchAdapterHelper;
        int r4 = this.dialogsType;
        boolean r8 = false;
        if (r4 == 4) goto L16;
        boolean r9 = true;
    L17:
        if (r4 != 4) goto L19;
    L21:
        long r10 = 0;
        boolean r6 = false;
    L22:
        if (r4 == 2) goto L26;
        if (r4 == 1) goto L26;
        boolean r7 = false;
    L27:
        if (r4 != 0) goto L29;
        r8 = true;
    L29:
        DialogsSearchAdapterDelegate r2 = this.delegate;
        if (r2 == null) goto L33;
        long r13 = r2.getSearchForumDialogId();
    L34:
        r12.queryServerSearch(r19, true, r9, true, r6, r7, 0, r8, 0, r18, r13);
    L37:
        if (this.needMessagesSearch != 0) goto L39;
    L43:
        this.waitingResponseCount--;
        return;
    L39:
        if (this.dialogsType == 15) goto L43;
        searchTopics(r20);
        searchMessagesInternal(r20, r18);
        searchForumMessagesInternal(r20, r18);
        return;
    L33:
        r13 = r10;
    L26:
        r7 = true;
        goto L27
    L19:
        if (r4 == 11) goto L21;
        r10 = 0;
        r6 = true;
        goto L22
    L16:
        r9 = false;
    L35:
        this.waitingResponseCount -= 2;
        goto L37
    }

    private /* synthetic */ void lambda$searchDialogs$19(String r3, int r4, String r5) {
        this.searchRunnable = null;
        searchDialogsInternal(r3, r4);
        if (this.dialogsType != 15) goto L6;
        this.waitingResponseCount -= 2;
        return;
    L6:
        DialogsSearchAdapter$$ExternalSyntheticLambda29 r0 = new DialogsSearchAdapter$$ExternalSyntheticLambda29(this, r4, r3, r5);
        this.searchRunnable2 = r0;
        AndroidUtilities.runOnUIThread(r0);
    }

    private /* synthetic */ void lambda$searchDialogs$20(int r6, TLObject r7, String r8) {
        if (r6 == this.lastSearchId) goto L6;
        return;
    L6:
        if ((r7 instanceof TLRPC.messages_Messages) == false) goto L27;
        TLRPC.messages_Messages r72 = (TLRPC.messages_Messages) r7;
        boolean r0 = false;
        if ((r72 instanceof TLRPC.TL_messages_messages) == false) goto L11;
        int r62 = ((TLRPC.TL_messages_messages) r72).messages.size();
    L14:
        this.publicPostsTotalCount = r62;
        this.publicPostsLastRate = r72.next_rate;
        this.publicPostsHashtag = r8;
        MessagesController.getInstance(this.currentAccount).putUsers(r72.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r72.chats, false);
        int r63 = 0;
    L16:
        if (r63 >= r72.messages.size()) goto L18;
        this.publicPosts.add(new MessageObject(this.currentAccount, r72.messages.get(r63), false, true));
        r63 = r63 + 1;
        goto L16
    L18:
        DialogsSearchAdapterDelegate r64 = this.delegate;
        if (r64 != null) goto L21;
    L24:
        notifyDataSetChanged();
        return;
    L21:
        if (this.waitingResponseCount <= 0) goto L23;
        r0 = true;
    L23:
        r64.searchStateChanged(r0, true);
        goto L24
    L11:
        if ((r72 instanceof TLRPC.TL_messages_messagesSlice) == false) goto L13;
        r62 = ((TLRPC.TL_messages_messagesSlice) r72).count;
        goto L14
    L13:
        r62 = 0;
        goto L14
    }

    private /* synthetic */ void lambda$searchDialogs$21(int r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new DialogsSearchAdapter$$ExternalSyntheticLambda34(this, r1, r3, r2));
    }

    private /* synthetic */ void lambda$searchDialogs$22(int r4, String r5) {
        this.searchHashtagRunnable = null;
        if (r4 == this.lastSearchId) goto L6;
        return;
    L6:
        if (this.searchHashtagRequest < 0) goto L8;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.searchHashtagRequest, true);
    L8:
        TLRPC.TL_channels_searchPosts r0 = new TLRPC.TL_channels_searchPosts();
        r0.flags = 1 | r0.flags;
        r0.hashtag = r5;
        r0.limit = 3;
        r0.offset_peer = new TLRPC.TL_inputPeerEmpty();
        this.searchHashtagRequest = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new DialogsSearchAdapter$$ExternalSyntheticLambda27(this, r4, r5));
    }

    private /* synthetic */ void lambda$searchDialogsInternal$12() {
        FilteredSearchView.Delegate r0 = this.filtersDelegate;
        if (r0 == null) goto L6;
        r0.updateFiltersView(false, null, this.localTipDates, this.localTipArchive);
        return;
    }

    private /* synthetic */ void lambda$searchDialogsInternal$13(String r17, int r18, String r19) {
        ArrayList<Object> r2 = new ArrayList();
        ArrayList<CharSequence> r3 = new ArrayList();
        ArrayList<TLRPC.User> r4 = new ArrayList();
        ArrayList<ContactsController.Contact> r10 = new ArrayList();
        MessagesStorage.getInstance(this.currentAccount).localSearch(this.dialogsType, r17, r2, r3, r4, this.filterDialogIds, -1);
        if (TextUtils.isEmpty(r17) == true) goto L30;
        if (TextUtils.isDigitsOnly(r17) == false) goto L30;
        long r11 = Long.parseLong(r17);     // Catch: NumberFormatException -> L15
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r11));     // Catch: NumberFormatException -> L15
        if (r0 == null) goto L18;
        int r32 = r2.size();     // Catch: NumberFormatException -> L15
        int r5 = 0;
    L9:
        if (r5 >= r32) goto L17;
        Object r9 = r2.get(r5);     // Catch: NumberFormatException -> L15
        r5 = r5 + 1;     // Catch: NumberFormatException -> L15
        if ((r9 instanceof TLRPC.User) == false) goto L9;
        if (((TLRPC.User) r9).id != r0.id) goto L9;
    L17:
        r2.add(0, r0);     // Catch: NumberFormatException -> L15
        r3.add(0, UserObject.getUserName(r0));     // Catch: NumberFormatException -> L15
    L18:
        TLRPC.Chat r02 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r11));     // Catch: NumberFormatException -> L15
        if (r02 == null) goto L30;
        int r33 = r2.size();     // Catch: NumberFormatException -> L15
        int r52 = 0;
    L21:
        if (r52 >= r33) goto L27;
        Object r8 = r2.get(r52);     // Catch: NumberFormatException -> L15
        r52 = r52 + 1;     // Catch: NumberFormatException -> L15
        if ((r8 instanceof TLRPC.Chat) == false) goto L21;
        if (((TLRPC.Chat) r8).id != r02.id) goto L21;
    L27:
        r2.add(0, r02);     // Catch: NumberFormatException -> L15
        r3.add(0, r02.title);     // Catch: NumberFormatException -> L15
    L15:
        e = move-exception;
        FileLog.e(e);
    L30:
        updateSearchResults(r2, r3, r4, r10, r18);
        FiltersView.fillTipDates(r17, this.localTipDates);
        this.localTipArchive = false;
        if (r17.length() >= 3) goto L33;
    L37:
        AndroidUtilities.runOnUIThread(new DialogsSearchAdapter$$ExternalSyntheticLambda37(this));
        return;
    L33:
        if (LocaleController.getString(R.string.ArchiveSearchFilter).toLowerCase().startsWith(r17) == false) goto L35;
    L36:
        this.localTipArchive = true;
        goto L37
    L35:
        if ("archive".startsWith(r19) == false) goto L37;
        goto L36
    }

    private /* synthetic */ void lambda$searchForumMessagesInternal$0(int r5, int r6, TLRPC.TL_error r7, String r8, TLObject r9, TLRPC.TL_messages_search r10, ArrayList r11) {
        if (r5 != this.lastForumReqId) goto L42;
        if (r6 > 0) goto L6;
    L7:
        this.waitingResponseCount--;
        if (r7 != null) goto L42;
        this.currentMessagesQuery = r8;
        TLRPC.messages_Messages r92 = (TLRPC.messages_Messages) r9;
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r92.users, r92.chats, true, true);
        MessagesController.getInstance(this.currentAccount).putUsers(r92.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r92.chats, false);
        if (r10.add_offset != 0) goto L12;
        this.searchForumResultMessages.clear();
    L12:
        this.nextSearchRate = r92.next_rate;
        int r52 = 0;
    L14:
        if (r52 >= r92.messages.size()) goto L22;
        TLRPC.Message r72 = r92.messages.get(r52);
        int r82 = MessagesController.getInstance(this.currentAccount).deletedHistory.get(MessageObject.getDialogId(r72));
        if (r82 != 0) goto L18;
    L20:
        this.searchForumResultMessages.add((MessageObject) r11.get(r52));
    L21:
        r52 = r52 + 1;
        goto L14
    L18:
        if (r72.id > r82) goto L20;
    L22:
        this.searchWas = true;
        if (r92.messages.size() == 20) goto L25;
        boolean r53 = true;
    L26:
        this.localMessagesSearchEndReached = r53;
        if (r6 <= 0) goto L34;
        this.lastMessagesSearchId = r6;
        if (this.lastLocalSearchId == r6) goto L32;
        this.searchResult.clear();
    L32:
        if (this.lastGlobalSearchId == r6) goto L34;
        this.searchAdapterHelper.clear();
    L34:
        this.searchAdapterHelper.mergeResults(this.searchResult, this.filtered2RecentSearchObjects);
        DialogsSearchAdapterDelegate r54 = this.delegate;
        if (r54 != null) goto L37;
    L41:
        notifyDataSetChanged();
        goto L42
    L37:
        if (this.waitingResponseCount <= 0) goto L39;
        boolean r62 = true;
    L40:
        r54.searchStateChanged(r62, true);
        this.delegate.runResultsEnterAnimation();
        goto L41
    L39:
        r62 = false;
        goto L40
    L25:
        r53 = false;
        goto L26
    L6:
        if (r6 == this.lastSearchId) goto L7;
    L42:
        this.reqForumId = 0;
    }

    private /* synthetic */ void lambda$searchForumMessagesInternal$1(String r11, int r12, int r13, TLRPC.TL_messages_search r14, TLObject r15, TLRPC.TL_error r16) {
        ArrayList r8 = new ArrayList();
        if (r16 != null) goto L16;
        TLRPC.messages_Messages r0 = (TLRPC.messages_Messages) r15;
        LongSparseArray r5 = new LongSparseArray();
        LongSparseArray r4 = new LongSparseArray();
        int r2 = 0;
    L6:
        if (r2 >= r0.chats.size()) goto L8;
        TLRPC.Chat r3 = r0.chats.get(r2);
        r5.put(r3.id, r3);
        r2 = r2 + 1;
        goto L6
    L8:
        int r22 = 0;
    L10:
        if (r22 >= r0.users.size()) goto L12;
        TLRPC.User r32 = r0.users.get(r22);
        r4.put(r32.id, r32);
        r22 = r22 + 1;
        goto L10
    L12:
        int r9 = 0;
    L14:
        if (r9 >= r0.messages.size()) goto L16;
        MessageObject r1 = new MessageObject(this.currentAccount, r0.messages.get(r9), r4, r5, false, true);
        r8.add(r1);
        r1.setQuery(r11);
        r9 = r9 + 1;
    L16:
        AndroidUtilities.runOnUIThread(new DialogsSearchAdapter$$ExternalSyntheticLambda31(this, r12, r13, r16, r11, r15, r14, r8));
    }

    private /* synthetic */ void lambda$searchMessagesInternal$2(int r8, int r9, TLRPC.TL_error r10, String r11, TLObject r12, TLRPC.TL_messages_searchGlobal r13, ArrayList r14) {
        if (r8 != this.lastReqId) goto L69;
        if (r9 > 0) goto L6;
    L7:
        this.waitingResponseCount--;
        if (r10 != null) goto L69;
        this.currentMessagesQuery = r11;
        TLRPC.messages_Messages r122 = (TLRPC.messages_Messages) r12;
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r122.users, r122.chats, true, true);
        MessagesController.getInstance(this.currentAccount).putUsers(r122.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r122.chats, false);
        if (r13.offset_id != 0) goto L12;
        this.searchResultMessages.clear();
    L12:
        this.nextSearchRate = r122.next_rate;
        int r82 = 0;
    L14:
        if (r82 >= r122.messages.size()) goto L46;
        TLRPC.Message r102 = r122.messages.get(r82);
        int r112 = MessagesController.getInstance(this.currentAccount).deletedHistory.get(MessageObject.getDialogId(r102));
        if (r112 != 0) goto L18;
    L20:
        MessageObject r113 = (MessageObject) r14.get(r82);
        if (this.searchForumResultMessages.isEmpty() == true) goto L34;
        int r132 = 0;
    L24:
        if (r132 >= this.searchForumResultMessages.size()) goto L34;
        MessageObject r2 = this.searchForumResultMessages.get(r132);
        if (r2 == null) goto L33;
        if (r113 == null) goto L33;
        if (r113.getId() != r2.getId()) goto L33;
        if (r113.getDialogId() != r2.getDialogId()) goto L33;
    L45:
        r82 = r82 + 1;
    L33:
        r132 = r132 + 1;
    L34:
        this.searchResultMessages.add(r113);
        long r22 = MessageObject.getDialogId(r102);
        boolean r114 = r102.out;
        int r133 = this.currentAccount;
        if (r114 == false) goto L37;
        ConcurrentHashMap<Long, Integer> r115 = MessagesController.getInstance(r133).dialogs_read_outbox_max;
    L38:
        Integer r116 = r115.get(Long.valueOf(r22));
        if (r116 == null) goto L45;
        if (r116.intValue() >= r102.id) goto L43;
        boolean r117 = true;
    L44:
        r102.unread = r117;
        goto L45
    L43:
        r117 = false;
        goto L44
    L37:
        r115 = MessagesController.getInstance(r133).dialogs_read_inbox_max;
        goto L38
    L18:
        if (r102.id > r112) goto L20;
    L46:
        this.searchWas = true;
        if (r122.messages.size() == 20) goto L49;
        boolean r83 = true;
    L50:
        this.messagesSearchEndReached = r83;
        if (r9 <= 0) goto L58;
        this.lastMessagesSearchId = r9;
        if (this.lastLocalSearchId == r9) goto L56;
        this.searchResult.clear();
    L56:
        if (this.lastGlobalSearchId == r9) goto L58;
        this.searchAdapterHelper.clear();
    L58:
        this.searchAdapterHelper.mergeResults(this.searchResult, this.filtered2RecentSearchObjects);
        DialogsSearchAdapterDelegate r84 = this.delegate;
        if (r84 != null) goto L61;
    L65:
        this.globalSearchCollapsed = true;
        this.phoneCollapsed = true;
        this.forceLoadingMessages = false;
        EmptyLayout r85 = this.messagesEmptyLayout;
        if (r85 == null) goto L68;
        r85.setQuery(this.lastMessagesSearchString);
    L68:
        notifyDataSetChanged();
        goto L69
    L61:
        if (this.waitingResponseCount <= 0) goto L63;
        boolean r92 = true;
    L64:
        r84.searchStateChanged(r92, true);
        this.delegate.runResultsEnterAnimation();
        goto L65
    L63:
        r92 = false;
        goto L64
    L49:
        r83 = false;
        goto L50
    L6:
        if (r9 == this.lastSearchId) goto L7;
    L69:
        this.reqId = 0;
    }

    private /* synthetic */ void lambda$searchMessagesInternal$3(HashSet r8, Runnable r9) {
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        Iterator r82 = r8.iterator();
    L4:
        if (r82.hasNext() == false) goto L10;
        Pair r1 = (Pair) r82.next();
        boolean r2 = ((Boolean) r1.first).booleanValue();
        Long r12 = (Long) r1.second;
        long r3 = r12.longValue();
        if (r2 == false) goto L8;
        ConcurrentHashMap<Long, Integer> r5 = r0.dialogs_read_outbox_max;
    L9:
        r5.put(r12, Integer.valueOf(MessagesStorage.getInstance(this.currentAccount).getDialogReadMaxSync(r2, r3)));
        goto L4
    L8:
        r5 = r0.dialogs_read_inbox_max;
        goto L9
    L10:
        AndroidUtilities.runOnUIThread(r9);
    }

    private /* synthetic */ void lambda$searchMessagesInternal$4(String r17, int r18, int r19, TLRPC.TL_messages_searchGlobal r20, TLObject r21, TLRPC.TL_error r22) {
        ArrayList r8 = new ArrayList();
        int r0 = 0;
        if (r22 != null) goto L16;
        TLRPC.messages_Messages r2 = (TLRPC.messages_Messages) r21;
        LongSparseArray r13 = new LongSparseArray();
        LongSparseArray r12 = new LongSparseArray();
        int r3 = 0;
    L6:
        if (r3 >= r2.chats.size()) goto L8;
        TLRPC.Chat r4 = r2.chats.get(r3);
        r13.put(r4.id, r4);
        r3 = r3 + 1;
        goto L6
    L8:
        int r32 = 0;
    L10:
        if (r32 >= r2.users.size()) goto L12;
        TLRPC.User r42 = r2.users.get(r32);
        r12.put(r42.id, r42);
        r32 = r32 + 1;
        goto L10
    L12:
        int r33 = 0;
    L14:
        if (r33 >= r2.messages.size()) goto L16;
        MessageObject r9 = new MessageObject(this.currentAccount, r2.messages.get(r33), r12, r13, false, true);
        r8.add(r9);
        r9.setQuery(r17);
        r33 = r33 + 1;
    L16:
        HashSet r92 = new HashSet();
        if (r22 != null) goto L29;
        TLRPC.messages_Messages r23 = (TLRPC.messages_Messages) r21;
    L20:
        if (r0 >= r23.messages.size()) goto L29;
        TLRPC.Message r34 = r23.messages.get(r0);
        long r6 = MessageObject.getDialogId(r34);
        boolean r43 = r34.out;
        int r10 = this.currentAccount;
        if (r43 == false) goto L24;
        ConcurrentHashMap<Long, Integer> r44 = MessagesController.getInstance(r10).dialogs_read_outbox_max;
    L26:
        if (r44.get(Long.valueOf(r6)) != null) goto L28;
        r92.add(new Pair(Boolean.valueOf(r34.out), Long.valueOf(r6)));
    L28:
        r0 = r0 + 1;
        goto L20
    L24:
        r44 = MessagesController.getInstance(r10).dialogs_read_inbox_max;
    L29:
        DialogsSearchAdapter$$ExternalSyntheticLambda32 r02 = new DialogsSearchAdapter$$ExternalSyntheticLambda32(this, r18, r19, r22, r17, r21, r20, r8);
        if (r92.isEmpty() == false) goto L33;
        AndroidUtilities.runOnUIThread(r02);
        return;
    L33:
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new DialogsSearchAdapter$$ExternalSyntheticLambda33(this, r92, r02));
    }

    private /* synthetic */ void lambda$updateSearchResults$14(long r2, Object r4, int r5) {
        if (r5 == (-1)) goto L12;
        TLRPC.TL_dialog r0 = new TLRPC.TL_dialog();
        r0.id = r2;
        if (r5 == 0) goto L8;
        r0.folder_id = r5;
    L8:
        if ((r4 instanceof TLRPC.Chat) == false) goto L10;
        r0.flags = ChatObject.isChannel((TLRPC.Chat) r4) ? 1 : 0;
    L10:
        MessagesController.getInstance(this.currentAccount).dialogs_dict.put(r2, r0);
        MessagesController.getInstance(this.currentAccount).getAllDialogs().add(r0);
        MessagesController.getInstance(this.currentAccount).sortDialogs(null);
        return;
    }

    private /* synthetic */ void lambda$updateSearchResults$15(int r10, ArrayList r11, ArrayList r12, ArrayList r13) {
        this.waitingResponseCount--;
        if (r10 != this.lastSearchId) goto L79;
        this.lastLocalSearchId = r10;
        if (this.lastGlobalSearchId == r10) goto L9;
        this.searchAdapterHelper.clear();
    L9:
        if (this.lastMessagesSearchId == r10) goto L11;
        this.searchResultMessages.clear();
    L11:
        this.searchWas = true;
        boolean r102 = false;
        int r0 = 0;
    L13:
        if (r0 >= r11.size()) goto L18;
        if (filter(r11.get(r0)) == true) goto L17;
        r11.remove(r0);
        r0 = r0 - 1;
    L17:
        r0 = r0 + 1;
        goto L13
    L18:
        int r02 = this.filtered2RecentSearchObjects.size();
        int r2 = 0;
    L20:
        if (r2 >= r11.size()) goto L58;
        Object r3 = r11.get(r2);
        if ((r3 instanceof TLRPC.User) == false) goto L25;
        TLRPC.User r4 = (TLRPC.User) r3;
        MessagesController.getInstance(this.currentAccount).putUser(r4, true);
        long r7 = r4.id;
    L32:
        if (r7 == 0) goto L37;
        if (MessagesController.getInstance(this.currentAccount).dialogs_dict.get(r7) != null) goto L37;
        MessagesStorage.getInstance(this.currentAccount).getDialogFolderId(r7, new DialogsSearchAdapter$$ExternalSyntheticLambda39(this, r7, r3));
    L37:
        if (recentSearchAvailable() == false) goto L57;
        if ((r3 instanceof TLRPC.EncryptedChat) == true) goto L57;
        DialogsSearchAdapterDelegate r32 = this.delegate;
        if (r32 != null) goto L43;
    L45:
        boolean r33 = false;
    L46:
        int r42 = 0;
    L47:
        if (r33 == true) goto L55;
        if (r42 >= r02) goto L55;
        RecentSearchObject r5 = this.filtered2RecentSearchObjects.get(r42);
        if (r5 == null) goto L54;
        if (r5.did != r7) goto L54;
        r33 = true;
    L54:
        r42 = r42 + 1;
    L55:
        if (r33 == false) goto L57;
        r11.remove(r2);
        r12.remove(r2);
        r2 = r2 - 1;
        goto L57
    L43:
        if (r32.getSearchForumDialogId() != r7) goto L45;
        r33 = true;
    L57:
        r2 = r2 + 1;
        goto L20
    L25:
        if ((r3 instanceof TLRPC.Chat) == false) goto L28;
        TLRPC.Chat r43 = (TLRPC.Chat) r3;
        MessagesController.getInstance(this.currentAccount).putChat(r43, true);
        r7 = -r43.id;
        goto L32
    L28:
        if ((r3 instanceof TLRPC.EncryptedChat) == false) goto L30;
        MessagesController.getInstance(this.currentAccount).putEncryptedChat((TLRPC.EncryptedChat) r3, true);
    L30:
        r7 = 0;
        goto L32
    L58:
        MessagesController.getInstance(this.currentAccount).putUsers(r13, true);
        this.searchResult = r11;
        this.searchResultNames = r12;
        this.searchAdapterHelper.mergeResults(r11, this.filtered2RecentSearchObjects);
        notifyDataSetChanged();
        DialogsSearchAdapterDelegate r112 = this.delegate;
        if (r112 != null) goto L61;
        return;
    L61:
        if (this.waitingResponseCount <= 0) goto L63;
        r102 = true;
    L63:
        r112.searchStateChanged(r102, true);
        this.delegate.runResultsEnterAnimation();
        return;
    }

    public static void loadRecentSearch(int r2, int r3, OnRecentSearchLoaded r4) {
        MessagesStorage.getInstance(r2).getStorageQueue().postRunnable(new DialogsSearchAdapter$$ExternalSyntheticLambda0(r2, r3, r4));
    }

    private boolean recentSearchAvailable() {
        int r2 = this.dialogsType;
        if (r2 != 2) goto L5;
        return false;
    L5:
        if (r2 != 4) goto L7;
        return false;
    L7:
        if (r2 != 5) goto L9;
        return false;
    L9:
        if (r2 != 6) goto L11;
        return false;
    L11:
        if (r2 != 1) goto L13;
        return false;
    L13:
        if (r2 != 11) goto L15;
        return false;
    L15:
        if (r2 == 15) goto L24;
        return true;
    L24:
        return false;
    }

    private void searchDialogsInternal(String r7, int r8) {
        if (this.needMessagesSearch != 2) goto L5;
        return;
    L5:
        String r0 = r7.trim().toLowerCase();
        if (r0.length() != 0) goto L9;
        this.lastSearchId = 0;
        updateSearchResults(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), this.lastSearchId);
        return;
    L9:
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new DialogsSearchAdapter$$ExternalSyntheticLambda35(this, r0, r8, r7));
    }

    private void searchForumMessagesInternal(String r11, int r12) {
        DialogsSearchAdapterDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getSearchForumDialogId() != 0) goto L8;
        return;
    L8:
        if (this.needMessagesSearch != 0) goto L10;
        return;
    L10:
        if (TextUtils.isEmpty(this.lastMessagesSearchString) == false) goto L15;
        if (TextUtils.isEmpty(r11) == false) goto L15;
        return;
    L15:
        if (this.reqForumId == 0) goto L18;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqForumId, true);
        this.reqForumId = 0;
    L18:
        if (TextUtils.isEmpty(r11) == false) goto L22;
        this.filteredRecentQuery = null;
        this.searchResultMessages.clear();
        this.searchForumResultMessages.clear();
        this.lastForumReqId = 0;
        this.lastMessagesSearchString = null;
        this.searchWas = false;
        notifyDataSetChanged();
        return;
    L22:
        if (this.dialogsType == 15) goto L35;
        long r2 = this.delegate.getSearchForumDialogId();
        TLRPC.TL_messages_search r9 = new TLRPC.TL_messages_search();
        r9.limit = 20;
        r9.q = r11;
        r9.filter = new TLRPC.TL_inputMessagesFilterEmpty();
        r9.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r2);
        if (r11.equals(this.lastMessagesSearchString) == true) goto L27;
    L29:
        this.lastMessagesSearchString = r11;
        int r7 = this.lastForumReqId + 1;
        this.lastForumReqId = r7;
        this.reqForumId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r9, new DialogsSearchAdapter$$ExternalSyntheticLambda28(this, r11, r7, r12, r9), 2);
        return;
    L27:
        if (this.searchForumResultMessages.isEmpty() == true) goto L29;
        r9.add_offset = this.searchForumResultMessages.size();
        goto L29
    }

    private void searchMessagesInternal(String r8, int r9) {
        if (this.needMessagesSearch != 0) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(this.lastMessagesSearchString) == true) goto L7;
    L9:
        boolean r2 = false;
        if (this.reqId == 0) goto L13;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
        this.reqId = 0;
    L13:
        if (TextUtils.isEmpty(r8) == true) goto L55;
        if (this.delegate.getSearchForumDialogId() != 0) goto L55;
        filterRecent(r8);
        this.searchAdapterHelper.mergeResults(this.searchResult, this.filtered2RecentSearchObjects);
        if (this.dialogsType != 15) goto L25;
        int r82 = this.waitingResponseCount - 1;
        this.waitingResponseCount = r82;
        DialogsSearchAdapterDelegate r92 = this.delegate;
        if (r92 == null) goto L58;
        if (r82 <= 0) goto L23;
        r2 = true;
    L23:
        r92.searchStateChanged(r2, true);
        this.delegate.runResultsEnterAnimation();
        return;
    L58:
        return;
    L25:
        TLRPC.TL_messages_searchGlobal r5 = new TLRPC.TL_messages_searchGlobal();
        int r0 = this.currentMessagesFilter.flags;
        if ((r0 & 2) == 0) goto L28;
        boolean r3 = true;
    L29:
        r5.broadcasts_only = r3;
        if ((r0 & 4) == 0) goto L32;
        boolean r32 = true;
    L33:
        r5.groups_only = r32;
        if ((r0 & 8) == 0) goto L36;
        boolean r02 = true;
    L37:
        r5.users_only = r02;
        r5.limit = 20;
        r5.q = r8;
        r5.filter = new TLRPC.TL_inputMessagesFilterEmpty();
        int r03 = this.folderId;
        if (r03 == 0) goto L41;
        r5.flags |= 1;
        r5.folder_id = r03;
    L41:
        if (r8.equals(this.lastMessagesSearchString) == true) goto L44;
        this.forceLoadingMessages = false;
    L44:
        if (r8.equals(this.lastMessagesSearchString) == true) goto L46;
    L52:
        r5.offset_rate = 0;
        r5.offset_id = 0;
        r5.offset_peer = new TLRPC.TL_inputPeerEmpty();
    L53:
        this.lastMessagesSearchString = r8;
        this.lastMessagesSearchFilterFlags = this.currentMessagesFilter.flags;
        int r33 = this.lastReqId + 1;
        this.lastReqId = r33;
        this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r5, new DialogsSearchAdapter$$ExternalSyntheticLambda30(this, r8, r33, r9, r5), 2);
        return;
    L46:
        if (this.lastMessagesSearchFilterFlags != this.currentMessagesFilter.flags) goto L52;
        if (this.searchResultMessages.isEmpty() == true) goto L52;
        if (this.lastMessagesSearchId != this.lastSearchId) goto L52;
        ArrayList<MessageObject> r04 = this.searchResultMessages;
        MessageObject r05 = r04.get(r04.size() - 1);
        r5.offset_id = r05.getId();
        r5.offset_rate = this.nextSearchRate;
        r5.offset_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(MessageObject.getPeerId(r05.messageOwner.peer_id));
        goto L53
    L36:
        r02 = false;
        goto L37
    L32:
        r32 = false;
        goto L33
    L28:
        r3 = false;
    L55:
        this.filteredRecentQuery = null;
        this.searchResultMessages.clear();
        this.searchForumResultMessages.clear();
        this.lastReqId = 0;
        this.lastMessagesSearchString = null;
        this.lastMessagesSearchFilterFlags = 0;
        this.searchWas = false;
        notifyDataSetChanged();
        return;
    L7:
        if (TextUtils.isEmpty(r8) == false) goto L9;
    }

    private void searchTopics(String r5) {
        this.searchTopics.clear();
        DialogsSearchAdapterDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getSearchForumDialogId() != 0) goto L8;
        return;
    L8:
        if (TextUtils.isEmpty(r5) == true) goto L18;
        long r02 = this.delegate.getSearchForumDialogId();
        ArrayList<TLRPC.TL_forumTopic> r03 = MessagesController.getInstance(this.currentAccount).getTopicsController().getTopics(-r02);
        String r52 = r5.trim();
        int r1 = 0;
    L11:
        if (r1 >= r03.size()) goto L18;
        if (r03.get(r1) == null) goto L17;
        if (r03.get(r1).title.toLowerCase().contains(r52) == false) goto L17;
        this.searchTopics.add(r03.get(r1));
        r03.get(r1).searchQuery = r52;
    L17:
        r1 = r1 + 1;
    L18:
        notifyDataSetChanged();
    }

    private void setRecentSearch(ArrayList<RecentSearchObject> r4, LongSparseArray<RecentSearchObject> r5) {
        this.recentSearchObjects = r4;
        this.recentSearchObjectsById = r5;
        int r42 = 0;
    L4:
        if (r42 >= this.recentSearchObjects.size()) goto L15;
        RecentSearchObject r52 = this.recentSearchObjects.get(r42);
        TLObject r0 = r52.object;
        if ((r0 instanceof TLRPC.User) == false) goto L9;
        MessagesController.getInstance(this.currentAccount).putUser((TLRPC.User) r52.object, true);
    L14:
        r42 = r42 + 1;
        goto L4
    L9:
        if ((r0 instanceof TLRPC.Chat) == false) goto L12;
        MessagesController.getInstance(this.currentAccount).putChat((TLRPC.Chat) r52.object, true);
        goto L14
    L12:
        if ((r0 instanceof TLRPC.EncryptedChat) == false) goto L14;
        MessagesController.getInstance(this.currentAccount).putEncryptedChat((TLRPC.EncryptedChat) r52.object, true);
        goto L14
    L15:
        filterRecent(null);
        notifyDataSetChanged();
    }

    private void updateSearchResults(ArrayList<Object> r1, ArrayList<CharSequence> r2, ArrayList<TLRPC.User> r3, ArrayList<ContactsController.Contact> r4, int r5) {
        AndroidUtilities.runOnUIThread(new DialogsSearchAdapter$$ExternalSyntheticLambda36(this, r5, r1, r2, r3));
    }

    private boolean wordStartsWith(String r3, String r4) {
        if (r4 == null) goto L18;
        if (r3 == null) goto L18;
        String[] r32 = r3.toLowerCase().split(" ");
        int r0 = 0;
    L8:
        if (r0 >= r32.length) goto L18;
        String r1 = r32[r0];
        if (r1 == null) goto L17;
        if (r1.startsWith(r4) == true) goto L15;
        if (r4.startsWith(r32[r0]) == false) goto L17;
        return true;
    L15:
        return true;
    L17:
        r0 = r0 + 1;
    L18:
        return false;
    }

    public void addHashtagsFromMessage(CharSequence r1) {
        this.searchAdapterHelper.addHashtagsFromMessage(r1);
    }

    public void clearRecentHashtags() {
        this.searchAdapterHelper.clearRecentHashtags();
        this.searchResultHashtags.clear();
        notifyDataSetChanged();
    }

    public void clearRecentSearch() {
        String r2 = null;
        if (this.searchWas == false) goto L14;
        StringBuilder r0 = null;
    L6:
        if (this.filtered2RecentSearchObjects.size() <= 0) goto L11;
        RecentSearchObject r3 = this.filtered2RecentSearchObjects.remove(0);
        this.recentSearchObjects.remove(r3);
        this.filteredRecentSearchObjects.remove(r3);
        this.recentSearchObjectsById.remove(r3.did);
        if (r0 == null) goto L9;
        r0.append(", ");
        r0.append(r3.did);
        goto L6
    L9:
        r0 = new StringBuilder("did IN (");
        r0.append(r3.did);
        goto L6
    L11:
        if (r0 != null) goto L13;
        r0 = new StringBuilder("1");
    L15:
        String r1 = this.lastSearchText;
        if (r1 == null) goto L18;
        r2 = r1.trim();
    L18:
        filterRecent(r2);
        notifyDataSetChanged();
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new DialogsSearchAdapter$$ExternalSyntheticLambda38(this, r0));
        return;
    L13:
        r0.append(")");
        goto L15
    L14:
        this.filtered2RecentSearchObjects.clear();
        this.filteredRecentSearchObjects.clear();
        this.recentSearchObjects.clear();
        this.recentSearchObjectsById.clear();
        r0 = new StringBuilder("1");
        goto L15
    }

    public void clickedSponsoredPeer(TLRPC.TL_sponsoredPeer r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        TLRPC.TL_messages_clickSponsoredMessage r0 = new TLRPC.TL_messages_clickSponsoredMessage();
        r0.random_id = r2.random_id;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
    }

    public void filterRecent(String r8) {
        this.filteredRecentQuery = r8;
        this.filtered2RecentSearchObjects.clear();
        int r1 = 0;
        if (TextUtils.isEmpty(r8) == false) goto L15;
        this.filteredRecentSearchObjects.clear();
        int r82 = this.recentSearchObjects.size();
    L5:
        if (r1 >= r82) goto L54;
        DialogsSearchAdapterDelegate r0 = this.delegate;
        if (r0 == null) goto L11;
        if (r0.getSearchForumDialogId() != this.recentSearchObjects.get(r1).did) goto L11;
    L14:
        r1 = r1 + 1;
    L11:
        if (filter(this.recentSearchObjects.get(r1).object) == false) goto L14;
        this.filteredRecentSearchObjects.add(this.recentSearchObjects.get(r1));
        goto L14
    L54:
        return;
    L15:
        String r83 = r8.toLowerCase();
        int r02 = this.recentSearchObjects.size();
    L16:
        if (r1 >= r02) goto L66;
        RecentSearchObject r2 = this.recentSearchObjects.get(r1);
        if (r2 == null) goto L53;
        if (r2.object == null) goto L53;
        DialogsSearchAdapterDelegate r3 = this.delegate;
        if (r3 == null) goto L27;
        if (r3.getSearchForumDialogId() == r2.did) goto L53;
    L27:
        if (filter(this.recentSearchObjects.get(r1).object) == false) goto L53;
        TLObject r32 = r2.object;
        if ((r32 instanceof TLRPC.Chat) == false) goto L37;
        TLRPC.Chat r33 = (TLRPC.Chat) r32;
        if (r33.monoforum == false) goto L34;
        String r34 = ForumUtilities.getMonoForumTitle(this.currentAccount, r33);
    L35:
        String r4 = ((TLRPC.Chat) r2.object).username;
    L43:
        if (r34 != null) goto L45;
    L46:
        if (r4 != null) goto L48;
    L51:
        if (this.filtered2RecentSearchObjects.size() < 5) goto L53;
        return;
    L48:
        if (wordStartsWith(r4.toLowerCase(), r83) == false) goto L51;
    L49:
        this.filtered2RecentSearchObjects.add(r2);
        goto L51
    L45:
        if (wordStartsWith(r34.toLowerCase(), r83) == true) goto L49;
    L34:
        r34 = r33.title;
        goto L35
    L37:
        if ((r32 instanceof TLRPC.User) == false) goto L40;
        r34 = UserObject.getUserName((TLRPC.User) r32);
        r4 = ((TLRPC.User) r2.object).username;
        goto L43
    L40:
        if ((r32 instanceof TLRPC.ChatInvite) == false) goto L42;
        r34 = ((TLRPC.ChatInvite) r32).title;
        r4 = null;
        goto L43
    L42:
        r34 = null;
        r4 = null;
    L53:
        r1 = r1 + 1;
        goto L16
    }

    public int getCurrentItemCount() {
        return this.currentItemCount;
    }

    public RecyclerListView getInnerListView() {
        return this.innerListView;
    }

    /* JADX WARN: Type inference failed for: r0v34, types: [boolean] */
    public Object getItem(int r12) {
        if (this.publicPosts.isEmpty() == true) goto L11;
        if (r12 <= 0) goto L9;
        int r0 = r12 - 1;
        if (r0 >= this.publicPosts.size()) goto L9;
        return this.publicPosts.get(r0);
    L9:
        r12 = r12 - (this.publicPosts.size() + 1);
    L11:
        if (this.searchResultHashtags.isEmpty() == true) goto L17;
        if (r12 > 0) goto L14;
        return null;
    L14:
        return this.searchResultHashtags.get(r12 - 1);
    L17:
        if (isRecentSearchDisplayed() == false) goto L39;
        ?? r02 = hasHints();
        if (this.searchWas == false) goto L21;
        ArrayList<RecentSearchObject> r3 = this.filtered2RecentSearchObjects;
    L22:
        if (r12 <= r02) goto L37;
        int r4 = (r12 - 1) - (r02 == true ? 1 : 0);
        if (r4 >= r3.size()) goto L37;
        TLObject r122 = r3.get(r4).object;
        if ((r122 instanceof TLRPC.User) == false) goto L32;
        TLRPC.User r11 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(((TLRPC.User) r122).id));
        if (r11 == null) goto L30;
        return r11;
    L30:
        return r122;
    L32:
        if ((r122 instanceof TLRPC.Chat) == false) goto L36;
        TLRPC.Chat r112 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(((TLRPC.Chat) r122).id));
        if (r112 == null) goto L36;
        return r112;
    L36:
        return r122;
    L37:
        r12 = r12 - getRecentItemsCount();
        goto L39
    L21:
        r3 = this.filteredRecentSearchObjects;
    L39:
        if (this.searchTopics.isEmpty() == true) goto L47;
        if (r12 > 0) goto L42;
    L45:
        r12 = r12 - (this.searchTopics.size() + 1);
        goto L47
    L42:
        if (r12 > this.searchTopics.size()) goto L45;
        return this.searchTopics.get(r12 - 1);
    L47:
        if (this.searchContacts.isEmpty() == true) goto L54;
        if (r12 > 0) goto L50;
    L53:
        r12 = r12 - (this.searchContacts.size() + 1);
        goto L54
    L50:
        if (r12 > this.searchContacts.size()) goto L53;
        return this.searchContacts.get(r12 - 1);
    L54:
        ArrayList<TLObject> r03 = this.searchAdapterHelper.getGlobalSearch();
        ArrayList<TLObject> r32 = this.searchAdapterHelper.getLocalServerSearch();
        ArrayList<Object> r42 = this.searchAdapterHelper.getPhoneSearch();
        int r5 = this.searchResult.size();
        int r6 = r32.size();
        if ((r5 + r6) > 0) goto L57;
    L65:
        int r7 = r42.size();
        int r8 = 3;
        if (r7 > 3) goto L68;
    L70:
        int r9 = r03.size();
        if (r9 > 3) goto L73;
    L75:
        r8 = r9;
    L76:
        int r10 = 0;
        if (r03.isEmpty() == true) goto L79;
    L81:
        int r82 = (r8 + this.sponsoredPeers.size()) + 1;
    L82:
        if (r12 < 0) goto L86;
        if (r12 >= r5) goto L86;
        return this.searchResult.get(r12);
    L86:
        int r123 = r12 - r5;
        if (r123 < 0) goto L91;
        if (r123 >= r6) goto L91;
        return r32.get(r123);
    L91:
        int r124 = r123 - r6;
        if (r124 < 0) goto L96;
        if (r124 >= r7) goto L96;
        return r42.get(r124);
    L96:
        int r125 = r124 - r7;
        if (r125 <= 0) goto L111;
        if (r125 >= r82) goto L111;
        int r126 = r125 - 1;
        if (r126 >= 0) goto L102;
    L105:
        int r127 = r126 - this.sponsoredPeers.size();
        if (r127 < 0) goto L113;
        if (r127 >= r03.size()) goto L113;
        return r03.get(r127);
    L113:
        if (this.searchForumResultMessages.isEmpty() == false) goto L115;
        int r04 = 0;
    L116:
        if (r127 <= 0) goto L122;
        if (r127 > this.searchForumResultMessages.size()) goto L122;
        return this.searchForumResultMessages.get(r127 - 1);
    L122:
        if (this.localMessagesSearchEndReached == false) goto L124;
    L126:
        int r128 = r127 - (r04 + r10);
        if (this.searchResultMessages.isEmpty() == true) goto L130;
        this.searchResultMessages.size();
    L130:
        if (r128 > 0) goto L132;
    L135:
        return null;
    L132:
        if (r128 > this.searchResultMessages.size()) goto L135;
        return this.searchResultMessages.get(r128 - 1);
    L124:
        if (this.searchForumResultMessages.isEmpty() == true) goto L126;
        r10 = 1;
        goto L126
    L115:
        r04 = this.searchForumResultMessages.size() + 1;
        goto L116
    L102:
        if (r126 >= this.sponsoredPeers.size()) goto L105;
        return this.sponsoredPeers.get(r126);
    L111:
        r127 = r125 - r82;
        goto L113
    L79:
        if (this.sponsoredPeers.isEmpty() == false) goto L81;
        r82 = 0;
        goto L82
    L73:
        if (this.globalSearchCollapsed == false) goto L75;
    L68:
        if (this.phoneCollapsed == false) goto L70;
        r7 = 3;
        goto L70
    L57:
        if (getRecentItemsCount() <= 0) goto L59;
    L62:
        if (r12 != 0) goto L64;
        return null;
    L64:
        r12 = r12 - 1;
        goto L65
    L59:
        if (this.searchTopics.isEmpty() == false) goto L62;
        if (this.publicPosts.isEmpty() == true) goto L65;
        goto L62
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int r1 = 0;
        int r2 = 3;
        if (this.waitingResponseCount != 3) goto L6;
        return 0;
    L6:
        if (this.publicPosts.isEmpty() == true) goto L8;
        int r0 = this.publicPosts.size() + 1;
    L10:
        if (this.searchResultHashtags.isEmpty() == true) goto L14;
        return r0 + (this.searchResultHashtags.size() + 1);
    L14:
        if (isRecentSearchDisplayed() == false) goto L19;
        r0 = r0 + getRecentItemsCount();
        if (this.searchWas == true) goto L19;
        return r0;
    L19:
        if (this.searchTopics.isEmpty() == true) goto L22;
        r0 = (r0 + 1) + this.searchTopics.size();
    L22:
        if (this.searchContacts.isEmpty() == true) goto L24;
        r0 = r0 + (this.searchContacts.size() + 1);
    L24:
        int r4 = this.searchResult.size();
        int r5 = this.searchAdapterHelper.getLocalServerSearch().size();
        int r02 = (r0 + r4) + r5;
        int r6 = this.searchAdapterHelper.getGlobalSearch().size();
        if (r6 > 3) goto L27;
    L29:
        int r62 = r6 + this.sponsoredPeers.size();
        int r7 = this.searchAdapterHelper.getPhoneSearch().size();
        if (r7 <= 3) goto L35;
        if (this.phoneCollapsed == false) goto L35;
        r7 = 3;
    L35:
        if ((r4 + r5) > 0) goto L37;
    L43:
        if (r62 == 0) goto L45;
        r02 = r02 + (r62 + 1);
    L45:
        if (r7 == 0) goto L47;
        r02 = r02 + r7;
    L47:
        int r42 = this.searchForumResultMessages.size();
        if (r42 == 0) goto L51;
        r02 = r02 + ((r42 + 1) + (!this.localMessagesSearchEndReached ? 1 : 0));
    L51:
        if (this.localMessagesSearchEndReached == true) goto L53;
        this.localMessagesLoadingRow = r02;
    L53:
        int r43 = this.searchResultMessages.size();
        if (this.currentMessagesFilter != Filter.All) goto L58;
        if (this.forceLoadingMessages == true) goto L58;
    L65:
        if (this.searchForumResultMessages.isEmpty() == false) goto L67;
    L69:
        r1 = r43;
    L70:
        if (r1 == 0) goto L73;
        r02 = r02 + ((r1 + 1) + (!this.messagesSearchEndReached ? 1 : 0));
    L73:
        if (this.localMessagesSearchEndReached == false) goto L75;
        this.localMessagesLoadingRow = r02;
    L75:
        this.currentItemCount = r02;
        return r02;
    L67:
        if (this.localMessagesSearchEndReached == true) goto L69;
    L58:
        if (this.searchResultMessages.isEmpty() == false) goto L65;
        if (this.forceLoadingMessages == true) goto L63;
        r2 = 1;
    L63:
        r43 = r2;
        goto L65
    L37:
        if (getRecentItemsCount() <= 0) goto L39;
    L42:
        r02 = r02 + 1;
        goto L43
    L39:
        if (this.searchTopics.isEmpty() == false) goto L42;
        if (this.publicPosts.isEmpty() == true) goto L43;
    L27:
        if (this.globalSearchCollapsed == false) goto L29;
        r6 = 3;
        goto L29
    L8:
        r0 = 0;
        goto L10
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int r1) {
        return r1;
    }

    /* JADX WARN: Type inference failed for: r0v37, types: [boolean] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r12) {
        if (this.searchResultHashtags.isEmpty() == true) goto L9;
        if (r12 != 0) goto L6;
        return 1;
    L6:
        return 5;
    L9:
        if (this.publicPosts.isEmpty() == true) goto L18;
        if (r12 != 0) goto L12;
        return 1;
    L12:
        int r122 = r12 - 1;
        if (r122 >= this.publicPosts.size()) goto L16;
        return 9;
    L16:
        r12 = r122 - this.publicPosts.size();
    L18:
        if (isRecentSearchDisplayed() == false) goto L29;
        ?? r0 = hasHints();
        if (r12 >= r0) goto L23;
        return 6;
    L23:
        if (r12 != r0) goto L26;
        return 1;
    L26:
        if (r12 >= getRecentItemsCount()) goto L28;
        return 0;
    L28:
        r12 = r12 - getRecentItemsCount();
    L29:
        int r3 = 3;
        if (this.searchTopics.isEmpty() == true) goto L38;
        if (r12 != 0) goto L34;
        return 1;
    L34:
        if (r12 > this.searchTopics.size()) goto L36;
        return 3;
    L36:
        r12 = r12 - (this.searchTopics.size() + 1);
    L38:
        if (this.searchContacts.isEmpty() == true) goto L46;
        if (r12 != 0) goto L42;
        return 1;
    L42:
        if (r12 > this.searchContacts.size()) goto L45;
        return 8;
    L45:
        r12 = r12 - (this.searchContacts.size() + 1);
    L46:
        ArrayList<TLObject> r02 = this.searchAdapterHelper.getGlobalSearch();
        int r4 = this.searchResult.size();
        int r5 = this.searchAdapterHelper.getLocalServerSearch().size();
        if ((r4 + r5) > 0) goto L49;
    L57:
        int r6 = this.searchAdapterHelper.getPhoneSearch().size();
        if (r6 > 3) goto L60;
    L62:
        int r7 = r02.size();
        if (r7 > 3) goto L65;
    L67:
        r3 = r7;
    L69:
        if (this.sponsoredPeers.isEmpty() == true) goto L71;
    L73:
        int r32 = (r3 + this.sponsoredPeers.size()) + 1;
    L75:
        if (this.searchResultMessages.isEmpty() == false) goto L77;
        int r03 = 0;
    L78:
        Filter r72 = this.currentMessagesFilter;
        Filter r8 = Filter.All;
        if (r72 != r8) goto L83;
        if (this.forceLoadingMessages == true) goto L83;
    L89:
        if (this.searchForumResultMessages.isEmpty() == true) goto L94;
        if (this.localMessagesSearchEndReached == true) goto L94;
        r03 = 0;
    L94:
        if (this.searchForumResultMessages.isEmpty() == false) goto L96;
        int r73 = 0;
    L97:
        if (r12 < 0) goto L100;
        if (r12 >= r4) goto L100;
        return 0;
    L100:
        int r123 = r12 - r4;
        if (r123 < 0) goto L104;
        if (r123 >= r5) goto L104;
        return 0;
    L104:
        int r124 = r123 - r5;
        if (r124 < 0) goto L115;
        if (r124 >= r6) goto L115;
        Object r11 = getItem(r124);
        if ((r11 instanceof String) == true) goto L110;
        return 0;
    L110:
        if ("section".equals((String) r11) == false) goto L112;
        return 1;
    L112:
        return 7;
    L115:
        int r125 = r124 - r6;
        if (r125 < 0) goto L121;
        if (r125 >= r32) goto L121;
        if (r125 != 0) goto L120;
        return 1;
    L120:
        return 0;
    L121:
        int r126 = r125 - r32;
        if (r73 <= 0) goto L135;
        if (r126 >= 0) goto L125;
    L134:
        r126 = r126 - (r73 + (!this.localMessagesSearchEndReached ? 1 : 0));
        goto L135
    L125:
        if (this.localMessagesSearchEndReached == false) goto L128;
        if (r126 >= r73) goto L134;
    L129:
        if (r126 != 0) goto L131;
        return 1;
    L131:
        if (r126 != r73) goto L133;
        return 4;
    L133:
        return 2;
    L128:
        if (r126 > r73) goto L134;
    L135:
        if (r126 < 0) goto L151;
        if (r126 >= r03) goto L151;
        if (r126 != 0) goto L140;
        return 1;
    L140:
        if (this.forceLoadingMessages == false) goto L145;
        if (this.searchResultMessages.isEmpty() == false) goto L145;
        return 4;
    L145:
        if (this.currentMessagesFilter != r8) goto L147;
    L150:
        return 2;
    L147:
        if (this.searchResultMessages.isEmpty() == false) goto L150;
        return 10;
    L151:
        return 4;
    L96:
        r73 = this.searchForumResultMessages.size() + 1;
    L83:
        if (this.searchResultMessages.isEmpty() == false) goto L89;
        if (this.forceLoadingMessages == false) goto L87;
        r03 = 4;
        goto L89
    L87:
        r03 = 2;
        goto L89
    L77:
        r03 = this.searchResultMessages.size() + 1;
        goto L78
    L71:
        if (r02.isEmpty() == false) goto L73;
        r32 = 0;
        goto L75
    L65:
        if (this.globalSearchCollapsed == false) goto L67;
    L60:
        if (this.phoneCollapsed == false) goto L62;
        r6 = 3;
        goto L62
    L49:
        if (getRecentItemsCount() <= 0) goto L51;
    L54:
        if (r12 != 0) goto L56;
        return 1;
    L56:
        r12 = r12 - 1;
        goto L57
    L51:
        if (this.searchTopics.isEmpty() == false) goto L54;
        if (this.publicPosts.isEmpty() == true) goto L57;
        goto L54
    }

    public String getLastSearchString() {
        return this.lastMessagesSearchString;
    }

    public int getRecentItemsCount() {
        if (this.searchWas == false) goto L5;
        ArrayList<RecentSearchObject> r0 = this.filtered2RecentSearchObjects;
    L7:
        if (r0.isEmpty() == true) goto L9;
        int r02 = r0.size() + 1;
    L11:
        return r02 + (hasHints() ? 1 : 0);
    L9:
        r02 = 0;
        goto L11
    L5:
        r0 = this.filteredRecentSearchObjects;
        goto L7
    }

    public int getRecentResultsCount() {
        if (this.searchWas == false) goto L5;
        ArrayList<RecentSearchObject> r1 = this.filtered2RecentSearchObjects;
    L6:
        if (r1 != null) goto L8;
        return 0;
    L8:
        return r1.size();
    L5:
        r1 = this.filteredRecentSearchObjects;
        goto L6
    }

    public boolean hasRecentSearch() {
        if (recentSearchAvailable() == true) goto L5;
        return false;
    L5:
        if (getRecentItemsCount() <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r2) {
        int r1 = r2.getItemViewType();
        if (r1 != 1) goto L5;
        return false;
    L5:
        if (r1 != 4) goto L7;
        return false;
    L7:
        if (r1 == 10) goto L12;
        return true;
    L12:
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v30, types: [boolean] */
    public boolean isGlobalSearch(int r10) {
        if (this.searchWas == true) goto L6;
        return false;
    L6:
        if (this.searchResultHashtags.isEmpty() == true) goto L9;
        return false;
    L9:
        if (this.publicPosts.isEmpty() == true) goto L12;
        r10 = r10 - (this.publicPosts.size() + 1);
    L12:
        if (isRecentSearchDisplayed() == false) goto L22;
        ?? r0 = hasHints();
        if (this.searchWas == false) goto L16;
        ArrayList<RecentSearchObject> r3 = this.filtered2RecentSearchObjects;
    L17:
        if (r10 > r0) goto L19;
    L21:
        r10 = r10 - getRecentItemsCount();
        goto L22
    L19:
        if (((r10 - 1) - (r0 == true ? 1 : 0)) >= r3.size()) goto L21;
        return false;
    L16:
        r3 = this.filteredRecentSearchObjects;
    L22:
        ArrayList<TLObject> r02 = this.searchAdapterHelper.getGlobalSearch();
        ArrayList<TLObject> r32 = this.searchAdapterHelper.getLocalServerSearch();
        int r4 = this.searchResult.size();
        int r33 = r32.size();
        int r5 = this.searchAdapterHelper.getPhoneSearch().size();
        int r6 = 3;
        if (r5 > 3) goto L25;
    L27:
        int r7 = r02.size();
        if (r7 > 3) goto L30;
    L32:
        r6 = r7;
    L34:
        if (r02.isEmpty() == true) goto L36;
    L38:
        int r62 = (r6 + this.sponsoredPeers.size()) + 1;
    L39:
        int r03 = this.searchContacts.size();
        if (r03 <= 0) goto L46;
        if (r10 < 0) goto L44;
        if (r10 >= r03) goto L44;
        return false;
    L44:
        r10 = r10 - (r03 + 1);
    L46:
        if ((r4 + r33) > 0) goto L48;
    L56:
        if (r10 < 0) goto L59;
        if (r10 >= r4) goto L59;
        return false;
    L59:
        int r102 = r10 - r4;
        if (r102 < 0) goto L63;
        if (r102 >= r33) goto L63;
        return false;
    L63:
        int r103 = r102 - r33;
        if (r103 <= 0) goto L67;
        if (r103 >= r5) goto L67;
        return false;
    L67:
        int r104 = r103 - r5;
        if (r104 <= 0) goto L71;
        if (r104 >= r62) goto L71;
        return true;
    L71:
        int r105 = r104 - r62;
        if (this.searchForumResultMessages.isEmpty() == false) goto L74;
        int r04 = 0;
    L75:
        if (r105 <= 0) goto L79;
        if (r105 >= r04) goto L79;
        return false;
    L79:
        if (this.searchResultMessages.isEmpty() == true) goto L83;
        this.searchResultMessages.size();
    L83:
        if (this.currentMessagesFilter == Filter.All) goto L85;
    L86:
        this.searchResultMessages.isEmpty();
    L87:
        return false;
    L85:
        if (this.forceLoadingMessages == false) goto L87;
    L74:
        r04 = this.searchForumResultMessages.size() + 1;
        goto L75
    L48:
        if (getRecentItemsCount() <= 0) goto L50;
    L53:
        if (r10 != 0) goto L55;
        return false;
    L55:
        r10 = r10 - 1;
        goto L56
    L50:
        if (this.searchTopics.isEmpty() == false) goto L53;
        if (this.publicPosts.isEmpty() == true) goto L56;
    L36:
        if (this.sponsoredPeers.isEmpty() == false) goto L38;
        r62 = 0;
        goto L39
    L30:
        if (this.globalSearchCollapsed == false) goto L32;
    L25:
        if (this.phoneCollapsed == false) goto L27;
        r5 = 3;
        goto L27
    }

    public boolean isHashtagSearch() {
        return !this.searchResultHashtags.isEmpty();
    }

    public boolean isMessagesSearchEndReached() {
        if (this.delegate.getSearchForumDialogId() == 0) goto L7;
        if (this.localMessagesSearchEndReached == true) goto L7;
        return false;
    L7:
        if (this.messagesSearchEndReached == false) goto L12;
        return true;
    L12:
        return false;
    }

    public boolean isRecentSearchDisplayed() {
        if (this.needMessagesSearch != 2) goto L5;
        return false;
    L5:
        if (hasRecentSearch() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isSearchWas() {
        return this.searchWas;
    }

    public boolean isSearching() {
        if (this.waitingResponseCount <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void loadMoreSearchMessages() {
        if (this.reqForumId == 0) goto L8;
        if (this.reqId == 0) goto L8;
        return;
    L8:
        if (this.lastMessagesSearchId == this.lastSearchId) goto L10;
        return;
    L10:
        DialogsSearchAdapterDelegate r0 = this.delegate;
        if (r0 != null) goto L13;
    L18:
        searchMessagesInternal(this.lastMessagesSearchString, this.lastMessagesSearchId);
        return;
    L13:
        if (r0.getSearchForumDialogId() == 0) goto L18;
        if (this.localMessagesSearchEndReached == true) goto L18;
        searchForumMessagesInternal(this.lastMessagesSearchString, this.lastMessagesSearchId);
    }

    public void loadRecentSearch() {
        int r0 = this.dialogsType;
        if (r0 != 15) goto L5;
        return;
    L5:
        loadRecentSearch(this.currentAccount, r0, new DialogsSearchAdapter$$ExternalSyntheticLambda4(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r0v18, types: [android.text.SpannableStringBuilder] */
    /* JADX WARN: Type inference failed for: r15v21 */
    /* JADX WARN: Type inference failed for: r15v22, types: [boolean] */
    /* JADX WARN: Type inference failed for: r15v27 */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r2v42, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.view.View, org.telegram.ui.Cells.ProfileSearchCell] */
    /* JADX WARN: Type inference failed for: r4v17, types: [org.telegram.tgnet.TLRPC$User] */
    /* JADX WARN: Type inference failed for: r4v47 */
    /* JADX WARN: Type inference failed for: r4v48 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v20, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v37 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v15, types: [android.text.SpannableStringBuilder] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r31, int r32) {
        int r2 = r32;
        boolean r10 = false;
        switch(r31.getItemViewType()) {
            case 0: goto L155;
            case 1: goto L27;
            case 2: goto L18;
            case 3: goto L16;
            case 4: goto L4;
            case 5: goto L11;
            case 6: goto L9;
            case 7: goto L7;
            case 8: goto L5;
            case 9: goto L18;
            default: goto L4;
        };
    L4:
        return;
    L5:
        ProfileSearchCell r11 = (ProfileSearchCell) r31.itemView;
        ContactsController.Contact r12 = (ContactsController.Contact) getItem(r2);
        r11.setData(r12, null, ContactsController.formatName(r12.first_name, r12.last_name), PhoneFormat.getInstance().format("+" + r12.shortPhones.get(0)), false, false);
        return;
    L7:
        String r1 = (String) getItem(r2);
        TextCell r0 = (TextCell) r31.itemView;
        r0.setColors(-1, Theme.key_windowBackgroundWhiteBlueText2);
        r0.setText(LocaleController.formatString("AddContactByPhone", R.string.AddContactByPhone, new Object[]{PhoneFormat.getInstance().format("+" + r1)}), false);
        return;
    L9:
        ((CategoryAdapterRecycler) ((RecyclerListView) r31.itemView).getAdapter()).setIndex(r2 / 2);
        return;
    L11:
        HashtagSearchCell r02 = (HashtagSearchCell) r31.itemView;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r02.setText(this.searchResultHashtags.get(r2 - 1));
        if (r2 == this.searchResultHashtags.size()) goto L14;
        r10 = true;
    L14:
        r02.setNeedDivider(r10);
        return;
    L16:
        ((TopicSearchCell) r31.itemView).setTopic((TLRPC.TL_forumTopic) getItem(r2));
        return;
    L18:
        DialogCell r03 = (DialogCell) r31.itemView;
        r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        if (r2 == (getItemCount() - 1)) goto L21;
        r10 = true;
    L21:
        r03.useSeparator = r10;
        MessageObject r4 = (MessageObject) getItem(r2);
        r03.useFromUserAsAvatar = this.searchForumResultMessages.contains(r4);
        if (r4 != null) goto L25;
        r03.setDialog(0, null, 0, false, false);
        return;
    L25:
        r03.setDialog(r4.getDialogId(), r4, r4.messageOwner.date, false, false);
        return;
    L27:
        GraySectionCell r04 = (GraySectionCell) r31.itemView;
        if (this.searchResultHashtags.isEmpty() == true) goto L32;
        r04.setText(LocaleController.getString(R.string.Hashtags), LocaleController.getString(R.string.ClearButton), new DialogsSearchAdapter$$ExternalSyntheticLambda14(this));
        return;
    L32:
        if (this.publicPosts.isEmpty() == true) goto L37;
        if (r2 != 0) goto L36;
        r04.setText(LocaleController.getString(R.string.PublicPostsTabs), AndroidUtilities.replaceArrows(LocaleController.getString(R.string.PublicPostsMore), false, AndroidUtilities.dp(-2.0f), AndroidUtilities.dp(1.0f)), new DialogsSearchAdapter$$ExternalSyntheticLambda15(this));
        return;
    L36:
        int r3 = r2 - (this.publicPosts.size() + 1);
    L38:
        ArrayList<TLObject> r42 = this.searchAdapterHelper.getGlobalSearch();
        if (isRecentSearchDisplayed() == false) goto L41;
    L46:
        ?? r6 = hasHints();
        if (r3 >= r6) goto L50;
        r04.setText(LocaleController.getString(R.string.ChatHints));
        return;
    L50:
        if (r3 == r6) goto L52;
    L59:
        int r62 = getRecentItemsCount();
        if (this.searchTopics.isEmpty() == false) goto L62;
        int r7 = 0;
    L63:
        int r63 = r62 + r7;
        if (this.searchContacts.isEmpty() == false) goto L66;
        int r72 = 0;
    L68:
        if (r3 == (r63 + r72)) goto L70;
    L73:
        r3 = r3 - getRecentItemsCount();
    L74:
        int r64 = this.searchResult.size();
        int r73 = this.searchAdapterHelper.getLocalServerSearch().size();
        int r122 = this.searchAdapterHelper.getPhoneSearch().size();
        if (r122 > 3) goto L77;
    L79:
        int r13 = r42.size();
        if (r13 <= 3) goto L85;
        if (this.globalSearchCollapsed == false) goto L85;
        r13 = 3;
    L85:
        if (r42.isEmpty() == true) goto L87;
    L89:
        int r132 = (r13 + this.sponsoredPeers.size()) + 1;
    L91:
        if (this.searchForumResultMessages.isEmpty() == false) goto L93;
        int r14 = 0;
    L95:
        if (this.searchResultMessages.isEmpty() == true) goto L99;
        this.searchResultMessages.size();
    L99:
        if (this.currentMessagesFilter == Filter.All) goto L101;
    L102:
        this.searchResultMessages.isEmpty();
    L104:
        if (this.searchTopics.isEmpty() == true) goto L109;
        if (r3 != 0) goto L107;
        String r9 = LocaleController.getString(R.string.Topics);
    L108:
        r3 = r3 - (this.searchTopics.size() + 1);
    L111:
        if (this.searchContacts.isEmpty() == true) goto L115;
        if (r3 != 0) goto L114;
        r9 = LocaleController.getString(R.string.InviteToTelegramShort);
    L114:
        r3 = r3 - (this.searchContacts.size() + 1);
    L115:
        if (r9 != null) goto L122;
        int r33 = r3 - (r64 + r73);
        if (r33 < 0) goto L123;
        if (r33 >= r122) goto L123;
        r9 = LocaleController.getString(R.string.PhoneNumberSearch);
        if (this.searchAdapterHelper.getPhoneSearch().size() <= 3) goto L122;
        r10 = this.phoneCollapsed;
        Runnable r92 = new DialogsSearchAdapter$$ExternalSyntheticLambda18(this, r04);
        String r22 = r9;
        CharSequence r15 = null;
    L143:
        if (r92 != null) goto L146;
        r04.setText(r22);
        return;
    L146:
        if (r15 == null) goto L149;
        r04.setText(r22, r15, new DialogsSearchAdapter$$ExternalSyntheticLambda21(r92));
        r04.setRightTextMargin(6);
        return;
    L149:
        if (r10 == false) goto L152;
        int r16 = R.string.ShowMore;
    L153:
        r04.setText(r22, LocaleController.getString(r16), new DialogsSearchAdapter$$ExternalSyntheticLambda12(r92));
        r04.setRightTextMargin(16);
        return;
    L152:
        r16 = R.string.ShowLess;
    L123:
        int r34 = r33 - r122;
        if (r34 < 0) goto L130;
        if (r34 >= r132) goto L130;
        r9 = LocaleController.getString(R.string.GlobalSearch);
        if (this.searchAdapterHelper.getGlobalSearch().size() <= 3) goto L122;
        r10 = this.globalSearchCollapsed;
        DialogsSearchAdapter$$ExternalSyntheticLambda19 r35 = new DialogsSearchAdapter$$ExternalSyntheticLambda19(this, r42, r2, r04);
        r22 = r9;
        r15 = null;
        r92 = r35;
    L130:
        if (this.delegate == null) goto L142;
        if (r14 <= 0) goto L142;
        if ((r34 - r132) > 1) goto L142;
        TLRPC.Chat r23 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.delegate.getSearchForumDialogId()));
        int r36 = R.string.SearchMessagesIn;
        if (r23 != null) goto L138;
        String r17 = "null";
    L141:
        r9 = LocaleController.formatString(r36, new Object[]{r17});
        goto L122
    L138:
        if (r23.monoforum == false) goto L140;
        r17 = ForumUtilities.getMonoForumTitle(this.currentAccount, r23);
        goto L141
    L140:
        r17 = r23.title;
    L142:
        this.messagesSectionPosition = r34;
        CharSequence r93 = getFilterFromString(this.currentMessagesFilter);
        DialogsSearchAdapter$$ExternalSyntheticLambda20 r24 = new DialogsSearchAdapter$$ExternalSyntheticLambda20(this, r04);
        r22 = LocaleController.getString(R.string.SearchMessages);
        r15 = r93;
        r92 = r24;
    L122:
        r22 = r9;
        r15 = null;
        r92 = null;
        goto L143
    L107:
        r9 = null;
        goto L108
    L109:
        r9 = null;
        goto L111
    L101:
        if (this.forceLoadingMessages == false) goto L104;
    L93:
        r14 = this.searchForumResultMessages.size() + 1;
        goto L95
    L87:
        if (this.sponsoredPeers.isEmpty() == false) goto L89;
        r132 = 0;
        goto L91
    L77:
        if (this.phoneCollapsed == false) goto L79;
        r122 = 3;
        goto L79
    L70:
        if (this.searchResult.isEmpty() == true) goto L73;
        r04.setText(LocaleController.getString(R.string.SearchAllChatsShort));
        return;
    L66:
        r72 = this.searchContacts.size() + 1;
        goto L68
    L62:
        r7 = this.searchTopics.size() + 1;
        goto L63
    L52:
        if (isRecentSearchDisplayed() == false) goto L59;
        if (this.searchWas == true) goto L57;
        r04.setText(LocaleController.getString(R.string.Recent), LocaleController.getString(R.string.ClearButton), new DialogsSearchAdapter$$ExternalSyntheticLambda16(this));
        return;
    L57:
        r04.setText(LocaleController.getString(R.string.Recent), LocaleController.getString(R.string.Clear), new DialogsSearchAdapter$$ExternalSyntheticLambda17(this));
        return;
    L41:
        if (this.searchTopics.isEmpty() == false) goto L46;
        if (this.searchContacts.isEmpty() == false) goto L46;
        if (this.publicPosts.isEmpty() == true) goto L74;
    L37:
        r3 = r2;
        goto L38
    L155:
        ?? r37 = (ProfileSearchCell) r31.itemView;
        r37.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        long r123 = r37.getDialogId();
        boolean r05 = isGlobalSearch(r2);
        Object r65 = getItem(r2);
        boolean r94 = r65 instanceof TLRPC.TL_sponsoredPeer;
        if (r94 == false) goto L170;
        TLRPC.TL_sponsoredPeer r142 = (TLRPC.TL_sponsoredPeer) r65;
        seenSponsoredPeer(r142);
        long r143 = DialogObject.getPeerDialogId(r142.peer);
        char r18 = 2;
        int r43 = this.currentAccount;
        if (r143 < 0) goto L164;
        TLRPC.User r44 = MessagesController.getInstance(r43).getUser(Long.valueOf(r143));
        if (r44 == null) goto L162;
        ArrayList<TLRPC.TL_username> r144 = r44.usernames;
        String r172 = DialogObject.getPublicUsername(r44, this.currentMessagesQuery);
        ArrayList<TLRPC.TL_username> r152 = r144;
        TLRPC.Chat r145 = null;
        TLRPC.User r45 = r44;
    L168:
        Object r312 = r65;
        ArrayList<TLRPC.TL_username> r5 = r152;
        TLRPC.User r46 = r45;
    L169:
        TLRPC.EncryptedChat r19 = null;
        int r153 = 1;
        ?? r47 = r46;
    L186:
        if (this.publicPosts.isEmpty() == true) goto L189;
        r2 = r2 - (this.publicPosts.size() + r153);
    L189:
        if (isRecentSearchDisplayed() == true) goto L191;
        ?? r66 = 0;
    L201:
        if (this.searchTopics.isEmpty() == true) goto L203;
        r2 = r2 - (this.searchTopics.size() + r153);
    L203:
        ArrayList<TLObject> r102 = this.searchAdapterHelper.getGlobalSearch();
        ArrayList<Object> r112 = this.searchAdapterHelper.getPhoneSearch();
        int r21 = r153;
        if ((this.searchResult.size() + this.searchAdapterHelper.getLocalServerSearch().size()) > 0) goto L206;
    L212:
        int r8 = r112.size();
        if (r8 > 3) goto L215;
    L217:
        if (r8 > 0) goto L219;
    L221:
        int r74 = r8;
    L222:
        int r113 = r102.size();
        int r25 = r74;
        if (r113 <= 3) goto L228;
        if (this.globalSearchCollapsed == false) goto L228;
        r113 = 3;
    L228:
        if (r102.isEmpty() == true) goto L230;
    L232:
        int r75 = (r113 + this.sponsoredPeers.size()) + 1;
    L233:
        if (r66 != 0) goto L244;
        if (r2 != ((getItemCount() - getRecentItemsCount()) - 1)) goto L237;
    L241:
        ?? r154 = 0;
    L242:
        r37.useSeparator = r154;
        goto L244
    L237:
        if (r2 == (((r15 + r25) + r7) - 1)) goto L241;
        if (r2 == ((((r15 + r75) + r8) + r7) - 1)) goto L241;
        r154 = r21;
    L244:
        if (r2 >= 0) goto L246;
    L256:
        String r26 = null;
    L254:
        String r82 = null;
    L257:
        if (r26 != null) goto L328;
        if (r66 == 0) goto L260;
        String r103 = this.filteredRecentQuery;
    L262:
        if (TextUtils.isEmpty(r103) == true) goto L328;
        if (r47 == 0) goto L265;
        String r114 = ContactsController.formatName(r47.first_name, r47.last_name);
    L271:
        if (r114 == null) goto L276;
        int r155 = AndroidUtilities.indexOfIgnoreCase(r114, r103);
        boolean r252 = r05;
        if (r155 == (-1)) goto L275;
        ?? r83 = new SpannableStringBuilder(r114);
        String r262 = r26;
        r83.setSpan(new ForegroundColorSpanThemable(Theme.key_windowBackgroundWhiteBlueText4), r155, r103.length() + r155, 33);
        String r84 = r83;
    L277:
        if (r5 != null) goto L279;
    L305:
        String r27 = r84;
    L306:
        String r28 = r172;
    L307:
        if (r28 == null) goto L327;
        if (r47 == 0) goto L311;
        if (r252 == false) goto L327;
    L311:
        if (r103.startsWith("@") == false) goto L382;
        r103 = r103.substring(1);
    L382:
        ?? r06 = new SpannableStringBuilder();     // Catch: Exception -> L321
        r06.append("@");     // Catch: Exception -> L321
        r06.append(r28);     // Catch: Exception -> L321
        int r52 = AndroidUtilities.indexOfIgnoreCase(r28, r103);     // Catch: Exception -> L321
        if (r52 == (-1)) goto L323;
        int r76 = r103.length();     // Catch: Exception -> L321
        if (r52 != 0) goto L318;
        r76 = r76 + 1;     // Catch: Exception -> L321
    L319:
        r06.setSpan(new ForegroundColorSpanThemable(Theme.key_windowBackgroundWhiteBlueText4), r52, r76 + r52, 33);     // Catch: Exception -> L321
        goto L323
    L318:
        r52 = r52 + 1;     // Catch: Exception -> L321
    L323:
        String r29 = r06;
    L324:
        String r85 = r27;
        String r210 = r29;
    L329:
        r37.setChecked(false, false);
        if (r47 == 0) goto L336;
        String r322 = r85;
        if (r47.id == this.selfUserId) goto L334;
    L337:
        String r20 = r322;
        boolean r212 = false;
        String r211 = r210;
    L339:
        if (r145 != null) goto L341;
    L356:
        if (r47 != 0) goto L358;
    L355:
        boolean r156 = true;
        boolean r242 = false;
        String r213 = r211;
    L368:
        r37.allowBotOpenButton(r66, new DialogsSearchAdapter$$ExternalSyntheticLambda11(this));
        r37.setOnSponsoredOptionsClick(new DialogsSearchAdapter$$ExternalSyntheticLambda13(this));
        if (r94 == false) goto L371;
        TLRPC.TL_sponsoredPeer r95 = (TLRPC.TL_sponsoredPeer) r312;
    L372:
        r37.setAd(r95);
        if (r47 == 0) goto L375;
        TLRPC.Chat r182 = r47;
    L376:
        r37.setData(r182, r19, r20, r213, true, r212);
        boolean r07 = this.delegate.isSelected(r37.getDialogId());
        if (r123 != r37.getDialogId()) goto L379;
        boolean r104 = r156;
    L380:
        r37.setChecked(r07, r104);
        return;
    L379:
        r104 = r242;
        goto L380
    L375:
        r182 = r145;
        goto L376
    L371:
        r95 = null;
        goto L372
    L358:
        if (r47.bot == false) goto L355;
        int r53 = r47.bot_active_users;
        if (r53 == 0) goto L355;
        String r54 = LocaleController.formatPluralStringSpaced("BotUsersShort", r53);
        if ((r211 instanceof SpannableStringBuilder) == false) goto L365;
        ((SpannableStringBuilder) r211).append(", ").append(r54);
        goto L355
    L365:
        if (TextUtils.isEmpty(r211) == true) goto L367;
        CharSequence[] r77 = new CharSequence[3];
        r242 = false;
        r77[0] = r211;
        r156 = true;
        r77[1] = ", ";
        r77[r18] = r54;
        r213 = TextUtils.concat(r77);
        goto L368
    L367:
        r156 = true;
        r242 = false;
        r213 = r54;
        goto L368
    L341:
        if (r145.participants_count == 0) goto L356;
        if (ChatObject.isChannel(r145) == true) goto L345;
    L347:
        String r55 = LocaleController.formatPluralStringSpaced("Members", r145.participants_count);
    L349:
        if ((r211 instanceof SpannableStringBuilder) == false) goto L352;
        ((SpannableStringBuilder) r211).append(", ").append(r55);
        goto L355
    L352:
        if (TextUtils.isEmpty(r211) == true) goto L354;
        CharSequence[] r78 = new CharSequence[3];
        r78[0] = r211;
        r78[1] = ", ";
        r78[r18] = r55;
        r211 = TextUtils.concat(r78);
        goto L355
    L354:
        r211 = r55;
        goto L355
    L345:
        if (r145.megagroup == true) goto L347;
        r55 = LocaleController.formatPluralStringSpaced("Subscribers", r145.participants_count);
        goto L349
    L334:
        if (this.dialogsType == 16) goto L337;
        r20 = LocaleController.getString(R.string.SavedMessages);
        r211 = null;
        r212 = true;
        goto L339
    L336:
        r322 = r85;
    L321:
        e = move-exception;
        FileLog.e(e);
        r29 = r28;
    L327:
        r29 = r262;
        goto L324
    L279:
        if (r5.size() <= r21) goto L305;
        if (r103.startsWith("@") == false) goto L283;
        String r08 = r103.substring(r21);
    L284:
        int r115 = r5.size();
        int r157 = 0;
        String r86 = r84;
    L285:
        if (r157 >= r115) goto L292;
        TLRPC.TL_username r272 = r5.get(r157);
        r157 = r157 + 1;
        TLRPC.TL_username r214 = r272;
        r27 = r86;
        if (r214.active == false) goto L288;
        if (r214.username.startsWith(r08) == false) goto L288;
        String r215 = r214.username;
    L293:
        if (r215 != null) goto L302;
        int r87 = r5.size();
        int r116 = 0;
    L295:
        if (r116 >= r87) goto L302;
        TLRPC.TL_username r158 = r5.get(r116);
        r116 = r116 + 1;
        TLRPC.TL_username r159 = r158;
        String r282 = r215;
        if (r159.active == false) goto L298;
        if (r159.username.contains(r08) == false) goto L298;
        r28 = r159.username;
    L303:
        if (r28 == null) goto L306;
    L298:
        r215 = r282;
    L302:
        r28 = r215;
    L288:
        r86 = r27;
        goto L285
    L292:
        r27 = r86;
        r215 = null;
        goto L293
    L283:
        r08 = r103;
    L275:
        r262 = r26;
        r84 = r82;
        goto L277
    L276:
        r252 = r05;
        goto L275
    L265:
        if (r145 != null) goto L267;
        r114 = null;
        goto L271
    L267:
        if (r145.monoforum == false) goto L269;
        r114 = ForumUtilities.getMonoForumTitle(this.currentAccount, r145);
        goto L271
    L269:
        r114 = r145.title;
        goto L271
    L260:
        r103 = this.searchAdapterHelper.getLastFoundUsername();
    L328:
        r210 = r26;
        r85 = r82;
        goto L329
    L246:
        if (r2 >= this.searchResult.size()) goto L256;
        if (r47 != 0) goto L256;
        r26 = this.searchResultNames.get(r2);
        String r88 = UserObject.getPublicUsername(r47);
        if (r26 == null) goto L255;
        if (r47 == 0) goto L255;
        if (r88 == null) goto L255;
        if (r26.toString().startsWith("@".concat(r88)) == true) goto L254;
    L255:
        r82 = r26;
        r26 = null;
        goto L257
    L230:
        if (this.sponsoredPeers.isEmpty() == false) goto L232;
        r75 = 0;
        goto L233
    L219:
        if ((r112.get(r8 - 1) instanceof String) == false) goto L221;
        r74 = r8 - 2;
        goto L222
    L215:
        if (this.phoneCollapsed == false) goto L217;
        r8 = 3;
        goto L217
    L206:
        if (getRecentItemsCount() <= 0) goto L208;
    L211:
        r2 = r2 - 1;
        goto L212
    L208:
        if (this.searchTopics.isEmpty() == false) goto L211;
        if (this.publicPosts.isEmpty() == true) goto L212;
    L191:
        if (r2 < getRecentItemsCount()) goto L193;
        int r67 = 0;
    L198:
        r2 = r2 - getRecentItemsCount();
        r66 = r67;
        goto L201
    L193:
        if (r2 == (getRecentItemsCount() - r153)) goto L195;
        ?? r68 = r153;
    L196:
        r37.useSeparator = r68;
        r67 = r153;
        goto L198
    L195:
        r68 = 0;
        goto L196
    L162:
        r145 = null;
        TLRPC.User r48 = r44;
    L163:
        r152 = null;
        r172 = null;
        r45 = r48;
        goto L168
    L164:
        TLRPC.Chat r49 = MessagesController.getInstance(r43).getChat(Long.valueOf(-r143));
        if (r49 == null) goto L167;
        ArrayList<TLRPC.TL_username> r146 = r49.usernames;
        r172 = DialogObject.getPublicUsername(r49, this.currentMessagesQuery);
        r152 = r146;
        r145 = r49;
        r45 = null;
        goto L168
    L167:
        r145 = r49;
        r48 = null;
        goto L163
    L170:
        r18 = 2;
        if ((r65 instanceof TLRPC.User) == false) goto L174;
        TLRPC.User r410 = (TLRPC.User) r65;
        ArrayList<TLRPC.TL_username> r1510 = r410.usernames;
        r172 = DialogObject.getPublicUsername(r410, this.currentMessagesQuery);
        r312 = r65;
        r5 = r1510;
        r145 = null;
        r46 = r410;
        goto L169
    L174:
        if ((r65 instanceof TLRPC.Chat) == false) goto L181;
        r145 = (TLRPC.Chat) r65;
        r153 = 1;
        TLRPC.Chat r411 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r145.id));
        if (r411 == null) goto L179;
        r145 = r411;
    L179:
        ArrayList<TLRPC.TL_username> r412 = r145.usernames;
        r172 = DialogObject.getPublicUsername(r145, this.currentMessagesQuery);
        r5 = r412;
        r312 = r65;
        boolean r413 = false;
    L180:
        r19 = null;
        r47 = r413;
        goto L186
    L181:
        r153 = 1;
        if ((r65 instanceof TLRPC.EncryptedChat) == false) goto L184;
        TLRPC.EncryptedChat r414 = MessagesController.getInstance(this.currentAccount).getEncryptedChat(Integer.valueOf(((TLRPC.EncryptedChat) r65).id));
        r312 = r65;
        r19 = r414;
        r47 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r414.user_id));
        r5 = null;
        r145 = null;
        r172 = null;
        goto L186
    L184:
        r312 = r65;
        r413 = false;
        r5 = null;
        r145 = null;
        r172 = null;
        goto L180
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r12, int r13) {
        boolean r0 = true;
        switch(r13) {
            case 0: goto L17;
            case 1: goto L16;
            case 2: goto L15;
            case 3: goto L14;
            case 4: goto L13;
            case 5: goto L12;
            case 6: goto L7;
            case 7: goto L4;
            case 8: goto L6;
            case 9: goto L15;
            case 10: goto L5;
            default: goto L4;
        };
    L4:
        View r122 = new TextCell(this.mContext, 16, false);
    L23:
        if (r13 != 5) goto L25;
        r122.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(86.0f)));
    L27:
        return new RecyclerListView.Holder(r122);
    L25:
        r122.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        goto L27
    L5:
        EmptyLayout r123 = new EmptyLayout(this.mContext, this.resourcesProvider, new DialogsSearchAdapter$$ExternalSyntheticLambda7(this));
        this.messagesEmptyLayout = r123;
        r123.setQuery(this.lastMessagesSearchString);
        r122 = r123;
        goto L23
    L6:
        r122 = new ProfileSearchCell(this.mContext);
        goto L23
    L7:
        AnonymousClass4 r2 = new AnonymousClass4(this, this.mContext);
        r2.setSelectorDrawableColor(Theme.getColor(Theme.key_listSelector));
        r2.setTag(9);
        r2.setItemAnimator(null);
        r2.setLayoutAnimation(null);
        AnonymousClass5 r3 = new AnonymousClass5(this, this.mContext);
        r3.setOrientation(0);
        r2.setLayoutManager(r3);
        Context r6 = this.mContext;
        int r7 = this.currentAccount;
        if (this.dialogsType != 3) goto L10;
        boolean r9 = true;
    L11:
        r2.setAdapter(new CategoryAdapterRecycler(r6, r7, false, r9, this.resourcesProvider));
        r2.setOnItemClickListener(new DialogsSearchAdapter$$ExternalSyntheticLambda5(this));
        r2.setOnItemLongClickListener(new DialogsSearchAdapter$$ExternalSyntheticLambda6(this));
        this.innerListView = r2;
        r122 = r2;
        goto L23
    L10:
        r9 = false;
        goto L11
    L12:
        r122 = new HashtagSearchCell(this.mContext);
        goto L23
    L13:
        FlickerLoadingView r124 = new FlickerLoadingView(this.mContext);
        r124.setViewType(1);
        r124.setIsSingleCell(true);
        r122 = r124;
        goto L23
    L14:
        r122 = new TopicSearchCell(this.mContext);
        goto L23
    L15:
        r122 = new AnonymousClass3(this, null, this.mContext, false, true);
        goto L23
    L16:
        r122 = new GraySectionCell(this.mContext);
        goto L23
    L17:
        ProfileSearchCell r22 = new ProfileSearchCell(this.mContext);
        if (this.dialogsType == 3) goto L21;
        r0 = false;
    L21:
        r122 = r22.showPremiumBlock(r0);
        goto L23
    }

    public abstract void openBotApp(TLRPC.User r1);

    public abstract void openPublicPosts();

    public abstract void openSponsoredOptions(ProfileSearchCell r1, TLRPC.TL_sponsoredPeer r2);

    public void putRecentSearch(long r6, TLObject r8) {
        RecentSearchObject r0 = this.recentSearchObjectsById.get(r6);
        if (r0 != null) goto L5;
        r0 = new RecentSearchObject();
        this.recentSearchObjectsById.put(r6, r0);
    L6:
        this.recentSearchObjects.add(0, r0);
        r0.did = r6;
        r0.object = r8;
        r0.date = (int) (System.currentTimeMillis() / 1000);
        String r82 = this.lastSearchText;
        if (r82 == null) goto L9;
        String r83 = r82.trim();
    L10:
        filterRecent(r83);
        notifyDataSetChanged();
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new DialogsSearchAdapter$$ExternalSyntheticLambda3(this, r6));
        return;
    L9:
        r83 = null;
        goto L10
    L5:
        this.recentSearchObjects.remove(r0);
        goto L6
    }

    public void removeAd(TLRPC.TL_sponsoredPeer r4) {
        if (this.sponsoredPeers.isEmpty() == true) goto L19;
        int r42 = this.sponsoredPeers.indexOf(r4);
        if (r42 < 0) goto L20;
        int r0 = globalSearchPosition();
        if (r0 >= getItemCount()) goto L21;
        this.sponsoredPeers.remove(r42);
        notifyItemRemoved((r0 + 1) + r42);
        int r43 = this.searchAdapterHelper.getGlobalSearch().size();
        int r1 = this.sponsoredPeers.size();
        if (this.globalSearchCollapsed == false) goto L15;
        r43 = Math.min(3, r43);
    L15:
        if ((r1 + r43) > 0) goto L18;
        notifyItemRemoved(r0);
        return;
    L18:
        return;
    L21:
        return;
    L20:
        return;
    }

    public void removeAllAds() {
        if (this.sponsoredPeers.isEmpty() == true) goto L15;
        int r0 = globalSearchPosition();
        if (r0 >= getItemCount()) goto L16;
        int r1 = this.sponsoredPeers.size();
        this.sponsoredPeers.clear();
        notifyItemRangeRemoved(r0 + 1, r1);
        int r12 = this.searchAdapterHelper.getGlobalSearch().size();
        if (this.globalSearchCollapsed == false) goto L11;
        r12 = Math.min(3, r12);
    L11:
        if (r12 > 0) goto L14;
        notifyItemRemoved(r0);
        return;
    L14:
        return;
    L16:
        return;
    }

    public void removeRecentSearch(long r3) {
        RecentSearchObject r0 = this.recentSearchObjectsById.get(r3);
        if (r0 != null) goto L5;
        return;
    L5:
        this.recentSearchObjectsById.remove(r3);
        this.recentSearchObjects.remove(r0);
        this.filtered2RecentSearchObjects.remove(r0);
        this.filteredRecentSearchObjects.remove(r0);
        notifyDataSetChanged();
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new DialogsSearchAdapter$$ExternalSyntheticLambda22(this, r3));
    }

    public void resetFilter() {
        this.currentMessagesFilter = Filter.All;
    }

    public void searchDialogs(String r23, int r24, boolean r25) {
        if (r23 != null) goto L5;
    L11:
        this.lastSearchText = r23;
        this.folderId = r24;
        String r3 = null;
        if (this.searchRunnable == null) goto L14;
        Utilities.searchQueue.cancelRunnable(this.searchRunnable);
        this.searchRunnable = null;
    L14:
        Runnable r2 = this.searchRunnable2;
        if (r2 == null) goto L17;
        AndroidUtilities.cancelRunOnUIThread(r2);
        this.searchRunnable2 = null;
    L17:
        Runnable r22 = this.searchHashtagRunnable;
        if (r22 == null) goto L21;
        AndroidUtilities.cancelRunOnUIThread(r22);
        this.searchHashtagRunnable = null;
    L21:
        if (this.searchHashtagRequest < 0) goto L23;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.searchHashtagRequest, true);
        this.searchHashtagRequest = -1;
    L23:
        if (r23 == null) goto L25;
        String r26 = r23.trim();
    L26:
        filterRecent(r26);
        if (TextUtils.equals(this.sponsoredQuery, r26) == true) goto L42;
        this.sponsoredQuery = r26;
        this.sponsoredPeers.clear();
        if (this.sponsoredReqId == 0) goto L31;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.sponsoredReqId, true);
        this.sponsoredReqId = 0;
    L31:
        if (r26 != null) goto L33;
    L40:
        this.sponsoredQuery = null;
        goto L42
    L33:
        if (r26.length() < 4) goto L40;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L37;
    L39:
        TLRPC.TL_contacts_getSponsoredPeers r5 = new TLRPC.TL_contacts_getSponsoredPeers();
        this.sponsoredQuery = r26;
        r5.q = r26;
        this.sponsoredReqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r5, new DialogsSearchAdapter$$ExternalSyntheticLambda8(this));
        goto L42
    L37:
        if (MessagesController.getInstance(this.currentAccount).isSponsoredDisabled() == false) goto L39;
    L42:
        if (TextUtils.isEmpty(r26) == false) goto L76;
        this.filteredRecentQuery = null;
        this.searchAdapterHelper.unloadRecentHashtags();
        this.searchResult.clear();
        this.searchResultNames.clear();
        this.searchResultHashtags.clear();
        this.publicPostsTotalCount = 0;
        this.publicPostsLastRate = 0;
        this.publicPostsHashtag = null;
        this.publicPosts.clear();
        this.searchAdapterHelper.mergeResults(null, null);
        int r1 = this.dialogsType;
        if (r1 == 15) goto L66;
        SearchAdapterHelper r8 = this.searchAdapterHelper;
        if (r1 == 11) goto L48;
        boolean r12 = true;
    L49:
        if (r1 == 11) goto L51;
        boolean r13 = true;
    L52:
        if (r1 == 2) goto L56;
        if (r1 == 11) goto L56;
        boolean r14 = false;
    L57:
        if (r1 != 0) goto L59;
        boolean r17 = true;
    L60:
        DialogsSearchAdapterDelegate r15 = this.delegate;
        if (r15 == null) goto L64;
        long r9 = r15.getSearchForumDialogId();
    L65:
        r8.queryServerSearch(null, true, true, r12, r13, r14, 0, r17, 0, 0, r9);
        goto L66
    L64:
        r9 = 0;
        goto L65
    L59:
        r17 = false;
    L56:
        r14 = true;
        goto L57
    L51:
        r13 = false;
        goto L52
    L48:
        r12 = false;
    L66:
        this.searchWas = false;
        this.lastSearchId = 0;
        this.waitingResponseCount = 0;
        this.globalSearchCollapsed = true;
        this.phoneCollapsed = true;
        DialogsSearchAdapterDelegate r16 = this.delegate;
        if (r16 == null) goto L70;
        r16.searchStateChanged(false, true);
    L70:
        if (this.dialogsType == 15) goto L72;
        searchTopics(null);
        searchMessagesInternal(null, 0);
        searchForumMessagesInternal(null, 0);
    L72:
        notifyDataSetChanged();
        this.localTipDates.clear();
        this.localTipArchive = false;
        FilteredSearchView.Delegate r18 = this.filtersDelegate;
        if (r18 == null) goto L113;
        r18.updateFiltersView(false, null, this.localTipDates, false);
        return;
    L113:
        return;
    L76:
        this.searchAdapterHelper.mergeResults(this.searchResult, this.filtered2RecentSearchObjects);
        this.publicPostsTotalCount = 0;
        this.publicPostsLastRate = 0;
        this.publicPostsHashtag = null;
        this.publicPosts.clear();
        if (this.needMessagesSearch != 2) goto L79;
    L91:
        this.searchResultHashtags.clear();
    L92:
        int r52 = this.lastSearchId + 1;
        this.lastSearchId = r52;
        this.waitingResponseCount = 3;
        this.globalSearchCollapsed = true;
        this.phoneCollapsed = true;
        notifyDataSetChanged();
        DialogsSearchAdapterDelegate r7 = this.delegate;
        if (r7 == null) goto L95;
        r7.searchStateChanged(true, false);
    L95:
        if (r25 == false) goto L107;
        if (r26 == null) goto L107;
        String r72 = r26.trim();
        if (r72.length() <= 1) goto L107;
        if (r72.charAt(0) != '#') goto L102;
    L103:
        int r32 = r72.indexOf(64);
        String r6 = r72.substring(1);
        if (r32 < 0) goto L106;
        r72.substring(r32 + 1);
    L106:
        r3 = r6;
        goto L107
    L102:
        if (r72.charAt(0) == '$') goto L103;
    L107:
        DispatchQueue r62 = Utilities.searchQueue;
        DialogsSearchAdapter$$ExternalSyntheticLambda9 r73 = new DialogsSearchAdapter$$ExternalSyntheticLambda9(this, r26, r52, r23);
        this.searchRunnable = r73;
        r62.postRunnable(r73, 300);
        if (r3 == null) goto L114;
        this.waitingResponseCount++;
        DialogsSearchAdapter$$ExternalSyntheticLambda10 r4 = new DialogsSearchAdapter$$ExternalSyntheticLambda10(this, r52, r3);
        this.searchHashtagRunnable = r4;
        AndroidUtilities.runOnUIThread(r4, 300);
        return;
    L114:
        return;
    L79:
        if (r26.startsWith("#") == false) goto L91;
        if (r26.length() != 1) goto L91;
        this.messagesSearchEndReached = true;
        if (this.searchAdapterHelper.loadRecentHashtags() == false) goto L92;
        this.searchResultMessages.clear();
        this.searchResultHashtags.clear();
        ArrayList<SearchAdapterHelper.HashtagObject> r53 = this.searchAdapterHelper.getHashtags();
        int r74 = 0;
    L86:
        if (r74 >= r53.size()) goto L88;
        this.searchResultHashtags.add(r53.get(r74).hashtag);
        r74 = r74 + 1;
        goto L86
    L88:
        this.globalSearchCollapsed = true;
        this.phoneCollapsed = true;
        this.waitingResponseCount = 0;
        notifyDataSetChanged();
        DialogsSearchAdapterDelegate r54 = this.delegate;
        if (r54 == null) goto L92;
        r54.searchStateChanged(false, false);
        goto L92
    L25:
        r26 = null;
        goto L26
    L5:
        if (r23.equals(this.lastSearchText) == false) goto L11;
        if (r24 != this.folderId) goto L9;
        return;
    L9:
        if (TextUtils.isEmpty(r23) == false) goto L11;
    }

    public void seenSponsoredPeer(TLRPC.TL_sponsoredPeer r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        Iterator<byte[]> r0 = this.seenSponsoredPeers.iterator();
    L6:
        if (r0.hasNext() == false) goto L10;
        if (Arrays.equals(r0.next(), r4.random_id) == false) goto L6;
        return;
    L10:
        this.seenSponsoredPeers.add(r4.random_id);
        TLRPC.TL_messages_viewSponsoredMessage r02 = new TLRPC.TL_messages_viewSponsoredMessage();
        r02.random_id = r4.random_id;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, null);
    }

    public void setDelegate(DialogsSearchAdapterDelegate r1) {
        this.delegate = r1;
    }

    public void setFilterDialogIds(ArrayList<Long> r1) {
        this.filterDialogIds = r1;
    }

    public void setFiltersDelegate(FilteredSearchView.Delegate r3, boolean r4) {
        this.filtersDelegate = r3;
        if (r3 == null) goto L7;
        if (r4 == false) goto L8;
        r3.updateFiltersView(false, null, this.localTipDates, this.localTipArchive);
        return;
    L8:
        return;
    }
}
