.class public final Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;
.implements Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u00ac\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\u0007\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\t\u0008\u0016\u00a2\u0006\u0004\u0008\u0004\u0010\u0005B\u001d\u0008\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\u0008\u0002\u0010\u0008\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\u0008\u0004\u0010\nBA\u0008\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0008\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\u000c\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u0008\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\u0008\u0002\u0010\u0008\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\u0008\u0004\u0010\u0011J\u0010\u0010&\u001a\u00020\u00002\u0008\u0010\u0019\u001a\u0004\u0018\u00010\tJ\u0008\u0010\'\u001a\u00020\tH\u0016J\u0008\u0010(\u001a\u00020)H\u0016J\u0008\u0010*\u001a\u00020+H\u0016J5\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u00172\u0016\u0010/\u001a\u000c\u0012\u0008\u0008\u0001\u0012\u0004\u0018\u00010100\"\u0004\u0018\u000101H\u0016\u00a2\u0006\u0002\u00102J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0014J\u0010\u00107\u001a\u00020+2\u0006\u00108\u001a\u00020)H\u0016J\u0008\u00109\u001a\u00020+H\u0002J\u0010\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020)H\u0002J\u0014\u0010<\u001a\u0004\u0018\u00010\t2\u0008\u0010=\u001a\u0004\u0018\u00010\tH\u0002J\u0006\u0010>\u001a\u00020+J(\u0010?\u001a\u00020+2\u0016\u0010@\u001a\u0012\u0012\u0004\u0012\u00020B0Aj\u0008\u0012\u0004\u0012\u00020B`C2\u0006\u0010D\u001a\u00020EH\u0014J0\u0010F\u001a\u00020+2\u0006\u0010G\u001a\u00020B2\u0006\u0010H\u001a\u0002042\u0006\u0010I\u001a\u00020\u00172\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020KH\u0014J4\u0010M\u001a\u00020+2\u0006\u0010G\u001a\u00020B2\u0006\u0010N\u001a\u00020\t2\u0006\u0010O\u001a\u00020)2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020+0QH\u0002J0\u0010R\u001a\u00020)2\u0006\u0010G\u001a\u00020B2\u0006\u0010H\u001a\u0002042\u0006\u0010I\u001a\u00020\u00172\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020KH\u0014J \u0010S\u001a\u00020+2\u0006\u0010G\u001a\u00020B2\u0006\u0010H\u001a\u0002042\u0006\u0010N\u001a\u00020\tH\u0002J \u0010T\u001a\u00020+2\u0006\u0010G\u001a\u00020B2\u0006\u0010H\u001a\u0002042\u0006\u0010N\u001a\u00020\tH\u0002J\u0010\u0010U\u001a\u00020\u00172\u0006\u0010G\u001a\u00020\u000eH\u0002J\u0018\u0010V\u001a\u00020+2\u0006\u0010G\u001a\u00020B2\u0006\u0010W\u001a\u00020\u0010H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0008\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\u000c\n\u0004\u0008\u001e\u0010\u001f\u001a\u0004\u0008\u001c\u0010\u001dR\u001e\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\t0!j\u0008\u0012\u0004\u0012\u00020\t`\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006X"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;",
        "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;",
        "<init>",
        "()V",
        "plugin",
        "Lcom/exteragram/messenger/plugins/Plugin;",
        "targetSettingName",
        "",
        "(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V",
        "title",
        "settingsList",
        "",
        "Lcom/exteragram/messenger/plugins/models/SettingItem;",
        "createSubFragmentCallback",
        "Lcom/chaquo/python/PyObject;",
        "(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Ljava/util/List;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V",
        "customTitle",
        "settingItems",
        "resetItem",
        "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;",
        "targetSettingItemId",
        "",
        "Ljava/lang/Integer;",
        "settingsLinkPrefix",
        "translation",
        "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;",
        "getTranslation",
        "()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;",
        "translation$delegate",
        "Lkotlin/Lazy;",
        "visibleTexts",
        "Ljava/util/LinkedHashSet;",
        "Lkotlin/collections/LinkedHashSet;",
        "translateItem",
        "translateButton",
        "Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;",
        "setSettingsLinkPrefix",
        "getTitle",
        "onFragmentCreate",
        "",
        "onFragmentDestroy",
        "",
        "didReceivedNotification",
        "id",
        "account",
        "args",
        "",
        "",
        "(II[Ljava/lang/Object;)V",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "onTranslationChanged",
        "failed",
        "onTranslateClick",
        "updateTranslateItem",
        "animated",
        "tr",
        "text",
        "checkTargetSetting",
        "fillItems",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "Lkotlin/collections/ArrayList;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "position",
        "x",
        "",
        "y",
        "toggleSwitch",
        "key",
        "newValue",
        "setViewChecked",
        "Lkotlin/Function1;",
        "onLongClick",
        "showStringInputDialog",
        "showSelectorDialog",
        "getStableId",
        "openSubFragmentNative",
        "callback",
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
        "SMAP\nPluginSettingsActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginSettingsActivity.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,723:1\n1#2:724\n11693#3:725\n12040#3,3:726\n11693#3:731\n12040#3,3:732\n37#4,2:729\n*S KotlinDebug\n*F\n+ 1 PluginSettingsActivity.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsActivity\n*L\n368#1:725\n368#1:726,3\n634#1:731\n634#1:732,3\n368#1:729,2\n*E\n"
    }
.end annotation


# instance fields
.field private createSubFragmentCallback:Lcom/chaquo/python/PyObject;

.field private customTitle:Ljava/lang/String;

.field private plugin:Lcom/exteragram/messenger/plugins/Plugin;

.field private resetItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

.field private settingItems:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "+",
            "Lcom/exteragram/messenger/plugins/models/SettingItem;",
            ">;"
        }
    .end annotation
.end field

.field private settingsLinkPrefix:Ljava/lang/String;

.field private targetSettingItemId:Ljava/lang/Integer;

.field private targetSettingName:Ljava/lang/String;

.field private translateButton:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

.field private translateItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

.field private final translation$delegate:Lkotlin/Lazy;

.field private final visibleTexts:Ljava/util/LinkedHashSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/LinkedHashSet<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static $r8$lambda$-pUp2ILhhcyv5WxvS1CSPTqdrFI(Lorg/telegram/ui/Components/EditTextBoldCursor;Lkotlin/jvm/internal/Ref$ObjectRef;Landroid/view/View;Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/lang/String;Lcom/exteragram/messenger/plugins/models/InputSetting;)Lkotlin/Unit;
    .locals 0

    .line 587
    invoke-virtual {p0}, Lcom/exteragram/messenger/components/ReceiveContentEditText;->getText()Landroid/text/Editable;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    if-nez p0, :cond_1

    const-string p0, ""

    .line 588
    :cond_1
    iget-object p1, p1, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    check-cast p1, Lorg/telegram/ui/ActionBar/AlertDialog;

    if-eqz p1, :cond_2

    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    .line 589
    :cond_2
    check-cast p2, Lorg/telegram/ui/Cells/TextCell;

    const/4 p1, 0x1

    invoke-virtual {p2, p0, p1}, Lorg/telegram/ui/Cells/TextCell;->setValue(Ljava/lang/CharSequence;Z)V

    .line 590
    sget-object p1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance p2, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda3;

    invoke-direct {p2, p3, p4, p0, p5}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/models/InputSetting;)V

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    .line 594
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$0EPq7QsU6t-rt8nXerx8Tdh5Ql4(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/models/InputSetting;)V
    .locals 1

    .line 591
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    .line 592
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/models/InputSetting;->getOnChangeCallback()Lcom/chaquo/python/PyObject;

    move-result-object p3

    invoke-virtual {v0, p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/PluginsController;->setPluginSettingAndTriggerOnChange(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Lcom/chaquo/python/PyObject;)V

    return-void
.end method

.method public static $r8$lambda$1-Ja_dxkLD7loik7O2cLHRMxUJg(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V
    .locals 3

    .line 212
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v0

    .line 213
    sget v1, Lorg/telegram/messenger/R$raw;->info:I

    .line 214
    sget v2, Lorg/telegram/messenger/R$string;->ResetPluginSettings:I

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {v2, p0}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    .line 212
    invoke-virtual {v0, v1, p0}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 215
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static $r8$lambda$1DSLgS5vJm0OSFsCSJZFD5-2nto(Lorg/telegram/ui/Components/EditTextBoldCursor;Landroid/content/DialogInterface;)V
    .locals 0

    .line 615
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->hideKeyboard(Landroid/view/View;)V

    return-void
.end method

.method public static $r8$lambda$6Z6uxA2UOxlGL4PYCk_0i6kuoQo(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Lcom/chaquo/python/PyObject;Lorg/telegram/ui/Components/UItem;)V
    .locals 4

    .line 689
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    const/4 v3, 0x0

    if-nez v2, :cond_0

    move-object v2, v3

    :cond_0
    invoke-virtual {v1, v2}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Lcom/exteragram/messenger/plugins/Plugin;)Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    :cond_1
    const/4 v1, 0x0

    .line 693
    :try_start_0
    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {p1, v1}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v1

    if-nez v1, :cond_2

    goto :goto_0

    .line 694
    :cond_2
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    const-string/jumbo v2, "python"

    invoke-virtual {v0, v2}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    instance-of v2, v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;

    if-eqz v2, :cond_3

    move-object v3, v0

    check-cast v3, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;

    :cond_3
    if-nez v3, :cond_4

    :goto_0
    return-void

    .line 696
    :cond_4
    invoke-virtual {v1}, Lcom/chaquo/python/PyObject;->asList()Ljava/util/List;

    move-result-object v0

    invoke-virtual {v3, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->parsePySettingDefinitions(Ljava/util/List;)Ljava/util/List;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 702
    new-instance v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda18;

    invoke-direct {v1, v0, p2, p0, p1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda18;-><init>(Ljava/util/List;Lorg/telegram/ui/Components/UItem;Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Lcom/chaquo/python/PyObject;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    :catch_0
    move-exception p0

    .line 698
    const-string p1, "Error opening subfragment"

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public static $r8$lambda$9pY5TzlHVvCQHIyjDvEtTS9-eys(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Lcom/chaquo/python/PyObject;)V
    .locals 4

    .line 137
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    const/4 v3, 0x0

    if-nez v2, :cond_0

    move-object v2, v3

    :cond_0
    invoke-virtual {v1, v2}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Lcom/exteragram/messenger/plugins/Plugin;)Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    :cond_1
    const/4 v1, 0x0

    .line 139
    :try_start_0
    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {p1, v1}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p1

    if-nez p1, :cond_2

    goto :goto_0

    .line 140
    :cond_2
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    const-string/jumbo v1, "python"

    invoke-virtual {v0, v1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    instance-of v1, v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;

    if-eqz v1, :cond_3

    move-object v3, v0

    check-cast v3, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;

    :cond_3
    if-nez v3, :cond_4

    goto :goto_0

    .line 142
    :cond_4
    invoke-virtual {p1}, Lcom/chaquo/python/PyObject;->asList()Ljava/util/List;

    move-result-object p1

    invoke-virtual {v3, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->parsePySettingDefinitions(Ljava/util/List;)Ljava/util/List;

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 147
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda14;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda14;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/util/List;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :catch_0
    :goto_0
    return-void
.end method

.method public static $r8$lambda$Ce6DjGUz1AF3cZ-vPIAbGAMv1I8(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 206
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBar;->requestLayout()V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$FtON3Qfl_JLDF4AkVSaK3Vu--aU(Lkotlin/jvm/internal/Ref$ObjectRef;Landroid/view/View;Ljava/util/List;ILcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/lang/String;Lcom/exteragram/messenger/plugins/models/SelectorSetting;Landroid/view/View;)V
    .locals 0

    .line 653
    iget-object p0, p0, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    check-cast p0, Landroid/app/Dialog;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/app/Dialog;->dismiss()V

    .line 654
    :cond_0
    check-cast p1, Lorg/telegram/ui/Cells/TextCell;

    invoke-interface {p2, p3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/CharSequence;

    const/4 p2, 0x1

    invoke-virtual {p1, p0, p2}, Lorg/telegram/ui/Cells/TextCell;->setValue(Ljava/lang/CharSequence;Z)V

    .line 655
    sget-object p0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance p1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda23;

    invoke-direct {p1, p4, p5, p3, p6}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda23;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/lang/String;ILcom/exteragram/messenger/plugins/models/SelectorSetting;)V

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$HBVQq8PwM6cRiVfKvot_s_07HcA(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/lang/String;ILcom/exteragram/messenger/plugins/models/SelectorSetting;)V
    .locals 1

    .line 656
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    .line 657
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getOnChangeCallback()Lcom/chaquo/python/PyObject;

    move-result-object p3

    invoke-virtual {v0, p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/PluginsController;->setPluginSettingAndTriggerOnChange(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Lcom/chaquo/python/PyObject;)V

    return-void
.end method

.method public static $r8$lambda$JT5omKF85jr8iGVjIOakR0qPPKE(Lorg/telegram/ui/Components/EditTextBoldCursor;Landroid/content/DialogInterface;)V
    .locals 0

    .line 617
    invoke-virtual {p0}, Landroid/view/View;->requestFocus()Z

    .line 618
    invoke-virtual {p0}, Landroid/widget/TextView;->length()I

    move-result p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setSelection(I)V

    .line 619
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    return-void
.end method

.method public static $r8$lambda$Mp2TFAjnIDnsFeYKm2SBjgKL0yY(Landroid/view/View;Z)Lkotlin/Unit;
    .locals 0

    .line 475
    check-cast p0, Lorg/telegram/ui/Cells/TextCheckCell;

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Cells/TextCheckCell;->setChecked(Z)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$OFWPI4yzwIJOrUvW8Cepcz2YkHA(Ljava/util/List;Lorg/telegram/ui/Components/UItem;Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Lcom/chaquo/python/PyObject;)V
    .locals 11

    .line 703
    move-object v0, p0

    check-cast v0, Ljava/util/Collection;

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_d

    .line 704
    iget-object v0, p1, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    instance-of v1, v0, Lcom/exteragram/messenger/plugins/models/TextSetting;

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    check-cast v0, Lcom/exteragram/messenger/plugins/models/TextSetting;

    goto :goto_0

    :cond_0
    move-object v0, v2

    :goto_0
    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getText()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_1

    goto :goto_1

    :cond_1
    iget-object v0, p1, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    :goto_1
    if-eqz v0, :cond_4

    .line 705
    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_4

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-lez v1, :cond_2

    goto :goto_2

    :cond_2
    move-object v0, v2

    :goto_2
    if-nez v0, :cond_3

    goto :goto_4

    :cond_3
    :goto_3
    move-object v5, v0

    goto :goto_5

    .line 706
    :cond_4
    :goto_4
    iget-object v0, p2, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->customTitle:Ljava/lang/String;

    if-nez v0, :cond_3

    .line 707
    iget-object v0, p2, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v0, :cond_5

    move-object v0, v2

    :cond_5
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v0

    goto :goto_3

    .line 709
    :goto_5
    iget-object v0, p2, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->settingsLinkPrefix:Ljava/lang/String;

    const-string v1, ""

    if-eqz v0, :cond_9

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_6

    goto :goto_8

    .line 712
    :cond_6
    iget-object v0, p2, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->settingsLinkPrefix:Ljava/lang/String;

    iget-object p1, p1, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    if-eqz p1, :cond_7

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/SettingItem;->getLinkAlias()Ljava/lang/String;

    move-result-object p1

    goto :goto_6

    :cond_7
    move-object p1, v2

    :goto_6
    if-nez p1, :cond_8

    goto :goto_7

    :cond_8
    move-object v1, p1

    :goto_7
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ":"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    goto :goto_a

    .line 710
    :cond_9
    :goto_8
    iget-object p1, p1, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    if-eqz p1, :cond_a

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/SettingItem;->getLinkAlias()Ljava/lang/String;

    move-result-object p1

    goto :goto_9

    :cond_a
    move-object p1, v2

    :goto_9
    if-nez p1, :cond_b

    goto :goto_a

    :cond_b
    move-object v1, p1

    .line 716
    :goto_a
    new-instance v3, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;

    iget-object p1, p2, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p1, :cond_c

    move-object v4, v2

    goto :goto_b

    :cond_c
    move-object v4, p1

    :goto_b
    const/16 v9, 0x10

    const/4 v10, 0x0

    const/4 v8, 0x0

    move-object v6, p0

    move-object v7, p3

    invoke-direct/range {v3 .. v10}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Ljava/util/List;Lcom/chaquo/python/PyObject;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 717
    invoke-virtual {v3, v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->setSettingsLinkPrefix(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;

    move-result-object p0

    .line 715
    invoke-virtual {p2, p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    :cond_d
    return-void
.end method

.method public static $r8$lambda$PhH2ExfyC8JNHIRVvp74w06bowU(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V
    .locals 6

    .line 209
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    const/4 v3, 0x0

    if-nez v2, :cond_0

    move-object v2, v3

    :cond_0
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v2

    const/4 v4, 0x0

    const/4 v5, 0x2

    invoke-static {v1, v2, v4, v5, v3}, Lcom/exteragram/messenger/plugins/PluginsController;->clearPluginSettingsPreferences$default(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;ZILjava/lang/Object;)V

    .line 210
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v1, :cond_1

    goto :goto_0

    :cond_1
    move-object v3, v1

    :goto_0
    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->loadPluginSettings(Ljava/lang/String;)V

    .line 211
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda6;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$QtNmdLcrwqKVepYU6Vz2gsC7dA8(Lkotlin/jvm/functions/Function0;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 611
    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void
.end method

.method public static $r8$lambda$RxWgJDfcHix48N9hb55E5aPYwzo(Landroid/view/View;Z)Lkotlin/Unit;
    .locals 0

    .line 476
    check-cast p0, Lorg/telegram/ui/Cells/NotificationsCheckCell;

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Cells/NotificationsCheckCell;->setChecked(Z)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$T_NnNSIT5Jyk2zKST0nB2HCN7WE(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/util/List;)V
    .locals 0

    .line 148
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->settingItems:Ljava/util/List;

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

.method public static $r8$lambda$UzH2mCmxw9lvEAd71iI0aCVth-U(Lorg/telegram/ui/ActionBar/ActionBarMenuItem;Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Landroid/view/View;)V
    .locals 3

    .line 200
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p0

    iget-object v1, p1, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v0, p0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 201
    sget p0, Lorg/telegram/messenger/R$string;->ResetSettings:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 202
    sget p0, Lorg/telegram/messenger/R$string;->ResetPluginSettingsInfo:I

    iget-object v1, p1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    const/4 v2, 0x0

    if-nez v1, :cond_0

    move-object v1, v2

    :cond_0
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v1

    filled-new-array {v1}, [Ljava/lang/Object;

    move-result-object v1

    invoke-static {p0, v1}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object p0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 203
    sget p0, Lorg/telegram/messenger/R$string;->Reset:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    new-instance v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda1;

    invoke-direct {v1, p1, p2}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Landroid/view/View;)V

    invoke-virtual {v0, p0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 219
    sget p0, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 220
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object p0

    .line 222
    invoke-virtual {p1, p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    const/4 p1, -0x1

    .line 223
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object p0

    instance-of p1, p0, Landroid/widget/TextView;

    if-eqz p1, :cond_1

    move-object v2, p0

    check-cast v2, Landroid/widget/TextView;

    :cond_1
    if-eqz v2, :cond_2

    .line 224
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedBold:I

    invoke-static {p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p0

    invoke-virtual {v2, p0}, Landroid/widget/TextView;->setTextColor(I)V

    :cond_2
    return-void
.end method

.method public static $r8$lambda$VrbycZPHCdbIObj6EGdZ3pXZPkI(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 162
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBar;->requestLayout()V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$gAEBM3Bjop7AmlJJRHsVjP6iksk(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/lang/String;ZLcom/chaquo/python/PyObject;)V
    .locals 1

    .line 491
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    .line 492
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-static {p2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p2

    .line 491
    invoke-virtual {v0, p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/PluginsController;->setPluginSettingAndTriggerOnChange(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Lcom/chaquo/python/PyObject;)V

    return-void
.end method

.method public static $r8$lambda$h03KbGNTNb1OtVtW90IDgFBXz1A(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Landroid/view/View;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 7

    .line 204
    iget-object p2, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz p2, :cond_0

    invoke-virtual {p2}, Landroid/view/View;->findFocus()Landroid/view/View;

    move-result-object p2

    if-eqz p2, :cond_0

    invoke-virtual {p2}, Landroid/view/View;->clearFocus()V

    .line 205
    :cond_0
    new-instance v6, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda16;

    invoke-direct {v6, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda16;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V

    const/4 v1, 0x0

    const/high16 v2, 0x3f000000    # 0.5f

    const/4 v3, 0x1

    const/high16 v4, 0x3f800000    # 1.0f

    const/4 v5, 0x1

    move-object v0, p1

    invoke-static/range {v0 .. v6}, Lorg/telegram/messenger/AndroidUtilities;->updateViewVisibilityAnimated(Landroid/view/View;ZFZFZLandroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 208
    sget-object p1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance p2, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda17;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda17;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$kwCmZ5TLqkavBSYxn6tyCG3apKs(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Landroid/view/View;)V
    .locals 0

    .line 186
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->onTranslateClick()V

    return-void
.end method

.method public static $r8$lambda$wxlGKayieU74hzvWkJtTGgqkxNw(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 256
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/ActionBar;->requestLayout()V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$xCyMslpbmlOrWwsP0i6G-lhGMtE(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;
    .locals 1

    .line 75
    sget-object v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->Companion:Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;->of(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$yxSicdVfS2sImvkcDsBjw3ufT3w(Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 612
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 80
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    .line 75
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda15;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda15;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->translation$delegate:Lkotlin/Lazy;

    .line 76
    new-instance v0, Ljava/util/LinkedHashSet;

    invoke-direct {v0}, Ljava/util/LinkedHashSet;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->visibleTexts:Ljava/util/LinkedHashSet;

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/plugins/Plugin;)V
    .locals 2
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 0
    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-direct {p0, p1, v0, v1, v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V
    .locals 0
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 86
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;-><init>()V

    .line 87
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    .line 88
    iput-object p2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->targetSettingName:Ljava/lang/String;

    return-void
.end method

.method public synthetic constructor <init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 82
    :cond_0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Ljava/util/List;Lcom/chaquo/python/PyObject;)V
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/exteragram/messenger/plugins/Plugin;",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "+",
            "Lcom/exteragram/messenger/plugins/models/SettingItem;",
            ">;",
            "Lcom/chaquo/python/PyObject;",
            ")V"
        }
    .end annotation

    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 0
    const/16 v6, 0x10

    const/4 v7, 0x0

    const/4 v5, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    invoke-direct/range {v0 .. v7}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Ljava/util/List;Lcom/chaquo/python/PyObject;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Ljava/util/List;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/exteragram/messenger/plugins/Plugin;",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "+",
            "Lcom/exteragram/messenger/plugins/models/SettingItem;",
            ">;",
            "Lcom/chaquo/python/PyObject;",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 98
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;-><init>()V

    .line 99
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    .line 100
    iput-object p2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->customTitle:Ljava/lang/String;

    .line 101
    iput-object p3, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->settingItems:Ljava/util/List;

    .line 102
    iput-object p4, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->createSubFragmentCallback:Lcom/chaquo/python/PyObject;

    .line 103
    iput-object p5, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->targetSettingName:Ljava/lang/String;

    return-void
.end method

.method public synthetic constructor <init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Ljava/util/List;Lcom/chaquo/python/PyObject;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 6

    and-int/lit8 p6, p6, 0x10

    if-eqz p6, :cond_0

    const/4 p5, 0x0

    :cond_0
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move-object v5, p5

    .line 91
    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Ljava/util/List;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    return-void
.end method

.method private final getStableId(Lcom/exteragram/messenger/plugins/models/SettingItem;)I
    .locals 1

    .line 672
    instance-of p0, p1, Lcom/exteragram/messenger/plugins/models/SwitchSetting;

    if-eqz p0, :cond_0

    check-cast p1, Lcom/exteragram/messenger/plugins/models/SwitchSetting;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/SwitchSetting;->getKey()Ljava/lang/String;

    move-result-object p0

    const-string/jumbo p1, "switch"

    filled-new-array {p1, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0

    .line 673
    :cond_0
    instance-of p0, p1, Lcom/exteragram/messenger/plugins/models/InputSetting;

    if-eqz p0, :cond_1

    check-cast p1, Lcom/exteragram/messenger/plugins/models/InputSetting;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/InputSetting;->getKey()Ljava/lang/String;

    move-result-object p0

    const-string/jumbo p1, "input"

    filled-new-array {p1, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0

    .line 674
    :cond_1
    instance-of p0, p1, Lcom/exteragram/messenger/plugins/models/EditTextSetting;

    if-eqz p0, :cond_2

    check-cast p1, Lcom/exteragram/messenger/plugins/models/EditTextSetting;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/EditTextSetting;->getKey()Ljava/lang/String;

    move-result-object p0

    const-string p1, "edit"

    filled-new-array {p1, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0

    .line 675
    :cond_2
    instance-of p0, p1, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    if-eqz p0, :cond_3

    check-cast p1, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getKey()Ljava/lang/String;

    move-result-object p0

    const-string/jumbo p1, "selector"

    filled-new-array {p1, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0

    .line 676
    :cond_3
    instance-of p0, p1, Lcom/exteragram/messenger/plugins/models/HeaderSetting;

    if-eqz p0, :cond_4

    check-cast p1, Lcom/exteragram/messenger/plugins/models/HeaderSetting;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/HeaderSetting;->getText()Ljava/lang/String;

    move-result-object p0

    const-string/jumbo p1, "header"

    filled-new-array {p1, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0

    .line 677
    :cond_4
    instance-of p0, p1, Lcom/exteragram/messenger/plugins/models/DividerSetting;

    if-eqz p0, :cond_5

    check-cast p1, Lcom/exteragram/messenger/plugins/models/DividerSetting;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/DividerSetting;->getText()Ljava/lang/String;

    move-result-object p0

    const-string p1, "divider"

    filled-new-array {p1, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0

    .line 678
    :cond_5
    instance-of p0, p1, Lcom/exteragram/messenger/plugins/models/TextSetting;

    if-eqz p0, :cond_6

    check-cast p1, Lcom/exteragram/messenger/plugins/models/TextSetting;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getText()Ljava/lang/String;

    move-result-object p0

    const-string/jumbo p1, "text"

    filled-new-array {p1, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0

    .line 679
    :cond_6
    instance-of p0, p1, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    if-eqz p0, :cond_a

    .line 681
    check-cast p1, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/CustomSetting;->getItem()Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    const/4 v0, 0x0

    if-eqz p0, :cond_7

    iget p0, p0, Lorg/telegram/ui/Components/UItem;->id:I

    :goto_0
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    goto :goto_1

    :cond_7
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/CustomSetting;->getFactory()Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;

    move-result-object p0

    if-eqz p0, :cond_8

    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result p0

    goto :goto_0

    :cond_8
    move-object p0, v0

    .line 682
    :goto_1
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/CustomSetting;->getFactoryArgs()Lcom/chaquo/python/PyObject;

    move-result-object p1

    if-eqz p1, :cond_9

    invoke-virtual {p1}, Lcom/chaquo/python/PyObject;->hashCode()I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    :cond_9
    const-string p1, "custom"

    filled-new-array {p1, p0, v0}, [Ljava/lang/Object;

    move-result-object p0

    .line 679
    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0

    .line 684
    :cond_a
    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result p0

    return p0
.end method

.method private final getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;
    .locals 0

    .line 75
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->translation$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    return-object p0
.end method

.method private final onTranslateClick()V
    .locals 1

    .line 244
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->isLoading()Z

    move-result v0

    if-nez v0, :cond_0

    .line 245
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v0

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->getEnabled()Z

    move-result p0

    xor-int/lit8 p0, p0, 0x1

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->setEnabled(Z)V

    :cond_0
    return-void
.end method

.method private final openSubFragmentNative(Lorg/telegram/ui/Components/UItem;Lcom/chaquo/python/PyObject;)V
    .locals 2

    .line 688
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda19;

    invoke-direct {v1, p0, p2, p1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda19;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Lcom/chaquo/python/PyObject;Lorg/telegram/ui/Components/UItem;)V

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method

.method private final showSelectorDialog(Lorg/telegram/ui/Components/UItem;Landroid/view/View;Ljava/lang/String;)V
    .locals 16

    move-object/from16 v5, p0

    move-object/from16 v8, p1

    .line 627
    iget-object v0, v8, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    instance-of v1, v0, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    const/4 v9, 0x0

    if-eqz v1, :cond_0

    check-cast v0, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    move-object v7, v0

    goto :goto_0

    :cond_0
    move-object v7, v9

    :goto_0
    if-nez v7, :cond_1

    goto :goto_1

    .line 628
    :cond_1
    invoke-virtual {v5}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_2

    :goto_1
    return-void

    .line 630
    :cond_2
    new-instance v1, Lkotlin/jvm/internal/Ref$ObjectRef;

    invoke-direct {v1}, Lkotlin/jvm/internal/Ref$ObjectRef;-><init>()V

    .line 631
    new-instance v10, Landroid/widget/LinearLayout;

    invoke-virtual {v5}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-direct {v10, v0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/4 v11, 0x1

    .line 632
    invoke-virtual {v10, v11}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 634
    invoke-virtual {v7}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getItems()[Ljava/lang/String;

    move-result-object v0

    .line 11693
    new-instance v3, Ljava/util/ArrayList;

    array-length v2, v0

    invoke-direct {v3, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 12040
    array-length v2, v0

    const/4 v12, 0x0

    move v4, v12

    :goto_2
    if-ge v4, v2, :cond_4

    aget-object v6, v0, v4

    .line 634
    invoke-direct {v5}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v13

    invoke-virtual {v13, v6}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->text(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    if-nez v6, :cond_3

    const-string v6, ""

    .line 12041
    :cond_3
    invoke-interface {v3, v6}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    add-int/lit8 v4, v4, 0x1

    goto :goto_2

    .line 635
    :cond_4
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    .line 636
    iget-object v2, v5, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v2, :cond_5

    move-object v2, v9

    :cond_5
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v7}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getDefaultValue()I

    move-result v4

    move-object/from16 v6, p3

    invoke-virtual {v0, v2, v6, v4}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginSettingInt(Ljava/lang/String;Ljava/lang/String;I)I

    move-result v13

    .line 638
    invoke-interface {v3}, Ljava/util/Collection;->size()I

    move-result v14

    move v4, v12

    :goto_3
    if-ge v4, v14, :cond_7

    .line 639
    new-instance v15, Lorg/telegram/ui/Cells/RadioColorCell;

    invoke-virtual {v5}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-direct {v15, v0}, Lorg/telegram/ui/Cells/RadioColorCell;-><init>(Landroid/content/Context;)V

    const/high16 v0, 0x40800000    # 4.0f

    .line 640
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    invoke-virtual {v15, v2, v12, v0, v12}, Landroid/view/View;->setPadding(IIII)V

    .line 642
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_radioBackground:I

    invoke-static {v0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v0

    .line 643
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_dialogRadioBackgroundChecked:I

    invoke-static {v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v2

    .line 641
    invoke-virtual {v15, v0, v2}, Lorg/telegram/ui/Cells/RadioColorCell;->setCheckColor(II)V

    .line 645
    invoke-interface {v3, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/CharSequence;

    if-ne v13, v4, :cond_6

    move v2, v11

    goto :goto_4

    :cond_6
    move v2, v12

    :goto_4
    invoke-virtual {v15, v0, v2}, Lorg/telegram/ui/Cells/RadioColorCell;->setTextAndValue(Ljava/lang/CharSequence;Z)V

    .line 647
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_listSelector:I

    invoke-static {v0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v0

    const/4 v2, 0x2

    .line 646
    invoke-static {v0, v2}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(II)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {v15, v0}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 651
    invoke-virtual {v10, v15}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 652
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda20;

    move-object/from16 v2, p2

    invoke-direct/range {v0 .. v7}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda20;-><init>(Lkotlin/jvm/internal/Ref$ObjectRef;Landroid/view/View;Ljava/util/List;ILcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/lang/String;Lcom/exteragram/messenger/plugins/models/SelectorSetting;)V

    invoke-virtual {v15, v0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    add-int/lit8 v4, v4, 0x1

    move-object/from16 v6, p3

    goto :goto_3

    .line 662
    :cond_7
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {v5}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-direct {v0, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 663
    iget-object v2, v8, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 664
    invoke-virtual {v0, v10}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setView(Landroid/view/View;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 665
    sget v2, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2, v9}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 666
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v0

    .line 662
    iput-object v0, v1, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    .line 668
    check-cast v0, Landroid/app/Dialog;

    invoke-virtual {v5, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method

.method private final showStringInputDialog(Lorg/telegram/ui/Components/UItem;Landroid/view/View;Ljava/lang/String;)V
    .locals 16

    move-object/from16 v4, p0

    move-object/from16 v0, p1

    .line 528
    iget-object v1, v0, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    instance-of v2, v1, Lcom/exteragram/messenger/plugins/models/InputSetting;

    const/4 v3, 0x0

    if-eqz v2, :cond_0

    check-cast v1, Lcom/exteragram/messenger/plugins/models/InputSetting;

    move-object v6, v1

    goto :goto_0

    :cond_0
    move-object v6, v3

    :goto_0
    if-nez v6, :cond_1

    goto :goto_1

    .line 529
    :cond_1
    invoke-virtual {v4}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    if-nez v1, :cond_2

    :goto_1
    return-void

    .line 531
    :cond_2
    new-instance v2, Lkotlin/jvm/internal/Ref$ObjectRef;

    invoke-direct {v2}, Lkotlin/jvm/internal/Ref$ObjectRef;-><init>()V

    .line 532
    new-instance v7, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {v4}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v1

    iget-object v5, v4, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v7, v1, v5}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 533
    iget-object v0, v0, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    invoke-virtual {v7, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 536
    new-instance v8, Landroid/widget/LinearLayout;

    invoke-virtual {v4}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-direct {v8, v0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/4 v0, 0x1

    .line 537
    invoke-virtual {v8, v0}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 540
    invoke-virtual {v6}, Lcom/exteragram/messenger/plugins/models/InputSetting;->getSubtext()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 541
    new-instance v5, Landroid/widget/TextView;

    invoke-virtual {v4}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v9

    invoke-direct {v5, v9}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 542
    sget v9, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextBlack:I

    iget-object v10, v4, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v9, v10}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v9

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v9, 0x41800000    # 16.0f

    .line 543
    invoke-virtual {v5, v0, v9}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 544
    invoke-direct {v4}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v9

    invoke-virtual {v9, v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->text(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v5, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const/high16 v13, 0x41c00000    # 24.0f

    const/high16 v14, 0x41400000    # 12.0f

    const/4 v9, -0x1

    const/4 v10, -0x2

    const/high16 v11, 0x41c00000    # 24.0f

    const/high16 v12, 0x40a00000    # 5.0f

    .line 548
    invoke-static/range {v9 .. v14}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v1

    .line 546
    invoke-virtual {v8, v5, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 559
    :cond_3
    new-instance v1, Lorg/telegram/ui/Components/EditTextBoldCursor;

    invoke-virtual {v4}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v5

    invoke-direct {v1, v5}, Lorg/telegram/ui/Components/EditTextBoldCursor;-><init>(Landroid/content/Context;)V

    .line 560
    iput-boolean v0, v1, Lorg/telegram/ui/Components/EditTextBoldCursor;->lineYFix:Z

    const/high16 v5, 0x41900000    # 18.0f

    .line 561
    invoke-virtual {v1, v0, v5}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setTextSize(IF)V

    .line 563
    sget-object v5, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v5

    .line 564
    iget-object v9, v4, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v9, :cond_4

    move-object v9, v3

    :cond_4
    invoke-virtual {v9}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v6}, Lcom/exteragram/messenger/plugins/models/InputSetting;->getDefaultValue()Ljava/lang/String;

    move-result-object v10

    move-object/from16 v11, p3

    invoke-virtual {v5, v9, v11, v10}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginSettingString(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 562
    invoke-virtual {v1, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 566
    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextBlack:I

    iget-object v9, v4, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v5, v9}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v5

    invoke-virtual {v1, v5}, Lorg/telegram/ui/Components/EditTextEffects;->setTextColor(I)V

    .line 567
    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_groupcreate_hintText:I

    iget-object v9, v4, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v5, v9}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v5

    invoke-virtual {v1, v5}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setHintColor(I)V

    .line 568
    sget v5, Lorg/telegram/messenger/R$string;->EnterValue:I

    invoke-static {v5}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setHintText(Ljava/lang/CharSequence;)V

    .line 569
    invoke-virtual {v1, v0}, Landroid/view/View;->setFocusable(Z)V

    const v0, 0x24001

    .line 570
    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setInputType(I)V

    .line 573
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteInputFieldActivated:I

    .line 574
    iget-object v5, v4, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 572
    invoke-static {v0, v5}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v5

    .line 571
    invoke-virtual {v1, v5}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setCursorColor(I)V

    .line 578
    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteInputField:I

    iget-object v9, v4, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v5, v9}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v5

    .line 579
    iget-object v9, v4, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v9}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    .line 580
    sget v9, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedRegular:I

    iget-object v10, v4, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v9, v10}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v9

    .line 577
    invoke-virtual {v1, v5, v0, v9}, Lorg/telegram/ui/Components/EditTextBoldCursor;->setLineColors(III)V

    .line 582
    invoke-virtual {v1, v3}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/high16 v0, 0x40c00000    # 6.0f

    .line 583
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    const/4 v9, 0x0

    invoke-virtual {v1, v9, v3, v9, v0}, Landroid/view/View;->setPadding(IIII)V

    .line 586
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda7;

    move-object/from16 v3, p2

    move-object v5, v11

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda7;-><init>(Lorg/telegram/ui/Components/EditTextBoldCursor;Lkotlin/jvm/internal/Ref$ObjectRef;Landroid/view/View;Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/lang/String;Lcom/exteragram/messenger/plugins/models/InputSetting;)V

    const/high16 v14, 0x41c00000    # 24.0f

    const/high16 v15, 0x41200000    # 10.0f

    const/4 v10, -0x1

    const/4 v11, -0x2

    const/high16 v12, 0x41c00000    # 24.0f

    const/4 v13, 0x0

    .line 598
    invoke-static/range {v10 .. v15}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v3

    .line 596
    invoke-virtual {v8, v1, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 607
    invoke-virtual {v7}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->makeCustomMaxHeight()Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 608
    invoke-virtual {v7, v8}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setView(Landroid/view/View;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    const/high16 v3, 0x43920000    # 292.0f

    .line 609
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-virtual {v7, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setWidth(I)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 611
    sget v3, Lorg/telegram/messenger/R$string;->Done:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    new-instance v5, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda8;

    invoke-direct {v5, v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda8;-><init>(Lkotlin/jvm/functions/Function0;)V

    invoke-virtual {v7, v3, v5}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 612
    sget v0, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-instance v3, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda9;

    invoke-direct {v3}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda9;-><init>()V

    invoke-virtual {v7, v0, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 614
    invoke-virtual {v7}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v0

    .line 615
    new-instance v3, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda10;

    invoke-direct {v3, v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda10;-><init>(Lorg/telegram/ui/Components/EditTextBoldCursor;)V

    invoke-virtual {v0, v3}, Landroid/app/Dialog;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)V

    .line 616
    new-instance v3, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda11;

    invoke-direct {v3, v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda11;-><init>(Lorg/telegram/ui/Components/EditTextBoldCursor;)V

    invoke-virtual {v0, v3}, Landroid/app/Dialog;->setOnShowListener(Landroid/content/DialogInterface$OnShowListener;)V

    .line 621
    invoke-virtual {v0, v9}, Lorg/telegram/ui/ActionBar/AlertDialog;->setDismissDialogByButtons(Z)V

    .line 614
    iput-object v0, v2, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    .line 623
    check-cast v0, Landroid/app/Dialog;

    invoke-virtual {v4, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method

.method private final toggleSwitch(Lorg/telegram/ui/Components/UItem;Ljava/lang/String;ZLkotlin/jvm/functions/Function1;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/ui/Components/UItem;",
            "Ljava/lang/String;",
            "Z",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/lang/Boolean;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 487
    invoke-static {p3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-interface {p4, v0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    .line 488
    invoke-virtual {p1, p3}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    .line 489
    iget-object p1, p1, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    instance-of p4, p1, Lcom/exteragram/messenger/plugins/models/SwitchSetting;

    const/4 v0, 0x0

    if-eqz p4, :cond_0

    check-cast p1, Lcom/exteragram/messenger/plugins/models/SwitchSetting;

    goto :goto_0

    :cond_0
    move-object p1, v0

    :goto_0
    if-eqz p1, :cond_1

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/models/SwitchSetting;->getOnChangeCallback()Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 490
    :cond_1
    sget-object p1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance p4, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda0;

    invoke-direct {p4, p0, p2, p3, v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Ljava/lang/String;ZLcom/chaquo/python/PyObject;)V

    invoke-virtual {p1, p4}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method

.method private final tr(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    if-eqz p1, :cond_0

    .line 270
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->visibleTexts:Ljava/util/LinkedHashSet;

    invoke-virtual {v0, p1}, Ljava/util/AbstractCollection;->add(Ljava/lang/Object;)Z

    .line 271
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object p0

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->text(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final updateTranslateItem(Z)V
    .locals 9

    .line 250
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->translateItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    .line 251
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->isTranslatable()Z

    move-result v1

    const/4 v7, 0x1

    const/4 v8, 0x0

    if-nez v1, :cond_1

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->getEnabled()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_0
    move v1, v8

    goto :goto_1

    :cond_1
    :goto_0
    move v1, v7

    .line 249
    :goto_1
    new-instance v6, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda2;

    invoke-direct {v6, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V

    const/high16 v2, 0x3f000000    # 0.5f

    const/4 v3, 0x1

    const/high16 v4, 0x3f800000    # 1.0f

    move v5, p1

    invoke-static/range {v0 .. v6}, Lorg/telegram/messenger/AndroidUtilities;->updateViewVisibilityAnimated(Landroid/view/View;ZFZFZLandroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 257
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->translateButton:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

    if-eqz p1, :cond_4

    .line 260
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->getEnabled()Z

    move-result v0

    if-nez v0, :cond_2

    move v7, v8

    goto :goto_2

    .line 261
    :cond_2
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->isLoading()Z

    move-result v0

    if-eqz v0, :cond_3

    goto :goto_2

    :cond_3
    const/4 v7, 0x2

    .line 258
    :goto_2
    invoke-virtual {p1, v7}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->setState(I)V

    .line 265
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->translateItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    if-eqz p0, :cond_4

    invoke-virtual {p1}, Landroid/view/View;->getContentDescription()Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p0, p1}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    :cond_4
    return-void
.end method


# virtual methods
.method public final checkTargetSetting()V
    .locals 1

    .line 275
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->targetSettingItemId:Ljava/lang/Integer;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/Number;->intValue()I

    move-result v0

    .line 276
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->scrollToItem(I)V

    const/4 v0, 0x0

    .line 277
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->targetSettingItemId:Ljava/lang/Integer;

    :cond_0
    return-void
.end method

.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 7

    .line 177
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object v0

    .line 179
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/ActionBar;->createMenu()Lorg/telegram/ui/ActionBar/ActionBarMenu;

    move-result-object v1

    const/4 v2, 0x1

    const/4 v3, 0x0

    .line 180
    invoke-virtual {v1, v2, v3}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(II)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v2

    .line 181
    new-instance v4, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    iget-object v6, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v5, v6}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v5

    invoke-direct {v4, p1, v5}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;-><init>(Landroid/content/Context;I)V

    const/4 p1, -0x1

    const/high16 v5, -0x40800000    # -1.0f

    .line 182
    invoke-static {p1, v5}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object p1

    invoke-virtual {v2, v4, p1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 183
    iput-object v4, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->translateButton:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

    .line 184
    invoke-virtual {v4}, Landroid/view/View;->getContentDescription()Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {v2, p1}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    const/high16 p1, 0x3f000000    # 0.5f

    .line 185
    invoke-static {v2, v3, p1, v3}, Lorg/telegram/messenger/AndroidUtilities;->updateViewVisibilityAnimated(Landroid/view/View;ZFZ)V

    .line 186
    new-instance v4, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda12;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda12;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V

    invoke-virtual {v2, v4}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 180
    iput-object v2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->translateItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    .line 189
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->createSubFragmentCallback:Lcom/chaquo/python/PyObject;

    if-nez v2, :cond_1

    .line 190
    sget v2, Lorg/telegram/messenger/R$drawable;->msg_reset:I

    invoke-virtual {v1, v3, v2}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(II)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v1

    .line 191
    sget v2, Lorg/telegram/messenger/R$string;->Reset:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 194
    sget-object v2, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v2

    iget-object v4, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    const/4 v5, 0x0

    if-nez v4, :cond_0

    move-object v4, v5

    :cond_0
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v2, v4}, Lcom/exteragram/messenger/plugins/PluginsController;->hasPluginSettingsPreferences(Ljava/lang/String;)Z

    move-result v2

    .line 192
    invoke-static {v1, v2, p1, v3}, Lorg/telegram/messenger/AndroidUtilities;->updateViewVisibilityAnimated(Landroid/view/View;ZFZ)V

    .line 198
    invoke-virtual {v1, v5}, Landroid/view/View;->setTag(Ljava/lang/Object;)V

    .line 199
    new-instance p1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda13;

    invoke-direct {p1, v1, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda13;-><init>(Lorg/telegram/ui/ActionBar/ActionBarMenuItem;Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V

    invoke-virtual {v1, p1}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 190
    iput-object v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->resetItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    .line 229
    :cond_1
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->checkTargetSetting()V

    .line 230
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->updateTranslateItem(Z)V

    .line 231
    iput-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    return-object v0
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 8

    .line 130
    sget p2, Lorg/telegram/messenger/NotificationCenter;->pluginSettingsRegistered:I

    const/4 v0, 0x0

    if-ne p1, p2, :cond_6

    .line 131
    invoke-static {p3}, Lkotlin/collections/ArraysKt;->firstOrNull([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    instance-of p2, p1, Ljava/lang/String;

    if-eqz p2, :cond_0

    check-cast p1, Ljava/lang/String;

    goto :goto_0

    :cond_0
    move-object p1, v0

    :goto_0
    if-eqz p1, :cond_2

    .line 132
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p2, :cond_1

    move-object p2, v0

    :cond_1
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p2

    invoke-static {p2, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_2

    goto/16 :goto_4

    .line 134
    :cond_2
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->createSubFragmentCallback:Lcom/chaquo/python/PyObject;

    if-eqz p1, :cond_3

    .line 136
    sget-object p2, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance p3, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda4;

    invoke-direct {p3, p0, p1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;Lcom/chaquo/python/PyObject;)V

    invoke-virtual {p2, p3}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void

    .line 153
    :cond_3
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p1, :cond_4

    const/4 p2, 0x1

    invoke-virtual {p1, p2}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 154
    :cond_4
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->resetItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    if-eqz v1, :cond_b

    .line 157
    sget-object p1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p1

    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p2, :cond_5

    goto :goto_1

    :cond_5
    move-object v0, p2

    :goto_1
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->hasPluginSettingsPreferences(Ljava/lang/String;)Z

    move-result v2

    .line 155
    new-instance v7, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda5;

    invoke-direct {v7, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V

    const/high16 v3, 0x3f000000    # 0.5f

    const/4 v4, 0x1

    const/high16 v5, 0x3f800000    # 1.0f

    const/4 v6, 0x1

    invoke-static/range {v1 .. v7}, Lorg/telegram/messenger/AndroidUtilities;->updateViewVisibilityAnimated(Landroid/view/View;ZFZFZLandroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    return-void

    .line 167
    :cond_6
    sget p2, Lorg/telegram/messenger/NotificationCenter;->pluginSettingsUnregistered:I

    if-ne p1, p2, :cond_b

    .line 168
    invoke-static {p3}, Lkotlin/collections/ArraysKt;->firstOrNull([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    instance-of p2, p1, Ljava/lang/String;

    if-eqz p2, :cond_7

    check-cast p1, Ljava/lang/String;

    goto :goto_2

    :cond_7
    move-object p1, v0

    :goto_2
    if-nez p1, :cond_8

    goto :goto_4

    .line 169
    :cond_8
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p2, :cond_9

    move-object p2, v0

    :cond_9
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p2

    invoke-static {p2, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_b

    sget-object p1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p1

    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p2, :cond_a

    goto :goto_3

    :cond_a
    move-object v0, p2

    :goto_3
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->hasPluginSettings(Ljava/lang/String;)Z

    move-result p1

    if-nez p1, :cond_b

    .line 170
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->finishFragment()V

    :cond_b
    :goto_4
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

    move-object/from16 v1, p0

    .line 282
    iget-object v0, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->settingItems:Ljava/util/List;

    const/4 v2, 0x0

    if-nez v0, :cond_1

    .line 283
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    iget-object v3, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v3, :cond_0

    move-object v3, v2

    :cond_0
    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginSettingsList(Ljava/lang/String;)Ljava/util/List;

    move-result-object v0

    .line 284
    :cond_1
    move-object v3, v0

    check-cast v3, Ljava/util/Collection;

    if-eqz v3, :cond_2b

    invoke-interface {v3}, Ljava/util/Collection;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_2

    goto/16 :goto_f

    .line 286
    :cond_2
    iget-object v3, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->visibleTexts:Ljava/util/LinkedHashSet;

    invoke-virtual {v3}, Ljava/util/AbstractCollection;->clear()V

    .line 287
    iget-object v3, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->customTitle:Ljava/lang/String;

    if-nez v3, :cond_4

    iget-object v3, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v3, :cond_3

    move-object v3, v2

    :cond_3
    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v3

    :cond_4
    invoke-direct {v1, v3}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    .line 290
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    const/4 v4, 0x0

    move v5, v4

    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    const/4 v6, 0x1

    if-eqz v0, :cond_28

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    move-object v7, v0

    check-cast v7, Lcom/exteragram/messenger/plugins/models/SettingItem;

    .line 291
    invoke-virtual {v7}, Lcom/exteragram/messenger/plugins/models/SettingItem;->getIcon()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_6

    .line 292
    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v8

    if-lez v8, :cond_5

    goto :goto_1

    :cond_5
    move-object v0, v2

    :goto_1
    if-eqz v0, :cond_6

    .line 294
    sget-object v8, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 295
    invoke-virtual {v8}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v9

    const-string v10, "drawable"

    invoke-virtual {v8}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v9, v0, v10, v8}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    goto :goto_2

    :cond_6
    move v0, v4

    .line 300
    :goto_2
    :try_start_0
    instance-of v8, v7, Lcom/exteragram/messenger/plugins/models/HeaderSetting;

    if-eqz v8, :cond_7

    .line 301
    move-object v0, v7

    check-cast v0, Lcom/exteragram/messenger/plugins/models/HeaderSetting;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/HeaderSetting;->getText()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_23

    .line 302
    invoke-direct {v1, v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    .line 303
    iput-object v7, v0, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    goto/16 :goto_b

    :catch_0
    move-exception v0

    goto/16 :goto_a

    .line 308
    :cond_7
    instance-of v8, v7, Lcom/exteragram/messenger/plugins/models/SwitchSetting;

    if-eqz v8, :cond_b

    .line 309
    move-object v8, v7

    check-cast v8, Lcom/exteragram/messenger/plugins/models/SwitchSetting;

    invoke-virtual {v8}, Lcom/exteragram/messenger/plugins/models/SwitchSetting;->getKey()Ljava/lang/String;

    move-result-object v8

    .line 310
    move-object v9, v7

    check-cast v9, Lcom/exteragram/messenger/plugins/models/SwitchSetting;

    invoke-virtual {v9}, Lcom/exteragram/messenger/plugins/models/SwitchSetting;->getText()Ljava/lang/String;

    move-result-object v9

    if-eqz v8, :cond_23

    if-eqz v9, :cond_23

    .line 313
    sget-object v10, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v10

    .line 314
    iget-object v11, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v11, :cond_8

    move-object v11, v2

    :cond_8
    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v11

    move-object v12, v7

    check-cast v12, Lcom/exteragram/messenger/plugins/models/SwitchSetting;

    invoke-virtual {v12}, Lcom/exteragram/messenger/plugins/models/SwitchSetting;->getDefaultValue()Z

    move-result v12

    invoke-virtual {v10, v11, v8, v12}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginSettingBoolean(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result v10

    .line 316
    invoke-direct {v1, v9}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    invoke-static {v4, v9}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v9

    .line 317
    invoke-virtual {v9, v10}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    .line 318
    iput-boolean v4, v9, Lorg/telegram/ui/Components/UItem;->drawLine:Z

    .line 319
    move-object v10, v7

    check-cast v10, Lcom/exteragram/messenger/plugins/models/SwitchSetting;

    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/models/SwitchSetting;->getSubtext()Ljava/lang/String;

    move-result-object v10

    if-eqz v10, :cond_9

    .line 320
    invoke-direct {v1, v10}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    iput-object v10, v9, Lorg/telegram/ui/Components/UItem;->textValue:Ljava/lang/CharSequence;

    .line 321
    iput-boolean v6, v9, Lorg/telegram/ui/Components/UItem;->multiline:Z

    :cond_9
    if-eqz v0, :cond_a

    .line 324
    iput v0, v9, Lorg/telegram/ui/Components/UItem;->iconResId:I

    .line 325
    :cond_a
    iput-object v8, v9, Lorg/telegram/ui/Components/UItem;->object2:Ljava/lang/Object;

    .line 326
    iput-object v7, v9, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    :goto_3
    move-object v0, v9

    goto/16 :goto_b

    .line 331
    :cond_b
    instance-of v8, v7, Lcom/exteragram/messenger/plugins/models/InputSetting;

    if-eqz v8, :cond_e

    .line 332
    move-object v8, v7

    check-cast v8, Lcom/exteragram/messenger/plugins/models/InputSetting;

    invoke-virtual {v8}, Lcom/exteragram/messenger/plugins/models/InputSetting;->getKey()Ljava/lang/String;

    move-result-object v8

    .line 333
    move-object v9, v7

    check-cast v9, Lcom/exteragram/messenger/plugins/models/InputSetting;

    invoke-virtual {v9}, Lcom/exteragram/messenger/plugins/models/InputSetting;->getText()Ljava/lang/String;

    move-result-object v9

    if-eqz v8, :cond_23

    if-eqz v9, :cond_23

    .line 336
    sget-object v10, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v10

    .line 337
    iget-object v11, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v11, :cond_c

    move-object v11, v2

    :cond_c
    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v11

    move-object v12, v7

    check-cast v12, Lcom/exteragram/messenger/plugins/models/InputSetting;

    invoke-virtual {v12}, Lcom/exteragram/messenger/plugins/models/InputSetting;->getDefaultValue()Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v10, v11, v8, v12}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginSettingString(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 339
    invoke-direct {v1, v9}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    invoke-static {v4, v9, v10}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v9

    .line 340
    move-object v10, v7

    check-cast v10, Lcom/exteragram/messenger/plugins/models/InputSetting;

    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/models/InputSetting;->getSubtext()Ljava/lang/String;

    move-result-object v10

    invoke-direct {v1, v10}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    if-eqz v0, :cond_d

    .line 342
    iput v0, v9, Lorg/telegram/ui/Components/UItem;->iconResId:I

    .line 343
    :cond_d
    iput-object v8, v9, Lorg/telegram/ui/Components/UItem;->object2:Ljava/lang/Object;

    .line 344
    iput-object v7, v9, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    goto :goto_3

    .line 349
    :cond_e
    instance-of v8, v7, Lcom/exteragram/messenger/plugins/models/EditTextSetting;

    if-eqz v8, :cond_10

    .line 350
    move-object v0, v7

    check-cast v0, Lcom/exteragram/messenger/plugins/models/EditTextSetting;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/EditTextSetting;->getKey()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_23

    move-object v0, v7

    check-cast v0, Lcom/exteragram/messenger/plugins/models/EditTextSetting;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/EditTextSetting;->getHint()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_23

    .line 351
    sget-object v0, Lcom/exteragram/messenger/plugins/ui/components/PluginEditTextCell$Factory;->Companion:Lcom/exteragram/messenger/plugins/ui/components/PluginEditTextCell$Factory$Companion;

    iget-object v8, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v8, :cond_f

    move-object v8, v2

    :cond_f
    move-object v9, v7

    check-cast v9, Lcom/exteragram/messenger/plugins/models/EditTextSetting;

    invoke-virtual {v0, v8, v9}, Lcom/exteragram/messenger/plugins/ui/components/PluginEditTextCell$Factory$Companion;->as(Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/models/EditTextSetting;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    .line 352
    move-object v8, v7

    check-cast v8, Lcom/exteragram/messenger/plugins/models/EditTextSetting;

    invoke-virtual {v8}, Lcom/exteragram/messenger/plugins/models/EditTextSetting;->getHint()Ljava/lang/String;

    move-result-object v8

    invoke-direct {v1, v8}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    iput-object v8, v0, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    goto/16 :goto_b

    .line 357
    :cond_10
    instance-of v8, v7, Lcom/exteragram/messenger/plugins/models/SelectorSetting;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    const-string v9, ""

    if-eqz v8, :cond_18

    .line 358
    :try_start_1
    move-object v8, v7

    check-cast v8, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    invoke-virtual {v8}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getKey()Ljava/lang/String;

    move-result-object v8

    .line 359
    move-object v10, v7

    check-cast v10, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getText()Ljava/lang/String;

    move-result-object v10

    if-eqz v8, :cond_23

    if-eqz v10, :cond_23

    .line 361
    move-object v11, v7

    check-cast v11, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getItems()[Ljava/lang/String;

    move-result-object v11

    array-length v11, v11

    if-nez v11, :cond_11

    move v11, v6

    goto :goto_4

    :cond_11
    move v11, v4

    :goto_4
    if-nez v11, :cond_23

    .line 362
    sget-object v11, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v12

    iget-object v13, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v13, :cond_12

    move-object v13, v2

    :cond_12
    invoke-virtual {v13}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v13

    move-object v14, v7

    check-cast v14, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    invoke-virtual {v14}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getDefaultValue()I

    move-result v14

    invoke-virtual {v12, v13, v8, v14}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginSettingInt(Ljava/lang/String;Ljava/lang/String;I)I

    move-result v12

    if-ltz v12, :cond_13

    .line 363
    move-object v13, v7

    check-cast v13, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    invoke-virtual {v13}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getItems()[Ljava/lang/String;

    move-result-object v13

    array-length v13, v13

    if-ge v12, v13, :cond_13

    goto :goto_5

    .line 364
    :cond_13
    move-object v12, v7

    check-cast v12, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    invoke-virtual {v12}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getDefaultValue()I

    move-result v12

    move-object v13, v7

    check-cast v13, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    invoke-virtual {v13}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getItems()[Ljava/lang/String;

    move-result-object v13

    invoke-static {v13}, Lkotlin/collections/ArraysKt;->getIndices([Ljava/lang/Object;)Lkotlin/ranges/IntRange;

    move-result-object v13

    invoke-static {v12, v13}, Lkotlin/ranges/RangesKt;->coerceIn(ILkotlin/ranges/ClosedRange;)I

    move-result v12

    .line 365
    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v11

    iget-object v13, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v13, :cond_14

    move-object v13, v2

    :cond_14
    invoke-virtual {v13}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v13

    invoke-static {v12}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v14

    invoke-virtual {v11, v13, v8, v14}, Lcom/exteragram/messenger/plugins/PluginsController;->setPluginSetting(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    .line 368
    :goto_5
    move-object v11, v7

    check-cast v11, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;->getItems()[Ljava/lang/String;

    move-result-object v11

    .line 11693
    new-instance v13, Ljava/util/ArrayList;

    array-length v14, v11

    invoke-direct {v13, v14}, Ljava/util/ArrayList;-><init>(I)V

    .line 12040
    array-length v14, v11

    move v15, v4

    :goto_6
    if-ge v15, v14, :cond_16

    aget-object v6, v11, v15

    .line 368
    invoke-direct {v1, v6}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    if-nez v6, :cond_15

    move-object v6, v9

    .line 12041
    :cond_15
    invoke-interface {v13, v6}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    add-int/lit8 v15, v15, 0x1

    const/4 v6, 0x1

    goto :goto_6

    .line 38
    :cond_16
    new-array v6, v4, [Ljava/lang/String;

    invoke-interface {v13, v6}, Ljava/util/Collection;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v6

    .line 368
    check-cast v6, [Ljava/lang/String;

    .line 369
    invoke-direct {v1, v10}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    aget-object v10, v6, v12

    invoke-static {v4, v9, v10}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v9

    .line 370
    iput-object v6, v9, Lorg/telegram/ui/Components/UItem;->texts:[Ljava/lang/String;

    .line 371
    iput v12, v9, Lorg/telegram/ui/Components/UItem;->intValue:I

    if-eqz v0, :cond_17

    .line 373
    iput v0, v9, Lorg/telegram/ui/Components/UItem;->iconResId:I

    .line 374
    :cond_17
    iput-object v8, v9, Lorg/telegram/ui/Components/UItem;->object2:Ljava/lang/Object;

    .line 375
    iput-object v7, v9, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    goto/16 :goto_3

    .line 380
    :cond_18
    instance-of v6, v7, Lcom/exteragram/messenger/plugins/models/TextSetting;

    if-eqz v6, :cond_1c

    .line 381
    move-object v6, v7

    check-cast v6, Lcom/exteragram/messenger/plugins/models/TextSetting;

    invoke-virtual {v6}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getText()Ljava/lang/String;

    move-result-object v6

    invoke-direct {v1, v6}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-static {v4, v6}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v6

    .line 382
    iput-object v7, v6, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    if-eqz v0, :cond_19

    .line 384
    iput v0, v6, Lorg/telegram/ui/Components/UItem;->iconResId:I

    .line 385
    :cond_19
    move-object v0, v7

    check-cast v0, Lcom/exteragram/messenger/plugins/models/TextSetting;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getAccent()Z

    move-result v0

    iput-boolean v0, v6, Lorg/telegram/ui/Components/UItem;->accent:Z

    .line 386
    move-object v0, v7

    check-cast v0, Lcom/exteragram/messenger/plugins/models/TextSetting;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getRed()Z

    move-result v0

    iput-boolean v0, v6, Lorg/telegram/ui/Components/UItem;->red:Z

    .line 387
    move-object v0, v7

    check-cast v0, Lcom/exteragram/messenger/plugins/models/TextSetting;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getSubtext()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_1b

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_1a

    goto :goto_7

    .line 388
    :cond_1a
    move-object v0, v7

    check-cast v0, Lcom/exteragram/messenger/plugins/models/TextSetting;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getSubtext()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v6, Lorg/telegram/ui/Components/UItem;->subtext:Ljava/lang/CharSequence;

    const/16 v0, 0x3c

    .line 389
    iput v0, v6, Lorg/telegram/ui/Components/UItem;->intValue:I

    :cond_1b
    :goto_7
    move-object v0, v6

    goto :goto_b

    .line 394
    :cond_1c
    instance-of v0, v7, Lcom/exteragram/messenger/plugins/models/DividerSetting;

    if-eqz v0, :cond_1f

    .line 395
    move-object v0, v7

    check-cast v0, Lcom/exteragram/messenger/plugins/models/DividerSetting;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/DividerSetting;->getText()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->tr(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_1e

    .line 396
    invoke-static {v0, v1, v2}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->fullyFormatText(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/Runnable;)Ljava/lang/CharSequence;

    move-result-object v0

    if-nez v0, :cond_1d

    goto :goto_8

    :cond_1d
    move-object v9, v0

    .line 398
    :cond_1e
    :goto_8
    invoke-static {v9}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    goto :goto_b

    .line 401
    :cond_1f
    instance-of v0, v7, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    if-eqz v0, :cond_23

    .line 402
    move-object v0, v7

    check-cast v0, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/CustomSetting;->getItem()Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    if-nez v0, :cond_22

    move-object v0, v7

    check-cast v0, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/models/CustomSetting;->getFactory()Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;

    move-result-object v0

    if-eqz v0, :cond_21

    iget-object v6, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v6, :cond_20

    move-object v6, v2

    :cond_20
    move-object v8, v7

    check-cast v8, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    move-object v9, v7

    check-cast v9, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    invoke-virtual {v9}, Lcom/exteragram/messenger/plugins/models/CustomSetting;->getFactoryArgs()Lcom/chaquo/python/PyObject;

    move-result-object v9

    invoke-virtual {v0, v6, v8, v9}, Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;->create(Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/models/CustomSetting;Lcom/chaquo/python/PyObject;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    goto :goto_9

    :cond_21
    move-object v0, v2

    :cond_22
    :goto_9
    if-eqz v0, :cond_23

    .line 404
    iput-object v7, v0, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_b

    .line 411
    :goto_a
    const-string v6, "PluginSettings"

    const-string v8, "Error creating item"

    invoke-static {v6, v8, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :cond_23
    move-object v0, v2

    :goto_b
    if-eqz v0, :cond_27

    .line 416
    invoke-direct {v1, v7}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getStableId(Lcom/exteragram/messenger/plugins/models/SettingItem;)I

    move-result v6

    iput v6, v0, Lorg/telegram/ui/Components/UItem;->id:I

    .line 417
    iget-object v6, v0, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    if-eqz v6, :cond_24

    invoke-virtual {v6}, Lcom/exteragram/messenger/plugins/models/SettingItem;->getLinkAlias()Ljava/lang/String;

    move-result-object v6

    goto :goto_c

    :cond_24
    move-object v6, v2

    .line 418
    :goto_c
    iget-object v7, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->targetSettingName:Ljava/lang/String;

    if-eqz v7, :cond_26

    invoke-interface {v7}, Ljava/lang/CharSequence;->length()I

    move-result v7

    if-nez v7, :cond_25

    goto :goto_d

    :cond_25
    iget-object v7, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->targetSettingName:Ljava/lang/String;

    invoke-static {v6, v7}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_26

    .line 419
    iget v5, v0, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    iput-object v5, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->targetSettingItemId:Ljava/lang/Integer;

    .line 420
    iput-object v2, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->targetSettingName:Ljava/lang/String;

    const/4 v5, 0x1

    :cond_26
    :goto_d
    move-object/from16 v6, p1

    .line 423
    invoke-virtual {v6, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_27
    move-object/from16 v6, p1

    goto/16 :goto_0

    :cond_28
    if-nez v5, :cond_2a

    .line 427
    iget-object v0, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->targetSettingName:Ljava/lang/String;

    if-eqz v0, :cond_2a

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_29

    goto :goto_e

    .line 428
    :cond_29
    invoke-static {}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->getInstance()Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;

    move-result-object v0

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->onSettingNotFound(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    .line 429
    iput-object v2, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->targetSettingName:Ljava/lang/String;

    .line 432
    :cond_2a
    :goto_e
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v0

    iget-object v2, v1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->visibleTexts:Ljava/util/LinkedHashSet;

    invoke-virtual {v0, v2}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->check(Ljava/util/Collection;)V

    const/4 v2, 0x1

    .line 433
    invoke-direct {v1, v2}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->updateTranslateItem(Z)V

    :cond_2b
    :goto_f
    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 2

    .line 111
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v0

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->customTitle:Ljava/lang/String;

    if-nez v1, :cond_1

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v1

    :cond_1
    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->text(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_2

    const-string p0, ""

    :cond_2
    return-object p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 1

    .line 437
    sget-object p3, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p3

    iget-object p4, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    const/4 p5, 0x0

    if-nez p4, :cond_0

    move-object p4, p5

    :cond_0
    invoke-virtual {p3, p4}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Lcom/exteragram/messenger/plugins/Plugin;)Z

    move-result p3

    if-nez p3, :cond_1

    goto/16 :goto_1

    .line 438
    :cond_1
    iget-object p3, p1, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    if-nez p3, :cond_2

    goto/16 :goto_1

    .line 441
    :cond_2
    instance-of p4, p3, Lcom/exteragram/messenger/plugins/models/TextSetting;

    if-eqz p4, :cond_4

    .line 442
    check-cast p3, Lcom/exteragram/messenger/plugins/models/TextSetting;

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getCreateSubFragmentCallback()Lcom/chaquo/python/PyObject;

    move-result-object p4

    if-eqz p4, :cond_3

    .line 444
    invoke-direct {p0, p1, p4}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->openSubFragmentNative(Lorg/telegram/ui/Components/UItem;Lcom/chaquo/python/PyObject;)V

    return-void

    .line 448
    :cond_3
    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getOnClickCallback()Lcom/chaquo/python/PyObject;

    move-result-object p0

    if-eqz p0, :cond_e

    .line 450
    :try_start_0
    filled-new-array {p2}, [Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_1

    .line 451
    :catch_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    goto/16 :goto_1

    .line 456
    :cond_4
    instance-of p4, p3, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    if-eqz p4, :cond_8

    .line 457
    move-object p4, p3

    check-cast p4, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    invoke-virtual {p4}, Lcom/exteragram/messenger/plugins/models/CustomSetting;->getCreateSubFragmentCallback()Lcom/chaquo/python/PyObject;

    move-result-object p4

    if-eqz p4, :cond_5

    .line 459
    invoke-direct {p0, p1, p4}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->openSubFragmentNative(Lorg/telegram/ui/Components/UItem;Lcom/chaquo/python/PyObject;)V

    return-void

    .line 464
    :cond_5
    :try_start_1
    move-object p4, p3

    check-cast p4, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    invoke-virtual {p4}, Lcom/exteragram/messenger/plugins/models/CustomSetting;->getFactory()Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;

    move-result-object p4

    if-eqz p4, :cond_7

    .line 465
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p0, :cond_6

    goto :goto_0

    :cond_6
    move-object p5, p0

    :goto_0
    invoke-virtual {p4, p5, p1, p2}, Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;->onClick(Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/ui/Components/UItem;Landroid/view/View;)V

    return-void

    .line 466
    :cond_7
    check-cast p3, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/models/CustomSetting;->getOnClickCallback()Lcom/chaquo/python/PyObject;

    move-result-object p0

    if-eqz p0, :cond_e

    filled-new-array {p2}, [Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    return-void

    .line 472
    :cond_8
    iget-object p4, p1, Lorg/telegram/ui/Components/UItem;->object2:Ljava/lang/Object;

    instance-of v0, p4, Ljava/lang/String;

    if-eqz v0, :cond_9

    move-object p5, p4

    check-cast p5, Ljava/lang/String;

    :cond_9
    if-nez p5, :cond_a

    goto :goto_1

    .line 475
    :cond_a
    instance-of p4, p2, Lorg/telegram/ui/Cells/TextCheckCell;

    if-eqz p4, :cond_b

    move-object p3, p2

    check-cast p3, Lorg/telegram/ui/Cells/TextCheckCell;

    invoke-virtual {p3}, Lorg/telegram/ui/Cells/TextCheckCell;->isChecked()Z

    move-result p3

    xor-int/lit8 p3, p3, 0x1

    new-instance p4, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda21;

    invoke-direct {p4, p2}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda21;-><init>(Landroid/view/View;)V

    invoke-direct {p0, p1, p5, p3, p4}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->toggleSwitch(Lorg/telegram/ui/Components/UItem;Ljava/lang/String;ZLkotlin/jvm/functions/Function1;)V

    return-void

    .line 476
    :cond_b
    instance-of p4, p2, Lorg/telegram/ui/Cells/NotificationsCheckCell;

    if-eqz p4, :cond_c

    move-object p3, p2

    check-cast p3, Lorg/telegram/ui/Cells/NotificationsCheckCell;

    invoke-virtual {p3}, Lorg/telegram/ui/Cells/NotificationsCheckCell;->isChecked()Z

    move-result p3

    xor-int/lit8 p3, p3, 0x1

    new-instance p4, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda22;

    invoke-direct {p4, p2}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity$$ExternalSyntheticLambda22;-><init>(Landroid/view/View;)V

    invoke-direct {p0, p1, p5, p3, p4}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->toggleSwitch(Lorg/telegram/ui/Components/UItem;Ljava/lang/String;ZLkotlin/jvm/functions/Function1;)V

    return-void

    .line 477
    :cond_c
    instance-of p4, p2, Lorg/telegram/ui/Cells/TextCell;

    if-eqz p4, :cond_e

    .line 479
    instance-of p4, p3, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    if-eqz p4, :cond_d

    invoke-direct {p0, p1, p2, p5}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->showSelectorDialog(Lorg/telegram/ui/Components/UItem;Landroid/view/View;Ljava/lang/String;)V

    return-void

    .line 480
    :cond_d
    instance-of p3, p3, Lcom/exteragram/messenger/plugins/models/InputSetting;

    if-eqz p3, :cond_e

    invoke-direct {p0, p1, p2, p5}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->showStringInputDialog(Lorg/telegram/ui/Components/UItem;Landroid/view/View;Ljava/lang/String;)V

    :catch_1
    :cond_e
    :goto_1
    return-void
.end method

.method public onFragmentCreate()Z
    .locals 2

    .line 114
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginSettingsRegistered:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 115
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginSettingsUnregistered:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 116
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->addListener(Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;)V

    .line 117
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->resetFailed()V

    .line 118
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentCreate()Z

    move-result p0

    return p0
.end method

.method public onFragmentDestroy()V
    .locals 2

    .line 122
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginSettingsRegistered:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 123
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginSettingsUnregistered:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 124
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTranslation()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->removeListener(Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;)V

    .line 125
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    return-void
.end method

.method public onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z
    .locals 3

    .line 498
    sget-object p3, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p3

    iget-object p4, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    const/4 p5, 0x0

    if-nez p4, :cond_0

    move-object p4, p5

    :cond_0
    invoke-virtual {p3, p4}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Lcom/exteragram/messenger/plugins/Plugin;)Z

    move-result p3

    const/4 p4, 0x0

    if-nez p3, :cond_1

    return p4

    .line 499
    :cond_1
    iget-object p3, p1, Lorg/telegram/ui/Components/UItem;->settingItem:Lcom/exteragram/messenger/plugins/models/SettingItem;

    if-nez p3, :cond_2

    return p4

    .line 501
    :cond_2
    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/models/SettingItem;->getOnLongClickCallback()Lcom/chaquo/python/PyObject;

    move-result-object v0

    const/4 v1, 0x1

    if-eqz v0, :cond_3

    .line 503
    :try_start_0
    filled-new-array {p2}, [Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return v1

    .line 508
    :cond_3
    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/models/SettingItem;->getLinkAlias()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_6

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_4

    goto :goto_0

    .line 509
    :cond_4
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez v0, :cond_5

    move-object v0, p5

    :cond_5
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v0

    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->settingsLinkPrefix:Ljava/lang/String;

    invoke-virtual {p3, v0, v2}, Lcom/exteragram/messenger/plugins/models/SettingItem;->getLink(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_6

    .line 510
    invoke-virtual {p0, p2, v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showCopyLinkOptions(Landroid/view/View;Ljava/lang/String;)V

    return v1

    .line 515
    :cond_6
    :goto_0
    instance-of v0, p3, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    if-eqz v0, :cond_8

    .line 516
    check-cast p3, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/models/CustomSetting;->getFactory()Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;

    move-result-object p3

    if-eqz p3, :cond_8

    .line 518
    :try_start_1
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p0, :cond_7

    goto :goto_1

    :cond_7
    move-object p5, p0

    :goto_1
    invoke-virtual {p3, p5, p1, p2}, Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;->onLongClick(Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/ui/Components/UItem;Landroid/view/View;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    :catch_1
    return v1

    :cond_8
    return p4
.end method

.method public onTranslationChanged(Z)V
    .locals 3

    .line 236
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    iget-object v0, v0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 237
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->getTitle()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    .line 238
    :cond_1
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->updateTranslateItem(Z)V

    if-eqz p1, :cond_2

    .line 239
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->isLastFragment()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 240
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->TranslationFailedAlert2:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createErrorBulletin(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_2
    return-void
.end method

.method public final setSettingsLinkPrefix(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;
    .locals 0

    .line 107
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->settingsLinkPrefix:Ljava/lang/String;

    return-object p0
.end method
