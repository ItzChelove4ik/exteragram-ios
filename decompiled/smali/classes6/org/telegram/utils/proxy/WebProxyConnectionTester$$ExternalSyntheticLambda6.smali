.class public final synthetic Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda6;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/proxy/WebProxyConnectionTester;

.field public final synthetic f$1:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

.field public final synthetic f$2:J


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda6;->f$0:Lorg/telegram/utils/proxy/WebProxyConnectionTester;

    iput-object p2, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda6;->f$1:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    iput-wide p3, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda6;->f$2:J

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 4

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda6;->f$0:Lorg/telegram/utils/proxy/WebProxyConnectionTester;

    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda6;->f$1:Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;

    iget-wide v2, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$$ExternalSyntheticLambda6;->f$2:J

    invoke-static {v0, v1, v2, v3}, Lorg/telegram/utils/proxy/WebProxyConnectionTester;->$r8$lambda$VaftKsXTMeXQish5gmoS-deyXA8(Lorg/telegram/utils/proxy/WebProxyConnectionTester;Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;J)V

    return-void
.end method
