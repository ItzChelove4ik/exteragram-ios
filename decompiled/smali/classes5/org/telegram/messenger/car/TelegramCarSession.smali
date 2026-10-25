.class public Lorg/telegram/messenger/car/TelegramCarSession;
.super Landroidx/car/app/Session;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 9
    invoke-direct {p0}, Landroidx/car/app/Session;-><init>()V

    return-void
.end method


# virtual methods
.method public onCreateScreen(Landroid/content/Intent;)Landroidx/car/app/Screen;
    .locals 0

    .line 14
    new-instance p1, Lorg/telegram/messenger/car/HomeScreen;

    invoke-virtual {p0}, Landroidx/car/app/Session;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object p0

    invoke-direct {p1, p0}, Lorg/telegram/messenger/car/HomeScreen;-><init>(Landroidx/car/app/CarContext;)V

    return-object p1
.end method
