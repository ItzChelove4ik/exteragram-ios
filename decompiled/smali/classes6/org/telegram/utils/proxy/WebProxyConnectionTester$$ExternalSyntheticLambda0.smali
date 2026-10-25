.class public final synthetic Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/proxy/WebProxyConnectionTester;

.field public final synthetic f$1:Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;

.field public final synthetic f$2:Lorg/telegram/utils/proxy/ProxySettings;

.field public final synthetic f$3:Lorg/telegram/tgnet/RequestTimeDelegate;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;Lorg/telegram/utils/proxy/ProxySettings;Lorg/telegram/tgnet/RequestTimeDelegate;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;->f$0:Lorg/telegram/utils/proxy/WebProxyConnectionTester;

    iput-object p2, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;->f$1:Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;

    iput-object p3, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;->f$2:Lorg/telegram/utils/proxy/ProxySettings;

    iput-object p4, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;->f$3:Lorg/telegram/tgnet/RequestTimeDelegate;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;->f$0:Lorg/telegram/utils/proxy/WebProxyConnectionTester;

    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;->f$1:Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;

    iget-object v2, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;->f$2:Lorg/telegram/utils/proxy/ProxySettings;

    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda0;->f$3:Lorg/telegram/tgnet/RequestTimeDelegate;

    invoke-static {v0, v1, v2, p0}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->$r8$lambda$ufe7vls3QFXLZj8v4zidu7dGV6M(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;Lorg/telegram/utils/proxy/ProxySettings;Lorg/telegram/tgnet/RequestTimeDelegate;)V

    return-void
.end method
