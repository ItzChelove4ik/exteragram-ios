.class public final synthetic Landroidx/media3/common/util/BackgroundThreadStateHandler$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Landroidx/media3/common/util/BackgroundThreadStateHandler;

.field public final synthetic f$1:Lcom/google/common/base/Function;


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/common/util/BackgroundThreadStateHandler;Lcom/google/common/base/Function;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/common/util/BackgroundThreadStateHandler$$ExternalSyntheticLambda1;->f$0:Landroidx/media3/common/util/BackgroundThreadStateHandler;

    iput-object p2, p0, Landroidx/media3/common/util/BackgroundThreadStateHandler$$ExternalSyntheticLambda1;->f$1:Lcom/google/common/base/Function;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/media3/common/util/BackgroundThreadStateHandler$$ExternalSyntheticLambda1;->f$0:Landroidx/media3/common/util/BackgroundThreadStateHandler;

    iget-object p0, p0, Landroidx/media3/common/util/BackgroundThreadStateHandler$$ExternalSyntheticLambda1;->f$1:Lcom/google/common/base/Function;

    invoke-static {v0, p0}, Landroidx/media3/common/util/BackgroundThreadStateHandler;->$r8$lambda$f0hFC73tfQBWeDExFIZmwNfoA-E(Landroidx/media3/common/util/BackgroundThreadStateHandler;Lcom/google/common/base/Function;)V

    return-void
.end method
