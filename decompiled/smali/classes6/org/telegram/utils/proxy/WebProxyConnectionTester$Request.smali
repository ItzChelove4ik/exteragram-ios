.class final Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/proxy/WebProxyConnectionTester;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Request"
.end annotation


# instance fields
.field final delegate:Lorg/telegram/tgnet/RequestTimeDelegate;

.field port:I

.field final settings:Lorg/telegram/utils/proxy/ProxySettings;

.field final testImplementation:Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;


# direct methods
.method public constructor <init>(Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;Lorg/telegram/utils/proxy/ProxySettings;Lorg/telegram/tgnet/RequestTimeDelegate;)V
    .locals 0

    .line 40
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 41
    iput-object p1, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->testImplementation:Lorg/telegram/utils/proxy/WebProxyConnectionTester$TestImplementation;

    .line 42
    iput-object p2, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    .line 43
    iput-object p3, p0, Lorg/telegram/utils/proxy/WebProxyConnectionTester$Request;->delegate:Lorg/telegram/tgnet/RequestTimeDelegate;

    return-void
.end method
