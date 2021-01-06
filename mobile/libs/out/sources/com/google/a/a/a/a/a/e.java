package com.google.a.a.a.a.a;

final class e extends b {
    private final c a = new c();

    e() {
    }

    public final void a(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        } else if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        } else {
            this.a.a(th, true).add(th2);
        }
    }
}
