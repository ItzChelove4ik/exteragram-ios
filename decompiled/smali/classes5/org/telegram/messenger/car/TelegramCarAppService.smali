.class public Lorg/telegram/messenger/car/TelegramCarAppService;
.super Landroidx/car/app/CarAppService;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 10
    invoke-direct {p0}, Landroidx/car/app/CarAppService;-><init>()V

    return-void
.end method


# virtual methods
.method public createHostValidator()Landroidx/car/app/validation/HostValidator;
    .locals 1

    .line 15
    sget-boolean v0, Lorg/telegram/messenger/BuildVars;->DEBUG_VERSION:Z

    if-eqz v0, :cond_0

    .line 16
    sget-object p0, Landroidx/car/app/validation/HostValidator;->ALLOW_ALL_HOSTS_VALIDATOR:Landroidx/car/app/validation/HostValidator;

    return-object p0

    .line 19
    :cond_0
    new-instance v0, Landroidx/car/app/validation/HostValidator$Builder;

    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    invoke-direct {v0, p0}, Landroidx/car/app/validation/HostValidator$Builder;-><init>(Landroid/content/Context;)V

    sget p0, Landroidx/car/app/R$array;->hosts_allowlist_sample:I

    .line 20
    invoke-virtual {v0, p0}, Landroidx/car/app/validation/HostValidator$Builder;->addAllowedHosts(I)Landroidx/car/app/validation/HostValidator$Builder;

    move-result-object p0

    .line 21
    invoke-virtual {p0}, Landroidx/car/app/validation/HostValidator$Builder;->build()Landroidx/car/app/validation/HostValidator;

    move-result-object p0

    return-object p0
.end method

.method public onCreateSession()Landroidx/car/app/Session;
    .locals 0

    .line 27
    new-instance p0, Lorg/telegram/messenger/car/TelegramCarSession;

    invoke-direct {p0}, Lorg/telegram/messenger/car/TelegramCarSession;-><init>()V

    return-object p0
.end method
