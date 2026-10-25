.class public abstract Lcom/google/android/recaptcha/internal/zzpz;
.super Lcom/google/android/recaptcha/internal/zzoz;
.source "SourceFile"

# interfaces
.implements Ljava/io/Serializable;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<K:",
        "Ljava/lang/Object;",
        "V:",
        "Ljava/lang/Object;",
        ">",
        "Lcom/google/android/recaptcha/internal/zzoz<",
        "TK;TV;>;",
        "Ljava/io/Serializable;"
    }
.end annotation


# instance fields
.field final transient map:Lcom/google/android/recaptcha/internal/zzpw;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/google/android/recaptcha/internal/zzpw<",
            "TK;+",
            "Lcom/google/android/recaptcha/internal/zzpq<",
            "TV;>;>;"
        }
    .end annotation
.end field

.field final transient size:I


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzpw;I)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzoz;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzpz;->map:Lcom/google/android/recaptcha/internal/zzpw;

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzpz;->size:I

    return-void
.end method


# virtual methods
.method public synthetic zza()Ljava/util/Map;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpz;->map:Lcom/google/android/recaptcha/internal/zzpw;

    return-object p0
.end method
