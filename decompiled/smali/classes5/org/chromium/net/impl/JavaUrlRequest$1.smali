.class Lorg/chromium/net/impl/JavaUrlRequest$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/chromium/net/impl/JavaUrlRequest;->followRedirect()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/chromium/net/impl/JavaUrlRequest;


# direct methods
.method public constructor <init>(Lorg/chromium/net/impl/JavaUrlRequest;)V
    .locals 0

    .line 514
    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest$1;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .line 517
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$1;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmPendingRedirectUrl(Lorg/chromium/net/impl/JavaUrlRequest;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fputmCurrentUrl(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/String;)V

    .line 518
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$1;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fputmPendingRedirectUrl(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/String;)V

    .line 519
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$1;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$mfireOpenConnection(Lorg/chromium/net/impl/JavaUrlRequest;)V

    return-void
.end method
