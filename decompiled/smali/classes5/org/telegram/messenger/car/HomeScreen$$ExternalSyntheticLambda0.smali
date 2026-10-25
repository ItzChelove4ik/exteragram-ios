.class public final synthetic Lorg/telegram/messenger/car/HomeScreen$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/messenger/car/HomeScreen;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/messenger/car/HomeScreen;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/car/HomeScreen$$ExternalSyntheticLambda0;->f$0:Lorg/telegram/messenger/car/HomeScreen;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/messenger/car/HomeScreen$$ExternalSyntheticLambda0;->f$0:Lorg/telegram/messenger/car/HomeScreen;

    invoke-virtual {p0}, Landroidx/car/app/Screen;->invalidate()V

    return-void
.end method
