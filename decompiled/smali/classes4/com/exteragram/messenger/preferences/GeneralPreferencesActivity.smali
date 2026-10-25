.class public final Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$Companion;,
        Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;,
        Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\u0008\u0003\n\u0002\u0010\u0008\n\u0002\u0008\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0007\n\u0002\u0008\u0005\n\u0002\u0010\u000b\n\u0002\u0008\u0012\u0018\u0000 62\u00020\u0001:\u000256B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u000f\u001a\u00020\u0010H\u0014J\u0008\u0010\u0011\u001a\u00020\u0012H\u0016J\u001e\u0010\u0013\u001a\u00020\u00102\u000c\u0010\u0014\u001a\u0008\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J0\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0014J\u0008\u0010(\u001a\u00020\u0010H\u0002J\u0010\u0010,\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\u0008\u0010-\u001a\u00020\u0010H\u0002J\u0008\u0010.\u001a\u00020\u0010H\u0002J\u0008\u00104\u001a\u00020\u0010H\u0002R\u0016\u0010\u0004\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\u0008\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\u000c\n\u0004\u0008\r\u0010\u000e\u001a\u0004\u0008\u000b\u0010\u000cR\u0014\u0010!\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\u0008\"\u0010#R\u0014\u0010$\u001a\u00020%8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\u0008&\u0010\'R\u0014\u0010)\u001a\u00020%8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\u0008*\u0010\'R\u0014\u0010+\u001a\u00020%8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\u0008+\u0010\'R\u0014\u0010/\u001a\u00020\u00128BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\u00080\u00101R\u0014\u00102\u001a\u00020\u00128BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\u00083\u00101\u00a8\u00067"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "idOptions",
        "",
        "",
        "[Ljava/lang/CharSequence;",
        "translationProviders",
        "fiveMinutesAgo",
        "",
        "getFiveMinutesAgo",
        "()I",
        "fiveMinutesAgo$delegate",
        "Lkotlin/Lazy;",
        "initializeOptionStrings",
        "",
        "getTitle",
        "",
        "fillItems",
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
        "x",
        "",
        "y",
        "doNotTranslateValue",
        "getDoNotTranslateValue",
        "()Ljava/lang/CharSequence;",
        "contextTranslateValue",
        "",
        "getContextTranslateValue",
        "()Z",
        "handleContextTranslateClick",
        "chatTranslateValue",
        "getChatTranslateValue",
        "isChatTranslateLocked",
        "handleChatTranslateClick",
        "handleFormatTimeWithSecondsClick",
        "handleHidePhoneNumberClick",
        "customSavePathDisplayValue",
        "getCustomSavePathDisplayValue",
        "()Ljava/lang/String;",
        "customSavePathInfo",
        "getCustomSavePathInfo",
        "showCustomSavePathDialog",
        "GeneralItem",
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
        "SMAP\nGeneralPreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneralPreferencesActivity.kt\ncom/exteragram/messenger/preferences/GeneralPreferencesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,300:1\n1642#2,10:301\n1915#2:311\n1916#2:313\n1652#2:314\n1#3:312\n*S KotlinDebug\n*F\n+ 1 GeneralPreferencesActivity.kt\ncom/exteragram/messenger/preferences/GeneralPreferencesActivity\n*L\n191#1:301,10\n191#1:311\n191#1:313\n191#1:314\n191#1:312\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$Companion;

.field private static final SAVE_PATH_PATTERN:Ljava/util/regex/Pattern;


# instance fields
.field private final fiveMinutesAgo$delegate:Lkotlin/Lazy;

.field private idOptions:[Ljava/lang/CharSequence;

.field private translationProviders:[Ljava/lang/CharSequence;


# direct methods
.method public static $r8$lambda$-1zVscb9gTLjNdEc6JbOjLBvQAk(Z)V
    .locals 0

    .line 159
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setFormatTimeWithSeconds(Z)V

    return-void
.end method

.method public static $r8$lambda$-6792H4QVEdinaIDuZk7bIWlb0s(Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 286
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    return-void
.end method

.method public static $r8$lambda$5GYjEBDTw6R69LJOxk-OvhZShiQ(Z)V
    .locals 0

    .line 181
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setArchiveOnPull(Z)V

    return-void
.end method

.method public static $r8$lambda$65QAtQ0CrA2nUJeI6PU4vcpP-xA(Z)V
    .locals 0

    .line 178
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHideArchiveFolder(Z)V

    return-void
.end method

.method public static $r8$lambda$6zgVf5fhCgfMnlN9P1_-x3spU2E(Lorg/telegram/ui/Components/EditTextBoldCursor;Landroid/content/DialogInterface;)V
    .locals 0

    .line 290
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->hideKeyboard(Landroid/view/View;)V

    return-void
.end method

.method public static $r8$lambda$CLrsnmEDdSD_bnhQjPlKm3Ogncs(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;)V
    .locals 3

    .line 216
    new-instance v0, Lorg/telegram/ui/Components/Premium/PremiumFeatureBottomSheet;

    const/16 v1, 0xd

    const/4 v2, 0x0

    invoke-direct {v0, p0, v1, v2}, Lorg/telegram/ui/Components/Premium/PremiumFeatureBottomSheet;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;IZ)V

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method

.method public static $r8$lambda$Dm6JSeTzSHL1BBskGBexT-XhGzI(Z)V
    .locals 0

    .line 164
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setDisableNotificationDelay(Z)V

    return-void
.end method

.method public static $r8$lambda$J831koXHuOVSMRGTuNEtCDswmvo(Z)V
    .locals 0

    .line 157
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setDisableNumberRounding(Z)V

    return-void
.end method

.method public static $r8$lambda$K9eupNPj_rS_RS7_N0xpbJJpzkM(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;Z)V
    .locals 0

    .line 142
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController;->getTranslateController()Lorg/telegram/messenger/TranslateController;

    move-result-object p0

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/TranslateController;->setContextTranslateEnabled(Z)V

    return-void
.end method

.method public static $r8$lambda$KrWwAmRC7Guvl72AisMkEr5VwJU(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;I)V
    .locals 0

    .line 174
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setShowIdAndDc(I)V

    .line 175
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 p1, 0x0

    invoke-interface {p0, p1}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method public static $r8$lambda$L_UdzkNIPcT9sRPhsIbeSp_0nU4(Z)V
    .locals 0

    .line 163
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setInAppVibration(Z)V

    return-void
.end method

.method public static $r8$lambda$TGgWqWciHfVoWWoSPJGiGkq3kAc(Lorg/telegram/ui/Components/EditTextBoldCursor;Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 1

    .line 277
    invoke-virtual {p0}, Lcom/exteragram/messenger/components/ReceiveContentEditText;->getText()Landroid/text/Editable;

    move-result-object p3

    if-eqz p3, :cond_0

    invoke-virtual {p3}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p3

    if-eqz p3, :cond_0

    invoke-static {p3}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p3

    invoke-virtual {p3}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p3

    if-nez p3, :cond_1

    :cond_0
    const-string p3, ""

    .line 278
    :cond_1
    invoke-interface {p3}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-lez v0, :cond_2

    sget-object v0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->SAVE_PATH_PATTERN:Ljava/util/regex/Pattern;

    invoke-virtual {v0, p3}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/regex/Matcher;->matches()Z

    move-result v0

    if-nez v0, :cond_2

    .line 279
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->shakeView(Landroid/view/View;)V

    return-void

    .line 282
    :cond_2
    invoke-static {p3}, Lcom/exteragram/messenger/ExteraConfig;->setCustomSavePath(Ljava/lang/String;)V

    .line 283
    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 284
    invoke-virtual {p2}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    return-void
.end method

.method public static $r8$lambda$U3axzru9WxR8DMYs4-Fwp9CNRKA(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;)I
    .locals 0

    .line 73
    iget p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {p0}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/tgnet/ConnectionsManager;->getCurrentTime()I

    move-result p0

    add-int/lit16 p0, p0, -0x12c

    return p0
.end method

.method public static $r8$lambda$VZeNIa2dT8V95SSPsFSkahviBm8(Z)V
    .locals 0

    .line 169
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHidePhoneNumber(Z)V

    return-void
.end method

.method public static $r8$lambda$aJNHyEfWVAyoGhMbpbKilQ468y8(Z)V
    .locals 0

    .line 162
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setRelativeLastSeen(Z)V

    return-void
.end method

.method public static $r8$lambda$eGlFftM4yupT7qEkp7fuBtEHT5w(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;Z)V
    .locals 0

    .line 219
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController;->getTranslateController()Lorg/telegram/messenger/TranslateController;

    move-result-object p0

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/TranslateController;->setChatTranslateEnabled(Z)V

    return-void
.end method

.method public static $r8$lambda$ey9elelLTBGwmTmpDtFlhV0SDII(Z)V
    .locals 0

    .line 166
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setUploadSpeedBoost(Z)V

    return-void
.end method

.method public static $r8$lambda$fkxN65LZaTfMEbFYf6y-GJljFos(Lorg/telegram/ui/Components/EditTextBoldCursor;Landroid/content/DialogInterface;)V
    .locals 0

    .line 292
    invoke-virtual {p0}, Landroid/view/View;->requestFocus()Z

    .line 293
    invoke-virtual {p0}, Landroid/widget/TextView;->length()I

    move-result p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setSelection(I)V

    .line 294
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    return-void
.end method

.method public static $r8$lambda$moiwS_OdMKGCLwy70XJIPkqI-cc(Z)V
    .locals 0

    .line 182
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setDisableUnarchiveSwipe(Z)V

    return-void
.end method

.method public static $r8$lambda$pPeUA80MUZui_qVriAzotjwYxPQ(Z)V
    .locals 0

    .line 165
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setFilterZalgo(Z)V

    return-void
.end method

.method public static $r8$lambda$pnIgrca-oicP1fGeYl5KQLx5eEI(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;I)V
    .locals 0

    .line 148
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setTranslationProvider(I)V

    .line 149
    invoke-static {}, Lcom/exteragram/messenger/translator/TranslatorUtils;->ensureTargetLanguageCompatibleWithProvider()V

    .line 150
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 p1, 0x0

    invoke-interface {p0, p1}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method public static $r8$lambda$we9EboXXYvclBnRhBglfMmxCWbw(Ljava/lang/Integer;)V
    .locals 0

    .line 116
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setDownloadSpeedBoost(I)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->Companion:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$Companion;

    .line 76
    const-string v0, "^(?!\\.{1,2}$)[A-Za-z0-9._ -]{1,255}$"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->SAVE_PATH_PATTERN:Ljava/util/regex/Pattern;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 45
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    .line 73
    new-instance v0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;)V

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->fiveMinutesAgo$delegate:Lkotlin/Lazy;

    return-void
.end method

.method private final getChatTranslateValue()Z
    .locals 0

    .line 205
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController;->getTranslateController()Lorg/telegram/messenger/TranslateController;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/TranslateController;->isChatTranslateEnabled()Z

    move-result p0

    return p0
.end method

.method private final getContextTranslateValue()Z
    .locals 0

    .line 197
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController;->getTranslateController()Lorg/telegram/messenger/TranslateController;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/TranslateController;->isContextTranslateEnabled()Z

    move-result p0

    return p0
.end method

.method private final getCustomSavePathDisplayValue()Ljava/lang/String;
    .locals 0

    .line 235
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCustomSavePath()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    sget p0, Lorg/telegram/messenger/R$string;->CustomSavePathDefault:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCustomSavePath()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final getCustomSavePathInfo()Ljava/lang/String;
    .locals 1

    .line 238
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCustomSavePath()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    sget p0, Lorg/telegram/messenger/R$string;->CustomSavePathInfo:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 239
    :cond_0
    sget p0, Lorg/telegram/messenger/R$string;->CustomSavePathInfoFolder:I

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCustomSavePath()Ljava/lang/String;

    move-result-object v0

    filled-new-array {v0}, [Ljava/lang/Object;

    move-result-object v0

    invoke-static {p0, v0}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final getDoNotTranslateValue()Ljava/lang/CharSequence;
    .locals 10

    .line 189
    invoke-static {}, Lorg/telegram/ui/RestrictedLanguagesSelectActivity;->getRestrictedLanguages()Ljava/util/HashSet;

    move-result-object p0

    const/4 v0, 0x1

    .line 190
    new-array v0, v0, [Z

    .line 1642
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 1915
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 1651
    check-cast v2, Ljava/lang/String;

    .line 192
    invoke-static {v2, v0}, Lorg/telegram/ui/Components/TranslateAlert2;->languageName(Ljava/lang/String;[Z)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lorg/telegram/ui/Components/TranslateAlert2;->capitalFirst(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_0

    .line 1651
    invoke-interface {v1, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_1
    const/16 v8, 0x3e

    const/4 v9, 0x0

    .line 193
    const-string v2, ", "

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-static/range {v1 .. v9}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final getFiveMinutesAgo()I
    .locals 0

    .line 73
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->fiveMinutesAgo$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->intValue()I

    move-result p0

    return p0
.end method

.method private final handleChatTranslateClick(Lorg/telegram/ui/Components/UItem;)V
    .locals 4

    .line 211
    iget-boolean v0, p1, Lorg/telegram/ui/Components/UItem;->checked:Z

    if-nez v0, :cond_0

    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->isChatTranslateLocked()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 212
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    .line 213
    sget v0, Lorg/telegram/messenger/R$raw;->msg_translate:I

    .line 214
    sget v1, Lorg/telegram/messenger/R$string;->ShowTranslateChatButtonLocked:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 215
    sget v2, Lorg/telegram/messenger/R$string;->MoreInfo:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 212
    new-instance v3, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda20;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda20;-><init>(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;)V

    invoke-virtual {p1, v0, v1, v2, v3}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 216
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 219
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda21;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda21;-><init>(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;)V

    invoke-virtual {p0, p1, v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 220
    iget p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->updateSearchSettings:I

    const/4 v1, 0x0

    new-array v2, v1, [Ljava/lang/Object;

    invoke-virtual {p1, v0, v2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 221
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {p0, v1}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method private final handleContextTranslateClick()V
    .locals 4

    .line 200
    iget v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->updateSearchSettings:I

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v3}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 201
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {p0, v2}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method private final handleFormatTimeWithSecondsClick()V
    .locals 1

    .line 225
    invoke-static {}, Lorg/telegram/messenger/LocaleController;->getInstance()Lorg/telegram/messenger/LocaleController;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/LocaleController;->recreateFormatters()V

    .line 226
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 v0, 0x0

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method private final handleHidePhoneNumberClick()V
    .locals 4

    .line 230
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->mainUserInfoChanged:I

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v3}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 231
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {p0, v2}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method private final isChatTranslateLocked()Z
    .locals 0

    .line 208
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getUserConfig()Lorg/telegram/messenger/UserConfig;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/UserConfig;->isPremium()Z

    move-result p0

    if-nez p0, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/translator/TranslatorUtils;->isAlternativeProvider()Z

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final showCustomSavePathDialog()V
    .locals 11

    .line 242
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    .line 243
    :cond_0
    new-instance v0, Lorg/telegram/ui/Components/EditTextBoldCursor;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Lorg/telegram/ui/Components/EditTextBoldCursor;-><init>(Landroid/content/Context;)V

    const/4 v1, 0x1

    .line 244
    iput-boolean v1, v0, Lorg/telegram/ui/Components/EditTextBoldCursor;->lineYFix:Z

    const/high16 v2, 0x41900000    # 18.0f

    .line 245
    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setTextSize(IF)V

    .line 246
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCustomSavePath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 247
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextBlack:I

    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v3}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/EditTextEffects;->setTextColor(I)V

    .line 248
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_groupcreate_hintText:I

    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v3}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setHintColor(I)V

    .line 249
    sget v2, Lorg/telegram/messenger/R$string;->CustomSavePathHint:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setHintText(Ljava/lang/CharSequence;)V

    .line 250
    invoke-virtual {v0, v1}, Landroid/view/View;->setFocusable(Z)V

    .line 251
    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setSingleLine(Z)V

    .line 252
    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setInputType(I)V

    const/4 v2, 0x0

    .line 253
    invoke-virtual {v0, v2}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 255
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteInputField:I

    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v3}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v2

    .line 256
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteInputFieldActivated:I

    iget-object v4, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v3, v4}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v4

    .line 257
    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedRegular:I

    iget-object v6, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v5, v6}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v5

    .line 254
    invoke-virtual {v0, v2, v4, v5}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setLineColors(III)V

    .line 259
    iget-object v2, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v3, v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setCursorColor(I)V

    const/high16 v2, 0x40c00000    # 6.0f

    .line 260
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    const/4 v4, 0x0

    invoke-virtual {v0, v4, v3, v4, v2}, Landroid/view/View;->setPadding(IIII)V

    .line 263
    new-instance v2, Landroid/widget/LinearLayout;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v3

    invoke-direct {v2, v3}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 264
    invoke-virtual {v2, v1}, Landroid/widget/LinearLayout;->setOrientation(I)V

    const/high16 v9, 0x41c00000    # 24.0f

    const/high16 v10, 0x41200000    # 10.0f

    const/4 v5, -0x1

    const/4 v6, -0x2

    const/high16 v7, 0x41c00000    # 24.0f

    const/4 v8, 0x0

    .line 265
    invoke-static/range {v5 .. v10}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v1

    invoke-virtual {v2, v0, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 271
    new-instance v1, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v3

    iget-object v5, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v1, v3, v5}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 272
    sget v3, Lorg/telegram/messenger/R$string;->CustomSavePath:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 273
    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->makeCustomMaxHeight()Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 274
    invoke-virtual {v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setView(Landroid/view/View;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    const/high16 v2, 0x43920000    # 292.0f

    .line 275
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-virtual {v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setWidth(I)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 276
    sget v2, Lorg/telegram/messenger/R$string;->Done:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda16;

    invoke-direct {v3, v0, p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda16;-><init>(Lorg/telegram/ui/Components/EditTextBoldCursor;Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;)V

    invoke-virtual {v1, v2, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 286
    sget v2, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda17;

    invoke-direct {v3}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda17;-><init>()V

    invoke-virtual {v1, v2, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 289
    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v1

    .line 290
    new-instance v2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda18;

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda18;-><init>(Lorg/telegram/ui/Components/EditTextBoldCursor;)V

    invoke-virtual {v1, v2}, Landroid/app/Dialog;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)V

    .line 291
    new-instance v2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda19;

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda19;-><init>(Lorg/telegram/ui/Components/EditTextBoldCursor;)V

    invoke-virtual {v1, v2}, Landroid/app/Dialog;->setOnShowListener(Landroid/content/DialogInterface$OnShowListener;)V

    .line 296
    invoke-virtual {v1, v4}, Lorg/telegram/ui/ActionBar/AlertDialog;->setDismissDialogByButtons(Z)V

    .line 298
    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method


# virtual methods
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

    move-object/from16 v14, p1

    .line 91
    sget v0, Lorg/telegram/messenger/R$string;->TranslateMessages:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 92
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->SHOW_TRANSLATE_BUTTON:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->ShowTranslateButton:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->getContextTranslateValue()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v11, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "showTranslateButton"

    const/4 v8, 0x0

    const/4 v9, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 93
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->SHOW_TRANSLATE_CHAT_BUTTON:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->ShowTranslateChatButton:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->getChatTranslateValue()Z

    move-result v3

    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->isChatTranslateLocked()Z

    move-result v0

    if-eqz v0, :cond_0

    sget v0, Lorg/telegram/messenger/R$drawable;->permission_locked:I

    :goto_0
    move v5, v0

    goto :goto_1

    :cond_0
    const/4 v0, 0x0

    goto :goto_0

    :goto_1
    const/16 v10, 0x1a8

    const/4 v11, 0x0

    const/4 v4, 0x0

    const/4 v6, 0x0

    const-string v7, "showTranslateChatButton"

    const/4 v8, 0x0

    const/4 v9, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 94
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->TRANSLATION_PROVIDERS:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v2, Lorg/telegram/messenger/R$string;->TranslationProvider:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->translationProviders:[Ljava/lang/CharSequence;

    const/4 v15, 0x0

    if-nez v3, :cond_1

    move-object v3, v15

    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTranslationProvider()I

    move-result v4

    aget-object v4, v3, v4

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const-string v8, "translationProvider"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 95
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->TRANSLATION_TARGET_LANGUAGE:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->TranslationTarget:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCurrentLangName()Ljava/lang/String;

    move-result-object v4

    const-string v8, "translationTargetLanguage"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 96
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->DO_NOT_TRANSLATE_LANGUAGES:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->DoNotTranslate:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->getDoNotTranslateValue()Ljava/lang/CharSequence;

    move-result-object v4

    const-string v8, "doNotTranslateLanguages"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 97
    sget v0, Lorg/telegram/messenger/R$string;->TranslateMessagesInfo1:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 99
    sget v0, Lorg/telegram/messenger/R$string;->General:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 100
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->DISABLE_NUMBER_ROUNDING:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->DisableNumberRounding:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDisableNumberRounding()Z

    move-result v3

    const/16 v10, 0x130

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v8, 0x0

    const-string v4, "1.23K -> 1,234"

    const-string v7, "disableNumberRounding"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 101
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->FORMAT_TIME_WITH_SECONDS:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->FormatTimeWithSeconds:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getFormatTimeWithSeconds()Z

    move-result v3

    const-string v4, "12:34 -> 12:34:56"

    const-string v7, "formatTimeWithSeconds"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 102
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->IN_APP_VIBRATION:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->InAppVibration:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getInAppVibration()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const-string v7, "inAppVibration"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 103
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->DISABLE_NOTIFICATION_DELAY:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->DisableNotificationDelay:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDisableNotificationDelay()Z

    move-result v3

    const-string v7, "disableNotificationDelay"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 104
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->FILTER_ZALGO:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->FilterZalgo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getFilterZalgo()Z

    move-result v3

    const-string v7, "filterZalgo"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 105
    sget v1, Lorg/telegram/messenger/R$string;->FilterZalgoInfo:I

    const-string v2, "Z\u0337\u034c\u034da\u0338\u0304\u031cl\u0338\u0302\u031eg\u0337\u034d\u031do\u0336\u0313\u0329"

    invoke-static {v2}, Lcom/exteragram/messenger/utils/text/ZalgoFilter;->filter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    filled-new-array {v2}, [Ljava/lang/Object;

    move-result-object v2

    invoke-static {v1, v2}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 107
    sget v1, Lorg/telegram/messenger/R$string;->DownloadSpeedBoost:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 109
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->DOWNLOAD_SPEED_BOOST:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->getId()I

    move-result v1

    .line 111
    sget v2, Lorg/telegram/messenger/R$string;->BlurOff:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 112
    sget v3, Lorg/telegram/messenger/R$string;->SpeedFast:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 113
    sget v4, Lorg/telegram/messenger/R$string;->Ultra:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    filled-new-array {v2, v3, v4}, [Ljava/lang/String;

    move-result-object v2

    .line 115
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDownloadSpeedBoost()I

    move-result v3

    .line 116
    new-instance v4, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda1;

    invoke-direct {v4}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda1;-><init>()V

    .line 108
    invoke-static {v1, v2, v3, v4}, Lorg/telegram/ui/Components/UItem;->asSlideView(I[Ljava/lang/String;ILorg/telegram/messenger/Utilities$Callback;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 116
    const-string v2, "downloadSpeedBoost"

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 108
    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 117
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->UPLOAD_SPEED_BOOST:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v2, Lorg/telegram/messenger/R$string;->UploadSpeedBoost:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUploadSpeedBoost()Z

    move-result v3

    const/4 v4, 0x0

    const-string v7, "uploadSpeedBoost"

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 118
    sget v0, Lorg/telegram/messenger/R$string;->SpeedBoostInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 120
    sget v0, Lorg/telegram/messenger/R$string;->StorageSettings:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 121
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->CUSTOM_SAVE_PATH:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->CustomSavePath:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->getCustomSavePathDisplayValue()Ljava/lang/String;

    move-result-object v4

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v10, 0x0

    const-string v8, "customSavePath"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 122
    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->getCustomSavePathInfo()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 124
    sget v0, Lorg/telegram/messenger/R$string;->Profile:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 125
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->RELATIVE_LAST_SEEN:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->RelativeLastSeen:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getRelativeLastSeen()Z

    move-result v3

    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->getFiveMinutesAgo()I

    move-result v0

    int-to-long v4, v0

    const/4 v0, 0x1

    new-array v0, v0, [Z

    invoke-static {v4, v5, v15, v0}, Lorg/telegram/messenger/LocaleController;->formatDateOnline(J[Z[Z)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x130

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v8, 0x0

    const-string v7, "relativeLastSeen"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 126
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->HIDE_PHONE_NUMBER:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->HidePhoneNumber:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHidePhoneNumber()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const-string v7, "hidePhoneNumber"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 127
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->SHOW_ID_AND_DC:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v2, Lorg/telegram/messenger/R$string;->ShowIdAndDc:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->idOptions:[Ljava/lang/CharSequence;

    if-nez v3, :cond_2

    goto :goto_2

    :cond_2
    move-object v15, v3

    :goto_2
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getShowIdAndDc()I

    move-result v3

    aget-object v4, v15, v3

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "showIdAndDc"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 128
    sget v0, Lorg/telegram/messenger/R$string;->ShowIdAndDcInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 130
    sget v0, Lorg/telegram/messenger/R$string;->ArchivedChats:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 131
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->HIDE_ARCHIVE_FOLDER:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->HideArchiveFolder:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideArchiveFolder()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "hideArchiveFolder"

    const/4 v8, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 132
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideArchiveFolder()Z

    move-result v0

    xor-int/lit8 v12, v0, 0x1

    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->ARCHIVE_ON_PULL:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->ArchiveOnPull:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getArchiveOnPull()Z

    move-result v3

    const-string v7, "archiveOnPull"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-static {v14, v12, v1}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    .line 133
    sget-object v1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->DISABLE_UNARCHIVE_SWIPE:Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    sget v0, Lorg/telegram/messenger/R$string;->DisableUnarchiveSwipe:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDisableUnarchiveSwipe()Z

    move-result v3

    const-string v7, "disableUnarchiveSwipe"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 134
    sget v0, Lorg/telegram/messenger/R$string;->DisableUnarchiveSwipeInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 88
    sget p0, Lorg/telegram/messenger/R$string;->General:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public initializeOptionStrings()V
    .locals 3

    .line 81
    sget v0, Lorg/telegram/messenger/R$string;->Hide:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x3

    .line 83
    new-array v1, v1, [Ljava/lang/CharSequence;

    const/4 v2, 0x0

    aput-object v0, v1, v2

    const-string v0, "Telegram API"

    const/4 v2, 0x1

    aput-object v0, v1, v2

    const-string v0, "Bot API"

    const/4 v2, 0x2

    aput-object v0, v1, v2

    .line 80
    iput-object v1, p0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->idOptions:[Ljava/lang/CharSequence;

    .line 85
    invoke-static {}, Lcom/exteragram/messenger/translator/TranslationProviders;->names()[Ljava/lang/CharSequence;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->translationProviders:[Ljava/lang/CharSequence;

    return-void
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 6

    .line 138
    iget p2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-lez p2, :cond_3

    invoke-static {}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p3

    invoke-interface {p3}, Ljava/util/List;->size()I

    move-result p3

    if-le p2, p3, :cond_0

    goto/16 :goto_1

    .line 139
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p2

    iget p3, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 p4, 0x1

    sub-int/2addr p3, p4

    invoke-interface {p2, p3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;

    .line 140
    sget-object p3, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    aget p2, p3, p2

    const/4 p3, 0x0

    packed-switch p2, :pswitch_data_0

    goto/16 :goto_1

    .line 182
    :pswitch_0
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda6;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda6;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 181
    :pswitch_1
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda5;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda5;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 178
    :pswitch_2
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda4;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda4;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 179
    iget p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->currentAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController;->checkArchiveFolder()V

    return-void

    .line 173
    :pswitch_3
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->idOptions:[Ljava/lang/CharSequence;

    if-nez p2, :cond_1

    move-object v2, p3

    goto :goto_0

    :cond_1
    move-object v2, p2

    :goto_0
    sget p2, Lorg/telegram/messenger/R$string;->ShowIdAndDc:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getShowIdAndDc()I

    move-result v4

    new-instance v5, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda3;

    invoke-direct {v5, p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;)V

    move-object v0, p0

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 169
    :pswitch_4
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda15;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda15;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 170
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->handleHidePhoneNumberClick()V

    return-void

    .line 167
    :pswitch_5
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->showCustomSavePathDialog()V

    return-void

    .line 166
    :pswitch_6
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda14;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda14;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 165
    :pswitch_7
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda13;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda13;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 164
    :pswitch_8
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda12;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda12;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 163
    :pswitch_9
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda11;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda11;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 162
    :pswitch_a
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda10;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda10;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 159
    :pswitch_b
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda9;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda9;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 160
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->handleFormatTimeWithSecondsClick()V

    return-void

    .line 157
    :pswitch_c
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda8;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda8;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 155
    :pswitch_d
    new-instance p1, Lorg/telegram/ui/RestrictedLanguagesSelectActivity;

    invoke-direct {p1}, Lorg/telegram/ui/RestrictedLanguagesSelectActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 153
    :pswitch_e
    new-instance p1, Lorg/telegram/ui/RestrictedLanguagesSelectActivity;

    invoke-direct {p1, p4}, Lorg/telegram/ui/RestrictedLanguagesSelectActivity;-><init>(I)V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 147
    :pswitch_f
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->translationProviders:[Ljava/lang/CharSequence;

    if-nez p2, :cond_2

    move-object p2, p3

    :cond_2
    sget p3, Lorg/telegram/messenger/R$string;->TranslationProvider:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTranslationProvider()I

    move-result p4

    new-instance p5, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda7;

    invoke-direct {p5, p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;)V

    invoke-virtual/range {p0 .. p5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 145
    :pswitch_10
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->handleChatTranslateClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 142
    :pswitch_11
    new-instance p2, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda2;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;)V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 143
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;->handleContextTranslateClick()V

    :cond_3
    :goto_1
    return-void

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
