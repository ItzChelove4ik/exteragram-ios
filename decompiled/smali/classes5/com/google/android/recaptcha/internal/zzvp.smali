.class public abstract Lcom/google/android/recaptcha/internal/zzvp;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/Class;

.field private final zzb:Ljava/lang/Class;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/Class;Ljava/lang/Class;[B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzvp;->zza:Ljava/lang/Class;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzvp;->zzb:Ljava/lang/Class;

    return-void
.end method

.method public static zzc(Lcom/google/android/recaptcha/internal/zzvo;Ljava/lang/Class;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvp;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzvn;

    invoke-direct {v0, p1, p2, p0}, Lcom/google/android/recaptcha/internal/zzvn;-><init>(Ljava/lang/Class;Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzvo;)V

    return-object v0
.end method


# virtual methods
.method public final zza()Ljava/lang/Class;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzvp;->zza:Ljava/lang/Class;

    return-object p0
.end method

.method public final zzb()Ljava/lang/Class;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzvp;->zzb:Ljava/lang/Class;

    return-object p0
.end method
