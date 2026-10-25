.class public final Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;,
        Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$Companion;,
        Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\u0008\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0008\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0007\n\u0002\u0008\u0004\n\u0002\u0010\u000b\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0006\u0018\u0000 E2\u00020\u0001:\u0002EFB\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\"\u001a\u00020#H\u0014J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'H\u0014J\u0008\u0010(\u001a\u00020)H\u0016J\u0008\u0010*\u001a\u00020\u0006H\u0002J\u001e\u0010+\u001a\u00020#2\u000c\u0010,\u001a\u0008\u0012\u0004\u0012\u00020.0-2\u0006\u0010/\u001a\u000200H\u0014J0\u00101\u001a\u00020#2\u0006\u00102\u001a\u00020.2\u0006\u00103\u001a\u00020%2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000207H\u0014J\u0008\u00109\u001a\u00020#H\u0002J\u0012\u0010:\u001a\u0004\u0018\u00010\u00142\u0006\u0010;\u001a\u00020<H\u0002J\u0008\u0010=\u001a\u00020<H\u0002J\u0010\u0010>\u001a\u00020#2\u0006\u00102\u001a\u00020.H\u0002J\u0008\u0010D\u001a\u00020#H\u0002R\u0016\u0010\u0004\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\u0008\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\t\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\n\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\u000b\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\u000c\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\r\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\u000e\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010?\u001a\u0008\u0012\u0004\u0012\u00020A0@8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\u0008B\u0010C\u00a8\u0006G"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "stickerTimeModes",
        "",
        "",
        "[Ljava/lang/CharSequence;",
        "doubleTapActions",
        "doubleTapOutActions",
        "bottomButtonOptions",
        "videoMessagesCameraOptions",
        "doubleTapSeekDurationOptions",
        "cameraTypeOptions",
        "recognitionLanguageOptions",
        "resetItem",
        "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;",
        "stickerSizeCell",
        "Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;",
        "doubleTapCell",
        "Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;",
        "stickerShapeCell",
        "Lcom/exteragram/messenger/preferences/chats/components/StickerShapeCell;",
        "messagesPreviewCell",
        "Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;",
        "replyElements",
        "Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "hideReactions",
        "widePosts",
        "aiFeatures",
        "quickTransitions",
        "messageMenu",
        "cameraSettings",
        "pauseOnMinimize",
        "initializeOptionStrings",
        "",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "getTitle",
        "",
        "swipeActionsValue",
        "fillItems",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "position",
        "",
        "x",
        "",
        "y",
        "updateReplySettings",
        "handleDoubleTapActionButtonClick",
        "outOwner",
        "",
        "isSeamlessSwitchingAvailable",
        "handleSpeechRecognitionLanguageClick",
        "downloadedRecognitionModels",
        "",
        "Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;",
        "getDownloadedRecognitionModels",
        "()Ljava/util/List;",
        "handleDeleteRecognitionModelClick",
        "Companion",
        "ChatsItem",
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
        "SMAP\nChatsPreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChatsPreferencesActivity.kt\ncom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,568:1\n1586#2:569\n1661#2,3:570\n2792#2,3:599\n296#2,2:602\n37#3,2:573\n41#4,12:575\n41#4,12:587\n*S KotlinDebug\n*F\n+ 1 ChatsPreferencesActivity.kt\ncom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity\n*L\n259#1:569\n259#1:570,3\n546#1:599,3\n549#1:602,2\n261#1:573,2\n401#1:575,12\n212#1:587,12\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$Companion;

.field private static final languageCodes:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final aiFeatures:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private bottomButtonOptions:[Ljava/lang/CharSequence;

.field private final cameraSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private cameraTypeOptions:[Ljava/lang/CharSequence;

.field private doubleTapActions:[Ljava/lang/CharSequence;

.field private doubleTapCell:Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;

.field private doubleTapOutActions:[Ljava/lang/CharSequence;

.field private doubleTapSeekDurationOptions:[Ljava/lang/CharSequence;

.field private final hideReactions:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private final messageMenu:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private messagesPreviewCell:Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;

.field private final pauseOnMinimize:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private final quickTransitions:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private recognitionLanguageOptions:[Ljava/lang/CharSequence;

.field private final replyElements:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private resetItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

.field private stickerShapeCell:Lcom/exteragram/messenger/preferences/chats/components/StickerShapeCell;

.field private stickerSizeCell:Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

.field private stickerTimeModes:[Ljava/lang/CharSequence;

.field private videoMessagesCameraOptions:[Ljava/lang/CharSequence;

.field private final widePosts:Lcom/exteragram/messenger/preferences/SwitchGroup;


# direct methods
.method public static $r8$lambda$3bAEwhc4N0xTYjnM3bIRxyA-8OI()Z
    .locals 2

    .line 218
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCameraType()Lcom/exteragram/messenger/CameraType;

    move-result-object v0

    sget-object v1, Lcom/exteragram/messenger/CameraType;->CAMERA_2:Lcom/exteragram/messenger/CameraType;

    if-eq v0, v1, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public static $r8$lambda$4KvpqlaYRwOjBXeSe628W1EL_Hc(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;Lorg/telegram/ui/Components/UItem;I)V
    .locals 3

    .line 534
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getRecognitionLanguage()Ljava/lang/String;

    move-result-object v0

    .line 535
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->languageCodes:Ljava/util/List;

    invoke-interface {v1, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    .line 536
    invoke-static {v0, p2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_2

    .line 538
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda44;

    invoke-direct {v0, p2, p0, p1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda44;-><init>(Ljava/lang/String;Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;Lorg/telegram/ui/Components/UItem;)V

    .line 546
    const-string p1, "none"

    invoke-static {p2, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_7

    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->getDownloadedRecognitionModels()Ljava/util/List;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 2792
    instance-of v1, p1, Ljava/util/Collection;

    if-eqz v1, :cond_1

    move-object v1, p1

    check-cast v1, Ljava/util/Collection;

    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_0

    .line 2793
    :cond_1
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_2
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;

    .line 546
    invoke-virtual {v1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, p2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    goto :goto_3

    .line 547
    :cond_3
    :goto_0
    invoke-static {}, Lcom/exteragram/messenger/speech/VoiceRecognitionController;->getInstance()Lcom/exteragram/messenger/speech/VoiceRecognitionController;

    move-result-object p1

    .line 548
    const-string v1, "vosk"

    invoke-virtual {p1, v1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController;->listAvailableModels(Ljava/lang/String;)Ljava/util/List;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 296
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_4
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_5

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;

    .line 549
    invoke-virtual {v2}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2, p2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_4

    goto :goto_1

    :cond_5
    const/4 v1, 0x0

    :goto_1
    check-cast v1, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;

    if-nez v1, :cond_6

    :goto_2
    return-void

    .line 552
    :cond_6
    invoke-static {p0, p2, v1, v0}, Lcom/exteragram/messenger/speech/ui/RecognitionModelDialogs;->showDownloadDialog(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/String;Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;Ljava/lang/Runnable;)V

    return-void

    .line 554
    :cond_7
    :goto_3
    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    return-void
.end method

.method public static $r8$lambda$84uyN27AjsN4umQceiXh1BS5wFQ(Z)V
    .locals 0

    .line 464
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setInlineMathResult(Z)V

    return-void
.end method

.method public static $r8$lambda$DhYH9Loy50Ur_v1N0SzJ-KvEr-w(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)Lkotlin/Unit;
    .locals 1

    .line 175
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->isTablet()Z

    move-result v0

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$F-wjjc_fyoE9NwsY91GTOwPnJXA(Z)V
    .locals 0

    .line 462
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHideKeyboardOnScroll(Z)V

    return-void
.end method

.method public static $r8$lambda$FWitcGe3P3jEExkXLxXfYkSVZ4s(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;I)V
    .locals 1

    .line 429
    invoke-static {}, Lcom/exteragram/messenger/StickerTimeMode;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v0

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/StickerTimeMode;

    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setStickerTimeMode(Lcom/exteragram/messenger/StickerTimeMode;)V

    .line 430
    iget-object p1, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerSizeCell:Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;->invalidate()V

    .line 431
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 p1, 0x0

    invoke-interface {p0, p1}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method public static $r8$lambda$FaZY3S7n_AQyadXG2RJ9uhDMbLM()Z
    .locals 2

    .line 219
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCameraType()Lcom/exteragram/messenger/CameraType;

    move-result-object v0

    sget-object v1, Lcom/exteragram/messenger/CameraType;->CAMERA_X:Lcom/exteragram/messenger/CameraType;

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public static $r8$lambda$IBjc4AxXgFeekyR4FCGvh48KrBM(Z)V
    .locals 0

    .line 506
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHideCameraTile(Z)V

    return-void
.end method

.method public static $r8$lambda$ITAZA42M2h_IgK5X0rWrMWGRPTw(Z)V
    .locals 0

    .line 504
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setStaticZoom(Z)V

    return-void
.end method

.method public static $r8$lambda$JUuCcMDTxW8ABK84fpnnPE2XeIY(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;I)V
    .locals 0

    .line 448
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setDoubleTapActionOutOwner(I)V

    const/4 p1, 0x1

    .line 449
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->handleDoubleTapActionButtonClick(Z)Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;

    return-void
.end method

.method public static $r8$lambda$Jt8nFFlJ2sJgv3YcBe3mz0E9m84(Z)V
    .locals 0

    .line 471
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setShowOnlineStatus(Z)V

    return-void
.end method

.method public static $r8$lambda$K6yOQX4Iotc1La7JOQr7CkKi4RE(I)V
    .locals 1

    .line 498
    invoke-static {}, Lcom/exteragram/messenger/CameraType;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/CameraType;

    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setCameraType(Lcom/exteragram/messenger/CameraType;)V

    return-void
.end method

.method public static $r8$lambda$KS8V5vv1M6-Glb4i2Hv8jWz-QKs(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;Landroid/view/View;)V
    .locals 3

    const/4 v0, 0x0

    const/high16 v1, 0x3f000000    # 0.5f

    const/4 v2, 0x1

    .line 300
    invoke-static {p1, v0, v1, v2}, Lorg/telegram/messenger/AndroidUtilities;->updateViewVisibilityAnimated(Landroid/view/View;ZFZ)V

    .line 301
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getStickerSize()F

    move-result p1

    const/4 v1, 0x2

    new-array v1, v1, [F

    aput p1, v1, v0

    const/high16 p1, 0x41400000    # 12.0f

    aput p1, v1, v2

    invoke-static {v1}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object p1

    const-wide/16 v0, 0xc8

    .line 302
    invoke-virtual {p1, v0, v1}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 303
    new-instance v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda11;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda11;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    invoke-virtual {p1, v0}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 309
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->start()V

    return-void
.end method

.method public static $r8$lambda$KWHRbL1zWGfxJYpycook0LEXYMk(Z)V
    .locals 0

    .line 461
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setDisableGreetingSticker(Z)V

    return-void
.end method

.method public static $r8$lambda$RVDr4oiCTKYRnls7MiRc08oPCs4(Z)V
    .locals 0

    .line 463
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setAddCommaAfterMention(Z)V

    return-void
.end method

.method public static $r8$lambda$SIECvH1woGtCA1yKWk5reWi9U30(Z)V
    .locals 2

    .line 212
    invoke-static {}, Lorg/telegram/messenger/MessagesController;->getGlobalMainSettings()Landroid/content/SharedPreferences;

    move-result-object v0

    .line 45
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 213
    const-string v1, "rounddual_available"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static $r8$lambda$Sf9ddikJ8W132h_hhZTZGz2P0lc(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;Landroid/animation/ValueAnimator;)V
    .locals 1

    .line 304
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    .line 305
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setStickerSize(F)V

    .line 306
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerSizeCell:Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

    if-eqz v0, :cond_0

    iget-object v0, v0, Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;->seekBar:Lcom/exteragram/messenger/preferences/components/AltSeekbar;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/components/AltSeekbar;->setProgress(F)V

    .line 307
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerSizeCell:Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;->invalidate()V

    :cond_1
    return-void
.end method

.method public static $r8$lambda$X3U9hE5HrzHECcL8uiPCLxnqhKQ()Z
    .locals 1

    .line 203
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->canUseAI()Z

    move-result v0

    return v0
.end method

.method public static $r8$lambda$YOefROm89BQul7bk9OuWlbZeuIY(Z)V
    .locals 0

    .line 509
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setSwipeToPip(Z)V

    return-void
.end method

.method public static $r8$lambda$YgkHDV87EEG0n9Tj5_Fjka7kHfE(I)V
    .locals 0

    .line 507
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setDoubleTapSeekDuration(I)V

    return-void
.end method

.method public static $r8$lambda$YmwtL7Jnn4wPbPnHNiL-8CRFfn8(Z)V
    .locals 0

    .line 505
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setAlwaysSendInHD(Z)V

    return-void
.end method

.method public static $r8$lambda$Z6TQ1sAjTbJhiJ9r9nzpIdA6Di4(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;)V
    .locals 1

    .line 564
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getRecognitionLanguage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const-string p1, "none"

    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setRecognitionLanguage(Ljava/lang/String;)V

    .line 565
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    return-void
.end method

.method public static $r8$lambda$Zyay2o4VkHEizG1V7GsE0WSPGh4(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;F)V
    .locals 1

    .line 276
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setStickerSize(F)V

    .line 277
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->resetItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    if-eqz p0, :cond_0

    .line 278
    invoke-virtual {p0}, Landroid/view/View;->getVisibility()I

    move-result p1

    if-eqz p1, :cond_0

    const/high16 p1, 0x3f000000    # 0.5f

    const/4 v0, 0x1

    .line 279
    invoke-static {p0, v0, p1, v0}, Lorg/telegram/messenger/AndroidUtilities;->updateViewVisibilityAnimated(Landroid/view/View;ZFZ)V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$aTj7CImeY3w9zA5fuudmfkAIdbA(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)Lkotlin/Unit;
    .locals 1

    .line 196
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 v0, 0x0

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$aa1yxM530VFz38JNCenQ6qK8Bvw(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)Lkotlin/Unit;
    .locals 1

    .line 182
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 v0, 0x0

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$akHl3KMik9FeBwEWS8MBWmnzpGA(I)V
    .locals 1

    .line 501
    invoke-static {}, Lcom/exteragram/messenger/VideoMessagesCamera;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/VideoMessagesCamera;

    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setVideoMessagesCamera(Lcom/exteragram/messenger/VideoMessagesCamera;)V

    return-void
.end method

.method public static $r8$lambda$cefdfm2DsK_hsuAL4lE-7qv2ngo(Ljava/lang/String;Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;Lorg/telegram/ui/Components/UItem;)V
    .locals 2

    .line 539
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setRecognitionLanguage(Ljava/lang/String;)V

    .line 540
    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    iget p2, p2, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-virtual {v0, p2}, Lorg/telegram/ui/Components/UniversalRecyclerView;->findViewByItemId(I)Landroid/view/View;

    move-result-object p2

    .line 541
    instance-of v0, p2, Lorg/telegram/ui/Cells/TextCell;

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    .line 542
    check-cast p2, Lorg/telegram/ui/Cells/TextCell;

    invoke-static {p0}, Lcom/exteragram/messenger/translator/TranslatorUtils;->getLanguageTitleSystem(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p2, p0, v1}, Lorg/telegram/ui/Cells/TextCell;->setValue(Ljava/lang/CharSequence;Z)V

    .line 543
    :cond_0
    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    invoke-virtual {p0, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    return-void
.end method

.method public static $r8$lambda$cwgJ6NDBTXZo7m4A2flYIMZg0d8(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)Z
    .locals 0

    .line 211
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/ui/Stories/recorder/DualCameraView;->roundDualAvailableStatic(Landroid/content/Context;)Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$cwwkBzNQ_86D_YBCMcm07TVXMxc(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)Z
    .locals 0

    .line 210
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->isSeamlessSwitchingAvailable()Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$e5Kk4sGPAnA2M99NVlDFUs_0ORk(Z)V
    .locals 0

    .line 490
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHideShareButton(Z)V

    return-void
.end method

.method public static $r8$lambda$e5yImsZcbNNudRLUTsJ96kZ_7uU(Z)V
    .locals 0

    .line 459
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setQuickAdminShortcuts(Z)V

    return-void
.end method

.method public static $r8$lambda$hF42LPDlT6bkWXq6lqrQM9LyN0k(Z)V
    .locals 0

    .line 496
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setPostprocessingWithAi(Z)V

    return-void
.end method

.method public static $r8$lambda$i6-s6ekwNSnZB1-jtZpy38OQM0U(Z)V
    .locals 0

    .line 467
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setReplaceEditedWithIcon(Z)V

    return-void
.end method

.method public static $r8$lambda$jQK7uxuHuR8vfwDoLjdXtOPVSGQ(Z)V
    .locals 0

    .line 475
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setShowForwardsCount(Z)V

    return-void
.end method

.method public static $r8$lambda$mvxO-JVz3LNxAun88Vdg-UhdHUg(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;I)V
    .locals 0

    .line 454
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setBottomButton(I)V

    .line 455
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 p1, 0x0

    invoke-interface {p0, p1}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method public static $r8$lambda$n_JNEUv_iWTJTwOshwVLxai8B9A(Z)V
    .locals 0

    .line 510
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setUnmuteWithVolumeButtons(Z)V

    return-void
.end method

.method public static $r8$lambda$oohFinv130Q8KjzojqiDzlgwZUQ(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;I)V
    .locals 0

    .line 442
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setDoubleTapAction(I)V

    const/4 p1, 0x0

    .line 443
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->handleDoubleTapActionButtonClick(Z)Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;

    return-void
.end method

.method public static $r8$lambda$pt-mrvuvGas_kvhbEd0CpPWJ-68(Z)V
    .locals 0

    .line 508
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setPreferOriginalQuality(Z)V

    return-void
.end method

.method public static $r8$lambda$rR-MO8U8uc7_oFt32JaMqyj-xMo(Z)V
    .locals 0

    .line 465
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHideSendAsPeer(Z)V

    return-void
.end method

.method public static $r8$lambda$t1rKI3d162q2kgs4LDbxto5laeg(Z)V
    .locals 0

    .line 503
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setZoomSlider(Z)V

    return-void
.end method

.method public static $r8$lambda$tvEZwVOqKFvPNLRgA8qtPQuHALY(Z)V
    .locals 0

    .line 494
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setGroupMessageMenu(Z)V

    return-void
.end method

.method public static $r8$lambda$wB9LWbf3JBoM3xg7yrDn07WEQe0(Z)V
    .locals 0

    .line 437
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setUnlimitedRecentStickers(Z)V

    return-void
.end method

.method public static $r8$lambda$x2TCZYohYSAJWFK2_jyE3RNzTBo(Z)V
    .locals 0

    .line 480
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setRemoveMessageTail(Z)V

    return-void
.end method

.method public static $r8$lambda$zJvc4yMuQVZnix3o-g2FSjex8BM(Z)V
    .locals 0

    .line 486
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setShowResultsBeforeVoting(Z)V

    return-void
.end method

.method public static $r8$lambda$zMv4m2tabIIQBo2Yx_P3u61iU1c(Z)V
    .locals 0

    .line 502
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setRememberLastUsedCamera(Z)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 27

    new-instance v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->Companion:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$Companion;

    .line 58
    const-string v25, "tg"

    const-string v26, "ca"

    const-string v2, "none"

    const-string v3, "en"

    const-string v4, "es"

    const-string v5, "zh"

    const-string v6, "hi"

    const-string v7, "fa"

    const-string v8, "fr"

    const-string v9, "ru"

    const-string v10, "pt"

    const-string v11, "de"

    const-string v12, "ja"

    const-string v13, "ko"

    const-string v14, "it"

    const-string v15, "uk"

    const-string v16, "gu"

    const-string v17, "pl"

    const-string v18, "nl"

    const-string v19, "tr"

    const-string v20, "vi"

    const-string v21, "cs"

    const-string v22, "uz"

    const-string v23, "eo"

    const-string v24, "kk"

    filled-new-array/range {v2 .. v26}, [Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->listOf([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->languageCodes:Ljava/util/List;

    return-void
.end method

.method public constructor <init>()V
    .locals 11

    .line 54
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    .line 158
    new-instance v0, Lcom/exteragram/messenger/preferences/SwitchGroup;

    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->REPLY_ELEMENTS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->RepliesTitle:I

    const/16 v7, 0x28

    const/4 v8, 0x0

    const/4 v4, 0x0

    const-string v5, "replyElements"

    const/4 v6, 0x0

    move-object v1, p0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 159
    new-instance v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$replyElements$1$1;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$replyElements$1$1;-><init>(Ljava/lang/Object;)V

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onChanged(Lkotlin/jvm/functions/Function0;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 160
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->REPLY_COLORS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->BackgroundColors:I

    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$replyElements$1$2;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$replyElements$1$2;

    const/16 v5, 0x8

    const/4 v6, 0x0

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 161
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->REPLY_EMOJI:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->Emoji:I

    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$replyElements$1$3;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$replyElements$1$3;

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 162
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->REPLY_BACKGROUND:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->ReplyBackground:I

    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$replyElements$1$4;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$replyElements$1$4;

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 158
    iput-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->replyElements:Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 166
    new-instance v1, Lcom/exteragram/messenger/preferences/SwitchGroup;

    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->HIDE_REACTIONS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->HideReactions:I

    const-string v5, "hideReactions"

    const/4 v6, 0x0

    move-object v0, v1

    move-object v1, p0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v1, v0

    .line 167
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$hideReactions$1$1;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$hideReactions$1$1;-><init>(Ljava/lang/Object;)V

    invoke-virtual {v1, v2}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onChanged(Lkotlin/jvm/functions/Function0;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 168
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->CHANNELS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->ChannelsTab:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$hideReactions$1$2;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$hideReactions$1$2;

    const/16 v6, 0x8

    const/4 v7, 0x0

    const/4 v5, 0x0

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 169
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->GROUPS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->SaveToGalleryGroups:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$hideReactions$1$3;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$hideReactions$1$3;

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 170
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->PRIVATE_CHATS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->PrivateChats:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$hideReactions$1$4;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$hideReactions$1$4;

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 166
    iput-object v1, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->hideReactions:Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 174
    new-instance v2, Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-object v0, v2

    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->WIDE_POSTS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->WidePosts:I

    const/16 v7, 0x28

    const/4 v4, 0x0

    const-string v5, "widePosts"

    const/4 v6, 0x0

    move-object v1, p0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 175
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda2;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    invoke-virtual {v0, v2}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onChanged(Lkotlin/jvm/functions/Function0;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 176
    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->WIDE_POSTS_FEED:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v4, Lorg/telegram/messenger/R$string;->Feed:I

    sget-object v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$widePosts$1$2;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$widePosts$1$2;

    const/16 v7, 0x8

    const/4 v6, 0x0

    move-object v2, v0

    invoke-static/range {v2 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 177
    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->WIDE_POSTS_CHANNELS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v4, Lorg/telegram/messenger/R$string;->ChannelsTab:I

    sget-object v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$widePosts$1$3;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$widePosts$1$3;

    invoke-static/range {v2 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 174
    iput-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->widePosts:Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 181
    new-instance v0, Lcom/exteragram/messenger/preferences/SwitchGroup;

    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->AI_FEATURES:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->AIFeatures:I

    const/16 v7, 0x28

    const/4 v4, 0x0

    const-string v5, "aiFeatures"

    const/4 v6, 0x0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v1, v0

    .line 182
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda3;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    invoke-virtual {v1, v2}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onChanged(Lkotlin/jvm/functions/Function0;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 183
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->AI_FEATURES_EDITOR:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->AIFeaturesEditor:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$aiFeatures$1$2;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$aiFeatures$1$2;

    const/16 v6, 0x8

    const/4 v7, 0x0

    const/4 v5, 0x0

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 184
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->AI_FEATURES_SUMMARIES:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->AIFeaturesSummaries:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$aiFeatures$1$3;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$aiFeatures$1$3;

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 185
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->AI_FEATURES_INSTANT_VIEW_SUMMARIES:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->AIFeaturesInstantViewSummaries:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$aiFeatures$1$4;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$aiFeatures$1$4;

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 181
    iput-object v1, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->aiFeatures:Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 189
    new-instance v2, Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-object v0, v2

    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->QUICK_TRANSITIONS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->QuickTransitions:I

    const/16 v7, 0x28

    const/4 v4, 0x0

    const-string v5, "quickTransitions"

    const/4 v6, 0x0

    move-object v1, p0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 190
    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->QUICK_TRANSITION_FOR_CHANNELS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v4, Lorg/telegram/messenger/R$string;->FilterChannels:I

    sget-object v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$quickTransitions$1$1;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$quickTransitions$1$1;

    const/16 v7, 0x8

    const/4 v6, 0x0

    move-object v2, v0

    invoke-static/range {v2 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 191
    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->QUICK_TRANSITION_FOR_TOPICS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v4, Lorg/telegram/messenger/R$string;->Topics:I

    sget-object v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$quickTransitions$1$2;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$quickTransitions$1$2;

    invoke-static/range {v2 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 189
    iput-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->quickTransitions:Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 195
    new-instance v0, Lcom/exteragram/messenger/preferences/SwitchGroup;

    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->MESSAGE_MENU:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->MessageMenu:I

    const/16 v7, 0x28

    const/4 v4, 0x0

    const-string v5, "messageMenu"

    const/4 v6, 0x0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v1, v0

    .line 196
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda4;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    invoke-virtual {v1, v2}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onChanged(Lkotlin/jvm/functions/Function0;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 197
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->COPY_PHOTO:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->CopyPhoto:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$2;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$2;

    const/16 v6, 0x8

    const/4 v7, 0x0

    const/4 v5, 0x0

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 198
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->SAVE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->Save:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$3;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$3;

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 199
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->REPEAT:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->Repeat:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$4;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$4;

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 200
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->CLEAR:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->Clear:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$5;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$5;

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 201
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->HISTORY:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->MessageHistory:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$6;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$6;

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 202
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->REPORT:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->ReportChat:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$7;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$7;

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 203
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda5;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda5;-><init>()V

    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->GENERATE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v4, Lorg/telegram/messenger/R$string;->Generate:I

    sget-object v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$9;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$9;

    const/16 v7, 0x10

    const/4 v6, 0x0

    invoke-static/range {v1 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->addIf$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 204
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->DETAILS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->Details:I

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$10;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$messageMenu$1$10;

    const/16 v6, 0x8

    const/4 v7, 0x0

    const/4 v5, 0x0

    invoke-static/range {v1 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 195
    iput-object v1, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->messageMenu:Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 208
    new-instance v2, Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-object v0, v2

    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->CAMERA_SETTINGS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v3, Lorg/telegram/messenger/R$string;->ExtendedSettings:I

    const/16 v7, 0x28

    const/4 v4, 0x0

    const-string v5, "cameraSettings"

    const/4 v6, 0x0

    move-object v1, p0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v2, v0

    .line 209
    new-instance v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda6;

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    .line 210
    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->DUAL_CAMERA:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v5, Lorg/telegram/messenger/R$string;->SeamlessSwitching:I

    .line 209
    new-instance v6, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda7;

    invoke-direct {v6, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    .line 212
    new-instance v7, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda8;

    invoke-direct {v7}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda8;-><init>()V

    const/16 v9, 0x20

    const/4 v10, 0x0

    .line 209
    invoke-static/range {v2 .. v10}, Lcom/exteragram/messenger/preferences/SwitchGroup;->addIf$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 216
    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->EXTENDED_FRAMES_PER_SECOND:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v4, Lorg/telegram/messenger/R$string;->ExtendedFramesPerSecond:I

    sget-object v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$cameraSettings$1$4;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$cameraSettings$1$4;

    const/16 v7, 0x8

    const/4 v6, 0x0

    invoke-static/range {v2 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 217
    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->CAMERA_STABILIZATION:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v4, Lorg/telegram/messenger/R$string;->CameraStabilization:I

    sget-object v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$cameraSettings$1$5;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$cameraSettings$1$5;

    invoke-static/range {v2 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 218
    new-instance v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda9;

    invoke-direct {v3}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda9;-><init>()V

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->CAMERA_MIRROR_MODE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v5, Lorg/telegram/messenger/R$string;->CameraMirrorMode:I

    sget-object v6, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$cameraSettings$1$7;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$cameraSettings$1$7;

    const/16 v8, 0x10

    const/4 v9, 0x0

    const/4 v7, 0x0

    invoke-static/range {v2 .. v9}, Lcom/exteragram/messenger/preferences/SwitchGroup;->addIf$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 219
    new-instance v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda10;

    invoke-direct {v3}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda10;-><init>()V

    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->START_WITH_WIDE_ANGLE_CAMERA:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v5, Lorg/telegram/messenger/R$string;->StartWithWideAngleCamera:I

    sget-object v6, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$cameraSettings$1$9;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$cameraSettings$1$9;

    invoke-static/range {v2 .. v9}, Lcom/exteragram/messenger/preferences/SwitchGroup;->addIf$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 220
    const-string v0, "Camera-ExtendedSettings-StartWithWideAngle"

    invoke-virtual {v2, v0}, Lcom/exteragram/messenger/preferences/SwitchGroup;->markNew(Ljava/lang/String;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 208
    iput-object v2, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->cameraSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 224
    new-instance v3, Lcom/exteragram/messenger/preferences/SwitchGroup;

    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->PAUSE_ON_MINIMIZE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    move-object v0, v3

    sget v3, Lorg/telegram/messenger/R$string;->PauseOnMinimize:I

    const/16 v7, 0x28

    const/4 v8, 0x0

    const/4 v4, 0x0

    const-string v5, "pauseOnMinimize"

    const/4 v6, 0x0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v3, v0

    .line 225
    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->PAUSE_ON_MINIMIZE_VIDEO:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v5, Lorg/telegram/messenger/R$string;->PauseOnMinimizeVideo:I

    sget-object v6, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$pauseOnMinimize$1$1;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$pauseOnMinimize$1$1;

    const/16 v8, 0x8

    const/4 v7, 0x0

    invoke-static/range {v3 .. v9}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 226
    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->PAUSE_ON_MINIMIZE_VOICE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v5, Lorg/telegram/messenger/R$string;->PauseOnMinimizeVoice:I

    sget-object v6, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$pauseOnMinimize$1$2;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$pauseOnMinimize$1$2;

    invoke-static/range {v3 .. v9}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 227
    sget-object v4, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->PAUSE_ON_MINIMIZE_ROUND:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v5, Lorg/telegram/messenger/R$string;->PauseOnMinimizeRound:I

    sget-object v6, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$pauseOnMinimize$1$3;->INSTANCE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$pauseOnMinimize$1$3;

    invoke-static/range {v3 .. v9}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 224
    iput-object v3, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->pauseOnMinimize:Lcom/exteragram/messenger/preferences/SwitchGroup;

    return-void
.end method

.method public static final synthetic access$getParentLayout$p$s-146837972(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)Lorg/telegram/ui/ActionBar/INavigationLayout;
    .locals 0

    .line 54
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    return-object p0
.end method

.method public static final synthetic access$getStickerSizeCell$p(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;
    .locals 0

    .line 54
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerSizeCell:Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

    return-object p0
.end method

.method public static final synthetic access$updateReplySettings(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V
    .locals 0

    .line 54
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->updateReplySettings()V

    return-void
.end method

.method private final getDownloadedRecognitionModels()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;",
            ">;"
        }
    .end annotation

    .line 560
    invoke-static {}, Lcom/exteragram/messenger/speech/VoiceRecognitionController;->getInstance()Lcom/exteragram/messenger/speech/VoiceRecognitionController;

    move-result-object p0

    const-string v0, "vosk"

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/speech/VoiceRecognitionController;->listDownloadedModels(Ljava/lang/String;)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method private final handleDeleteRecognitionModelClick()V
    .locals 2

    .line 563
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->getDownloadedRecognitionModels()Ljava/util/List;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda43;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda43;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    invoke-static {p0, v0, v1}, Lcom/exteragram/messenger/speech/ui/RecognitionModelDialogs;->showDeleteFlow(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/util/List;Lorg/telegram/messenger/Utilities$Callback;)V

    return-void
.end method

.method private final handleDoubleTapActionButtonClick(Z)Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;
    .locals 1

    .line 521
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->doubleTapCell:Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;

    if-eqz p0, :cond_1

    const/4 v0, 0x1

    if-eqz p1, :cond_0

    const/4 p1, 0x2

    goto :goto_0

    :cond_0
    move p1, v0

    .line 522
    :goto_0
    invoke-virtual {p0, p1, v0}, Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;->updateIcons(IZ)V

    .line 523
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;->invalidate()V

    return-object p0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method private final handleSpeechRecognitionLanguageClick(Lorg/telegram/ui/Components/UItem;)V
    .locals 7

    .line 533
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->recognitionLanguageOptions:[Ljava/lang/CharSequence;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :cond_0
    move-object v1, v0

    sget v0, Lorg/telegram/messenger/R$string;->RecognitionLanguage:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget-object v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->languageCodes:Ljava/util/List;

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getRecognitionLanguage()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v0, v2}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result v4

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v5

    new-instance v6, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda42;

    invoke-direct {v6, p0, p1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda42;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;Lorg/telegram/ui/Components/UItem;)V

    const/4 v2, 0x0

    invoke-static/range {v1 .. v6}, Lcom/exteragram/messenger/utils/ui/PopupUtils;->showDialog([Ljava/lang/CharSequence;[ILjava/lang/String;ILandroid/content/Context;Lcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void
.end method

.method private final isSeamlessSwitchingAvailable()Z
    .locals 2

    .line 527
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCameraType()Lcom/exteragram/messenger/CameraType;

    move-result-object v0

    sget-object v1, Lcom/exteragram/messenger/CameraType;->CAMERA_X:Lcom/exteragram/messenger/CameraType;

    if-ne v0, v1, :cond_0

    .line 528
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/exteragram/messenger/camera/CameraXSession;->isSeamlessSwitchingAvailable(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 529
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCameraType()Lcom/exteragram/messenger/CameraType;

    move-result-object v0

    sget-object v1, Lcom/exteragram/messenger/CameraType;->CAMERA_2:Lcom/exteragram/messenger/CameraType;

    if-ne v0, v1, :cond_2

    .line 530
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/ui/Stories/recorder/DualCameraView;->dualAvailableStatic(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_2

    :cond_1
    const/4 p0, 0x1

    return p0

    :cond_2
    const/4 p0, 0x0

    return p0
.end method

.method private final swipeActionsValue()Ljava/lang/CharSequence;
    .locals 8

    .line 319
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/SwipeAction;->enabled()Ljava/util/List;

    move-result-object v0

    .line 320
    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    sget p0, Lorg/telegram/messenger/R$string;->Disable:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 321
    :cond_0
    new-instance v1, Landroid/text/SpannableStringBuilder;

    invoke-direct {v1}, Landroid/text/SpannableStringBuilder;-><init>()V

    .line 322
    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v2

    const/4 v3, 0x5

    invoke-static {v2, v3}, Lkotlin/ranges/RangesKt;->coerceAtMost(II)I

    move-result v2

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_3

    if-lez v3, :cond_1

    const/16 v4, 0x20

    .line 325
    invoke-virtual {v1, v4}, Landroid/text/SpannableStringBuilder;->append(C)Landroid/text/SpannableStringBuilder;

    .line 326
    :cond_1
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v4

    invoke-interface {v0, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/exteragram/messenger/utils/chats/SwipeAction;

    iget v5, v5, Lcom/exteragram/messenger/utils/chats/SwipeAction;->iconRes:I

    invoke-static {v4, v5}, Landroidx/core/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v4

    if-nez v4, :cond_2

    goto :goto_1

    .line 327
    :cond_2
    invoke-virtual {v1}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v5

    const/16 v6, 0x2a

    .line 328
    invoke-virtual {v1, v6}, Landroid/text/SpannableStringBuilder;->append(C)Landroid/text/SpannableStringBuilder;

    .line 329
    new-instance v6, Lorg/telegram/ui/Components/ColoredImageSpan;

    invoke-virtual {v4}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v4

    invoke-direct {v6, v4}, Lorg/telegram/ui/Components/ColoredImageSpan;-><init>(Landroid/graphics/drawable/Drawable;)V

    const/high16 v4, 0x41900000    # 18.0f

    .line 330
    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    invoke-virtual {v6, v4}, Lorg/telegram/ui/Components/ColoredImageSpan;->setSize(I)V

    .line 331
    invoke-virtual {v1}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v4

    const/16 v7, 0x21

    invoke-virtual {v1, v6, v5, v4, v7}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    :goto_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 334
    :cond_3
    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result p0

    if-le p0, v2, :cond_4

    .line 335
    const-string p0, " +"

    invoke-virtual {v1, p0}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object p0

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    sub-int/2addr v0, v2

    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    :cond_4
    return-object v1
.end method

.method private final updateReplySettings()V
    .locals 1

    .line 517
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerSizeCell:Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;->invalidate()V

    .line 518
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 v0, 0x0

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 11

    .line 265
    new-instance v0, Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

    .line 266
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    .line 267
    sget-object v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->STICKER_SIZE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    invoke-virtual {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->getId()I

    move-result v3

    .line 269
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getStickerSize()F

    move-result v6

    .line 270
    sget v2, Lorg/telegram/messenger/R$string;->StickerSize:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v7

    .line 271
    sget v2, Lorg/telegram/messenger/R$string;->StickerSizeLeft:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v8

    .line 272
    sget v2, Lorg/telegram/messenger/R$string;->StickerSizeRight:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v9

    const/4 v10, 0x0

    const/4 v4, 0x4

    const/16 v5, 0x14

    move-object v2, p1

    .line 265
    invoke-direct/range {v0 .. v10}, Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;-><init>(Lorg/telegram/ui/ActionBar/INavigationLayout;Landroid/content/Context;IIIFLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V

    .line 275
    new-instance p1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda0;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;->setListener(Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell$OnSliderChangedListener;)Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

    .line 282
    iput-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerSizeCell:Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

    .line 284
    new-instance p1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$createView$2;

    invoke-direct {p1, v2, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$createView$2;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerShapeCell:Lcom/exteragram/messenger/preferences/chats/components/StickerShapeCell;

    .line 290
    new-instance p1, Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;

    invoke-direct {p1, v2}, Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;-><init>(Landroid/content/Context;)V

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->doubleTapCell:Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;

    .line 291
    new-instance p1, Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;

    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 v1, 0x1

    invoke-direct {p1, v2, v0, v1}, Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/INavigationLayout;I)V

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->messagesPreviewCell:Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;

    .line 293
    invoke-super {p0, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object p1

    .line 295
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/ActionBar;->createMenu()Lorg/telegram/ui/ActionBar/ActionBarMenu;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/R$drawable;->msg_reset:I

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(II)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v0

    .line 296
    sget v1, Lorg/telegram/messenger/R$string;->Reset:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 297
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getStickerSize()F

    move-result v1

    const/high16 v3, 0x41400000    # 12.0f

    cmpg-float v1, v1, v3

    if-nez v1, :cond_0

    const/16 v2, 0x8

    :cond_0
    invoke-virtual {v0, v2}, Landroid/view/View;->setVisibility(I)V

    const/4 v1, 0x0

    .line 298
    invoke-virtual {v0, v1}, Landroid/view/View;->setTag(Ljava/lang/Object;)V

    .line 299
    new-instance v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 295
    iput-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->resetItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    return-object p1
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 17
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

    .line 340
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->STICKER_SIZE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->getId()I

    move-result v1

    iget-object v2, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerSizeCell:Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    const-string v2, "stickerSize"

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 341
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->STICKER_TIME:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->StickerTimeMode:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerTimeModes:[Ljava/lang/CharSequence;

    if-nez v3, :cond_0

    const/4 v3, 0x0

    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getStickerTimeMode()Lcom/exteragram/messenger/StickerTimeMode;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Enum;->ordinal()I

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

    const-string v8, "stickerTime"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 342
    iget-object v1, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->replyElements:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 343
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 345
    sget v1, Lorg/telegram/messenger/R$string;->StickerShape:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 346
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->STICKER_SHAPE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->getId()I

    move-result v1

    iget-object v2, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerShapeCell:Lcom/exteragram/messenger/preferences/chats/components/StickerShapeCell;

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    const-string v2, "stickerShape"

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 347
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 349
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->AI:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->AIChat:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->ai_chat:I

    sget v4, Lorg/telegram/messenger/R$string;->AIChatInfo:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v5

    const/16 v12, 0x768

    const/4 v4, 0x0

    const-string v8, "aiChat"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 350
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->CHAT_SETTINGS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->ChatSettings:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_discussion:I

    sget v0, Lorg/telegram/messenger/R$string;->ChatSettingsInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v5

    const/16 v12, 0x728

    const-string v8, "chatSettings"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 351
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 353
    sget v0, Lorg/telegram/messenger/R$string;->StickersName:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 354
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->UNLIMITED_RECENT_STICKERS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->UnlimitedRecentStickers:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUnlimitedRecentStickers()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v8, 0x0

    const-string v7, "unlimitedRecentStickers"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 355
    iget-object v1, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->hideReactions:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 356
    sget v1, Lorg/telegram/messenger/R$string;->HideReactionsInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 358
    sget v1, Lorg/telegram/messenger/R$string;->Gestures:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 359
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->DOUBLE_TAP:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->getId()I

    move-result v1

    iget-object v2, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->doubleTapCell:Lcom/exteragram/messenger/preferences/chats/components/DoubleTapCell;

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 360
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->DOUBLE_TAP_ACTION:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->DoubleTapIncoming:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDoubleTapAction()I

    move-result v3

    const/4 v4, 0x0

    invoke-static {v3, v4}, Lcom/exteragram/messenger/utils/chats/DoubleTapUtils;->getDoubleTapActionLabel(IZ)Ljava/lang/CharSequence;

    move-result-object v3

    const/16 v12, 0x774

    move v5, v4

    move-object v4, v3

    const/4 v3, 0x0

    move v6, v5

    const/4 v5, 0x0

    move v7, v6

    const/4 v6, 0x0

    move v8, v7

    const/4 v7, 0x0

    const/4 v10, 0x0

    move/from16 v16, v8

    const-string v8, "doubleTapIncoming"

    move/from16 v15, v16

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 361
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->DOUBLE_TAP_ACTION_OUT_OWNER:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->DoubleTapOutgoing:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDoubleTapActionOutOwner()I

    move-result v0

    const/4 v3, 0x1

    invoke-static {v0, v3}, Lcom/exteragram/messenger/utils/chats/DoubleTapUtils;->getDoubleTapActionLabel(IZ)Ljava/lang/CharSequence;

    move-result-object v4

    move v0, v3

    const/4 v3, 0x0

    const-string v8, "doubleTapOutgoing"

    move/from16 v16, v0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 362
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->SWIPE_ACTIONS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->SwipeActions:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->swipeActionsValue()Ljava/lang/CharSequence;

    move-result-object v4

    const-string v8, "swipeActions"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 363
    sget v1, Lorg/telegram/messenger/R$string;->DoubleTapInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 365
    sget v1, Lorg/telegram/messenger/R$string;->MainTabsChats:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 366
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->BOTTOM_BUTTON:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->BottomButton:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->bottomButtonOptions:[Ljava/lang/CharSequence;

    if-nez v3, :cond_1

    const/4 v3, 0x0

    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getBottomButton()I

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

    const-string v8, "bottomButton"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 367
    iget-object v1, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->widePosts:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 368
    iget-object v1, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->aiFeatures:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 369
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->ADMIN_SHORTCUTS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->AdminShortcuts:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getQuickAdminShortcuts()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v8, 0x0

    const-string v7, "adminShortcuts"

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 370
    iget-object v1, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->quickTransitions:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 371
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->DISABLE_GREETING_STICKER:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->DisableGreetingSticker:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDisableGreetingSticker()Z

    move-result v3

    const-string v7, "disableGreetingSticker"

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 372
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->HIDE_KEYBOARD_ON_SCROLL:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->HideKeyboardOnScroll:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideKeyboardOnScroll()Z

    move-result v3

    const-string v7, "hideKeyboardOnScroll"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 373
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->ADD_COMMA_AFTER_MENTION:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->AddCommaAfterMention:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getAddCommaAfterMention()Z

    move-result v3

    const-string v7, "addCommaAfterMention"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 374
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->INLINE_MATH_RESULT:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->InlineMathResult:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getInlineMathResult()Z

    move-result v3

    sget v0, Lorg/telegram/messenger/R$string;->InlineMathResultHint:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x1b0

    const-string v7, "inlineMathResult"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 375
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->HIDE_SEND_AS_PEER:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->HideSendAsPeer:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideSendAsPeer()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const-string v7, "hideSendAsPeer"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 376
    sget v1, Lorg/telegram/messenger/R$string;->HideSendAsPeerInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 378
    sget v1, Lorg/telegram/messenger/R$string;->MessagesChartTitle:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 379
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->MESSAGES_PREVIEW:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->getId()I

    move-result v1

    iget-object v2, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->messagesPreviewCell:Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 380
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->REMOVE_MESSAGE_TAIL:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->RemoveMessageTail:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getRemoveMessageTail()Z

    move-result v3

    const-string v7, "removeMessageTail"

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 381
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->REPLACE_EDITED_WITH_ICON:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->ReplaceEditedWithIcon:I

    sget v2, Lorg/telegram/messenger/R$string;->EditedMessage:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    filled-new-array {v2}, [Ljava/lang/Object;

    move-result-object v2

    invoke-static {v0, v2}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getReplaceEditedWithIcon()Z

    move-result v3

    const-string v7, "replaceEditedWithIcon"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 382
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->SHOW_ONLINE_STATUS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->ShowOnlineStatus:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getShowOnlineStatus()Z

    move-result v3

    const-string v7, "showOnlineStatus"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 383
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->SHOW_FORWARDS_COUNT:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->ShowForwardsCount:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getShowForwardsCount()Z

    move-result v3

    const-string v7, "showForwardsCount"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 384
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->HIDE_SHARE_BUTTON:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->HideShareButton:I

    sget v2, Lorg/telegram/messenger/R$string;->ShareFile:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    filled-new-array {v2}, [Ljava/lang/Object;

    move-result-object v2

    invoke-static {v0, v2}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideShareButton()Z

    move-result v3

    const-string v7, "hideShareButton"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 385
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->SHOW_RESULTS_BEFORE_VOTING:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->ShowPollResultsBeforeVoting:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getShowResultsBeforeVoting()Z

    move-result v3

    sget v0, Lorg/telegram/messenger/R$string;->ShowPollResultsBeforeVotingHint:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x1b0

    const-string v7, "showResultsBeforeVoting"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 386
    iget-object v1, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->messageMenu:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 387
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->GROUP_MESSAGE_MENU:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->GroupMessageMenu:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGroupMessageMenu()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const-string v7, "groupMessageMenu"

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 388
    sget v0, Lorg/telegram/messenger/R$string;->GroupMessageMenuInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 390
    sget v0, Lorg/telegram/messenger/R$string;->PremiumPreviewVoiceToText:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 391
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->SPEECH_RECOGNITION_LANGUAGE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->RecognitionLanguage:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getRecognitionLanguage()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/exteragram/messenger/translator/TranslatorUtils;->getLanguageTitleSystem(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v10, 0x0

    const-string v8, "recognitionLanguage"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 392
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->canUseAI()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-static {}, Lcom/exteragram/messenger/speech/VoiceRecognitionController;->isCustomRecognitionEnabled()Z

    move-result v0

    if-eqz v0, :cond_2

    move/from16 v12, v16

    goto :goto_0

    :cond_2
    move v12, v15

    :goto_0
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->POST_PROCESSING_WITH_AI:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->PostProcessingWithAi:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPostprocessingWithAi()Z

    move-result v3

    sget v0, Lorg/telegram/messenger/R$string;->PostProcessingWithAiInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x1b0

    const/4 v11, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "postprocessingWithAi"

    const/4 v8, 0x0

    const/4 v9, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-static {v14, v12, v1}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    .line 393
    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->getDownloadedRecognitionModels()Ljava/util/List;

    move-result-object v0

    check-cast v0, Ljava/util/Collection;

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->DELETE_RECOGNITION_MODEL:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->DeleteRecognitionModel:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_delete:I

    const/16 v12, 0x678

    const/4 v13, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "deleteRecognitionModel"

    const/4 v9, 0x1

    const/4 v10, 0x0

    move v15, v0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-static {v14, v15, v1}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    .line 394
    sget v1, Lorg/telegram/messenger/R$string;->RecognitionInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 396
    sget v1, Lorg/telegram/messenger/R$string;->VoipCamera:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 397
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->CAMERA_TYPE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->CameraType:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->cameraTypeOptions:[Ljava/lang/CharSequence;

    if-nez v3, :cond_3

    const/4 v3, 0x0

    :cond_3
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCameraType()Lcom/exteragram/messenger/CameraType;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Enum;->ordinal()I

    move-result v4

    aget-object v4, v3, v4

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "cameraType"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 398
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCameraType()Lcom/exteragram/messenger/CameraType;

    move-result-object v1

    sget-object v2, Lcom/exteragram/messenger/CameraType;->CAMERA_1:Lcom/exteragram/messenger/CameraType;

    if-eq v1, v2, :cond_4

    .line 399
    iget-object v1, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->cameraSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 400
    invoke-direct {v0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->isSeamlessSwitchingAvailable()Z

    move-result v1

    if-nez v1, :cond_4

    .line 401
    invoke-static {}, Lorg/telegram/messenger/MessagesController;->getGlobalMainSettings()Landroid/content/SharedPreferences;

    move-result-object v1

    .line 45
    invoke-interface {v1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    .line 401
    const-string v2, "rounddual_available"

    const/4 v15, 0x0

    invoke-interface {v1, v2, v15}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->apply()V

    goto :goto_1

    :cond_4
    const/4 v15, 0x0

    .line 403
    :goto_1
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->VIDEO_MESSAGES_CAMERA:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->VideoMessagesCamera:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->videoMessagesCameraOptions:[Ljava/lang/CharSequence;

    if-nez v3, :cond_5

    const/4 v3, 0x0

    :cond_5
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getVideoMessagesCamera()Lcom/exteragram/messenger/VideoMessagesCamera;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Enum;->ordinal()I

    move-result v4

    aget-object v4, v3, v4

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "videoMessagesCamera"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 404
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getVideoMessagesCamera()Lcom/exteragram/messenger/VideoMessagesCamera;

    move-result-object v0

    sget-object v1, Lcom/exteragram/messenger/VideoMessagesCamera;->ASK:Lcom/exteragram/messenger/VideoMessagesCamera;

    if-eq v0, v1, :cond_6

    move/from16 v15, v16

    :cond_6
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->REMEMBER_LAST_USED_CAMERA:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->RememberLastUsedCamera:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getRememberLastUsedCamera()Z

    move-result v3

    sget v0, Lorg/telegram/messenger/R$string;->RememberLastUsedCameraInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x1b0

    const/4 v11, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "rememberLastUsedCamera"

    const/4 v8, 0x0

    const/4 v9, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-static {v14, v15, v1}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    .line 405
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->ZOOM_SLIDER:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->ZoomSlider:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getZoomSlider()Z

    move-result v3

    sget v0, Lorg/telegram/messenger/R$string;->ZoomSliderInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const-string v7, "zoomSlider"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 406
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->STATIC_ZOOM:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->StaticZoom:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getStaticZoom()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const-string v7, "staticZoom"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 407
    sget v0, Lorg/telegram/messenger/R$string;->StaticZoomInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 409
    sget v0, Lorg/telegram/messenger/R$string;->AutoDownloadPhotos:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 410
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->ALWAYS_SEND_IN_HD:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->AlwaysSendInHD:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getAlwaysSendInHD()Z

    move-result v3

    const-string v7, "alwaysSendInHD"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 411
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->HIDE_CAMERA_TILE:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->HideCameraTile:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideCameraTile()Z

    move-result v3

    const-string v7, "hideCameraTile"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 412
    sget v1, Lorg/telegram/messenger/R$string;->HideCameraTileInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 414
    sget v1, Lorg/telegram/messenger/R$string;->AutoDownloadVideos:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 415
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->DOUBLE_TAP_SEEK_DURATION:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v2, Lorg/telegram/messenger/R$string;->DoubleTapSeekDuration:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->doubleTapSeekDurationOptions:[Ljava/lang/CharSequence;

    if-nez v3, :cond_7

    const/4 v15, 0x0

    goto :goto_2

    :cond_7
    move-object v15, v3

    :goto_2
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDoubleTapSeekDuration()I

    move-result v3

    aget-object v4, v15, v3

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "doubleTapSeekDuration"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 416
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->PREFER_ORIGINAL_QUALITY:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->PreferOriginalQuality:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPreferOriginalQuality()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "preferOriginalQuality"

    const/4 v8, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 417
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->SWIPE_TO_PIP:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->SwipeToPip:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSwipeToPip()Z

    move-result v3

    const-string v7, "swipeToPip"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 418
    sget-object v1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->UNMUTE_WITH_VOLUME_BUTTONS:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    sget v0, Lorg/telegram/messenger/R$string;->UnmuteWithVolumeButtons:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUnmuteWithVolumeButtons()Z

    move-result v3

    sget v0, Lorg/telegram/messenger/R$string;->UnmuteWithVolumeButtonsInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x1b0

    const-string v7, "unmuteWithVolumeButtons"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 419
    iget-object v0, v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->pauseOnMinimize:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v0, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 420
    sget v0, Lorg/telegram/messenger/R$string;->PauseOnMinimizeInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 316
    sget p0, Lorg/telegram/messenger/R$string;->SearchAllChatsShort:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public initializeOptionStrings()V
    .locals 11

    .line 232
    sget v0, Lorg/telegram/messenger/R$string;->Default:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 233
    sget v1, Lorg/telegram/messenger/R$string;->StickerTimeSide:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 234
    sget v2, Lorg/telegram/messenger/R$string;->StickerTimeHidden:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    const/4 v3, 0x3

    new-array v4, v3, [Ljava/lang/CharSequence;

    const/4 v5, 0x0

    aput-object v0, v4, v5

    const/4 v0, 0x1

    aput-object v1, v4, v0

    const/4 v1, 0x2

    aput-object v2, v4, v1

    .line 231
    iput-object v4, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerTimeModes:[Ljava/lang/CharSequence;

    .line 236
    invoke-static {v5}, Lcom/exteragram/messenger/utils/chats/DoubleTapUtils;->getDoubleTapActions(Z)[Ljava/lang/CharSequence;

    move-result-object v2

    iput-object v2, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->doubleTapActions:[Ljava/lang/CharSequence;

    .line 237
    invoke-static {v0}, Lcom/exteragram/messenger/utils/chats/DoubleTapUtils;->getDoubleTapActions(Z)[Ljava/lang/CharSequence;

    move-result-object v2

    iput-object v2, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->doubleTapOutActions:[Ljava/lang/CharSequence;

    .line 239
    sget v2, Lorg/telegram/messenger/R$string;->Hide:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 240
    sget v4, Lorg/telegram/messenger/R$string;->ChannelMuteNoCaps:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 241
    sget v6, Lorg/telegram/messenger/R$string;->ChannelDiscussNoCaps:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-array v7, v3, [Ljava/lang/CharSequence;

    aput-object v2, v7, v5

    aput-object v4, v7, v0

    aput-object v6, v7, v1

    .line 238
    iput-object v7, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->bottomButtonOptions:[Ljava/lang/CharSequence;

    .line 244
    sget v2, Lorg/telegram/messenger/R$string;->VideoMessagesCameraFront:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 245
    sget v4, Lorg/telegram/messenger/R$string;->VideoMessagesCameraRear:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 246
    sget v6, Lorg/telegram/messenger/R$string;->VideoMessagesCameraAsk:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-array v7, v3, [Ljava/lang/CharSequence;

    aput-object v2, v7, v5

    aput-object v4, v7, v0

    aput-object v6, v7, v1

    .line 243
    iput-object v7, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->videoMessagesCameraOptions:[Ljava/lang/CharSequence;

    const/4 v2, 0x5

    .line 249
    new-array v4, v5, [Ljava/lang/Object;

    const-string v6, "Seconds"

    invoke-static {v6, v2, v4}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    .line 250
    new-array v4, v5, [Ljava/lang/Object;

    const/16 v7, 0xa

    invoke-static {v6, v7, v4}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    const/16 v8, 0xf

    .line 251
    new-array v9, v5, [Ljava/lang/Object;

    invoke-static {v6, v8, v9}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v8

    const/16 v9, 0x1e

    .line 252
    new-array v10, v5, [Ljava/lang/Object;

    invoke-static {v6, v9, v10}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    const/4 v9, 0x4

    new-array v9, v9, [Ljava/lang/CharSequence;

    aput-object v2, v9, v5

    aput-object v4, v9, v0

    aput-object v8, v9, v1

    aput-object v6, v9, v3

    .line 248
    iput-object v9, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->doubleTapSeekDurationOptions:[Ljava/lang/CharSequence;

    .line 257
    new-array v2, v3, [Ljava/lang/CharSequence;

    const-string v3, "Camera 1"

    aput-object v3, v2, v5

    const-string v3, "Camera 2"

    aput-object v3, v2, v0

    const-string v0, "Camera X"

    aput-object v0, v2, v1

    .line 254
    iput-object v2, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->cameraTypeOptions:[Ljava/lang/CharSequence;

    .line 259
    sget-object v0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->languageCodes:Ljava/util/List;

    check-cast v0, Ljava/lang/Iterable;

    .line 1586
    new-instance v1, Ljava/util/ArrayList;

    invoke-static {v0, v7}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v2

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 1662
    check-cast v2, Ljava/lang/String;

    .line 260
    invoke-static {v2}, Lcom/exteragram/messenger/speech/ui/RecognitionModelDialogs;->getRecognitionLanguageOption(Ljava/lang/String;)Ljava/lang/CharSequence;

    move-result-object v2

    .line 1662
    invoke-interface {v1, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 38
    :cond_0
    new-array v0, v5, [Ljava/lang/CharSequence;

    invoke-interface {v1, v0}, Ljava/util/Collection;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/CharSequence;

    .line 259
    iput-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->recognitionLanguageOptions:[Ljava/lang/CharSequence;

    return-void
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 11

    .line 424
    iget v2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-lez v2, :cond_9

    invoke-static {}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/List;->size()I

    move-result v3

    if-le v2, v3, :cond_0

    goto/16 :goto_2

    .line 425
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v2

    iget v3, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 v4, 0x1

    sub-int/2addr v3, v4

    invoke-interface {v2, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$ChatsItem;

    .line 426
    sget-object v3, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {v2}, Ljava/lang/Enum;->ordinal()I

    move-result v2

    aget v2, v3, v2

    const/4 v3, 0x0

    const/4 v5, 0x0

    packed-switch v2, :pswitch_data_0

    goto/16 :goto_2

    .line 511
    :pswitch_0
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->pauseOnMinimize:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 510
    :pswitch_1
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda33;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda33;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 509
    :pswitch_2
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda32;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda32;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 508
    :pswitch_3
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda31;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda31;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 507
    :pswitch_4
    iget-object v2, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->doubleTapSeekDurationOptions:[Ljava/lang/CharSequence;

    if-nez v2, :cond_1

    move-object v2, v5

    :cond_1
    sget v3, Lorg/telegram/messenger/R$string;->DoubleTapSeekDuration:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDoubleTapSeekDuration()I

    move-result v4

    new-instance v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda30;

    invoke-direct {v5}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda30;-><init>()V

    move-object v0, p0

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 506
    :pswitch_5
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda29;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda29;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 505
    :pswitch_6
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda28;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda28;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 504
    :pswitch_7
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda27;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda27;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 503
    :pswitch_8
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda26;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda26;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 502
    :pswitch_9
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda25;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda25;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 501
    :pswitch_a
    iget-object v2, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->videoMessagesCameraOptions:[Ljava/lang/CharSequence;

    if-nez v2, :cond_2

    move-object v2, v5

    :cond_2
    sget v3, Lorg/telegram/messenger/R$string;->VideoMessagesCamera:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getVideoMessagesCamera()Lcom/exteragram/messenger/VideoMessagesCamera;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Enum;->ordinal()I

    move-result v4

    new-instance v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda24;

    invoke-direct {v5}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda24;-><init>()V

    move-object v0, p0

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 500
    :pswitch_b
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->cameraSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 498
    :pswitch_c
    iget-object v2, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->cameraTypeOptions:[Ljava/lang/CharSequence;

    if-nez v2, :cond_3

    move-object v2, v5

    :cond_3
    sget v3, Lorg/telegram/messenger/R$string;->CameraType:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCameraType()Lcom/exteragram/messenger/CameraType;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Enum;->ordinal()I

    move-result v4

    new-instance v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda22;

    invoke-direct {v5}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda22;-><init>()V

    move-object v0, p0

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 497
    :pswitch_d
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->handleDeleteRecognitionModelClick()V

    return-void

    .line 496
    :pswitch_e
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda21;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda21;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 495
    :pswitch_f
    invoke-direct/range {p0 .. p1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->handleSpeechRecognitionLanguageClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 494
    :pswitch_10
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda20;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda20;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 493
    :pswitch_11
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->messageMenu:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 490
    :pswitch_12
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda19;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda19;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 491
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->messagesPreviewCell:Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;

    if-eqz v0, :cond_9

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;->refreshMessages()V

    return-void

    .line 486
    :pswitch_13
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda18;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda18;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 487
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {v0, v3}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void

    .line 480
    :pswitch_14
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda17;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda17;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 481
    sput-object v5, Lorg/telegram/ui/ActionBar/Theme;->chat_msgInDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

    .line 482
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-static {v1, v3}, Lorg/telegram/ui/ActionBar/Theme;->createChatResources(Landroid/content/Context;Z)V

    .line 483
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->messagesPreviewCell:Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;

    if-eqz v0, :cond_9

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;->refreshMessages()V

    return-void

    .line 475
    :pswitch_15
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda16;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda16;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 476
    iget-object v1, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->messagesPreviewCell:Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;

    if-eqz v1, :cond_4

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;->refreshMessages()V

    .line 477
    :cond_4
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->isTablet()Z

    move-result v1

    invoke-interface {v0, v1}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void

    .line 471
    :pswitch_16
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda15;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda15;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 472
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->messagesPreviewCell:Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;

    if-eqz v0, :cond_9

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;->refreshMessages()V

    return-void

    .line 467
    :pswitch_17
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda14;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda14;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 468
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->messagesPreviewCell:Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;

    if-eqz v0, :cond_9

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/chats/components/MessagesPreviewCell;->refreshMessages()V

    return-void

    .line 465
    :pswitch_18
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda13;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda13;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 464
    :pswitch_19
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda41;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda41;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 463
    :pswitch_1a
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda40;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda40;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 462
    :pswitch_1b
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda39;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda39;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 461
    :pswitch_1c
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda38;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda38;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 460
    :pswitch_1d
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->quickTransitions:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 459
    :pswitch_1e
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda37;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda37;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 458
    :pswitch_1f
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->aiFeatures:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 457
    :pswitch_20
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->widePosts:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 453
    :pswitch_21
    iget-object v2, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->bottomButtonOptions:[Ljava/lang/CharSequence;

    if-nez v2, :cond_5

    move-object v2, v5

    :cond_5
    sget v3, Lorg/telegram/messenger/R$string;->BottomButton:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getBottomButton()I

    move-result v4

    new-instance v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda36;

    invoke-direct {v5, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda36;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    move-object v0, p0

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 451
    :pswitch_22
    new-instance v1, Lcom/exteragram/messenger/preferences/chats/SwipeActionsPreferencesActivity;

    invoke-direct {v1}, Lcom/exteragram/messenger/preferences/chats/SwipeActionsPreferencesActivity;-><init>()V

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 446
    :pswitch_23
    iget-object v1, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->doubleTapOutActions:[Ljava/lang/CharSequence;

    if-nez v1, :cond_6

    move-object v2, v5

    goto :goto_0

    :cond_6
    move-object v2, v1

    :goto_0
    invoke-static {v4}, Lcom/exteragram/messenger/utils/chats/DoubleTapUtils;->getDoubleTapIcons(Z)[I

    move-result-object v3

    sget v1, Lorg/telegram/messenger/R$string;->DoubleTapOutgoing:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDoubleTapActionOutOwner()I

    move-result v5

    new-instance v6, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda35;

    invoke-direct {v6, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda35;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    const/16 v9, 0xc0

    const/4 v10, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    move-object v0, p0

    move-object v1, p1

    invoke-static/range {v0 .. v10}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;ZZILjava/lang/Object;)V

    return-void

    .line 440
    :pswitch_24
    iget-object v1, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->doubleTapActions:[Ljava/lang/CharSequence;

    if-nez v1, :cond_7

    move-object v2, v5

    goto :goto_1

    :cond_7
    move-object v2, v1

    :goto_1
    invoke-static {v3}, Lcom/exteragram/messenger/utils/chats/DoubleTapUtils;->getDoubleTapIcons(Z)[I

    move-result-object v3

    sget v1, Lorg/telegram/messenger/R$string;->DoubleTapIncoming:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDoubleTapAction()I

    move-result v5

    new-instance v6, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda34;

    invoke-direct {v6, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda34;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    const/16 v9, 0xc0

    const/4 v10, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    move-object v0, p0

    move-object v1, p1

    invoke-static/range {v0 .. v10}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;ZZILjava/lang/Object;)V

    return-void

    .line 438
    :pswitch_25
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->hideReactions:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 437
    :pswitch_26
    new-instance v2, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda23;

    invoke-direct {v2}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda23;-><init>()V

    invoke-virtual {p0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 435
    :pswitch_27
    new-instance v1, Lorg/telegram/ui/ThemeActivity;

    invoke-direct {v1, v3}, Lorg/telegram/ui/ThemeActivity;-><init>(I)V

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 434
    :pswitch_28
    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;

    invoke-direct {v1}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;-><init>()V

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 433
    :pswitch_29
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->replyElements:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 428
    :pswitch_2a
    iget-object v2, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->stickerTimeModes:[Ljava/lang/CharSequence;

    if-nez v2, :cond_8

    move-object v2, v5

    :cond_8
    sget v3, Lorg/telegram/messenger/R$string;->StickerTimeMode:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getStickerTimeMode()Lcom/exteragram/messenger/StickerTimeMode;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Enum;->ordinal()I

    move-result v4

    new-instance v5, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda12;

    invoke-direct {v5, p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$$ExternalSyntheticLambda12;-><init>(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V

    move-object v0, p0

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    :cond_9
    :goto_2
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_2a
        :pswitch_29
        :pswitch_29
        :pswitch_29
        :pswitch_29
        :pswitch_28
        :pswitch_27
        :pswitch_26
        :pswitch_25
        :pswitch_25
        :pswitch_25
        :pswitch_25
        :pswitch_24
        :pswitch_23
        :pswitch_22
        :pswitch_21
        :pswitch_20
        :pswitch_20
        :pswitch_20
        :pswitch_1f
        :pswitch_1f
        :pswitch_1f
        :pswitch_1f
        :pswitch_1e
        :pswitch_1d
        :pswitch_1d
        :pswitch_1d
        :pswitch_1c
        :pswitch_1b
        :pswitch_1a
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_11
        :pswitch_11
        :pswitch_11
        :pswitch_11
        :pswitch_11
        :pswitch_11
        :pswitch_11
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_b
        :pswitch_b
        :pswitch_b
        :pswitch_b
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
        :pswitch_0
        :pswitch_0
        :pswitch_0
    .end packed-switch
.end method
