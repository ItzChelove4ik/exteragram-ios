.class final Lorg/telegram/utils/proxy/WebProxyTransport$Stream;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/proxy/WebProxyTransport;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Stream"
.end annotation


# instance fields
.field private final id:I

.field private opened:Z

.field private receiveWindow:J

.field private sendWindow:J

.field private final socket:Ljava/net/Socket;


# direct methods
.method public static bridge synthetic -$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->id:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetopened(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->opened:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetreceiveWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->receiveWindow:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetsendWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->sendWindow:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetsocket(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)Ljava/net/Socket;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->socket:Ljava/net/Socket;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fputopened(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->opened:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputreceiveWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->receiveWindow:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputsendWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->sendWindow:J

    return-void
.end method

.method private constructor <init>(ILjava/net/Socket;)V
    .locals 2

    .line 854
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-wide/32 v0, 0x400000

    .line 850
    iput-wide v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->sendWindow:J

    .line 851
    iput-wide v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->receiveWindow:J

    .line 855
    iput p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->id:I

    .line 856
    iput-object p2, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->socket:Ljava/net/Socket;

    return-void
.end method

.method public synthetic constructor <init>(ILjava/net/Socket;Lorg/telegram/utils/proxy/WebProxyTransport-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;-><init>(ILjava/net/Socket;)V

    return-void
.end method
