.class public final synthetic Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda5;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/Utilities$Callback;


# instance fields
.field public final synthetic f$0:Ljava/lang/String;

.field public final synthetic f$1:Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda5;->f$0:Ljava/lang/String;

    iput-object p2, p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda5;->f$1:Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;

    return-void
.end method


# virtual methods
.method public final run(Ljava/lang/Object;)V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda5;->f$0:Ljava/lang/String;

    iget-object p0, p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda5;->f$1:Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;

    check-cast p1, Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;

    invoke-static {v0, p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->$r8$lambda$oRbqtd6yYrrPwcGrsMaifSCNU90(Ljava/lang/String;Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;)V

    return-void
.end method
