package com.github.johnmcguinness.lambdacalculus.ast;

import java.util.Objects;

public abstract class Expr {

    private Expr() {}

    public static Var var(String name) {
        return new Var(name);
    }

    public static Lam lam(String s, Expr e) {
        return new Lam(s, e);
    }

    public static App app(Expr f, Expr arg) {
        return new App(f, arg);
    }

    public static class App extends Expr {

        public final Expr f;
        public final Expr arg;

        private App(Expr f, Expr arg) {
            this.f = Objects.requireNonNull(f, "App.f must not be null");
            this.arg = Objects.requireNonNull(arg, "App.arg must not be null");
        }

        @Override
        public boolean equals(Object obj) {

            return (obj != null && obj instanceof App)
                        ? (((App) obj).f.equals(f) && ((App) obj).arg.equals(arg))
                        : false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + Objects.hashCode(this.f);
            hash = 79 * hash + Objects.hashCode(this.arg);
            return hash;
        }
    }

    public static class Lam extends Expr {

        public final String s;
        public final Expr e;

        private Lam(String s, Expr e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public boolean equals(Object obj) {

            return (obj != null && obj instanceof Lam)
                    ? (((Lam) obj).s.equals(s) && ((Lam) obj).e.equals(e))
                    : false;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 59 * hash + Objects.hashCode(s);
            hash = 59 * hash + Objects.hashCode(e);
            return hash;
        }
    }

    public static class Var extends Expr {

        public final String name;

        private Var(String name) {
            this.name = Objects.requireNonNull(name, "Var.name must not be null");
        }

        @Override
        public boolean equals(Object obj) {

            return (obj != null && obj instanceof Var)
                        ? (((Var) obj).name.equals(name))
                        : false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 41 * hash + Objects.hashCode(this.name);
            return hash;
        }
    }
}
