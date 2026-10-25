.class public final Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$Companion;,
        Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;,
        Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0003\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\t\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\u0007\n\u0002\u0008\u0007\u0018\u0000 42\u00020\u00012\u00020\u0002:\u000234B\u0007\u00a2\u0006\u0004\u0008\u0003\u0010\u0004J\u0008\u0010\u000e\u001a\u00020\u000bH\u0016J\u0008\u0010\u000f\u001a\u00020\rH\u0016J\u0008\u0010\u0010\u001a\u00020\u0011H\u0016J5\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0016\u0010\u0016\u001a\u000c\u0012\u0008\u0008\u0001\u0012\u0004\u0018\u00010\u00180\u0017\"\u0004\u0018\u00010\u0018H\u0016\u00a2\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0014J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\rH\u0016J\u0008\u0010 \u001a\u00020\u0011H\u0002J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0002J\u001e\u0010$\u001a\u00020\u00112\u000c\u0010%\u001a\u0008\u0012\u0004\u0012\u00020&0\u00082\u0006\u0010\'\u001a\u00020(H\u0014J0\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u00142\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0014J0\u00100\u001a\u00020\r2\u0006\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u00142\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0014J\u0010\u00101\u001a\u00020\u00112\u0006\u00102\u001a\u00020\rH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0008\u0012\u0004\u0012\u00020\t0\u0008X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000c\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00065"
    }
    d2 = {
        "Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;",
        "<init>",
        "()V",
        "otherItem",
        "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;",
        "channels",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/tgnet/TLRPC$Chat;",
        "query",
        "",
        "searching",
        "",
        "getTitle",
        "onFragmentCreate",
        "onFragmentDestroy",
        "",
        "didReceivedNotification",
        "id",
        "",
        "account",
        "args",
        "",
        "",
        "(II[Ljava/lang/Object;)V",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "onBackPressed",
        "invoked",
        "reloadChannels",
        "removeChannel",
        "dialogId",
        "",
        "fillItems",
        "items",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "position",
        "x",
        "",
        "y",
        "onLongClick",
        "setAllExcluded",
        "excluded",
        "SettingItem",
        "Companion",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nFeedChannelsActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FeedChannelsActivity.kt\ncom/exteragram/messenger/feed/ui/FeedChannelsActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,248:1\n1915#2,2:249\n296#2,2:251\n1586#2:253\n1661#2,3:254\n1915#2,2:257\n*S KotlinDebug\n*F\n+ 1 FeedChannelsActivity.kt\ncom/exteragram/messenger/feed/ui/FeedChannelsActivity\n*L\n168#1:249,2\n202#1:251,2\n242#1:253\n242#1:254,3\n139#1:257,2\n*E\n"
    }
.end annotation


# static fields
.field private static final BY_TITLE:Ljava/util/Comparator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Comparator<",
            "Lorg/telegram/tgnet/TLRPC$Chat;",
            ">;"
        }
    .end annotation
.end field

.field public static final Companion:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$Companion;


# instance fields
.field private final channels:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/tgnet/TLRPC$Chat;",
            ">;"
        }
    .end annotation
.end field

.field private otherItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

.field private query:Ljava/lang/String;

.field private searching:Z


# direct methods
.method public static $r8$lambda$6HdJlreNY2feI1YiMjmGZIb4Kyo(Lorg/telegram/tgnet/TLRPC$Chat;Lorg/telegram/tgnet/TLRPC$Chat;)I
    .locals 2

    .line 46
    iget-object p0, p0, Lorg/telegram/tgnet/TLRPC$Chat;->title:Ljava/lang/String;

    const-string v0, ""

    if-eqz p0, :cond_0

    sget-object v1, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p0, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_1

    :cond_0
    move-object p0, v0

    .line 47
    :cond_1
    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$Chat;->title:Ljava/lang/String;

    if-eqz p1, :cond_3

    sget-object v1, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p1, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_2

    goto :goto_0

    :cond_2
    move-object v0, p1

    .line 48
    :cond_3
    :goto_0
    invoke-virtual {p0, v0}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result p0

    return p0
.end method

.method public static $r8$lambda$6kPVIVrSCN3A66Wp2WuN7QKUh7A(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Ljava/util/ArrayList;IZI)V
    .locals 4

    if-eqz p3, :cond_0

    goto :goto_2

    .line 138
    :cond_0
    iget-object p2, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->channels:Ljava/util/ArrayList;

    invoke-virtual {p2}, Ljava/util/ArrayList;->clear()V

    .line 1915
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p2

    const/4 p3, 0x0

    :goto_0
    if-ge p3, p2, :cond_2

    invoke-virtual {p1, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p4

    add-int/lit8 p3, p3, 0x1

    check-cast p4, Lorg/telegram/tgnet/TLRPC$Chat;

    .line 140
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->channels:Ljava/util/ArrayList;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    iget-wide v2, p4, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/MessagesController;->getChat(Ljava/lang/Long;)Lorg/telegram/tgnet/TLRPC$Chat;

    move-result-object v1

    if-nez v1, :cond_1

    goto :goto_1

    :cond_1
    move-object p4, v1

    :goto_1
    invoke-virtual {v0, p4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 142
    :cond_2
    iget-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->channels:Ljava/util/ArrayList;

    sget-object p2, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->BY_TITLE:Ljava/util/Comparator;

    invoke-static {p1, p2}, Lkotlin/collections/CollectionsKt;->sortWith(Ljava/util/List;Ljava/util/Comparator;)V

    .line 143
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_3

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_3
    :goto_2
    return-void
.end method

.method public static $r8$lambda$NdNT-swXl0Yh3ztKlwrNCKK4iEQ(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Z
    .locals 0

    .line 148
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Boolean;

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$VBScUFb7q-oSQN5JSln8k3N_2bU(Z)V
    .locals 0

    .line 214
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setShowFeedUnreadCounter(Z)V

    return-void
.end method

.method public static $r8$lambda$YDcLpKPTQq9Gdi46k6ND7yYe9Mk(Z)V
    .locals 0

    .line 210
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setWidePostsInFeed(Z)V

    return-void
.end method

.method public static $r8$lambda$_CTgy8HhNXd_piH3E4vnMV36dbk(Z)V
    .locals 0

    .line 205
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setShowFeedTab(Z)V

    return-void
.end method

.method public static $r8$lambda$bIMigA7ivjRS4Yf3e5MWMwf2RLA(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Lorg/telegram/tgnet/TLRPC$Chat;)V
    .locals 2

    .line 230
    iget-wide v0, p1, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    neg-long v0, v0

    invoke-static {v0, v1}, Lorg/telegram/ui/ChatActivity;->of(J)Lorg/telegram/ui/ChatActivity;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void
.end method

.method public static $r8$lambda$cU8Q7fPs8sCCUAsN5hMBc6EMwcM(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Lorg/telegram/tgnet/TLRPC$Chat;)V
    .locals 1

    const/4 v0, 0x0

    .line 232
    invoke-static {p0, p1, v0, v0}, Lcom/exteragram/messenger/feed/FeedChannelActions;->leaveChannel(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/tgnet/TLRPC$Chat;Ljava/lang/Runnable;Ljava/util/function/Consumer;)V

    return-void
.end method

.method public static $r8$lambda$jwCrvnnUGaVtUIzGRQQdgmPoqhM(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Lorg/telegram/tgnet/TLRPC$Chat;Z)V
    .locals 2

    .line 197
    sget-object v0, Lcom/exteragram/messenger/feed/FeedConfig;->Companion:Lcom/exteragram/messenger/feed/FeedConfig$Companion;

    iget p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/feed/FeedConfig$Companion;->getInstance(I)Lcom/exteragram/messenger/feed/FeedConfig;

    move-result-object p0

    iget-wide v0, p1, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    neg-long v0, v0

    xor-int/lit8 p1, p2, 0x1

    invoke-virtual {p0, v0, v1, p1}, Lcom/exteragram/messenger/feed/FeedConfig;->setExcluded(JZ)V

    return-void
.end method

.method public static $r8$lambda$xHdbIe-FkeUuD-NLCgdyhjVEi10(JLorg/telegram/tgnet/TLRPC$Chat;)Z
    .locals 2

    .line 148
    iget-wide v0, p2, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    neg-long v0, v0

    cmp-long p0, v0, p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->Companion:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$Companion;

    .line 45
    new-instance v0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda0;

    invoke-direct {v0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda0;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->BY_TITLE:Ljava/util/Comparator;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 29
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    .line 53
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->channels:Ljava/util/ArrayList;

    return-void
.end method

.method public static final synthetic access$getOtherItem$p(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;
    .locals 0

    .line 29
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->otherItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    return-object p0
.end method

.method public static final synthetic access$setAllExcluded(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Z)V
    .locals 0

    .line 29
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->setAllExcluded(Z)V

    return-void
.end method

.method public static final synthetic access$setQuery$p(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Ljava/lang/String;)V
    .locals 0

    .line 29
    iput-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->query:Ljava/lang/String;

    return-void
.end method

.method public static final synthetic access$setSearching$p(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Z)V
    .locals 0

    .line 29
    iput-boolean p1, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->searching:Z

    return-void
.end method

.method private final reloadChannels()V
    .locals 2

    .line 136
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;)V

    const/4 p0, 0x1

    invoke-virtual {v0, p0, v1}, Lcom/exteragram/messenger/feed/FeedController;->loadChannels(ZLcom/exteragram/messenger/feed/FeedController$ChannelsCallback;)V

    return-void
.end method

.method private final removeChannel(J)V
    .locals 2

    .line 148
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->channels:Ljava/util/ArrayList;

    new-instance v1, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda4;

    invoke-direct {v1, p1, p2}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda4;-><init>(J)V

    new-instance p1, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda5;

    invoke-direct {p1, v1}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda5;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->removeIf(Ljava/util/function/Predicate;)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 149
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_0

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
    return-void
.end method

.method private final setAllExcluded(Z)V
    .locals 6

    .line 240
    sget-object v0, Lcom/exteragram/messenger/feed/FeedConfig;->Companion:Lcom/exteragram/messenger/feed/FeedConfig$Companion;

    iget v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/feed/FeedConfig$Companion;->getInstance(I)Lcom/exteragram/messenger/feed/FeedConfig;

    move-result-object v0

    if-eqz p1, :cond_1

    .line 242
    iget-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->channels:Ljava/util/ArrayList;

    .line 1586
    new-instance v1, Ljava/util/ArrayList;

    const/16 v2, 0xa

    invoke-static {p1, v2}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v2

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v2

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_0

    invoke-virtual {p1, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    .line 1662
    check-cast v4, Lorg/telegram/tgnet/TLRPC$Chat;

    .line 242
    iget-wide v4, v4, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    neg-long v4, v4

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    .line 1662
    invoke-interface {v1, v4}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 242
    :cond_0
    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/feed/FeedConfig;->excludeAll(Ljava/util/Collection;)V

    goto :goto_1

    .line 244
    :cond_1
    invoke-virtual {v0}, Lcom/exteragram/messenger/feed/FeedConfig;->clearExcluded()V

    .line 246
    :goto_1
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_2

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_2
    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 4

    .line 80
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object p1

    .line 82
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    new-instance v1, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$1;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$1;-><init>(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;)V

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setActionBarMenuOnItemClick(Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;)V

    .line 92
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/ActionBar;->createMenu()Lorg/telegram/ui/ActionBar/ActionBarMenu;

    move-result-object v0

    const/4 v1, 0x0

    .line 93
    sget v2, Lorg/telegram/messenger/R$drawable;->outline_header_search:I

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(II)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v1

    const/4 v2, 0x1

    .line 94
    invoke-virtual {v1, v2}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setIsSearchField(Z)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v1

    .line 95
    new-instance v3, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;-><init>(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;)V

    invoke-virtual {v1, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setActionBarMenuItemSearchListener(Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemSearchListener;)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v1

    const/4 v3, 0x7

    .line 113
    invoke-virtual {v1, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setSearchPaddingStart(I)V

    .line 114
    sget v3, Lorg/telegram/messenger/R$string;->Search:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setSearchFieldHint(Ljava/lang/CharSequence;)V

    const/4 v1, 0x3

    .line 116
    sget v3, Lorg/telegram/messenger/R$drawable;->ic_ab_other:I

    invoke-virtual {v0, v1, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(II)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v0

    .line 117
    sget v1, Lorg/telegram/messenger/R$drawable;->msg_select:I

    sget v3, Lorg/telegram/messenger/R$string;->SelectAll:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v2, v1, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addSubItem(IILjava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    .line 118
    sget v1, Lorg/telegram/messenger/R$drawable;->msg_cancel:I

    sget v2, Lorg/telegram/messenger/R$string;->DeselectAll:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    const/4 v3, 0x2

    invoke-virtual {v0, v3, v1, v2}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addSubItem(IILjava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    .line 119
    iput-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->otherItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    .line 121
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->reloadChannels()V

    return-object p1
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 72
    sget p2, Lorg/telegram/messenger/NotificationCenter;->feedNeedReload:I

    if-ne p1, p2, :cond_0

    .line 73
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->reloadChannels()V

    return-void

    .line 74
    :cond_0
    sget p2, Lorg/telegram/messenger/NotificationCenter;->dialogDeleted:I

    if-ne p1, p2, :cond_1

    const/4 p1, 0x0

    .line 75
    aget-object p1, p3, p1

    check-cast p1, Ljava/lang/Long;

    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide p1

    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->removeChannel(J)V

    :cond_1
    return-void
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 16
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Lorg/telegram/ui/Components/UniversalAdapter;",
            ")V"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v12, p1

    .line 154
    sget-object v1, Lcom/exteragram/messenger/feed/FeedConfig;->Companion:Lcom/exteragram/messenger/feed/FeedConfig$Companion;

    iget v2, v0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-virtual {v1, v2}, Lcom/exteragram/messenger/feed/FeedConfig$Companion;->getInstance(I)Lcom/exteragram/messenger/feed/FeedConfig;

    move-result-object v13

    .line 155
    iget-object v14, v0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->query:Ljava/lang/String;

    .line 156
    invoke-static {v14}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v15

    if-eqz v15, :cond_0

    .line 158
    sget v1, Lorg/telegram/messenger/R$string;->General:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v12, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 159
    sget-object v1, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;->FEED_TAB:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;->getId()I

    move-result v1

    sget v2, Lorg/telegram/messenger/R$string;->FeedBottomTab:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getShowFeedTab()Z

    move-result v3

    sget v4, Lorg/telegram/messenger/R$string;->FeedBottomTabInfo:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x1b0

    const/4 v11, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "feedBottomTab"

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v12, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 160
    sget-object v0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;->WIDE_POSTS:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;

    invoke-virtual {v0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;->getId()I

    move-result v1

    sget v0, Lorg/telegram/messenger/R$string;->WidePostsInFeed:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getWidePostsInFeed()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const-string v7, "feedWidePosts"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v12, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 161
    sget-object v0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;->UNREAD_COUNTER:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;

    invoke-virtual {v0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;->getId()I

    move-result v1

    sget v0, Lorg/telegram/messenger/R$string;->FeedUnreadCounter:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getShowFeedUnreadCounter()Z

    move-result v3

    const-string v7, "feedUnreadCounter"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v12, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 162
    sget-object v0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;->ARCHIVE:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;

    invoke-virtual {v0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;->getId()I

    move-result v1

    sget v0, Lorg/telegram/messenger/R$string;->FeedIncludeArchived:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v13}, Lcom/exteragram/messenger/feed/FeedConfig;->isIncludeArchived()Z

    move-result v3

    const-string v7, "feedIncludeArchived"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v12, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 163
    sget v1, Lorg/telegram/messenger/R$string;->FeedIncludeArchivedInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v12, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 166
    :cond_0
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 167
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 168
    iget-object v0, v0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->channels:Ljava/util/ArrayList;

    .line 1915
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v4, 0x0

    move v5, v4

    :cond_1
    :goto_0
    if-ge v5, v3, :cond_4

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    check-cast v6, Lorg/telegram/tgnet/TLRPC$Chat;

    if-nez v15, :cond_2

    .line 169
    iget-object v7, v6, Lorg/telegram/tgnet/TLRPC$Chat;->title:Ljava/lang/String;

    if-eqz v7, :cond_1

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x2

    const/4 v9, 0x0

    invoke-static {v7, v14, v4, v8, v9}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_2

    goto :goto_0

    .line 172
    :cond_2
    iget-wide v7, v6, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    neg-long v7, v7

    invoke-virtual {v13, v7, v8}, Lcom/exteragram/messenger/feed/FeedConfig;->isExcluded(J)Z

    move-result v7

    xor-int/lit8 v8, v7, 0x1

    .line 173
    iget-wide v9, v6, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    invoke-static {v9, v10}, Ljava/lang/Long;->hashCode(J)I

    move-result v9

    invoke-static {v9, v6}, Lorg/telegram/ui/Components/UItem;->asUserCheckbox(ILorg/telegram/tgnet/TLObject;)Lorg/telegram/ui/Components/UItem;

    move-result-object v6

    invoke-virtual {v6, v8}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object v6

    if-nez v7, :cond_3

    move-object v7, v1

    goto :goto_1

    :cond_3
    move-object v7, v2

    .line 174
    :goto_1
    invoke-virtual {v7, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 177
    :cond_4
    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_5

    .line 178
    sget v0, Lorg/telegram/messenger/R$string;->FeedShownChannels:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v12, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 179
    invoke-virtual {v12, v1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 181
    :cond_5
    invoke-interface {v2}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_7

    .line 182
    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_6

    .line 183
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v12, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 185
    :cond_6
    sget v0, Lorg/telegram/messenger/R$string;->FeedHiddenChannels:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v12, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 186
    invoke-virtual {v12, v2}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    :cond_7
    if-eqz v15, :cond_9

    .line 188
    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_8

    invoke-interface {v2}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_9

    .line 189
    :cond_8
    sget v0, Lorg/telegram/messenger/R$string;->FeedChannelsInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v12, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_9
    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 57
    sget p0, Lorg/telegram/messenger/R$string;->FeedSettings:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public onBackPressed(Z)Z
    .locals 1

    .line 126
    iget-boolean v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->searching:Z

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    .line 128
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBar;->closeSearchField()V

    :cond_0
    const/4 p0, 0x0

    return p0

    .line 132
    :cond_1
    invoke-super {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->onBackPressed(Z)Z

    move-result p0

    return p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 1

    .line 194
    iget-object p2, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p3, p2, Lorg/telegram/tgnet/TLRPC$Chat;

    const/4 p4, 0x0

    if-eqz p3, :cond_0

    check-cast p2, Lorg/telegram/tgnet/TLRPC$Chat;

    goto :goto_0

    :cond_0
    move-object p2, p4

    :goto_0
    if-eqz p2, :cond_1

    .line 196
    new-instance p3, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda6;

    invoke-direct {p3, p0, p2}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Lorg/telegram/tgnet/TLRPC$Chat;)V

    invoke-virtual {p0, p1, p3}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 202
    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p2

    .line 296
    invoke-interface {p2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :cond_2
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result p3

    if-eqz p3, :cond_3

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p3

    move-object p5, p3

    check-cast p5, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;

    .line 202
    invoke-virtual {p5}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;->getId()I

    move-result p5

    iget v0, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-ne p5, v0, :cond_2

    move-object p4, p3

    :cond_3
    check-cast p4, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$SettingItem;

    if-nez p4, :cond_4

    return-void

    .line 203
    :cond_4
    sget-object p2, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p4}, Ljava/lang/Enum;->ordinal()I

    move-result p3

    aget p2, p2, p3

    const/4 p3, 0x0

    const/4 p4, 0x1

    if-eq p2, p4, :cond_8

    const/4 p5, 0x2

    if-eq p2, p5, :cond_7

    const/4 p5, 0x3

    if-eq p2, p5, :cond_6

    const/4 p1, 0x4

    if-ne p2, p1, :cond_5

    .line 218
    sget-object p1, Lcom/exteragram/messenger/feed/FeedConfig;->Companion:Lcom/exteragram/messenger/feed/FeedConfig$Companion;

    iget p2, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/feed/FeedConfig$Companion;->getInstance(I)Lcom/exteragram/messenger/feed/FeedConfig;

    move-result-object p1

    .line 219
    invoke-virtual {p1}, Lcom/exteragram/messenger/feed/FeedConfig;->isIncludeArchived()Z

    move-result p2

    xor-int/2addr p2, p4

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/feed/FeedConfig;->setIncludeArchived(Z)V

    .line 220
    invoke-direct {p0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->reloadChannels()V

    return-void

    .line 203
    :cond_5
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 214
    :cond_6
    new-instance p2, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda9;

    invoke-direct {p2}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda9;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 215
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/NotificationCenter;->updateInterfaces:I

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    filled-new-array {p2}, [Ljava/lang/Object;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void

    .line 210
    :cond_7
    new-instance p2, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda8;

    invoke-direct {p2}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda8;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 211
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->isTablet()Z

    move-result p1

    invoke-interface {p0, p1}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void

    .line 205
    :cond_8
    new-instance p2, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda7;

    invoke-direct {p2}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda7;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 206
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p1, :cond_9

    invoke-virtual {p1, p4}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 207
    :cond_9
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/NotificationCenter;->feedTabVisibleToggled:I

    new-array p2, p3, [Ljava/lang/Object;

    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method public onFragmentCreate()Z
    .locals 2

    .line 60
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->feedNeedReload:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 61
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->dialogDeleted:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 62
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentCreate()Z

    move-result p0

    return p0
.end method

.method public onFragmentDestroy()V
    .locals 2

    .line 66
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->feedNeedReload:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 67
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->dialogDeleted:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 68
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    return-void
.end method

.method public onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z
    .locals 2

    .line 226
    iget-object v0, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of v1, v0, Lorg/telegram/tgnet/TLRPC$Chat;

    if-eqz v1, :cond_0

    check-cast v0, Lorg/telegram/tgnet/TLRPC$Chat;

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    if-nez v0, :cond_1

    invoke-super/range {p0 .. p5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z

    move-result p0

    return p0

    .line 227
    :cond_1
    invoke-static {p0, p2}, Lorg/telegram/ui/Components/ItemOptions;->makeOptions(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/View;)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object p1

    const/4 p3, 0x0

    .line 228
    invoke-virtual {p1, p3}, Lorg/telegram/ui/Components/ItemOptions;->setLongPressSelectionEnabled(Z)Lorg/telegram/ui/Components/ItemOptions;

    .line 229
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p3

    invoke-virtual {p3, p2}, Lorg/telegram/ui/Components/RecyclerListView;->getClipBackground(Landroid/view/View;)Landroid/graphics/drawable/Drawable;

    move-result-object p2

    invoke-virtual {p1, p2}, Lorg/telegram/ui/Components/ItemOptions;->setScrimViewBackground(Landroid/graphics/drawable/Drawable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 230
    sget p2, Lorg/telegram/messenger/R$drawable;->msg_channel:I

    sget p3, Lorg/telegram/messenger/R$string;->OpenChannel2:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p3

    new-instance p4, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda2;

    invoke-direct {p4, p0, v0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Lorg/telegram/tgnet/TLRPC$Chat;)V

    invoke-virtual {p1, p2, p3, p4}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    move-object p2, p0

    move-object p0, p1

    .line 231
    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedChannelActions;->canLeave(Lorg/telegram/tgnet/TLRPC$Chat;)Z

    move-result p1

    move-object p3, p2

    sget p2, Lorg/telegram/messenger/R$drawable;->msg_leave:I

    sget p4, Lorg/telegram/messenger/R$string;->LeaveChannelMenu:I

    invoke-static {p4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p4

    new-instance p5, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda3;

    invoke-direct {p5, p3, v0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Lorg/telegram/tgnet/TLRPC$Chat;)V

    move-object p3, p4

    const/4 p4, 0x1

    invoke-virtual/range {p0 .. p5}, Lorg/telegram/ui/Components/ItemOptions;->addIf(ZILjava/lang/CharSequence;ZLjava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 234
    invoke-virtual {p0}, Lorg/telegram/ui/Components/ItemOptions;->show()Lorg/telegram/ui/Components/ItemOptions;

    const/4 p0, 0x1

    return p0
.end method
