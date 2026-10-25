.class public final Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Companion;,
        Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;,
        Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;,
        Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;,
        Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0003\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0007\n\u0002\u0008\u0006\n\u0002\u0010$\n\u0002\u0008\u0010\u0018\u0000 22\u00020\u0001:\u00042345B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0010\u0010\u000c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0008\u0010\u0010\u001a\u00020\u0011H\u0016J\u001e\u0010\u0012\u001a\u00020\u00132\u000c\u0010\u0014\u001a\u0008\u0012\u0004\u0012\u00020\u00150\u00082\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J0\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0014J0\u0010!\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0014JJ\u0010\"\u001a\u00020\u00132\u000c\u0010\u0014\u001a\u0008\u0012\u0004\u0012\u00020\u00150\u00082\u0006\u0010#\u001a\u00020\u00112\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0&2\u0006\u0010\'\u001a\u00020\u001dH\u0002J\u0012\u0010(\u001a\u0004\u0018\u00010\u00062\u0006\u0010)\u001a\u00020\u001dH\u0002J\u0008\u0010*\u001a\u00020\u0013H\u0002J\u000f\u0010+\u001a\u0004\u0018\u00010\u0013H\u0002\u00a2\u0006\u0002\u0010,J\u0008\u0010-\u001a\u00020\u0013H\u0002J\u0008\u0010.\u001a\u00020\u0013H\u0002J\u0010\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u0006H\u0002J\u0018\u00101\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\rH\u0002R\u0014\u0010\u0004\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0008\u0012\u0004\u0012\u00020\t0\u0008X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00066"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "donates",
        "",
        "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;",
        "subscribers",
        "Ljava/util/ArrayList;",
        "Lcom/exteragram/messenger/api/dto/BoostySubscriberDTO;",
        "defaultDonateIcon",
        "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "getTitle",
        "",
        "fillItems",
        "",
        "items",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onLongClick",
        "",
        "item",
        "view",
        "position",
        "",
        "x",
        "",
        "y",
        "onClick",
        "addDonateSection",
        "headerTitle",
        "donateList",
        "donateIcons",
        "",
        "idOffset",
        "getDonate",
        "id",
        "handleCrashlyticsClick",
        "handleAnalyticsClick",
        "()Lkotlin/Unit;",
        "handleResetSettingsClick",
        "handleDeleteAccountClick",
        "handleDonateClick",
        "donate",
        "handleDonateLongClick",
        "Companion",
        "OtherItem",
        "IconInfo",
        "Donate",
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
        "SMAP\nOtherPreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OtherPreferencesActivity.kt\ncom/exteragram/messenger/preferences/OtherPreferencesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,323:1\n1834#2,4:324\n1924#2,2:329\n296#2,2:331\n1926#2:333\n1#3:328\n29#4:334\n*S KotlinDebug\n*F\n+ 1 OtherPreferencesActivity.kt\ncom/exteragram/messenger/preferences/OtherPreferencesActivity\n*L\n98#1:324,4\n168#1:329,2\n171#1:331,2\n168#1:333\n288#1:334\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Companion;


# instance fields
.field private final defaultDonateIcon:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

.field private donates:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;",
            ">;"
        }
    .end annotation
.end field

.field private final subscribers:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/api/dto/BoostySubscriberDTO;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static $r8$lambda$46eE77krG1ev0fsEgwsM5fDul2U(Z)V
    .locals 0

    .line 142
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setUseGoogleCrashlytics(Z)V

    return-void
.end method

.method public static $r8$lambda$DShbhmj_8_W37UQxRBukG8QD66E(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 2

    .line 205
    invoke-static {}, Lcom/exteragram/messenger/backup/PreferencesUtils;->clearPreferences()V

    .line 206
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 p2, 0x0

    invoke-interface {p1, p2}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    .line 207
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->mainUserInfoChanged:I

    new-array v1, p2, [Ljava/lang/Object;

    invoke-virtual {p1, v0, v1}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 208
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->dialogFiltersUpdated:I

    new-array p2, p2, [Ljava/lang/Object;

    invoke-virtual {p1, v0, p2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 209
    invoke-static {}, Lorg/telegram/messenger/LocaleController;->getInstance()Lorg/telegram/messenger/LocaleController;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/messenger/LocaleController;->recreateFormatters()V

    .line 210
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/ui/ActionBar/Theme;->reloadAllResources(Landroid/content/Context;)V

    .line 211
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    .line 212
    sget p2, Lorg/telegram/messenger/R$string;->ResetPreferences:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-virtual {p1, p2, p0}, Lorg/telegram/ui/Components/BulletinFactory;->createErrorBulletin(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static $r8$lambda$FdxV3ix9VS2QUhaOOP76BJFpvuI(Z)V
    .locals 0

    .line 146
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setUseGoogleAnalytics(Z)V

    return-void
.end method

.method public static $r8$lambda$J0iYmoKZXcoJ1hvSxBth-l3w_CE(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;)V
    .locals 1

    const/4 v0, 0x0

    .line 181
    invoke-static {p0, v0}, Lcom/exteragram/messenger/components/SupporterBottomSheet;->showAlert(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lcom/exteragram/messenger/components/SupporterBottomSheet;

    return-void
.end method

.method public static $r8$lambda$dVNaH0qjGRIV502FTtIuADYa9nA(Lorg/telegram/ui/ActionBar/AlertDialog;Landroid/content/DialogInterface;)V
    .locals 1

    const/4 p1, -0x1

    .line 262
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object p0

    instance-of p1, p0, Landroid/widget/TextView;

    if-eqz p1, :cond_0

    check-cast p0, Landroid/widget/TextView;

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    if-nez p0, :cond_1

    return-void

    .line 263
    :cond_1
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedBold:I

    invoke-static {p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p1

    invoke-virtual {p0, p1}, Landroid/widget/TextView;->setTextColor(I)V

    const/4 p1, 0x0

    .line 264
    invoke-virtual {p0, p1}, Landroid/widget/TextView;->setEnabled(Z)V

    .line 265
    invoke-virtual {p0}, Landroid/widget/TextView;->getText()Ljava/lang/CharSequence;

    move-result-object p1

    .line 267
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1;-><init>(Landroid/widget/TextView;Ljava/lang/CharSequence;)V

    .line 276
    invoke-virtual {v0}, Landroid/os/CountDownTimer;->start()Landroid/os/CountDownTimer;

    return-void
.end method

.method public static $r8$lambda$fcSW2o031d6H2Ez7_0b8lx4cBms(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Ljava/util/List;)V
    .locals 0

    .line 90
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->subscribers:Ljava/util/ArrayList;

    check-cast p1, Ljava/util/Collection;

    invoke-virtual {p0, p1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    return-void
.end method

.method public static $r8$lambda$ilM5cqEH6Q8fGXs5ROPy5rBCyec(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lorg/telegram/ui/ActionBar/AlertDialog;)V
    .locals 3

    .line 231
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getConnectionsManager()Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v0

    new-instance v1, Lorg/telegram/tgnet/tl/TL_account$deleteAccount;

    invoke-direct {v1}, Lorg/telegram/tgnet/tl/TL_account$deleteAccount;-><init>()V

    .line 232
    const-string v2, "\u042d\u041a\u0421\u0422\u0415\u0420\u0410\u0413\u0420\u0410\u041c"

    iput-object v2, v1, Lorg/telegram/tgnet/tl/TL_account$deleteAccount;->reason:Ljava/lang/String;

    .line 231
    new-instance v2, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda8;

    invoke-direct {v2, p1, p0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda8;-><init>(Lorg/telegram/ui/ActionBar/AlertDialog;Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;)V

    invoke-virtual {v0, v1, v2}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    return-void
.end method

.method public static $r8$lambda$vTi_wXRb_f1M9is-gtAXKE_w2do(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 1

    .line 225
    new-instance p1, Lorg/telegram/ui/ActionBar/AlertDialog;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p2

    const/4 v0, 0x3

    invoke-direct {p1, p2, v0}, Lorg/telegram/ui/ActionBar/AlertDialog;-><init>(Landroid/content/Context;I)V

    const/4 p2, 0x0

    .line 226
    invoke-virtual {p1, p2}, Lorg/telegram/ui/ActionBar/AlertDialog;->setCanCancel(Z)V

    .line 227
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/AlertDialog;->show()V

    .line 230
    sget-object p2, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda7;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lorg/telegram/ui/ActionBar/AlertDialog;)V

    const-wide/16 p0, 0x1f4

    invoke-virtual {p2, v0, p0, p1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method public static $r8$lambda$wafXI4sX6H1dACsVEwON_Y0b8JM(Lorg/telegram/ui/ActionBar/AlertDialog;Lorg/telegram/tgnet/TLObject;Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 1

    .line 235
    :try_start_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    .line 237
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 240
    :goto_0
    instance-of p0, p1, Lorg/telegram/tgnet/TLRPC$TL_boolTrue;

    if-eqz p0, :cond_0

    .line 241
    invoke-virtual {p2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/MessagesController;->performLogout(I)V

    goto :goto_2

    :cond_0
    if-eqz p3, :cond_1

    .line 245
    iget p0, p3, Lorg/telegram/tgnet/TLRPC$TL_error;->code:I

    const/16 p1, -0x3e8

    if-eq p0, p1, :cond_4

    .line 246
    :cond_1
    sget p0, Lorg/telegram/messenger/R$string;->ErrorOccurred:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    const/4 p1, 0x0

    if-eqz p3, :cond_2

    iget-object p3, p3, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    if-eqz p3, :cond_2

    const-string v0, "\n"

    invoke-virtual {v0, p3}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    goto :goto_1

    :cond_2
    move-object p3, p1

    :goto_1
    if-nez p3, :cond_3

    const-string p3, ""

    :cond_3
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 247
    new-instance p3, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p2

    invoke-direct {p3, p2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 248
    sget p2, Lorg/telegram/messenger/R$string;->AppName:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p3, p2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 249
    invoke-virtual {p3, p0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 250
    sget p0, Lorg/telegram/messenger/R$string;->OK:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p3, p0, p1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 251
    invoke-virtual {p3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->show()Lorg/telegram/ui/ActionBar/AlertDialog;

    :cond_4
    :goto_2
    return-void
.end method

.method public static $r8$lambda$yWCQpFPXkqfATo6phc_BXBXCqMQ(Lorg/telegram/ui/ActionBar/AlertDialog;Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 1

    .line 233
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;

    invoke-direct {v0, p0, p2, p1, p3}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;-><init>(Lorg/telegram/ui/ActionBar/AlertDialog;Lorg/telegram/tgnet/TLObject;Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->Companion:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 3

    .line 55
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    .line 85
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->donates:Ljava/util/List;

    .line 86
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->subscribers:Ljava/util/ArrayList;

    .line 87
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

    sget v1, Lorg/telegram/messenger/R$drawable;->msg_payment_card:I

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;-><init>(II)V

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->defaultDonateIcon:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

    return-void
.end method

.method private final addDonateSection(Ljava/util/ArrayList;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;I)V
    .locals 19
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;",
            ">;I)V"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v14, p1

    .line 164
    move-object/from16 v1, p3

    check-cast v1, Ljava/util/Collection;

    if-eqz v1, :cond_7

    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto/16 :goto_4

    .line 166
    :cond_0
    invoke-static/range {p2 .. p2}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 168
    move-object/from16 v1, p3

    check-cast v1, Ljava/lang/Iterable;

    .line 1925
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v15

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    invoke-interface {v15}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_6

    invoke-interface {v15}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v16, v2, 0x1

    if-gez v2, :cond_1

    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwIndexOverflow()V

    :cond_1
    check-cast v3, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;

    .line 169
    invoke-virtual {v3}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;->getName()Ljava/lang/String;

    move-result-object v4

    sget-object v5, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {v4, v5}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v4

    .line 170
    invoke-interface/range {p4 .. p4}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v5

    .line 296
    invoke-interface {v5}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :cond_2
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    const/4 v7, 0x0

    if-eqz v6, :cond_3

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    move-object v8, v6

    check-cast v8, Ljava/util/Map$Entry;

    invoke-interface {v8}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/lang/String;

    const/4 v9, 0x2

    .line 171
    invoke-static {v4, v8, v1, v9, v7}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_2

    move-object v7, v6

    :cond_3
    check-cast v7, Ljava/util/Map$Entry;

    if-eqz v7, :cond_4

    .line 172
    invoke-interface {v7}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

    if-eqz v4, :cond_4

    :goto_1
    move-object/from16 v17, v4

    goto :goto_2

    :cond_4
    iget-object v4, v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->defaultDonateIcon:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

    goto :goto_1

    .line 173
    :goto_2
    sget-object v4, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->DONATE:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    invoke-virtual {v4}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->getId()I

    move-result v4

    add-int v4, v4, p5

    add-int/2addr v4, v2

    invoke-virtual {v3}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;->getName()Ljava/lang/String;

    move-result-object v2

    const/16 v12, 0x7bc

    const/4 v13, 0x0

    const/4 v3, 0x0

    move v5, v1

    move v1, v4

    const/4 v4, 0x0

    move v6, v5

    const/4 v5, 0x0

    move v7, v6

    const/4 v6, 0x0

    move v8, v7

    const/4 v7, 0x1

    move v9, v8

    const/4 v8, 0x0

    move v10, v9

    const/4 v9, 0x0

    move v11, v10

    const/4 v10, 0x0

    move/from16 v18, v11

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 174
    invoke-virtual/range {v17 .. v17}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->getIconColor()I

    move-result v2

    if-nez v2, :cond_5

    invoke-virtual/range {v17 .. v17}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->getIconResId()I

    move-result v2

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/UItem;->setIcon(I)Lorg/telegram/ui/Components/UItem;

    goto :goto_3

    .line 175
    :cond_5
    invoke-virtual/range {v17 .. v17}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->getIconResId()I

    move-result v2

    invoke-virtual/range {v17 .. v17}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;->getIconColor()I

    move-result v3

    invoke-virtual {v1, v2, v3}, Lorg/telegram/ui/Components/UItem;->setColorfulIcon(II)Lorg/telegram/ui/Components/UItem;

    .line 177
    :goto_3
    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move/from16 v2, v16

    move/from16 v1, v18

    goto/16 :goto_0

    .line 180
    :cond_6
    sget v1, Lorg/telegram/messenger/R$string;->GetBadgeInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda1;

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;)V

    invoke-static {v1, v2}, Lorg/telegram/messenger/AndroidUtilities;->replaceSingleTag(Ljava/lang/String;Ljava/lang/Runnable;)Landroid/text/SpannableStringBuilder;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_7
    :goto_4
    return-void
.end method

.method private final getDonate(I)Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;
    .locals 1

    .line 186
    sget-object v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->DONATE:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->getId()I

    move-result v0

    sub-int/2addr p1, v0

    if-ltz p1, :cond_0

    .line 187
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->donates:Ljava/util/List;

    check-cast v0, Ljava/util/Collection;

    invoke-interface {v0}, Ljava/util/Collection;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    iget-object p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->donates:Ljava/util/List;

    invoke-interface {p0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private final handleAnalyticsClick()Lkotlin/Unit;
    .locals 1

    .line 194
    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFirebaseAnalytics()Lcom/google/firebase/analytics/FirebaseAnalytics;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 195
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUseGoogleAnalytics()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/google/firebase/analytics/FirebaseAnalytics;->setAnalyticsCollectionEnabled(Z)V

    .line 196
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUseGoogleAnalytics()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/google/firebase/analytics/FirebaseAnalytics;->resetAnalyticsData()V

    .line 194
    :cond_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method private final handleCrashlyticsClick()V
    .locals 1

    .line 192
    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFirebaseCrashlytics()Lcom/google/firebase/crashlytics/FirebaseCrashlytics;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUseGoogleCrashlytics()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/google/firebase/crashlytics/FirebaseCrashlytics;->setCrashlyticsCollectionEnabled(Z)V

    :cond_0
    return-void
.end method

.method private final handleDeleteAccountClick()V
    .locals 3

    .line 221
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 222
    sget v1, Lorg/telegram/messenger/R$string;->TosDeclineDeleteAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 223
    sget v1, Lorg/telegram/messenger/R$string;->DeleteAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 224
    sget v1, Lorg/telegram/messenger/R$string;->Deactivate:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda5;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;)V

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 257
    sget v1, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 260
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v0

    .line 261
    new-instance v1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda6;

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/ui/ActionBar/AlertDialog;)V

    invoke-virtual {v0, v1}, Landroid/app/Dialog;->setOnShowListener(Landroid/content/DialogInterface$OnShowListener;)V

    .line 278
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method

.method private final handleDonateClick(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;)V
    .locals 5

    .line 282
    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v0

    .line 284
    const-string v1, "ton"

    const/4 v2, 0x0

    const/4 v3, 0x2

    const/4 v4, 0x0

    invoke-static {v0, v1, v2, v3, v4}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 285
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    iget-wide v0, v0, Lorg/telegram/messenger/UserConfig;->clientUserId:J

    .line 286
    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;->getDetails()Ljava/lang/String;

    move-result-object v2

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "ton://transfer/"

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "?text="

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 29
    invoke-static {v0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    const/4 v2, 0x1

    .line 288
    new-array v2, v2, [Z

    invoke-static {v1, v2}, Lorg/telegram/messenger/browser/Browser;->isInternalUri(Landroid/net/Uri;[Z)Z

    move-result v1

    if-nez v1, :cond_0

    .line 289
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    invoke-static {p0, v0}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    return-void

    .line 290
    :cond_0
    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;->getDetails()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_5

    .line 291
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->TextCopied:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyBulletin(Ljava/lang/String;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 294
    :cond_1
    const-string v1, "boosty"

    invoke-static {v0, v1, v2, v3, v4}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->subscribers:Ljava/util/ArrayList;

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_3

    .line 295
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_2

    goto :goto_0

    .line 296
    :cond_2
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    iget-object v1, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->subscribers:Ljava/util/ArrayList;

    new-instance v2, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDonateClick$1;

    invoke-direct {v2, p0, p1, v0, v1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$handleDonateClick$1;-><init>(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;Landroid/app/Activity;Ljava/util/ArrayList;)V

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void

    .line 301
    :cond_3
    sget-object v0, Lorg/telegram/messenger/LinkifyPort;->WEB_URL:Ljava/util/regex/Pattern;

    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;->getDetails()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/regex/Matcher;->matches()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 302
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;->getDetails()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    return-void

    .line 306
    :cond_4
    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;->getDetails()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_5

    .line 307
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->TextCopied:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyBulletin(Ljava/lang/String;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_5
    :goto_0
    return-void
.end method

.method private final handleDonateLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;)Z
    .locals 0

    .line 313
    iget p1, p1, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->getDonate(I)Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 314
    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;->getDetails()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 315
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->TextCopied:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyBulletin(Ljava/lang/String;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_0
    const/4 p0, 0x3

    .line 318
    invoke-static {p0}, Lcom/exteragram/messenger/utils/system/VibratorUtils;->getType(I)I

    move-result p0

    const/4 p1, 0x1

    .line 317
    invoke-virtual {p2, p0, p1}, Landroid/view/View;->performHapticFeedback(II)Z

    const/4 p0, 0x0

    return p0
.end method

.method private final handleResetSettingsClick()V
    .locals 4

    .line 200
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 201
    sget v1, Lorg/telegram/messenger/R$string;->ResetPreferencesInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 202
    sget v1, Lorg/telegram/messenger/R$string;->ResetSettings:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 203
    sget v1, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 204
    sget v1, Lorg/telegram/messenger/R$string;->Reset:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v3, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda4;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;)V

    invoke-virtual {v0, v1, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 214
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v0

    .line 215
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    const/4 p0, -0x1

    .line 216
    invoke-virtual {v0, p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object p0

    instance-of v0, p0, Landroid/widget/TextView;

    if-eqz v0, :cond_0

    move-object v2, p0

    check-cast v2, Landroid/widget/TextView;

    :cond_0
    if-eqz v2, :cond_1

    .line 217
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedBold:I

    invoke-static {p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p0

    invoke-virtual {v2, p0}, Landroid/widget/TextView;->setTextColor(I)V

    :cond_1
    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 1

    .line 90
    new-instance v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;)V

    invoke-static {v0}, Lcom/exteragram/messenger/api/db/DatabaseHelper;->getBoostySubscribers(Ljava/util/function/Consumer;)V

    .line 91
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object p0

    return-object p0
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

    .line 97
    sget-object v1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->Companion:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Companion;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Companion;->getDonates()Ljava/util/List;

    move-result-object v1

    iput-object v1, v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->donates:Ljava/util/List;

    .line 98
    sget-object v1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/concurrent/ConcurrentHashMap;->values()Ljava/util/Collection;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    .line 1834
    move-object v2, v1

    check-cast v2, Ljava/util/Collection;

    invoke-interface {v2}, Ljava/util/Collection;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_0

    const/4 v15, 0x0

    goto :goto_1

    .line 1836
    :cond_0
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    const/4 v2, 0x0

    :cond_1
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/exteragram/messenger/plugins/Plugin;

    .line 98
    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/Plugin;->isEnabled()Z

    move-result v3

    if-eqz v3, :cond_1

    add-int/lit8 v2, v2, 0x1

    if-gez v2, :cond_1

    .line 1836
    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwCountOverflow()V

    goto :goto_0

    :cond_2
    move v15, v2

    .line 99
    :goto_1
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeDark()Z

    move-result v1

    .line 101
    new-instance v2, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

    sget v3, Lorg/telegram/messenger/R$drawable;->mastercard_icon:I

    if-eqz v1, :cond_3

    const/4 v4, -0x1

    goto :goto_2

    :cond_3
    const/high16 v4, -0x1000000

    :goto_2
    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;-><init>(II)V

    const-string v3, "mastercard"

    invoke-static {v3, v2}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v2

    .line 102
    new-instance v3, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

    sget v4, Lorg/telegram/messenger/R$drawable;->ton_icon:I

    if-eqz v1, :cond_4

    const v5, -0xd8c9b3

    goto :goto_3

    :cond_4
    const v5, -0xeff9e1

    :goto_3
    invoke-direct {v3, v4, v5}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;-><init>(II)V

    const-string v4, "tonkeeper"

    invoke-static {v4, v3}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v3

    .line 103
    new-instance v4, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

    sget v5, Lorg/telegram/messenger/R$drawable;->ton_space_icon:I

    const v6, -0xcf560a

    invoke-direct {v4, v5, v6}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;-><init>(II)V

    const-string v5, "space"

    invoke-static {v5, v4}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v4

    .line 104
    new-instance v5, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;

    sget v6, Lorg/telegram/messenger/R$drawable;->boosty_icon:I

    if-eqz v1, :cond_5

    const v1, -0x111112

    goto :goto_4

    :cond_5
    const v1, -0xdbd4d4

    :goto_4
    invoke-direct {v5, v6, v1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$IconInfo;-><init>(II)V

    const-string v1, "boosty"

    invoke-static {v1, v5}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v1

    filled-new-array {v2, v3, v4, v1}, [Lkotlin/Pair;

    move-result-object v1

    .line 100
    invoke-static {v1}, Lkotlin/collections/MapsKt;->mapOf([Lkotlin/Pair;)Ljava/util/Map;

    move-result-object v4

    .line 107
    sget v1, Lorg/telegram/messenger/R$string;->Support:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->donates:Ljava/util/List;

    const/4 v5, 0x0

    move-object/from16 v1, p1

    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->addDonateSection(Ljava/util/ArrayList;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;I)V

    move-object v12, v1

    .line 109
    const-string v0, "Google"

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v12, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 110
    sget-object v1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->CRASHLYTICS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUseGoogleCrashlytics()Z

    move-result v3

    sget v9, Lorg/telegram/messenger/R$drawable;->msg_report:I

    const/16 v10, 0xb8

    const/4 v11, 0x0

    const-string v2, "Crashlytics"

    const/4 v4, 0x0

    const/4 v6, 0x0

    const-string v7, "crashlytics"

    const/4 v8, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v12, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 111
    sget-object v1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->ANALYTICS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUseGoogleAnalytics()Z

    move-result v3

    sget v9, Lorg/telegram/messenger/R$drawable;->msg_data:I

    const-string v2, "Analytics"

    const-string v7, "analytics"

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v12, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 112
    sget v0, Lorg/telegram/messenger/R$string;->AnalyticsInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v12, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 114
    sget-object v1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->EXPORT_SETTINGS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    sget v0, Lorg/telegram/messenger/R$string;->ExportSettings:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_settings:I

    const/16 v12, 0x778

    const/4 v13, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "exportSettings"

    const/4 v9, 0x0

    const/4 v10, 0x0

    move-object/from16 v0, p0

    move-object/from16 v14, p1

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 116
    sget-object v0, Lcom/exteragram/messenger/badges/BadgesController;->INSTANCE:Lcom/exteragram/messenger/badges/BadgesController;

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-static {v0, v1, v2, v1}, Lcom/exteragram/messenger/badges/BadgesController;->isDeveloper$default(Lcom/exteragram/messenger/badges/BadgesController;Lorg/telegram/tgnet/TLRPC$User;ILjava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_6

    if-gt v15, v2, :cond_6

    move v15, v2

    goto :goto_5

    :cond_6
    const/4 v15, 0x0

    .line 117
    :goto_5
    sget-object v1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->EXPORT_DATA:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    sget v0, Lorg/telegram/messenger/R$string;->ExportData:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_archive:I

    const/16 v12, 0x778

    const/4 v13, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "exportData"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 115
    invoke-static {v14, v15, v1}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    .line 119
    sget-object v1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->RESET_SETTINGS:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    sget v0, Lorg/telegram/messenger/R$string;->ResetSettings:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_reset:I

    const-string v8, "resetSettings"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 120
    sget-object v1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->DELETE_ACCOUNT:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    sget v0, Lorg/telegram/messenger/R$string;->DeleteAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_clearcache:I

    const/16 v12, 0x678

    const-string v8, "deleteAccount"

    const/4 v9, 0x1

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 121
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 94
    sget p0, Lorg/telegram/messenger/R$string;->LocalOther:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 132
    iget p2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    .line 133
    sget-object p3, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->DONATE:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    invoke-virtual {p3}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->getId()I

    move-result p4

    if-lt p2, p4, :cond_0

    invoke-virtual {p3}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->getId()I

    move-result p4

    iget-object p5, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->donates:Ljava/util/List;

    invoke-interface {p5}, Ljava/util/List;->size()I

    move-result p5

    add-int/2addr p4, p5

    if-ge p2, p4, :cond_0

    .line 134
    invoke-direct {p0, p2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->getDonate(I)Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;

    move-result-object p1

    if-eqz p1, :cond_2

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->handleDonateClick(Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$Donate;)V

    return-void

    :cond_0
    if-lez p2, :cond_2

    .line 138
    invoke-virtual {p3}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->getId()I

    move-result p3

    if-lt p2, p3, :cond_1

    goto :goto_0

    .line 139
    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p3

    add-int/lit8 p2, p2, -0x1

    invoke-interface {p3, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    .line 140
    sget-object p3, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    aget p2, p3, p2

    packed-switch p2, :pswitch_data_0

    goto :goto_0

    .line 152
    :pswitch_0
    new-instance p1, Lcom/exteragram/messenger/export/ui/ExportActivity;

    const/4 p2, 0x0

    invoke-direct {p1, p2}, Lcom/exteragram/messenger/export/ui/ExportActivity;-><init>(Lorg/telegram/tgnet/TLRPC$InputPeer;)V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 151
    :pswitch_1
    invoke-static {}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getInstance()Lcom/exteragram/messenger/backup/PreferencesUtils;

    move-result-object p1

    invoke-virtual {p1, p0}, Lcom/exteragram/messenger/backup/PreferencesUtils;->exportSettings(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    return-void

    .line 150
    :pswitch_2
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->handleDeleteAccountClick()V

    return-void

    .line 149
    :pswitch_3
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->handleResetSettingsClick()V

    return-void

    .line 146
    :pswitch_4
    new-instance p2, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda3;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda3;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 147
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->handleAnalyticsClick()Lkotlin/Unit;

    return-void

    .line 142
    :pswitch_5
    new-instance p2, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda2;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda2;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 143
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->handleCrashlyticsClick()V

    :cond_2
    :goto_0
    return-void

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z
    .locals 6

    .line 125
    iget v0, p1, Lorg/telegram/ui/Components/UItem;->id:I

    .line 126
    sget-object v1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->DONATE:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->getId()I

    move-result v2

    if-lt v0, v2, :cond_0

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$OtherItem;->getId()I

    move-result v1

    iget-object v2, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->donates:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    add-int/2addr v1, v2

    if-ge v0, v1, :cond_0

    .line 127
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->handleDonateLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;)Z

    move-result p0

    return p0

    :cond_0
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move v3, p3

    move v4, p4

    move v5, p5

    .line 128
    invoke-super/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z

    move-result p0

    return p0
.end method
