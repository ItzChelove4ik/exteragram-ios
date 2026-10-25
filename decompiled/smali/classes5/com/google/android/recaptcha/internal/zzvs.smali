.class public final Lcom/google/android/recaptcha/internal/zzvs;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/util/Map;

.field private final zzb:Ljava/util/Map;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzvq;[B)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p2, Ljava/util/HashMap;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzvq;->zzc()Ljava/util/Map;

    move-result-object v0

    invoke-direct {p2, v0}, Ljava/util/HashMap;-><init>(Ljava/util/Map;)V

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzvs;->zza:Ljava/util/Map;

    new-instance p2, Ljava/util/HashMap;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzvq;->zzd()Ljava/util/Map;

    move-result-object p1

    .line 2
    invoke-direct {p2, p1}, Ljava/util/HashMap;-><init>(Ljava/util/Map;)V

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzvs;->zzb:Ljava/util/Map;

    return-void
.end method


# virtual methods
.method public final synthetic zza()Ljava/util/Map;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzvs;->zza:Ljava/util/Map;

    return-object p0
.end method

.method public final synthetic zzb()Ljava/util/Map;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzvs;->zzb:Ljava/util/Map;

    return-object p0
.end method
