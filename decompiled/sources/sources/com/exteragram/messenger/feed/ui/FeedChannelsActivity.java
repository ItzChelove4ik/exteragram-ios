package com.exteragram.messenger.feed.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.feed.FeedChannelActions;
import com.exteragram.messenger.feed.FeedConfig;
import com.exteragram.messenger.feed.FeedController;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u0000 42\u00020\u00012\u00020\u0002:\u000234B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J5\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0016\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00180\u0017\"\u0004\u0018\u00010\u0018H\u0016¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0014J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\rH\u0016J\b\u0010 \u001a\u00020\u0011H\u0002J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0002J\u001e\u0010$\u001a\u00020\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\b2\u0006\u0010'\u001a\u00020(H\u0014J0\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u00142\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0014J0\u00100\u001a\u00020\r2\u0006\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u00142\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0014J\u0010\u00101\u001a\u00020\u00112\u0006\u00102\u001a\u00020\rH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "<init>", "()V", "otherItem", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;", "channels", "Ljava/util/ArrayList;", "Lorg/telegram/tgnet/TLRPC$Chat;", "query", _UrlKt.FRAGMENT_ENCODE_SET, "searching", _UrlKt.FRAGMENT_ENCODE_SET, "getTitle", "onFragmentCreate", "onFragmentDestroy", _UrlKt.FRAGMENT_ENCODE_SET, "didReceivedNotification", "id", _UrlKt.FRAGMENT_ENCODE_SET, "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "onBackPressed", "invoked", "reloadChannels", "removeChannel", "dialogId", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", PluginsConstants.Settings.ITEMS, "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onLongClick", "setAllExcluded", "excluded", "SettingItem", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFeedChannelsActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FeedChannelsActivity.kt\ncom/exteragram/messenger/feed/ui/FeedChannelsActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,248:1\n1915#2,2:249\n296#2,2:251\n1586#2:253\n1661#2,3:254\n1915#2,2:257\n*S KotlinDebug\n*F\n+ 1 FeedChannelsActivity.kt\ncom/exteragram/messenger/feed/ui/FeedChannelsActivity\n*L\n168#1:249,2\n202#1:251,2\n242#1:253\n242#1:254,3\n139#1:257,2\n*E\n"})
/* loaded from: classes4.dex */
public final class FeedChannelsActivity extends BasePreferencesActivity implements NotificationCenter.NotificationCenterDelegate {
    private static final Comparator<TLRPC.Chat> BY_TITLE = null;
    public static final Companion Companion = null;
    private final ArrayList<TLRPC.Chat> channels;
    private ActionBarMenuItem otherItem;
    private String query;
    private boolean searching;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MENU_SELECT_ALL", _UrlKt.FRAGMENT_ENCODE_SET, "MENU_DESELECT_ALL", "MENU_OTHER", "BY_TITLE", "Ljava/util/Comparator;", "Lorg/telegram/tgnet/TLRPC$Chat;", "Lkotlin/Comparator;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\f"}, d2 = {"Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "FEED_TAB", "WIDE_POSTS", "UNREAD_COUNTER", "ARCHIVE", "id", _UrlKt.FRAGMENT_ENCODE_SET, "getId", "()I", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum SettingItem extends Enum<SettingItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ SettingItem[] $VALUES = null;
        public static final SettingItem ARCHIVE = null;
        public static final SettingItem FEED_TAB = null;
        public static final SettingItem UNREAD_COUNTER = null;
        public static final SettingItem WIDE_POSTS = null;
        private final int id;

        private static final /* synthetic */ SettingItem[] $values() {
            return new SettingItem[]{FEED_TAB, WIDE_POSTS, UNREAD_COUNTER, ARCHIVE};
        }

        static {
            FEED_TAB = new SettingItem("FEED_TAB", 0);
            WIDE_POSTS = new SettingItem("WIDE_POSTS", 1);
            UNREAD_COUNTER = new SettingItem("UNREAD_COUNTER", 2);
            ARCHIVE = new SettingItem("ARCHIVE", 3);
            SettingItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        SettingItem(String r1, int r2) {
            this.id = ordinal() + 1000;
        }

        public static EnumEntries<SettingItem> getEntries() {
            return $ENTRIES;
        }

        public static SettingItem valueOf(String r1) {
            return (SettingItem) Enum.valueOf(SettingItem.class, r1);
        }

        public static SettingItem[] values() {
            return (SettingItem[]) $VALUES.clone();
        }

        public final int getId() {
            return this.id;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[SettingItem.values().length];
            r0[SettingItem.FEED_TAB.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L13:
            r0[SettingItem.WIDE_POSTS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L19:
            r0[SettingItem.UNREAD_COUNTER.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L15:
            r0[SettingItem.ARCHIVE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L7:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$1", "Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;", "onItemClick", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.feed.ui.FeedChannelsActivity$createView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ FeedChannelsActivity this$0;

        public AnonymousClass1(FeedChannelsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 != (-1)) goto L5;
            this.this$0.finishFragment();
            return;
        L5:
            if (r3 != 1) goto L7;
            FeedChannelsActivity.access$setAllExcluded(this.this$0, false);
            return;
        L7:
            if (r3 == 2) goto L9;
            return;
        L9:
            FeedChannelsActivity.access$setAllExcluded(this.this$0, true);
        }
    }

    public static int $r8$lambda$6HdJlreNY2feI1YiMjmGZIb4Kyo(TLRPC.Chat r2, TLRPC.Chat r3) {
        String r22 = r2.title;
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r22 == null) goto L6;
        String r23 = r22.toLowerCase(Locale.ROOT);
        if (r23 == null) goto L6;
    L7:
        String r32 = r3.title;
        if (r32 == null) goto L14;
        String r33 = r32.toLowerCase(Locale.ROOT);
        if (r33 == null) goto L14;
        r0 = r33;
    L14:
        return r23.compareTo(r0);
    L6:
        r23 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }

    public static void $r8$lambda$6kPVIVrSCN3A66Wp2WuN7QKUh7A(FeedChannelsActivity r4, ArrayList r5, int r6, boolean r7, int r8) {
        if (r7 == true) goto L19;
        r4.channels.clear();
        int r62 = r5.size();
        int r72 = 0;
    L5:
        if (r72 >= r62) goto L11;
        Object r82 = r5.get(r72);
        r72 = r72 + 1;
        TLRPC.Chat r83 = (TLRPC.Chat) r82;
        ArrayList<TLRPC.Chat> r0 = r4.channels;
        TLRPC.Chat r1 = r4.getMessagesController().getChat(Long.valueOf(r83.id));
        if (r1 == null) goto L10;
        r83 = r1;
    L10:
        r0.add(r83);
        goto L5
    L11:
        CollectionsKt.sortWith(r4.channels, BY_TITLE);
        UniversalAdapter r42 = r4.getListView$TMessagesProj().adapter;
        if (r42 == null) goto L18;
        r42.update(true);
        return;
    L18:
        return;
    }

    /* renamed from: $r8$lambda$NdNT-swXl0Yh3ztKlwrNCKK4iEQ, reason: not valid java name */
    public static boolean m1436$r8$lambda$NdNTswXl0Yh3ztKlwrNCKK4iEQ(Function1 r0, Object r1) {
        return ((Boolean) r0.invoke(r1)).booleanValue();
    }

    /* renamed from: $r8$lambda$VBScUFb7q-oSQN5JSln8k3N_2bU, reason: not valid java name */
    public static void m1437$r8$lambda$VBScUFb7qoSQN5JSln8k3N_2bU(boolean r0) {
        ExteraConfig.setShowFeedUnreadCounter(r0);
    }

    public static void $r8$lambda$YDcLpKPTQq9Gdi46k6ND7yYe9Mk(boolean r0) {
        ExteraConfig.setWidePostsInFeed(r0);
    }

    public static void $r8$lambda$_CTgy8HhNXd_piH3E4vnMV36dbk(boolean r0) {
        ExteraConfig.setShowFeedTab(r0);
    }

    public static void $r8$lambda$bIMigA7ivjRS4Yf3e5MWMwf2RLA(FeedChannelsActivity r2, TLRPC.Chat r3) {
        r2.presentFragment(ChatActivity.of(-r3.id));
    }

    public static void $r8$lambda$cU8Q7fPs8sCCUAsN5hMBc6EMwcM(FeedChannelsActivity r1, TLRPC.Chat r2) {
        FeedChannelActions.leaveChannel(r1, r2, null, null);
    }

    public static void $r8$lambda$jwCrvnnUGaVtUIzGRQQdgmPoqhM(FeedChannelsActivity r2, TLRPC.Chat r3, boolean r4) {
        FeedConfig.Companion.getInstance(r2.currentAccount).setExcluded(-r3.id, !r4);
    }

    /* renamed from: $r8$lambda$xHdbIe-FkeUuD-NLCgdyhjVEi10, reason: not valid java name */
    public static boolean m1438$r8$lambda$xHdbIeFkeUuDNLCgdyhjVEi10(long r2, TLRPC.Chat r4) {
        if ((-r4.id) != r2) goto L6;
        return true;
    L6:
        return false;
    }

    static {
        Companion = new Companion(null);
        BY_TITLE = new FeedChannelsActivity$$ExternalSyntheticLambda0();
    }

    public FeedChannelsActivity() {
        this.channels = new ArrayList();
    }

    public static final /* synthetic */ ActionBarMenuItem access$getOtherItem$p(FeedChannelsActivity r0) {
        return r0.otherItem;
    }

    public static final /* synthetic */ void access$setAllExcluded(FeedChannelsActivity r0, boolean r1) {
        r0.setAllExcluded(r1);
    }

    public static final /* synthetic */ void access$setQuery$p(FeedChannelsActivity r0, String r1) {
        r0.query = r1;
    }

    public static final /* synthetic */ void access$setSearching$p(FeedChannelsActivity r0, boolean r1) {
        r0.searching = r1;
    }

    private final void reloadChannels() {
        FeedController.getInstance(this.currentAccount).loadChannels(true, new FeedChannelsActivity$$ExternalSyntheticLambda1(this));
    }

    private final void removeChannel(long r3) {
        if (this.channels.removeIf(new FeedChannelsActivity$$ExternalSyntheticLambda5(new FeedChannelsActivity$$ExternalSyntheticLambda4(r3))) == false) goto L8;
        UniversalAdapter r2 = getListView$TMessagesProj().adapter;
        if (r2 == null) goto L9;
        r2.update(true);
        return;
    L9:
        return;
    }

    private final void setAllExcluded(boolean r7) {
        FeedConfig r0 = FeedConfig.Companion.getInstance(this.currentAccount);
        if (r7 == false) goto L8;
        ArrayList<TLRPC.Chat> r72 = this.channels;
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r72, 10));
        int r2 = r72.size();
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L7;
        TLRPC.Chat r4 = r72.get(r3);
        r3 = r3 + 1;
        r1.add(Long.valueOf(-r4.id));
        goto L5
    L7:
        r0.excludeAll(r1);
    L9:
        UniversalAdapter r6 = getListView$TMessagesProj().adapter;
        if (r6 == null) goto L14;
        r6.update(true);
        return;
    L14:
        return;
    L8:
        r0.clearExcluded();
        goto L9
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r5) {
        View r52 = super.createView(r5);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r0 = this.actionBar.createMenu();
        ActionBarMenuItem r1 = r0.addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new FeedChannelsActivity$createView$searchItem$1(this));
        r1.setSearchPaddingStart(7);
        r1.setSearchFieldHint(LocaleController.getString(R.string.Search));
        ActionBarMenuItem r02 = r0.addItem(3, R.drawable.ic_ab_other);
        r02.addSubItem(1, R.drawable.msg_select, LocaleController.getString(R.string.SelectAll));
        r02.addSubItem(2, R.drawable.msg_cancel, LocaleController.getString(R.string.DeselectAll));
        this.otherItem = r02;
        reloadChannels();
        return r52;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.feedNeedReload) goto L7;
        reloadChannels();
        return;
    L7:
        if (r1 != NotificationCenter.dialogDeleted) goto L10;
        removeChannel(((Long) r3[0]).longValue());
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r17, UniversalAdapter r18) {
        FeedChannelsActivity r0 = this;
        FeedConfig r13 = FeedConfig.Companion.getInstance(r0.currentAccount);
        String r14 = r0.query;
        boolean r15 = TextUtils.isEmpty(r14);
        if (r15 == false) goto L5;
        r17.add(UItem.asHeader(LocaleController.getString(R.string.General)));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(r0, SettingItem.FEED_TAB.getId(), LocaleController.getString(R.string.FeedBottomTab), ExteraConfig.getShowFeedTab(), LocaleController.getString(R.string.FeedBottomTabInfo), 0, false, "feedBottomTab", false, 0, 432, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, SettingItem.WIDE_POSTS.getId(), LocaleController.getString(R.string.WidePostsInFeed), ExteraConfig.getWidePostsInFeed(), null, 0, false, "feedWidePosts", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, SettingItem.UNREAD_COUNTER.getId(), LocaleController.getString(R.string.FeedUnreadCounter), ExteraConfig.getShowFeedUnreadCounter(), null, 0, false, "feedUnreadCounter", false, 0, 440, null));
        r0 = this;
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(r0, SettingItem.ARCHIVE.getId(), LocaleController.getString(R.string.FeedIncludeArchived), r13.isIncludeArchived(), null, 0, false, "feedIncludeArchived", false, 0, 440, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.FeedIncludeArchivedInfo)));
    L5:
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        ArrayList<TLRPC.Chat> r02 = r0.channels;
        int r3 = r02.size();
        int r5 = 0;
    L6:
        if (r5 >= r3) goto L20;
        TLRPC.Chat r6 = r02.get(r5);
        r5 = r5 + 1;
        TLRPC.Chat r62 = r6;
        if (r15 == true) goto L14;
        String r7 = r62.title;
        if (r7 == null) goto L6;
        if (StringsKt.contains$default(r7.toLowerCase(Locale.getDefault()), r14, false, 2, null) == false) goto L6;
    L14:
        boolean r72 = r13.isExcluded(-r62.id);
        UItem r63 = UItem.asUserCheckbox(Long.hashCode(r62.id), r62).setChecked(!r72);
        if (r72 == true) goto L17;
        ArrayList r73 = r1;
    L18:
        r73.add(r63);
        goto L6
    L17:
        r73 = r2;
        goto L18
    L20:
        if (r1.isEmpty() == true) goto L23;
        r17.add(UItem.asHeader(LocaleController.getString(R.string.FeedShownChannels)));
        r17.addAll(r1);
    L23:
        if (r2.isEmpty() == false) goto L25;
    L28:
        if (r15 == true) goto L30;
        return;
    L30:
        if (r1.isEmpty() == true) goto L32;
    L33:
        r17.add(UItem.asShadow(LocaleController.getString(R.string.FeedChannelsInfo)));
        return;
    L32:
        if (r2.isEmpty() == false) goto L33;
        return;
    L25:
        if (r1.isEmpty() == true) goto L27;
        r17.add(UItem.asShadow());
    L27:
        r17.add(UItem.asHeader(LocaleController.getString(R.string.FeedHiddenChannels)));
        r17.addAll(r2);
        goto L28
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.FeedSettings);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.searching == false) goto L9;
        if (r2 == false) goto L10;
        this.actionBar.closeSearchField();
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r2, View r3, int r4, float r5, float r6) {
        Object r32 = r2.object;
        SettingItem r52 = null;
        if ((r32 instanceof TLRPC.Chat) == false) goto L5;
        TLRPC.Chat r33 = (TLRPC.Chat) r32;
    L6:
        if (r33 == null) goto L9;
        toggleBooleanSettingAndRefresh$TMessagesProj(r2, new FeedChannelsActivity$$ExternalSyntheticLambda6(this, r33));
        return;
    L9:
        Iterator<SettingItem> r34 = SettingItem.getEntries().iterator();
    L11:
        if (r34.hasNext() == false) goto L15;
        SettingItem r42 = r34.next();
        if (r42.getId() != r2.id) goto L11;
        r52 = r42;
    L15:
        SettingItem r53 = r52;
        if (r53 != null) goto L18;
        return;
    L18:
        int r35 = WhenMappings.$EnumSwitchMapping$0[r53.ordinal()];
        if (r35 != 1) goto L21;
        toggleBooleanSettingAndRefresh$TMessagesProj(r2, new FeedChannelsActivity$$ExternalSyntheticLambda7());
        UniversalAdapter r22 = getListView$TMessagesProj().adapter;
        if (r22 == null) goto L37;
        r22.update(true);
    L37:
        getNotificationCenter().postNotificationName(NotificationCenter.feedTabVisibleToggled, new Object[0]);
        return;
    L21:
        if (r35 != 2) goto L23;
        toggleBooleanSettingAndRefresh$TMessagesProj(r2, new FeedChannelsActivity$$ExternalSyntheticLambda8());
        this.parentLayout.rebuildFragments(AndroidUtilities.isTablet() ? 1 : 0);
        return;
    L23:
        if (r35 != 3) goto L25;
        toggleBooleanSettingAndRefresh$TMessagesProj(r2, new FeedChannelsActivity$$ExternalSyntheticLambda9());
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{0});
        return;
    L25:
        if (r35 != 4) goto L28;
        FeedConfig.Companion.getInstance(this.currentAccount).setIncludeArchived(!r2.isIncludeArchived());
        reloadChannels();
        return;
    L28:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L5:
        r33 = null;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.feedNeedReload);
        getNotificationCenter().addObserver(this, NotificationCenter.dialogDeleted);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.feedNeedReload);
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogDeleted);
        super.onFragmentDestroy();
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean onLongClick(UItem r3, View r4, int r5, float r6, float r7) {
        Object r0 = r3.object;
        if ((r0 instanceof TLRPC.Chat) == false) goto L5;
        TLRPC.Chat r02 = (TLRPC.Chat) r0;
    L6:
        if (r02 == null) goto L8;
        ItemOptions r32 = ItemOptions.makeOptions(this, r4);
        r32.setLongPressSelectionEnabled(false);
        r32.setScrimViewBackground(getListView$TMessagesProj().getClipBackground(r4));
        r32.add(R.drawable.msg_channel, LocaleController.getString(R.string.OpenChannel2), new FeedChannelsActivity$$ExternalSyntheticLambda2(this, r02));
        r32.addIf(FeedChannelActions.canLeave(r02), R.drawable.msg_leave, LocaleController.getString(R.string.LeaveChannelMenu), true, new FeedChannelsActivity$$ExternalSyntheticLambda3(this, r02));
        r32.show();
        return true;
    L8:
        return super.onLongClick(r3, r4, r5, r6, r7);
    L5:
        r02 = null;
        goto L6
    }
}
