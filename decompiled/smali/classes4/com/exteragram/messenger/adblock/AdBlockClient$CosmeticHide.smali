.class public Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/adblock/AdBlockClient;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "CosmeticHide"
.end annotation


# instance fields
.field private final exceptions:[Ljava/lang/String;

.field private final genericHide:Z

.field private final hideCss:Ljava/lang/String;

.field private final injectedScript:Ljava/lang/String;

.field private final url:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Z)V
    .locals 0

    .line 247
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 248
    iput-object p1, p0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->url:Ljava/lang/String;

    .line 249
    iput-object p2, p0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->hideCss:Ljava/lang/String;

    .line 250
    iput-object p3, p0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->injectedScript:Ljava/lang/String;

    .line 251
    iput-object p4, p0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->exceptions:[Ljava/lang/String;

    .line 252
    iput-boolean p5, p0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->genericHide:Z

    return-void
.end method


# virtual methods
.method public getExceptions()[Ljava/lang/String;
    .locals 0

    .line 268
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->exceptions:[Ljava/lang/String;

    return-object p0
.end method

.method public getHideCss()Ljava/lang/String;
    .locals 0

    .line 260
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->hideCss:Ljava/lang/String;

    return-object p0
.end method

.method public getInjectedScript()Ljava/lang/String;
    .locals 0

    .line 264
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->injectedScript:Ljava/lang/String;

    return-object p0
.end method

.method public getUrl()Ljava/lang/String;
    .locals 0

    .line 256
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->url:Ljava/lang/String;

    return-object p0
.end method

.method public isGenericHide()Z
    .locals 0

    .line 272
    iget-boolean p0, p0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->genericHide:Z

    return p0
.end method
