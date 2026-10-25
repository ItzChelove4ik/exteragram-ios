.class final Lorg/telegram/utils/proxy/WebProxyTransport$Address;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/proxy/WebProxyTransport;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Address"
.end annotation


# instance fields
.field private final host:Ljava/lang/String;

.field private final path:Ljava/lang/String;

.field private final value:Ljava/lang/String;


# direct methods
.method public static bridge synthetic -$$Nest$fgethost(Lorg/telegram/utils/proxy/WebProxyTransport$Address;)Ljava/lang/String;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Address;->host:Ljava/lang/String;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpath(Lorg/telegram/utils/proxy/WebProxyTransport$Address;)Ljava/lang/String;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Address;->path:Ljava/lang/String;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetvalue(Lorg/telegram/utils/proxy/WebProxyTransport$Address;)Ljava/lang/String;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Address;->value:Ljava/lang/String;

    return-object p0
.end method

.method private constructor <init>(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    .line 865
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 866
    iput-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Address;->host:Ljava/lang/String;

    .line 867
    iput-object p2, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Address;->path:Ljava/lang/String;

    .line 868
    invoke-virtual {p2}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "/"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    :goto_0
    iput-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport$Address;->value:Ljava/lang/String;

    return-void
.end method

.method public synthetic constructor <init>(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/utils/proxy/WebProxyTransport-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/proxy/WebProxyTransport$Address;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method
