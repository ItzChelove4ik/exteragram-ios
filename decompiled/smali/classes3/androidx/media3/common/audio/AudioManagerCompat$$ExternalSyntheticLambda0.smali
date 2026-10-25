.class public final synthetic Landroidx/media3/common/audio/AudioManagerCompat$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Landroid/content/Context;

.field public final synthetic f$1:Landroidx/media3/common/util/ConditionVariable;


# direct methods
.method public synthetic constructor <init>(Landroid/content/Context;Landroidx/media3/common/util/ConditionVariable;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/common/audio/AudioManagerCompat$$ExternalSyntheticLambda0;->f$0:Landroid/content/Context;

    iput-object p2, p0, Landroidx/media3/common/audio/AudioManagerCompat$$ExternalSyntheticLambda0;->f$1:Landroidx/media3/common/util/ConditionVariable;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/media3/common/audio/AudioManagerCompat$$ExternalSyntheticLambda0;->f$0:Landroid/content/Context;

    iget-object p0, p0, Landroidx/media3/common/audio/AudioManagerCompat$$ExternalSyntheticLambda0;->f$1:Landroidx/media3/common/util/ConditionVariable;

    invoke-static {v0, p0}, Landroidx/media3/common/audio/AudioManagerCompat;->$r8$lambda$1DKwaorNyy5mIYtOpjykiqi6f7A(Landroid/content/Context;Landroidx/media3/common/util/ConditionVariable;)V

    return-void
.end method
