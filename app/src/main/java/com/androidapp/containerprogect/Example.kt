package com.androidapp.containerprogect

class A(b: B, c: C)

class B(d: D)

class C(e: E)

class D(g: G)

class G()

class E()

class Test() {

    init {
        A(B(D(G())), C(E()))
    }
}
