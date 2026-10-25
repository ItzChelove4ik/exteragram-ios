.class public final synthetic Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Ljava/lang/Runnable;

.field public final synthetic f$1:Landroid/content/Context;

.field public final synthetic f$2:I

.field public final synthetic f$3:Lorg/telegram/ui/Components/BulletinFactory;

.field public final synthetic f$4:I

.field public final synthetic f$5:Ljava/lang/CharSequence;

.field public final synthetic f$6:I

.field public final synthetic f$7:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/Runnable;Landroid/content/Context;ILorg/telegram/ui/Components/BulletinFactory;ILjava/lang/CharSequence;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$0:Ljava/lang/Runnable;

    iput-object p2, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$1:Landroid/content/Context;

    iput p3, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$2:I

    iput-object p4, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$3:Lorg/telegram/ui/Components/BulletinFactory;

    iput p5, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$4:I

    iput-object p6, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$5:Ljava/lang/CharSequence;

    iput p7, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$6:I

    iput-object p8, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$7:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 8

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$0:Ljava/lang/Runnable;

    iget-object v1, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$1:Landroid/content/Context;

    iget v2, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$2:I

    iget-object v3, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$3:Lorg/telegram/ui/Components/BulletinFactory;

    iget v4, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$4:I

    iget-object v5, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$5:Ljava/lang/CharSequence;

    iget v6, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$6:I

    iget-object v7, p0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;->f$7:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static/range {v0 .. v7}, Lcom/exteragram/messenger/ai/AiController;->$r8$lambda$UrxaUoTrlqMH85byRPLNTm58_Dc(Ljava/lang/Runnable;Landroid/content/Context;ILorg/telegram/ui/Components/BulletinFactory;ILjava/lang/CharSequence;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-void
.end method
