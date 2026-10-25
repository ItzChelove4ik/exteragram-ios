.class public final Lorg/telegram/utils/proxy/WebProxyConnectionTester;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;,
        Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;
    }
.end annotation


# static fields
.field private static volatile instance:Lorg/telegram/utils/proxy/WebProxyConnectionTester;


# instance fields
.field private currentRequest:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

.field private nativeCheckTimeoutRunnable:Ljava/lang/Runnable;

.field private final queue:Ljava/util/ArrayDeque;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayDeque<",
            "Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;",
            ">;"
        }
    .end annotation
.end field

.field private readyTimeoutRunnable:Ljava/lang/Runnable;


# direct methods
.method public static synthetic $r8$lambda$-r3LDqvZe-Wsch-I7s1_9SRdtqs(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->lambda$processNext$2(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    return-void
.end method

.method public static synthetic $r8$lambda$0Cw-P3fd1jmmL8sphiY2bpzYd7k(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->lambda$onTransportReady$6(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    return-void
.end method

.method public static synthetic $r8$lambda$Btp45qf0GPwxEwFhLVJHl6nDqaQ(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->lambda$onTransportReady$5(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V

    return-void
.end method

.method public static synthetic $r8$lambda$VaftKsXTMeXQish5gmoS-deyXA8(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->lambda$onTransportReady$4(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V

    return-void
.end method

.method public static synthetic $r8$lambda$bXzw5oXp5Pu9iSTPZhLKGU4XBVQ(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->lambda$onTransportReady$3(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    return-void
.end method

.method public static synthetic $r8$lambda$gmLiuQYj6J4B6tAEg3z7-02L4lc(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->lambda$processNext$1(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ufe7vls3QFXLZj8v4zidu7dGV6M(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;Lorg/telegram/utils/proxy/ProxySettings;Lorg/telegram/tgnet/RequestTimeDelegate;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->lambda$checkProxy$0(Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;Lorg/telegram/utils/proxy/ProxySettings;Lorg/telegram/tgnet/RequestTimeDelegate;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 54
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 47
    new-instance v0, Ljava/util/ArrayDeque;

    invoke-direct {v0}, Ljava/util/ArrayDeque;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->queue:Ljava/util/ArrayDeque;

    return-void
.end method

.method private cancelNativeCheckTimeout()V
    .locals 1

    .line 202
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->nativeCheckTimeoutRunnable:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 203
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    const/4 v0, 0x0

    .line 204
    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->nativeCheckTimeoutRunnable:Ljava/lang/Runnable;

    :cond_0
    return-void
.end method

.method private cancelReadyTimeout()V
    .locals 1

    .line 195
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->readyTimeoutRunnable:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 196
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    const/4 v0, 0x0

    .line 197
    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->readyTimeoutRunnable:Ljava/lang/Runnable;

    :cond_0
    return-void
.end method

.method private failCurrent(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V
    .locals 2

    .line 178
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->currentRequest:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    if-eq v0, p1, :cond_0

    return-void

    .line 182
    :cond_0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->cancelReadyTimeout()V

    .line 183
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->cancelNativeCheckTimeout()V

    .line 185
    invoke-static {}, Lorg/telegram/utils/proxy/WebProxyTransport;->stopConnectionCheck()V

    const/4 v0, 0x0

    .line 187
    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->currentRequest:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    .line 189
    iget-object p1, p1, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->delegate:Lorg/telegram/tgnet/RequestTimeDelegate;

    const-wide/16 v0, -0x1

    invoke-interface {p1, v0, v1}, Lorg/telegram/tgnet/RequestTimeDelegate;->run(J)V

    .line 191
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->processNext()V

    return-void
.end method

.method public static getInstance()Lorg/telegram/utils/proxy/WebProxyConnectionTester;
    .locals 2

    .line 16
    sget-object v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->instance:Lorg/telegram/utils/proxy/WebProxyConnectionTester;

    if-nez v0, :cond_1

    .line 18
    const-class v1, Lorg/telegram/utils/proxy/WebProxyConnectionTester;

    monitor-enter v1

    .line 19
    :try_start_0
    sget-object v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->instance:Lorg/telegram/utils/proxy/WebProxyConnectionTester;

    if-nez v0, :cond_0

    .line 21
    new-instance v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;

    invoke-direct {v0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;-><init>()V

    sput-object v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->instance:Lorg/telegram/utils/proxy/WebProxyConnectionTester;

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 23
    :cond_0
    :goto_0
    monitor-exit v1

    return-object v0

    :goto_1
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    :cond_1
    return-object v0
.end method

.method private synthetic lambda$checkProxy$0(Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;Lorg/telegram/utils/proxy/ProxySettings;Lorg/telegram/tgnet/RequestTimeDelegate;)V
    .locals 2

    .line 74
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->queue:Ljava/util/ArrayDeque;

    new-instance v1, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    invoke-direct {v1, p1, p2, p3}, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;-><init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;Lorg/telegram/utils/proxy/ProxySettings;Lorg/telegram/tgnet/RequestTimeDelegate;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayDeque;->addLast(Ljava/lang/Object;)V

    .line 80
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->processNext()V

    return-void
.end method

.method private synthetic lambda$onTransportReady$3(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V
    .locals 1

    .line 137
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->currentRequest:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    if-eq v0, p1, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x0

    .line 141
    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->nativeCheckTimeoutRunnable:Ljava/lang/Runnable;

    .line 143
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->failCurrent(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    return-void
.end method

.method private synthetic lambda$onTransportReady$4(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V
    .locals 0

    .line 155
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->onNativeCheckFinished(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V

    return-void
.end method

.method private synthetic lambda$onTransportReady$5(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V
    .locals 1

    .line 154
    new-instance v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda6;

    invoke-direct {v0, p0, p1, p2, p3}, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$onTransportReady$6(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V
    .locals 4

    .line 124
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->currentRequest:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    if-eq v0, p1, :cond_0

    return-void

    .line 128
    :cond_0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->cancelReadyTimeout()V

    .line 130
    iget v0, p1, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->port:I

    if-nez v0, :cond_1

    .line 132
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->failCurrent(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    return-void

    .line 136
    :cond_1
    new-instance v1, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    iput-object v1, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->nativeCheckTimeoutRunnable:Ljava/lang/Runnable;

    const-wide/16 v2, 0x4e20

    .line 146
    invoke-static {v1, v2, v3}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    .line 151
    iget-object v1, p1, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->testImplementation:Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;

    iget-object v2, p1, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    new-instance v3, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda5;

    invoke-direct {v3, p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    invoke-interface {v1, v2, v0, v3}, Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;->doCheck(Lorg/telegram/utils/proxy/ProxySettings;ILorg/telegram/tgnet/RequestTimeDelegate;)V

    return-void
.end method

.method private synthetic lambda$processNext$1(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V
    .locals 1

    .line 97
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->currentRequest:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    if-eq v0, p1, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x0

    .line 101
    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->readyTimeoutRunnable:Ljava/lang/Runnable;

    .line 103
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->failCurrent(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    return-void
.end method

.method private synthetic lambda$processNext$2(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V
    .locals 0

    .line 114
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->onTransportReady(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    return-void
.end method

.method private onNativeCheckFinished(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V
    .locals 1

    .line 162
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->currentRequest:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    if-eq v0, p1, :cond_0

    return-void

    .line 166
    :cond_0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->cancelNativeCheckTimeout()V

    .line 168
    invoke-static {}, Lorg/telegram/utils/proxy/WebProxyTransport;->stopConnectionCheck()V

    const/4 v0, 0x0

    .line 170
    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->currentRequest:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    .line 172
    iget-object p1, p1, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->delegate:Lorg/telegram/tgnet/RequestTimeDelegate;

    invoke-interface {p1, p2, p3}, Lorg/telegram/tgnet/RequestTimeDelegate;->run(J)V

    .line 174
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->processNext()V

    return-void
.end method

.method private onTransportReady(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V
    .locals 1

    .line 123
    new-instance v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0, p1}, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    const-wide/16 p0, 0x1f4

    invoke-static {v0, p0, p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method

.method private processNext()V
    .locals 4

    .line 85
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->currentRequest:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    if-eqz v0, :cond_0

    goto :goto_0

    .line 89
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->queue:Ljava/util/ArrayDeque;

    invoke-virtual {v0}, Ljava/util/ArrayDeque;->pollFirst()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->currentRequest:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    if-nez v0, :cond_1

    goto :goto_0

    .line 96
    :cond_1
    new-instance v1, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0, v0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    iput-object v1, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->readyTimeoutRunnable:Ljava/lang/Runnable;

    const-wide/16 v2, 0x2710

    .line 106
    invoke-static {v1, v2, v3}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    .line 111
    iget-object v1, v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    .line 112
    invoke-virtual {v1}, Lorg/telegram/utils/proxy/ProxySettings;->getAddress()Ljava/lang/String;

    move-result-object v1

    iget-object v2, v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    .line 113
    invoke-virtual {v2}, Lorg/telegram/utils/proxy/ProxySettings;->getSecret()Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda2;

    invoke-direct {v3, p0, v0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    .line 111
    invoke-static {v1, v2, v3}, Lorg/telegram/utils/proxy/WebProxyTransport;->startConnectionCheck(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/utils/proxy/WebProxyTransport$ReadyCallback;)I

    move-result v1

    iput v1, v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->port:I

    if-nez v1, :cond_2

    .line 118
    invoke-direct {p0, v0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->failCurrent(Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;)V

    :cond_2
    :goto_0
    return-void
.end method


# virtual methods
.method public checkProxy(Lorg/telegram/utils/proxy/ProxySettings;Lorg/telegram/tgnet/RequestTimeDelegate;Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;)V
    .locals 2

    if-eqz p3, :cond_1

    if-eqz p1, :cond_1

    .line 64
    invoke-virtual {p1}, Lorg/telegram/utils/proxy/ProxySettings;->getType()Lorg/telegram/utils/proxy/ProxySettings$Type;

    move-result-object v0

    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-ne v0, v1, :cond_1

    .line 65
    invoke-virtual {p1}, Lorg/telegram/utils/proxy/ProxySettings;->isValid()Z

    move-result v0

    if-eqz v0, :cond_1

    if-nez p2, :cond_0

    goto :goto_0

    .line 73
    :cond_0
    new-instance v0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0, p3, p1, p2}, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;Lorg/telegram/utils/proxy/ProxySettings;Lorg/telegram/tgnet/RequestTimeDelegate;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    :cond_1
    :goto_0
    if-eqz p2, :cond_2

    const-wide/16 p0, -0x1

    .line 68
    invoke-interface {p2, p0, p1}, Lorg/telegram/tgnet/RequestTimeDelegate;->run(J)V

    :cond_2
    return-void
.end method
