.class public abstract Lorg/telegram/ui/web/BotWebViewContainer;
.super Landroid/widget/FrameLayout;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;,
        Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;,
        Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;,
        Lorg/telegram/ui/web/BotWebViewContainer$WebViewScrollListener;,
        Lorg/telegram/ui/web/BotWebViewContainer$Delegate;,
        Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;,
        Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;
    }
.end annotation


# static fields
.field public static firstWebView:Z = true

.field private static rotatedTONHosts:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private static tags:I


# instance fields
.field private biometry:Lorg/telegram/ui/bots/BotBiometry;

.field private blockedDialogsUntil:J

.field public final bot:Z

.field private botUser:Lorg/telegram/tgnet/TLRPC$User;

.field private botWebViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

.field private buttonData:Ljava/lang/String;

.field private cameraBottomSheet:Lorg/telegram/ui/ActionBar/BottomSheet;

.field private currentAccount:I

.field private currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

.field private currentPaymentSlug:Ljava/lang/String;

.field private delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

.field private dialogSequentialOpenTimes:I

.field private documentGeneration:J

.field private downloads:Lorg/telegram/ui/bots/BotDownloads;

.field private final flickerDrawable:Lorg/telegram/ui/Components/voip/CellFlickerDrawable;

.field private flickerView:Lorg/telegram/ui/Components/BackupImageView;

.field private flickerViewColor:I

.field private flickerViewColorOverriden:Z

.field private flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

.field private forceHeight:I

.field private hasQRPending:Z

.field private hasUserPermissions:Z

.field private isBackButtonVisible:Z

.field private isFlickeringCenter:Z

.field private isPageLoaded:Z

.field private isRequestingPageOpen:Z

.field private isSettingsButtonVisible:Z

.field private isViewPortByMeasureSuppressed:Z

.field private keyboardFocusable:Z

.field private lastButtonColor:I

.field private lastButtonText:Ljava/lang/String;

.field private lastButtonTextColor:I

.field private lastClickMs:J

.field private lastDialogClosed:J

.field private lastDialogCooldownTime:J

.field private lastDialogType:I

.field private lastExpanded:Z

.field private final lastInsets:Landroid/graphics/Rect;

.field private lastInsetsTopMargin:I

.field private lastPostStoryMs:J

.field private lastQrText:Ljava/lang/String;

.field private lastSecondaryButtonColor:I

.field private lastSecondaryButtonPosition:Ljava/lang/String;

.field private lastSecondaryButtonText:Ljava/lang/String;

.field private lastSecondaryButtonTextColor:I

.field private lastViewportHeightReported:I

.field private lastViewportIsExpanded:Z

.field private lastViewportStateStable:Z

.field private location:Lorg/telegram/ui/bots/BotLocation;

.field private locationRequestContext:Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;

.field private mFilePathCallback:Landroid/webkit/ValueCallback;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/webkit/ValueCallback<",
            "[",
            "Landroid/net/Uri;",
            ">;"
        }
    .end annotation
.end field

.field private mUrl:Ljava/lang/String;

.field private final notifyLocationChecked:Ljava/lang/Runnable;

.field private onCloseListener:Ljava/lang/Runnable;

.field private onPermissionsRequestResultCallback:Ljava/lang/Runnable;

.field private onVerifiedAge:Lorg/telegram/messenger/Utilities$Callback4;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lorg/telegram/messenger/Utilities$Callback4<",
            "Ljava/lang/Boolean;",
            "Ljava/lang/Double;",
            "Ljava/lang/String;",
            "Ljava/lang/Double;",
            ">;"
        }
    .end annotation
.end field

.field private opener:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

.field private parentActivity:Landroid/app/Activity;

.field private preserving:Z

.field private resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

.field private restrictBridgeToOrigin:Z

.field private secondaryButtonData:Ljava/lang/String;

.field private secureStorage:Lorg/telegram/ui/bots/BotStorage;

.field private sensors:Lorg/telegram/ui/bots/BotSensors;

.field private shownDialogsCount:I

.field private storage:Lorg/telegram/ui/bots/BotStorage;

.field private final tag:I

.field private trustedOrigin:Ljava/lang/String;

.field private viewPortHeightOffset:F

.field private wasFocusable:Z

.field private wasOpenedByBot:Lorg/telegram/ui/bots/WebViewRequestProps;

.field private wasOpenedByLinkIntent:Z

.field private webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

.field private webViewNotAvailable:Z

.field private webViewNotAvailableText:Landroid/widget/TextView;

.field private webViewProgressListener:Landroidx/core/util/Consumer;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/core/util/Consumer<",
            "Ljava/lang/Float;",
            ">;"
        }
    .end annotation
.end field

.field private webViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;

.field private webViewScrollListener:Lorg/telegram/ui/web/BotWebViewContainer$WebViewScrollListener;


# direct methods
.method public static synthetic $r8$lambda$0xXA-tfesGsfVmYkCtQ9Sk9tez0(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$9(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/ActionBar/AlertDialog;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$0yjXzIN7PcjxhlSWY_7fV0AXBoI(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_error;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onWebEventReceived$5(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_error;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$1PCFrs4SY0M8O3LxuN_X5iT8mwY(Ljava/io/File;[ILjava/lang/Runnable;)V
    .locals 2

    .line 2587
    invoke-virtual {p0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    const-wide/16 v0, 0x0

    .line 2588
    invoke-static {p0, p1, v0, v1}, Lorg/telegram/ui/Components/AnimatedFileNative;->getVideoInfo(Ljava/lang/String;[IJ)V

    .line 2589
    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$1qyV4yNdi7hA88yIyjUXChkiRi4([Ljava/lang/String;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V
    .locals 3

    const/4 v0, 0x0

    .line 2282
    aget-object v1, p0, v0

    if-nez v1, :cond_0

    return-void

    .line 2286
    :cond_0
    :try_start_0
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1}, Lorg/json/JSONObject;-><init>()V

    .line 2287
    const-string/jumbo v2, "status"

    aget-object p0, p0, v0

    invoke-virtual {v1, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2288
    const-string/jumbo p0, "phone_requested"

    invoke-static {p1, p2, p3, p0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 2290
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$6zZmFYch9MQ7bG7epOhTgH8fHSw(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$34(Ljava/lang/String;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;)V

    return-void
.end method

.method public static synthetic $r8$lambda$7Lk9r_2_fzNDn3CmD4UFfkS3iVY(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$28(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    return-void
.end method

.method public static synthetic $r8$lambda$8NDPmdVMVYESF97_vL5FBVZDv4w(Lorg/telegram/ui/web/BotWebViewContainer;[ZLjava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/util/List;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$55([ZLjava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/util/List;)V

    return-void
.end method

.method public static synthetic $r8$lambda$8V2lWUyaoZIqhdJas8Yl3GzNqag(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$41(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$8p07neB3AFKR34Ayg9l-QHO7GG8(Lorg/telegram/ui/web/BotWebViewContainer;ZLjava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$evaluateJs$3(ZLjava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$8rqha2ksac_Jzqm9H90maCdksR4([Ljava/lang/Runnable;Landroid/content/DialogInterface;)V
    .locals 1

    const/4 p1, 0x0

    .line 2352
    aget-object v0, p0, p1

    if-eqz v0, :cond_0

    .line 2353
    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    const/4 v0, 0x0

    .line 2354
    aput-object v0, p0, p1

    :cond_0
    return-void
.end method

.method public static synthetic $r8$lambda$A58zimWnw9sgr5RazfaT9wk2Z7o(Lorg/telegram/ui/web/BotWebViewContainer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$reload$1()V

    return-void
.end method

.method public static synthetic $r8$lambda$AvO0D7sxJYyGrH-gDMs6RLXi-UU(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p7}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$restoreStorageKey$60(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$BJI_VGyiuJvL7FYUUN0FMPZYAFc(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p6}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$22(Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    return-void
.end method

.method public static synthetic $r8$lambda$F5ikMaWrcNfUsXB12gJkNg6WGbU(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLObject;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$20(Lorg/telegram/tgnet/TLObject;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$F7VjKe3XFKtk6ZiK_vub3sr3bbE(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$47(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$GqRfJ9GoZUth5VkSQmXaRAyqouo(Lorg/telegram/ui/web/BotWebViewContainer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$48()V

    return-void
.end method

.method public static synthetic $r8$lambda$HGS9h3ajtC5j4N1YZmg75AV3hfQ(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/Runnable;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$30([Ljava/lang/Runnable;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/ActionBar/AlertDialog;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$HknNJkVURwhtMVCsBnHTRB2mxZk(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$Document;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$40(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$Document;)V

    return-void
.end method

.method public static synthetic $r8$lambda$I_aUGAFGzhVOfath5hs-cjCzZjk(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/io/File;Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$37(Ljava/io/File;Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$J9qr9gLAdQ5wU81HYfMsniA0JKs(Lorg/telegram/ui/web/BotWebViewContainer;Landroidx/core/util/Consumer;[Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$runWithPermissions$0(Landroidx/core/util/Consumer;[Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$JrH7COzs1n74R4yEvdR0iM7Rffs(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Ljava/lang/String;Ljava/util/ArrayList;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$50(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method public static synthetic $r8$lambda$KSXQ2maG6p6gPJnCo9G2ihQTogw(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$Updates;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$57(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$Updates;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$LGRuy-ph4w60BFo6oRQxQ0YEEcc(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$User;Lorg/telegram/tgnet/TLRPC$Updates;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$52(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$User;Lorg/telegram/tgnet/TLRPC$Updates;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$MWiOlblEDaV-Pj5-Sa8foj8qi98(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$14(Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$MqavG0gbGiHEGu6PudB-aunDB54(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p6}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$23(Ljava/lang/String;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$NztNqmje239wA8btEO2bgwzWicc(Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 2159
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    return-void
.end method

.method public static synthetic $r8$lambda$PVzAyGg1RLqSJpyp_NnI82Cdnm4(Lorg/telegram/ui/web/BotWebViewContainer;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$21(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$PyuWomtfMrQMsnfwR5r-X-BmhO0(Lorg/telegram/ui/web/BotWebViewContainer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$new$61()V

    return-void
.end method

.method public static synthetic $r8$lambda$QfJ0rjH9pLPfBrn5ybx6IsBj5r4(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$33(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$SW-TVE8uKPxMUjyKC90X2Zgo6JM(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$38(Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V

    return-void
.end method

.method public static synthetic $r8$lambda$UoVc4DYS1eoKDCTnpnGxYcHim-4(Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Ljava/util/ArrayList;)V
    .locals 0

    if-eqz p0, :cond_0

    .line 2894
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;->container:Lorg/telegram/ui/web/BotWebViewContainer;

    if-eqz p0, :cond_0

    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz p0, :cond_0

    .line 2895
    invoke-interface {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onSharedTo(Ljava/util/ArrayList;)V

    :cond_0
    return-void
.end method

.method public static synthetic $r8$lambda$VAwKPo-PAUrbx1DWtLBTjFPsMzY(Lorg/telegram/ui/web/BotWebViewContainer;[ILjava/io/File;Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p6}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$35([ILjava/io/File;Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$WM1cflUWlwXm_J8ggOi2TM50gm4(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$KeyboardButton;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$59(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$KeyboardButton;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$WsDXKJRSSs-BX-7vtPIIg6jMH7M(Lorg/telegram/ui/web/BotWebViewContainer;[ZLjava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/DialogsActivity;Ljava/util/ArrayList;Ljava/lang/CharSequence;ZZIILorg/telegram/ui/TopicsFragment;)Z
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p12}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$58([ZLjava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/DialogsActivity;Ljava/util/ArrayList;Ljava/lang/CharSequence;ZZIILorg/telegram/ui/TopicsFragment;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$Y3ua4OBDuREC2Q91YS_YpuBsFxU(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/Runnable;Landroid/content/DialogInterface;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$showDialog$62(Ljava/lang/Runnable;Landroid/content/DialogInterface;)V

    return-void
.end method

.method public static synthetic $r8$lambda$YaOp6gEY4IbSd4_Nzeq__GWre7s(Lorg/telegram/ui/web/BotWebViewContainer;[ZLorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Landroid/content/DialogInterface;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$56([ZLorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Landroid/content/DialogInterface;)V

    return-void
.end method

.method public static synthetic $r8$lambda$YetVadcB9fdX70tFhwsqwfMmGmg([Ljava/lang/String;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V
    .locals 3

    .line 2164
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 2165
    const-string/jumbo v1, "status"

    const/4 v2, 0x0

    aget-object p0, p0, v2

    invoke-virtual {v0, v1, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2166
    const-string/jumbo p0, "write_access_requested"

    invoke-static {p1, p2, p3, p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 2168
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ZRjCv3V3Qax8gXmiIgn-qLA3x8w(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Landroid/content/DialogInterface;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$12(Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Landroid/content/DialogInterface;)V

    return-void
.end method

.method public static synthetic $r8$lambda$_lVrYXF0nZPDbQHa_1TELn0pyAM(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onWebEventReceived$6(Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$aMEF3aDzXzgqDn-1MUwGV8sBllE(Lorg/telegram/ui/web/BotWebViewContainer;ZDLjava/lang/String;D)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p6}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$51(ZDLjava/lang/String;D)V

    return-void
.end method

.method public static synthetic $r8$lambda$b7gZsdIdfwuiB2Ah-9ZWu2gPUgo(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$10(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/ActionBar/AlertDialog;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$e-zt8Hi95Mzku_2MsScFPNSrfvw(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/String;Lorg/telegram/ui/ActionBar/AlertDialog;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$16([Ljava/lang/String;Lorg/telegram/ui/ActionBar/AlertDialog;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$e6enTNZ47xTJMNJO9nplAg-gIFM(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$39(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;)V

    return-void
.end method

.method public static synthetic $r8$lambda$fMzyHdeDmfgvULRkWJty0ZkYkxk(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLRPC$TL_error;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;Lorg/telegram/tgnet/TLObject;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$13(Lorg/telegram/tgnet/TLRPC$TL_error;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;Lorg/telegram/tgnet/TLObject;)V

    return-void
.end method

.method public static synthetic $r8$lambda$hHXmpWTLgly3M84Bh7S8P-Il0mQ(Lorg/telegram/ui/web/BotWebViewContainer;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$24(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    return-void
.end method

.method public static synthetic $r8$lambda$kMoqJtZZzJCsv_34FZMnkNRyF0E(Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 2279
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    return-void
.end method

.method public static synthetic $r8$lambda$kRmiOPggHs0ri1qY9Fv2bOXMbFI(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$45(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V

    return-void
.end method

.method public static synthetic $r8$lambda$kUPvh4nnoofKjwICwTePg40h3AE(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/String;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$17([Ljava/lang/String;Lorg/telegram/ui/ActionBar/AlertDialog;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$kui9aoopQkD1JxPGdKz_aWMxegI(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/String;ZILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p7}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$25([Ljava/lang/String;ZILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/ActionBar/AlertDialog;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$kzVCxsSbWX2YAXvSwYoSp0uUrug(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/tgnet/TLRPC$User;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$53(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/tgnet/TLRPC$User;)V

    return-void
.end method

.method public static synthetic $r8$lambda$oGp1DOAVggxjGKoJroyWsj6ZzSI(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$11(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/ActionBar/AlertDialog;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$rkG1IvKf4jhz0YFmTMpEIA-Vvi0(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    .locals 1

    .line 1274
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->bot:Z

    if-eqz v0, :cond_1

    invoke-static {p0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->-$$Nest$fgetbotWebViewContainer(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)Lorg/telegram/ui/web/BotWebViewContainer;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-static {p0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->-$$Nest$fgetbotWebViewContainer(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)Lorg/telegram/ui/web/BotWebViewContainer;

    move-result-object v0

    .line 1275
    invoke-direct {v0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestContextCurrent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Z

    move-result p1

    if-nez p1, :cond_1

    .line 1276
    :cond_0
    new-instance p0, Ljava/lang/StringBuilder;

    const-string/jumbo p1, "notifyEvent "

    invoke-direct {p0, p1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " dropped after document change"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    return-void

    .line 1279
    :cond_1
    new-instance p1, Ljava/lang/StringBuilder;

    const-string/jumbo v0, "window.Telegram.WebView.receiveEvent(\'"

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, "\', "

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p2, ");"

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->evaluateJS(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$scuqqZoPDSoL06nEiRnY-fcJVM8(Lorg/telegram/ui/web/BotWebViewContainer;Landroid/webkit/WebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onBotWebMessage$8(Landroid/webkit/WebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$tmWmT3eWq1tUXEB-3R05pu_8d0s(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$loadUrl$2(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$u3erE8n02i9w5UrUHHieWXEu87A(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/Runnable;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$31([Ljava/lang/Runnable;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/ActionBar/AlertDialog;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$ueKMEashOQsH5Jm2BwK_4Z1JZ20(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$Updates;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$54(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$Updates;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ui6KwASw-ccztVa-yJe1Os-VARQ(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLObject;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$46(Lorg/telegram/tgnet/TLObject;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$vBDEnGumSpvVLyyyzo2YGiz63Bk(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/Boolean;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$43(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/Boolean;)V

    return-void
.end method

.method public static synthetic $r8$lambda$v_1LcymqnDRkLB1FjL3K34MvFcQ(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLObject;[Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_error;Lorg/telegram/ui/ActionBar/AlertDialog;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$15(Lorg/telegram/tgnet/TLObject;[Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_error;Lorg/telegram/ui/ActionBar/AlertDialog;)V

    return-void
.end method

.method public static synthetic $r8$lambda$wmobJxTSHzowRqeC-JvCc6breNc(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$29(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$xHlzo8XElH7c8w9-gsDckDJI5XE(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/json/JSONObject;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$42(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/json/JSONObject;)V

    return-void
.end method

.method public static synthetic $r8$lambda$x_5pHPcuK_VVNirD7rBSB3842Iw(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;Landroid/webkit/WebView;Landroidx/webkit/WebMessageCompat;Landroid/net/Uri;ZLandroidx/webkit/JavaScriptReplyProxy;)V
    .locals 0

    .line 1450
    invoke-static {p0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->-$$Nest$fgetbotWebViewContainer(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)Lorg/telegram/ui/web/BotWebViewContainer;

    move-result-object p4

    if-ne p1, p0, :cond_0

    if-eqz p4, :cond_0

    .line 1452
    invoke-direct {p4, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->onBotWebMessage(Landroid/webkit/WebView;Landroidx/webkit/WebMessageCompat;Landroid/net/Uri;)V

    :cond_0
    return-void
.end method

.method public static synthetic $r8$lambda$z8xOhAOilDkdM4BRRCxQdZxjT28(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/json/JSONObject;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->lambda$onEventReceived$44(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/json/JSONObject;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetbotUser(Lorg/telegram/ui/web/BotWebViewContainer;)Lorg/telegram/tgnet/TLRPC$User;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcurrentAccount(Lorg/telegram/ui/web/BotWebViewContainer;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetdelegate(Lorg/telegram/ui/web/BotWebViewContainer;)Lorg/telegram/ui/web/BotWebViewContainer$Delegate;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetdocumentGeneration(Lorg/telegram/ui/web/BotWebViewContainer;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->documentGeneration:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetflickerView(Lorg/telegram/ui/web/BotWebViewContainer;)Lorg/telegram/ui/Components/BackupImageView;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetisFlickeringCenter(Lorg/telegram/ui/web/BotWebViewContainer;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isFlickeringCenter:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetlastClickMs(Lorg/telegram/ui/web/BotWebViewContainer;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetlastQrText(Lorg/telegram/ui/web/BotWebViewContainer;)Ljava/lang/String;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastQrText:Ljava/lang/String;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmFilePathCallback(Lorg/telegram/ui/web/BotWebViewContainer;)Landroid/webkit/ValueCallback;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->mFilePathCallback:Landroid/webkit/ValueCallback;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetparentActivity(Lorg/telegram/ui/web/BotWebViewContainer;)Landroid/app/Activity;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->parentActivity:Landroid/app/Activity;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetresourcesProvider(Lorg/telegram/ui/web/BotWebViewContainer;)Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetwebViewProgressListener(Lorg/telegram/ui/web/BotWebViewContainer;)Landroidx/core/util/Consumer;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewProgressListener:Landroidx/core/util/Consumer;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fputdocumentGeneration(Lorg/telegram/ui/web/BotWebViewContainer;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->documentGeneration:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputhasQRPending(Lorg/telegram/ui/web/BotWebViewContainer;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->hasQRPending:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputhasUserPermissions(Lorg/telegram/ui/web/BotWebViewContainer;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->hasUserPermissions:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputlastClickMs(Lorg/telegram/ui/web/BotWebViewContainer;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputmFilePathCallback(Lorg/telegram/ui/web/BotWebViewContainer;Landroid/webkit/ValueCallback;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->mFilePathCallback:Landroid/webkit/ValueCallback;

    return-void
.end method

.method public static bridge synthetic -$$Nest$mcreateRequestContext(Lorg/telegram/ui/web/BotWebViewContainer;)Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->createRequestContext()Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$misTrustedDocumentCurrent(Lorg/telegram/ui/web/BotWebViewContainer;)Z
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->isTrustedDocumentCurrent()Z

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$misVerifyingAge(Lorg/telegram/ui/web/BotWebViewContainer;)Z
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->isVerifyingAge()Z

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$mnotifyEvent(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$monEventReceived(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->onEventReceived(Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$monOpenUri(Lorg/telegram/ui/web/BotWebViewContainer;Landroid/net/Uri;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->onOpenUri(Landroid/net/Uri;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$monWebEventReceived(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->onWebEventReceived(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mopenQrScanActivity(Lorg/telegram/ui/web/BotWebViewContainer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->openQrScanActivity()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mrunWithPermissions(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/String;Landroidx/core/util/Consumer;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->runWithPermissions([Ljava/lang/String;Landroidx/core/util/Consumer;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$sfgettags()I
    .locals 1

    .line 0
    sget v0, Lorg/telegram/ui/web/BotWebViewContainer;->tags:I

    return v0
.end method

.method public static bridge synthetic -$$Nest$sfputtags(I)V
    .locals 0

    .line 0
    sput p0, Lorg/telegram/ui/web/BotWebViewContainer;->tags:I

    return-void
.end method

.method public static bridge synthetic -$$Nest$smtonsite2magic(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->tonsite2magic(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;IZ)V
    .locals 5

    .line 265
    invoke-direct {p0, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 197
    new-instance v0, Lorg/telegram/ui/Components/voip/CellFlickerDrawable;

    invoke-direct {v0}, Lorg/telegram/ui/Components/voip/CellFlickerDrawable;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerDrawable:Lorg/telegram/ui/Components/voip/CellFlickerDrawable;

    .line 206
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_addButton:I

    invoke-direct {p0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result v2

    iput v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastButtonColor:I

    .line 207
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_buttonText:I

    invoke-direct {p0, v2}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result v3

    iput v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastButtonTextColor:I

    .line 208
    const-string v3, ""

    iput-object v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastButtonText:Ljava/lang/String;

    .line 211
    invoke-direct {p0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result v1

    iput v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonColor:I

    .line 212
    invoke-direct {p0, v2}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result v1

    iput v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonTextColor:I

    .line 213
    iput-object v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonText:Ljava/lang/String;

    .line 214
    iput-object v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonPosition:Ljava/lang/String;

    .line 217
    sget v1, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    iput v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    const/4 v1, -0x1

    .line 924
    iput v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->forceHeight:I

    .line 3338
    new-instance v2, Landroid/graphics/Rect;

    const/4 v3, 0x0

    invoke-direct {v2, v3, v3, v3, v3}, Landroid/graphics/Rect;-><init>(IIII)V

    iput-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastInsets:Landroid/graphics/Rect;

    .line 3339
    iput v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastInsetsTopMargin:I

    .line 3414
    new-instance v2, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda1;

    invoke-direct {v2, p0}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;)V

    iput-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->notifyLocationChecked:Ljava/lang/Runnable;

    .line 3417
    iput v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastDialogType:I

    .line 3418
    iput v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->shownDialogsCount:I

    .line 5481
    sget v2, Lorg/telegram/ui/web/BotWebViewContainer;->tags:I

    add-int/lit8 v4, v2, 0x1

    sput v4, Lorg/telegram/ui/web/BotWebViewContainer;->tags:I

    iput v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->tag:I

    .line 266
    iput-boolean p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    .line 267
    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 269
    const-string p2, "created new webview container"

    invoke-virtual {p0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 271
    instance-of p2, p1, Landroid/app/Activity;

    if-eqz p2, :cond_0

    .line 272
    move-object p2, p1

    check-cast p2, Landroid/app/Activity;

    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->parentActivity:Landroid/app/Activity;

    .line 275
    :cond_0
    iput-boolean v3, v0, Lorg/telegram/ui/Components/voip/CellFlickerDrawable;->drawFrame:Z

    const/16 p2, 0x99

    const/16 p4, 0xcc

    .line 276
    invoke-virtual {v0, p3, p2, p4}, Lorg/telegram/ui/Components/voip/CellFlickerDrawable;->setColors(III)V

    .line 277
    new-instance p2, Lorg/telegram/ui/web/BotWebViewContainer$1;

    invoke-direct {p2, p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$1;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Landroid/content/Context;)V

    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    .line 307
    new-instance p3, Landroid/graphics/PorterDuffColorFilter;

    sget p4, Lorg/telegram/ui/ActionBar/Theme;->key_bot_loadingIcon:I

    invoke-direct {p0, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result p4

    iput p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewColor:I

    sget-object v0, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {p3, p4, v0}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {p2, p3}, Lorg/telegram/ui/Components/BackupImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 308
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p2}, Lorg/telegram/ui/Components/BackupImageView;->getImageReceiver()Lorg/telegram/messenger/ImageReceiver;

    move-result-object p2

    const/4 p3, 0x1

    invoke-virtual {p2, p3}, Lorg/telegram/messenger/ImageReceiver;->setAspectFit(Z)V

    .line 309
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    const/16 p4, 0x30

    const/4 v0, -0x2

    invoke-static {v1, v0, p4}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object p4

    invoke-virtual {p0, p2, p4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 311
    new-instance p2, Landroid/widget/TextView;

    invoke-direct {p2, p1}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailableText:Landroid/widget/TextView;

    .line 312
    sget p1, Lorg/telegram/messenger/R$string;->BotWebViewNotAvailablePlaceholder:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 313
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailableText:Landroid/widget/TextView;

    sget p2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteGrayText:I

    invoke-direct {p0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result p2

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setTextColor(I)V

    .line 314
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailableText:Landroid/widget/TextView;

    const/high16 p2, 0x41700000    # 15.0f

    invoke-virtual {p1, p3, p2}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 315
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailableText:Landroid/widget/TextView;

    const/16 p2, 0x11

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setGravity(I)V

    .line 316
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailableText:Landroid/widget/TextView;

    const/16 p3, 0x8

    invoke-virtual {p1, p3}, Landroid/view/View;->setVisibility(I)V

    const/high16 p1, 0x41800000    # 16.0f

    .line 317
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    .line 318
    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailableText:Landroid/widget/TextView;

    invoke-virtual {p3, p1, p1, p1, p1}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 319
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailableText:Landroid/widget/TextView;

    invoke-static {v1, v0, p2}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 321
    invoke-virtual {p0, v3}, Landroid/view/View;->setFocusable(Z)V

    return-void
.end method

.method private buildThemeParams()Lorg/json/JSONObject;
    .locals 2

    .line 3491
    :try_start_0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/4 v0, 0x1

    invoke-static {p0, v0}, Lorg/telegram/ui/bots/BotWebViewSheet;->makeThemeParams(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)Lorg/json/JSONObject;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 3493
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    const-string/jumbo v1, "theme_params"

    invoke-virtual {v0, v1, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    .line 3496
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 3498
    :cond_0
    new-instance p0, Lorg/json/JSONObject;

    invoke-direct {p0}, Lorg/json/JSONObject;-><init>()V

    return-object p0
.end method

.method private static capitalizeFirst(Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    if-nez p0, :cond_0

    .line 428
    const-string p0, ""

    return-object p0

    .line 429
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    const/4 v1, 0x1

    if-gt v0, v1, :cond_1

    invoke-virtual {p0}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 430
    :cond_1
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const/4 v2, 0x0

    invoke-virtual {p0, v2, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private checkPermissions([Ljava/lang/String;)Z
    .locals 5

    .line 736
    array-length v0, p1

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    if-ge v2, v0, :cond_1

    aget-object v3, p1, v2

    .line 737
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v4

    invoke-virtual {v4, v3}, Landroid/content/Context;->checkSelfPermission(Ljava/lang/String;)I

    move-result v3

    if-eqz v3, :cond_0

    return v1

    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x1

    return p0
.end method

.method private clearStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 3

    .line 3316
    const-string v0, "error"

    const-string/jumbo v1, "req_id"

    if-eqz p2, :cond_1

    iget-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v2, :cond_0

    goto :goto_0

    .line 3320
    :cond_0
    :try_start_0
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2, p3}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 3321
    invoke-virtual {v2, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    .line 3330
    :try_start_1
    invoke-virtual {p2}, Lorg/telegram/ui/bots/BotStorage;->clear()V
    :try_end_1
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_0

    .line 3335
    invoke-static {v1, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p4, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :catch_0
    move-exception p2

    .line 3332
    invoke-virtual {p2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p2

    invoke-static {v1, p3, v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :catch_1
    move-exception p2

    .line 3323
    invoke-static {p2}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 3324
    const-string p2, ""

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p3

    if-nez p3, :cond_1

    .line 3325
    const-string p3, "UNKNOWN_ERROR"

    invoke-static {v1, p2, v0, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private createBiometry()V
    .locals 4

    .line 3376
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v0, :cond_0

    return-void

    .line 3379
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    if-nez v0, :cond_1

    .line 3380
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    iget v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    iget-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v2, v2, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-static {v0, v1, v2, v3}, Lorg/telegram/ui/bots/BotBiometry;->get(Landroid/content/Context;IJ)Lorg/telegram/ui/bots/BotBiometry;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    return-void

    .line 3382
    :cond_1
    invoke-virtual {v0}, Lorg/telegram/ui/bots/BotBiometry;->load()V

    return-void
.end method

.method private createRequestContext()Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;
    .locals 8

    .line 1234
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;

    iget-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    iget-wide v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->documentGeneration:J

    iget-boolean v5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->restrictBridgeToOrigin:Z

    iget-object v6, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    const/4 v7, 0x0

    move-object v1, p0

    invoke-direct/range {v0 .. v7}, Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;JZLjava/lang/String;Lorg/telegram/ui/web/BotWebViewContainer-IA;)V

    return-object v0
.end method

.method private error(Ljava/lang/String;)V
    .locals 1

    .line 3410
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {p0, v0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget v0, Lorg/telegram/messenger/R$raw;->error:I

    invoke-virtual {p0, v0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method private getColor(I)I
    .locals 0

    .line 3502
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    if-eqz p0, :cond_0

    .line 3503
    invoke-interface {p0, p1}, Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;->getColor(I)I

    move-result p0

    return p0

    .line 3505
    :cond_0
    invoke-static {p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p0

    return p0
.end method

.method public static getMainButtonRippleColor(I)I
    .locals 4

    .line 602
    invoke-static {p0}, Landroidx/core/graphics/ColorUtils;->calculateLuminance(I)D

    move-result-wide v0

    const-wide v2, 0x3fd3333340000000L    # 0.30000001192092896

    cmpl-double p0, v0, v2

    if-ltz p0, :cond_0

    const/high16 p0, 0x12000000

    return p0

    :cond_0
    const p0, 0x16ffffff

    return p0
.end method

.method public static getMainButtonRippleDrawable(I)Landroid/graphics/drawable/Drawable;
    .locals 1

    .line 606
    invoke-static {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->getMainButtonRippleColor(I)I

    move-result v0

    invoke-static {p0, v0}, Lorg/telegram/ui/ActionBar/Theme;->createSelectorWithBackgroundDrawable(II)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    return-object p0
.end method

.method public static getOriginHost(Ljava/lang/String;)Ljava/lang/String;
    .locals 4

    const/4 v0, 0x0

    if-eqz p0, :cond_5

    .line 1519
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 1520
    :cond_0
    invoke-static {p0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p0

    .line 1521
    invoke-virtual {p0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v1

    .line 1522
    invoke-virtual {p0}, Landroid/net/Uri;->getHost()Ljava/lang/String;

    move-result-object v2

    .line 1523
    invoke-virtual {p0}, Landroid/net/Uri;->getPort()I

    move-result p0

    if-eqz v1, :cond_5

    if-nez v2, :cond_1

    goto :goto_0

    .line 1525
    :cond_1
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 1526
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1527
    const-string v3, "://"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1528
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/4 v2, -0x1

    if-eq p0, v2, :cond_4

    .line 1529
    const-string v2, "http"

    .line 1530
    invoke-virtual {v1, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_2

    const/16 v2, 0x50

    if-eq p0, v2, :cond_4

    :cond_2
    const-string v2, "https"

    .line 1531
    invoke-virtual {v1, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_3

    const/16 v1, 0x1bb

    if-eq p0, v1, :cond_4

    .line 1533
    :cond_3
    const-string v1, ":"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1534
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 1536
    :cond_4
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_5
    :goto_0
    return-object v0
.end method

.method private getStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 10

    .line 3225
    const-string v0, "KEY_INVALID"

    const-string v1, "error"

    const-string/jumbo v2, "req_id"

    if-eqz p2, :cond_3

    iget-object v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v3, :cond_0

    goto :goto_1

    .line 3229
    :cond_0
    :try_start_0
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3, p3}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 3230
    invoke-virtual {v3, v2}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_2

    .line 3240
    :try_start_1
    const-string p3, "key"

    invoke-virtual {v3, p3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    if-nez p3, :cond_1

    .line 3246
    invoke-static {v2, v5, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3250
    :cond_1
    :try_start_2
    invoke-virtual {p2, p3}, Lorg/telegram/ui/bots/BotStorage;->getKey(Ljava/lang/String;)Landroid/util/Pair;

    move-result-object p3

    .line 3251
    iget-boolean p2, p2, Lorg/telegram/ui/bots/BotStorage;->secured:Z

    if-eqz p2, :cond_2

    iget-object v7, p3, Landroid/util/Pair;->first:Ljava/lang/Object;

    if-nez v7, :cond_2

    .line 3252
    const-string/jumbo v4, "req_id"

    const-string/jumbo v6, "value"

    const-string v8, "can_restore"

    iget-object v9, p3, Landroid/util/Pair;->second:Ljava/lang/Object;

    invoke-static/range {v4 .. v9}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p4, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :catch_0
    move-exception v0

    move-object p2, v0

    goto :goto_0

    .line 3254
    :cond_2
    const-string/jumbo p2, "value"

    iget-object p3, p3, Landroid/util/Pair;->first:Ljava/lang/Object;

    invoke-static {v2, v5, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p4, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_2
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_0

    return-void

    .line 3257
    :goto_0
    invoke-virtual {p2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p2

    invoke-static {v2, v5, v1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3242
    :catch_1
    invoke-static {v2, v5, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :catch_2
    move-exception v0

    move-object p2, v0

    .line 3232
    invoke-static {p2}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 3233
    const-string p2, ""

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p3

    if-nez p3, :cond_3

    .line 3234
    const-string p3, "UNKNOWN_ERROR"

    invoke-static {v2, p2, v1, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    :cond_3
    :goto_1
    return-void
.end method

.method private ignoreDialog(I)Z
    .locals 7

    .line 3422
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    return v1

    .line 3425
    :cond_0
    iget-wide v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->blockedDialogsUntil:J

    const-wide/16 v4, 0x0

    cmp-long v0, v2, v4

    if-lez v0, :cond_1

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    iget-wide v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->blockedDialogsUntil:J

    cmp-long v0, v2, v4

    if-gez v0, :cond_1

    return v1

    .line 3428
    :cond_1
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastDialogType:I

    const/4 v2, 0x0

    if-ne v0, p1, :cond_2

    iget p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->shownDialogsCount:I

    const/4 v0, 0x3

    if-le p1, v0, :cond_2

    .line 3429
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    const-wide/16 v5, 0xbb8

    add-long/2addr v3, v5

    iput-wide v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->blockedDialogsUntil:J

    .line 3430
    iput v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->shownDialogsCount:I

    return v1

    :cond_2
    return v2
.end method

.method private isRequestContextCurrent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Z
    .locals 4

    if-eqz p1, :cond_1

    .line 1238
    invoke-static {p1}, Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;->-$$Nest$fgetowner(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Lorg/telegram/ui/web/BotWebViewContainer;

    move-result-object v0

    if-ne v0, p0, :cond_1

    invoke-static {p1}, Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;->-$$Nest$fgetwebView(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-ne v0, v1, :cond_1

    invoke-static {p1}, Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;->-$$Nest$fgetdocumentGeneration(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)J

    move-result-wide v0

    iget-wide v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->documentGeneration:J

    cmp-long v0, v0, v2

    if-nez v0, :cond_1

    invoke-static {p1}, Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;->-$$Nest$fgetrestrictToOrigin(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Z

    move-result v0

    iget-boolean v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->restrictBridgeToOrigin:Z

    if-ne v0, v1, :cond_1

    invoke-static {p1}, Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;->-$$Nest$fgetrestrictToOrigin(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {p1}, Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;->-$$Nest$fgetorigin(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Ljava/lang/String;

    move-result-object v0

    .line 1244
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {p1}, Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;->-$$Nest$fgetorigin(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    .line 1245
    invoke-static {v0, v1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {p1}, Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;->-$$Nest$fgetorigin(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Ljava/lang/String;

    move-result-object p1

    .line 1246
    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->getOriginHost()Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_1

    :cond_0
    const/4 p0, 0x1

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method public static isTonsite(Landroid/net/Uri;)Z
    .locals 3

    .line 3847
    const-string/jumbo v0, "tonsite"

    invoke-virtual {p0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    return v1

    .line 3850
    :cond_0
    invoke-virtual {p0}, Landroid/net/Uri;->getAuthority()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_1

    .line 3851
    invoke-virtual {p0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v2

    if-nez v2, :cond_1

    .line 3852
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v2, "http://"

    invoke-direct {v0, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p0

    invoke-virtual {p0}, Landroid/net/Uri;->getAuthority()Ljava/lang/String;

    move-result-object v0

    :cond_1
    if-eqz v0, :cond_3

    .line 3854
    const-string p0, ".ton"

    invoke-virtual {v0, p0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result p0

    if-nez p0, :cond_2

    const-string p0, ".adnl"

    invoke-virtual {v0, p0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_3

    :cond_2
    return v1

    :cond_3
    const/4 p0, 0x0

    return p0
.end method

.method public static isTonsite(Ljava/lang/String;)Z
    .locals 0

    if-eqz p0, :cond_0

    .line 3843
    invoke-static {p0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->isTonsite(Landroid/net/Uri;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private isTrustedDocumentCurrent()Z
    .locals 2

    .line 1250
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    .line 1253
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->restrictBridgeToOrigin:Z

    if-eqz v0, :cond_2

    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    .line 1254
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->getOriginHost()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_1

    goto :goto_0

    :cond_1
    return v1

    :cond_2
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private isVerifyingAge()Z
    .locals 0

    .line 5572
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->onVerifiedAge:Lorg/telegram/messenger/Utilities$Callback4;

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private synthetic lambda$evaluateJs$3(ZLjava/lang/String;)V
    .locals 0

    if-eqz p1, :cond_0

    .line 1181
    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->checkCreateWebView()V

    .line 1183
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-nez p0, :cond_1

    return-void

    .line 1186
    :cond_1
    invoke-virtual {p0, p2}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->evaluateJS(Ljava/lang/String;)V

    return-void
.end method

.method private synthetic lambda$loadUrl$2(Ljava/lang/String;)V
    .locals 3

    const/4 v0, 0x0

    .line 1069
    iput-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isPageLoaded:Z

    const-wide/16 v1, 0x0

    .line 1070
    iput-wide v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 1071
    iput-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->hasUserPermissions:Z

    .line 1072
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->mUrl:Ljava/lang/String;

    .line 1073
    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->checkCreateWebView()V

    .line 1074
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v0, :cond_0

    .line 1075
    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->onResume()V

    .line 1076
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {v0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->loadUrl(Ljava/lang/String;)V

    .line 1078
    :cond_0
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->updateKeyboardFocusable()V

    return-void
.end method

.method private synthetic lambda$new$61()V
    .locals 3

    .line 3415
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->locationRequestContext:Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;

    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->location:Lorg/telegram/ui/bots/BotLocation;

    invoke-virtual {v1}, Lorg/telegram/ui/bots/BotLocation;->checkObject()Lorg/json/JSONObject;

    move-result-object v1

    const-string v2, "location_checked"

    invoke-direct {p0, v0, v2, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onBotWebMessage$8(Landroid/webkit/WebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    .line 1499
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-ne p1, v0, :cond_1

    .line 1500
    invoke-direct {p0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestContextCurrent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Z

    move-result p1

    if-nez p1, :cond_0

    goto :goto_0

    .line 1504
    :cond_0
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botWebViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->onEventReceived(Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V

    return-void

    .line 1501
    :cond_1
    :goto_0
    const-string/jumbo p1, "onBotWebMessage ignored after document change"

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$10(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 1

    .line 1737
    invoke-virtual {p4}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    .line 1739
    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p4

    iput-wide p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 1740
    const-string/jumbo p4, "popup_closed"

    new-instance p5, Lorg/json/JSONObject;

    invoke-direct {p5}, Lorg/json/JSONObject;-><init>()V

    const-string v0, "button_id"

    iget-object p2, p2, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->id:Ljava/lang/String;

    invoke-virtual {p5, v0, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p4, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    const/4 p0, 0x1

    .line 1741
    invoke-virtual {p3, p0}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 1743
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$11(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 1

    .line 1751
    invoke-virtual {p4}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    .line 1753
    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p4

    iput-wide p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 1754
    const-string/jumbo p4, "popup_closed"

    new-instance p5, Lorg/json/JSONObject;

    invoke-direct {p5}, Lorg/json/JSONObject;-><init>()V

    const-string v0, "button_id"

    iget-object p2, p2, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->id:Ljava/lang/String;

    invoke-virtual {p5, v0, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p4, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    const/4 p0, 0x1

    .line 1755
    invoke-virtual {p3, p0}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 1757
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$12(Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Landroid/content/DialogInterface;)V
    .locals 0

    .line 1762
    invoke-virtual {p1}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result p1

    if-nez p1, :cond_0

    .line 1763
    new-instance p1, Lorg/json/JSONObject;

    invoke-direct {p1}, Lorg/json/JSONObject;-><init>()V

    const-string/jumbo p3, "popup_closed"

    invoke-direct {p0, p2, p3, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    :cond_0
    const/4 p1, 0x0

    .line 1765
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    .line 1766
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p1

    iput-wide p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastDialogClosed:J

    return-void
.end method

.method private synthetic lambda$onEventReceived$13(Lorg/telegram/tgnet/TLRPC$TL_error;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;Lorg/telegram/tgnet/TLObject;)V
    .locals 0

    if-eqz p1, :cond_0

    .line 2024
    const-string p1, "failed"

    invoke-virtual {p0, p2, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->onInvoiceStatusUpdate(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    .line 2026
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {p0, p3, p2, p4}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppOpenInvoice(Lorg/telegram/tgnet/TLRPC$InputInvoice;Ljava/lang/String;Lorg/telegram/tgnet/TLObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$14(Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 6

    .line 2022
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda39;

    move-object v1, p0

    move-object v3, p1

    move-object v4, p2

    move-object v5, p3

    move-object v2, p4

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda39;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLRPC$TL_error;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;Lorg/telegram/tgnet/TLObject;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$15(Lorg/telegram/tgnet/TLObject;[Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_error;Lorg/telegram/ui/ActionBar/AlertDialog;)V
    .locals 2

    if-eqz p1, :cond_0

    .line 2147
    const-string v0, "allowed"

    const/4 v1, 0x0

    aput-object v0, p2, v1

    .line 2148
    instance-of p2, p1, Lorg/telegram/tgnet/TLRPC$Updates;

    if-eqz p2, :cond_0

    .line 2149
    iget p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {p2}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p2

    check-cast p1, Lorg/telegram/tgnet/TLRPC$Updates;

    invoke-virtual {p2, p1, v1}, Lorg/telegram/messenger/MessagesController;->processUpdates(Lorg/telegram/tgnet/TLRPC$Updates;Z)V

    :cond_0
    if-eqz p3, :cond_1

    .line 2153
    iget-object p1, p3, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->unknownError(Ljava/lang/String;)V

    .line 2155
    :cond_1
    invoke-virtual {p4}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    return-void
.end method

.method private synthetic lambda$onEventReceived$16([Ljava/lang/String;Lorg/telegram/ui/ActionBar/AlertDialog;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 6

    .line 2145
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda62;

    move-object v1, p0

    move-object v3, p1

    move-object v5, p2

    move-object v2, p3

    move-object v4, p4

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda62;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLObject;[Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_error;Lorg/telegram/ui/ActionBar/AlertDialog;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$17([Ljava/lang/String;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 2

    .line 2143
    new-instance p3, Lorg/telegram/tgnet/tl/TL_bots$allowSendMessage;

    invoke-direct {p3}, Lorg/telegram/tgnet/tl/TL_bots$allowSendMessage;-><init>()V

    .line 2144
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/MessagesController;->getInputUser(Lorg/telegram/tgnet/TLRPC$User;)Lorg/telegram/tgnet/TLRPC$InputUser;

    move-result-object v0

    iput-object v0, p3, Lorg/telegram/tgnet/tl/TL_bots$allowSendMessage;->bot:Lorg/telegram/tgnet/TLRPC$InputUser;

    .line 2145
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v0

    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda61;

    invoke-direct {v1, p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda61;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/String;Lorg/telegram/ui/ActionBar/AlertDialog;)V

    invoke-virtual {v0, p3, v1}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    return-void
.end method

.method private synthetic lambda$onEventReceived$20(Lorg/telegram/tgnet/TLObject;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 2

    .line 2124
    instance-of p1, p1, Lorg/telegram/tgnet/TLRPC$TL_boolTrue;

    if-eqz p1, :cond_0

    .line 2126
    :try_start_0
    new-instance p0, Lorg/json/JSONObject;

    invoke-direct {p0}, Lorg/json/JSONObject;-><init>()V

    .line 2127
    const-string/jumbo p1, "status"

    const-string p5, "allowed"

    invoke-virtual {p0, p1, p5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2128
    const-string/jumbo p1, "write_access_requested"

    invoke-static {p2, p3, p4, p1, p0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 2130
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void

    :cond_0
    if-eqz p5, :cond_1

    .line 2134
    iget-object p1, p5, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->unknownError(Ljava/lang/String;)V

    return-void

    .line 2138
    :cond_1
    const-string p1, "cancelled"

    filled-new-array {p1}, [Ljava/lang/String;

    move-result-object p1

    .line 2139
    new-instance p5, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-direct {p5, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    sget v0, Lorg/telegram/messenger/R$string;->BotWebViewRequestWriteTitle:I

    .line 2140
    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p5, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p5

    sget v0, Lorg/telegram/messenger/R$string;->BotWebViewRequestWriteMessage:I

    .line 2141
    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p5, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p5

    sget v0, Lorg/telegram/messenger/R$string;->BotWebViewRequestAllow:I

    .line 2142
    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda57;

    invoke-direct {v1, p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda57;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/String;)V

    invoke-virtual {p5, v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p5

    sget v0, Lorg/telegram/messenger/R$string;->BotWebViewRequestDontAllow:I

    .line 2158
    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda58;

    invoke-direct {v1}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda58;-><init>()V

    invoke-virtual {p5, v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p5

    .line 2161
    invoke-virtual {p5}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object p5

    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda59;

    invoke-direct {v0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda59;-><init>([Ljava/lang/String;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    const/4 p1, 0x3

    .line 2139
    invoke-direct {p0, p1, p5, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->showDialog(ILorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/Runnable;)Z

    return-void
.end method

.method private synthetic lambda$onEventReceived$21(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 7

    .line 2123
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda47;

    move-object v1, p0

    move v3, p1

    move-object v4, p2

    move-object v5, p3

    move-object v2, p4

    move-object v6, p5

    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda47;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLObject;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$22(Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V
    .locals 2

    .line 2204
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 2205
    const-string/jumbo v1, "req_id"

    invoke-virtual {v0, v1, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2206
    instance-of p1, p2, Lorg/telegram/tgnet/TLRPC$TL_dataJSON;

    if-eqz p1, :cond_0

    .line 2207
    new-instance p1, Lorg/json/JSONTokener;

    check-cast p2, Lorg/telegram/tgnet/TLRPC$TL_dataJSON;

    iget-object p2, p2, Lorg/telegram/tgnet/TLRPC$TL_dataJSON;->data:Ljava/lang/String;

    invoke-direct {p1, p2}, Lorg/json/JSONTokener;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Lorg/json/JSONTokener;->nextValue()Ljava/lang/Object;

    move-result-object p1

    .line 2208
    const-string/jumbo p2, "result"

    invoke-virtual {v0, p2, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_0

    :catch_0
    move-exception p1

    goto :goto_1

    :cond_0
    if-eqz p3, :cond_1

    .line 2210
    const-string p1, "error"

    iget-object p2, p3, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    invoke-virtual {v0, p1, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2212
    :cond_1
    :goto_0
    const-string p1, "custom_method_invoked"

    invoke-static {p4, p5, p6, p1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 2214
    :goto_1
    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 2215
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->unknownError()V

    return-void
.end method

.method private synthetic lambda$onEventReceived$23(Ljava/lang/String;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 8

    .line 2202
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda48;

    move-object v1, p0

    move-object v2, p1

    move v5, p2

    move-object v6, p3

    move-object v7, p4

    move-object v3, p5

    move-object v4, p6

    invoke-direct/range {v0 .. v7}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda48;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$24(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V
    .locals 12

    .line 2256
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/SendMessagesHelper;->getInstance(I)Lorg/telegram/messenger/SendMessagesHelper;

    move-result-object v0

    iget v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v1

    invoke-virtual {v1}, Lorg/telegram/messenger/UserConfig;->getCurrentUser()Lorg/telegram/tgnet/TLRPC$User;

    move-result-object v2

    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v3, p0, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x1

    invoke-static/range {v2 .. v11}, Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;->of(Lorg/telegram/tgnet/TLRPC$User;JLorg/telegram/messenger/MessageObject;Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$ReplyMarkup;Ljava/util/HashMap;ZII)Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;

    move-result-object p0

    invoke-virtual {v0, p0}, Lorg/telegram/messenger/SendMessagesHelper;->sendMessage(Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;)V

    .line 2259
    :try_start_0
    new-instance p0, Lorg/json/JSONObject;

    invoke-direct {p0}, Lorg/json/JSONObject;-><init>()V

    .line 2260
    const-string/jumbo v0, "status"

    const-string/jumbo v1, "sent"

    invoke-virtual {p0, v0, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2261
    const-string/jumbo v0, "phone_requested"

    invoke-static {p1, p2, p3, v0, p0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 2263
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$25([Ljava/lang/String;ZILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 16

    move-object/from16 v0, p0

    move/from16 v1, p3

    move-object/from16 v2, p4

    move-object/from16 v3, p5

    const/4 v4, 0x0

    const/4 v5, 0x0

    .line 2251
    aput-object v5, p1, v4

    .line 2252
    invoke-virtual/range {p6 .. p6}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    .line 2267
    iget v4, v0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    if-eqz p2, :cond_0

    .line 2255
    invoke-static {v4}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v4

    iget-object v5, v0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v5, v5, Lorg/telegram/tgnet/TLRPC$User;->id:J

    new-instance v7, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda50;

    invoke-direct {v7, v0, v1, v2, v3}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda50;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v4, v5, v6, v7}, Lorg/telegram/messenger/MessagesController;->unblockPeer(JLjava/lang/Runnable;)V

    return-void

    .line 2267
    :cond_0
    invoke-static {v4}, Lorg/telegram/messenger/SendMessagesHelper;->getInstance(I)Lorg/telegram/messenger/SendMessagesHelper;

    move-result-object v4

    iget v5, v0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v5}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v5

    invoke-virtual {v5}, Lorg/telegram/messenger/UserConfig;->getCurrentUser()Lorg/telegram/tgnet/TLRPC$User;

    move-result-object v6

    iget-object v0, v0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v7, v0, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x1

    invoke-static/range {v6 .. v15}, Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;->of(Lorg/telegram/tgnet/TLRPC$User;JLorg/telegram/messenger/MessageObject;Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$ReplyMarkup;Ljava/util/HashMap;ZII)Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;

    move-result-object v0

    invoke-virtual {v4, v0}, Lorg/telegram/messenger/SendMessagesHelper;->sendMessage(Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;)V

    .line 2270
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 2271
    const-string/jumbo v4, "status"

    const-string/jumbo v5, "sent"

    invoke-virtual {v0, v4, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2272
    const-string/jumbo v4, "phone_requested"

    invoke-static {v1, v2, v3, v4, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    .line 2274
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$28(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V
    .locals 2

    .line 2316
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lorg/telegram/ui/bots/BotBiometry;->access_requested:Z

    .line 2317
    invoke-virtual {v0}, Lorg/telegram/ui/bots/BotBiometry;->save()V

    .line 2318
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyBiometryReceived(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$29(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/String;)V
    .locals 0

    .line 2335
    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_0

    .line 2336
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    const/4 p3, 0x1

    iput-boolean p3, p2, Lorg/telegram/ui/bots/BotBiometry;->access_granted:Z

    .line 2337
    invoke-virtual {p2}, Lorg/telegram/ui/bots/BotBiometry;->save()V

    .line 2339
    :cond_0
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyBiometryReceived(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$30([Ljava/lang/Runnable;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 1

    const/4 p3, 0x0

    .line 2329
    aget-object p4, p1, p3

    const/4 v0, 0x0

    if-eqz p4, :cond_0

    .line 2330
    aput-object v0, p1, p3

    .line 2332
    :cond_0
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    const/4 p3, 0x1

    iput-boolean p3, p1, Lorg/telegram/ui/bots/BotBiometry;->access_requested:Z

    .line 2333
    invoke-virtual {p1}, Lorg/telegram/ui/bots/BotBiometry;->save()V

    .line 2334
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    new-instance p3, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda41;

    invoke-direct {p3, p0, p2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda41;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {p1, v0, p3}, Lorg/telegram/ui/bots/BotBiometry;->requestToken(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback2;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$31([Ljava/lang/Runnable;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    const/4 p3, 0x0

    .line 2343
    aget-object p4, p1, p3

    if-eqz p4, :cond_0

    const/4 p4, 0x0

    .line 2344
    aput-object p4, p1, p3

    .line 2346
    :cond_0
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    const/4 p3, 0x1

    iput-boolean p3, p1, Lorg/telegram/ui/bots/BotBiometry;->access_requested:Z

    .line 2347
    iput-boolean p3, p1, Lorg/telegram/ui/bots/BotBiometry;->disabled:Z

    .line 2348
    invoke-virtual {p1}, Lorg/telegram/ui/bots/BotBiometry;->save()V

    .line 2349
    invoke-direct {p0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyBiometryReceived(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$33(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/String;)V
    .locals 2

    .line 2388
    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2389
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lorg/telegram/ui/bots/BotBiometry;->access_granted:Z

    .line 2392
    :cond_0
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 2393
    const-string/jumbo v1, "status"

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_1

    const-string p2, "authorized"

    goto :goto_0

    :cond_1
    const-string p2, "failed"

    :goto_0
    invoke-virtual {v0, v1, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2394
    const-string/jumbo p2, "token"

    invoke-virtual {v0, p2, p3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2395
    const-string p2, "biometry_auth_requested"

    invoke-direct {p0, p1, p2, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 2397
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$34(Ljava/lang/String;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;)V
    .locals 2

    .line 2436
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 2437
    const-string/jumbo v1, "status"

    invoke-virtual {p3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p3

    if-eqz p3, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_0

    const-string/jumbo p1, "removed"

    goto :goto_0

    :cond_0
    const-string/jumbo p1, "updated"

    goto :goto_0

    :cond_1
    const-string p1, "failed"

    :goto_0
    invoke-virtual {v0, v1, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2438
    const-string p1, "biometry_token_updated"

    invoke-direct {p0, p2, p1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 2440
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$35([ILjava/io/File;Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 24

    const/16 v18, 0x4

    .line 2530
    aget v0, p1, v18

    const/16 v19, 0x2

    const/4 v2, 0x0

    if-lez v0, :cond_4

    const/4 v0, 0x1

    .line 2532
    aget v3, p1, v0

    .line 2533
    aget v4, p1, v19

    .line 2535
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->getPhotoSize()I

    move-result v0

    if-le v3, v0, :cond_0

    .line 2536
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->getPhotoSize()I

    move-result v0

    move v15, v0

    goto :goto_0

    :cond_0
    move v15, v3

    .line 2538
    :goto_0
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->getPhotoSize()I

    move-result v0

    if-le v4, v0, :cond_1

    .line 2539
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->getPhotoSize()I

    move-result v0

    move/from16 v16, v0

    goto :goto_1

    :cond_1
    move/from16 v16, v4

    .line 2541
    :goto_1
    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    const-string v5, "jpg"

    invoke-static {v0, v5}, Lorg/telegram/ui/Stories/recorder/StoryEntry;->makeCacheFile(ILjava/lang/String;)Ljava/io/File;

    move-result-object v0

    move-object v5, v2

    .line 2542
    new-instance v2, Lorg/telegram/ui/Components/AnimatedFileDrawable;

    sget v13, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    const/4 v14, 0x1

    const/16 v17, 0x0

    move v6, v4

    const/4 v4, 0x1

    move-object v8, v5

    move v7, v6

    const-wide/16 v5, 0x0

    move v9, v7

    const/4 v7, 0x0

    move-object v10, v8

    const/4 v8, 0x0

    move v11, v9

    const/4 v9, 0x0

    move-object v12, v10

    const/4 v10, 0x0

    move/from16 v20, v11

    move-object/from16 v21, v12

    const-wide/16 v11, 0x0

    move/from16 v22, v3

    move/from16 v23, v20

    move-object/from16 v1, v21

    move-object/from16 v3, p2

    invoke-direct/range {v2 .. v17}, Lorg/telegram/ui/Components/AnimatedFileDrawable;-><init>(Ljava/io/File;ZJILorg/telegram/tgnet/TLRPC$Document;Lorg/telegram/messenger/ImageLocation;Ljava/lang/Object;JIZIILorg/telegram/messenger/utils/BitmapsCache$CacheOptions;)V

    .line 2543
    invoke-virtual {v2, v1}, Lorg/telegram/ui/Components/AnimatedFileDrawable;->getFirstFrame(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object v4

    .line 2544
    invoke-virtual {v2}, Lorg/telegram/ui/Components/AnimatedFileDrawable;->recycle()V

    if-eqz v4, :cond_2

    .line 2547
    :try_start_0
    sget-object v2, Landroid/graphics/Bitmap$CompressFormat;->JPEG:Landroid/graphics/Bitmap$CompressFormat;

    new-instance v5, Ljava/io/FileOutputStream;

    invoke-direct {v5, v0}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    const/16 v6, 0x50

    invoke-virtual {v4, v2, v6, v5}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    :catch_0
    move-exception v0

    .line 2549
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    move-object v2, v1

    goto :goto_3

    :cond_2
    :goto_2
    move-object v2, v0

    :goto_3
    if-nez v2, :cond_3

    move-object v2, v1

    goto :goto_4

    .line 2553
    :cond_3
    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v2

    :goto_4
    aget v0, p1, v18

    int-to-long v4, v0

    invoke-static {v3, v2, v4, v5}, Lorg/telegram/ui/Stories/recorder/StoryEntry;->fromVideoShoot(Ljava/io/File;Ljava/lang/String;J)Lorg/telegram/ui/Stories/recorder/StoryEntry;

    move-result-object v0

    move/from16 v2, v22

    .line 2554
    iput v2, v0, Lorg/telegram/ui/Stories/recorder/StoryEntry;->width:I

    move/from16 v9, v23

    .line 2555
    iput v9, v0, Lorg/telegram/ui/Stories/recorder/StoryEntry;->height:I

    .line 2556
    invoke-virtual {v0}, Lorg/telegram/ui/Stories/recorder/StoryEntry;->setupMatrix()V

    goto :goto_5

    :cond_4
    move-object/from16 v3, p2

    move-object v1, v2

    .line 2558
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->getImageOrientation(Ljava/io/File;)Landroid/util/Pair;

    move-result-object v0

    .line 2559
    iget-object v0, v0, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-static {v3, v0}, Lorg/telegram/ui/Stories/recorder/StoryEntry;->fromPhotoShoot(Ljava/io/File;I)Lorg/telegram/ui/Stories/recorder/StoryEntry;

    move-result-object v0

    .line 2561
    :goto_5
    iget v2, v0, Lorg/telegram/ui/Stories/recorder/StoryEntry;->width:I

    const-wide/16 v3, 0x1f4

    if-lez v2, :cond_5

    iget v2, v0, Lorg/telegram/ui/Stories/recorder/StoryEntry;->height:I

    if-gtz v2, :cond_6

    :cond_5
    move-object/from16 v1, p3

    goto :goto_7

    :cond_6
    move-object/from16 v2, p4

    if-eqz v2, :cond_7

    .line 2566
    iput-object v2, v0, Lorg/telegram/ui/Stories/recorder/StoryEntry;->caption:Ljava/lang/CharSequence;

    .line 2568
    :cond_7
    invoke-static/range {p5 .. p5}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_a

    move-object/from16 v2, p0

    iget v5, v2, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v5}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v5

    invoke-virtual {v5}, Lorg/telegram/messenger/UserConfig;->isPremium()Z

    move-result v5

    if-eqz v5, :cond_b

    .line 2569
    iget-object v5, v0, Lorg/telegram/ui/Stories/recorder/StoryEntry;->mediaEntities:Ljava/util/ArrayList;

    if-nez v5, :cond_8

    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    iput-object v5, v0, Lorg/telegram/ui/Stories/recorder/StoryEntry;->mediaEntities:Ljava/util/ArrayList;

    .line 2570
    :cond_8
    new-instance v5, Lorg/telegram/messenger/VideoEditedInfo$MediaEntity;

    invoke-direct {v5}, Lorg/telegram/messenger/VideoEditedInfo$MediaEntity;-><init>()V

    const/4 v6, 0x7

    .line 2571
    iput-byte v6, v5, Lorg/telegram/messenger/VideoEditedInfo$MediaEntity;->type:B

    const/4 v6, -0x1

    .line 2572
    iput-byte v6, v5, Lorg/telegram/messenger/VideoEditedInfo$MediaEntity;->subType:B

    .line 2573
    iput v6, v5, Lorg/telegram/messenger/VideoEditedInfo$MediaEntity;->color:I

    .line 2574
    new-instance v6, Lorg/telegram/ui/Components/Paint/Views/LinkPreview$WebPagePreview;

    invoke-direct {v6}, Lorg/telegram/ui/Components/Paint/Views/LinkPreview$WebPagePreview;-><init>()V

    iput-object v6, v5, Lorg/telegram/messenger/VideoEditedInfo$MediaEntity;->linkSettings:Lorg/telegram/ui/Components/Paint/Views/LinkPreview$WebPagePreview;

    move-object/from16 v7, p5

    .line 2575
    iput-object v7, v6, Lorg/telegram/ui/Components/Paint/Views/LinkPreview$WebPagePreview;->url:Ljava/lang/String;

    move-object/from16 v7, p6

    if-eqz v7, :cond_9

    .line 2577
    iget v8, v6, Lorg/telegram/ui/Components/Paint/Views/LinkPreview$WebPagePreview;->flags:I

    or-int/lit8 v8, v8, 0x2

    iput v8, v6, Lorg/telegram/ui/Components/Paint/Views/LinkPreview$WebPagePreview;->flags:I

    .line 2578
    iput-object v7, v6, Lorg/telegram/ui/Components/Paint/Views/LinkPreview$WebPagePreview;->name:Ljava/lang/String;

    .line 2580
    :cond_9
    iget-object v6, v0, Lorg/telegram/ui/Stories/recorder/StoryEntry;->mediaEntities:Ljava/util/ArrayList;

    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_6

    :cond_a
    move-object/from16 v2, p0

    .line 2582
    :cond_b
    :goto_6
    iget-object v2, v2, Lorg/telegram/ui/web/BotWebViewContainer;->parentActivity:Landroid/app/Activity;

    sget v5, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v2, v5}, Lorg/telegram/ui/Stories/recorder/StoryRecorder;->getInstance(Landroid/app/Activity;I)Lorg/telegram/ui/Stories/recorder/StoryRecorder;

    move-result-object v2

    .line 2583
    invoke-virtual {v2, v1, v0}, Lorg/telegram/ui/Stories/recorder/StoryRecorder;->openRepost(Lorg/telegram/ui/Stories/recorder/StoryRecorder$SourceView;Lorg/telegram/ui/Stories/recorder/StoryEntry;)V

    move-object/from16 v1, p3

    .line 2584
    invoke-virtual {v1, v3, v4}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismissUnless(J)V

    return-void

    .line 2562
    :goto_7
    invoke-virtual {v1, v3, v4}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismissUnless(J)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$37(Ljava/io/File;Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 9

    if-nez p1, :cond_0

    const-wide/16 p0, 0x1f4

    .line 2524
    invoke-virtual {p2, p0, p1}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismissUnless(J)V

    return-void

    :cond_0
    const/16 v0, 0xb

    .line 2527
    new-array v3, v0, [I

    .line 2528
    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda52;

    move-object v2, p0

    move-object v4, p1

    move-object v5, p2

    move-object v6, p3

    move-object v7, p4

    move-object v8, p5

    invoke-direct/range {v1 .. v8}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda52;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;[ILjava/io/File;Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 2586
    sget-object p0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance p1, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda53;

    invoke-direct {p1, v4, v3, v1}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda53;-><init>(Ljava/io/File;[ILjava/lang/Runnable;)V

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private synthetic lambda$onEventReceived$38(Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V
    .locals 7

    .line 2522
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda38;

    move-object v1, p0

    move-object v3, p1

    move-object v4, p2

    move-object v5, p3

    move-object v6, p4

    move-object v2, p5

    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda38;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/io/File;Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$39(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;)V
    .locals 1

    .line 2701
    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_0

    .line 2702
    const-string p2, "home_screen_added"

    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2704
    :cond_0
    const-string p2, "error"

    const-string v0, "UNSUPPORTED"

    invoke-static {p2, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    const-string v0, "home_screen_failed"

    invoke-direct {p0, p1, v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$40(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$Document;)V
    .locals 1

    if-nez p2, :cond_1

    .line 2734
    const-string p2, "emoji_status_set"

    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    .line 2735
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz p0, :cond_0

    .line 2736
    invoke-interface {p0, p3}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onEmojiStatusSet(Lorg/telegram/tgnet/TLRPC$Document;)V

    :cond_0
    return-void

    .line 2739
    :cond_1
    const-string p3, "error"

    invoke-static {p3, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    const-string p3, "emoji_status_failed"

    invoke-direct {p0, p1, p3, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$41(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/String;)V
    .locals 0

    .line 2749
    invoke-direct {p0, p1, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEmojiStatusAccess(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;)V

    .line 2750
    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_0

    const-string p1, "allowed"

    invoke-virtual {p1, p3}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz p0, :cond_0

    const/4 p1, 0x1

    .line 2751
    invoke-interface {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onEmojiStatusGranted(Z)V

    :cond_0
    return-void
.end method

.method private synthetic lambda$onEventReceived$42(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/json/JSONObject;)V
    .locals 1

    .line 2779
    const-string v0, "location_requested"

    invoke-direct {p0, p1, v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$43(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/Boolean;Ljava/lang/Boolean;)V
    .locals 1

    .line 2775
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz v0, :cond_0

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_0

    .line 2776
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-virtual {p3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p3

    invoke-interface {p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onLocationGranted(Z)V

    .line 2778
    :cond_0
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->location:Lorg/telegram/ui/bots/BotLocation;

    new-instance p3, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda34;

    invoke-direct {p3, p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda34;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {p2, p3}, Lorg/telegram/ui/bots/BotLocation;->requestObject(Lorg/telegram/messenger/Utilities$Callback;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$44(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/json/JSONObject;)V
    .locals 1

    .line 2784
    const-string v0, "location_requested"

    invoke-direct {p0, p1, v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$45(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V
    .locals 2

    .line 2852
    invoke-virtual {p4}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p4

    const-string/jumbo v0, "status"

    const-string v1, "file_download_requested"

    if-nez p4, :cond_0

    .line 2853
    const-string p2, "cancelled"

    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, v1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2857
    :cond_0
    iget-object p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->downloads:Lorg/telegram/ui/bots/BotDownloads;

    invoke-virtual {p4, p2, p3}, Lorg/telegram/ui/bots/BotDownloads;->download(Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/ui/bots/BotDownloads$FileDownload;

    .line 2858
    const-string p2, "downloading"

    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, v1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$46(Lorg/telegram/tgnet/TLObject;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V
    .locals 2

    .line 2847
    instance-of p1, p1, Lorg/telegram/tgnet/TLRPC$TL_boolTrue;

    if-nez p1, :cond_0

    .line 2848
    const-string/jumbo p1, "status"

    const-string p3, "cancelled"

    invoke-static {p1, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    const-string p3, "file_download_requested"

    invoke-direct {p0, p2, p3, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2851
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p1

    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-static {v0}, Lorg/telegram/messenger/UserObject;->getUserName(Lorg/telegram/tgnet/TLRPC$User;)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda60;

    invoke-direct {v1, p0, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda60;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {p1, p3, p4, v0, v1}, Lorg/telegram/ui/bots/BotDownloads;->showAlert(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$47(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 1

    move-object p5, p3

    move-object p3, p1

    move-object p1, p0

    .line 2846
    new-instance p0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda42;

    move-object v0, p4

    move-object p4, p2

    move-object p2, v0

    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda42;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLObject;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$48()V
    .locals 0

    .line 2883
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz p0, :cond_0

    .line 2884
    invoke-interface {p0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onCloseToTabs()V

    .line 2886
    :cond_0
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->dismissAllWeb()V

    return-void
.end method

.method private synthetic lambda$onEventReceived$50(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Ljava/lang/String;Ljava/util/ArrayList;)V
    .locals 1

    .line 2888
    invoke-static {p3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 2889
    const-string/jumbo p3, "prepared_message_sent"

    const/4 v0, 0x0

    invoke-direct {p0, p1, p3, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    .line 2890
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz p0, :cond_0

    .line 2891
    invoke-interface {p0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onOpenBackFromTabs()V

    .line 2893
    :cond_0
    new-instance p0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda35;

    invoke-direct {p0, p2, p4}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda35;-><init>(Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Ljava/util/ArrayList;)V

    const-wide/16 p1, 0x1f4

    invoke-static {p0, p1, p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void

    .line 2899
    :cond_1
    const-string p2, "error"

    invoke-static {p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    const-string/jumbo p3, "prepared_message_failed"

    invoke-direct {p0, p1, p3, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$51(ZDLjava/lang/String;D)V
    .locals 0

    .line 2982
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->onVerifiedAge:Lorg/telegram/messenger/Utilities$Callback4;

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    invoke-static {p2, p3}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object p2

    invoke-static {p5, p6}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object p3

    invoke-interface {p0, p1, p2, p4, p3}, Lorg/telegram/messenger/Utilities$Callback4;->run(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$52(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$User;Lorg/telegram/tgnet/TLRPC$Updates;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 6

    .line 3018
    const-string/jumbo v0, "req_id"

    if-eqz p4, :cond_2

    .line 3019
    iget p5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {p5}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p5

    const/4 v1, 0x0

    invoke-virtual {p5, p4, v1}, Lorg/telegram/messenger/MessagesController;->processUpdates(Lorg/telegram/tgnet/TLRPC$Updates;Z)V

    .line 3020
    const-string/jumbo p4, "requested_chat_sent"

    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p4, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    .line 3022
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v4, p1, Lorg/telegram/tgnet/TLRPC$User;->id:J

    .line 3023
    new-instance v2, Landroid/os/Bundle;

    invoke-direct {v2}, Landroid/os/Bundle;-><init>()V

    .line 3024
    const-string/jumbo p1, "user_id"

    iget-wide p4, p3, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-virtual {v2, p1, p4, p5}, Landroid/os/BaseBundle;->putLong(Ljava/lang/String;J)V

    .line 3025
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$6;

    move-object v1, p0

    move-object v3, p3

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/web/BotWebViewContainer$6;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Landroid/os/Bundle;Lorg/telegram/tgnet/TLRPC$User;J)V

    .line 3043
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 3045
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    .line 3047
    :cond_0
    iget-object p0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz p0, :cond_1

    .line 3048
    invoke-interface {p0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onCloseToTabs()V

    :cond_1
    return-void

    :cond_2
    move-object v1, p0

    .line 3056
    iget-object p0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 3052
    const-string/jumbo p3, "requested_chat_failed"

    if-eqz p5, :cond_3

    .line 3053
    invoke-static {v1, p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    invoke-virtual {p0, p5}, Lorg/telegram/ui/Components/BulletinFactory;->showForError(Lorg/telegram/tgnet/TLRPC$TL_error;)V

    .line 3054
    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p0

    invoke-direct {v1, p1, p3, p0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3056
    :cond_3
    invoke-static {v1, p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    const-string p4, "UNKNOWN_BUTTON"

    invoke-virtual {p0, p4}, Lorg/telegram/ui/Components/BulletinFactory;->showForError(Ljava/lang/String;)V

    .line 3057
    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p0

    invoke-direct {v1, p1, p3, p0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$53(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/tgnet/TLRPC$User;)V
    .locals 4

    .line 3007
    const-string/jumbo v0, "req_id"

    if-nez p4, :cond_0

    .line 3008
    const-string/jumbo p3, "requested_chat_failed"

    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p3, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3012
    :cond_0
    new-instance v1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;-><init>()V

    .line 3013
    iget-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-static {v2}, Lorg/telegram/messenger/MessagesController;->getInputPeer(Lorg/telegram/tgnet/TLRPC$User;)Lorg/telegram/tgnet/TLRPC$InputPeer;

    move-result-object v2

    iput-object v2, v1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->peer:Lorg/telegram/tgnet/TLRPC$InputPeer;

    .line 3014
    iput-object p2, v1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->webapp_req_id:Ljava/lang/String;

    .line 3015
    iget p3, p3, Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;->button_id:I

    iput p3, v1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->button_id:I

    .line 3016
    iget-object p3, v1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->requested_peers:Ljava/util/ArrayList;

    invoke-static {p4}, Lorg/telegram/messenger/MessagesController;->getInputPeer(Lorg/telegram/tgnet/TLRPC$User;)Lorg/telegram/tgnet/TLRPC$InputPeer;

    move-result-object v2

    invoke-virtual {p3, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 3017
    iget p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {p3}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p3

    new-instance v2, Lorg/telegram/messenger/AiTonesController$$ExternalSyntheticLambda0;

    invoke-direct {v2}, Lorg/telegram/messenger/AiTonesController$$ExternalSyntheticLambda0;-><init>()V

    new-instance v3, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda55;

    invoke-direct {v3, p0, p1, p2, p4}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda55;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$User;)V

    invoke-virtual {p3, v1, v2, v3}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequestTyped(Lorg/telegram/tgnet/TLMethod;Ljava/util/concurrent/Executor;Lorg/telegram/messenger/Utilities$Callback2;)I

    .line 3060
    const-string/jumbo p3, "requested_chat_sent"

    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p3, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$54(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$Updates;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 2

    .line 3078
    const-string/jumbo v0, "req_id"

    if-eqz p3, :cond_0

    .line 3079
    iget p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {p4}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p4

    const/4 v1, 0x0

    invoke-virtual {p4, p3, v1}, Lorg/telegram/messenger/MessagesController;->processUpdates(Lorg/telegram/tgnet/TLRPC$Updates;Z)V

    .line 3080
    const-string/jumbo p3, "requested_chat_sent"

    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p3, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3087
    :cond_0
    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 3083
    const-string/jumbo v1, "requested_chat_failed"

    if-eqz p4, :cond_1

    .line 3084
    invoke-static {p0, p3}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p3

    invoke-virtual {p3, p4}, Lorg/telegram/ui/Components/BulletinFactory;->showForError(Lorg/telegram/tgnet/TLRPC$TL_error;)V

    .line 3085
    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, v1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3087
    :cond_1
    invoke-static {p0, p3}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p3

    const-string p4, "UNKNOWN_BUTTON"

    invoke-virtual {p3, p4}, Lorg/telegram/ui/Components/BulletinFactory;->showForError(Ljava/lang/String;)V

    .line 3088
    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, v1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$55([ZLjava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/util/List;)V
    .locals 4

    if-eqz p5, :cond_1

    .line 3068
    invoke-interface {p5}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 3069
    aput-boolean v1, p1, v0

    .line 3070
    new-instance p1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;

    invoke-direct {p1}, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;-><init>()V

    .line 3071
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInputPeer(Lorg/telegram/tgnet/TLRPC$User;)Lorg/telegram/tgnet/TLRPC$InputPeer;

    move-result-object v0

    iput-object v0, p1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->peer:Lorg/telegram/tgnet/TLRPC$InputPeer;

    .line 3072
    iput-object p2, p1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->webapp_req_id:Ljava/lang/String;

    .line 3073
    iget p3, p3, Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;->button_id:I

    iput p3, p1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->button_id:I

    .line 3074
    invoke-interface {p5}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p3

    :goto_0
    invoke-interface {p3}, Ljava/util/Iterator;->hasNext()Z

    move-result p5

    if-eqz p5, :cond_0

    invoke-interface {p3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p5

    check-cast p5, Ljava/lang/Long;

    .line 3075
    iget-object v0, p1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->requested_peers:Ljava/util/ArrayList;

    iget v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    invoke-virtual {p5}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Lorg/telegram/messenger/MessagesController;->getInputPeer(J)Lorg/telegram/tgnet/TLRPC$InputPeer;

    move-result-object p5

    invoke-virtual {v0, p5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 3077
    :cond_0
    iget p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {p3}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p3

    new-instance p5, Lorg/telegram/messenger/AiTonesController$$ExternalSyntheticLambda0;

    invoke-direct {p5}, Lorg/telegram/messenger/AiTonesController$$ExternalSyntheticLambda0;-><init>()V

    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda56;

    invoke-direct {v0, p0, p4, p2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda56;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;)V

    invoke-virtual {p3, p1, p5, v0}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequestTyped(Lorg/telegram/tgnet/TLMethod;Ljava/util/concurrent/Executor;Lorg/telegram/messenger/Utilities$Callback2;)I

    :cond_1
    return-void
.end method

.method private synthetic lambda$onEventReceived$56([ZLorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Landroid/content/DialogInterface;)V
    .locals 1

    const/4 p4, 0x0

    .line 3095
    aget-boolean v0, p1, p4

    if-nez v0, :cond_0

    const/4 v0, 0x1

    .line 3096
    aput-boolean v0, p1, p4

    .line 3097
    const-string/jumbo p1, "req_id"

    invoke-static {p1, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    const-string/jumbo p3, "requested_chat_failed"

    invoke-direct {p0, p2, p3, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    :cond_0
    return-void
.end method

.method private synthetic lambda$onEventReceived$57(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$Updates;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 2

    .line 3141
    const-string/jumbo v0, "req_id"

    if-eqz p3, :cond_0

    .line 3142
    iget p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {p4}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p4

    const/4 v1, 0x0

    invoke-virtual {p4, p3, v1}, Lorg/telegram/messenger/MessagesController;->processUpdates(Lorg/telegram/tgnet/TLRPC$Updates;Z)V

    .line 3143
    const-string/jumbo p3, "requested_chat_sent"

    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p3, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3150
    :cond_0
    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 3146
    const-string/jumbo v1, "requested_chat_failed"

    if-eqz p4, :cond_1

    .line 3147
    invoke-static {p0, p3}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p3

    invoke-virtual {p3, p4}, Lorg/telegram/ui/Components/BulletinFactory;->showForError(Lorg/telegram/tgnet/TLRPC$TL_error;)V

    .line 3148
    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, v1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3150
    :cond_1
    invoke-static {p0, p3}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p3

    const-string p4, "UNKNOWN_BUTTON"

    invoke-virtual {p3, p4}, Lorg/telegram/ui/Components/BulletinFactory;->showForError(Ljava/lang/String;)V

    .line 3151
    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, v1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$58([ZLjava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/DialogsActivity;Ljava/util/ArrayList;Ljava/lang/CharSequence;ZZIILorg/telegram/ui/TopicsFragment;)Z
    .locals 0

    const/4 p7, 0x1

    if-eqz p6, :cond_2

    .line 3127
    invoke-virtual {p6}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p8

    if-nez p8, :cond_2

    const/4 p8, 0x0

    .line 3128
    aput-boolean p7, p1, p8

    .line 3129
    new-instance p1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;

    invoke-direct {p1}, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;-><init>()V

    .line 3130
    iget p9, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {p9}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    iget-object p9, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-static {p9}, Lorg/telegram/messenger/MessagesController;->getInputPeer(Lorg/telegram/tgnet/TLRPC$User;)Lorg/telegram/tgnet/TLRPC$InputPeer;

    move-result-object p9

    iput-object p9, p1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->peer:Lorg/telegram/tgnet/TLRPC$InputPeer;

    .line 3131
    iput-object p2, p1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->webapp_req_id:Ljava/lang/String;

    .line 3132
    iget p3, p3, Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;->button_id:I

    iput p3, p1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->button_id:I

    .line 3133
    new-instance p3, Ljava/util/HashSet;

    invoke-direct {p3}, Ljava/util/HashSet;-><init>()V

    .line 3134
    invoke-virtual {p6}, Ljava/util/ArrayList;->size()I

    move-result p9

    :goto_0
    if-ge p8, p9, :cond_0

    invoke-virtual {p6, p8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p10

    add-int/lit8 p8, p8, 0x1

    check-cast p10, Lorg/telegram/messenger/MessagesStorage$TopicKey;

    .line 3135
    iget-wide p10, p10, Lorg/telegram/messenger/MessagesStorage$TopicKey;->dialogId:J

    invoke-static {p10, p11}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p10

    invoke-virtual {p3, p10}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 3137
    :cond_0
    invoke-virtual {p3}, Ljava/util/HashSet;->iterator()Ljava/util/Iterator;

    move-result-object p3

    :goto_1
    invoke-interface {p3}, Ljava/util/Iterator;->hasNext()Z

    move-result p6

    if-eqz p6, :cond_1

    invoke-interface {p3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p6

    check-cast p6, Ljava/lang/Long;

    invoke-virtual {p6}, Ljava/lang/Long;->longValue()J

    move-result-wide p8

    .line 3138
    iget-object p6, p1, Lorg/telegram/tgnet/TLRPC$TL_messages_sendBotRequestedPeer;->requested_peers:Ljava/util/ArrayList;

    iget p10, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {p10}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p10

    invoke-virtual {p10, p8, p9}, Lorg/telegram/messenger/MessagesController;->getInputPeer(J)Lorg/telegram/tgnet/TLRPC$InputPeer;

    move-result-object p8

    invoke-virtual {p6, p8}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 3140
    :cond_1
    iget p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {p3}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p3

    new-instance p6, Lorg/telegram/messenger/AiTonesController$$ExternalSyntheticLambda0;

    invoke-direct {p6}, Lorg/telegram/messenger/AiTonesController$$ExternalSyntheticLambda0;-><init>()V

    new-instance p8, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda51;

    invoke-direct {p8, p0, p4, p2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda51;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;)V

    invoke-virtual {p3, p1, p6, p8}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequestTyped(Lorg/telegram/tgnet/TLMethod;Ljava/util/concurrent/Executor;Lorg/telegram/messenger/Utilities$Callback2;)I

    .line 3155
    :cond_2
    invoke-virtual {p5}, Lorg/telegram/ui/DialogsActivity;->finishFragment()V

    return p7
.end method

.method private synthetic lambda$onEventReceived$59(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$KeyboardButton;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 14

    move-object/from16 v3, p2

    move-object/from16 v0, p4

    .line 3002
    const-class v1, Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;

    move-object/from16 v2, p3

    invoke-static {v2, v1}, Lorg/telegram/messenger/utils/tlutils/TLKeyboardHelper;->getType(Lorg/telegram/tgnet/tl/TL_keyboard$KeyboardButtonProto;Ljava/lang/Class;)Lorg/telegram/tgnet/tl/TL_keyboard$ButtonTypeProto;

    move-result-object v1

    move-object v4, v1

    check-cast v4, Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;

    if-eqz v4, :cond_4

    .line 3004
    iget-object v0, v4, Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;->peer_type:Lorg/telegram/tgnet/TLRPC$RequestPeerType;

    instance-of v1, v0, Lorg/telegram/tgnet/TLRPC$TL_requestPeerTypeCreateBot;

    if-eqz v1, :cond_0

    .line 3005
    move-object v8, v0

    check-cast v8, Lorg/telegram/tgnet/TLRPC$TL_requestPeerTypeCreateBot;

    .line 3006
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v5

    iget v6, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    iget-object v7, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    new-instance v10, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda43;

    invoke-direct {v10, p0, p1, v3, v4}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda43;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;)V

    iget-object v11, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 3061
    invoke-static {p0, v11}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v12

    const/4 v13, 0x1

    const/4 v9, 0x0

    .line 3006
    invoke-static/range {v5 .. v13}, Lorg/telegram/ui/Components/CreateBotAlert;->show(Landroid/content/Context;ILorg/telegram/tgnet/TLRPC$User;Lorg/telegram/tgnet/TLRPC$TL_requestPeerTypeCreateBot;ZLorg/telegram/messenger/Utilities$Callback;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Lorg/telegram/ui/Components/BulletinFactory;Z)V

    return-void

    .line 3064
    :cond_0
    instance-of v1, v0, Lorg/telegram/tgnet/TLRPC$TL_requestPeerTypeUser;

    const/4 v6, 0x1

    if-eqz v1, :cond_1

    iget v7, v4, Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;->max_quantity:I

    if-le v7, v6, :cond_1

    .line 3065
    check-cast v0, Lorg/telegram/tgnet/TLRPC$TL_requestPeerTypeUser;

    .line 3066
    new-array v2, v6, [Z

    .line 3067
    iget-object v6, v0, Lorg/telegram/tgnet/TLRPC$TL_requestPeerTypeUser;->bot:Ljava/lang/Boolean;

    iget-object v8, v0, Lorg/telegram/tgnet/TLRPC$TL_requestPeerTypeUser;->premium:Ljava/lang/Boolean;

    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda44;

    move-object v1, p0

    move-object v5, p1

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda44;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;[ZLjava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-static {v6, v8, v7, v0}, Lorg/telegram/ui/MultiContactsSelectorBottomSheet;->open(Ljava/lang/Boolean;Ljava/lang/Boolean;ILorg/telegram/ui/MultiContactsSelectorBottomSheet$SelectorListener;)Lorg/telegram/ui/MultiContactsSelectorBottomSheet;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 3094
    new-instance v4, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda45;

    invoke-direct {v4, p0, v2, p1, v3}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda45;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;[ZLorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;)V

    invoke-virtual {v0, v4}, Lorg/telegram/ui/ActionBar/BottomSheet;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)V

    return-void

    .line 3103
    :cond_1
    new-instance v2, Landroid/os/Bundle;

    invoke-direct {v2}, Landroid/os/Bundle;-><init>()V

    .line 3104
    const-string/jumbo v0, "onlySelect"

    invoke-virtual {v2, v0, v6}, Landroid/os/BaseBundle;->putBoolean(Ljava/lang/String;Z)V

    .line 3105
    const-string v0, "dialogsType"

    const/16 v7, 0xf

    invoke-virtual {v2, v0, v7}, Landroid/os/BaseBundle;->putInt(Ljava/lang/String;I)V

    .line 3106
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v7, v0, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const-string/jumbo v0, "requestPeerBotId"

    invoke-virtual {v2, v0, v7, v8}, Landroid/os/BaseBundle;->putLong(Ljava/lang/String;J)V

    .line 3108
    :try_start_0
    new-instance v0, Lorg/telegram/tgnet/SerializedData;

    iget-object v7, v4, Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;->peer_type:Lorg/telegram/tgnet/TLRPC$RequestPeerType;

    invoke-virtual {v7}, Lorg/telegram/tgnet/TLObject;->getObjectSize()I

    move-result v7

    invoke-direct {v0, v7}, Lorg/telegram/tgnet/SerializedData;-><init>(I)V

    .line 3109
    iget-object v7, v4, Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;->peer_type:Lorg/telegram/tgnet/TLRPC$RequestPeerType;

    invoke-virtual {v7, v0}, Lorg/telegram/tgnet/TLObject;->serializeToStream(Lorg/telegram/tgnet/OutputSerializedData;)V

    .line 3110
    const-string/jumbo v7, "requestPeerType"

    invoke-virtual {v0}, Lorg/telegram/tgnet/SerializedData;->toByteArray()[B

    move-result-object v8

    invoke-virtual {v2, v7, v8}, Landroid/os/Bundle;->putByteArray(Ljava/lang/String;[B)V

    .line 3111
    invoke-virtual {v0}, Lorg/telegram/tgnet/SerializedData;->cleanup()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 3113
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 3115
    :goto_0
    new-array v0, v6, [Z

    .line 3116
    new-instance v7, Lorg/telegram/ui/web/BotWebViewContainer$7;

    invoke-direct {v7, p0, v2, v0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$7;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Landroid/os/Bundle;[ZLorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    move-object v2, v0

    .line 3126
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda46;

    move-object v1, p0

    move-object v5, p1

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda46;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;[ZLjava/lang/String;Lorg/telegram/tgnet/tl/TL_keyboard$TL_buttonTypeRequestPeer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v7, v0}, Lorg/telegram/ui/DialogsActivity;->setDelegate(Lorg/telegram/ui/DialogsActivity$DialogsActivityDelegate;)V

    .line 3158
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object p0

    if-nez p0, :cond_3

    :cond_2
    return-void

    .line 3160
    :cond_3
    new-instance p1, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;

    invoke-direct {p1}, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;-><init>()V

    .line 3161
    iput-boolean v6, p1, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;->transitionFromLeft:Z

    const/4 v0, 0x0

    .line 3162
    iput-boolean v0, p1, Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;->allowNestedScroll:Z

    .line 3163
    invoke-virtual {p0, v7, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->showAsSheet(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/BaseFragment$BottomSheetParams;)[Lorg/telegram/ui/ActionBar/INavigationLayout;

    return-void

    .line 3170
    :cond_4
    iget-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 3166
    const-string/jumbo v4, "req_id"

    const-string/jumbo v6, "requested_chat_failed"

    if-eqz v0, :cond_5

    .line 3167
    invoke-static {p0, v2}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v2

    invoke-virtual {v2, v0}, Lorg/telegram/ui/Components/BulletinFactory;->showForError(Lorg/telegram/tgnet/TLRPC$TL_error;)V

    .line 3168
    invoke-static {v4, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {p0, p1, v6, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3170
    :cond_5
    invoke-static {p0, v2}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v0

    const-string v2, "UNKNOWN_BUTTON"

    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/BulletinFactory;->showForError(Ljava/lang/String;)V

    .line 3171
    invoke-static {v4, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {p0, p1, v6, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$onEventReceived$9(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 1

    .line 1723
    invoke-virtual {p4}, Lorg/telegram/ui/ActionBar/AlertDialog;->dismiss()V

    .line 1725
    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p4

    iput-wide p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 1726
    const-string/jumbo p4, "popup_closed"

    new-instance p5, Lorg/json/JSONObject;

    invoke-direct {p5}, Lorg/json/JSONObject;-><init>()V

    const-string v0, "button_id"

    iget-object p2, p2, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->id:Ljava/lang/String;

    invoke-virtual {p5, v0, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p4, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    const/4 p0, 0x1

    .line 1727
    invoke-virtual {p3, p0}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 1729
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$onWebEventReceived$5(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_error;Ljava/lang/String;)V
    .locals 11

    if-eqz p1, :cond_2

    .line 1391
    instance-of v1, p1, Lorg/telegram/tgnet/TLRPC$TL_urlAuthResultRequest;

    if-eqz v1, :cond_0

    .line 1392
    iget v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    move-object v3, p1

    check-cast v3, Lorg/telegram/tgnet/TLRPC$TL_urlAuthResultRequest;

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v0, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    move-object v8, p0

    move-object v2, p2

    invoke-static/range {v0 .. v8}, Lorg/telegram/ui/OAuthSheet;->handle(ZILorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;Lorg/telegram/tgnet/TLRPC$UrlAuthResult;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$UrlAuthResult;Ljava/lang/String;ZLorg/telegram/ui/web/BotWebViewContainer;)V

    return-void

    .line 1393
    :cond_0
    instance-of v1, p1, Lorg/telegram/tgnet/TLRPC$TL_urlAuthResultAccepted;

    if-eqz v1, :cond_1

    .line 1394
    iget v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    move-object v3, p1

    check-cast v3, Lorg/telegram/tgnet/TLRPC$TL_urlAuthResultAccepted;

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v0, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    move-object v8, p0

    move-object v2, p2

    invoke-static/range {v0 .. v8}, Lorg/telegram/ui/OAuthSheet;->handle(ZILorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;Lorg/telegram/tgnet/TLRPC$UrlAuthResult;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$UrlAuthResult;Ljava/lang/String;ZLorg/telegram/ui/web/BotWebViewContainer;)V

    return-void

    .line 1395
    :cond_1
    instance-of v0, p1, Lorg/telegram/tgnet/TLRPC$TL_urlAuthResultDefault;

    if-eqz v0, :cond_4

    .line 1396
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v1

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x1

    const/4 v5, 0x1

    const/4 v6, 0x0

    const-wide/16 v7, 0x0

    move-object v2, p3

    invoke-static/range {v1 .. v10}, Lorg/telegram/ui/Components/AlertsCreator;->showOpenUrlAlert(Landroid/content/Context;Ljava/lang/String;ZZZZJLorg/telegram/messenger/browser/Browser$Progress;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-void

    :cond_2
    if-eqz p4, :cond_4

    .line 1399
    const-string v0, "URL_EXPIRED"

    iget-object v2, p4, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    .line 1404
    iget-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    if-eqz v0, :cond_3

    .line 1400
    invoke-static {p0, v2}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/R$raw;->error:I

    sget v2, Lorg/telegram/messenger/R$string;->BotAuthLoggedInFailTitle:I

    .line 1401
    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$string;->BotAuthLoggedInFail:I

    filled-new-array/range {p5 .. p5}, [Ljava/lang/Object;

    move-result-object v4

    invoke-static {v3, v4}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_undo_cancelColor:I

    iget-object v5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v4, v5}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v4

    invoke-static {v3, v4}, Lorg/telegram/messenger/AndroidUtilities;->replaceSingleLinkBold(Ljava/lang/String;I)Landroid/text/SpannableStringBuilder;

    move-result-object v3

    invoke-virtual {v0, v1, v2, v3}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object v0

    .line 1402
    invoke-virtual {v0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 1404
    :cond_3
    invoke-static {p0, v2}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v0

    invoke-virtual {v0, p4}, Lorg/telegram/ui/Components/BulletinFactory;->showForError(Lorg/telegram/tgnet/TLRPC$TL_error;)V

    :cond_4
    return-void
.end method

.method private synthetic lambda$onWebEventReceived$6(Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 7

    .line 1389
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda37;

    move-object v1, p0

    move-object v3, p1

    move-object v4, p2

    move-object v6, p3

    move-object v2, p4

    move-object v5, p5

    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda37;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_error;Ljava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$reload$1()V
    .locals 4

    .line 1031
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isSettingsButtonVisible:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 1032
    iput-boolean v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isSettingsButtonVisible:Z

    .line 1033
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz v0, :cond_0

    .line 1034
    invoke-interface {v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onSetSettingsButtonVisible(Z)V

    .line 1038
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->checkCreateWebView()V

    .line 1039
    iput-boolean v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isPageLoaded:Z

    const-wide/16 v2, 0x0

    .line 1040
    iput-wide v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 1041
    iput-boolean v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->hasUserPermissions:Z

    .line 1042
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v0, :cond_1

    .line 1043
    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->onResume()V

    .line 1044
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->reload()V

    .line 1046
    :cond_1
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->updateKeyboardFocusable()V

    .line 1048
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->sensors:Lorg/telegram/ui/bots/BotSensors;

    if-eqz p0, :cond_2

    .line 1049
    invoke-virtual {p0}, Lorg/telegram/ui/bots/BotSensors;->stopAll()V

    :cond_2
    return-void
.end method

.method private synthetic lambda$restoreStorageKey$60(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 2

    .line 3299
    const-string v0, "error"

    const-string/jumbo v1, "req_id"

    if-nez p7, :cond_0

    .line 3300
    const-string p4, "RESTORE_CANCELLED"

    invoke-static {v1, p3, v0, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p3

    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3305
    :cond_0
    :try_start_0
    invoke-virtual {p4, p7}, Lorg/telegram/ui/bots/BotStorage;->restoreFrom(Ljava/lang/String;)V

    .line 3306
    invoke-virtual {p4, p5}, Lorg/telegram/ui/bots/BotStorage;->getKey(Ljava/lang/String;)Landroid/util/Pair;

    move-result-object p4

    iget-object p4, p4, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast p4, Ljava/lang/String;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 3311
    const-string/jumbo p2, "value"

    invoke-static {v1, p3, p2, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p6, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :catch_0
    move-exception p4

    .line 3308
    invoke-virtual {p4}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p4

    invoke-static {v1, p3, v0, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p3

    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private synthetic lambda$runWithPermissions$0(Landroidx/core/util/Consumer;[Ljava/lang/String;)V
    .locals 0

    .line 717
    invoke-direct {p0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->checkPermissions([Ljava/lang/String;)Z

    move-result p0

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    invoke-interface {p1, p0}, Landroidx/core/util/Consumer;->accept(Ljava/lang/Object;)V

    return-void
.end method

.method private synthetic lambda$showDialog$62(Ljava/lang/Runnable;Landroid/content/DialogInterface;)V
    .locals 0

    if-eqz p1, :cond_0

    .line 3442
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    :cond_0
    const/4 p1, 0x0

    .line 3444
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    return-void
.end method

.method public static magic2tonsite(Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    .line 5505
    sget-object v0, Lorg/telegram/ui/web/BotWebViewContainer;->rotatedTONHosts:Ljava/util/HashMap;

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    if-nez p0, :cond_1

    :goto_0
    return-object p0

    .line 5507
    :cond_1
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->getHostAuthority(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 5508
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "."

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    sget v2, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v2}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v2

    iget-object v2, v2, Lorg/telegram/messenger/MessagesController;->tonProxyAddress:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_2

    goto :goto_1

    .line 5511
    :cond_2
    sget-object v1, Lorg/telegram/ui/web/BotWebViewContainer;->rotatedTONHosts:Ljava/util/HashMap;

    invoke-virtual {v1, v0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    if-nez v0, :cond_3

    goto :goto_1

    .line 5513
    :cond_3
    invoke-static {p0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p0

    const-string/jumbo v1, "tonsite"

    const/4 v2, 0x0

    invoke-static {p0, v1, v2, v0, v2}, Lorg/telegram/messenger/browser/Browser;->replace(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    :cond_4
    :goto_1
    return-object p0
.end method

.method private notifyBiometryReceived(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V
    .locals 2

    .line 3387
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v0, :cond_0

    goto :goto_0

    .line 3390
    :cond_0
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->createBiometry()V

    .line 3391
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    if-nez v0, :cond_1

    :goto_0
    return-void

    .line 3395
    :cond_1
    :try_start_0
    const-string v1, "biometry_info_received"

    invoke-virtual {v0}, Lorg/telegram/ui/bots/BotBiometry;->getStatus()Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {p0, p1, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 3397
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method private notifyEmojiStatusAccess(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;)V
    .locals 1

    .line 3372
    const-string/jumbo v0, "status"

    invoke-static {v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    const-string v0, "emoji_status_access_requested"

    invoke-direct {p0, p1, v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private static notifyEvent(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    .locals 1

    if-nez p1, :cond_0

    return-void

    .line 1273
    :cond_0
    invoke-static {p0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda40;

    invoke-direct {v0, p1, p2, p3, p4}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda40;-><init>(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    invoke-virtual {p0, v0}, Lorg/telegram/messenger/NotificationCenter;->doOnIdle(Ljava/lang/Runnable;)V

    return-void
.end method

.method private notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    .locals 0

    .line 1226
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestContextCurrent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Z

    move-result p1

    if-nez p1, :cond_0

    .line 1227
    new-instance p1, Ljava/lang/StringBuilder;

    const-string/jumbo p3, "notifyEvent "

    invoke-direct {p1, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, " dropped after document change"

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void

    .line 1230
    :cond_0
    invoke-virtual {p0, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method private notifyEvent_fast(Ljava/lang/String;Ljava/lang/String;)V
    .locals 2

    .line 1258
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->isTrustedDocumentCurrent()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1259
    new-instance p2, Ljava/lang/StringBuilder;

    const-string/jumbo v0, "notifyEvent "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " dropped for untrusted document"

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void

    .line 1262
    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "window.Telegram.WebView.receiveEvent(\'"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 1264
    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1265
    const-string p1, "\', "

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1266
    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1267
    const-string p1, ");"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1268
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->evaluateJs(Ljava/lang/String;Z)V

    return-void
.end method

.method public static obj()Lorg/json/JSONObject;
    .locals 1

    .line 5518
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    const/4 v0, 0x0

    return-object v0
.end method

.method public static obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    .locals 1

    .line 5526
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 5527
    invoke-virtual {v0, p0, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public static obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    .locals 1

    .line 5536
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 5537
    invoke-virtual {v0, p0, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 5538
    invoke-virtual {v0, p2, p3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public static obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    .locals 1

    .line 5547
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 5548
    invoke-virtual {v0, p0, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 5549
    invoke-virtual {v0, p2, p3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 5550
    invoke-virtual {v0, p4, p5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public static obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    .locals 1

    .line 5559
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 5560
    invoke-virtual {v0, p0, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 5561
    invoke-virtual {v0, p2, p3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 5562
    invoke-virtual {v0, p4, p5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 5563
    invoke-virtual {v0, p6, p7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private onBotWebMessage(Landroid/webkit/WebView;Landroidx/webkit/WebMessageCompat;Landroid/net/Uri;)V
    .locals 6

    .line 1470
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-ne p1, v0, :cond_0

    if-eqz p2, :cond_0

    invoke-virtual {p2}, Landroidx/webkit/WebMessageCompat;->getType()I

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    move-object v1, p0

    goto/16 :goto_3

    :cond_1
    const/4 v0, 0x0

    if-nez p3, :cond_2

    move-object p3, v0

    goto :goto_0

    .line 1475
    :cond_2
    invoke-virtual {p3}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p3

    :goto_0
    invoke-static {p3}, Lorg/telegram/ui/web/BotWebViewContainer;->getOriginHost(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    .line 1476
    iget-boolean v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->restrictBridgeToOrigin:Z

    if-eqz v1, :cond_4

    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    .line 1477
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_3

    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    .line 1478
    invoke-static {v1, p3}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p3

    if-eqz p3, :cond_3

    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    .line 1479
    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->getOriginHost()Ljava/lang/String;

    move-result-object v1

    invoke-static {p3, v1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p3

    if-nez p3, :cond_4

    .line 1480
    :cond_3
    const-string/jumbo p1, "onBotWebMessage ignored: untrusted origin"

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void

    .line 1484
    :cond_4
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->createRequestContext()Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;

    move-result-object v3

    .line 1486
    :try_start_0
    invoke-virtual {p2}, Landroidx/webkit/WebMessageCompat;->getData()Ljava/lang/String;

    move-result-object p2

    if-eqz p2, :cond_5

    .line 1487
    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p3

    const/high16 v1, 0x100000

    if-le p3, v1, :cond_6

    :cond_5
    move-object v1, p0

    goto :goto_2

    .line 1491
    :cond_6
    new-instance p3, Lorg/json/JSONObject;

    invoke-direct {p3, p2}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1492
    const-string p2, "eventType"

    invoke-virtual {p3, p2}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 1493
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result p2

    if-nez p2, :cond_7

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result p2

    const/16 v1, 0x80

    if-le p2, v1, :cond_8

    :cond_7
    move-object v1, p0

    goto :goto_1

    .line 1497
    :cond_8
    const-string p2, "eventData"

    invoke-virtual {p3, p2, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 1498
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda49;

    move-object v1, p0

    move-object v2, p1

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda49;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Landroid/webkit/WebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 1494
    :goto_1
    const-string/jumbo p0, "onBotWebMessage ignored: invalid event type"

    invoke-virtual {v1, p0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void

    .line 1488
    :goto_2
    const-string/jumbo p0, "onBotWebMessage ignored: invalid payload length"

    invoke-virtual {v1, p0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 1507
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void

    .line 1471
    :goto_3
    const-string/jumbo p0, "onBotWebMessage ignored: invalid source or payload"

    invoke-virtual {v1, p0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void
.end method

.method private onEventReceived(Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V
    .locals 44

    move-object/from16 v1, p0

    move-object/from16 v2, p2

    move-object/from16 v0, p3

    move-object/from16 v4, p4

    .line 1540
    const-string v3, "MESSAGE_EXPIRED"

    const-string/jumbo v5, "prepared_message_failed"

    const-string v6, "https://t.me/"

    iget-boolean v7, v1, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-nez v7, :cond_0

    goto/16 :goto_31

    .line 1543
    :cond_0
    iget-object v7, v1, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    const-string/jumbo v8, "onEventReceived "

    if-eqz v7, :cond_b4

    iget-object v7, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-nez v7, :cond_1

    goto/16 :goto_32

    .line 1547
    :cond_1
    invoke-direct {v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestContextCurrent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)Z

    move-result v7

    if-nez v7, :cond_2

    .line 1548
    new-instance v2, Ljava/lang/StringBuilder;

    const-string/jumbo v3, "onEventReceived ignore "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, " after document change"

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void

    .line 1551
    :cond_2
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v1, v7}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 1552
    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v7

    sparse-switch v7, :sswitch_data_0

    :goto_0
    const/4 v7, -0x1

    goto/16 :goto_1

    :sswitch_0
    const-string/jumbo v7, "web_app_expand"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_3

    goto :goto_0

    :cond_3
    const/16 v7, 0x3f

    goto/16 :goto_1

    :sswitch_1
    const-string/jumbo v7, "web_app_request_write_access"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_4

    goto :goto_0

    :cond_4
    const/16 v7, 0x3e

    goto/16 :goto_1

    :sswitch_2
    const-string/jumbo v7, "web_app_set_background_color"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_5

    goto :goto_0

    :cond_5
    const/16 v7, 0x3d

    goto/16 :goto_1

    :sswitch_3
    const-string/jumbo v7, "web_app_request_safe_area"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_6

    goto :goto_0

    :cond_6
    const/16 v7, 0x3c

    goto/16 :goto_1

    :sswitch_4
    const-string/jumbo v7, "web_app_set_header_color"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_7

    goto :goto_0

    :cond_7
    const/16 v7, 0x3b

    goto/16 :goto_1

    :sswitch_5
    const-string/jumbo v7, "web_app_set_bottom_bar_color"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_8

    goto :goto_0

    :cond_8
    const/16 v7, 0x3a

    goto/16 :goto_1

    :sswitch_6
    const-string/jumbo v7, "web_app_biometry_update_token"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_9

    goto :goto_0

    :cond_9
    const/16 v7, 0x39

    goto/16 :goto_1

    :sswitch_7
    const-string/jumbo v7, "web_app_start_device_orientation"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_a

    goto :goto_0

    :cond_a
    const/16 v7, 0x38

    goto/16 :goto_1

    :sswitch_8
    const-string/jumbo v7, "web_app_request_chat"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_b

    goto :goto_0

    :cond_b
    const/16 v7, 0x37

    goto/16 :goto_1

    :sswitch_9
    const-string/jumbo v7, "web_app_check_home_screen"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_c

    goto/16 :goto_0

    :cond_c
    const/16 v7, 0x36

    goto/16 :goto_1

    :sswitch_a
    const-string/jumbo v7, "web_app_setup_settings_button"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_d

    goto/16 :goto_0

    :cond_d
    const/16 v7, 0x35

    goto/16 :goto_1

    :sswitch_b
    const-string/jumbo v7, "web_app_setup_swipe_behavior"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_e

    goto/16 :goto_0

    :cond_e
    const/16 v7, 0x34

    goto/16 :goto_1

    :sswitch_c
    const-string/jumbo v7, "web_app_setup_main_button"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_f

    goto/16 :goto_0

    :cond_f
    const/16 v7, 0x33

    goto/16 :goto_1

    :sswitch_d
    const-string/jumbo v7, "web_app_trigger_haptic_feedback"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_10

    goto/16 :goto_0

    :cond_10
    const/16 v7, 0x32

    goto/16 :goto_1

    :sswitch_e
    const-string/jumbo v7, "web_app_biometry_request_access"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_11

    goto/16 :goto_0

    :cond_11
    const/16 v7, 0x31

    goto/16 :goto_1

    :sswitch_f
    const-string/jumbo v7, "web_app_setup_back_button"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_12

    goto/16 :goto_0

    :cond_12
    const/16 v7, 0x30

    goto/16 :goto_1

    :sswitch_10
    const-string/jumbo v7, "web_app_open_location_settings"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_13

    goto/16 :goto_0

    :cond_13
    const/16 v7, 0x2f

    goto/16 :goto_1

    :sswitch_11
    const-string/jumbo v7, "web_app_verify_age"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_14

    goto/16 :goto_0

    :cond_14
    const/16 v7, 0x2e

    goto/16 :goto_1

    :sswitch_12
    const-string/jumbo v7, "web_app_exit_fullscreen"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_15

    goto/16 :goto_0

    :cond_15
    const/16 v7, 0x2d

    goto/16 :goto_1

    :sswitch_13
    const-string/jumbo v7, "web_app_secure_storage_save_key"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_16

    goto/16 :goto_0

    :cond_16
    const/16 v7, 0x2c

    goto/16 :goto_1

    :sswitch_14
    const-string/jumbo v7, "web_app_switch_inline_query"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_17

    goto/16 :goto_0

    :cond_17
    const/16 v7, 0x2b

    goto/16 :goto_1

    :sswitch_15
    const-string/jumbo v7, "web_app_request_fullscreen"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_18

    goto/16 :goto_0

    :cond_18
    const/16 v7, 0x2a

    goto/16 :goto_1

    :sswitch_16
    const-string/jumbo v7, "web_app_add_to_home_screen"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_19

    goto/16 :goto_0

    :cond_19
    const/16 v7, 0x29

    goto/16 :goto_1

    :sswitch_17
    const-string/jumbo v7, "web_app_request_content_safe_area"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_1a

    goto/16 :goto_0

    :cond_1a
    const/16 v7, 0x28

    goto/16 :goto_1

    :sswitch_18
    const-string/jumbo v7, "web_app_data_send"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_1b

    goto/16 :goto_0

    :cond_1b
    const/16 v7, 0x27

    goto/16 :goto_1

    :sswitch_19
    const-string/jumbo v7, "web_app_send_prepared_message"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_1c

    goto/16 :goto_0

    :cond_1c
    const/16 v7, 0x26

    goto/16 :goto_1

    :sswitch_1a
    const-string/jumbo v7, "web_app_stop_accelerometer"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_1d

    goto/16 :goto_0

    :cond_1d
    const/16 v7, 0x25

    goto/16 :goto_1

    :sswitch_1b
    const-string/jumbo v7, "web_app_start_accelerometer"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_1e

    goto/16 :goto_0

    :cond_1e
    const/16 v7, 0x24

    goto/16 :goto_1

    :sswitch_1c
    const-string/jumbo v7, "web_app_device_storage_clear"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_1f

    goto/16 :goto_0

    :cond_1f
    const/16 v7, 0x23

    goto/16 :goto_1

    :sswitch_1d
    const-string/jumbo v7, "web_app_secure_storage_clear"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_20

    goto/16 :goto_0

    :cond_20
    const/16 v7, 0x22

    goto/16 :goto_1

    :sswitch_1e
    const-string/jumbo v7, "web_app_stop_gyroscope"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_21

    goto/16 :goto_0

    :cond_21
    const/16 v7, 0x21

    goto/16 :goto_1

    :sswitch_1f
    const-string/jumbo v7, "web_app_hide_keyboard"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_22

    goto/16 :goto_0

    :cond_22
    const/16 v7, 0x20

    goto/16 :goto_1

    :sswitch_20
    const-string/jumbo v7, "web_app_read_text_from_clipboard"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_23

    goto/16 :goto_0

    :cond_23
    const/16 v7, 0x1f

    goto/16 :goto_1

    :sswitch_21
    const-string/jumbo v7, "web_app_ready"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_24

    goto/16 :goto_0

    :cond_24
    const/16 v7, 0x1e

    goto/16 :goto_1

    :sswitch_22
    const-string/jumbo v7, "web_app_close"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_25

    goto/16 :goto_0

    :cond_25
    const/16 v7, 0x1d

    goto/16 :goto_1

    :sswitch_23
    const-string/jumbo v7, "web_app_start_gyroscope"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_26

    goto/16 :goto_0

    :cond_26
    const/16 v7, 0x1c

    goto/16 :goto_1

    :sswitch_24
    const-string/jumbo v7, "web_app_request_location"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_27

    goto/16 :goto_0

    :cond_27
    const/16 v7, 0x1b

    goto/16 :goto_1

    :sswitch_25
    const-string/jumbo v7, "web_app_share_to_story"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_28

    goto/16 :goto_0

    :cond_28
    const/16 v7, 0x1a

    goto/16 :goto_1

    :sswitch_26
    const-string/jumbo v7, "web_app_secure_storage_restore_key"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_29

    goto/16 :goto_0

    :cond_29
    const/16 v7, 0x19

    goto/16 :goto_1

    :sswitch_27
    const-string/jumbo v7, "web_app_open_tg_link"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_2a

    goto/16 :goto_0

    :cond_2a
    const/16 v7, 0x18

    goto/16 :goto_1

    :sswitch_28
    const-string/jumbo v7, "web_app_allow_scroll"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_2b

    goto/16 :goto_0

    :cond_2b
    const/16 v7, 0x17

    goto/16 :goto_1

    :sswitch_29
    const-string/jumbo v7, "web_app_toggle_orientation_lock"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_2c

    goto/16 :goto_0

    :cond_2c
    const/16 v7, 0x16

    goto/16 :goto_1

    :sswitch_2a
    const-string/jumbo v7, "web_app_biometry_request_auth"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_2d

    goto/16 :goto_0

    :cond_2d
    const/16 v7, 0x15

    goto/16 :goto_1

    :sswitch_2b
    const-string/jumbo v7, "web_app_device_storage_get_key"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_2e

    goto/16 :goto_0

    :cond_2e
    const/16 v7, 0x14

    goto/16 :goto_1

    :sswitch_2c
    const-string/jumbo v7, "web_app_device_storage_save_key"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_2f

    goto/16 :goto_0

    :cond_2f
    const/16 v7, 0x13

    goto/16 :goto_1

    :sswitch_2d
    const-string/jumbo v7, "web_app_stop_device_orientation"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_30

    goto/16 :goto_0

    :cond_30
    const/16 v7, 0x12

    goto/16 :goto_1

    :sswitch_2e
    const-string/jumbo v7, "web_app_request_emoji_status_access"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_31

    goto/16 :goto_0

    :cond_31
    const/16 v7, 0x11

    goto/16 :goto_1

    :sswitch_2f
    const-string/jumbo v7, "web_app_request_viewport"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_32

    goto/16 :goto_0

    :cond_32
    const/16 v7, 0x10

    goto/16 :goto_1

    :sswitch_30
    const-string/jumbo v7, "web_app_biometry_open_settings"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_33

    goto/16 :goto_0

    :cond_33
    const/16 v7, 0xf

    goto/16 :goto_1

    :sswitch_31
    const-string/jumbo v7, "web_app_check_location"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_34

    goto/16 :goto_0

    :cond_34
    const/16 v7, 0xe

    goto/16 :goto_1

    :sswitch_32
    const-string/jumbo v7, "web_app_secure_storage_get_key"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_35

    goto/16 :goto_0

    :cond_35
    const/16 v7, 0xd

    goto/16 :goto_1

    :sswitch_33
    const-string/jumbo v7, "web_app_request_theme"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_36

    goto/16 :goto_0

    :cond_36
    const/16 v7, 0xc

    goto/16 :goto_1

    :sswitch_34
    const-string/jumbo v7, "web_app_request_phone"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_37

    goto/16 :goto_0

    :cond_37
    const/16 v7, 0xb

    goto/16 :goto_1

    :sswitch_35
    const-string/jumbo v7, "web_app_open_scan_qr_popup"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_38

    goto/16 :goto_0

    :cond_38
    const/16 v7, 0xa

    goto/16 :goto_1

    :sswitch_36
    const-string/jumbo v7, "web_app_setup_closing_behavior"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_39

    goto/16 :goto_0

    :cond_39
    const/16 v7, 0x9

    goto/16 :goto_1

    :sswitch_37
    const-string/jumbo v7, "web_app_setup_secondary_button"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_3a

    goto/16 :goto_0

    :cond_3a
    const/16 v7, 0x8

    goto/16 :goto_1

    :sswitch_38
    const-string/jumbo v7, "web_app_set_emoji_status"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_3b

    goto/16 :goto_0

    :cond_3b
    const/4 v7, 0x7

    goto :goto_1

    :sswitch_39
    const-string/jumbo v7, "web_app_open_invoice"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_3c

    goto/16 :goto_0

    :cond_3c
    const/4 v7, 0x6

    goto :goto_1

    :sswitch_3a
    const-string/jumbo v7, "web_app_open_popup"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_3d

    goto/16 :goto_0

    :cond_3d
    const/4 v7, 0x5

    goto :goto_1

    :sswitch_3b
    const-string/jumbo v7, "web_app_request_file_download"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_3e

    goto/16 :goto_0

    :cond_3e
    const/4 v7, 0x4

    goto :goto_1

    :sswitch_3c
    const-string/jumbo v7, "web_app_open_link"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_3f

    goto/16 :goto_0

    :cond_3f
    const/4 v7, 0x3

    goto :goto_1

    :sswitch_3d
    const-string/jumbo v7, "web_app_biometry_get_info"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_40

    goto/16 :goto_0

    :cond_40
    const/4 v7, 0x2

    goto :goto_1

    :sswitch_3e
    const-string/jumbo v7, "web_app_close_scan_qr_popup"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_41

    goto/16 :goto_0

    :cond_41
    const/4 v7, 0x1

    goto :goto_1

    :sswitch_3f
    const-string/jumbo v7, "web_app_invoke_custom_method"

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_42

    goto/16 :goto_0

    :cond_42
    const/4 v7, 0x0

    .line 3177
    :goto_1
    const-string v12, "accelerometer_failed"

    const-string v10, "data"

    const-string v9, "fullscreen_failed"

    const-string v8, "is_fullscreen"

    const-string v14, "fullscreen_changed"

    const-string v11, "icon_custom_emoji_id"

    const-string v15, "has_shine_effect"

    const-string v13, "is_progress_visible"

    move/from16 v23, v7

    const-string v7, "is_active"

    move-object/from16 v24, v6

    const-string v6, "device_orientation_failed"

    move-object/from16 v25, v12

    const-string v12, "JSON Parse error"

    move-object/from16 v26, v5

    const-string v5, "failed"

    move-object/from16 v27, v3

    const-string/jumbo v3, "url"

    move-object/from16 v28, v3

    const-string/jumbo v3, "refresh_rate"

    const-wide/16 v29, 0x3e8

    move-object/from16 v31, v10

    const-string/jumbo v10, "reason"

    move-object/from16 v32, v9

    const-string/jumbo v9, "text_color"

    move-object/from16 v33, v14

    const-string/jumbo v14, "text"

    move-object/from16 v34, v8

    const-string v8, "is_visible"

    move-object/from16 v35, v11

    const-string/jumbo v11, "req_id"

    move-object/from16 v36, v15

    const-string v15, "cancelled"

    move-object/from16 v37, v13

    move-object/from16 v38, v14

    const-string v13, "UNSUPPORTED"

    const-string v14, "color"

    move-object/from16 v39, v9

    const-string/jumbo v9, "status"

    const-wide/16 v40, 0x2710

    move-object/from16 v42, v7

    const-string v7, "error"

    move-object/from16 v43, v8

    const/4 v8, 0x0

    packed-switch v23, :pswitch_data_0

    const-string/jumbo v1, "unknown webapp event "

    invoke-virtual {v1, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    return-void

    .line 2035
    :pswitch_0
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppExpand()V

    return-void

    :pswitch_1
    const/4 v3, 0x3

    .line 2108
    invoke-direct {v1, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->ignoreDialog(I)Z

    move-result v0

    if-eqz v0, :cond_43

    .line 2110
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 2111
    invoke-virtual {v0, v9, v15}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2112
    const-string/jumbo v3, "write_access_requested"

    invoke-direct {v1, v2, v3, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    .line 2114
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void

    .line 2119
    :cond_43
    iget v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    .line 2120
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    .line 2121
    new-instance v4, Lorg/telegram/tgnet/tl/TL_bots$canSendMessage;

    invoke-direct {v4}, Lorg/telegram/tgnet/tl/TL_bots$canSendMessage;-><init>()V

    .line 2122
    iget v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v5}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v5

    iget-object v6, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-virtual {v5, v6}, Lorg/telegram/messenger/MessagesController;->getInputUser(Lorg/telegram/tgnet/TLRPC$User;)Lorg/telegram/tgnet/TLRPC$InputUser;

    move-result-object v5

    iput-object v5, v4, Lorg/telegram/tgnet/tl/TL_bots$canSendMessage;->bot:Lorg/telegram/tgnet/TLRPC$InputUser;

    .line 2123
    iget v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v5}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v5

    new-instance v6, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda26;

    invoke-direct {v6, v1, v0, v3, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda26;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v5, v4, v6}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    return-void

    .line 1822
    :pswitch_2
    :try_start_1
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1823
    iget-object v1, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    const-string v2, "#ffffff"

    invoke-virtual {v0, v14, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    const/high16 v2, -0x1000000

    or-int/2addr v0, v2

    invoke-interface {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppSetBackgroundColor(I)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    return-void

    :catch_1
    move-exception v0

    .line 1825
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 2757
    :pswitch_3
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastInsets:Landroid/graphics/Rect;

    const/4 v2, 0x1

    invoke-direct {v1, v0, v2}, Lorg/telegram/ui/web/BotWebViewContainer;->reportSafeInsets(Landroid/graphics/Rect;Z)V

    return-void

    .line 1831
    :pswitch_4
    :try_start_2
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1832
    invoke-virtual {v0, v14, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 1833
    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_44

    .line 1834
    invoke-static {v2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    if-eqz v0, :cond_b3

    .line 1836
    iget-object v1, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    const/4 v2, -0x1

    const/4 v3, 0x1

    invoke-interface {v1, v2, v0, v3}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppSetActionBarColor(IIZ)V

    return-void

    .line 1839
    :cond_44
    const-string v2, "color_key"

    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1841
    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v2

    const v3, -0x4b676917

    if-eq v2, v3, :cond_46

    const v3, -0xc9046ac

    if-eq v2, v3, :cond_45

    goto :goto_2

    :cond_45
    const-string/jumbo v2, "secondary_bg_color"

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_47

    .line 1847
    sget v11, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    goto :goto_3

    .line 1841
    :cond_46
    const-string v2, "bg_color"

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_47

    .line 1843
    sget v11, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    goto :goto_3

    :cond_47
    :goto_2
    const/4 v11, -0x1

    :goto_3
    if-ltz v11, :cond_b3

    .line 1852
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    iget-object v1, v1, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v11, v1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v1

    const/4 v2, 0x0

    invoke-interface {v0, v11, v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppSetActionBarColor(IIZ)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    return-void

    :catch_2
    move-exception v0

    .line 1856
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 1862
    :pswitch_5
    :try_start_3
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1863
    invoke-virtual {v0, v14, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1865
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_48

    .line 1866
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    iget-object v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    goto :goto_4

    .line 1868
    :cond_48
    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    .line 1870
    :goto_4
    iget-object v1, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz v1, :cond_b3

    .line 1871
    invoke-interface {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppSetNavigationBarColor(I)V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_3

    return-void

    :catch_3
    move-exception v0

    .line 1874
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 2406
    :pswitch_6
    :try_start_4
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2407
    const-string/jumbo v3, "token"

    invoke-virtual {v0, v3}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_6

    .line 2409
    :try_start_5
    invoke-virtual {v0, v10}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_4

    .line 2420
    :catch_4
    invoke-direct {v1}, Lorg/telegram/ui/web/BotWebViewContainer;->createBiometry()V

    .line 2421
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    if-nez v0, :cond_49

    goto/16 :goto_31

    .line 2424
    :cond_49
    iget-boolean v4, v0, Lorg/telegram/ui/bots/BotBiometry;->access_granted:Z

    if-nez v4, :cond_4a

    .line 2426
    :try_start_6
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 2427
    invoke-virtual {v0, v9, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2428
    const-string v3, "biometry_token_updated"

    invoke-direct {v1, v2, v3, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_5

    goto/16 :goto_31

    :catch_5
    move-exception v0

    .line 2430
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 2434
    :cond_4a
    new-instance v4, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda10;

    invoke-direct {v4, v1, v3, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda10;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v0, v8, v3, v4}, Lorg/telegram/ui/bots/BotBiometry;->updateToken(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V

    goto/16 :goto_31

    :catch_6
    move-exception v0

    .line 2412
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 2413
    instance-of v0, v0, Lorg/json/JSONException;

    if-eqz v0, :cond_4b

    .line 2414
    invoke-direct {v1, v12}, Lorg/telegram/ui/web/BotWebViewContainer;->error(Ljava/lang/String;)V

    goto/16 :goto_31

    .line 2416
    :cond_4b
    invoke-direct {v1}, Lorg/telegram/ui/web/BotWebViewContainer;->unknownError()V

    goto/16 :goto_31

    .line 2667
    :pswitch_7
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->getBotSensors()Lorg/telegram/ui/bots/BotSensors;

    move-result-object v0

    .line 2671
    :try_start_7
    new-instance v5, Lorg/json/JSONObject;

    invoke-direct {v5, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2672
    invoke-virtual {v5, v3}, Lorg/json/JSONObject;->getLong(Ljava/lang/String;)J

    move-result-wide v29

    .line 2673
    const-string/jumbo v3, "need_absolute"

    const/4 v4, 0x0

    invoke-virtual {v5, v3, v4}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v14
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_7 .. :try_end_7} :catch_7

    :goto_5
    move-wide/from16 v15, v29

    goto :goto_6

    :catch_7
    const/4 v14, 0x0

    goto :goto_5

    :goto_6
    const-wide/16 v17, 0x3e8

    const-wide/16 v19, 0x14

    .line 2675
    invoke-static/range {v15 .. v20}, Lorg/telegram/messenger/Utilities;->clamp(JJJ)J

    move-result-wide v3

    if-eqz v0, :cond_4c

    .line 2676
    invoke-virtual {v0, v14, v3, v4}, Lorg/telegram/ui/bots/BotSensors;->startOrientation(ZJ)Z

    move-result v0

    if-eqz v0, :cond_4c

    .line 2677
    const-string v0, "device_orientation_started"

    invoke-direct {v1, v2, v0, v8}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    goto/16 :goto_31

    .line 2679
    :cond_4c
    invoke-static {v7, v13}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {v1, v2, v6, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    goto/16 :goto_31

    .line 2990
    :pswitch_8
    :try_start_8
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2991
    invoke-virtual {v0, v11}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8
    :try_end_8
    .catch Ljava/lang/Exception; {:try_start_8 .. :try_end_8} :catch_8

    goto :goto_7

    :catch_8
    move-exception v0

    .line 2993
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :goto_7
    if-nez v8, :cond_4d

    goto/16 :goto_31

    .line 2998
    :cond_4d
    new-instance v0, Lorg/telegram/tgnet/tl/TL_bots$getRequestedWebViewButton;

    invoke-direct {v0}, Lorg/telegram/tgnet/tl/TL_bots$getRequestedWebViewButton;-><init>()V

    .line 2999
    iget v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v3}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v3

    iget-object v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-virtual {v3, v4}, Lorg/telegram/messenger/MessagesController;->getInputUser(Lorg/telegram/tgnet/TLRPC$User;)Lorg/telegram/tgnet/TLRPC$InputUser;

    move-result-object v3

    iput-object v3, v0, Lorg/telegram/tgnet/tl/TL_bots$getRequestedWebViewButton;->bot:Lorg/telegram/tgnet/TLRPC$InputUser;

    .line 3000
    iput-object v8, v0, Lorg/telegram/tgnet/tl/TL_bots$getRequestedWebViewButton;->webapp_req_id:Ljava/lang/String;

    .line 3001
    iget v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v3}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v3

    new-instance v4, Lorg/telegram/messenger/AiTonesController$$ExternalSyntheticLambda0;

    invoke-direct {v4}, Lorg/telegram/messenger/AiTonesController$$ExternalSyntheticLambda0;-><init>()V

    new-instance v5, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda22;

    invoke-direct {v5, v1, v2, v8}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda22;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;)V

    invoke-virtual {v3, v0, v4, v5}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequestTyped(Lorg/telegram/tgnet/TLMethod;Ljava/util/concurrent/Executor;Lorg/telegram/messenger/Utilities$Callback2;)I

    goto/16 :goto_31

    .line 2711
    :pswitch_9
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-eqz v0, :cond_4f

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0x1a

    if-lt v0, v3, :cond_4f

    .line 2712
    iget v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MediaDataController;->getInstance(I)Lorg/telegram/messenger/MediaDataController;

    move-result-object v0

    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v3, v3, Lorg/telegram/tgnet/TLRPC$User;->id:J

    sget v5, Lorg/telegram/messenger/MediaDataController;->SHORTCUT_TYPE_ATTACHED_BOT:I

    invoke-virtual {v0, v3, v4, v5}, Lorg/telegram/messenger/MediaDataController;->isShortcutAdded(JI)Z

    move-result v0

    if-eqz v0, :cond_4e

    const-string v0, "added"

    goto :goto_8

    :cond_4e
    const-string/jumbo v0, "missed"

    goto :goto_8

    .line 2713
    :cond_4f
    const-string/jumbo v0, "unsupported"

    .line 2710
    :goto_8
    invoke-static {v9, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    const-string v3, "home_screen_checked"

    invoke-direct {v1, v2, v3, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 1993
    :pswitch_a
    :try_start_9
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    move-object/from16 v2, v43

    .line 1994
    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;)Z

    move-result v0

    .line 1995
    iget-boolean v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isSettingsButtonVisible:Z

    if-eq v0, v2, :cond_b3

    .line 1996
    iput-boolean v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isSettingsButtonVisible:Z

    .line 1998
    iget-object v1, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onSetSettingsButtonVisible(Z)V
    :try_end_9
    .catch Lorg/json/JSONException; {:try_start_9 .. :try_end_9} :catch_9

    return-void

    :catch_9
    move-exception v0

    .line 2001
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 1813
    :pswitch_b
    :try_start_a
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1814
    iget-object v1, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    const-string v2, "allow_vertical_swipe"

    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;)Z

    move-result v0

    invoke-interface {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppSwipingBehavior(Z)V
    :try_end_a
    .catch Lorg/json/JSONException; {:try_start_a .. :try_end_a} :catch_a

    return-void

    :catch_a
    move-exception v0

    .line 1816
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    :pswitch_c
    move-object/from16 v2, v43

    .line 2053
    :try_start_b
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    move-object/from16 v3, v42

    const/4 v5, 0x0

    .line 2054
    invoke-virtual {v0, v3, v5}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v24

    .line 2055
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastButtonText:Ljava/lang/String;

    move-object/from16 v6, v38

    invoke-virtual {v0, v6, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    .line 2056
    invoke-virtual {v0, v2, v5}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v2

    if-eqz v2, :cond_50

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_50

    const/16 v23, 0x1

    goto :goto_9

    :cond_50
    const/16 v23, 0x0

    .line 2057
    :goto_9
    invoke-virtual {v0, v14}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_51

    invoke-virtual {v0, v14}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v2

    :goto_a
    move-object/from16 v5, v39

    goto :goto_b

    :cond_51
    iget v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastButtonColor:I

    goto :goto_a

    .line 2058
    :goto_b
    invoke-virtual {v0, v5}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_52

    invoke-virtual {v0, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v5

    :goto_c
    move-object/from16 v7, v37

    const/4 v6, 0x0

    goto :goto_d

    :cond_52
    iget v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastButtonTextColor:I

    goto :goto_c

    .line 2059
    :goto_d
    invoke-virtual {v0, v7, v6}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v7

    if-eqz v7, :cond_53

    if-eqz v23, :cond_53

    const/16 v30, 0x1

    :goto_e
    move-object/from16 v8, v36

    goto :goto_f

    :cond_53
    move/from16 v30, v6

    goto :goto_e

    .line 2060
    :goto_f
    invoke-virtual {v0, v8, v6}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v7
    :try_end_b
    .catch Ljava/lang/Exception; {:try_start_b .. :try_end_b} :catch_b

    if-eqz v7, :cond_54

    if-eqz v23, :cond_54

    const/16 v31, 0x1

    :goto_10
    move-object/from16 v9, v35

    goto :goto_11

    :cond_54
    const/16 v31, 0x0

    goto :goto_10

    .line 2063
    :goto_11
    :try_start_c
    invoke-virtual {v0, v9}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v13
    :try_end_c
    .catchall {:try_start_c .. :try_end_c} :catchall_0

    move-wide/from16 v26, v13

    goto :goto_12

    :catchall_0
    const-wide/16 v26, 0x0

    .line 2067
    :goto_12
    :try_start_d
    iput v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastButtonColor:I

    .line 2068
    iput v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastButtonTextColor:I

    .line 2069
    iput-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastButtonText:Ljava/lang/String;

    .line 2070
    iput-object v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->buttonData:Ljava/lang/String;

    .line 2072
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    move-object/from16 v22, v0

    move/from16 v28, v2

    move-object/from16 v25, v3

    move/from16 v29, v5

    invoke-interface/range {v22 .. v31}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onSetupMainButton(ZZLjava/lang/String;JIIZZ)V
    :try_end_d
    .catch Ljava/lang/Exception; {:try_start_d .. :try_end_d} :catch_b

    goto/16 :goto_31

    :catch_b
    move-exception v0

    .line 2074
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 1889
    :pswitch_d
    :try_start_e
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1890
    const-string/jumbo v1, "type"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 1893
    invoke-virtual {v1}, Ljava/lang/String;->hashCode()I

    move-result v2

    const v3, -0x469ec2ba

    if-eq v2, v3, :cond_5a

    const v3, 0xb8209c3

    if-eq v2, v3, :cond_59

    const v3, 0x237a88eb

    if-eq v2, v3, :cond_55

    goto/16 :goto_13

    :cond_55
    const-string/jumbo v2, "notification"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_5b

    .line 1920
    const-string/jumbo v1, "notification_type"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v1

    const v2, -0x6f4abffd

    if-eq v1, v2, :cond_58

    const v2, 0x5c4d208

    if-eq v1, v2, :cond_57

    const v2, 0x4305af9c

    if-eq v1, v2, :cond_56

    goto/16 :goto_13

    :cond_56
    const-string/jumbo v1, "warning"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5b

    .line 1930
    sget-object v8, Lorg/telegram/messenger/BotWebViewVibrationEffect;->NOTIFICATION_WARNING:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    goto/16 :goto_13

    .line 1920
    :cond_57
    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5b

    .line 1922
    sget-object v8, Lorg/telegram/messenger/BotWebViewVibrationEffect;->NOTIFICATION_ERROR:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    goto :goto_13

    .line 1920
    :cond_58
    const-string/jumbo v1, "success"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5b

    .line 1926
    sget-object v8, Lorg/telegram/messenger/BotWebViewVibrationEffect;->NOTIFICATION_SUCCESS:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    goto :goto_13

    .line 1893
    :cond_59
    const-string/jumbo v0, "selection_change"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5b

    .line 1937
    sget-object v8, Lorg/telegram/messenger/BotWebViewVibrationEffect;->SELECTION_CHANGE:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    goto :goto_13

    .line 1893
    :cond_5a
    const-string v2, "impact"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_5b

    .line 1895
    const-string v1, "impact_style"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v1

    sparse-switch v1, :sswitch_data_1

    goto :goto_13

    :sswitch_40
    const-string/jumbo v1, "rigid"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5b

    .line 1909
    sget-object v8, Lorg/telegram/messenger/BotWebViewVibrationEffect;->IMPACT_RIGID:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    goto :goto_13

    .line 1895
    :sswitch_41
    const-string v1, "light"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5b

    .line 1897
    sget-object v8, Lorg/telegram/messenger/BotWebViewVibrationEffect;->IMPACT_LIGHT:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    goto :goto_13

    .line 1895
    :sswitch_42
    const-string v1, "heavy"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5b

    .line 1905
    sget-object v8, Lorg/telegram/messenger/BotWebViewVibrationEffect;->IMPACT_HEAVY:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    goto :goto_13

    .line 1895
    :sswitch_43
    const-string/jumbo v1, "soft"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5b

    .line 1913
    sget-object v8, Lorg/telegram/messenger/BotWebViewVibrationEffect;->IMPACT_SOFT:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    goto :goto_13

    .line 1895
    :sswitch_44
    const-string v1, "medium"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5b

    .line 1901
    sget-object v8, Lorg/telegram/messenger/BotWebViewVibrationEffect;->IMPACT_MEDIUM:Lorg/telegram/messenger/BotWebViewVibrationEffect;

    :cond_5b
    :goto_13
    if-eqz v8, :cond_b3

    .line 1942
    invoke-virtual {v8}, Lorg/telegram/messenger/BotWebViewVibrationEffect;->vibrate()V
    :try_end_e
    .catch Ljava/lang/Exception; {:try_start_e .. :try_end_e} :catch_c

    return-void

    :catch_c
    move-exception v0

    .line 1945
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 2302
    :pswitch_e
    :try_start_f
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2303
    invoke-virtual {v0, v10}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8
    :try_end_f
    .catch Ljava/lang/Exception; {:try_start_f .. :try_end_f} :catch_d

    .line 2306
    :catch_d
    invoke-direct {v1}, Lorg/telegram/ui/web/BotWebViewContainer;->createBiometry()V

    .line 2307
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    if-nez v0, :cond_5c

    goto/16 :goto_31

    .line 2310
    :cond_5c
    iget-boolean v3, v0, Lorg/telegram/ui/bots/BotBiometry;->access_requested:Z

    if-eqz v3, :cond_5d

    .line 2311
    invoke-direct {v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyBiometryReceived(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    return-void

    .line 2314
    :cond_5d
    iget-boolean v4, v0, Lorg/telegram/ui/bots/BotBiometry;->access_granted:Z

    if-nez v4, :cond_5f

    .line 2315
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda5;

    invoke-direct {v0, v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Runnable;

    const/16 v20, 0x0

    aput-object v0, v3, v20

    .line 2320
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v4

    iget-object v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v0, v4, v5}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 2321
    invoke-static {v8}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-eqz v4, :cond_5e

    .line 2322
    sget v4, Lorg/telegram/messenger/R$string;->BotAllowBiometryTitle:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v4}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 2323
    sget v4, Lorg/telegram/messenger/R$string;->BotAllowBiometryMessage:I

    iget-object v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-static {v5}, Lorg/telegram/messenger/UserObject;->getUserName(Lorg/telegram/tgnet/TLRPC$User;)Ljava/lang/String;

    move-result-object v5

    filled-new-array {v5}, [Ljava/lang/Object;

    move-result-object v5

    invoke-static {v4, v5}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v4

    invoke-virtual {v0, v4}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    goto :goto_14

    .line 2325
    :cond_5e
    sget v4, Lorg/telegram/messenger/R$string;->BotAllowBiometryMessage:I

    iget-object v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-static {v5}, Lorg/telegram/messenger/UserObject;->getUserName(Lorg/telegram/tgnet/TLRPC$User;)Ljava/lang/String;

    move-result-object v5

    filled-new-array {v5}, [Ljava/lang/Object;

    move-result-object v5

    invoke-static {v4, v5}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v4

    invoke-virtual {v0, v4}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 2326
    invoke-virtual {v0, v8}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 2328
    :goto_14
    sget v4, Lorg/telegram/messenger/R$string;->Allow:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    new-instance v5, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda6;

    invoke-direct {v5, v1, v3, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/Runnable;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v0, v4, v5}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 2342
    sget v4, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    new-instance v5, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda7;

    invoke-direct {v5, v1, v3, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda7;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/Runnable;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v0, v4, v5}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 2351
    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda8;

    invoke-direct {v1, v3}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda8;-><init>([Ljava/lang/Runnable;)V

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 2357
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->show()Lorg/telegram/ui/ActionBar/AlertDialog;

    goto/16 :goto_31

    :cond_5f
    if-nez v3, :cond_60

    const/4 v3, 0x1

    .line 2360
    iput-boolean v3, v0, Lorg/telegram/ui/bots/BotBiometry;->access_requested:Z

    .line 2361
    invoke-virtual {v0}, Lorg/telegram/ui/bots/BotBiometry;->save()V

    .line 2363
    :cond_60
    invoke-direct {v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyBiometryReceived(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    goto/16 :goto_31

    :pswitch_f
    move-object/from16 v2, v43

    .line 1979
    :try_start_10
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1980
    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;)Z

    move-result v0

    .line 1981
    iget-boolean v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isBackButtonVisible:Z

    if-eq v0, v2, :cond_b3

    .line 1982
    iput-boolean v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isBackButtonVisible:Z

    .line 1984
    iget-object v1, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onSetBackButtonVisible(Z)V
    :try_end_10
    .catch Lorg/json/JSONException; {:try_start_10 .. :try_end_10} :catch_e

    return-void

    :catch_e
    move-exception v0

    .line 1987
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 2799
    :pswitch_10
    iget-boolean v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestingPageOpen:Z

    if-nez v0, :cond_b3

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-eqz v0, :cond_b3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    iget-wide v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    sub-long/2addr v2, v4

    cmp-long v0, v2, v40

    if-lez v0, :cond_61

    goto/16 :goto_31

    :cond_61
    const-wide/16 v2, 0x0

    .line 2803
    iput-wide v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 2805
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v0

    if-eqz v0, :cond_b3

    .line 2806
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v2

    if-nez v2, :cond_62

    goto/16 :goto_31

    .line 2807
    :cond_62
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v2

    .line 2808
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v3, v3, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-static {v3, v4}, Lorg/telegram/ui/ProfileActivity;->of(J)Lorg/telegram/ui/ProfileActivity;

    move-result-object v3

    invoke-virtual {v0, v3}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    .line 2809
    const-string v0, "botPermissionLocation"

    invoke-static {v2, v0}, Lorg/telegram/messenger/AndroidUtilities;->scrollToFragmentRow(Lorg/telegram/ui/ActionBar/INavigationLayout;Ljava/lang/String;)V

    .line 2810
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz v0, :cond_b3

    .line 2811
    invoke-interface {v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onCloseToTabs()V

    return-void

    .line 2966
    :pswitch_11
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->onVerifiedAge:Lorg/telegram/messenger/Utilities$Callback4;

    if-eqz v0, :cond_b3

    .line 2972
    :try_start_11
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2973
    const-string/jumbo v2, "passed"

    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->getBoolean(Ljava/lang/String;)Z

    move-result v2

    .line 2974
    const-string v3, "age"

    invoke-virtual {v0, v3}, Lorg/json/JSONObject;->getDouble(Ljava/lang/String;)D

    move-result-wide v3

    .line 2975
    const-string v5, "gender"

    invoke-virtual {v0, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 2976
    const-string v6, "genderProbability"

    invoke-virtual {v0, v6}, Lorg/json/JSONObject;->optDouble(Ljava/lang/String;)D

    move-result-wide v6
    :try_end_11
    .catch Ljava/lang/Exception; {:try_start_11 .. :try_end_11} :catch_f

    .line 2981
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda21;

    invoke-direct/range {v0 .. v7}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda21;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;ZDLjava/lang/String;D)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    :catch_f
    move-exception v0

    .line 2978
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void

    .line 2613
    :pswitch_12
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    const/4 v3, 0x1

    const/4 v4, 0x0

    invoke-interface {v0, v4, v3}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onFullscreenRequested(ZZ)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_63

    .line 2614
    sget-object v0, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    move-object/from16 v3, v34

    invoke-static {v3, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    move-object/from16 v4, v33

    invoke-direct {v1, v2, v4, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2616
    :cond_63
    invoke-static {v7, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    move-object/from16 v5, v32

    invoke-direct {v1, v2, v5, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2934
    :pswitch_13
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v0, :cond_64

    goto/16 :goto_31

    .line 2935
    :cond_64
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    if-nez v0, :cond_65

    new-instance v5, Lorg/telegram/ui/bots/BotStorage;

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v6

    iget v7, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v7}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->getClientUserId()J

    move-result-wide v8

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v10, v0, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const/4 v12, 0x1

    invoke-direct/range {v5 .. v12}, Lorg/telegram/ui/bots/BotStorage;-><init>(Landroid/content/Context;IJJZ)V

    iput-object v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    .line 2936
    :cond_65
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    const-string/jumbo v5, "secure_storage_key_saved"

    const-string/jumbo v6, "secure_storage_failed"

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer;->setStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :pswitch_14
    move-object v0, v4

    .line 1604
    :try_start_12
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1605
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 1606
    const-string v3, "chat_types"

    invoke-virtual {v2, v3}, Lorg/json/JSONObject;->getJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v3

    const/4 v14, 0x0

    .line 1607
    :goto_15
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v4

    if-ge v14, v4, :cond_66

    .line 1608
    invoke-virtual {v3, v14}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-interface {v0, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 v14, v14, 0x1

    goto :goto_15

    .line 1611
    :cond_66
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    iget-object v1, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    const-string/jumbo v4, "query"

    invoke-virtual {v2, v4}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-interface {v3, v1, v2, v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppSwitchInlineQuery(Lorg/telegram/tgnet/TLRPC$User;Ljava/lang/String;Ljava/util/List;)V
    :try_end_12
    .catch Lorg/json/JSONException; {:try_start_12 .. :try_end_12} :catch_10

    return-void

    :catch_10
    move-exception v0

    .line 1613
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    :pswitch_15
    move-object v0, v4

    move-object/from16 v5, v32

    move-object/from16 v4, v33

    move-object/from16 v3, v34

    .line 2600
    :try_start_13
    new-instance v6, Lorg/json/JSONObject;

    invoke-direct {v6, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2601
    const-string v0, "blur"
    :try_end_13
    .catch Ljava/lang/Exception; {:try_start_13 .. :try_end_13} :catch_11

    const/4 v8, 0x1

    :try_start_14
    invoke-virtual {v6, v0, v8}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v21
    :try_end_14
    .catch Ljava/lang/Exception; {:try_start_14 .. :try_end_14} :catch_12

    move/from16 v0, v21

    goto :goto_16

    :catch_11
    const/4 v8, 0x1

    :catch_12
    move v0, v8

    .line 2604
    :goto_16
    iget-object v6, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v6, v8, v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onFullscreenRequested(ZZ)Ljava/lang/String;

    move-result-object v6

    if-nez v6, :cond_67

    .line 2605
    sget-object v5, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    const-string v6, "blur_enabled"

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-static {v3, v5, v6, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {v1, v2, v4, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    goto/16 :goto_31

    .line 2607
    :cond_67
    invoke-static {v7, v6}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {v1, v2, v5, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    goto/16 :goto_31

    .line 2693
    :pswitch_16
    iget-boolean v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestingPageOpen:Z

    if-nez v0, :cond_b3

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-eqz v0, :cond_b3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    iget-wide v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    sub-long/2addr v3, v5

    cmp-long v0, v3, v40

    if-lez v0, :cond_68

    goto/16 :goto_31

    .line 2696
    :cond_68
    iget v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MediaDataController;->getInstance(I)Lorg/telegram/messenger/MediaDataController;

    move-result-object v0

    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v3, v3, Lorg/telegram/tgnet/TLRPC$User;->id:J

    sget v5, Lorg/telegram/messenger/MediaDataController;->SHORTCUT_TYPE_ATTACHED_BOT:I

    invoke-virtual {v0, v3, v4, v5}, Lorg/telegram/messenger/MediaDataController;->isShortcutAdded(JI)Z

    move-result v0

    if-eqz v0, :cond_69

    .line 2697
    const-string v0, "home_screen_added"

    invoke-direct {v1, v2, v0, v8}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2700
    :cond_69
    iget v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MediaDataController;->getInstance(I)Lorg/telegram/messenger/MediaDataController;

    move-result-object v0

    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v3, v3, Lorg/telegram/tgnet/TLRPC$User;->id:J

    sget v5, Lorg/telegram/messenger/MediaDataController;->SHORTCUT_TYPE_ATTACHED_BOT:I

    new-instance v6, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda12;

    invoke-direct {v6, v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda12;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v0, v3, v4, v5, v6}, Lorg/telegram/messenger/MediaDataController;->installShortcut(JILorg/telegram/messenger/Utilities$Callback;)V

    return-void

    .line 2761
    :pswitch_17
    iget v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastInsetsTopMargin:I

    const/4 v3, 0x1

    invoke-direct {v1, v0, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->reportSafeContentInsets(IZ)V

    return-void

    :pswitch_18
    move-object v0, v4

    .line 1880
    :try_start_15
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1881
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    move-object/from16 v1, v31

    invoke-virtual {v2, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onSendWebViewData(Ljava/lang/String;)V
    :try_end_15
    .catch Lorg/json/JSONException; {:try_start_15 .. :try_end_15} :catch_13

    return-void

    :catch_13
    move-exception v0

    .line 1883
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    :pswitch_19
    move-object v0, v4

    .line 2864
    iget-boolean v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestingPageOpen:Z

    if-nez v3, :cond_b3

    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-eqz v3, :cond_b3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    iget-wide v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    sub-long/2addr v3, v5

    cmp-long v3, v3, v40

    if-lez v3, :cond_6a

    goto/16 :goto_31

    .line 2870
    :cond_6a
    :try_start_16
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2871
    const-string v0, "id"

    invoke-virtual {v3, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v12
    :try_end_16
    .catch Ljava/lang/Exception; {:try_start_16 .. :try_end_16} :catch_14

    .line 2877
    invoke-static {v12}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_6b

    move-object/from16 v3, v27

    .line 2878
    invoke-static {v7, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    move-object/from16 v4, v26

    invoke-direct {v1, v2, v4, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2882
    :cond_6b
    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v8

    iget v9, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v10, v0, Lorg/telegram/tgnet/TLRPC$User;->id:J

    iget-object v13, v1, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    new-instance v14, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda19;

    invoke-direct {v14, v1}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda19;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;)V

    new-instance v15, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda20;

    move-object/from16 v0, p1

    invoke-direct {v15, v1, v2, v0}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda20;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;)V

    invoke-static/range {v8 .. v15}, Lorg/telegram/ui/bots/BotShareSheet;->share(Landroid/content/Context;IJLjava/lang/String;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Ljava/lang/Runnable;Lorg/telegram/messenger/Utilities$Callback2;)V

    return-void

    :catch_14
    move-exception v0

    move-object/from16 v4, v26

    move-object/from16 v3, v27

    .line 2873
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 2874
    invoke-static {v7, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {v1, v2, v4, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2635
    :pswitch_1a
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->getBotSensors()Lorg/telegram/ui/bots/BotSensors;

    move-result-object v0

    if-eqz v0, :cond_6c

    .line 2636
    invoke-virtual {v0}, Lorg/telegram/ui/bots/BotSensors;->stopAccelerometer()Z

    move-result v0

    if-eqz v0, :cond_6c

    .line 2637
    const-string v0, "accelerometer_stopped"

    invoke-direct {v1, v2, v0, v8}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2639
    :cond_6c
    invoke-static {v7, v13}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    move-object/from16 v4, v25

    invoke-direct {v1, v2, v4, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :pswitch_1b
    move-object v0, v4

    move-object/from16 v4, v25

    .line 2621
    iget-object v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v5}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->getBotSensors()Lorg/telegram/ui/bots/BotSensors;

    move-result-object v5

    .line 2624
    :try_start_17
    new-instance v6, Lorg/json/JSONObject;

    invoke-direct {v6, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    invoke-virtual {v6, v3}, Lorg/json/JSONObject;->getLong(Ljava/lang/String;)J

    move-result-wide v29
    :try_end_17
    .catch Ljava/lang/Exception; {:try_start_17 .. :try_end_17} :catch_15

    :catch_15
    move-wide/from16 v14, v29

    const-wide/16 v16, 0x3e8

    const-wide/16 v18, 0x14

    .line 2626
    invoke-static/range {v14 .. v19}, Lorg/telegram/messenger/Utilities;->clamp(JJJ)J

    move-result-wide v9

    if-eqz v5, :cond_6d

    .line 2627
    invoke-virtual {v5, v9, v10}, Lorg/telegram/ui/bots/BotSensors;->startAccelerometer(J)Z

    move-result v0

    if-eqz v0, :cond_6d

    .line 2628
    const-string v0, "accelerometer_started"

    invoke-direct {v1, v2, v0, v8}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    goto/16 :goto_31

    .line 2630
    :cond_6d
    invoke-static {v7, v13}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {v1, v2, v4, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    goto/16 :goto_31

    :pswitch_1c
    move-object v0, v4

    .line 2928
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v3, :cond_6e

    goto/16 :goto_31

    .line 2929
    :cond_6e
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    if-nez v3, :cond_6f

    new-instance v4, Lorg/telegram/ui/bots/BotStorage;

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v5

    iget v6, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v6}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v3

    invoke-virtual {v3}, Lorg/telegram/messenger/UserConfig;->getClientUserId()J

    move-result-wide v7

    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v9, v3, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const/4 v11, 0x0

    invoke-direct/range {v4 .. v11}, Lorg/telegram/ui/bots/BotStorage;-><init>(Landroid/content/Context;IJJZ)V

    iput-object v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    .line 2930
    :cond_6f
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    const-string v5, "device_storage_cleared"

    const-string v6, "device_storage_failed"

    move-object v4, v0

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer;->clearStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void

    .line 2946
    :pswitch_1d
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v0, :cond_70

    goto/16 :goto_31

    .line 2947
    :cond_70
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    if-nez v0, :cond_71

    new-instance v2, Lorg/telegram/ui/bots/BotStorage;

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v3

    iget v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v4}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->getClientUserId()J

    move-result-wide v5

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v7, v0, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const/4 v9, 0x1

    invoke-direct/range {v2 .. v9}, Lorg/telegram/ui/bots/BotStorage;-><init>(Landroid/content/Context;IJJZ)V

    iput-object v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    .line 2948
    :cond_71
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    const-string/jumbo v5, "secure_storage_cleared"

    const-string/jumbo v6, "secure_storage_cleared"

    move-object/from16 v2, p2

    move-object/from16 v4, p4

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer;->clearStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :pswitch_1e
    move-object v5, v2

    move-object v2, v1

    .line 2658
    iget-object v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->getBotSensors()Lorg/telegram/ui/bots/BotSensors;

    move-result-object v0

    if-eqz v0, :cond_72

    .line 2659
    invoke-virtual {v0}, Lorg/telegram/ui/bots/BotSensors;->stopGyroscope()Z

    move-result v0

    if-eqz v0, :cond_72

    .line 2660
    const-string v0, "gyroscope_stopped"

    invoke-direct {v2, v5, v0, v8}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2662
    :cond_72
    const-string v0, "gyroscope_failed"

    invoke-static {v7, v13}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v1

    invoke-direct {v2, v5, v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :pswitch_1f
    move-object v2, v1

    .line 2958
    invoke-virtual {v2}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->findActivity(Landroid/content/Context;)Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_73

    .line 2959
    sget-object v0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    :cond_73
    if-eqz v0, :cond_b3

    .line 2961
    invoke-virtual {v0}, Landroid/app/Activity;->getCurrentFocus()Landroid/view/View;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->hideKeyboard(Landroid/view/View;)V

    return-void

    :pswitch_20
    move-object v5, v2

    move-object v2, v1

    move-object/from16 v1, v31

    .line 1619
    :try_start_18
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1620
    invoke-virtual {v0, v11}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1621
    iget-object v3, v2, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v3}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->isClipboardAvailable()Z

    move-result v3

    if-eqz v3, :cond_76

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    iget-wide v6, v2, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    sub-long/2addr v3, v6

    cmp-long v3, v3, v40

    if-lez v3, :cond_74

    goto :goto_18

    .line 1626
    :cond_74
    invoke-virtual {v2}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v3

    const-string v4, "clipboard"

    invoke-virtual {v3, v4}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/content/ClipboardManager;

    .line 1627
    invoke-virtual {v3}, Landroid/content/ClipboardManager;->getText()Ljava/lang/CharSequence;

    move-result-object v3

    if-eqz v3, :cond_75

    .line 1628
    invoke-interface {v3}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v3

    goto :goto_17

    :cond_75
    const-string v3, ""

    .line 1629
    :goto_17
    const-string v4, "clipboard_text_received"

    new-instance v6, Lorg/json/JSONObject;

    invoke-direct {v6}, Lorg/json/JSONObject;-><init>()V

    invoke-virtual {v6, v11, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-virtual {v0, v1, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {v2, v5, v4, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 1622
    :cond_76
    :goto_18
    const-string v1, "clipboard_text_received"

    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3}, Lorg/json/JSONObject;-><init>()V

    invoke-virtual {v3, v11, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {v2, v5, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_18
    .catch Lorg/json/JSONException; {:try_start_18 .. :try_end_18} :catch_16

    return-void

    :catch_16
    move-exception v0

    .line 1631
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    :pswitch_21
    move-object v2, v1

    .line 2048
    iget-object v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getUrl()Ljava/lang/String;

    move-result-object v0

    const/4 v3, 0x1

    invoke-virtual {v2, v0, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->setPageLoaded(Ljava/lang/String;Z)V

    return-void

    :pswitch_22
    move-object v2, v1

    .line 1570
    :try_start_19
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1571
    const-string/jumbo v1, "return_back"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;)Z

    move-result v0
    :try_end_19
    .catch Ljava/lang/Exception; {:try_start_19 .. :try_end_19} :catch_17

    goto :goto_19

    :catch_17
    move-exception v0

    .line 1573
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    const/4 v0, 0x0

    .line 1576
    :goto_19
    iget-object v1, v2, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v1, v8}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onCloseRequested(Ljava/lang/Runnable;)V

    if-eqz v0, :cond_b3

    .line 1578
    iget-boolean v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->wasOpenedByLinkIntent:Z

    if-eqz v0, :cond_78

    sget-object v0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    if-eqz v0, :cond_78

    .line 1579
    invoke-virtual {v2}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->findActivity(Landroid/content/Context;)Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_77

    .line 1580
    sget-object v0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    :cond_77
    if-eqz v0, :cond_b3

    .line 1581
    invoke-virtual {v0}, Landroid/app/Activity;->isFinishing()Z

    move-result v1

    if-nez v1, :cond_b3

    const/4 v3, 0x1

    .line 1582
    invoke-virtual {v0, v3}, Landroid/app/Activity;->moveTaskToBack(Z)Z

    goto/16 :goto_31

    .line 1584
    :cond_78
    iget-object v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->wasOpenedByBot:Lorg/telegram/ui/bots/WebViewRequestProps;

    if-eqz v0, :cond_b3

    sget-object v0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    if-eqz v0, :cond_b3

    invoke-virtual {v0}, Lorg/telegram/ui/LaunchActivity;->getBottomSheetTabs()Lorg/telegram/ui/ActionBar/BottomSheetTabs;

    move-result-object v0

    if-eqz v0, :cond_b3

    .line 1585
    sget-object v0, Lorg/telegram/ui/LaunchActivity;->instance:Lorg/telegram/ui/LaunchActivity;

    invoke-virtual {v0}, Lorg/telegram/ui/LaunchActivity;->getBottomSheetTabs()Lorg/telegram/ui/ActionBar/BottomSheetTabs;

    move-result-object v0

    .line 1586
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BottomSheetTabs;->getTabs()Ljava/util/ArrayList;

    move-result-object v1

    const/4 v14, 0x0

    .line 1588
    :goto_1a
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v14, v3, :cond_7a

    .line 1589
    invoke-virtual {v1, v14}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/telegram/ui/ActionBar/BottomSheetTabs$WebTabData;

    .line 1590
    iget-object v4, v2, Lorg/telegram/ui/web/BotWebViewContainer;->wasOpenedByBot:Lorg/telegram/ui/bots/WebViewRequestProps;

    iget-object v5, v3, Lorg/telegram/ui/ActionBar/BottomSheetTabs$WebTabData;->props:Lorg/telegram/ui/bots/WebViewRequestProps;

    invoke-virtual {v4, v5}, Lorg/telegram/ui/bots/WebViewRequestProps;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_79

    iget-object v4, v3, Lorg/telegram/ui/ActionBar/BottomSheetTabs$WebTabData;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    iget-object v5, v2, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eq v4, v5, :cond_79

    move-object v8, v3

    goto :goto_1b

    :cond_79
    add-int/lit8 v14, v14, 0x1

    goto :goto_1a

    :cond_7a
    :goto_1b
    if-eqz v8, :cond_b3

    .line 1596
    invoke-virtual {v0, v8}, Lorg/telegram/ui/ActionBar/BottomSheetTabs;->openTab(Lorg/telegram/ui/ActionBar/BottomSheetTabs$WebTabData;)V

    goto/16 :goto_31

    :pswitch_23
    move-object v5, v2

    move-object v2, v1

    .line 2644
    iget-object v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->getBotSensors()Lorg/telegram/ui/bots/BotSensors;

    move-result-object v0

    .line 2647
    :try_start_1a
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v3}, Lorg/json/JSONObject;->getLong(Ljava/lang/String;)J

    move-result-wide v29
    :try_end_1a
    .catch Ljava/lang/Exception; {:try_start_1a .. :try_end_1a} :catch_18

    :catch_18
    move-wide/from16 v14, v29

    const-wide/16 v16, 0x3e8

    const-wide/16 v18, 0x14

    .line 2649
    invoke-static/range {v14 .. v19}, Lorg/telegram/messenger/Utilities;->clamp(JJJ)J

    move-result-wide v3

    if-eqz v0, :cond_7b

    .line 2650
    invoke-virtual {v0, v3, v4}, Lorg/telegram/ui/bots/BotSensors;->startGyroscope(J)Z

    move-result v0

    if-eqz v0, :cond_7b

    .line 2651
    const-string v0, "gyroscope_started"

    invoke-direct {v2, v5, v0, v8}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    goto/16 :goto_31

    .line 2653
    :cond_7b
    const-string v0, "gyroscope_failed"

    invoke-static {v7, v13}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v1

    invoke-direct {v2, v5, v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    goto/16 :goto_31

    :pswitch_24
    move-object v5, v2

    move-object v2, v1

    .line 2765
    iget-boolean v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestingPageOpen:Z

    if-nez v0, :cond_b3

    iget-object v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v0, :cond_7c

    goto/16 :goto_31

    .line 2768
    :cond_7c
    iget-object v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->location:Lorg/telegram/ui/bots/BotLocation;

    if-nez v0, :cond_7d

    .line 2769
    invoke-virtual {v2}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    iget v1, v2, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    iget-object v3, v2, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v3, v3, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-static {v0, v1, v3, v4}, Lorg/telegram/ui/bots/BotLocation;->get(Landroid/content/Context;IJ)Lorg/telegram/ui/bots/BotLocation;

    move-result-object v0

    iput-object v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->location:Lorg/telegram/ui/bots/BotLocation;

    .line 2770
    iget-object v1, v2, Lorg/telegram/ui/web/BotWebViewContainer;->notifyLocationChecked:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/bots/BotLocation;->listen(Ljava/lang/Runnable;)V

    .line 2772
    :cond_7d
    iput-object v5, v2, Lorg/telegram/ui/web/BotWebViewContainer;->locationRequestContext:Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;

    .line 2773
    iget-object v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->location:Lorg/telegram/ui/bots/BotLocation;

    invoke-virtual {v0}, Lorg/telegram/ui/bots/BotLocation;->granted()Z

    move-result v0

    .line 2783
    iget-object v1, v2, Lorg/telegram/ui/web/BotWebViewContainer;->location:Lorg/telegram/ui/bots/BotLocation;

    if-nez v0, :cond_7e

    .line 2774
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda16;

    invoke-direct {v0, v2, v5}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda16;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v1, v0}, Lorg/telegram/ui/bots/BotLocation;->request(Lorg/telegram/messenger/Utilities$Callback2;)V

    return-void

    .line 2783
    :cond_7e
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda17;

    invoke-direct {v0, v2, v5}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda17;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v1, v0}, Lorg/telegram/ui/bots/BotLocation;->requestObject(Lorg/telegram/messenger/Utilities$Callback;)V

    return-void

    :pswitch_25
    move-object v2, v1

    move-object/from16 v6, v38

    .line 2464
    iget-boolean v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestingPageOpen:Z

    if-nez v0, :cond_b3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-wide v9, v2, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    sub-long/2addr v0, v9

    cmp-long v0, v0, v40

    if-gtz v0, :cond_b3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-wide v9, v2, Lorg/telegram/ui/web/BotWebViewContainer;->lastPostStoryMs:J

    sub-long/2addr v0, v9

    const-wide/16 v9, 0x7d0

    cmp-long v0, v0, v9

    if-gez v0, :cond_7f

    goto/16 :goto_31

    :cond_7f
    const-wide/16 v0, 0x0

    .line 2467
    iput-wide v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 2468
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->lastPostStoryMs:J

    .line 2474
    :try_start_1b
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2475
    const-string v1, "media_url"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1
    :try_end_1b
    .catch Ljava/lang/Exception; {:try_start_1b .. :try_end_1b} :catch_1c

    .line 2476
    :try_start_1c
    invoke-virtual {v0, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3
    :try_end_1c
    .catch Ljava/lang/Exception; {:try_start_1c .. :try_end_1c} :catch_1b

    .line 2477
    :try_start_1d
    const-string/jumbo v4, "widget_link"

    invoke-virtual {v0, v4}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    if-eqz v0, :cond_80

    move-object/from16 v4, v28

    .line 2479
    invoke-virtual {v0, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4
    :try_end_1d
    .catch Ljava/lang/Exception; {:try_start_1d .. :try_end_1d} :catch_1a

    .line 2480
    :try_start_1e
    const-string/jumbo v5, "name"

    invoke-virtual {v0, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0
    :try_end_1e
    .catch Ljava/lang/Exception; {:try_start_1e .. :try_end_1e} :catch_19

    goto :goto_1c

    :catch_19
    move-exception v0

    goto :goto_1f

    :catch_1a
    move-exception v0

    move-object v4, v8

    goto :goto_1f

    :cond_80
    move-object v0, v8

    move-object v4, v0

    :goto_1c
    move-object v5, v0

    :goto_1d
    move-object v6, v1

    goto :goto_20

    :catch_1b
    move-exception v0

    move-object v3, v8

    :goto_1e
    move-object v4, v3

    goto :goto_1f

    :catch_1c
    move-exception v0

    move-object v1, v8

    move-object v3, v1

    goto :goto_1e

    .line 2483
    :goto_1f
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    move-object v5, v8

    goto :goto_1d

    :goto_20
    if-nez v6, :cond_81

    goto/16 :goto_31

    .line 2490
    :cond_81
    iget v0, v2, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/MessagesController;->storiesEnabled()Z

    move-result v0

    if-nez v0, :cond_82

    .line 2491
    new-instance v0, Lorg/telegram/ui/Components/Premium/PremiumFeatureBottomSheet;

    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$5;

    invoke-direct {v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer$5;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;)V

    const/16 v2, 0xe

    const/4 v3, 0x1

    invoke-direct {v0, v1, v2, v3}, Lorg/telegram/ui/Components/Premium/PremiumFeatureBottomSheet;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;IZ)V

    .line 2516
    invoke-virtual {v0}, Lorg/telegram/ui/Components/Premium/PremiumFeatureBottomSheet;->show()V

    return-void

    .line 2520
    :cond_82
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog;

    iget-object v1, v2, Lorg/telegram/ui/web/BotWebViewContainer;->parentActivity:Landroid/app/Activity;

    const/4 v7, 0x3

    invoke-direct {v0, v1, v7}, Lorg/telegram/ui/ActionBar/AlertDialog;-><init>(Landroid/content/Context;I)V

    .line 2521
    new-instance v7, Lorg/telegram/ui/web/HttpGetFileTask;

    move-object v2, v0

    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda11;

    move-object/from16 v1, p0

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda11;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v7, v0, v8}, Lorg/telegram/ui/web/HttpGetFileTask;-><init>(Lorg/telegram/messenger/Utilities$Callback;Lorg/telegram/messenger/Utilities$Callback;)V

    filled-new-array {v6}, [Ljava/lang/String;

    move-result-object v0

    .line 2592
    invoke-virtual {v7, v0}, Landroid/os/AsyncTask;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    const-wide/16 v0, 0xfa

    .line 2593
    invoke-virtual {v2, v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog;->showDelayed(J)V

    goto/16 :goto_31

    :pswitch_26
    move-object v5, v2

    .line 2952
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v0, :cond_83

    goto/16 :goto_31

    .line 2953
    :cond_83
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    if-nez v0, :cond_84

    new-instance v6, Lorg/telegram/ui/bots/BotStorage;

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v7

    iget v8, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v8}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->getClientUserId()J

    move-result-wide v9

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v11, v0, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const/4 v13, 0x1

    invoke-direct/range {v6 .. v13}, Lorg/telegram/ui/bots/BotStorage;-><init>(Landroid/content/Context;IJJZ)V

    iput-object v6, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    .line 2954
    :cond_84
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    const-string/jumbo v5, "secure_storage_key_restored"

    const-string/jumbo v6, "secure_storage_failed"

    move-object/from16 v2, p2

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer;->restoreStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void

    .line 1965
    :pswitch_27
    :try_start_1f
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1966
    const-string/jumbo v1, "path_full"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 1967
    const-string v2, "force_request"

    const/4 v4, 0x0

    invoke-virtual {v0, v2, v4}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v6

    .line 1968
    const-string v0, "/"

    invoke-virtual {v1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_85

    const/4 v3, 0x1

    .line 1969
    invoke-virtual {v1, v3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v1

    :cond_85
    move-object/from16 v0, v24

    .line 1971
    invoke-virtual {v0, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v2

    const/4 v4, 0x0

    const/4 v5, 0x1

    const/4 v3, 0x0

    move-object/from16 v1, p0

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer;->onOpenUri(Landroid/net/Uri;Ljava/lang/String;ZZZ)V
    :try_end_1f
    .catch Lorg/json/JSONException; {:try_start_1f .. :try_end_1f} :catch_1d

    return-void

    :catch_1d
    move-exception v0

    .line 1973
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 1556
    :pswitch_28
    :try_start_20
    new-instance v0, Lorg/json/JSONArray;

    invoke-direct {v0, v4}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    const/4 v3, 0x1

    const/4 v4, 0x0

    .line 1557
    invoke-virtual {v0, v4, v3}, Lorg/json/JSONArray;->optBoolean(IZ)Z

    move-result v2
    :try_end_20
    .catch Ljava/lang/Exception; {:try_start_20 .. :try_end_20} :catch_1e

    .line 1558
    :try_start_21
    invoke-virtual {v0, v3, v3}, Lorg/json/JSONArray;->optBoolean(IZ)Z

    move-result v15
    :try_end_21
    .catch Ljava/lang/Exception; {:try_start_21 .. :try_end_21} :catch_1f

    goto :goto_21

    :catch_1e
    const/4 v2, 0x1

    :catch_1f
    const/4 v15, 0x1

    .line 1560
    :goto_21
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v3, "allowScroll "

    invoke-direct {v0, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v3, " "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 1561
    invoke-virtual {v1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    instance-of v0, v0, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;

    if-eqz v0, :cond_b3

    .line 1562
    invoke-virtual {v1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;

    .line 1563
    invoke-virtual {v0, v2, v15}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->allowThisScroll(ZZ)V

    goto/16 :goto_31

    .line 2907
    :pswitch_29
    :try_start_22
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2908
    const-string v2, "locked"

    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->getBoolean(Ljava/lang/String;)Z

    move-result v14
    :try_end_22
    .catch Ljava/lang/Exception; {:try_start_22 .. :try_end_22} :catch_20

    goto :goto_22

    :catch_20
    const/4 v14, 0x0

    .line 2910
    :goto_22
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz v0, :cond_b3

    .line 2911
    invoke-interface {v0, v14}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onOrientationLockChanged(Z)V

    goto/16 :goto_31

    .line 2370
    :pswitch_2a
    :try_start_23
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2371
    invoke-virtual {v0, v10}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8
    :try_end_23
    .catch Ljava/lang/Exception; {:try_start_23 .. :try_end_23} :catch_21

    .line 2373
    :catch_21
    invoke-direct {v1}, Lorg/telegram/ui/web/BotWebViewContainer;->createBiometry()V

    .line 2374
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    if-nez v0, :cond_86

    goto/16 :goto_31

    .line 2377
    :cond_86
    iget-boolean v3, v0, Lorg/telegram/ui/bots/BotBiometry;->access_granted:Z

    if-nez v3, :cond_87

    .line 2379
    :try_start_24
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 2380
    invoke-virtual {v0, v9, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2381
    const-string v3, "biometry_auth_requested"

    invoke-direct {v1, v2, v3, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_24
    .catch Ljava/lang/Exception; {:try_start_24 .. :try_end_24} :catch_22

    goto/16 :goto_31

    :catch_22
    move-exception v0

    .line 2383
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 2387
    :cond_87
    new-instance v3, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda9;

    invoke-direct {v3, v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda9;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v0, v8, v3}, Lorg/telegram/ui/bots/BotBiometry;->requestToken(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback2;)V

    goto/16 :goto_31

    .line 2922
    :pswitch_2b
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v0, :cond_88

    goto/16 :goto_31

    .line 2923
    :cond_88
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    if-nez v0, :cond_89

    new-instance v5, Lorg/telegram/ui/bots/BotStorage;

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v6

    iget v7, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v7}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->getClientUserId()J

    move-result-wide v8

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v10, v0, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const/4 v12, 0x0

    invoke-direct/range {v5 .. v12}, Lorg/telegram/ui/bots/BotStorage;-><init>(Landroid/content/Context;IJJZ)V

    iput-object v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    .line 2924
    :cond_89
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    const-string v5, "device_storage_key_received"

    const-string v6, "device_storage_failed"

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer;->getStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void

    .line 2916
    :pswitch_2c
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v0, :cond_8a

    goto/16 :goto_31

    .line 2917
    :cond_8a
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    if-nez v0, :cond_8b

    new-instance v2, Lorg/telegram/ui/bots/BotStorage;

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v3

    iget v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v4}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->getClientUserId()J

    move-result-wide v5

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v7, v0, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const/4 v9, 0x0

    invoke-direct/range {v2 .. v9}, Lorg/telegram/ui/bots/BotStorage;-><init>(Landroid/content/Context;IJJZ)V

    iput-object v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    .line 2918
    :cond_8b
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    const-string v5, "device_storage_key_saved"

    const-string v6, "device_storage_failed"

    move-object/from16 v2, p2

    move-object/from16 v4, p4

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer;->setStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void

    .line 2684
    :pswitch_2d
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->getBotSensors()Lorg/telegram/ui/bots/BotSensors;

    move-result-object v0

    if-eqz v0, :cond_8c

    .line 2685
    invoke-virtual {v0}, Lorg/telegram/ui/bots/BotSensors;->stopOrientation()Z

    move-result v0

    if-eqz v0, :cond_8c

    .line 2686
    const-string v0, "device_orientation_stopped"

    invoke-direct {v1, v2, v0, v8}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2688
    :cond_8c
    invoke-static {v7, v13}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-direct {v1, v2, v6, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2745
    :pswitch_2e
    iget-boolean v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestingPageOpen:Z

    if-nez v0, :cond_b3

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-eqz v0, :cond_b3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    iget-wide v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    sub-long/2addr v3, v5

    cmp-long v0, v3, v40

    if-lez v0, :cond_8d

    goto/16 :goto_31

    .line 2748
    :cond_8d
    iget v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v3, v3, Lorg/telegram/tgnet/TLRPC$User;->id:J

    new-instance v5, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda14;

    invoke-direct {v5, v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda14;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-static {v0, v3, v4, v5}, Lorg/telegram/ui/bots/SetupEmojiStatusSheet;->askPermission(IJLorg/telegram/messenger/Utilities$Callback2;)V

    return-void

    .line 2039
    :pswitch_2f
    invoke-virtual {v1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    instance-of v0, v0, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;

    if-eqz v0, :cond_8e

    invoke-virtual {v1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;

    invoke-virtual {v0}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->isSwipeInProgress()Z

    move-result v0

    if-eqz v0, :cond_8e

    const/4 v14, 0x1

    :goto_23
    const/4 v3, 0x1

    goto :goto_24

    :cond_8e
    const/4 v14, 0x0

    goto :goto_23

    :goto_24
    xor-int/lit8 v0, v14, 0x1

    .line 2040
    invoke-virtual {v1, v0, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->invalidateViewPortHeight(ZZ)V

    return-void

    .line 2446
    :pswitch_30
    iget-boolean v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestingPageOpen:Z

    if-nez v0, :cond_b3

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-eqz v0, :cond_b3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    iget-wide v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    sub-long/2addr v2, v4

    cmp-long v0, v2, v40

    if-lez v0, :cond_8f

    goto/16 :goto_31

    :cond_8f
    const-wide/16 v10, 0x0

    .line 2450
    iput-wide v10, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 2452
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v0

    if-eqz v0, :cond_b3

    .line 2453
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v2

    if-nez v2, :cond_90

    goto/16 :goto_31

    .line 2454
    :cond_90
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentLayout()Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v2

    .line 2455
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v3, v3, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-static {v3, v4}, Lorg/telegram/ui/ProfileActivity;->of(J)Lorg/telegram/ui/ProfileActivity;

    move-result-object v3

    invoke-virtual {v0, v3}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    .line 2456
    const-string v0, "botPermissionBiometry"

    invoke-static {v2, v0}, Lorg/telegram/messenger/AndroidUtilities;->scrollToFragmentRow(Lorg/telegram/ui/ActionBar/INavigationLayout;Ljava/lang/String;)V

    .line 2457
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz v0, :cond_b3

    .line 2458
    invoke-interface {v0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onCloseToTabs()V

    return-void

    .line 2790
    :pswitch_31
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->location:Lorg/telegram/ui/bots/BotLocation;

    if-nez v0, :cond_91

    .line 2791
    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    iget v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    iget-object v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v4, v4, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-static {v0, v3, v4, v5}, Lorg/telegram/ui/bots/BotLocation;->get(Landroid/content/Context;IJ)Lorg/telegram/ui/bots/BotLocation;

    move-result-object v0

    iput-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->location:Lorg/telegram/ui/bots/BotLocation;

    .line 2792
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->notifyLocationChecked:Ljava/lang/Runnable;

    invoke-virtual {v0, v3}, Lorg/telegram/ui/bots/BotLocation;->listen(Ljava/lang/Runnable;)V

    .line 2794
    :cond_91
    iput-object v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->locationRequestContext:Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;

    .line 2795
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->notifyLocationChecked:Ljava/lang/Runnable;

    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    return-void

    .line 2940
    :pswitch_32
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v0, :cond_92

    goto/16 :goto_31

    .line 2941
    :cond_92
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    if-nez v0, :cond_93

    new-instance v3, Lorg/telegram/ui/bots/BotStorage;

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v4

    iget v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v5}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->getClientUserId()J

    move-result-wide v6

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v8, v0, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const/4 v10, 0x1

    invoke-direct/range {v3 .. v10}, Lorg/telegram/ui/bots/BotStorage;-><init>(Landroid/content/Context;IJJZ)V

    iput-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    .line 2942
    :cond_93
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    const-string/jumbo v5, "secure_storage_key_received"

    const-string/jumbo v6, "secure_storage_failed"

    move-object/from16 v4, p4

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer;->getStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void

    .line 2044
    :pswitch_33
    invoke-virtual {v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyThemeChanged()V

    return-void

    :pswitch_34
    const/4 v0, 0x4

    .line 2221
    invoke-direct {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->ignoreDialog(I)Z

    move-result v3

    if-eqz v3, :cond_94

    .line 2223
    :try_start_25
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 2224
    invoke-virtual {v0, v9, v15}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 2225
    const-string/jumbo v3, "phone_requested"

    invoke-direct {v1, v2, v3, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V
    :try_end_25
    .catch Ljava/lang/Exception; {:try_start_25 .. :try_end_25} :catch_23

    return-void

    :catch_23
    move-exception v0

    .line 2227
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void

    .line 2232
    :cond_94
    iget v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    .line 2233
    iget-object v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    .line 2234
    filled-new-array {v15}, [Ljava/lang/String;

    move-result-object v2

    .line 2235
    new-instance v7, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v7, v0, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 2236
    sget v0, Lorg/telegram/messenger/R$string;->ShareYouPhoneNumberTitle:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v7, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 2237
    new-instance v0, Landroid/text/SpannableStringBuilder;

    invoke-direct {v0}, Landroid/text/SpannableStringBuilder;-><init>()V

    .line 2238
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-static {v3}, Lorg/telegram/messenger/UserObject;->getUserName(Lorg/telegram/tgnet/TLRPC$User;)Ljava/lang/String;

    move-result-object v3

    .line 2239
    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-nez v6, :cond_95

    .line 2240
    sget v6, Lorg/telegram/messenger/R$string;->AreYouSureShareMyContactInfoWebapp:I

    filled-new-array {v3}, [Ljava/lang/Object;

    move-result-object v3

    invoke-static {v6, v3}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v3

    invoke-virtual {v0, v3}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    goto :goto_25

    .line 2242
    :cond_95
    sget v3, Lorg/telegram/messenger/R$string;->AreYouSureShareMyContactInfoBot:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v3

    invoke-virtual {v0, v3}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 2244
    :goto_25
    iget v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v3}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v3

    iget-object v3, v3, Lorg/telegram/messenger/MessagesController;->blockePeers:Lorg/telegram/messenger/support/LongSparseIntArray;

    iget-object v6, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v8, v6, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-virtual {v3, v8, v9}, Lorg/telegram/messenger/support/LongSparseIntArray;->indexOfKey(J)I

    move-result v3

    if-ltz v3, :cond_96

    const/4 v3, 0x1

    goto :goto_26

    :cond_96
    const/4 v3, 0x0

    :goto_26
    if-eqz v3, :cond_97

    .line 2246
    const-string v6, "\n\n"

    invoke-virtual {v0, v6}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 2247
    sget v6, Lorg/telegram/messenger/R$string;->AreYouSureShareMyContactInfoBotUnblock:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 2249
    :cond_97
    invoke-virtual {v7, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 2250
    sget v0, Lorg/telegram/messenger/R$string;->ShareContact:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v8

    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda28;

    move-object/from16 v6, p2

    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda28;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;[Ljava/lang/String;ZILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    move-object v3, v0

    move-object v0, v2

    move-object v2, v6

    invoke-virtual {v7, v8, v3}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 2278
    sget v3, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    new-instance v6, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda29;

    invoke-direct {v6}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda29;-><init>()V

    invoke-virtual {v7, v3, v6}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 2281
    invoke-virtual {v7}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v3

    new-instance v6, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda30;

    invoke-direct {v6, v0, v4, v5, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda30;-><init>([Ljava/lang/String;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    const/4 v0, 0x4

    invoke-direct {v1, v0, v3, v6}, Lorg/telegram/ui/web/BotWebViewContainer;->showDialog(ILorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/Runnable;)Z

    return-void

    :pswitch_35
    move-object v0, v4

    move-object/from16 v6, v38

    .line 1643
    :try_start_26
    iget-boolean v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->hasQRPending:Z

    if-nez v3, :cond_b3

    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->parentActivity:Landroid/app/Activity;

    if-nez v3, :cond_98

    goto/16 :goto_31

    .line 1647
    :cond_98
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1648
    invoke-virtual {v3, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastQrText:Ljava/lang/String;

    const/4 v3, 0x1

    .line 1649
    iput-boolean v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->hasQRPending:Z

    .line 1651
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->parentActivity:Landroid/app/Activity;

    const-string v3, "android.permission.CAMERA"

    invoke-virtual {v0, v3}, Landroid/content/Context;->checkSelfPermission(Ljava/lang/String;)I

    move-result v0

    if-eqz v0, :cond_99

    .line 1652
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    new-instance v3, Lorg/telegram/ui/web/BotWebViewContainer$4;

    invoke-direct {v3, v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer$4;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    sget v2, Lorg/telegram/messenger/NotificationCenter;->onRequestPermissionResultReceived:I

    invoke-virtual {v0, v3, v2}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 1672
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->parentActivity:Landroid/app/Activity;

    const-string v1, "android.permission.CAMERA"

    filled-new-array {v1}, [Ljava/lang/String;

    move-result-object v1

    const/16 v2, 0x1388

    invoke-virtual {v0, v1, v2}, Landroid/app/Activity;->requestPermissions([Ljava/lang/String;I)V

    return-void

    .line 1676
    :cond_99
    invoke-direct {v1}, Lorg/telegram/ui/web/BotWebViewContainer;->openQrScanActivity()V
    :try_end_26
    .catch Lorg/json/JSONException; {:try_start_26 .. :try_end_26} :catch_24

    return-void

    :catch_24
    move-exception v0

    .line 1678
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    :pswitch_36
    move-object v0, v4

    .line 1804
    :try_start_27
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1805
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    const-string/jumbo v1, "need_confirmation"

    invoke-virtual {v2, v1}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;)Z

    move-result v1

    invoke-interface {v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppSetupClosingBehavior(Z)V
    :try_end_27
    .catch Lorg/json/JSONException; {:try_start_27 .. :try_end_27} :catch_25

    return-void

    :catch_25
    move-exception v0

    .line 1807
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    :pswitch_37
    move-object v0, v4

    move-object/from16 v9, v35

    move-object/from16 v8, v36

    move-object/from16 v7, v37

    move-object/from16 v6, v38

    move-object/from16 v5, v39

    move-object/from16 v3, v42

    move-object/from16 v2, v43

    const-wide/16 v10, 0x0

    .line 2080
    :try_start_28
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    const/4 v12, 0x0

    .line 2081
    invoke-virtual {v4, v3, v12}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v24

    .line 2082
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonText:Ljava/lang/String;

    invoke-virtual {v4, v6, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    .line 2083
    invoke-virtual {v4, v2, v12}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v2

    if-eqz v2, :cond_9a

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_9a

    const/16 v23, 0x1

    goto :goto_27

    :cond_9a
    const/16 v23, 0x0

    .line 2084
    :goto_27
    invoke-virtual {v4, v14}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_9b

    invoke-virtual {v4, v14}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v2

    goto :goto_28

    :cond_9b
    iget v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonColor:I

    .line 2085
    :goto_28
    invoke-virtual {v4, v5}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_9c

    invoke-virtual {v4, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v5

    :goto_29
    const/4 v12, 0x0

    goto :goto_2a

    :cond_9c
    iget v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonTextColor:I

    goto :goto_29

    .line 2086
    :goto_2a
    invoke-virtual {v4, v7, v12}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v6

    if-eqz v6, :cond_9d

    if-eqz v23, :cond_9d

    const/16 v30, 0x1

    goto :goto_2b

    :cond_9d
    move/from16 v30, v12

    .line 2087
    :goto_2b
    invoke-virtual {v4, v8, v12}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v6

    if-eqz v6, :cond_9e

    if-eqz v23, :cond_9e

    const/16 v31, 0x1

    goto :goto_2c

    :cond_9e
    const/16 v31, 0x0

    .line 2088
    :goto_2c
    const-string/jumbo v6, "position"

    invoke-virtual {v4, v6}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_9f

    const-string/jumbo v6, "position"

    invoke-virtual {v4, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    goto :goto_2d

    :cond_9f
    iget-object v6, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonPosition:Ljava/lang/String;

    :goto_2d
    if-nez v6, :cond_a0

    .line 2089
    const-string v6, "left"
    :try_end_28
    .catch Ljava/lang/Exception; {:try_start_28 .. :try_end_28} :catch_26

    .line 2092
    :cond_a0
    :try_start_29
    invoke-virtual {v4, v9}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v13
    :try_end_29
    .catchall {:try_start_29 .. :try_end_29} :catchall_1

    move-wide/from16 v26, v13

    goto :goto_2e

    :catchall_1
    move-wide/from16 v26, v10

    .line 2095
    :goto_2e
    :try_start_2a
    iput v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonColor:I

    .line 2096
    iput v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonTextColor:I

    .line 2097
    iput-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonText:Ljava/lang/String;

    .line 2098
    iput-object v6, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastSecondaryButtonPosition:Ljava/lang/String;

    .line 2099
    iput-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->secondaryButtonData:Ljava/lang/String;

    .line 2101
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    move-object/from16 v22, v0

    move/from16 v28, v2

    move-object/from16 v25, v3

    move/from16 v29, v5

    move-object/from16 v32, v6

    invoke-interface/range {v22 .. v32}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onSetupSecondaryButton(ZZLjava/lang/String;JIIZZLjava/lang/String;)V
    :try_end_2a
    .catch Ljava/lang/Exception; {:try_start_2a .. :try_end_2a} :catch_26

    goto/16 :goto_31

    :catch_26
    move-exception v0

    .line 2103
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    :pswitch_38
    move-object v0, v4

    const-wide/16 v10, 0x0

    .line 2718
    iget-boolean v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestingPageOpen:Z

    if-nez v3, :cond_b3

    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-eqz v3, :cond_b3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    iget-wide v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    sub-long/2addr v3, v5

    cmp-long v3, v3, v40

    if-lez v3, :cond_a1

    goto/16 :goto_31

    .line 2724
    :cond_a1
    :try_start_2b
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2725
    const-string v0, "custom_emoji_id"

    invoke-virtual {v3, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v13
    :try_end_2b
    .catch Ljava/lang/Exception; {:try_start_2b .. :try_end_2b} :catch_27

    .line 2726
    :try_start_2c
    const-string v0, "duration"

    invoke-virtual {v3, v0}, Lorg/json/JSONObject;->getInt(Ljava/lang/String;)I

    move-result v0
    :try_end_2c
    .catch Ljava/lang/Exception; {:try_start_2c .. :try_end_2c} :catch_28

    move v4, v0

    goto :goto_2f

    :catch_27
    move-wide v13, v10

    :catch_28
    const/4 v4, 0x0

    .line 2728
    :goto_2f
    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v0, :cond_a2

    .line 2729
    const-string v0, "UNKNOWN_ERROR"

    invoke-static {v7, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    const-string v3, "emoji_status_failed"

    invoke-direct {v1, v2, v3, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :cond_a2
    move-object v3, v0

    .line 2732
    iget v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    new-instance v5, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda13;

    invoke-direct {v5, v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda13;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    move-object v1, v3

    move-wide v2, v13

    invoke-static/range {v0 .. v5}, Lorg/telegram/ui/bots/SetupEmojiStatusSheet;->show(ILorg/telegram/tgnet/TLRPC$User;JILorg/telegram/messenger/Utilities$Callback2;)V

    goto/16 :goto_31

    :pswitch_39
    move-object v0, v4

    .line 2007
    :try_start_2d
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2008
    const-string/jumbo v0, "slug"

    invoke-virtual {v2, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 2010
    iget-object v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentPaymentSlug:Ljava/lang/String;

    if-eqz v2, :cond_a3

    const/4 v3, 0x1

    .line 2011
    invoke-virtual {v1, v0, v15, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->onInvoiceStatusUpdate(Ljava/lang/String;Ljava/lang/String;Z)V

    return-void

    .line 2015
    :cond_a3
    iput-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentPaymentSlug:Ljava/lang/String;

    .line 2017
    new-instance v2, Lorg/telegram/tgnet/TLRPC$TL_payments_getPaymentForm;

    invoke-direct {v2}, Lorg/telegram/tgnet/TLRPC$TL_payments_getPaymentForm;-><init>()V

    .line 2018
    new-instance v3, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;

    invoke-direct {v3}, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;-><init>()V

    .line 2019
    iput-object v0, v3, Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;->slug:Ljava/lang/String;

    .line 2020
    iput-object v3, v2, Lorg/telegram/tgnet/TLRPC$TL_payments_getPaymentForm;->invoice:Lorg/telegram/tgnet/TLRPC$InputInvoice;

    .line 2022
    iget v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v4}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v4

    new-instance v5, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda25;

    invoke-direct {v5, v1, v0, v3}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda25;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_inputInvoiceSlug;)V

    invoke-virtual {v4, v2, v5}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I
    :try_end_2d
    .catch Lorg/json/JSONException; {:try_start_2d .. :try_end_2d} :catch_29

    return-void

    :catch_29
    move-exception v0

    .line 2030
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    :pswitch_3a
    move-object v0, v4

    .line 1684
    :try_start_2e
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    if-eqz v3, :cond_a4

    goto/16 :goto_31

    .line 1688
    :cond_a4
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    iget-wide v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastDialogClosed:J

    sub-long/2addr v3, v5

    const-wide/16 v5, 0x96

    cmp-long v3, v3, v5

    if-gtz v3, :cond_a5

    .line 1689
    iget v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->dialogSequentialOpenTimes:I

    const/16 v21, 0x1

    add-int/lit8 v3, v3, 0x1

    iput v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->dialogSequentialOpenTimes:I

    const/4 v7, 0x3

    if-lt v3, v7, :cond_a5

    const/4 v4, 0x0

    .line 1692
    iput v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->dialogSequentialOpenTimes:I

    .line 1693
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    iput-wide v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastDialogCooldownTime:J

    return-void

    .line 1698
    :cond_a5
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    iget-wide v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastDialogCooldownTime:J

    sub-long/2addr v3, v5

    const-wide/16 v5, 0xbb8

    cmp-long v3, v3, v5

    if-gtz v3, :cond_a6

    goto/16 :goto_31

    .line 1702
    :cond_a6
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1703
    const-string/jumbo v0, "title"

    invoke-virtual {v3, v0, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1704
    const-string/jumbo v4, "message"

    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 1705
    const-string v5, "buttons"

    invoke-virtual {v3, v5}, Lorg/json/JSONObject;->getJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v3

    .line 1707
    new-instance v5, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v6

    invoke-direct {v5, v6}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 1708
    invoke-virtual {v5, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 1709
    invoke-virtual {v0, v4}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 1711
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    const/4 v5, 0x0

    .line 1712
    :goto_30
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v6

    if-ge v5, v6, :cond_a7

    .line 1713
    new-instance v6, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;

    invoke-virtual {v3, v5}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v7

    invoke-direct {v6, v7}, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;-><init>(Lorg/json/JSONObject;)V

    invoke-interface {v4, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 v5, v5, 0x1

    goto :goto_30

    .line 1715
    :cond_a7
    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v3

    const/4 v7, 0x3

    if-le v3, v7, :cond_a8

    goto/16 :goto_31

    .line 1719
    :cond_a8
    new-instance v3, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v3}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>()V

    .line 1720
    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v5

    const/4 v8, 0x1

    if-lt v5, v8, :cond_a9

    const/4 v12, 0x0

    .line 1721
    invoke-interface {v4, v12}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;

    .line 1722
    iget-object v6, v5, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->text:Ljava/lang/String;

    new-instance v7, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda4;

    invoke-direct {v7, v1, v2, v5, v3}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;)V

    invoke-virtual {v0, v6, v7}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 1734
    :cond_a9
    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v5

    const/4 v6, 0x2

    if-lt v5, v6, :cond_aa

    const/4 v8, 0x1

    .line 1735
    invoke-interface {v4, v8}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;

    .line 1736
    iget-object v6, v5, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->text:Ljava/lang/String;

    new-instance v7, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda15;

    invoke-direct {v7, v1, v2, v5, v3}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda15;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;)V

    invoke-virtual {v0, v6, v7}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 1748
    :cond_aa
    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v5

    const/4 v7, 0x3

    if-ne v5, v7, :cond_ab

    const/4 v6, 0x2

    .line 1749
    invoke-interface {v4, v6}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;

    .line 1750
    iget-object v6, v5, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->text:Ljava/lang/String;

    new-instance v7, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda23;

    invoke-direct {v7, v1, v2, v5, v3}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda23;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;Ljava/util/concurrent/atomic/AtomicBoolean;)V

    invoke-virtual {v0, v6, v7}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNeutralButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 1761
    :cond_ab
    new-instance v5, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda24;

    invoke-direct {v5, v1, v3, v2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda24;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v0, v5}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 1769
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->show()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v0

    iput-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    .line 1770
    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v0

    const/4 v3, 0x1

    if-lt v0, v3, :cond_ac

    const/4 v12, 0x0

    .line 1771
    invoke-interface {v4, v12}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;

    .line 1772
    iget v2, v0, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->textColorKey:I

    if-ltz v2, :cond_ac

    .line 1773
    iget-object v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    const/4 v3, -0x1

    invoke-virtual {v2, v3}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/TextView;

    if-eqz v2, :cond_ac

    .line 1775
    iget v0, v0, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->textColorKey:I

    invoke-direct {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result v0

    invoke-virtual {v2, v0}, Landroid/widget/TextView;->setTextColor(I)V

    .line 1779
    :cond_ac
    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v0

    const/4 v6, 0x2

    if-lt v0, v6, :cond_ad

    const/4 v3, 0x1

    .line 1780
    invoke-interface {v4, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;

    .line 1781
    iget v2, v0, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->textColorKey:I

    if-ltz v2, :cond_ad

    .line 1782
    iget-object v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    const/4 v3, -0x2

    invoke-virtual {v2, v3}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/TextView;

    if-eqz v2, :cond_ad

    .line 1784
    iget v0, v0, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->textColorKey:I

    invoke-direct {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result v0

    invoke-virtual {v2, v0}, Landroid/widget/TextView;->setTextColor(I)V

    .line 1788
    :cond_ad
    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v0

    const/4 v7, 0x3

    if-ne v0, v7, :cond_b3

    const/4 v6, 0x2

    .line 1789
    invoke-interface {v4, v6}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;

    .line 1790
    iget v2, v0, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->textColorKey:I

    if-ltz v2, :cond_b3

    .line 1791
    iget-object v2, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    const/4 v3, -0x3

    invoke-virtual {v2, v3}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/TextView;

    if-eqz v2, :cond_b3

    .line 1793
    iget v0, v0, Lorg/telegram/ui/web/BotWebViewContainer$PopupButton;->textColorKey:I

    invoke-direct {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result v0

    invoke-virtual {v2, v0}, Landroid/widget/TextView;->setTextColor(I)V
    :try_end_2e
    .catch Lorg/json/JSONException; {:try_start_2e .. :try_end_2e} :catch_2a

    return-void

    :catch_2a
    move-exception v0

    .line 1798
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    :pswitch_3b
    move-object v0, v4

    move-object/from16 v4, v28

    .line 2817
    iget-boolean v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestingPageOpen:Z

    if-nez v3, :cond_b3

    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-eqz v3, :cond_b3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v5

    iget-wide v7, v1, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    sub-long/2addr v5, v7

    cmp-long v3, v5, v40

    if-lez v3, :cond_ae

    goto/16 :goto_31

    .line 2821
    :cond_ae
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->downloads:Lorg/telegram/ui/bots/BotDownloads;

    if-nez v3, :cond_af

    .line 2822
    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v3

    iget v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    iget-object v6, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v6, v6, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-static {v3, v5, v6, v7}, Lorg/telegram/ui/bots/BotDownloads;->get(Landroid/content/Context;IJ)Lorg/telegram/ui/bots/BotDownloads;

    move-result-object v3

    iput-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->downloads:Lorg/telegram/ui/bots/BotDownloads;

    .line 2826
    :cond_af
    :try_start_2f
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2827
    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 2828
    const-string v4, "file_name"

    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3
    :try_end_2f
    .catch Ljava/lang/Exception; {:try_start_2f .. :try_end_2f} :catch_2b

    .line 2834
    iget-object v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->downloads:Lorg/telegram/ui/bots/BotDownloads;

    invoke-virtual {v4, v0}, Lorg/telegram/ui/bots/BotDownloads;->getCached(Ljava/lang/String;)Lorg/telegram/ui/bots/BotDownloads$FileDownload;

    move-result-object v4

    if-eqz v4, :cond_b0

    .line 2835
    iget-object v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->downloads:Lorg/telegram/ui/bots/BotDownloads;

    invoke-virtual {v4, v0, v3}, Lorg/telegram/ui/bots/BotDownloads;->download(Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/ui/bots/BotDownloads$FileDownload;

    .line 2836
    const-string v0, "downloading"

    invoke-static {v9, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    const-string v3, "file_download_requested"

    invoke-direct {v1, v2, v3, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 2842
    :cond_b0
    new-instance v4, Lorg/telegram/tgnet/tl/TL_bots$checkDownloadFileParams;

    invoke-direct {v4}, Lorg/telegram/tgnet/tl/TL_bots$checkDownloadFileParams;-><init>()V

    .line 2843
    iget v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v5}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v5

    iget-object v6, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    invoke-virtual {v5, v6}, Lorg/telegram/messenger/MessagesController;->getInputUser(Lorg/telegram/tgnet/TLRPC$User;)Lorg/telegram/tgnet/TLRPC$InputUser;

    move-result-object v5

    iput-object v5, v4, Lorg/telegram/tgnet/tl/TL_bots$checkDownloadFileParams;->bot:Lorg/telegram/tgnet/TLRPC$InputUser;

    .line 2844
    iput-object v3, v4, Lorg/telegram/tgnet/tl/TL_bots$checkDownloadFileParams;->file_name:Ljava/lang/String;

    .line 2845
    iput-object v0, v4, Lorg/telegram/tgnet/tl/TL_bots$checkDownloadFileParams;->url:Ljava/lang/String;

    .line 2846
    iget v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v5}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v5

    new-instance v6, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda18;

    invoke-direct {v6, v1, v2, v0, v3}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda18;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v5, v4, v6}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    return-void

    :catch_2b
    move-exception v0

    .line 2830
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 2831
    const-string v0, "file_download_requested"

    invoke-static {v9, v15}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v3

    invoke-direct {v1, v2, v0, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :pswitch_3c
    move-object v0, v4

    move-object/from16 v4, v28

    .line 1951
    :try_start_30
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1952
    invoke-virtual {v2, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    .line 1953
    const-string/jumbo v3, "try_browser"

    invoke-virtual {v2, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 1954
    iget v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v4}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v4

    iget-object v4, v4, Lorg/telegram/messenger/MessagesController;->webAppAllowedProtocols:Ljava/util/Set;

    if-eqz v4, :cond_b3

    iget v4, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    .line 1955
    invoke-static {v4}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v4

    iget-object v4, v4, Lorg/telegram/messenger/MessagesController;->webAppAllowedProtocols:Ljava/util/Set;

    invoke-virtual {v0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v4, v5}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_b3

    .line 1956
    const-string/jumbo v4, "try_instant_view"

    invoke-virtual {v2, v4}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;)Z

    move-result v4

    const/4 v5, 0x1

    const/4 v6, 0x0

    move-object v2, v0

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer;->onOpenUri(Landroid/net/Uri;Ljava/lang/String;ZZZ)V
    :try_end_30
    .catch Ljava/lang/Exception; {:try_start_30 .. :try_end_30} :catch_2c

    return-void

    :catch_2c
    move-exception v0

    .line 1959
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto/16 :goto_31

    .line 2296
    :pswitch_3d
    invoke-direct {v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyBiometryReceived(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    return-void

    .line 1636
    :pswitch_3e
    iget-boolean v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->hasQRPending:Z

    if-eqz v0, :cond_b3

    iget-object v0, v1, Lorg/telegram/ui/web/BotWebViewContainer;->cameraBottomSheet:Lorg/telegram/ui/ActionBar/BottomSheet;

    if-eqz v0, :cond_b3

    .line 1637
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BottomSheet;->dismiss()V

    return-void

    :pswitch_3f
    move-object v0, v4

    .line 2176
    iget-object v3, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v3, :cond_b1

    goto :goto_31

    .line 2180
    :cond_b1
    :try_start_31
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 2181
    invoke-virtual {v3, v11}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 2182
    const-string/jumbo v4, "method"

    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 2183
    const-string/jumbo v5, "params"

    invoke-virtual {v3, v5}, Lorg/json/JSONObject;->get(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    .line 2184
    invoke-virtual {v3}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v3
    :try_end_31
    .catch Ljava/lang/Exception; {:try_start_31 .. :try_end_31} :catch_2d

    .line 2195
    iget v5, v1, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    .line 2196
    iget-object v6, v1, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    .line 2197
    new-instance v7, Lorg/telegram/tgnet/tl/TL_bots$invokeWebViewCustomMethod;

    invoke-direct {v7}, Lorg/telegram/tgnet/tl/TL_bots$invokeWebViewCustomMethod;-><init>()V

    .line 2198
    invoke-static {v5}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v8

    iget-object v9, v1, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    iget-wide v9, v9, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-virtual {v8, v9, v10}, Lorg/telegram/messenger/MessagesController;->getInputUser(J)Lorg/telegram/tgnet/TLRPC$InputUser;

    move-result-object v8

    iput-object v8, v7, Lorg/telegram/tgnet/tl/TL_bots$invokeWebViewCustomMethod;->bot:Lorg/telegram/tgnet/TLRPC$InputUser;

    .line 2199
    iput-object v4, v7, Lorg/telegram/tgnet/tl/TL_bots$invokeWebViewCustomMethod;->custom_method:Ljava/lang/String;

    .line 2200
    new-instance v4, Lorg/telegram/tgnet/TLRPC$TL_dataJSON;

    invoke-direct {v4}, Lorg/telegram/tgnet/TLRPC$TL_dataJSON;-><init>()V

    iput-object v4, v7, Lorg/telegram/tgnet/tl/TL_bots$invokeWebViewCustomMethod;->params:Lorg/telegram/tgnet/TLRPC$TL_dataJSON;

    .line 2201
    iput-object v3, v4, Lorg/telegram/tgnet/TLRPC$TL_dataJSON;->data:Ljava/lang/String;

    .line 2202
    invoke-static {v5}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v8

    move-object v2, v0

    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda27;

    move v3, v5

    move-object v4, v6

    move-object/from16 v5, p2

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda27;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;)V

    invoke-virtual {v8, v7, v0}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    return-void

    :catch_2d
    move-exception v0

    .line 2186
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 2187
    instance-of v0, v0, Lorg/json/JSONException;

    if-eqz v0, :cond_b2

    .line 2188
    invoke-direct {v1, v12}, Lorg/telegram/ui/web/BotWebViewContainer;->error(Ljava/lang/String;)V

    goto :goto_31

    .line 2190
    :cond_b2
    invoke-direct {v1}, Lorg/telegram/ui/web/BotWebViewContainer;->unknownError()V

    :cond_b3
    :goto_31
    return-void

    .line 1544
    :cond_b4
    :goto_32
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ": no webview or delegate!"

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void

    nop

    :sswitch_data_0
    .sparse-switch
        -0x78380c2f -> :sswitch_3f
        -0x712ef480 -> :sswitch_3e
        -0x67840eae -> :sswitch_3d
        -0x665c257a -> :sswitch_3c
        -0x6643928e -> :sswitch_3b
        -0x64ed6860 -> :sswitch_3a
        -0x52e382df -> :sswitch_39
        -0x529356cf -> :sswitch_38
        -0x50abbe78 -> :sswitch_37
        -0x4feea439 -> :sswitch_36
        -0x4e07a07c -> :sswitch_35
        -0x4b514e0b -> :sswitch_34
        -0x4b1915b0 -> :sswitch_33
        -0x494594ed -> :sswitch_32
        -0x468baa4b -> :sswitch_31
        -0x412ee203 -> :sswitch_30
        -0x36e69d41 -> :sswitch_2f
        -0x3613b5a1 -> :sswitch_2e
        -0x24f605c5 -> :sswitch_2d
        -0x22de85df -> :sswitch_2c
        -0x22a1700e -> :sswitch_2b
        -0x1e8f02cd -> :sswitch_2a
        -0x1db0aec4 -> :sswitch_29
        -0x1c4afc94 -> :sswitch_28
        -0x1a365bc6 -> :sswitch_27
        -0x11848435 -> :sswitch_26
        -0xe9410c6 -> :sswitch_25
        -0xceaf632 -> :sswitch_24
        -0x6a09221 -> :sswitch_23
        -0x44674d1 -> :sswitch_22
        -0x3767926 -> :sswitch_21
        0x14fedd3 -> :sswitch_20
        0x79d187b -> :sswitch_1f
        0xb4715b1 -> :sswitch_1e
        0xff702aa -> :sswitch_1d
        0x14ccd349 -> :sswitch_1c
        0x190db429 -> :sswitch_1b
        0x1c5922fb -> :sswitch_1a
        0x2514a113 -> :sswitch_19
        0x27d30cb4 -> :sswitch_18
        0x280b07c0 -> :sswitch_17
        0x2b082f8f -> :sswitch_16
        0x2ca2c394 -> :sswitch_15
        0x2cc7cfc4 -> :sswitch_14
        0x2f410320 -> :sswitch_13
        0x2f73adf3 -> :sswitch_12
        0x347d4962 -> :sswitch_11
        0x36358261 -> :sswitch_10
        0x3c49757f -> :sswitch_f
        0x48211e2f -> :sswitch_e
        0x4bed2d1d -> :sswitch_d
        0x535b446d -> :sswitch_c
        0x569bcda2 -> :sswitch_b
        0x56c86043 -> :sswitch_a
        0x5927e9cc -> :sswitch_9
        0x68e6f1f1 -> :sswitch_8
        0x6c06f5cd -> :sswitch_7
        0x7038f2de -> :sswitch_6
        0x7131a349 -> :sswitch_5
        0x7244ae57 -> :sswitch_4
        0x73755306 -> :sswitch_3
        0x7749e138 -> :sswitch_2
        0x795c475d -> :sswitch_1
        0x7f8ad843 -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_3f
        :pswitch_3e
        :pswitch_3d
        :pswitch_3c
        :pswitch_3b
        :pswitch_3a
        :pswitch_39
        :pswitch_38
        :pswitch_37
        :pswitch_36
        :pswitch_35
        :pswitch_34
        :pswitch_33
        :pswitch_32
        :pswitch_31
        :pswitch_30
        :pswitch_2f
        :pswitch_2e
        :pswitch_2d
        :pswitch_2c
        :pswitch_2b
        :pswitch_2a
        :pswitch_29
        :pswitch_28
        :pswitch_27
        :pswitch_26
        :pswitch_25
        :pswitch_24
        :pswitch_23
        :pswitch_22
        :pswitch_21
        :pswitch_20
        :pswitch_1f
        :pswitch_1e
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

    :sswitch_data_1
    .sparse-switch
        -0x4041708b -> :sswitch_44
        0x35f42a -> :sswitch_43
        0x5e8f0c7 -> :sswitch_42
        0x6233516 -> :sswitch_41
        0x677c22b -> :sswitch_40
    .end sparse-switch
.end method

.method private onOpenUri(Landroid/net/Uri;)V
    .locals 7

    .line 551
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    xor-int/lit8 v4, v0, 0x1

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v3, 0x0

    move-object v1, p0

    move-object v2, p1

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/web/BotWebViewContainer;->onOpenUri(Landroid/net/Uri;Ljava/lang/String;ZZZ)V

    return-void
.end method

.method private onOpenUri(Landroid/net/Uri;Ljava/lang/String;ZZZ)V
    .locals 12

    .line 555
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isRequestingPageOpen:Z

    if-nez v0, :cond_2

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-wide v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    sub-long/2addr v0, v2

    const-wide/16 v2, 0x2710

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    if-eqz p4, :cond_0

    goto :goto_0

    :cond_0
    const-wide/16 v0, 0x0

    .line 559
    iput-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    const/4 v0, 0x1

    .line 560
    new-array v0, v0, [Z

    const/4 v1, 0x0

    aput-boolean v1, v0, v1

    .line 561
    invoke-static {p1, v0}, Lorg/telegram/messenger/browser/Browser;->isInternalUri(Landroid/net/Uri;[Z)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 563
    aget-boolean v0, v0, v1

    if-nez v0, :cond_1

    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-eqz v0, :cond_1

    .line 564
    invoke-virtual {p0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->setKeyboardFocusable(Z)V

    .line 567
    :cond_1
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v2

    const/4 v9, 0x0

    const/4 v10, 0x1

    const/4 v4, 0x1

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v3, p1

    move-object v8, p2

    move v5, p3

    move/from16 v11, p5

    invoke-static/range {v2 .. v11}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Landroid/net/Uri;ZZZLorg/telegram/messenger/browser/Browser$Progress;Ljava/lang/String;ZZZ)V

    :cond_2
    :goto_0
    return-void
.end method

.method private onWebEventReceived(Ljava/lang/String;Ljava/lang/String;)V
    .locals 8

    .line 1313
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-eqz v0, :cond_0

    goto/16 :goto_3

    .line 1314
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    if-nez v0, :cond_1

    goto/16 :goto_3

    .line 1316
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    if-eqz v0, :cond_2

    .line 1317
    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->getOriginHost()Ljava/lang/String;

    move-result-object v0

    .line 1318
    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    invoke-static {v0, v1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 1319
    new-instance p2, Ljava/lang/StringBuilder;

    const-string/jumbo v0, "onWebEventReceived ignore "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void

    .line 1324
    :cond_2
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "onWebEventReceived "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 1325
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result v0

    const/4 v1, 0x3

    const-string v2, "actionBarColor"

    const/4 v3, 0x2

    const/4 v4, 0x0

    const/4 v5, 0x1

    const/4 v6, -0x1

    sparse-switch v0, :sswitch_data_0

    goto :goto_0

    :sswitch_0
    const-string v0, "allowScroll"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    goto :goto_0

    :cond_3
    const/4 v6, 0x4

    goto :goto_0

    :sswitch_1
    const-string/jumbo v0, "siteName"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_4

    goto :goto_0

    :cond_4
    move v6, v1

    goto :goto_0

    :sswitch_2
    const-string/jumbo v0, "oauth_request"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_5

    goto :goto_0

    :cond_5
    move v6, v3

    goto :goto_0

    :sswitch_3
    const-string/jumbo v0, "navigationBarColor"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_6

    goto :goto_0

    :cond_6
    move v6, v5

    goto :goto_0

    :sswitch_4
    invoke-virtual {p1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_7

    goto :goto_0

    :cond_7
    move v6, v4

    :goto_0
    packed-switch v6, :pswitch_data_0

    goto/16 :goto_3

    .line 1354
    :pswitch_0
    :try_start_0
    new-instance p1, Lorg/json/JSONArray;

    invoke-direct {p1, p2}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 1355
    invoke-virtual {p1, v4, v5}, Lorg/json/JSONArray;->optBoolean(IZ)Z

    move-result p2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 1356
    :try_start_1
    invoke-virtual {p1, v5, v5}, Lorg/json/JSONArray;->optBoolean(IZ)Z

    move-result v5
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_1

    :catch_0
    move p2, v5

    .line 1359
    :catch_1
    :goto_1
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p1

    instance-of p1, p1, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;

    if-eqz p1, :cond_c

    .line 1360
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p0

    check-cast p0, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;

    .line 1361
    invoke-virtual {p0, p2, v5}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->allowThisScroll(ZZ)V

    goto/16 :goto_3

    .line 1366
    :pswitch_1
    new-instance p1, Ljava/lang/StringBuilder;

    const-string/jumbo v0, "siteName "

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 1367
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz p0, :cond_c

    .line 1368
    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->lastSiteName:Ljava/lang/String;

    .line 1369
    invoke-static {p0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->-$$Nest$msaveHistory(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V

    return-void

    .line 1374
    :pswitch_2
    new-instance p1, Ljava/lang/StringBuilder;

    const-string/jumbo v0, "oauth_request "

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 1375
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-nez p1, :cond_8

    goto/16 :goto_3

    .line 1376
    :cond_8
    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->getOriginHost()Ljava/lang/String;

    move-result-object p1

    .line 1377
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_9

    goto/16 :goto_3

    .line 1379
    :cond_9
    :try_start_2
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, p2}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 1380
    const-string/jumbo p2, "url"

    invoke-virtual {v0, p2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    .line 1381
    const-string/jumbo v0, "oauth_supported"

    const-string/jumbo v1, "version"

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-static {v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    .line 1383
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_c

    .line 1384
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;-><init>()V

    .line 1385
    iput-object p2, v0, Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;->url:Ljava/lang/String;

    .line 1386
    iget v1, v0, Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;->flags:I

    .line 1387
    iput-object p1, v0, Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;->in_app_origin:Ljava/lang/String;

    or-int/lit8 v1, v1, 0xc

    .line 1388
    iput v1, v0, Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;->flags:I

    .line 1389
    iget v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v1

    new-instance v2, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda32;

    invoke-direct {v2, p0, v0, p2, p1}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda32;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/tgnet/TLRPC$TL_messages_requestUrlAuth;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v0, v2, v3}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;I)I
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    return-void

    :catch_2
    move-exception p0

    .line 1410
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    goto :goto_3

    .line 1329
    :pswitch_3
    :try_start_3
    new-instance v0, Lorg/json/JSONArray;

    invoke-direct {v0, p2}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 1330
    invoke-static {p1, v2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    const-wide/high16 v6, 0x3ff0000000000000L    # 1.0

    .line 1332
    invoke-virtual {v0, v1, v6, v7}, Lorg/json/JSONArray;->optDouble(ID)D

    move-result-wide v1

    const-wide v6, 0x406fe00000000000L    # 255.0

    mul-double/2addr v1, v6

    invoke-static {v1, v2}, Ljava/lang/Math;->round(D)J

    move-result-wide v1

    long-to-int p2, v1

    .line 1333
    invoke-virtual {v0, v4}, Lorg/json/JSONArray;->optDouble(I)D

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Math;->round(D)J

    move-result-wide v1

    long-to-int v1, v1

    .line 1334
    invoke-virtual {v0, v5}, Lorg/json/JSONArray;->optDouble(I)D

    move-result-wide v6

    invoke-static {v6, v7}, Ljava/lang/Math;->round(D)J

    move-result-wide v6

    long-to-int v2, v6

    .line 1335
    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->optDouble(I)D

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Math;->round(D)J

    move-result-wide v3

    long-to-int v0, v3

    .line 1331
    invoke-static {p2, v1, v2, v0}, Landroid/graphics/Color;->argb(IIII)I

    move-result p2

    .line 1337
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v0, :cond_b

    if-eqz p1, :cond_a

    .line 1339
    iput-boolean v5, v0, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->lastActionBarColorGot:Z

    .line 1340
    iput p2, v0, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->lastActionBarColor:I

    goto :goto_2

    .line 1342
    :cond_a
    iput-boolean v5, v0, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->lastBackgroundColorGot:Z

    .line 1343
    iput p2, v0, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->lastBackgroundColor:I

    .line 1345
    :goto_2
    invoke-static {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->-$$Nest$msaveHistory(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V

    .line 1347
    :cond_b
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppBackgroundChanged(ZI)V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_3

    :catch_3
    :cond_c
    :goto_3
    return-void

    :sswitch_data_0
    .sparse-switch
        -0x65085c9a -> :sswitch_4
        -0x1b948ebc -> :sswitch_3
        0x1c9820e7 -> :sswitch_2
        0x283bd272 -> :sswitch_1
        0x3b751b76 -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_3
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private openQrScanActivity()V
    .locals 4

    .line 3461
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->parentActivity:Landroid/app/Activity;

    if-nez v0, :cond_0

    return-void

    .line 3465
    :cond_0
    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$8;

    invoke-direct {v1, p0}, Lorg/telegram/ui/web/BotWebViewContainer$8;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;)V

    const/4 v2, 0x0

    const/4 v3, 0x3

    invoke-static {v0, v2, v3, v1}, Lorg/telegram/ui/CameraScanActivity;->showAsSheet(Landroid/app/Activity;ZILorg/telegram/ui/CameraScanActivity$CameraScanActivityDelegate;)Lorg/telegram/ui/ActionBar/BottomSheet;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->cameraBottomSheet:Lorg/telegram/ui/ActionBar/BottomSheet;

    return-void
.end method

.method public static proxyTON(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;
    .locals 2

    .line 3858
    invoke-interface {p0}, Landroid/webkit/WebResourceRequest;->getMethod()Ljava/lang/String;

    move-result-object v0

    invoke-interface {p0}, Landroid/webkit/WebResourceRequest;->getUrl()Landroid/net/Uri;

    move-result-object v1

    invoke-virtual {v1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-interface {p0}, Landroid/webkit/WebResourceRequest;->getRequestHeaders()Ljava/util/Map;

    move-result-object p0

    invoke-static {v0, v1, p0}, Lorg/telegram/ui/web/BotWebViewContainer;->proxyTON(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)Landroid/webkit/WebResourceResponse;

    move-result-object p0

    return-object p0
.end method

.method public static proxyTON(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)Landroid/webkit/WebResourceResponse;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;)",
            "Landroid/webkit/WebResourceResponse;"
        }
    .end annotation

    .line 3881
    :try_start_0
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->getHostAuthority(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/ui/web/BotWebViewContainer;->rotateTONHost(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    const-string v1, "https"

    invoke-static {v0, p1, v1}, Lorg/telegram/messenger/browser/Browser;->replaceHostname(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 3882
    new-instance v0, Ljava/net/URL;

    invoke-direct {v0, p1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 3883
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p1

    check-cast p1, Ljava/net/HttpURLConnection;

    .line 3884
    invoke-virtual {p1, p0}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    if-eqz p2, :cond_0

    .line 3886
    invoke-interface {p2}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/util/Map$Entry;

    .line 3887
    invoke-interface {p2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-interface {p2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    invoke-virtual {p1, v0, p2}, Ljava/net/URLConnection;->addRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    .line 3890
    :cond_0
    invoke-virtual {p1}, Ljava/net/URLConnection;->connect()V

    .line 3891
    invoke-virtual {p1}, Ljava/net/URLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    .line 3892
    invoke-virtual {p1}, Ljava/net/URLConnection;->getContentType()Ljava/lang/String;

    move-result-object p2

    .line 3893
    const-string v0, ";"

    const/4 v1, 0x2

    invoke-virtual {p2, v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object p2

    const/4 v0, 0x0

    aget-object p2, p2, v0

    .line 3894
    new-instance v0, Landroid/webkit/WebResourceResponse;

    invoke-virtual {p1}, Ljava/net/URLConnection;->getContentEncoding()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v0, p2, p1, p0}, Landroid/webkit/WebResourceResponse;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/io/InputStream;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception p0

    .line 3896
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private reportSafeContentInsets(IZ)V
    .locals 9

    const/4 v0, 0x0

    .line 3359
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    if-nez p2, :cond_0

    .line 3356
    iget p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastInsetsTopMargin:I

    if-ne p1, p2, :cond_0

    return-void

    :cond_0
    int-to-float p2, p1

    .line 3359
    sget v0, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr p2, v0

    .line 3360
    invoke-static {p2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v4

    const-string/jumbo v5, "right"

    .line 3361
    const-string v7, "bottom"

    .line 3358
    const-string v1, "left"

    const-string/jumbo v3, "top"

    move-object v6, v2

    move-object v8, v2

    invoke-static/range {v1 .. v8}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    const-string v0, "content_safe_area_changed"

    invoke-virtual {p0, v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    .line 3364
    iput p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastInsetsTopMargin:I

    return-void
.end method

.method private reportSafeInsets(Landroid/graphics/Rect;Z)V
    .locals 8

    if-eqz p1, :cond_1

    if-nez p2, :cond_0

    .line 3345
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastInsets:Landroid/graphics/Rect;

    invoke-virtual {p2, p1}, Landroid/graphics/Rect;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_0

    goto :goto_0

    .line 3347
    :cond_0
    iget p2, p1, Landroid/graphics/Rect;->left:I

    int-to-float p2, p2

    sget v0, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr p2, v0

    .line 3348
    invoke-static {p2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    iget p2, p1, Landroid/graphics/Rect;->top:I

    int-to-float p2, p2

    sget v0, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr p2, v0

    .line 3349
    invoke-static {p2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v3

    iget p2, p1, Landroid/graphics/Rect;->right:I

    int-to-float p2, p2

    sget v0, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr p2, v0

    .line 3350
    invoke-static {p2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v5

    iget p2, p1, Landroid/graphics/Rect;->bottom:I

    int-to-float p2, p2

    sget v0, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr p2, v0

    .line 3351
    invoke-static {p2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v7

    .line 3347
    const-string v0, "left"

    const-string/jumbo v2, "top"

    const-string/jumbo v4, "right"

    const-string v6, "bottom"

    invoke-static/range {v0 .. v7}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    const-string/jumbo v0, "safe_area_changed"

    invoke-virtual {p0, v0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    .line 3353
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastInsets:Landroid/graphics/Rect;

    invoke-virtual {p0, p1}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private restoreStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 10

    .line 3262
    const-string v0, "KEY_INVALID"

    const-string v1, "error"

    const-string/jumbo v2, "req_id"

    if-eqz p2, :cond_3

    iget-object v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v4, :cond_0

    goto/16 :goto_0

    .line 3266
    :cond_0
    :try_start_0
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4, p3}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 3267
    invoke-virtual {v4, v2}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_2

    .line 3277
    :try_start_1
    const-string v5, "key"

    invoke-virtual {v4, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    if-nez v6, :cond_1

    .line 3283
    invoke-static {v2, p3, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3288
    :cond_1
    :try_start_2
    invoke-virtual {p2, v6}, Lorg/telegram/ui/bots/BotStorage;->getStoragesWithKey(Ljava/lang/String;)Ljava/util/List;

    move-result-object v8
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    .line 3293
    invoke-interface {v8}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 3294
    const-string p2, "RESTORE_UNAVAILABLE"

    invoke-static {v2, p3, v1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3298
    :cond_2
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v9

    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda33;

    move-object v1, p0

    move-object v2, p1

    move-object v5, p2

    move-object v4, p3

    move-object v7, p4

    move-object v3, p5

    invoke-direct/range {v0 .. v7}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda33;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p2, v9, v8, v0}, Lorg/telegram/ui/bots/BotStorage;->showChooseStorage(Landroid/content/Context;Ljava/util/List;Lorg/telegram/messenger/Utilities$Callback;)V

    return-void

    :catch_0
    move-exception v0

    move-object v4, p3

    move-object p4, v0

    .line 3290
    invoke-virtual {p4}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p4

    invoke-static {v2, v4, v1, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p4

    invoke-direct {p0, p1, p5, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :catch_1
    move-object v4, p3

    .line 3279
    invoke-static {v2, v4, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p4

    invoke-direct {p0, p1, p5, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :catch_2
    move-exception v0

    move-object p4, v0

    .line 3269
    invoke-static {p4}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 3270
    const-string p4, ""

    invoke-static {p4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_3

    .line 3271
    const-string v0, "UNKNOWN_ERROR"

    invoke-static {v2, p4, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p4

    invoke-direct {p0, p1, p5, p4}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    :cond_3
    :goto_0
    return-void
.end method

.method public static rotateTONHost(Ljava/lang/String;)Ljava/lang/String;
    .locals 5

    const/4 v0, 0x1

    .line 3863
    :try_start_0
    invoke-static {p0, v0}, Ljava/net/IDN;->toASCII(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 3865
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 3867
    :goto_0
    const-string v0, "\\."

    invoke-virtual {p0, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p0

    .line 3868
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const/4 v1, 0x0

    .line 3869
    :goto_1
    array-length v2, p0

    if-ge v1, v2, :cond_1

    if-lez v1, :cond_0

    .line 3871
    const-string v2, "-d"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 3873
    :cond_0
    aget-object v2, p0, v1

    const-string v3, "\\-"

    const-string v4, "-h"

    invoke-virtual {v2, v3, v4}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 3875
    :cond_1
    const-string p0, "."

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget p0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/messenger/MessagesController;->tonProxyAddress:Ljava/lang/String;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 3876
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private runWithPermissions([Ljava/lang/String;Landroidx/core/util/Consumer;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([",
            "Ljava/lang/String;",
            "Landroidx/core/util/Consumer<",
            "Ljava/lang/Boolean;",
            ">;)V"
        }
    .end annotation

    .line 714
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->checkPermissions([Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 715
    sget-object p0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-interface {p2, p0}, Landroidx/core/util/Consumer;->accept(Ljava/lang/Object;)V

    return-void

    .line 717
    :cond_0
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda54;

    invoke-direct {v0, p0, p2, p1}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda54;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Landroidx/core/util/Consumer;[Ljava/lang/String;)V

    iput-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->onPermissionsRequestResultCallback:Ljava/lang/Runnable;

    .line 719
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->parentActivity:Landroid/app/Activity;

    if-eqz p0, :cond_1

    const/16 p2, 0xfa0

    .line 720
    invoke-virtual {p0, p1, p2}, Landroid/app/Activity;->requestPermissions([Ljava/lang/String;I)V

    :cond_1
    return-void
.end method

.method private setStorageKey(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Lorg/telegram/ui/bots/BotStorage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 5

    .line 3184
    const-string v0, "KEY_INVALID"

    const-string v1, "error"

    const-string/jumbo v2, "req_id"

    if-eqz p2, :cond_2

    iget-object v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v3, :cond_0

    goto :goto_0

    .line 3188
    :cond_0
    :try_start_0
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3, p3}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 3189
    invoke-virtual {v3, v2}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_3

    .line 3199
    :try_start_1
    const-string v4, "key"

    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_2

    if-nez v4, :cond_1

    .line 3205
    invoke-static {v2, p3, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3210
    :cond_1
    :try_start_2
    const-string/jumbo v0, "value"

    invoke-virtual {v3, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    .line 3216
    :try_start_3
    invoke-virtual {p2, v4, v0}, Lorg/telegram/ui/bots/BotStorage;->setKey(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_3
    .catch Ljava/lang/RuntimeException; {:try_start_3 .. :try_end_3} :catch_0

    .line 3221
    invoke-static {v2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p4, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :catch_0
    move-exception p2

    .line 3218
    invoke-virtual {p2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p2

    invoke-static {v2, p3, v1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3212
    :catch_1
    const-string p2, "VALUE_INVALID"

    invoke-static {v2, p3, v1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    .line 3201
    :catch_2
    invoke-static {v2, p3, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void

    :catch_3
    move-exception p2

    .line 3191
    invoke-static {p2}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 3192
    const-string p2, ""

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p3

    if-nez p3, :cond_2

    .line 3193
    const-string p3, "UNKNOWN_ERROR"

    invoke-static {v2, p2, v1, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-direct {p0, p1, p5, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Lorg/json/JSONObject;)V

    :cond_2
    :goto_0
    return-void
.end method

.method private setupBotWebMessageBridge()V
    .locals 4

    .line 1428
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v0, :cond_3

    iget-boolean v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-nez v1, :cond_0

    goto :goto_0

    .line 1433
    :cond_0
    const-string v1, "TelegramWebviewProxy"

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->removeJavascriptInterface(Ljava/lang/String;)V

    .line 1435
    const-string v0, "WEB_MESSAGE_LISTENER"

    invoke-static {v0}, Landroidx/webkit/WebViewFeature;->isFeatureSupported(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 1436
    const-string v0, "Bot WebMessageListener is unsupported; native bridge disabled"

    invoke-virtual {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void

    .line 1443
    :cond_1
    const-string v0, "*"

    invoke-static {v0}, Ljava/util/Collections;->singleton(Ljava/lang/Object;)Ljava/util/Set;

    move-result-object v0

    .line 1444
    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-static {v1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->-$$Nest$fgetbotWebMessageListenerAdded(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)Z

    move-result v1

    if-nez v1, :cond_2

    .line 1445
    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    .line 1446
    new-instance v2, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda3;

    invoke-direct {v2, v1}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V

    const-string v3, "TelegramWebviewProxyMessage"

    invoke-static {v1, v3, v0, v2}, Landroidx/webkit/WebViewCompat;->addWebMessageListener(Landroid/webkit/WebView;Ljava/lang/String;Ljava/util/Set;Landroidx/webkit/WebViewCompat$WebMessageListener;)V

    .line 1456
    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    const/4 v2, 0x1

    invoke-static {v1, v2}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->-$$Nest$fputbotWebMessageListenerAdded(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;Z)V

    .line 1459
    :cond_2
    const-string v1, "DOCUMENT_START_SCRIPT"

    invoke-static {v1}, Landroidx/webkit/WebViewFeature;->isFeatureSupported(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_3

    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-static {v1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->-$$Nest$fgetbotWebMessageShimHandler(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)Landroidx/webkit/ScriptHandler;

    move-result-object v1

    if-nez v1, :cond_3

    .line 1461
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    const-string/jumbo v1, "window.TelegramWebviewProxy={postEvent:function(eventType,eventData){window.TelegramWebviewProxyMessage.postMessage(JSON.stringify({eventType:eventType,eventData:eventData}));}};"

    invoke-static {p0, v1, v0}, Landroidx/webkit/WebViewCompat;->addDocumentStartJavaScript(Landroid/webkit/WebView;Ljava/lang/String;Ljava/util/Set;)Landroidx/webkit/ScriptHandler;

    move-result-object v0

    invoke-static {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->-$$Nest$fputbotWebMessageShimHandler(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;Landroidx/webkit/ScriptHandler;)V

    :cond_3
    :goto_0
    return-void
.end method

.method private setupFlickerParams(Z)V
    .locals 2

    .line 1016
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isFlickeringCenter:Z

    .line 1017
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout$LayoutParams;

    if-eqz p1, :cond_0

    const/16 v1, 0x11

    goto :goto_0

    :cond_0
    const/16 v1, 0x30

    .line 1018
    :goto_0
    iput v1, v0, Landroid/widget/FrameLayout$LayoutParams;->gravity:I

    if-eqz p1, :cond_1

    const/high16 p1, 0x42c80000    # 100.0f

    .line 1020
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    iput p1, v0, Landroid/widget/FrameLayout$LayoutParams;->height:I

    iput p1, v0, Landroid/widget/FrameLayout$LayoutParams;->width:I

    goto :goto_1

    :cond_1
    const/4 p1, -0x1

    .line 1022
    iput p1, v0, Landroid/widget/FrameLayout$LayoutParams;->width:I

    const/4 p1, -0x2

    .line 1023
    iput p1, v0, Landroid/widget/FrameLayout$LayoutParams;->height:I

    .line 1026
    :goto_1
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p0}, Landroid/view/View;->requestLayout()V

    return-void
.end method

.method private setupWebView(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V
    .locals 1

    const/4 v0, 0x0

    .line 380
    invoke-direct {p0, p1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->setupWebView(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;Ljava/lang/Object;)V

    return-void
.end method

.method private setupWebView(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;Ljava/lang/Object;)V
    .locals 10
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "SetJavaScriptEnabled",
            "AddJavascriptInterface"
        }
    .end annotation

    .line 435
    const-string v0, ")"

    const-string v1, "(Linux; Android "

    iget-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v2, :cond_0

    .line 436
    invoke-virtual {v2}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->destroy()V

    .line 437
    iget-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {p0, v2}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    :cond_0
    if-eqz p1, :cond_1

    .line 440
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->removeFromParent(Landroid/view/View;)V

    :cond_1
    const/4 v2, 0x0

    const/4 v3, 0x1

    .line 443
    :try_start_0
    sget-boolean v4, Lorg/telegram/messenger/SharedConfig;->debugWebView:Z

    if-eqz v4, :cond_2

    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->isVerifyingAge()Z

    move-result v4

    if-nez v4, :cond_2

    move v4, v3

    goto :goto_0

    :catch_0
    move-exception v4

    goto :goto_1

    :cond_2
    move v4, v2

    :goto_0
    invoke-static {v4}, Landroid/webkit/WebView;->setWebContentsDebuggingEnabled(Z)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    .line 445
    :goto_1
    invoke-static {v4}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :goto_2
    if-nez p1, :cond_5

    .line 447
    new-instance v4, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v5

    iget-boolean v6, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    const-wide/16 v7, 0x0

    if-eqz v6, :cond_4

    iget-object v9, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    if-nez v9, :cond_3

    goto :goto_3

    :cond_3
    iget-wide v7, v9, Lorg/telegram/tgnet/TLRPC$User;->id:J

    :cond_4
    :goto_3
    invoke-direct {v4, v5, v6, v7, v8}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;-><init>(Landroid/content/Context;ZJ)V

    goto :goto_4

    :cond_5
    move-object v4, p1

    :goto_4
    iput-object v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    .line 448
    iget-boolean v5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-nez v5, :cond_6

    .line 449
    invoke-static {}, Landroid/webkit/CookieManager;->getInstance()Landroid/webkit/CookieManager;

    move-result-object v4

    .line 450
    invoke-virtual {v4, v3}, Landroid/webkit/CookieManager;->setAcceptCookie(Z)V

    .line 451
    iget-object v5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {v4, v5, v3}, Landroid/webkit/CookieManager;->setAcceptThirdPartyCookies(Landroid/webkit/WebView;Z)V

    .line 452
    invoke-static {}, Landroid/webkit/CookieManager;->getInstance()Landroid/webkit/CookieManager;

    move-result-object v4

    invoke-virtual {v4}, Landroid/webkit/CookieManager;->flush()V

    .line 453
    iget-object v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    iget-object v5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->opener:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    iput-object v5, v4, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->opener:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    goto :goto_5

    .line 455
    :cond_6
    sget v5, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-direct {p0, v5}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result v5

    invoke-virtual {v4, v5}, Landroid/view/View;->setBackgroundColor(I)V

    .line 457
    :goto_5
    iget v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v4}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v4

    iget-boolean v4, v4, Lorg/telegram/messenger/MessagesController;->disableBotFullscreenBlur:Z

    if-nez v4, :cond_7

    .line 458
    iget-object v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    const/4 v5, 0x2

    const/4 v6, 0x0

    invoke-virtual {v4, v5, v6}, Landroid/view/View;->setLayerType(ILandroid/graphics/Paint;)V

    .line 460
    :cond_7
    iget-object v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    iget-object v5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewScrollListener:Lorg/telegram/ui/web/BotWebViewContainer$WebViewScrollListener;

    invoke-virtual {v4, p0, v5}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->setContainers(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebViewScrollListener;)V

    .line 461
    iget-object v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    iget-object v5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->onCloseListener:Ljava/lang/Runnable;

    invoke-virtual {v4, v5}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->setCloseListener(Ljava/lang/Runnable;)V

    .line 462
    iget-object v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {v4}, Landroid/webkit/WebView;->getSettings()Landroid/webkit/WebSettings;

    move-result-object v4

    .line 463
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setJavaScriptEnabled(Z)V

    .line 464
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setGeolocationEnabled(Z)V

    .line 465
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setDomStorageEnabled(Z)V

    .line 466
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setDatabaseEnabled(Z)V

    .line 467
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setSupportMultipleWindows(Z)V

    .line 468
    invoke-virtual {v4, v2}, Landroid/webkit/WebSettings;->setAllowFileAccess(Z)V

    .line 469
    invoke-virtual {v4, v2}, Landroid/webkit/WebSettings;->setAllowContentAccess(Z)V

    .line 470
    invoke-virtual {v4, v2}, Landroid/webkit/WebSettings;->setAllowFileAccessFromFileURLs(Z)V

    .line 471
    invoke-virtual {v4, v2}, Landroid/webkit/WebSettings;->setAllowUniversalAccessFromFileURLs(Z)V

    .line 472
    iget-boolean v5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-nez v5, :cond_8

    .line 473
    sget-object v5, Landroid/webkit/WebSettings$RenderPriority;->HIGH:Landroid/webkit/WebSettings$RenderPriority;

    invoke-virtual {v4, v5}, Landroid/webkit/WebSettings;->setRenderPriority(Landroid/webkit/WebSettings$RenderPriority;)V

    const/4 v5, -0x1

    .line 474
    invoke-virtual {v4, v5}, Landroid/webkit/WebSettings;->setCacheMode(I)V

    .line 475
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setSaveFormData(Z)V

    .line 476
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setSavePassword(Z)V

    .line 477
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setSupportZoom(Z)V

    .line 478
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setBuiltInZoomControls(Z)V

    .line 479
    invoke-virtual {v4, v2}, Landroid/webkit/WebSettings;->setDisplayZoomControls(Z)V

    .line 480
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setUseWideViewPort(Z)V

    .line 481
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setLoadWithOverviewMode(Z)V

    .line 482
    sget v5, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v6, 0x1a

    if-lt v5, v6, :cond_8

    .line 483
    invoke-virtual {v4, v3}, Landroid/webkit/WebSettings;->setSafeBrowsingEnabled(Z)V

    .line 486
    :cond_8
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->isVerifyingAge()Z

    move-result v5

    if-eqz v5, :cond_9

    .line 487
    invoke-virtual {v4, v2}, Landroid/webkit/WebSettings;->setMediaPlaybackRequiresUserGesture(Z)V

    .line 491
    :cond_9
    :try_start_1
    invoke-virtual {v4}, Landroid/webkit/WebSettings;->getUserAgentString()Ljava/lang/String;

    move-result-object v5

    .line 492
    const-string v6, "; wv)"

    invoke-virtual {v5, v6, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v5

    .line 493
    const-string v6, "\\(Linux; Android.+;[^)]+\\)"

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    sget-object v1, Landroid/os/Build$VERSION;->RELEASE:Ljava/lang/String;

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v8, "; K)"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v5, v6, v7}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 494
    const-string v6, "Version/[\\d\\.]+ "

    const-string v7, ""

    invoke-virtual {v5, v6, v7}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 495
    iget-boolean v6, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-eqz v6, :cond_c

    .line 496
    sget-object v6, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {v6}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v6

    sget-object v7, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {v7}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7, v2}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object v6

    .line 497
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->getDevicePerformanceClass()I

    move-result v7

    if-nez v7, :cond_a

    .line 498
    const-string v3, "LOW"

    goto :goto_6

    :catch_1
    move-exception v0

    goto :goto_7

    :cond_a
    if-ne v7, v3, :cond_b

    const-string v3, "AVERAGE"

    goto :goto_6

    :cond_b
    const-string v3, "HIGH"

    .line 499
    :goto_6
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, " Telegram-Android/"

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, v6, Landroid/content/pm/PackageInfo;->versionName:Ljava/lang/String;

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, " ("

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v5, Landroid/os/Build;->MANUFACTURER:Ljava/lang/String;

    invoke-static {v5}, Lorg/telegram/ui/web/BotWebViewContainer;->capitalizeFirst(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, " "

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v5, Landroid/os/Build;->MODEL:Ljava/lang/String;

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, "; Android "

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "; SDK "

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, "; "

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    .line 501
    :cond_c
    invoke-virtual {v4, v5}, Landroid/webkit/WebSettings;->setUserAgentString(Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_8

    .line 503
    :goto_7
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 507
    :goto_8
    sget-object v0, Landroid/webkit/WebSettings$TextSize;->NORMAL:Landroid/webkit/WebSettings$TextSize;

    invoke-virtual {v4, v0}, Landroid/webkit/WebSettings;->setTextSize(Landroid/webkit/WebSettings$TextSize;)V

    .line 509
    new-instance v0, Ljava/io/File;

    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFilesDirFixed()Ljava/io/File;

    move-result-object v1

    const-string/jumbo v3, "webview_database"

    invoke-direct {v0, v1, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 510
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_d

    invoke-virtual {v0}, Ljava/io/File;->isDirectory()Z

    move-result v1

    if-nez v1, :cond_e

    :cond_d
    invoke-virtual {v0}, Ljava/io/File;->mkdirs()Z

    move-result v1

    if-eqz v1, :cond_f

    .line 511
    :cond_e
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Landroid/webkit/WebSettings;->setDatabasePath(Ljava/lang/String;)V

    .line 513
    :cond_f
    invoke-static {}, Landroid/webkit/GeolocationPermissions;->getInstance()Landroid/webkit/GeolocationPermissions;

    move-result-object v0

    invoke-virtual {v0}, Landroid/webkit/GeolocationPermissions;->clearAll()V

    .line 515
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {v0, v2}, Landroid/view/View;->setVerticalScrollBarEnabled(Z)V

    if-nez p1, :cond_10

    .line 516
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-eqz v0, :cond_10

    .line 517
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setAlpha(F)V

    .line 519
    :cond_10
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {p0, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 524
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-eqz v0, :cond_13

    .line 525
    instance-of p1, p2, Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    if-eqz p1, :cond_11

    .line 526
    check-cast p2, Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botWebViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    .line 528
    :cond_11
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botWebViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    if-nez p1, :cond_12

    .line 529
    new-instance p1, Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    invoke-direct {p1, p0}, Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;)V

    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botWebViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    .line 531
    :cond_12
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botWebViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    invoke-virtual {p1, p0}, Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;->setContainer(Lorg/telegram/ui/web/BotWebViewContainer;)V

    .line 532
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->setupBotWebMessageBridge()V

    goto :goto_a

    .line 534
    :cond_13
    instance-of v0, p2, Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;

    if-eqz v0, :cond_14

    .line 535
    check-cast p2, Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;

    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;

    .line 537
    :cond_14
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;

    const-string v0, "TelegramWebviewProxy"

    if-nez p2, :cond_15

    .line 538
    new-instance p1, Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;

    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-direct {p1, p2, p0}, Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;-><init>(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;Lorg/telegram/ui/web/BotWebViewContainer;)V

    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;

    .line 539
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {p2, p1, v0}, Landroid/webkit/WebView;->addJavascriptInterface(Ljava/lang/Object;Ljava/lang/String;)V

    goto :goto_9

    :cond_15
    if-nez p1, :cond_16

    .line 541
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {p1, p2, v0}, Landroid/webkit/WebView;->addJavascriptInterface(Ljava/lang/Object;Ljava/lang/String;)V

    .line 543
    :cond_16
    :goto_9
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;

    invoke-virtual {p1, p0}, Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;->setContainer(Lorg/telegram/ui/web/BotWebViewContainer;)V

    .line 546
    :goto_a
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->onWebViewCreated(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V

    .line 547
    sput-boolean v2, Lorg/telegram/ui/web/BotWebViewContainer;->firstWebView:Z

    return-void
.end method

.method private showDialog(ILorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/Runnable;)Z
    .locals 2

    const/4 v0, 0x0

    if-eqz p2, :cond_2

    .line 3437
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->ignoreDialog(I)Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 3440
    :cond_0
    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda36;

    invoke-direct {v1, p0, p3}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda36;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/Runnable;)V

    invoke-virtual {p2, v1}, Landroid/app/Dialog;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)V

    .line 3446
    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    .line 3447
    invoke-virtual {p2, v0}, Lorg/telegram/ui/ActionBar/AlertDialog;->setDismissDialogByButtons(Z)V

    .line 3448
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentDialog:Lorg/telegram/ui/ActionBar/AlertDialog;

    invoke-virtual {p2}, Lorg/telegram/ui/ActionBar/AlertDialog;->show()V

    .line 3450
    iget p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastDialogType:I

    if-eq p2, p1, :cond_1

    .line 3451
    iput p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastDialogType:I

    .line 3452
    iput v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->shownDialogsCount:I

    const-wide/16 p1, 0x0

    .line 3453
    iput-wide p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->blockedDialogsUntil:J

    .line 3455
    :cond_1
    iget p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->shownDialogsCount:I

    const/4 p2, 0x1

    add-int/2addr p1, p2

    iput p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->shownDialogsCount:I

    return p2

    :cond_2
    :goto_0
    return v0
.end method

.method private static tonsite2magic(Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    if-nez p0, :cond_0

    return-object p0

    .line 5490
    :cond_0
    invoke-static {p0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    .line 5491
    invoke-static {v0}, Lorg/telegram/ui/web/BotWebViewContainer;->isTonsite(Landroid/net/Uri;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 5492
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->getHostAuthority(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x1

    .line 5494
    :try_start_0
    invoke-static {v0, v1}, Ljava/net/IDN;->toASCII(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 5496
    :catch_0
    invoke-static {v0}, Lorg/telegram/ui/web/BotWebViewContainer;->rotateTONHost(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 5497
    sget-object v2, Lorg/telegram/ui/web/BotWebViewContainer;->rotatedTONHosts:Ljava/util/HashMap;

    if-nez v2, :cond_1

    new-instance v2, Ljava/util/HashMap;

    invoke-direct {v2}, Ljava/util/HashMap;-><init>()V

    sput-object v2, Lorg/telegram/ui/web/BotWebViewContainer;->rotatedTONHosts:Ljava/util/HashMap;

    .line 5498
    :cond_1
    sget-object v2, Lorg/telegram/ui/web/BotWebViewContainer;->rotatedTONHosts:Ljava/util/HashMap;

    invoke-virtual {v2, v1, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 5499
    invoke-static {p0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p0

    const-string v0, "https"

    invoke-static {p0, v1, v0}, Lorg/telegram/messenger/browser/Browser;->replaceHostname(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    :cond_2
    return-object p0
.end method

.method private unknownError()V
    .locals 1

    const/4 v0, 0x0

    .line 3402
    invoke-direct {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->unknownError(Ljava/lang/String;)V

    return-void
.end method

.method private unknownError(Ljava/lang/String;)V
    .locals 3

    .line 3406
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "UnknownError"

    sget v2, Lorg/telegram/messenger/R$string;->UnknownError:I

    invoke-static {v1, v2}, Lorg/telegram/messenger/LocaleController;->getString(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-eqz p1, :cond_0

    const-string v1, ": "

    invoke-virtual {v1, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    const-string p1, ""

    :goto_0
    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->error(Ljava/lang/String;)V

    return-void
.end method

.method private updateKeyboardFocusable()V
    .locals 3

    .line 574
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->wasFocusable:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    const/high16 v0, 0x60000

    .line 576
    invoke-virtual {p0, v0}, Landroid/view/ViewGroup;->setDescendantFocusability(I)V

    .line 577
    invoke-virtual {p0, v1}, Landroid/view/View;->setFocusable(Z)V

    .line 579
    iget-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v2, :cond_0

    .line 580
    invoke-virtual {v2, v0}, Landroid/view/ViewGroup;->setDescendantFocusability(I)V

    .line 581
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {v0}, Landroid/view/View;->clearFocus()V

    .line 583
    :cond_0
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->hideKeyboard(Landroid/view/View;)V

    .line 593
    :cond_1
    iput-boolean v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->wasFocusable:Z

    return-void
.end method


# virtual methods
.method public checkCreateWebView()V
    .locals 2

    .line 349
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailable:Z

    if-nez v0, :cond_0

    const/4 v0, 0x0

    .line 351
    :try_start_0
    invoke-direct {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->setupWebView(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-void

    :catchall_0
    move-exception v0

    .line 353
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 355
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    const/4 v0, 0x1

    .line 356
    iput-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailable:Z

    .line 357
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailableText:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 358
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v0, :cond_0

    .line 359
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {p0, v0}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    :cond_0
    return-void
.end method

.method public d(Ljava/lang/String;)V
    .locals 2

    .line 5483
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "[webviewcontainer] #"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->tag:I

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, " "

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    return-void
.end method

.method public destroyWebView()V
    .locals 3

    .line 1136
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "destroyWebView preserving="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-boolean v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->preserving:Z

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 1137
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v0, :cond_5

    .line 1138
    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 1139
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {p0, v0}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 1141
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->preserving:Z

    if-nez v0, :cond_1

    .line 1142
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->destroy()V

    .line 1143
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->onWebViewDestroyed(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V

    :cond_1
    const/4 v0, 0x0

    .line 1145
    iput-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isPageLoaded:Z

    .line 1146
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->updateKeyboardFocusable()V

    .line 1148
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    .line 1149
    iput-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->biometry:Lorg/telegram/ui/bots/BotBiometry;

    .line 1151
    :cond_2
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    if-eqz v0, :cond_3

    .line 1152
    iput-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->storage:Lorg/telegram/ui/bots/BotStorage;

    .line 1154
    :cond_3
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    if-eqz v0, :cond_4

    .line 1155
    iput-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->secureStorage:Lorg/telegram/ui/bots/BotStorage;

    .line 1157
    :cond_4
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->location:Lorg/telegram/ui/bots/BotLocation;

    if-eqz v0, :cond_5

    .line 1158
    iget-object v2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->notifyLocationChecked:Ljava/lang/Runnable;

    invoke-virtual {v0, v2}, Lorg/telegram/ui/bots/BotLocation;->unlisten(Ljava/lang/Runnable;)V

    .line 1159
    iput-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->location:Lorg/telegram/ui/bots/BotLocation;

    .line 1160
    iput-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->locationRequestContext:Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;

    :cond_5
    return-void
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 3

    .line 1192
    sget p2, Lorg/telegram/messenger/NotificationCenter;->didSetNewTheme:I

    const/4 v0, 0x0

    if-ne p1, p2, :cond_3

    .line 1193
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz p1, :cond_0

    .line 1194
    sget p2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-direct {p0, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result p2

    invoke-virtual {p1, p2}, Landroid/view/View;->setBackgroundColor(I)V

    .line 1196
    :cond_0
    iget-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewColorOverriden:Z

    if-nez p1, :cond_2

    .line 1197
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    new-instance p2, Landroid/graphics/PorterDuffColorFilter;

    sget p3, Lorg/telegram/ui/ActionBar/Theme;->key_bot_loadingIcon:I

    invoke-direct {p0, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result v1

    iput v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewColor:I

    sget-object v2, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {p2, v1, v2}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {p1, p2}, Lorg/telegram/ui/Components/BackupImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 1198
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    if-eqz p1, :cond_1

    .line 1199
    iget p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewColor:I

    invoke-virtual {p1, p2}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setColor(I)V

    .line 1200
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-virtual {p1, p3, p2, v1, v0}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setupGradient(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;FZ)V

    .line 1202
    :cond_1
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p1}, Landroid/view/View;->invalidate()V

    .line 1204
    :cond_2
    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyThemeChanged()V

    return-void

    .line 1205
    :cond_3
    sget p2, Lorg/telegram/messenger/NotificationCenter;->onActivityResultReceived:I

    const/4 v1, 0x2

    const/4 v2, 0x1

    if-ne p1, p2, :cond_4

    .line 1206
    aget-object p1, p3, v0

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    aget-object p2, p3, v2

    check-cast p2, Ljava/lang/Integer;

    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result p2

    aget-object p3, p3, v1

    check-cast p3, Landroid/content/Intent;

    invoke-virtual {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->onActivityResult(IILandroid/content/Intent;)V

    return-void

    .line 1207
    :cond_4
    sget p2, Lorg/telegram/messenger/NotificationCenter;->onRequestPermissionResultReceived:I

    if-ne p1, p2, :cond_5

    .line 1208
    aget-object p1, p3, v0

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    aget-object p2, p3, v2

    check-cast p2, [Ljava/lang/String;

    aget-object p3, p3, v1

    check-cast p3, [I

    invoke-virtual {p0, p1, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->onRequestPermissionsResult(I[Ljava/lang/String;[I)V

    :cond_5
    return-void
.end method

.method public drawChild(Landroid/graphics/Canvas;Landroid/view/View;J)Z
    .locals 4

    .line 892
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    const/high16 v1, 0x40000000    # 2.0f

    const/4 v2, 0x0

    if-ne p2, v0, :cond_3

    .line 893
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isFlickeringCenter:Z

    if-eqz v0, :cond_0

    .line 894
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 895
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    .line 896
    invoke-static {}, Lorg/telegram/ui/ActionBar/ActionBar;->getCurrentActionBarHeight()I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {v0}, Landroid/view/View;->getTranslationY()F

    move-result v0

    sub-float/2addr v3, v0

    div-float/2addr v3, v1

    invoke-virtual {p1, v2, v3}, Landroid/graphics/Canvas;->translate(FF)V

    .line 898
    :cond_0
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/ViewGroup;->drawChild(Landroid/graphics/Canvas;Landroid/view/View;J)Z

    move-result p2

    .line 899
    iget-boolean p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isFlickeringCenter:Z

    if-eqz p3, :cond_1

    .line 900
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 903
    :cond_1
    iget-boolean p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isFlickeringCenter:Z

    if-nez p3, :cond_2

    .line 904
    sget-object p3, Lorg/telegram/messenger/AndroidUtilities;->rectTmp:Landroid/graphics/RectF;

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result p4

    int-to-float p4, p4

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {p3, v2, v2, p4, v0}, Landroid/graphics/RectF;->set(FFFF)V

    .line 905
    iget-object p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerDrawable:Lorg/telegram/ui/Components/voip/CellFlickerDrawable;

    invoke-virtual {p4, p1, p3, v2, p0}, Lorg/telegram/ui/Components/voip/CellFlickerDrawable;->draw(Landroid/graphics/Canvas;Landroid/graphics/RectF;FLandroid/view/View;)V

    .line 906
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_2
    return p2

    .line 910
    :cond_3
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewNotAvailableText:Landroid/widget/TextView;

    if-ne p2, v0, :cond_4

    .line 911
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 912
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    .line 913
    invoke-static {}, Lorg/telegram/ui/ActionBar/ActionBar;->getCurrentActionBarHeight()I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {v0}, Landroid/view/View;->getTranslationY()F

    move-result v0

    sub-float/2addr v3, v0

    div-float/2addr v3, v1

    invoke-virtual {p1, v2, v3}, Landroid/graphics/Canvas;->translate(FF)V

    .line 914
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/ViewGroup;->drawChild(Landroid/graphics/Canvas;Landroid/view/View;J)Z

    move-result p0

    .line 915
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    return p0

    .line 918
    :cond_4
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-ne p2, v0, :cond_6

    sget-boolean v0, Lorg/telegram/messenger/AndroidUtilities;->makingGlobalBlurBitmap:Z

    if-nez v0, :cond_5

    invoke-virtual {p0}, Landroid/view/View;->getLayerType()I

    move-result v0

    const/4 v1, 0x2

    if-ne v0, v1, :cond_6

    invoke-virtual {p1}, Landroid/graphics/Canvas;->isHardwareAccelerated()Z

    move-result v0

    if-nez v0, :cond_6

    :cond_5
    const/4 p0, 0x1

    return p0

    .line 921
    :cond_6
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/ViewGroup;->drawChild(Landroid/graphics/Canvas;Landroid/view/View;J)Z

    move-result p0

    return p0
.end method

.method public evaluateJs(Ljava/lang/String;Z)V
    .locals 2

    .line 1179
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0, p2, p1}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;ZLjava/lang/String;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/NotificationCenter;->doOnIdle(Ljava/lang/Runnable;)V

    return-void
.end method

.method public getBotProxy()Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;
    .locals 0

    .line 410
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botWebViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    return-object p0
.end method

.method public getMinHeight()I
    .locals 2

    .line 844
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    instance-of v0, v0, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;

    if-eqz v0, :cond_0

    .line 845
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;

    .line 846
    invoke-virtual {v0}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->isFullSize()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 847
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {v0}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->getOffsetY()F

    move-result v0

    sub-float/2addr v1, v0

    iget p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->viewPortHeightOffset:F

    add-float/2addr v1, p0

    float-to-int p0, v1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public getOriginHost()Ljava/lang/String;
    .locals 0

    .line 1513
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 1514
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->getUrl()Ljava/lang/String;

    move-result-object p0

    .line 1515
    invoke-static {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->getOriginHost(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public getProxy()Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;
    .locals 0

    .line 414
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;

    return-object p0
.end method

.method public getTrustedOrigin()Ljava/lang/String;
    .locals 0

    .line 1424
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    return-object p0
.end method

.method public getUrlLoaded()Ljava/lang/String;
    .locals 0

    .line 699
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->mUrl:Ljava/lang/String;

    return-object p0
.end method

.method public getWebView()Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;
    .locals 0

    .line 946
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    return-object p0
.end method

.method public hasUserPermissions()Z
    .locals 0

    .line 703
    iget-boolean p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->hasUserPermissions:Z

    return p0
.end method

.method public invalidateViewPortHeight()V
    .locals 1

    const/4 v0, 0x0

    .line 831
    invoke-virtual {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->invalidateViewPortHeight(Z)V

    return-void
.end method

.method public invalidateViewPortHeight(Z)V
    .locals 1

    const/4 v0, 0x0

    .line 835
    invoke-virtual {p0, p1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->invalidateViewPortHeight(ZZ)V

    return-void
.end method

.method public invalidateViewPortHeight(ZZ)V
    .locals 4

    .line 858
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    .line 859
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isPageLoaded:Z

    if-nez v0, :cond_0

    if-eqz p2, :cond_5

    :cond_0
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-nez v0, :cond_1

    goto/16 :goto_1

    .line 863
    :cond_1
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    instance-of v0, v0, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;

    if-eqz v0, :cond_5

    .line 864
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;

    if-eqz p1, :cond_3

    .line 867
    invoke-virtual {v0}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->getSwipeOffsetY()F

    move-result v1

    invoke-virtual {v0}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->getOffsetY()F

    move-result v2

    neg-float v2, v2

    invoke-virtual {v0}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->getTopActionBarOffsetY()F

    move-result v3

    add-float/2addr v2, v3

    cmpl-float v1, v1, v2

    if-nez v1, :cond_2

    const/4 v1, 0x1

    goto :goto_0

    :cond_2
    const/4 v1, 0x0

    :goto_0
    iput-boolean v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastExpanded:Z

    .line 870
    :cond_3
    invoke-virtual {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->getMinHeight()I

    move-result v1

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {v0}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->getOffsetY()F

    move-result v3

    sub-float/2addr v2, v3

    invoke-virtual {v0}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->getSwipeOffsetY()F

    move-result v3

    sub-float/2addr v2, v3

    invoke-virtual {v0}, Lorg/telegram/ui/bots/ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer;->getTopActionBarOffsetY()F

    move-result v0

    add-float/2addr v2, v0

    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->viewPortHeightOffset:F

    add-float/2addr v2, v0

    float-to-int v0, v2

    invoke-static {v1, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    if-nez p2, :cond_4

    .line 871
    iget p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastViewportHeightReported:I

    if-ne v0, p2, :cond_4

    iget-boolean p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastViewportStateStable:Z

    if-ne p2, p1, :cond_4

    iget-boolean p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastViewportIsExpanded:Z

    iget-boolean v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastExpanded:Z

    if-eq p2, v1, :cond_5

    .line 877
    :cond_4
    iput v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastViewportHeightReported:I

    .line 878
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastViewportStateStable:Z

    .line 879
    iget-boolean p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastExpanded:Z

    iput-boolean p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastViewportIsExpanded:Z

    .line 881
    new-instance p2, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "{height:"

    invoke-direct {p2, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    int-to-float v0, v0

    .line 882
    sget v1, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr v0, v1

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v0, ",is_state_stable:"

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 883
    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string p1, ",is_expanded:"

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 884
    iget-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastExpanded:Z

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string/jumbo p1, "}"

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 885
    const-string/jumbo p1, "viewport_changed"

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent_fast(Ljava/lang/String;Ljava/lang/String;)V

    :cond_5
    :goto_1
    return-void
.end method

.method public isBackButtonVisible()Z
    .locals 0

    .line 1170
    iget-boolean p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isBackButtonVisible:Z

    return p0
.end method

.method public isBridgeRestrictedToOrigin()Z
    .locals 0

    .line 1420
    iget-boolean p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->restrictBridgeToOrigin:Z

    return p0
.end method

.method public isPageLoaded()Z
    .locals 0

    .line 727
    iget-boolean p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isPageLoaded:Z

    return p0
.end method

.method public loadFlickerAndSettingsItem(IJLorg/telegram/ui/ActionBar/ActionBarMenuSubItem;)V
    .locals 9

    .line 950
    invoke-static {p1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p4

    invoke-static {p2, p3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    invoke-virtual {p4, v0}, Lorg/telegram/messenger/MessagesController;->getUser(Ljava/lang/Long;)Lorg/telegram/tgnet/TLRPC$User;

    move-result-object p4

    .line 951
    invoke-static {p1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    invoke-virtual {v0, p2, p3}, Lorg/telegram/messenger/MessagesController;->getUserFull(J)Lorg/telegram/tgnet/TLRPC$UserFull;

    move-result-object v0

    .line 952
    invoke-static {p4}, Lorg/telegram/messenger/UserObject;->getPublicUsername(Lorg/telegram/tgnet/TLRPC$User;)Ljava/lang/String;

    move-result-object p4

    const/high16 v1, 0x3f800000    # 1.0f

    const/4 v2, 0x0

    const/4 v3, 0x0

    if-eqz p4, :cond_0

    .line 953
    const-string v4, "DurgerKingBot"

    invoke-virtual {p4, v4}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p4

    if-eqz p4, :cond_0

    .line 954
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p1, v3}, Landroid/view/View;->setVisibility(I)V

    .line 955
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p1, v1}, Landroid/view/View;->setAlpha(F)V

    .line 956
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    sget p2, Lorg/telegram/messenger/R$raw;->durgerking_placeholder:I

    sget p3, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-direct {p0, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->getColor(I)I

    move-result p3

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    invoke-static {p2, p3}, Lorg/telegram/messenger/SvgHelper;->getDrawable(ILjava/lang/Integer;)Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    move-result-object p2

    invoke-virtual {p1, v2, v2, p2}, Lorg/telegram/ui/Components/BackupImageView;->setImage(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V

    .line 957
    invoke-direct {p0, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->setupFlickerParams(Z)V

    return-void

    .line 962
    :cond_0
    invoke-static {p1}, Lorg/telegram/messenger/MediaDataController;->getInstance(I)Lorg/telegram/messenger/MediaDataController;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/messenger/MediaDataController;->getAttachMenuBots()Lorg/telegram/tgnet/TLRPC$TL_attachMenuBots;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$TL_attachMenuBots;->bots:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p4

    move v4, v3

    :cond_1
    if-ge v4, p4, :cond_2

    invoke-virtual {p1, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v4, v4, 0x1

    check-cast v5, Lorg/telegram/tgnet/TLRPC$TL_attachMenuBot;

    .line 963
    iget-wide v6, v5, Lorg/telegram/tgnet/TLRPC$TL_attachMenuBot;->bot_id:J

    cmp-long v6, v6, p2

    if-nez v6, :cond_1

    goto :goto_0

    :cond_2
    move-object v5, v2

    :goto_0
    const/4 p1, 0x1

    if-eqz v5, :cond_5

    .line 971
    invoke-static {v5}, Lorg/telegram/messenger/MediaDataController;->getPlaceholderStaticAttachMenuBotIcon(Lorg/telegram/tgnet/TLRPC$TL_attachMenuBot;)Lorg/telegram/tgnet/TLRPC$TL_attachMenuBotIcon;

    move-result-object p2

    if-nez p2, :cond_3

    .line 973
    invoke-static {v5}, Lorg/telegram/messenger/MediaDataController;->getStaticAttachMenuBotIcon(Lorg/telegram/tgnet/TLRPC$TL_attachMenuBot;)Lorg/telegram/tgnet/TLRPC$TL_attachMenuBotIcon;

    move-result-object p2

    goto :goto_1

    :cond_3
    move p1, v3

    :goto_1
    if-eqz p2, :cond_4

    .line 977
    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p3, v3}, Landroid/view/View;->setVisibility(I)V

    .line 978
    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p3, v1}, Landroid/view/View;->setAlpha(F)V

    .line 979
    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    iget-object p2, p2, Lorg/telegram/tgnet/TLRPC$TL_attachMenuBotIcon;->icon:Lorg/telegram/tgnet/TLRPC$Document;

    invoke-static {p2}, Lorg/telegram/messenger/ImageLocation;->getForDocument(Lorg/telegram/tgnet/TLRPC$Document;)Lorg/telegram/messenger/ImageLocation;

    move-result-object p2

    invoke-virtual {p3, p2, v2, v2, v5}, Lorg/telegram/ui/Components/BackupImageView;->setImage(Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Landroid/graphics/drawable/Drawable;Ljava/lang/Object;)V

    .line 980
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->setupFlickerParams(Z)V

    :cond_4
    return-void

    :cond_5
    const/16 p2, 0x200

    if-eqz v0, :cond_7

    .line 982
    iget-object p3, v0, Lorg/telegram/tgnet/TLRPC$UserFull;->bot_info:Lorg/telegram/tgnet/tl/TL_bots$BotInfo;

    if-eqz p3, :cond_7

    iget-object p3, p3, Lorg/telegram/tgnet/tl/TL_bots$BotInfo;->app_settings:Lorg/telegram/tgnet/tl/TL_bots$botAppSettings;

    if-eqz p3, :cond_7

    iget-object p3, p3, Lorg/telegram/tgnet/tl/TL_bots$botAppSettings;->placeholder_svg_path:Landroid/graphics/Path;

    if-eqz p3, :cond_7

    .line 983
    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p3, v3}, Landroid/view/View;->setVisibility(I)V

    .line 984
    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p3, v1}, Landroid/view/View;->setAlpha(F)V

    .line 985
    iget-object p3, v0, Lorg/telegram/tgnet/TLRPC$UserFull;->bot_info:Lorg/telegram/tgnet/tl/TL_bots$BotInfo;

    iget-object p3, p3, Lorg/telegram/tgnet/tl/TL_bots$BotInfo;->app_settings:Lorg/telegram/tgnet/tl/TL_bots$botAppSettings;

    iget-object p3, p3, Lorg/telegram/tgnet/tl/TL_bots$botAppSettings;->placeholder_svg_path:Landroid/graphics/Path;

    invoke-static {p3, p2, p2}, Lorg/telegram/messenger/SvgHelper;->getDrawableByPath(Landroid/graphics/Path;II)Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    move-result-object p2

    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    if-eqz p2, :cond_6

    .line 987
    iget p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewColor:I

    invoke-virtual {p2, p3}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setColor(I)V

    .line 988
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    sget p3, Lorg/telegram/ui/ActionBar/Theme;->key_bot_loadingIcon:I

    iget-object p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-virtual {p2, p3, p4, v1, v3}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setupGradient(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;FZ)V

    .line 990
    :cond_6
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    invoke-virtual {p2, v2, v2, p3}, Lorg/telegram/ui/Components/BackupImageView;->setImage(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V

    .line 991
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->setupFlickerParams(Z)V

    return-void

    .line 993
    :cond_7
    new-instance p3, Landroid/graphics/Path;

    invoke-direct {p3}, Landroid/graphics/Path;-><init>()V

    .line 995
    sget-object p4, Lorg/telegram/messenger/AndroidUtilities;->rectTmp:Landroid/graphics/RectF;

    const v0, 0x42d5547a

    const v4, 0x43705ae1

    invoke-virtual {p4, v0, v0, v4, v4}, Landroid/graphics/RectF;->set(FFFF)V

    .line 996
    sget-object v5, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    const/high16 v6, 0x41900000    # 18.0f

    invoke-virtual {p3, p4, v6, v6, v5}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    const v7, 0x4387d28f

    const v8, 0x43caaae1

    .line 997
    invoke-virtual {p4, v7, v0, v8, v4}, Landroid/graphics/RectF;->set(FFFF)V

    .line 998
    invoke-virtual {p3, p4, v6, v6, v5}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    .line 999
    invoke-virtual {p4, v0, v7, v4, v8}, Landroid/graphics/RectF;->set(FFFF)V

    .line 1000
    invoke-virtual {p3, p4, v6, v6, v5}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    .line 1001
    invoke-virtual {p4, v7, v7, v8, v8}, Landroid/graphics/RectF;->set(FFFF)V

    .line 1002
    invoke-virtual {p3, p4, v6, v6, v5}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V

    .line 1003
    iget-object p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p4, v3}, Landroid/view/View;->setVisibility(I)V

    .line 1004
    iget-object p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p4, v1}, Landroid/view/View;->setAlpha(F)V

    .line 1005
    invoke-static {p3, p2, p2}, Lorg/telegram/messenger/SvgHelper;->getDrawableByPath(Landroid/graphics/Path;II)Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    move-result-object p2

    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    if-eqz p2, :cond_8

    .line 1007
    iget p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewColor:I

    invoke-virtual {p2, p3}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setColor(I)V

    .line 1008
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    sget p3, Lorg/telegram/ui/ActionBar/Theme;->key_bot_loadingIcon:I

    iget-object p4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-virtual {p2, p3, p4, v1, v3}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setupGradient(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;FZ)V

    .line 1010
    :cond_8
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    invoke-virtual {p2, v2, v2, p3}, Lorg/telegram/ui/Components/BackupImageView;->setImage(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V

    .line 1011
    invoke-direct {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->setupFlickerParams(Z)V

    return-void
.end method

.method public loadUrl(ILjava/lang/String;Z)V
    .locals 4

    .line 1059
    iput p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    .line 1060
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-eqz v0, :cond_1

    .line 1061
    iget-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->documentGeneration:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->documentGeneration:J

    .line 1062
    iput-boolean p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->restrictBridgeToOrigin:Z

    if-eqz p3, :cond_0

    .line 1063
    invoke-static {p2}, Lorg/telegram/ui/web/BotWebViewContainer;->getOriginHost(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    goto :goto_0

    :cond_0
    const/4 p3, 0x0

    :goto_0
    iput-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    .line 1064
    iget-object p3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz p3, :cond_1

    .line 1065
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->setupBotWebMessageBridge()V

    .line 1068
    :cond_1
    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    new-instance p3, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda2;

    invoke-direct {p3, p0, p2}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;Ljava/lang/String;)V

    invoke-virtual {p1, p3}, Lorg/telegram/messenger/NotificationCenter;->doOnIdle(Ljava/lang/Runnable;)V

    return-void
.end method

.method public notifyEmojiStatusAccess(Ljava/lang/String;)V
    .locals 1

    .line 3368
    const-string/jumbo v0, "status"

    invoke-static {v0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    const-string v0, "emoji_status_access_requested"

    invoke-virtual {p0, v0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method public notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V
    .locals 2

    .line 1217
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    const-string/jumbo v1, "notifyEvent "

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->isTrustedDocumentCurrent()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1218
    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " dropped for untrusted document"

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void

    .line 1221
    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 1222
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "window.Telegram.WebView.receiveEvent(\'"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "\', "

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ");"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->evaluateJs(Ljava/lang/String;Z)V

    return-void
.end method

.method public notifyThemeChanged()V
    .locals 2

    .line 1213
    const-string/jumbo v0, "theme_changed"

    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->buildThemeParams()Lorg/json/JSONObject;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method public onActivityResult(IILandroid/content/Intent;)V
    .locals 2

    const/16 v0, 0xbb8

    if-ne p1, v0, :cond_3

    .line 802
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->mFilePathCallback:Landroid/webkit/ValueCallback;

    if-eqz p1, :cond_3

    const/4 p1, -0x1

    const/4 v0, 0x0

    if-ne p2, p1, :cond_1

    if-eqz p3, :cond_1

    .line 806
    invoke-virtual {p3}, Landroid/content/Intent;->getClipData()Landroid/content/ClipData;

    move-result-object p1

    const/4 p2, 0x0

    if-eqz p1, :cond_0

    .line 807
    invoke-virtual {p3}, Landroid/content/Intent;->getClipData()Landroid/content/ClipData;

    move-result-object p1

    .line 808
    invoke-virtual {p1}, Landroid/content/ClipData;->getItemCount()I

    move-result p3

    new-array p3, p3, [Landroid/net/Uri;

    .line 809
    :goto_0
    invoke-virtual {p1}, Landroid/content/ClipData;->getItemCount()I

    move-result v1

    if-ge p2, v1, :cond_2

    .line 810
    invoke-virtual {p1, p2}, Landroid/content/ClipData;->getItemAt(I)Landroid/content/ClipData$Item;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/ClipData$Item;->getUri()Landroid/net/Uri;

    move-result-object v1

    aput-object v1, p3, p2

    add-int/lit8 p2, p2, 0x1

    goto :goto_0

    .line 812
    :cond_0
    invoke-virtual {p3}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object p1

    if-eqz p1, :cond_1

    const/4 p1, 0x1

    .line 813
    new-array p1, p1, [Landroid/net/Uri;

    invoke-virtual {p3}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object p3

    aput-object p3, p1, p2

    move-object p3, p1

    goto :goto_1

    :cond_1
    move-object p3, v0

    .line 817
    :cond_2
    :goto_1
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->mFilePathCallback:Landroid/webkit/ValueCallback;

    invoke-interface {p1, p3}, Landroid/webkit/ValueCallback;->onReceiveValue(Ljava/lang/Object;)V

    .line 818
    iput-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->mFilePathCallback:Landroid/webkit/ValueCallback;

    :cond_3
    return-void
.end method

.method public onAttachedToWindow()V
    .locals 2

    .line 1084
    invoke-super {p0}, Landroid/view/View;->onAttachedToWindow()V

    .line 1085
    const-string v0, "attached"

    invoke-virtual {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 1087
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->didSetNewTheme:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 1088
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->onActivityResultReceived:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 1089
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->onRequestPermissionResultReceived:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 1091
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$3;

    invoke-direct {v0, p0}, Lorg/telegram/ui/web/BotWebViewContainer$3;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;)V

    invoke-static {p0, v0}, Lorg/telegram/ui/Components/Bulletin;->addDelegate(Landroid/widget/FrameLayout;Lorg/telegram/ui/Components/Bulletin$Delegate;)V

    return-void
.end method

.method public onBackPressed()Z
    .locals 2

    .line 627
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    .line 630
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isBackButtonVisible:Z

    if-eqz v0, :cond_1

    .line 631
    const-string v0, "back_button_pressed"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    const/4 p0, 0x1

    return p0

    :cond_1
    return v1
.end method

.method public onDetachedFromWindow()V
    .locals 2

    .line 1105
    invoke-super {p0}, Landroid/view/View;->onDetachedFromWindow()V

    .line 1106
    const-string v0, "detached"

    invoke-virtual {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 1108
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->didSetNewTheme:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 1109
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->onActivityResultReceived:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 1110
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->onRequestPermissionResultReceived:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 1112
    invoke-static {p0}, Lorg/telegram/ui/Components/Bulletin;->removeDelegate(Landroid/widget/FrameLayout;)V

    return-void
.end method

.method public onErrorShown(ZILjava/lang/String;)V
    .locals 0

    return-void
.end method

.method public onFaviconChanged(Landroid/graphics/Bitmap;)V
    .locals 0

    return-void
.end method

.method public onInvoiceStatusUpdate(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    const/4 v0, 0x0

    .line 758
    invoke-virtual {p0, p1, p2, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->onInvoiceStatusUpdate(Ljava/lang/String;Ljava/lang/String;Z)V

    return-void
.end method

.method public onInvoiceStatusUpdate(Ljava/lang/String;Ljava/lang/String;Z)V
    .locals 3

    .line 763
    const-string v0, "invoice_closed "

    :try_start_0
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1}, Lorg/json/JSONObject;-><init>()V

    .line 764
    const-string/jumbo v2, "slug"

    invoke-virtual {v1, v2, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 765
    const-string/jumbo v2, "status"

    invoke-virtual {v1, v2, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 766
    const-string p2, "invoice_closed"

    invoke-virtual {p0, p2, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    .line 767
    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-static {p2}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    if-nez p3, :cond_0

    .line 769
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentPaymentSlug:Ljava/lang/String;

    invoke-static {p2, p1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    .line 770
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentPaymentSlug:Ljava/lang/String;
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    :cond_0
    return-void

    :catch_0
    move-exception p0

    .line 773
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method public onMainButtonPressed()V
    .locals 2

    .line 783
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 784
    const-string v0, "main_button_pressed"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method public onMeasure(II)V
    .locals 1

    .line 933
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->forceHeight:I

    if-ltz v0, :cond_0

    const/high16 p2, 0x40000000    # 2.0f

    .line 934
    invoke-static {v0, p2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p2

    .line 936
    :cond_0
    invoke-super {p0, p1, p2}, Landroid/widget/FrameLayout;->onMeasure(II)V

    .line 938
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerDrawable:Lorg/telegram/ui/Components/voip/CellFlickerDrawable;

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result p0

    invoke-virtual {p1, p0}, Lorg/telegram/ui/Components/voip/CellFlickerDrawable;->setParentWidth(I)V

    return-void
.end method

.method public onRequestPermissionsResult(I[Ljava/lang/String;[I)V
    .locals 0

    const/16 p2, 0xfa0

    if-ne p1, p2, :cond_0

    .line 794
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->onPermissionsRequestResultCallback:Ljava/lang/Runnable;

    if-eqz p1, :cond_0

    .line 795
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    const/4 p1, 0x0

    .line 796
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->onPermissionsRequestResultCallback:Ljava/lang/Runnable;

    :cond_0
    return-void
.end method

.method public onSecondaryButtonPressed()V
    .locals 2

    .line 788
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 789
    const-string/jumbo v0, "secondary_button_pressed"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method public onSettingsButtonPressed()V
    .locals 2

    .line 778
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->lastClickMs:J

    .line 779
    const-string/jumbo v0, "settings_button_pressed"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    return-void
.end method

.method public onSizeChanged(IIII)V
    .locals 0

    .line 824
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/View;->onSizeChanged(IIII)V

    .line 825
    iget-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isViewPortByMeasureSuppressed:Z

    if-nez p1, :cond_0

    const/4 p1, 0x1

    .line 826
    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->invalidateViewPortHeight(Z)V

    :cond_0
    return-void
.end method

.method public onTitleChanged(Ljava/lang/String;)V
    .locals 0

    return-void
.end method

.method public onURLChanged(Ljava/lang/String;ZZ)V
    .locals 0

    return-void
.end method

.method public onWebViewCreated(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V
    .locals 0

    return-void
.end method

.method public onWebViewDestroyed(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V
    .locals 0

    return-void
.end method

.method public preserveWebView()V
    .locals 4

    .line 1117
    const-string/jumbo v0, "preserveWebView"

    invoke-virtual {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    const/4 v0, 0x1

    .line 1118
    iput-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->preserving:Z

    .line 1119
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-eqz v0, :cond_0

    .line 1120
    const-string v0, "is_visible"

    sget-object v1, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    invoke-static {v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v0

    const-string/jumbo v1, "visibility_changed"

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    .line 1121
    iget-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->documentGeneration:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->documentGeneration:J

    .line 1123
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 1124
    invoke-virtual {v0, v1, v1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->setContainers(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebViewScrollListener;)V

    .line 1125
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->setCloseListener(Ljava/lang/Runnable;)V

    .line 1127
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botWebViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    if-eqz v0, :cond_2

    .line 1128
    invoke-virtual {v0, v1}, Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;->setContainer(Lorg/telegram/ui/web/BotWebViewContainer;)V

    .line 1130
    :cond_2
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;

    if-eqz p0, :cond_3

    .line 1131
    invoke-virtual {p0, v1}, Lorg/telegram/ui/web/BotWebViewContainer$WebViewProxy;->setContainer(Lorg/telegram/ui/web/BotWebViewContainer;)V

    :cond_3
    return-void
.end method

.method public reload()V
    .locals 2

    .line 1030
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    new-instance v1, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda31;

    invoke-direct {v1, p0}, Lorg/telegram/ui/web/BotWebViewContainer$$ExternalSyntheticLambda31;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/NotificationCenter;->doOnIdle(Ljava/lang/Runnable;)V

    return-void
.end method

.method public replaceWebView(ILorg/telegram/ui/web/BotWebViewContainer$MyWebView;Ljava/lang/Object;Ljava/lang/String;Z)V
    .locals 4

    .line 366
    iput p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->currentAccount:I

    const/4 p1, 0x0

    .line 367
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->preserving:Z

    .line 368
    iget-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-eqz p1, :cond_1

    .line 369
    iget-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->documentGeneration:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->documentGeneration:J

    .line 370
    iput-boolean p5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->restrictBridgeToOrigin:Z

    if-eqz p5, :cond_0

    .line 371
    invoke-static {p4}, Lorg/telegram/ui/web/BotWebViewContainer;->getOriginHost(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->trustedOrigin:Ljava/lang/String;

    .line 373
    :cond_1
    invoke-direct {p0, p2, p3}, Lorg/telegram/ui/web/BotWebViewContainer;->setupWebView(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;Ljava/lang/Object;)V

    .line 374
    iget-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-eqz p1, :cond_2

    .line 375
    const-string p1, "is_visible"

    sget-object p2, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-static {p1, p2}, Lorg/telegram/ui/web/BotWebViewContainer;->obj(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    const-string/jumbo p2, "visibility_changed"

    invoke-virtual {p0, p2, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->notifyEvent(Ljava/lang/String;Lorg/json/JSONObject;)V

    :cond_2
    return-void
.end method

.method public reportSafeInsets(Landroid/graphics/Rect;I)V
    .locals 1

    const/4 v0, 0x0

    .line 3341
    invoke-direct {p0, p1, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->reportSafeInsets(Landroid/graphics/Rect;Z)V

    .line 3342
    invoke-direct {p0, p2, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->reportSafeContentInsets(IZ)V

    return-void
.end method

.method public resetWebView()V
    .locals 1

    const/4 v0, 0x0

    .line 1166
    iput-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    return-void
.end method

.method public restoreButtonData()V
    .locals 4

    .line 746
    :try_start_0
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->buttonData:Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 747
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botWebViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->createRequestContext()Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;

    move-result-object v1

    const-string/jumbo v2, "web_app_setup_main_button"

    iget-object v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->buttonData:Ljava/lang/String;

    invoke-direct {p0, v0, v1, v2, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->onEventReceived(Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V

    .line 749
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->secondaryButtonData:Ljava/lang/String;

    if-eqz v0, :cond_1

    .line 750
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botWebViewProxy:Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;

    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->createRequestContext()Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;

    move-result-object v1

    const-string/jumbo v2, "web_app_setup_secondary_button"

    iget-object v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->secondaryButtonData:Ljava/lang/String;

    invoke-direct {p0, v0, v1, v2, v3}, Lorg/telegram/ui/web/BotWebViewContainer;->onEventReceived(Lorg/telegram/ui/web/BotWebViewContainer$BotWebViewProxy;Lorg/telegram/ui/web/BotWebViewContainer$WebRequestContext;Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :cond_1
    return-void

    :catch_0
    move-exception p0

    .line 753
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method public setBotUser(Lorg/telegram/tgnet/TLRPC$User;)V
    .locals 0

    .line 707
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->botUser:Lorg/telegram/tgnet/TLRPC$User;

    return-void
.end method

.method public setDelegate(Lorg/telegram/ui/web/BotWebViewContainer$Delegate;)V
    .locals 0

    .line 1309
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    return-void
.end method

.method public setFlickerViewColor(I)V
    .locals 4

    .line 330
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->computePerceivedBrightness(I)F

    move-result v0

    const v1, 0x3f333333    # 0.7f

    cmpl-float v0, v0, v1

    if-lez v0, :cond_0

    const/4 v0, 0x0

    const v1, -0x41e66666    # -0.15f

    .line 333
    invoke-static {p1, v0, v1}, Lorg/telegram/ui/ActionBar/Theme;->adaptHSV(IFF)I

    move-result p1

    goto :goto_0

    :cond_0
    const v0, 0x3ccccccd    # 0.025f

    const v1, 0x3e19999a    # 0.15f

    .line 335
    invoke-static {p1, v0, v1}, Lorg/telegram/ui/ActionBar/Theme;->adaptHSV(IFF)I

    move-result p1

    .line 337
    :goto_0
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewColor:I

    if-ne v0, p1, :cond_1

    return-void

    .line 338
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    new-instance v1, Landroid/graphics/PorterDuffColorFilter;

    iput p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewColor:I

    sget-object v2, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v1, p1, v2}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/BackupImageView;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 339
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    if-eqz p1, :cond_2

    .line 340
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewColor:I

    invoke-virtual {p1, v0}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setColor(I)V

    .line 341
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewDrawable:Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_bot_loadingIcon:I

    iget-object v1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const/high16 v2, 0x3f800000    # 1.0f

    const/4 v3, 0x0

    invoke-virtual {p1, v0, v1, v2, v3}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setupGradient(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;FZ)V

    :cond_2
    const/4 p1, 0x1

    .line 343
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerViewColorOverriden:Z

    .line 344
    iget-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {p1}, Landroid/view/View;->invalidate()V

    .line 345
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public setForceHeight(I)V
    .locals 1

    .line 926
    iget v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->forceHeight:I

    if-ne v0, p1, :cond_0

    return-void

    .line 927
    :cond_0
    iput p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->forceHeight:I

    .line 928
    invoke-virtual {p0}, Landroid/view/View;->requestLayout()V

    return-void
.end method

.method public setIsBackButtonVisible(Z)V
    .locals 0

    .line 695
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isBackButtonVisible:Z

    return-void
.end method

.method public setKeyboardFocusable(Z)V
    .locals 0

    .line 597
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->keyboardFocusable:Z

    .line 598
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->updateKeyboardFocusable()V

    return-void
.end method

.method public setOnCloseRequestedListener(Ljava/lang/Runnable;)V
    .locals 0

    .line 1292
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->onCloseListener:Ljava/lang/Runnable;

    .line 1293
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz p0, :cond_0

    .line 1294
    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->setCloseListener(Ljava/lang/Runnable;)V

    :cond_0
    return-void
.end method

.method public setOnVerifiedAge(Lorg/telegram/messenger/Utilities$Callback4;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/messenger/Utilities$Callback4<",
            "Ljava/lang/Boolean;",
            "Ljava/lang/Double;",
            "Ljava/lang/String;",
            "Ljava/lang/Double;",
            ">;)V"
        }
    .end annotation

    .line 5575
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->onVerifiedAge:Lorg/telegram/messenger/Utilities$Callback4;

    return-void
.end method

.method public setOpener(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;)V
    .locals 1

    .line 421
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->opener:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    .line 422
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->bot:Z

    if-nez v0, :cond_0

    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz p0, :cond_0

    .line 423
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->opener:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    :cond_0
    return-void
.end method

.method public setPageLoaded(Ljava/lang/String;Z)V
    .locals 7

    .line 638
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v0, :cond_0

    iget-boolean v1, v0, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->dangerousUrl:Z

    if-eqz v1, :cond_0

    iget-object v1, v0, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->urlFallback:Ljava/lang/String;

    goto :goto_0

    :cond_0
    move-object v1, p1

    :goto_0
    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->canGoBack()Z

    move-result v0

    if-nez v0, :cond_1

    goto :goto_1

    :cond_1
    move v0, v2

    goto :goto_2

    :cond_2
    :goto_1
    move v0, v3

    :goto_2
    iget-object v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v4, :cond_4

    invoke-virtual {v4}, Landroid/webkit/WebView;->canGoForward()Z

    move-result v4

    if-nez v4, :cond_3

    goto :goto_3

    :cond_3
    move v4, v2

    goto :goto_4

    :cond_4
    :goto_3
    move v4, v3

    :goto_4
    invoke-virtual {p0, v1, v0, v4}, Lorg/telegram/ui/web/BotWebViewContainer;->onURLChanged(Ljava/lang/String;ZZ)V

    .line 640
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v0, :cond_5

    .line 641
    invoke-static {v0, v3}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->-$$Nest$fputisPageLoaded(Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;Z)V

    .line 642
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->updateKeyboardFocusable()V

    .line 645
    :cond_5
    iget-boolean v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isPageLoaded:Z

    if-eqz v0, :cond_6

    .line 646
    const-string/jumbo p1, "setPageLoaded: already loaded"

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    return-void

    :cond_6
    const/4 v0, 0x0

    const/high16 v1, 0x3f800000    # 1.0f

    if-eqz p2, :cond_7

    .line 650
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz p2, :cond_7

    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    if-eqz p2, :cond_7

    .line 651
    new-instance p2, Landroid/animation/AnimatorSet;

    invoke-direct {p2}, Landroid/animation/AnimatorSet;-><init>()V

    .line 652
    iget-object v4, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    new-array v5, v3, [F

    aput v1, v5, v2

    .line 653
    sget-object v1, Landroid/view/View;->ALPHA:Landroid/util/Property;

    invoke-static {v4, v1, v5}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v4

    iget-object v5, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    new-array v6, v3, [F

    aput v0, v6, v2

    .line 654
    invoke-static {v5, v1, v6}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    const/4 v1, 0x2

    new-array v1, v1, [Landroid/animation/Animator;

    aput-object v4, v1, v2

    aput-object v0, v1, v3

    .line 652
    invoke-virtual {p2, v1}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 656
    new-instance v0, Lorg/telegram/ui/web/BotWebViewContainer$2;

    invoke-direct {v0, p0}, Lorg/telegram/ui/web/BotWebViewContainer$2;-><init>(Lorg/telegram/ui/web/BotWebViewContainer;)V

    invoke-virtual {p2, v0}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 662
    invoke-virtual {p2}, Landroid/animation/AnimatorSet;->start()V

    goto :goto_5

    .line 664
    :cond_7
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz p2, :cond_8

    .line 665
    invoke-virtual {p2, v1}, Landroid/view/View;->setAlpha(F)V

    .line 667
    :cond_8
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    if-eqz p2, :cond_9

    .line 668
    invoke-virtual {p2, v0}, Landroid/view/View;->setAlpha(F)V

    .line 669
    iget-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerView:Lorg/telegram/ui/Components/BackupImageView;

    const/16 v0, 0x8

    invoke-virtual {p2, v0}, Landroid/view/View;->setVisibility(I)V

    .line 672
    :cond_9
    :goto_5
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->mUrl:Ljava/lang/String;

    .line 673
    const-string/jumbo p1, "setPageLoaded: isPageLoaded = true!"

    invoke-virtual {p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 674
    iput-boolean v3, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isPageLoaded:Z

    .line 675
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->updateKeyboardFocusable()V

    .line 676
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->delegate:Lorg/telegram/ui/web/BotWebViewContainer$Delegate;

    invoke-interface {p0}, Lorg/telegram/ui/web/BotWebViewContainer$Delegate;->onWebAppReady()V

    return-void
.end method

.method public setParentActivity(Landroid/app/Activity;)V
    .locals 0

    .line 731
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->parentActivity:Landroid/app/Activity;

    return-void
.end method

.method public setState(ZLjava/lang/String;)V
    .locals 2

    .line 688
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "setState("

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, ", "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ")"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/web/BotWebViewContainer;->d(Ljava/lang/String;)V

    .line 689
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isPageLoaded:Z

    .line 690
    iput-object p2, p0, Lorg/telegram/ui/web/BotWebViewContainer;->mUrl:Ljava/lang/String;

    .line 691
    invoke-direct {p0}, Lorg/telegram/ui/web/BotWebViewContainer;->updateKeyboardFocusable()V

    return-void
.end method

.method public setViewPortByMeasureSuppressed(Z)V
    .locals 0

    .line 324
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->isViewPortByMeasureSuppressed:Z

    return-void
.end method

.method public setViewPortHeightOffset(F)V
    .locals 0

    .line 854
    iput p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->viewPortHeightOffset:F

    return-void
.end method

.method public setWasOpenedByBot(Lorg/telegram/ui/bots/WebViewRequestProps;)V
    .locals 0

    .line 1305
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->wasOpenedByBot:Lorg/telegram/ui/bots/WebViewRequestProps;

    return-void
.end method

.method public setWasOpenedByLinkIntent(Z)V
    .locals 0

    .line 1300
    iput-boolean p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->wasOpenedByLinkIntent:Z

    return-void
.end method

.method public setWebViewProgressListener(Landroidx/core/util/Consumer;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroidx/core/util/Consumer<",
            "Ljava/lang/Float;",
            ">;)V"
        }
    .end annotation

    .line 942
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewProgressListener:Landroidx/core/util/Consumer;

    return-void
.end method

.method public setWebViewScrollListener(Lorg/telegram/ui/web/BotWebViewContainer$WebViewScrollListener;)V
    .locals 1

    .line 1284
    iput-object p1, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webViewScrollListener:Lorg/telegram/ui/web/BotWebViewContainer$WebViewScrollListener;

    .line 1285
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->webView:Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;

    if-eqz v0, :cond_0

    .line 1286
    invoke-virtual {v0, p0, p1}, Lorg/telegram/ui/web/BotWebViewContainer$MyWebView;->setContainers(Lorg/telegram/ui/web/BotWebViewContainer;Lorg/telegram/ui/web/BotWebViewContainer$WebViewScrollListener;)V

    :cond_0
    return-void
.end method

.method public showLinkCopiedBulletin()V
    .locals 1

    .line 256
    iget-object v0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {p0, v0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyLinkBulletin()Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/Bulletin;->show(Z)Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public updateFlickerBackgroundColor(I)V
    .locals 2

    .line 610
    iget-object p0, p0, Lorg/telegram/ui/web/BotWebViewContainer;->flickerDrawable:Lorg/telegram/ui/Components/voip/CellFlickerDrawable;

    const/16 v0, 0x99

    const/16 v1, 0xcc

    invoke-virtual {p0, p1, v0, v1}, Lorg/telegram/ui/Components/voip/CellFlickerDrawable;->setColors(III)V

    return-void
.end method
