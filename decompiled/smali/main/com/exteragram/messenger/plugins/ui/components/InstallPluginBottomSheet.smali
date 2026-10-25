.class public final Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;
.super Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$Companion;,
        Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u00e4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0007\n\u0002\u0010\u0007\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\t\n\u0002\u0018\u0002\n\u0002\u0008\u000f\n\u0002\u0018\u0002\n\u0002\u0008\n\n\u0002\u0018\u0002\n\u0002\u0008\u0005\u0018\u0000 z2\u00020\u0001:\u0002yzB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\u0008\u0008\u0010\tJ\u0008\u00107\u001a\u00020\u001bH\u0014J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0014J(\u0010<\u001a\u00020=2\u0016\u0010>\u001a\u0012\u0012\u0004\u0012\u00020@0?j\u0008\u0012\u0004\u0012\u00020@`A2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010B\u001a\u00020@2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020+H\u0002J\u0018\u0010F\u001a\u00020@2\u0006\u0010C\u001a\u00020D2\u0006\u0010G\u001a\u00020+H\u0002J\u0010\u0010H\u001a\u00020@2\u0006\u0010I\u001a\u00020\u001bH\u0002J\u0010\u0010J\u001a\u00020@2\u0006\u0010K\u001a\u00020LH\u0002J\u0010\u0010M\u001a\u00020=2\u0006\u0010N\u001a\u00020\u001dH\u0016J \u0010O\u001a\u00020=2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020D2\u0006\u0010S\u001a\u00020LH\u0014J\u0010\u0010T\u001a\u00020=2\u0006\u0010U\u001a\u000202H\u0002J\u0008\u0010V\u001a\u00020=H\u0002J\u0010\u0010W\u001a\u00020=2\u0006\u0010X\u001a\u00020\rH\u0002J\u0010\u0010Y\u001a\u00020\u001f2\u0006\u0010Z\u001a\u00020[H\u0002J\u0010\u0010\\\u001a\u00020\u001f2\u0006\u0010Z\u001a\u00020[H\u0002J\u0010\u0010]\u001a\u00020\u001f2\u0006\u0010Z\u001a\u00020[H\u0002J\u0010\u0010^\u001a\u00020=2\u0006\u0010_\u001a\u00020+H\u0002J\u0008\u0010`\u001a\u00020=H\u0002J\u0010\u0010a\u001a\u00020=2\u0006\u0010b\u001a\u00020\u001fH\u0002J\u0010\u0010c\u001a\u00020\u001f2\u0006\u0010Z\u001a\u00020[H\u0002J\u0008\u0010d\u001a\u00020=H\u0002J\u0008\u0010e\u001a\u00020=H\u0002J\u0008\u0010f\u001a\u00020=H\u0002J6\u0010g\u001a\u00020=2\u0006\u0010I\u001a\u00020+2\u0006\u0010h\u001a\u00020+2\u0006\u0010i\u001a\u00020+2\u0014\u0010j\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010+\u0012\u0004\u0012\u00020=0kH\u0002J\u001e\u0010l\u001a\u00020=2\u000c\u0010m\u001a\u0008\u0012\u0004\u0012\u00020-012\u0006\u0010n\u001a\u00020\rH\u0002J\u0010\u0010o\u001a\u00020=2\u0006\u0010p\u001a\u00020\rH\u0002J\u0008\u0010q\u001a\u00020=H\u0002J\u0008\u0010r\u001a\u00020=H\u0002J\u0018\u0010s\u001a\u00020=2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010t\u001a\u00020=2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010u\u001a\u00020vH\u0002J\u0008\u0010w\u001a\u00020=H\u0016J\u0008\u0010x\u001a\u00020=H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000c\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\'\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020-X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u00100\u001a\u0008\u0012\u0004\u0012\u00020201X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u00105\u001a\u0008\u0012\u0004\u0012\u00020206X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006{"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;",
        "Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;",
        "fragment",
        "Lorg/telegram/ui/ActionBar/BaseFragment;",
        "validationResult",
        "Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;",
        "params",
        "Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;",
        "<init>",
        "(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;)V",
        "plugin",
        "Lcom/exteragram/messenger/plugins/Plugin;",
        "enableAfterInstallation",
        "",
        "installing",
        "cancellationRequested",
        "delayedLoadingRunnable",
        "Ljava/lang/Runnable;",
        "currentHint",
        "Lorg/telegram/ui/Stories/recorder/HintView2;",
        "isUpdate",
        "button",
        "Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;",
        "contentReady",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "titleText",
        "",
        "sheetContainer",
        "Landroid/widget/FrameLayout;",
        "headerView",
        "Landroid/view/View;",
        "descriptionView",
        "actionsPill",
        "Landroid/widget/LinearLayout;",
        "translateButton",
        "Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;",
        "bottomContainer",
        "enableAfterInstallRow",
        "errorView",
        "errorTextView",
        "Landroid/widget/TextView;",
        "errorText",
        "",
        "nameText",
        "Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;",
        "descriptionText",
        "translating",
        "requirements",
        "",
        "Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;",
        "unknownSourceBlocked",
        "blocked",
        "requirementDelegate",
        "Lorg/telegram/messenger/Utilities$Callback;",
        "getTitle",
        "createAdapter",
        "Lorg/telegram/ui/Components/RecyclerListView$SelectionAdapter;",
        "listView",
        "Lorg/telegram/ui/Components/RecyclerListView;",
        "fillItems",
        "",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "Lkotlin/collections/ArrayList;",
        "pipRow",
        "id",
        "",
        "requirement",
        "wheelRow",
        "fileName",
        "header",
        "text",
        "spacer",
        "heightDp",
        "",
        "onViewCreated",
        "containerView",
        "onPreDraw",
        "canvas",
        "Landroid/graphics/Canvas;",
        "top",
        "progressToFullView",
        "onRequirementAction",
        "info",
        "checkRequirementsSatisfied",
        "applyButtonState",
        "animated",
        "buildHeader",
        "activity",
        "Landroid/app/Activity;",
        "buildDescription",
        "buildError",
        "showError",
        "message",
        "scrollToError",
        "showSourceHint",
        "view",
        "createEnableAfterInstallRow",
        "openPluginFile",
        "checkTranslatable",
        "onTranslateClick",
        "translateText",
        "fromLanguage",
        "toLanguage",
        "done",
        "Lkotlin/Function1;",
        "onTranslationFinished",
        "texts",
        "success",
        "showTexts",
        "translated",
        "onTextTransitionUpdate",
        "onInstallClick",
        "showSuccessBulletin",
        "showSimpleSuccessBulletin",
        "bf",
        "Lorg/telegram/ui/Components/BulletinFactory;",
        "dismiss",
        "onSwipeStarts",
        "PluginInstallParams",
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
        "SMAP\nInstallPluginBottomSheet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstallPluginBottomSheet.kt\ncom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,1369:1\n1#2:1370\n1807#3,3:1371\n1915#3,2:1377\n3938#4:1374\n4474#4,2:1375\n*S KotlinDebug\n*F\n+ 1 InstallPluginBottomSheet.kt\ncom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet\n*L\n411#1:1371,3\n1002#1:1377,2\n941#1:1374\n941#1:1375,2\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$Companion;

.field private static final SECTION_GAP:F = 12.0f


# instance fields
.field private actionsPill:Landroid/widget/LinearLayout;

.field private adapter:Lorg/telegram/ui/Components/UniversalAdapter;

.field private blocked:Z

.field private bottomContainer:Landroid/widget/LinearLayout;

.field private final button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

.field private volatile cancellationRequested:Z

.field private contentReady:Z

.field private currentHint:Lorg/telegram/ui/Stories/recorder/HintView2;

.field private delayedLoadingRunnable:Ljava/lang/Runnable;

.field private final descriptionText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

.field private descriptionView:Landroid/view/View;

.field private enableAfterInstallRow:Landroid/view/View;

.field private enableAfterInstallation:Z

.field private errorText:Ljava/lang/String;

.field private errorTextView:Landroid/widget/TextView;

.field private errorView:Landroid/view/View;

.field private final fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

.field private headerView:Landroid/view/View;

.field private installing:Z

.field private final isUpdate:Z

.field private final nameText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

.field private final params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

.field private final plugin:Lcom/exteragram/messenger/plugins/Plugin;

.field private final requirementDelegate:Lorg/telegram/messenger/Utilities$Callback;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;",
            ">;"
        }
    .end annotation
.end field

.field private final requirements:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;",
            ">;"
        }
    .end annotation
.end field

.field private sheetContainer:Landroid/widget/FrameLayout;

.field private titleText:Ljava/lang/CharSequence;

.field private translateButton:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

.field private translating:Z

.field private final unknownSourceBlocked:Z


# direct methods
.method public static $r8$lambda$--hFGMA5QevyK28Idv1Y9ba9cxI(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V
    .locals 1

    .line 1037
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setLoading(Z)V

    return-void
.end method

.method public static $r8$lambda$0bzmhvmogRnBU8vwylqF8Odp7Is(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Landroid/view/View;I)V
    .locals 0

    .line 187
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p1, :cond_3

    invoke-virtual {p1, p2}, Lorg/telegram/ui/Components/UniversalAdapter;->getItem(I)Lorg/telegram/ui/Components/UItem;

    move-result-object p1

    if-nez p1, :cond_0

    goto :goto_1

    .line 188
    :cond_0
    iget-object p1, p1, Lorg/telegram/ui/Components/UItem;->object2:Ljava/lang/Object;

    instance-of p2, p1, Ljava/lang/String;

    if-eqz p2, :cond_1

    check-cast p1, Ljava/lang/String;

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    :goto_0
    if-nez p1, :cond_2

    goto :goto_1

    .line 189
    :cond_2
    invoke-virtual {p0}, Landroid/app/Dialog;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    :cond_3
    :goto_1
    return-void
.end method

.method public static $r8$lambda$0iFSAj_4pVkRlrIMq9sW2Re2HQQ(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;)V
    .locals 4

    .line 1042
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->delayedLoadingRunnable:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    :cond_0
    const/4 v0, 0x0

    .line 1043
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->delayedLoadingRunnable:Ljava/lang/Runnable;

    .line 1044
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setLoading(Z)V

    const/4 v1, 0x1

    .line 1045
    invoke-virtual {p0, v1}, Landroid/app/Dialog;->setCancelable(Z)V

    .line 1046
    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCanDismissWithSwipe(Z)V

    .line 1047
    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCanDismissWithTouchOutside(Z)V

    .line 1048
    iput-boolean v2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->installing:Z

    .line 1050
    iget-boolean v3, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->cancellationRequested:Z

    if-eqz v3, :cond_1

    .line 1051
    iput-boolean v2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->cancellationRequested:Z

    .line 1052
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->applyButtonState(Z)V

    return-void

    :cond_1
    if-eqz p1, :cond_3

    .line 1057
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->applyButtonState(Z)V

    .line 1058
    const-string v1, "Installation cancelled"

    const/4 v3, 0x2

    invoke-static {p1, v1, v2, v3, v0}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 1059
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showError(Ljava/lang/String;)V

    :cond_2
    return-void

    .line 1063
    :cond_3
    iget-boolean p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->enableAfterInstallation:Z

    if-eqz p1, :cond_4

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result p1

    if-nez p1, :cond_4

    .line 1064
    iput-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->installing:Z

    .line 1065
    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCanDismissWithSwipe(Z)V

    .line 1066
    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCanDismissWithTouchOutside(Z)V

    .line 1067
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    invoke-virtual {p1, v1}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setLoading(Z)V

    .line 1069
    sget-object p1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p1

    .line 1070
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v0

    new-instance v2, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda24;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda24;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {p1, v0, v1, v2}, Lcom/exteragram/messenger/plugins/PluginsController;->setPluginEnabled(Ljava/lang/String;ZLorg/telegram/messenger/Utilities$Callback;)V

    return-void

    .line 1088
    :cond_4
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->dismiss()V

    .line 1089
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showSuccessBulletin(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/Plugin;)V

    return-void
.end method

.method public static $r8$lambda$1mwQKeA8NTran0o7I5m7IQPO56g(Landroid/widget/LinearLayout;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V
    .locals 1

    .line 715
    invoke-virtual {p0}, Landroid/view/View;->performClick()Z

    .line 716
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    .line 718
    iget-object p1, p1, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getTrusted()Z

    move-result p1

    if-eqz p1, :cond_0

    const-string/jumbo p1, "trusted_source_hint"

    goto :goto_0

    :cond_0
    const-string/jumbo p1, "unknown_source_hint"

    :goto_0
    const/4 v0, 0x1

    .line 717
    invoke-interface {p0, p1, v0}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    .line 721
    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static $r8$lambda$4owLgGt87wFgmWYGGJXyRImSMkc(Lkotlin/jvm/internal/Ref$BooleanRef;Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Lkotlin/jvm/internal/Ref$IntRef;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/util/List;Ljava/lang/String;)Lkotlin/Unit;
    .locals 1

    const/4 v0, 0x1

    if-nez p5, :cond_0

    .line 957
    iput-boolean v0, p0, Lkotlin/jvm/internal/Ref$BooleanRef;->element:Z

    goto :goto_0

    .line 959
    :cond_0
    invoke-virtual {p1, p5}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->setTranslation(Ljava/lang/String;)V

    .line 961
    :goto_0
    iget p1, p2, Lkotlin/jvm/internal/Ref$IntRef;->element:I

    add-int/lit8 p1, p1, -0x1

    iput p1, p2, Lkotlin/jvm/internal/Ref$IntRef;->element:I

    if-nez p1, :cond_1

    .line 962
    iget-boolean p0, p0, Lkotlin/jvm/internal/Ref$BooleanRef;->element:Z

    xor-int/2addr p0, v0

    invoke-direct {p3, p4, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->onTranslationFinished(Ljava/util/List;Z)V

    .line 964
    :cond_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$51uVpX6e938kFdqF76dtHaIpIp8(Landroid/view/View;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Lorg/telegram/ui/Stories/recorder/HintView2;)V
    .locals 4

    const/4 v0, 0x2

    .line 862
    new-array v1, v0, [I

    .line 863
    invoke-virtual {p0, v1}, Landroid/view/View;->getLocationInWindow([I)V

    .line 865
    new-array v0, v0, [I

    .line 866
    iget-object p1, p1, Lorg/telegram/ui/ActionBar/BottomSheet;->container:Lorg/telegram/ui/ActionBar/BottomSheet$ContainerView;

    invoke-virtual {p1, v0}, Landroid/view/View;->getLocationInWindow([I)V

    const/4 p1, 0x0

    .line 868
    aget v2, v1, p1

    aget v3, v0, p1

    sub-int/2addr v2, v3

    aput v2, v1, p1

    const/4 v2, 0x1

    .line 869
    aget v3, v1, v2

    aget v0, v0, v2

    sub-int/2addr v3, v0

    aput v3, v1, v2

    const/high16 v0, 0x42c80000    # 100.0f

    .line 871
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    sub-int/2addr v3, v0

    const/high16 v0, 0x40c00000    # 6.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    sub-int/2addr v3, v0

    .line 872
    aget p1, v1, p1

    int-to-float p1, p1

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result p0

    int-to-float p0, p0

    const/high16 v0, 0x40000000    # 2.0f

    div-float/2addr p0, v0

    add-float/2addr p1, p0

    int-to-float p0, v3

    .line 874
    invoke-virtual {p2, p0}, Landroid/view/View;->setTranslationY(F)V

    const/high16 p0, 0x42000000    # 32.0f

    .line 875
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    neg-int p0, p0

    int-to-float p0, p0

    add-float/2addr p0, p1

    const/4 p1, 0x0

    invoke-virtual {p2, p1, p0}, Lorg/telegram/ui/Stories/recorder/HintView2;->setJointPx(FF)Lorg/telegram/ui/Stories/recorder/HintView2;

    const-wide/16 p0, 0x157c

    .line 876
    invoke-virtual {p2, p0, p1}, Lorg/telegram/ui/Stories/recorder/HintView2;->setDuration(J)Lorg/telegram/ui/Stories/recorder/HintView2;

    .line 877
    invoke-virtual {p2}, Lorg/telegram/ui/Stories/recorder/HintView2;->show()Lorg/telegram/ui/Stories/recorder/HintView2;

    return-void
.end method

.method public static $r8$lambda$7PvEkq0ZY-75y_UTl5UgLN3mEoc(Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;)V
    .locals 1

    .line 388
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda11;

    invoke-direct {v0, p2, p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda11;-><init>(Ljava/lang/String;Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$9k86gw9Mzua9-n0HXczcoHJ-H2M(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;)Ljava/lang/CharSequence;
    .locals 2

    .line 133
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    new-instance v1, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda5;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-static {p1, v0, v1}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->fullyFormatText(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/Runnable;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$GdDIZfVsbBJ4Wc-NT-ZutfZirDU(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;)V
    .locals 0

    .line 144
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->onRequirementAction(Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;)V

    return-void
.end method

.method public static $r8$lambda$JDAKNeU10Fil-bAnsaIZ2uI7PKk(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Landroid/view/View;)V
    .locals 0

    .line 792
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->errorText:Ljava/lang/String;

    if-nez p1, :cond_0

    goto :goto_0

    .line 793
    :cond_0
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 794
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->topBulletinContainer:Landroid/widget/FrameLayout;

    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {p1, p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 795
    sget p1, Lorg/telegram/messenger/R$string;->TextCopied:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyBulletin(Ljava/lang/String;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 796
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_1
    :goto_0
    return-void
.end method

.method public static $r8$lambda$JcFMrAkO6mT6xcKCfpOjvc83vx8(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Landroid/view/View;IIIIIIII)V
    .locals 0

    sub-int/2addr p5, p3

    .line 235
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {p1}, Landroid/view/View;->getPaddingBottom()I

    move-result p1

    if-eq p1, p5, :cond_0

    .line 236
    iget-object p1, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    .line 237
    iget p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->backgroundPaddingLeft:I

    const/4 p2, 0x0

    .line 236
    invoke-virtual {p1, p0, p2, p0, p5}, Landroid/view/View;->setPadding(IIII)V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$KlFP3p1GoTtkXZ1lBR4je9x2kFM(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Landroid/view/View;)V
    .locals 0

    .line 672
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showSourceHint(Landroid/view/View;)V

    return-void
.end method

.method public static $r8$lambda$PcrlDgT4JGXCfZRinXFErAkdLs0(Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/tgnet/TLRPC$TL_messages_stickerSet;Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/Runnable;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;Lorg/telegram/ui/Components/BulletinFactory;)V
    .locals 3

    .line 1131
    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_3

    :cond_0
    const/4 v0, 0x0

    if-eqz p1, :cond_1

    .line 1136
    iget-object v1, p1, Lorg/telegram/tgnet/TLRPC$messages_StickerSet;->documents:Ljava/util/ArrayList;

    goto :goto_0

    :cond_1
    move-object v1, v0

    :goto_0
    if-eqz v1, :cond_2

    iget-object v1, p1, Lorg/telegram/tgnet/TLRPC$messages_StickerSet;->documents:Ljava/util/ArrayList;

    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_2

    .line 1137
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/Plugin;->getIndex()I

    move-result v1

    if-ltz v1, :cond_2

    .line 1138
    iget-object v2, p1, Lorg/telegram/tgnet/TLRPC$messages_StickerSet;->documents:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v1, v2, :cond_2

    .line 1139
    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$messages_StickerSet;->documents:Ljava/util/ArrayList;

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    move-object v0, p1

    check-cast v0, Lorg/telegram/tgnet/TLRPC$Document;

    :cond_2
    if-eqz v0, :cond_5

    const/4 p1, 0x1

    .line 1143
    invoke-virtual {p0, p1}, Ljava/util/concurrent/atomic/AtomicBoolean;->getAndSet(Z)Z

    move-result p0

    if-nez p0, :cond_5

    .line 1144
    invoke-static {p3}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 1147
    iget-boolean p0, p4, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->isUpdate:Z

    if-eqz p0, :cond_3

    sget p0, Lorg/telegram/messenger/R$string;->PluginUpdated:I

    goto :goto_1

    :cond_3
    sget p0, Lorg/telegram/messenger/R$string;->PluginInstalled:I

    .line 1148
    :goto_1
    filled-new-array {p5}, [Ljava/lang/Object;

    move-result-object p1

    .line 1146
    invoke-static {p0, p1}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    .line 1145
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object p0

    .line 1152
    sget-object p1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p3

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object p3

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p4

    invoke-virtual {p3, p4}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz p3, :cond_4

    .line 1154
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p1

    .line 1155
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p4

    invoke-virtual {p1, p4}, Lcom/exteragram/messenger/plugins/PluginsController;->hasPluginSettings(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_4

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->isEnabled()Z

    move-result p1

    if-eqz p1, :cond_4

    .line 1160
    sget p1, Lorg/telegram/messenger/R$string;->Settings:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    .line 1157
    new-instance p3, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda14;

    invoke-direct {p3, p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda14;-><init>(Lcom/exteragram/messenger/plugins/Plugin;)V

    invoke-virtual {p6, v0, p0, p1, p3}, Lorg/telegram/ui/Components/BulletinFactory;->createEmojiBulletin(Lorg/telegram/tgnet/TLRPC$Document;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    goto :goto_2

    .line 1163
    :cond_4
    invoke-virtual {p6, v0, p0}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(Lorg/telegram/tgnet/TLRPC$Document;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 1165
    :goto_2
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_5
    :goto_3
    return-void
.end method

.method public static $r8$lambda$Pi0Yg3kmZq9UkRULiFYt6kZYc_8(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;)V
    .locals 1

    .line 1041
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda29;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda29;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$QmIdZAyHeJKamuDsNUC71z3zbGQ(Lkotlin/jvm/internal/Ref$BooleanRef;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)Lkotlin/Unit;
    .locals 1

    .line 971
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0, p1, p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda0;-><init>(Lkotlin/jvm/internal/Ref$BooleanRef;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 977
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$S6yUHpGKTwONSUPNmjtxj1U2NIs(Ljava/lang/String;Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V
    .locals 2

    if-nez p0, :cond_4

    .line 390
    sget-object p0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object p0

    iget-object v0, p1, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;->id:Ljava/lang/String;

    invoke-virtual {p0, v0}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/plugins/Plugin;

    const/4 v0, 0x0

    if-eqz p0, :cond_0

    .line 391
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getVersion()Ljava/lang/String;

    move-result-object v1

    goto :goto_0

    :cond_0
    move-object v1, v0

    :goto_0
    iput-object v1, p1, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;->currentVersion:Ljava/lang/String;

    if-eqz p0, :cond_1

    .line 392
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_2

    :cond_1
    iget-object p0, p1, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;->name:Ljava/lang/String;

    :cond_2
    iput-object p0, p1, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;->name:Ljava/lang/String;

    const/4 p0, 0x3

    .line 393
    iput p0, p1, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;->state:I

    .line 394
    iput-object v0, p2, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->errorText:Ljava/lang/String;

    .line 395
    iget-object p0, p2, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_3

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 396
    :cond_3
    invoke-direct {p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->checkRequirementsSatisfied()V

    return-void

    .line 398
    :cond_4
    invoke-direct {p2, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showError(Ljava/lang/String;)V

    return-void
.end method

.method public static $r8$lambda$SEi4h0JXOWTPvYm8mfQFdJHPNZk(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Landroid/view/View;)V
    .locals 0

    .line 689
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->onTranslateClick()V

    return-void
.end method

.method public static $r8$lambda$U5co24RCnkNfmnx5ohZVPCpsK-0(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;)V
    .locals 3

    const/4 v0, 0x0

    .line 1072
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->installing:Z

    const/4 v1, 0x1

    .line 1073
    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCanDismissWithSwipe(Z)V

    .line 1074
    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCanDismissWithTouchOutside(Z)V

    .line 1075
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    invoke-virtual {v2, v0}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setLoading(Z)V

    if-eqz p1, :cond_0

    .line 1078
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->applyButtonState(Z)V

    .line 1079
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showError(Ljava/lang/String;)V

    return-void

    .line 1082
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->dismiss()V

    .line 1083
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showSuccessBulletin(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/Plugin;)V

    return-void
.end method

.method public static $r8$lambda$ZQHzvKNFHqfqpJsWcYEwFWcaMDg(Lcom/exteragram/messenger/plugins/Plugin;)V
    .locals 1

    .line 1190
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->openPluginSettings(Ljava/lang/String;)V

    return-void
.end method

.method public static $r8$lambda$_keszDIvYRSPiS1ENLcwZrfDATw(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;)V
    .locals 1

    .line 1071
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda12;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda12;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$aPNjQGCz0SRiyiVD9F8ZjNUC_0c(Lcom/exteragram/messenger/plugins/Plugin;)V
    .locals 1

    .line 1161
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->openPluginSettings(Ljava/lang/String;)V

    return-void
.end method

.method public static $r8$lambda$axbWnxDrBj8SEXz68q0_0EeZ7WI(Landroid/view/View;)V
    .locals 5

    .line 352
    instance-of v0, p0, Lorg/telegram/ui/Cells/HeaderCell;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    check-cast p0, Lorg/telegram/ui/Cells/HeaderCell;

    goto :goto_0

    :cond_0
    move-object p0, v1

    :goto_0
    if-nez p0, :cond_1

    goto :goto_1

    .line 353
    :cond_1
    invoke-virtual {p0}, Lorg/telegram/ui/Cells/HeaderCell;->getTextView()Landroid/widget/TextView;

    move-result-object v0

    .line 354
    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v2

    instance-of v3, v2, Landroid/widget/FrameLayout$LayoutParams;

    if-eqz v3, :cond_2

    move-object v1, v2

    check-cast v1, Landroid/widget/FrameLayout$LayoutParams;

    :cond_2
    if-nez v1, :cond_3

    goto :goto_1

    :cond_3
    const/high16 v2, 0x40c00000    # 6.0f

    .line 355
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    const/high16 v3, 0x42080000    # 34.0f

    .line 356
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    .line 357
    iget v4, v1, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    if-ne v4, v2, :cond_5

    invoke-virtual {v0}, Landroid/widget/TextView;->getMinHeight()I

    move-result v4

    if-eq v4, v3, :cond_4

    goto :goto_2

    :cond_4
    :goto_1
    return-void

    .line 358
    :cond_5
    :goto_2
    iput v2, v1, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    .line 359
    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setMinHeight(I)V

    .line 360
    invoke-virtual {p0}, Landroid/view/View;->requestLayout()V

    return-void
.end method

.method public static $r8$lambda$bCqSZM6hxYuEJX0VdupAXrW9z48(Ljava/util/concurrent/atomic/AtomicBoolean;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/ui/Components/BulletinFactory;)V
    .locals 1

    const/4 v0, 0x1

    .line 1121
    invoke-virtual {p0, v0}, Ljava/util/concurrent/atomic/AtomicBoolean;->getAndSet(Z)Z

    move-result p0

    if-nez p0, :cond_0

    .line 1122
    invoke-direct {p1, p2, p3}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showSimpleSuccessBulletin(Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/ui/Components/BulletinFactory;)V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$cj8Fxmv3WzdlRmOhCU2DoQ437yc(Lkotlin/jvm/internal/Ref$BooleanRef;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)V
    .locals 1

    .line 972
    iget-boolean v0, p0, Lkotlin/jvm/internal/Ref$BooleanRef;->element:Z

    if-nez v0, :cond_0

    const/4 v0, 0x1

    .line 973
    iput-boolean v0, p0, Lkotlin/jvm/internal/Ref$BooleanRef;->element:Z

    .line 974
    invoke-interface {p1, p2}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_0
    return-void
.end method

.method public static $r8$lambda$dgq_OwmZilX4u_QAT-t01iXTPS0(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;)V
    .locals 1

    .line 920
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda23;

    invoke-direct {v0, p2, p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda23;-><init>(Ljava/lang/String;Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$q4p5RiDrgsviqy7JLiU9TlMNu8Q(Ljava/lang/String;)Ljava/lang/CharSequence;
    .locals 0

    .line 0
    return-object p0
.end method

.method public static $r8$lambda$qWRuZ2J8_oT7EeUQTT2otMF0ew0(Ljava/util/concurrent/atomic/AtomicBoolean;Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/Runnable;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;Lorg/telegram/ui/Components/BulletinFactory;Lorg/telegram/tgnet/TLRPC$TL_messages_stickerSet;)V
    .locals 8

    .line 1130
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda28;

    move-object v1, p0

    move-object v3, p1

    move-object v4, p2

    move-object v5, p3

    move-object v6, p4

    move-object v7, p5

    move-object v2, p6

    invoke-direct/range {v0 .. v7}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda28;-><init>(Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/tgnet/TLRPC$TL_messages_stickerSet;Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/Runnable;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;Lorg/telegram/ui/Components/BulletinFactory;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$sYEAZbpbDxpv-Jl6detG-O0ze-s(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Landroid/view/View;)V
    .locals 0

    .line 680
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->openPluginFile()V

    return-void
.end method

.method public static $r8$lambda$uW8h013qngMyO9QW7bp8hpiNB2E(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Z)Lkotlin/Unit;
    .locals 0

    .line 888
    iput-boolean p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->enableAfterInstallation:Z

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static synthetic $r8$lambda$wQhmu7AbcThMqfCYx86e6RzypGU(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V

    return-void
.end method

.method public static $r8$lambda$xwESlfoHFGWhHXNA_r6mweAjQ8I(Ljava/lang/String;Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V
    .locals 1

    if-eqz p0, :cond_1

    .line 921
    invoke-interface {p0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const-string/jumbo v0, "und"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {p0}, Lcom/exteragram/messenger/translator/TranslatorUtils;->isRestrictedLanguage(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 922
    invoke-virtual {p1, p0}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->setLanguage(Ljava/lang/String;)V

    .line 923
    iget-object p0, p2, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->translateButton:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

    if-eqz p0, :cond_1

    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Landroid/view/View;->setVisibility(I)V

    :cond_1
    :goto_0
    return-void
.end method

.method public static $r8$lambda$zFYNOl6mxRm-m1IcxZRTUXyOo-o(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Landroid/view/View;)V
    .locals 0

    .line 457
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->onInstallClick()V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->Companion:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$Companion;

    return-void
.end method

.method public constructor <init>(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;)V
    .locals 17

    .line 94
    invoke-virtual/range {p1 .. p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    .line 100
    sget-object v7, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;->FADING:Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;

    .line 101
    invoke-virtual/range {p1 .. p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v8

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x1

    move-object/from16 v0, p0

    move-object/from16 v2, p1

    .line 93
    invoke-direct/range {v0 .. v8}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;ZZZZLorg/telegram/ui/Components/BottomSheetWithRecyclerListView$ActionBarType;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 90
    iput-object v2, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    move-object/from16 v1, p3

    .line 92
    iput-object v1, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    .line 103
    invoke-virtual/range {p2 .. p2}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getPlugin()Lcom/exteragram/messenger/plugins/Plugin;

    move-result-object v3

    if-eqz v3, :cond_9

    iput-object v3, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    .line 111
    sget-object v4, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v4

    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v4

    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v4

    iput-boolean v4, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->isUpdate:Z

    .line 130
    new-instance v4, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v5

    new-instance v6, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda6;

    invoke-direct {v6}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda6;-><init>()V

    new-instance v7, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$nameText$2;

    invoke-direct {v7, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$nameText$2;-><init>(Ljava/lang/Object;)V

    invoke-direct {v4, v5, v6, v7}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;-><init>(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V

    iput-object v4, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->nameText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    .line 131
    new-instance v4, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    .line 132
    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/Plugin;->getDescription()Ljava/lang/String;

    move-result-object v5

    .line 133
    new-instance v6, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda7;

    invoke-direct {v6, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    .line 134
    new-instance v7, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$descriptionText$2;

    invoke-direct {v7, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$descriptionText$2;-><init>(Ljava/lang/Object;)V

    .line 131
    invoke-direct {v4, v5, v6, v7}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;-><init>(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V

    iput-object v4, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->descriptionText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    .line 139
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getRequiredPlugins()Ljava/util/List;

    move-result-object v4

    if-eqz v4, :cond_0

    check-cast v4, Ljava/lang/Iterable;

    invoke-static {v4}, Lkotlin/collections/CollectionsKt;->toList(Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object v4

    if-nez v4, :cond_1

    :cond_0
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v4

    :cond_1
    iput-object v4, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->requirements:Ljava/util/List;

    .line 140
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getTrusted()Z

    move-result v4

    const/4 v5, 0x1

    const/4 v6, 0x0

    if-nez v4, :cond_2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsUnknownSources()Z

    move-result v4

    if-nez v4, :cond_2

    move v4, v5

    goto :goto_0

    :cond_2
    move v4, v6

    :goto_0
    iput-boolean v4, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->unknownSourceBlocked:Z

    if-nez v4, :cond_4

    .line 141
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getBlockedTitle()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_3

    goto :goto_1

    :cond_3
    move v1, v6

    goto :goto_2

    :cond_4
    :goto_1
    move v1, v5

    :goto_2
    iput-boolean v1, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->blocked:Z

    .line 144
    new-instance v1, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda8;

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda8;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    iput-object v1, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->requirementDelegate:Lorg/telegram/messenger/Utilities$Callback;

    .line 147
    new-instance v1, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$1;

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$1;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->setDelegate(Lorg/telegram/ui/ActionBar/BottomSheet$BottomSheetDelegateInterface;)V

    .line 151
    invoke-virtual {v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    .line 153
    iput-boolean v5, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->occupyNavigationBar:Z

    .line 154
    invoke-virtual {v0, v6}, Lorg/telegram/ui/ActionBar/BottomSheet;->setApplyBottomPadding(Z)V

    const v2, 0x3e4ccccd    # 0.2f

    .line 155
    iput v2, v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->topPadding:F

    .line 157
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v4

    invoke-virtual {v0, v4}, Lorg/telegram/ui/ActionBar/BottomSheet;->setBackgroundColor(I)V

    .line 158
    iget-object v4, v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    .line 159
    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v7

    invoke-virtual {v4, v7}, Lorg/telegram/ui/ActionBar/ActionBar;->setBackgroundColor(I)V

    .line 160
    invoke-virtual {v4, v6}, Lorg/telegram/ui/ActionBar/ActionBar;->setCastShadows(Z)V

    .line 162
    iput-boolean v6, v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->showShadow:Z

    .line 164
    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v4

    iput-object v4, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->titleText:Ljava/lang/CharSequence;

    .line 165
    invoke-virtual {v0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->updateTitle()V

    .line 167
    iput-boolean v5, v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->takeTranslationIntoAccount:Z

    .line 168
    iget-object v4, v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    .line 169
    invoke-virtual {v4, v6}, Landroid/view/ViewGroup;->setClipChildren(Z)V

    .line 170
    invoke-virtual {v4, v6}, Landroidx/recyclerview/widget/RecyclerView;->setClipToPadding(Z)V

    const/4 v7, 0x2

    .line 171
    invoke-virtual {v4, v7}, Landroid/view/View;->setOverScrollMode(I)V

    .line 172
    iget v7, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->backgroundPaddingLeft:I

    invoke-virtual {v4, v7, v6, v7, v6}, Landroid/view/View;->setPadding(IIII)V

    .line 173
    invoke-virtual {v4}, Lorg/telegram/ui/Components/RecyclerListView;->setSections()V

    .line 174
    new-instance v7, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$3$1;

    invoke-direct {v7, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$3$1;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    .line 180
    invoke-virtual {v7, v6}, Landroidx/recyclerview/widget/SimpleItemAnimator;->setSupportsChangeAnimations(Z)V

    .line 181
    invoke-virtual {v7, v6}, Landroidx/recyclerview/widget/DefaultItemAnimator;->setDelayAnimations(Z)V

    .line 182
    sget-object v8, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v7, v8}, Landroidx/recyclerview/widget/RecyclerView$ItemAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    const-wide/16 v8, 0x15e

    .line 183
    invoke-virtual {v7, v8, v9}, Landroidx/recyclerview/widget/RecyclerView$ItemAnimator;->setDurations(J)V

    .line 174
    invoke-virtual {v4, v7}, Lorg/telegram/ui/Components/RecyclerListView;->setItemAnimator(Landroidx/recyclerview/widget/RecyclerView$ItemAnimator;)V

    .line 186
    iget-object v4, v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    new-instance v7, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda9;

    invoke-direct {v7, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda9;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v4, v7}, Lorg/telegram/ui/Components/RecyclerListView;->setOnItemClickListener(Lorg/telegram/ui/Components/RecyclerListView$OnItemClickListener;)V

    .line 192
    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->buildHeader(Landroid/app/Activity;)Landroid/view/View;

    move-result-object v4

    iput-object v4, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->headerView:Landroid/view/View;

    .line 193
    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->buildDescription(Landroid/app/Activity;)Landroid/view/View;

    move-result-object v4

    iput-object v4, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->descriptionView:Landroid/view/View;

    .line 194
    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->buildError(Landroid/app/Activity;)Landroid/view/View;

    move-result-object v4

    iput-object v4, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->errorView:Landroid/view/View;

    .line 195
    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->checkTranslatable()V

    .line 197
    new-instance v4, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    iget-object v7, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v4, v1, v5, v7}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;-><init>(Landroid/content/Context;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 198
    invoke-virtual {v4}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setRound()Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    .line 197
    iput-object v4, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    .line 200
    invoke-direct {v0, v6}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->applyButtonState(Z)V

    .line 202
    new-instance v7, Landroid/widget/LinearLayout;

    invoke-direct {v7, v1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 203
    invoke-virtual {v7, v5}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 204
    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v2

    invoke-static {v2}, Lcom/exteragram/messenger/utils/ui/UIUtil;->createBottomFade(I)Landroid/graphics/drawable/GradientDrawable;

    move-result-object v2

    invoke-virtual {v7, v2}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 206
    sget v2, Lorg/telegram/messenger/AndroidUtilities;->navigationBarHeight:I

    invoke-virtual {v7, v6, v6, v6, v2}, Landroid/view/View;->setPadding(IIII)V

    .line 210
    new-instance v2, Landroid/widget/LinearLayout$LayoutParams;

    const/high16 v8, 0x42400000    # 48.0f

    invoke-static {v8}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v8

    const/4 v9, -0x1

    invoke-direct {v2, v9, v8}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 211
    iget v8, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->backgroundPaddingLeft:I

    const/high16 v10, 0x41400000    # 12.0f

    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v11

    add-int/2addr v8, v11

    .line 212
    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v11

    invoke-static {v10}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v10

    invoke-virtual {v2, v8, v11, v8, v10}, Landroid/view/ViewGroup$MarginLayoutParams;->setMargins(IIII)V

    .line 213
    sget-object v8, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    .line 208
    invoke-virtual {v7, v4, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 215
    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/Plugin;->isEnabled()Z

    move-result v2

    if-nez v2, :cond_6

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v2

    if-nez v2, :cond_6

    .line 216
    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->createEnableAfterInstallRow(Landroid/app/Activity;)Landroid/view/View;

    move-result-object v1

    .line 217
    iget-boolean v2, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->blocked:Z

    if-eqz v2, :cond_5

    const/16 v2, 0x8

    goto :goto_3

    :cond_5
    move v2, v6

    :goto_3
    invoke-virtual {v1, v2}, Landroid/view/View;->setVisibility(I)V

    const/4 v15, 0x0

    const/high16 v16, 0x41000000    # 8.0f

    const/4 v10, -0x2

    const/4 v11, -0x2

    const/4 v12, 0x1

    const/4 v13, 0x0

    const/4 v14, 0x0

    .line 220
    invoke-static/range {v10 .. v16}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v2

    .line 218
    invoke-virtual {v7, v1, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 216
    iput-object v1, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->enableAfterInstallRow:Landroid/view/View;

    .line 233
    :cond_6
    new-instance v1, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda10;

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda10;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v7, v1}, Landroid/view/View;->addOnLayoutChangeListener(Landroid/view/View$OnLayoutChangeListener;)V

    .line 202
    iput-object v7, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->bottomContainer:Landroid/widget/LinearLayout;

    .line 245
    iget-object v1, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->sheetContainer:Landroid/widget/FrameLayout;

    if-eqz v1, :cond_7

    const/4 v2, -0x2

    const/16 v3, 0x50

    .line 247
    invoke-static {v9, v2, v3}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v2

    .line 245
    invoke-virtual {v1, v7, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 254
    :cond_7
    iget-object v1, v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/ActionBar;->createMenu()Lorg/telegram/ui/ActionBar/ActionBarMenu;

    move-result-object v1

    const/high16 v2, 0x40600000    # 3.5f

    .line 255
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-virtual {v1, v6, v6, v2, v6}, Landroid/view/View;->setPadding(IIII)V

    .line 256
    sget v2, Lorg/telegram/messenger/R$drawable;->msg_openin:I

    invoke-virtual {v1, v5, v2}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(II)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    .line 257
    iget-object v1, v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    new-instance v2, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$7;

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$7;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v1, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setActionBarMenuOnItemClick(Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;)V

    .line 266
    iput-boolean v5, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->contentReady:Z

    .line 267
    iget-object v1, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz v1, :cond_8

    invoke-virtual {v1, v6}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 269
    :cond_8
    iget-object v0, v0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {v0, v6, v6}, Landroidx/recyclerview/widget/LinearLayoutManager;->scrollToPositionWithOffset(II)V

    return-void

    .line 103
    :cond_9
    const-string v0, "Required value was null."

    invoke-static {v0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 v0, 0x0

    throw v0
.end method

.method public static final synthetic access$getButton$p(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;
    .locals 0

    .line 89
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    return-object p0
.end method

.method public static final synthetic access$getCancellationRequested$p(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)Z
    .locals 0

    .line 89
    iget-boolean p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->cancellationRequested:Z

    return p0
.end method

.method public static final synthetic access$getContainerView$p$s126290054(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)Landroid/view/ViewGroup;
    .locals 0

    .line 89
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    return-object p0
.end method

.method public static final synthetic access$getDelayedLoadingRunnable$p(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)Ljava/lang/Runnable;
    .locals 0

    .line 89
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->delayedLoadingRunnable:Ljava/lang/Runnable;

    return-object p0
.end method

.method public static final synthetic access$getDescriptionText$p(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;
    .locals 0

    .line 89
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->descriptionText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    return-object p0
.end method

.method public static final synthetic access$getInstalling$p(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)Z
    .locals 0

    .line 89
    iget-boolean p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->installing:Z

    return p0
.end method

.method public static final synthetic access$getNameText$p(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;
    .locals 0

    .line 89
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->nameText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    return-object p0
.end method

.method public static final synthetic access$onTextTransitionUpdate(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V
    .locals 0

    .line 89
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->onTextTransitionUpdate()V

    return-void
.end method

.method public static final synthetic access$openPluginFile(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V
    .locals 0

    .line 89
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->openPluginFile()V

    return-void
.end method

.method public static final synthetic access$setDelayedLoadingRunnable$p(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/Runnable;)V
    .locals 0

    .line 89
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->delayedLoadingRunnable:Ljava/lang/Runnable;

    return-void
.end method

.method private final applyButtonState(Z)V
    .locals 6

    .line 419
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    .line 420
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_buttonText:I

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setTextColor(I)V

    .line 422
    iget-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->unknownSourceBlocked:Z

    if-eqz v1, :cond_0

    .line 423
    sget v1, Lorg/telegram/messenger/R$string;->PluginInstallBlocked:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    goto :goto_0

    .line 425
    :cond_0
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getBlockedTitle()Ljava/lang/String;

    move-result-object v1

    .line 427
    :goto_0
    iget-boolean v2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->unknownSourceBlocked:Z

    if-eqz v2, :cond_1

    .line 428
    sget v2, Lorg/telegram/messenger/R$string;->PluginInstallBlockedInfo:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    goto :goto_1

    .line 430
    :cond_1
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getBlockedSubtitle()Ljava/lang/String;

    move-result-object v2

    .line 432
    :goto_1
    iget-boolean v3, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->blocked:Z

    const/4 v4, 0x0

    if-eqz v3, :cond_2

    if-eqz v1, :cond_2

    .line 436
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_addButton:I

    invoke-virtual {p0, v3}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v3

    const/16 v5, 0xb4

    .line 435
    invoke-static {v3, v5}, Landroidx/core/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result v3

    .line 439
    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {p0, v5}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result p0

    .line 434
    invoke-static {v3, p0}, Landroidx/core/graphics/ColorUtils;->compositeColors(II)I

    move-result p0

    .line 433
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setColor(I)V

    .line 442
    invoke-virtual {v0, v1, p1}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setText(Ljava/lang/CharSequence;Z)V

    .line 443
    invoke-virtual {v0, v2, p1}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setSubText(Ljava/lang/CharSequence;Z)V

    .line 444
    invoke-virtual {v0, v4}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const/4 p0, 0x0

    .line 445
    invoke-virtual {v0, p0}, Landroid/view/View;->setClickable(Z)V

    .line 446
    invoke-virtual {v0, p0}, Landroid/view/View;->setFocusable(Z)V

    return-void

    .line 450
    :cond_2
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_addButton:I

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setColor(I)V

    .line 452
    iget-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->isUpdate:Z

    if-eqz v1, :cond_3

    sget v1, Lorg/telegram/messenger/R$string;->UpdatePlugin:I

    goto :goto_2

    :cond_3
    sget v1, Lorg/telegram/messenger/R$string;->InstallPlugin:I

    :goto_2
    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 451
    invoke-virtual {v0, v1, p1}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setText(Ljava/lang/CharSequence;Z)V

    .line 455
    invoke-virtual {v0, v4, p1}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setSubText(Ljava/lang/CharSequence;Z)V

    const/4 p1, 0x1

    .line 456
    invoke-virtual {v0, p1}, Landroid/view/View;->setFocusable(Z)V

    .line 457
    new-instance p1, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda13;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda13;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v0, p1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method private final buildDescription(Landroid/app/Activity;)Landroid/view/View;
    .locals 4

    .line 751
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildDescription$1;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildDescription$1;-><init>(Landroid/app/Activity;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    const/4 p1, 0x3

    .line 763
    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setGravity(I)V

    .line 764
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->regular()Landroid/graphics/Typeface;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 765
    new-instance p1, Lorg/telegram/messenger/AndroidUtilities$LinkMovementMethodMy;

    invoke-direct {p1}, Lorg/telegram/messenger/AndroidUtilities$LinkMovementMethodMy;-><init>()V

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setMovementMethod(Landroid/text/method/MovementMethod;)V

    .line 766
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextLink:I

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setLinkTextColor(I)V

    const/4 p1, 0x1

    const/high16 v1, 0x41700000    # 15.0f

    .line 767
    invoke-virtual {v0, p1, v1}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 768
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result p1

    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/spoilers/SpoilersTextView;->setTextColor(I)V

    .line 769
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/Plugin;->getDescription()Ljava/lang/String;

    move-result-object p1

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    new-instance v2, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda5;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-static {p1, v1, v2}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->fullyFormatText(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/Runnable;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const/high16 p1, 0x41800000    # 16.0f

    .line 770
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    const/high16 v2, 0x41600000    # 14.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    const/high16 v3, 0x41400000    # 12.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-virtual {v0, v1, v2, p1, v3}, Landroid/view/View;->setPadding(IIII)V

    .line 771
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->descriptionText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->setView(Landroid/widget/TextView;)V

    return-object v0
.end method

.method private final buildError(Landroid/app/Activity;)Landroid/view/View;
    .locals 8

    .line 775
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedRegular:I

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v0

    .line 776
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSectionRadiusDp()I

    move-result v1

    int-to-float v1, v1

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    .line 777
    new-instance v2, Landroid/widget/TextView;

    invoke-direct {v2, p1}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 778
    const-string v3, "fonts/rmono.ttf"

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->getTypeface(Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    const/4 v3, 0x1

    const/high16 v4, 0x41400000    # 12.0f

    .line 779
    invoke-virtual {v2, v3, v4}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 780
    invoke-virtual {v2, v0}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v3, 0x41800000    # 16.0f

    .line 781
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    const/high16 v6, 0x41600000    # 14.0f

    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    invoke-virtual {v2, v5, v6, v3, v7}, Landroid/widget/TextView;->setPadding(IIII)V

    const v3, 0x3dcccccd    # 0.1f

    .line 786
    invoke-static {v0, v3}, Lorg/telegram/messenger/AndroidUtilities;->multiplyAlphaComponent(IF)I

    move-result v0

    .line 787
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {p0, v3}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v3

    .line 785
    invoke-static {v0, v3}, Landroidx/core/graphics/ColorUtils;->compositeColors(II)I

    move-result v0

    .line 782
    invoke-static {v1, v1, v0}, Lorg/telegram/ui/ActionBar/Theme;->createRoundRectDrawable(III)Landroid/graphics/drawable/ShapeDrawable;

    move-result-object v0

    invoke-virtual {v2, v0}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const v0, 0x3ca3d70a    # 0.02f

    const/high16 v1, 0x3fc00000    # 1.5f

    .line 790
    invoke-static {v2, v0, v1}, Lorg/telegram/ui/Components/ScaleStateListAnimator;->apply(Landroid/view/View;FF)V

    .line 791
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda15;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda15;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v2, v0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 800
    iput-object v2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->errorTextView:Landroid/widget/TextView;

    .line 801
    new-instance p0, Landroid/widget/FrameLayout;

    invoke-direct {p0, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 802
    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    const/4 v3, 0x0

    invoke-virtual {p0, p1, v3, v0, v1}, Landroid/view/View;->setPadding(IIII)V

    const/4 p1, -0x1

    const/high16 v0, -0x40000000    # -2.0f

    .line 805
    invoke-static {p1, v0}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object p1

    .line 803
    invoke-virtual {p0, v2, p1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    return-object p0
.end method

.method private final buildHeader(Landroid/app/Activity;)Landroid/view/View;
    .locals 21

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    .line 461
    new-instance v2, Landroid/widget/LinearLayout;

    invoke-direct {v2, v1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/4 v3, 0x1

    .line 462
    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->setOrientation(I)V

    const/4 v4, 0x0

    .line 463
    invoke-virtual {v2, v4}, Landroid/view/ViewGroup;->setClipChildren(Z)V

    .line 464
    invoke-virtual {v2, v4}, Landroid/view/ViewGroup;->setClipToPadding(Z)V

    .line 467
    iget-object v5, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getIconDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v5

    const/high16 v6, 0x41400000    # 12.0f

    const/high16 v7, 0x41800000    # 16.0f

    const/16 v8, 0x11

    if-nez v5, :cond_1

    .line 468
    iget-object v9, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v9}, Lcom/exteragram/messenger/plugins/Plugin;->getPack()Ljava/lang/String;

    move-result-object v9

    if-eqz v9, :cond_0

    iget-object v9, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v9}, Lcom/exteragram/messenger/plugins/Plugin;->getIndex()I

    move-result v9

    if-ltz v9, :cond_0

    goto :goto_0

    .line 525
    :cond_0
    new-instance v5, Landroid/widget/ImageView;

    invoke-direct {v5, v1}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 526
    sget-object v9, Landroid/widget/ImageView$ScaleType;->FIT_CENTER:Landroid/widget/ImageView$ScaleType;

    invoke-virtual {v5, v9}, Landroid/widget/ImageView;->setScaleType(Landroid/widget/ImageView$ScaleType;)V

    .line 527
    sget v9, Lorg/telegram/messenger/R$drawable;->plugins_filled:I

    invoke-virtual {v5, v9}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 528
    new-instance v9, Landroid/graphics/PorterDuffColorFilter;

    .line 529
    sget v10, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_buttonText:I

    invoke-virtual {v0, v10}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v10

    .line 530
    sget-object v11, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    .line 528
    invoke-direct {v9, v10, v11}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v5, v9}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    const/high16 v9, 0x429c0000    # 78.0f

    .line 533
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v9

    .line 534
    sget v10, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_addButton:I

    invoke-virtual {v0, v10}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v10

    .line 532
    invoke-static {v9, v10}, Lorg/telegram/ui/ActionBar/Theme;->createCircleDrawable(II)Landroid/graphics/drawable/ShapeDrawable;

    move-result-object v9

    invoke-virtual {v5, v9}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 536
    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v9

    .line 537
    invoke-virtual {v5, v9, v9, v9, v9}, Landroid/view/View;->setPadding(IIII)V

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v10, 0x4e

    const/16 v11, 0x4e

    const/4 v12, 0x1

    const/4 v13, 0x0

    const/high16 v14, 0x41e00000    # 28.0f

    .line 541
    invoke-static/range {v10 .. v16}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v9

    .line 539
    invoke-virtual {v2, v5, v9}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    goto/16 :goto_1

    .line 469
    :cond_1
    :goto_0
    new-instance v9, Landroid/widget/FrameLayout;

    invoke-direct {v9, v1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 470
    invoke-virtual {v9, v4}, Landroid/view/ViewGroup;->setClipChildren(Z)V

    .line 471
    invoke-virtual {v9, v4}, Landroid/view/ViewGroup;->setClipToPadding(Z)V

    .line 473
    new-instance v11, Lorg/telegram/ui/Components/BackupImageView;

    invoke-direct {v11, v1}, Lorg/telegram/ui/Components/BackupImageView;-><init>(Landroid/content/Context;)V

    .line 474
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v10

    invoke-static {v10}, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->boundsWithRoundRect(F)Landroid/view/ViewOutlineProvider;

    move-result-object v10

    invoke-virtual {v11, v10}, Landroid/view/View;->setOutlineProvider(Landroid/view/ViewOutlineProvider;)V

    .line 475
    invoke-virtual {v11, v3}, Landroid/view/View;->setClipToOutline(Z)V

    .line 476
    invoke-virtual {v11}, Lorg/telegram/ui/Components/BackupImageView;->getImageReceiver()Lorg/telegram/messenger/ImageReceiver;

    move-result-object v10

    invoke-virtual {v10, v3}, Lorg/telegram/messenger/ImageReceiver;->setAutoRepeat(I)V

    const/16 v10, 0x4e

    .line 477
    invoke-static {v10, v10, v8}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v10

    invoke-virtual {v9, v11, v10}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 479
    new-instance v10, Landroid/widget/FrameLayout;

    invoke-direct {v10, v1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    const/high16 v12, 0x41e00000    # 28.0f

    .line 482
    invoke-static {v12}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v12

    .line 483
    sget v13, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {v0, v13}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v13

    .line 481
    invoke-static {v12, v13}, Lorg/telegram/ui/ActionBar/Theme;->createCircleDrawable(II)Landroid/graphics/drawable/ShapeDrawable;

    move-result-object v12

    .line 480
    invoke-virtual {v10, v12}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 486
    new-instance v12, Landroid/widget/FrameLayout;

    invoke-direct {v12, v1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    const/high16 v13, 0x41c00000    # 24.0f

    .line 488
    invoke-static {v13}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v13

    .line 489
    sget v14, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_addButton:I

    invoke-virtual {v0, v14}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v14

    .line 487
    invoke-static {v13, v14}, Lorg/telegram/ui/ActionBar/Theme;->createCircleDrawable(II)Landroid/graphics/drawable/ShapeDrawable;

    move-result-object v13

    invoke-virtual {v12, v13}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/16 v13, 0x18

    .line 492
    invoke-static {v13, v13, v8}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v13

    invoke-virtual {v10, v12, v13}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 494
    new-instance v12, Landroid/widget/ImageView;

    invoke-direct {v12, v1}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 495
    sget v13, Lorg/telegram/messenger/R$drawable;->plugins_filled:I

    invoke-virtual {v12, v13}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 496
    new-instance v13, Landroid/graphics/PorterDuffColorFilter;

    .line 497
    sget v14, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_buttonText:I

    invoke-virtual {v0, v14}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v14

    .line 498
    sget-object v15, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    .line 496
    invoke-direct {v13, v14, v15}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v12, v13}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 500
    sget-object v13, Landroid/widget/ImageView$ScaleType;->FIT_CENTER:Landroid/widget/ImageView$ScaleType;

    invoke-virtual {v12, v13}, Landroid/widget/ImageView;->setScaleType(Landroid/widget/ImageView$ScaleType;)V

    const/16 v13, 0x10

    .line 502
    invoke-static {v13, v13, v8}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v13

    invoke-virtual {v10, v12, v13}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    const/high16 v19, -0x3f800000    # -4.0f

    const/high16 v20, -0x3f800000    # -4.0f

    const/16 v14, 0x1c

    const/high16 v15, 0x41e00000    # 28.0f

    const v16, 0x800055

    const/16 v17, 0x0

    const/16 v18, 0x0

    .line 506
    invoke-static/range {v14 .. v20}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v12

    .line 504
    invoke-virtual {v9, v10, v12}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    const/16 v19, 0x0

    const/16 v13, 0x4e

    const/16 v14, 0x4e

    const/4 v15, 0x1

    const/16 v16, 0x0

    const/high16 v17, 0x41e00000    # 28.0f

    .line 510
    invoke-static/range {v13 .. v19}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v10

    .line 508
    invoke-virtual {v2, v9, v10}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    if-eqz v5, :cond_2

    .line 513
    invoke-virtual {v11, v5}, Lorg/telegram/ui/Components/BackupImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    goto :goto_1

    .line 515
    :cond_2
    sget v5, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v5}, Lorg/telegram/messenger/MediaDataController;->getInstance(I)Lorg/telegram/messenger/MediaDataController;

    move-result-object v10

    .line 518
    iget-object v5, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/Plugin;->getPack()Ljava/lang/String;

    move-result-object v12

    .line 519
    iget-object v5, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/Plugin;->getIndex()I

    move-result v13

    .line 520
    const-string v14, "150_150"

    .line 521
    iget-object v15, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 516
    invoke-virtual/range {v10 .. v15}, Lorg/telegram/messenger/MediaDataController;->setPlaceholderImageByIndex(Lorg/telegram/ui/Components/BackupImageView;Ljava/lang/String;ILjava/lang/String;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 545
    :goto_1
    new-instance v5, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildHeader$pluginNameView$1;

    invoke-direct {v5, v1, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildHeader$pluginNameView$1;-><init>(Landroid/app/Activity;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    .line 557
    invoke-virtual {v5, v3}, Landroid/widget/TextView;->setGravity(I)V

    .line 558
    sget v9, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {v0, v9}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v9

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v9, 0x41900000    # 18.0f

    .line 559
    invoke-virtual {v5, v3, v9}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 560
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v9

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 561
    iget-object v9, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v9}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 563
    iget-object v9, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->nameText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    invoke-virtual {v9, v5}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->setView(Landroid/widget/TextView;)V

    const/high16 v15, 0x42200000    # 40.0f

    const/16 v16, 0x0

    const/4 v10, -0x1

    const/4 v11, -0x2

    const/4 v12, 0x0

    const/high16 v13, 0x42200000    # 40.0f

    const/high16 v14, 0x41800000    # 16.0f

    .line 566
    invoke-static/range {v10 .. v16}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v9

    .line 564
    invoke-virtual {v2, v5, v9}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 577
    new-instance v5, Lorg/telegram/ui/Components/EffectsTextView;

    iget-object v9, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    invoke-virtual {v9}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v9

    invoke-direct {v5, v1, v9}, Lorg/telegram/ui/Components/EffectsTextView;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 578
    invoke-virtual {v5, v3}, Landroid/widget/TextView;->setGravity(I)V

    .line 579
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->regular()Landroid/graphics/Typeface;

    move-result-object v9

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 580
    new-instance v9, Lorg/telegram/messenger/AndroidUtilities$LinkMovementMethodMy;

    invoke-direct {v9}, Lorg/telegram/messenger/AndroidUtilities$LinkMovementMethodMy;-><init>()V

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setMovementMethod(Landroid/text/method/MovementMethod;)V

    .line 581
    sget v9, Lorg/telegram/ui/ActionBar/Theme;->key_dialogTextLink:I

    invoke-virtual {v0, v9}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v9

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setLinkTextColor(I)V

    const/high16 v9, 0x41600000    # 14.0f

    .line 582
    invoke-virtual {v5, v3, v9}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 583
    sget v9, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteGrayText:I

    invoke-virtual {v0, v9}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v9

    invoke-virtual {v5, v9}, Lorg/telegram/ui/Components/spoilers/SpoilersTextView;->setTextColor(I)V

    .line 586
    new-instance v9, Landroid/text/SpannableStringBuilder;

    sget v10, Lorg/telegram/messenger/R$string;->PluginVersion:I

    invoke-static {v10}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v10

    invoke-direct {v9, v10}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    const-string v10, " "

    invoke-virtual {v9, v10}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object v9

    .line 587
    invoke-virtual {v9}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v10

    .line 588
    iget-boolean v11, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->isUpdate:Z

    if-eqz v11, :cond_5

    .line 589
    sget-object v11, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v11

    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v11

    iget-object v12, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v12}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v11, v12}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz v11, :cond_6

    .line 591
    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/Plugin;->getVersion()Ljava/lang/String;

    move-result-object v11

    const-string v12, ""

    if-nez v11, :cond_3

    move-object v11, v12

    .line 592
    :cond_3
    iget-object v13, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v13}, Lcom/exteragram/messenger/plugins/Plugin;->getVersion()Ljava/lang/String;

    move-result-object v13

    if-nez v13, :cond_4

    goto :goto_2

    :cond_4
    move-object v12, v13

    .line 593
    :goto_2
    invoke-virtual {v9, v11}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object v13

    const-string v14, " -> "

    invoke-virtual {v13, v14}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object v13

    invoke-virtual {v13, v12}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 594
    invoke-virtual {v0}, Landroid/app/Dialog;->getContext()Landroid/content/Context;

    move-result-object v12

    sget v13, Lorg/telegram/messenger/R$drawable;->msg_mini_arrow_mediathin:I

    invoke-static {v12, v9, v13}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->replaceArrows(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/text/SpannableStringBuilder;

    move-result-object v9

    .line 596
    new-instance v12, Landroid/text/style/StrikethroughSpan;

    invoke-direct {v12}, Landroid/text/style/StrikethroughSpan;-><init>()V

    .line 598
    invoke-virtual {v11}, Ljava/lang/String;->length()I

    move-result v11

    add-int/2addr v11, v10

    const/16 v13, 0x21

    .line 595
    invoke-virtual {v9, v12, v10, v11, v13}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    goto :goto_3

    .line 603
    :cond_5
    iget-object v10, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/Plugin;->getVersion()Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v9, v10}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 605
    :cond_6
    :goto_3
    const-string v10, " \u2022 "

    invoke-virtual {v9, v10}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object v10

    .line 606
    iget-object v11, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/Plugin;->getAuthor()Ljava/lang/String;

    move-result-object v11

    iget-object v12, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    new-instance v13, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda5;

    invoke-direct {v13, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-static {v11, v12, v13}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->formatWithUsernames(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/Runnable;)Ljava/lang/CharSequence;

    move-result-object v11

    invoke-virtual {v10, v11}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 607
    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const/high16 v17, 0x41a80000    # 21.0f

    const/16 v18, 0x0

    const/4 v12, -0x1

    const/4 v13, -0x2

    const/4 v14, 0x0

    const/high16 v15, 0x41a80000    # 21.0f

    const/high16 v16, 0x40800000    # 4.0f

    .line 610
    invoke-static/range {v12 .. v18}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v9

    .line 608
    invoke-virtual {v2, v5, v9}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 621
    iget-object v5, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getTrusted()Z

    move-result v5

    if-eqz v5, :cond_7

    sget v5, Lorg/telegram/messenger/R$drawable;->trusted_mini:I

    goto :goto_4

    :cond_7
    sget v5, Lorg/telegram/messenger/R$drawable;->unknown_mini:I

    .line 623
    :goto_4
    iget-object v9, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v9}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getTrusted()Z

    move-result v9

    if-eqz v9, :cond_8

    sget v9, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteGreenText:I

    goto :goto_5

    :cond_8
    sget v9, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedRegular:I

    :goto_5
    invoke-virtual {v0, v9}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v9

    .line 625
    iget-object v10, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getTrusted()Z

    move-result v10

    if-eqz v10, :cond_9

    sget v10, Lorg/telegram/messenger/R$string;->PluginSourceTrusted:I

    :goto_6
    invoke-static {v10}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v10

    goto :goto_7

    .line 626
    :cond_9
    sget v10, Lorg/telegram/messenger/R$string;->PluginSourceUnknown:I

    goto :goto_6

    .line 629
    :goto_7
    new-instance v11, Landroid/widget/LinearLayout;

    invoke-direct {v11, v1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const v12, 0x3d4ccccd    # 0.05f

    const/high16 v13, 0x3fc00000    # 1.5f

    .line 630
    invoke-static {v11, v12, v13}, Lorg/telegram/ui/Components/ScaleStateListAnimator;->apply(Landroid/view/View;FF)V

    .line 631
    invoke-virtual {v11, v4}, Landroid/widget/LinearLayout;->setOrientation(I)V

    const/high16 v12, 0x41a00000    # 20.0f

    .line 633
    invoke-static {v12}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v14

    .line 634
    invoke-static {v12}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v15

    move/from16 v16, v6

    const v6, 0x3dcccccd    # 0.1f

    .line 636
    invoke-static {v9, v6}, Lorg/telegram/messenger/AndroidUtilities;->multiplyAlphaComponent(IF)I

    move-result v6

    move/from16 v17, v7

    .line 637
    sget v7, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {v0, v7}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v7

    .line 635
    invoke-static {v6, v7}, Landroidx/core/graphics/ColorUtils;->compositeColors(II)I

    move-result v6

    .line 632
    invoke-static {v14, v15, v6}, Lorg/telegram/ui/ActionBar/Theme;->createRoundRectDrawable(III)Landroid/graphics/drawable/ShapeDrawable;

    move-result-object v6

    invoke-virtual {v11, v6}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 640
    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    const/high16 v7, 0x40c00000    # 6.0f

    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v14

    invoke-static/range {v17 .. v17}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v15

    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    invoke-virtual {v11, v6, v14, v15, v7}, Landroid/view/View;->setPadding(IIII)V

    .line 641
    invoke-virtual {v11, v8}, Landroid/widget/LinearLayout;->setGravity(I)V

    .line 644
    new-instance v6, Landroid/widget/ImageView;

    invoke-direct {v6, v1}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 645
    invoke-virtual {v6, v5}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 646
    new-instance v5, Landroid/graphics/PorterDuffColorFilter;

    sget-object v7, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v5, v9, v7}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v6, v5}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    const/high16 v19, 0x40c00000    # 6.0f

    const/16 v20, 0x0

    const/16 v14, 0xe

    const/16 v15, 0xe

    const/16 v16, 0x10

    const/16 v17, 0x0

    const/16 v18, 0x0

    .line 650
    invoke-static/range {v14 .. v20}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v5

    .line 648
    invoke-virtual {v11, v6, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 653
    new-instance v5, Landroid/widget/TextView;

    invoke-direct {v5, v1}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 654
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->regular()Landroid/graphics/Typeface;

    move-result-object v6

    invoke-virtual {v5, v6}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 655
    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setTextColor(I)V

    const/high16 v6, 0x41500000    # 13.0f

    .line 656
    invoke-virtual {v5, v3, v6}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 657
    invoke-virtual {v5, v10}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 659
    invoke-virtual {v11, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    const/16 v19, 0x0

    const/4 v14, -0x2

    const/4 v15, -0x2

    const/16 v16, 0x11

    const/high16 v18, 0x41400000    # 12.0f

    .line 662
    invoke-static/range {v14 .. v20}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v5

    .line 660
    invoke-virtual {v2, v11, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 672
    new-instance v5, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda17;

    invoke-direct {v5, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda17;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v11, v5}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 674
    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteGrayIcon:I

    invoke-virtual {v0, v5}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v5

    .line 675
    new-instance v6, Landroid/widget/ImageView;

    invoke-direct {v6, v1}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    const v7, 0x3e19999a    # 0.15f

    .line 676
    invoke-static {v6, v7, v13}, Lorg/telegram/ui/Components/ScaleStateListAnimator;->apply(Landroid/view/View;FF)V

    .line 677
    sget v8, Lorg/telegram/messenger/R$drawable;->msg_openin:I

    invoke-static {v1, v8}, Landroidx/core/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v8

    if-eqz v8, :cond_a

    invoke-virtual {v8}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v8

    goto :goto_8

    :cond_a
    const/4 v8, 0x0

    :goto_8
    invoke-virtual {v6, v8}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 678
    new-instance v8, Landroid/graphics/PorterDuffColorFilter;

    sget-object v9, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v8, v5, v9}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v6, v8}, Landroid/widget/ImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 679
    sget-object v8, Landroid/widget/ImageView$ScaleType;->CENTER:Landroid/widget/ImageView$ScaleType;

    invoke-virtual {v6, v8}, Landroid/widget/ImageView;->setScaleType(Landroid/widget/ImageView$ScaleType;)V

    .line 680
    new-instance v8, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda18;

    invoke-direct {v8, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda18;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v6, v8}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 682
    sget v8, Lorg/telegram/ui/ActionBar/Theme;->key_dialogButtonSelector:I

    invoke-virtual {v0, v8}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v9

    .line 683
    invoke-static {v12}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v10

    .line 681
    invoke-static {v9, v3, v10}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(III)Landroid/graphics/drawable/Drawable;

    move-result-object v9

    invoke-virtual {v6, v9}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 686
    new-instance v9, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

    invoke-direct {v9, v1, v5}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;-><init>(Landroid/content/Context;I)V

    .line 687
    invoke-static {v9, v7, v13}, Lorg/telegram/ui/Components/ScaleStateListAnimator;->apply(Landroid/view/View;FF)V

    const/16 v5, 0x8

    .line 688
    invoke-virtual {v9, v5}, Landroid/view/View;->setVisibility(I)V

    .line 689
    new-instance v5, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda19;

    invoke-direct {v5, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda19;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v9, v5}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 691
    invoke-virtual {v0, v8}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v5

    .line 692
    invoke-static {v12}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    .line 690
    invoke-static {v5, v3, v7}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorDrawable(III)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    invoke-virtual {v9, v3}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 695
    iput-object v9, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->translateButton:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

    .line 696
    new-instance v3, Landroid/widget/LinearLayout;

    invoke-direct {v3, v1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 697
    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->setOrientation(I)V

    const/high16 v5, 0x40000000    # 2.0f

    .line 698
    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    .line 699
    invoke-virtual {v3, v5, v5, v5, v5}, Landroid/view/View;->setPadding(IIII)V

    .line 700
    new-instance v5, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    invoke-direct {v5}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;-><init>()V

    .line 701
    sget v7, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {v0, v7}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v7

    invoke-virtual {v5, v7}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;->setColor(I)V

    .line 702
    invoke-virtual {v5}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;->createDrawable()Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v5

    .line 703
    iget-object v7, v0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v7}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->topPanelChatActivity(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object v7

    invoke-virtual {v5, v7}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setColorProvider(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v5

    const/high16 v7, 0x41b00000    # 22.0f

    .line 704
    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v7

    invoke-virtual {v5, v7}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setRadius(F)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v5

    const/4 v7, 0x0

    .line 705
    invoke-virtual {v5, v7}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setShadowAlpha(F)V

    .line 700
    invoke-virtual {v3, v5}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/16 v5, 0x28

    .line 706
    invoke-static {v5, v5}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(II)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v7

    invoke-virtual {v3, v9, v7}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 707
    invoke-static {v5, v5}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(II)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v5

    invoke-virtual {v3, v6, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 709
    iput-object v3, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->actionsPill:Landroid/widget/LinearLayout;

    .line 711
    iget-object v5, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getTrusted()Z

    move-result v5

    if-eqz v5, :cond_b

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v5

    const-string/jumbo v6, "trusted_source_hint"

    invoke-interface {v5, v6, v4}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v5

    if-eqz v5, :cond_c

    .line 712
    :cond_b
    iget-object v5, v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getTrusted()Z

    move-result v5

    if-nez v5, :cond_d

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v5

    const-string/jumbo v6, "unknown_source_hint"

    invoke-interface {v5, v6, v4}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v5

    if-nez v5, :cond_d

    .line 714
    :cond_c
    new-instance v5, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda20;

    invoke-direct {v5, v11, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda20;-><init>(Landroid/widget/LinearLayout;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    const-wide/16 v6, 0x258

    invoke-static {v5, v6, v7}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    .line 725
    :cond_d
    new-instance v0, Landroid/widget/FrameLayout;

    invoke-direct {v0, v1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 726
    invoke-virtual {v0, v4}, Landroid/view/ViewGroup;->setClipChildren(Z)V

    .line 727
    invoke-virtual {v0, v4}, Landroid/view/ViewGroup;->setClipToPadding(Z)V

    const/4 v1, -0x1

    const/high16 v4, -0x40000000    # -2.0f

    .line 730
    invoke-static {v1, v4}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v1

    .line 728
    invoke-virtual {v0, v2, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    const/high16 v9, 0x41400000    # 12.0f

    const/4 v10, 0x0

    const/4 v4, -0x2

    const/high16 v5, 0x42300000    # 44.0f

    const/16 v6, 0x35

    const/4 v7, 0x0

    const/high16 v8, 0x41600000    # 14.0f

    .line 737
    invoke-static/range {v4 .. v10}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v1

    .line 735
    invoke-virtual {v0, v3, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    return-object v0
.end method

.method private final checkRequirementsSatisfied()V
    .locals 2

    .line 411
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->unknownSourceBlocked:Z

    if-nez v0, :cond_3

    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->blocked:Z

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->requirements:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_3

    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->requirements:Ljava/util/List;

    check-cast v0, Ljava/lang/Iterable;

    .line 1807
    instance-of v1, v0, Ljava/util/Collection;

    if-eqz v1, :cond_0

    move-object v1, v0

    check-cast v1, Ljava/util/Collection;

    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 1808
    :cond_0
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_1
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;

    .line 411
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;->isSatisfied()Z

    move-result v1

    if-nez v1, :cond_1

    return-void

    :cond_2
    :goto_0
    const/4 v0, 0x0

    .line 414
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->blocked:Z

    const/4 v1, 0x1

    .line 415
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->applyButtonState(Z)V

    .line 416
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->enableAfterInstallRow:Landroid/view/View;

    if-eqz p0, :cond_3

    invoke-virtual {p0, v0}, Landroid/view/View;->setVisibility(I)V

    :cond_3
    return-void
.end method

.method private final checkTranslatable()V
    .locals 5

    .line 911
    invoke-static {}, Lorg/telegram/messenger/LanguageDetector;->hasSupport()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_2

    .line 914
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->nameText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->descriptionText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    filled-new-array {v0, v1}, [Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    move-result-object v0

    const/4 v1, 0x0

    :goto_0
    const/4 v2, 0x2

    if-ge v1, v2, :cond_3

    aget-object v2, v0, v1

    .line 915
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->getOriginal()Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_2

    .line 916
    invoke-static {v3}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v4

    if-eqz v4, :cond_1

    goto :goto_1

    .line 919
    :cond_1
    new-instance v4, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda3;

    invoke-direct {v4, v2, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    const/4 v2, 0x0

    invoke-static {v3, v4, v2}, Lorg/telegram/messenger/LanguageDetector;->detectLanguage(Ljava/lang/String;Lorg/telegram/messenger/LanguageDetector$StringCallback;Lorg/telegram/messenger/LanguageDetector$ExceptionCallback;)V

    :cond_2
    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_3
    :goto_2
    return-void
.end method

.method private final createEnableAfterInstallRow(Landroid/app/Activity;)Landroid/view/View;
    .locals 4

    .line 882
    new-instance v0, Lcom/exteragram/messenger/components/CheckBoxRow;

    .line 884
    sget v1, Lorg/telegram/messenger/R$string;->EnableAfterInstallation:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 885
    iget-boolean v2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->enableAfterInstallation:Z

    .line 886
    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 882
    invoke-direct {v0, p1, v1, v2, v3}, Lcom/exteragram/messenger/components/CheckBoxRow;-><init>(Landroid/content/Context;Ljava/lang/CharSequence;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 888
    new-instance p1, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda21;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda21;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/components/CheckBoxRow;->setOnCheckedChange(Lkotlin/jvm/functions/Function1;)V

    return-object v0
.end method

.method private final fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
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

    .line 287
    iget-boolean p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->contentReady:Z

    if-nez p2, :cond_0

    goto/16 :goto_5

    .line 290
    :cond_0
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->headerView:Landroid/view/View;

    if-eqz p2, :cond_1

    .line 291
    invoke-static {p2}, Lorg/telegram/ui/Components/UItem;->asCustomShadow(Landroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    const/high16 p2, 0x41a00000    # 20.0f

    .line 292
    invoke-direct {p0, p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->spacer(F)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 294
    :cond_1
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->descriptionView:Landroid/view/View;

    if-eqz p2, :cond_2

    invoke-static {p2}, Lorg/telegram/ui/Components/UItem;->asCustom(Landroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 296
    :cond_2
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->requirements:Ljava/util/List;

    check-cast p2, Ljava/util/Collection;

    invoke-interface {p2}, Ljava/util/Collection;->isEmpty()Z

    move-result p2

    const/high16 v0, 0x41400000    # 12.0f

    if-nez p2, :cond_3

    .line 297
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->spacer(F)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 298
    sget p2, Lorg/telegram/messenger/R$string;->PluginRequired:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-direct {p0, p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->header(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 299
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->requirements:Ljava/util/List;

    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;

    .line 300
    sget-object v2, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginCell$Factory;->Companion:Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginCell$Factory$Companion;

    iget-object v3, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->requirementDelegate:Lorg/telegram/messenger/Utilities$Callback;

    invoke-virtual {v2, v1, v3}, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginCell$Factory$Companion;->asRequirement(Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;Lorg/telegram/messenger/Utilities$Callback;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 304
    :cond_3
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/Plugin;->getRequirements()Ljava/util/List;

    move-result-object p2

    .line 305
    move-object v1, p2

    check-cast v1, Ljava/util/Collection;

    const/4 v2, 0x0

    if-eqz v1, :cond_5

    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_4

    goto :goto_2

    .line 306
    :cond_4
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->spacer(F)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 307
    sget v1, Lorg/telegram/messenger/R$string;->PluginRequirements:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->header(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 308
    check-cast p2, Ljava/lang/Iterable;

    invoke-interface {p2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p2

    move v1, v2

    :goto_1
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_5

    add-int/lit8 v3, v1, 0x1

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    add-int/lit16 v1, v1, 0x3e8

    .line 309
    invoke-direct {p0, v1, v4}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->pipRow(ILjava/lang/String;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move v1, v3

    goto :goto_1

    .line 313
    :cond_5
    :goto_2
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getBundledPackages()Ljava/util/List;

    move-result-object p2

    if-eqz p2, :cond_7

    move-object v1, p2

    check-cast v1, Ljava/util/Collection;

    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_6

    goto :goto_3

    :cond_6
    const/4 p2, 0x0

    :goto_3
    if-eqz p2, :cond_7

    .line 314
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->spacer(F)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 315
    sget v1, Lorg/telegram/messenger/R$string;->PluginBundledPackages:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->header(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 316
    check-cast p2, Ljava/lang/Iterable;

    invoke-interface {p2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_4
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_7

    add-int/lit8 v1, v2, 0x1

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    add-int/lit16 v2, v2, 0x5dc

    .line 317
    invoke-direct {p0, v2, v3}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->wheelRow(ILjava/lang/String;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move v2, v1

    goto :goto_4

    .line 321
    :cond_7
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->errorText:Ljava/lang/String;

    if-eqz p2, :cond_8

    .line 322
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->errorView:Landroid/view/View;

    if-eqz p2, :cond_8

    .line 323
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->spacer(F)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 324
    invoke-static {p2}, Lorg/telegram/ui/Components/UItem;->asCustomShadow(Landroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_8
    :goto_5
    return-void
.end method

.method private final header(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;
    .locals 0

    .line 351
    invoke-static {p1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    new-instance p1, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda27;

    invoke-direct {p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda27;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UItem;->onBind(Lorg/telegram/messenger/Utilities$Callback;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0
.end method

.method private final onInstallClick()V
    .locals 5

    .line 1017
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->installing:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    .line 1018
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->cancellationRequested:Z

    if-nez v0, :cond_2

    .line 1019
    iput-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->cancellationRequested:Z

    .line 1020
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setLoading(Z)V

    .line 1021
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->button:Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;

    const/4 v0, 0x0

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Stories/recorder/ButtonWithCounterView;->setSubText(Ljava/lang/CharSequence;Z)V

    return-void

    .line 1026
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    .line 1027
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/Plugin;->getEngine()Ljava/lang/String;

    move-result-object v2

    if-nez v2, :cond_1

    const-string/jumbo v2, "python"

    .line 1026
    :cond_1
    invoke-virtual {v0, v2}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    if-nez v0, :cond_3

    :cond_2
    return-void

    .line 1030
    :cond_3
    iput-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->installing:Z

    const/4 v1, 0x0

    .line 1031
    iput-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->cancellationRequested:Z

    .line 1033
    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCanDismissWithSwipe(Z)V

    .line 1034
    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->setCanDismissWithTouchOutside(Z)V

    .line 1036
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->delayedLoadingRunnable:Ljava/lang/Runnable;

    if-eqz v1, :cond_4

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 1037
    :cond_4
    new-instance v1, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    iput-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->delayedLoadingRunnable:Ljava/lang/Runnable;

    const-wide/16 v2, 0xfa

    .line 1038
    invoke-static {v1, v2, v3}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    .line 1040
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getFilePath()Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    new-instance v3, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda2;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    .line 1091
    new-instance v4, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$onInstallClick$4;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$onInstallClick$4;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    .line 1040
    invoke-interface {v0, v1, v2, v3, v4}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->loadPluginFromFile(Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/messenger/Utilities$Callback;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    return-void
.end method

.method private final onRequirementAction(Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;)V
    .locals 4

    .line 383
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;->isSatisfied()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 386
    :cond_0
    iget v0, p1, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;->state:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_1

    .line 387
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    iget-object v2, p1, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;->id:Ljava/lang/String;

    new-instance v3, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda30;

    invoke-direct {v3, p1, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda30;-><init>(Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    invoke-virtual {v0, v2, v1, v3}, Lcom/exteragram/messenger/plugins/PluginsController;->setPluginEnabled(Ljava/lang/String;ZLorg/telegram/messenger/Utilities$Callback;)V

    return-void

    .line 405
    :cond_1
    iget-object p1, p1, Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;->downloadUrl:Ljava/lang/String;

    if-nez p1, :cond_2

    :goto_0
    return-void

    .line 406
    :cond_2
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->dismiss()V

    .line 407
    invoke-virtual {p0}, Landroid/app/Dialog;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method

.method private final onTextTransitionUpdate()V
    .locals 0

    .line 1013
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->containerView:Landroid/view/ViewGroup;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_0
    return-void
.end method

.method private final onTranslateClick()V
    .locals 11

    .line 931
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->translateButton:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

    if-nez v0, :cond_0

    goto/16 :goto_2

    .line 932
    :cond_0
    iget-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->translating:Z

    if-eqz v1, :cond_1

    goto/16 :goto_2

    .line 935
    :cond_1
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->getState()I

    move-result v1

    const/4 v2, 0x2

    const/4 v3, 0x0

    if-ne v1, v2, :cond_2

    .line 936
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showTexts(Z)V

    .line 937
    invoke-virtual {v0, v3}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->setState(I)V

    return-void

    .line 941
    :cond_2
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->nameText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    iget-object v4, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->descriptionText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    filled-new-array {v1, v4}, [Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    move-result-object v1

    .line 3938
    new-instance v9, Ljava/util/ArrayList;

    invoke-direct {v9}, Ljava/util/ArrayList;-><init>()V

    move v4, v3

    :goto_0
    if-ge v4, v2, :cond_4

    .line 4474
    aget-object v5, v1, v4

    .line 941
    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->getLanguage()Ljava/lang/String;

    move-result-object v6

    if-eqz v6, :cond_3

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->getTranslation()Ljava/lang/String;

    move-result-object v6

    if-nez v6, :cond_3

    .line 4474
    invoke-interface {v9, v5}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    :cond_3
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 942
    :cond_4
    invoke-interface {v9}, Ljava/util/List;->isEmpty()Z

    move-result v1

    const/4 v4, 0x1

    if-eqz v1, :cond_5

    .line 943
    invoke-direct {p0, v4}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showTexts(Z)V

    .line 944
    invoke-virtual {v0, v2}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->setState(I)V

    return-void

    .line 948
    :cond_5
    iput-boolean v4, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->translating:Z

    .line 949
    invoke-virtual {v0, v4}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->setState(I)V

    .line 950
    invoke-static {}, Lcom/exteragram/messenger/translator/TranslatorUtils;->getResolvedTargetLanguageCode()Ljava/lang/String;

    move-result-object v0

    .line 951
    new-instance v7, Lkotlin/jvm/internal/Ref$IntRef;

    invoke-direct {v7}, Lkotlin/jvm/internal/Ref$IntRef;-><init>()V

    invoke-interface {v9}, Ljava/util/List;->size()I

    move-result v1

    iput v1, v7, Lkotlin/jvm/internal/Ref$IntRef;->element:I

    .line 952
    new-instance v5, Lkotlin/jvm/internal/Ref$BooleanRef;

    invoke-direct {v5}, Lkotlin/jvm/internal/Ref$BooleanRef;-><init>()V

    .line 953
    invoke-virtual {v9}, Ljava/util/ArrayList;->size()I

    move-result v1

    :goto_1
    if-ge v3, v1, :cond_8

    invoke-virtual {v9, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v3, v3, 0x1

    move-object v6, v2

    check-cast v6, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    .line 954
    invoke-virtual {v6}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->startLoading()V

    .line 955
    invoke-virtual {v6}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->getOriginal()Ljava/lang/String;

    move-result-object v2

    if-nez v2, :cond_6

    const-string v2, ""

    :cond_6
    invoke-virtual {v6}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->getLanguage()Ljava/lang/String;

    move-result-object v4

    if-nez v4, :cond_7

    const-string v4, "auto"

    :cond_7
    move-object v10, v4

    new-instance v4, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda4;

    move-object v8, p0

    invoke-direct/range {v4 .. v9}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda4;-><init>(Lkotlin/jvm/internal/Ref$BooleanRef;Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Lkotlin/jvm/internal/Ref$IntRef;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/util/List;)V

    invoke-direct {v8, v2, v10, v0, v4}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->translateText(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V

    goto :goto_1

    :cond_8
    :goto_2
    return-void
.end method

.method private final onTranslationFinished(Ljava/util/List;Z)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;",
            ">;Z)V"
        }
    .end annotation

    const/4 v0, 0x0

    .line 990
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->translating:Z

    if-eqz p2, :cond_0

    const/4 p2, 0x1

    .line 992
    invoke-direct {p0, p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showTexts(Z)V

    .line 993
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->translateButton:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

    if-eqz p0, :cond_2

    const/4 p2, 0x2

    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->setState(I)V

    goto :goto_0

    .line 995
    :cond_0
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->translateButton:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;

    if-eqz p2, :cond_1

    invoke-virtual {p2, v0}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->setState(I)V

    .line 996
    :cond_1
    invoke-virtual {p0}, Landroid/app/Dialog;->isShowing()Z

    move-result p2

    if-eqz p2, :cond_2

    .line 997
    iget-object p2, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->topBulletinContainer:Landroid/widget/FrameLayout;

    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {p2, p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 998
    sget p2, Lorg/telegram/messenger/R$string;->TranslationFailedAlert2:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createErrorBulletin(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 999
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    .line 1002
    :cond_2
    :goto_0
    check-cast p1, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    .line 1002
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->stopLoading()V

    goto :goto_1

    :cond_3
    return-void
.end method

.method private final openPluginFile()V
    .locals 6

    .line 892
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->installing:Z

    if-eqz v0, :cond_0

    goto :goto_1

    .line 895
    :cond_0
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getFilePath()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 896
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_1

    .line 899
    :cond_1
    invoke-static {v0}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->canOpen(Ljava/io/File;)Z

    move-result v1

    if-eqz v1, :cond_5

    .line 900
    invoke-virtual {v0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    const/4 v3, 0x2

    const/16 v4, 0x2e

    const/4 v5, 0x0

    invoke-static {v1, v4, v2, v3, v5}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;CZILjava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto :goto_0

    :cond_2
    move-object v1, v5

    :goto_0
    if-nez v1, :cond_3

    .line 901
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->plugin:Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ".plugin"

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 902
    :cond_3
    sget-object v2, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->INSTANCE:Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;

    iget-object v3, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    invoke-virtual {v2, v3, v0, v1}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->open(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/io/File;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 903
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->dismiss()V

    :cond_4
    :goto_1
    return-void

    .line 906
    :cond_5
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->fragment:Lorg/telegram/ui/ActionBar/BaseFragment;

    invoke-virtual {v0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-static {p0, v0, v1}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->openExternally(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/io/File;Ljava/lang/String;)Z

    return-void
.end method

.method private final pipRow(ILjava/lang/String;)Lorg/telegram/ui/Components/UItem;
    .locals 3

    .line 330
    invoke-static {p2}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    .line 331
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/components/PluginRequirementsView;->Companion:Lcom/exteragram/messenger/plugins/ui/components/PluginRequirementsView$Companion;

    invoke-virtual {p2, p0}, Lcom/exteragram/messenger/plugins/ui/components/PluginRequirementsView$Companion;->requirementName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 332
    invoke-virtual {p2, p0}, Lcom/exteragram/messenger/plugins/ui/components/PluginRequirementsView$Companion;->requirementUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    const/4 v1, 0x0

    if-nez p2, :cond_0

    .line 333
    invoke-static {p1, p0, v1}, Lorg/telegram/ui/Components/UItem;->asSettingsCell(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0

    .line 334
    :cond_0
    invoke-static {p0, v0}, Lkotlin/text/StringsKt;->removePrefix(Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-interface {p0}, Ljava/lang/CharSequence;->length()I

    move-result v2

    if-nez v2, :cond_1

    goto :goto_0

    :cond_1
    move-object v1, p0

    :goto_0
    invoke-static {p1, v0, v1}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    .line 335
    iput-object p2, p0, Lorg/telegram/ui/Components/UItem;->object2:Ljava/lang/Object;

    return-object p0
.end method

.method private final scrollToError()V
    .locals 1

    .line 826
    iget-object v0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/RecyclerView;->getAdapter()Landroidx/recyclerview/widget/RecyclerView$Adapter;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroidx/recyclerview/widget/RecyclerView$Adapter;->getItemCount()I

    move-result v0

    .line 827
    iget-object p0, p0, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->recyclerListView:Lorg/telegram/ui/Components/RecyclerListView;

    add-int/lit8 v0, v0, -0x1

    invoke-virtual {p0, v0}, Landroidx/recyclerview/widget/RecyclerView;->smoothScrollToPosition(I)V

    :cond_0
    return-void
.end method

.method private final showError(Ljava/lang/String;)V
    .locals 3

    .line 814
    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    .line 815
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->errorText:Ljava/lang/String;

    const/4 v1, 0x1

    if-nez v0, :cond_0

    move v0, v1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 816
    :goto_0
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->errorText:Ljava/lang/String;

    .line 817
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->errorTextView:Landroid/widget/TextView;

    if-eqz v2, :cond_1

    invoke-virtual {v2, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 819
    :cond_1
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p1, :cond_2

    invoke-virtual {p1, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_2
    if-eqz v0, :cond_3

    .line 821
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->scrollToError()V

    :cond_3
    return-void
.end method

.method private final showSimpleSuccessBulletin(Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/ui/Components/BulletinFactory;)V
    .locals 3

    .line 1175
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v0

    .line 1177
    iget-boolean p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->isUpdate:Z

    if-eqz p0, :cond_0

    sget p0, Lorg/telegram/messenger/R$string;->PluginUpdated:I

    goto :goto_0

    :cond_0
    sget p0, Lorg/telegram/messenger/R$string;->PluginInstalled:I

    .line 1178
    :goto_0
    filled-new-array {v0}, [Ljava/lang/Object;

    move-result-object v0

    .line 1176
    invoke-static {p0, v0}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    .line 1180
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v1

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz v1, :cond_1

    .line 1182
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    .line 1183
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lcom/exteragram/messenger/plugins/PluginsController;->hasPluginSettings(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/Plugin;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 1186
    sget v0, Lorg/telegram/messenger/R$raw;->contact_check:I

    .line 1188
    sget v1, Lorg/telegram/messenger/R$string;->Settings:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 1185
    new-instance v2, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda25;

    invoke-direct {v2, p1}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda25;-><init>(Lcom/exteragram/messenger/plugins/Plugin;)V

    invoke-virtual {p2, v0, p0, v1, v2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 1191
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 1193
    :cond_1
    sget p1, Lorg/telegram/messenger/R$raw;->contact_check:I

    invoke-virtual {p2, p1, p0}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method private final showSourceHint(Landroid/view/View;)V
    .locals 9

    .line 831
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->currentHint:Lorg/telegram/ui/Stories/recorder/HintView2;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lorg/telegram/ui/Stories/recorder/HintView2;->hide()V

    :cond_0
    const/4 v0, 0x0

    .line 832
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->currentHint:Lorg/telegram/ui/Stories/recorder/HintView2;

    .line 835
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->params:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getTrusted()Z

    move-result v0

    if-eqz v0, :cond_1

    sget v0, Lorg/telegram/messenger/R$string;->PluginSourceTrustedInfo:I

    goto :goto_0

    :cond_1
    sget v0, Lorg/telegram/messenger/R$string;->PluginSourceUnknownInfo:I

    :goto_0
    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 837
    new-instance v1, Lorg/telegram/ui/Stories/recorder/HintView2;

    invoke-virtual {p0}, Landroid/app/Dialog;->getContext()Landroid/content/Context;

    move-result-object v2

    const/4 v3, 0x3

    invoke-direct {v1, v2, v3}, Lorg/telegram/ui/Stories/recorder/HintView2;-><init>(Landroid/content/Context;I)V

    const/4 v2, 0x1

    .line 838
    invoke-virtual {v1, v2}, Lorg/telegram/ui/Stories/recorder/HintView2;->setMultilineText(Z)Lorg/telegram/ui/Stories/recorder/HintView2;

    move-result-object v1

    .line 839
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_undo_background:I

    invoke-virtual {p0, v3}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v3

    invoke-virtual {v1, v3}, Lorg/telegram/ui/Stories/recorder/HintView2;->setBgColor(I)Lorg/telegram/ui/Stories/recorder/HintView2;

    move-result-object v1

    .line 840
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_undo_infoColor:I

    invoke-virtual {p0, v3}, Lorg/telegram/ui/ActionBar/BottomSheet;->getThemedColor(I)I

    move-result v3

    invoke-virtual {v1, v3}, Lorg/telegram/ui/Stories/recorder/HintView2;->setTextColor(I)Lorg/telegram/ui/Stories/recorder/HintView2;

    move-result-object v1

    .line 841
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v0

    invoke-virtual {v1, v0}, Lorg/telegram/ui/Stories/recorder/HintView2;->setText(Ljava/lang/CharSequence;)Lorg/telegram/ui/Stories/recorder/HintView2;

    move-result-object v0

    .line 842
    sget-object v1, Landroid/text/Layout$Alignment;->ALIGN_CENTER:Landroid/text/Layout$Alignment;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Stories/recorder/HintView2;->setTextAlign(Landroid/text/Layout$Alignment;)Lorg/telegram/ui/Stories/recorder/HintView2;

    move-result-object v0

    .line 843
    invoke-virtual {v0, v2}, Lorg/telegram/ui/Stories/recorder/HintView2;->allowBlur(Z)Lorg/telegram/ui/Stories/recorder/HintView2;

    move-result-object v0

    const/high16 v1, 0x41400000    # 12.0f

    .line 844
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Stories/recorder/HintView2;->setRounding(F)Lorg/telegram/ui/Stories/recorder/HintView2;

    move-result-object v0

    .line 845
    invoke-virtual {v0}, Lorg/telegram/ui/Stories/recorder/HintView2;->getText()Ljava/lang/CharSequence;

    move-result-object v1

    invoke-virtual {v0}, Lorg/telegram/ui/Stories/recorder/HintView2;->getTextPaint()Landroid/text/TextPaint;

    move-result-object v2

    invoke-static {v1, v2}, Lorg/telegram/ui/Stories/recorder/HintView2;->cutInFancyHalf(Ljava/lang/CharSequence;Landroid/text/TextPaint;)I

    move-result v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Stories/recorder/HintView2;->setMaxWidthPx(I)Lorg/telegram/ui/Stories/recorder/HintView2;

    .line 847
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->container:Lorg/telegram/ui/ActionBar/BottomSheet$ContainerView;

    const/high16 v7, 0x42000000    # 32.0f

    const/4 v8, 0x0

    const/4 v2, -0x1

    const/high16 v3, 0x42c80000    # 100.0f

    const/16 v4, 0x37

    const/high16 v5, 0x42000000    # 32.0f

    const/4 v6, 0x0

    .line 849
    invoke-static/range {v2 .. v8}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v2

    .line 847
    invoke-virtual {v1, v0, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 859
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->currentHint:Lorg/telegram/ui/Stories/recorder/HintView2;

    .line 861
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->container:Lorg/telegram/ui/ActionBar/BottomSheet$ContainerView;

    new-instance v2, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda22;

    invoke-direct {v2, p1, p0, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda22;-><init>(Landroid/view/View;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Lorg/telegram/ui/Stories/recorder/HintView2;)V

    invoke-virtual {v1, v2}, Landroid/view/View;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private final showSuccessBulletin(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/Plugin;)V
    .locals 9

    .line 1110
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v6

    .line 1111
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v5

    .line 1113
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/Plugin;->getPack()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/Plugin;->getIndex()I

    move-result p1

    if-ltz p1, :cond_0

    .line 1114
    new-instance p1, Lorg/telegram/tgnet/TLRPC$TL_inputStickerSetShortName;

    invoke-direct {p1}, Lorg/telegram/tgnet/TLRPC$TL_inputStickerSetShortName;-><init>()V

    .line 1115
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/Plugin;->getPack()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p1, Lorg/telegram/tgnet/TLRPC$InputStickerSet;->short_name:Ljava/lang/String;

    .line 1118
    new-instance v1, Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v0, 0x0

    invoke-direct {v1, v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    .line 1120
    new-instance v3, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda31;

    invoke-direct {v3, v1, p0, p2, v6}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda31;-><init>(Ljava/util/concurrent/atomic/AtomicBoolean;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/ui/Components/BulletinFactory;)V

    const-wide/16 v7, 0x12c

    .line 1126
    invoke-static {v3, v7, v8}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    .line 1128
    sget v2, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v2}, Lorg/telegram/messenger/MediaDataController;->getInstance(I)Lorg/telegram/messenger/MediaDataController;

    move-result-object v7

    .line 1129
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda32;

    move-object v4, p0

    move-object v2, p2

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda32;-><init>(Ljava/util/concurrent/atomic/AtomicBoolean;Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/Runnable;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;Ljava/lang/String;Lorg/telegram/ui/Components/BulletinFactory;)V

    const/4 p0, 0x1

    invoke-virtual {v7, p1, v8, p0, v0}, Lorg/telegram/messenger/MediaDataController;->getStickerSet(Lorg/telegram/tgnet/TLRPC$InputStickerSet;Ljava/lang/Integer;ZLorg/telegram/messenger/Utilities$Callback;)Lorg/telegram/tgnet/TLRPC$TL_messages_stickerSet;

    return-void

    :cond_0
    move-object v4, p0

    move-object v2, p2

    .line 1170
    invoke-direct {v4, v2, v6}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->showSimpleSuccessBulletin(Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/ui/Components/BulletinFactory;)V

    return-void
.end method

.method private final showTexts(Z)V
    .locals 1

    .line 1006
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->nameText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->show(Z)V

    .line 1007
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->descriptionText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->show(Z)V

    .line 1008
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->nameText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    if-eqz p1, :cond_0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->getTranslation()Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_1

    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->nameText:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->getOriginal()Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->getOriginal()Ljava/lang/String;

    move-result-object p1

    :cond_1
    :goto_0
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->titleText:Ljava/lang/CharSequence;

    .line 1009
    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->updateTitle()V

    return-void
.end method

.method private final spacer(F)Lorg/telegram/ui/Components/UItem;
    .locals 0

    .line 365
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    invoke-static {p0}, Lorg/telegram/ui/Components/UItem;->asSpace(I)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    const/4 p1, 0x1

    iput-boolean p1, p0, Lorg/telegram/ui/Components/UItem;->transparent:Z

    return-object p0
.end method

.method private final translateText(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/lang/String;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 969
    new-instance p0, Lkotlin/jvm/internal/Ref$BooleanRef;

    invoke-direct {p0}, Lkotlin/jvm/internal/Ref$BooleanRef;-><init>()V

    .line 970
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda16;

    invoke-direct {v0, p0, p4}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda16;-><init>(Lkotlin/jvm/internal/Ref$BooleanRef;Lkotlin/jvm/functions/Function1;)V

    .line 978
    new-instance p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$translateText$1;

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$translateText$1;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-static {p1, p2, p3, p0}, Lcom/exteragram/messenger/translator/TranslatorUtils;->translate(Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/translator/TranslatorUtils$TranslateCallback;)V

    return-void
.end method

.method private final wheelRow(ILjava/lang/String;)Lorg/telegram/ui/Components/UItem;
    .locals 9

    .line 341
    const-string p0, ".whl"

    const/4 v0, 0x1

    invoke-static {p2, p0, v0}, Lkotlin/text/StringsKt;->endsWith(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result p0

    const/4 v1, 0x0

    if-eqz p0, :cond_0

    move-object p0, p2

    goto :goto_0

    :cond_0
    move-object p0, v1

    :goto_0
    if-eqz p0, :cond_1

    const/4 v2, 0x4

    .line 342
    invoke-static {p0, v2}, Lkotlin/text/StringsKt;->dropLast(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_1

    .line 343
    const-string p0, "-"

    filled-new-array {p0}, [Ljava/lang/String;

    move-result-object v4

    const/4 v7, 0x6

    const/4 v8, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-static/range {v3 .. v8}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object p0

    goto :goto_1

    :cond_1
    move-object p0, v1

    :goto_1
    if-eqz p0, :cond_3

    .line 344
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v2

    const/4 v3, 0x2

    if-ge v2, v3, :cond_2

    goto :goto_2

    :cond_2
    const/4 p2, 0x0

    .line 347
    invoke-interface {p0, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    move-object v1, p2

    check-cast v1, Ljava/lang/String;

    const/4 v5, 0x4

    const/4 v6, 0x0

    const/16 v2, 0x5f

    const/16 v3, 0x2d

    const/4 v4, 0x0

    invoke-static/range {v1 .. v6}, Lkotlin/text/StringsKt;->replace$default(Ljava/lang/String;CCZILjava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/CharSequence;

    invoke-static {p1, p2, p0}, Lorg/telegram/ui/Components/UItem;->asSettingsCell(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0

    .line 345
    :cond_3
    :goto_2
    invoke-static {p1, p2, v1}, Lorg/telegram/ui/Components/UItem;->asSettingsCell(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public createAdapter(Lorg/telegram/ui/Components/RecyclerListView;)Lorg/telegram/ui/Components/RecyclerListView$SelectionAdapter;
    .locals 8

    .line 275
    new-instance v0, Lorg/telegram/ui/Components/UniversalAdapter;

    .line 277
    invoke-virtual {p0}, Landroid/app/Dialog;->getContext()Landroid/content/Context;

    move-result-object v2

    .line 278
    iget v3, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->currentAccount:I

    .line 279
    invoke-virtual {p0}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->getBaseFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v1

    if-eqz v1, :cond_0

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getClassGuid()I

    move-result v1

    :goto_0
    move v4, v1

    goto :goto_1

    :cond_0
    const/4 v1, 0x0

    goto :goto_0

    .line 281
    :goto_1
    new-instance v6, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda26;

    invoke-direct {v6, p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$$ExternalSyntheticLambda26;-><init>(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V

    .line 282
    iget-object v7, p0, Lorg/telegram/ui/ActionBar/BottomSheet;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/4 v5, 0x1

    move-object v1, p1

    .line 275
    invoke-direct/range {v0 .. v7}, Lorg/telegram/ui/Components/UniversalAdapter;-><init>(Lorg/telegram/ui/Components/RecyclerListView;Landroid/content/Context;IIZLorg/telegram/messenger/Utilities$Callback2;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 283
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    return-object v0
.end method

.method public dismiss()V
    .locals 1

    .line 1198
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->currentHint:Lorg/telegram/ui/Stories/recorder/HintView2;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lorg/telegram/ui/Stories/recorder/HintView2;->hide()V

    :cond_0
    const/4 v0, 0x0

    .line 1199
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->currentHint:Lorg/telegram/ui/Stories/recorder/HintView2;

    .line 1200
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BottomSheet;->dismiss()V

    return-void
.end method

.method public getTitle()Ljava/lang/CharSequence;
    .locals 0

    .line 272
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->titleText:Ljava/lang/CharSequence;

    if-nez p0, :cond_0

    const-string p0, ""

    :cond_0
    return-object p0
.end method

.method public onPreDraw(Landroid/graphics/Canvas;IF)V
    .locals 3

    .line 373
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->actionsPill:Landroid/widget/LinearLayout;

    if-eqz p0, :cond_1

    const/high16 p1, 0x3f800000    # 1.0f

    sub-float/2addr p1, p3

    .line 375
    invoke-virtual {p0, p1}, Landroid/view/View;->setAlpha(F)V

    .line 376
    invoke-virtual {p0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result p2

    const/4 p3, 0x0

    move v0, p3

    :goto_0
    if-ge v0, p2, :cond_1

    .line 377
    invoke-virtual {p0, v0}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    const/high16 v2, 0x3f000000    # 0.5f

    cmpl-float v2, p1, v2

    if-lez v2, :cond_0

    const/4 v2, 0x1

    goto :goto_1

    :cond_0
    move v2, p3

    :goto_1
    invoke-virtual {v1, v2}, Landroid/view/View;->setClickable(Z)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method public onSwipeStarts()V
    .locals 1

    .line 1204
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->currentHint:Lorg/telegram/ui/Stories/recorder/HintView2;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lorg/telegram/ui/Stories/recorder/HintView2;->hide()V

    :cond_0
    const/4 v0, 0x0

    .line 1205
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->currentHint:Lorg/telegram/ui/Stories/recorder/HintView2;

    return-void
.end method

.method public onViewCreated(Landroid/widget/FrameLayout;)V
    .locals 0

    .line 368
    invoke-super {p0, p1}, Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;->onViewCreated(Landroid/widget/FrameLayout;)V

    .line 369
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->sheetContainer:Landroid/widget/FrameLayout;

    return-void
.end method

.method public bridge synthetic setLastVisible(Z)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment$AttachedSheet;->setLastVisible(Z)V

    return-void
.end method
