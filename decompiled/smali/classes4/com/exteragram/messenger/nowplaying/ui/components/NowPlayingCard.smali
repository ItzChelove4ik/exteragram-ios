.class public abstract Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;
.super Landroid/widget/FrameLayout;
.source "SourceFile"


# annotations
.annotation build Landroid/annotation/SuppressLint;
    value = {
        "ViewConstructor"
    }
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0008\n\u0002\u0010\u0008\n\u0002\u0008\u0006\u0008\u0017\u0018\u0000 72\u00020\u0001:\u00017B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0008\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\u0008\u0006\u0010\u0007J\u0008\u0010&\u001a\u00020\'H\u0002J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0010J\u0008\u0010+\u001a\u00020)H\u0002J\u0008\u0010,\u001a\u00020)H\u0002J\u0008\u0010-\u001a\u00020\u0016H\u0002J\u0008\u0010.\u001a\u00020)H\u0002J\u0008\u0010/\u001a\u00020)H\u0002J\u0008\u00100\u001a\u00020)H\u0002J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u000202H\u0002J\u0008\u00104\u001a\u00020)H\u0014J\u0008\u00105\u001a\u00020)H\u0014J\u0008\u00106\u001a\u00020)H\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0008\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000c\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00068"
    }
    d2 = {
        "Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;",
        "Landroid/widget/FrameLayout;",
        "context",
        "Landroid/content/Context;",
        "resourcesProvider",
        "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;",
        "<init>",
        "(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V",
        "imageView",
        "Lorg/telegram/ui/Components/BackupImageView;",
        "nameView",
        "Lorg/telegram/ui/ActionBar/SimpleTextView;",
        "artistView",
        "albumView",
        "cardLayout",
        "nowPlayingCardData",
        "Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;",
        "playPauseButton",
        "Landroid/widget/ImageView;",
        "playPauseDrawable",
        "Lorg/telegram/ui/Components/PlayPauseDrawable;",
        "isPlaying",
        "",
        "player",
        "Landroidx/media3/exoplayer/ExoPlayer;",
        "audioManager",
        "Landroid/media/AudioManager;",
        "audioFocusRequest",
        "Landroid/media/AudioFocusRequest;",
        "resumeOnFocusGain",
        "audioFocusChangeListener",
        "Landroid/media/AudioManager$OnAudioFocusChangeListener;",
        "emoji",
        "Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;",
        "currentDocId",
        "",
        "currentPreviewUrl",
        "",
        "getCoverCornerRadius",
        "",
        "set",
        "",
        "cardData",
        "initializePlayer",
        "togglePlayPause",
        "requestAudioFocus",
        "abandonAudioFocus",
        "updatePlayPauseButton",
        "releasePlayer",
        "getThemedColor",
        "",
        "key",
        "onAttachedToWindow",
        "onDetachedFromWindow",
        "onSavedMusicClick",
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
        "SMAP\nNowPlayingCard.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NowPlayingCard.kt\ncom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard\n+ 2 View.kt\nandroidx/core/view/ViewKt\n+ 3 BitmapDrawable.kt\nandroidx/core/graphics/drawable/BitmapDrawableKt\n+ 4 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,473:1\n257#2,2:474\n255#2:476\n257#2,2:477\n27#3:479\n29#4:480\n*S KotlinDebug\n*F\n+ 1 NowPlayingCard.kt\ncom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard\n*L\n261#1:474,2\n263#1:476\n290#1:477,2\n329#1:479\n370#1:480\n*E\n"
    }
.end annotation


# static fields
.field private static final Companion:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$Companion;


# instance fields
.field private final albumView:Lorg/telegram/ui/ActionBar/SimpleTextView;

.field private final artistView:Lorg/telegram/ui/ActionBar/SimpleTextView;

.field private final audioFocusChangeListener:Landroid/media/AudioManager$OnAudioFocusChangeListener;

.field private audioFocusRequest:Landroid/media/AudioFocusRequest;

.field private final audioManager:Landroid/media/AudioManager;

.field private final cardLayout:Landroid/widget/FrameLayout;

.field private currentDocId:J

.field private currentPreviewUrl:Ljava/lang/String;

.field private final emoji:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

.field private final imageView:Lorg/telegram/ui/Components/BackupImageView;

.field private isPlaying:Z

.field private final nameView:Lorg/telegram/ui/ActionBar/SimpleTextView;

.field private nowPlayingCardData:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;

.field private final playPauseButton:Landroid/widget/ImageView;

.field private playPauseDrawable:Lorg/telegram/ui/Components/PlayPauseDrawable;

.field private player:Landroidx/media3/exoplayer/ExoPlayer;

.field private final resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

.field private resumeOnFocusGain:Z


# direct methods
.method public static $r8$lambda$1BAf14SAIk8s4HkHdGaX6glplHA(Lcom/exteragram/messenger/api/dto/NowPlayingDTO;Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;Landroid/widget/FrameLayout;Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;Landroid/view/View;)Z
    .locals 0

    .line 319
    invoke-virtual {p0}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getPlatform()Ljava/lang/String;

    move-result-object p0

    const-string p4, "TELEGRAM"

    invoke-static {p0, p4}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 320
    invoke-virtual {p1}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->onSavedMusicClick()V

    goto :goto_0

    .line 322
    :cond_0
    invoke-virtual {p2}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p3}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getNowPlayingDTO()Lcom/exteragram/messenger/api/dto/NowPlayingDTO;

    move-result-object p1

    invoke-virtual {p1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getSongUrl()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public static $r8$lambda$FzMm4OZnNI9aFq2UwuvuSO_aGjo(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;Landroid/view/View;)V
    .locals 0

    .line 228
    invoke-direct {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->togglePlayPause()V

    return-void
.end method

.method public static $r8$lambda$Hrhdg_VFnUTPkcpH4kxy4QConTU(Lcom/exteragram/messenger/api/dto/NowPlayingDTO;Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;ZLandroid/widget/FrameLayout;Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;Landroid/view/View;)V
    .locals 0

    .line 307
    invoke-virtual {p0}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getPlatform()Ljava/lang/String;

    move-result-object p0

    const-string p5, "TELEGRAM"

    invoke-static {p0, p5}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 308
    invoke-virtual {p1}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->onSavedMusicClick()V

    return-void

    .line 310
    :cond_0
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object p0

    if-eqz p2, :cond_1

    .line 312
    invoke-virtual {p3}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p4}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getNowPlayingDTO()Lcom/exteragram/messenger/api/dto/NowPlayingDTO;

    move-result-object p1

    invoke-virtual {p1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getSongUrl()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    return-void

    :cond_1
    if-eqz p0, :cond_2

    .line 314
    invoke-static {p0}, Lcom/exteragram/messenger/components/SupporterBottomSheet;->showAlert(Lorg/telegram/ui/ActionBar/BaseFragment;)Lcom/exteragram/messenger/components/SupporterBottomSheet;

    :cond_2
    return-void
.end method

.method public static $r8$lambda$s9QIjAj9D55Esk_1M7EGE-0vK2g(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;I)V
    .locals 3

    const/4 v0, -0x3

    const/4 v1, 0x1

    if-eq p1, v0, :cond_3

    const/4 v0, -0x2

    if-eq p1, v0, :cond_3

    const/4 v0, -0x1

    const/4 v2, 0x0

    if-eq p1, v0, :cond_2

    if-eq p1, v1, :cond_0

    goto :goto_0

    .line 96
    :cond_0
    iget-boolean p1, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->resumeOnFocusGain:Z

    if-eqz p1, :cond_4

    .line 97
    iget-object p1, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p1, :cond_1

    invoke-interface {p1}, Landroidx/media3/common/Player;->play()V

    .line 98
    :cond_1
    iput-boolean v2, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->resumeOnFocusGain:Z

    return-void

    .line 86
    :cond_2
    iput-boolean v2, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->resumeOnFocusGain:Z

    .line 87
    iget-object p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_4

    invoke-interface {p0}, Landroidx/media3/common/Player;->pause()V

    return-void

    .line 90
    :cond_3
    iget-object p1, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p1, :cond_4

    invoke-interface {p1}, Landroidx/media3/common/Player;->isPlaying()Z

    move-result p1

    if-ne p1, v1, :cond_4

    .line 91
    iput-boolean v1, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->resumeOnFocusGain:Z

    .line 92
    iget-object p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_4

    invoke-interface {p0}, Landroidx/media3/common/Player;->pause()V

    :cond_4
    :goto_0
    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->Companion:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$Companion;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 17

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    .line 65
    invoke-direct/range {p0 .. p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    move-object/from16 v2, p2

    .line 64
    iput-object v2, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 80
    const-string v2, "audio"

    invoke-virtual {v1, v2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/media/AudioManager;

    iput-object v2, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioManager:Landroid/media/AudioManager;

    .line 83
    new-instance v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$$ExternalSyntheticLambda2;

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)V

    iput-object v2, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioFocusChangeListener:Landroid/media/AudioManager$OnAudioFocusChangeListener;

    const-wide/16 v2, -0x1

    .line 105
    iput-wide v2, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->currentDocId:J

    const/4 v2, 0x0

    .line 108
    invoke-virtual {v0, v2}, Landroid/view/View;->setClickable(Z)V

    .line 109
    invoke-virtual {v0, v2}, Landroid/view/View;->setWillNotDraw(Z)V

    .line 111
    new-instance v3, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$1;

    invoke-direct {v3, v1, v0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$1;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)V

    .line 125
    new-instance v4, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$2$drawable$1;

    invoke-direct {v4}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$2$drawable$1;-><init>()V

    .line 131
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSectionRadiusDp()I

    move-result v5

    int-to-float v5, v5

    invoke-static {v5}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v5

    invoke-virtual {v4, v5}, Landroid/graphics/drawable/GradientDrawable;->setCornerRadius(F)V

    .line 133
    invoke-virtual {v3, v4}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    const/4 v5, 0x1

    .line 134
    invoke-virtual {v3, v5}, Landroid/view/View;->setClipToOutline(Z)V

    .line 135
    invoke-static {v4}, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->fromDrawable(Landroid/graphics/drawable/Drawable;)Landroid/view/ViewOutlineProvider;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/view/View;->setOutlineProvider(Landroid/view/ViewOutlineProvider;)V

    .line 136
    invoke-virtual {v3, v5}, Landroid/view/View;->setClickable(Z)V

    const v4, 0x3d0f5c29    # 0.035f

    const/high16 v6, 0x3fc00000    # 1.5f

    .line 137
    invoke-static {v3, v4, v6}, Lorg/telegram/ui/Components/ScaleStateListAnimator;->apply(Landroid/view/View;FF)V

    .line 111
    iput-object v3, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->cardLayout:Landroid/widget/FrameLayout;

    const/high16 v4, -0x40000000    # -2.0f

    const/4 v6, -0x1

    .line 141
    invoke-static {v6, v4}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v4

    .line 139
    invoke-virtual {v0, v3, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 144
    new-instance v4, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    const/high16 v7, 0x41a00000    # 20.0f

    .line 145
    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    const/16 v8, 0xd

    .line 144
    invoke-direct {v4, v3, v2, v7, v8}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;-><init>(Landroid/view/View;ZII)V

    iput-object v4, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->emoji:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    .line 148
    new-instance v4, Landroid/widget/LinearLayout;

    invoke-direct {v4, v1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 149
    invoke-virtual {v4, v2}, Landroid/widget/LinearLayout;->setOrientation(I)V

    const/16 v12, 0xc

    const/16 v13, 0xc

    const/4 v7, -0x1

    const/4 v8, -0x2

    const/16 v9, 0x77

    const/16 v10, 0xc

    const/16 v11, 0xc

    .line 151
    invoke-static/range {v7 .. v13}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v7

    .line 150
    invoke-virtual {v3, v4, v7}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 162
    new-instance v3, Lorg/telegram/ui/Components/BackupImageView;

    invoke-direct {v3, v1}, Lorg/telegram/ui/Components/BackupImageView;-><init>(Landroid/content/Context;)V

    .line 163
    invoke-virtual {v3, v5}, Landroid/view/View;->setClipToOutline(Z)V

    .line 164
    invoke-direct {v0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->getCoverCornerRadius()F

    move-result v7

    invoke-static {v7}, Lorg/telegram/messenger/utils/ViewOutlineProviderImpl;->boundsWithRoundRect(F)Landroid/view/ViewOutlineProvider;

    move-result-object v7

    invoke-virtual {v3, v7}, Landroid/view/View;->setOutlineProvider(Landroid/view/ViewOutlineProvider;)V

    .line 162
    iput-object v3, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->imageView:Lorg/telegram/ui/Components/BackupImageView;

    const/4 v14, 0x0

    const/16 v8, 0x40

    const/16 v9, 0x40

    const/16 v10, 0x13

    const/4 v11, 0x0

    const/4 v12, 0x0

    .line 167
    invoke-static/range {v8 .. v14}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v7

    .line 166
    invoke-virtual {v4, v3, v7}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 170
    new-instance v3, Landroid/widget/LinearLayout;

    invoke-direct {v3, v1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 171
    invoke-virtual {v3, v5}, Landroid/widget/LinearLayout;->setOrientation(I)V

    const/4 v5, -0x2

    const/high16 v7, 0x3f800000    # 1.0f

    const/16 v8, 0x10

    .line 174
    invoke-static {v2, v5, v7, v8}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFI)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v5

    .line 173
    invoke-virtual {v4, v3, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 179
    new-instance v5, Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-direct {v5, v1}, Lorg/telegram/ui/ActionBar/SimpleTextView;-><init>(Landroid/content/Context;)V

    const/16 v7, 0x13

    .line 180
    invoke-virtual {v5, v7}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setGravity(I)V

    .line 181
    invoke-virtual {v5, v6}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    .line 182
    invoke-virtual {v5, v8}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextSize(I)V

    .line 183
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v9

    invoke-virtual {v5, v9}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 184
    invoke-static {v5}, Lorg/telegram/messenger/NotificationCenter;->listenEmojiLoading(Landroid/view/View;)V

    .line 179
    iput-object v5, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nameView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    const/16 v9, 0x16

    .line 188
    invoke-static {v6, v9}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(II)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v9

    .line 186
    invoke-virtual {v3, v5, v9}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 191
    new-instance v5, Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-direct {v5, v1}, Lorg/telegram/ui/ActionBar/SimpleTextView;-><init>(Landroid/content/Context;)V

    .line 192
    invoke-virtual {v5, v7}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setGravity(I)V

    .line 193
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->regular()Landroid/graphics/Typeface;

    move-result-object v9

    invoke-virtual {v5, v9}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTypeface(Landroid/graphics/Typeface;)V

    const/16 v9, 0xe

    .line 194
    invoke-virtual {v5, v9}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextSize(I)V

    .line 195
    invoke-virtual {v5, v6}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    const v10, 0x3f19999a    # 0.6f

    .line 196
    invoke-virtual {v5, v10}, Landroid/view/View;->setAlpha(F)V

    .line 197
    invoke-static {v5}, Lorg/telegram/messenger/NotificationCenter;->listenEmojiLoading(Landroid/view/View;)V

    .line 191
    iput-object v5, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->artistView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/4 v11, -0x1

    const/16 v12, 0x13

    const/4 v13, 0x0

    const/high16 v14, 0x40000000    # 2.0f

    .line 200
    invoke-static/range {v11 .. v16}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v11

    .line 199
    invoke-virtual {v3, v5, v11}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 205
    new-instance v5, Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-direct {v5, v1}, Lorg/telegram/ui/ActionBar/SimpleTextView;-><init>(Landroid/content/Context;)V

    .line 206
    invoke-virtual {v5, v7}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setGravity(I)V

    .line 207
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->regular()Landroid/graphics/Typeface;

    move-result-object v7

    invoke-virtual {v5, v7}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 208
    invoke-virtual {v5, v9}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextSize(I)V

    .line 209
    invoke-virtual {v5, v6}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    .line 210
    invoke-virtual {v5, v10}, Landroid/view/View;->setAlpha(F)V

    .line 211
    invoke-static {v5}, Lorg/telegram/messenger/NotificationCenter;->listenEmojiLoading(Landroid/view/View;)V

    .line 205
    iput-object v5, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->albumView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    const/4 v11, -0x1

    .line 214
    invoke-static/range {v11 .. v16}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIFFFF)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v7

    .line 213
    invoke-virtual {v3, v5, v7}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 219
    new-instance v3, Lorg/telegram/ui/Components/PlayPauseDrawable;

    invoke-direct {v3, v8}, Lorg/telegram/ui/Components/PlayPauseDrawable;-><init>(I)V

    .line 220
    invoke-virtual {v3, v2}, Lorg/telegram/ui/Components/PlayPauseDrawable;->setPause(Z)V

    .line 221
    invoke-virtual {v3, v6}, Lorg/telegram/ui/Components/PlayPauseDrawable;->setColor(I)V

    .line 219
    iput-object v3, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->playPauseDrawable:Lorg/telegram/ui/Components/PlayPauseDrawable;

    .line 223
    new-instance v2, Landroid/widget/ImageView;

    invoke-direct {v2, v1}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 224
    invoke-static {v2}, Lorg/telegram/ui/Components/ScaleStateListAnimator;->apply(Landroid/view/View;)V

    .line 225
    sget-object v1, Landroid/widget/ImageView$ScaleType;->CENTER:Landroid/widget/ImageView$ScaleType;

    invoke-virtual {v2, v1}, Landroid/widget/ImageView;->setScaleType(Landroid/widget/ImageView$ScaleType;)V

    .line 226
    iget-object v1, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->playPauseDrawable:Lorg/telegram/ui/Components/PlayPauseDrawable;

    invoke-virtual {v2, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 227
    new-instance v1, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$$ExternalSyntheticLambda3;

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)V

    invoke-virtual {v2, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 223
    iput-object v2, v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->playPauseButton:Landroid/widget/ImageView;

    const/16 v10, 0x8

    const/4 v11, 0x0

    const/16 v5, 0x20

    const/16 v6, 0x20

    const/16 v7, 0x10

    const/16 v8, 0x8

    const/4 v9, 0x0

    .line 232
    invoke-static/range {v5 .. v11}, Lorg/telegram/ui/Components/LayoutHelper;->createLinear(IIIIIII)Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v0

    .line 231
    invoke-virtual {v4, v2, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    return-void
.end method

.method private final abandonAudioFocus()V
    .locals 2

    .line 429
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1a

    if-lt v0, v1, :cond_1

    .line 430
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioFocusRequest:Landroid/media/AudioFocusRequest;

    if-eqz v0, :cond_0

    .line 431
    iget-object v1, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioManager:Landroid/media/AudioManager;

    invoke-virtual {v1, v0}, Landroid/media/AudioManager;->abandonAudioFocusRequest(Landroid/media/AudioFocusRequest;)I

    const/4 v0, 0x0

    .line 432
    iput-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioFocusRequest:Landroid/media/AudioFocusRequest;

    :cond_0
    return-void

    .line 435
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioManager:Landroid/media/AudioManager;

    iget-object p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioFocusChangeListener:Landroid/media/AudioManager$OnAudioFocusChangeListener;

    invoke-virtual {v0, p0}, Landroid/media/AudioManager;->abandonAudioFocus(Landroid/media/AudioManager$OnAudioFocusChangeListener;)I

    return-void
.end method

.method public static final synthetic access$abandonAudioFocus(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)V
    .locals 0

    .line 61
    invoke-direct {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->abandonAudioFocus()V

    return-void
.end method

.method public static final synthetic access$getEmoji$p(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;
    .locals 0

    .line 61
    iget-object p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->emoji:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    return-object p0
.end method

.method public static final synthetic access$getNowPlayingCardData$p(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;
    .locals 0

    .line 61
    iget-object p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nowPlayingCardData:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;

    return-object p0
.end method

.method public static final synthetic access$getThemedColor(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;I)I
    .locals 0

    .line 61
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->getThemedColor(I)I

    move-result p0

    return p0
.end method

.method public static final synthetic access$setPlaying$p(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;Z)V
    .locals 0

    .line 61
    iput-boolean p1, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->isPlaying:Z

    return-void
.end method

.method public static final synthetic access$updatePlayPauseButton(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)V
    .locals 0

    .line 61
    invoke-direct {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->updatePlayPauseButton()V

    return-void
.end method

.method private final getCoverCornerRadius()F
    .locals 1

    .line 241
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSectionRadiusDp()I

    move-result p0

    add-int/lit8 p0, p0, -0xc

    const/16 v0, 0x8

    invoke-static {p0, v0}, Lkotlin/ranges/RangesKt;->coerceAtLeast(II)I

    move-result p0

    int-to-float p0, p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result p0

    return p0
.end method

.method private final getThemedColor(I)I
    .locals 0

    .line 451
    iget-object p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {p1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0
.end method

.method private final initializePlayer()V
    .locals 4

    .line 364
    invoke-direct {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->releasePlayer()V

    .line 365
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nowPlayingCardData:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :cond_0
    invoke-virtual {v0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getNowPlayingDTO()Lcom/exteragram/messenger/api/dto/NowPlayingDTO;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getPreviewUrl()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_1

    return-void

    .line 367
    :cond_1
    new-instance v1, Landroidx/media3/exoplayer/ExoPlayer$Builder;

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v1, v2}, Landroidx/media3/exoplayer/ExoPlayer$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {v1}, Landroidx/media3/exoplayer/ExoPlayer$Builder;->build()Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object v1

    .line 368
    new-instance v2, Landroidx/media3/datasource/DefaultDataSource$Factory;

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v3

    invoke-direct {v2, v3}, Landroidx/media3/datasource/DefaultDataSource$Factory;-><init>(Landroid/content/Context;)V

    .line 369
    new-instance v3, Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;

    invoke-direct {v3, v2}, Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;-><init>(Landroidx/media3/datasource/DataSource$Factory;)V

    .line 29
    invoke-static {v0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    .line 370
    invoke-static {v0}, Landroidx/media3/common/MediaItem;->fromUri(Landroid/net/Uri;)Landroidx/media3/common/MediaItem;

    move-result-object v0

    invoke-virtual {v3, v0}, Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;->createMediaSource(Landroidx/media3/common/MediaItem;)Landroidx/media3/exoplayer/source/ProgressiveMediaSource;

    move-result-object v0

    .line 371
    invoke-interface {v1, v0}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;)V

    .line 372
    invoke-interface {v1}, Landroidx/media3/common/Player;->prepare()V

    .line 373
    new-instance v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$initializePlayer$1$1;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$initializePlayer$1$1;-><init>(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)V

    invoke-interface {v1, v0}, Landroidx/media3/common/Player;->addListener(Landroidx/media3/common/Player$Listener;)V

    .line 367
    iput-object v1, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->player:Landroidx/media3/exoplayer/ExoPlayer;

    return-void
.end method

.method private final releasePlayer()V
    .locals 1

    .line 444
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Landroidx/media3/exoplayer/ExoPlayer;->release()V

    :cond_0
    const/4 v0, 0x0

    .line 445
    iput-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/4 v0, 0x0

    .line 446
    iput-boolean v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->isPlaying:Z

    .line 447
    invoke-direct {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->updatePlayPauseButton()V

    .line 448
    invoke-direct {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->abandonAudioFocus()V

    return-void
.end method

.method private final requestAudioFocus()Z
    .locals 5

    .line 408
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1a

    const/4 v2, 0x0

    const/4 v3, 0x2

    const/4 v4, 0x1

    if-lt v0, v1, :cond_1

    .line 409
    new-instance v0, Landroid/media/AudioAttributes$Builder;

    invoke-direct {v0}, Landroid/media/AudioAttributes$Builder;-><init>()V

    invoke-virtual {v0, v4}, Landroid/media/AudioAttributes$Builder;->setUsage(I)Landroid/media/AudioAttributes$Builder;

    move-result-object v0

    .line 410
    invoke-virtual {v0, v3}, Landroid/media/AudioAttributes$Builder;->setContentType(I)Landroid/media/AudioAttributes$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/media/AudioAttributes$Builder;->build()Landroid/media/AudioAttributes;

    move-result-object v0

    .line 412
    invoke-static {v3}, Landroidx/media3/common/audio/AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0;->m(I)Landroid/media/AudioFocusRequest$Builder;

    move-result-object v1

    .line 413
    invoke-virtual {v1, v0}, Landroid/media/AudioFocusRequest$Builder;->setAudioAttributes(Landroid/media/AudioAttributes;)Landroid/media/AudioFocusRequest$Builder;

    move-result-object v0

    .line 414
    iget-object v1, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioFocusChangeListener:Landroid/media/AudioManager$OnAudioFocusChangeListener;

    invoke-virtual {v0, v1}, Landroid/media/AudioFocusRequest$Builder;->setOnAudioFocusChangeListener(Landroid/media/AudioManager$OnAudioFocusChangeListener;)Landroid/media/AudioFocusRequest$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/media/AudioFocusRequest$Builder;->build()Landroid/media/AudioFocusRequest;

    move-result-object v0

    .line 412
    iput-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioFocusRequest:Landroid/media/AudioFocusRequest;

    .line 416
    iget-object p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioManager:Landroid/media/AudioManager;

    invoke-virtual {p0, v0}, Landroid/media/AudioManager;->requestAudioFocus(Landroid/media/AudioFocusRequest;)I

    move-result p0

    if-ne p0, v4, :cond_0

    return v4

    :cond_0
    return v2

    .line 419
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioManager:Landroid/media/AudioManager;

    .line 420
    iget-object p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->audioFocusChangeListener:Landroid/media/AudioManager$OnAudioFocusChangeListener;

    const/4 v1, 0x3

    .line 419
    invoke-virtual {v0, p0, v1, v3}, Landroid/media/AudioManager;->requestAudioFocus(Landroid/media/AudioManager$OnAudioFocusChangeListener;II)I

    move-result p0

    if-ne p0, v4, :cond_2

    return v4

    :cond_2
    return v2
.end method

.method private final togglePlayPause()V
    .locals 3

    .line 392
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v0, :cond_0

    goto :goto_0

    .line 394
    :cond_0
    invoke-interface {v0}, Landroidx/media3/common/Player;->isPlaying()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 395
    invoke-interface {v0}, Landroidx/media3/common/Player;->pause()V

    .line 396
    invoke-direct {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->abandonAudioFocus()V

    return-void

    .line 398
    :cond_1
    invoke-direct {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->requestAudioFocus()Z

    move-result p0

    if-eqz p0, :cond_3

    .line 399
    invoke-interface {v0}, Landroidx/media3/common/Player;->getPlaybackState()I

    move-result p0

    const/4 v1, 0x4

    if-ne p0, v1, :cond_2

    const-wide/16 v1, 0x0

    .line 400
    invoke-interface {v0, v1, v2}, Landroidx/media3/common/Player;->seekTo(J)V

    .line 402
    :cond_2
    invoke-interface {v0}, Landroidx/media3/common/Player;->play()V

    :cond_3
    :goto_0
    return-void
.end method

.method private final updatePlayPauseButton()V
    .locals 1

    .line 440
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->playPauseDrawable:Lorg/telegram/ui/Components/PlayPauseDrawable;

    iget-boolean p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->isPlaying:Z

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/PlayPauseDrawable;->setPause(Z)V

    return-void
.end method


# virtual methods
.method public onAttachedToWindow()V
    .locals 1

    .line 454
    invoke-super {p0}, Landroid/view/View;->onAttachedToWindow()V

    .line 455
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->emoji:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;->attach()V

    .line 456
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nowPlayingCardData:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;

    if-eqz v0, :cond_0

    .line 457
    invoke-direct {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->initializePlayer()V

    :cond_0
    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 1

    .line 462
    invoke-super {p0}, Landroid/view/View;->onDetachedFromWindow()V

    .line 463
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->emoji:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;->detach()V

    .line 464
    invoke-direct {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->releasePlayer()V

    return-void
.end method

.method public abstract onSavedMusicClick()V
.end method

.method public final set(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;)V
    .locals 16

    move-object/from16 v2, p0

    move-object/from16 v5, p1

    .line 245
    iput-object v5, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nowPlayingCardData:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;

    .line 246
    invoke-virtual {v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getNowPlayingDTO()Lcom/exteragram/messenger/api/dto/NowPlayingDTO;

    move-result-object v1

    .line 247
    invoke-virtual {v1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getArtists()Ljava/util/List;

    move-result-object v0

    .line 249
    iget-object v3, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->artistView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    const/4 v6, 0x0

    invoke-virtual {v3, v6}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setText(Ljava/lang/CharSequence;)Z

    .line 250
    iget-object v3, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nameView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v3, v6}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setText(Ljava/lang/CharSequence;)Z

    .line 251
    iget-object v3, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->albumView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v3, v6}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setText(Ljava/lang/CharSequence;)Z

    .line 253
    move-object v3, v0

    check-cast v3, Ljava/util/Collection;

    if-eqz v3, :cond_1

    invoke-interface {v3}, Ljava/util/Collection;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_0

    goto :goto_0

    .line 256
    :cond_0
    iget-object v3, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->artistView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    move-object v7, v0

    check-cast v7, Ljava/lang/Iterable;

    const/16 v14, 0x3e

    const/4 v15, 0x0

    const-string v8, ", "

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    invoke-static/range {v7 .. v15}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setText(Ljava/lang/CharSequence;)Z

    goto :goto_1

    .line 254
    :cond_1
    :goto_0
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->artistView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    sget v3, Lorg/telegram/messenger/R$string;->AudioUnknownArtist:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setText(Ljava/lang/CharSequence;)Z

    .line 258
    :goto_1
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nameView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    .line 259
    invoke-virtual {v1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getTrackName()Ljava/lang/String;

    move-result-object v3

    iget-object v4, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nameView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v4}, Lorg/telegram/ui/ActionBar/SimpleTextView;->getPaint()Lcom/exteragram/messenger/utils/ui/TextPaint;

    move-result-object v4

    invoke-virtual {v4}, Landroid/graphics/Paint;->getFontMetricsInt()Landroid/graphics/Paint$FontMetricsInt;

    move-result-object v4

    const/4 v7, 0x0

    invoke-static {v3, v4, v7}, Lorg/telegram/messenger/Emoji;->replaceEmoji(Ljava/lang/CharSequence;Landroid/graphics/Paint$FontMetricsInt;Z)Ljava/lang/CharSequence;

    move-result-object v3

    .line 258
    invoke-virtual {v0, v3}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setText(Ljava/lang/CharSequence;)Z

    .line 261
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->albumView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    .line 262
    invoke-virtual {v1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getAlbumName()Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x1

    if-eqz v3, :cond_3

    invoke-interface {v3}, Ljava/lang/CharSequence;->length()I

    move-result v3

    if-nez v3, :cond_2

    goto :goto_2

    :cond_2
    invoke-virtual {v1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getTrackName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getAlbumName()Ljava/lang/String;

    move-result-object v8

    invoke-static {v3, v8}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_3

    move v3, v4

    goto :goto_3

    :cond_3
    :goto_2
    move v3, v7

    :goto_3
    const/16 v8, 0x8

    if-eqz v3, :cond_4

    move v3, v7

    goto :goto_4

    :cond_4
    move v3, v8

    .line 257
    :goto_4
    invoke-virtual {v0, v3}, Landroid/view/View;->setVisibility(I)V

    .line 263
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->albumView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    .line 255
    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_5

    .line 264
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->albumView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    .line 265
    invoke-virtual {v1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getAlbumName()Ljava/lang/String;

    move-result-object v3

    iget-object v9, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->albumView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v9}, Lorg/telegram/ui/ActionBar/SimpleTextView;->getPaint()Lcom/exteragram/messenger/utils/ui/TextPaint;

    move-result-object v9

    invoke-virtual {v9}, Landroid/graphics/Paint;->getFontMetricsInt()Landroid/graphics/Paint$FontMetricsInt;

    move-result-object v9

    invoke-static {v3, v9, v7}, Lorg/telegram/messenger/Emoji;->replaceEmoji(Ljava/lang/CharSequence;Landroid/graphics/Paint$FontMetricsInt;Z)Ljava/lang/CharSequence;

    move-result-object v3

    .line 264
    invoke-virtual {v0, v3}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setText(Ljava/lang/CharSequence;)Z

    :cond_5
    const/high16 v0, 0x41400000    # 12.0f

    .line 268
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    invoke-virtual {v2, v3, v7, v0, v7}, Landroid/view/View;->setPadding(IIII)V

    .line 270
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->cardLayout:Landroid/widget/FrameLayout;

    invoke-virtual {v0}, Landroid/view/View;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 271
    instance-of v3, v0, Landroid/graphics/drawable/GradientDrawable;

    if-eqz v3, :cond_8

    .line 272
    check-cast v0, Landroid/graphics/drawable/GradientDrawable;

    .line 273
    invoke-virtual {v0}, Landroid/graphics/drawable/GradientDrawable;->mutate()Landroid/graphics/drawable/Drawable;

    .line 274
    invoke-virtual {v0, v4}, Landroid/graphics/drawable/GradientDrawable;->setDither(Z)V

    .line 276
    invoke-virtual {v0, v4}, Landroid/graphics/drawable/GradientDrawable;->setGradientType(I)V

    const/high16 v3, 0x3f800000    # 1.0f

    const/high16 v9, 0x3f000000    # 0.5f

    .line 277
    invoke-virtual {v0, v3, v9}, Landroid/graphics/drawable/GradientDrawable;->setGradientCenter(FF)V

    .line 279
    invoke-virtual {v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getBackgroundColor()Ljava/lang/Integer;

    move-result-object v3

    if-eqz v3, :cond_6

    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I

    move-result v3

    :goto_5
    move v10, v3

    goto :goto_6

    :cond_6
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-direct {v2, v3}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->getThemedColor(I)I

    move-result v3

    goto :goto_5

    .line 282
    :goto_6
    invoke-virtual {v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getBackgroundColor()Ljava/lang/Integer;

    move-result-object v3

    if-eqz v3, :cond_7

    sget-object v9, Lcom/exteragram/messenger/utils/ui/UIUtil;->INSTANCE:Lcom/exteragram/messenger/utils/ui/UIUtil;

    const/4 v13, 0x4

    const/4 v14, 0x0

    const/high16 v11, 0x3fc00000    # 1.5f

    const/4 v12, 0x0

    invoke-static/range {v9 .. v14}, Lcom/exteragram/messenger/utils/ui/UIUtil;->adjustHsl$default(Lcom/exteragram/messenger/utils/ui/UIUtil;IFFILjava/lang/Object;)I

    move-result v3

    goto :goto_7

    :cond_7
    move v3, v10

    .line 285
    :goto_7
    filled-new-array {v10, v3}, [I

    move-result-object v3

    .line 280
    invoke-virtual {v0, v3}, Landroid/graphics/drawable/GradientDrawable;->setColors([I)V

    .line 290
    :cond_8
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->playPauseButton:Landroid/widget/ImageView;

    invoke-virtual {v1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getPreviewUrl()Ljava/lang/String;

    move-result-object v3

    const-string v9, "TELEGRAM"

    if-eqz v3, :cond_a

    invoke-interface {v3}, Ljava/lang/CharSequence;->length()I

    move-result v3

    if-nez v3, :cond_9

    goto :goto_8

    :cond_9
    invoke-virtual {v1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getPlatform()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3, v9}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_a

    move v3, v4

    goto :goto_9

    :cond_a
    :goto_8
    move v3, v7

    :goto_9
    if-eqz v3, :cond_b

    goto :goto_a

    :cond_b
    move v7, v8

    .line 257
    :goto_a
    invoke-virtual {v0, v7}, Landroid/view/View;->setVisibility(I)V

    .line 291
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->playPauseButton:Landroid/widget/ImageView;

    const/high16 v3, 0x42000000    # 32.0f

    .line 292
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    iget-object v7, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nowPlayingCardData:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;

    if-nez v7, :cond_c

    move-object v7, v6

    :cond_c
    invoke-virtual {v7}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getAccentColor()Ljava/lang/Integer;

    move-result-object v7

    if-eqz v7, :cond_d

    invoke-virtual {v7}, Ljava/lang/Integer;->intValue()I

    move-result v7

    goto :goto_b

    :cond_d
    sget v7, Lorg/telegram/ui/ActionBar/Theme;->key_featuredStickers_addButton:I

    invoke-direct {v2, v7}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->getThemedColor(I)I

    move-result v7

    .line 291
    :goto_b
    invoke-static {v3, v7}, Lorg/telegram/ui/ActionBar/Theme;->createCircleDrawable(II)Landroid/graphics/drawable/ShapeDrawable;

    move-result-object v3

    invoke-virtual {v0, v3}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 296
    invoke-virtual {v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getUserEmoji()J

    move-result-wide v7

    const-wide/16 v10, 0x0

    cmp-long v0, v7, v10

    if-lez v0, :cond_e

    invoke-virtual {v1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getPlatform()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, v9}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_e

    invoke-virtual {v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getUserEmoji()J

    move-result-wide v7

    goto :goto_c

    :cond_e
    sget-object v0, Lcom/exteragram/messenger/nowplaying/ServiceEmoji;->Companion:Lcom/exteragram/messenger/nowplaying/ServiceEmoji$Companion;

    .line 297
    invoke-virtual {v1}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getPlatform()Ljava/lang/String;

    move-result-object v3

    .line 296
    invoke-virtual {v0, v3}, Lcom/exteragram/messenger/nowplaying/ServiceEmoji$Companion;->fromString(Ljava/lang/String;)Lcom/exteragram/messenger/nowplaying/ServiceEmoji;

    move-result-object v0

    .line 298
    invoke-virtual {v0}, Lcom/exteragram/messenger/nowplaying/ServiceEmoji;->getDocumentId()J

    move-result-wide v7

    .line 299
    :goto_c
    iget-wide v9, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->currentDocId:J

    cmp-long v0, v7, v9

    if-eqz v0, :cond_f

    .line 300
    iput-wide v7, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->currentDocId:J

    .line 301
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->emoji:Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;

    invoke-virtual {v0, v7, v8, v4}, Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;->set(JZ)Z

    .line 304
    :cond_f
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->cardLayout:Landroid/widget/FrameLayout;

    .line 305
    sget-object v3, Lcom/exteragram/messenger/badges/BadgesController;->INSTANCE:Lcom/exteragram/messenger/badges/BadgesController;

    invoke-static {v3, v6, v4, v6}, Lcom/exteragram/messenger/badges/BadgesController;->hasBadge$default(Lcom/exteragram/messenger/badges/BadgesController;Lorg/telegram/tgnet/TLObject;ILjava/lang/Object;)Z

    move-result v3

    move-object v4, v0

    .line 306
    new-instance v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$$ExternalSyntheticLambda0;

    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/api/dto/NowPlayingDTO;Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;ZLandroid/widget/FrameLayout;Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;)V

    invoke-virtual {v4, v0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 318
    new-instance v0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$$ExternalSyntheticLambda1;

    invoke-direct {v0, v1, v2, v4, v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/api/dto/NowPlayingDTO;Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;Landroid/widget/FrameLayout;Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;)V

    invoke-virtual {v4, v0}, Landroid/view/View;->setOnLongClickListener(Landroid/view/View$OnLongClickListener;)V

    .line 328
    invoke-virtual {v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getImageLocation()Lorg/telegram/messenger/ImageLocation;

    move-result-object v0

    if-eqz v0, :cond_12

    .line 329
    invoke-virtual {v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getCoverBitmap()Landroid/graphics/Bitmap;

    move-result-object v0

    if-eqz v0, :cond_10

    invoke-virtual {v2}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 27
    new-instance v6, Landroid/graphics/drawable/BitmapDrawable;

    invoke-direct {v6, v1, v0}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    :cond_10
    move-object v10, v6

    .line 330
    iget-object v7, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->imageView:Lorg/telegram/ui/Components/BackupImageView;

    invoke-virtual {v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getImageLocation()Lorg/telegram/messenger/ImageLocation;

    move-result-object v8

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v9, 0x0

    invoke-virtual/range {v7 .. v12}, Lorg/telegram/ui/Components/BackupImageView;->setImage(Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Landroid/graphics/drawable/Drawable;ILjava/lang/Object;)V

    .line 332
    invoke-virtual {v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getCoverBitmap()Landroid/graphics/Bitmap;

    move-result-object v0

    if-eqz v0, :cond_11

    .line 334
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->artistView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    const/4 v1, -0x1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    .line 335
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nameView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    .line 336
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->albumView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    goto :goto_d

    .line 339
    :cond_11
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->getThemedColor(I)I

    move-result v0

    .line 340
    iget-object v1, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->artistView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v1, v0}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    .line 341
    iget-object v1, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nameView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v1, v0}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    .line 342
    iget-object v1, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->albumView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v1, v0}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    goto :goto_d

    .line 346
    :cond_12
    iget-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->imageView:Lorg/telegram/ui/Components/BackupImageView;

    sget v1, Lorg/telegram/messenger/R$drawable;->nocover:I

    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_player_button:I

    invoke-direct {v2, v3}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->getThemedColor(I)I

    move-result v3

    invoke-virtual {v0, v1, v3}, Lorg/telegram/ui/Components/BackupImageView;->setImageResource(II)V

    .line 347
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->getThemedColor(I)I

    move-result v0

    .line 348
    iget-object v1, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->artistView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v1, v0}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    .line 349
    iget-object v1, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->nameView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v1, v0}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    .line 350
    iget-object v1, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->albumView:Lorg/telegram/ui/ActionBar/SimpleTextView;

    invoke-virtual {v1, v0}, Lorg/telegram/ui/ActionBar/SimpleTextView;->setTextColor(I)V

    .line 354
    :goto_d
    invoke-virtual {v5}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;->getNowPlayingDTO()Lcom/exteragram/messenger/api/dto/NowPlayingDTO;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/api/dto/NowPlayingDTO;->getPreviewUrl()Ljava/lang/String;

    move-result-object v0

    .line 355
    iget-object v1, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->currentPreviewUrl:Ljava/lang/String;

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_13

    .line 356
    iput-object v0, v2, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->currentPreviewUrl:Ljava/lang/String;

    .line 357
    invoke-direct {v2}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->initializePlayer()V

    .line 360
    :cond_13
    invoke-virtual {v2}, Landroid/view/View;->invalidate()V

    return-void
.end method
