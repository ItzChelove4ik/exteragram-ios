.class public Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$Companion;,
        Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u000e\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\u0007\n\u0002\u0008\u0005\n\u0002\u0010\u0000\n\u0002\u0008\u0006\u0008\u0016\u0018\u0000 A2\u00020\u00012\u00020\u0002:\u0002ABB\u0011\u0012\u0008\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\u0008\u0005\u0010\u0006J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0014J\u0008\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u0012H\u0002J\u0010\u0010\'\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0012H\u0002J\u0008\u0010(\u001a\u00020 H\u0002J\u0008\u0010)\u001a\u00020\u0008H\u0016J\u0008\u0010*\u001a\u00020 H\u0016J\u0008\u0010+\u001a\u00020 H\u0002J\u0008\u0010,\u001a\u00020\nH\u0016J\u000e\u0010-\u001a\u0008\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u001e\u0010.\u001a\u00020 2\u000c\u0010/\u001a\u0008\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u00100\u001a\u000201H\u0014J0\u00102\u001a\u00020 2\u0006\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u00122\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000207H\u0014J5\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020\u00122\u0006\u0010;\u001a\u00020\u00122\u0016\u0010<\u001a\u000c\u0012\u0008\u0008\u0001\u0012\u0004\u0018\u00010=0\u000e\"\u0004\u0018\u00010=H\u0016\u00a2\u0006\u0002\u0010>J\u0010\u0010?\u001a\u00020\u00082\u0006\u0010@\u001a\u00020\u0008H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u000cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\u0008\u0012\u0004\u0012\u00020\n0\u000eX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006C"
    }
    d2 = {
        "Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;",
        "iconPack",
        "Lcom/exteragram/messenger/icons/IconPack;",
        "<init>",
        "(Lcom/exteragram/messenger/icons/IconPack;)V",
        "searching",
        "",
        "query",
        "",
        "otherItem",
        "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;",
        "filterItems",
        "",
        "Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;",
        "[Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;",
        "iconFilter",
        "",
        "searchRunnable",
        "Ljava/lang/Runnable;",
        "packItems",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "packItemLowerNames",
        "[Ljava/lang/String;",
        "packItemsFor",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "updateAdapter",
        "",
        "createFilterLayout",
        "Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;",
        "createFilterItem",
        "layout",
        "text",
        "filter",
        "setIconFilter",
        "updateFilterChecks",
        "onFragmentCreate",
        "onFragmentDestroy",
        "loadIconsAsync",
        "getTitle",
        "getPackItems",
        "fillItems",
        "items",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "position",
        "x",
        "",
        "y",
        "didReceivedNotification",
        "id",
        "account",
        "args",
        "",
        "(II[Ljava/lang/Object;)V",
        "onBackPressed",
        "invoked",
        "Companion",
        "EditorIconCell",
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
        "SMAP\nIconPacksEditorActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPacksEditorActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksEditorActivity\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,388:1\n14113#2,3:389\n1#3:392\n1924#4,3:393\n1021#4,2:396\n*S KotlinDebug\n*F\n+ 1 IconPacksEditorActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksEditorActivity\n*L\n175#1:389,3\n228#1:393,3\n208#1:396,2\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$Companion;

.field private static final cachedIconItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;"
        }
    .end annotation
.end field

.field private static isIconsLoaded:Z

.field private static isLoading:Z


# instance fields
.field private final filterItems:[Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

.field private iconFilter:I

.field private iconPack:Lcom/exteragram/messenger/icons/IconPack;

.field private otherItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

.field private packItemLowerNames:[Ljava/lang/String;

.field private packItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;"
        }
    .end annotation
.end field

.field private packItemsFor:Lcom/exteragram/messenger/icons/IconPack;

.field private query:Ljava/lang/String;

.field private searchRunnable:Ljava/lang/Runnable;

.field private searching:Z


# direct methods
.method public static $r8$lambda$1D1TkBWKe-ycW3UIMgBeZ5aqz0E(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)V
    .locals 6

    .line 195
    new-instance v0, Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {v1}, Lcom/exteragram/messenger/icons/IconManager;->getSystemIcons()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/util/HashMap;-><init>(Ljava/util/Map;)V

    .line 196
    invoke-virtual {v0}, Ljava/util/HashMap;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 197
    new-instance p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$$ExternalSyntheticLambda2;

    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$$ExternalSyntheticLambda2;-><init>()V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 204
    :cond_0
    new-instance v1, Ljava/util/ArrayList;

    const/16 v2, 0x5dc

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 205
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    .line 206
    sget-object v4, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;->Companion:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    const/4 v5, 0x0

    invoke-virtual {v4, v2, v3, v5}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;->asIcon(ILjava/lang/CharSequence;Lcom/exteragram/messenger/icons/IconPack;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 1021
    :cond_1
    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v0

    const/4 v2, 0x1

    if-le v0, v2, :cond_2

    new-instance v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$loadIconsAsync$lambda$6$$inlined$sortBy$1;

    invoke-direct {v0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$loadIconsAsync$lambda$6$$inlined$sortBy$1;-><init>()V

    invoke-static {v1, v0}, Lkotlin/collections/CollectionsKt;->sortWith(Ljava/util/List;Ljava/util/Comparator;)V

    .line 210
    :cond_2
    new-instance v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$$ExternalSyntheticLambda3;

    invoke-direct {v0, v1, p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$$ExternalSyntheticLambda3;-><init>(Ljava/util/ArrayList;Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$69mJBxZl5O5EW_Nl6tO4arW-8Ac()V
    .locals 2

    const/4 v0, 0x0

    .line 198
    sput-boolean v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->isLoading:Z

    .line 199
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/icons/IconManager;->initialize(Z)V

    return-void
.end method

.method public static $r8$lambda$7Bzs4DV-0Xa7qKoQ3-e9Qk0UYws(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Landroid/view/View;)V
    .locals 0

    .line 144
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->otherItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->getPopupLayout()Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;->getSwipeBack()Lorg/telegram/ui/Components/PopupSwipeBackLayout;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/PopupSwipeBackLayout;->closeForeground()V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$EYr2Y6g3-Q1c1cfyqK3hs-YGcNo(Ljava/util/ArrayList;Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)V
    .locals 1

    .line 211
    sget-object v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->cachedIconItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 212
    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    const/4 p0, 0x1

    .line 213
    sput-boolean p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->isIconsLoaded:Z

    const/4 p0, 0x0

    .line 214
    sput-boolean p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->isLoading:Z

    const/4 p0, 0x0

    .line 215
    iput-object p0, p1, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->packItems:Ljava/util/ArrayList;

    .line 216
    invoke-direct {p1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->updateAdapter()V

    return-void
.end method

.method public static $r8$lambda$o85uiPt34Xr3v1pDEtDOK1NsfDk(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;ILandroid/view/View;)V
    .locals 0

    .line 163
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->setIconFilter(I)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->Companion:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$Companion;

    .line 58
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->cachedIconItems:Ljava/util/ArrayList;

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/icons/IconPack;)V
    .locals 0

    .line 48
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    const/4 p1, 0x3

    .line 67
    new-array p1, p1, [Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->filterItems:[Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    const/4 p1, 0x0

    .line 73
    new-array p1, p1, [Ljava/lang/String;

    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->packItemLowerNames:[Ljava/lang/String;

    return-void
.end method

.method public static final synthetic access$getIconPack$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)Lcom/exteragram/messenger/icons/IconPack;
    .locals 0

    .line 48
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    return-object p0
.end method

.method public static final synthetic access$getOtherItem$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;
    .locals 0

    .line 48
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->otherItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    return-object p0
.end method

.method public static final synthetic access$getSearchRunnable$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)Ljava/lang/Runnable;
    .locals 0

    .line 48
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->searchRunnable:Ljava/lang/Runnable;

    return-object p0
.end method

.method public static final synthetic access$setQuery$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Ljava/lang/String;)V
    .locals 0

    .line 48
    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->query:Ljava/lang/String;

    return-void
.end method

.method public static final synthetic access$setSearchRunnable$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Ljava/lang/Runnable;)V
    .locals 0

    .line 48
    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->searchRunnable:Ljava/lang/Runnable;

    return-void
.end method

.method public static final synthetic access$setSearching$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Z)V
    .locals 0

    .line 48
    iput-boolean p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->searching:Z

    return-void
.end method

.method public static final synthetic access$updateAdapter(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)V
    .locals 0

    .line 48
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->updateAdapter()V

    return-void
.end method

.method private final createFilterItem(Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;Ljava/lang/String;I)V
    .locals 6

    .line 159
    new-instance v0, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    invoke-virtual {p1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v1

    const/4 v4, 0x0

    iget-object v5, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/4 v2, 0x1

    const/4 v3, 0x0

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;-><init>(Landroid/content/Context;ZZZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    const/4 v1, 0x0

    .line 160
    invoke-virtual {v0, p2, v1}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setTextAndIcon(Ljava/lang/CharSequence;I)V

    const/high16 p2, 0x43440000    # 196.0f

    .line 161
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p2

    invoke-virtual {v0, p2}, Landroid/view/View;->setMinimumWidth(I)V

    const/4 p2, -0x1

    const/16 v1, 0x30

    .line 162
    invoke-static {p2, v1}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(II)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object p2

    invoke-virtual {p1, v0, p2}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;->addView(Landroid/view/View;Landroid/widget/LinearLayout$LayoutParams;)V

    .line 163
    new-instance p1, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$$ExternalSyntheticLambda4;

    invoke-direct {p1, p0, p3}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;I)V

    invoke-virtual {v0, p1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 164
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->filterItems:[Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    aput-object v0, p0, p3

    return-void
.end method

.method private final createFilterLayout(Landroid/content/Context;)Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;
    .locals 5

    .line 140
    new-instance v0, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/4 v2, 0x0

    invoke-direct {v0, p1, v2, v1}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;-><init>(Landroid/content/Context;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    const/4 p1, 0x1

    .line 141
    invoke-virtual {v0, p1}, Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;->setFitItems(Z)V

    .line 143
    sget v1, Lorg/telegram/messenger/R$drawable;->msg_arrow_back:I

    sget v3, Lorg/telegram/messenger/R$string;->Back:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    iget-object v4, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v1, v3, v2, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addItem(Landroid/view/ViewGroup;ILjava/lang/CharSequence;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    move-result-object v1

    .line 144
    new-instance v3, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$$ExternalSyntheticLambda1;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)V

    invoke-virtual {v1, v3}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 146
    invoke-static {v2, v0}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addGap(ILorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;)Landroid/view/View;

    move-result-object v1

    const/4 v3, -0x1

    const/16 v4, 0x8

    .line 147
    invoke-static {v3, v4}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(II)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v3

    invoke-virtual {v1, v3}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 148
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultSubmenuSeparator:I

    invoke-static {v3}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v3

    invoke-virtual {v1, v3}, Landroid/view/View;->setBackgroundColor(I)V

    .line 150
    sget v1, Lorg/telegram/messenger/R$string;->IconPickerAllIcons:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v0, v1, v2}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->createFilterItem(Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;Ljava/lang/String;I)V

    .line 151
    sget v1, Lorg/telegram/messenger/R$string;->IconPickerReplacedIcons:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v0, v1, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->createFilterItem(Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;Ljava/lang/String;I)V

    .line 152
    sget p1, Lorg/telegram/messenger/R$string;->IconPickerNotReplacedIcons:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    const/4 v1, 0x2

    invoke-direct {p0, v0, p1, v1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->createFilterItem(Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;Ljava/lang/String;I)V

    .line 153
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->updateFilterChecks()V

    return-object v0
.end method

.method private final getPackItems()Ljava/util/ArrayList;
    .locals 13
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;"
        }
    .end annotation

    .line 224
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->packItems:Ljava/util/ArrayList;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->packItemsFor:Lcom/exteragram/messenger/icons/IconPack;

    iget-object v2, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-static {v1, v2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    return-object v0

    .line 226
    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    sget-object v1, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->cachedIconItems:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v2

    invoke-direct {v0, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 227
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    new-array v2, v1, [Ljava/lang/String;

    const/4 v3, 0x0

    move v4, v3

    :goto_0
    const-string v5, ""

    if-ge v4, v1, :cond_1

    aput-object v5, v2, v4

    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 228
    :cond_1
    sget-object v1, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->cachedIconItems:Ljava/util/ArrayList;

    .line 1925
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v4

    move v6, v3

    :goto_1
    if-ge v6, v4, :cond_5

    invoke-virtual {v1, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v6, v6, 0x1

    add-int/lit8 v8, v3, 0x1

    if-gez v3, :cond_2

    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwIndexOverflow()V

    :cond_2
    check-cast v7, Lorg/telegram/ui/Components/UItem;

    .line 229
    sget-object v9, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;->Companion:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;

    iget v10, v7, Lorg/telegram/ui/Components/UItem;->id:I

    iget-object v11, v7, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    iget-object v12, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {v9, v10, v11, v12}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;->asIcon(ILjava/lang/CharSequence;Lcom/exteragram/messenger/icons/IconPack;)Lorg/telegram/ui/Components/UItem;

    move-result-object v9

    invoke-virtual {v0, v9}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 230
    iget-object v7, v7, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    if-eqz v7, :cond_3

    invoke-virtual {v7}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v7

    if-eqz v7, :cond_3

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v9

    invoke-virtual {v7, v9}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v7

    if-nez v7, :cond_4

    :cond_3
    move-object v7, v5

    :cond_4
    aput-object v7, v2, v3

    move v3, v8

    goto :goto_1

    .line 232
    :cond_5
    iput-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->packItems:Ljava/util/ArrayList;

    .line 233
    iput-object v2, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->packItemLowerNames:[Ljava/lang/String;

    .line 234
    iget-object v1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    iput-object v1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->packItemsFor:Lcom/exteragram/messenger/icons/IconPack;

    return-object v0
.end method

.method private final loadIconsAsync()V
    .locals 2

    .line 190
    sget-boolean v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->isIconsLoaded:Z

    if-eqz v0, :cond_0

    sget-object v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->cachedIconItems:Ljava/util/ArrayList;

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 191
    :cond_0
    sget-boolean v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->isLoading:Z

    if-eqz v0, :cond_1

    :goto_0
    return-void

    :cond_1
    const/4 v0, 0x1

    .line 192
    sput-boolean v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->isLoading:Z

    .line 194
    sget-object v0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private final setIconFilter(I)V
    .locals 1

    .line 168
    iget v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconFilter:I

    if-ne v0, p1, :cond_0

    return-void

    .line 169
    :cond_0
    iput p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconFilter:I

    .line 170
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->updateFilterChecks()V

    .line 171
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->updateAdapter()V

    return-void
.end method

.method private final updateAdapter()V
    .locals 1

    .line 134
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz v0, :cond_0

    .line 135
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_0

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
    return-void
.end method

.method private final updateFilterChecks()V
    .locals 8

    .line 175
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->filterItems:[Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    .line 14114
    array-length v1, v0

    const/4 v2, 0x0

    move v3, v2

    move v4, v3

    :goto_0
    if-ge v3, v1, :cond_2

    aget-object v5, v0, v3

    add-int/lit8 v6, v4, 0x1

    if-eqz v5, :cond_1

    .line 175
    iget v7, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconFilter:I

    if-ne v7, v4, :cond_0

    const/4 v4, 0x1

    goto :goto_1

    :cond_0
    move v4, v2

    :goto_1
    invoke-virtual {v5, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setChecked(Z)V

    :cond_1
    add-int/lit8 v3, v3, 0x1

    move v4, v6

    goto :goto_0

    :cond_2
    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 7

    .line 77
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object v0

    .line 79
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/ActionBar;->createMenu()Lorg/telegram/ui/ActionBar/ActionBarMenu;

    move-result-object v1

    const/4 v2, 0x0

    .line 80
    sget v3, Lorg/telegram/messenger/R$drawable;->outline_header_search:I

    invoke-virtual {v1, v2, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(II)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v2

    const/4 v3, 0x1

    .line 81
    invoke-virtual {v2, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setIsSearchField(Z)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v2

    .line 82
    new-instance v4, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)V

    invoke-virtual {v2, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setActionBarMenuItemSearchListener(Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemSearchListener;)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v2

    const/4 v4, 0x7

    .line 105
    invoke-virtual {v2, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setSearchPaddingStart(I)V

    const/4 v2, 0x3

    .line 107
    sget v4, Lorg/telegram/messenger/R$drawable;->ic_ab_other:I

    invoke-virtual {v1, v2, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(II)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v1

    .line 108
    iput-object v1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->otherItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    .line 109
    sget v2, Lorg/telegram/messenger/R$drawable;->msg_select:I

    sget v4, Lorg/telegram/messenger/R$string;->IconPickerFilter:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->createFilterLayout(Landroid/content/Context;)Lorg/telegram/ui/ActionBar/ActionBarPopupWindow$ActionBarPopupWindowLayout;

    move-result-object v5

    const/4 v6, 0x0

    invoke-virtual {v1, v2, v6, v4, v5}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addSwipeBackItem(ILandroid/graphics/drawable/Drawable;Ljava/lang/String;Landroid/view/View;)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    .line 110
    sget v2, Lorg/telegram/messenger/R$drawable;->msg_edit:I

    sget v4, Lorg/telegram/messenger/R$string;->Edit:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v3, v2, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addSubItem(IILjava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    .line 111
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditingIconPackId()Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_0

    .line 112
    sget v2, Lorg/telegram/messenger/R$drawable;->ic_ab_done:I

    sget v3, Lorg/telegram/messenger/R$string;->IconPickerSaveAndExit:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x2

    invoke-virtual {v1, v4, v2, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->addSubItem(IILjava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    move-result-object v1

    .line 113
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_addButtonPressed:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v3

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v2

    invoke-virtual {v1, v3, v2}, Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;->setColors(II)Lorg/telegram/ui/ActionBar/ActionBarMenuSubItem;

    .line 116
    :cond_0
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    new-instance v2, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1;

    invoke-direct {v2, p0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Landroid/content/Context;)V

    invoke-virtual {v1, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setActionBarMenuOnItemClick(Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;)V

    return-object v0
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 264
    sget p2, Lorg/telegram/messenger/NotificationCenter;->iconPackUpdated:I

    if-eq p1, p2, :cond_0

    goto :goto_1

    .line 265
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object p1

    if-nez p1, :cond_1

    goto :goto_1

    .line 267
    :cond_1
    sget-boolean p1, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->isIconsLoaded:Z

    if-nez p1, :cond_2

    .line 268
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->loadIconsAsync()V

    .line 271
    :cond_2
    iget-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    if-eqz p1, :cond_4

    .line 272
    sget-object p2, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Lcom/exteragram/messenger/icons/IconManager;->findPackById(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object p1

    if-nez p1, :cond_3

    .line 274
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->finishFragment()V

    return-void

    .line 277
    :cond_3
    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    .line 278
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->getTitle()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lorg/telegram/ui/ActionBar/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    .line 281
    :cond_4
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz p1, :cond_5

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getLayoutManager()Landroidx/recyclerview/widget/LinearLayoutManager;

    move-result-object p1

    invoke-virtual {p1}, Landroidx/recyclerview/widget/LinearLayoutManager;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object p1

    goto :goto_0

    :cond_5
    const/4 p1, 0x0

    .line 282
    :goto_0
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->updateAdapter()V

    if-eqz p1, :cond_6

    .line 284
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getLayoutManager()Landroidx/recyclerview/widget/LinearLayoutManager;

    move-result-object p0

    invoke-virtual {p0, p1}, Landroidx/recyclerview/widget/LinearLayoutManager;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    :cond_6
    :goto_1
    return-void
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 11
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

    .line 239
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object p2

    if-eqz p2, :cond_a

    sget-boolean p2, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->isIconsLoaded:Z

    if-nez p2, :cond_0

    goto/16 :goto_6

    .line 241
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->getPackItems()Ljava/util/ArrayList;

    move-result-object p2

    .line 242
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->query:Ljava/lang/String;

    .line 243
    iget-boolean v1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->searching:Z

    const/4 v2, 0x0

    if-eqz v1, :cond_2

    if-eqz v0, :cond_2

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    :cond_1
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v0

    goto :goto_1

    :cond_2
    :goto_0
    move-object v0, v2

    .line 244
    :goto_1
    iget-object v1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    if-eqz v1, :cond_3

    invoke-virtual {v1}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object v1

    goto :goto_2

    :cond_3
    move-object v1, v2

    .line 246
    :goto_2
    invoke-interface {p2}, Ljava/util/Collection;->size()I

    move-result v3

    const/4 v4, 0x0

    move v5, v4

    :goto_3
    if-ge v5, v3, :cond_a

    const/4 v6, 0x2

    if-eqz v0, :cond_4

    .line 247
    iget-object v7, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->packItemLowerNames:[Ljava/lang/String;

    aget-object v7, v7, v5

    invoke-static {v7, v0, v4, v6, v2}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_9

    .line 248
    :cond_4
    invoke-virtual {p2, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lorg/telegram/ui/Components/UItem;

    .line 249
    iget v8, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconFilter:I

    if-eqz v8, :cond_8

    .line 250
    iget-object v8, v7, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    if-eqz v8, :cond_5

    invoke-virtual {v8}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v8

    goto :goto_4

    :cond_5
    move-object v8, v2

    :goto_4
    const/4 v9, 0x1

    if-eqz v8, :cond_6

    if-eqz v1, :cond_6

    .line 251
    invoke-interface {v1, v8}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v8

    if-ne v8, v9, :cond_6

    move v8, v9

    goto :goto_5

    :cond_6
    move v8, v4

    .line 252
    :goto_5
    iget v10, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconFilter:I

    if-ne v10, v9, :cond_7

    if-eqz v8, :cond_9

    :cond_7
    if-ne v10, v6, :cond_8

    if-nez v8, :cond_9

    .line 255
    :cond_8
    invoke-virtual {p1, v7}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_9
    add-int/lit8 v5, v5, 0x1

    goto :goto_3

    :cond_a
    :goto_6
    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 221
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Lcom/exteragram/messenger/icons/IconPack;->getName()Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    return-object p0

    :cond_1
    :goto_0
    sget p0, Lorg/telegram/messenger/R$string;->NewIconPack:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public onBackPressed(Z)Z
    .locals 1

    .line 381
    iget-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->searching:Z

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    .line 382
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBar;->closeSearchField()V

    :cond_0
    const/4 p0, 0x0

    return p0

    .line 385
    :cond_1
    invoke-super {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->onBackPressed(Z)Z

    move-result p0

    return p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 260
    sget-object p2, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object p3

    iget p1, p1, Lorg/telegram/ui/Components/UItem;->id:I

    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->iconPack:Lcom/exteragram/messenger/icons/IconPack;

    invoke-virtual {p2, p3, p1, p0}, Lcom/exteragram/messenger/icons/IconManager;->showReplaceAlert(Landroid/content/Context;ILcom/exteragram/messenger/icons/IconPack;)V

    return-void
.end method

.method public onFragmentCreate()Z
    .locals 2

    .line 179
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->iconPackUpdated:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 180
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->loadIconsAsync()V

    .line 181
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentCreate()Z

    move-result p0

    return p0
.end method

.method public onFragmentDestroy()V
    .locals 2

    .line 185
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->iconPackUpdated:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 186
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    return-void
.end method
