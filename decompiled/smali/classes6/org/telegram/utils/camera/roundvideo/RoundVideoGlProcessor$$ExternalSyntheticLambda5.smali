.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

.field public final synthetic f$1:Landroid/util/Size;

.field public final synthetic f$2:I

.field public final synthetic f$3:Z

.field public final synthetic f$4:[Ljava/lang/RuntimeException;

.field public final synthetic f$5:Ljava/util/concurrent/CountDownLatch;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Landroid/util/Size;IZ[Ljava/lang/RuntimeException;Ljava/util/concurrent/CountDownLatch;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$1:Landroid/util/Size;

    iput p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$2:I

    iput-boolean p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$3:Z

    iput-object p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$4:[Ljava/lang/RuntimeException;

    iput-object p6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$5:Ljava/util/concurrent/CountDownLatch;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 6

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$1:Landroid/util/Size;

    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$2:I

    iget-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$3:Z

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$4:[Ljava/lang/RuntimeException;

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;->f$5:Ljava/util/concurrent/CountDownLatch;

    invoke-static/range {v0 .. v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->$r8$lambda$V8xLYOIrG0lh7qmwkOuU5hwa8kM(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Landroid/util/Size;IZ[Ljava/lang/RuntimeException;Ljava/util/concurrent/CountDownLatch;)V

    return-void
.end method
