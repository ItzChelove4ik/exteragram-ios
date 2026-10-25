.class public final synthetic Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:I

.field public final synthetic f$1:Z

.field public final synthetic f$2:I

.field public final synthetic f$3:Ljava/lang/Runnable;


# direct methods
.method public synthetic constructor <init>(IZILjava/lang/Runnable;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;->f$0:I

    iput-boolean p2, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;->f$1:Z

    iput p3, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;->f$2:I

    iput-object p4, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;->f$3:Ljava/lang/Runnable;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 0
    iget v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;->f$0:I

    iget-boolean v1, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;->f$1:Z

    iget v2, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;->f$2:I

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;->f$3:Ljava/lang/Runnable;

    invoke-static {v0, v1, v2, p0}, Lorg/chromium/net/impl/JavaUrlRequest;->$r8$lambda$8Jo97Oa6sMq63KSfyXfVRlvw9I0(IZILjava/lang/Runnable;)V

    return-void
.end method
