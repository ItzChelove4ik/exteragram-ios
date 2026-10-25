.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlh;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/function/BinaryOperator;


# static fields
.field static final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlh;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlh;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlh;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlh;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlh;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic apply(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    .line 1
    iget-object p0, p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzme;->zza:[Ljava/lang/Object;

    iget p2, p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzme;->zzb:I

    invoke-virtual {p1, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzme;->zzb([Ljava/lang/Object;I)V

    return-object p1
.end method
