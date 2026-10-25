.class public final synthetic Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/concurrent/Executor;


# instance fields
.field public final synthetic f$0:Ljava/util/concurrent/Executor;

.field public final synthetic f$1:I

.field public final synthetic f$2:Z

.field public final synthetic f$3:I


# direct methods
.method public synthetic constructor <init>(Ljava/util/concurrent/Executor;IZI)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;->f$0:Ljava/util/concurrent/Executor;

    iput p2, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;->f$1:I

    iput-boolean p3, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;->f$2:Z

    iput p4, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;->f$3:I

    return-void
.end method


# virtual methods
.method public final execute(Ljava/lang/Runnable;)V
    .locals 3

    .line 0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;->f$0:Ljava/util/concurrent/Executor;

    iget v1, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;->f$1:I

    iget-boolean v2, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;->f$2:Z

    iget p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;->f$3:I

    invoke-static {v0, v1, v2, p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->$r8$lambda$ecpyyaPHU986yHiuxnDquO9vmIo(Ljava/util/concurrent/Executor;IZILjava/lang/Runnable;)V

    return-void
.end method
