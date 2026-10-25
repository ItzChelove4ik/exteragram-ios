.class public Landroidx/webkit/internal/ApiFeature$NoFrameworkInternal;
.super Landroidx/webkit/internal/ApiFeature;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/webkit/internal/ApiFeature;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "NoFrameworkInternal"
.end annotation


# direct methods
.method public constructor <init>(Ljava/lang/String;)V
    .locals 1

    .line 133
    const-string v0, "IMPLEMENTATION_ONLY_FEATURE"

    invoke-direct {p0, v0, p1}, Landroidx/webkit/internal/ApiFeature;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public final isSupportedByFramework()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method
