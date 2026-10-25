.class public final Lcom/exteragram/messenger/proxy/ProxyController;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;,
        Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;
    }
.end annotation


# static fields
.field private static final INSTANCE:Lcom/exteragram/messenger/proxy/ProxyController;


# instance fields
.field private currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

.field private loaded:Z

.field private final pinnedProxies:Ljava/util/HashSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashSet<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private final pinnedProxyOrder:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private final proxyList:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/SharedConfig$ProxyInfo;",
            ">;"
        }
    .end annotation
.end field

.field private final proxyNames:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static synthetic $r8$lambda$8nqVbMH6tqZG4pxYVfVZ0Mk-G7U(Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Ljava/lang/String;)V
    .locals 0

    .line 243
    invoke-interface {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;->onCountryResolved(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$AmZUEYL-7cYteVYKP9bAMO4poLg(Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Ljava/lang/String;)V
    .locals 0

    .line 232
    invoke-interface {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;->onCountryResolved(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$OcyeWGIuaV-SIcBNk9h1f4w0rFQ(Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Ljava/lang/String;)V
    .locals 0

    .line 238
    invoke-interface {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;->onCountryResolved(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$TdY6Cr8_tKxnkeT37UhQ-TXIvZA(Lcom/exteragram/messenger/proxy/ProxyController;Lorg/telegram/messenger/SharedConfig$ProxyInfo;ZLjava/util/ArrayList;Lorg/telegram/messenger/SharedConfig$ProxyInfo;Lorg/telegram/messenger/SharedConfig$ProxyInfo;)I
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lcom/exteragram/messenger/proxy/ProxyController;->lambda$sortProxyList$6(Lorg/telegram/messenger/SharedConfig$ProxyInfo;ZLjava/util/ArrayList;Lorg/telegram/messenger/SharedConfig$ProxyInfo;Lorg/telegram/messenger/SharedConfig$ProxyInfo;)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$o2u5Mt7BsHFzVYQuxEPhAre7_kE(Ljava/lang/String;Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Ljava/lang/String;)V
    .locals 1

    .line 236
    invoke-static {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->resolveProxyIpAddress(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 237
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 238
    new-instance p0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda4;

    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Ljava/lang/String;)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 241
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda5;

    invoke-direct {v0, p2, p1}, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda5;-><init>(Ljava/lang/String;Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;)V

    invoke-static {p0, v0}, Lcom/exteragram/messenger/proxy/IpAddressInfoController;->requestIpAddressInfo(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)Lokhttp3/Call;

    return-void
.end method

.method public static synthetic $r8$lambda$oRbqtd6yYrrPwcGrsMaifSCNU90(Ljava/lang/String;Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;)V
    .locals 0

    if-eqz p2, :cond_0

    .line 242
    iget-object p0, p2, Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;->countryCode:Ljava/lang/String;

    invoke-static {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->getCountryDisplayName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 243
    :cond_0
    new-instance p2, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda6;

    invoke-direct {p2, p1, p0}, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Ljava/lang/String;)V

    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$pKO6UU3ex6y24YckVjP_LJNaPTI(Lcom/exteragram/messenger/proxy/ProxyController;Lorg/telegram/messenger/SharedConfig$ProxyInfo;Lorg/telegram/messenger/SharedConfig$ProxyInfo;)I
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/proxy/ProxyController;->lambda$saveProxyList$0(Lorg/telegram/messenger/SharedConfig$ProxyInfo;Lorg/telegram/messenger/SharedConfig$ProxyInfo;)I

    move-result p0

    return p0
.end method

.method static constructor <clinit>()V
    .locals 1

    .line 55
    new-instance v0, Lcom/exteragram/messenger/proxy/ProxyController;

    invoke-direct {v0}, Lcom/exteragram/messenger/proxy/ProxyController;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/proxy/ProxyController;->INSTANCE:Lcom/exteragram/messenger/proxy/ProxyController;

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 68
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 57
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    .line 58
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    .line 59
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    .line 60
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    return-void
.end method

.method private static buildSettings(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings;
    .locals 2

    .line 718
    invoke-static {}, Lorg/telegram/utils/proxy/ProxySettings;->builder()Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    .line 719
    invoke-static {p4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    goto :goto_0

    :cond_0
    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->MTPROTO:Lorg/telegram/utils/proxy/ProxySettings$Type;

    :goto_0
    invoke-virtual {v0, v1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setType(Lorg/telegram/utils/proxy/ProxySettings$Type;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    .line 720
    invoke-virtual {v0, p0}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setAddress(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object p0

    .line 721
    invoke-virtual {p0, p1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setPort(I)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object p0

    .line 722
    invoke-virtual {p0, p2}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setUser(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object p0

    .line 723
    invoke-virtual {p0, p3}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setPassword(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object p0

    .line 724
    invoke-virtual {p0, p4}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setSecret(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object p0

    .line 725
    invoke-virtual {p0}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->build()Lorg/telegram/utils/proxy/ProxySettings;

    move-result-object p0

    return-object p0
.end method

.method private static decode(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 658
    new-instance v0, Ljava/lang/String;

    const/4 v1, 0x2

    invoke-static {p0, v1}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object p0

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, p0, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0
.end method

.method private static encode(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    if-eqz p0, :cond_0

    goto :goto_0

    .line 654
    :cond_0
    const-string p0, ""

    :goto_0
    sget-object v0, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p0, v0}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p0

    const/4 v0, 0x2

    invoke-static {p0, v0}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private ensureLoaded()V
    .locals 1

    .line 478
    iget-boolean v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->loaded:Z

    if-nez v0, :cond_0

    .line 479
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->load()V

    :cond_0
    return-void
.end method

.method private static getCountryDisplayName(Ljava/lang/String;)Ljava/lang/String;
    .locals 4

    .line 697
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const-string v1, ""

    if-eqz v0, :cond_0

    return-object v1

    .line 700
    :cond_0
    invoke-static {}, Lorg/telegram/messenger/LocaleController;->getInstance()Lorg/telegram/messenger/LocaleController;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/LocaleController;->getCurrentLocale()Ljava/util/Locale;

    move-result-object v0

    if-nez v0, :cond_1

    .line 702
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v0

    .line 704
    :cond_1
    new-instance v2, Ljava/util/Locale;

    sget-object v3, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {p0, v3}, Ljava/lang/String;->toUpperCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {v2, v1, p0}, Ljava/util/Locale;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/util/Locale;->getDisplayCountry(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    .line 705
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_2

    sget p0, Lorg/telegram/messenger/R$string;->Unknown:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    :cond_2
    return-object p0
.end method

.method public static getInstance()Lcom/exteragram/messenger/proxy/ProxyController;
    .locals 1

    .line 65
    sget-object v0, Lcom/exteragram/messenger/proxy/ProxyController;->INSTANCE:Lcom/exteragram/messenger/proxy/ProxyController;

    return-object v0
.end method

.method private static getProxyCountryKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;
    .locals 0

    if-nez p0, :cond_0

    .line 682
    const-string p0, ""

    return-object p0

    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {p0}, Lorg/telegram/utils/proxy/ProxySettings;->getAddress()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->normalizeProxyCountryKey(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;
    .locals 0

    if-eqz p1, :cond_1

    .line 646
    iget-object p0, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {p0}, Lorg/telegram/utils/proxy/ProxySettings;->getLink()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    iget-object p0, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {p0}, Lorg/telegram/utils/proxy/ProxySettings;->getLink()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private static isTelegramProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Z
    .locals 0

    if-eqz p0, :cond_0

    .line 729
    iget-object p0, p0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {p0}, Lorg/telegram/utils/proxy/ProxySettings;->getSecret()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private synthetic lambda$saveProxyList$0(Lorg/telegram/messenger/SharedConfig$ProxyInfo;Lorg/telegram/messenger/SharedConfig$ProxyInfo;)I
    .locals 9

    .line 95
    iget-object p0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    const-wide/16 v0, 0x0

    const-wide/32 v2, -0x30d40

    if-ne p0, p1, :cond_0

    move-wide v4, v2

    goto :goto_0

    :cond_0
    move-wide v4, v0

    .line 96
    :goto_0
    iget-boolean v6, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->available:Z

    const-wide/32 v7, 0x186a0

    if-nez v6, :cond_1

    add-long/2addr v4, v7

    :cond_1
    if-ne p0, p2, :cond_2

    move-wide v0, v2

    .line 100
    :cond_2
    iget-boolean p0, p2, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->available:Z

    if-nez p0, :cond_3

    add-long/2addr v0, v7

    .line 103
    :cond_3
    iget-wide p0, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    add-long/2addr p0, v4

    iget-wide v2, p2, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    add-long/2addr v2, v0

    invoke-static {p0, p1, v2, v3}, Ljava/lang/Long;->compare(JJ)I

    move-result p0

    return p0
.end method

.method private synthetic lambda$sortProxyList$6(Lorg/telegram/messenger/SharedConfig$ProxyInfo;ZLjava/util/ArrayList;Lorg/telegram/messenger/SharedConfig$ProxyInfo;Lorg/telegram/messenger/SharedConfig$ProxyInfo;)I
    .locals 8

    .line 450
    invoke-virtual {p0, p4}, Lcom/exteragram/messenger/proxy/ProxyController;->getPinnedIndex(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)I

    move-result v0

    .line 451
    invoke-virtual {p0, p5}, Lcom/exteragram/messenger/proxy/ProxyController;->getPinnedIndex(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)I

    move-result p0

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-ltz v0, :cond_0

    move v3, v2

    goto :goto_0

    :cond_0
    move v3, v1

    :goto_0
    if-ltz p0, :cond_1

    move v1, v2

    :cond_1
    if-eq v3, v1, :cond_3

    if-eqz v3, :cond_2

    const/4 p0, -0x1

    return p0

    :cond_2
    return v2

    :cond_3
    if-eqz v3, :cond_4

    .line 458
    invoke-static {v0, p0}, Ljava/lang/Integer;->compare(II)I

    move-result p0

    return p0

    :cond_4
    const-wide/16 v0, 0x0

    const-wide/32 v2, -0x30d40

    if-ne p1, p4, :cond_5

    move-wide v4, v2

    goto :goto_1

    :cond_5
    move-wide v4, v0

    .line 461
    :goto_1
    iget-boolean p0, p4, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->available:Z

    const-wide/32 v6, 0x186a0

    if-nez p0, :cond_6

    add-long/2addr v4, v6

    :cond_6
    if-ne p1, p5, :cond_7

    move-wide v0, v2

    .line 465
    :cond_7
    iget-boolean p0, p5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->available:Z

    if-nez p0, :cond_8

    add-long/2addr v0, v6

    :cond_8
    const-wide/16 v2, 0x2710

    if-eqz p2, :cond_9

    if-eq p4, p1, :cond_9

    .line 468
    invoke-virtual {p3, p4}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result p0

    int-to-long v4, p0

    mul-long/2addr v4, v2

    goto :goto_2

    :cond_9
    iget-wide v6, p4, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    add-long/2addr v4, v6

    :goto_2
    if-eqz p2, :cond_a

    if-eq p5, p1, :cond_a

    .line 469
    invoke-virtual {p3, p5}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result p0

    int-to-long p0, p0

    mul-long/2addr p0, v2

    goto :goto_3

    :cond_a
    iget-wide p0, p5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    add-long/2addr p0, v0

    .line 468
    :goto_3
    invoke-static {v4, v5, p0, p1}, Ljava/lang/Long;->compare(JJ)I

    move-result p0

    return p0
.end method

.method private load()V
    .locals 14

    .line 484
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    const/4 v0, 0x0

    .line 485
    iput-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 486
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v1}, Ljava/util/HashSet;->clear()V

    .line 487
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    .line 488
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v1}, Ljava/util/HashMap;->clear()V

    .line 489
    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string/jumbo v2, "mainconfig"

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v1

    .line 490
    invoke-static {v1}, Lorg/telegram/utils/proxy/ProxySettings;->fromSharedPreferences(Landroid/content/SharedPreferences;)Lorg/telegram/utils/proxy/ProxySettings;

    move-result-object v2

    .line 491
    invoke-virtual {v2}, Lorg/telegram/utils/proxy/ProxySettings;->isValid()Z

    move-result v4

    .line 493
    const-string/jumbo v5, "proxy_list"

    invoke-interface {v1, v5, v0}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 495
    invoke-static {v5}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    const/4 v7, 0x1

    if-nez v6, :cond_d

    .line 496
    invoke-static {v5, v3}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object v5

    .line 497
    new-instance v6, Lorg/telegram/tgnet/SerializedData;

    invoke-direct {v6, v5}, Lorg/telegram/tgnet/SerializedData;-><init>([B)V

    .line 498
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v5

    const/4 v8, -0x1

    if-ne v5, v8, :cond_9

    .line 500
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readByte(Z)B

    move-result v5

    const/4 v8, 0x5

    if-ne v5, v8, :cond_2

    .line 503
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v5

    move v8, v3

    :goto_0
    if-ge v8, v5, :cond_b

    .line 505
    invoke-static {v6}, Lcom/exteragram/messenger/proxy/ProxyController;->readProxyInfo(Lorg/telegram/tgnet/SerializedData;)Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    move-result-object v9

    .line 506
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readInt64(Z)J

    move-result-wide v10

    iput-wide v10, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    .line 507
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readInt64(Z)J

    move-result-wide v10

    invoke-static {v10, v11}, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->normalizeAvailableCheckTime(J)J

    move-result-wide v10

    iput-wide v10, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->availableCheckTime:J

    .line 508
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readBool(Z)Z

    move-result v10

    if-eqz v10, :cond_0

    .line 509
    invoke-static {}, Lorg/telegram/utils/proxy/ProxySettings;->builder()Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v10

    sget-object v11, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    .line 510
    invoke-virtual {v10, v11}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setType(Lorg/telegram/utils/proxy/ProxySettings$Type;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v10

    iget-object v11, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    .line 511
    invoke-virtual {v11}, Lorg/telegram/utils/proxy/ProxySettings;->getAddress()Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v10, v11}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setAddress(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v10

    iget-object v11, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    .line 512
    invoke-virtual {v11}, Lorg/telegram/utils/proxy/ProxySettings;->getPort()I

    move-result v11

    invoke-virtual {v10, v11}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setPort(I)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v10

    iget-object v11, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    .line 513
    invoke-virtual {v11}, Lorg/telegram/utils/proxy/ProxySettings;->getSecret()Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v10, v11}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setSecret(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v10

    .line 514
    invoke-virtual {v10}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->build()Lorg/telegram/utils/proxy/ProxySettings;

    move-result-object v10

    iput-object v10, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    .line 517
    :cond_0
    iget-object v10, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v10, v3, v9}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 518
    iget-object v10, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    if-nez v10, :cond_1

    if-eqz v4, :cond_1

    .line 519
    iget-object v10, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v2, v10}, Lorg/telegram/utils/proxy/ProxySettings;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_1

    .line 520
    iput-object v9, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    :cond_1
    add-int/lit8 v8, v8, 0x1

    goto :goto_0

    :cond_2
    const/4 v8, 0x3

    if-ne v5, v8, :cond_5

    .line 525
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v5

    move v8, v3

    move v9, v8

    :goto_1
    if-ge v8, v5, :cond_c

    .line 527
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readString(Z)Ljava/lang/String;

    move-result-object v10

    .line 528
    invoke-static {v6}, Lcom/exteragram/messenger/proxy/ProxyController;->readProxyInfo(Lorg/telegram/tgnet/SerializedData;)Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    move-result-object v11

    .line 530
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readInt64(Z)J

    move-result-wide v12

    iput-wide v12, v11, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    .line 531
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readInt64(Z)J

    move-result-wide v12

    invoke-static {v12, v13}, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->normalizeAvailableCheckTime(J)J

    move-result-wide v12

    iput-wide v12, v11, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->availableCheckTime:J

    .line 533
    iget-object v12, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v12, v3, v11}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 534
    invoke-direct {p0, v11}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object v12

    .line 535
    invoke-static {v10}, Lcom/exteragram/messenger/proxy/ProxyController;->normalizeName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 536
    invoke-static {v12}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v13

    if-nez v13, :cond_3

    invoke-static {v10}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v13

    if-nez v13, :cond_3

    .line 537
    iget-object v9, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v9, v12, v10}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move v9, v7

    .line 540
    :cond_3
    iget-object v10, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    if-nez v10, :cond_4

    if-eqz v4, :cond_4

    .line 541
    iget-object v10, v11, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v2, v10}, Lorg/telegram/utils/proxy/ProxySettings;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_4

    .line 542
    iput-object v11, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    :cond_4
    add-int/lit8 v8, v8, 0x1

    goto :goto_1

    :cond_5
    const/4 v8, 0x4

    if-eq v5, v8, :cond_7

    const/4 v8, 0x2

    if-ne v5, v8, :cond_6

    goto :goto_2

    .line 562
    :cond_6
    new-instance v8, Ljava/lang/StringBuilder;

    const-string v9, "Unknown proxy schema version: "

    invoke-direct {v8, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    goto :goto_5

    .line 547
    :cond_7
    :goto_2
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v5

    move v8, v3

    :goto_3
    if-ge v8, v5, :cond_b

    .line 549
    invoke-static {v6}, Lcom/exteragram/messenger/proxy/ProxyController;->readProxyInfo(Lorg/telegram/tgnet/SerializedData;)Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    move-result-object v9

    .line 551
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readInt64(Z)J

    move-result-wide v10

    iput-wide v10, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    .line 552
    invoke-virtual {v6, v3}, Lorg/telegram/tgnet/SerializedData;->readInt64(Z)J

    move-result-wide v10

    invoke-static {v10, v11}, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->normalizeAvailableCheckTime(J)J

    move-result-wide v10

    iput-wide v10, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->availableCheckTime:J

    .line 554
    iget-object v10, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v10, v3, v9}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 555
    iget-object v10, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    if-nez v10, :cond_8

    if-eqz v4, :cond_8

    .line 556
    iget-object v10, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v2, v10}, Lorg/telegram/utils/proxy/ProxySettings;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_8

    .line 557
    iput-object v9, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    :cond_8
    add-int/lit8 v8, v8, 0x1

    goto :goto_3

    :cond_9
    move v8, v3

    :goto_4
    if-ge v8, v5, :cond_b

    .line 566
    invoke-static {v6}, Lcom/exteragram/messenger/proxy/ProxyController;->readProxyInfo(Lorg/telegram/tgnet/SerializedData;)Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    move-result-object v9

    .line 567
    iget-object v10, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v10, v3, v9}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 568
    iget-object v10, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    if-nez v10, :cond_a

    if-eqz v4, :cond_a

    .line 569
    iget-object v10, v9, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v2, v10}, Lorg/telegram/utils/proxy/ProxySettings;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_a

    .line 570
    iput-object v9, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    :cond_a
    add-int/lit8 v8, v8, 0x1

    goto :goto_4

    :cond_b
    :goto_5
    move v9, v3

    .line 575
    :cond_c
    invoke-virtual {v6}, Lorg/telegram/tgnet/SerializedData;->cleanup()V

    goto :goto_6

    :cond_d
    move v9, v3

    .line 577
    :goto_6
    iget-object v5, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    if-nez v5, :cond_e

    if-eqz v4, :cond_e

    .line 578
    new-instance v4, Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    invoke-direct {v4, v2}, Lorg/telegram/messenger/SharedConfig$ProxyInfo;-><init>(Lorg/telegram/utils/proxy/ProxySettings;)V

    iput-object v4, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 579
    iget-object v2, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v2, v3, v4}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 581
    :cond_e
    const-string/jumbo v2, "proxy_pinned_links_order"

    invoke-interface {v1, v2, v0}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    const-string/jumbo v4, "proxy_pinned_order"

    invoke-interface {v1, v4, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 582
    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    const-string v5, "\\n"

    if-nez v4, :cond_10

    .line 583
    invoke-virtual {v2, v5}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v2

    .line 584
    array-length v4, v2

    move v6, v3

    :goto_7
    if-ge v6, v4, :cond_10

    aget-object v8, v2, v6

    .line 585
    invoke-static {v8}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v10

    if-nez v10, :cond_f

    iget-object v10, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v10, v8}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_f

    .line 586
    iget-object v10, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v10, v8}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_f
    add-int/lit8 v6, v6, 0x1

    goto :goto_7

    .line 590
    :cond_10
    const-string/jumbo v2, "proxy_pinned_links"

    invoke-interface {v1, v2, v0}, Landroid/content/SharedPreferences;->getStringSet(Ljava/lang/String;Ljava/util/Set;)Ljava/util/Set;

    move-result-object v2

    const-string/jumbo v4, "proxy_pinned"

    invoke-interface {v1, v4, v2}, Landroid/content/SharedPreferences;->getStringSet(Ljava/lang/String;Ljava/util/Set;)Ljava/util/Set;

    move-result-object v2

    if-eqz v2, :cond_12

    .line 592
    invoke-interface {v2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_11
    :goto_8
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_12

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    .line 593
    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-nez v6, :cond_11

    iget-object v6, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v6, v4}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_11

    .line 594
    iget-object v6, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v6, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_8

    .line 598
    :cond_12
    const-string/jumbo v2, "proxy_names"

    invoke-interface {v1, v2, v0}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 599
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_16

    .line 600
    invoke-virtual {v0, v5}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    .line 601
    array-length v1, v0

    move v2, v3

    :goto_9
    if-ge v2, v1, :cond_16

    aget-object v4, v0, v2

    .line 602
    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_13

    goto :goto_a

    :cond_13
    const/16 v5, 0x9

    .line 605
    invoke-virtual {v4, v5}, Ljava/lang/String;->indexOf(I)I

    move-result v5

    if-lez v5, :cond_15

    .line 606
    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v6

    sub-int/2addr v6, v7

    if-lt v5, v6, :cond_14

    goto :goto_a

    .line 609
    :cond_14
    invoke-virtual {v4, v3, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v6

    invoke-static {v6}, Lcom/exteragram/messenger/proxy/ProxyController;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    .line 610
    invoke-virtual {v4, v5}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Lcom/exteragram/messenger/proxy/ProxyController;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 611
    invoke-static {v6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_15

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_15

    .line 612
    iget-object v5, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v5, v6, v4}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_15
    :goto_a
    add-int/lit8 v2, v2, 0x1

    goto :goto_9

    .line 616
    :cond_16
    iput-boolean v7, p0, Lcom/exteragram/messenger/proxy/ProxyController;->loaded:Z

    if-eqz v9, :cond_17

    .line 618
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->save()V

    .line 620
    :cond_17
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->syncProxyList()V

    return-void
.end method

.method private static normalizeName(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    if-nez p0, :cond_0

    .line 650
    const-string p0, ""

    return-object p0

    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static normalizeProxyCountryKey(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 686
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 687
    const-string p0, ""

    return-object p0

    .line 689
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 690
    const-string v0, "["

    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    const-string v0, "]"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    const/4 v1, 0x2

    if-le v0, v1, :cond_1

    .line 691
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    invoke-virtual {p0, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    .line 693
    :cond_1
    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {p0, v0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static readProxyInfo(Lorg/telegram/tgnet/SerializedData;)Lorg/telegram/messenger/SharedConfig$ProxyInfo;
    .locals 5

    const/4 v0, 0x0

    .line 709
    invoke-virtual {p0, v0}, Lorg/telegram/tgnet/SerializedData;->readString(Z)Ljava/lang/String;

    move-result-object v1

    .line 710
    invoke-virtual {p0, v0}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v2

    .line 711
    invoke-virtual {p0, v0}, Lorg/telegram/tgnet/SerializedData;->readString(Z)Ljava/lang/String;

    move-result-object v3

    .line 712
    invoke-virtual {p0, v0}, Lorg/telegram/tgnet/SerializedData;->readString(Z)Ljava/lang/String;

    move-result-object v4

    .line 713
    invoke-virtual {p0, v0}, Lorg/telegram/tgnet/SerializedData;->readString(Z)Ljava/lang/String;

    move-result-object p0

    .line 714
    new-instance v0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    invoke-static {v1, v2, v3, v4, p0}, Lcom/exteragram/messenger/proxy/ProxyController;->buildSettings(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings;

    move-result-object p0

    invoke-direct {v0, p0}, Lorg/telegram/messenger/SharedConfig$ProxyInfo;-><init>(Lorg/telegram/utils/proxy/ProxySettings;)V

    return-object v0
.end method

.method private static resolveProxyIpAddress(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    const/4 v0, 0x0

    .line 670
    :try_start_0
    invoke-static {p0}, Ljava/net/IDN;->toASCII(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Ljava/net/InetAddress;->getByName(Ljava/lang/String;)Ljava/net/InetAddress;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 675
    invoke-virtual {p0}, Ljava/net/InetAddress;->isAnyLocalAddress()Z

    move-result v1

    if-nez v1, :cond_1

    invoke-virtual {p0}, Ljava/net/InetAddress;->isLoopbackAddress()Z

    move-result v1

    if-nez v1, :cond_1

    invoke-virtual {p0}, Ljava/net/InetAddress;->isLinkLocalAddress()Z

    move-result v1

    if-nez v1, :cond_1

    invoke-virtual {p0}, Ljava/net/InetAddress;->isSiteLocalAddress()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 678
    :cond_0
    invoke-virtual {p0}, Ljava/net/InetAddress;->getHostAddress()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    :goto_0
    return-object v0

    :catch_0
    move-exception p0

    .line 672
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-object v0
.end method

.method private save()V
    .locals 7

    .line 624
    invoke-static {}, Lorg/telegram/messenger/MessagesController;->getGlobalMainSettings()Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 625
    new-instance v1, Ljava/util/HashSet;

    iget-object v2, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-direct {v1, v2}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    const-string/jumbo v2, "proxy_pinned"

    invoke-interface {v0, v2, v1}, Landroid/content/SharedPreferences$Editor;->putStringSet(Ljava/lang/String;Ljava/util/Set;)Landroid/content/SharedPreferences$Editor;

    .line 626
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    const-string v2, "\n"

    const-string/jumbo v3, "proxy_pinned_order"

    if-eqz v1, :cond_0

    .line 627
    invoke-interface {v0, v3}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    goto :goto_0

    .line 629
    :cond_0
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-static {v2, v1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v3, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 631
    :goto_0
    const-string/jumbo v1, "proxy_pinned_links"

    invoke-interface {v0, v1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 632
    const-string/jumbo v1, "proxy_pinned_links_order"

    invoke-interface {v0, v1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 633
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v1}, Ljava/util/HashMap;->isEmpty()Z

    move-result v1

    const-string/jumbo v3, "proxy_names"

    if-eqz v1, :cond_1

    .line 634
    invoke-interface {v0, v3}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    goto :goto_2

    .line 636
    :cond_1
    new-instance v1, Ljava/util/ArrayList;

    iget-object v4, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v4}, Ljava/util/HashMap;->size()I

    move-result v4

    invoke-direct {v1, v4}, Ljava/util/ArrayList;-><init>(I)V

    .line 637
    iget-object p0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {p0}, Ljava/util/HashMap;->entrySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Map$Entry;

    .line 638
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-interface {v4}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-static {v6}, Lcom/exteragram/messenger/proxy/ProxyController;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "\t"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-interface {v4}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-static {v4}, Lcom/exteragram/messenger/proxy/ProxyController;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 640
    :cond_2
    invoke-static {v2, v1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object p0

    invoke-interface {v0, v3, p0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 642
    :goto_2
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method private syncProxyList()V
    .locals 2

    .line 662
    sget-object v0, Lorg/telegram/messenger/SharedConfig;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 663
    sget-object v0, Lorg/telegram/messenger/SharedConfig;->proxyList:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 664
    iget-object p0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    sput-object p0, Lorg/telegram/messenger/SharedConfig;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    return-void
.end method


# virtual methods
.method public declared-synchronized addProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Lorg/telegram/messenger/SharedConfig$ProxyInfo;
    .locals 6

    monitor-enter p0

    .line 127
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 128
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 v1, 0x0

    move v2, v1

    .line 135
    :goto_0
    iget-object v3, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    if-ge v2, v0, :cond_1

    .line 130
    :try_start_1
    invoke-virtual {v3, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 131
    iget-object v4, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    iget-object v5, v3, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v4, v5}, Lorg/telegram/utils/proxy/ProxySettings;->equals(Ljava/lang/Object;)Z

    move-result v4
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz v4, :cond_0

    .line 132
    monitor-exit p0

    return-object v3

    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 135
    :cond_1
    :try_start_2
    invoke-virtual {v3, v1, p1}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 136
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->syncProxyList()V

    .line 137
    invoke-virtual {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->saveProxyList()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 138
    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_3
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw p1
.end method

.method public declared-synchronized applySelectedPinAction(Ljava/util/List;)Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lorg/telegram/messenger/SharedConfig$ProxyInfo;",
            ">;)",
            "Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;"
        }
    .end annotation

    monitor-enter p0

    .line 356
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 357
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getSelectedPinAction(Ljava/util/List;)I

    move-result v0

    if-nez v0, :cond_0

    .line 359
    sget-object p1, Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;->NO_CHANGE:Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    goto/16 :goto_3

    :cond_0
    const/4 v1, 0x0

    const/4 v2, 0x1

    if-ne v0, v2, :cond_5

    .line 363
    :try_start_1
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 364
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_1
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 365
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object v3

    .line 366
    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_1

    iget-object v4, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v4, v3}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_1

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_1

    .line 367
    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 370
    :cond_2
    iget-object p1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p1

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v3

    add-int/2addr p1, v3

    const/16 v3, 0xa

    if-le p1, v3, :cond_3

    .line 371
    sget-object p1, Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;->LIMIT_REACHED:Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit p0

    return-object p1

    .line 373
    :cond_3
    :try_start_2
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result p1

    move v3, v1

    :cond_4
    :goto_1
    if-ge v3, p1, :cond_9

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    check-cast v4, Ljava/lang/String;

    .line 374
    iget-object v5, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v5, v4}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_4

    .line 375
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move v1, v2

    goto :goto_1

    .line 380
    :cond_5
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_6
    :goto_2
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_9

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 381
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object v0

    .line 382
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_7

    goto :goto_2

    .line 385
    :cond_7
    iget-object v3, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v3, v0}, Ljava/util/HashSet;->remove(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_8

    move v1, v2

    .line 388
    :cond_8
    iget-object v3, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v3, v0}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_6

    move v1, v2

    goto :goto_2

    :cond_9
    if-eqz v1, :cond_a

    .line 394
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->save()V

    .line 395
    sget-object p1, Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;->CHANGED:Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    monitor-exit p0

    return-object p1

    .line 397
    :cond_a
    :try_start_3
    sget-object p1, Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;->NO_CHANGE:Lcom/exteragram/messenger/proxy/ProxyController$PinOperationResult;
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    monitor-exit p0

    return-object p1

    :goto_3
    :try_start_4
    monitor-exit p0
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    throw p1
.end method

.method public declared-synchronized buildShareLink(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;
    .locals 1

    monitor-enter p0

    const/4 v0, 0x0

    .line 154
    :try_start_0
    invoke-virtual {p0, p1, v0}, Lcom/exteragram/messenger/proxy/ProxyController;->buildShareLink(Lorg/telegram/messenger/SharedConfig$ProxyInfo;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized buildShareLink(Lorg/telegram/messenger/SharedConfig$ProxyInfo;Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    monitor-enter p0

    if-nez p1, :cond_0

    .line 159
    :try_start_0
    const-string p1, ""
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    goto :goto_0

    .line 161
    :cond_0
    :try_start_1
    iget-object v0, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v0}, Lorg/telegram/utils/proxy/ProxySettings;->getLink()Ljava/lang/String;

    move-result-object v0

    .line 162
    invoke-static {p2}, Lcom/exteragram/messenger/proxy/ProxyController;->normalizeName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    .line 163
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 164
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getName(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object p2

    .line 166
    :cond_1
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz p1, :cond_2

    .line 167
    monitor-exit p0

    return-object v0

    .line 170
    :cond_2
    :try_start_2
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "&title="

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "UTF-8"

    invoke-static {p2, v1}, Lj$/net/URLEncoder;->encode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1
    :try_end_2
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    monitor-exit p0

    return-object p1

    .line 172
    :catch_0
    monitor-exit p0

    return-object v0

    :goto_0
    :try_start_3
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw p1
.end method

.method public declared-synchronized clearAll()V
    .locals 1

    monitor-enter p0

    .line 436
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 437
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v0}, Ljava/util/HashSet;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->isEmpty()Z

    move-result v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v0, :cond_0

    .line 438
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    goto :goto_0

    .line 440
    :cond_0
    :try_start_1
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v0}, Ljava/util/HashSet;->clear()V

    .line 441
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 442
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->clear()V

    .line 443
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->save()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 444
    monitor-exit p0

    return-void

    :goto_0
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v0
.end method

.method public declared-synchronized deleteProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)V
    .locals 5

    monitor-enter p0

    .line 182
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 183
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object v0

    .line 184
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    if-eq v1, p1, :cond_0

    if-eqz v0, :cond_1

    .line 185
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    :cond_0
    :goto_0
    const/4 v0, 0x0

    .line 187
    iput-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 188
    sput-object v0, Lorg/telegram/messenger/SharedConfig;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 189
    invoke-static {}, Lorg/telegram/messenger/MessagesController;->getGlobalMainSettings()Landroid/content/SharedPreferences;

    move-result-object v1

    .line 190
    const-string/jumbo v2, "proxy_enabled"

    const/4 v3, 0x0

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v2

    .line 191
    invoke-interface {v1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    .line 192
    sget-object v4, Lorg/telegram/utils/proxy/ProxySettings;->EMPTY:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v4, v1}, Lorg/telegram/utils/proxy/ProxySettings;->toSharedPreferences(Landroid/content/SharedPreferences$Editor;)V

    .line 193
    const-string/jumbo v4, "proxy_enabled"

    invoke-interface {v1, v4, v3}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 194
    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->apply()V

    if-eqz v2, :cond_1

    .line 196
    invoke-static {v3, v0}, Lorg/telegram/tgnet/ConnectionsManager;->setProxySettings(ZLorg/telegram/utils/proxy/ProxySettings;)V

    .line 199
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 200
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->removeProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)V

    .line 201
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->syncProxyList()V

    .line 202
    invoke-virtual {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->saveProxyList()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 203
    monitor-exit p0

    return-void

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized getCurrentProxy()Lorg/telegram/messenger/SharedConfig$ProxyInfo;
    .locals 1

    monitor-enter p0

    .line 77
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 78
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public declared-synchronized getDisplayName(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;
    .locals 2

    monitor-enter p0

    if-nez p1, :cond_0

    .line 207
    :try_start_0
    const-string p1, ""
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 209
    :cond_0
    :try_start_1
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getName(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object v0

    .line 210
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-nez v1, :cond_1

    .line 211
    monitor-exit p0

    return-object v0

    .line 213
    :cond_1
    :try_start_2
    iget-object v0, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v0}, Lorg/telegram/utils/proxy/ProxySettings;->getType()Lorg/telegram/utils/proxy/ProxySettings$Type;

    move-result-object v0

    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-ne v0, v1, :cond_2

    .line 214
    iget-object p1, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {p1}, Lorg/telegram/utils/proxy/ProxySettings;->getAddress()Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    .line 215
    :cond_2
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v1}, Lorg/telegram/utils/proxy/ProxySettings;->getAddress()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ":"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {p1}, Lorg/telegram/utils/proxy/ProxySettings;->getPort()I

    move-result p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 213
    :goto_0
    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_3
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw p1
.end method

.method public getMaxPinnedProxies()I
    .locals 0

    const/16 p0, 0xa

    return p0
.end method

.method public declared-synchronized getName(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    monitor-enter p0

    .line 253
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 254
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 255
    const-string p1, ""
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 257
    :cond_0
    :try_start_1
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    if-eqz p1, :cond_1

    goto :goto_0

    .line 258
    :cond_1
    const-string p1, ""
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :goto_0
    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method public declared-synchronized getName(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;
    .locals 0

    monitor-enter p0

    if-nez p1, :cond_0

    .line 249
    :try_start_0
    const-string p1, ""

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    :cond_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :goto_0
    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized getPinnedCount()I
    .locals 1

    monitor-enter p0

    .line 328
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 329
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return v0

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public declared-synchronized getPinnedIndex(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)I
    .locals 2

    monitor-enter p0

    .line 319
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 v0, -0x1

    if-nez p1, :cond_0

    .line 321
    monitor-exit p0

    return v0

    .line 323
    :cond_0
    :try_start_1
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object p1

    .line 324
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :goto_0
    monitor-exit p0

    return v0

    :catchall_0
    move-exception p1

    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method public declared-synchronized getProxyList()Ljava/util/ArrayList;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/SharedConfig$ProxyInfo;",
            ">;"
        }
    .end annotation

    monitor-enter p0

    .line 72
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 73
    new-instance v0, Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public declared-synchronized getProxyTypeName(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;
    .locals 2

    monitor-enter p0

    if-eqz p1, :cond_0

    .line 219
    :try_start_0
    iget-object v0, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v0}, Lorg/telegram/utils/proxy/ProxySettings;->getType()Lorg/telegram/utils/proxy/ProxySettings$Type;

    move-result-object v0

    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-ne v0, v1, :cond_0

    .line 220
    sget p1, Lorg/telegram/messenger/R$string;->UseProxyWeb:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    goto :goto_2

    .line 222
    :cond_0
    :try_start_1
    invoke-static {p1}, Lcom/exteragram/messenger/proxy/ProxyController;->isTelegramProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Z

    move-result p1

    if-eqz p1, :cond_1

    sget p1, Lorg/telegram/messenger/R$string;->UseProxyTelegram:I

    :goto_0
    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    :cond_1
    sget p1, Lorg/telegram/messenger/R$string;->UseProxySocks5:I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :goto_1
    monitor-exit p0

    return-object p1

    :goto_2
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method public declared-synchronized getSelectedPinAction(Ljava/util/List;)I
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lorg/telegram/messenger/SharedConfig$ProxyInfo;",
            ">;)I"
        }
    .end annotation

    monitor-enter p0

    .line 333
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 336
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    const/4 v0, 0x0

    move v1, v0

    move v2, v1

    :cond_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    const/4 v4, 0x1

    if-eqz v3, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 337
    invoke-virtual {p0, v3}, Lcom/exteragram/messenger/proxy/ProxyController;->isPinned(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Z

    move-result v3
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v3, :cond_1

    move v1, v4

    goto :goto_0

    :cond_1
    move v2, v4

    :goto_0
    if-eqz v1, :cond_0

    if-eqz v2, :cond_0

    .line 343
    monitor-exit p0

    return v0

    :catchall_0
    move-exception p1

    goto :goto_1

    :cond_2
    if-eqz v1, :cond_3

    .line 347
    monitor-exit p0

    const/4 p0, 0x2

    return p0

    :cond_3
    if-eqz v2, :cond_4

    .line 350
    monitor-exit p0

    return v4

    .line 352
    :cond_4
    monitor-exit p0

    return v0

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized isPinned(Ljava/lang/String;)Z
    .locals 1

    monitor-enter p0

    .line 314
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 315
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v0, p1}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    :cond_0
    const/4 p1, 0x0

    :goto_0
    monitor-exit p0

    return p1

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized isPinned(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Z
    .locals 0

    monitor-enter p0

    if-eqz p1, :cond_0

    .line 310
    :try_start_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->isPinned(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :catchall_0
    move-exception p1

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1

    :cond_0
    const/4 p1, 0x0

    :goto_0
    monitor-exit p0

    return p1
.end method

.method public declared-synchronized loadProxyList()V
    .locals 1

    monitor-enter p0

    .line 88
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 89
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public declared-synchronized moveMetadata(Ljava/lang/String;Ljava/lang/String;)V
    .locals 3

    monitor-enter p0

    .line 284
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 285
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_5

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_5

    invoke-static {p1, p2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_2

    .line 289
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 290
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    const/4 v2, 0x1

    if-nez v1, :cond_1

    .line 291
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v1, p2, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move v0, v2

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_3

    :cond_1
    const/4 v0, 0x0

    .line 294
    :goto_0
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result v1

    if-ltz v1, :cond_3

    .line 296
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 297
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v0, p1}, Ljava/util/HashSet;->remove(Ljava/lang/Object;)Z

    .line 298
    iget-object p1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_2

    .line 299
    iget-object p1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    invoke-static {v1, v0}, Ljava/lang/Math;->min(II)I

    move-result v0

    invoke-virtual {p1, v0, p2}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 301
    :cond_2
    iget-object p1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {p1, p2}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :cond_3
    move v2, v0

    :goto_1
    if-eqz v2, :cond_4

    .line 305
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->save()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 307
    :cond_4
    monitor-exit p0

    return-void

    .line 286
    :cond_5
    :goto_2
    monitor-exit p0

    return-void

    :goto_3
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized movePinnedProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Z
    .locals 3

    monitor-enter p0

    .line 401
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 402
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object p1

    .line 403
    invoke-direct {p0, p2}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object p2

    .line 404
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_5

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_5

    invoke-static {p1, p2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_2

    .line 407
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v0, p1}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_4

    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v0, p2}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    goto :goto_1

    .line 410
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result v0

    .line 411
    iget-object v2, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v2, p2}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result p2

    if-ltz v0, :cond_3

    if-gez p2, :cond_2

    goto :goto_0

    .line 415
    :cond_2
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 416
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v0, p2, p1}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 417
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->save()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 418
    monitor-exit p0

    const/4 p0, 0x1

    return p0

    :catchall_0
    move-exception p1

    goto :goto_3

    .line 413
    :cond_3
    :goto_0
    monitor-exit p0

    return v1

    .line 408
    :cond_4
    :goto_1
    monitor-exit p0

    return v1

    .line 405
    :cond_5
    :goto_2
    monitor-exit p0

    return v1

    :goto_3
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized removeProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)V
    .locals 2

    monitor-enter p0

    .line 422
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 423
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object p1

    .line 424
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v0, :cond_0

    .line 425
    monitor-exit p0

    return-void

    .line 427
    :cond_0
    :try_start_1
    iget-object v0, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_1

    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 428
    :goto_0
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxies:Ljava/util/HashSet;

    invoke-virtual {v1, p1}, Ljava/util/HashSet;->remove(Ljava/lang/Object;)Z

    move-result v1

    or-int/2addr v0, v1

    .line 429
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->pinnedProxyOrder:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    move-result p1

    or-int/2addr p1, v0

    if-eqz p1, :cond_2

    .line 431
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->save()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_2

    .line 433
    :cond_2
    :goto_1
    monitor-exit p0

    return-void

    :goto_2
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method public requestProxyCountry(Lorg/telegram/messenger/SharedConfig$ProxyInfo;Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;)V
    .locals 2

    if-nez p2, :cond_0

    return-void

    .line 229
    :cond_0
    sget p0, Lorg/telegram/messenger/R$string;->Unknown:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    .line 230
    invoke-static {p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyCountryKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object p1

    .line 231
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 232
    new-instance p1, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda2;

    invoke-direct {p1, p2, p0}, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Ljava/lang/String;)V

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 235
    :cond_1
    sget-object v0, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda3;

    invoke-direct {v1, p1, p2, p0}, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda3;-><init>(Ljava/lang/String;Lcom/exteragram/messenger/proxy/ProxyController$ProxyCountryCallback;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public declared-synchronized saveProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/messenger/SharedConfig$ProxyInfo;
    .locals 1

    monitor-enter p0

    .line 142
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 143
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v0}, Lorg/telegram/utils/proxy/ProxySettings;->getLink()Ljava/lang/String;

    move-result-object v0

    invoke-static {p2, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 144
    iget-object v0, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v0}, Lorg/telegram/utils/proxy/ProxySettings;->getLink()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, p2, v0}, Lcom/exteragram/messenger/proxy/ProxyController;->moveMetadata(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 146
    :cond_0
    :goto_0
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->addProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    move-result-object p1

    if-eqz p3, :cond_1

    .line 148
    invoke-virtual {p0, p1, p3}, Lcom/exteragram/messenger/proxy/ProxyController;->setName(Lorg/telegram/messenger/SharedConfig$ProxyInfo;Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 150
    :cond_1
    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized saveProxyList()V
    .locals 8

    monitor-enter p0

    .line 92
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 93
    new-instance v0, Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyList:Ljava/util/ArrayList;

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 94
    new-instance v1, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/proxy/ProxyController;)V

    invoke-static {v0, v1}, Ljava/util/Collections;->sort(Ljava/util/List;Ljava/util/Comparator;)V

    .line 105
    new-instance v1, Lorg/telegram/tgnet/SerializedData;

    invoke-direct {v1}, Lorg/telegram/tgnet/SerializedData;-><init>()V

    const/4 v2, -0x1

    .line 106
    invoke-virtual {v1, v2}, Lorg/telegram/tgnet/SerializedData;->writeInt32(I)V

    const/4 v2, 0x5

    .line 107
    invoke-virtual {v1, v2}, Lorg/telegram/tgnet/SerializedData;->writeByte(I)V

    .line 108
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v2

    .line 109
    invoke-virtual {v1, v2}, Lorg/telegram/tgnet/SerializedData;->writeInt32(I)V

    const/4 v3, 0x1

    sub-int/2addr v2, v3

    :goto_0
    const/4 v4, 0x0

    if-ltz v2, :cond_1

    .line 111
    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 112
    iget-object v6, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v6}, Lorg/telegram/utils/proxy/ProxySettings;->getAddress()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v1, v6}, Lorg/telegram/tgnet/SerializedData;->writeString(Ljava/lang/String;)V

    .line 113
    iget-object v6, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v6}, Lorg/telegram/utils/proxy/ProxySettings;->getPort()I

    move-result v6

    invoke-virtual {v1, v6}, Lorg/telegram/tgnet/SerializedData;->writeInt32(I)V

    .line 114
    iget-object v6, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v6}, Lorg/telegram/utils/proxy/ProxySettings;->getUser()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v1, v6}, Lorg/telegram/tgnet/SerializedData;->writeString(Ljava/lang/String;)V

    .line 115
    iget-object v6, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v6}, Lorg/telegram/utils/proxy/ProxySettings;->getPassword()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v1, v6}, Lorg/telegram/tgnet/SerializedData;->writeString(Ljava/lang/String;)V

    .line 116
    iget-object v6, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v6}, Lorg/telegram/utils/proxy/ProxySettings;->getSecret()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v1, v6}, Lorg/telegram/tgnet/SerializedData;->writeString(Ljava/lang/String;)V

    .line 117
    iget-wide v6, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    invoke-virtual {v1, v6, v7}, Lorg/telegram/tgnet/SerializedData;->writeInt64(J)V

    .line 118
    iget-wide v6, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->availableCheckTime:J

    invoke-virtual {v1, v6, v7}, Lorg/telegram/tgnet/SerializedData;->writeInt64(J)V

    .line 119
    iget-object v5, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v5}, Lorg/telegram/utils/proxy/ProxySettings;->getType()Lorg/telegram/utils/proxy/ProxySettings$Type;

    move-result-object v5

    sget-object v6, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-ne v5, v6, :cond_0

    move v4, v3

    :cond_0
    invoke-virtual {v1, v4}, Lorg/telegram/tgnet/SerializedData;->writeBool(Z)V

    add-int/lit8 v2, v2, -0x1

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 121
    :cond_1
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string/jumbo v2, "mainconfig"

    invoke-virtual {v0, v2, v4}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 122
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string/jumbo v2, "proxy_list"

    invoke-virtual {v1}, Lorg/telegram/tgnet/SerializedData;->toByteArray()[B

    move-result-object v3

    const/4 v4, 0x2

    invoke-static {v3, v4}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v3

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 123
    invoke-virtual {v1}, Lorg/telegram/tgnet/SerializedData;->cleanup()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 124
    monitor-exit p0

    return-void

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public declared-synchronized setCurrentProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)V
    .locals 0

    monitor-enter p0

    .line 82
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 83
    iput-object p1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 84
    sput-object p1, Lorg/telegram/messenger/SharedConfig;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 85
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized setName(Ljava/lang/String;Ljava/lang/String;)V
    .locals 3

    monitor-enter p0

    .line 266
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 267
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v0, :cond_0

    .line 268
    monitor-exit p0

    return-void

    .line 270
    :cond_0
    :try_start_1
    invoke-static {p2}, Lcom/exteragram/messenger/proxy/ProxyController;->normalizeName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    .line 272
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 275
    iget-object v1, p0, Lcom/exteragram/messenger/proxy/ProxyController;->proxyNames:Ljava/util/HashMap;

    const/4 v2, 0x1

    if-eqz v0, :cond_2

    .line 273
    :try_start_2
    invoke-virtual {v1, p1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    if-eqz p1, :cond_1

    goto :goto_0

    :cond_1
    const/4 v2, 0x0

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 275
    :cond_2
    invoke-virtual {v1, p1, p2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    .line 276
    invoke-static {p1, p2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    xor-int/2addr v2, p1

    :goto_0
    if-eqz v2, :cond_3

    .line 279
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->save()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 281
    :cond_3
    monitor-exit p0

    return-void

    :goto_1
    :try_start_3
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw p1
.end method

.method public declared-synchronized setName(Lorg/telegram/messenger/SharedConfig$ProxyInfo;Ljava/lang/String;)V
    .locals 0

    monitor-enter p0

    .line 262
    :try_start_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/proxy/ProxyController;->getProxyKey(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/proxy/ProxyController;->setName(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 263
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized sortProxyList(Ljava/util/List;ZLorg/telegram/messenger/SharedConfig$ProxyInfo;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lorg/telegram/messenger/SharedConfig$ProxyInfo;",
            ">;Z",
            "Lorg/telegram/messenger/SharedConfig$ProxyInfo;",
            ")V"
        }
    .end annotation

    monitor-enter p0

    .line 447
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/proxy/ProxyController;->ensureLoaded()V

    .line 448
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0, p1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 449
    new-instance v1, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0, p3, p2, v0}, Lcom/exteragram/messenger/proxy/ProxyController$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/proxy/ProxyController;Lorg/telegram/messenger/SharedConfig$ProxyInfo;ZLjava/util/ArrayList;)V

    invoke-static {p1, v1}, Ljava/util/Collections;->sort(Ljava/util/List;Ljava/util/Comparator;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 471
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method
