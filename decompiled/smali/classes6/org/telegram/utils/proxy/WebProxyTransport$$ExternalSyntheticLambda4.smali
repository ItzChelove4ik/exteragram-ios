.class public final synthetic Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda4;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/proxy/WebProxyTransport;

.field public final synthetic f$1:Lorg/telegram/utils/proxy/WebProxyTransport$Stream;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/proxy/WebProxyTransport;Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda4;->f$0:Lorg/telegram/utils/proxy/WebProxyTransport;

    iput-object p2, p0, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda4;->f$1:Lorg/telegram/utils/proxy/WebProxyTransport$Stream;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda4;->f$0:Lorg/telegram/utils/proxy/WebProxyTransport;

    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda4;->f$1:Lorg/telegram/utils/proxy/WebProxyTransport$Stream;

    invoke-static {v0, p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->$r8$lambda$RC9hMGcOexZfSHIimGPiMfX37cc(Lorg/telegram/utils/proxy/WebProxyTransport;Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)V

    return-void
.end method
