.class public final Lorg/telegram/utils/proxy/ProxySettings$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/proxy/ProxySettings;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field private address:Ljava/lang/String;

.field private password:Ljava/lang/String;

.field private port:I

.field private secret:Ljava/lang/String;

.field private type:Lorg/telegram/utils/proxy/ProxySettings$Type;

.field private user:Ljava/lang/String;


# direct methods
.method public static bridge synthetic -$$Nest$fgetaddress(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Ljava/lang/String;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->address:Ljava/lang/String;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpassword(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Ljava/lang/String;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->password:Ljava/lang/String;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetport(Lorg/telegram/utils/proxy/ProxySettings$Builder;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->port:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetsecret(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Ljava/lang/String;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->secret:Ljava/lang/String;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgettype(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Lorg/telegram/utils/proxy/ProxySettings$Type;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetuser(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Ljava/lang/String;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->user:Ljava/lang/String;

    return-object p0
.end method

.method private constructor <init>()V
    .locals 1

    .line 331
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 324
    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    .line 325
    const-string v0, ""

    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->address:Ljava/lang/String;

    .line 327
    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->user:Ljava/lang/String;

    .line 328
    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->password:Ljava/lang/String;

    .line 329
    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->secret:Ljava/lang/String;

    return-void
.end method

.method public synthetic constructor <init>(Lorg/telegram/utils/proxy/ProxySettings-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/ProxySettings$Builder;-><init>()V

    return-void
.end method


# virtual methods
.method public build()Lorg/telegram/utils/proxy/ProxySettings;
    .locals 2

    .line 365
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-ne v0, v1, :cond_2

    .line 366
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->address:Ljava/lang/String;

    invoke-static {v0}, Lorg/telegram/utils/proxy/ProxySettings;->-$$Nest$smnormalizeWebAddress(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->address:Ljava/lang/String;

    .line 367
    invoke-static {v0}, Lorg/telegram/utils/proxy/ProxySettings;->-$$Nest$smhasWebProxyPath(Ljava/lang/String;)Z

    move-result v0

    .line 371
    iget-object v1, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->secret:Ljava/lang/String;

    if-eqz v0, :cond_1

    .line 368
    invoke-static {v1}, Lorg/telegram/utils/proxy/ProxySettings;->-$$Nest$smdecodeMarkedWebProxySecret(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 369
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->secret:Ljava/lang/String;

    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {v0, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v0

    :goto_0
    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->secret:Ljava/lang/String;

    goto :goto_1

    .line 371
    :cond_1
    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {v1, v0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->secret:Ljava/lang/String;

    .line 374
    :cond_2
    :goto_1
    new-instance v0, Lorg/telegram/utils/proxy/ProxySettings;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/telegram/utils/proxy/ProxySettings;-><init>(Lorg/telegram/utils/proxy/ProxySettings$Builder;Lorg/telegram/utils/proxy/ProxySettings-IA;)V

    return-object v0
.end method

.method public setAddress(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;
    .locals 0

    if-eqz p1, :cond_0

    goto :goto_0

    .line 340
    :cond_0
    const-string p1, ""

    :goto_0
    iput-object p1, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->address:Ljava/lang/String;

    return-object p0
.end method

.method public setPassword(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;
    .locals 0

    if-eqz p1, :cond_0

    goto :goto_0

    .line 355
    :cond_0
    const-string p1, ""

    :goto_0
    iput-object p1, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->password:Ljava/lang/String;

    return-object p0
.end method

.method public setPort(I)Lorg/telegram/utils/proxy/ProxySettings$Builder;
    .locals 0

    .line 345
    iput p1, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->port:I

    return-object p0
.end method

.method public setSecret(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;
    .locals 0

    if-eqz p1, :cond_0

    goto :goto_0

    .line 360
    :cond_0
    const-string p1, ""

    :goto_0
    iput-object p1, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->secret:Ljava/lang/String;

    return-object p0
.end method

.method public setType(Lorg/telegram/utils/proxy/ProxySettings$Type;)Lorg/telegram/utils/proxy/ProxySettings$Builder;
    .locals 0

    if-eqz p1, :cond_0

    goto :goto_0

    .line 335
    :cond_0
    sget-object p1, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    :goto_0
    iput-object p1, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    return-object p0
.end method

.method public setUser(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;
    .locals 0

    if-eqz p1, :cond_0

    goto :goto_0

    .line 350
    :cond_0
    const-string p1, ""

    :goto_0
    iput-object p1, p0, Lorg/telegram/utils/proxy/ProxySettings$Builder;->user:Ljava/lang/String;

    return-object p0
.end method
