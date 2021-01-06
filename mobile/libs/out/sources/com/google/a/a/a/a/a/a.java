package com.google.a.a.a.a.a;

public final class a {
    private static b a;

    /* renamed from: com.google.a.a.a.a.a.a$a reason: collision with other inner class name */
    static final class C0000a extends b {
        C0000a() {
        }

        public final void a(Throwable th, Throwable th2) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    static {
        /*
            java.lang.Integer r0 = a()     // Catch:{ Throwable -> 0x006e }
            if (r0 == 0) goto L_0x001b
            int r1 = r0.intValue()     // Catch:{ Throwable -> 0x002c }
            r2 = 19
            if (r1 < r2) goto L_0x001b
            com.google.a.a.a.a.a.f r1 = new com.google.a.a.a.a.a.f     // Catch:{ Throwable -> 0x002c }
            r1.<init>()     // Catch:{ Throwable -> 0x002c }
        L_0x0013:
            a = r1
            if (r0 == 0) goto L_0x001a
            r0.intValue()
        L_0x001a:
            return
        L_0x001b:
            java.lang.String r1 = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic"
            boolean r1 = java.lang.Boolean.getBoolean(r1)     // Catch:{ Throwable -> 0x002c }
            if (r1 != 0) goto L_0x0066
            r1 = 1
        L_0x0024:
            if (r1 == 0) goto L_0x0068
            com.google.a.a.a.a.a.e r1 = new com.google.a.a.a.a.a.e     // Catch:{ Throwable -> 0x002c }
            r1.<init>()     // Catch:{ Throwable -> 0x002c }
            goto L_0x0013
        L_0x002c:
            r1 = move-exception
        L_0x002d:
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.Class<com.google.a.a.a.a.a.a$a> r3 = com.google.a.a.a.a.a.a.C0000a.class
            java.lang.String r3 = r3.getName()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r3)
            int r5 = r5.length()
            int r5 = r5 + 132
            r4.<init>(r5)
            java.lang.String r5 = "An error has occured when initializing the try-with-resources desuguring strategy. The default strategy "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r4 = "will be used. The error is: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            java.io.PrintStream r2 = java.lang.System.err
            r1.printStackTrace(r2)
            com.google.a.a.a.a.a.a$a r1 = new com.google.a.a.a.a.a.a$a
            r1.<init>()
            goto L_0x0013
        L_0x0066:
            r1 = 0
            goto L_0x0024
        L_0x0068:
            com.google.a.a.a.a.a.a$a r1 = new com.google.a.a.a.a.a.a$a     // Catch:{ Throwable -> 0x002c }
            r1.<init>()     // Catch:{ Throwable -> 0x002c }
            goto L_0x0013
        L_0x006e:
            r1 = move-exception
            r0 = 0
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.a.a.a.a.a.a.<clinit>():void");
    }

    private static Integer a() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    public static void a(Throwable th, Throwable th2) {
        a.a(th, th2);
    }
}
