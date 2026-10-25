.class public abstract Landroidx/media3/common/util/TraceUtil;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static beginSection(Ljava/lang/String;)V
    .locals 0

    .line 38
    invoke-static {p0}, Landroid/os/Trace;->beginSection(Ljava/lang/String;)V

    return-void
.end method

.method public static endSection()V
    .locals 0

    .line 49
    invoke-static {}, Landroid/os/Trace;->endSection()V

    return-void
.end method
