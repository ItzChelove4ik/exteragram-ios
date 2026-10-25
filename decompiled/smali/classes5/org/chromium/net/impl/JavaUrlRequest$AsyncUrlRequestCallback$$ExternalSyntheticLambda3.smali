.class public final synthetic Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;


# instance fields
.field public final synthetic f$0:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

.field public final synthetic f$1:Lorg/chromium/net/UrlResponseInfo;

.field public final synthetic f$2:Ljava/nio/ByteBuffer;


# direct methods
.method public synthetic constructor <init>(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3;->f$0:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    iput-object p2, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3;->f$1:Lorg/chromium/net/UrlResponseInfo;

    iput-object p3, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3;->f$2:Ljava/nio/ByteBuffer;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3;->f$0:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3;->f$1:Lorg/chromium/net/UrlResponseInfo;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3;->f$2:Ljava/nio/ByteBuffer;

    invoke-static {v0, v1, p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->$r8$lambda$L2MmDQF1IP9TU3XYnKRRuW-u5Xs(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V

    return-void
.end method
