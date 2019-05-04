package com.whatakitty.jmore.console.infrastructure.stream;

import java.io.PrintStream;
import lombok.RequiredArgsConstructor;

/**
 * adapter for print stream
 *
 * @author WhatAKitty
 * @date 2019/05/05
 * @description
 **/
@RequiredArgsConstructor
public final class PrintStreamAdapter {

    private final PrintStream out;

    public void print(boolean b) {
        printTip();
        out.print(b);
    }

    public void print(char c) {
        printTip();
        out.print(c);
    }

    public void print(int i) {
        printTip();
        out.print(i);
    }

    public void print(long l) {
        printTip();
        out.print(l);
    }

    public void print(float f) {
        printTip();
        out.print(f);
    }

    public void print(double d) {
        printTip();
        out.print(d);
    }

    public void print(char[] s) {
        printTip();
        out.print(s);
    }

    public void print(String s) {
        printTip();
        out.print(s);
    }

    public void print(Object obj) {
        printTip();
        out.print(obj);
    }

    public void println() {
        printTip();
        out.println();
    }

    public void println(boolean x) {
        printTip();
        out.println(x);
    }

    public void println(char x) {
        printTip();
        out.println(x);
    }

    public void println(int x) {
        printTip();
        out.println(x);
    }

    public void println(long x) {
        printTip();
        out.println(x);
    }

    public void println(float x) {
        printTip();
        out.println(x);
    }

    public void println(double x) {
        printTip();
        out.println(x);
    }

    public void println(char[] x) {
        printTip();
        out.println(x);
    }

    public void println(String x) {
        printTip();
        out.println(x);
    }

    public void println(Object x) {
        printTip();
        out.println(x);
    }

    private void printTip() {
        out.print("~ ");
    }

}
