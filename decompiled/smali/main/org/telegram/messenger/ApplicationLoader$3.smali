.class Lorg/telegram/messenger/ApplicationLoader$3;
.super Landroid/net/ConnectivityManager$NetworkCallback;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/telegram/messenger/ApplicationLoader;->ensureCurrentNetworkGet(Z)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 536
    invoke-direct {p0}, Landroid/net/ConnectivityManager$NetworkCallback;-><init>()V

    return-void
.end method


# virtual methods
.method public onAvailable(Landroid/net/Network;)V
    .locals 0

    const/4 p0, -0x1

    .line 539
    invoke-static {p0}, Lorg/telegram/messenger/ApplicationLoader;->-$$Nest$sfputlastKnownNetworkType(I)V

    .line 540
    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->checkProxyForNetworkState()V

    return-void
.end method

.method public onCapabilitiesChanged(Landroid/net/Network;Landroid/net/NetworkCapabilities;)V
    .locals 0

    const/4 p0, -0x1

    .line 545
    invoke-static {p0}, Lorg/telegram/messenger/ApplicationLoader;->-$$Nest$sfputlastKnownNetworkType(I)V

    .line 546
    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->checkProxyForNetworkState()V

    return-void
.end method

.method public onLost(Landroid/net/Network;)V
    .locals 0

    const/4 p0, -0x1

    .line 551
    invoke-static {p0}, Lorg/telegram/messenger/ApplicationLoader;->-$$Nest$sfputlastKnownNetworkType(I)V

    .line 552
    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->checkProxyForNetworkState()V

    return-void
.end method
