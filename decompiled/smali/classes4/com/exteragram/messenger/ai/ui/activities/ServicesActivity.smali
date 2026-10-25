.class public final Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\r\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\u0007\n\u0002\u0008\u0006\u0018\u0000 ;2\u00020\u00012\u00020\u0002:\u0001;B\u0007\u00a2\u0006\u0004\u0008\u0003\u0010\u0004J\u0008\u0010\u000c\u001a\u00020\rH\u0016J\u0008\u0010\u000e\u001a\u00020\u000fH\u0016J\u0008\u0010\u0010\u001a\u00020\u000fH\u0016J\u0008\u0010\u0011\u001a\u00020\u000fH\u0016J5\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0016\u0010\u0016\u001a\u000c\u0012\u0008\u0008\u0001\u0012\u0004\u0018\u00010\u00180\u0017\"\u0004\u0018\u00010\u0018H\u0016\u00a2\u0006\u0002\u0010\u0019J\u0008\u0010\u001a\u001a\u00020\u001bH\u0016J\u0008\u0010\u001c\u001a\u00020\rH\u0014J\u001e\u0010\u001d\u001a\u00020\u000f2\u000c\u0010\u001e\u001a\u0008\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"H\u0014J\u0008\u0010#\u001a\u00020\u000bH\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0002J\u0008\u0010\'\u001a\u00020\u000fH\u0002J\u0010\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020-H\u0002J\u0016\u0010.\u001a\u00020\u000f2\u000c\u0010/\u001a\u0008\u0012\u0004\u0012\u00020\u000f00H\u0002J\u0008\u00101\u001a\u00020\u000fH\u0002J0\u00102\u001a\u00020\u000f2\u0006\u00103\u001a\u00020 2\u0006\u0010,\u001a\u00020-2\u0006\u00104\u001a\u00020\u00142\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206H\u0014J0\u00108\u001a\u00020\r2\u0006\u00103\u001a\u00020 2\u0006\u0010,\u001a\u00020-2\u0006\u00104\u001a\u00020\u00142\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206H\u0014J\u0010\u00109\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010:\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0008X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0008X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"
    }
    d2 = {
        "Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;",
        "<init>",
        "()V",
        "statusPoll",
        "Ljava/lang/Runnable;",
        "newServiceCircle",
        "Landroid/graphics/drawable/Drawable;",
        "newServicePlus",
        "newServiceDrawable",
        "Lorg/telegram/ui/Components/CombinedDrawable;",
        "onFragmentCreate",
        "",
        "onResume",
        "",
        "onPause",
        "onFragmentDestroy",
        "didReceivedNotification",
        "id",
        "",
        "account",
        "args",
        "",
        "",
        "(II[Ljava/lang/Object;)V",
        "getTitle",
        "",
        "needHideTitle",
        "fillItems",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "getNewServiceDrawable",
        "applyOnDeviceBadge",
        "",
        "title",
        "reloadServices",
        "onDeviceUnavailable",
        "service",
        "Lcom/exteragram/messenger/ai/data/Service;",
        "showOnDeviceOptions",
        "view",
        "Landroid/view/View;",
        "changeModelConfig",
        "change",
        "Lkotlin/Function0;",
        "openAiCoreSettings",
        "onClick",
        "item",
        "position",
        "x",
        "",
        "y",
        "onLongClick",
        "confirmDeleteService",
        "deleteService",
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
        "SMAP\nServicesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServicesActivity.kt\ncom/exteragram/messenger/ai/ui/activities/ServicesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,290:1\n1924#2,3:291\n*S KotlinDebug\n*F\n+ 1 ServicesActivity.kt\ncom/exteragram/messenger/ai/ui/activities/ServicesActivity\n*L\n111#1:291,3\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$Companion;


# instance fields
.field private newServiceCircle:Landroid/graphics/drawable/Drawable;

.field private newServiceDrawable:Lorg/telegram/ui/Components/CombinedDrawable;

.field private newServicePlus:Landroid/graphics/drawable/Drawable;

.field private final statusPoll:Ljava/lang/Runnable;


# direct methods
.method public static $r8$lambda$3oj12F1tQPr8C2eulDW8tSsMxYs(Lcom/exteragram/messenger/ai/data/Service;Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V
    .locals 3

    .line 252
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Service;->getUrl()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Service;->getModel()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Service;->getKey()Ljava/lang/String;

    move-result-object p0

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "\n"

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 253
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->TextCopied:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyBulletin(Ljava/lang/String;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_0
    return-void
.end method

.method public static $r8$lambda$FI2TIpAwsCT0r57J4GG-ovSwgvg(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 0

    .line 255
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->confirmDeleteService(Lcom/exteragram/messenger/ai/data/Service;)V

    return-void
.end method

.method public static $r8$lambda$J5FwvCCb6gvNmrvFa6BnEgpawXU(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V
    .locals 1

    .line 180
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getOnDeviceReasoning()Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->setOnDeviceReasoning(Z)V

    .line 181
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->reloadServices()V

    return-void
.end method

.method public static $r8$lambda$K-j6WNT5KP23GrFAaw4DS1f_4DE(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V
    .locals 0

    .line 192
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->openAiCoreSettings()V

    return-void
.end method

.method public static $r8$lambda$QVuHMXK4Xz_lIjCUAFt3M-kEt1w(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V
    .locals 0

    .line 227
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->openAiCoreSettings()V

    return-void
.end method

.method public static $r8$lambda$TuZnTxTCXXoI5onXdbftkEf9SkM(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V
    .locals 1

    .line 191
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refresh(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$V4YpQSHLGGUCXy_MEMp44PF3JmM(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->reloadServices()V

    return-void
.end method

.method public static $r8$lambda$Vm-0Gcg3EpLJLd-sI33sFpym5jo(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V
    .locals 1

    .line 188
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda12;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda12;-><init>()V

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->changeModelConfig(Lkotlin/jvm/functions/Function0;)V

    return-void
.end method

.method public static $r8$lambda$iJt0lAxd7qdQnUfnJPryceLwHYA()Lkotlin/Unit;
    .locals 1

    .line 185
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getOnDevicePreviewModel()Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->setOnDevicePreviewModel(Z)V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0
.end method

.method public static $r8$lambda$tzTFq1IWkrg8JKLICcBXJc5XXjQ(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V
    .locals 1

    .line 185
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda13;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda13;-><init>()V

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->changeModelConfig(Lkotlin/jvm/functions/Function0;)V

    return-void
.end method

.method public static $r8$lambda$vEJSsXMOGR-duS4ls0mYqdidfJQ()Lkotlin/Unit;
    .locals 1

    .line 188
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getOnDeviceFastModel()Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->setOnDeviceFastModel(Z)V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0
.end method

.method public static $r8$lambda$w7MLWq2dmqoCm_oD7NBh7uP8LM4(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 1

    .line 250
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;-><init>(Lcom/exteragram/messenger/ai/data/Service;)V

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void
.end method

.method public static $r8$lambda$wMznpH1eB0FbSVLkAHpKikm25ho(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 0

    .line 229
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->onDeviceUnavailable(Lcom/exteragram/messenger/ai/data/Service;)V

    return-void
.end method

.method public static $r8$lambda$wZTLgFZV023pJJhY6kHr6Mb9e7o(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;Lcom/exteragram/messenger/ai/data/Service;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 268
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->deleteService(Lcom/exteragram/messenger/ai/data/Service;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->Companion:Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 52
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    .line 60
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$statusPoll$1;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$statusPoll$1;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->statusPoll:Ljava/lang/Runnable;

    return-void
.end method

.method public static final synthetic access$reloadServices(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V
    .locals 0

    .line 52
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->reloadServices()V

    return-void
.end method

.method private final applyOnDeviceBadge(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
    .locals 2

    .line 142
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isDownloadInProgress()Z

    move-result p0

    if-eqz p0, :cond_1

    .line 143
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getDownloadPercent()I

    move-result p0

    if-ltz p0, :cond_0

    .line 144
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, "%"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    sget p0, Lorg/telegram/messenger/R$string;->AIOnDeviceBadgeDownloading:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    .line 145
    :goto_0
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chats_unreadCounterMuted:I

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_chats_unreadCounterText:I

    invoke-static {p1, p0, v0, v1}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->applyBadge(Ljava/lang/CharSequence;Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 147
    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->needsDownload()Z

    move-result p0

    if-eqz p0, :cond_2

    .line 148
    sget p0, Lorg/telegram/messenger/R$string;->AIOnDeviceBadgeDownload:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chats_unreadCounterMuted:I

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_chats_unreadCounterText:I

    invoke-static {p1, p0, v0, v1}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->applyBadge(Ljava/lang/CharSequence;Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 149
    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isReady()Z

    move-result p0

    if-eqz p0, :cond_3

    .line 150
    sget p0, Lorg/telegram/messenger/R$string;->AIOnDeviceBadgeReady:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_addButton:I

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_buttonText:I

    invoke-static {p1, p0, v0, v1}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->applyBadge(Ljava/lang/CharSequence;Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    .line 152
    :cond_3
    sget p0, Lorg/telegram/messenger/R$string;->AIOnDeviceBadgeUnavailable:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_chats_unreadCounterMuted:I

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_chats_unreadCounterText:I

    invoke-static {p1, p0, v0, v1}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->applyBadge(Ljava/lang/CharSequence;Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method

.method private final changeModelConfig(Lkotlin/jvm/functions/Function0;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 200
    invoke-interface {p1}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    .line 201
    new-instance p1, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda0;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-static {p1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->onModelConfigChanged(Ljava/lang/Runnable;)V

    .line 202
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->statusPoll:Ljava/lang/Runnable;

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 203
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->statusPoll:Ljava/lang/Runnable;

    const-wide/16 v0, 0x1388

    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method

.method private final confirmDeleteService(Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 3

    .line 264
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 265
    :cond_0
    new-instance v1, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-direct {v1, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 266
    sget v0, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 267
    sget v1, Lorg/telegram/messenger/R$string;->DeleteServiceInfo:I

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Service;->getShortModel()Ljava/lang/String;

    move-result-object v2

    filled-new-array {v2}, [Ljava/lang/Object;

    move-result-object v2

    invoke-static {v1, v2}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 268
    sget v1, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda6;

    invoke-direct {v2, p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;Lcom/exteragram/messenger/ai/data/Service;)V

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p1

    .line 269
    sget v0, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p1

    .line 270
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object p1

    .line 271
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    const/4 p0, -0x1

    .line 272
    invoke-virtual {p1, p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object p0

    instance-of p1, p0, Landroid/widget/TextView;

    if-eqz p1, :cond_1

    move-object v1, p0

    check-cast v1, Landroid/widget/TextView;

    :cond_1
    if-eqz v1, :cond_2

    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedBold:I

    invoke-static {p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p0

    invoke-virtual {v1, p0}, Landroid/widget/TextView;->setTextColor(I)V

    :cond_2
    :goto_0
    return-void
.end method

.method private final deleteService(Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 2

    .line 276
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Service;->isSelected()Z

    move-result v0

    .line 277
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v1

    invoke-virtual {v1, p1}, Lcom/exteragram/messenger/ai/AiController;->removeService(Lcom/exteragram/messenger/ai/data/Service;)Z

    move-result p1

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    if-eqz v0, :cond_1

    .line 280
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->clearSelectedService()V

    .line 281
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/AiController;->isServicesEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    .line 282
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/AiController;->getAll()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/ai/data/Service;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServices(Lcom/exteragram/messenger/ai/data/Service;)V

    .line 286
    :cond_1
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->servicesUpdated:I

    new-array p1, p1, [Ljava/lang/Object;

    invoke-virtual {v0, v1, p1}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 287
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz p1, :cond_2

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_2

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_2
    :goto_0
    return-void
.end method

.method private final getNewServiceDrawable()Lorg/telegram/ui/Components/CombinedDrawable;
    .locals 5

    .line 125
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->newServiceDrawable:Lorg/telegram/ui/Components/CombinedDrawable;

    const/4 v1, 0x0

    if-nez v0, :cond_2

    .line 126
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v2, Lorg/telegram/messenger/R$drawable;->poll_add_circle:I

    invoke-virtual {v0, v2}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->newServiceCircle:Landroid/graphics/drawable/Drawable;

    .line 127
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v2, Lorg/telegram/messenger/R$drawable;->poll_add_plus:I

    invoke-virtual {v0, v2}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->newServicePlus:Landroid/graphics/drawable/Drawable;

    .line 128
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->newServiceCircle:Landroid/graphics/drawable/Drawable;

    if-nez v2, :cond_0

    move-object v2, v1

    :cond_0
    if-nez v0, :cond_1

    move-object v0, v1

    :cond_1
    new-instance v3, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$getNewServiceDrawable$1;

    invoke-direct {v3, v2, v0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$getNewServiceDrawable$1;-><init>(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V

    iput-object v3, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->newServiceDrawable:Lorg/telegram/ui/Components/CombinedDrawable;

    .line 136
    :cond_2
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->newServiceCircle:Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_3

    move-object v0, v1

    :cond_3
    new-instance v2, Landroid/graphics/PorterDuffColorFilter;

    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_switchTrackChecked:I

    invoke-virtual {p0, v3}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v3

    sget-object v4, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v2, v3, v4}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v2}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 137
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->newServicePlus:Landroid/graphics/drawable/Drawable;

    if-nez v0, :cond_4

    move-object v0, v1

    :cond_4
    new-instance v2, Landroid/graphics/PorterDuffColorFilter;

    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_checkboxCheck:I

    invoke-virtual {p0, v3}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v3

    invoke-direct {v2, v3, v4}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v2}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 138
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->newServiceDrawable:Lorg/telegram/ui/Components/CombinedDrawable;

    if-nez p0, :cond_5

    return-object v1

    :cond_5
    return-object p0
.end method

.method private final onDeviceUnavailable(Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 1

    .line 162
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 163
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->reloadServices()V

    .line 165
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isReady()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 166
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Service;->isSelected()Z

    move-result v0

    if-nez v0, :cond_1

    .line 167
    invoke-static {p1}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServices(Lcom/exteragram/messenger/ai/data/Service;)V

    .line 168
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->reloadServices()V

    :cond_1
    :goto_0
    return-void

    .line 171
    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->needsDownload()Z

    move-result p1

    if-eqz p1, :cond_3

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs;->showDownloadDialog(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    return-void

    .line 172
    :cond_3
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceInfo:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createErrorBulletin(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method private final openAiCoreSettings()V
    .locals 2

    .line 208
    :try_start_0
    new-instance v0, Landroid/content/Intent;

    const-string v1, "android.settings.APPLICATION_DETAILS_SETTINGS"

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 209
    const-string v1, "package:com.google.android.aicore"

    invoke-static {v1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setData(Landroid/net/Uri;)Landroid/content/Intent;

    const/high16 v1, 0x10000000

    .line 210
    invoke-virtual {v0, v1}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 211
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 213
    :catch_0
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget v0, Lorg/telegram/messenger/R$string;->AIOnDeviceManageUnavailable:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/BulletinFactory;->createErrorBulletin(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method private final reloadServices()V
    .locals 1

    .line 156
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/AiController;->loadServices()V

    .line 157
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-nez v0, :cond_0

    goto :goto_0

    .line 158
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_1

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_1
    :goto_0
    return-void
.end method

.method private final showOnDeviceOptions(Landroid/view/View;)V
    .locals 5

    .line 177
    invoke-static {p0, p1}, Lorg/telegram/ui/Components/ItemOptions;->makeOptions(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/View;)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    .line 178
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isThinkingSupported()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 179
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getOnDeviceReasoning()Z

    move-result v1

    sget v2, Lorg/telegram/messenger/R$drawable;->msg_discuss:I

    sget v3, Lorg/telegram/messenger/R$string;->AIReasoning:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    new-instance v4, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda7;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-virtual {v0, v1, v2, v3, v4}, Lorg/telegram/ui/Components/ItemOptions;->addChecked(ZILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 184
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getOnDevicePreviewModel()Z

    move-result v1

    sget v2, Lorg/telegram/messenger/R$drawable;->msg_fave:I

    sget v3, Lorg/telegram/messenger/R$string;->AIOnDevicePreviewModel:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    new-instance v4, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda8;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda8;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-virtual {v0, v1, v2, v3, v4}, Lorg/telegram/ui/Components/ItemOptions;->addChecked(ZILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 187
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getOnDeviceFastModel()Z

    move-result v1

    sget v2, Lorg/telegram/messenger/R$drawable;->msg_speed:I

    sget v3, Lorg/telegram/messenger/R$string;->AIOnDeviceFastModel:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    new-instance v4, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda9;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda9;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-virtual {v0, v1, v2, v3, v4}, Lorg/telegram/ui/Components/ItemOptions;->addChecked(ZILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 190
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ItemOptions;->addGap()Lorg/telegram/ui/Components/ItemOptions;

    .line 191
    sget v1, Lorg/telegram/messenger/R$drawable;->msg_reset:I

    sget v2, Lorg/telegram/messenger/R$string;->AIOnDeviceRefresh:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda10;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda10;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-virtual {v0, v1, v2, v3}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 192
    sget v1, Lorg/telegram/messenger/R$drawable;->msg_settings:I

    sget v2, Lorg/telegram/messenger/R$string;->AIOnDeviceManage:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda11;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda11;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-virtual {v0, v1, v2, v3}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 193
    sget-boolean v1, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz v1, :cond_1

    const/4 v1, 0x3

    goto :goto_0

    :cond_1
    const/4 v1, 0x5

    :goto_0
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/ItemOptions;->setGravity(I)Lorg/telegram/ui/Components/ItemOptions;

    .line 194
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/RecyclerListView;->getClipBackground(Landroid/view/View;)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/ItemOptions;->setScrimViewBackground(Landroid/graphics/drawable/Drawable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 195
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ItemOptions;->show()Lorg/telegram/ui/Components/ItemOptions;

    return-void
.end method


# virtual methods
.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 99
    sget p2, Lorg/telegram/messenger/NotificationCenter;->servicesUpdated:I

    if-ne p1, p2, :cond_0

    .line 100
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->reloadServices()V

    :cond_0
    return-void
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 5
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

    .line 109
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->getTitle()Ljava/lang/String;

    move-result-object p2

    sget v0, Lorg/telegram/messenger/R$string;->ServicesInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    const-string v1, "exteraGramPlaceholders"

    const-string v2, "\ud83d\udd11"

    invoke-static {p2, v0, v1, v2}, Lorg/telegram/ui/Components/UItem;->asTopView(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 110
    sget p2, Lorg/telegram/messenger/R$string;->Services:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-static {p2}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 111
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object p2

    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/AiController;->getAll()Ljava/util/List;

    move-result-object p2

    check-cast p2, Ljava/lang/Iterable;

    .line 1925
    invoke-interface {p2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p2

    const/4 v0, 0x0

    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    add-int/lit8 v2, v0, 0x1

    if-gez v0, :cond_0

    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwIndexOverflow()V

    :cond_0
    check-cast v1, Lcom/exteragram/messenger/ai/data/Service;

    .line 112
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Service;->getModel()Ljava/lang/String;

    move-result-object v3

    invoke-direct {p0, v3}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->applyOnDeviceBadge(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v3

    goto :goto_1

    :cond_1
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Service;->getModel()Ljava/lang/String;

    move-result-object v3

    .line 113
    :goto_1
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result v4

    if-eqz v4, :cond_2

    sget v4, Lorg/telegram/messenger/R$string;->AIOnDevice:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    goto :goto_2

    :cond_2
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Service;->getUrl()Ljava/lang/String;

    move-result-object v4

    :goto_2
    add-int/lit8 v0, v0, 0x64

    .line 114
    invoke-static {v0, v3, v4}, Lorg/telegram/ui/Components/UItem;->asRadio2(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Service;->isSelected()Z

    move-result v3

    invoke-virtual {v0, v3}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    .line 115
    iput-object v1, v0, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    .line 116
    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move v0, v2

    goto :goto_0

    .line 119
    :cond_3
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->getNewServiceDrawable()Lorg/telegram/ui/Components/CombinedDrawable;

    move-result-object p0

    sget p2, Lorg/telegram/messenger/R$string;->NewService:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    const/4 v0, 0x1

    invoke-static {v0, p0, p2}, Lorg/telegram/ui/Components/UItem;->asButton(ILandroid/graphics/drawable/Drawable;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/UItem;->accent()Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    const/16 p2, 0x3d

    .line 120
    iput p2, p0, Lorg/telegram/ui/Components/UItem;->pad:I

    .line 121
    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 104
    sget p0, Lorg/telegram/messenger/R$string;->Services:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public needHideTitle()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 218
    iget-object p2, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p3, p2, Lcom/exteragram/messenger/ai/data/Service;

    const/4 p4, 0x0

    if-eqz p3, :cond_0

    check-cast p2, Lcom/exteragram/messenger/ai/data/Service;

    goto :goto_0

    :cond_0
    move-object p2, p4

    .line 219
    :goto_0
    iget p1, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/16 p3, 0x64

    const/4 p5, 0x1

    if-lt p1, p3, :cond_4

    if-eqz p2, :cond_4

    .line 220
    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result p1

    if-eqz p1, :cond_3

    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isReady()Z

    move-result p1

    if-nez p1, :cond_3

    .line 222
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isDownloadInProgress()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 223
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    .line 224
    sget p2, Lorg/telegram/messenger/R$raw;->ic_download:I

    .line 225
    sget p3, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceDownloading:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p3

    .line 226
    sget p4, Lorg/telegram/messenger/R$string;->AIOnDeviceManage:I

    invoke-static {p4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p4

    .line 223
    new-instance p5, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda4;

    invoke-direct {p5, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-virtual {p1, p2, p3, p4, p5}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 227
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 228
    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->needsDownload()Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs;->showDownloadDialog(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    return-void

    .line 229
    :cond_2
    new-instance p1, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda5;

    invoke-direct {p1, p0, p2}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;Lcom/exteragram/messenger/ai/data/Service;)V

    invoke-static {p1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refresh(Ljava/lang/Runnable;)V

    return-void

    .line 233
    :cond_3
    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/data/Service;->isSelected()Z

    move-result p1

    if-nez p1, :cond_5

    .line 234
    invoke-static {p2}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServices(Lcom/exteragram/messenger/ai/data/Service;)V

    .line 235
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_5

    invoke-virtual {p0, p5}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    return-void

    :cond_4
    if-ne p1, p5, :cond_5

    .line 238
    new-instance p1, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;

    invoke-direct {p1, p4, p5, p4}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;-><init>(Lcom/exteragram/messenger/ai/data/Service;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    :cond_5
    return-void
.end method

.method public onFragmentCreate()Z
    .locals 2

    .line 75
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->servicesUpdated:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 76
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refresh(Ljava/lang/Runnable;)V

    .line 77
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentCreate()Z

    move-result p0

    return p0
.end method

.method public onFragmentDestroy()V
    .locals 2

    .line 93
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->statusPoll:Ljava/lang/Runnable;

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 94
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->servicesUpdated:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 95
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    return-void
.end method

.method public onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z
    .locals 2

    .line 243
    iget-object p1, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p3, p1, Lcom/exteragram/messenger/ai/data/Service;

    if-eqz p3, :cond_0

    check-cast p1, Lcom/exteragram/messenger/ai/data/Service;

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    if-nez p1, :cond_1

    const/4 p0, 0x0

    return p0

    .line 244
    :cond_1
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result p3

    const/4 p4, 0x1

    if-eqz p3, :cond_2

    .line 245
    invoke-direct {p0, p2}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->showOnDeviceOptions(Landroid/view/View;)V

    return p4

    .line 249
    :cond_2
    invoke-static {p0, p2}, Lorg/telegram/ui/Components/ItemOptions;->makeOptions(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/View;)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object p3

    .line 250
    sget p5, Lorg/telegram/messenger/R$drawable;->msg_edit:I

    sget v0, Lorg/telegram/messenger/R$string;->Edit:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;Lcom/exteragram/messenger/ai/data/Service;)V

    invoke-virtual {p3, p5, v0, v1}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 251
    sget p5, Lorg/telegram/messenger/R$drawable;->msg_copy:I

    sget v0, Lorg/telegram/messenger/R$string;->Copy:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda2;

    invoke-direct {v1, p1, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/ai/data/Service;Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-virtual {p3, p5, v0, v1}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 255
    sget p5, Lorg/telegram/messenger/R$drawable;->msg_delete:I

    sget v0, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda3;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;Lcom/exteragram/messenger/ai/data/Service;)V

    invoke-virtual {p3, p5, v0, p4, v1}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;ZLjava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 256
    sget-boolean p1, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz p1, :cond_3

    const/4 p1, 0x3

    goto :goto_1

    :cond_3
    const/4 p1, 0x5

    :goto_1
    invoke-virtual {p3, p1}, Lorg/telegram/ui/Components/ItemOptions;->setGravity(I)Lorg/telegram/ui/Components/ItemOptions;

    .line 257
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/RecyclerListView;->getClipBackground(Landroid/view/View;)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    invoke-virtual {p3, p0}, Lorg/telegram/ui/Components/ItemOptions;->setScrimViewBackground(Landroid/graphics/drawable/Drawable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 258
    invoke-virtual {p3}, Lorg/telegram/ui/Components/ItemOptions;->show()Lorg/telegram/ui/Components/ItemOptions;

    return p4
.end method

.method public onPause()V
    .locals 0

    .line 88
    invoke-super {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onPause()V

    .line 89
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->statusPoll:Ljava/lang/Runnable;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public onResume()V
    .locals 2

    .line 81
    invoke-super {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onResume()V

    .line 82
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refresh(Ljava/lang/Runnable;)V

    .line 83
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->statusPoll:Ljava/lang/Runnable;

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 84
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;->statusPoll:Ljava/lang/Runnable;

    const-wide/16 v0, 0x1388

    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method
