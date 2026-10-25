.class final synthetic Lcom/google/android/recaptcha/internal/zzaz;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field private final synthetic zza:Lcom/google/android/recaptcha/internal/zzbb;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzbb;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaz;->zza:Lcom/google/android/recaptcha/internal/zzbb;

    return-void
.end method


# virtual methods
.method public final synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p2, Landroid/view/MotionEvent;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaz;->zza:Lcom/google/android/recaptcha/internal/zzbb;

    check-cast p1, Ljava/lang/String;

    invoke-static {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzbb;->zzf(Lcom/google/android/recaptcha/internal/zzbb;Ljava/lang/String;Landroid/view/MotionEvent;)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method
