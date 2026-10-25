.class public final Landroidx/media3/session/legacy/MediaControllerCompat;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImplApi29;,
        Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImpl;,
        Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImplApi21;,
        Landroidx/media3/session/legacy/MediaControllerCompat$Callback;
    }
.end annotation


# instance fields
.field private final impl:Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImpl;

.field private final registeredCallbacks:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Landroidx/media3/session/legacy/MediaControllerCompat$Callback;",
            ">;"
        }
    .end annotation
.end field

.field private final token:Landroidx/media3/session/legacy/MediaSessionCompat$Token;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroidx/media3/session/legacy/MediaSessionCompat$Token;)V
    .locals 2

    .line 168
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 169
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    invoke-static {v0}, Ljava/util/Collections;->synchronizedSet(Ljava/util/Set;)Ljava/util/Set;

    move-result-object v0

    iput-object v0, p0, Landroidx/media3/session/legacy/MediaControllerCompat;->registeredCallbacks:Ljava/util/Set;

    .line 170
    iput-object p2, p0, Landroidx/media3/session/legacy/MediaControllerCompat;->token:Landroidx/media3/session/legacy/MediaSessionCompat$Token;

    .line 172
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1d

    if-lt v0, v1, :cond_0

    .line 173
    new-instance v0, Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImplApi29;

    invoke-direct {v0, p1, p2}, Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImplApi29;-><init>(Landroid/content/Context;Landroidx/media3/session/legacy/MediaSessionCompat$Token;)V

    iput-object v0, p0, Landroidx/media3/session/legacy/MediaControllerCompat;->impl:Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImpl;

    return-void

    .line 175
    :cond_0
    new-instance v0, Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImplApi21;

    invoke-direct {v0, p1, p2}, Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImplApi21;-><init>(Landroid/content/Context;Landroidx/media3/session/legacy/MediaSessionCompat$Token;)V

    iput-object v0, p0, Landroidx/media3/session/legacy/MediaControllerCompat;->impl:Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImpl;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroidx/media3/session/legacy/MediaSessionCompat;)V
    .locals 0

    .line 158
    invoke-virtual {p2}, Landroidx/media3/session/legacy/MediaSessionCompat;->getSessionToken()Landroidx/media3/session/legacy/MediaSessionCompat$Token;

    move-result-object p2

    invoke-direct {p0, p1, p2}, Landroidx/media3/session/legacy/MediaControllerCompat;-><init>(Landroid/content/Context;Landroidx/media3/session/legacy/MediaSessionCompat$Token;)V

    return-void
.end method


# virtual methods
.method public dispatchMediaButtonEvent(Landroid/view/KeyEvent;)Z
    .locals 0

    if-eqz p1, :cond_0

    .line 199
    iget-object p0, p0, Landroidx/media3/session/legacy/MediaControllerCompat;->impl:Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImpl;

    invoke-interface {p0, p1}, Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImpl;->dispatchMediaButtonEvent(Landroid/view/KeyEvent;)Z

    move-result p0

    return p0

    .line 197
    :cond_0
    const-string p0, "KeyEvent may not be null"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return p0
.end method

.method public getQueueTitle()Ljava/lang/CharSequence;
    .locals 0

    .line 317
    iget-object p0, p0, Landroidx/media3/session/legacy/MediaControllerCompat;->impl:Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImpl;

    invoke-interface {p0}, Landroidx/media3/session/legacy/MediaControllerCompat$MediaControllerImpl;->getQueueTitle()Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method
