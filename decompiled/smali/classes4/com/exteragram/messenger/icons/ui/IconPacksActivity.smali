.class public final Lcom/exteragram/messenger/icons/ui/IconPacksActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\u0007\n\u0002\u0008\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\u00a2\u0006\u0004\u0008\u0003\u0010\u0004J\u0008\u0010\u000b\u001a\u00020\u0008H\u0016J\u0008\u0010\u000c\u001a\u00020\rH\u0016J5\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0016\u0010\u0012\u001a\u000c\u0012\u0008\u0008\u0001\u0012\u0004\u0018\u00010\u00140\u0013\"\u0004\u0018\u00010\u0014H\u0016\u00a2\u0006\u0002\u0010\u0015J(\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0014J\u001e\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00102\u000c\u0010!\u001a\u0008\u0012\u0004\u0012\u00020#0\"H\u0002J\u0008\u0010$\u001a\u00020%H\u0016J\u001e\u0010&\u001a\u00020\r2\u000c\u0010\'\u001a\u0008\u0012\u0004\u0012\u00020#0\"2\u0006\u0010(\u001a\u00020)H\u0014J<\u0010*\u001a\u00020\r2\u000c\u0010\'\u001a\u0008\u0012\u0004\u0012\u00020#0\"2\u0006\u0010(\u001a\u00020)2\u0006\u0010+\u001a\u00020%2\u000c\u0010,\u001a\u0008\u0012\u0004\u0012\u00020%0-2\u0006\u0010.\u001a\u00020\u0008H\u0002J\u0016\u0010/\u001a\u00020\u00102\u000c\u0010,\u001a\u0008\u0012\u0004\u0012\u00020%0-H\u0002J\u0012\u00100\u001a\u0004\u0018\u0001012\u0006\u0010\u000f\u001a\u00020%H\u0002J0\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020#2\u0006\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000207H\u0014J0\u00109\u001a\u00020\u00082\u0006\u00103\u001a\u00020#2\u0006\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000207H\u0014J\u0010\u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u000201H\u0002J\u0010\u0010<\u001a\u00020\r2\u0006\u0010;\u001a\u000201H\u0002J\u0010\u0010=\u001a\u00020\r2\u0006\u0010;\u001a\u000201H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006>"
    }
    d2 = {
        "Lcom/exteragram/messenger/icons/ui/IconPacksActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;",
        "<init>",
        "()V",
        "floatingButton",
        "Lorg/telegram/ui/Components/FragmentFloatingButton;",
        "scrollUpdated",
        "",
        "reorderRunnable",
        "Ljava/lang/Runnable;",
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
        "onInsets",
        "left",
        "top",
        "right",
        "bottom",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "updateConfigFromReorder",
        "sectionId",
        "uItems",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "getTitle",
        "",
        "fillItems",
        "items",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "addCustomPacksSection",
        "header",
        "ids",
        "",
        "reorderable",
        "countCustomPacks",
        "getPackById",
        "Lcom/exteragram/messenger/icons/IconPack;",
        "onClick",
        "item",
        "view",
        "position",
        "x",
        "",
        "y",
        "onLongClick",
        "openEditor",
        "pack",
        "sharePack",
        "confirmDeletePack",
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
        "SMAP\nIconPacksActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPacksActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,234:1\n1642#2,10:235\n1915#2:245\n1916#2:247\n1652#2:248\n296#2,2:249\n296#2,2:252\n1834#2,4:254\n1#3:246\n1#3:251\n*S KotlinDebug\n*F\n+ 1 IconPacksActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksActivity\n*L\n89#1:235,10\n89#1:245\n89#1:247\n89#1:248\n93#1:249,2\n119#1:252,2\n164#1:254,4\n89#1:246\n*E\n"
    }
.end annotation


# instance fields
.field private floatingButton:Lorg/telegram/ui/Components/FragmentFloatingButton;

.field private reorderRunnable:Ljava/lang/Runnable;

.field private scrollUpdated:Z


# direct methods
.method public static synthetic $r8$lambda$5ACnyBOPEUZ6o3wgWrjnrOh2IGg(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;ILjava/util/ArrayList;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->updateConfigFromReorder(ILjava/util/ArrayList;)V

    return-void
.end method

.method public static $r8$lambda$A703UknTxEoYhXsLsLMEQOxGKTs(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Landroid/content/Context;Landroid/view/View;)V
    .locals 0

    .line 71
    new-instance p2, Lcom/exteragram/messenger/icons/ui/components/NewIconPackBottomSheet;

    invoke-direct {p2, p0, p1}, Lcom/exteragram/messenger/icons/ui/components/NewIconPackBottomSheet;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;)V

    invoke-virtual {p2}, Lorg/telegram/ui/ActionBar/BottomSheet;->show()V

    return-void
.end method

.method public static $r8$lambda$Nlmz4x_L8eYWlQRuLcV6MQ_eW7w(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Lcom/exteragram/messenger/icons/IconPack;)V
    .locals 0

    .line 195
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->sharePack(Lcom/exteragram/messenger/icons/IconPack;)V

    return-void
.end method

.method public static $r8$lambda$O09Q0YvY6IqFbcNwPawtwKXo5og(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Ljava/io/File;)V
    .locals 3

    .line 213
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    if-nez p0, :cond_0

    return-void

    .line 214
    :cond_0
    new-instance v0, Landroid/content/Intent;

    const-string v1, "android.intent.action.SEND"

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 215
    const-string v1, "application/zip"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setType(Ljava/lang/String;)Landroid/content/Intent;

    .line 216
    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getApplicationId()Ljava/lang/String;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ".provider"

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {p0, v1, p1}, Landroidx/core/content/FileProvider;->getUriForFile(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;)Landroid/net/Uri;

    move-result-object p1

    const-string v1, "android.intent.extra.STREAM"

    invoke-virtual {v0, v1, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;

    const/4 p1, 0x1

    .line 217
    invoke-virtual {v0, p1}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 218
    sget p1, Lorg/telegram/messenger/R$string;->ShareFile:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Landroid/content/Intent;->createChooser(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;

    move-result-object p1

    invoke-virtual {p0, p1}, Landroid/app/Activity;->startActivity(Landroid/content/Intent;)V

    return-void
.end method

.method public static $r8$lambda$_KE7t3AKcOcQlqRy3JLg14r82rk(Lcom/exteragram/messenger/icons/IconPack;Lcom/exteragram/messenger/icons/ui/IconPacksActivity;)V
    .locals 1

    .line 211
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {p0}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/icons/IconManager;->bundlePackBlocking(Ljava/lang/String;)Ljava/io/File;

    move-result-object p0

    if-nez p0, :cond_0

    return-void

    .line 212
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda9;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda9;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Ljava/io/File;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$cpdY_71GLVKD68AGwMX2-k7ryE0()V
    .locals 2

    .line 107
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/icons/IconManager;->initialize(Z)V

    return-void
.end method

.method public static $r8$lambda$j8xr0tsHauQK4SkJPnBUddf7xjA(Lcom/exteragram/messenger/icons/IconPack;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 227
    sget-object p1, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {p0}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Lcom/exteragram/messenger/icons/IconManager;->deletePack(Ljava/lang/String;)V

    return-void
.end method

.method public static $r8$lambda$qvBNI3GJXGyDRJJ2Z9oWKE21GP4(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Lcom/exteragram/messenger/icons/IconPack;)V
    .locals 0

    .line 196
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->confirmDeletePack(Lcom/exteragram/messenger/icons/IconPack;)V

    return-void
.end method

.method public static $r8$lambda$vIV9A2HliqWTFOh2bzB6NKZr1DY(Ljava/lang/String;)Z
    .locals 4

    const/4 v0, 0x2

    const/4 v1, 0x0

    .line 173
    const-string v2, "base."

    const/4 v3, 0x0

    invoke-static {p0, v2, v3, v0, v1}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$zPvZBHCf4glQiJchkElgGhbG6CU(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Lcom/exteragram/messenger/icons/IconPack;)V
    .locals 0

    .line 194
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->openEditor(Lcom/exteragram/messenger/icons/IconPack;)V

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 38
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    return-void
.end method

.method public static final synthetic access$getFloatingButton$p(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;)Lorg/telegram/ui/Components/FragmentFloatingButton;
    .locals 0

    .line 38
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->floatingButton:Lorg/telegram/ui/Components/FragmentFloatingButton;

    return-object p0
.end method

.method public static final synthetic access$getScrollUpdated$p(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;)Z
    .locals 0

    .line 38
    iget-boolean p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->scrollUpdated:Z

    return p0
.end method

.method public static final synthetic access$setScrollUpdated$p(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Z)V
    .locals 0

    .line 38
    iput-boolean p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->scrollUpdated:Z

    return-void
.end method

.method private final addCustomPacksSection(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;Ljava/lang/String;Ljava/util/List;Z)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Lorg/telegram/ui/Components/UniversalAdapter;",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;Z)V"
        }
    .end annotation

    .line 151
    invoke-virtual {p2}, Lorg/telegram/ui/Components/UniversalAdapter;->whiteSectionStart()V

    .line 152
    invoke-static {p3}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p3

    invoke-virtual {p1, p3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    if-eqz p5, :cond_0

    .line 153
    invoke-virtual {p2}, Lorg/telegram/ui/Components/UniversalAdapter;->reorderSectionStart()I

    .line 154
    :cond_0
    invoke-interface {p4}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p3

    :cond_1
    :goto_0
    invoke-interface {p3}, Ljava/util/Iterator;->hasNext()Z

    move-result p4

    if-eqz p4, :cond_3

    invoke-interface {p3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p4

    check-cast p4, Ljava/lang/String;

    const/4 v0, 0x2

    const/4 v1, 0x0

    .line 155
    const-string v2, "base."

    const/4 v3, 0x0

    invoke-static {p4, v2, v3, v0, v1}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 156
    invoke-direct {p0, p4}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->getPackById(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object p4

    if-nez p4, :cond_2

    goto :goto_0

    .line 157
    :cond_2
    invoke-static {p4}, Lcom/exteragram/messenger/icons/ui/components/IconPackCell$Factory;->asIconPackCell(Lcom/exteragram/messenger/icons/IconPack;)Lorg/telegram/ui/Components/UItem;

    move-result-object p4

    invoke-virtual {p4, p5}, Lorg/telegram/ui/Components/UItem;->setReordering(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p4

    invoke-virtual {p1, p4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_3
    if-eqz p5, :cond_4

    .line 159
    invoke-virtual {p2}, Lorg/telegram/ui/Components/UniversalAdapter;->reorderSectionEnd()V

    .line 160
    :cond_4
    invoke-virtual {p2}, Lorg/telegram/ui/Components/UniversalAdapter;->whiteSectionEnd()V

    return-void
.end method

.method private final confirmDeletePack(Lcom/exteragram/messenger/icons/IconPack;)V
    .locals 2

    .line 224
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v0, v1, p0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 225
    sget p0, Lorg/telegram/messenger/R$string;->DeletePack:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 226
    sget v0, Lorg/telegram/messenger/R$string;->DeletePackInfo:I

    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getName()Ljava/lang/String;

    move-result-object v1

    filled-new-array {v1}, [Ljava/lang/Object;

    move-result-object v1

    invoke-static {v0, v1}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 227
    sget v0, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda5;

    invoke-direct {v1, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/icons/IconPack;)V

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 228
    sget p1, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p0

    .line 229
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object p0

    .line 230
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->show()V

    const/4 p1, -0x1

    .line 231
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object p0

    instance-of p1, p0, Landroid/widget/TextView;

    if-eqz p1, :cond_0

    move-object v0, p0

    check-cast v0, Landroid/widget/TextView;

    :cond_0
    if-eqz v0, :cond_1

    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedBold:I

    invoke-static {p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p0

    invoke-virtual {v0, p0}, Landroid/widget/TextView;->setTextColor(I)V

    :cond_1
    return-void
.end method

.method private final countCustomPacks(Ljava/util/List;)I
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)I"
        }
    .end annotation

    .line 164
    check-cast p1, Ljava/lang/Iterable;

    .line 1834
    instance-of v0, p1, Ljava/util/Collection;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    move-object v0, p1

    check-cast v0, Ljava/util/Collection;

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    return v1

    .line 1836
    :cond_0
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    move v0, v1

    :cond_1
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    const/4 v3, 0x2

    const/4 v4, 0x0

    .line 164
    const-string v5, "base."

    invoke-static {v2, v5, v1, v3, v4}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_1

    invoke-direct {p0, v2}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->getPackById(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object v2

    if-eqz v2, :cond_1

    add-int/lit8 v0, v0, 0x1

    if-gez v0, :cond_1

    .line 1836
    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwCountOverflow()V

    goto :goto_0

    :cond_2
    return v0
.end method

.method private final getPackById(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;
    .locals 3

    const/4 p0, 0x2

    const/4 v0, 0x0

    .line 167
    const-string v1, "base."

    const/4 v2, 0x0

    invoke-static {p1, v1, v2, p0, v0}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    sget-object p0, Lcom/exteragram/messenger/icons/BaseIconPacks;->INSTANCE:Lcom/exteragram/messenger/icons/BaseIconPacks;

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/icons/BaseIconPacks;->getBasePack(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object p0

    return-object p0

    :cond_0
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/icons/IconManager;->findPackById(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object p0

    return-object p0
.end method

.method private final openEditor(Lcom/exteragram/messenger/icons/IconPack;)V
    .locals 2

    .line 204
    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/exteragram/messenger/ExteraConfig;->setEditingIconPackId(Ljava/lang/String;)V

    .line 205
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/LaunchActivity;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/exteragram/messenger/icons/ui/picker/IconPickerController;->setActive(Lorg/telegram/ui/LaunchActivity;Z)V

    .line 206
    new-instance v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;-><init>(Lcom/exteragram/messenger/icons/IconPack;)V

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void
.end method

.method private final sharePack(Lcom/exteragram/messenger/icons/IconPack;)V
    .locals 2

    .line 210
    sget-object v0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda7;

    invoke-direct {v1, p1, p0}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/icons/IconPack;Lcom/exteragram/messenger/icons/ui/IconPacksActivity;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private final updateConfigFromReorder(ILjava/util/ArrayList;)V
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;)V"
        }
    .end annotation

    .line 1642
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 1915
    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    move v3, v2

    :cond_0
    :goto_0
    const/4 v4, 0x0

    if-ge v3, v1, :cond_3

    invoke-virtual {p2, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v3, v3, 0x1

    .line 1651
    check-cast v5, Lorg/telegram/ui/Components/UItem;

    .line 89
    iget-object v5, v5, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of v6, v5, Lcom/exteragram/messenger/icons/IconPack;

    if-eqz v6, :cond_1

    check-cast v5, Lcom/exteragram/messenger/icons/IconPack;

    goto :goto_1

    :cond_1
    move-object v5, v4

    :goto_1
    if-eqz v5, :cond_2

    invoke-virtual {v5}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v4

    :cond_2
    if-eqz v4, :cond_0

    .line 1651
    invoke-interface {v0, v4}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 90
    :cond_3
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p2

    invoke-direct {p0, p2}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->countCustomPacks(Ljava/util/List;)I

    move-result p2

    const/4 v1, 0x1

    if-le p2, v1, :cond_4

    if-nez p1, :cond_4

    move p1, v1

    goto :goto_2

    :cond_4
    move p1, v2

    :goto_2
    if-eqz p1, :cond_7

    .line 93
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p2

    .line 296
    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result v3

    move v5, v2

    :cond_5
    if-ge v5, v3, :cond_6

    invoke-virtual {p2, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    move-object v7, v6

    check-cast v7, Ljava/lang/String;

    .line 93
    const-string v8, "base."

    const/4 v9, 0x2

    invoke-static {v7, v8, v2, v9, v4}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_5

    move-object v4, v6

    :cond_6
    check-cast v4, Ljava/lang/String;

    .line 94
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2}, Ljava/util/ArrayList;->clear()V

    .line 95
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    if-eqz v4, :cond_8

    .line 97
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_3

    .line 100
    :cond_7
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2}, Ljava/util/ArrayList;->clear()V

    .line 101
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 104
    :cond_8
    :goto_3
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->saveIconPacksLayout()V

    if-eqz p1, :cond_a

    .line 106
    iget-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->reorderRunnable:Ljava/lang/Runnable;

    if-eqz p1, :cond_9

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 107
    :cond_9
    new-instance p1, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda6;

    invoke-direct {p1}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda6;-><init>()V

    .line 108
    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->reorderRunnable:Ljava/lang/Runnable;

    const-wide/16 v2, 0x1f4

    .line 109
    invoke-static {p1, v2, v3}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    .line 111
    :cond_a
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz p1, :cond_b

    .line 112
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_b

    invoke-virtual {p0, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_b
    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 4

    .line 66
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object v0

    .line 68
    new-instance v1, Lorg/telegram/ui/Components/FragmentFloatingButton;

    iget-object v2, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v1, p1, v2}, Lorg/telegram/ui/Components/FragmentFloatingButton;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    iput-object v1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->floatingButton:Lorg/telegram/ui/Components/FragmentFloatingButton;

    .line 69
    iget-object v1, v1, Lorg/telegram/ui/Components/FragmentFloatingButton;->imageView:Lorg/telegram/ui/Components/RLottieImageView;

    sget v2, Lorg/telegram/messenger/R$drawable;->msg_add:I

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/RLottieImageView;->setImageResource(I)V

    .line 70
    iget-object v1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->floatingButton:Lorg/telegram/ui/Components/FragmentFloatingButton;

    const/4 v2, 0x0

    if-nez v1, :cond_0

    move-object v1, v2

    :cond_0
    sget v3, Lorg/telegram/messenger/R$string;->Add:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 71
    iget-object v1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->floatingButton:Lorg/telegram/ui/Components/FragmentFloatingButton;

    if-nez v1, :cond_1

    move-object v1, v2

    :cond_1
    new-instance v3, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda3;

    invoke-direct {v3, p0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Landroid/content/Context;)V

    invoke-virtual {v1, v3}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 72
    instance-of p1, v0, Landroid/widget/FrameLayout;

    if-eqz p1, :cond_2

    move-object p1, v0

    check-cast p1, Landroid/widget/FrameLayout;

    goto :goto_0

    :cond_2
    move-object p1, v2

    :goto_0
    if-eqz p1, :cond_4

    iget-object v1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->floatingButton:Lorg/telegram/ui/Components/FragmentFloatingButton;

    if-nez v1, :cond_3

    goto :goto_1

    :cond_3
    move-object v2, v1

    :goto_1
    invoke-static {}, Lorg/telegram/ui/Components/FragmentFloatingButton;->createDefaultLayoutParams()Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v1

    invoke-virtual {p1, v2, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 74
    :cond_4
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    const/4 v1, 0x1

    invoke-virtual {p1, v1}, Lorg/telegram/ui/Components/UniversalRecyclerView;->allowReorder(Z)V

    .line 75
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    invoke-virtual {p1, v1}, Lorg/telegram/ui/Components/UniversalRecyclerView;->setReorderHandleOnly(Z)V

    .line 76
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    new-instance v1, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;)V

    invoke-virtual {p1, v1}, Lorg/telegram/ui/Components/UniversalRecyclerView;->listenReorder(Lorg/telegram/messenger/Utilities$Callback2;)V

    .line 77
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    new-instance v1, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$createView$3;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$createView$3;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;)V

    invoke-virtual {p1, v1}, Lorg/telegram/ui/Components/RecyclerListView;->setOnScrollListener(Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;)V

    return-object v0
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 55
    sget p2, Lorg/telegram/messenger/NotificationCenter;->iconPackUpdated:I

    if-ne p1, p2, :cond_0

    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz p1, :cond_0

    .line 56
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_0

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
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

    .line 119
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object v2

    .line 296
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v6, 0x0

    move v4, v6

    :cond_0
    const/4 v5, 0x0

    if-ge v4, v3, :cond_1

    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v4, v4, 0x1

    move-object v8, v7

    check-cast v8, Ljava/lang/String;

    .line 119
    const-string v9, "base."

    const/4 v10, 0x2

    invoke-static {v8, v9, v6, v10, v5}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_0

    move-object v5, v7

    :cond_1
    check-cast v5, Ljava/lang/String;

    const-string v2, "base.default"

    if-nez v5, :cond_2

    move-object v5, v2

    .line 121
    :cond_2
    invoke-virtual {p2}, Lorg/telegram/ui/Components/UniversalAdapter;->whiteSectionStart()V

    .line 122
    sget v3, Lorg/telegram/messenger/R$string;->BasePacks:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v3

    invoke-virtual {p1, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 123
    const-string v3, "base.solar"

    const-string v4, "base.remix"

    filled-new-array {v2, v3, v4}, [Ljava/lang/String;

    move-result-object v2

    move v3, v6

    :goto_0
    const/4 v4, 0x3

    if-ge v3, v4, :cond_4

    aget-object v4, v2, v3

    .line 124
    invoke-direct {p0, v4}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->getPackById(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object v7

    if-nez v7, :cond_3

    goto :goto_1

    .line 125
    :cond_3
    invoke-static {v7}, Lcom/exteragram/messenger/icons/ui/components/IconPackCell$Factory;->asIconPackCell(Lcom/exteragram/messenger/icons/IconPack;)Lorg/telegram/ui/Components/UItem;

    move-result-object v7

    .line 126
    invoke-static {v4, v5}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v4

    iput-boolean v4, v7, Lorg/telegram/ui/Components/UItem;->checked:Z

    .line 127
    invoke-virtual {p1, v7}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :goto_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 129
    :cond_4
    invoke-virtual {p2}, Lorg/telegram/ui/Components/UniversalAdapter;->whiteSectionEnd()V

    .line 130
    sget v2, Lorg/telegram/messenger/R$string;->BaseIconPackInfo:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 132
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object v2

    invoke-direct {p0, v2}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->countCustomPacks(Ljava/util/List;)I

    move-result v7

    const/4 v8, 0x1

    if-lez v7, :cond_6

    .line 134
    sget v2, Lorg/telegram/messenger/R$string;->EnabledPacks:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object v4

    if-le v7, v8, :cond_5

    move v5, v8

    :goto_2
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    goto :goto_3

    :cond_5
    move v5, v6

    goto :goto_2

    :goto_3
    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->addCustomPacksSection(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;Ljava/lang/String;Ljava/util/List;Z)V

    .line 137
    :cond_6
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object v2

    invoke-direct {p0, v2}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->countCustomPacks(Ljava/util/List;)I

    move-result v9

    if-lez v9, :cond_9

    if-lez v7, :cond_7

    .line 140
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 142
    :cond_7
    sget v2, Lorg/telegram/messenger/R$string;->AllPacks:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object v4

    if-le v9, v8, :cond_8

    move v5, v8

    :goto_4
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    goto :goto_5

    :cond_8
    move v5, v6

    goto :goto_4

    :goto_5
    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->addCustomPacksSection(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;Ljava/lang/String;Ljava/util/List;Z)V

    :cond_9
    if-gtz v7, :cond_b

    if-lez v9, :cond_a

    goto :goto_6

    :cond_a
    return-void

    .line 146
    :cond_b
    :goto_6
    sget v0, Lorg/telegram/messenger/R$string;->IconPacksHint:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 116
    sget p0, Lorg/telegram/messenger/R$string;->IconPacks:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 170
    iget-object p1, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p2, p1, Lcom/exteragram/messenger/icons/IconPack;

    const/4 p3, 0x0

    if-eqz p2, :cond_0

    check-cast p1, Lcom/exteragram/messenger/icons/IconPack;

    goto :goto_0

    :cond_0
    move-object p1, p3

    :goto_0
    if-eqz p1, :cond_5

    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_1

    goto :goto_2

    .line 172
    :cond_1
    const-string p2, "base."

    const/4 p4, 0x2

    const/4 p5, 0x0

    invoke-static {p1, p2, p5, p4, p3}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_2

    .line 173
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p2

    new-instance p3, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda8;

    invoke-direct {p3}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda8;-><init>()V

    invoke-static {p2, p3}, Lkotlin/collections/CollectionsKt;->removeAll(Ljava/util/List;Lkotlin/jvm/functions/Function1;)Z

    .line 174
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 175
    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_3

    .line 176
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 177
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_4

    .line 178
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, p5, p1}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    goto :goto_1

    .line 180
    :cond_3
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_4

    .line 181
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 182
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2, p5, p1}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 184
    :cond_4
    :goto_1
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->saveIconPacksLayout()V

    .line 185
    sget-object p1, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    const/4 p2, 0x1

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/icons/IconManager;->initialize(Z)V

    .line 186
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_5

    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_5
    :goto_2
    return-void
.end method

.method public onFragmentCreate()Z
    .locals 2

    .line 45
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->iconPackUpdated:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 46
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentCreate()Z

    move-result p0

    return p0
.end method

.method public onFragmentDestroy()V
    .locals 2

    .line 50
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    .line 51
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->iconPackUpdated:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    return-void
.end method

.method public onInsets(IIII)V
    .locals 2

    .line 61
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->floatingButton:Lorg/telegram/ui/Components/FragmentFloatingButton;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :cond_0
    int-to-float v1, p4

    neg-float v1, v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/FragmentFloatingButton;->setTranslationY(F)V

    .line 62
    invoke-super {p0, p1, p2, p3, p4}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onInsets(IIII)V

    return-void
.end method

.method public onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z
    .locals 3

    .line 190
    iget-object v0, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of v1, v0, Lcom/exteragram/messenger/icons/IconPack;

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    check-cast v0, Lcom/exteragram/messenger/icons/IconPack;

    goto :goto_0

    :cond_0
    move-object v0, v2

    :goto_0
    if-nez v0, :cond_1

    invoke-super/range {p0 .. p5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z

    move-result p0

    return p0

    .line 191
    :cond_1
    invoke-virtual {v0}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object p1

    const-string p3, "base."

    const/4 p4, 0x2

    const/4 p5, 0x0

    invoke-static {p1, p3, p5, p4, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_2

    return p5

    .line 193
    :cond_2
    invoke-static {p0, p2}, Lorg/telegram/ui/Components/ItemOptions;->makeOptions(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/View;)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object p1

    .line 194
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object p3

    invoke-virtual {v0}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object p4

    invoke-virtual {p3, p4}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p3

    sget p4, Lorg/telegram/messenger/R$drawable;->msg_edit:I

    sget p5, Lorg/telegram/messenger/R$string;->Edit:I

    invoke-static {p5}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p5

    new-instance v1, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0, v0}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Lcom/exteragram/messenger/icons/IconPack;)V

    invoke-virtual {p1, p3, p4, p5, v1}, Lorg/telegram/ui/Components/ItemOptions;->addIf(ZILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 195
    sget p3, Lorg/telegram/messenger/R$drawable;->msg_share:I

    sget p4, Lorg/telegram/messenger/R$string;->ShareFile:I

    invoke-static {p4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p4

    new-instance p5, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda1;

    invoke-direct {p5, p0, v0}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Lcom/exteragram/messenger/icons/IconPack;)V

    invoke-virtual {p1, p3, p4, p5}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 196
    sget p3, Lorg/telegram/messenger/R$drawable;->msg_delete:I

    sget p4, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {p4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p4

    new-instance p5, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda2;

    invoke-direct {p5, p0, v0}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Lcom/exteragram/messenger/icons/IconPack;)V

    const/4 v0, 0x1

    invoke-virtual {p1, p3, p4, v0, p5}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;ZLjava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 197
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/RecyclerListView;->getClipBackground(Landroid/view/View;)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    invoke-virtual {p1, p0}, Lorg/telegram/ui/Components/ItemOptions;->setScrimViewBackground(Landroid/graphics/drawable/Drawable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 198
    invoke-virtual {p1}, Lorg/telegram/ui/Components/ItemOptions;->show()Lorg/telegram/ui/Components/ItemOptions;

    return v0
.end method
