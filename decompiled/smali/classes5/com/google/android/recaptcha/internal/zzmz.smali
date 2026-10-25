.class final Lcom/google/android/recaptcha/internal/zzmz;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field final synthetic zza:Lorg/chromium/net/UrlRequest;


# direct methods
.method public constructor <init>(Ljava/lang/String;Lorg/chromium/net/UrlRequest;)V
    .locals 0

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzmz;->zza:Lorg/chromium/net/UrlRequest;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 1
    check-cast p1, Ljava/lang/Throwable;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzmz;->zza:Lorg/chromium/net/UrlRequest;

    .line 2
    invoke-virtual {p0}, Lorg/chromium/net/UrlRequest;->cancel()V

    .line 1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
