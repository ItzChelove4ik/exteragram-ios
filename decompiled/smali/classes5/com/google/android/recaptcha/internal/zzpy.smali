.class public abstract Lcom/google/android/recaptcha/internal/zzpy;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field zza:Ljava/util/Map;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza()Ljava/util/Map;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzpy;->zza:Ljava/util/Map;

    if-nez v0, :cond_0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzpj;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzpj;-><init>()V

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzpy;->zza:Ljava/util/Map;

    :cond_0
    return-object v0
.end method
