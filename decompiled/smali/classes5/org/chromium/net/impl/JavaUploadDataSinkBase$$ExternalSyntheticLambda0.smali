.class public final synthetic Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;


# instance fields
.field public final synthetic f$0:Lorg/chromium/net/impl/JavaUploadDataSinkBase;

.field public final synthetic f$1:Z


# direct methods
.method public synthetic constructor <init>(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Z)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda0;->f$0:Lorg/chromium/net/impl/JavaUploadDataSinkBase;

    iput-boolean p2, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda0;->f$1:Z

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda0;->f$0:Lorg/chromium/net/impl/JavaUploadDataSinkBase;

    iget-boolean p0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda0;->f$1:Z

    invoke-static {v0, p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->$r8$lambda$Zs_uXTVqIwC-sg8cGt-9I-Wb3F8(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Z)V

    return-void
.end method
