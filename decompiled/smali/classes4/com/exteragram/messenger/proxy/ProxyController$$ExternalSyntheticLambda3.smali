.class public final synthetic Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Ljava/lang/String;

.field public final synthetic f$1:Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;

.field public final synthetic f$2:Ljava/lang/String;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda3;->f$0:Ljava/lang/String;

    iput-object p2, p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda3;->f$1:Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;

    iput-object p3, p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda3;->f$2:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda3;->f$0:Ljava/lang/String;

    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda3;->f$1:Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;

    iget-object p0, p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda3;->f$2:Ljava/lang/String;

    invoke-static {v0, v1, p0}, Lcom/exteragram/messenger/proxy/ProxyController;->$r8$lambda$o2u5Mt7BsHFzVYQuxEPhAre7_kE(Ljava/lang/String;Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Ljava/lang/String;)V

    return-void
.end method
