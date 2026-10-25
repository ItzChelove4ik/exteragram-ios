.class public final Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$Companion;,
        Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;,
        Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;,
        Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;,
        Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0002\u0008\u0004\n\u0002\u0010\u0008\n\u0002\u0008\u0008\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u0007\n\u0002\u0008>\u0018\u0000 y2\u00020\u0001:\u0004xyz{B\u0015\u0008\u0007\u0012\n\u0008\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\u0008\u0004\u0010\u0005J\u0018\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020&H\u0016J(\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u0018H\u0016J\u0010\u00100\u001a\u00020(2\u0006\u00101\u001a\u000202H\u0014J\u0008\u00103\u001a\u00020\u000bH\u0016J\u001e\u00104\u001a\u00020+2\u000c\u00105\u001a\u0008\u0012\u0004\u0012\u000207062\u0006\u00108\u001a\u000209H\u0014J0\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u0002072\u0006\u0010\'\u001a\u00020(2\u0006\u0010<\u001a\u00020\u00182\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>H\u0014J\u0008\u0010@\u001a\u00020+H\u0016J\u0008\u0010A\u001a\u00020+H\u0016J\u0008\u0010B\u001a\u00020+H\u0016J\u0018\u0010C\u001a\u00020+2\u0006\u0010D\u001a\u00020\u00132\u0006\u0010E\u001a\u00020\u0013H\u0016J\u0010\u0010F\u001a\u00020+2\u0006\u00101\u001a\u000202H\u0002J\u0008\u0010G\u001a\u00020+H\u0002J\u0008\u0010H\u001a\u00020+H\u0002J\u0010\u0010I\u001a\u00020+2\u0006\u0010J\u001a\u00020\u0013H\u0002J\u0010\u0010K\u001a\u00020+2\u0006\u0010L\u001a\u00020\u0018H\u0002J\u0010\u0010M\u001a\u00020\u000b2\u0006\u0010L\u001a\u00020\u0018H\u0002J\u001c\u0010N\u001a\u00020\u00182\u0008\u0010O\u001a\u0004\u0018\u00010\u000b2\u0008\u0010P\u001a\u0004\u0018\u00010\u000bH\u0002J\u0008\u0010Q\u001a\u00020\u0018H\u0002J\u0008\u0010R\u001a\u00020+H\u0002J\u0010\u0010S\u001a\u00020+2\u0006\u0010T\u001a\u00020\u0003H\u0002J\u0010\u0010U\u001a\u00020+2\u0006\u0010V\u001a\u00020\u0003H\u0002J\u0010\u0010W\u001a\u00020+2\u0006\u0010T\u001a\u00020\u0003H\u0002J\u0012\u0010X\u001a\u0004\u0018\u00010\u00032\u0006\u0010T\u001a\u00020\u0003H\u0002J\u0008\u0010Y\u001a\u00020\u0013H\u0002J\u0010\u0010Z\u001a\u00020\u00132\u0006\u0010O\u001a\u00020\u000bH\u0002J\u001c\u0010[\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\\\u001a\u00020\u00182\u0008\u0010]\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010^\u001a\u00020+2\u0008\u0010_\u001a\u0004\u0018\u00010\u000fH\u0002J\u0010\u0010`\u001a\u00020+2\u0006\u0010a\u001a\u00020\rH\u0002J\u0008\u0010b\u001a\u00020+H\u0002J\u0008\u0010c\u001a\u00020+H\u0002J\u0008\u0010d\u001a\u00020+H\u0002J\u0008\u0010e\u001a\u00020+H\u0002J\u0008\u0010f\u001a\u00020+H\u0002J\u0014\u0010g\u001a\u0004\u0018\u00010\r2\u0008\u0010h\u001a\u0004\u0018\u00010\u000bH\u0002J \u0010i\u001a\u00020\u00132\u0006\u0010O\u001a\u00020\u000b2\u0006\u0010P\u001a\u00020\u000b2\u0006\u0010j\u001a\u00020\u000bH\u0002J\u0010\u0010k\u001a\u00020\u00132\u0006\u0010h\u001a\u00020\u000bH\u0002J\u0010\u0010l\u001a\u00020\u00132\u0006\u0010h\u001a\u00020\u000bH\u0002J\u0010\u0010m\u001a\u00020\u00132\u0006\u0010h\u001a\u00020\u000bH\u0002J\u0008\u0010n\u001a\u00020+H\u0002J\u0010\u0010n\u001a\u00020+2\u0006\u0010o\u001a\u00020\u0013H\u0002J\u001a\u0010n\u001a\u00020+2\u0008\u00101\u001a\u0004\u0018\u0001022\u0006\u0010o\u001a\u00020\u0013H\u0002J\u0012\u0010p\u001a\u0004\u0018\u00010\u000b2\u0006\u00101\u001a\u000202H\u0002J\u0008\u0010q\u001a\u00020+H\u0002J\u0008\u0010r\u001a\u00020+H\u0002J\u0008\u0010s\u001a\u00020\u0013H\u0002J\u0012\u0010t\u001a\u00020\u000b2\u0008\u0010u\u001a\u0004\u0018\u00010\u000fH\u0002J\u0012\u0010v\u001a\u00020\u000b2\u0008\u0010w\u001a\u0004\u0018\u00010\u000bH\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0008\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000c\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006|"
    }
    d2 = {
        "Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "currentService",
        "Lcom/exteragram/messenger/ai/data/Service;",
        "<init>",
        "(Lcom/exteragram/messenger/ai/data/Service;)V",
        "clipChangedListener",
        "Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;",
        "clipboardManager",
        "Landroid/content/ClipboardManager;",
        "pasteString",
        "",
        "pasteInput",
        "Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;",
        "urlCell",
        "Lorg/telegram/ui/Cells/EditTextCell;",
        "modelCell",
        "keyCell",
        "hasChanges",
        "",
        "updatingFields",
        "isTesting",
        "forceCustomPreset",
        "selectedPresetIndex",
        "",
        "shiftDp",
        "keyboardInset",
        "initialUrl",
        "initialModel",
        "initialKey",
        "initialReasoningEnabled",
        "reasoningEnabled",
        "testingClient",
        "Lcom/exteragram/messenger/ai/network/Client;",
        "testingRequestId",
        "testingProgressDialog",
        "Lorg/telegram/ui/ActionBar/AlertDialog;",
        "onInsetsInternal",
        "Landroidx/core/view/WindowInsetsCompat;",
        "view",
        "Landroid/view/View;",
        "windowInsets",
        "onInsets",
        "",
        "left",
        "top",
        "right",
        "bottom",
        "createView",
        "context",
        "Landroid/content/Context;",
        "getTitle",
        "fillItems",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "position",
        "x",
        "",
        "y",
        "onResume",
        "onPause",
        "onFragmentDestroy",
        "onTransitionAnimationEnd",
        "isOpen",
        "backward",
        "createFields",
        "initializeState",
        "updateFormState",
        "setTesting",
        "testing",
        "applyPreset",
        "index",
        "getPresetTitle",
        "findPresetIndex",
        "url",
        "model",
        "getCustomPresetIndex",
        "saveConfig",
        "testAndSave",
        "service",
        "useExistingService",
        "existingService",
        "applySelection",
        "findExistingService",
        "validateFields",
        "isValidServiceUrl",
        "getFieldForError",
        "errorCode",
        "errorMessage",
        "showFieldError",
        "cell",
        "applyParsedServiceInput",
        "input",
        "showTestingProgressDialog",
        "cancelTesting",
        "stopTestingRequest",
        "hideTestingProgressDialog",
        "clearTestingState",
        "parseServiceInput",
        "text",
        "isLikelyService",
        "key",
        "isLikelyServiceUrl",
        "isLikelyModel",
        "isLikelyApiKey",
        "updateClipboardState",
        "updateList",
        "readClipboardText",
        "confirmDeleteService",
        "deleteCurrentService",
        "hasFields",
        "getFieldText",
        "field",
        "safeString",
        "value",
        "ServiceItem",
        "Companion",
        "ServicePreset",
        "ParsedServiceInput",
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
        "SMAP\nEditServiceActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EditServiceActivity.kt\ncom/exteragram/messenger/ai/ui/activities/EditServiceActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,653:1\n1915#2,2:654\n296#2,2:657\n1586#2:660\n1661#2,3:661\n777#2:664\n873#2,2:665\n1#3:656\n29#4:659\n*S KotlinDebug\n*F\n+ 1 EditServiceActivity.kt\ncom/exteragram/messenger/ai/ui/activities/EditServiceActivity\n*L\n143#1:654,2\n451#1:657,2\n544#1:660\n544#1:661,3\n544#1:664\n544#1:665,2\n471#1:659\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$Companion;

.field private static final SERVICE_PRESETS:[Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;


# instance fields
.field private final clipChangedListener:Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;

.field private clipboardManager:Landroid/content/ClipboardManager;

.field private final currentService:Lcom/exteragram/messenger/ai/data/Service;

.field private forceCustomPreset:Z

.field private hasChanges:Z

.field private initialKey:Ljava/lang/String;

.field private initialModel:Ljava/lang/String;

.field private initialReasoningEnabled:Z

.field private initialUrl:Ljava/lang/String;

.field private isTesting:Z

.field private keyCell:Lorg/telegram/ui/Cells/EditTextCell;

.field private keyboardInset:I

.field private modelCell:Lorg/telegram/ui/Cells/EditTextCell;

.field private pasteInput:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

.field private pasteString:Ljava/lang/String;

.field private reasoningEnabled:Z

.field private selectedPresetIndex:I

.field private shiftDp:I

.field private testingClient:Lcom/exteragram/messenger/ai/network/Client;

.field private testingProgressDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

.field private testingRequestId:Ljava/lang/String;

.field private updatingFields:Z

.field private urlCell:Lorg/telegram/ui/Cells/EditTextCell;


# direct methods
.method public static $r8$lambda$16DHgHjQFg621J5IV517E4afcTI(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V
    .locals 0

    .line 79
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateClipboardState()V

    return-void
.end method

.method public static $r8$lambda$1SCL0CnuTbI82uTDAeg_HX1M5zw(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Landroid/widget/TextView;ILandroid/view/KeyEvent;)Z
    .locals 0

    const/4 p1, 0x5

    if-eq p2, p1, :cond_0

    const/4 p1, 0x6

    if-eq p2, p1, :cond_0

    const/4 p0, 0x0

    return p0

    .line 268
    :cond_0
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    const/4 p2, 0x0

    if-nez p1, :cond_1

    move-object p1, p2

    :cond_1
    iget-object p1, p1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {p1}, Landroid/view/View;->requestFocus()Z

    .line 269
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p0, :cond_2

    goto :goto_0

    :cond_2
    move-object p2, p0

    :goto_0
    iget-object p0, p2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    const/4 p0, 0x1

    return p0
.end method

.method public static $r8$lambda$CdZ809pBHO3u-h3ZzxpJc0JEu18(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Z)V
    .locals 0

    .line 202
    iput-boolean p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->reasoningEnabled:Z

    return-void
.end method

.method public static $r8$lambda$LyjYmxXvUoag5N9K1FIKslL2SH0(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Landroid/content/DialogInterface;)V
    .locals 0

    .line 507
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->cancelTesting()V

    return-void
.end method

.method public static $r8$lambda$_m8vjYxg1LD6Lz_KbmGMzrOsdn8(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Landroid/widget/TextView;ILandroid/view/KeyEvent;)Z
    .locals 0

    const/4 p1, 0x6

    if-ne p2, p1, :cond_0

    .line 285
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->saveConfig()V

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static $r8$lambda$lscJkIKf7n3nnFMVVhpBpnjxbUM(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Landroid/widget/TextView;ILandroid/view/KeyEvent;)Z
    .locals 0

    const/4 p1, 0x5

    if-eq p2, p1, :cond_0

    const/4 p1, 0x6

    if-eq p2, p1, :cond_0

    const/4 p0, 0x0

    return p0

    .line 251
    :cond_0
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    const/4 p2, 0x0

    if-nez p1, :cond_1

    move-object p1, p2

    :cond_1
    iget-object p1, p1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {p1}, Landroid/view/View;->requestFocus()Z

    .line 252
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p0, :cond_2

    goto :goto_0

    :cond_2
    move-object p2, p0

    :goto_0
    iget-object p0, p2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    const/4 p0, 0x1

    return p0
.end method

.method public static $r8$lambda$rUqfvXf4VbxG9eWlgbCm0fJDEno(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 614
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->deleteCurrentService()V

    return-void
.end method

.method public static $r8$lambda$wYDNLouUWvFNTh3qP5BBmNXhyr4(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V
    .locals 4

    .line 117
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    invoke-virtual {p0}, Landroid/view/View;->findFocus()Landroid/view/View;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 118
    new-instance v0, Landroid/graphics/Rect;

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v1

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v2

    const/4 v3, 0x0

    invoke-direct {v0, v3, v3, v1, v2}, Landroid/graphics/Rect;-><init>(IIII)V

    invoke-virtual {p0, v0, v3}, Landroid/view/View;->requestRectangleOnScreen(Landroid/graphics/Rect;Z)Z

    :cond_0
    return-void
.end method

.method static constructor <clinit>()V
    .locals 8

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->Companion:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$Companion;

    .line 71
    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    const-string v2, "https://generativelanguage.googleapis.com/v1beta"

    const-string v3, "gemini-3.5-flash"

    const-string v4, "Gemini"

    invoke-direct {v0, v4, v2, v3}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 72
    new-instance v2, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    const-string v3, "https://api.openai.com/v1"

    const-string v4, "gpt-5.4-mini"

    const-string v5, "OpenAI"

    invoke-direct {v2, v5, v3, v4}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 73
    new-instance v3, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    const-string v4, "https://openrouter.ai/api/v1"

    const-string v5, "openai/gpt-5.4-mini"

    const-string v6, "OpenRouter"

    invoke-direct {v3, v6, v4, v5}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 74
    new-instance v4, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    const-string v5, "https://api.perplexity.ai/v1/agent"

    const-string v6, "perplexity/glm-5.3-flash"

    const-string v7, "Perplexity"

    invoke-direct {v4, v7, v5, v6}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 75
    new-instance v5, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    invoke-direct {v5, v1, v1, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    filled-new-array {v0, v2, v3, v4, v5}, [Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    move-result-object v0

    .line 70
    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->SERVICE_PRESETS:[Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    return-void
.end method

.method public constructor <init>()V
    .locals 2
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 0
    const/4 v0, 0x0

    const/4 v1, 0x1

    invoke-direct {p0, v0, v1, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;-><init>(Lcom/exteragram/messenger/ai/data/Service;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 0
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 53
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    .line 52
    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    .line 79
    new-instance p1, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda4;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V

    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clipChangedListener:Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;

    const/4 p1, -0x4

    .line 94
    iput p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->shiftDp:I

    return-void
.end method

.method public synthetic constructor <init>(Lcom/exteragram/messenger/ai/data/Service;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p2, p2, 0x1

    if-eqz p2, :cond_0

    const/4 p1, 0x0

    .line 51
    :cond_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;-><init>(Lcom/exteragram/messenger/ai/data/Service;)V

    return-void
.end method

.method public static final synthetic access$applySelection(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 0

    .line 51
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->applySelection(Lcom/exteragram/messenger/ai/data/Service;)V

    return-void
.end method

.method public static final synthetic access$clearTestingState(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V
    .locals 0

    .line 51
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clearTestingState()V

    return-void
.end method

.method public static final synthetic access$getCurrentService$p(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)Lcom/exteragram/messenger/ai/data/Service;
    .locals 0

    .line 51
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    return-object p0
.end method

.method public static final synthetic access$getFieldForError(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;ILjava/lang/String;)Lorg/telegram/ui/Cells/EditTextCell;
    .locals 0

    .line 51
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldForError(ILjava/lang/String;)Lorg/telegram/ui/Cells/EditTextCell;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$showFieldError(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Lorg/telegram/ui/Cells/EditTextCell;)V
    .locals 0

    .line 51
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->showFieldError(Lorg/telegram/ui/Cells/EditTextCell;)V

    return-void
.end method

.method public static final synthetic access$updateFormState(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V
    .locals 0

    .line 51
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateFormState()V

    return-void
.end method

.method private final applyParsedServiceInput(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;)V
    .locals 3

    const/4 v0, 0x1

    .line 495
    iput-boolean v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updatingFields:Z

    .line 496
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->getUrl()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_1

    move-object v0, v1

    :cond_1
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->getUrl()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    .line 497
    :cond_2
    :goto_0
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->getModel()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_5

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_3

    goto :goto_1

    :cond_3
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_4

    move-object v0, v1

    :cond_4
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->getModel()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    .line 498
    :cond_5
    :goto_1
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->getKey()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_8

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_6

    goto :goto_3

    :cond_6
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_7

    goto :goto_2

    :cond_7
    move-object v1, v0

    :goto_2
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->getKey()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    :cond_8
    :goto_3
    const/4 p1, 0x0

    .line 499
    iput-boolean p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->forceCustomPreset:Z

    .line 500
    iput-boolean p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updatingFields:Z

    .line 501
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateFormState()V

    return-void
.end method

.method private final applyPreset(I)V
    .locals 4

    .line 342
    sget-object v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->SERVICE_PRESETS:[Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    aget-object p1, v0, p1

    .line 343
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;->getUrl()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x1

    const/4 v2, 0x0

    const/4 v3, 0x0

    if-eqz v0, :cond_5

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;->getModel()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 354
    :cond_0
    iput-boolean v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->forceCustomPreset:Z

    .line 355
    iput-boolean v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updatingFields:Z

    .line 356
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_1

    move-object v0, v3

    :cond_1
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;->getUrl()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    .line 357
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_2

    move-object v0, v3

    :cond_2
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;->getModel()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    .line 358
    iput-boolean v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updatingFields:Z

    .line 359
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateFormState()V

    .line 360
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p1, :cond_3

    move-object p1, v3

    :cond_3
    iget-object p1, p1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {p1}, Landroid/view/View;->requestFocus()Z

    .line 361
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p0, :cond_4

    goto :goto_0

    :cond_4
    move-object v3, p0

    :goto_0
    iget-object p0, v3, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    return-void

    .line 344
    :cond_5
    :goto_1
    iput-boolean v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->forceCustomPreset:Z

    .line 345
    iput-boolean v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updatingFields:Z

    .line 346
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p1, :cond_6

    move-object p1, v3

    :cond_6
    const-string v0, ""

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    .line 347
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p1, :cond_7

    move-object p1, v3

    :cond_7
    invoke-virtual {p1, v0}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    .line 348
    iput-boolean v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updatingFields:Z

    .line 349
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateFormState()V

    .line 350
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p1, :cond_8

    move-object p1, v3

    :cond_8
    iget-object p1, p1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {p1}, Landroid/view/View;->requestFocus()Z

    .line 351
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p0, :cond_9

    goto :goto_2

    :cond_9
    move-object v3, p0

    :goto_2
    iget-object p0, v3, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    return-void
.end method

.method private final applySelection(Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 2

    .line 445
    invoke-static {p1}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServices(Lcom/exteragram/messenger/ai/data/Service;)V

    .line 446
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->servicesUpdated:I

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {p1, v0, v1}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 447
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->finishFragment()V

    return-void
.end method

.method private final cancelTesting()V
    .locals 0

    .line 513
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->stopTestingRequest()V

    .line 514
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clearTestingState()V

    return-void
.end method

.method private final clearTestingState()V
    .locals 1

    .line 532
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->hideTestingProgressDialog()V

    const/4 v0, 0x0

    .line 533
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->setTesting(Z)V

    const/4 v0, 0x0

    .line 534
    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingRequestId:Ljava/lang/String;

    .line 535
    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingClient:Lcom/exteragram/messenger/ai/network/Client;

    return-void
.end method

.method private final confirmDeleteService()V
    .locals 3

    .line 610
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    if-eqz v0, :cond_2

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 611
    :cond_0
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 612
    sget v1, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 613
    sget v1, Lorg/telegram/messenger/R$string;->DeleteServiceInfo:I

    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    invoke-virtual {v2}, Lcom/exteragram/messenger/ai/data/Service;->getShortModel()Ljava/lang/String;

    move-result-object v2

    filled-new-array {v2}, [Ljava/lang/Object;

    move-result-object v2

    invoke-static {v1, v2}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 614
    sget v1, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda7;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 615
    sget v1, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 616
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v0

    .line 618
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    const/4 p0, -0x1

    .line 619
    invoke-virtual {v0, p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object p0

    instance-of v0, p0, Landroid/widget/TextView;

    if-eqz v0, :cond_1

    move-object v2, p0

    check-cast v2, Landroid/widget/TextView;

    :cond_1
    if-eqz v2, :cond_2

    .line 620
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedBold:I

    invoke-static {p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p0

    invoke-virtual {v2, p0}, Landroid/widget/TextView;->setTextColor(I)V

    :cond_2
    :goto_0
    return-void
.end method

.method private final createFields(Landroid/content/Context;)V
    .locals 4

    .line 243
    sget v0, Lorg/telegram/messenger/R$string;->ServiceURL:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    new-instance v2, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$createFields$1;

    invoke-direct {v2, p1, p0, v0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$createFields$1;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Ljava/lang/String;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 246
    iget-object v0, v2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    const v1, 0x80011

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setInputType(I)V

    .line 247
    iget-object v0, v2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setRawInputType(I)V

    .line 248
    iget-object v0, v2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    const v1, 0x10000005

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setImeOptions(I)V

    .line 249
    iget-object v0, v2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    new-instance v3, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda0;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V

    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setOnEditorActionListener(Landroid/widget/TextView$OnEditorActionListener;)V

    .line 243
    iput-object v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    .line 260
    sget v0, Lorg/telegram/messenger/R$string;->ServiceModel:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    iget-object v2, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    new-instance v3, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$createFields$3;

    invoke-direct {v3, p1, p0, v0, v2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$createFields$3;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Ljava/lang/String;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 263
    iget-object v0, v3, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    const v2, 0x80001

    invoke-virtual {v0, v2}, Landroid/widget/TextView;->setInputType(I)V

    .line 264
    iget-object v0, v3, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {v0, v2}, Landroid/widget/TextView;->setRawInputType(I)V

    .line 265
    iget-object v0, v3, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setImeOptions(I)V

    .line 266
    iget-object v0, v3, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setOnEditorActionListener(Landroid/widget/TextView$OnEditorActionListener;)V

    .line 260
    iput-object v3, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    .line 277
    sget v0, Lorg/telegram/messenger/R$string;->ServiceKey:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    new-instance v2, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$createFields$5;

    invoke-direct {v2, p1, p0, v0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$createFields$5;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Ljava/lang/String;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 280
    iget-object p1, v2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    const v0, 0x80091

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setInputType(I)V

    .line 281
    iget-object p1, v2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setRawInputType(I)V

    .line 282
    iget-object p1, v2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    const v0, 0x10000006

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setImeOptions(I)V

    .line 283
    iget-object p1, v2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setOnEditorActionListener(Landroid/widget/TextView$OnEditorActionListener;)V

    .line 277
    iput-object v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    return-void
.end method

.method private final deleteCurrentService()V
    .locals 4

    .line 624
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->isSelected()Z

    move-result v0

    const/4 v2, 0x1

    if-ne v0, v2, :cond_0

    goto :goto_0

    :cond_0
    move v2, v1

    .line 625
    :goto_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    if-eqz v0, :cond_2

    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    iget-object v3, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    invoke-virtual {v0, v3}, Lcom/exteragram/messenger/ai/AiController;->removeService(Lcom/exteragram/messenger/ai/data/Service;)Z

    move-result v0

    if-eqz v0, :cond_2

    if-eqz v2, :cond_1

    .line 627
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->clearSelectedService()V

    .line 628
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/AiController;->isServicesEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    .line 629
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/AiController;->getAll()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/ai/data/Service;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServices(Lcom/exteragram/messenger/ai/data/Service;)V

    .line 631
    :cond_1
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v2, Lorg/telegram/messenger/NotificationCenter;->servicesUpdated:I

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {v0, v2, v1}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 632
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->finishFragment()V

    :cond_2
    return-void
.end method

.method private final findExistingService(Lcom/exteragram/messenger/ai/data/Service;)Lcom/exteragram/messenger/ai/data/Service;
    .locals 2

    .line 451
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->getAll()Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    .line 296
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    move-object v1, v0

    check-cast v1, Lcom/exteragram/messenger/ai/data/Service;

    .line 451
    invoke-static {v1, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 297
    :goto_0
    check-cast v0, Lcom/exteragram/messenger/ai/data/Service;

    return-object v0
.end method

.method private final findPresetIndex(Ljava/lang/String;Ljava/lang/String;)I
    .locals 4

    .line 370
    sget-object v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->SERVICE_PRESETS:[Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    array-length v0, v0

    add-int/lit8 v0, v0, -0x1

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_1

    .line 371
    sget-object v2, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->SERVICE_PRESETS:[Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    aget-object v2, v2, v1

    .line 372
    invoke-virtual {v2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;->getUrl()Ljava/lang/String;

    move-result-object v3

    invoke-static {p1, v3}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-virtual {v2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;->getModel()Ljava/lang/String;

    move-result-object v2

    invoke-static {p2, v2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_0

    return v1

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 375
    :cond_1
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getCustomPresetIndex()I

    move-result p0

    return p0
.end method

.method private final getCustomPresetIndex()I
    .locals 0

    .line 378
    sget-object p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->SERVICE_PRESETS:[Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    array-length p0, p0

    add-int/lit8 p0, p0, -0x1

    return p0
.end method

.method private final getFieldForError(ILjava/lang/String;)Lorg/telegram/ui/Cells/EditTextCell;
    .locals 3

    const/4 v0, 0x0

    packed-switch p1, :pswitch_data_0

    :pswitch_0
    return-object v0

    :pswitch_1
    if-eqz p2, :cond_1

    .line 481
    sget-object p1, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p2, p1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x0

    const/4 v1, 0x2

    const-string v2, "model"

    invoke-static {p1, v2, p2, v1, v0}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p0, :cond_0

    return-object v0

    :cond_0
    return-object p0

    :cond_1
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p0, :cond_2

    return-object v0

    :cond_2
    return-object p0

    .line 479
    :pswitch_2
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p0, :cond_3

    return-object v0

    :cond_3
    return-object p0

    .line 480
    :pswitch_3
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p0, :cond_4

    return-object v0

    :cond_4
    return-object p0

    nop

    :pswitch_data_0
    .packed-switch 0x190
        :pswitch_3
        :pswitch_2
        :pswitch_0
        :pswitch_2
        :pswitch_1
    .end packed-switch
.end method

.method private final getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;
    .locals 0

    if-eqz p1, :cond_1

    .line 639
    invoke-virtual {p1}, Lorg/telegram/ui/Cells/EditTextCell;->getText()Ljava/lang/CharSequence;

    move-result-object p0

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    if-eqz p0, :cond_1

    invoke-static {p0}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    return-object p0

    :cond_1
    :goto_0
    const-string p0, ""

    return-object p0
.end method

.method private final getPresetTitle(I)Ljava/lang/String;
    .locals 0

    .line 365
    sget-object p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->SERVICE_PRESETS:[Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    aget-object p0, p0, p1

    .line 366
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;->getName()Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_0

    sget p0, Lorg/telegram/messenger/R$string;->ServiceProviderCustom:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    :cond_0
    return-object p0
.end method

.method private final hasFields()Z
    .locals 1

    .line 637
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final hideTestingProgressDialog()V
    .locals 1

    .line 523
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingProgressDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    if-eqz v0, :cond_0

    .line 525
    :try_start_0
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    :cond_0
    const/4 v0, 0x0

    .line 528
    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingProgressDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    return-void
.end method

.method private final initializeState()V
    .locals 3

    .line 295
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    if-nez v0, :cond_0

    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->DEFAULT_SERVICE:Lcom/exteragram/messenger/ai/data/Service;

    .line 296
    :cond_0
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->getUrl()Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialUrl:Ljava/lang/String;

    .line 297
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->getModel()Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialModel:Ljava/lang/String;

    .line 298
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->getKey()Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialKey:Ljava/lang/String;

    .line 299
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->isReasoningEnabled()Z

    move-result v0

    iput-boolean v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialReasoningEnabled:Z

    .line 301
    iput-boolean v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->reasoningEnabled:Z

    .line 302
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    const/4 v1, 0x0

    if-nez v0, :cond_1

    move-object v0, v1

    :cond_1
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialUrl:Ljava/lang/String;

    invoke-direct {p0, v2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->safeString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    .line 303
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_2

    move-object v0, v1

    :cond_2
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialModel:Ljava/lang/String;

    invoke-direct {p0, v2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->safeString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    .line 304
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_3

    goto :goto_0

    :cond_3
    move-object v1, v0

    :goto_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialKey:Ljava/lang/String;

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->safeString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lorg/telegram/ui/Cells/EditTextCell;->setText(Ljava/lang/CharSequence;)V

    const/4 v0, 0x0

    .line 305
    iput-boolean v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->hasChanges:Z

    .line 306
    iget-object v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialUrl:Ljava/lang/String;

    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialModel:Ljava/lang/String;

    invoke-direct {p0, v1, v2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->findPresetIndex(Ljava/lang/String;Ljava/lang/String;)I

    move-result v1

    iput v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->selectedPresetIndex:I

    .line 307
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getCustomPresetIndex()I

    move-result v2

    if-ne v1, v2, :cond_4

    const/4 v0, 0x1

    :cond_4
    iput-boolean v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->forceCustomPreset:Z

    return-void
.end method

.method private final isLikelyApiKey(Ljava/lang/String;)Z
    .locals 1

    .line 568
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p0

    if-lez p0, :cond_0

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p0

    const/16 v0, 0x14

    if-lt p0, v0, :cond_0

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p0

    const/16 v0, 0x200

    if-gt p0, v0, :cond_0

    new-instance p0, Lkotlin/text/Regex;

    const-string v0, "[A-Za-z0-9_.\\-]+"

    invoke-direct {p0, v0}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p1}, Lkotlin/text/Regex;->matches(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final isLikelyModel(Ljava/lang/String;)Z
    .locals 1

    .line 565
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p0

    if-lez p0, :cond_0

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p0

    const/16 v0, 0x80

    if-gt p0, v0, :cond_0

    new-instance p0, Lkotlin/text/Regex;

    const-string v0, ".*\\s+.*"

    invoke-direct {p0, v0}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p1}, Lkotlin/text/Regex;->matches(Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final isLikelyService(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    .locals 0

    .line 559
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isLikelyServiceUrl(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-direct {p0, p2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isLikelyModel(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-direct {p0, p3}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isLikelyApiKey(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final isLikelyServiceUrl(Ljava/lang/String;)Z
    .locals 3

    .line 562
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p0

    const/4 v0, 0x0

    if-lez p0, :cond_1

    const-string p0, "https://"

    const/4 v1, 0x2

    const/4 v2, 0x0

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_0

    const-string p0, "http://"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    :cond_0
    const/4 p0, 0x1

    return p0

    :cond_1
    return v0
.end method

.method private final isValidServiceUrl(Ljava/lang/String;)Z
    .locals 3

    .line 470
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p0

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    .line 29
    :cond_0
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p0

    .line 472
    invoke-virtual {p0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object p1

    .line 473
    const-string v1, "http"

    const/4 v2, 0x1

    invoke-static {v1, p1, v2}, Lkotlin/text/StringsKt;->equals(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result v1

    if-nez v1, :cond_1

    const-string v1, "https"

    invoke-static {v1, p1, v2}, Lkotlin/text/StringsKt;->equals(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 474
    :cond_1
    invoke-virtual {p0}, Landroid/net/Uri;->getHost()Ljava/lang/String;

    move-result-object p0

    if-eqz p0, :cond_3

    invoke-interface {p0}, Ljava/lang/CharSequence;->length()I

    move-result p0

    if-nez p0, :cond_2

    goto :goto_0

    :cond_2
    return v2

    :cond_3
    :goto_0
    return v0
.end method

.method private final parseServiceInput(Ljava/lang/String;)Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;
    .locals 10

    const/4 v0, 0x0

    if-eqz p1, :cond_8

    .line 539
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_0

    goto/16 :goto_3

    .line 540
    :cond_0
    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    .line 541
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_1

    return-object v0

    .line 543
    :cond_1
    new-instance v1, Lkotlin/text/Regex;

    const-string v2, "\\r?\\n"

    invoke-direct {v1, v2}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    const/4 v2, 0x0

    invoke-virtual {v1, p1, v2}, Lkotlin/text/Regex;->split(Ljava/lang/CharSequence;I)Ljava/util/List;

    move-result-object v1

    .line 544
    check-cast v1, Ljava/lang/Iterable;

    .line 1586
    new-instance v3, Ljava/util/ArrayList;

    const/16 v4, 0xa

    invoke-static {v1, v4}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v4

    invoke-direct {v3, v4}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    .line 1662
    check-cast v4, Ljava/lang/String;

    .line 544
    invoke-static {v4}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v4

    .line 1662
    invoke-interface {v3, v4}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 777
    :cond_2
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v4

    move v5, v2

    :cond_3
    :goto_1
    if-ge v5, v4, :cond_4

    invoke-virtual {v3, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    move-object v7, v6

    check-cast v7, Ljava/lang/String;

    .line 544
    invoke-interface {v7}, Ljava/lang/CharSequence;->length()I

    move-result v7

    if-lez v7, :cond_3

    .line 873
    invoke-interface {v1, v6}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 546
    :cond_4
    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v3

    const/4 v4, 0x3

    const/4 v5, 0x1

    if-lt v3, v4, :cond_5

    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-interface {v1, v5}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    const/4 v7, 0x2

    invoke-interface {v1, v7}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/lang/String;

    invoke-direct {p0, v3, v6, v8}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isLikelyService(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_5

    .line 547
    new-instance p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    invoke-interface {v1, v5}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-interface {v1, v7}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-direct {p0, p1, v0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-object p0

    .line 549
    :cond_5
    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v3

    sub-int/2addr v3, v4

    if-ltz v3, :cond_7

    .line 550
    :goto_2
    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    add-int/lit8 v6, v2, 0x1

    invoke-interface {v1, v6}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    add-int/lit8 v8, v2, 0x2

    invoke-interface {v1, v8}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/lang/String;

    invoke-direct {p0, v4, v7, v9}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isLikelyService(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_6

    .line 551
    new-instance p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    invoke-interface {v1, v6}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-interface {v1, v8}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-direct {p0, p1, v0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-object p0

    :cond_6
    if-eq v2, v3, :cond_7

    move v2, v6

    goto :goto_2

    .line 553
    :cond_7
    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    if-ne v1, v5, :cond_8

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isLikelyApiKey(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_8

    .line 554
    new-instance p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    invoke-direct {p0, v0, v0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-object p0

    :cond_8
    :goto_3
    return-object v0
.end method

.method private final readClipboardText(Landroid/content/Context;)Ljava/lang/String;
    .locals 2

    .line 599
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clipboardManager:Landroid/content/ClipboardManager;

    const/4 v0, 0x0

    if-eqz p0, :cond_2

    invoke-virtual {p0}, Landroid/content/ClipboardManager;->getPrimaryClip()Landroid/content/ClipData;

    move-result-object p0

    if-nez p0, :cond_0

    goto :goto_0

    .line 600
    :cond_0
    invoke-virtual {p0}, Landroid/content/ClipData;->getItemCount()I

    move-result v1

    if-gtz v1, :cond_1

    return-object v0

    :cond_1
    const/4 v1, 0x0

    .line 602
    :try_start_0
    invoke-virtual {p0, v1}, Landroid/content/ClipData;->getItemAt(I)Landroid/content/ClipData$Item;

    move-result-object p0

    invoke-virtual {p0, p1}, Landroid/content/ClipData$Item;->coerceToText(Landroid/content/Context;)Ljava/lang/CharSequence;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 603
    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    :cond_2
    :goto_0
    return-object v0
.end method

.method private final safeString(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    if-nez p1, :cond_0

    .line 641
    const-string p0, ""

    return-object p0

    :cond_0
    return-object p1
.end method

.method private final saveConfig()V
    .locals 5

    .line 381
    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isTesting:Z

    if-nez v0, :cond_8

    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->validateFields()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_2

    .line 383
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/ai/data/Service;

    iget-object v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    move-object v1, v2

    :cond_1
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v1

    iget-object v3, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v3, :cond_2

    move-object v3, v2

    :cond_2
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v3

    iget-object v4, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v4, :cond_3

    goto :goto_0

    :cond_3
    move-object v2, v4

    :goto_0
    invoke-direct {p0, v2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v2

    iget-boolean v4, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->reasoningEnabled:Z

    invoke-direct {v0, v1, v3, v2, v4}, Lcom/exteragram/messenger/ai/data/Service;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V

    .line 384
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->findExistingService(Lcom/exteragram/messenger/ai/data/Service;)Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v1

    .line 385
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    if-eqz v2, :cond_4

    invoke-virtual {v2}, Lcom/exteragram/messenger/ai/data/Service;->isReasoningEnabled()Z

    move-result v2

    iget-boolean v3, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->reasoningEnabled:Z

    if-eq v2, v3, :cond_4

    const/4 v2, 0x1

    goto :goto_1

    :cond_4
    const/4 v2, 0x0

    :goto_1
    if-eqz v1, :cond_6

    .line 387
    iget-object v3, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_6

    .line 388
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    if-eqz v0, :cond_5

    .line 389
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget v0, Lorg/telegram/messenger/R$string;->ServiceDuplicate:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/BulletinFactory;->createErrorBulletin(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 392
    :cond_5
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->useExistingService(Lcom/exteragram/messenger/ai/data/Service;)V

    return-void

    :cond_6
    if-eqz v1, :cond_7

    if-nez v2, :cond_7

    .line 396
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->applySelection(Lcom/exteragram/messenger/ai/data/Service;)V

    return-void

    .line 400
    :cond_7
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testAndSave(Lcom/exteragram/messenger/ai/data/Service;)V

    :cond_8
    :goto_2
    return-void
.end method

.method private final setTesting(Z)V
    .locals 0

    .line 337
    iput-boolean p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isTesting:Z

    .line 338
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_0

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
    return-void
.end method

.method private final showFieldError(Lorg/telegram/ui/Cells/EditTextCell;)V
    .locals 1

    if-nez p1, :cond_0

    return-void

    .line 487
    :cond_0
    sget-object v0, Lorg/telegram/messenger/BotWebViewVibrationEffect;->APP_ERROR:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    invoke-virtual {v0}, Lorg/telegram/messenger/BotWebViewVibrationEffect;->vibrate()V

    .line 488
    iget v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->shiftDp:I

    neg-int v0, v0

    iput v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->shiftDp:I

    int-to-float p0, v0

    .line 489
    invoke-static {p1, p0}, Lorg/telegram/messenger/AndroidUtilities;->shakeViewSpring(Landroid/view/View;F)V

    .line 490
    iget-object p0, p1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {p0}, Landroid/view/View;->requestFocus()Z

    .line 491
    iget-object p0, p1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    return-void
.end method

.method private final showTestingProgressDialog()V
    .locals 3

    .line 505
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    .line 506
    :cond_0
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    const/4 v2, 0x3

    invoke-direct {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog;-><init>(Landroid/content/Context;I)V

    .line 507
    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda5;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)V

    .line 506
    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingProgressDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    .line 509
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method

.method private final stopTestingRequest()V
    .locals 1

    .line 518
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingClient:Lcom/exteragram/messenger/ai/network/Client;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingRequestId:Ljava/lang/String;

    if-eqz v0, :cond_1

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 519
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingClient:Lcom/exteragram/messenger/ai/network/Client;

    if-eqz v0, :cond_1

    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingRequestId:Ljava/lang/String;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/ai/network/Client;->stopRequest(Ljava/lang/String;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private final testAndSave(Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 2

    .line 404
    new-instance v0, Lcom/exteragram/messenger/ai/network/Client$Builder;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/network/Client$Builder;-><init>()V

    .line 405
    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/ai/network/Client$Builder;->serviceOverride(Lcom/exteragram/messenger/ai/data/Service;)Lcom/exteragram/messenger/ai/network/Client$Builder;

    .line 406
    sget-object v1, Lcom/exteragram/messenger/ai/data/Suggestions;->ASSISTANT:Lcom/exteragram/messenger/ai/data/Suggestions;

    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Suggestions;->getRole()Lcom/exteragram/messenger/ai/data/Role;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/ai/network/Client$Builder;->roleOverride(Lcom/exteragram/messenger/ai/data/Role;)Lcom/exteragram/messenger/ai/network/Client$Builder;

    .line 407
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/network/Client$Builder;->build()Lcom/exteragram/messenger/ai/network/Client;

    move-result-object v0

    .line 404
    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingClient:Lcom/exteragram/messenger/ai/network/Client;

    .line 408
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->showTestingProgressDialog()V

    const/4 v0, 0x1

    .line 409
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->setTesting(Z)V

    .line 410
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingClient:Lcom/exteragram/messenger/ai/network/Client;

    if-eqz v0, :cond_0

    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Lcom/exteragram/messenger/ai/data/Service;)V

    const-string p1, "Say \'hi\'."

    invoke-virtual {v0, p1, v1}, Lcom/exteragram/messenger/ai/network/Client;->getResponse(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingRequestId:Ljava/lang/String;

    return-void
.end method

.method private final updateClipboardState()V
    .locals 1

    const/4 v0, 0x1

    .line 570
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateClipboardState(Z)V

    return-void
.end method

.method private final updateClipboardState(Landroid/content/Context;Z)V
    .locals 9

    .line 576
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clipboardManager:Landroid/content/ClipboardManager;

    if-eqz v0, :cond_a

    if-eqz p1, :cond_a

    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->hasFields()Z

    move-result v0

    if-nez v0, :cond_0

    goto/16 :goto_4

    .line 578
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteInput:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    move v3, v2

    goto :goto_0

    :cond_1
    move v3, v1

    :goto_0
    if-eqz v0, :cond_2

    .line 579
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->hasServiceFields()Z

    move-result v0

    if-ne v0, v2, :cond_2

    move v0, v2

    goto :goto_1

    :cond_2
    move v0, v1

    .line 581
    :goto_1
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->readClipboardText(Landroid/content/Context;)Ljava/lang/String;

    move-result-object p1

    .line 582
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->parseServiceInput(Ljava/lang/String;)Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    move-result-object v4

    const/4 v5, 0x0

    if-eqz v4, :cond_6

    .line 583
    iget-object v6, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v6, :cond_3

    move-object v6, v5

    :cond_3
    invoke-direct {p0, v6}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v6

    iget-object v7, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v7, :cond_4

    move-object v7, v5

    :cond_4
    invoke-direct {p0, v7}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v7

    iget-object v8, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v8, :cond_5

    move-object v8, v5

    :cond_5
    invoke-direct {p0, v8}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v4, v6, v7, v8}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->differsFrom(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_6

    .line 584
    iput-object v4, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteInput:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    .line 585
    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteString:Ljava/lang/String;

    goto :goto_2

    .line 587
    :cond_6
    iput-object v5, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteInput:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    .line 588
    iput-object v5, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteString:Ljava/lang/String;

    .line 591
    :goto_2
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteInput:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    if-eqz p1, :cond_7

    move v4, v2

    goto :goto_3

    :cond_7
    move v4, v1

    :goto_3
    if-eqz p1, :cond_8

    .line 592
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->hasServiceFields()Z

    move-result p1

    if-ne p1, v2, :cond_8

    move v1, v2

    :cond_8
    if-eqz p2, :cond_a

    .line 593
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz p1, :cond_a

    if-ne v3, v4, :cond_9

    if-eq v0, v1, :cond_a

    .line 594
    :cond_9
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_a

    invoke-virtual {p0, v2}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_a
    :goto_4
    return-void
.end method

.method private final updateClipboardState(Z)V
    .locals 1

    .line 573
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    if-nez v0, :cond_1

    :cond_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    :cond_1
    invoke-direct {p0, v0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateClipboardState(Landroid/content/Context;Z)V

    return-void
.end method

.method private final updateFormState()V
    .locals 9

    .line 311
    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updatingFields:Z

    if-nez v0, :cond_e

    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->hasFields()Z

    move-result v0

    if-nez v0, :cond_0

    goto/16 :goto_6

    .line 313
    :cond_0
    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->hasChanges:Z

    .line 314
    iget v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->selectedPresetIndex:I

    .line 315
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteInput:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-eqz v2, :cond_1

    move v5, v4

    goto :goto_0

    :cond_1
    move v5, v3

    :goto_0
    if-eqz v2, :cond_2

    .line 316
    invoke-virtual {v2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->hasServiceFields()Z

    move-result v2

    if-ne v2, v4, :cond_2

    move v2, v4

    goto :goto_1

    :cond_2
    move v2, v3

    .line 318
    :goto_1
    iget-object v6, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    const/4 v7, 0x0

    if-nez v6, :cond_3

    move-object v6, v7

    :cond_3
    invoke-direct {p0, v6}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v6

    iget-object v8, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialUrl:Ljava/lang/String;

    invoke-direct {p0, v8}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->safeString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-static {v6, v8}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_7

    .line 319
    iget-object v6, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v6, :cond_4

    move-object v6, v7

    :cond_4
    invoke-direct {p0, v6}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v6

    iget-object v8, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialModel:Ljava/lang/String;

    invoke-direct {p0, v8}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->safeString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-static {v6, v8}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_7

    .line 320
    iget-object v6, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v6, :cond_5

    move-object v6, v7

    :cond_5
    invoke-direct {p0, v6}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v6

    iget-object v8, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialKey:Ljava/lang/String;

    invoke-direct {p0, v8}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->safeString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-static {v6, v8}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_7

    .line 321
    iget-boolean v6, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->reasoningEnabled:Z

    iget-boolean v8, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initialReasoningEnabled:Z

    if-eq v6, v8, :cond_6

    goto :goto_2

    :cond_6
    move v6, v3

    goto :goto_3

    :cond_7
    :goto_2
    move v6, v4

    .line 318
    :goto_3
    iput-boolean v6, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->hasChanges:Z

    .line 322
    iget-object v6, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v6, :cond_8

    move-object v6, v7

    :cond_8
    invoke-direct {p0, v6}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v6

    iget-object v8, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v8, :cond_9

    goto :goto_4

    :cond_9
    move-object v7, v8

    :goto_4
    invoke-direct {p0, v7}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v7

    invoke-direct {p0, v6, v7}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->findPresetIndex(Ljava/lang/String;Ljava/lang/String;)I

    move-result v6

    .line 323
    iget-boolean v7, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->forceCustomPreset:Z

    if-eqz v7, :cond_a

    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getCustomPresetIndex()I

    move-result v6

    :cond_a
    iput v6, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->selectedPresetIndex:I

    .line 324
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateClipboardState(Z)V

    .line 326
    iget-object v6, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteInput:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    if-eqz v6, :cond_b

    move v7, v4

    goto :goto_5

    :cond_b
    move v7, v3

    :goto_5
    if-eqz v6, :cond_c

    .line 327
    invoke-virtual {v6}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->hasServiceFields()Z

    move-result v6

    if-ne v6, v4, :cond_c

    move v3, v4

    .line 328
    :cond_c
    iget-object v6, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz v6, :cond_e

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v6

    iget-object v6, v6, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz v6, :cond_e

    iget-boolean v6, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->hasChanges:Z

    if-ne v0, v6, :cond_d

    .line 329
    iget v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->selectedPresetIndex:I

    if-ne v1, v0, :cond_d

    if-ne v5, v7, :cond_d

    if-eq v2, v3, :cond_e

    .line 332
    :cond_d
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    invoke-virtual {p0, v4}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_e
    :goto_6
    return-void
.end method

.method private final useExistingService(Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 2

    .line 432
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Service;->isReasoningEnabled()Z

    move-result v0

    iget-boolean v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->reasoningEnabled:Z

    if-eq v0, v1, :cond_0

    .line 433
    invoke-virtual {p1, v1}, Lcom/exteragram/messenger/ai/data/Service;->setReasoningEnabled(Z)V

    .line 434
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/AiController;->saveServices()V

    .line 436
    :cond_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->applySelection(Lcom/exteragram/messenger/ai/data/Service;)V

    .line 437
    invoke-static {}, Lorg/telegram/ui/Components/BulletinFactory;->global()Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 438
    sget p1, Lorg/telegram/messenger/R$raw;->chats_infotip:I

    .line 439
    sget v0, Lorg/telegram/messenger/R$string;->ServiceAlreadyExists:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 440
    sget v1, Lorg/telegram/messenger/R$string;->ServiceAlreadyExistsInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 437
    invoke-virtual {p0, p1, v0, v1}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 441
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method private final validateFields()Z
    .locals 3

    .line 454
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    move-object v0, v1

    :cond_0
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isValidServiceUrl(Ljava/lang/String;)Z

    move-result v0

    const/4 v2, 0x0

    if-nez v0, :cond_2

    .line 455
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_1

    goto :goto_0

    :cond_1
    move-object v1, v0

    :goto_0
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->showFieldError(Lorg/telegram/ui/Cells/EditTextCell;)V

    return v2

    .line 458
    :cond_2
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_3

    move-object v0, v1

    :cond_3
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v0

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_5

    .line 459
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_4

    goto :goto_1

    :cond_4
    move-object v1, v0

    :goto_1
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->showFieldError(Lorg/telegram/ui/Cells/EditTextCell;)V

    return v2

    .line 462
    :cond_5
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_6

    move-object v0, v1

    :cond_6
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getFieldText(Lorg/telegram/ui/Cells/EditTextCell;)Ljava/lang/String;

    move-result-object v0

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_8

    .line 463
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v0, :cond_7

    goto :goto_2

    :cond_7
    move-object v1, v0

    :goto_2
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->showFieldError(Lorg/telegram/ui/Cells/EditTextCell;)V

    return v2

    :cond_8
    const/4 p0, 0x1

    return p0
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 2

    .line 125
    const-string v0, "clipboard"

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    instance-of v1, v0, Landroid/content/ClipboardManager;

    if-eqz v1, :cond_0

    check-cast v0, Landroid/content/ClipboardManager;

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clipboardManager:Landroid/content/ClipboardManager;

    .line 126
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->createFields(Landroid/content/Context;)V

    .line 127
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->initializeState()V

    const/4 v0, 0x0

    .line 128
    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateClipboardState(Landroid/content/Context;Z)V

    .line 130
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object p1

    .line 131
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/ActionBar;->setAllowOverlayTitle(Z)V

    return-object p1
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

    move-object/from16 v14, p1

    .line 140
    const-string v1, "AI-Service-Reasoning"

    invoke-static {v1}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->markAsNewFeature(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    sget v1, Lorg/telegram/messenger/R$string;->AIReasoning:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->applyNewSpan(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v1

    :goto_0
    move-object v2, v1

    goto :goto_1

    :cond_0
    sget v1, Lorg/telegram/messenger/R$string;->AIReasoning:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    goto :goto_0

    .line 142
    :goto_1
    sget v1, Lorg/telegram/messenger/R$string;->ServiceProvider:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 143
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->SERVICE_PRESETS:[Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServicePreset;

    invoke-static {v1}, Lkotlin/collections/ArraysKt;->getIndices([Ljava/lang/Object;)Lkotlin/ranges/IntRange;

    move-result-object v1

    .line 1915
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_2
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    const/4 v12, 0x0

    const/4 v15, 0x1

    if-eqz v3, :cond_6

    move-object v3, v1

    check-cast v3, Lkotlin/collections/IntIterator;

    invoke-virtual {v3}, Lkotlin/collections/IntIterator;->nextInt()I

    move-result v3

    if-eqz v3, :cond_4

    if-eq v3, v15, :cond_3

    const/4 v4, 0x2

    if-eq v3, v4, :cond_2

    const/4 v4, 0x3

    if-eq v3, v4, :cond_1

    .line 149
    sget-object v4, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->PRESET_CUSTOM:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->getId()I

    move-result v4

    goto :goto_3

    .line 148
    :cond_1
    sget-object v4, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->PRESET_PERPLEXITY:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->getId()I

    move-result v4

    goto :goto_3

    .line 147
    :cond_2
    sget-object v4, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->PRESET_OPENROUTER:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->getId()I

    move-result v4

    goto :goto_3

    .line 146
    :cond_3
    sget-object v4, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->PRESET_OPENAI:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->getId()I

    move-result v4

    goto :goto_3

    .line 145
    :cond_4
    sget-object v4, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->PRESET_GEMINI:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->getId()I

    move-result v4

    .line 151
    :goto_3
    invoke-direct {v0, v3}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->getPresetTitle(I)Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Lorg/telegram/ui/Components/UItem;->asRadio(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v4

    iget v5, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->selectedPresetIndex:I

    if-ne v3, v5, :cond_5

    move v12, v15

    :cond_5
    invoke-virtual {v4, v12}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object v3

    iget-boolean v4, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isTesting:Z

    xor-int/2addr v4, v15

    invoke-virtual {v3, v4}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object v3

    invoke-virtual {v14, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_2

    .line 153
    :cond_6
    sget v1, Lorg/telegram/messenger/R$string;->ServicesInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 155
    sget v1, Lorg/telegram/messenger/R$string;->ServiceInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 156
    iget-object v1, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->urlCell:Lorg/telegram/ui/Cells/EditTextCell;

    const/4 v3, 0x0

    if-nez v1, :cond_7

    move-object v1, v3

    :cond_7
    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asCustom(Landroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 157
    iget-object v1, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->modelCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v1, :cond_8

    move-object v1, v3

    :cond_8
    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asCustom(Landroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 158
    iget-object v1, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez v1, :cond_9

    goto :goto_4

    :cond_9
    move-object v3, v1

    :goto_4
    invoke-static {v3}, Lorg/telegram/ui/Components/UItem;->asCustom(Landroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 159
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->REASONING:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;

    iget-boolean v3, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->reasoningEnabled:Z

    sget v4, Lorg/telegram/messenger/R$string;->AIReasoningInfo:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x1d0

    const/4 v11, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 160
    iget-boolean v2, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isTesting:Z

    xor-int/2addr v2, v15

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    .line 159
    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 162
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 165
    iget-object v1, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteInput:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    if-eqz v1, :cond_b

    move-object v2, v1

    .line 167
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->PASTE:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;

    invoke-virtual {v2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;->hasServiceFields()Z

    move-result v2

    if-eqz v2, :cond_a

    sget v2, Lorg/telegram/messenger/R$string;->ServicePasteService:I

    goto :goto_5

    :cond_a
    sget v2, Lorg/telegram/messenger/R$string;->ServicePasteKey:I

    :goto_5
    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_copy:I

    const/16 v12, 0x5b8

    const/4 v13, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x1

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 168
    iget-boolean v2, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isTesting:Z

    xor-int/2addr v2, v15

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    .line 167
    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move v12, v15

    .line 171
    :cond_b
    iget-boolean v1, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->hasChanges:Z

    if-eqz v1, :cond_c

    .line 173
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->SAVE:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;

    sget v2, Lorg/telegram/messenger/R$string;->ServiceTestAndSave:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->ic_ab_done:I

    const/16 v12, 0x5b8

    const/4 v13, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x1

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 174
    iget-boolean v2, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isTesting:Z

    xor-int/2addr v2, v15

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    .line 173
    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move v12, v15

    .line 177
    :cond_c
    iget-object v1, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    if-eqz v1, :cond_d

    .line 179
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->DELETE:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;

    sget v2, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_delete:I

    const/16 v12, 0x6b8

    const/4 v13, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x1

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 180
    iget-boolean v0, v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isTesting:Z

    xor-int/2addr v0, v15

    invoke-virtual {v1, v0}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    .line 179
    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_6

    :cond_d
    move v15, v12

    .line 183
    :goto_6
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-static {v14, v15, v0}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 137
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->currentService:Lcom/exteragram/messenger/ai/data/Service;

    if-eqz p0, :cond_0

    sget p0, Lorg/telegram/messenger/R$string;->EditService:I

    goto :goto_0

    :cond_0
    sget p0, Lorg/telegram/messenger/R$string;->NewService:I

    :goto_0
    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 187
    iget-boolean p2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->isTesting:Z

    if-nez p2, :cond_2

    iget p2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-lez p2, :cond_2

    invoke-static {}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p3

    invoke-interface {p3}, Ljava/util/List;->size()I

    move-result p3

    if-le p2, p3, :cond_0

    goto :goto_0

    .line 188
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p2

    iget p3, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 p4, 0x1

    sub-int/2addr p3, p4

    invoke-interface {p2, p3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ServiceItem;

    .line 189
    sget-object p3, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    aget p2, p3, p2

    packed-switch p2, :pswitch_data_0

    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 202
    :pswitch_0
    new-instance p2, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda6;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 203
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateFormState()V

    return-void

    .line 200
    :pswitch_1
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->confirmDeleteService()V

    return-void

    .line 199
    :pswitch_2
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->saveConfig()V

    return-void

    .line 196
    :pswitch_3
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteInput:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    if-nez p1, :cond_1

    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->pasteString:Ljava/lang/String;

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->parseServiceInput(Ljava/lang/String;)Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;

    move-result-object p1

    :cond_1
    if-eqz p1, :cond_2

    .line 197
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->applyParsedServiceInput(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$ParsedServiceInput;)V

    return-void

    :pswitch_4
    const/4 p1, 0x4

    .line 194
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->applyPreset(I)V

    return-void

    :pswitch_5
    const/4 p1, 0x3

    .line 193
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->applyPreset(I)V

    return-void

    :pswitch_6
    const/4 p1, 0x2

    .line 192
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->applyPreset(I)V

    return-void

    .line 191
    :pswitch_7
    invoke-direct {p0, p4}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->applyPreset(I)V

    return-void

    :pswitch_8
    const/4 p1, 0x0

    .line 190
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->applyPreset(I)V

    :cond_2
    :goto_0
    return-void

    :pswitch_data_0
    .packed-switch 0x1
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

.method public onFragmentDestroy()V
    .locals 1

    .line 229
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->stopTestingRequest()V

    .line 230
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->hideTestingProgressDialog()V

    const/4 v0, 0x0

    .line 231
    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingRequestId:Ljava/lang/String;

    .line 232
    iput-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testingClient:Lcom/exteragram/messenger/ai/network/Client;

    .line 233
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    return-void
.end method

.method public onInsets(IIII)V
    .locals 1

    .line 114
    iget v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyboardInset:I

    add-int/2addr p4, v0

    invoke-super {p0, p1, p2, p3, p4}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onInsets(IIII)V

    .line 115
    iget p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyboardInset:I

    if-lez p1, :cond_0

    .line 116
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    new-instance p2, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda3;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V

    invoke-virtual {p1, p2}, Landroid/view/View;->post(Ljava/lang/Runnable;)Z

    :cond_0
    return-void
.end method

.method public onInsetsInternal(Landroid/view/View;Landroidx/core/view/WindowInsetsCompat;)Landroidx/core/view/WindowInsetsCompat;
    .locals 2

    .line 108
    invoke-static {}, Landroidx/core/view/WindowInsetsCompat$Type;->navigationBars()I

    move-result v0

    invoke-static {}, Landroidx/core/view/WindowInsetsCompat$Type;->statusBars()I

    move-result v1

    or-int/2addr v0, v1

    invoke-virtual {p2, v0}, Landroidx/core/view/WindowInsetsCompat;->getInsets(I)Landroidx/core/graphics/Insets;

    move-result-object v0

    .line 109
    invoke-static {}, Landroidx/core/view/WindowInsetsCompat$Type;->ime()I

    move-result v1

    invoke-virtual {p2, v1}, Landroidx/core/view/WindowInsetsCompat;->getInsets(I)Landroidx/core/graphics/Insets;

    move-result-object v1

    iget v1, v1, Landroidx/core/graphics/Insets;->bottom:I

    iget v0, v0, Landroidx/core/graphics/Insets;->bottom:I

    sub-int/2addr v1, v0

    const/4 v0, 0x0

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyboardInset:I

    .line 110
    invoke-super {p0, p1, p2}, Lorg/telegram/ui/ActionBar/BaseFragment;->onInsetsInternal(Landroid/view/View;Landroidx/core/view/WindowInsetsCompat;)Landroidx/core/view/WindowInsetsCompat;

    move-result-object p0

    return-object p0
.end method

.method public onPause()V
    .locals 2

    .line 224
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clipboardManager:Landroid/content/ClipboardManager;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clipChangedListener:Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;

    invoke-virtual {v0, v1}, Landroid/content/ClipboardManager;->removePrimaryClipChangedListener(Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;)V

    .line 225
    :cond_0
    invoke-super {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onPause()V

    return-void
.end method

.method public onResume()V
    .locals 3

    .line 209
    invoke-super {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onResume()V

    .line 210
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clipboardManager:Landroid/content/ClipboardManager;

    if-eqz v0, :cond_0

    .line 211
    iget-object v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clipChangedListener:Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;

    invoke-virtual {v0, v1}, Landroid/content/ClipboardManager;->removePrimaryClipChangedListener(Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;)V

    .line 212
    iget-object v1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->clipChangedListener:Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;

    invoke-virtual {v0, v1}, Landroid/content/ClipboardManager;->addPrimaryClipChangedListener(Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;)V

    .line 214
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->updateClipboardState()V

    .line 216
    invoke-static {}, Lorg/telegram/messenger/MessagesController;->getGlobalMainSettings()Landroid/content/SharedPreferences;

    move-result-object v0

    .line 217
    const-string v1, "view_animations"

    const/4 v2, 0x1

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-nez v0, :cond_3

    .line 218
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    const/4 v1, 0x0

    if-nez v0, :cond_1

    move-object v0, v1

    :cond_1
    iget-object v0, v0, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {v0}, Landroid/view/View;->requestFocus()Z

    .line 219
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p0, :cond_2

    goto :goto_0

    :cond_2
    move-object v1, p0

    :goto_0
    iget-object p0, v1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    :cond_3
    return-void
.end method

.method public onTransitionAnimationEnd(ZZ)V
    .locals 0

    if-nez p1, :cond_0

    return-void

    .line 238
    :cond_0
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    const/4 p2, 0x0

    if-nez p1, :cond_1

    move-object p1, p2

    :cond_1
    iget-object p1, p1, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-virtual {p1}, Landroid/view/View;->requestFocus()Z

    .line 239
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->keyCell:Lorg/telegram/ui/Cells/EditTextCell;

    if-nez p0, :cond_2

    goto :goto_0

    :cond_2
    move-object p2, p0

    :goto_0
    iget-object p0, p2, Lorg/telegram/ui/Cells/EditTextCell;->editText:Lorg/telegram/ui/Components/EditTextCaption;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->showKeyboard(Landroid/view/View;)Z

    return-void
.end method
