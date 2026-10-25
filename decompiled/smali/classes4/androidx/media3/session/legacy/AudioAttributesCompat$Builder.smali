.class public Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/media3/session/legacy/AudioAttributesCompat;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field final builderImpl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 319
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 320
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1a

    if-lt v0, v1, :cond_0

    .line 321
    new-instance v0, Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImplApi26$Builder;

    invoke-direct {v0}, Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImplApi26$Builder;-><init>()V

    iput-object v0, p0, Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;->builderImpl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;

    return-void

    .line 323
    :cond_0
    new-instance v0, Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImplApi21$Builder;

    invoke-direct {v0}, Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImplApi21$Builder;-><init>()V

    iput-object v0, p0, Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;->builderImpl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;

    return-void
.end method


# virtual methods
.method public build()Landroidx/media3/session/legacy/AudioAttributesCompat;
    .locals 1

    .line 347
    new-instance v0, Landroidx/media3/session/legacy/AudioAttributesCompat;

    iget-object p0, p0, Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;->builderImpl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;

    invoke-interface {p0}, Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;->build()Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;

    move-result-object p0

    invoke-direct {v0, p0}, Landroidx/media3/session/legacy/AudioAttributesCompat;-><init>(Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;)V

    return-object v0
.end method

.method public setContentType(I)Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;
    .locals 1

    .line 388
    iget-object v0, p0, Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;->builderImpl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;

    invoke-interface {v0, p1}, Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;->setContentType(I)Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;

    return-object p0
.end method

.method public setFlags(I)Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;
    .locals 1

    .line 401
    iget-object v0, p0, Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;->builderImpl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;

    invoke-interface {v0, p1}, Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;->setFlags(I)Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;

    return-object p0
.end method

.method public setUsage(I)Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;
    .locals 1

    .line 372
    iget-object v0, p0, Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;->builderImpl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;

    invoke-interface {v0, p1}, Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;->setUsage(I)Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl$Builder;

    return-object p0
.end method
