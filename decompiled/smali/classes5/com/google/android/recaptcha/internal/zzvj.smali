.class final Lcom/google/android/recaptcha/internal/zzvj;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Iterable;


# instance fields
.field final synthetic zza:Ljava/util/List;

.field final synthetic zzb:Ljava/util/List;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzvm;Ljava/util/List;Ljava/util/List;)V
    .locals 0

    .line 1
    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzvj;->zza:Ljava/util/List;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzvj;->zzb:Ljava/util/List;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final iterator()Ljava/util/Iterator;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzvj;->zzb:Ljava/util/List;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzvj;->zza:Ljava/util/List;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzvl;

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    const/4 v2, 0x0

    invoke-direct {v1, p0, v0, v2}, Lcom/google/android/recaptcha/internal/zzvl;-><init>(Ljava/util/Iterator;Ljava/util/Iterator;[B)V

    return-object v1
.end method
