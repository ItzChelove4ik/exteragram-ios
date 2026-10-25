.class public final synthetic Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda14;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/chromium/net/impl/JavaUrlRequest;

.field public final synthetic f$1:Lorg/chromium/net/UrlResponseInfo;


# direct methods
.method public synthetic constructor <init>(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda14;->f$0:Lorg/chromium/net/impl/JavaUrlRequest;

    iput-object p2, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda14;->f$1:Lorg/chromium/net/UrlResponseInfo;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda14;->f$0:Lorg/chromium/net/impl/JavaUrlRequest;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda14;->f$1:Lorg/chromium/net/UrlResponseInfo;

    invoke-static {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest;->$r8$lambda$r4_dhdLDT53SSUzE8bdt2qsjO3Q(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/UrlResponseInfo;)V

    return-void
.end method
