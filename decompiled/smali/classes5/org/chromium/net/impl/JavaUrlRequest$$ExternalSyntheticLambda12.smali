.class public final synthetic Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda12;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/chromium/net/impl/JavaUrlRequest;

.field public final synthetic f$1:Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;


# direct methods
.method public synthetic constructor <init>(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda12;->f$0:Lorg/chromium/net/impl/JavaUrlRequest;

    iput-object p2, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda12;->f$1:Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda12;->f$0:Lorg/chromium/net/impl/JavaUrlRequest;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda12;->f$1:Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;

    invoke-static {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest;->$r8$lambda$bMvZoSNaUr4APxhn21vnlY_ECAQ(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    return-void
.end method
