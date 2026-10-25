.class public final Lcom/exteragram/messenger/notifications/AccountNotificationsActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u0006\u001a\u00020\u0007H\u0016J\u001e\u0010\u0008\u001a\u00020\t2\u000c\u0010\n\u001a\u0008\u0012\u0004\u0012\u00020\u000c0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0014J0\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000c2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"
    }
    d2 = {
        "Lcom/exteragram/messenger/notifications/AccountNotificationsActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "shiftDp",
        "",
        "getTitle",
        "",
        "fillItems",
        "",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "Landroid/view/View;",
        "position",
        "",
        "x",
        "y",
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
        "SMAP\nAccountNotificationsActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AccountNotificationsActivity.kt\ncom/exteragram/messenger/notifications/AccountNotificationsActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,51:1\n1915#2,2:52\n*S KotlinDebug\n*F\n+ 1 AccountNotificationsActivity.kt\ncom/exteragram/messenger/notifications/AccountNotificationsActivity\n*L\n27#1:52,2\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$Companion;


# instance fields
.field private shiftDp:F


# direct methods
.method public static $r8$lambda$0zg-YvVaHRH7kK1NsE-XFc5V7Yw(IZ)V
    .locals 1

    .line 48
    sget-object v0, Lcom/exteragram/messenger/notifications/AccountNotifications;->INSTANCE:Lcom/exteragram/messenger/notifications/AccountNotifications;

    invoke-virtual {v0, p0, p1}, Lcom/exteragram/messenger/notifications/AccountNotifications;->setEnabled(IZ)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity;->Companion:Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 15
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    const/high16 v0, -0x3fc00000    # -3.0f

    .line 21
    iput v0, p0, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity;->shiftDp:F

    return-void
.end method


# virtual methods
.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 2
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

    .line 26
    sget p0, Lorg/telegram/messenger/R$string;->ShowNotificationsFor:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 27
    invoke-static {}, Lcom/exteragram/messenger/notifications/AccountNotifications;->getAccounts()Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/Number;

    invoke-virtual {p2}, Ljava/lang/Number;->intValue()I

    move-result p2

    add-int/lit16 v0, p2, 0x3e8

    .line 29
    invoke-static {p2}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v1

    invoke-virtual {v1}, Lorg/telegram/messenger/UserConfig;->getCurrentUser()Lorg/telegram/tgnet/TLRPC$User;

    move-result-object v1

    invoke-static {v0, v1}, Lorg/telegram/ui/Components/UItem;->asUserCheckbox(ILorg/telegram/tgnet/TLObject;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    .line 30
    sget v1, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    if-eq p2, v1, :cond_1

    sget-object v1, Lcom/exteragram/messenger/notifications/AccountNotifications;->INSTANCE:Lcom/exteragram/messenger/notifications/AccountNotifications;

    invoke-virtual {v1, p2}, Lcom/exteragram/messenger/notifications/AccountNotifications;->isEnabled(I)Z

    move-result p2

    if-eqz p2, :cond_0

    goto :goto_1

    :cond_0
    const/4 p2, 0x0

    goto :goto_2

    :cond_1
    :goto_1
    const/4 p2, 0x1

    :goto_2
    invoke-virtual {v0, p2}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 28
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 33
    :cond_2
    sget p0, Lorg/telegram/messenger/R$string;->AccountNotificationsInfo:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 23
    sget p0, Lorg/telegram/messenger/R$string;->AccountNotifications:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 37
    iget p3, p1, Lorg/telegram/ui/Components/ListView/AdapterWithDiffUtils$Item;->viewType:I

    const/16 p4, 0x25

    if-eq p3, p4, :cond_0

    return-void

    .line 40
    :cond_0
    iget p3, p1, Lorg/telegram/ui/Components/UItem;->id:I

    add-int/lit16 p3, p3, -0x3e8

    .line 41
    sget p4, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    if-ne p3, p4, :cond_1

    .line 42
    iget p1, p0, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity;->shiftDp:F

    neg-float p1, p1

    iput p1, p0, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity;->shiftDp:F

    .line 43
    invoke-static {p2, p1}, Lorg/telegram/messenger/AndroidUtilities;->shakeViewSpring(Landroid/view/View;F)V

    .line 44
    sget-object p1, Lorg/telegram/messenger/BotWebViewVibrationEffect;->APP_ERROR:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    invoke-virtual {p1}, Lorg/telegram/messenger/BotWebViewVibrationEffect;->vibrate()V

    .line 45
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$raw;->info:I

    sget p2, Lorg/telegram/messenger/R$string;->AccountNotificationsCurrent:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 48
    :cond_1
    new-instance p2, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$$ExternalSyntheticLambda0;

    invoke-direct {p2, p3}, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$$ExternalSyntheticLambda0;-><init>(I)V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void
.end method
