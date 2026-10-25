.class public final synthetic Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda28;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/common/util/Consumer;


# instance fields
.field public final synthetic f$0:Z


# direct methods
.method public synthetic constructor <init>(Z)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-boolean p1, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda28;->f$0:Z

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 0

    .line 0
    iget-boolean p0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda28;->f$0:Z

    check-cast p1, Landroidx/media3/session/PlayerWrapper;

    invoke-static {p0, p1}, Landroidx/media3/session/MediaSessionStub;->$r8$lambda$7iJyAqo_yFvIK6ewaRF71VXX7as(ZLandroidx/media3/session/PlayerWrapper;)V

    return-void
.end method
