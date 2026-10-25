.class public final synthetic Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/chromium/net/impl/JavaUploadDataSinkBase;

.field public final synthetic f$1:Ljava/lang/String;

.field public final synthetic f$2:Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;


# direct methods
.method public synthetic constructor <init>(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Ljava/lang/String;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda2;->f$0:Lorg/chromium/net/impl/JavaUploadDataSinkBase;

    iput-object p2, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda2;->f$1:Ljava/lang/String;

    iput-object p3, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda2;->f$2:Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda2;->f$0:Lorg/chromium/net/impl/JavaUploadDataSinkBase;

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda2;->f$1:Ljava/lang/String;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda2;->f$2:Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;

    invoke-static {v0, v1, p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->$r8$lambda$THk-oE5eTe5Y90I_YQZikeVqrI4(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Ljava/lang/String;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    return-void
.end method
