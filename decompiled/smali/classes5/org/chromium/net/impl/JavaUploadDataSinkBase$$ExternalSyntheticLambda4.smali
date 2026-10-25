.class public final synthetic Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda4;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Ljava/lang/String;

.field public final synthetic f$1:Ljava/lang/Runnable;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda4;->f$0:Ljava/lang/String;

    iput-object p2, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda4;->f$1:Ljava/lang/Runnable;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda4;->f$0:Ljava/lang/String;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda4;->f$1:Ljava/lang/Runnable;

    invoke-static {v0, p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->$r8$lambda$Skz_oWstgCsE5yV2p_g6vrBh_QI(Ljava/lang/String;Ljava/lang/Runnable;)V

    return-void
.end method
