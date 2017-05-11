/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

public class RandomIntGenerator {
    private int max;
    private int min;

    public RandomIntGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int nextValue() {
        return this.min + (int)(Math.random() * (double)(this.max - this.min + 1));
    }

    public String toString() {
        return "RandomIntGenerator with range " + this.min + " to " + this.max;
    }
}

