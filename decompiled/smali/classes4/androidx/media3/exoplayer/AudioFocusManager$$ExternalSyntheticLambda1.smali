.class public final synthetic Landroidx/media3/exoplayer/AudioFocusManager$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/common/base/Supplier;


# instance fields
.field public final synthetic f$0:Landroid/content/Context;


# direct methods
.method public synthetic constructor <init>(Landroid/content/Context;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/exoplayer/AudioFocusManager$$ExternalSyntheticLambda1;->f$0:Landroid/content/Context;

    return-void
.end method


# virtual methods
.method public final get()Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Landroidx/media3/exoplayer/AudioFocusManager$$ExternalSyntheticLambda1;->f$0:Landroid/content/Context;

    invoke-static {p0}, Landroidx/media3/exoplayer/AudioFocusManager;->$r8$lambda$9-ZDmzK1qMcz_wrnYIW8vbeeQek(Landroid/content/Context;)Landroid/media/AudioManager;

    move-result-object p0

    return-object p0
.end method
